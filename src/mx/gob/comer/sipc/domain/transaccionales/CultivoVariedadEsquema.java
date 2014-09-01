package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cultivo_variedad_esquema")
public class CultivoVariedadEsquema {
	
	private long idCve;
	private Integer idPrograma;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double volumen;
	
	@Id	
	@Column(name =  "id_cve")
	@GeneratedValue(generator = "cultivo_variedad_esquema_id_cve_seq")
	@SequenceGenerator(name = "cultivo_variedad_esquema_id_cve_seq", sequenceName = "cultivo_variedad_esquema_id_cve_seq")
	public long getIdCve() {
		return idCve;
	}
	public void setIdCve(long idCve) {
		this.idCve = idCve;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	
	@Column(name =  "volumen")
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

}
