package mx.gob.comer.sipc.vistas.domain.relaciones;



@SuppressWarnings("rawtypes")
public class BoletasCamposRequeridos implements Comparable{	
	
	private String claveBodega;	
	private String nombreEstado;
	private String folioContrato;
	private String paternoProductor;
	private String maternoProductor;
	private String nombreProductor;
	private String boletaTicketBascula;
	private String FechaEntrada;
	private String Volumen;
	private Double volBolTicket;

	
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
	public String getBoletaTicketBascula() {
		return boletaTicketBascula;
	}
	public void setBoletaTicketBascula(String boletaTicketBascula) {
		this.boletaTicketBascula = boletaTicketBascula;
	}	
	
	public String getFechaEntrada() {
		return FechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		FechaEntrada = fechaEntrada;
	}
	public String getVolumen() {
		return Volumen;
	}
	public void setVolumen(String volumen) {
		Volumen = volumen;
	}
		
	public Double getVolBolTicket() {
		return volBolTicket;
	}
	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
	}
	
	@Override
	public int compareTo(Object o) {
		BoletasCamposRequeridos obj= (BoletasCamposRequeridos) o;     
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
