package mx.gob.comer.sipc.vistas.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartas_adhesion_v")
public class CartasAdhesionV{
	
	private String id;
	private int idPrograma;
	private String programa;
	private String programaLargo;
	private int idComprador;
	private String comprador;
	private String carta;
	private Integer estatus;
	private String estatusDesc;
	private Boolean expedienteCompleto;
	private Boolean fianza;
	private Boolean documentacion;
	private String oficioEntrega;
	private Date fechaEntrega;
	private String nombreOficioEntrega;
	
	@Id
	@Column(name =  "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name =  "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_comprador")
	public int getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name =  "programa")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	@Column(name =  "programa_largo")
	public String getProgramaLargo() {
		return programaLargo;
	}
	public void setProgramaLargo(String programaLargo) {
		this.programaLargo = programaLargo;
	}
	
	@Column(name =  "nombre_comprador")
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	
	@Column(name =  "folio_carta_adhesion")
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	
	@Column(name =  "estatus_ca")
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	@Column(name =  "desc_estatus_ca")
	public String getEstatusDesc() {
		return estatusDesc;
	}
	public void setEstatusDesc(String estatusDesc) {
		this.estatusDesc = estatusDesc;
	}

	@Column(name =  "expediente_completo")
	public Boolean getExpedienteCompleto() {
		return expedienteCompleto;
	}
	public void setExpedienteCompleto(Boolean expedienteCompleto) {
		this.expedienteCompleto = expedienteCompleto;
	}
	
	@Column(name =  "fianza")
	public Boolean getFianza() {
		return fianza;
	}
	public void setFianza(Boolean fianza) {
		this.fianza = fianza;
	}
	
	@Column(name =  "documentacion")
	public Boolean getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(Boolean documentacion) {
		this.documentacion = documentacion;
	}
	
	@Column(name =  "oficio_entrega")
	public String getOficioEntrega() {
		return oficioEntrega;
	}
	public void setOficioEntrega(String oficioEntrega) {
		this.oficioEntrega = oficioEntrega;
	}
	
	@Column(name =  "fecha_entrega")
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	@Column(name =  "nombre_oficio_entrega")
	public String getNombreOficioEntrega() {
		return nombreOficioEntrega;
	}
	public void setNombreOficioEntrega(String nombreOficioEntrega) {
		this.nombreOficioEntrega = nombreOficioEntrega;
	}
}