package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "constancias_almacenamiento")
public class ConstanciasAlmacenamiento {
	
	private Long idConstanciaAlmacenamiento;
	private Integer idAlmacenadora;
	private String claveBodega;
	private String folio;
	private Date fechaExpedicion;
	private Double volumen;
	private String folioCartaAdhesion;
	private Integer idCultivo;
	private Integer idVariedad;
	
	@Id
	@GeneratedValue(generator = "constancias_almacenamiento_id_constancia_almacenamiento_seq")
	@SequenceGenerator(name = "constancias_almacenamiento_id_constancia_almacenamiento_seq", sequenceName = "constancias_almacenamiento_id_constancia_almacenamiento_seq")
	@Column(name =  "id_constancia_almacenamiento")
	public Long getIdConstanciaAlmacenamiento() {
		return idConstanciaAlmacenamiento;
	}
	public void setIdConstanciaAlmacenamiento(Long idConstanciaAlmacenamiento) {
		this.idConstanciaAlmacenamiento = idConstanciaAlmacenamiento;
	}
	
	@Column(name =  "id_almacenadora")
	public Integer getIdAlmacenadora() {
		return idAlmacenadora;
	}
	
	public void setIdAlmacenadora(Integer idAlmacenadora) {
		this.idAlmacenadora = idAlmacenadora;
	}
	
	@Column(name =  "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	@Column(name =  "folio")
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	@Column(name =  "fecha_expedicion")
	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	
	@Column(name =  "volumen")
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name =  "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}

	
}
