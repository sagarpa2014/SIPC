package mx.gob.comer.sipc.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "programas")
public class Programa{
	private int idPrograma;
	private String descripcionCorta;
	private String descripcionLarga;
	private String ciclo;
	private String cicloCorto;
	private String producto;
	private String estados;
	private String descripcion;
	private String rutaDocumentos;
	private Integer idArea;
	private Integer criterioPago;
	private Integer numeroEtapa;
	private Integer idComponente;
	private Integer idUnidadMedida;
	private String desApoyoEnOficioClc;
	private Date inicioPeriodoAcopio;
	private Date finPeriodoAcopio;
	
	

	
	public Programa() {
		super();
	}
	public Programa(int idPrograma, String descripcionCorta) {
		super();
		this.idPrograma = idPrograma;
		this.descripcionCorta = descripcionCorta;
	}
	@Id
	@GeneratedValue(generator = "programas_id_programa_seq")
	@SequenceGenerator(name = "programas_id_programa_seq", sequenceName = "programas_id_programa_seq")
	@Column(name =  "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "descripcion_corta")
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	@Column(name =  "descripcion_larga")
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
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
	
	@Column(name =  "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name =  "ruta_documentos")
	public String getRutaDocumentos() {
		return rutaDocumentos;
	}
	public void setRutaDocumentos(String rutaDocumentos) {
		this.rutaDocumentos = rutaDocumentos;
	}
	
	@Column(name =  "id_area")
	public Integer getIdArea() {
		return idArea;
	}
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
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
	
	@Column(name =  "id_componente")
	public Integer getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(Integer idComponente) {
		this.idComponente = idComponente;
	}
	
	@Column(name =  "id_unidad_medida")
	public Integer getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(Integer idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	

	@Column(name =  "des_apoyo_en_oficio_clc")
	public String getDesApoyoEnOficioClc() {
		return desApoyoEnOficioClc;
	}
	
	public void setDesApoyoEnOficioClc(String desApoyoEnOficioClc) {
		this.desApoyoEnOficioClc = desApoyoEnOficioClc;
	}
	@Column(name =  "inicio_periodo_acopio")
	public Date getInicioPeriodoAcopio() {
		return inicioPeriodoAcopio;
	}
	public void setInicioPeriodoAcopio(Date inicioPeriodoAcopio) {
		this.inicioPeriodoAcopio = inicioPeriodoAcopio;
	}
	
	@Column(name =  "fin_periodo_acopio")
	public Date getFinPeriodoAcopio() {
		return finPeriodoAcopio;
	}
	public void setFinPeriodoAcopio(Date finPeriodoAcopio) {
		this.finPeriodoAcopio = finPeriodoAcopio;
	}
	
}
	
