package mx.gob.comer.sipc.domain.transaccionales;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "carta_adhesion")
public class CartaAdhesion {
	
	private String folioCartaAdhesion;
	private Integer idSI;
	private Integer idPrograma;
	private Integer idComprador;
	private Boolean documentacion;
	private Boolean fianza;
	private Integer estatus;
	//private Set<AsignacionCartasAdhesion> asignacionCartaAdhesion;
	private Boolean tCartaAdehsionHastaSP;
	private String clabe;
	private String oficioEntrega;
	private Date fechaEntrega;
	private String nombreOficioEntrega;
	private Integer tipoConstancia;
	//private Set<DetalleAsignacionCartasAdhesion> detalleAsignacionCartasAdhesion;
	
	
	@Id	
	@Column(name =  "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "id_si")
	public Integer getIdSI() {
		return idSI;
	}
	public void setIdSI(Integer idSI) {
		this.idSI = idSI;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name =  "documentacion")
	public Boolean getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(Boolean documentacion) {
		this.documentacion = documentacion;
	}
	
	@Column(name = "fianza")
	public Boolean getFianza() {
		return fianza;
	}
	
	public void setFianza(Boolean fianza) {
		this.fianza = fianza;
	}
	
	@Column(name = "estatus_ca")	
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	/*@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
	@JoinColumn(name="folio_carta_adhesion", nullable=false)
	public Set<AsignacionCartasAdhesion> getAsignacionCartaAdhesion() {
		return asignacionCartaAdhesion;
	}
	public void setAsignacionCartaAdhesion(
			Set<AsignacionCartasAdhesion> asignacionCartaAdhesion) {
		this.asignacionCartaAdhesion = asignacionCartaAdhesion;
	}*/
	
	@Column(name = "t_carta_adhesion_hasta_sp")
	public Boolean gettCartaAdehsionHastaSP() {
		return tCartaAdehsionHastaSP;
	}
	public void settCartaAdehsionHastaSP(Boolean tCartaAdehsionHastaSP) {
		this.tCartaAdehsionHastaSP = tCartaAdehsionHastaSP;
	}
	
	@Column(name = "clabe")
	public String getClabe() {
		return clabe;
	}
	
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	
	@Column(name = "oficio_entrega")
	public String getOficioEntrega() {
		return oficioEntrega;
	}
	public void setOficioEntrega(String oficioEntrega) {
		this.oficioEntrega = oficioEntrega;
	}
	
	@Column(name = "fecha_entrega")
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	@Column(name = "nombre_oficio_entrega")
	public String getNombreOficioEntrega() {
		return nombreOficioEntrega;
	}
	public void setNombreOficioEntrega(String nombreOficioEntrega) {
		this.nombreOficioEntrega = nombreOficioEntrega;
	}
	
	@Column(name = "tipo_constancia")
	public Integer getTipoConstancia() {
		return tipoConstancia;
	}
	
	public void setTipoConstancia(Integer tipoConstancia) {
		this.tipoConstancia = tipoConstancia;
	}
	
	/*@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
	@JoinColumn(name="folio_carta_adhesion", nullable=false)
	public Set<DetalleAsignacionCartasAdhesion> getDetalleAsignacionCartasAdhesion() {
		return detalleAsignacionCartasAdhesion;
	}
	public void setDetalleAsignacionCartasAdhesion(
			Set<DetalleAsignacionCartasAdhesion> detalleAsignacionCartasAdhesion) {
		this.detalleAsignacionCartasAdhesion = detalleAsignacionCartasAdhesion;
	}*/	
	
}
