package mx.gob.comer.sipc.action.avisosdof;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.AvancePagosDAO;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Ejercicios;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.catalogos.ApoyoAviso;
import mx.gob.comer.sipc.domain.catalogos.ProgAviso;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDof;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDofDetalle;
import mx.gob.comer.sipc.vistas.domain.AvisosDofDetalleV;
import mx.gob.comer.sipc.vistas.domain.AvisosDofV;

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
public class AvisosDofAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{
	
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private AvancePagosDAO aDAO = new AvancePagosDAO();
	private List<Ejercicios> lstEjercicios;
	private List<ProgAviso> lstPgrAviso;
	private List<Estado> lstEstados;
	private List<ApoyoAviso> lstApoyoAviso;
	private List<Cultivo> lstCultivo;
	private List<AvisosDofDetalleV> lstAvisosDofDetalleV;
	private String programa;
	private String estado;
	private AvisosDof av;
	private AvisosDofDetalle avd;
	private AvisosDof avisosDof;
	private AvisosDofDetalle avisosDofDetalle;
	
	private int registrar;	
	private String claveAviso;
	private Map<String, Object> session;
	private int error;
	private List<AvisosDofV> lstAvisosDofV;
	private Boolean bandera;
	private String cultivo;
	private int errorDetalleRepetido;
	private int errorAvisoYaCapturado;
	private int registrosBorrados;
	private int id;
	private int avanceCapturados;
	
	
	public String ltsAvisosDof(){
		try{			
			lstAvisosDofV = aDAO.getAvisosDofV(null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	public String buscarAvisoDof(){
		try{			
			lstAvisosDofV = aDAO.getAvisosDofV(claveAviso);
			bandera = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	public String capturaAvisosDof(){
		try{
			if(registrar != 0){
				//consigue el aviso
				av = aDAO.getAvisosDof(claveAviso).get(0);
				lstAvisosDofDetalleV = aDAO.getAvisosDofDetalle(claveAviso);
			}
			lstApoyoAviso = cDAO.getApoyoAviso(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String registrarAvisosDof(){
		try{		
			session = ActionContext.getContext().getSession();
			
			if(registrar == 0){
				 //Verifica si la clave de aviso no se ha capturado
				if( aDAO.getAvisosDof(av.getClaveAviso()).size()>0){
					errorAvisoYaCapturado = 1;
					return SUCCESS;
				}				
				 avisosDof = new AvisosDof();
				 avisosDof.setClaveAviso(av.getClaveAviso());
				 avisosDof.setFechaRegistro(new Date());
				 avisosDof.setUsuarioRegistro((Integer) session.get("idUsuario"));
				 avisosDof.setAvisoDofDetalle(new HashSet<AvisosDofDetalle>());
			}else{
				avisosDof = aDAO.getAvisosDof(claveAviso).get(0);
				avisosDof.setFechaActualizacion(new Date());
				avisosDof.setUsuarioActualizacion((Integer) session.get("idUsuario"));
			}			
			//Verifica que no se haya capturado la llave unica (claveAcceso, ciclo, ejercicio, programa, cultivo y estado)
			lstAvisosDofDetalleV =  aDAO.getAvisosDofDetalle(av.getClaveAviso(), avd.getPrograma(), avd.getIdCultivo(),
					avd.getCiclo(), avd.getEjercicio(), avd.getIdEstado(), null,0);
			if(lstAvisosDofDetalleV.size() > 0){
				errorDetalleRepetido = 1;
				return SUCCESS;
			}
			
			avisosDof.setIdApoyo(av.getIdApoyo());
			avisosDof.setLeyenda(av.getLeyenda());
			avisosDof.setFechaPublicacion(av.getFechaPublicacion());
			
			//if(registrar == 0){
				avisosDofDetalle = new AvisosDofDetalle();
			//}		
			avisosDofDetalle.setClaveAviso(av.getClaveAviso());
			avisosDofDetalle.setPrograma(avd.getPrograma());
			avisosDofDetalle.setIdCultivo(avd.getIdCultivo());
			avisosDofDetalle.setCiclo(avd.getCiclo());
			avisosDofDetalle.setEjercicio(avd.getEjercicio());	
			avisosDofDetalle.setCicloAgricola(avd.getCiclo()+ avd.getEjercicio().toString().substring(2,4));
			avisosDofDetalle.setIdEstado(avd.getIdEstado());
			avisosDofDetalle.setVolumen(avd.getVolumen());
			avisosDofDetalle.setImporte(avd.getImporte());			
			avisosDof.getAvisoDofDetalle().add(avisosDofDetalle);			
			cDAO.guardaObjeto(avisosDof);			
			programa = cDAO.getProgAviso(avd.getPrograma()).get(0).getNombre();
			estado = cDAO.consultaEstado(avd.getIdEstado()).get(0).getNombre();
			cultivo = cDAO.consultaCultivo(avd.getIdCultivo()).get(0).getCultivo();
			error = 0;
			registrar = 1; 
		}catch(Exception e){			
			error = 1;
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	public String getTablaAvisosDof(){
		try{	
			//Consigue la informacion de la tabla de aviso
			System.out.println("Tabla aviso dof");
			lstEjercicios  = cDAO.consultaEjercicio(0);
			lstPgrAviso = cDAO.getProgAviso(0);
			lstEstados = cDAO.consultaEstado(0);
			lstApoyoAviso = cDAO.getApoyoAviso(0);
			lstCultivo = cDAO.consultaCultivo();
		}catch(JDBCException e) {	
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	
	
	public String eliminarDetalleAviso(){	
		
		try {
			//Verifica que no se haya captura un avance con esta configuracion
			lstAvisosDofDetalleV =  aDAO.getAvisosDofDetalle(id);
			AvisosDofDetalleV ad = lstAvisosDofDetalleV.get(0);
			avanceCapturados  = aDAO.getAvanceExistente(ad.getClaveAviso(), ad.getIdProgAviso(), ad.getIdCultivo(), ad.getIdEstado(), ad.getCicloAgricola());
			if(avanceCapturados > 0){
				registrosBorrados = -1;
			}else{
				registrosBorrados = aDAO.borrarDetalleAviso(id);	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public List<Ejercicios> getLstEjercicios() {
		return lstEjercicios;
	}

	public void setLstEjercicios(List<Ejercicios> lstEjercicios) {
		this.lstEjercicios = lstEjercicios;
	}
	
	public List<ProgAviso> getLstPgrAviso() {
		return lstPgrAviso;
	}

	public void setLstPgrAviso(List<ProgAviso> lstPgrAviso) {
		this.lstPgrAviso = lstPgrAviso;
	}
	
	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}	

	public List<ApoyoAviso> getLstApoyoAviso() {
		return lstApoyoAviso;
	}

	public void setLstApoyoAviso(List<ApoyoAviso> lstApoyoAviso) {
		this.lstApoyoAviso = lstApoyoAviso;
	}	
	
	
	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}
		
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public AvisosDof getAv() {
		return av;
	}

	public void setAv(AvisosDof av) {
		this.av = av;
	}

	public AvisosDofDetalle getAvd() {
		return avd;
	}

	public void setAvd(AvisosDofDetalle avd) {
		this.avd = avd;
	}

	

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
	
	public List<AvisosDofV> getLstAvisosDofV() {
		return lstAvisosDofV;
	}

	public void setLstAvisosDofV(List<AvisosDofV> lstAvisosDofV) {
		this.lstAvisosDofV = lstAvisosDofV;
	}
	
	public Boolean getBandera() {
		return bandera;
	}

	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}
	
	
	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}	

	public List<AvisosDofDetalleV> getLstAvisosDofDetalleV() {
		return lstAvisosDofDetalleV;
	}

	public void setLstAvisosDofDetalleV(List<AvisosDofDetalleV> lstAvisosDofDetalleV) {
		this.lstAvisosDofDetalleV = lstAvisosDofDetalleV;
	}

	
	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	

	public int getErrorDetalleRepetido() {
		return errorDetalleRepetido;
	}

	public void setErrorDetalleRepetido(int errorDetalleRepetido) {
		this.errorDetalleRepetido = errorDetalleRepetido;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {	
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		
	}

	public int getRegistrosBorrados() {
		return registrosBorrados;
	}

	public void setRegistrosBorrados(int registrosBorrados) {
		this.registrosBorrados = registrosBorrados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getErrorAvisoYaCapturado() {
		return errorAvisoYaCapturado;
	}

	public void setErrorAvisoYaCapturado(int errorAvisoYaCapturado) {
		this.errorAvisoYaCapturado = errorAvisoYaCapturado;
	}

	public String getClaveAviso() {
		return claveAviso;
	}

	public void setClaveAviso(String claveAviso) {
		this.claveAviso = claveAviso;
	}

	public int getAvanceCapturados() {
		return avanceCapturados;
	}

	public void setAvanceCapturados(int avanceCapturados) {
		this.avanceCapturados = avanceCapturados;
	}
}
