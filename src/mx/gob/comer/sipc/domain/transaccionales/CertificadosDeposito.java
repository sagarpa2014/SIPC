package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "certificados_deposito")
public class CertificadosDeposito {
	
	private Long idCertificadoDeposito;
	private Integer idAlmacenadora;
	private String claveBodega;
	private String folio;
	private Date fechaExpedicion;
	private Date fechaFinVigencia;
	private Double volumen;
	private String folioCartaAdhesion;
	private Integer idCultivo;
	private Integer idVariedad;
	
	@Id
	@GeneratedValue(generator = "certificados_deposito_id_certificado_deposito_seq")
	@SequenceGenerator(name = "certificados_deposito_id_certificado_deposito_seq", sequenceName = "certificados_deposito_id_certificado_deposito_seq")
	@Column(name =  "id_certificado_deposito")
	public Long getIdCertificadoDeposito() {
		return idCertificadoDeposito;
	}
	public void setIdCertificadoDeposito(Long idCertificadoDeposito) {
		this.idCertificadoDeposito = idCertificadoDeposito;
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
	
	@Column(name =  "fecha_fin_vigencia")
	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
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
