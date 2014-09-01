package mx.gob.comer.sipc.action.solicitudpago;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.DocumentacionSPCartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.ObservacionDocumentacionSP;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Anexo32DAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private String folioCartaAdhesion;
	private Long idExpSPCartaAdhesion; 
	private Comprador comprador;
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private DocumentacionSPCartaAdhesion documento;
	private String nombreArchivo;
	private int idPrograma;
	private Programa programa;
	private String rutaCartaAdhesion;
	private File doc; 
	private String docFileName;
	private Date fechaExpedicion;
	private String msjOk;
	private List<ObservacionDocumentacionSP> lstObservacionDocumentacionSP;
	
	
	
	public String capAnexo32D(){
		try{
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			// Recupera los datos del comprador
			comprador = cDAO.consultaComprador(ca.getIdComprador()).get(0);
			//Verifica los datos del expediente
			documento = spDAO.consultaExpedientesSPCartaAdhesion(idExpSPCartaAdhesion).get(0);
			lstObservacionDocumentacionSP = spDAO.consultaObservacionDocumentacionSPByOficioNull(idExpSPCartaAdhesion);
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capAnexo32D  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capAnexo32D  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;		
	}

	public String registraAnexo32D(){
		try{			
			System.out.println("registraAnexo32D");
			documento = spDAO.consultaExpedientesSPCartaAdhesion(idExpSPCartaAdhesion).get(0);
			File f1 = null, f2=null; 
			String ext = "";
			rutaCartaAdhesion = getRecuperaRutaCarta();		
			//Guarda el archivo en la tabla de observaciones y actualiza el registro de fecha expedición
			f1 = new File(documento.getRutaDocumento());
			nombreArchivo = documento.getRutaDocumento().substring(documento.getRutaDocumento().lastIndexOf("/")+1);
			System.out.println("nombre Archivo substring "+nombreArchivo);
			nombreArchivo = "HCO"+nombreArchivo; //Archivo de Expediente
			f2 = new File(rutaCartaAdhesion+nombreArchivo);
			if(!f1.renameTo(f2)){
				AppLogger.error("errores","No se pudo renombrar el archivo al sustituir el documento" );
				addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
				return SUCCESS;
			}
			//Guarda en historicos
			ObservacionDocumentacionSP obsDoc = new ObservacionDocumentacionSP();
			obsDoc.setIdExpSPCA(idExpSPCartaAdhesion);
			obsDoc.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
			cDAO.guardaObjeto(obsDoc);

			ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf(".") );
			//Carga el nuevo documento 
			nombreArchivo = 5+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date())+ext; //Archivo de Expediente
			Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc);
			//Actualiza el registro a traves de idExpSPCartaAdhesion
			documento.setRutaDocumento(rutaCartaAdhesion+nombreArchivo);
			documento.setFechaExpedicionAnexo(fechaExpedicion);
			cDAO.guardaObjeto(documento);
			capAnexo32D();	
			msjOk = "Se registró satisfactoriamente la información";
			fechaExpedicion = null;
			
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraAnexo32D debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraAnexo32D debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	
	private String getRecuperaRutaCarta() throws JDBCException, Exception {
		String  nomRutaCartaAdhesion = "";
		//Recupera la ruta de la solicitud de pago
		AsignacionCAaEspecialistaV acaaev= spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		idPrograma = acaaev.getIdPrograma();
		//Recupera la ruta del programa
		programa = cDAO.consultaPrograma(idPrograma).get(0);		
		nomRutaCartaAdhesion = folioCartaAdhesion.replaceAll("-", "");
		return programa.getRutaDocumentos()+"SolicitudPago/"+acaaev.getIdOficioCASP()+"/"+nomRutaCartaAdhesion+"/";
		 
	}
	
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	public Long getIdExpSPCartaAdhesion() {
		return idExpSPCartaAdhesion;
	}

	public void setIdExpSPCartaAdhesion(Long idExpSPCartaAdhesion) {
		this.idExpSPCartaAdhesion = idExpSPCartaAdhesion;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public DocumentacionSPCartaAdhesion getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentacionSPCartaAdhesion documento) {
		this.documento = documento;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public String getRutaCartaAdhesion() {
		return rutaCartaAdhesion;
	}

	public void setRutaCartaAdhesion(String rutaCartaAdhesion) {
		this.rutaCartaAdhesion = rutaCartaAdhesion;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	
	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public List<ObservacionDocumentacionSP> getLstObservacionDocumentacionSP() {
		return lstObservacionDocumentacionSP;
	}

	public void setLstObservacionDocumentacionSP(
			List<ObservacionDocumentacionSP> lstObservacionDocumentacionSP) {
		this.lstObservacionDocumentacionSP = lstObservacionDocumentacionSP;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
}
