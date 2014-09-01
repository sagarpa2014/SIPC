package mx.gob.comer.sipc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "auditores_externos")
public class AuditoresExternos implements Serializable{
	/**
	 * 
	 */
	private String numeroAuditor;
	private String nombre;
	private String despacho;
	private Integer idEstado;
	private Integer claveMunicipio;
	private Integer claveLocalidad;
	private String colonia;
	private String calle;
	private String telefonos;
	private Integer estatus;
	private Integer codigoPostal;
	private String rutaAuditor;
	private Integer numeroRegistroDespacho;
	private Date fechaAlta;
	private Date fechaModificacion;
	private Integer usuarioModificacion;
	private Integer usuarioCreacion;
	private String motivoInhabilitado;
	
	@Id
	@Column(name = "numero_auditor")
	public String getNumeroAuditor() {
		return numeroAuditor;
	}
	public void setNumeroAuditor(String numeroAuditor) {
		this.numeroAuditor = numeroAuditor;
	}
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "despacho")
	public String getDespacho() {
		return despacho;
	}
	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}
	
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "clave_municipio")
	public Integer getClaveMunicipio() {
		return claveMunicipio;
	}
	public void setClaveMunicipio(Integer claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}
	
	@Column(name = "clave_localidad")
	public Integer getClaveLocalidad() {
		return claveLocalidad;
	}
	public void setClaveLocalidad(Integer claveLocalidad) {
		this.claveLocalidad = claveLocalidad;
	}
	
	@Column(name = "colonia")
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	@Column(name = "calle")
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	@Column(name = "telefonos")
	public String getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
	
	@Column(name = "estatus")	
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	@Column(name = "codigo_postal")
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Column(name = "ruta_auditor")
	public String getRutaAuditor() {
		return rutaAuditor;
	}
	public void setRutaAuditor(String rutaAuditor) {
		this.rutaAuditor = rutaAuditor;
	}
	
	@Column(name = "numero_registro_despacho")
	public Integer getNumeroRegistroDespacho() {
		return numeroRegistroDespacho;
	}
	public void setNumeroRegistroDespacho(Integer numeroRegistroDespacho) {
		this.numeroRegistroDespacho = numeroRegistroDespacho;
	}
	
	@Column(name = "fecha_alta")
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	@Column(name = "fecha_modificacion")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	@Column(name = "usuario_modificacion")
	public Integer getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(Integer usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	@Column(name = "usuario_creacion")
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	@Column(name = "motivo_inhabilitado")
	public String getMotivoInhabilitado() {
		return motivoInhabilitado;
	}
	public void setMotivoInhabilitado(String motivoInhabilitado) {
		this.motivoInhabilitado = motivoInhabilitado;
	}
}
