package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_estado_v")
public class ProgramaEstadoV{
	
	private int idPgrEdo;
	private int idPrograma;
	private int idEstado;
	private String ciclo;
	private String estado;
	private String nomProgramaCorto;
	
	@Id
	@Column(name =  "id_pgr_edo")
	public int getIdPgrEdo() {
		return idPgrEdo;
	}
	public void setIdPgrEdo(int idPgrEdo) {
		this.idPgrEdo = idPgrEdo;
	}
	
	@Column(name =  "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_estado")
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name =  "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name =  "estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name =  "nom_programa_corto")
	public String getNomProgramaCorto() {
		return nomProgramaCorto;
	}
	public void setNomProgramaCorto(String nomProgramaCorto) {
		this.nomProgramaCorto = nomProgramaCorto;
	}

}
