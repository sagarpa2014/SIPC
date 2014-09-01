package mx.gob.comer.sipc.action.solicitudpago;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.AuditoresExternos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.transaccionales.AuditorSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.log.AppLogger;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AsignacionAuditorSolPagoAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private List<AuditoresExternos> lstAuditoresExternos;
	private List<AuditorSolicitudPago> lstAuditorSolPago;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private String folioCartaAdhesion;
	private Comprador comprador;
	private int registrar;
	private int tipoDocumentacion;
	private String numeroAuditor;
	private String msjOk;
	private Integer numCamposAV;
	private int numCamposAVAnterior;
	private Double volumenAuditor;
	private String[] capNumeroAuditor;
	private String[] selectedAuditorV;
	private Double[] capVolumen;
	private int idComprador;
	private int errorNumeroAuditor;
	private String msjError;
	private String nombreAuditor;
	private int count;
	
	
	public String capAuditorSolPago(){
		try{
			List<AuditorSolicitudPago> lstAuditorSolPago = spDAO.consultaAuditorSolPago(folioCartaAdhesion, tipoDocumentacion);
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			// Recupera los datos del comprador
			idComprador = ca.getIdComprador();
			comprador = cDAO.consultaComprador(ca.getIdComprador()).get(0);
			if (lstAuditorSolPago.size()>0){
				numCamposAV = lstAuditorSolPago.size();
			}else{
				numCamposAV = null;
			}
			
			agregarAuditorVolumen();

		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	
	
	public String agregarAuditorVolumen(){
		try{
			lstAuditorSolPago = new ArrayList<AuditorSolicitudPago>();

			for(int i=1; i<=numCamposAV; i++){
				lstAuditorSolPago.add(new AuditorSolicitudPago());
			}
			lstAuditorSolPago = spDAO.consultaAuditorSolPago(folioCartaAdhesion , tipoDocumentacion);
			numCamposAVAnterior = lstAuditorSolPago.size(); 
			if(numCamposAV > numCamposAVAnterior){
				int resta = 0;
				resta = numCamposAV - numCamposAVAnterior;
				for(int i =1; i<=resta; i++){
					lstAuditorSolPago.add(new AuditorSolicitudPago());
				}
			}else if(numCamposAV < numCamposAVAnterior){
				List<AuditorSolicitudPago> lstAuditorSolPagoTemp = new ArrayList<AuditorSolicitudPago>();
				for (int i=1; i<=numCamposAV; i++){
					lstAuditorSolPagoTemp.add(lstAuditorSolPago.get(i-1));
				}
				lstAuditorSolPago = new ArrayList<AuditorSolicitudPago>();
				lstAuditorSolPago = lstAuditorSolPagoTemp;
				
			}
			
		}catch (JDBCException e) {
	
		e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getMessage());
		}
		lstAuditoresExternos = cDAO.consultaAuditores("", "", -1);
		return SUCCESS;
	}


	
	public String registrarAuditorSolPago(){
		session = ActionContext.getContext().getSession();
		try{
			int elementosBorrados = 0;
			elementosBorrados = spDAO.borrarVolumenAuditor(folioCartaAdhesion, tipoDocumentacion);
			AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla auditor_solicitud_pago");
			//Guarda informacion del auditor
			System.out.println("registrarAuditorSolPago");
			for (int i = 0; i < capNumeroAuditor.length; i++) {
				AuditorSolicitudPago asp = new AuditorSolicitudPago();	
				asp.setNumeroAuditor(capNumeroAuditor[i]);
				asp.setVolumenAuditor(capVolumen[i]);
				asp.setFolioCartaAdhesion(folioCartaAdhesion);
				asp.setTipoDocumentacion(tipoDocumentacion);
				System.out.println("capVolumen[i] "+ capVolumen[i]);
				cDAO.guardaObjeto(asp);
			}
			capAuditorSolPago();
			agregarAuditorVolumen();
			msjOk = "Se guardó satisfactoriamente el registro";
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	
	public String validarNumeroAuditor(){
		lstAuditoresExternos = cDAO.consultaAuditoresbyNumAuditor(numeroAuditor);
		if(lstAuditoresExternos.size()==0){
			errorNumeroAuditor = 1;
			msjError = "El numero del Auditor no se encuentra registrado, por favor verifique";
			return SUCCESS;
		}else{
			nombreAuditor = lstAuditoresExternos.get(0).getNombre();
		}

		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}


	public List<AuditoresExternos> getLstAuditoresExternos() {
		return lstAuditoresExternos;
	}


	public void setLstAuditoresExternos(List<AuditoresExternos> lstAuditoresExternos) {
		this.lstAuditoresExternos = lstAuditoresExternos;
	}


	public List<AuditorSolicitudPago> getLstAuditorSolPago() {
		return lstAuditorSolPago;
	}


	public void setLstAuditorSolPago(List<AuditorSolicitudPago> lstAuditorSolPago) {
		this.lstAuditorSolPago = lstAuditorSolPago;
	}


	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}


	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}


	public Comprador getComprador() {
		return comprador;
	}


	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}


	public int getRegistrar() {
		return registrar;
	}


	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}


	public int getTipoDocumentacion() {
		return tipoDocumentacion;
	}


	public void setTipoDocumentacion(int tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}


	public String getNumeroAuditor() {
		return numeroAuditor;
	}


	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}


	public String getMsjOk() {
		return msjOk;
	}


	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}


	public Integer getNumCamposAV() {
		return numCamposAV;
	}


	public void setNumCamposAV(Integer numCamposAV) {
		this.numCamposAV = numCamposAV;
	}


	public int getNumCamposAVAnterior() {
		return numCamposAVAnterior;
	}


	public void setNumCamposAVAnterior(int numCamposAVAnterior) {
		this.numCamposAVAnterior = numCamposAVAnterior;
	}


	public Double getVolumenAuditor() {
		return volumenAuditor;
	}


	public void setVolumenAuditor(Double volumenAuditor) {
		this.volumenAuditor = volumenAuditor;
	}


	public String[] getSelectedAuditorV() {
		return selectedAuditorV;
	}


	public void setSelectedAuditorV(String[] selectedAuditorV) {
		this.selectedAuditorV = selectedAuditorV;
	}


	public Double[] getCapVolumen() {
		return capVolumen;
	}


	public void setCapVolumen(Double[] capVolumen) {
		this.capVolumen = capVolumen;
	}
	
	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}


	public String[] getCapNumeroAuditor() {
		return capNumeroAuditor;
	}


	public void setCapNumeroAuditor(String[] capNumeroAuditor) {
		this.capNumeroAuditor = capNumeroAuditor;
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


	public String getNombreAuditor() {
		return nombreAuditor;
	}
	
	public void setNombreAuditor(String nombreAuditor) {
		this.nombreAuditor = nombreAuditor;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}

}