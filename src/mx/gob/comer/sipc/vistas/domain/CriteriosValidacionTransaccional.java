package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "criterios_validacion_transaccional")
public class CriteriosValidacionTransaccional {
		
	private long idCriterioValidacionTransaccional;
	private Long idCampoRelacion;
	private Integer idGrupo;
	private Integer idCampo;
	private Integer idCriterioValidacionRlFk;
	private Long idPerRel;
	
	@Id	
	@Column(name =  "id_criterio_validacion_transaccional")
	@GeneratedValue(generator = "criterios_validacion_transacc_id_criterio_validacion_transa_seq")
	@SequenceGenerator(name = "criterios_validacion_transacc_id_criterio_validacion_transa_seq", sequenceName = "criterios_validacion_transacc_id_criterio_validacion_transa_seq")
	public long getIdCriterioValidacionTransaccional() {
		return idCriterioValidacionTransaccional;
	}
	public void setIdCriterioValidacionTransaccional(
			long idCriterioValidacionTransaccional) {
		this.idCriterioValidacionTransaccional = idCriterioValidacionTransaccional;
	}
	
	@Column(name =  "id_campo_relacion")
	public Long getIdCampoRelacion() {
		return idCampoRelacion;
	}
	public void setIdCampoRelacion(Long idCampoRelacion) {
		this.idCampoRelacion = idCampoRelacion;
	}
	
	@Column(name =  "id_grupo")
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	@Column(name =  "id_campo")
	public Integer getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(Integer idCampo) {
		this.idCampo = idCampo;
	}
	@Column(name =  "id_criterio_validacion_rl_fk")
	public Integer getIdCriterioValidacionRlFk() {
		return idCriterioValidacionRlFk;
	}
	public void setIdCriterioValidacionRlFk(Integer idCriterioValidacionRlFk) {
		this.idCriterioValidacionRlFk = idCriterioValidacionRlFk;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}	
}
