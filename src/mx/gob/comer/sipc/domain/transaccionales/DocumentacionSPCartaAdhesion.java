package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "documentacion_sp_carta_adhesion")
public class DocumentacionSPCartaAdhesion{	
	
	private long idExpSPCartaAdhesion;
	private Integer  idExpediente; 
	private String  folioCartaAdhesion;
	private String  rutaDocumento;
	private Date fechaDocumento;
	private Date fechaAcuse;
	private String noOficio;
	private Boolean observacion;
	private Date fechaExpedicionAnexo;
	private Double volumen;
	private String rutaCertificados;
	private String rutaConstancias;
	private Integer tipoConstancia;
	
	
	public DocumentacionSPCartaAdhesion() {
		super();
	}
	
	public DocumentacionSPCartaAdhesion(Integer idExpediente) {
		super();
		this.idExpediente = idExpediente;
	}
	@Id	
	@Column(name =  "id_exp_sp_carta_adhesion")
	@GeneratedValue(generator = "documentacion_sp_carta_adhesion_id_exp_sp_carta_adhesion_seq")
	@SequenceGenerator(name = "documentacion_sp_carta_adhesion_id_exp_sp_carta_adhesion_seq", sequenceName = "documentacion_sp_carta_adhesion_id_exp_sp_carta_adhesion_seq")	
	public long getIdExpSPCartaAdhesion() {
		return idExpSPCartaAdhesion;
	}
	public void setIdExpSPCartaAdhesion(long idExpSPCartaAdhesion) {
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
	
	@Column(name =  "no_oficio")
	public String getNoOficio() {
		return noOficio;
	}
	public void setNoOficio(String noOficio) {
		this.noOficio = noOficio;
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
	
	@Column(name =  "ruta_certificados")
	public String getRutaCertificados() {
		return rutaCertificados;
	}

	public void setRutaCertificados(String rutaCertificados) {
		this.rutaCertificados = rutaCertificados;
	}
	
	@Column(name =  "ruta_constancias")
	public String getRutaConstancias() {
		return rutaConstancias;
	}

	public void setRutaConstancias(String rutaConstancias) {
		this.rutaConstancias = rutaConstancias;
	}
	
	@Column(name =  "tipo_constancia")
	public Integer getTipoConstancia() {
		return tipoConstancia;
	}

	public void setTipoConstancia(Integer tipoConstancia) {
		this.tipoConstancia = tipoConstancia;
	}
	
}
