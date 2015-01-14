package mx.gob.comer.sipc.action.solicitudpago;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Bancos;
import mx.gob.comer.sipc.domain.Bitacora;
import mx.gob.comer.sipc.domain.CuentaBancaria;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.PagosDetalle;
import mx.gob.comer.sipc.domain.Personal;
import mx.gob.comer.sipc.domain.PlazasBancarias;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Especialista;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.EtapaIniEsquema;
import mx.gob.comer.sipc.domain.transaccionales.OficioObsSolicitudPago;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.oficios.pdf.AtentaNotaPagos;
import mx.gob.comer.sipc.oficios.pdf.AtentaNotaPagosC;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.AsignacionCartasAdhesionEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.AsignacionCartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.CartaAdhesionEtapaVolImpV;
import mx.gob.comer.sipc.vistas.domain.CartasPrimerPagoV;
import mx.gob.comer.sipc.vistas.domain.DocumentacionSPCartaAdhesionV;
import mx.gob.comer.sipc.vistas.domain.ExpedientesProgramasV;
import mx.gob.comer.sipc.vistas.domain.PagosCartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleCAV;
import mx.gob.comer.sipc.vistas.domain.PagosV;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lowagie.text.Document;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapturaPagosCartaAdhesionAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{

	private Map<String, Object> session;
	private ServletContext context;
	/**** BD ****/
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private InscripcionDAO iDAO = new InscripcionDAO();
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private PagosDAO pDAO = new PagosDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	/***LISTAS**/
	private List<Programa> lstProgramas;
	private List<Especialista> lstEspecialista;
	private List<AsignacionCartasAdhesionV> lstAsigCA;
	private List<AsignacionCAaEspecialistaV> lstAsignacionCAaEspecialistaV;
	private List<ExpedientesProgramasV> lstExpedientesProgramasV;
	private List<OficioObsSolicitudPago> lstOficioObsSolicitudPago;
	private List<AsignacionCartasAdhesionEspecialistaV> lstDetallePagosCAEspecialistaV, lstPagosVistaPrevia;
	private List<PagosV> lstConsultaPagosCAEspecialista;
	private List<CuentaBancaria> lstCuentaBancarias;
	private List<EtapaIniEsquema> lstEtapaIniEsquema;
	private List<CartaAdhesionEtapaVolImpV> lstCartaAdhesionEtapaVolImpV;
	private List<DocumentacionSPCartaAdhesionV> lstDocumentacionSPCartaAdhesion;
	private List<PagosDetalleCAV> lstEditDetallePagosCAEspecialistaV;
	private AsignacionCAaEspecialistaV lstEspecialistaCA;
	private List<Personal> lstPersonal;
	private List<Bancos> bancos;
	private Personal destinatario;
	private Especialista emisor;
	private Programa programa;
	private Pagos p;
	/***Variables de formulario**/
	private int tieneObservacion;
	private int tipoDocumentacion;
	private int idCriterioPago;
	private int idEspecialista;
	private int idPrograma;
	private String folioCartaAdhesion; 
	private int documentacion;
	private String nombrePrograma;
	private Integer criterioPago;
	private String descCriterioPago;
	private String estatusCA;
	private String fianza;
	private Integer numCampos;
	private Long idPago;
	private Integer idComprador;
	private String nombreComprador;
	private PlazasBancarias plaza;
	private List<PlazasBancarias> lstPlazas;
	private String clabe;
	private String nombrePlaza;
	private String ctaBancaria;
	private String cuenta;
	private String nombreBanco;
	private String sucursal;
	private Map<Integer,String> capVolumen;
	private Map<Integer,String> capImporte;
	private Map<Integer,String> capEtapa;
	private Double[] cuotasApoyo;
	private Double[] volumenesAut;
	private Double[] importesAut;
	private String atentaNotaNum;
	private String fechaActual;
	private String fechaAtentaNota;
	private String leyendaCriterio;
	private Double cantidadCriterio;
	private String cultivo;
	private String numeroPago;
	
	private String rutaRaiz;
	private String rutaSalida;
	private String nombreArchivo;
	private String nombreCoordinacion;
	private String selectedPagos;
	private String rutaImagen;
	private String leyendaOficio;
	private String rutaMarcaAgua;
	private String rutaAserca;
	private String ramo;
	private String unidadResponsable;
	private String leyendaEsquema;
	private String textoFianza;

	private String log;
	private String msjOk;
	private int errorSistema;
	private int registrar;
	private boolean band = false;
	private boolean tieneFianza;
	private Integer  solicitudesAtendidas;
	private Integer productoresBeneficiados;
	private boolean cerrarExpediente;
	private Integer idEstatusCA;	
	private Personal voBo;
	private Double porcentajeFianza;
	private List<CartasPrimerPagoV> lstCartasPrimerPagoV;
	private Double volumenPago;
	private Double importePago;
	
	private Double volumenCertificados;
	private Double volumenConstancias;
	
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	/***METODOS**/
	public String listarProgramasPagosCartaAdhesion(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}
			
			if(idPerfil == 6){
				lstProgramas= spDAO.consultaProgramasAsignadosAEspecialista(idEspecialista, true);
			}else if(idPerfil == 10){
				lstProgramas = spDAO.consultarProgramasPrimerPagoEfectuado();
			}
						
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listarProgramasPagosCartaAdhesion debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listarProgramasPagosCartaAdhesion debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}
	
	public String verCartaAdhesionAsignadasPagos(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}
			if(idPerfil == 6){
				lstAsignacionCAaEspecialistaV = spDAO.consultaCAaEspecialistaV(idEspecialista, idPrograma, true);
			}else if(idPerfil == 10){
				lstCartasPrimerPagoV = spDAO.consultarCartasPrimerPagoEfectuado(idPrograma, null);
			}
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en verCartaAdhesionAsignadasPagos  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en verCartaAdhesionAsignadasPagos  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}		

	public String detallePagosCartaAdhesion(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}
			lstDetallePagosCAEspecialistaV = spDAO.verCartaAdhesionAsignadasPagos(idEspecialista, folioCartaAdhesion);
			numCampos = lstDetallePagosCAEspecialistaV.size();
			criterioPago = lstDetallePagosCAEspecialistaV.get(0).getIdCriterioPago();
			descCriterioPago = lstDetallePagosCAEspecialistaV.get(0).getCriterioPago();
			clabe = lstDetallePagosCAEspecialistaV.get(0).getClabe();
			idComprador = lstDetallePagosCAEspecialistaV.get(0).getIdComprador();
			idPrograma = lstDetallePagosCAEspecialistaV.get(0).getIdPrograma();
			folioCartaAdhesion = lstDetallePagosCAEspecialistaV.get(0).getFolioCartaAdhesion();
			idEspecialista = lstDetallePagosCAEspecialistaV.get(0).getIdEspecialista();
			estatusCA = lstDetallePagosCAEspecialistaV.get(0).getDescEstatus();
			nombreComprador = lstDetallePagosCAEspecialistaV.get(0).getComprador();
			nombrePrograma = lstDetallePagosCAEspecialistaV.get(0).getPrograma();
			fianza = lstDetallePagosCAEspecialistaV.get(0).getDescFianza();
			recuperaDatosPlaza();
			fechaAtentaNota = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
			solicitudesAtendidas =  lstDetallePagosCAEspecialistaV.get(0).getSolicitudesAtendidas()!=null ? lstDetallePagosCAEspecialistaV.get(0).getSolicitudesAtendidas() : 0;
			productoresBeneficiados = lstDetallePagosCAEspecialistaV.get(0).getProductoresBeneficiados()!=null ? lstDetallePagosCAEspecialistaV.get(0).getProductoresBeneficiados() : 0;
			/*Recupera las cuentas bancarias asociadas al comprador*/
			//lstCuentaBancarias = cDAO.consultaCtaBancaria(idComprador,1);
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en detallePagosCartaAdhesion  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en detallePagosCartaAdhesion  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}	

	
	public String capturaPagoCartaAdhesion() throws Exception{
		Double importeEtapaI=0.0, importeEtapaII=0.0, importeEtapaIII=0.0, importeEtapaIV=0.0;
		Double totalVolApoEdoCulVarCA = 0.0, volumenAutEdoCulVarCA=0.0;
		int diasVigencia = 90;
		Date fechaVigenciaLimitePago; 
		StringBuilder dest = new StringBuilder();
		String vEstadoAux = "", vCultivoAux = "", vVariedadAux = "", vBodegaAux = "";
		
		try{
			errorSistema = 0;
			/* Guarda los datos del pago*/
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}			
			
			if(cerrarExpediente){
				//Cambiar el estatus de la carta de adhesion a 9 "EXPEDIENTE CERRADO"
				CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
				ca.setEstatus(9);
				cDAO.guardaObjeto(ca);
				
			}
			
			// Valida que la atenta nota no se duplique por año
			//if (pDAO.consultaPagos(0, null, null, 0, atentaNotaNum+"-"+new java.text.SimpleDateFormat("yyyy").format(new Date() )).size()==0){
				// Valida vigencia del Anexo 32D y que no tenga observaciones 			
				lstDocumentacionSPCartaAdhesion = spDAO.consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, 5, null, "folioCartaAdhesion");
				fechaVigenciaLimitePago = uDAO.getFechaDiaNaturalSumaDias(lstDocumentacionSPCartaAdhesion.get(0).getFechaExpedicionAnexo(), diasVigencia);
	
				String fechaMin = new SimpleDateFormat("yyyyMMdd").format(lstDocumentacionSPCartaAdhesion.get(0).getFechaExpedicionAnexo()).toString();
				String fechaMax = new SimpleDateFormat("yyyyMMdd").format(fechaVigenciaLimitePago).toString();	
				String fechaPago = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
				
				if(cDAO.consultaComprador(lstDocumentacionSPCartaAdhesion.get(0).getIdComprador()).get(0).getEstatus()==1){
					if((lstDocumentacionSPCartaAdhesion.get(0).getObservacion()==null || !lstDocumentacionSPCartaAdhesion.get(0).getObservacion()) && Long.parseLong(fechaPago) >= Long.parseLong(fechaMin) && Long.parseLong(fechaPago) <= Long.parseLong(fechaMax)){				
						
						lstDetallePagosCAEspecialistaV = spDAO.verCartaAdhesionAsignadasPagos(idEspecialista, folioCartaAdhesion);
						fianza = lstDetallePagosCAEspecialistaV.get(0).getDescFianza();
						if (criterioPago==1){
							Set<Integer> idsCapVolumen = capVolumen.keySet();
							Iterator<Integer> it = idsCapVolumen.iterator();
							while(it.hasNext()){
								Integer idCapVolumen = it.next();
								String item = capVolumen.get(idCapVolumen);
								if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
									for (int i = 0; i < lstDetallePagosCAEspecialistaV.size(); i++) {
										if (lstDetallePagosCAEspecialistaV.get(i).getIdAsiganacionCA() == idCapVolumen){
											if(lstDetallePagosCAEspecialistaV.get(i).getBodega()==null||lstDetallePagosCAEspecialistaV.get(i).getBodega().isEmpty()){
												List<PagosCartasAdhesionV> pca = pDAO.consultaPagosEdoCulVarCA(folioCartaAdhesion, lstDetallePagosCAEspecialistaV.get(i).getIdEstado(), lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad(),null);
												if (pca.size()>0){
													totalVolApoEdoCulVarCA = pca.get(0).getVolumen();
												} else {
													totalVolApoEdoCulVarCA = 0.0;
												}
												volumenCertificados = spDAO.getSumaCertificadoDepositoByFolioCABodegaCultVar(folioCartaAdhesion, null, lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad());
												if(volumenCertificados==0.0){
													volumenConstancias = spDAO.getgetSumaConstanciasAlmacenamientoByFolioCABodegaCultVar(folioCartaAdhesion, null, lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad());
												}
											} else {
												List<PagosCartasAdhesionV> pca = pDAO.consultaPagosEdoCulVarCA(folioCartaAdhesion, lstDetallePagosCAEspecialistaV.get(i).getIdEstado(), lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad(), lstDetallePagosCAEspecialistaV.get(i).getBodega());
												if (pca.size()>0){
													totalVolApoEdoCulVarCA = pca.get(0).getVolumen();
												} else {
													totalVolApoEdoCulVarCA = 0.0;
												}
												volumenCertificados = spDAO.getSumaCertificadoDepositoByFolioCABodegaCultVar(folioCartaAdhesion, lstDetallePagosCAEspecialistaV.get(i).getBodega(), lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad());
												if(volumenCertificados==0.0){
													volumenConstancias = spDAO.getgetSumaConstanciasAlmacenamientoByFolioCABodegaCultVar(folioCartaAdhesion, lstDetallePagosCAEspecialistaV.get(i).getBodega(), lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad());
												}
											}											
											volumenAutEdoCulVarCA = lstDetallePagosCAEspecialistaV.get(i).getVolumen();
											vEstadoAux = lstDetallePagosCAEspecialistaV.get(i).getEstado();
											vCultivoAux = lstDetallePagosCAEspecialistaV.get(i).getCultivo();
											vVariedadAux = lstDetallePagosCAEspecialistaV.get(i).getVariedad();
											vBodegaAux = lstDetallePagosCAEspecialistaV.get(i).getBodega();
											
											break;
										}
									}
									Double suma = Double.parseDouble(item)+totalVolApoEdoCulVarCA;
									//System.out.println("suma antes "+suma);
									suma =Double.parseDouble(TextUtil.formateaNumeroComoVolumenSinComas(suma));
									//System.out.println(suma);
									if (volumenCertificados>0.0){
										if (suma > volumenCertificados){
											errorSistema = 1;
											if(vBodegaAux==null||vBodegaAux.isEmpty()) {
												addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
															   " no puede ser mayor al volumen en Certificados de Depósito: "+volumenCertificados+
															   " de la carta de adhesión para el cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+", por favor verifique");
											} else {
												addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
															   " no puede ser mayor al volumen en Certificados de Depósito: "+volumenCertificados+
															   " de la carta de adhesión para el cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+" bodega: "+vBodegaAux+", por favor verifique");											
											}
											return SUCCESS;												
										}
									} else {
										if (suma > volumenConstancias){
											errorSistema = 1;
											if(vBodegaAux==null||vBodegaAux.isEmpty()) {
												addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
															   " no puede ser mayor al volumen en Constancias de Almacenamiento: "+volumenConstancias+
															   " de la carta de adhesión para el cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+", por favor verifique");
											} else {
												addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
															   " no puede ser mayor al volumen en Constancias de Almacenamiento: "+volumenConstancias+
															   " de la carta de adhesión para el cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+" bodega: "+vBodegaAux+", por favor verifique");											
											}
											return SUCCESS;												
										}											
									}									
									if (suma > volumenAutEdoCulVarCA){
										errorSistema = 1;
										if(vBodegaAux==null||vBodegaAux.isEmpty()) {
											addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
														   " no puede ser mayor al volumen autorizado: "+volumenAutEdoCulVarCA+
														   " de la carta de adhesión para el estado: "+vEstadoAux+
														   " cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+", por favor verifique");
										} else {
											addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
													   " no puede ser mayor al volumen autorizado: "+volumenAutEdoCulVarCA+
													   " de la carta de adhesión para el estado: "+vEstadoAux+
													   " cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+" bodega: "+vBodegaAux+", por favor verifique");											
										}
										return SUCCESS;
									}
								}
							}
						} else if (criterioPago==2){	
							Set<Integer> idsCapImporte = capImporte.keySet();
							Iterator<Integer> it = idsCapImporte.iterator();
							while(it.hasNext()){
								Integer idCapImporte = it.next();
								String item = capEtapa.get(idCapImporte);
								if(item!=null && !item.trim().isEmpty()){
									if(item=="I"){
										importeEtapaI += Double.parseDouble(capImporte.get(idCapImporte));
									} else if(item=="II"){
										importeEtapaII += Double.parseDouble(capImporte.get(idCapImporte));
									} else if(item=="III"){
										importeEtapaIII += Double.parseDouble(capImporte.get(idCapImporte));
									} else if(item=="IV"){
										importeEtapaIV += Double.parseDouble(capImporte.get(idCapImporte));
									}									
								}
							}
							if(importeEtapaI>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 1);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 1);
								if ((importeEtapaI+lstCartaAdhesionEtapaVolImpV.get(0).getImporte())>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaI+" más el importe apoyado: "+lstCartaAdhesionEtapaVolImpV.get(0).getImporte()+" en la etapa I de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							} 
							if(importeEtapaII>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 2);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 2);
								if ((importeEtapaII+lstCartaAdhesionEtapaVolImpV.get(0).getImporte())>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaII+" más el importe apoyado: "+lstCartaAdhesionEtapaVolImpV.get(0).getImporte()+" en la etapa II de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							} 
							if(importeEtapaIII>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 3);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 3);
								if ((importeEtapaIII+lstCartaAdhesionEtapaVolImpV.get(0).getImporte())>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaIII+" más el importe apoyado: "+lstCartaAdhesionEtapaVolImpV.get(0).getImporte()+" en la etapa III de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							} 
							if(importeEtapaIV>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 4);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 4);
								if ((importeEtapaIV+lstCartaAdhesionEtapaVolImpV.get(0).getImporte())>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaIV+" más el importe apoyado: "+lstCartaAdhesionEtapaVolImpV.get(0).getImporte()+" en la etapa IV de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							}
						} else if (criterioPago==3){
						}
		
		                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		                Date fechaActualConvert = dateFormat.parse(fechaAtentaNota); 
		                           
		                fechaActual = " a "+new SimpleDateFormat("dd").format(fechaActualConvert).toString()+" de "
		                                   + TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(fechaActualConvert).toString()))+" de "
		                                   + new SimpleDateFormat("yyyy").format(fechaActualConvert).toString();
						//Recupera el destinatario
						lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false,false);
						if(lstPersonal.size()>0){
							destinatario = lstPersonal.get(0);
							dest.append(lstPersonal.get(0).getNombre()).append(" ")
										.append(lstPersonal.get(0).getPaterno())
										.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
							destinatario.setNombre(dest.toString().toUpperCase());
							destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
						}
						
						//Recupera el emisor
						lstEspecialista = cDAO.consultaEspecialista(idEspecialista);
						if(lstEspecialista.size()>0){
							emisor = lstEspecialista.get(0);
							emisor.setNombre(emisor.getNombre().toUpperCase());
							emisor.setPuesto(emisor.getPuesto().toUpperCase());
						}
						//Recupera la leyenda del oficio
						leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
						
						guardarPagos();
						generarAtentaNotaPago();
						PagosV pv = new PagosV();
						pv = pDAO.consultaPagosV(idPago).get(0);
								
						log = "Se le informa que se capturó un pago del Programa: "+pv.getNombrePgrCorto().toUpperCase()+" - Comprador: "+pv.getNombreComprador().toUpperCase()
							   + " - Carta: "+pv.getNoCarta()+ " - clabe:"+ pv.getClabe()
							   +" - importe: $"+ TextUtil.formateaNumeroComoCantidad(pv.getImporte())+ (pv.getVolumen()!=null ? " - volumen:"+ TextUtil.formateaNumeroComoVolumen(pv.getVolumen()):"")+
							   (pv.getEtapa()!=null ? " - Etapa: "+pv.getEtapa():"" );
						cDAO.guardaObjeto(new Bitacora((Integer) session.get("idUsuario"), 3, new Date(),log));
						/*Enviar aviso de la captura de pago*/
						EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
						mensajes.enviarMensaje((Integer) session.get("idUsuario"), 3, log, "Aviso");
						detallePagosCartaAdhesion();
						msjOk = "Se agrego correctamente el pago";
					} else {
						errorSistema = 1;
						addActionError("La vigencia del documento Anexo 32D ("+fechaVigenciaLimitePago.toString()+") no es válida para el registro del pago ("+new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString()+") o el anexo 32D esta en estatus de observación, por favor verifique");
						return SUCCESS;
					}			
			} else {
				errorSistema = 1;
				addActionError("El comprador esta en estatus Inhabilitado por lo que no procede el registro del pago, por favor verifique");
				return SUCCESS;
			}
					
//			} else {
//				errorSistema = 1;
//				addActionError("El número de la Atenta Nota: "+atentaNotaNum+" ya esta registrada, por favor verifique");
//				return SUCCESS;
//			}
		}catch (JDBCException e) {
	    	errorSistema = 1;
			addActionError("Ocurrio un error al guardar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrio un error al guardar el pago en base de datos debido a: "+e.getCause());
			e.printStackTrace();
		}catch(Exception e){
	    	errorSistema = 1;
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");		
			AppLogger.error("errores","Ocurrio un error inesperado al guardar el pago debido a: "+e.getCause());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	private void guardarPagos() throws JDBCException, Exception{
		double totalImporte =0;
		double totalVolumen =0;
		String etapaPago="", vBodegaAux = "";
		Integer vEstadoAux = 0, vCultivoAux = 0, vVariedadAux = 0;
		Double vCuotaAux = 0.0;
		p = new Pagos();
		p.setClabe(lstDetallePagosCAEspecialistaV.get(0).getClabe());
		p.setEstatus(7);
		p.setFechaCreacion(new Date());
		p.setIdComprador(lstDetallePagosCAEspecialistaV.get(0).getIdComprador());
		p.setIdPrograma(lstDetallePagosCAEspecialistaV.get(0).getIdPrograma());
		p.setNoCarta(folioCartaAdhesion);
		p.setAtentaNota(new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActualConvert = dateFormat.parse(fechaAtentaNota); 
		p.setFechaAtentaNota(fechaActualConvert);
		p.setFianza(tieneFianza);
		if(tieneFianza){
			p.setPorcentajeFianza(porcentajeFianza);
		}
		p.setPagosDetalle(new HashSet<PagosDetalle>());
		p.setSolicitudesAtendidas(solicitudesAtendidas);
		p.setProductoresBeneficiados(productoresBeneficiados);
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2); 
	    nf.setGroupingUsed(false);
	    Double importeAux = 0.0;
		if (criterioPago==1){
			Set<Integer> idsCapVolumen = capVolumen.keySet();
			Iterator<Integer> it = idsCapVolumen.iterator();
			while(it.hasNext()){
				Integer idCapVolumen = it.next();
				String item = capVolumen.get(idCapVolumen);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					for (int i = 0; i < lstDetallePagosCAEspecialistaV.size(); i++) {
						if (lstDetallePagosCAEspecialistaV.get(i).getIdAsiganacionCA() == idCapVolumen){
							vEstadoAux = lstDetallePagosCAEspecialistaV.get(i).getIdEstado();
							vCultivoAux = lstDetallePagosCAEspecialistaV.get(i).getIdCultivo();
							vVariedadAux = lstDetallePagosCAEspecialistaV.get(i).getIdVariedad();
							vBodegaAux = lstDetallePagosCAEspecialistaV.get(i).getBodega();
							vCuotaAux = lstDetallePagosCAEspecialistaV.get(i).getCuota();
							break;
						}
					}
					PagosDetalle pd = new PagosDetalle();
					pd.setIdEstado(vEstadoAux);
					pd.setIdCultivo(vCultivoAux);
					pd.setIdVariedad(vVariedadAux);
					pd.setBodega(vBodegaAux);			
					pd.setVolumen(Double.parseDouble(item));
					importeAux = (Double.parseDouble(item)*vCuotaAux);
					importeAux = (Double.parseDouble(nf.format(importeAux)));
					System.out.println("Importe Actualizado "+ importeAux);
					pd.setImporte(importeAux);
					pd.setCuota(vCuotaAux);
					p.getPagosDetalle().add(pd);
					totalImporte +=importeAux;
					totalVolumen += Double.parseDouble(item);
				}			
			}			
			p.setVolumen(totalVolumen);
			p.setImporte(totalImporte);
			p.setUsuarioCreacion((Integer) session.get("idUsuario"));
		} else if (criterioPago==2){
			Set<Integer> idsCapImporte = capImporte.keySet();
			Iterator<Integer> it = idsCapImporte.iterator();
			while(it.hasNext()){
				Integer idCapImporte = it.next();
				String item = capImporte.get(idCapImporte);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					for (int i = 0; i < lstDetallePagosCAEspecialistaV.size(); i++) {
						if (lstDetallePagosCAEspecialistaV.get(i).getIdAsiganacionCA() == idCapImporte){
							vEstadoAux = lstDetallePagosCAEspecialistaV.get(i).getIdEstado();
							vCultivoAux = lstDetallePagosCAEspecialistaV.get(i).getIdCultivo();
							vVariedadAux = lstDetallePagosCAEspecialistaV.get(i).getIdVariedad();
							vBodegaAux = lstDetallePagosCAEspecialistaV.get(i).getBodega();
							vCuotaAux = 0.0;
							break;
						}
					}
					PagosDetalle pd = new PagosDetalle();
					pd.setIdEstado(vEstadoAux);
					pd.setIdCultivo(vCultivoAux);
					pd.setIdVariedad(vVariedadAux);
					pd.setBodega(vBodegaAux);
					pd.setVolumen(0.0);
					pd.setEtapa(capEtapa.get(idCapImporte));
					etapaPago = capEtapa.get(idCapImporte);
					pd.setImporte(Double.parseDouble(capImporte.get(idCapImporte)));
					pd.setCuota(vCuotaAux);
					p.getPagosDetalle().add(pd);
					totalImporte += Double.parseDouble(capImporte.get(idCapImporte));
					totalVolumen = 0.0;
				}
			}
			p.setVolumen(totalVolumen);
			p.setEtapa(etapaPago);
			p.setImporte(totalImporte);
			p.setUsuarioCreacion((Integer) session.get("idUsuario"));
		}		
		idPago = ((Pagos)cDAO.guardaObjeto(p)).getIdPago();
	}

	public String recuperaDatosPlaza(){
		try{
			plaza = new PlazasBancarias();
			lstPlazas = cDAO.consultaPlaza(clabe.substring(3,6));
			if(lstPlazas.size()>0){
				plaza =lstPlazas.get(0);
			}else{
				AppLogger.error("errores","No se encuentra los datos de la plaza asociado a la cuenta bancaria");
			}
			cuenta = clabe.substring(6,17);
			ctaBancaria = clabe.substring(6,17);
			/*Verifica que la sucursal se encuentre en la cuenta bancaria*/
			List<CuentaBancaria> cb = cDAO.consultaCtaBancaria(clabe);
			if(cb.size()>0){
				sucursal =cb.get(0).getSucursal();
			}
			
			bancos = cDAO.consultaBanco(Integer.parseInt(clabe.substring(1,3)));
			if(bancos.size()>0){
				nombreBanco = bancos.get(0).getNombre();	
			}else{
				nombreBanco = "NO DEFINIDO";
			}
		}catch (JDBCException e) {
			addActionError("Ocurrio un error inesperado al consultar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrió un error al consultar los datos de la plaza debido a:"+e.getCause());
			e.printStackTrace();
		}catch (Exception e) {
			addActionError("Ocurrio un error inesperado al consultar los datos de la plaza, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrió un error al consultar los datos de la plaza debido a:"+e.getCause());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@SuppressWarnings("unused")
	public String generarAtentaNotaPago() throws Exception{
		session = ActionContext.getContext().getSession();
		if(lstDetallePagosCAEspecialistaV != null){
			programa = cDAO.consultaPrograma(lstDetallePagosCAEspecialistaV.get(0).getIdPrograma()).get(0);
		} else {
			programa = cDAO.consultaPrograma(lstEditDetallePagosCAEspecialistaV.get(0).getIdPrograma()).get(0);
		}
		
		//criterioPago = programa.getCriterioPago();
		//recuperDatosOficio();
		try {
			/**Genera la Atenta Nota del Pago generado por el Especialista en formato PDF*/
			Document document;
			
			rutaSalida = cDAO.consultaParametros("RUTA_ATENTA_NOTA_PAGO");
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			nombreArchivo ="AtentaNotaPago-"+new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+".pdf";
			//nombreArchivo =new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"-AtentaNotaPago-"+atentaNotaNum+".pdf";
			rutaSalida +=nombreArchivo;
			rutaRaiz = context.getRealPath("/");
			rutaImagen = context.getRealPath("/images/logoSagarpa.png");
			rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
			nombreCoordinacion = cDAO.consultaParametros("NOMBRE_COORDINACION");
			List<Personal> lstPersonal1 = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false, false, false, true, false, null);
			if(lstPersonal1.size()>0){
				setVoBo(lstPersonal1.get(0));
			}
			AtentaNotaPagos pdf = new AtentaNotaPagos(this, sessionTarget);
			pdf.generarAtentaNotaPago(idPago);
			Pagos pagoAux = pDAO.consultaPagos(idPago).get(0);
			pagoAux.setArchivoAtentaNota(nombreArchivo);
			cDAO.guardaPagos(pagoAux);
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrió un error al generar la atenta nota debido a:"+e.getCause());
		}		
		return SUCCESS;
	}

	public String consigueAtentaNota() throws Exception{
		try{
			List<Pagos> pago = pDAO.consultaPagos(idPago);
			rutaSalida = cDAO.consultaParametros("RUTA_ATENTA_NOTA_PAGO");	
			nombreArchivo = pago.get(0).getArchivoAtentaNota(); 
			if (!rutaSalida.endsWith(File.separator)){
				rutaSalida += File.separator;
			}
			Utilerias.entregarArchivo(rutaSalida,nombreArchivo,"pdf");
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return SUCCESS;
	}

	public String consultaPagosCartaAdhesion(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}
			lstConsultaPagosCAEspecialista = pDAO.consultaPagosV(-1, -1, -1, null, null, null, -1, idEspecialista, folioCartaAdhesion);
			criterioPago = lstConsultaPagosCAEspecialista.get(0).getIdCriterioPago();
			descCriterioPago = lstConsultaPagosCAEspecialista.get(0).getCriterioPago();
			clabe = lstConsultaPagosCAEspecialista.get(0).getClabe();
			idComprador = lstConsultaPagosCAEspecialista.get(0).getIdComprador();
			idPrograma = lstConsultaPagosCAEspecialista.get(0).getIdPrograma();
			folioCartaAdhesion = lstConsultaPagosCAEspecialista.get(0).getNoCarta();
			idEspecialista = lstConsultaPagosCAEspecialista.get(0).getIdEspecialista();
			estatusCA = lstConsultaPagosCAEspecialista.get(0).getDescEstatus();
			setIdEstatusCA(lstConsultaPagosCAEspecialista.get(0).getIdEstatusCA());
			nombreComprador = lstConsultaPagosCAEspecialista.get(0).getNombreComprador();
			nombrePrograma = lstConsultaPagosCAEspecialista.get(0).getNombrePgrCorto();
			fianza = lstConsultaPagosCAEspecialista.get(0).getDescFianza();
			recuperaDatosPlaza();
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en consultaPagosCartaAdhesion debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en consultaPagosCartaAdhesion debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}	

	public String detalleEditarPagosCartaAdhesion(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}
			
			//Consigue pago
			Pagos p = pDAO.consultaPagos(idPago).get(0);
			tieneFianza = p.getFianza();
			porcentajeFianza = p.getPorcentajeFianza();
			
			lstEditDetallePagosCAEspecialistaV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, 0, 0, 0, 0);		
			numCampos = lstEditDetallePagosCAEspecialistaV.size();
			criterioPago = lstEditDetallePagosCAEspecialistaV.get(0).getIdCriterioPago();
			descCriterioPago = lstEditDetallePagosCAEspecialistaV.get(0).getCriterioPago();
			clabe = lstEditDetallePagosCAEspecialistaV.get(0).getClabe();
			idComprador = lstEditDetallePagosCAEspecialistaV.get(0).getIdComprador();
			idPrograma = lstEditDetallePagosCAEspecialistaV.get(0).getIdPrograma();
			folioCartaAdhesion = lstEditDetallePagosCAEspecialistaV.get(0).getFolioCartaAdhesion();
			idEspecialista = lstEditDetallePagosCAEspecialistaV.get(0).getIdEspecialista();
			estatusCA = lstEditDetallePagosCAEspecialistaV.get(0).getDescEstatus();
			nombreComprador = lstEditDetallePagosCAEspecialistaV.get(0).getNombreComprador();
			nombrePrograma = lstEditDetallePagosCAEspecialistaV.get(0).getPrograma();
			fianza = lstEditDetallePagosCAEspecialistaV.get(0).getDescFianza();
			idEstatusCA = lstEditDetallePagosCAEspecialistaV.get(0).getIdEstatusCA();
			recuperaDatosPlaza();
			fechaAtentaNota = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
			solicitudesAtendidas =  lstEditDetallePagosCAEspecialistaV.get(0).getSolicitudesAtendidas()!=null ? lstEditDetallePagosCAEspecialistaV.get(0).getSolicitudesAtendidas() : 0;
			productoresBeneficiados = lstEditDetallePagosCAEspecialistaV.get(0).getProductoresBeneficiados()!=null ? lstEditDetallePagosCAEspecialistaV.get(0).getProductoresBeneficiados() : 0;
			//Recupera volumen e importe para ajustar el primer pago de la carta de adhesion  
			if(criterioPago == 1){
				volumenPago = p.getVolumen();	
			}
			importePago = p.getImporte();
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en detalleEditarPagosCartaAdhesion  debido a: "+e.getCause());
	    } catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en detalleEditarPagosCartaAdhesion  debido a: "+e.getMessage());
		}
		return SUCCESS;		
	}	

	public String editarPagoCartaAdhesion() throws Exception{
		
		session = ActionContext.getContext().getSession();
		int idPerfil = (Integer) session.get("idPerfil");
		
		Double importeEtapaI=0.0, importeEtapaII=0.0, importeEtapaIII=0.0, importeEtapaIV=0.0;
		Double totalVolApoEdoCulVarCA = 0.0, volumenAutEdoCulVarCA=0.0, importeTotalPagado=0.0;
		int diasVigencia = 90;
		Date fechaVigenciaLimitePago; 
		StringBuilder dest = new StringBuilder();
		String vEstadoAux = "", vCultivoAux = "", vVariedadAux = "", vBodegaAux = "";
		List<PagosDetalleCAV> lstEditDetallePagosCAEspecialistaAuxV;
		
		
		try{			
			errorSistema = 0;
			/* Guarda los datos del pago*/
			session = ActionContext.getContext().getSession();
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}			
			if(idPerfil == 6){			
				// Valida vigencia del Anexo 32D y que no tenga observaciones 
				lstDocumentacionSPCartaAdhesion = spDAO.consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, 5, null, "folioCartaAdhesion");
				fechaVigenciaLimitePago = uDAO.getFechaDiaNaturalSumaDias(lstDocumentacionSPCartaAdhesion.get(0).getFechaExpedicionAnexo(), diasVigencia);
	
				String fechaMin = new SimpleDateFormat("yyyyMMdd").format(lstDocumentacionSPCartaAdhesion.get(0).getFechaExpedicionAnexo()).toString();
				String fechaMax = new SimpleDateFormat("yyyyMMdd").format(fechaVigenciaLimitePago).toString();	
				String fechaPago = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
				
				if(cDAO.consultaComprador(lstDocumentacionSPCartaAdhesion.get(0).getIdComprador()).get(0).getEstatus()==1){
					if((lstDocumentacionSPCartaAdhesion.get(0).getObservacion()==null || !lstDocumentacionSPCartaAdhesion.get(0).getObservacion()) && Long.parseLong(fechaPago) >= Long.parseLong(fechaMin) && Long.parseLong(fechaPago) <= Long.parseLong(fechaMax)){				
						//lstDetallePagosCAEspecialistaV = spDAO.verCartaAdhesionAsignadasPagos(idEspecialista, folioCartaAdhesion);
						lstEditDetallePagosCAEspecialistaV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, 0, 0, 0, 0);
						fianza = lstEditDetallePagosCAEspecialistaV.get(0).getDescFianza();
						if(criterioPago==1){
							Set<Integer> idsCapVolumen = capVolumen.keySet();
							Iterator<Integer> it = idsCapVolumen.iterator();
							while(it.hasNext()){
								Integer idCapVolumen = it.next();
								String item = capVolumen.get(idCapVolumen);
								if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
									for (int i = 0; i < lstEditDetallePagosCAEspecialistaV.size(); i++) {
										if (lstEditDetallePagosCAEspecialistaV.get(i).getIdPagoDetalle() == idCapVolumen){
											 
											if(lstEditDetallePagosCAEspecialistaV.get(i).getBodega()==null||lstEditDetallePagosCAEspecialistaV.get(i).getBodega().isEmpty()){
												
												List<PagosCartasAdhesionV> pca = pDAO.consultaPagosEdoCulVarCA(folioCartaAdhesion, lstEditDetallePagosCAEspecialistaV.get(i).getIdEstado(), lstEditDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstEditDetallePagosCAEspecialistaV.get(i).getIdVariedad(), null);
												System.out.println("carta"+pca.get(0).getFolioCartaAdhesion());
												//lstEditDetallePagosCAEspecialistaV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, lstDetallePagosCAEspecialistaV.get(i).getIdEstado(), lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad(), 0);
												if (pca.size()>0){
													totalVolApoEdoCulVarCA = pca.get(0).getVolumen()-lstEditDetallePagosCAEspecialistaV.get(i).getVolumen();
												} else {
													totalVolApoEdoCulVarCA = 0.0;
												}											
											} else {
												
												List<PagosCartasAdhesionV> pca = pDAO.consultaPagosEdoCulVarCA(folioCartaAdhesion, lstEditDetallePagosCAEspecialistaV.get(i).getIdEstado(), lstEditDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstEditDetallePagosCAEspecialistaV.get(i).getIdVariedad(), lstEditDetallePagosCAEspecialistaV.get(i).getBodega());
												//lstEditDetallePagosCAEspecialistaV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, lstDetallePagosCAEspecialistaV.get(i).getIdEstado(), lstDetallePagosCAEspecialistaV.get(i).getIdCultivo(), lstDetallePagosCAEspecialistaV.get(i).getIdVariedad(), 0);
												if (pca.size()>0){
													totalVolApoEdoCulVarCA = pca.get(0).getVolumen()-lstEditDetallePagosCAEspecialistaV.get(i).getVolumen();
												} else {
													totalVolApoEdoCulVarCA = 0.0;
												}											
											}
											
											volumenAutEdoCulVarCA = lstEditDetallePagosCAEspecialistaV.get(i).getVolumenAutorizado();
											vEstadoAux = lstEditDetallePagosCAEspecialistaV.get(i).getEstado();
											vCultivoAux = lstEditDetallePagosCAEspecialistaV.get(i).getCultivo();
											vVariedadAux = lstEditDetallePagosCAEspecialistaV.get(i).getVariedad();
											vBodegaAux = lstEditDetallePagosCAEspecialistaV.get(i).getBodega();
											break;
										}
									}
									Double suma = Double.parseDouble(item)+totalVolApoEdoCulVarCA;
									//System.out.println("suma antes "+suma);
									suma =Double.parseDouble(TextUtil.formateaNumeroComoVolumenSinComas(suma));
									//System.out.println(suma);
									if (suma >volumenAutEdoCulVarCA){
										errorSistema = 1;
										if(vBodegaAux==null||vBodegaAux.isEmpty()){
											addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
													   " no puede ser mayor al volumen autorizado: "+volumenAutEdoCulVarCA+
													   " de la carta de adhesión para el estado: "+vEstadoAux+
													   " cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+", por favor verifique");
										} else {
											addActionError("El volumen a apoyar: "+item+" más el volumen apoyado: "+totalVolApoEdoCulVarCA+
													   " no puede ser mayor al volumen autorizado: "+volumenAutEdoCulVarCA+
													   " de la carta de adhesión para el estado: "+vEstadoAux+
													   " cultivo: "+vCultivoAux+" variedad: "+vVariedadAux+" bodega: "+vBodegaAux+", por favor verifique");										
										}
										return SUCCESS;
									}
									
								}
								totalVolApoEdoCulVarCA = 0.0;
							}//		while(it.hasNext()					
						} else if (criterioPago==2){
							Set<Integer> idsCapImporte = capImporte.keySet();
							Iterator<Integer> it = idsCapImporte.iterator();
							while(it.hasNext()){
								Integer idCapImporte = it.next();
								String item = capEtapa.get(idCapImporte);
								if(item!=null && !item.trim().isEmpty()){
									if(item=="I"){
										importeEtapaI += Double.parseDouble(capImporte.get(idCapImporte));
									} else if(item=="II"){
										importeEtapaII += Double.parseDouble(capImporte.get(idCapImporte));
									} else if(item=="III"){
										importeEtapaIII += Double.parseDouble(capImporte.get(idCapImporte));
									} else if(item=="IV"){
										importeEtapaIV += Double.parseDouble(capImporte.get(idCapImporte));
									}									
								}
							}
							if(importeEtapaI>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 1);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 1);
								lstEditDetallePagosCAEspecialistaAuxV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, 0, 0, 0, 1);
								if ((importeEtapaI+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte()))>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaI+" más el importe apoyado: "+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte())+" en la etapa I de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							} 
							if(importeEtapaII>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 2);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 2);
								lstEditDetallePagosCAEspecialistaAuxV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, 0, 0, 0, 2);
								if ((importeEtapaII+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte()))>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaII+" más el importe apoyado: "+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte())+" en la etapa II de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							} 
							if(importeEtapaIII>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 3);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 3);
								lstEditDetallePagosCAEspecialistaAuxV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, 0, 0, 0, 3);
								if ((importeEtapaIII+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte()))>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaIII+" más el importe apoyado: "+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte())+" en la etapa III de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							} 
							if(importeEtapaIV>0){
								lstEtapaIniEsquema = spDAO.consultaEtapasIniEsquema(lstDocumentacionSPCartaAdhesion.get(0).getIdPrograma(), 4);
								lstCartaAdhesionEtapaVolImpV = pDAO.consultaVolImpCartaAdhesionEtapa(folioCartaAdhesion, 4);
								lstEditDetallePagosCAEspecialistaAuxV = pDAO.consultaPagosDetalleCAV(folioCartaAdhesion, idPago, 0, 0, 0, 4);
								if ((importeEtapaIV+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte()))>lstEtapaIniEsquema.get(0).getMonto()){
									errorSistema = 1;
									addActionError("El importe a apoyar: "+importeEtapaIV+" más el importe apoyado: "+(lstCartaAdhesionEtapaVolImpV.get(0).getImporte()-lstEditDetallePagosCAEspecialistaAuxV.get(0).getImporte())+" en la etapa IV de la carta de adhesión no puede ser mayor al importe autorizado: "+lstEtapaIniEsquema.get(0).getMonto()+" para la etapa y esquema de apoyos, por favor verifique");
									return SUCCESS;
								}
							}
						} else if (criterioPago==3){
							
						}
		
		                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		                Date fechaActualConvert = dateFormat.parse(fechaAtentaNota); 
		                           
		                fechaActual = " a "+new SimpleDateFormat("dd").format(fechaActualConvert).toString()+" de "
		                                   + TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(fechaActualConvert).toString()))+" de "
		                                   + new SimpleDateFormat("yyyy").format(fechaActualConvert).toString();
						//Recupera el destinatario
						lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false,false);
						if(lstPersonal.size()>0){
							destinatario = lstPersonal.get(0);
							dest.append(lstPersonal.get(0).getNombre()).append(" ")
										.append(lstPersonal.get(0).getPaterno())
										.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
							destinatario.setNombre(dest.toString().toUpperCase());
							destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
						}
						
						//Recupera el emisor
						lstEspecialista = cDAO.consultaEspecialista(idEspecialista);
						if(lstEspecialista.size()>0){
							emisor = lstEspecialista.get(0);
							emisor.setNombre(emisor.getNombre().toUpperCase());
							emisor.setPuesto(emisor.getPuesto().toUpperCase());
						}
						//Recupera la leyenda del oficio
						leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
						
						actualizaPagos(idPago);
						generarAtentaNotaPago();
						PagosV pv = new PagosV();
						pv = pDAO.consultaPagosV(idPago).get(0);
								
						log = "Se le informa que se modificó un pago del Programa: "+pv.getNombrePgrCorto().toUpperCase()+" - Comprador: "+pv.getNombreComprador().toUpperCase()
							   + " - Carta: "+pv.getNoCarta()+ " - clabe:"+ pv.getClabe()
							   +" - importe: $"+ TextUtil.formateaNumeroComoCantidad(pv.getImporte())+ (pv.getVolumen()!=null ? " - volumen:"+ TextUtil.formateaNumeroComoVolumen(pv.getVolumen()):"")+
							   (pv.getEtapa()!=null ? " - Etapa: "+pv.getEtapa():"" );
						cDAO.guardaObjeto(new Bitacora((Integer) session.get("idUsuario"), 3, new Date(),log));
						/*Enviar aviso de la captura de pago*/
						EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
						mensajes.enviarMensaje((Integer) session.get("idUsuario"), 3, log, "Aviso");
						band = true;
						detalleEditarPagosCartaAdhesion();
						msjOk = "Se actualizó correctamente el pago";
					} else {
						errorSistema = 1;
						addActionError("La vigencia del documento Anexo 32D ("+fechaVigenciaLimitePago.toString()+") no es válida para la actualización del pago ("+new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString()+") o el anexo 32D esta en estatus de observación, por favor verifique");
						return SUCCESS;
					}
				} else {
					errorSistema = 1;
					addActionError("El comprador esta en estatus Inhabilitado por lo que no procede la actualización del pago, por favor verifique");
					return SUCCESS;
				}
			}else if(idPerfil == 10){//perfil 10 ajuste en el primer pago
				//Consigue el pago>
				Pagos p = pDAO.consultaPagos(idPago).get(0);
				if(criterioPago==1){
					//Recupera el volumen e importe del pago 
					volumenPago = p.getVolumen();		
					Set<Integer> idsCapVolumen = capVolumen.keySet();
					Iterator<Integer> it = idsCapVolumen.iterator();
					while(it.hasNext()){
						Integer idCapVolumen = it.next();
						String item = capVolumen.get(idCapVolumen);
						if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
							//Sumariza los totales 
							totalVolApoEdoCulVarCA += Double.parseDouble(item); 
						}//item diferente de null, de vacio y mayor a cero
					}//end mientras it.hasNext()	
					
					System.out.println("totalVolApoEdoCulVarCA "+totalVolApoEdoCulVarCA);
					totalVolApoEdoCulVarCA = Double.parseDouble(TextUtil.formateaNumeroComoVolumenSinComas(totalVolApoEdoCulVarCA));
					if(totalVolApoEdoCulVarCA.doubleValue() != volumenPago.doubleValue()){
					//if(!TextUtil.formateaNumeroComoVolumenSinComas(totalVolApoEdoCulVarCA).equals(volumenPago)){
						errorSistema = 1; 
						addActionError("El total volumen a apoyar: "+totalVolApoEdoCulVarCA+
								   " no puede ser mayor al volumen pagado: "+volumenPago+
								   " de la carta de adhesión, por favor verifique");
					}else{
						actualizaPrimerPago();
						detalleEditarPagosCartaAdhesion();
					}						
				}else if(criterioPago == 2){
					importePago = p.getImporte();
					Set<Integer> idsCapImporte = capImporte.keySet();
					Iterator<Integer> it = idsCapImporte.iterator();
					while(it.hasNext()){
						Integer idCapImporte = it.next();
						String item = capImporte.get(idCapImporte);
						if(item!=null && !item.trim().isEmpty()){
							importeTotalPagado += Double.parseDouble(item); 
						}
					}//end while
					
					if(!importeTotalPagado.equals(importePago)){						
						errorSistema = 1; 
						addActionError("El total importe a apoyar: "+importeTotalPagado+
								   " no puede ser mayor al importe pagado: "+importePago+
								   " de la carta de adhesión, por favor verifique");
					}else{
						actualizaPrimerPago();
						detalleEditarPagosCartaAdhesion();
					}	
				}
				
			}//End id perfil == 10
		
		}catch (JDBCException e) {
	    	errorSistema = 1;
			addActionError("Ocurrio un error al guardar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrio un error al guardar el pago en base de datos debido a: "+e.getCause());
			e.printStackTrace();
		}catch(Exception e){
	    	errorSistema = 1;
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");		
			AppLogger.error("errores","Ocurrio un error inesperado al guardar el pago debido a: "+e.getCause());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	private void actualizaPrimerPago()throws JDBCException, Exception{
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2); 
	    nf.setGroupingUsed(false);
	    Double importeAux = 0.0;
		if (criterioPago==1){
			Set<Integer> idsCapVolumen = capVolumen.keySet();
			Iterator<Integer> it = idsCapVolumen.iterator();
			while(it.hasNext()){
				Integer idCapVolumen = it.next();
				String item = capVolumen.get(idCapVolumen);
				PagosDetalle pd = pDAO.consultaPagosDetalle(idCapVolumen, idPago).get(0);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					pd.setVolumen(Double.parseDouble(item));					
					importeAux = (Double.parseDouble(nf.format(Double.parseDouble(item)*pd.getCuota())));
					pd.setImporte(importeAux);
				}else{
					pd.setVolumen(0.0);
					pd.setImporte(0.0);					
				}
				cDAO.guardaObjeto(pd);
			}
		}else if(criterioPago == 2){
			Set<Integer> idsCapImporte = capImporte.keySet();
			Iterator<Integer> it = idsCapImporte.iterator();
			
			while(it.hasNext()){
				Integer idCapImporte = it.next();
				String item = capImporte.get(idCapImporte);
				PagosDetalle pd = pDAO.consultaPagosDetalle(idCapImporte, idPago).get(0);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					pd.setImporte(Double.parseDouble(item));
				}else{
					pd.setVolumen(0.0);
					pd.setImporte(0.0);		
				}
				cDAO.guardaObjeto(pd);
			}
		}
		
	}
	
	private void actualizaPagos(Long idPago) throws JDBCException, Exception{
		double totalImporte =0;
		double totalVolumen =0;
		String etapaPago="", vBodegaAux = "";
		Double vCuotaAux = 0.0;
		Integer vEstadoAux = 0, vCultivoAux = 0, vVariedadAux = 0;

		p = pDAO.getPagos(idPago);
		p.setClabe(lstEditDetallePagosCAEspecialistaV.get(0).getClabe());
		p.setEstatus(7);
		p.setFechaModificacion(new Date());
		p.setIdComprador(lstEditDetallePagosCAEspecialistaV.get(0).getIdComprador());
		p.setIdPrograma(lstEditDetallePagosCAEspecialistaV.get(0).getIdPrograma());
		p.setNoCarta(folioCartaAdhesion);
		p.setAtentaNota(new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActualConvert = dateFormat.parse(fechaAtentaNota); 
		p.setFechaAtentaNota(fechaActualConvert);
		p.setFianza(tieneFianza);
		if(tieneFianza){
			p.setPorcentajeFianza(porcentajeFianza);
		}
		p.setSolicitudesAtendidas(solicitudesAtendidas);
		p.setProductoresBeneficiados(productoresBeneficiados);
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2); 
	    nf.setGroupingUsed(false);
	    Double importeAux = 0.0;
		if (criterioPago==1){
			//List<PagosDetalle> pd = pDAO.consultaPagosDetalle(0, idPago);
			Set<Integer> idsCapVolumen = capVolumen.keySet();
			Iterator<Integer> it = idsCapVolumen.iterator();
			while(it.hasNext()){
				Integer idCapVolumen = it.next();
				String item = capVolumen.get(idCapVolumen);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					for (int i = 0; i < lstEditDetallePagosCAEspecialistaV.size(); i++) {
						if (lstEditDetallePagosCAEspecialistaV.get(i).getIdPagoDetalle() == idCapVolumen){
							vEstadoAux = lstEditDetallePagosCAEspecialistaV.get(i).getIdEstado();
							vCultivoAux = lstEditDetallePagosCAEspecialistaV.get(i).getIdCultivo();
							vVariedadAux = lstEditDetallePagosCAEspecialistaV.get(i).getIdVariedad();
							vBodegaAux = lstEditDetallePagosCAEspecialistaV.get(i).getBodega();
							vCuotaAux = lstEditDetallePagosCAEspecialistaV.get(i).getCuota();
							break;
						}
					}
					PagosDetalle pd = pDAO.consultaPagosDetalle(idCapVolumen, idPago).get(0);
					pd.setIdEstado(vEstadoAux);
					pd.setIdCultivo(vCultivoAux);
					pd.setIdVariedad(vVariedadAux);
					pd.setBodega(vBodegaAux);
					pd.setVolumen(Double.parseDouble(item));
					importeAux = (Double.parseDouble(item)*vCuotaAux);
					importeAux = (Double.parseDouble(nf.format(importeAux)));
					System.out.println("importeAux resgistrado"+importeAux);
					pd.setImporte(importeAux);
					pd.setCuota(vCuotaAux);
					cDAO.guardaObjeto(pd);
					totalImporte += importeAux;
					totalVolumen += Double.parseDouble(item);
				}				
			}						
			p.setVolumen(totalVolumen);
			p.setImporte(totalImporte);
			p.setUsuarioModificacion((Integer) session.get("idUsuario"));
		} else if (criterioPago==2){
			//List<PagosDetalle> pd = pDAO.consultaPagosDetalle(0, idPago);
			Set<Integer> idsCapImporte = capImporte.keySet();
			Iterator<Integer> it = idsCapImporte.iterator();
			while(it.hasNext()){
				Integer idCapImporte = it.next();
				String item = capImporte.get(idCapImporte);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					for (int i = 0; i < lstEditDetallePagosCAEspecialistaV.size(); i++) {
						if (lstEditDetallePagosCAEspecialistaV.get(i).getIdPagoDetalle() == idCapImporte){
							vEstadoAux = lstEditDetallePagosCAEspecialistaV.get(i).getIdEstado();
							vCultivoAux = lstEditDetallePagosCAEspecialistaV.get(i).getIdCultivo();
							vVariedadAux = lstEditDetallePagosCAEspecialistaV.get(i).getIdVariedad();
							vBodegaAux = lstEditDetallePagosCAEspecialistaV.get(i).getBodega();
							vCuotaAux = 0.0;
							break;
						}
					}
					PagosDetalle pd = pDAO.consultaPagosDetalle(idCapImporte, idPago).get(0);
					pd.setIdEstado(vEstadoAux);
					pd.setIdCultivo(vCultivoAux);
					pd.setIdVariedad(vVariedadAux);
					pd.setBodega(vBodegaAux);
					pd.setVolumen(0.0);
					pd.setEtapa(capEtapa.get(idCapImporte));
					etapaPago = capEtapa.get(idCapImporte);
					pd.setImporte(Double.parseDouble(item));
					pd.setCuota(vCuotaAux);					
					cDAO.guardaObjeto(pd);
					totalImporte += Double.parseDouble(item);
					totalVolumen = 0.0;
				}
			}
			p.setVolumen(totalVolumen);
			p.setEtapa(etapaPago);
			p.setImporte(totalImporte);
			p.setUsuarioModificacion((Integer) session.get("idUsuario"));
		}
		cDAO.guardaObjeto(p);
	}

	public String  vistaPreviaAtentaNota() throws Exception {
		AsignacionCartasAdhesionEspecialistaV pagoVistaPrevia, pagoVistaPreviaAux;
		boolean elementoRepetido = false;
		StringBuilder dest = new StringBuilder();
		session = ActionContext.getContext().getSession();
		int idPerfil = (Integer) session.get("idPerfil");
		if(idPerfil == 6){
			idEspecialista = (Integer) session.get("idEspecialista");
		}
		lstEspecialistaCA = spDAO.consultaCAaEspecialistaV(folioCartaAdhesion).get(0);
		if (lstEspecialistaCA.getCriterioPago()==1){
			leyendaCriterio = "volumen";
			cantidadCriterio = lstEspecialistaCA.getVolumenAutorizado(); 
		} else if (lstEspecialistaCA.getCriterioPago()==2){
			leyendaCriterio = "importe";
			cantidadCriterio = lstEspecialistaCA.getImporteAutorizado();
		} else if (lstEspecialistaCA.getCriterioPago()==3){
			leyendaCriterio = "volumen";
			cantidadCriterio = lstEspecialistaCA.getVolumenAutorizado();
		}
		lstPagosVistaPrevia = new ArrayList<AsignacionCartasAdhesionEspecialistaV>();
		if (lstEspecialistaCA.getCriterioPago()==1){
			Set<Integer> idsCapVolumen = capVolumen.keySet();
			Iterator<Integer> it = idsCapVolumen.iterator();
			while(it.hasNext()){
				Integer idCapVolumen = it.next();
				String item = capVolumen.get(idCapVolumen);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					pagoVistaPrevia = new AsignacionCartasAdhesionEspecialistaV();
					pagoVistaPreviaAux = new AsignacionCartasAdhesionEspecialistaV();
					pagoVistaPrevia = spDAO.verCartaAdhesionAsignadasPagos(idCapVolumen);
					//pagoVistaPreviaAux.setIdAsiganacionCA(pagoVistaPrevia.getIdAsiganacionCA());
					//pagoVistaPreviaAux.setIdEspecialista(pagoVistaPrevia.getIdEspecialista());
					//pagoVistaPreviaAux.setIdComprador(pagoVistaPrevia.getIdComprador());
					//pagoVistaPreviaAux.setFolioCartaAdhesion(pagoVistaPrevia.getFolioCartaAdhesion());
					//pagoVistaPreviaAux.setIdPrograma(pagoVistaPrevia.getIdPrograma());
					pagoVistaPreviaAux.setIdEstado(pagoVistaPrevia.getIdEstado());
					pagoVistaPreviaAux.setEstado(pagoVistaPrevia.getEstado());
					pagoVistaPreviaAux.setIdCultivo(pagoVistaPrevia.getIdCultivo());
					pagoVistaPreviaAux.setCultivo(pagoVistaPrevia.getCultivo());
					pagoVistaPreviaAux.setIdVariedad(pagoVistaPrevia.getIdVariedad());
					pagoVistaPreviaAux.setVariedad(pagoVistaPrevia.getVariedad());
					pagoVistaPreviaAux.setBodega(pagoVistaPrevia.getBodega());
					pagoVistaPreviaAux.setCuota(pagoVistaPrevia.getCuota());
					pagoVistaPreviaAux.setVolumen(Double.parseDouble(item));
					pagoVistaPreviaAux.setImporte((Double.parseDouble(item)*pagoVistaPrevia.getCuota()));
					//pagoVistaPreviaAux.setEstatus(pagoVistaPrevia.getEstatus());
					//pagoVistaPreviaAux.setFianza(pagoVistaPrevia.getFianza());
					//pagoVistaPreviaAux.setClabe(pagoVistaPrevia.getClabe());
					//pagoVistaPreviaAux.setVolumenApoyado(pagoVistaPrevia.getVolumenApoyado());
					//pagoVistaPreviaAux.setImporteApoyado(pagoVistaPrevia.getImporteApoyado());
					//pagoVistaPreviaAux.setEtapa(pagoVistaPrevia.getEtapa());
					pagoVistaPreviaAux.setIdCriterioPago(pagoVistaPrevia.getIdCriterioPago());
					for(AsignacionCartasAdhesionEspecialistaV l:lstPagosVistaPrevia){
						if ((l.getIdCultivo().intValue() == pagoVistaPrevia.getIdCultivo().intValue())
								&& (l.getIdVariedad().intValue() == pagoVistaPrevia.getIdVariedad().intValue())
								&& (l.getIdEstado().intValue() == pagoVistaPrevia.getIdEstado().intValue())) {
							l.setVolumen(l.getVolumen()+pagoVistaPreviaAux.getVolumen());
							l.setImporte(l.getImporte()+pagoVistaPreviaAux.getImporte());
							elementoRepetido = true;
							break;
						}
					}
					
					if(!elementoRepetido){
						lstPagosVistaPrevia.add(pagoVistaPreviaAux);
					}
					elementoRepetido = false;
				}
			}
		} else if (lstEspecialistaCA.getCriterioPago()==2){
			Set<Integer> idsCapImporte = capImporte.keySet();
			Iterator<Integer> it = idsCapImporte.iterator();
			while(it.hasNext()){
				Integer idCapImporte = it.next();
				String item = capImporte.get(idCapImporte);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					pagoVistaPrevia = new AsignacionCartasAdhesionEspecialistaV();
					pagoVistaPreviaAux = new AsignacionCartasAdhesionEspecialistaV();
					pagoVistaPrevia = spDAO.verCartaAdhesionAsignadasPagos(idCapImporte);
					pagoVistaPreviaAux.setIdAsiganacionCA(pagoVistaPrevia.getIdAsiganacionCA());
					pagoVistaPreviaAux.setIdEspecialista(pagoVistaPrevia.getIdEspecialista());
					pagoVistaPreviaAux.setIdComprador(pagoVistaPrevia.getIdComprador());
					pagoVistaPreviaAux.setFolioCartaAdhesion(pagoVistaPrevia.getFolioCartaAdhesion());
					pagoVistaPreviaAux.setIdPrograma(pagoVistaPrevia.getIdPrograma());
					pagoVistaPreviaAux.setIdEstado(pagoVistaPrevia.getIdEstado());
					pagoVistaPreviaAux.setEstado(pagoVistaPrevia.getEstado());
					pagoVistaPreviaAux.setIdCultivo(pagoVistaPrevia.getIdCultivo());
					pagoVistaPreviaAux.setCultivo(pagoVistaPrevia.getCultivo());
					pagoVistaPreviaAux.setIdVariedad(pagoVistaPrevia.getIdVariedad());
					pagoVistaPreviaAux.setVariedad(pagoVistaPrevia.getVariedad());
					pagoVistaPreviaAux.setBodega(pagoVistaPrevia.getBodega());
					pagoVistaPreviaAux.setCuota(pagoVistaPrevia.getCuota());
					pagoVistaPreviaAux.setVolumen(pagoVistaPrevia.getVolumen());
					pagoVistaPreviaAux.setImporte(Double.parseDouble(item));
					pagoVistaPreviaAux.setEstatus(pagoVistaPrevia.getEstatus());
					pagoVistaPreviaAux.setFianza(pagoVistaPrevia.getFianza());
					pagoVistaPreviaAux.setClabe(pagoVistaPrevia.getClabe());
					pagoVistaPreviaAux.setVolumenApoyado(pagoVistaPrevia.getVolumenApoyado());
					pagoVistaPreviaAux.setImporteApoyado(pagoVistaPrevia.getImporteApoyado());
					pagoVistaPreviaAux.setEtapa(capEtapa.get(idCapImporte));
					pagoVistaPreviaAux.setIdCriterioPago(pagoVistaPrevia.getIdCriterioPago());
					
					for(AsignacionCartasAdhesionEspecialistaV l:lstPagosVistaPrevia){
						if ((l.getIdCultivo().intValue() == pagoVistaPrevia.getIdCultivo().intValue())
								&& (l.getIdVariedad().intValue() == pagoVistaPrevia.getIdVariedad().intValue())
								&& (l.getIdEstado().intValue() == pagoVistaPrevia.getIdEstado().intValue())) {
							l.setVolumen(l.getVolumen()+pagoVistaPreviaAux.getVolumen());
							l.setImporte(l.getImporte()+pagoVistaPreviaAux.getImporte());
							elementoRepetido = true;
							break;
						}
					}
					
					if(!elementoRepetido){
						lstPagosVistaPrevia.add(pagoVistaPreviaAux);
					}
					elementoRepetido = false;
										
				}
			}
		} else if (lstEspecialistaCA.getCriterioPago()==3){
			Set<Integer> idsCapVolumen = capVolumen.keySet();
			Iterator<Integer> it = idsCapVolumen.iterator();
			while(it.hasNext()){
				Integer idCapVolumen = it.next();
				String item = capVolumen.get(idCapVolumen);
				if(item!=null && !item.trim().isEmpty() && Double.parseDouble(item)>0){
					pagoVistaPrevia = new AsignacionCartasAdhesionEspecialistaV();
					pagoVistaPreviaAux = new AsignacionCartasAdhesionEspecialistaV();
					pagoVistaPrevia = spDAO.verCartaAdhesionAsignadasPagos(idCapVolumen);				
					pagoVistaPreviaAux.setIdAsiganacionCA(pagoVistaPrevia.getIdAsiganacionCA());
					pagoVistaPreviaAux.setIdEspecialista(pagoVistaPrevia.getIdEspecialista());
					pagoVistaPreviaAux.setIdComprador(pagoVistaPrevia.getIdComprador());
					pagoVistaPreviaAux.setFolioCartaAdhesion(pagoVistaPrevia.getFolioCartaAdhesion());
					pagoVistaPreviaAux.setIdPrograma(pagoVistaPrevia.getIdPrograma());
					pagoVistaPreviaAux.setIdEstado(pagoVistaPrevia.getIdEstado());
					pagoVistaPreviaAux.setEstado(pagoVistaPrevia.getEstado());
					pagoVistaPreviaAux.setIdCultivo(pagoVistaPrevia.getIdCultivo());
					pagoVistaPreviaAux.setCultivo(pagoVistaPrevia.getCultivo());
					pagoVistaPreviaAux.setIdVariedad(pagoVistaPrevia.getIdVariedad());
					pagoVistaPreviaAux.setVariedad(pagoVistaPrevia.getVariedad());
					pagoVistaPreviaAux.setBodega(pagoVistaPrevia.getBodega());
					pagoVistaPreviaAux.setCuota(pagoVistaPrevia.getCuota());
					pagoVistaPreviaAux.setVolumen(Double.parseDouble(item));
					pagoVistaPreviaAux.setImporte((Double.parseDouble(item)*pagoVistaPrevia.getCuota()));
					pagoVistaPreviaAux.setEstatus(pagoVistaPrevia.getEstatus());
					pagoVistaPreviaAux.setFianza(pagoVistaPrevia.getFianza());
					pagoVistaPreviaAux.setClabe(pagoVistaPrevia.getClabe());
					pagoVistaPreviaAux.setVolumenApoyado(pagoVistaPrevia.getVolumenApoyado());
					pagoVistaPreviaAux.setImporteApoyado(pagoVistaPrevia.getImporteApoyado());
					pagoVistaPreviaAux.setEtapa(capEtapa.get(idCapVolumen));
					pagoVistaPreviaAux.setIdCriterioPago(pagoVistaPrevia.getIdCriterioPago());
					for(AsignacionCartasAdhesionEspecialistaV l:lstPagosVistaPrevia){
						if((l.getIdCultivo().intValue() == pagoVistaPrevia.getIdCultivo().intValue())
								&& (l.getIdVariedad().intValue() == pagoVistaPrevia.getIdVariedad().intValue())
								&& (l.getIdEstado().intValue() == pagoVistaPrevia.getIdEstado().intValue())) {
							l.setVolumen(l.getVolumen()+pagoVistaPreviaAux.getVolumen());
							l.setImporte(l.getImporte()+pagoVistaPreviaAux.getImporte());
							elementoRepetido = true;
							break;
						}
					}
					
					if(!elementoRepetido){
						lstPagosVistaPrevia.add(pagoVistaPreviaAux);
					}
					elementoRepetido = false;
				}
			}		
		}
		
		cultivo = lstPagosVistaPrevia.get(0).getCultivo();
		numeroPago = lstEspecialistaCA.getNumPago();
		if (tieneFianza){
			setTextoFianza(" (Fianza)");
		}

		clabe = lstEspecialistaCA.getClabe();
		recuperaDatosPlaza();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActualConvert = dateFormat.parse(fechaAtentaNota);                    
        fechaActual = " a "+new SimpleDateFormat("dd").format(fechaActualConvert).toString()+" de "
                           + TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(fechaActualConvert).toString()))+" de "
                           + new SimpleDateFormat("yyyy").format(fechaActualConvert).toString();
		//Recupera el destinatario
		lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false,false);
		if(lstPersonal.size()>0){
			destinatario = lstPersonal.get(0);
			dest.append(lstPersonal.get(0).getNombre()).append(" ")
						.append(lstPersonal.get(0).getPaterno())
						.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
			destinatario.setNombre(dest.toString().toUpperCase());
			destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
		}
		
		//Recupera el emisor
		lstEspecialista = cDAO.consultaEspecialista(idEspecialista);
		if(lstEspecialista.size()>0){
			emisor = lstEspecialista.get(0);
			emisor.setNombre(emisor.getNombre().toUpperCase());
			emisor.setPuesto(emisor.getPuesto().toUpperCase());
		}
		//Recupera la leyenda del oficio
		leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
		nombrePrograma = cDAO.consultaPrograma(lstEspecialistaCA.getIdPrograma()).get(0).getDescripcionLarga();
		leyendaEsquema = "* Cuota: corresponde al monto del apoyo por tonelada establecido en "+iDAO.consultaInicializacionPrograma(lstEspecialistaCA.getIdPrograma()).get(0).getLeyendaAtentaNota();
		return SUCCESS;
	}
	
	public String  incioGenAN() throws Exception {
		
		return SUCCESS;
	}
	
	public String generaANMasiva() throws Exception {
		List<PagosV> listPagosV = spDAO.consultaPagoAtentaNota();
		//Recupera la leyenda del oficio
		StringBuilder dest = new StringBuilder();
		leyendaOficio = cDAO.consultaParametros("LEYENDA_OFICIO");
		lstPersonal = cDAO.consultaPersonal(0, "Director de Pagos de Apoyos a la Comercialización", false, false,false);
		if(lstPersonal.size()>0){
			destinatario = lstPersonal.get(0);
			dest.append(lstPersonal.get(0).getNombre()).append(" ")
						.append(lstPersonal.get(0).getPaterno())
						.append(lstPersonal.get(0).getMaterno()!=null && !lstPersonal.get(0).getMaterno().isEmpty()? " "+lstPersonal.get(0).getMaterno():"");
			destinatario.setNombre(dest.toString().toUpperCase());
			destinatario.setPuesto(destinatario.getPuesto().toUpperCase());
		}
		
		for(PagosV p: listPagosV){
			try {
				//Consigue el programa al que pertenece el pago
				programa = cDAO.consultaPrograma(p.getIdPrograma()).get(0);
				criterioPago = programa.getCriterioPago();
				//Verifica la fianza de acuerdo a la carta adhesion
				List<CartaAdhesion> ca = spDAO.consultaCartaAdhesion(p.getNoCarta());
				System.out.println("ca.");
				if(ca.get(0).getFianza()!=null){
					boolean fianzaB = ca.get(0).getFianza();
					if(fianzaB == true){
						fianza = "SI";
					}else{
						fianza = "NO";
					}
				}				
				//Recupera el emisor
				lstEspecialista = cDAO.consultaEspecialista(p.getIdEspecialista());								
				if(lstEspecialista.size()>0){
					emisor = lstEspecialista.get(0);
					emisor.setNombre(emisor.getNombre().toUpperCase());
					emisor.setPuesto(emisor.getPuesto().toUpperCase());
				}
				
				//fianza = (ca.get(0).getFianza()?"SI":"NO");
				/**Genera la Atenta Nota del Pago generado por el Especialista en formato PDF*/
							
				rutaSalida = cDAO.consultaParametros("RUTA_ATENTA_NOTA_PAGO");
				if (!rutaSalida.endsWith(File.separator)){
					rutaSalida += File.separator;
				}
				nombreArchivo ="AtentaNotaPago-"+p.getIdPago()+".pdf";
				//nombreArchivo =new java.text.SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date() )+"-AtentaNotaPago-"+atentaNotaNum+".pdf";
				rutaSalida +=nombreArchivo;
				rutaRaiz = context.getRealPath("/");
				rutaImagen = context.getRealPath("/images/logoSagarpa.png");
				rutaMarcaAgua = context.getRealPath("/images/sagarpaMarcaAgua.PNG");
				nombreCoordinacion = cDAO.consultaParametros("NOMBRE_COORDINACION");
				AtentaNotaPagosC pdf = new AtentaNotaPagosC(this, sessionTarget);
				pdf.generarAtentaNotaPago(p.getIdPago());
				Pagos pagoAux = pDAO.consultaPagos(p.getIdPago()).get(0);
				pagoAux.setArchivoAtentaNota(nombreArchivo);
				cDAO.guardaPagos(pagoAux);
			} catch (JDBCException e) {
				e.printStackTrace();				
				System.out.println("Ocurrió un error al generar la atenta nota debido a:"+e.getCause());
			}	
				
		}
		
		return SUCCESS;
	}
	

	public InscripcionDAO getiDAO() {
		return iDAO;
	}
	public void setiDAO(InscripcionDAO iDAO) {
		this.iDAO = iDAO;
	}
	
	/***Variables de formulario**/	
	public int getTieneObservacion() {
		return tieneObservacion;
	}
	
	public void setTieneObservacion(int tieneObservacion) {
		this.tieneObservacion = tieneObservacion;
	}
	
	public int getTipoDocumentacion() {
		return tipoDocumentacion;
	}
	public void setTipoDocumentacion(int tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}
	public int getIdCriterioPago() {
		return idCriterioPago;
	}
	public void setIdCriterioPago(int idCriterioPago) {
		this.idCriterioPago = idCriterioPago;
	}	
	
	public int getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(int idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	public int getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(int documentacion) {
		this.documentacion = documentacion;
	}	
	
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	public int getErrorSistema() {
		return errorSistema;
	}
	public void setErrorSistema(int errorSistema) {
		this.errorSistema = errorSistema;
	}
	
	public int getRegistrar() {
		return registrar;
	}
	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}
	public String getMsjOk() {
		return msjOk;
	}
	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	
	/***LISTAS**/
	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public List<Especialista> getLstEspecialista() {
		return lstEspecialista;
	}
	
	public void setLstEspecialista(List<Especialista> lstEspecialista) {
		this.lstEspecialista = lstEspecialista;
	}
	
	public List<AsignacionCartasAdhesionV> getLstAsigCA() {
		return lstAsigCA;
	}
	public void setLstAsigCA(List<AsignacionCartasAdhesionV> lstAsigCA) {
		this.lstAsigCA = lstAsigCA;
	}
	
	public List<AsignacionCAaEspecialistaV> getLstAsignacionCAaEspecialistaV() {
		return lstAsignacionCAaEspecialistaV;
	}
	public void setLstAsignacionCAaEspecialistaV(
			List<AsignacionCAaEspecialistaV> lstAsignacionCAaEspecialistaV) {
		this.lstAsignacionCAaEspecialistaV = lstAsignacionCAaEspecialistaV;
	}
	
	public List<ExpedientesProgramasV> getLstExpedientesProgramasV() {
		return lstExpedientesProgramasV;
	}
	public void setLstExpedientesProgramasV(
			List<ExpedientesProgramasV> lstExpedientesProgramasV) {
		this.lstExpedientesProgramasV = lstExpedientesProgramasV;
	}
	

	public List<OficioObsSolicitudPago> getLstOficioObsSolicitudPago() {
		return lstOficioObsSolicitudPago;
	}
	public void setLstOficioObsSolicitudPago(
			List<OficioObsSolicitudPago> lstOficioObsSolicitudPago) {
		this.lstOficioObsSolicitudPago = lstOficioObsSolicitudPago;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public List<AsignacionCartasAdhesionEspecialistaV> getLstDetallePagosCAEspecialistaV() {
		return lstDetallePagosCAEspecialistaV;
	}

	public void setLstDetallePagosCAEspecialistaV(
			List<AsignacionCartasAdhesionEspecialistaV> lstDetallePagosCAEspecialistaV) {
		this.lstDetallePagosCAEspecialistaV = lstDetallePagosCAEspecialistaV;
	}

	public Integer getCriterioPago() {
		return criterioPago;
	}

	public void setCriterioPago(Integer criterioPago) {
		this.criterioPago = criterioPago;
	}

	public String getDescCriterioPago() {
		return descCriterioPago;
	}

	public void setDescCriterioPago(String descCriterioPago) {
		this.descCriterioPago = descCriterioPago;
	}

	public String getEstatusCA() {
		return estatusCA;
	}

	public void setEstatusCA(String estatusCA) throws UnsupportedEncodingException {
		this.estatusCA = Utilerias.convertirISO88591aUTF8(estatusCA);
	}

	public String getFianza() {
		return fianza;
	}

	public void setFianza(String fianza) {
		this.fianza = fianza;
	}

	public Integer getNumCampos() {
		return numCampos;
	}

	public void setNumCampos(Integer numCampos) {
		this.numCampos = numCampos;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public Pagos getP() {
		return p;
	}

	public void setP(Pagos p) {
		this.p = p;
	}

	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public List<CuentaBancaria> getLstCuentaBancarias() {
		return lstCuentaBancarias;
	}

	public void setLstCuentaBancarias(List<CuentaBancaria> lstCuentaBancarias) {
		this.lstCuentaBancarias = lstCuentaBancarias;
	}

	public List<Bancos> getBancos() {
		return bancos;
	}

	public void setBancos(List<Bancos> bancos) {
		this.bancos = bancos;
	}

	public PlazasBancarias getPlaza() {
		return plaza;
	}

	public void setPlaza(PlazasBancarias plaza) {
		this.plaza = plaza;
	}

	public List<PlazasBancarias> getLstPlazas() {
		return lstPlazas;
	}

	public void setLstPlazas(List<PlazasBancarias> lstPlazas) {
		this.lstPlazas = lstPlazas;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public String getNombrePlaza() {
		return nombrePlaza;
	}

	public void setNombrePlaza(String nombrePlaza) {
		this.nombrePlaza = nombrePlaza;
	}

	public String getCtaBancaria() {
		return ctaBancaria;
	}

	public void setCtaBancaria(String ctaBancaria) {
		this.ctaBancaria = ctaBancaria;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	public Double[] getVolumenesAut() {
		return volumenesAut;
	}
	public void setVolumenesAut(Double[] volumenesAut) {
		this.volumenesAut = volumenesAut;
	}

	public Double[] getImportesAut() {
		return importesAut;
	}

	public void setImportesAut(Double[] importesAut) {
		this.importesAut = importesAut;
	}

	public Double[] getCuotasApoyo() {
		return cuotasApoyo;
	}

	public void setCuotasApoyo(Double[] cuotasApoyo) {
		this.cuotasApoyo = cuotasApoyo;
	}

	public List<EtapaIniEsquema> getLstEtapaIniEsquema() {
		return lstEtapaIniEsquema;
	}

	public void setLstEtapaIniEsquema(List<EtapaIniEsquema> lstEtapaIniEsquema) {
		this.lstEtapaIniEsquema = lstEtapaIniEsquema;
	}

	public List<CartaAdhesionEtapaVolImpV> getLstCartaAdhesionEtapaVolImpV() {
		return lstCartaAdhesionEtapaVolImpV;
	}

	public void setLstCartaAdhesionEtapaVolImpV(
			List<CartaAdhesionEtapaVolImpV> lstCartaAdhesionEtapaVolImpV) {
		this.lstCartaAdhesionEtapaVolImpV = lstCartaAdhesionEtapaVolImpV;
	}

	public List<DocumentacionSPCartaAdhesionV> getLstDocumentacionSPCartaAdhesion() {
		return lstDocumentacionSPCartaAdhesion;
	}

	public void setLstDocumentacionSPCartaAdhesion(
			List<DocumentacionSPCartaAdhesionV> lstDocumentacionSPCartaAdhesion) {
		this.lstDocumentacionSPCartaAdhesion = lstDocumentacionSPCartaAdhesion;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}	

	/* Implementar ServletContextAware */
	public void setServletContext(ServletContext context){
		this.context = context;
	}

	public String getRutaRaiz() {
		return rutaRaiz;
	}

	public void setRutaRaiz(String rutaRaiz) {
		this.rutaRaiz = rutaRaiz;
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

	public String getSelectedPagos() {
		return selectedPagos;
	}

	public void setSelectedPagos(String selectedPagos) {
		this.selectedPagos = selectedPagos;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getLeyendaOficio() {
		return leyendaOficio;
	}

	public void setLeyendaOficio(String leyendaOficio) {
		this.leyendaOficio = leyendaOficio;
	}

	public String getRutaMarcaAgua() {
		return rutaMarcaAgua;
	}

	public void setRutaMarcaAgua(String rutaMarcaAgua) {
		this.rutaMarcaAgua = rutaMarcaAgua;
	}

	public String getRutaAserca() {
		return rutaAserca;
	}

	public void setRutaAserca(String rutaAserca) {
		this.rutaAserca = rutaAserca;
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

	public String getAtentaNotaNum() {
		return atentaNotaNum;
	}

	public void setAtentaNotaNum(String atentaNotaNum) {
		this.atentaNotaNum = atentaNotaNum;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<Personal> getLstPersonal() {
		return lstPersonal;
	}

	public void setLstPersonal(List<Personal> lstPersonal) {
		this.lstPersonal = lstPersonal;
	}

	public Personal getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Personal destinatario) {
		this.destinatario = destinatario;
	}

	public Especialista getEmisor() {
		return emisor;
	}

	public void setEmisor(Especialista emisor) {
		this.emisor = emisor;
	}

	public List<PagosV> getLstConsultaPagosCAEspecialista() {
		return lstConsultaPagosCAEspecialista;
	}

	public void setLstConsultaPagosCAEspecialista(
			List<PagosV> lstConsultaPagosCAEspecialista) {
		this.lstConsultaPagosCAEspecialista = lstConsultaPagosCAEspecialista;
	}

	public String getFechaAtentaNota() {
		return fechaAtentaNota;
	}

	public void setFechaAtentaNota(String fechaAtentaNota) {
		this.fechaAtentaNota = fechaAtentaNota;
	}

	public List<PagosDetalleCAV> getLstEditDetallePagosCAEspecialistaV() {
		return lstEditDetallePagosCAEspecialistaV;
	}

	public void setLstEditDetallePagosCAEspecialistaV(
			List<PagosDetalleCAV> lstEditDetallePagosCAEspecialistaV) {
		this.lstEditDetallePagosCAEspecialistaV = lstEditDetallePagosCAEspecialistaV;
	}

	public boolean isBand() {
		return band;
	}

	public void setBand(boolean band) {
		this.band = band;
	}

	public AsignacionCAaEspecialistaV getLstEspecialistaCA() {
		return lstEspecialistaCA;
	}

	public void setLstEspecialistaCA(AsignacionCAaEspecialistaV lstEspecialistaCA) {
		this.lstEspecialistaCA = lstEspecialistaCA;
	}

	public String getLeyendaCriterio() {
		return leyendaCriterio;
	}

	public void setLeyendaCriterio(String leyendaCriterio) {
		this.leyendaCriterio = leyendaCriterio;
	}

	public Double getCantidadCriterio() {
		return cantidadCriterio;
	}

	public void setCantidadCriterio(Double cantidadCriterio) {
		this.cantidadCriterio = cantidadCriterio;
	}

	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public String getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}

	public List<AsignacionCartasAdhesionEspecialistaV> getLstPagosVistaPrevia() {
		return lstPagosVistaPrevia;
	}

	public void setLstPagosVistaPrevia(List<AsignacionCartasAdhesionEspecialistaV> lstPagosVistaPrevia) {
		this.lstPagosVistaPrevia = lstPagosVistaPrevia;
	}

	public Map<Integer, String> getCapVolumen() {
		return capVolumen;
	}

	public void setCapVolumen(Map<Integer, String> capVolumen) {
		this.capVolumen = capVolumen;
	}

	public Map<Integer, String> getCapImporte() {
		return capImporte;
	}

	public void setCapImporte(Map<Integer, String> capImporte) {
		this.capImporte = capImporte;
	}

	public Map<Integer, String> getCapEtapa() {
		return capEtapa;
	}

	public void setCapEtapa(Map<Integer, String> capEtapa) {
		this.capEtapa = capEtapa;
	}

	public String getLeyendaEsquema() {
		return leyendaEsquema;
	}

	public void setLeyendaEsquema(String leyendaEsquema) {
		this.leyendaEsquema = leyendaEsquema;
	}

	public String getTextoFianza() {
		return textoFianza;
	}

	public void setTextoFianza(String textoFianza) {
		this.textoFianza = textoFianza;
	}
	
	public boolean isTieneFianza() {
		return tieneFianza;
	}

	public void setTieneFianza(boolean tieneFianza) {
		this.tieneFianza = tieneFianza;
	}

	public Integer getSolicitudesAtendidas() {
		return solicitudesAtendidas;
	}

	public void setSolicitudesAtendidas(Integer solicitudesAtendidas) {
		this.solicitudesAtendidas = solicitudesAtendidas;
	}

	public Integer getProductoresBeneficiados() {
		return productoresBeneficiados;
	}

	public void setProductoresBeneficiados(Integer productoresBeneficiados) {
		this.productoresBeneficiados = productoresBeneficiados;
	}

	public boolean isCerrarExpediente() {
		return cerrarExpediente;
	}

	public void setCerrarExpediente(boolean cerrarExpediente) {
		this.cerrarExpediente = cerrarExpediente;
	}

	public Integer getIdEstatusCA() {
		return idEstatusCA;
	}

	public void setIdEstatusCA(Integer idEstatusCA) {
		this.idEstatusCA = idEstatusCA;
	}

	public Personal getVoBo() {
		return voBo;
	}

	public void setVoBo(Personal voBo) {
		this.voBo = voBo;
	}

	public Double getPorcentajeFianza() {
		return porcentajeFianza;
	}

	public void setPorcentajeFianza(Double porcentajeFianza) {
		this.porcentajeFianza = porcentajeFianza;
	}

	public List<CartasPrimerPagoV> getLstCartasPrimerPagoV() {
		return lstCartasPrimerPagoV;
	}

	public void setLstCartasPrimerPagoV(List<CartasPrimerPagoV> lstCartasPrimerPagoV) {
		this.lstCartasPrimerPagoV = lstCartasPrimerPagoV;
	}

	public Double getVolumenPago() {
		return volumenPago;
	}

	public void setVolumenPago(Double volumenPago) {
		this.volumenPago = volumenPago;
	}

	public Double getImportePago() {
		return importePago;
	}

	public void setImportePago(Double importePago) {
		this.importePago = importePago;
	}

	public Double getVolumenCertificados() {
		return volumenCertificados;
	}

	public void setVolumenCertificados(Double volumenCertificados) {
		this.volumenCertificados = volumenCertificados;
	}

	public Double getVolumenConstancias() {
		return volumenConstancias;
	}

	public void setVolumenConstancias(Double volumenConstancias) {
		this.volumenConstancias = volumenConstancias;
	}
}
