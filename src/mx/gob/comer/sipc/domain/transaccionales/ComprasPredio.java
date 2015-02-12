package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_predio")
public class  ComprasPredio{
	
	private  long idPredio;
	private  long idCompProd;;
	private  String folioPredio;
	private  Integer folioPredioSec;
	private  Long idPerRel;
	private  Double superficie;
	private  Integer idModalidad;
	private Integer usuarioRegistro;
	private Date fechaRegistro;
	private Integer usuarioActualiza;
	private Date fechaActualiza;
	private String predioAlterno;
	
	public ComprasPredio(){
        
	}	
	public ComprasPredio(String folioPredio) {
		super();
		this.folioPredio = folioPredio;
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
	public Integer getFolioPredioSec() {
		return folioPredioSec;
	}

	public void setFolioPredioSec(Integer folioPredioSec) {
		this.folioPredioSec = folioPredioSec;
	}

	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}

	@Column(name =  "superficie")
	public Double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}
	
	@Column(name =  "id_modalidad")
	public Integer getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(Integer idModalidad) {
		this.idModalidad = idModalidad;
	}	
	
	@Column(name = "usuario_registro")
	public Integer getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
	@Column(name = "fecha_registro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name = "usuario_actualiza")
	public Integer getUsuarioActualiza() {
		return usuarioActualiza;
	}
	public void setUsuarioActualiza(Integer usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	
	@Column(name = "fecha_actualiza")
	public Date getFechaActualiza() {
		return fechaActualiza;
	}
	public void setFechaActualiza(Date fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}
	
	@Column(name = "predio_alterno")
	public String getPredioAlterno() {
		return predioAlterno;
	}
	public void setPredioAlterno(String predioAlterno) {
		this.predioAlterno = predioAlterno;
	}

}