package mx.gob.comer.sipc.action.inscripcion;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.AuditoresExternos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.CuotasEsquema;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.InicializacionEsquema;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.ProgramaComprador;
import mx.gob.comer.sipc.domain.transaccionales.AlcanceSolicitudInscripcion;
import mx.gob.comer.sipc.domain.transaccionales.AsignacionCartasAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.CultivoVariedadEsquema;
import mx.gob.comer.sipc.domain.transaccionales.DetalleAsignacionCartasAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.SolicitudInscripcion;
import mx.gob.comer.sipc.domain.catalogos.Componente;
import mx.gob.comer.sipc.domain.catalogos.Variedad;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.AcuseCartaAdhesion;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.CiclosProgramasV;
import mx.gob.comer.sipc.vistas.domain.CompradorExpedientesV;
import mx.gob.comer.sipc.vistas.domain.CuotasEsquemaV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCultivo;
import mx.gob.comer.sipc.vistas.domain.ProgramasV;
import mx.gob.comer.sipc.vistas.domain.RepresentanteCompradorV;
import mx.gob.comer.sipc.vistas.domain.SolicitudInscripcionV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException; 

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class InscripcionAction extends ActionSupport implements SessionAware, ServletContextAware{

	private Map<String, Object> session;
	private ServletContext context;
	private int idArea;
	//Datos del formulario
	private int idPrograma;
	private int idCicloPrograma;
	private int idComprador;
	private String folioSI;	
	private File docSI;
	private String docSIFileName;
	private Date fechaDocSI;
	private Date fechaAcuseSI;
	//variables de alcance a la solicitud de inscripcion
	private File docASI;
	private String docASIFileName;
	private Date fechaDocASI;
	private Date fechaAcuseASI;
	private String noOficioASI;
	private String numeroAuditor;
	private int tipoReporte;
	private File docOPA;
	private String docOPAFileName;
	private Date fechaDocOPA;
	private Date fechaAcuseOPA;  
	private String noOficioPA;
	//private boolean acreditacionTecnica;
	//private String obsAcreditacionTecnica;
	private File docOA;
	private String docOAFileName;
	private Date fechaDocOA;
	private Date fechaAcuseOA;
	private String noOficioA;
	private File docOOA;
	private String docOOAFileName;
	private Date fechaDocOOA;
	private Date fechaAcuseOOA;
	private String noOficioOA;
	private File docOL;
	private String docOLFileName;
	private Date fechaDocLeg;
	private Date fechaAcuseLeg;
	private String noOficioLeg;
	private File docOD;
	private String docODFileName;
	private Date fechaDocDic;
	private Date fechaAcuseDic;
	private String noOficioDic;
	private String obsDictaminacion;
	private int idCultivo;
	private Long idSI;
	private File docCartaA;
	private String docCartaAFileName;
	private Date fechaFirmaCA;
		
	private int acreditacionJuridica;
	private int acreditacionSI;
	private int acreditacionDictamen;
	private int idCriterioPago;
	private Double volumenDisponible;
	private Double volumenApoyar;
	private Double volumenInscripcion;
	private Double importeDisponible;
	private Double importeApoyar;	
	private Double importeInscripcion;
	private Programa programa;
	
	//Render de la pagina	
	private ProgramasV pv;
	private String nombreCultivo;
	private Comprador comprador;
	private String nombreCiclo;
	private String folioCartaAdhesion;
	private Integer periodoDOFSI;
	private int fueraTiempo;
	private Date fechaPublicacionDOF;
	private Date fechaLimiteSI;
	private String descripcionEstatus;
	private Double volumenAutorizado;
	private Double importeAutorizado;
	
	private Integer numCampos;
	private int registrar;
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private InscripcionDAO iDAO = new InscripcionDAO ();
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private PagosDAO pDAO = new PagosDAO();
	private List<ProgramasV> lstProgramasV;
	private List<CiclosProgramasV> lstCiclosProgramasV;
	private List<Comprador> lstCompradores;
	private List<Componente> lstComponente;	
	private List<AuditoresExternos> lstAuditoresExternos;
	private List<CuotasEsquemaV> lstCuotasEsqV;
	private List<Cultivo> lstCultivo;
	private List<Estado> lstEstado;
	
	private Integer[] capEstado;
	private Double [] capVolumen;
	private Double [] capImporte;
	private Integer[] capCultivo;
	private Integer[] capVariedad;
	private String [] capBodega;
	private String [] nomCultivo;
	private String [] nomVariedad;
	private String [] nomEstado;
	private Double [] capCuota;
	private SolicitudInscripcion si;
	private String msjOk;
	private String msjError;
	private long idInicializacionEsquema;	
	private int idComponente;
	private List<ProgramaCultivo> lstCultivoByPrograma;
	private List<SolicitudInscripcionV> lstSolInsV;
	private boolean bandera ;
	private int estatusSI;
	private List<AsignacionCartasAdhesionV> lstAsigCA;
	private List<Variedad> lstVariedad;
	private String descripcionLarga;
	private String rutaSolicitud;
	private String nombreOficio;
	private String rutaImagen;
	private String rutaAserca;
	private String nombreComprador;
	private String unidadMedida;
	private String rutaMarcaAgua;
	private String obsSolInscripcion;
	private int cartaAdhesionSistema;
	private int datosCompradorCompleto;
	private int errorFolioSI;
	private int fechaPublicacionDOFInt;
	private int fechaDOCSIInt;
	private int volumenXCultivoVariedad;
	private int idVariedad;
	private Double volumenXCV;
	private int count;
	private Date fechaAcuseFCA;
	private String acronimoCA;
	private int tipoAccion;
	private List<AlcanceSolicitudInscripcion> lstaAlcanceSI;
	private int habilitaAccion;
	private int idEstado;
	private Integer numCamposComp;
	private boolean complementoPorampliacionChk;
	private File docAmpliacionCA;
	private String docAmpliacionCAFileName;
	private Date fechaFirmaCAComplemento;
	private SolicitudInscripcionV solInsV;
	private InicializacionEsquema ie;
	private List<DetalleAsignacionCartasAdhesion> lstDetAsigCAO;
	private List<DetalleAsignacionCartasAdhesion> lstDetAsigCAC;
	private String claveBodega;
	private int errorClaveBodega;
	private String cuotaS;
	private String importeS;
	private String volumenXCVS;
	
	private List<CuotasEsquema> ce;
	private double totalVolSI;
	private double totalVolAutCA;
	private double totalImpAutCA;
	private List<RepresentanteCompradorV> lstRepresentanteV;
	private List<PagosV> lstPagosV;
	private double totalVolEnTramPago;
	private double totalImpEnTramPago;
	private List<CompradorExpedientesV> lstCompradorExpedientesV;
	
	
	public String busquedaSolicitudIns(){
		try{
			//Recupera lista de programas
			session = ActionContext.getContext().getSession();
			if((Integer)session.get("idPerfil") == 10){
				lstProgramasV = cDAO.consultaProgramaV(0);
			}else{
				lstProgramasV = cDAO.consultaProgramaV(0,(Integer) session.get("idArea"));
			}		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String ejecutaBusquedaSolicitudIns(){
		try{
			totalVolSI = 0.0;
			totalVolAutCA = 0.0;
			totalImpAutCA = 0.0;
			//Recupera lista de programas
			session = ActionContext.getContext().getSession();			
			if((Integer)session.get("idPerfil") == 5){
				int idArea = (Integer)session.get("idArea");
				lstSolInsV = iDAO.consultaSolicitudInscripcionVBusqueda(0, idPrograma, folioSI, folioCartaAdhesion, idArea, nombreComprador);
			}else{
				lstSolInsV = iDAO.consultaSolicitudInscripcionVBusqueda(0, idPrograma, folioSI, folioCartaAdhesion, 0, nombreComprador);
			}
			folioSI = null;
			folioCartaAdhesion = null;
			idPrograma = -1;
			bandera = true;
			//Totales en solicitud de inscripción
			for(SolicitudInscripcionV siv: lstSolInsV){
				totalVolSI += siv.getVolumenInscripcion(); 
				totalVolAutCA += siv.getVolumenTotalCarta();
				totalImpAutCA += siv.getImporteTotalCarta();
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			busquedaSolicitudIns();
		}
		return SUCCESS;
	}
	
	public String getDetalleSolIns(){
		try{
			//recupera la solicitud de inscripcion
			lstSolInsV = iDAO.consultaSolicitudInscripcionV(idSI);
			idPrograma = lstSolInsV.get(0).getIdPrograma();
			capturarInscripcion();			
			idCicloPrograma = lstSolInsV.get(0).getIdCicloPrograma();
			idComprador = lstSolInsV.get(0).getIdComprador();
			folioSI = lstSolInsV.get(0).getFolioSI();
			idCultivo = lstSolInsV.get(0).getIdCultivo();
			volumenInscripcion = lstSolInsV.get(0).getVolumenInscripcion();
			importeInscripcion = lstSolInsV.get(0).getImporteInscripcion();
			verificarDatosComprador();
			estatusSI = lstSolInsV.get(0).getEstatus();
			String rutaSolicitud = programa.getRutaDocumentos()+"solicitudes/"+lstSolInsV.get(0).getIdSI()+"/";
			docSIFileName = rutaSolicitud+lstSolInsV.get(0).getNomArchivoSI();
			fechaDocSI = lstSolInsV.get(0).getFechaDocSI();
			fechaAcuseSI = lstSolInsV.get(0).getFechaAcuseSI();
			numeroAuditor = lstSolInsV.get(0).getNumeroAuditor();
			String tipoReporteS = lstSolInsV.get(0).getTipoReporte();
			if(tipoReporteS.equals("CONSUMO")){
				tipoReporte = 0;
			}else if(tipoReporteS.equals("VENTAS")){
				tipoReporte = 1;
			}else{
				tipoReporte = 2;
			}
			docOPAFileName = rutaSolicitud+lstSolInsV.get(0).getNomArchivoOPA();
			fechaDocOPA = lstSolInsV.get(0).getFechaDocOPA();
			fechaAcuseOPA = lstSolInsV.get(0).getFechaAcuseOPA();
			noOficioPA = lstSolInsV.get(0).getNoOficioPA();
			
			acreditacionJuridica = lstSolInsV.get(0).getAcreditacionJuridica();
			acreditacionDictamen = lstSolInsV.get(0).getAcreditacionDictaminacion();
			if(acreditacionDictamen == 2){
				obsDictaminacion = lstSolInsV.get(0).getObsAcreditacionDic();
			}
			acreditacionSI = lstSolInsV.get(0).getAcreditacionSI();
			if(acreditacionSI == 2){
				obsSolInscripcion = lstSolInsV.get(0).getObsSolInscripcion();
			}
			
			if(acreditacionJuridica == 0 || acreditacionJuridica == 1){
				docOAFileName = rutaSolicitud+lstSolInsV.get(0).getNomArchivoOA();
				fechaDocOA = lstSolInsV.get(0).getFechaDocOA();
				fechaAcuseOA = lstSolInsV.get(0).getFechaAcuseOA();
				noOficioA = lstSolInsV.get(0).getNoOficioA();
			}else{
				docOOAFileName = rutaSolicitud+lstSolInsV.get(0).getNomArchivoOOA();
				fechaDocOOA = lstSolInsV.get(0).getFechaDocOOA();
				fechaAcuseOOA = lstSolInsV.get(0).getFechaAcuseOOA();
				noOficioOA = lstSolInsV.get(0).getNoOficioOA();
			}
			if(acreditacionDictamen == 0 || acreditacionDictamen == 1){
				if(lstSolInsV.get(0).getNomArchivoDic()!="" && lstSolInsV.get(0).getNomArchivoDic()!=null){
					docODFileName = rutaSolicitud+lstSolInsV.get(0).getNomArchivoDic();	
				}
				fechaAcuseDic = lstSolInsV.get(0).getFechaAcuseDic();
				fechaDocDic = lstSolInsV.get(0).getFechaDocDic();
				noOficioDic = lstSolInsV.get(0).getNoOficioDic();
			}
			
			if(lstSolInsV.get(0).getFolioCartaAdhesion() != null){
				folioCartaAdhesion = lstSolInsV.get(0).getFolioCartaAdhesion();
				lstPagosV = pDAO.consultaPagosV(-1, -1, -1, null, null, null, folioCartaAdhesion);
				for(PagosV p: lstPagosV){
					totalVolEnTramPago += p.getVolumen();
					totalImpEnTramPago += p.getImporte();
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	
	public String capturarInscripcion() throws JDBCException, Exception{
		try {
			session = ActionContext.getContext().getSession();
			if((Integer)session.get("idPerfil")==5){
				idArea= (Integer)session.get("idArea");
			}
			//recupera los programas
			if(registrar == 0 || registrar == 1){	
				lstProgramasV = cDAO.consultaProgramaV(0, idArea);
			}else if(registrar == 2|| registrar ==3 || registrar == 4){
				lstProgramasV = cDAO.consultaProgramaV(idPrograma);
			}			
			//recupera los compradores
			if(registrar == 0 || registrar ==1 || registrar == 3 || registrar == 4 ){
				lstCompradores = cDAO.consultaComprador(-1);	
			}else if(registrar == 2){
				lstCompradores = cDAO.consultaComprador(idComprador);
			}
			//Verifica si tiene alcances para mostrar en la pantalla de consulta
			if(registrar == 2 || registrar == 4){
				
				lstaAlcanceSI = iDAO.consultaAlcanceSI(idSI);		
				if(lstaAlcanceSI.size() == 0){
					habilitaAccion = 1;
				}else if(lstaAlcanceSI.size() == 1){
					habilitaAccion = 2;
				}
			}			
			
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","capturarInscripcion.- Ocurrio un error  debido a: "+e.getCause());
		}catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","capturarInscripcion.- Ocurrio un error debido a: "+e.getMessage());
		}		
		
		return SUCCESS;
	}
	
	public String verificarDatosComprador(){
		try {	
			boolean actaConstitutiva = false, rfc = false, comprobanteDomicilio=false;
			//Verifica si los datos del comprador estan completos
		    comprador = cDAO.consultaComprador(idComprador).get(0);
		    if( (comprador.getClaveMunicipio()==null)
		    	|| (comprador.getCodigoPostal() == null || comprador.getCodigoPostal().isEmpty()) 
		    	|| (comprador.getIdEstado()==null) ||(comprador.getNumExterior()==null || comprador.getNumExterior().isEmpty())
		    	|| (comprador.getRfc() == null || comprador.getRfc().isEmpty())){
		    	datosCompradorCompleto = 0;
		    	msjError = "Debe capturar todos los datos del comprador";
		    	return SUCCESS;
		    }	
		    
		    lstRepresentanteV = cDAO.consultaRepresentanteCompradorV(0, idComprador);
		    if(lstRepresentanteV.size()==0){
		    	msjError = "Debe capturar todos los datos del comprador en el apartado de representante legal";
		    	datosCompradorCompleto = 0;
		    	return SUCCESS;
		    }
		    
		    lstCompradorExpedientesV = cDAO.consultaCompradoresExpedientesV(0,idComprador,0);
		    if(lstCompradorExpedientesV.size()==0){
		    	msjError = "Debe cargar los expedientes correspondientes del comprador";
		    	datosCompradorCompleto = 0;
		    	return SUCCESS;
		    }
		    for(CompradorExpedientesV cev:  lstCompradorExpedientesV){
		    	if(cev.getIdExpediente().intValue()==23){
		    		actaConstitutiva = true;
		    	}else if(cev.getIdExpediente().intValue()==24){
		    		rfc= true;
		    	}else if (cev.getIdExpediente().intValue()==25){
		    		comprobanteDomicilio = true;
		    	}
		    }
		    
		    if(!actaConstitutiva){
		    	msjError = "Debe cargar el acta constitutiva del comprador, por favor verifique";
		    	datosCompradorCompleto = 0;
		    	return SUCCESS;
		    }
		    if(!rfc){
		    	msjError = "Debe cargar el rfc del comprador, por favor verifique";
		    	datosCompradorCompleto = 0;
		    	return SUCCESS;
		    }
		    if(!comprobanteDomicilio){
		    	msjError = "Debe cargar el comprobante de domicilio del comprador, por favor verifique";
		    	datosCompradorCompleto = 0;
		    	return SUCCESS;
		    }
		    
		    datosCompradorCompleto = 1;
		    
		    if(registrar == 0 || registrar ==1 || registrar == 3 || registrar == 4 ){
				lstCiclosProgramasV = iDAO.consultaCicloProgramasV(idPrograma);	
				lstCompradores = cDAO.consultaComprador(-1);
				lstAuditoresExternos = cDAO.consultaAuditores("", "", 1);
				lstCultivoByPrograma = iDAO.recuperaCultivoByPrograma(idPrograma,0);
				programa = cDAO.consultaPrograma(idPrograma).get(0);
				idCriterioPago = programa.getCriterioPago();
				pv = cDAO.consultaProgramaV(idPrograma).get(0);
				 
				if(pv.isCartaAdhesionSistema()==true){
					cartaAdhesionSistema = 1;
				}else{
					cartaAdhesionSistema = 0;
					acronimoCA = pv.getAcronimoCA();
				}				
				//Recupera la fecha de publicacion para validar contra fechas de la solicitud de inscripcion
				fechaPublicacionDOF = cDAO.consultaProgramaV(idPrograma).get(0).getFechaPublicacion();
				fechaPublicacionDOFInt= Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(fechaPublicacionDOF).toString());
					
			}else if(registrar == 2){
				lstCiclosProgramasV = iDAO.consultaCicloProgramasV(0, idCicloPrograma);
				lstCompradores = cDAO.consultaComprador(idComprador);
				lstAuditoresExternos = cDAO.consultaAuditoresbyNumAuditor(numeroAuditor);
				lstCultivoByPrograma = iDAO.recuperaCultivoByPrograma(0,idCultivo);
				programa = cDAO.consultaPrograma(idPrograma).get(0);
				idCriterioPago = programa.getCriterioPago();
			}		    
		    			
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error debido a: "+e.getCause());
		}catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error debido a: "+e.getMessage());
		}		
		return SUCCESS;
	}
	
	public String selectAccionAlcanceEdicion(){
		
		getDetalleSolIns();
		
		return SUCCESS;
	}
	
	public String acreditar(){	
		return SUCCESS;
	}
	
	public String acreditarSI(){
		
		return SUCCESS;
	}
	
	public String acreditarDictaminacion(){	
		return SUCCESS;
	}	
	
	public String validarFolioInscripcion(){
		try{
			//Verifica si el folio de la carta de adhesion es generada por el sistema
			pv = cDAO.consultaProgramaV(idPrograma).get(0);
			boolean bandera =false;
			if(registrar == 0){
				bandera = true;
			}else if(registrar == 3){
				if(!si.getFolioSI().equals(folioSI)){
					bandera = true;	
				}
			}
			if(bandera){
				if(!pv.isCartaAdhesionSistema()){
					if(iDAO.consultaSolInscripcionByFolioSIORFCA(folioSI).size()>0){
						errorFolioSI = 1;
						msjError = "El folio de la solicitud ya se encuentra registrado, por favor verifique";
						System.out.println("Es posible que ya se encuentre registrado el folio de la solicitud o ya se tiene un folio de carta de adhesión que coincide");
						AppLogger.info("app","Es posible que ya se encuentre registrado el folio de la solicitud o ya se tiene un folio de carta de adhesión que coincide");
					}
				}else{
					if(iDAO.consultaSolicitudInscripcion(0, folioSI).size()>0){
						errorFolioSI = 1;
						msjError = "El folio de la solicitud ya se encuentra registrado, por favor verifique";
										
					}
				}
				
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al validar el folio de la solicitud de inscripcion debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	
	public String registrarSolicitudInscripcion() throws JDBCException, Exception{
		try{
			
			String tipoReporteS = "", ext ="", nombreArchivoSI="", nombreArchivoOPA="", nombreArchivoOA="", nombreArchivoOOA="", nombreArchivoDic="", rutaSolicitud="", nombreArchivoOASI ="";
			session = ActionContext.getContext().getSession();									
			pv = cDAO.consultaProgramaV(idPrograma).get(0);
			if(registrar == 0 || registrar == 1 || registrar == 3){ // nuevo
				fechaPublicacionDOF = pv.getFechaPublicacion();
				//Recupera nombre de cultivo
				nombreCultivo = cDAO.consultaCultivo(idCultivo).get(0).getCultivo();			
				//Recupera ciclo-año
				CiclosProgramasV cpv= iDAO.consultaCicloProgramasV(0, idCicloPrograma).get(0);
				String anio = cpv.getEjercicio().toString();
				anio = anio.substring(2,4);
				nombreCiclo = cpv.getCicloCorto()+anio;
			}
			if(registrar == 0 || registrar == 1){
				bandera = true;
				si = new SolicitudInscripcion();
			}else if(registrar == 3 || registrar == 4 ){
				si = iDAO.consultaSolicitudInscripcion(0, null, idSI).get(0);
			}	
				
			//Verifica que la fecha de la solicitud se encuentra en tiempo de acuerdo a la inicializacion del esquema
			if(registrar == 0 || registrar == 1 || registrar == 3){
				periodoDOFSI = pv.getPeriodoDOFSI();
				fechaLimiteSI = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(fechaPublicacionDOF).toString(), periodoDOFSI);
				String fechaLimiteSIS = new SimpleDateFormat("yyyyMMdd").format(fechaLimiteSI).toString();
				String fechaDocSIS = new SimpleDateFormat("yyyyMMdd").format(fechaDocSI).toString();	
				if(Long.parseLong(fechaDocSIS)>Long.parseLong(fechaLimiteSIS)){
				    fueraTiempo = 1;
				    si.settTramitePresentacionSI(true);
			    }else{
			    	si.settTramitePresentacionSI(false);
			    	fueraTiempo=0;
			    }
			
			}
			
			if(tipoReporte==0){
				tipoReporteS = "CONSUMO";
			}else if(tipoReporte==1){
				tipoReporteS = "VENTAS";
			}else if(tipoReporte==2){
				tipoReporteS = "AMBOS";
			}

			if(registrar == 3){
				rutaSolicitud =  pv.getRutaDocumentos()+"solicitudes/"+si.getIdSI()+"/";
				if(docSI!=null){	
					File f = new File(rutaSolicitud+si.getNomArchivoSI());
					if(f.exists()){
						if(!f.delete()){
							AppLogger.error("errores","Error al eliminar: "+rutaSolicitud+si.getNomArchivoSI());
						}else{
							AppLogger.info("app","Se borro archivo: "+rutaSolicitud+si.getNomArchivoSI());
						}
					}
				}else{
					nombreArchivoSI = si.getNomArchivoSI();
				}
				if(docOPA!=null){	
					File f = new File(rutaSolicitud+si.getNomArchivoOPA());
					if(f.exists()){
						if(!f.delete()){
							AppLogger.error("errores","Error al eliminar: "+rutaSolicitud+si.getNomArchivoOPA());
						}else{
							AppLogger.info("app","Se borro archivo: "+rutaSolicitud+si.getNomArchivoOPA());
						}
					}
				}else{
					nombreArchivoOPA = si.getNomArchivoOPA();
				}
				
				
			} // end registrar == 3
			//Renombrando archivos de solicitud de inscripcion
			if(registrar==0 || (registrar == 3 && docSI!=null)){
				ext = docSIFileName.toLowerCase().substring( docSIFileName.lastIndexOf(".") );
				nombreArchivoSI = "SI"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de solicitud de inscripcion	

			}
			
			if(registrar==0 || (registrar == 3 && docOPA!=null)){
				ext = docOPAFileName.toLowerCase().substring(docOPAFileName.lastIndexOf(".") );
				nombreArchivoOPA = "OPA"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext; //Archivo de oficio de peticion de acreditacion
			}
			if( registrar != 4 ){
				si.setIdPrograma(idPrograma);
				si.setIdCicloPrograma(idCicloPrograma);
				si.setIdComprador(idComprador);
				si.setFolioSI(folioSI);				 
				si.setIdCultivo(idCultivo);
				si.setNomArchivoSI(nombreArchivoSI);
				si.setFechaDocSI(fechaDocSI);
				si.setFechaAcuseSI(fechaAcuseSI);
				si.setNumeroAuditor(numeroAuditor);
				si.setTipoReporte(tipoReporteS);
				si.setNomArchivoOPA(nombreArchivoOPA);
				si.setFechaDocOPA(fechaDocOPA);
				si.setFechaAcuseOPA(fechaAcuseOPA);
				si.setNoOficioPA(noOficioPA);
				if(registrar == 0){
					si.setFechaAlta(new Date());
				}
				
			}
			if(registrar == 0){
				si.setUsuarioCreacion((Integer)session.get("idUsuario"));
			}else{
				si.setUsuarioModificacion((Integer)session.get("idUsuario"));
			}
			
			if(idCriterioPago == 1 || idCriterioPago ==3){
				si.setVolumenInscripcion(volumenInscripcion);
			}else{
				si.setImporteInscripcion(importeInscripcion);
			}
			
			if(registrar == 0 || registrar == 3){
				if(acreditacionJuridica==0 || acreditacionJuridica==1){
					si.setFechaDocOA(fechaDocOA);
					si.setFechaAcuseOA(fechaAcuseOA);
					si.setNoOficioA(noOficioA);
					//Si la acreditacion juridica es positiva o negativa, guardar el oficio de peticion 
					if(registrar == 3){
						if(docOA!=null){	
							File f = new File(rutaSolicitud+si.getNomArchivoOA());
							if(f.exists()){
								if(!f.delete()){
									AppLogger.error("errores","Error al eliminar: "+rutaSolicitud+si.getNomArchivoOA());
								}else{
									AppLogger.info("app","Se borro archivo: "+rutaSolicitud+si.getNomArchivoOA());
								}
							}
						}else{
							nombreArchivoOA = si.getNomArchivoOA();
						}	
					}
					if(docOA!=null){
						ext = docOAFileName.toLowerCase().substring( docOAFileName.lastIndexOf(".") );
						nombreArchivoOA = "OA"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;
					}				
					si.setNomArchivoOA(nombreArchivoOA);		
				}else if(acreditacionJuridica == 2){ //Con observacion
					if(registrar == 3){
						if(docOOA!=null){	
							File f = new File(rutaSolicitud+si.getNomArchivoOOA());
							if(f.exists()){
								if(!f.delete()){
									AppLogger.error("errores","Error al eliminar: "+rutaSolicitud+si.getNomArchivoOOA());
								}else{
									AppLogger.info("app","Se borro archivo: "+rutaSolicitud+si.getNomArchivoOOA());
								}
							}
						}else{
							nombreArchivoOOA = si.getNomArchivoOOA();
						}	
					}
					
					
					if(docOOA!=null){
						ext = docOOAFileName.toLowerCase().substring( docOOAFileName.lastIndexOf(".") );
						nombreArchivoOOA = "OOA"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext;	
					}
					si.setNomArchivoOOA(nombreArchivoOOA);
					si.setFechaAcuseOOA(fechaAcuseOOA);
					si.setFechaDocOOA(fechaDocOOA);
					si.setNoOficioOA(noOficioOA);
					//si.setEstatus(3);
				}
				if(acreditacionDictamen == 0 || acreditacionDictamen == 1){				
					if(registrar == 3){
						if(docOD!=null){	
							File f = new File(rutaSolicitud+si.getNomArchivoDic());
							if(f.exists()){  
								if(!f.delete()){
									AppLogger.error("errores","Error al eliminar: "+rutaSolicitud+si.getNomArchivoDic());
								}else{
									AppLogger.info("app","Se borro archivo: "+rutaSolicitud+si.getNomArchivoDic());
								}
							}
						}else{
							nombreArchivoDic = si.getNomArchivoDic();
						}
					}
					
					if(docOD!=null){
						ext = docODFileName.toLowerCase().substring( docODFileName.lastIndexOf(".") );
						nombreArchivoDic = "DIC"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;	
					}				
					if(docOD!=null){//estos campos no son requeridos ni en el registro ni en la edicion
						si.setNomArchivoDic(nombreArchivoDic);
						si.setFechaAcuseDic(fechaAcuseDic);
						si.setFechaDocDic(fechaDocDic);
						si.setNoOficioDic(noOficioDic);
					}
				}else{
					si.setObsAcreditacionDic(obsDictaminacion);
				}
				si.setAcreditacionJuridica(acreditacionJuridica);
				si.setAcreditacionDictaminacion(acreditacionDictamen);
				si.setAcreditacionSI(acreditacionSI);
				if(acreditacionSI == 2){
					si.setObsSolInscripcion(obsSolInscripcion);
				}
				
			}
			
			//Si el programa no fue configurado para que el folio de la carta de adhesion no se genere por el sistema
			if(registrar == 0 || registrar == 3){
				if(!pv.isCartaAdhesionSistema()){
					folioCartaAdhesion = folioSI;
					if(registrar==0 ||(registrar == 3 && !si.getFolioSI().equals(folioSI) )){
						si.setFolioCartaAdhesion(folioCartaAdhesion);
					}
					
				}
			}
			
			if(registrar == 0 || registrar == 3){
				//Guardar el estatus de la solicitud de acuerdo a las acreditaciones
				if(acreditacionJuridica == 0 && acreditacionDictamen == 0 && acreditacionSI ==  0){
					estatusSI = 1;
					if(!pv.isCartaAdhesionSistema()){
						si.setFechaAcuseFCA(new Date());
					}
					si.setEstatus(estatusSI);
					
				}else if(acreditacionJuridica == 2 || acreditacionDictamen == 2 || acreditacionSI ==  2){
					estatusSI = 3;
					si.setEstatus(estatusSI);
				}else{
					estatusSI = 2;
					si.setEstatus(estatusSI);
				}
		
			}
			
			si = (SolicitudInscripcion) cDAO.guardaObjeto(si);
			idSI= si.getIdSI();
			if(!pv.isCartaAdhesionSistema()){
				idSI = si.getIdSI();	
			}
			//Guardando los archivos en los directorios
			rutaSolicitud =  pv.getRutaDocumentos()+"solicitudes/"+si.getIdSI()+"/";
			if(registrar == 0 || registrar == 3){
				Utilerias.crearDirectorio(rutaSolicitud);
				if(docSI!=null){
					Utilerias.cargarArchivo(rutaSolicitud, nombreArchivoSI, docSI);	
				}
				if(docOPA!=null){
					Utilerias.cargarArchivo(rutaSolicitud, nombreArchivoOPA, docOPA);	
				}
				
			}
			
			if(registrar == 0 || registrar == 3){
				if(acreditacionJuridica == 0 || acreditacionJuridica == 1){
					if(docOA!=null){
						Utilerias.cargarArchivo(rutaSolicitud, nombreArchivoOA, docOA);
					}
				}else if(acreditacionJuridica == 2){
					if(docOOA!=null){
						Utilerias.cargarArchivo(rutaSolicitud, nombreArchivoOOA, docOOA);
					}
						
				}			
				if(acreditacionDictamen == 0 || acreditacionDictamen == 1){
					if(docOD!=null){
						Utilerias.cargarArchivo(rutaSolicitud, nombreArchivoDic, docOD);
					}
					
				}
			}
			if(registrar == 4){
				//Guarda los archivos del alcance registrar == 4
				AlcanceSolicitudInscripcion asi = new AlcanceSolicitudInscripcion();
				asi.setFechaAcuseAlcance(fechaAcuseASI);
				asi.setFechaDocAlcance(fechaDocASI);
				asi.setIdSI(idSI);
				asi.setNoOficioAlcance(noOficioASI);
				ext = docASIFileName.toLowerCase().substring( docASIFileName.lastIndexOf(".") );
				nombreArchivoOASI = "OASI"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext;					
				Utilerias.cargarArchivo(rutaSolicitud, nombreArchivoOASI, docASI);
				asi.setRutaArchivo(rutaSolicitud);
				asi.setNomArchivoAlcance(nombreArchivoOASI);
				cDAO.guardaObjeto(asi);
				
			}
		
			//Recupera datos del comprador
			comprador = cDAO.consultaComprador(idComprador).get(0);
			msjOk = "Se guardó satisfactoriamente la Solicitud";
			registrar = 2;			
			
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrio un error al guardar la solicitud de inscripcion debido a: "+ e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			return INPUT;
		} catch (Exception e) {			
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrio un error al guardar la solicitud de inscripcion debido a: "+ e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			return INPUT;
		}finally{
			try{		
				getDetalleSolIns();
				verificarDatosComprador();
			}catch (JDBCException e) {
				AppLogger.error("errores","Ocurrió un error en finally del metodo registrar registrarSolicitudInscripcion debido a: "+e.getCause());
			}catch (Exception e) {
				AppLogger.error("errores","Ocurrió un error en finally del metodo registrar registrarSolicitudInscripcion debido a: "+e.getMessage());
			}
				
		}

		
		return SUCCESS;
	}
	
	public String generarFolioCartaAdhesion() throws JDBCException, Exception{
		try{		
			si = iDAO.consultaSolicitudInscripcion(0, null, idSI).get(0);
			CiclosProgramasV cpv= iDAO.consultaCicloProgramasV(0,  si.getIdCicloPrograma()).get(0);
			String anio = cpv.getEjercicio().toString();
			anio = anio.substring(2,4);
			nombreCiclo = cpv.getCicloCorto()+anio;
			pv = cDAO.consultaProgramaV(si.getIdPrograma()).get(0);	
			
			//Recupera nombre de cultivo
			nombreCultivo = cDAO.consultaCultivo(si.getIdCultivo()).get(0).getCultivo();	
			Integer consecutivo = iDAO.recuperaMaxConsecutivoCartaAdhesion(pv.getAcronimoCA(),  si.getIdCultivo(), si.getIdCicloPrograma());
			System.out.println("Cosecutivo carta"+consecutivo);
			folioCartaAdhesion = pv.getAcronimoCA()+"-"+nombreCiclo+"-"+nombreCultivo+"-"+TextUtil.rellenarConCerosIzquierda(consecutivo.intValue(), 3);
			si.setFolioCartaAdhesion(folioCartaAdhesion);
			si.setConsecutivoCarta(consecutivo);
			si.setFechaAcuseFCA(new Date());
			cDAO.guardaObjeto(si);
		
			getDetalleSolIns();
			registrar = 2;			
			comprador = cDAO.consultaComprador(si.getIdComprador()).get(0);
			estatusSI = 1;	
				
			msjOk = "Se generó satisfactoriamente el folio de la carta";
			
			
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un  error al generar el folio de la carta de adhesion debido a: "+e.getMessage());
		}
		return SUCCESS;
	}

	public String recuperaCuotasInicEsquema()throws JDBCException, Exception{
		try{
			lstDetAsigCAO = new ArrayList<DetalleAsignacionCartasAdhesion>();
			lstDetAsigCAC = new ArrayList<DetalleAsignacionCartasAdhesion>();
			recuperarDatosAsignacionCA();						 			
			if(registrar ==2 || registrar == 3){ 
					rutaSolicitud =  programa.getRutaDocumentos()+"solicitudes/"+solInsV.getIdSI()+"/";
					if(solInsV.getNomArchivoCa()!=null && solInsV.getNomArchivoCa()!=""){
						docCartaAFileName = rutaSolicitud + solInsV.getNomArchivoCa();
					}
					if(solInsV.getFechaFirmaCa()!=null ){
						fechaFirmaCA = solInsV.getFechaFirmaCa(); 
					}
					docOLFileName = rutaSolicitud + solInsV.getNomArchivoLeg();
					fechaDocLeg = solInsV.getFechaDocLeg();
					fechaAcuseLeg = solInsV.getFechaAcuseLeg();
					noOficioLeg = solInsV.getNoOficioLeg();
					if(idCriterioPago == 1 || idCriterioPago == 3){
						volumenApoyar = iDAO.recuperaVolumenAutorizadoBySolicitud(folioCartaAdhesion).doubleValue();
					}else if(idCriterioPago == 2){
						importeApoyar = iDAO.recuperaImporteAutorizadoBySolicitud(folioCartaAdhesion).doubleValue();
					}
					
			 }
					  
		  if(registrar !=0){
			  agregaCultivoVariedadEstadoCA();
			  agregaCultivoVariedadEstadoCAComp(); 
		  }
		  		
		}catch(Exception e){
			e.printStackTrace();
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador "+e.getMessage());
		}
		return SUCCESS;
	}

	private void recuperarDatosAsignacionCA() throws JDBCException, Exception {
		solInsV = iDAO.consultaSolicitudInscripcionV(idSI).get(0);
		folioCartaAdhesion = solInsV.getFolioCartaAdhesion();
		idPrograma = solInsV.getIdPrograma();
		programa =  cDAO.consultaPrograma(solInsV.getIdPrograma()).get(0);
		nombreComprador = cDAO.consultaComprador(solInsV.getIdComprador()).get(0).getNombre();
		nombreCultivo = solInsV.getCultivo();
		unidadMedida = cDAO.consultaUnidadMedida(programa.getIdUnidadMedida()).get(0).getAbreviatura();
		nombreCiclo = programa.getCiclo();
		fechaDocSI = solInsV.getFechaDocSI();
		fechaDOCSIInt= Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(fechaDocSI).toString());
		//Recupera el id_inicializacion_esquema
		ie = iDAO.consultaInicializacionPrograma(solInsV.getIdPrograma()).get(0);
		idInicializacionEsquema = ie.getId();
		idCriterioPago = programa.getCriterioPago();
		descripcionLarga = programa.getDescripcionLarga();
		//Verifica si el volumen esta limitado por cultivo variedad en la inicializacion del programa
		List<CultivoVariedadEsquema> lstCultivoVariedadEsquema = iDAO.consultaCultivoVariedadEsquema(idPrograma);
		if(lstCultivoVariedadEsquema.size()>0){
			volumenXCultivoVariedad = 1;
		}
		if(idCriterioPago == 1 || idCriterioPago == 3){
			BigDecimal volumenBigDe= iDAO.recuperaVolumenAutorizadoByPrograma(solInsV.getIdPrograma());
			double volumenComprometido = volumenBigDe.doubleValue();
			volumenAutorizado =ie.getVolumenAutorizado();
			volumenDisponible = volumenAutorizado - volumenComprometido;
			System.out.println("volumen Disponible"+ volumenDisponible);
			volumenInscripcion = solInsV.getVolumenInscripcion();
		}else if(idCriterioPago == 2){
			BigDecimal importeBigDe= iDAO.recuperaImporteAutorizadoByPrograma(solInsV.getIdPrograma());
			double importeComprometido = importeBigDe.doubleValue();
			if(registrar==0){
				importeAutorizado = ie.getImporteAutorizado();
			}else if(registrar == 1 || registrar == 2 || registrar == 3){
				importeAutorizado =ie.getImporteAutorizado();
			}
			importeDisponible = importeAutorizado - importeComprometido;
			System.out.println("Importe Disponible"+ importeDisponible);
			importeInscripcion = solInsV.getImporteInscripcion();
		}
	}

	/**Este metodo asigna el volumen o importe a las cartas de adhesion por tipo "Original" por "Complemento"
	 * Tipo en detalle_asignacion carta 1 (Original) 2 (Complemento)
	 */
	public String asignarCECartaAdhesion() throws JDBCException, Exception{
		String ext = "", nombreArchivo="";
		try{
			//Recupera los datos de la solicitud de inscripcion
			List<SolicitudInscripcion> lstSI = iDAO.consultaSolicitudInscripcion(0, "", idSI);
			SolicitudInscripcion solIns = lstSI.get(0);
			idPrograma = solIns.getIdPrograma();
			folioCartaAdhesion = solIns.getFolioCartaAdhesion();
			//Recupera ruta documentos de programa
			programa = cDAO.consultaPrograma(idPrograma).get(0);
			idCriterioPago = programa.getCriterioPago();
			String rutaSolicitud =  programa.getRutaDocumentos()+"solicitudes/"+solIns.getIdSI()+"/";
			//Guarda el archivo de la carta de adhesion
			if(docCartaAFileName !=null && docCartaAFileName !=""){
				ext = docCartaAFileName.toLowerCase().substring(docCartaAFileName.lastIndexOf(".") );
				nombreArchivo = "CA"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;
				solIns.setNomArchivoCA(nombreArchivo);
				Utilerias.cargarArchivo(rutaSolicitud, nombreArchivo, docCartaA);
				solIns.setFechaFirmaCa(fechaFirmaCA);
			}			
			
			//Guarda el archivo de legibilidad
			if(docOLFileName!=null && docOLFileName != ""){
				ext = docOLFileName.toLowerCase().substring(docOLFileName.lastIndexOf(".") );
				nombreArchivo = "OL"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;
				solIns.setNomArchivoLeg(nombreArchivo);
				solIns.setFechaDocLeg(fechaDocLeg);
				solIns.setFechaAcuseLeg(fechaAcuseLeg);
				solIns.setNoOficioLeg(noOficioLeg);
				solIns.setEstatus(4);
				Utilerias.cargarArchivo(rutaSolicitud, nombreArchivo, docOL);
			}
			
			if(registrar == 0){
				//Guardar la carta de adhesion en la tabla correspondiente
				CartaAdhesion ca = new CartaAdhesion();   
				ca.setFolioCartaAdhesion(solIns.getFolioCartaAdhesion());
				ca.setIdSI(idSI.intValue());
				ca.setIdPrograma(idPrograma);
				ca.setIdComprador(solIns.getIdComprador());
				ca.setEstatus(1);	
				//Guardar carta adhesion
				cDAO.guardaObjeto(ca);
				//Guarda Asignacion cuotas carta adhesion
				for(int i=0; i< capEstado.length; i++){
					AsignacionCartasAdhesion aca = new AsignacionCartasAdhesion();
					aca.setIdCultivo(capCultivo[i]);
					aca.setIdVariedad(capVariedad[i]);
					aca.setIdEstado(capEstado[i]);
					
					aca.setFolioCartaAdhesion(folioCartaAdhesion);
					aca.setIdPrograma(idPrograma);	
					aca.setImporte(capImporte[i]);
					aca.setClaveBodega(capBodega[i]);
					aca.setFechaCreacion(new Date());
					DetalleAsignacionCartasAdhesion daca = new DetalleAsignacionCartasAdhesion();
					daca.setIdCultivo(capCultivo[i]);
					daca.setIdVariedad(capVariedad[i]);
					daca.setIdEstado(capEstado[i]);		
					daca.setClaveBodega(capBodega[i]);					
					daca.setIdPrograma(idPrograma);
					daca.setFolioCartaAdhesion(folioCartaAdhesion);
					daca.setFechaCreacion(new Date());
					if(idCriterioPago == 1 || idCriterioPago == 3){
						aca.setVolumen(capVolumen[i]);								
						daca.setVolumen(capVolumen[i]);
						aca.setCuota(capCuota[i]);
						daca.setCuota(capCuota[i]);
						daca.setImporte(capImporte[i]);
					}else if(idCriterioPago == 2){
						aca.setImporte(capImporte[i]);					
						daca.setImporte(capImporte[i]);					
					}
					daca.setTipo(1);//Normal
					cDAO.guardaObjeto(aca);
					cDAO.guardaObjeto(daca);					
				}			
				//Guardar la carta adhesion en tabla de programa_comprador
				ProgramaComprador pc = new ProgramaComprador();
				pc.setIdPrograma(solIns.getIdPrograma());
				pc.setIdComprador(solIns.getIdComprador());
				pc.setNoCarta(solIns.getFolioCartaAdhesion());
				cDAO.guardaObjeto(pc);		
			}
			
			//Guarda Complemento de la CARTA ADHESION
			if(complementoPorampliacionChk){
				//GUARDA LOS DATOS DE FECHA Y ARCHIVO DEL COMPLEMENTO DE LA CARTA ADHESION
				solIns.setFechaFirmaCAComplemento(fechaFirmaCAComplemento);
				//Carga archivo del complemento de la carta de adhesion
				ext = docAmpliacionCAFileName.toLowerCase().substring(docAmpliacionCAFileName.lastIndexOf(".") );
				nombreArchivo = "CCA"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;
				solIns.setNomArchivoCACom(nombreArchivo);
				Utilerias.cargarArchivo(rutaSolicitud, nombreArchivo, docAmpliacionCA);
				for(int i=0; i< capEstado.length; i++){
					//Recupera el registro de la asignacion de la carta de adhesion
					List<AsignacionCartasAdhesion> lstAca = iDAO.consultaAsignacionCartasAdhesion(capBodega[i], capEstado[i], capCultivo[i], capVariedad[i], folioCartaAdhesion);
					//Verifica si el incremento es un mismo EDO, CULTIVO y VARIEDAD
					if(lstAca.size()>0){
						AsignacionCartasAdhesion aca = lstAca.get(0);
						if(idCriterioPago == 1 || idCriterioPago == 3){
							aca.setVolumen(capVolumen[i]+aca.getVolumen());
							aca.setImporte(capImporte[i]+aca.getImporte());
						}else if(idCriterioPago==2){
							aca.setImporte(capImporte[i]+aca.getImporte());
						}
						//Actualiza volumen o importe del registro
						cDAO.guardaObjeto(aca);
					}else{
						//Insertar registro en AsignacionCartasAdhesion
						AsignacionCartasAdhesion aca = new AsignacionCartasAdhesion();
						aca.setFolioCartaAdhesion(folioCartaAdhesion);
						aca.setIdEstado(capEstado[i]);
						aca.setIdCultivo(capCultivo[i]);
						aca.setIdVariedad(capVariedad[i]);
						aca.setClaveBodega(capBodega[i]);
						aca.setFechaCreacion(new Date());
						aca.setIdPrograma(idPrograma);
						if(idCriterioPago == 1 || idCriterioPago == 3){
							aca.setVolumen(capVolumen[i]);
							aca.setCuota(capCuota[i]);
						}else if(idCriterioPago==2){						
							aca.setImporte(capImporte[i]);
						}
						cDAO.guardaObjeto(aca);
					}
					DetalleAsignacionCartasAdhesion daca = new DetalleAsignacionCartasAdhesion();
					daca.setFolioCartaAdhesion(folioCartaAdhesion);
					daca.setIdEstado(capEstado[i]);
					daca.setIdCultivo(capCultivo[i]);
					daca.setIdVariedad(capVariedad[i]);
					daca.setClaveBodega(capBodega[i]);
					daca.setTipo(2); //Complemento
					daca.setFechaCreacion(new Date());
					daca.setIdPrograma(idPrograma);
					if(idCriterioPago == 1 || idCriterioPago == 3){
						daca.setVolumen(capVolumen[i]);
						daca.setCuota(capCuota[i]);
						daca.setImporte(capImporte[i]);
					}else if(idCriterioPago==2){						
						daca.setImporte(capImporte[i]);
					}
					//Inserta nuevo registro en la tabla de DetalleAsignacionCartasAdhesion
					cDAO.guardaObjeto(daca);
				}
			}
			
			if(registrar == 3 || complementoPorampliacionChk){
				cDAO.guardaObjeto(solIns);
				complementoPorampliacionChk = false;
			}else{
				registrar = 2; //para solo consulta en el jsp
			}		
			recuperaCuotasInicEsquema();	
			msjOk = "Se guardó satisfactoriamente el registro";
			
		}catch(JDBCException e){
			e.printStackTrace();
			registrar=1;
			recuperaCuotasInicEsquema();	
			AppLogger.error("errores","Ocurrio un error al guardar la asignacion de carta de adhesion debido a:"+e.getCause()+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}catch(Exception e){
			e.printStackTrace();
			registrar=1;
			recuperaCuotasInicEsquema();	
			AppLogger.error("errores","Ocurrio un error al guardar la asignacion de carta de adhesion debido a:"+e.getCause()+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}
	
	public String generarAcuseAsigCartaAdhesion(){
		try{			
			System.out.println("generarAcuseAsigCartaAdhesion");
			SolicitudInscripcionV solInsV = iDAO.consultaSolicitudInscripcionV(idSI).get(0);
			folioCartaAdhesion = solInsV.getFolioCartaAdhesion();
			programa =  cDAO.consultaPrograma(solInsV.getIdPrograma()).get(0);
			descripcionLarga = programa.getDescripcionLarga();
			nombreComprador = cDAO.consultaComprador(solInsV.getIdComprador()).get(0).getNombre();
			fechaAcuseFCA = solInsV.getFechaAcuseFCA();
			nombreCultivo = solInsV.getCultivo();
			rutaSolicitud =  programa.getRutaDocumentos()+"solicitudes/"+solInsV.getIdSI()+"/";
			Utilerias.crearDirectorio(rutaSolicitud);
			unidadMedida = cDAO.consultaUnidadMedida(programa.getIdUnidadMedida()).get(0).getAbreviatura();			
			nombreCiclo = programa.getCiclo();
			nombreOficio = "Acuse-CartaAdhesion.pdf";
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");
			rutaAserca = context.getRealPath("/images/logoASERCA.jpg");
			rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
			AcuseCartaAdhesion a = new AcuseCartaAdhesion(this);
			a.generarAcuseCartaAdhesion();			
			Utilerias.entregarArchivo(rutaSolicitud, nombreOficio, "pdf");
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error debido a: ");
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return null;
	}
	
	public String agregaCultivoVariedadEstadoCA(){
		
		try{	
			if(registrar == 0){
				lstAsigCA = new ArrayList<AsignacionCartasAdhesionV>();
				for(int i=1; i<=numCampos; i++){
					lstAsigCA.add(new AsignacionCartasAdhesionV());
				}								
			}else if(registrar == 2 || registrar == 3){
				setLstDetAsigCAO(iDAO.consultaDetalleAsignacionCartasAdhesion(folioCartaAdhesion, 1));
				numCampos = lstDetAsigCAO.size();
			}
			lstEstado = iDAO.recuperaEstadoByInicilizacionEsquema(idInicializacionEsquema);
			lstCultivo = iDAO.recuperaCultivoByInicilizacionEsquema(idInicializacionEsquema);
			if(registrar == 0){
				lstVariedad = new ArrayList<Variedad>();
			}else{
				lstVariedad = iDAO.recuperaVariedadByInicilizacionEsquema(idInicializacionEsquema, idCultivo);
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return SUCCESS;
	}
	
	public String agregaCultivoVariedadEstadoCAComp(){
		if(complementoPorampliacionChk){
			lstAsigCA = new ArrayList<AsignacionCartasAdhesionV>();
			for(int i=1; i<=numCamposComp; i++){
				lstAsigCA.add(new AsignacionCartasAdhesionV());
			}
			lstEstado = iDAO.recuperaEstadoByInicilizacionEsquema(idInicializacionEsquema);
			lstCultivo = new ArrayList<Cultivo>();
			lstVariedad = new ArrayList<Variedad>();
		}
		//Recupera el complemento de la carta Adhesion		
		lstDetAsigCAC = iDAO.consultaDetalleAsignacionCartasAdhesion(folioCartaAdhesion, 2);
		if(lstDetAsigCAC.size()>0){
			numCamposComp = lstDetAsigCAC.size();
			docAmpliacionCAFileName = rutaSolicitud + solInsV.getNomArchivoCACom();
			fechaFirmaCAComplemento = solInsV.getFechaFirmaCAComplemento();
			lstEstado = iDAO.recuperaEstadoByInicilizacionEsquema(idInicializacionEsquema);
			lstCultivo = iDAO.recuperaCultivoByInicilizacionEsquema(idInicializacionEsquema);
			lstVariedad = iDAO.recuperaVariedadByInicilizacionEsquema(idInicializacionEsquema, idCultivo);
		}
		
		return SUCCESS;
	}
	public String recuperaCultivoByVariedadAsigCA(){
		//Recupera los datos de la variedad por cultivo
		lstVariedad = iDAO.recuperaVariedadByInicilizacionEsquema(idInicializacionEsquema, idCultivo);
		return SUCCESS;		
	}
	
	//public String validarVolumenXCultivoVariedad(){
	public String recuperarDatosCuotaImpCulXVariedad(){

		double cuota = 0;
		
		if(idCriterioPago != 2){
			//Inicializacion esquema por etapa
			 List<CultivoVariedadEsquema> listCve = iDAO.consultaCultivoVariedadEsquema(idPrograma, idCultivo, idVariedad);
			if(listCve.size()>0){
				volumenXCVS = TextUtil.formateaNumeroComoCantidad(listCve.get(0).getVolumen());
			}
			
			//Recupera la cuota
			ce = iDAO.consultaCuotasEsquema(idInicializacionEsquema, idEstado, idCultivo, idVariedad);
			if(ce.size()>0){
				cuota = ce.get(0).getCuota();
				importeAutorizado = (cuota * volumenAutorizado);
				cuotaS = TextUtil.formateaNumeroComoCantidad(cuota);
				importeS = TextUtil.formateaNumeroComoCantidad(importeAutorizado);
			}
			
		}			
		
			
		
		return SUCCESS;
	}
	
	public String complementoPorAmpliacion(){
		lstDetAsigCAC = new ArrayList<DetalleAsignacionCartasAdhesion>();
		System.out.println("lstDetAsig"+lstDetAsigCAC.size());
		
		return SUCCESS;
	}
	
	public String recuperaVariedadByCultivoEdoAsigCA(){
		//Recupera los datos de la variedad por cultivo
		lstVariedad = iDAO.recuperaVariedadByInicilizacionEsquema(idInicializacionEsquema, idCultivo, idEstado);
		return SUCCESS;		
	}
	
	public String recuperaCultivoByEstadoAsigCA(){
		//Recupera los cultivos por estado e inicializacion
		System.out.println("idEstado "+idEstado);
		lstCultivo = iDAO.recuperaCultivoByInicilizacionEsquema(idInicializacionEsquema,idEstado);
		
		return SUCCESS;		
	}
		
	public String validarClaveBodega(){
		try{
			//Verifica que la claveBodega exista en la base de datos
			if(!(cDAO.consultaBodegas(claveBodega).size()>0)){
				errorClaveBodega = 1;
			}
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error al validar la clave de la bodega debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } 
		return SUCCESS;
	}
	
	
	public Double getVolumenApoyar() {
		return volumenApoyar;
	}

	public void setVolumenApoyar(Double volumenApoyar) {
		this.volumenApoyar = volumenApoyar;
	}

	public Double getImporteApoyar() {
		return importeApoyar;
	}

	public void setImporteApoyar(Double importeApoyar) {
		this.importeApoyar = importeApoyar;
	}

	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}
	
	public Date getFechaAcuseSI() {
		return fechaAcuseSI;
	}

	public void setFechaAcuseSI(Date fechaAcuseSI) {
		this.fechaAcuseSI = fechaAcuseSI;
	}

	public String getNumeroAuditor() {
		return numeroAuditor;
	}

	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}

	
	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	public int getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(int tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public int getIdCicloPrograma() {
		return idCicloPrograma;
	}

	public void setIdCicloPrograma(int idCicloPrograma) {
		this.idCicloPrograma = idCicloPrograma;
	}

	public String getFolioSI() {
		return folioSI;
	}

	public void setFolioSI(String folioSI) {
		this.folioSI = folioSI;
	}

	public Double getVolumenInscripcion() {
		return volumenInscripcion;
	}

	public void setVolumenInscripcion(Double volumenInscripcion) {
		this.volumenInscripcion = volumenInscripcion;
	}

	public Double getImporteInscripcion() {
		return importeInscripcion;
	}

	public void setImporteInscripcion(Double importeInscripcion) {
		this.importeInscripcion = importeInscripcion;
	}

	public File getDocSI() {
		return docSI;
	}

	public void setDocSI(File docSI) {
		this.docSI = docSI;
	}

	public String getDocSIFileName() {
		return docSIFileName;
	}

	public void setDocSIFileName(String docSIFileName) {
		this.docSIFileName = docSIFileName;
	}

	public Date getFechaDocSI() {
		return fechaDocSI;
	}

	public void setFechaDocSI(Date fechaDocSI) {
		this.fechaDocSI = fechaDocSI;
	}

	public File getDocOPA() {
		return docOPA;
	}

	public void setDocOPA(File docOPA) {
		this.docOPA = docOPA;
	}

	public String getDocOPAFileName() {
		return docOPAFileName;
	}

	public void setDocOPAFileName(String docOPAFileName) {
		this.docOPAFileName = docOPAFileName;
	}

	public Date getFechaDocOPA() {
		return fechaDocOPA;
	}

	public void setFechaDocOPA(Date fechaDocOPA) {
		this.fechaDocOPA = fechaDocOPA;
	}

	public Date getFechaAcuseOPA() {
		return fechaAcuseOPA;
	}

	public void setFechaAcuseOPA(Date fechaAcuseOPA) {
		this.fechaAcuseOPA = fechaAcuseOPA;
	}

	public String getNoOficioPA() {
		return noOficioPA;
	}

	public void setNoOficioPA(String noOficioPA) {
		this.noOficioPA = noOficioPA;
	}
	
	
	public File getDocOA() {
		return docOA;
	}

	public void setDocOA(File docOA) {
		this.docOA = docOA;
	}

	public String getDocOAFileName() {
		return docOAFileName;
	}

	public void setDocOAFileName(String docOAFileName) {
		this.docOAFileName = docOAFileName;
	}

	public Date getFechaDocOA() {
		return fechaDocOA;
	}

	public void setFechaDocOA(Date fechaDocOA) {
		this.fechaDocOA = fechaDocOA;
	}

	public Date getFechaAcuseOA() {
		return fechaAcuseOA;
	}

	public void setFechaAcuseOA(Date fechaAcuseOA) {
		this.fechaAcuseOA = fechaAcuseOA;
	}

	public String getNoOficioA() {
		return noOficioA;
	}

	public void setNoOficioA(String noOficioA) {
		this.noOficioA = noOficioA;
	}

	public File getDocOOA() {
		return docOOA;
	}

	public void setDocOOA(File docOOA) {
		this.docOOA = docOOA;
	}

	public String getDocOOAFileName() {
		return docOOAFileName;
	}

	public void setDocOOAFileName(String docOOAFileName) {
		this.docOOAFileName = docOOAFileName;
	}

	public Date getFechaDocOOA() {
		return fechaDocOOA;
	}

	public void setFechaDocOOA(Date fechaDocOOA) {
		this.fechaDocOOA = fechaDocOOA;
	}

	public Date getFechaAcuseOOA() {
		return fechaAcuseOOA;
	}

	public void setFechaAcuseOOA(Date fechaAcuseOOA) {
		this.fechaAcuseOOA = fechaAcuseOOA;
	}

	public String getNoOficioOA() {
		return noOficioOA;
	}

	public void setNoOficioOA(String noOficioOA) {
		this.noOficioOA = noOficioOA;
	}

	public File getDocOL() {
		return docOL;
	}

	public void setDocOL(File docOL) {
		this.docOL = docOL;
	}

	public String getDocOLFileName() {
		return docOLFileName;
	}

	public void setDocOLFileName(String docOLFileName) {
		this.docOLFileName = docOLFileName;
	}

	public Date getFechaDocLeg() {
		return fechaDocLeg;
	}

	public void setFechaDocLeg(Date fechaDocLeg) {
		this.fechaDocLeg = fechaDocLeg;
	}

	public Date getFechaAcuseLeg() {
		return fechaAcuseLeg;
	}

	public void setFechaAcuseLeg(Date fechaAcuseLeg) {
		this.fechaAcuseLeg = fechaAcuseLeg;
	}

	public String getNoOficioLeg() {
		return noOficioLeg;
	}

	public void setNoOficioLeg(String noOficioLeg) {
		this.noOficioLeg = noOficioLeg;
	}

	
	public File getDocOD() {
		return docOD;
	}

	public void setDocOD(File docOD) {
		this.docOD = docOD;
	}

	public String getDocODFileName() {
		return docODFileName;
	}

	public void setDocODFileName(String docODFileName) {
		this.docODFileName = docODFileName;
	}

	public Date getFechaDocDic() {
		return fechaDocDic;
	}

	public void setFechaDocDic(Date fechaDocDic) {
		this.fechaDocDic = fechaDocDic;
	}

	public Date getFechaAcuseDic() {
		return fechaAcuseDic;
	}

	public void setFechaAcuseDic(Date fechaAcuseDic) {
		this.fechaAcuseDic = fechaAcuseDic;
	}

	public String getNoOficioDic() {
		return noOficioDic;
	}

	public void setNoOficioDic(String noOficioDic) {
		this.noOficioDic = noOficioDic;
	}

	public String getObsDictaminacion() {
		return obsDictaminacion;
	}

	public void setObsDictaminacion(String obsDictaminacion) {
		this.obsDictaminacion = obsDictaminacion;
	}

	public Double getImporteDisponible() {
		return importeDisponible;
	}

	public void setImporteDisponible(Double importeDisponible) {
		this.importeDisponible = importeDisponible;
	}

	
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Integer getNumCampos() {
		return numCampos;
	}

	public void setNumCampos(Integer numCampos) {
		this.numCampos = numCampos;
	}
			
	public List<CiclosProgramasV> getLstCiclosProgramasV() {
		return lstCiclosProgramasV;
	}

	public void setLstCiclosProgramasV(List<CiclosProgramasV> lstCiclosProgramasV) {
		this.lstCiclosProgramasV = lstCiclosProgramasV;
	}

	public List<Comprador> getLstCompradores() {
		return lstCompradores;
	}

	public void setLstCompradores(List<Comprador> lstCompradores) {
		this.lstCompradores = lstCompradores;
	}

	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public long getIdSI() {
		return idSI;
	}

	public void setIdSI(long idSI) {
		this.idSI = idSI;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}

	public List<Estado> getLstEstado() {
		return lstEstado;
	}

	public void setLstEstado(List<Estado> lstEstado) {
		this.lstEstado = lstEstado;
	}

	public List<AuditoresExternos> getLstAuditoresExternos() {
		return lstAuditoresExternos;
	}

	public void setLstAuditoresExternos(List<AuditoresExternos> lstAuditoresExternos) {
		this.lstAuditoresExternos = lstAuditoresExternos;
	}

	public List<CuotasEsquemaV> getLstCuotasEsqV() {
		return lstCuotasEsqV;
	}

	public void setLstCuotasEsqV(List<CuotasEsquemaV> lstCuotasEsqV) {
		this.lstCuotasEsqV = lstCuotasEsqV;
	}

	
	public List<Componente> getLstComponente() {
		return lstComponente;
	}

	public void setLstComponente(List<Componente> lstComponente) {
		this.lstComponente = lstComponente;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public Integer[] getCapEstado() {
		return capEstado;
	}

	public void setCapEstado(Integer[] capEstado) {
		this.capEstado = capEstado;
	}

	public Double[] getCapVolumen() {
		return capVolumen;
	}

	public void setCapVolumen(Double[] capVolumen) {
		this.capVolumen = capVolumen;
	}

	public Integer[] getCapCultivo() {
		return capCultivo;
	}

	public void setCapCultivo(Integer[] capCultivo) {
		this.capCultivo = capCultivo;
	}

	public Double[] getCapImporte() {
		return capImporte;
	}

	public void setCapImporte(Double[] capImporte) {
		this.capImporte = capImporte;
	}

	public Integer[] getCapVariedad() {
		return capVariedad;
	}

	public void setCapVariedad(Integer[] capVariedad) {
		this.capVariedad = capVariedad;
	}

	public String[] getNomCultivo() {
		return nomCultivo;
	}

	public void setNomCultivo(String[] nomCultivo) {
		this.nomCultivo = nomCultivo;
	}

	public String[] getNomVariedad() {
		return nomVariedad;
	}

	public void setNomVariedad(String[] nomVariedad) {
		this.nomVariedad = nomVariedad;
	}

	public String[] getNomEstado() {
		return nomEstado;
	}

	public void setNomEstado(String[] nomEstado) {
		this.nomEstado = nomEstado;
	}

	public Double[] getCapCuota() {
		return capCuota;
	}

	public void setCapCuota(Double[] capCuota) {
		this.capCuota = capCuota;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public SolicitudInscripcion getSi() {
		return si;
	}

	public void setSi(SolicitudInscripcion si) {
		this.si = si;
	}

	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public int getIdCriterioPago() {
		return idCriterioPago;
	}

	public void setIdCriterioPago(int idCriterioPago) {
		this.idCriterioPago = idCriterioPago;
	}

	public int getAcreditacionJuridica() {
		return acreditacionJuridica;
	}

	public void setAcreditacionJuridica(int acreditacionJuridica) {
		this.acreditacionJuridica = acreditacionJuridica;
	}
	
	public int getAcreditacionSI() {
		return acreditacionSI;
	}

	public void setAcreditacionSI(int acreditacionSI) {
		this.acreditacionSI = acreditacionSI;
	}

	public int getAcreditacionDictamen() {
		return acreditacionDictamen;
	}

	public void setAcreditacionDictamen(int acreditacionDictamen) {
		this.acreditacionDictamen = acreditacionDictamen;
	}

	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	public int getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(int idVariedad) {
		this.idVariedad = idVariedad;
	}

	public Double getVolumenDisponible() {
		return volumenDisponible;
	}

	public void setVolumenDisponible(Double volumenDisponible) {
		this.volumenDisponible = volumenDisponible;
	}

	public ProgramasV getPv() {
		return pv;
	}

	public void setPv(ProgramasV pv) {
		this.pv = pv;
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public List<ProgramaCultivo> getLstCultivoByPrograma() {
		return lstCultivoByPrograma;
	}

	public void setLstCultivoByPrograma(List<ProgramaCultivo> lstCultivoByPrograma) {
		this.lstCultivoByPrograma = lstCultivoByPrograma;
	}
	
	//Render de pagina
	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public String getNombreCultivo() {
		return nombreCultivo;
	}

	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public String getNombreCiclo() {
		return nombreCiclo;
	}

	public void setNombreCiclo(String nombreCiclo) {
		this.nombreCiclo = nombreCiclo;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion.trim();
	}

	public Integer getPeriodoDOFSI() {
		return periodoDOFSI;
	}

	public void setPeriodoDOFSI(Integer periodoDOFSI) {
		this.periodoDOFSI = periodoDOFSI;
	}

	public int getFueraTiempo() {
		return fueraTiempo;
	}

	public void setFueraTiempo(int fueraTiempo) {
		this.fueraTiempo = fueraTiempo;
	}

	public Date getFechaPublicacionDOF() {
		return fechaPublicacionDOF;
	}

	public void setFechaPublicacionDOF(Date fechaPublicacionDOF) {
		this.fechaPublicacionDOF = fechaPublicacionDOF;
	}

	public Date getFechaLimiteSI() {
		return fechaLimiteSI;
	}

	public String getDescripcionEstatus() {
		return descripcionEstatus;
	}

	public void setDescripcionEstatus(String descripcionEstatus) {
		this.descripcionEstatus = descripcionEstatus;
	}

	public void setFechaLimiteSI(Date fechaLimiteSI) {
		this.fechaLimiteSI = fechaLimiteSI;
	}

	public List<SolicitudInscripcionV> getLstSolInsV() {
		return lstSolInsV;
	}

	public void setLstSolInsV(List<SolicitudInscripcionV> lstSolInsV) {
		this.lstSolInsV = lstSolInsV;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public File getDocCartaA() {
		return docCartaA;
	}

	public void setDocCartaA(File docCartaA) {
		this.docCartaA = docCartaA;
	}

	public String getDocCartaAFileName() {
		return docCartaAFileName;
	}

	public void setDocCartaAFileName(String docCartaAFileName) {
		this.docCartaAFileName = docCartaAFileName;
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

	public List<AsignacionCartasAdhesionV> getLstAsigCA() {
		return lstAsigCA;
	}

	public void setLstAsigCA(List<AsignacionCartasAdhesionV> lstAsigCA) {
		this.lstAsigCA = lstAsigCA;
	}

	public int getEstatusSI() {
		return estatusSI;
	}

	public void setEstatusSI(int estatusSI) {
		this.estatusSI = estatusSI;
	}

	public Date getFechaFirmaCA() {
		return fechaFirmaCA;
	}

	public void setFechaFirmaCA(Date fechaFirmaCA) {
		this.fechaFirmaCA = fechaFirmaCA;
	}

	public long getIdInicializacionEsquema() {
		return idInicializacionEsquema;
	}

	public void setIdInicializacionEsquema(long idInicializacionEsquema) {
		this.idInicializacionEsquema = idInicializacionEsquema;
	}

	
	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public String getRutaSolicitud() {
		return rutaSolicitud;
	}

	public void setRutaSolicitud(String rutaSolicitud) {
		this.rutaSolicitud = rutaSolicitud;
	}

	public String getNombreOficio() {
		return nombreOficio;
	}

	public void setNombreOficio(String nombreOficio) {
		this.nombreOficio = nombreOficio;
	}
	
	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador.trim();
	}

	public String getObsSolInscripcion() {
		return obsSolInscripcion;
	}

	public void setObsSolInscripcion(String obsSolInscripcion) {
		this.obsSolInscripcion = obsSolInscripcion;
	}
	
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public String getRutaMarcaAgua() {
		return rutaMarcaAgua;
	}

	public void setRutaMarcaAgua(String rutaMarcaAgua) {
		this.rutaMarcaAgua = rutaMarcaAgua;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getRutaAserca() {
		return rutaAserca;
	}

	public void setRutaAserca(String rutaAserca) {
		this.rutaAserca = rutaAserca;
	}


	public int getCartaAdhesionSistema() {
		return cartaAdhesionSistema;
	}

	public void setCartaAdhesionSistema(int cartaAdhesionSistema) {
		this.cartaAdhesionSistema = cartaAdhesionSistema;
	}

	public int getDatosCompradorCompleto() {
		return datosCompradorCompleto;
	}

	public void setDatosCompradorCompleto(int datosCompradorCompleto) {
		this.datosCompradorCompleto = datosCompradorCompleto;
	}

	public int getErrorFolioSI() {
		return errorFolioSI;
	}

	public void setErrorFolioSI(int errorFolioSI) {
		this.errorFolioSI = errorFolioSI;
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}

	public int getFechaPublicacionDOFInt() {
		return fechaPublicacionDOFInt;
	}

	public void setFechaPublicacionDOFInt(int fechaPublicacionDOFInt) {
		this.fechaPublicacionDOFInt = fechaPublicacionDOFInt;
	}
	
	public int getFechaDOCSIInt() {
		return fechaDOCSIInt;
	}

	public void setFechaDOCSIInt(int fechaDOCSIInt) {
		this.fechaDOCSIInt = fechaDOCSIInt;
	}

	public int getVolumenXCultivoVariedad() {
		return volumenXCultivoVariedad;
	}

	public void setVolumenXCultivoVariedad(int volumenXCultivoVariedad) {
		this.volumenXCultivoVariedad = volumenXCultivoVariedad;
	}

	public Double getVolumenXCV() {
		return volumenXCV;
	}

	public void setVolumenXCV(Double volumenXCV) {
		this.volumenXCV = volumenXCV;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ProgramasV> getLstProgramasV() {
		return lstProgramasV;
	}

	public void setLstProgramasV(List<ProgramasV> lstProgramasV) {
		this.lstProgramasV = lstProgramasV;
	}

	public List<Variedad> getLstVariedad() {
		return lstVariedad;
	}

	public void setLstVariedad(List<Variedad> lstVariedad) {
		this.lstVariedad = lstVariedad;
	}

	public Date getFechaAcuseFCA() {
		return fechaAcuseFCA;
	}

	public void setFechaAcuseFCA(Date fechaAcuseFCA) {
		this.fechaAcuseFCA = fechaAcuseFCA;
	}

	public String getAcronimoCA() {
		return acronimoCA;
	}

	public void setAcronimoCA(String acronimoCA) {
		this.acronimoCA = acronimoCA;
	}

	public int getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(int tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	public File getDocASI() {
		return docASI;
	}

	public void setDocASI(File docASI) {
		this.docASI = docASI;
	}

	public String getDocASIFileName() {
		return docASIFileName;
	}

	public void setDocASIFileName(String docASIFileName) {
		this.docASIFileName = docASIFileName;
	}

	public Date getFechaDocASI() {
		return fechaDocASI;
	}

	public void setFechaDocASI(Date fechaDocASI) {
		this.fechaDocASI = fechaDocASI;
	}

	public Date getFechaAcuseASI() {
		return fechaAcuseASI;
	}

	public void setFechaAcuseASI(Date fechaAcuseASI) {
		this.fechaAcuseASI = fechaAcuseASI;
	}

	public String getNoOficioASI() {
		return noOficioASI;
	}

	public void setNoOficioASI(String noOficioASI) {
		this.noOficioASI = noOficioASI;
	}

	public List<AlcanceSolicitudInscripcion> getLstaAlcanceSI() {
		return lstaAlcanceSI;
	}

	public void setLstaAlcanceSI(List<AlcanceSolicitudInscripcion> lstaAlcanceSI) {
		this.lstaAlcanceSI = lstaAlcanceSI;
	}

	public int getHabilitaAccion() {
		return habilitaAccion;
	}

	public void setHabilitaAccion(int habilitaAccion) {
		this.habilitaAccion = habilitaAccion;
	}

	public Integer getNumCamposComp() {
		return numCamposComp;
	}

	public void setNumCamposComp(Integer numCamposComp) {
		this.numCamposComp = numCamposComp;
	}

	public boolean isComplementoPorampliacionChk() {
		return complementoPorampliacionChk;
	}

	public void setComplementoPorampliacionChk(boolean complementoPorampliacionChk) {
		this.complementoPorampliacionChk = complementoPorampliacionChk;
	}

	public File getDocAmpliacionCA() {
		return docAmpliacionCA;
	}

	public void setDocAmpliacionCA(File docAmpliacionCA) {
		this.docAmpliacionCA = docAmpliacionCA;
	}

	public String getDocAmpliacionCAFileName() {
		return docAmpliacionCAFileName;
	}

	public void setDocAmpliacionCAFileName(String docAmpliacionCAFileName) {
		this.docAmpliacionCAFileName = docAmpliacionCAFileName;
	}

	public Date getFechaFirmaCAComplemento() {
		return fechaFirmaCAComplemento;
	}

	public void setFechaFirmaCAComplemento(Date fechaFirmaCAComplemento) {
		this.fechaFirmaCAComplemento = fechaFirmaCAComplemento;
	}

	public List<DetalleAsignacionCartasAdhesion> getLstDetAsigCAO() {
		return lstDetAsigCAO;
	}

	public void setLstDetAsigCAO(List<DetalleAsignacionCartasAdhesion> lstDetAsigCAO) {
		this.lstDetAsigCAO = lstDetAsigCAO;
	}

	public List<DetalleAsignacionCartasAdhesion> getLstDetAsigCAC() {
		return lstDetAsigCAC;
	}

	public void setLstDetAsigCAC(List<DetalleAsignacionCartasAdhesion> lstDetAsigCAC) {
		this.lstDetAsigCAC = lstDetAsigCAC;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getClaveBodega() {
		return claveBodega;
	}

	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}

	public int getErrorClaveBodega() {
		return errorClaveBodega;
	}

	public void setErrorClaveBodega(int errorClaveBodega) {
		this.errorClaveBodega = errorClaveBodega;
	}

	public String getCuotaS() {
		return cuotaS;
	}

	public void setCuotaS(String cuotaS) {
		this.cuotaS = cuotaS;
	}

	public String getImporteS() {
		return importeS;
	}

	public void setImporteS(String importeS) {
		this.importeS = importeS;
	}

	public String getVolumenXCVS() {
		return volumenXCVS;
	}

	public void setVolumenXCVS(String volumenXCVS) {
		this.volumenXCVS = volumenXCVS;
	}

	public String[] getCapBodega() {
		return capBodega;
	}

	public void setCapBodega(String[] capBodega) {
		this.capBodega = capBodega;
	}

	public double getTotalVolSI() {
		return totalVolSI;
	}

	public void setTotalVolSI(double totalVolSI) {
		this.totalVolSI = totalVolSI;
	}

	public double getTotalVolAutCA() {
		return totalVolAutCA;
	}

	public void setTotalVolAutCA(double totalVolAutCA) {
		this.totalVolAutCA = totalVolAutCA;
	}

	public double getTotalImpAutCA() {
		return totalImpAutCA;
	}

	public void setTotalImpAutCA(double totalImpAutCA) {
		this.totalImpAutCA = totalImpAutCA;
	}

	public List<RepresentanteCompradorV> getLstRepresentanteV() {
		return lstRepresentanteV;
	}

	public void setLstRepresentanteV(List<RepresentanteCompradorV> lstRepresentanteV) {
		this.lstRepresentanteV = lstRepresentanteV;
	}

	public List<PagosV> getLstPagosV() {
		return lstPagosV;
	}

	public void setLstPagosV(List<PagosV> lstPagosV) {
		this.lstPagosV = lstPagosV;
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
}
