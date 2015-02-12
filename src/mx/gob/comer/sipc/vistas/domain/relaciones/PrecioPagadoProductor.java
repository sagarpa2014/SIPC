package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrecioPagadoProductor implements Comparable {	
			
	@Id
	@Column(name =  "id")
	private Integer id;	
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "folio_contrato")
	private String folioContrato;
	@Column(name =  "productor")
	private Long productor;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "folio_factura_venta")
	private String folioFacturaVenta;
	@Column(name =  "vol_sol_fac_venta")
	private Double volSolFacVenta;
	@Column(name =  "fecha_tipo_cambio")
	private Date fechaTipoCambio;
	@Column(name =  "imp_sol_fac_venta")
	private Double impSolFacVenta;
	@Column(name =  "precio_pactado_por_tonelada")
	private Double precioPactadoPorTonelada;
	@Column(name =  "tipo_cambio")
	private Double tipoCambio;
	@Column(name =  "precio_tonelada")
	private Double precioTonelada;
	@Column(name =  "precio_pac_x_tipo_cambio")
	private Double precioPacXTipoCambio;
	@Column(name =  "total_pagar_factura_pna_prec_cal")
	private Double totalPagarFacturaPnaPrecCal;
	@Column(name =  "dif_monto_fac")
	private Double difMontoFac;
	@Column(name =  "dif_monto_total")
	private Double difMontoTotal;
	
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
	public Long getProductor() {
		return productor;
	}
	public void setProductor(Long productor) {
		this.productor = productor;
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
	public Double getVolSolFacVenta() {
		return volSolFacVenta;
	}
	public void setVolSolFacVenta(Double volSolFacVenta) {
		this.volSolFacVenta = volSolFacVenta;
	}
	public Date getFechaTipoCambio() {
		return fechaTipoCambio;
	}
	public void setFechaTipoCambio(Date fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
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
	public Double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public Double getPrecioTonelada() {
		return precioTonelada;
	}
	public void setPrecioTonelada(Double precioTonelada) {
		this.precioTonelada = precioTonelada;
	}	
	
	public Double getPrecioPacXTipoCambio() {
		return precioPacXTipoCambio;
	}
	public void setPrecioPacXTipoCambio(Double precioPacXTipoCambio) {
		this.precioPacXTipoCambio = precioPacXTipoCambio;
	}
	public Double getTotalPagarFacturaPnaPrecCal() {
		return totalPagarFacturaPnaPrecCal;
	}
	public void setTotalPagarFacturaPnaPrecCal(Double totalPagarFacturaPnaPrecCal) {
		this.totalPagarFacturaPnaPrecCal = totalPagarFacturaPnaPrecCal;
	}
	public Double getDifMontoFac() {
		return difMontoFac;
	}
	public void setDifMontoFac(Double difMontoFac) {
		this.difMontoFac = difMontoFac;
	}
	public Double getDifMontoTotal() {
		return difMontoTotal;
	}
	public void setDifMontoTotal(Double difMontoTotal) {
		this.difMontoTotal = difMontoTotal;
	}	
	
	public int compareTo(Object o) {
		PrecioPagadoProductor factura = (PrecioPagadoProductor) o;     
	    if(this.claveBodega.compareToIgnoreCase(factura.claveBodega) == 0) { 
    		if(this.paternoProductor.compareToIgnoreCase(factura.paternoProductor) == 0) {
            	if(this.maternoProductor.compareToIgnoreCase(factura.maternoProductor) == 0) {
            		  return this.nombreProductor.compareToIgnoreCase(factura.nombreProductor); 
            	}else{
            		return this.maternoProductor.compareTo(factura.maternoProductor);
            	}	                 
            } else { 
                return this.paternoProductor.compareToIgnoreCase(factura.paternoProductor); 
            }
	    	             
	    } else {
	            return this.claveBodega.compareToIgnoreCase(factura.claveBodega); 
	    }    		
	}
}
