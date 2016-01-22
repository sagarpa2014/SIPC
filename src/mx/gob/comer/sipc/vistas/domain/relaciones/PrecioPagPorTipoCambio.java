package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;



@SuppressWarnings("rawtypes")
public class PrecioPagPorTipoCambio implements Comparable{
		
	private String claveBodega;
	private String nombreEstado;
	private String folioContrato; 
	private String nombreProductor;
	private String paternoProductor;
	private String maternoProductor;
	private String curpProductor;
	private String rfcProductor;
	private String folioFacturaVenta;  
	private Date fechaEmisionFac;
	private Double volTotalFacVenta;
	private Double impSolFacVenta;
	private Double precioFacMxpTon;
	private Double precioPactadoPorTonelada;
	private Double tipoCambio;
	private Double precioCalculadoEnPesos;
	private Double importeCalculadoPagar;
	private Double difMontoXFac;
	private Double difMontoTotal;
	
	
	
	
	
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
	public String getFolioFacturaVenta() {
		return folioFacturaVenta;
	}
	public void setFolioFacturaVenta(String folioFacturaVenta) {
		this.folioFacturaVenta = folioFacturaVenta;
	}
	public Date getFechaEmisionFac() {
		return fechaEmisionFac;
	}
	public void setFechaEmisionFac(Date fechaEmisionFac) {
		this.fechaEmisionFac = fechaEmisionFac;
	}	
	
	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	public Double getImpSolFacVenta() {
		return impSolFacVenta;
	}
	public void setImpSolFacVenta(Double impSolFacVenta) {
		this.impSolFacVenta = impSolFacVenta;
	}
	public Double getPrecioPactadoPorTonelada() {
		return precioPactadoPorTonelada;
	}
	public void setPrecioPactadoPorTonelada(Double precioPactadoPorTonelada) {
		this.precioPactadoPorTonelada = precioPactadoPorTonelada;
	}
	public Double getPrecioFacMxpTon() {
		return precioFacMxpTon;
	}
	public void setPrecioFacMxpTon(Double precioFacMxpTon) {
		this.precioFacMxpTon = precioFacMxpTon;
	}	
	public Double getPrecioCalculadoEnPesos() {
		return precioCalculadoEnPesos;
	}
	public void setPrecioCalculadoEnPesos(Double precioCalculadoEnPesos) {
		this.precioCalculadoEnPesos = precioCalculadoEnPesos;
	}
	public Double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public Double getImporteCalculadoPagar() {
		return importeCalculadoPagar;
	}
	public void setImporteCalculadoPagar(Double importeCalculadoPagar) {
		this.importeCalculadoPagar = importeCalculadoPagar;
	}
	public Double getDifMontoXFac() {
		return difMontoXFac;
	}
	public void setDifMontoXFac(Double difMontoXFac) {
		this.difMontoXFac = difMontoXFac;
	}
	public Double getDifMontoTotal() {
		return difMontoTotal;
	}
	public void setDifMontoTotal(Double difMontoTotal) {
		this.difMontoTotal = difMontoTotal;
	}	
	
	
	@Override
	public int compareTo(Object o) {
		PrecioPagPorTipoCambio obj= (PrecioPagPorTipoCambio) o;     
	    //if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) { 
	    	//if(this.nombreEstado.compareToIgnoreCase(obj.nombreEstado) == 0){
	    		if((this.folioContrato==null?"":this.folioContrato).compareToIgnoreCase(obj.folioContrato==null?"":obj.folioContrato) == 0){	    			
	    				if(this.paternoProductor.compareToIgnoreCase(obj.paternoProductor) == 0) {
			            	if(this.maternoProductor.compareToIgnoreCase(obj.maternoProductor) == 0) {
			            		if(this.nombreProductor.compareToIgnoreCase(obj.nombreProductor) == 0) {
			            			return this.claveBodega.compareToIgnoreCase(obj.claveBodega);
			            		}else{
			            			return this.nombreProductor.compareToIgnoreCase(obj.nombreProductor);
			            		}
			            		   
			            	}else{
			            		return this.maternoProductor.compareTo(obj.maternoProductor);
			            	}	                 
			            } else { 
			                return this.paternoProductor.compareToIgnoreCase(obj.paternoProductor); 
			            }
	    				    		
		    	}else{
		    		return this.folioContrato.compareToIgnoreCase(obj.folioContrato);
		    	} 
	    	}//else{
	    		//return this.nombreEstado.compareToIgnoreCase(obj.nombreEstado);
	    	//}
	    //} else {
	      //      return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
	    //}    		
	//}

}
