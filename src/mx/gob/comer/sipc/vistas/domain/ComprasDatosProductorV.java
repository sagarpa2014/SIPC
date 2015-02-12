package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_comp_productor_v")
public class  ComprasDatosProductorV{
	
	private  long idCompProd;
	private long idCompPer;
	private Long  folioProductor;
	private Integer numProdBen;
	private Long idPerRel;
	private String nombre;
	private String paterno;
	private String materno;
	private String rfc; 
	private String curp;
	private String tipoPersona;
	private String ccpRfc;
	private String ccpCurp;
	
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
	
	@Column(name =  "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name =  "paterno")
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	
	@Column(name =  "materno")
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
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
	
	@Column(name =  "tipo_persona")
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	@Column(name =  "ccp_rfc")
	public String getCcpRfc() {
		return ccpRfc;
	}
	public void setCcpRfc(String ccpRfc) {
		this.ccpRfc = ccpRfc;
	}
	
	@Column(name =  "ccp_curp")
	public String getCcpCurp() {
		return ccpCurp;
	}
	public void setCcpCurp(String ccpCurp) {
		this.ccpCurp = ccpCurp;
	}		
}