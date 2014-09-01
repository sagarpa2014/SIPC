package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "asignacion_cartas_adhesion")
public class AsignacionCartasAdhesion {
	
	private long idAsiganacionCA;
	private String folioCartaAdhesion;
	private Integer idEstado;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double volumen;
	private Double importe;
	
	@Id
	@GeneratedValue(generator = "asignacion_cartas_adhesion_id_asignacion_ca_seq")
	@SequenceGenerator(name = "asignacion_cartas_adhesion_id_asignacion_ca_seq", sequenceName = "asignacion_cartas_adhesion_id_asignacion_ca_seq")
	@Column(name = "id_asignacion_ca")
	public long getIdAsiganacionCA() {
		return idAsiganacionCA;
	}
	public void setIdAsiganacionCA(long idAsiganacionCA) {
		this.idAsiganacionCA = idAsiganacionCA;
	}
	
	@Column(name = "folio_carta_adhesion")	
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name = "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	
	@Column(name = "volumen")	
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name = "importe")
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
  
}
