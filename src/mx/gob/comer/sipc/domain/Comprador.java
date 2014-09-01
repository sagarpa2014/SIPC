package mx.gob.comer.sipc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "compradores")
public class Comprador{
	/**
	 * 
	 */
	private Integer idComprador;
	private String nombre;
	private String tipoPersona;
	private String rfc;
	private String curp;
	private String telefono;
	private String fax;
	private String correoElectronico;
	private Integer claveLocalidad;
	private String calle;
	private String colonia;
	private String numExterior;
	private String numInterior;
	private String codigoPostal;
	private Integer estatus;
	private Integer idEstado;
	private Integer claveMunicipio;
	private String rutaComprador;
	private Date fechaAlta;
	private Date fechaModificacion;
	private Integer usuarioModificacion;
	private Integer usuarioCreacion;
	private String motivoInhabilitado;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private Integer entidadNacimiento;
	private String sexo;
	private String folio;
	private String idSuri;
	private String representanteLegal;
	private Integer estadoCivil;
	private Integer nacionalidad;
	private Integer tipoIdentificacion;
	private String folioIdentificacion;
	private String rfe;
	private Integer tipoVialidad;
	private String nombreVialidad;
	private Integer numeroExt1;
	private Integer numeroExt2;
	private Integer numeroInt;
	private Integer tipoAsentHumano;
	private String nombreAsentHumano;
	private String referencia1;
	private String referencia2;
	private String referencia3;
	private Integer tipoDictamen;
	private Date vigencia;
	private Date consultaSat;
	private Date fechaInscripcionRFC;
	private Integer compradorTemporal;
	
	@Id
	@Column(name = "id_comprador")
	@GeneratedValue(generator = "compradores_id_comprador_seq")
	@SequenceGenerator(name = "compradores_id_comprador_seq", sequenceName = "compradores_id_comprador_seq")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "tipo_persona")	
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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
	
	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	
	@Column(name = "clave_localidad")
	public Integer getClaveLocalidad() {
		return claveLocalidad;
	}
	public void setClaveLocalidad(Integer claveLocalidad) {
		this.claveLocalidad = claveLocalidad;
	}
	
	@Column(name = "calle")
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	@Column(name = "colonia")
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
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
	
	@Column(name = "codigo_postal")
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Column(name = "estatus")
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
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
	
	@Column(name = "ruta_comprador")
	public String getRutaComprador() {
		return rutaComprador;
	}
	public void setRutaComprador(String rutaComprador) {
		this.rutaComprador = rutaComprador;
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
	
	@Column(name = "fecha_nacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Column(name = "entidad_nacimiento")
	public Integer getEntidadNacimiento() {
		return entidadNacimiento;
	}
	public void setEntidadNacimiento(Integer entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}
	
	@Column(name = "sexo")
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Column(name = "folio")
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	@Column(name = "id_suri")
	public String getIdSuri() {
		return idSuri;
	}
	public void setIdSuri(String idSuri) {
		this.idSuri = idSuri;
	}
	
	@Column(name = "representante_legal")
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
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
	
	@Column(name = "tipo_vialidad")
	public Integer getTipoVialidad() {
		return tipoVialidad;
	}
	public void setTipoVialidad(Integer tipoVialidad) {
		this.tipoVialidad = tipoVialidad;
	}
	
	@Column(name = "nombre_vialidad")
	public String getNombreVialidad() {
		return nombreVialidad;
	}
	public void setNombreVialidad(String nombreVialidad) {
		this.nombreVialidad = nombreVialidad;
	}
	
	@Column(name = "numero_ext1")
	public Integer getNumeroExt1() {
		return numeroExt1;
	}
	public void setNumeroExt1(Integer numeroExt1) {
		this.numeroExt1 = numeroExt1;
	}
	
	@Column(name = "numero_ext2")
	public Integer getNumeroExt2() {
		return numeroExt2;
	}
	public void setNumeroExt2(Integer numeroExt2) {
		this.numeroExt2 = numeroExt2;
	}
	
	@Column(name = "numero_int")
	public Integer getNumeroInt() {
		return numeroInt;
	}
	public void setNumeroInt(Integer numeroInt) {
		this.numeroInt = numeroInt;
	}
	
	@Column(name = "tipo_asent_humano")
	public Integer getTipoAsentHumano() {
		return tipoAsentHumano;
	}
	public void setTipoAsentHumano(Integer tipoAsentHumano) {
		this.tipoAsentHumano = tipoAsentHumano;
	}
	
	@Column(name = "nombre_asent_humano")
	public String getNombreAsentHumano() {
		return nombreAsentHumano;
	}
	public void setNombreAsentHumano(String nombreAsentHumano) {
		this.nombreAsentHumano = nombreAsentHumano;
	}
	
	@Column(name = "referencia_1")
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	
	@Column(name = "referencia_2")
	public String getReferencia2() {
		return referencia2;
	}
	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}
	
	@Column(name = "referencia_3")
	public String getReferencia3() {
		return referencia3;
	}
	public void setReferencia3(String referencia3) {
		this.referencia3 = referencia3;
	}
	
	@Column(name = "tipo_dictamen")
	public Integer getTipoDictamen() {
		return tipoDictamen;
	}
	public void setTipoDictamen(Integer tipoDictamen) {
		this.tipoDictamen = tipoDictamen;
	}
	
	@Column(name = "vigencia")
	public Date getVigencia() {
		return vigencia;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	
	@Column(name = "consulta_sat")
	public Date getConsultaSat() {
		return consultaSat;
	}
	public void setConsultaSat(Date consultaSat) {
		this.consultaSat = consultaSat;
	}
	
	@Column(name = "fecha_inscripcion_rfc")
	public Date getFechaInscripcionRFC() {
		return fechaInscripcionRFC;
	}
	public void setFechaInscripcionRFC(Date fechaInscripcionRFC) {
		this.fechaInscripcionRFC = fechaInscripcionRFC;
	}
	
	@Column(name = "comprador_temporal")
	public Integer getCompradorTemporal() {
		return compradorTemporal;
	}
	public void setCompradorTemporal(Integer compradorTemporal) {
		this.compradorTemporal = compradorTemporal;
	}	
	
}