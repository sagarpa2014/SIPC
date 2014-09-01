package mx.gob.comer.sipc.action.inscripcion;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Personal;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.AreasResponsables;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.OficioEntregaCartas;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.CartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.ProgramaEstadoV;
import mx.gob.comer.sipc.vistas.domain.ProgramasV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.lowagie.text.Document;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;

import javax.servlet.ServletContext;

@SuppressWarnings("serial")
public class OficioEntregaCartasAction  extends ActionSupport implements ServletContextAware, SessionAware, Serializable { 
	
	private Map<String, Object> session;
	private ServletContext context;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private InscripcionDAO iDAO = new InscripcionDAO();
	private List<Estado> lstEstados;
	private List<ProgramasV> lstProgramas;
	private List<Comprador> lstComprador;
	private List<ProgramaEstadoV> lstProgramaEdoV;
	private List<PagosV> lstPagosV;
	private List<PagosDetalleV> lstPagosDetalleV;
	private List<Personal> lstPersonal;
	private List<CartasAdhesionV> lstCartasV;
	private List<AreasResponsables> lstEmisor;
	private Personal destinatario;
	private Personal emisor;
	private int idPrograma;
	private int idEstado;
	private String noOficio;
	private String claveOficio;
	private String anioOficio;
	private String rutaRaiz;
	private String rutaSalida;
	private String nombreArchivo;
	private String nombreCoordinacion;
	private String fechaActual;
	private String nombrePrograma;
	private String selectedCartas;
	private String rutaImagen;
	private String nombreOficio;
	private String leyendaOficio;
	private long idOficio;
	private String rutaMarcaAgua;
	private boolean errorOficioDuplicado;
	private String tipoArchivo;
	private String log;
	private Programa programa;
	private String direccionEmisor;
	private String oficioEntrega;
	private String fechaOficioEntrega;
	private int errorSistema;
	
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	
	
	public String oficioEntregaCartas(){
		try{
			session = ActionContext.getContext().getSession();
			lstProgramas = cDAO.consultaProgramaV(0,(Integer) session.get("idArea"));
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar el catalogo de programas debido a:"+e.getCause());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	public String recuperaCartasEntregar(){
		try{
			programa = cDAO.consultaPrograma(idPrograma).get(0);
			/*Recupera los pagos del programa seleccionado por el usuario con estatus = 1*/
			lstCartasV = iDAO.consultaCartasV(null, idPrograma, 0, 1, null);
			claveOficio = "F00.4000/";
			anioOficio = "/"+new SimpleDateFormat("yyyy").format(new Date());
			fechaOficioEntrega = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public String vistaPreviaOficio(){
		programa = cDAO.consultaPrograma(idPrograma).get(0);
		Utilerias.getResponseISO();
		try {
			recuperDatosOficio();
			if(errorOficioDuplicado){
				addActionError("El número de oficio ya se encuentra registrado, por favor verifique");
				return SUCCESS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	private void recuperDatosOficio() throws Exception {
		session = ActionContext.getContext().getSession();
		StringBuilder dest = new StringBuilder();
		StringBuilder emi = new StringBuilder();
		String idCarta = "";
		lstCartasV = new ArrayList<CartasAdhesionV>();
		String oficio =claveOficio +noOficio +anioOficio;
		/*Verifica que el numero de oficio no se encuentre en la base de datos*/
		if(iDAO.consultaCartasV(null, idPrograma, 0, 0, oficio).size()>0){
			errorOficioDuplicado = true;
		}else{
			StringTokenizer tokens = new StringTokenizer(selectedCartas, ",");		
			while(tokens.hasMoreTokens()){
				idCarta = tokens.nextToken();  
				if(!idCarta.equals("-1")){
					lstCartasV.add(iDAO.consultaCartasV(idCarta).get(0));
				}
			 }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            Date fechaActualConvert = dateFormat.parse(fechaOficioEntrega); 

            fechaActual = " a "+new SimpleDateFormat("dd").format(fechaActualConvert).toString()+" de "
                    + TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(fechaActualConvert).toString()))+" de "
                    + new SimpleDateFormat("yyyy").format(fechaActualConvert).toString();			
			// Esquemas de Apoyos con UR diferente DGDM
//			if ((Integer)session.get("idArea")!=2) {
//				//Recupera el destinatario
//				lstPersonal = cDAO.consultaPersonal(0, "Director General de Desarrollo de Mercados", false, false, false, false, true, false, null);
//				if(lstPersonal.size()>0){
//					destinatario = lstPersonal.get(0);
//					dest.append(lstPersonal.get(0).getNombre()).append(" ")
//								.append(lstPersonal.get(0).getPaterno())
//								.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
//					destinatario.setNombre(dest.toString().toUpperCase());
//					destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
//				}
				
				//Recupera el emisor
//				lstEmisor = cDAO.consultaAreasResponsables((Integer)session.get("idArea"));
//				lstPersonal = cDAO.consultaPersonal(0, null, false, false, false, false, true, false, lstEmisor.get(0).getArea());
//				if(lstPersonal.size()>0){
//					emisor = lstPersonal.get(0);
//					emi.append(lstPersonal.get(0).getNombre()).append(" ")
//						  .append(lstPersonal.get(0).getPaterno())
//						  .append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
//					emisor.setNombre(emi.toString().toUpperCase());
//					emisor.setPuesto(emisor.getPuesto().toUpperCase());
//				}
			// Esquemas de Apoyos con UR igual DGDM				
//			} else {
				//Recupera el destinatario
				lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false, false, false, true, false, null);
				if(lstPersonal.size()>0){
					destinatario = lstPersonal.get(0);
					dest.append(lstPersonal.get(0).getNombre()).append(" ")
								.append(lstPersonal.get(0).getPaterno())
								.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
					destinatario.setNombre(dest.toString().toUpperCase());
					destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
				}
				
				//Recupera el emisor
				lstEmisor = cDAO.consultaAreasResponsables((Integer)session.get("idArea"));
				lstPersonal = cDAO.consultaPersonal(0, null, false, false, false, false, true, false, lstEmisor.get(0).getArea());
				if(lstPersonal.size()>0){
					emisor = lstPersonal.get(0);
					emi.append(lstPersonal.get(0).getNombre()).append(" ")
						  .append(lstPersonal.get(0).getPaterno())
						  .append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
					emisor.setNombre(emi.toString().toUpperCase());
					emisor.setPuesto(emisor.getPuesto().toUpperCase());
				}				
//			}
			//Recupera los cpp
			lstPersonal = cDAO.consultaPersonalSQLQuery(0, "", false, false, false, idPrograma, true, true);
			nombrePrograma = cDAO.consultaPrograma(idPrograma).get(0).getDescripcionLarga();
			direccionEmisor = lstEmisor.get(0).getNombreArea();
			//Recupera la leyenda del oficio
			leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
		}
	}
	
	@SuppressWarnings("unused")
	public String generarOficioEntregaCartas() throws Exception{
		session = ActionContext.getContext().getSession();
		programa = cDAO.consultaPrograma(idPrograma).get(0);
		oficioEntrega = claveOficio+noOficio+anioOficio;
		recuperDatosOficio();
		if(errorOficioDuplicado){
			return SUCCESS;
		}
		try {
			/**Genera el oficio de envio de CGC a DGAF en formato PDF*/
			Document document;
			
			rutaSalida = cDAO.consultaParametros("RUTA_OFICIO_ENTREGA_ACTAS");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			nombreOficio =new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"-OficioEntregaCartas.pdf";
			rutaSalida +=nombreOficio;
			rutaRaiz = context.getRealPath("/");
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");
			rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
			//nombreArchivo = "informeRE04.pdf";
			nombreCoordinacion = cDAO.consultaParametros("NOMBRE_COORDINACION");
			OficioEntregaCartas pdf = new OficioEntregaCartas(this);
			pdf.generarOficioEntregaCartas();
			/**Actualizando las cartas del oficio*/
			StringTokenizer tokens = new StringTokenizer(selectedCartas, ",");
			String cartas = "", idCarta;
			while(tokens.hasMoreTokens()){
				idCarta = tokens.nextToken();  
				if(!idCarta.equals("-1")){
					if (cartas==null||cartas==""){
						cartas = "'"+idCarta+"'";
					} else {
						cartas = cartas+",'"+idCarta+"'";
					}					
				}
			}
			int registros = iDAO.actualizaCartasPorOficio(cartas, oficioEntrega, nombreOficio, fechaOficioEntrega);
			log = "Se le informa que se generó Oficio de Entrega de Cartas de Adhesión: "+claveOficio+noOficio+anioOficio+" - Programa: "+cDAO.consultaPrograma(idPrograma).get(0).getDescripcionCorta().toUpperCase()+" - No. Cartas: "+registros;
			
			EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
			mensajes.enviarMensaje((Integer) session.get("idUsuario"), 8, log, "Aviso");
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al generar el oficio debido a:"+e.getCause());
		}
		
		return SUCCESS;
	}
	
	public String consigueOficio() throws Exception{
		try{
			rutaSalida = cDAO.consultaParametros("RUTA_OFICIO_ENTREGA_ACTAS");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreOficio,"pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public List<Estado> getLstEstados() {
		return lstEstados;
	}
	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}
	public List<Comprador> getLstComprador() {
		return lstComprador;
	}
	
	public void setLstComprador(List<Comprador> lstComprador) {
		this.lstComprador = lstComprador;
	}
	
	public List<ProgramaEstadoV> getLstProgramaEdoV() {
		return lstProgramaEdoV;
	}
	public void setLstProgramaEdoV(List<ProgramaEstadoV> lstProgramaEdoV) {
		this.lstProgramaEdoV = lstProgramaEdoV;
	}
	
	public List<PagosV> getLstPagosV() {
		return lstPagosV;
	}
	public void setLstPagosV(List<PagosV> lstPagosV) {
		this.lstPagosV = lstPagosV;
	}

	public String getRutaRaiz() {
		return rutaRaiz;
	}
	public void setRutaRaiz(String rutaRaiz) {
		this.rutaRaiz = rutaRaiz;
	}
	
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public String getRutaSalida() {
		return rutaSalida;
	}
	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getNombreCoordinacion() {
		return nombreCoordinacion;
	}
	public void setNombreCoordinacion(String nombreCoordinacion) {
		this.nombreCoordinacion = nombreCoordinacion;
	}
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public List<PagosDetalleV> getLstPagosDetalleV() {
		return lstPagosDetalleV;
	}
	public void setLstPagosDetalleV(List<PagosDetalleV> lstPagosDetalleV) {
		this.lstPagosDetalleV = lstPagosDetalleV;
	}

	
	public String getNombreOficio() {
		return nombreOficio;
	}
	public void setNombreOficio(String nombreOficio) {
		this.nombreOficio = nombreOficio;
	}
	public List<Personal> getLstPersonal() {
		return lstPersonal;
	}
	public void setLstPersonal(List<Personal> lstPersonal) {
		this.lstPersonal = lstPersonal;
	}
	public String getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}
	
	public long getIdOficio() {
		return idOficio;
	}
	public void setIdOficio(long idOficio) {
		this.idOficio = idOficio;
	}
	public Personal getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Personal destinatario) {
		this.destinatario = destinatario;
	}
	public Personal getEmisor() {
		return emisor;
	}
	public void setEmisor(Personal emisor) {
		this.emisor = emisor;
	}
	
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public String getNoOficio() {
		return noOficio;
	}
	public void setNoOficio(String noOficio) {
		this.noOficio = noOficio;
	}
	public String getRutaMarcaAgua() {
		return rutaMarcaAgua;
	}
	public void setRutaMarcaAgua(String rutaMarcaAgua) {
		this.rutaMarcaAgua = rutaMarcaAgua;
	}

	public String getClaveOficio() {
		return claveOficio;
	}
	public void setClaveOficio(String claveOficio) {
		this.claveOficio = claveOficio;
	}
	public String getAnioOficio() {
		return anioOficio;
	}
	public void setAnioOficio(String anioOficio) {
		this.anioOficio = anioOficio;
	}	
	public boolean isErrorOficioDuplicado() {
		return errorOficioDuplicado;
	}	
	public void setErrorOficioDuplicado(boolean errorOficioDuplicado) {
		this.errorOficioDuplicado = errorOficioDuplicado;
	}
	
	public String getLeyendaOficio() {
		return leyendaOficio;
	}

	public void setLeyendaOficio(String leyendaOficio) {
		this.leyendaOficio = leyendaOficio;
	}

	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public List<CartasAdhesionV> getLstCartasV() {
		return lstCartasV;
	}

	public void setLstCartasV(List<CartasAdhesionV> lstCartasV) {
		this.lstCartasV = lstCartasV;
	}

	public List<ProgramasV> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<ProgramasV> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public String getSelectedCartas() {
		return selectedCartas;
	}

	public void setSelectedCartas(String selectedCartas) {
		this.selectedCartas = selectedCartas;
	}

	public List<AreasResponsables> getLstEmisor() {
		return lstEmisor;
	}

	public void setLstEmisor(List<AreasResponsables> lstEmisor) {
		this.lstEmisor = lstEmisor;
	}

	public String getDireccionEmisor() {
		return direccionEmisor;
	}

	public void setDireccionEmisor(String direccionEmisor) {
		this.direccionEmisor = direccionEmisor;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public String getOficioEntrega() {
		return oficioEntrega;
	}

	public void setOficioEntrega(String oficioEntrega) {
		this.oficioEntrega = oficioEntrega;
	}

	public int getErrorSistema() {
		return errorSistema;
	}

	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
	}

	public String getFechaOficioEntrega() {
		return fechaOficioEntrega;
	}

	public void setFechaOficioEntrega(String fechaOficioEntrega) {
		this.fechaOficioEntrega = fechaOficioEntrega;
	}		
}
