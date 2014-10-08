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
//	private String rfcPropietario;
	private Date fechaRegistro;
	private Double latitudGrados;
	private Double latitudMinutos;
	private Double latitudSegundos;
	private Double longitudGrados;
	private Double longitudMinutos;
	private Double longitudSegundos;
	private Integer clasificacion;
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
	private Double capacidadBodega;
	private Integer noSilos;
	private Double capacidadSilos;
	private Double capacidadIntemperie;
	private Double capacidadTechada;
	private Double totalAlmacenamiento;
	private Integer numRampas;
	private Double capacidadRampas;
	private Integer numSecadoras;
	private Double capacidadSecado;
	private Integer numCargas;
	private Double capacidadCargas;
	private String piso;
	private Double capacidadPiso;
	private String ffcc;
	private Double capacidadFfcc;
	private String espuelaFfcc;
	private Double capacidadEspuela;
	private Double capacidadRecepcion;
	private Double capacidadEmbarque;
	private Double capacidadCamionera;
	private String camionera;
	private String equipoAnalisis;
	private String aplicaNormasCalidad;
	private String observaciones;
	private String domicilioFiscal;
	private String calle;
	private String numeroBodega;
	private String personaAutorizada1;
	private String puestoPersonaAutorizada1;
	private String personaAutorizada2;
	private String puestoPersonaAutorizada2;
	private String rutaCroquisBodega;
	private String nombreArchivoCroquis;
	private Integer idRepresentante;
	private String rfcReplegal;
	private Integer idLocalidadBodega;
	private Integer idEstadoDomFiscal;
	private Integer claveMunicipioDomFiscal;
	private Date fechaCalibracion;
	
	
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
//	@Column(name = "rfc_propietario")
//	public String getRfcPropietario() {
//		return rfcPropietario;
//	}
//	public void setRfcPropietario(String rfcPropietario) {
//		this.rfcPropietario = rfcPropietario;
//	}
	
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
	public Integer getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(Integer clasificacion) {
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
	public Double getCapacidadBodega() {
		return capacidadBodega;
	}
	public void setCapacidadBodega(Double capacidadBodega) {
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
	public Double getCapacidadSilos() {
		return capacidadSilos;
	}
	public void setCapacidadSilos(Double capacidadSilos) {
		this.capacidadSilos = capacidadSilos;
	}
	
	@Column(name = "capacidad_intemperie")
	public Double getCapacidadIntemperie() {
		return capacidadIntemperie;
	}
	public void setCapacidadIntemperie(Double capacidadIntemperie) {
		this.capacidadIntemperie = capacidadIntemperie;
	}
	
	@Column(name = "capacidad_techada")
	public Double getCapacidadTechada() {
		return capacidadTechada;
	}
	public void setCapacidadTechada(Double capacidadTechada) {
		this.capacidadTechada = capacidadTechada;
	}
	
	@Column(name = "total_almacenamiento")
	public Double getTotalAlmacenamiento() {
		return totalAlmacenamiento;
	}
	public void setTotalAlmacenamiento(Double totalAlmacenamiento) {
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
	public Double getCapacidadRampas() {
		return capacidadRampas;
	}
	public void setCapacidadRampas(Double capacidadRampas) {
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
	public Double getCapacidadSecado() {
		return capacidadSecado;
	}
	public void setCapacidadSecado(Double capacidadSecado) {
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
	public Double getCapacidadCargas() {
		return capacidadCargas;
	}
	public void setCapacidadCargas(Double capacidadCargas) {
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
	public Double getCapacidadPiso() {
		return capacidadPiso;
	}
	public void setCapacidadPiso(Double capacidadPiso) {
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
	public Double getCapacidadFfcc() {
		return capacidadFfcc;
	}
	public void setCapacidadFfcc(Double capacidadFfcc) {
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
	public Double getCapacidadEspuela() {
		return capacidadEspuela;
	}
	public void setCapacidadEspuela(Double capacidadEspuela) {
		this.capacidadEspuela = capacidadEspuela;
	}
	
	@Column(name = "capacidad_recepcion")
	public Double getCapacidadRecepcion() {
		return capacidadRecepcion;
	}
	public void setCapacidadRecepcion(Double capacidadRecepcion) {
		this.capacidadRecepcion = capacidadRecepcion;
	}
	
	@Column(name = "capacidad_embarque")
	public Double getCapacidadEmbarque() {
		return capacidadEmbarque;
	}
	public void setCapacidadEmbarque(Double capacidadEmbarque) {
		this.capacidadEmbarque = capacidadEmbarque;
	}

	@Column(name = "capacidad_camionera")
	public Double getCapacidadCamionera() {
		return capacidadCamionera;
	}
	public void setCapacidadCamionera(Double capacidadCamionera) {
		this.capacidadCamionera = capacidadCamionera;
	}
	
	@Column(name = "camionera")
	public String getCamionera() {
		return camionera;
	}
	public void setCamionera(String camionera) {
		this.camionera = camionera;
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
	
	@Column(name = "domicilio_fiscal")
	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}
	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}
	
	@Column(name = "calle_bodega")
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	@Column(name = "numero_bodega")
	public String getNumeroBodega() {
		return numeroBodega;
	}
	public void setNumeroBodega(String numeroBodega) {
		this.numeroBodega = numeroBodega;
	}
	
	@Column(name = "persona_autorizada1")
	public String getPersonaAutorizada1() {
		return personaAutorizada1;
	}
	public void setPersonaAutorizada1(String personaAutorizada1) {
		this.personaAutorizada1 = personaAutorizada1;
	}
	
	@Column(name = "puesto_persona_autorizada1")
	public String getPuestoPersonaAutorizada1() {
		return puestoPersonaAutorizada1;
	}
	public void setPuestoPersonaAutorizada1(String puestoPersonaAutorizada1) {
		this.puestoPersonaAutorizada1 = puestoPersonaAutorizada1;
	}
	
	@Column(name = "persona_autorizada2")
	public String getPersonaAutorizada2() {
		return personaAutorizada2;
	}
	public void setPersonaAutorizada2(String personaAutorizada2) {
		this.personaAutorizada2 = personaAutorizada2;
	}
	
	@Column(name = "puesto_persona_autorizada2")
	public String getPuestoPersonaAutorizada2() {
		return puestoPersonaAutorizada2;
	}
	
	public void setPuestoPersonaAutorizada2(String puestoPersonaAutorizada2) {
		this.puestoPersonaAutorizada2 = puestoPersonaAutorizada2;
	}
	
	@Column(name = "ruta_croquis_bodega")
	public String getRutaCroquisBodega() {
		return rutaCroquisBodega;
	}
	public void setRutaCroquisBodega(String rutaCroquisBodega) {
		this.rutaCroquisBodega = rutaCroquisBodega;
	}
	
	@Column(name = "nombre_archivo_croquis")
	public String getNombreArchivoCroquis() {
		return nombreArchivoCroquis;
	}
	public void setNombreArchivoCroquis(String nombreArchivoCroquis) {
		this.nombreArchivoCroquis = nombreArchivoCroquis;
	}

	@Column(name = "id_representante")
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	@Column(name = "rfc_replegal")
	public String getRfcReplegal() {
		return rfcReplegal;
	}
	public void setRfcReplegal(String rfcReplegal) {
		this.rfcReplegal = rfcReplegal;
	}
	
	@Column(name = "id_localidad_bodega")
	public Integer getIdLocalidadBodega() {
		return idLocalidadBodega;
	}
	public void setIdLocalidadBodega(Integer idLocalidadBodega) {
		this.idLocalidadBodega = idLocalidadBodega;
	}
	
	
	@Column(name = "id_estado_dom_fiscal")
	public Integer getIdEstadoDomFiscal() {
		return idEstadoDomFiscal;
	}
	public void setIdEstadoDomFiscal(Integer idEstadoDomFiscal) {
		this.idEstadoDomFiscal = idEstadoDomFiscal;
	}
	
	@Column(name = "clave_municipio_dom_fiscal")
	public Integer getClaveMunicipioDomFiscal() {
		return claveMunicipioDomFiscal;
	}
	public void setClaveMunicipioDomFiscal(Integer claveMunicipioDomFiscal) {
		this.claveMunicipioDomFiscal = claveMunicipioDomFiscal;
	}
	
	@Column(name = "fecha_calibracion")
	public Date getFechaCalibracion() {
		return fechaCalibracion;
	}
	public void setFechaCalibracion(Date fechaCalibracion) {
		this.fechaCalibracion = fechaCalibracion;
	}

	
}
