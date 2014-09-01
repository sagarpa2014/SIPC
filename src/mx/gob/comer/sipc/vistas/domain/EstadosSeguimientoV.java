package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_seguimiento_v")
public class EstadosSeguimientoV {
	
	private long idEstado;
	private String nombreEstado;
	
	@Id
	@Column(name =  "id_estado_bodega")
	public long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name =  "nombre_estado_bodega")
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}	
}
