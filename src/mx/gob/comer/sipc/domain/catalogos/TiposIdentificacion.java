package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_identificacion")
public class TiposIdentificacion {
	private Integer tipoIdentificacion;
	private String descripcion;

	@Id
	@Column(name = "id_tipo_identificacion")
	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
