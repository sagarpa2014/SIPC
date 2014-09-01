package mx.gob.comer.sipc.domain.historico;

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
@Table(name = "hco_inicializacion_esquema")
public class HcoInicializacionEsquema {

	private Long hcoIdinicializacionEsquema;
	private int hcoIdPrograma;
	private Date fechaPublicacionDOF;
	private Double volumenAutorizado;
	private Integer periodoDOFSI;
	private Integer periodoCartaSP;
	private Integer periodoSPOO;
	private Integer periodoORPago;
	private Set<HcoCuotasEsquema> hcoCuotasEsquema;
	private Double importeAutorizado;
	private Integer periodoOSIROSI;
	private String archivoPublicacionDof;
	private boolean cartaAdhesionSistema;
	private String acronimoCA; 
	private Date fechaCreacion;
	private Integer usuarioCreacion;
	private String leyendaAtentaNota;
		
	@Id
	@GeneratedValue(generator = "hco_inicializacion_esquema_hco_id_inicializacion_esquema_seq")
	@SequenceGenerator(name = "hco_inicializacion_esquema_hco_id_inicializacion_esquema_seq", sequenceName = "hco_inicializacion_esquema_hco_id_inicializacion_esquema_seq")
	@Column(name = "hco_id_inicializacion_esquema")
	public Long getHcoIdinicializacionEsquema() {
		return hcoIdinicializacionEsquema;
	}

	public void setHcoIdinicializacionEsquema(Long hcoIdinicializacionEsquema) {
		this.hcoIdinicializacionEsquema = hcoIdinicializacionEsquema;
	}
	
	@Column(name = "hco_id_programa")
	public int getHcoIdPrograma() {
		return hcoIdPrograma;
	}
	
	
	public void setHcoIdPrograma(int hcoIdPrograma) {
		this.hcoIdPrograma = hcoIdPrograma;
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
	@JoinColumn(name="hco_id_inicializacion_esquema", nullable=false) 	
	public Set<HcoCuotasEsquema> getHcoCuotasEsquema() {
		return hcoCuotasEsquema;
	}
	public void setHcoCuotasEsquema(Set<HcoCuotasEsquema> hcoCuotasEsquema) {
		this.hcoCuotasEsquema = hcoCuotasEsquema;
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
	
	@Column(name = "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "usuario_creacion")
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	@Column(name = "leyenda_atenta_nota")
	public String getLeyendaAtentaNota() {
		return leyendaAtentaNota;
	}

	public void setLeyendaAtentaNota(String leyendaAtentaNota) {
		this.leyendaAtentaNota = leyendaAtentaNota;
	}

	
}