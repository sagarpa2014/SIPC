package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_relaciones")
public class Relaciones {
	
	private int idRelacion ;
	private String relacion;
	
	@Id	
	@Column(name = "id_tipo_relacion")
	public int getIdRelacion() {
		return idRelacion;
	}
	
	public void setIdRelacion(int idRelacion) {
		this.idRelacion = idRelacion;
	}
	
	@Column(name = "relacion")
	public String getRelacion() {
		return relacion;
	}
	
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
		
}
