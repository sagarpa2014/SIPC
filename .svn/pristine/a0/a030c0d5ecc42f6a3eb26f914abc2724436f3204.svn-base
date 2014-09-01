package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_comprador_v")
public class ProgramaCompradorV{
	
	private int id;
	private int idPrograma;
	private int idComprador;
	private String nombre;
	
	@Id
	@Column(name =  "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name =  "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_comprador")
	public int getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name =  "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
