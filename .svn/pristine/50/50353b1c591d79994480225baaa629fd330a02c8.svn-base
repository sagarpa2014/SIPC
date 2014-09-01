package mx.gob.comer.sipc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "expediente_auditor")
public class ExpedienteAuditor implements Serializable{
	/**
	 * 
	 */
	private Long idExpedienteAuditor;
	private Integer idExpediente;
	private String rutaExpediente;
	private String numeroAuditor;

	  
	@Id
	@GeneratedValue(generator = "expediente_auditor_id_expediente_auditor_seq")
	@SequenceGenerator(name = "expediente_auditor_id_expediente_auditor_seq", sequenceName = "expediente_auditor_id_expediente_auditor_seq")
	@Column(name = "id_expediente_auditor")
	public Long getIdExpedienteAuditor() {
		return idExpedienteAuditor;
	}
	public void setIdExpedienteAuditor(Long idExpedienteAuditor) {
		this.idExpedienteAuditor = idExpedienteAuditor;
	}
	
	@Column(name = "id_expediente")
	public Integer getIdExpediente() {
		return idExpediente;
	}
	
	public void setIdExpediente(Integer idExpediente) {
		this.idExpediente = idExpediente;
	}
	
	@Column(name = "ruta_expediente")
	public String getRutaExpediente() {
		return rutaExpediente;
	}
	public void setRutaExpediente(String rutaExpediente) {
		this.rutaExpediente = rutaExpediente;
	}
	

	@Column(name = "numero_auditor")
	public String getNumeroAuditor() {
		return numeroAuditor;
	}
	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}
		
}
