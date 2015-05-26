package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class PrecioPagadoMenor implements Comparable{	
			
	@Id
	@Column(name =  "id")
	private Integer id;	
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
	@Column(name =  "peso_neto")
	private Double pesoNeto;
	@Column(name =  "importe_cheque")
	private Double importeCheque;
	@Column(name =  "importe_factura")
	private Double importeFactura;
	@Column(name =  "importe_total")
	private Double importeTotal;
	@Column(name =  "precio_en_rel_compras")
	private Double precioEnRelCompras;
	@Column(name =  "precio_calculado")
	private Double precioCalculado;
	@Column(name =  "precio_pagado")
	private Double precioPagado;
	@Column(name =  "obs")
	private String observacion;
	
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	public Double getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(Double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public Double getImporteCheque() {
		return importeCheque;
	}
	public void setImporteCheque(Double importeCheque) {
		this.importeCheque = importeCheque;
	}
	public Double getImporteFactura() {
		return importeFactura;
	}
	public void setImporteFactura(Double importeFactura) {
		this.importeFactura = importeFactura;
	}
	public Double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public Double getPrecioEnRelCompras() {
		return precioEnRelCompras;
	}
	public void setPrecioEnRelCompras(Double precioEnRelCompras) {
		this.precioEnRelCompras = precioEnRelCompras;
	}
	public Double getPrecioCalculado() {
		return precioCalculado;
	}
	public void setPrecioCalculado(Double precioCalculado) {
		this.precioCalculado = precioCalculado;
	}
	public Double getPrecioPagado() {
		return precioPagado;
	}
	public void setPrecioPagado(Double precioPagado) {
		this.precioPagado = precioPagado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	@Override
	public int compareTo(Object o) {
		PrecioPagadoMenor obj= (PrecioPagadoMenor) o;     
	    if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(obj.nombreEstado) == 0){
	    		if((this.folioContrato==null?"":this.folioContrato).compareToIgnoreCase(obj.folioContrato==null?"":obj.folioContrato) == 0){
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
