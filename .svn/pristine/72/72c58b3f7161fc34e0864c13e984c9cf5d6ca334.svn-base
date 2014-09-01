package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estatus_carta_adhesion")
public class EstatusCartaAdhesion {
	private int idEstatusCA;
	private String descripcionStatus;
	
	
	@Id
	@Column(name = "id_estatus_ca")
	public int getIdEstatusCA() {
		return idEstatusCA;
	}

	public void setIdEstatusCA(int idEstatusCA) {
		this.idEstatusCA = idEstatusCA;
	}		
	
	@Column(name = "descripcion")
	public String getDescripcionStatus() {
		return descripcionStatus;
	}
	
	public void setDescripcionStatus(String descripcionStatus) {
		this.descripcionStatus = descripcionStatus;
	}
	
}
