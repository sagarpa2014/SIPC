package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "avisos_dof")
public class AvisosDof {
	
	@Id	
	@Column(name =  "clave_aviso")
	private String claveAviso;
	@Column(name =  "leyenda")
	private String leyenda;
	@Column(name =  "id_apoyo")
	private Integer idApoyo;
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
	@JoinColumn(name="clave_aviso", nullable=false) 
	private Set<AvisosDofDetalle> avisoDofDetalle;
	
	public String getClaveAviso() {
		return claveAviso;
	}
	public void setClaveAviso(String claveAviso) {
		this.claveAviso = claveAviso;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
		
	public Integer getIdApoyo() {
		return idApoyo;
	}
	public void setIdApoyo(Integer idApoyo) {
		this.idApoyo = idApoyo;
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
