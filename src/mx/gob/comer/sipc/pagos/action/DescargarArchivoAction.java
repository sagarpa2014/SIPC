package mx.gob.comer.sipc.pagos.action;

import java.io.*;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.UtileriasDAO;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.validator.Valid;

import com.opensymphony.xwork2.ActionSupport;
/**
 * Accion encargada de procesar las solicitudes de descarga de archivos.
 * 
 */
@SuppressWarnings("serial")
public class DescargarArchivoAction extends ActionSupport implements ServletContextAware{
	// Propiedades para la descarga del archivo
	private String contentType;
	private int bufferSize;
	private String contentDisposition;
	private InputStream inputStream;
	
	private ServletContext context;
	private String archivo;
	private String tipo;
	@Valid
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	public String execute(){
		
		String rutaSalida =  getParameterPath();		
		try{
			setInputStream( new FileInputStream(rutaSalida + archivo));
		}catch(Exception e){
			System.out.println( e.getMessage() );
		}
		String n = archivo.trim().toLowerCase();
		if(n.endsWith(".xls")){
			setContentType("application/vnd.ms-excel");
		}else if(n.endsWith(".pdf")){
			setContentType("application/pdf");
		}else if(n.endsWith(".jpg") || n.endsWith(".jpeg") ){
			setContentType("image/jpeg");
		}else if(n.endsWith(".gif")){
			setContentType("image/gif");
		}else if(n.endsWith(".tiff")){
			setContentType("image/tiff");
		}else if(n.endsWith(".png")){
			setContentType("image/x-png");
		}else if(n.endsWith(".txt")){
			setContentType("text/plain");
		}else if(n.endsWith(".zip")){
			setContentType("application/zip");
		}else if(n.endsWith(".doc")){
			setContentType("application/doc");
		}else if(n.endsWith(".docx")){
			setContentType("application/docx");
		}
		setBufferSize(1024);
		return SUCCESS;
	}
	
	public void validate(){
		
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

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	// metodos axiliares
	private String getParameterPath(){
		String path = null;
		if(tipo.equalsIgnoreCase("pagos") ){
			path =  utileriasDAO.getParametros("RUTA_PLANTILLA_PAGOS");
			if(!new File(path).exists()){
				path = context.getRealPath("/WEB-INF/archivos/archivosPagos");
			}
		}		if(!path.endsWith(File.separator)){
			path += File.separator;
		}		
		return path;
	}
}
