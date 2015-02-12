package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@SuppressWarnings("rawtypes")
@Entity
public class RfcProductorVsRfcFactura implements Comparable  {	
	
	@Id
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "nombre_estado")
	private String nombreEstado;
	@Column(name =  "folio_contrato")
	private String folioContrato;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "rfc_productor")
	private String rfcProductor;
	@Column(name =  "rfc_fac_venta")
	private String rfcFacVenta;	
	
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
		
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}	
	
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
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
	public String getNombreProductor() {
		return nombreProductor;
	}
	public void setNombreProductor(String nombreProductor) {
		this.nombreProductor = nombreProductor;
	}
	public String getRfcProductor() {
		return rfcProductor;
	}
	
	public void setRfcProductor(String rfcProductor) {
		this.rfcProductor = rfcProductor;
	}
	
	public String getRfcFacVenta() {
		return rfcFacVenta;
	}
	
	public void setRfcFacVenta(String rfcFacVenta) {
		this.rfcFacVenta = rfcFacVenta;
	}	
	
	@Override
	public int compareTo(Object o) {
		RfcProductorVsRfcFactura p = (RfcProductorVsRfcFactura) o;     
	    if(this.claveBodega.compareToIgnoreCase(p.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(p.nombreEstado) == 0){
	    		if(this.paternoProductor.compareToIgnoreCase(p.paternoProductor) == 0) {
	            	if(this.maternoProductor.compareToIgnoreCase(p.maternoProductor) == 0) {
	            		  return this.nombreProductor.compareToIgnoreCase(p.nombreProductor); 
	            	}else{
	            		return this.maternoProductor.compareTo(p.maternoProductor);
	            	}	                 
	            } else { 
	                return this.paternoProductor.compareToIgnoreCase(p.paternoProductor); 
	            }	
	    	}else{
		    	return this.nombreEstado.compareToIgnoreCase(p.nombreEstado);	
	    	}
	    } else {
	    	return this.claveBodega.compareToIgnoreCase(p.claveBodega); 
	    }    		
	}
}
