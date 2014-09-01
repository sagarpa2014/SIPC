package mx.gob.comer.sipc.domain.historico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hco_etapa_ini_esquema")
public class HcoEtapaIniEsquema {
	
	private long idHcoEtapaIniEsquema;
	private Integer idEtapa;
	private Integer idPrograma;
	private Double monto;
	private Date fechaCreacion;
	private Integer usuarioCreacion;	
	
	@Id	
	@Column(name =  "id_hco_etapa_ini_esquema")
	@GeneratedValue(generator = "etapa_ini_esquema_id_etapa_ini_esquema_seq")
	@SequenceGenerator(name = "etapa_ini_esquema_id_etapa_ini_esquema_seq", sequenceName = "etapa_ini_esquema_id_etapa_ini_esquema_seq")
	public long getIdHcoEtapaIniEsquema() {
		return idHcoEtapaIniEsquema;
	}

	public void setIdHcoEtapaIniEsquema(long idHcoEtapaIniEsquema) {
		this.idHcoEtapaIniEsquema = idHcoEtapaIniEsquema;
	}
	
	@Column(name =  "id_etapa")
	public Integer getIdEtapa() {
		return idEtapa;
	}
	
	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	
	@Column(name =  "monto")
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
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
