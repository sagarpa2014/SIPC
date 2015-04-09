package mx.gob.comer.sipc.utilerias;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import mx.gob.comer.sipc.log.AppLogger;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;



public class Utilerias implements ServletResponseAware {
	private static HttpServletResponse response;
	
	/**
	 * Metodo encargado de convertir caracteres especialeas  de ISO-8859-1 a UTF-8
	 * 	  
	 * @throws FileNotFoundException Archivo no encontrasdo
	 * @throws IOException   Error en lectura del archivo 
	 */
	public static String convertirISO88591aUTF8(String texto) throws UnsupportedEncodingException{
		
		String textoConvertido="";
		String var=new String(texto);
		byte[] arrByte = var.getBytes("ISO-8859-1");
		textoConvertido = new String(arrByte, "UTF-8");
		  
		return textoConvertido;
	}
	
	/**
	 *  Devuelve el archivo, serializandolo para entregarlo al usuario 
	 *   
	 * @param log objeto que se guarda en la base de datos
	 * @throws RutinaExcepcion Si ocurre un error al devolver el archivo
	 * @throws IOException Error al leer el archivo
	 * @throws RutinaExcepcion Si ocurre un error al devolver el archivo
	 * @throws FileNotFoundException Error al conseguir el archivo
	 * 
	 */
	public static void entregarArchivo(String ruta, String nombreArchivo, String mimeType) throws IOException {
		// Configurar respuesta al usuario
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/"+mimeType); 
		response.setHeader("Content-Disposition", "attachment; filename="+nombreArchivo);
		InputStream in = null;
		OutputStream out = response.getOutputStream();
		try {	
			in = new BufferedInputStream(new FileInputStream(ruta+nombreArchivo));
			byte[] buf = new byte[4 * 1024]; 
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				out.write(buf, 0, bytesRead);
			}	
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		}		
	}

	public static void entregarArchivo(String ruta, String mimeType) throws IOException {
		// Configurar respuesta al usuario
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/"+mimeType); 
		response.setHeader("Content-Disposition", "attachment; filename="+"ok.pdf");
		InputStream in = null;
		OutputStream out = response.getOutputStream();
		try {	
			in = new BufferedInputStream(new FileInputStream(ruta));
			byte[] buf = new byte[4 * 1024]; 
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				out.write(buf, 0, bytesRead);
			}	
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		}		
	}

	public static void copiaArchivo(String ruta, File documento) throws IOException {
		File copiaExpediente = new File(ruta);
		FileUtils.copyFile(documento, copiaExpediente);
	}
	/**
	 *   
	 * Settea la respuesta a ISO-8859-1, para la parte de caracteres especiales
	 * 
	 * 
	 */
	public static void getResponseISO(){
		response = ServletActionContext.getResponse();
		response.setContentType("text; charset=ISO-8859-1");
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static String conformaCadena(String [] lisCadena) {
		StringBuilder cadena = new StringBuilder();
		for(int i=0; i<=lisCadena.length-1; i++){
			System.out.println(lisCadena[i]);
		}
		if(lisCadena.length>1){
			for(int i=0; i<lisCadena.length; i++){
				if(i != lisCadena.length-1){
					cadena.append(lisCadena[i]).append(",").append(" ");
				}
			}
			cadena.deleteCharAt(cadena.length() - 2);
			cadena.append("y ").append(lisCadena[lisCadena.length-1]);
		}else{
			cadena.append(lisCadena[0]);
		}
		
		return cadena.toString();
		
		
	} 
	
	public static void crearDirectorio(String ruta) {
		try{
			//Si el directorio no existe, se crea
			File directorio =  new File(ruta);
			if (!ruta.endsWith(File.separator)){
				ruta += File.separator;
			}
			if(!directorio.exists()){
				boolean mkdir = new File(ruta).mkdirs();
				if(!mkdir){
					System.out.println("No se pudo crear");
					AppLogger.error("errores", "No se pudo crear directorio:"+ruta);
				}else{
					//Se creo el directorio en caso de que no exista
					System.out.println("Se creo directorio");
					AppLogger.info("app","Se creo directorio: "+ruta);					
				}	
			} 
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores", "No se pudo crear directorio debido a: "+e.getMessage());
		}
		
		
	}
	
	public static void cargarArchivo(String rutaPrograma, String nombreArchivo, File doc ) {
		
		try {
			if (!rutaPrograma.endsWith(File.separator)){
				rutaPrograma += File.separator;
			}
			Utilerias.copiaArchivo(rutaPrograma+nombreArchivo, doc);
		} catch (IOException e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al guardar el archivo de aviso");
		}				
		
	}
	
	public static boolean validaFecha1MenorFecha2(Date fecha1, Date fecha2) {
		boolean bandera = false;
		try{
	
			String fechaString1 = new SimpleDateFormat("yyyyMMdd").format(fecha1).toString();
			String fechaString2 = new SimpleDateFormat("yyyyMMdd").format(fecha2).toString();	
			if(Long.parseLong(fechaString1) < Long.parseLong(fechaString2)){
				bandera = true;
		    }else{
		    	bandera = false;		    }
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrio un error al comparar las fechas debido a: "+e.getMessage());
		}
		
		return bandera;
	}//validaFecha1MayorFecha2

	public static boolean verificarTamanioArchivo(File file) {
		boolean bandera = false;
		try{
			double bytes = file.length();
			double megabytes = ((bytes/1024)/1024);
			if(megabytes>3){
				System.out.println("lo sentimos los archivos cargados no debe exceder de 3 MegaBytes");
				bandera = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrio un error al comparar las fechas debido a: "+e.getMessage());
		}
		
		return bandera;
	}
	
	public static int borrarArchivo(String ruta) throws Exception{
		int error = 0;
		File f = new File(ruta);
		if(f.exists()){
			if(!f.delete()){
				AppLogger.error("errores","Error al eliminar el archivo: "+ruta);
				error = 1;
			}else{
				AppLogger.info("app","Se borro archivo: "+ruta);
			}
		}
		
		return error;
	}
	
	public static Date convertirStringToDate(String strFecha) throws ParseException{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;	 
		fecha = formatoDelTexto.parse(strFecha);
		return fecha;
		
	}
	
	public static Date convertirStringToDateMMDDYYY(String strFecha) throws ParseException{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("MMM-dd-yyyy");
		Date fecha = null;	 
		fecha = formatoDelTexto.parse(strFecha);
		return fecha;
		
	}
	

	public static Date convertirStringToDateyyyyMMdd(String strFecha) throws ParseException{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;	 
		fecha = formatoDelTexto.parse(strFecha);
		return fecha;
		
	}
	

	
	
}
