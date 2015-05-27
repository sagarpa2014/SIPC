import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.gob.comer.sipc.utilerias.Utilerias;


public class Prueba {

	/**
	 * @param args
	 * @throws ParseException 
	 */	
		public static void main(String[] argv) throws ParseException {
			 String cadena = "17CF726E-8537-4CDE-AE18-8CDD111F4450";
			 System.out.println("tamaño cadena "+cadena.length());
			 if(cadena.length() >= 32 && cadena.length() <=36){
				 System.out.println("ok");
			 }else{
				 System.out.println("Cadena no permitida");
			 }
			 
			 
			 Date fecha = new Date();
			 String fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fecha).toString();
			 Date fecha2 = Utilerias.convertirStringToDateyyyyMMdd(fechaInicioS);
			 
			 System.out.println(fecha2);
			
			 
		}
}


