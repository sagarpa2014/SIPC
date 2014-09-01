package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bancos")
public class Bancos {

	private int bancoId;
	private String nombre;
	private String contacto;
	private String email;
	private String cargo;

	@Id
	@Column(name = "banco_id")
	public int getBancoId() {
		return bancoId;
	}
	public void setBancoId(int bancoId) {
		this.bancoId = bancoId;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "contacto")
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	@Column(name = "cargo")
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
