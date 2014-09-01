package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resumen_avance_acopio_v")
public class ResumenAvanceAcopioV {
		
	private long id;
	private Integer idCiclo;
	private String cicloLargo;
	private String cicloCorto;
	private String ciclo;
	private Integer idCicloSeg;
	private Integer ejercicio;
	private Integer idEstadoBodega;
	private String nombreEstadoBodega;
	private Integer idCultivo;
	private String nombreCultivo;
	private Double acopioTotal;
	private Double movilizadoTotal;
	private Double existenciaAM;

	@Id	
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name = "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}
	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}
	
	@Column(name = "ciclo_corto")
	public String getCicloCorto() {
		return cicloCorto;
	}
	public void setCicloCorto(String cicloCorto) {
		this.cicloCorto = cicloCorto;
	}
	
	@Column(name = "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name = "id_ciclo_seg")
	public Integer getIdCicloSeg() {
		return idCicloSeg;
	}
	public void setIdCicloSeg(Integer idCicloSeg) {
		this.idCicloSeg = idCicloSeg;
	}
	
	@Column(name = "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	@Column(name = "id_estado_bodega")
	public Integer getIdEstadoBodega() {
		return idEstadoBodega;
	}
	public void setIdEstadoBodega(Integer idEstadoBodega) {
		this.idEstadoBodega = idEstadoBodega;
	}
	
	@Column(name = "nombre_estado_bodega")
	public String getNombreEstadoBodega() {
		return nombreEstadoBodega;
	}
	public void setNombreEstadoBodega(String nombreEstadoBodega) {
		this.nombreEstadoBodega = nombreEstadoBodega;
	}
	
	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name = "nombre_cultivo")
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}

	@Column(name = "acopio_total")
	public Double getAcopioTotal() {
		return acopioTotal;
	}
	public void setAcopioTotal(Double acopioTotal) {
		this.acopioTotal = acopioTotal;
	}
	
	@Column(name = "movilizado_total")
	public Double getMovilizadoTotal() {
		return movilizadoTotal;
	}
	public void setMovilizadoTotal(Double movilizadoTotal) {
		this.movilizadoTotal = movilizadoTotal;
	}
	
	@Column(name = "existencia_am")
	public Double getExistenciaAM() {
		return existenciaAM;
	}
	public void setExistenciaAM(Double existenciaAM) {
		this.existenciaAM = existenciaAM;
	}		
}