package mx.gob.comer.sipc.utilerias;

import java.io.File;
import java.text.DateFormat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Generales {
	
	public static Matcher validaPatron(String patron, String entrada){
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(entrada);
		return matcher; 
	}
	public static String formatPath(String path){
		return !path.endsWith(File.separator)? path + File.separator : path; 
	}
	
	public static String formateaNumeroCerosIzquierda(long cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("00000").format(cantidad);
	}
	
	/**
	 * Metodo que valida un horario establecido con la fecha y hora actual
	 * @param startHorario
	 * @param endHorario
	 * @return
	 */
	 public static boolean isHoraPermitida(String startHorario, String endHorario){
		 boolean isHoraPermitida= false;
		 Date today = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 String fecha = sdf.format(today);
		 
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmm").withLocale( new java.util.Locale("es", "MX") );
		 DateTime startHour = fmt.parseDateTime(fecha + startHorario);
         DateTime endHour = fmt.parseDateTime(fecha + endHorario); 
		 sdf = new SimpleDateFormat("yyyyMMddHHmm");
         DateTime time = fmt.parseDateTime( sdf.format(today ));
         
         if( (time.isAfter(startHour) || time.equals( startHour)) && (time.isBefore(endHour ) || time.equals( endHour )) ){
        	 isHoraPermitida=true;
         }
		 
		 return isHoraPermitida;
	 }
		
	/**
	 * Metodo que valida que dia actual sea diferente de Sabado y Domingo 
	 * @return
	 */
	 public static boolean isDiaPermitido(){
		 boolean isDiaPermitido= false;
		 Calendar now = Calendar.getInstance();
		 if(now.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && now.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY){                
			 isDiaPermitido = true;
	        }
		 return isDiaPermitido;
	 }
		 
	 
	 
	 
	public static void main(String[] args) {
		
		Matcher match=	validaPatron("^0(\\d{2})(.{18})(\\d{3})(\\d{2})(.{1})(\\d{2})(.{4})(\\d{2})(\\d{8})(\\d{3})(\\d{2})(\\d{10})(\\d{6})(\\d{15})(.{221})$", "00101218000180262250601206D25PV110120110701001010000814572001092000001954972800                                                                                                                                                                                                                             ");
				
		
		System.out.println("Valida Patron "+match.find());
		System.out.println("Es hora permitida: "+isHoraPermitida("0900","1610"));
	      
	    System.out.println(TextUtil.formateaNumeroComoCantidad(1800));
	    
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    
	    System.out.println(dateFormat.format(date));
	    
	  //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   System.out.println((cal.getTime()));

		
	}
	
	public static String getNombreMes(Integer mes) {
		switch (mes) {
		case 0:
			return "Enero";
		case 1:
			return "Febrero";
		case 2:
			return "Marzo";
		case 3:
			return "Abril";
		case 4:
			return "Mayo";
		case 5:
			return "Junio";
		case 6:
			return "Julio";
		case 7:
			return "Agosto";
		case 8:
			return "Septiembre";
		case 9:
			return "Octubre";
		case 10:
			return "Noviembre";
		case 11:
			return "Diciembre";

		}
		return "";
	}
}
