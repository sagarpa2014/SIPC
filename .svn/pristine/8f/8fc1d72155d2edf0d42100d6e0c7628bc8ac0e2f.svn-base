package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "almacen_general_deposito")
public class AlmacenGeneralDeposito {
	
	private int idAlmacen;
	private String nombre;
	private int idEstado;
	private String direccion;
	private String codigoPostal;
	private String telefonos;
	
	
	@Id
	@GeneratedValue(generator = "almacen_general_deposito_id_almacen_seq")
	@SequenceGenerator(name = "almacen_general_deposito_id_almacen_seq", sequenceName = "almacen_general_deposito_id_almacen_seq")	
	@Column(name =  "id_almacen")
	public int getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	
	@Column(name =  "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name =  "id_estado")	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	@Column(name =  "direccion")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Column(name =  "codigo_postal")
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Column(name =  "telefonos")
	public String getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
	

}
