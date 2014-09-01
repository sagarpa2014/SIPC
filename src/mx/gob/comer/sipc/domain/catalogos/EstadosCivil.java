package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_civil")
public class EstadosCivil {
	private Integer estadoCivil;
	private String descripcion;

	@Id
	@Column(name = "id_estado_civil")
	public Integer getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
