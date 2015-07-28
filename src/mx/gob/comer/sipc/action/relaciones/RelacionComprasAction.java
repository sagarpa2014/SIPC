package mx.gob.comer.sipc.action.relaciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.domain.catalogos.CatCriteriosValidacion;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcompras;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcomprasDetalle;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcomprasDetalleHCO;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcomprasHCO;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.ComprasContrato;
import mx.gob.comer.sipc.domain.transaccionales.ComprasDatosParticipante;
import mx.gob.comer.sipc.domain.transaccionales.ComprasDatosProductor;
import mx.gob.comer.sipc.domain.transaccionales.ComprasEntradaBodega;
import mx.gob.comer.sipc.domain.transaccionales.ComprasFacVenta;
import mx.gob.comer.sipc.domain.transaccionales.ComprasFacVentaGlobal;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPagoProdSinAxc;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPredio;
import mx.gob.comer.sipc.domain.transaccionales.DocumentacionSPCartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.CruceRelComprasError;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.ComprasDatosParticipanteV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionV;
import mx.gob.comer.sipc.vistas.domain.PersonalizacionRelacionesProgramaV;
import mx.gob.comer.sipc.vistas.domain.relaciones.BoletasCamposRequeridos;
import mx.gob.comer.sipc.vistas.domain.relaciones.BoletasDuplicadas;
import mx.gob.comer.sipc.vistas.domain.relaciones.BoletasFacturasPagosIncosistentes;
import mx.gob.comer.sipc.vistas.domain.relaciones.BoletasFueraDePeriodo;
import mx.gob.comer.sipc.vistas.domain.relaciones.BoletasFueraDePeriodoPago;
import mx.gob.comer.sipc.vistas.domain.relaciones.BoletasVsFacturas;
import mx.gob.comer.sipc.vistas.domain.relaciones.ChequeFueraPeriodoAuditor;
import mx.gob.comer.sipc.vistas.domain.relaciones.ChequeFueraPeriodoContrato;
import mx.gob.comer.sipc.vistas.domain.relaciones.ChequesDuplicadoBancoPartipante;
import mx.gob.comer.sipc.vistas.domain.relaciones.ContratosRelacionCompras;
import mx.gob.comer.sipc.vistas.domain.relaciones.FacturaFueraPeriodoAuditor;
import mx.gob.comer.sipc.vistas.domain.relaciones.FacturaFueraPeriodoPago;
import mx.gob.comer.sipc.vistas.domain.relaciones.FacturaFueraPeriodoPagoAdendum;
import mx.gob.comer.sipc.vistas.domain.relaciones.FacturasCamposRequeridos;
import mx.gob.comer.sipc.vistas.domain.relaciones.FacturasIgualesFacAserca;
import mx.gob.comer.sipc.vistas.domain.relaciones.FacturasVsPago;
import mx.gob.comer.sipc.vistas.domain.relaciones.GeneralToneladasTotalesPorBodFac;
import mx.gob.comer.sipc.vistas.domain.relaciones.PagosCamposRequeridos;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrecioPagPorTipoCambio;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrecioPagadoMenor;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrecioPagadoNoCorrespondeConPagosV;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrecioPagadoProductor;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrediosNoPagados;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrediosNoValidadosDRDE;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrediosProdsContNoExisteBD;
import mx.gob.comer.sipc.vistas.domain.relaciones.ProductorExisteBD;
import mx.gob.comer.sipc.vistas.domain.relaciones.ProductorFactura;
import mx.gob.comer.sipc.vistas.domain.relaciones.ProductorPredioPagado;
import mx.gob.comer.sipc.vistas.domain.relaciones.ProductorPredioValidado;
import mx.gob.comer.sipc.vistas.domain.relaciones.ProductoresIncosistentes;
import mx.gob.comer.sipc.vistas.domain.relaciones.RendimientosProcedente;
import mx.gob.comer.sipc.vistas.domain.relaciones.RfcProductorVsRfcFactura;
import mx.gob.comer.sipc.vistas.domain.relaciones.VolumenFiniquito;
import mx.gob.comer.sipc.vistas.domain.relaciones.VolumenNoProcedente;

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
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RelacionComprasAction extends ActionSupport implements SessionAware, ServletContextAware {

	private Map<String, Object> session;
	private ServletContext context;
	private File doc;
	private String  docFileName;
	private List<String> lstLog = new ArrayList<String>();
	private String msj;
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private RelacionesDAO rDAO = new RelacionesDAO();
	private String folioCartaAdhesion; 
	private Programa programa;
	private int idPrograma;
	private Integer idComprador;
	private Integer idEstado;
	private String claveBodega;
	private String rutaCartaAdhesion;
	private String nombreArchivoLog;
	private int errorSistema;
	private boolean errorArchivo;
	private boolean errorInternoArchivo;
	private long idPerRel;
	private int contRow;
	private int contColumna;
	private Integer idCriterio;
	private int idCriterioG;
	private boolean pidePeriodo;
	private boolean periodoAcopioPrograma;
	 //Variable que determina que accion fue la que se selecciono {0, cargaArchivo; 1,Eliminar Archivo 2, Prevalidacion; 3, Generacion Bitacora; 4, Cargar Archivo}
	private int tipoAccion;
	private List<GruposCamposRelacionV> lstGruposCamposEncabezadoRelacionV;
	private List<GruposCamposRelacionV> lstGruposCamposDetalleRelacionV;
	private CartaAdhesion cartaAdhesion;
	private List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV; 
	private BitacoraRelcompras b; 
	private BitacoraRelcomprasDetalle bd; 
	private boolean existeRelacionYaValidada;
	private List<BitacoraRelcompras> lstBitacoraRelCompras;
	private List<RelacionComprasTMP>  lstRCTemp;
	private String nombreComprador;
	private String nombrePrograma;
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	private List<CatCriteriosValidacion> lstCatCriteriosValidacion;	
	private String rutaSalida;
	private String nombreArchivo;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaInicioAuditor;
	private Date fechaFinAuditor;
	private List<ProductorExisteBD> lstProductorExisteBD;
	private List<BoletasVsFacturas> lstBoletasVsFacturas;
	private List<BoletasDuplicadas> lstBoletasDuplicadas;
	private List<RelacionComprasTMP> lstPrediosNoExistenBD;
	private List<PrediosProdsContNoExisteBD> lstPrediosProdsContNoExistenBD;
	private List<BoletasFueraDePeriodo> lstBoletasFueraDePeriodo;
	private List<BoletasFueraDePeriodoPago> lstBoletasFueraDePeriodoPago;
	private List<ProductorFactura> lstProductorFactura;
	private List<RfcProductorVsRfcFactura> lstRfcProductorVsRfcFactura;
	private List<FacturasVsPago> lstFacturasVsPago;
	private List<PrediosNoValidadosDRDE> lstPrediosNoValidadosDRDE;
	private List<PrediosNoPagados> lstPrediosNoPagados;
	private List<ProductorPredioValidado> lstProductorPredioValidado;
	private List<FacturaFueraPeriodoAuditor> lstFacturaFueraPeriodo;
	private List<FacturaFueraPeriodoPago> lstFacturaFueraPeriodoPago;
	private List<ChequeFueraPeriodoAuditor> lstChequeFueraPeriodoAuditor;
	private List<BoletasFacturasPagosIncosistentes> lstBoletasFacturasPagosIncosistentes;
	private String rutaImagen;
	private String rutaMarcaAgua;
	private List<ChequeFueraPeriodoContrato> lstChequeFueraPeriodoContrato;
	private List<ProductorPredioPagado> lstProductorPredioPagado;
	private boolean aplicaAdendum;
	private boolean aplicaComplemento;
	private List<FacturaFueraPeriodoPagoAdendum> lstFacturaFueraPeriodoPagoAdendum;
	private List<ChequeFueraPeriodoContrato> lstChequeFueraPeriodoPagoAdendum;
	private List<ChequesDuplicadoBancoPartipante> lstChequesDuplicadoBancoPartipante;
	private List<PrecioPagadoProductor> lstPrecioPagadoProductor;	
	private double totalVolSolFacVenta;
	private double totalPrecioTonelada;
	private double totalImpSolFacVenta;
	private double totalPrecioPactadoPorTonelada;
	private double totalTipoCambio;
	private double totalPrecioPacXTipoCambio;
	private double totalPagarFacturaPnaPrecCal;
	private double totalDifMontoFac;
	private double totalDifMontoTotal;
	private List<ContratosRelacionCompras> lstContratos;
	private Map<String, Date> fechaContratoTipoCambio;
	private Map<String, Date> fechaInicioAdendumContrato;
	private Map<String, Date> fechaFinAdendumContrato;
	private Integer [] camposFactura;
	private List<FacturasIgualesFacAserca> lstFacturasIgualesFacAserca;
	private List<PrecioPagadoMenor> lstPrecioPagadoMenor;
	private Comprador comprador;
	private List<RelacionComprasTMP> lstComprasPagoProdSinAXC;
	private String cuadroSatisfactorio;
	private String grupoCriterio;
	private String grupoCriterioG;
	private List<ProductoresIncosistentes> lstProductoresIncosistentes;
	private List<CatCriteriosValidacion> lstValidacionPorGrupo;
	private String nombreArchivoLogXls;
	private int aplicaPeriodoLineamiento;
	private int aplicaPeriodoAuditor;
	private boolean noSeTieneConfPerEnPrg;
	private int pideTipoPeriodo;
	
	private List<BitacoraRelcompras> lstBitacoraRelComprasPorGrupo;
	private String nombreEstado;
	private String nombreArchivoExcel99;
	private String camposQueAplica;
	private List<BoletasCamposRequeridos> lstBoletasCamposRequeridos;
	private List<FacturasCamposRequeridos> lstFacturasCamposRequeridos;
	private List<PagosCamposRequeridos> lstPagosCamposRequeridos;
	private List<GeneralToneladasTotalesPorBodFac> lstGeneralToneladasTotalesPorBodFac;
	
	private HSSFWorkbook wbLog; //inicializar excel para log 
	private HSSFSheet sheetLog;
	private Cell cellLog;
	private int countRow;
	private Row row;
	private int countColumn;
	private boolean bandera;
	private RelacionComprasTMP relacionComprasTMP;
	@SuppressWarnings("unused")
	private String claveBodegaTmp="", nomEstadoTmp="";
	private boolean claveBodegaCorrecta, estadoCorrecto;
	private RelacionComprasTMP rcTmp;
	private boolean errorLecturaArchivo; 
	private String nombreArchivoLogCargaXls;
	private boolean errorCartaAdhesion;
	private boolean siSeguardoRelacion;
	private List<RendimientosProcedente> lstRendimientosProcedente;
	private List<VolumenFiniquito> lstVolumenCumplido;
	private Date fechaDeReporte;
	private List<PrecioPagPorTipoCambio> lstPrecioPagPorTipoCambio;
	private List<PrecioPagadoNoCorrespondeConPagosV> lstPrecioPagadoNoCorrespondeConPagosV;
	private List<PrecioPagadoNoCorrespondeConPagosV> lstPrecioPagadoMenorQueAviso;
	private List<RelacionComprasTMP> lstRfcVsCurpProductor;
	private List<RelacionComprasTMP> lstRfcProductorVsRfcFactura2;
	private List<VolumenNoProcedente> lstVolNoProcedenteYApoyEnReg;
	
	
	public String capturaCargaArchivoRelCompras(){ 
		//Verifica que no exista la relacion en la base de datos
		if(cartaAdhesion!=null){
			cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
		}
		//En este metodo recupera los datos del programa
		try {
			rutaCartaAdhesion = getRecuperaRutaCarta();
		}catch (JDBCException e) {
		e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}	

		AsignacionCAaEspecialistaV acaaev =  spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		idPrograma = acaaev.getIdPrograma();
		lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 1, null, null, null, null, idPrograma);
		if(lstPersonalizacionRelacionesProgramaV.size()>0){
			idPerRel = lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();
			//recupera la lista de criterios configurados en el layout del programa
			lstCatCriteriosValidacion = rDAO.consultaCatCriteriosValidacionByPrograma(idPerRel);
			//Verifica que no se encuentre ya cargada la relacion de compras en comprasDatosParticipante
			List<ComprasDatosParticipanteV> lstComprasDatosParticipanteV = rDAO.consultaComprasDatosParticipanteV(folioCartaAdhesion);
			nombrePrograma = acaaev.getPrograma();
			nombreComprador = acaaev.getNombreComprador();
			if(lstComprasDatosParticipanteV.size()>0){
				existeRelacionYaValidada = true;
			}	
			lstBitacoraRelCompras =	rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, "99", null);
			if(lstBitacoraRelCompras.size()>0){
				nombreArchivoExcel99 = lstBitacoraRelCompras.get(0).getNombreArchivo(); 
			}
		}else{
			addActionError("No es posible cargar el archivo, no se ha configurado el layout del programa");
		}					
		return SUCCESS;
	}
	
	@SuppressWarnings("unused")
	public String cargarArchivoRelCompras(){
		wbLog = new HSSFWorkbook(); //inicializar excel para log 		
		Row row = null;
		boolean band = false, band1 = false ;
		try{
			session = ActionContext.getContext().getSession();
			//Verifica si existe informacion en la tabla de relacion_compras_tmp
			lstRCTemp = rDAO.consultaRelacionComprasTMPLimit1(folioCartaAdhesion);
			if(lstRCTemp.size() == 0){
				
			}else{
				//Verifica la bitacora de historicos  
				List<BitacoraRelcompras> lstBitacoraHCO = rDAO.consultaBitacoraRelacionHCO(folioCartaAdhesion);
				if(lstBitacoraHCO.size()==0){
					addActionError("Debe realizar la prevalidación y generación de criterios para poder realizar otra carga o en su caso, elimine el archivo previamente cargado");
					return SUCCESS;
				}else if(lstBitacoraHCO.size()==1){					
					if (!rDAO.verificaCargaBitacoraRelCompras(folioCartaAdhesion).get(0).getCarga()){
						int elementoBorrados = rDAO.borrarRelacionComprasTmpByFolioCarta(folioCartaAdhesion);
						lstBitacoraRelCompras =	rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, null, null);
						if(lstBitacoraRelCompras.size()>0){						
							//borrar de la tabla bitacora_relcompras los registros de la bitacora del archivo historico
							for(BitacoraRelcompras b: lstBitacoraRelCompras){
								cDAO.borrarObjeto(b);
							}
						}
						AppLogger.info("app", "Se elimino de la tabla relacion_compras_tmp "+elementoBorrados+" por una nueva carga de archivo");						
					} else {
						addActionError("Debe realizar la prevalidación y generación de criterios para poder realizar otra carga o en su caso, elimine el archivo previamente cargado");
						return SUCCESS;						
					}
				}else{
					addActionError("El número de intentos de carga de la relación excedio su limite, solo es posible la carga de dos archivos");
					return SUCCESS;
				}

			}
			
			
			
//			//Verifica que si exista informacion en la tabla relacion_compras_tmp y en caso de que haya 
//			//verificar ultimo registro bitacora == 99 con archivo de excel cargado y estatus ok y al menos un registro 
//			//bitacora <> 99 con archivo pdf y estatus Error<
//			band = rDAO.verificaCriterio99OKmaxFechaRegistro(folioCartaAdhesion);
//			band1 = rDAO.verificaErrorCriterioDif99(folioCartaAdhesion);
//			
//			if(band){
//				if(!band1){
//					addActionError("No existen las condiciones adecuadas para poder cargar un archivo");
//					return SUCCESS;
//				}
//			}
//			//guardar la bitacora del ultimo archivo cargado, en caso de haber
//			lstBitacoraRelCompras =	rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, null, null);
//			if(lstBitacoraRelCompras.size()>0){
//				//Guardar en la tabla de historicos
//				guardarBitacoraHCOS();
//				//borrar de la tabla bitacora_relcompras los registros de la bitacora del archivo historico
//				for(BitacoraRelcompras b: lstBitacoraRelCompras){
//					cDAO.borrarObjeto(b);
//				}
//				//Borra registros de relacion_compras_tmp
//				int elementoBorrados = rDAO.borrarRelacionComprasTmpByFolioCarta(folioCartaAdhesion);
//				AppLogger.info("app", "Se elimino de la tabla relacion_compras_tmp "+elementoBorrados);
//			}			
//						
			//En este metodo recupera los datos del programa
			rutaCartaAdhesion = getRecuperaRutaCarta();	
			
			//Consigue el idPerRel de la configuracion de la relacion
			List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 1, null, null, null, null, programa.getIdPrograma());
			idPerRel =  lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();
						
			XSSFWorkbook wbXSSF = null;
			XSSFSheet sheetXSSF = null;
			HSSFWorkbook wbHSSF = null;
			HSSFSheet sheetHSSF = null;
			Iterator<Row> rowIterator = null;
			//Get the workbook instance for XLS file 
			FileInputStream inp = new FileInputStream(doc);
				
			String ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf("."));
			AppLogger.info("app","entrando a leer xls "+ext);
			if(ext.equals(".xlsx")||ext.equals(".XLSX")){
				wbXSSF =  new XSSFWorkbook (inp);	
				b = new BitacoraRelcompras();
				b.setFolioCartaAdhesion(folioCartaAdhesion);
				b.setFechaRegistro(new Date());
				b.setUsuarioCreacion((Integer) session.get("idUsuario"));
				b.setBitacoraRelcomprasDetalle(new HashSet<BitacoraRelcomprasDetalle>());
				
				for(int i=0; i<wbXSSF.getNumberOfSheets(); i++){
					errorCartaAdhesion = false;
					sheetXSSF = wbXSSF.getSheetAt(i);
					rowIterator = sheetXSSF.iterator();
					errorInternoArchivo = false;
					msj =  "Extrayendo información de la hoja "+(i+1);
					lstLog.add("Extrayendo información de la hoja "+(i+1));
					sheetLog = wbLog.createSheet("LOG CARGA ARCHIVO "+(i+1));
					sheetLog = setMargenSheet(sheetLog);
					countRow = 0;
					crearCeldaenLogXls();					
					leerPagina(rowIterator);
					guardarRelacioncompras(i+1);
				}
				
				if(siSeguardoRelacion){
					//Carga el archivo de la relacion de compras
					ext = docFileName.toLowerCase().substring(docFileName.lastIndexOf("."));
					String nombreArchivo = new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date())+ext; //
					nombreArchivoExcel99 = nombreArchivo ;
					Utilerias.cargarArchivo(rutaCartaAdhesion, nombreArchivo, doc);
					b.setNombreArchivo(nombreArchivo);
					b.setRutaArchivo(rutaCartaAdhesion);
					b.setStatus(1);//termino bien el proceso de estructura
					b.setIdCriterio(99);
					List<BitacoraRelcompras> lstBitacoraHCO = rDAO.consultaBitacoraRelacionHCO(folioCartaAdhesion);
					if(lstBitacoraHCO.size()==1){
						b.setCarga(true);
					}
					cDAO.guardaObjeto(b);
					if(errorLecturaArchivo){
						b.setStatus(0); //termino con error
					}else{
						b.setStatus(1);
					}
				}				
				
				//Recuperar el rfc de la factura de venta por productor
				//Setear valores de rfc_productor a rfc_fac_venta
				lstRCTemp = rDAO.consultaRFCFacturaVentaByProductor(folioCartaAdhesion);
				
				for(RelacionComprasTMP r : lstRCTemp){
					rDAO.actualizaRFCProductor(folioCartaAdhesion, r.getClaveBodega(), r.getNombreEstado(), r.getFolioContrato(), r.getPaternoProductor(), 
							r.getMaternoProductor(), r.getNombreProductor(), r.getRfcFacVenta());
				}	
			}else if(ext.equals(".xls")||ext.equals(".XLS")){
				wbHSSF = new HSSFWorkbook(inp);			
				sheetHSSF = wbHSSF.getSheetAt(0);			
				rowIterator = sheetHSSF.iterator();
			}	
			nombreArchivoLogCargaXls = "LogCargaArchivo.xls"; //Log del archivo excel  // AHS 29012015
			nombreArchivoLogXls = null; // AHS 29012015 
			FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogCargaXls));
		    wbLog.write(out);
		    out.close();
			
			if(!errorLecturaArchivo && siSeguardoRelacion){
				cuadroSatisfactorio = "La información se guardo satisfactoriamente en la base de datos";
			}else{
				addActionError("No se guardaron con exito los registros de la relación en la base de datos, por favor veifique el log de procesos");
			}
			capturaCargaArchivoRelCompras();
			
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en cargarArchivoRelCompras debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en cargarArchivoRelCompras  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			capturaCargaArchivoRelCompras();
			tipoAccion = 0;
		}
		return SUCCESS;
	}
	
	private void leerPagina(Iterator<Row> rowIterator) throws JDBCException, Exception{
		contRow = 0;
		contColumna = 0;
		String valor = "";	
		boolean bandFinArchivo = false;
		//Recupera la configuracion del encabezado de la relacion de compras
		//Recupera los encabezados de la configuracion de la relacion
		if(lstGruposCamposEncabezadoRelacionV == null){
			lstGruposCamposEncabezadoRelacionV = rDAO.consultaGruposCampostV(0, 0, idPerRel, 0, 0, "ENC");
		}				
		
		if(lstGruposCamposDetalleRelacionV == null){
			lstGruposCamposDetalleRelacionV = rDAO.consultaGruposCampostV(0, 0, idPerRel, 0, 0, "DET");
		}			
		lstRCTemp = new ArrayList<RelacionComprasTMP>();		
		bandFinArchivo = false;
		//Recupera el rfc del comprador 
		List<Comprador> comprador = cDAO.consultaComprador(idComprador);
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();					
			if(errorCartaAdhesion){
				break;
			}
			Iterator<Cell> cellIterator = row.cellIterator();
			AppLogger.info("app","Fila "+contRow);
			contColumna = 0;				
			if(contRow >=lstGruposCamposEncabezadoRelacionV.size()){
				rcTmp = new RelacionComprasTMP(); 
				rcTmp.setIdPerRel(idPerRel);
				rcTmp.setFolioCartaAdhesion(folioCartaAdhesion); 
				rcTmp.setIdComprador(idComprador);
				rcTmp.setIdPrograma(idPrograma);
				rcTmp.setRfcComprador(comprador.get(0).getRfc());
			}
			bandFinArchivo = false;
		   while(cellIterator.hasNext()){
			   valor =  recuperarDatoDeCelda(cellIterator.next());
 			   if (valor != null && !valor.equals("")){
				   bandFinArchivo = true;
			   }
			   if(contRow < (lstGruposCamposEncabezadoRelacionV.size())){
				   getValidacionEncabezadoArchivo(valor);
			   }		   
			   
			   if(contRow >= lstGruposCamposEncabezadoRelacionV.size() && contColumna < lstGruposCamposDetalleRelacionV.size()){
				   getValidacionDetalle(valor);			   
			   }//end valida el detalle contRow >=lstGruposCamposEncabezadoRelacionV.size() El detalle
			   
			   //Lectura de las dos columnas adicionales Bodega y Estado
			   if(contColumna == lstGruposCamposDetalleRelacionV.size()){	//Penultima columna que lee la bodega	
				   if(valor==null || valor.isEmpty()){
					   bd = new BitacoraRelcomprasDetalle();
					   bd.setMensaje(msj);
					   b.getBitacoraRelcomprasDetalle().add(bd);
					   crearCeldaenLogXls();
					   //errorInternoArchivo = true;
				   }else{
					   //if( !claveBodegaTmp.equals(valor)){
						   //verificar en bd si la clave de la bodega existe en SIPC
						   //Recupera valor de la clave de la bodega
						   List<Bodegas> lstBodegas = cDAO.consultaBodegas(valor);
						   if(lstBodegas.size() > 0){
							   claveBodega = lstBodegas.get(0).getClaveBodega();
							   claveBodegaCorrecta = true;
						   }else{
							   msj = "Fila: "+(contRow+1)+". No se encontro la bodega "+valor;
							   bd = new BitacoraRelcomprasDetalle();
							   bd.setMensaje(msj);
							   b.getBitacoraRelcomprasDetalle().add(bd);
							   crearCeldaenLogXls();
							   claveBodegaTmp = valor;
							   AppLogger.error("errores","Error interno bodega "+errorInternoArchivo+contRow);
							   errorInternoArchivo = true;
							   errorLecturaArchivo = true;
							   //break; 
							   								   
						   }
						//}
					   rcTmp.setClaveBodega(claveBodega);
					   claveBodegaTmp = valor;
					  
				   }
			   }else if(contColumna == lstGruposCamposDetalleRelacionV.size()+1){
				   if(valor==null || valor.isEmpty()){
					   bd = new BitacoraRelcomprasDetalle();
					   bd.setMensaje(msj);
					   b.getBitacoraRelcomprasDetalle().add(bd);
					   crearCeldaenLogXls();
				   }else{					  
					   //verificar en bd si el estado existe en SIPC
					   List<Estado> lstEdo = cDAO.consultaEstado(0, valor,0);
					   nombreEstado = valor;
					   if(lstEdo.size() > 0){
						   idEstado = lstEdo.get(0).getIdEstado();
						   estadoCorrecto = true;
					   }else{
						   msj = "Fila: "+contRow+". No se encontro el estado "+valor;
						   bd = new BitacoraRelcomprasDetalle();
						   bd.setMensaje(msj);
						   b.getBitacoraRelcomprasDetalle().add(bd);
						   errorInternoArchivo = true;
						   errorLecturaArchivo = true;
						   crearCeldaenLogXls();								   
					   }
					
//					   idEstado = 99;
//					   nombreEstado = valor;
//					   estadoCorrecto = true;
//					   rcTmp.setEstadoAcopio(99);
					   rcTmp.setEstadoAcopio(idEstado);
					   rcTmp.setNombreEstado(nombreEstado);
					   nomEstadoTmp = valor;
					  
				   }
			   }	
			   
			   contColumna++;
			   if(contColumna == lstGruposCamposDetalleRelacionV.size()+2){
				   break; //en caso de que haya mas columnas en el archivo que no pertenecen a la configuración de la relacion de compras
			   }
		   }//end while cellIterator
			
		   if(contRow >=lstGruposCamposEncabezadoRelacionV.size()){
			 //agrega elemento a la lista 
			 if(bandFinArchivo){
				 if(claveBodegaCorrecta && estadoCorrecto){
					 
					 lstRCTemp.add(rcTmp);
					 claveBodegaCorrecta = false;
					 estadoCorrecto = false;
				 }else{//Si se encontraron menos columnas
					 if(contColumna < lstGruposCamposDetalleRelacionV.size()+2){
						 msj = "Fila:"+(contRow+1)+". Se esperaban "+(lstGruposCamposDetalleRelacionV.size()+2)+", se encontraron solo " + contColumna;
						 errorInternoArchivo = true;
						 crearCeldaenLogXls();
					 }
				 }
			 }else{
				 break;
			 }
	         
		   }
		   contRow++;
		   AppLogger.info("app","termina fila");
		}//while(rowIterator.hasNext())
	}//end leer pagina

	private void getValidacionEncabezadoArchivo(String valor) {
		   //ENCABEZADO DEL ARCHIVO (VALIDACION)
		   if(contColumna == 1){
			   if(lstGruposCamposEncabezadoRelacionV.get(contRow).getIdCampo()== 1){//COMPRADOR
				   if(valor==null || valor.isEmpty()){
					   msj = lstGruposCamposEncabezadoRelacionV.get(contRow).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Se esperaba valor";
					   bd = new BitacoraRelcomprasDetalle();
					   bd.setMensaje(msj);
					   b.getBitacoraRelcomprasDetalle().add(bd);
					   crearCeldaenLogXls();
				   }
			   }else if(lstGruposCamposEncabezadoRelacionV.get(contRow).getIdCampo()== 2){//CARTA DE ADHESION
				   //Verificar que la carta de adhesion del archivo corresponda a la del sistema
				   if(valor==null || valor.isEmpty()){
					   msj = lstGruposCamposEncabezadoRelacionV.get(contRow).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Se esperaba valor";
					   bd = new BitacoraRelcomprasDetalle();
					   bd.setMensaje(msj);
					   b.getBitacoraRelcomprasDetalle().add(bd);
					   crearCeldaenLogXls();
					   errorCartaAdhesion = true;
					   errorLecturaArchivo = true;
				   }else{
					   if(!folioCartaAdhesion.equals(valor)){
						   msj =lstGruposCamposEncabezadoRelacionV.get(contRow).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". No coinciden el folio de la carta de adhesión del archivo con  el sistema";
						   bd = new BitacoraRelcomprasDetalle();
						   bd.setMensaje(msj);
						   b.getBitacoraRelcomprasDetalle().add(bd);
						   crearCeldaenLogXls();
						   errorCartaAdhesion = true;
						   errorLecturaArchivo = true;
					   }
				   }					   
			   }else if(lstGruposCamposEncabezadoRelacionV.get(contRow).getIdCampo()==5){//"PRODUCTO A APOYAR"
				   if(valor==null || valor.isEmpty()){
					   msj = lstGruposCamposEncabezadoRelacionV.get(contRow).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Se esperaba valor";
					   bd = new BitacoraRelcomprasDetalle();
					   bd.setMensaje(msj);
					   b.getBitacoraRelcomprasDetalle().add(bd);
					   crearCeldaenLogXls();
				   }
			   }
		   } 
		   
		
	}

	private void getValidacionDetalle(String valor) throws ParseException {
		   if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 6){//GRUPO 6
			   if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 6){//CAMPO 6 FOLIO DEL PREDIO (Incluyendo el secuencial)							  
				  if(valor!=null && !valor.isEmpty()){
					  if(idPrograma <= 42){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 6 el valor es "+valor);
							  if(valor.contains("C")){
								  if(valor.indexOf("-")!=-1){ 
									  rcTmp.setFolioPredioAlterno(valor.substring(0, (valor.lastIndexOf("-"))));
									}else{
										 rcTmp.setFolioPredioAlterno(valor);
									}							 
								}else{
									  if(valor.indexOf("-")!=-1){ 
										  try{
											  rcTmp.setFolioPredioSec(Integer.parseInt(valor.substring((valor.lastIndexOf("-")+1))));
										  	  rcTmp.setFolioPredio(valor.substring(0, (valor.lastIndexOf("-"))));
										  }catch(Exception e){
											  rcTmp.setFolioPredio("INVALIDO");	
											  rcTmp.setFolioPredioSec(-1);
											  rcTmp.setPredioIncorrecto(valor);
										  }				 
									  }else{
										  try{
											  double vDouble = Double.parseDouble(valor);
											  Long vLong = (long) vDouble;
											  rcTmp.setFolioPredio("INVALIDO");	
											  rcTmp.setFolioPredioSec(-1);
											  rcTmp.setPredioIncorrecto(vLong.toString());
										  }catch(Exception e){
											  rcTmp.setFolioPredio("INVALIDO");	
											  rcTmp.setFolioPredioSec(-1);  
											  rcTmp.setPredioIncorrecto(valor);
										  }	
									  }
								}				  
						  }else{
							  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
						  } 
					  }else{
						  Long l = Long.valueOf(valor);
						  System.out.println("l"+l);
						  System.out.println("l1"+l.toString());
						  rcTmp.setFolioPredio(valor);				  
					  }
				  } // end valor diferente de nulo 
			   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 7){ //folio del productor			
				  if(valor!=null && !valor.isEmpty()){
					  long vLong = 0;
					  Double valorD = 0.0;
					  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
						  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 7 el valor es "+valor);
						  valorD = Double.parseDouble(valor);								  
						  rcTmp.setFolioProductor(valorD.longValue());
					  }	else{
						  if(valor.indexOf("-")!=-1){ 
								try{			
									vLong =Long.parseLong( valor.substring(0,valor.lastIndexOf("-")));
							  		rcTmp.setFolioProductor(vLong);									  		
							  	}catch(Exception e){
							  		rcTmp.setFolioProductor(new Long (-1));									  		
							  		rcTmp.setProductorIncorrecto(valor);
							  		AppLogger.info("app", "El valor "+valor+" es invalido y no se puede cargar en la base de datos "+lstGruposCamposDetalleRelacionV.get(contColumna).getCampo() +" Fila :"+(contRow+1));
							  	}	 
						  }else{
							  rcTmp.setFolioProductor(new Long (-1));									  		
						  	  rcTmp.setProductorIncorrecto(valor);
						  	  AppLogger.info("app", "El valor "+valor+" es invalido y no se puede cargar en la base de datos "+lstGruposCamposDetalleRelacionV.get(contColumna).getCampo() +" Fila :"+(contRow+1));
						  }
					  }
						 			  							  
				  }else{
					  //msj = lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". El campo es requerido";
					  //crearCeldaenLogXls();
				  }
			   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 8){//CAMPO 8 "NOMBRE (S) O RAZÓN SOCIAL"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 8 el valor es "+valor);
							  rcTmp.setNombreProductor(valor);
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
						  }
					  }  
				}else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 9){//"APELLIDO PATERNO"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 9 el valor es "+valor);
							  rcTmp.setPaternoProductor(valor);
						  }else{
							  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());							  
						  }
					  }  
				}else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 10){//"APELLIDO MATERNO"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 10 el valor es "+valor);
							  rcTmp.setMaternoProductor(valor);
						  }else{
							  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  
						  }
					  }  
				}else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 69){//"CURP"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 69 el valor es "+valor);
							  rcTmp.setCurpProductor(valor);
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
						  }
					  }  
				}else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 17){//"RFC"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 6 campo 17 el valor es "+valor);
							  rcTmp.setRfcProductor(valor);
						  }else{
							  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());							 
						  }
					  }  
				}
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 7){//GRUPO 7
			   if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 11){//N° DE BOLETA O TICKET DE BÁSCULA
				   if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app", "fila "+contRow+" Columna"+contColumna+" grupo 7 campo 11 el valor es "+valor);
							  try{
									Double d = Double.parseDouble(valor);
									valor = TextUtil.formateaNumeroComoCantidadSincomas(d);
								}catch (Exception e){
									
								}								  
							  	rcTmp.setBoletaTicketBascula(valor.toString());
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  
						  }  
						  
				   }
			   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 12){//"FECHA DE ENTRADA (dd-mmm-aa)"
				   if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 7 campo 12 el valor es "+valor);
							  rcTmp.setFechaEntradaBoleta(Utilerias.convertirStringToDateMMDDYYY(valor));
						  }else{
							  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());									  
						  } 								  
						 
				   }
			   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 63){ //"P.N.A. DE LA BOLETA (TON.)"
				   if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 7 campo 63 el valor es "+valor);
							  rcTmp.setVolBolTicket(Double.parseDouble(valor));
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
						  } 								  
						  
				   }
			   }
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 8){//GRUPO 8 "FACTURA DE VENTA GLOBAL, UTILIZADA PARA LA DETERMINACIÓN DEL T.C. (DE SER DIFERENTE A LA DEL PRODUCTOR)"
			   	  if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 14){//"NOMBRE DE LA PERSONA FISICA O MORAL QUE FACTURA"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 8 campo 14 el valor es "+valor);
							  rcTmp.setPersonaFacturaGlobal(valor);
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
						  }
					  }  
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 15){ //"NUMERO"
					   if(valor!=null && !valor.isEmpty()){
					 		  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 8 campo 15 el valor es "+valor);
								  rcTmp.setNumeroFacGlobal(valor);
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 16){ //"FECHA"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 8 campo 16 el valor es "+valor);
								  rcTmp.setFechaFacGlobal(Utilerias.convertirStringToDateMMDDYYY(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  	  
					   }	
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 17){ //"RFC"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 8 campo 17 el valor es "+valor);
								  rcTmp.setRfcFacGlobal(valor);
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 64){ //"P.N.A. DE LA FACTURA GLOBAL (TON.)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 8 campo 64 el valor es "+valor);
								  rcTmp.setVolPnaFacGlobal(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  } 								  
							  
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 18){ //"IMPORTE TOTAL FACTURADO ($)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 8 campo 18 el valor es "+valor);
								  rcTmp.setImpFacGlobal(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							 
					   }
				   }
			   
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 9){//GRUPO 9  "FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO"
				   if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 15){//CAMPO 6 NUMERO							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 9 campo 15 el valor es "+valor);
							  if(valor.length() >= 32 && valor.length() <= 36){
								  rcTmp.setFolioFacturaVenta(valor);
								 }else{
									 AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" no tiene de 32 a 36 caracteres "+valor);
								 }			  
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  
						  }
					  }  
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 16){ //"FECHA (dd-mmm-aa)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 9 campo 16 el valor es "+valor);
								  rcTmp.setFechaEmisionFac(Utilerias.convertirStringToDateMMDDYYY(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor "+valor+ " no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							  
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 17){ //"RFC"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 9 campo 16 el valor es "+valor);
								  rcTmp.setRfcFacVenta(valor);
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  } 								  
							 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 19){ //"P.N.A. SOLICITADO PARA APOYO (TON.)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 9 campo 19 el valor es "+valor);
								  rcTmp.setVolSolFacVenta(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 65){ //"P.N.A. TOTAL DE LA FACTURA (TON.)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 9 campo 65 el valor es "+valor);								
								  rcTmp.setVolTotalFacVenta(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							  
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 20){ //"IMPORTE TOTAL FACTURADO ($)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 9 campo 20 el valor es "+valor);
								  rcTmp.setImpSolFacVenta(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 70){//"VARIEDAD "
					   if(valor!=null && !valor.isEmpty()){
							  Double valorD = 0.0;
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+(contRow+1)+" Columna"+contColumna+" grupo 9 campo 70 el valor es "+valor);
								  valorD = Double.parseDouble(valor);								  
								  rcTmp.setIdVariedad(valorD.intValue());
							  }else{
								  rcTmp.setIdVariedad(-1);
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  } 							  
					   }
				   }	   
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 10){//GRUPO 10 "CONTRATO DE COMPRA-VENTA A TÉRMINO"
			   if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 21){//"FOLIO"							  
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 10 campo 21 el valor es "+valor);
							  rcTmp.setFolioContrato(valor);
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  
						  }
					  }  
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 22){//"PRECIO PACTADO EN EL CONTRATO (DLS.)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 10 campo 22 el valor es "+valor);
								  rcTmp.setImpPactadoCompraVenta(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
								  
							  } 								  
							  
					   }
				   }		   
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 11){//GRUPO 11 "PAGO AL PRODUCTOR EN AXC"
			   
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 12){//GRUPO 12 "PAGO AL PRODUCTOR SIN AXC"
			   	if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 27){//CAMPO 27 "TIPO DE DOCUMENTO DE PAGO (Póliza de cheque, Cheque de pago, Recibos de liquidación al productor o Pago electrónico)"							  
					
					  if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 12 campo 27 el valor es "+valor);
							  rcTmp.setTipoDocPago(valor);	
						  }else{
							  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  
						  }
					  }  
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 21){ //"FOLIO"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 12 campo 21 el valor es "+valor);
								  try{
									  if(!valor.equals("1F") && !valor.equals("1D")){
										  Double d = Double.parseDouble(valor);
										  valor = TextUtil.formateaNumeroComoCantidadSincomas(d); 
									  }										
									}catch (Exception e){
										
									}	
								  rcTmp.setFolioDocPago(valor);
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());								  
							  } 								  
							  
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 16){ //"FECHA (dd-mmm-aa)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 12 campo 16 el valor es "+valor);
								  rcTmp.setFechaDocPagoSinaxc(Utilerias.convertirStringToDateMMDDYYY(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());								  
								  
							  } 						  
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 29){ //"BANCO"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 12 campo 29 el valor es "+valor);
								  rcTmp.setBancoSinaxc(valor);  
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());								  
							  } 								 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 66){ //"IMPORTE DE DOCUMENTO DE PAGO ($)""
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 12 campo 66 el valor es "+valor);
								  rcTmp.setImpDocPagoSinaxc(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());								  
							  } 								  
							 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 67){ //"IMPORTE TOTAL PAGADO ($)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 12 campo 67 el valor es "+valor);
								  rcTmp.setImpTotalPagoSinaxc(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  } 								  
							 
					   }
				   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 68){//"PRECIO PAGADO POR TONELADA ($)"
					   if(valor!=null && !valor.isEmpty()){
							  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
								  AppLogger.info("app","fila "+contRow+" Columna"+contColumna+" grupo 12 campo 68 el valor es "+valor);
								  rcTmp.setImpPrecioTonPagiSinaxc(Double.parseDouble(valor));
							  }else{
								  AppLogger.info("app", lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  } 								  
							 
					   }
				   }			   
		   }else if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdGrupo().intValue() == 13){//GRUPO 13 "NÚMERO DE PROD. BENEF."
			   if(lstGruposCamposDetalleRelacionV.get(contColumna).getIdCampo().intValue() == 30){//CAMPO 30 "NÚMERO DE PROD. BENEF."							  
					 if(valor!=null && !valor.isEmpty()){
						  if(verificarTipoDato(valor, lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato())){
							  Double valorD = Double.parseDouble(valor);
							  AppLogger.info("app","fila: "+contRow+" Columna"+contColumna+" grupo 13 campo 30 el valor es "+valor);
							  rcTmp.setNumeroProdBenef(valorD.intValue());
						  }else{
							  AppLogger.info("app",lstGruposCamposDetalleRelacionV.get(contColumna).getCampo()+" Fila :"+(contRow+1)+" Columna: "+(contColumna+1)+". Valor no permitido, se esperaba valor de tipo "+ lstGruposCamposDetalleRelacionV.get(contColumna).getTipoDato());
							  
						  }
					  }  
				   }
		   }        

		
	}

	public String eliminarArchivoComprasXls(){
		try{
			if(rDAO.consultaRelacionComprasTMP(folioCartaAdhesion).size()==0){
				addActionError("No hay nada que eliminar, primero realice la carga del archivo");
				return SUCCESS;
			}			
			lstBitacoraRelCompras = rDAO.consultaBitacoraDif99(folioCartaAdhesion, "0,1", true);			
			if(lstBitacoraRelCompras.size()>0){
				addActionError("Ya se generó reporte de inconsistencias, no es posible eliminar el archivo");
				return SUCCESS;
			}
			
//			band = rDAO.verificaCriterio99OKmaxFechaRegistro(folioCartaAdhesion);
//			band1 = rDAO.verificaErrorCriterioDif99(folioCartaAdhesion);
//			
//			if(band){
//				if(!band1){
//					addActionError("No existen las condiciones adecuadas para eliminar un archivo");
//					return SUCCESS;
//				}
//			}			
			//elimina registros de la tabla relacion_compras_tmp en la base de datos
			rDAO.borrarRelacionComprasByFolioCarta(folioCartaAdhesion);
			//elimina el archivo fisico de la relacion de compras			
			lstBitacoraRelCompras =	rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, "99", null);
			if(lstBitacoraRelCompras.size()>0){
				File nombreArchivoExcel99File = new File(lstBitacoraRelCompras.get(0).getRutaArchivo(),lstBitacoraRelCompras.get(0).getNombreArchivo());
				if(nombreArchivoExcel99File.exists()){
					if(!nombreArchivoExcel99File.delete()){
						AppLogger.error("errores","Error al eliminar el archivo  de compras 99 de la carta "+folioCartaAdhesion);
					}else{
						AppLogger.info("app","Se borro "+lstBitacoraRelCompras.get(0).getNombreArchivo()+"archivo log de compras para la carta  "+folioCartaAdhesion);
					}
				}	
			}	
			lstBitacoraRelCompras =	rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, null, null);
			for(BitacoraRelcompras l: lstBitacoraRelCompras){
				cDAO.borrarObjeto(l);
			}
			cuadroSatisfactorio = "Se eliminó satisfactoriamente el ultimo archivo cargado";
			
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en cargarArchivoRelCompras debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }finally{
	    	capturaCargaArchivoRelCompras();
			tipoAccion = 1;
	    }
		return SUCCESS;
	}
	
	private void crearCeldaenLogXls() {		
		row = sheetLog.createRow(++countRow);
		cellLog = row.createCell(0);
		cellLog.setCellValue(msj);
	}
	
	private void guardarBitacoraHCOS() {
		for(BitacoraRelcompras b: lstBitacoraRelCompras){
			BitacoraRelcomprasHCO  bhco = new BitacoraRelcomprasHCO();
			bhco.setFolioCartaAdhesion(folioCartaAdhesion);
			bhco.setIdCriterio(b.getIdCriterio());
			bhco.setStatus(b.getStatus());
			bhco.setNombreArchivo(b.getNombreArchivo());
			bhco.setRutaArchivo(b.getRutaArchivo());
			bhco.setFechaRegistroHCO(new Date());
			bhco.setFechaRegistro(b.getFechaRegistro());
			bhco.setUsuarioCreacion((Integer) session.get("idUsuario"));
			bhco.setBitacoraRelcomprasDetalleHCO(new HashSet<BitacoraRelcomprasDetalleHCO>());
			//Recuperar la bitacora detalle
			List<BitacoraRelcomprasDetalle> lstBitacoraDetalle = rDAO.consultaBitacoraRelcomprasDetalle(b.getIdBitacoraRelcompras());
			//Iterator<BitacoraRelcomprasDetalle> itr=b.getBitacoraRelcomprasDetalle().iterator();  
			//while(itr.hasNext()){
			for(BitacoraRelcomprasDetalle bd:lstBitacoraDetalle){
				//BitacoraRelcomprasDetalle bd  = itr.next();
				BitacoraRelcomprasDetalleHCO bdhco = new BitacoraRelcomprasDetalleHCO();
				bdhco.setMensaje(bd.getMensaje());
				bhco.getBitacoraRelcomprasDetalleHCO().add(bdhco);
			}
			cDAO.guardaObjeto(bhco);
		}		
		
	}

	private void guardarRelacioncompras(int numeroHoja){
		    int numRegNoGuardados = 0;
			for(RelacionComprasTMP l: lstRCTemp){
				cDAO.guardaObjeto(l);
			}
			if(lstRCTemp.size()>0){
				siSeguardoRelacion = true;
			}
			numRegNoGuardados = (contRow-lstGruposCamposEncabezadoRelacionV.size())-lstRCTemp.size();
			System.out.println("numRegNoGuardados "+numRegNoGuardados);
			msj = "Se guardaron "+lstRCTemp.size()+" registros en la base de datos de la hoja "+numeroHoja;
			lstLog.add(msj);
			if(numRegNoGuardados > 0){
				msj = "No fue posible guardar "+(numRegNoGuardados == 1?"registro":"registros")+" en la base de datos de la hoja"+numeroHoja+", por favor verifique";
				lstLog.add(msj);
			}	
			if((contRow-lstGruposCamposEncabezadoRelacionV.size()) >= 1){
				msj = "Total registros del detalle "+(contRow-lstGruposCamposEncabezadoRelacionV.size())+" de la hoja "+numeroHoja;
				lstLog.add(msj);
			}					
			crearCeldaenLogXls();		
			
					
	}
			
	public String verificaSiRecuperaPeriodo (){
		//verifica que el criterio seleccionado pida periodo		
		List<CatCriteriosValidacion> lstCatCriterio = cDAO.consultaCatCriteriosValidacion(idCriterio);
		if(lstCatCriterio.size() > 0){
			if(lstCatCriterio.get(0).getPidePeriodo().equals("S")){
				if(idCriterio == 9){
					cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
					//Recupera el programa para verificar si hay periodo inicializado en el programa
					programa = cDAO.consultaPrograma(cartaAdhesion.getIdPrograma()).get(0);
					pidePeriodo = true;
					if(programa.getInicioPeriodoAcopio()!=null && programa.getFinPeriodoAcopio()!=null){
						periodoAcopioPrograma = false;	
						fechaInicio = programa.getInicioPeriodoAcopio();
						fechaFin = programa.getFinPeriodoAcopio();
					}
					
				}else{
					pidePeriodo = true;
				}
			}
		}
		
		return SUCCESS;
	}
	
	public String verificarQueCriterioPeriodoAplicar (){			
		if(grupoCriterio.equals("Boletas")){
			cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			programa = cDAO.consultaPrograma(cartaAdhesion.getIdPrograma()).get(0);
			if(programa.getInicioPeriodoAcopio()!=null && programa.getFinPeriodoAcopio()!=null){
				fechaInicioAuditor = programa.getInicioPeriodoAcopio();
				fechaFinAuditor = programa.getFinPeriodoAcopio();
				System.out.println("si aplica lineamiento");
				aplicaPeriodoLineamiento = 1;
				return SUCCESS;
			}
		}else if(grupoCriterio.equals("Facturas") || grupoCriterio.equals("Pagos")){
			
			
			
		}
				
		//Verifica que se haya capturado en la documentacion el periodo inicial y final del auditor
		
		
		
				
		return SUCCESS;
	}
	
	public String verificaSiRecuperaPeriodoPorGrupo (){
		
	
		//Consigue el id_per_rel a traves de la carta adhesion
		cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
		lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 1, null, null, null, null, cartaAdhesion.getIdPrograma());
		if(lstPersonalizacionRelacionesProgramaV.size()>0){
			idPerRel = lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();
		}
		//Verifica si se pide periodo en el grupo seleccionado
		lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio, "S");
		if(lstValidacionPorGrupo.size()>0){
			pidePeriodo = true;
		}
		
		if(aplicaPeriodoLineamiento == 1){
			programa = cDAO.consultaPrograma(cartaAdhesion.getIdPrograma()).get(0);
			if(programa.getInicioPeriodoAcopio()!=null && programa.getFinPeriodoAcopio()!=null){
				fechaInicio = programa.getInicioPeriodoAcopio();
				fechaFin = programa.getFinPeriodoAcopio();
			}else{
				
				noSeTieneConfPerEnPrg = true;
			}
		}		
		
		return SUCCESS;
	}

	public String recuperaCriterioAplicarPeriodo(){
		if(pideTipoPeriodo == 3){
			//Recupera el periodo del dictamen del auditor a través de la carta de adhesion		
			List<DocumentacionSPCartaAdhesion> lstDocumentacionSP = spDAO.consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 7);
			if(lstDocumentacionSP.size()>0){
				if(lstDocumentacionSP.get(0).getPeriodoInicialAuditor() !=null && lstDocumentacionSP.get(0).getPeriodoFinalAuditor() != null){
					fechaInicioAuditor = lstDocumentacionSP.get(0).getPeriodoInicialAuditor();
					fechaFinAuditor = lstDocumentacionSP.get(0).getPeriodoFinalAuditor();
				}
			}
		}
		
		return SUCCESS;
	}
		
	public String recuperaContrato (){
		
		//Recupera los contratos de la carta adhesion
		//lstContratos = rDAO.recuperaContratoByCartaAdhesion(folioCartaAdhesion);
		
		return SUCCESS;
	}
		
	public String respuestaPeriodoContratoAdendum (){
			
		//Recupera los periodos de los contratos de la relacion de compras  de la carta adhesion
		lstContratos = rDAO.consultaPeriodosContratosRelCompras(folioCartaAdhesion);
		
		return SUCCESS;
	}
	
	public String respuestaPeriodoContratoComplemento (){		
		//Muestra el periodo inicial y final del complemento del contrato		
		return SUCCESS;
	}
		
	public String recuperaCamposPorGrupo(int idGrupo){
		//Recupera el programa a traves de la carta de adhesion
		cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
		idPrograma = cartaAdhesion.getIdPrograma();		
		//Recupera la configuracion de la personalizacion
		lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 0, null, null, null, null, idPrograma);
		if(lstPersonalizacionRelacionesProgramaV.size()>0){
			idPerRel = lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();
			//Recupera los campos de la factura a validar, segun la configuracion de la relacion de compras de acuerdo al programa
			lstGruposCamposDetalleRelacionV = rDAO.consultaGruposCampostV(idPerRel, idGrupo);
		}				
		return SUCCESS;
	}
	
	public String prevalidarRelacionCompras(){
		String fechaInicioS = "", fechaInicioComS = "" ;
		String fechaFinS = "", fechaFinComS = "";

		try{
			rutaCartaAdhesion = getRecuperaRutaCarta();	
			//inicializarArchivoLog();
			//verifica que exista información en la tabla de relacion_compras_tmp
			if(rDAO.consultaRelacionComprasTMP(folioCartaAdhesion).size()==0){
				addActionError("No hay nada que validar, primero realice la carga del archivo");
				return SUCCESS;
			}		
			//Recupera los datos de la carta de adhesion
			cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			idPrograma = cartaAdhesion.getIdPrograma(); 
			//Si existe antes un error en la validacion borrar registro de la bitacora
			List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 1, null, null, null, null, idPrograma);
			idPerRel =  lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();		
			
			boolean siAplicaFolioContrato = false;
			//Verifica que en la personalización se haya configurado el campo "FOLIO CONTRATO"
			List<GruposCamposRelacionV> lstGruposCampos = rDAO.consultaGruposCampostV(idPerRel,10,21);
			if(lstGruposCampos.size()>0){
				siAplicaFolioContrato = true;
			}			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			Cell cell  = null;
			int countRow = 0;
			Row row = null; 
			if(grupoCriterio.equals("Predio")){
				//Consigue los criterios correspondiente al grupo "Predio"
				lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);				
				String s = integraCriteriosByGrupo(lstValidacionPorGrupo);
				
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s, "0,1", true);
				if(lstBitacoraRelCompras.size()>0){
					//Actualiza campo predio incosistente en la tabla relacion_compras_tmp
					rDAO.actualizaCamposIconsistentes(folioCartaAdhesion, false, false, true, false);
					addActionError("Los criterio para el grupo '"+grupoCriterio+"' ya se encuentra validado y se generó bitacora definitiva, por favor verifique ");
					return SUCCESS;
				}						
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1");
				if(lstBitacoraRelCompras.size() > 0){					
					//Borra el registro de la bitacora
					for(BitacoraRelcompras b: lstBitacoraRelCompras){
						cDAO.borrarObjeto(b);
					}					
				}		
				for(CatCriteriosValidacion l:lstValidacionPorGrupo){
					if(l.getIdCriterio()== 1){//PREDIO EXISTA EN BASE DE DATOS
						sheet = wb.createSheet("PRED EXISTA EN BD");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstPrediosNoExistenBD = rDAO.verificaSiPrediosExistenBD(folioCartaAdhesion, cartaAdhesion.getIdPrograma());
						if(lstPrediosNoExistenBD.size()>0){//En el listado se guardan los predios que no existen en la base de datos
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Carta Adhesión: "+folioCartaAdhesion);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);    
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Nombre Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Predio");
													
							for(RelacionComprasTMP p: lstPrediosNoExistenBD){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getClaveBodega()+";"+p.getNombreEstado()+";"+p.getPaternoProductor()+";"
										+p.getMaternoProductor()+";"+p.getNombreProductor()+";"
										+p.getFolioPredio()+";"+p.getFolioPredioSec()+";"+(p.getFolioPredioAlterno()!=null&&!p.getFolioPredioAlterno().isEmpty()?p.getFolioPredioAlterno():"null;"));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaPredioIncosistente(p.getIdRelacionComprasTmp(), folioCartaAdhesion);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(p.getClaveBodega()!=null && !p.getClaveBodega().isEmpty()?p.getClaveBodega():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNombreEstado()!=null && !p.getNombreEstado().isEmpty()?p.getNombreEstado():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue((p.getPaternoProductor()!=null && !p.getPaternoProductor().isEmpty() ? p.getPaternoProductor()+" " :"" )
										+(p.getMaternoProductor()!=null ? p.getMaternoProductor()+" " :"") 
										+(p.getNombreProductor()!=null ? p.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getFolioPredio()!=null && !p.getFolioPredio().isEmpty()?p.getFolioPredio():"");

							}							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"LOS PREDIOS SI EXISTEN EN LA BASE DE DATOS DE ASERCA\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}						
					}else if(l.getIdCriterio()== 2){//PREDIO SE ENCUENTRE VALIDADO POR LA DR/DE
						sheet = wb.createSheet("PREDIO VAL");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						lstPrediosNoValidadosDRDE = rDAO.verificaPrediosNoValidadosDRDE(folioCartaAdhesion, idPrograma);
						if(lstPrediosNoValidadosDRDE.size()>0){ //En el listado se guardan los predios que no estan validados por la DR/DE
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(0);
							cell.setCellValue("Predio");
							cell = row.createCell(1);
							cell.setCellValue("Secuencial");
							cell = row.createCell(2);
							cell.setCellValue("Predio Alterno");
							for(PrediosNoValidadosDRDE p: lstPrediosNoValidadosDRDE){
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getFolioPredio()+";"+p.getFolioPredioSec()+";"+p.getFolioPredioAlterno());
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue(p.getFolioPredio()!=null && !p.getFolioPredio().isEmpty()?p.getFolioPredio():"");
								cell = row.createCell(1);
								cell.setCellValue(p.getFolioPredioSec()!=null?p.getFolioPredio():"");
								cell = row.createCell(2);
								cell.setCellValue(p.getFolioPredioAlterno()!=null && !p.getFolioPredioAlterno().isEmpty()?p.getFolioPredioAlterno():"");
							}
							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"LOS PREDIOS SE ENCUENTRAN VALIDADOS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}else if(l.getIdCriterio()== 3){//PREDIOS ESTEN PAGADOS
						sheet = wb.createSheet("PREDIOS PAG");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						lstPrediosNoPagados = rDAO.verificaPrediosNoPagados(folioCartaAdhesion, idPrograma);
						if(lstPrediosNoPagados.size()>0){ //En el listado se guardan los predios que no estan pagados
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(0);
							cell.setCellValue("Predio");
							cell = row.createCell(1);
							cell.setCellValue("Secuencial");
							cell = row.createCell(2);
							cell.setCellValue("Predio Alterno");
							for(PrediosNoPagados p: lstPrediosNoPagados){
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getFolioPredio()+";"+p.getFolioPredioSec()+";"+p.getFolioPredioAlterno());
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue(p.getFolioPredio()!=null && !p.getFolioPredio().isEmpty()?p.getFolioPredio():"");
								cell = row.createCell(1);
								cell.setCellValue(p.getFolioPredioSec()!=null?p.getFolioPredio():"");
								cell = row.createCell(2);
								cell.setCellValue(p.getFolioPredioAlterno()!=null && !p.getFolioPredioAlterno().isEmpty()?p.getFolioPredioAlterno():"");
							}				
							b.setMensaje(msj);							
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"LOS PREDIOS SE ENCUENTRAN PAGADOS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					} else if(l.getIdCriterio()== 26){//PREDIO/PRODUCTOR/CONTRATO EXISTA EN BASE DE DATOS
						sheet = wb.createSheet("PRED-PROD-CON EXISTA BD");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstPrediosProdsContNoExistenBD = rDAO.verificaSiPrediosProdsContExistenBD(folioCartaAdhesion, cartaAdhesion.getIdPrograma());
						if(lstPrediosProdsContNoExistenBD.size()>0){//En el listado se guardan los predios que no existen en la base de datos
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());							
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Folio Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Nombre Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Predio");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Secuencial");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Predio Alterno");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Carta Adhesión");
							for(PrediosProdsContNoExisteBD p: lstPrediosProdsContNoExistenBD){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getClaveBodega()+";"+p.getNombreEstado()+";"+(siAplicaFolioContrato?p.getFolioContrato()+";":"")+p.getProductor()+";"+p.getPaternoProductor()+";"
										+p.getMaternoProductor()+";"+p.getNombreProductor()+";"
										+p.getFolioPredio()+";"+p.getFolioPredioSec()+";"+p.getFolioPredioAlterno()+";"
										+(p.getFolioCartaExterna()!=null&&!p.getFolioCartaExterna().isEmpty()?p.getFolioCartaExterna():"null;"));								
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaPredioIncosistente(p.getId(), folioCartaAdhesion);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(p.getClaveBodega()!=null ? p.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNombreEstado()!=null ? p.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(p.getFolioContrato()!=null ? p.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getProductor()!=null ? p.getProductor()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue((p.getPaternoProductor()!=null && !p.getPaternoProductor().isEmpty() ? p.getPaternoProductor()+" " :"" )
										+(p.getMaternoProductor()!=null ? p.getMaternoProductor()+" " :"") 
										+(p.getNombreProductor()!=null ? p.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getFolioPredio()!=null && !p.getFolioPredio().isEmpty()?p.getFolioPredio():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getFolioPredioSec()!=null?p.getFolioPredioSec()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getFolioPredioAlterno()!=null && !p.getFolioPredioAlterno().isEmpty()?p.getFolioPredioAlterno():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getFolioCartaExterna()!=null && !p.getFolioCartaExterna().isEmpty()?p.getFolioCartaExterna():"");
							}
							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"LOS PREDIOS/PRODUCTORES/CONTRATOS SI EXISTEN EN LA BASE DE DATOS DE ASERCA\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}						
					}					
					
				}//end recorrido lstValidacionPorGrupo
				nombreArchivoLogXls = "Predios.xls";
				FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogXls));
			    wb.write(out);
			    out.close();	
			}else if(grupoCriterio.equals("Productor")){
				lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);
				String s = integraCriteriosByGrupo(lstValidacionPorGrupo);
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s, "0,1", true);
				if(lstBitacoraRelCompras.size()>0){
					addActionError("Los criterio para el grupo '"+grupoCriterio+"' ya se encuentra validado y se genero bitacora definitiva, por favor verifique ");
					return SUCCESS;
				}						
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1");
				if(lstBitacoraRelCompras.size() > 0){					
					//Borra el registro de la bitacora
					for(BitacoraRelcompras b: lstBitacoraRelCompras){
						cDAO.borrarObjeto(b);
					}
					
				}			
				for(CatCriteriosValidacion l:lstValidacionPorGrupo){
					if(l.getIdCriterio()== 4){//PRODUCTOR EXISTA EN BASE DE DATOS"
						sheet = wb.createSheet("PROD EXISTA EN BD");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						lstProductorExisteBD = rDAO.verificaSiexisteProductor(folioCartaAdhesion, idPrograma); // AHS 29012015
						if(lstProductorExisteBD.size()>0){ //En el listado se guardan los productores que no existen en la base de datos
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(0);
							cell.setCellValue("Folio Productor");
							cell = row.createCell(1);
							cell.setCellValue("Productor");
							cell = row.createCell(2);
							cell.setCellValue("CURP");
							cell = row.createCell(3);
							cell.setCellValue("RFC");
							for(ProductorExisteBD p: lstProductorExisteBD){
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getFolioProductor()+";"+p.getPaternoProductor()+";"
										+p.getMaternoProductor()+";"+p.getNombreProductor()+";"+p.getCurpProductor()+";"+p.getRfcProductor());
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue(p.getFolioProductor()!=null ? p.getFolioProductor()+"":"");
								cell = row.createCell(1);
								cell.setCellValue(p.getPaternoProductor() != null ? p.getPaternoProductor()+" ": ""
										+ p.getMaternoProductor() != null ? p.getMaternoProductor()+" ":""
										+ p.getNombreProductor() != null ? p.getNombreProductor():"");
								cell = row.createCell(2);
								cell.setCellValue(p.getCurpProductor()!=null && !p.getCurpProductor().isEmpty()?p.getCurpProductor():"");
								cell = row.createCell(3);
								cell.setCellValue(p.getRfcProductor()!=null && !p.getRfcProductor().isEmpty()?p.getRfcProductor():"");
							}					
							
							b.setMensaje(msj);
							//crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							cDAO.guardaObjeto(b);
						}			
					}else if(l.getIdCriterio()== 5){//PRODUCTORES ESTÉN ASOCIADOS A PREDIOS VALIDADOS"
						sheet = wb.createSheet("PROD PRE VAL");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						lstProductorPredioValidado = rDAO.verificaProductorAsociadoPredioValidado(folioCartaAdhesion, idPrograma);
						if(lstProductorPredioValidado.size()>0){ //En el listado se guardan los productores que no existen en la base de datos
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(0);
							cell.setCellValue("Folio Productor");
							cell = row.createCell(1);
							cell.setCellValue("Productor");
							cell = row.createCell(2);
							cell.setCellValue("CURP");
							cell = row.createCell(3);
							cell.setCellValue("RFC");
							for(ProductorPredioValidado p: lstProductorPredioValidado){
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getFolioProductor()+";"+p.getPaternoProductor()+";"
										+p.getMaternoProductor()+";"+p.getNombreProductor()+";"+p.getCurpProductor()+";"+p.getRfcProductor());
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue(p.getFolioProductor()!=null ? p.getFolioProductor()+"":"");
								cell = row.createCell(1);
								cell.setCellValue(p.getPaternoProductor() != null ? p.getPaternoProductor()+" ": ""
										+ p.getMaternoProductor() != null ? p.getMaternoProductor()+" ":""
										+ p.getNombreProductor() != null ? p.getNombreProductor():"");
								cell = row.createCell(2);
								cell.setCellValue(p.getCurpProductor()!=null && !p.getCurpProductor().isEmpty()?p.getCurpProductor():"");
								cell = row.createCell(3);
								cell.setCellValue(p.getRfcProductor()!=null && !p.getRfcProductor().isEmpty()?p.getRfcProductor():"");
							}					
							
							b.setMensaje(msj);
							//crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"LOS PRODUCTORES SE ENCUENTRAN ASOCIADOS A UN PREDIO VALIDADO\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}							
					}else if(l.getIdCriterio()== 6){//PRODUCTORES ESTÉN ASOCIADOS A PREDIOS PAGADOS
						sheet = wb.createSheet("PROD PRE PAG");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						lstProductorPredioPagado = rDAO.verificaProductorAsociadoPredioPagado(folioCartaAdhesion, idPrograma);
						if(lstProductorPredioPagado.size()>0){ //En el listado se guardan los productores que no existen en la base de datos
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(0);
							cell.setCellValue("Folio Productor");
							cell = row.createCell(1);
							cell.setCellValue("Productor");
							cell = row.createCell(2);
							cell.setCellValue("CURP");
							cell = row.createCell(3);
							cell.setCellValue("RFC");
							for(ProductorPredioPagado p: lstProductorPredioPagado){
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getFolioProductor()+";"+p.getPaternoProductor()+";"
										+p.getMaternoProductor()+";"+p.getNombreProductor()+";"+p.getCurpProductor()+";"+p.getRfcProductor());
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue(p.getFolioProductor()!=null ? p.getFolioProductor()+"":"");
								cell = row.createCell(1);
								cell.setCellValue(p.getPaternoProductor() != null ? p.getPaternoProductor()+" ": ""
										+ p.getMaternoProductor() != null ? p.getMaternoProductor()+" ":""
										+ p.getNombreProductor() != null ? p.getNombreProductor():"");
								cell = row.createCell(2);
								cell.setCellValue(p.getCurpProductor()!=null && !p.getCurpProductor().isEmpty()?p.getCurpProductor():"");
								cell = row.createCell(3);
								cell.setCellValue(p.getRfcProductor()!=null && !p.getRfcProductor().isEmpty()?p.getRfcProductor():"");
							}					
							
							b.setMensaje(msj);
							//crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"TODOS LOS PRODUCTORES SE ENCUENTRAN ASOCIADOS A UN PREDIO PAGADO\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}else if(l.getIdCriterio()== 14){//VALIDAR QUE SEA MISMO FOLIO, RFC, VOLUMEN, IMPORTE AL REGISTRADO EN BDD
						//De acuerdo a los campos seleccionados, se validaran vs la base de aserca
						/*int folioFacturaVenta = 0, fechaEmisionFac = 0, rfcFacVenta = 0, volSolFacVenta = 0, volTotalFacVenta = 0, impSolFacVenta = 0, variedad = 0, cultivo = 0;
						for(int i=0; i< camposFactura.length; i++){
							if(camposFactura[i]==15){//folioFacturaVenta
								folioFacturaVenta = 1;
							}else if(camposFactura[i]==16){//Fecha factura
								fechaEmisionFac = 1;
							}else if(camposFactura[i]==17){//Rfc factura
								rfcFacVenta = 1;
							}else if(camposFactura[i]==19){//P.N.A. SOLICITADO PARA APOYO (TON.)
								volSolFacVenta = 1;
							}else if(camposFactura[i]==65){//P.N.A. TOTAL DE LA FACTURA (TON.)
								volTotalFacVenta = 1;
							}else if(camposFactura[i]==20){//IMPORTE TOTAL FACTURADO ($)
								impSolFacVenta = 1;
							}else if(camposFactura[i]==70){//VARIEDAD
								impSolFacVenta = 1;
							}else if(camposFactura[i]==71){//CULTIVO
								impSolFacVenta = 1;
							}
						}
						//Recupera campos de la configuracion de la relacion para las facturas
						recuperaCamposFactura(9);
						lstFacturasIgualesFacAserca = rDAO.consultaFacIgualesFacAserca(folioCartaAdhesion, folioFacturaVenta, fechaEmisionFac,  rfcFacVenta,  volTotalFacVenta,  volSolFacVenta,  impSolFacVenta,  variedad,  cultivo, lstGruposCamposDetalleRelacionV);
						if(lstFacturasIgualesFacAserca.size()>0){//En el listado se guardan los predios que no existen en la base de datos
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = "LOS DATOS DE LAS FACTURAS NO CORRESPONDEN A LAS QUE SE ENCUENTRAN REGISTRADAS EN LA BASE DE DATOS DE ASERCA";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(0);
							cell.setCellValue("Folio Productor");
							cell = row.createCell(1);
							cell.setCellValue("Productor");
							cell = row.createCell(2);
							cell.setCellValue("RFC Productor");
							cell = row.createCell(3);
							cell.setCellValue("RFC Comprador ");
							for(FacturasIgualesFacAserca p: lstFacturasIgualesFacAserca){
								StringBuilder bdMsj = new StringBuilder();	
								bd = new BitacoraRelcomprasDetalle();
								bdMsj.append(p.getFolioProductor()).append(";")
								.append(p.getPaternoProductor()).append(";")
								.append(p.getMaternoProductor()).append(";")
								.append(p.getNombreProductor()).append(";")
								.append(p.getRfcProductor()).append(";")
								.append(p.getRfcComprador()).append(";");
								for(GruposCamposRelacionV g: lstGruposCamposDetalleRelacionV){
									if(g.getIdCampo()==15){//folioFacturaVenta
										bdMsj.append(p.getFolioFacturaVenta()).append(";");
									}else if(g.getIdCampo()==16){//Fecha factura
										bdMsj.append(p.getFechaEmisionFac()).append(";");
									}else if(g.getIdCampo()==17){//Rfc factura
										bdMsj.append(p.getRfcFacVenta()).append(";");
									}else if(g.getIdCampo()==19){//P.N.A. SOLICITADO PARA APOYO (TON.)
										bdMsj.append(p.getVolSolFacVenta()).append(";");
									}else if(g.getIdCampo()==65){//P.N.A. TOTAL DE LA FACTURA (TON.)
										bdMsj.append(p.getVolTotalFacVenta()).append(";");
									}else if(g.getIdCampo()==20){//IMPORTE TOTAL FACTURADO ($)
										bdMsj.append(p.getImpSolFacVenta()).append(";");
									}else if(g.getIdCampo()==70){//VARIEDAD
										bdMsj.append(p.getVariedad()).append(";");
									}else if(g.getIdCampo()==71){//CULTIVO
										bdMsj.append(p.getCultivo()).append(";");
									}
								}	
								
								bdMsj.deleteCharAt(bdMsj.length()-1);
								bd.setMensaje(bdMsj.toString());
								b.getBitacoraRelcomprasDetalle().add(bd);
							}
							
							b.setMensaje(msj);
							crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta";
							crearCeldaenLogXls();
							llenarBitacora(false, l.getIdCriterio());
							cDAO.guardaObjeto(b);
						}
			*/
					}//end criterio 14
				}//end recorrido lstValidacionPorGrupo PRODUCTOR
				nombreArchivoLogXls = "Productor.xls";
				nombreArchivoLogCargaXls = "LogCargaArchivo.xls"; //Log del archivo excel  // AHS 29012015
				FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogXls));
			    wb.write(out);
			    out.close();	
				//End grupo productor
			}else if(grupoCriterio.equals("General")){
				//Recupera todos los criterios relacionados al grupo configurados en la personalizacion
				lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);
				String s = integraCriteriosByGrupo(lstValidacionPorGrupo);
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1", true);
				if(lstBitacoraRelCompras.size()>0){
					addActionError("Los criterio para el grupo '"+grupoCriterio+"' ya se encuentra validado y se genero bitacora definitiva, por favor verifique ");
					return SUCCESS;
				}						
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1");
				if(lstBitacoraRelCompras.size() > 0){
					//Actualiza campo volumen_no_procedente en la tabla relacion_compras_tmp
					rDAO.actualizaCamposIconsistentes(folioCartaAdhesion, false, false, false, true);
					//Actualiza campo rfc_inconsistente en la tabla relacion_compras_tmp
					rDAO.actualizaCamposIconsistentes(folioCartaAdhesion, false, false, false, false, true);
					//Borra el registro de la bitacora
					for(BitacoraRelcompras b: lstBitacoraRelCompras){
						cDAO.borrarObjeto(b);
					}					
				}
				for(CatCriteriosValidacion l:lstValidacionPorGrupo){
					if(l.getIdCriterio()== 7){//EXISTA POR UNIDAD DE PRODUCCIÓN AL MENOS UN REGISTRO DE BOLETA, FACTURA, PREDIO Y PAGO
						sheet = wb.createSheet("PROD INCOSISTENTES");
						sheet = setMargenSheet(sheet);
						countRow = 0;
					   	countColumn = 0;
						lstProductoresIncosistentes = rDAO.consultaProductoresIncosistentes(folioCartaAdhesion);
						if(lstProductoresIncosistentes.size() > 0){
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Predio");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Predio Alterno");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Boletas");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Facturas");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Pagos");							
							for(ProductoresIncosistentes p: lstProductoresIncosistentes){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getClaveBodega()+";"+p.getNombreEstado()+";"+(siAplicaFolioContrato?p.getFolioContrato()+";":"")+p.getPaternoProductor()+";"
										+p.getMaternoProductor()+";"+p.getNombreProductor()+";"
										+p.getFolioPredio()+";"+p.getPredioAlterno()+";"+p.getNumeroBoletas()+";"
										+p.getNumeroFacturas()+";"+p.getNumeroPagos());								
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza predio como inconsistente
								
								
								
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(p.getClaveBodega()!=null ? p.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNombreEstado()!=null ? p.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(p.getFolioContrato()!=null ? p.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((p.getPaternoProductor()!=null && !p.getPaternoProductor().isEmpty() ? p.getPaternoProductor()+" " :"" )
										+(p.getMaternoProductor()!=null ? p.getMaternoProductor()+" " :"") 
										+(p.getNombreProductor()!=null ? p.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getFolioPredio()!=null ?p.getFolioPredio()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getPredioAlterno()!=null?p.getPredioAlterno()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNumeroBoletas()!=null?p.getNumeroBoletas()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNumeroFacturas()!=null?p.getNumeroFacturas()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNumeroPagos()!=null?p.getNumeroPagos()+"":"");
							}
						
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"LOS PRODUCTORES NO PRESENTAN INCOSISTENCIAS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}	
					}else if(l.getIdCriterio() == 12){// "QUE EL RFC CORRESPONDA AL PRODUCTOR"t
						sheet = wb.createSheet("RFC CORRESPONDE RFC PROD");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn =0;
						lstRfcProductorVsRfcFactura = rDAO.verificaRFCProductorVsRFCFactura(folioCartaAdhesion, idPrograma);  
						if(lstRfcProductorVsRfcFactura.size()>0){//En el listado se guardan las facturas duplicadas por productor
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}	
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("RFC Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("RFC Factura");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto ana. (ton) Fac ");
							for(RfcProductorVsRfcFactura r: lstRfcProductorVsRfcFactura){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(r.getClaveBodega()+";"+r.getNombreEstado()+";"+(siAplicaFolioContrato?r.getFolioContrato()+";":"")+r.getPaternoProductor()+";"+r.getMaternoProductor()+";"
										+r.getNombreProductor()+";"
									+r.getRfcProductor()+";"+r.getRfcFacVenta()+";"+(r.getVolTotalFacVenta()!=null?r.getVolTotalFacVenta():0));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaFacMayBolOPagMenFac(folioCartaAdhesion, r.getClaveBodega(), r.getNombreEstado(), r.getFolioContrato(),
										r.getPaternoProductor(), r.getMaternoProductor(), r.getNombreProductor(), false, false, true, false);	
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(r.getClaveBodega()!=null ? r.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getNombreEstado()!=null ? r.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(r.getFolioContrato()!=null ? r.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((r.getPaternoProductor()!=null && !r.getPaternoProductor().isEmpty() ? r.getPaternoProductor()+" " :"" )
										+(r.getMaternoProductor()!=null ?r.getMaternoProductor()+" " :"") 
										+(r.getNombreProductor()!=null ?r.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getRfcProductor()!=null && !r.getRfcProductor().isEmpty()?r.getRfcProductor():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getRfcFacVenta()!=null && !r.getRfcFacVenta().isEmpty()?r.getRfcFacVenta():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getVolTotalFacVenta()!=null?TextUtil.formateaNumeroComoVolumenSinComas(r.getVolTotalFacVenta())+"":"");
							}							
							b.setMensaje(msj);
							//crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"RFC DE LAS FACTURAS SI CORRESPONDEN AL PRODUCTOR\"";							
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
					
						}
					}else if(l.getIdCriterio() == 30){
						sheet = wb.createSheet("RFC CORRESPONDE CURP PROD"); //10.2 DIFERENCIA DE RFC DE PRODUCTOR RELACIÓN DE COMPRAS VS CURP
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn =0;
						lstRfcVsCurpProductor = rDAO.verificaRFCVsCURPProductor(folioCartaAdhesion);  
						if(lstRfcVsCurpProductor.size()>0){//En el listado se guardan las facturas duplicadas por productor
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("RFC Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("CURP Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto ana. (ton) Fac ");
							for(RelacionComprasTMP r: lstRfcVsCurpProductor){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(r.getClaveBodega()+";"+r.getNombreEstado()+";"+r.getPaternoProductor()+";"+r.getMaternoProductor()+";"
										+r.getNombreProductor()+";"
									+r.getRfcProductor()+";"+r.getCurpProductor()+";"+(r.getVolTotalFacVenta()!=null?r.getVolTotalFacVenta():0));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaFacMayBolOPagMenFac(folioCartaAdhesion, r.getClaveBodega(), r.getNombreEstado(), r.getFolioContrato(),
										r.getPaternoProductor(), r.getMaternoProductor(), r.getNombreProductor(), false, false, true, false);	
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(r.getClaveBodega()!=null ? r.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getNombreEstado()!=null ? r.getNombreEstado()+"":"");								
								cell = row.createCell(++countColumn);
								cell.setCellValue((r.getPaternoProductor()!=null && !r.getPaternoProductor().isEmpty() ? r.getPaternoProductor()+" " :"" )
										+(r.getMaternoProductor()!=null ?r.getMaternoProductor()+" " :"") 
										+(r.getNombreProductor()!=null ?r.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getRfcProductor()!=null && !r.getRfcProductor().isEmpty()?r.getRfcProductor():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getCurpProductor()!=null && !r.getCurpProductor().isEmpty()?r.getCurpProductor():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getVolTotalFacVenta()!=null?TextUtil.formateaNumeroComoVolumenSinComas(r.getVolTotalFacVenta())+"":"");
							}							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"RFC VS CURP SI CORRESPONDEN AL PRODUCTOR\"";							
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
					
						}			
					/*}else if(l.getIdCriterio() == 31){
						sheet = wb.createSheet("RFC PROD VS RFC COMPRADOR"); //10.1 DIFERENCIA DE RFC DE PRODUCTOR VS RFC FACTURA
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn =0;
						lstRfcProductorVsRfcFactura2 = rDAO.validaRFCFacVsFacComprador(folioCartaAdhesion, idPrograma);  
						if(lstRfcProductorVsRfcFactura2.size()>0){//En el listado se guardan las facturas duplicadas por productor
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("RFC Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("RFC Factura");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto ana. (ton) Fac ");
							for(RelacionComprasTMP r: lstRfcVsCurpProductor){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(r.getClaveBodega()+";"+r.getNombreEstado()+";"+(siAplicaFolioContrato?r.getFolioContrato()+";":"")+r.getPaternoProductor()+";"+r.getMaternoProductor()+";"
										+r.getNombreProductor()+";"
									+r.getRfcProductor()+";"+r.getRfcFacVenta()+";"+(r.getVolTotalFacVenta()!=null?r.getVolTotalFacVenta():0));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaFacMayBolOPagMenFac(folioCartaAdhesion, r.getClaveBodega(), r.getNombreEstado(), r.getFolioContrato(),
										r.getPaternoProductor(), r.getMaternoProductor(), r.getNombreProductor(), false, false, true, false);	
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(r.getClaveBodega()!=null ? r.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getNombreEstado()!=null ? r.getNombreEstado()+"":"");	
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(r.getFolioContrato()!=null ? r.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((r.getPaternoProductor()!=null && !r.getPaternoProductor().isEmpty() ? r.getPaternoProductor()+" " :"" )
										+(r.getMaternoProductor()!=null ?r.getMaternoProductor()+" " :"") 
										+(r.getNombreProductor()!=null ?r.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getRfcProductor()!=null && !r.getRfcProductor().isEmpty()?r.getRfcProductor():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getRfcFacVenta()!=null && !r.getRfcFacVenta().isEmpty()?r.getRfcFacVenta():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(r.getVolTotalFacVenta()!=null?TextUtil.formateaNumeroComoVolumenSinComas(r.getVolTotalFacVenta())+"":"");
							}							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"RFC PRODUCTOR VS RFC FACTURA SI CORRESPONDEN AL PRODUCTOR\"";							
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
					
						}	*/		
					}else if(l.getIdCriterio() == 20){
						sheet = wb.createSheet("RESUMEN DE OBSERVACIONES");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;						
						lstBoletasFacturasPagosIncosistentes = rDAO.consultaBoletasFacturasPagosIncosistentes(folioCartaAdhesion,idPrograma);
						if(lstBoletasFacturasPagosIncosistentes.size()>0){//En el listado se guardan las boletas, facturas y pagos incosistentes por productores
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();		
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);							
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}								
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Total Facturado");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Ana Ton (Boletas)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Ana Ton (Facturas)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Pagos");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Valor no procedente");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Diferencia entre volumen");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Diferencia entre importe");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Diferencia entre RFC");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen Observado");
							double valorMaximo = 0;
							for(BoletasFacturasPagosIncosistentes bod: lstBoletasFacturasPagosIncosistentes){
								countColumn = 0;
								Double array [] =  {bod.getVolBolTicket(), bod.getVolTotalFacVenta(), 
										bod.getVolEnpagos(), bod.getVolumenNoProcedente(), bod.getDiferenciaEntreVolumen(), bod.getDiferenciaEntreImportes(), bod.getDiferenciaEntreRFC() };
								valorMaximo = calcularMaximo(array);
								
								bd = new BitacoraRelcomprasDetalle();
								//System.out.println("bod.getVolTotalFacVenta() "+bod.getVolTotalFacVenta());
								bd.setMensaje(bod.getClaveBodega()+";"+bod.getNombreEstado()+";"+(siAplicaFolioContrato?bod.getFolioContrato()+";":"")+bod.getPaternoProductor()+";"+bod.getMaternoProductor()+";"
										+bod.getNombreProductor()+";"+bod.getVolTotalFacturado()+";"+bod.getVolBolTicket()+";"+bod.getVolTotalFacVenta()+";"+bod.getVolEnpagos()+";"
										+(bod.getVolumenNoProcedente()!=null?bod.getVolumenNoProcedente()+";":";")+bod.getDiferenciaEntreVolumen()+";"
										+(bod.getDiferenciaEntreImportes()!=null ?bod.getDiferenciaEntreImportes()+";":"0;")+(bod.getDiferenciaEntreRFC()!=null?bod.getDiferenciaEntreRFC()+";":"0;")+valorMaximo);
								b.getBitacoraRelcomprasDetalle().add(bd);															
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bod.getClaveBodega()!=null ? bod.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getNombreEstado()!=null ? bod.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bod.getFolioContrato()!=null ? bod.getFolioContrato()+"":"");
								}								
								cell = row.createCell(++countColumn);
								cell.setCellValue((bod.getPaternoProductor()!=null && !bod.getPaternoProductor().isEmpty() ? bod.getPaternoProductor()+" " :"" )
										+(bod.getMaternoProductor()!=null ?bod.getMaternoProductor()+" " :"") 
										+(bod.getNombreProductor()!=null ?bod.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolTotalFacturado()!=null ?bod.getVolTotalFacturado()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolBolTicket()!=null?bod.getVolBolTicket()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolTotalFacVenta()!=null?bod.getVolTotalFacVenta()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolEnpagos()!=null?bod.getVolEnpagos()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolumenNoProcedente()!=null?bod.getVolumenNoProcedente()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getDiferenciaEntreVolumen()!=null?bod.getDiferenciaEntreVolumen()+"":"");	
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getDiferenciaEntreImportes()!=null?bod.getDiferenciaEntreImportes()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getDiferenciaEntreRFC()!=null?bod.getDiferenciaEntreRFC()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(valorMaximo);
							}					
							                                                                                          
							b.setMensaje(msj);
							//crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"NO HAY INCONSISTENCIAS DE BOLETAS EN LOS PRODUCTORES\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}			
										
					}else if(l.getIdCriterio() == 21){//TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS
						sheet = wb.createSheet("TONELADAS TOTALES");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						lstGeneralToneladasTotalesPorBodFac = rDAO.consultaGeneralToneladasTotalesPorBodFac(folioCartaAdhesion);
						if(lstGeneralToneladasTotalesPorBodFac.size()>0){
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
					    	countColumn =0;
					    	row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							if(siAplicaFolioContrato){
								cell = row.createCell(countColumn);
								cell.setCellValue("Folio Contrato");
							}
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
							}else{
								cell = row.createCell(countColumn);
							}							
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Nombre Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Ana (Bol)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Ana (Fac)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Total de Boletas");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Total de Facturas");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Total de Registros");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productores Beneficiados");
							
							for(GeneralToneladasTotalesPorBodFac g: lstGeneralToneladasTotalesPorBodFac){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje((siAplicaFolioContrato?g.getFolioContrato()+";":"")+g.getClaveBodega()+";"+g.getNombreBodega()+";"
										+g.getNombreEstado()+";"+g.getPesoNetoAnaBol()+";"+g.getPesoNetoAnaFac()+";"+g.getTotalBoletas()+";"
										+g.getTotalFacturas()+";"+g.getTotalRegistros()+";"+(g.getNumeroProdBenef()!=null?g.getNumeroProdBenef():"-1"));
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								if(siAplicaFolioContrato){
									cell = row.createCell(countColumn);
									cell.setCellValue(g.getFolioContrato()!=null ? g.getFolioContrato()+"":"");
									cell = row.createCell(++countColumn);
								}else{
									cell = row.createCell(countColumn);
								}								
								cell.setCellValue(g.getClaveBodega()!=null ? g.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getNombreBodega()!=null ? g.getNombreBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getNombreEstado()!=null ? g.getNombreEstado()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getPesoNetoAnaBol()!=null ? g.getPesoNetoAnaBol()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getPesoNetoAnaFac()!=null ? g.getPesoNetoAnaFac()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getTotalBoletas()!=null ? g.getTotalBoletas()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getTotalFacturas()!=null ? g.getTotalFacturas()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getTotalRegistros()!=null ? g.getTotalRegistros()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(g.getNumeroProdBenef()!=null ? g.getNumeroProdBenef()+"":"");
							}
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);					
						}else{
							msj = "NO SE ENCONTRARON REGISTROS PARA EL REPORTE GENERAL ";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}						
					}else if(l.getIdCriterio() == 25){// "REPORTE DE RENDIMIENTO"
						sheet = wb.createSheet("VOLUMEN NO PROCEDENTE");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;						
						lstRendimientosProcedente = rDAO.consultaRendimientosProcedente(folioCartaAdhesion,idPrograma);
						
						if(lstRendimientosProcedente.size()>0){//En el listado se guardan los volumenes no procedentes
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();		
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);							
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}								
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Total Facturado");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Rendimiento maximo aceptable");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen no procedente");
							cell = row.createCell(++countColumn);
				
							for(RendimientosProcedente bod: lstRendimientosProcedente){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(bod.getClaveBodega()+";"+bod.getNombreEstado()+";"+(siAplicaFolioContrato?bod.getFolioContrato()+";":"")+bod.getPaternoProductor()+";"+bod.getMaternoProductor()+";"
										+bod.getNombreProductor()+";"+bod.getVolTotalFacturado()+";"+bod.getRendimientoMaximoAceptable()+";"
										+(bod.getVolNoProcedente()!=null?bod.getVolNoProcedente():"0"));  // AHS [LINEA] - 17022015
								b.getBitacoraRelcomprasDetalle().add(bd);		
								//Actualiza el productor como inconsistente
								rDAO.actualizaVolumenNoProcedente(folioCartaAdhesion, bod.getClaveBodega(), bod.getNombreEstado(), bod.getFolioContrato(),
										bod.getPaternoProductor(), bod.getMaternoProductor(), bod.getNombreProductor(), bod.getVolNoProcedente());	// AHS [LINEA] - 17022015
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bod.getClaveBodega()!=null ? bod.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getNombreEstado()!=null ? bod.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bod.getFolioContrato()!=null ? bod.getFolioContrato()+"":"");
								}								
								cell = row.createCell(++countColumn);
								cell.setCellValue((bod.getPaternoProductor()!=null && !bod.getPaternoProductor().isEmpty() ? bod.getPaternoProductor()+" " :"" )
										+(bod.getMaternoProductor()!=null ?bod.getMaternoProductor()+" " :"") 
										+(bod.getNombreProductor()!=null ?bod.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolTotalFacturado()!=null?bod.getVolTotalFacturado()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getRendimientoMaximoAceptable()!=null?bod.getRendimientoMaximoAceptable()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolNoProcedente()!=null?bod.getVolNoProcedente()+"":""); // AHS [LINEA] - 17022015
							}				
							                                                                                          
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"NO HAY VOLUMEN NO PROCEDENTE\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}					
						//end criterio 25
					}else if(l.getIdCriterio() == 32){// "VOLUMEN NO PROCEDENTE VARIEDAD"
						sheet = wb.createSheet("VOL NO PROCEDENTE Y APOY EN REG");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;						
						lstVolNoProcedenteYApoyEnReg = rDAO.validaVolumenNoProcedenteYApoyadoEnReg(folioCartaAdhesion,idPrograma);
						if(lstVolNoProcedenteYApoyEnReg.size()>0){//En el listado se guardan los volumenes no procedentes
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();		
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(folioCartaAdhesion);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);							
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}								
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Variedad");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen Factura");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen Apoyado en Regional");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen no procedente");
							cell = row.createCell(++countColumn);
				
							for(VolumenNoProcedente bod: lstVolNoProcedenteYApoyEnReg){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(bod.getClaveBodega()+";"+bod.getNombreEstado()+";"+(siAplicaFolioContrato?bod.getFolioContrato()+";":"")+bod.getPaternoProductor()+";"+bod.getMaternoProductor()+";"
										+bod.getNombreProductor()+";"+bod.getIdVariedad()+";"+bod.getVolTotalFacturado()+";"+bod.getVolApoyadoEnRegional()+";"
										+(bod.getVolNoProcedente()!=null?bod.getVolNoProcedente():"0"));  
								b.getBitacoraRelcomprasDetalle().add(bd);		
								//Actualiza el productor como inconsistente
								rDAO.actualizaVolumenNoProcedente(folioCartaAdhesion, bod.getClaveBodega(), bod.getNombreEstado(), bod.getFolioContrato(),
										bod.getPaternoProductor(), bod.getMaternoProductor(), bod.getNombreProductor(), bod.getVolNoProcedente().toString());	// AHS [LINEA] - 17022015
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bod.getClaveBodega()!=null ? bod.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getNombreEstado()!=null ? bod.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bod.getFolioContrato()!=null ? bod.getFolioContrato()+"":"");
								}								
								cell = row.createCell(++countColumn);
								cell.setCellValue((bod.getPaternoProductor()!=null && !bod.getPaternoProductor().isEmpty() ? bod.getPaternoProductor()+" " :"" )
										+(bod.getMaternoProductor()!=null ?bod.getMaternoProductor()+" " :"") 
										+(bod.getNombreProductor()!=null ?bod.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getIdVariedad()!=null?bod.getIdVariedad()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolTotalFacturado()!=null?bod.getVolTotalFacturado()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolApoyadoEnRegional()!=null?bod.getVolApoyadoEnRegional()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolNoProcedente()!=null?bod.getVolNoProcedente()+"":""); 
							}				
							                                                                                          
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"NO HAY VOLUMEN NO PROCEDENTE Y APOYADO EN REGIONAL\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}					
						//end criterio 25
					}else if(l.getIdCriterio()== 27){//VOLUMEN DE FINIQUITO
						sheet = wb.createSheet("VOLUMEN DE FINIQUITO");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstVolumenCumplido = rDAO.consultaVolumenCumplido(folioCartaAdhesion);
						if(lstVolumenCumplido.size()>0){//En el listado se guardan el volumen no cumplido
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());							
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");							
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Comprador");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Vendedor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio Pactado por Ton (Dlls)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen");								
							for(VolumenFiniquito p: lstVolumenCumplido){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(p.getClaveBodega()+";"+(siAplicaFolioContrato?p.getFolioContrato()+";":"")+p.getNombreComprador()+";"+p.getNombreVendedor()+";"
										+(p.getPrecioPactadoPorTonelada()!=null?p.getPrecioPactadoPorTonelada()+";":0+";")+(p.getVolumen()!=null?p.getVolumen():0));								
								b.getBitacoraRelcomprasDetalle().add(bd);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(p.getClaveBodega()!=null ? p.getClaveBodega()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(p.getFolioContrato()!=null ? p.getFolioContrato()+"":"");
								}							
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNombreComprador()!=null ? p.getNombreComprador()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getNombreVendedor()!=null ? p.getNombreVendedor()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getPrecioPactadoPorTonelada()!=null?p.getPrecioPactadoPorTonelada()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(p.getVolumen()!=null?TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumen())+"":"");
							}							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "No se encontraron registros en \"VOLUMEN NO CUMPLIDO\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}						
					}//END 27
				}//END RECORRIDO lstValidacionPorGrupo GENERAL				
				nombreArchivoLogXls = "General.xls";
				nombreArchivoLogCargaXls = "LogCargaArchivo.xls"; //Log del archivo excel  // AHS 29012015
				FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogXls));
			    wb.write(out);
			    out.close();
			}else if(grupoCriterio.equals("Boletas")){
				lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);
				String s = integraCriteriosByGrupo(lstValidacionPorGrupo);
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s, "0,1", true);
				if(lstBitacoraRelCompras.size()>0){
					addActionError("Los criterio para el grupo '"+grupoCriterio+"' ya se encuentra validado y se genero bitacora definitiva, por favor verifique ");
					return SUCCESS;
				}						
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1");
				if(lstBitacoraRelCompras.size() > 0){	
					//Actualiza campo boleta_incosistente en la tabla relacion_compras_tmp
					rDAO.actualizaBolFactPagEnRelacionComprasTMP(folioCartaAdhesion, true, false, false);
					//Borra el registro de la bitacora
					for(BitacoraRelcompras b: lstBitacoraRelCompras){
						cDAO.borrarObjeto(b);
					}
				}			
				for(CatCriteriosValidacion l:lstValidacionPorGrupo){
					if(l.getIdCriterio() == 8){//"BOLETAS NO ESTEN DUPLICADAS POR BODEGA"
						sheet = wb.createSheet("BOLETAS DUPLICADAS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstBoletasDuplicadas = rDAO.verificaBoletaDuplicadasEnRelComprasTmp(folioCartaAdhesion);
						if(lstBoletasDuplicadas.size()>0){//En el listado se guardan las boletas duplicadas por bodega
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();		
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);							
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}								
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Boleta");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Ana. (Ton)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Fecha de Entrada");
							for(BoletasDuplicadas bod: lstBoletasDuplicadas){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(bod.getClaveBodega()+";"+bod.getNombreEstado()+";"+(siAplicaFolioContrato?bod.getFolioContrato()+";":"")+bod.getPaternoProductor()+";"+bod.getMaternoProductor()+";"
										+bod.getNombreProductor()+";"+bod.getBoletaTicketBascula()+";"+bod.getVolBolTicket()+";"+bod.getFechaEntradaBoleta());
								b.getBitacoraRelcomprasDetalle().add(bd);
								actualizarRelacionComprasTMPByBoletasIncosistentes(folioCartaAdhesion, bod.getClaveBodega(),bod.getNombreEstado(),bod.getFolioContrato(), 
										bod.getPaternoProductor(), bod.getMaternoProductor(), bod.getNombreProductor(), bod.getBoletaTicketBascula());
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bod.getClaveBodega()!=null ? bod.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getNombreEstado()!=null ? bod.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bod.getFolioContrato()!=null ? bod.getFolioContrato()+"":"");
								}								
								cell = row.createCell(++countColumn);
								cell.setCellValue((bod.getPaternoProductor()!=null && !bod.getPaternoProductor().isEmpty() ? bod.getPaternoProductor()+" " :"" )
										+(bod.getMaternoProductor()!=null ?bod.getMaternoProductor()+" " :"") 
										+(bod.getNombreProductor()!=null ?bod.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getBoletaTicketBascula()!=null && !bod.getBoletaTicketBascula().isEmpty()?bod.getBoletaTicketBascula():"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getVolBolTicket()!=null?bod.getVolBolTicket()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bod.getFechaEntradaBoleta()!=null?bod.getFechaEntradaBoleta()+"":"");								
							}					
							                                                                                          
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"NO HAY DUPLICADOS EN LAS BOLETAS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}else if(l.getIdCriterio()== 9){
						//pideTipoPeriodo 1 : LINEAMIENTO; 2: CONTRATO AXC; 3: DICTAMEN AUDITOR   
						sheet = wb.createSheet("BOLETAS FUERA PER");
						sheet = setMargenSheet(sheet);
						countRow = 0; 
						countColumn = 0;
						
						if(pideTipoPeriodo == 1 || pideTipoPeriodo == 3){
							fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicioAuditor).toString();
							fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinAuditor).toString();
							lstBoletasFueraDePeriodo = rDAO.verificaBoletasFueraDePeriodo(folioCartaAdhesion, fechaInicioS, fechaFinS);
							if(lstBoletasFueraDePeriodo.size()>0){//En el listado se guardan las boletas que estan fuera del periodo seleccionado
								llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
								msj = l.getCriterio()+"/";//new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioAuditor).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFinAuditor).toString();
								row = sheet.createRow(countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(msj);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue("Clave Bodega");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Estado");
								//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue("Folio Contrato");
								}			
								cell = row.createCell(++countColumn);
								cell.setCellValue("Productor");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Boleta");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Fecha Entrada");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Peso Neto Ana. (Ton):");
								for(BoletasFueraDePeriodo bfp: lstBoletasFueraDePeriodo){
									countColumn = 0;
									bd = new BitacoraRelcomprasDetalle();
									bd.setMensaje(bfp.getClaveBodega()+";"+bfp.getNombreEstado()+";"+(siAplicaFolioContrato?bfp.getFolioContrato()+";":"")
												+bfp.getPaternoProductor()+";"+bfp.getMaternoProductor()+";"+bfp.getNombreProductor()+";"
												+bfp.getBoletaTicketBascula()+";"+(bfp.getFechaEntradaBoleta()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getFechaEntradaBoleta()).toString()+";":";")
												+(bfp.getVolBolTicket()!=null?bfp.getVolBolTicket():"-1"));
									b.getBitacoraRelcomprasDetalle().add(bd);
									actualizarRelacionComprasTMPByBoletasIncosistentes(folioCartaAdhesion, bfp.getClaveBodega(),bfp.getNombreEstado(),bfp.getFolioContrato(), 
											bfp.getPaternoProductor(), bfp.getMaternoProductor(), bfp.getNombreProductor(), bfp.getBoletaTicketBascula());
									row = sheet.createRow(++countRow);
									cell = row.createCell(countColumn);
									cell.setCellValue(bfp.getClaveBodega()!=null ? bfp.getClaveBodega()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getNombreEstado()!=null ? bfp.getNombreEstado()+"":"");
									if(siAplicaFolioContrato){
										cell = row.createCell(++countColumn);
										cell.setCellValue(bfp.getFolioContrato()!=null ? bfp.getFolioContrato()+"":"");
									}
									cell = row.createCell(++countColumn);
									cell.setCellValue((bfp.getPaternoProductor()!=null && !bfp.getPaternoProductor().isEmpty() ? bfp.getPaternoProductor()+" " :"" )
											+(bfp.getMaternoProductor()!=null ?bfp.getMaternoProductor()+" " :"") 
											+(bfp.getNombreProductor()!=null ?bfp.getNombreProductor() :""));
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getBoletaTicketBascula()!=null && !bfp.getBoletaTicketBascula().isEmpty()?bfp.getBoletaTicketBascula():"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getFechaEntradaBoleta()!=null?bfp.getFechaEntradaBoleta()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getVolBolTicket()!=null?bfp.getVolBolTicket()+"":"");
								}							
								 
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}else{
								msj = "La validación es correcta \"BOLETAS SE ENCUENTRAN DENTRO DEL PERIODO DE ACOPIO\"";
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								llenarBitacora(false, l.getIdCriterio());
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}
					
						}else{//Periodo por contrato
							if(aplicaAdendum){
								//Actualizar periodo en la tabla de relacion de compras
								lstContratos = rDAO.consultaPeriodosContratosRelCompras(folioCartaAdhesion);
								//lstRCTemp =	rDAO.consultaSoloContratosRelacionCompras(folioCartaAdhesion);								
								for(ContratosRelacionCompras r: lstContratos){
									//Preguntar si el folio del contrato se capturo la fecha del adendum									
									if (fechaInicioAdendumContrato.get(r.getFolioContrato())!=null && fechaFinAdendumContrato.get(r.getFolioContrato())!=null){
										//Actualiza fecha del adendum que se capturo
										rDAO.actualizaFechaAdendumContratoEnRelCompras(r.getFolioContrato(), folioCartaAdhesion, fechaInicioAdendumContrato.get(r.getFolioContrato()), fechaFinAdendumContrato.get(r.getFolioContrato()));
										r.setPeriodoInicialPagoAdendum(fechaInicioAdendumContrato.get(r.getFolioContrato()));
										r.setPeriodoFinalPagoAdendum(fechaFinAdendumContrato.get(r.getFolioContrato()));										
										cDAO.guardaObjeto(r);									
									}else{
										rDAO.actualizaFechaAdendumContratoEnRelCompras(r.getFolioContrato(), folioCartaAdhesion, r.getPeriodoInicialPago(), r.getPeriodoFinalPago());
										r.setPeriodoInicialPagoAdendum(null);
										r.setPeriodoFinalPagoAdendum(null);										
										cDAO.guardaObjeto(r);	
									}									
								}
								lstBoletasFueraDePeriodoPago = rDAO.verificaBoletasFueraDePeriodoContratoAdendum(folioCartaAdhesion);					
							}else{
								lstBoletasFueraDePeriodoPago = rDAO.verificaBoletasFueraDePeriodoPago(folioCartaAdhesion);
							}				
								
							if(lstBoletasFueraDePeriodoPago.size()>0){//En el listado se guardan las boletas que estan fuera del periodo seleccionado
								llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
								msj = l.getCriterio();
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue("Clave Bodega");
								cell = row.createCell(1);
								cell.setCellValue("Estado");
								cell = row.createCell(2);
								cell.setCellValue("Folio Contrato");
								cell = row.createCell(3);
								cell.setCellValue("Productor");
								cell = row.createCell(4);
								cell.setCellValue("Boleta");
								cell = row.createCell(5);
								cell.setCellValue("Periodo Inicio");
								cell = row.createCell(6);
								cell.setCellValue("Periodo Termino");
								cell = row.createCell(7);
								cell.setCellValue("Fecha Entrada");
								cell = row.createCell(8);
								cell.setCellValue("Peso Neto Ana. (Ton)");
								for(BoletasFueraDePeriodoPago bfp: lstBoletasFueraDePeriodoPago){
									bd = new BitacoraRelcomprasDetalle();
									bd.setMensaje(bfp.getClaveBodega()+";"+bfp.getNombreEstado()+";"+(siAplicaFolioContrato ? bfp.getFolioContrato()+";":"")
												+bfp.getPaternoProductor()+";"+bfp.getMaternoProductor()+";"+bfp.getNombreProductor()+";"
												+bfp.getBoletaTicketBascula()+";"+bfp.getPeriodoInicialPago()+";"+bfp.getPeriodoFinalPago()+";"
												+new SimpleDateFormat("yyyy-MM-dd").format(bfp.getFechaEntradaBoleta()).toString()+";"+bfp.getVolBolTicket());
									b.getBitacoraRelcomprasDetalle().add(bd);
									actualizarRelacionComprasTMPByBoletasIncosistentes(folioCartaAdhesion, bfp.getClaveBodega(),bfp.getNombreEstado(),bfp.getFolioContrato(), 
											bfp.getPaternoProductor(), bfp.getMaternoProductor(), bfp.getNombreProductor(), bfp.getBoletaTicketBascula());
									row = sheet.createRow(++countRow);
									cell = row.createCell(0);
									cell.setCellValue(bfp.getClaveBodega()!=null ? bfp.getClaveBodega()+"":"");
									cell = row.createCell(1);
									cell.setCellValue(bfp.getNombreEstado()!=null ? bfp.getNombreEstado()+"":"");
									cell = row.createCell(2);
									cell.setCellValue(bfp.getFolioContrato()!=null ? bfp.getFolioContrato()+"":"");
									cell = row.createCell(3);
									cell.setCellValue((bfp.getPaternoProductor()!=null && !bfp.getPaternoProductor().isEmpty() ? bfp.getPaternoProductor()+" " :"" )
											+(bfp.getMaternoProductor()!=null ?bfp.getMaternoProductor()+" " :"") 
											+(bfp.getNombreProductor()!=null ?bfp.getNombreProductor() :""));
									cell = row.createCell(4);
									cell.setCellValue(bfp.getBoletaTicketBascula()!=null && !bfp.getBoletaTicketBascula().isEmpty()?bfp.getBoletaTicketBascula():"");									
									cell = row.createCell(5);
									cell.setCellValue(bfp.getPeriodoInicialPago()!=null?bfp.getPeriodoInicialPago()+"":"");
									cell = row.createCell(6);
									cell.setCellValue(bfp.getPeriodoFinalPago()!=null?bfp.getPeriodoFinalPago()+"":"");
									cell = row.createCell(7);
									cell.setCellValue(bfp.getFechaEntradaBoleta()!=null?bfp.getFechaEntradaBoleta()+"":"");
									cell = row.createCell(8);
									cell.setCellValue(bfp.getVolBolTicket()!=null?bfp.getVolBolTicket()+"":"");
									
								}						
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}else{
								msj = "La validación es correcta \"BOLETAS SE ENCUENTRAN DENTRO DEL PERIODO DEL CONTRATO\"";
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								llenarBitacora(false, l.getIdCriterio());
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}						
							
						}//end else por periodo de contrato
						
					}else if(l.getIdCriterio() == 22){
						sheet = wb.createSheet("BOLETAS VAL NULOS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						recuperaCamposPorGrupo(7);
						camposQueAplica = integrarCamposAplica(lstGruposCamposDetalleRelacionV);	
					    lstBoletasCamposRequeridos = rDAO.consultaBoletasInfoRequerida(folioCartaAdhesion, camposQueAplica);
					    if(lstBoletasCamposRequeridos.size()>0){
					    	llenarBitacora(true, l.getIdCriterio());
					    	msj = l.getCriterio();
					    	countColumn =0;
					    	row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Boleta");
							if(camposQueAplica.contains("12,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Fecha Entrada");
							}
							if(camposQueAplica.contains("63,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Volumen");
							}							
							cell = row.createCell(++countColumn);
							cell.setCellValue("P.N.A. (TON.)");
							for(BoletasCamposRequeridos bo: lstBoletasCamposRequeridos){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(bo.getClaveBodega()+";"+bo.getNombreEstado()+";"+(siAplicaFolioContrato?bo.getFolioContrato()+";":"")
										+bo.getPaternoProductor()+";"+bo.getMaternoProductor()+";"+bo.getNombreProductor()+";"+bo.getBoletaTicketBascula()+";"
										+(camposQueAplica.contains("12,")?bo.getFechaEntrada()+";":"")
										+(camposQueAplica.contains("63,")?bo.getVolumen()+";":"")+(bo.getVolBolTicket()!=null?bo.getVolBolTicket():"0"));								
								b.getBitacoraRelcomprasDetalle().add(bd);
								actualizarRelacionComprasTMPByBoletasIncosistentes(folioCartaAdhesion, bo.getClaveBodega(),bo.getNombreEstado(),bo.getFolioContrato(), 
										bo.getPaternoProductor(), bo.getMaternoProductor(), bo.getNombreProductor(), bo.getBoletaTicketBascula());
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bo.getClaveBodega()!=null ? bo.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bo.getNombreEstado()!=null ? bo.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getFolioContrato()!=null ? bo.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((bo.getPaternoProductor() != null ? bo.getPaternoProductor()+" ": "")
										+ (bo.getMaternoProductor() != null ? bo.getMaternoProductor()+" ":"")
										+ (bo.getNombreProductor() != null ? bo.getNombreProductor():""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bo.getBoletaTicketBascula());
								if(camposQueAplica.contains("12,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getFechaEntrada());
								}
								
								if(camposQueAplica.contains("63,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getVolumen());
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((bo.getVolBolTicket()!=null?bo.getVolBolTicket():"")+"");
							}
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
					    	
					    }else{
							msj = "La validación es correcta \"NO SE ENCONTRARON VALORES NULOS EN LOS CAMPOS REQUERIDOS DE LAS BOLETAS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);							
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					    
					}
					
				}//end recorrido lstValidacionPorGrupo BOLETAS
				nombreArchivoLogXls = "Boletas.xls";
				nombreArchivoLogCargaXls = "LogCargaArchivo.xls"; //Log del archivo excel  // AHS 29012015
				FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogXls));
			    wb.write(out);
			    out.close();
			}else if(grupoCriterio.equals("Facturas")){
				lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);
				String s = integraCriteriosByGrupo(lstValidacionPorGrupo);
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1", true);
				if(lstBitacoraRelCompras.size()>0){
					addActionError("Los criterios para el grupo '"+grupoCriterio+"' ya se encuentra validado y se genero bitacora definitiva, por favor verifique ");
					return SUCCESS;
				}						
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s.toString(), "0,1");
				if(lstBitacoraRelCompras.size() > 0){
					//Actualiza campo factura_inconsistente en la tabla relacion_compras_tmp
					rDAO.actualizaBolFactPagEnRelacionComprasTMP(folioCartaAdhesion, false, true, false);
					//Actualiza campo facturas_mayores_boletas en la tabla relacion_compras_tmp
					rDAO.actualizaCamposIconsistentes(folioCartaAdhesion, true, false,false, false);
					//Borra el registro de la bitacora
					for(BitacoraRelcompras b: lstBitacoraRelCompras){						
						cDAO.borrarObjeto(b);						
					}					
				}				
				for(CatCriteriosValidacion l:lstValidacionPorGrupo){
				if(l.getIdCriterio() == 10){
					sheet = wb.createSheet("FACTURAS DUPLICADAS");
					sheet = setMargenSheet(sheet);
					countRow = 0;
					countColumn = 0;
					lstProductorFactura = rDAO.verificaFacturaDuplicadasEnRelComprasTmp(folioCartaAdhesion);
					if(lstProductorFactura.size()>0){//En el listado se guardan las facturas duplicadas por productor
						llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
						msj = l.getCriterio();
						row = sheet.createRow(countRow);
						cell = row.createCell(countColumn);
						cell.setCellValue(msj);
						row = sheet.createRow(++countRow);
						cell = row.createCell(countColumn);
						cell.setCellValue("Clave Bodega");
						cell = row.createCell(++countColumn);
						cell.setCellValue("Estado");
						//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
						if(siAplicaFolioContrato){
							cell = row.createCell(++countColumn);
							cell.setCellValue("Folio Contrato");
						}	
						cell = row.createCell(++countColumn);
						cell.setCellValue("Productor");
						cell = row.createCell(++countColumn);
						cell.setCellValue("Folio Fiscal de la Factura");
						cell = row.createCell(++countColumn);
						cell.setCellValue("Peso Neto Ana. (Ton)");
						cell = row.createCell(++countColumn);
						cell.setCellValue("Fecha de Factura ");
						for(ProductorFactura pf: lstProductorFactura){
							countColumn = 0;
							bd = new BitacoraRelcomprasDetalle();
							bd.setMensaje(pf.getClaveBodega()+";"+pf.getNombreEstado()+";"+(siAplicaFolioContrato ? pf.getFolioContrato()+";":"")+
									pf.getPaternoProductor()+";"+pf.getMaternoProductor()+";"+pf.getNombreProductor()+";"+pf.getFolioFacturaVenta()+";"+
									pf.getVolTotalFacVenta()+";"+
									(pf.getFechaEmisionFac() != null ? new SimpleDateFormat("yyyy-MM-dd").format(pf.getFechaEmisionFac()).toString():"-"));
							b.getBitacoraRelcomprasDetalle().add(bd);
							actualizarRelacionComprasTMPByFacturasIncosistentes(folioCartaAdhesion, pf.getClaveBodega(),pf.getNombreEstado(),pf.getFolioContrato(), 
									pf.getPaternoProductor(), pf.getMaternoProductor(), pf.getNombreProductor(), pf.getFolioFacturaVenta());
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(pf.getClaveBodega()!=null ? pf.getClaveBodega()+"":"");
							cell = row.createCell(++countColumn);
							cell.setCellValue(pf.getNombreEstado()!=null ? pf.getNombreEstado()+"":"");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue(pf.getFolioContrato()!=null ? pf.getFolioContrato()+"":"");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue((pf.getPaternoProductor()!=null && !pf.getPaternoProductor().isEmpty() ? pf.getPaternoProductor()+" " :"" )
									+(pf.getMaternoProductor()!=null ? pf.getMaternoProductor()+" " :"") 
									+(pf.getNombreProductor()!=null ? pf.getNombreProductor() :""));
							cell = row.createCell(++countColumn);
							cell.setCellValue(pf.getFolioFacturaVenta()!=null && !pf.getFolioFacturaVenta().isEmpty()?pf.getFolioFacturaVenta():"");
							cell = row.createCell(++countColumn);
							cell.setCellValue(pf.getVolTotalFacVenta()!=null?pf.getVolTotalFacVenta()+"":"");
							cell = row.createCell(++countColumn);
							cell.setCellValue(pf.getFechaEmisionFac()!=null?pf.getFechaEmisionFac()+"":"");
						}									
						b.setMensaje(msj);						
						b = (BitacoraRelcompras) cDAO.guardaObjeto(b);						
						

					}else{
						msj = "La validación es correcta \"NO HAY FACTURAS DUPLICADAS\"";
						row = sheet.createRow(countRow);
						cell = row.createCell(0);
						cell.setCellValue(msj);
						llenarBitacora(false, l.getIdCriterio());
						b.setMensaje(msj);
						cDAO.guardaObjeto(b);
					}				
				}else if(l.getIdCriterio()== 11){
						sheet = wb.createSheet("FAC MAY BOL");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;

						double difVolumenFacMayorBoleta = 0;
						lstBoletasVsFacturas = rDAO.verificaBoletaVsFacturas(folioCartaAdhesion);
						if(lstBoletasVsFacturas.size()>0){//VOLUMEN FACTURADO MAYOR
							//Guardar en bitacora
							llenarBitacora(true, l.getIdCriterio());
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}	
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen Boletas");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Volumen Facturas");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Diferencia de Volumen");
							for(BoletasVsFacturas bf: lstBoletasVsFacturas){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								difVolumenFacMayorBoleta = bf.getVolTotalFacVenta()- bf.getVolBolTicket();
								bd.setMensaje(bf.getClaveBodega()+";"+bf.getNombreEstado()+";"+(siAplicaFolioContrato?bf.getFolioContrato()+";":"")+
										bf.getPaternoProductor()+";"+bf.getMaternoProductor()+";"+bf.getNombreProductor()+";"
								+bf.getVolBolTicket()+";"+(bf.getVolTotalFacVenta()!=null?bf.getVolTotalFacVenta()+";":"0;")+TextUtil.formateaNumeroComoVolumenSinComas(difVolumenFacMayorBoleta));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaFacMayBolOPagMenFac(folioCartaAdhesion, bf.getClaveBodega(), bf.getNombreEstado(), bf.getFolioContrato(),
										bf.getPaternoProductor(), bf.getMaternoProductor(), bf.getNombreProductor(), true, false);
								
							
								//Actualiza campo dif_volumen_fac_mayor por la diferencia de factura mayor a la boleta
								rDAO.actualizaDiferenciaVolumenXFacturaMayor(folioCartaAdhesion, bf.getClaveBodega(), bf.getNombreEstado(), bf.getFolioContrato(),
										bf.getPaternoProductor(), bf.getMaternoProductor(), bf.getNombreProductor(), difVolumenFacMayorBoleta);	
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bf.getClaveBodega()!=null ? bf.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bf.getNombreEstado()!=null ? bf.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bf.getFolioContrato()!=null ? bf.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((bf.getPaternoProductor()!=null && !bf.getPaternoProductor().isEmpty() ? bf.getPaternoProductor()+" " :"" )
										+(bf.getMaternoProductor()!=null ?bf.getMaternoProductor()+" " :"") 
										+(bf.getNombreProductor()!=null ?bf.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bf.getVolBolTicket()!=null ? bf.getVolBolTicket()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bf.getVolTotalFacVenta()!=null ? bf.getVolTotalFacVenta()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(difVolumenFacMayorBoleta+"");
							}							
							b.setMensaje(msj);
							//crearCeldaenLogXls();
							cDAO.guardaObjeto(b);
						}else{
							msj = "La validación es correcta \"EL MONTO DE LAS BOLETAS SI ES MAYOR AL MONTO FACTURADO";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}else if(l.getIdCriterio() == 13){//"FACTURA FUERA DEL PERIODO DEL AUDITOR"
						sheet = wb.createSheet("FAC FUERA PER");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;						
						if(pideTipoPeriodo == 1 || pideTipoPeriodo == 3){//PERIODO POR LINEAMIENTO (1) : DICTAMEN DE AUDITOR (3)
							if(aplicaComplemento){
								fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicioAuditor).toString();
								fechaInicioComS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();
								fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFinAuditor).toString();
								fechaFinComS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
								lstFacturaFueraPeriodo = rDAO.verificaFacturaFueraDePeriodoAudLinComplemento(folioCartaAdhesion, fechaInicioS, fechaFinS, fechaInicioComS, fechaFinComS);
							}else{
								fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicioAuditor).toString();
								fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinAuditor).toString();
								lstFacturaFueraPeriodo = rDAO.verificaFacturaFueraDePeriodo(folioCartaAdhesion, fechaInicioS, fechaFinS);
							}
							
							if(lstFacturaFueraPeriodo.size()>0){//En el listado se guardan las facturas duplicadas por productor
								llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
								if(aplicaComplemento){
									msj = l.getCriterio()+" "+new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioAuditor).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFinAuditor).toString()
											+ " y "+new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFin).toString();	
								}else{									
									msj = l.getCriterio()+" "+new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioAuditor).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFinAuditor).toString();
								}
								
								row = sheet.createRow(countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(msj);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue("Clave Bodega");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Estado");
								//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue("Folio Contrato");
								}	
								cell = row.createCell(++countColumn);
								cell.setCellValue("Productor");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Factura");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Fecha Emisión");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Peso Neto Ana. (Ton)");
								for(FacturaFueraPeriodoAuditor f: lstFacturaFueraPeriodo){
									countColumn = 0;
									bd = new BitacoraRelcomprasDetalle();
									bd.setMensaje(f.getClaveBodega() + ";"+f.getNombreEstado()+";"+(siAplicaFolioContrato?f.getFolioContrato()+";":"")
											+ f.getPaternoProductor() + ";"+ f.getMaternoProductor() + ";"+ f.getNombreProductor() + ";"								
											+ f.getFolioFacturaVenta()+";"+(f.getFechaEmisionFac()!=null?new SimpleDateFormat("yyyy-MM-dd").format(f.getFechaEmisionFac())+";":"")+f.getVolTotalFacVenta());
									b.getBitacoraRelcomprasDetalle().add(bd);
									actualizarRelacionComprasTMPByFacturasIncosistentes(folioCartaAdhesion, f.getClaveBodega(),f.getNombreEstado(),f.getFolioContrato(), 
											f.getPaternoProductor(), f.getMaternoProductor(), f.getNombreProductor(), f.getFolioFacturaVenta());
									row = sheet.createRow(++countRow);
									cell = row.createCell(countColumn);
									cell.setCellValue(f.getClaveBodega()!=null ? f.getClaveBodega()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getNombreEstado()!=null ? f.getNombreEstado()+"":"");
									if(siAplicaFolioContrato){
										cell = row.createCell(++countColumn);
										cell.setCellValue(f.getFolioContrato()!=null ? f.getFolioContrato()+"":"");
									}
									cell = row.createCell(++countColumn);
									cell.setCellValue((f.getPaternoProductor()!=null && !f.getPaternoProductor().isEmpty() ? f.getPaternoProductor()+" " :"" )
											+(f.getMaternoProductor()!=null ?f.getMaternoProductor()+" " :"") 
											+(f.getNombreProductor()!=null ?f.getNombreProductor() :""));
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getFolioFacturaVenta()!=null ? f.getFolioFacturaVenta()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getFechaEmisionFac()!=null ? f.getFechaEmisionFac()+"":"");		
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getVolTotalFacVenta()!=null?f.getVolTotalFacVenta()+"":"");
								}								
								b.setMensaje(msj);
								b = (BitacoraRelcompras) cDAO.guardaObjeto(b);
																
								
							}else{
								msj = "La validación es correcta \"LAS FACTURAS SE ENCUENTRAN DENTRO DEL PERIODO";
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								llenarBitacora(false, l.getIdCriterio());
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}	
						}else{//Periodo por contrato
							if(aplicaAdendum ){
								//Actualizar periodo en la tabla de relacion de compras
								lstContratos = rDAO.consultaPeriodosContratosRelCompras(folioCartaAdhesion);
								//lstRCTemp =	rDAO.consultaSoloContratosRelacionCompras(folioCartaAdhesion);								
								for(ContratosRelacionCompras r: lstContratos){
									//Preguntar si el folio del contrato se capturo la fecha del adendum									
									if (fechaInicioAdendumContrato.get(r.getFolioContrato())!=null && fechaFinAdendumContrato.get(r.getFolioContrato())!=null){
										//Actualiza fecha del adendum que se capturo
										rDAO.actualizaFechaAdendumContratoEnRelCompras(r.getFolioContrato(), folioCartaAdhesion, fechaInicioAdendumContrato.get(r.getFolioContrato()), fechaFinAdendumContrato.get(r.getFolioContrato()));
										r.setPeriodoInicialPagoAdendum(fechaInicioAdendumContrato.get(r.getFolioContrato()));
										r.setPeriodoFinalPagoAdendum(fechaFinAdendumContrato.get(r.getFolioContrato()));										
										cDAO.guardaObjeto(r);									
									}else{
										rDAO.actualizaFechaAdendumContratoEnRelCompras(r.getFolioContrato(), folioCartaAdhesion, r.getPeriodoInicialPago(), r.getPeriodoFinalPago());
										r.setPeriodoInicialPagoAdendum(null);
										r.setPeriodoFinalPagoAdendum(null);										
										cDAO.guardaObjeto(r);	
									}									
								}
								if(aplicaComplemento){
									fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();
									fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
									lstFacturaFueraPeriodoPago = rDAO.verificaFacturaFueraDePeriodoContratoAdendumComplemento(folioCartaAdhesion, fechaInicioS, fechaFinS );
								} else {
									lstFacturaFueraPeriodoPago = rDAO.verificaFacturaFueraDePeriodoContratoAdendum(folioCartaAdhesion);
								}
							}else{
								if(aplicaComplemento){
									fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();
									fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
									lstFacturaFueraPeriodoPago = rDAO.verificaFacturaFueraDePeriodoContratoComplemento(folioCartaAdhesion, fechaInicioS, fechaFinS);
								} else {
									lstFacturaFueraPeriodoPago = rDAO.verificaFacturaFueraDePeriodoContrato(folioCartaAdhesion);
								}								
							}			
							if(lstFacturaFueraPeriodoPago.size()>0){//En el listado se guardan las facturas que estan fuera del periodo seleccionado
								llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
								msj = l.getCriterio();
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue("Clave Bodega");
								cell = row.createCell(1);
								cell.setCellValue("Estado");
								cell = row.createCell(2);
								cell.setCellValue("Folio Contrato");
								cell = row.createCell(3);
								cell.setCellValue("Productor");
								cell = row.createCell(4);
								cell.setCellValue("Factura");
								cell = row.createCell(5);
								cell.setCellValue("Periodo Inicio");
								cell = row.createCell(6);
								cell.setCellValue("Periodo Termino");
								cell = row.createCell(7);
								cell.setCellValue("Fecha Emisión");
								cell = row.createCell(8);
								cell.setCellValue("Peso Neto Ana. (Ton)");
								for(FacturaFueraPeriodoPago bfp: lstFacturaFueraPeriodoPago){
									bd = new BitacoraRelcomprasDetalle();
									bd.setMensaje(bfp.getClaveBodega()+";"+bfp.getNombreEstado()+";"+(siAplicaFolioContrato ? bfp.getFolioContrato()+";":"")
												+bfp.getPaternoProductor()+";"+bfp.getMaternoProductor()+";"+bfp.getNombreProductor()+";"
												+bfp.getFolioFacturaVenta()+";"
												+(bfp.getPeriodoInicialPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoInicialPago()).toString():"")+";"
												+(bfp.getPeriodoFinalPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoFinalPago()).toString():"")+";"
												+new SimpleDateFormat("yyyy-MM-dd").format(bfp.getFechaEmisionFac()).toString()+";"+bfp.getVolTotalFacVenta());
									b.getBitacoraRelcomprasDetalle().add(bd);									
									actualizarRelacionComprasTMPByFacturasIncosistentes(folioCartaAdhesion, bfp.getClaveBodega(),bfp.getNombreEstado(),bfp.getFolioContrato(), 
											bfp.getPaternoProductor(), bfp.getMaternoProductor(), bfp.getNombreProductor(), bfp.getFolioFacturaVenta());
									row = sheet.createRow(++countRow);
									cell = row.createCell(0);
									cell.setCellValue(bfp.getClaveBodega()!=null ? bfp.getClaveBodega()+"":"");
									cell = row.createCell(1);
									cell.setCellValue(bfp.getNombreEstado()!=null ? bfp.getNombreEstado()+"":"");
									cell = row.createCell(2);
									cell.setCellValue(bfp.getFolioContrato()!=null ? bfp.getFolioContrato()+"":"");
									cell = row.createCell(3);
									cell.setCellValue((bfp.getPaternoProductor()!=null && !bfp.getPaternoProductor().isEmpty() ? bfp.getPaternoProductor()+" " :"" )
											+(bfp.getMaternoProductor()!=null ?bfp.getMaternoProductor()+" " :"") 
											+(bfp.getNombreProductor()!=null ?bfp.getNombreProductor() :""));
									cell = row.createCell(4);
									cell.setCellValue(bfp.getFolioFacturaVenta()!=null && !bfp.getFolioFacturaVenta().isEmpty()?bfp.getFolioFacturaVenta():"");									
									cell = row.createCell(5);
									cell.setCellValue(bfp.getPeriodoInicialPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoInicialPago()).toString()+"":"");
									cell = row.createCell(6);
									cell.setCellValue(bfp.getPeriodoFinalPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoFinalPago()).toString()+"":"");
									cell = row.createCell(7);
									cell.setCellValue(bfp.getFechaEmisionFac()!=null?bfp.getFechaEmisionFac()+"":"");
									cell = row.createCell(8);
									cell.setCellValue(bfp.getVolTotalFacVenta()!=null?bfp.getVolTotalFacVenta()+"":"");
									
								}						
								b.setMensaje(msj);
								b = (BitacoraRelcompras) cDAO.guardaObjeto(b);						
														
								
							}else{
								msj = "La validación es correcta \"FACTURAS SE ENCUENTRAN DENTRO DEL PERIODO DEL CONTRATO\"";
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								llenarBitacora(false, l.getIdCriterio());
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}
						}						
					}else if(l.getIdCriterio() == 15){// //PRECIO PAGADO AL PRODUCTOR DEBERÁ SER IGUAL AL TIPO DE CAMBIO DE LA  FECHA  DE LA FACTURA						
						sheet = wb.createSheet("PRECIO PAGADO POR TIPO CAMBIO");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstPrecioPagPorTipoCambio = rDAO.consultaPrecioPagPorTipoCambio(folioCartaAdhesion);
						if(lstPrecioPagPorTipoCambio.size()>0){
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}	
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("P.N.A. total de la factura (ton.) por contrato");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio pactado por tonelada (dlls)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Facturado Por Contrato");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe  Calculado para Contrato");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Diferencia a Pagar");
							for(PrecioPagPorTipoCambio c: lstPrecioPagPorTipoCambio){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje( c.getClaveBodega()+";"+c.getNombreEstado()+";"+(siAplicaFolioContrato ? c.getFolioContrato()+";":"")
										+c.getPaternoProductor()+";"+c.getMaternoProductor()+";"+c.getNombreProductor()+";"
										+(c.getVolTotalFacVenta()!=null?c.getVolTotalFacVenta()+";":";0")
										+(c.getPrecioPactadoPorTonelada()!=null?c.getPrecioPactadoPorTonelada()+";":";0")
										+(c.getImpSolFacVenta()!=null?c.getImpSolFacVenta()+";":";0")
										+(c.getImporteContrato()!=null?c.getImporteContrato()+";":";0")
										+(c.getDiferenciaImporte()!=null?c.getDiferenciaImporte()+";":";0"));
								b.getBitacoraRelcomprasDetalle().add(bd);								
								//Actualiza el productor en campo factura_inconsistente como inconsistente
								rDAO.actualizaFacMayBolOPagMenFac(folioCartaAdhesion, c.getClaveBodega(), c.getNombreEstado(), c.getFolioContrato(),
										c.getPaternoProductor(), c.getMaternoProductor(), c.getNombreProductor(), false, false, false, true);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(c.getClaveBodega()!=null ? c.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getNombreEstado()!=null ? c.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getFolioContrato()!=null ? c.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((c.getPaternoProductor()!=null && !c.getPaternoProductor().isEmpty() ? c.getPaternoProductor()+" " :"" )
										+(c.getMaternoProductor()!=null ? c.getMaternoProductor()+" " :"") 
										+(c.getNombreProductor()!=null ? c.getNombreProductor() :""));								
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getVolTotalFacVenta()!=null?c.getVolTotalFacVenta()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getPrecioPactadoPorTonelada()!=null?c.getPrecioPactadoPorTonelada()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getImpSolFacVenta()!=null?c.getImpSolFacVenta()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getImporteContrato()!=null?c.getImporteContrato()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getDiferenciaImporte()!=null?c.getDiferenciaImporte()+"":"");								
								
							}							
							b.setMensaje(msj);							
							b = (BitacoraRelcompras) cDAO.guardaObjeto(b);		
						}else{ 
							msj = "La validación es correcta \"EL PRECIO PAGADO AL PRODUCTOR ES IGUAL AL TIPO DE CAMBIO DE LA  FECHA  DE LA FACTURA\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
						
						
					}else if(l.getIdCriterio() == 23){// "VALORES REQUERIDOS EN CAMPOS DE FACTURAS"
						sheet = wb.createSheet("FACTURAS VAL NULOS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						recuperaCamposPorGrupo(9);
						camposQueAplica = integrarCamposAplica(lstGruposCamposDetalleRelacionV);	
						lstFacturasCamposRequeridos = rDAO.consultaFacturasInfoRequerida(folioCartaAdhesion, camposQueAplica);
						if(lstFacturasCamposRequeridos.size()>0){
					    	llenarBitacora(true, l.getIdCriterio());
					    	msj = l.getCriterio();
					    	int countColumn =0;
					    	row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("CLAVE BODEGA");
							cell = row.createCell(++countColumn);
							cell.setCellValue("ESTADO");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("PRODUCTOR");
							cell = row.createCell(++countColumn);
							cell.setCellValue("FACTURA");
							if(camposQueAplica.contains("16,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("FECHA EMISIÓN");
							}
							if(camposQueAplica.contains("17,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("RFC FACTURA");
							}
							if(camposQueAplica.contains("19,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("P.N.A. SOLICITADO PARA APOYO (TON.)");
							}
							if(camposQueAplica.contains("65,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("P.N.A. TOTAL DE LA FACTURA (TON.)");
							}	
							if(camposQueAplica.contains("20,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("IMPORTE TOTAL FACTURADO ($)");
							}	
							if(camposQueAplica.contains("70,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("VARIEDAD");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("P.N.A. TOTAL DE LA FACTURA (TON.)");						
							for(FacturasCamposRequeridos bo: lstFacturasCamposRequeridos){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(bo.getClaveBodega()+";"+bo.getNombreEstado()+";"+(siAplicaFolioContrato?bo.getFolioContrato()+";":"")
										+bo.getPaternoProductor()+";"+bo.getMaternoProductor()+";"+bo.getNombreProductor()+";"+bo.getFolioFacturaVenta()+";"
										+(camposQueAplica.contains("16,")?bo.getFechaEmisionFac()+";":"")+(camposQueAplica.contains("17,")?bo.getRfcFacVenta()+";":"")
										+(camposQueAplica.contains("19,")?bo.getVolSolFacVenta()+";":"")+(camposQueAplica.contains("65,")?bo.getVolTotalFacVenta()+";":"")
										+(camposQueAplica.contains("20,")?bo.getImpSolFacVenta()+";":"")+(camposQueAplica.contains("70,")?bo.getVariedad()+";":"")
										+(bo.getVolTotalFacVentaD()!=null?bo.getVolTotalFacVentaD():"0" ));
								b.getBitacoraRelcomprasDetalle().add(bd);
								actualizarRelacionComprasTMPByFacturasIncosistentes(folioCartaAdhesion, bo.getClaveBodega(),bo.getNombreEstado(),bo.getFolioContrato(), 
										bo.getPaternoProductor(), bo.getMaternoProductor(), bo.getNombreProductor(), bo.getFolioFacturaVenta());
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bo.getClaveBodega()!=null ? bo.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bo.getNombreEstado()!=null ? bo.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getFolioContrato()!=null ? bo.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((bo.getPaternoProductor() != null ? bo.getPaternoProductor()+" ": "")
										+ (bo.getMaternoProductor() != null ? bo.getMaternoProductor()+" ":"")
										+ (bo.getNombreProductor() != null ? bo.getNombreProductor():""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bo.getFolioFacturaVenta()!=null ? bo.getFolioFacturaVenta()+"":"");								
								if(camposQueAplica.contains("16,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getFechaEmisionFac());
								}
								
								if(camposQueAplica.contains("17,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getRfcFacVenta());
								}
								if(camposQueAplica.contains("19,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getVolSolFacVenta());
								}
								if(camposQueAplica.contains("65,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getVolTotalFacVenta());
								}
								if(camposQueAplica.contains("20,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getImpSolFacVenta());
								}
								if(camposQueAplica.contains("70,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getVariedad());
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((bo.getVolTotalFacVentaD()!=null?bo.getVolTotalFacVentaD():"")+"");								
							}
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
					    	
					    }else{
							msj = "La validación es correcta \"NO SE ENCONTRARON VALORES NULOS EN LOS CAMPOS REQUERIDOS DE LAS FACTURAS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}
				}//end recorrido lstValidacionPorGrupo FACTURAS
				nombreArchivoLogXls = "Facturas.xls";
				nombreArchivoLogCargaXls = "LogCargaArchivo.xls"; //Log del archivo excel  // AHS 29012015
				FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogXls));
			    wb.write(out);
			    out.close();
			}else if(grupoCriterio.equals("Pagos")){
				lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);
				String s = integraCriteriosByGrupo(lstValidacionPorGrupo);
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s, "0,1", true);
				if(lstBitacoraRelCompras.size()>0){
					addActionError("Los criterio para el grupo '"+grupoCriterio+"' ya se encuentra validado y se genero bitacora definitiva, por favor verifique ");
					return SUCCESS;
				}						
				lstBitacoraRelCompras = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, s, "0,1");
				if(lstBitacoraRelCompras.size() > 0){
					//Actualiza campo pago_incosistente en la tabla relacion_compras_tmp
					rDAO.actualizaBolFactPagEnRelacionComprasTMP(folioCartaAdhesion, false, false, true);
					//Actualiza campo pagos_menores_facturas en la tabla relacion_compras_tmp
					rDAO.actualizaCamposIconsistentes(folioCartaAdhesion, false, true, false, false);
					//Borra el registro de la bitacora
					for(BitacoraRelcompras b: lstBitacoraRelCompras){
						cDAO.borrarObjeto(b);
					}					
				}
				for(CatCriteriosValidacion l:lstValidacionPorGrupo){
					if(l.getIdCriterio() == 16){//"NO SE REPITAN CHEQUES-BANCO POR EMPRESA"
						sheet = wb.createSheet("CHEQUES DUPLICADOS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstChequesDuplicadoBancoPartipante = rDAO.verifiChequeDuplicadosPorEmpresaBanco(folioCartaAdhesion);
						if(lstChequesDuplicadoBancoPartipante.size()>0){//En el listado se guarda los cheque duplicados por banco empresa
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}	
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Folio Pago");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Banco");
							cell = row.createCell(++countColumn);
							cell.setCellValue("P.N.A. TOTAL DE LA FACTURA (TON.)");
							for(ChequesDuplicadoBancoPartipante c: lstChequesDuplicadoBancoPartipante){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje( c.getClaveBodega()+";"+c.getNombreEstado()+";"+(siAplicaFolioContrato ? c.getFolioContrato()+";":"")
										+c.getPaternoProductor()+";"+c.getMaternoProductor()+";"+c.getNombreProductor()+";"+c.getFolioDocPago() + ";"
										+ c.getBancoSinaxc()+";"+(c.getVolTotalFacVenta()!=null?c.getVolTotalFacVenta():"-1"));
								b.getBitacoraRelcomprasDetalle().add(bd);								
								actualizarRelacionComprasTMPByPagosDuplicados(folioCartaAdhesion, c.getClaveBodega(), c.getNombreEstado(), c.getFolioContrato(), 
										c.getPaternoProductor(), c.getMaternoProductor(), c.getNombreProductor(), c.getFolioDocPago(), c.getBancoSinaxc());								
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(c.getClaveBodega()!=null ? c.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getNombreEstado()!=null ? c.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getFolioContrato()!=null ? c.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((c.getPaternoProductor()!=null && !c.getPaternoProductor().isEmpty() ? c.getPaternoProductor()+" " :"" )
										+(c.getMaternoProductor()!=null ? c.getMaternoProductor()+" " :"") 
										+(c.getNombreProductor()!=null ? c.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getFolioDocPago()!=null ? c.getFolioDocPago()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getBancoSinaxc()!=null ? c.getBancoSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(c.getVolTotalFacVenta()!=null?c.getVolTotalFacVenta()+"":"");
							}							
							b.setMensaje(msj);							
							b = (BitacoraRelcompras) cDAO.guardaObjeto(b);		
						}else{ 
							msj = "La validación es correcta \"LOS CHEQUES NO PRESENTAN DUPLICIDAD\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}					
					}else if(l.getIdCriterio()== 17){//"PAGOS FUERA DEL PERIODO"
						sheet = wb.createSheet("PAGOS FUERA PER");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;		
						if(pideTipoPeriodo == 1 || pideTipoPeriodo == 3){//PERIODO POR LINEAMIENTO (1) : DICTAMEN DE AUDITOR (3)
							if(aplicaComplemento){
								fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicioAuditor).toString();
								fechaInicioComS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();
								fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFinAuditor).toString();
								fechaFinComS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
								lstChequeFueraPeriodoAuditor = rDAO.verificaChequeFueraDePeriodoAudLinComplemento(folioCartaAdhesion, fechaInicioS, fechaFinS, fechaInicioComS, fechaFinComS);
							}else{
								fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicioAuditor).toString();
								fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinAuditor).toString();
								lstChequeFueraPeriodoAuditor = rDAO.verificaChequeFueraDePeriodo(folioCartaAdhesion, fechaInicioS, fechaFinS);
							}
							
							  
							if(lstChequeFueraPeriodoAuditor.size()>0){//En el listado se guarda las facturas menores a lo pagado
								llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
								if(aplicaComplemento){
									msj = l.getCriterio()+" " +new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioAuditor).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFinAuditor).toString()
										+	" y "+new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFin).toString();
								}else{
									msj = l.getCriterio()+new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioAuditor).toString()+ " - "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaFinAuditor).toString();	
								}
								
								row = sheet.createRow(countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(msj);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue("Clave Bodega");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Estado");
								//Si aplica "FOLIO CONTRATO" en la configuracion del esquema 
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue("Folio Contrato");
								}	
								cell = row.createCell(++countColumn);
								cell.setCellValue("Productor");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Documento");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Banco");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Fecha de Cheque");
								cell = row.createCell(++countColumn);
								cell.setCellValue("P.N.A. Total de la Factura (ton)");
								cell = row.createCell(++countColumn);
								cell.setCellValue("Poliza Cheque");							
								
								for(ChequeFueraPeriodoAuditor c: lstChequeFueraPeriodoAuditor){
									countColumn = 0;
									bd = new BitacoraRelcomprasDetalle();
									bd.setMensaje(c.getClaveBodega() + ";"+c.getNombreEstado()+";"+(siAplicaFolioContrato?c.getFolioContrato()+";":"")
											+ c.getPaternoProductor() + ";"+ c.getMaternoProductor() + ";"+ c.getNombreProductor() + ";"
											+ c.getFolioDocPago()+";"+c.getBancoSinaxc()+";"+(c.getFechaDocPagoSinaxc()!=null?new SimpleDateFormat("yyyy-MM-dd").format(c.getFechaDocPagoSinaxc())+";":"")
											+ c.getVolTotalFacVenta()+";"+(c.getImpTotalPagoSinaxc()!=null?c.getImpTotalPagoSinaxc():"-"));
									b.getBitacoraRelcomprasDetalle().add(bd);									
									actualizarRelacionComprasTMPByPagosIncosistentes(folioCartaAdhesion, c.getClaveBodega(), c.getNombreEstado(), c.getFolioContrato(), 
											c.getPaternoProductor(), c.getMaternoProductor(), c.getNombreProductor(), c.getFolioDocPago());									
									row = sheet.createRow(++countRow);
									cell = row.createCell(countColumn);
									cell.setCellValue(c.getClaveBodega()!=null ? c.getClaveBodega()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getNombreEstado()!=null ? c.getNombreEstado()+"":"");
									if(siAplicaFolioContrato){
										cell = row.createCell(++countColumn);
										cell.setCellValue(c.getFolioContrato()!=null ? c.getFolioContrato()+"":"");
									}
									cell = row.createCell(++countColumn);
									cell.setCellValue((c.getPaternoProductor()!=null && !c.getPaternoProductor().isEmpty() ? c.getPaternoProductor()+" " :"" )
											+(c.getMaternoProductor()!=null ?c.getMaternoProductor()+" " :"") 
											+(c.getNombreProductor()!=null ?c.getNombreProductor() :""));
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getFolioDocPago()!=null ? c.getFolioDocPago()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getBancoSinaxc()!=null ? c.getBancoSinaxc()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getFechaDocPagoSinaxc()!=null ? c.getFechaDocPagoSinaxc()+"":"");		
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getVolTotalFacVenta()!=null?c.getVolTotalFacVenta()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(c.getImpTotalPagoSinaxc()!=null?c.getImpTotalPagoSinaxc()+"":"");
								}							
								
								b.setMensaje(msj);
								
								cDAO.guardaObjeto(b);
							}else{ 
								msj = "La validación es correcta \"LOS PAGOS SE ENCUENTRAN DENTRO DEL PERIODO DEL AUDITOR\"";
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								llenarBitacora(false, l.getIdCriterio());
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}
						}else{//Periodo por contrato
							if(aplicaAdendum){
//								if(aplicaAdendum ){
									//Actualizar periodo en la tabla de relacion de compras
								lstContratos = rDAO.consultaPeriodosContratosRelCompras(folioCartaAdhesion);
								//lstRCTemp =	rDAO.consultaSoloContratosRelacionCompras(folioCartaAdhesion);								
								for(ContratosRelacionCompras r: lstContratos){
									//Preguntar si el folio del contrato se capturo la fecha del adendum									
									if (fechaInicioAdendumContrato.get(r.getFolioContrato())!=null && fechaFinAdendumContrato.get(r.getFolioContrato())!=null){
										//Actualiza fecha del adendum que se capturo
										rDAO.actualizaFechaAdendumContratoEnRelCompras(r.getFolioContrato(), folioCartaAdhesion, fechaInicioAdendumContrato.get(r.getFolioContrato()), fechaFinAdendumContrato.get(r.getFolioContrato()));
										r.setPeriodoInicialPagoAdendum(fechaInicioAdendumContrato.get(r.getFolioContrato()));
										r.setPeriodoFinalPagoAdendum(fechaFinAdendumContrato.get(r.getFolioContrato()));										
										cDAO.guardaObjeto(r);									
									}else{
										rDAO.actualizaFechaAdendumContratoEnRelCompras(r.getFolioContrato(), folioCartaAdhesion, r.getPeriodoInicialPago(), r.getPeriodoFinalPago());
										r.setPeriodoInicialPagoAdendum(null);
										r.setPeriodoFinalPagoAdendum(null);										
										cDAO.guardaObjeto(r);	
									}									
								}
								if(aplicaComplemento){
									fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();
									fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
									lstChequeFueraPeriodoContrato = rDAO.verificaChequeFueraDePeriodoContratoAdendumComplemento(folioCartaAdhesion, fechaInicioS, fechaFinS);
								} else {
									lstChequeFueraPeriodoContrato = rDAO.verificaChequeFueraDePeriodoPagoAdendum(folioCartaAdhesion);
								}
							}else{
								if(aplicaComplemento){
									fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();
									fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
									lstChequeFueraPeriodoContrato = rDAO.verificaChequeFueraDePeriodoContratoComplemento(folioCartaAdhesion, fechaInicioS, fechaFinS);
								} else {
									lstChequeFueraPeriodoContrato = rDAO.verificaChequeFueraDePeriodoContrato(folioCartaAdhesion);
								}
								 
							}				
								
							if(lstChequeFueraPeriodoContrato.size()>0){//En el listado se guardan las boletas que estan fuera del periodo seleccionado
								llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
								msj = l.getCriterio();
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								row = sheet.createRow(++countRow);
								cell = row.createCell(0);
								cell.setCellValue("Clave Bodega");
								cell = row.createCell(1);
								cell.setCellValue("Estado");
								cell = row.createCell(2);
								cell.setCellValue("Folio Contrato");
								cell = row.createCell(3);
								cell.setCellValue("Productor");
								cell = row.createCell(4);
								cell.setCellValue("Fecha Inicial");
								cell = row.createCell(5);
								cell.setCellValue("Fecha Final");
								cell = row.createCell(6);
								cell.setCellValue("Folio Documento");
								cell = row.createCell(7);
								cell.setCellValue("Banco");
								cell = row.createCell(8);
								cell.setCellValue("Fecha de Cheque");
								cell = row.createCell(9);
								cell.setCellValue("P.N.A. Total de la Factura (ton)");
								cell = row.createCell(10);
								cell.setCellValue("Poliza Cheque");
								for(ChequeFueraPeriodoContrato bfp: lstChequeFueraPeriodoContrato){
									countColumn = 0;
									bd = new BitacoraRelcomprasDetalle();
									bd.setMensaje(bfp.getClaveBodega()+";"+bfp.getNombreEstado()+";"+bfp.getFolioContrato()+";"
												+bfp.getPaternoProductor()+";"+bfp.getMaternoProductor()+";"+bfp.getNombreProductor()+";"
												+(bfp.getPeriodoInicialPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoInicialPago()).toString():"")+";"
												+(bfp.getPeriodoFinalPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoFinalPago()).toString():"")+";"
												+bfp.getFolioDocPago()+";"+bfp.getBancoSinaxc()+";"
												+(bfp.getFechaDocPagoSinaxc()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getFechaDocPagoSinaxc()).toString():"")+";"
												+bfp.getVolTotalFacVenta()+";"+bfp.getImpTotalPagoSinaxc());
									b.getBitacoraRelcomprasDetalle().add(bd);
									actualizarRelacionComprasTMPByPagosIncosistentes(folioCartaAdhesion, bfp.getClaveBodega(), bfp.getNombreEstado(), bfp.getFolioContrato(), 
											bfp.getPaternoProductor(), bfp.getMaternoProductor(), bfp.getNombreProductor(), bfp.getFolioDocPago());		
									row = sheet.createRow(++countRow);
									cell = row.createCell(countColumn);
									cell.setCellValue(bfp.getClaveBodega()!=null ? bfp.getClaveBodega()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getNombreEstado()!=null ? bfp.getNombreEstado()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getFolioContrato()!=null ? bfp.getFolioContrato()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue((bfp.getPaternoProductor()!=null && !bfp.getPaternoProductor().isEmpty() ? bfp.getPaternoProductor()+" " :"" )
											+(bfp.getMaternoProductor()!=null ?bfp.getMaternoProductor()+" " :"") 
											+(bfp.getNombreProductor()!=null ?bfp.getNombreProductor() :""));
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getPeriodoInicialPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoInicialPago()).toString()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getPeriodoFinalPago()!=null?new SimpleDateFormat("yyyy-MM-dd").format(bfp.getPeriodoFinalPago()).toString()+"":"");									
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getFolioDocPago()!=null && !bfp.getFolioDocPago().isEmpty()?bfp.getFolioDocPago():"");									
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getBancoSinaxc()!=null?bfp.getBancoSinaxc()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getFechaDocPagoSinaxc()!=null ? bfp.getFechaDocPagoSinaxc()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getVolTotalFacVenta()!=null?bfp.getVolTotalFacVenta()+"":"");
									cell = row.createCell(++countColumn);
									cell.setCellValue(bfp.getImpTotalPagoSinaxc()!=null?bfp.getImpTotalPagoSinaxc()+"":"");
								}						
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}else{
								msj = "La validación es correcta \"PAGOS SE ENCUENTRAN DENTRO DEL PERIODO DEL CONTRATO\"";
								row = sheet.createRow(countRow);
								cell = row.createCell(0);
								cell.setCellValue(msj);
								llenarBitacora(false, l.getIdCriterio());
								b.setMensaje(msj);
								cDAO.guardaObjeto(b);
							}
						}						
						
					}else if(l.getIdCriterio() == 18){//"IMPORTE FACTURADO MAYOR AL IMPORTE PAGADO."
						sheet = wb.createSheet("MONTOS PAG MENORES FACTURAS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;	
						lstFacturasVsPago = rDAO.verificaFacturasVsPago(folioCartaAdhesion); 
						int i = 0;
						if(lstFacturasVsPago.size()>0){//En el listado se guarda las facturas menores a lo pagado
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}	
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Factura");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Pagos");
							cell = row.createCell(++countColumn);
							cell.setCellValue("P.N.A. Total de la Factura (ton)");
							for(FacturasVsPago f: lstFacturasVsPago){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(f.getClaveBodega()+";"+f.getNombreEstado()+";"+(siAplicaFolioContrato?f.getFolioContrato()+";":"")
										+f.getPaternoProductor()+";"+f.getMaternoProductor()+";"+f.getNombreProductor()+";"
										+f.getImpSolFacVenta()+";"+(f.getImpTotalPagoSinaxc()!=null?f.getImpTotalPagoSinaxc()+";":0+";")+
										+(f.getVolTotalFacVenta()!=null?f.getVolTotalFacVenta():0));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza el productor como inconsistente
								rDAO.actualizaFacMayBolOPagMenFac(folioCartaAdhesion, f.getClaveBodega(), f.getNombreEstado(), f.getFolioContrato(),
										f.getPaternoProductor(), f.getMaternoProductor(), f.getNombreProductor(), false, true);
								System.out.println("i pago actualizada "+i++);
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(f.getClaveBodega()!=null ? f.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getNombreEstado()!=null ? f.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getFolioContrato()!=null ? f.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((f.getPaternoProductor()!=null && !f.getPaternoProductor().isEmpty() ? f.getPaternoProductor()+" " :"" )
										+(f.getMaternoProductor()!=null ? f.getMaternoProductor()+" " :"") 
										+(f.getNombreProductor()!=null ? f.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpSolFacVenta());
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpTotalPagoSinaxc()!=null?f.getImpTotalPagoSinaxc():0);
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getVolTotalFacVenta()!=null?f.getVolTotalFacVenta():0);
							}
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{ 
							msj = "La validación es correcta \"LOS MONTOS PAGADOS SON MAYORES A LAS FACTURAS EMITIDAS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}						
					}else if(l.getIdCriterio() == 19){//"11.1 PRECIO PAGADO NO CORRESPONDE CON PAGOS"
						sheet = wb.createSheet("PRECIO PAGADO CORRES PAGOS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstPrecioPagadoNoCorrespondeConPagosV = rDAO.consultaPrecioPagadoNoCorrespondeConPagosV(folioCartaAdhesion); 
						if(lstPrecioPagadoNoCorrespondeConPagosV.size() > 0){//"11.1 PRECIO PAGADO NO CORRESPONDE CON PAGOS" 
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Fac (ton)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Cheque");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Total");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio en Rel de Compras");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio Calculado");														
							for(PrecioPagadoNoCorrespondeConPagosV f: lstPrecioPagadoNoCorrespondeConPagosV){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(f.getClaveBodega()+";"+f.getNombreEstado()+";"+(siAplicaFolioContrato?f.getFolioContrato()+";":"")+
										f.getPaternoProductor()+";"+f.getMaternoProductor()+";"+f.getNombreProductor()+";"+
										f.getVolTotalFacVenta()+";"+f.getImpDocPagoSinaxc()+";"+f.getImpTotalPagoSinaxc()+";"+
										f.getImpPrecioTonPagiSinaxc()+";"+(f.getPrecioCalculado()!=null ? f.getPrecioCalculado():0));
								b.getBitacoraRelcomprasDetalle().add(bd);								
								//Actualiza los pagos del productor como inconsistente
								rDAO.actualizaPagosInconsistente(folioCartaAdhesion, f.getClaveBodega(), f.getNombreEstado(), f.getFolioContrato(),
										f.getPaternoProductor(), f.getMaternoProductor(), f.getNombreProductor(), true);
							 	row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(f.getClaveBodega()!=null ? f.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getNombreEstado()!=null ? f.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getFolioContrato()!=null ? f.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((f.getPaternoProductor()!=null && !f.getPaternoProductor().isEmpty() ? f.getPaternoProductor()+" " :"" )
										+(f.getMaternoProductor()!=null ? f.getMaternoProductor()+" " :"") 
										+(f.getNombreProductor()!=null ? f.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getVolTotalFacVenta()!=null?f.getVolTotalFacVenta()+"":"");								
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpDocPagoSinaxc()!=null?f.getImpDocPagoSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpTotalPagoSinaxc()!=null?f.getImpTotalPagoSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpPrecioTonPagiSinaxc()!=null?f.getImpPrecioTonPagiSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getPrecioCalculado()!=null?f.getPrecioCalculado()+"":"");
							}							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{ 
							msj = "La validación es correcta \"PRECIO PAGADO CORRESPONDE CON PAGOS\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}else if(l.getIdCriterio() == 24){// "VALORES REQUERIDOS EN CAMPOS DE PAGOS"
						sheet = wb.createSheet("PAGOS VAL NULOS");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						recuperaCamposPorGrupo(12);
						camposQueAplica = integrarCamposAplica(lstGruposCamposDetalleRelacionV);	
						lstPagosCamposRequeridos = rDAO.consultaPagosInfoRequerida(folioCartaAdhesion, camposQueAplica);
						if(lstPagosCamposRequeridos.size()>0){
					    	llenarBitacora(true, l.getIdCriterio());
					    	msj = l.getCriterio();
					    	int countColumn =0;
					    	row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("CLAVE BODEGA");
							cell = row.createCell(++countColumn);
							cell.setCellValue("ESTADO");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("FOLIO CONTRATO");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("PRODUCTOR");
							cell = row.createCell(++countColumn);
							cell.setCellValue("FOLIO DOCUMENTO");
							if(camposQueAplica.contains("67,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("IMPORTE TOTAL PAGADO");
							}
							if(camposQueAplica.contains("27,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("TIPO DE DOCUMENTO DE PAGO (Póliza de cheque, Cheque de pago, Recibos de liquidación al productor o Pago electrónico)");
							}
							if(camposQueAplica.contains("16,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("FECHA");
							}
							if(camposQueAplica.contains("29,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("BANCO");
							}	
							if(camposQueAplica.contains("66,")){
								cell = row.createCell(++countColumn);
								cell.setCellValue("IMPORTE DE DOCUMENTO DE PAGO");
							}
							if(camposQueAplica.contains("68,")){//"PRECIO PAGADO POR TONELADA ($)"
								cell = row.createCell(++countColumn);
								cell.setCellValue("PRECIO PAGADO POR TONELADA");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("IMPORTE TOTAL PAGADO ($)");
							cell = row.createCell(++countColumn);	// AHS [LINEA] - 10022015
							cell.setCellValue("P.N.A. TOTAL DE LA FACTURA (TON.)");		// AHS [LINEA] - 10022015
							for(PagosCamposRequeridos bo: lstPagosCamposRequeridos){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(bo.getClaveBodega()+";"+bo.getNombreEstado()+";"+(siAplicaFolioContrato?bo.getFolioContrato()+";":"")
										+bo.getPaternoProductor()+";"+bo.getMaternoProductor()+";"+bo.getNombreProductor()+";"+bo.getFolioDocPago()+";"
										+(camposQueAplica.contains("67,")?bo.getImpTotalPagoSinaxc()+";":"")+(camposQueAplica.contains("27,")?bo.getTipoDdocPago()+";":"")
										+(camposQueAplica.contains("16,")?bo.getFechaDocPagoSinaxc()+";":"")+(camposQueAplica.contains("29,")?bo.getBancoSinaxc()+";":"")
										+(camposQueAplica.contains("66,")?bo.getImpDocPagoSinaxc()+";":"")+(camposQueAplica.contains("68,")?bo.getImpDocPagoSinaxc()+";":";")+bo.getImpTotalPagoSinaxcD());								
								b.getBitacoraRelcomprasDetalle().add(bd);	
								actualizarRelacionComprasTMPByPagosIncosistentes(folioCartaAdhesion, bo.getClaveBodega(),bo.getNombreEstado(),bo.getFolioContrato(), 
										bo.getPaternoProductor(), bo.getMaternoProductor(), bo.getNombreProductor(), bo.getFolioDocPago());
								row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(bo.getClaveBodega()!=null ? bo.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(bo.getNombreEstado()!=null ? bo.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getFolioContrato()!=null ? bo.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((bo.getPaternoProductor() != null ? bo.getPaternoProductor()+" ": "")
										+ (bo.getMaternoProductor() != null ? bo.getMaternoProductor()+" ":"")
										+ (bo.getNombreProductor() != null ? bo.getNombreProductor():""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(bo.getFolioDocPago()!=null ? bo.getFolioDocPago()+"":"");																
								if(camposQueAplica.contains("67,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getImpTotalPagoSinaxc());
								}
								
								if(camposQueAplica.contains("27,")){
									
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getTipoDdocPago());
								}
								if(camposQueAplica.contains("16,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getFechaDocPagoSinaxc());
								}
								if(camposQueAplica.contains("29,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getBancoSinaxc());
								}
								if(camposQueAplica.contains("66,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getImpDocPagoSinaxc());
								}
								if(camposQueAplica.contains("68,")){
									cell = row.createCell(++countColumn);
									cell.setCellValue(bo.getImpPrecioTonPagoSinaxc());
								}
								
								cell = row.createCell(++countColumn);
								cell.setCellValue((bo.getImpTotalPagoSinaxcD()!=null?bo.getImpTotalPagoSinaxcD():"")+"");	
								
								cell = row.createCell(++countColumn);	// AHS [LINEA] - 10022015
								cell.setCellValue((bo.getVolTotalFacVentaD()!=null?bo.getVolTotalFacVentaD():"")+"");	// AHS [LINEA] - 10022015	
							}
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
					    	
					    }else{
							msj = "La validación es correcta \"NO SE ENCONTRARON VALORES NULOS EN LOS CAMPOS REQUERIDOS DE LOS PAGOS\"";
							//crearCeldaenLogXls();
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);	
							llenarBitacora(false, l.getIdCriterio());
							cDAO.guardaObjeto(b);
						}
						
					}else if(l.getIdCriterio() == 28){//"11.2 PRECIO MENOR AL ESTABLECIDO EN AVISO"
						sheet = wb.createSheet("PRECIO PAG MEN AL EST EN AVISO");
						sheet = setMargenSheet(sheet);
						countRow = 0;
						countColumn = 0;
						lstPrecioPagadoMenorQueAviso = rDAO.consultaPrecioMenorQAviso(folioCartaAdhesion); 
						if(lstPrecioPagadoMenorQueAviso.size() > 0){//"11.2 PRECIO MENOR AL ESTABLECIDO EN AVISO" 
							llenarBitacora(true, l.getIdCriterio()); //Guardar en bitacora
							msj = l.getCriterio();
							row = sheet.createRow(countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue(msj);
							row = sheet.createRow(++countRow);
							cell = row.createCell(countColumn);
							cell.setCellValue("Clave Bodega");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Estado");
							if(siAplicaFolioContrato){
								cell = row.createCell(++countColumn);
								cell.setCellValue("Folio Contrato");
							}
							cell = row.createCell(++countColumn);
							cell.setCellValue("Productor");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Peso Neto Fac (ton)");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Factura");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Cheque");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Importe Total");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio en Rel de Compras");
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio Calculado");	
							cell = row.createCell(++countColumn);
							cell.setCellValue("Precio en Aviso");	
							for(PrecioPagadoNoCorrespondeConPagosV f: lstPrecioPagadoMenorQueAviso){
								countColumn = 0;
								bd = new BitacoraRelcomprasDetalle();
								bd.setMensaje(f.getClaveBodega()+";"+f.getNombreEstado()+";"+(siAplicaFolioContrato?f.getFolioContrato()+";":"")+
										f.getPaternoProductor()+";"+f.getMaternoProductor()+";"+f.getNombreProductor()+";"+
										f.getVolTotalFacVenta()+";"+f.getImpSolFacVenta()+";"+f.getImpDocPagoSinaxc()+";"+f.getImpTotalPagoSinaxc()+";"+
										f.getImpPrecioTonPagiSinaxc()+";"+(f.getPrecioCalculado()!=null ? f.getPrecioCalculado()+";":0+";")+
										(f.getPrecioPagadoEnAviso()!=null ? f.getPrecioPagadoEnAviso()+";":0+";"));
								b.getBitacoraRelcomprasDetalle().add(bd);
								//Actualiza los pagos del productor como inconsistente
								rDAO.actualizaPagosInconsistente(folioCartaAdhesion, f.getClaveBodega(), f.getNombreEstado(), f.getFolioContrato(),
										f.getPaternoProductor(), f.getMaternoProductor(), f.getNombreProductor(), true);
							 	row = sheet.createRow(++countRow);
								cell = row.createCell(countColumn);
								cell.setCellValue(f.getClaveBodega()!=null ? f.getClaveBodega()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getNombreEstado()!=null ? f.getNombreEstado()+"":"");
								if(siAplicaFolioContrato){
									cell = row.createCell(++countColumn);
									cell.setCellValue(f.getFolioContrato()!=null ? f.getFolioContrato()+"":"");
								}
								cell = row.createCell(++countColumn);
								cell.setCellValue((f.getPaternoProductor()!=null && !f.getPaternoProductor().isEmpty() ? f.getPaternoProductor()+" " :"" )
										+(f.getMaternoProductor()!=null ? f.getMaternoProductor()+" " :"") 
										+(f.getNombreProductor()!=null ? f.getNombreProductor() :""));
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getVolTotalFacVenta()!=null?f.getVolTotalFacVenta()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpSolFacVenta()!=null?f.getImpSolFacVenta()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpDocPagoSinaxc()!=null?f.getImpDocPagoSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpTotalPagoSinaxc()!=null?f.getImpTotalPagoSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getImpPrecioTonPagiSinaxc()!=null?f.getImpPrecioTonPagiSinaxc()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getPrecioCalculado()!=null?f.getPrecioCalculado()+"":"");
								cell = row.createCell(++countColumn);
								cell.setCellValue(f.getPrecioPagadoEnAviso()!=null?f.getPrecioPagadoEnAviso()+"":"");
							}							
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}else{ 
							msj = "La validación es correcta \"PRECIO PAGADO ES MENOR AL ESTABLECIDO EN AVISO\"";
							row = sheet.createRow(countRow);
							cell = row.createCell(0);
							cell.setCellValue(msj);
							llenarBitacora(false, l.getIdCriterio());
							b.setMensaje(msj);
							cDAO.guardaObjeto(b);
						}
					}			
				}//end recorrido lstValidacionPorGrupo PAGOS
				nombreArchivoLogXls = "Pagos.xls";
				nombreArchivoLogCargaXls = "LogCargaArchivo.xls"; //Log del archivo excel  // AHS 29012015
				FileOutputStream out = new FileOutputStream(new File(rutaCartaAdhesion+nombreArchivoLogXls));
			    wb.write(out);
			    out.close();
			}// end grupo pagos
																
			
			
			
		}catch (JDBCException e){
			e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en prevalidarRelacionCompras debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}catch (Exception e){
			e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en prevalidarRelacionCompras debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			capturaCargaArchivoRelCompras();
			tipoAccion = 2;
		}
		return SUCCESS;
	}
		
	private void actualizarRelacionComprasTMPByBoletasIncosistentes(String folioCartaAdhesion, String claveBodega, 
										String nombreEstado, String folioContrato, 
										String paternoProductor, String maternoProductor, 
										String nombreProductor, String boletaTicketBascula) {

		lstRCTemp = rDAO.consultaRelacionComprasTMP(folioCartaAdhesion, claveBodega, nombreEstado, folioContrato,
				paternoProductor, maternoProductor, nombreProductor,boletaTicketBascula );
		
		for(RelacionComprasTMP r: lstRCTemp){
			if(r.getBoletaIncosistente()!=null){
				if(r.getBoletaIncosistente()){
					relacionComprasTMP = lstRCTemp.get(0);
					bandera = true;
					break;
				}
			}
		}
		if(!bandera){
			relacionComprasTMP = lstRCTemp.get(0);
			//Actualizar el registro a boletas incosistentes = true
			relacionComprasTMP.setBoletaIncosistente(true);
			cDAO.guardaObjeto(relacionComprasTMP);
		}	
		
		bandera = false;
	}
	
	private void actualizarRelacionComprasTMPByFacturasIncosistentes(String folioCartaAdhesion, String claveBodega, 
									String nombreEstado, String folioContrato, 
									String paternoProductor, String maternoProductor, 
									String nombreProductor, String folioFacturaVenta) {
		lstRCTemp = rDAO.consultaRelacionComprasTMP(folioCartaAdhesion, claveBodega, nombreEstado, folioContrato,
				paternoProductor, maternoProductor, nombreProductor,null, folioFacturaVenta, null, null);
		
		for(RelacionComprasTMP r: lstRCTemp){
			if(r.getFacturaInconsistente()!=null){
				if(r.getFacturaInconsistente()){
					relacionComprasTMP = lstRCTemp.get(0);
					bandera = true;
					break;
				}
			}
		}
		if(!bandera){
			relacionComprasTMP = lstRCTemp.get(0);
			//Actualizar el registro a factura_inconsistente = true
			relacionComprasTMP.setFacturaInconsistente(true);
			cDAO.guardaObjeto(relacionComprasTMP);
		}	
		
		bandera = false;
	}
	
	private void actualizarRelacionComprasTMPByPagosIncosistentes(String folioCartaAdhesion, String claveBodega, 
			String nombreEstado, String folioContrato, 
			String paternoProductor, String maternoProductor, 
			String nombreProductor, String folioDocumento) {
		lstRCTemp = rDAO.consultaRelacionComprasTMP(folioCartaAdhesion, claveBodega, nombreEstado, folioContrato,paternoProductor, maternoProductor, nombreProductor,null, null,folioDocumento, null);	
		for(RelacionComprasTMP r: lstRCTemp){
			if(r.getPagoInconsistente()!=null){
				if(r.getPagoInconsistente()){
					relacionComprasTMP = lstRCTemp.get(0);
					bandera = true;
					break;
				}
			}
		}
		if(!bandera){
			relacionComprasTMP = lstRCTemp.get(0);
			//Actualizar el registro a pago_inconsistente = true
			relacionComprasTMP.setPagoInconsistente(true);
			cDAO.guardaObjeto(relacionComprasTMP);
		}	
		bandera = false;	
	}//end actualizarRelacionComprasTMPByPagosIncosistentes

	private void actualizarRelacionComprasTMPByPagosDuplicados(String folioCartaAdhesion, String claveBodega, 
			String nombreEstado, String folioContrato, 
			String paternoProductor, String maternoProductor, 
			String nombreProductor, String folioDocumento, String banco) {
		lstRCTemp = rDAO.consultaRelacionComprasTMP(folioCartaAdhesion, claveBodega, nombreEstado, folioContrato,paternoProductor, maternoProductor, nombreProductor,null, null,folioDocumento, banco);	
		for(RelacionComprasTMP r: lstRCTemp){
			if(r.getPagoInconsistente()!=null){
				if(r.getPagoInconsistente()){
					relacionComprasTMP = lstRCTemp.get(0);
					bandera = true;
					break;
				}
			}
		}
		if(!bandera){
			relacionComprasTMP = lstRCTemp.get(0);
			//Actualizar el registro a pago_inconsistente = true
			relacionComprasTMP.setPagoInconsistente(true);
			cDAO.guardaObjeto(relacionComprasTMP);
		}	
		bandera = false;	
	}//end actualizarRelacionComprasTMPByPagosIncosistentes
	
	private String integrarCamposAplica(
			List<GruposCamposRelacionV> lstGruposCamposDetalleRelacionV2) {
		StringBuilder camposQueAplica = new StringBuilder();
		for(GruposCamposRelacionV l: lstGruposCamposDetalleRelacionV){
			if(l.getOpcional() == false){
				camposQueAplica.append(l.getIdCampo()).append(",");
			}			
		}		
		return camposQueAplica.toString();		
	}

	private void llenarBitacora(boolean error, int idCriterio) {
		b = new BitacoraRelcompras();
		b.setFolioCartaAdhesion(folioCartaAdhesion);
		b.setFechaRegistro(new Date());
		b.setUsuarioCreacion((Integer) session.get("idUsuario"));
		b.setBitacoraRelcomprasDetalle(new HashSet<BitacoraRelcomprasDetalle>());
		if(error){
			b.setStatus(0);//hubo un error
		}else{
			b.setStatus(1);//ok
		}		
		b.setIdCriterio(idCriterio);
		
	}
	
	public String  generarBitacoraRelCompras() {
		try{
			int countStatus = 0, countStatusBad = 0;
			grupoCriterio = grupoCriterioG;
			cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			programa  = cDAO.consultaPrograma(cartaAdhesion.getIdPrograma()).get(0);
			comprador = cDAO.consultaComprador(cartaAdhesion.getIdComprador()).get(0);
			List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 1, null, null, null, null, programa.getIdPrograma());
			idPerRel =  lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();
			//Consigue los criterios correspondiente al grupo seleccionado
			//lstValidacionPorGrupo = rDAO.recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupoCriterio);
			//String  s = integraCriteriosByGrupo(lstValidacionPorGrupo);
			
			//Verifica que exista una prevalidacion previa del criterio seleccionado
			lstBitacoraRelComprasPorGrupo = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, null, "0,1", false);
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");					
			rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
			String rutaCarta =  getRecuperaRutaCarta();
			if(lstBitacoraRelComprasPorGrupo.size()>0){
				for(BitacoraRelcompras l: lstBitacoraRelComprasPorGrupo){
					//for(BitacoraRelcompras l: lstBitacoraRelComprasPorGrupo){
					lstBitacoraRelCompras = new ArrayList<BitacoraRelcompras>();
					if(l.getStatus()==0){						
						idCriterio = l.getIdCriterio();
						lstBitacoraRelCompras.add(l);
						l.setRutaArchivo(rutaCarta);
						
						nombreArchivo =  idCriterio+new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+".pdf";
						l.setNombreArchivo(nombreArchivo);
						rutaSalida = rutaCarta;
						if(idCriterio== 14){
							recuperaCamposPorGrupo(9);
						}else if(idCriterio == 22){
							recuperaCamposPorGrupo(7);
							camposQueAplica=integrarCamposAplica(lstGruposCamposDetalleRelacionV);
						}else if(idCriterio == 23){
							recuperaCamposPorGrupo(9);
							camposQueAplica=integrarCamposAplica(lstGruposCamposDetalleRelacionV);
						}else if(idCriterio == 24){
							recuperaCamposPorGrupo(12);
							camposQueAplica=integrarCamposAplica(lstGruposCamposDetalleRelacionV);
						}
						
						CruceRelComprasError pdf = new CruceRelComprasError(this, sessionTarget);
						pdf.generarCruce();
						
						cDAO.guardaObjeto(l);
						rutaSalida  = "";
						countStatusBad ++;
					}else if(l.getStatus() == 1){//todo bien
						countStatus++;
					}
				}
				
				if(countStatusBad == 0){
					cuadroSatisfactorio = "No hay nada que generar";
					lstBitacoraRelComprasPorGrupo  = null;
					return SUCCESS;
				}
				lstBitacoraRelComprasPorGrupo = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion);
				if(countStatus == lstBitacoraRelComprasPorGrupo.size()){
					cuadroSatisfactorio = "No se encontro ninguna incosistencia en el grupo seleccionado";
					return SUCCESS;
				}

				//Guardar Historicos en bitacora				
				//guardar la bitacora del ultimo archivo cargado, en caso de haber
				lstBitacoraRelCompras =	rDAO.consultaBitacoraRelcompras(folioCartaAdhesion, null, null);
				if(lstBitacoraRelCompras.size()>0){
					//Guardar en la tabla de historicos
					guardarBitacoraHCOS();
//					//borrar de la tabla bitacora_relcompras los registros de la bitacora del archivo historico
//					for(BitacoraRelcompras b: lstBitacoraRelCompras){
//						cDAO.borrarObjeto(b);
//					}

				}			
			}else{
				addActionError("No hay nada que generar");
			}
			
				
			
//			if(countStatus == lstBitacoraRelComprasPorGrupo.size()){
//				cuadroSatisfactorio = "No se encontro ninguna incosistencia en el grupo seleccionado";
//			}
			
		}catch (Exception e){
			e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en generarBitacoraRelCompras debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			capturaCargaArchivoRelCompras();
			tipoAccion = 3;
		}
		return SUCCESS;
	}
	
	private String integraCriteriosByGrupo(
			List<CatCriteriosValidacion> lstValidacionPorGrupo2) {
		
		StringBuilder s = new StringBuilder();
		for(CatCriteriosValidacion l:lstValidacionPorGrupo){
			//Recuperando los criterios del grupo
			s.append(l.getIdCriterio()).append(",");
		}	
		s.deleteCharAt(s.length()-1);
		
		return s.toString();
		
	}

	public String  cargarInformacion() {		
		session = ActionContext.getContext().getSession();
		Integer idUsuario = (Integer)session.get("idUsuario");
		try{
			//Verifica que no se haya cargado la información previamente
			List<ComprasDatosParticipante> lstComprasDatosParticipante = rDAO.consultaComprasDatosParticipantes(folioCartaAdhesion);
			if(lstComprasDatosParticipante.size()>0){
				addActionError("Ya se tiene cargada la información para la carta "+folioCartaAdhesion);
				return SUCCESS;
			}			
			
			//Guardar la informacion de la relacion de compras a sus respectivas tablas			
			//recupera la informacion de bodegas-estado
			List<RelacionComprasTMP> lstParaComprasDatosParticipante = rDAO.recuperaParaComprasDatosParticipante(folioCartaAdhesion);
			cartaAdhesion = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			lstPersonalizacionRelacionesProgramaV = rDAO.consultaPersonalizacionRelacionesProgramaV(0, 1, null, null, null, null, cartaAdhesion.getIdPrograma());
			idPerRel = lstPersonalizacionRelacionesProgramaV.get(0).getIdPerRel();
			for(RelacionComprasTMP l:lstParaComprasDatosParticipante){
				//Guarda los campos en compras_datos_participante
				ComprasDatosParticipante participante = new ComprasDatosParticipante();
				participante.setIdComprador(cartaAdhesion.getIdComprador());
				participante.setFolioCartaAdhesion(folioCartaAdhesion);
				participante.setIdPerRel(idPerRel);
				participante.setClaveBodega(l.getClaveBodega());
				participante.setEstadoAcopio(l.getEstadoAcopio());
				participante.setFechaRegistro(new Date());
				participante.setUsuarioRegistro(idUsuario);
				participante = (ComprasDatosParticipante) cDAO.guardaObjeto(participante);
				//Recupera los campos para compras_comp_productor
				List<RelacionComprasTMP> lstComprasCompProductor = rDAO.recuperaParaComprasCompProductor(folioCartaAdhesion, l.getClaveBodega(), l.getEstadoAcopio());
				
				for(RelacionComprasTMP p: lstComprasCompProductor){
					ComprasDatosProductor cP = new ComprasDatosProductor();
					cP.setIdCompPer(participante.getIdCompPer());
					cP.setFolioProductor(p.getFolioProductor());
					cP.setCurp(p.getCurpProductor());
					cP.setRfc(p.getRfcProductor());
					cP.setIdPerRel(idPerRel);
					cP.setUsuarioRegistro(idUsuario);
					cP.setFechaRegistro(new Date());
					cP.setEstatus(2);
					cP = (ComprasDatosProductor) cDAO.guardaObjeto(cP);
					//Recupera los predios del productor
					List<RelacionComprasTMP> lstComprasPredios = rDAO. recuperaParaComprasPredio(folioCartaAdhesion, l.getClaveBodega(), l.getEstadoAcopio(), p.getFolioProductor());
					for(RelacionComprasTMP predios: lstComprasPredios){
						ComprasPredio cPredio = new ComprasPredio();
						cPredio.setFolioPredio(predios.getFolioPredio());
						cPredio.setFolioPredioSec(predios.getFolioPredioSec());
						cPredio.setPredioAlterno(predios.getFolioPredioAlterno());
						cPredio.setIdCompProd(cP.getIdCompProd());
						cPredio.setFechaRegistro(new Date());						
						cPredio.setUsuarioRegistro(idUsuario);
						cDAO.guardaObjeto(cPredio);						
						
					}
					
					//recupera  las boletas del productor
					List<RelacionComprasTMP> lstComprasEntradaBodega = rDAO.recuperaParaComprasEntradaBodega(folioCartaAdhesion, l.getClaveBodega(), l.getEstadoAcopio(), p.getFolioProductor());
					for(RelacionComprasTMP boletas: lstComprasEntradaBodega){
						ComprasEntradaBodega cBoletas = new ComprasEntradaBodega();
						cBoletas.setBoletaTicketBascula(boletas.getBoletaTicketBascula());
						cBoletas.setFechaEntrada(boletas.getFechaEntradaBoleta());
						cBoletas.setVolBolTicket(boletas.getVolBolTicket());
						cBoletas.setIdCompProd(cP.getIdCompProd());
						cBoletas.setFechaRegistro(new Date());						
						cBoletas.setUsuarioRegistro(idUsuario);
						cDAO.guardaObjeto(cBoletas);
					}
					//Recupera las facturas del productor					
					List<RelacionComprasTMP> lstComprasFacVenta = rDAO.recuperaParaComprasFacVenta(folioCartaAdhesion,l.getClaveBodega(), l.getEstadoAcopio(), p.getFolioProductor());
					for(RelacionComprasTMP lF : lstComprasFacVenta ){
						ComprasFacVenta fac = new ComprasFacVenta();
						fac.setFolioFac(lF.getFolioFacturaVenta());
						fac.setFechaEmisionFac(lF.getFechaEmisionFac());
						fac.setRfcFac(lF.getRfcFacVenta());
						fac.setVolTotalFac(lF.getVolTotalFacVenta());
						fac.setVolSolFac(lF.getVolSolFacVenta());
						fac.setImpSolFac(lF.getImpSolFacVenta());
						fac.setIdCompProd(cP.getIdCompProd());
						fac.setFechaRegistro(new Date());						
						fac.setUsuarioRegistro(idUsuario);
						cDAO.guardaObjeto(fac);
					}
					//Recupera los pagos del productor
					lstComprasPagoProdSinAXC = rDAO.recuperaParaComprasPagoProdSinAXC(folioCartaAdhesion,l.getClaveBodega(), l.getEstadoAcopio(), p.getFolioProductor());
					
					for(RelacionComprasTMP lP:lstComprasPagoProdSinAXC){
						ComprasPagoProdSinAxc pS = new ComprasPagoProdSinAxc();
						pS.setIdCompProd(cP.getIdCompProd());
						pS.setFolioDocPago(lP.getFolioDocPago());
						//pS.setIdTipoDocPago(lP.getIdTipoDocPago().intValue());
						pS.setImpDocPago(lP.getImpDocPagoSinaxc());
						pS.setFechaDocPago(lP.getFechaDocPagoSinaxc());
						pS.setBancoId(lP.getBancoId());
						pS.setImpDocPago(lP.getImpTotalPagoSinaxc());
						pS.setImpPrecioTonPago(lP.getImpPrecioTonPagiSinaxc());
						cDAO.guardaObjeto(pS);					
					}
					//recupera las facturas globales					
					List<RelacionComprasTMP> lstComprasFacVentaGlobal = rDAO.recuperaParaComprasFacVentaGlobal(folioCartaAdhesion,l.getClaveBodega(), l.getEstadoAcopio(), p.getFolioProductor());
					for(RelacionComprasTMP fG : lstComprasFacVentaGlobal ){
						ComprasFacVentaGlobal global  = new ComprasFacVentaGlobal();
						global.setIdCompProd(cP.getIdCompProd());
						global.setFolioFacGlobal(fG.getNumeroFacGlobal());
						global.setNombrePerFac(fG.getPersonaFacturaGlobal());
						global.setRfcFacGlobal(fG.getRfcFacGlobal());
						global.setFechaEmisionFacGlobal(fG.getFechaFacGlobal());
						global.setVolFacGlobal(fG.getVolPnaFacGlobal());
						global.setImpFacGlobal(fG.getImpFacGlobal());
						
						cDAO.guardaObjeto(global);
						
					}
					//recupera los contratos
					List<RelacionComprasTMP> lstComprasContrato = rDAO.recuperaParaComprasContrato(folioCartaAdhesion,l.getClaveBodega(), l.getEstadoAcopio(), p.getFolioProductor());
					for(RelacionComprasTMP lC : lstComprasContrato ){
						ComprasContrato c = new ComprasContrato ();
						c.setFolioContrato(lC.getFolioContrato());
						c.setImpPactado(lC.getImpPactadoCompraVenta());
						c.setIdCompProd(cP.getIdCompProd());
						cDAO.guardaObjeto(c);
					}
				
					
				}
				
			}
			//rDAO.borrarRelacionComprasByFolioCarta(folioCartaAdhesion);	
			cuadroSatisfactorio = "La información se guardo satisfactoriamente en la base de datos";				
		}catch (Exception e){
			e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en generarBitacoraRelCompras debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			capturaCargaArchivoRelCompras();
			tipoAccion = 4;
		}
		return SUCCESS;
		
	}
	
	public String verReportesCruce() {
		//lstBitacoraRelComprasPorGrupo = rDAO.consultaBitacoraRelcomprasHCO(folioCartaAdhesion);
		lstBitacoraRelComprasPorGrupo = rDAO.consultaBitacoraRelcompras(folioCartaAdhesion);
		return SUCCESS;
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
			
	private String getRecuperaRutaCarta() throws JDBCException, Exception{
		String  nomRutaCartaAdhesion = "";
		AppLogger.info("app","folioCartaAdhesion "+folioCartaAdhesion);
		//Recupera la ruta de la solicitud de pago
		AsignacionCAaEspecialistaV acaaev= spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		//Recupera la ruta del programa
		programa = cDAO.consultaPrograma(acaaev.getIdPrograma()).get(0);
		idPrograma = programa.getIdPrograma();
		idComprador= acaaev.getIdComprador();
		nomRutaCartaAdhesion = folioCartaAdhesion.replaceAll("-", "");
		String ruta = programa.getRutaDocumentos()+"SolicitudPago/"+acaaev.getIdOficioCASP()+"/"+nomRutaCartaAdhesion+"/";
		Utilerias.crearDirectorio(ruta);
		return ruta;
	}
	
	private  double calcularMaximo(Double array [] ) {
		double valorMax = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > valorMax){
				valorMax = array[i];
			}
		}
		
		return valorMax;
	}
		
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
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
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	public String getRutaCartaAdhesion() {
		return rutaCartaAdhesion;
	}
	public void setRutaCartaAdhesion(String rutaCartaAdhesion) {
		this.rutaCartaAdhesion = rutaCartaAdhesion;
	}
	public String getNombreArchivoLog() {
		return nombreArchivoLog;
	}
	public void setNombreArchivoLog(String nombreArchivoLog) {
		this.nombreArchivoLog = nombreArchivoLog;
	}
	public List<String> getLstLog() {
		return lstLog;
	}
	public void setLstLog(List<String> lstLog) {
		this.lstLog = lstLog;
	}
	public int getErrorSistema() {
		return errorSistema;
	}
	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
	}
	public boolean getErrorArchivo() {
		return errorArchivo;
	}
	public void setErrorArchivo(boolean errorArchivo) {
		this.errorArchivo = errorArchivo;
	}
	public CartaAdhesion getCartaAdhesion() {
		return cartaAdhesion;
	}
	public void setCartaAdhesion(CartaAdhesion cartaAdhesion) {
		this.cartaAdhesion = cartaAdhesion;
	}
	public List<PersonalizacionRelacionesProgramaV> getLstPersonalizacionRelacionesProgramaV() {
		return lstPersonalizacionRelacionesProgramaV;
	}
	public void setLstPersonalizacionRelacionesProgramaV(
			List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV) {
		this.lstPersonalizacionRelacionesProgramaV = lstPersonalizacionRelacionesProgramaV;
	}
	public List<CatCriteriosValidacion> getLstCatCriteriosValidacion() {
		return lstCatCriteriosValidacion;
	}
	public void setLstCatCriteriosValidacion(
			List<CatCriteriosValidacion> lstCatCriteriosValidacion) {
		this.lstCatCriteriosValidacion = lstCatCriteriosValidacion;
	}
	public boolean isPidePeriodo() {
		return pidePeriodo;
	}
	public void setPidePeriodo(boolean pidePeriodo) {
		this.pidePeriodo = pidePeriodo;
	}

	public int getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(int tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	public boolean isExisteRelacionYaValidada() {
		return existeRelacionYaValidada;
	}

	public void setExisteRelacionYaValidada(boolean existeRelacionYaValidada) {
		this.existeRelacionYaValidada = existeRelacionYaValidada;
	}

	public int getIdCriterio() {
		return idCriterio;
	}

	public void setIdCriterio(int idCriterio) {
		this.idCriterio = idCriterio;
	}
	
	public String getRutaSalida() {
		return rutaSalida;
	}
	
	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}

	public List<BitacoraRelcompras> getLstBitacoraRelCompras() {
		return lstBitacoraRelCompras;
	}

	public void setLstBitacoraRelCompras(
			List<BitacoraRelcompras> lstBitacoraRelCompras) {
		this.lstBitacoraRelCompras = lstBitacoraRelCompras;
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
	
	public List<ProductorExisteBD> getLstProductorExisteBD() {
		return lstProductorExisteBD;
	}

	public void setLstProductorExisteBD(List<ProductorExisteBD> lstProductorExisteBD) {
		this.lstProductorExisteBD = lstProductorExisteBD;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getRutaMarcaAgua() {
		return rutaMarcaAgua;
	}

	public void setRutaMarcaAgua(String rutaMarcaAgua) {
		this.rutaMarcaAgua = rutaMarcaAgua;
	}

	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}

	public List<BoletasVsFacturas> getLstBoletasVsFacturas() {
		return lstBoletasVsFacturas;
	}

	public void setLstBoletasVsFacturas(List<BoletasVsFacturas> lstBoletasVsFacturas) {
		this.lstBoletasVsFacturas = lstBoletasVsFacturas;
	}

	public List<BoletasDuplicadas> getLstBoletasDuplicadas() {
		return lstBoletasDuplicadas;
	}

	public void setLstBoletasDuplicadas(List<BoletasDuplicadas> lstBoletasDuplicadas) {
		this.lstBoletasDuplicadas = lstBoletasDuplicadas;
	}

	
	public List<RelacionComprasTMP> getLstPrediosNoExistenBD() {
		return lstPrediosNoExistenBD;
	}

	public void setLstPrediosNoExistenBD(
			List<RelacionComprasTMP> lstPrediosNoExistenBD) {
		this.lstPrediosNoExistenBD = lstPrediosNoExistenBD;
	}

	public List<BoletasFueraDePeriodo> getLstBoletasFueraDePeriodo() {
		return lstBoletasFueraDePeriodo;
	}

	public void setLstBoletasFueraDePeriodo(
			List<BoletasFueraDePeriodo> lstBoletasFueraDePeriodo) {
		this.lstBoletasFueraDePeriodo = lstBoletasFueraDePeriodo;
	}

	public List<BoletasFueraDePeriodoPago> getLstBoletasFueraDePeriodoPago() {
		return lstBoletasFueraDePeriodoPago;
	}

	public void setLstBoletasFueraDePeriodoPago(
			List<BoletasFueraDePeriodoPago> lstBoletasFueraDePeriodoPago) {
		this.lstBoletasFueraDePeriodoPago = lstBoletasFueraDePeriodoPago;
	}

	public List<RfcProductorVsRfcFactura> getLstRfcProductorVsRfcFactura() {
		return lstRfcProductorVsRfcFactura;
	}

	public void setLstRfcProductorVsRfcFactura(
			List<RfcProductorVsRfcFactura> lstRfcProductorVsRfcFactura) {
		this.lstRfcProductorVsRfcFactura = lstRfcProductorVsRfcFactura;
	}

	public List<FacturasVsPago> getLstFacturasVsPago() {
		return lstFacturasVsPago;
	}

	public void setLstFacturasVsPago(List<FacturasVsPago> lstFacturasVsPago) {
		this.lstFacturasVsPago = lstFacturasVsPago;
	}

	public List<PrediosNoValidadosDRDE> getLstPrediosNoValidadosDRDE() {
		return lstPrediosNoValidadosDRDE;
	}

	public void setLstPrediosNoValidadosDRDE(
			List<PrediosNoValidadosDRDE> lstPrediosNoValidadosDRDE) {
		this.lstPrediosNoValidadosDRDE = lstPrediosNoValidadosDRDE;
	}
	
	public List<PrediosNoPagados> getLstPrediosNoPagados() {
		return lstPrediosNoPagados;
	}
	
	public void setLstPrediosNoPagados(List<PrediosNoPagados> lstPrediosNoPagados) {
		this.lstPrediosNoPagados = lstPrediosNoPagados;
	}
	
	public boolean isPeriodoAcopioPrograma() {
		return periodoAcopioPrograma;
	}
	
	public void setPeriodoAcopioPrograma(boolean periodoAcopioPrograma) {
		this.periodoAcopioPrograma = periodoAcopioPrograma;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	public boolean isAplicaAdendum() {
		return aplicaAdendum;
	}

	public void setAplicaAdendum(boolean aplicaAdendum) {
		this.aplicaAdendum = aplicaAdendum;
	}

	public List<ProductorFactura> getLstProductorFactura() {
		return lstProductorFactura;
	}

	public void setLstProductorFactura(List<ProductorFactura> lstProductorFactura) {
		this.lstProductorFactura = lstProductorFactura;
	}

	public List<ProductorPredioValidado> getLstProductorPredioValidado() {
		return lstProductorPredioValidado;
	}

	public void setLstProductorPredioValidado(
			List<ProductorPredioValidado> lstProductorPredioValidado) {
		this.lstProductorPredioValidado = lstProductorPredioValidado;
	}
	
	public List<FacturaFueraPeriodoAuditor> getLstFacturaFueraPeriodo() {
		return lstFacturaFueraPeriodo;
	}

	public void setLstFacturaFueraPeriodo(
			List<FacturaFueraPeriodoAuditor> lstFacturaFueraPeriodo) {
		this.lstFacturaFueraPeriodo = lstFacturaFueraPeriodo;
	}

	public List<FacturaFueraPeriodoPago> getLstFacturaFueraPeriodoPago() {
		return lstFacturaFueraPeriodoPago;
	}

	public void setLstFacturaFueraPeriodoPago(
			List<FacturaFueraPeriodoPago> lstFacturaFueraPeriodoPago) {
		this.lstFacturaFueraPeriodoPago = lstFacturaFueraPeriodoPago;
	}

	public List<ChequeFueraPeriodoAuditor> getLstChequeFueraPeriodoAuditor() {
		return lstChequeFueraPeriodoAuditor;
	}

	public void setLstChequeFueraPeriodoAuditor(
			List<ChequeFueraPeriodoAuditor> lstChequeFueraPeriodoAuditor) {
		this.lstChequeFueraPeriodoAuditor = lstChequeFueraPeriodoAuditor;
	}	
	
	public List<ChequeFueraPeriodoContrato> getLstChequeFueraPeriodoContrato() {
		return lstChequeFueraPeriodoContrato;
	}

	public void setLstChequeFueraPeriodoContrato(
			List<ChequeFueraPeriodoContrato> lstChequeFueraPeriodoContrato) {
		this.lstChequeFueraPeriodoContrato = lstChequeFueraPeriodoContrato;
	}

	public List<ProductorPredioPagado> getLstProductorPredioPagado() {
		return lstProductorPredioPagado;
	}

	public void setLstProductorPredioPagado(
			List<ProductorPredioPagado> lstProductorPredioPagado) {
		this.lstProductorPredioPagado = lstProductorPredioPagado;
	}

	public List<FacturaFueraPeriodoPagoAdendum> getLstFacturaFueraPeriodoPagoAdendum() {
		return lstFacturaFueraPeriodoPagoAdendum;
	}

	public void setLstFacturaFueraPeriodoPagoAdendum(
			List<FacturaFueraPeriodoPagoAdendum> lstFacturaFueraPeriodoPagoAdendum) {
		this.lstFacturaFueraPeriodoPagoAdendum = lstFacturaFueraPeriodoPagoAdendum;
	}

	public List<ChequeFueraPeriodoContrato> getLstChequeFueraPeriodoPagoAdendum() {
		return lstChequeFueraPeriodoPagoAdendum;
	}

	public void setLstChequeFueraPeriodoPagoAdendum(
			List<ChequeFueraPeriodoContrato> lstChequeFueraPeriodoPagoAdendum) {
		this.lstChequeFueraPeriodoPagoAdendum = lstChequeFueraPeriodoPagoAdendum;
	}

	public List<ChequesDuplicadoBancoPartipante> getLstChequesDuplicadoBancoPartipante() {
		return lstChequesDuplicadoBancoPartipante;
	}

	public void setLstChequesDuplicadoBancoPartipante(
			List<ChequesDuplicadoBancoPartipante> lstChequesDuplicadoBancoPartipante) {
		this.lstChequesDuplicadoBancoPartipante = lstChequesDuplicadoBancoPartipante;
	}

	public List<PrecioPagadoProductor> getLstPrecioPagadoProductor() {
		return lstPrecioPagadoProductor;
	}

	public void setLstPrecioPagadoProductor(
			List<PrecioPagadoProductor> lstPrecioPagadoProductor) {
		this.lstPrecioPagadoProductor = lstPrecioPagadoProductor;
	}

	public double getTotalVolSolFacVenta() {
		return totalVolSolFacVenta;
	}

	public void setTotalVolSolFacVenta(double totalVolSolFacVenta) {
		this.totalVolSolFacVenta = totalVolSolFacVenta;
	}

	public double getTotalPrecioTonelada() {
		return totalPrecioTonelada;
	}

	public void setTotalPrecioTonelada(double totalPrecioTonelada) {
		this.totalPrecioTonelada = totalPrecioTonelada;
	}

	public double getTotalImpSolFacVenta() {
		return totalImpSolFacVenta;
	}

	public void setTotalImpSolFacVenta(double totalImpSolFacVenta) {
		this.totalImpSolFacVenta = totalImpSolFacVenta;
	}

	public double getTotalPrecioPactadoPorTonelada() {
		return totalPrecioPactadoPorTonelada;
	}

	public void setTotalPrecioPactadoPorTonelada(
			double totalPrecioPactadoPorTonelada) {
		this.totalPrecioPactadoPorTonelada = totalPrecioPactadoPorTonelada;
	}

	public double getTotalPrecioPacXTipoCambio() {
		return totalPrecioPacXTipoCambio;
	}

	public void setTotalPrecioPacXTipoCambio(double totalPrecioPacXTipoCambio) {
		this.totalPrecioPacXTipoCambio = totalPrecioPacXTipoCambio;
	}

	public double getTotalPagarFacturaPnaPrecCal() {
		return totalPagarFacturaPnaPrecCal;
	}

	public void setTotalPagarFacturaPnaPrecCal(double totalPagarFacturaPnaPrecCal) {
		this.totalPagarFacturaPnaPrecCal = totalPagarFacturaPnaPrecCal;
	}

	public double getTotalDifMontoFac() {
		return totalDifMontoFac;
	}

	public void setTotalDifMontoFac(double totalDifMontoFac) {
		this.totalDifMontoFac = totalDifMontoFac;
	}

	public double getTotalDifMontoTotal() {
		return totalDifMontoTotal;
	}

	public void setTotalDifMontoTotal(double totalDifMontoTotal) {
		this.totalDifMontoTotal = totalDifMontoTotal;
	}

	public double getTotalTipoCambio() {
		return totalTipoCambio;
	}

	public void setTotalTipoCambio(double totalTipoCambio) {
		this.totalTipoCambio = totalTipoCambio;
	}
	
	public List<ContratosRelacionCompras> getLstContratos() {
		return lstContratos;
	}

	public void setLstContratos(List<ContratosRelacionCompras> lstContratos) {
		this.lstContratos = lstContratos;
	}

	public Map<String, Date> getFechaContratoTipoCambio() {
		return fechaContratoTipoCambio;
	}

	public void setFechaContratoTipoCambio(Map<String, Date> fechaContratoTipoCambio) {
		this.fechaContratoTipoCambio = fechaContratoTipoCambio;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposDetalleRelacionV() {
		return lstGruposCamposDetalleRelacionV;
	}
	

	public void setLstGruposCamposDetalleRelacionV(
			List<GruposCamposRelacionV> lstGruposCamposDetalleRelacionV) {
		this.lstGruposCamposDetalleRelacionV = lstGruposCamposDetalleRelacionV;
	}

	
	public List<FacturasIgualesFacAserca> getLstFacturasIgualesFacAserca() {
		return lstFacturasIgualesFacAserca;
	}

	
	public void setLstFacturasIgualesFacAserca(
			List<FacturasIgualesFacAserca> lstFacturasIgualesFacAserca) {
		this.lstFacturasIgualesFacAserca = lstFacturasIgualesFacAserca;
	}

	public Integer[] getCamposFactura() {
		return camposFactura;
	}

	public void setCamposFactura(Integer[] camposFactura) {
		this.camposFactura = camposFactura;
	}

	public List<PrecioPagadoMenor> getLstPrecioPagadoMenor() {
		return lstPrecioPagadoMenor;
	}

	public void setLstPrecioPagadoMenor(List<PrecioPagadoMenor> lstPrecioPagadoMenor) {
		this.lstPrecioPagadoMenor = lstPrecioPagadoMenor;
	}
	
	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public int getIdCriterioG() {
		return idCriterioG;
	}

	public void setIdCriterioG(int idCriterioG) {
		this.idCriterioG = idCriterioG;
	}
	
	public String getCuadroSatisfactorio() {
		return cuadroSatisfactorio;
	}

	public void setCuadroSatisfactorio(String cuadroSatisfactorio) {
		this.cuadroSatisfactorio = cuadroSatisfactorio;
	}

	public String getGrupoCriterio() {
		return grupoCriterio;
	}

	public void setGrupoCriterio(String grupoCriterio) {
		this.grupoCriterio = grupoCriterio;
	}

	public String getGrupoCriterioG() {
		return grupoCriterioG;
	}

	public void setGrupoCriterioG(String grupoCriterioG) {
		this.grupoCriterioG = grupoCriterioG;
	}

	public String getNombreArchivoLogXls() {
		return nombreArchivoLogXls;
	}
	
	public void setNombreArchivoLogXls(String nombreArchivoLogXls) {
		this.nombreArchivoLogXls = nombreArchivoLogXls;
	}

	public int getAplicaPeriodoLineamiento() {
		return aplicaPeriodoLineamiento;
	}
	
	public void setAplicaPeriodoLineamiento(int aplicaPeriodoLineamiento) {
		this.aplicaPeriodoLineamiento = aplicaPeriodoLineamiento;
	}

	public boolean isNoSeTieneConfPerEnPrg() {
		return noSeTieneConfPerEnPrg;
	}

	public void setNoSeTieneConfPerEnPrg(boolean noSeTieneConfPerEnPrg) {

		this.noSeTieneConfPerEnPrg = noSeTieneConfPerEnPrg;
	}

	public List<BitacoraRelcompras> getLstBitacoraRelComprasPorGrupo() {
		return lstBitacoraRelComprasPorGrupo;
	}

	public void setLstBitacoraRelComprasPorGrupo(
			List<BitacoraRelcompras> lstBitacoraRelComprasPorGrupo) {
		this.lstBitacoraRelComprasPorGrupo = lstBitacoraRelComprasPorGrupo;
	}

	public List<ProductoresIncosistentes> getLstProductoresIncosistentes() {
		return lstProductoresIncosistentes;
	}

	public void setLstProductoresIncosistentes(
			List<ProductoresIncosistentes> lstProductoresIncosistentes) {
		this.lstProductoresIncosistentes = lstProductoresIncosistentes;
	}

	public String getNombreArchivoExcel99() {
		return nombreArchivoExcel99;
	}

	public void setNombreArchivoExcel99(String nombreArchivoExcel99) {
		this.nombreArchivoExcel99 = nombreArchivoExcel99;
	}

	public List<BoletasCamposRequeridos> getLstBoletasCamposRequeridos() {
		return lstBoletasCamposRequeridos;
	}

	public void setLstBoletasCamposRequeridos(
			List<BoletasCamposRequeridos> lstBoletasCamposRequeridos) {
		this.lstBoletasCamposRequeridos = lstBoletasCamposRequeridos;
	}

	public String getCamposQueAplica() {
		return camposQueAplica;
	}

	public void setCamposQueAplica(String camposQueAplica) {
		this.camposQueAplica = camposQueAplica;
	}

	public int getPideTipoPeriodo() {
		return pideTipoPeriodo;
	}
	public void setPideTipoPeriodo(int pideTipoPeriodo) {
		this.pideTipoPeriodo = pideTipoPeriodo;
	}
	
	public long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(long idPerRel) {
		this.idPerRel = idPerRel;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	
	public int getAplicaPeriodoAuditor() {
		return aplicaPeriodoAuditor;
	}
	

	public void setAplicaPeriodoAuditor(int aplicaPeriodoAuditor) {
		this.aplicaPeriodoAuditor = aplicaPeriodoAuditor;
	}

	public Map<String, Date> getFechaInicioAdendumContrato() {
		return fechaInicioAdendumContrato;
	}

	public void setFechaInicioAdendumContrato(
			Map<String, Date> fechaInicioAdendumContrato) {
		this.fechaInicioAdendumContrato = fechaInicioAdendumContrato;
	}

	public Map<String, Date> getFechaFinAdendumContrato() {
		return fechaFinAdendumContrato;
	}

	public void setFechaFinAdendumContrato(Map<String, Date> fechaFinAdendumContrato) {
		this.fechaFinAdendumContrato = fechaFinAdendumContrato;
	}

	public List<FacturasCamposRequeridos> getLstFacturasCamposRequeridos() {
		return lstFacturasCamposRequeridos;
	}

	public void setLstFacturasCamposRequeridos(
			List<FacturasCamposRequeridos> lstFacturasCamposRequeridos) {
		this.lstFacturasCamposRequeridos = lstFacturasCamposRequeridos;
	}

	public List<PagosCamposRequeridos> getLstPagosCamposRequeridos() {
		return lstPagosCamposRequeridos;
	}

	public void setLstPagosCamposRequeridos(
			List<PagosCamposRequeridos> lstPagosCamposRequeridos) {
		this.lstPagosCamposRequeridos = lstPagosCamposRequeridos;
	}

	
	public List<GeneralToneladasTotalesPorBodFac> getLstGeneralToneladasTotalesPorBodFac() {
		return lstGeneralToneladasTotalesPorBodFac;
	}

	public void setLstGeneralToneladasTotalesPorBodFac(
			List<GeneralToneladasTotalesPorBodFac> lstGeneralToneladasTotalesPorBodFac) {
		this.lstGeneralToneladasTotalesPorBodFac = lstGeneralToneladasTotalesPorBodFac;
	}

	
	public List<BoletasFacturasPagosIncosistentes> getLstBoletasFacturasPagosIncosistentes() {
		return lstBoletasFacturasPagosIncosistentes;
	}
	

	public void setLstBoletasFacturasPagosIncosistentes(
			List<BoletasFacturasPagosIncosistentes> lstBoletasFacturasPagosIncosistentes) {
		this.lstBoletasFacturasPagosIncosistentes = lstBoletasFacturasPagosIncosistentes;
	}

	
	public String getNombreArchivoLogCargaXls() {
		return nombreArchivoLogCargaXls;
	}
	

	public void setNombreArchivoLogCargaXls(String nombreArchivoLogCargaXls) {
		this.nombreArchivoLogCargaXls = nombreArchivoLogCargaXls;
	}

	public List<RendimientosProcedente> getLstRendimientosProcedente() {
		return lstRendimientosProcedente;
	}

	public void setLstRendimientosProcedente(
			List<RendimientosProcedente> lstRendimientosProcedente) {
		this.lstRendimientosProcedente = lstRendimientosProcedente;
	}
	
	public List<PrediosProdsContNoExisteBD> getLstPrediosProdsContNoExistenBD() {
		return lstPrediosProdsContNoExistenBD;
	}

	public void setLstPrediosProdsContNoExistenBD(
			List<PrediosProdsContNoExisteBD> lstPrediosProdsContNoExistenBD) {
		this.lstPrediosProdsContNoExistenBD = lstPrediosProdsContNoExistenBD;
	}
	
	
	public List<VolumenFiniquito> getLstVolumenCumplido() {
		return lstVolumenCumplido;
	}

	public void setLstVolumenCumplido(List<VolumenFiniquito> lstVolumenCumplido) {
		this.lstVolumenCumplido = lstVolumenCumplido;
	}

	public Date getFechaDeReporte() {
		return fechaDeReporte;
	}

	public void setFechaDeReporte(Date fechaDeReporte) {
		this.fechaDeReporte = fechaDeReporte;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<PrecioPagPorTipoCambio> getLstPrecioPagPorTipoCambio() {
		return lstPrecioPagPorTipoCambio;
	}

	public void setLstPrecioPagPorTipoCambio(
			List<PrecioPagPorTipoCambio> lstPrecioPagPorTipoCambio) {
		this.lstPrecioPagPorTipoCambio = lstPrecioPagPorTipoCambio;
	}

	public List<PrecioPagadoNoCorrespondeConPagosV> getLstPrecioPagadoNoCorrespondeConPagosV() {
		return lstPrecioPagadoNoCorrespondeConPagosV;
	}

	public void setLstPrecioPagadoNoCorrespondeConPagosV(
			List<PrecioPagadoNoCorrespondeConPagosV> lstPrecioPagadoNoCorrespondeConPagosV) {
		this.lstPrecioPagadoNoCorrespondeConPagosV = lstPrecioPagadoNoCorrespondeConPagosV;
	}

	public List<PrecioPagadoNoCorrespondeConPagosV> getLstPrecioPagadoMenorQueAviso() {
		return lstPrecioPagadoMenorQueAviso;
	}

	public void setLstPrecioPagadoMenorQueAviso(
			List<PrecioPagadoNoCorrespondeConPagosV> lstPrecioPagadoMenorQueAviso) {
		this.lstPrecioPagadoMenorQueAviso = lstPrecioPagadoMenorQueAviso;
	}

	public List<RelacionComprasTMP> getLstRfcVsCurpProductor() {
		return lstRfcVsCurpProductor;
	}

	public void setLstRfcVsCurpProductor(
			List<RelacionComprasTMP> lstRfcVsCurpProductor) {
		this.lstRfcVsCurpProductor = lstRfcVsCurpProductor;
	}

	public boolean isAplicaComplemento() {
		return aplicaComplemento;
	}

	public void setAplicaComplemento(boolean aplicaComplemento) {
		this.aplicaComplemento = aplicaComplemento;

	}

	public Date getFechaInicioAuditor() {
		return fechaInicioAuditor;
	}

	public void setFechaInicioAuditor(Date fechaInicioAuditor) {
		this.fechaInicioAuditor = fechaInicioAuditor;
	}

	public Date getFechaFinAuditor() {
		return fechaFinAuditor;
	}

	public void setFechaFinAuditor(Date fechaFinAuditor) {
		this.fechaFinAuditor = fechaFinAuditor;
	}

	public List<RelacionComprasTMP> getLstRfcProductorVsRfcFactura2() {
		return lstRfcProductorVsRfcFactura2;
	}

	public void setLstRfcProductorVsRfcFactura2(
			List<RelacionComprasTMP> lstRfcProductorVsRfcFactura2) {
		this.lstRfcProductorVsRfcFactura2 = lstRfcProductorVsRfcFactura2;
	}

	public List<VolumenNoProcedente> getLstVolNoProcedenteYApoyEnReg() {
		return lstVolNoProcedenteYApoyEnReg;
	}

	public void setLstVolNoProcedenteYApoyEnReg(
			List<VolumenNoProcedente> lstVolNoProcedenteYApoyEnReg) {
		this.lstVolNoProcedenteYApoyEnReg = lstVolNoProcedenteYApoyEnReg;
	}	

	
}

