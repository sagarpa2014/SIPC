package mx.gob.comer.sipc.vistas.domain.relaciones;




@SuppressWarnings("rawtypes")
public class VolumenFiniquito implements Comparable{			
	
	private String claveBodega;	
	private String folioContrato;
	private String nombreComprador;
	private String nombreVendedor;
	private Double precioPactadoPorTonelada;
	private Double volumen;
	private Double volTotalFacVenta;
	private Double difVolumenFiniquito;
	private String modalidad;
	

	
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
	
	public Double getPrecioPactadoPorTonelada() {
		return precioPactadoPorTonelada;
	}
	public void setPrecioPactadoPorTonelada(Double precioPactadoPorTonelada) {
		this.precioPactadoPorTonelada = precioPactadoPorTonelada;
	}
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}	
	
	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	
	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	public Double getDifVolumenFiniquito() {
		return difVolumenFiniquito;
	}
	public void setDifVolumenFiniquito(Double difVolumenFiniquito) {
		this.difVolumenFiniquito = difVolumenFiniquito;
	}	
	
	
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	@Override
	public int compareTo(Object o) {
		VolumenFiniquito obj= (VolumenFiniquito) o;     
    	if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) {
    		  return (this.folioContrato!=null?this.folioContrato:"").compareToIgnoreCase(obj.folioContrato); 
    	}else{
    		return this.claveBodega.compareTo(obj.claveBodega);
    	}	                 
   
	}

}
