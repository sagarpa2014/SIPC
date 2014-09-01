package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operadores_bodegas_v")
public class OperadoresBodegasV {
	
	
	private long id;
	private String ciclo;
	private String claveBodega;
	private String rfcOperador;
	private String nombre;
		
	@Id
	@Column(name =  "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name =  "clave_bodega")	
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	@Column(name =  "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name =  "rfc_operador")
	public String getRfcOperador() {
		return rfcOperador;
	}
	public void setRfcOperador(String rfcOperador) {
		this.rfcOperador = rfcOperador;
	}
	
	@Column(name =  "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
