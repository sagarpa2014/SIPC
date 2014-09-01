package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "expedientes_compradores")
public class ExpedientesCompradores{
	/**
	 * 
	 */
	private Integer idExpedienteComprador;
	private Integer idComprador;
	private String rutaExpediente;
	private Integer idExpediente;

	@Id
	@Column(name = "id_expediente_comprador")
	@GeneratedValue(generator = "expedientes_compradores_id_expediente_comprador_seq")
	@SequenceGenerator(name = "expedientes_compradores_id_expediente_comprador_seq", sequenceName = "expedientes_compradores_id_expediente_comprador_seq")	
	public Integer getIdExpedienteComprador() {
		return idExpedienteComprador;
	}
	public void setIdExpedienteComprador(Integer idExpedienteComprador) {
		this.idExpedienteComprador = idExpedienteComprador;
	}
	
	@Column(name = "id_comprador")

	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name = "ruta_Expediente")
	public String getRutaExpediente() {
		return rutaExpediente;
	}
	public void setRutaExpediente(String rutaExpediente) {
		this.rutaExpediente = rutaExpediente;
	}
	
	@Column(name = "id_expediente")
	public Integer getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(Integer idExpediente) {
		this.idExpediente = idExpediente;
	}
	
}