package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class ChequesDuplicadoBancoPartipante implements Comparable{		
	@Id
	@Column(name =  "id")
	private String id;
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
	@Column(name =  "folio_doc_pago")
	private String folioDocPago;
	@Column(name =  "banco_sinaxc")
	private String bancoSinaxc;
	@Column(name =  "vol_total_fac_venta")
	private Double volTotalFacVenta ;
		
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

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

	public String getFolioDocPago() {
		return folioDocPago;
	}

	public void setFolioDocPago(String folioDocPago) {
		this.folioDocPago = folioDocPago;
	}

	public String getBancoSinaxc() {
		return bancoSinaxc;
	}

	public void setBancoSinaxc(String bancoSinaxc) {
		this.bancoSinaxc = bancoSinaxc;
	}

	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}

	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	
	@Override
	public int compareTo(Object o) {
		ChequesDuplicadoBancoPartipante obj= (ChequesDuplicadoBancoPartipante) o;     
	    if(this.folioDocPago.compareToIgnoreCase(obj.folioDocPago) == 0) { 
	    	if(this.paternoProductor.compareToIgnoreCase(obj.paternoProductor) == 0){
		    	if(this.maternoProductor.compareToIgnoreCase(obj.maternoProductor) == 0){
		    		if(this.nombreProductor.compareToIgnoreCase(obj.nombreProductor) == 0) {
		            	if(this.folioContrato.compareToIgnoreCase(obj.folioContrato) == 0) {
		            		  return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
		            	}else{
		            		return this.folioContrato.compareTo(obj.folioContrato);
		            	}	                 
		            } else { 
		                return this.nombreProductor.compareToIgnoreCase(obj.nombreProductor); 
		            }
		    	}else{
		    		return this.maternoProductor.compareToIgnoreCase(obj.maternoProductor);
		    	} 
	    	}else{
	    		return this.paternoProductor.compareToIgnoreCase(obj.paternoProductor);
	    	}
	    } else {
	            return this.folioDocPago.compareToIgnoreCase(obj.folioDocPago); 
	    }    		
	}
}
