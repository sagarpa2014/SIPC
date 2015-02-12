package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
*Pagos
*
*Clase que mapea a la tabla "bitacora_relcompras_detalle_hco" de la base de datos  
*
*Versión 1.0
*
*Octubre 2014
*
*/

@Entity
@Table(name="bitacora_relcompras_detalle_hco")
public class BitacoraRelcomprasDetalleHCO {

	@Id
	@GeneratedValue(generator = "bitacora_relcompras_detalle_h_id_bitacora_relcompras_detall_seq")
	@SequenceGenerator(name = "bitacora_relcompras_detalle_h_id_bitacora_relcompras_detall_seq", sequenceName = "bitacora_relcompras_detalle_h_id_bitacora_relcompras_detall_seq")
	@Column(name = "id_bitacora_relcompras_detalle_hco")
	private Long idBitacoraRelcomprasDetalleHCO;
	@Column(name = "id_bitacora_relcompras_hco", updatable=false, insertable=false)
	private Long idBitacoraRelcomprasHCO;
	@Column(name = "mensaje")
	private String mensaje;
	
	public Long getIdBitacoraRelcomprasDetalleHCO() {
		return idBitacoraRelcomprasDetalleHCO;
	}
	public void setIdBitacoraRelcomprasDetalleHCO(
			Long idBitacoraRelcomprasDetalleHCO) {
		this.idBitacoraRelcomprasDetalleHCO = idBitacoraRelcomprasDetalleHCO;
	}	
	
	public Long getIdBitacoraRelcomprasHCO() {
		return idBitacoraRelcomprasHCO;
	}
	public void setIdBitacoraRelcomprasHCO(Long idBitacoraRelcomprasHCO) {
		this.idBitacoraRelcomprasHCO = idBitacoraRelcomprasHCO;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	
}
