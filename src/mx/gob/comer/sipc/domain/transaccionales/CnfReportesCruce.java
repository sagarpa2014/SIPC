package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "cnf_reportes_cruce")
public class CnfReportesCruce {
	
	@Id	
	@Column(name =  "id")
	@GeneratedValue(generator = "cnf_reportes_cruce_id_seq")
	@SequenceGenerator(name = "cnf_reportes_cruce_id_seq", sequenceName = "cnf_reportes_cruce_id_seq")
	private Integer id;
	@Column(name =  "id_criterio")
	private Integer idCriterio;
	@Column(name =  "id_programa")
	private Integer idPrograma;
	@Column(name =  "usuario_modificacion")
	private Integer usuarioModificacion;
	@Column(name =  "usuario_creacion")
	private Integer usuarioCreacion;
	@Column(name =  "fecha_modificacion")
	private Date fechaModificacion;
	@Column(name =  "fecha_alta")
	private Date fechaAlta;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(Integer idCriterio) {
		this.idCriterio = idCriterio;
	}
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	public Integer getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(Integer usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
