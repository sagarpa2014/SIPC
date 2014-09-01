package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "representante_legal_v")
public class RepresentanteLegalV {

	private String idRepresentante;
	private String nombre;
	private String rfc;
	private String curp;
	private String estado;
	private String municipio;
	private String localidad;
	private String calle;
	private String numExterior;
	private String numInterior;	
	private String telefonos;
	private String estatus;
	private Integer codigoPostal;
	private String colonia;
	private Integer idEstado;
	private Integer claveMunicipio;
	private Integer claveLocalidad;
	private Date fechaNacimiento;
	private String estadoNacimiento;
	private Integer entidadNacimiento;
	private String sexo;
	private String fax;
	private String correoElectronico;
	private Integer estadoCivil;
	private String descEstadoCivil;
	private Integer nacionalidad;
	private String descNacionalidad;
	private Integer tipoIdentificacion;
	private String descTipoIdentificacion;
	private String folioIdentificacion;
	private String rfe;
	private String documentoAcredita;
	private Date vigenciaPoder;
	
	
	@Id
	@Column(name = "id_representante")	
	public String getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(String idRepresentante) {
		this.idRepresentante = idRepresentante;
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
	
	@Column(name = "curp")
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name = "municipio")
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	@Column(name = "localidad")
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	@Column(name = "codigo_postal")
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Column(name = "colonia")
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
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
	
	@Column(name = "fecha_nacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Column(name = "estado_nacimiento")
	public String getEstadoNacimiento() {
		return estadoNacimiento;
	}
	public void setEstadoNacimiento(String estadoNacimiento) {
		this.estadoNacimiento = estadoNacimiento;
	}
	
	@Column(name = "sexo")
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Column(name = "entidad_nacimiento")
	public Integer getEntidadNacimiento() {
		return entidadNacimiento;
	}
	public void setEntidadNacimiento(Integer entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}

	@Column(name = "estado_civil")
	public Integer getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	@Column(name = "nacionalidad")
	public Integer getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Integer nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	@Column(name = "tipo_identificacion")
	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	@Column(name = "folio_identificacion")
	public String getFolioIdentificacion() {
		return folioIdentificacion;
	}
	public void setFolioIdentificacion(String folioIdentificacion) {
		this.folioIdentificacion = folioIdentificacion;
	}
	
	@Column(name = "rfe")
	public String getRfe() {
		return rfe;
	}
	public void setRfe(String rfe) {
		this.rfe = rfe;
	}

	@Column(name = "desc_edo_civil")
	public String getDescEstadoCivil() {
		return descEstadoCivil;
	}
	public void setDescEstadoCivil(String descEstadoCivil) {
		this.descEstadoCivil = descEstadoCivil;
	}
	
	@Column(name = "desc_nacionalidad")
	public String getDescNacionalidad() {
		return descNacionalidad;
	}
	public void setDescNacionalidad(String descNacionalidad) {
		this.descNacionalidad = descNacionalidad;
	}
	
	@Column(name = "desc_tipo_ident")
	public String getDescTipoIdentificacion() {
		return descTipoIdentificacion;
	}
	public void setDescTipoIdentificacion(String descTipoIdentificacion) {
		this.descTipoIdentificacion = descTipoIdentificacion;
	}
	
	@Column(name = "documento_acredita")
	public String getDocumentoAcredita() {
		return documentoAcredita;
	}
	public void setDocumentoAcredita(String documentoAcredita) {
		this.documentoAcredita = documentoAcredita;
	}
	
	@Column(name = "vigencia_poder")
	public Date getVigenciaPoder() {
		return vigenciaPoder;
	}
	public void setVigenciaPoder(Date vigenciaPoder) {
		this.vigenciaPoder = vigenciaPoder;
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
	
	@Column(name = "fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name = "correo_electronico")
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}