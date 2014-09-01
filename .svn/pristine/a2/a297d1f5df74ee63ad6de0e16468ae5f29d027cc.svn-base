package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_asignaciones")
public class DetalleAsignacionCartasAdhesion {
	
	private long idDetalleAsignacion;
	private String folioCartaAdhesion;
	private Integer idEstado;
	private Integer idCultivo;
	private Integer idVariedad;
	private Double volumen;
	private Double importe;
	private Integer tipo;
	private Double cuota;
	private String claveBodega;
	private Date fechaCreacion;
	private Integer idPrograma;
	
	@Id
	@GeneratedValue(generator = "detalle_asignaciones_id_detalle_asignacion_seq")
	@SequenceGenerator(name = "detalle_asignaciones_id_detalle_asignacion_seq", sequenceName = "detalle_asignaciones_id_detalle_asignacion_seq")
	@Column(name = "id_detalle_asignacion")
	public long getIdDetalleAsignacion() {
		return idDetalleAsignacion;
	}
	public void setIdDetalleAsignacion(long idDetalleAsignacion) {
		this.idDetalleAsignacion = idDetalleAsignacion;
	}
	
	@Column(name = "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	@Column(name = "id_estado")
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "id_cultivo")
	public Integer getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name = "id_variedad")
	public Integer getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}
	
	@Column(name = "volumen")	
	public Double getVolumen() {
		return volumen;
	}
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	@Column(name = "importe")
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	@Column(name = "tipo")
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "cuota")
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	
	@Column(name = "clave_bodega")
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	@Column(name = "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name = "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
}
