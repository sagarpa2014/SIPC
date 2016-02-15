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
	@Column(name =  "programa")
	private String programa;
	@Column(name =  "ciclo")
	private String ciclo;
	@Column(name =  "ejercicio")
	private Integer ejercicio;
	@Column(name =  "cultivo")
	private String cultivo;
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
	
	public Integer getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
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
