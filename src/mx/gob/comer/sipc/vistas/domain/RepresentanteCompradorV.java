package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "representante_comprador_v")
public class RepresentanteCompradorV {
	
	private Integer idRepComp;
	private Integer idComprador;
	private Integer idRepresentante;
	private String nombreComprador;
	private String nombreRepresentante;
	private String rfc;
	private String curp;
	private Integer estatusRepresentante;
	
	@Id
	@Column(name = "id_rep_comp")	
	public Integer getIdRepComp() {
		return idRepComp;
	}
	public void setIdRepComp(Integer idRepComp) {
		this.idRepComp = idRepComp;
	}
	
	@Column(name = "id_comprador")	
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name = "id_representante")
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	@Column(name = "nombre_comprador")
	public String getNombreComprador() {
		return nombreComprador;
	}
	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}
	
	@Column(name = "nombre_representante")
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}
	
	@Column(name = "rfc")
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	@Column(name = "curp")
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}

	@Column(name = "estatus")
	public Integer getEstatusRepresentante() {
		return estatusRepresentante;
	}
	public void setEstatusRepresentante(Integer estatusRepresentante) {
		this.estatusRepresentante = estatusRepresentante;
	}
	
}