package mx.gob.comer.sipc.pagos.action;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import mx.gob.comer.sipc.dao.ArchivosPagosDAO;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.ArchivosPagos;
import mx.gob.comer.sipc.domain.FileItem;
import mx.gob.comer.sipc.domain.OficioPagos;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.Usuarios;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.Generales;
import mx.gob.comer.sipc.vistas.domain.PagosV;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.Valid;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GeneraArchivoPagosTesofeAction extends ActionSupport implements SessionAware {
		

	@Valid
	//variables para generacion del archivo
	private UtileriasDAO utileriasDAO = new UtileriasDAO();
	private List<PagosV> pagosV;
	private Pagos pagos;
	private OficioPagos oficio;
	private PagosDAO pDAO = new PagosDAO();
	private CatalogosDAO cDAO = new CatalogosDAO();
	private ArchivosPagosDAO archivosPagosDAO = new ArchivosPagosDAO();
	private ArchivosPagos archivo = new ArchivosPagos();
	private Long folio;
	private String nombreArchivo = null;
	private List<FileItem>         logs;
	private List<String>           lstLog       =new ArrayList<String>();
	private String log;
	
	private String oficioCGC; 
	private String folioCLC;
	private Long idOficio;
    private boolean errorBand;
    
	//Variables de session
	protected Integer   idUsuario;
	protected Integer 	rol;
	protected Usuarios	usuario;
	private int numSecuencia;

	private Map<String,Object> session;
	private Integer consecutivoArchivo;
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	public String execute(){		
		return SUCCESS;
	}

	public void validate(){		
		
	}
	public String generaArchivoPagosTesofe() throws Exception {
		try{
			boolean error = false;
			/*Valida que no exista el folio de la CLC*/
			if(archivosPagosDAO.consultaArchivoPagos(new SimpleDateFormat("yyyy").format(new Date()).toString(),Integer.parseInt(folioCLC)).size()>0){
				error = true;
				addActionError("El folio de la CLC ya se encuentra registrado, favor de verificar");
			}
			/*Valida que el numero del consecutivo no se encuentre en la base de datos*/
			if(archivosPagosDAO.consultaArchivoPagos(consecutivoArchivo.intValue(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()).size()>0){
				error=true;
				addActionError("Error en el número de archivo, ya encuentra registrado para la fecha actual");
				return SUCCESS;
			}
			if(error){
				return SUCCESS;	
			}
			
			session = ActionContext.getContext().getSession();
			System.setProperty("line.separator","\r\n");
			final String tipoRegEncabezadoD = "01";
			final String tipoRegDetalleD = "02";
			final String tipoRegSumarioD = "09";
			final String espacios = "                                        ";
			final String ceros = "000000000000000";
			final String codigoOperacionDep = "T";
			final Integer bancoIdTesofe = 167;
			final String bancoNomTesofe = "TESORERIA DE LA FEDERACION";
			final Integer medioPagoId = 1; // DEPOSITO EN CUENTA
			
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd");
			NumberFormat formatter1 = new DecimalFormat("00");
			NumberFormat formatter2 = new DecimalFormat("000000000000000");
			NumberFormat formatter4 = new DecimalFormat("0000000");
			NumberFormat formatter5 = new DecimalFormat("000000000000000000");		
			String registroEncabezado = "";
			String registroDetalle = "";
			String registroSumario = "";
			String usoFuturo = "";
			String usoFuturoCCE = "";
			String usoFuturoBanco = "";
			
			Integer i;
			Double importeTotal = 0.0;
			Date fechaAplicacionPago;
			Long secuenciaInicialArchivo, secuenciaFinalMaximoArchivo, j;
			//Long secuencia;
	
			// Obtiene la fecha actual para el archivo
			Calendar calendario = GregorianCalendar.getInstance();
			Date fecha = calendario.getTime();
	
			// Obtiene la fecha de aplicacion del pago
			Boolean isHoraPermitidoCADia = Generales.isHoraPermitida(utileriasDAO.getParametros("HORARIO_START_CA_DIA"), utileriasDAO.getParametros("HORARIO_TESOFE"));
			if (isHoraPermitidoCADia){
				fechaAplicacionPago = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(new Date()).toString(), 1);
			} else {
				fechaAplicacionPago = utileriasDAO.getFechaDiaHabilSumaDias(new SimpleDateFormat("yyyyMMdd").format(new Date()).toString(), 2);
			}
			
			// Obtiene la fecha de presentacion del pago 
			Date fechaPresentacion = utileriasDAO.getFechaDiaHabilRestaDias(fechaAplicacionPago, 1);
	
			// Obtiene la ruta del directorio donde se generan los archivos de pago
			String rutaEnvioArchivos =  utileriasDAO.getParametros("RUTA_ENVIO_ARCHIVOS");
			
		    // Obtiene información de los Pagos relacionados con el Oficio CGC a DGAF para generar en archivo
			if (pDAO.consultaOficiosPago(0, oficioCGC,0).size()>0){
				idOficio = pDAO.consultaOficiosPago(0, oficioCGC,0).get(0).getIdOficioPagos();
			    pagosV=pDAO.consultaPagosV(idOficio, 2);
		
				// Agrega mensajes a LOG
				AppLogger.info( "pagos", "*** Generacion Archivo de Pagos (INICIO) - Oficio CGC a DGAF: "+oficioCGC+"***" );
				addLogLista("*** Generacion Archivo de Pagos (INICIO) - Oficio CGC a DGAF: "+oficioCGC+"***", true);
				AppLogger.info( "pagos", " Fecha Presentacion: "+formatoDeFecha.format(fechaPresentacion)+
										 " Fecha Pago: "+formatoDeFecha.format(fechaAplicacionPago));
				addLogLista(" Fecha Presentacion: "+formatoDeFecha.format(fechaPresentacion)+
						 " Fecha Pago: "+formatoDeFecha.format(fechaAplicacionPago), true);
				AppLogger.info( "pagos", " Esquema de Apoyos: "+pagosV.get(0).getNombrePgrCorto());
				addLogLista(" Esquema de Apoyos: "+pagosV.get(0).getNombrePgrCorto(), true);
		
			    // Verifica si existen pagos
			    if(pagosV!=null&&pagosV.size()>0){
			    	String ramoGenerador = utileriasDAO.getParametros("RAMO_TESOFE");
			    	String oficinaGeneradora = utileriasDAO.getParametros("OFICINA_GENERADORA_TESOFE");
			    	String unidadResponsableGeneradora = utileriasDAO.getParametros("UNIDAD_RESPONSABLE_TESOFE");
			    	String cuentaTESOFE = utileriasDAO.getParametros("CUENTA_TESOFE");
			    	String rfcTESOFE = utileriasDAO.getParametros("RFC_TESOFE");
			    	
			    	
			    	for(;rfcTESOFE.length()<18;){
			    		rfcTESOFE+=" ";
	    			}
			    	
					// Crea el archivo de salida
		    		nombreArchivo = ramoGenerador+"_"+ // RAMO GENERADOR DE LA INFORMACION
		    						unidadResponsableGeneradora+"_"+ // UNIDAD RESPONSABLE GENERADORA
		    						folioCLC+"_"+ // FOLIO CLC
		    						formatoDeFecha.format(fechaPresentacion)+ // FECHA DE OPERACION
		    						".dat";
		    		
		    		// Crea archivo de salida en el servidor
		    		FileWriter fw = new FileWriter(rutaEnvioArchivos+nombreArchivo);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter salida = new PrintWriter(bw);
			
		    		// Uso futuro Registro Encabezado
					for(;usoFuturo.length()<386;){
						usoFuturo+=" ";
					}
		
		    		// Verifica que el consecutivo de archivo por día, banco TESOFE 
		    		//consecutivoArchivo = archivosPagosDAO.getMaximoConsecutivoArchivoPagos(bancoIdTesofe, new SimpleDateFormat("yyyy-MM-dd").format(fecha));
		
		    		// Obtiene secuencia inicial y final del ultimo archivo de pagos generado en el dia 
		    		secuenciaFinalMaximoArchivo = archivosPagosDAO.getSecuenciaFinalMaximoArchivo(bancoIdTesofe, new SimpleDateFormat("yyyy-MM-dd").format(fecha));
		    		secuenciaInicialArchivo = secuenciaFinalMaximoArchivo + 1;
		    		j = secuenciaInicialArchivo;
		    		//secuencia = new Long(1);
		    		
		    		numSecuencia = 1;
		    		
		    		
					// Concatenacion de campos que conforman el registro
		    		registroEncabezado = tipoRegEncabezadoD+ // TIPO REGISTRO
		    							 formatter4.format(numSecuencia)+ // NUMERO DE SECUENCIA
		    							 "60"+ // CODIGO DE OPERACION
		    							 bancoIdTesofe+ // BANCO PARTICIPANTE
		    							 "E"+ // SENTIDO
		    							 "2"+ // SERVICIO
		    							 formatoDeFecha.format(fechaPresentacion).substring(6,8)+ // NUMERO BLOQUE PARTE 1
		    							 oficinaGeneradora+ // NUMERO BLOQUE PARTE 2
		    							 formatter1.format(consecutivoArchivo)+ // NUMERO BLOQUE PARTE 3
		    							 formatoDeFecha.format(fechaPresentacion)+ // FECHA DE PRESENTACION
		    							 "01"+ // CODIGO DE DIVISA
		    							 "00"+ // CAUSA DE RECHAZO DE BLOQUE
		    							 "2"+ // MODALIDAD
		    							 usoFuturo; // USO FUTURO
		
		    		// Agrega registro encabezado al archivo de salida
					salida.println(registroEncabezado);		    				    	
		    		
		    		//Arma Registro Detalle
		    		for (i = 0, j++; i < pagosV.size(); i++, j++) {	
		    			//secuencia +=1;
		    			// Uso futuro Registro Detalle - USO FUTURO CCE
		    			for(;usoFuturoCCE.length()<16;){
		    				usoFuturoCCE+=" ";
		    			}
		    			// Uso futuro Registro Detalle - USO FUTURO ZONA BANCO
		    			for(;usoFuturoBanco.length()<11;){
		    				usoFuturoBanco+=" ";
		    			}
		
		    			// Rfc del Beneficiario
		    			String rfcReceptor = pagosV.get(i).getRfc();
		    			for(;rfcReceptor.length()<18;){
		    				rfcReceptor+=" ";
		    			}
		
		    			// Nombre del Ordenante
		    			String nombreOrdenante = bancoNomTesofe;
		    			for(;nombreOrdenante.length()<40;){
		    				nombreOrdenante+=" ";
		    			}
		
		    			// Clave de Rastreo
		    			String claveRastreo = obtenClaveRastreo(ramoGenerador, oficinaGeneradora, j, fechaAplicacionPago, fechaPresentacion);
		    			for(;claveRastreo.length()<30;){
		    				claveRastreo+=" ";
		    			}
		
		    			// Concepto
		    			String concepto = pagosV.get(i).getNombrePgrCorto();
		    			concepto=concepto.replace("/", " ");
		    			for(;concepto.length()<40;){
		    				concepto+=" ";
		    			}    		
		    			// Comprador
		    			String nombreReceptor = utileriasDAO.eliminaCaracteresEspeciales(pagosV.get(i).getNombreComprador());
		    			if(nombreReceptor.length()>=40){
		    				nombreReceptor = nombreReceptor.substring(0, 40);
		    			}else{
		    				for(;nombreReceptor.length()<40;){
		    					nombreReceptor+=" ";
		    				}
		    			}
		    			
		    			// Suma el importe de cada registro-pago
		    			importeTotal+=pagosV.get(i).getImporte();
		    			
		    			// Concatenacion de campos que conforman el registro
		    			//secuencia +=1;
		    			numSecuencia +=1;
		    			registroDetalle = 	tipoRegDetalleD+ // TIPO REGISTRO
		    								formatter4.format(numSecuencia)+ // NUMERO DE SECUENCIA
										 	"60"+ // CODIGO DE OPERACION
										 	"01"+ // CODIGO DE DIVISA
										 	formatoDeFecha.format(fechaAplicacionPago)+ // FECHA DE TRANSFERENCIA
										 	bancoIdTesofe+ // BANCO PRESENTADOR
										 	pagosV.get(i).getClabe().substring(0, 3)+ // BANCO RECEPTOR
		    								formatter2.format((pagosV.get(i).getImporte()*100))+ // IMPORTE
		    								usoFuturoCCE+ // USO FUTURO CCE
		    								"02"+ // TIPO DE OPERACION
		    								formatoDeFecha.format(fechaAplicacionPago)+ // FECHA DE APLICACION
		    								"40"+ // TIPO CUENTA DEL ORDENANTE
		    								cuentaTESOFE+ // NUMERO CUENTA DEL ORDENANTE
		    								nombreOrdenante+ // NOMBRE DEL ORDENANTE
		    								rfcTESOFE+ // RFC DEL ORDENANTE
		    								"40"+ // TIPO CUENTA DEL RECEPTOR
		    								"00"+pagosV.get(i).getClabe()+ // NUMERO CUENTA DEL RECEPTOR
		    								nombreReceptor+ // NOMBRE DEL RECEPTOR
		    								//utileriasDAO.eliminaCaracteresEspeciales(pagosV.get(i).getNombreComprador().substring(0, 40))+ // NOMBRE DEL RECEPTOR 
		    								rfcReceptor+ // RFC DEL RECEPTOR
		    								espacios+ // REFERENCIA DEL SERVICIO
		    								espacios+ // NOMBRE DEL TITULAR DEL SERVICIO
		    								ceros+ // IMPORTE DEL IVA DE LA OPERACION
		    								formatter4.format(pagosV.get(i).getIdPago())+ // REFERENCIA NUMERICA DEL ORD.
		    								concepto+ // REFERENCIA LEYENDA DEL ORD.
		    								claveRastreo+ // CLAVE DE RASTREO
		    								"00"+ // MOTIVO DE DEVOLUCION
		    							 	formatoDeFecha.format(fechaPresentacion)+ // FECHA DE PRESENTACION INICIAL
											"1"+ // SOLICITUD DE CONFIRMACION
											usoFuturoBanco; // USO FUTURO CCE
											
		    			// Actualiza el Pago de estatus "SOLICITADO" a "ENVIADO"
		    			pagos = pDAO.getPagos(pagosV.get(i).getIdPago()); 
		    			pagos.setEstatus(4);
		    			// OJO ver si aplica este codigo con LUZ 		pagos.setUsuarioModificacion(idUsuario);
		    			pagos.setUsuarioModificacion((Integer)session.get("idUsuario"));
		    			pagos.setFechaModificacion(fecha);
		    			pagos.setClaveRastreo(claveRastreo);
		    			pagos.setFechaPresentacion(fechaPresentacion);
		    			pagos.setFechaPago(fechaAplicacionPago);
		    			cDAO.guardaObjeto(pagos);
		
		    			// Agrega registro detalle al archivo de salida
		    			salida.println(registroDetalle);
		    		} // end for (i = 0; i < pagos.size(); i++)
		
		    		usoFuturoCCE = "";
					// Uso futuro Registro Sumario - USO FUTURO CCE
					for(;usoFuturoCCE.length()<40;){
						usoFuturoCCE+=" ";
					}
					usoFuturoBanco = "";
					// Uso futuro Registro Sumario - USO FUTURO BANCO
					for(;usoFuturoBanco.length()<339;){
						usoFuturoBanco+=" ";
					}
		
					numSecuencia +=1;
					// Concatenacion de campos que conforman el registro
		    		registroSumario = tipoRegSumarioD+ // TIPO REGISTRO
		    						  formatter4.format(numSecuencia)+ // NUMERO DE SECUENCIA
		    						  "60"+ // CODIGO DE OPERACION
		    						  formatoDeFecha.format(fechaPresentacion).substring(6,8)+ // NUMERO BLOQUE PARTE 1
		    						  oficinaGeneradora+ // NUMERO BLOQUE PARTE 2
		    						  formatter1.format(consecutivoArchivo)+ // NUMERO BLOQUE PARTE 3
		    						  formatter4.format(pagosV.size())+// NUMERO DE OPERACIONES
		    						  formatter5.format((importeTotal*100))+ // IMPORTE  TOTAL 
		    						  usoFuturoCCE+ // USO FUTURO CCE
		    						  usoFuturoBanco; // USO FUTURO BANCO
		
		    		// Agrega registro sumario al archivo de salida
					salida.println(registroSumario);
		
		    		// Cierra el archivo de salida
				    salida.close();
				    AppLogger.info( "pagos", "Archivo de Pagos TESOFE Generado: "+nombreArchivo );
				    addLogLista("Archivo de Pagos TESOFE Generado: "+nombreArchivo, true);
		
				    // Inserta en BD el archivo de salida generado
				    archivo.setBancoId(bancoIdTesofe);
				    archivo.setCodigoOperacion(codigoOperacionDep);
				    archivo.setConsecutivoArchivo(consecutivoArchivo);
				    archivo.setFechaArchivo(fecha);
				    archivo.setFolio(folioCLC);
				    archivo.setNombreArchivo(nombreArchivo);
				    archivo.setMedioPago(medioPagoId);
				    archivo.setSecuenciaInicial(secuenciaInicialArchivo);
				    archivo.setSecuenciaFinal(j);
				    archivo.setIdOficio(idOficio);
				    archivo.setEstatus(1);
				    archivosPagosDAO.saveArchivosPagos(archivo);
				    AppLogger.info( "pagos", "Insertando Registro en Archivos_Pagos" );
				    addLogLista("Insertando Registro en Archivos_Pagos", true);
			    } // end if(pagos!=null&&pagos.size()>0)
		
			    // Actualiza el folio CLC y nombre de archivo envio al oficio pagos
			    oficio = pDAO.consultaOficiosPago(idOficio, "",0).get(0);
			    oficio.setArchivoEnvio(nombreArchivo);
			    oficio.setFolioCLC(Integer.parseInt(folioCLC));
			    cDAO.guardaObjeto(oficio);
			    AppLogger.info( "pagos", "Actualizando archivo de envio y folio CLC al oficio de pagos" );
			    addLogLista("Actualizando archivo de envio y folio CLC al oficio de pagos", true);	
			    log = "Se le informa que se generó archivo de pagos:"+nombreArchivo+" - Programa: "+pagosV.get(0).getNombrePgrCorto()
			    	 + " - CLC: "+folioCLC +" - Oficio CGC a DGAF: "+oficioCGC+" - Fecha presentación:"+new SimpleDateFormat("dd-MM-yyyy").format(fechaPresentacion)
			    	 + " - Fecha de Pago: "+new SimpleDateFormat("dd-MM-yyyy").format(fechaAplicacionPago);
			    EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
				mensajes.enviarMensaje((Integer) session.get("idUsuario"), 6, log, "Aviso");
			    
			} else {
				errorBand = true;
				AppLogger.error("pagos", "Generacion Archivo de Pagos: El Oficio de Pagos: "+oficioCGC+" no existe en la base de datos, verifiquelo!!!");
				addLogLista("El Oficio de Pagos: "+oficioCGC+" no existe en la base de datos, verifiquelo!!!", false);
			} // end if (pDAO.consultaOficiosPago(0, oficioCGC).size()>0)
		}catch (Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error inesperado al generar el archivo, debido a: "+e.getCause());
			addActionError("Ocurrió un error inesperado, favor de informar al administrador del sistema");
		}
		return SUCCESS;
	} // end generaArchivoPagosTESOFE()
	
	public String obtenClaveRastreo(String ramo, String oficina, Long consecutivo, Date fechaPago, Date fechaPresenta) throws Exception{
		String claveRastreoAux;
		String claveRastreo;
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatoDeFecha1 = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat formatter = new DecimalFormat("000000");
		NumberFormat formatter2 = new DecimalFormat("000");
		NumberFormat formatter3 = new DecimalFormat("00");
		
		String tipoNomina = utileriasDAO.getParametros("TIPO_NOMINA_TESOFE");
		
		// Concatenacion de campos que conforman el registro
		claveRastreoAux = "40"+ // CODIGO DE PAGO
						  formatoDeFecha.format(fechaPresenta).substring(0,4)+ // AÑO CALENDARIO
						  ramo+ // RAMO GENERADOR DE PAGO
						  oficina+ // OFICINA GENERADORA DE PAGO
						  formatter2.format(utileriasDAO.getNumeroDiaAnio(formatoDeFecha1.format(fechaPresenta)))+ // DIA CONSECUTIVO DEL AÑO
						  formatter3.format(utileriasDAO.getNumeroQuincenas(formatoDeFecha1.format(fechaPago)))+ // NUMERO DE QUINCENA A PAGAR
						  tipoNomina+ // TIPO NOMINA
						  formatter.format(consecutivo); // CONSECUTIVO ARCHIVO
						   
		claveRastreo = claveRastreoAux+utileriasDAO.getClaveRastreoTESOFE(claveRastreoAux).toString();
		return claveRastreo;
	} // end obtenClaveRastreo()

	private void addLogLista(String msj, boolean info) {
		String valor="";
		if(lstLog.size()==0){
			lstLog.add(msj);
		}else if(lstLog.size()<50){
			if(info){
				valor="[INFO]";
			}else{
				valor="[ERROR]";
			}
			lstLog.add(valor+" - "+msj);
		}else if(lstLog.size()==50){
			lstLog.add("Siga la traza completa en el log....");
		}
	} // end addLogLista

	public String descargarArchivoPagosEnvio()
	{
		//	Abrir ventana para descargar el archivo
		try
		{			
			returnFile(nombreArchivo);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppLogger.error( "pagos", "Problemas al leer archivo: "+nombreArchivo+" para su descarga");
			addLogLista("Problemas al leer archivo: "+nombreArchivo+" para su descarga", false);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppLogger.error( "pagos", "Problemas al descargar archivo: "+nombreArchivo);
			addLogLista("Problemas al descargar archivo: "+nombreArchivo, false);
		}
		
		return null;
	} // end descargarArchivoPagosEnvio()

	private  void returnFile(String filename) throws FileNotFoundException, IOException {
		HttpServletResponse response 		= ServletActionContext.getResponse();
		StringBuilder 		attachment 		= new StringBuilder();
		
		response.setContentType("application/txt"); // se regresa un txt
		OutputStream 		out 			= response.getOutputStream();		
		InputStream 		in 				= null;
		String 				rutaRaiz 		= "/SIPC/";
		String 				rutaCarpetaTxt 	= "archivosTESOFEEnvio/";
		File 				archivoTxt 		= new File(rutaRaiz + rutaCarpetaTxt + filename);
		attachment.append("attachment; filename=").append(filename);
		response.setHeader("Content-Disposition", attachment.toString());
		
		try {
			in = new BufferedInputStream(new FileInputStream(archivoTxt.getAbsoluteFile()));
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				out.write(buf, 0, bytesRead);
			}
		} finally {
			if (in != null)
				in.close();
//			if(out != null)
//				out.close();
		}
	} // end returnFile()

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public void setArchivo(ArchivosPagos archivo) {
		this.archivo = archivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public ArchivosPagos getArchivo() {
		return archivo;
	}

	
	
	public boolean isErrorBand() {
		return errorBand;
	}

	public void setErrorBand(boolean errorBand) {
		this.errorBand = errorBand;
	}

	public List<FileItem> getLogs() {
		return logs;
	}

	public void setLogs(List<FileItem> logs) {
		this.logs = logs;
	}

	public List<String> getLstLog() {
		return lstLog;
	}

	public void setLstLog(List<String> lstLog) {
		this.lstLog = lstLog;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public String getOficioCGC() {
		return oficioCGC;
	}

	public void setOficioCGC(String oficioCGC) {
		this.oficioCGC = oficioCGC;
	}

	public String getFolioCLC() {
		return folioCLC;
	}

	public void setFolioCLC(String folioCLC) {
		this.folioCLC = folioCLC;
	}

	public Long getIdOficio() {
		return idOficio;
	}

	public void setIdOficio(Long idOficio) {
		this.idOficio = idOficio;
	}

	public List<PagosV> getPagosV() {
		return pagosV;
	}

	public void setPagosV(List<PagosV> pagosV) {
		this.pagosV = pagosV;
	}

	public Pagos getPagos() {
		return pagos;
	}

	public void setPagos(Pagos pagos) {
		this.pagos = pagos;
	}

	public OficioPagos getOficio() {
		return oficio;
	}

	public void setOficio(OficioPagos oficio) {
		this.oficio = oficio;
	}
	
	public Integer getConsecutivoArchivo() {
		return consecutivoArchivo;
	}

	public void setConsecutivoArchivo(Integer consecutivoArchivo) {
		this.consecutivoArchivo = consecutivoArchivo;
	}

	/* Implementar Session aware*/
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
}
