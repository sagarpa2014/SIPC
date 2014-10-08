package mx.gob.comer.sipc.domain.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_uso_bodega")
public class BodegaUso {

	@Id
	@Column(name = "id_uso")
	private char clave;	
	@Column(name = "tipo_uso")
	private String tipoBodegaUso;
	@Column(name = "descripcion")
	private String descripcionUso;
		
	public char getClave() {
		return clave;
	}
	public void setClave(char clave) {
		this.clave = clave;
	}
	public String getTipoBodegaUso() {
		return tipoBodegaUso;
	}
	public void setTipoBodegaUso(String tipoBodegaUso) {
		this.tipoBodegaUso = tipoBodegaUso;
	}
	public String getDescripcionUso() {
		return descripcionUso;
	}
	public void setDescripcionUso(String descripcionUso) {
		this.descripcionUso = descripcionUso;
	}			
}
