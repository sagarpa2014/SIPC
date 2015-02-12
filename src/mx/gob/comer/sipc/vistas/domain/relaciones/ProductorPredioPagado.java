package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@SuppressWarnings("rawtypes")
@Entity
public class ProductorPredioPagado implements Comparable{
	@Id
	@Column(name =  "productor")
	private Long folioProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "curp_productor")
	private String curpProductor;
	@Column(name =  "rfc_productor")
	private String rfcProductor;
	
	public Long getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(Long folioProductor) {
		this.folioProductor = folioProductor;
	}
	public String getNombreProductor() {
		return nombreProductor;
	}
	public void setNombreProductor(String nombreProductor) {
		this.nombreProductor = nombreProductor;
	}
	public String getPaternoProductor() {
		return paternoProductor;
	}
	public void setPaternoProductor(String paternoProductor) {
		this.paternoProductor = paternoProductor;
	}
	public String getMaternoProductor() {
		return maternoProductor;
	}
	public void setMaternoProductor(String maternoProductor) {
		this.maternoProductor = maternoProductor;
	}
	public String getCurpProductor() {
		return curpProductor;
	}
	public void setCurpProductor(String curpProductor) {
		this.curpProductor = curpProductor;
	}
	public String getRfcProductor() {
		return rfcProductor;
	}
	public void setRfcProductor(String rfcProductor) {
		this.rfcProductor = rfcProductor;
	}
	
	@Override
	public int compareTo(Object o) {
		ProductorPredioPagado cheque = (ProductorPredioPagado) o;     	    		
		if(this.paternoProductor.compareToIgnoreCase(cheque.paternoProductor) == 0) {
        	if(this.maternoProductor.compareToIgnoreCase(cheque.maternoProductor) == 0) {
        		  return this.nombreProductor.compareToIgnoreCase(cheque.nombreProductor); 
        	}else{
        		return this.maternoProductor.compareTo(cheque.maternoProductor);
        	}	                 
        } else { 
            return this.paternoProductor.compareToIgnoreCase(cheque.paternoProductor); 
        }
	    	 		
	}

}
