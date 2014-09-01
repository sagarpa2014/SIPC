package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programa_cartas_v")
public class ProgramaCartasV{
	
	private String id;
	private int idPrograma;
	private String programa;
	private int idComprador;
	private String comprador;
	private String carta;
	private Integer idEspecialista;
	
	@Id
	@Column(name =  "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	@Column(name =  "programa")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	@Column(name =  "nombre_comprador")
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	
	@Column(name =  "folio_carta_adhesion")
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}

	@Column(name =  "id_especialista")
	public Integer getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(Integer idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
}