package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ejidos")
public class Ejido {

	@Id
	@Column(name = "id_ejido")
	private long idEjido;	
	@Column(name = "estado")
	private Integer idEstado;
	@Column(name = "ejido")	
	private Integer ejido;
	@Column(name = "nombre")
	private String nombreEjido;
	
	public long getIdEjido() {
		return idEjido;
	}
	public void setIdEjido(long idEjido) {
		this.idEjido = idEjido;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getEjido() {
		return ejido;
	}
	public void setEjido(Integer ejido) {
		this.ejido = ejido;
	}
	public String getNombreEjido() {
		return nombreEjido;
	}
	public void setNombreEjido(String nombreEjido) {
		this.nombreEjido = nombreEjido;
	}		
		
}
