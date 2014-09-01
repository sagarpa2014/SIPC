package mx.gob.comer.sipc.domain.catalogos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "expedientes_representante")
public class ExpedienteRepresentante implements Serializable{
	/**
	 * 
	 */
	private Long idExpedienteRepresentante;
	private Integer idRepresentante;
	private String rutaExpediente;
	private Integer idExpediente;
	
	  
	@Id
	@GeneratedValue(generator = "expedientes_representante_id_expediente_representante_seq")
	@SequenceGenerator(name = "expedientes_representante_id_expediente_representante_seq", sequenceName = "expedientes_representante_id_expediente_representante_seq")
	@Column(name = "id_expediente_representante")
	public Long getIdExpedienteRepresentante() {
		return idExpedienteRepresentante;
	}
	public void setIdExpedienteRepresentante(Long idExpedienteRepresentante) {
		this.idExpedienteRepresentante = idExpedienteRepresentante;
	}

	@Column(name = "id_representante")
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
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
		
}
