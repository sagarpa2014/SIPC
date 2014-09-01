package mx.gob.comer.sipc.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "programa_comprador")
public class ProgramaComprador{
	/**
	 * 
	 */
	private Integer idProgComprador;
	private int idPrograma;
	private int idComprador;
	private String noCarta;
	
	
	@Id
	@GeneratedValue(generator = "programa_comprador_id_prog_comprador_seq")
	@SequenceGenerator(name = "programa_comprador_id_prog_comprador_seq", sequenceName = "programa_comprador_id_prog_comprador_seq")
	@Column(name =  "id_prog_comprador")
	public Integer getIdProgComprador() {
		return idProgComprador;
	}
	public void setIdProgComprador(Integer idProgComprador) {
		this.idProgComprador = idProgComprador;
	}
	
	@Column(name =  "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "id_comprador")
	public int getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name =  "no_carta")
	public String getNoCarta() {
		return noCarta;
	}
	public void setNoCarta(String noCarta) {
		this.noCarta = noCarta;
	}
	
}
	
