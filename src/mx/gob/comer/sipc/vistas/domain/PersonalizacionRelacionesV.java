package mx.gob.comer.sipc.vistas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "personalizacion_relaciones_v")
public class PersonalizacionRelacionesV {
	
	private Long idPerRel; 
	private Integer idTipoRelacion; 
	private String  relacion; 
	private String cicloAgricola;
	private String cultivos;
	private Long idIniEsquemaRelacion;
	private String nombreEsquema;	
	private Date fechaCreacion;
	private Integer relCompras;
	private Integer relCertificadoDeposito;
	private Integer relMaritima;
	private Integer relTerrestre;
	private Integer relVentas;
	
	
	@Id
	@Column(name =  "id_per_rel")
	public Long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}
	
	@Column(name =  "id_tipo_relacion")
	public Integer getIdTipoRelacion() {
		return idTipoRelacion;
	}
	public void setIdTipoRelacion(Integer idTipoRelacion) {
		this.idTipoRelacion = idTipoRelacion;
	}
	
	@Column(name =  "relacion")
	public String getRelacion() {
		return relacion;
	}
	public void setRelacion(String relacion) {
		this.relacion = relacion;
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
		
	@Column(name =  "id_ini_esquema_relacion")
	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}
	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
		
	@Column(name ="fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
		
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name ="rel_compras")
	public Integer getRelCompras() {
		return relCompras;
	}
	public void setRelCompras(Integer relCompras) {
		this.relCompras = relCompras;
	}
	@Column(name =  "rel_certificado_deposito")	
	public Integer getRelCertificadoDeposito() {
		return relCertificadoDeposito;
	}
	public void setRelCertificadoDeposito(Integer relCertificadoDeposito) {
		this.relCertificadoDeposito = relCertificadoDeposito;
	}
	
	@Column(name =  "rel_maritima")
	public Integer getRelMaritima() {
		return relMaritima;
	}
	
	public void setRelMaritima(Integer relMaritima) {
		this.relMaritima = relMaritima;
	}
	
	@Column(name =  "rel_terrestre")
	public Integer getRelTerrestre() {
		return relTerrestre;
	}
	public void setRelTerrestre(Integer relTerrestre) {
		this.relTerrestre = relTerrestre;
	}
	
	@Column(name =  "rel_ventas")
	public Integer getRelVentas() {
		return relVentas;
	}
	public void setRelVentas(Integer relVentas) {
		this.relVentas = relVentas;
	}
	
	@Column(name =  "nombre_esquema")
	public String getNombreEsquema() {
		return nombreEsquema;
	}
	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}	
	
	
	
}
