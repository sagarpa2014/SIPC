package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cuotas_esquema")
public class CuotasEsquema {

	private Long idCuotasEsquema;
	private Long idInicializacionEsquema;
	private Integer idEstado;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double cuota;
	
	
	@Id
	@GeneratedValue(generator = "cuotas_esquema_id_cuotas_esquema_seq")
	@SequenceGenerator(name = "cuotas_esquema_id_cuotas_esquema_seq", sequenceName = "cuotas_esquema_id_cuotas_esquema_seq")
	@Column(name = "id_cuotas_esquema")	
	public Long getIdCuotasEsquema() {
		return idCuotasEsquema;
	}
	public void setIdCuotasEsquema(Long idCuotasEsquema) {
		this.idCuotasEsquema = idCuotasEsquema;
	}
	
	@Column(name = "id_inicializacion_esquema", updatable=false, insertable=false)
	public Long getIdInicializacionEsquema() {
		return idInicializacionEsquema;
	}
	public void setIdInicializacionEsquema(Long idInicializacionEsquema) {
		this.idInicializacionEsquema = idInicializacionEsquema;
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
	
	@Column(name = "cuota")
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	
}