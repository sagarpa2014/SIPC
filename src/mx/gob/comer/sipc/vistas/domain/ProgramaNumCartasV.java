package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_num_cartas_v")
public class ProgramaNumCartasV {
	private Integer idPrograma;	
	private String programa;
	private Integer numeroCartas;
	private Integer noCartaOperacion; //no_carta_operacion
	private Integer noCartaCerradas;//no_carta_cerradas
	private Double volumenSusceptible;
	private Double importeSusceptible;
	private Double volumenApoyado;
	private Double importeApoyado;
	private Double volumenEnRevision;
	private Double importeEnRevision;
	private Double volumenAutorizado;
	private Double importeAutorizado;
	
	
	@Id	
	@Column(name = "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name = "descripcion_corta")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	@Column(name = "num_cartas")
	public Integer getNumeroCartas() {
		return numeroCartas;
	}
	
	public void setNumeroCartas(Integer numeroCartas) {
		this.numeroCartas = numeroCartas;
	}
	
	@Column(name = "no_carta_operacion")
	public Integer getNoCartaOperacion() {
		return noCartaOperacion;
	}
	public void setNoCartaOperacion(Integer noCartaOperacion) {
		this.noCartaOperacion = noCartaOperacion;
	}
	
	@Column(name = "no_carta_cerradas")
	public Integer getNoCartaCerradas() {
		return noCartaCerradas;
	}
	public void setNoCartaCerradas(Integer noCartaCerradas) {
		this.noCartaCerradas = noCartaCerradas;
	}
	
	@Column(name = "volumen_susceptible")
	public Double getVolumenSusceptible() {
		return volumenSusceptible;
	}
	public void setVolumenSusceptible(Double volumenSusceptible) {
		this.volumenSusceptible = volumenSusceptible;
	}
	
	@Column(name = "importe_susceptible")
	public Double getImporteSusceptible() {
		return importeSusceptible;
	}
	public void setImporteSusceptible(Double importeSusceptible) {
		this.importeSusceptible = importeSusceptible;
	}
	
	@Column(name = "volumen_apoyado")
	public Double getVolumenApoyado() {
		return volumenApoyado;
	}
	public void setVolumenApoyado(Double volumenApoyado) {
		this.volumenApoyado = volumenApoyado;
	}
	
	@Column(name = "importe_apoyado")
	public Double getImporteApoyado() {
		return importeApoyado;
	}
	public void setImporteApoyado(Double importeApoyado) {
		this.importeApoyado = importeApoyado;
	}
	
	@Column(name = "volumen_en_revision")
	public Double getVolumenEnRevision() {
		return volumenEnRevision;
	}
	public void setVolumenEnRevision(Double volumenEnRevision) {
		this.volumenEnRevision = volumenEnRevision;
	}
	
	@Column(name = "importe_en_revision")
	public Double getImporteEnRevision() {
		return importeEnRevision;
	}
	public void setImporteEnRevision(Double importeEnRevision) {
		this.importeEnRevision = importeEnRevision;
	}
	
	@Column(name = "volumen_autorizado")
	public Double getVolumenAutorizado() {
		return volumenAutorizado;
	}
	public void setVolumenAutorizado(Double volumenAutorizado) {
		this.volumenAutorizado = volumenAutorizado;
	}
	
	@Column(name = "importe_autorizado")
	public Double getImporteAutorizado() {
		return importeAutorizado;
	}
	
	public void setImporteAutorizado(Double importeAutorizado) {
		this.importeAutorizado = importeAutorizado;
	}
	
}
