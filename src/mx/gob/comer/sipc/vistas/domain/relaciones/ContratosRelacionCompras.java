package mx.gob.comer.sipc.vistas.domain.relaciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contratos_relacion_compras")
public class ContratosRelacionCompras {

	@Id
	@Column(name = "folio_contrato")
	private String folioContrato;
	@Column(name = "periodo_inicial_pago")
	private Date periodoInicialPago;
	@Column(name = "periodo_final_pago")
	private Date periodoFinalPago;
	@Column(name = "periodo_inicial_pago_adendum")
	private Date periodoInicialPagoAdendum;
	@Column(name = "periodo_final_pago_adendum")
	private Date periodoFinalPagoAdendum;
	
	public String getFolioContrato() {
		return folioContrato;
	}
	
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}	
	
	public Date getPeriodoInicialPago() {
		return periodoInicialPago;
	}
	public void setPeriodoInicialPago(Date periodoInicialPago) {
		this.periodoInicialPago = periodoInicialPago;
	}
	public Date getPeriodoFinalPago() {
		return periodoFinalPago;
	}
	public void setPeriodoFinalPago(Date periodoFinalPago) {
		this.periodoFinalPago = periodoFinalPago;
	}
	public Date getPeriodoInicialPagoAdendum() {
		return periodoInicialPagoAdendum;
	}
	public void setPeriodoInicialPagoAdendum(Date periodoInicialPagoAdendum) {
		this.periodoInicialPagoAdendum = periodoInicialPagoAdendum;
	}
	public Date getPeriodoFinalPagoAdendum() {
		return periodoFinalPagoAdendum;
	}
	public void setPeriodoFinalPagoAdendum(Date periodoFinalPagoAdendum) {
		this.periodoFinalPagoAdendum = periodoFinalPagoAdendum;
	}	
}
