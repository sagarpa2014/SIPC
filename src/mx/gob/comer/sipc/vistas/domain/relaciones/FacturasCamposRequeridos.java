package mx.gob.comer.sipc.vistas.domain.relaciones;



@SuppressWarnings("rawtypes")
public class FacturasCamposRequeridos implements Comparable{	
	
	private String claveBodega;	
	private String nombreEstado;
	private String folioContrato;
	private String paternoProductor;
	private String maternoProductor;
	private String nombreProductor;
	private String folioFacturaVenta;
	private String fechaEmisionFac;
	private String rfcFacVenta;
	private String volTotalFacVenta;
	private String volSolFacVenta;
	private String impSolFacVenta;
	private Double volTotalFacVentaD;

	
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
	
	public String getFolioFacturaVenta() {
		return folioFacturaVenta;
	}
	public void setFolioFacturaVenta(String folioFacturaVenta) {
		this.folioFacturaVenta = folioFacturaVenta;
	}
	public String getFechaEmisionFac() {
		return fechaEmisionFac;
	}
	public void setFechaEmisionFac(String fechaEmisionFac) {
		this.fechaEmisionFac = fechaEmisionFac;
	}
	public String getRfcFacVenta() {
		return rfcFacVenta;
	}
	public void setRfcFacVenta(String rfcFacVenta) {
		this.rfcFacVenta = rfcFacVenta;
	}
	public String getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	public void setVolTotalFacVenta(String volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	public String getVolSolFacVenta() {
		return volSolFacVenta;
	}
	public void setVolSolFacVenta(String volSolFacVenta) {
		this.volSolFacVenta = volSolFacVenta;
	}
	public String getImpSolFacVenta() {
		return impSolFacVenta;
	}
	public void setImpSolFacVenta(String impSolFacVenta) {
		this.impSolFacVenta = impSolFacVenta;
	}	
	
	public Double getVolTotalFacVentaD() {
		return volTotalFacVentaD;
	}
	
	public void setVolTotalFacVentaD(Double volTotalFacVentaD) {
		this.volTotalFacVentaD = volTotalFacVentaD;
	}
		
	@Override
	public int compareTo(Object o) {
		FacturasCamposRequeridos obj= (FacturasCamposRequeridos) o;     
	    if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(obj.nombreEstado) == 0){
	    		if((this.folioContrato==null?"":this.folioContrato).compareToIgnoreCase(obj.folioContrato==null?"":obj.folioContrato) == 0){
		    		if(this.paternoProductor.compareToIgnoreCase(obj.paternoProductor) == 0) {
		            	if(this.maternoProductor.compareToIgnoreCase(obj.maternoProductor) == 0) {
		            		  return this.nombreProductor.compareToIgnoreCase(obj.nombreProductor); 
		            	}else{
		            		return this.maternoProductor.compareTo(obj.maternoProductor);
		            	}	                 
		            } else { 
		                return this.paternoProductor.compareToIgnoreCase(obj.paternoProductor); 
		            }
		    	}else{
		    		return this.folioContrato.compareToIgnoreCase(obj.folioContrato);
		    	} 
	    	}else{
	    		return this.nombreEstado.compareToIgnoreCase(obj.nombreEstado);
	    	}
	    } else {
	            return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
	    }    		
	}

}
