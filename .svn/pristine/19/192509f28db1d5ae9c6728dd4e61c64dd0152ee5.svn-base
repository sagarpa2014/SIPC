package mx.gob.comer.sipc.domain;



import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "bitacora")
public class Bitacora {
	private Long id;
	private Integer idUsuario;
	private Integer idEvento;
	private Date fecha;
	private String detalle;
	private String archivo;

	public Bitacora(){
		
	}
	
	
	public Bitacora(Integer idUsuario, Integer idEvento, Date fecha,
			String detalle) {
		super();
		this.idUsuario = idUsuario;
		this.idEvento = idEvento;
		this.fecha = fecha;
		this.detalle = detalle;
		
	}

	@Id
	@GeneratedValue(generator = "bitacora_id_seq")
	@SequenceGenerator(name = "bitacora_id_seq", sequenceName = "bitacora_id_seq")
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "usuario_id")
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}	
	
	@Column(name = "evento_id")
	public Integer  getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	@Column(name = "fecha")
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Column(name = "detalle")
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	@Column(name = "archivo")
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
}
