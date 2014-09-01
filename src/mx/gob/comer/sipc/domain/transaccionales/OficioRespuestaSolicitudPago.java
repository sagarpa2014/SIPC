package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "oficio_respuesta_solicitud_pago")
public class OficioRespuestaSolicitudPago {

	private long idOficioRespuesta;
	private String folioCartaAdhesion;
	private Date  fechaDocResp; 
	private Date  fechaAcuseResp; 
	private String noOficioRespuesta;
	private String nomArchivoRespuesta;
	private String rutaDocRespuesta;  
	private Date  fechaCreacion;
	
	
	@Id	
	@Column(name =  "id_oficio_respuesta")
	@GeneratedValue(generator = "oficio_respuesta_solicitud_pago_id_oficio_respuesta_seq")
	@SequenceGenerator(name = "oficio_respuesta_solicitud_pago_id_oficio_respuesta_seq", sequenceName = "oficio_respuesta_solicitud_pago_id_oficio_respuesta_seq")
	public long getIdOficioRespuesta() {
		return idOficioRespuesta;
	}
	public void setIdOficioRespuesta(long idOficioRespuesta) {
		this.idOficioRespuesta = idOficioRespuesta;
	}
	
	
	@Column(name =  "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "fecha_doc_resp")
	public Date getFechaDocResp() {
		return fechaDocResp;
	}
	public void setFechaDocResp(Date fechaDocResp) {
		this.fechaDocResp = fechaDocResp;
	}
	
	@Column(name =  "fecha_acuse_resp")
	public Date getFechaAcuseResp() {
		return fechaAcuseResp;
	}
	public void setFechaAcuseResp(Date fechaAcuseResp) {
		this.fechaAcuseResp = fechaAcuseResp;
	}
	
	@Column(name =  "no_oficio_respuesta")
	public String getNoOficioRespuesta() {
		return noOficioRespuesta;
	}
	public void setNoOficioRespuesta(String noOficioRespuesta) {
		this.noOficioRespuesta = noOficioRespuesta;
	}
	
	@Column(name =  "nom_archivo_respuesta")
	public String getNomArchivoRespuesta() {
		return nomArchivoRespuesta;
	}
	public void setNomArchivoRespuesta(String nomArchivoRespuesta) {
		this.nomArchivoRespuesta = nomArchivoRespuesta;
	}
	
	@Column(name =  "ruta_doc_respuesta")
	public String getRutaDocRespuesta() {
		return rutaDocRespuesta;
	}
	public void setRutaDocRespuesta(String rutaDocRespuesta) {
		this.rutaDocRespuesta = rutaDocRespuesta;
	}
	
	@Column(name =  "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
		
		
}
