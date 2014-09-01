package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "concentrado_det_pagos_trimestres_etapas_v")
public class ReporteDetConcentradoPagosEtapasV {
	
	private long id;
	private long idPrograma;
	private String programa;
	private long ejercicio;
	private long idComprador;
	private String comprador;
	private String ciclo;
	private String producto;
	private String estados;
	private Double volumen1erTrimestreEtapa1;
	private Double volumen1erTrimestreEtapa2;
	private Double volumen1erTrimestreEtapa3;
	private Double volumen1erTrimestreEtapa4;
	private Double volumen1erTrimestreEtapa5;
	private Double importe1erTrimestreEtapa1;
	private Double importe1erTrimestreEtapa2;
	private Double importe1erTrimestreEtapa3;
	private Double importe1erTrimestreEtapa4;
	private Double importe1erTrimestreEtapa5;
	private Double volumen2doTrimestreEtapa1;
	private Double volumen2doTrimestreEtapa2;
	private Double volumen2doTrimestreEtapa3;
	private Double volumen2doTrimestreEtapa4;
	private Double volumen2doTrimestreEtapa5;
	private Double importe2doTrimestreEtapa1;
	private Double importe2doTrimestreEtapa2;
	private Double importe2doTrimestreEtapa3;
	private Double importe2doTrimestreEtapa4;
	private Double importe2doTrimestreEtapa5;
	private Double volumen3erTrimestreEtapa1;
	private Double volumen3erTrimestreEtapa2;
	private Double volumen3erTrimestreEtapa3;
	private Double volumen3erTrimestreEtapa4;
	private Double volumen3erTrimestreEtapa5;
	private Double importe3erTrimestreEtapa1;
	private Double importe3erTrimestreEtapa2;
	private Double importe3erTrimestreEtapa3;
	private Double importe3erTrimestreEtapa4;
	private Double importe3erTrimestreEtapa5;
	private Double volumen4toTrimestreEtapa1;
	private Double volumen4toTrimestreEtapa2;
	private Double volumen4toTrimestreEtapa3;
	private Double volumen4toTrimestreEtapa4;
	private Double volumen4toTrimestreEtapa5;
	private Double importe4toTrimestreEtapa1;
	private Double importe4toTrimestreEtapa2;
	private Double importe4toTrimestreEtapa3;
	private Double importe4toTrimestreEtapa4;
	private Double importe4toTrimestreEtapa5;
	private String programaDesc;
	private String cartaAdhesion;
	private String entidadFederativa;
	private String municipio;
	private Integer solicitudes;
	private Integer productores;
	private String nombreEdoPago;

	@Id
	@Column(name =  "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name =  "id_programa")
	public long getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(long idPrograma) {
		this.idPrograma = idPrograma;
	}
	@Column(name =  "programa")
	public String getPrograma() {
		return programa;
	}	
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@Column(name =  "anio")
	public long getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(long ejercicio) {
		this.ejercicio = ejercicio;
	}
	@Column(name =  "id_comprador")
	public long getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(long idComprador) {
		this.idComprador = idComprador;
	}
	@Column(name =  "comprador")
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
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
	@Column(name =  "programa_desc")
	public String getProgramaDesc() {
		return programaDesc;
	}
	public void setProgramaDesc(String programaDesc) {
		this.programaDesc = programaDesc;
	}
	@Column(name =  "carta_adhesion")
	public String getCartaAdhesion() {
		return cartaAdhesion;
	}
	public void setCartaAdhesion(String cartaAdhesion) {
		this.cartaAdhesion = cartaAdhesion;
	}
	
	@Column(name =  "entidad_federativa")
	public String getEntidadFederativa() {
		return entidadFederativa;
	}
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	
	@Column(name =  "nombre_municipio")
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	@Column(name =  "volumen_1er_trimestre_etapa1")
	public Double getVolumen1erTrimestreEtapa1() {
		return volumen1erTrimestreEtapa1;
	}
	public void setVolumen1erTrimestreEtapa1(Double volumen1erTrimestreEtapa1) {
		this.volumen1erTrimestreEtapa1 = volumen1erTrimestreEtapa1;
	}
	@Column(name =  "volumen_1er_trimestre_etapa2")
	public Double getVolumen1erTrimestreEtapa2() {
		return volumen1erTrimestreEtapa2;
	}
	public void setVolumen1erTrimestreEtapa2(Double volumen1erTrimestreEtapa2) {
		this.volumen1erTrimestreEtapa2 = volumen1erTrimestreEtapa2;
	}
	@Column(name =  "volumen_1er_trimestre_etapa3")
	public Double getVolumen1erTrimestreEtapa3() {
		return volumen1erTrimestreEtapa3;
	}
	public void setVolumen1erTrimestreEtapa3(Double volumen1erTrimestreEtapa3) {
		this.volumen1erTrimestreEtapa3 = volumen1erTrimestreEtapa3;
	}
	@Column(name =  "volumen_1er_trimestre_etapa4")
	public Double getVolumen1erTrimestreEtapa4() {
		return volumen1erTrimestreEtapa4;
	}
	public void setVolumen1erTrimestreEtapa4(Double volumen1erTrimestreEtapa4) {
		this.volumen1erTrimestreEtapa4 = volumen1erTrimestreEtapa4;
	}
	@Column(name =  "volumen_1er_trimestre_etapa5")
	public Double getVolumen1erTrimestreEtapa5() {
		return volumen1erTrimestreEtapa5;
	}
	public void setVolumen1erTrimestreEtapa5(Double volumen1erTrimestreEtapa5) {
		this.volumen1erTrimestreEtapa5 = volumen1erTrimestreEtapa5;
	}
	@Column(name =  "importe_1er_trimestre_etapa1")
	public Double getImporte1erTrimestreEtapa1() {
		return importe1erTrimestreEtapa1;
	}
	public void setImporte1erTrimestreEtapa1(Double importe1erTrimestreEtapa1) {
		this.importe1erTrimestreEtapa1 = importe1erTrimestreEtapa1;
	}
	@Column(name =  "importe_1er_trimestre_etapa2")
	public Double getImporte1erTrimestreEtapa2() {
		return importe1erTrimestreEtapa2;
	}
	public void setImporte1erTrimestreEtapa2(Double importe1erTrimestreEtapa2) {
		this.importe1erTrimestreEtapa2 = importe1erTrimestreEtapa2;
	}
	@Column(name =  "importe_1er_trimestre_etapa3")
	public Double getImporte1erTrimestreEtapa3() {
		return importe1erTrimestreEtapa3;
	}
	public void setImporte1erTrimestreEtapa3(Double importe1erTrimestreEtapa3) {
		this.importe1erTrimestreEtapa3 = importe1erTrimestreEtapa3;
	}
	@Column(name =  "importe_1er_trimestre_etapa4")
	public Double getImporte1erTrimestreEtapa4() {
		return importe1erTrimestreEtapa4;
	}
	public void setImporte1erTrimestreEtapa4(Double importe1erTrimestreEtapa4) {
		this.importe1erTrimestreEtapa4 = importe1erTrimestreEtapa4;
	}
	@Column(name =  "importe_1er_trimestre_etapa5")
	public Double getImporte1erTrimestreEtapa5() {
		return importe1erTrimestreEtapa5;
	}
	public void setImporte1erTrimestreEtapa5(Double importe1erTrimestreEtapa5) {
		this.importe1erTrimestreEtapa5 = importe1erTrimestreEtapa5;
	}
	@Column(name =  "volumen_2do_trimestre_etapa1")
	public Double getVolumen2doTrimestreEtapa1() {
		return volumen2doTrimestreEtapa1;
	}
	public void setVolumen2doTrimestreEtapa1(Double volumen2doTrimestreEtapa1) {
		this.volumen2doTrimestreEtapa1 = volumen2doTrimestreEtapa1;
	}
	@Column(name =  "volumen_2do_trimestre_etapa2")
	public Double getVolumen2doTrimestreEtapa2() {
		return volumen2doTrimestreEtapa2;
	}
	public void setVolumen2doTrimestreEtapa2(Double volumen2doTrimestreEtapa2) {
		this.volumen2doTrimestreEtapa2 = volumen2doTrimestreEtapa2;
	}
	@Column(name =  "volumen_2do_trimestre_etapa3")
	public Double getVolumen2doTrimestreEtapa3() {
		return volumen2doTrimestreEtapa3;
	}
	public void setVolumen2doTrimestreEtapa3(Double volumen2doTrimestreEtapa3) {
		this.volumen2doTrimestreEtapa3 = volumen2doTrimestreEtapa3;
	}
	@Column(name =  "volumen_2do_trimestre_etapa4")
	public Double getVolumen2doTrimestreEtapa4() {
		return volumen2doTrimestreEtapa4;
	}
	public void setVolumen2doTrimestreEtapa4(Double volumen2doTrimestreEtapa4) {
		this.volumen2doTrimestreEtapa4 = volumen2doTrimestreEtapa4;
	}
	@Column(name =  "volumen_2do_trimestre_etapa5")
	public Double getVolumen2doTrimestreEtapa5() {
		return volumen2doTrimestreEtapa5;
	}
	public void setVolumen2doTrimestreEtapa5(Double volumen2doTrimestreEtapa5) {
		this.volumen2doTrimestreEtapa5 = volumen2doTrimestreEtapa5;
	}
	@Column(name =  "importe_2do_trimestre_etapa1")
	public Double getImporte2doTrimestreEtapa1() {
		return importe2doTrimestreEtapa1;
	}
	public void setImporte2doTrimestreEtapa1(Double importe2doTrimestreEtapa1) {
		this.importe2doTrimestreEtapa1 = importe2doTrimestreEtapa1;
	}
	@Column(name =  "importe_2do_trimestre_etapa2")
	public Double getImporte2doTrimestreEtapa2() {
		return importe2doTrimestreEtapa2;
	}
	public void setImporte2doTrimestreEtapa2(Double importe2doTrimestreEtapa2) {
		this.importe2doTrimestreEtapa2 = importe2doTrimestreEtapa2;
	}
	@Column(name =  "importe_2do_trimestre_etapa3")
	public Double getImporte2doTrimestreEtapa3() {
		return importe2doTrimestreEtapa3;
	}
	public void setImporte2doTrimestreEtapa3(Double importe2doTrimestreEtapa3) {
		this.importe2doTrimestreEtapa3 = importe2doTrimestreEtapa3;
	}
	@Column(name =  "importe_2do_trimestre_etapa4")
	public Double getImporte2doTrimestreEtapa4() {
		return importe2doTrimestreEtapa4;
	}
	public void setImporte2doTrimestreEtapa4(Double importe2doTrimestreEtapa4) {
		this.importe2doTrimestreEtapa4 = importe2doTrimestreEtapa4;
	}
	@Column(name =  "importe_2do_trimestre_etapa5")
	public Double getImporte2doTrimestreEtapa5() {
		return importe2doTrimestreEtapa5;
	}
	public void setImporte2doTrimestreEtapa5(Double importe2doTrimestreEtapa5) {
		this.importe2doTrimestreEtapa5 = importe2doTrimestreEtapa5;
	}
	@Column(name =  "volumen_3er_trimestre_etapa1")
	public Double getVolumen3erTrimestreEtapa1() {
		return volumen3erTrimestreEtapa1;
	}
	public void setVolumen3erTrimestreEtapa1(Double volumen3erTrimestreEtapa1) {
		this.volumen3erTrimestreEtapa1 = volumen3erTrimestreEtapa1;
	}
	@Column(name =  "volumen_3er_trimestre_etapa2")
	public Double getVolumen3erTrimestreEtapa2() {
		return volumen3erTrimestreEtapa2;
	}
	public void setVolumen3erTrimestreEtapa2(Double volumen3erTrimestreEtapa2) {
		this.volumen3erTrimestreEtapa2 = volumen3erTrimestreEtapa2;
	}
	@Column(name =  "volumen_3er_trimestre_etapa3")
	public Double getVolumen3erTrimestreEtapa3() {
		return volumen3erTrimestreEtapa3;
	}
	public void setVolumen3erTrimestreEtapa3(Double volumen3erTrimestreEtapa3) {
		this.volumen3erTrimestreEtapa3 = volumen3erTrimestreEtapa3;
	}
	@Column(name =  "volumen_3er_trimestre_etapa4")
	public Double getVolumen3erTrimestreEtapa4() {
		return volumen3erTrimestreEtapa4;
	}
	public void setVolumen3erTrimestreEtapa4(Double volumen3erTrimestreEtapa4) {
		this.volumen3erTrimestreEtapa4 = volumen3erTrimestreEtapa4;
	}
	@Column(name =  "volumen_3er_trimestre_etapa5")
	public Double getVolumen3erTrimestreEtapa5() {
		return volumen3erTrimestreEtapa5;
	}
	public void setVolumen3erTrimestreEtapa5(Double volumen3erTrimestreEtapa5) {
		this.volumen3erTrimestreEtapa5 = volumen3erTrimestreEtapa5;
	}
	@Column(name =  "importe_3er_trimestre_etapa1")
	public Double getImporte3erTrimestreEtapa1() {
		return importe3erTrimestreEtapa1;
	}
	public void setImporte3erTrimestreEtapa1(Double importe3erTrimestreEtapa1) {
		this.importe3erTrimestreEtapa1 = importe3erTrimestreEtapa1;
	}
	@Column(name =  "importe_3er_trimestre_etapa2")
	public Double getImporte3erTrimestreEtapa2() {
		return importe3erTrimestreEtapa2;
	}
	public void setImporte3erTrimestreEtapa2(Double importe3erTrimestreEtapa2) {
		this.importe3erTrimestreEtapa2 = importe3erTrimestreEtapa2;
	}
	@Column(name =  "importe_3er_trimestre_etapa3")
	public Double getImporte3erTrimestreEtapa3() {
		return importe3erTrimestreEtapa3;
	}
	public void setImporte3erTrimestreEtapa3(Double importe3erTrimestreEtapa3) {
		this.importe3erTrimestreEtapa3 = importe3erTrimestreEtapa3;
	}
	@Column(name =  "importe_3er_trimestre_etapa4")
	public Double getImporte3erTrimestreEtapa4() {
		return importe3erTrimestreEtapa4;
	}
	public void setImporte3erTrimestreEtapa4(Double importe3erTrimestreEtapa4) {
		this.importe3erTrimestreEtapa4 = importe3erTrimestreEtapa4;
	}
	@Column(name =  "importe_3er_trimestre_etapa5")
	public Double getImporte3erTrimestreEtapa5() {
		return importe3erTrimestreEtapa5;
	}
	public void setImporte3erTrimestreEtapa5(Double importe3erTrimestreEtapa5) {
		this.importe3erTrimestreEtapa5 = importe3erTrimestreEtapa5;
	}
	@Column(name =  "volumen_4to_trimestre_etapa1")
	public Double getVolumen4toTrimestreEtapa1() {
		return volumen4toTrimestreEtapa1;
	}
	public void setVolumen4toTrimestreEtapa1(Double volumen4toTrimestreEtapa1) {
		this.volumen4toTrimestreEtapa1 = volumen4toTrimestreEtapa1;
	}
	@Column(name =  "volumen_4to_trimestre_etapa2")
	public Double getVolumen4toTrimestreEtapa2() {
		return volumen4toTrimestreEtapa2;
	}
	public void setVolumen4toTrimestreEtapa2(Double volumen4toTrimestreEtapa2) {
		this.volumen4toTrimestreEtapa2 = volumen4toTrimestreEtapa2;
	}
	@Column(name =  "volumen_4to_trimestre_etapa3")
	public Double getVolumen4toTrimestreEtapa3() {
		return volumen4toTrimestreEtapa3;
	}
	public void setVolumen4toTrimestreEtapa3(Double volumen4toTrimestreEtapa3) {
		this.volumen4toTrimestreEtapa3 = volumen4toTrimestreEtapa3;
	}
	@Column(name =  "volumen_4to_trimestre_etapa4")
	public Double getVolumen4toTrimestreEtapa4() {
		return volumen4toTrimestreEtapa4;
	}
	public void setVolumen4toTrimestreEtapa4(Double volumen4toTrimestreEtapa4) {
		this.volumen4toTrimestreEtapa4 = volumen4toTrimestreEtapa4;
	}
	@Column(name =  "volumen_4to_trimestre_etapa5")
	public Double getVolumen4toTrimestreEtapa5() {
		return volumen4toTrimestreEtapa5;
	}
	public void setVolumen4toTrimestreEtapa5(Double volumen4toTrimestreEtapa5) {
		this.volumen4toTrimestreEtapa5 = volumen4toTrimestreEtapa5;
	}
	@Column(name =  "importe_4to_trimestre_etapa1")
	public Double getImporte4toTrimestreEtapa1() {
		return importe4toTrimestreEtapa1;
	}
	public void setImporte4toTrimestreEtapa1(Double importe4toTrimestreEtapa1) {
		this.importe4toTrimestreEtapa1 = importe4toTrimestreEtapa1;
	}
	@Column(name =  "importe_4to_trimestre_etapa2")
	public Double getImporte4toTrimestreEtapa2() {
		return importe4toTrimestreEtapa2;
	}
	public void setImporte4toTrimestreEtapa2(Double importe4toTrimestreEtapa2) {
		this.importe4toTrimestreEtapa2 = importe4toTrimestreEtapa2;
	}
	@Column(name =  "importe_4to_trimestre_etapa3")
	public Double getImporte4toTrimestreEtapa3() {
		return importe4toTrimestreEtapa3;
	}
	public void setImporte4toTrimestreEtapa3(Double importe4toTrimestreEtapa3) {
		this.importe4toTrimestreEtapa3 = importe4toTrimestreEtapa3;
	}
	@Column(name =  "importe_4to_trimestre_etapa4")
	public Double getImporte4toTrimestreEtapa4() {
		return importe4toTrimestreEtapa4;
	}
	public void setImporte4toTrimestreEtapa4(Double importe4toTrimestreEtapa4) {
		this.importe4toTrimestreEtapa4 = importe4toTrimestreEtapa4;
	}
	@Column(name =  "importe_4to_trimestre_etapa5")
	public Double getImporte4toTrimestreEtapa5() {
		return importe4toTrimestreEtapa5;
	}
	public void setImporte4toTrimestreEtapa5(Double importe4toTrimestreEtapa5) {
		this.importe4toTrimestreEtapa5 = importe4toTrimestreEtapa5;
	}	
	@Column(name =  "solicitudes")
	public Integer getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(Integer solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	@Column(name =  "productores")
	public Integer getProductores() {
		return productores;
	}
	public void setProductores(Integer productores) {
		this.productores = productores;
	}
	
	@Column(name =  "origen_grano")
	public String getNombreEdoPago() {
		return nombreEdoPago;
	}
	public void setNombreEdoPago(String nombreEdoPago) {
		this.nombreEdoPago = nombreEdoPago;
	}
}
