package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unidad_medida")
public class UnidadMedida {
	
	private int idUnidadMedida;
	private String unidadMedida;
	private String abreviatura;
	
	@Id
	@Column(name =  "id_unidad_medida")
	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	
	@Column(name =  "unidad_medida")
	public String getUnidadMedida() {
		return unidadMedida;
	}
	
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	@Column(name =  "abreviatura")
	public String getAbreviatura() {
		return abreviatura;
	}
	
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	

}
