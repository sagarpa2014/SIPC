package mx.gob.comer.sipc.vistas.domain.relaciones;


public class RendimientosProcedente extends ReporteRelacionCompras {
	
	private Double volTotalFacturado;
	private Double rendimientoMaximoAceptable;
	private Double volNoProcedente;
	
	public Double getVolTotalFacturado() {
		return volTotalFacturado;
	}

	public void setVolTotalFacturado(Double volTotalFacturado) {
		this.volTotalFacturado = volTotalFacturado;
	}

	public Double getRendimientoMaximoAceptable() {
		return rendimientoMaximoAceptable;
	}

	public void setRendimientoMaximoAceptable(Double rendimientoMaximoAceptable) {
		this.rendimientoMaximoAceptable = rendimientoMaximoAceptable;
	}

	public Double getVolNoProcedente() {
		return volNoProcedente;
	}

	public void setVolNoProcedente(Double volNoProcedente) {
		this.volNoProcedente = volNoProcedente;
	}
	
}
