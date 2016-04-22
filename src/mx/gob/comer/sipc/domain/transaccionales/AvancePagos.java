package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "avance_pagos")
public class AvancePagos {
	
	@Id	
	@GeneratedValue(generator = "avance_pagos_id_seq")
	@SequenceGenerator(name = "avance_pagos_id_seq", sequenceName = "avance_pagos_id_seq")
	@Column(name =  "id")
	private Integer id;
	@Column(name =  "id_programa")
	private Integer programa;
	@Column(name =  "id_apoyo")
	private Integer idApoyo;
	@Column(name =  "id_cultivo")
	private Integer idCultivo;
	@Column(name =  "ciclo_agricola")
	private String cicloAgricola;
	@Column(name =  "id_estado")
	private Integer idEstado;
	@Column(name =  "solicitudes")
	private Integer solicitudes;	
	@Column(name =  "volumen")
	private Double volumen;
	@Column(name =  "importe")
	private Double importe;
	@Column(name =  "productores")
	private Integer productores;
	@Column(name =  "fecha_avance")
	private Date fechaAvance;
	@Column(name =  "fecha_registro")
	private Date fechaRegistro;
	@Column(name =  "folio_archivo")
	private String folioArchivo;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdApoyo() {
		return idApoyo;
	}

	public void setIdApoyo(Integer idApoyo) {
		this.idApoyo = idApoyo;
	}

	public Integer getPrograma() {
		return programa;
	}
	public void setPrograma(Integer programa) {
		this.programa = programa;
	}
	
	public String getCicloAgricola() {
		return cicloAgricola;
	}

	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}
	
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}	
	
	public Integer getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Integer solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Integer getProductores() {
		return productores;
	}

	public void setProductores(Integer productores) {
		this.productores = productores;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaAvance() {
		return fechaAvance;
	}

	public void setFechaAvance(Date fechaAvance) {
		this.fechaAvance = fechaAvance;
	}

	public String getFolioArchivo() {
		return folioArchivo;
	}

	public void setFolioArchivo(String folioArchivo) {
		this.folioArchivo = folioArchivo;
	}
		
}
