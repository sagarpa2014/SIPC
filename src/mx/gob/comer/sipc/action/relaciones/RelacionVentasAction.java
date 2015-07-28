package mx.gob.comer.sipc.action.relaciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.ClienteDeParticipante;
import mx.gob.comer.sipc.domain.catalogos.Pais;
import mx.gob.comer.sipc.domain.catalogos.TipoUsoGrano;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.relaciones.RelacionVentas;
import mx.gob.comer.sipc.vistas.domain.relaciones.RelacionVentasV;

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
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionSupport;



@SuppressWarnings("serial")
public class RelacionVentasAction extends ActionSupport implements SessionAware{

	@SessionTarget
	Session sessionTarget;
	@TransactionTarget
	Transaction transaction;
	private Map<String, Object> session;
	
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private CatalogosDAO cDAO = new CatalogosDAO();
	private RelacionesDAO rDAO = new RelacionesDAO();
	private String folioCartaAdhesion; 
	private String nombrePrograma; 
	private String nombreComprador;
	private List<ClienteDeParticipante> lstClienteDeParticipante;
	private List<TipoUsoGrano> lstTipoUsoGrano;
	private List<Pais> lstDestinoInternacional;
	private List<Estado> lstDestinoNacional;
	private boolean aplicaInternacional;
	private int registrar;
	private RelacionVentas rv;
	private int error;
	private long idRelVentas;
	private String cliente;
	private String destino;
	private String usoGrano;
	private List<RelacionVentasV> lstRelVentasV;
	private File doc; 
	private String docFileName;
	private String ext;
	private int contRow;
	private int contColumna;
	private HSSFWorkbook wbLog; //inicializar excel para log 
	private HSSFSheet sheetLog;
	private Cell cellLog;
	private int countRowLog;
	private Row rowLog;
	private String msj;
	private List<String> lstLog;
	private boolean errorCartaAdhesion;
	private ArrayList<RelacionVentas> lstRelVentas;
	private int tipoCargaRelVentas;
	private String nombreArchivoLogCargaXls;
	private String rutaCartaAdhesion;
	private int errorSistema;
	private boolean errorEnCampo;
	private String direccionCliente;
	
	
	
	public String capRelacionVentas(){		
		AsignacionCAaEspecialistaV acaaev =  spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		nombrePrograma = acaaev.getPrograma();
		nombreComprador = acaaev.getNombreComprador();
		//Verifica si ya tiene registros de relacion de ventas
		lstRelVentasV = rDAO.consultaRelVentasV(0, folioCartaAdhesion);
		return SUCCESS;
	}	
	
	public String getTablaRelVentas(){
		try{			
			lstClienteDeParticipante = cDAO.consultaClienteDeParticipante(0,"","");
			lstTipoUsoGrano = cDAO.consultaTipoUsoGrano(0);
			lstDestinoNacional = cDAO.consultaEstado(0);
			if(registrar == 1){
				System.out.println("id "+idRelVentas);
				rv = rDAO.consultaRelacionVenta(idRelVentas).get(0);
			}
			
			
		}catch(JDBCException e) {	
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	
	public String registrarRelVentas(){
		try{	
			RelacionVentas relVentas = null; 
			if(registrar == 0){
				relVentas = new RelacionVentas();
			}else{
				relVentas = rDAO.consultaRelacionVenta(idRelVentas).get(0);
				rv.setId(idRelVentas);
			}
			relVentas.setFolioCartaAdhesion(folioCartaAdhesion);
			relVentas.setIdCliente(rv.getIdCliente());
			relVentas.setFolioFactura(rv.getFolioFactura());
			relVentas.setFecha(rv.getFecha());
			relVentas.setVolumen(rv.getVolumen());
			relVentas.setIdUsoGrano(rv.getIdUsoGrano());
			relVentas.setInternacional(aplicaInternacional);
			if(aplicaInternacional){
				relVentas.setIdPais(rv.getIdPais());
			}else{
				relVentas.setIdEstado(rv.getIdPais());
			}
						
			relVentas = (RelacionVentas) cDAO.guardaObjeto(relVentas);
			//Recupera el registro de la relacion de ventas			
			RelacionVentasV relVentasV = rDAO.consultaRelVentasV(relVentas.getId(), folioCartaAdhesion).get(0);	
			idRelVentas = relVentasV.getId();
			cliente =  relVentasV.getCliente();
			direccionCliente = relVentasV.getDireccionCliente();
			System.out.println("Direccion cliente "+direccionCliente);
			destino = relVentasV.getDestino();
			usoGrano = relVentasV.getDesUsoGrano();
			error = 0;			
			
		}catch(JDBCException e) {	
			e.printStackTrace();
			error = 1;
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}		
		return SUCCESS;
	}
		
	public String consigueDestinoRelVentas(){
		
		if(aplicaInternacional){
			lstDestinoInternacional =  cDAO.consultaPais(0, null); 
		}else{
			lstDestinoNacional = cDAO.consultaEstado(0);
		}	
		
		return SUCCESS;
	}
	
	public String cargaIndRelVentas(){
		lstRelVentasV = rDAO.consultaRelVentasV(0, folioCartaAdhesion);
		return SUCCESS;
	}
	
	public String cargaMasivaRelVentas(){
		return SUCCESS;
	}
		
	public String registraArchivoRelVentas(){
		try{	
			wbLog = new HSSFWorkbook(); //inicializar excel para log 	
			lstLog =  new ArrayList<String>(); 
			rutaCartaAdhesion = getRecuperaRutaCarta();	
			XSSFWorkbook wbXSSF = null;
			XSSFSheet sheetXSSF = null;
			Iterator<Row> rowIterator = null;
			FileInputStream inp = new FileInputStream(doc);
			ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf("."));
			if(ext.equals(".xlsx")||ext.equals(".XLSX")){
				wbXSSF =  new XSSFWorkbook (inp);	
			}
			
			for(int i=0; i<wbXSSF.getNumberOfSheets(); i++){
				sheetXSSF = wbXSSF.getSheetAt(i);
				rowIterator = sheetXSSF.iterator();
				lstLog.add("Extrayendo información de la hoja "+(i+1));
				sheetLog = wbLog.createSheet("LOG CARGA ARCHIVO "+(i+1));
				sheetLog = setMargenSheet(sheetLog);
				countRowLog = 0;
				crearCeldaenLogXls();
				leerPagina(rowIterator);
				guardarRelVentas((i+1));
			}
			
			nombreArchivoLogCargaXls = "LogCargaArchivoRelVentas.xls";  
			FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogCargaXls));
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
	
	private void guardarRelVentas(int numeroHoja) {
		 int numRegGuardados = 0, totalRegistrosDetalle = 0, numRegNoGuardados=0;
		 for(RelacionVentas r: lstRelVentas){
			 cDAO.guardaObjeto(r);
			 numRegGuardados++;
		 }
		 totalRegistrosDetalle = (contRow - 2 );
		 msj = "Se guardaron "+numRegGuardados+" registros en la base de datos de la hoja "+numeroHoja;
		 lstLog.add(msj);
		 crearCeldaenLogXls();		
		 numRegNoGuardados = totalRegistrosDetalle-numRegGuardados;
		 if(numRegNoGuardados > 0){
				msj = "No fue posible guardar "+(numRegNoGuardados == 1?"registro":"registros")+" en la base de datos de la hoja"+numeroHoja+", por favor verifique";
				lstLog.add(msj);
				crearCeldaenLogXls();
		}	
		 
		 msj = "Total registros del detalle "+(totalRegistrosDetalle>1?totalRegistrosDetalle:0)+" de la hoja "+numeroHoja;
		 lstLog.add(msj);
		 crearCeldaenLogXls();
	}

	private void leerPagina(Iterator<Row> rowIterator) throws ParseException {
		contRow = 0;
		contColumna = 0;
		String valor = "";
		boolean bandFinArchivo = false;
		
		lstRelVentas = new ArrayList<RelacionVentas>();
		while(rowIterator.hasNext()){
			contColumna = 0;	
			Row row = rowIterator.next();
			errorEnCampo = false;
			if(errorCartaAdhesion){
				break;
			}
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				 valor =  recuperarDatoDeCelda(cellIterator.next());
	 			 if (valor != null && !valor.equals("")){
				   bandFinArchivo = true;
				 }	 			 
	 			 if(contRow == 0){
				   if(contColumna == 1){
					   if(valor==null || valor.isEmpty()){
						   msj = "";						   
						   crearCeldaenLogXls();
						   errorCartaAdhesion = true;
						   break;
						   
					   }else{
						   if(!folioCartaAdhesion.equals(valor)){
							   msj = "El folio de la carta de adhesión del archivo no coincide con  el sistema";
							   crearCeldaenLogXls();
							   errorCartaAdhesion = true;
						   }
					   }		
				   }  
	 			 }else{
	 				 
	 				 
	 			 }	 			 
	 			 if(contRow > 1 && contColumna < 8 ){
	 				 if(contColumna == 0){
	 					rv = new RelacionVentas();
		 				rv.setFolioCartaAdhesion(folioCartaAdhesion); 
	 				 }
	 				
	 				getValidacionDetalle(valor);
	 				if(contColumna == 7){
	 					if(!errorEnCampo){
		 					lstRelVentas.add(rv);
		 				}
	 				}
	 				
	 			 }
	 			contColumna++;
				if(contColumna == 8){
					break; //en caso de que haya mas columnas en el archivo omitir
				} 
			}//end while cellIterator
			
			if(bandFinArchivo){
				
			}else{
				 break;
			}	
			contRow++;
			AppLogger.info("app","termina fila");
		}//while(rowIterator.hasNext())
	}	
	
	private void getValidacionDetalle(String valor) throws ParseException {
		  if(valor!=null && !valor.isEmpty()){	  
			  switch(contColumna){
				  case 0:{
					  //Verifica si el RFC  del cliente existe
					 List<ClienteDeParticipante> lstCliente = cDAO.consultaClienteDeParticipante(0,"", valor);
					 if(lstCliente.size()>0){
						 rv.setIdCliente(lstCliente.get(0).getId());
					 }else{
						 msj = "RFC del Cliente: Fila :"+(contRow+2)+" Columna: "+(contColumna+1)+". El cliente no se encuentra en la base de datos";
						 errorEnCampo = true;
						 crearCeldaenLogXls();
					 }
					 break;
				  }
				  case 1:{
					  //Valida el folio de la factura
					  try{
							Double d = Double.parseDouble(valor);
							valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
						}catch (Exception e){
							
						}
					  rv.setFolioFactura(valor);	
					  break;
				  }
				  case 2:{
					  //Valida la fecha de la factura
					  if(verificarTipoDato(valor, "fecha")){
						  rv.setFecha(Utilerias.convertirStringToDateMMDDYYY(valor));  
					  }else{
						  msj = "Fecha de la Factura Fila :"+(contRow+2)+" Columna: "+(contColumna+1)+". Fecha invalida";
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }	
					  break;
				  }
				  case 3:{
					  //Valida el volumen de la factura
					  if(verificarTipoDato(valor, "volumen")){
						  rv.setVolumen(Double.parseDouble(valor));
					  }else{
						  msj = "Volumen de la Factura: Fila :"+(contRow+2)+" Columna: "+(contColumna+1)+". Volumen no valido";
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 4:{
					  //Valida el Aplica Internacional 
					  if(valor.equalsIgnoreCase("S")||valor.equalsIgnoreCase("N") ){
						  if(valor.equalsIgnoreCase("S")){
							  rv.setInternacional(true);
						  }else{
							  rv.setInternacional(false);
						  }
					  }else{
						  msj = "Aplica Pais Internacional: Fila:"+(contRow+2)+" Columna: "+(contColumna+1)+". Solo se permite el valor \"S\" o \"N\"";
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 5:{
					  //Verifica el id del pais
					  if(verificarTipoDato(valor, "numero")){
						 Double d = Double.parseDouble(valor);
						 valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
						 lstDestinoInternacional =  cDAO.consultaPais(Integer.parseInt(valor), null);
						 if(lstDestinoInternacional.size() > 0){
							 rv.setIdPais(Integer.parseInt(valor));
						 }else{
							 msj = "id del pais no se encontro en la base de datos: Fila :"+(contRow+2)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
						 }
					  }else{
						  msj = "id del pais invalido: Fila :"+(contRow+2)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }case 6:{
					  //Verifica el id del estado
					  if(verificarTipoDato(valor, "numero")){
						 Double d = Double.parseDouble(valor);
						 valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
						 lstDestinoNacional =  cDAO.consultaEstado(Integer.parseInt(valor));
						 if(lstDestinoNacional.size() > 0){
							 rv.setIdEstado(Integer.parseInt(valor));
						 }else{
							 msj = "id del estado no se encontro en la base de datos: Fila :"+(contRow+2)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
						 }
					  }else{
						  msj = "id del estado invalido: Fila :"+(contRow+2)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }	
					  break;
				  }case 7:{
					  //Verifica el id del uso de grano
					  if(verificarTipoDato(valor, "numero")){
						 Double d = Double.parseDouble(valor);
						 valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
						 lstTipoUsoGrano =  cDAO.consultaTipoUsoGrano(Integer.parseInt(valor));
						 if(lstTipoUsoGrano.size() > 0){
							 rv.setIdUsoGrano(Integer.parseInt(valor));
						 }else{
							 msj = "id del uso de grano no se encontro en la base de datos: Fila :"+(contRow+2)+" Columna: "+(contColumna+1);
							 crearCeldaenLogXls();
							 errorEnCampo = true;
						 }
					  }else{
						  msj = "id del uso de grano invalido: Fila :"+(contRow+2)+" Columna: "+(contColumna+1);
						  crearCeldaenLogXls();
						  errorEnCampo = true;
					  }
					  break;
				  }
			  }
		  }else{
			  
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
	
	
	private String getRecuperaRutaCarta() throws JDBCException, Exception{
		String  nomRutaCartaAdhesion = "";	
		//Recupera la ruta de la solicitud de pago
		AsignacionCAaEspecialistaV acaaev= spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		//Recupera la ruta del programa
		Programa programa = cDAO.consultaPrograma(acaaev.getIdPrograma()).get(0);
		nomRutaCartaAdhesion = folioCartaAdhesion.replaceAll("-", "");
		String ruta = programa.getRutaDocumentos()+"SolicitudPago/"+acaaev.getIdOficioCASP()+"/"+nomRutaCartaAdhesion+"/";
		Utilerias.crearDirectorio(ruta);
		return ruta;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public List<ClienteDeParticipante> getLstClienteDeParticipante() {
		return lstClienteDeParticipante;
	}

	public void setLstClienteDeParticipante(
			List<ClienteDeParticipante> lstClienteDeParticipante) {
		this.lstClienteDeParticipante = lstClienteDeParticipante;
	}

	public List<TipoUsoGrano> getLstTipoUsoGrano() {
		return lstTipoUsoGrano;
	}

	public void setLstTipoUsoGrano(List<TipoUsoGrano> lstTipoUsoGrano) {
		this.lstTipoUsoGrano = lstTipoUsoGrano;
	}


	public List<Pais> getLstDestinoInternacional() {
		return lstDestinoInternacional;
	}

	public void setLstDestinoInternacional(List<Pais> lstDestinoInternacional) {
		this.lstDestinoInternacional = lstDestinoInternacional;
	}

	public List<Estado> getLstDestinoNacional() {
		return lstDestinoNacional;
	}

	public void setLstDestinoNacional(List<Estado> lstDestinoNacional) {
		this.lstDestinoNacional = lstDestinoNacional;
	}

	public boolean isAplicaInternacional() {
		return aplicaInternacional;
	}

	public void setAplicaInternacional(boolean aplicaInternacional) {
		this.aplicaInternacional = aplicaInternacional;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public RelacionVentas getRv() {
		return rv;
	}

	public void setRv(RelacionVentas rv) {
		this.rv = rv;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public long getIdRelVentas() {
		return idRelVentas;
	}

	public void setIdRelVentas(long idRelVentas) {
		this.idRelVentas = idRelVentas;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getUsoGrano() {
		return usoGrano;
	}

	public void setUsoGrano(String usoGrano) {
		this.usoGrano = usoGrano;
	}

	public List<RelacionVentasV> getLstRelVentasV() {
		return lstRelVentasV;
	}

	public void setLstRelVentasV(List<RelacionVentasV> lstRelVentasV) {
		this.lstRelVentasV = lstRelVentasV;
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

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	public List<String> getLstLog() {
		return lstLog;
	}

	public void setLstLog(List<String> lstLog) {
		this.lstLog = lstLog;
	}

	public int getTipoCargaRelVentas() {
		return tipoCargaRelVentas;
	}

	public void setTipoCargaRelVentas(int tipoCargaRelVentas) {
		this.tipoCargaRelVentas = tipoCargaRelVentas;
	}

	public String getNombreArchivoLogCargaXls() {
		return nombreArchivoLogCargaXls;
	}

	public void setNombreArchivoLogCargaXls(String nombreArchivoLogCargaXls) {
		this.nombreArchivoLogCargaXls = nombreArchivoLogCargaXls;
	}

	public int getErrorSistema() {
		return errorSistema;
	}

	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
	}

	public String getRutaCartaAdhesion() {
		return rutaCartaAdhesion;
	}

	public void setRutaCartaAdhesion(String rutaCartaAdhesion) {
		this.rutaCartaAdhesion = rutaCartaAdhesion;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}	

}