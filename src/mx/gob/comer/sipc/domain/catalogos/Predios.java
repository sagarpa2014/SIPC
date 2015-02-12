package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "predios_relaciones")
public class Predios {
	
	private Long id;
	private String predio;
	private Long predioSecuencial;
	private String predioAlterno;
	private Double superficieTotal; 
	private String folioProductor; 
	private String modalidad; 
	private Integer idEstado; 
	private Double volumen; 
	private String validado; 
	private String pagado; 
	  	
	@Id
	@Column(name =  "id_predio")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name =  "predio")
	public String getPredio() {
		return predio;
	}
	public void setPredio(String predio) {
		this.predio = predio;
	}
	
	@Column(name =  "predio_secuencial")
	public Long getPredioSecuencial() {
		return predioSecuencial;
	}
	public void setPredioSecuencial(Long predioSecuencial) {
		this.predioSecuencial = predioSecuencial;
	}
	
	@Column(name =  "predio_alterno")
	public String getPredioAlterno() {
		return predioAlterno;
	}
	public void setPredioAlterno(String predioAlterno) {
		this.predioAlterno = predioAlterno;
	}	
	
	@Column(name ="superficie")
	public Double getSuperficieTotal() {
		return superficieTotal;
	}
	
	public void setSuperficieTotal(Double superficieTotal) {
		this.superficieTotal = superficieTotal;
	}
	
	@Column(name ="productor")
	public String getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(String folioProductor) {
		this.folioProductor = folioProductor;
	}
	
	@Column(name ="modalidad")
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
	@Column(name ="estado_id")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	@Column(name ="volumen")
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name ="validado")
	public String getValidado() {
		return validado;
	}
	public void setValidado(String validado) {
		this.validado = validado;
	}
	
	@Column(name ="pagado")
	public String getPagado() {
		return pagado;
	}
	public void setPagado(String pagado) {
		this.pagado = pagado;
	}
	
}
