package mx.gob.comer.sipc.domain.historico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hco_cultivo_variedad_esquema")
public class HcoCultivoVariedadEsquema {
	
	private long idHcoCve;
	private Integer idPrograma;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double volumen;
	private Date fechaCreacion;
	private Integer usuarioCreacion;
	
	@Id	
	@Column(name =  "id_hco_cve")
	@GeneratedValue(generator = "hco_cultivo_variedad_esquema_id_hco_cve_seq")
	@SequenceGenerator(name = "hco_cultivo_variedad_esquema_id_hco_cve_seq", sequenceName = "hco_cultivo_variedad_esquema_id_hco_cve_seq")
	public long getIdHcoCve() {
		return idHcoCve;
	}
	
	public void setIdHcoCve(long idHcoCve) {
		this.idHcoCve = idHcoCve;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	
	@Column(name =  "volumen")
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	@Column(name =  "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name =  "usuario_creacion")
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}	

}
