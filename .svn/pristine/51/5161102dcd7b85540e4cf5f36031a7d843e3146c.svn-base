package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
*PagosDetalle
*
*Clase que mapea a la tabla "pagos_detalle" de la base de datos  
*
*Versión 1.0
*
*Enero 2013
*
*/

@Entity
@Table(name="pagos_detalle")
public class PagosDetalle {
	
	private int idPagoDetalle;
	private int idPago;
	private Integer idEstado;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double volumen;	
	private Double importe;
	private String etapa;
	private Double cuota;
	
	public PagosDetalle(){}

	@Id
	@GeneratedValue(generator = "pagos_detalle_id_pago_detalle_seq")
	@SequenceGenerator(name = "pagos_detalle_id_pago_detalle_seq", sequenceName = "pagos_detalle_id_pago_detalle_seq")
	@Column(name = "id_pago_detalle")
	public int getIdPagoDetalle() {
		return idPagoDetalle;
	}

	public void setIdPagoDetalle(int idPagoDetalle) {
		this.idPagoDetalle = idPagoDetalle;
	}

	@Column(name = "id_pago", updatable=false, insertable=false)
	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}

	@Column(name = "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}

	@Column(name = "volumen")
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name = "importe")
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@Column(name = "etapa")
	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	@Column(name = "cuota")
	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}	
}
