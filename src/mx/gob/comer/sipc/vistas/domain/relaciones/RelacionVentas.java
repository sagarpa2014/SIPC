package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "relacion_ventas")
public class RelacionVentas {
	
	@Id	
	@GeneratedValue(generator = "relacion_ventas_id_seq")
	@SequenceGenerator(name = "relacion_ventas_id_seq", sequenceName = "relacion_ventas_id_seq")
	@Column(name =  "id")
	private long id;
	@Column(name =  "id_cliente")
	private Integer idCliente;
	@Column(name =  "folio_carta_adhesion")
	private String folioCartaAdhesion;
	@Column(name =  "folio_factura")
	private String folioFactura;
	@Column(name =  "fecha")
	private Date fecha;
	@Column(name =  "volumen")
	private Double volumen;
	@Column(name =  "id_estado")
	private Integer idEstado;
	@Column(name =  "id_pais")
	private Integer idPais;
	@Column(name =  "internacional")
	private Boolean internacional;
	@Column(name =  "id_uso_grano")
	private Integer idUsoGrano;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	public String getFolioFactura() {
		return folioFactura;
	}
	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public Boolean getInternacional() {
		return internacional;
	}
	public void setInternacional(Boolean internacional) {
		this.internacional = internacional;
	}
	public Integer getIdUsoGrano() {
		return idUsoGrano;
	}
	public void setIdUsoGrano(Integer idUsoGrano) {
		this.idUsoGrano = idUsoGrano;
	}	  

}
