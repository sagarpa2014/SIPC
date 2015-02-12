package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "campos_relacion_terrestre")
public class CamposRelacionTerrestre {

	private long idCampoRelacionTerrestre;
	private Long idCampoRelacion;
	private Integer idComprador;
	private String folio;
	private Integer folioCartaAdhesion;
	private String claveBodegaOpp;
	private Integer estadoBodega;
	private Integer idPgrCultivo;
	private long idPgrCiclo;
	private String descripcion;
	private Long idPerRel;
	private Integer tipoTramo;
	
	@Id	
	@Column(name =  "id_campo_relacion_terrestre")
	@GeneratedValue(generator = "campos_relacion_terrestre_id_campo_relacion_terrestre_seq")
	@SequenceGenerator(name = "campos_relacion_terrestre_id_campo_relacion_terrestre_seq", sequenceName = "campos_relacion_terrestre_id_campo_relacion_terrestre_seq")
	public long getIdCampoRelacionTerrestre() {
		return idCampoRelacionTerrestre;
	}
	
	public void setIdCampoRelacionTerrestre(long idCampoRelacionTerrestre) {
		this.idCampoRelacionTerrestre = idCampoRelacionTerrestre;
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
	
	@Column(name =  "folio_carta_adhesion")
	public Integer getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	
	public void setFolioCartaAdhesion(Integer folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	@Column(name =  "clave_bodega_o_pp")
	public String getClaveBodegaOpp() {
		return claveBodegaOpp;
	}

	public void setClaveBodegaOpp(String claveBodegaOpp) {
		this.claveBodegaOpp = claveBodegaOpp;
	}	
	
	@Column(name = "estado_bodega")
	public Integer getEstadoBodega() {
		return estadoBodega;
	}

	
	public void setEstadoBodega(Integer estadoBodega) {
		this.estadoBodega = estadoBodega;
	}
	
	@Column(name =  "id_pgr_cultivo")
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

	
	@Column(name =  "tipo_tramo")
	public Integer getTipoTramo() {
		return tipoTramo;
	}

	public void setTipoTramo(Integer tipoTramo) {
		this.tipoTramo = tipoTramo;
	}
	
}
