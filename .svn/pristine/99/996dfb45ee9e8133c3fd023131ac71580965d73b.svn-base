package mx.gob.comer.sipc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "estados")
public class Estado implements Serializable{
	
	/**
	 * 
	 */
	private int idEstado;
	private String nombre;
	private String clave;
	private int regionalId;
	private String ciudad;
	private String direccion;
	
	  
	@Id
	@GeneratedValue(generator = "estados_id_estado_seq")
	@SequenceGenerator(name = "estados_id_estado_seq", sequenceName = "estados_id_estado_seq")
	@Column(name = "id_estado")
	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "clave")
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	@Column(name = "regional_id",updatable=false,insertable=false)
	public int getRegionalId() {
		return regionalId;
	}
	
	public void setRegionalId(int regionalId) {
		this.regionalId = regionalId;
	}
	
	@Column(name = "ciudad")
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	@Column(name = "direccion")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
