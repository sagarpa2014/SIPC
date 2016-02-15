package mx.gob.comer.sipc.domain.catalogos;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.gob.comer.sipc.domain.PagosDetalle;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDofDetalle;

@Entity
@Table(name = "apoyo_aviso")
public class ApoyoAviso {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
