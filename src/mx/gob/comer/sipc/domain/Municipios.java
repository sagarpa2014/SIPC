package mx.gob.comer.sipc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "municipios")

public class Municipios implements Serializable{
	
	/**
	 * 
	 */
		private int idMunicipio;
		private int idEstado;
		private int claveMunicipio;
		private String nombreMunicipio;
		
		  
		@Id
		@GeneratedValue(generator = "estados_id_municipio_seq")
		@SequenceGenerator(name = "estados_id_municipio_seq", sequenceName = "estados_id_estado_seq")
		@Column(name = "id_municipio")
		public int getIdMunicipio() {
			return idMunicipio;
		}

		public void setIdMunicipio(int idMunicipio) {
			this.idMunicipio = idMunicipio;
		}

		@Column(name = "id_estado")
		public int getIdEstado() {
			return idEstado;
		}
		
		public void setIdEstado(int idEstado) {
			this.idEstado = idEstado;
		}
		
		@Column(name = "clave_municipio")
		public int getClaveMunicipio() {
			return claveMunicipio;
		}

		public void setClaveMunicipio(int claveMunicipio) {
			this.claveMunicipio = claveMunicipio;
		}
		
		@Column(name = "nombre_municipio")
		public String getNombreMunicipio() {
			return nombreMunicipio;
		}
		public void setNombreMunicipio(String nombreMunicipio) {
			this.nombreMunicipio = nombreMunicipio;
		}
		
	}
