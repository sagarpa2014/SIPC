package mx.gob.comer.sipc.catalogos.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Expediente;
import mx.gob.comer.sipc.domain.Municipios;
import mx.gob.comer.sipc.domain.Localidades;
import mx.gob.comer.sipc.domain.catalogos.ExpedienteRepresentante;
import mx.gob.comer.sipc.domain.catalogos.RepresentanteLegal;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.RepresentanteExpedientesV;
import mx.gob.comer.sipc.vistas.domain.RepresentanteLegalV;

import mx.gob.comer.sipc.dao.UtileriasDAO; 

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RepresentanteLegalAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private List<RepresentanteLegal> lstRepresentanteLegal;
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private int idRepresentante;
	private List<Estado> lstEstados;
	private List<Municipios> lstMunicipios;
	private List<Localidades> lstLocalidades;	
	private List<Expediente> lstExpedientes;
	private String nombre;
	private String rfc;
	private String curp;
	private Integer idEstado;
	private Integer claveMunicipio;
	private Integer claveLocalidad;
	private String colonia;
	private String calle;
	private String telefonos;
	private Integer estatus;
	private Integer codigoPostal;
	private String rutaRepLegal;
	private int editar;
	private RepresentanteLegal representante;
	private String msjOk;
	private List<RepresentanteExpedientesV> lstRepresentanteExpedientesV;
	private RepresentanteLegalV representanteLegalV;
	private String docFileName;
	private String nombreArchivo;
	private int idExpediente;
	private long idExpedienteRepresentante;
	private File doc;
	private int errorRfc;
	private String msjError;
	private int tipoSexo;
	private String sexo;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private Integer entidadNacimiento;
	private String numExterior;
	private String numInterior;
	private String fax;
	private String correoElectronico;
	
	public String busquedaRepLegal(){
		try{
			lstRepresentanteLegal = cDAO.consultaRepresentanteLegal(0,"","");
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String ejecutaBusquedaRepLegal(){
		try{
			lstRepresentanteLegal = cDAO.consultaRepresentanteLegal(0,nombre,rfc);
		}catch(JDBCException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String registrarRepLegal(){
		try{
			representante = new RepresentanteLegal();
			
			/*Recupera los datos del Representante*/
			if(editar==3){
				representante = cDAO.consultaRepresentanteLegal(idRepresentante,"","").get(0);
			}
			
			representante.setNombre(nombre.toUpperCase());
			representante.setRfc(rfc.toUpperCase());
			
			if (tipoSexo == 1){
				sexo = "M";
			}else{
				sexo = "F";
			}
			representante.setSexo(sexo);
			
			if(curp == null || curp.isEmpty()){
				representante.setCurp(null);
			}else{
				representante.setCurp(curp.toUpperCase());
			}
			
			if(apellidoPaterno == null || apellidoPaterno.isEmpty() || apellidoPaterno.equals("")){
				representante.setApellidoPaterno(null);
			}else{
				representante.setApellidoPaterno(apellidoPaterno.toUpperCase());
			}
			
			if(apellidoMaterno == null || apellidoMaterno.isEmpty() || apellidoMaterno.equals("")){
				representante.setApellidoMaterno(null);
			}else{
				representante.setApellidoMaterno(apellidoMaterno.toUpperCase());
			}
			
			if(fechaNacimiento == null){
				representante.setFechaNacimiento(null);
			}else{
				representante.setFechaNacimiento(fechaNacimiento);
			}
			
			if(entidadNacimiento == null || entidadNacimiento == -1){
				representante.setEntidadNacimiento(null);
			}else{
				representante.setEntidadNacimiento(entidadNacimiento);
			}
			
			if(idEstado == null || idEstado == -1){
				representante.setIdEstado(null);
			}else{
				representante.setIdEstado(idEstado);
			}
			
			if(claveMunicipio == null || claveMunicipio == -1 ){
				representante.setClaveMunicipio(null);
			}else{
				representante.setClaveMunicipio(claveMunicipio);
			}
			
			if(claveLocalidad == null || claveLocalidad == -1){
				representante.setClaveLocalidad(null);
			}else{
				representante.setClaveLocalidad(claveLocalidad);
			}
			
			if(calle == null || calle.isEmpty()){
				representante.setCalle(null);
			}else{
				representante.setCalle(calle.toUpperCase());
			}
			
			if(colonia == null || colonia.isEmpty()){
				representante.setColonia(null);
			}else{
				representante.setColonia(colonia.toUpperCase());
			}
			
			if(numExterior == null || numExterior.isEmpty()){
				representante.setNumExterior(null);
			}else{
				representante.setNumExterior(numExterior.toUpperCase());
			}
			
			if(numInterior == null || numInterior.isEmpty()){
				representante.setNumInterior(null);
			}else{
				representante.setNumExterior(numInterior.toUpperCase());
			}
	
			if(codigoPostal == null){
				representante.setCodigoPostal(null);
			}else{
				representante.setCodigoPostal(codigoPostal);
			}
			
			if(telefonos == null || telefonos.isEmpty()){
				representante.setTelefonos(null);
			}else{
				representante.setTelefonos(telefonos);
			}
			
			if(fax == null || fax.isEmpty()){
				representante.setFax(null);
			}else{
				representante.setFax(fax.toUpperCase());
			}
			
			if(correoElectronico == null || correoElectronico.isEmpty()){
				representante.setCorreoElectronico(null);
			}else{
				representante.setCorreoElectronico(correoElectronico.toLowerCase());
			}
			
			if(editar == 3){
				representante.setUsuarioModificacion((Integer) session.get("idUsuario"));
				representante.setFechaModificacion(new Date());
			}else{
				representante.setUsuarioCreacion((Integer) session.get("idUsuario"));
				representante.setFechaAlta(new Date());
			}
			
			representante.setEstatus(1);
			
			representante = (RepresentanteLegal) cDAO.guardaObjeto(representante);
			
			if(editar !=3){
				representante.setRutaRepLegal(representante.getIdRepresentante()+"/");
				rutaRepLegal  = utileriasDAO.getParametros("RUTA_REPRESENTANTES");
				Utilerias.crearDirectorio(rutaRepLegal+representante.getIdRepresentante()+"/");
				cDAO.guardaObjeto(representante);
			}
			
			lstRepresentanteLegal = cDAO.consultaRepresentanteLegal(-1,"","");
			
			if (editar == 3){
				msjOk = "El Representante Legal se editó correctamente";			
			}else{		
				msjOk = "El Representante Legal se agregó correctamente";
			}
			
			nombre = "";
			rfc = "";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String verExpedientesRepLegal(){
		try{			
			representante = cDAO.consultaRepresentanteLegal(idRepresentante,"","").get(0);
			rutaRepLegal =  utileriasDAO.getParametros("RUTA_REPRESENTANTES");
			if(representante.getRutaRepLegal()!=null){
				rutaRepLegal += representante.getRutaRepLegal();
			}else{
				Utilerias.crearDirectorio(rutaRepLegal+representante.getIdRepresentante());
				//guardar la ruta Representante
				representante.setRutaRepLegal(representante.getIdRepresentante()+"/");
				cDAO.guardaObjeto(representante);
			}
			
			//Consigue expediente del Representante
			lstExpedientes = cDAO.consultaExpediente(0, "RL");
			
			
			lstRepresentanteExpedientesV = cDAO.consultaRepresentanteExpedientesV(0, idRepresentante, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String agregarRepLegal(){
		try{
			lstEstados = cDAO.consultaEstado(0);
			//Consigue expediente del Representante
			lstExpedientes = cDAO.consultaExpediente(0, "RL");
		}catch(Exception e){
				e.printStackTrace();
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
	
	public String detallesRepresentante(){
		try{
			/*Recupera los datos del Representante*/
			representanteLegalV = cDAO.consultaRepresentanteLegalV(idRepresentante).get(0);
			if (representanteLegalV.getSexo() != null){
				if(representanteLegalV.getSexo().equals("M")){
					tipoSexo = 1;
				}else{
					tipoSexo = 2;
				}
			}else if (representanteLegalV.getSexo() == null){
				tipoSexo = 0;
			}
			
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String cargarExpedienteRepresentante(){
		try {
			representante = cDAO.consultaRepresentanteLegal(idRepresentante, "", "").get(0);
			rutaRepLegal =  utileriasDAO.getParametros("RUTA_REPRESENTANTES");
			rutaRepLegal += representante.getRutaRepLegal();
			String ext = docFileName.toLowerCase().substring( docFileName.lastIndexOf(".") );
			nombreArchivo = idExpediente+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;
			if(idExpedienteRepresentante != 0){//Si ya existe el expediente, actualiza registro
				List<ExpedienteRepresentante> lstExpedienteRepresentante = cDAO.consultaExpedienteRepresentante(idExpedienteRepresentante, 0, 0);
				ExpedienteRepresentante e = lstExpedienteRepresentante.get(0);
				
				//borrando el archivo existente
				File f = new File(rutaRepLegal+e.getRutaExpediente());
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
				//guarda expediente Representante
				ExpedienteRepresentante er = new ExpedienteRepresentante();
				er.setRutaExpediente(nombreArchivo);
				er.setIdRepresentante(representante.getIdRepresentante());
				er.setIdExpediente(idExpediente);
				cDAO.guardaObjeto(er);
			}
			Utilerias.cargarArchivo(rutaRepLegal, nombreArchivo, doc);
		} catch (JDBCException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			verExpedientesRepLegal();	
		}
		return SUCCESS;
	}	
	
	public String recuperaDatosRepresentante(){
		try{
			/*Recupera los datos del Representante*/
			representanteLegalV = cDAO.consultaRepresentanteLegalV(idRepresentante).get(0);
			representante = cDAO.consultaRepresentanteLegal(idRepresentante, "", "").get(0);
			lstEstados = cDAO.consultaEstado(0);
			if (representanteLegalV.getIdEstado() != null){
				lstMunicipios= cDAO.consultaMunicipiosbyEdo(representanteLegalV.getIdEstado(), 0, 0, null);
			}
			
			if (representanteLegalV.getIdEstado() !=null && representanteLegalV.getClaveMunicipio() != null){
				lstLocalidades= cDAO.consultaLocalidadByMunicipio(0, representanteLegalV.getIdEstado(), representanteLegalV.getClaveMunicipio(), 0, null);
			}
				
			nombre = representante.getNombre();
			apellidoPaterno = representante.getApellidoPaterno();
			apellidoMaterno = representante.getApellidoMaterno();
			rfc = representanteLegalV.getRfc();
			curp = representanteLegalV.getCurp();
			fechaNacimiento = representanteLegalV.getFechaNacimiento();
			if(representanteLegalV.getEntidadNacimiento() != null){
				entidadNacimiento = representanteLegalV.getEntidadNacimiento();
			}
			if (representanteLegalV.getSexo() != null){
				if (representanteLegalV.getSexo().equals("M")){
					tipoSexo = 1;
				}else{
					tipoSexo = 2;
				}
			}
			
			if(representanteLegalV.getIdEstado() != null){
				idEstado = representanteLegalV.getIdEstado();
			}
			if(representanteLegalV.getClaveMunicipio() != null){
				claveMunicipio = representanteLegalV.getClaveMunicipio();
			}
			if(representanteLegalV.getClaveLocalidad()!=null){
				claveLocalidad = representanteLegalV.getClaveLocalidad();
			}
			
			telefonos = representanteLegalV.getTelefonos();
			colonia = representanteLegalV.getColonia();
			calle = representanteLegalV.getCalle();
			codigoPostal = representanteLegalV.getCodigoPostal();
			
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String validaRfc(){
		if(cDAO.consultaRepresentantebyRfc(rfc).size()>0){
			errorRfc = 1;
			msjError = "El RFC del Representante ya se encuentra registrado, por favor verifique";
			}
		return SUCCESS;
		
	}


	public List<RepresentanteLegal> getLstRepresentanteLegal() {
		return lstRepresentanteLegal;
	}

	public void setLstRepresentanteLegal(
			List<RepresentanteLegal> lstRepresentanteLegal) {
		this.lstRepresentanteLegal = lstRepresentanteLegal;
	}

	public int getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getClaveMunicipio() {
		return claveMunicipio;
	}

	public void setClaveMunicipio(Integer claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
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

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getRutaRepLegal() {
		return rutaRepLegal;
	}

	public void setRutaRepLegal(String rutaRepLegal) {
		this.rutaRepLegal = rutaRepLegal;
	}

	public int getEditar() {
		return editar;
	}

	public void setEditar(int editar) {
		this.editar = editar;
	}

	public RepresentanteLegal getRepresentante() {
		return representante;
	}

	public void setRepresentante(RepresentanteLegal representante) {
		this.representante = representante;
	}

	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public UtileriasDAO getUtileriasDAO() {
		return utileriasDAO;
	}

	public void setUtileriasDAO(UtileriasDAO utileriasDAO) {
		this.utileriasDAO = utileriasDAO;
	}

	public List<RepresentanteExpedientesV> getLstRepresentanteExpedientesV() {
		return lstRepresentanteExpedientesV;
	}

	public void setLstRepresentanteExpedientesV(
			List<RepresentanteExpedientesV> lstRepresentanteExpedientesV) {
		this.lstRepresentanteExpedientesV = lstRepresentanteExpedientesV;
	}

	public RepresentanteLegalV getRepresentanteLegalV() {
		return representanteLegalV;
	}

	public void setRepresentanteLegalV(RepresentanteLegalV representanteLegalV) {
		this.representanteLegalV = representanteLegalV;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public int getIdExpediente() {
		return idExpediente;
	}

	public void setIdExpediente(int idExpediente) {
		this.idExpediente = idExpediente;
	}

	public long getIdExpedienteRepresentante() {
		return idExpedienteRepresentante;
	}

	public void setIdExpedienteRepresentante(long idExpedienteRepresentante) {
		this.idExpedienteRepresentante = idExpedienteRepresentante;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public int getErrorRfc() {
		return errorRfc;
	}

	public void setErrorRfc(int errorRfc) {
		this.errorRfc = errorRfc;
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
	
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEntidadNacimiento() {
		return entidadNacimiento;
	}

	public void setEntidadNacimiento(Integer entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getTipoSexo() {
		return tipoSexo;
	}
	
	public void setTipoSexo(int tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public String getNumExterior() {
		return numExterior;
	}

	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}

	public String getNumInterior() {
		return numInterior;
	}

	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
}
