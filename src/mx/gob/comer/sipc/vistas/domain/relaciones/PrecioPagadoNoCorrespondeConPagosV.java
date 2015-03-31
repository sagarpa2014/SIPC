package mx.gob.comer.sipc.vistas.domain.relaciones;


import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;


public class PrecioPagadoNoCorrespondeConPagosV extends RelacionComprasTMP {	

	private Double precioCalculado;
	private Double precioPagadoEnAviso;

	public Double getPrecioCalculado() {
		return precioCalculado;
	}

	public void setPrecioCalculado(Double precioCalculado) {
		this.precioCalculado = precioCalculado;
	}

	public Double getPrecioPagadoEnAviso() {
		return precioPagadoEnAviso;
	}

	public void setPrecioPagadoEnAviso(Double precioPagadoEnAviso) {
		this.precioPagadoEnAviso = precioPagadoEnAviso;
	}
	
}
