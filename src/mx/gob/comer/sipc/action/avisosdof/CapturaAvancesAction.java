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
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.catalogos.ProgAviso;
import mx.gob.comer.sipc.domain.transaccionales.AvancePagos;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AvancePagosV;
import mx.gob.comer.sipc.vistas.domain.AvisosDofDetalleV;
import mx.gob.comer.sipc.vistas.domain.AvisosDofV;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
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
	private boolean errorEnArchivo;
	private String msj;
	private int contRow;
	private int contColumna;
	private AvancePagos ap;
	private ArrayList<AvancePagos> lstAvancePagos;
	private int idEstado;
	private int idCultivo;
	private int idApoyo;	
	private String cicloAgricola;
	private Date fechaRegistro;
	private Date fechaRegistro1;
	private Date fechaAvance;
	private String rutaLog;
	private String folioArchivo;
	private int registrosBorrados;
	private int idProgAviso;
	private HSSFCellStyle csCamposDetalleFecha;
	private CreationHelper createHelper;
	private List<ProgAviso> lstPgrAviso;
	private List<Estado> lstEstados;
	private List<Cultivo> lstCultivo;
	private boolean bandera;
	private String cuadroSatisfactorio;

	private List<AvancePagosV> lstAvancePagosV;

	private int countColumn;

	private List<AvancePagos> lstFechasRegistro;
	
	public String capturaAvancePagos(){
		try{			
			//adv = aDAO.getAvisosDofV(claveAviso).get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String registraArchivoAvancePagos(){
		try{	
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
				errorEnArchivo = false; 
				sheetXSSF = wbXSSF.getSheetAt(i);
				rowIterator = sheetXSSF.iterator();
				lstLog.add("Extrayendo información de la hoja "+(i+1));
				sheetLog = wbLog.createSheet("LOG CARGA ARCHIVO "+(i+1));
				sheetLog = setMargenSheet(sheetLog);
				countRowLog = 0;
				crearCeldaenLogXls();
				leerPagina(rowIterator);
				if(!errorEnArchivo){
					guardarAvancePagos(i+1);
					cuadroSatisfactorio = "La información se guardo satisfactoriamente en la base de datos";
				}else{
					addActionError("No se guardó ningun registro en la base de datos, por favor veifique el log de procesos");
					break;
				}
				
			}			
			nombreArchivoLogCargaXls = "LogCargaArchivoAvancePagos.xls";  
			rutaLog = "/SIPC/logs/";
			FileOutputStream out = new FileOutputStream(new File(rutaLog+nombreArchivoLogCargaXls));
		    wbLog.write(out);
		    out.close();
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraArchivoRelVentas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			errorSistema = 1;
		}
		
		return SUCCESS;
	}
		
	private void guardarAvancePagos(int numeroHoja) {
		 int numRegGuardados = 0, totalRegistrosDetalle = 0;//, numRegNoGuardados=0;
		 for(AvancePagos a: lstAvancePagos){
			 cDAO.guardaObjeto(a);
			 numRegGuardados++;
		 }
		 totalRegistrosDetalle = (contRow - 1);
		 msj = "Se guardaron "+numRegGuardados+" registros en la base de datos de la hoja "+numeroHoja;
		 lstLog.add(msj);
		 crearCeldaenLogXls();		
//			 numRegNoGuardados = totalRegistrosDetalle-numRegGuardados;
//			 if(numRegNoGuardados > 0){
//					msj = "No fue posible guardar "+(numRegNoGuardados == 1?numRegNoGuardados+" registro":numRegNoGuardados+" registros")+" en la base de datos de la hoja"+numeroHoja+", por favor verifique";
//					lstLog.add(msj);
//					crearCeldaenLogXls();
//			}	
//			 
		 msj = "Total registros del detalle "+(totalRegistrosDetalle>1?totalRegistrosDetalle:0)+" de la hoja "+numeroHoja;
		 lstLog.add(msj);
		 crearCeldaenLogXls();
		 //Vaciar informacion cargada 
		 getExportaInfoCargada();		  
		 
		  
	}
	
	private void getExportaInfoCargada() {
		 int countColumn = 0;		 
		 rowLog = sheetLog.createRow(++countRowLog);
		 cellLog = rowLog.createCell(countColumn);
		 cellLog.setCellValue("Detalle de Registros Cargados"); 
		 rowLog = sheetLog.createRow(++countRowLog);
		 cellLog = rowLog.createCell(countColumn);
		 cellLog.setCellValue("Clave Apoyo"); 						 	 	
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Estado");
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Programa");		 
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Cultivo");		 
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Ciclo");		 
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Solicitudes	");		 
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Volumen Pagado (ton)");
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Importe Pagado");
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Productores");
		 cellLog = rowLog.createCell(++countColumn);
		 cellLog.setCellValue("Fecha de Pago");
		 for(AvancePagos a: lstAvancePagos){
			 countColumn = 0;
			 rowLog = sheetLog.createRow(++countRowLog);
			 cellLog = rowLog.createCell(countColumn);
			 cellLog.setCellValue(a.getIdApoyo());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getIdEstado());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getPrograma());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getIdCultivo());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getCicloAgricola());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getSolicitudes());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getVolumen());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getImporte());
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue(a.getProductores());
			 cellLog = rowLog.createCell(++countColumn);
			 createHelper =  wbLog.getCreationHelper();
			 csCamposDetalleFecha = wbLog.createCellStyle();
			 csCamposDetalleFecha.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yy"));
			 cellLog.setCellValue(a.getFechaAvance());
			 cellLog.setCellStyle(csCamposDetalleFecha);
		 }
		
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
 							List<AvisosDofDetalleV> lstAvisoDofDetalle = aDAO.getAvisosDofDetalleV(-1, idProgAviso, idApoyo, idCultivo,
 									cicloAgricola,  idEstado,0);
 							if(lstAvisoDofDetalle.size() == 0){
 								 msj = "Configuración no registrada  : Fila :"+(contRow+2);
 								 crearCeldaenLogXls();
 								errorEnCampo = true;
 								errorEnArchivo = true;
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
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica el id del estado
						  if(verificarTipoDato(valor, "numero")){
							  Double d = Double.parseDouble(valor);
							  valor = TextUtil.formateaNumeroComoCantidadSincomas(d);  
							 idApoyo = Integer.parseInt(valor);
							 ap.setIdApoyo(idApoyo);
						  }else{
							 msj = "id del apoyo invalido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
							 errorEnArchivo = true;
						  }	
					  }else{
						  msj = "Id del apoyo es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					break;
				  }
				  case 1:{//Estado
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
							 errorEnArchivo = true;
						  }	
					  }else{
						  msj = "Id del estado es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }
				  case 2:{//PROGRAMA
					  if(valor !=null && !valor.isEmpty()){
						//Verifica el PROGRAMA
//						  Double d = Double.parseDouble(valor);
//						  valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
						  //Recupera el programa
						  List<ProgAviso> lstProgAviso = cDAO.getProgAviso(0, valor);
						  if(lstProgAviso.size()>0){
							  idProgAviso  = lstProgAviso.get(0).getId();
							  ap.setPrograma(lstProgAviso.get(0).getId());  
						  }else{
							  msj = "programa no registrado: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
							  crearCeldaenLogXls();
							  errorEnCampo = true;
							  errorEnArchivo = true;
						  }				  
						  						  
					  }else{
						  msj = "Id del programa es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }
				  case 3:{//CULTIVO
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
							 errorEnArchivo = true;
						 }	
					  }else{
						  msj = "Id del cultivo es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }case 4:{//CICLO AGRICOLA
					  if(valor !=null && !valor.isEmpty()){
						  //Valida Ciclo
						  cicloAgricola = valor;
						  ap.setCicloAgricola(valor);
					  }else{
						  msj = "Ciclo Agricola es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }case 5:{//SOLICITUDES
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
								 errorEnArchivo = true;
						 }
					  }else{
						  msj = "Número de solicitudes es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }case 6:{//VOLUMEN
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica el volumen
						  if(verificarTipoDato(valor, "volumen")){
							  ap.setVolumen(Double.parseDouble(valor)	);
						  }else{
							  msj = "Volumen de la Factura: Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Volumen no valido";
							  crearCeldaenLogXls();
							  errorEnCampo = true;
							  errorEnArchivo = true;
						  }
					  }else{
						  msj = "Volumen es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }case 7:{//IMPORTE
					  if(valor !=null && !valor.isEmpty()){
						  //Verifica el importe
						  if(verificarTipoDato(valor, "importe")){
							  ap.setImporte(Double.parseDouble(valor));
						  }else{
							  msj = "Importe: Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Importe no valido";
							  crearCeldaenLogXls();
							  errorEnCampo = true;
							  errorEnArchivo = true;
						  }
					  }else{
						  msj = "Importe es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
					  }
					  break;
				  }case 8:{//PRODUCTORES
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
							  errorEnArchivo = true;
						  }
					  }else{
						  msj = "Número de productores es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
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
							  errorEnArchivo = true;
						  }
					  }else{
						  msj = "Fecha de avance es valor requerido: Fila :"+(contRow+1)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
						  errorEnArchivo = true;
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
		registrosBorrados = aDAO.borrarUltimoArchivoAvancePagos();
		return SUCCESS;
	}
	
	public String consultaAvancePagos() throws Exception{ 		
		//Cargar las lista para la consulta
		lstPgrAviso = cDAO.getProgAviso(0, null);
		lstEstados = cDAO.consultaEstado(0);
		lstCultivo = cDAO.consultaCultivo();
		lstFechasRegistro = aDAO.getFechasRegistroAvancet();
				
		
		return SUCCESS;
	}
	public String ejecutaConsultaAvancePagos() throws Exception{ 		
		String fechaRegistroS = "";//, fechaAvanceS = "";
		if(fechaRegistro != null){
			fechaRegistroS = new SimpleDateFormat("yyyy-MM-dd").format(fechaRegistro);
		}
		if(fechaRegistro1 != null){
			fechaRegistroS = new SimpleDateFormat("yyyy-MM-dd").format(fechaRegistro1);
		}
//		if(fechaAvance != null){
//			fechaAvanceS = new SimpleDateFormat("yyyy-MM-dd").format(fechaAvance);
//		}		
//		
		lstAvancePagosV  = aDAO.getAvancePagosV(idProgAviso, idApoyo, idCultivo, idEstado, 
				cicloAgricola, fechaRegistroS,null);	
		
		if(lstAvancePagosV.size() > 0){	
			wbLog = new HSSFWorkbook(); //inicializar excel
			//Exporta informacion a un excel
			sheetLog = wbLog.createSheet("DATOS ");
			sheetLog = setMargenSheet(sheetLog);
			//ENCABEZADO
			rowLog = sheetLog.createRow(++countRowLog);
			 cellLog = rowLog.createCell(countColumn);
			 cellLog.setCellValue("Ciclo");		 
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Apoyo"); 						 	 	
			 cellLog = rowLog.createCell(++countColumn);			
			 cellLog.setCellValue("Programa");		 
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Cultivo");		 
			 cellLog = rowLog.createCell(++countColumn);			 
			 cellLog.setCellValue("Estado");
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Solicitudes	");		 
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Volumen Pagado (ton)");
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Importe Pagado");
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Productores");
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Fecha de Pago");
			 cellLog = rowLog.createCell(++countColumn);
			 cellLog.setCellValue("Fecha de Registro");
			 //DETALLE			
			 for(AvancePagosV a: lstAvancePagosV){
				 countColumn = 0;
				 rowLog = sheetLog.createRow(++countRowLog);
				 cellLog = rowLog.createCell(countColumn);
				 cellLog.setCellValue(a.getCicloAgricola());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getApoyo());				 
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getPrograma());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getCultivo());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getEstado());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getSolicitudes());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getVolumen());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getImporte());
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getProductores());
				 cellLog = rowLog.createCell(++countColumn);
				 createHelper =  wbLog.getCreationHelper();
				 csCamposDetalleFecha = wbLog.createCellStyle();
				 csCamposDetalleFecha.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yy"));
				 cellLog.setCellValue(a.getFechaAvance());
				 cellLog.setCellStyle(csCamposDetalleFecha);
				 cellLog = rowLog.createCell(++countColumn);
				 cellLog.setCellValue(a.getFechaRegistro());
				 cellLog.setCellStyle(csCamposDetalleFecha);
			 }
			
			nombreArchivoLogCargaXls = "avancePagos.xls";  
			rutaLog = "/SIPC/logs/";
			FileOutputStream out = new FileOutputStream(new File(rutaLog+nombreArchivoLogCargaXls));
		    wbLog.write(out);
		    out.close();
	    
		}
	    consultaAvancePagos();
	    bandera = true;
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

	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	

	public Date getFechaAvance() {
		return fechaAvance;
	}

	public void setFechaAvance(Date fechaAvance) {
		this.fechaAvance = fechaAvance;
	}

	public List<ProgAviso> getLstPgrAviso() {
		return lstPgrAviso;
	}

	public void setLstPgrAviso(List<ProgAviso> lstPgrAviso) {
		this.lstPgrAviso = lstPgrAviso;
	}

	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}

	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}

	
	public String getCicloAgricola() {
		return cicloAgricola;
	}

	
	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	public int getIdProgAviso() {
		return idProgAviso;
	}

	public void setIdProgAviso(int idProgAviso) {
		this.idProgAviso = idProgAviso;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public String getCuadroSatisfactorio() {
		return cuadroSatisfactorio;
	}

	public void setCuadroSatisfactorio(String cuadroSatisfactorio) {
		this.cuadroSatisfactorio = cuadroSatisfactorio;
	}

	public List<AvancePagos> getLstFechasRegistro() {
		return lstFechasRegistro;
	}

	public void setLstFechasRegistro(List<AvancePagos> lstFechasRegistro) {
		this.lstFechasRegistro = lstFechasRegistro;
	}

	public Date getFechaRegistro1() {
		return fechaRegistro1;
	}

	public void setFechaRegistro1(Date fechaRegistro1) {
		this.fechaRegistro1 = fechaRegistro1;
	}	
		
}
