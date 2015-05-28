package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regionales")
public class Regional{
	
	@Id
	@Column(name = "id_regional")
	private int 	idRegional;
	@Column(name = "regional")
	private String 	regional;
	public int getIdRegional() {
		return idRegional;
	}
	public void setIdRegional(int idRegional) {
		this.idRegional = idRegional;
	}
	
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	
}
