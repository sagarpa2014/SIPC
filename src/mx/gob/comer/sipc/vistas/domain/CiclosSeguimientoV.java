package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ciclos_seguimiento_v")
public class CiclosSeguimientoV {
	
	private long idCiclo;
	private String ciclo;
	private Integer ejercicio;

	@Id
	@Column(name =  "id")
	public long getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(long idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name =  "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name =  "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}	
}