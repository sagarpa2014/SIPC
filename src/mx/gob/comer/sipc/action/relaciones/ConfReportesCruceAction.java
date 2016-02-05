package mx.gob.comer.sipc.action.relaciones;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.CatCriteriosValidacion;
import mx.gob.comer.sipc.domain.transaccionales.CnfReportesCruce;
import mx.gob.comer.sipc.log.AppLogger;

import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ConfReportesCruceAction extends ActionSupport {
	
	private RelacionesDAO rDAO = new RelacionesDAO();
	private CatalogosDAO cDAO = new CatalogosDAO();
	private int idPrograma;
	private Programa programa; 
	private Integer [] idCriterio;
	private List<CatCriteriosValidacion> lstCatCriteriosValidacion; 
	private Map<String, Object> session;
	private int registrar;
	
	
	public String getConfReportesCruce(){
		try{			
			programa = cDAO.consultaPrograma(idPrograma).get(0); 
			lstCatCriteriosValidacion = rDAO.getCnfReportesCruce(idPrograma);			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en   debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listPersonalizacionRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}

	public String registrarConfReportesCruce(){
		session = ActionContext.getContext().getSession();		
		
		//borra todos los registros configurados
		rDAO.borrarCnfReportesCruce(idPrograma);
		
		for(int i = 0; i < idCriterio.length; i++){
			CnfReportesCruce c = new CnfReportesCruce();
			c.setIdCriterio(idCriterio[i]);
			c.setIdPrograma(idPrograma);
			c.setUsuarioCreacion((Integer) session.get("idUsuario"));
			c.setFechaAlta(new Date());
			cDAO.guardaObjeto(c);
		}
		getConfReportesCruce();
		
		
		
		return SUCCESS;
	}
	
	
	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<CatCriteriosValidacion> getLstCatCriteriosValidacion() {
		return lstCatCriteriosValidacion;
	}

	public void setLstCatCriteriosValidacion(
			List<CatCriteriosValidacion> lstCatCriteriosValidacion) {
		this.lstCatCriteriosValidacion = lstCatCriteriosValidacion;
	}

	public Integer[] getIdCriterio() {
		return idCriterio;
	}

	public void setIdCriterio(Integer[] idCriterio) {
		this.idCriterio = idCriterio;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}		
	
}
