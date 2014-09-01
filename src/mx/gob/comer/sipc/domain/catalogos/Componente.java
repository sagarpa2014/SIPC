package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_componente")
public class Componente {
	
	private int idComponente;
	private String componente;
	private String acronimo;
	
	@Id
	@Column(name =  "id_componente")
	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	
	@Column(name =  "componente")
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	
	@Column(name =  "acronimo")
	public String getAcronimo() {
		return acronimo;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}	

}
