package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "programa_relacion_cultivos")
public class ProgramaRelacionCultivos {
	private Long idPgrRelCultivo;
	private Long idIniEsquemaRelacion;
	private Integer idCultivo;
	

	@Id	
	@Column(name =  "id_pgr_rel_cultivo")
	@GeneratedValue(generator = "programa_relacion_cultivos_id_pgr_rel_cultivo_seq")
	@SequenceGenerator(name = "programa_relacion_cultivos_id_pgr_rel_cultivo_seq", sequenceName = "programa_relacion_cultivos_id_pgr_rel_cultivo_seq")
	public Long getIdPgrRelCultivo() {
		return idPgrRelCultivo;
	}	
	
	public void setIdPgrRelCultivo(Long idPgrRelCultivo) {
		this.idPgrRelCultivo = idPgrRelCultivo;
	}
	
		
	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}
	
	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
	
	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}

		
}
