package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ini_esquema_relacion")
public class IniEsquemaRelacion {

	private Long idIniEsquemaRelacion;
	private String nombreEsquema;
	private String cicloAgricola;
	private String cultivos;
	private Date fechaCreacion;
	private Date fechaModificacion;
		
	@Id	
	@GeneratedValue(generator = "ini_esquema_relacion_id_ini_esquema_relacion_seq")
	@SequenceGenerator(name = "ini_esquema_relacion_id_ini_esquema_relacion_seq", sequenceName = "ini_esquema_relacion_id_ini_esquema_relacion_seq")
	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}
	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
	
	@Column(name =  "nombre_esquema")	
	public String getNombreEsquema() {
		return nombreEsquema;
	}
	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}
	@Column(name =  "ciclo_agricola")
	public String getCicloAgricola() {
		return cicloAgricola;
	}
	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}
	
	@Column(name =  "cultivos")
	public String getCultivos() {
		return cultivos;
	}
	public void setCultivos(String cultivos) {
		this.cultivos = cultivos;
	}
	
	@Column(name =  "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name =  "fecha_modificacion")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}	
}
