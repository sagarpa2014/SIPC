package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
*PagosDetalleV
*
*Clase que mapea a la tabla "pagos_detalle_v" de la base de datos  
*
*Versión 1.0
*
*Enero 2013
*
*/

@Entity
@Table(name="pagos_detalle_group_v")
public class PagosDetalleGroupV {
	
	private long id;
	private Long idPago;
	private int idEstado;
	private String etapa;
	private Double volumen;	
	private Double importe;
	private String estado;
	private Integer idCultivo;
	private String cultivo;	
	private Integer idVariedad;
	private String variedad;
	private Double cuota;
	
	public PagosDetalleGroupV(){}


	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}	
	
	@Column(name = "id_pago")
	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	@Column(name = "id_estado")
	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "etapa")
	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
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

	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "id_cultivo")
	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	@Column(name = "cultivo")
	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	@Column(name = "id_variedad")
	public int getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(int idVariedad) {
		this.idVariedad = idVariedad;
	}

	@Column(name = "variedad")
	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	@Column(name = "cuota")
	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
}