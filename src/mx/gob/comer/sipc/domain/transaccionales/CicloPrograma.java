package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ciclos_programas")
public class CicloPrograma {
	
	private long idCicloPrograma;
	private Integer idCiclo;
	private Integer idPrograma;
	private Integer idEjercicio;
	
	@Id	
	@Column(name =  "id_ciclo_programa")
	@GeneratedValue(generator = "ciclos_programas_id_ciclo_programa_seq")
	@SequenceGenerator(name = "ciclos_programas_id_ciclo_programa_seq", sequenceName = "ciclos_programas_id_ciclo_programa_seq")
	public long getIdCicloPrograma() {
		return idCicloPrograma;
	}
	public void setIdCicloPrograma(long idCicloPrograma) {
		this.idCicloPrograma = idCicloPrograma;
	}
	
	@Column(name =  "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_ejercicio")
	public Integer getIdEjercicio() {
		return idEjercicio;
	}
	public void setIdEjercicio(Integer idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

}
