package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagos_cartas_adhesion_v")
public class PagosCartasAdhesionV {
	
	private Long idPagosCA;
	private String folioCartaAdhesion;
	private Integer idEstado;
	private String estado;
	private Integer idCultivo;
	private String cultivo;
	private Integer idVariedad;
	private String variedad;
	private String bodega;
	private Double volumen;
	private Double importe;
	private Double cuota;
	private String etapa;
	
	@Id
	@Column(name = "id")
	public Long getIdPagosCA() {
		return idPagosCA;
	}
	public void setIdPagosCA(Long idPagosCA) {
		this.idPagosCA = idPagosCA;
	}
	
	@Column(name = "folio_carta_adhesion")        
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
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
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
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
	
	@Column(name = "cuota")
	public Double getCuota() {
		return cuota;
	}
	
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	
	@Column(name = "etapa")
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}	
	
	
  
}
