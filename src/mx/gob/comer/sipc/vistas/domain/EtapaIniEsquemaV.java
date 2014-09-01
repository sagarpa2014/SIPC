package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "etapa_ini_esquema_v")
public class EtapaIniEsquemaV {
	
	private long idEtapaIniEsquema;
	private Integer idEtapa;
	private Integer idPrograma;
	private Double monto;
	private String etapa;
	
	public EtapaIniEsquemaV() {
		
	}
	public EtapaIniEsquemaV(String etapa) {
		super();
		this.etapa = etapa;
	}
	@Id	
	@Column(name =  "id_etapa_ini_esquema")
	@GeneratedValue(generator = "etapa_ini_esquema_id_etapa_ini_esquema_seq")
	@SequenceGenerator(name = "etapa_ini_esquema_id_etapa_ini_esquema_seq", sequenceName = "etapa_ini_esquema_id_etapa_ini_esquema_seq")
	public long getIdEtapaIniEsquema() {
		return idEtapaIniEsquema;
	}
	public void setIdEtapaIniEsquema(long idEtapaIniEsquema) {
		this.idEtapaIniEsquema = idEtapaIniEsquema;
	}
	
	@Column(name =  "id_etapa")
	public Integer getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}
	
	@Column(name =  "id_programa")
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	

	@Column(name =  "monto")
	public Double getMonto() {
		return monto;
	}
	
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	@Column(name =  "etapa")
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	

}
