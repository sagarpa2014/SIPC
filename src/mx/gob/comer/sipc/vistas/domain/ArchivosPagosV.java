package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "archivos_pagos_v")
public class ArchivosPagosV {

	private Long archivoId;
	private String nombreArchivo;
	private Date fechaArchivo;
	private String codigoOperacion;
	private Integer bancoId;
	private String bancoNombre;
	private Integer consecutivoArchivo;
	private String folio;
	private Integer medioPago;
	private String medioPagoNombre;
	private Long idOficio;
	private String oficio;
	private String folioCLC;
	
	@Id
	@Column(name = "archivo_id")	
	public Long getArchivoId() {
		return archivoId;
	}
	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}

	@Column(name = "nombre")
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	@Column(name = "fecha")
	public Date getFechaArchivo() {
		return fechaArchivo;
	}
	public void setFechaArchivo(Date fecha) {
		this.fechaArchivo = fecha;
	}
	
	@Column(name = "codigo_operacion")
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	
	@Column(name = "banco_id")
	public Integer getBancoId() {
		return bancoId;
	}
	public void setBancoId(Integer bancoId) {
		this.bancoId = bancoId;
	}

	@Column(name = "banco")
	public String getBancoNombre() {
		return bancoNombre;
	}
	public void setBancoNombre(String bancoNombre) {
		this.bancoNombre = bancoNombre;
	}
	@Column(name = "consecutivo_archivo")
	public Integer getConsecutivoArchivo() {
		return consecutivoArchivo;
	}
	public void setConsecutivoArchivo(Integer consecutivoArchivo) {
		this.consecutivoArchivo = consecutivoArchivo;
	}	

	@Column(name = "folio")
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "medio_pago_id")
	public Integer getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(Integer medioPago) {
		this.medioPago = medioPago;
	}

	@Column(name = "medio_pago")
	public String getMedioPagoNombre() {
		return medioPagoNombre;
	}
	public void setMedioPagoNombre(String medioPagoNombre) {
		this.medioPagoNombre = medioPagoNombre;
	}
	
	@Column(name = "id_oficio")
	public Long getIdOficio() {
		return idOficio;
	}
	public void setIdOficio(Long idOficio) {
		this.idOficio = idOficio;
	}
	
	@Column(name = "no_oficio")
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	
	@Column(name = "folio_clc")
	public String getFolioCLC() {
		return folioCLC;
	}
	public void setFolioCLC(String folioCLC) {
		this.folioCLC = folioCLC;
	}
}