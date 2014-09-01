package mx.gob.comer.sipc.pagos.action;

import java.io.*;

import java.util.Map;

import javax.servlet.http.*;

import org.apache.struts2.interceptor.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.Valid;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.exceptions.ParameterException;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Accion encargada de la descarga de las bitacoras.
 *
 */
@SuppressWarnings("serial")
public class ShowLogAction extends ActionSupport implements SessionAware, ServletRequestAware,ServletResponseAware {
	@SessionTarget
	Session hSession;	
	@TransactionTarget
	Transaction transaction;
	@Valid
	private String header;
	private String contentType;
	private int bufferSize;
	private String contentDisposition;
	private InputStream inputStream;
	private String l;
	private int hc;
	private String logPath;

	private Map<String, Object> appSession;	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	private CatalogosDAO catalogosDAO = new CatalogosDAO();
	  
	public String execute(){
		try{
			validateFile();
			setInputStream( new FileInputStream(logPath + l));
		}catch(ParameterException e){
			addActionError( e.getMessage() );
			return ERROR;
		}catch(FileNotFoundException e){
			addActionError( e.getMessage() );
			return ERROR;
		}
		setContentType("text/plain");
		response.setHeader("Content-Disposition", "inline; filename=\"" + l +".txt\"");
		
		setBufferSize(1024);
		return SUCCESS;
	}
	/**
	 * La validacion se lleva a cabo mediante el siguiente metodo en lugar de
	 * public void validate(), propio ActionSupport, ya que esta accion sera
	 * invocada via get y no tiene un "INPUT" al cual regresar en caso de error.
	 * @throws ParameterException
	 */
	private void validateFile()throws ParameterException{
		if( (l==null || hc ==0) || ( l.hashCode() != hc )  )  {
			throw new ParameterException("Error en parametros");
		}else{
			if( getSession()==null){
				throw new ParameterException("No ha iniciado sesion");
			}
			
			logPath = getLogPath();
			if( !new File(logPath + l).exists() ){
				throw new ParameterException("El archivo no existe");
			}
		}
	}
	
	private String getLogPath()throws ParameterException{
		String path =catalogosDAO.consultaParametros("RUTA_LOGS");
		if(!path.endsWith(File.separator)){
			path += File.separator;
		}
		return path;
	}
	
	// Setters & getters
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getL() {
		return l;
	}
	public void setL(String l) {
		this.l = l;
	}
	public int getHc() {
		return hc;
	}
	public void setHc(int hc) {
		this.hc = hc;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	// Implementar interfaz SessionAware
	public void setSession(Map<String, Object> appSession) {
	    this.appSession = appSession;
	}
	
	public Map<String, Object> getSession() {
	    return appSession;
	}
	// Implementar interfaz ServletResponseAware
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}
}
