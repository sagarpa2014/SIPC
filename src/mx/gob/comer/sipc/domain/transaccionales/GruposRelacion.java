package mx.gob.comer.sipc.domain.transaccionales;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "grupos_relacion")
public class GruposRelacion {

	private long idGrupoRelacion;
	private Long idPerRel;
	private Integer idGrupo ;
	private Boolean visible;
	private Integer posicion;
	//private Set<CamposRelacion> camposRelacion;
	
	@Id	
	@Column(name =  "id_grupo_relacion")
	@GeneratedValue(generator = "grupos_relacion_id_grupo_relacion_seq")
	@SequenceGenerator(name = "grupos_relacion_id_grupo_relacion_seq", sequenceName = "grupos_relacion_id_grupo_relacion_seq")
	public long getIdGrupoRelacion() {
		return idGrupoRelacion;
	}
	public void setIdGrupoRelacion(long idGrupoRelacion) {
		this.idGrupoRelacion = idGrupoRelacion;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}
	
	@Column(name =  "id_grupo")	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	@Column(name =  "visible ")		
	public Boolean getVisible() {
		return visible;
	}
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	@Column(name =  "posicion")
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	
//	@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
//	@JoinColumn(name="id_grupo_relacion", nullable=false)
//	public Set<CamposRelacion> getCamposRelacion() {
//		return camposRelacion;
//	}
//	public void setCamposRelacion(Set<CamposRelacion> camposRelacion) {
//		this.camposRelacion = camposRelacion;
//	}
	

}
