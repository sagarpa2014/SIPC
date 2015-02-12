package mx.gob.comer.sipc.action.relaciones;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.domain.*;
import mx.gob.comer.sipc.domain.catalogos.*;
import mx.gob.comer.sipc.domain.transaccionales.*;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.*;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapturaRelacionesAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private RelacionesDAO rDAO = new RelacionesDAO();
	private InscripcionDAO iDAO = new InscripcionDAO();
	private List<Ciclo> lstCiclos;
	private List<Ejercicios> lstEjercicios;
	private List<Cultivo> lstCultivo;
	private List<ProgramaRelacionCultivosV> lstCultivoPerRel;
	private List<ProgramaRelacionCiclosV> lstCicloPerRel;
	private List<ProgramaRelacionCiclosV> lstEjercicioPerRel;
	private List<Relaciones> lstRelaciones;
	private List<Estado> lstEstados;
	private List<CompradoresV> lstCompradoresV;
	private List<Bancos> lstBancos;
	private List<AlmacenGeneralDeposito> lstAlmacenes;
	private List<TipoDocumentoPago> lstTipoDocumentoPagos;
	private List<GruposCamposRelacionV> lstGpoCampoTerrestreRelEncabezadoV;
	private List<GruposCamposRelacionTerrestreV> lstGpoCampoTerrestreRelDetalleV;
	private List<GruposCamposRelacionV> lstGpoCampoMaritimaRelEncabezadoV;
	private List<GruposCamposRelacionMaritimaV> lstGpoCampoMaritimaRelDetalleV;
	private List<GruposCamposRelacionV> lstGpoCampoCertificadosRelEncabezadoV;
	private List<GruposCamposRelacionCertificadosV> lstGpoCampoCertificadosRelDetalleV;
	private List<GruposCamposRelacionV> lstGpoCampoVentasRelEncabezadoV;
	private List<GruposCamposRelacionVentasV> lstGpoCampoVentasRelDetalleV;
	private List<PersonalizacionRelacionesV> lstPersonalizacionRelV;
	
	private List<IniEsquemaRelacion> lstIniEsquemaRelacion;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPartV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionProdV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPrediosV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionBodV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionBoletasV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionCompraVentaV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionNumProdV;
	private List<CicloPrograma> lstCiclosProgramas;
	private List<GruposCamposRelacionTerrestreV> lstRelacionesTerrestre;
	private List<GruposCamposRelacionMaritimaV> lstRelacionesMaritima;
	private List<GruposCamposRelacionCertificadosV> lstRelacionesCertificados;
	private List<GruposCamposRelacionVentasV> lstRelacionesVentas;
	private List<ComprasDatosParticipante> lstComprasDatosParticipante;
	private List<ComprasDatosParticipanteV> lstComprasDatosParticipanteV;
	private List<ComprasDatosProductor> lstComprasDatosProductor;
	private List<ComprasDatosProductorV> lstComprasDatosProductorV;
	private Long folioRegistro;
	private BigInteger folioSerial;
	private int idCultivo;	
	private int idCiclo;
	private int idEjercicio;
	private int idRelacion;
	private Map<Long,String> descripcionDet;
	private Integer[] grupoVisible;
	private int registrar;
	private long idPerRel;
	private boolean bandConfiguracionYaRegistrada;	
	private int numCiclos;
	private String selecCiclo;
	private String selecAnio;
	private long idRelCompEnc;
	private Integer idComprador;
	private String tipoDato;
	private String fechaInicio;
	private String fechaFin;
	private String cicloAgricola;
	private String nombre;
	private String claveBodega;
	private String folioCertificado;
	private int errorClaveBodega;
	private Integer exactitud;
	private Integer tamanio;
	private String folioCartaAdhesion;
	private Integer cultivoRelacion;
	private Integer estadoBodega;
	private Integer idEstado;
	private String nombreCultivos;
	private long idPgrCiclo;	
	private String nombreBarco;
	private String lugarDestino;
	private String domicilioBodega;
	private Integer razonSocialAlmacen;
	private String domicilio;
	private String cuadroSatisfactorio;
	private int numPredios;
	private int numBolTicket;
	private Integer numFactGlobal;
	private int numFacVenta;
	private Integer numContratos;
	private Integer numPagosAXC;
	private int numPagosSinAXC;
	private String[] capPredio;
	private long idCompPer;
	private String folioProductor;
	private Integer numProdBen;
	private List<ComprasPredioV> lstComprasPredio;
	private String folioPredio;
	private Integer predioSecuencial;
	private String predioAlterno;
	private long idCompProd;
	private String[] capBolTicket;
	private Date[] capFechaEntrada;
	private Double[] capVolBolTicket;
	private Date[] capFechaEmisionFacGlobal;
	private String[] capFolioFacGlobal;
	private Double[] capImpFacGlobal;
	private String[] capNombrePerFac;
	private String[] capRfcFacGlobal;
	private Double[] capVolFacGlobal;
	private Date[] capFechaEmisionFac;
	private String[] capFolioFac;
	private Double[] capImpSolFac;
	private String[] capRfcFac;
	private Double[] capVolSolFac;
	private Double[] capVolTotalFac;
	private String[] capFolioContrato;
	private Double[] capImpPactado;
	private Double[] capImpCompTon;
	private Double[] capImpCompTonProd;
	private Double[] capImpPacAxc;
	private Double[] capImpPacAxcProd;
	private Integer[] capBancoIdSin;
	private Date[] capFechaDocPago;
	private String[] capFolioDocPago;
	private Integer[] capIdTipoDocPago;
	private Double[] capImpDocPago;
	private List<ComprasEntradaBodega> lstComprasEntradaBodBoletas;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalNombreV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalNumeroV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalFechaV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalRfcV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalImporteV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalVolumenV;
	private List<ComprasFacVentaGlobal> lstComprasVentaGlobalNombre;
	private List<ComprasFacVentaGlobal> lstComprasVentaGlobalNumero;
	private List<ComprasFacVentaGlobal> lstComprasVentaGlobalFecha;
	private List<ComprasFacVentaGlobal> lstComprasVentaGlobalRfc;
	private List<ComprasFacVentaGlobal> lstComprasVentaGlobalImporte;
	private List<ComprasFacVentaGlobal> lstComprasVentaGlobalVolumen;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionVentaNumeroV;
	private List<ComprasFacVenta> lstComprasFacVentaGrano;
	private List<ComprasMaxCamposV> lstComprasMaxCamposV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionContratoFolioV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionContratoImporteV;
	private List<ComprasContrato> lstComprasContratoFolio;
	private List<ComprasContrato> lstComprasContratoImporte;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompProdV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompPacV;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompPacProdV;
	private List<ComprasPagoProdAxc> lstComprasContratoImporteComp;
	private List<ComprasPagoProdAxc> lstComprasContratoImporteCompProd;
	private List<ComprasPagoProdAxc> lstComprasContratoImporteCompPac;
	private List<ComprasPagoProdAxc> lstComprasContratoImporteCompPacProd;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoSinAXCBancoIdSin;
	private List<GruposCamposRelacionV> lstGruposCamposRelacionPagoSinAXC;
	private List<ComprasPagoProdSinAxc> lstComprasPagoSinAXC;	
	private List<IniEsquemaRelacion> lstIniEsquemaRelacionCriterio;
	private List<IniEsquemaRelacionV> lstIniEsquemaRelacionV;
	private Long idIniEsquemaRelacion;
	private List<ComprasBodegaTicketV> lstBodegaTicket;
	private int errorBodegaTicket;
	private int errorClaveBodegaExiste;
	private String boletaTicket;
	private List<Productores> lstProductores;
	private int errorProductores;
	private String nombreProductor;
	private String paterno;
	private String materno;
	private String rfc;
	private String curp;
	private String tipoPersona;
	private Integer[] capPredioSec;
	private String folioFac;
	private String rfcR;
	private boolean si;
	private int numPrediosAnterior;
	//Entrada a la bodega
	private double totalVolumenBolTicket;
	//Factura de venta del grano*/
	private double totalFacVentaVolSolicitado;
	private double totalFacVentaVolFacturado;
	private double totalFacVentaImpFacturas;
	//pagos sin AXC*/
	private double totalPagoSinAXCImpDocPagos;
	private String nombreRelacion;
	private String nombreEsquema;
	private Double superficiePredio;
	private int errorValidacion;
	private Double superficieTotal;
	private Double superficieTotalTmp;
	private Double superficieSumTotalPredio;
	private int count;
	private int errorFacturaByProductor;
	private int validaParBodProd;
	private long fechaActualL;
	private int excluyePredio;
	private int idPredio;
	private List<Modalidad> lstModalidad;
	private Integer [] capModalidad;
	private double totalesSuperficieTotal; 
	private double totalesSuperficieApoyar;
	private int validaApartado;	
	private Map<String, Double> capSuperficiePredio;
	private int errorNoExistenPredios;
	private int estatusProductor;
	private Long [] idSComPer;
	private String [] idRegCA;
	private String estado;
	private int errorFolioCertificado;
	private int errorFolioCertificadoAlmacen;
	private String nombreAlmacen;
	private String folioFacVenta;
	private int errorFolioFacVenta;
	private int tipoFormatoTerrestre;
	private int tipoFormatoMaritimo;
	private String folioTalonTerrestre;
	private String folioTalonMaritimo;
	private int errorFolioTalonTerrestre;
	private int errorFolioTalonMaritimo;
	private int estatus;
	
	public String listProgramaCap(){
		try{	
			lstIniEsquemaRelacionCriterio = rDAO.consultaIniEsquemaRelacion(0,null,null,null);
			fechaInicio = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
			fechaFin = new SimpleDateFormat("yyyy").format(new Date())+"-12-31";
			lstIniEsquemaRelacionV = rDAO.consultaIniEsquemaRelacionV(0,null, null, null);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listProgramaRelacion debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listProgramaRelacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}

	public String ejecutaBusquedaPrograma(){	
		lstIniEsquemaRelacionCriterio = rDAO.consultaIniEsquemaRelacion(0,null,null,null);
		lstIniEsquemaRelacionV = rDAO.consultaIniEsquemaRelacionV(idIniEsquemaRelacion);		
		return SUCCESS;
	}
	
	public String listRelacionesCapturadas(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			lstPersonalizacionRelV = rDAO.consultaPersonalizacionRelaciones(0, 0, null, null, fechaInicio, fechaFin,idIniEsquemaRelacion);
			lstRelaciones = rDAO.consultaRelacion(-1);
			lstIniEsquemaRelacion = rDAO.consultaIniEsquemaRelacion(0,null,null,null);
			List<PersonalizacionRelacionesV> listaPerRelaciones = rDAO.consultaPersonalizacionRelaciones(idPerRel);
			if(listaPerRelaciones.size()==0){
				return SUCCESS;
			}else{
				PersonalizacionRelacionesV prv = rDAO.consultaPersonalizacionRelaciones(idPerRel).get(0);			
				idRelacion = prv.getIdTipoRelacion();	
				nombreEsquema = prv.getNombreEsquema();
			}
			recuperaDatosGenerales(0,idIniEsquemaRelacion,0,0);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}		
		return SUCCESS;
	}
	
	public String capturarRelaciones(){
		try{			
			String fechaActualS = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
			fechaActualL = Long.parseLong(fechaActualS);
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			nombre = cDAO.consultaComprador(idComprador).get(0).getNombre();
			lstRelaciones = rDAO.consultaRelacion(idRelacion);
			PersonalizacionRelacionesV prv = rDAO.consultaPersonalizacionRelaciones(idPerRel).get(0);
			idRelacion = prv.getIdTipoRelacion();
			nombreRelacion = prv.getRelacion();
			lstBancos = cDAO.consultaBanco(-1);
			recuperaDatosRelaciones();
			if(idRelacion==1 || idRelacion  == 2 || idRelacion  == 3){
				lstEstados = cDAO.consultaEstado(0);
				if(idRelacion == 1){
					lstCompradoresV = cDAO.consultaCompradoresV(-1);
					lstTipoDocumentoPagos = cDAO.cosultaTipoDocumentoPagos(-1);
				}
			}else if(idRelacion==4){
				lstAlmacenes = cDAO.consultaAlmacenadora(-1);
			}else if(idRelacion == 5){
				lstEstados = cDAO.consultaEstado(0);
			}
			nombreCultivos = prv.getCultivos();
			cicloAgricola = prv.getCicloAgricola();
			lstCultivoPerRel = rDAO.consultaCultivosPerRel(idPerRel);
			lstCicloPerRel = rDAO.consultaCiclosEjerPerRel(idPerRel);
			
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en personalizarRelaciones  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en personalizarRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			listRelacionesCapturadas();
		}
		return SUCCESS;		
	}
	
	public String foliosPredios() throws JDBCException, Exception{
		try{ 
			if(lstGruposCamposRelacionPrediosV == null){
				lstGruposCamposRelacionPrediosV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 6, 6, "", "PRODUCTOR", "");
			}
			setLstModalidad(cDAO.consultaModalidad(0));
			if(registrar == 0){
				lstComprasPredio = new ArrayList<ComprasPredioV>();
				for(int i=1; i<=numPredios; i++){
					lstComprasPredio.add(new ComprasPredioV());
				}
			}else if(registrar == 1){				
				lstComprasPredio = rDAO.consultaComprasPredioV(idCompProd);
				if(lstComprasPredio.size()>0){
					numPredios = lstComprasPredio.size();
				}
			}else if(registrar == 2){
				if(lstComprasPredio == null){
					lstComprasPredio = rDAO.consultaComprasPredioV(idCompProd);
				}				
				if(lstComprasPredio.size()>0){
					numPrediosAnterior = lstComprasPredio.size();
					if(numPredios > numPrediosAnterior){
						int resta = 0;
						resta = numPredios - numPrediosAnterior;
						for(int i =1; i<=resta; i++){
							lstComprasPredio.add(new ComprasPredioV());
						}
					}else if(numPredios < numPrediosAnterior){
						List<ComprasPredioV> lstComprasPredioTmp = new ArrayList<ComprasPredioV>();
						for (int i = 1; i <= numPredios; i++){
							lstComprasPredioTmp.add(lstComprasPredio.get(i-1));
						}
						lstComprasPredio = new ArrayList<ComprasPredioV>();
						lstComprasPredio = lstComprasPredioTmp;
					}
				}
				// Recuperando Totales
				for(ComprasPredioV l: lstComprasPredio){
					totalesSuperficieApoyar += l.getSuperficieApoyar();
				}
			}
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String bolTicket() throws JDBCException, Exception{
		try{
			totalVolumenBolTicket = 0;
			lstGruposCamposRelacionBodV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 7, -1, "", "", "");
			//if(registrar == 0){
				lstComprasEntradaBodBoletas = new ArrayList<ComprasEntradaBodega>();	
				for(int i=1; i<=numBolTicket; i++){
					lstComprasEntradaBodBoletas.add(new ComprasEntradaBodega());
				}
			//}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}

	public String facturaGlobal() throws JDBCException, Exception{
		try{
			lstGruposCamposRelacionVentaGlobalNombreV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 8, 14, "", "VENTAGLOBAL", "");
			lstGruposCamposRelacionVentaGlobalNumeroV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 8, 15, "", "VENTAGLOBAL", "");
			lstGruposCamposRelacionVentaGlobalFechaV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 8, 16, "", "VENTAGLOBAL", "");
			lstGruposCamposRelacionVentaGlobalRfcV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 8, 17, "", "VENTAGLOBAL", "");
			lstGruposCamposRelacionVentaGlobalImporteV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 8, 18, "", "VENTAGLOBAL", "");
			lstGruposCamposRelacionVentaGlobalVolumenV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 8, 64, "", "VENTAGLOBAL", "");
			lstComprasVentaGlobalNombre = new ArrayList<ComprasFacVentaGlobal>();
			lstComprasVentaGlobalNumero = new ArrayList<ComprasFacVentaGlobal>();
			lstComprasVentaGlobalFecha = new ArrayList<ComprasFacVentaGlobal>();
			lstComprasVentaGlobalRfc = new ArrayList<ComprasFacVentaGlobal>();
			lstComprasVentaGlobalImporte = new ArrayList<ComprasFacVentaGlobal>();
			lstComprasVentaGlobalVolumen = new ArrayList<ComprasFacVentaGlobal>();

			if(registrar == 0){
				for(int i=1; i<=numFactGlobal; i++){
					lstComprasVentaGlobalNombre.add(new ComprasFacVentaGlobal());
					lstComprasVentaGlobalNumero.add(new ComprasFacVentaGlobal());
					lstComprasVentaGlobalFecha.add(new ComprasFacVentaGlobal());
					lstComprasVentaGlobalRfc.add(new ComprasFacVentaGlobal());
					lstComprasVentaGlobalImporte.add(new ComprasFacVentaGlobal());
					lstComprasVentaGlobalVolumen.add(new ComprasFacVentaGlobal());
				}
			}else if(registrar == 1 || registrar == 2){
				Integer ventaGlobal = rDAO.consultaComprasMaxCamposV(idCompPer, idCompProd).get(0).getComprasFacVentaGlobal();
				if(ventaGlobal != 0){
					numFactGlobal = ventaGlobal;
					lstComprasVentaGlobalNombre= rDAO.consultaComprasFacVentaGlobal(idCompProd);
					lstComprasVentaGlobalNumero = rDAO.consultaComprasFacVentaGlobal(idCompProd);
					lstComprasVentaGlobalFecha = rDAO.consultaComprasFacVentaGlobal(idCompProd);
					lstComprasVentaGlobalRfc = rDAO.consultaComprasFacVentaGlobal(idCompProd);
					lstComprasVentaGlobalImporte = rDAO.consultaComprasFacVentaGlobal(idCompProd);
					lstComprasVentaGlobalVolumen = rDAO.consultaComprasFacVentaGlobal(idCompProd);
				}
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {	
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String facturaVenta() throws JDBCException, Exception{
		try{
			lstGruposCamposRelacionVentaNumeroV= rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 9, -1, "", "", "");
			lstComprasFacVentaGrano = new ArrayList<ComprasFacVenta>();
			for(int i = 1; i <= numFacVenta; i++){
				lstComprasFacVentaGrano.add(new ComprasFacVenta());
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en facturaVenta debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en facturaVenta debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String contratoCompras() throws JDBCException, Exception{
		try{
			lstGruposCamposRelacionContratoFolioV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 10, 21, "", "COMPRAVENTA", "");
			lstGruposCamposRelacionContratoImporteV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 10, 22, "", "COMPRAVENTA", "");
			lstComprasContratoFolio = new ArrayList<ComprasContrato>();
			lstComprasContratoImporte = new ArrayList<ComprasContrato>();
			
			if(registrar == 0){
				for(int i=1; i<=numContratos; i++){
					lstComprasContratoFolio.add(new ComprasContrato());
					lstComprasContratoImporte.add(new ComprasContrato());
				}
			}else if(registrar == 1 || registrar == 2){
				Integer contratos = rDAO.consultaComprasMaxCamposV(idCompPer, idCompProd).get(0).getComprasContrato();
				if(contratos != 0){
					numContratos = contratos;
					lstComprasContratoFolio = rDAO.consultaComprasContratos(idCompProd);
					lstComprasContratoImporte = rDAO.consultaComprasContratos(idCompProd);
				}
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String pagosAXC() throws JDBCException, Exception{
		try{
			lstGruposCamposRelacionPagoAXCImporteCompV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 11, 23, "", "PAGOAXC", "");
			lstGruposCamposRelacionPagoAXCImporteCompProdV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 11, 24, "", "PAGOAXC", "");
			lstGruposCamposRelacionPagoAXCImporteCompPacV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 11, 25, "", "PAGOAXC", "");
			lstGruposCamposRelacionPagoAXCImporteCompPacProdV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 11, 26, "", "PAGOAXC", "");

			lstComprasContratoImporteComp = new ArrayList<ComprasPagoProdAxc>();
			lstComprasContratoImporteCompProd = new ArrayList<ComprasPagoProdAxc>();
			lstComprasContratoImporteCompPac = new ArrayList<ComprasPagoProdAxc>();
			lstComprasContratoImporteCompPacProd = new ArrayList<ComprasPagoProdAxc>();
			
			if(registrar == 0){
				for(int i=1; i<=numPagosAXC; i++){
					lstComprasContratoImporteComp.add(new ComprasPagoProdAxc());
					lstComprasContratoImporteCompProd.add(new ComprasPagoProdAxc());
					lstComprasContratoImporteCompPac.add(new ComprasPagoProdAxc());
					lstComprasContratoImporteCompPacProd.add(new ComprasPagoProdAxc());
				}
			}else if(registrar == 1 || registrar == 2){
				Integer axc = rDAO.consultaComprasMaxCamposV(idCompPer, idCompProd).get(0).getComprasPagoProdAxc();
				if(axc != 0){
					numPagosAXC = axc;
					lstComprasContratoImporteComp = rDAO.consultaComprasPagoProdAxc(idCompProd);
					lstComprasContratoImporteCompProd = rDAO.consultaComprasPagoProdAxc(idCompProd);
					lstComprasContratoImporteCompPac = rDAO.consultaComprasPagoProdAxc(idCompProd);
					lstComprasContratoImporteCompPacProd = rDAO.consultaComprasPagoProdAxc(idCompProd);
				}
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String pagosSinAXC() throws JDBCException, Exception{
		try{
			totalPagoSinAXCImpDocPagos = 0;
			lstBancos = cDAO.consultaBanco(-1);
			lstTipoDocumentoPagos= cDAO.cosultaTipoDocumentoPagos(-1);
			lstGruposCamposRelacionPagoSinAXC = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 12, -1, "", "", "");
			lstComprasPagoSinAXC = new ArrayList<ComprasPagoProdSinAxc>();
			
			for(int i = 1; i <= numPagosSinAXC; i++){
				lstComprasPagoSinAXC.add(new ComprasPagoProdSinAxc());	
			}
			
			
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listRelacionesCapturadas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public void recuperaDatosRelaciones() throws Exception{
			if(idRelacion == 1){				
				if(idCompPer>0){
					//Recupera datos de la tabla de compras_datos_participante
					lstComprasDatosParticipante = rDAO.consultaComprasDatosParticipantes(idCompPer);
					claveBodega = lstComprasDatosParticipante.get(0).getClaveBodega();
					cultivoRelacion = lstComprasDatosParticipante.get(0).getIdCultivo();
					idPgrCiclo = lstComprasDatosParticipante.get(0).getIdPgrCiclo();
					estadoBodega = lstComprasDatosParticipante.get(0).getEstadoAcopio();	
					domicilioBodega = cDAO.consultaBodegas(claveBodega).get(0).getCalle();
					folioCartaAdhesion = lstComprasDatosParticipante.get(0).getFolioCartaAdhesion();
				}
				
				lstGruposCamposRelacionPartV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1, -1, "", "PARTICIPANTE", "");
				lstGruposCamposRelacionProdV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1, -1, "", "PRODUCTOR", "");
				lstGruposCamposRelacionNumProdV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1, 30, "", "NUMPROD", "");
				lstGruposCamposRelacionPrediosV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 6, 6, "", "PRODUCTOR", "");
				lstGruposCamposRelacionBodV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1, -1, "", "BODEGA", "");
				lstGruposCamposRelacionVentaNumeroV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 9    , -1, "", "VENTA", "");
				lstGruposCamposRelacionPagoSinAXC = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, 12, -1, "", "PAGOSINAXC", "");					
					if(registrar == 2 || registrar == 1){
						lstComprasDatosProductor = rDAO.consultaComprasDatosProductor(idCompProd,0,0);
						estatusProductor = lstComprasDatosProductor.get(0).getEstatus(); 
						folioProductor = lstComprasDatosProductor.get(0).getFolioProductor().toString();
						numProdBen = lstComprasDatosProductor.get(0).getNumProdBen();
						recuperaDatosProductor();
						if(lstGruposCamposRelacionBodV.size()>0){
							lstComprasEntradaBodBoletas= rDAO.consultaComprasEntradaBodega(idCompProd);
							numBolTicket = lstComprasEntradaBodBoletas.size();
							// Recuperando Totales
							for(ComprasEntradaBodega l: lstComprasEntradaBodBoletas){
								if(l.getVolBolTicket() != null){
									totalVolumenBolTicket += l.getVolBolTicket();
								}
							}
						}
						if(lstGruposCamposRelacionVentaNumeroV.size()>0){
							lstComprasFacVentaGrano = rDAO.consultaComprasFacVenta(idCompProd);
							numFacVenta = lstComprasFacVentaGrano.size(); 
							// Recuperando Totales							
							for(ComprasFacVenta l: lstComprasFacVentaGrano){
								if(l.getVolSolFac()!=null){
									totalFacVentaVolSolicitado += l.getVolSolFac();
								}
								if(l.getVolTotalFac() != null){
									totalFacVentaVolFacturado += l.getVolTotalFac();
								}
								if(l.getImpSolFac()!=null){
									totalFacVentaImpFacturas += l.getImpSolFac();	
								}
							}
						}
						if(lstGruposCamposRelacionPagoSinAXC.size()>0){
							totalPagoSinAXCImpDocPagos = 0;
							lstBancos = cDAO.consultaBanco(-1);
							lstTipoDocumentoPagos= cDAO.cosultaTipoDocumentoPagos(-1);
							lstComprasPagoSinAXC = rDAO.consultaComprasPagoProdSinAxc(idCompProd);
							numPagosSinAXC = lstComprasPagoSinAXC.size(); 

							for(ComprasPagoProdSinAxc l: lstComprasPagoSinAXC){
								if(l.getImpDocPago()!=null){
									totalPagoSinAXCImpDocPagos += l.getImpDocPago();
								}						 
							}

						}
							 
					}//End registrar = 1 o registrar = 2
			
			}else if (idRelacion == 2){//RELACION TERRESTRE
				lstGpoCampoTerrestreRelEncabezadoV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "ENC", "");
				if(registrar == 0){
					List<GruposCamposRelacionV> lstGpoCampoTerrestreRelDetalle = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "DET", "");
					lstGpoCampoTerrestreRelDetalleV = new ArrayList<GruposCamposRelacionTerrestreV>();
					for(GruposCamposRelacionV gcrv : lstGpoCampoTerrestreRelDetalle){
						GruposCamposRelacionTerrestreV g = new GruposCamposRelacionTerrestreV();
						g.setCampo(gcrv.getCampo());
						g.setIdCampo(gcrv.getIdCampo());
						g.setIdGrupo(gcrv.getIdGrupo());
						g.setGrupo(gcrv.getGrupo());
						g.setDescripcion(null);
						g.setIdCampoRelacion(gcrv.getIdCampoRelacion());
						lstGpoCampoTerrestreRelDetalleV.add(g);
					}
				}else{
					lstGpoCampoTerrestreRelDetalleV = rDAO.consultaGruposCamposTerrestreV(0,  idPerRel, idComprador, null, claveBodega, cultivoRelacion, null, folioTalonTerrestre, false, idPgrCiclo, estadoBodega);
					folioCartaAdhesion = lstGpoCampoTerrestreRelDetalleV.get(0).getFolioCartaAdhesion();
				}
			}else if (idRelacion == 3){//RELACION MARITIMA
				lstGpoCampoMaritimaRelEncabezadoV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "ENC", "");
				if(registrar == 0){
					List<GruposCamposRelacionV> lstlstGpoCampoMaritimoRelDetalle = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "DET", "");
					lstGpoCampoMaritimaRelDetalleV = new ArrayList<GruposCamposRelacionMaritimaV>();
					for(GruposCamposRelacionV glst: lstlstGpoCampoMaritimoRelDetalle){
						GruposCamposRelacionMaritimaV gcrm = new GruposCamposRelacionMaritimaV();
						gcrm.setCampo(glst.getCampo());
						gcrm.setIdCampo(glst.getIdCampo());
						gcrm.setIdGrupo(glst.getIdGrupo());
						gcrm.setGrupo(glst.getGrupo());
						gcrm.setDescripcion(null);
						gcrm.setIdCampoRelacion(glst.getIdCampoRelacion());
						lstGpoCampoMaritimaRelDetalleV.add(gcrm);
					}
				}else{
					lstGpoCampoMaritimaRelDetalleV = rDAO.consultaGruposCamposMaritimaV(0,  idPerRel, idComprador, null, claveBodega, cultivoRelacion, null, folioTalonMaritimo, false, idPgrCiclo, nombreBarco, lugarDestino);
					folioCartaAdhesion = lstGpoCampoMaritimaRelDetalleV.get(0).getFolioCartaAdhesion();
				}
				
			}else if (idRelacion == 4){//Certificados de deposito
				lstGpoCampoCertificadosRelEncabezadoV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "ENC", "");
				if(registrar == 0){
					List<GruposCamposRelacionV> lstGpoCampoCertificadosRelDetalle = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "DET", "");
					lstGpoCampoCertificadosRelDetalleV = new ArrayList<GruposCamposRelacionCertificadosV>();
					for(GruposCamposRelacionV glst: lstGpoCampoCertificadosRelDetalle){
						GruposCamposRelacionCertificadosV g = new GruposCamposRelacionCertificadosV();
						g.setCampo(glst.getCampo());
						g.setIdCampo(glst.getIdCampo());
						g.setIdGrupo(glst.getIdGrupo());
						g.setGrupo(glst.getGrupo());
						g.setDescripcion(null);
						g.setIdCampoRelacion(glst.getIdCampoRelacion());
						lstGpoCampoCertificadosRelDetalleV.add(g);
					}
				}else{	
					lstGpoCampoCertificadosRelDetalleV = rDAO.consultaGruposCamposCertificadosV(0, idPerRel, idComprador, null,claveBodega, cultivoRelacion, null,  folioCertificado, false, idPgrCiclo, razonSocialAlmacen);
					folioCartaAdhesion = lstGpoCampoCertificadosRelDetalleV.get(0).getFolioCartaAdhesion();
				}	
				if(claveBodega!=null && !claveBodega.isEmpty()){
					domicilioBodega = cDAO.consultaBodegas(claveBodega).get(0).getCalle();
				}
		
			}else if (idRelacion == 5){
				lstGpoCampoVentasRelEncabezadoV = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "ENC", "");
				if(registrar == 0){
					List<GruposCamposRelacionV> lstGpoCampoVentasDetalle = rDAO.consultaGruposCampostV(-1, idRelacion, idPerRel, -1,-1, "DET", "");
					lstGpoCampoVentasRelDetalleV = new ArrayList<GruposCamposRelacionVentasV>();
					for(GruposCamposRelacionV gcrv: lstGpoCampoVentasDetalle){
						GruposCamposRelacionVentasV g = new GruposCamposRelacionVentasV();
						g.setCampo(gcrv.getCampo());
						g.setIdCampo(gcrv.getIdCampo());
						g.setIdGrupo(gcrv.getIdGrupo());
						g.setGrupo(gcrv.getGrupo());
						g.setDescripcion(null);
						g.setIdCampoRelacion(gcrv.getIdCampoRelacion());
						lstGpoCampoVentasRelDetalleV.add(g); 
					}
				}else{
					lstGpoCampoVentasRelDetalleV = rDAO.consultaGruposCamposVentasV(0,idPerRel, idComprador, null, cultivoRelacion,null, folioFacVenta, false, idPgrCiclo);
					folioCartaAdhesion = lstGpoCampoVentasRelDetalleV.get(0).getFolioCartaAdhesion();
				}
			}//End relacion 5
		
	}	
	
	public String registraCapturaRelacion(){
		try{ 
			 if(idRelacion == 1){
				guardarDetalleRelacionCompras(idPerRel);
			 }else if(idRelacion == 2){
				guardarDetalleRelacionTerrestre(idPerRel);
			 }else if(idRelacion == 3){
				guardarDetalleRelacionMaritima(idPerRel);
			 }else if(idRelacion == 4){
				guardarDetalleRelacionCertificados(idPerRel);
			 }else if(idRelacion == 5){
				guardarDetalleRelacionVentas(idPerRel);
			 }
			 registrar = 1;	
			 capturarRelaciones();
			 cuadroSatisfactorio = ("El registro se guardo exitosamente");
					
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraPersonalizacionRelacion debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraPersonalizacionRelacion debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unused")
	private void guardarDetalleRelacionCompras(long idPerRel) throws JDBCException, Exception{
			int elementosBorrados = 0;
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			ComprasDatosParticipante participante = null;
			ComprasDatosProductor productor = null;
			if (registrar == 0){
				if(idCompPer == 0){ //Se realiza nueva captura de bodega en la tabla de "compras_datos_participante" 
					participante = new ComprasDatosParticipante();
					participante.setIdComprador(idComprador);
					participante.setIdPerRel(idPerRel);
					participante.setClaveBodega(claveBodega.toUpperCase().trim());
					participante.setEstadoAcopio(estadoBodega);
					participante.setIdCultivo(cultivoRelacion);
					participante.setIdPgrCiclo(idPgrCiclo);
					participante.setFechaRegistro(new Date());
					participante.setUsuarioRegistro( (Integer)session.get("idUsuario"));
					participante.setIdEstatusComprasDatosPar(1); //Estatus de registrado
					
					participante  = (ComprasDatosParticipante) cDAO.guardaObjeto(participante);
				}else{//De otro modo se captura sobre la misma bodega
					participante = rDAO.consultaComprasDatosParticipantes(idComprador, claveBodega, idPerRel).get(0);					
				}
			
				idCompPer = participante.getIdCompPer();
				productor = new ComprasDatosProductor();
				productor.setIdCompPer(idCompPer);
				
				if(folioProductor.indexOf("-") != -1){
					folioProductor = folioProductor.substring(0, folioProductor.lastIndexOf("-"));   
					System.out.println("folioProductor "+folioProductor);
				}
				
				productor.setFolioProductor(Long.parseLong(folioProductor));
				productor.setRfc(rfc);
				if(curp!=null && !curp.isEmpty()){
					productor.setRfc(curp);
				}
				productor.setIdPerRel(idPerRel);
				productor.setFechaRegistro(new Date());
				productor.setUsuarioRegistro((Integer)session.get("idUsuario"));
				if(curp != null && curp != ""){
					productor.setCurp(curp);
				}				
				if(numProdBen != null){
					productor.setNumProdBen(numProdBen);
				}
				if(validaApartado != 100){
					productor.setEstatus(1); //captura parcial
					estatusProductor = 1;
				}else{
					productor.setEstatus(2); //captura completa
					estatusProductor = 2;
				}
				productor = (ComprasDatosProductor) cDAO.guardaObjeto(productor);
				idCompProd = productor.getIdCompProd();
			}else if(registrar == 2){
				productor = rDAO.consultaComprasDatosProductor(idCompProd, 0,0).get(0);
				if(validaApartado == 100){
					productor.setEstatus(2); //captura completa
					estatusProductor = 2;
				}
			}

			if(validaApartado  == 6 || validaApartado == 100 ){
				if(registrar == 2 ){
					elementosBorrados = rDAO.borrarRegistros(idCompProd, 6);
				}
				String predioSecuencial = "";
				Set<String> capSuperficiePredioIt = capSuperficiePredio.keySet();
				Iterator<String> it =  capSuperficiePredioIt.iterator();
				
				while(it.hasNext()){
					String predio = it.next();
					Double superficie = capSuperficiePredio.get(predio);
					if(superficie != null){
						ComprasPredio cp = new ComprasPredio();
						if(predio.contains("-")){
							//Guarda en los campos con predio y predio secuencial
							String [] predioConSec = predio.split("-");
							predio = predioConSec[0];
							predioSecuencial = predioConSec[1];
							cp.setFolioPredio(predio);
							cp.setFolioPredioSec(Integer.parseInt(predioSecuencial));
						}else{
							//Guarda en los campos con predio alterno
							cp.setPredioAlterno(predio);
						}
						cp.setIdCompProd(idCompProd);
						cp.setIdPerRel(idPerRel);
						cp.setSuperficie(superficie);
						cDAO.guardaObjeto(cp);
					}
				}
			}
			
			if(validaApartado  == 7 || validaApartado == 100 ){// Entradas a la bodega
				if(registrar == 2 ){
					//Borrar los registros de la base de datos
					elementosBorrados = rDAO.borrarRegistros(idCompProd, 7);
				}					
				if (numBolTicket > 0 ){
					for (int i = 0; i < capBolTicket.length; i++) {
						ComprasEntradaBodega ceb = new ComprasEntradaBodega();
						ceb.setIdCompProd(idCompProd);
						if(capBolTicket != null){
							if(capBolTicket[i] != null){
								ceb.setBoletaTicketBascula(capBolTicket[i]);
							}
						}
						
						if(capFechaEntrada != null){
							if(capFechaEntrada[i] != null){
								ceb.setFechaEntrada(capFechaEntrada[i]);
							}
						}
						if(capVolBolTicket != null){
							if(capVolBolTicket[i] != null){
								ceb.setVolBolTicket(capVolBolTicket[i]);
							}
						}
						
						cDAO.guardaObjeto(ceb);
					}
				}
			}//end if(validaApartado  == 7 || validaApartado == 100 ){
	
			if(validaApartado  == 9 || validaApartado == 100 ){//Facturas de venta al grano
				if(registrar == 2 ){
					System.out.println("idCompProd "+idCompProd +"validaApartado "+validaApartado);
					//Borrar los registros de la base de datos
					elementosBorrados = rDAO.borrarRegistros(idCompProd, 9);
				}
				
				if (numFacVenta > 0 ){
					for (int i = 0; i < capFolioFac.length; i++) {
						ComprasFacVenta cfv = new ComprasFacVenta();
						cfv.setIdCompProd(idCompProd);
						if(capFechaEmisionFac != null){
							if(capFechaEmisionFac [i]!=null){
								cfv.setFechaEmisionFac(capFechaEmisionFac[i]);
							}
						}
						if(capFolioFac != null){
							if(capFolioFac [i]!=null){
								cfv.setFolioFac(capFolioFac[i]);	
							}
						}
						if(capImpSolFac != null){
							if(capImpSolFac [i]!=null){
								cfv.setImpSolFac(capImpSolFac[i]);
							}
						}
						cfv.setRfcFac(rfc);
						if(capVolSolFac != null){
							if(capVolSolFac [i]!=null){
								cfv.setVolSolFac(capVolSolFac[i]);	
							}
						}
						if(capVolTotalFac != null){
							if(capVolTotalFac [i]!=null){
								cfv.setVolTotalFac(capVolTotalFac[i]);
							}
						}
						cDAO.guardaObjeto(cfv);
					}
				}
			}// 	if(validaApartado  == 9 || validaApartado == 100 ) end Facturas de venta al grano
			
			if(validaApartado  == 12 || validaApartado == 100 ){//Pago al Productor sin AXC
				if(registrar == 2 ){
					System.out.println("idCompProd "+idCompProd +"validaApartado "+validaApartado);
					//Borrar los registros de la base de datos
					elementosBorrados = rDAO.borrarRegistros(idCompProd, 12);
				}
				if (numPagosSinAXC > 0){
					for (int i = 0; i < capImpDocPago.length; i++) {				
						ComprasPagoProdSinAxc cppsa = new ComprasPagoProdSinAxc();
						cppsa.setIdCompProd(idCompProd);					
						if(capBancoIdSin!=null){
							if(capBancoIdSin[i]!= -1){
								cppsa.setBancoId(capBancoIdSin[i]);
							}
						}	
						if(capFechaDocPago!=null){
							if(capFechaDocPago[i]!= null){
								cppsa.setFechaDocPago(capFechaDocPago[i]);
							}
						}	
						if(capFolioDocPago!=null){
							if(capFolioDocPago[i]!= null){
								cppsa.setFolioDocPago(capFolioDocPago[i]);
							}
						}	
						if(capIdTipoDocPago!=null){
							if(capIdTipoDocPago[i]!= -1){
								cppsa.setIdTipoDocPago(capIdTipoDocPago[i]);
							}
						}
						
						if(capImpDocPago!=null){
							if(capImpDocPago[i]!= null){								
								cppsa.setImpDocPago(capImpDocPago[i]);
							}
						}	
						cDAO.guardaObjeto(cppsa);				
					}
				}
			}// end validaApartado  == 12 || validaApartado == 100 Pago al Productor sin AXC*/
	}//end guardarDetalleRelacionCompras
	
	private void guardarDetalleRelacionTerrestre(long idPerRel) throws Exception{
		session = ActionContext.getContext().getSession();
		idComprador = (Integer)session.get("idComprador");
		GruposCamposRelacionV gCampoRelacion = null;
		CamposRelacionTerrestre crt = null;
		Set<Long> descripcionDetIt = descripcionDet.keySet();
		Iterator<Long> it =  descripcionDetIt.iterator();
		while(it.hasNext()){
			Long idGpoCampoRelacionDet = it.next();
			String descripcion = descripcionDet.get(idGpoCampoRelacionDet);
			gCampoRelacion = rDAO.consultaGruposCampostV(idGpoCampoRelacionDet, "DET").get(0);
			if (registrar == 0){
				crt = new CamposRelacionTerrestre();
				crt.setIdCampoRelacion(gCampoRelacion.getIdCampoRelacion());
				crt.setIdComprador(idComprador);
				crt.setClaveBodegaOpp(claveBodega.toUpperCase().trim());
				crt.setIdPgrCultivo(cultivoRelacion);
				crt.setIdPgrCiclo(idPgrCiclo);
				crt.setEstadoBodega(estadoBodega);
				crt.setTipoTramo(tipoFormatoTerrestre);
			}else if(registrar == 1 || registrar == 2){
				crt =  rDAO.consultaCamposTerrestre(idGpoCampoRelacionDet,  idComprador, cultivoRelacion, idPgrCiclo, folioTalonTerrestre, claveBodega).get(0);
			}			
			if(descripcion!=null && !descripcion.isEmpty()){
				if(gCampoRelacion.getIdCampo() ==33 && gCampoRelacion.getIdGrupo() == 15){//Folio del talon
					crt.setDescripcion(descripcion.toUpperCase());
				}else if (gCampoRelacion.getIdCampo() ==32 && gCampoRelacion.getIdGrupo() == 15){//Fecha de Embarque
					crt.setDescripcion(descripcion);
				}else if(gCampoRelacion.getIdCampo() ==39 && gCampoRelacion.getIdGrupo() == 16){//Fecha de Recepcion
					crt.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==35 && gCampoRelacion.getIdGrupo() == 16){//Bodega Final
					crt.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==37 && gCampoRelacion.getIdGrupo() == 16){//Frontera entidad
					crt.setDescripcion(descripcion);					
				}else if (gCampoRelacion.getIdCampo() ==38 && gCampoRelacion.getIdGrupo() == 16){//Planta procesadora
					crt.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==34 && gCampoRelacion.getIdGrupo() == 15){//Volumen embarcado
					crt.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==41 && gCampoRelacion.getIdGrupo() == 16){//Volumen recibido
					crt.setDescripcion(descripcion);
				}else if(gCampoRelacion.getIdCampo() ==40 && gCampoRelacion.getIdGrupo() == 16){//N° TICKETS DE BÁSCULA DE ENTRADA EN ALMACÉN
						crt.setDescripcion(descripcion);
				}
				crt.setIdPerRel(idPerRel);
				crt.setFolio(folioTalonTerrestre);
				cDAO.guardaObjeto(crt);
			}
		}
	}
	
	private void guardarDetalleRelacionMaritima(long idPerRel) throws ParseException {
		session = ActionContext.getContext().getSession();
		idComprador = (Integer)session.get("idComprador");
		GruposCamposRelacionV gCampoRelacion = null;
		CamposRelacionMaritima crm = null;
		Set<Long> descripcionDetIt = descripcionDet.keySet();
		Iterator<Long> it =  descripcionDetIt.iterator();
		while(it.hasNext()){
			Long idGpoCampoRelacionDet = it.next();
			String descripcion = descripcionDet.get(idGpoCampoRelacionDet);
			gCampoRelacion = rDAO.consultaGruposCampostV(idGpoCampoRelacionDet, "DET").get(0);
			if (registrar == 0 || registrar == 3){
				crm = new CamposRelacionMaritima();
				crm.setIdCampoRelacion(gCampoRelacion.getIdCampoRelacion());
				crm.setIdComprador(idComprador);
				crm.setNombreBarco(nombreBarco.toUpperCase());
				crm.setLugarDestino(lugarDestino.toUpperCase());
				crm.setClaveBodegaOpp(claveBodega.toUpperCase().trim());
				crm.setIdPgrCultivo(cultivoRelacion);
				crm.setIdPgrCiclo(idPgrCiclo);
				crm.setTipoTramo(tipoFormatoMaritimo);
			}else if(registrar == 1 || registrar == 2){
				crm =  rDAO.consultaCamposMaritima(idGpoCampoRelacionDet, idComprador, cultivoRelacion, idPgrCiclo, folioTalonMaritimo, claveBodega, nombreBarco, lugarDestino).get(0);			
			}
			if(descripcion!=null){
				if (gCampoRelacion.getIdCampo() ==46 && gCampoRelacion.getIdGrupo() == 15){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==36 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==48 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion.toUpperCase());
				}else if (gCampoRelacion.getIdCampo() ==47 && gCampoRelacion.getIdGrupo() == 15){
					crm.setDescripcion(descripcion.toUpperCase());
				}else if (gCampoRelacion.getIdCampo() ==51 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion.toUpperCase());
				}else if (gCampoRelacion.getIdCampo() ==32 && gCampoRelacion.getIdGrupo() == 15){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==39 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==49 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==50 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==34 && gCampoRelacion.getIdGrupo() == 15){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==41 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==40 && gCampoRelacion.getIdGrupo() == 16){
					crm.setDescripcion(descripcion);
				}
				crm.setIdPerRel(idPerRel);
				crm.setFolio(folioTalonMaritimo);
				cDAO.guardaObjeto(crm);
			}
		}
	}
		
	private void guardarDetalleRelacionCertificados(long idPerRel) throws ParseException {
		session = ActionContext.getContext().getSession();
		idComprador = (Integer)session.get("idComprador");
		GruposCamposRelacionV gCampoRelacion = null;
		CamposRelacionCertificados crcr = null;
		Set<Long> descripcionDetIt = descripcionDet.keySet();
		Iterator<Long> it =  descripcionDetIt.iterator();
		//String folioCertificado = "";
		while(it.hasNext()){
			Long idGpoCampoRelacionDet = it.next();
			String descripcion = descripcionDet.get(idGpoCampoRelacionDet);
			gCampoRelacion = rDAO.consultaGruposCampostV(idGpoCampoRelacionDet, "DET").get(0);
			if(registrar == 1 || registrar == 2){
				crcr =  rDAO.consultaCamposCertificados(idGpoCampoRelacionDet, idComprador, cultivoRelacion, idPgrCiclo, folioCertificado, claveBodega, razonSocialAlmacen).get(0);
			}else if (registrar == 0 || registrar == 3){
				crcr = new CamposRelacionCertificados();
				crcr.setIdCampoRelacion(gCampoRelacion.getIdCampoRelacion());
				crcr.setIdComprador(idComprador);
				crcr.setClaveBodega(claveBodega.toUpperCase().trim());
				crcr.setRazonSocialAlmacen(razonSocialAlmacen);
				crcr.setIdPgrCultivo(cultivoRelacion);
				crcr.setIdPgrCiclo(idPgrCiclo);
			}
			if(descripcion!=null){
				if (gCampoRelacion.getIdCampo() ==54 && gCampoRelacion.getIdGrupo() == 23){// Folio del certificado
					folioCertificado = descripcion.toUpperCase();
					crcr.setDescripcion(descripcion.toUpperCase());	
				}
				if (gCampoRelacion.getIdCampo() ==55 && gCampoRelacion.getIdGrupo() == 24){
					crcr.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() ==56 && gCampoRelacion.getIdGrupo() == 25){
					crcr.setDescripcion(descripcion);
				}
				if (gCampoRelacion.getIdCampo() ==57 && gCampoRelacion.getIdGrupo() == 26){
					crcr.setDescripcion(descripcion);
				}
				crcr.setIdPerRel(idPerRel);
				crcr.setFolio(folioCertificado);
				cDAO.guardaObjeto(crcr);
			}
		}//End while
	}
	
	private void guardarDetalleRelacionVentas(long idPerRel) throws ParseException{
		session = ActionContext.getContext().getSession();
		idComprador = (Integer)session.get("idComprador");
		GruposCamposRelacionV gCampoRelacion = null;
		CamposRelacionVentas crv = null;
		Set<Long> descripcionDetIt = descripcionDet.keySet();
		Iterator<Long> it =  descripcionDetIt.iterator();
		
		while(it.hasNext()){
			Long idGpoCampoRelacionDet = it.next();
			String descripcion = descripcionDet.get(idGpoCampoRelacionDet);
			gCampoRelacion = rDAO.consultaGruposCampostV(idGpoCampoRelacionDet, "DET").get(0);
			if(registrar == 0){
				crv = new CamposRelacionVentas();
				crv.setIdCampoRelacion(gCampoRelacion.getIdCampoRelacion());
				crv.setIdComprador(idComprador);
				crv.setIdPgrCultivo(cultivoRelacion);
				crv.setIdPgrCiclo(idPgrCiclo);
			}else if(registrar == 1 || registrar == 2){
				crv =  rDAO.consultaCamposVentas(idGpoCampoRelacionDet , idComprador, cultivoRelacion, idPgrCiclo, folioFacVenta).get(0);
			}
				
			if(descripcion!=null){
				if(gCampoRelacion.getIdCampo() == 21 && gCampoRelacion.getIdGrupo() == 28){
					crv.setDescripcion(descripcion.toUpperCase());
				}else if(gCampoRelacion.getIdCampo() == 8 && gCampoRelacion.getIdGrupo() == 27){
					crv.setDescripcion(descripcion.toUpperCase());
				}else if(gCampoRelacion.getIdCampo() == 58 && gCampoRelacion.getIdGrupo() == 27){
					crv.setDescripcion(descripcion.toUpperCase());
				}else if (gCampoRelacion.getIdCampo() == 59 && gCampoRelacion.getIdGrupo() == 27){
					crv.setDescripcion(descripcion.toUpperCase());
				}else if (gCampoRelacion.getIdCampo() == 16 && gCampoRelacion.getIdGrupo() == 28){
						crv.setDescripcion(descripcion);					
				}else if (gCampoRelacion.getIdCampo() == 60 && gCampoRelacion.getIdGrupo() == 28){
						crv.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() == 61 && gCampoRelacion.getIdGrupo() == 29){
						crv.setDescripcion(descripcion);
				}else if (gCampoRelacion.getIdCampo() == 62 && gCampoRelacion.getIdGrupo() == 30){
						crv.setDescripcion(descripcion);
				}
				crv.setIdPerRel(idPerRel);
				crv.setFolio(folioFacVenta);
				cDAO.guardaObjeto(crv);
			}
		}
	}
	
	@SuppressWarnings("unused")
	public String validarPredio()throws JDBCException, Exception{
		try{
			Double superficieAnterior = 0.0;
			List<Predios> lstPredio = new ArrayList<Predios>();
			List<ComprasPredio> lstComprasPredio1 = null;
			if(predioAlterno!=null && !predioAlterno.isEmpty()){
				//Predio Alterno
				lstPredio = rDAO.consultaPredios(null,0, predioAlterno);
			}else{
				//Validar si el folio predio se encuentra registrado
				lstPredio = rDAO.consultaPredios(folioPredio,predioSecuencial);
			}
			
			
			if(registrar == 2){
				if(predioAlterno!=null && !predioAlterno.isEmpty()){
					//Recupera la superficie anterior capturada del Predio Alterno
					lstComprasPredio1 = rDAO.consultaComprasPredio(0, idCompProd,null, 0, predioAlterno);
				}else{
					//Recupera la superficie anterior capturada del predio y predio secuencial
					lstComprasPredio1 = rDAO.consultaComprasPredio(0, idCompProd,folioPredio,predioSecuencial, null);
				}		
				if(lstComprasPredio1.size()>0){
					superficieAnterior = lstComprasPredio1.get(0).getSuperficie();  
				}
			}
			superficieTotal = lstPredio.get(0).getSuperficieTotal();
			if(superficiePredio!=null){					 
				//Validar que no rebase la superficie total del predio a nivel programa
				if(predioSecuencial!=null){
					superficieSumTotalPredio = rDAO.getSuperficiePredioPrograma(folioPredio, predioSecuencial, idPerRel);
				}else{
					superficieSumTotalPredio = rDAO.getSuperficiePredioProgramaAlterno(folioPredio, idPerRel);
				}	
				superficieSumTotalPredio = superficieSumTotalPredio - superficieSumTotalPredio;
				superficieSumTotalPredio += superficiePredio;				
				if(superficieSumTotalPredio > superficieTotal){
					errorValidacion = 2;
				}
			}
				
			//}
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en validarSiExistePredio  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validarSiExistePredio  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
	    return SUCCESS;
	}
	
	public String validaClaveBodega(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			List<Bodegas> lstBodega = cDAO.consultaBodegas(claveBodega);
			//Verifica que la claveBodega exista en la base de datos
			if(idRelacion != 2 && idRelacion != 3){
				if(lstBodega.size()==0){
					errorClaveBodega = 1;
					return SUCCESS;
				}
			}else if(idRelacion == 2){//relacion terrestre
				if(tipoFormatoTerrestre == 0){
					if(lstBodega.size()==0){
						errorClaveBodega = 1;
						return SUCCESS;
					}
				}
			}else if(idRelacion == 3){//relacion maritima
				if(tipoFormatoMaritimo == 0){
					if(lstBodega.size()==0){
						errorClaveBodega = 1;
						return SUCCESS;
					}
				}
			}
			
			if(idRelacion == 1){
				if(rDAO.consultaComprasDatosParticipantes(idComprador, claveBodega, idPerRel,0, cultivoRelacion, idPgrCiclo, estadoBodega, null).size()>0){
					errorClaveBodegaExiste = 1;
					return SUCCESS;
				}
			}else if(idRelacion == 2){
				if (rDAO.consultaGruposCamposTerrestreV(0, idPerRel,
						idComprador, null, claveBodega, cultivoRelacion, null,
						null, true, idPgrCiclo, estadoBodega).size() > 0) {
					errorClaveBodegaExiste = 1;
					return SUCCESS;
				}
			}else if(idRelacion == 3){
				if (rDAO.consultaGruposCamposMaritimaV(0, idPerRel,
						idComprador, folioCartaAdhesion, claveBodega,
						cultivoRelacion, null, null, true, idPgrCiclo,
						nombreBarco, lugarDestino).size() > 0) {
					errorClaveBodegaExiste = 1;
					return SUCCESS;
				}
			}else if(idRelacion == 4){
				//Valida que la bodega no se encuentre capturada para el cultivo, ciclo, y almacen
				if(rDAO.consultaGruposCamposCertificadosV(4, idPerRel, idComprador, null,  claveBodega, cultivoRelacion, null, null, true, idPgrCiclo, razonSocialAlmacen).size()>0){
					errorClaveBodegaExiste = 1;
					return SUCCESS;
				}
			}
			
			if(idRelacion == 1 || idRelacion == 4){
				domicilio = lstBodega.get(0).getCalle();
			}			
			
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}

	public String validaBodegaTicket(){
		try{
			boletaTicket = boletaTicket.toUpperCase();
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			//Verifica que la claveBodega exista en la base de datos
			lstBodegaTicket = rDAO.consultaBodegaTicket( -1, boletaTicket, claveBodega);
			if(lstBodegaTicket.size()>0){
				errorBodegaTicket = 1;
				return SUCCESS;
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String validaFolioCertificadoDeposito(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
		
			//Verifica que el folio y almacen no se encuentren registrados en el mismo cultivo, ciclo, bodega y almacen
			lstGpoCampoCertificadosRelDetalleV = rDAO.consultaGruposCamposCertificadosV(0, idPerRel, idComprador, null,claveBodega, cultivoRelacion, null,  folioCertificado, false, idPgrCiclo, razonSocialAlmacen);
			if(lstGpoCampoCertificadosRelDetalleV.size()>0){
				errorFolioCertificado = 1;
				return SUCCESS;
			}
			//Verifica que no exista a nivel clave bodega - razon social
			lstGpoCampoCertificadosRelDetalleV = rDAO.consultaGruposCamposCertificadosV( folioCertificado, razonSocialAlmacen);
			if(lstGpoCampoCertificadosRelDetalleV.size()>0){
				errorFolioCertificadoAlmacen = 1;
				return SUCCESS;
			}
	
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String validarFolioFacVenta(){ //Relacion de Ventas
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			//Verifica que el folio no se encuentre registrado para el comprador, cultivo y ciclo
			lstGpoCampoVentasRelDetalleV = rDAO.consultaGruposCamposVentasV(0, idPerRel, idComprador, null, cultivoRelacion, null, folioFacVenta, true, idPgrCiclo);
			if(lstGpoCampoVentasRelDetalleV.size()>0){
				errorFolioFacVenta = 1;
				return SUCCESS;
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCertificadoDeposito  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
		
	public String validarFolioTalonTerrestre(){ //Relacion terrestre
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			//Verifica que el folio no se encuentre registrado para el comprador, cultivo, ciclo y bodega
			lstGpoCampoTerrestreRelDetalleV = rDAO.consultaGruposCamposTerrestreV(0,  idPerRel, idComprador, null, claveBodega, cultivoRelacion, null, folioTalonTerrestre, false, idPgrCiclo, estadoBodega);
			if(lstGpoCampoTerrestreRelDetalleV.size()>0){
				errorFolioTalonTerrestre = 1;
				return SUCCESS;
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validarFolioTalonTerrestre  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validarFolioTalonTerrestre  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String validarFolioTalonMaritima(){ //Relacion maritima
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			//Verifica que el folio no se encuentre registrado para el comprador, cultivo,  ciclo, clave bodega, folioTalonMaritimo, nombreBarco y lugar destino
			lstGpoCampoMaritimaRelDetalleV = rDAO.consultaGruposCamposMaritimaV(0,  idPerRel, idComprador, null, claveBodega, cultivoRelacion, null, folioTalonMaritimo, false, idPgrCiclo, nombreBarco, lugarDestino);
			if(lstGpoCampoMaritimaRelDetalleV.size()>0){
				errorFolioTalonMaritimo = 1;
				return SUCCESS;
			}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validarFolioTalonMaritima  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validarFolioTalonMaritima  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
		
	public String borrarRegistro() throws JDBCException, Exception{
		try{
			int elementosBorrados = 0;
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			idRelacion = rDAO.consultaPersonalizacionRelaciones(idPerRel).get(0).getIdTipoRelacion();
			if(idRelacion == 1){
				lstComprasDatosParticipante = rDAO.consultaComprasDatosParticipantes(idComprador, claveBodega, idPerRel);
				if(lstComprasDatosParticipante.size()!=0){
					idCompPer = rDAO.consultaComprasDatosParticipantes(idComprador, claveBodega, idPerRel).get(0).getIdCompPer();
					lstComprasDatosProductor = rDAO.consultaComprasDatosProductor(0,idCompPer, 0);
					if(lstComprasDatosProductor.size()!=0){
						idCompProd = rDAO.consultaComprasDatosProductor(0,idCompPer, 0).get(0).getIdCompProd();
						elementosBorrados = rDAO.borrarPartProd(idCompPer, idCompProd);
						AppLogger.info("app", "Se borro el registro  con la clave de Bodega "+claveBodega+" correspondiente al folio productor "+folioProductor+" de la tabla campos_relacion_compras");
						AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla campos_relacion_compras");
						cuadroSatisfactorio = ("Se borro el registro con la clave de Bodega "+claveBodega+" correspondiente al folio productor "+folioProductor+" de manera exitosa");
						return SUCCESS;
					}else if(lstComprasDatosProductor.size()==0){
						addActionError("No se han encontrado registros con esa clave bodega y folio productor, favor de verificar");
					}
				}else if(lstComprasDatosParticipante.size()==0){
					addActionError("No se han encontrado registros con esa clave bodega, favor de verificar");
					return SUCCESS;
				}
			}else if(idRelacion == 2){
				lstGpoCampoTerrestreRelDetalleV = rDAO.consultaGruposCamposTerrestreV(0, idPerRel, idComprador, null, claveBodega, cultivoRelacion, null, folioTalonTerrestre, false, idPgrCiclo, estadoBodega);
				if(lstGpoCampoTerrestreRelDetalleV.size()!=0){
					elementosBorrados = rDAO.borrarFolioTerrestre(idPerRel,  idComprador, claveBodega, cultivoRelacion, folioTalonTerrestre, idPgrCiclo);
					AppLogger.info("app", "Se borro el folio "+folioTalonTerrestre+" de la tabla campos_relacion_terrestre");
					AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla campos_relacion_terrestre");
					cuadroSatisfactorio = ("Se borro el folio "+folioTalonTerrestre+" de manera exitosa");
					return SUCCESS;
				}
			}else if(idRelacion == 3){
				lstGpoCampoMaritimaRelDetalleV = rDAO.consultaGruposCamposMaritimaV(0, idPerRel, idComprador, null, claveBodega, cultivoRelacion, null, folioTalonTerrestre, false, idPgrCiclo, nombreBarco, lugarDestino);
				if(lstGpoCampoMaritimaRelDetalleV.size()!=0){
					elementosBorrados = rDAO.borrarFolioMaritima(idPerRel,  idComprador, claveBodega, cultivoRelacion, folioTalonMaritimo, idPgrCiclo, nombreBarco, lugarDestino);
					AppLogger.info("app", "Se borro el folio "+folioTalonMaritimo+" de la tabla campos_relacion_maritima");
					AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla campos_relacion_maritima");
					cuadroSatisfactorio =("Se borro el folio "+folioTalonMaritimo+" de manera exitosa");
					return SUCCESS;
				}
			}else if(idRelacion == 4){
				lstGpoCampoCertificadosRelDetalleV = rDAO.consultaGruposCamposCertificadosV(0, idPerRel, idComprador, null,claveBodega, cultivoRelacion, null,  folioCertificado, false, idPgrCiclo, razonSocialAlmacen);
				if(lstGpoCampoCertificadosRelDetalleV.size()!=0){
					elementosBorrados = rDAO.borrarFolioCertificados(idPerRel, idComprador, claveBodega, cultivoRelacion, folioCertificado, idPgrCiclo, razonSocialAlmacen);
					AppLogger.info("app", "Se borro el folio ["+folioCertificado+"] clave bodega ["+claveBodega+"] almacen ["+razonSocialAlmacen+"] " +
							" comprador ["+idComprador+"] cultivo relacion ["+cultivoRelacion+"]de la tabla campos_relacion_certificados");
					cuadroSatisfactorio =("Se borro el folio "+folioCertificado+" de manera exitosa");
					return SUCCESS;
				}
			}else if(idRelacion == 5){
				lstGpoCampoVentasRelDetalleV = rDAO.consultaGruposCamposVentasV( 0, idPerRel, idComprador, null, cultivoRelacion, null, folioFacVenta, false, idPgrCiclo);
				if(lstGpoCampoVentasRelDetalleV.size()!=0){
					elementosBorrados = rDAO.borrarFolioVentas(idPerRel, idComprador, cultivoRelacion, folioFacVenta, idPgrCiclo);
					AppLogger.info("app", "Se borro el folio "+folioFacVenta+" de la tabla campos_relacion_ventas");
					AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla campos_relacion_ventas");
					cuadroSatisfactorio =("Se borro el folio "+folioFacVenta+" de manera exitosa");
					return SUCCESS;
				}
			}
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en borrarRegistro  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en borrarRegistro  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			listRelacionesCapturadas();
		}
		return SUCCESS;
	}
	
	public String recuperaDatosProductor(){
		try{
			//Recupera Datos Productores
			lstProductores = rDAO.consultaProductores(Long.parseLong(folioProductor));
			if(registrar == 0){
				if(validaParBodProd == 1){
					int valida = 0;
					session = ActionContext.getContext().getSession();
					idComprador = (Integer)session.get("idComprador");
					//Validar que el mismo participante, bodega y productor no se encuentre registrado
					valida = rDAO.validaPartBodegaProd(claveBodega, Long.parseLong(folioProductor), idComprador);
					if(valida!=0){
						errorProductores = 2;
						return SUCCESS;
					}					
				}
			}
			
			List<Predios> lstPredio = new ArrayList<Predios>();
			//recupera los predios ligados al productor, estado
			lstPredio = rDAO.consultaPredios(null, 0, "", folioProductor.toString(), estadoBodega);
			lstComprasPredio  = new ArrayList<ComprasPredioV>();
			if(registrar == 0){	
				for(Predios l: lstPredio){
					lstComprasPredio.add(new ComprasPredioV(l.getPredio(), l.getPredioSecuencial(), l.getPredioAlterno(), null,  l.getSuperficieTotal(), null, l.getModalidad()));
				}
			}else if(registrar == 1 || registrar == 2){
				List<ComprasPredio> lstComprasPredio1 = new ArrayList<ComprasPredio>();
				for(Predios l: lstPredio){
					ComprasPredioV cpv = new ComprasPredioV();
					cpv.setSuperficieTotal(l.getSuperficieTotal());
					cpv.setModalidad(l.getModalidad());
					//Recupera el predio en tabla de compras_predio
					if(l.getPredioAlterno()!=null && !l.getPredioAlterno().isEmpty()){
						cpv.setPredioAlterno(l.getPredioAlterno());
						lstComprasPredio1 = rDAO.consultaComprasPredio(0, idCompProd,null, 0, l.getPredioAlterno());
					}else{
						cpv.setFolioPredio(l.getPredio());
						cpv.setFolioPredioSec(l.getPredioSecuencial());
						lstComprasPredio1 = rDAO.consultaComprasPredio(0, idCompProd,l.getPredio(),l.getPredioSecuencial().intValue(), null);
					}
					
					if(lstComprasPredio1.size()>0){
						cpv.setSuperficieApoyar(lstComprasPredio1.get(0).getSuperficie());
					}
					lstComprasPredio.add(cpv);
				}
				for(ComprasPredioV l: lstComprasPredio){
					if(l.getSuperficieApoyar() != null){
						totalesSuperficieApoyar += l.getSuperficieApoyar();
					}					
				}
				
			}	
			
			if(lstComprasPredio.size()==0){
				errorNoExistenPredios = 1;
			}
			numPredios = lstComprasPredio.size(); 
			

			if(lstProductores.size() == 0){
				errorProductores = 1;
				nombreProductor = null;
				paterno = null;
				materno = null;
				rfc = null;
				curp = null;
				return SUCCESS;
			}else{
				tipoPersona = lstProductores.get(0).getTipoPersona();
				nombreProductor = lstProductores.get(0).getNombre();
				paterno = lstProductores.get(0).getPaterno();
				materno = lstProductores.get(0).getMaterno();
				rfc = lstProductores.get(0).getRfc();
				if(tipoPersona.equals("F")){
					curp = lstProductores.get(0).getCurp();
				}
			}			
			
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaDatosProductor  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaDatosProductor  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	
	public String busquedaParticipanteBodegas(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			if(idRelacion==1){
				lstComprasDatosParticipanteV = rDAO.consultaComprasDatosParticipanteV(0, null,null, idPerRel, idComprador,0,0,0);
			}else if(idRelacion == 2){
				lstGpoCampoTerrestreRelDetalleV = rDAO.consultaRelacionTerrestreByBodegaOPP(idPerRel, idComprador, null);	
			}else if(idRelacion == 3){				
				lstGpoCampoMaritimaRelDetalleV  = rDAO.consultaRelacionMaritimaByBodegaOPP(idPerRel,idComprador, null);
			}else if(idRelacion==4){
				lstGpoCampoCertificadosRelDetalleV = rDAO.consultaRelacionCDByBodegaAlmacen(idPerRel,idComprador,null);	
			}else if(idRelacion==5){
				lstGpoCampoVentasRelDetalleV = rDAO.consultaRelacionVentaByCicloCultivo(idPerRel, idComprador,null);	
			}			
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCartaAdhesion  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}catch (Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en busquedaParticipanteBodegas  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	

	public String lstProductorRelCompras(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			lstComprasDatosProductorV = rDAO.consultaComprasDatosProductorV(idCompPer, 0);
			recuperaDatosGenerales(1,idIniEsquemaRelacion, estadoBodega,0);		
			//verifica el estatus de los datos del participante
			estatus = rDAO.consultaComprasDatosParticipantes(idCompPer).get(0).getIdEstatusComprasDatosPar();
			
		}catch (Exception e){
			AppLogger.error("errores","Ocurrio un error en productorRelCompras  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	
	public String lstCertificadoPorBodega(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			lstGpoCampoCertificadosRelDetalleV= rDAO.consultaRelacionBodegaFolioCertificado(idPerRel, idComprador, claveBodega, cultivoRelacion, idPgrCiclo, razonSocialAlmacen);
			recuperaDatosGenerales(4, idIniEsquemaRelacion,0,razonSocialAlmacen);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en lstCertificadoPorBodega  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en lstCertificadoPorBodega  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}	
			
		return SUCCESS;
	}
		
	public String mostrarVentasByCicloCultivo(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			lstGpoCampoVentasRelDetalleV = rDAO.consultaVentaByFolioCicloCultivo(idPerRel, idComprador, cultivoRelacion, idPgrCiclo);
			recuperaDatosGenerales(5, idIniEsquemaRelacion,0,0);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en mostrarVentasByCicloCultivo  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en mostrarVentasByCicloCultivo  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}	
		return SUCCESS;
	}
	
	public String mostrarTerrestreByBodegaOPlantaProcesadora(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			lstGpoCampoTerrestreRelDetalleV = rDAO.consultaTerrestreByFolioBodegaOPlanP(idPerRel, idComprador, cultivoRelacion, idPgrCiclo, claveBodega);
			recuperaDatosGenerales(2, idIniEsquemaRelacion, estadoBodega, 0);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en mostrarVentasByCicloCultivo  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en mostrarVentasByCicloCultivo  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}	
		return SUCCESS;
	}

	public String mostrarMaritimaByBodegaOPlantaProcesadora(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			lstGpoCampoMaritimaRelDetalleV = rDAO.consultaMaritimaByFolioBodegaOPlanP(idPerRel, idComprador, cultivoRelacion, idPgrCiclo, claveBodega, nombreBarco, lugarDestino);
			recuperaDatosGenerales(3, idIniEsquemaRelacion,0,0);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en mostrarVentasByCicloCultivo  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    }catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en mostrarVentasByCicloCultivo  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}	
		return SUCCESS;
	}
	
	public void recuperaDatosGenerales(int idRelacion, long idIniEsquemaRelacion, int idEstado, int razonSocialAlmacen){
		//Recupera datos generales
		if(idRelacion !=0 && idRelacion != -1){
			nombreRelacion = rDAO.consultaRelacion(idRelacion).get(0).getRelacion();
		}		
		if(idIniEsquemaRelacion !=0 && idIniEsquemaRelacion != -1){
			nombreEsquema = rDAO.consultaIniEsquemaRelacion(idIniEsquemaRelacion,null,null,null).get(0).getNombreEsquema();	
		}		
		if(idEstado !=0 && idEstado != -1){
			estado = cDAO.consultaEstado(idEstado).get(0).getNombre();
		}		
		if(razonSocialAlmacen !=0 && razonSocialAlmacen != -1){
			nombreAlmacen = cDAO.consultaAlmacenadora(razonSocialAlmacen).get(0).getNombre();
		}	
	}
	
	public String setSuperficieTotalByPredio(){
		
		//Recupera la superfice total del predio
		List<Predios> lstPredio = new ArrayList<Predios>();
		if(predioSecuencial!=null){
			//Validar si el folio predio se encuentra registrado
			lstPredio = rDAO.consultaPredios(folioPredio,predioSecuencial);
		}else{
			//Predio Alterno
			lstPredio = rDAO.consultaPredios(null,0,folioPredio);
		}	
	
		superficieTotal = lstPredio.get(0).getSuperficieTotal();
		
		return SUCCESS;
	}
	
	public String validaFoliosFactura(){
		try{
			errorFacturaByProductor = rDAO.validaFacturaByProductor(folioFac.trim(), Long.parseLong(folioProductor),-1);						
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en validaFoliosFactura  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
	}
	
	public String capRegistroCartaAdhesion(){
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			recuperaDatosGenerales(idRelacion, idIniEsquemaRelacion,0,0);
			if(idRelacion == 1){
				lstComprasDatosParticipanteV = rDAO.consultaComprasDatosParticipanteV(0, null,null, idPerRel, idComprador,0,0,1);
			}else if(idRelacion == 2){
				lstGpoCampoTerrestreRelDetalleV = rDAO.consultaRelacionTerrestreByBodegaOPPRegistroCA(idPerRel, idComprador);	
				System.out.println("Registro CA"+lstGpoCampoTerrestreRelDetalleV.size());
			}else if(idRelacion == 3){				
				lstGpoCampoMaritimaRelDetalleV  = rDAO.consultaRelacionMaritimaByBodegaOPPRegistroCA(idPerRel, idComprador);
			}else if(idRelacion == 4){
				lstGpoCampoCertificadosRelDetalleV = rDAO.consultaRelacionCDByBodegaAlmacenRegistroCA(idPerRel,idComprador);	
			}else if(idRelacion == 5){
				lstGpoCampoVentasRelDetalleV = rDAO.consultaRelacionVentaByCicloCultivoRegistroCA(idPerRel,idComprador);
			}
			
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en capRegistroCartaAdhesion  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}catch (Exception e){
			AppLogger.error("errores","Ocurrio un error en capRegistroCartaAdhesion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}		
		
		return SUCCESS;
	}
	
	public String registraCartaAdhesion() throws JDBCException, Exception{
		try{
			session = ActionContext.getContext().getSession();
			idComprador = (Integer)session.get("idComprador");
			int idCultivoSI = 0, ejercicio = 0, idCiclo = 0, contEntitiesActualizadas = 0;
			
			List<SolicitudInscripcionV> lstSIV = iDAO.consultaSolicitudInscripcionV(folioCartaAdhesion, idComprador);
			if(lstSIV.size()>0){
				SolicitudInscripcionV siv = lstSIV.get(0);
				CiclosProgramasV cpv = iDAO.consultaCicloProgramasV(0,  siv.getIdCicloPrograma()).get(0);
				idCultivoSI = siv.getIdCultivo();
				idCiclo = cpv.getIdCiclo();
				ejercicio = cpv.getEjercicio();
				List<ProgramaRelacionCiclosV>  lstPgrCiclo = rDAO.consultaIdPgrCiclo(idCiclo, ejercicio, idPerRel);
				idRelacion = rDAO.consultaPersonalizacionRelaciones(idPerRel).get(0).getIdTipoRelacion();
				if(lstPgrCiclo.size() >0){
					if(idRelacion == 1){
						for(int i = 0; i< idSComPer.length; i++){
							ComprasDatosParticipanteV cdpv = rDAO.consultaComprasDatosParticipanteV(0, null,null, 0, 0, idSComPer[i],0,0).get(0);
							if(idCiclo == cdpv.getIdCiclo().intValue() &&  ejercicio == cdpv.getEjercicio() && idCultivoSI == cdpv.getIdCultivo()){
								rDAO.registraFolioCartaCompras(folioCartaAdhesion, idSComPer[i]);
								contEntitiesActualizadas += 1;
							}else{
								addActionError("El cultivo y ciclo agricola de la carta no corresponden a ningun registro, favor de verificar");
							}
						}		
						if(contEntitiesActualizadas > 0){
							cuadroSatisfactorio = ("Se asociaron ["+contEntitiesActualizadas+"] registros exitosamente");	
						}
					}else if (idRelacion == 2){
						for(int i = 0; i< idRegCA.length; i++){
							String [] idRegCATemp = idRegCA [i].split("-");
							idPerRel = Long.parseLong(idRegCATemp[0]);
							claveBodega = idRegCATemp[1];
							cultivoRelacion = Integer.parseInt(idRegCATemp[2]);
							idPgrCiclo = Integer.parseInt(idRegCATemp[3]);
							if(rDAO.registraFolioCartaTerrestre(folioCartaAdhesion, idComprador, idPerRel, cultivoRelacion, idPgrCiclo, claveBodega) > 0){
								contEntitiesActualizadas += 1;
							}		
						}						
						if(contEntitiesActualizadas == 0){
							addActionError("El cultivo y ciclo agricola de la carta no corresponden a ningun registro, favor de verificar");
						}else{
							cuadroSatisfactorio = ("Se asociaron ["+contEntitiesActualizadas+"] registros exitosamente");	
						} //End Relacion terrestre					
					}else if (idRelacion == 3){
						for(int i = 0; i< idRegCA.length; i++){
							String [] idRegCATemp = idRegCA [i].split("-");
							idPerRel = Long.parseLong(idRegCATemp[0]);
							claveBodega = idRegCATemp[1];
							cultivoRelacion = Integer.parseInt(idRegCATemp[2]);
							idPgrCiclo = Integer.parseInt(idRegCATemp[3]);
							nombreBarco = idRegCATemp[4];
							lugarDestino = idRegCATemp[5];
							if(rDAO.registraFolioCartaMaritima(folioCartaAdhesion, idComprador, idPerRel, cultivoRelacion, idPgrCiclo, claveBodega, nombreBarco, lugarDestino) > 0){
								contEntitiesActualizadas += 1;
							}		
						}						
						if(contEntitiesActualizadas == 0){
							addActionError("El cultivo y ciclo agricola de la carta no corresponden a ningun registro, favor de verificar");
						}else{
							cuadroSatisfactorio = ("Se asociaron ["+contEntitiesActualizadas+"] registros exitosamente");	
						}//End Relacion Maritimo
					}else if(idRelacion == 4){
						for(int i = 0; i< idRegCA.length; i++){
							String [] idRegCATemp = idRegCA [i].split("-");
							idPerRel = Long.parseLong(idRegCATemp[0]);
							claveBodega = idRegCATemp[1];
							razonSocialAlmacen = Integer.parseInt(idRegCATemp[2]);
							cultivoRelacion = Integer.parseInt(idRegCATemp[3]);
							idPgrCiclo = Integer.parseInt(idRegCATemp[4]);
							if(rDAO.registraFolioCartaCertificados(folioCartaAdhesion, idComprador, idPerRel, claveBodega, cultivoRelacion, idPgrCiclo, razonSocialAlmacen) > 0){
								contEntitiesActualizadas += 1;
							}		
						}
						
						if(contEntitiesActualizadas == 0){
							addActionError("El cultivo y ciclo agricola de la carta no corresponden a ningun registro, favor de verificar");
						}else{
							cuadroSatisfactorio = ("Se asociaron ["+contEntitiesActualizadas+"] registros exitosamente");	
						} //End Certificado de deposito
					}else if(idRelacion == 5){
						for(int i = 0; i< idRegCA.length; i++){
							String [] idRegCATemp = idRegCA [i].split("-");
							idPerRel = Long.parseLong(idRegCATemp[0]);
							cultivoRelacion = Integer.parseInt(idRegCATemp[1]);
							idPgrCiclo = Integer.parseInt(idRegCATemp[2]);
							if(rDAO.registraFolioCartaVentas(folioCartaAdhesion,  idComprador,  idPerRel, cultivoRelacion,  idPgrCiclo) > 0){
								contEntitiesActualizadas += 1;
							}		
						}
						if(contEntitiesActualizadas == 0){
							addActionError("El cultivo y ciclo agricola de la carta no corresponden a ningun registro, favor de verificar");
						}else{
							cuadroSatisfactorio = ("Se asociaron ["+contEntitiesActualizadas+"] registros exitosamente");	
						}
					}//END RELACION VENTAS
				}else{
					addActionError("El cultivo y ciclo agricola de la carta no corresponden a ningun registro, favor de verificar");
				}
				
			}else{
				addActionError("La carta de adhesion no existe o no corresponde al participante, favor de verificar");
			}
			//}
		}catch(JDBCException e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCartaAdhesion  debido a: "+e.getCause());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		} catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraCartaAdhesion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			listRelacionesCapturadas();
		}
		return SUCCESS;
	}
	
	public String cambiaRfcFacturas(){
		return SUCCESS;
	}
	
	public String validaTipoDocPago(){
		si = true;
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public List<Ciclo> getLstCiclos() {
		return lstCiclos;
	}

	public void setLstCiclos(List<Ciclo> lstCiclos) {
		this.lstCiclos = lstCiclos;
	}
	
	public List<Ejercicios> getLstEjercicios() {
		return lstEjercicios;
	}
	
	public void setLstEjercicios(List<Ejercicios> lstEjercicios) {
		this.lstEjercicios = lstEjercicios;
	}
	
	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}

	public List<Relaciones> getLstRelaciones() {
		return lstRelaciones;
	}

	public void setLstRelaciones(List<Relaciones> lstRelaciones) {
		this.lstRelaciones = lstRelaciones;
	}

	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	public int getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(int idCiclo) {
		this.idCiclo = idCiclo;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public int getIdRelacion() {
		return idRelacion;
	}

	public void setIdRelacion(int idRelacion) {
		this.idRelacion = idRelacion;
	}

	public Integer[] getGrupoVisible() {
		return grupoVisible;
	}

	public void setGrupoVisible(Integer[] grupoVisible) {
		this.grupoVisible = grupoVisible;
	}

	public Map<Long, String> getDescripcionDet() {
		return descripcionDet;
	}

	public void setDescripcionDet(Map<Long, String> descripcionDet) {
		this.descripcionDet = descripcionDet;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public boolean isBandConfiguracionYaRegistrada() {
		return bandConfiguracionYaRegistrada;
	}

	public void setBandConfiguracionYaRegistrada(
			boolean bandConfiguracionYaRegistrada) {
		this.bandConfiguracionYaRegistrada = bandConfiguracionYaRegistrada;
	}
	
	public List<PersonalizacionRelacionesV> getLstPersonalizacionRelV() {
		return lstPersonalizacionRelV;
	}

	public void setLstPersonalizacionRelV(List<PersonalizacionRelacionesV> lstPersonalizacionRelV) {
		this.lstPersonalizacionRelV = lstPersonalizacionRelV;
	}

	public int getNumCiclos() {
		return numCiclos;
	}

	public void setNumCiclos(int numCiclos) {
		this.numCiclos = numCiclos;
	}

	public List<CicloPrograma> getLstCiclosProgramas() {
		return lstCiclosProgramas;
	}

	public void setLstCiclosProgramas(List<CicloPrograma> lstCiclosProgramas) {
		this.lstCiclosProgramas = lstCiclosProgramas;
	}

	public String getSelecCiclo() {
		return selecCiclo;
	}

	public void setSelecCiclo(String selecCiclo) {
		this.selecCiclo = selecCiclo;
	}

	public String getSelecAnio() {
		return selecAnio;
	}

	public void setSelecAnio(String selecAnio) {
		this.selecAnio = selecAnio;
	}

	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}

	public List<CompradoresV> getLstCompradoresV() {
		return lstCompradoresV;
	}

	public void setLstCompradoresV(List<CompradoresV> lstCompradoresV) {
		this.lstCompradoresV = lstCompradoresV;
	}

	public List<Bancos> getLstBancos() {
		return lstBancos;
	}

	public void setLstBancos(List<Bancos> lstBancos) {
		this.lstBancos = lstBancos;
	}

	public List<TipoDocumentoPago> getLstTipoDocumentoPagos() {
		return lstTipoDocumentoPagos;
	}

	public void setLstTipoDocumentoPagos(List<TipoDocumentoPago> lstTipoDocumentoPagos) {
		this.lstTipoDocumentoPagos = lstTipoDocumentoPagos;
	}

	public long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(long idPerRel) {
		this.idPerRel = idPerRel;
	}

	public long getIdRelCompEnc() {
		return idRelCompEnc;
	}

	public void setIdRelCompEnc(long idRelCompEnc) {
		this.idRelCompEnc = idRelCompEnc;
	}

	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getCicloAgricola() {
		return cicloAgricola;
	}

	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}
	
	public List<GruposCamposRelacionTerrestreV> getLstRelacionesTerrestre() {
		return lstRelacionesTerrestre;
	}

	public void setLstRelacionesTerrestre(
			List<GruposCamposRelacionTerrestreV> lstRelacionesTerrestre) {
		this.lstRelacionesTerrestre = lstRelacionesTerrestre;
	}

	public List<GruposCamposRelacionMaritimaV> getLstRelacionesMaritima() {
		return lstRelacionesMaritima;
	}

	public void setLstRelacionesMaritima(
			List<GruposCamposRelacionMaritimaV> lstRelacionesMaritima) {
		this.lstRelacionesMaritima = lstRelacionesMaritima;
	}

	public List<GruposCamposRelacionCertificadosV> getLstRelacionesCertificados() {
		return lstRelacionesCertificados;
	}

	public void setLstRelacionesCertificados(
			List<GruposCamposRelacionCertificadosV> lstRelacionesCertificados) {
		this.lstRelacionesCertificados = lstRelacionesCertificados;
	}

	public List<GruposCamposRelacionVentasV> getLstRelacionesVentas() {
		return lstRelacionesVentas;
	}

	public void setLstRelacionesVentas(
			List<GruposCamposRelacionVentasV> lstRelacionesVentas) {
		this.lstRelacionesVentas = lstRelacionesVentas;
	}

	public BigInteger getFolioSerial() {
		return folioSerial;
	}

	public void setFolioSerial(BigInteger folioSerial) {
		this.folioSerial = folioSerial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getClaveBodega() {
		return claveBodega;
	}

	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega.toUpperCase().trim();
	}
	
	public Integer getEstadoBodega() {
		return estadoBodega;
	}

	public void setEstadoBodega(Integer estadoBodega) {
		this.estadoBodega = estadoBodega;
	}

	public int getErrorClaveBodega() {
		return errorClaveBodega;
	}

	public void setErrorClaveBodega(int errorClaveBodega) {
		this.errorClaveBodega = errorClaveBodega;
	}
	
	public Integer  getExactitud() {
		return exactitud;
	}
	
	public void setExactitud(Integer  exactitud) {
		this.exactitud = exactitud;
	}
	
	public Integer getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}

	public List<GruposCamposRelacionV> getLstGpoCampoTerrestreRelEncabezadoV() {
		return lstGpoCampoTerrestreRelEncabezadoV;
	}

	public void setLstGpoCampoTerrestreRelEncabezadoV(
			List<GruposCamposRelacionV> lstGpoCampoTerrestreRelEncabezadoV) {
		this.lstGpoCampoTerrestreRelEncabezadoV = lstGpoCampoTerrestreRelEncabezadoV;
	}

	public List<GruposCamposRelacionTerrestreV> getLstGpoCampoTerrestreRelDetalleV() {
		return lstGpoCampoTerrestreRelDetalleV;
	}

	public void setLstGpoCampoTerrestreRelDetalleV(
			List<GruposCamposRelacionTerrestreV> lstGpoCampoTerrestreRelDetalleV) {
		this.lstGpoCampoTerrestreRelDetalleV = lstGpoCampoTerrestreRelDetalleV;
	}
	
	public List<GruposCamposRelacionV> getLstGpoCampoMaritimaRelEncabezadoV() {
		return lstGpoCampoMaritimaRelEncabezadoV;
	}

	public void setLstGpoCampoMaritimaRelEncabezadoV(
			List<GruposCamposRelacionV> lstGpoCampoMaritimaRelEncabezadoV) {
		this.lstGpoCampoMaritimaRelEncabezadoV = lstGpoCampoMaritimaRelEncabezadoV;
	}

	public List<GruposCamposRelacionMaritimaV> getLstGpoCampoMaritimaRelDetalleV() {
		return lstGpoCampoMaritimaRelDetalleV;
	}

	public void setLstGpoCampoMaritimaRelDetalleV(
			List<GruposCamposRelacionMaritimaV> lstGpoCampoMaritimaRelDetalleV) {
		this.lstGpoCampoMaritimaRelDetalleV = lstGpoCampoMaritimaRelDetalleV;
	}	
	public List<GruposCamposRelacionV> getLstGpoCampoCertificadosRelEncabezadoV() {
		return lstGpoCampoCertificadosRelEncabezadoV;
	}

	public void setLstGpoCampoCertificadosRelEncabezadoV(
			List<GruposCamposRelacionV> lstGpoCampoCertificadosRelEncabezadoV) {
		this.lstGpoCampoCertificadosRelEncabezadoV = lstGpoCampoCertificadosRelEncabezadoV;
	}

	public List<GruposCamposRelacionCertificadosV> getLstGpoCampoCertificadosRelDetalleV() {
		return lstGpoCampoCertificadosRelDetalleV;
	}

	public void setLstGpoCampoCertificadosRelDetalleV(
			List<GruposCamposRelacionCertificadosV> lstGpoCampoCertificadosRelDetalleV) {
		this.lstGpoCampoCertificadosRelDetalleV = lstGpoCampoCertificadosRelDetalleV;
	}
	
	public List<GruposCamposRelacionVentasV> getLstGpoCampoVentasRelDetalleV() {
		return lstGpoCampoVentasRelDetalleV;
	}

	public void setLstGpoCampoVentasRelDetalleV(
			List<GruposCamposRelacionVentasV> lstGpoCampoVentasRelDetalleV) {
		this.lstGpoCampoVentasRelDetalleV = lstGpoCampoVentasRelDetalleV;
	}

	public List<ProgramaRelacionCultivosV> getLstCultivoPerRel() {
		return lstCultivoPerRel;
	}

	public void setLstCultivoPerRel(List<ProgramaRelacionCultivosV> lstCultivoPerRel) {
		this.lstCultivoPerRel = lstCultivoPerRel;
	}

	public List<ProgramaRelacionCiclosV> getLstCicloPerRel() {
		return lstCicloPerRel;
	}

	public void setLstCicloPerRel(List<ProgramaRelacionCiclosV> lstCicloPerRel) {
		this.lstCicloPerRel = lstCicloPerRel;
	}

	public List<ProgramaRelacionCiclosV> getLstEjercicioPerRel() {
		return lstEjercicioPerRel;
	}

	public void setLstEjercicioPerRel(List<ProgramaRelacionCiclosV> lstEjercicioPerRel) {
		this.lstEjercicioPerRel = lstEjercicioPerRel;
	}

	public List<AlmacenGeneralDeposito> getLstAlmacenes() {
		return lstAlmacenes;
	}

	public void setLstAlmacenes(List<AlmacenGeneralDeposito> lstAlmacenes) {
		this.lstAlmacenes = lstAlmacenes;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}

	public Integer getCultivoRelacion() {
		return cultivoRelacion;
	}

	public void setCultivoRelacion(Integer cultivoRelacion) {
		this.cultivoRelacion = cultivoRelacion;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getNombreCultivos() {
		return nombreCultivos;
	}
	
	public void setNombreCultivos(String nombreCultivos) {
		this.nombreCultivos = nombreCultivos;
	}

	public String getNombreBarco() {
		return nombreBarco;
	}

	public void setNombreBarco(String nombreBarco) {
		this.nombreBarco = nombreBarco.toUpperCase().trim();
	}

	public String getLugarDestino() {
		return lugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino.toUpperCase().trim();
	}

	public String getDomicilioBodega() {
		return domicilioBodega;
	}

	public void setDomicilioBodega(String domicilioBodega) {
		this.domicilioBodega = domicilioBodega;
	}

	public Integer getRazonSocialAlmacen() {
		return razonSocialAlmacen;
	}

	public void setRazonSocialAlmacen(Integer razonSocialAlmacen) {
		this.razonSocialAlmacen = razonSocialAlmacen;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCuadroSatisfactorio() {
		return cuadroSatisfactorio;
	}

	public void setCuadroSatisfactorio(String cuadroSatisfactorio) {
		this.cuadroSatisfactorio = cuadroSatisfactorio;
	}
	
	public int getNumPredios() {
		return numPredios;
	}

	public void setNumPredios(Integer numPredios) {
		this.numPredios = numPredios;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPartV() {
		return lstGruposCamposRelacionPartV;
	}

	public void setLstGruposCamposRelacionPartV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPartV) {
		this.lstGruposCamposRelacionPartV = lstGruposCamposRelacionPartV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionProdV() {
		return lstGruposCamposRelacionProdV;
	}

	public void setLstGruposCamposRelacionProdV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionProdV) {
		this.lstGruposCamposRelacionProdV = lstGruposCamposRelacionProdV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionBodV() {
		return lstGruposCamposRelacionBodV;
	}

	public void setLstGruposCamposRelacionBodV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionBodV) {
		this.lstGruposCamposRelacionBodV = lstGruposCamposRelacionBodV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalV() {
		return lstGruposCamposRelacionVentaGlobalV;
	}

	public void setLstGruposCamposRelacionVentaGlobalV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalV) {
		this.lstGruposCamposRelacionVentaGlobalV = lstGruposCamposRelacionVentaGlobalV;
	}
/*
	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaV() {
		return lstGruposCamposRelacionVentaV;
	}

	public void setLstGruposCamposRelacionVentaV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaV) {
		this.lstGruposCamposRelacionVentaV = lstGruposCamposRelacionVentaV;
	}*/

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionCompraVentaV() {
		return lstGruposCamposRelacionCompraVentaV;
	}

	public void setLstGruposCamposRelacionCompraVentaV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionCompraVentaV) {
		this.lstGruposCamposRelacionCompraVentaV = lstGruposCamposRelacionCompraVentaV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoAXCV() {
		return lstGruposCamposRelacionPagoAXCV;
	}

	public void setLstGruposCamposRelacionPagoAXCV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCV) {
		this.lstGruposCamposRelacionPagoAXCV = lstGruposCamposRelacionPagoAXCV;
	}

	public int getNumBolTicket() {
		return numBolTicket; 
	}

	public void setNumBolTicket(int numBolTicket) {
		this.numBolTicket = numBolTicket;
	}

	public Integer getNumFactGlobal() {
		return numFactGlobal;
	}

	public void setNumFactGlobal(Integer numFactGlobal) {
		this.numFactGlobal = numFactGlobal;
	}

	public int getNumFacVenta() {
		return numFacVenta;
	}

	public void setNumFacVenta(int numFacVenta) {
		this.numFacVenta = numFacVenta;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionV() {
		return lstGruposCamposRelacionV;
	}

	public void setLstGruposCamposRelacionV(List<GruposCamposRelacionV> lstGruposCamposRelacionV) {
		this.lstGruposCamposRelacionV = lstGruposCamposRelacionV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionNumProdV() {
		return lstGruposCamposRelacionNumProdV;
	}

	public void setLstGruposCamposRelacionNumProdV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionNumProdV) {
		this.lstGruposCamposRelacionNumProdV = lstGruposCamposRelacionNumProdV;
	}

	public Integer getNumContratos() {
		return numContratos;
	}

	public void setNumContratos(Integer numContratos) {
		this.numContratos = numContratos;
	}

	public Integer getNumPagosAXC() {
		return numPagosAXC;
	}

	public void setNumPagosAXC(Integer numPagosAXC) {
		this.numPagosAXC = numPagosAXC;
	}

	public int getNumPagosSinAXC() {
		return numPagosSinAXC;
	}

	public void setNumPagosSinAXC(int numPagosSinAXC) {
		this.numPagosSinAXC = numPagosSinAXC;
	}

	public List<ComprasDatosParticipante> getLstComprasDatosParticipante() {
		return lstComprasDatosParticipante;
	}

	public void setLstComprasDatosParticipante(
			List<ComprasDatosParticipante> lstComprasDatosParticipante) {
		this.lstComprasDatosParticipante = lstComprasDatosParticipante;
	}

	public long getIdCompPer() {
		return idCompPer;
	}

	public void setIdCompPer(long idCompPer) {
		this.idCompPer = idCompPer;
	}

	public String getFolioProductor() {
		return folioProductor;
	}

	public void setFolioProductor(String folioProductor) {
		this.folioProductor = folioProductor;
	}

	public Integer getNumProdBen() {
		return numProdBen;
	}

	public void setNumProdBen(Integer numProdBen) {
		this.numProdBen = numProdBen;
	}

	public List<ComprasDatosProductor> getLstComprasDatosProductor() {
		return lstComprasDatosProductor;
	}

	public void setLstComprasDatosProductor(List<ComprasDatosProductor> lstComprasDatosProductor) {
		this.lstComprasDatosProductor = lstComprasDatosProductor;
	}

	
	public String getFolioPredio() {
		return folioPredio;
	}

	public void setFolioPredio(String folioPredio) {
		this.folioPredio = folioPredio;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPrediosV() {
		return lstGruposCamposRelacionPrediosV;
	}

	public void setLstGruposCamposRelacionPrediosV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPrediosV) {
		this.lstGruposCamposRelacionPrediosV = lstGruposCamposRelacionPrediosV;
	}
	
	public List<IniEsquemaRelacion> getLstIniEsquemaRelacion() {
		return lstIniEsquemaRelacion;
	}
	public void setLstIniEsquemaRelacion(List<IniEsquemaRelacion> lstIniEsquemaRelacion) {
		this.lstIniEsquemaRelacion = lstIniEsquemaRelacion;
	}
	
	public String[] getCapPredio() {
		return capPredio;
	}
	public void setCapPredio(String[] capPredio) {
		this.capPredio = capPredio;
	}

	public long getIdCompProd() {
		return idCompProd;
	}
	public void setIdCompProd(long idCompProd) {
		this.idCompProd = idCompProd;
	}
	
	
	public String[] getCapBolTicket() {
		return capBolTicket;
	}

	public void setCapBolTicket(String[] capBolTicket) {
		this.capBolTicket = capBolTicket;
	}

	public Date[] getCapFechaEntrada() {
		return capFechaEntrada;
	}

	public void setCapFechaEntrada(Date[] capFechaEntrada) {
		this.capFechaEntrada = capFechaEntrada;
	}

	public Double[] getCapVolBolTicket() {
		return capVolBolTicket;
	}

	public void setCapVolBolTicket(Double[] capVolBolTicket) {
		this.capVolBolTicket = capVolBolTicket;
	}

	public Date[] getCapFechaEmisionFacGlobal() {
		return capFechaEmisionFacGlobal;
	}

	public void setCapFechaEmisionFacGlobal(Date[] capFechaEmisionFacGlobal) {
		this.capFechaEmisionFacGlobal = capFechaEmisionFacGlobal;
	}

	public String[] getCapFolioFacGlobal() {
		return capFolioFacGlobal;
	}

	public void setCapFolioFacGlobal(String[] capFolioFacGlobal) {
		this.capFolioFacGlobal = capFolioFacGlobal;
	}

	public Double[] getCapImpFacGlobal() {
		return capImpFacGlobal;
	}

	public void setCapImpFacGlobal(Double[] capImpFacGlobal) {
		this.capImpFacGlobal = capImpFacGlobal;
	}

	public String[] getCapNombrePerFac() {
		return capNombrePerFac;
	}

	public void setCapNombrePerFac(String[] capNombrePerFac) {
		this.capNombrePerFac = capNombrePerFac;
	}

	public String[] getCapRfcFacGlobal() {
		return capRfcFacGlobal;
	}

	public void setCapRfcFacGlobal(String[] capRfcFacGlobal) {
		this.capRfcFacGlobal = capRfcFacGlobal;
	}

	public Double[] getCapVolFacGlobal() {
		return capVolFacGlobal;
	}

	public void setCapVolFacGlobal(Double[] capVolFacGlobal) {
		this.capVolFacGlobal = capVolFacGlobal;
	}

	public Date[] getCapFechaEmisionFac() {
		return capFechaEmisionFac;
	}

	public void setCapFechaEmisionFac(Date[] capFechaEmisionFac) {
		this.capFechaEmisionFac = capFechaEmisionFac;
	}

	public String[] getCapFolioFac() {
		return capFolioFac;
	}

	public void setCapFolioFac(String[] capFolioFac) {
		this.capFolioFac = capFolioFac;
	}

	public Double[] getCapImpSolFac() {
		return capImpSolFac;
	}

	public void setCapImpSolFac(Double[] capImpSolFac) {
		this.capImpSolFac = capImpSolFac;
	}

	public String[] getCapRfcFac() {
		return capRfcFac;
	}

	public void setCapRfcFac(String[] capRfcFac) {
		this.capRfcFac = capRfcFac;
	}

	public Double[] getCapVolSolFac() {
		return capVolSolFac;
	}

	public void setCapVolSolFac(Double[] capVolSolFac) {
		this.capVolSolFac = capVolSolFac;
	}

	public Double[] getCapVolTotalFac() {
		return capVolTotalFac;
	}

	public void setCapVolTotalFac(Double[] capVolTotalFac) {
		this.capVolTotalFac = capVolTotalFac;
	}

	public String[] getCapFolioContrato() {
		return capFolioContrato;
	}

	public void setCapFolioContrato(String[] capFolioContrato) {
		this.capFolioContrato = capFolioContrato;
	}

	public Double[] getCapImpPactado() {
		return capImpPactado;
	}

	public void setCapImpPactado(Double[] capImpPactado) {
		this.capImpPactado = capImpPactado;
	}

	public Double[] getCapImpCompTon() {
		return capImpCompTon;
	}

	public void setCapImpCompTon(Double[] capImpCompTon) {
		this.capImpCompTon = capImpCompTon;
	}

	public Double[] getCapImpCompTonProd() {
		return capImpCompTonProd;
	}

	public void setCapImpCompTonProd(Double[] capImpCompTonProd) {
		this.capImpCompTonProd = capImpCompTonProd;
	}

	public Double[] getCapImpPacAxc() {
		return capImpPacAxc;
	}

	public void setCapImpPacAxc(Double[] capImpPacAxc) {
		this.capImpPacAxc = capImpPacAxc;
	}

	public Double[] getCapImpPacAxcProd() {
		return capImpPacAxcProd;
	}

	public void setCapImpPacAxcProd(Double[] capImpPacAxcProd) {
		this.capImpPacAxcProd = capImpPacAxcProd;
	}

	public Integer[] getCapBancoIdSin() {
		return capBancoIdSin;
	}

	public void setCapBancoIdSin(Integer[] capBancoIdSin) {
		this.capBancoIdSin = capBancoIdSin;
	}

	public Date[] getCapFechaDocPago() {
		return capFechaDocPago;
	}

	public void setCapFechaDocPago(Date[] capFechaDocPago) {
		this.capFechaDocPago = capFechaDocPago;
	}

	public String[] getCapFolioDocPago() {
		return capFolioDocPago;
	}

	public void setCapFolioDocPago(String[] capFolioDocPago) {
		this.capFolioDocPago = capFolioDocPago;
	}

	public Integer[] getCapIdTipoDocPago() {
		return capIdTipoDocPago;
	}

	public void setCapIdTipoDocPago(Integer[] capIdTipoDocPago) {
		this.capIdTipoDocPago = capIdTipoDocPago;
	}

	public Double[] getCapImpDocPago() {
		return capImpDocPago;
	}

	public void setCapImpDocPago(Double[] capImpDocPago) {
		this.capImpDocPago = capImpDocPago;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionBoletasV() {
		return lstGruposCamposRelacionBoletasV;
	}

	public void setLstGruposCamposRelacionBoletasV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionBoletasV) {
		this.lstGruposCamposRelacionBoletasV = lstGruposCamposRelacionBoletasV;
	}

	public List<ComprasEntradaBodega> getLstComprasEntradaBodBoletas() {
		return lstComprasEntradaBodBoletas;
	}

	public void setLstComprasEntradaBodBoletas(
			List<ComprasEntradaBodega> lstComprasEntradaBodBoletas) {
		this.lstComprasEntradaBodBoletas = lstComprasEntradaBodBoletas;
	}

	public long getIdPgrCiclo() {
		return idPgrCiclo;
	}

	public void setIdPgrCiclo(long idPgrCiclo) {
		this.idPgrCiclo = idPgrCiclo;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalNombreV() {
		return lstGruposCamposRelacionVentaGlobalNombreV;
	}

	public void setLstGruposCamposRelacionVentaGlobalNombreV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalNombreV) {
		this.lstGruposCamposRelacionVentaGlobalNombreV = lstGruposCamposRelacionVentaGlobalNombreV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalNumeroV() {
		return lstGruposCamposRelacionVentaGlobalNumeroV;
	}

	public void setLstGruposCamposRelacionVentaGlobalNumeroV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalNumeroV) {
		this.lstGruposCamposRelacionVentaGlobalNumeroV = lstGruposCamposRelacionVentaGlobalNumeroV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalFechaV() {
		return lstGruposCamposRelacionVentaGlobalFechaV;
	}

	public void setLstGruposCamposRelacionVentaGlobalFechaV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalFechaV) {
		this.lstGruposCamposRelacionVentaGlobalFechaV = lstGruposCamposRelacionVentaGlobalFechaV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalRfcV() {
		return lstGruposCamposRelacionVentaGlobalRfcV;
	}

	public void setLstGruposCamposRelacionVentaGlobalRfcV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalRfcV) {
		this.lstGruposCamposRelacionVentaGlobalRfcV = lstGruposCamposRelacionVentaGlobalRfcV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalImporteV() {
		return lstGruposCamposRelacionVentaGlobalImporteV;
	}

	public void setLstGruposCamposRelacionVentaGlobalImporteV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalImporteV) {
		this.lstGruposCamposRelacionVentaGlobalImporteV = lstGruposCamposRelacionVentaGlobalImporteV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaGlobalVolumenV() {
		return lstGruposCamposRelacionVentaGlobalVolumenV;
	}

	public void setLstGruposCamposRelacionVentaGlobalVolumenV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaGlobalVolumenV) {
		this.lstGruposCamposRelacionVentaGlobalVolumenV = lstGruposCamposRelacionVentaGlobalVolumenV;
	}

	public List<ComprasFacVentaGlobal> getLstComprasVentaGlobalNombre() {
		return lstComprasVentaGlobalNombre;
	}

	public void setLstComprasVentaGlobalNombre(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalNombre) {
		this.lstComprasVentaGlobalNombre = lstComprasVentaGlobalNombre;
	}

	public List<ComprasFacVentaGlobal> getLstComprasVentaGlobalNumero() {
		return lstComprasVentaGlobalNumero;
	}

	public void setLstComprasVentaGlobalNumero(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalNumero) {
		this.lstComprasVentaGlobalNumero = lstComprasVentaGlobalNumero;
	}

	public List<ComprasFacVentaGlobal> getLstComprasVentaGlobalFecha() {
		return lstComprasVentaGlobalFecha;
	}

	public void setLstComprasVentaGlobalFecha(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalFecha) {
		this.lstComprasVentaGlobalFecha = lstComprasVentaGlobalFecha;
	}

	public List<ComprasFacVentaGlobal> getLstComprasVentaGlobalRfc() {
		return lstComprasVentaGlobalRfc;
	}

	public void setLstComprasVentaGlobalRfc(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalRfc) {
		this.lstComprasVentaGlobalRfc = lstComprasVentaGlobalRfc;
	}

	public List<ComprasFacVentaGlobal> getLstComprasVentaGlobalImporte() {
		return lstComprasVentaGlobalImporte;
	}

	public void setLstComprasVentaGlobalImporte(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalImporte) {
		this.lstComprasVentaGlobalImporte = lstComprasVentaGlobalImporte;
	}

	public List<ComprasFacVentaGlobal> getLstComprasVentaGlobalVolumen() {
		return lstComprasVentaGlobalVolumen;
	}

	public void setLstComprasVentaGlobalFactura(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalVolumen) {
		this.lstComprasVentaGlobalVolumen = lstComprasVentaGlobalVolumen;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionVentaNumeroV() {
		return lstGruposCamposRelacionVentaNumeroV;
	}

	public void setLstGruposCamposRelacionVentaNumeroV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionVentaNumeroV) {
		this.lstGruposCamposRelacionVentaNumeroV = lstGruposCamposRelacionVentaNumeroV;
	}

	public List<ComprasFacVenta> getLstComprasFacVentaGrano() {
		return lstComprasFacVentaGrano;
	}

	public void setLstComprasFacVentaGrano(
			List<ComprasFacVenta> lstComprasFacVentaGrano) {
		this.lstComprasFacVentaGrano = lstComprasFacVentaGrano;
	}

	public void setLstComprasVentaGlobalVolumen(
			List<ComprasFacVentaGlobal> lstComprasVentaGlobalVolumen) {
		this.lstComprasVentaGlobalVolumen = lstComprasVentaGlobalVolumen;
	}

	public Long getFolioRegistro() {
		return folioRegistro;
	}

	public void setFolioRegistro(Long folioRegistro) {
		this.folioRegistro = folioRegistro;
	}

	public List<ComprasMaxCamposV> getLstComprasMaxCamposV() {
		return lstComprasMaxCamposV;
	}

	public void setLstComprasMaxCamposV(List<ComprasMaxCamposV> lstComprasMaxCamposV) {
		this.lstComprasMaxCamposV = lstComprasMaxCamposV;
	}

	public List<ComprasContrato> getLstComprasContratoFolio() {
		return lstComprasContratoFolio;
	}

	public void setLstComprasContratoFolio(
			List<ComprasContrato> lstComprasContratoFolio) {
		this.lstComprasContratoFolio = lstComprasContratoFolio;
	}

	public List<ComprasContrato> getLstComprasContratoImporte() {
		return lstComprasContratoImporte;
	}

	public void setLstComprasContratoImporte(
			List<ComprasContrato> lstComprasContratoImporte) {
		this.lstComprasContratoImporte = lstComprasContratoImporte;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoAXCImporteCompV() {
		return lstGruposCamposRelacionPagoAXCImporteCompV;
	}

	public void setLstGruposCamposRelacionPagoAXCImporteCompV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompV) {
		this.lstGruposCamposRelacionPagoAXCImporteCompV = lstGruposCamposRelacionPagoAXCImporteCompV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoAXCImporteCompProdV() {
		return lstGruposCamposRelacionPagoAXCImporteCompProdV;
	}

	public void setLstGruposCamposRelacionPagoAXCImporteCompProdV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompProdV) {
		this.lstGruposCamposRelacionPagoAXCImporteCompProdV = lstGruposCamposRelacionPagoAXCImporteCompProdV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoAXCImporteCompPacV() {
		return lstGruposCamposRelacionPagoAXCImporteCompPacV;
	}

	public void setLstGruposCamposRelacionPagoAXCImporteCompPacV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompPacV) {
		this.lstGruposCamposRelacionPagoAXCImporteCompPacV = lstGruposCamposRelacionPagoAXCImporteCompPacV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoAXCImporteCompPacProdV() {
		return lstGruposCamposRelacionPagoAXCImporteCompPacProdV;
	}

	public void setLstGruposCamposRelacionPagoAXCImporteCompPacProdV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoAXCImporteCompPacProdV) {
		this.lstGruposCamposRelacionPagoAXCImporteCompPacProdV = lstGruposCamposRelacionPagoAXCImporteCompPacProdV;
	}

	public List<ComprasPagoProdAxc> getLstComprasContratoImporteComp() {
		return lstComprasContratoImporteComp;
	}

	public void setLstComprasContratoImporteComp(
			List<ComprasPagoProdAxc> lstComprasContratoImporteComp) {
		this.lstComprasContratoImporteComp = lstComprasContratoImporteComp;
	}

	public List<ComprasPagoProdAxc> getLstComprasContratoImporteCompProd() {
		return lstComprasContratoImporteCompProd;
	}

	public void setLstComprasContratoImporteCompProd(
			List<ComprasPagoProdAxc> lstComprasContratoImporteCompProd) {
		this.lstComprasContratoImporteCompProd = lstComprasContratoImporteCompProd;
	}

	public List<ComprasPagoProdAxc> getLstComprasContratoImporteCompPac() {
		return lstComprasContratoImporteCompPac;
	}

	public void setLstComprasContratoImporteCompPac(
			List<ComprasPagoProdAxc> lstComprasContratoImporteCompPac) {
		this.lstComprasContratoImporteCompPac = lstComprasContratoImporteCompPac;
	}

	public List<ComprasPagoProdAxc> getLstComprasContratoImporteCompPacProd() {
		return lstComprasContratoImporteCompPacProd;
	}

	public void setLstComprasContratoImporteCompPacProd(
			List<ComprasPagoProdAxc> lstComprasContratoImporteCompPacProd) {
		this.lstComprasContratoImporteCompPacProd = lstComprasContratoImporteCompPacProd;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoSinAXCBancoIdSin() {
		return lstGruposCamposRelacionPagoSinAXCBancoIdSin;
	}

	public void setLstGruposCamposRelacionPagoSinAXCBancoIdSin(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoSinAXCBancoIdSin) {
		this.lstGruposCamposRelacionPagoSinAXCBancoIdSin = lstGruposCamposRelacionPagoSinAXCBancoIdSin;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionContratoFolioV() {
		return lstGruposCamposRelacionContratoFolioV;
	}

	public void setLstGruposCamposRelacionContratoFolioV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionContratoFolioV) {
		this.lstGruposCamposRelacionContratoFolioV = lstGruposCamposRelacionContratoFolioV;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionContratoImporteV() {
		return lstGruposCamposRelacionContratoImporteV;
	}

	public void setLstGruposCamposRelacionContratoImporteV(
			List<GruposCamposRelacionV> lstGruposCamposRelacionContratoImporteV) {
		this.lstGruposCamposRelacionContratoImporteV = lstGruposCamposRelacionContratoImporteV;
	}

	public List<IniEsquemaRelacion> getLstIniEsquemaRelacionCriterio() {
		return lstIniEsquemaRelacionCriterio;
	}

	public void setLstIniEsquemaRelacionCriterio(
			List<IniEsquemaRelacion> lstIniEsquemaRelacionCriterio) {
		this.lstIniEsquemaRelacionCriterio = lstIniEsquemaRelacionCriterio;
	}

	public List<IniEsquemaRelacionV> getLstIniEsquemaRelacionV() {
		return lstIniEsquemaRelacionV;
	}

	public void setLstIniEsquemaRelacionV(
			List<IniEsquemaRelacionV> lstIniEsquemaRelacionV) {
		this.lstIniEsquemaRelacionV = lstIniEsquemaRelacionV;
	}

	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}

	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}

	public List<ComprasBodegaTicketV> getLstBodegaTicket() {
		return lstBodegaTicket;
	}

	public void setLstBodegaTicket(
			List<ComprasBodegaTicketV> lstBodegaTicket) {
		this.lstBodegaTicket = lstBodegaTicket;
	}

	public int getErrorBodegaTicket() {
		return errorBodegaTicket;
	}

	public void setErrorBodegaTicket(int errorBodegaTicket) {
		this.errorBodegaTicket = errorBodegaTicket;
	}

	public int getErrorClaveBodegaExiste() {
		return errorClaveBodegaExiste;
	}

	public void setErrorClaveBodegaExiste(int errorClaveBodegaExiste) {
		this.errorClaveBodegaExiste = errorClaveBodegaExiste;
	}

	public String getBoletaTicket() {
		return boletaTicket;
	}

	public void setBoletaTicket(String boletaTicket) {
		this.boletaTicket = boletaTicket;
	}

	public List<Productores> getLstProductores() {
		return lstProductores;
	}

	public void setLstProductores(List<Productores> lstProductores) {
		this.lstProductores = lstProductores;
	}

	public int getErrorProductores() {
		return errorProductores;
	}

	public void setErrorProductores(int errorProductores) {
		this.errorProductores = errorProductores;
	}

	public String getNombreProductor() {
		return nombreProductor;
	}

	public void setNombreProductor(String nombreProductor) {
		this.nombreProductor = nombreProductor;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Integer[] getCapPredioSec() {
		return capPredioSec;
	}

	public void setCapPredioSec(Integer[] capPredioSec) {
		this.capPredioSec = capPredioSec;
	}

	public String getRfcR() {
		return rfcR;
	}

	public void setRfcR(String rfcR) {
		this.rfcR = rfcR;
	}

	public boolean getSi() {
		return si;
	}

	public void setSi(boolean si) {
		this.si = si;
	}

	public List<GruposCamposRelacionV> getLstGruposCamposRelacionPagoSinAXC() {
		return lstGruposCamposRelacionPagoSinAXC;
	}

	public void setLstGruposCamposRelacionPagoSinAXC(
			List<GruposCamposRelacionV> lstGruposCamposRelacionPagoSinAXC) {
		this.lstGruposCamposRelacionPagoSinAXC = lstGruposCamposRelacionPagoSinAXC;
	}

	public List<ComprasPagoProdSinAxc> getLstComprasPagoSinAXC() {
		return lstComprasPagoSinAXC;
	}

	public void setLstComprasPagoSinAXC(
			List<ComprasPagoProdSinAxc> lstComprasPagoSinAXC) {
		this.lstComprasPagoSinAXC = lstComprasPagoSinAXC;
	}

	public double getTotalVolumenBolTicket() {
		return totalVolumenBolTicket;
	}

	public void setTotalVolumenBolTicket(double totalVolumenBolTicket) {
		this.totalVolumenBolTicket = totalVolumenBolTicket;
	}

	public double getTotalFacVentaVolSolicitado() {
		return totalFacVentaVolSolicitado;
	}

	public void setTotalFacVentaVolSolicitado(double totalFacVentaVolSolicitado) {
		this.totalFacVentaVolSolicitado = totalFacVentaVolSolicitado;
	}

	public double getTotalFacVentaVolFacturado() {
		return totalFacVentaVolFacturado;
	}

	public void setTotalFacVentaVolFacturado(double totalFacVentaVolFacturado) {
		this.totalFacVentaVolFacturado = totalFacVentaVolFacturado;
	}

	public double getTotalFacVentaImpFacturas() {
		return totalFacVentaImpFacturas;
	}

	public void setTotalFacVentaImpFacturas(double totalFacVentaImpFacturas) {
		this.totalFacVentaImpFacturas = totalFacVentaImpFacturas;
	}

	public double getTotalPagoSinAXCImpDocPagos() {
		return totalPagoSinAXCImpDocPagos;
	}

	public void setTotalPagoSinAXCImpDocPagos(double totalPagoSinAXCImpDocPagos) {
		this.totalPagoSinAXCImpDocPagos = totalPagoSinAXCImpDocPagos;
	}

	public String getNombreRelacion() {
		return nombreRelacion;
	}

	public void setNombreRelacion(String nombreRelacion) {
		this.nombreRelacion = nombreRelacion;
	}

	public String getNombreEsquema() {
		return nombreEsquema;
	}

	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}

	public Double getSuperficiePredio() {
		return superficiePredio;
	}

	public void setSuperficiePredio(Double superficiePredio) {
		this.superficiePredio = superficiePredio;
	}

	public int getErrorValidacion() {
		return errorValidacion;
	}

	public void setErrorValidacion(int errorValidacion) {
		this.errorValidacion = errorValidacion;
	}

	public Integer getPredioSecuencial() {
		return predioSecuencial;
	}

	public void setPredioSecuencial(Integer predioSecuencial) {
		this.predioSecuencial = predioSecuencial;
	}
	
	public String getPredioAlterno() {
		return predioAlterno;
	}

	public void setPredioAlterno(String predioAlterno) {
		this.predioAlterno = predioAlterno;
	}

	public Double getSuperficieTotal() {
		return superficieTotal;
	}

	public void setSuperficieTotal(Double superficieTotal) {
		this.superficieTotal = superficieTotal;
	}

	public Double getSuperficieSumTotalPredio() {
		return superficieSumTotalPredio;
	}

	public void setSuperficieSumTotalPredio(Double superficieSumTotalPredio) {
		this.superficieSumTotalPredio = superficieSumTotalPredio;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ComprasPredioV> getLstComprasPredio() {
		return lstComprasPredio;
	}

	public void setLstComprasPredio(List<ComprasPredioV> lstComprasPredio) {
		this.lstComprasPredio = lstComprasPredio;
	}

	public Double getSuperficieTotalTmp() {
		return superficieTotalTmp;
	}

	public void setSuperficieTotalTmp(Double superficieTotalTmp) {
		this.superficieTotalTmp = superficieTotalTmp;
	}

	public String getFolioFac() {
		return folioFac;
	}

	public void setFolioFac(String folioFac) {
		this.folioFac = folioFac;
	}

	public int getErrorFacturaByProductor() {
		return errorFacturaByProductor;
	}

	public void setErrorFacturaByProductor(int errorFacturaByProductor) {
		this.errorFacturaByProductor = errorFacturaByProductor;
	}

	public int getValidaParBodProd() {
		return validaParBodProd;
	}

	public void setValidaParBodProd(int validaParBodProd) {
		this.validaParBodProd = validaParBodProd;
	}

	public long getFechaActualL() {
		return fechaActualL;
	}

	public void setFechaActualL(long fechaActualL) {
		this.fechaActualL = fechaActualL;
	}

	public List<ComprasDatosProductorV> getLstComprasDatosProductorV() {
		return lstComprasDatosProductorV;
	}

	public void setLstComprasDatosProductorV(
			List<ComprasDatosProductorV> lstComprasDatosProductorV) {
		this.lstComprasDatosProductorV = lstComprasDatosProductorV;
	}

	public int getExcluyePredio() {
		return excluyePredio;
	}

	public void setExcluyePredio(int excluyePredio) {
		this.excluyePredio = excluyePredio;
	}

	public int getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(int idPredio) {
		this.idPredio = idPredio;
	}

	public List<Modalidad> getLstModalidad() {
		return lstModalidad;
	}

	public void setLstModalidad(List<Modalidad> lstModalidad) {
		this.lstModalidad = lstModalidad;
	}

	

	public Integer[] getCapModalidad() {
		return capModalidad;
	}

	public void setCapModalidad(Integer[] capModalidad) {
		this.capModalidad = capModalidad;
	}

	public List<ComprasDatosParticipanteV> getLstComprasDatosParticipanteV() {
		return lstComprasDatosParticipanteV;
	}

	public void setLstComprasDatosParticipanteV(
			List<ComprasDatosParticipanteV> lstComprasDatosParticipanteV) {
		this.lstComprasDatosParticipanteV = lstComprasDatosParticipanteV;
	}

	public double getTotalesSuperficieTotal() {
		return totalesSuperficieTotal;
	}

	public void setTotalesSuperficieTotal(double totalesSuperficieTotal) {
		this.totalesSuperficieTotal = totalesSuperficieTotal;
	}

	public double getTotalesSuperficieApoyar() {
		return totalesSuperficieApoyar;
	}

	public void setTotalesSuperficieApoyar(double totalesSuperficieApoyar) {
		this.totalesSuperficieApoyar = totalesSuperficieApoyar;
	}

	public int getValidaApartado() {
		return validaApartado;
	}

	public void setValidaApartado(int validaApartado) {
		this.validaApartado = validaApartado;
	}

	public Map<String, Double> getCapSuperficiePredio() {
		return capSuperficiePredio;
	}

	public void setCapSuperficiePredio(Map<String, Double> capSuperficiePredio) {
		this.capSuperficiePredio = capSuperficiePredio;
	}

	public int getErrorNoExistenPredios() {
		return errorNoExistenPredios;
	}

	public void setErrorNoExistenPredios(int errorNoExistenPredios) {
		this.errorNoExistenPredios = errorNoExistenPredios;
	}

	public int getEstatusProductor() {
		return estatusProductor;
	}

	public void setEstatusProductor(int estatusProductor) {
		this.estatusProductor = estatusProductor;
	}

	public String getFolioCertificado() {
		return folioCertificado;
	}

	public void setFolioCertificado(String folioCertificado) {
		this.folioCertificado = folioCertificado;
	}

	public Long[] getIdSComPer() {
		return idSComPer;
	}

	public void setIdSComPer(Long[] idSComPer) {
		this.idSComPer = idSComPer;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getErrorFolioCertificado() {
		return errorFolioCertificado;
	}

	public void setErrorFolioCertificado(int errorFolioCertificado) {
		this.errorFolioCertificado = errorFolioCertificado;
	}

	public int getErrorFolioCertificadoAlmacen() {
		return errorFolioCertificadoAlmacen;
	}

	public void setErrorFolioCertificadoAlmacen(int errorFolioCertificadoAlmacen) {
		this.errorFolioCertificadoAlmacen = errorFolioCertificadoAlmacen;
	}

	public String getNombreAlmacen() {
		return nombreAlmacen;
	}

	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

	public String[] getIdRegCA() {
		return idRegCA;
	}

	public void setIdRegCA(String[] idRegCA) {
		this.idRegCA = idRegCA;
	}

	public String getFolioFacVenta() {
		return folioFacVenta;
	}

	public void setFolioFacVenta(String folioFacVenta) {
		this.folioFacVenta = folioFacVenta;
	}

	public List<GruposCamposRelacionV> getLstGpoCampoVentasRelEncabezadoV() {
		return lstGpoCampoVentasRelEncabezadoV;
	}

	public void setLstGpoCampoVentasRelEncabezadoV(
			List<GruposCamposRelacionV> lstGpoCampoVentasRelEncabezadoV) {
		this.lstGpoCampoVentasRelEncabezadoV = lstGpoCampoVentasRelEncabezadoV;
	}

	public int getErrorFolioFacVenta() {
		return errorFolioFacVenta;
	}

	public void setErrorFolioFacVenta(int errorFolioFacVenta) {
		this.errorFolioFacVenta = errorFolioFacVenta;
	}

	public int getTipoFormatoTerrestre() {
		return tipoFormatoTerrestre;
	}

	public void setTipoFormatoTerrestre(int tipoFormatoTerrestre) {
		this.tipoFormatoTerrestre = tipoFormatoTerrestre;
	}

	public String getFolioTalonMaritimo() {
		return folioTalonMaritimo;
	}

	public void setFolioTalonMaritimo(String folioTalonMaritimo) {
		this.folioTalonMaritimo = folioTalonMaritimo;
	}

	public int getErrorFolioTalonTerrestre() {
		return errorFolioTalonTerrestre;
	}

	public void setErrorFolioTalonTerrestre(int errorFolioTalonTerrestre) {
		this.errorFolioTalonTerrestre = errorFolioTalonTerrestre;
	}

	public int getErrorFolioTalonMaritimo() {
		return errorFolioTalonMaritimo;
	}

	public void setErrorFolioTalonMaritimo(int errorFolioTalonMaritimo) {
		this.errorFolioTalonMaritimo = errorFolioTalonMaritimo;
	}

	public int getTipoFormatoMaritimo() {
		return tipoFormatoMaritimo;
	}

	public void setTipoFormatoMaritimo(int tipoFormatoMaritimo) {
		this.tipoFormatoMaritimo = tipoFormatoMaritimo;
	}

	public String getFolioTalonTerrestre() {
		return folioTalonTerrestre;
	}

	public void setFolioTalonTerrestre(String folioTalonTerrestre) {
		this.folioTalonTerrestre = folioTalonTerrestre;
	}
	

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}		
	
}