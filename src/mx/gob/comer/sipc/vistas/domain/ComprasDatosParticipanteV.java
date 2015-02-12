package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "compras_datos_participante_v")
public class ComprasDatosParticipanteV {
	
	private  long idCompPer;
	private  Integer idComprador;
	private  Long idPerRel;
	private  String folioCartaAdhesion;
	private  String claveBodega;
	private  Integer estadoAcopio;
	private  String estado;
	private  Integer idPgrCultivo;
	private  long idPgrCiclo;
	private  long idRelacion;
	private Integer idCultivo;
	private  String cultivo;
	private Integer idCiclo;
	private  String cicloLargo;
	private  Integer ejercicio;
	private Integer idEstatusComprasDatosPar;

	
	@Id	
	@Column(name =  "id_comp_per")
	public long getIdCompPer() {
		return idCompPer;
	}
	public void setIdCompPer(long idCompPer) {
		this.idCompPer = idCompPer;
	}
	
	@Column(name =  "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}
	
	@Column(name =  "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	@Column(name =  "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	@Column(name =  "estado_acopio")
	public Integer getEstadoAcopio() {
		return estadoAcopio;
	}
	public void setEstadoAcopio(Integer estadoAcopio) {
		this.estadoAcopio = estadoAcopio;
	}
	
	@Column(name =  "estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	
	@Column(name =  "id_tipo_relacion")
	public long getIdRelacion() {
		return idRelacion;
	}
	public void setIdRelacion(long idRelacion) {
		this.idRelacion = idRelacion;
	}

	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	@Column(name =  "cultivo")
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	@Column(name =  "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}
	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	@Column(name =  "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}
	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}
	
	@Column(name =  "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	@Column(name = "id_estatus_compras_datos_par")
	public Integer getIdEstatusComprasDatosPar() {
		return idEstatusComprasDatosPar;
	}
	
	public void setIdEstatusComprasDatosPar(Integer idEstatusComprasDatosPar) {
		this.idEstatusComprasDatosPar = idEstatusComprasDatosPar;
	}
	
}