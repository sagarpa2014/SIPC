package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "campos_relacion_ventas")
public class CamposRelacionVentas {

	private long idCampoRelacionVentas;
	private Long idCampoRelacion;
	private Integer idComprador;
	private String folio;
	private String folioCartaAdhesion;
	private Integer idPgrCultivo;
	private long idPgrCiclo;
	private String descripcion;
	private Long idPerRel;
	
	@Id	
	@Column(name =  "id_campo_relacion_ventas")
	@GeneratedValue(generator = "campos_relacion_ventas_id_campo_relacion_ventas_seq")
	@SequenceGenerator(name = "campos_relacion_ventas_id_campo_relacion_ventas_seq", sequenceName = "campos_relacion_ventas_id_campo_relacion_ventas_seq")
	public long getIdCampoRelacionVentas() {
		return idCampoRelacionVentas;
	}
	
	public void setIdCampoRelacionVentas(long idCampoRelacionVentas) {
		this.idCampoRelacionVentas = idCampoRelacionVentas;
	}
	
	
	@Column(name = "id_campo_relacion")
	public Long getIdCampoRelacion() {
		return idCampoRelacion;
	}

	public void setIdCampoRelacion(Long idCampoRelacion) {
		this.idCampoRelacion = idCampoRelacion;
	}

	@Column(name = "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
			
	@Column(name =  "folio")
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}	

	@Column(name = "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name = "id_pgr_cultivo")
	public Integer getIdPgrCultivo() {
		return idPgrCultivo;
	}
	public void setIdPgrCultivo(Integer idPgrCultivo) {
		this.idPgrCultivo = idPgrCultivo;
	}
	
	@Column(name =  "id_pgr_ciclo")
	public long getIdPgrCiclo() {
		return idPgrCiclo;
	}

	public void setIdPgrCiclo(long idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
	}
	
	@Column(name =  "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}
}
