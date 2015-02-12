package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_comp_productor")
public class  ComprasDatosProductor{
	
	private  long idCompProd;
	private long idCompPer;
	private Long  folioProductor;
	private String rfc; 
	private String curp; 
	private Integer numProdBen;
	private Long idPerRel;
	private Integer usuarioRegistro;
	private Date fechaRegistro;
	private Integer usuarioActualiza;
	private Date fechaActualiza;
	private Integer estatus; 
	
	
	@Id	
	@Column(name =  "id_comp_prod")
	@GeneratedValue(generator = "compras_comp_productor_id_comp_prod_seq")
	@SequenceGenerator(name = "compras_comp_productor_id_comp_prod_seq", sequenceName = "compras_comp_productor_id_comp_prod_seq")
	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	@Column(name =  "id_comp_per")
	public long getIdCompPer() {
		return idCompPer;
	}
	public void setIdCompPer(long idCompPer) {
		this.idCompPer = idCompPer;
	}
	
	@Column(name =  "folio_productor")
	public Long getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(Long folioProductor) {
		this.folioProductor = folioProductor;
	}
	
	@Column(name =  "rfc")
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	@Column(name =  "curp")
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	@Column(name =  "num_prod_ben")
	public Integer getNumProdBen() {
		return numProdBen;
	}
	public void setNumProdBen(Integer numProdBen) {
		this.numProdBen = numProdBen;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
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
	
	@Column(name = "id_estatus_compras_prod")
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	
}