package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_criterios_validacion")
public class CatCriteriosValidacion {
	
	@Id	
	@Column(name =  "id_criterio")
	private int idCriterio;
	@Column(name =  "criterio")
	private String criterio;
	@Column(name =  "pide_periodo")
	private String pidePeriodo;
	@Column(name =  "grupo")
	private String grupo;
	@Column(name =  "prioridad")
	private Integer prioridad;

	
	public CatCriteriosValidacion() {
		
	}
	public CatCriteriosValidacion(int idCriterio, String criterio) {
		super();
		this.idCriterio = idCriterio;
		this.criterio = criterio;
	}
	public int getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(int idCriterio) {
		this.idCriterio = idCriterio;
	}
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	public String getPidePeriodo() {
		return pidePeriodo;
	}
	public void setPidePeriodo(String pidePeriodo) {
		this.pidePeriodo = pidePeriodo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	
}
