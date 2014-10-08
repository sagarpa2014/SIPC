package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
*Usuarios
*
*Clase que mapea a la tabla "USUARIOS" de la base de datos  
*
*Versión 1.0
*
*Septiembre 2012
*
*/

@Entity
@Table(name = "usuarios")
public class Usuarios {
	
	private Integer idUsuario;
	private int idPerfil;
	private String nombreUsuario;
	private String contrasenia;
	private String nombre;
	private String paterno;
	private String materno;
	private Integer idArea;
	private Integer idEspecialista;
	private String area;

	
	public Usuarios(){
		
	}
	

	@Id
	@GeneratedValue(generator = "usuarios_id_usuario_seq")
	@SequenceGenerator(name = "usuarios_id_usuario_seq", sequenceName = "usuarios_id_usuario_seq")
	@Column(name = "id_usuario")
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}	
	
	@Column(name = "id_perfil")
	public int getIdPerfil() {
		return idPerfil;
	}
	
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	@Column(name = "nombre_usuario")
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "contrasenia")
	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "paterno")
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	
	@Column(name = "materno")
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}

	@Column(name = "id_area")
	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}


	@Column(name = "id_especialista")
	public Integer getIdEspecialista() {
		return idEspecialista;
	}
	
	public void setIdEspecialista(Integer idEspecialista) {
		this.idEspecialista = idEspecialista;
	}


	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}	 
	  
	
	
	
}
