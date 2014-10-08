package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caders")
public class Cader {

	@Id
	private long idCader;
	@Column(name = "estado")
	private Integer idEstado;
	@Column(name = "ddr")
	private Integer ddr;
	@Column(name = "cader")
	private Integer cader;
	@Column(name = "nombre")
	private String nombre;	
	
	public long getIdCader() {
		return idCader;
	}
	public void setIdCader(long idCader) {
		this.idCader = idCader;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getDdr() {
		return ddr;
	}
	public void setDdr(Integer ddr) {
		this.ddr = ddr;
	}
	public Integer getCader() {
		return cader;
	}
	public void setCader(Integer cader) {
		this.cader = cader;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
