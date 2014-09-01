package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expedientes_programas_v")
public class ExpedientesProgramasV {

	private Long idExpedientePrograma;
	private int idPrograma;
	private int idExpediente;
	private String programa;
	private String expediente;
	private String tipo;
	private Integer prioridadExpediente;
	private boolean documentacionOpcional; 
	
	@Id
	@Column(name = "id_expediente_programa")
	public Long getIdExpedientePrograma() {
		return idExpedientePrograma;
	}
	public void setIdExpedientePrograma(Long idExpedientePrograma) {
		this.idExpedientePrograma = idExpedientePrograma;
	}
	
	@Column(name = "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name = "id_expediente")
	public int getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(int idExpediente) {
		this.idExpediente = idExpediente;
	}
	
	@Column(name = "programa")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	@Column(name = "expediente")
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
	@Column(name = "tipo")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "prioridad_expediente")
	public Integer getPrioridadExpediente() {
		return prioridadExpediente;
	}
	public void setPrioridadExpediente(Integer prioridadExpediente) {
		this.prioridadExpediente = prioridadExpediente;
	}
	
	@Column(name = "documentacion_opcional")
	public boolean isDocumentacionOpcional() {
		return documentacionOpcional;
	}
	public void setDocumentacionOpcional(boolean documentacionOpcional) {
		this.documentacionOpcional = documentacionOpcional;
	}
	
}
