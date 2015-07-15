package mx.gob.comer.sipc.domain.catalogos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_del_participante")
public class ClienteDeParticipante{
	/**
	 * luz.corona
	 */
	private Integer id;
	private String rfc;
	private String tipoPersona;
	private String curp;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private Integer idEstado;
	private Integer claveMunicipio;
	private Integer claveLocalidad;
	private String colonia;
	private String calle;
	private String numExterior;
	private String numInterior;	
	private Integer codigoPostal;
	private String telefonos;		
	private Date fechaAlta;
	private Date fechaModificacion;
	private Integer usuarioModificacion;
	private Integer usuarioCreacion;	
	
	@Id
	@GeneratedValue(generator = "cliente_del_participante_id_seq")
	@SequenceGenerator(name = "cliente_del_participante_id_seq", sequenceName = "cliente_del_participante_id_seq")
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "rfc")
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}	
	
	@Column(name = "tipo_persona")
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	@Column(name = "curp")
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	@Column(name = "apellido_paterno")
	public String getApellidoPaterno() {
			return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	@Column(name = "apellido_materno")
	public String getApellidoMaterno() {
			return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
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
	
	@Column(name = "num_exterior")
	public String getNumExterior() {
		return numExterior;
	}
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}
	
	@Column(name = "num_interior")
	public String getNumInterior() {
		return numInterior;
	}
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}
	
	@Column(name = "telefonos")
	public String getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
	
	@Column(name = "codigo_postal")
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
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
		
}