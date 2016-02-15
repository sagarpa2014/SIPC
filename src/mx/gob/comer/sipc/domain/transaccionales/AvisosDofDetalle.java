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
	@Column(name = "clave_aviso", updatable=false, insertable=false)
	private String claveAviso;
	@Column(name =  "programa")
	private Integer programa;
	@Column(name =  "ciclo")
	private String ciclo;
	@Column(name =  "ciclo_agricola")
	private String cicloAgricola;
	@Column(name =  "ejercicio")
	private Integer ejercicio;
	@Column(name =  "id_cultivo")
	private Integer idCultivo;
	@Column(name =  "id_estado")
	private Integer idEstado;
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
	
	public Integer getPrograma() {
		return programa;
	}
	public void setPrograma(Integer programa) {
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
}
