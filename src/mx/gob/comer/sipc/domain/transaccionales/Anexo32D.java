package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "anexo_32d")
public class Anexo32D {
	
	@Id	
	@Column(name =  "id")
	@GeneratedValue(generator = "anexo_32d_id_seq")
	@SequenceGenerator(name = "anexo_32d_id_seq", sequenceName = "anexo_32d_id_seq")
	private Integer id;
	@Column(name =  "folio_carta_adhesion")
	private String folioCartaAdhesion;
	@Column(name =  "fecha_anexo_32d")
	private Date fechaAnexo32d;
	@Column(name =  "fecha_registro")
	private Date fechaRegistro;
	@Column(name =  "usuario_registro")
	private Integer usuarioRegistro;
	@Column(name =  "nombre_archivo")
	private String nombreArchivo; 
	@Column(name =  "ruta_archivo")
	private String rutaArchivo;	
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	public Date getFechaAnexo32d() {
		return fechaAnexo32d;
	}
	public void setFechaAnexo32d(Date fechaAnexo32d) {
		this.fechaAnexo32d = fechaAnexo32d;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
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
		
}
