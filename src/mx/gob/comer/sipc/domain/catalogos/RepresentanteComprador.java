package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "representante_comprador")
public class RepresentanteComprador{
	/**
	 * 
	 */
	private Integer idRepComp;
	private Integer idRepresentante;
	private Integer idComprador;
	private Integer estatus;
	
	@Id
	@Column(name = "id_rep_comp")
	@GeneratedValue(generator = "representante_comprador_id_rep_comp_seq")
	@SequenceGenerator(name = "representante_comprador_id_rep_comp_seq", sequenceName = "representante_comprador_id_rep_comp_seq")
	public Integer getIdRepComp() {
		return idRepComp;
	}
	public void setIdRepComp(Integer idRepComp) {
		this.idRepComp = idRepComp;
	}
	
	@Column(name = "id_representante")
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	@Column(name = "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name = "estatus")
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	

}