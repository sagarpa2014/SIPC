package mx.gob.comer.sipc.domain.historico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hco_programas_estados")
public class HcoProgramaEstado {
	
	private long hcoIdPgrEdo;
	private Integer idPrograma;
	private Integer idEstado;
	private Date fechaCreacion;
	private Integer usuarioCreacion;
	
	@Id	
	@GeneratedValue(generator = "hco_programas_estados_hco_id_pgr_edo_seq")
	@SequenceGenerator(name = "hco_programas_estados_hco_id_pgr_edo_seq", sequenceName = "hco_programas_estados_hco_id_pgr_edo_seq")
	@Column(name =  "hco_id_pgr_edo")
	public long getHcoIdPgrEdo() {
		return hcoIdPgrEdo;
	}

	public void setHcoIdPgrEdo(long hcoIdPgrEdo) {
		this.hcoIdPgrEdo = hcoIdPgrEdo;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
		
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
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
