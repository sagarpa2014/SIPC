
public class Prueba {

	/**
	 * @param args
	 */	
		public static void main(String[] argv) {
			 String cadena = "17CF726E-8537-4CDE-AE18-8CDD111F4450";
			 System.out.println("tamaño cadena "+cadena.length());
			 if(cadena.length() >= 32 && cadena.length() <=36){
				 System.out.println("ok");
			 }else{
				 System.out.println("Cadena no permitida");
			 }
			
			 
		}
}


