package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "avisos_dof_detalle")
public class AvisosDofDetalle {
	
	@Id	
	@GeneratedValue(generator = "avisos_dof_detalle_id_seq")
	@SequenceGenerator(name = "avisos_dof_detalle_id_seq", sequenceName = "avisos_dof_detalle_id_seq")
	@Column(name =  "id")
	private Integer id;
	@Column(name = "id_avisos_dof", updatable=false, insertable=false)
	private Integer idAvisosDof;
	@Column(name =  "id_programa")
	private Integer idPrograma;
	@Column(name =  "id_apoyo")
	private Integer idApoyo;
	@Column(name =  "ciclo_agricola")
	private String cicloAgricola;
	@Column(name =  "ciclo")
	private String ciclo;
	@Column(name =  "anio")
	private Integer anio;	
	@Column(name =  "id_cultivo")
	private Integer idCultivo;
	@Column(name =  "id_estado")
	private Integer idEstado;
	@Column(name =  "volumen")
	private Double volumen;
	@Column(name =  "importe")
	private Double importe;
	@Column(name =  "volumen_regional")
	private Double volumenRegional;
	@Column(name =  "importe_regional")
	private Double importeRegional;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
		
	public Integer getIdAvisosDof() {
		return idAvisosDof;
	}

	public void setIdAvisosDof(Integer idAvisosDof) {
		this.idAvisosDof = idAvisosDof;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Integer getIdApoyo() {
		return idApoyo;
	}

	public void setIdApoyo(Integer idApoyo) {
		this.idApoyo = idApoyo;
	}

	public String getCicloAgricola() {
		return cicloAgricola;
	}

	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}
	
	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
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

	public Double getVolumenRegional() {
		return volumenRegional;
	}

	public void setVolumenRegional(Double volumenRegional) {
		this.volumenRegional = volumenRegional;
	}

	public Double getImporteRegional() {
		return importeRegional;
	}

	public void setImporteRegional(Double importeRegional) {
		this.importeRegional = importeRegional;
	}	
	
}
