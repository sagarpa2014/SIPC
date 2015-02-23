package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class BoletasDuplicadas implements Comparable{	
		
	@Id
	@Column(name =  "id")
	private String id;		
	@Column(name =  "clave_bodega")
	private String claveBodega;	
	@Column(name =  "nombre_estado")
	private String nombreEstado;
	@Column(name =  "folio_contrato")
	private String folioContrato;	
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "boleta_ticket_bascula")
	private String boletaTicketBascula;
	@Column(name =  "vol_bol_ticket")
	private Double volBolTicket;
	@Column(name =  "fecha_entrada_boleta")
	private Date fechaEntradaBoleta ;

	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
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
		
	public Double getVolBolTicket() {
		return volBolTicket;
	}
	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
	}
	
	public Date getFechaEntradaBoleta() {
		return fechaEntradaBoleta;
	}
	public void setFechaEntradaBoleta(Date fechaEntradaBoleta) {
		this.fechaEntradaBoleta = fechaEntradaBoleta;
	}
		
	@Override
	public int compareTo(Object o) {
		BoletasDuplicadas obj= (BoletasDuplicadas) o;     
    	//if(this.folioContrato.compareToIgnoreCase(obj.folioContrato) == 0){
	    if(this.boletaTicketBascula.compareToIgnoreCase(obj.boletaTicketBascula) == 0) { 
	    	if(this.paternoProductor.compareToIgnoreCase(obj.paternoProductor) == 0){
		    	if(this.maternoProductor.compareToIgnoreCase(obj.maternoProductor) == 0){
		    		if(this.nombreProductor.compareToIgnoreCase(obj.nombreProductor) == 0) {
		            	if(this.folioContrato.compareToIgnoreCase(obj.folioContrato) == 0) {
		            		  return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
		            	}else{
		            		return this.folioContrato.compareTo(obj.folioContrato);
		            	}	                 
		            } else { 
		                return this.nombreProductor.compareToIgnoreCase(obj.nombreProductor); 
		            }
		    	}else{
		    		return this.maternoProductor.compareToIgnoreCase(obj.maternoProductor);
		    	} 
	    	}else{
	    		return this.paternoProductor.compareToIgnoreCase(obj.paternoProductor);
	    	}
	    } else {
	        return this.boletaTicketBascula.compareToIgnoreCase(obj.boletaTicketBascula); 
	    } 
	}

}
