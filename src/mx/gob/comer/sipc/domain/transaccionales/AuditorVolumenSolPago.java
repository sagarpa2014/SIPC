/**
 * 
 */
package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author luz.corona
 *
 */

@Entity
@Table(name = "auditor_volumen_sol_pago")
public class AuditorVolumenSolPago {
	@Id	
	@GeneratedValue(generator = "auditor_volumen_sol_pago_id_seq")
	@SequenceGenerator(name = "auditor_volumen_sol_pago_id_seq", sequenceName = "auditor_volumen_sol_pago_id_seq")
	@Column(name =  "id")
	private int id;
	@Column(name =  "id_auditor_sol_pago")
	private Long idAuditorSolPago;
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "volumen_auditor")
	private Double volumenAuditor;
	@Column(name =  "id_estado")
	private Integer idEstado;
	@Column(name =  "id_pais")
	private Integer idPais;
	private Boolean internacional;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Long getIdAuditorSolPago() {
		return idAuditorSolPago;
	}
	public void setIdAuditorSolPago(Long idAuditorSolPago) {
		this.idAuditorSolPago = idAuditorSolPago;
	}
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public Double getVolumenAuditor() {
		return volumenAuditor;
	}
	public void setVolumenAuditor(Double volumenAuditor) {
		this.volumenAuditor = volumenAuditor;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public Boolean getInternacional() {
		return internacional;
	}
	public void setInternacional(Boolean internacional) {
		this.internacional = internacional;
	}
	
}
