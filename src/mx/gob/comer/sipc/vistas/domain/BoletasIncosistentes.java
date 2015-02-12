package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "boletas_inconsistentes")
public class BoletasIncosistentes {

	@Id
	@GeneratedValue(generator = "boletas_inconsistentes_id_bol_inconsistente_seq")
	@SequenceGenerator(name = "boletas_inconsistentes_id_bol_inconsistente_seq", sequenceName = "boletas_inconsistentes_id_bol_inconsistente_seq")
	@Column(name =  "id_bol_inconsistente")
	private Long idBolInconsistente;	
	@Column(name = "id_bitacora_relcompras", updatable=false, insertable=false)
	private Long idBitacoraRelcompras;
	@Column(name =  "folio_carta_adhesion")
	private String folioCartaAdhesion;		
	@Column(name =  "clave_bodega")
	private String claveBodega;	
	@Column(name =  "nombre_estado")
	private String nombreEstado;
	@Column(name =  "folio_contrato")
	private String folioContrato;	
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "boleta_ticket_bascula")
	private String boletaTicketBascula;
	@Column(name =  "vol_bol_ticket")
	private Double volBolTicket;
		
	
	public Long getIdBolInconsistente() {
		return idBolInconsistente;
	}
	public void setIdBolInconsistente(Long idBolInconsistente) {
		this.idBolInconsistente = idBolInconsistente;
	}

	public Long getIdBitacoraRelcompras() {
		return idBitacoraRelcompras;
	}
	public void setIdBitacoraRelcompras(Long idBitacoraRelcompras) {
		this.idBitacoraRelcompras = idBitacoraRelcompras;
	}
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
	public String getPaternoProductor() {
		return paternoProductor;
	}
	public void setPaternoProductor(String paternoProductor) {
		this.paternoProductor = paternoProductor;
	}
	public String getMaternoProductor() {
		return maternoProductor;
	}
	public void setMaternoProductor(String maternoProductor) {
		this.maternoProductor = maternoProductor;
	}
	public String getNombreProductor() {
		return nombreProductor;
	}
	public void setNombreProductor(String nombreProductor) {
		this.nombreProductor = nombreProductor;
	}
	public String getBoletaTicketBascula() {
		return boletaTicketBascula;
	}
	public void setBoletaTicketBascula(String boletaTicketBascula) {
		this.boletaTicketBascula = boletaTicketBascula;
	}
	public Double getVolBolTicket() {
		return volBolTicket;
	}
	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
	}
	
}
