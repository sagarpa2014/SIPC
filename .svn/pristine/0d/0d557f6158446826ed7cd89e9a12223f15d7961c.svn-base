package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ciclos")
public class Etapas {
	
	private int idCiclo;
	private String cicloLargo;
	private String cicloCorto;
	
	@Id	
	@GeneratedValue(generator = "ciclos_id_ciclo_seq")
	@SequenceGenerator(name = "ciclos_id_ciclo_seq", sequenceName = "ciclos_id_ciclo_seq")
	@Column(name =  "id_ciclo")
	public int getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(int idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name =  "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}
	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}
	
	@Column(name =  "ciclo_corto")
	public String getCicloCorto() {
		return cicloCorto;
	}
	public void setCicloCorto(String cicloCorto) {
		this.cicloCorto = cicloCorto;
	}
	
	

}
