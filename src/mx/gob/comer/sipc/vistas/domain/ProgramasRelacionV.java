package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programas_relacion_v")
public class ProgramasRelacionV {

	private Long idPrograma;
	private String descripcionCorta;
	private String cicloAgricola;
	private String producto;
	private Date fechaRegistro;
	private Date fechaActualiza;
	private Integer numRelaciones;
		
	@Id	
	@Column(name =  "id_programa")		
	public Long getIdPrograma() {
		return idPrograma;
	}
	
	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "descripcion_corta")	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	@Column(name =  "ciclo")	
	public String getCicloAgricola() {
		return cicloAgricola;
	}
	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}
	
	@Column(name =  "producto")
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	

	@Column(name =  "fecha_registro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name =  "fecha_actualiza")
	public Date getFechaActualiza() {
		return fechaActualiza;
	}
	public void setFechaActualiza(Date fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}
	
	@Column(name =  "num_relaciones")
	public Integer getNumRelaciones() {
		return numRelaciones;
	}
	
	public void setNumRelaciones(Integer numRelaciones) {
		this.numRelaciones = numRelaciones;
	}
}
