package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_uso_grano")
public class TipoUsoGrano {

	@Id	
	@Column(name =  "id")
	private Long id;
	@Column(name =  "uso_grano")
	private String usoGrano;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsoGrano() {
		return usoGrano;
	}
	public void setUsoGrano(String usoGrano) {
		this.usoGrano = usoGrano;
	}
		
}
