package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "compras_datos_participante")
public class ComprasDatosParticipante {
	
	private  long idCompPer;
	private  Integer idComprador;
	private  long idPerRel;
	private  String folioCartaAdhesion;
	private  String claveBodega;
	private  Integer estadoAcopio;
	private  Integer idCultivo;
	private  long idPgrCiclo;
	private Integer usuarioRegistro;
	private Date fechaRegistro;
	private Integer usuarioActualiza;
	private Date fechaActualiza;
	private Integer idEstatusComprasDatosPar;
	
	
	@Id	
	@Column(name =  "id_comp_per")
	@GeneratedValue(generator = "compras_datos_participante_id_comp_per_seq")
	@SequenceGenerator(name = "compras_datos_participante_id_comp_per_seq", sequenceName = "compras_datos_participante_id_comp_per_seq")
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
	public long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(long idPerRel) {
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
	
	@Column(name =  "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "id_pgr_ciclo")
	public long getIdPgrCiclo() {
		return idPgrCiclo;
	}
	public void setIdPgrCiclo(long idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
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
	
	@Column(name = "id_estatus_compras_datos_par")
	public Integer getIdEstatusComprasDatosPar() {
		return idEstatusComprasDatosPar;
	}
	
	public void setIdEstatusComprasDatosPar(Integer idEstatusComprasDatosPar) {
		this.idEstatusComprasDatosPar = idEstatusComprasDatosPar;
	}	
}