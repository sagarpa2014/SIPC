package mx.gob.comer.sipc.reportes.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import mx.gob.comer.sipc.domain.EstatusOficio;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.OficioPagosV;
import mx.gob.comer.sipc.vistas.domain.OficiosCLCV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCartasV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCompradorV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReporteControlOficiosCLCAction extends ActionSupport implements SessionAware, Serializable {
	private Map<String, Object> session;
	private int idPrograma;
	private int idComprador;
	private Long idPago;	
	private List<Estado> lstEstados;
	private List<Programa> lstProgramas;
	private List<Comprador> lstComprador;
	private List<EstatusOficio> lstEstatusOficio;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private UtileriasDAO uDAO = new UtileriasDAO();
	private PagosDAO pDAO = new PagosDAO();
	private String oficioCLC;
	private Integer estatusId;
	private List<OficiosCLCV> lstOficiosCLCV;
	private List<ProgramaCompradorV> lstComByPrograma;
	private boolean bandera;
	private Integer folioCLC;
	private OficioPagosV oficioPagosV;
	private String msjOk;
	private int idEspecialista;
	private String noCarta;
	private List<ProgramaCartasV> lstCartas;
		
	public String busquedaOficiosCLC(){
		try {
			session = ActionContext.getContext().getSession();
			lstCartas = cDAO.consultaCarta(0, 0, null, null);
			recuperaCatalogos("P", "C", "E");
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
		try{
			session = ActionContext.getContext().getSession();
			session.put("idPrograma", idPrograma);
			session.put("idComprador", idComprador);
			session.put("noCarta", noCarta);			
			session.put("oficioCLC", oficioCLC);
			session.put("estatusId", estatusId);

			if(estatusId != null){
				if(estatusId.intValue() == -1){
					estatusId = null;
				}
			}
			
			/**recupera datos a través de los criterios de busqueda seleccionado por el usuario**/
			lstOficiosCLCV = pDAO.consultaOficioCLCV(idPrograma, idComprador, noCarta, oficioCLC, (estatusId==null?null:estatusId.toString()));
			bandera = true;
		}catch (JDBCException e) {	
			e.printStackTrace();
		}finally{
			idPrograma = -1;
			try {
				session = ActionContext.getContext().getSession();
				lstCartas = cDAO.consultaCarta(0, 0, null,null);
				recuperaCatalogos("P", "C", "E");
			} catch (JDBCException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return SUCCESS;
	}
	
	public String exportaConsultaOficiosCLC() throws IOException, WriteException{
		WritableWorkbook workbook = null;
		try {
			double totalVolumen=0, totalImporte=0;
			session = ActionContext.getContext().getSession();
			idPrograma= (Integer) session.get("idPrograma");
			idComprador = (Integer) session.get("idComprador");
			noCarta = (String)session.get("noCarta");
			oficioCLC = (String)session.get("oficioCLC");
			estatusId = (Integer) session.get("estatusId");
			
			if(estatusId != null){
				if(estatusId.intValue()==-1){
					estatusId = null;
				}
			}
			/**recupera datos a través de los criterios de busqueda seleccionado por el usuario**/
			lstOficiosCLCV = pDAO.consultaOficioCLCV(idPrograma, idComprador, noCarta, oficioCLC, (estatusId==null?null:estatusId.toString()));
			String rutaSalida = uDAO.getParametros("RUTA_PLANTILLA_REPORTES");
			String nombreArchivo = "oficiosCLC.xls";
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
			sheet.addCell(new Label(0, 0, "No. Carta", cf1)); 
			sheet.addCell(new Label(1, 0, "Comprador", cf1)); 
			sheet.addCell(new Label(2, 0, "RFC", cf1));
			sheet.addCell(new Label(3, 0, "Clabe", cf1));
			sheet.addCell(new Label(4, 0, "Volumen", cf1)); 
			sheet.addCell(new Label(5, 0, "Importe", cf1));
			sheet.addCell(new Label(6, 0, "No. Oficio", cf1));
			sheet.addCell(new Label(7, 0, "Folio CLC", cf1));
			sheet.addCell(new Label(8, 0, "Estatus", cf1));
			
			//Detalle
			int i=1;
			for(OficiosCLCV v:lstOficiosCLCV){
				int j = 0;
				sheet.addCell(new Label(j++, i, v.getNoCarta(), cf2));
				sheet.addCell(new Label(j++, i, v.getNombreComprador(), cf2));				
				sheet.addCell(new Label(j++, i, v.getRfc(), cf2));
				sheet.addCell(new Label(j++, i, v.getClabe(), cf2));
				sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoVolumenSinComas(v.getVolumen()), cf2));	
				sheet.addCell(new Label(j++, i, TextUtil.formateaNumeroComoImporteSinComas(v.getImporte()), cf2));
				sheet.addCell(new Label(j++, i, v.getNoOficio(), cf2));
				sheet.addCell(new Label(j++, i, v.getFolioCLC(), cf2));
				sheet.addCell(new Label(j++, i, v.getEstatusDesc(), cf2));
				
				totalImporte += v.getImporte();
				totalVolumen += v.getVolumen();					
				i++;
			}
			sheet.addCell(new Label(0, i, "Totales", cf1));
			sheet.addCell(new Label(4, i, TextUtil.formateaNumeroComoVolumenSinComas(totalVolumen), cf2));			
			
			sheet.addCell(new Label(5, i, TextUtil.formateaNumeroComoImporteSinComas(totalImporte), cf2));
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

	private void recuperaCatalogos(String pgrs, String compradores, String estatus) throws JDBCException, Exception {	
		if(pgrs!=null && !pgrs.equals("")){
			lstProgramas = cDAO.consultaPrograma(0);
		}
		if(compradores!=null && !compradores.equals("")){
			lstComprador = cDAO.consultaComprador(0,1);
		}
		if(estatus!=null && !estatus.equals("")){
			lstEstatusOficio = cDAO.consultaEstatusOficio();
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

	public List<Comprador> getLstComprador() {
		return lstComprador;
	}

	public void setLstComprador(List<Comprador> lstComprador) {
		this.lstComprador = lstComprador;
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
	
	public Integer getFolioCLC() {
		return folioCLC;
	}
	public void setFolioCLC(Integer folioCLC) {
		this.folioCLC = folioCLC;
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
	public String getOficioCLC() {
		return oficioCLC;
	}
	public void setOficioCLC(String oficioCLC) {
		this.oficioCLC = oficioCLC;
	}
	public List<EstatusOficio> getLstEstatusOficio() {
		return lstEstatusOficio;
	}
	public void setLstEstatusOficio(List<EstatusOficio> lstEstatusOficio) {
		this.lstEstatusOficio = lstEstatusOficio;
	}
	public List<OficiosCLCV> getLstOficiosCLCV() {
		return lstOficiosCLCV;
	}
	public void setLstOficiosCLCV(List<OficiosCLCV> lstOficiosCLCV) {
		this.lstOficiosCLCV = lstOficiosCLCV;
	}	
}
