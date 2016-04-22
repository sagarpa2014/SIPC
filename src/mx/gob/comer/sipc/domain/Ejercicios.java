package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ejercicios")
public class Ejercicios {
	/**
	 * 
	 */
	protected int idEjercicio;
	protected int ejercicio;

	@Id
	@Column(name =  "id_ejercicio")
	public Integer getIdEjercicio() {
		return idEjercicio;
	}
	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}
	
	@Column(name =  "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}
	
	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	
		
}