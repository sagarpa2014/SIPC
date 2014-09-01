package mx.gob.comer.sipc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "expedientes")
public class Expediente implements Serializable{
	/**
	 * 
	 */
	private Long idExpediente;
	private String expediente;
	private String descripcion;
	private String tipoExpediente;
	private Integer prioridadExpediente;
	
	@Id
	@Column(name = "id_expediente")
	public Long getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(Long idExpediente) {
		this.idExpediente = idExpediente;
	}
	
	@Column(name = "expediente")
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "tipo")
	public String getTipoExpediente() {
		return tipoExpediente;
	}
	public void setTipoExpediente(String tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}
	
	@Column(name = "prioridad_expediente")
	public Integer getPrioridadExpediente() {
		return prioridadExpediente;
	}
	public void setPrioridadExpediente(Integer prioridadExpediente) {
		this.prioridadExpediente = prioridadExpediente;
	}
	
	
			
}
