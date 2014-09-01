package mx.gob.comer.sipc.pagos.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.EstatusPago;
import mx.gob.comer.sipc.domain.OficioPagos;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.OficioPagosV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCartasV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCompradorV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BusquedaPagosAction extends ActionSupport implements SessionAware, Serializable {
	private Map<String, Object> session;
	private int idPrograma;
	private int idComprador;
	private Long idPago;	
	private List<Estado> lstEstados;
	private List<Programa> lstProgramas;
	private List<Comprador> lstComprador;
	private List<EstatusPago> lstEstatusPago;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	private PagosDAO pDAO = new PagosDAO();
	private Date fechaInicio;
	private Date fechaFin;
	private int estatusId;
	private List<PagosV> lstPagosV;
	private List<PagosDetalleV> lstPagosDetalleV;
	private List<ProgramaCompradorV> lstComByPrograma;
	private PagosV pagosV;
	private List<OficioPagos> lstOficioPagos;
	private String oficioCGC; 
	private long idOficio;
	private Programa programa;
	
	private boolean bandera;
	private int folioCLC;
	private OficioPagosV oficioPagosV;
	private String msjOk;
	private int criterioPago;
	private int idEspecialista;
	
	private String noCarta;
	private List<ProgramaCartasV> lstCartas;
	
	private OficioPagos oficioPagos;
	private List<Pagos> lstPagosOficio;
	
	public String busquedaPagos(){
		try {
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
				lstCartas = cDAO.consultaCarta(0, 0, null, idEspecialista);
			} else {
				lstCartas = cDAO.consultaCarta(0, 0, null, null);
			}			
			recuperaCatalogos(null, "P", "C", "E");
			idPrograma = -1;
		} catch (JDBCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String recuperaCompradoresByPrograma() throws JDBCException, Exception{
		if(idPrograma==-1){
			lstComprador = cDAO.consultaComprador(0,1);
			lstCartas = cDAO.consultaCarta(0, 0, null, null);
		}else{
			lstComByPrograma = cDAO.consultaCompradorByPrograma(idPrograma);
			lstCartas = cDAO.consultaCartasByPrograma(idPrograma, 0);
		}
		
		return SUCCESS;
	}
	
	public String realizarBusqueda(){
		String fechaInicioS = "";
		String fechaFinS = "";
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil");
			if(idPerfil == 6){
				idEspecialista = (Integer) session.get("idEspecialista");
			}					
			/* Validacion de rango de fechas */
			if(fechaInicio !=null && !fechaInicio.equals("")){
				if(fechaFin != null && !fechaFin.equals("")){
					fechaInicioS = new SimpleDateFormat("yyyyMMdd").format(fechaInicio).toString();		
					fechaFinS = new SimpleDateFormat("yyyyMMdd").format(fechaFin).toString();
					if(Long.parseLong(fechaFinS)< Long.parseLong(fechaInicioS)){
						addActionError("La fecha inicio no puede ser mayor a la fecha final");
						return SUCCESS;
					}			
				     	
				}
			}
			if(fechaInicio != null && !fechaInicio.equals("")){
				fechaInicioS = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio).toString();
			}
			if(fechaFin != null && !fechaFin.equals("")){
				fechaFinS = new SimpleDateFormat("yyyy-MM-dd").format(fechaFin).toString();
			}
			session.put("idPrograma", idPrograma);
			session.put("idComprador", idComprador);
			session.put("fechaInicioS", fechaInicioS);
			session.put("fechaFinS", fechaFinS);
			session.put("estatusId", estatusId);
			session.put("idEspecialista", idEspecialista);
			session.put("noCarta", noCarta);
		
			/**recupera datos a través de los criterios de busqueda seleccionado por el usuario**/
			if (idEspecialista!=0){
				lstPagosV = pDAO.consultaPagosV(-1, idPrograma, idComprador, fechaInicioS, fechaFinS, estatusId, -1, idEspecialista, noCarta);
			} else {
				lstPagosV = pDAO.consultaPagosV(-1, idPrograma, idComprador, fechaInicioS, fechaFinS, estatusId, noCarta);
			}
			bandera = true;
		}catch (JDBCException e) {	
			e.printStackTrace();
		}finally{
			idPrograma = -1;
			try {
				session = ActionContext.getContext().getSession();
				int idPerfil = (Integer) session.get("idPerfil");
				if(idPerfil == 6){
					idEspecialista = (Integer) session.get("idEspecialista");
					lstCartas = cDAO.consultaCarta(0, 0, null, idEspecialista);
				} else {
					lstCartas = cDAO.consultaCarta(0, 0, null,null);
				}			
				recuperaCatalogos(null, "P", "C", "E");
			} catch (JDBCException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	
		return SUCCESS;
	}
	
	public String exportaConsultaPagos() throws IOException, WriteException{
		WritableWorkbook workbook = null;
		try {
			String fechaInicioS = "";
			String fechaFinS = "";
			double totalVolumen=0, totalImporte=0;
			session = ActionContext.getContext().getSession();
			idPrograma= (Integer) session.get("idPrograma");
			idComprador = (Integer) session.get("idComprador");
			fechaInicioS = (String)session.get("fechaInicioS");
			fechaFinS = (String)session.get("fechaFinS");
			estatusId = (Integer) session.get("estatusId");
			idEspecialista = (Integer) session.get("idEspecialista");
			noCarta = (String)session.get("noCarta");
			/**recupera datos a través de los criterios de busqueda seleccionado por el usuario**/
			if (idEspecialista!=0){
				lstPagosV = pDAO.consultaPagosV(-1, idPrograma, idComprador, fechaInicioS, fechaFinS, estatusId, -1, idEspecialista, noCarta);
			} else {
				lstPagosV = pDAO.consultaPagosV(-1, idPrograma, idComprador, fechaInicioS, fechaFinS, estatusId, noCarta);
			}
			programa = cDAO.consultaPrograma(idPrograma).get(0);
			//criterioPago = programa.getCriterioPago();
			String rutaSalida = uDAO.getParametros("RUTA_PLANTILLA_REPORTES");
			String nombreArchivo = "pagos.xls";
			workbook = Workbook.createWorkbook(new File(rutaSalida+nombreArchivo));
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			WritableCellFormat cf1 = new WritableCellFormat(wf1);
			cf1.setAlignment(Alignment.CENTRE);
			cf1.setWrap(true);
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
			WritableCellFormat cf2 = new WritableCellFormat(wf2);
			cf2.setAlignment(Alignment.LEFT);
			cf2.setWrap(true);
			
			//Encabezados
			sheet.addCell(new Label(0, 0, "Fecha", cf1)); 
			sheet.addCell(new Label(1, 0, "Programa", cf1)); 
			sheet.addCell(new Label(2, 0, "No. Carta", cf1));
			sheet.addCell(new Label(3, 0, "Comprador", cf1));
			sheet.addCell(new Label(4, 0, "RFC", cf1)); 
			sheet.addCell(new Label(5, 0, "Clabe", cf1));
			sheet.addCell(new Label(6, 0, "Estatus", cf1));
			sheet.addCell(new Label(7, 0, "Etapa", cf1));
			sheet.addCell(new Label(8, 0, "Volumen", cf1));
			sheet.addCell(new Label(9, 0, "Importe", cf1));
			
			//Detalle
			int i=1;
			for(PagosV v:lstPagosV){
				int j = 0;
				sheet.addCell(new Label(j++, i, new SimpleDateFormat("dd-MM-yyyy").format(v.getFechaCreacion()).toString(), cf2));
				sheet.addCell(new Label(j++, i, v.getNombrePgrCorto(), cf2));
				sheet.addCell(new Label(j++, i, v.getNoCarta(), cf2));
				sheet.addCell(new Label(j++, i, v.getNombreComprador(), cf2));
				sheet.addCell(new Label(j++, i, v.getRfc(), cf2));
				sheet.addCell(new Label(j++, i, v.getClabe(), cf2));
				sheet.addCell(new Label(j++, i, v.getEstatusPago(), cf2));
				if(v.getEtapa()!= null && v.getEtapa()!=""){
					sheet.addCell(new Label(j++, i, v.getEtapa(), cf2));
				}else{
					sheet.addCell(new Label(j++, i, "", cf2));
				}
				if(v.getVolumen()!=null && v.getVolumen()!=0){
					sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas(v.getVolumen()), cf2));	
				}else{
					sheet.addCell(new Label(j++, i, "", cf2));
				}
				sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas(v.getImporte()), cf2));
				totalImporte += v.getImporte();
				if(v.getVolumen()!=null && v.getVolumen()!=0){
					totalVolumen += v.getVolumen();	
				}
				
				i++;
			}
			sheet.addCell(new Label(0, i, "Totales", cf1));
			sheet.addCell(new Label(8, i, TextUtil.formateaNumeroComoVolumenSinComas(totalVolumen), cf2));			
			
			sheet.addCell(new Label(9, i, TextUtil.formateaNumeroComoImporteSinComas(totalImporte), cf2));
			workbook.write();
			workbook.close();
			Utilerias.entregarArchivo(rutaSalida, nombreArchivo, "xls");
			
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al exportar la información de la consulta del reporte, debido a:"+e.getCause());
		}
		finally{
			if (workbook != null){
				try {
					workbook.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
		return SUCCESS;
	}


	public String detallesPago(){
		try{
			/*Recupera los datos del pago*/
			pagosV = pDAO.consultaPagosV(idPago).get(0);
			programa = cDAO.consultaPrograma(pagosV.getIdPrograma()).get(0);
			criterioPago = programa.getCriterioPago();
			/*Recupera los datos del detalle del pago*/
			lstPagosDetalleV = pDAO.consultaPagosDetalleV(0,idPago);
			
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String consultaOficiosEnvioTesofe(){
		try{
			
			
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String realizarConOficiosEnvioTesofe(){
		try{
			//System.out.println(idOficio+oficioCGC+folioCLC);
			lstOficioPagos = pDAO.consultaOficiosPago(idOficio,oficioCGC, folioCLC);
			bandera = true;
			
			
		}catch (JDBCException e) {	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String detalleOficioPagos(){
		oficioPagosV = pDAO.consultaOficiosPagoV(idOficio,null,0).get(0);
				
		return SUCCESS;
	}
	

	private void recuperaCatalogos(String edos, String pgrs, String compradores, String estatusPago) throws JDBCException, Exception {	
		if(edos!=null && !edos.equals("")){
			lstEstados = cDAO.consultaEstado(0);
		}
		if(pgrs!=null && !pgrs.equals("")){
			lstProgramas = cDAO.consultaPrograma(0);
		}
		if(compradores!=null && !compradores.equals("")){
			lstComprador = cDAO.consultaComprador(0,1);
		}
		if(estatusPago!=null && !estatusPago.equals("")){
			lstEstatusPago = cDAO.consultaEstatusPago();
		}		
	}

	public String recuperaCartasByComprador() throws JDBCException, Exception{
		if(idPrograma==-1){
			if (idComprador==-1){
				lstCartas = cDAO.consultaCarta(0, 0, null, null);
			} else {
				lstCartas = cDAO.consultaCartasByPrograma(-1, idComprador);
			}
		}else{
			if (idComprador==-1){
				lstCartas = cDAO.consultaCartasByPrograma(idPrograma, -1);
			} else {
				lstCartas = cDAO.consultaCartasByPrograma(idPrograma, idComprador);
			}
		}
		
		return SUCCESS;
	}

	public String regresaOficioPagos() throws JDBCException, Exception{
		try {
			/**Actualizando los pagos del oficio*/
			lstPagosOficio = pDAO.consultaPagosOficio(idOficio);
			for (int i = 0; i < lstPagosOficio.size(); i++) {
				Pagos p = pDAO.consultaPagos(lstPagosOficio.get(i).getIdPago()).get(0);
				p.setEstatus(1);
				p.setIdOficio(null);
				uDAO.guardaObjeto(p);
			}		
			/**Borra Oficio de Pagos*/
			oficioPagos = pDAO.consultaOficiosPago(idOficio, null, -1).get(0);
			uDAO.borrarObjeto(oficioPagos);
			
			msjOk = "Se regresó satisfactoriamente el oficio de pagos";
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al regresar la información del oficio de pagos, debido a:"+e.getCause());
		}
		return SUCCESS;
	}


	/**
	 * Implementar la interfaz SessionAware, para session de usuario
	 */ 
	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
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

	public List<PagosV> getLstPagosV() {
		return lstPagosV;
	}
	public void setLstPagosV(List<PagosV> lstPagosV) {
		this.lstPagosV = lstPagosV;
	}
	public List<Comprador> getLstComprador() {
		return lstComprador;
	}

	public void setLstComprador(List<Comprador> lstComprador) {
		this.lstComprador = lstComprador;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getEstatusId() {
		return estatusId;
	}
	public void setEstatusId(int estatusId) {
		this.estatusId = estatusId;
	}
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}
	
	public int getFolioCLC() {
		return folioCLC;
	}
	public void setFolioCLC(int folioCLC) {
		this.folioCLC = folioCLC;
	}
	public PagosV getPagosV() {
		return pagosV;
	}
	public void setPagosV(PagosV pagosV) {
		this.pagosV = pagosV;
	}
	
	public List<PagosDetalleV> getLstPagosDetalleV() {
		return lstPagosDetalleV;
	}
	public void setLstPagosDetalleV(List<PagosDetalleV> lstPagosDetalleV) {
		this.lstPagosDetalleV = lstPagosDetalleV;
	}
	
	public List<OficioPagos> getLstOficioPagos() {
		return lstOficioPagos;
	}
	public void setLstOficioPagos(List<OficioPagos> lstOficioPagos) {
		this.lstOficioPagos = lstOficioPagos;
	}
	
	public String getOficioCGC() {
		return oficioCGC;
	}
	public void setOficioCGC(String oficioCGC) {
		this.oficioCGC = oficioCGC;
	}
	
	public OficioPagosV getOficioPagosV() {
		return oficioPagosV;
	}
	public void setOficioPagosV(OficioPagosV oficioPagosV) {
		this.oficioPagosV = oficioPagosV;
	}
	
	public String getMsjOk() {
		return msjOk;
	}
	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
	public long getIdOficio() {
		return idOficio;
	}
	public void setIdOficio(long idOficio) {
		this.idOficio = idOficio;
	}
	
	public List<EstatusPago> getLstEstatusPago() {
		return lstEstatusPago;
	}
	public void setLstEstatusPago(List<EstatusPago> lstEstatusPago) {
		this.lstEstatusPago = lstEstatusPago;
	}
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
	public List<ProgramaCompradorV> getLstComByPrograma() {
		return lstComByPrograma;
	}
	public void setLstComByPrograma(List<ProgramaCompradorV> lstComByPrograma) {
		this.lstComByPrograma = lstComByPrograma;
	}
	public int getCriterioPago() {
		return criterioPago;
	}
	public void setCriterioPago(int criterioPago) {
		this.criterioPago = criterioPago;
	}
	public int getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(int idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	public List<ProgramaCartasV> getLstCartas() {
		return lstCartas;
	}
	public void setLstCartas(List<ProgramaCartasV> lstCartas) {
		this.lstCartas = lstCartas;
	}
	public String getNoCarta() {
		return noCarta;
	}
	public void setNoCarta(String noCarta) {
		this.noCarta = noCarta;
	}
	public OficioPagos getOficioPagos() {
		return oficioPagos;
	}
	public void setOficioPagos(OficioPagos oficioPagos) {
		this.oficioPagos = oficioPagos;
	}
	public List<Pagos> getLstPagosOficio() {
		return lstPagosOficio;
	}
	public void setLstPagosOficio(List<Pagos> lstPagosOficio) {
		this.lstPagosOficio = lstPagosOficio;
	}
}
