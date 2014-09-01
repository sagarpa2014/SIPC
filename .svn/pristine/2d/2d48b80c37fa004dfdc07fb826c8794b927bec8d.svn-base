
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;



public class Prueba {

	/**
	 * @param args
	 */	
		public static void main(String[] argv) {
			 
			String item = "4182.179";
			String cadenaImporteTotal ="";
			String cadenaImporteTotal2 ="";
			String cadenaImporteTotal3 ="";
			Double vCuotaAux = 0.0;
			Double suma = 0.0;
			Double m  =  (Double.parseDouble(item)*174.00);
			 System.out.println("m "+m);
			
			Double v1 =  2459.453;
			Double v2 = 1722.726;
			Double v11 = v1 * 174.00;
			Double v21 = v2 * 174.00;
			System.out.println("v11 " +v11+" v21 "+v21);
			System.out.println("Suma "+ (v11+v21));
			
			
			NumberFormat nf = NumberFormat.getInstance();
		    nf.setMaximumFractionDigits(2); 
		    nf.setGroupingUsed(false);
		    cadenaImporteTotal = nf.format(v11);
		    cadenaImporteTotal2 = nf.format(v21);
		    System.out.println("Cadena importe "+cadenaImporteTotal);
		    System.out.println("Cadena importe "+cadenaImporteTotal2);
		    suma = (Double.parseDouble(cadenaImporteTotal)+Double.parseDouble(cadenaImporteTotal2));
		    cadenaImporteTotal3 = nf.format(suma);
		    System.out.println("Suma 2"+ Double.parseDouble(cadenaImporteTotal3));
			
			
			 
		}
}


