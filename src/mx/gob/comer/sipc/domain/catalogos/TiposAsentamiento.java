package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_asentamiento")
public class TiposAsentamiento {
	private Integer tipoAsentHumano;
	private String descripcion;

	@Id
	@Column(name = "id_tipo_asentamiento")
	public Integer getTipoAsentHumano() {
		return tipoAsentHumano;
	}
	public void setTipoAsentHumano(Integer tipoAsentHumano) {
		this.tipoAsentHumano = tipoAsentHumano;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
