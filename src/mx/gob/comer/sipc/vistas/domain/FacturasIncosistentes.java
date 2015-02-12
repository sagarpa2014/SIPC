package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "facturas_inconsistentes")
public class FacturasIncosistentes {		
	@Id
	@GeneratedValue(generator = "facturas_inconsistentes_id_fac_inconsistente_seq")
	@SequenceGenerator(name = "facturas_inconsistentes_id_fac_inconsistente_seq", sequenceName = "facturas_inconsistentes_id_fac_inconsistente_seq")
	@Column(name =  "id_fac_inconsistente")
	private long idFacInconsistente;
	@Column(name = "id_bitacora_relcompras")//
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
	@Column(name =  "folio_factura_venta")
	private String folioFacturaVenta;	
	@Column(name =  "vol_total_fac_venta")
	private Double volTotalFacVenta;
	
	public long getIdFacInconsistente() {
		return idFacInconsistente;
	}
	public void setIdFacInconsistente(long idFacInconsistente) {
		this.idFacInconsistente = idFacInconsistente;
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
	
	public String getFolioFacturaVenta() {
		return folioFacturaVenta;
	}
	public void setFolioFacturaVenta(String folioFacturaVenta) {
		this.folioFacturaVenta = folioFacturaVenta;
	}
	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
		
}
