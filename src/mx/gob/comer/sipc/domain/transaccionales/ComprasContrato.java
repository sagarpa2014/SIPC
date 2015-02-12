package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_contrato")
public class  ComprasContrato{
	
	private  long idContrato;
	private  long idCompProd;
	private  String  folioContrato;
	private Double impPactado;
	
	@Id	
	@Column(name =  "id_contrato")
	@GeneratedValue(generator = "compras_contrato_id_contrato_seq")
	@SequenceGenerator(name = "compras_contrato_id_contrato_seq", sequenceName = "compras_contrato_id_contrato_seq")
	public long getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(long idContrato) {
		this.idContrato = idContrato;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "folio_contrato")
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
	
	@Column(name =  "imp_pactado")
	public Double getImpPactado() {
		return impPactado;
	}
	public void setImpPactado(Double impPactado) {
		this.impPactado = impPactado;
	}
}