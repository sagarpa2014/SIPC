package mx.gob.comer.sipc.vistas.domain.relaciones;




public class RelacionVentasV extends RelacionVentas{
	
	private String cliente;
	private String direccionCliente;
	private String destino;
	private String desUsoGrano;
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getDesUsoGrano() {
		return desUsoGrano;
	}
	public void setDesUsoGrano(String desUsoGrano) {
		this.desUsoGrano = desUsoGrano;
	}
	
}
