package mx.gob.comer.sipc.pagos.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.domain.OficioPagos;
import mx.gob.comer.sipc.log.AppLogger;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CargaArchivosAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private String noOficio;
	private PagosDAO pDAO = new PagosDAO();
	private CatalogosDAO cDAO= new CatalogosDAO();
	private OficioPagos oficioPagos;
	private File cgcDgaf;
	private File dgafCgc;
	private String cgcDgafFileName;
	private String dgafCgcFileName;
	private String msjOk;
	private long idOficio;
	private String oficioCGC;
	private int folioCLC;
	
	
	public String capturaCargaArchivoTesofe(){
		try{	
			oficioPagos = pDAO.consultaOficiosPago(0, noOficio, 0).get(0);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrió un error al cargar los archivos de la tesofe");
	    }
		
		return SUCCESS;
	}
	
	
	public String cargarArchivoTesofe(){
		try{	
			String rutaSalida="";
			String cgcDgafNomArchivo="";
			String dgafCgcNomArchivo="";
			/*Recupera los datos del oficio*/
			oficioPagos = pDAO.consultaOficiosPago(0, noOficio, 0).get(0);
			rutaSalida = cDAO.consultaParametros("OFICIO_ESCANEO_TESOFE");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			 cgcDgafNomArchivo = new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"cgcDgaf.pdf";
			 dgafCgcNomArchivo = new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"dgafCgc.pdf";
			
			 File copiaCgcDgaf = new File(rutaSalida+cgcDgafNomArchivo.toString());
			 File copiaDgafCgc = new File(rutaSalida+dgafCgcNomArchivo.toString());
			 if(!copiaCgcDgaf.exists()){
				FileUtils.copyFile(cgcDgaf, copiaCgcDgaf);	//	Copia archivo del cliente al servidor					
			 }
			 if(!copiaDgafCgc.exists()){
					FileUtils.copyFile(cgcDgaf, copiaDgafCgc);	//	Copia archivo del cliente al servidor					
			 }
			 /*Guarda las rutas de los oficios escaneados en la tabla de oficio pagos*/
			 oficioPagos.setOficioCgcDgafEscaneo(cgcDgafNomArchivo);
			 oficioPagos.setOficioDgafCgcEscaneo(dgafCgcNomArchivo);
			 cDAO.guardaObjeto(oficioPagos);
			 idOficio = oficioPagos.getIdOficioPagos();
			 
			 /*Regresa el archivo al usuario*/
			 
			 msjOk = "Se cargaron satisfactoriamente los archivos";
			 oficioCGC = "";
			 folioCLC = 0;
			 
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrió un error al cargar los archivos de la tesofe");
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
		
	
	public String getNoOficio() {
		return noOficio;
	}

	public void setNoOficio(String noOficio) {
		this.noOficio = noOficio;
	}
	
	public OficioPagos getOficioPagos() {
		return oficioPagos;
	}

	public void setOficioPagos(OficioPagos oficioPagos) {
		this.oficioPagos = oficioPagos;
	}

	/**
	 * Implementar la interfaz SessionAware, para session de usuario
	 */ 
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}


	
	public File getCgcDgaf() {
		return cgcDgaf;
	}

	public void setCgcDgaf(File cgcDgaf) {
		this.cgcDgaf = cgcDgaf;
	}

	public File getDgafCgc() {
		return dgafCgc;
	}

	public void setDgafCgc(File dgafCgc) {
		this.dgafCgc = dgafCgc;
	}

	public String getCgcDgafFileName() {
		return cgcDgafFileName;
	}

	public void setCgcDgafFileName(String cgcDgafFileName) {
		this.cgcDgafFileName = cgcDgafFileName;
	}

	public String getDgafCgcFileName() {
		return dgafCgcFileName;
	}

	public void setDgafCgcFileName(String dgafCgcFileName) {
		this.dgafCgcFileName = dgafCgcFileName;
	}

	public String getMsjOk() {
		return msjOk;
	}
	
	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public long getIdOficio() {
		return idOficio;
	}

	public void setIdOficio(long idOficio) {
		this.idOficio = idOficio;
	}

	public String getOficioCGC() {
		return oficioCGC;
	}

	public void setOficioCGC(String oficioCGC) {
		this.oficioCGC = oficioCGC;
	}

	public int getFolioCLC() {
		return folioCLC;
	}

	public void setFolioCLC(int folioCLC) {
		this.folioCLC = folioCLC;
	}
	
}
