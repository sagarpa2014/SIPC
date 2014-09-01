package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ProgramaCultivo {
	
	private int idCultivo;
	private int idPrograma;
	private String cultivo;
	
	@Id
	@Column(name = "id_cultivo")
	public int getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	@Column(name = "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name = "cultivo")
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	

}
