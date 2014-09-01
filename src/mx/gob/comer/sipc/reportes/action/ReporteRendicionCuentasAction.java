package mx.gob.comer.sipc.reportes.action;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import jxl.CellType;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.ReportesDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.RendicionCuentasV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;

import com.lowagie.text.Document;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReporteRendicionCuentasAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{
	private Map<String, Object> session;
	private String cuadroSatisfactorio;	
	private CatalogosDAO cDAO;
	private ReportesDAO rDAO;
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private List<RendicionCuentasV> lstReporteRendicionCuentasCID;
	private List<RendicionCuentasV> lstReporteRendicionCuentasSID;
	private Date fechaInicio;
	private Date fechaFin;
	private String xls;
	
	private ServletContext context;
	private String rutaSalida;
	private String nombreReporte;
	private String rutaRaiz;
	private String rutaLogoS;
	private String rutaLogoA;
	
	private Integer idUsuario;
	
	public ReporteRendicionCuentasAction() {
		super();
		cDAO = new CatalogosDAO();
		rDAO = new ReportesDAO();
	}
	
	public String rendicionCuentasCID(){
		session = ActionContext.getContext().getSession();
		idUsuario = (Integer)session.get("idUsuario");
		
		return SUCCESS;
	}

	public String realizarConsultaRendicionCuentasCID(){
		try{	
			session = ActionContext.getContext().getSession();			
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario			
			lstReporteRendicionCuentasCID = rDAO.consultaReporteRendicionCuentasCID(fechaInicio, fechaFin);

			//subir a session los criterios que el usuario selecciono.
			session.put("fechaInicio", fechaInicio);
			session.put("fechaFin", fechaFin);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al realizar la consulta del reporte de rendicion de cuentas con ID, debido a: "+e.getCause());
		}finally{
		}
		
		return SUCCESS;
	}

	public String exportaReporteRendicionCuentasCID() throws Exception{
		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_RENDICION_CUENTAS");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportesRendicionCuentas");
			}
	
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteRendicionCuentasCID = rDAO.isolatedconsultaReporteRendicionCuentasCID((Date)session.get("fechaInicio"), (Date)session.get("fechaFin"));
			
			if(lstReporteRendicionCuentasCID!=null && lstReporteRendicionCuentasCID.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivoRendicionCuentasCID(rutaPlantilla,rutaSalida,lstReporteRendicionCuentasCID);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del reporte de rendicion de cuentas con ID, debido a: "+e.getCause());
		}finally{
		}		
		return "SUCCESS";
	}

	private String construyeArchivoRendicionCuentasCID(String rutaPlantilla, String rutaSalida, List<RendicionCuentasV> lst){
		String xlsOut = "ReporteRendicionCuentasCID-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPRENDICIONCTACID.xls";
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

			int row = 8;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				RendicionCuentasV p = lst.get(i);
		        // ID BENEFICIARIO
		        sheet.addCell( new Label(col++,row, p.getIdSuri(), cf));
				// CARTA DE ADHESION
		        sheet.addCell( new Label(col++,row, p.getNoCarta(), cf));
				// ENTIDAD APLICACION
				sheet.addCell( new Label(col++,row, p.getIdEstado().toString(), cf));
				// MUNICIPIO APLICACION
				sheet.addCell( new Label(col++,row, p.getIdMunicipio().toString(), cf));
				// LOCALIDAD APLICACION
				sheet.addCell( new Label(col++,row, "", cf));
				// DDR
				sheet.addCell( new Label(col++,row, "", cf));
				// CADER
				sheet.addCell( new Label(col++,row, "", cf));
				// PROGRAMA
				sheet.addCell( new Label(col++,row, p.getPrograma(), cf));
				// COMPONENTE
				sheet.addCell( new Label(col++,row, p.getComponente(), cf));
				// SUBCOMPONENTE
				sheet.addCell( new Label(col++,row, p.getSubComponente(), cf));
				// CULTIVO ESPECIE
				sheet.addCell( new Label(col++,row, p.getIdCultivo().toString(), cf));
				// FECHA
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getFecha()), cf));
				// ESTATUS MONTO
				sheet.addCell( new Label(col++,row, p.getEstatusMonto(), cf));
				// MONTO FEDERAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getMontoFederal()), cf));
				// MONTO ESTATAL
				sheet.addCell( new Label(col++,row, "", cf));
				// MONTO BENEFICIARIO
				sheet.addCell( new Label(col++,row, "", cf));
				// APOYO
				sheet.addCell( new Label(col++,row, p.getApoyo(), cf));
				// CANTIDAD
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getCantidad()), cf));
				// UNIDAD MEDIDA
				sheet.addCell( new Label(col++,row, p.getUnidadMedida().toString(), cf));
				// INSTANCIA EJECUTORA
				sheet.addCell( new Label(col++,row, p.getInstanciaEjecutora(), cf));
				// CICLO AGRICOLA
				sheet.addCell( new Label(col++,row, p.getCicloAgricola(), cf));
				// AÑO FISCAL
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("yyyy").format(new Date()), cf));
				// AÑO EJERCICIO
				sheet.addCell( new Label(col++,row, p.getAnioEjercicio().toString(), cf));
				// ID SISTEMA
				sheet.addCell( new Label(col++,row, p.getIdSistema().toString(), cf));
				// BENEFICIARIOS HOMBRES
				sheet.addCell( new Label(col++,row, p.getBeneficiariosH(), cf));
				// BENEFICIARIOS MUJERES
				sheet.addCell( new Label(col++,row, p.getBeneficiariosM(), cf));
		        row++;
			}
	        
			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. RENDICION CTA. CID "+ Calendar.getInstance().get(Calendar.YEAR));
			} 			
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

	public String consigueArchivoExcelRendCtaCID() throws Exception{
		try{
			String rutaSalida = cDAO.consultaParametros("RUTA_RENDICION_CUENTAS");	
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreReporte,"xls");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String rendicionCuentasSID(){
		session = ActionContext.getContext().getSession();
		idUsuario = (Integer)session.get("idUsuario");
		
		return SUCCESS;
	}

	public String realizarConsultaRendicionCuentasSID(){
		try{	
			session = ActionContext.getContext().getSession();			
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario			
			lstReporteRendicionCuentasSID = rDAO.consultaReporteRendicionCuentasSID(fechaInicio, fechaFin);

			//subir a session los criterios que el usuario selecciono.
			session.put("fechaInicio", fechaInicio);
			session.put("fechaFin", fechaFin);
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al realizar la consulta del reporte de rendicion de cuentas sin ID, debido a: "+e.getCause());
		}finally{
		}
		
		return SUCCESS;
	}

	public String exportaReporteRendicionCuentasSID() throws Exception{
		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = utileriasDAO.isolatedGetParametros("RUTA_RENDICION_CUENTAS");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/reportesRendicionCuentas");
			}
	
			//Consulta el reporte de acuerdo a los criterios seleccionados por el usuario
			lstReporteRendicionCuentasSID = rDAO.isolatedconsultaReporteRendicionCuentasSID((Date)session.get("fechaInicio"), (Date)session.get("fechaFin"));
			
			if(lstReporteRendicionCuentasSID!=null && lstReporteRendicionCuentasSID.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivoRendicionCuentasSID(rutaPlantilla,rutaSalida,lstReporteRendicionCuentasSID);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta del reporte de rendicion de cuentas sin ID, debido a: "+e.getCause());
		}finally{
		}		
		return "SUCCESS";
	}

	private String construyeArchivoRendicionCuentasSID(String rutaPlantilla, String rutaSalida, List<RendicionCuentasV> lst){
		String xlsOut = "ReporteRendicionCuentasSID-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_REPRENDICIONCTASID.xls";
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

			int row = 8;
			for(int i= 0;i<lst.size();i++){				
				int col = 0;
				RendicionCuentasV p = lst.get(i);
		        // CURP
		        sheet.addCell( new Label(col++,row, p.getCurp(), cf));
		        // RFC
		        sheet.addCell( new Label(col++,row, p.getRfc(), cf));
		        // PRIMER APELLIDO
		        sheet.addCell( new Label(col++,row, p.getPrimerApellido(), cf));
		        // SEGUNDO APELLIDO
		        sheet.addCell( new Label(col++,row, p.getSegundoApellido(), cf));
		        // NOMBRE
		        sheet.addCell( new Label(col++,row, p.getNombreComprador(), cf));
		        // RAZON SOCIAL
		        sheet.addCell( new Label(col++,row, p.getRazonSocial(), cf));
		        // SEXO
		        sheet.addCell( new Label(col++,row, p.getSexo(), cf));
		        // FECHA NACIMIENTO
		        if(p.getFechaNacimiento()!=null){
		        	sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getFechaNacimiento()), cf));
		        } else {
		        	sheet.addCell( new Label(col++,row, "", cf));
		        }
		        // ESTADO NACIMIENTO
		        if(p.getEntidadNacimiento()!=null){
		        	sheet.addCell( new Label(col++,row, p.getEntidadNacimiento().toString(), cf));
		        } else {
		        	sheet.addCell( new Label(col++,row, "", cf));
		        }		        
		        // TIPO PERSONA
		        sheet.addCell( new Label(col++,row, p.getTipoPersona().toString(), cf));
				// CARTA DE ADHESION
		        sheet.addCell( new Label(col++,row, p.getNoCarta(), cf));
				// ENTIDAD APLICACION
				sheet.addCell( new Label(col++,row, p.getIdEstado().toString(), cf));
				// MUNICIPIO APLICACION
				sheet.addCell( new Label(col++,row, p.getIdMunicipio().toString(), cf));
				// LOCALIDAD APLICACION
				sheet.addCell( new Label(col++,row, "", cf));
				// DDR
				sheet.addCell( new Label(col++,row, "", cf));
				// CADER
				sheet.addCell( new Label(col++,row, "", cf));
				// PROGRAMA
				sheet.addCell( new Label(col++,row, p.getPrograma(), cf));
				// COMPONENTE
				sheet.addCell( new Label(col++,row, p.getComponente(), cf));
				// SUBCOMPONENTE
				sheet.addCell( new Label(col++,row, p.getSubComponente(), cf));
				// CULTIVO ESPECIE
				sheet.addCell( new Label(col++,row, p.getIdCultivo().toString(), cf));
				// FECHA
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("dd-MM-yyyy").format(p.getFecha()), cf));
				// ESTATUS MONTO
				sheet.addCell( new Label(col++,row, p.getEstatusMonto(), cf));
				// MONTO FEDERAL
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoImporteSinComas(p.getMontoFederal()), cf));
				// MONTO ESTATAL
				sheet.addCell( new Label(col++,row, "", cf));
				// MONTO BENEFICIARIO
				sheet.addCell( new Label(col++,row, "", cf));
				// APOYO
				sheet.addCell( new Label(col++,row, p.getApoyo(), cf));
				// CANTIDAD
				sheet.addCell( new Label(col++,row, TextUtil.formateaNumeroComoVolumenSinComas(p.getCantidad()), cf));
				// UNIDAD MEDIDA
				sheet.addCell( new Label(col++,row, p.getUnidadMedida().toString(), cf));
				// INSTANCIA EJECUTORA
				sheet.addCell( new Label(col++,row, p.getInstanciaEjecutora(), cf));
				// CICLO AGRICOLA
				sheet.addCell( new Label(col++,row, p.getCicloAgricola(), cf));
				// AÑO FISCAL
				sheet.addCell( new Label(col++,row, new SimpleDateFormat("yyyy").format(new Date()), cf));
				// AÑO EJERCICIO
				sheet.addCell( new Label(col++,row, p.getAnioEjercicio().toString(), cf));
				// ID SISTEMA
				sheet.addCell( new Label(col++,row, p.getIdSistema().toString(), cf));
				// BENEFICIARIOS HOMBRES
				sheet.addCell( new Label(col++,row, p.getBeneficiariosH(), cf));
				// BENEFICIARIOS MUJERES
				sheet.addCell( new Label(col++,row, p.getBeneficiariosM(), cf));
		        row++;
			}
	        
			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. RENDICION CTA. SID "+ Calendar.getInstance().get(Calendar.YEAR));
			} 			
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

	public String getCuadroSatisfactorio() {
		return cuadroSatisfactorio;
	}
	public void setCuadroSatisfactorio(String cuadroSatisfactorio) {
		this.cuadroSatisfactorio = cuadroSatisfactorio;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}	

	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}

	public String getRutaSalida() {
		return rutaSalida;
	}
	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}
	public String getNombreReporte() {
		return nombreReporte;
	}
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	public String getRutaRaiz() {
		return rutaRaiz;
	}
	public void setRutaRaiz(String rutaRaiz) {
		this.rutaRaiz = rutaRaiz;
	}
	public String getRutaLogoS() {
		return rutaLogoS;
	}
	public void setRutaLogoS(String rutaLogoS) {
		this.rutaLogoS = rutaLogoS;
	}
	public String getRutaLogoA() {
		return rutaLogoA;
	}
	public void setRutaLogoA(String rutaLogoA) {
		this.rutaLogoA = rutaLogoA;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setXls(String xls) {
		this.xls = xls;
	}

	public String getXls() {
		return xls;
	}

	public List<RendicionCuentasV> getLstReporteRendicionCuentasCID() {
		return lstReporteRendicionCuentasCID;
	}

	public void setLstReporteRendicionCuentasCID(
			List<RendicionCuentasV> lstReporteRendicionCuentasCID) {
		this.lstReporteRendicionCuentasCID = lstReporteRendicionCuentasCID;
	}

	public List<RendicionCuentasV> getLstReporteRendicionCuentasSID() {
		return lstReporteRendicionCuentasSID;
	}

	public void setLstReporteRendicionCuentasSID(
			List<RendicionCuentasV> lstReporteRendicionCuentasSID) {
		this.lstReporteRendicionCuentasSID = lstReporteRendicionCuentasSID;
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
}
