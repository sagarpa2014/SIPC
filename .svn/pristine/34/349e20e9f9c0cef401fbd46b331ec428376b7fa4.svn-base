package mx.gob.comer.sipc.domain.historico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hco_cuotas_esquema")
public class HcoCuotasEsquema {

	
	private Long hcoIdCuotasEsquema;
	private Long hcoIdInicializacionEsquema;
	private Integer idEstado;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double cuota;
	private Date fechaCreacion;
	private Integer usuarioCreacion;
	
	
	@Id
	@GeneratedValue(generator = "hco_cuotas_esquema_hco_id_cuotas_esquema_seq")
	@SequenceGenerator(name = "hco_cuotas_esquema_hco_id_cuotas_esquema_seq", sequenceName = "hco_cuotas_esquema_hco_id_cuotas_esquema_seq")
	@Column(name = "hco_id_cuotas_esquema")	
	public Long getHcoIdCuotasEsquema() {
		return hcoIdCuotasEsquema;
	}

	public void setHcoIdCuotasEsquema(Long hcoIdCuotasEsquema) {
		this.hcoIdCuotasEsquema = hcoIdCuotasEsquema;
	}	
	
	
	@Column(name = "hco_id_inicializacion_esquema", updatable=false, insertable=false)
	public Long getHcoIdInicializacionEsquema() {
		return hcoIdInicializacionEsquema;
	}

	public void setHcoIdInicializacionEsquema(Long hcoIdInicializacionEsquema) {
		this.hcoIdInicializacionEsquema = hcoIdInicializacionEsquema;
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

	@Column(name = "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "usuario_creacion")
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
}