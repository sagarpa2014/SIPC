package mx.gob.comer.sipc.vistas.domain.relaciones;


public class RendimientosProcedente extends ReporteRelacionCompras {
	
	private Double volTotalFacturado;
	private Double rendimientoMaximoAceptable;
	private String volNoProcedente;	// AHS [LINEA] - 17022015
	
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

	public String getVolNoProcedente() {	// AHS [LINEA] - 17022015
		return volNoProcedente;
	}

	public void setVolNoProcedente(String volNoProcedente) {	// AHS [LINEA] - 17022015
		this.volNoProcedente = volNoProcedente;
	}
	
}
