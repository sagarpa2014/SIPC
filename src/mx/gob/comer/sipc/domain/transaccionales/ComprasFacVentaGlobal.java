package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_fac_venta_global")
public class ComprasFacVentaGlobal {
	
	private  long idVentaGlobal;
	private  long idCompProd;
	private  String nombrePerFac;
	private  String folioFacGlobal;
	private  Date fechaEmisionFacGlobal;
	private  String rfcFacGlobal;
	private  Double volFacGlobal;
	private  Double impFacGlobal;
	
	@Id	
	@Column(name =  "id_venta_global")
	@GeneratedValue(generator = "compras_fac_venta_global_id_venta_global_seq")
	@SequenceGenerator(name = "compras_fac_venta_global_id_venta_global_seq", sequenceName = "compras_fac_venta_global_id_venta_global_seq")
	public long getIdVentaGlobal() {
		return idVentaGlobal;
	}
	public void setIdVentaGlobal(long idVentaGlobal) {
		this.idVentaGlobal = idVentaGlobal;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "nombre_per_fac")
	public String getNombrePerFac() {
		return nombrePerFac;
	}
	public void setNombrePerFac(String nombrePerFac) {
		this.nombrePerFac = nombrePerFac;
	}
	
	@Column(name =  "folio_fac_global")
	public String getFolioFacGlobal() {
		return folioFacGlobal;
	}
	public void setFolioFacGlobal(String folioFacGlobal) {
		this.folioFacGlobal = folioFacGlobal;
	}
	
	@Column(name =  "fecha_emision_fac_global")
	public Date getFechaEmisionFacGlobal() {
		return fechaEmisionFacGlobal;
	}
	public void setFechaEmisionFacGlobal(Date fechaEmisionFacGlobal) {
		this.fechaEmisionFacGlobal = fechaEmisionFacGlobal;
	}
	
	@Column(name =  "rfc_fac_global")
	public String getRfcFacGlobal() {
		return rfcFacGlobal;
	}
	public void setRfcFacGlobal(String rfcFacGlobal) {
		this.rfcFacGlobal = rfcFacGlobal;
	}
	
	@Column(name =  "vol_fac_global")
	public Double getVolFacGlobal() {
		return volFacGlobal;
	}
	public void setVolFacGlobal(Double volFacGlobal) {
		this.volFacGlobal = volFacGlobal;
	}
	
	@Column(name =  "imp_fac_global")
	public Double getImpFacGlobal() {
		return impFacGlobal;
	}
	public void setImpFacGlobal(Double impFacGlobal) {
		this.impFacGlobal = impFacGlobal;
	}
}