package mx.gob.comer.sipc.domain.catalogos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paises")
public class Pais {
	
	@Id
	@Column(name = "id_pais")
	private Integer idPais;
	@Column(name = "pais")
	private String pais;
	@Column(name = "clave_pais")
	private String clavePais;
	
	
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getClavePais() {
		return clavePais;
	}
	public void setClavePais(String clavePais) {
		this.clavePais = clavePais;
	}
	
	
	
	
}
