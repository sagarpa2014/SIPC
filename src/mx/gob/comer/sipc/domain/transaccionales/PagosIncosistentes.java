package mx.gob.comer.sipc.domain.transaccionales;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "pagos_inconsistentes")
public class PagosIncosistentes {		
	@Id
	@GeneratedValue(generator = "pagos_inconsistentes_id_pago_inconsistente_seq")
	@SequenceGenerator(name = "pagos_inconsistentes_id_pago_inconsistente_seq", sequenceName = "pagos_inconsistentes_id_pago_inconsistente_seq")
	@Column(name =  "id_pago_inconsistente")
	private long idPagoInconsistente;
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
	@Column(name =  "folio_doc_pago")
	private String folioDocPago; 	
	@Column(name =  "imp_total_pago_sinaxc")
	private Double impTotalPagoSinaxc;
		
	public long getIdPagoInconsistente() {
		return idPagoInconsistente;
	}
	public void setIdPagoInconsistente(long idPagoInconsistente) {
		this.idPagoInconsistente = idPagoInconsistente;
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
	
	public String getFolioDocPago() {
		return folioDocPago;
	}
	public void setFolioDocPago(String folioDocPago) {
		this.folioDocPago = folioDocPago;
	}
	
	public Double getImpTotalPagoSinaxc() {
		return impTotalPagoSinaxc;
	}
	public void setImpTotalPagoSinaxc(Double impTotalPagoSinaxc) {
		this.impTotalPagoSinaxc = impTotalPagoSinaxc;
	}
		
}
