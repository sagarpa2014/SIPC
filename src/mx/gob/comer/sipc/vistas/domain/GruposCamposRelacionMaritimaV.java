package mx.gob.comer.sipc.vistas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupos_campos_relacion_maritima_v")
public class GruposCamposRelacionMaritimaV {

	private Long idCampoRelacionMartima;
	private Long idCampoRelacion;
	private Integer idComprador;
	private String folio;
	private String folioCartaAdhesion;
	private String claveBodegaOpp;
	private String nombreBarco;
	private String lugarDestino;
	private Integer cultivoRegistro;
	private String  cultivo;
	private Integer idTipoRelacion;
	private String  relacion;
	private Integer idPerRel;
	private Integer idPgrCiclo;
	private Integer idCiclo;
	private Integer ejercicio;
	private String  cicloLargo;
	private String  cicloAgricola;
	private Integer idGrupo;
	private String  grupo;
	private String  tipoSeccion;
	private Integer posicionGrupo;
	private Integer idCampo;
	private String campo;
	private Integer posicionCampo;
	private String tipoDato;
	private String descripcion;
	private Integer exactitud;
	private Integer tamanio;	
	private Integer tipoTramo;
	
	
	public GruposCamposRelacionMaritimaV(){
	            
	}
	
	
	public GruposCamposRelacionMaritimaV(Integer idPerRel, Integer idComprador, 
			String folioCartaAdhesion,  String claveBodegaOpp, Integer cultivoRegistro, String cultivo, 		 
			Integer idPgrCiclo, String cicloLargo, Integer ejercicio,  String folio, String nombreBarco, String lugarDestino) {
		super();
		this.idPerRel = idPerRel;
		this.idComprador = idComprador;
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.claveBodegaOpp = claveBodegaOpp;
		this.cultivoRegistro = cultivoRegistro;
		this.cultivo = cultivo;
		this.idPgrCiclo = idPgrCiclo;
		this.cicloLargo = cicloLargo;
		this.ejercicio = ejercicio;
		this.folio = folio;
		this.nombreBarco = nombreBarco;
		this.lugarDestino = lugarDestino;
		
	}

	public GruposCamposRelacionMaritimaV(Integer idPerRel, Integer idTipoRelacion, Integer idComprador,
			String folioCartaAdhesion, String claveBodegaOpp,
			Integer cultivoRegistro, String cultivo, 
			 Integer idPgrCiclo, String cicloLargo, Integer ejercicio, String nombreBarco, String lugarDestino) {
		super();
		this.idPerRel = idPerRel;
		this.idTipoRelacion = idTipoRelacion;
		this.idComprador = idComprador;
		this.folioCartaAdhesion = folioCartaAdhesion;
		this.claveBodegaOpp = claveBodegaOpp;
		this.cultivoRegistro = cultivoRegistro;
		this.cultivo = cultivo;		
		this.idPgrCiclo = idPgrCiclo;
		this.cicloLargo = cicloLargo;
		this.ejercicio = ejercicio;
		this.nombreBarco = nombreBarco;
		this.lugarDestino = lugarDestino;
	}
	

	public GruposCamposRelacionMaritimaV(Long idCampoRelacion, Integer idGrupo,
			String grupo, Integer posicionGrupo, Integer idCampo, String campo,
			Integer posicionCampo, String tipoDato) {
		super();
		this.idCampoRelacion= idCampoRelacion;
		this.idGrupo = idGrupo;
		this.grupo = grupo;
		this.posicionGrupo = posicionGrupo;
		this.idCampo = idCampo;
		this.campo = campo;
		this.posicionCampo = posicionCampo;
		this.tipoDato = tipoDato;
	}
	
	@Id
	@Column(name = "id_campo_relacion_maritima")
	public Long getIdCampoRelacionMartima() {
	    return idCampoRelacionMartima;
	}
	public void setIdCampoRelacionMartima(Long idCampoRelacionMartima) {
	    this.idCampoRelacionMartima = idCampoRelacionMartima;
	}
	
	@Column(name = "id_campo_relacion")
	public Long getIdCampoRelacion() {
		return idCampoRelacion;
	}

	public void setIdCampoRelacion(Long idCampoRelacion) {
		this.idCampoRelacion = idCampoRelacion;
	}
	
	@Column(name = "id_tipo_relacion")
	public Integer getIdTipoRelacion() {
	    return idTipoRelacion;
	}
	
	public void setIdTipoRelacion(Integer idTipoRelacion) {
		this.idTipoRelacion = idTipoRelacion;
	}

	@Column(name = "id_comprador")
	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	@Column(name = "folio")	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}


	@Column(name = "folio_carta_adhesion")
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	@Column(name =  "clave_bodega_o_pp")
	public String getClaveBodegaOpp() {
		return claveBodegaOpp;
	}

	public void setClaveBodegaOpp(String claveBodegaOpp) {
		this.claveBodegaOpp = claveBodegaOpp;
	}	

	@Column(name = "nombre_barco")
	public String getNombreBarco() {
		return nombreBarco;
	}

	public void setNombreBarco(String nombreBarco) {
		this.nombreBarco = nombreBarco;
	}

	@Column(name = "lugar_destino")
	public String getLugarDestino() {
		return lugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino;
	}
	
	@Column(name = "cultivo_registro")	
	public Integer getCultivoRegistro() {
		return cultivoRegistro;
	}
	public void setCultivoRegistro(Integer cultivoRegistro) {
		this.cultivoRegistro = cultivoRegistro;
	}
	
	@Column(name = "relacion")
	public String getRelacion() {
	    return relacion;
	}
	public void setRelacion(String relacion) {
	    this.relacion = relacion;
	}
	
	@Column(name = "id_per_rel")
	public Integer getIdPerRel() {
	    return idPerRel;
	}
	public void setIdPerRel(Integer idPerRel) {
	    this.idPerRel = idPerRel;
	}
	
	@Column(name = "ciclo_agricola")
	public String getCicloAgricola() {
	    return cicloAgricola;
	}
	
	public void setCicloAgricola(String cicloAgricola) {
	    this.cicloAgricola = cicloAgricola;
	}
	
	@Column(name = "cultivo")
	public String getCultivo() {
	    return cultivo;
	}
	public void setCultivo(String cultivo) {
	    this.cultivo = cultivo;
	}
	@Column(name = "id_grupo")
	public Integer getIdGrupo() {
	    return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
	   this.idGrupo = idGrupo;
	}
	
	@Column(name = "grupo")
	public String getGrupo() {
	    return grupo;
	}
	public void setGrupo(String grupo) {
	    this.grupo = grupo;
	}
	
	@Column(name = "tipo_seccion")
	public String getTipoSeccion() {
	    return tipoSeccion;
	}
	public void setTipoSeccion(String tipoSeccion) {
	    this.tipoSeccion = tipoSeccion;
	}
	
	@Column(name = "posicion_grupo")
	public Integer getPosicionGrupo() {
	    return posicionGrupo;
	}
	public void setPosicionGrupo(Integer posicionGrupo) {
	    this.posicionGrupo = posicionGrupo;
	}
	
	@Column(name = "id_campo")
	public Integer getIdCampo() {
	    return idCampo;
	}
	public void setIdCampo(Integer idCampo) {
	    this.idCampo = idCampo;
	}
	
	@Column(name = "campo")
	public String getCampo() {
	    return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	@Column(name = "posicion_campo")
	public Integer getPosicionCampo() {
		return posicionCampo;
	}
	public void setPosicionCampo(Integer posicionCampo) {
	   this.posicionCampo = posicionCampo;
	}

	@Column(name = "tipo_dato")
	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	@Column(name =  "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "exactitud")
	public Integer getExactitud() {
		return exactitud;
	}
	public void setExactitud(Integer exactitud) {
		this.exactitud = exactitud;
	}
	
	@Column(name = "tamanio")
	public Integer getTamanio() {
		return tamanio;
	}
	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}
	
	@Column(name = "id_pgr_ciclo")
	public Integer getIdPgrCiclo() {
		return idPgrCiclo;
	}

	public void setIdPgrCiclo(Integer idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
	}

	@Column(name = "ciclo_largo")
	public String getCicloLargo() {
		return cicloLargo;
	}

	public void setCicloLargo(String cicloLargo) {
		this.cicloLargo = cicloLargo;
	}
	
	@Column(name = "id_ciclo")
	public Integer getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}

	@Column(name = "ejercicio")
	public Integer getEjercicio() {
		return ejercicio;
	}


	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}	
	
	@Column(name = "tipo_tramo")
	public Integer getTipoTramo() {
		return tipoTramo;
	}

	

	public void setTipoTramo(Integer tipoTramo) {
		this.tipoTramo = tipoTramo;
	}

}
