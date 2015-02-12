package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupos_campos_relacion_v")
public class GruposCamposRelacionV {

	private Long idCampoRelacion;
	private Integer idTipoRelacion;
	private String  relacion;
	private Integer idPerRel;
	private String  cicloAgricola;
	private Integer idGrupo;
	private String  grupo;
	private Boolean visible;
	private String  tipoSeccion;
	private String tipoGrupo;
	private Integer posicionGrupo;
	private Integer idCampo;
	private String campo;
	private Integer posicionCampo;
	private String tipoDato;
	private Boolean opcional;
	
	
	
	public GruposCamposRelacionV(){
	            
	}
	public GruposCamposRelacionV(Long idCampoRelacion, Integer idGrupo, String grupo, Integer idCampo, String campo) {
	   super();
	   this.idCampoRelacion = idCampoRelacion;
	   this.idGrupo = idGrupo;
	   this.grupo = grupo;
	   this.idCampo = idCampo;
	   this.campo = campo;
	}
	
	public GruposCamposRelacionV(Long idCampoRelacion, Integer idGrupo, String grupo, Integer posicionGrupo, Integer idCampo, String campo, Integer posicionCampo, Boolean visible, Boolean opcional) {
		super();
		this.idCampoRelacion = idCampoRelacion;
		this.idGrupo = idGrupo;
		this.grupo = grupo;
		this.posicionGrupo = posicionGrupo;
		this.idCampo = idCampo;
		this.campo = campo;
		this.posicionCampo = posicionCampo;
		this.visible = visible;
		this.opcional = opcional;		
	}
	
	public GruposCamposRelacionV( String campo) {
		super();
		this.campo = campo;
	}
	
	@Id
	@Column(name = "id_campo_relacion")
	public Long getIdCampoRelacion() {
	    return idCampoRelacion;
	}
	public void setIdCampoRelacion(Long idCampoRelacion) {
	    this.idCampoRelacion = idCampoRelacion;
	}
	
	@Column(name = "id_tipo_relacion")
	public Integer getIdTipoRelacion() {
	    return idTipoRelacion;
	}
	
	public void setIdTipoRelacion(Integer idTipoRelacion) {
		this.idTipoRelacion = idTipoRelacion;
	}
	
	@Column(name = "relacion")
	public String getRelacion() {
	    return relacion;
	}
	public void setRelacion(String relacion) {
	    this.relacion = relacion;
	}
	
	@Column(name = "id_per_rel")
	public Integer getIdPerRel() {
	    return idPerRel;
	}
	public void setIdPerRel(Integer idPerRel) {
	    this.idPerRel = idPerRel;
	}
	
	@Column(name = "ciclo_agricola")
	public String getCicloAgricola() {
	    return cicloAgricola;
	}
	
	public void setCicloAgricola(String cicloAgricola) {
	    this.cicloAgricola = cicloAgricola;
	}
	
	
	@Column(name = "id_grupo")
	public Integer getIdGrupo() {
	    return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
	   this.idGrupo = idGrupo;
	}
	
	@Column(name = "grupo")
	public String getGrupo() {
	    return grupo;
	}
	public void setGrupo(String grupo) {
	    this.grupo = grupo;
	}
	
	@Column(name = "visible")
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
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
	
	@Column(name = "posicion_grupo")
	public Integer getPosicionGrupo() {
	    return posicionGrupo;
	}
	public void setPosicionGrupo(Integer posicionGrupo) {
	    this.posicionGrupo = posicionGrupo;
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

	@Column(name = "tipo_dato")
	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	 
	@Column(name = "opcional")
	public Boolean getOpcional() {
		return opcional;
	}
	public void setOpcional(Boolean opcional) {
		this.opcional = opcional;
	}
	
}
