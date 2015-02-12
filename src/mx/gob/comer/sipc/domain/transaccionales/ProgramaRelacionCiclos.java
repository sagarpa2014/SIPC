package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "programa_relacion_ciclos")
public class ProgramaRelacionCiclos {
	
	private long idPgrCiclo;
	private Long idIniEsquemaRelacion;
	private Integer idCiclo;
	private Integer ejercicio;
	
	
	@Id	
	@Column(name =  "id_pgr_ciclo")
	@GeneratedValue(generator = "programa_relacion_ciclos_id_pgr_ciclo_seq")
	@SequenceGenerator(name = "programa_relacion_ciclos_id_pgr_ciclo_seq", sequenceName = "programa_relacion_ciclos_id_pgr_ciclo_seq")
	public long getIdPgrCiclo() {
		return idPgrCiclo;
	}
	public void setIdPgrCiclo(long idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
	}
	
	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}
	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
	
	@Column(name =  "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}
	
	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name =  "ejercicio")	
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}	
}
