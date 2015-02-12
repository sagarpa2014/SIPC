package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupos_campos_relacion_default_v")
public class GruposCamposRelacionDefaultV {

	private Long idGpoCampoRelacionD;
	private Integer idRelacion;
	private String relacion;
	private Integer idGrupo;
	private String grupo;
	private Integer posicionGrupo;
	private String tipoSeccion;
	private String tipoGrupo;
	private Integer idCampo;
	private String campo;
	private Integer posicionCampo;
	
	@Id
	@Column(name = "id_gpo_campo_relacion_d")
	public Long getIdGpoCampoRelacionD() {
		return idGpoCampoRelacionD;
	}
	public void setIdGpoCampoRelacionD(Long idGpoCampoRelacionD) {
		this.idGpoCampoRelacionD = idGpoCampoRelacionD;
	}
	
	@Column(name = "id_tipo_relacion")
	public Integer getIdRelacion() {
		return idRelacion;
	}
	public void setIdRelacion(Integer idRelacion) {
		this.idRelacion = idRelacion;
	}
	
	@Column(name = "relacion")
	public String getRelacion() {
		return relacion;
	}
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	
	@Column(name = "grupo")
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
		
	@Column(name = "posicion_grupo")
	public Integer getPosicionGrupo() {
		return posicionGrupo;
	}
	public void setPosicionGrupo(Integer posicionGrupo) {
		this.posicionGrupo = posicionGrupo;
	}
	
	@Column(name = "id_grupo")
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	@Column(name = "tipo_seccion")
	public String getTipoSeccion() {
		return tipoSeccion;
	}
	
	public void setTipoSeccion(String tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}
	
	@Column(name = "tipo_grupo")
	public String getTipoGrupo() {
		return tipoGrupo;
	}
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	
	@Column(name = "id_campo")
	public Integer getIdCampo() {
		return idCampo;
	}
	
	public void setIdCampo(Integer idCampo) {
		this.idCampo = idCampo;
	}
	
	@Column(name = "campo")
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	@Column(name = "posicion_campo")
	public Integer getPosicionCampo() {
		return posicionCampo;
	}
	public void setPosicionCampo(Integer posicionCampo) {
		this.posicionCampo = posicionCampo;
	}
	
}