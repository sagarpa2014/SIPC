package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cultivo")
public class Cultivo {
	private int idCultivo;
	private String cultivo;
	
	@Id
	@Column(name =  "id_cultivo")
	public int getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "cultivo")
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	
	

}
