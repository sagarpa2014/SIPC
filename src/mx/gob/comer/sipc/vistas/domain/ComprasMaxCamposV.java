package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "compras_max_campos_v")
public class ComprasMaxCamposV {
	
	private  long idCompProd;
	private  Long idCompPer;
	private  Integer idComprador;
	private  Integer comprasPredio;
	private  Integer comprasEntradaBodega;
	private  Integer comprasFacVentaGlobal;
	private  Integer comprasFacVenta;
	private  Integer comprasContrato;
	private  Integer comprasPagoProdAxc;
	private  Integer comprasPagoProdSinAxc;
	
	@Id	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "id_comp_per")
	public Long getIdCompPer() {
		return idCompPer;
	}
	public void setIdCompPer(Long idCompPer) {
		this.idCompPer = idCompPer;
	}	

	@Column(name =  "id_comprador")	
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	@Column(name =  "compras_predio")	
	public Integer getComprasPredio() {
		return comprasPredio;
	}
	public void setComprasPredio(Integer comprasPredio) {
		this.comprasPredio = comprasPredio;
	}
	
	@Column(name =  "compras_entrada_bodega")
	public Integer getComprasEntradaBodega() {
		return comprasEntradaBodega;
	}
	public void setComprasEntradaBodega(Integer comprasEntradaBodega) {
		this.comprasEntradaBodega = comprasEntradaBodega;
	}
	
	@Column(name =  "compras_fac_venta_global")
	public Integer getComprasFacVentaGlobal() {
		return comprasFacVentaGlobal;
	}
	public void setComprasFacVentaGlobal(Integer comprasFacVentaGlobal) {
		this.comprasFacVentaGlobal = comprasFacVentaGlobal;
	}
	
	@Column(name =  "compras_fac_venta")
	public Integer getComprasFacVenta() {
		return comprasFacVenta;
	}
	public void setComprasFacVenta(Integer comprasFacVenta) {
		this.comprasFacVenta = comprasFacVenta;
	}
	
	@Column(name =  "compras_contrato")
	public Integer getComprasContrato() {
		return comprasContrato;
	}
	public void setComprasContrato(Integer comprasContrato) {
		this.comprasContrato = comprasContrato;
	}
	
	@Column(name =  "compras_pago_prod_axc")
	public Integer getComprasPagoProdAxc() {
		return comprasPagoProdAxc;
	}
	public void setComprasPagoProdAxc(Integer comprasPagoProdAxc) {
		this.comprasPagoProdAxc = comprasPagoProdAxc;
	}
	
	@Column(name =  "compras_pago_prod_sin_axc")
	public Integer getComprasPagoProdSinAxc() {
		return comprasPagoProdSinAxc;
	}
	public void setComprasPagoProdSinAxc(Integer comprasPagoProdSinAxc) {
		this.comprasPagoProdSinAxc = comprasPagoProdSinAxc;
	}
	
	
}