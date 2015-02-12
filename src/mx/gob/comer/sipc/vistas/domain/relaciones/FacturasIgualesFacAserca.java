package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class FacturasIgualesFacAserca {
	
	@Id
	@Column(name =  "folio_factura_venta")
	private String folioFacturaVenta;
	@Column(name =  "productor")
	private Long folioProductor;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "fecha_emision_fac")
	private Date fechaEmisionFac;
	@Column(name =  "rfc_fac_venta")
	private String rfcFacVenta;
	@Column(name =  "vol_total_fac_venta")
	private Double volTotalFacVenta;
	@Column(name =  "vol_sol_fac_venta")
	private Double volSolFacVenta;
	@Column(name =  "imp_sol_fac_venta")
	private Double impSolFacVenta;
	@Column(name =  "id_variedad")
	private String variedad;
	@Column(name =  "id_cultivo")
	private String cultivo;
	@Column(name =  "rfc_productor")
	private String rfcProductor;
	@Column(name =  "rfc_comprador")
	private String rfcComprador;
	
	public String getFolioFacturaVenta() {
		return folioFacturaVenta;
	}
	public void setFolioFacturaVenta(String folioFacturaVenta) {
		this.folioFacturaVenta = folioFacturaVenta;
	}
	public Long getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(Long folioProductor) {
		this.folioProductor = folioProductor;
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
	public Date getFechaEmisionFac() {
		return fechaEmisionFac;
	}
	public void setFechaEmisionFac(Date fechaEmisionFac) {
		this.fechaEmisionFac = fechaEmisionFac;
	}
	public String getRfcFacVenta() {
		return rfcFacVenta;
	}
	public void setRfcFacVenta(String rfcFacVenta) {
		this.rfcFacVenta = rfcFacVenta;
	}
	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	public Double getVolSolFacVenta() {
		return volSolFacVenta;
	}
	public void setVolSolFacVenta(Double volSolFacVenta) {
		this.volSolFacVenta = volSolFacVenta;
	}
	public Double getImpSolFacVenta() {
		return impSolFacVenta;
	}
	public void setImpSolFacVenta(Double impSolFacVenta) {
		this.impSolFacVenta = impSolFacVenta;
	}
	
	public String getVariedad() {
		return variedad;
	}
	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	public String getRfcProductor() {
		return rfcProductor;
	}
	public void setRfcProductor(String rfcProductor) {
		this.rfcProductor = rfcProductor;
	}
	public String getRfcComprador() {
		return rfcComprador;
	}
	public void setRfcComprador(String rfcComprador) {
		this.rfcComprador = rfcComprador;
	}
}
