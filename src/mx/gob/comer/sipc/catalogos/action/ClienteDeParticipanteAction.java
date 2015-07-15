package mx.gob.comer.sipc.catalogos.action;
import java.util.ArrayList;
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
import mx.gob.comer.sipc.domain.catalogos.ClienteDeParticipante;
import mx.gob.comer.sipc.domain.catalogos.EstadosCivil;
import mx.gob.comer.sipc.domain.catalogos.TiposIdentificacion;
import mx.gob.comer.sipc.dao.UtileriasDAO; 

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ClienteDeParticipanteAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private List<ClienteDeParticipante> lstClienteDeParticipante;
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private int idCliente;
	private List<Estado> lstEstados;
	private List<Municipios> lstMunicipios;
	private List<Localidades> lstLocalidades;	
	private List<Expediente> lstExpedientes;
	private int registrar;
	private ClienteDeParticipante clienteParticipante;
	private ClienteDeParticipante r;
	private int errorRfc;
	private String msjError;
	private List<EstadosCivil> lstEstadosCivil;
	private List<TiposIdentificacion> lstTiposIdentificacion;
	private String cuadroSatisfactorio;
	private int idEstado;
	private int claveMunicipio;
	private String rfc;
	private String tipoPersonaFiscalCliente;

	public String busquedaClienteParticipante(){
		try{
			lstClienteDeParticipante = cDAO.consultaClienteDeParticipante(0,"","");
		}catch(JDBCException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String ejecutaBusquedaClienteParticipante(){
		try{
			lstClienteDeParticipante = cDAO.consultaClienteDeParticipante(0,clienteParticipante.getNombre(),clienteParticipante.getRfc());
		}catch(JDBCException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String registrarClienteParticipante(){
		try{			
			/*Recupera los datos del cliente del participante*/
			if(registrar==0){
				r = new ClienteDeParticipante();	
			}else{
				r = cDAO.consultaClienteDeParticipante(idCliente,"","").get(0);				
			}
			
			r.setNombre(clienteParticipante.getNombre());
			r.setRfc(clienteParticipante.getRfc().toUpperCase());	
			r.setTipoPersona(tipoPersonaFiscalCliente);
			clienteParticipante.setTipoPersona(tipoPersonaFiscalCliente);
			r.setCurp(registrar==0?clienteParticipante.getCurp()!=null && !clienteParticipante.getCurp().isEmpty()?clienteParticipante.getCurp():null:clienteParticipante.getCurp().isEmpty()?null:clienteParticipante.getCurp());			
			r.setApellidoPaterno(registrar==0?clienteParticipante.getApellidoPaterno()!=null && !clienteParticipante.getApellidoPaterno().isEmpty()?clienteParticipante.getApellidoPaterno():null:clienteParticipante.getApellidoPaterno().isEmpty()?null:clienteParticipante.getApellidoPaterno());
			r.setApellidoMaterno(registrar==0?clienteParticipante.getApellidoMaterno()!=null && !clienteParticipante.getApellidoMaterno().isEmpty()?clienteParticipante.getApellidoMaterno():null:clienteParticipante.getApellidoMaterno().isEmpty()?null:clienteParticipante.getApellidoMaterno());			
					
			if(clienteParticipante.getIdEstado() != -1){
					r.setIdEstado(clienteParticipante.getIdEstado());
			}else{
				r.setIdEstado(null);
			}
			
			if(clienteParticipante.getClaveMunicipio() != -1 ){
				r.setClaveMunicipio(clienteParticipante.getClaveMunicipio());
			}else{
				r.setClaveMunicipio(null);
			}				
			
			if(clienteParticipante.getClaveLocalidad() != -1){
				r.setClaveLocalidad(clienteParticipante.getClaveLocalidad());
			}else{
				r.setClaveLocalidad(null);
			}	
			r.setCalle(registrar==0?clienteParticipante.getCalle()!=null && !clienteParticipante.getCalle().isEmpty()?clienteParticipante.getCalle():null:clienteParticipante.getCalle().isEmpty()?null:clienteParticipante.getCalle());
			r.setColonia(registrar==0?clienteParticipante.getColonia()!=null && !clienteParticipante.getColonia().isEmpty()?clienteParticipante.getColonia():null:clienteParticipante.getColonia().isEmpty()?null:clienteParticipante.getColonia());
			r.setNumExterior(registrar==0?clienteParticipante.getNumExterior()!=null && !clienteParticipante.getNumExterior().isEmpty()?clienteParticipante.getNumExterior():null:clienteParticipante.getNumExterior().isEmpty()?null:clienteParticipante.getNumExterior());
			r.setNumInterior(registrar==0?clienteParticipante.getNumInterior()!=null && !clienteParticipante.getNumInterior().isEmpty()?clienteParticipante.getNumInterior():null:clienteParticipante.getNumInterior().isEmpty()?null:clienteParticipante.getNumInterior());
			r.setCodigoPostal(clienteParticipante.getCodigoPostal());
			r.setTelefonos(registrar==0?clienteParticipante.getTelefonos()!=null && !clienteParticipante.getTelefonos().isEmpty()?clienteParticipante.getTelefonos():null:clienteParticipante.getTelefonos().isEmpty()?null:clienteParticipante.getTelefonos());
			
			if(registrar == 1){
				r.setUsuarioModificacion((Integer) session.get("idUsuario"));
				r.setFechaModificacion(new Date());
			}else{
				r.setUsuarioCreacion((Integer) session.get("idUsuario"));
				r.setFechaAlta(new Date());
			}	
			r= (ClienteDeParticipante) cDAO.guardaObjeto(r);			
			lstClienteDeParticipante = cDAO.consultaClienteDeParticipante(-1,"","");
			if (registrar == 1){
				cuadroSatisfactorio = "El cliente del participante se editó correctamente";			
			}else{		
				cuadroSatisfactorio = "El cliente del participante se agregó correctamente";
			}		
			clienteParticipante.setNombre(null);
			clienteParticipante.setRfc(null);
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return SUCCESS;
	}
	
	public String agregarClienteParticipante(){
		try{
			lstEstados = cDAO.consultaEstado(0);
			lstMunicipios = new ArrayList<Municipios>();
			lstLocalidades = new ArrayList<Localidades>();
			if(registrar != 0){
				clienteParticipante = cDAO.consultaClienteDeParticipante(idCliente, "", "").get(0);	
				if(clienteParticipante.getIdEstado()!=null){
					lstMunicipios = cDAO.consultaMunicipiosbyEdo(clienteParticipante.getIdEstado(), 0, 0, "");
				}				
				if(clienteParticipante.getIdEstado()!=null && clienteParticipante.getClaveMunicipio()!=null){
					lstLocalidades = cDAO.consultaLocalidadByMunicipio(0, clienteParticipante.getIdEstado(),clienteParticipante.getClaveMunicipio(), 0, "");
				}
				tipoPersonaFiscalCliente = clienteParticipante.getTipoPersona();
			}else{
				tipoPersonaFiscalCliente = "F";
				clienteParticipante = new ClienteDeParticipante();
				clienteParticipante.setTipoPersona("F");  
			}
		}catch(Exception e){ 
			e.printStackTrace();
		}
				
		return SUCCESS;
	}
	
	public String recuperaMunicipioByEstadoClientePar(){
		try{
			if(idEstado != -1){
				lstMunicipios= cDAO.consultaMunicipiosbyEdo(idEstado, 0, 0, null);
			}else{
				lstMunicipios = new ArrayList<Municipios>();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	
	public String recuperaLocalidadesPorMunicipioClienterPar(){
		try{
			if(idEstado != -1 && claveMunicipio != -1 ){
				lstLocalidades= cDAO.consultaLocalidadByMunicipio(0, idEstado, claveMunicipio, 0, null);
			}else{
				lstLocalidades = new ArrayList<Localidades>();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	public String validarRfcClienteParticipante(){
		
		lstClienteDeParticipante = cDAO.consultaClienteDeParticipante(0,"",rfc);
		if(lstClienteDeParticipante.size() > 0){
			errorRfc = 1;
			msjError = "El RFC del Cliente del Participante ya se encuentra registrado, por favor verifique";
		}
		
		return SUCCESS;
		
	}
	
	public String recuperaSeleccionTipoPersonaFiscalCliente(){
		//Recupera el tipo de persona de acuerdo a lo seleccionado
		
		return SUCCESS;
	}
	
	
	public List<ClienteDeParticipante> getLstClienteDeParticipante() {
		return lstClienteDeParticipante;
	}

	public void setLstClienteDeParticipante(
			List<ClienteDeParticipante> lstClienteDeParticipante) {
		this.lstClienteDeParticipante = lstClienteDeParticipante;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public ClienteDeParticipante getClienteParticipante() {
		return clienteParticipante;
	}

	public void setClienteParticipante(ClienteDeParticipante clienteParticipante) {
		this.clienteParticipante = clienteParticipante;
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
	
	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public UtileriasDAO getUtileriasDAO() {
		return utileriasDAO;
	}

	public void setUtileriasDAO(UtileriasDAO utileriasDAO) {
		this.utileriasDAO = utileriasDAO;
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
	
	public List<EstadosCivil> getLstEstadosCivil() {
		return lstEstadosCivil;
	}

	public void setLstEstadosCivil(List<EstadosCivil> lstEstadosCivil) {
		this.lstEstadosCivil = lstEstadosCivil;
	}

	public List<TiposIdentificacion> getLstTiposIdentificacion() {
		return lstTiposIdentificacion;
	}

	public void setLstTiposIdentificacion(
			List<TiposIdentificacion> lstTiposIdentificacion) {
		this.lstTiposIdentificacion = lstTiposIdentificacion;
	}

	public String getCuadroSatisfactorio() {
		return cuadroSatisfactorio;
	}

	public void setCuadroSatisfactorio(String cuadroSatisfactorio) {
		this.cuadroSatisfactorio = cuadroSatisfactorio;
	}
	
	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getClaveMunicipio() {
		return claveMunicipio;
	}

	public void setClaveMunicipio(int claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}
	
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	
	public String getTipoPersonaFiscalCliente() {
		return tipoPersonaFiscalCliente;
	}
	

	public void setTipoPersonaFiscalCliente(String tipoPersonaFiscalCliente) {
		this.tipoPersonaFiscalCliente = tipoPersonaFiscalCliente;
	}
	
}
