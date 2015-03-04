package mx.gob.comer.sipc.vistas.domain.relaciones;

import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;

public class PrecioPagPorTipoCambio extends RelacionComprasTMP{
		

	private Double importeContrato;
	private Double diferenciaImporte;
	private Double precioPactadoPorTonelada;
	
	

	public Double getImporteContrato() {
		return importeContrato;
	}
	public void setImporteContrato(Double importeContrato) {
		this.importeContrato = importeContrato;
	}
	public Double getDiferenciaImporte() {
		return diferenciaImporte;
	}
	public void setDiferenciaImporte(Double diferenciaImporte) {
		this.diferenciaImporte = diferenciaImporte;
	}
	public Double getPrecioPactadoPorTonelada() {
		return precioPactadoPorTonelada;
	}
	public void setPrecioPactadoPorTonelada(Double precioPactadoPorTonelada) {
		this.precioPactadoPorTonelada = precioPactadoPorTonelada;
	}
	

}
