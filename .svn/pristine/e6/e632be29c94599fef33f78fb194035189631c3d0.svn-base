package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "programas_estados")
public class ProgramaEstado {
	
	private long idPgrEdo;
	private Integer idPrograma;
	private Integer idEstado;
		
	@Id	
	@GeneratedValue(generator = "programas_estados_id_pgr_edo_seq")
	@SequenceGenerator(name = "programas_estados_id_pgr_edo_seq", sequenceName = "programas_estados_id_pgr_edo_seq")
	@Column(name =  "id_pgr_edo")
	
	public long getIdPgrEdo() {
		return idPgrEdo;
	}
	public void setIdPgrEdo(long idPgrEdo) {
		this.idPgrEdo = idPgrEdo;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
}
