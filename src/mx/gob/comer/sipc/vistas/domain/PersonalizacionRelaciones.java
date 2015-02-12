package mx.gob.comer.sipc.vistas.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "personalizacion_relaciones")
public class PersonalizacionRelaciones {

	private long idPerRel;
	private Integer idRelacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Long idIniEsquemaRelacion;
	private Integer idPrograma;

	@Id	
	@Column(name =  "id_per_rel")
	@GeneratedValue(generator = "personalizacion_relaciones_id_per_rel_seq")
	@SequenceGenerator(name = "personalizacion_relaciones_id_per_rel_seq", sequenceName = "personalizacion_relaciones_id_per_rel_seq")
	public long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(long idPerRel) {
		this.idPerRel = idPerRel;
	}
	
	@Column(name =  "id_tipo_relacion")	
	public Integer getIdRelacion() {
		return idRelacion;
	}
	public void setIdRelacion(Integer idRelacion) {
		this.idRelacion = idRelacion;
	}
	

	@Column(name =  "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name =  "fecha_modificacion")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}
	
	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}	
}
