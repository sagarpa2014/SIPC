package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oficio_comprador_programa_v")
public class OficioCompradorProgramaV {

	private Long idPago;
	private Long idOficioPagos;
	private String noOficio;
	private Integer folioClc;
	private Integer idComprador;
	private String comprador;
	private Double importe;
	private String etapa;
	private Double volumen;
	private String claveRastreo;
	private Date fechaPresentacion;
	private Date fechaPago;	
	private String clabe;
	private String rfc;
	private int estatus;
	private String programa;
	private String banco;
	private String desRechazo;
	private Integer criterioPago;
	private String programaLargo;
	private String cartaAdhesion;
	

	@Id
	@Column(name = "id_pago")
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}
	
	@Column(name = "id_oficio_pagos")
	public Long getIdOficioPagos() {
		return idOficioPagos;
	}
	public void setIdOficioPagos(Long idOficioPagos) {
		this.idOficioPagos = idOficioPagos;
	}
	
	@Column(name = "no_oficio")
	public String getNoOficio() {
		return noOficio;
	}
	public void setNoOficio(String noOficio) {
		this.noOficio = noOficio;
	}

	@Column(name = "folio_clc")
	public Integer getFolioClc() {
		return folioClc;
	}
	public void setFolioClc(Integer folioClc) {
		this.folioClc = folioClc;
	}
	
	@Column(name = "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	@Column(name = "comprador")
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	
	@Column(name = "importe")
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	@Column(name = "etapa")
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	@Column(name = "volumen")
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name = "clave_rastreo")
	public String getClaveRastreo() {
		return claveRastreo;
	}
	public void setClaveRastreo(String claveRastreo) {
		this.claveRastreo = claveRastreo;
	}
	
	@Column(name = "fecha_presentacion")
	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}
	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}
	
	@Column(name = "fecha_pago")
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	@Column(name = "clabe")
	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	
	@Column(name = "rfc")
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	@Column(name = "estatus")
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	@Column(name = "programa")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	@Column(name = "banco")
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	@Column(name = "desc_rechazo")
	public String getDesRechazo() {
		return desRechazo;
	}
	public void setDesRechazo(String desRechazo) {
		this.desRechazo = desRechazo;
	}
	
	@Column(name = "criterio_pago")
	public Integer getCriterioPago() {
		return criterioPago;
	}
	public void setCriterioPago(Integer criterioPago) {
		this.criterioPago = criterioPago;
	}
	
	@Column(name = "programa_largo")
	public String getProgramaLargo() {
		return programaLargo;
	}
	public void setProgramaLargo(String programaLargo) {
		this.programaLargo = programaLargo;
	}
	
	@Column(name = "carta_adhesion")
	public String getCartaAdhesion() {
		return cartaAdhesion;
	}
	public void setCartaAdhesion(String cartaAdhesion) {
		this.cartaAdhesion = cartaAdhesion;
	}
	
}
