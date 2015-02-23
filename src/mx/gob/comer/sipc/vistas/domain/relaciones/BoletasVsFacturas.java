package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class BoletasVsFacturas implements Comparable  {	
	
	@Id
	@Column(name =  "id")
	private Long id;
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
	@Column(name =  "vol_bol_ticket")
	private Double volBolTicket;
	@Column(name =  "vol_total_fac_venta")
	private Double volTotalFacVenta;
	@Column(name =  "diferencia_volumen")
	private Double difVolumen;
	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Double getVolBolTicket() {
		return volBolTicket;
	}
	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
	}	
	
	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	
	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	
	public Double getDifVolumen() {
		return difVolumen;
	}
	public void setDifVolumen(Double difVolumen) {
		this.difVolumen = difVolumen;
	}
	@Override
	public int compareTo(Object o) {
		BoletasVsFacturas obj= (BoletasVsFacturas) o;     
	    if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(obj.nombreEstado) == 0){
		    	if(this.folioContrato.compareToIgnoreCase(obj.folioContrato) == 0){
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
