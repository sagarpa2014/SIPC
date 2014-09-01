package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ciclos_programas_v")
public class CiclosProgramasV {
	
	private int idCicloPrograma;
	private int idCiclo;
	private int idPrograma;
	private Integer ejercicio;
	private String cicloLargo;
	private String cicloCorto;

	@Id
	@Column(name = "id_ciclo_programa")	
	public int getIdCicloPrograma() {
		return idCicloPrograma;
	}
	public void setIdCicloPrograma(int idCicloPrograma) {
		this.idCicloPrograma = idCicloPrograma;
	}
	
	@Column(name = "id_ciclo")	
	public int getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(int idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name = "id_programa")	
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	@Column(name = "id_ejercicio")	
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	@Column(name = "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}
	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}
	
	@Column(name = "ciclo_corto")	
	public String getCicloCorto() {
		return cicloCorto;
	}
	public void setCicloCorto(String cicloCorto) {
		this.cicloCorto = cicloCorto;
	}	

}
