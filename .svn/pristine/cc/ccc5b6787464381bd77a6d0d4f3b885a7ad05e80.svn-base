package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "alcance_solicitud_inscripcion")
public class AlcanceSolicitudInscripcion {

	private Long idAlcanceSI;
	private Long idSI;
	private Date fechaDocAlcance; 
	private Date fechaAcuseAlcance; 
	private String noOficioAlcance;
	private String nomArchivoAlcance;
	private String rutaArchivo;
	
	@Id	
	@GeneratedValue(generator = "alcance_solicitud_inscripcion_id_alcance_si_seq")
	@SequenceGenerator(name = "alcance_solicitud_inscripcion_id_alcance_si_seq", sequenceName = "alcance_solicitud_inscripcion_id_alcance_si_seq")
	@Column(name =  "id_alcance_si")
	public Long getIdAlcanceSI() {
		return idAlcanceSI;
	}
	public void setIdAlcanceSI(Long idAlcanceSI) {
		this.idAlcanceSI = idAlcanceSI;
	}

	@Column(name =  "id_si")
	public Long getIdSI() {
		return idSI;
	}
	public void setIdSI(Long idSI) {
		this.idSI = idSI;
	}
	
	@Column(name =  "fecha_doc_alcance")
	public Date getFechaDocAlcance() {
		return fechaDocAlcance;
	}
	public void setFechaDocAlcance(Date fechaDocAlcance) {
		this.fechaDocAlcance = fechaDocAlcance;
	}
	
	@Column(name =  "fecha_acuse_alcance")
	public Date getFechaAcuseAlcance() {
		return fechaAcuseAlcance;
	}
	public void setFechaAcuseAlcance(Date fechaAcuseAlcance) {
		this.fechaAcuseAlcance = fechaAcuseAlcance;
	}
	
	@Column(name =  "no_oficio_alcance")
	public String getNoOficioAlcance() {
		return noOficioAlcance;
	}
	public void setNoOficioAlcance(String noOficioAlcance) {
		this.noOficioAlcance = noOficioAlcance;
	}
	
	@Column(name =  "nom_archivo_alcance")
	public String getNomArchivoAlcance() {
		return nomArchivoAlcance;
	}
	public void setNomArchivoAlcance(String nomArchivoAlcance) {
		this.nomArchivoAlcance = nomArchivoAlcance;
	}
	 
	
	@Column(name =  "ruta_archivo")
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	} 
	
	
	
	
	
}
