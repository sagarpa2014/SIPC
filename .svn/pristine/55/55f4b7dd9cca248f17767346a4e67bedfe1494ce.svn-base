package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "especialistas")
public class Especialista {
	
	private Long idEspecialista;
	private String nombre;
	private String puesto;
	
	@Id
	@Column(name = "id_especialista")
	public Long getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(Long idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "puesto")
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
}
