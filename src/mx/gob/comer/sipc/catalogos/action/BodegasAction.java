package mx.gob.comer.sipc.catalogos.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Localidades;
import mx.gob.comer.sipc.domain.Municipios;
import mx.gob.comer.sipc.domain.Usuarios;
import mx.gob.comer.sipc.domain.catalogos.BodegaUso;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.domain.catalogos.BodegasCambios;
import mx.gob.comer.sipc.domain.catalogos.CapacidadesBodegas;
import mx.gob.comer.sipc.domain.catalogos.CapacidadesBodegasCambios;
import mx.gob.comer.sipc.domain.catalogos.Ejido;
import mx.gob.comer.sipc.domain.catalogos.RepresentanteLegal;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.BodegasV;
import mx.gob.comer.sipc.vistas.domain.CadersV;
import mx.gob.comer.sipc.vistas.domain.RepresentanteLegalV;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BodegasAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	
	private String nombreBodega;
	
	private List<Estado> lstEstado;
	private List<BodegasV> lstBodegasV;
	private BodegasV bodegasV;
	private boolean bandera;
	private int errorSistema;
	private String msjOk;
	private int registrar; // 0 = agregar nueva bodega; 1 = editar bodega; 2 = consultar bodega
	private CadersV caderV;
	private List<Municipios> lstMunicipios;
	private List<Municipios> lstMunicipiosUbicacionBodegas;
	private List<Ejido> lstEjido;
	//ubicacion de la bodega
	private String claveBodega;
	private Date fechaRegistro;
	private long fechaActualL;
	private int idEstado;
	private int ddr;
	private int cader;
	private int claveMunicipio;
	private int ejido;
	private Double latitudGrados;
	private Double latitudMinutos;
	private Double latitudSegundos;
	private Double longitudGrados;
	private Double longitudMinutos;
	private Double longitudSegundos;
	//Datos de la bodega
	private Integer clasificacion;
	private String claveUso1;
	private String claveUso2;
	private String claveUso3;
	private String rfcBodega;
	private String nombreRazonSocial;
	private String domicilioFiscal;
	private String calleDomFiscal;	
	private String numeroDomFiscal;
	private int idEstadoDomFiscal;
	private int claveMunicipioDomFis;
	private int claveLocalidadDomFis;
	private String cpDomFiscal;
	private String telefonoDomFis;
	private String faxDomFis;
	private String personaAutorizada1;
	private String puestoPersonaAutorizada1;
	private String personaAutorizada2;
	private String puestoPersonaAutorizada2;
	//CAPACIDAD BODEBA
	private Double capacidadBodega;
	private Double tejaban;
	private Double intemperie;
	private int noSilos;
	private Double capacidadSilos;
	private Double capacidadTechada;
	private Double totalAlmacenamiento;
	private int numRampas;
	private Double capacidadRampas;
	private int numSecadoras;
	private Double capacidadSecado;
	private int numCargas;
	private Double capacidadCarga;
	private String piso;
	private Double capacidadPiso;
	private String camionera;
	private Double capacidadCamionera;
	private String  ffcc;
	private Double capacidadFfcc;
	private String  espuelaFFCC;
	private Double capacidadEspuela;
	private Date fechaCalibracion;
	private String  equipoAnalisis;
	private String  aplicaNormasCalidad;
	private String observacionCapacidadBodega;
	
	private List<BodegaUso> lstBodegaUso;
	private List<Estado> lstEstados;
	private List<Localidades> lstLocalidades;
	private List<CadersV> ltsCaderV;
	private int errorClaveBodega;
	private String rfcRepresentanteLegal;
	private RepresentanteLegalV representanteLegal;
	private File doc; 
	private String docFileName;
	private String nombreArchivoCroquis;
	private String rutaCroquisBodega;
	private int errorRepresentanteLegal;
	private Integer idUsuario;
	private Bodegas bodega;
	private BodegasCambios bodegaCambios;
	private CapacidadesBodegasCambios capacidadBodegaCambios;
	
	private Usuarios usuario;
	
		
	public String busquedaBodegas(){
		try{
			session = ActionContext.getContext().getSession();
			idUsuario = (Integer) session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);
			
			//Carga el catalogo de estados
			if (usuario.getArea()!= null && !usuario.getArea().isEmpty()){
				setLstEstado(cDAO.consultaEstado(usuario.getArea()));
			} else {
				setLstEstado(cDAO.consultaEstado(0));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String ejecutaBusquedaBodegas(){
		try{
			session = ActionContext.getContext().getSession();
			idUsuario = (Integer) session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);
			
			setLstBodegasV(cDAO.consultaBodegasV(claveBodega, idEstado, nombreBodega));
			bandera = true;
			//Carga el catalogo de estados
			if (usuario.getArea()!= null && !usuario.getArea().isEmpty()){
				setLstEstado(cDAO.consultaEstado(usuario.getArea()));
			} else {
				setLstEstado(cDAO.consultaEstado(0));
			}

			claveBodega = null;
			nombreBodega = null;
			idEstado = -1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String detalleBodegas(){
		try{
			session = ActionContext.getContext().getSession();
			idUsuario = (Integer) session.get("idUsuario");
			usuario = cDAO.consultaUsuarios(idUsuario, null, null).get(0);
			
			String fechaActualS = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
			fechaActualL = Long.parseLong(fechaActualS);
			if(registrar !=0){
				setBodegasV(cDAO.consultaBodegasV(claveBodega).get(0));
				descomponerClaveBodega(claveBodega);
				caderV = new CadersV();
				caderV.setNombreEstado(bodegasV.getNombreEstado());
				caderV.setNombreDDR(bodegasV.getNombreDdr());
				caderV.setNombreCader(bodegasV.getNombreCader());
				//recupera los datos del representante legal 
				if(bodegasV.getIdRepresentante()!=null){
					List<RepresentanteLegalV> lstRepresentateLegal = cDAO.consultaRepresentanteLegalV(bodegasV.getIdRepresentante(), null);
					if(lstRepresentateLegal.size()>0){
						representanteLegal = lstRepresentateLegal.get(0);
					}
				}
				
				lstMunicipiosUbicacionBodegas = cDAO.consultaMunicipiosbyEdo(idEstado, 0, 0, "");
				if(bodegasV.getIdEstadoDomFiscal()!=null){
					lstMunicipios = cDAO.consultaMunicipiosbyEdo(bodegasV.getIdEstadoDomFiscal(), 0, 0, "");
				}else{
					lstMunicipios = cDAO.consultaMunicipiosbyEdo(0, 0, 0, "");
				}
				if(bodegasV.getIdEstadoDomFiscal()!=null && bodegasV.getClaveMunicipioDomFiscal() != null){
					lstLocalidades = cDAO.consultaLocalidadByMunicipio(0, bodegasV.getIdEstadoDomFiscal(), bodegasV.getClaveMunicipioDomFiscal(), 0, "");
				}else{
					lstLocalidades  = new  ArrayList<Localidades>();
				}				
				lstEjido = cDAO.consultaEjido(0, idEstado);
			}else{
				lstMunicipiosUbicacionBodegas = new ArrayList<Municipios>();
				lstMunicipios = new ArrayList<Municipios>();
				lstLocalidades = new ArrayList<Localidades>();
				lstEjido = new ArrayList<Ejido>();
			}
			
			lstBodegaUso = cDAO.consultaBodegaUsuo(null);
			if (usuario.getArea()!= null && !usuario.getArea().isEmpty()){
				lstEstados = cDAO.consultaEstado(0);
			} else {
				lstEstados = cDAO.consultaEstado(usuario.getArea());
			}
		}catch(Exception e){    
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	
	private void descomponerClaveBodega(String claveBodega2) {
		idEstado = Integer.parseInt(claveBodega.substring(1, 3));
		ddr = Integer.parseInt(claveBodega.substring(3, 6));
		cader =Integer.parseInt(claveBodega.substring(6, 8));
	}


	public String registraBodega(){	
		session = ActionContext.getContext().getSession();
		idUsuario = (Integer) session.get("idPerfil");
		//Valida que el croquis no exceda de 3MB
		
		if(registrar != 0){
			bodega = cDAO.consultaBodegas(claveBodega).get(0);
		}
		if(doc!=null){
			if(Utilerias.verificarTamanioArchivo(doc)){
				errorSistema = 1;
				addActionError("Lo sentimos los archivos cargados no debe exceder de 3 MegaBytes");
				return SUCCESS;
			}
			//Recupera la ruta raiz de la bodega
			rutaCroquisBodega = uDAO.getParametros("RUTA_BODEGAS");
			rutaCroquisBodega = rutaCroquisBodega+claveBodega+"/";
			Utilerias.crearDirectorio(rutaCroquisBodega);
			nombreArchivoCroquis = recuperaNomArchivoYCargaArchivo(rutaCroquisBodega, doc, docFileName, "");
			if(registrar == 1){
				if( bodega.getNombreArchivoCroquis()!=null && !bodega.getNombreArchivoCroquis().isEmpty()){
					try {
						Utilerias.borrarArchivo(bodega.getRutaCroquisBodega()+bodega.getNombreArchivoCroquis());
					} catch (Exception e) {
						addActionError("Error al borrar el archivo que se sustituye");
						e.printStackTrace();
					}
				}
			}
			
		}
				
		
		//Guarda los datos de la bodega en la tabla bodegas
		guardarDatosEnBodega();
		guardarDatosCapacidadBodega();
		
		registrar = 2;
		detalleBodegas();
		msjOk = "Se guardó satisfactoriamente el registro";
		
		return SUCCESS;
	}
	
	private void guardarDatosEnBodega() {
	
		if(registrar == 0){
			bodega = new Bodegas();
			bodega.setFechaCaptura(new Date());
			bodega.setUsuario("NO");
			bodega.setUsuarioRegistro(idUsuario);
		}else{
			guardarDatosEnBodegaCambios(bodega);
			bodega.setFechaModificacion(new Date());
			bodega.setUsuarioActualizo(idUsuario);
		}	
		bodega.setClaveBodega(claveBodega);
		bodega.setFechaRegistro(fechaRegistro);
		
		bodega.setIdEstado(Integer.parseInt(claveBodega.substring(1, 3)));
		bodega.setDdr(Integer.parseInt(claveBodega.substring(3, 6)));
		bodega.setCader(Integer.parseInt(claveBodega.substring(6, 8)));
		bodega.setSecuenciaBodega(Integer.parseInt(claveBodega.substring(8, 10)));
		bodega.setMunicipio(claveMunicipio);
		bodega.setEjido(ejido);
		bodega.setLatitudGrados(latitudGrados);
		bodega.setLatitudMinutos(latitudMinutos);
		bodega.setLatitudSegundos(latitudSegundos);
		bodega.setLongitudGrados(longitudGrados);
		bodega.setLongitudMinutos(longitudMinutos);
		bodega.setLongitudSegundos(longitudSegundos);
		bodega.setClasificacion(clasificacion.doubleValue());
		bodega.setUso1(claveUso1);
		if(!claveUso2.equals("-1")){
			bodega.setUso2(claveUso2);
		}
		if(!claveUso3.equals("-1")){
			bodega.setUso3(claveUso3);
		}		
		bodega.setClaveMunicipioDomFiscal(claveMunicipioDomFis);
		bodega.setIdEstadoDomFiscal(idEstadoDomFiscal);
		bodega.setClaveLocalidad(claveLocalidadDomFis);
		bodega.setRfcBodega(rfcBodega);
		bodega.setNombre(nombreRazonSocial);
		bodega.setDomicilioFiscal(domicilioFiscal);
		bodega.setCalle(calleDomFiscal);
		bodega.setNumero(numeroDomFiscal);
		bodega.setCodigoPostal(cpDomFiscal);
		bodega.setTelefono(telefonoDomFis);
		bodega.setFax(faxDomFis);
		bodega.setPersonaAutorizada1(personaAutorizada1);
		bodega.setPuestoPersonaAutorizada1(puestoPersonaAutorizada1);
		bodega.setPersonaAutorizada2(personaAutorizada2);
		bodega.setPuestoPersonaAutorizada2(puestoPersonaAutorizada2);
		bodega.setStatus("HAB");
		
		
		if(rutaCroquisBodega!=null && !rutaCroquisBodega.isEmpty()){
			bodega.setRutaCroquisBodega(rutaCroquisBodega);
		}
		if(nombreArchivoCroquis!=null && !nombreArchivoCroquis.isEmpty()){
			bodega.setNombreArchivoCroquis(nombreArchivoCroquis);
		}
		
		//recupera representante por rfc 
		RepresentanteLegal rl = cDAO.consultaRepresentantebyRfc(rfcRepresentanteLegal).get(0);
		bodega.setIdRepresentante(rl.getIdRepresentante());
		cDAO.guardaObjeto(bodega);
	}

	private void guardarDatosEnBodegaCambios(Bodegas pBodega) {

		bodegaCambios = new BodegasCambios();
		
		bodegaCambios.setFechaCaptura(pBodega.getFechaCaptura());
		bodegaCambios.setUsuario(pBodega.getUsuario());
		bodegaCambios.setUsuarioRegistro(pBodega.getUsuarioRegistro());
		bodegaCambios.setFechaModificacion(pBodega.getFechaModificacion());
		bodegaCambios.setUsuarioActualizo(pBodega.getUsuarioActualizo());
		bodegaCambios.setClaveBodega(pBodega.getClaveBodega());
		bodegaCambios.setFechaRegistro(pBodega.getFechaRegistro());
		bodegaCambios.setIdEstado(pBodega.getIdEstado());
		bodegaCambios.setDdr(pBodega.getDdr());
		bodegaCambios.setCader(pBodega.getCader());
		bodegaCambios.setSecuenciaBodega(pBodega.getSecuenciaBodega());
		bodegaCambios.setMunicipio(pBodega.getMunicipio());
		bodegaCambios.setEjido(pBodega.getEjido());
		bodegaCambios.setLatitudGrados(pBodega.getLatitudGrados());
		bodegaCambios.setLatitudMinutos(pBodega.getLatitudMinutos());
		bodegaCambios.setLatitudSegundos(pBodega.getLatitudSegundos());
		bodegaCambios.setLongitudGrados(pBodega.getLongitudGrados());
		bodegaCambios.setLongitudMinutos(pBodega.getLongitudMinutos());
		bodegaCambios.setLongitudSegundos(pBodega.getLongitudSegundos());
		bodegaCambios.setClasificacion(pBodega.getClasificacion());
		bodegaCambios.setUso1(pBodega.getUso1());
//		if(!claveUso2.equals("-1")){
			bodegaCambios.setUso2(pBodega.getUso2());
//		}
//		if(!claveUso3.equals("-1")){
			bodegaCambios.setUso3(pBodega.getUso3());
//		}		
		bodegaCambios.setClaveMunicipioDomFiscal(pBodega.getClaveMunicipioDomFiscal());
		bodegaCambios.setIdEstadoDomFiscal(pBodega.getIdEstadoDomFiscal());
		bodegaCambios.setClaveLocalidad(pBodega.getClaveLocalidad());
		bodegaCambios.setRfcBodega(pBodega.getRfcBodega());
		bodegaCambios.setNombre(pBodega.getNombre());
		bodegaCambios.setDomicilioFiscal(pBodega.getDomicilioFiscal());
		bodegaCambios.setCalle(pBodega.getCalle());
		bodegaCambios.setNumero(pBodega.getNumero());
		bodegaCambios.setCodigoPostal(pBodega.getCodigoPostal());
		bodegaCambios.setTelefono(pBodega.getTelefono());
		bodegaCambios.setFax(pBodega.getFax());
		bodegaCambios.setPersonaAutorizada1(pBodega.getPersonaAutorizada1());
		bodegaCambios.setPuestoPersonaAutorizada1(pBodega.getPuestoPersonaAutorizada1());
		bodegaCambios.setPersonaAutorizada2(pBodega.getPersonaAutorizada2());
		bodegaCambios.setPuestoPersonaAutorizada2(pBodega.getPuestoPersonaAutorizada2());
		bodegaCambios.setStatus(pBodega.getStatus());
		
		
//		if(rutaCroquisBodega!=null && !rutaCroquisbodegaCambios.isEmpty()){
			bodegaCambios.setRutaCroquisBodega(pBodega.getRutaCroquisBodega());
//		}
//		if(nombreArchivoCroquis!=null && !nombreArchivoCroquis.isEmpty()){
			bodegaCambios.setNombreArchivoCroquis(pBodega.getNombreArchivoCroquis());
//		}
		
		bodegaCambios.setIdRepresentante(pBodega.getIdRepresentante());
		cDAO.guardaObjeto(bodegaCambios);
	}
	

	private void guardarDatosCapacidadBodega() {
		CapacidadesBodegas cb = null;
		if(registrar == 0){
			 cb = new CapacidadesBodegas();
			 cb.setClaveBodega(claveBodega);
		}else{
			//recupera la capacidad de bodega
			cb = cDAO.consultaCapacidadBodega(claveBodega).get(0);
			guardarDatosCapacidadBodegaCambios(cb);
		}
		
		cb.setCapacidadBodega(capacidadBodega);
		cb.setCapacidadTechada(capacidadTechada);
		cb.setCapacidadSilos(capacidadSilos);
		cb.setCapacidadIntemperie(intemperie);
		cb.setNoSilos(noSilos);
		cb.setTotalAlmacenamiento(totalAlmacenamiento);
		cb.setNumRampas(numRampas);
		cb.setCapacidadRampas(capacidadRampas);
		cb.setNumSecadoras(numSecadoras);
		cb.setCapacidadSecado(capacidadSecado);
		cb.setNumCargas(numCargas);
		cb.setCapacidadCargas(capacidadCarga);
		cb.setPiso(piso);
		cb.setCapacidadPiso(capacidadPiso);
		cb.setCamionera(camionera);
		cb.setCapacidadCamionera(capacidadCamionera);
		cb.setFfcc(ffcc);
		cb.setCapacidadFfcc(capacidadFfcc);
		cb.setEspuelaFfcc(espuelaFFCC);
		cb.setCapacidadEspuela(capacidadEspuela);
		cb.setFechaCalibracion(fechaCalibracion);
		cb.setEquipoAnalisis(equipoAnalisis);
		cb.setAplicaNormasCalidad(aplicaNormasCalidad);
		cb.setObservaciones(observacionCapacidadBodega);
		cDAO.guardaObjeto(cb);
	}
	

	private void guardarDatosCapacidadBodegaCambios(CapacidadesBodegas pCapBodegas) {
		capacidadBodegaCambios = new CapacidadesBodegasCambios();
		capacidadBodegaCambios.setClaveBodega(pCapBodegas.getClaveBodega());
		
		capacidadBodegaCambios.setCapacidadBodega(pCapBodegas.getCapacidadBodega());
		capacidadBodegaCambios.setCapacidadTechada(pCapBodegas.getCapacidadTechada());
		capacidadBodegaCambios.setCapacidadSilos(pCapBodegas.getCapacidadPiso());
		capacidadBodegaCambios.setCapacidadIntemperie(pCapBodegas.getCapacidadIntemperie());
		capacidadBodegaCambios.setNoSilos(pCapBodegas.getNoSilos());
		capacidadBodegaCambios.setTotalAlmacenamiento(pCapBodegas.getTotalAlmacenamiento());
		capacidadBodegaCambios.setNumRampas(pCapBodegas.getNumRampas());
		capacidadBodegaCambios.setCapacidadRampas(pCapBodegas.getCapacidadRampas());
		capacidadBodegaCambios.setNumSecadoras(pCapBodegas.getNumSecadoras());
		capacidadBodegaCambios.setCapacidadSecado(pCapBodegas.getCapacidadSecado());
		capacidadBodegaCambios.setNumCargas(pCapBodegas.getNumCargas());
		capacidadBodegaCambios.setCapacidadCargas(pCapBodegas.getCapacidadCargas());
		capacidadBodegaCambios.setPiso(pCapBodegas.getPiso());
		capacidadBodegaCambios.setCapacidadPiso(pCapBodegas.getCapacidadPiso());
		capacidadBodegaCambios.setCamionera(pCapBodegas.getCamionera());
		capacidadBodegaCambios.setCapacidadCamionera(pCapBodegas.getCapacidadCamionera());
		capacidadBodegaCambios.setFfcc(pCapBodegas.getFfcc());
		capacidadBodegaCambios.setCapacidadFfcc(pCapBodegas.getCapacidadFfcc());
		capacidadBodegaCambios.setEspuelaFfcc(pCapBodegas.getEspuelaFfcc());
		capacidadBodegaCambios.setCapacidadEspuela(pCapBodegas.getCapacidadEspuela());
		capacidadBodegaCambios.setFechaCalibracion(pCapBodegas.getFechaCalibracion());
		capacidadBodegaCambios.setEquipoAnalisis(pCapBodegas.getEquipoAnalisis());
		capacidadBodegaCambios.setAplicaNormasCalidad(pCapBodegas.getAplicaNormasCalidad());
		capacidadBodegaCambios.setObservaciones(pCapBodegas.getObservaciones());
		cDAO.guardaObjeto(capacidadBodegaCambios);
	}

	public String validaComposicionClaveBodega (){
		try {
			idEstado = Integer.parseInt(claveBodega.substring(1, 3)); 
			ddr = Integer.parseInt(claveBodega.substring(3, 6)); 
			cader = Integer.parseInt(claveBodega.substring(6, 8)); 
			ltsCaderV = cDAO.consultaCadersV(0, idEstado, ddr, cader);
			if(ltsCaderV.size()>0){
				caderV = ltsCaderV.get(0);
				//carga la lista de municipios relacionados al estado
				lstMunicipiosUbicacionBodegas = cDAO.consultaMunicipiosbyEdo(idEstado, 0, 0, null);
				lstEjido = cDAO.consultaEjido(0, idEstado);
				//Verifica que no se tenga registrado la clave de la bodega en el catalogo
				List<Bodegas> lstBodega = cDAO.consultaBodegas(claveBodega);
				if(lstBodega.size()>0){
					errorClaveBodega = 1;
				}
				if(errorClaveBodega == 0){
					//verifica la secuencia sea unico por entidad
				}
				
			}else{
				lstMunicipiosUbicacionBodegas = new ArrayList<Municipios>();
				lstEjido = new ArrayList<Ejido>();
				errorClaveBodega = 2;
				
			}
		} catch (JDBCException e) {
			e.printStackTrace();
			addActionError("Ocurrió un error inesperado, favor de informar al administrador");
			AppLogger.error("errores","Ocurrió un error en consultaSeguimiento debido a: " +e.getCause() );
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Ocurrió un error inesperado, favor de informar al administrador");
			AppLogger.error("errores","Ocurrió un error en consultaSeguimiento debido a: " +e.getCause());			
		}
		return SUCCESS;
	}	
	

	public String recuperaMunicipioByEdoBodegas(){
		try {
			
			lstMunicipios= cDAO.consultaMunicipiosbyEdo(idEstado, 0, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Ocurrió un error inesperado, favor de informar al administrador");
			AppLogger.error("errores","Ocurrió un error en consultaSeguimiento debido a: " +e.getCause());
		}
		return SUCCESS;
	}
	public String recuperaLocalidadesByMunicipioBodegas(){
		try{
			lstLocalidades= cDAO.consultaLocalidadByMunicipio(0, idEstado, claveMunicipio, 0, null);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	
	
	public String recuperaDatosRepresentanteBodega(){
		try{
			List<RepresentanteLegalV> lstRepresentateLegal = cDAO.consultaRepresentanteLegalV(0,  rfcRepresentanteLegal);
			if(lstRepresentateLegal.size()>0){
				representanteLegal = lstRepresentateLegal.get(0);
			}else{
				errorRepresentanteLegal =  1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return SUCCESS;
	}
	
	private String  recuperaNomArchivoYCargaArchivo(String ruta, File doc, String docFileName, String nombre){
		String nombreArchivo="", ext="";
		if(docFileName.lastIndexOf(".")>0){
			ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf(".") );
		}else{
			ext = "";
		}		
		nombreArchivo = nombre+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
		Utilerias.cargarArchivo(ruta, nombreArchivo, doc);
		return nombreArchivo;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public String getClaveBodega() {
		return claveBodega;
	}
	
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	public String getNombreBodega() {
		return nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}

	public Double getCapacidadBodega() {
		return capacidadBodega;
	}

	public void setCapacidadBodega(Double capacidadBodega) {
		this.capacidadBodega = capacidadBodega;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public List<Estado> getLstEstado() {
		return lstEstado;
	}
	
	public void setLstEstado(List<Estado> lstEstado) {
		this.lstEstado = lstEstado;
	}


	public List<BodegasV> getLstBodegasV() {
		return lstBodegasV;
	}


	public void setLstBodegasV(List<BodegasV> lstBodegasV) {
		this.lstBodegasV = lstBodegasV;
	}


	public BodegasV getBodegasV() {
		return bodegasV;
	}

	public void setBodegasV(BodegasV bodegasV) {
		this.bodegasV = bodegasV;
	}


	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
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

	public int getDdr() {
		return ddr;
	}

	public void setDdr(int ddr) {
		this.ddr = ddr;
	}

	public int getCader() {
		return cader;
	}

	public void setCader(int cader) {
		this.cader = cader;
	}

	public CadersV getCaderV() {
		return caderV;
	}

	public void setCaderV(CadersV caderV) {
		this.caderV = caderV;
	}

	public List<Municipios> getLstMunicipios() {
		return lstMunicipios;
	}

	public void setLstMunicipios(List<Municipios> lstMunicipios) {
		this.lstMunicipios = lstMunicipios;
	}

	public List<Ejido> getLstEjido() {
		return lstEjido;
	}

	public void setLstEjido(List<Ejido> lstEjido) {
		this.lstEjido = lstEjido;
	}


	public int getClaveMunicipio() {
		return claveMunicipio;
	}


	public void setClaveMunicipio(int claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}


	public int getEjido() {
		return ejido;
	}


	public void setEjido(int ejido) {
		this.ejido = ejido;
	}


	public Double getLatitudGrados() {
		return latitudGrados;
	}


	public void setLatitudGrados(Double latitudGrados) {
		this.latitudGrados = latitudGrados;
	}


	public Double getLatitudMinutos() {
		return latitudMinutos;
	}


	public void setLatitudMinutos(Double latitudMinutos) {
		this.latitudMinutos = latitudMinutos;
	}


	public Double getLatitudSegundos() {
		return latitudSegundos;
	}


	public void setLatitudSegundos(Double latitudSegundos) {
		this.latitudSegundos = latitudSegundos;
	}


	public Double getLongitudGrados() {
		return longitudGrados;
	}


	public void setLongitudGrados(Double longitudGrados) {
		this.longitudGrados = longitudGrados;
	}


	public Double getLongitudMinutos() {
		return longitudMinutos;
	}


	public void setLongitudMinutos(Double longitudMinutos) {
		this.longitudMinutos = longitudMinutos;
	}


	public Double getLongitudSegundos() {
		return longitudSegundos;
	}


	public void setLongitudSegundos(Double longitudSegundos) {
		this.longitudSegundos = longitudSegundos;
	}


	public List<BodegaUso> getLstBodegaUso() {
		return lstBodegaUso;
	}


	public void setLstBodegaUso(List<BodegaUso> lstBodegaUso) {
		this.lstBodegaUso = lstBodegaUso;
	}


	public List<Estado> getLstEstados() {
		return lstEstados;
	}


	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}


	public List<Localidades> getLstLocalidades() {
		return lstLocalidades;
	}


	public void setLstLocalidades(List<Localidades> lstLocalidades) {
		this.lstLocalidades = lstLocalidades;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}	
	
	public long getFechaActualL() {
		return fechaActualL;
	}

	public void setFechaActualL(long fechaActualL) {
		this.fechaActualL = fechaActualL;
	}

	public Integer getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Integer clasificacion) {
		this.clasificacion = clasificacion;
	}


	public String getClaveUso1() {
		return claveUso1;
	}


	public void setClaveUso1(String claveUso1) {
		this.claveUso1 = claveUso1;
	}


	public String getClaveUso2() {
		return claveUso2;
	}


	public void setClaveUso2(String claveUso2) {
		this.claveUso2 = claveUso2;
	}


	public String getClaveUso3() {
		return claveUso3;
	}


	public void setClaveUso3(String claveUso3) {
		this.claveUso3 = claveUso3;
	}


	public String getRfcBodega() {
		return rfcBodega;
	}


	public void setRfcBodega(String rfcBodega) {
		this.rfcBodega = rfcBodega;
	}


	public String getNombreRazonSocial() {
		return nombreRazonSocial;
	}


	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}


	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}


	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}


	public String getCalleDomFiscal() {
		return calleDomFiscal;
	}


	public void setCalleDomFiscal(String calleDomFiscal) {
		this.calleDomFiscal = calleDomFiscal;
	}


	public String getNumeroDomFiscal() {
		return numeroDomFiscal;
	}


	public void setNumeroDomFiscal(String numeroDomFiscal) {
		this.numeroDomFiscal = numeroDomFiscal;
	}


	public int getIdEstadoDomFiscal() {
		return idEstadoDomFiscal;
	}


	public void setIdEstadoDomFiscal(int idEstadoDomFiscal) {
		this.idEstadoDomFiscal = idEstadoDomFiscal;
	}


	public int getClaveMunicipioDomFis() {
		return claveMunicipioDomFis;
	}


	public void setClaveMunicipioDomFis(int claveMunicipioDomFis) {
		this.claveMunicipioDomFis = claveMunicipioDomFis;
	}


	public int getClaveLocalidadDomFis() {
		return claveLocalidadDomFis;
	}


	public void setClaveLocalidadDomFis(int claveLocalidadDomFis) {
		this.claveLocalidadDomFis = claveLocalidadDomFis;
	}
	
	public String getCpDomFiscal() {
		return cpDomFiscal;
	}
	
	public void setCpDomFiscal(String cpDomFiscal) {
		this.cpDomFiscal = cpDomFiscal;
	}

	public String getTelefonoDomFis() {
		return telefonoDomFis;
	}


	public void setTelefonoDomFis(String telefonoDomFis) {
		this.telefonoDomFis = telefonoDomFis;
	}


	public String getFaxDomFis() {
		return faxDomFis;
	}


	public void setFaxDomFis(String faxDomFis) {
		this.faxDomFis = faxDomFis;
	}


	public String getPersonaAutorizada1() {
		return personaAutorizada1;
	}


	public void setPersonaAutorizada1(String personaAutorizada1) {
		this.personaAutorizada1 = personaAutorizada1;
	}


	public String getPuestoPersonaAutorizada1() {
		return puestoPersonaAutorizada1;
	}


	public void setPuestoPersonaAutorizada1(String puestoPersonaAutorizada1) {
		this.puestoPersonaAutorizada1 = puestoPersonaAutorizada1;
	}


	public String getPersonaAutorizada2() {
		return personaAutorizada2;
	}

	public void setPersonaAutorizada2(String personaAutorizada2) {
		this.personaAutorizada2 = personaAutorizada2;
	}

	public String getPuestoPersonaAutorizada2() {
		return puestoPersonaAutorizada2;
	}
	public void setPuestoPersonaAutorizada2(String puestoPersonaAutorizada2) {
		this.puestoPersonaAutorizada2 = puestoPersonaAutorizada2;
	}


	public CatalogosDAO getcDAO() {
		return cDAO;
	}


	public void setcDAO(CatalogosDAO cDAO) {
		this.cDAO = cDAO;
	}


	public Double getIntemperie() {
		return intemperie;
	}


	public void setIntemperie(Double intemperie) {
		this.intemperie = intemperie;
	}


	public int getNoSilos() {
		return noSilos;
	}


	public void setNoSilos(int noSilos) {
		this.noSilos = noSilos;
	}


	public Double getCapacidadSilos() {
		return capacidadSilos;
	}


	public void setCapacidadSilos(Double capacidadSilos) {
		this.capacidadSilos = capacidadSilos;
	}


	public Double getCapacidadTechada() {
		return capacidadTechada;
	}


	public void setCapacidadTechada(Double capacidadTechada) {
		this.capacidadTechada = capacidadTechada;
	}


	public Double getTotalAlmacenamiento() {
		return totalAlmacenamiento;
	}


	public void setTotalAlmacenamiento(Double totalAlmacenamiento) {
		this.totalAlmacenamiento = totalAlmacenamiento;
	}


	public int getNumRampas() {
		return numRampas;
	}


	public void setNumRampas(int numRampas) {
		this.numRampas = numRampas;
	}


	public Double getCapacidadRampas() {
		return capacidadRampas;
	}


	public void setCapacidadRampas(Double capacidadRampas) {
		this.capacidadRampas = capacidadRampas;
	}


	public int getNumSecadoras() {
		return numSecadoras;
	}


	public void setNumSecadoras(int numSecadoras) {
		this.numSecadoras = numSecadoras;
	}


	public Double getCapacidadSecado() {
		return capacidadSecado;
	}


	public void setCapacidadSecado(Double capacidadSecado) {
		this.capacidadSecado = capacidadSecado;
	}


	public int getNumCargas() {
		return numCargas;
	}


	public void setNumCargas(int numCargas) {
		this.numCargas = numCargas;
	}


	public Double getCapacidadCarga() {
		return capacidadCarga;
	}


	public void setCapacidadCarga(Double capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}


	public String getPiso() {
		return piso;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public Double getCapacidadPiso() {
		return capacidadPiso;
	}


	public void setCapacidadPiso(Double capacidadPiso) {
		this.capacidadPiso = capacidadPiso;
	}


	public String getCamionera() {
		return camionera;
	}


	public void setCamionera(String camionera) {
		this.camionera = camionera;
	}


	public Double getCapacidadCamionera() {
		return capacidadCamionera;
	}


	public void setCapacidadCamionera(Double capacidadCamionera) {
		this.capacidadCamionera = capacidadCamionera;
	}


	public String getFfcc() {
		return ffcc;
	}


	public void setFfcc(String ffcc) {
		this.ffcc = ffcc;
	}


	public Double getCapacidadFfcc() {
		return capacidadFfcc;
	}


	public void setCapacidadFfcc(Double capacidadFfcc) {
		this.capacidadFfcc = capacidadFfcc;
	}


	public String getEspuelaFFCC() {
		return espuelaFFCC;
	}


	public void setEspuelaFFCC(String espuelaFFCC) {
		this.espuelaFFCC = espuelaFFCC;
	}


	public Double getCapacidadEspuela() {
		return capacidadEspuela;
	}


	public void setCapacidadEspuela(Double capacidadEspuela) {
		this.capacidadEspuela = capacidadEspuela;
	}


	public Date getFechaCalibracion() {
		return fechaCalibracion;
	}


	public void setFechaCalibracion(Date fechaCalibracion) {
		this.fechaCalibracion = fechaCalibracion;
	}


	public String getEquipoAnalisis() {
		return equipoAnalisis;
	}


	public void setEquipoAnalisis(String equipoAnalisis) {
		this.equipoAnalisis = equipoAnalisis;
	}


	public String getAplicaNormasCalidad() {
		return aplicaNormasCalidad;
	}

	public void setAplicaNormasCalidad(String aplicaNormasCalidad) {
		this.aplicaNormasCalidad = aplicaNormasCalidad;
	}

	public Double getTejaban() {
		return tejaban;
	}
	
	public void setTejaban(Double tejaban) {
		this.tejaban = tejaban;
	}

	public List<CadersV> getLtsCaderV() {
		return ltsCaderV;
	}

	public void setLtsCaderV(List<CadersV> ltsCaderV) {
		this.ltsCaderV = ltsCaderV;
	}

	public int getErrorClaveBodega() {
		return errorClaveBodega;
	}

	public void setErrorClaveBodega(int errorClaveBodega) {
		this.errorClaveBodega = errorClaveBodega;
	}

	public String getRfcRepresentanteLegal() {
		return rfcRepresentanteLegal;
	}

	public void setRfcRepresentanteLegal(String rfcRepresentanteLegal) {
		this.rfcRepresentanteLegal = rfcRepresentanteLegal;
	}

	public RepresentanteLegalV getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(RepresentanteLegalV representanteLegal) {
		this.representanteLegal = representanteLegal;
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

	public int getErrorRepresentanteLegal() {
		return errorRepresentanteLegal;
	}

	public void setErrorRepresentanteLegal(int errorRepresentanteLegal) {
		this.errorRepresentanteLegal = errorRepresentanteLegal;
	}


	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}


	public List<Municipios> getLstMunicipiosUbicacionBodegas() {
		return lstMunicipiosUbicacionBodegas;
	}


	public void setLstMunicipiosUbicacionBodegas(
			List<Municipios> lstMunicipiosUbicacionBodegas) {
		this.lstMunicipiosUbicacionBodegas = lstMunicipiosUbicacionBodegas;
	}


	public String getObservacionCapacidadBodega() {
		return observacionCapacidadBodega;
	}


	public void setObservacionCapacidadBodega(String observacionCapacidadBodega) {
		this.observacionCapacidadBodega = observacionCapacidadBodega;
	}


	public Usuarios getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}


	public BodegasCambios getBodegaCambios() {
		return bodegaCambios;
	}


	public void setBodegaCambios(BodegasCambios bodegaCambios) {
		this.bodegaCambios = bodegaCambios;
	}


	public CapacidadesBodegasCambios getCapacidadBodegaCambios() {
		return capacidadBodegaCambios;
	}


	public void setCapacidadBodegaCambios(
			CapacidadesBodegasCambios capacidadBodegaCambios) {
		this.capacidadBodegaCambios = capacidadBodegaCambios;
	}		
}
