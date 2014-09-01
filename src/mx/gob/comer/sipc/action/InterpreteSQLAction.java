package mx.gob.comer.sipc.action;


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
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.ResumenAvanceAcopioV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InterpreteSQLAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{
	private Map<String, Object> session;
	private UtileriasDAO uDAO = new UtileriasDAO();
	private String consultaSQL;
	private List<String> listRegistrosSQL;
	private String xls;
	private ServletContext context;
	private String rutaSalida;
	private String nombreReporte;
	private String rutaRaiz;	
		
	public String ejecutaSQL(){	
		try{
			if(consultaSQL != null && !consultaSQL.equals("")){
				listRegistrosSQL = uDAO.ejecutaSQL(consultaSQL);	
				session.put("consultaSQL", consultaSQL);
			}
		}catch(JDBCException e){
			addActionError(e.getCause().getMessage());
			//e.printStackTrace();
		}catch(Exception e){
			addActionError("ERROR: La consulta no es válida (Causa: "+e.getMessage()+"), verifiquelo!!!");
			//e.printStackTrace();
		}
		return SUCCESS;
	}

	public String exportaResultadoSQL() throws Exception{
		try{
			if (session==null){
				session = ActionContext.getContext().getSession();	
			}
			
			String rutaPlantilla = context.getRealPath("/WEB-INF/archivos/plantillas");
			// Leer la Ruta de salida configurada en la tabla parametros
			String rutaSalida = uDAO.isolatedGetParametros("RUTA_CONSULTAS_SQL");
			if(!new File(rutaSalida).exists() ){
				rutaSalida =context.getRealPath("/WEB-INF/archivos/resultadosSQL");
			}
	
			listRegistrosSQL = uDAO.isolatedejecutaSQL((String)session.get("consultaSQL"));
			
			if(listRegistrosSQL!=null && listRegistrosSQL.size()>0){
				// Generar XLS
				nombreReporte = construyeArchivoResultadosSQL(rutaPlantilla,rutaSalida,listRegistrosSQL);
			}
		}catch (JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error al realizar la consulta SQL, debido a: "+e.getCause());
		}finally{
		}		
		return "SUCCESS";
	}

	private String construyeArchivoResultadosSQL(String rutaPlantilla, String rutaSalida, List<String> lst){
		String xlsOut = "ResultadoSQL-"+new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() )+".xls";
		if(!rutaPlantilla.endsWith(File.separator)){
			rutaPlantilla += File.separator;
		}
		rutaPlantilla += "PLANTILLA_RESULTADOSQL.xls";
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
			cf.setBorder( Border.NONE, BorderLineStyle.NONE );

			int row = 0;
			int col = 0;
			for(int i= 0;i<lst.size();i++){				
				String p = lst.get(i);
		        // REGISTRO
		        sheet.addCell( new Label(col,row, p, cf));
		        row++;
			}

			// Leyenda del Encabezado
			WritableCell cell = null;
			cell = sheet.getWritableCell(7,0);
			if (cell.getType() == CellType.LABEL){
			  ((Label) cell).setString("REP. RESULTADO SQL "+ Calendar.getInstance().get(Calendar.YEAR));
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

	public String consigueArchivoExcel() throws Exception{
		try{
			String rutaSalida = uDAO.isolatedGetParametros("RUTA_CONSULTAS_SQL");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreReporte,"xls");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getConsultaSQL() {
		return consultaSQL;
	}

	public void setConsultaSQL(String consultaSQL) {
		this.consultaSQL = consultaSQL;
	}

	public List<String> getListRegistrosSQL() {
		return listRegistrosSQL;
	}

	public void setListRegistrosSQL(List<String> listRegistrosSQL) {
		this.listRegistrosSQL = listRegistrosSQL;
	}

	/**
	 * Implementar la interfaz SessionAware, para session de usuario
	 */ 
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

	public String getXls() {
		return xls;
	}

	public void setXls(String xls) {
		this.xls = xls;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
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
}
