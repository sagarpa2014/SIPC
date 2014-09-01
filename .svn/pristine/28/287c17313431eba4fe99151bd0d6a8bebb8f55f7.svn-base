package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_num_cartas_v")
public class ProgramaNumCartasV {
	private Integer idPrograma;	
	private String programa;
	private Integer numeroCartas;
	
	@Id	
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

	@Column(name = "num_cartas")
	public Integer getNumeroCartas() {
		return numeroCartas;
	}
	
	public void setNumeroCartas(Integer numeroCartas) {
		this.numeroCartas = numeroCartas;
	}
}
