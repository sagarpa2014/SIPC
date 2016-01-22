 package mx.gob.comer.sipc.action.solicitudpago;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.AuditoresExternos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Expediente;
import mx.gob.comer.sipc.domain.InicializacionEsquema;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Especialista;
import mx.gob.comer.sipc.domain.catalogos.EstatusCartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.Anexo32D;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcompras;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.DocumentacionSPCartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.ObservacionDocumentacionSP;
import mx.gob.comer.sipc.domain.transaccionales.OficioObsSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.OficioRespuestaSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.SolicitudInscripcion;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.AsignacionCartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.AuditorSolicitudPagoV;
import mx.gob.comer.sipc.vistas.domain.DocumentacionSPCartaAdhesionV;
import mx.gob.comer.sipc.vistas.domain.ExpedientesProgramasV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.PrgEspecialistaNumCartasV;
import mx.gob.comer.sipc.vistas.domain.ProgramaNumCartasV;
import mx.gob.comer.sipc.vistas.domain.RepresentanteCompradorV;
import mx.gob.comer.sipc.vistas.domain.SolicitudInscripcionV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RelacionDoctosSolPagoAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	/**** BD ****/
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private InscripcionDAO iDAO = new InscripcionDAO();
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private PagosDAO pDAO = new PagosDAO();
	private RelacionesDAO rDAO = new RelacionesDAO();
	/***LISTAS**/
	private List<Programa> lstProgramas;
	private List<Especialista> lstEspecialista;
	private List<AsignacionCartasAdhesionV> lstAsigCA;
	private List<AsignacionCAaEspecialistaV> lstAsignacionCAaEspecialistaV;
	private List<ExpedientesProgramasV> lstExpedientesProgramasV;
	private List<DocumentacionSPCartaAdhesionV>lstsExpedientesSPCartaAdhesionV;
	private List<OficioObsSolicitudPago> lstOficioObsSolicitudPago;
	private List<AuditoresExternos> lstAuditoresExternos;
	private List<RepresentanteCompradorV> lstRepresentanteV;
	private List<EstatusCartaAdhesion> lstEstatusCartaAdhesion;
	private List<PrgEspecialistaNumCartasV> lstPrgEspecialistaNumCartasVs;
	private List<ProgramaNumCartasV> lstNumCartasVs;
	private List<AuditorSolicitudPagoV> lstAuditorSolPagoV;
	private DocumentacionSPCartaAdhesion docSPCA;
	
	/***Variables de formulario**/
	private int tieneObservacion;
	private int tipoDocumentacion;
	private int idCriterioPago;
	private int idEspecialista;
	private int idPrograma;
	private String folioCartaAdhesion; 
	private int documentacion;
	private String nombrePrograma;
	private String comprador;
	private String cultivo;
	private Double volumenAutorizado;
	private Double importeAutorizado;
	private Date fechaDocEntDoctos;
	private Date fechaAcuseEntDoctos;
	private String noOficioED;
	private Integer[] capObsExpediente;
	private String nombreArchivo;
	
	private File doc1; 
	private String doc1FileName;
	private File doc2;
	private String doc2FileName;
	private File doc3; // documentacion:1,2,3,5,6,7,8,9,10,11,12,15
	private String doc3FileName;
	private File doc4;
	private String doc4FileName;
	private File doc5;
	private String doc5FileName;
	private File doc6;
	private String doc6FileName;
	private File doc7;
	private String doc7FileName;
	private File doc8;
	private String doc8FileName;
	private File doc9;
	private String doc9FileName;
	private File doc10;
	private String doc10FileName;
	private File doc11;
	private String doc11FileName;
	private File doc12;
	private String doc12FileName;
	private File doc13;
	private String doc13FileName;
	private File doc14;
	private String doc14FileName;
	private File doc15;
	private String doc15FileName;
	private File doc32;
	private String doc32FileName;
	private File doc33;
	private String doc33FileName;
	private File doc34;
	private String doc34FileName;
	private File [] docRespA;
	private String [] docRespAFileName;
	private File [] docObsA;
	private String [] docObsAFileName;
	

	
	
	/**Variables del oficio de observaciones**/
	private File docObs;
	private String docObsFileName;
	private Date fechaDocOBS;
	private Date fechaAcuseOBS;
	private String noOficioOBS;
	/**Variables del oficio de respuesta de observaciones**/
	private File docResp;
	private String docRespFileName;
	private Date fechaDocResp;
	private Date fechaAcuseResp;
	private boolean tCartaAdehsionHastaSP;
	private boolean capturarInfoDocumento;
	private boolean capturarInfoFianza;
	private boolean habilitaNuevasObservaciones;
	private String rutaCompleta;
	//private String nombreAuditor;	
	private boolean alcanceDocumentacion;
	private boolean nuevasObservaciones;
	private Programa programa;
	private String msjOk;
	private int errorSistema;
	private int registrar;
	private String ext;
	private Date fechaExpedicion;
	private Double volumen;
	private boolean deshabilitaAccion;
	private boolean anexo32DyaCapturado;
	private boolean edoCuentaYaCapturado;
	private String estatusCartaAdhesion;
	private int estatusCA;
	private boolean docParcial;
	private boolean sustituirArchivo;
	private boolean habilitarOficioObs;
	private int idEstatusCA;
	private List<OficioRespuestaSolicitudPago> lstOficioRespuestaSP;
	private int doctosObservados;
	private String idExpedientesObservados;
	private Comprador participante;
	private Date fechaRecepcionCA;
	private Date fechaFirmaCA;
	private boolean errorExpedienteCompleto;
	private String msjError;
	private String idExpedientesRequeridos;
	private boolean doctosSinObservacion;
	private String rutaCartaAdhesion;
	private List<DocumentacionSPCartaAdhesion> lstDoctos;
	private String rutaDocObs;
	private String idExpedientesTotal;
	private int habilitaAccionSP; //1:'Documentación Sin Observaciones:',2:'Habilita Oficio de Observaciones'
	private boolean deshabilitaAlcanceDocumentacion;
	private int certDepositoOAlmacenamiento;
	private int tipoConstancia;
	private String busParticipante;
	private Double volumenOriginal;
	private Double volumenComplemento;
	private List<PagosV> lstPagosV;
	private long totalCAFirmadas;
	private long totalCAEnRevision;
	private long totalCACerradas;
	private double totalVolSusceptible;
	private double totalImpSusceptible;
	private double totalVolEnRevision;
	private double totalImpEnRevision;
	private double totalVolEnTramPago;
	private double totalImpEnTramPago;
	private double totalVolPago;
	private double totalImpPago;

	/**Variables del documento del finiquito**/
	private File docFin;
	private String docFinFileName;
	private Date fechaDocFin;
	private Date fechaAcuseFin;
	private String nombreArchivoFiniquito;
	private String rutaFiniquito;
	private boolean expedienteFiniquitado;
	private int tipoVistaFiniquito;
	private Integer [] idOficioRespuestaA;
	private Integer [] idOficioObsSolPagoA;
	
	private Long idSI;
	private Double totalVolumenCertificados;
	private Double totalVolumenConstancia; 

	private Date fechaPeriodoInicialAuditor;
	private Date fechaPeriodoFinalAuditor;
	private String archivoRelacionCompras;
	private boolean reporteCruce;
	private List<BitacoraRelcompras> lstBitacoraRelcompras;
	private String especialista;
	private Anexo32D anexo32d;
	private List<Anexo32D> lstAnexo32D;
	
	
	/***METODOS**/
	public String listarPrograma(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
				lstPrgEspecialistaNumCartasVs = spDAO.consultaPgrEspecialistaNumCartasV(idEspecialista);
			}else{
				lstNumCartasVs= spDAO.consultaProgramaNumCartasV();
				//Realiza conteo de totales
				setTotalCAFirmadas(0);
				setTotalCAEnRevision(0);
				setTotalCACerradas(0);
				totalVolSusceptible = 0.0;
				totalImpSusceptible= 0.0;
				totalVolEnRevision = 0.0;
				totalImpEnRevision = 0.0; 
				totalVolEnTramPago = 0.0;
				totalImpEnTramPago = 0.0; 
				totalVolPago = 0.0;
				totalImpPago=0.0; 
				for(ProgramaNumCartasV p: lstNumCartasVs){
					setTotalCAFirmadas(getTotalCAFirmadas() + p.getNumeroCartas());
					setTotalCAEnRevision(getTotalCAEnRevision() + p.getNoCartaOperacion());
					setTotalCACerradas(getTotalCACerradas() + p.getNoCartaCerradas());
					totalVolSusceptible += p.getVolumenSusceptible();
					totalImpSusceptible += p.getImporteSusceptible();
					totalVolEnRevision += p.getVolumenEnRevision();
					totalImpEnRevision += p.getImporteEnRevision();
					totalVolEnTramPago += p.getVolumenAutorizado();
					totalImpEnTramPago += p.getImporteAutorizado();
					totalVolPago += p.getVolumenApoyado();
					totalImpPago += p.getImporteApoyado();
					
				}
				
			}		
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listarPrograma  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listarPrograma  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}

	public String verCartaAdehsionAsignadas(){
		try{
			session = ActionContext.getContext().getSession();
			//Recupera nombre de programa
			nombrePrograma = cDAO.consultaPrograma(idPrograma).get(0).getDescripcionCorta();			
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
				lstAsignacionCAaEspecialistaV = spDAO.consultaCAaEspecialistaV(idEspecialista, idPrograma);
			}else{
				totalVolSusceptible = 0.0;
				totalImpSusceptible= 0.0;
				totalVolEnRevision = 0.0;
				totalImpEnRevision = 0.0; 
				totalVolEnTramPago = 0.0;
				totalImpEnTramPago = 0.0; 
				totalVolPago = 0.0;
				totalImpPago=0.0;
				lstAsignacionCAaEspecialistaV = spDAO.consultaCAaEspecialistaV(null, idEspecialista, (folioCartaAdhesion !=null?folioCartaAdhesion.trim():""), idPrograma, idEstatusCA, false, 0, busParticipante);
				//Recupera la lista de Especialistas
				lstEspecialista = cDAO.consultaEspecialista(0); 
				lstEstatusCartaAdhesion = cDAO.consultaEstatusCartaAdhesion("3,4,5,6,8,9");
				busParticipante = null;
				for(AsignacionCAaEspecialistaV p: lstAsignacionCAaEspecialistaV ){
					totalVolSusceptible += p.getVolumenAutorizado();
					totalImpSusceptible += p.getImporteAutorizado();
					totalVolEnRevision += p.getVolumenEnRevision();
					totalImpEnRevision += p.getImporteEnRevision();
					totalVolEnTramPago += p.getVolumenAutorizadoPago();
					totalImpEnTramPago += p.getImporteAutorizadoPago();
					totalVolPago += p.getVolumenApoyado();
					totalImpPago += p.getImporteApoyado();
					
				}
				System.out.println("totalVolSusceptible "+totalVolSusceptible);
				
			}
			
			
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listarPrograma  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listarPrograma  debido a: "+e.getMessage());
		}finally{
			
		}
		return SUCCESS;		
	}	
   
	public String consultaCartaAdhesionSP(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 10){
				lstAsignacionCAaEspecialistaV = spDAO.consultaCAaEspecialistaV(null, idEspecialista, folioCartaAdhesion.trim(), idPrograma, idEstatusCA, false, 0, busParticipante);
				//Recupera la lista de Especialistas
				lstEspecialista = cDAO.consultaEspecialista(0); 
				lstEstatusCartaAdhesion = cDAO.consultaEstatusCartaAdhesion("3,4,5,6");
			}		
			//Recupera nombre de programa
			nombrePrograma = cDAO.consultaPrograma(idPrograma).get(0).getDescripcionCorta();			
			folioCartaAdhesion = "";
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en consultaCartaAdhesionSP  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en consultaCartaAdhesionSP  debido a: "+e.getMessage());
		}
		return SUCCESS;		
		
	}

	public String selecAccionDocumentacion(){
		try{
			capDocumentacion(); 
			//Verifica si la documentacion es fianza, documentacion o ambos 1 (Documentacion) 2 (Fianza y Documentacion)
			InicializacionEsquema ie = iDAO.consultaInicializacionPrograma(idPrograma).get(0);
			documentacion = ie.getDocumentacion();		
			tipoDocumentacion = 1;
			SolicitudInscripcionV si = iDAO.consultaSolicitudInscripcionV(folioCartaAdhesion).get(0);
			idSI = si.getIdSI();
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	public String capDocumentacion(){
		try{
			boolean band10 = false, band34 = false;
			recuperaDatosCartaAdhesion("doc"); 
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			rutaFiniquito = ca.getRutaDocFiniquito();
			fechaDocFin = ca.getFechaDocFiniquito();
			fechaAcuseFin = ca.getFechaAcuseFiniquito();
			if(ca.gettCartaAdehsionHastaSP()!=null){
				tCartaAdehsionHastaSP =  ca.gettCartaAdehsionHastaSP();
			}
			if(ca.getTipoConstancia()!=null){
				tipoConstancia = ca.getTipoConstancia();	
			}
			
			//Recupera los expedientes configurados en la inicializacion de la solicitud de pago para documentacion 
			//y comunes entre la fianza
			lstExpedientesProgramasV = spDAO.consultaExpedientesProgramasV(0, idPrograma, "'DSP','DSPYF'");
			if(lstExpedientesProgramasV.size()>0){
				StringBuilder sb= new StringBuilder();				
				for(ExpedientesProgramasV e: lstExpedientesProgramasV){					
					sb.append(e.getIdExpediente()).append(",");
				}
				
				idExpedientesTotal = sb.deleteCharAt(sb.length()-1).toString(); //Todos id de los expedientes configurados en la inicializacion
				if(idExpedientesTotal.contains("10")){
					band10 = true;
				}
				if(idExpedientesTotal.contains("34")){
					band34 = true;
				}				
				if(band10 && band34){
					setCertDepositoOAlmacenamiento(3);
				}else if(band10){
					setCertDepositoOAlmacenamiento(1);
				}else if(band34){
					setCertDepositoOAlmacenamiento(2);
				}					 
			}
			//Verifica que el anexo 32 D ya haya sido cargado
//			if(lstAnexo32D == null){
				lstAnexo32D = spDAO.getMaxFechaAnexo32D(folioCartaAdhesion);
				if(lstAnexo32D.size() > 0){
					anexo32d = lstAnexo32D.get(0);
					anexo32DyaCapturado = true;
				}
//			}			
			
			verificarArchivoRelComprasYReporteCruce();
			if(alcanceDocumentacion){
				lstsExpedientesSPCartaAdhesionV = spDAO.consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, "DSP,DSPYF", "prioridadExpediente");
			}else if(estatusCA == 3){
				llenarListaExpedientesProgramas();
			}else if(estatusCA == 4 || estatusCA ==5 || estatusCA == 9){									
				lstsExpedientesSPCartaAdhesionV = spDAO.consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, "DSP,DSPYF", "prioridadExpediente");
				//recupera el oficio de observaciones
				lstOficioObsSolicitudPago = spDAO.consultaOficioObsSolicitudPago(folioCartaAdhesion);
				if(lstOficioObsSolicitudPago.size()>0){
					fechaDocOBS = lstOficioObsSolicitudPago.get(0).getFechaDocObs();
					fechaAcuseOBS= lstOficioObsSolicitudPago.get(0).getFechaAcuseObs();
					if(lstOficioObsSolicitudPago.get(0).getRutaDocObs()!=null && !lstOficioObsSolicitudPago.get(0).getRutaDocObs().isEmpty()){
						rutaDocObs =lstOficioObsSolicitudPago.get(0).getRutaDocObs();
					}else{
						rutaDocObs = null;
					}
					noOficioOBS = lstOficioObsSolicitudPago.get(0).getNoOficioObs(); 
				}
				setLstOficioRespuestaSP(spDAO.consultaOficioRespuestaSolicitudPago(folioCartaAdhesion));
				if(spDAO.verificarSiHayAlcanceObservacion(folioCartaAdhesion)){
					deshabilitaAlcanceDocumentacion = false;
				}
				//Recupera los pagos 
				lstPagosV = pDAO.consultaPagosV(-1, -1, -1, null, null, "1,2,4,5,6,7,9,10", folioCartaAdhesion);
				for(PagosV p: lstPagosV){
					totalVolEnTramPago += p.getVolumen();
					totalImpEnTramPago += p.getImporte();
				}
				
			}else if(estatusCA == 6 || estatusCA == 8){
				verificarArchivoRelComprasYReporteCruce();
				llenarListaExpedientesProgramas();
			}/*else if(estatusCA == 5 || estatusCA == 9){
				lstsExpedientesSPCartaAdhesionV = spDAO.consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, "DSP,DSPYF", "idExpediente");
			}*/
			if(estatusCA != 3){
				//consulta los auditores capturados
				lstAuditorSolPagoV = spDAO.consultaAuditorSolPagoV(folioCartaAdhesion, 1,0);
			}
			
			//Recupera el total del volumen capturado en los certificados de deposito
			totalVolumenCertificados = spDAO.getSumaCertificadoDepositoByFolioCA(folioCartaAdhesion);
			//Recupera el total del volumen capturado en la constancia de almacenamiento
			totalVolumenConstancia = spDAO.getSumaConstanciasAlmacenamientoByFolioCA(folioCartaAdhesion);
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capDocumentacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capDocumentacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	private void verificarArchivoRelComprasYReporteCruce() {
		//Recupera el archivo de la relacion de compras e indica si hay relacion de cruces
		lstBitacoraRelcompras  = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, "99", null);
		if(lstBitacoraRelcompras.size()>0){
			archivoRelacionCompras = lstBitacoraRelcompras.get(0).getRutaArchivo()+lstBitacoraRelcompras.get(0).getNombreArchivo();
		}		
		//Indica si hay reporte de cruces
		if(rDAO.consultaBitacoraRelacionHCO(folioCartaAdhesion).size() > 0){
			reporteCruce = true;
		}		
	}

	public String recuperaDatosCartaAdhesion(String tipo){
		try{
			//Recupera los datos de la carta adhesion
			//CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			 AsignacionCAaEspecialistaV ca = spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
			 especialista = ca.getEspecialista();
			 //Verifica el estado del comprador "habilitado o inhabilitado"
			setParticipante(cDAO.consultaComprador(ca.getIdComprador()).get(0));
			comprador = participante.getNombre()+(participante.getApellidoPaterno()!=null?participante.getApellidoPaterno():"")+(participante.getApellidoMaterno()!=null?participante.getApellidoMaterno():"");
			idPrograma = ca.getIdPrograma();
			setEstatusCartaAdhesion(ca.getEstatusCartaAdhesion());
			setEstatusCA(ca.getEstatusCA());		
			setFechaRecepcionCA(ca.getFechaAcuseCA());
			//Recupera el archivo de la carta de adhesion firmada
			if(tipo.equals("doc")){
				if(ca.getEstatusCA() == 4){
					idExpedientesObservados ="";
					StringBuilder sb= new StringBuilder();
					//registrar = 3;	
					//Recupera el numero de documentos observados
					List<DocumentacionSPCartaAdhesion> lstExpedientesObservados = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 0, 0, true);
					setDoctosObservados(lstExpedientesObservados.size());
					for(DocumentacionSPCartaAdhesion e: lstExpedientesObservados){
						sb.append(e.getIdExpediente()).append(",");
					}
					if(sb.length()>0){
						idExpedientesObservados = sb.deleteCharAt(sb.length()-1).toString();
					}
					
				}
				
				//Verifica que el anexo haya sido capturado en la fianza
				if(ca.getFianza() != null){
					if(ca.getFianza().equals("SI")){
						anexo32DyaCapturado = true;
						edoCuentaYaCapturado = true;
					}						
				}
			}else{
				if(ca.getFianza()!=null){
					if(ca.getFianza().equals("SI")){
						capturarInfoFianza = true;
						registrar = 2;
					}else{
						registrar = 0;
					}
				}
				
			}
			SolicitudInscripcionV si = iDAO.consultaSolicitudInscripcionV(folioCartaAdhesion).get(0);
			idPrograma = si.getIdPrograma();
			setFechaFirmaCA(si.getFechaFirmaCa());
			programa = cDAO.consultaPrograma(idPrograma).get(0);
			String rutaSolicitud =  programa.getRutaDocumentos()+"solicitudes/"+si.getIdSI()+"/";
			rutaCompleta = rutaSolicitud+si.getNomArchivoCa();
			//nombreAuditor = si.getAuditor();
			//Recupera los representantes legales
			lstRepresentanteV = cDAO.consultaRepresentanteCompradorV(0, si.getIdComprador());
			idCriterioPago = programa.getCriterioPago();  
			nombrePrograma = si.getPrograma();
			cultivo = si.getCultivo();
			if(idCriterioPago == 1 || idCriterioPago ==3 ){ //Volumen o Volumen/Etapa
				volumenAutorizado = si.getVolumenTotalCarta();
				//REcupera el volumen original y en caso de volumen complemento en carta adhesion
				setVolumenOriginal(iDAO.getSumaVolumenAsignacionCA(folioCartaAdhesion, 1));
				setVolumenComplemento(iDAO.getSumaVolumenAsignacionCA(folioCartaAdhesion, 2));
				
			}else{ //Etapa
				importeAutorizado = si.getImporteTotalCarta();
			}	

		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en recuperaDatosCartaAdhesion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaDatosCartaAdhesion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}

	private void llenarListaExpedientesProgramas() {
		List<ObservacionDocumentacionSP> rutaDocHis;
		lstExpedientesProgramasV = spDAO.consultaExpedientesProgramasV(0, idPrograma, "'DSP','DSPYF'");
		lstsExpedientesSPCartaAdhesionV = new ArrayList<DocumentacionSPCartaAdhesionV>();
		for(ExpedientesProgramasV e:lstExpedientesProgramasV ){
			List<DocumentacionSPCartaAdhesion> documentolst = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, e.getIdExpediente());
			if(documentolst.size()>0){
				DocumentacionSPCartaAdhesion documento = documentolst.get(0);
				rutaDocHis = spDAO.consultaObservacionDocumentacion(documento.getIdExpSPCartaAdhesion());
				if(rutaDocHis.size()>0){
					if(e.getIdExpediente() == 1){//Escrito libre
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getIdExpSPCartaAdhesion(), documento.getFechaDocumento(), documento.getFechaAcuse(), documento.getRutaDocumento(), rutaDocHis.get(0).getRutaDocumento()));
					}else if(e.getIdExpediente() == 3){//Solicitud de pago del apoyo
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getIdExpSPCartaAdhesion(),  documento.getRutaDocumento(), documento.getVolumen(), documento.getObservacion(), rutaDocHis.get(0).getRutaDocumento()));
					}else if(e.getIdExpediente() == 4){//Estado de Cuenta
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion(), rutaDocHis.get(0).getRutaDocumento()));
					}else if(e.getIdExpediente() == 5){//Anexo  32-D
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion(), rutaDocHis.get(0).getRutaDocumento()));
					}else if(e.getIdExpediente() == 7){//Dictamen del Auditor
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion(), documento.getPeriodoInicialAuditor(), documento.getPeriodoFinalAuditor(),rutaDocHis.get(0).getRutaDocumento()));
					}else if(e.getIdExpediente() == 10){//Certificados de depositos
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(),  documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion(), rutaDocHis.get(0).getRutaDocumento()));
					}else if(e.getIdExpediente() == 34){//Constancia de almacenamiento
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion,  documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(),  documento.getRutaDocumento(), documento.getObservacion(), rutaDocHis.get(0).getRutaDocumento()));
					}else{
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion(), rutaDocHis.get(0).getRutaDocumento()));
					}

				} else {
				
					if(e.getIdExpediente() == 1){//Escrito libre
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getIdExpSPCartaAdhesion(), documento.getFechaDocumento(), documento.getFechaAcuse(), documento.getRutaDocumento()));
					}else if(e.getIdExpediente() == 3){//Solicitud de pago del apoyo
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getIdExpSPCartaAdhesion(),  documento.getRutaDocumento(), documento.getVolumen(), documento.getObservacion()));
					}else if(e.getIdExpediente() == 4){//Estado de Cuenta
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion()));
					}else if(e.getIdExpediente() == 5){//Anexo  32-D
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion()));
					}else if(e.getIdExpediente() == 7){//Dictamen del Auditor
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion(), documento.getPeriodoInicialAuditor(), documento.getPeriodoFinalAuditor()));
					}else if(e.getIdExpediente() == 10){//Certificados de depositos
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(),  documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion()));
					}else if(e.getIdExpediente() == 34){//Constancia de almacenamiento
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion,  documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(),  documento.getRutaDocumento(), documento.getObservacion()));
					}else{
						lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), folioCartaAdhesion, documento.getFechaExpedicionAnexo(), documento.getIdExpSPCartaAdhesion(), documento.getRutaDocumento(), documento.getObservacion()));
					}
				}
			 }else{
				 lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(folioCartaAdhesion, e.getIdExpediente(), e.getExpediente(), e.isDocumentacionOpcional()));
			 }
		}
		
		int j=0, indice=-1;
		if(estatusCA == 6 || estatusCA == 8){
			if(tipoConstancia!=0){
				if(tipoConstancia==1){
					for(DocumentacionSPCartaAdhesionV v:lstsExpedientesSPCartaAdhesionV){
						if(v.getIdExpediente()==34){
							indice=j;
							break;
						}
						j++;
					}
					if(indice!= -1){
						lstsExpedientesSPCartaAdhesionV.remove(indice);
					}
				}else if(tipoConstancia==2){
					for(DocumentacionSPCartaAdhesionV v:lstsExpedientesSPCartaAdhesionV){
						if(v.getIdExpediente()==10){
							indice=j;
							break;
						}
						j++;
					}
					if(indice!= -1){
						lstsExpedientesSPCartaAdhesionV.remove(indice);
					}
					
				}
				
			}
		}
		
	}

	public String alcanceDocumentacion(){
		try{
			capDocumentacion();			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en alcanceDocumentacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en alcanceDocumentacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	public String activarNuevasObservaciones(){
		try{
			capDocumentacion();
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en alcanceDocumentacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en alcanceDocumentacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	public String sustituirArchivo(){
		try{
			capDocumentacion();
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en sustituirArchivo  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en sustituirArchivo  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}

	public String docParcial(){
		try{
			capDocumentacion();
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en docParcial  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en docParcial  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	public String capFianza(){
		try{
			recuperaDatosCartaAdhesion("fianza");
			//recuperaDocRequeridosFianza();
			//Recupera los expedientes configurados en la inicializacion de la solicitud de pago para fianza 
			//y comunes entre la documentacion
			if(registrar == 0){
				lstExpedientesProgramasV = spDAO.consultaExpedientesProgramasV(0, idPrograma, "'F','DSPYF'");
				lstsExpedientesSPCartaAdhesionV = new ArrayList<DocumentacionSPCartaAdhesionV>();
				String rutaDocumento = null;
				for(ExpedientesProgramasV e:lstExpedientesProgramasV ){
					if(e.getTipo().equals("DSPYF")){						
						//Recupera datos de los expedientes comunes entre documentacion y fianza
						List<DocumentacionSPCartaAdhesion> listDocumentos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, e.getIdExpediente());
						if(listDocumentos.size()>0){
							rutaDocumento = listDocumentos.get(0).getRutaDocumento();	
						}

					}
					lstsExpedientesSPCartaAdhesionV.add(new DocumentacionSPCartaAdhesionV(e.getIdExpediente(), e.getExpediente(), rutaDocumento, !(e.isDocumentacionOpcional())));
					rutaDocumento = null;
				}
			}else if(registrar == 2){
				lstsExpedientesSPCartaAdhesionV = spDAO.consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, "F,DSPYF", "prioridadExpediente");
			}	
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capFianza  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capFianza  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	/* 3; "ASIGNADO A ESPECIALISTA"
	   4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
	   5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES"
	   6; "DOCUMENTACIÓN PARCIAL SIN OBSERVACIONES"
	   8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
	 */
	public String registraCapDocumentacion(){
		try{
			if(habilitaAccionSP==1){
				doctosSinObservacion = true;
				habilitarOficioObs = false;
			}else if(habilitaAccionSP==2){
				habilitarOficioObs = true;
				doctosSinObservacion=false;				
			}
			
			if(doctosSinObservacion == true || habilitarOficioObs == true ){
				//Validar que el anexo haya sido cargado en el modulo de carga de anexo 32 D
				lstAnexo32D = spDAO.getMaxFechaAnexo32D(folioCartaAdhesion);
				if(lstAnexo32D.size() == 0){
					errorSistema = 1;
					addActionError("Debe cargar el anexo 32D, antes de marcar 'TRAMITAR OFICIO DE OBSERVACIONES Y PAGO' o 'TRAMITAR PAGO SIN OBSERVACIONES' ");
					return SUCCESS;
				}
			}
			rutaCartaAdhesion = getRecuperaRutaCarta();			
			File f1 = null, f2=null; 
			boolean band = false;
			int periodoCAhastaSP = 0, periodoSPOO = 0, periodoORPago=0;
			Date fechaFirmaCA = null;
			long idOficioRespuesta = 0;
			//Recupera expedientes configurados en la inicializacion 
			lstExpedientesProgramasV = spDAO.consultaExpedientesProgramasV(0, idPrograma, "'DSP','DSPYF'");
			//Recupera los datos de la carta de adhesion
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			//Validacion de tamaños de archivos
			if(estatusCA == 3 || estatusCA == 6 || estatusCA == 8){
				//Valida el tamaño de los archivos a cargar
				validarDocumentos();
				if(errorSistema == 1){
					addActionError("Los archivos a cargar no deben exceder de 3MB, por favor verifique");
					return SUCCESS;
				}
			}
			//Consigue los valores de fecha Documento y fecha Acuse, en caso de que se haya capturado en documentacion parcial o
			if(estatusCA != 3){
				tCartaAdehsionHastaSP =  ca.gettCartaAdehsionHastaSP();
				DocumentacionSPCartaAdhesion documento = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 1).get(0);
				fechaDocEntDoctos = documento.getFechaDocumento();
				fechaAcuseEntDoctos = documento.getFechaAcuse();
			}			
					
			if(estatusCA==3 || estatusCA==4){
				//Recupera la fecha de carta de adhesion
				SolicitudInscripcion si = iDAO.consultaSolicitudInscripcion(folioCartaAdhesion).get(0);
				fechaFirmaCA = si.getFechaFirmaCa();
				//Recupea el periodo de "Emision de Carta Adhesion hasta presentacion de solicitud de pago"
				InicializacionEsquema ie = iDAO.consultaInicializacionPrograma(ca.getIdPrograma()).get(0);
				//Verifica fecha limite de emision de carta adhesion hasta presentacion de solicitud de pago
				periodoCAhastaSP = ie.getPeriodoCartaSP();
				periodoSPOO = ie.getPeriodoSPOO();
				periodoORPago = ie.getPeriodoORPago();
				if(estatusCA==3){
					Date fechaLimiteFCAHSP = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(fechaFirmaCA).toString(), periodoCAhastaSP);
					String fechaLimiteFCAHSPS = new SimpleDateFormat("yyyyMMdd").format(fechaLimiteFCAHSP).toString();
					String fechaAcuseEntDoctosS = new SimpleDateFormat("yyyyMMdd").format(fechaAcuseEntDoctos).toString();	
					if(Long.parseLong(fechaAcuseEntDoctosS)>Long.parseLong(fechaLimiteFCAHSPS)){
						tCartaAdehsionHastaSP = true;
				    }else{
				    	tCartaAdehsionHastaSP = false;			    
				    }
					ca.setDocumentacion(true);
					ca.settCartaAdehsionHastaSP(tCartaAdehsionHastaSP);
				}
			}
			
			OficioObsSolicitudPago oficioObsSP = new OficioObsSolicitudPago();
			if((habilitarOficioObs)|| alcanceDocumentacion){
				//Antes que nada, validar que archivo del oficio de observaciones a cargar no exceda de 3MB
				if(docObs!=null){
					verificarTamanioArchivo(docObs);
					if(errorSistema == 1){
						addActionError("El oficio de observaciones no debe exceder de 3MB, por favor verifique");
						return SUCCESS;
					}
				}
				//Guarda el oficio de observacion
				oficioObsSP = new OficioObsSolicitudPago();
				oficioObsSP.setFolioCartaAdhesion(folioCartaAdhesion);
				if(fechaDocOBS!=null){
					oficioObsSP.setFechaDocObs(fechaDocOBS);	
				}
				if(fechaAcuseOBS!=null){
					oficioObsSP.setFechaAcuseObs(fechaAcuseOBS);
				}
		
				oficioObsSP.setNoOficioObs(noOficioOBS);
				oficioObsSP.setFechaCreacion(new Date());
				if(alcanceDocumentacion){
					oficioObsSP.setAlcance(true);
				}else{
					oficioObsSP.setAlcance(false);
				}
				
				if(fechaAcuseOBS!=null){
					//Verifica la fecha del oficio de observacion no este fuera de tiempo
					Date fechaLimiteSPHOO = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(fechaAcuseEntDoctos).toString(), periodoSPOO);
					String fechaLimiteSPHOOS = new SimpleDateFormat("yyyyMMdd").format(fechaLimiteSPHOO).toString();
					String fechaAcuseOBSS = new SimpleDateFormat("yyyyMMdd").format(fechaAcuseOBS).toString();	
					if(Long.parseLong(fechaAcuseOBSS)>Long.parseLong(fechaLimiteSPHOOS)){
						oficioObsSP.settSolicitudPagoHastaOficioObs(true); //La fecha de Acuse de OBS esta fuera de tiempo
				    }else{
				    	oficioObsSP.settSolicitudPagoHastaOficioObs(false);
				    }	
				}
				if(docObs!=null){
					ext = docObsFileName.toLowerCase().substring(docObsFileName.lastIndexOf("."));
					if(alcanceDocumentacion){
						nombreArchivo = "OOBSA"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Oficio de observaciones con alcance
					}else{
						nombreArchivo = "OOBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Oficio de observaciones
					}					
					Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, docObs);
					oficioObsSP.setRutaDocObs(rutaCartaAdhesion+nombreArchivo);
				}
				
				//Guarda Oficio
				oficioObsSP = (OficioObsSolicitudPago) cDAO.guardaObjeto(oficioObsSP);
								
			}
			
			if(estatusCA==4 && !alcanceDocumentacion){// registrar respuesta de oficio de observaciones
				
				//Verifica que los datos del oficio de observaciones se encuentren completos
				lstOficioObsSolicitudPago = spDAO.consultaOficioObsSolicitudPago(folioCartaAdhesion);
				if(lstOficioObsSolicitudPago.size()>0){
					oficioObsSP = lstOficioObsSolicitudPago.get(0);
					if(docObs!=null){
						verificarTamanioArchivo(docObs);
						if(errorSistema == 1){
							addActionError("El oficio de observaciones no debe exceder de 3MB, por favor verifique");
							return SUCCESS;
						}
						oficioObsSP.setFechaDocObs(fechaDocOBS);	
						oficioObsSP.setFechaAcuseObs(fechaAcuseOBS);						
						ext = docObsFileName.toLowerCase().substring(docObsFileName.lastIndexOf("."));
						nombreArchivo = "OOBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Oficio de observaciones			
						Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, docObs);
						oficioObsSP.setRutaDocObs(rutaCartaAdhesion+nombreArchivo);						
						//Verifica la fecha del oficio de observacion no este fuera de tiempo
						Date fechaLimiteSPHOO = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(fechaAcuseEntDoctos).toString(), periodoSPOO);
						String fechaLimiteSPHOOS = new SimpleDateFormat("yyyyMMdd").format(fechaLimiteSPHOO).toString();
						String fechaAcuseOBSS = new SimpleDateFormat("yyyyMMdd").format(fechaAcuseOBS).toString();	
						if(Long.parseLong(fechaAcuseOBSS)>Long.parseLong(fechaLimiteSPHOOS)){
							oficioObsSP.settSolicitudPagoHastaOficioObs(true); //La fecha de Acuse de OBS esta fuera de tiempo
					    }else{
					    	oficioObsSP.settSolicitudPagoHastaOficioObs(false);
					    }
						oficioObsSP = (OficioObsSolicitudPago) cDAO.guardaObjeto(oficioObsSP);
					}
				}//End si hubo oficio registrado en la base de datos
				
				//validar documentos de los oficios de respuesta
				if(docResp != null){
					verificarTamanioArchivo(docResp);
					if(errorSistema == 1){
						addActionError("El archivo de respuesta a cargar no deben exceder de 3MB, por favor verifique");
						return SUCCESS;
					}
				}
				//Carga oficio de respuesta, y crea nuevo registro en la tabla de oficio_respuesta_solicitud_pago
				if(fechaDocResp != null){
					OficioRespuestaSolicitudPago orsp = new OficioRespuestaSolicitudPago();
					orsp.setFolioCartaAdhesion(folioCartaAdhesion);
					orsp.setFechaDocResp(fechaDocResp);
					orsp.setFechaAcuseResp(fechaAcuseResp);
					orsp.setNoOficioRespuesta("S/N");
					orsp.setFechaCreacion(new Date());
					orsp.setRutaDocRespuesta(rutaCartaAdhesion);
					
					System.out.println("docRespFileName "+docRespFileName);
					ext = docRespFileName.toLowerCase().substring(docRespFileName.lastIndexOf(".") );
					
					System.out.println("Ext "+ext);
					nombreArchivo = "resp"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Oficio de respuesta de observaciones
				
					System.out.println("Nombre Archivo respuesta: "+nombreArchivo);
					Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, docResp);
					orsp.setNomArchivoRespuesta(nombreArchivo);					
					orsp = (OficioRespuestaSolicitudPago) cDAO.guardaObjeto(orsp);
					idOficioRespuesta = orsp.getIdOficioRespuesta();
				}
				
			}//end estatusCA == 4 respuesta de oficio de observaciones 
		
			if(sustituirArchivo){
				//No cambia estatus
			}else if(alcanceDocumentacion){
				//No cambia estatus				
			}else if(estatusCA == 3 && capObsExpediente == null && !habilitarOficioObs && !doctosSinObservacion){ //sin ninguna observacion sin subir oficio de observaciones
				ca.setEstatus(6); //DOCUMENTACIÓN PARCIAL
			}else if(estatusCA == 3 && capObsExpediente == null && doctosSinObservacion && !habilitarOficioObs){//sin ninguna observacion con marca de doctos sin observacion
				ca.setEstatus(5); //5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES"
				System.out.println("Estatus "+5);
			}else if(estatusCA == 3 && capObsExpediente != null && habilitarOficioObs && !doctosSinObservacion){//marcar observacion con oficio de observaciones
				ca.setEstatus(4); //4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
			}else if(estatusCA == 3 && capObsExpediente != null && !habilitarOficioObs && !doctosSinObservacion){//marcar observacion sin oficio de observaciones
				ca.setEstatus(8); //8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
			}else if(estatusCA == 6 && capObsExpediente == null && !habilitarOficioObs && !doctosSinObservacion){ //sin ninguna observacion sin subir oficio de observaciones
				ca.setEstatus(6); //DOCUMENTACIÓN PARCIAL
			}else if(estatusCA == 6 && capObsExpediente == null && doctosSinObservacion && !habilitarOficioObs ){//sin ninguna observacion con marca de doctos sin observacion
				ca.setEstatus(5); //5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES"
			}else if(estatusCA == 6 && capObsExpediente != null && habilitarOficioObs && !doctosSinObservacion){//marcar observacion con oficio de observaciones
				ca.setEstatus(4); //4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
			}else if(estatusCA == 6 && capObsExpediente != null && !habilitarOficioObs && !doctosSinObservacion){//marcar observacion sin oficio de observaciones
				ca.setEstatus(8); //8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
			}else if(estatusCA == 8 && capObsExpediente == null && !habilitarOficioObs && !doctosSinObservacion){//sin ninguna observacion sin subir oficio de observaciones
				ca.setEstatus(6); //DOCUMENTACIÓN PARCIAL
			}else if(estatusCA == 8 && capObsExpediente == null && doctosSinObservacion && !habilitarOficioObs){//sin ninguna observacion con marca de doctos sin observacion
				ca.setEstatus(5); //5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES"
			}else if(estatusCA == 8 && capObsExpediente != null && habilitarOficioObs && !doctosSinObservacion){//marcar observacion con oficio de observaciones
				ca.setEstatus(4); //4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
			}else if(estatusCA == 8 && capObsExpediente != null && !habilitarOficioObs && !doctosSinObservacion){//marcar observacion sin oficio de observaciones
				ca.setEstatus(8); //8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
			}else if(estatusCA==4){
				//ca.setEstatus(5);//5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES" 
			}
			
			for(ExpedientesProgramasV epv: lstExpedientesProgramasV){//expedientes:1,2,3,5,6,7,8,9,10,11,12,15
				if(epv.getIdExpediente() != 2){//Diferente de la carta de adhesion
					boolean siCargoDocto = verificarSicargoDocumento(epv.getIdExpediente());
					band =  verificaSiHayObservacionExpediente(epv.getIdExpediente());
					String rutaDocumento = "";
					if(alcanceDocumentacion){
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente()).get(0);
							//Si el documento ya estaba marcado anteriormente y  sigue aplicando insertar en la tabla de observacion_documentacion_sp 
							band =  verificaSiHayObservacionExpediente(epv.getIdExpediente());
							//Si el documento no estaba observado y en el alcance tampoco esta marcado
							if(!docSPCA.getObservacion() && !band){
								//no hace nada, solo para considerarlo
							}else if(!docSPCA.getObservacion() && band){//No estaba observado y ahora si se marco como observado 
								//Renombrar archivo anterior para guardarlo en la ruta de observados
								if(docSPCA.getRutaDocumento() != null && !docSPCA.getRutaDocumento().isEmpty()){
									f1 = new File(docSPCA.getRutaDocumento());
									nombreArchivo = docSPCA.getRutaDocumento().substring(docSPCA.getRutaDocumento().lastIndexOf("/")+1);
									nombreArchivo = "OBS"+nombreArchivo;
									//nombreArchivo = recuperaNomArchivoYCargaArchivo(epv.getIdExpediente(), rutaCartaAdhesion, true, false);
									f2 = new File(rutaCartaAdhesion+nombreArchivo);
									if(!f1.renameTo(f2)){
										AppLogger.error("errores","No se pudo renombrar el archivo  del expediente "+epv.getIdExpediente()+" en el alcance de documento" );
										addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
										errorSistema = 1;
										return SUCCESS;
									}
								}else{
									rutaCartaAdhesion = "";
									nombreArchivo = "";
								}
								
								//Guarda en la tabla de observaciones expedientes nuevo registro
								guardarObservados(oficioObsSP.getIdOficioObsSolPago(), rutaCartaAdhesion+nombreArchivo);
								//Cargar archivo nuevo
								docSPCA.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
								docSPCA.setObservacion(true);
								cDAO.guardaObjeto(docSPCA);								
							}/*else if(docSPCA.getObservacion() && !band){ //Estaba observado y ahora se marca como no observado, este caso no deberia suceder
								//Solo se actualiza a no observado
								docSPCA.setObservacion(false);
								cDAO.guardaObjeto(docSPCA);								
							}/*else if(docSPCA.getObservacion() && band){
								ObservacionDocumentacionSP obsDoc = new ObservacionDocumentacionSP();
								obsDoc.setIdExpSPCA(docSPCA.getIdExpSPCartaAdhesion());
								obsDoc.setIdOficioObsSP(oficioObsSP.getIdOficioObsSolPago());
								obsDoc.setRutaDocumento(docSPCA.getRutaDocumento());
								cDAO.guardaObjeto(obsDoc);															
							}*/
							deshabilitaAlcanceDocumentacion = false;
						}//end hay documento registrado
						
					}else if(sustituirArchivo){
						//Verifica si cargo el documento
						siCargoDocto = verificarSicargoDocumento(epv.getIdExpediente());
						String nombreArchivoAnterior = "";
						if(siCargoDocto){
							//Recupera registro del documento
							DocumentacionSPCartaAdhesion docSPCA = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente()).get(0);
							boolean observacion = docSPCA.getObservacion();
							nombreArchivo = recuperaNomArchivoYCargaArchivo(epv.getIdExpediente(), rutaCartaAdhesion, (observacion ? true:false), true);
							nombreArchivoAnterior = docSPCA.getRutaDocumento();
							docSPCA.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
							cDAO.guardaObjeto(docSPCA);
							if(nombreArchivoAnterior != null && !nombreArchivoAnterior.isEmpty()){
								//Borrar archivo de la carpeta							
								File f = new File(nombreArchivoAnterior);
								if(f.exists()){
									if(!f.delete()){
										AppLogger.error("errores","Error al eliminar: "+nombreArchivoAnterior+" al sustituir el archivo");
									}else{
										AppLogger.info("app","Se borro archivo: "+nombreArchivoAnterior+" al sustituir el archivo");
									}
								}
							}							
							//Verifica si el documento esta observado
							if(docSPCA.getObservacion()){
								String nombreArchivoObservacion = ""; 
								if(nombreArchivoAnterior != null && !nombreArchivoAnterior.isEmpty()){
									nombreArchivoAnterior = nombreArchivoAnterior.substring(nombreArchivoAnterior.lastIndexOf("/")+1);
									//Recupera de la tabla de observados 
									List<ObservacionDocumentacionSP> lstObservacionDocumentacionSP = spDAO.consultaObservacionDocumentacion(docSPCA.getIdExpSPCartaAdhesion());
									if(nombreArchivoAnterior != null && !nombreArchivoAnterior.isEmpty()){
										for(ObservacionDocumentacionSP o: lstObservacionDocumentacionSP){										
											if(o.getRutaDocumento() != null && !o.getRutaDocumento().isEmpty()){
												nombreArchivoObservacion = o.getRutaDocumento().substring(o.getRutaDocumento().lastIndexOf("/")+1);
												if(nombreArchivoObservacion.equals(nombreArchivoAnterior)){
													//Actualizar registro de observaciones
													o.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
													cDAO.guardaObjeto(o);
													break;
												}
											}
										}	
									}		
								}
							}
						}
						
					}else if(estatusCA == 4){						
						if(idPrograma < 41 || (idPrograma >= 41 && epv.getIdExpediente() != 8 && epv.getIdExpediente() != 9 && epv.getIdExpediente() != 5 )){ 
							if(siCargoDocto){
								DocumentacionSPCartaAdhesion documento = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente()).get(0);
								nombreArchivo = recuperaNomArchivoYCargaArchivo(epv.getIdExpediente(), rutaCartaAdhesion, false, true);
								documento.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
								documento.setObservacion(false);
								if(epv.getIdExpediente() == 3){
									if(idCriterioPago == 1 || idCriterioPago == 3){
										//Guarda el Volumen Solicitado a Apoyar  
										documento.setVolumen(volumen);
									}
								}
								List<ObservacionDocumentacionSP> lstObservacionDocumentacionSP = spDAO.consultaObservacionDocumentacion(documento.getIdExpSPCartaAdhesion());
								for(ObservacionDocumentacionSP odsp : lstObservacionDocumentacionSP){
									odsp.setIdOficioRespuesta(idOficioRespuesta);
									//Verifica las fechas de tramite hasta la solicitud de pago
									OficioObsSolicitudPago oficioObsSolicitudPago = spDAO.consultaOficioObsSolPagoMaxIdExp(odsp.getIdExpSPCA()).get(0);
									Date fechaLimiteORPago = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(oficioObsSolicitudPago.getFechaAcuseObs()).toString(), periodoORPago);
									String fechaLimiteORPagoS = new SimpleDateFormat("yyyyMMdd").format(fechaLimiteORPago).toString();
									String fechaAcuseRespS = new SimpleDateFormat("yyyyMMdd").format(fechaAcuseResp).toString();	
									if(Long.parseLong(fechaAcuseRespS)>Long.parseLong(fechaLimiteORPagoS)){
										odsp.settRespuestaSP(true);
								    }else{
								    	odsp.settRespuestaSP(false);
								    }				
									//odsp.settRespuestaSP(false);
									cDAO.guardaObjeto(odsp);	
								}
								documento = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(documento);
							}							
						}else {							
							if(!band){						
								DocumentacionSPCartaAdhesion documento = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente()).get(0);
								documento.setObservacion(false);					
								List<ObservacionDocumentacionSP> lstObservacionDocumentacionSP = spDAO.consultaObservacionDocumentacion(documento.getIdExpSPCartaAdhesion());
								for(ObservacionDocumentacionSP odsp : lstObservacionDocumentacionSP){
									odsp.setIdOficioRespuesta(idOficioRespuesta);
									//Verifica las fechas de tramite hasta la solicitud de pago
									OficioObsSolicitudPago oficioObsSolicitudPago = spDAO.consultaOficioObsSolPagoMaxIdExp(odsp.getIdExpSPCA()).get(0);
									Date fechaLimiteORPago = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(oficioObsSolicitudPago.getFechaAcuseObs()).toString(), periodoORPago);
									String fechaLimiteORPagoS = new SimpleDateFormat("yyyyMMdd").format(fechaLimiteORPago).toString();
									String fechaAcuseRespS = new SimpleDateFormat("yyyyMMdd").format(fechaAcuseResp).toString();	
									if(Long.parseLong(fechaAcuseRespS)>Long.parseLong(fechaLimiteORPagoS)){
										odsp.settRespuestaSP(true);
								    }else{
								    	odsp.settRespuestaSP(false);
								    }				
									//odsp.settRespuestaSP(false);
									cDAO.guardaObjeto(odsp);	
								}
								documento = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(documento);
							}
							
						}
						
						
						
					}else if(estatusCA == 3 && capObsExpediente == null && !habilitarOficioObs && !doctosSinObservacion){//sin ninguna observacion sin subir oficio de observaciones
						//ca.setEstatus(6); //DOCUMENTACIÓN PARCIAL
						if(siCargoDocto){	
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
							cargarArchivos(epv.getIdExpediente(), band);
							docSPCA.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}else if(!siCargoDocto && band){
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
							docSPCA.setRutaDocumento(null);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}						
					}else if(estatusCA == 3 && capObsExpediente == null && doctosSinObservacion && !habilitarOficioObs){//sin ninguna observación con marca de doctos sin observacion
						//ca.setEstatus(5); //DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES
						if(siCargoDocto){	
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
							cargarArchivos(epv.getIdExpediente(), band);
							docSPCA.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}			
						
						if(epv.getIdExpediente() == 5){ //Cambios 12-01-2016
							//Verifica si existe cargado el anexo 32 D
							
						}
					}else if(estatusCA == 3 && capObsExpediente != null && habilitarOficioObs && !doctosSinObservacion){//marcar observacion con oficio de observaciones
						if(siCargoDocto){	
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
							cargarArchivos(epv.getIdExpediente(), band);
							rutaDocumento = rutaCartaAdhesion+nombreArchivo;
							docSPCA.setRutaDocumento(rutaDocumento);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}else if(!siCargoDocto && band){
							rutaDocumento = null;
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);							
							docSPCA.setRutaDocumento(rutaDocumento);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}
						if(band){
							//Guarda en la tabla de observacion_documentacion_sp
							guardarObservados(oficioObsSP.getIdOficioObsSolPago(), rutaDocumento);	
						}
						//ca.setEstatus(4); //4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
					}else if(estatusCA == 3 && capObsExpediente != null && !habilitarOficioObs && !doctosSinObservacion){//marcar observacion sin oficio de observaciones
						//ca.setEstatus(8); //8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
						if(siCargoDocto){						
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
							cargarArchivos(epv.getIdExpediente(), band);
							rutaDocumento = rutaCartaAdhesion+nombreArchivo;
							docSPCA.setRutaDocumento(rutaDocumento);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}else if(!siCargoDocto && band){
							rutaDocumento = null;
							setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);							
							docSPCA.setRutaDocumento(rutaDocumento);
							docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
						}
						if(band){
							//Guarda en la tabla de observacion_documentacion_sp
							guardarObservados(-1, rutaDocumento);	
						}
						
					}else if(estatusCA == 6 && capObsExpediente == null && !habilitarOficioObs && !doctosSinObservacion){ //sin ninguna observacion sin subir oficio de observaciones
						//ca.setEstatus(6); //DOCUMENTACIÓN PARCIAL
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, -1,  siCargoDocto, docSPCA.getRutaDocumento()); 
						}else{
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
						}					
												
					}else if(estatusCA == 6 && capObsExpediente == null && doctosSinObservacion && !habilitarOficioObs){//sin ninguna observacion con marca de doctos sin observacion
						//ca.setEstatus(5); //5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES"
						//Verifica si existe Documento
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							System.out.println("expediente "+epv.getIdExpediente());
							verificaEstadoDocumento(epv.getIdExpediente(), band, -1,  false, docSPCA.getRutaConstancias() ); //band siempre es false
						}else{
							//Nuevo registro en documentacion
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
						}						
					}else if(estatusCA == 6 && capObsExpediente != null && habilitarOficioObs && !doctosSinObservacion){//marcar observacion con oficio de observaciones
						//ca.setEstatus(4); //4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, oficioObsSP.getIdOficioObsSolPago(), siCargoDocto, docSPCA.getRutaDocumento()); 
						}else{
							//Nuevo registro en documentacion
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}else if(!siCargoDocto && band){
								rutaDocumento = null;
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);							
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
							if(band){
								//Guarda en la tabla de observacion_documentacion_sp
								guardarObservados(oficioObsSP.getIdOficioObsSolPago(), rutaDocumento);	
							}
						}	
						
						
					}else if(estatusCA == 6 && capObsExpediente != null && !habilitarOficioObs && !doctosSinObservacion){//marcar observacion sin oficio de observaciones
						//ca.setEstatus(8); //8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, -1, siCargoDocto, docSPCA.getRutaDocumento()); 
						}else{
							//Nuevo registro en documentacion
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}else if(!siCargoDocto && band){
								rutaDocumento = null;
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);							
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
							if(band){
								//Guarda en la tabla de observacion_documentacion_sp
								guardarObservados(-1, rutaDocumento);	
							}
						}		
					}else if(estatusCA == 8 && capObsExpediente == null && !habilitarOficioObs && !doctosSinObservacion){//sin ninguna observacion sin subir oficio de observaciones
						//ca.setEstatus(6); //DOCUMENTACIÓN PARCIAL
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, -1,  siCargoDocto, docSPCA.getRutaDocumento()); 
						}else{
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
						}	
						
					}else if(estatusCA == 8 && capObsExpediente == null && doctosSinObservacion && !habilitarOficioObs){//sin ninguna observacion con marca de doctos sin observacion
						//ca.setEstatus(5); //5; "DOCUMENTACIÓN COMPLETA SIN OBSERVACIONES"
						//Verifica si existe Documento
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, -1,  false, docSPCA.getRutaDocumento()); //band siempre es false
						}else{
							//Nuevo registro en documentacion
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
						}	
					}else if(estatusCA == 8 && capObsExpediente != null && habilitarOficioObs && !doctosSinObservacion){//marcar observacion con oficio de observaciones
						//ca.setEstatus(4); //4; "DOCUMENTACIÓN COMPLETA CON OBSERVACIONES"
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, oficioObsSP.getIdOficioObsSolPago(), siCargoDocto, docSPCA.getRutaDocumento());
							
						}else{
							//Nuevo registro en documentacion
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}else if(!siCargoDocto && band){
								rutaDocumento = null;
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);							
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
							if(band){
								//Guarda en la tabla de observabcion_documentacion_sp
								guardarObservados(oficioObsSP.getIdOficioObsSolPago(), rutaDocumento);	
							}
						}
					}else if(estatusCA == 8 && capObsExpediente != null && !habilitarOficioObs && !doctosSinObservacion){//marcar observacion sin oficio de observaciones
						//ca.setEstatus(8); //8; "DOCUMENTACIÓN PARCIAL CON OBSERVACIONES"
						lstDoctos = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente());
						if(lstDoctos.size()>0){
							docSPCA = lstDoctos.get(0);
							verificaEstadoDocumento(epv.getIdExpediente(), band, -1, siCargoDocto, docSPCA.getRutaDocumento()); 
						}else{
							//Nuevo registro en documentacion
							if(siCargoDocto){						
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);
								cargarArchivos(epv.getIdExpediente(), band);
								rutaDocumento = rutaCartaAdhesion+nombreArchivo;
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}else if(!siCargoDocto && band){
								rutaDocumento = null;
								setDocumentacionSPCartaAdhesion(epv.getIdExpediente(), band);							
								docSPCA.setRutaDocumento(rutaDocumento);
								docSPCA = (DocumentacionSPCartaAdhesion) cDAO.guardaObjeto(docSPCA);
							}
							if(band){
								//Guarda en la tabla de observacion_documentacion_sp
								guardarObservados(-1, rutaDocumento);	
							}
						}						
					}
				}//End idExpediente !=2
			}// end FOR
			//Verifica que se hayan insertado los expedientes  5 Anexo 32 D, 8 Relacion de Compras y 9 reporte de cruce, esto debido 
			//a la eliminacion de carga de archivo, sobre todo cuando nunca es observado cualquiera de los documentos, considerando que 
			//estos documentos siempre son forzosos.
			//ANEXO 32D
			 List<DocumentacionSPCartaAdhesion> lst = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 5);
			if(lst.size() > 0){
				docSPCA = lst.get(0);
			}else{
				docSPCA = new DocumentacionSPCartaAdhesion();
				docSPCA.setFolioCartaAdhesion(folioCartaAdhesion);
				docSPCA.setFechaDocumento(fechaDocEntDoctos);
				docSPCA.setFechaAcuse(fechaAcuseEntDoctos);
				docSPCA.setIdExpediente(5);
				cDAO.guardaObjeto(docSPCA);
			}		
			
			if(lstAnexo32D == null){
				lstAnexo32D = spDAO.getMaxFechaAnexo32D(folioCartaAdhesion);
				if(lstAnexo32D.size() > 0){
					anexo32d = lstAnexo32D.get(0);
					System.out.println("primer anexo "+anexo32d.getFechaAnexo32d());
					docSPCA.setFechaExpedicionAnexo(anexo32d.getFechaAnexo32d());
					docSPCA.setRutaDocumento(anexo32d.getRutaArchivo()+anexo32d.getNombreArchivo());
					cDAO.guardaObjeto(docSPCA);
				}
				
			}
			
			
			//8 RELACION DE COMPRAS
			lst = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 8);
			if(lst.size() == 0){
				docSPCA = new DocumentacionSPCartaAdhesion();
				docSPCA.setFolioCartaAdhesion(folioCartaAdhesion);
				docSPCA.setIdExpediente(8);
				docSPCA.setFechaDocumento(fechaDocEntDoctos);
				docSPCA.setFechaAcuse(fechaAcuseEntDoctos);
				cDAO.guardaObjeto(docSPCA);
			}
		
			//9 REPORTE DE CRUCE
			lst = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 9);
			if(lst.size() == 0){
				docSPCA = new DocumentacionSPCartaAdhesion();
				docSPCA.setFolioCartaAdhesion(folioCartaAdhesion);
				docSPCA.setIdExpediente(9);
				docSPCA.setFechaDocumento(fechaDocEntDoctos);
				docSPCA.setFechaAcuse(fechaAcuseEntDoctos);
				cDAO.guardaObjeto(docSPCA);
			}			
			
			if(estatusCA == 4){				
				//Verifica si algun documento de la carta de adhesion se encuentra observada
				if(spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 0, 0, true).size()==0){
					//Actualizar estatus = 5 a la carta adhesion
					ca.setEstatus(5);
					
				}
				fechaAcuseResp = null;
				fechaDocResp = null;
			}
			//Guarda la carta de adhesion
			if(tipoConstancia!=0){
				ca.setTipoConstancia(tipoConstancia);
			}
			cDAO.guardaObjeto(ca);
			
			idPrograma=ca.getIdPrograma();
			verCartaAdehsionAsignadas();
			if(alcanceDocumentacion){
				alcanceDocumentacion = false;
			}
			if(sustituirArchivo){
				//Sustituir los archivos del oficio de observaciones
				if(!sustituirArchivoObservaciones()){
					return SUCCESS;
				}	
				
				//Sustituir los archivos de respuesta
				if(!sustituirArchivoRespuesta()){
					return SUCCESS;
				}
				sustituirArchivo = false;
			}
			capDocumentacion();
			msjOk = "Se guardó satisfactoriamente el registro";
			deshabilitaAccion = true;
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en base de datos en registraCapDocumentacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado a, favor de reportar al administrador "+e.getMessage());
	    	errorSistema = 1;
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCapDocumentacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado b, favor de reportar al administrador "+e.getMessage());
			errorSistema = 1;
		}
		return SUCCESS;		
	}

	private boolean sustituirArchivoRespuesta() {
		boolean sustitucionARSatisfactoria = false;
		try{
			if (idOficioRespuestaA != null){
				for(int i=0, j=0; i<idOficioRespuestaA.length; i++){
					if(idOficioRespuestaA[i]!=null){					
						if(docRespA[j]!=null){					
							//Verifica que el archivo no exceda de los 3MB
							if(Utilerias.verificarTamanioArchivo(docRespA[j])){
								addActionError("Lo sentimos el archivo "+docRespAFileName[j]+" no deben exceder de 3 MegaBytes");
								return false; 
							}
							
							//Recupera el registro de oficio de respuesta
							OficioRespuestaSolicitudPago orsp = spDAO.consultaOficioRespuestaSolicitudPago(folioCartaAdhesion, idOficioRespuestaA[i]).get(0);
							
												
							File f = new File(orsp.getRutaDocRespuesta()+orsp.getNomArchivoRespuesta());
							if(f.exists()){
								if(!f.delete()){
									AppLogger.error("errores","Error al eliminar: "+orsp.getNomArchivoRespuesta()+" al sustituir el archivo");
								}else{
									AppLogger.info("app","Se borro archivo: "+orsp.getNomArchivoRespuesta()+" al sustituir el archivo");
								}
							}
							//Cargar nuevo archivo
							String ext = docRespAFileName[j].toLowerCase().substring( docRespAFileName[j].lastIndexOf(".") );
							nombreArchivo = "resp"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Oficio de respuesta de observaciones
							Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, docRespA[j]);
							orsp.setRutaDocRespuesta(rutaCartaAdhesion);
							orsp.setNomArchivoRespuesta(nombreArchivo);
							cDAO.guardaObjeto(orsp);	
							j++;
						}
					}
				}
			}
			sustitucionARSatisfactoria = true;
		}catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en sustituirArchivoRespuesta  debido a: "+e.getMessage());
		}
		
		return sustitucionARSatisfactoria;
				
	}
		
	private boolean sustituirArchivoObservaciones(){
		boolean sustitucionAOSatisfactoria = false;
		try{
			
			if (idOficioObsSolPagoA != null){				
				for(int i=0, j=0; i < idOficioObsSolPagoA.length; i++){
					if(idOficioObsSolPagoA[i]!=null){						
						if(docObsA[j]!=null){			
							//Verifica que el archivo no exceda de los 3MB
							if(Utilerias.verificarTamanioArchivo(docObsA[j])){
								addActionError("Lo sentimos el archivo "+docObsAFileName[j]+" no deben exceder de 3 MegaBytes");
								return false; 
							}					
							//Recupera el registro de oficio de respuesta
							OficioObsSolicitudPago oosp = spDAO.consultaOficioObsSolicitudPago(idOficioObsSolPagoA[i]).get(0);
							
							if(oosp.getRutaDocObs()!= null && !oosp.getRutaDocObs().isEmpty()){
								File f = new File(oosp.getRutaDocObs());
								if(f.exists()){
									if(!f.delete()){
										AppLogger.error("errores","Error al eliminar: "+oosp.getRutaDocObs()+" al sustituir el archivo");
									}else{
										AppLogger.info("app","Se borro archivo: "+oosp.getRutaDocObs()+" al sustituir el archivo");
									}
								}
							}
							//Cargar nuevo archivo
							String ext = docObsAFileName[j].toLowerCase().substring( docObsAFileName[j].lastIndexOf(".") );
							nombreArchivo = "OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Oficio de observaciones
							Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, docObsA[j]);
							oosp.setRutaDocObs(rutaCartaAdhesion+nombreArchivo);
							cDAO.guardaObjeto(oosp);
							j++;
						}
					}
				}
			}
			sustitucionAOSatisfactoria = true;
		}catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en sustituirArchivoObservaciones  debido a: "+e.getMessage());

		}
		
		return sustitucionAOSatisfactoria;
				
	}

	private void cargarArchivos(int idExpediente, boolean band) throws Exception {
		if(idExpediente != 5 && idExpediente !=4 ){
			nombreArchivo = recuperaNomArchivoYCargaArchivo(idExpediente, rutaCartaAdhesion, band, true);
		}else if(idExpediente==4){
			if(!edoCuentaYaCapturado){
				nombreArchivo = recuperaNomArchivoYCargaArchivo(idExpediente, rutaCartaAdhesion, band, true);
			}else if(edoCuentaYaCapturado && !band){
				nombreArchivo = docSPCA.getRutaDocumento().substring(docSPCA.getRutaDocumento().lastIndexOf("/")+1);
			}
			
		}else if(idExpediente==5){
			if(!anexo32DyaCapturado){
				nombreArchivo = recuperaNomArchivoYCargaArchivo(idExpediente, rutaCartaAdhesion, band, true);
			}else if(anexo32DyaCapturado && !band){
				nombreArchivo = docSPCA.getRutaDocumento().substring(docSPCA.getRutaDocumento().lastIndexOf("/")+1);
			}
		}
			
	}
	private void setDocumentacionSPCartaAdhesion(int idExpediente, boolean band) throws Exception{
		File f1 = null, f2=null; 
		docSPCA = new DocumentacionSPCartaAdhesion();
		docSPCA.setIdExpediente(idExpediente);
		docSPCA.setFolioCartaAdhesion(folioCartaAdhesion);
		docSPCA.setFechaDocumento(fechaDocEntDoctos);
		docSPCA.setFechaAcuse(fechaAcuseEntDoctos);
		if(idExpediente==4){
			if(edoCuentaYaCapturado){
				docSPCA = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, idExpediente).get(0);
			}
		}						
		if(idExpediente==5){
			if(anexo32DyaCapturado){
				docSPCA = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, idExpediente).get(0);
			}
		}						
		if(idExpediente == 3){
			if(idCriterioPago == 1 || idCriterioPago == 3){
				//Guarda el Volumen Solicitado a Apoyar  
				docSPCA.setVolumen(volumen);
			}
		}
		// CAPTURA DE PERIODOS DE DICTAMEN DEL AUDITOR - INICIO		
		if(idExpediente == 7){ // Dictamen del Auditor
			docSPCA.setPeriodoInicialAuditor(fechaPeriodoInicialAuditor);
			docSPCA.setPeriodoFinalAuditor(fechaPeriodoFinalAuditor);
		}
		// CAPTURA DE PERIODOS DE DICTAMEN DEL AUDITOR - FIN		
		if(band){
			docSPCA.setObservacion(true); //con observacion							
			if(idExpediente == 4){
				//Renombra a archivo ya existente
				if(edoCuentaYaCapturado){
					f1 = new File(docSPCA.getRutaDocumento());
					nombreArchivo = docSPCA.getRutaDocumento().substring(docSPCA.getRutaDocumento().lastIndexOf("/")+1);
					nombreArchivo = "OBS"+nombreArchivo;
					f2 = new File(rutaCartaAdhesion+nombreArchivo);
					if(!f1.renameTo(f2)){
						AppLogger.error("errores","No se pudo renombrar el archivo  del expediente "+idExpediente+" en observaciones de documento" );
						addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
						errorSistema = 1;
					}
				}
			}
			if(idExpediente == 5){
				if(fechaExpedicion !=null){
					docSPCA.setFechaExpedicionAnexo(fechaExpedicion);
				}
				//Renombra a archivo ya existente
				if(anexo32DyaCapturado){
					f1 = new File(docSPCA.getRutaDocumento());
					nombreArchivo = docSPCA.getRutaDocumento().substring(docSPCA.getRutaDocumento().lastIndexOf("/")+1);
					nombreArchivo = "OBS"+nombreArchivo;
					
					f2 = new File(rutaCartaAdhesion+nombreArchivo);
					if(!f1.renameTo(f2)){
						AppLogger.error("errores","No se pudo renombrar el archivo  del expediente "+idExpediente+" en observaciones de documento" );
						addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
						errorSistema = 1;
						
					}
				} 
			}
		}else{							
			docSPCA.setObservacion(false);//sin observacion
			if(idExpediente == 5){
				if(!anexo32DyaCapturado){
					//Guarda los datos de la fecha de expedicion
					docSPCA.setFechaExpedicionAnexo(fechaExpedicion);
				}	
			}
		}
		
	}
	
	private void verificaEstadoDocumento(int idExpediente, boolean band, long idOficioObsSolPago,  boolean siCargoDocumento, String rutaDocumento) throws Exception{ 
		if(docSPCA.getObservacion()==null){
			docSPCA.setObservacion(false);
		}
		/*if(!docSPCA.getObservacion() && !band){ // 0,0 No estaba observado y tampoco se marco como observado
			//no hace nada, solo para considerarlo
		}else*/ if(!docSPCA.getObservacion() && band){//0,1 No estaba observado y ahora si se marco como observado 
			if(siCargoDocumento){
				//Subir archivo a servidor
				cargarArchivos(idExpediente, band);
				rutaDocumento = rutaCartaAdhesion+nombreArchivo;
				docSPCA.setRutaDocumento(rutaDocumento);
			}else{				  
				//Renombrar Archivo como observado
				if(rutaDocumento !=null && rutaDocumento!=""){
					File f1 = null, f2=null; 
					//Renombrar el documento
					f1 = new File(rutaDocumento);
					nombreArchivo = rutaDocumento.substring(rutaDocumento.lastIndexOf("/")+1);
					nombreArchivo = "OBS"+nombreArchivo;
					//nombreArchivo = recuperaNomArchivoYCargaArchivo(epv.getIdExpediente(), rutaCartaAdhesion, true, false);
					f2 = new File(rutaCartaAdhesion+nombreArchivo);
					if(!f1.renameTo(f2)){
						AppLogger.error("errores","No se pudo renombrar el archivo  del expediente "+idExpediente );
						addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
						errorSistema = 1;
						return;
					}
					rutaDocumento = rutaCartaAdhesion+nombreArchivo;
				}
				docSPCA.setRutaDocumento(rutaDocumento);			
			}			
			guardarObservados(idOficioObsSolPago, rutaDocumento);
			docSPCA.setObservacion(true);
			cDAO.guardaObjeto(docSPCA);
		}else if(docSPCA.getObservacion() && !band){ //1,0 Estaba observado y ahora se marco como no observado
			docSPCA.setObservacion(false); //Actualizar a no observado
			//Renombrar archivo a no observado
			File f1 = null, f2=null; 
			if(rutaDocumento !=null && rutaDocumento!=""){
				//Renombrar el documento
				f1 = new File(rutaDocumento);
				nombreArchivo = rutaDocumento.substring(rutaDocumento.lastIndexOf("/")+1);
				System.out.println("NOMBRE ARCHIVO "+nombreArchivo);
				//nombreArchivo = "OBS"+nombreArchivo;
				nombreArchivo = nombreArchivo.replace("OBS", "");
				//nombreArchivo = recuperaNomArchivoYCargaArchivo(epv.getIdExpediente(), rutaCartaAdhesion, true, false);
				f2 = new File(rutaCartaAdhesion+nombreArchivo);
				if(!f1.renameTo(f2)){
					AppLogger.error("errores","No se pudo renombrar el archivo  del expediente "+idExpediente );
					addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
					errorSistema = 1;
					return;
				}
				rutaDocumento = rutaCartaAdhesion+nombreArchivo;
			}
			//Si existe documento observado
			/*if(docSPCA.getRutaDocumento()!=null && !docSPCA.getRutaDocumento().isEmpty()){
				//Borrar el expediente
				File f = new File(docSPCA.getRutaDocumento());
				if(f.exists()){
					if(!f.delete()){
						AppLogger.error("errores","Error al eliminar: "+docSPCA.getRutaDocumento());
					}else{
						AppLogger.info("app","Se borro archivo: "+docSPCA.getRutaDocumento());
					}
				}	
				
				docSPCA.setRutaDocumento(null);
			}*/	
			
			docSPCA.setRutaDocumento(rutaDocumento);
			cDAO.guardaObjeto(docSPCA);
			//Borrar de la tabla de observados
			spDAO.borraDocumentosObservados(docSPCA.getIdExpSPCartaAdhesion());
		}else if(docSPCA.getObservacion() && band){ //1,1 Estaba observado y se marco observado
			if(!siCargoDocumento && !habilitarOficioObs){ // 0, 0 no cargo documento y no habilito oficio de observaciones
				System.out.println("NO HACE NADA");
			}else if(!siCargoDocumento && habilitarOficioObs){ // 0, 1 no cargo documento y habilito oficio de observaciones
				//Actualizar registro en observados
				ObservacionDocumentacionSP obsDoc = spDAO.consultaObservacionDocumentacion(docSPCA.getIdExpSPCartaAdhesion()).get(0); //Previamente debe estar registrado el documento observado
				obsDoc.setRutaDocumento(rutaDocumento);	
				obsDoc.setIdOficioObsSP(idOficioObsSolPago);
				cDAO.guardaObjeto(obsDoc);
			}else if(siCargoDocumento && !habilitarOficioObs){// 1, 0 si cargo documento y no habilito oficio de observaciones 
				cargarArchivos(idExpediente, band);
				rutaDocumento = rutaCartaAdhesion+nombreArchivo;
				docSPCA.setRutaDocumento(rutaDocumento);
				cDAO.guardaObjeto(docSPCA);
				//Actualizar registro en observados
				ObservacionDocumentacionSP obsDoc = spDAO.consultaObservacionDocumentacion(docSPCA.getIdExpSPCartaAdhesion()).get(0); //Previamente debe estar registrado el documento observado
				obsDoc.setRutaDocumento(rutaDocumento);	
				cDAO.guardaObjeto(obsDoc);				
			}else if(siCargoDocumento && habilitarOficioObs){ // 1, 1 si cargo documento y si habilito oficio de observaciones
				cargarArchivos(idExpediente, band);
				rutaDocumento = rutaCartaAdhesion+nombreArchivo;
				docSPCA.setRutaDocumento(rutaDocumento);
				cDAO.guardaObjeto(docSPCA);
				//Actualizar registro en observados
				ObservacionDocumentacionSP obsDoc = spDAO.consultaObservacionDocumentacion(docSPCA.getIdExpSPCartaAdhesion()).get(0); //Previamente debe estar registrado el documento observado
				obsDoc.setRutaDocumento(rutaDocumento);	
				obsDoc.setIdOficioObsSP(idOficioObsSolPago);
				cDAO.guardaObjeto(obsDoc);
			}				
		}
	}
	
	private void guardarObservados(long idOficioObsSolPago, String rutaDocumento){
		//Guarda en la tabla de observaciones expedientes nuevo registro
		ObservacionDocumentacionSP obsDoc = new ObservacionDocumentacionSP();
		obsDoc.setIdExpSPCA(docSPCA.getIdExpSPCartaAdhesion());
		obsDoc.setFolioCartaAdhesion(folioCartaAdhesion);
		if(idOficioObsSolPago!=-1){
			obsDoc.setIdOficioObsSP(idOficioObsSolPago);
		}		
		obsDoc.setRutaDocumento(rutaDocumento);
		cDAO.guardaObjeto(obsDoc);
	}
	
	
	private boolean verificarSicargoDocumento(int idExpediente) {
		boolean siCargoDocto = false;	
		if(idExpediente == 1){
			if(doc1!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 2){
			if(doc1!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 2){
			if(doc2!=null){
				siCargoDocto = true;
			}	
		}else if(idExpediente == 3){
			if(doc3!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 4){
			if(doc4!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 5){
			if(doc5!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 6){
			if(doc6!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 7){
			if(doc7!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 8){
			if(doc8!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 9){
			if(doc9!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 10){
			if(doc10!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 11){
			if(doc11!=null){
				siCargoDocto = true;
			}
		}else if(idExpediente == 12){
			if(doc12!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 13){
			if(doc13!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 14){
			if(doc14!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 15){
			if(doc15!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 32){
			if(doc32!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 33){
			if(doc33!=null){
				siCargoDocto = true;
			}
			
		}else if(idExpediente == 34){
			if(doc34!=null){
				siCargoDocto = true;
			}
			
		}		

		
		
		return siCargoDocto;
		
	}
	public String registraCapFianza(){
		try{
			boolean insertar = true;
			String rutaCartaAdhesion = getRecuperaRutaCarta();
			//Recupera expedientes configurados en la inicializacion 
			lstExpedientesProgramasV = spDAO.consultaExpedientesProgramasV(0, idPrograma, "'F','DSPYF'");
			//Valida el tamaño de los archivos a cargar
			validarDocumentos();
			if(errorSistema == 1){
				addActionError("Los archivos a cargar no deben exceder de 3MB, por favor verifique");
				return SUCCESS;
			}			
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			if(registrar == 0){
				ca.setFianza(true);
			}
			for(ExpedientesProgramasV epv: lstExpedientesProgramasV){
				//Verifica si el expediente 4 o 5 no ha sido cargada previamente "Estado de Cuenta o Anexo-32D", para no cargarlo
				if(epv.getTipo().equals("DSPYF")){
					if(spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, epv.getIdExpediente()).size()>0){
						insertar = false;
					}
				}
				if(insertar){
					DocumentacionSPCartaAdhesion docSPCA = new DocumentacionSPCartaAdhesion();
					if(epv.getIdExpediente() == 5){
						//Guarda la fecha de expedicion
						docSPCA.setFechaExpedicionAnexo(fechaExpedicion);
						
					}
					if(epv.getIdExpediente() == 32){
						//Guarda la fecha de expedicion
						docSPCA.setVolumen(volumen);
						
					}
					docSPCA.setIdExpediente(epv.getIdExpediente());
					docSPCA.setFolioCartaAdhesion(folioCartaAdhesion);
					try{
						nombreArchivo = recuperaNomArchivoYCargaArchivo(epv.getIdExpediente(), rutaCartaAdhesion,false, true);
					}catch(Exception e){
						errorSistema = 1;
						return SUCCESS;
					}
					docSPCA.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
					cDAO.guardaObjeto(docSPCA);
					
				}
				insertar = true;				
			}
			capFianza();
			registrar = 2;
			msjOk = "Se guardó satisfactoriamente el registro";
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraCapFianza  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    	errorSistema = 1;
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCapFianza  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			errorSistema = 1;
		}
		return SUCCESS;
		
	}

	
	private void validarDocumentos() {
		//for(ExpedientesProgramasV epv: lstExpedientesProgramasV){
			if(doc1!=null){
				verificarTamanioArchivo(doc1);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc2!=null){
				verificarTamanioArchivo(doc2);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc3 != null){
				verificarTamanioArchivo(doc3);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc4 != null){
				verificarTamanioArchivo(doc4);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc5 != null){
				verificarTamanioArchivo(doc5);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc6 != null){
				verificarTamanioArchivo(doc6);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc7 != null){
				verificarTamanioArchivo(doc7);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc8 != null){
				verificarTamanioArchivo(doc8);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc9 != null){
				verificarTamanioArchivo(doc9);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc10 != null){
				verificarTamanioArchivo(doc10);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc11 != null){
				verificarTamanioArchivo(doc11);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc12 != null){
				verificarTamanioArchivo(doc12);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc13 != null){
				verificarTamanioArchivo(doc13);
				if(errorSistema == 1){
					return;
				}
			}	
			if(doc15 != null){
				verificarTamanioArchivo(doc15);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc32 != null){
				verificarTamanioArchivo(doc32);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc33 != null){
				verificarTamanioArchivo(doc33);
				if(errorSistema == 1){
					return;
				}
			}
			if(doc34 != null){
				verificarTamanioArchivo(doc34);
				if(errorSistema == 1){
					return;
				}
			}
			
		//}
		
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
	
	private String recuperaNomArchivoYCargaArchivo(long idExpediente, String rutaCartaAdhesion, boolean observacion, boolean cargarArchivo){
		  
		if(idExpediente == 1){
			ext = doc1FileName.toLowerCase().substring(doc1FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc1);	
			}
			
		}else if(idExpediente == 2){
			ext = doc2FileName.toLowerCase().substring(doc2FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc2);	
			}
			
		}else if(idExpediente == 3){
			ext = doc3FileName.toLowerCase().substring(doc3FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}	
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc3);	
			}
			
		}else if(idExpediente == 4){
			ext = doc4FileName.toLowerCase().substring(doc4FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}	
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc4);	
			}
			
		}else if(idExpediente == 5){
			ext = doc5FileName.toLowerCase().substring(doc5FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc5);
			}
			
		}else if(idExpediente == 6){
			ext = doc6FileName.toLowerCase().substring(doc6FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc6);
			}
		}else if(idExpediente == 7){
			ext = doc7FileName.toLowerCase().substring(doc7FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc7);
			}
		}else if(idExpediente == 8){
			ext = doc8FileName.toLowerCase().substring(doc8FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc8);	
			}
			
		}else if(idExpediente == 9){
			ext = doc9FileName.toLowerCase().substring(doc9FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc9);	
			}
			
		}else if(idExpediente == 10){
			ext = doc10FileName.toLowerCase().substring(doc10FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc10);	
			}
			
		}else if(idExpediente == 11){
			ext = doc11FileName.toLowerCase().substring(doc11FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc11);
			}
			
		}else if(idExpediente == 12){
			ext = doc12FileName.toLowerCase().substring(doc12FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc12);	
			}
			
		}else if(idExpediente == 13){
			ext = doc13FileName.toLowerCase().substring(doc13FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc13);	
			}
			
		}else if(idExpediente == 14){
			ext = doc14FileName.toLowerCase().substring(doc14FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc14);	
			}
			
		}else if(idExpediente == 15){
			ext = doc15FileName.toLowerCase().substring(doc15FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc15);	
			}
			
		}else if(idExpediente == 32){
			ext = doc32FileName.toLowerCase().substring(doc32FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc32);	
			}
			
		}else if(idExpediente == 33){
			ext = doc33FileName.toLowerCase().substring(doc33FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc33);	
			}
			
		}else if(idExpediente == 34){
			ext = doc34FileName.toLowerCase().substring(doc34FileName.lastIndexOf(".") );
			if(observacion){
				nombreArchivo = idExpediente+"OBS"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}else{
				nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			}
			if(cargarArchivo){
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc34);	
			}
			
		}		
		return nombreArchivo;
		
	}
	
	
	private boolean verificaSiHayObservacionExpediente(int idExpediente) {
		boolean band = false;
		if(capObsExpediente!=null){
			for(int i=0; i < capObsExpediente.length; i++){
				if(idExpediente == capObsExpediente[i]){
					band = true;
				}
			}	
		}
		
		return band;
		
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
		
	
	public String recuperaOficioObservacion(){
		return SUCCESS;		
	}
	
	public String capAsignacionVolumenSolPago(){
		try{			
			
			lstProgramas = cDAO.consultaPrograma(0);
			lstEspecialista = cDAO.consultaEspecialista(0);
			//lstAsigCA =  iDAO.consultaAsignacionCartasAdhesionV(0, 0);
			idCriterioPago = 1;
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getCause());
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}
	
	public String habilitarOficioObservacion(){
		try{	
			msjError ="";
			idExpedientesRequeridos ="";
			//Consigue los datos de la Carta Adhesion
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);				
			List<Expediente> lstExpediente = spDAO.consultaCAExpedienteCompleto(folioCartaAdhesion, ca.getIdPrograma());
			if(lstExpediente.size()>0){
				setErrorExpedienteCompleto(true);
				StringBuilder sb= new StringBuilder();	
				for(Expediente e: lstExpediente){					
					sb.append(e.getIdExpediente()).append(",");
				}
				idExpedientesRequeridos = sb.deleteCharAt(sb.length()-1).toString(); //Todos id de los expedientes configurados en la inicializacion		
			}			
			
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en habilitarOficioObservacion  debido a: "+e.getCause());
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en habilitarOficioObservacion  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}
	
	
	public String recuperaDocRequeridos(){
		try{	
			idExpedientesRequeridos ="";
			//Consigue los datos de la Carta Adhesion
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);				
			List<Expediente> lstExpediente = spDAO.consultaCAExpedienteCompleto(folioCartaAdhesion, ca.getIdPrograma());
			if(lstExpediente.size()>0){
				StringBuilder sb= new StringBuilder();	
				for(Expediente e: lstExpediente){					
					//idExpedientesRequeridos+=e.getIdExpediente()+",";
					sb.append(e.getIdExpediente()).append(",");
				}	
				idExpedientesRequeridos = sb.deleteCharAt(sb.length()-1).toString(); //Todos id de los expedientes configurados en la inicializacion
			}		
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en recuperaDocRequeridos  debido a: "+e.getCause());
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaDocRequeridos  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}

	public void recuperaDocRequeridosFianza(){
		try{	
			idExpedientesRequeridos ="";
			//Consigue los datos de la Carta Adhesion
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);				
			List<Expediente> lstExpediente = spDAO.consultaCAExpedienteFianza(folioCartaAdhesion, ca.getIdPrograma());
			if(lstExpediente.size()>0){
				StringBuilder sb= new StringBuilder();	
				for(Expediente e: lstExpediente){					
					//idExpedientesRequeridos+=e.getIdExpediente()+",";
					sb.append(e.getIdExpediente()).append(",");
				}	
				idExpedientesRequeridos = sb.deleteCharAt(sb.length()-1).toString(); //Todos id de los expedientes configurados en la inicializacion				
			}		
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en recuperaDocRequeridosFianza  debido a: "+e.getCause());
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaDocRequeridosFianza  debido a: "+e.getMessage());
		}
//		return SUCCESS;		
	}

	public String cierreExpedienteCA(){
		//Cambiar el estatus de la carta de adhesion a 9 "EXPEDIENTE CERRADO"
		CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
		ca.setEstatus(9);
		ca.setExpedienteReabierto(true);
		cDAO.guardaObjeto(ca);				
		
		folioCartaAdhesion=null;
		verCartaAdehsionAsignadas();
		
		return SUCCESS;		
	}
	
	public String cierreExpedienteCARevocar(){
		//Cambiar el estatus de la carta de adhesion a 4 "DOCUMENTACION COMPLETA CON OBS"
		CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
		ca.setEstatus(5);
		cDAO.guardaObjeto(ca);				
		
		folioCartaAdhesion=null;
		verCartaAdehsionAsignadas();
		
		return SUCCESS;		
	}
	
	public String capturarFiniquito(){
		//Verifica que el finiquito este cargado
		CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
		try {
			rutaCartaAdhesion = getRecuperaRutaCarta();
			if(ca.getExpdienteFiniquitado()==true){
				rutaFiniquito = ca.getRutaDocFiniquito();
				fechaDocFin = ca.getFechaDocFiniquito();
				fechaAcuseFin = ca.getFechaAcuseFiniquito();				
			}
		} catch (JDBCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return SUCCESS;
	}
	public String registraFiniquito(){
		
		try {
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			rutaCartaAdhesion = getRecuperaRutaCarta();
			
			//Antes que nada, validar que archivo del documento del finiquito a cargar no exceda de 3MB
			if(docFin!=null){
				verificarTamanioArchivo(docFin);
				if(errorSistema == 1){
					addActionError("El documento del finiquito no debe exceder de 3MB, por favor verifique");
					return SUCCESS;
				}
			}
			if(fechaDocFin!=null){
				ca.setFechaDocFiniquito(fechaDocFin);	
			}
			if(fechaAcuseFin!=null){
				ca.setFechaAcuseFiniquito(fechaAcuseFin);
			}
			if(docFin!=null){
				ext = docFinFileName.toLowerCase().substring(docFinFileName.lastIndexOf("."));
				nombreArchivoFiniquito = "FINIQUITO"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Documento del Finiquito 
				Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivoFiniquito, docFin);
				ca.setRutaDocFiniquito(rutaCartaAdhesion+nombreArchivoFiniquito);
			}
			ca.setExpdienteFiniquitado(true);
			cDAO.guardaObjeto(ca);
			verCartaAdehsionAsignadas();
			
			msjOk = "Se guardó satisfactoriamente la información";
			
		} catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraFiniquito  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch (Exception e) {			
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraFiniquito  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
			
		return SUCCESS;
	}
	/****Archivos****/
	public File getDoc1() {
		return doc1;
	}
	public void setDoc1(File doc1) {
		this.doc1 = doc1;
	}
	
	public File getDoc2() {
		return doc2;
	}
	public void setDoc2(File doc2) {
		this.doc2 = doc2;
	}
	
	public InscripcionDAO getiDAO() {
		return iDAO;
	}
	public void setiDAO(InscripcionDAO iDAO) {
		this.iDAO = iDAO;
	}
	public String getDoc1FileName() {
		return doc1FileName;
	}
	public void setDoc1FileName(String doc1FileName) {
		this.doc1FileName = doc1FileName;
	}
	public String getDoc2FileName() {
		return doc2FileName;
	}
	public void setDoc2FileName(String doc2FileName) {
		this.doc2FileName = doc2FileName;
	}
	
	public File getDoc3() {
		return doc3;
	}
	public void setDoc3(File doc3) {
		this.doc3 = doc3;
	}
	public String getDoc3FileName() {
		return doc3FileName;
	}
	public void setDoc3FileName(String doc3FileName) {
		this.doc3FileName = doc3FileName;
	}

	public File getDoc4() {
		return doc4;
	}
	public void setDoc4(File doc4) {
		this.doc4 = doc4;
	}

	public String getDoc4FileName() {
		return doc4FileName;
	}
	public void setDoc4FileName(String doc4FileName) {
		this.doc4FileName = doc4FileName;
	}
	public File getDoc5() {
		return doc5;
	}
	public void setDoc5(File doc5) {
		this.doc5 = doc5;
	}
	public String getDoc5FileName() {
		return doc5FileName;
	}
	public void setDoc5FileName(String doc5FileName) {
		this.doc5FileName = doc5FileName;
	}
	public File getDoc6() {
		return doc6;
	}
	public void setDoc6(File doc6) {
		this.doc6 = doc6;
	}
	public String getDoc6FileName() {
		return doc6FileName;
	}
	public void setDoc6FileName(String doc6FileName) {
		this.doc6FileName = doc6FileName;
	}
	public File getDoc7() {
		return doc7;
	}
	public void setDoc7(File doc7) {
		this.doc7 = doc7;
	}
	public String getDoc7FileName() {
		return doc7FileName;
	}
	public void setDoc7FileName(String doc7FileName) {
		this.doc7FileName = doc7FileName;
	}
	public File getDoc8() {
		return doc8;
	}
	public void setDoc8(File doc8) {
		this.doc8 = doc8;
	}
	public String getDoc8FileName() {
		return doc8FileName;
	}
	public void setDoc8FileName(String doc8FileName) {
		this.doc8FileName = doc8FileName;
	}
	public File getDoc9() {
		return doc9;
	}
	public void setDoc9(File doc9) {
		this.doc9 = doc9;
	}
	public String getDoc9FileName() {
		return doc9FileName;
	}
	public void setDoc9FileName(String doc9FileName) {
		this.doc9FileName = doc9FileName;
	}
	public File getDoc10() {
		return doc10;
	}
	public void setDoc10(File doc10) {
		this.doc10 = doc10;
	}
	public String getDoc10FileName() {
		return doc10FileName;
	}
	public void setDoc10FileName(String doc10FileName) {
		this.doc10FileName = doc10FileName;
	}
	public File getDoc11() {
		return doc11;
	}
	public void setDoc11(File doc11) {
		this.doc11 = doc11;
	}
	public String getDoc11FileName() {
		return doc11FileName;
	}
	public void setDoc11FileName(String doc11FileName) {
		this.doc11FileName = doc11FileName;
	}
	public File getDoc12() {
		return doc12;
	}
	public void setDoc12(File doc12) {
		this.doc12 = doc12;
	}
	public String getDoc12FileName() {
		return doc12FileName;
	}
	public void setDoc12FileName(String doc12FileName) {
		this.doc12FileName = doc12FileName;
	}
	public File getDoc13() {
		return doc13;
	}
	public void setDoc13(File doc13) {
		this.doc13 = doc13;
	}
	public String getDoc13FileName() {
		return doc13FileName;
	}
	public void setDoc13FileName(String doc13FileName) {
		this.doc13FileName = doc13FileName;
	}
	public File getDoc14() {
		return doc14;
	}
	public void setDoc14(File doc14) {
		this.doc14 = doc14;
	}
	public String getDoc14FileName() {
		return doc14FileName;
	}
	public void setDoc14FileName(String doc14FileName) {
		this.doc14FileName = doc14FileName;
	}
	public File getDoc15() {
		return doc15;
	}
	public void setDoc15(File doc15) {
		this.doc15 = doc15;
	}
	public String getDoc15FileName() {
		return doc15FileName;
	}
	public void setDoc15FileName(String doc15FileName) {
		this.doc15FileName = doc15FileName;
	}
	
	public File getDoc32() {
		return doc32;
	}
	public void setDoc32(File doc32) {
		this.doc32 = doc32;
	}
	public String getDoc32FileName() {
		return doc32FileName;
	}
	public void setDoc32FileName(String doc32FileName) {
		this.doc32FileName = doc32FileName;
	}
	public File getDoc33() {
		return doc33;
	}
	public void setDoc33(File doc33) {
		this.doc33 = doc33;
	}
	public String getDoc33FileName() {
		return doc33FileName;
	}
	public void setDoc33FileName(String doc33FileName) {
		this.doc33FileName = doc33FileName;
	}
	public File getDoc34() {
		return doc34;
	}
	public void setDoc34(File doc34) {
		this.doc34 = doc34;
	}
	public String getDoc34FileName() {
		return doc34FileName;
	}
	public void setDoc34FileName(String doc34FileName) {
		this.doc34FileName = doc34FileName;
	}
	public File getDocObs() {
		return docObs;
	}
	public void setDocObs(File docObs) {
		this.docObs = docObs;
	}
	public String getDocObsFileName() {
		return docObsFileName;
	}
	public void setDocObsFileName(String docObsFileName) {
		this.docObsFileName = docObsFileName;
	}
	
	public Date getFechaDocOBS() {
		return fechaDocOBS;
	}
	public void setFechaDocOBS(Date fechaDocOBS) {
		this.fechaDocOBS = fechaDocOBS;
	}
	public Date getFechaAcuseOBS() {
		return fechaAcuseOBS;
	}
	public void setFechaAcuseOBS(Date fechaAcuseOBS) {
		this.fechaAcuseOBS = fechaAcuseOBS;
	}
	public String getNoOficioOBS() {
		return noOficioOBS;
	}
	public void setNoOficioOBS(String noOficioOBS) {
		this.noOficioOBS = noOficioOBS;
	}
	
	public boolean isAlcanceDocumentacion() {
		return alcanceDocumentacion;
	}
	public void setAlcanceDocumentacion(boolean alcanceDocumentacion) {
		this.alcanceDocumentacion = alcanceDocumentacion;
	}
		
	public boolean isNuevasObservaciones() {
		return nuevasObservaciones;
	}
	public void setNuevasObservaciones(boolean nuevasObservaciones) {
		this.nuevasObservaciones = nuevasObservaciones;
	}
	public boolean istCartaAdehsionHastaSP() {
		return tCartaAdehsionHastaSP;
	}
	public void settCartaAdehsionHastaSP(boolean tCartaAdehsionHastaSP) {
		this.tCartaAdehsionHastaSP = tCartaAdehsionHastaSP;
	}
	
	/***Variables de formulario**/	
	public int getTieneObservacion() {
		return tieneObservacion;
	}
	
	public void setTieneObservacion(int tieneObservacion) {
		this.tieneObservacion = tieneObservacion;
	}
	
	public int getTipoDocumentacion() {
		return tipoDocumentacion;
	}
	public void setTipoDocumentacion(int tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}
	public int getIdCriterioPago() {
		return idCriterioPago;
	}
	public void setIdCriterioPago(int idCriterioPago) {
		this.idCriterioPago = idCriterioPago;
	}	
	
	public int getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(int idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	public int getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(int documentacion) {
		this.documentacion = documentacion;
	}	
	
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	public Double getVolumenAutorizado() {
		return volumenAutorizado;
	}
	public void setVolumenAutorizado(Double volumenAutorizado) {
		this.volumenAutorizado = volumenAutorizado;
	}
	public Double getImporteAutorizado() {
		return importeAutorizado;
	}
	public void setImporteAutorizado(Double importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}
	
	public Date getFechaDocEntDoctos() {
		return fechaDocEntDoctos;
	}
	public void setFechaDocEntDoctos(Date fechaDocEntDoctos) {
		this.fechaDocEntDoctos = fechaDocEntDoctos;
	}
	public Date getFechaAcuseEntDoctos() {
		return fechaAcuseEntDoctos;
	}
	public void setFechaAcuseEntDoctos(Date fechaAcuseEntDoctos) {
		this.fechaAcuseEntDoctos = fechaAcuseEntDoctos;
	}
	public String getNoOficioED() {
		return noOficioED;
	}
	public void setNoOficioED(String noOficioED) {
		this.noOficioED = noOficioED;
	}
	
	public Integer[] getCapObsExpediente() {
		return capObsExpediente;
	}
	public void setCapObsExpediente(Integer[] capObsExpediente) {
		this.capObsExpediente = capObsExpediente;
	}
	public int getErrorSistema() {
		return errorSistema;
	}
	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
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
	
	public boolean isCapturarInfoDocumento() {
		return capturarInfoDocumento;
	}
	public void setCapturarInfoDocumento(boolean capturarInfoDocumento) {
		this.capturarInfoDocumento = capturarInfoDocumento;
	}

	public boolean isCapturarInfoFianza() {
		return capturarInfoFianza;
	}
	public void setCapturarInfoFianza(boolean capturarInfoFianza) {
		this.capturarInfoFianza = capturarInfoFianza;
	}
	
	public boolean isHabilitaNuevasObservaciones() {
		return habilitaNuevasObservaciones;
	}
	public void setHabilitaNuevasObservaciones(boolean habilitaNuevasObservaciones) {
		this.habilitaNuevasObservaciones = habilitaNuevasObservaciones;
	}
	public String getRutaCompleta() {
		return rutaCompleta;
	}
	public void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}
		
	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	
	
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	/***LISTAS**/
	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public List<Especialista> getLstEspecialista() {
		return lstEspecialista;
	}
	
	public void setLstEspecialista(List<Especialista> lstEspecialista) {
		this.lstEspecialista = lstEspecialista;
	}
	
	public List<AsignacionCartasAdhesionV> getLstAsigCA() {
		return lstAsigCA;
	}
	public void setLstAsigCA(List<AsignacionCartasAdhesionV> lstAsigCA) {
		this.lstAsigCA = lstAsigCA;
	}
	
	public List<AsignacionCAaEspecialistaV> getLstAsignacionCAaEspecialistaV() {
		return lstAsignacionCAaEspecialistaV;
	}
	public void setLstAsignacionCAaEspecialistaV(
			List<AsignacionCAaEspecialistaV> lstAsignacionCAaEspecialistaV) {
		this.lstAsignacionCAaEspecialistaV = lstAsignacionCAaEspecialistaV;
	}
	
	public List<ExpedientesProgramasV> getLstExpedientesProgramasV() {
		return lstExpedientesProgramasV;
	}
	public void setLstExpedientesProgramasV(
			List<ExpedientesProgramasV> lstExpedientesProgramasV) {
		this.lstExpedientesProgramasV = lstExpedientesProgramasV;
	}
	
	public List<DocumentacionSPCartaAdhesionV> getLstsExpedientesSPCartaAdhesionV() {
		return lstsExpedientesSPCartaAdhesionV;
	}
	public void setLstsExpedientesSPCartaAdhesionV(
			List<DocumentacionSPCartaAdhesionV> lstsExpedientesSPCartaAdhesionV) {
		this.lstsExpedientesSPCartaAdhesionV = lstsExpedientesSPCartaAdhesionV;
	}
	public List<OficioObsSolicitudPago> getLstOficioObsSolicitudPago() {
		return lstOficioObsSolicitudPago;
	}
	public void setLstOficioObsSolicitudPago(
			List<OficioObsSolicitudPago> lstOficioObsSolicitudPago) {
		this.lstOficioObsSolicitudPago = lstOficioObsSolicitudPago;
	}

	public List<AuditoresExternos> getLstAuditoresExternos() {
		return lstAuditoresExternos;
	}
	public void setLstAuditoresExternos(List<AuditoresExternos> lstAuditoresExternos) {
		this.lstAuditoresExternos = lstAuditoresExternos;
	}

	public boolean isAnexo32DyaCapturado() {
		return anexo32DyaCapturado;
	}
	public void setAnexo32DyaCapturado(boolean anexo32DyaCapturado) {
		this.anexo32DyaCapturado = anexo32DyaCapturado;
	}
	
	public boolean isEdoCuentaYaCapturado() {
		return edoCuentaYaCapturado;
	}
	public void setEdoCuentaYaCapturado(boolean edoCuentaYaCapturado) {
		this.edoCuentaYaCapturado = edoCuentaYaCapturado;
	}
	public boolean isDeshabilitaAccion() {
		return deshabilitaAccion;
	}
	public void setDeshabilitaAccion(boolean deshabilitaAccion) {
		this.deshabilitaAccion = deshabilitaAccion;
	}
	public List<RepresentanteCompradorV> getLstRepresentanteV() {
		return lstRepresentanteV;
	}
	public void setLstRepresentanteV(List<RepresentanteCompradorV> lstRepresentanteV) {
		this.lstRepresentanteV = lstRepresentanteV;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	public String getEstatusCartaAdhesion() {
		return estatusCartaAdhesion;
	}
	public void setEstatusCartaAdhesion(String estatusCartaAdhesion) {
		this.estatusCartaAdhesion = estatusCartaAdhesion;
	}
	public int getEstatusCA() {
		return estatusCA;
	}
	public void setEstatusCA(int estatusCA) {
		this.estatusCA = estatusCA;
	}
	
	public boolean isDocParcial() {
		return docParcial;
	}
	public void setDocParcial(boolean docParcial) {
		this.docParcial = docParcial;
	}
	
	public boolean isSustituirArchivo() {
		return sustituirArchivo;
	}
	public void setSustituirArchivo(boolean sustituirArchivo) {
		this.sustituirArchivo = sustituirArchivo;
	}
	public List<EstatusCartaAdhesion> getLstEstatusCartaAdhesion() {
		return lstEstatusCartaAdhesion;
	}
	public void setLstEstatusCartaAdhesion(List<EstatusCartaAdhesion> lstEstatusCartaAdhesion) {
		this.lstEstatusCartaAdhesion = lstEstatusCartaAdhesion;
	}
	public int getIdEstatusCA() {
		return idEstatusCA;
	}
	public void setIdEstatusCA(int idEstatusCA) {
		this.idEstatusCA = idEstatusCA;
	}
	public List<PrgEspecialistaNumCartasV> getLstPrgEspecialistaNumCartasVs() {
		return lstPrgEspecialistaNumCartasVs;
	}
	public void setLstPrgEspecialistaNumCartasVs(
			List<PrgEspecialistaNumCartasV> lstPrgEspecialistaNumCartasVs) {
		this.lstPrgEspecialistaNumCartasVs = lstPrgEspecialistaNumCartasVs;
	}
	public List<ProgramaNumCartasV> getLstNumCartasVs() {
		return lstNumCartasVs;
	}
	public void setLstNumCartasVs(List<ProgramaNumCartasV> lstNumCartasVs) {
		this.lstNumCartasVs = lstNumCartasVs;
	}
	public List<OficioRespuestaSolicitudPago> getLstOficioRespuestaSP() {
		return lstOficioRespuestaSP;
	}
	public void setLstOficioRespuestaSP(List<OficioRespuestaSolicitudPago> lstOficioRespuestaSP) {
		this.lstOficioRespuestaSP = lstOficioRespuestaSP;
	}
	
	public Date getFechaDocResp() {
		return fechaDocResp;
	}
	public void setFechaDocResp(Date fechaDocResp) {
		this.fechaDocResp = fechaDocResp;
	}
	/**
	 * @return the fechaAcuseResp
	 */
	public Date getFechaAcuseResp() {
		return fechaAcuseResp;
	}
	/**
	 * @param fechaAcuseResp the fechaAcuseResp to set
	 */
	public void setFechaAcuseResp(Date fechaAcuseResp) {
		this.fechaAcuseResp = fechaAcuseResp;
	}
	/**
	 * @return the docResp
	 */
	public File getDocResp() {
		return docResp;
	}
	/**
	 * @param docResp the docResp to set
	 */
	public void setDocResp(File docResp) {
		this.docResp = docResp;
	}
	/**
	 * @return the docRespFileName
	 */
	public String getDocRespFileName() {
		return docRespFileName;
	}
	/**
	 * @param docRespFileName the docRespFileName to set
	 */
	public void setDocRespFileName(String docRespFileName) {
		this.docRespFileName = docRespFileName;
	}
	/**
	 * @return the doctosObservados
	 */
	public int getDoctosObservados() {
		return doctosObservados;
	}
	/**
	 * @param doctosObservados the doctosObservados to set
	 */
	public void setDoctosObservados(int doctosObservados) {
		this.doctosObservados = doctosObservados;
	}
	/**
	 * @return the participante
	 */
	public Comprador getParticipante() {
		return participante;
	}
	/**
	 * @param participante the participante to set
	 */
	public void setParticipante(Comprador participante) {
		this.participante = participante;
	}	
	
	public List<AuditorSolicitudPagoV> getLstAuditorSolPagoV() {
		return lstAuditorSolPagoV;
	}
	public void setLstAuditorSolPagoV(List<AuditorSolicitudPagoV> lstAuditorSolPagoV) {
		this.lstAuditorSolPagoV = lstAuditorSolPagoV;
	}
	public Date getFechaRecepcionCA() {
		return fechaRecepcionCA;
	}
	public void setFechaRecepcionCA(Date fechaRecepcionCA) {
		this.fechaRecepcionCA = fechaRecepcionCA;
	}
	public Date getFechaFirmaCA() {
		return fechaFirmaCA;
	}
	public void setFechaFirmaCA(Date fechaFirmaCA) {
		this.fechaFirmaCA = fechaFirmaCA;
	}
	public boolean isHabilitarOficioObs() {
		return habilitarOficioObs;
	}
	public void setHabilitarOficioObs(boolean habilitarOficioObs) {
		this.habilitarOficioObs = habilitarOficioObs;
	}
	public boolean isErrorExpedienteCompleto() {
		return errorExpedienteCompleto;
	}
	public void setErrorExpedienteCompleto(boolean errorExpedienteCompleto) {
		this.errorExpedienteCompleto = errorExpedienteCompleto;
	}
	public String getMsjError() {
		return msjError;
	}
	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	public String getIdExpedientesRequeridos() {
		return idExpedientesRequeridos;
	}
	public void setIdExpedientesRequeridos(String idExpedientesRequeridos) {
		this.idExpedientesRequeridos = idExpedientesRequeridos;
	}
	public boolean isDoctosSinObservacion() {
		return doctosSinObservacion;
	}
	public void setDoctosSinObservacion(boolean doctosSinObservacion) {
		this.doctosSinObservacion = doctosSinObservacion;
	}
	public String getIdExpedientesObservados() {
		return idExpedientesObservados;
	}
	public void setIdExpedientesObservados(String idExpedientesObservados) {
		this.idExpedientesObservados = idExpedientesObservados;
	}
	public String getRutaDocObs() {
		return rutaDocObs;
	}
	public void setRutaDocObs(String rutaDocObs) {
		this.rutaDocObs = rutaDocObs;
	}
	public String getIdExpedientesTotal() {
		return idExpedientesTotal;
	}
	public void setIdExpedientesTotal(String idExpedientesTotal) {
		this.idExpedientesTotal = idExpedientesTotal;
	}
	public void setHabilitaAccionSP(int habilitaAccionSP) {
		this.habilitaAccionSP = habilitaAccionSP;
	}
	public int getHabilitaAccionSP() {
		return habilitaAccionSP;
	}
	public boolean isDeshabilitaAlcanceDocumentacion() {
		return deshabilitaAlcanceDocumentacion;
	}
	public void setDeshabilitaAlcanceDocumentacion(
			boolean deshabilitaAlcanceDocumentacion) {
		this.deshabilitaAlcanceDocumentacion = deshabilitaAlcanceDocumentacion;
	}
	public int getCertDepositoOAlmacenamiento() {
		return certDepositoOAlmacenamiento;
	}
	public void setCertDepositoOAlmacenamiento(int certDepositoOAlmacenamiento) {
		this.certDepositoOAlmacenamiento = certDepositoOAlmacenamiento;
	}
	public int getTipoConstancia() {
		return tipoConstancia;
	}
	public void setTipoConstancia(int tipoConstancia) {
		this.tipoConstancia = tipoConstancia;
	}
	
	public String getBusParticipante() {
		return busParticipante;
	}
	public void setBusParticipante(String busParticipante) {
		this.busParticipante = busParticipante.toUpperCase();
	}
	
	public Double getVolumenOriginal() {
		return volumenOriginal;
	}
	public void setVolumenOriginal(Double volumenOriginal) {
		this.volumenOriginal = volumenOriginal;
	}
	public Double getVolumenComplemento() {
		return volumenComplemento;
	}
	public void setVolumenComplemento(Double volumenComplemento) {
		this.volumenComplemento = volumenComplemento;
	}

	public List<PagosV> getLstPagosV() {
		return lstPagosV;
	}

	public void setLstPagosV(List<PagosV> lstPagosV) {
		this.lstPagosV = lstPagosV;
	}

	public long getTotalCAFirmadas() {
		return totalCAFirmadas;
	}

	public void setTotalCAFirmadas(long totalCAFirmadas) {
		this.totalCAFirmadas = totalCAFirmadas;
	}

	public long getTotalCAEnRevision() {
		return totalCAEnRevision;
	}

	public void setTotalCAEnRevision(long totalCAEnRevision) {
		this.totalCAEnRevision = totalCAEnRevision;
	}

	public long getTotalCACerradas() {
		return totalCACerradas;
	}

	public void setTotalCACerradas(long totalCACerradas) {
		this.totalCACerradas = totalCACerradas;
	}	
	
	public Double getTotalVolSusceptible() {
		return totalVolSusceptible;
	}

	public void setTotalVolSusceptible(Double totalVolSusceptible) {
		this.totalVolSusceptible = totalVolSusceptible;
	}

	public Double getTotalImpSusceptible() {
		return totalImpSusceptible;
	}

	public void setTotalImpSusceptible(Double totalImpSusceptible) {
		this.totalImpSusceptible = totalImpSusceptible;
	}

	public double getTotalVolEnRevision() {
		return totalVolEnRevision;
	}

	public void setTotalVolEnRevision(double totalVolEnRevision) {
		this.totalVolEnRevision = totalVolEnRevision;
	}

	public double getTotalImpEnRevision() {
		return totalImpEnRevision;
	}

	public void setTotalImpEnRevision(double totalImpEnRevision) {
		this.totalImpEnRevision = totalImpEnRevision;
	}

	public double getTotalVolEnTramPago() {
		return totalVolEnTramPago;
	}

	public void setTotalVolEnTramPago(double totalVolEnTramPago) {
		this.totalVolEnTramPago = totalVolEnTramPago;
	}

	public double getTotalImpEnTramPago() {
		return totalImpEnTramPago;
	}

	public void setTotalImpEnTramPago(double totalImpEnTramPago) {
		this.totalImpEnTramPago = totalImpEnTramPago;
	}

	public double getTotalVolPago() {
		return totalVolPago;
	}

	public void setTotalVolPago(double totalVolPago) {
		this.totalVolPago = totalVolPago;
	}

	public double getTotalImpPago() {
		return totalImpPago;
	}

	public void setTotalImpPago(double totalImpPago) {
		this.totalImpPago = totalImpPago;
	}

	public File getDocFin() {
		return docFin;
	}

	public void setDocFin(File docFin) {
		this.docFin = docFin;
	}

	public String getDocFinFileName() {
		return docFinFileName;
	}

	public void setDocFinFileName(String docFinFileName) {
		this.docFinFileName = docFinFileName;
	}
	
	public Date getFechaDocFin() {
		return fechaDocFin;
	}

	public void setFechaDocFin(Date fechaDocFin) {
		this.fechaDocFin = fechaDocFin;
	}

	public Date getFechaAcuseFin() {
		return fechaAcuseFin;
	}

	public void setFechaAcuseFin(Date fechaAcuseFin) {
		this.fechaAcuseFin = fechaAcuseFin;
	}

	public String getRutaFiniquito() {
		return rutaFiniquito;
	}

	public void setRutaFiniquito(String rutaFiniquito) {
		this.rutaFiniquito = rutaFiniquito;
	}

	public boolean isExpedienteFiniquitado() {
		return expedienteFiniquitado;
	}

	public void setExpedienteFiniquitado(boolean expedienteFiniquitado) {
		this.expedienteFiniquitado = expedienteFiniquitado;
	}

	public int getTipoVistaFiniquito() {
		return tipoVistaFiniquito;
	}

	public void setTipoVistaFiniquito(int tipoVistaFiniquito) {
		this.tipoVistaFiniquito = tipoVistaFiniquito;
	}

	public File[] getDocRespA() {
		return docRespA;
	}

	public void setDocRespA(File[] docRespA) {
		this.docRespA = docRespA;
	}

	public String[] getDocRespAFileName() {
		return docRespAFileName;
	}

	public void setDocRespAFileName(String[] docRespAFileName) {
		this.docRespAFileName = docRespAFileName;
	}

	public File[] getDocObsA() {
		return docObsA;
	}

	public void setDocObsA(File[] docObsA) {
		this.docObsA = docObsA;
	}

	public String[] getDocObsAFileName() {
		return docObsAFileName;
	}

	public void setDocObsAFileName(String[] docObsAFileName) {
		this.docObsAFileName = docObsAFileName;
	}

	public Integer[] getIdOficioRespuestaA() {
		return idOficioRespuestaA;
	}

	public void setIdOficioRespuestaA(Integer[] idOficioRespuestaA) {
		this.idOficioRespuestaA = idOficioRespuestaA;
	}

	public Integer[] getIdOficioObsSolPagoA() {
		return idOficioObsSolPagoA;
	}

	public void setIdOficioObsSolPagoA(Integer[] idOficioObsSolPagoA) {
		this.idOficioObsSolPagoA = idOficioObsSolPagoA;
	}

	public Long getIdSI() {
		return idSI;
	}

	public void setIdSI(Long idSI) {
		this.idSI = idSI;
	}

	public Double getTotalVolumenCertificados() {
		return totalVolumenCertificados;
	}

	public void setTotalVolumenCertificados(Double totalVolumenCertificados) {
		this.totalVolumenCertificados = totalVolumenCertificados;
	}

	public Double getTotalVolumenConstancia() {
		return totalVolumenConstancia;
	}

	public void setTotalVolumenConstancia(Double totalVolumenConstancia) {

		this.totalVolumenConstancia = totalVolumenConstancia;
	}

	public String getArchivoRelacionCompras() {
		return archivoRelacionCompras;
	}

	public void setArchivoRelacionCompras(String archivoRelacionCompras) {
		this.archivoRelacionCompras = archivoRelacionCompras;
	}

	public boolean isReporteCruce() {
		return reporteCruce;
	}

	public void setReporteCruce(boolean reporteCruce) {
		this.reporteCruce = reporteCruce;
	}

	public Date getFechaPeriodoInicialAuditor() {
		return fechaPeriodoInicialAuditor;
	}

	public void setFechaPeriodoInicialAuditor(Date fechaPeriodoInicialAuditor) {
		this.fechaPeriodoInicialAuditor = fechaPeriodoInicialAuditor;
	}

	public Date getFechaPeriodoFinalAuditor() {
		return fechaPeriodoFinalAuditor;
	}

	public void setFechaPeriodoFinalAuditor(Date fechaPeriodoFinalAuditor) {
		this.fechaPeriodoFinalAuditor = fechaPeriodoFinalAuditor;
	}

	public String getEspecialista() {
		return especialista;
	}

	public void setEspecialista(String especialista) {
		this.especialista = especialista;
	}

	
	public Anexo32D getAnexo32d() {
		return anexo32d;
	}
	

	public void setAnexo32d(Anexo32D anexo32d) {
		this.anexo32d = anexo32d;
	}
}
