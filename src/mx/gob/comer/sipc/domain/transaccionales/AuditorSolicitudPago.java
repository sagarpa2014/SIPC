package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "auditor_solicitud_pago")
public class AuditorSolicitudPago {

	
	private Long idAuditorSolPago;
	private String numeroAuditor;
	private String folioCartaAdhesion;
	private int tipoDocumentacion;
	private Double volumenAuditor;
	
	@Id	
	@GeneratedValue(generator = "auditor_solicitud_pago_id_auditor_sol_pago_seq")
	@SequenceGenerator(name = "auditor_solicitud_pago_id_auditor_sol_pago_seq", sequenceName = "auditor_solicitud_pago_id_auditor_sol_pago_seq")
	@Column(name =  "id_auditor_sol_pago")
	public Long getIdAuditorSolPago() {
		return idAuditorSolPago;
	}
	public void setIdAuditorSolPago(Long idAuditorSolPago) {
		this.idAuditorSolPago = idAuditorSolPago;
	}
	
	@Column(name =  "numero_auditor")
	public String getNumeroAuditor() {
		return numeroAuditor;
	}
	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}
	
	@Column(name = "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "tipo_documentacion")
	public int getTipoDocumentacion() {
		return tipoDocumentacion;
	}
	public void setTipoDocumentacion(int tipoDocumentacion2) {
		this.tipoDocumentacion = tipoDocumentacion2;
	}
	
	@Column(name =  "volumen_auditor")
	public Double getVolumenAuditor() {
		return volumenAuditor;
	}
	public void setVolumenAuditor(Double volumenAuditor) {
		this.volumenAuditor = volumenAuditor;
	}
	
	
}
