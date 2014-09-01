package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rendicion_cuentas_v")
public class RendicionCuentasV {
		
	private long id;
	private Date fechaPago;
	private String noCarta;
	private Long idPago;
	private Integer idEstado;
	private String nombreEstado;
	private Integer idMunicipio;
	private String nombreMunicipio;	
	private String programa;
	private String componente;
	private String subComponente;
	private Integer idCultivo;
	private String nombreCultivo;
	private Date fecha;
	private String estatusMonto;
	private Double montoFederal;
	private String apoyo;
	private Double cantidad;
	private Integer unidadMedida;
	private String instanciaEjecutora;
	private String cicloAgricola;
	private Integer anioEjercicio;
	private Integer idSistema;
	private String beneficiariosH;
	private String beneficiariosM;
	private String curp;
	private String rfc;
	private String primerApellido;
	private String segundoApellido;
	private String nombreComprador;
	private String razonSocial;
	private String sexo;
	private Date fechaNacimiento;
	private Integer entidadNacimiento;
	private Integer tipoPersona;
	private String idSuri;

	@Id	
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "fecha_pago")
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	@Column(name = "no_carta")
	public String getNoCarta() {
		return noCarta;
	}
	public void setNoCarta(String noCarta) {
		this.noCarta = noCarta;
	}
	@Column(name = "id_pago")
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	@Column(name = "nombre_estado")
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	@Column(name = "id_municipio")
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	@Column(name = "nombre_municipio")
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	@Column(name = "programa")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@Column(name = "componente")
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	@Column(name = "subcomponente")
	public String getSubComponente() {
		return subComponente;
	}
	public void setSubComponente(String subComponente) {
		this.subComponente = subComponente;
	}
	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	@Column(name = "nombre_cultivo")
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}
	@Column(name = "fecha")
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Column(name = "estatus_monto")
	public String getEstatusMonto() {
		return estatusMonto;
	}
	public void setEstatusMonto(String estatusMonto) {
		this.estatusMonto = estatusMonto;
	}
	@Column(name = "monto_federal")
	public Double getMontoFederal() {
		return montoFederal;
	}
	public void setMontoFederal(Double montoFederal) {
		this.montoFederal = montoFederal;
	}
	@Column(name = "apoyo")
	public String getApoyo() {
		return apoyo;
	}
	public void setApoyo(String apoyo) {
		this.apoyo = apoyo;
	}
	@Column(name = "cantidad")
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	@Column(name = "unidad_medida")
	public Integer getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(Integer unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	@Column(name = "instancia_ejecutora")
	public String getInstanciaEjecutora() {
		return instanciaEjecutora;
	}
	public void setInstanciaEjecutora(String instanciaEjecutora) {
		this.instanciaEjecutora = instanciaEjecutora;
	}
	@Column(name = "ciclo_agricola")
	public String getCicloAgricola() {
		return cicloAgricola;
	}
	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}
	@Column(name = "anio_ejercicio")
	public Integer getAnioEjercicio() {
		return anioEjercicio;
	}
	public void setAnioEjercicio(Integer anioEjercicio) {
		this.anioEjercicio = anioEjercicio;
	}
	@Column(name = "id_sistema")
	public Integer getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}
	@Column(name = "beneficiarios_h")
	public String getBeneficiariosH() {
		return beneficiariosH;
	}
	public void setBeneficiariosH(String beneficiariosH) {
		this.beneficiariosH = beneficiariosH;
	}
	@Column(name = "beneficiarios_m")
	public String getBeneficiariosM() {
		return beneficiariosM;
	}
	public void setBeneficiariosM(String beneficiariosM) {
		this.beneficiariosM = beneficiariosM;
	}
	@Column(name = "curp")
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	@Column(name = "rfc")
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	@Column(name = "primer_apellido")
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	@Column(name = "segundo_apellido")
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	@Column(name = "nombre_comprador")
	public String getNombreComprador() {
		return nombreComprador;
	}
	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}
	@Column(name = "razon_social")
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	@Column(name = "sexo")
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	@Column(name = "tipo_persona")
	public Integer getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(Integer tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	@Column(name = "id_suri")
	public String getIdSuri() {
		return idSuri;
	}
	public void setIdSuri(String idSuri) {
		this.idSuri = idSuri;
	}
}