package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrediosNoPagados implements Comparable {	
	@Id
	@Column(name =  "id")
	private String id;
	@Column(name =  "folio_predio")
	private String folioPredio;
	@Column(name =  "folio_predio_sec")
	private Integer folioPredioSec;
	@Column(name =  "folio_predio_alterno")
	private String folioPredioAlterno;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFolioPredio() {
		return folioPredio;
	}
	public void setFolioPredio(String folioPredio) {
		this.folioPredio = folioPredio;
	}
	public Integer getFolioPredioSec() {
		return folioPredioSec;
	}
	public void setFolioPredioSec(Integer folioPredioSec) {
		this.folioPredioSec = folioPredioSec;
	}
	public String getFolioPredioAlterno() {
		return folioPredioAlterno;
	}
	public void setFolioPredioAlterno(String folioPredioAlterno) {
		this.folioPredioAlterno = folioPredioAlterno;
	}	
	
	@Override
	public int compareTo(Object o) {
		PrediosNoPagados  predio = (PrediosNoPagados) o;     
	    if(this.folioPredio.compareToIgnoreCase(predio.folioPredio) == 0) { 
	    	if(this.folioPredioSec.toString().compareTo(predio.folioPredioSec.toString()) == 0){
	    		return this.folioPredioAlterno.compareToIgnoreCase(predio.folioPredioAlterno);     
	    	}else{
	    		return this.folioPredioSec.toString().compareTo(predio.folioPredioSec.toString());
	    	}             
	    }else{
	    	return this.folioPredio.compareToIgnoreCase(predio.folioPredio); 
	    }    		
	}
	
}
