package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "capacidades_bodegas")
public class CapacidadesBodegas {
	
	private String claveBodega;
	private Double capacidadBodega;
	private Integer noSilos;
	private Double capacidadSilos;
	private Double capacidadIntemperie;
	private Double capacidadTechada;
	private Double totalAlmacenamiento;
	private Integer numRampas;
	private Double capacidadRampas;
	private Integer numSecadoras;
	private Double capacidadSecado;
	private Integer numCargas;
	private Double capacidadCargas;
	private String piso;
	private Double capacidadPiso;
	private String ffcc;
	private Double capacidadFfcc;
	private String espuelaFfcc;
	private Double capacidadEspuela;
	private Double capacidadRecepcion;
	private Double capacidadEmbarque;
	private String equipoAnalisis;
	private String aplicaNormasCalidad;
	private String observaciones;

	@Id
	@Column(name = "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	@Column(name = "capacidad_bodega")
	public Double getCapacidadBodega() {
		return capacidadBodega;
	}
	public void setCapacidadBodega(Double capacidadBodega) {
		this.capacidadBodega = capacidadBodega;
	}
	
	@Column(name = "no_silos")
	public Integer getNoSilos() {
		return noSilos;
	}
	public void setNoSilos(Integer noSilos) {
		this.noSilos = noSilos;
	}
	
	@Column(name = "capacidad_silos")
	public Double getCapacidadSilos() {
		return capacidadSilos;
	}
	public void setCapacidadSilos(Double capacidadSilos) {
		this.capacidadSilos = capacidadSilos;
	}
	
	@Column(name = "capacidad_intemperie")
	public Double getCapacidadIntemperie() {
		return capacidadIntemperie;
	}
	public void setCapacidadIntemperie(Double capacidadIntemperie) {
		this.capacidadIntemperie = capacidadIntemperie;
	}
	
	@Column(name = "capacidad_techada")
	public Double getCapacidadTechada() {
		return capacidadTechada;
	}
	public void setCapacidadTechada(Double capacidadTechada) {
		this.capacidadTechada = capacidadTechada;
	}
	
	@Column(name = "total_almacenamiento")
	public Double getTotalAlmacenamiento() {
		return totalAlmacenamiento;
	}
	public void setTotalAlmacenamiento(Double totalAlmacenamiento) {
		this.totalAlmacenamiento = totalAlmacenamiento;
	}
	
	@Column(name = "num_rampas")
	public Integer getNumRampas() {
		return numRampas;
	}
	public void setNumRampas(Integer numRampas) {
		this.numRampas = numRampas;
	}
	
	@Column(name = "capacidad_rampas")
	public Double getCapacidadRampas() {
		return capacidadRampas;
	}
	public void setCapacidadRampas(Double capacidadRampas) {
		this.capacidadRampas = capacidadRampas;
	}
	
	@Column(name = "num_secadoras")
	public Integer getNumSecadoras() {
		return numSecadoras;
	}
	public void setNumSecadoras(Integer numSecadoras) {
		this.numSecadoras = numSecadoras;
	}
	
	@Column(name = "capacidad_secado")
	public Double getCapacidadSecado() {
		return capacidadSecado;
	}
	public void setCapacidadSecado(Double capacidadSecado) {
		this.capacidadSecado = capacidadSecado;
	}
	
	@Column(name = "num_cargas")
	public Integer getNumCargas() {
		return numCargas;
	}
	public void setNumCargas(Integer numCargas) {
		this.numCargas = numCargas;
	}
	
	@Column(name = "capacidad_cargas")
	public Double getCapacidadCargas() {
		return capacidadCargas;
	}
	public void setCapacidadCargas(Double capacidadCargas) {
		this.capacidadCargas = capacidadCargas;
	}
	
	@Column(name = "piso")
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	@Column(name = "capacidad_piso")
	public Double getCapacidadPiso() {
		return capacidadPiso;
	}
	public void setCapacidadPiso(Double capacidadPiso) {
		this.capacidadPiso = capacidadPiso;
	}
	
	@Column(name = "ffcc")
	public String getFfcc() {
		return ffcc;
	}
	public void setFfcc(String ffcc) {
		this.ffcc = ffcc;
	}
	
	@Column(name = "capacidad_ffcc")
	public Double getCapacidadFfcc() {
		return capacidadFfcc;
	}
	public void setCapacidadFfcc(Double capacidadFfcc) {
		this.capacidadFfcc = capacidadFfcc;
	}
	
	@Column(name = "espuela_ffcc")
	public String getEspuelaFfcc() {
		return espuelaFfcc;
	}
	public void setEspuelaFfcc(String espuelaFfcc) {
		this.espuelaFfcc = espuelaFfcc;
	}
	
	@Column(name = "capacidad_espuela")
	public Double getCapacidadEspuela() {
		return capacidadEspuela;
	}
	public void setCapacidadEspuela(Double capacidadEspuela) {
		this.capacidadEspuela = capacidadEspuela;
	}
	
	@Column(name = "capacidad_recepcion")
	public Double getCapacidadRecepcion() {
		return capacidadRecepcion;
	}
	public void setCapacidadRecepcion(Double capacidadRecepcion) {
		this.capacidadRecepcion = capacidadRecepcion;
	}
	
	@Column(name = "capacidad_embarque")
	public Double getCapacidadEmbarque() {
		return capacidadEmbarque;
	}
	public void setCapacidadEmbarque(Double capacidadEmbarque) {
		this.capacidadEmbarque = capacidadEmbarque;
	}
	
	@Column(name = "equipo_analisis")
	public String getEquipoAnalisis() {
		return equipoAnalisis;
	}
	public void setEquipoAnalisis(String equipoAnalisis) {
		this.equipoAnalisis = equipoAnalisis;
	}
	
	@Column(name = "aplica_normas_calidad")
	public String getAplicaNormasCalidad() {
		return aplicaNormasCalidad;
	}
	public void setAplicaNormasCalidad(String aplicaNormasCalidad) {
		this.aplicaNormasCalidad = aplicaNormasCalidad;
	}
	
	@Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}