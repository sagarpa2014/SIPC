package mx.gob.comer.sipc.action;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Aviso;
import mx.gob.comer.sipc.domain.Bitacora;
import mx.gob.comer.sipc.domain.Usuarios;


import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
*AccesoAction.
*Acciones para  los accesos al sistema "SIPC"
*
*Version 1.0
*
*Enero 2013
*
*/
@SuppressWarnings("serial")
public class AccesoAction extends ActionSupport implements SessionAware, Serializable{
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	private String  nombreUsuario;
	private String password;
	private Usuarios usuario;
	@SuppressWarnings("unused")
	private String log;
	private List<Aviso> lstAvisos;
	private int idAviso;
	
	
		
	/**
	 *  Acción que verifica el acceso de los usuarios y consulta el menu de acuerdo al perfil del mismo.
	 * 
	 * @throws JDBCException Si ocurre un error de base de datos al consultar el acceso de usuarios
	 * 
	 */
	public String acceso(){
		try{		
			/*Verificando el acceso del usuario*/		
			usuario = new Usuarios();
			List<Usuarios> lstUsuario = cDAO.consultaUsuarios(0,nombreUsuario, password);
			if(lstUsuario.size()>0){
				usuario =  lstUsuario.get(0);
			}else{
				addActionError("Los datos del usuario son incorrectos, favor de verificar ");
				//regresa a login.jsp 
				return INPUT;
			}
			//Se suben a session los datos del usuario
			session = ActionContext.getContext().getSession();
			session.put("nombreUsuario", usuario.getNombreUsuario());
			session.put("idUsuario", usuario.getIdUsuario());
			session.put("idPerfil", usuario.getIdPerfil());
			//Consiguiendo el nombre de perfil
			session.put("perfil", cDAO.consultaPerfil(usuario.getIdPerfil()).get(0).getPerfil());
			//Si el perfil pertence a una area, conseguir el programa que tiene a carga
			if(usuario.getIdPerfil()==5){
				session.put("idArea", usuario.getIdArea());
			}else if(usuario.getIdPerfil()==6){
				session.put("idEspecialista", usuario.getIdEspecialista());
			}
			
			cDAO.guardaObjeto(new Bitacora(usuario.getIdUsuario(), 1, new Date(),"Acceso el usuario "+usuario.getNombreUsuario()));
			lstAvisos = uDAO.consultaAvisos(usuario.getIdUsuario(),0, true);
			
		}catch (JDBCException e) {
	    	
	    }
		
		return SUCCESS;
	}
	
	/**
	 *  Acción que destruye las variables de session del usuario
	 * 
	 * @throws IllegalStateException Si ocurre un error al destruir las variables de session del usuario
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public String salir(){	
		session = ActionContext.getContext().getSession();
		
		if( getSession().get("nombreUsuario")!=null  && session.get("idUsuario")!=null ){
			log = "El usuario "+getSession().get("nombreUsuario")+" salió del sistema ";
			//AppLogger.info("app",log);
			//cDAO.guardaObjeto(new LogApp(new Date(), (Integer)session.get("idUsuario"), log));
		}else{
			//AppLogger.info("app","*** La sesión expiró ***");
		}
		
		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
		    try {
		        ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		    } catch (IllegalStateException e) {
		    	//AppLogger.error( "errores",""+e.getMessage() );
		    }
		}
		
		return SUCCESS;
	}
	public String cierraAviso(){	
		try{
			session = ActionContext.getContext().getSession();
			/*Cosigue el aviso a través de su id*/
			Aviso a = uDAO.consultaAvisos(0, idAviso, true).get(0);
			a.setHabilitar(false);
			cDAO.guardaObjeto(a);
			lstAvisos = uDAO.consultaAvisos((Integer) session.get("idUsuario"),0,true);
			System.out.println("r:"+lstAvisos.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String bienvenido(){	
		try{
			session = ActionContext.getContext().getSession();
			/*Cosigue el aviso a través de su id*/
			lstAvisos = uDAO.consultaAvisos((Integer) session.get("idUsuario"),0,true);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Aviso> getLstAvisos() {
		return lstAvisos;
	}

	public void setLstAvisos(List<Aviso> lstAvisos) {
		this.lstAvisos = lstAvisos;
	}

	public int getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(int idAviso) {
		this.idAviso = idAviso;
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
}
