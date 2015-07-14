package mx.gob.comer.sipc.action.solicitudpago;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.AuditoresExternos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.domain.catalogos.Pais;
import mx.gob.comer.sipc.domain.transaccionales.AuditorSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.AuditorVolumenSolPago;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.AuditorSolicitudPagoV;









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
	private Map<Integer, Double> capVolumen;
	private int idComprador;
	private int errorNumeroAuditor;
	private String msjError;
	private String nombreAuditor;
	private int count;
	private Integer estatus;
	private Date fechaPeriodoInicialAuditor,fechaPeriodoFinalAuditor;
	
	private int numCamposBodVolumen;	
	private long idAuditorSolPago;	
	private List<AuditorSolicitudPagoV> lstAuditorSolPagoV;
	private List<AuditorVolumenSolPago> lstAuditorVolumenSolPago;
	private List<Bodegas> lstBodegas;
	private Map<Integer, String> capBodega;	
	private Map<Integer, Boolean> capChkDestino;
	private Map<Integer, Integer> capDestino;
	private boolean aplicaInternacional;
	private List<Pais> lstDestinoInternacional;
	private List<Estado> lstDestinoNacional;
	
	
	
	
	public String capAuditorSolPago(){
		try{
			lstAuditorSolPagoV = spDAO.consultaAuditorSolPagoV(folioCartaAdhesion, tipoDocumentacion,0);
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			// Recupera los datos del comprador
			idComprador = ca.getIdComprador();
			comprador = cDAO.consultaComprador(ca.getIdComprador()).get(0);
			
//			if (lstAuditorSolPago.size()>0){
//				numCamposAV = lstAuditorSolPago.size();
//			}else{
//				numCamposAV = null;
//			}
//			
//			agregarAuditorVolumen();
			estatus = ca.getEstatus();
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capAuditorSolPago  debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	
	
	public String agregarAuditor(){
		try{	
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			// Recupera los datos del comprador
			idComprador = ca.getIdComprador();
			comprador = cDAO.consultaComprador(ca.getIdComprador()).get(0);
			
			if(registrar != 0){
				//Recupera los datos del auditor
				lstAuditorSolPagoV = spDAO.consultaAuditorSolPagoV(folioCartaAdhesion, -1, idAuditorSolPago);
				if(lstAuditorSolPagoV.size() > 0){
					numeroAuditor = lstAuditorSolPagoV.get(0).getNumeroAuditor();
					nombreAuditor = lstAuditorSolPagoV.get(0).getNombre();
					fechaPeriodoInicialAuditor =  lstAuditorSolPagoV.get(0).getFechaInicio(); 
					fechaPeriodoFinalAuditor = lstAuditorSolPagoV.get(0).getFechaFin(); 
				}
				//Recupera la lista de asignacion de bodega y volumen
				lstAuditorVolumenSolPago = spDAO.consultaAuditorVolumenSolPago(idAuditorSolPago);
				if(lstAuditorVolumenSolPago.size() > 0){
					numCamposBodVolumen = lstAuditorVolumenSolPago.size();
				}
				lstBodegas = spDAO.consultaBodegasAutorizadasByCarta(folioCartaAdhesion);
				lstDestinoInternacional =  cDAO.consultaPais(0, null); 
				lstDestinoNacional = cDAO.consultaEstado(0);					
			}
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en agregarAuditor  debido a: "+e.getMessage());
		}
		
		return SUCCESS;
	}


	
	public String registrarAuditorSolPago(){
		session = ActionContext.getContext().getSession();
		try{
			int elementosBorrados = 0;
			AuditorSolicitudPago asp = null;
//			elementosBorrados = spDAO.borrarVolumenAuditor(folioCartaAdhesion, tipoDocumentacion);
//			AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla auditor_solicitud_pago");
			//Guarda informacion del auditor1
			if(registrar == 0){
				asp = new AuditorSolicitudPago();
			}else{
				//Recupera registro 
				asp = spDAO.consultaAuditorSolPago(idAuditorSolPago, null, -1).get(0);
			}			
			asp.setFolioCartaAdhesion(folioCartaAdhesion);
			asp.setNumeroAuditor(numeroAuditor);
			asp.setFechaInicio(fechaPeriodoInicialAuditor);
			asp.setFechaFin(fechaPeriodoFinalAuditor);
			asp.setTipoDocumentacion(tipoDocumentacion);
			asp = (AuditorSolicitudPago) cDAO.guardaObjeto(asp);
			if(registrar != 0){
				//Borrar los datos de bodega volumen en tabla auditor_volumen_sol_pago
				spDAO.borraAuditorVolumenSolPago(idAuditorSolPago);
			}
			//Guardar informacion de volumen por bodega
			if(numCamposBodVolumen > 0){
				Set<Integer> bodegaIt = capBodega.keySet();
				Iterator<Integer> it =  bodegaIt.iterator();
				while(it.hasNext()){
					Integer id = it.next();
					String bodega =  capBodega.get(id);
					Double volumen = capVolumen.get(id);
					Boolean chkDestino = capChkDestino.get(id);
					Integer destino = capDestino.get(id);				

					if(bodega !=null && !bodega.isEmpty()){
						AuditorVolumenSolPago avsp =  new AuditorVolumenSolPago();
						avsp.setClaveBodega(bodega);
						avsp.setIdAuditorSolPago(asp.getIdAuditorSolPago());					
						avsp.setVolumenAuditor(volumen);
						System.out.println("chkDestino "+chkDestino);
						if(chkDestino!=null){
							if(chkDestino){
								avsp.setIdPais(destino);
								avsp.setInternacional(true);
							}else{
								avsp.setIdEstado(destino);
								avsp.setInternacional(false);
							}
						}else{
							avsp.setIdEstado(destino);
							avsp.setInternacional(false);
						}
						cDAO.guardaObjeto(avsp);
					}
				}
				
			}			
			capAuditorSolPago();
			//agregarAuditorVolumen();
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
	
	
	public String agregaCamposVolumenBodega(){
		try{
			lstAuditorVolumenSolPago = new ArrayList<AuditorVolumenSolPago>();
			for(int i=1; i<= numCamposBodVolumen; i++){
				lstAuditorVolumenSolPago.add(new AuditorVolumenSolPago());
			}
			//Recupera la lista de Bodegas que estan autorizadas en carta
			
			lstBodegas = spDAO.consultaBodegasAutorizadasByCarta(folioCartaAdhesion);
			lstDestinoNacional = cDAO.consultaEstado(0);
			
			
			
		}catch (JDBCException e) {
			addActionError("Ocurrió un error inesperado, favor de informar al administrador");
			AppLogger.error("errores","Ocurrió un error en sintesisEjecutivaProyecto debido a: " +e.getCause() );
			e.printStackTrace();
		} 		
		return SUCCESS;
	}
	public String consigueDestinoAuditorSolPago(){
		System.out.println("Aplica Internacional "+aplicaInternacional);
		if(aplicaInternacional){
			lstDestinoInternacional =  cDAO.consultaPais(0, null); 
		}else{
			lstDestinoNacional = cDAO.consultaEstado(0);
			
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
			//Verifica que no se encuentre ya capturado en la carta adhesion
			lstAuditorSolPagoV = spDAO.consultaAuditorSolPagoV(folioCartaAdhesion, tipoDocumentacion, 0, numeroAuditor);
			if(lstAuditorSolPagoV.size() > 0){
				msjError = "El numero del Auditor ya se encuentra registrado para la carta de adhesión, por favor verifique";
				errorNumeroAuditor = 2;
			}			
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

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
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

	
	public int getNumCamposBodVolumen() {
		return numCamposBodVolumen;
	}
	public void setNumCamposBodVolumen(int numCamposBodVolumen) {
		this.numCamposBodVolumen = numCamposBodVolumen;
	}

	public long getIdAuditorSolPago() {
		return idAuditorSolPago;
	}

	public void setIdAuditorSolPago(long idAuditorSolPago) {
		this.idAuditorSolPago = idAuditorSolPago;
	}


	public List<AuditorSolicitudPagoV> getLstAuditorSolPagoV() {
		return lstAuditorSolPagoV;
	}

	public void setLstAuditorSolPagoV(List<AuditorSolicitudPagoV> lstAuditorSolPagoV) {
		this.lstAuditorSolPagoV = lstAuditorSolPagoV;
	}


	public List<AuditorVolumenSolPago> getLstAuditorVolumenSolPago() {
		return lstAuditorVolumenSolPago;
	}

	public void setLstAuditorVolumenSolPago(
			List<AuditorVolumenSolPago> lstAuditorVolumenSolPago) {
		this.lstAuditorVolumenSolPago = lstAuditorVolumenSolPago;
	}


	public Map<Integer, Double> getCapVolumen() {
		return capVolumen;
	}


	public void setCapVolumen(Map<Integer, Double> capVolumen) {
		this.capVolumen = capVolumen;
	}


	public List<Bodegas> getLstBodegas() {
		return lstBodegas;
	}


	public void setLstBodegas(List<Bodegas> lstBodegas) {
		this.lstBodegas = lstBodegas;
	}


	public Map<Integer, String> getCapBodega() {
		return capBodega;
	}


	public void setCapBodega(Map<Integer, String> capBodega) {
		this.capBodega = capBodega;
	}

	public Map<Integer, Boolean> getCapChkDestino() {
		return capChkDestino;
	}

	public void setCapChkDestino(Map<Integer, Boolean> capChkDestino) {
		this.capChkDestino = capChkDestino;
	}

	public Map<Integer, Integer> getCapDestino() {
		return capDestino;
	}

	public void setCapDestino(Map<Integer, Integer> capDestino) {
		this.capDestino = capDestino;
	}
	public boolean isAplicaInternacional() {
		return aplicaInternacional;
	}

	public void setAplicaInternacional(boolean aplicaInternacional) {
		this.aplicaInternacional = aplicaInternacional;
	}


	public List<Pais> getLstDestinoInternacional() {
		return lstDestinoInternacional;
	}


	public void setLstDestinoInternacional(List<Pais> lstDestinoInternacional) {
		this.lstDestinoInternacional = lstDestinoInternacional;
	}


	public List<Estado> getLstDestinoNacional() {
		return lstDestinoNacional;
	}


	public void setLstDestinoNacional(List<Estado> lstDestinoNacional) {
		this.lstDestinoNacional = lstDestinoNacional;
	}
	
	
}