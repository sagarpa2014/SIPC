package mx.gob.comer.sipc.pagos.action;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.CuentaBancaria;
import mx.gob.comer.sipc.domain.FileItem;
import mx.gob.comer.sipc.domain.OficioPagos;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.vistas.domain.PagosV;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.Valid;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ProcesamientoArchivosPagosAction extends ActionSupport implements SessionAware {

	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;		

	@Valid
	//variables para generacion del archivo
	private CatalogosDAO cDAO = new CatalogosDAO();
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private PagosDAO pagosDAO = new PagosDAO();
	private Pagos pagos = new Pagos();
	private CuentaBancaria cuenta = new CuentaBancaria();
	private OficioPagos oficio = new OficioPagos();
	private String nombreArchivo;
	private List<FileItem>   logs;
	private List<String>     lstLog =new ArrayList<String>();
	//Variables de session
	protected Long 		idUsuario;
	protected Integer 	rol;
	protected Integer	urIdSession;
	// Variables de carga de archivo
	private File	fileUpload;
	private String	fileUploadContentType;
	private String	fileUploadFileName;	
	private String 	docFileName;
	private String log;
	private boolean errorBand = false;
	// Context
	private ServletContext			context;
	private Map<String,Object> userSession;
	private Map<String,Object> session;
	
	public String execute(){		
		return SUCCESS;
	}

	public void validate(){		
		
	}

	public String ProcesarArchivoPagos() throws Exception{
		//getDatosSession();
		session = ActionContext.getContext().getSession();
		
		// Constantes
		final Integer statusIdPagoEfectuado = 5; // Pago Efectuado
		final Integer statusIdPagoNoEfectuado = 6; // Pago No Efectuado		

		boolean bandPagoNoEfectuado = false;
		Long idOficio = null;
		
		//String patternArchivoDepositosTESOFE = "^DEV_IEX99(\\d{4})S(\\d{5}).IN.OUT$"; // nombre del archivo
		String patternRegistroDetalleDepositosTESOFE = "^(0|1|2|3|4|5|6|7|8|9)\\|(\\d{26}\\s{4})\\|(\\d{1,2})\\|(.{3})\\|(\\d{1,10})\\|(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})\\|(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})\\|(.{1,250})\\|(.{18})\\|(.{40})\\|(\\d{18})\\|(\\d{1,36})((\\.\\d{1,2})|(\\.))?\\|(\\d{1,10})*\\|(RECHAZO|CONFIRMADO)$"; // estructura registro detalle
		Pattern regDetalle = Pattern.compile("^(0|1|2|3|4|5|6|7|8|9)\\|(\\d{26}\\s{4})\\|(\\d{1,2})\\|(.{3})\\|(\\d{1,10})\\|(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})\\|(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})\\|(.{1,250})\\|(.{18})\\|(.{40})\\|(\\d{18})\\|(.{1,39})\\|(\\d{1,10})*\\|(RECHAZO|CONFIRMADO)$");
		
		// Obtiene la fecha actual para el archivo
		Calendar calendario = GregorianCalendar.getInstance();
		Date fecha = calendario.getTime();
		
		nombreArchivo = fileUploadFileName;

		// Agrega mensajes a LOG
		AppLogger.info( "pagos", "*** Procesamiento Archivo Respuesta de Pagos (INICIO) - Archivo Respuesta de Pagos: "+nombreArchivo+"***" );
		addLogLista("*** Procesamiento Archivo Respuesta de Pagos (INICIO) - Archivo Respuesta de Pagos: "+nombreArchivo+"***", true);
		
    	// Valida la nomenclatura del Nombre de Archivo Respuesta a Procesar
		// Obtiene la ruta del directorio donde se depositan los archivos de pago respuesta
		String rutaRespuestaArchivos =  utileriasDAO.getParametros("RUTA_RESPUESTA_ARCHIVOS");					
		if(!rutaRespuestaArchivos.endsWith(File.separator)){
			rutaRespuestaArchivos += File.separator;
		}
		
		// Abre el Archivo Respuesta a Procesar de solo Lectura
		File file = new File(rutaRespuestaArchivos+nombreArchivo); 
    	BufferedReader reader = null;
    	
    	try { 
    		// Lee el Archivo Respuesta a Procesar
    		reader = new BufferedReader(new FileReader(file)); 
    		String linea = null; 
    		int pagosEfectuados = 0;
    		int pagosNoEfectuados = 0;
    		int totalPagos = 0;
    		PagosV  pago = null;
    		while ((linea = reader.readLine()) != null) {
    			// Verifica el tipo de registro del archivo: 0 o 3 - DETALLE
				if (!linea.substring(0, 1).equals("C")) {
					// Valida la estructura del registro detalle 
					if (linea.matches(patternRegistroDetalleDepositosTESOFE)) {
		    			Matcher detMatcher = regDetalle.matcher(linea);
		    			String cveRastreo = null;
		    			String clabe = null;
		    			Double importe = null;
		    			String fechaPago = null;
		    			String estatus = null;
		    			if (detMatcher.find()){
			    			cveRastreo = detMatcher.group(2);
			    			clabe = detMatcher.group(15);
			    			importe = Double.parseDouble(detMatcher.group(16));
			    			estatus = detMatcher.group(1);
			    			fechaPago = detMatcher.group(9)+"/"+detMatcher.group(10)+"/"+detMatcher.group(11);
		    			}
						// Valida que la Clave de Rastreo del Registro Detalle corresponda al Pago de la BD y 
						// regresa el Importe del Pago registrado en la BD
						pago = pagosDAO.validaExisteCveRastreo(cveRastreo);
						if (pago!=null){
							// Obtiene id Oficio relacionado con el pago procesado
							idOficio = pago.getIdOficio();
							// Valida que la CLABE del Registro Detalle y del Pago sean la misma
							if (!pago.getClabe().equals(clabe)){
								errorBand = true;
								AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, tiene una CLABE: "+clabe+" que difiere al del Pago: "+pago.getClabe());
								addLogLista("Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, tiene una CLABE: "+clabe+" que difiere al del Pago: "+pago.getClabe(), false);
				    			//break;
							} // end if (!pago.getClabe().equals(detMatcher.group(11)))
							// Valida que el Importe del Registro Detalle y del Pago sean el mismo
							if (!pago.getImporte().equals(importe)){
								errorBand = true;
								AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, tiene un Importe: "+importe+" que difiere al del Pago: "+pago.getImporte());
								addLogLista("Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, tiene un Importe: "+importe+" que difiere al del Pago: "+pago.getImporte(), false);
				    			//break;
							} // end if (!pago.getImporte().equals(Double.parseDouble(detMatcher.group(12))))
			    			// Actualiza el Pago de estatus "ENVIADO" a "EFECTUADO"
			    			if(!errorBand) {		    			
				    			pagos = pagosDAO.getPagos(pago.getIdPago());
				    			if(estatus.equals("0")){
				    				pagosEfectuados += 1;
				    				pagos.setEstatus(statusIdPagoEfectuado);
					    			bandPagoNoEfectuado = false;
				    				AppLogger.info( "pagos", "Pago - Clave Rastreo: "+cveRastreo+" CLABE: "+clabe+" Importe: "+importe+" Fecha Pago: "+fechaPago+" Estatus: EFECTUADO");
					    			addLogLista("Pago - Clave Rastreo: "+cveRastreo+" CLABE: "+clabe+" Importe: "+importe+" Fecha Pago: "+fechaPago+" Estatus: EFECTUADO", true);
				    			} else {
				    				pagosNoEfectuados += 1;
					    			pagos.setEstatus(statusIdPagoNoEfectuado);
					    			bandPagoNoEfectuado = true;
					    			AppLogger.info( "pagos", "Pago - Clave Rastreo: "+cveRastreo+" CLABE: "+clabe+" Importe: "+importe+" Fecha Pago: "+fechaPago+" Estatus: NO EFECTUADO");
					    			addLogLista("Pago - Clave Rastreo: "+cveRastreo+" CLABE: "+clabe+" Importe: "+importe+" Fecha Pago: "+fechaPago+" Estatus: NO EFECTUADO", true);
				    			}
				    			pagos.setUsuarioModificacion((Integer)session.get("idUsuario"));
				    			pagos.setFechaModificacion(fecha);
				    			cDAO.guardaObjeto(pagos);
				    			if(bandPagoNoEfectuado){
					    			// Actualiza la cuenta bancaria a estatus = 0 (INACTIVA)
					    			cuenta = pagosDAO.getCuentaBancaria(pago.getClabe());
					    			cuenta.setEstatus(0);
					    			cDAO.guardaObjeto(cuenta);					    				
				    			}
			    			} // end if(!errorBand)
						} else {
							errorBand = true;
							AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, no existe en la BD o ya fue procesada");
							addLogLista("Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, no existe en la BD o ya fue procesada", false);
			    			//break;												
						} // end if (pago!=null)
					} else {
						errorBand = true;
						AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Registro '"+linea+"' del Archivo, no cumple con la estructura valida" );
						addLogLista("Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Secuencia '"+linea+"' del Archivo, no cumple con la estructura valida", false);
		    			//break;
					} // end if (estructuraValidaRegistroDetalleDepositos.find())									
	    		} // end if (!linea.substring(0, 1).equals("C"))
    		} // end while ((linea = reader.readLine()) != null)
    		if(!errorBand){
    			totalPagos = pagosEfectuados+ pagosNoEfectuados;
    			if(totalPagos > 0){
    				if (pago!=null){
    					/*Consigue el oficio relacionado con el pago*/
    					OficioPagos op = pagosDAO.consultaOficiosPago(pago.getIdOficio(), null,0).get(0);
    					log = "Se le informa que ha sido procesada la respuesta del Archivo de Pagos: "+op.getArchivoEnvio()
    					+" - Programa: "+ pago.getNombrePgrCorto()+ (pagosEfectuados > 0 ?" - Pagos Efectuados: "+pagosEfectuados:"")
    					+(pagosNoEfectuados > 0 ?" - Pagos No Efectuados: "+pagosNoEfectuados:"")+" - Total Pagos: "+totalPagos;
    					
    					EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
    					mensajes.enviarMensaje((Integer) session.get("idUsuario"), 5, log, "Aviso");
    				}
    			}
    		}
    		
    		//Actualiza el nombre del archivo de respuesta en la tabla "oficios_pago"
    		oficio = pagosDAO.getOficioPagos(idOficio);
    		oficio.setArchivoRespuesta(nombreArchivo);
    		cDAO.guardaObjeto(oficio);	
    		/*Envia Aviso*/
	    		
	    		
	    		
    	} catch (FileNotFoundException e) { 
			errorBand = true;
			AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Archivo "+nombreArchivo+" no existe en el directorio de 'archivosTESOFERespuesta'" );					    		
			addLogLista("Procesamiento de Archivo Respuesta de Pagos: Archivo "+nombreArchivo+" no existe en el directorio de 'archivosTESOFERespuesta'", false);
    	} catch (IOException e) { 
			errorBand = true;
			AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Problemas en la lectura del Archivo "+nombreArchivo );					    		
			addLogLista("Procesamiento de Archivo Respuesta de Pagos: Problemas en la lectura del Archivo "+nombreArchivo, false);
    	} finally { 
    		try { 
    			if (reader != null) { 
    				reader.close();
    				AppLogger.info( "pagos", "Archivo Respuesta de Pagos Procesado: "+nombreArchivo );
    				addLogLista("Archivo Respuesta de Pagos Procesado: "+nombreArchivo, true);
    			} 
    		} catch (IOException e) { 
				errorBand = true;
				AppLogger.error( "pagos", "Procesamiento de Archivo Respuesta de Pagos: Problemas al cerrar el Archivo "+nombreArchivo );					    		
				addLogLista("Procesamiento de Archivo Respuesta de Pagos: Problemas al cerrar el Archivo "+nombreArchivo, false);
    		} 
    	}
		AppLogger.info( "pagos", "*** Procesamiento Archivo Respuesta de Pagos (FIN) - Archivo Respuesta de Pagos: "+nombreArchivo+"***" );
		addLogLista("*** Procesamiento Archivo Respuesta de Pagos (FIN) - Archivo Respuesta de Pagos: "+nombreArchivo+"***", true);

		return SUCCESS;
	} // end ProcesarArchivoPagos()


	@SuppressWarnings("unused")
	private String getRealPath(String path)
	{
		return TextUtil.formatPath(context.getRealPath(path));
	}
	@SuppressWarnings("unused")
	private List<FileItem> formatLogs(String path, String[] logs){
		if(!path.endsWith(File.separator)){
			path += File.separator;
		}
		List<FileItem> nLogs = new ArrayList<FileItem>( logs.length );
		for(String log:logs){
			String size = "";
			try{
				size = bytesToMeg(new File( path + log).length());
			}catch(Exception ignore){}
			nLogs.add( new FileItem( log, size ) );
		}
		return nLogs;
	}
	
	private String bytesToMeg(long bytes) {
		String kb = "";
          if( bytes == 0){
        	  kb = "0 Kb";
          }else if(bytes>1024){
        	  if( ((bytes/1024)/1024)>1 ){
        		  kb = ((bytes/1024)/1024)+" Mb";  
        	  }else{
        		  kb = (bytes/1024)+" Kb";  
        	  }  
          }else{
        	  kb = bytes + " Bytes";  
          }
          return kb;      
	}	

	private void addLogLista(String msj, boolean info) {
		String valor="";
		if(lstLog.size()==0){
			lstLog.add(msj);
		}else if(lstLog.size()<50){
			if(info){
				valor="[INFO]";
			}else{
				valor="[ERROR]";
			}
			lstLog.add(valor+" - "+msj);
		}else if(lstLog.size()==50){
			lstLog.add("Siga la traza completa en el log....");
		}
		
	}

	public String cargarArchivo() throws Exception{	
		try{					
			// Verifica que se haya introducido un nombre de archivo a procesar
			if(fileUploadFileName!=null&&!fileUploadFileName.isEmpty()){
				// Obtiene la ruta del directorio donde se depositan los archivos de pago respuesta
				String rutaRespuestaArchivos =  utileriasDAO.getParametros("RUTA_RESPUESTA_ARCHIVOS");					
				if(!rutaRespuestaArchivos.endsWith(File.separator)){
					rutaRespuestaArchivos += File.separator;
				}
				// Verifica que solo se procesen archivos con nomenclaturas validas (CHEQUES, DEPOSITOS u ORDENES DE PAGO)
				StringBuilder nombreArchivoCopia = new StringBuilder(rutaRespuestaArchivos);
				nombreArchivoCopia.append(fileUploadFileName);
				File copia = new File(nombreArchivoCopia.toString());
				if(!copia.exists()){
					//Copia archivo del cliente al servidor
					FileUtils.copyFile(fileUpload, copia);				
				}				
				return ProcesarArchivoPagos();
			}
		}
		catch (Exception e){
			AppLogger.info("Procesamiento de Archivos de Pagos TESOFE (RESPUESTA) Problemas al copiar archivo al servidor: "+e.getMessage());					    		
			return INPUT;
		}
		return INPUT;
	}
	
	public String ProcesarArchivoPagosRec() throws Exception{	
		try{					
			// Verifica que se haya introducido un nombre de archivo a procesar
			if(fileUploadFileName!=null&&!fileUploadFileName.isEmpty()){
				Pattern patternArchivoRechazosTESOFE = Pattern.compile("^(\\d{1,10})-CLC-RECHAZO.txt$");
				// Obtiene la ruta del directorio donde se depositan los archivos de pago respuesta
				String rutaRespuestaArchivos =  utileriasDAO.getParametros("RUTA_RESPUESTA_ARCHIVOS");					
				if(!rutaRespuestaArchivos.endsWith(File.separator)){
					rutaRespuestaArchivos += File.separator;
				}
				// Verifica que solo se procesen archivos con nomenclaturas validas (CHEQUES, DEPOSITOS u ORDENES DE PAGO)
				Matcher archMatcher = patternArchivoRechazosTESOFE.matcher(fileUploadFileName);
				if (archMatcher.find()) {
					StringBuilder nombreArchivoCopia = new StringBuilder(rutaRespuestaArchivos);
					nombreArchivoCopia.append(fileUploadFileName);
					File copia = new File(nombreArchivoCopia.toString());
					if(!copia.exists()){
						//	Copia archivo del cliente al servidor
						FileUtils.copyFile(fileUpload, copia);				
					}
					
					cargarArchivoRechazo();
					return SUCCESS;
					
				} else {
					addActionError("La nomenclatura del Archvo de rechazo no es valida, ejemplo: (111-CLC-RECHAZO)");
			    	return INPUT;
				} // end else
			} else {
				addActionError("No se ha introducido un nombre de archivo a procesar");
				return INPUT;
			} // end else
		}
		catch (Exception e){
			addActionError("Problemas al copiar archivo al servidor: "+e.getMessage());
			//error = 1;	
			return INPUT;
		}
	}
			
	
	public String cargarArchivoRechazo() throws Exception  {
		String linea = null, cveRastreo = null, desRechazo=null; 
		int estatus=6;
		Pagos pago = null;
		List<Pagos> lstPagos = null;
		boolean error = false;
		File file = new File(fileUpload.toString()); 
    	BufferedReader reader = null; 
		try{
	    	reader = new BufferedReader(new FileReader(file)); 
	    	Pattern regDetalle= Pattern.compile("^(.{1,50})\\|(\\d{1,50})\\|(\\d{1,50})\\|(\\d{1,50})\\|(.{1,50})\\|(.{1,50})\\|(\\d{1,50})\\|(\\d{1,50})\\|(\\d{1,50})\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(.{1,50})\\|(.{1,50})\\|(\\d{1,50})\\|(.{1,50})\\|(\\d{1,50})\\|(.{1,50})\\|\\/(.{1,50})\\/(.{1,50})\\/(.{1,50})\\/(\\d{1,50})\\/(.{1,50})\\/(.{1,50})\\|\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(.{1,50})\\|(.{1,50})\\|(\\d{1,50})\\|(.{1,50})\\|(.{1,50})\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(\\d{1,50})\\|(\\d{1,50})\\|\\|(\\d{1,50})\\|(.{1,250})\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(\\d{1,50})\\/(\\d{1,50})\\/(\\d{1,50})\\|(.{1,250})\\|(\\d{1,50})\\|(.{1,250})\\|\\|(.{1,250})\\|(.{1,250})\\|\\|(.{1,250})\\|(.{1,250})$");
	    	nombreArchivo = fileUploadFileName;
	    	AppLogger.info( "pagos", "*** Procesamiento Archivo Respuesta Rechazo de Pagos (INICIO) - Archivo Respuesta de Pagos: "+nombreArchivo+"***" );
			addLogLista("*** Procesamiento Archivo Respuesta Rechazo de Pagos (INICIO) - Archivo Respuesta de Pagos: "+nombreArchivo+"***", true);
	    	while ((linea = reader.readLine()) != null) {
	    		Matcher detMatcher = regDetalle.matcher(linea);
	    		if (!linea.substring(0, 1).equals("C")) {
		    		if (detMatcher.find()){
		    			cveRastreo = detMatcher.group(52);
		    			desRechazo = detMatcher.group(54);
		    			lstPagos = pagosDAO.consultaPagos(0, cveRastreo, null, estatus);
		    			if(lstPagos.size()>0){
		    				pago = lstPagos.get(0); 
		    				pago.setDescRechazo(desRechazo);
		    				cDAO.guardaObjeto(pago);	    				
		    			}else{
		    				AppLogger.info( "atr", "Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, no se encuenrtra en la base de datos");
							addLogLista("Procesamiento de Archivo Respuesta de Pagos: Registro Detalle - Cve. Rastreo '"+cveRastreo+"' del Archivo, no se encuenrtra en la base de datos", false);
		    				continue;
		    			}
		    			//Valida que el pago exista
		    			addLogLista("Archivo Respuesta Rechazos Pagos con clave de rastreo número: "+cveRastreo+" Procesado: se actualizó la descripción del pago "+desRechazo+" de acuerdo al archivo "+nombreArchivo, true);
		    			
		    			
					}else{
						error = true;
		
					}
	    		}
	    		
	    	} 
	    	if (error){
	    		addActionError("El archivo no es un rechazo");
	    	}
		
		}catch (FileNotFoundException e) { 
			errorBand = true;
			AppLogger.error("pagos", "Procesamiento de Archivo Respuesta de Pagos: Archivo "+nombreArchivo+" no existe en el directorio de 'archivosTESOFERespuesta'" );					    		
			addLogLista("Procesamiento de Archivo Respuesta de Pagos: Archivo "+nombreArchivo+" no existe en el directorio de 'archivosTESOFERespuesta'", false);
    	} catch (IOException e) { 
			errorBand = true;
			AppLogger.error("pagos", "Procesamiento de Archivo Respuesta de Pagos: Problemas en la lectura del Archivo "+nombreArchivo );					    		
			addLogLista("Procesamiento de Archivo Respuesta de Pagos: Problemas en la lectura del Archivo "+nombreArchivo, false);
    	} finally { 
    		try { 
    			if (reader != null) { 
    				reader.close();
    				AppLogger.info("pagos", "Archivo Respuesta de Pagos Procesado: "+nombreArchivo );
    				addLogLista("Archivo Respuesta de Pagos Procesado: "+nombreArchivo, true);
    			} 
    		} catch (IOException e) { 
				errorBand = true;
				AppLogger.error("pagos", "Procesamiento de Archivo Respuesta de Pagos: Problemas al cerrar el Archivo "+nombreArchivo );					    		
				addLogLista("Procesamiento de Archivo Respuesta de Pagos: Problemas al cerrar el Archivo "+nombreArchivo, false);
    		} 	
	    }
		AppLogger.info("pagos", "*** Procesamiento Archivo Respuesta de Pagos (FIN) - Archivo Respuesta de Pagos: "+nombreArchivo+"***" );
		addLogLista("*** Procesamiento Archivo Respuesta de Pagos (FIN) - Archivo Respuesta de Pagos: "+nombreArchivo+"***", true);

		return SUCCESS;	
	}	// end cargarArchivoRechazo()
	
			
	public Pagos getPagos() {
		return pagos;
	}

	public void setPagos(Pagos pagos) {
		this.pagos = pagos;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public List<FileItem> getLogs() {
		return logs;
	}

	public void setLogs(List<FileItem> logs) {
		this.logs = logs;
	}

	public List<String> getLstLog() {
		return lstLog;
	}

	public void setLstLog(List<String> lstLog) {
		this.lstLog = lstLog;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public Integer getUrIdSession() {
		return urIdSession;
	}

	public void setUrIdSession(Integer urIdSession) {
		this.urIdSession = urIdSession;
	}
	
	public File getFileUpload()
	{
		return fileUpload;
	}

	public void setFileUpload(File fileUpload)
	{
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType()
	{
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType)
	{
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName()
	{
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName)
	{
		this.fileUploadFileName = fileUploadFileName;
	}	
	
	public boolean isErrorBand() {
		return errorBand;
	}

	public void setErrorBand(boolean errorBand) {
		this.errorBand = errorBand;
	}

	/* Implementar Session aware*/
	public void setSession(Map<String,Object> session) {
	    this.userSession = session;
	 }
	public Map<String,Object> getSession() {
	    return userSession;
	}
	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context)
	{
		this.context = context;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
