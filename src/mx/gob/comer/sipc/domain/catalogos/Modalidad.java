package mx.gob.comer.sipc.domain.catalogos;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modalidad_relacion")
public class Modalidad{
	private Long 		idModalidad;
	private  String 	modalidad;
	
	
	@Id
	@Column(name = "id_modalidad")
	public Long getIdModalidad() {
		return idModalidad;
	}
	public void setIdModalidad(Long idModalidad) {
		this.idModalidad = idModalidad;
	}
	
	@Column(name = "modalidad")
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}	
}
