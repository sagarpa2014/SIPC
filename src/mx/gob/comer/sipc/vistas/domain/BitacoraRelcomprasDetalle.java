package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
*Pagos
*
*Clase que mapea a la tabla "bitacora_relcompras_detalle" de la base de datos  
*
*Versión 1.0
*
*Octubre 2014
*
*/

@Entity
@Table(name="bitacora_relcompras_detalle")
public class BitacoraRelcomprasDetalle {

	@Id
	@GeneratedValue(generator = "bitacora_relcompras_detalle_id_bitacora_relcompras_detalle_seq")
	@SequenceGenerator(name = "bitacora_relcompras_detalle_id_bitacora_relcompras_detalle_seq", sequenceName = "bitacora_relcompras_detalle_id_bitacora_relcompras_detalle_seq")
	@Column(name = "id_bitacora_relcompras_detalle")
	private Long idBitacoraRelcomprasDetalle;
	@Column(name = "id_bitacora_relcompras", updatable=false, insertable=false)
	private Long idBitacoraRelcompras;
	@Column(name = "mensaje")
	private String mensaje;
	
	
	public Long getIdBitacoraRelcomprasDetalle() {
		return idBitacoraRelcomprasDetalle;
	}
	public void setIdBitacoraRelcomprasDetalle(Long idBitacoraRelcomprasDetalle) {
		this.idBitacoraRelcomprasDetalle = idBitacoraRelcomprasDetalle;
	}
	public Long getIdBitacoraRelcompras() {
		return idBitacoraRelcompras;
	}
	public void setIdBitacoraRelcompras(Long idBitacoraRelcompras) {
		this.idBitacoraRelcompras = idBitacoraRelcompras;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	
}
