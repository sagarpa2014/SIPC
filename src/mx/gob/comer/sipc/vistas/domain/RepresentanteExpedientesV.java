package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "representante_expedientes_v")
public class RepresentanteExpedientesV {

	private Long idExpedienteRepresentante;
	private Integer idExpediente;	
	private String expediente;
	private String rutaExpediente;
	private Integer idRepresentante;
	private String representante;
	
	@Id
	@Column(name = "id_expediente_representante")
	public Long getIdExpedienteRepresentante() {
		return idExpedienteRepresentante;
	}
	public void setIdExpedienteRepresentante(Long idExpedienteRepresentante) {
		this.idExpedienteRepresentante = idExpedienteRepresentante;
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
	@Column(name = "expediente")
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
	@Column(name = "id_representante")
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	@Column(name = "representante")
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	
}
