package mx.gob.comer.sipc.pagos.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.ReportesDAO;
import mx.gob.comer.sipc.domain.Personal;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.AcusePagosAplicados;
import mx.gob.comer.sipc.oficios.pdf.AcusePagosRechazados;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.vistas.domain.OficioCompradorProgramaV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AcusesPagosAction extends ActionSupport implements  SessionAware, ServletContextAware{
	private Map<String, Object> session;
	private ServletContext context;
	private long idOficio;
	private List<OficioCompradorProgramaV> lstOfCompradorPgr;
	private ReportesDAO rDAO = new ReportesDAO();
	private CatalogosDAO cDAO = new CatalogosDAO();
	private String	rutaSalida;
	private String rutaImagen;
	private String rutaAserca;
	private String rutaMarcaAgua;
	private String fechaActual;
	private String selectedPagos;
	private String selectedOficios;
	private String archivoGenerado;
	private String nombreOficio;

	private List<Personal> lstPersonal;
	private List<Personal> lstPersonalCpp;
	private Personal emisor;
	private String ramo;
	private String unidadResponsable;
	private String leyendaOficio;
	
	
	
	
	public String capturaRechazos(){
		try{
			lstOfCompradorPgr = rDAO.consultaOficioCompradorProgramaV(idOficio, 6);
			
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar los rechazos del oficio seleccionado debido a:"+e.getCause());
			addActionError("Ocurrió un error al recuperar los rechazos del oficio seleccionado, favor de informar al administrador del sistema");
			
		}
		return SUCCESS;
	}
	
	public String capturaAplicados(){
		try{
			lstOfCompradorPgr = rDAO.consultaOficioCompradorProgramaV(idOficio, 5);
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar los rechazos del oficio seleccionado debido a:"+e.getCause());
			addActionError("Ocurrió un error al recuperar los rechazos del oficio seleccionado, favor de informar al administrador del sistema");
			
		}
		return SUCCESS;
	}
	
	public String generarAcuseRechazos(){
		try{
			
			rutaSalida = cDAO.consultaParametros("ACUSES_PAGOS");
			nombreOficio = "Rechazos-Tesofe.pdf";
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");
			rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
			leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
			lstOfCompradorPgr = rDAO.consultaOficioCompradorProgramaV(0, selectedPagos,0);
			
			fechaActual = " a "+new SimpleDateFormat("dd").format(new Date()).toString()+" de "
			+ TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(new Date()).toString()))+" de "
			+ new SimpleDateFormat("yyyy").format(new Date()).toString();
			
			/*Consigue los datos del emisor*/
			StringBuilder personal = new StringBuilder();
			lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false, false);
			if(lstPersonal.size()>0){
				emisor = lstPersonal.get(0);
				personal.append(lstPersonal.get(0).getNombre()).append(" ")
					  .append(lstPersonal.get(0).getPaterno())
					  .append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
				emisor.setNombre(personal.toString().toUpperCase());
				emisor.setPuesto(emisor.getPuesto().toUpperCase());
			}
			/*Consigue los datos del cpp*/
			lstPersonalCpp = cDAO.consultaPersonal(true);
			AcusePagosRechazados a = new AcusePagosRechazados(this);
			a.generarAcusePagosRechazados();
			
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al generar el acuse de los rechazos del oficio seleccionado debido a:"+e.getCause());
			addActionError("Ocurrió un error al generar el acuse de los rechazos del oficio seleccionado, favor de informar al administrador del sistema");			
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al generar los acuses, debido a: "+e.getCause());
		}
		return SUCCESS;
	}
	
	
	public String generarAcusesAplicados(){
		try{
			rutaSalida = cDAO.consultaParametros("ACUSES_PAGOS");
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");
			rutaAserca = context.getRealPath("/images/logoASERCA.jpg");
			ramo = cDAO.consultaParametros("RAMO_TESOFE");
			unidadResponsable = cDAO.consultaParametros("UNIDAD_RESPONSABLE_TESOFE");
			nombreOficio = "Aplicados-Tesofe.pdf";			
			fechaActual = " a "+new SimpleDateFormat("dd").format(new Date()).toString()+" de "
			+ TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(new Date()).toString()))+" de "
			+ new SimpleDateFormat("yyyy").format(new Date()).toString();
	
			
			AcusePagosAplicados a = new AcusePagosAplicados(this);
			a.generarAcusePagosAplicados();
			
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al generar el acuse de los aplicados del oficio seleccionado debido a:"+e.getCause());
			addActionError("Ocurrió un error al generar el acuse de los aplicados del oficio seleccionado, favor de informar al administrador del sistema");			
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al generar el acuse de los pagos aplicados, debido a: "+e.getCause());
			addActionError("Ocurrió un error inesperado al generar el acuse de los aplicados del oficio seleccionado, favor de informar al administrador del sistema");
		}
		return SUCCESS;
	}
	
	
	/*Listas*/
	public List<OficioCompradorProgramaV> getLstOfCompradorPgr() {
		return lstOfCompradorPgr;
	}

	public void setLstOfCompradorPgr(
			List<OficioCompradorProgramaV> lstOfCompradorPgr) {
		this.lstOfCompradorPgr = lstOfCompradorPgr;
	}

	public List<Personal> getLstPersonalCpp() {
		return lstPersonalCpp;
	}

	public void setLstPersonalCpp(List<Personal> lstPersonalCpp) {
		this.lstPersonalCpp = lstPersonalCpp;
	}

	public long getIdOficio() {
		return idOficio;
	}
	

	public void setIdOficio(long idOficio) {
		this.idOficio = idOficio;
	}

	public String getRutaSalida() {
		return rutaSalida;
	}

	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	public String getRutaAserca() {
		return rutaAserca;
	}

	public void setRutaAserca(String rutaAserca) {
		this.rutaAserca = rutaAserca;
	}

	public String getRutaMarcaAgua() {
		return rutaMarcaAgua;
	}

	public void setRutaMarcaAgua(String rutaMarcaAgua) {
		this.rutaMarcaAgua = rutaMarcaAgua;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getSelectedPagos() {
		return selectedPagos;
	}

	public void setSelectedPagos(String selectedPagos) {
		this.selectedPagos = selectedPagos;
	}

	public String getSelectedOficios() {
		return selectedOficios;
	}

	public void setSelectedOficios(String selectedOficios) {
		this.selectedOficios = selectedOficios;
	}
	
	public Personal getEmisor() {
		return emisor;
	}

	public void setEmisor(Personal emisor) {
		this.emisor = emisor;
	}

	public String getArchivoGenerado() {
		return archivoGenerado;
	}

	public void setArchivoGenerado(String archivoGenerado) {
		this.archivoGenerado = archivoGenerado;
	}
	
	

	public String getNombreOficio() {
		return nombreOficio;
	}

	public void setNombreOficio(String nombreOficio) {
		this.nombreOficio = nombreOficio;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(String unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
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
	public Map<String, Object> getSession() {
	    return session;
	}
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
}
