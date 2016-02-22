package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avisos_dof_v")
public class AvisosDofV {
	
	@Id	
	@Column(name =  "clave_aviso")
	private String claveAviso;
	@Column(name =  "leyenda")
	private String leyenda;
	@Column(name =  "apoyo")
	private String apoyo;
	@Column(name =  "fecha_publicacion") 
	private Date fechaPublicacion;
	
	public String getClaveAviso() {
		return claveAviso;
	}
	public void setClaveAviso(String claveAviso) {
		this.claveAviso = claveAviso;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}		
	
	public String getApoyo() {
		return apoyo;
	}
	public void setApoyo(String apoyo) {
		this.apoyo = apoyo;
	}
	
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
		
}