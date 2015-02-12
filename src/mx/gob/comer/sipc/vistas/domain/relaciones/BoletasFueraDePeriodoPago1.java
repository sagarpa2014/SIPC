package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class BoletasFueraDePeriodoPago1  {	
		
	@Id
	@Column(name =  "id")
	private Long id;
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "nombre_estado")
	private String nombreEstado;	
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "boleta_ticket_bascula")
	private String boletaTicketBascula;	
	@Column(name =  "fecha_entrada_boleta")
	private Date fechaEntradaBoleta;
	@Column(name =  "folio_contrato")
	private String folioContrato;
	@Column(name =  "periodo_inicial_pago")
	private Date periodoInicialPago;
	@Column(name =  "periodo_final_pago")
	private Date periodoFinalPago;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	public String getPaternoProductor() {
		return paternoProductor;
	}
	public void setPaternoProductor(String paternoProductor) {
		this.paternoProductor = paternoProductor;
	}
	public String getMaternoProductor() {
		return maternoProductor;
	}
	public void setMaternoProductor(String maternoProductor) {
		this.maternoProductor = maternoProductor;
	}
	public String getNombreProductor() {
		return nombreProductor;
	}
	public void setNombreProductor(String nombreProductor) {
		this.nombreProductor = nombreProductor;
	}
	public String getBoletaTicketBascula() {
		return boletaTicketBascula;
	}
	public void setBoletaTicketBascula(String boletaTicketBascula) {
		this.boletaTicketBascula = boletaTicketBascula;
	}
	public Date getFechaEntradaBoleta() {
		return fechaEntradaBoleta;
	}
	public void setFechaEntradaBoleta(Date fechaEntradaBoleta) {
		this.fechaEntradaBoleta = fechaEntradaBoleta;
	}	
	
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
	public Date getPeriodoInicialPago() {
		return periodoInicialPago;
	}
	public void setPeriodoInicialPago(Date periodoInicialPago) {
		this.periodoInicialPago = periodoInicialPago;
	}
	public Date getPeriodoFinalPago() {
		return periodoFinalPago;
	}
	public void setPeriodoFinalPago(Date periodoFinalPago) {
		this.periodoFinalPago = periodoFinalPago;
	}
//	@Override
//	public int compareTo(Object o) {
//		BoletasFueraDePeriodoPago boleta = (BoletasFueraDePeriodoPago) o;     
//	    if(this.claveBodega.compareToIgnoreCase(boleta.claveBodega) == 0) { 
//	    	if(this.nombreEstado.compareToIgnoreCase(boleta.nombreEstado) == 0){
//	    		if(this.paternoProductor.compareToIgnoreCase(boleta.paternoProductor) == 0) {
//	            	if(this.maternoProductor.compareToIgnoreCase(boleta.maternoProductor) == 0) {
//	            		  return this.nombreProductor.compareToIgnoreCase(boleta.nombreProductor); 
//	            	}else{
//	            		return this.maternoProductor.compareTo(boleta.maternoProductor);
//	            	}	                 
//	            } else { 
//	                return this.paternoProductor.compareToIgnoreCase(boleta.paternoProductor); 
//	            }
//	    	}else{
//	    		return this.nombreEstado.compareToIgnoreCase(boleta.nombreEstado);
//	    	}             
//	    } else {
//	            return this.claveBodega.compareToIgnoreCase(boleta.claveBodega); 
//	    }    		
//	}
}
