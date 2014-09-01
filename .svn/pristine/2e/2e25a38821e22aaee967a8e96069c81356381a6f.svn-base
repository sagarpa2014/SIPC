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
@Table(name = "localidades")

public class Localidades implements Serializable{
	
	private int idLocalidad;
	private int idEstado;
	private int claveMunicipio;
	private int claveLocalidad;
	private String nombreLocalidad;

	
	@Id
	@GeneratedValue(generator = "estados_id_localidad_seq")
	@SequenceGenerator(name = "estados_id_localidad_seq", sequenceName = "estados_id_localidad_seq")
	@Column(name = "id_localidad")
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	@Column(name = "id_estado")
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	@Column(name = "clave_municipio")
	public int getClaveMunicipio() {
		return claveMunicipio;
	}
	public void setClaveMunicipio(int claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}
	@Column(name = "clave_localidad")
	public int getClaveLocalidad() {
		return claveLocalidad;
	}
	public void setClaveLocalidad(int claveLocalidad) {
		this.claveLocalidad = claveLocalidad;
	}
	@Column(name = "nombre_localidad")
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	
}
