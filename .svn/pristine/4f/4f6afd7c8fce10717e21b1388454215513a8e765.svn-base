package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "oficio_obs_solicitud_pago")
public class OficioObsSolicitudPago {

	private long idOficioObsSolPago;
	private String folioCartaAdhesion;
	private Date  fechaDocObs; 
	private Date  fechaAcuseObs; 
	private String noOficioObs;
	private String rutaDocObs;
	private Date  fechaDocRespObs; 
	private Date  fechaAcuseRespObs;
	private String noOficioRespObs;
	private String rutaDocRespObs;  
	private Date  fechaCreacion;
	private String noOficioOrigen;
	private Boolean alcance;
	private Boolean tSolicitudPagoHastaOficioObs;
	private Boolean tOficioObsHastaOficioResp;	
	
	
	@Id	
	@Column(name =  "id_oficio_obs_sol_pago")
	@GeneratedValue(generator = "oficio_obs_solicitud_pago_id_oficio_obs_sol_pago_seq")
	@SequenceGenerator(name = "oficio_obs_solicitud_pago_id_oficio_obs_sol_pago_seq", sequenceName = "oficio_obs_solicitud_pago_id_oficio_obs_sol_pago_seq")
	public long getIdOficioObsSolPago() {
		return idOficioObsSolPago;
	}
	public void setIdOficioObsSolPago(long idOficioObsSolPago) {
		this.idOficioObsSolPago = idOficioObsSolPago;
	}	  
	
	@Column(name =  "folio_carta_adhesion")	   
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "fecha_doc_obs")	
	public Date getFechaDocObs() {
		return fechaDocObs;
	}
	public void setFechaDocObs(Date fechaDocObs) {
		this.fechaDocObs = fechaDocObs;
	}
	
	@Column(name =  "fecha_acuse_obs")
	public Date getFechaAcuseObs() {
		return fechaAcuseObs;
	}
	public void setFechaAcuseObs(Date fechaAcuseObs) {
		this.fechaAcuseObs = fechaAcuseObs;
	}
	
	@Column(name =  "no_oficio_obs")
	public String getNoOficioObs() {
		return noOficioObs;
	}
	public void setNoOficioObs(String noOficioObs) {
		this.noOficioObs = noOficioObs;
	}
	
	@Column(name =  "ruta_doc_obs")
	public String getRutaDocObs() {
		return rutaDocObs;
	}
	public void setRutaDocObs(String rutaDocObs) {
		this.rutaDocObs = rutaDocObs;
	}
	
	@Column(name =  "fecha_doc_resp_obs")
	public Date getFechaDocRespObs() {
		return fechaDocRespObs;
	}
	public void setFechaDocRespObs(Date fechaDocRespObs) {
		this.fechaDocRespObs = fechaDocRespObs;
	}
	
	@Column(name =  "fecha_acuse_resp_obs")
	public Date getFechaAcuseRespObs() {
		return fechaAcuseRespObs;
	}
	public void setFechaAcuseRespObs(Date fechaAcuseRespObs) {
		this.fechaAcuseRespObs = fechaAcuseRespObs;
	}
	
	@Column(name =  "no_oficio_resp_obs")
	public String getNoOficioRespObs() {
		return noOficioRespObs;
	}
	public void setNoOficioRespObs(String noOficioRespObs) {
		this.noOficioRespObs = noOficioRespObs;
	}
	
	@Column(name =  "ruta_doc_resp_obs")
	public String getRutaDocRespObs() {
		return rutaDocRespObs;
	}
	public void setRutaDocRespObs(String rutaDocRespObs) {
		this.rutaDocRespObs = rutaDocRespObs;
	}
	
	@Column(name =  "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name =  "no_oficio_origen")
	public String getNoOficioOrigen() {
		return noOficioOrigen;
	}
	public void setNoOficioOrigen(String noOficioOrigen) {
		this.noOficioOrigen = noOficioOrigen;
	}
	
	@Column(name =  "alcance")
	public Boolean getAlcance() {
		return alcance;
	}
	public void setAlcance(Boolean alcance) {
		this.alcance = alcance;
	}
	
	@Column(name =  "t_solicitud_pago_hasta_oficio_obs")
	public Boolean gettSolicitudPagoHastaOficioObs() {
		return tSolicitudPagoHastaOficioObs;
	}
	public void settSolicitudPagoHastaOficioObs(Boolean tSolicitudPagoHastaOficioObs) {
		this.tSolicitudPagoHastaOficioObs = tSolicitudPagoHastaOficioObs;
	}
	
	@Column(name =  "t_oficio_obs_hasta_oficio_resp")
	public Boolean gettOficioObsHastaOficioResp() {
		return tOficioObsHastaOficioResp;
	}
	public void settOficioObsHastaOficioResp(Boolean tOficioObsHastaOficioResp) {
		this.tOficioObsHastaOficioResp = tOficioObsHastaOficioResp;
	}	

	
}
