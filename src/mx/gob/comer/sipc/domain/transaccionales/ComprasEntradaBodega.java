package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_entrada_bodega")
public class ComprasEntradaBodega {
	
	private long idEntradaBodega;
	private long idCompProd;
	private String boletaTicketBascula;
	private Date fechaEntrada;
	private Double volBolTicket;
	private Integer usuarioRegistro;
	private Date fechaRegistro;
	private Integer usuarioActualiza;
	private Date fechaActualiza;
	
	
	@Id
	@Column(name =  "id_entrada_bodega")
	@GeneratedValue(generator = "compras_entrada_bodega_id_entrada_bodega_seq")
	@SequenceGenerator(name = "compras_entrada_bodega_id_entrada_bodega_seq", sequenceName = "compras_entrada_bodega_id_entrada_bodega_seq")
	public long getIdEntradaBodega() {
		return idEntradaBodega;
	}
	public void setIdEntradaBodega(long idEntradaBodega) {
		this.idEntradaBodega = idEntradaBodega;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "boleta_ticket_bascula")
	public String getBoletaTicketBascula() {
		return boletaTicketBascula;
	}
	public void setBoletaTicketBascula(String boletaTicketBascula) {
		this.boletaTicketBascula = boletaTicketBascula;
	}
	
	@Column(name =  "fecha_entrada")
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	@Column(name =  "vol_bol_ticket")
	public Double getVolBolTicket() {
		return volBolTicket;
	}
	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
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