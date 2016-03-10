package mx.gob.comer.sipc.action.relaciones;

import java.io.File;
import java.io.IOException;
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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrediosRelaciones;

@SuppressWarnings("serial")
public class ConsultaPrediosAction extends ActionSupport {
	
	private Map<String, Object> session;
	private List<Programa> lstProgramas;
	private RelacionesDAO rDAO = new RelacionesDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	private int idPrograma;
	private String curp;
	private String rfc;
	private String predio;
	private List<PrediosRelaciones> lstPrediosRelaciones;
	private boolean bandera;
	public String consultaPredios(){
		try{			
			lstProgramas = rDAO.getProgramaEnPredio();
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listPersonalizacionRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}
	
	public String ejecutaConsultaPredios(){
		try{
			session = ActionContext.getContext().getSession();
			lstPrediosRelaciones  = rDAO.getPredioCurpYOrfc(idPrograma, predio, curp, rfc);
			session.put("idPrograma", idPrograma);
			session.put("predio", predio);
			session.put("curp", curp);
			session.put("rfc", rfc);
			idPrograma = -1;
			predio = null;
			curp = null;
			rfc = null;					
			bandera = true;
			consultaPredios();
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listPersonalizacionRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}

	
	public String exportaConsultaPredios() throws IOException, WriteException{
		WritableWorkbook workbook = null;
		try {			
			session = ActionContext.getContext().getSession();
			idPrograma= (Integer) session.get("idPrograma");
			predio = (String) session.get("predio");
			curp = (String)session.get("curp");
			rfc = (String)session.get("rfc");		
			/**recupera datos a través de los criterios de busqueda seleccionado por el usuario**/
			lstPrediosRelaciones  = rDAO.getPredioCurpYOrfc(idPrograma, predio, curp, rfc);
			
			String rutaSalida = uDAO.getParametros("RUTA_PLANTILLA_REPORTES");
			String nombreArchivo = "consultaPredios.xls";
			workbook = Workbook.createWorkbook(new File(rutaSalida+nombreArchivo));
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			WritableCellFormat cf1 = new WritableCellFormat(wf1);
			cf1.setAlignment(Alignment.CENTRE);
			cf1.setWrap(true);
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
			WritableCellFormat cf2 = new WritableCellFormat(wf2);
			cf2.setAlignment(Alignment.LEFT);
			cf2.setWrap(true);			
			//Encabezados
			sheet.addCell(new Label(0, 0, "Programa", cf1)); 
			sheet.addCell(new Label(1, 0, "Folio Contrato", cf1)); 
			sheet.addCell(new Label(2, 0, "Predio", cf1));
			sheet.addCell(new Label(3, 0, "Curp", cf1));
			sheet.addCell(new Label(4, 0, "Rfc", cf1)); 
			//Detalle
			int i=1;
			for(PrediosRelaciones v:lstPrediosRelaciones){
				int j = 0;
				sheet.addCell(new Label(j++, i, v.getDescripcionCorta(), cf2));
				sheet.addCell(new Label(j++, i, v.getFolioContrato(), cf2));
				sheet.addCell(new Label(j++, i, v.getPredio(), cf2));
				sheet.addCell(new Label(j++, i, v.getCurp(), cf2));
				sheet.addCell(new Label(j++, i, v.getRfc(), cf2));				
				i++;
			}			
			workbook.write();
			workbook.close();
			Utilerias.entregarArchivo(rutaSalida, nombreArchivo, "xls");			
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al exportar la información de la consulta del reporte, debido a:"+e.getCause());
		}
		finally{
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
		
		
		return SUCCESS;
	}
	
	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getPredio() {
		return predio;
	}

	public void setPredio(String predio) {
		this.predio = predio;
	}

	public List<PrediosRelaciones> getLstPrediosRelaciones() {
		return lstPrediosRelaciones;
	}

	public void setLstPrediosRelaciones(List<PrediosRelaciones> lstPrediosRelaciones) {
		this.lstPrediosRelaciones = lstPrediosRelaciones;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}	
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	
}
