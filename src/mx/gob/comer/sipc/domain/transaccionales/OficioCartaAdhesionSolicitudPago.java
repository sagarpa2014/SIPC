package mx.gob.comer.sipc.domain.transaccionales;

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

@Entity
@Table(name = "oficio_ca_solicitud_pago")
public class OficioCartaAdhesionSolicitudPago {
	
	private long idOficioCASP;
	private String  noOficioCA;
	private Integer idPrograma;
	private String  nomArchivoCA; 
	private Date  fechaDocCA; 
	private Date  fechaAcuseCA; 
	private Date  fechaCreacion; 
	private Integer usuarioCreacion;
	private Date  fechaModificacion;
	private Integer usuarioModificacion;
	private Set<AsignacionCartaEspecialista> asignacionCartaEspecialista;
	
	
	@Id	
	@Column(name =  "id_oficio_ca_sp")
	@GeneratedValue(generator = "oficio_ca_solicitud_pago_id_oficio_ca_sp_seq")
	@SequenceGenerator(name = "oficio_ca_solicitud_pago_id_oficio_ca_sp_seq", sequenceName = "oficio_ca_solicitud_pago_id_oficio_ca_sp_seq")
	public long getIdOficioCASP() {
		return idOficioCASP;
	}
	public void setIdOficioCASP(long idOficioCASP) {
		this.idOficioCASP = idOficioCASP;
	}
	
	@Column(name =  "no_oficio_ca")
	public String getNoOficioCA() {
		return noOficioCA;
	}
	public void setNoOficioCA(String noOficioCA) {
		this.noOficioCA = noOficioCA;
	}
		
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	@Column(name =  "nom_archivo_ca")
	public String getNomArchivoCA() {
		return nomArchivoCA;
	}
	public void setNomArchivoCA(String nomArchivoCA) {
		this.nomArchivoCA = nomArchivoCA;
	}
	
	@Column(name =  "fecha_doc_ca")
	public Date getFechaDocCA() {
		return fechaDocCA;
	}
	public void setFechaDocCA(Date fechaDocCA) {
		this.fechaDocCA = fechaDocCA;
	}
	
	@Column(name =  "fecha_acuse_ca")
	public Date getFechaAcuseCA() {
		return fechaAcuseCA;
	}
	public void setFechaAcuseCA(Date fechaAcuseCA) {
		this.fechaAcuseCA = fechaAcuseCA;
	}
	
	@Column(name =  "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name =  "usuario_creacion")
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	@Column(name =  "fecha_modificacion")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	@Column(name =  "usuario_modificacion")
	public Integer getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(Integer usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.LAZY)	
	@JoinColumn(name="id_oficio_ca_sp", nullable=false)
	public Set<AsignacionCartaEspecialista> getAsignacionCartaEspecialista() {
		return asignacionCartaEspecialista;
	}
	public void setAsignacionCartaEspecialista(
			Set<AsignacionCartaEspecialista> asignacionCartaEspecialista) {
		this.asignacionCartaEspecialista = asignacionCartaEspecialista;
	}	
	
}
