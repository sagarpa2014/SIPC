package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class FacturaFueraPeriodoPagoAdendum implements Comparable{	
	@Id
	@Column(name =  "id")
	private Long id;
	@Column(name =  "productor")
	private Long folioProductor;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "estado_acopio")
	private Integer estadoAcopio;
	@Column(name =  "nombre_estado")
	private String nombreEstado;                                                                                                                                              
	@Column(name =  "folio_factura_venta")
	private String folioFacturaVenta;                                                                                    
	@Column(name =  "fecha_emision_fac")
	private Date fechaEmisionFac;	

		 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public Integer getEstadoAcopio() {
		return estadoAcopio;
	}
	public void setEstadoAcopio(Integer estadoAcopio) {
		this.estadoAcopio = estadoAcopio;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
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
	public Long getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(Long folioProductor) {
		this.folioProductor = folioProductor;
	}	
	
	@Override
	public int compareTo(Object o) {
		FacturaFueraPeriodoPagoAdendum factura = (FacturaFueraPeriodoPagoAdendum) o;     
	    if(this.claveBodega.compareToIgnoreCase(factura.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(factura.nombreEstado) == 0){	    		
	    		if(this.paternoProductor.compareToIgnoreCase(factura.paternoProductor) == 0) {
	            	if(this.maternoProductor.compareToIgnoreCase(factura.maternoProductor) == 0) {
	            		  return this.nombreProductor.compareToIgnoreCase(factura.nombreProductor); 
	            	}else{
	            		return this.maternoProductor.compareTo(factura.maternoProductor);
	            	}	                 
	            } else { 
	                return this.paternoProductor.compareToIgnoreCase(factura.paternoProductor); 
	            }
	    	}else{
	    		return this.nombreEstado.compareToIgnoreCase(factura.nombreEstado);
	    	}
	    } else {
	    	return this.claveBodega.compareToIgnoreCase(factura.claveBodega); 
	    }    		
	}
}
