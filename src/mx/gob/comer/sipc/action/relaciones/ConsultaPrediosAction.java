package mx.gob.comer.sipc.action.relaciones;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.relaciones.PrediosRelaciones;

@SuppressWarnings("serial")
public class ConsultaPrediosAction extends ActionSupport {	
	
	private List<Programa> lstProgramas;
	private RelacionesDAO rDAO = new RelacionesDAO();
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
			
			lstPrediosRelaciones  = rDAO.getPredioCurpYOrfc(idPrograma, predio, curp, rfc);
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
	
	
}
