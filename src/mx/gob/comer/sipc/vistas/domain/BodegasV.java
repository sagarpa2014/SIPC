package mx.gob.comer.sipc.vistas.domain;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bodegas_v")
public class BodegasV {
	
	private Integer idBodega;
	private String claveBodega;
	private String status;
	private Integer idEstado;
	private String nombreEstado;
	private Integer ddr;
	private String nombreDdr;
	private Integer cader;
	private String nombreCader;
	private Integer municipio;
	private String nombreMunicipio;
	private Integer ejido;
	private String nombreEjido;
	private String rfcPropietario;
	private Date fechaRegistro;
	private Double latitudGrados;
	private Double latitudMinutos;
	private Double latitudSegundos;
	private Double longitudGrados;
	private Double longitudMinutos;
	private Double longitudSegundos;
	private Double clasificacion;
	private String rfcBodega;
	private String nombre;
	private String direccion;
	private String codigoPostal;
	private String telefono;
	private String fax;
	private String gerente;
	private String correoElectronico;
	private String usuario;
	private Date fechaCaptura;
	private Date fechaModificacion;
	private String aliasBodega;
	private String uso1;
	private String uso2;
	private String uso3;
	private String capacidadBodega;
	private Integer noSilos;
	private String capacidadSilos;
	private String capacidadIntemperie;
	private String capacidadTechada;
	private String totalAlmacenamiento;
	private Integer numRampas;
	private String capacidadRampas;
	private Integer numSecadoras;
	private String capacidadSecado;
	private Integer numCargas;
	private String capacidadCargas;
	private String piso;
	private String capacidadPiso;
	private String ffcc;
	private String capacidadFfcc;
	private String espuelaFfcc;
	private String capacidadEspuela;
	private String capacidadRecepcion;
	private String capacidadEmbarque;
	private String equipoAnalisis;
	private String aplicaNormasCalidad;
	private String observaciones;
	private String nombrePropietario;
	private String paternoPropietario;
	private String maternoPropietario;
	private String curpPropietario;
	private String callePropietario;
	private String localidadPropietario;
	private String cpPropietario;
	private String telefonoPropietario;
	private String faxPropietario;
	private String correoPropietario;	
	       
	@Id
	@Column(name = "id_bodega")	
	public Integer getIdBodega() {
		return idBodega;
	}
	public void setIdBodega(Integer idBodega) {
		this.idBodega = idBodega;
	}
	
	@Column(name = "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "estado")
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	
	
	
	@Column(name = "ddr")
	public Integer getDdr() {
		return ddr;
	}
	
	public void setDdr(Integer ddr) {
		this.ddr = ddr;
	}

	@Column(name = "nombre_ddr")
	public String getNombreDdr() {
		return nombreDdr;
	}
	public void setNombreDdr(String nombreDdr) {
		this.nombreDdr = nombreDdr;
	}
	@Column(name = "cader")
	public Integer getCader() {
		return cader;
	}
	public void setCader(Integer cader) {
		this.cader = cader;
	}
		
	@Column(name = "nombre_cader")
	public String getNombreCader() {
		return nombreCader;
	}
	public void setNombreCader(String nombreCader) {
		this.nombreCader = nombreCader;
	}
	@Column(name = "municipio")
	public Integer getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}	
	
	@Column(name = "nombre_municipio")
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	@Column(name = "ejido")
	public Integer getEjido() {
		return ejido;
	}
	public void setEjido(Integer ejido) {
		this.ejido = ejido;
	}
	
	@Column(name = "nombre_ejido")
	public String getNombreEjido() {
		return nombreEjido;
	}
	public void setNombreEjido(String nombreEjido) {
		this.nombreEjido = nombreEjido;
	}
	@Column(name = "rfc_propietario")
	public String getRfcPropietario() {
		return rfcPropietario;
	}
	public void setRfcPropietario(String rfcPropietario) {
		this.rfcPropietario = rfcPropietario;
	}
	
	@Column(name = "fecha_registro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name = "latitud_grados")
	public Double getLatitudGrados() {
		return latitudGrados;
	}
	public void setLatitudGrados(Double latitudGrados) {
		this.latitudGrados = latitudGrados;
	}
	
	@Column(name = "latitud_minutos")
	public Double getLatitudMinutos() {
		return latitudMinutos;
	}
	public void setLatitudMinutos(Double latitudMinutos) {
		this.latitudMinutos = latitudMinutos;
	}
	
	@Column(name = "latitud_segundos")
	public Double getLatitudSegundos() {
		return latitudSegundos;
	}
	public void setLatitudSegundos(Double latitudSegundos) {
		this.latitudSegundos = latitudSegundos;
	}
	
	@Column(name = "longitud_grados")
	public Double getLongitudGrados() {
		return longitudGrados;
	}
	public void setLongitudGrados(Double longitudGrados) {
		this.longitudGrados = longitudGrados;
	}
	
	@Column(name = "longitud_minutos")
	public Double getLongitudMinutos() {
		return longitudMinutos;
	}
	public void setLongitudMinutos(Double longitudMinutos) {
		this.longitudMinutos = longitudMinutos;
	}
	
	@Column(name = "longitud_segundos")
	public Double getLongitudSegundos() {
		return longitudSegundos;
	}
	public void setLongitudSegundos(Double longitudSegundos) {
		this.longitudSegundos = longitudSegundos;
	}
	
	@Column(name = "clasificacion")
	public Double getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(Double clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	@Column(name = "rfc_bodega")
	public String getRfcBodega() {
		return rfcBodega;
	}
	public void setRfcBodega(String rfcBodega) {
		this.rfcBodega = rfcBodega;
	}
	
	@Column(name = "nombre_bodega")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	@Column(name = "direccion")
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Column(name = "codigo_postal")
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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
	
	@Column(name = "gerente")
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	
	@Column(name = "correo_electronico")
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	@Column(name = "usuario")
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Column(name = "fecha_captura")
	public Date getFechaCaptura() {
		return fechaCaptura;
	}
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}
	
	@Column(name = "fecha_modificacion")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	@Column(name = "alias_bodega")
	public String getAliasBodega() {
		return aliasBodega;
	}
	public void setAliasBodega(String aliasBodega) {
		this.aliasBodega = aliasBodega;
	}
	
	@Column(name = "uso1")
	public String getUso1() {
		return uso1;
	}
	public void setUso1(String uso1) {
		this.uso1 = uso1;
	}
	
	@Column(name = "uso2")
	public String getUso2() {
		return uso2;
	}
	public void setUso2(String uso2) {
		this.uso2 = uso2;
	}
	
	@Column(name = "uso3")
	public String getUso3() {
		return uso3;
	}
	public void setUso3(String uso3) {
		this.uso3 = uso3;
	}
	
	@Column(name = "capacidad_bodega")
	public String getCapacidadBodega() {
		return capacidadBodega;
	}
	public void setCapacidadBodega(String capacidadBodega) {
		this.capacidadBodega = capacidadBodega;
	}
	
	@Column(name = "no_silos")
	public Integer getNoSilos() {
		return noSilos;
	}
	public void setNoSilos(Integer noSilos) {
		this.noSilos = noSilos;
	}
	
	@Column(name = "capacidad_silos")
	public String getCapacidadSilos() {
		return capacidadSilos;
	}
	public void setCapacidadSilos(String capacidadSilos) {
		this.capacidadSilos = capacidadSilos;
	}
	
	@Column(name = "capacidad_intemperie")
	public String getCapacidadIntemperie() {
		return capacidadIntemperie;
	}
	public void setCapacidadIntemperie(String capacidadIntemperie) {
		this.capacidadIntemperie = capacidadIntemperie;
	}
	
	@Column(name = "capacidad_techada")
	public String getCapacidadTechada() {
		return capacidadTechada;
	}
	public void setCapacidadTechada(String capacidadTechada) {
		this.capacidadTechada = capacidadTechada;
	}
	
	@Column(name = "total_almacenamiento")
	public String getTotalAlmacenamiento() {
		return totalAlmacenamiento;
	}
	public void setTotalAlmacenamiento(String totalAlmacenamiento) {
		this.totalAlmacenamiento = totalAlmacenamiento;
	}
	
	@Column(name = "num_rampas")
	public Integer getNumRampas() {
		return numRampas;
	}
	public void setNumRampas(Integer numRampas) {
		this.numRampas = numRampas;
	}
	
	@Column(name = "capacidad_rampas")
	public String getCapacidadRampas() {
		return capacidadRampas;
	}
	public void setCapacidadRampas(String capacidadRampas) {
		this.capacidadRampas = capacidadRampas;
	}
	
	@Column(name = "num_secadoras")
	public Integer getNumSecadoras() {
		return numSecadoras;
	}
	public void setNumSecadoras(Integer numSecadoras) {
		this.numSecadoras = numSecadoras;
	}
	
	@Column(name = "capacidad_secado")
	public String getCapacidadSecado() {
		return capacidadSecado;
	}
	public void setCapacidadSecado(String capacidadSecado) {
		this.capacidadSecado = capacidadSecado;
	}
	
	@Column(name = "num_cargas")
	public Integer getNumCargas() {
		return numCargas;
	}
	public void setNumCargas(Integer numCargas) {
		this.numCargas = numCargas;
	}
	
	@Column(name = "capacidad_cargas")
	public String getCapacidadCargas() {
		return capacidadCargas;
	}
	public void setCapacidadCargas(String capacidadCargas) {
		this.capacidadCargas = capacidadCargas;
	}
	
	@Column(name = "piso")
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	@Column(name = "capacidad_piso")
	public String getCapacidadPiso() {
		return capacidadPiso;
	}
	public void setCapacidadPiso(String capacidadPiso) {
		this.capacidadPiso = capacidadPiso;
	}
	
	@Column(name = "ffcc")
	public String getFfcc() {
		return ffcc;
	}
	public void setFfcc(String ffcc) {
		this.ffcc = ffcc;
	}
	
	@Column(name = "capacidad_ffcc")
	public String getCapacidadFfcc() {
		return capacidadFfcc;
	}
	public void setCapacidadFfcc(String capacidadFfcc) {
		this.capacidadFfcc = capacidadFfcc;
	}
	
	@Column(name = "espuela_ffcc")
	public String getEspuelaFfcc() {
		return espuelaFfcc;
	}
	public void setEspuelaFfcc(String espuelaFfcc) {
		this.espuelaFfcc = espuelaFfcc;
	}
	
	@Column(name = "capacidad_espuela")
	public String getCapacidadEspuela() {
		return capacidadEspuela;
	}
	public void setCapacidadEspuela(String capacidadEspuela) {
		this.capacidadEspuela = capacidadEspuela;
	}
	
	@Column(name = "capacidad_recepcion")
	public String getCapacidadRecepcion() {
		return capacidadRecepcion;
	}
	public void setCapacidadRecepcion(String capacidadRecepcion) {
		this.capacidadRecepcion = capacidadRecepcion;
	}
	
	@Column(name = "capacidad_embarque")
	public String getCapacidadEmbarque() {
		return capacidadEmbarque;
	}
	public void setCapacidadEmbarque(String capacidadEmbarque) {
		this.capacidadEmbarque = capacidadEmbarque;
	}
	
	@Column(name = "equipo_analisis")
	public String getEquipoAnalisis() {
		return equipoAnalisis;
	}
	public void setEquipoAnalisis(String equipoAnalisis) {
		this.equipoAnalisis = equipoAnalisis;
	}
	
	@Column(name = "aplica_normas_calidad")
	public String getAplicaNormasCalidad() {
		return aplicaNormasCalidad;
	}
	public void setAplicaNormasCalidad(String aplicaNormasCalidad) {
		this.aplicaNormasCalidad = aplicaNormasCalidad;
	}
	
	@Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	@Column(name = "nombre_prop")
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	
	@Column(name = "paterno_prop")
	public String getPaternoPropietario() {
		return paternoPropietario;
	}
	public void setPaternoPropietario(String paternoPropietario) {
		this.paternoPropietario = paternoPropietario;
	}
	
	@Column(name = "materno_prop")
	public String getMaternoPropietario() {
		return maternoPropietario;
	}
	public void setMaternoPropietario(String maternoPropietario) {
		this.maternoPropietario = maternoPropietario;
	}
	
	@Column(name = "curp_prop")
	public String getCurpPropietario() {
		return curpPropietario;
	}
	public void setCurpPropietario(String curpPropietario) {
		this.curpPropietario = curpPropietario;
	}
	
	@Column(name = "calle_prop")
	public String getCallePropietario() {
		return callePropietario;
	}
	public void setCallePropietario(String callePropietario) {
		this.callePropietario = callePropietario;
	}
	
	@Column(name = "localidad_prop")
	public String getLocalidadPropietario() {
		return localidadPropietario;
	}
	public void setLocalidadPropietario(String localidadPropietario) {
		this.localidadPropietario = localidadPropietario;
	}
	
	@Column(name = "cp_prop")
	public String getCpPropietario() {
		return cpPropietario;
	}
	public void setCpPropietario(String cpPropietario) {
		this.cpPropietario = cpPropietario;
	}
	
	@Column(name = "telefono_prop")
	public String getTelefonoPropietario() {
		return telefonoPropietario;
	}
	public void setTelefonoPropietario(String telefonoPropietario) {
		this.telefonoPropietario = telefonoPropietario;
	}
	
	@Column(name = "fax_prop")
	public String getFaxPropietario() {
		return faxPropietario;
	}
	public void setFaxPropietario(String faxPropietario) {
		this.faxPropietario = faxPropietario;
	}
	
	@Column(name = "email_prop")
	public String getCorreoPropietario() {
		return correoPropietario;
	}
	public void setCorreoPropietario(String correoPropietario) {
		this.correoPropietario = correoPropietario;
	}
}
