package mx.gob.comer.sipc.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
*Perfiles
*
*Clase que mapea a la tabla "PERFILES" de la base de datos  
*
*Versión 1.0
*
*Septiembre 2012
*
*/
@Entity
@Table(name = "perfiles")
public class Perfiles {
	
	private int idPerfil;
	private String perfil;
	private String descripcion;
	
	
	public Perfiles(){}	
	
	public Perfiles(Perfiles perfil) {
		super();
		this.idPerfil = perfil.getIdPerfil();
		this.perfil = perfil.getPerfil();
		this.descripcion = perfil.getDescripcion();		
	}
	@Id
	@Column(name = "id_perfil")	
	public int getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	@Column(name = "perfil")
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Perfiles [idPerfil=" + idPerfil + ", perfil=" + perfil
				+ ", descripcion=" + descripcion + "]";
	}
	
	
	
}
