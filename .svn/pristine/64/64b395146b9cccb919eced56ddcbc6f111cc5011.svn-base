package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificaciones_v")
public class NotificacionesV
{
	private Integer id;
	private Integer usuarioId;
	private Integer eventoId;
	private boolean aviso;
	private boolean correo;
	private String correoElectronico;


	@Id
	@Column(name = "id")
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "id_usuario")
	public Integer getUsuarioId()
	{
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId)
	{
		this.usuarioId = usuarioId;
	}

	@Column(name = "evento_id")
	public Integer getEventoId() {
		return eventoId;
	}
	public void setEventoId(Integer eventoId) {
		this.eventoId = eventoId;
	}
	
	@Column(name = "aviso")
	public boolean isAviso()
	{
		return aviso;
	}
	public void setAviso(boolean aviso)
	{
		this.aviso = aviso;
	}

	@Column(name = "correo")
	public boolean isCorreo()
	{
		return correo;
	}
	public void setCorreo(boolean correo)
	{
		this.correo = correo;
	}


	@Column(name = "correo_electronico")
	public String getCorreoElectronico()
	{
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico)
	{
		this.correoElectronico = correoElectronico;
	}
	
	
}
