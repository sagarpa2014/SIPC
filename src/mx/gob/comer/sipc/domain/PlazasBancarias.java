package mx.gob.comer.sipc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plazas_bancarias")
public class PlazasBancarias {
	
	private String numeroPlaza;
	private String nombrePlaza;
	private String entidadPlaza;
	private int estadoId;
	
	@Id
	@Column(name = "numero_plaza")
	public String getNumeroPlaza() {
		return numeroPlaza;
	}
	public void setNumeroPlaza(String numeroPlaza) {
		this.numeroPlaza = numeroPlaza;
	}
	
	@Column(name = "nombre_plaza")
	public String getNombrePlaza() {
		return nombrePlaza;
	}
	public void setNombrePlaza(String nombrePlaza) {
		this.nombrePlaza = nombrePlaza;
	}
	
	@Column(name = "entidad_plaza")
	public String getEntidadPlaza() {
		return entidadPlaza;
	}
	public void setEntidadPlaza(String entidadPlaza) {
		this.entidadPlaza = entidadPlaza;
	}
	
	@Column(name = "estado_id")
	public int getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}
}