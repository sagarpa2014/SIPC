package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_fac_venta")
public class ComprasFacVenta {
	
	private  long idVenta;
	private  long idCompProd;
	private  String folioFac;
	private  Date  fechaEmisionFac;
	private  String rfcFac;
	private  Double volTotalFac;
	private  Double volSolFac;
	private  Double impSolFac;
	private Integer usuarioRegistro;
	private Date fechaRegistro;
	private Integer usuarioActualiza;
	private Date fechaActualiza;
	
	
	@Id	
	@Column(name =  "id_venta")
	@GeneratedValue(generator = "compras_fac_venta_id_venta_seq")
	@SequenceGenerator(name = "compras_fac_venta_id_venta_seq", sequenceName = "compras_fac_venta_id_venta_seq")
	public long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "folio_fac")
	public String getFolioFac() {
		return folioFac;
	}
	public void setFolioFac(String folioFac) {
		this.folioFac = folioFac;
	}
	
	@Column(name =  "fecha_emision_fac")
	public Date getFechaEmisionFac() {
		return fechaEmisionFac;
	}
	public void setFechaEmisionFac(Date fechaEmisionFac) {
		this.fechaEmisionFac = fechaEmisionFac;
	}
	
	@Column(name =  "rfc_fac")
	public String getRfcFac() {
		return rfcFac;
	}
	public void setRfcFac(String rfcFac) {
		this.rfcFac = rfcFac;
	}
	
	@Column(name =  "vol_total_fac")
	public Double getVolTotalFac() {
		return volTotalFac;
	}
	public void setVolTotalFac(Double volTotalFac) {
		this.volTotalFac = volTotalFac;
	}
	
	@Column(name =  "vol_sol_fac")
	public Double getVolSolFac() {
		return volSolFac;
	}
	public void setVolSolFac(Double volSolFac) {
		this.volSolFac = volSolFac;
	}
	
	@Column(name =  "imp_sol_fac")
	public Double getImpSolFac() {
		return impSolFac;
	}
	public void setImpSolFac(Double impSolFac) {
		this.impSolFac = impSolFac;
	}
	@Column(name = "usuario_registro")
	public Integer getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
	@Column(name = "fecha_registro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name = "usuario_actualiza")
	public Integer getUsuarioActualiza() {
		return usuarioActualiza;
	}
	public void setUsuarioActualiza(Integer usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	
	@Column(name = "fecha_actualiza")
	public Date getFechaActualiza() {
		return fechaActualiza;
	}
	public void setFechaActualiza(Date fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}
}