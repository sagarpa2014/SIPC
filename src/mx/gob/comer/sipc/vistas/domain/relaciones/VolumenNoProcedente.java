package mx.gob.comer.sipc.vistas.domain.relaciones;


public class VolumenNoProcedente extends ReporteRelacionCompras {
	
	private Double volTotalFacturado;
	private Double volApoyadoEnRegional;
	private Double volNoProcedente;	
	private Integer idVariedad;
	
	public Double getVolTotalFacturado() {
		return volTotalFacturado;
	}

	public void setVolTotalFacturado(Double volTotalFacturado) {
		this.volTotalFacturado = volTotalFacturado;
	}
	
	public Double getVolApoyadoEnRegional() {
		return volApoyadoEnRegional;
	}

	public void setVolApoyadoEnRegional(Double volApoyadoEnRegional) {
		this.volApoyadoEnRegional = volApoyadoEnRegional;
	}

	public Double getVolNoProcedente() {
		return volNoProcedente;
	}

	public void setVolNoProcedente(Double volNoProcedente) {
		this.volNoProcedente = volNoProcedente;
	}

	public Integer getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	

	
	
}
