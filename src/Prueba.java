import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;

import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;


public class Prueba {

	/**
	 * @param args
	 * @throws ParseException 
	 */	
		public static void main(String[] argv) throws ParseException {
//			 String cadena = "1,TOTAL CON;6,39627.54200000002,v;7,191.649,v;8,30825.90600000001,v;9,39627.54200000002,v;10,36410.06600000001,v;11,0.0,v;12,28461.680000000004,v;13,0.0,v;14,0.20100000000000015,v;15,39627.54200000002,v;1,TOTAL CON;6,34538.12099999999,v;7,0.0,v;8,28299.238000000005,v;9,34538.12099999999,v;10,34538.12099999999,v;11,0.0,v;12,27974.739000000012,v;13,0.0,v;14,225687.95500000005,v;15,34538.12099999999,v;";
			 String cadena = "1,TOTAL CON;6,39627.54200000002,v;7,191.649,v;8,30825.90600000001,v;9,39627.54200000002,v;10,36410.06600000001,v;11,0.0,v;12,28461.680000000004,v;13,0.0,v;14,0.20100000000000015,v;15,39627.54200000002,v;1,TOTAL CON;";
		
			 colocarTotales(cadena, 5 );	
			 
			 
		}
		
		
		private static void colocarTotales(String configuracion, int tamanio ){
			 String[] numElementos = configuracion.split(";");
			 int contador = 0, contadorTemp = 0;
			 for(int i=0; i < numElementos.length; i++){
				 String[] subElementos = numElementos[i].split(",");
				 contador=Integer.parseInt(subElementos[0]);		
				 for(int j= contadorTemp; j<contador-1; j++){				 
					 
				 }
				 if(i != 0){
					 if (subElementos[2].equals("v")){
						  
					 }else if (subElementos[2].equals("v6")){
						 
					 }else if(subElementos[2].equals("i")){
						
					 }else{
						 Double valor = Double.parseDouble(subElementos[1]);
						 
					 }
					 			 
				 }else{
					  
				 }			 
				 
				 contadorTemp = contador;	
			 }		 
			 for(int i=contadorTemp; i< tamanio; i++){
				
			 }	 				
		}
		
}


