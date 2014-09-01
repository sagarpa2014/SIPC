package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_vialidad")
public class TiposVialidad {
	private Integer tipoVialidad;
	private String descripcion;

	@Id
	@Column(name = "id_tipo_vialidad")
	public Integer getTipoVialidad() {
		return tipoVialidad;
	}
	public void setTipoVialidad(Integer tipoVialidad) {
		this.tipoVialidad = tipoVialidad;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
