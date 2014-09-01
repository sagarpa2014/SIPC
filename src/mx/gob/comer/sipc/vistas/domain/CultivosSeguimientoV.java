package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cultivos_seguimiento_v")
public class CultivosSeguimientoV {
	
	private long idCultivo;
	private String nombreCultivo;
	
	@Id
	@Column(name =  "id_cultivo")
	public long getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(long idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	@Column(name =  "nombre_cultivo")
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}	
}
