package mx.gob.comer.sipc.catalogos.action;

import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.vistas.domain.BodegasV;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BodegasAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private String claveBodega;
	private String nombreBodega;
	private int idEstado;
	private Double capacidadBodega;
	private List<Estado> lstEstado;
	private List<BodegasV> lstBodegasV;
	private BodegasV bodegasV;
	private boolean bandera;
		
	public String busquedaBodegas(){
		try{						
			//Carga el catalogo de estados			
			setLstEstado(cDAO.consultaEstado(0));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String ejecutaBusquedaBodegas(){
		try{						
			
			setLstBodegasV(cDAO.consultaBodegasV(claveBodega, idEstado, nombreBodega));
			bandera = true;
			//Carga el catalogo de estados
			setLstEstado(cDAO.consultaEstado(0));
			claveBodega = null;
			nombreBodega = null;
			idEstado = -1;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	
	public String detalleBodegas(){
		try{						
			setBodegasV(cDAO.consultaBodegasV(claveBodega).get(0));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	
	public String ejecutaAlta(){
	
		System.out.println("claveBodega"+claveBodega);
		System.out.println("nombreBodega"+nombreBodega);
		System.out.println("capacidadBodega"+capacidadBodega);
		
		return SUCCESS;
	}
	
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public String getClaveBodega() {
		return claveBodega;
	}
	
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	public String getNombreBodega() {
		return nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}

	public Double getCapacidadBodega() {
		return capacidadBodega;
	}

	public void setCapacidadBodega(Double capacidadBodega) {
		this.capacidadBodega = capacidadBodega;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public List<Estado> getLstEstado() {
		return lstEstado;
	}
	
	public void setLstEstado(List<Estado> lstEstado) {
		this.lstEstado = lstEstado;
	}


	public List<BodegasV> getLstBodegasV() {
		return lstBodegasV;
	}


	public void setLstBodegasV(List<BodegasV> lstBodegasV) {
		this.lstBodegasV = lstBodegasV;
	}


	public BodegasV getBodegasV() {
		return bodegasV;
	}

	public void setBodegasV(BodegasV bodegasV) {
		this.bodegasV = bodegasV;
	}


	public boolean isBandera() {
		return bandera;
	}


	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	
}
