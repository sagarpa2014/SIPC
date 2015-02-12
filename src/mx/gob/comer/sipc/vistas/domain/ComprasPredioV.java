package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_predio_v")
public class  ComprasPredioV{
	
	private  long idPredio;
	private  long idCompProd;;
	private  String folioPredio;
	private  Long folioPredioSec;
	private  String predioAlterno;
	private  Long idPerRel;
	private  Double superficieApoyar;
	private  Double superficieTotal;
	private  Double superficieAcumuladaTotal;
	private  String modalidad;
	
	public ComprasPredioV(){
        
	}
	
	public ComprasPredioV(String folioPredio) {
		super();
		this.folioPredio = folioPredio;
	}

	public ComprasPredioV(String folioPredio, Long folioPredioSec,
			String predioAlterno, Double superficieApoyar,
			Double superficieTotal, Double superficieAcumuladaTotal,
			String modalidad) {
		super();
		this.folioPredio = folioPredio;
		this.folioPredioSec = folioPredioSec;
		this.predioAlterno = predioAlterno;
		this.superficieApoyar = superficieApoyar;
		this.superficieTotal = superficieTotal;
		this.superficieAcumuladaTotal = superficieAcumuladaTotal;
		this.modalidad = modalidad;
	}

	@Id	
	@Column(name =  "id_predio")
	@GeneratedValue(generator = "compras_predio_id_predio_seq") 
	@SequenceGenerator(name = "compras_predio_id_predio_seq", sequenceName = "compras_predio_id_predio_seq")
	public long getIdPredio() {
		return idPredio;
	}
	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
	}
	
	@Column(name =  "id_comp_prod")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "folio_predio")
	public String getFolioPredio() {
		return folioPredio;
	}
	public void setFolioPredio(String folioPredio) {
		this.folioPredio = folioPredio;
	}

	@Column(name =  "folio_predio_sec")
	public Long getFolioPredioSec() {
		return folioPredioSec;
	}

	public void setFolioPredioSec(Long folioPredioSec) {
		this.folioPredioSec = folioPredioSec;
	}
	
	@Column(name =  "predio_alterno")
	public String getPredioAlterno(){
		return predioAlterno;
	}

	public void setPredioAlterno(String predioAlterno) {
		this.predioAlterno = predioAlterno;
	}

	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}

	@Column(name =  "superficie_apoyar")
	public Double getSuperficieApoyar() {
		return superficieApoyar;
	}

	public void setSuperficieApoyar(Double superficieApoyar) {
		this.superficieApoyar = superficieApoyar;
	}	
	
	@Column(name =  "superficie_total")
	public Double getSuperficieTotal() {
		return superficieTotal;
	}
	
	public void setSuperficieTotal(Double superficieTotal) {
		this.superficieTotal = superficieTotal;
	}
	
	@Column(name =  "superficie_acumulada_total")	
	public Double getSuperficieAcumuladaTotal() {
		return superficieAcumuladaTotal;
	}

	public void setSuperficieAcumuladaTotal(Double superficieAcumuladaTotal) {
		this.superficieAcumuladaTotal = superficieAcumuladaTotal;
	}

	@Column(name =  "modalidad")
	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
}