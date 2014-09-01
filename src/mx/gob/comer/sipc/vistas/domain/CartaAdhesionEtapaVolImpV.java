package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carta_adhesion_etapa_volimp_v")
public class CartaAdhesionEtapaVolImpV {
	
	private long id;
	private String folioCartaAdhesion;
	private Integer idEtapa;
	private String etapa;
	private Double volumen;
	private Double importe;
	
	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "folio_carta_adhesion")        
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	@Column(name = "id_etapa")
	public Integer getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
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
}
