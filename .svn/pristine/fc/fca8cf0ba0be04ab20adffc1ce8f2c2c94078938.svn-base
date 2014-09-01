package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "observacion_documentacion_sp")
public class ObservacionDocumentacionSP {
	
	private long idObsExpSPCa;
	private Long idExpSPCA;
	private String rutaDocumento;
	private Long idOficioObsSP;
	private Long idOficioRespuesta;
	private Boolean tRespuestaSP;
	
	@Id
	@GeneratedValue(generator = "observacion_documentacion_sp_id_obs_exp_sp_ca_seq")
	@SequenceGenerator(name = "observacion_documentacion_sp_id_obs_exp_sp_ca_seq", sequenceName = "observacion_documentacion_sp_id_obs_exp_sp_ca_seq")
	@Column(name =  "id_obs_exp_sp_ca")	
	public long getIdObsExpSPCa() {
		return idObsExpSPCa;
	}
	public void setIdObsExpSPCa(long idObsExpSPCa) {
		this.idObsExpSPCa = idObsExpSPCa;
	}
	
	@Column(name =  "id_exp_sp_carta_adhesion")
	public Long getIdExpSPCA() {
		return idExpSPCA;
	}
	public void setIdExpSPCA(Long idExpSPCA) {
		this.idExpSPCA = idExpSPCA;
	}
	
	@Column(name =  "ruta_documento")
	public String getRutaDocumento() {
		return rutaDocumento;
	}
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}
	
	@Column(name =  "id_oficio_obs_sol_pago")
	public Long getIdOficioObsSP() {
		return idOficioObsSP;
	}
	public void setIdOficioObsSP(Long idOficioObsSP) {
		this.idOficioObsSP = idOficioObsSP;
	}
	
	@Column(name =  "id_oficio_respuesta")
	public Long getIdOficioRespuesta() {
		return idOficioRespuesta;
	}
	public void setIdOficioRespuesta(Long idOficioRespuesta) {
		this.idOficioRespuesta = idOficioRespuesta;
	}
	
	@Column(name =  "t_respuesta_sp")
	public Boolean gettRespuestaSP() {
		return tRespuestaSP;
	}
	
	public void settRespuestaSP(Boolean tRespuestaSP) {
		this.tRespuestaSP = tRespuestaSP;
	}
		
}
