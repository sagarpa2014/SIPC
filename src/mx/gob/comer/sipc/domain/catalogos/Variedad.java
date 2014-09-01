package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "variedad")
public class Variedad {
	
	private int idVariedad;
	private int idCultivo;
	private String variedad;
	
	@Id	
	@Column(name =  "id_variedad")
	public int getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(int idVariedad) {
		this.idVariedad = idVariedad;
	}
	
	@Column(name =  "id_cultivo")
	public int getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "variedad")
	public String getVariedad() {
		return variedad;
	}
	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}	

}
