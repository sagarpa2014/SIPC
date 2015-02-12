package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_relacion_cultivos_v")
public class ProgramaRelacionCultivosV{
	
	private long idPgrRelCultivo;
	private Long idPerRel;
	private Integer idCultivo;
	private String nombreCultivo;
	private Long idIniEsquemaRelacion;

	@Id	
	@Column(name =  "id_pgr_rel_cultivo")
	public long getIdPgrRelCultivo() {
		return idPgrRelCultivo;
	}

	public void setIdPgrRelCultivo(long idPgrRelCultivo) {
		this.idPgrRelCultivo = idPgrRelCultivo;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}
	
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}
	
	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "nombre_cultivo")	
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}
	
	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}

	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
}