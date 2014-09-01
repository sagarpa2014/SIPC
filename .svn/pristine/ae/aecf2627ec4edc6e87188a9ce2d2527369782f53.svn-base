package mx.gob.comer.sipc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aviso")
public class Aviso{
	protected Long 		idAviso;
	protected int 		idUsuario;
	protected Date 		fecha;
	protected int 		de;
	protected int 		para;
	protected boolean 	habilitar;
	protected String 	mensaje;
	protected Long 		instruccionPagoId;
	protected boolean	automatico;
	
	public Aviso(Aviso copia){
		this.idAviso = copia.getIdAviso();
		this.idUsuario = copia.getIdUsuario();
		this.fecha = copia.getFecha();
		this.de = copia.getDe();
		this.para = copia.getPara();
		this.habilitar = copia.isHabilitar();
		this.mensaje = copia.getMensaje();
	}
	
	public Aviso(){
		
	}
	
	@Id
	@GeneratedValue(generator = "aviso_id_aviso_seq")
	@SequenceGenerator(name = "aviso_id_aviso_seq", sequenceName = "aviso_id_aviso_seq")
	@Column(name = "id_aviso")
	public Long getIdAviso(){
		return idAviso;
	}
	public void setIdAviso(Long idAviso){
		this.idAviso = idAviso;
	}
	
	@Column(name = "id_usuario")
	public int getIdUsuario(){
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario){
		this.idUsuario = idUsuario;
	}
	
	@Column(name = "fecha")
	public Date getFecha(){
		return fecha;
	}
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	
	@Column(name = "de")
	public int getDe(){
		return de;
	}
	public void setDe(int de){
		this.de = de;
	}
	
	@Column(name = "para")
	public int getPara(){
		return para;
	}
	public void setPara(int para){
		this.para = para;
	}
	
	@Column(name = "habilitar")
	public boolean isHabilitar(){
		return habilitar;
	}
	public void setHabilitar(boolean habilitar){
		this.habilitar = habilitar;
	}
	
	@Column(name = "mensaje")
	public String getMensaje(){
		return mensaje;
	}
	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}
	
	@Column(name = "automatico")
	public boolean getAutomatico(){
		return automatico;
	}
	public void setAutomatico(boolean automatico){
		this.automatico = automatico;
	}

}
