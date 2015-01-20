package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
*PagosDetalleV
*
*Clase que mapea a la tabla "pagos_detalle_ca_v" de la base de datos  
*
*Versión 1.0
*
*Enero 2013
*
*/

@Entity
@Table(name="pagos_detalle_ca_v")
public class PagosDetalleCAV {
	
	private long idPagoDetalle;
	private int idPago;
	private int idEstado;
	private String etapa;
	private int idEtapa;
	private Double volumen;	
	private Double importe;
	private String estado;
	private int idCultivo;
	private String cultivo;	
	private int idVariedad;
	private String variedad;
	private String bodega;
	private Double cuota;
	private int idStatus;
	private String descStatus;
	private String folioCartaAdhesion;
	private Double volumenAutorizado;	
	private Double importeAutorizado;
	private Double volumenApoyado;	
	private Double importeApoyado;
	private int idPrograma;
	private String programa;
	private int idComprador;
	private String nombreComprador;
	private String clabe;
	private Integer idEspecialista;
	private Integer idCriterioPago;
	private String criterioPago;
	private String descEstatus;
	private String descFianza;
	private Integer solicitudesAtendidas;
	private Integer productoresBeneficiados;
	private Integer idEstatusCA;
	private Long idAsiganacionCA;
	
	public PagosDetalleCAV(){}
	
	public PagosDetalleCAV(int idEstado,
			String estado, int idCultivo, String cultivo, int idVariedad,
			String variedad, String bodega, Double cuota,
			String folioCartaAdhesion, Double volumenAutorizado,
			Double importeAutorizado, Double volumenApoyado,
			Double importeApoyado, int idPrograma, String programa,
			String clabe, Integer idEspecialista, Integer idCriterioPago,
			String criterioPago, Integer solicitudesAtendidas,
			Integer productoresBeneficiados, Integer idComprador,String nombreComprador, String descEstatus,
			String descFianza, Long idAsiganacionCA, Double volumen) {
		super();
		this.idEstado = idEstado;
		this.estado = estado;
		this.idCultivo = idCultivo;
		this.cultivo = cultivo;
		this.idVariedad = idVariedad;
		this.variedad = variedad;
		this.bodega = bodega;
		this.cuota = cuota;
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.volumenAutorizado = volumenAutorizado;
		this.importeAutorizado = importeAutorizado;
		this.volumenApoyado = volumenApoyado;
		this.importeApoyado = importeApoyado;
		this.idPrograma = idPrograma;
		this.programa = programa;
		this.clabe = clabe;
		this.idEspecialista = idEspecialista;
		this.idCriterioPago = idCriterioPago;
		this.criterioPago = criterioPago;
		this.solicitudesAtendidas = solicitudesAtendidas;
		this.productoresBeneficiados = productoresBeneficiados;
		this.idComprador = idComprador;
		this.descEstatus = descEstatus; 
		this.nombreComprador = nombreComprador;
		this.descFianza = descFianza;
		this.idAsiganacionCA = idAsiganacionCA;
		this.volumen = volumen;
	}



	@Id
	@Column(name = "id_pago_detalle")
	public long getIdPagoDetalle() {
		return idPagoDetalle;
	}

	public void setIdPagoDetalle(long idPagoDetalle) {
		this.idPagoDetalle = idPagoDetalle;
	}
	
	
	@Column(name = "id_pago")
	public int getIdPago() {
		return idPago;
	}

	
	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	@Column(name = "id_estado")
	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "etapa")
	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	@Column(name = "volumen")
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name = "importe")
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "id_cultivo")
	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	@Column(name = "cultivo")
	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	@Column(name = "id_variedad")
	public int getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(int idVariedad) {
		this.idVariedad = idVariedad;
	}

	@Column(name = "variedad")
	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	@Column(name = "bodega")
	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	@Column(name = "cuota")
	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	@Column(name = "estatus")
	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	@Column(name = "desc_estatus")
	public String getDescStatus() {
		return descStatus;
	}

	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	@Column(name = "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	@Column(name = "volumen_autorizado")
	public Double getVolumenAutorizado() {
		return volumenAutorizado;
	}

	public void setVolumenAutorizado(Double volumenAutorizado) {
		this.volumenAutorizado = volumenAutorizado;
	}

	@Column(name = "importe_autorizado")
	public Double getImporteAutorizado() {
		return importeAutorizado;
	}

	public void setImporteAutorizado(Double importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}

	@Column(name = "volumen_apoyado")
	public Double getVolumenApoyado() {
		return volumenApoyado;
	}

	public void setVolumenApoyado(Double volumenApoyado) {
		this.volumenApoyado = volumenApoyado;
	}

	@Column(name = "importe_apoyado")
	public Double getImporteApoyado() {
		return importeApoyado;
	}

	public void setImporteApoyado(Double importeApoyado) {
		this.importeApoyado = importeApoyado;
	}

	@Column(name = "id_etapa")
	public int getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(int idEtapa) {
		this.idEtapa = idEtapa;
	}

	@Column(name = "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	@Column(name = "nombre_pgr_corto")
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	@Column(name = "id_comprador")
	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}

	@Column(name = "nombre_comprador")
	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	@Column(name = "clabe")
	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	@Column(name = "id_especialista")
	public Integer getIdEspecialista() {
		return idEspecialista;
	}

	public void setIdEspecialista(Integer idEspecialista) {
		this.idEspecialista = idEspecialista;
	}

	@Column(name = "criterio_pago")
	public Integer getIdCriterioPago() {
		return idCriterioPago;
	}

	public void setIdCriterioPago(Integer idCriterioPago) {
		this.idCriterioPago = idCriterioPago;
	}

	@Column(name = "des_criterio_pago")
	public String getCriterioPago() {
		return criterioPago;
	}

	public void setCriterioPago(String criterioPago) {
		this.criterioPago = criterioPago;
	}

	@Column(name = "estatus_ca")
	public String getDescEstatus() {
		return descEstatus;
	}

	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	@Column(name = "desc_fianza")
	public String getDescFianza() {
		return descFianza;
	}

	public void setDescFianza(String descFianza) {
		this.descFianza = descFianza;
	}
	@Column(name = "solicitudes_atendidas")
	public Integer getSolicitudesAtendidas() {
		return solicitudesAtendidas;
	}

	public void setSolicitudesAtendidas(Integer solicitudesAtendidas) {
		this.solicitudesAtendidas = solicitudesAtendidas;
	}

	@Column(name = "productores_beneficiados")
	public Integer getProductoresBeneficiados() {
		return productoresBeneficiados;
	}

	public void setProductoresBeneficiados(Integer productoresBeneficiados) {
		this.productoresBeneficiados = productoresBeneficiados;
	}

	@Column(name = "id_estatus_ca")
	public Integer getIdEstatusCA() {
		return idEstatusCA;
	}

	public void setIdEstatusCA(Integer idEstatusCA) {
		this.idEstatusCA = idEstatusCA;
	}

	@Column(name = "id_asignacion_ca")
	public Long getIdAsiganacionCA() {
		return idAsiganacionCA;
	}

	public void setIdAsiganacionCA(Long idAsiganacionCA) {
		this.idAsiganacionCA = idAsiganacionCA;
	}
	
	
}