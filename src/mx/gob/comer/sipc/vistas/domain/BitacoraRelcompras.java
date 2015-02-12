package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
*BitacoraRelcompras
*
*Clase que mapea a la tabla "bitacora_relcompras" de la base de datos  
*
*Versión 1.0
*
*Octubre 2014
*
*/

@Entity
@Table(name="bitacora_relcompras")
public class BitacoraRelcompras {
	@Id
	@GeneratedValue(generator = "bitacora_relcompras_id_bitacora_relcompras_seq")
	@SequenceGenerator(name = "bitacora_relcompras_id_bitacora_relcompras_seq", sequenceName = "bitacora_relcompras_id_bitacora_relcompras_seq")
	@Column(name = "id_bitacora_relcompras")
	private Long idBitacoraRelcompras;
	@Column(name = "folio_carta_adhesion")
	private String folioCartaAdhesion;
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	@Column(name = "usuario_registro")
	private Integer usuarioCreacion;
	@Column(name = "status")
	private Integer status;	
	@Column(name = "id_criterio")
	private Integer idCriterio;	
	@Column(name = "nombre_archivo")
	private String nombreArchivo;
	@Column(name = "ruta_archivo")
	private String rutaArchivo;
	@Column(name = "mensaje")
	private String mensaje;
	@Column(name = "carga")
	private Boolean carga;	
	@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
	@JoinColumn(name="id_bitacora_relcompras", nullable=false)
	private Set<BitacoraRelcomprasDetalle> bitacoraRelcomprasDetalle;
	

	public BitacoraRelcompras (){
		
	}

	public Long getIdBitacoraRelcompras() {
		return idBitacoraRelcompras;
	}

	public void setIdBitacoraRelcompras(Long idBitacoraRelcompras) {
		this.idBitacoraRelcompras = idBitacoraRelcompras;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIdCriterio() {
		return idCriterio;
	}

	public void setIdCriterio(Integer idCriterio) {
		this.idCriterio = idCriterio;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Boolean getCarga() {
		return carga;
	}

	public void setCarga(Boolean carga) {
		this.carga = carga;
	}

	public Set<BitacoraRelcomprasDetalle> getBitacoraRelcomprasDetalle() {
		return bitacoraRelcomprasDetalle;
	}

	public void setBitacoraRelcomprasDetalle(Set<BitacoraRelcomprasDetalle> bitacoraRelcomprasDetalle) {
		this.bitacoraRelcomprasDetalle = bitacoraRelcomprasDetalle;
	}
		
}
