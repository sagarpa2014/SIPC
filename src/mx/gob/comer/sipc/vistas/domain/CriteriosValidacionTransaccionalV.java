package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "criterios_validacion_transaccional_v")
public class CriteriosValidacionTransaccionalV {
	
	@Id
	@Column(name = "id_criterio_validacion_transaccional")	
	private long idCriterioValidacionTransaccional;
	@Column(name = "id_campo_relacion")
	private Long idCampoRelacion;
	@Column(name = "id_grupo")
	private Integer idGrupo;
	@Column(name = "id_campo")
	private Integer idCampo;
	@Column(name = "id_criterio_validacion_rl_fk")
	private Long idCriterioValidacionRlFk;
	@Column(name = "id_per_rel")
	private Long idPerRel;
	@Column(name = "id_criterio")
	private Integer idCriterio;
	@Column(name = "criterio")
	private String criterio;
	
	
	
	public CriteriosValidacionTransaccionalV(
			long idCriterioValidacionRlFk, String criterio) {
		super();
		this.idCriterioValidacionRlFk = idCriterioValidacionRlFk;
		this.criterio = criterio;
	}
		
	public CriteriosValidacionTransaccionalV(
			long idCriterioValidacionRlFk, 
			String criterio,Long idCampoRelacion) {
		super();
		this.idCriterioValidacionRlFk = idCriterioValidacionRlFk;
		this.criterio = criterio;
		this.idCampoRelacion = idCampoRelacion;
	}



	

	public long getIdCriterioValidacionTransaccional() {
		return idCriterioValidacionTransaccional;
	}
	public void setIdCriterioValidacionTransaccional(
			long idCriterioValidacionTransaccional) {
		this.idCriterioValidacionTransaccional = idCriterioValidacionTransaccional;
	}
	
	
	public Long getIdCampoRelacion() {
		return idCampoRelacion;
	}
	
	public void setIdCampoRelacion(Long idCampoRelacion) {
		this.idCampoRelacion = idCampoRelacion;
	}
	
	public Long getIdCriterioValidacionRlFk() {
		return idCriterioValidacionRlFk;
	}

	public void setIdCriterioValidacionRlFk(Long idCriterioValidacionRlFk) {
		this.idCriterioValidacionRlFk = idCriterioValidacionRlFk;
	}

	public Long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}	

}
