package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*Oficios CLC para Pagos TESOFE
*
*Clase que mapea a la tabla "oficios_clc_v" de la base de datos  
*
*Versión 1.0
*
*Enero 2015
*
*/

@Entity
@Table(name="oficios_clc_v")
public class OficiosCLCV {

	private Long id;
	private String noOficio;
	private String folioCLC;
	private Integer estatus;
	private String estatusDesc;
	private int idPrograma;
	private String nombrePgrCorto;
	private int idComprador;
	private String nombreComprador;
	private String rfc;
	private String noCarta;
	private Integer idEspecialista;	
	private String clabe;
	private Double volumen;	
	private Double importe;
	
	public OficiosCLCV (){
		
	}
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "no_oficio")
	public String getNoOficio() {
		return noOficio;
	}

	public void setNoOficio(String noOficio) {
		this.noOficio = noOficio;
	}

	@Column(name = "folio_clc")
	public String getFolioCLC() {
		return folioCLC;
	}

	public void setFolioCLC(String folioCLC) {
		this.folioCLC = folioCLC;
	}
	
	@Column(name = "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name = "id_comprador")
	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}

	@Column(name = "nombre_comprador")
	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}
	
	@Column(name = "rfc_comprador")
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column(name = "clabe")
	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	@Column(name = "volumen")
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	@Column(name = "importe")
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	@Column(name = "estatus_oficio")
	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	@Column(name = "estatus_oficio_desc")
	public String getEstatusDesc() {
		return estatusDesc;
	}

	public void setEstatusDesc(String estatusDesc) {
		this.estatusDesc = estatusDesc;
	}

	@Column(name = "folio_carta_adhesion")
	public String getNoCarta() {
		return noCarta;
	}

	public void setNoCarta(String noCarta) {
		this.noCarta = noCarta;
	}

	@Column(name = "programa")
	public String getNombrePgrCorto() {
		return nombrePgrCorto;
	}

	public void setNombrePgrCorto(String nombrePgrCorto) {
		this.nombrePgrCorto = nombrePgrCorto;
	}	
	
	@Column(name = "id_especialista")
	public Integer getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(Integer idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
}
