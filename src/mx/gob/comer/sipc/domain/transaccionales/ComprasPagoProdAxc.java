package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_pago_prod_axc")
public class ComprasPagoProdAxc {
	
	private  long idPagoProdAxc;
	private  long idCompProd;
	private  Double impPacAxc;
	private  Double impPacAxcProd;
	private  Double impCompTon;
	private  Double impCompTonProd;
	
	@Id	
	@Column(name =  "id_pago_prod_axc")
	@GeneratedValue(generator = "compras_pago_prod_axc_id_pago_prod_axc_seq")
	@SequenceGenerator(name = "compras_pago_prod_axc_id_pago_prod_axc_seq", sequenceName = "compras_pago_prod_axc_id_pago_prod_axc_seq")
	public long getIdPagoProdAxc() {
		return idPagoProdAxc;
	}
	public void setIdPagoProdAxc(long idPagoProdAxc) {
		this.idPagoProdAxc = idPagoProdAxc;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "imp_pac_axc")
	public Double getImpPacAxc() {
		return impPacAxc;
	}
	public void setImpPacAxc(Double impPacAxc) {
		this.impPacAxc = impPacAxc;
	}
	
	@Column(name =  "imp_pac_axc_prod")
	public Double getImpPacAxcProd() {
		return impPacAxcProd;
	}
	public void setImpPacAxcProd(Double impPacAxcProd) {
		this.impPacAxcProd = impPacAxcProd;
	}
	
	@Column(name =  "imp_comp_ton")
	public Double getImpCompTon() {
		return impCompTon;
	}
	public void setImpCompTon(Double impCompTon) {
		this.impCompTon = impCompTon;
	}
	
	@Column(name =  "imp_comp_ton_prod")
	public Double getImpCompTonProd() {
		return impCompTonProd;
	}
	public void setImpCompTonProd(Double impCompTonProd) {
		this.impCompTonProd = impCompTonProd;
	}
}