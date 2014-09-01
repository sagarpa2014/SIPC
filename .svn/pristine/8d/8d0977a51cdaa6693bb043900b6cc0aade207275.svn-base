package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hco_programas_v")
public class HcoProgramasV {
	
	private Long idHcoPrograma; //id_hco_programa
	private Integer idPrograma; //id_programa
	private Date fechaPublicacionDof; //
	private String archivoPublicacionDof; //archivo_publicacion_dof
	
	@Id
	@Column(name = "id_hco_programa")	
	public Long getIdHcoPrograma() {
		return idHcoPrograma;
	}
	public void setIdHcoPrograma(Long idHcoPrograma) {
		this.idHcoPrograma = idHcoPrograma;
	}
	
	@Column(name = "id_programa")	
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}	
	@Column(name = "fecha_publicacion_dof")
	public Date getFechaPublicacionDof() {
		return fechaPublicacionDof;
	}
	public void setFechaPublicacionDof(Date fechaPublicacionDof) {
		this.fechaPublicacionDof = fechaPublicacionDof;
	}
	@Column(name = "archivo_publicacion_dof")	
	public String getArchivoPublicacionDof() {
		return archivoPublicacionDof;
	}
	public void setArchivoPublicacionDof(String archivoPublicacionDof) {
		this.archivoPublicacionDof = archivoPublicacionDof;
	}
	
	
	
}
