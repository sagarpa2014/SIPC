package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_pago_prod_sin_axc")
public class ComprasPagoProdSinAxc {
	
	private  long idPagoProdSinAxc;
	private  long idCompProd;
	private  Integer idTipoDocPago;
	private  String folioDocPago;
	private  Double impDocPago;
	private  Double impPrecioTonPago;
	private  Date fechaDocPago;
	private  Integer bancoId;


	@Id	
	@Column(name =  "id_pago_prod_sin_axc")
	@GeneratedValue(generator = "compras_pago_prod_sin_axc_id_pago_prod_sin_axc_seq")
	@SequenceGenerator(name = "compras_pago_prod_sin_axc_id_pago_prod_sin_axc_seq", sequenceName = "compras_pago_prod_sin_axc_id_pago_prod_sin_axc_seq")
	public long getIdPagoProdSinAxc() {
		return idPagoProdSinAxc;
	}
	public void setIdPagoProdSinAxc(long idPagoProdSinAxc) {
		this.idPagoProdSinAxc = idPagoProdSinAxc;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "id_tipo_doc_pago")
	public Integer getIdTipoDocPago() {
		return idTipoDocPago;
	}
	public void setIdTipoDocPago(Integer idTipoDocPago) {
		this.idTipoDocPago = idTipoDocPago;
	}
	
	@Column(name =  "folio_doc_pago")
	public String getFolioDocPago() {
		return folioDocPago;
	}
	public void setFolioDocPago(String folioDocPago) {
		this.folioDocPago = folioDocPago;
	}
	
	@Column(name =  "imp_doc_pago")
	public Double getImpDocPago() {
		return impDocPago;
	}
	public void setImpDocPago(Double impDocPago) {
		this.impDocPago = impDocPago;
	}	
	
	@Column(name =  "imp_precio_ton_pago")
	public Double getImpPrecioTonPago() {
		return impPrecioTonPago;
	}
	public void setImpPrecioTonPago(Double impPrecioTonPago) {
		this.impPrecioTonPago = impPrecioTonPago;
	}
	@Column(name =  "fecha_doc_pago")
	public Date getFechaDocPago() {
		return fechaDocPago;
	}
	public void setFechaDocPago(Date fechaDocPago) {
		this.fechaDocPago = fechaDocPago;
	}
	
	@Column(name =  "banco_id")
	public Integer getBancoId() {
		return bancoId;
	}
	public void setBancoId(Integer bancoId) {
		this.bancoId = bancoId;
	}
}