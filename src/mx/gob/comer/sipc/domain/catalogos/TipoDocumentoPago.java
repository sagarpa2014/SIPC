package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_doc_pago")
public class TipoDocumentoPago {

	private Long idTipoDocumentoPago;
	private String tipoPago;
	
	@Id	
	@GeneratedValue(generator = "tipo_documento_pago_id_tipo_documento_pago_seq")
	@SequenceGenerator(name = "tipo_documento_pago_id_tipo_documento_pago_seq", sequenceName = "tipo_documento_pago_id_tipo_documento_pago_seq")
	@Column(name =  "id_tipo_doc_pago")
	public Long getIdTipoDocumentoPago() {
		return idTipoDocumentoPago;
	}
	public void setIdTipoDocumentoPago(Long idTipoDocumentoPago) {
		this.idTipoDocumentoPago = idTipoDocumentoPago;
	}
	
	@Column(name =  "tipo_pago")
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}	
}
