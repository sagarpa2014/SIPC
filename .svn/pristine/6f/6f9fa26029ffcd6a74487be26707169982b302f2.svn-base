package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prg_especialista_num_cartas_v")
public class PrgEspecialistaNumCartasV {

	private Long idPk;
	private Integer idPrograma;	
	private String programa;
	private String idEspecialista;
	private Integer numeroCartas;
	
	@Id
	@Column(name = "id_pk")
	public Long getIdPk() {
		return idPk;
	}
	public void setIdPk(Long idPk) {
		this.idPk = idPk;
	}
	
	@Column(name = "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name = "descripcion_corta")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	@Column(name = "id_especialista")
	public String getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(String idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	
	@Column(name = "num_cartas")
	public Integer getNumeroCartas() {
		return numeroCartas;
	}
	public void setNumeroCartas(Integer numeroCartas) {
		this.numeroCartas = numeroCartas;
	}
}
