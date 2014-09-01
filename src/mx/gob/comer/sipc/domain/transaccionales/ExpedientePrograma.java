package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "expedientes_programas")
public class ExpedientePrograma {
	
	private Long idExpedientePrograma;
	private Integer idPrograma;
	private Integer idExpediente;
	private boolean documentacionOpcional;
	
	
	@Id
	@GeneratedValue(generator = "expedientes_programas_id_expediente_programa_seq")
	@SequenceGenerator(name = "expedientes_programas_id_expediente_programa_seq", sequenceName = "expedientes_programas_id_expediente_programa_seq")
	@Column(name = "id_expediente_programa")
	public Long getIdExpedientePrograma() {
		return idExpedientePrograma;
	}
	public void setIdExpedientePrograma(Long idExpedientePrograma) {
		this.idExpedientePrograma = idExpedientePrograma;
	}
	
	@Column(name = "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	@Column(name = "id_expediente")
	public Integer getIdExpediente() {
		return idExpediente;
	}
	
	public void setIdExpediente(Integer idExpediente) {
		this.idExpediente = idExpediente;
	}
	
	@Column(name = "documentacion_opcional")
	public boolean isDocumentacionOpcional() {
		return documentacionOpcional;
	}
	public void setDocumentacionOpcional(boolean documentacionOpcional) {
		this.documentacionOpcional = documentacionOpcional;
	}
	

}
