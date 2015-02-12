package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "criterios_validacion_relacion_df_v")
public class CriteriosValidacionRelacionDFV {

	private long idCriterioValidacionRlDf;
	private int idGrupo;
	private int idCampo;
	private int idCriterio;
	private String criterio;
		
	@Id	
	@Column(name =  "id_criterio_validacion_rl_df")
	public long getIdCriterioValidacionRlDf() {
		return idCriterioValidacionRlDf;
	}
	public void setIdCriterioValidacionRlDf(long idCriterioValidacionRlDf) {
		this.idCriterioValidacionRlDf = idCriterioValidacionRlDf;
	}
	
	@Column(name =  "id_grupo")
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	@Column(name =  "id_campo")
	public int getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}
	
	@Column(name =  "id_criterio")
	public int getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(int idCriterio) {
		this.idCriterio = idCriterio;
	}
	
	@Column(name =  "criterio")
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}	
	
//	id_criterio_validacion_rl_df cvrdf.id_grupo, cvrdf.id_campo, cvrdf.id_criterio, ccv.criterio
		
}
