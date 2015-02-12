package mx.gob.comer.sipc.vistas.domain.relaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("rawtypes")
@Entity
public class ProductoresIncosistentes implements Comparable {	
		
	@Id
	@Column(name =  "id")
	private Long id;
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
	@Column(name =  "folio_predio")
	private Integer folioPredio;
	@Column(name =  "predio_alterno")
	private Integer predioAlterno;
	@Column(name =  "numero_boletas")
	private Integer numeroBoletas;
	@Column(name =  "numero_facturas")
	private Integer numeroFacturas;
	@Column(name =  "numero_pagos")
	private Integer numeroPagos;
	
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
	
	public Integer getFolioPredio() {
		return folioPredio;
	}
	public void setFolioPredio(Integer folioPredio) {
		this.folioPredio = folioPredio;
	}
	public Integer getPredioAlterno() {
		return predioAlterno;
	}
	public void setPredioAlterno(Integer predioAlterno) {
		this.predioAlterno = predioAlterno;
	}
	public Integer getNumeroBoletas() {
		return numeroBoletas;
	}
	public void setNumeroBoletas(Integer numeroBoletas) {
		this.numeroBoletas = numeroBoletas;
	}
	public Integer getNumeroFacturas() {
		return numeroFacturas;
	}
	public void setNumeroFacturas(Integer numeroFacturas) {
		this.numeroFacturas = numeroFacturas;
	}
	public Integer getNumeroPagos() {
		return numeroPagos;
	}
	public void setNumeroPagos(Integer numeroPagos) {
		this.numeroPagos = numeroPagos;
	}
	@Override
	public int compareTo(Object o) {
		ProductoresIncosistentes obj= (ProductoresIncosistentes) o;     
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
