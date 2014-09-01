package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "documentacion_sp_carta_adhesion_v")
public class DocumentacionSPCartaAdhesionV{	
	
	private Long idExpSPCartaAdhesion;
	private Integer  idExpediente; 
	private String  folioCartaAdhesion;
	private String  rutaDocumento;
	private Date fechaDocumento;
	private Date fechaAcuse;
	private Boolean observacion;
	private Date fechaExpedicionAnexo;
	private Double volumen;	
	private String expediente;	
	private String 	tipoExpediente;
	private Integer prioridadExpediente;
	private boolean requerido;
	private Integer idComprador;
	private Integer idPrograma;
	private Integer tipoConstancia;
	
	public DocumentacionSPCartaAdhesionV() {
		super();
	}
	
	public DocumentacionSPCartaAdhesionV(Integer idExpediente, String expediente) {
		super();
		this.idExpediente = idExpediente;
		this.expediente = expediente;
	}
	
	//Expediente Anexo 32-D
	public DocumentacionSPCartaAdhesionV(Integer idExpediente, String expediente, String folioCartaAdhesion, Date fechaExpedicionAnexo, Long idExpSPCartaAdhesion, String rutaDocumento, Boolean observacion) {
		super();
		this.idExpediente = idExpediente;
		this.expediente = expediente;
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.fechaExpedicionAnexo = fechaExpedicionAnexo;
		this.idExpSPCartaAdhesion = idExpSPCartaAdhesion;
		this.rutaDocumento = rutaDocumento;
		this.observacion = observacion;
	}
	
	//Expediente Solicitud de apoyo (3)
	public DocumentacionSPCartaAdhesionV(Integer idExpediente, String expediente, String folioCartaAdhesion, Long idExpSPCartaAdhesion, String rutaDocumento,  Double volumen, Boolean observacion) {
		super();
		this.idExpediente = idExpediente;
		this.expediente = expediente;
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.idExpSPCartaAdhesion = idExpSPCartaAdhesion;
		this.rutaDocumento = rutaDocumento;
		this.volumen = volumen;
		this.observacion= observacion;
	}
	
	//Expediente escrito libre(1)
	public DocumentacionSPCartaAdhesionV(Integer idExpediente, String expediente, String folioCartaAdhesion, Long idExpSPCartaAdhesion, Date fechaDocumento, Date fechaAcuse, String rutaDocumento) {
		super();
		this.idExpediente = idExpediente;
		this.expediente = expediente;
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.idExpSPCartaAdhesion = idExpSPCartaAdhesion;
		this.fechaDocumento = fechaDocumento;
		this.fechaAcuse = fechaAcuse;
		this.rutaDocumento = rutaDocumento;
	}
	
	//Expediente Certificados de deposito 
	public DocumentacionSPCartaAdhesionV(Integer idExpediente, String expediente, String folioCartaAdhesion, Long idExpSPCartaAdhesion, String rutaDocumento, int tipoConstancia, Boolean observacion) {
			super();
			this.idExpediente = idExpediente;
			this.expediente = expediente;
			this.folioCartaAdhesion = folioCartaAdhesion;
			this.idExpSPCartaAdhesion = idExpSPCartaAdhesion;
			this.rutaDocumento = rutaDocumento;
			this.setTipoConstancia(tipoConstancia); 
			this.observacion = observacion;
	
		}
	
	public DocumentacionSPCartaAdhesionV(Integer idExpediente, String expediente, String rutaDocumento) {
		super();
		this.idExpediente = idExpediente;
		this.expediente = expediente;
		this.rutaDocumento = rutaDocumento;
		
	}
	
	public DocumentacionSPCartaAdhesionV(String folioCartaAdhesion, Integer idExpediente, String expediente, boolean requerido) {
		super();
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.idExpediente = idExpediente;
		this.expediente = expediente;
		this.requerido = requerido;
	
	}
	@Id	
	@Column(name =  "id_exp_sp_carta_adhesion")
	@GeneratedValue(generator = "expedientes_sp_carta_adhesion_id_exp_sp_carta_adhesion_seq")
	@SequenceGenerator(name = "expedientes_sp_carta_adhesion_id_exp_sp_carta_adhesion_seq", sequenceName = "expedientes_sp_carta_adhesion_id_exp_sp_carta_adhesion_seq")	
	public Long getIdExpSPCartaAdhesion() {
		return idExpSPCartaAdhesion;
	}
	public void setIdExpSPCartaAdhesion(Long idExpSPCartaAdhesion) {
		this.idExpSPCartaAdhesion = idExpSPCartaAdhesion;
	}
	
	@Column(name =  "id_expediente")
	public Integer getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(Integer idExpediente) {
		this.idExpediente = idExpediente;
	}
	
	@Column(name =  "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "ruta_documento")	
	public String getRutaDocumento() {
		return rutaDocumento;
	}
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}	
	
	@Column(name =  "fecha_documento")	
	public Date getFechaDocumento() {
		return fechaDocumento;
	}
	
	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	
	@Column(name =  "fecha_acuse")
	public Date getFechaAcuse() {
		return fechaAcuse;
	}
	public void setFechaAcuse(Date fechaAcuse) {
		this.fechaAcuse = fechaAcuse;
	}
	
	@Column(name =  "observacion")
	public Boolean getObservacion() {
		return observacion;
	}

	public void setObservacion(Boolean observacion) {
		this.observacion = observacion;
	}

	@Column(name =  "fecha_expedicion_anexo")
	public Date getFechaExpedicionAnexo() {
		return fechaExpedicionAnexo;
	}

	public void setFechaExpedicionAnexo(Date fechaExpedicionAnexo) {
		this.fechaExpedicionAnexo = fechaExpedicionAnexo;
	}

	
	@Column(name =  "volumen")
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	@Column(name =  "expediente")
	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	@Column(name =  "tipo_expediente")
	public String getTipoExpediente() {
		return tipoExpediente;
	}

	public void setTipoExpediente(String tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}
	
	@Column(name =  "prioridad_expediente")
	public Integer getPrioridadExpediente() {
		return prioridadExpediente;
	}

	public void setPrioridadExpediente(Integer prioridadExpediente) {
		this.prioridadExpediente = prioridadExpediente;
	}

	@Column(name =  "requerido")
	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}
	
	@Column(name =  "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	@Column(name =  "tipo_constancia")
	public Integer getTipoConstancia() {
		return tipoConstancia;
	}

	public void setTipoConstancia(Integer tipoConstancia) {
		this.tipoConstancia = tipoConstancia;
	}	
}
