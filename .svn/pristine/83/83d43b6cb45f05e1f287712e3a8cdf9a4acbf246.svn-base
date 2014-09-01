package mx.gob.comer.sipc.action.solicitudpago;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Expediente;
import mx.gob.comer.sipc.domain.InicializacionEsquema;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.transaccionales.ExpedientePrograma;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.ExpedientesProgramasV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InicializacionSolPagoAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	/**** BD ****/
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO ();
	private InscripcionDAO iDAO = new InscripcionDAO();
	/***LISTAS**/
	private List<Programa> lstProgramas;
	private List<Expediente> lstExpedientesDoc;
	private List<Expediente> lstExpedientesFianza;
	private List<ExpedientesProgramasV> lstExpedienteProgramaDoc;
	private List<ExpedientesProgramasV> lstExpedienteProgramaFianza;
	private List<ExpedientesProgramasV> lstExpedientesProgramasOpcionalV;
	/***VARIABLES DEL FORMULARIO****/
	private Integer[] idExpedientesDoc;
	private Integer[] idExpedientesFianza;
	private int idPrograma;
	private int registrar;
	private String msjOk;
	private String msjError;
	private int documentacion;
	private int errorProgramaYaExisteConfDoc;
	private Integer[] idExpedientesDoc1;
	private Boolean documentacionOpcional;
	private int idExpediente;
	private Boolean opcional;

	public String programasConfigExpediente(){
		try{
			lstProgramas =  spDAO.consultaProgramasByConfiguracionExpediente();			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en programasConfigExpediente  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en programasConfigExpediente  debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	/**Metodo que recupera el detalle de la configuracion de documentación en el programa
	 * Aplica solo para ver detalle y editar
	 * 
	 * @return
	 */
	public String getDetalleConfExpPrograma(){
		try{
			
			capInicializacionSolPago();
			//lstExpedienteProgramaDoc = spDAO.consultaExpedientesProgramasV(0,idPrograma,"DSP,DSPYF");
			//lstExpedienteProgramaFianza = spDAO.consultaExpedientesProgramasV(0,idPrograma,"F,DSPYF");
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en getDetalleConfExpPrograma  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en getDetalleConfExpPrograma  debido a: "+e.getMessage());
		}
		return SUCCESS;
	} 
	
	
	public String capInicializacionSolPago(){
		try{
			if(registrar == 0 || registrar == 3){
				lstProgramas = spDAO.consultaProgramasParaSolPago();
			}else if(registrar == 2 ){
				lstProgramas = cDAO.consultaPrograma(idPrograma);
			}	
			if(registrar == 2 || registrar == 3){
				//if(registrar == 3){
					//Recupera documentacion para verificar si hay configuracion de fianza
					InicializacionEsquema ie = iDAO.consultaInicializacionPrograma(idPrograma).get(0);
					documentacion = ie.getDocumentacion();
				//}
				//Recupera en lista la documentacion registrada
				lstExpedienteProgramaDoc = spDAO.consultaExpedientesProgramasV(0,idPrograma,"'DSP','DSPYF'");
				
				System.out.println("opcional" + lstExpedienteProgramaDoc.get(0).isDocumentacionOpcional());
				System.out.println("lstExpedienteProgramaDoc "+lstExpedienteProgramaDoc.size());
				if(documentacion == 2){
					lstExpedienteProgramaFianza = spDAO.consultaExpedientesProgramasV(0,idPrograma,"'F','DSPYF'");
				}else{
					lstExpedienteProgramaFianza = new ArrayList<ExpedientesProgramasV>();
				}
			}
						
			
			lstExpedientesDoc = cDAO.consultaExpediente(0, "DSP,DSPYF"); //Recupera todos los expedientes de la tabla expedientes
			lstExpedientesFianza = cDAO.consultaExpediente(0, "F,DSPYF");//Recupera todos los expedientes de la tabla expedientes		
			System.out.println("Documentacion "+documentacion);
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capInicializacionSolPago  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}
	
	public String registraInicializacionSP() throws JDBCException, Exception{
		try{
			documentacion = 0;
			int elementosBorrados = 0;
			if(registrar == 3){ // si la accion es editar
				//Borrar de los datos de expedientes programas
				elementosBorrados = spDAO.borrarExpedientesPrograma(idPrograma);
				AppLogger.info("Se borraron "+elementosBorrados+" de expedientes programas");				
			}
			if(idExpedientesFianza!=null){
				//marcar que el usuario selecciono fianza
				for(int i = 0; i < idExpedientesFianza.length; i++){
					//Guarda el expediente en la tabla de expediente_programa
					 ExpedientePrograma ep = new ExpedientePrograma();
					 ep.setIdExpediente(idExpedientesFianza[i]);
					 ep.setIdPrograma(idPrograma);
					 cDAO.guardaObjeto(ep);
				}// END FOR
				
			}//END SI HUBO FIANZA
						
			for(int i = 0; i < idExpedientesDoc.length; i++){
				if(idExpedientesFianza != null){
					//Recupera el tipo de documento y si es ""DSPYF(Documentacion solicitud pago y fianza)" no se guarda en la tabla de expediente_programa
					 List<Expediente> lstExpediente = cDAO.consultaExpediente(idExpedientesDoc[i], "");
					 if(!lstExpediente.get(0).getTipoExpediente().equals("DSPYF")){
						 //Guarda el expediente en la tabla de expediente_programa
						 ExpedientePrograma ep = new ExpedientePrograma();
						 ep.setIdExpediente(idExpedientesDoc[i]);
						 ep.setIdPrograma(idPrograma);
						 for(int j = 0; j < idExpedientesDoc1.length; j++){
							 System.out.println("idEDoc1 "+idExpedientesDoc1[j].intValue());
							 System.out.println("idExpedientesDoc "+idExpedientesDoc[i].intValue());
							 if (idExpedientesDoc1[j] == idExpedientesDoc[i]){
								 ep.setDocumentacionOpcional(true); 
							 }	 
						 }
						 cDAO.guardaObjeto(ep);
					 }
				}else{
					ExpedientePrograma ep = new ExpedientePrograma();
					ep.setIdExpediente(idExpedientesDoc[i]);
					ep.setIdPrograma(idPrograma);
					for(int j = 0; j < idExpedientesDoc1.length; j++){
						if (idExpedientesDoc1[j] == idExpedientesDoc[i]){
							ep.setDocumentacionOpcional(true);
							
						}
					}
					 
					cDAO.guardaObjeto(ep);
				}
			}
			
			//Actualizando el campo documentacion en inicializacion de programa
			InicializacionEsquema ie = iDAO.consultaInicializacionPrograma(idPrograma).get(0);
			
			if(idExpedientesFianza !=null){
				documentacion = 2;
				ie.setDocumentacion(documentacion); //Documentacion y fianza
			}else{
				documentacion = 1;
				ie.setDocumentacion(documentacion); //Solo Documentacion
			}		
			cDAO.guardaObjeto(ie);
			
			registrar = 2;
			msjOk = "Se guardó satisfactoriamente el registro";
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraInicializacionSP debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraInicializacionSP  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			capInicializacionSolPago();
		}
		return SUCCESS;		
	}
	
	public String verificarProgramaConfigurado(){
		try{
			System.out.println("verificarProgramaConfigurado");
			if(spDAO.consultaExpedientesProgramasV(0,idPrograma,"").size()>0){
				errorProgramaYaExisteConfDoc  = 1;
				msjError = "La inicialización del programa  ya se encuentra registrado, por favor verifique";
			}				
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en verificarProgramaConfigurado  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en verificarProgramaConfigurado  debido a: "+e.getMessage());
		}
		return SUCCESS;
	}
	
	
	/***LISTAS**/
	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}
	
	public List<Expediente> getLstExpedientesDoc() {
		return lstExpedientesDoc;
	}

	public void setLstExpedientesDoc(List<Expediente> lstExpedientesDoc) {
		this.lstExpedientesDoc = lstExpedientesDoc;
	}

	public List<Expediente> getLstExpedientesFianza() {
		return lstExpedientesFianza;
	}

	public void setLstExpedientesFianza(List<Expediente> lstExpedientesFianza) {
		this.lstExpedientesFianza = lstExpedientesFianza;
	}
	
	public List<ExpedientesProgramasV> getLstExpedienteProgramaDoc() {
		return lstExpedienteProgramaDoc;
	}

	public void setLstExpedienteProgramaDoc(
			List<ExpedientesProgramasV> lstExpedienteProgramaDoc) {
		this.lstExpedienteProgramaDoc = lstExpedienteProgramaDoc;
	}

	public List<ExpedientesProgramasV> getLstExpedienteProgramaFianza() {
		return lstExpedienteProgramaFianza;
	}

	public void setLstExpedienteProgramaFianza(
			List<ExpedientesProgramasV> lstExpedienteProgramaFianza) {
		this.lstExpedienteProgramaFianza = lstExpedienteProgramaFianza;
	}

	public Integer[] getIdExpedientesDoc() {
		return idExpedientesDoc;
	}

	public void setIdExpedientesDoc(Integer[] idExpedientesDoc) {
		this.idExpedientesDoc = idExpedientesDoc;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}
	
	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	
	public String getMsjError() {
		return msjError;
	}
	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	public int getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(int documentacion) {
		this.documentacion = documentacion;
	}
	
	public int getErrorProgramaYaExisteConfDoc() {
		return errorProgramaYaExisteConfDoc;
	}
	public void setErrorProgramaYaExisteConfDoc(int errorProgramaYaExisteConfDoc) {
		this.errorProgramaYaExisteConfDoc = errorProgramaYaExisteConfDoc;
	}
	public Integer[] getIdExpedientesFianza() {
		return idExpedientesFianza;
	}

	public void setIdExpedientesFianza(Integer[] idExpedientesFianza) {
		this.idExpedientesFianza = idExpedientesFianza;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public Integer[] getIdExpedientesDoc1() {
		return idExpedientesDoc1;
	}
	public void setIdExpedientesDoc1(Integer[] idExpedientesDoc1) {
		this.idExpedientesDoc1 = idExpedientesDoc1;
	}
	
	public Boolean getDocumentacionOpcional() {
		return documentacionOpcional;
	}
	public void setDocumentacionOpcional(Boolean documentacionOpcional) {
		this.documentacionOpcional = documentacionOpcional;
	}
	public List<ExpedientesProgramasV> getLstExpedientesProgramasOpcionalV() {
		return lstExpedientesProgramasOpcionalV;
	}
	public void setLstExpedientesProgramasOpcionalV(
			List<ExpedientesProgramasV> lstExpedientesProgramasOpcionalV) {
		this.lstExpedientesProgramasOpcionalV = lstExpedientesProgramasOpcionalV;
	}
	public int getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(int idExpediente) {
		this.idExpediente = idExpediente;
	}
	public Boolean getOpcional() {
		return opcional;
	}
	public void setOpcional(Boolean opcional) {
		this.opcional = opcional;
	}

}
