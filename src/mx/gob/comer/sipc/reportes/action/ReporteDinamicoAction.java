package mx.gob.comer.sipc.reportes.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.ReportesDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Opciones;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Variedad;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.BodegasV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
*ReporteDinamicoAction.
*Acciones para generar un reporte dinamico, as� como las acciones para realizar la paginaci�n de los resultados
*
*Versi�n 1.0
*
*Marzo 2013
*
*/
@SuppressWarnings("serial")
public class ReporteDinamicoAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private int tipoReporte;
	private int tramitado;
	private int registrado;
	private int autorizado;
	private int solicitado;
	private int rechazado;
	private int pendiente;
	private int pagado;
	private int programa;
	private int participante;
	private int oficio;
	private int fechaPago;
	private int estado;
	private int cultivo;
	private int variedad;
	private int bodega;
		
	/*paginador*/
	private int inicio;
	private int pagina;
	private final Integer numRegistros = 40;
	private List<Opciones> lstTotalPaginas;
	private String encabezado;
	private StringBuilder registroDetalle;
	
	private ReportesDAO rDAO = new ReportesDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	private CatalogosDAO cDAO = new CatalogosDAO();
	private List<Opciones> lstOpciones;
	private String selectCriterios;
	private String selectAgrupados;
	private String[] idCriterios;
	private String[] agrupados;
	private Date fechaInicio;
	private Date fechaFin;
	private String fechaInicioS;
	private String fechaFinS;
	@SuppressWarnings("rawtypes")
	private List lstReporte;
	private Integer totalRegistros;
	private int posicionSiguiente;
	private String encabezadoReporte;
	private List<Programa> lstProgramas;
	private List<Cultivo> lstCultivos;
	private List<Variedad> lstVariedad;
	private List<BodegasV> lstBodega;
	private Integer idPrograma;
	private Integer idCultivo;
	private Integer idVariedad;
	private Integer idBodega;
	
	
	/**
	 * Accion que llena la lista de opciones para el tipo de reporte "Por fecha de deposito".
	 * Por default aparecer� esta opci�n en el jsp "capturaReporteDinamico.jsp" que retorna 
	 * la accion
	 *  
	 * @throws Exception   Error al consultar el cat�logo de centrales
	 */
	public String capturaReporteDinamico(){
		try{
			lstOpciones = new ArrayList<Opciones>();
			lstOpciones.add(new Opciones(1,"Programa",""));
			lstOpciones.add(new Opciones(2,"Participante",""));
			lstOpciones.add(new Opciones(3,"Oficio",""));
			lstOpciones.add(new Opciones(4,"Fecha Pago",""));
			lstOpciones.add(new Opciones(5,"Estado",""));
			lstOpciones.add(new Opciones(6,"Cultivo",""));
			lstOpciones.add(new Opciones(7,"Variedad",""));			
			lstOpciones.add(new Opciones(8,"Bodega",""));

			/*Recupera catalogos de programas, cultivos, variedades y bodegas*/
			lstProgramas = cDAO.consultaPrograma(0);
			lstCultivos = cDAO.consultaCultivo();
			lstVariedad = new ArrayList<Variedad>();
			lstBodega = cDAO.consultaBodegasV("");
			//lstVariedad = cDAO.consultaVariedad();
		}catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurri� un error en metodo capturaReporteDinamico debido a: "+e.getCause());
	    }
		return SUCCESS;
	}
	
	
	/**
	 * Tipo de accion:AJAX
	 * Accion que llena la lista de opciones de acuerdo al tipo de reporte seleccionado por el usuario.
	 * Retorna al jsp seleccionCriterios.jsp
	 * 
	 *  
	 * @throws Exception   Error inesperado al llenar la lista de opciones
	 */
	public String seleccionCriterios(){
		try{
			lstOpciones = new ArrayList<Opciones>();
			if(tipoReporte==0){
				lstOpciones.add(new Opciones(1,"Programa",""));
				lstOpciones.add(new Opciones(2,"Participante",""));
				lstOpciones.add(new Opciones(3,"Oficio",""));
				lstOpciones.add(new Opciones(4,"Fecha Pago",""));
				lstOpciones.add(new Opciones(5,"Estado",""));
				lstOpciones.add(new Opciones(6,"Cultivo",""));
				lstOpciones.add(new Opciones(7,"Variedad",""));
				lstOpciones.add(new Opciones(8,"Bodega",""));
			}else if(tipoReporte==1){
				lstOpciones.add(new Opciones(1,"Programa",""));
				lstOpciones.add(new Opciones(2,"Participante",""));
				lstOpciones.add(new Opciones(3,"Oficio",""));
				lstOpciones.add(new Opciones(4,"Estado",""));
				lstOpciones.add(new Opciones(5,"Cultivo",""));
				lstOpciones.add(new Opciones(6,"Variedad",""));	
				lstOpciones.add(new Opciones(7,"Bodega",""));			
			}
			/*Recupera catalogos de programas, cultivos, variedades y bodegas*/
			lstProgramas = cDAO.consultaPrograma(0);
			lstCultivos = cDAO.consultaCultivo();
			lstVariedad = new ArrayList<Variedad>();
			lstBodega = cDAO.consultaBodegasV("");
			//lstVariedad = cDAO.consultaVariedad();			
		}catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurri� un error al llenar la lista de opciones en metodo seleccionCriterios debido a: "+e.getCause());
	    }
		return SUCCESS;
	}
	
	/**
	 * Accion que genera un reporte de acuerdo al tipo de reporte y los criterios seleccionados por el usuario.
	 * Retorna al jsp resultadoReporteDinamico.jsp
	 * Tipo de Reporte: 0 - Por fecha de deposito 1 - Por oficio
	 * Criterios para generaci�n: Programa, Participante, Oficio y FechaPago(Solo para tipo reporte 0),
	 * para el tipo de reporte tambien es posible seleccionar por; registrado, autorizado, solicitado, pagado, no efectuado y sin respuesta TESOFE
	 * 
	 *  
	 * @throws JDBCException Error al ejecutar la consulta en la base de datos con los criterios seleccionados por el usuario
	 * @throws Exception Error inesperado al generar el reporte
	 */
	public String generarReporteDinamico(){
		try{	
			fechaInicioS="";
			fechaFinS="";
			inicio = 0;
			pagina = 0;
			//Consigue la variable de la session
			session = ActionContext.getContext().getSession();
			//idCriterios es el arreglo de los criterios seleccionados por el usuario*/
			idCriterios = selectCriterios.split(",");
			//agrupados es el arreglo de la agrupaci�n de los criterios seleccionados por el usuario*/
			agrupados = selectAgrupados.split(",");
			//Sube a la session del usuario los arreglos de los criterios seleccionados
			session.put("idCriterios", idCriterios);
			session.put("agrupados", agrupados);
			if(fechaInicio != null && !fechaInicio.equals("")){
				fechaInicioS = new SimpleDateFormat("dd-MM-yyyy").format(fechaInicio).toString();
			}
			if(fechaFin != null && !fechaFin.equals("")){
				fechaFinS = new SimpleDateFormat("dd-MM-yyyy").format(fechaFin).toString();
			}
			session.put("fechaInicioS", fechaInicioS);
			session.put("fechaFinS", fechaFinS);
			//Asigna identificadores a los criterios de la seleccion
			int idCriterio = 0;
			for(int i=0; i<idCriterios.length; i++){
				idCriterio = Integer.parseInt(idCriterios[i]);
				if(tipoReporte==0){
					if(idCriterio==1){
						programa = idCriterio;
					}else if(idCriterio==2){
						participante = idCriterio;
					}else if(idCriterio==3){
						oficio = idCriterio;
					}else if(idCriterio==4){
						fechaPago = idCriterio;
					}else if(idCriterio==5){
						estado = idCriterio;
					}else if(idCriterio==6){
						cultivo = idCriterio;
					}else if(idCriterio==7){
						variedad = idCriterio;
					}else if(idCriterio==8){
						bodega = idCriterio;
					}					
				} else {
					if(idCriterio==1){
						programa = idCriterio;
					}else if(idCriterio==2){
						participante = idCriterio;
					}else if(idCriterio==3){
						oficio = idCriterio;
					}else if(idCriterio==4){
						estado = idCriterio;
					}else if(idCriterio==5){
						cultivo = idCriterio;
					}else if(idCriterio==6){
						variedad = idCriterio;
					}else if(idCriterio==7){
						bodega = idCriterio;
					}
				}
			}
			//sube los criterios a session, para posteriormente ocuparlos en la paginacion y exportacion a excel
			session.put("programa", programa);
			session.put("participante", participante);
			session.put("oficio", oficio);
			session.put("fechaPago", fechaPago);
			session.put("estado", estado);
			session.put("tipoReporte", tipoReporte);
			session.put("idPrograma", idPrograma);
			session.put("cultivo", cultivo);
			session.put("idCultivo", idCultivo);
			session.put("variedad", variedad);
			session.put("idVariedad", idVariedad);
			session.put("bodega", bodega);
			session.put("idBodega", idBodega);
			
			if(tipoReporte==0){
				lstReporte=rDAO.consultaReporteDinamico(tipoReporte,idCriterios, agrupados, 1, 0, 0, 0, fechaInicioS, fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
				if(lstReporte.size()>0){
					pagina=1;
					paginar();					
				}
			}else if(tipoReporte==1){
				lstReporte=rDAO.consultaReporteDinamico(tipoReporte,idCriterios, agrupados, registrado, autorizado, solicitado, pagado, rechazado, pendiente,0,0,0,fechaInicioS,fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
				if(lstReporte.size()>0){
					pagina=1;
					paginar();
					//session.put("tramitado", tramitado);
					session.put("registrado", registrado);
					session.put("autorizado", autorizado);
					session.put("solicitado", solicitado);
					session.put("pagado", pagado);
					session.put("rechazado", rechazado);
					session.put("pendiente", pendiente);
				}
			}
			generarEncabezado();
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurri� un error al generar el reporte dinamico debido a: "+e.getCause());
			addActionError("No fue posible ejecutar la consulta, favor de informar al administrador ");
	    }catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurri� un error al generar el reporte dinamico debido a: "+e.getCause());
			addActionError("Ocurri� un error inesperado, favor de reportar al administrador ");
	    }
		return SUCCESS;
	}
	
	/**
	 * 
	 * Accion que pagina los resultados de reportes que consulta el usuario       
	 * Retorna al jsp resultadoReporteDinamico.jsps
	 *
	 * @throws Exception Error inesperado al paginar los resultados de la consulta del reporte
	 */
	@SuppressWarnings("unchecked")
	public String irPaginando() throws Exception{	
		session = ActionContext.getContext().getSession();
		tipoReporte = (Integer) session.get("tipoReporte");
		lstTotalPaginas = (List<Opciones>) session.get("lstTotalPaginas");
		idCriterios = (String []) session.get("idCriterios");
		agrupados = (String[]) session.get("agrupados");
		fechaInicioS = (String) session.get("fechaInicioS");
		fechaFinS = (String) session.get("fechaFinS");
		agrupados = (String[]) session.get("agrupados");
		inicio= (pagina-1)*numRegistros.intValue();
		if(tipoReporte==0){			
			lstReporte=rDAO.consultaReporteDinamico(0,(String []) session.get("idCriterios"), (String[]) session.get("agrupados"), 1, inicio, numRegistros, pagina, fechaInicioS, fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
			generarRegistroDetalle();
			
		}else{
			//tramitado = (Integer)session.get("tramitado");
			registrado = (Integer)session.get("registrado");
			autorizado = (Integer)session.get("autorizado");
			solicitado = (Integer)session.get("solicitado");
			pagado    = (Integer)session.get("pagado");
			rechazado = (Integer)session.get("rechazado");
			pendiente = (Integer)session.get("pendiente");
			lstReporte=rDAO.consultaReporteDinamico(tipoReporte,(String []) session.get("idCriterios"), (String[]) session.get("agrupados"), registrado, autorizado, solicitado, pagado, rechazado, pendiente,inicio,numRegistros,pagina, fechaInicioS,fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
			generarRegistroDetalle();
			
		}
		generarEncabezado();
		return SUCCESS;
	}
	
	/**
	 * Accion que exporta los resultados de reportes que consulta el usuario       
	 * 
	 * @throws Exception Error inesperado al exportar la informaci�n de los resultados de la consulta del reporte
	 */
	public String exportarExcelReporteDinamico()throws Exception{
		try{
			/*Consigue la ruta donde depositara el reporte*/
			String rutaSalida = uDAO.getParametros("RUTA_PLANTILLA_REPORTES");
			String nombreArchivo = "repoDinamico.xls";
			session = ActionContext.getContext().getSession();
			programa = (Integer)session.get("programa");
			participante = (Integer)session.get("participante");
			oficio = (Integer)session.get("oficio");
			fechaPago = (Integer)session.get("fechaPago");
			estado = (Integer)session.get("estado");
			cultivo = (Integer)session.get("cultivo");
			variedad = (Integer)session.get("variedad");
			idPrograma = (Integer)session.get("idPrograma");
			idCultivo = (Integer)session.get("idCultivo");
			idVariedad = (Integer)session.get("idVariedad");
			idBodega = (Integer)session.get("idBodega");

			tipoReporte = (Integer)session.get("tipoReporte");
			idCriterios = (String []) session.get("idCriterios");
			agrupados = (String[]) session.get("agrupados");
			fechaInicioS = (String) session.get("fechaInicioS");
			fechaFinS = (String) session.get("fechaFinS");
			if(tipoReporte==0){
				lstReporte=rDAO.consultaReporteDinamico(tipoReporte,idCriterios, agrupados, 1, 0, 0, 0, fechaInicioS,fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
			}else if(tipoReporte==1){
				//tramitado = (Integer)session.get("tramitado");
				registrado = (Integer)session.get("registrado");
				autorizado = (Integer)session.get("autorizado");
				solicitado = (Integer)session.get("solicitado");				
				pagado = (Integer)session.get("pagado");
				rechazado = (Integer)session.get("rechazado");
				pendiente = (Integer)session.get("pendiente");
				lstReporte=rDAO.consultaReporteDinamico(tipoReporte,idCriterios, agrupados, registrado, autorizado, solicitado, pagado, rechazado, pendiente,0,0,0, fechaInicioS, fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
							
			}
			construyeArchivoExcel(rutaSalida, nombreArchivo);
			Utilerias.entregarArchivo(rutaSalida, nombreArchivo, "xls");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Exporta la informaci�n de los resultado en un archivo con formato "XLS"     
	 * 
	 * @throws Exception Error inesperado al exportar la informaci�n de los resultados de la consulta del reporte
	 */
	@SuppressWarnings("rawtypes")
	private void construyeArchivoExcel(String rutaSalida, String nombreArchivo) throws Exception{
		/*Colocar encabezados*/
		generarEncabezado();
		String [] encRepArray =encabezadoReporte.split(",");
		WritableWorkbook workbook = null;
		try {
			int i=0;
			workbook = Workbook.createWorkbook(new File(rutaSalida+nombreArchivo));
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			WritableCellFormat cf1 = new WritableCellFormat(wf1);
			cf1.setAlignment(Alignment.CENTRE);
			cf1.setWrap(true);
			for(i=0; i<encRepArray.length; i++){
				sheet.addCell(new Label(i, 0, encRepArray[i], cf1)); /*1 Columna, 2 Fila*/
			}
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
			WritableCellFormat cf2 = new WritableCellFormat(wf2);
			cf2.setAlignment(Alignment.LEFT);
			cf2.setWrap(true);
			String[] posicion=posicionar().split(",");
			Iterator ite = lstReporte.iterator();
			i=1;
			int j = 0;
			int temporal = posicionSiguiente;
			//int totalPagosTramitados = 0, totalPagosPagados = 0, totalPagosRechazados = 0, totalPagosPendientes = 0;
			int totalPagosRegistrados = 0, totalPagosAutorizados = 0, totalPagosSolicitados = 0, totalPagosPagados = 0, totalPagosRechazados = 0, totalPagosPendientes = 0;
			//int posicionAplicados =0, posicionTramitados=0, posicionRechazados=0, posicionPendiente=0;
			int posicionAplicados =0, posicionRegistrados=0, posicionAutorizados=0, posicionSolicitados=0, posicionRechazados=0, posicionPendiente=0;
			//double totalVolumenTramitados=0, totalImporteTramitados=0, totalVolumenAplicados=0, totalImporteAplicados=0, totalVolumenRechazados=0, totalImporteRechazados=0, totalVolumenPendiente=0, totalImportePendiente=0; 
			double totalVolumenRegistrados=0, totalImporteRegistrados=0,
					totalVolumenAutorizados=0, totalImporteAutorizados=0,
					totalVolumenSolicitados=0, totalImporteSolicitados=0,
					totalVolumenAplicados=0, totalImporteAplicados=0, 
					totalVolumenRechazados=0, totalImporteRechazados=0, 
					totalVolumenPendiente=0, totalImportePendiente=0;
			while (ite.hasNext()) {
				temporal = posicionSiguiente;
				Object[] registro = (Object[]) ite.next();
				for(j=0; j<posicion.length; j++){
					try{
						sheet.addCell(new Label(j, i, ((String)registro[Integer.parseInt(posicion[j])]), cf2));
					}catch (Exception e) {	
						AppLogger.error("errores","El dato "+registro[Integer.parseInt(posicion[j])]+", no se pudo convertir a tipo String");
						try{
							sheet.addCell(new Label(j, i, new SimpleDateFormat("yyyy-MM-dd").format((Date)registro[Integer.parseInt(posicion[j])]).toString(), cf2));
						}catch (Exception e1) {	
							e1.printStackTrace();
							AppLogger.error("errores","El dato "+registro[Integer.parseInt(posicion[j])]+", no se pudo convertir a tipo Date");
							try{
								sheet.addCell(new Label(j, i, registro[Integer.parseInt(posicion[j])].toString(), cf2));
							}catch(Exception e2){
								
							}
						}
					}
					
				}
				if(tipoReporte==0){
					sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
					posicionAplicados=j-1;
					totalPagosPagados += ((BigInteger) registro[(temporal)]).intValue();
					sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
					totalVolumenAplicados+=((BigDecimal)registro[(temporal)]).doubleValue();
					sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
					totalImporteAplicados += ((BigDecimal) registro[(temporal)]).doubleValue();
					
				}else if(tipoReporte==1){
/*
					if(tramitado!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionTramitados=j-1;
						totalPagosTramitados += ((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenTramitados += ((BigDecimal) registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImporteTramitados += ((BigDecimal) registro[(temporal)]).doubleValue();
					}
*/					
					if(registrado!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionRegistrados=j-1;
						totalPagosRegistrados += ((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenRegistrados += ((BigDecimal) registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImporteRegistrados += ((BigDecimal) registro[(temporal)]).doubleValue();
					}
					if(autorizado!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionAutorizados=j-1;
						totalPagosAutorizados += ((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenAutorizados += ((BigDecimal) registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImporteAutorizados += ((BigDecimal) registro[(temporal)]).doubleValue();
					}
					if(solicitado!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionSolicitados=j-1;
						totalPagosSolicitados += ((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenSolicitados += ((BigDecimal) registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImporteSolicitados += ((BigDecimal) registro[(temporal)]).doubleValue();
					}

					if(pagado!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionAplicados=j-1;
						totalPagosPagados += ((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenAplicados+=((BigDecimal)registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImporteAplicados += ((BigDecimal) registro[(temporal)]).doubleValue();
					}
					if(rechazado!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionRechazados = j-1;
						totalPagosRechazados +=((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenRechazados += ((BigDecimal)registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImporteRechazados += ((BigDecimal)registro[(temporal)]).doubleValue();
					}
					if(pendiente!=0){
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoCantidadSinComas((BigInteger) registro[(temporal+=1)]), cf2));
						posicionPendiente = j-1;
						totalPagosPendientes += ((BigInteger) registro[(temporal)]).intValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalVolumenPendiente += ((BigDecimal)registro[(temporal)]).doubleValue();
						sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas((BigDecimal) registro[(temporal+=1)]), cf2));
						totalImportePendiente += ((BigDecimal)registro[(temporal)]).doubleValue();
					}
				}
				i++;
			}	
			
			/*Totales*/
			sheet.addCell(new Label(0, i, "Totales", cf1));
			if(tipoReporte==0){
				sheet.addCell(new Label(posicionAplicados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosPagados), cf2));
				sheet.addCell(new Label(posicionAplicados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenAplicados), cf2));
				sheet.addCell(new Label(posicionAplicados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteAplicados), cf2));
			}else{
/*
				if(tramitado!=0){
					sheet.addCell(new Label(posicionTramitados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosTramitados), cf2));
					sheet.addCell(new Label(posicionTramitados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenTramitados), cf2));
					sheet.addCell(new Label(posicionTramitados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteTramitados), cf2));
				}
*/				
				if(registrado!=0){
					sheet.addCell(new Label(posicionRegistrados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosRegistrados), cf2));
					sheet.addCell(new Label(posicionRegistrados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenRegistrados), cf2));
					sheet.addCell(new Label(posicionRegistrados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteRegistrados), cf2));
				}
				if(autorizado!=0){
					sheet.addCell(new Label(posicionAutorizados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosAutorizados), cf2));
					sheet.addCell(new Label(posicionAutorizados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenAutorizados), cf2));
					sheet.addCell(new Label(posicionAutorizados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteAutorizados), cf2));
				}
				if(solicitado!=0){
					sheet.addCell(new Label(posicionSolicitados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosSolicitados), cf2));
					sheet.addCell(new Label(posicionSolicitados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenSolicitados), cf2));
					sheet.addCell(new Label(posicionSolicitados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteSolicitados), cf2));
				}

				if(pagado!=0){
					sheet.addCell(new Label(posicionAplicados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosPagados), cf2));
					sheet.addCell(new Label(posicionAplicados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenAplicados), cf2));
					sheet.addCell(new Label(posicionAplicados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteAplicados), cf2));
				}
				if(rechazado!=0){
					sheet.addCell(new Label(posicionRechazados++, i, TextUtil.formateaNumeroComoCantidad(totalPagosRechazados), cf2));
					sheet.addCell(new Label(posicionRechazados++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenRechazados), cf2));
					sheet.addCell(new Label(posicionRechazados++, i, TextUtil.formateaNumeroComoCantidad(totalImporteRechazados), cf2));
				}
				if(pendiente!=0){
					sheet.addCell(new Label(posicionPendiente++, i, TextUtil.formateaNumeroComoCantidad(totalPagosPendientes), cf2));
					sheet.addCell(new Label(posicionPendiente++, i, TextUtil.formateaNumeroComoVolumen(totalVolumenPendiente), cf2));
					sheet.addCell(new Label(posicionPendiente++, i, TextUtil.formateaNumeroComoCantidad(totalImportePendiente), cf2));
				}
			}
			
			workbook.write();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally{
			if (workbook != null){
				try {
					workbook.close();
				} catch (WriteException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Accion que construye el paginador 
	 * 
	 * @throws Exception Error inesperado al exportar la informaci�n de los resultados de la consulta del reporte
	 */
	private void paginar()  throws Exception{
		// cuenta el total de registros a trav�s de la consulta ejecutada por los criterios seleccionados
		totalRegistros =lstReporte.size();
		session.put("totalRegistros", totalRegistros);
		//Cuenta el total de paginas de acuerdo al total de registros / numero registros
		Double totalPaginas = Math.ceil(totalRegistros.doubleValue()/numRegistros.doubleValue());
		lstTotalPaginas = new ArrayList<Opciones>();
		for(int i=1; i<=totalPaginas.intValue(); i++){
			lstTotalPaginas.add(new Opciones());
		}
		session.put("lstTotalPaginas", lstTotalPaginas);
		if(tipoReporte==0){
			lstReporte=rDAO.consultaReporteDinamico(tipoReporte,idCriterios, agrupados, 1, inicio, numRegistros, pagina, fechaInicioS, fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
			generarRegistroDetalle();
		}else{
			lstReporte=rDAO.consultaReporteDinamico(tipoReporte,idCriterios, agrupados, registrado, autorizado, solicitado, pagado, rechazado, pendiente,inicio,numRegistros,pagina, fechaInicioS, fechaFinS, idPrograma, idCultivo, idVariedad, idBodega);
			generarRegistroDetalle();
		}
		
		
	}
	/**
	 * Accion que construye los registros del detalle que se visuliza en el jsp de "resultadoReporteDinamico.jsp" 
	 * 
	 * @throws Exception Error inesperado al generar el detalle de los registros de la consulta del reporte
	 */
	private void generarRegistroDetalle() throws Exception {
		@SuppressWarnings("rawtypes")
		Iterator j = lstReporte.iterator();
		registroDetalle = new StringBuilder();
		String [] posicion=posicionar().split(",");
		int temporal = posicionSiguiente;
		while (j.hasNext()) {
			temporal = posicionSiguiente;
			Object[] registro = (Object[]) j.next();
			registroDetalle.append("<tr>");
			for(int i=0; i<posicion.length; i++){
				registroDetalle.append("<td>").append(registro[Integer.parseInt(posicion[i])]).append("</td>");
			}
			if(tipoReporte==0){
				registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
				registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
				registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
			}else if(tipoReporte==1){
/*
				if(tramitado!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}
*/				
				if(registrado!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}
				if(autorizado!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}
				if(solicitado!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}

				if(pagado!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}
				if(rechazado!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}
				if(pendiente!=0){
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoCantidad((BigInteger) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoVolumen((BigDecimal) registro[(temporal+=1)])).append("</td>");
					registroDetalle.append("<td>").append(TextUtil.formateaNumeroComoImporte((BigDecimal) registro[(temporal+=1)])).append("</td>");
				}
			}
			registroDetalle.append("</tr>");
			
		}
		
	}

	/**
	 * Accion que determina las posiciones de los registros, que retorna la consulta a la base de datos  
	 * para conocer las columnas de acuerdo a los criterios seleccionados por el usuario
	 * 
	 * @throws Exception Error inesperado al consultar las posiciones de las columnas
	 */
	private String  posicionar()  throws Exception{
		String [] configuracionRegistros = new String [idCriterios.length];
		int programa =0;
		int agrupacionPrograma = 0;
		int participante =0;
		int agrupacionParticipante = 0;
		int oficio=0;
		int agrupacionOficio = 0;
		int fechaPago=0;
		int agrupacionFechaPago = 0;
		int estado=0;
		int agrupacionEstado = 0;
		int cultivo=0;
		int agrupacionCultivo = 0;
		int variedad=0;
		int agrupacionVariedad = 0;
		int bodega=0;
		int agrupacionBodega = 0;

		int idCriterio = 0;
		for(int i=0; i<idCriterios.length; i++){
			idCriterio = Integer.parseInt(idCriterios[i]);
			if(tipoReporte==0){
				if(idCriterio==1){
					programa = idCriterio;
					agrupacionPrograma = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==2){
					participante = idCriterio;
					agrupacionParticipante = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==3){
					oficio = idCriterio;
					agrupacionOficio = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==4){
					fechaPago = idCriterio;
					agrupacionFechaPago = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==5){
					estado = idCriterio;
					agrupacionEstado = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==6){
					cultivo = idCriterio;
					agrupacionCultivo = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==7){
					variedad = idCriterio;
					agrupacionVariedad = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==8){
					bodega = idCriterio;
					agrupacionBodega = Integer.parseInt(agrupados[i]);
				}					
			} else {
				if(idCriterio==1){
					programa = idCriterio;
					agrupacionPrograma = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==2){
					participante = idCriterio;
					agrupacionParticipante = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==3){
					oficio = idCriterio;
					agrupacionOficio = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==4){
					estado = idCriterio;
					agrupacionEstado = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==5){
					cultivo = idCriterio;
					agrupacionCultivo = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==6){
					variedad = idCriterio;
					agrupacionVariedad = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==7){
					bodega = idCriterio;
					agrupacionBodega = Integer.parseInt(agrupados[i]);					
				}									
			}
		}
		
		if(programa != 0){
			configuracionRegistros[agrupacionPrograma]="1,1"; //posicion, numero de registros
		}

		if(participante != 0){
			configuracionRegistros[agrupacionParticipante]="1,2"; 
		}
		
		if(oficio != 0){
			configuracionRegistros[agrupacionOficio]="1,2";
		}
		
		if(fechaPago != 0){
			configuracionRegistros[agrupacionFechaPago]="0,1";
		}
		if(estado != 0){
			if(participante != 0){
				if(bodega != 0){
					configuracionRegistros[agrupacionEstado]="1,3";
				}else{
					configuracionRegistros[agrupacionEstado]="1,2"; //07072015
				}				
			} else {
				configuracionRegistros[agrupacionEstado]="1,1";
			}
		}
		if(cultivo != 0){
			configuracionRegistros[agrupacionCultivo]="1,1";
		}
		if(variedad != 0){
			configuracionRegistros[agrupacionVariedad]="1,1";
		}
		if(bodega != 0){
			configuracionRegistros[agrupacionBodega]="1,1";
		}

		String guardaPosicion ="";
		int posicion=0;
		for(int i=0; i<configuracionRegistros.length; i++){
			String[] parametros = configuracionRegistros[i].split(",");
			if(i==0){
				posicion += Integer.parseInt(parametros[0]);	
			}else{
				posicion += Integer.parseInt(parametros[0])+1;
			}
			guardaPosicion += posicion+",";
			for(int j=1; j< Integer.parseInt(parametros[1]); j++){
				posicion += 1;
				guardaPosicion += posicion+",";
			}
		}
		posicionSiguiente = posicion;
		
		return guardaPosicion;
	}


	/**
	 * Accion que genera el encabezado del reporte dinamico de acuerdo a los criterios seleccionados por el usuario
	 * 
	 * @throws Exception Error inesperado al generar los encabezados del reporte dinamico
	 */
	private void generarEncabezado() throws Exception{
		encabezado="";
		encabezadoReporte="";
		String [] ordenados = new String [idCriterios.length];
		String [] ordenadosReporte = new String [idCriterios.length];
		int agrupacionPrograma = 0;
		int agrupacionParticipante = 0;
		int agrupacionOficio = 0;
		int agrupacionFechaPago = 0;
		int agrupacionEstado = 0;
		int agrupacionCultivo = 0;
		int agrupacionVariedad = 0;
		int agrupacionBodega = 0;
		int idCriterio = 0;
		for(int i=0; i<idCriterios.length; i++){
			idCriterio = Integer.parseInt(idCriterios[i]);
			if(tipoReporte==0){
				if(idCriterio==1){
					programa = idCriterio;
					agrupacionPrograma = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==2){
					participante = idCriterio;
					agrupacionParticipante = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==3){
					oficio = idCriterio;
					agrupacionOficio = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==4){
					fechaPago = idCriterio;
					agrupacionFechaPago = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==5){
					estado = idCriterio;
					agrupacionEstado = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==6){
					cultivo = idCriterio;
					agrupacionCultivo = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==7){
					variedad = idCriterio;
					agrupacionVariedad = Integer.parseInt(agrupados[i]);					
				}else if(idCriterio==8){
					bodega = idCriterio;
					agrupacionBodega = Integer.parseInt(agrupados[i]);
				}
			} else {
				if(idCriterio==1){
					programa = idCriterio;
					agrupacionPrograma = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==2){
					participante = idCriterio;
					agrupacionParticipante = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==3){
					oficio = idCriterio;
					agrupacionOficio = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==4){
					estado = idCriterio;
					agrupacionEstado = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==5){
					cultivo = idCriterio;
					agrupacionCultivo = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==6){
					variedad = idCriterio;
					agrupacionVariedad = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==7){
					bodega = idCriterio;
					agrupacionBodega = Integer.parseInt(agrupados[i]);
				}				
			}
		}
		//dependiendo a los criterios seleccionados y el orden se colocan los encabezados
		if(programa != 0){
			ordenados[agrupacionPrograma]="<th>Programa</th>";
			ordenadosReporte[agrupacionPrograma]="Programa,";
		}
		if(participante != 0){
			ordenados[agrupacionParticipante]="<th>Participante</th><th>Carta</th>";
			ordenadosReporte[agrupacionParticipante]="Participante,Carta,";
		}
		if(oficio != 0){
			ordenados[agrupacionOficio]="<th>No. Oficio</th><th>Fecha Oficio</th>";
			ordenadosReporte[agrupacionOficio]="No. Oficio,Fecha Oficio,";
		}
		if(fechaPago != 0){
			ordenados[agrupacionFechaPago]="<th>Fecha Pago</th>";
			ordenadosReporte[agrupacionFechaPago]="Fecha Pago,";
		}
		if(estado != 0){
			if(participante != 0){
				if(bodega != 0){
					ordenados[agrupacionEstado]="<th>Estado</th><th>Municipio</th><th>Num Productores Bene</th>";
				}else{
					ordenados[agrupacionEstado]="<th>Estado</th><th>Municipio</th>";
				}				
				ordenadosReporte[agrupacionEstado]="Estado,Municipio,Num Prod Beneficiados,";
			} else {
				ordenados[agrupacionEstado]="<th>Estado</th>";
				ordenadosReporte[agrupacionEstado]="Estado,";
			}
		}
		if(cultivo != 0){
			ordenados[agrupacionCultivo]="<th>Cultivo</th>";
			ordenadosReporte[agrupacionCultivo]="Cultivo,";
		}
		if(variedad != 0){
			ordenados[agrupacionVariedad]="<th>Variedad</th>";
			ordenadosReporte[agrupacionVariedad]="Variedad,";
		}
		if(bodega != 0){
			ordenados[agrupacionBodega]="<th>Bodega</th>";
			ordenadosReporte[agrupacionBodega]="Bodega,";
		}

		for(int i=0; i<ordenados.length; i++){
			encabezado += ordenados[i];
			encabezadoReporte += ordenadosReporte[i];
		}
	
		if(tipoReporte==0){
			encabezado +="<th>Aplicados</th><th>Volumen Aplicado</th><th>Importe Aplicado</th>";
			encabezadoReporte += "Aplicados,Volumen Aplicado,Importe Aplicado,";
		}else if(tipoReporte==1){
/*
			if(tramitado!=0){
				encabezado +="<th>Tramitado</th><th>Volumen Tramitado</th><th>Importe Tramitado</th>";
				encabezadoReporte +="Tramitado,Volumen Tramitado,Importe Tramitado,";
			}
*/			
			if(registrado!=0){
				encabezado +="<th>Registrado</th><th>Volumen Registrado</th><th>Importe Registrado</th>";
				encabezadoReporte +="Registrado,Volumen Registrado,Importe Registrado,";
			}
			if(autorizado!=0){
				encabezado +="<th>Autorizado</th><th>Volumen Autorizado</th><th>Importe Autorizado</th>";
				encabezadoReporte +="Autorizado,Volumen Autorizado,Importe Autorizado,";
			}
			if(solicitado!=0){
				encabezado +="<th>Solicitado</th><th>Volumen Solicitado</th><th>Importe Solicitado</th>";
				encabezadoReporte +="Solicitado,Volumen Solicitado,Importe Solicitado,";
			}

			if(pagado!=0){
				encabezado +="<th>Aplicados</th><th>Volumen Aplicado</th><th>Importe Aplicado</th>";
				encabezadoReporte += "Aplicados,Volumen Aplicado,Importe Aplicado,";
			}
			if(rechazado!=0){
				encabezado +="<th>Rechazado</th><th>Volumen Rechazado</th><th>Importe Rechazado</th>";
				encabezadoReporte += "Rechazado,Volumen Rechazado,Importe Rechazado,";
			}
			if(pendiente!=0){
				encabezado +="<th>Pendiente</th><th>Volumen Pendiente</th><th>Importe Pendiente</th>";
				encabezadoReporte += "Pendiente,Volumen Pendiente,Importe Pendiente"; 
			}
		}
	}

	public String recuperaVariedadByCultivo(){
		//Recupera los datos de la variedad por cultivo
		lstVariedad = cDAO.consultaVariedad(-1, idCultivo, null);
		return SUCCESS;		
	}

	public int getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(int tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public int getTramitado() {
		return tramitado;
	}
	public void setTramitado(int tramitado) {
		this.tramitado = tramitado;
	}
	public int getRechazado() {
		return rechazado;
	}
	public void setRechazado(int rechazado) {
		this.rechazado = rechazado;
	}
	public int getPendiente() {
		return pendiente;
	}
	public void setPendiente(int pendiente) {
		this.pendiente = pendiente;
	}
	public int getPagado() {
		return pagado;
	}
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
	
	public List<Opciones> getLstOpciones() {
		return lstOpciones;
	}

	public void setLstOpciones(List<Opciones> lstOpciones) {
		this.lstOpciones = lstOpciones;
	}
	
	public String[] getIdCriterios() {
		return idCriterios;
	}

	public void setIdCriterios(String[] idCriterios) {
		this.idCriterios = idCriterios;
	}

	public String[] getAgrupados() {
		return agrupados;
	}
	public void setAgrupados(String[] agrupados) {
		this.agrupados = agrupados;
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

	@SuppressWarnings("rawtypes")
	public List getLstReporte() {
		return lstReporte;
	}
	@SuppressWarnings("rawtypes")
	public void setLstReporte(List lstReporte) {
		this.lstReporte = lstReporte;
	}
	public int getPrograma() {
		return programa;
	}
	public void setPrograma(int programa) {
		this.programa = programa;
	}
	public int getParticipante() {
		return participante;
	}
	public void setParticipante(int participante) {
		this.participante = participante;
	}
	public int getOficio() {
		return oficio;
	}
	public void setOficio(int oficio) {
		this.oficio = oficio;
	}
	public int getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(int fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}


	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public StringBuilder getRegistroDetalle() {
		return registroDetalle;
	}

	public void setRegistroDetalle(StringBuilder registroDetalle) {
		this.registroDetalle = registroDetalle;
	}

	public void setSelectCriterios(String selectCriterios) {
		this.selectCriterios = selectCriterios;
	}

	public String getSelectAgrupados() {
		return selectAgrupados;
	}

	public void setSelectAgrupados(String selectAgrupados) {
		this.selectAgrupados = selectAgrupados;
	}

	/*Variables del paginador*/
	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public List<Opciones> getLstTotalPaginas() {
		return lstTotalPaginas;
	}

	public void setLstTotalPaginas(List<Opciones> lstTotalPaginas) {
		this.lstTotalPaginas = lstTotalPaginas;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}


	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}


	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}


	public Integer getIdPrograma() {
		return idPrograma;
	}


	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}


	public List<Cultivo> getLstCultivos() {
		return lstCultivos;
	}


	public void setLstCultivos(List<Cultivo> lstCultivos) {
		this.lstCultivos = lstCultivos;
	}


	public List<Variedad> getLstVariedad() {
		return lstVariedad;
	}


	public void setLstVariedad(List<Variedad> lstVariedad) {
		this.lstVariedad = lstVariedad;
	}


	public Integer getIdCultivo() {
		return idCultivo;
	}


	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}


	public Integer getIdVariedad() {
		return idVariedad;
	}


	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}


	public int getCultivo() {
		return cultivo;
	}


	public void setCultivo(int cultivo) {
		this.cultivo = cultivo;
	}


	public int getVariedad() {
		return variedad;
	}


	public void setVariedad(int variedad) {
		this.variedad = variedad;
	}	

	public List<BodegasV> getLstBodega() {
		return lstBodega;
	}

	public void setLstBodega(List<BodegasV> lstBodega) {
		this.lstBodega = lstBodega;
	}

	public int getBodega() {
		return bodega;
	}

	public void setBodega(int bodega) {
		this.bodega = bodega;
	}

	public Integer getIdBodega() {
		return idBodega;
	}


	public void setIdBodega(Integer idBodega) {
		this.idBodega = idBodega;
	}


	public int getRegistrado() {
		return registrado;
	}


	public void setRegistrado(int registrado) {
		this.registrado = registrado;
	}


	public int getAutorizado() {
		return autorizado;
	}


	public void setAutorizado(int autorizado) {
		this.autorizado = autorizado;
	}


	public int getSolicitado() {
		return solicitado;
	}


	public void setSolicitado(int solicitado) {
		this.solicitado = solicitado;
	}
}
