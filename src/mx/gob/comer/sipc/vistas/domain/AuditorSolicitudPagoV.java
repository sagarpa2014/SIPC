package mx.gob.comer.sipc.vistas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "auditor_solicitud_pago_v")
public class AuditorSolicitudPagoV implements Serializable{
	/**
	 * 
	 */
	private long idAuditorSolPago;
	private String numeroAuditor;
	private String nombre;
	private String folioCartaAdhesion;
	private Double volumenAuditor;
	private int tipoDocumentacion;
	private Date fechaInicio;
	private Date fechaFin;
	
	@Id
	@Column(name = "id_auditor_sol_pago")
	public long getIdAuditorSolPago() {
		return idAuditorSolPago;
	}
	public void setIdAuditorSolPago(long idAuditorSolPago) {
		this.idAuditorSolPago = idAuditorSolPago;
	}

	@Column(name = "numero_auditor")
	public String getNumeroAuditor() {
		return numeroAuditor;
	}
	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name = "volumen_auditor")
	public Double getVolumenAuditor() {
		return volumenAuditor;
	}
	public void setVolumenAuditor(Double volumenAuditor) {
		this.volumenAuditor = volumenAuditor;
	}
	
	@Column(name = "tipo_documentacion")
	public int getTipoDocumentacion() {
		return tipoDocumentacion;
	}
	
	public void setTipoDocumentacion(int tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}
	
	@Column(name = "fecha_inicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}	
	@Column(name = "fecha_fin")
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}	
}
