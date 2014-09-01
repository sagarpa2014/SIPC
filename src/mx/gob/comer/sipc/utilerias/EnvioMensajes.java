package mx.gob.comer.sipc.utilerias;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Aviso;
import mx.gob.comer.sipc.vistas.domain.NotificacionesV;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class EnvioMensajes {
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	Aviso aviso;
	CatalogosDAO cDAO;
	UtileriasDAO uDAO;
	
	List<NotificacionesV> usuariosDestino;
	public EnvioMensajes(Session session){
		this.session = session;
		cDAO = new CatalogosDAO(session);
		uDAO = new UtileriasDAO(session);
		aviso = new Aviso();
	}
	
	
	public void enviarMensaje(int idUsuario, int idEvento, String mensaje, String tipoMsj){
		if(tipoMsj.equals("Aviso")){
			/*Recuperando a los usuarios destinatarios que se le enviara el aviso*/
			usuariosDestino = uDAO.consultaDestinatariosNotifacacionV(idEvento, true, false);
			enviarAviso(idUsuario, mensaje, usuariosDestino);	
		}else if(tipoMsj.equals("Correo")){
			//enviar correo
		}else{
			//enviar aviso y correo
		}
		
		
	}
	
	
	private void enviarAviso(int idUsuario, String mensajeAviso, List<NotificacionesV> usuariosDestino){
		Calendar cal = Calendar.getInstance();
		aviso.setDe(idUsuario);
		aviso.setFecha(cal.getTime());
		aviso.setHabilitar(true);
		aviso.setMensaje(mensajeAviso);
		aviso.setIdUsuario(idUsuario);
		for(NotificacionesV ud : usuariosDestino){
			aviso.setPara(ud.getUsuarioId().intValue());
			cDAO.guardaObjeto(new Aviso(aviso));
		}
	}

	
	@SuppressWarnings("unused")
	private void enviarCorreo(String destinatario, String mensaje, String ccp, String asunto){
		Map<String,String> config = new HashMap<String,String>();
		
		config.put("MAIL_SERVER", "130.10.80.77");
		//config.put("MAIL_SERVER", "eserver.aserca.gob.mx");  //nombre de dominio del servidor de correo, metodo alternativo
		config.put("CORREO_EMISOR", "noreply@aserca.gob.mx");
		config.put("DESTINATARIO", destinatario);
		config.put("CON_COPIA_PARA", ccp);
		config.put("CORREO_ADMIN", "atec.dgmp@aserca.gob.mx");
		config.put("TITULO_CORREO", asunto);
		config.put("MENSAJE", mensaje);
		config.put("ALERTAS_HABILITADAS_USUARIO", "true");
		config.put("ALERTAS_HABILITADAS_ADMIN", "true");
		new MailService(config).start();
	}

}
