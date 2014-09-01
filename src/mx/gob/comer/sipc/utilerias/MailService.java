package mx.gob.comer.sipc.utilerias;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import mx.gob.comer.sipc.log.AppLogger;

/**
 * Clase encargada de realizar el envio del correo.
 * @author L.I. Francisco Daniel Perez Morales
 *
 */
public class MailService extends Thread {
	
	private Map<String,String> config;
	private String host;
	private String from;
	private String to;
	private String adminCc;
	private String CCP;
	private String subject;
	private String message;
	private boolean adminEnabled;
	private boolean userEnabled;
	
	
	public MailService(){}
	
	public MailService(Map<String,String> config){
		this.config = config;
	}
	
	public MailService(String host, String from, String to, String adminCc, String CCP, String subject,
			String message, boolean adminEnabled, boolean userEnabled) {
		this.host = host;
		this.from = from;
		this.to = to;
		this.adminCc = adminCc;
		this.CCP = CCP;
		this.subject = subject;
		this.message = message;
		this.adminEnabled = adminEnabled;
		this.userEnabled = userEnabled;
	}
	
	public void run() {
		try{
			if(config!=null){
				host 	= config.get("MAIL_SERVER");
				from 	= config.get("CORREO_EMISOR");
				to 		= config.get("DESTINATARIO");
				CCP 	= config.get("CON_COPIA_PARA"); 
				adminCc = config.get("CORREO_ADMIN");
				subject = config.get("TITULO_CORREO");
				message = config.get("MENSAJE");
				userEnabled =  new Boolean( config.get("ALERTAS_HABILITADAS_USUARIO") ).booleanValue();
				adminEnabled = new Boolean( config.get("ALERTAS_HABILITADAS_ADMIN") ).booleanValue();
			}
			if(host==null || from == null || to == null || adminCc == null || subject == null || message == null  ){
				System.out.println("El correo no se ha enviado porque existen valores nulos en los parametros de configuracion");
				return;
			}
			if(adminEnabled || userEnabled){
	            TimeZone.setDefault( TimeZone.getTimeZone("America/Mexico_City") );
	            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	            
	            String[] To  	= null;
				String[] Cc		= null;
				String[] Bcc 	= null;
	            
	            if(userEnabled)
	            {
	            	To  	= to.split(",");
					Cc		= CCP.split(",");
					Bcc 	= adminCc.split(",");
	            }
	            else
	            {
	            	To  	= adminCc.split(",");
	            }
				
				
		        Properties props = System.getProperties();
		        // Setup mail server
		        props.put("mail.smtp.host", host);
		        
		        Session session = Session.getInstance(props, null);
		        Transport t = session.getTransport("smtp");
		        t.connect();
		        if(!t.isConnected()){
		          throw new Exception("Servidor de correo no disponible");
		        }
		
		        Message msg = null;                
		        
		        // Define message
		        msg = new MimeMessage(session);
		        
		        // De:
		        msg.setFrom(new InternetAddress(from));
		        msg.setSubject(subject);
		        
		        // Para:
		        for(int i=0;i<To.length;i++)
		        {
		        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress( To[i].trim() ));
		        }
		        
		        //	Copias
		        if( adminEnabled && userEnabled )
		        {
		        	// Con Copia Para:
		        	if(!Cc[0].equals(""))
		        	{
				        for(String ccpRecipient : Cc)
				        {
				        	msg.addRecipient(Message.RecipientType.CC, new InternetAddress( ccpRecipient.trim() ));
				        }
		        	}
			        
			        // Copias ocultas
	                for(int i=0;i<Bcc.length;i++)
	                {
	                    msg.addRecipient(Message.RecipientType.BCC, new InternetAddress( Bcc[i].trim() ));
	                }		        	
		        }
		        
		        // Mensaje
		        BodyPart messageBodyPart = new MimeBodyPart();
		        // Fill the message
		        message = new StringBuilder().append( formatter.format(new Date()) )
							            	 .append("\n\nEstimado usuario(a),\n\n")
//							            	 .append("Por medio del presente se le notifica la siguiente informacion:\n\n")
		        							 .append(message)
							            	 .append("\n\nNota.- Este correo fue enviado usando un sistema automatizado, por favor no responda el presente correo.").toString();
		        
		        messageBodyPart.setContent(message, "text/plain");
		        // Create a Multipart
		        Multipart multipart = new MimeMultipart();
		        // Add part one
		        multipart.addBodyPart(messageBodyPart);
		        // Put parts in message
		        msg.setContent(multipart);
		        // Enviar
		        Transport.send(msg);
		        AppLogger.info("app","Correo enviado "+config.get("MENSAJE"));
		        
			}else{
				AppLogger.warn("errores","No se puede enviar el correo porque las alertas estan deshabilitadas");
			}
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrio un error al enviar el correo debido a: "+e.getCause());
		}
	}
	/* Setters & getters */
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isAdminEnabled() {
		return adminEnabled;
	}
	public void setAdminEnabled(boolean adminEnabled) {
		this.adminEnabled = adminEnabled;
	}
	public boolean isUserEnabled() {
		return userEnabled;
	}
	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	public String getAdminCc() {
		return adminCc;
	}

	public void setAdminCc(String adminCc) {
		this.adminCc = adminCc;
	}

	public static void main(String args[]){
		Map<String,String> config = new HashMap<String,String>();
		config.put("MAIL_SERVER", "130.10.80.77");
		config.put("CORREO_EMISOR", "noreply@aserca.gob.mx");
		config.put("DESTINATARIO", "alejandro.hurtado@aserca.gob.mx");
		config.put("CORREO_ADMIN", "alejandro.hurtado@aserca.gob.mx");
		config.put("TITULO_CORREO", "Prueba de envio");
		config.put("MENSAJE", "Mensaje de prueba");
		config.put("ALERTAS_HABILITADAS_USUARIO", "true");
		config.put("ALERTAS_HABILITADAS_ADMIN", "true");
		
		new MailService(config).start();
	}
	
}
