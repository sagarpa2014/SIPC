package mx.gob.comer.sipc.vistas.domain.relaciones;



@SuppressWarnings("rawtypes")
public class PagosCamposRequeridos implements Comparable{	
	
	private Long idRelacionComprasTmp;
	private String claveBodega;	
	private String nombreEstado;
	private String folioContrato;
	private String paternoProductor;
	private String maternoProductor;
	private String nombreProductor;
	private String curpProductor;
	private String rfcProductor;
	private String tipoDdocPago;
	private String folioDocPago;
	private String impDocPagoSinaxc;
	private String fechaDocPagoSinaxc;
	private String bancoSinaxc;
	private String impTotalPagoSinaxc;
	private String impPrecioTonPagoSinaxc;
	private Double impTotalPagoSinaxcD;
	private Double volTotalFacVentaD;
	
	public Long getIdRelacionComprasTmp() {
		return idRelacionComprasTmp;
	}
	public void setIdRelacionComprasTmp(Long idRelacionComprasTmp) {
		this.idRelacionComprasTmp = idRelacionComprasTmp;
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
	
	public String getTipoDdocPago() {
		return tipoDdocPago;
	}
	public void setTipoDdocPago(String tipoDdocPago) {
		this.tipoDdocPago = tipoDdocPago;
	}
	public String getFolioDocPago() {
		return folioDocPago;
	}
	public void setFolioDocPago(String folioDocPago) {
		this.folioDocPago = folioDocPago;
	}
	public String getImpDocPagoSinaxc() {
		return impDocPagoSinaxc;
	}
	public void setImpDocPagoSinaxc(String impDocPagoSinaxc) {
		this.impDocPagoSinaxc = impDocPagoSinaxc;
	}
	public String getFechaDocPagoSinaxc() {
		return fechaDocPagoSinaxc;
	}
	public void setFechaDocPagoSinaxc(String fechaDocPagoSinaxc) {
		this.fechaDocPagoSinaxc = fechaDocPagoSinaxc;
	}
	public String getBancoSinaxc() {
		return bancoSinaxc;
	}
	public void setBancoSinaxc(String bancoSinaxc) {
		this.bancoSinaxc = bancoSinaxc;
	}
	public String getImpTotalPagoSinaxc() {
		return impTotalPagoSinaxc;
	}
	public void setImpTotalPagoSinaxc(String impTotalPagoSinaxc) {
		this.impTotalPagoSinaxc = impTotalPagoSinaxc;
	}
	public String getImpPrecioTonPagoSinaxc() {
		return impPrecioTonPagoSinaxc;
	}
	public void setImpPrecioTonPagoSinaxc(String impPrecioTonPagoSinaxc) {
		this.impPrecioTonPagoSinaxc = impPrecioTonPagoSinaxc;
	}
	
	public Double getImpTotalPagoSinaxcD() {
		return impTotalPagoSinaxcD;
	}
	public void setImpTotalPagoSinaxcD(Double impTotalPagoSinaxcD) {
		this.impTotalPagoSinaxcD = impTotalPagoSinaxcD;
	}
	// AHS [LINEA] - 10022015
	public Double getVolTotalFacVentaD() {
		return volTotalFacVentaD;
	}
	public void setVolTotalFacVentaD(Double volTotalFacVentaD) {
		this.volTotalFacVentaD = volTotalFacVentaD;
	}
	// AHS [LINEA] - 10022015
	@Override
	public int compareTo(Object o) {
		PagosCamposRequeridos obj= (PagosCamposRequeridos) o;     
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
