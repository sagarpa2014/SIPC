package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "concentrado_pagos_trimestres_v")
public class ReporteConcentradoPagosV {
	
	private long idPrograma;
	private String programa;
	private String programaLargo;
	private long anio;
	private String ciclo;
	private String producto;
	private String estados;
	private Integer criterioPago;
	private Integer numeroEtapa;
//	private Integer estatus;
	private Double volumen1erTrimestre;
	private Double importe1erTrimestre;
	private Double volumen2doTrimestre;
	private Double importe2doTrimestre;
	private Double volumen3erTrimestre;
	private Double importe3erTrimestre;
	private Double volumen4toTrimestre;
	private Double importe4toTrimestre;
	private Integer solicitudes1ertrimestre;
	private Integer solicitudes2dotrimestre;
	private Integer solicitudes3ertrimestre;
	private Integer solicitudes4totrimestre;
	private Integer productores1ertrimestre;
	private Integer productores2dotrimestre;
	private Integer productores3ertrimestre;
	private Integer productores4totrimestre;
	
	public void setIdPrograma(long idPrograma) {
		this.idPrograma = idPrograma;
	}
	@Id
	@Column(name =  "id_programa")
	public long getIdPrograma() {
		return idPrograma;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@Column(name =  "programa")
	public String getPrograma() {
		return programa;
	}
	
	@Column(name =  "programa_larga")
	public String getProgramaLargo() {
		return programaLargo;
	}
	public void setProgramaLargo(String programaLargo) {
		this.programaLargo = programaLargo;
	}
	@Column(name =  "anio")
	public long getAnio() {
		return anio;
	}
	public void setAnio(long anio) {
		this.anio = anio;
	}
	@Column(name =  "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	@Column(name =  "producto")
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	@Column(name =  "estados")
	public String getEstados() {
		return estados;
	}
	public void setEstados(String estados) {
		this.estados = estados;
	}
	@Column(name =  "volumen_1er_trimestre")
	public Double getVolumen1erTrimestre() {
		return volumen1erTrimestre;
	}
	public void setVolumen1erTrimestre(Double volumen1erTrimestre) {
		this.volumen1erTrimestre = volumen1erTrimestre;
	}
	@Column(name =  "importe_1er_trimestre")
	public Double getImporte1erTrimestre() {
		return importe1erTrimestre;
	}
	public void setImporte1erTrimestre(Double importe1erTrimestre) {
		this.importe1erTrimestre = importe1erTrimestre;
	}
	@Column(name =  "volumen_2do_trimestre")
	public Double getVolumen2doTrimestre() {
		return volumen2doTrimestre;
	}
	public void setVolumen2doTrimestre(Double volumen2doTrimestre) {
		this.volumen2doTrimestre = volumen2doTrimestre;
	}
	@Column(name =  "importe_2do_trimestre")
	public Double getImporte2doTrimestre() {
		return importe2doTrimestre;
	}
	public void setImporte2doTrimestre(Double importe2doTrimestre) {
		this.importe2doTrimestre = importe2doTrimestre;
	}
	@Column(name =  "volumen_3er_trimestre")
	public Double getVolumen3erTrimestre() {
		return volumen3erTrimestre;
	}
	public void setVolumen3erTrimestre(Double volumen3erTrimestre) {
		this.volumen3erTrimestre = volumen3erTrimestre;
	}
	@Column(name =  "importe_3er_trimestre")
	public Double getImporte3erTrimestre() {
		return importe3erTrimestre;
	}
	public void setImporte3erTrimestre(Double importe3erTrimestre) {
		this.importe3erTrimestre = importe3erTrimestre;
	}
	@Column(name =  "volumen_4to_trimestre")
	public Double getVolumen4toTrimestre() {
		return volumen4toTrimestre;
	}
	public void setVolumen4toTrimestre(Double volumen4toTrimestre) {
		this.volumen4toTrimestre = volumen4toTrimestre;
	}
	@Column(name =  "importe_4to_trimestre")
	public Double getImporte4toTrimestre() {
		return importe4toTrimestre;
	}
	public void setImporte4toTrimestre(Double importe4toTrimestre) {
		this.importe4toTrimestre = importe4toTrimestre;
	}
	@Column(name =  "criterio_pago")
	public Integer getCriterioPago() {
		return criterioPago;
	}
	public void setCriterioPago(Integer criterioPago) {
		this.criterioPago = criterioPago;
	}
	@Column(name =  "numero_etapa")
	public Integer getNumeroEtapa() {
		return numeroEtapa;
	}
	public void setNumeroEtapa(Integer numeroEtapa) {
		this.numeroEtapa = numeroEtapa;
	}
//	@Column(name =  "estatus")
//	public Integer getEstatus() {
//		return estatus;
//	}
//	public void setEstatus(Integer estatus) {
//		this.estatus = estatus;
//	}
	
	@Column(name =  "solicitudes_1er_trimestre")
	public Integer getSolicitudes1ertrimestre() {
		return solicitudes1ertrimestre;
	}
	public void setSolicitudes1ertrimestre(Integer solicitudes1ertrimestre) {
		this.solicitudes1ertrimestre = solicitudes1ertrimestre;
	}
	
	@Column(name =  "solicitudes_2do_trimestre")
	public Integer getSolicitudes2dotrimestre() {
		return solicitudes2dotrimestre;
	}
	public void setSolicitudes2dotrimestre(Integer solicitudes2dotrimestre) {
		this.solicitudes2dotrimestre = solicitudes2dotrimestre;
	}
	
	@Column(name =  "solicitudes_3er_trimestre")
	public Integer getSolicitudes3ertrimestre() {
		return solicitudes3ertrimestre;
	}
	public void setSolicitudes3ertrimestre(Integer solicitudes3ertrimestre) {
		this.solicitudes3ertrimestre = solicitudes3ertrimestre;
	}
	
	@Column(name =  "solicitudes_4to_trimestre")
	public Integer getSolicitudes4totrimestre() {
		return solicitudes4totrimestre;
	}
	public void setSolicitudes4totrimestre(Integer solicitudes4totrimestre) {
		this.solicitudes4totrimestre = solicitudes4totrimestre;
	}
	
	@Column(name =  "productores_1er_trimestre")
	public Integer getProductores1ertrimestre() {
		return productores1ertrimestre;
	}
	public void setProductores1ertrimestre(Integer productores1ertrimestre) {
		this.productores1ertrimestre = productores1ertrimestre;
	}
	
	@Column(name =  "productores_2do_trimestre")
	public Integer getProductores2dotrimestre() {
		return productores2dotrimestre;
	}
	public void setProductores2dotrimestre(Integer productores2dotrimestre) {
		this.productores2dotrimestre = productores2dotrimestre;
	}
	
	@Column(name =  "productores_3er_trimestre")
	public Integer getProductores3ertrimestre() {
		return productores3ertrimestre;
	}
	public void setProductores3ertrimestre(Integer productores3ertrimestre) {
		this.productores3ertrimestre = productores3ertrimestre;
	}
	
	@Column(name =  "productores_4to_trimestre")
	public Integer getProductores4totrimestre() {
		return productores4totrimestre;
	}
	public void setProductores4totrimestre(Integer productores4totrimestre) {
		this.productores4totrimestre = productores4totrimestre;
	}
}
