package mx.gob.comer.sipc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "archivos_pagos")
public class ArchivosPagos {

	private Long archivoId;
	private String nombreArchivo;
	private Date fechaArchivo;
	private String codigoOperacion;
	private Integer bancoId;
	private Integer consecutivoArchivo;
	private String folio;
	private Integer medioPago;
	private Long secuenciaInicial;
	private Long secuenciaFinal;
	private Long idOficio;
	private Integer estatus;
	
	
	@Id
	@GeneratedValue(generator = "archivos_pagos_id_seq")
	@SequenceGenerator(name = "archivos_pagos_id_seq", sequenceName = "archivos_pagos_id_seq")
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

	@Column(name = "medio_pago")
	public Integer getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(Integer medioPago) {
		this.medioPago = medioPago;
	}
	
	@Column(name = "secuencia_inicial")
	public Long getSecuenciaInicial() {
		return secuenciaInicial;
	}
	public void setSecuenciaInicial(Long secuenciaInicial) {
		this.secuenciaInicial = secuenciaInicial;
	}
	
	@Column(name = "secuencia_final")
	public Long getSecuenciaFinal() {
		return secuenciaFinal;
	}
	public void setSecuenciaFinal(Long secuenciaFinal) {
		this.secuenciaFinal = secuenciaFinal;
	}
	@Column(name = "id_oficio")
	public Long getIdOficio() {
		return idOficio;
	}
	public void setIdOficio(Long idOficio) {
		this.idOficio = idOficio;
	}
	
	@Column(name = "estatus")
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
}