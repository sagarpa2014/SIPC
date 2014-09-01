package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "temp")
public class Temp {
	
	private int id;
	private String name;
	private String bod;
	private double vol;

	
	
	@Id
	@GeneratedValue(generator = "temp_id_seq")
	@SequenceGenerator(name = "temp_id_seq", sequenceName = "temp_id_seq")	
	@Column(name =  "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name =  "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name =  "bod")
	public String getBod() {
		return bod;
	}
	public void setBod(String bod) {
		this.bod = bod;
	}
	
	@Column(name =  "vol")
	public double getVol() {
		return vol;
	}
	public void setVol(double vol) {
		this.vol = vol;
	}

}
