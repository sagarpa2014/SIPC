package mx.gob.comer.sipc.catalogos.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.domain.AuditoresExternos;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Expediente;
import mx.gob.comer.sipc.domain.ExpedienteAuditor;
import mx.gob.comer.sipc.domain.Municipios;
import mx.gob.comer.sipc.domain.Localidades;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AuditorExpedientesV;
import mx.gob.comer.sipc.vistas.domain.AuditoresExternosV;

import mx.gob.comer.sipc.dao.UtileriasDAO; 

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AuditoresAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private InscripcionDAO iDAO = new InscripcionDAO ();
	private List<AuditoresExternos> lstAuditoresExternos;
	private List<Estado> lstEstados;
	private List<Municipios> lstMunicipios;
	private List<Localidades> lstLocalidades;	
	private List<Expediente> lstExpedientes;
	private String numeroAuditor;
	private String nombre;
	private String despacho;
	private String telefonos;
	private Integer idEstado;
	private Integer claveLocalidad;
	private Integer claveMunicipio;
	private String colonia;
	private String calle;
	private Integer codigoPostal;
	private int estatus;
	private AuditoresExternos auditor;
	private String msjOk;
	private AuditoresExternosV auditoresExternosV;
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private int editar;
	private String rutaAuditor;
	private String docFileName;
	private String nombreArchivo;
	private int idExpediente;
	private long idExpedienteAuditor;
	private File doc;
	private List<AuditorExpedientesV> lstAuditorExpedientesV;
	private Integer numeroRegistroDespacho;
	private int errorNumeroAuditor;
	private String msjError;
	private String motivo;



	public String verExpedientesAuditor(){
		try{			
			auditor = cDAO.consultaAuditoresbyNumAuditor(numeroAuditor).get(0);
			rutaAuditor =  utileriasDAO.getParametros("RUTA_AUDITORES");
			if(auditor.getRutaAuditor()!=null){
				rutaAuditor += auditor.getRutaAuditor();
			}else{
				Utilerias.crearDirectorio(rutaAuditor+auditor.getNumeroAuditor());
				//guardar la ruta comprador
				auditor.setRutaAuditor(auditor.getNumeroAuditor()+"/");
				cDAO.guardaObjeto(auditor);
			}
			
			//Consigue expediente del auditor
			lstExpedientes = cDAO.consultaExpediente(0, "A");
			
			
			lstAuditorExpedientesV = cDAO.consultaAuditorExpedientesV(0, numeroAuditor, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String busquedaAuditores(){
		try{
			lstAuditoresExternos = cDAO.consultaAuditores("", "",-1);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String ejecutaBusquedaAuditor(){
		try{
			lstAuditoresExternos = cDAO.consultaAuditores(numeroAuditor, nombre,-1);
		}catch(JDBCException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String agregarAuditor(){
		try{
			lstEstados = cDAO.consultaEstado(0);
			//Consigue expediente del auditor
			lstExpedientes = cDAO.consultaExpediente(0, "A");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	public String registrarAuditor(){
		try{
			auditor = new AuditoresExternos();
			/*Recupera los datos del Auditor*/
			if(editar==3){
				auditor = cDAO.consultaAuditoresbyNumAuditor(numeroAuditor).get(0);
			}
			
			auditor.setNumeroAuditor(numeroAuditor);
			auditor.setNombre(nombre.toUpperCase());
			
			if(numeroRegistroDespacho == null || numeroRegistroDespacho ==0){
				auditor.setNumeroRegistroDespacho(null);
			}else{
				auditor.setNumeroRegistroDespacho(numeroRegistroDespacho);
			}
			
			if(despacho == null || despacho.isEmpty()){
				auditor.setDespacho(null);
			}else{
				auditor.setDespacho(despacho.toUpperCase());
			}
			
			if(telefonos == null || telefonos.isEmpty()){
				auditor.setTelefonos(null);
			}else{
				auditor.setTelefonos(telefonos);
			}
			
			if(idEstado == null  || idEstado == -1){
				auditor.setIdEstado(null);
			}else{
				auditor.setIdEstado(idEstado);
			}
			
			if(claveMunicipio == null || claveMunicipio == -1){
				auditor.setClaveMunicipio(null);
			}else{
				auditor.setClaveMunicipio(claveMunicipio);
			}
			
			if(claveLocalidad == null || claveLocalidad == -1){
				auditor.setClaveLocalidad(null);
			}else{
				auditor.setClaveLocalidad(claveLocalidad);
			}
			
			if(colonia == null || colonia.isEmpty()){
				auditor.setColonia(null);
			}else{
				auditor.setColonia(colonia);
			}
			
			if(calle == null || calle.isEmpty()){
				auditor.setCalle(null);
			}else{
				auditor.setCalle(calle);
			}
			
			if(codigoPostal == null || codigoPostal == 0){
				auditor.setCodigoPostal(null);
			}else{
				auditor.setCodigoPostal(codigoPostal);
			}
			
			if(editar == 3){
				auditor.setUsuarioModificacion((Integer) session.get("idUsuario"));
				auditor.setFechaModificacion(new Date());
			}else{
				auditor.setUsuarioCreacion((Integer) session.get("idUsuario"));
				auditor.setFechaAlta(new Date());
			}
			
			auditor.setEstatus(1);
			
			auditor = (AuditoresExternos) cDAO.guardaObjeto(auditor);
			if(editar !=3){
				auditor.setRutaAuditor(auditor.getNumeroAuditor()+"/");
				rutaAuditor  = utileriasDAO.getParametros("RUTA_AUDITORES");
				Utilerias.crearDirectorio(rutaAuditor+auditor.getNumeroAuditor()+"/");
				cDAO.guardaObjeto(auditor);
			}
			
			lstAuditoresExternos = cDAO.consultaAuditores("", "",-1);
			
			if (editar == 3){
				msjOk = "El Auditor se editó correctamente";			
			}else{		
				msjOk = "El Auditor se agregó correctamente";
			}
			numeroAuditor = "";
			nombre = "";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	
	public String validarNumeroAuditor(){
		if(cDAO.consultaAuditoresbyNumAuditor(numeroAuditor).size()>0){
			errorNumeroAuditor = 1;
			msjError = "El número de Auditor ya se encuentra registrado, por favor verifique";
							
		}
		return SUCCESS;
		
	}


	public String recuperaDatosAuditor(){
		try{
			/*Recupera los datos del Auditor*/
			auditoresExternosV = cDAO.consultaAuditoresExternosV(numeroAuditor).get(0);
			lstEstados = cDAO.consultaEstado(0);
			if (auditoresExternosV.getIdEstado() != null){
				lstMunicipios= cDAO.consultaMunicipiosbyEdo(auditoresExternosV.getIdEstado(), 0, 0, null);
			}
			
			if (auditoresExternosV.getIdEstado() !=null && auditoresExternosV.getClaveMunicipio() != null){
				lstLocalidades= cDAO.consultaLocalidadByMunicipio(0, auditoresExternosV.getIdEstado(), auditoresExternosV.getClaveMunicipio(), 0, null);
			}
				
			numeroAuditor = auditoresExternosV.getNumeroAuditor();
			nombre = auditoresExternosV.getNombre();
			numeroRegistroDespacho = auditoresExternosV.getNumeroRegistroDespacho();
			despacho = auditoresExternosV.getDespacho();
			if(auditoresExternosV.getIdEstado() != null){
				idEstado = auditoresExternosV.getIdEstado();
			}
			if(auditoresExternosV.getClaveMunicipio() != null){
				claveMunicipio = auditoresExternosV.getClaveMunicipio();
			}
			if(auditoresExternosV.getClaveLocalidad()!=null){
				claveLocalidad = auditoresExternosV.getClaveLocalidad();
			}
			telefonos = auditoresExternosV.getTelefonos();
			colonia = auditoresExternosV.getColonia();
			calle = auditoresExternosV.getCalle();
			codigoPostal = auditoresExternosV.getCodigoPostal();
			
		}catch (JDBCException e) {	
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al guardar el auditor debido a:" + e.getCause());
			addActionError("Ocurrio un error inesperado al guardar el auditor, favor de reportar al administrador");
		}catch (Exception e) {	
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al guardar el auditor debido a:" + e.getMessage());
			addActionError("Ocurrio un error inesperado al guardar el auditor, favor de reportar al administrador");
		}
		return SUCCESS;
	}


	public String recuperaMunicipioByEstado(){
		try{
			lstMunicipios= cDAO.consultaMunicipiosbyEdo(idEstado, 0, 0, null);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String recuperaLocalidadesByMunicipio(){
		try{
			lstLocalidades= cDAO.consultaLocalidadByMunicipio(0, idEstado, claveMunicipio, 0, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String detallesAuditor(){
		try{
			/*Recupera los datos del Auditor*/
			auditoresExternosV = cDAO.consultaAuditoresExternosV(numeroAuditor).get(0);
					//, nombre, estado, municipio, localidad, calle, telefonos, estatus, codigoPostal
			
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String cargarExpedienteAuditor(){
		try {
			auditor = cDAO.consultaAuditoresbyNumAuditor(numeroAuditor).get(0);
			rutaAuditor =  utileriasDAO.getParametros("RUTA_AUDITORES");
			rutaAuditor += auditor.getRutaAuditor();
			String ext = docFileName.toLowerCase().substring( docFileName.lastIndexOf(".") );
			nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;
			if(idExpedienteAuditor != 0){//Si ya existe el expediente, actualiza registro
				List<ExpedienteAuditor> lstExpedienteAuditor = cDAO.consultaExpedienteAuditor(idExpedienteAuditor, null, 0);
				ExpedienteAuditor e = lstExpedienteAuditor.get(0);
				
				//borrando el archivo existente
				File f = new File(rutaAuditor+e.getRutaExpediente());
				if(f.exists()){
					if(!f.delete()){
						AppLogger.error("errores","Error al eliminar: "+e.getRutaExpediente());
					}else{
						AppLogger.info("app","Se borro archivo: "+e.getRutaExpediente());
					}
				}
				e.setRutaExpediente(nombreArchivo);
				cDAO.guardaObjeto(e);
			}else{
				//guarda expediente auditor
				ExpedienteAuditor ea = new ExpedienteAuditor();
				ea.setRutaExpediente(nombreArchivo);
				ea.setNumeroAuditor(auditor.getNumeroAuditor());
				ea.setIdExpediente(idExpediente);
				cDAO.guardaObjeto(ea);
			}
			Utilerias.cargarArchivo(rutaAuditor, nombreArchivo, doc);
		} catch (JDBCException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			verExpedientesAuditor();	
		}
		return SUCCESS;
	}	
	
	public String cambiarEstatusAuditor(){
		try{
			auditor = cDAO.consultaAuditoresbyNumAuditor(numeroAuditor).get(0);
			if (auditor.getEstatus() == 1){
				auditor.setEstatus(0);
				auditor.setMotivoInhabilitado(motivo);
			}else if (auditor.getEstatus() == 0){
				auditor.setEstatus(1);
			}
			cDAO.guardaObjeto(auditor);
			msjOk="Se actualizó el estatus correctamente";
			busquedaAuditores();
			nombre = "";
			numeroAuditor="";
			
		}catch(JDBCException e) {	
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public List<AuditoresExternos> getLstAuditoresExternos() {
		return lstAuditoresExternos;
	}

	public void setLstAuditoresExternos(List<AuditoresExternos> lstAuditoresExternos) {
		this.lstAuditoresExternos = lstAuditoresExternos;
	}

	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}

	public List<Municipios> getLstMunicipios() {
		return lstMunicipios;
	}

	public void setLstMunicipios(List<Municipios> lstMunicipios) {
		this.lstMunicipios = lstMunicipios;
	}
	
	public List<Localidades> getLstLocalidades() {
		return lstLocalidades;
	}

	public void setLstLocalidades(List<Localidades> lstLocalidades) {
		this.lstLocalidades = lstLocalidades;
	}

	public List<Expediente> getLstExpedientes() {
		return lstExpedientes;
	}

	public void setLstExpedientes(List<Expediente> lstExpedientes) {
		this.lstExpedientes = lstExpedientes;
	}

	public String getNumeroAuditor() {
		return numeroAuditor;
	}

	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Integer getClaveMunicipio() {
		return claveMunicipio;
	}

	public void setClaveMunicipio(Integer claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}
	
	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	
	public Integer getClaveLocalidad() {
		return claveLocalidad;
	}

	public void setClaveLocalidad(Integer claveLocalidad) {
		this.claveLocalidad = claveLocalidad;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public AuditoresExternosV getAuditoresExternosV() {
		return auditoresExternosV;
	}

	public void setAuditoresExternosV(AuditoresExternosV auditoresExternosV) {
		this.auditoresExternosV = auditoresExternosV;
	}

	public String getRutaAuditor() {
		return rutaAuditor;
	}

	public void setRutaAuditor(String rutaAuditor) {
		this.rutaAuditor = rutaAuditor;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public int getIdExpediente() {
		return idExpediente;
	}

	public void setIdExpediente(int idExpediente) {
		this.idExpediente = idExpediente;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public List<AuditorExpedientesV> getLstAuditorExpedientesV() {
		return lstAuditorExpedientesV;
	}

	public void setLstAuditorExpedientesV(
			List<AuditorExpedientesV> lstAuditorExpedientesV) {
		this.lstAuditorExpedientesV = lstAuditorExpedientesV;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public int getEditar() {
		return editar;
	}

	public void setEditar(int editar) {
		this.editar = editar;
	}

	public CatalogosDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(CatalogosDAO cDAO) {
		this.cDAO = cDAO;
	}

	public InscripcionDAO getiDAO() {
		return iDAO;
	}

	public void setiDAO(InscripcionDAO iDAO) {
		this.iDAO = iDAO;
	}

	public AuditoresExternos getAuditor() {
		return auditor;
	}

	public void setAuditor(AuditoresExternos auditor) {
		this.auditor = auditor;
	}

	public UtileriasDAO getUtileriasDAO() {
		return utileriasDAO;
	}

	public void setUtileriasDAO(UtileriasDAO utileriasDAO) {
		this.utileriasDAO = utileriasDAO;
	}

	public long getIdExpedienteAuditor() {
		return idExpedienteAuditor;
	}

	public void setIdExpedienteAuditor(long idExpedienteAuditor) {
		this.idExpedienteAuditor = idExpedienteAuditor;
	}
	
	public Integer getNumeroRegistroDespacho() {
		return numeroRegistroDespacho;
	}

	public void setNumeroRegistroDespacho(Integer numeroRegistroDespacho) {
		this.numeroRegistroDespacho = numeroRegistroDespacho;
	}

	
	public int getErrorNumeroAuditor() {
		return errorNumeroAuditor;
	}

	public void setErrorNumeroAuditor(int errorNumeroAuditor) {
		this.errorNumeroAuditor = errorNumeroAuditor;
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
