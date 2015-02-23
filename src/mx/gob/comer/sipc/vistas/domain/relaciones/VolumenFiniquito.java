package mx.gob.comer.sipc.vistas.domain.relaciones;




@SuppressWarnings("rawtypes")
public class VolumenFiniquito implements Comparable{			
	
	private String claveBodega;	
	private String folioContrato;
	private String nombreComprador;
	private String nombreVendedor;
	private Double volumen;
	

	
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}

	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}	
		
	public String getNombreComprador() {
		return nombreComprador;
	}
	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}
	public String getNombreVendedor() {
		return nombreVendedor;
	}
	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	@Override
	public int compareTo(Object o) {
		VolumenFiniquito obj= (VolumenFiniquito) o;     
    	if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) {
    		  return this.folioContrato.compareToIgnoreCase(obj.folioContrato); 
    	}else{
    		return this.claveBodega.compareTo(obj.claveBodega);
    	}	                 
   
	}

}
