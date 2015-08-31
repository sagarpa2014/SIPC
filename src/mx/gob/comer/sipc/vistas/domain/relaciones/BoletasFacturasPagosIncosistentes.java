package mx.gob.comer.sipc.vistas.domain.relaciones;

public class BoletasFacturasPagosIncosistentes extends ReporteRelacionCompras {

	private Double volTotalFacturado;
	private Double volBolTicket;
	private Double volTotalFacVenta;
	private Double volEnpagos;
	private Double volumenNoProcedente;
	private Double diferenciaEntreVolumen;
	private Double diferenciaEntreImportes;
	private Double diferenciaEntreRFC;
	private Double predioNoPagado;	
	private Double volumenObservado;
	
	public Double getVolTotalFacturado() {
		return volTotalFacturado;
	}

	public void setVolTotalFacturado(Double volTotalFacturado) {
		this.volTotalFacturado = volTotalFacturado;
	}

	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}

	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}	

	public Double getVolEnpagos() {
		return volEnpagos;
	}

	public void setVolEnpagos(Double volEnpagos) {
		this.volEnpagos = volEnpagos;
	}

	public Double getVolumenNoProcedente() {
		return volumenNoProcedente;
	}

	public void setVolumenNoProcedente(Double volumenNoProcedente) {
		this.volumenNoProcedente = volumenNoProcedente;
	}

	public Double getVolBolTicket() {
		return volBolTicket;
	}

	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
	}

	public Double getDiferenciaEntreVolumen() {
		return diferenciaEntreVolumen;
	}

	public void setDiferenciaEntreVolumen(Double diferenciaEntreVolumen) {
		this.diferenciaEntreVolumen = diferenciaEntreVolumen;
	}

	public Double getDiferenciaEntreImportes() {
		return diferenciaEntreImportes;
	}

	public void setDiferenciaEntreImportes(Double diferenciaEntreImportes) {
		this.diferenciaEntreImportes = diferenciaEntreImportes;
	}
	
	public Double getDiferenciaEntreRFC() {
		return diferenciaEntreRFC;
	}

	public void setDiferenciaEntreRFC(Double diferenciaEntreRFC) {
		this.diferenciaEntreRFC = diferenciaEntreRFC;
	}

	public Double getVolumenObservado() {
		return volumenObservado;
	}

	public void setVolumenObservado(Double volumenObservado) {
		this.volumenObservado = volumenObservado;
	}

	public Double getPredioNoPagado() {
		return predioNoPagado;
	}

	public void setPredioNoPagado(Double predioNoPagado) {
		this.predioNoPagado = predioNoPagado;
	}
	
}
