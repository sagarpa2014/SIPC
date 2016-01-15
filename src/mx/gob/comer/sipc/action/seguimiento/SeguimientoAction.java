package mx.gob.comer.sipc.action.seguimiento;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import jxl.CellType;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.SeguimientoDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Ejercicios;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Usuarios;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.domain.catalogos.CapacidadesBodegas;
import mx.gob.comer.sipc.domain.catalogos.Ciclo;
import mx.gob.comer.sipc.domain.catalogos.Regional;
import mx.gob.comer.sipc.domain.catalogos.Variedad;
import mx.gob.comer.sipc.domain.transaccionales.SeguimientoCentroAcopio;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.ReporteSeguimientoAcopio;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.CiclosSeguimientoV;
import mx.gob.comer.sipc.vistas.domain.CultivosSeguimientoV;
import mx.gob.comer.sipc.vistas.domain.EstadosSeguimientoV;
import mx.gob.comer.sipc.vistas.domain.OperadoresBodegasV;
import mx.gob.comer.sipc.vistas.domain.ReporteSeguimientoAcopioV;
import mx.gob.comer.sipc.vistas.domain.ResumenAvanceAcopioV;
import mx.gob.comer.sipc.vistas.domain.SeguimientoCentroAcopioV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;

import com.lowagie.text.Document;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SeguimientoAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{
	private Map<String, Object> session;
	private Integer idCiclo;
	private int idCicloAux;
	private Integer ejercicio;
	private Integer ejercicioAux;
	private Date periodoInicial;
	private Date periodoFinal;
	private String claveBodega;
	private String claveBodegaAux;
	private String  operadorCentroAcopio;
	private String nombreCentroAcopio;
	private String nomRegionalCentroAcopio;
	private int idCultivo;
	private int idCultivoAux;
	private Double volumenMercadoLibre;
	private Double volumenAXC;
	private Double transferencia;
	private Double acopioTotalTon;
	private int idComprador;
	private Double pagadoTon;
	private Double pagadoPorcentaje;
	private Double precioPromPagAXC;
	private Double precioPromPagLibre;
	private Double mfurgon;
	private Double mcamion;
	private Double mmaritimo;
	private Double mautoconsumo;
	private Double mtransferencia;
	private Double mtotal;
	private Double existenciaAM;
	private double existenciaAMAnt;
	private int idEstado;
	private String destino;
	private String observaciones;
	private Double avanceCosecha;
	private Date fechaEnvio;
	private String nombreGerenteA;
	private String nombreReciboInfo;
	
	private List<Ejercicios> lstEjercicios;
	private List<Ciclo> lstCiclos;
	private List<Cultivo> lstCultivo;
	private List<Estado> lstEstados;
	private List<Estado> lstOperadorCA;
	private SeguimientoCentroAcopio sca;
	private long idSeguimientoCA;
	private int registrar; 
	private int errorClaveBodega;
	private String cuadroSatisfactorio;
	
	private CatalogosDAO cDAO;
	private SeguimientoDAO sDAO;
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private List<Comprador> lstComprador;
	private List<SeguimientoCentroAcopioV> listSeguimientoCentroAcopioV;
	private List<EstadosSeguimientoV> lstEstadosSeg;
	private List<CultivosSeguimientoV> lstCultivosSeg;
	private List<CiclosSeguimientoV> lstCiclosSeg;
	private List<ReporteSeguimientoAcopioV> lstReporteConsolidado;
	private List<SeguimientoCentroAcopioV> lstReporteDetalle;
	private List<ResumenAvanceAcopioV> lstReporteResumen;
	private boolean bandera;
	private Integer idEstadoSeg;
	private Integer idCultivoSeg;
	private Integer idCicloSeg;
	private String xls;
	
	private Integer idSeguimiento;
	private List<SeguimientoCentroAcopioV> lstSeguimientoAcopio;
	private String ciclo;
	private ServletContext context;
	private String rutaSalida;
	private String nombreReporte;
	private String rutaRaiz;
	private String rutaLogoS;
	private String rutaLogoA;
	
	private Integer idUsuario;
	private String descEstatus;
	
	/**Variables de la autorizacion de cambios**/
	private File doc;
	private String docFileName;
	private String ext;
	private String justificacionAutoriza;
	private String rutaJustificacionAutoriza;
	private int errorSistema;
	
	private List<Variedad> lstVariedad;
	private Integer idVariedad;
	private Date fechaActual;
	
	private Usuarios usuario;
	List<OperadoresBodegasV> lstObv;
	private String rutaSalidaTmp;
	private String rfcOperador;
	private int regionalId;
	private List<Regional> lstRegionales;
	
	

	public SeguimientoAction() {
		super();
		cDAO = new CatalogosDAO();
		sDAO = new SeguimientoDAO();
	}
	
	public String listSeguimiento(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			idUsuario = (Integer)session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);
			
			lstEjercicios = cDAO.consultaEjercicio(0);
			lstCiclos = cDAO.consultaCiclo(0);
			lstEstados = cDAO.consultaEstado(0);
			lstRegionales = cDAO.consultaRegional(0);
			
			if(idPerfil == 12){			
				listSeguimientoCentroAcopioV = sDAO.consultaSeguimientoCAV(0, -1);
			} else { 
				// Caso particular: Regional Occidente
				if(idPerfil == 11 && usuario.getArea().equals("1,6,14,18")){
					listSeguimientoCentroAcopioV = sDAO.consultaSeguimientoCAV(usuario.getArea());
				} else {
					listSeguimientoCentroAcopioV = sDAO.consultaSeguimientoCAV(0, idUsuario);	
				}
			}
		}catch (JDBCException e) {
	    	e.printStackTrace();	    
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;		
	}
		
	public String consultaSeguimiento(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			idUsuario = (Integer)session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);
			
			if(idPerfil == 12){
				listSeguimientoCentroAcopioV = sDAO.consultaSeguimientoCAV(0, idCiclo, ejercicio, periodoInicial, periodoFinal, claveBodega, -1, idEstado, regionalId);
			} else {
				// Caso particular: Regional Occidente
				if(idPerfil == 11 && usuario.getArea().equals("1,6,14,18")){
					listSeguimientoCentroAcopioV = sDAO.consultaSeguimientoCAV(0, idCiclo, ejercicio, periodoInicial, periodoFinal, claveBodega, usuario.getArea());
				} else {
					listSeguimientoCentroAcopioV = sDAO.consultaSeguimientoCAV(0, idCiclo, ejercicio, periodoInicial, periodoFinal, claveBodega, idUsuario);
				}
			}
			lstEjercicios = cDAO.consultaEjercicio(0);
			lstCiclos = cDAO.consultaCiclo(0);	
			lstEstados = cDAO.consultaEstado(0);
			lstRegionales = cDAO.consultaRegional(0);
			
			session.put("idCiclo", idCiclo);
			session.put("ejercicio", ejercicio);
			session.put("periodoInicial", periodoInicial);
			session.put("periodoFinal", periodoFinal);
			session.put("claveBodega", claveBodega);	
			session.put("idEstado", idEstado);	
			session.put("regionalId", regionalId);	
			
			
		}catch (JDBCException e){
			addActionError("Ocurrió un error inesperado, favor de informar al administrador");
			AppLogger.error("errores","Ocurrió un error en consultaSeguimiento debido a: " +e.getCause() );
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	public String capSeguimiento(){
		try{			
			lstObv = new ArrayList<OperadoresBodegasV>();
			if(registrar!=0 ){
				sca = sDAO.consultaSeguimientoCA(idSeguimientoCA).get(0);
				idCiclo = sca.getIdCiclo(); 
				ejercicio = sca.getEjercicio();
				idCultivo = sca.getIdCultivo();
				idVariedad = sca.getIdVariedad();
				claveBodega = sca.getClaveBodega();
				descEstatus = cDAO.consultaEstatusSeguimiento(sca.getIdEstatus()).get(0).getDescripcion();
				justificacionAutoriza = sca.getJustificacionAutoriza(); 
				rutaJustificacionAutoriza = sca.getRutaJustificacionAutoriza();
				rfcOperador = sca.getRfcOperador();
				idVariedad = sca.getIdVariedad();
				if (idVariedad==null)
					idVariedad = 0;
				validaClaveBodega();
			} else {
				idVariedad = 0;
				fechaActual = new Date();
			}
			recuperaCatalogos();
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;		
	}
	
	public String registroSeguimientoCA(){
		try{
			session = ActionContext.getContext().getSession();
			
			if(registrar != 3){
				if(registrar == 0){//Nuevo registro
					if(cDAO.validaPeriodoSeguimientoExistente(claveBodega, idCiclo, ejercicio, idCultivo, idVariedad, new java.text.SimpleDateFormat("yyyyMMdd").format(periodoInicial))==0 &&
							   cDAO.validaPeriodoSeguimientoExistente(claveBodega, idCiclo, ejercicio, idCultivo, idVariedad, new java.text.SimpleDateFormat("yyyyMMdd").format(periodoFinal))==0){
						sca = new SeguimientoCentroAcopio();
						sca.setClaveBodega(claveBodega);
						sca.setEjercicio(ejercicio);
						sca.setIdCiclo(idCiclo);
						sca.setPeriodoFinal(periodoFinal);
						sca.setPeriodoInicial(periodoInicial);
						sca.setUsuarioRegistro((Integer) session.get("idUsuario"));
						sca.setFechaRegistro(new Date());
						// Valida que lo registrado de almacenamiento total sea menor o igual capacidad de la Bodega para el Ciclo, Ejercicio y Cultivo
						CapacidadesBodegas capacidadCA = new CapacidadesBodegas();
						capacidadCA = cDAO.consultaCapacidadBodega(claveBodega).get(0);
						Double almacenamientoCA = sDAO.obtieneAcopioBodega(claveBodega, idCiclo, ejercicio, idCultivo);
						
						sca.setIdEstatus(1);
						sca.setVolumenMercadoLibre(volumenMercadoLibre);
						sca.setVolumenAXC(volumenAXC);
						sca.setTransferencia(transferencia);
						sca.setAcopioTotalTon(acopioTotalTon);
						sca.setAvanceCosecha(avanceCosecha);
						sca.setExistenciaAM(existenciaAM);
						sca.setFechaEnvio(fechaEnvio);
//						if (idComprador==0 || idComprador==-1){
//							sca.setIdComprador(null);
//						} else {
//							sca.setIdComprador(idComprador);
//						}
						sca.setIdCultivo(idCultivo);
						if(idVariedad != -1){
							sca.setIdVariedad(idVariedad);	
						}
						
			//			if (idEstado==0 || idEstado==-1){
			//				sca.setIdEstado(null);
			//			} else {
			//				sca.setIdEstado(idEstado);
			//			}
						sca.setDestino(destino);
						sca.setMcamion(mcamion);
						sca.setMfurgon(mfurgon);
						sca.setMmaritimo(mmaritimo);
						sca.setMautoconsumo(mautoconsumo);
						sca.setMtransferencia(mtransferencia);
						sca.setMtotal(mtotal);
						//sca.setNombreReciboInfo(nombreReciboInfo);
						sca.setObservaciones(observaciones);
						sca.setPagadoPorcentaje(pagadoPorcentaje);
						sca.setPagadoTon(pagadoTon);
						sca.setPrecioPromPagAXC(precioPromPagAXC);
						sca.setPrecioPromPagLibre(precioPromPagLibre);
						if(rfcOperador != null &&  !rfcOperador.isEmpty()){
							if(!rfcOperador.equals("-1")){
								sca.setRfcOperador(rfcOperador);
							}
						}						
						if((almacenamientoCA+acopioTotalTon)<=capacidadCA.getTotalAlmacenamiento()){
							//if((acopioTotalTon)<=capacidadCA.getTotalAlmacenamiento()){
								cuadroSatisfactorio = "Se registró satisfactoriamente el registro";		
						} else {
								cuadroSatisfactorio = "Se registró satisfactoriamente el registro. "+
													  "NOTA: Se ha rebasado la capacidad de la bodega: "+capacidadCA.getTotalAlmacenamiento().toString()+
													  " tons. de acuerdo a lo acopiado: "+(almacenamientoCA+acopioTotalTon)+" tons., verifiquelo!!!";
						}
						
					} else {
						addActionError("Ya existe información de seguimiento de acopio de la bodega: "+claveBodega+
								" para el periodo inicio: "+new java.text.SimpleDateFormat("dd/MM/yyyy").format(periodoInicial)+
								" y/o termino: "+new java.text.SimpleDateFormat("dd/MM/yyyy").format(periodoFinal)+" a capturar, por favor verifique");
						return SUCCESS;					
					}
				}else if(registrar == 2){ //Edicion de segumiento 
					sca = sDAO.consultaSeguimientoCA(idSeguimientoCA).get(0);
					sca.setUsuarioActualiza((Integer) session.get("idUsuario"));
					sca.setFechaActualiza(new Date());
					sca.setPeriodoFinal(periodoFinal);
					sca.setPeriodoInicial(periodoInicial);					
					// Valida que lo registrado de almacenamiento total sea menor o igual capacidad de la Bodega para el Ciclo, Ejercicio y Cultivo
					CapacidadesBodegas capacidadCA = new CapacidadesBodegas();
					capacidadCA = cDAO.consultaCapacidadBodega(claveBodegaAux).get(0);
					Double almacenamientoCA = sDAO.obtieneAcopioBodega(claveBodegaAux, idCicloAux, ejercicioAux, idCultivo);
					
					
					sca.setIdEstatus(1);
					sca.setVolumenMercadoLibre(volumenMercadoLibre);
					sca.setVolumenAXC(volumenAXC);
					sca.setTransferencia(transferencia);
					sca.setAcopioTotalTon(acopioTotalTon);
					sca.setAvanceCosecha(avanceCosecha);
					sca.setExistenciaAM(existenciaAM);
					sca.setFechaEnvio(fechaEnvio);
//					if (idComprador==0 || idComprador==-1){
//						sca.setIdComprador(null);
//					} else {
//						sca.setIdComprador(idComprador);
//					}
					sca.setIdCultivo(idCultivo);
					if(idVariedad != -1){
						sca.setIdVariedad(idVariedad);	
					}
					
		//			if (idEstado==0 || idEstado==-1){
		//				sca.setIdEstado(null);
		//			} else {
		//				sca.setIdEstado(idEstado);
		//			}
					sca.setDestino(destino);
					sca.setMcamion(mcamion);
					sca.setMfurgon(mfurgon);
					sca.setMmaritimo(mmaritimo);
					sca.setMautoconsumo(mautoconsumo);
					sca.setMtransferencia(mtransferencia);
					sca.setMtotal(mtotal);
					//sca.setNombreReciboInfo(nombreReciboInfo);
					sca.setObservaciones(observaciones);
					sca.setPagadoPorcentaje(pagadoPorcentaje);
					sca.setPagadoTon(pagadoTon);
					sca.setPrecioPromPagAXC(precioPromPagAXC);
					sca.setPrecioPromPagLibre(precioPromPagLibre);
					if(rfcOperador != null &&  !rfcOperador.isEmpty()){
						if(!rfcOperador.equals("-1")){
							sca.setRfcOperador(rfcOperador);
						}
					}
					if(((almacenamientoCA-sca.getAcopioTotalTon())+acopioTotalTon)<=capacidadCA.getTotalAlmacenamiento()){
						//if(acopioTotalTon<=capacidadCA.getTotalAlmacenamiento()){
							cuadroSatisfactorio = "Se registró satisfactoriamente el registro";
					} else {
							cuadroSatisfactorio = "Se registró satisfactoriamente el registro. "+
												  "NOTA: Se ha rebasado la capacidad de la bodega: "+capacidadCA.getTotalAlmacenamiento().toString()+
												  " tons. de acuerdo a lo acopiado: "+(((almacenamientoCA-sca.getAcopioTotalTon())+acopioTotalTon))+" tons., verifiquelo!!!";
					}

				}
			} else { //Autorizacion de cambios
				if(doc!=null){
					verificarTamanioArchivo(doc);
					if(errorSistema == 1){
						addActionError("El archivo no debe exceder de 3MB, por favor verifique");
						return SUCCESS;
					}
				}
				
				sca = sDAO.consultaSeguimientoCA(idSeguimientoCA).get(0);
				sca.setUsuarioActualiza((Integer) session.get("idUsuario"));
				sca.setFechaActualiza(new Date());				
				ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf("."));
				nombreReporte = "JustificacionCambiosSeguimiento-"+idSeguimientoCA+ext; // Archivo Justificacion de Cambios
				rutaSalida = cDAO.consultaParametros("RUTA_SEGUIMIENTO_ACOPIO");
				if (!rutaSalida.endsWith(File.separator)){
					rutaSalida += File.separator;
				}
								
				Utilerias.cargarArchivo(rutaSalida, nombreReporte, doc);
				sca.setRutaJustificacionAutoriza(rutaSalida+nombreReporte);
				sca.setJustificacionAutoriza(justificacionAutoriza);
				sca.setIdEstatus(3);
			}
			cDAO.guardaObjeto(sca);
			registrar = 1;
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch (Exception e) {
			e.printStackTrace();
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} finally {
			listSeguimiento();
		}
		
		return SUCCESS;		
	}
	
	public String validaClaveBodega(){
		try{
			existenciaAMAnt = 0;
			List<Bodegas> lstBodegas = cDAO.consultaBodegas(claveBodega);
			if(lstBodegas.size()>0){
				nombreCentroAcopio = lstBodegas.get(0).getNombre();
				nomRegionalCentroAcopio = lstBodegas.get(0).getAliasBodega();
				//Recupera operador de centro de acopio de acuerdo al ciclo, ejercicio y bodega
				if(idCiclo != -1 && ejercicio !=-1){
					//Recupera ciclo corto
					String cicloCorto = "", ejercicioS="", cadena ="";
					Ciclo c = cDAO.consultaCiclo(idCiclo).get(0);
					cicloCorto = c.getCicloCorto();
					ejercicioS = ejercicio.toString().substring(2,4);
					cadena = cicloCorto+ejercicioS;  
					System.out.println("cadena "+cadena);
					//Recupera el operador del centro de acopio
					lstObv = sDAO.consultaOperadoresBodegasV(null, claveBodega);
					 if(lstObv.size()>0){
						 operadorCentroAcopio =  lstObv.get(0).getNombre();
					 }else{
						 lstObv = new ArrayList<OperadoresBodegasV>(); 	 
					 }
					 
					 if(idCultivo > 0 && idVariedad > 0){
						 if(registrar!=1&&registrar!=2){
							 existenciaAMAnt = sDAO.consultaExistenciaBodega(claveBodega, idCiclo, ejercicio, idCultivo, idVariedad);
							 System.out.println("Existencia !=1 y != 2 "+existenciaAMAnt);
						 } else {
							 existenciaAMAnt = sDAO.consultaExistenciaBodega(claveBodega, idCiclo, ejercicio, idCultivo, idVariedad, sca.getPeriodoInicial(), sca.getPeriodoFinal());
							 System.out.println("Existencia ==1 y == 2"+existenciaAMAnt);
						 }
					 }
				}
				
			}else{
				errorClaveBodega = 1;
			}
		
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en validaClaveBodega  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validaClaveBodega  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	private void recuperaCatalogos() throws JDBCException, Exception {

		lstEjercicios = cDAO.consultaEjercicio(0);
		lstCiclos = cDAO.consultaCiclo(0);
		lstCultivo = cDAO.consultaCultivo();
		lstVariedad = cDAO.consultaVariedad(idVariedad, 0, null, true);
		//lstEstados = cDAO.consultaEstado(0);
		lstOperadorCA = cDAO.consultaEstado(0);
		//lstComprador  = cDAO.consultaComprador(0);
	}

	public String vistaPreviaOficio(){
		lstSeguimientoAcopio = sDAO.consultaSeguimientoCAV(idSeguimiento);
		claveBodega = lstSeguimientoAcopio.get(0).getClaveBodega();
		idCiclo = lstSeguimientoAcopio.get(0).getIdCiclo();
		ejercicio = lstSeguimientoAcopio.get(0).getEjercicio();
		idCultivo = lstSeguimientoAcopio.get(0).getIdCultivo();
		idVariedad = lstSeguimientoAcopio.get(0).getIdVariedad();
		ciclo = lstSeguimientoAcopio.get(0).getCiclo();
		periodoInicial = lstSeguimientoAcopio.get(0).getPeriodoInicial();
		periodoFinal = lstSeguimientoAcopio.get(0).getPeriodoFinal();

		existenciaAMAnt = sDAO.consultaExistenciaBodega(claveBodega, idCiclo, ejercicio, idCultivo, idVariedad, periodoInicial, periodoFinal); 

		Utilerias.getResponseISO();
		return SUCCESS;
	}
	
	@SuppressWarnings("unused")
	public String generarReporteSeguimiento() throws Exception{
		session = ActionContext.getContext().getSession();
		lstSeguimientoAcopio = sDAO.consultaSeguimientoCAV(idSeguimiento);
		ciclo = lstSeguimientoAcopio.get(0).getCiclo();
		periodoInicial = lstSeguimientoAcopio.get(0).getPeriodoInicial();
		periodoFinal = lstSeguimientoAcopio.get(0).getPeriodoFinal();
	
		sca = sDAO.consultaSeguimientoCA(idSeguimiento).get(0);
		sca.setUsuarioActualiza((Integer) session.get("idUsuario"));
		sca.setFechaActualiza(new Date());		
		sca.setIdEstatus(2);
		cDAO.guardaObjeto(sca);
		
		try {
			/**Genera el Reporte de Seguimiento de Acopio en formato PDF*/
			Document document;
			
			rutaSalida = cDAO.consultaParametros("RUTA_SEGUIMIENTO_ACOPIO");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			//nombreReporte =new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"-SeguimientoAcopio.pdf";
			nombreReporte ="SeguimientoAcopio-"+idSeguimiento+".pdf";
			rutaSalidaTmp = rutaSalida;
			rutaSalida += nombreReporte;			
			rutaRaiz = context.getRealPath("/");
			rutaLogoS = context.getRealPath("/images/logoSagarpa.png");
			rutaLogoA = context.getRealPath("/images/logoASERCA.jpg");
			ReporteSeguimientoAcopio pdf = new ReporteSeguimientoAcopio(this);
			pdf.generarReporteSeguimientoAcopio();
			consigueOficio();
//			listSeguimiento();
/*			
			/**Guardando Reporte de Seguimiento de Acopio
			oficioPagos = new OficioPagos();
			oficioPagos.setNoOficio(claveOficio+noOficio+anioOficio);
			oficioPagos.setTotalImporte(totalImporte);
			oficioPagos.setTotalPagos(noDepositos);
			if(criterioPago == 1 || criterioPago ==3){
				oficioPagos.setTotalVolumen(totalVolumen);	
			}
			oficioPagos.setFecha(new Date());
			oficioPagos.setNombreOficio(nombreReporte);
			idOficio = pDAO.guardaOficioPagos(oficioPagos);
*/			
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al generar el reporte de seguimiento de acopio debido a:"+e.getCause());
		}
		return null;
	}

	public void consigueOficio() throws Exception{
		try{
			//rutaSalida = cDAO.consultaParametros("RUTA_SEGUIMIENTO_ACOPIO");	
			System.out.println("consigue ruta "+rutaSalidaTmp+"nombre reporte "+nombreReporte);
			//nombreReporte = "SeguimientoAcopio-"+idSeguimiento+".pdf"; 
//			if (!rutaSalida.endsWith(File.separator)){
//				rutaSalida += File.separator;
//			}
			Utilerias.entregarArchivo(rutaSalidaTmp,nombreReporte,"pdf");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verificarTamanioArchivo(File file){
		double bytes = file.length();
		System.out.println("bytes "+ bytes);
		double megabytes = ((bytes/1024)/1024);
		System.out.println("megabytes "+ megabytes);
		if(megabytes>3){
			System.out.println("lo sentimos los archivos cargados no debe exceder de 3 MegaBytes");
			errorSistema = 1;
		}
		
	}

	public String consultaReporteConsolidado(){
		try{	
			/*Recupera catalogos de Estados, Cultivos y Ciclos*/
			lstEstadosSeg = cDAO.consultaEstadosSeguimiento(0);
			lstCultivosSeg = cDAO.consultaCultivosSeguimiento(0);
			lstCiclosSeg = cDAO.consultaCiclosSeguimiento(0);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al recuperar el catálogo de estados, cultivos y/o ciclos de seguimiento de acopio, debido a: "+e.getCause());
	    }
		return SUCCESS;
	}

	public String realizarConsultaReporteConsolidado(){
		try{	
			session = ActionContext.getContext().getSession();			
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario			
			lstReporteConsolidado = sDAO.consultaReporteConsolidado(idEstadoSeg, idCultivoSeg, idCicloSeg);
			bandera = true;
			//subir a session los criterios que el usuario selecciono.
			session.put("idEstadoSeg", idEstadoSeg);
			session.put("idCultivoSeg", idCultivoSeg);
			session.put("idCicloSeg", idCicloSeg);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al realizar la consulta del reporte consolidado del seguimiento de acopio, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogos de Estados, Cultivos y Ciclos*/
			lstEstadosSeg = cDAO.consultaEstadosSeguimiento(0);
			lstCultivosSeg = cDAO.consultaCultivosSeguimiento(0);
			lstCiclosSeg = cDAO.consultaCiclosSeguimiento(0);
		}
		
		return SUCCESS;
	}

	public String exportaReporteConsolidado() throws Exception{
		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			//setTipo("pagos");
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_SEGUIMIENTO_ACOPIO");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportesSeguimiento");
			}
	
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteConsolidado = sDAO.isolatedConsultaReporteConsolidado((Integer)session.get("idEstadoSeg"), (Integer)session.get("idCultivoSeg"), (Integer)session.get("idCicloSeg"));
			
			if(lstReporteConsolidado!=null && lstReporteConsolidado.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivo(rutaPlantilla,rutaSalida,lstReporteConsolidado);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del Reporte Consolidado de Seguimiento, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogos de Estados, Cultivos y Ciclos*/
			lstEstadosSeg = cDAO.isolatedConsultaEstadosSeguimiento(0);
			lstCultivosSeg = cDAO.isolatedConsultaCultivosSeguimiento(0);
			lstCiclosSeg = cDAO.isolatedConsultaCiclosSeguimiento(0);
		}		
		return "SUCCESS";
	}

	private String construyeArchivo(String rutaPlantilla, String rutaSalida, List<ReporteSeguimientoAcopioV> lst){
		String xlsOut = "ReporteConsolidadoSeg-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPCONSOLIDADOSEG.xls";
		if(!rutaSalida.endsWith(File.separator)){
			rutaSalida += File.separator;
		}
				
		Workbook workbook = null;
		WritableWorkbook copy = null;
		try{
			// Abrir plantilla
			workbook = Workbook.getWorkbook(new File(rutaPlantilla));
			// Hacer copia
			
			copy = Workbook.createWorkbook(new File(rutaSalida+xlsOut), workbook);
			WritableSheet sheet = copy.getSheet(0); 
			// Escribir datos
			
			WritableCellFormat cf = new WritableCellFormat();
			cf.setBorder( Border.ALL , BorderLineStyle.THIN );
	        // Fecha del Reporte

			sheet.addCell( new Label(4,4, lst.get(0).getNombreEstadoBodega(), cf));
			sheet.addCell( new Label(4,5, lst.get(0).getNombreCultivo(), cf));
			sheet.addCell( new Label(4,6, lst.get(0).getCiclo(), cf));

			int row = 11;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				ReporteSeguimientoAcopioV p = lst.get(i);
		        // CLAVE DEL CENTRO DE ACOPIO
		        sheet.addCell( new Label(col++,row, p.getClaveBodega(), cf));
				// NOMBRE O RAZÓN SOCIAL DEL CENTRO DE ACOPIO REGISTRADO EN ASERCA
				sheet.addCell( new Label(col++,row, p.getNombreBodega(), cf));
				// NOMBRE LOCAL O REGIONAL DEL CENTRO DE ACOPIO
				sheet.addCell( new Label(col++,row, p.getNombreLocalBodega(), cf));
				// OPERADOR DEL CENTRO DE ACOPIO
				sheet.addCell( new Label(col++,row, p.getNombreOperador(), cf));
				// NOMBRE O RAZÓN SOCIAL DEL COMPRADOR
				sheet.addCell( new Label(col++,row, p.getNombreComprador(), cf));
				// VOLUMEN PROGRAMA DE ASERCA (TONS)
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenAXC()), cf));
				// PRECIO PAGADO 
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getPrecioPromPagAXC()), cf));
				// VOLUMEN LIBRE MERCADO (TONS)
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenMercadoLibre()), cf));
				// ACOPIO TONELADAS
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getAcopioTotalTon()), cf));
				// PAGADO TONS.
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getPagadoTon()), cf));
				// PAGADO %
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getPagadoPorcentaje()), cf));
				// MOVILIZADO FURGON
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMovilizadoFurgon()), cf));
				// MOVILIZADO CAMION
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMovilizadoCamion()), cf));
				// MOVILIZADO MARITIMO
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMovilizadoMaritimo()), cf));
				// MOVILIZADO AUTOCONSUMO
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMovilizadoAutoconsumo()), cf));				
				// MOVILIZADO TOTAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMovilizadoTotal()), cf));
				// EXISTENCIA ACOP-MOV
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getExistenciaAM()), cf));
				// AVANCE COSECHA
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getAvanceCosecha()), cf));
		        row++;
			}
	        
			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. CONSOLIDADO SEG."+ Calendar.getInstance().get(Calendar.YEAR));
			} 			
		}catch(Exception e){
			System.err.println( e.getMessage() );
		}finally{
			// Cerrar y guardar copia
			if(copy!=null){
				try{
					copy.write(); 
					copy.close();
				}catch(Exception e){
					System.out.println( e.getMessage() );
				}
			}
			// Cerrar plantilla
			if(workbook!=null){
				workbook.close();
			}
		}
		setXls(xlsOut);
		return xlsOut;
	}

	public String consigueArchivoExcel() throws Exception{
		try{
			String rutaSalida = cDAO.consultaParametros("RUTA_SEGUIMIENTO_ACOPIO");	
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreReporte,"xls");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String consultaReporteDetalle(){
		try{	
			/*Recupera catalogos de Estados, Cultivos y Ciclos*/
			lstEstadosSeg = cDAO.consultaEstadosSeguimiento(0);
			lstCultivosSeg = cDAO.consultaCultivosSeguimiento(0);
			lstCiclosSeg = cDAO.consultaCiclosSeguimiento(0);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al recuperar el catálogo de estados, cultivos y/o ciclos de seguimiento de acopio, debido a: "+e.getCause());
	    }
		return SUCCESS;
	}

	public String realizarConsultaReporteDetalle(){
		try{	
			session = ActionContext.getContext().getSession();			
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario			
			lstReporteDetalle = sDAO.consultaSeguimientoCAV(idEstadoSeg, idCultivoSeg, idCicloSeg);
			bandera = true;
			//subir a session los criterios que el usuario selecciono.
			session.put("idEstadoSeg", idEstadoSeg);
			session.put("idCultivoSeg", idCultivoSeg);
			session.put("idCicloSeg", idCicloSeg);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al realizar la consulta del reporte detalle del seguimiento de acopio, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogos de Estados, Cultivos y Ciclos*/
			lstEstadosSeg = cDAO.consultaEstadosSeguimiento(0);
			lstCultivosSeg = cDAO.consultaCultivosSeguimiento(0);
			lstCiclosSeg = cDAO.consultaCiclosSeguimiento(0);
		}
		
		return SUCCESS;
	}

	public String exportaReporteDetalle() throws Exception{
		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			//setTipo("pagos");
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_SEGUIMIENTO_ACOPIO");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportesSeguimiento");
			}
	
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteDetalle = sDAO.isolatedConsultaReporteDetalle((Integer)session.get("idEstadoSeg"), (Integer)session.get("idCultivoSeg"), (Integer)session.get("idCicloSeg"));
			
			if(lstReporteDetalle!=null && lstReporteDetalle.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivoDetalle(rutaPlantilla,rutaSalida,lstReporteDetalle);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del Reporte Detalle de Seguimiento, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogos de Estados, Cultivos y Ciclos*/
			lstEstadosSeg = cDAO.isolatedConsultaEstadosSeguimiento(0);
			lstCultivosSeg = cDAO.isolatedConsultaCultivosSeguimiento(0);
			lstCiclosSeg = cDAO.isolatedConsultaCiclosSeguimiento(0);
		}		
		return "SUCCESS";
	}

	private String construyeArchivoDetalle(String rutaPlantilla, String rutaSalida, List<SeguimientoCentroAcopioV> lst){
		String xlsOut = "ReporteDetalleSeg-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPDETALLESEG.xls";
		if(!rutaSalida.endsWith(File.separator)){
			rutaSalida += File.separator;
		}
				
		Workbook workbook = null;
		WritableWorkbook copy = null;
		try{
			// Abrir plantilla
			workbook = Workbook.getWorkbook(new File(rutaPlantilla));
			// Hacer copia
			
			copy = Workbook.createWorkbook(new File(rutaSalida+xlsOut), workbook);
			WritableSheet sheet = copy.getSheet(0); 
			// Escribir datos
			
			WritableCellFormat cf = new WritableCellFormat();
			cf.setBorder( Border.ALL , BorderLineStyle.THIN );
	        // Fecha del Reporte

			sheet.addCell( new Label(4,4, lst.get(0).getNombreEstadoBodega(), cf));
			sheet.addCell( new Label(4,5, lst.get(0).getNombreCultivo(), cf));
			sheet.addCell( new Label(4,6, lst.get(0).getCiclo(), cf));

			int row = 11;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				SeguimientoCentroAcopioV p = lst.get(i);
		        // CLAVE DEL CENTRO DE ACOPIO
		        sheet.addCell( new Label(col++,row, p.getClaveBodega(), cf));
				// NOMBRE O RAZÓN SOCIAL DEL CENTRO DE ACOPIO REGISTRADO EN ASERCA
				sheet.addCell( new Label(col++,row, p.getNombreBodega(), cf));
				// NOMBRE LOCAL O REGIONAL DEL CENTRO DE ACOPIO
				sheet.addCell( new Label(col++,row, p.getNombreLocalBodega(), cf));
				// OPERADOR DEL CENTRO DE ACOPIO
				sheet.addCell( new Label(col++,row, p.getNombreOperador(), cf));
				// NOMBRE O RAZÓN SOCIAL DEL COMPRADOR
				sheet.addCell( new Label(col++,row, p.getNombreComprador(), cf));
				// PERIODO INICIAL
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getPeriodoInicial()), cf));
				// PERIODO FINAL
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getPeriodoFinal()), cf));
				// VOLUMEN PROGRAMA DE ASERCA (TONS)
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenAXC()), cf));
				// PRECIO PAGADO 
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getPrecioPromPagAXC()), cf));
				// VOLUMEN LIBRE MERCADO (TONS)
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenMercadoLibre()), cf));
				// ACOPIO TONELADAS
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getAcopioTotalTon()), cf));
				// PAGADO TONS.
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getPagadoTon()), cf));
				// PAGADO %
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getPagadoPorcentaje()), cf));
				// MOVILIZADO FURGON
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMfurgon()), cf));
				// MOVILIZADO CAMION
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMcamion()), cf));
				// MOVILIZADO MARITIMO
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMmaritimo()), cf));
				// MOVILIZADO AUTOCONSUMO
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMautoconsumo()), cf));
				// MOVILIZADO TOTAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMtotal()), cf));
				// EXISTENCIA ACOP-MOV
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getExistenciaAM()), cf));
				// DESTINO
				sheet.addCell( new Label(col++,row, p.getDestino(), cf));
				// AVANCE COSECHA
				sheet.addCell( new Label(col++,row, p.getAvanceCosecha(), cf));
				// OBSERVACIONES
				sheet.addCell( new Label(col++,row, p.getObservaciones(), cf));				
		        row++;
			}
	        
			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. DETALLE SEG."+ Calendar.getInstance().get(Calendar.YEAR));
			} 			
		}catch(Exception e){
			System.err.println( e.getMessage() );
		}finally{
			// Cerrar y guardar copia
			if(copy!=null){
				try{
					copy.write(); 
					copy.close();
				}catch(Exception e){
					System.out.println( e.getMessage() );
				}
			}
			// Cerrar plantilla
			if(workbook!=null){
				workbook.close();
			}
		}
		setXls(xlsOut);
		return xlsOut;
	}

	public String consultaReporteResumenAvance(){
		try{	
			/*Recupera catalogo de Ciclos*/
			lstCiclosSeg = cDAO.consultaCiclosSeguimiento(0);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al recuperar el catálogo de ciclos de seguimiento de acopio, debido a: "+e.getCause());
	    }
		return SUCCESS;
	}

	public String realizarConsultaReporteResumenAva(){
		try{	
			session = ActionContext.getContext().getSession();	
			idUsuario = (Integer) session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);

			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario			
			lstReporteResumen = sDAO.consultaReporteResumen(idCicloSeg, usuario.getArea());
			bandera = true;
			//subir a session los criterios que el usuario selecciono.
			session.put("idCicloSeg", idCicloSeg);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al realizar la consulta del reporte reseumen de avance del seguimiento de acopio, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogo Ciclos*/
			lstCiclosSeg = cDAO.consultaCiclosSeguimiento(0);
		}
		
		return SUCCESS;
	}

	public String exportaReporteResumenAva() throws Exception{
		try{
			if (session==null){
				session = ActionContext.getContext().getSession();					
			}
			idUsuario = (Integer) session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);
			//setTipo("pagos");
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_SEGUIMIENTO_ACOPIO");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportesSeguimiento");
			}	
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteResumen = sDAO.isolatedconsultaReporteResumen((Integer)session.get("idCicloSeg"), usuario.getArea());
			
			if(lstReporteResumen!=null && lstReporteResumen.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivoReporteResumen(rutaPlantilla,rutaSalida,lstReporteResumen);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del Reporte Resumen de Avance de Seguimiento, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogo de Ciclos*/
			lstCiclosSeg = cDAO.isolatedConsultaCiclosSeguimiento(0);
		}		
		return "SUCCESS";
	}

	private String construyeArchivoReporteResumen(String rutaPlantilla, String rutaSalida, List<ResumenAvanceAcopioV> lst){
		String xlsOut = "ReporteResumenAvance-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		Double totalAcopio = 0.0, totalMovilizado = 0.0, totalExistencia = 0.0;
		Double subTotalAcopio = 0.0, subTotalMovilizado = 0.0, subTotalExistencia = 0.0;
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPRESUMENAVA.xls";
		if(!rutaSalida.endsWith(File.separator)){
			rutaSalida += File.separator;
		}
				
		Workbook workbook = null;
		WritableWorkbook copy = null;
		try{
			// Abrir plantilla
			workbook = Workbook.getWorkbook(new File(rutaPlantilla));
			// Hacer copia
			
			copy = Workbook.createWorkbook(new File(rutaSalida+xlsOut), workbook);
			WritableSheet sheet = copy.getSheet(0); 
			// Escribir datos
			
			WritableCellFormat cf = new WritableCellFormat();
			cf.setBorder( Border.ALL , BorderLineStyle.THIN );

		    WritableFont cellFont5 = new WritableFont(WritableFont.createFont("Arial"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
		    cellFont5.setColour(Colour.BLACK);

            WritableCellFormat cellFormat5 = new WritableCellFormat(cellFont5);
            cellFormat5.setBackground(Colour.WHITE);
            cellFormat5.setBorder(Border.NONE, BorderLineStyle.NONE);

			if(lst.get(0).getCicloCorto().equals("OI")){
				sheet.addCell( new Label(2,2, lst.get(0).getCicloLargo()+" "+lst.get(0).getEjercicio()+"/"+(lst.get(0).getEjercicio()+1), cellFormat5));
			} else {
				sheet.addCell( new Label(2,2, lst.get(0).getCicloLargo()+" "+lst.get(0).getEjercicio(), cellFormat5));
			}

	        // Fecha del Reporte
			sheet.addCell( new Label(3,6, new SimpleDateFormat("dd/MM/yyyy").format( new Date() ), cellFormat5));

            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBackground(Colour.GRAY_25);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		    // Create cell font and format
		    WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Arial"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
		    cellFont2.setColour(Colour.BLACK);

            WritableCellFormat cellFormat3 = new WritableCellFormat(cellFont2);
            cellFormat3.setBackground(Colour.GRAY_25);
            cellFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);

		    // Create cell font and format
		    WritableFont cellFont3 = new WritableFont(WritableFont.createFont("Arial"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
		    cellFont2.setColour(Colour.BLACK);

            WritableCellFormat cellFormat4 = new WritableCellFormat(cellFont3);
            cellFormat4.setBackground(Colour.WHITE);
            cellFormat4.setBorder(Border.ALL, BorderLineStyle.THIN);

			int row = 10;
			String vNomEstadoAux = "";
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				ResumenAvanceAcopioV p = lst.get(i);
		        if(!p.getNombreEstadoBodega().equals(vNomEstadoAux)){
		        	if (!vNomEstadoAux.equals("")){
				        sheet.addCell( new Label(0,row, "Total "+vNomEstadoAux+":", cellFormat3));		        		
				        // SUBTOTAL ACOPIO
				        sheet.addCell( new Label(1,row, TextUtil.formateaNumeroComoVolumenSinComas(subTotalAcopio), cellFormat4));
				        // SUBTOTAL MOVILIZADO
				        sheet.addCell( new Label(2,row, TextUtil.formateaNumeroComoVolumenSinComas(subTotalMovilizado), cellFormat4));
				        // SUBTOTAL EXISTENCIA
				        sheet.addCell( new Label(3,row++, TextUtil.formateaNumeroComoVolumenSinComas(subTotalExistencia), cellFormat4));
				        
				        subTotalAcopio = 0.0;
				        subTotalMovilizado = 0.0; 
				        subTotalExistencia = 0.0;
		        	}
		            		            
			        // ENTIDAD FEDERATIVA
			        sheet.addCell(new Label(col++,row, p.getNombreEstadoBodega(), cellFormat));
			        vNomEstadoAux = p.getNombreEstadoBodega();
		        	col = 0;
		        	row++;
		        }
		        // CULTIVO
		        sheet.addCell( new Label(col++,row, p.getNombreCultivo(), cf));
		        // ACOPIO TOTAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getAcopioTotal()), cf));
				subTotalAcopio = subTotalAcopio + p.getAcopioTotal();
				totalAcopio = totalAcopio + p.getAcopioTotal();
				// MOVILIZADO TOTAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMovilizadoTotal()), cf));
				subTotalMovilizado = subTotalMovilizado + p.getMovilizadoTotal();
				totalMovilizado = totalMovilizado + p.getMovilizadoTotal();
				// EXISTENCIA ACOP-MOV
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getExistenciaAM()), cf));
				subTotalExistencia = subTotalExistencia + p.getExistenciaAM();
				totalExistencia = totalExistencia + p.getExistenciaAM();
		        row++;
			}
			if (!vNomEstadoAux.equals("")){
		        sheet.addCell( new Label(0,row, "Total "+vNomEstadoAux+":", cellFormat3));		        		
		        // SUBTOTAL ACOPIO
		        sheet.addCell( new Label(1,row, TextUtil.formateaNumeroComoVolumenSinComas(subTotalAcopio), cellFormat4));
		        // SUBTOTAL MOVILIZADO
		        sheet.addCell( new Label(2,row, TextUtil.formateaNumeroComoVolumenSinComas(subTotalMovilizado), cellFormat4));
		        // SUBTOTAL EXISTENCIA
		        sheet.addCell( new Label(3,row++, TextUtil.formateaNumeroComoVolumenSinComas(subTotalExistencia), cellFormat4));
			}

		    // Create cell font and format
		    WritableFont cellFont = new WritableFont(WritableFont.createFont("Arial"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
		    cellFont.setColour(Colour.BLACK);
		    
            WritableCellFormat cellFormat2 = new WritableCellFormat(cellFont);
            cellFormat2.setBackground(Colour.WHITE);
            cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);

			sheet.addCell( new Label(0,row, "ACUMULADO:", cellFormat2));
	        // ACOPIO GRAN TOTAL
			sheet.addCell( new Label(1,row, TextUtil.formateaNumeroComoVolumenSinComas(totalAcopio), cellFormat2));
			// MOVILIZADO GRAN TOTAL
			sheet.addCell( new Label(2,row, TextUtil.formateaNumeroComoVolumenSinComas(totalMovilizado), cellFormat2));
			// EXISTENCIA GRAN ACOP-MOV
			sheet.addCell( new Label(3,row, TextUtil.formateaNumeroComoVolumenSinComas(totalExistencia), cellFormat2));

			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. RESUMEN AVANCE SEG."+ Calendar.getInstance().get(Calendar.YEAR));
			} 			
		}catch(Exception e){
			System.err.println( e.getMessage() );
		}finally{
			// Cerrar y guardar copia
			if(copy!=null){
				try{
					copy.write(); 
					copy.close();
				}catch(Exception e){
					System.out.println( e.getMessage() );
				}
			}
			// Cerrar plantilla
			if(workbook!=null){
				workbook.close();
			}
		}
		setXls(xlsOut);
		return xlsOut;
	}

	public String recuperaVariedadByCultivo(){
		//Recupera los datos de la variedad por cultivo
		lstVariedad = cDAO.consultaVariedad(-1, idCultivo, null, true);

		if((claveBodega!=null && !claveBodega.equals("")) && idCiclo != -1 && ejercicio !=-1 && idCultivo > 0 && idVariedad > 0){
			existenciaAMAnt = sDAO.consultaExistenciaBodega(claveBodega, idCiclo, ejercicio, idCultivo, idVariedad);
		}

		return SUCCESS;		
	}

	public String eliminarSeguimiento(){
		SeguimientoCentroAcopio seguimientoAux;
		try {
			//recupera seguimiento de acopio 
			seguimientoAux = sDAO.consultaSeguimientoCA(idSeguimientoCA).get(0);
			//borra objeto seguimiento de acopio
			sDAO.borrarObjeto(seguimientoAux);
			listSeguimiento();
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al eliminar regoistro de seguimiento de acopio debido a:"+e.getCause());
		}
		return SUCCESS;
	}

	public String exportaListSeguimiento() throws Exception{
		try{
			int idPerfil = (Integer) session.get("idPerfil");
			idUsuario = (Integer)session.get("idUsuario");
			if((Integer)session.get("idCiclo")!=null)
				idCiclo = (Integer)session.get("idCiclo");
			if((Integer)session.get("ejercicio")!=null)
				ejercicio = (Integer)session.get("ejercicio");
			if((Date)session.get("periodoInicial")!=null)
				periodoInicial = (Date)session.get("periodoInicial");
			if((Date)session.get("periodoFinal")!=null)
				periodoFinal = (Date)session.get("periodoFinal");
			if((String)session.get("claveBodega")!=null)
				claveBodega = (String)session.get("claveBodega");
			if((Integer)session.get("idEstado")!=null)
				idEstado = (Integer)session.get("idEstado");
			if((Integer)session.get("regionalId")!=null)
				regionalId = (Integer)session.get("regionalId");
				
			usuario = cDAO.isolatedconsultaUsuarios(idUsuario, null, null).get(0);

			if (session==null){
				session = ActionContext.getContext().getSession();	
			}			

			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_SEGUIMIENTO_ACOPIO");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportesSeguimiento");
			}
			
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			if(idPerfil == 12){
				listSeguimientoCentroAcopioV = sDAO.isolatedConsultaSeguimientoCAV(0, idCiclo, ejercicio, periodoInicial, periodoFinal, claveBodega, -1, idEstado, regionalId);
				//listSeguimientoCentroAcopioV = sDAO.isolatedConsultaSeguimientoCAV(0, (Integer)session.get("idCiclo"), (Integer)session.get("ejercicio"), (Date)session.get("periodoInicial"), (Date)session.get("periodoFinal"), (String)session.get("claveBodega"), -1);
			} else {
				// Caso particular: Regional Occidente
				if(idPerfil == 11 && usuario.getArea().equals("1,6,14,18")){
					listSeguimientoCentroAcopioV = sDAO.isolatedConsultaSeguimientoCAV(0, idCiclo, ejercicio, periodoInicial, periodoFinal, claveBodega, usuario.getArea());
					//listSeguimientoCentroAcopioV = sDAO.isolatedConsultaSeguimientoCAV(0, (Integer)session.get("idCiclo"), (Integer)session.get("ejercicio"), (Date)session.get("periodoInicial"), (Date)session.get("periodoFinal"), (String)session.get("claveBodega"), usuario.getArea());
				} else {
					listSeguimientoCentroAcopioV = sDAO.isolatedConsultaSeguimientoCAV(0, idCiclo, ejercicio, periodoInicial, periodoFinal, claveBodega, idUsuario,-1,-1);
					//listSeguimientoCentroAcopioV = sDAO.isolatedConsultaSeguimientoCAV(0, (Integer)session.get("idCiclo"), (Integer)session.get("ejercicio"), (Date)session.get("periodoInicial"), (Date)session.get("periodoFinal"), (String)session.get("claveBodega"), idUsuario);
				}
			}
			
			if(listSeguimientoCentroAcopioV!=null && listSeguimientoCentroAcopioV.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivoListaSeguimiento(rutaPlantilla,rutaSalida,listSeguimientoCentroAcopioV);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta de la Relacion de Seguimientos de Acopio registrados, debido a: "+e.getCause());
		}finally{
			/*Recupera catalogos de Ejercicios y Ciclos*/
			lstEjercicios = cDAO.isolatedConsultaEjercicio(0);
			lstCiclos = cDAO.isolatedConsultaCiclo(0);	
		}		
		return "SUCCESS";
	}

	private String construyeArchivoListaSeguimiento(String rutaPlantilla, String rutaSalida, List<SeguimientoCentroAcopioV> lst){
		String xlsOut = "ReporteSeguimientosAcopio-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_LISTASEGUI.xls";
		if(!rutaSalida.endsWith(File.separator)){
			rutaSalida += File.separator;
		}
				
		Workbook workbook = null;
		WritableWorkbook copy = null;
		try{
			// Abrir plantilla
			workbook = Workbook.getWorkbook(new File(rutaPlantilla));
			// Hacer copia
			
			copy = Workbook.createWorkbook(new File(rutaSalida+xlsOut), workbook);
			WritableSheet sheet = copy.getSheet(0); 
			// Escribir datos
			
			WritableCellFormat cf = new WritableCellFormat();
			cf.setBorder( Border.ALL , BorderLineStyle.THIN );
	        // Fecha del Reporte

			sheet.addCell( new Label(4,4, lst.get(0).getCiclo(), cf));

			int row = 11;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				SeguimientoCentroAcopioV p = lst.get(i);
		        // CLAVE DEL CENTRO DE ACOPIO
		        sheet.addCell( new Label(col++,row, p.getClaveBodega(), cf));
				// NOMBRE O RAZÓN SOCIAL DEL CENTRO DE ACOPIO REGISTRADO EN ASERCA
				sheet.addCell( new Label(col++,row, p.getNombreBodega(), cf));
				// NOMBRE LOCAL O REGIONAL DEL CENTRO DE ACOPIO
				sheet.addCell( new Label(col++,row, p.getNombreLocalBodega(), cf));
				// OPERADOR DEL CENTRO DE ACOPIO
				sheet.addCell( new Label(col++,row, p.getNombreOperador(), cf));
				// NOMBRE O RAZÓN SOCIAL DEL COMPRADOR
				sheet.addCell( new Label(col++,row, p.getNombreComprador(), cf));
				// CULTIVO
				sheet.addCell( new Label(col++,row, p.getNombreCultivo(), cf));
				// VARIEDAD
				sheet.addCell( new Label(col++,row, p.getNombreVariedad(), cf));
				// PERIODO INICIAL
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getPeriodoInicial()), cf));
				// PERIODO FINAL
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getPeriodoFinal()), cf));
				// VOLUMEN PROGRAMA DE ASERCA (TONS)
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenAXC()), cf));
				// PRECIO PAGADO 
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getPrecioPromPagAXC()), cf));
				// VOLUMEN LIBRE MERCADO (TONS)
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenMercadoLibre()), cf));
				// ACOPIO TONELADAS
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getAcopioTotalTon()), cf));
				// PAGADO TONS.
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getPagadoTon()), cf));
				// PAGADO %
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getPagadoPorcentaje()), cf));
				// MOVILIZADO FURGON
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMfurgon()), cf));
				// MOVILIZADO CAMION
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMcamion()), cf));
				// MOVILIZADO MARITIMO
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMmaritimo()), cf));
				// MOVILIZADO AUTOCONSUMO
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMautoconsumo()), cf));
				// MOVILIZADO TOTAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getMtotal()), cf));
				// EXISTENCIA ACOP-MOV
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getExistenciaAM()), cf));
				// DESTINO
				sheet.addCell( new Label(col++,row, p.getDestino(), cf));
				// AVANCE COSECHA
				sheet.addCell( new Label(col++,row, p.getAvanceCosecha(), cf));
				// OBSERVACIONES
				sheet.addCell( new Label(col++,row, p.getObservaciones(), cf));				
		        row++;
			}
	        
			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. SEGUI. ACOPIO"+ Calendar.getInstance().get(Calendar.YEAR));
			} 			
		}catch(Exception e){
			System.err.println( e.getMessage() );
		}finally{
			// Cerrar y guardar copia
			if(copy!=null){
				try{
					copy.write(); 
					copy.close();
				}catch(Exception e){
					System.out.println( e.getMessage() );
				}
			}
			// Cerrar plantilla
			if(workbook!=null){
				workbook.close();
			}
		}
		setXls(xlsOut);
		return xlsOut;
	}

	
	public String recuperaEstadosByRegional() throws Exception{
		
		lstEstados = cDAO.consultaEstado(0,null,regionalId);
		
		return SUCCESS;
	}
	
	
	public Integer getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}

	public int getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public String getClaveBodega() {
		return claveBodega;
	}

	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}	

	public String getOperadorCentroAcopio() {
		return operadorCentroAcopio;
	}
	public void setOperadorCentroAcopio(String operadorCentroAcopio) {
		this.operadorCentroAcopio = operadorCentroAcopio;
	}
	public String getNombreCentroAcopio() {
		return nombreCentroAcopio;
	}

	public void setNombreCentroAcopio(String nombreCentroAcopio) {
		this.nombreCentroAcopio = nombreCentroAcopio;
	}

	public String getNomRegionalCentroAcopio() {
		return nomRegionalCentroAcopio;
	}

	public void setNomRegionalCentroAcopio(String nomRegionalCentroAcopio) {
		this.nomRegionalCentroAcopio = nomRegionalCentroAcopio;
	}

	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	public Double getVolumenMercadoLibre() {
		return volumenMercadoLibre;
	}

	public void setVolumenMercadoLibre(Double volumenMercadoLibre) {
		this.volumenMercadoLibre = volumenMercadoLibre;
	}

	public Double getVolumenAXC() {
		return volumenAXC;
	}

	public void setVolumenAXC(Double volumenAXC) {
		this.volumenAXC = volumenAXC;
	}

	public Double getAcopioTotalTon() {
		return acopioTotalTon;
	}

	public void setAcopioTotalTon(Double acopioTotalTon) {
		this.acopioTotalTon = acopioTotalTon;
	}

	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}

	public Double getPagadoTon() {
		return pagadoTon;
	}

	public void setPagadoTon(Double pagadoTon) {
		this.pagadoTon = pagadoTon;
	}

	public Double getPagadoPorcentaje() {
		return pagadoPorcentaje;
	}

	public void setPagadoPorcentaje(Double pagadoPorcentaje) {
		this.pagadoPorcentaje = pagadoPorcentaje;
	}
	
	public Double getPrecioPromPagAXC() {
		return precioPromPagAXC;
	}
	public void setPrecioPromPagAXC(Double precioPromPagAXC) {
		this.precioPromPagAXC = precioPromPagAXC;
	}
	public Double getPrecioPromPagLibre() {
		return precioPromPagLibre;
	}
	public void setPrecioPromPagLibre(Double precioPromPagLibre) {
		this.precioPromPagLibre = precioPromPagLibre;
	}
	public Double getMfurgon() {
		return mfurgon;
	}

	public void setMfurgon(Double mfurgon) {
		this.mfurgon = mfurgon;
	}

	public Double getMcamion() {
		return mcamion;
	}

	public void setMcamion(Double mcamion) {
		this.mcamion = mcamion;
	}

	public Double getMmaritimo() {
		return mmaritimo;
	}

	public void setMmaritimo(Double mmaritimo) {
		this.mmaritimo = mmaritimo;
	}
	
	public Double getMtotal() {
		return mtotal;
	}
	public void setMtotal(Double mtotal) {
		this.mtotal = mtotal;
	}
	public Double getExistenciaAM() {
		return existenciaAM;
	}

	public void setExistenciaAM(Double existenciaAM) {
		this.existenciaAM = existenciaAM;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Double getAvanceCosecha() {
		return avanceCosecha;
	}

	public void setAvanceCosecha(Double avanceCosecha) {
		this.avanceCosecha = avanceCosecha;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getNombreGerenteA() {
		return nombreGerenteA;
	}

	public void setNombreGerenteA(String nombreGerenteA) {
		this.nombreGerenteA = nombreGerenteA;
	}

	public String getNombreReciboInfo() {
		return nombreReciboInfo;
	}

	public void setNombreReciboInfo(String nombreReciboInfo) {
		this.nombreReciboInfo = nombreReciboInfo;
	}

	
	public List<Ejercicios> getLstEjercicios() {
		return lstEjercicios;
	}
	public void setLstEjercicios(List<Ejercicios> lstEjercicios) {
		this.lstEjercicios = lstEjercicios;
	}
	public List<Ciclo> getLstCiclos() {
		return lstCiclos;
	}
	public void setLstCiclos(List<Ciclo> lstCiclos) {
		this.lstCiclos = lstCiclos;
	}
	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}
	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}
	public List<Estado> getLstEstados() {
		return lstEstados;
	}
	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}
	public List<Estado> getLstOperadorCA() {
		return lstOperadorCA;
	}
	public void setLstOperadorCA(List<Estado> lstOperadorCA) {
		this.lstOperadorCA = lstOperadorCA;
	}

	public List<Comprador> getLstComprador() {
		return lstComprador;
	}
	public void setLstComprador(List<Comprador> lstComprador) {
		this.lstComprador = lstComprador;
	}
	public SeguimientoCentroAcopio getSca() {
		return sca;
	}
	public void setSca(SeguimientoCentroAcopio sca) {
		this.sca = sca;
	}
	public long getIdSeguimientoCA() {
		return idSeguimientoCA;
	}
	public void setIdSeguimientoCA(long idSeguimientoCA) {
		this.idSeguimientoCA = idSeguimientoCA;
	}
	public int getRegistrar() {
		return registrar;
	}
	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}
	public int getErrorClaveBodega() {
		return errorClaveBodega;
	}
	public void setErrorClaveBodega(int errorClaveBodega) {
		this.errorClaveBodega = errorClaveBodega;
	}
	
	public List<SeguimientoCentroAcopioV> getListSeguimientoCentroAcopioV() {
		return listSeguimientoCentroAcopioV;
	} 
	public void setListSeguimientoCentroAcopioV(
			List<SeguimientoCentroAcopioV> listSeguimientoCentroAcopioV) {
		this.listSeguimientoCentroAcopioV = listSeguimientoCentroAcopioV;
	}	
	
	public String getCuadroSatisfactorio() {
		return cuadroSatisfactorio;
	}
	public void setCuadroSatisfactorio(String cuadroSatisfactorio) {
		this.cuadroSatisfactorio = cuadroSatisfactorio;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}	

	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}
	public Integer getIdSeguimiento() {
		return idSeguimiento;
	}
	public void setIdSeguimiento(Integer idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}
	public List<SeguimientoCentroAcopioV> getLstSeguimientoAcopio() {
		return lstSeguimientoAcopio;
	}
	public void setLstSeguimientoAcopio(
			List<SeguimientoCentroAcopioV> lstSeguimientoAcopio) {
		this.lstSeguimientoAcopio = lstSeguimientoAcopio;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getRutaSalida() {
		return rutaSalida;
	}
	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}
	public String getNombreReporte() {
		return nombreReporte;
	}
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	public String getRutaRaiz() {
		return rutaRaiz;
	}
	public void setRutaRaiz(String rutaRaiz) {
		this.rutaRaiz = rutaRaiz;
	}
	public String getRutaLogoS() {
		return rutaLogoS;
	}
	public void setRutaLogoS(String rutaLogoS) {
		this.rutaLogoS = rutaLogoS;
	}
	public String getRutaLogoA() {
		return rutaLogoA;
	}
	public void setRutaLogoA(String rutaLogoA) {
		this.rutaLogoA = rutaLogoA;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDescEstatus() {
		return descEstatus;
	}
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
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
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getJustificacionAutoriza() {
		return justificacionAutoriza;
	}
	public void setJustificacionAutoriza(String justificacionAutoriza) {
		this.justificacionAutoriza = justificacionAutoriza;
	}
	public int getErrorSistema() {
		return errorSistema;
	}
	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
	}
	public String getRutaJustificacionAutoriza() {
		return rutaJustificacionAutoriza;
	}
	public void setRutaJustificacionAutoriza(String rutaJustificacionAutoriza) {
		this.rutaJustificacionAutoriza = rutaJustificacionAutoriza;
	}
	public List<EstadosSeguimientoV> getLstEstadosSeg() {
		return lstEstadosSeg;
	}
	public void setLstEstadosSeg(List<EstadosSeguimientoV> lstEstadosSeg) {
		this.lstEstadosSeg = lstEstadosSeg;
	}
	public List<CultivosSeguimientoV> getLstCultivosSeg() {
		return lstCultivosSeg;
	}
	public void setLstCultivosSeg(List<CultivosSeguimientoV> lstCultivosSeg) {
		this.lstCultivosSeg = lstCultivosSeg;
	}
	public List<CiclosSeguimientoV> getLstCiclosSeg() {
		return lstCiclosSeg;
	}
	public void setLstCiclosSeg(List<CiclosSeguimientoV> lstCiclosSeg) {
		this.lstCiclosSeg = lstCiclosSeg;
	}
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	public Integer getIdEstadoSeg() {
		return idEstadoSeg;
	}
	public void setIdEstadoSeg(Integer idEstadoSeg) {
		this.idEstadoSeg = idEstadoSeg;
	}
	public Integer getIdCultivoSeg() {
		return idCultivoSeg;
	}
	public void setIdCultivoSeg(Integer idCultivoSeg) {
		this.idCultivoSeg = idCultivoSeg;
	}
	public Integer getIdCicloSeg() {
		return idCicloSeg;
	}
	public void setIdCicloSeg(Integer idCicloSeg) {
		this.idCicloSeg = idCicloSeg;
	}
	public List<ReporteSeguimientoAcopioV> getLstReporteConsolidado() {
		return lstReporteConsolidado;
	}
	public void setLstReporteConsolidado(
			List<ReporteSeguimientoAcopioV> lstReporteConsolidado) {
		this.lstReporteConsolidado = lstReporteConsolidado;
	}
	public void setXls(String xls) {
		this.xls = xls;
	}

	public String getXls() {
		return xls;
	}
	public List<SeguimientoCentroAcopioV> getLstReporteDetalle() {
		return lstReporteDetalle;
	}
	public void setLstReporteDetalle(
			List<SeguimientoCentroAcopioV> lstReporteDetalle) {
		this.lstReporteDetalle = lstReporteDetalle;
	}
	public int getIdCicloAux() {
		return idCicloAux;
	}
	public void setIdCicloAux(int idCicloAux) {
		this.idCicloAux = idCicloAux;
	}
	public Integer getEjercicioAux() {
		return ejercicioAux;
	}
	public void setEjercicioAux(Integer ejercicioAux) {
		this.ejercicioAux = ejercicioAux;
	}
	public String getClaveBodegaAux() {
		return claveBodegaAux;
	}
	public void setClaveBodegaAux(String claveBodegaAux) {
		this.claveBodegaAux = claveBodegaAux;
	}
	public int getIdCultivoAux() {
		return idCultivoAux;
	}
	public void setIdCultivoAux(int idCultivoAux) {
		this.idCultivoAux = idCultivoAux;
	}
	public List<ResumenAvanceAcopioV> getLstReporteResumen() {
		return lstReporteResumen;
	}
	public void setLstReporteResumen(List<ResumenAvanceAcopioV> lstReporteResumen) {
		this.lstReporteResumen = lstReporteResumen;
	}
	public List<Variedad> getLstVariedad() {
		return lstVariedad;
	}
	public void setLstVariedad(List<Variedad> lstVariedad) {
		this.lstVariedad = lstVariedad;
	}
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public double getExistenciaAMAnt() {
		return existenciaAMAnt;
	}
	public void setExistenciaAMAnt(double existenciaAMAnt) {
		this.existenciaAMAnt = existenciaAMAnt;
	}

	public List<OperadoresBodegasV> getLstObv() {
		return lstObv;
	}

	public void setLstObv(List<OperadoresBodegasV> lstObv) {
		this.lstObv = lstObv;
	}

	public String getRfcOperador() {
		return rfcOperador;
	}

	public void setRfcOperador(String rfcOperador) {
		this.rfcOperador = rfcOperador;
	}

	public Double getMautoconsumo() {
		return mautoconsumo;
	}

	public void setMautoconsumo(Double mautoconsumo) {
		this.mautoconsumo = mautoconsumo;
	}

	
	public int getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(int regionalId) {
		this.regionalId = regionalId;
	}

	public List<Regional> getLstRegionales() {
		return lstRegionales;
	}

	public void setLstRegionales(List<Regional> lstRegionales) {
		this.lstRegionales = lstRegionales;
	}

	public Double getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Double transferencia) {
		this.transferencia = transferencia;
	}

	public Double getMtransferencia() {
		return mtransferencia;
	}

	public void setMtransferencia(Double mtransferencia) {
		this.mtransferencia = mtransferencia;
	}
	
}
