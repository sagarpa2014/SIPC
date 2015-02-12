package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GrupoPorRelacion {
	
	private Integer idGrupo;
	private String grupo;
	private Integer posicionGrupo;
	private Integer numeroCampos;
	private Boolean visible;
	
	@Id
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
	
	@Column(name = "posicion_grupo")
	public Integer getPosicionGrupo() {
		return posicionGrupo;
	}
	public void setPosicionGrupo(Integer posicionGrupo) {
		this.posicionGrupo = posicionGrupo;
	}
	
	@Column(name = "numero_campos")
	public Integer getNumeroCampos() {
		return numeroCampos;
	}
	public void setNumeroCampos(Integer numeroCampos) {
		this.numeroCampos = numeroCampos;
	}
	
	@Column(name = "visible")
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}
