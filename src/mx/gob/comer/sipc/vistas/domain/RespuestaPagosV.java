package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "respuesta_pagos_v")
public class RespuestaPagosV {
	
	
	private long idOficioPagos;
	private String noOficio;
	private Integer totalPagos;
	private Double totalImporte;
	private Double totalVolumen;
	private Date fechaOficio;
	private Integer folioClc;
	private String archivoEnvio;
	private Integer criterioPago;
	private String etapa;
	private Integer aplicados;
	private Double totalImporteAplicados;
	private Double totalVolumenAplicados;
	private Integer rechazados;
	private Double totalImporteRechazados;
	private Double totalVolumenRechazados;
	private Integer idPrograma;
	private String descripcionCorta;
	private String ciclo;
	private String cicloCorto;

	@Id
	@Column(name =  "id_oficio_pagos")
	public long getIdOficioPagos() {
		return idOficioPagos;
	}
	public void setIdOficioPagos(long idOficioPagos) {
		this.idOficioPagos = idOficioPagos;
	}
	@Column(name =  "no_oficio")
	public String getNoOficio() {
		return noOficio;
	}
	public void setNoOficio(String noOficio) {
		this.noOficio = noOficio;
	}
	
	@Column(name =  "total_pagos")
	public Integer getTotalPagos() {
		return totalPagos;
	}
	public void setTotalPagos(Integer totalPagos) {
		this.totalPagos = totalPagos;
	}
	
	@Column(name =  "total_importe")
	public Double getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(Double totalImporte) {
		this.totalImporte = totalImporte;
	}
	
	@Column(name =  "total_volumen")
	public Double getTotalVolumen() {
		return totalVolumen;
	}
	public void setTotalVolumen(Double totalVolumen) {
		this.totalVolumen = totalVolumen;
	}
	
	@Column(name =  "fecha")
	public Date getFechaOficio() {
		return fechaOficio;
	}
	public void setFechaOficio(Date fechaOficio) {
		this.fechaOficio = fechaOficio;
	}
	
	@Column(name =  "folio_clc")
	public Integer getFolioClc() {
		return folioClc;
	}
	public void setFolioClc(Integer folioClc) {
		this.folioClc = folioClc;
	}
	
	@Column(name =  "archivo_envio")
	public String getArchivoEnvio() {
		return archivoEnvio;
	}
	public void setArchivoEnvio(String archivoEnvio) {
		this.archivoEnvio = archivoEnvio;
	}
	
	@Column(name =  "criterio_pago")
	public Integer getCriterioPago() {
		return criterioPago;
	}
	public void setCriterioPago(Integer criterioPago) {
		this.criterioPago = criterioPago;
	}
	
	@Column(name =  "etapa")
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	@Column(name =  "aplicados")
	public Integer getAplicados() {
		return aplicados;
	}
	public void setAplicados(Integer aplicados) {
		this.aplicados = aplicados;
	}

	@Column(name =  "total_importe_aplicados")
	public Double getTotalImporteAplicados() {
		return totalImporteAplicados;
	}
	public void setTotalImporteAplicados(Double totalImporteAplicados) {
		this.totalImporteAplicados = totalImporteAplicados;
	}
	
	@Column(name =  "total_volumen_aplicados")
	public Double getTotalVolumenAplicados() {
		return totalVolumenAplicados;
	}
	public void setTotalVolumenAplicados(Double totalVolumenAplicados) {
		this.totalVolumenAplicados = totalVolumenAplicados;
	}
	
	@Column(name =  "rechazados")
	public Integer getRechazados() {
		return rechazados;
	}
	public void setRechazados(Integer rechazados) {
		this.rechazados = rechazados;
	}
	
	@Column(name =  "total_importe_rechazados")
	public Double getTotalImporteRechazados() {
		return totalImporteRechazados;
	}
	public void setTotalImporteRechazados(Double totalImporteRechazados) {
		this.totalImporteRechazados = totalImporteRechazados;
	}
	
	@Column(name =  "total_volumen_rechazados")
	public Double getTotalVolumenRechazados() {
		return totalVolumenRechazados;
	}
	public void setTotalVolumenRechazados(Double totalVolumenRechazados) {
		this.totalVolumenRechazados = totalVolumenRechazados;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "descripcion_corta")
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	@Column(name =  "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name =  "ciclo_corto")
	public String getCicloCorto() {
		return cicloCorto;
	}
	public void setCicloCorto(String cicloCorto) {
		this.cicloCorto = cicloCorto;
	}
}
