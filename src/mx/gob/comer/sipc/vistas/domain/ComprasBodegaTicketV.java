package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "compras_bodega_ticket_v")
public class ComprasBodegaTicketV {
	
	private  long idCompProd;
	private  String claveBodega;
	private  String boletaTicketBascula;
	
	@Id	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}

	@Column(name =  "boleta_ticket_bascula")
	public String getBoletaTicketBascula() {
		return boletaTicketBascula;
	}
	public void setBoletaTicketBascula(String boletaTicketBascula) {
		this.boletaTicketBascula = boletaTicketBascula;
	}
}