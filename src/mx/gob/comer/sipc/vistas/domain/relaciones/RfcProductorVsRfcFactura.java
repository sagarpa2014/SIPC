package mx.gob.comer.sipc.vistas.domain.relaciones;


import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;

public class RfcProductorVsRfcFactura extends RelacionComprasTMP   {	
	
	private String curpProductorBD;	
	private String rfcProductorBD;
	private String leyenda;
	
	public String getCurpProductorBD() {
		return curpProductorBD;
	}
	public void setCurpProductorBD(String curpProductorBD) {
		this.curpProductorBD = curpProductorBD;
	}
	public String getRfcProductorBD() {
		return rfcProductorBD;
	}
	public void setRfcProductorBD(String rfcProductorBD) {
		this.rfcProductorBD = rfcProductorBD;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}	
	
}
