package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seguimiento_centro_acopio_v")
public class SeguimientoCentroAcopioV {
		
	private long idSeguimientoCA;
	private Integer idCiclo;
	private String cicloLargo;
	private String cicloCorto;
	private String ciclo;
	private Integer ejercicio;
	private Date periodoInicial; 
	private Date periodoFinal;
	private String claveBodega;
	private String nombreBodega;
	private String nombreLocalBodega;
	private String rfcOperador;
	private String nombreOperador;
	//private Integer operadorCentroAcopio;
	//private String nombreCentroAcopio;
	//private String nomRegionalCentroAcopio;
	private Integer idCultivo;
	private String nombreCultivo;
	private Double volumenMercadoLibre;
	private Double volumenAXC;
	private Double acopioTotalTon;
	private Integer idComprador;
	private String nombreComprador;
	private Double pagadoTon;
	private Double pagadoPorcentaje;
	private Double precioPromPagAXC;
	private Double precioPromPagLibre;
	private Double mfurgon;
	private Double mcamion;
	private Double mmaritimo;
	private Double mtotal;
	private Double existenciaAM;
	private Integer idEstado;
	private String destino;
	private String nombreEstado;
	private String observaciones;
	private String avanceCosecha;
	private Date fechaEnvio;
	private Integer usuarioRegistro;
	private Date fechaRegistro;
	private Integer usuarioActualiza;
	private Date fechaActualiza;
	private Integer idEstatus;
	private String descEstatus;
	private Integer idEstadoBodega;
	private String nombreEstadoBodega;
	private Integer idCicloSeg;
	private Integer idVariedad;
	private String nombreVariedad;
	
	@Id	
	@Column(name = "id_seguimiento_ca")
	public long getIdSeguimientoCA() {
		return idSeguimientoCA;
	}
	public void setIdSeguimientoCA(long idSeguimientoCA) {
		this.idSeguimientoCA = idSeguimientoCA;
	}
	
	
	@Column(name = "id_ciclo")	
	public Integer getIdCiclo() {
		return idCiclo;
	}
	
	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name = "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	@Column(name = "periodo_inicial")
	public Date getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	@Column(name = "periodo_final")
	public Date getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	
	@Column(name = "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}	
	
	/*@Column(name = "operador_centro_acopio")
	public Integer getOperadorCentroAcopio() {
		return operadorCentroAcopio;
	}
	public void setOperadorCentroAcopio(Integer operadorCentroAcopio) {
		this.operadorCentroAcopio = operadorCentroAcopio;
	}*/
	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name = "volumen_mercado_libre")
	public Double getVolumenMercadoLibre() {
		return volumenMercadoLibre;
	}
	public void setVolumenMercadoLibre(Double volumenMercadoLibre) {
		this.volumenMercadoLibre = volumenMercadoLibre;
	}
	
	@Column(name = "volumen_AXC")
	public Double getVolumenAXC() {
		return volumenAXC;
	}
	public void setVolumenAXC(Double volumenAXC) {
		this.volumenAXC = volumenAXC;
	}
	
	@Column(name = "acopio_total_ton")
	public Double getAcopioTotalTon() {
		return acopioTotalTon;
	}
	public void setAcopioTotalTon(Double acopioTotalTon) {
		this.acopioTotalTon = acopioTotalTon;
	}
	
	@Column(name = "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name = "pagado_ton")
	public Double getPagadoTon() {
		return pagadoTon;
	}
	public void setPagadoTon(Double pagadoTon) {
		this.pagadoTon = pagadoTon;
	}
	
	@Column(name = "pagado_porcentaje")
	public Double getPagadoPorcentaje() {
		return pagadoPorcentaje;
	}
	public void setPagadoPorcentaje(Double pagadoPorcentaje) {
		this.pagadoPorcentaje = pagadoPorcentaje;
	}
	
	@Column(name = "precio_prom_pag_axc")
	public Double getPrecioPromPagAXC() {
		return precioPromPagAXC;
	}
	public void setPrecioPromPagAXC(Double precioPromPagAXC) {
		this.precioPromPagAXC = precioPromPagAXC;
	}
	
	@Column(name = "precio_prom_pag_libre")
	public Double getPrecioPromPagLibre() {
		return precioPromPagLibre;
	}
	public void setPrecioPromPagLibre(Double precioPromPagLibre) {
		this.precioPromPagLibre = precioPromPagLibre;
	}
	
	@Column(name = "mov_furgon")
	public Double getMfurgon() {
		return mfurgon;
	}
	public void setMfurgon(Double mfurgon) {
		this.mfurgon = mfurgon;
	}
	
	@Column(name = "mov_camion")
	public Double getMcamion() {
		return mcamion;
	}
	public void setMcamion(Double mcamion) {
		this.mcamion = mcamion;
	}
	
	@Column(name = "mov_maritimo")
	public Double getMmaritimo() {
		return mmaritimo;
	}
	public void setMmaritimo(Double mmaritimo) {
		this.mmaritimo = mmaritimo;
	}
	
	@Column(name = "mov_total")
	public Double getMtotal() {
		return mtotal;
	}
	public void setMtotal(Double mtotal) {
		this.mtotal = mtotal;
	}
	
	@Column(name = "existencia_am")
	public Double getExistenciaAM() {
		return existenciaAM;
	}
	public void setExistenciaAM(Double existenciaAM) {
		this.existenciaAM = existenciaAM;
	}
	
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	@Column(name = "avance_cosecha")
	public String getAvanceCosecha() {
		return avanceCosecha;
	}
	public void setAvanceCosecha(String avanceCosecha) {
		this.avanceCosecha = avanceCosecha;
	}
	
	@Column(name = "fecha_envio")
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
		
	@Column(name = "usuario_registro")
	public Integer getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
	@Column(name = "fecha_registro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name = "usuario_actualiza")
	public Integer getUsuarioActualiza() {
		return usuarioActualiza;
	}
	public void setUsuarioActualiza(Integer usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	
	@Column(name = "fecha_actualiza")
	public Date getFechaActualiza() {
		return fechaActualiza;
	}
	public void setFechaActualiza(Date fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}
	
	@Column(name = "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}
	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}
	
	@Column(name = "ciclo_corto")
	public String getCicloCorto() {
		return cicloCorto;
	}
	public void setCicloCorto(String cicloCorto) {
		this.cicloCorto = cicloCorto;
	}
	
	@Column(name = "nombre_bodega")
	public String getNombreBodega() {
		return nombreBodega;
	}
	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}
	
	@Column(name = "nombre_local")
	public String getNombreLocalBodega() {
		return nombreLocalBodega;
	}
	public void setNombreLocalBodega(String nombreLocalBodega) {
		this.nombreLocalBodega = nombreLocalBodega;
	}
	
	@Column(name = "rfc_operador")
	public String getRfcOperador() {
		return rfcOperador;
	}
	public void setRfcOperador(String rfcOperador) {
		this.rfcOperador = rfcOperador;
	}
	
	@Column(name = "nombre_operador")
	public String getNombreOperador() {
		return nombreOperador;
	}
	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}
	
	@Column(name = "nombre_cultivo")
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}
	
	@Column(name = "nombre_comprador")
	public String getNombreComprador() {
		return nombreComprador;
	}
	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}
	
	@Column(name = "nombre_estado")
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	@Column(name = "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	@Column(name = "destino")
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	@Column(name = "id_estatus")
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	
	@Column(name = "desc_estatus")
	public String getDescEstatus() {
		return descEstatus;
	}
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	@Column(name = "id_estado_bodega")
	public Integer getIdEstadoBodega() {
		return idEstadoBodega;
	}
	public void setIdEstadoBodega(Integer idEstadoBodega) {
		this.idEstadoBodega = idEstadoBodega;
	}
	
	@Column(name = "nombre_estado_bodega")
	public String getNombreEstadoBodega() {
		return nombreEstadoBodega;
	}
	public void setNombreEstadoBodega(String nombreEstadoBodega) {
		this.nombreEstadoBodega = nombreEstadoBodega;
	}

	@Column(name = "id_ciclo_seg")
	public Integer getIdCicloSeg() {
		return idCicloSeg;
	}
	public void setIdCicloSeg(Integer idCicloSeg) {
		this.idCicloSeg = idCicloSeg;
	}
	
	@Column(name = "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	
	@Column(name = "nombre_variedad")
	public String getNombreVariedad() {
		return nombreVariedad;
	}
	public void setNombreVariedad(String nombreVariedad) {
		this.nombreVariedad = nombreVariedad;
	}
}
