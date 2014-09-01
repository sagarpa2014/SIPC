package mx.gob.comer.sipc.domain.historico;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Entity
@Table(name = "hco_programas")
public class HcoPrograma{

	private int idHcoPrograma;
	private int idPrograma;
	private String descripcionCorta;
	private String descripcionLarga;
	private String ciclo;
	private String cicloCorto;
	private String producto;
	private String estados;
	private String descripcion;
	private String rutaDocumentos;
	private int idArea;
	private int criterioPago;
	private Integer numeroEtapa;
	private Integer idComponente;
	private Integer idUnidadMedida;
	private Date fechaCreacion;
	private Integer usuarioCreacion;
	
	@Id
	@GeneratedValue(generator = "hco_programas_id_hco_programa_seq")
	@SequenceGenerator(name = "hco_programas_id_hco_programa_seq", sequenceName = "hco_programas_id_hco_programa_seq")
	@Column(name =  "id_hco_programa")
	public int getIdHcoPrograma() {
		return idHcoPrograma;
	}
	public void setIdHcoPrograma(int idHcoPrograma) {
		this.idHcoPrograma = idHcoPrograma;
	}
	@Column(name =  "id_programa")
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	@Column(name =  "descripcion_corta")
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	@Column(name =  "descripcion_larga")
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	@Column(name =  "ciclo")
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Column(name =  "ciclo_corto")
	public String getCicloCorto() {
		return cicloCorto;
	}
	public void setCicloCorto(String cicloCorto) {
		this.cicloCorto = cicloCorto;
	}
	@Column(name =  "producto")
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	@Column(name =  "estados")
	public String getEstados() {
		return estados;
	}
	public void setEstados(String estados) {
		this.estados = estados;
	}
	
	@Column(name =  "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name =  "ruta_documentos")
	public String getRutaDocumentos() {
		return rutaDocumentos;
	}
	public void setRutaDocumentos(String rutaDocumentos) {
		this.rutaDocumentos = rutaDocumentos;
	}
	
	@Column(name =  "id_area")
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	
	@Column(name =  "criterio_pago")
	public int getCriterioPago() {
		return criterioPago;
	}
	public void setCriterioPago(int criterioPago) {
		this.criterioPago = criterioPago;
	}

	
	@Column(name =  "numero_etapa")
	public Integer getNumeroEtapa() {
		return numeroEtapa;
	}
	public void setNumeroEtapa(Integer numeroEtapa) {
		this.numeroEtapa = numeroEtapa;
	}
	
	@Column(name =  "id_componente")
	public Integer getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(Integer idComponente) {
		this.idComponente = idComponente;
	}
	
	@Column(name =  "id_unidad_medida")
	public Integer getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(Integer idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	@Column(name = "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "usuario_creacion")
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	

}
	
