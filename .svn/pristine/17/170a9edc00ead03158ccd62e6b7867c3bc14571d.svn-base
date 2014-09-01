package mx.gob.comer.sipc.action;

import java.io.IOException;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EntregaArchivoAction  extends ActionSupport {

	
	private String nombreArchivo;
	private String rutaArchivo;
	private String mimeType;
	private String rutaCompleta;
	
	public String devuelveArchivo(){
		try{
			Utilerias.entregarArchivo(rutaArchivo, nombreArchivo, mimeType);
		}catch(IOException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar el archivo debido a:"+e.getCause());
		} 
		return SUCCESS;
	}
	
	public String devuelveArchivoByRuta(){
		try{
			rutaArchivo = rutaCompleta.substring(0, (rutaCompleta.lastIndexOf("/")+1));
			nombreArchivo = rutaCompleta.substring( rutaCompleta.lastIndexOf("/"));
			nombreArchivo = nombreArchivo.substring(1,nombreArchivo.length());			
			mimeType = rutaCompleta.substring(rutaCompleta.lastIndexOf("."));
			Utilerias.entregarArchivo(rutaArchivo, nombreArchivo, mimeType);
			
		}catch(IOException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar el archivo debido a: "+e.getCause());
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar el archivo debido a: "+e.getMessage());
		} 
		return SUCCESS;
	}
	
	
	
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getRutaCompleta() {
		return rutaCompleta;
	}

	public void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
