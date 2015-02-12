package mx.gob.comer.sipc.domain.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "campos_relacion_certificados")
public class CamposRelacionCertificados {

	private long idCampoRelacionCertificados;
	private Long idCampoRelacion;
	private Integer idComprador;
	private String folio;
	private String folioCartaAdhesion;
	private String claveBodega;
	private Integer idPgrCultivo;
	private long idPgrCiclo;
	private Integer razonSocialAlmacen;
	private String descripcion;
	private Long idPerRel;
	
	@Id	
	@Column(name =  "id_campo_relacion_certificados")
	@GeneratedValue(generator = "campos_relacion_certificados_id_campo_relacion_certificados_seq")
	@SequenceGenerator(name = "campos_relacion_certificados_id_campo_relacion_certificados_seq", sequenceName = "campos_relacion_certificados_id_campo_relacion_certificados_seq")
	public long getIdCampoRelacionCertificados() {
		return idCampoRelacionCertificados;
	}
	
	public void setIdCampoRelacionCertificados(long idCampoRelacionCertificados) {
		this.idCampoRelacionCertificados = idCampoRelacionCertificados;
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

	@Column(name = "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}

	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
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

	@Column(name = "razon_social_almacen")
	public Integer getRazonSocialAlmacen() {
		return razonSocialAlmacen;
	}

	public void setRazonSocialAlmacen(Integer razonSocialAlmacen) {
		this.razonSocialAlmacen = razonSocialAlmacen;
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
