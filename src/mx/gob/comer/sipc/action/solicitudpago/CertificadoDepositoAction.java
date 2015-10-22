package mx.gob.comer.sipc.action.solicitudpago;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.AlmacenGeneralDeposito;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.domain.transaccionales.AsignacionCartasAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.CertificadosDeposito;
import mx.gob.comer.sipc.domain.transaccionales.DocumentacionSPCartaAdhesion;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.BodegasV;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CertificadoDepositoAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private String folioCartaAdhesion;
	private int idComprador;
	private Comprador comprador;
	private Integer numCampos;
	private List<AlmacenGeneralDeposito> lstAlmacenadora;
	private List<Bodegas> lstBodegas;
	private List<CertificadosDeposito> lstCertificadosDeposito;
	private List<AsignacionCartasAdhesion> lstCultivo;
	private List<AsignacionCartasAdhesion> lstVariedad;
	private Integer[] selectedAlmacenadora;
	private String[] capBodega;
	private String[] capFolio;
	private Date[] selectFechaExpedicion;
	private Date[] selectFechaFinVigencia;
	private Double[] capVolumen;
	private Integer[] selectedCultivo;
	private Integer[] selectedVariedad;
	private int registrar;
	private int agregarCer;
	private String msjOk;
	private String claveBodega;
	private int idAlmacen;
	private String folio;
	private int errorClaveBodega;
	private Double totalVolumen;
	private Programa programa;
	private int idPrograma;
	private String nombreArchivo;
	private String ext;
	private File doc; 
	private String docFileName;
	private boolean band;
	private int tipoCargaCer;
	private BodegasV bodegasV;
	private String msjError;
	private Integer estatus;
	
	public String capCertificadoDeposito(){
		try{
			System.out.println("registrar "+registrar);
			System.out.println("agregarCer "+agregarCer);
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			idComprador = ca.getIdComprador();
			// Recupera los datos del comprador
			comprador = cDAO.consultaComprador(idComprador).get(0);
			//verifica si se han cargado los certificados para determinar el valor de registrar 
			if(spDAO.consultaCertificadosDeposito(folioCartaAdhesion).size()>0 && agregarCer == 0){
				registrar = 2;
				agregarCertificadoDeposito();
			}else if (spDAO.consultaCertificadosDeposito(folioCartaAdhesion).size()>0 && agregarCer == 1){
				registrar = 0;
			}else{
				registrar = 0;
			}
			tipoCargaCer = 1;
			
			estatus = ca.getEstatus();
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;		
	}
	
	
	public String lstCertificadoDeposito() {
		try{
			lstAlmacenadora = cDAO.consultaAlmacenadora(0);
			lstCultivo = spDAO.consultaCultivo(folioCartaAdhesion, 0);
			lstVariedad = spDAO.consultaVariedad(folioCartaAdhesion,0);
			
			lstCertificadosDeposito = spDAO.consultaCertificadosDeposito(folioCartaAdhesion);
			totalVolumen = spDAO.getSumaCertificadoDepositoByFolioCA(folioCartaAdhesion);
			registrar = 2;
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en lstCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en lstCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;		
	}
	public String agregarCertificadoDeposito(){
		try{			
			lstCertificadosDeposito = new ArrayList<CertificadosDeposito>();
			lstAlmacenadora = cDAO.consultaAlmacenadora(0);
			lstCultivo = spDAO.consultaCultivo(folioCartaAdhesion, 0);
			lstVariedad = spDAO.consultaVariedad(folioCartaAdhesion,0);
			
			if(registrar == 0 && numCampos != null){
				for(int i=1; i<=numCampos; i++){
					lstCertificadosDeposito.add(new CertificadosDeposito());
				}
			}else{	
				//Consulta los certificados depositos para la carta de adhesion
				lstCertificadosDeposito = spDAO.consultaCertificadosDeposito(folioCartaAdhesion);
				totalVolumen = spDAO.getSumaCertificadoDepositoByFolioCA(folioCartaAdhesion);
				numCampos = lstCertificadosDeposito.size(); 
			}
		
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en agregarCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en agregarCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;	
	}
	
	public String registroManualCertificadoDeposito(){
		try{
			boolean error = false;
			if (numCampos != null && numCampos > 0){
				System.out.println("registraCertificadoDeposito");
				for (int i = 0; i < selectedAlmacenadora.length; i++) {
					lstAlmacenadora = cDAO.consultaAlmacenadora(selectedAlmacenadora[i]);
					String almacenadora = lstAlmacenadora.get(0).getNombre();
					//Guarda el certificado de deposito
					CertificadosDeposito cd = new CertificadosDeposito();
					cd.setClaveBodega(capBodega[i]);
					cd.setFechaExpedicion(selectFechaExpedicion[i]);
					cd.setFechaFinVigencia(selectFechaFinVigencia[i]);
					cd.setFolio(capFolio[i].toUpperCase());
					cd.setFolioCartaAdhesion(folioCartaAdhesion);
					cd.setIdAlmacenadora(selectedAlmacenadora[i]);
					cd.setIdCultivo(selectedCultivo[i]);
					cd.setIdVariedad(selectedVariedad[i]);
					cd.setVolumen(capVolumen[i]);
					
					if (!capFolio[i].toUpperCase().matches("^([A-Z]{1,2})(\\d{1,10})$")){						
						error = true;
						addActionError("El folio "+capFolio[i]+", del registro "+(i+1)+", no cumple con la nomenclatura establecida (letra, letra/numero, numeros ejem. AB123456), favor de verificarlo");						
					}else if (selectedAlmacenadora[i] != null && capFolio[i] != null){
						lstCertificadosDeposito = spDAO.consultaCertificadosDeposito("", selectedAlmacenadora[i], "", capFolio[i]);
						if((lstCertificadosDeposito.size()>0)){
							error = true;
							addActionError("El almacen "+almacenadora+" y folio "+capFolio[i]+" ya se encuentra registrado en la carta de adhesion "+   lstCertificadosDeposito.get(0).getFolioCartaAdhesion()+ ", favor de verificarlo");
						}
					}else if(cDAO.consultaBodegas(capBodega[i]).size()==0){
						error= true;
						addActionError("La clave de la Bodega "+capBodega[i]+" de la fila "+(i+1)+" no existe en la base de datos, favor de verificarlo");
					}else if(!(cDAO.consultaAlmacenadora(selectedAlmacenadora[i]).size()>0)){
						error = true;
						addActionError("El almacen "+almacenadora+" de la fila "+(i+1)+" no existe en la base de datos, favor de verificarlo");
					}else if(cDAO.consultaCultivo(selectedCultivo[i]).size()==0){
						error= true;
						addActionError("El cultivo "+selectedCultivo[i]+" en la fila "+(i+1)+" no existe en la base de datos, favor de verificarlo");
					}else if(cDAO.consultaVariedad(selectedVariedad[i].toString()).size()==0){
						error= true;
						addActionError("La variedad "+selectedVariedad[i]+" en la fila "+(i+1)+" no existe en la base de datos, favor de verificarlo");
					}
					if (error){
						addActionError("No se guardo el registro de la fila "+(i+1));
					}else{
						cDAO.guardaObjeto(cd);
					}
				}
			}
			capCertificadoDeposito();
			agregarCertificadoDeposito();
			if (!error){
				msjOk = "Se registró satisfactoriamente la información";
			}
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}

	
	public String registroArchivoCertificadoDeposito(){
		try{
			System.out.println("folioCA "+folioCartaAdhesion);
			if(docFileName!=null){
				ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf(".") );
				if (ext.equals(".xls")){
					if(doc != null){
						leerDocCerDeposito();
						 if (band){
							System.out.println("Se ha encontrado un error en el Archivo");
							addActionError("Se ha encontrado un error en el Archivo");
							capCertificadoDeposito();
							agregarCertificadoDeposito();
							return SUCCESS;
							
						 }
					 }
				}else{
					addActionError("El archivo que intento cargar no es del tipo .xls es "+ext+" , favor de verificarlo!");
					return SUCCESS;
				}
				capCertificadoDeposito();
				agregarCertificadoDeposito();
			}
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	
	public String validaClaveBodega(){
		try{
			System.out.println("Clave bodega "+claveBodega);
			//Verifica que la claveBodega exista en la base de datos
			if(!(cDAO.consultaBodegas(claveBodega).size()>0)){
				errorClaveBodega = 1;
			}
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
		
	
	public String validaAlmaBodegaFolio(){
		try{
			//Valida que el almacen-bodega-folio no se encuentren repetidos en la base de datos
			
			
			
			
			
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en validaAlmaBodegaFolio  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validaAlmaBodegaFolio  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	private String getRecuperaRutaCarta() throws JDBCException, Exception {
		String  nomRutaCartaAdhesion = "";
		System.out.println("folioCartaAdhesion "+folioCartaAdhesion);
		//Recupera la ruta de la solicitud de pago
		AsignacionCAaEspecialistaV acaaev= spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		idPrograma = acaaev.getIdPrograma();
		//Recupera la ruta del programa
		programa = cDAO.consultaPrograma(idPrograma).get(0);
		nomRutaCartaAdhesion = folioCartaAdhesion.replaceAll("-", "");
		return programa.getRutaDocumentos()+"SolicitudPago/"+acaaev.getIdOficioCASP()+"/"+nomRutaCartaAdhesion+"/";
		 
	}
	
	public boolean leerDocCerDeposito() throws JDBCException, Exception{
		try{
			boolean error = false, fechaExpInvalida = false, fechaVigInvalida = false;
			int almacen = 0;
			int registrosGuardados = 0;
			int registrosNoGuadados = 0;
			String bodega = "";
			String folio = "";
			Date fechaExpedicion = null;
			Date fechaVigencia  = null;
			Integer cultivo  = 0;
			Integer variedad  = 0;
			double volumen  = 0;
			String carta = "";
			String rutaCartaAdhesion = getRecuperaRutaCarta();
			DocumentacionSPCartaAdhesion documento = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 10).get(0);
			ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf(".") );
			nombreArchivo = "Certificados"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc);
			documento.setRutaCertificados(rutaCartaAdhesion+nombreArchivo+ext);
			int contFilas = 0;
			/* We should now load excel objects and loop through the worksheet data */
			FileInputStream input_document = new FileInputStream(doc);
			/* Load workbook */
			HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);
			/* Load worksheet */
			HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
			// we loop through and insert data
			Iterator<Row> rowIterator = my_worksheet.iterator(); 
			
			while(rowIterator.hasNext()) {
				CertificadosDeposito cd = new CertificadosDeposito();
				error = false;
				fechaExpInvalida  = false;
				fechaVigInvalida  = false;
				Row row = rowIterator.next(); 
				Iterator<Cell> cellIterator = row.cellIterator();
				if (contFilas >= 1){
					int contColumnas = 0;
					while(cellIterator.hasNext()) {
						String valorChar = "";
						Double valorNum = 0.0;
						Date valorDate = null;
						
						Cell cell = cellIterator.next();
						System.out.println("cell "+cell);
						System.out.println("index"+cell.getColumnIndex()+1);
						
						if (contColumnas <=8 ){						
							switch(cell.getCellType()) {
							
							case Cell.CELL_TYPE_STRING: //handle string columns
								//System.out.println(cell.getStringCellValue());
								valorChar = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC: //handle double data
								if (HSSFDateUtil.isCellDateFormatted(cell)){
									valorDate = cell.getDateCellValue();									
								}else{
									valorNum = cell.getNumericCellValue();									
								}
								break;
							case Cell.CELL_TYPE_BLANK:
								if (contFilas == 1){
									addActionError("El archivo no cuenta con el Lay-out establecido (Id del Almacen, Clave Bodega, Folio, Fecha Expedicion, Fecha Fin Vigencia, Id del Cultivo, Id de la Variedad, Volumen, Folio Carta Adhesion) favor de verificarlo");
								}
								msjOk = "Se guardaron exitosamente "+registrosGuardados+" registros";
								msjError = "No se guardo el registro de la fila "+contFilas+", debido a que tiene campos en blanco";
								return true;
							}// end switch(cell.getCellType())
							
							switch(contColumnas) {
							
							case 0: // Columna ALMACENADORA
								if (valorNum != null){
									almacen = valorNum.intValue();
									System.out.println(almacen);
								}// if (valorNum != null)
								break;
							case 1: // Columna BODEGA
								if (valorChar != null){
									System.out.println(valorChar);
									bodega = valorChar;
									bodega = bodega.trim();
								}
								break;
							case 2: // Columna FOLIO
								if (valorChar != null || valorNum !=0){
									if (valorNum != 0){
										Integer entero = valorNum.intValue();
										folio = entero.toString();
										System.out.println(folio);
									}else{
										folio = valorChar.trim();
										System.out.println(folio);
									}
								}
								break;
							case 3: // Columna FECHA EXPEDICION
								if (valorDate != null){
									fechaExpedicion = valorDate;
									System.out.println(fechaExpedicion);
								}else{
									String fechaExp = valorChar.trim();
									try{
										SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
										fechaExpedicion = formatter.parse(fechaExp);
									}catch(Exception e){
										fechaExpInvalida = true;
									}									
								}
								break;
							case 4: // Columna FECHA FIN VIGENCIA
								if (valorDate != null){
									fechaVigencia = valorDate;
									System.out.println(fechaVigencia);
								}else{
									String fechaVig = valorChar.trim();
									try{
										SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
										fechaVigencia = formatter.parse(fechaVig);
									}catch(Exception e){
										fechaVigInvalida = true;
									}								
								}
								break;
							case 5: // Columna CULTIVO
								if (valorNum != null){
									cultivo = valorNum.intValue();
									//int num = (int)Math.floor(valorNum);
									System.out.println(cultivo);
								}
								break;
							case 6: // Columna VARIEDAD
								if (valorNum != null){
									variedad = valorNum.intValue();
									//int num = (int)Math.floor(valorNum);
									System.out.println(variedad);
								}
								break;									
							case 7: // Columna VOLUMEN
								if (valorNum != null){
									volumen = valorNum;
									System.out.println(volumen);
								}
								break;
							case 8: // Columna FOLIO CARTA ADHESION
								if (valorChar != null){
									carta = valorChar.trim();
									System.out.println(carta);
								}
								break;
							}// switch(contColumnas)
						}// if (contColumnas <=8 )
						if (contColumnas == 8){
							break;
						}
						contColumnas += 1;
					}// while(cellIterator.hasNext())
					System.out.println("VALIDACIONES TODAS");
					if (contColumnas != 8){
						addActionError("El archivo no cuenta con el Lay-out establecido (Id del Almacen, Clave Bodega, Folio, Fecha Expedicion, Fecha Fin Vigencia, Id del Cultivo, Id de la Variedad, Volumen, Folio Carta Adhesion) favor de verificarlo");
						return band;
					}else if(!carta.equals(folioCartaAdhesion)){
						error= true;
						addActionError("El folio de la Carta Adhesion "+carta+" en la fila "+(contFilas+1)+" no corresponde a la del Participante "+folioCartaAdhesion);
					}else if (!folio.matches("^([A-Z]{1,2})(\\d{1,10})$")){						
						error = true;
						addActionError("El folio "+folio+", del registro "+(contFilas+1)+", no cumple con la nomenclatura establecida (letra, letra/numero, numeros ejem. AB123456), favor de verificarlo");						
					}else if(cDAO.consultaBodegas(bodega).size()==0){
						error= true;
						addActionError("La clave de la Bodega "+bodega+" de la fila "+(contFilas+1)+" no existe en la base de datos, favor de verificarlo");
					}else if(!(cDAO.consultaAlmacenadora(almacen).size()>0)){
						error = true;
						addActionError("La clave de la almacenadora "+almacen+" de la fila "+(contFilas+1)+" no existe en la base de datos, favor de verificarlo");
					}else if(!(spDAO.consultaCultivo(folioCartaAdhesion, cultivo).size()>0)){
						error= true;
						addActionError("El cultivo "+cultivo+" en la fila "+(contFilas+1)+" no corresponde al del Participante, favor de verificarlo");
					}else if(!(spDAO.consultaVariedad(folioCartaAdhesion, variedad).size()>0)){
						error= true;
						addActionError("La variedad "+variedad+" en la fila "+(contFilas+1)+" no corresponde al del Participante, favor de verificarlo");
					}else if(fechaExpInvalida){
						error= true;
						addActionError("La fecha de expedición es invalida en la fila "+(contFilas+1));
					}else if(fechaVigInvalida){
						error= true;
						addActionError("La fecha de vigencia es invalida en la fila "+(contFilas+1));
					}else if (almacen != 0 && folio != null){
						lstCertificadosDeposito = spDAO.consultaCertificadosDeposito("", almacen, "", folio);
						if((lstCertificadosDeposito.size()>0)){
							error = true;
							addActionError("El almacen "+almacen+" y folio "+folio+" ya se encuentra registrado en la carta de adhesion "+   lstCertificadosDeposito.get(0).getFolioCartaAdhesion()+ ", favor de verificarlo");
						}
					}
					
					if (error){
						registrosNoGuadados += 1;
						//addActionError("No se guardo el registro de la fila "+(contFilas+1));
					}else{
						registrosGuardados += 1; 
						cd.setIdAlmacenadora(almacen);
						cd.setClaveBodega(bodega);
						cd.setFolio(folio);
						cd.setFechaExpedicion(fechaExpedicion);
						cd.setFechaFinVigencia(fechaVigencia);
						cd.setIdCultivo(cultivo);
						cd.setIdVariedad(variedad);
						cd.setVolumen(volumen);
						cd.setFolioCartaAdhesion(carta);
						cDAO.guardaObjeto(cd);
					}
				}// if (contFilas >= 1)
				contFilas += 1;
			}// while(rowIterator.hasNext())
			if (registrosGuardados !=0){
				msjOk = "Se guardaron "+registrosGuardados+" registros";
				if (registrosNoGuadados >= 0){
					msjError = "No se guardaron "+registrosNoGuadados+" registros";
				}
			}else if (registrosNoGuadados >= 0 && registrosGuardados ==0){
				msjError = "No se guardaron "+registrosNoGuadados+" registros";
			}
			/* Close input stream */
			input_document.close();
		}catch(JDBCException e){ // try
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en leerDocCerDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e){ // catch(JDBCException e)
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en leerDocCerDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} // catch(Exception e)
		return band;
	}
	
	public String verDetalleBodegas(){
		try{
			/*Recupera los datos de la Bodega*/
			setBodegasV((cDAO.consultaBodegasV(claveBodega).get(0)));
		
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	
	
	public String cargaIndividual(){
		return SUCCESS;
	}
	public String cargaMasiva(){
		return SUCCESS;
	}
	
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	public Integer getNumCampos() {
		return numCampos;
	}

	public void setNumCampos(Integer numCampos) {
		this.numCampos = numCampos;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}
	
	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	
	/*****Listas******/
	public List<AlmacenGeneralDeposito> getLstAlmacenadora() {
		return lstAlmacenadora;
	}

	public void setLstAlmacenadora(List<AlmacenGeneralDeposito> lstAlmacenadora) {
		this.lstAlmacenadora = lstAlmacenadora;
	}

	public List<Bodegas> getLstBodegas() {
		return lstBodegas;
	}

	public void setLstBodegas(List<Bodegas> lstBodegas) {
		this.lstBodegas = lstBodegas;
	}

	public List<CertificadosDeposito> getLstCertificadosDeposito() {
		return lstCertificadosDeposito;
	}

	public void setLstCertificadosDeposito(
			List<CertificadosDeposito> lstCertificadosDeposito) {
		this.lstCertificadosDeposito = lstCertificadosDeposito;
	}
	
	public Integer[] getSelectedAlmacenadora() {
		return selectedAlmacenadora;
	}

	public void setSelectedAlmacenadora(Integer[] selectedAlmacenadora) {
		this.selectedAlmacenadora = selectedAlmacenadora;
	}

	public String[] getCapBodega() {
		return capBodega;
	}

	public void setCapBodega(String[] capBodega) {
		this.capBodega = capBodega;
	}

	public String[] getCapFolio() {
		return capFolio;
	}

	public void setCapFolio(String[] capFolio) {
		this.capFolio = capFolio;
	}

	public Date[] getSelectFechaExpedicion() {
		return selectFechaExpedicion;
	}

	public void setSelectFechaExpedicion(Date[] selectFechaExpedicion) {
		this.selectFechaExpedicion = selectFechaExpedicion;
	}

	public Date[] getSelectFechaFinVigencia() {
		return selectFechaFinVigencia;
	}

	public void setSelectFechaFinVigencia(Date[] selectFechaFinVigencia) {
		this.selectFechaFinVigencia = selectFechaFinVigencia;
	}

	public Double[] getCapVolumen() {
		return capVolumen;
	}

	public void setCapVolumen(Double[] capVolumen) {
		this.capVolumen = capVolumen;
	}
	
	public Double getTotalVolumen() {
		return totalVolumen;
	}

	public void setTotalVolumen(Double totalVolumen) {
		this.totalVolumen = totalVolumen;
	}

	public String getClaveBodega() {
		return claveBodega;
	}

	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public int getErrorClaveBodega() {
		return errorClaveBodega;
	}

	public void setErrorClaveBodega(int errorClaveBodega) {
		this.errorClaveBodega = errorClaveBodega;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}	
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public Integer[] getSelectedCultivo() {
		return selectedCultivo;
	}

	public void setSelectedCultivo(Integer[] selectedCultivo) {
		this.selectedCultivo = selectedCultivo;
	}

	public Integer[] getSelectedVariedad() {
		return selectedVariedad;
	}

	public void setSelectedVariedad(Integer[] selectedVariedad) {
		this.selectedVariedad = selectedVariedad;
	}

	public int getTipoCargaCer() {
		return tipoCargaCer;
	}

	public void setTipoCargaCer(int tipoCargaCer) {
		this.tipoCargaCer = tipoCargaCer;
	}

	public int getAgregarCer() {
		return agregarCer;
	}

	public void setAgregarCer(int agregarCer) {
		this.agregarCer = agregarCer;
	}

	public BodegasV getBodegasV() {
		return bodegasV;
	}

	public void setBodegasV(BodegasV bodegasV) {
		this.bodegasV = bodegasV;
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}

	public List<AsignacionCartasAdhesion> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<AsignacionCartasAdhesion> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}

	public List<AsignacionCartasAdhesion> getLstVariedad() {
		return lstVariedad;
	}

	public void setLstVariedad(List<AsignacionCartasAdhesion> lstVariedad) {
		this.lstVariedad = lstVariedad;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
}
