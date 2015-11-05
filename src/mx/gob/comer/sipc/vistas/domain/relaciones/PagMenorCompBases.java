package mx.gob.comer.sipc.vistas.domain.relaciones;

import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;

public class PagMenorCompBases extends RelacionComprasTMP{
	
	private Double cuota;
	private Double compBaseMenorTotal;
	private Double pagoMenorCuota;	
	private Double pagoMenor;
		
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
		
	public Double getCompBaseMenorTotal() {
		return compBaseMenorTotal;
	}
	public void setCompBaseMenorTotal(Double compBaseMenorTotal) {
		this.compBaseMenorTotal = compBaseMenorTotal;
	}
	public Double getPagoMenorCuota() {
		return pagoMenorCuota;
	}
	public void setPagoMenorCuota(Double pagoMenorCuota) {
		this.pagoMenorCuota = pagoMenorCuota;
	}
	public Double getPagoMenor() {
		return pagoMenor;
	}
	public void setPagoMenor(Double pagoMenor) {
		this.pagoMenor = pagoMenor;
	}
		
}
