package mx.gob.comer.sipc.action.relaciones;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.doctos.excel.ExportarRelacionCertificado;
import mx.gob.comer.sipc.doctos.excel.ExportarRelacionCompras;
import mx.gob.comer.sipc.doctos.excel.ExportarRelacionMaritima;
import mx.gob.comer.sipc.doctos.excel.ExportarRelacionTerrestre;
import mx.gob.comer.sipc.doctos.excel.ExportarRelacionVentas;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Relaciones;
import mx.gob.comer.sipc.domain.transaccionales.SolicitudInscripcion;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AuditoresExternosV;
import mx.gob.comer.sipc.vistas.domain.ComprasDatosParticipanteV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionCertificadosV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionMaritimaV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionTerrestreV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionVentasV;
import mx.gob.comer.sipc.vistas.domain.IniEsquemaRelacionV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ExportarRelacionAction extends ActionSupport implements SessionAware, ServletContextAware {
	
	private Map<String, Object> session;
	private RelacionesDAO rDAO = new RelacionesDAO();
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private InscripcionDAO iDAO = new InscripcionDAO ();
	private UtileriasDAO uDAO = new UtileriasDAO ();
	private Programa programa;
	private List<Relaciones> lstRelaciones;
	private String folioCartaAdhesion;
	private int idRelacion;
	private String rutaCartaAdhesion;
	private String nombreRelacion;
	private SolicitudInscripcion si;
	private AuditoresExternosV auditor;	
	private ServletContext context;
	private String rutaImagen;
	private Integer idComprador;
	private String msjOk;
	private int opcion;
	private String claveBodega;
	private long idCompPer;
	private Long idIniEsquemaRelacion;
	private IniEsquemaRelacionV iniEsquemaRelacion;	
	private String tipoApoyo;
	private long idPerRel;
	private Integer cultivoRelacion;
	private long idPgrCiclo;
	private Integer estadoBodega;
	private Integer razonSocialAlmacen;
	private String nombreBarco;
	private String lugarDestino;
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;

	
	public String capExportarRelacion(){
		try{
			setLstRelaciones(rDAO.consultaRelacion(0));
		}catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capExportarRelacion debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}	
		return SUCCESS;
	}
	
	public String exportarRelacion(){
		try{			
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			if(opcion == 1){
				iniEsquemaRelacion = rDAO.consultaIniEsquemaRelacionV(idIniEsquemaRelacion).get(0);	
				rutaCartaAdhesion = getRecuperaRutaProgramaPrevio(idComprador);
				tipoApoyo = iniEsquemaRelacion.getNombreEsquema();
			}else{
				List<SolicitudInscripcion> lstSolIns = iDAO.consultaSolicitudInscripcion(folioCartaAdhesion, idComprador);
				if(lstSolIns.size()==0){
					//addActionError("No se tiene registrado el folio de la carta de adhesión que capturo");
					return SUCCESS;
				}
				si = lstSolIns.get(0);
				//Verifca que exista informacion en las tablas correspondientes
				if(!consultarInformacionRelacion()){
					//addActionError("No existe información registrada para la carta de adhesión que capturo");
					return SUCCESS;
				}
				//Recupera el nombre y numero de auditor
				List<AuditoresExternosV> lstAuditor = cDAO.consultaAuditoresExternosV(si.getNumeroAuditor());
				if(lstAuditor.size()>0){
					setAuditor(lstAuditor.get(0)); 
				}
				rutaCartaAdhesion = getRecuperaRutaCarta();
			}						
			rutaImagen = context.getRealPath("/images/asercaExcel.png");
			nombreRelacion = getRecuperaNombreArchivoRelacion();
			if(idRelacion == 1){
				ExportarRelacionCompras  er = new ExportarRelacionCompras(this, sessionTarget);
				er.generarArchivoExcel();
			}else if(idRelacion == 2){//TERRESTRE
				ExportarRelacionTerrestre  er = new ExportarRelacionTerrestre(this, sessionTarget);
				er.generarArchivoExcel();
			}else if(idRelacion == 3){//MARITIMA
				ExportarRelacionMaritima  er = new ExportarRelacionMaritima(this, sessionTarget);
				er.generarArchivoExcel();
			}else if(idRelacion == 4){//CERTIFICADOS DE DEPOSITO
				ExportarRelacionCertificado  er = new ExportarRelacionCertificado(this, sessionTarget);
				er.generarArchivoExcel();
			}else if(idRelacion == 5){//VENTAS
				ExportarRelacionVentas  er = new ExportarRelacionVentas(this, sessionTarget);
				er.generarArchivoExcel();
			}
			
			//Actualiza la ruta de la relacion en carta_adhesion_relacion		
			msjOk = "Se generó satisfactoriamente la exportación del archivo";
		
		}catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en exportarRelacion debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	private boolean consultarInformacionRelacion(){
		boolean existeInformacion=false;
		switch(idRelacion){
			case 1:	{
					List<ComprasDatosParticipanteV> lstComprasDatosParticipanteV = rDAO.consultaComprasDatosParticipanteV(idRelacion, folioCartaAdhesion, null);
					if(lstComprasDatosParticipanteV.size()> 0){
						existeInformacion = true;
					}
					break;
					
			}case 2:{
				List<GruposCamposRelacionTerrestreV> lstGruposCamposTerrestreV = rDAO.consultaGruposCamposTerrestreV(idRelacion, -1, -1,folioCartaAdhesion, null, -1, "", "", true, 0, 0);
				if(lstGruposCamposTerrestreV.size()>0){
					idPerRel = lstGruposCamposTerrestreV.get(0).getIdPerRel(); 
					existeInformacion = true;
				}
				break;
			}case 3:	{
				List<GruposCamposRelacionMaritimaV> lstGruposCamposMaritimaV = rDAO.consultaGruposCamposMaritimaV(idRelacion, -1, -1,folioCartaAdhesion, null, -1, "", "", true, 0,null,null);
				if(lstGruposCamposMaritimaV.size()>0){
					idPerRel = lstGruposCamposMaritimaV.get(0).getIdPerRel(); 
					existeInformacion = true;
				}
				break;
			}
			case 4:	{
				List<GruposCamposRelacionCertificadosV> lstCamposRelacionCertificadosV = rDAO.consultaGruposCamposCertificadosV(idRelacion,  -1, -1, folioCartaAdhesion,  null, -1, "", null, true,-1,0);
				if(lstCamposRelacionCertificadosV.size()>0){
					idPerRel = lstCamposRelacionCertificadosV.get(0).getIdPerRel(); 
					existeInformacion = true;
				}
				break;
			}
			case 5:	{
				List<GruposCamposRelacionVentasV> lstGruposCamposVentasV = rDAO.consultaGruposCamposVentasV(idRelacion, -1, -1, folioCartaAdhesion, -1, null, null, true, 0);
				if(lstGruposCamposVentasV.size()>0){
					idPerRel = lstGruposCamposVentasV.get(0).getIdPerRel(); 
					existeInformacion = true;
				}
				break;
			}
		}
		return existeInformacion;
		
	}

	private String getRecuperaRutaCarta() throws JDBCException, Exception{
		String  nomRutaCartaAdhesion = "", ruta="";		
		//Recupera la ruta del programa
		programa = cDAO.consultaPrograma(si.getIdPrograma()).get(0);
		tipoApoyo = programa.getDescripcionLarga(); 
		nomRutaCartaAdhesion = folioCartaAdhesion.replaceAll("-", "");	
		ruta = programa.getRutaDocumentos()+"/"+nomRutaCartaAdhesion+"/";
		Utilerias.crearDirectorio(ruta);
		return ruta;		 
	}
	
	private String getRecuperaRutaProgramaPrevio(Integer idComprador) throws JDBCException, Exception{
		String ruta="";	
		ruta = uDAO.getParametros("RUTA_COMPRADORES");		
		ruta += "/"+idComprador;
		Utilerias.crearDirectorio(ruta);
		return ruta;		 
	}
	
	private String getRecuperaNombreArchivoRelacion(){
		String nomArchivoRelacion="";
		if(idRelacion==1){
			nomArchivoRelacion = "relCompras.xls";
		}else if(idRelacion==2) {
			nomArchivoRelacion = "relMovTerrestre.xls";
		}else if(idRelacion==3) {
			nomArchivoRelacion = "relMovMaritima.xls";
		}else if(idRelacion==4) {
			nomArchivoRelacion = "relCertDeposito.xls";
		}else if(idRelacion==5) {
			nomArchivoRelacion = "relVentas.xls";
		}
		return nomArchivoRelacion;
		
	}
	public String getRutaCartaAdhesion() {
		return rutaCartaAdhesion;
	}

	public void setRutaCartaAdhesion(String rutaCartaAdhesion) {
		this.rutaCartaAdhesion = rutaCartaAdhesion;
	}
	
	public String getNombreRelacion() {
		return nombreRelacion;
	}

	public void setNombreRelacion(String nombreRelacion) {
		this.nombreRelacion = nombreRelacion;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	public int getIdRelacion() {
		return idRelacion;
	}

	public void setIdRelacion(int idRelacion) {
		this.idRelacion = idRelacion;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public List<Relaciones> getLstRelaciones() {
		return lstRelaciones;
	}

	public void setLstRelaciones(List<Relaciones> lstRelaciones) {
		this.lstRelaciones = lstRelaciones;
	}
	
	public AuditoresExternosV getAuditor() {
		return auditor;
	}
	
	public void setAuditor(AuditoresExternosV auditor) {
		this.auditor = auditor;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}	
	
	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}	
	
	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	
	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
	
	public String getClaveBodega() {
		return claveBodega;
	}

	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	public long getIdCompPer() {
		return idCompPer;
	}

	public void setIdCompPer(long idCompPer) {
		this.idCompPer = idCompPer;
	}

	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}

	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}
	
	public IniEsquemaRelacionV getIniEsquemaRelacion() {
		return iniEsquemaRelacion;
	}

	public void setIniEsquemaRelacion(IniEsquemaRelacionV iniEsquemaRelacion) {
		this.iniEsquemaRelacion = iniEsquemaRelacion;
	}


	public String getTipoApoyo() {
		return tipoApoyo;
	}

	public void setTipoApoyo(String tipoApoyo) {
		this.tipoApoyo = tipoApoyo;
	}

	public long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(long idPerRel) {
		this.idPerRel = idPerRel;
	}


	public Integer getCultivoRelacion() {
		return cultivoRelacion;
	}

	public void setCultivoRelacion(Integer cultivoRelacion) {
		this.cultivoRelacion = cultivoRelacion;
	}

	public long getIdPgrCiclo() {
		return idPgrCiclo;
	}

	public void setIdPgrCiclo(long idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
	}
	

	public Integer getEstadoBodega() {
		return estadoBodega;
	}

	public void setEstadoBodega(Integer estadoBodega) {
		this.estadoBodega = estadoBodega;
	}

	public Integer getRazonSocialAlmacen() {
		return razonSocialAlmacen;
	}

	public void setRazonSocialAlmacen(Integer razonSocialAlmacen) {
		this.razonSocialAlmacen = razonSocialAlmacen;
	}	
	
	public String getNombreBarco() {
		return nombreBarco;
	}

	public void setNombreBarco(String nombreBarco) {
		this.nombreBarco = nombreBarco;
	}

	public String getLugarDestino() {
		return lugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino;
	}

	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}
}
