package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class ChequeFueraPeriodoPago implements Comparable{	
	@Id
	@Column(name =  "id")
	private Long id;
	@Column(name =  "productor")
	private Long folioProductor;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "estado_acopio")
	private Integer estadoAcopio;
	@Column(name =  "nombre_estado")
	private String nombreEstado;
	@Column(name =  "folio_doc_pago")
	private String folioDocPago;
	@Column(name =  "banco_sinaxc")
	private String bancoSinaxc;	
	@Column(name =  "folio_contrato")
	private String  folioContrato;
	@Column(name =  "fecha_doc_pago_sinaxc")
	private Date fechaDocPagoSinaxc;
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
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public Integer getEstadoAcopio() {
		return estadoAcopio;
	}
	public void setEstadoAcopio(Integer estadoAcopio) {
		this.estadoAcopio = estadoAcopio;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}	
	
	public String getFolioDocPago() {
		return folioDocPago;
	}
	public void setFolioDocPago(String folioDocPago) {
		this.folioDocPago = folioDocPago;
	}		
	
	public String getBancoSinaxc() {
		return bancoSinaxc;
	}
	public void setBancoSinaxc(String bancoSinaxc) {
		this.bancoSinaxc = bancoSinaxc;
	}
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
	public Date getFechaDocPagoSinaxc() {
		return fechaDocPagoSinaxc;
	}
	public void setFechaDocPagoSinaxc(Date fechaDocPagoSinaxc) {
		this.fechaDocPagoSinaxc = fechaDocPagoSinaxc;
	}
	public Long getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(Long folioProductor) {
		this.folioProductor = folioProductor;
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
	@Override
	public int compareTo(Object o) {
		ChequeFueraPeriodoPago obj= (ChequeFueraPeriodoPago) o;     
	    if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(obj.nombreEstado) == 0){
		    	if(this.folioContrato.compareToIgnoreCase(obj.folioContrato) == 0){
		    		if(this.paternoProductor.compareToIgnoreCase(obj.paternoProductor) == 0) {
		            	if(this.maternoProductor.compareToIgnoreCase(obj.maternoProductor) == 0) {
		            		  return this.nombreProductor.compareToIgnoreCase(obj.nombreProductor); 
		            	}else{
		            		return this.maternoProductor.compareTo(obj.maternoProductor);
		            	}	                 
		            } else { 
		                return this.paternoProductor.compareToIgnoreCase(obj.paternoProductor); 
		            }
		    	}else{
		    		return this.folioContrato.compareToIgnoreCase(obj.folioContrato);
		    	} 
	    	}else{
	    		return this.nombreEstado.compareToIgnoreCase(obj.nombreEstado);
	    	}
	    } else {
	            return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
	    }    		
	}
}
