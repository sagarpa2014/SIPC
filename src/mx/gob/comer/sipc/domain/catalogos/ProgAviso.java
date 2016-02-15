package mx.gob.comer.sipc.domain.catalogos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prog_aviso")
public class ProgAviso {
	
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
