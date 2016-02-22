package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "avisos_dof_detalle_v")
public class AvisosDofDetalleV {
	
	@Id	
	@Column(name =  "id")
	private Integer id;
	@Column(name = "clave_aviso")
	private String claveAviso;
	@Column(name = "id_prog_aviso")
	private Integer idProgAviso;
	@Column(name =  "programa")
	private String programa;
	@Column(name =  "ciclo")
	private String ciclo;
	@Column(name =  "ciclo_agricola")
	private String cicloAgricola;
	@Column(name =  "ejercicio")
	private Integer ejercicio;
	@Column(name =  "id_cultivo")
	private Integer idCultivo;
	@Column(name =  "cultivo")
	private String cultivo;
	@Column(name =  "id_estado")
	private Integer idEstado;
	@Column(name =  "estado")
	private String estado;
	@Column(name =  "volumen")
	private Double volumen;
	@Column(name =  "importe")
	private Double importe;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getClaveAviso() {
		return claveAviso;
	}
	public void setClaveAviso(String claveAviso) {
		this.claveAviso = claveAviso;
	}	
	
	public Integer getIdProgAviso() {
		return idProgAviso;
	}

	public void setIdProgAviso(Integer idProgAviso) {
		this.idProgAviso = idProgAviso;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}	
	
	public String getCicloAgricola() {
		return cicloAgricola;
	}

	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}

	public Integer getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}	
	
	public Integer getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}

	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	
}
