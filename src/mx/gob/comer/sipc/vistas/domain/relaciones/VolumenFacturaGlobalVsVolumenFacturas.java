package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VolumenFacturaGlobalVsVolumenFacturas {	
		
	@Id
	@Column(name = "id")
	private Integer id;	
	@Column(name =  "numero_fac_global")
	private String numeroFacGlobal;
	@Column(name =  "volumen_global")
	private Double volumenGlobal;
	@Column(name =  "volumen_facturas")
	private Double volumenFacturas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroFacGlobal() {
		return numeroFacGlobal;
	}
	public void setNumeroFacGlobal(String numeroFacGlobal) {
		this.numeroFacGlobal = numeroFacGlobal;
	}
	public Double getVolumenGlobal() {
		return volumenGlobal;
	}
	public void setVolumenGlobal(Double volumenGlobal) {
		this.volumenGlobal = volumenGlobal;
	}
	public Double getVolumenFacturas() {
		return volumenFacturas;
	}
	public void setVolumenFacturas(Double volumenFacturas) {
		this.volumenFacturas = volumenFacturas;
	}	
}
