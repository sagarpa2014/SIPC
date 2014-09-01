package mx.gob.comer.sipc.action.solicitudpago;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Especialista;
import mx.gob.comer.sipc.domain.transaccionales.AsignacionCartaEspecialista;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.OficioCartaAdhesionSolicitudPago;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;


import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AsignacionCartaAdhesionAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	/**** BD ****/
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private InscripcionDAO iDAO = new InscripcionDAO();
	/***LISTAS**/
	private List<Programa> lstProgramas;
	private List<Especialista> lstEspecialista;
	private List<AsignacionCartaEspecialista> lstAsigCAEspecialista;
	private List<CartaAdhesion> lstCartaAdhesion;
	private List<AsignacionCAaEspecialistaV> lstAsignacionCAaEspecialistaV;
	/***VARIABLES DE FORMULARIO***/
	private int registrar;
	private Integer numCamposACA;
	private int idPrograma;
	private File docCA;
	private String docCAFileName;
	private Date fechaDocCA;
	private Date fechaAcuseCA;
	private String noOficioCA;
	private String[] capCartaAdhesion;
	private String[] capCartaAdhesionCopy;
	private Integer[] capEspecialista;
	private int count;
	private String folioCartaAdhesion;
	private Date fechaFirmaCartaAdhesion;
	private String ext;
	private String msjOk;
	private int idEspecialista;
	private boolean bandera;
	private long idOficioCASP;
	
	private OficioCartaAdhesionSolicitudPago ocasp;
	private List<AsignacionCAaEspecialistaV> lstAsigCAEspecialistaV;
	
	
	
	/***METODOS**/
	public String listarAsignacionCAEspecialista(){
		try{
			//Carga la lista de especialista
			lstEspecialista = cDAO.consultaEspecialista(0);
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listarAsignacionCAEspecialista  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listarAsignacionCAEspecialista  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String ejecutaBusquedaAsignacionCAaEspecialista(){
		try{			
			lstAsignacionCAaEspecialistaV = spDAO.consultaCAaEspecialistaV(noOficioCA, idEspecialista, folioCartaAdhesion);
			bandera = true;
			listarAsignacionCAEspecialista();
			noOficioCA = null;
			folioCartaAdhesion = null;
			idEspecialista = -1;
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en consultarAsignacionCAEspecialista  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en consultarAsignacionCAEspecialista  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String getDetalleAsigCAaEspecialista(){
		try{
			OficioCartaAdhesionSolicitudPago ocasp = spDAO.consultaOficioCartaAdhesionSolicitudPago( idOficioCASP).get(0);
			idPrograma = ocasp.getIdPrograma();
			fechaDocCA = ocasp.getFechaDocCA();
			fechaAcuseCA = ocasp.getFechaAcuseCA();
			noOficioCA = ocasp.getNoOficioCA();
			//Consigue ruta del programa
			Programa programa = cDAO.consultaPrograma(idPrograma).get(0);
			String rutaSolicitudPago = programa.getRutaDocumentos()+"SolicitudPago/"+ocasp.getIdOficioCASP()+"/";
			docCAFileName = rutaSolicitudPago+ocasp.getNomArchivoCA();
			capAsignacionSolPago();
			agregarCamposAsignacionCA();
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en getDetalleAsigCAaEspecialista  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en getDetalleAsigCAaEspecialista  debido a: "+e.getMessage());
		}
		return SUCCESS;		

	}
	
	public String capAsignacionSolPago(){
		try{
			if(registrar == 0){
				lstProgramas = spDAO.consultaProgramasParaSolPago(7);	
			}else{
				lstProgramas = cDAO.consultaPrograma(idPrograma);
			}	
		
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}
	
	public String agregarCamposAsignacionCA(){
		try{
			if(registrar == 0){
				lstAsigCAEspecialista = new ArrayList<AsignacionCartaEspecialista>();
				for(int i=1; i<=numCamposACA; i++){
					lstAsigCAEspecialista.add(new AsignacionCartaEspecialista());
				}
				lstCartaAdhesion = spDAO.consultaCartaAdhesion(idPrograma, 7, null);								
			}else if (registrar == 2){
				//lstAsigCAEspecialista = spDAO.consultaAsignacionCAEspecialista(idOficioCASP, null, -1);
				setLstAsigCAEspecialistaV(spDAO.consultaCAaEspecialistaV(idOficioCASP));
				lstCartaAdhesion = spDAO.consultaCartaAdhesion(idPrograma, 0, null);
			}else if(registrar == 3){
				
				lstCartaAdhesion = spDAO.consultaCartaAdhesion(idPrograma, 0, null);
				setLstAsigCAEspecialistaV(spDAO.consultaCAaEspecialistaV(idOficioCASP));
			}
			
			lstEspecialista = cDAO.consultaEspecialista(0);
			if(registrar == 0){
				numCamposACA = lstAsigCAEspecialista.size();
			}else if( registrar == 2|| registrar == 3){
				numCamposACA  = lstAsigCAEspecialistaV.size();
			}
			
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en agregarCamposAsignacionCA  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en agregarCamposAsignacionCA  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}	
	
	public String registraAsignacionCartaAdhesion(){
		String nombreArchivo  = "";
		try{
			session = ActionContext.getContext().getSession();
			if(registrar == 0){
				//Guarda en la base de datos
				ocasp = new OficioCartaAdhesionSolicitudPago();
				ocasp.setIdPrograma(idPrograma);			
				ocasp.setFechaDocCA(fechaDocCA);
				ocasp.setFechaAcuseCA(fechaAcuseCA);
				ocasp.setNoOficioCA(noOficioCA);
				ocasp.setUsuarioCreacion((Integer)session.get("idUsuario"));
				ocasp.setFechaCreacion(new Date());
				ocasp.setAsignacionCartaEspecialista(new HashSet<AsignacionCartaEspecialista>());
				for(int i = 0; i < capCartaAdhesion.length; i++){
					AsignacionCartaEspecialista ace = new AsignacionCartaEspecialista();
					ace.setFolioCartaAdhesion(capCartaAdhesion[i]);
					ace.setIdEspecialista(capEspecialista[i]);
					ocasp.getAsignacionCartaEspecialista().add(ace);
					//Actualizar el campo estatus de la carta Adhesion
					CartaAdhesion ca = spDAO.consultaCartaAdhesion(capCartaAdhesion[i]).get(0);
					ca.setEstatus(3);
					cDAO.guardaObjeto(ca);				
				}
				ocasp = (OficioCartaAdhesionSolicitudPago) cDAO.guardaObjeto(ocasp);
				//Recupera programa para verificar la ruta del programa
				Programa programa = cDAO.consultaPrograma(idPrograma).get(0);
				String rutaSolicitudPago = programa.getRutaDocumentos()+"SolicitudPago/"+ocasp.getIdOficioCASP()+"/";
				Utilerias.crearDirectorio(rutaSolicitudPago);
				ext = docCAFileName.toLowerCase().substring(docCAFileName.lastIndexOf(".") );
				nombreArchivo = "OCASP"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext; //Archivo de oficio de carta de adhesion
				ocasp.setNomArchivoCA(nombreArchivo);
				docCAFileName = rutaSolicitudPago+nombreArchivo;
				Utilerias.cargarArchivo(rutaSolicitudPago, nombreArchivo, docCA);
				cDAO.guardaObjeto(ocasp);
				idOficioCASP = ocasp.getIdOficioCASP();
			}else if(registrar == 3){
				for(int i = 0; i < capEspecialista.length; i++){
					//Actualiza el folio de la carta de adhesion
					AsignacionCartaEspecialista asigCartaEspecialista = spDAO.consultaAsignacionCAEspecialista(idOficioCASP, capCartaAdhesionCopy[i], 0).get(0);
					asigCartaEspecialista.setIdEspecialista(capEspecialista[i]);
					cDAO.guardaObjeto(asigCartaEspecialista);
				}
				getDetalleAsigCAaEspecialista();
			}
			
			registrar = 2;		
			capAsignacionSolPago();
			agregarCamposAsignacionCA();		
			msjOk = "Se guardó satisfactoriamente el registro";
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraAsignacionCartaAdhesion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraAsignacionCartaAdhesion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;		
	}
	
	public String recuperaFechaCartaAdhesion(){
		try{
			//Recupera la fecha de firma de carta de adhesión para validacion en el formulario de asignacón de carta de adhesión
			fechaFirmaCartaAdhesion = iDAO.consultaSolicitudInscripcion(folioCartaAdhesion).get(0).getFechaFirmaCa();
			
			 
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraAsignacionCartaAdhesion  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraAsignacionCartaAdhesion  debido a: "+e.getMessage());
		}
		return SUCCESS;		
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
	/***DATOS DEL FORMULARIO**/
	public List<AsignacionCartaEspecialista> getLstAsigCAEspecialista() {
		return lstAsigCAEspecialista;
	}

	public void setLstAsigCAEspecialista(
			List<AsignacionCartaEspecialista> lstAsigCAEspecialista) {
		this.lstAsigCAEspecialista = lstAsigCAEspecialista;
	}

	public List<CartaAdhesion> getLstCartaAdhesion() {
		return lstCartaAdhesion;
	}

	public void setLstCartaAdhesion(List<CartaAdhesion> lstCartaAdhesion) {
		this.lstCartaAdhesion = lstCartaAdhesion;
	}
	
	public List<AsignacionCAaEspecialistaV> getLstAsignacionCAaEspecialistaV() {
		return lstAsignacionCAaEspecialistaV;
	}

	public void setLstAsignacionCAaEspecialistaV(
			List<AsignacionCAaEspecialistaV> lstAsignacionCAaEspecialistaV) {
		this.lstAsignacionCAaEspecialistaV = lstAsignacionCAaEspecialistaV;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public Integer getNumCamposACA() {
		return numCamposACA;
	}

	public void setNumCamposACA(Integer numCamposACA) {
		this.numCamposACA = numCamposACA;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public File getDocCA() {
		return docCA;
	}

	public void setDocCA(File docCA) {
		this.docCA = docCA;
	}

	public String getDocCAFileName() {
		return docCAFileName;
	}

	public void setDocCAFileName(String docCAFileName) {
		this.docCAFileName = docCAFileName;
	}

	public Date getFechaDocCA() {
		return fechaDocCA;
	}

	public void setFechaDocCA(Date fechaDocCA) {
		this.fechaDocCA = fechaDocCA;
	}

	public Date getFechaAcuseCA() {
		return fechaAcuseCA;
	}

	public void setFechaAcuseCA(Date fechaAcuseCA) {
		this.fechaAcuseCA = fechaAcuseCA;
	}

	public String getNoOficioCA() {
		return noOficioCA;
	}

	public void setNoOficioCA(String noOficioCA) {
		this.noOficioCA = noOficioCA;
	}

	public String[] getCapCartaAdhesion() {
		return capCartaAdhesion;
	}

	public void setCapCartaAdhesion(String[] capCartaAdhesion) {
		this.capCartaAdhesion = capCartaAdhesion;
	}

	public Integer[] getCapEspecialista() {
		return capEspecialista;
	}

	public void setCapEspecialista(Integer[] capEspecialista) {
		this.capEspecialista = capEspecialista;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	public Date getFechaFirmaCartaAdhesion() {
		return fechaFirmaCartaAdhesion;
	}

	public void setFechaFirmaCartaAdhesion(Date fechaFirmaCartaAdhesion) {
		this.fechaFirmaCartaAdhesion = fechaFirmaCartaAdhesion;
	}
	
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	
	public int getIdEspecialista() {
		return idEspecialista;
	}

	public void setIdEspecialista(int idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	
	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public long getIdOficioCASP() {
		return idOficioCASP;
	}

	public void setIdOficioCASP(long idOficioCASP) {
		this.idOficioCASP = idOficioCASP;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	/**
	 * @return the lstAsigCAEspecialistaV
	 */
	public List<AsignacionCAaEspecialistaV> getLstAsigCAEspecialistaV() {
		return lstAsigCAEspecialistaV;
	}

	/**
	 * @param lstAsigCAEspecialistaV the lstAsigCAEspecialistaV to set
	 */
	public void setLstAsigCAEspecialistaV(List<AsignacionCAaEspecialistaV> lstAsigCAEspecialistaV) {
		this.lstAsigCAEspecialistaV = lstAsigCAEspecialistaV;
	}

	/**
	 * @return the capCartaAdhesionCopy
	 */
	public String[] getCapCartaAdhesionCopy() {
		return capCartaAdhesionCopy;
	}

	/**
	 * @param capCartaAdhesionCopy the capCartaAdhesionCopy to set
	 */
	public void setCapCartaAdhesionCopy(String[] capCartaAdhesionCopy) {
		this.capCartaAdhesionCopy = capCartaAdhesionCopy;
	}
	
}
