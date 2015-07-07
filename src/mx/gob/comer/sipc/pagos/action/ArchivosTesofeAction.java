package mx.gob.comer.sipc.pagos.action;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.OficioPagos;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.Personal;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.OficioEnvioCGCDGAF;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.DocumentacionSPCartaAdhesionV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.ProgramaEstadoV;

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
public class ArchivosTesofeAction  extends ActionSupport implements ServletContextAware, SessionAware, Serializable { 
	
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();

	private Map<String, Object> session;
	private ServletContext context;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private PagosDAO pDAO = new PagosDAO();
	private List<Estado> lstEstados;
	private List<Programa> lstProgramas;
	private List<Comprador> lstComprador;
	private List<ProgramaEstadoV> lstProgramaEdoV;
	private List<PagosV> lstPagosV;
	private List<PagosDetalleV> lstPagosDetalleV;
	private List<Personal> lstPersonal;
	private Personal ccep1;
	private Personal ccep2;
	private Personal ccep3;
	private List<Personal> lstPersonalVoBo;
	private Personal destinatario;
	private Personal emisor;
	private Personal elaboro;
	private int idPrograma;
	private int idEstado;
	private String noOficio;
	private String claveOficio;
	private String anioOficio;
	private String	rutaRaiz;
	private String	rutaSalida;
	private String nombreArchivo;
	private String nombreCoordinacion;
	private String fechaActual;
	private String nombreEstado;
	private int tipoForma;
	private String ciclo;
	private String nombrePrograma;
	private int noDepositos;
	private String selectedPagos;
	private String importeLetra;
	private OficioPagos oficioPagos;
	private String rutaImagen;
	private String nombreOficio;
	private String leyendaOficio;
	private long idOficio;
	private String rutaMarcaAgua;
	private Double totalImporte;
	private Double totalVolumen;
	private boolean errorOficioDuplicado;
	private String tipoArchivo;
	private String log;
	private Programa programa;
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	private int criterioPago;
	private String primerParrafo;
	private String p11Texto;
	private String p12Texto;
	private String segundoParrafo;
	private String p21Texto; 
	private String p22Texto; 
	
	private String tercerParrafo;
	private String cuartoParrafo;
	private String p13Texto;
	private int version;
	private String p14Texto;
	
	private String quintoParrafo;
	private String leyendaConformidad;
	
	public String archivoTesofeEnvio(){
		try{
			recuperaCatalogos(null,"Programas",null);
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al recuperar el catalogo de programas debido a:"+e.getCause());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String oficioEnvio() throws JDBCException, Exception{
		Utilerias.getResponseISO();
		System.out.println("oficioEnvio");
		recuperaCatalogos(null,"Programas",null);
		
		return SUCCESS;
	}
	
	public String recuperaPagos(){
		try{
			programa = cDAO.consultaPrograma(idPrograma).get(0);
			/*Recupera el ciclo*/
			ciclo = programa.getCiclo();
			criterioPago = programa.getCriterioPago();
			/*Recupera los pagos del programa seleccionado por el usuario con estatus = 1*/
			lstPagosV = pDAO.consultaPagosV(-1, idPrograma, -1, "", "", "1");
//			claveOficio = "F00.4203/";
			claveOficio = "F00.4200/";
			anioOficio = "/"+new SimpleDateFormat("yyyy").format(new Date());
						
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String archivoEnvio(){
		return SUCCESS;
	}

	public String regresoArchivo(){
		return SUCCESS;
	}

	public String vistaPreviaOficio(){
		programa = cDAO.consultaPrograma(idPrograma).get(0);
		criterioPago = programa.getCriterioPago();
		Utilerias.getResponseISO();
		try {
			recuperDatosOficio(true);
			if(errorOficioDuplicado){
				addActionError("El número de oficio ya se encuentra registrado, por favor verifique");
				return SUCCESS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private void recuperDatosOficio(boolean html) throws Exception {
		StringBuilder dest = new StringBuilder();
		StringBuilder emi = new StringBuilder();
		String idPagoDetalle = "";
		Pagos pagosAux;
		int diasVigencia = 90;
		Date fechaVigenciaLimitePago;
		List<DocumentacionSPCartaAdhesionV> lstDocumentacionSPCartaAdhesion;
		String selectedPagosAux;
		leyendaConformidad = "De conformidad con los artículos 8, fracción VIII, 21 y Noveno Transitorio del Reglamento Interior de la Agencia de Servicios" +
				 " a la Comercialización y Desarrollo de Mercados Agropecuarios y el oficio No. F00.1000/001/2015";
		noDepositos = 0;
		lstPagosV = new ArrayList<PagosV>();
		String oficio =claveOficio +noOficio +anioOficio;
		/*Verifica que el numero de oficio no se encuentre en la base de datos*/
		if(pDAO.consultaOficiosPago(0, oficio,0).size()>0){
			errorOficioDuplicado = true;
		}else{
			selectedPagosAux = selectedPagos;
			selectedPagos = "";
			/**Ir por los pagos seleccionados para sumar los importes*/ 
			StringTokenizer tokens = new StringTokenizer(selectedPagosAux, ",");		
			while(tokens.hasMoreTokens()){
				  idPagoDetalle = tokens.nextToken();  
		            if(!idPagoDetalle.equals("-1")){
		            	pagosAux = pDAO.consultaPagos(Long.parseLong(idPagoDetalle)).get(0);
		            	//Si el pago no esta desde inscripcion no valida la fecha de expedicion del anexo
		            	if(spDAO.consultaCartaAdhesion(pagosAux.getNoCarta()).size()>0){
		            		// Valida vigencia del Anexo 32D y que no tenga observaciones  		            	
			            	lstDocumentacionSPCartaAdhesion = spDAO.consultaExpedientesSPCartaAdhesionV(pagosAux.getNoCarta(), 5, null, "folioCartaAdhesion");
							fechaVigenciaLimitePago = uDAO.getFechaDiaNaturalSumaDias(lstDocumentacionSPCartaAdhesion.get(0).getFechaExpedicionAnexo(), diasVigencia);
							String fechaMin = new SimpleDateFormat("yyyyMMdd").format(lstDocumentacionSPCartaAdhesion.get(0).getFechaExpedicionAnexo()).toString();
							String fechaMax = new SimpleDateFormat("yyyyMMdd").format(fechaVigenciaLimitePago).toString();	
							String fechaAutPago = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
							if((lstDocumentacionSPCartaAdhesion.get(0).getObservacion()==null 
								|| !lstDocumentacionSPCartaAdhesion.get(0).getObservacion()) 
								&& Long.parseLong(fechaAutPago) >= Long.parseLong(fechaMin) 
								&& Long.parseLong(fechaAutPago) <= Long.parseLong(fechaMax)){
				            	noDepositos +=1;
				            	lstPagosV.add(pDAO.consultaPagosV(Long.parseLong(idPagoDetalle)).get(0));
				            	if(selectedPagos==null || selectedPagos.isEmpty()){
				            		selectedPagos = idPagoDetalle;
				            	} else {
				            		selectedPagos = selectedPagos + "," + idPagoDetalle;
				            	}
							} else {
								addActionError("La vigencia del documento Anexo 32D (Fecha Anexo: "+fechaVigenciaLimitePago.toString()+") no es válida para la autorización del pago: "+ pagosAux.getIdPago() + " Carta: "+ pagosAux.getNoCarta() +" (Fecha Aut.: "+new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString()+") o el anexo 32D esta en estatus de observación, por favor verifique");
							}
		            	} else{
		            		noDepositos +=1;
			            	lstPagosV.add(pDAO.consultaPagosV(Long.parseLong(idPagoDetalle)).get(0));
			            	if(selectedPagos==null || selectedPagos.isEmpty()){
			            		selectedPagos = idPagoDetalle;
			            	} else {
			            		selectedPagos = selectedPagos + "," + idPagoDetalle;
			            	}
		            	}
		            		            	
		            }
			 }
			 totalImporte = pDAO.getSumaImportePagos(selectedPagos);
			 if(criterioPago == 1 || criterioPago == 3){
				 totalVolumen = pDAO.getSumaVolumenPagos(selectedPagos);
			 }
			 
			String residuo = " 00/100 M.N.";
			String tmp = TextUtil.formateaNumeroComoCantidad(totalImporte);
			int i = tmp.indexOf(".");
			if (i != -1)
			{
				residuo = tmp.substring(i + 1);
				switch (residuo.length())
				{
					case 0 :
						residuo = "00/100 M.N.";
						break;
					case 1 :
						residuo += "0/100 M.N.";
						break;
					case 2 :
						residuo += "/100 M.N.";
						break;
					default :
						residuo = residuo.substring(0, 2) + "/100 M.N.";
				}
			}
			importeLetra = "$ " + TextUtil.formateaNumeroComoCantidad(totalImporte) + " ("
					+ TextUtil.number2Text(totalImporte) + " pesos " + residuo + ")";
			
			importeLetra = importeLetra.toUpperCase();
			
			fechaActual = " a "+new SimpleDateFormat("dd").format(new Date()).toString()+" de "
						+ TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(new Date()).toString()))+" de "
						+ new SimpleDateFormat("yyyy").format(new Date()).toString();	
			/**Recupera administrativos**/
			//Recupera el destinatario
			lstPersonal = cDAO.consultaPersonal(0, "Director General de Administración y Finanzas", true, false,false);
			if(lstPersonal.size()>0){
				destinatario = lstPersonal.get(0);
				dest.append(lstPersonal.get(0).getNombre()).append(" ")
							.append(lstPersonal.get(0).getPaterno())
							.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
				destinatario.setNombre(dest.toString().toUpperCase());
				destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
			}
			
			//Recupera el emisor
			//lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", true, false, false);
			lstPersonal = cDAO.consultaPersonal(0, "Encargado del Despacho de la Dirección General de Desarrollo de Mercados e Infraestructura Comercial", true, false, false);
			//lstPersonal = cDAO.consultaPersonal(0, "Coordinador General de Comercialización", true, false, false);
			if(lstPersonal.size()>0){
				emisor = lstPersonal.get(0);
				emi.append(lstPersonal.get(0).getNombre()).append(" ")
					  .append(lstPersonal.get(0).getPaterno())
					  .append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
				emisor.setNombre(emi.toString().toUpperCase());
				emisor.setPuesto(emisor.getPuesto().toUpperCase());
				//emisor.setPuesto("EL DIRECTOR");
				//emisor.setPuesto("EL DIRECTOR GENERAL");
				//emisor.setPuesto("EL COORDINADOR GENERAL");
			}
			
			//Recupera los datos de quien elabora
			lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false, false);
			elaboro = lstPersonal.get(0);	
			elaboro.setNombre(elaboro.getNombre()+" "+(elaboro.getPaterno()!=null?elaboro.getPaterno():"")+(elaboro.getMaterno()!=null?elaboro.getMaterno():""));
			//Recupera los cpp y vobo.
			//if(idPrograma == 8){
//			lstPersonal = cDAO.consultaPersonalSQLQuery(0, "Director en Jefe de ASERCA", false, false,false, idPrograma);
//			if(lstPersonal.size()>0){
//				ccep1 = lstPersonal.get(0);
//			}
			lstPersonal = cDAO.consultaPersonalSQLQuery(0, "Coordinador General de Comercialización", false, false,false, idPrograma);  // AHS CAMBIO 02072015 			
			//lstPersonal = cDAO.consultaPersonalSQLQuery(0, "Encargado del Despacho de ASERCA", false, false,false, idPrograma);
			//lstPersonal = cDAO.consultaPersonalSQLQuery(0, "Director General de Desarrollo de Mercados", false, false,false, idPrograma);
			if(lstPersonal.size()>0){
				ccep2 = lstPersonal.get(0);
			}
//			lstPersonal = cDAO.consultaPersonalSQLQuery(0, "Director General de Desarrollo de Mercados", false, false,false, idPrograma);
//			if(lstPersonal.size()>0){
//				ccep3 = lstPersonal.get(0);
//			}
				//lstPersonalVoBo = cDAO.consultaPersonalSQLQuery(0, "", false, false, true, idPrograma);
			/*}else{
				lstPersonal = cDAO.consultaPersonal(0, "Director General de Desarrollo de Mercados", false, true,false);
				lstPersonalVoBo = cDAO.consultaPersonal(0, "", false, false, true);
			}*/
			
			//Recupera nombre de estado
			nombreEstado = cDAO.consultaEstado(idEstado).get(0).getNombre();
			//Recupera el tipo de forma
			String tF = cDAO.consultaParametros("TIPO_FORMA");
			tipoForma = Integer.parseInt(tF);
			/*Recupera el ciclo*/
			ciclo = cDAO.consultaPrograma(idPrograma).get(0).getCiclo();
			//ciclo = cDAO.consultaEdoXPrograma(idPrograma, idEstado).get(0).getCiclo();
			programa = cDAO.consultaPrograma(idPrograma).get(0);
			
			primerParrafo = "Con referencia a la operación del esquema ";
			p11Texto = ", sobre el particular, me permito informar que han sido autorizados los apoyos correspondientes " +
							"para que en lo conducente sean considerados dentro del proceso de dispersión de los recursos para el pago de los apoyos a ";
			p12Texto =" comprador";
			
			if(programa.getDesApoyoEnOficioClc()!= null && !programa.getDesApoyoEnOficioClc().isEmpty()){
				version = 0;
				nombrePrograma  = programa.getDesApoyoEnOficioClc();
				p13Texto =" del citado esquema.";
			}else{
				version = 1;
				nombrePrograma = cDAO.consultaPrograma(idPrograma).get(0).getDescripcionLarga();
				p13Texto =" del citado esquema, del ciclo ";	
			}			
			p14Texto = programa.getCiclo()+".";
			
			segundoParrafo = "Con base en lo anterior, solicito a esa Dirección General a su cargo se realicen las acciones necesarias para la elaboración de la Cuenta " +
					"por Liquidar Certificada (CLC) correspondiente e informe a esta Dirección General, para efectuar la vinculación de archivos de pago a TESOFE "+
					"por medio del sistema SIAFF; dicha autorización de emisión de apoyos se refiere a la dispersión de recursos correspondientes a ";					
			
			p21Texto = " depósitos ";
			p22Texto =" en cuentas bancarias a través de la Tesorería de la Federación, lo que significa una erogación de ";
			tercerParrafo = "Los pagos solicitados se encuentran identificados, dictaminados positivamente, verificados y validados a través de la debida integración de los expedientes y cuentan " +
					"con la documentación soporte completa, formalizada y validada. Los beneficiarios del apoyo, cuentan con la respectiva validación de la Dirección Regional correspondiente, " +
					" por lo que los importes determinados se encuentran debidamente registrados y aprobados."; 
			
			cuartoParrafo ="Este ejercicio, el control y la evaluación del gasto, se realizará conforme a lo establecido en la Ley Federal de Presupuesto y Responsabilidad " +
					"Hacendaria, la Ley General de Contabilidad Gubernamental y en las disposiciones que, en el marco de dichas leyes, estén establecidas en otros ordenamientos " +
					"legales y en el Presupuesto de Egresos de la Federación para el presente ejercicio fiscal "+Calendar.getInstance().get(Calendar.YEAR)+".";
			quintoParrafo = "Sin más por el momento reciba un cordial saludo.";
			//Recupera la leyenda del oficio
			leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
		}	
		
		
	}
	@SuppressWarnings("unused")
	public String generarOficioEnvioCGCDGAF() throws Exception{
		session = ActionContext.getContext().getSession();
		programa = cDAO.consultaPrograma(idPrograma).get(0);
		criterioPago = programa.getCriterioPago();
		recuperDatosOficio(false);
		if(errorOficioDuplicado){
			return SUCCESS;
		}
		try {
			/**Genera el oficio de envio de DGMD a DGAF en formato PDF*/
			Document document;
			
			rutaSalida = cDAO.consultaParametros("RUTA_OFICIO_ENVIO_CGC_DGAF");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			nombreOficio =new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"-Tesofe.pdf";
			rutaSalida +=nombreOficio;
			rutaRaiz = context.getRealPath("/");
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");
			rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
			//nombreArchivo = "informeRE04.pdf";
			nombreCoordinacion = cDAO.consultaParametros("NOMBRE_COORDINACION");
			OficioEnvioCGCDGAF pdf = new OficioEnvioCGCDGAF(this);
			pdf.generarOficioEnvioCGCDGAF();
			/**Guardando Oficio*/
			oficioPagos = new OficioPagos();
			oficioPagos.setNoOficio(claveOficio+noOficio+anioOficio);
			oficioPagos.setTotalImporte(totalImporte);
			oficioPagos.setTotalPagos(noDepositos);
			if(criterioPago == 1 || criterioPago ==3){
				oficioPagos.setTotalVolumen(totalVolumen);	
			}
			oficioPagos.setFecha(new Date());
			oficioPagos.setNombreOficio(nombreOficio);
			idOficio = pDAO.guardaOficioPagos(oficioPagos);
			/**Actualizando los pagos del oficio*/
			int registros = pDAO.actualizaPagosPorOficio(selectedPagos, idOficio, 2);
			log = "Se le informa que se generó Oficio:"+claveOficio+noOficio+anioOficio+" - Programa: "+ cDAO.consultaPrograma(idPrograma).get(0).getDescripcionCorta().toUpperCase()
				  +" - Número de Pagos: "+noDepositos+" - Importe: $" +TextUtil.formateaNumeroComoCantidad(totalImporte)
				  +((criterioPago == 1 || criterioPago ==3)?" - Volumen: "+TextUtil.formateaNumeroComoVolumen(totalVolumen):"");
			
			EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
			mensajes.enviarMensaje((Integer) session.get("idUsuario"), 5, log, "Aviso");
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al generar el oficio debido a:"+e.getCause());
	
		}
		
		
		return SUCCESS;
	}
	
	public String consigueOficio() throws Exception{
		try{
			List<OficioPagos> oficios = pDAO.consultaOficiosPago(idOficio, "",0);
			if(tipoArchivo.equals("oficioEnvioCgcDgaf")){
				rutaSalida = cDAO.consultaParametros("RUTA_OFICIO_ENVIO_CGC_DGAF");	
				nombreArchivo = oficios.get(0).getNombreOficio(); 
			}else if(tipoArchivo.equals("oficioCgcDgafEscaneo")){
				rutaSalida = cDAO.consultaParametros("OFICIO_ESCANEO_TESOFE");	
				nombreArchivo = oficios.get(0).getOficioCgcDgafEscaneo(); 
			}else if(tipoArchivo.equals("oficioDgafCgcEscaneo")){
				rutaSalida = cDAO.consultaParametros("OFICIO_ESCANEO_TESOFE");	
				nombreArchivo = oficios.get(0).getOficioDgafCgcEscaneo(); 
			}
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreArchivo,"pdf");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void recuperaCatalogos(String edos, String pgrs, String compradores) throws JDBCException, Exception {	
		if(edos!=null && !edos.equals("")){
			lstEstados = cDAO.consultaEstado(0);
		}
		if(pgrs!=null && !pgrs.equals("")){
			lstProgramas = cDAO.consultaPrograma(0);
		}
		if(compradores!=null && !compradores.equals("")){
			lstComprador = cDAO.consultaComprador(0,1);
		}
		
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
	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}
	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
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
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	public int getTipoForma() {
		return tipoForma;
	}
	public void setTipoForma(int tipoForma) {
		this.tipoForma = tipoForma;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
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
	public int getNoDepositos() {
		return noDepositos;
	}
	public void setNoDepositos(int noDepositos) {
		this.noDepositos = noDepositos;
	}
	public String getSelectedPagos() {
		return selectedPagos;
	}

	public String getImporteLetra() {
		return importeLetra;
	}
	public void setImporteLetra(String importeLetra) {
		this.importeLetra = importeLetra;
	}
	public void setSelectedPagos(String selectedPagos) {
		this.selectedPagos = selectedPagos;
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
	
	public Double getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(Double totalImporte) {
		this.totalImporte = totalImporte;
	}
	public Double getTotalVolumen() {
		return totalVolumen;
	}
	public void setTotalVolumen(Double totalVolumen) {
		this.totalVolumen = totalVolumen;
	}
	public boolean isErrorOficioDuplicado() {
		return errorOficioDuplicado;
	}
	
	public List<Personal> getLstPersonalVoBo() {
		return lstPersonalVoBo;
	}
	public void setLstPersonalVoBo(List<Personal> lstPersonalVoBo) {
		this.lstPersonalVoBo = lstPersonalVoBo;
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

	public int getCriterioPago() {
		return criterioPago;
	}

	public void setCriterioPago(int criterioPago) {
		this.criterioPago = criterioPago;
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

	public String getPrimerParrafo() {
		return primerParrafo;
	}

	public void setPrimerParrafo(String primerParrafo) {
		this.primerParrafo = primerParrafo;
	}

	public String getSegundoParrafo() {
		return segundoParrafo;
	}

	public void setSegundoParrafo(String segundoParrafo) {
		this.segundoParrafo = segundoParrafo;
	}

	public String getTercerParrafo() {
		return tercerParrafo;
	}

	public void setTercerParrafo(String tercerParrafo) {
		this.tercerParrafo = tercerParrafo;
	}

	public String getCuartoParrafo() {
		return cuartoParrafo;
	}

	public void setCuartoParrafo(String cuartoParrafo) {
		this.cuartoParrafo = cuartoParrafo;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public String getP11Texto() {
		return p11Texto;
	}

	public void setP11Texto(String p11Texto) {
		this.p11Texto = p11Texto;
	}

	public String getP12Texto() {
		return p12Texto;
	}

	public void setP12Texto(String p12Texto) {
		this.p12Texto = p12Texto;
	}

	public String getP21Texto() {
		return p21Texto;
	}

	public void setP21Texto(String p21Texto) {
		this.p21Texto = p21Texto;
	}

	public String getP22Texto() {
		return p22Texto;
	}

	public void setP22Texto(String p22Texto) {
		this.p22Texto = p22Texto;
	}
	
	public String getP13Texto() {
		return p13Texto;
	}

	public void setP13Texto(String p13Texto) {
		this.p13Texto = p13Texto;
	}

	public Personal getCcep1() {
		return ccep1;
	}

	public void setCcep1(Personal ccep1) {
		this.ccep1 = ccep1;
	}

	public Personal getCcep2() {
		return ccep2;
	}

	public void setCcep2(Personal ccep2) {
		this.ccep2 = ccep2;
	}

	public Personal getCcep3() {
		return ccep3;
	}

	public void setCcep3(Personal ccep3) {
		this.ccep3 = ccep3;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getP14Texto() {
		return p14Texto;
	}

	public void setP14Texto(String p14Texto) {
		this.p14Texto = p14Texto;
	}

	public String getQuintoParrafo() {
		return quintoParrafo;
	}

	public void setQuintoParrafo(String quintoParrafo) {
		this.quintoParrafo = quintoParrafo;
	}

	public Personal getElaboro() {
		return elaboro;
	}

	public void setElaboro(Personal elaboro) {
		this.elaboro = elaboro;
	}

	public String getLeyendaConformidad() {
		return leyendaConformidad;
	}

	public void setLeyendaConformidad(String leyendaConformidad) {
		this.leyendaConformidad = leyendaConformidad;
	}
	
	
	
}
