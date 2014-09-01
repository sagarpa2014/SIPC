package mx.gob.comer.sipc.reportes.action;

import java.io.File;
import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.ReportesDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.ReporteGlobalV;
import mx.gob.comer.sipc.vistas.domain.ReporteProgramaCompradorV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.ServletContext;

import jxl.Workbook;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@SuppressWarnings({ "serial", "deprecation" })
public class MonitorPagosAction  extends ActionSupport implements ServletContextAware, SessionAware, Serializable { 
	
	private Map<String, Object> session;
	private ServletContext context;
	private ReportesDAO rDAO = new ReportesDAO();
	private CatalogosDAO cDAO = new CatalogosDAO();
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	@SuppressWarnings("unused")
	private TextUtil textUtil;
	private List<ReporteGlobalV> lstReporteGlobalV;
	private List<ReporteProgramaCompradorV> lstReporteProgramaV;
	private String xls;
	private String tipo;
	private String nombreArchivo;
	private Date fechaInicio;
	private Date fechaFin;
	private Double totalPagosTramitados;
	private Double totalVolumenTramitados;
	private Double totalImporteTramitados;
	private Double totalPagosPagados;
	private Double totalVolumenPagados;
	private Double totalImportePagados;
	private Double totalPagosRechazados;
	private Double totalVolumenRechazados;
	private Double totalImporteRechazados;
	private Double totalPagosPendientes;
	private Double totalVolumenPendientes;
	private Double totalImportePendientes;	
	
	public String reporteMonitorGlobal(){
		session = ActionContext.getContext().getSession();
		session.put("fechaInicio", fechaInicio);
		session.put("fechaFin", fechaFin);

		Utilerias.getResponseISO();
		String fechaInicioS = "";
		String fechaFinS = "";
		/*Validacion de rango de fecha*/
		if(fechaInicio !=null && !fechaInicio.equals("")){
			if(fechaFin != null && !fechaFin.equals("")){
				fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();		
				fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
				if(Long.parseLong(fechaFinS)< Long.parseLong(fechaInicioS)){
					addActionError("La fecha inicio no puede ser mayor a la fecha final");
					return SUCCESS;
				}			
			     	
			}
		}
		
		if(fechaInicio != null && !fechaInicio.equals("")){
			fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio).toString();
		}
		if(fechaFin != null && !fechaFin.equals("")){
			fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFin).toString();
		}		
		setLstReporteGlobalV(rDAO.consultaReporteGlobalPagos(fechaInicioS, fechaFinS));
		totalPagosTramitados = rDAO.consultaReporteGlobalTotales(1, fechaInicioS, fechaFinS);
		totalVolumenTramitados = rDAO.consultaReporteGlobalTotales(2, fechaInicioS, fechaFinS);
		totalImporteTramitados = rDAO.consultaReporteGlobalTotales(3, fechaInicioS, fechaFinS);
		totalPagosPagados = rDAO.consultaReporteGlobalTotales(4, fechaInicioS, fechaFinS);
		totalVolumenPagados = rDAO.consultaReporteGlobalTotales(5, fechaInicioS, fechaFinS);
		totalImportePagados = rDAO.consultaReporteGlobalTotales(6, fechaInicioS, fechaFinS);
		totalPagosRechazados = rDAO.consultaReporteGlobalTotales(7, fechaInicioS, fechaFinS);
		totalVolumenRechazados = rDAO.consultaReporteGlobalTotales(8, fechaInicioS, fechaFinS);
		totalImporteRechazados = rDAO.consultaReporteGlobalTotales(9, fechaInicioS, fechaFinS);
		totalPagosPendientes = rDAO.consultaReporteGlobalTotales(10, fechaInicioS, fechaFinS);
		totalVolumenPendientes = rDAO.consultaReporteGlobalTotales(11, fechaInicioS, fechaFinS);
		totalImportePendientes = rDAO.consultaReporteGlobalTotales(12, fechaInicioS, fechaFinS);	
		return SUCCESS;
	}

	public String reporteMonitorPrograma(){
		session = ActionContext.getContext().getSession();
		session.put("fechaInicio", fechaInicio);
		session.put("fechaFin", fechaFin);
		
		Utilerias.getResponseISO();
		String fechaInicioS = "";
		String fechaFinS = "";
		/*Validacion de rango de fecha*/
		if(fechaInicio !=null && !fechaInicio.equals("")){
			if(fechaFin != null && !fechaFin.equals("")){
				fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();		
				fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
				if(Long.parseLong(fechaFinS)< Long.parseLong(fechaInicioS)){
					addActionError("La fecha inicio no puede ser mayor a la fecha final");
					return SUCCESS;
				}			
			     	
			}
		}
		
		if(fechaInicio != null && !fechaInicio.equals("")){
			fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio).toString();
		}
		if(fechaFin != null && !fechaFin.equals("")){
			fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFin).toString();
		}		
		setLstReporteProgramaV(rDAO.consultaReporteProgramaCompradorPagos(fechaInicioS, fechaFinS));
		totalPagosTramitados = rDAO.consultaReporteProgramaTotales(1, fechaInicioS, fechaFinS);
		totalVolumenTramitados = rDAO.consultaReporteProgramaTotales(2, fechaInicioS, fechaFinS);
		totalImporteTramitados = rDAO.consultaReporteProgramaTotales(3, fechaInicioS, fechaFinS);
		totalPagosPagados = rDAO.consultaReporteProgramaTotales(4, fechaInicioS, fechaFinS);
		totalVolumenPagados = rDAO.consultaReporteProgramaTotales(5, fechaInicioS, fechaFinS);
		totalImportePagados = rDAO.consultaReporteProgramaTotales(6, fechaInicioS, fechaFinS);
		totalPagosRechazados = rDAO.consultaReporteProgramaTotales(7, fechaInicioS, fechaFinS);
		totalVolumenRechazados = rDAO.consultaReporteProgramaTotales(8, fechaInicioS, fechaFinS);
		totalImporteRechazados = rDAO.consultaReporteProgramaTotales(9, fechaInicioS, fechaFinS);
		totalPagosPendientes = rDAO.consultaReporteProgramaTotales(10, fechaInicioS, fechaFinS);
		totalVolumenPendientes = rDAO.consultaReporteProgramaTotales(11, fechaInicioS, fechaFinS);
		totalImportePendientes = rDAO.consultaReporteProgramaTotales(12, fechaInicioS, fechaFinS);	

		return SUCCESS;
	}

	public String exportaReporteGlobal(){
		String fechaInicioS = "";
		String fechaFinS = "";

		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			setTipo("reporteg");
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_PLANTILLA_REPORTES");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportes");
			}

			fechaInicio = (Date)(session.get("fechaInicio"));
			fechaFin = (Date)(session.get("fechaFin"));
			/*Validacion de rango de fecha*/
			if(fechaInicio !=null && !fechaInicio.equals("")){
				if(fechaFin != null && !fechaFin.equals("")){
					fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();		
					fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
					if(Long.parseLong(fechaFinS)< Long.parseLong(fechaInicioS)){
						addActionError("La fecha inicio no puede ser mayor a la fecha final");
						return SUCCESS;
					}			
				     	
				}
			}
			
			if(fechaInicio != null && !fechaInicio.equals("")){
				fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio).toString();
			}
			if(fechaFin != null && !fechaFin.equals("")){
				fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFin).toString();
			}		

			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteGlobalV = rDAO.insolatedConsultaReporteGlobalPagos(fechaInicioS, fechaFinS);
			
			if(lstReporteGlobalV!=null && lstReporteGlobalV.size()>0){
				// Generar XLS
				nombreArchivo = contruyeArchivoReporteGlobal(rutaPlantilla,rutaSalida,lstReporteGlobalV);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del reporte global de pagos, debido a: "+e.getCause());
		}finally{
			lstReporteGlobalV = rDAO.insolatedConsultaReporteGlobalPagos(fechaInicioS, fechaFinS);
		}		
		return "SUCCESS";
	}


	private String contruyeArchivoReporteGlobal(String rutaPlantilla, String rutaSalida, List<ReporteGlobalV> lst){
		String xlsOut = new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+"-REPORTEGLOBALPAGOS.xls";
		Integer sumaTotalPagosTramitados = 0, sumaTotalPagosPagados = 0, sumaTotalPagosRechazados = 0, sumaTotalPagosPendientes = 0;
		Double sumaTotalImporteTramitado = 0.0, sumaTotalVolumenTramitado = 0.0, sumaTotalImportePagado = 0.0, sumaTotalVolumenPagado = 0.0, sumaTotalImporteRechazado = 0.0, sumaTotalVolumenRechazado = 0.0, sumaTotalImportePendiente = 0.0, sumaTotalVolumenPendiente = 0.0;
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPORTEG.xls";
		if(!rutaSalida.endsWith(File.separator)){
			rutaSalida += File.separator;
		}
				
		Workbook workbook = null;
		WritableWorkbook copy = null;
		try{
			// Abrir plantilla
			workbook = Workbook.getWorkbook(new File(rutaPlantilla));
			// Hacer copia
			
			copy = Workbook.createWorkbook(new File(rutaSalida+xlsOut), workbook);
			WritableSheet sheet = copy.getSheet(0); 
			// Escribir datos
			
			WritableCellFormat cf = new WritableCellFormat();
			cf.setBorder( Border.ALL , BorderLineStyle.THIN );

			int row = 2;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				ReporteGlobalV p = lst.get(i);
		        // Comprador
				sheet.addCell( new Label(col++,row, p.getComprador(), cf));
				// Tramitados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosTramitados()), cf));
				sumaTotalPagosTramitados+=p.getPagosTramitados();
				// Volumen Tramitados
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenTramitado()), cf));
		        sumaTotalVolumenTramitado+=p.getVolumenTramitado();
				// Importe Tramitados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImporteTramitado()), cf));
				sumaTotalImporteTramitado+=p.getImporteTramitado();
				// Pagados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosPagados()), cf));
				sumaTotalPagosPagados+=p.getPagosPagados();
				// Volumen Pagado
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenPagado()), cf));
		        sumaTotalVolumenPagado+=p.getVolumenPagado();
				// Importe Pagados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImportePagado()), cf));
				sumaTotalImportePagado+=p.getImportePagado();
				// Rechazados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosRechazados()), cf));
				sumaTotalPagosRechazados+=p.getPagosRechazados();
				// Volumen Rechazado
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenRechazado()), cf));
		        sumaTotalVolumenRechazado+=p.getVolumenRechazado();
				// Importe Rechazado
				sheet.addCell( new Label(col++,row,TextUtil.formateaNumeroComoImporteSinComas(p.getImporteRechazado()), cf));
				sumaTotalImporteRechazado+=p.getImporteRechazado();
				// Pendientes
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosPendientes()), cf));
				sumaTotalPagosPendientes+=p.getPagosPendientes();
				// Volumen Pendiente
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenPendiente()), cf));
		        sumaTotalVolumenPendiente+=p.getVolumenPendiente();
				// Importe Pendientes
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImportePendiente()), cf));
				sumaTotalImportePendiente+=p.getImportePendiente();
				row++;
			}
			sheet.addCell( new Label(0,row, "TOTALES:", cf));
			// Totales Tramitados
			sheet.addCell( new Label(1,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosTramitados), cf));
			// Totales Volumen Tramitado
	        sheet.addCell( new Label(2,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenTramitado), cf));
			// Totales Importe Tramitado
			sheet.addCell( new Label(3,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImporteTramitado), cf));
			// Totales Pagados
			sheet.addCell( new Label(4,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosPagados), cf));
			// Totales Volumen Pagado
	        sheet.addCell( new Label(5,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenPagado), cf));
			// Totales Importe Pagado
			sheet.addCell( new Label(6,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImportePagado), cf));
			// Totales Rechazados
			sheet.addCell( new Label(7,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosRechazados), cf));
			// Totales Volumen Rechazado
	        sheet.addCell( new Label(8,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenRechazado), cf));
			// Totales Importe Rechazado
			sheet.addCell( new Label(9,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImporteRechazado), cf));
			// Totales Pendientes
			sheet.addCell( new Label(10,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosPendientes), cf));
			// Totales Volumen Pendiente
	        sheet.addCell( new Label(11,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenPendiente), cf));
			// Totales Importe Pendiente
			sheet.addCell( new Label(12,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImportePendiente), cf));
	        
		 			
		}catch(Exception e){
			System.err.println( e.getMessage() );
		}finally{
			// Cerrar y guardar copia
			if(copy!=null){
				try{
					copy.write(); 
					copy.close();
				}catch(Exception e){
					System.out.println( e.getMessage() );
				}
			}
			// Cerrar plantilla
			if(workbook!=null){
				workbook.close();
			}
		}
		setXls(xlsOut);
		return xlsOut;
	}
	
	public String consigueArchivoExcelRepG() throws Exception{
		try{
			String rutaSalida = cDAO.consultaParametros("RUTA_PLANTILLA_REPORTES");	
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreArchivo,"xls");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String exportaReportePrograma(){
		String fechaInicioS = "";
		String fechaFinS = "";

		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			setTipo("reportep");
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_PLANTILLA_REPORTES");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportes");
			}

			fechaInicio = (Date)(session.get("fechaInicio"));
			fechaFin = (Date)(session.get("fechaFin"));
			/*Validacion de rango de fecha*/
			if(fechaInicio !=null && !fechaInicio.equals("")){
				if(fechaFin != null && !fechaFin.equals("")){
					fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();		
					fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
					if(Long.parseLong(fechaFinS)< Long.parseLong(fechaInicioS)){
						addActionError("La fecha inicio no puede ser mayor a la fecha final");
						return SUCCESS;
					}			
				     	
				}
			}
			
			if(fechaInicio != null && !fechaInicio.equals("")){
				fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio).toString();
			}
			if(fechaFin != null && !fechaFin.equals("")){
				fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFin).toString();
			}		

			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteProgramaV = rDAO.insolatedConsultaReporteProgramaCompradorPagos(fechaInicioS, fechaFinS);
			
			if(lstReporteProgramaV!=null && lstReporteProgramaV.size()>0){
				// Generar XLS
				nombreArchivo = contruyeArchivoReportePrograma(rutaPlantilla,rutaSalida,lstReporteProgramaV);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del reporte de pagos por programa-comprador, debido a: "+e.getCause());
		}finally{
			lstReporteProgramaV = rDAO.insolatedConsultaReporteProgramaCompradorPagos(fechaInicioS, fechaFinS);
		}		
		return "SUCCESS";
	}

	private String contruyeArchivoReportePrograma(String rutaPlantilla, String rutaSalida, List<ReporteProgramaCompradorV> lst){
		String xlsOut = new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+"-REPORTEPROGRAMAPAGOS.xls";
		Integer sumaTotalPagosTramitados = 0, sumaTotalPagosPagados = 0, sumaTotalPagosRechazados = 0, sumaTotalPagosPendientes = 0;
		Double sumaTotalImporteTramitado = 0.0, sumaTotalVolumenTramitado = 0.0, sumaTotalImportePagado = 0.0, sumaTotalVolumenPagado = 0.0, sumaTotalImporteRechazado = 0.0, sumaTotalVolumenRechazado = 0.0, sumaTotalImportePendiente = 0.0, sumaTotalVolumenPendiente = 0.0;
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPORTEP.xls";
		if(!rutaSalida.endsWith(File.separator)){
			rutaSalida += File.separator;
		}
				
		Workbook workbook = null;
		WritableWorkbook copy = null;
		try{
			// Abrir plantilla
			workbook = Workbook.getWorkbook(new File(rutaPlantilla));
			// Hacer copia
			
			copy = Workbook.createWorkbook(new File(rutaSalida+xlsOut), workbook);
			WritableSheet sheet = copy.getSheet(0); 
			// Escribir datos
			
			WritableCellFormat cf = new WritableCellFormat();
			cf.setBorder( Border.ALL , BorderLineStyle.THIN );

			int row = 2;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				ReporteProgramaCompradorV p = lst.get(i);
		        // Programa
				sheet.addCell( new Label(col++,row, p.getPrograma(), cf));
				// Comprador
				sheet.addCell( new Label(col++,row, p.getComprador(), cf));
				// Tramitados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosTramitados()), cf));
				sumaTotalPagosTramitados+=p.getPagosTramitados();
				// Volumen Tramitados
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenTramitado()), cf));
		        sumaTotalVolumenTramitado+=p.getVolumenTramitado();
				// Importe Tramitados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImporteTramitado()), cf));
				sumaTotalImporteTramitado+=p.getImporteTramitado();
				// Pagados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosPagados()), cf));
				sumaTotalPagosPagados+=p.getPagosPagados();
				// Volumen Pagado
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenPagado()), cf));
		        sumaTotalVolumenPagado+=p.getVolumenPagado();
				// Importe Pagados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImportePagado()), cf));
				sumaTotalImportePagado+=p.getImportePagado();
				// Rechazados
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosRechazados()), cf));
				sumaTotalPagosRechazados+=p.getPagosRechazados();
				// Volumen Rechazado
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenRechazado()), cf));
		        sumaTotalVolumenRechazado+=p.getVolumenRechazado();
				// Importe Rechazado
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImporteRechazado()), cf));
				sumaTotalImporteRechazado+=p.getImporteRechazado();
				// Pendientes
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoCantidadSincomas(p.getPagosPendientes()), cf));
				sumaTotalPagosPendientes+=p.getPagosPendientes();
				// Volumen Pendiente
		        sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getVolumenPendiente()), cf));
		        sumaTotalVolumenPendiente+=p.getVolumenPendiente();
				// Importe Pendientes
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getImportePendiente()), cf));
				sumaTotalImportePendiente+=p.getImportePendiente();
				row++;
			}
			sheet.addCell( new Label(1,row, "TOTALES:", cf));
			// Totales Tramitados
			sheet.addCell( new Label(2,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosTramitados), cf));
			// Totales Volumen Tramitado
	        sheet.addCell( new Label(3,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenTramitado), cf));
			// Totales Importe Tramitado
			sheet.addCell( new Label(4,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImporteTramitado), cf));
			// Totales Pagados
			sheet.addCell( new Label(5,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosPagados), cf));
			// Totales Volumen Pagado
	        sheet.addCell( new Label(6,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenPagado), cf));
			// Totales Importe Pagado
			sheet.addCell( new Label(7,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImportePagado), cf));
			// Totales Rechazados
			sheet.addCell( new Label(8,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosRechazados), cf));
			// Totales Volumen Rechazado
	        sheet.addCell( new Label(9,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenRechazado), cf));
			// Totales Importe Rechazado
			sheet.addCell( new Label(10,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImporteRechazado), cf));
			// Totales Pendientes
			sheet.addCell( new Label(11,row, TextUtil.formateaNumeroComoCantidadSincomas(sumaTotalPagosPendientes), cf));
			// Totales Volumen Pendiente
	        sheet.addCell( new Label(12,row, TextUtil.formateaNumeroComoVolumenSinComas(sumaTotalVolumenPendiente), cf));
			// Totales Importe Pendiente
			sheet.addCell( new Label(13,row, TextUtil.formateaNumeroComoImporteSinComas(sumaTotalImportePendiente), cf));
	         			
		}catch(Exception e){
			System.err.println( e.getMessage() );
		}finally{
			// Cerrar y guardar copia
			if(copy!=null){
				try{
					copy.write(); 
					copy.close();
				}catch(Exception e){
					System.out.println( e.getMessage() );
				}
			}
			// Cerrar plantilla
			if(workbook!=null){
				workbook.close();
			}
		}
		setXls(xlsOut);
		return xlsOut;
	}

	public String consigueArchivoExcelRepP() throws Exception{
		try{
			String rutaSalida = cDAO.consultaParametros("RUTA_PLANTILLA_REPORTES");	
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreArchivo,"xls");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Map<String, Object> getSession() {
	    return session;
	}
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}

	public void setLstReporteGlobalV(List<ReporteGlobalV> lstReporteGlobalV) {
		this.lstReporteGlobalV = lstReporteGlobalV;
	}

	public List<ReporteGlobalV> getLstReporteGlobalV() {
		return lstReporteGlobalV;
	}

	public void setLstReporteProgramaV(List<ReporteProgramaCompradorV> lstReporteProgramaV) {
		this.lstReporteProgramaV = lstReporteProgramaV;
	}

	public List<ReporteProgramaCompradorV> getLstReporteProgramaV() {
		return lstReporteProgramaV;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public void setXls(String xls) {
		this.xls = xls;
	}

	public String getXls() {
		return xls;
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

	public void setTotalPagosTramitados(Double totalPagosTramitados) {
		this.totalPagosTramitados = totalPagosTramitados;
	}

	public Double getTotalPagosTramitados() {
		return totalPagosTramitados;
	}

	public Double getTotalVolumenTramitados() {
		return totalVolumenTramitados;
	}

	public void setTotalVolumenTramitados(Double totalVolumenTramitados) {
		this.totalVolumenTramitados = totalVolumenTramitados;
	}

	public Double getTotalImporteTramitados() {
		return totalImporteTramitados;
	}

	public void setTotalImporteTramitados(Double totalImporteTramitados) {
		this.totalImporteTramitados = totalImporteTramitados;
	}

	public Double getTotalPagosPagados() {
		return totalPagosPagados;
	}

	public void setTotalPagosPagados(Double totalPagosPagados) {
		this.totalPagosPagados = totalPagosPagados;
	}

	public Double getTotalVolumenPagados() {
		return totalVolumenPagados;
	}

	public void setTotalVolumenPagados(Double totalVolumenPagados) {
		this.totalVolumenPagados = totalVolumenPagados;
	}

	public Double getTotalImportePagados() {
		return totalImportePagados;
	}

	public void setTotalImportePagados(Double totalImportePagados) {
		this.totalImportePagados = totalImportePagados;
	}

	public Double getTotalPagosRechazados() {
		return totalPagosRechazados;
	}

	public void setTotalPagosRechazados(Double totalPagosRechazados) {
		this.totalPagosRechazados = totalPagosRechazados;
	}

	public Double getTotalVolumenRechazados() {
		return totalVolumenRechazados;
	}

	public void setTotalVolumenRechazados(Double totalVolumenRechazados) {
		this.totalVolumenRechazados = totalVolumenRechazados;
	}

	public Double getTotalImporteRechazados() {
		return totalImporteRechazados;
	}

	public void setTotalImporteRechazados(Double totalImporteRechazados) {
		this.totalImporteRechazados = totalImporteRechazados;
	}

	public Double getTotalPagosPendientes() {
		return totalPagosPendientes;
	}

	public void setTotalPagosPendientes(Double totalPagosPendientes) {
		this.totalPagosPendientes = totalPagosPendientes;
	}

	public Double getTotalVolumenPendientes() {
		return totalVolumenPendientes;
	}

	public void setTotalVolumenPendientes(Double totalVolumenPendientes) {
		this.totalVolumenPendientes = totalVolumenPendientes;
	}

	public Double getTotalImportePendientes() {
		return totalImportePendientes;
	}

	public void setTotalImportePendientes(Double totalImportePendientes) {
		this.totalImportePendientes = totalImportePendientes;
	}
}
