package mx.gob.comer.sipc.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inicializacion_esquema")
public class InicializacionEsquema {

	private Long id;
	private int idPrograma;
	private Date fechaPublicacionDOF;
	private Double volumenAutorizado;
	private Integer periodoDOFSI;
	private Integer periodoCartaSP;
	private Integer periodoSPOO;
	private Integer periodoORPago;
	private Set<CuotasEsquema> cuotasEsquema;
	private Double importeAutorizado;
	private Integer periodoOSIROSI;
	private String archivoPublicacionDof;
	private boolean cartaAdhesionSistema;
	private String acronimoCA; 
	private Integer documentacion;
	private String leyendaAtentaNota;
	
	
	
	@Id
	@GeneratedValue(generator = "inicializacion_esquema_id_seq")
	@SequenceGenerator(name = "inicializacion_esquema_id_seq", sequenceName = "inicializacion_esquema_id_seq")
	@Column(name = "id_inicializacion_esquema")	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name = "fecha_publicacion_dof")
	public Date getFechaPublicacionDOF() {
		return fechaPublicacionDOF;
	}
	public void setFechaPublicacionDOF(Date fechaPublicacionDOF) {
		this.fechaPublicacionDOF = fechaPublicacionDOF;
	}
	
	@Column(name = "volumen_autorizado")
	public Double getVolumenAutorizado() {
		return volumenAutorizado;
	}
	public void setVolumenAutorizado(Double volumenAutorizado) {
		this.volumenAutorizado = volumenAutorizado;
	}
	
	@Column(name = "periodo_dof_si")
	public Integer getPeriodoDOFSI() {
		return periodoDOFSI;
	}
	public void setPeriodoDOFSI(Integer periodoDOFSI) {
		this.periodoDOFSI = periodoDOFSI;
	}
	
	@Column(name = "periodo_carta_sp")
	public Integer getPeriodoCartaSP() {
		return periodoCartaSP;
	}
	public void setPeriodoCartaSP(Integer periodoCartaSP) {
		this.periodoCartaSP = periodoCartaSP;
	}
	
	@Column(name = "periodo_sp_oo")
	public Integer getPeriodoSPOO() {
		return periodoSPOO;
	}
	public void setPeriodoSPOO(Integer periodoSPOO) {
		this.periodoSPOO = periodoSPOO;
	}
	
	@Column(name = "periodo_or_pago")
	public Integer getPeriodoORPago() {
		return periodoORPago;
	}
	public void setPeriodoORPago(Integer periodoORPago) {
		this.periodoORPago = periodoORPago;
	}
	
		
	@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
	@JoinColumn(name="id_inicializacion_esquema", nullable=false) 	
	public Set<CuotasEsquema> getCuotasEsquema() {
		return cuotasEsquema;
	}
	
	public void setCuotasEsquema(Set<CuotasEsquema> cuotasEsquema) {
		this.cuotasEsquema = cuotasEsquema;
	}
	
	@Column(name = "importe_autorizado")
	public Double getImporteAutorizado() {
		return importeAutorizado;
	}
	public void setImporteAutorizado(Double importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}
	
	@Column(name = "periodo_osi_rosi")
	public Integer getPeriodoOSIROSI() {
		return periodoOSIROSI;
	}
	public void setPeriodoOSIROSI(Integer periodoOSIROSI) {
		this.periodoOSIROSI = periodoOSIROSI;
	}
	
	@Column(name = "archivo_publicacion_dof")
	public String getArchivoPublicacionDof() {
		return archivoPublicacionDof;
	}
	public void setArchivoPublicacionDof(String archivoPublicacionDof) {
		this.archivoPublicacionDof = archivoPublicacionDof;
	}
	
	@Column(name = "carta_adhesion_sistema")
	public boolean isCartaAdhesionSistema() {
		return cartaAdhesionSistema;
	}
	public void setCartaAdhesionSistema(boolean cartaAdhesionSistema) {
		this.cartaAdhesionSistema = cartaAdhesionSistema;
	}
		
	@Column(name = "acronimo_ca")
	public String getAcronimoCA() {
		return acronimoCA;
	}
	public void setAcronimoCA(String acronimoCA) {
		this.acronimoCA = acronimoCA;
	}
	
	@Column(name = "documentacion")
	public Integer getDocumentacion() {
		return documentacion;
	}
	
	public void setDocumentacion(Integer documentacion) {
		this.documentacion = documentacion;
	}
	
	@Column(name = "leyenda_atenta_nota")
	public String getLeyendaAtentaNota() {
		return leyendaAtentaNota;
	}
	public void setLeyendaAtentaNota(String leyendaAtentaNota) {
		this.leyendaAtentaNota = leyendaAtentaNota;
	}	

}