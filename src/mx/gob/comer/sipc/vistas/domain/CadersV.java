package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caders_v")
public class CadersV {

	@Id
	@Column(name = "id_cader")	
	private long idCader;
	@Column(name = "cader")
	private Integer cader;	
	@Column(name = "nombre_cader")
	private String nombreCader;
	@Column(name = "ddr")
	private Long ddr;
	@Column(name = "nombre_ddr")
	private String nombreDDR;
	@Column(name = "id_estado")
	private Integer idEstado;
	@Column(name = "nombre_estado")
	private String nombreEstado;	
	
	public long getIdCader() {
		return idCader;
	}
	public void setIdCader(long idCader) {
		this.idCader = idCader;
	}
		
	public Integer getCader() {
		return cader;
	}
	public void setCader(Integer cader) {
		this.cader = cader;
	}
	public String getNombreCader() {
		return nombreCader;
	}
	public void setNombreCader(String nombreCader) {
		this.nombreCader = nombreCader;
	}
	public Long getDdr() {
		return ddr;
	}
	public void setDdr(Long ddr) {
		this.ddr = ddr;
	}
	public String getNombreDDR() {
		return nombreDDR;
	}
	public void setNombreDDR(String nombreDDR) {
		this.nombreDDR = nombreDDR;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
}
