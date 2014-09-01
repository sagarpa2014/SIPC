package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "asignacion_ca_especialista")
public class AsignacionCartaEspecialista {	
	
	private long idAsigCAEspecialista;
	private String  idOficioCASP; 
	private String  folioCartaAdhesion;
	private Integer idEspecialista;
	
	@Id	
	@Column(name =  "id_asig_ca_especialista")
	@GeneratedValue(generator = "asignacion_ca_especialista_id_asig_ca_especialista_seq")
	@SequenceGenerator(name = "asignacion_ca_especialista_id_asig_ca_especialista_seq", sequenceName = "oficio_ca_solicitud_pago_id_oficio_ca_sp_seq")	
	public long getIdAsigCAEspecialista() {
		return idAsigCAEspecialista;
	}
	public void setIdAsigCAEspecialista(long idAsigCAEspecialista) {
		this.idAsigCAEspecialista = idAsigCAEspecialista;
	}
	
	@Column(name = "id_oficio_ca_sp", updatable=false, insertable=false)
	public String getIdOficioCASP() {
		return idOficioCASP;
	}
	public void setIdOficioCASP(String idOficioCASP) {
		this.idOficioCASP = idOficioCASP;
	}
	
	@Column(name =  "folio_carta_adhesion")	
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "id_especialista")
	public Integer getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(Integer idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	

}
