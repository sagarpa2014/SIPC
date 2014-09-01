package mx.gob.comer.sipc.utilerias;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Locale;

public class TextUtil {

	public static String  consigueMes(int numeroMes){
		switch( numeroMes ){
			case 1: return "Enero";
			case 2: return "Febrero";
			case 3: return "Marzo";
			case 4: return "Abril";
			case 5: return "Mayo";
			case 6: return "Junio";
			case 7: return "Julio";
			case 8: return "Agosto";
			case 9: return "Septiembre";
			case 10: return "Octubre";
			case 11: return "Noviembre";
			case 12: return "Diciembre";
			default: return "";
		}	
	}
	
	public static String  convierteNumARomano(int num){
		switch( num ){
			case 1: return "I";
			case 2: return "II";
			case 3: return "III";
			case 4: return "IV";
			case 5: return "V";
			case 6: return "VI";
			default: return "";
		}	
	}
	
	public static int  convierteRomanoANum(String num){
		if(num.equals("I")){
			return 1;
		}else if(num.equals("II")){
			return 2;
		}else if(num.equals("III")){
			return 3;
		}else if(num.equals("IV")){
			return 4;
		}else if(num.equals("V")){
			return 5;
		}
		return 0;	
	}
	public static String  consigueNombreDia(String nombreDia){
		nombreDia = nombreDia.toUpperCase();
		if(nombreDia.equals("MONDAY")){
			return "lunes";
		}else if(nombreDia.equals("TUESDAY")){
			return "martes";
		}else if(nombreDia.equals("WEDNESDAY")){
			return "miércoles";
		}else if(nombreDia.equals("THURSDAY")){
			return "jueves";
		}else if(nombreDia.equals("FRIDAY")){
			return "viernes";
		}else if(nombreDia.equals("SATURDAY")){
			return "sabado";
		}else if(nombreDia.equals("SONDAY")){
			return "domingo";
		}
		return "";
	}
	public enum Estilo
	{
		MAYUS, MINUS, TITULO
	};

	public static String formateaNumeroComoCantidad(double cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###,###,###,###.00").format(cantidad);
	}
	public static String formateaNumeroComoImporte(BigDecimal cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###,###,###,###.00").format(cantidad);
	}
	public static String formateaNumeroComoImporteSinComas(double cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###.00").format(cantidad);
	}
	public static String formateaNumeroComoImporteSinComas(BigDecimal cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###.00").format(cantidad);
	}
	public static String formateaNumeroComoVolumen(double cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###,###,###,###.000").format(cantidad);
	}
	public static String formateaNumeroComoVolumen(BigDecimal cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###,###,###,###.000").format(cantidad);
	}
	public static String formateaNumeroComoVolumenSinComas(double cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###.000").format(cantidad);
	}
	public static String formateaNumeroComoVolumenSinComas(BigDecimal cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###.000").format(cantidad);
	}
	public static String formateaNumeroComoCantidad(int cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###,###,###,###").format(cantidad);
	}
	public static String formateaNumeroComoCantidad(BigInteger cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###,###,###,###").format(cantidad);
	}	
	public static String formateaNumeroComoCantidadSinComas(BigInteger cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###").format(cantidad);
	}
	public static String formateaNumeroComoCantidadSincomas(Integer cantidad){
		Locale.setDefault(Locale.ENGLISH);
		return new DecimalFormat("###").format(cantidad);
	}

	public static String number2Text(double number)
	{
		Locale.setDefault(Locale.ENGLISH);
		return number2Text(number, "", "", false, true, Estilo.MINUS);
	}

	public static String number2Text(double number, Estilo estilo)
	{
		return number2Text(number, "", "", true, false, estilo);
	}

	public static String number2Text(double number, boolean ignoreDecimals)
	{
		return number2Text(number, "", "", true, ignoreDecimals, Estilo.MAYUS);
	}

	public static String number2Text(double number, boolean ignoreDecimals, Estilo estilo)
	{
		return number2Text1(number, "", "", true, ignoreDecimals, estilo);
	}

	public static String number2Text(double number, boolean fraccionLetras, boolean ignoreDecimals)
	{
		return number2Text(number, "", "", true, ignoreDecimals, Estilo.MAYUS);
	}

	public static String number2Text(double number, String nombreMoneda, String nombreFraccion, Estilo estilo)
	{
		return number2Text(number, nombreMoneda, nombreFraccion, true, false, estilo);
	}

	/**
	 * 
	 * @param numero
	 * @param nombreMoneda
	 *            Nombre la moneda en singular. Ejemplo: peso.
	 * @param nombreFraccion
	 *            Nombre de la parte fraccionaria de la moneda. Ejemplo: centavos.
	 * @param fraccionLetras
	 *            Mostrar con letra la parte fraccionaria.
	 * @param ignoreDecimals
	 *            Ignorar parte fraccionaria.
	 * @param estilo
	 *            Regresar texto a mayuscular, minusculas o tipo titulo.
	 * @return
	 */
	public static String number2Text(double numero, String nombreMoneda, String nombreFraccion, boolean fraccionLetras,
			boolean ignoreDecimals, Estilo estilo)
	{

		StringBuilder letras = new StringBuilder();

		// Convertimos a positivo si es negativo
		numero = Math.abs(numero);
		String NumTmp = new DecimalFormat("000000000000000.00").format(numero);
		if (numero < 1)
		{
			letras.append("cero ").append(plural(nombreMoneda)).append(" ");
		}
		else
		{
			long val = Long.parseLong(NumTmp.substring(0, 15));
			letras.append(numLetras(val));
			if (val == 1 || val < 2)
			{
				letras.append(nombreMoneda).append(" ");
			}
			else if (Double.parseDouble(NumTmp.substring(3).trim()) == 0
					|| Double.parseDouble(NumTmp.substring(9).trim()) == 0)
			{
				letras.append("de ").append(plural(nombreMoneda)).append(" ");
			}
			else
			{
				letras.append(plural(nombreMoneda)).append(" ");
			}
		}
		if (fraccionLetras && !ignoreDecimals)
		{
			int intFraccion = Integer.parseInt(NumTmp.substring(NumTmp.length() - 2));
			switch (intFraccion)
			{
				case 0 :
					letras.append("con cero ").append(plural(nombreFraccion));
					break;
				case 1 :
					letras.append("con un ").append(nombreFraccion);
					break;
				default :
					letras.append("con ").append(numLetras((long) intFraccion)).append(plural(nombreFraccion));
			}
		}
		else
		{
			if (!ignoreDecimals)
			{
				letras.append(NumTmp.substring(NumTmp.length() - 2));
			}
		}

		String out = null;
		if (estilo == Estilo.MAYUS)
		{
			out = letras.toString().toUpperCase();
		}
		else if (estilo == Estilo.MINUS)
		{
			out = letras.toString().toLowerCase();
		}
		else if (estilo == Estilo.TITULO)
		{
			out = toTitle(letras.toString());
		}
		return out.replaceAll("  ", " ").trim();
	}

	public static String number2Text1(double numero, String nombreMoneda, String nombreFraccion,
			boolean fraccionLetras, boolean ignoreDecimals, Estilo estilo)
	{

		StringBuilder letras = new StringBuilder();

		// Convertimos a positivo si es negativo
		numero = Math.abs(numero);
		String NumTmp = new DecimalFormat("000000000000000.00").format(numero);
		if (numero < 1)
		{
			letras.append("cero ").append(plural(nombreMoneda)).append(" ");
		}
		else
		{
			long val = Long.parseLong(NumTmp.substring(0, 15));
			letras.append(numLetras(val));
			if (val == 1 || val < 2)
			{
				letras.append(nombreMoneda).append(" ");
			}
			else
			{
				letras.append(plural(nombreMoneda)).append(" ");
			}
		}
		if (fraccionLetras && !ignoreDecimals)
		{
			int intFraccion = Integer.parseInt(NumTmp.substring(NumTmp.length() - 2));
			switch (intFraccion)
			{
				case 0 :
					letras.append("con cero ").append(plural(nombreFraccion));
					break;
				case 1 :
					letras.append("con un ").append(nombreFraccion);
					break;
				default :
					letras.append("con ").append(numLetras((long) intFraccion)).append(plural(nombreFraccion));
			}
		}
		else
		{
			if (!ignoreDecimals)
			{
				letras.append(NumTmp.substring(NumTmp.length() - 2));
			}
		}

		String out = null;
		if (estilo == Estilo.MAYUS)
		{
			out = letras.toString().toUpperCase();
		}
		else if (estilo == Estilo.MINUS)
		{
			out = letras.toString().toLowerCase();
		}
		else if (estilo == Estilo.TITULO)
		{
			out = toTitle(letras.toString());
		}
		return out.replaceAll("  ", " ").trim();
	}

	private static String plural(String palabra)
	{
		if (palabra != null && !palabra.isEmpty())
		{
			if (palabra.endsWith("a") || palabra.endsWith("e") || palabra.endsWith("i") || palabra.endsWith("o")
					|| palabra.endsWith("u"))
			{
				return palabra + "s";
			}
			else
			{
				return palabra + "es";
			}

		}
		return "";
	}

	private static String numLetras(long numero)
	{
		int co1 = 1;
		int pos = 0;
		int dig = 0;
		int cen = 0;
		int dec = 0;
		int uni = 0;
		String leyenda = "";
		String letra1 = "";
		String letra2 = "";
		String letra3 = "";
		StringBuilder tfNumero = new StringBuilder();
		String numTmp = new DecimalFormat("000000000000000.00").format(numero);
		// Extraer tres digitos cada vez
		while (co1 <= 5)
		{
			int co2 = 1;
			while (co2 <= 3)
			{
				// Extrae un digito cada vez de izquierda a derecha
				dig = Integer.parseInt("" + numTmp.charAt(pos));
				switch (co2)
				{
					case 1 :
						cen = dig;
						break;
					case 2 :
						dec = dig;
						break;
					case 3 :
						uni = dig;
						break;
				}
				co2++;
				pos++;
			}
			letra3 = centena(uni, dec, cen);
			letra2 = decena(uni, dec);
			letra1 = unidad(uni, dec);
			switch (co1)
			{
				case 1 :
					if ((cen + dec + uni) == 1)
					{
						leyenda = "billon ";
					}
					else if (cen + dec + uni > 1)
					{
						leyenda = "billones ";
					}
					break;
				case 2 :
					if (cen + dec + uni >= 1 && Integer.parseInt(numTmp.substring(6, 10)) == 0)
					{ // If cen + dec + uni >= 1 And Val(Mid(NumTmp, 7, 3)) = 0 Then
						leyenda = "mil millones ";
					}
					else if (cen + dec + uni >= 1)
					{
						leyenda = "mil ";
					}
					break;
				case 3 :
					if (cen + dec == 0 && uni == 1)
					{
						leyenda = "millon ";
					}
					else if (cen > 0 || dec > 0 || uni > 1)
					{
						leyenda = "millones ";
					}
					break;
				case 4 :
					if (cen + dec + uni >= 1)
					{
						leyenda = "mil ";
					}
					break;
				case 5 :
					if (cen + dec + uni >= 1)
					{
						leyenda = "";
					}
					break;
			}
			co1++;
			tfNumero.append(letra3).append(letra2).append(letra1).append(leyenda);
			leyenda = "";
			letra1 = "";
			letra2 = "";
			letra3 = "";
		}
		return tfNumero.toString();
	}

	private static String centena(int uni, int dec, int cen)
	{
		switch (cen)
		{
			case 1 :
				if ((dec + uni) == 0)
				{
					return "cien ";
				}
				else
				{
					return "ciento ";
				}
			case 2 :
				return "doscientos ";
			case 3 :
				return "trescientos ";
			case 4 :
				return "cuatrocientos ";
			case 5 :
				return "quinientos ";
			case 6 :
				return "seiscientos ";
			case 7 :
				return "setecientos ";
			case 8 :
				return "ochocientos ";
			case 9 :
				return "novecientos ";
			default :
				return "";
		}
	}

	private static String decena(int uni, int dec)
	{
		StringBuilder cTexto = new StringBuilder();
		switch (dec)
		{
			case 1 :
				switch (uni)
				{
					case 0 :
						cTexto.append("diez ");
						break;
					case 1 :
						cTexto.append("once ");
						break;
					case 2 :
						cTexto.append("doce ");
						break;
					case 3 :
						cTexto.append("trece ");
						break;
					case 4 :
						cTexto.append("catorce ");
						break;
					case 5 :
						cTexto.append("quince ");
						break;
					default :
						cTexto.append("dieci");
				}
				break;
			case 2 :
				if (uni == 0)
				{
					cTexto.append("veinte ");
				}
				else if (uni > 0)
				{
					cTexto.append("veinti");
				}
				break;
			case 3 :
				cTexto.append("treinta ");
				break;
			case 4 :
				cTexto.append("cuarenta ");
				break;
			case 5 :
				cTexto.append("cincuenta ");
				break;
			case 6 :
				cTexto.append("sesenta ");
				break;
			case 7 :
				cTexto.append("setenta ");
				break;
			case 8 :
				cTexto.append("ochenta ");
				break;
			case 9 :
				cTexto.append("noventa ");
				break;
		}
		if (uni > 0 && dec > 2)
		{
			cTexto.append("y ");
		}
		return cTexto.toString();
	}

	private static String unidad(int uni, int dec)
	{
		if (dec != 1)
		{
			switch (uni)
			{
				case 1 :
					return "un ";
				case 2 :
					return "dos ";
				case 3 :
					return "tres ";
				case 4 :
					return "cuatro ";
				case 5 :
					return "cinco ";
			}
		}
		switch (uni)
		{
			case 6 :
				return "seis ";
			case 7 :
				return "siete ";
			case 8 :
				return "ocho ";
			case 9 :
				return "nueve ";
		}
		return "";
	}

	public static String toTitle(String text)
	{
		StringBuilder sb = new StringBuilder(text.toLowerCase());
		boolean toUpper = true;
		for (int i = 0; i < sb.length(); i++)
		{
			char c = sb.charAt(i);
			if (toUpper)
			{
				sb.setCharAt(i, Character.toUpperCase(c));
				toUpper = false;
			}
			else
			{
				if (Character.isWhitespace(c) || c == '_')
				{
					toUpper = true;
				}
			}
		}
		return sb.toString();
	}
	
	public static String formatPath(String path)
	{
		return !path.endsWith(File.separator) ? path + File.separator : path;
	}

	
	public static String rellenarConCerosIzquierda(Integer numero, int largo){
		String s =""; 
		try{
			
			StringBuilder cadena = new StringBuilder();
			cadena.append(numero);
			int resta = largo-cadena.length();			
			for(int i = 1; i<=resta; i++){
				s+="0";
			}
			
			s = cadena.insert(0,s).toString();
			
		}catch(Exception e){
			
		}
		return s;
		
	}


}
