package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estatus_oficios")
public class EstatusOficio {
	private int estatusId;
	private String descripcionStatus;
	
	@Id
	@Column(name = "estatus_id")
	public int getEstatusId() {
		return estatusId;
	}
	public void setEstatusId(int estatusId) {
		this.estatusId = estatusId;
	}	
	
	@Column(name = "status")
	public String getDescripcionStatus() {
		return descripcionStatus;
	}
	
	public void setDescripcionStatus(String descripcionStatus) {
		this.descripcionStatus = descripcionStatus;
	}
}
