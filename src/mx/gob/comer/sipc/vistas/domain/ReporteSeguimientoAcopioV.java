package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reporte_seguimiento_acopio_v")
public class ReporteSeguimientoAcopioV {
		
	private long id;
	private Integer idEstadoBodega;
	private String nombreEstadoBodega;
	private Integer idCultivo;
	private String nombreCultivo;	
	private Integer idCiclo;
	private Integer idCicloSeg;
	private String ciclo;
	private Integer ejercicio;
	private String claveBodega;
	private String nombreBodega;
	private String nombreLocalBodega;
	private String rfcOperador;
	private String nombreOperador;
	private Integer idComprador;
	private String nombreComprador;
	private Double volumenAXC;
	private Double precioPromPagAXC;
	private Double volumenMercadoLibre;
	private Double acopioTotalTon;
	private Double pagadoTon;
	private Double pagadoPorcentaje;
	private Double movilizadoFurgon;
	private Double movilizadoCamion;
	private Double movilizadoMaritimo;
	private Double movilizadoAutoconsumo;
	private Double movilizadoTotal;
	private Double existenciaAM;
	private Double avanceCosecha;
	
	@Id	
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	@Column(name = "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name = "id_ciclo_seg")
	public Integer getIdCicloSeg() {
		return idCicloSeg;
	}
	public void setIdCicloSeg(Integer idCicloSeg) {
		this.idCicloSeg = idCicloSeg;
	}

	@Column(name = "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name = "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	@Column(name = "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
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
	
	@Column(name = "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name = "nombre_comprador")
	public String getNombreComprador() {
		return nombreComprador;
	}
	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}
	
	@Column(name = "volumen_axc")
	public Double getVolumenAXC() {
		return volumenAXC;
	}
	public void setVolumenAXC(Double volumenAXC) {
		this.volumenAXC = volumenAXC;
	}
	
	@Column(name = "precio_prom_pag_axc")
	public Double getPrecioPromPagAXC() {
		return precioPromPagAXC;
	}
	public void setPrecioPromPagAXC(Double precioPromPagAXC) {
		this.precioPromPagAXC = precioPromPagAXC;
	}
	
	@Column(name = "volumen_mercado_libre")
	public Double getVolumenMercadoLibre() {
		return volumenMercadoLibre;
	}
	public void setVolumenMercadoLibre(Double volumenMercadoLibre) {
		this.volumenMercadoLibre = volumenMercadoLibre;
	}
	
	@Column(name = "acopio_total_ton")
	public Double getAcopioTotalTon() {
		return acopioTotalTon;
	}
	public void setAcopioTotalTon(Double acopioTotalTon) {
		this.acopioTotalTon = acopioTotalTon;
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
	
	@Column(name = "movilizado_furgon")
	public Double getMovilizadoFurgon() {
		return movilizadoFurgon;
	}
	public void setMovilizadoFurgon(Double movilizadoFurgon) {
		this.movilizadoFurgon = movilizadoFurgon;
	}
	
	@Column(name = "movilizado_camion")
	public Double getMovilizadoCamion() {
		return movilizadoCamion;
	}
	public void setMovilizadoCamion(Double movilizadoCamion) {
		this.movilizadoCamion = movilizadoCamion;
	}
	
	@Column(name = "movilizado_maritimo")
	public Double getMovilizadoMaritimo() {
		return movilizadoMaritimo;
	}
	public void setMovilizadoMaritimo(Double movilizadoMaritimo) {
		this.movilizadoMaritimo = movilizadoMaritimo;
	}
	
	@Column(name = "movilizado_total")
	public Double getMovilizadoTotal() {
		return movilizadoTotal;
	}
	public void setMovilizadoTotal(Double movilizadoTotal) {
		this.movilizadoTotal = movilizadoTotal;
	}
	
	@Column(name = "existencia_am")
	public Double getExistenciaAM() {
		return existenciaAM;
	}
	public void setExistenciaAM(Double existenciaAM) {
		this.existenciaAM = existenciaAM;
	}
	
	@Column(name = "avance_cosecha")
	public Double getAvanceCosecha() {
		return avanceCosecha;
	}
	public void setAvanceCosecha(Double avanceCosecha) {
		this.avanceCosecha = avanceCosecha;
	}
	
	@Column(name = "movilizado_autoconsumo")
	public Double getMovilizadoAutoconsumo() {
		return movilizadoAutoconsumo;
	}
	public void setMovilizadoAutoconsumo(Double movilizadoAutoconsumo) {
		this.movilizadoAutoconsumo = movilizadoAutoconsumo;
	}
}
