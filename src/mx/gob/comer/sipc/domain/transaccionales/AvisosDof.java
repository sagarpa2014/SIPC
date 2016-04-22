package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "avisos_dof")
public class AvisosDof {
	
	@Id
	@Column(name =  "id_avisos_dof")
	@GeneratedValue(generator = "avisos_dof_id_avisos_dof_seq")
	@SequenceGenerator(name = "avisos_dof_id_avisos_dof_seq", sequenceName = "avisos_dof_id_avisos_dof_seq")	
	private Integer id;
	@Column(name =  "leyenda")
	private String leyenda;
	@Column(name =  "fecha_publicacion") 
	private Date fechaPublicacion;
	@Column(name =  "fecha_registro")
	private Date fechaRegistro;
	@Column(name =  "usuario_registro")
	private Integer usuarioRegistro;
	@Column(name =  "fecha_actualizacion")
	private Date fechaActualizacion;
	@Column(name =  "usuario_actualizacion")
	private Integer usuarioActualizacion;
	@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
	@JoinColumn(name="id_avisos_dof", nullable=false) 
	private Set<AvisosDofDetalle> avisoDofDetalle;
		
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}		
	
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Integer getUsuarioActualizacion() {
		return usuarioActualizacion;
	}
	public void setUsuarioActualizacion(Integer usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}	
	
	public Set<AvisosDofDetalle> getAvisoDofDetalle() {
		return avisoDofDetalle;
	}
	public void setAvisoDofDetalle(Set<AvisosDofDetalle> avisoDofDetalle) {
		this.avisoDofDetalle = avisoDofDetalle;
	}
	
}
