package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_relacion_ciclos_v")
public class ProgramaRelacionCiclosV{
	
	private Long idPgrCiclo;
	private Long idPerRel;
	private Integer idCiclo;
	private Integer ejercicio;
	private String cicloLargo;
	private Long idIniEsquemaRelacion;

	@Id	
	@Column(name =  "id_pgr_ciclo")
	public long getIdPgrCiclo() {
		return idPgrCiclo;
	}

	public void setIdPgrCiclo(long idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}
	
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}

	@Column(name =  "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}

	@Column(name =  "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}

	@Column(name =  "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}

	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}

	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}

	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
}