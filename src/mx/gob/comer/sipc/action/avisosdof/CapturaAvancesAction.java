package mx.gob.comer.sipc.action.avisosdof;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.AvancePagosDAO;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.domain.transaccionales.AvancePagos;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AvisosDofDetalleV;
import mx.gob.comer.sipc.vistas.domain.AvisosDofV;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapturaAvancesAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{
	
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private AvancePagosDAO aDAO = new AvancePagosDAO();
	private String claveAviso;
	private AvisosDofV adv;	
	private List<String> lstLog;
	private HSSFWorkbook wbLog; //inicializar excel para log
	private HSSFSheet sheetLog;
	private int countRowLog;
	private Row rowLog;
	private Cell cellLog;
	private String nombreArchivoLogCargaXls;
	private File doc; 
	private String docFileName;
	private String ext;
	private int errorSistema;
	private boolean errorEnCampo;
	private String msj;
	private int contRow;
	private int contColumna;
	private AvancePagos ap;
	private ArrayList<AvancePagos> lstAvancePagos;
	private int idEstado;
	private int programa;
	private int idCultivo;
	private String cicloAgricola;

	private String rutaLog;

	private String folioArchivo;

	private int registrosBorrados;	

	public String capturaAvancePagos(){
		try{			
			adv = aDAO.getAvisosDofV(claveAviso).get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String registraArchivoAvancePagos(){
		try{	
			System.out.println("entrando a registraArchivoAvancePagos ");
			wbLog = new HSSFWorkbook(); //inicializar excel para log 	
			lstLog =  new ArrayList<String>();
			XSSFWorkbook wbXSSF = null;
			XSSFSheet sheetXSSF = null;
			Iterator<Row> rowIterator = null;
			FileInputStream inp = new FileInputStream(doc);
			ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf("."));
			if(ext.equals(".xlsx")||ext.equals(".XLSX")){
				wbXSSF =  new XSSFWorkbook (inp);	
			}	
			folioArchivo = new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date());
			for(int i = 0; i < wbXSSF.getNumberOfSheets(); i++){
				sheetXSSF = wbXSSF.getSheetAt(i);
				rowIterator = sheetXSSF.iterator();
				lstLog.add("Extrayendo informaci�n de la hoja "+(i+1));
				sheetLog = wbLog.createSheet("LOG CARGA ARCHIVO "+(i+1));
				sheetLog = setMargenSheet(sheetLog);
				countRowLog = 0;
				crearCeldaenLogXls();
				leerPagina(rowIterator);
				guardarAvancePagos(i+1);
			}			
			nombreArchivoLogCargaXls = "LogCargaArchivoAvancePagos.xls";  
			rutaLog = "/SIPC/logs/";
			FileOutputStream out = new FileOutputStream(new File(rutaLog+nombreArchivoLogCargaXls));
		    wbLog.write(out);
		    out.close();
		    capturaAvancePagos();
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraArchivoRelVentas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			errorSistema = 1;
		}
		
		return SUCCESS;
	}
		
	private void guardarAvancePagos(int numeroHoja) {
		 int numRegGuardados = 0, totalRegistrosDetalle = 0, numRegNoGuardados=0;
		 for(AvancePagos a: lstAvancePagos){
			 cDAO.guardaObjeto(a);
			 numRegGuardados++;
		 }
		 totalRegistrosDetalle = (contRow - 1);
		 msj = "Se guardaron "+numRegGuardados+" registros en la base de datos de la hoja "+numeroHoja;
		 lstLog.add(msj);
		 crearCeldaenLogXls();		
		 numRegNoGuardados = totalRegistrosDetalle-numRegGuardados;
		 if(numRegNoGuardados > 0){
				msj = "No fue posible guardar "+(numRegNoGuardados == 1?numRegNoGuardados+" registro":numRegNoGuardados+" registros")+" en la base de datos de la hoja"+numeroHoja+", por favor verifique";
				lstLog.add(msj);
				crearCeldaenLogXls();
		}	
		 
		 msj = "Total registros del detalle "+(totalRegistrosDetalle>1?totalRegistrosDetalle:0)+" de la hoja "+numeroHoja;
		 lstLog.add(msj);
		 crearCeldaenLogXls();
	}
	
	@SuppressWarnings("unused")
	private void leerPagina(Iterator<Row> rowIterator) throws ParseException {
		contRow = 0;
		contColumna = 0;
		String valor = "";
		boolean bandFinArchivo = false;
		lstAvancePagos = new ArrayList<AvancePagos>();
		while(rowIterator.hasNext()){
			contColumna = 0;	
			Row row = rowIterator.next();
			errorEnCampo = false;
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				valor =  recuperarDatoDeCelda(cellIterator.next());
				 if (valor != null && !valor.equals("")){
					   bandFinArchivo = true;
				 }	
				 if(contRow > 0 && contColumna < 10 ){
					 if(contColumna == 0){
						 ap = new AvancePagos();
						 ap.setFolioArchivo(folioArchivo);
						 ap.setFechaRegistro(new Date());
					 }
					 getValidacionDetalle(valor);			 
					 if(contColumna == 9){						 
	 					if(!errorEnCampo){
	 						 //valida que el avance se haya registrado previamente en la configuracion del aviso
 							List<AvisosDofDetalleV> lstAvisoDofDetalle = aDAO.getAvisosDofDetalle(claveAviso, programa, idCultivo,
 										null, 0, idEstado, cicloAgricola,0);
 							if(lstAvisoDofDetalle.size() == 0){
 								 msj = "Configuraci�n no existe registrada para el aviso "+claveAviso+": Fila :"+(contRow+2);
 								 crearCeldaenLogXls();
 								errorEnCampo = true;
 							}else{
 								lstAvancePagos.add(ap);
 							}				 
		 				}
		 			}
				 }				 
				 contColumna++;
				 if(contColumna == 10){			 					 
					break; //en caso de que haya mas columnas en el archivo omitir	
				 } 				 
			}//end while cellIterator
			
//			if(contRow >= 1){
//				if(bandFinArchivo){
//					if(contColumna < 8){
//						msj = "Fila:"+(contRow+1)+". Se esperaban "+9+", se encontraron solo " + contColumna;
//						 errorEnCampo  = true;
//						 crearCeldaenLogXls();
//					}
//				}else{
//					break;
//				}
//			}
			contRow++;
		}//while(rowIterator.hasNext())		
	}	
	
	private void getValidacionDetalle(String valor) throws ParseException {  
			  switch(contColumna){
				  case 0:{	
					  String claveAvisoTmp = "";
					if(valor !=null && !valor.isEmpty()){					
						try{
							  double vDouble = Double.parseDouble(valor);
							  Long vLong = (long) vDouble;
							  claveAvisoTmp  = vLong.toString();
						  }catch(Exception e){
							  claveAvisoTmp = valor; 
						  }	
						if(claveAvisoTmp.equals(claveAviso)){
							ap.setClaveAviso(claveAvisoTmp);
						}else{
							msj = "Clave de aviso "+claveAvisoTmp+" invalido, no corresponde a "+claveAviso+" seleccionado: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
							crearCeldaenLogXls();
							errorEnCampo = true;
						}
					}else{
						msj = "Clave de aviso es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						crearCeldaenLogXls();
						errorEnCampo = true;
					}
					break;
				  }
				  case 1:{
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica el id del estado
						  if(verificarTipoDato(valor, "numero")){
							  Double d = Double.parseDouble(valor);
							  valor = TextUtil.formateaNumeroComoCantidadSincomas(d);  
							 idEstado = Integer.parseInt(valor);
							 ap.setIdEstado(idEstado);
						  }else{
							 msj = "id del estado invalido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
						  }	
					  }else{
						  msj = "Id del estado es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }
				  case 2:{
					  if(valor !=null && !valor.isEmpty()){
						//Verifica el id del PROGRAMA
						  if(verificarTipoDato(valor, "numero")){
							  Double d = Double.parseDouble(valor);
							  valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
							  programa = Integer.parseInt(valor);
							  ap.setPrograma(programa);
						  }else{
							 msj = "id del programa invalido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
						  }
					  }else{
						  msj = "Id del programa es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }
				  case 3:{
					  if(valor !=null && !valor.isEmpty()){
						  //Valida el id del cultivo
						  if(verificarTipoDato(valor, "numero")){
							  Double d = Double.parseDouble(valor);
							  valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
							  idCultivo = Integer.parseInt(valor);
							 ap.setIdCultivo(idCultivo);
						}else{
							 msj = "id del cultivo invalido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
						 }	
					  }else{
						  msj = "Id del cultivo es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 4:{
					  if(valor !=null && !valor.isEmpty()){
						  //Valida Ciclo
						  cicloAgricola = valor;
						  ap.setCicloAgricola(valor);
					  }else{
						  msj = "Ciclo Agricola es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 5:{
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica  Solucitudes
						  if(verificarTipoDato(valor, "numero")){
							  	Double d = Double.parseDouble(valor);
							  	valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
								ap.setSolicitudes(Integer.parseInt(valor));
						  }else{
								 msj = "Tipo de dato en solicitudes invalido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
								 crearCeldaenLogXls();
								 errorEnCampo = true;
						 }
					  }else{
						  msj = "N�mero de solicitudes es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 6:{
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica el volumen
						  if(verificarTipoDato(valor, "volumen")){
							  ap.setVolumen(Double.parseDouble(valor)	);
						  }else{
							  msj = "Volumen de la Factura: Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Volumen no valido";
							  crearCeldaenLogXls();
							  errorEnCampo = true;
						  }
					  }else{
						  msj = "Volumen es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 7:{
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica el importe
						  if(verificarTipoDato(valor, "importe")){
							  ap.setImporte(Double.parseDouble(valor));
						  }else{
							  msj = "Importe: Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Importe no valido";
							  crearCeldaenLogXls();
							  errorEnCampo = true;
						  }
					  }else{
						  msj = "Importe es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 8:{
					  if(valor !=null && !valor.isEmpty()){
						  //Valida tipo de dato de productores
						  if(verificarTipoDato(valor, "numero")){
							  Double d = Double.parseDouble(valor);
							  valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
							  ap.setProductores(Integer.parseInt(valor));
						  }else{
							  msj = "Productores: Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Tipo de dato no valido";
							  crearCeldaenLogXls();
							  errorEnCampo = true;
						  }
					  }else{
						  msj = "N�mero de productores es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 9:{
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica la fecha de avance
						  if(verificarTipoDato(valor, "fecha")){
							  ap.setFechaAvance(Utilerias.convertirStringToDateMMDDYYY(valor));  
						  }else{
							  msj = "Fecha de avance Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Fecha invalida";
							  crearCeldaenLogXls();
							  errorEnCampo = true;
						  }
					  }else{
						  msj = "Fecha de avance es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }
			  }
		  
		
	}
	
	
	private String  recuperarDatoDeCelda(Cell cell) {
		String valor = "";
         switch(cell.getCellType()) {
         case Cell.CELL_TYPE_BOOLEAN:
        	 Boolean vBoolean = cell.getBooleanCellValue();
        	 valor = vBoolean.toString();
             break;
         case Cell.CELL_TYPE_NUMERIC:
        	 Double vDouble = null;
        	 Date valorDate = null;
        	 if (HSSFDateUtil.isCellDateFormatted(cell)){
        		 	valorDate = cell.getDateCellValue();
        		 	AppLogger.info("app",valorDate);
        		 	valor = new SimpleDateFormat("MMM-dd-yyyy").format(valorDate); 
				}else{
					vDouble = cell.getNumericCellValue();
					valor = vDouble.toString();
				}			
             break;
         case Cell.CELL_TYPE_STRING:
        	 valor = cell.getStringCellValue();
             break;
         case Cell.CELL_TYPE_FORMULA:
        	 valor = cell.getCellFormula();
             break;
         }         
         return valor.trim();
		
	}
	
	
	public String eliminarArchivoAvance(){
		//Verifica, que no se haya capturado un avance para 		
		registrosBorrados = aDAO.borrarUltimoArchivoAvancePagos(claveAviso);
		return SUCCESS;
	}
	private void crearCeldaenLogXls() {		
		rowLog = sheetLog.createRow(++countRowLog);
		cellLog = rowLog.createCell(0);
		cellLog.setCellValue(msj);
	}

	private HSSFSheet setMargenSheet(HSSFSheet sheet){
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		sheet.setMargin(Sheet.LeftMargin, 0.25);
		sheet.setMargin(Sheet.RightMargin, 0.25);
		sheet.setMargin(Sheet.TopMargin, 0.75);
		sheet.setMargin(Sheet.BottomMargin, 0.75);
	    sheet.getPrintSetup().setLandscape(true);
        sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.LEGAL_PAPERSIZE);
        
		// Setup the Header and Footer Margins
		sheet.setMargin(Sheet.HeaderMargin, 0.25);
		sheet.setMargin(Sheet.FooterMargin, 0.25);
		Header header = sheet.getHeader();
		header.setRight(df.format(date));
		
		return sheet;
	}
	
	@SuppressWarnings("unused")
	private boolean verificarTipoDato(String valor, String tipoDato) {
		boolean esTipoDatoPermitido = false;
	
		if(tipoDato.equals("caracter")){
			esTipoDatoPermitido = true;			
		}else if(tipoDato.equals("fecha")){	
			SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
			try {				 
				Date date = formatter.parse(valor);
				esTipoDatoPermitido = true;			 
			} catch (Exception e) {
				esTipoDatoPermitido = false;	
			}				
		}else if(tipoDato.equals("volumen") || tipoDato.equals("importe") || tipoDato.equals("numero")){
			valor = valor.replace(",", "");
			try{
				double decimal = Double.parseDouble(valor);
				esTipoDatoPermitido = true;
			}catch(Exception e){
				AppLogger.info("app","No es un valor decimal o numero");
				esTipoDatoPermitido = false;
			}
		}
		return esTipoDatoPermitido;
	}

    public String getClaveAviso() {
		return claveAviso;
	}

	public void setClaveAviso(String claveAviso) {
		this.claveAviso = claveAviso;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public AvisosDofV getAdv() {
		return adv;
	}
	
	public void setAdv(AvisosDofV adv) {
		this.adv = adv;
	}
	
	public int getErrorSistema() {
		return errorSistema;
	}

	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
	}
	
	public String getRutaLog() {
		return rutaLog;
	}

	public void setRutaLog(String rutaLog) {
		this.rutaLog = rutaLog;
	}

	public String getNombreArchivoLogCargaXls() {
		return nombreArchivoLogCargaXls;
	}

	public void setNombreArchivoLogCargaXls(String nombreArchivoLogCargaXls) {
		this.nombreArchivoLogCargaXls = nombreArchivoLogCargaXls;
	}
	
	public List<String> getLstLog() {
		return lstLog;
	}

	public void setLstLog(List<String> lstLog) {
		this.lstLog = lstLog;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		
	}

	
	public int getRegistrosBorrados() {
		return registrosBorrados;
	}

	
	public void setRegistrosBorrados(int registrosBorrados) {
		this.registrosBorrados = registrosBorrados;
	}
		
	
}
