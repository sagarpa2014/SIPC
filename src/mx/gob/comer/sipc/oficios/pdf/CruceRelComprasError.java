package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.gob.comer.sipc.action.relaciones.RelacionComprasAction;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcompras;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcomprasDetalle;
import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.CuentasBancariasV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.relaciones.*;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont; // AHS [LINEA] - 10022015
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte; // AHS [LINEA] - 10022015
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;	// AHS [LINEA] - 10022015
import com.lowagie.text.pdf.PdfWriter;

public class CruceRelComprasError extends PdfPageEventHelper {
	private final Font TIMESROMAN08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08	= FontFactory.getFont(FontFactory.COURIER, 6,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN08BOLD	= FontFactory.getFont(FontFactory.COURIER, 6,Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES, 11,Font.NORMAL, Color.BLACK);	
	private RelacionComprasAction rca;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	private RelacionesDAO rDAO;
	private List<PagosV> pagoV;
	private List<PagosDetalleV> pagoDet;
	private List<CuentasBancariasV> cuentaBancaria;
	private StringBuilder texto;
	private PdfPTable piePagina;
	
	private List<ProductorPredioValidado> lstProductorPredioValidado;
	private List<ProductorPredioPagado> lstProductorPredioPagado;
	private List<PrediosProdsContNoExisteBD> lstPrediosProdsContNoExistenBD;
	private List<RelacionComprasTMP> lstPrediosNoExistenBD;
	private List<PrediosNoValidadosDRDE> lstPrediosNoValidadosDRDE;
	private List<PrediosNoPagados> lstPrediosNoPagados;
	private List<ProductorExisteBD> lstProductorExisteBD;
	private List<ProductoresIncosistentes> lstProductoresIncosistentes;
	private List<BoletasDuplicadas> lstBoletasDuplicadas;
	private List<BoletasFueraDePeriodo> lstBoletasFueraDePeriodo;
	private List<BoletasFueraDePeriodoPago> lstBoletasFueraDePeriodoPago;
	private List<ProductorFactura> lstProductorFactura;
	private List<BoletasVsFacturas> lstBoletasVsFacturas;
	private List<RfcProductorVsRfcFactura> lstRfcProductorVsRfcFactura;
	private List<RelacionComprasTMP> lstRfcProductorVsRfcFactura2;
	private List<RelacionComprasTMP> lstRfcProductorVsRfcFacturaSinContrato;
	private List<FacturaFueraPeriodoAuditor> lstFacturaFueraPeriodoAuditor;
	private List<FacturasIgualesFacAserca> lstFacturasIgualesFacAserca;
	private List<ChequesDuplicadoBancoPartipante> lstChequesDuplicadoBancoPartipante;
	private List<ChequeFueraPeriodoAuditor> lstChequeFueraPeriodoAuditor;
	private List<ChequeFueraPeriodoContrato> listChequeFueraPeriodoContrato;
	private List<FacturasVsPago> lstFacturasVsPago;
	private List<FacturaFueraPeriodoPago> lstFacturaFueraPeriodoPago;
	private List<BoletasCamposRequeridos> lstBoletasCamposRequeridos;
	private List<FacturasCamposRequeridos> lstFacturasCamposRequeridos;
	private List<PagosCamposRequeridos> lstPagosCamposRequeridos;
	private List<GeneralToneladasTotalesPorBodFac> lstGeneralToneladasTotalesPorBodFac;
	private List<GeneralToneladasTotPorBodega> lstGeneralTonTotPorBodega;
	private List<BoletasFacturasPagosIncosistentes> lstBoletasFacturasPagosIncosistentes;
	private List<VolumenFiniquito> lstVolumenCumplido;
	private List<PrecioPagPorTipoCambio> lstPrecioPagPorTipoCambio;
	private List<RelacionComprasTMP> lstFGlobalVsFIndividual;
	private List<RelacionComprasTMP> lstCurpRfcYONombresInconsistentes;
	private List<PagMenorCompBases> lstPagMenorCompBases;
	private boolean siAplicaFolioContrato;	
	private PdfPCell cell;
	boolean aplicaAdendum;
	@SessionTarget
	Session session;	
	@TransactionTarget
	Transaction transaction;
	private PdfPTable t;
	private int columna; 
	private int contBitacoraDet; 
	private String [] configTotales;
	private double totalVolPorContrato, totalVolPorContrato1, totalVolPorContrato2, totalVolPorContrato3, totalVolPorContrato4, totalVolPorContrato5, totalVolPorContrato6,totalVolPorContrato7;   
	private double totalVolPorContratoPredioNoPag, totalVolPorContratoDifFacGlobal, totalVolPorContraTotalObs, totalVolPorContratoPredioIncons;
	private double totalVolPorBodegaPredioNoPag, totalVolPorBodegaDifFacGlobal, totalVolPorBodegaTotalObs, totalVolPorBodegaPredioIncos;
	private double granTotalVolNoPag, granTotalVolDifFacGlobal, granTotalVolObs, granTotalVolPredioIncos;
	private double totalVolPorBodega, totalVolPorBodega1, totalVolPorBodega2,totalVolPorBodega3, totalVolPorBodega4, totalVolPorBodega5,totalVolPorBodega6,totalVolPorBodega7;
	
	private double granTotalVol,granTotalVol1, granTotalVol2, granTotalVol3, granTotalVol4, granTotalVol5, granTotalVol6,granTotalVol7;
	
	private String claveBodega, nombreEstado, folioContrato, paternoProductor, maternoProductor, nombreProductor, curpProductor, rfcProductor, fcProductor;
	private String claveBodegaTmp, nomEstadoTmp,  temFCProductor, temFolioContrato, temFolioContratoAux;
	private StringBuilder totalesContrato, totalesBodega, totalesGran;
	private float[] w;
	private List<RendimientosProcedente> lstRendimientosProcedente;
	
	private List<PrecioPagadoNoCorrespondeConPagosV> lstPrecioPagadoMenorQueAviso;
	
	private PdfTemplate total; 	// AHS [LINEA] - 10022015
	private BaseFont baseFont = null;	// AHS [LINEA] - 10022015
	private double totalVolPorContrato8, totalVolPorContrato9, totalVolPorContrato10;
	private double totalVolPorBodega8, totalVolPorBodega9, totalVolPorBodega10;
	private double granTotalVol8, granTotalVol9 ;
	private List<VolumenNoProcedente> lstVolNoProcedenteYApoyEnReg;
	private String reporteAplica;
	
	
	public CruceRelComprasError(RelacionComprasAction rca, Session session) {
		this.rca = rca;
		this.session = session;
		rDAO = new RelacionesDAO(session);				
	}
	
	public void generarCruce() throws Exception{	
		reporteAplica = "";
		if(rca.getIdCriterio()==10 || rca.getIdCriterio()==12 || rca.getIdCriterio()==13 || rca.getIdCriterio()==15 || rca.getIdCriterio()==20 
				|| rca.getIdCriterio()==21 || rca.getIdCriterio() == 23 || rca.getIdCriterio() == 28 || rca.getIdCriterio() == 29){
			document = new Document(new Rectangle(792, 612));
		}else{
			document = new Document(PageSize.LETTER, 50, 50, 50, 50);
		}
		
		String rutaTemp = rca.getNombreArchivo();
		writer = PdfWriter.getInstance(document, new FileOutputStream(rca.getRutaSalida()+rutaTemp));
		writer.setPageEvent(this);
		siAplicaFolioContrato = false;
		//Verifica que en la personalización se haya configurado el campo "FOLIO CONTRATO"
		List<GruposCamposRelacionV> lstGruposCampos = rDAO.consultaGruposCampostV(rca.getIdPerRel(),10,21);
		if(lstGruposCampos.size()>0){
			siAplicaFolioContrato = true;
		}
		document.open();		
		document.newPage();
		getEncabezado();
		getLugarYFecha();
		getConfiguracionTabla();
		getDatosGeneralesCartaAdehsion(null);
		addEmptyLine(2);		
		getCuerpo();
		addEmptyLine(1);
		
		if(rca.getIdCriterio() == 21 ){
			document.newPage();
			getEncabezado();
			getLugarYFecha();
			getConfiguracionTabla();
			getDatosGeneralesCartaAdehsion("1 TONELADAS TOTALES POR CONTRATO DE BOLETAS Y FACTURAS");
			addEmptyLine(2);
			creaEncabezadoDetalle(2);
			colocaDetalleEnTabla(2);			
			document.add(t);
			addEmptyLine(1);
			
			
			addEmptyLine(1);
		}		
		
		
		document.close();			
	}

	private void getConfiguracionTabla() {
		w = configurarNumColumnaEnTabla();			
		t = new PdfPTable(w);
		t.setWidthPercentage(100);		
	}

	public  void getEncabezado() throws MalformedURLException, IOException, DocumentException {		
		PdfPTable table1 = null;	
		Paragraph enunciado = null;
		/**TABLA DE LOGOS**/	
		Image sagarpa = Image.getInstance(rca.getRutaImagen());
		sagarpa.scalePercent(50, 50);
		table1= new PdfPTable(2);
		table1.setWidthPercentage(100);
		enunciado = new Paragraph();
		enunciado.add(new Chunk("ASERCA\nCoordinación General de Comercialización\nDirección General de Desarrollo de Mercados\nDirección de Pagos de Apoyos a la Comercialización", TIMESROMAN12));
		cell =	createCell(null, 0, 2, 1, sagarpa,0);
		table1.addCell(cell);
		cell =	createCell(enunciado, 0, 3, 1, null,0);
		table1.addCell(cell);

		document.add(table1);
	}
	
	private void getLugarYFecha() throws DocumentException {
		String fechaActual = new SimpleDateFormat("dd-MMM-yyyy").format(rca.getFechaDeReporte()).toString();
		parrafo = new Paragraph("Fecha :"+fechaActual, TIMESROMAN10NORMAL);
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_RIGHT);
		document.add(parrafo);	
	}
	
	private void getDatosGeneralesCartaAdehsion(String mensaje) throws DocumentException {
		
		String msj = rca.getLstBitacoraRelCompras().get(0).getMensaje();
		if(rca.getIdCriterio() == 9){
			if(msj.contains("/")){
				msj = msj.replace("/", "");
			}
		}
		parrafo =  new Paragraph("FOLIO CARTA ADHESIÓN : "+rca.getFolioCartaAdhesion()+"\n\t", TIMESROMAN08);
		cell = createCell(parrafo, w.length, 3, 1, null,0);
		t.addCell(cell); 
		parrafo = new Paragraph("NOMBRE DEL PARTICIPANTE : "+rca.getComprador().getNombre()+" "+(rca.getComprador().getApellidoPaterno()!=null?rca.getComprador().getApellidoPaterno():"")
				+(rca.getComprador().getApellidoMaterno()!=null?rca.getComprador().getApellidoMaterno():"")+"\n\t", TIMESROMAN08);
		cell = createCell(parrafo, w.length, 3, 1, null,0);
		t.addCell(cell);
		parrafo = new Paragraph(rca.getPrograma().getDescripcionLarga()+"\n\t", TIMESROMAN08);
		cell = createCell(parrafo, w.length, 1, 1, null,0);
		t.addCell(cell);
		if(mensaje != null && !mensaje.isEmpty()){
			parrafo = new Paragraph(mensaje+"\n\t", TIMESROMAN08);
		}else{
			parrafo = new Paragraph(msj+"\n\t", TIMESROMAN08);
		}
		
		cell = createCell(parrafo, w.length, 1, 1, null,0);
		t.addCell(cell);				
	}
		
	private void getCuerpo() throws DocumentException, ParseException{	
				
		inicializarArrayList();					
		colocarInfoEnPojo() ;
		colocaDetalleEnTabla(1);			
		document.add(t);
		addEmptyLine(1);
	}
	  
	@SuppressWarnings("unchecked")
	private void colocaDetalleEnTabla(int tipo) {
		claveBodegaTmp = ""; nomEstadoTmp="";  temFCProductor =""; temFolioContrato= "" ;
		int posicionTotal = 0;
		if(rca.getIdCriterio() == 1){//VALIDA QUE EXISTA PREDIO EN BASE DE DATOS 7.4 DIFERENCIAS PREDIOS/PRODUCTOR/COMPRADOR 
			Collections.sort(lstPrediosNoExistenBD);
			for(RelacionComprasTMP p: lstPrediosNoExistenBD){				
//				if(!claveBodegaTmp.equals(p.getClaveBodega())){
//					parrafo =  new Paragraph(p.getClaveBodega(), TIMESROMAN08);
//				}else{
//					parrafo =  new Paragraph("", TIMESROMAN08);
//				}				
//				cell = new PdfPCell(parrafo);
//				cell =createCell(parrafo, 0, 1, 1);
//				t.addCell(cell);
				if(!nomEstadoTmp.equals(p.getNombreEstado())){
					parrafo =  new Paragraph(p.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				crearColumna("S-2-"+ p.getPaternoProductor()+" "+p.getMaternoProductor()+" "+p.getNombreProductor(),"DET");
//				crearColumna("S-2-"+ (p.getCurpProductor()!=null?p.getCurpProductor():p.getRfcProductor()),"DET");
//				crearColumna("S-2-"+ (p.getFolioPredioSec()!=-1 ?p.getFolioPredioSec():""),"DET");
//				crearColumna("S-2-"+ (p.getFolioPredioAlterno()!=null && p.getFolioPredioAlterno().isEmpty()?p.getFolioPredioAlterno():""),"DET");
				parrafo =  new Paragraph((p.getCurpProductor()!=null?p.getCurpProductor():p.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);	
				parrafo =  new Paragraph(p.getFolioPredio(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getBancoSinaxc(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				//claveBodegaTmp = p.getClaveBodega();
				nomEstadoTmp = p.getNombreEstado();
				
			}			
		}else if(rca.getIdCriterio() == 2){//"PREDIO SE ENCUENTRE VALIDADO POR LA DR/DE"
			Collections.sort(lstPrediosNoValidadosDRDE);
			for(PrediosNoValidadosDRDE p: lstPrediosNoValidadosDRDE){
				parrafo =  new Paragraph(p.getFolioPredio(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioSec().toString(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioAlterno(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
			}			
		}else if(rca.getIdCriterio() == 3){//"PREDIOS ESTEN PAGADOS"
			Collections.sort(lstPrediosNoPagados);
			for(PrediosNoPagados p: lstPrediosNoPagados){
				parrafo =  new Paragraph(p.getFolioPredio(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioSec().toString(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioAlterno(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);	
			}
		}else if(rca.getIdCriterio() == 26){//VALIDA QUE EXISTA PREDIO/PRODUCTOR/CONTRATO EN BASE DE DATOS
			Collections.sort(lstPrediosProdsContNoExistenBD);
			for(PrediosProdsContNoExisteBD p: lstPrediosProdsContNoExistenBD){
				if(!claveBodegaTmp.equals(p.getClaveBodega())){
					parrafo =  new Paragraph(p.getClaveBodega(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(!nomEstadoTmp.equals(p.getNombreEstado())){
					parrafo =  new Paragraph(p.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(p.getFolioContrato())){					
						parrafo =  new Paragraph(p.getFolioContrato(), TIMESROMAN08);
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}				
				parrafo =  new Paragraph(p.getPaternoProductor()+" "+p.getMaternoProductor()+" "+p.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph((p.getCurpProductor()!=null?p.getCurpProductor():p.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(p.getFolioPredio(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
//				parrafo =  new Paragraph(p.getFolioPredioSec()!=-1?p.getFolioPredioSec().toString():"", TIMESROMAN08);
//				cell = new PdfPCell(parrafo);
//				cell =createCell(parrafo, 0, 2, 1);
//				t.addCell(cell);
//				parrafo =  new Paragraph(p.getFolioPredioAlterno(), TIMESROMAN08);
//				cell = new PdfPCell(parrafo);
//				cell =createCell(parrafo, 0, 2, 1);
//				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioCartaExterna(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				claveBodegaTmp = p.getClaveBodega();
				nomEstadoTmp = p.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = p.getFolioContrato();
				}				
			}				
		}else if(rca.getIdCriterio() == 30){//"CURP, RFC Y/O NOMBRES INCONSISTENTES"
			Collections.sort(lstCurpRfcYONombresInconsistentes);
			for(RelacionComprasTMP p: lstCurpRfcYONombresInconsistentes){
				if(!claveBodegaTmp.equals(p.getClaveBodega())){
					parrafo =  new Paragraph(p.getClaveBodega(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(!nomEstadoTmp.equals(p.getNombreEstado())){
					parrafo =  new Paragraph(p.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(p.getFolioContrato())){					
						parrafo =  new Paragraph(p.getFolioContrato(), TIMESROMAN08);
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}				
				parrafo =  new Paragraph(p.getPaternoProductor()+" "+p.getMaternoProductor()+" "+p.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph((p.getCurpProductor()!=null?p.getCurpProductor():""), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph((p.getRfcProductor()!=null?p.getRfcProductor():""), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);				
				claveBodegaTmp = p.getClaveBodega();
				nomEstadoTmp = p.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = p.getFolioContrato();
				}				
			}
		}else if(rca.getIdCriterio() == 5){//"Productores Estén asociados a Predios Validados"
			Collections.sort(lstProductorPredioValidado);
			for(ProductorPredioValidado l: lstProductorPredioValidado){
				parrafo =  new Paragraph(l.getFolioProductor().toString(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getCurpProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getRfcProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);	
			}		
		}else if(rca.getIdCriterio() == 6){//PRODUCTORES ESTÉN ASOCIADOS A PREDIOS PAGADOS
			Collections.sort(lstProductorPredioPagado);
			for(ProductorPredioPagado l: lstProductorPredioPagado){
				parrafo =  new Paragraph(l.getFolioProductor().toString(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getCurpProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getRfcProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);				
			}
		}else if(rca.getIdCriterio() == 7){//EXISTA POR UNIDAD DE PRODUCCIÓN AL MENOS UN REGISTRO DE BOLETA, FACTURA, PREDIO Y PAGO			
			Collections.sort(lstProductoresIncosistentes);
			int contBitacoraDet = 1;
			for(ProductoresIncosistentes l: lstProductoresIncosistentes){
				if(contBitacoraDet != 0){							
					if(siAplicaFolioContrato){					
						if( !temFolioContrato.equals(l.getFolioContrato())&&contBitacoraDet>1){
							//colocarSumaFolioContratoEnBoletasDup(totalVolBolTicket);						
							//totalVolBolTicket = 0;						
						}
					}					
					if( !claveBodegaTmp.equals(l.getClaveBodega())&&contBitacoraDet>1){
						//colocarSumaFolioContratoEnBoletasDup(totalVolBolTicketBodega);						
						//totalVolBolTicketBodega = 0;						
					}														
				}		
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					parrafo =  new Paragraph(l.getClaveBodega(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					parrafo =  new Paragraph(l.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						parrafo =  new Paragraph(l.getFolioContrato(), TIMESROMAN08);
									
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()) , TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getFolioPredio()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
//				parrafo =  new Paragraph(l.getPredioAlterno()+"", TIMESROMAN08);
//				cell = new PdfPCell(parrafo);
//				cell =createCell(parrafo, 0, 2, 1);
//				t.addCell(cell);
				parrafo =  new Paragraph(l.getNumeroBoletas()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getNumeroFacturas()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getNumeroPagos()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);				
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				contBitacoraDet ++;			
			}						

		}else if(rca.getIdCriterio() == 8){//"BOLETAS NO ESTEN DUPLICADAS POR BODEGA"
			Collections.sort(lstBoletasDuplicadas);
			contBitacoraDet = 1;
			for(BoletasDuplicadas l: lstBoletasDuplicadas){				
/*
				configTotales = new String [2];
				configTotales[0] = "3,TOTAL;6,"+totalVolPorContrato+",v";
				configTotales[1] = "1,TOTAL;6,"+totalVolPorBodega+",v";	*/			
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(null, w.length);
//				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
//				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
//				parrafo.setLeading(1, 1);
//				cell = new PdfPCell(parrafo);
//				cell =createCell(parrafo, 0, 1, 1);
//				t.addCell(cell);
				parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getVolBolTicket() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolBolTicket()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getFechaEntradaBoleta() != null ? new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEntradaBoleta()).toString()+"":"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
//				if(siAplicaFolioContrato){					
//					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
//				}
//				crearColumna("S-1-"+l.getClaveBodega(),"DET");				
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();					
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}				
				//totalVolPorContrato += l.getVolBolTicket();
				//totalVolPorBodega += l.getVolBolTicket();
				//granTotalVol += l.getVolBolTicket();
				//contBitacoraDet ++;				
			}
			//colocarTotales("3,TOTAL;6,"+totalVolPorContrato+",v", 7);
			//colocarTotales("1,TOTAL;6,"+totalVolPorBodega+",v", 7);
			//colocarTotales("3,TOTAL;6,"+granTotalVol+",v", 7);
		}else if(rca.getIdCriterio() == 9){
			if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				Collections.sort(lstBoletasFueraDePeriodo);
				contBitacoraDet = 1;			
				for(BoletasFueraDePeriodo l: lstBoletasFueraDePeriodo){
					configTotales = new String [2];
					if(siAplicaFolioContrato){		
						configTotales[0] = "3,TOTAL CON;7,"+totalVolPorContrato+",v";
						configTotales[1] = "1,TOTAL BOD;7,"+totalVolPorBodega+",v";
					}else{
						configTotales[1] = "1,TOTAL BOD;7,"+totalVolPorBodega+",v";
					}
									
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					curpProductor = l.getCurpProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);	
					parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEntradaBoleta()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getVolBolTicket() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolBolTicket()):"", TIMESROMAN08);
					parrafo.setLeading(1, 1);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolBolTicket();
					totalVolPorBodega += l.getVolBolTicket();
					granTotalVol += l.getVolBolTicket();
					contBitacoraDet ++;
				}//end for
				if(siAplicaFolioContrato){
					colocarTotales("1,TOTAL CON;7,"+totalVolPorContrato+",v", w.length);
					colocarTotales("1,TOTAL BOD;7,"+totalVolPorBodega+",v", w.length);
					colocarTotales("1,GRAN TOTAL;7,"+granTotalVol+",v", w.length);
				}else{
					colocarTotales("1,TOTAL BOD;7,"+totalVolPorBodega+",v", w.length);
					colocarTotales("1,GRAN TOTAL;7,"+granTotalVol+",v", w.length);
				}
				
			} else {
				Collections.sort(lstBoletasFueraDePeriodoPago);
				contBitacoraDet = 1;			
				for(BoletasFueraDePeriodoPago l: lstBoletasFueraDePeriodoPago){					
					configTotales = new String [2];
					configTotales[0] = "3,TOTAL CON;9,"+totalVolPorContrato+",v";
					configTotales[1] = "3,TOTAL BOD;9,"+totalVolPorBodega+",v";
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					curpProductor = l.getCurpProductor();
					rfcProductor = l.getRfcProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoInicialPago()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoFinalPago()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEntradaBoleta()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getVolBolTicket() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolBolTicket()):"", TIMESROMAN08);
					parrafo.setLeading(1, 1);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolBolTicket();
					totalVolPorBodega += l.getVolBolTicket();
					granTotalVol += l.getVolBolTicket();			
					contBitacoraDet ++;
				}
				colocarTotales("3,TOTAL CON;10,"+totalVolPorContrato+",v", w.length);
				colocarTotales("3,TOTAL BOD;10,"+totalVolPorBodega+",v", w.length);
				colocarTotales("3,GRAN TOTAL;10,"+granTotalVol+",v", w.length);				
			}
		}else if(rca.getIdCriterio() == 10){//"NO SE DUPLIQUE EL FOLIO DE LA FACTURA"
			contBitacoraDet = 1;
			Collections.sort(lstProductorFactura);
			granTotalVol = 0;
			for (ProductorFactura l: lstProductorFactura){
				//configTotales = new String [2]; 
				//configTotales[0] = "3,TOTAL;6,"+totalVolPorContrato+",v";
				//configTotales[1] = "1,TOTAL;6,"+totalVolPorBodega+",v";						
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();	
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(null, w.length);				
//				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");	
//				crearColumna("S-2-"+ (l.getCurpProductor() != null?l.getCurpProductor():l.getRfcProductor()),"DET");	
				parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(l.getFechaEmisionFac()!=null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString():"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);	
//				if(siAplicaFolioContrato){					
//					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
//				}	
				//crearColumna("S-1-"+l.getClaveBodega(),"DET");				
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}				
				//totalVolPorContrato += l.getVolTotalFacVenta();
				//totalVolPorBodega += l.getVolTotalFacVenta();
				//granTotalVol += l.getVolTotalFacVenta();		
				contBitacoraDet ++;				
			}
//			colocarTotales("3,TOTAL;6,"+totalVolPorContrato+",v", 7);
//			colocarTotales("1,TOTAL;6,"+totalVolPorBodega+",v", 7);
			//System.out.println("gran total "+granTotalVol);
			//colocarTotales("2,TOTAL;3,"+granTotalVol+",v", 7);
		}else if(rca.getIdCriterio() == 11){//"LA SUMA TOTAL DE LA FACTURAS POR PRODUCTOR NO SEA MAYOR A LA SUMA DE LAS BOLETAS"
			Collections.sort(lstBoletasVsFacturas); 					
			contBitacoraDet = 1;
			for(BoletasVsFacturas l: lstBoletasVsFacturas){
				configTotales = new String [2];
				if(siAplicaFolioContrato){
					configTotales[0] = "3,TOTAL CON;6,"+totalVolPorContrato+",v"+";7,"+totalVolPorContrato1+",v;8,"+totalVolPorContrato2+",v";
					configTotales[1] = "3,TOTAL BOD;6,"+totalVolPorBodega+",v"+";7,"+totalVolPorBodega1+",v;8,"+totalVolPorBodega2+",v";
				}else{
					configTotales[1] = "1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v";
				}
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();	
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);				
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getVolBolTicket()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getDifVolumen()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				totalVolPorContrato += l.getVolBolTicket();
				totalVolPorContrato1+= l.getVolTotalFacVenta();
				totalVolPorContrato2+= l.getDifVolumen();
				totalVolPorBodega += l.getVolBolTicket();				
				totalVolPorBodega1 += l.getVolTotalFacVenta();					
				totalVolPorBodega2 += l.getDifVolumen();					
				granTotalVol += l.getVolBolTicket();				
				granTotalVol1 += l.getVolTotalFacVenta();				
				granTotalVol2 += l.getDifVolumen();				
				contBitacoraDet ++;				
			}	
			//if(siAplicaFolioContrato){
				posicionTotal = (siAplicaFolioContrato?5:4);
				colocarTotales("3,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",v"+";"+(++posicionTotal)+","+totalVolPorContrato1+",v;"+(++posicionTotal)+","+totalVolPorContrato2+",v", w.length);
				posicionTotal = (siAplicaFolioContrato?5:4);
				colocarTotales("3,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v"+";"+(++posicionTotal)+","+totalVolPorBodega1+",v;"+(++posicionTotal)+","+totalVolPorBodega2+",v", w.length);
				posicionTotal = (siAplicaFolioContrato?5:4);
				colocarTotales("3,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",v"+";"+(++posicionTotal)+","+granTotalVol1+",v;"+(++posicionTotal)+","+granTotalVol2+",v", w.length);
			//}else{
//				colocarTotales("1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v", w.length);
//				colocarTotales("1,GRAN TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",v;7,"+granTotalVol2+",v", w.length);
//			}
				
		}else if(rca.getIdCriterio() == 12){//"10 DIFERENCIA DE RFC DE PRODUCTOR RELACION DE COMPRAS CON LO REGISTRADO EN EL CONTRATO"
			Collections.sort(lstRfcProductorVsRfcFactura);			
			for (RfcProductorVsRfcFactura l: lstRfcProductorVsRfcFactura){					
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){					
						if(!temFolioContrato.equals(folioContrato) && contBitacoraDet > 1){
							posicionTotal = (siAplicaFolioContrato?9:8);
							totalesContrato = new StringBuilder();
							totalesContrato.append("1,TOTAL CON;"+(siAplicaFolioContrato?(++posicionTotal)+","+totalVolPorContrato+",v;":""));
							colocarTotales(totalesContrato.toString(),  w.length);
							totalVolPorContrato = 0;
						}
					}
				}
				if(!claveBodegaTmp.equals(claveBodega) && contBitacoraDet > 1){
					if(siAplicaFolioContrato && temFolioContrato.equals(folioContrato)){
						posicionTotal = (siAplicaFolioContrato?9:8);
						totalesContrato = new StringBuilder();
						totalesContrato.append("1,TOTAL CON;"+(siAplicaFolioContrato?(++posicionTotal)+","+totalVolPorContrato+",v;":""));
						colocarTotales(totalesContrato.toString(),  w.length);
					}	
					posicionTotal = (siAplicaFolioContrato?9:8);
					totalesBodega = new StringBuilder();
					totalesBodega.append("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v;");
					colocarTotales(totalesBodega.toString(),  w.length);
					totalVolPorBodega = 0;	
					if(siAplicaFolioContrato){
						totalVolPorContrato = 0;
					}
				}
				
					
				if(!claveBodegaTmp.equals(claveBodega)){
					parrafo =  new Paragraph(claveBodega, TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);							
				if(!nomEstadoTmp.equals(nombreEstado)){
					parrafo =  new Paragraph(nombreEstado, TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(folioContrato)){					
						parrafo =  new Paragraph(folioContrato, TIMESROMAN08);			
					}else if(!claveBodegaTmp.equals(claveBodega)){
						parrafo =  new Paragraph(folioContrato, TIMESROMAN08);								
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
				parrafo =  new Paragraph(paternoProductor+" "+maternoProductor+" "+nombreProductor, TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);		
						
				parrafo =  new Paragraph(l.getCurpProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getCurpProductorBD(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getRfcProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getRfcProductorBD(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getLeyenda(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				totalVolPorContrato += l.getVolTotalFacVenta();
				totalVolPorBodega += l.getVolTotalFacVenta();
				granTotalVol += l.getVolTotalFacVenta();
				contBitacoraDet ++;
			}	//end for
			posicionTotal = (siAplicaFolioContrato?9:8);
			if(siAplicaFolioContrato){
				colocarTotales("1,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",v", w.length);
				posicionTotal = (siAplicaFolioContrato?9:8);
				colocarTotales("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v", w.length);
				posicionTotal = (siAplicaFolioContrato?9:8);
				colocarTotales("1,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",v", w.length);
			}else{
				posicionTotal = (siAplicaFolioContrato?8:7);
				colocarTotales("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v", w.length);
				posicionTotal = (siAplicaFolioContrato?8:7);
				colocarTotales("1,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",v", w.length);
			}
		}else if(rca.getIdCriterio() == 31){//"10.1 DIFERENCIA DE RFC DE PRODUCTOR VS RFC FACTURA"
			Collections.sort(lstRfcProductorVsRfcFactura2);			
			for (RelacionComprasTMP l: lstRfcProductorVsRfcFactura2){					
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);							
				parrafo =  new Paragraph(l.getRfcProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getRfcFacVenta(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}				
				contBitacoraDet ++;
			}
			
		}else if(rca.getIdCriterio() == 30000){//10.1 DIFERENCIA DE RFC DE PRODUCTOR RELACIÓN DE COMPRAS VS BASE DE DATOS
			Collections.sort(lstRfcProductorVsRfcFacturaSinContrato);			
			for (RelacionComprasTMP l: lstRfcProductorVsRfcFacturaSinContrato){					
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);							
				parrafo =  new Paragraph(l.getRfcProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getCurpProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();					
				contBitacoraDet ++;
			}	
		}else if(rca.getIdCriterio() == 13){// 5.2 FACTURAS FUERA DE PERIODO
			if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				Collections.sort(lstFacturaFueraPeriodoAuditor);
				contBitacoraDet = 1;				
				for(FacturaFueraPeriodoAuditor l: lstFacturaFueraPeriodoAuditor){
					configTotales = new String [2];
					if(siAplicaFolioContrato){
						configTotales[0] = "1,TOTAL CON;8,"+totalVolPorContrato+",v";
						configTotales[1] = "1,TOTAL BOD;8,"+totalVolPorBodega+",v";
					}else{
						configTotales[1] = "1,TOTAL BOD;7,"+totalVolPorBodega+",v";
					}					
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					curpProductor = l.getCurpProductor();
					rfcProductor = l.getRfcProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? 
							TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
					parrafo.setLeading(1, 1);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolTotalFacVenta();
					totalVolPorBodega +=  l.getVolTotalFacVenta();
					granTotalVol +=  l.getVolTotalFacVenta();			
					contBitacoraDet ++;
				}
				if(siAplicaFolioContrato){
					colocarTotales("1,TOTAL CON;8,"+totalVolPorContrato+",v", w.length);
					colocarTotales("1,TOTAL BOD;8,"+totalVolPorBodega+",v", w.length);
					colocarTotales("1,GRAN TOTAL;8,"+granTotalVol+",v", w.length);
				}else{
					colocarTotales("1,TOTAL BOD;7,"+totalVolPorBodega+",v", w.length);
					colocarTotales("1,GRAN TOTAL;7,"+granTotalVol+",v", w.length);
				}
				
			} else {
				Collections.sort(lstFacturaFueraPeriodoPago);
				contBitacoraDet = 1;				
				for(FacturaFueraPeriodoPago l: lstFacturaFueraPeriodoPago){
					configTotales = new String [2];
					if(siAplicaFolioContrato){
						configTotales[0] = "3,TOTAL CON;10,"+totalVolPorContrato+",v";
						configTotales[1] = "3,TOTAL BOD;10,"+totalVolPorBodega+",v";
					}else{
						configTotales[1] = "1,TOTAL BOD;9,"+totalVolPorBodega+",v";
					}
					
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					curpProductor = l.getCurpProductor();
					rfcProductor = l.getRfcProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoInicialPago()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoFinalPago()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
					parrafo.setLeading(1, 1);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();

					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolTotalFacVenta();
					totalVolPorBodega +=  l.getVolTotalFacVenta();
					granTotalVol +=  l.getVolTotalFacVenta();	
					contBitacoraDet ++;
				}
				if(siAplicaFolioContrato){
					colocarTotales("1,TOTAL CON;10,"+totalVolPorContrato+",v", w.length);
					colocarTotales("1,TOTAL BOD;10,"+totalVolPorBodega+",v", w.length);
					colocarTotales("1,GRAN TOTAL;10,"+granTotalVol+",v", w.length);
				}else{
					colocarTotales("1,TOTAL BOD;9,"+totalVolPorBodega+",v", w.length);
					colocarTotales("1,GRAN TOTAL;9,"+granTotalVol+",v", w.length);
				}
								
			}		
		}else if(rca.getIdCriterio() == 14){//VALIDAR QUE SEA MISMO FOLIO, RFC, VOLUMEN, IMPORTE AL REGISTRADO EN BDD
			for(FacturasIgualesFacAserca l: lstFacturasIgualesFacAserca){
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getRfcProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getRfcComprador(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				for(GruposCamposRelacionV lg: rca.getLstGruposCamposDetalleRelacionV()){
					if(lg.getIdCampo()==15){//folioFacturaVenta
						parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 2, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==16){//Fecha factura
						parrafo =  new Paragraph(l.getFechaEmisionFac()!=null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString():"", TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 2, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==17){//Rfc factura
						parrafo =  new Paragraph(l.getRfcFacVenta(), TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 2, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==19){//P.N.A. SOLICITADO PARA APOYO (TON.)
						parrafo =  new Paragraph(l.getVolSolFacVenta()!=null?TextUtil.formateaNumeroComoVolumen(l.getVolSolFacVenta()):"" , TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 3, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==65){//P.N.A. TOTAL DE LA FACTURA (TON.)
						parrafo =  new Paragraph(l.getVolTotalFacVenta()!=null?TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"" , TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 3, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==20){//IMPORTE TOTAL FACTURADO ($)
						parrafo =  new Paragraph(l.getImpSolFacVenta()!=null?TextUtil.formateaNumeroComoCantidad(l.getImpSolFacVenta()):"" , TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 3, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==70){//VARIEDAD
						parrafo =  new Paragraph(l.getVariedad()!=null && !l.getVariedad().isEmpty()?l.getVariedad():"" , TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 2, 1);
						t.addCell(cell);
					}else if(lg.getIdCampo()==71){//CULTIVO
						parrafo =  new Paragraph(l.getCultivo()!=null && !l.getCultivo().isEmpty()?l.getCultivo():"" , TIMESROMAN08);
						cell = new PdfPCell(parrafo);
						cell =createCell(parrafo, 0, 2, 1);
						t.addCell(cell);
					}
				}
				
			}		
		}else if(rca.getIdCriterio() == 15){//PRECIO PAGADO AL PRODUCTOR DEBERÁ SER IGUAL AL TIPO DE CAMBIO DE LA  FECHA  DE LA FACTURA
			contBitacoraDet = 1;
			Collections.sort(lstPrecioPagPorTipoCambio);
			for(PrecioPagPorTipoCambio l: lstPrecioPagPorTipoCambio){												
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				curpProductor = l.getFolioFacturaVenta();
				rfcProductor = l.getRfcProductor();
				fcProductor = l.getFolioContrato()+((l.getCurpProductor() != null && !l.getCurpProductor().isEmpty())?l.getCurpProductor():l.getRfcProductor());				
				
				if(contBitacoraDet != 0){
					if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){						
						posicionTotal = (siAplicaFolioContrato?6:5);	
						totalesContrato = new StringBuilder();
						totalesContrato.append("1,TOTAL CON;"+(siAplicaFolioContrato?(++posicionTotal)+","+totalVolPorContrato+",v;":"")+(++posicionTotal)+","+totalVolPorContrato1+",i;"
								+(++posicionTotal)+","+totalVolPorContrato2+",i;"+(++posicionTotal)+","+totalVolPorContrato3+",i;"
								+(++posicionTotal)+","+totalVolPorContrato4+",i;"+(++posicionTotal)+","+totalVolPorContrato5+",i;"+(++posicionTotal)+","+totalVolPorContrato6+",i;"
								+(++posicionTotal)+","+totalVolPorContrato7+",i;");
						colocarTotales(totalesContrato.toString(),  w.length);
						totalVolPorContrato = 0;						
						totalVolPorContrato1 = 0;						
						totalVolPorContrato2 = 0;						
						totalVolPorContrato3 = 0;						
						totalVolPorContrato4 = 0;						
						totalVolPorContrato5 = 0;						
						totalVolPorContrato6 = 0;	
						totalVolPorContrato7 = 0;
					}
							
				}		
								
				if(!claveBodegaTmp.equals(claveBodega)){
					parrafo =  new Paragraph(claveBodega, TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);							
				if(!nomEstadoTmp.equals(nombreEstado)){
					parrafo =  new Paragraph(nombreEstado, TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){		
					if(!temFolioContrato.equals(folioContrato)){					
						parrafo =  new Paragraph(folioContrato, TIMESROMAN08);			
					}	else{
						parrafo =  new Paragraph("", TIMESROMAN08);
					}
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
				parrafo =  new Paragraph(paternoProductor+" "+maternoProductor+" "+nombreProductor, TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getFechaEmisionFac() != null ? new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString()+"":"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
								
				parrafo =  new Paragraph(l.getPrecioFacMxpTon() != null ? TextUtil.formateaNumeroComoCantidad(l.getPrecioFacMxpTon()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getImpSolFacVenta() != null ? TextUtil.formateaNumeroComoCantidad(l.getImpSolFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getPrecioPactadoPorTonelada() != null ? TextUtil.formateaNumeroComoCantidad(l.getPrecioPactadoPorTonelada()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				parrafo =  new Paragraph(l.getTipoCambio() != null ? TextUtil.formateaNumeroComoCantidad(l.getTipoCambio()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
							
				parrafo =  new Paragraph(l.getImporteCalculadoPagar() != null ? TextUtil.formateaNumeroComoCantidad(l.getImporteCalculadoPagar()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);				
				
				
				parrafo =  new Paragraph(l.getDifMontoXFac() != null ? TextUtil.formateaNumeroComoCantidad(l.getDifMontoXFac()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				
				if(!claveBodegaTmp.equals(claveBodega)){
					parrafo =  new Paragraph(claveBodega, TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}	
				
				if(!temFCProductor.equals(fcProductor)){
					parrafo =  new Paragraph(l.getDifMontoTotal() != null ? TextUtil.formateaNumeroComoCantidad(l.getDifMontoTotal()):"", TIMESROMAN08);					
					totalVolPorContrato7 += l.getDifMontoTotal();
					System.out.println(totalVolPorContrato7);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}					
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();	
				if(!temFCProductor.equals(fcProductor)){
					//totalVolPorContrato7 += l.getDifMontoTotal();	
					granTotalVol7 += l.getDifMontoTotal();
//					System.out.println(granTotalVol7);
				}
				
				
				temFCProductor = l.getFolioContrato()+((l.getCurpProductor() != null && !l.getCurpProductor().isEmpty())?l.getCurpProductor():l.getRfcProductor());
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				totalVolPorContrato += l.getVolTotalFacVenta();
				totalVolPorContrato1 += l.getPrecioFacMxpTon();
				totalVolPorContrato2 += l.getImpSolFacVenta();
				totalVolPorContrato3 += l.getPrecioPactadoPorTonelada();
				totalVolPorContrato4 += l.getTipoCambio();
				totalVolPorContrato5 += l.getImporteCalculadoPagar();
				totalVolPorContrato6 += l.getDifMontoXFac();
				//System.out.println(totalVolPorContrato7);
			
				granTotalVol += l.getVolTotalFacVenta();
				granTotalVol1 += l.getPrecioFacMxpTon();
				granTotalVol2 += l.getImpSolFacVenta();
				granTotalVol3 += l.getPrecioPactadoPorTonelada();
				granTotalVol4 += l.getTipoCambio();
				granTotalVol5 += l.getImporteCalculadoPagar();
				granTotalVol6 += l.getDifMontoXFac();
				
				//granTotalVol7 += totalVolPorContrato7;
				
								
			
				
				contBitacoraDet ++;
			}	
			posicionTotal = (siAplicaFolioContrato?6:5);		
			totalesContrato = new StringBuilder(); 
			totalesContrato.append("1,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",v;"+(++posicionTotal)+","+totalVolPorContrato1+",i;"
					+(++posicionTotal)+","+totalVolPorContrato2+",i;"+(++posicionTotal)+","+totalVolPorContrato3+",i;"
					+(++posicionTotal)+","+totalVolPorContrato4+",i;"+(++posicionTotal)+","+totalVolPorContrato5+",i;"+(++posicionTotal)+","+totalVolPorContrato6+",i;"
					+(++posicionTotal)+","+totalVolPorContrato7+",i;");
			colocarTotales(totalesContrato.toString(),  w.length);
			posicionTotal = (siAplicaFolioContrato?6:5);			
			colocarTotales("1,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",v;"+(++posicionTotal)+","+granTotalVol1+",i;"+(++posicionTotal)+","+granTotalVol2+",i;"
					+(++posicionTotal)+","+granTotalVol3+",i;"+(++posicionTotal)+","+granTotalVol4+",i;"
					+(++posicionTotal)+","+granTotalVol5+",i;"+(++posicionTotal)+","+granTotalVol6+",i;"+(++posicionTotal)+","+granTotalVol7+",i;",w.length);
		
		}else if(rca.getIdCriterio() == 16){//"NO SE REPITAN CHEQUES-BANCO POR EMPRESA"
			contBitacoraDet = 1;
			Collections.sort(lstChequesDuplicadoBancoPartipante);
			for(ChequesDuplicadoBancoPartipante l: lstChequesDuplicadoBancoPartipante){
//				configTotales = new String [2];
//				if(siAplicaFolioContrato){
//					configTotales[0] = "3,TOTAL;7,"+totalVolPorContrato+",v";
//					configTotales[1] = "1,TOTAL;7,"+totalVolPorBodega+",v";
//				}else{
//					//configTotales[0] = "3,TOTAL;6,"+totalVolPorContrato+",v";
//					configTotales[1] = "1,TOTAL;6,"+totalVolPorBodega+",v";
//				}								
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();	
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);
//				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
//				crearColumna("S-2-"+ (l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()),"DET");
				crearColumna("S-3-"+ l.getFolioDocPago(), "DET");
				crearColumna("S-3-"+l.getBancoSinaxc(),"DET");				
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);			
//				if(siAplicaFolioContrato){					
//					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
//				}
//				crearColumna("S-1-"+l.getClaveBodega(),"DET");	
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();					
//				if(siAplicaFolioContrato){				
//					temFolioContrato = l.getFolioContrato();
//				}
//				totalVolPorContrato += l.getVolTotalFacVenta();
//				totalVolPorBodega += l.getVolTotalFacVenta();
				//granTotalVol += l.getVolTotalFacVenta();
				//contBitacoraDet ++;
			}			
/*
			if(siAplicaFolioContrato){
				//colocarTotales("3,TOTAL;7,"+totalVolPorContrato+",v",w.length);
				//colocarTotales("3,TOTAL;7,"+totalVolPorBodega+",v",w.length);
				colocarTotales("3,TOTAL;4,"+granTotalVol+",v",w.length);
			}else{
				colocarTotales("3,TOTAL;4,"+granTotalVol+",v",w.length);
			}
*/			
		}else if(rca.getIdCriterio() == 17){//"CHEQUES FUERA DEL PERIODO DEL AUDITOR (SOLO APLICA AXC)."	
			contBitacoraDet = 1;
			if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				Collections.sort(lstChequeFueraPeriodoAuditor);
				for(ChequeFueraPeriodoAuditor l: lstChequeFueraPeriodoAuditor){
					configTotales = new String [2];
					if(siAplicaFolioContrato){
//						configTotales[0] = "3,TOTAL CON;8,"+totalVolPorContrato+",v"+";9,"+totalVolPorContrato1+",v";
						configTotales[0] = "3,TOTAL CON;5,"+totalVolPorContrato+",v";
//						configTotales[1] = "3,TOTAL BOD;8,"+totalVolPorBodega+",v"+";9,"+totalVolPorBodega1+",v";
						configTotales[1] = "3,TOTAL BOD;5,"+totalVolPorBodega+",v";
					}else{
//						configTotales[1] = "1,TOTAL BOD;7,"+totalVolPorBodega+",v"+";8,"+totalVolPorBodega1+",v";
						configTotales[1] = "1,TOTAL BOD;5,"+totalVolPorBodega+",v";
					}
									
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					curpProductor = l.getCurpProductor();
					rfcProductor = l.getRfcProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					
					parrafo =  new Paragraph(l.getVolTotalFacVenta()!=null && !l.getVolTotalFacVenta().equals("-")&& !l.getVolTotalFacVenta().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"" , TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getFolioDocPago(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);				
					parrafo =  new Paragraph(l.getBancoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getFechaDocPagoSinaxc() != null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaDocPagoSinaxc()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);				
					
//					parrafo =  new Paragraph(l.getImpTotalPagoSinaxc()!=null && !l.getImpTotalPagoSinaxc().equals("-")&& !l.getImpTotalPagoSinaxc().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getImpTotalPagoSinaxc()):"" , TIMESROMAN08);
//					cell = new PdfPCell(parrafo);
//					cell =createCell(parrafo, 0, 3, 1);
//					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolTotalFacVenta();
//					totalVolPorContrato1 += l.getImpTotalPagoSinaxc();
					totalVolPorBodega += l.getVolTotalFacVenta();
//					totalVolPorBodega1 += l.getImpTotalPagoSinaxc();
					granTotalVol += l.getVolTotalFacVenta();
//					granTotalVol1 += l.getImpTotalPagoSinaxc();
					contBitacoraDet ++;
				}//end for
				if(siAplicaFolioContrato){
//					colocarTotales("3,TOTAL CON;8,"+totalVolPorContrato+",v"+";9,"+totalVolPorContrato1+",v", w.length);
					colocarTotales("3,TOTAL CON;6,"+totalVolPorContrato+",v", w.length);
//					colocarTotales("3,TOTAL BOD;8,"+totalVolPorBodega+",v"+";9,"+totalVolPorBodega1+",v", w.length);
					colocarTotales("3,TOTAL BOD;6,"+totalVolPorBodega+",v", w.length);
//					colocarTotales("3,GRAN TOTAL;8,"+granTotalVol+",v"+";9,"+granTotalVol1+",v", w.length);
					colocarTotales("3,GRAN TOTAL;6,"+granTotalVol+",v", w.length);
				}else{
//					colocarTotales("1,TOTAL BOD;7,"+totalVolPorBodega+",v"+";8,"+totalVolPorBodega1+",v", w.length);
					colocarTotales("1,TOTAL BOD;5,"+totalVolPorBodega+",v", w.length);
//					colocarTotales("1,GRAN TOTAL;7,"+granTotalVol+",v"+";8,"+granTotalVol1+",v", w.length);
					colocarTotales("1,GRAN TOTAL;5,"+granTotalVol+",v", w.length);
				}
				
			}else{ 
				Collections.sort(listChequeFueraPeriodoContrato);
				for(ChequeFueraPeriodoContrato l: listChequeFueraPeriodoContrato){
					configTotales = new String [2];
					if(siAplicaFolioContrato){
						configTotales[0] = "3,TOTAL CON;6,"+totalVolPorContrato+",v";
						configTotales[1] = "3,TOTAL BOD;6,"+totalVolPorBodega+",v";
					}else{		
						configTotales[1] = "1,TOTAL BOD;5,"+totalVolPorBodega+",v";
					}
								
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					curpProductor = l.getCurpProductor();
					rfcProductor = l.getRfcProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					parrafo =  new Paragraph(l.getVolTotalFacVenta()!=null && !l.getVolTotalFacVenta().equals("-")&& !l.getVolTotalFacVenta().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"" , TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getPeriodoInicialPago()!=null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoInicialPago()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getPeriodoFinalPago()!=null ?new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoFinalPago()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);					
					parrafo =  new Paragraph(l.getFolioDocPago(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);				
					parrafo =  new Paragraph(l.getBancoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getFechaDocPagoSinaxc() != null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaDocPagoSinaxc()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);				
					
//					parrafo =  new Paragraph(l.getImpTotalPagoSinaxc()!=null && !l.getImpTotalPagoSinaxc().equals("-")&& !l.getImpTotalPagoSinaxc().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getImpTotalPagoSinaxc()):"" , TIMESROMAN08);
//					cell = new PdfPCell(parrafo);
//					cell =createCell(parrafo, 0, 3, 1);
//					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += (l.getVolTotalFacVenta() != null ? l.getVolTotalFacVenta():0);
					totalVolPorContrato1 += (l.getImpTotalPagoSinaxc()!= null ? l.getImpTotalPagoSinaxc():0);
					totalVolPorBodega += (l.getVolTotalFacVenta() != null ? l.getVolTotalFacVenta() :0);
					totalVolPorBodega1 += (l.getImpTotalPagoSinaxc() != null ? l.getImpTotalPagoSinaxc() :0);
					granTotalVol += (l.getVolTotalFacVenta() != null ? l.getVolTotalFacVenta() :0);
					granTotalVol1 += (l.getImpTotalPagoSinaxc() != null ? l.getImpTotalPagoSinaxc() :0);
					contBitacoraDet ++;
				}//end for				
				if(siAplicaFolioContrato){
					colocarTotales("3,TOTAL CON;6,"+totalVolPorContrato+",v",w.length);
					colocarTotales("3,TOTAL BOD;6,"+totalVolPorBodega+",v", w.length);
					colocarTotales("3,GRAN TOTAL;6,"+granTotalVol+",v",w.length);
				}else{
					colocarTotales("1,TOTAL BOD;5,"+totalVolPorBodega+",v",w.length);
					colocarTotales("1,GRAN TOTAL;5,"+granTotalVol+",v",w.length);
				}
				
			}

		}else if(rca.getIdCriterio() == 18){//"9 IMPORTE FACTURADO MAYOR AL IMPORTE PAGADO"
			contBitacoraDet = 1;
			Collections.sort(lstFacturasVsPago);
			for(FacturasVsPago l: lstFacturasVsPago ){
				configTotales = new String [2];
				configTotales[0] = "1,TOTAL CON;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",v;7,"+totalVolPorContrato2+",v";
				configTotales[1] = "1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v";
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpSolFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpTotalPagoSinaxc()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				
				totalVolPorContrato += l.getImpSolFacVenta();
				totalVolPorContrato1 += l.getImpTotalPagoSinaxc();
				totalVolPorContrato2 += l.getVolTotalFacVenta();
				totalVolPorBodega += l.getImpSolFacVenta();
				totalVolPorBodega1 += l.getImpTotalPagoSinaxc();
				totalVolPorBodega2 += l.getVolTotalFacVenta();
				granTotalVol += l.getImpSolFacVenta();
				granTotalVol1 += l.getImpTotalPagoSinaxc();
				granTotalVol2 += l.getVolTotalFacVenta();
				
				contBitacoraDet ++;
			}		
			if(siAplicaFolioContrato){
				colocarTotales("1,TOTAL CON;6,"+totalVolPorContrato+",v"+";7,"+totalVolPorContrato1+",v;8,"+totalVolPorContrato2+",v", w.length);
				colocarTotales("1,TOTAL BOD;6,"+totalVolPorBodega+",v"+";7,"+totalVolPorBodega1+",v;8,"+totalVolPorBodega2+",v", w.length);
				colocarTotales("1,GRAN TOTAL;6,"+granTotalVol+",v"+";7,"+granTotalVol1+",v;8,"+granTotalVol2+",v", w.length);
			}else{				
				colocarTotales("1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v", w.length);
				colocarTotales("1,GRAN TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",v;7,"+granTotalVol2+",v", w.length);
			}
			
			
		}else if(rca.getIdCriterio() == 19){//"5.5 DIFERENCIA DE VOLUMEN FACTURA GLOBAL VS FACTURAS INDIVIDUALES"
			contBitacoraDet = 1;
			Collections.sort(lstFGlobalVsFIndividual);
			for(RelacionComprasTMP  l: lstFGlobalVsFIndividual){
				configTotales = new String [2];
				if(siAplicaFolioContrato){
					configTotales[0] = "1,TOTAL CON;6,"+totalVolPorContrato+",v"+";7,"+totalVolPorContrato1+",i"+";8,"+totalVolPorContrato2+",i";
					configTotales[1] = "1,TOTAL BOD;6,"+totalVolPorBodega+",v"+";7,"+totalVolPorBodega1+",i"+";8,"+totalVolPorBodega2+",i";
				}else{
					configTotales[1] = "1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i";
				}
				
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);
				parrafo =  new Paragraph(l.getNumeroFacGlobal(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getVolPnaFacGlobal()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getDifVolumenFglobalVSFind()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);				
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}				
				totalVolPorContrato += l.getVolPnaFacGlobal();
				totalVolPorContrato1 += l.getVolTotalFacVenta();
				totalVolPorContrato2 += l.getDifVolumenFglobalVSFind();
//				totalVolPorContrato3 += l.getImpPrecioTonPagiSinaxc();
//											
				totalVolPorBodega += l.getVolPnaFacGlobal();
				totalVolPorBodega1 += l.getVolTotalFacVenta();
				totalVolPorBodega2 += l.getDifVolumenFglobalVSFind();
//				totalVolPorBodega3 += l.getImpPrecioTonPagiSinaxc();
								
				granTotalVol += l.getVolPnaFacGlobal();
				granTotalVol1 += l.getVolTotalFacVenta();
				granTotalVol2 += l.getDifVolumenFglobalVSFind();
//				granTotalVol3 += l.getImpPrecioTonPagiSinaxc();
//				
				contBitacoraDet ++;				
			}	
			if(siAplicaFolioContrato){
				colocarTotales("1,TOTAL CON;6,"+totalVolPorContrato+",v"+";7,"+totalVolPorContrato1+",i"+";8,"+totalVolPorContrato2+",i", w.length);
				colocarTotales("1,TOTAL BOD;6,"+totalVolPorBodega+",v"+";7,"+totalVolPorBodega1+",i"+";8,"+totalVolPorBodega2+",i", w.length);
				colocarTotales("1,GRAN TOTAL;6,"+granTotalVol+",v"+";7,"+granTotalVol1+",i"+";8,"+granTotalVol2+",i", w.length);
			}else{
				colocarTotales("1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i", w.length);
				colocarTotales("1,GRAN TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",i"+";7,"+granTotalVol2+",i", w.length);
			}
			
		}else if(rca.getIdCriterio() == 20){//RESUMEN DE OBSERVACIONES
			totalesContrato = new StringBuilder();
			Collections.sort(lstBoletasFacturasPagosIncosistentes);
			contBitacoraDet = 1;	
			for(BoletasFacturasPagosIncosistentes l: lstBoletasFacturasPagosIncosistentes){			
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){							
							posicionTotal = (siAplicaFolioContrato?5:4);	
							totalesContrato = new StringBuilder();
							totalesContrato.append("1,TOTAL CON;"+(siAplicaFolioContrato?(++posicionTotal)+","+totalVolPorContrato+",v;":"")+(++posicionTotal)+","+totalVolPorContrato1+",v;"
									+(++posicionTotal)+","+totalVolPorContrato2+",v;"+(++posicionTotal)+","+totalVolPorContrato3+",v;"
									+(++posicionTotal)+","+totalVolPorContrato4+",v;"+(++posicionTotal)+","+totalVolPorContrato5+",v;"+(++posicionTotal)+","+totalVolPorContrato6+",v;"
									+(++posicionTotal)+","+totalVolPorContrato7+",v;");				
							if(reporteAplica.contains( ",1,")){
								totalesContrato.append((++posicionTotal)+","+totalVolPorContratoPredioNoPag+",v;");
							}
							if(reporteAplica.contains( ",26,")){
								totalesContrato.append((++posicionTotal)+","+totalVolPorContratoPredioIncons+",v;");
							}
							if(reporteAplica.contains(",19,")){
								totalesContrato.append((++posicionTotal)+","+totalVolPorContratoDifFacGlobal+",v;");
							}							
							totalesContrato.append((++posicionTotal)+","+totalVolPorContraTotalObs+",v;");
							
							colocarTotales(totalesContrato.toString(),  w.length);							
							totalVolPorContrato = 0;
							totalVolPorContrato1 = 0;
							totalVolPorContrato2 = 0;
							totalVolPorContrato3 = 0;
							totalVolPorContrato4 = 0;
							totalVolPorContrato5 = 0;
							totalVolPorContrato6 = 0;
							totalVolPorContrato7 = 0;
							totalVolPorContrato8 = 0;	
							if(reporteAplica.contains( ",1,")){
								totalVolPorContratoPredioNoPag = 0;
							}
							if(reporteAplica.contains( ",26,")){
								totalVolPorContratoPredioIncons = 0;
							}
							if(reporteAplica.contains(",19,")){
								totalVolPorContratoDifFacGlobal = 0;
							}							
							totalVolPorContraTotalObs = 0;
						}						
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){							
							if(siAplicaFolioContrato && temFolioContrato.equals(l.getFolioContrato())){
								totalesContrato = new StringBuilder();
								posicionTotal = (siAplicaFolioContrato?5:4);				
								totalesContrato.append("1,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",v;"+(++posicionTotal)+","+totalVolPorContrato1+",v;"
										+(++posicionTotal)+","+totalVolPorContrato2+",v;"+(++posicionTotal)+","+totalVolPorContrato3+",v;"
										+(++posicionTotal)+","+totalVolPorContrato4+",v;"+(++posicionTotal)+","+totalVolPorContrato5+",v;"+(++posicionTotal)+","+totalVolPorContrato6+",v;"
										+(++posicionTotal)+","+totalVolPorContrato7+",v;");				
								if(reporteAplica.contains( ",1,")){
									totalesContrato.append((++posicionTotal)+","+totalVolPorContratoPredioNoPag+",v;");
								}
								if(reporteAplica.contains( ",26,")){
									totalesContrato.append((++posicionTotal)+","+totalVolPorContratoPredioIncons+",v;");
								}
								if(reporteAplica.contains(",19,")){
									totalesContrato.append((++posicionTotal)+","+totalVolPorContratoDifFacGlobal+",v;");
								}							
								totalesContrato.append((++posicionTotal)+","+totalVolPorContraTotalObs+",v;");
								System.out.println("Totales Contrato "+totalesContrato);
								colocarTotales(totalesContrato.toString(),  w.length);	
							}
							//ULTIMA FILA DE LOS TOTALES POR BODEGA
							posicionTotal = (siAplicaFolioContrato?5:4);
							totalesBodega = new StringBuilder();
							totalesBodega.append("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v;"+(++posicionTotal)+","+totalVolPorBodega1+",v;"
									+(++posicionTotal)+","+totalVolPorBodega2+",v;"+(++posicionTotal)+","+totalVolPorBodega3+",v;"
									+(++posicionTotal)+","+totalVolPorBodega4+",v;"+(++posicionTotal)+","+totalVolPorBodega5+",v;"+(++posicionTotal)+","+totalVolPorBodega6+",v;"
									+(++posicionTotal)+","+totalVolPorBodega7+",v;");
							if(reporteAplica.contains( ",1,")){
								totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaPredioNoPag+",v;");
							}
							if(reporteAplica.contains( ",26,")){
								totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaPredioIncos+",v;");
							}
							if(reporteAplica.contains(",19,")){
								totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaDifFacGlobal+",v;");
							}							
							totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaTotalObs+",v;");
							colocarTotales(totalesBodega.toString(),  w.length);
							
							totalVolPorContrato = 0;
							totalVolPorContrato1 = 0;
							totalVolPorContrato2 = 0;
							totalVolPorContrato3 = 0;
							totalVolPorContrato4 = 0;
							totalVolPorContrato5 = 0;
							totalVolPorContrato6 = 0;
							totalVolPorContrato7 = 0;
							totalVolPorContrato8 = 0;							
							totalVolPorContraTotalObs = 0;
							
							totalVolPorBodega = 0;						
							totalVolPorBodega1 = 0;						
							totalVolPorBodega2 = 0;						
							totalVolPorBodega3 = 0;						
							totalVolPorBodega4 = 0;						
							totalVolPorBodega5 = 0;						
							totalVolPorBodega6 = 0;						
							totalVolPorBodega7 = 0;						
							totalVolPorBodega8 = 0;								
							totalVolPorBodegaTotalObs = 0;
							
							
							
							if(reporteAplica.contains( ",1,")){
								totalVolPorBodegaPredioNoPag = 0;
							}
							
							if(reporteAplica.contains( ",26,")){
								totalVolPorBodegaPredioIncos = 0;
							}
							if(reporteAplica.contains(",19,")){
								totalVolPorBodegaDifFacGlobal = 0;
							}				
							
						}
					}else{//No aplica contrato
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){						
							totalesBodega = new StringBuilder();					
							posicionTotal = (siAplicaFolioContrato?5:4);					
							totalesBodega.append("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v;"+(++posicionTotal)+","+totalVolPorBodega1+",v;"
									+(++posicionTotal)+","+totalVolPorBodega2+",v;"+(++posicionTotal)+","+totalVolPorBodega3+",v;"
									+(++posicionTotal)+","+totalVolPorBodega4+",v;"+(++posicionTotal)+","+totalVolPorBodega5+",v;"+(++posicionTotal)+","+totalVolPorBodega6+",v;"
									+(++posicionTotal)+","+totalVolPorBodega7+",v;");
							if(reporteAplica.contains( ",1,")){
								totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaPredioNoPag+",v;");
							}							
							if(reporteAplica.contains( ",26,")){
								totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaPredioIncos+",v;");
							}
							if(reporteAplica.contains(",19,")){
								totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaDifFacGlobal+",v;");
							}							
							totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaTotalObs+",v;");
							colocarTotales(totalesBodega.toString(),  w.length);
							totalVolPorBodega = 0;						
							totalVolPorBodega1 = 0;						
							totalVolPorBodega2 = 0;						
							totalVolPorBodega3 = 0;						
							totalVolPorBodega4 = 0;						
							totalVolPorBodega5 = 0;						
							totalVolPorBodega6 = 0;						
							totalVolPorBodega7 = 0;						
							totalVolPorBodega8 = 0;	
							totalVolPorBodega9 = 0;
							// AHS CAMBIO 13102015
							totalVolPorBodegaTotalObs = 0;
							if(reporteAplica.contains( ",1,")){
								totalVolPorBodegaPredioNoPag = 0;
							}
							if(reporteAplica.contains( ",26,")){
								totalVolPorBodegaPredioIncos = 0;
							}
							
							if(reporteAplica.contains(",19,")){
								totalVolPorBodegaDifFacGlobal = 0;
							}							
						}
					}
				}				
				
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					crearColumna("S-1-"+l.getClaveBodega(),"DET");	
					
				}else{
					crearColumna("S-1-\t;","DET");	
				}	
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					crearColumna("S-1-"+l.getNombreEstado(),"DET");	
				}else{
					crearColumna("S-1-\t","DET");
				}	
				
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						crearColumna("S-1-"+l.getFolioContrato(),"DET");	
					}else if(!claveBodegaTmp.equals(l.getClaveBodega())){
						crearColumna("S-1-"+l.getFolioContrato(),"DET");								
					}else{
						crearColumna("S-1-\t","DET");
					}
				}	
			
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
				
				//crearColumna("S-2-"+ (l.getCurpProductor()!=null && !l.getCurpProductor().isEmpty()?l.getCurpProductor():l.getRfcProductor()),"DET");
				
				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				
				crearColumna("V-3-"+l.getVolTotalFacturado()+";"+"V-3-"+l.getVolBolTicket()+";"+"V-3-"+l.getVolTotalFacVenta()+";"+"V-3-"+l.getVolEnpagos()+";"
						+"V-3-"+l.getVolumenNoProcedente()+";"+"V-3-"+l.getDiferenciaEntreVolumen()+";"+"V-3-"+l.getDiferenciaEntreImportes()+";V-3-"+l.getDiferenciaEntreRFC()+";", "DET");
				
				if(rca.getCriteriosByPrograma().contains( ",1,")){
					crearColumna("V-3-"+l.getPredioNoPagado()+";", "DET");						
				}
				
				if(rca.getCriteriosByPrograma().contains( ",26,")){
					crearColumna("V-3-"+l.getPredioInconsistente()+";", "DET");						
				}
				
				if(rca.getCriteriosByPrograma().contains( ",19,")){//Diferencia de facturas globales VS individuales
					crearColumna("V-3-"+l.getDifFacGlobalIndividual()+";", "DET");						
				}				
				crearColumna("V-3-"+l.getVolumenObservado()+";", "DET");
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				totalVolPorContrato += l.getVolTotalFacturado();
				totalVolPorContrato1 += l.getVolBolTicket();
				totalVolPorContrato2 += (l.getVolTotalFacVenta()!=null?l.getVolTotalFacVenta():0);
				totalVolPorContrato3 += l.getVolEnpagos();
				totalVolPorContrato4 += l.getVolumenNoProcedente();
				totalVolPorContrato5 += l.getDiferenciaEntreVolumen();
				totalVolPorContrato6 += l.getDiferenciaEntreImportes();
				totalVolPorContrato7 += (l.getDiferenciaEntreRFC()!=null?l.getDiferenciaEntreRFC():0);
				totalVolPorContraTotalObs += (l.getVolumenObservado()!=null?l.getVolumenObservado():0);			
				if(reporteAplica.contains( ",1,")){
					totalVolPorContratoPredioNoPag += (l.getPredioNoPagado()!=null?l.getPredioNoPagado():0);
					totalVolPorBodegaPredioNoPag += (l.getPredioNoPagado()!=null?l.getPredioNoPagado():0);
					granTotalVolNoPag += (l.getPredioNoPagado()!=null?l.getPredioNoPagado():0);
				}
				
				if(reporteAplica.contains( ",26,")){
					totalVolPorContratoPredioIncons += (l.getPredioInconsistente()!=null?l.getPredioInconsistente():0);
					totalVolPorBodegaPredioIncos += (l.getPredioInconsistente()!=null?l.getPredioInconsistente():0);
					granTotalVolPredioIncos += (l.getPredioInconsistente()!=null?l.getPredioInconsistente():0);
				}
				if(reporteAplica.contains(",19,")){
					totalVolPorContratoDifFacGlobal += (l.getDifFacGlobalIndividual()!=null?l.getDifFacGlobalIndividual():0);
					totalVolPorBodegaDifFacGlobal += (l.getDifFacGlobalIndividual()!=null?l.getDifFacGlobalIndividual():0);
					granTotalVolDifFacGlobal += (l.getDifFacGlobalIndividual()!=null?l.getDifFacGlobalIndividual():0);
				}						
				totalVolPorBodega += l.getVolTotalFacturado();
				totalVolPorBodega1 += l.getVolBolTicket();
				totalVolPorBodega2 += (l.getVolTotalFacVenta()!=null?l.getVolTotalFacVenta():0);
				totalVolPorBodega3 += l.getVolEnpagos();
				totalVolPorBodega4 += l.getVolumenNoProcedente();
				totalVolPorBodega5 += l.getDiferenciaEntreVolumen();
				totalVolPorBodega6 += l.getDiferenciaEntreImportes();
				totalVolPorBodega7 += (l.getDiferenciaEntreRFC()!=null?l.getDiferenciaEntreRFC():0);				
				totalVolPorBodegaTotalObs += (l.getVolumenObservado()!=null?l.getVolumenObservado():0);
				
				granTotalVol += l.getVolTotalFacturado();
				granTotalVol1 += l.getVolBolTicket();
				granTotalVol2 += (l.getVolTotalFacVenta()!=null?l.getVolTotalFacVenta():0);
				granTotalVol3 += l.getVolEnpagos();
				granTotalVol4 += l.getVolumenNoProcedente();
				granTotalVol5 += l.getDiferenciaEntreVolumen();
				granTotalVol6 += l.getDiferenciaEntreImportes();
				granTotalVol7 += (l.getDiferenciaEntreRFC()!=null?l.getDiferenciaEntreRFC():0);
				granTotalVolObs +=  (l.getVolumenObservado()!=null?l.getVolumenObservado():0);
				contBitacoraDet ++;						
			}			
				
			totalesContrato = new StringBuilder();
			totalesBodega = new StringBuilder();
			totalesGran = new StringBuilder();
			//ULTIMA FILA DE LOS TOTALES POR CONTRATO
			if(siAplicaFolioContrato){
				posicionTotal = (siAplicaFolioContrato?5:4);				
				totalesContrato.append("1,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",v;"+(++posicionTotal)+","+totalVolPorContrato1+",v;"
						+(++posicionTotal)+","+totalVolPorContrato2+",v;"+(++posicionTotal)+","+totalVolPorContrato3+",v;"
						+(++posicionTotal)+","+totalVolPorContrato4+",v;"+(++posicionTotal)+","+totalVolPorContrato5+",v;"+(++posicionTotal)+","+totalVolPorContrato6+",v;"
						+(++posicionTotal)+","+totalVolPorContrato7+",v;");				
				if(reporteAplica.contains( ",1,")){
					totalesContrato.append((++posicionTotal)+","+totalVolPorContratoPredioNoPag+",v;");
				}
				
				if(reporteAplica.contains( ",26,")){
					totalesContrato.append((++posicionTotal)+","+totalVolPorContratoPredioIncons+",v;");
				}
				if(reporteAplica.contains(",19,")){
					totalesContrato.append((++posicionTotal)+","+totalVolPorContratoDifFacGlobal+",v;");
				}							
				totalesContrato.append((++posicionTotal)+","+totalVolPorContraTotalObs+",v;");
				colocarTotales(totalesContrato.toString(),  w.length);
			}				
			//ULTIMA FILA DE LOS TOTALES POR BODEGA
			posicionTotal = (siAplicaFolioContrato?5:4);					
			totalesBodega.append("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",v;"+(++posicionTotal)+","+totalVolPorBodega1+",v;"
					+(++posicionTotal)+","+totalVolPorBodega2+",v;"+(++posicionTotal)+","+totalVolPorBodega3+",v;"
					+(++posicionTotal)+","+totalVolPorBodega4+",v;"+(++posicionTotal)+","+totalVolPorBodega5+",v;"+(++posicionTotal)+","+totalVolPorBodega6+",v;"
					+(++posicionTotal)+","+totalVolPorBodega7+",v;");
			if(reporteAplica.contains( ",1,")){
				totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaPredioNoPag+",v;");
			}
			if(reporteAplica.contains( ",26,")){
				totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaPredioIncos+",v;");
			}
			if(reporteAplica.contains(",19,")){
				totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaDifFacGlobal+",v;");
			}							
			totalesBodega.append((++posicionTotal)+","+totalVolPorBodegaTotalObs+",v;");
			colocarTotales(totalesBodega.toString(),  w.length);
			
			//Ultima Fila de gran total
			posicionTotal = (siAplicaFolioContrato?5:4);
			totalesGran.append("1,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",v;"+(++posicionTotal)+","+granTotalVol1+",v;"
					+(++posicionTotal)+","+granTotalVol2+",v;"+(++posicionTotal)+","+granTotalVol3+",v;"
					+(++posicionTotal)+","+granTotalVol4+",v;"+(++posicionTotal)+","+granTotalVol5+",v;"+(++posicionTotal)+","+granTotalVol6+",v;"
					+(++posicionTotal)+","+granTotalVol7+",v;");
			if(reporteAplica.contains( ",1,")){
				totalesGran.append((++posicionTotal)+","+granTotalVolNoPag+",v;");
			}
			if(reporteAplica.contains( ",26,")){
				totalesGran.append((++posicionTotal)+","+granTotalVolPredioIncos+",v;");
			}
			if(reporteAplica.contains(",19,")){
				totalesGran.append((++posicionTotal)+","+granTotalVolDifFacGlobal+",v;");
			}							
			totalesGran.append((++posicionTotal)+","+granTotalVolObs+",v;");
			colocarTotales(totalesGran.toString(),  w.length);			
				
		}else if(rca.getIdCriterio() == 21){	
			if(tipo == 1){// TONELADAS TOTALES POR BODEGA DE BOLETAS PAGOS Y FACTURAS		
				//TONELADAS TOTALES POR BODEGA DE BOLETAS PAGOS Y FACTURAS		
				Collections.sort(lstGeneralTonTotPorBodega);
				contBitacoraDet = 1;			
				totalVolPorContrato = 0;
				totalVolPorContrato1 = 0;
				totalVolPorContrato2 = 0;
				totalVolPorContrato3 = 0;
				totalVolPorContrato4 = 0;
				totalVolPorContrato5 = 0;
							
				granTotalVol = 0;
				granTotalVol1 = 0;
				granTotalVol2 = 0;
				granTotalVol3 = 0;
				granTotalVol4 = 0;
				granTotalVol5 = 0;
				 
				for(GeneralToneladasTotPorBodega l: lstGeneralTonTotPorBodega){			
					if(contBitacoraDet != 0){
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){
							posicionTotal = (siAplicaFolioContrato?3:2);
							if(siAplicaFolioContrato){
								colocarTotales("1,TOTAL;"+(++posicionTotal)+","+totalVolPorBodega+",v;"+(++posicionTotal)+","+totalVolPorBodega1+",v;"
										+(++posicionTotal)+","+totalVolPorBodega2+",c;"+(++posicionTotal)+","+totalVolPorBodega3+",c;"
										+(++posicionTotal)+","+totalVolPorBodega4+",c;"+(++posicionTotal)+","+totalVolPorBodega5+",c;", w.length);
							}
							totalVolPorBodega = 0;
							totalVolPorBodega1 = 0;
							totalVolPorBodega2 = 0;
							totalVolPorBodega3 = 0;
							totalVolPorBodega4 = 0;
							totalVolPorBodega5 = 0;
						}
											
					}
					 
					if(!claveBodegaTmp.equals(l.getClaveBodega())){
						crearColumna("S-1-"+l.getClaveBodega(),"DET");	
					}else{
						crearColumna("S-1-\t","DET");
					}
					
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato())){					
							crearColumna("S-1-"+l.getFolioContrato(),"DET");	
						}else{
							crearColumna("S-1-\t;","DET");								
						}				
					}		
					if(!nomEstadoTmp.equals(l.getNombreEstado())){
						crearColumna("S-1-"+l.getNombreEstado(),"DET");	
					}else{
						crearColumna("S-1-"+"\t","DET");
					}	
					crearColumna("V-3-"+l.getPesoNetoAnaBol()+";"+"V-3-"+l.getPesoNetoAnaFac()+";"+"C-3-"+l.getTotalBoletas()+";"+"C-3-"+l.getTotalFacturas()+";"
								+"C-3-"+l.getTotalRegistros()+";"+"C-3-"+l.getNumeroProdBenef(), "DET");
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorBodega += l.getPesoNetoAnaBol();
					totalVolPorBodega1 += l.getPesoNetoAnaFac();
					totalVolPorBodega2 += l.getTotalBoletas();
					totalVolPorBodega3 += l.getTotalFacturas();
					totalVolPorBodega4 += l.getTotalRegistros();
					totalVolPorBodega5 += l.getNumeroProdBenef();
					granTotalVol += l.getPesoNetoAnaBol();
					granTotalVol1 += l.getPesoNetoAnaFac();
					granTotalVol2 += l.getTotalBoletas();
					granTotalVol3 += l.getTotalFacturas();
					granTotalVol4 += l.getTotalRegistros();
					granTotalVol5 += l.getNumeroProdBenef();
					contBitacoraDet ++;						
				}		
				posicionTotal = (siAplicaFolioContrato?3:2);	
				colocarTotales("1,TOTAL;"+(++posicionTotal)+","+totalVolPorBodega+",v;"+(++posicionTotal)+","+totalVolPorBodega1+",v;"
						+(++posicionTotal)+","+totalVolPorBodega2+",c;"+(++posicionTotal)+","+totalVolPorBodega3+",c;"
						+(++posicionTotal)+","+totalVolPorBodega4+",c;"+(++posicionTotal)+","+totalVolPorBodega5+",c;", w.length);
				posicionTotal = (siAplicaFolioContrato?3:2);	
				colocarTotales("1,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",v;"+(++posicionTotal)+","+granTotalVol1+",v;"
						+(++posicionTotal)+","+granTotalVol2+",c;"+(++posicionTotal)+","+granTotalVol3+",c;"
						+(++posicionTotal)+","+granTotalVol4+",c;"+(++posicionTotal)+","+granTotalVol5+",c;", w.length);
			}else{
				//TONELADAS TOTALES POR CONTRATO DE BOLETAS PAGOS Y FACTURAS
				//Inicializar totales
				totalVolPorContrato = 0;
				totalVolPorContrato1 = 0;
				totalVolPorContrato2 = 0;
				totalVolPorContrato3 = 0;
				totalVolPorContrato4 = 0;
				totalVolPorContrato5 = 0;
			
				granTotalVol = 0;
				granTotalVol1 = 0;
				granTotalVol2 = 0;
				granTotalVol3 = 0;
				granTotalVol4 = 0;
				granTotalVol5 = 0;
				
				Collections.sort(lstGeneralToneladasTotalesPorBodFac);
				contBitacoraDet = 1;
				for(GeneralToneladasTotalesPorBodFac l: lstGeneralToneladasTotalesPorBodFac){			
					if(contBitacoraDet != 0){
						if(siAplicaFolioContrato){
							if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){
								colocarTotales("1,TOTAL;4,"+totalVolPorContrato+",v;5,"+totalVolPorContrato1+",v;"
										+"6,"+totalVolPorContrato2+",c;"+"7,"+totalVolPorContrato3+",c;"
										+"8,"+totalVolPorContrato4+",c;"+"9,"+totalVolPorContrato5+",c;", w.length);			
								totalVolPorContrato = 0;
								totalVolPorContrato1 = 0;
								totalVolPorContrato2 = 0;
								totalVolPorContrato3 = 0;
								totalVolPorContrato4 = 0;
								totalVolPorContrato5 = 0;
							}
						}					
					}				
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato())){					
							crearColumna("S-1-"+l.getFolioContrato(),"DET");	
						}else{
							crearColumna("S-1-\t;","DET");								
						}				
					}	
					if(!claveBodegaTmp.equals(l.getClaveBodega())){
						crearColumna("S-1-"+l.getClaveBodega(),"DET");	
					}else if(siAplicaFolioContrato && !temFolioContrato.equals(l.getFolioContrato())){
						crearColumna("S-1-"+l.getClaveBodega(),"DET");	
					}else{
						crearColumna("S-1-\t;S-1-\t","DET");	
					}	
					if(!nomEstadoTmp.equals(l.getNombreEstado())){
						crearColumna("S-1-"+l.getNombreEstado(),"DET");	
					}else{
						crearColumna("S-1-"+"\t","DET");
					}	
					crearColumna("V-3-"+l.getPesoNetoAnaBol()+";"+"V-3-"+l.getPesoNetoAnaFac()+";"+"C-3-"+l.getTotalBoletas()+";"+"C-3-"+l.getTotalFacturas()+";"
								+"C-3-"+l.getTotalRegistros()+";"+"C-3-"+l.getNumeroProdBenef(), "DET");
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getPesoNetoAnaBol();
					totalVolPorContrato1 += l.getPesoNetoAnaFac();
					totalVolPorContrato2 += l.getTotalBoletas();
					totalVolPorContrato3 += l.getTotalFacturas();
					totalVolPorContrato4 += l.getTotalRegistros();
					totalVolPorContrato5 += l.getNumeroProdBenef();
					granTotalVol += l.getPesoNetoAnaBol();
					granTotalVol1 += l.getPesoNetoAnaFac();
					granTotalVol2 += l.getTotalBoletas();
					granTotalVol3 += l.getTotalFacturas();
					granTotalVol4 += l.getTotalRegistros();
					granTotalVol5 += l.getNumeroProdBenef();
					contBitacoraDet ++;						
				}			

				if(siAplicaFolioContrato){
					colocarTotales("1,TOTAL;4,"+totalVolPorContrato+",v;5,"+totalVolPorContrato1+",v;"
							+"6,"+totalVolPorContrato2+",c;"+"7,"+totalVolPorContrato3+",c;"
							+"8,"+totalVolPorContrato4+",c;"+"9,"+totalVolPorContrato5+",c;", w.length);
					colocarTotales("1,GRAN TOTAL;4,"+granTotalVol+",v;5,"+granTotalVol1+",v;"
							+"6,"+granTotalVol2+",c;"+"7,"+granTotalVol3+",c;"
							+"8,"+granTotalVol4+",c;"+"9,"+granTotalVol5+",c;", w.length);	
				}else{
					colocarTotales("1,GRAN TOTAL;3,"+granTotalVol+",v;4,"+granTotalVol1+",v;"
							+"5,"+granTotalVol2+",c;"+"6,"+granTotalVol3+",c;"
							+"7,"+granTotalVol4+",c;"+"8,"+granTotalVol5+",c;", w.length);
				}

			}
						
		}else if(rca.getIdCriterio() == 22){//"4.3 VALORES NULOS REQUERIDOS EN BOLETAS"
			int contBitacoraDet = 1;
			for(BoletasCamposRequeridos l: lstBoletasCamposRequeridos){
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if( !temFolioContrato.equals(l.getFolioContrato())&&contBitacoraDet>1){
							//colocarSumaFolioContratoEnBoletasFueraPeriodo(totalVolBolTicket);						
							//totalVolBolTicket = 0;						
						}
					}
					
					if( !claveBodegaTmp.equals(l.getClaveBodega())&&contBitacoraDet>1){
						//colocarSumaFolioContratoEnBoletasFueraPeriodo(totalVolBolTicketBodega);						
						//totalVolBolTicketBodega = 0;						
					}
				}
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					parrafo =  new Paragraph(l.getClaveBodega(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					parrafo =  new Paragraph(l.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);				
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						parrafo =  new Paragraph(l.getFolioContrato(), TIMESROMAN08);
									
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				if(rca.getCamposQueAplica().contains("12")){
					parrafo =  new Paragraph(l.getFechaEntrada(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				
				if(rca.getCamposQueAplica().contains("63")){
					parrafo =  new Paragraph(l.getVolumen(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				
			}
		}else if(rca.getIdCriterio() == 23){//VALORES NULOS EN FACTURAS
			int contBitacoraDet = 1;
			Collections.sort(lstFacturasCamposRequeridos);
			for(FacturasCamposRequeridos l: lstFacturasCamposRequeridos){
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if( !temFolioContrato.equals(l.getFolioContrato())&&contBitacoraDet>1){
							//colocarSumaFolioContratoEnBoletasFueraPeriodo(totalVolBolTicket);						
							//totalVolBolTicket = 0;						
						}
					}
					
					if( !claveBodegaTmp.equals(l.getClaveBodega())&&contBitacoraDet>1){
						//colocarSumaFolioContratoEnBoletasFueraPeriodo(totalVolBolTicketBodega);						
						//totalVolBolTicketBodega = 0;						
					}
				}
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					parrafo =  new Paragraph(l.getClaveBodega(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					parrafo =  new Paragraph(l.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						parrafo =  new Paragraph(l.getFolioContrato(), TIMESROMAN08);
									
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				if(rca.getCamposQueAplica().contains("16,")){
					parrafo =  new Paragraph(l.getFechaEmisionFac(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				
				if(rca.getCamposQueAplica().contains("17,")){
					parrafo =  new Paragraph(l.getRfcFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains(",19,")){
					parrafo =  new Paragraph(l.getVolSolFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("65,")){
					parrafo =  new Paragraph(l.getVolTotalFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("20,")){
					parrafo =  new Paragraph(l.getImpSolFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("70,")){
					System.out.println("VAriedad "+l.getVariedad());
					parrafo =  new Paragraph(l.getVariedad(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				contBitacoraDet ++;			
			}
			
		}else if(rca.getIdCriterio() == 24){//VALORES NULOS EN PAGOS
			int contBitacoraDet = 1;
			Collections.sort(lstPagosCamposRequeridos);
			for(PagosCamposRequeridos l: lstPagosCamposRequeridos){
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if( !temFolioContrato.equals(l.getFolioContrato())&&contBitacoraDet>1){
							//colocarSumaFolioContratoEnBoletasFueraPeriodo(totalVolBolTicket);						
							//totalVolBolTicket = 0;						
						}
					}
					
					if( !claveBodegaTmp.equals(l.getClaveBodega())&&contBitacoraDet>1){
						//colocarSumaFolioContratoEnBoletasFueraPeriodo(totalVolBolTicketBodega);						
						//totalVolBolTicketBodega = 0;						
					}
				}
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					parrafo =  new Paragraph(l.getClaveBodega(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					parrafo =  new Paragraph(l.getNombreEstado(), TIMESROMAN08);
				}else{
					parrafo =  new Paragraph("", TIMESROMAN08);
				}				
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						parrafo =  new Paragraph(l.getFolioContrato(), TIMESROMAN08);
									
					}else{
						parrafo =  new Paragraph("", TIMESROMAN08);								
					}				
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
				parrafo =  new Paragraph(l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(l.getFolioDocPago(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				if(rca.getCamposQueAplica().contains("67,")){
					parrafo =  new Paragraph(l.getImpTotalPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				
				if(rca.getCamposQueAplica().contains("27,")){
					parrafo =  new Paragraph(l.getTipoDdocPago(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("16,")){
					parrafo =  new Paragraph(l.getFechaDocPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("29,")){
					parrafo =  new Paragraph(l.getBancoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("66,")){
					parrafo =  new Paragraph(l.getImpDocPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("68,")){
					parrafo =  new Paragraph(l.getImpPrecioTonPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
				}
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				contBitacoraDet ++;	
			}
	
		}else if(rca.getIdCriterio() == 29){//9.1 PAGO MENOR DE LA COMPENSACIÓN DE BASES
			Collections.sort(lstPagMenorCompBases);
			for(PagMenorCompBases l: lstPagMenorCompBases){	
				configTotales = new String [2];
				posicionTotal = (siAplicaFolioContrato?5:4);
				configTotales[0] = "1,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",i;"+(++posicionTotal)+","+totalVolPorContrato1+",i;"
						+(++posicionTotal)+","+totalVolPorContrato2+",i;"+(++posicionTotal)+","+totalVolPorContrato3+",i;"
						+(++posicionTotal)+","+totalVolPorContrato4+",i;"+(++posicionTotal)+","+totalVolPorContrato5+",i;"
						+(++posicionTotal)+","+totalVolPorContrato6+",i;"+(++posicionTotal)+","+totalVolPorContrato7+",i;";
				posicionTotal = (siAplicaFolioContrato?5:4);
				configTotales[1] = "1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",i;"+(++posicionTotal)+","+totalVolPorBodega1+",i;"
					+(++posicionTotal)+","+totalVolPorBodega2+",i;"+(++posicionTotal)+","+totalVolPorBodega3+",i;"
					+(++posicionTotal)+","+totalVolPorBodega4+",i;"+(++posicionTotal)+","+totalVolPorBodega5+",i;"
					+(++posicionTotal)+","+totalVolPorBodega6+",i;"+(++posicionTotal)+","+totalVolPorBodega7+",i;";
				
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				
				colocarPrimerasColumnasDetalle(configTotales, w.length);					
				
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpSolFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpCompTonAXC()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpTotalPagoSinaxc()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getCuota()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPagoMenorCuota()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getCompBaseMenorTotal()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPagoMenor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);								
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}			
				totalVolPorContrato += l.getVolTotalFacVenta();
				totalVolPorContrato1 += l.getImpSolFacVenta();
				totalVolPorContrato2 += l.getImpCompTonAXC();				
				totalVolPorContrato3 += l.getImpTotalPagoSinaxc();
				totalVolPorContrato4 += l.getCuota();
				totalVolPorContrato5 += l.getPagoMenorCuota();
				totalVolPorContrato6 += l.getCompBaseMenorTotal();
				totalVolPorContrato7 += l.getPagoMenor();
				
				totalVolPorBodega += l.getVolTotalFacVenta();
				totalVolPorBodega1 += l.getImpSolFacVenta();
				totalVolPorBodega2 += l.getImpCompTonAXC();				
				totalVolPorBodega3 += l.getImpTotalPagoSinaxc();
				totalVolPorBodega4 += l.getCuota();
				totalVolPorBodega5 += l.getPagoMenorCuota();
				totalVolPorBodega6 += l.getCompBaseMenorTotal();
				totalVolPorBodega7 += l.getPagoMenor();
			
				granTotalVol  += l.getVolTotalFacVenta();
				granTotalVol1 += l.getImpSolFacVenta();
				granTotalVol2 += l.getImpCompTonAXC();				
				granTotalVol3 += l.getImpTotalPagoSinaxc();
				granTotalVol4 += l.getCuota();
				granTotalVol5 += l.getPagoMenorCuota();
				granTotalVol6 += l.getCompBaseMenorTotal();
				granTotalVol7 += l.getPagoMenor();
				
			}
			posicionTotal = (siAplicaFolioContrato?5:4);	
			colocarTotales("1,TOTAL CON;"+(++posicionTotal)+","+totalVolPorContrato+",i;"+(++posicionTotal)+","+totalVolPorContrato1+",i;"
					+(++posicionTotal)+","+totalVolPorContrato2+",i;"+(++posicionTotal)+","+totalVolPorContrato3+",i;"
					+(++posicionTotal)+","+totalVolPorContrato4+",i;"+(++posicionTotal)+","+totalVolPorContrato5+",i;"
					+(++posicionTotal)+","+totalVolPorContrato6+",i;"+(++posicionTotal)+","+totalVolPorContrato7+",i;", w.length);
			posicionTotal = (siAplicaFolioContrato?5:4);
			colocarTotales("1,TOTAL BOD;"+(++posicionTotal)+","+totalVolPorBodega+",i;"+(++posicionTotal)+","+totalVolPorBodega1+",i;"
				+(++posicionTotal)+","+totalVolPorBodega2+",i;"+(++posicionTotal)+","+totalVolPorBodega3+",i;"
				+(++posicionTotal)+","+totalVolPorBodega4+",i;"+(++posicionTotal)+","+totalVolPorBodega5+",i;"
				+(++posicionTotal)+","+totalVolPorBodega6+",i;"+(++posicionTotal)+","+totalVolPorBodega7+",i;", w.length);
			posicionTotal = (siAplicaFolioContrato?5:4);			
			colocarTotales("1,GRAN TOTAL;"+(++posicionTotal)+","+granTotalVol+",i;"+(++posicionTotal)+","+granTotalVol1+",i;"
					+(++posicionTotal)+","+granTotalVol2+",i;"+(++posicionTotal)+","+granTotalVol3+",i;"
					+(++posicionTotal)+","+granTotalVol4+",i;"+(++posicionTotal)+","+granTotalVol5+",i;"
					+(++posicionTotal)+","+granTotalVol6+",i;"+(++posicionTotal)+","+granTotalVol7+",i;", w.length);
			
	
		}else if(rca.getIdCriterio() == 25){//"7.1 VOLUMEN NO PROCEDENTE POR RENDIMIENTO MAXIMO ACEPTABLE"
			Collections.sort(lstRendimientosProcedente);
			contBitacoraDet = 1;
			for(RendimientosProcedente l: lstRendimientosProcedente){			
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){
							colocarTotales("3,TOTAL CONTRATO;6,"+totalVolPorContrato+",v;7,"+totalVolPorContrato1+",v;"	
									+"8,"+totalVolPorContrato2+",v",  w.length);	// AHS [LINEA] - 17022015
							totalVolPorContrato = 0;
							totalVolPorContrato1 = 0;
							totalVolPorContrato2 = 0;
						}
						
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){
							if(siAplicaFolioContrato && temFolioContrato.equals(l.getFolioContrato())){
								colocarTotales("3,TOTAL CONTRATO;6,"+totalVolPorContrato+",v;7,"+totalVolPorContrato1+",v;"	
										+"8,"+totalVolPorContrato2+",v",  w.length);	
							}
							
							colocarTotales("3,TOTAL BODEGA;6,"+totalVolPorBodega+",v;7,"+totalVolPorBodega1+",v;"	// AHS [LINEA] - 17022015
									+"8,"+totalVolPorBodega2+",v",  w.length);				// AHS [LINEA] - 17022015				
							totalVolPorBodega = 0;						
							totalVolPorBodega1 = 0;						
							totalVolPorBodega2 = 0;	
							if(siAplicaFolioContrato){
								totalVolPorContrato = 0;
								totalVolPorContrato1 = 0;
								totalVolPorContrato2 = 0;
							}
							
						}
					}					
				}				
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					crearColumna("S-1-"+l.getClaveBodega(),"DET");	
				}else{
					crearColumna("S-1-\t;","DET");	
				}	
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					crearColumna("S-1-"+l.getNombreEstado(),"DET");	
				}else{
					crearColumna("S-1-\t","DET");
				}	
				
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						crearColumna("S-1-"+l.getFolioContrato(),"DET");	
					}else if(!claveBodegaTmp.equals(l.getClaveBodega())){
						crearColumna("S-1-"+l.getFolioContrato(),"DET");								
					}else{
						crearColumna("S-1-\t","DET");
					}				
				}	
			
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor()+";","DET");
				parrafo =  new Paragraph((l.getCurpProductor()!= null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);		
				crearColumna("V-3-"+l.getVolTotalFacturado()+";"+"V-3-"+l.getRendimientoMaximoAceptable()+";"+"V-3-"+l.getVolNoProcedente(), "DET");	// AHS [LINEA] - 17022015
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				totalVolPorContrato += (l.getVolTotalFacturado() != null?l.getVolTotalFacturado():0);
				totalVolPorContrato1 += (l.getRendimientoMaximoAceptable()!=null?l.getRendimientoMaximoAceptable():0);
				//totalVolPorContrato2 += (l.getVolNoProcedente()!=null?l.getVolNoProcedente():0);
				totalVolPorContrato2 += (l.getVolNoProcedente()!=null?Double.parseDouble(l.getVolNoProcedente()):0);	// AHS [LINEA] - 17022015
				
				totalVolPorBodega += (l.getVolTotalFacturado() != null?l.getVolTotalFacturado():0);
				totalVolPorBodega1 += (l.getRendimientoMaximoAceptable()!=null?l.getRendimientoMaximoAceptable():0);
				//totalVolPorBodega2 += (l.getVolNoProcedente()!=null?l.getVolNoProcedente():0);	// AHS [LINEA] - 17022015
				totalVolPorBodega2 += (l.getVolNoProcedente()!=null?Double.parseDouble(l.getVolNoProcedente()):0);	// AHS [LINEA] - 17022015
				
				granTotalVol += (l.getVolTotalFacturado() != null?l.getVolTotalFacturado():0);
				granTotalVol1 += (l.getRendimientoMaximoAceptable()!=null?l.getRendimientoMaximoAceptable():0);
				//granTotalVol2 += (l.getVolNoProcedente()!=null?l.getVolNoProcedente():0);	// AHS [LINEA] - 17022015
				granTotalVol2 += (l.getVolNoProcedente()!=null?Double.parseDouble(l.getVolNoProcedente()):0); 	// AHS [LINEA] - 17022015
				
				contBitacoraDet ++;		
				temFolioContratoAux = l.getFolioContrato();	// AHS [LINEA] - 17022015
			}			
			if(siAplicaFolioContrato){
				// AHS [LINEA] - 17022015				
//				if(temFolioContrato.equals(temFolioContratoAux)){
//					colocarTotales("3,TOTAL BODEGA;5,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",v;"	// AHS [LINEA] - 17022015
//							+"7,"+totalVolPorBodega2+",v",  w.length);	// AHS [LINEA] - 17022015
//					colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"	// AHS [LINEA] - 17022015
//							+"7,"+totalVolPorContrato2+",v",  w.length);	// AHS [LINEA] - 17022015
//					colocarTotales("3,GRAN TOTAL;5,"+granTotalVol+",v;6,"+granTotalVol1+",v;"	// AHS [LINEA] - 17022015
//							+"7,"+granTotalVol2+",v",  w.length);	// AHS [LINEA] - 17022015					
//				} else {
					colocarTotales("3,TOTAL CONTRATO;6,"+totalVolPorContrato+",v;7,"+totalVolPorContrato1+",v;"	// AHS [LINEA] - 17022015
							+"8,"+totalVolPorContrato2+",v",  w.length);	// AHS [LINEA] - 17022015
					colocarTotales("3,TOTAL BODEGA;6,"+totalVolPorBodega+",v;7,"+totalVolPorBodega1+",v;"	// AHS [LINEA] - 17022015
							+"8,"+totalVolPorBodega2+",v",  w.length);	// AHS [LINEA] - 17022015
					colocarTotales("3,GRAN TOTAL;6,"+granTotalVol+",v;7,"+granTotalVol1+",v;"	// AHS [LINEA] - 17022015
							+"8,"+granTotalVol2+",v",  w.length);	// AHS [LINEA] - 17022015										
				//}
				// AHS [LINEA] - 17022015
	
			}else{
				colocarTotales("3,TOTAL;4,"+totalVolPorBodega+",v;5,"+totalVolPorBodega1+",v;"
						+"6,"+totalVolPorBodega2+",v",  w.length);
				colocarTotales("3,TOTAL;4,"+granTotalVol+",v;5,"+granTotalVol1+",v;"
						+"6,"+granTotalVol2+",v",  w.length);
			}
			//end criterio = 25	
		}else if(rca.getIdCriterio() == 32){//"7.5 VOLUMEN NO PROCEDENTE APOYADO EN REGIONAL"
			Collections.sort(lstVolNoProcedenteYApoyEnReg);
			contBitacoraDet = 1;
			for(VolumenNoProcedente l: lstVolNoProcedenteYApoyEnReg){			
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){
							colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"	// 
									+"7,"+totalVolPorContrato2+",v",  w.length);	// 
							totalVolPorContrato = 0;
							totalVolPorContrato1 = 0;
							totalVolPorContrato2 = 0;
						}
						
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){
							if(siAplicaFolioContrato && temFolioContrato.equals(l.getFolioContrato())){
								colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"	
										+"7,"+totalVolPorContrato2+",v",  w.length);	
							}
							
							colocarTotales("3,TOTAL BODEGA;5,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",v;"
									+"7,"+totalVolPorBodega2+",v",  w.length);								
							totalVolPorBodega = 0;						
							totalVolPorBodega1 = 0;						
							totalVolPorBodega2 = 0;	
							if(siAplicaFolioContrato){
								totalVolPorContrato = 0;
								totalVolPorContrato1 = 0;
								totalVolPorContrato2 = 0;
							}
							
						}
					}else{						
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){							
							colocarTotales("1,TOTAL BODEGA;6,"+totalVolPorBodega+",v;7,"+totalVolPorBodega1+",v;"
									+"8,"+totalVolPorBodega2+",v",  w.length);								
							totalVolPorBodega = 0;						
							totalVolPorBodega1 = 0;						
							totalVolPorBodega2 = 0;	
							
							
						}
						
						
					}
				}				
				if(!claveBodegaTmp.equals(l.getClaveBodega())){
					crearColumna("S-1-"+l.getClaveBodega(),"DET");	
				}else{
					crearColumna("S-1-\t;","DET");	
				}	
				if(!nomEstadoTmp.equals(l.getNombreEstado())){
					crearColumna("S-1-"+l.getNombreEstado(),"DET");	
				}else{
					crearColumna("S-1-\t","DET");
				}	
				
				if(siAplicaFolioContrato){
					if(!temFolioContrato.equals(l.getFolioContrato())){					
						crearColumna("S-1-"+l.getFolioContrato(),"DET");	
					}else if(!claveBodegaTmp.equals(l.getClaveBodega())){
						crearColumna("S-1-"+l.getFolioContrato(),"DET");								
					}else{
						crearColumna("S-1-\t","DET");
					}				
				}	
			
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
				parrafo =  new Paragraph((l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				
				//crearColumna("S-2-"+(l.getCurpProductor()!=null?l.getCurpProductor():l.getRfcProductor()),"DET");
				
				
				
				
				crearColumna("S-3-"+ l.getIdVariedad()+";V-3-"+l.getVolTotalFacturado()+";"+"V-3-"+l.getVolApoyadoEnRegional()+";"+"V-3-"+l.getVolNoProcedente(), "DET");	// AHS [LINEA] - 17022015
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				totalVolPorContrato += (l.getVolTotalFacturado() != null?l.getVolTotalFacturado():0);
				totalVolPorContrato1 += (l.getVolApoyadoEnRegional()!=null?l.getVolApoyadoEnRegional():0);
				totalVolPorContrato2 += (l.getVolNoProcedente()!=null?l.getVolNoProcedente():0);	
				totalVolPorBodega += (l.getVolTotalFacturado() != null?l.getVolTotalFacturado():0);
				totalVolPorBodega1 += (l.getVolApoyadoEnRegional()!=null?l.getVolApoyadoEnRegional():0);
				totalVolPorBodega2 += (l.getVolNoProcedente()!=null?l.getVolNoProcedente():0);	 
				
				granTotalVol += (l.getVolTotalFacturado() != null?l.getVolTotalFacturado():0);
				granTotalVol1 += (l.getVolApoyadoEnRegional()!=null?l.getVolApoyadoEnRegional():0);
				granTotalVol2 += (l.getVolNoProcedente()!=null?l.getVolNoProcedente():0); 	 
				contBitacoraDet ++;		
				temFolioContratoAux = l.getFolioContrato();	 
			}			
			if(siAplicaFolioContrato){
					colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"	
							+"7,"+totalVolPorContrato2+",v",  w.length);
					colocarTotales("3,TOTAL BODEGA;5,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",v;"	
							+"7,"+totalVolPorBodega2+",v",  w.length);	
					colocarTotales("3,GRAN TOTAL;5,"+granTotalVol+",v;6,"+granTotalVol1+",v;"	
							+"7,"+granTotalVol2+",v",  w.length);											
			}else{
				colocarTotales("1,TOTAL BODEGA;6,"+totalVolPorBodega+",v;7,"+totalVolPorBodega1+",v;"
						+"8,"+totalVolPorBodega2+",v",  w.length);
				colocarTotales("1,GRAN TOTAL;6,"+granTotalVol+",v;7,"+granTotalVol1+",v;"
						+"8,"+granTotalVol2+",v",  w.length);
			}
			//end criterio = 32	
		}else if(rca.getIdCriterio() == 27){
			Collections.sort(lstVolumenCumplido); // AHS CAMBIO 29062015
			contBitacoraDet = 1;
			for(VolumenFiniquito l: lstVolumenCumplido){			
				crearColumna("S-1-"+l.getClaveBodega(),"DET");					
				if(siAplicaFolioContrato){					
					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
				}	
				crearColumna("S-1-"+(l.getNombreComprador()!=null?l.getNombreComprador():""),"DET");
				crearColumna("S-1-"+(l.getNombreVendedor()!=null?l.getNombreVendedor():""),"DET");
				crearColumna("I-3-"+l.getPrecioPactadoPorTonelada()+";", "DET");
				crearColumna("V-3-"+l.getVolumen()+";", "DET");
				crearColumna("V-3-"+l.getVolTotalFacVenta()+";", "DET");
				crearColumna("V-3-"+l.getDifVolumenFiniquito()+";", "DET");
				//crearColumna("S-1-"+((l.getModalidad()!=null && !l.getModalidad().equals("NA"))?l.getModalidad():""),"DET");				
				parrafo =  new Paragraph((l.getModalidad()!=null && !l.getModalidad().equals("NA"))?l.getModalidad():"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				
				granTotalVol += (l.getPrecioPactadoPorTonelada() != null ? l.getPrecioPactadoPorTonelada():0);						
				granTotalVol1 += (l.getVolumen() != null ? l.getVolumen():0);						
				granTotalVol2 += (l.getVolTotalFacVenta() != null ? l.getVolTotalFacVenta():0);						
				granTotalVol3 += (l.getDifVolumenFiniquito() != null ? l.getDifVolumenFiniquito():0);						
			}							
			colocarTotales("4,TOTAL;5,"+granTotalVol+",i;6,"+granTotalVol1+",v;"+"7,"+granTotalVol2+",v;"+"8,"+granTotalVol3+",v;",  w.length);
			//end criterio = 27
		}else if(rca.getIdCriterio() == 28){//"11.2 PRECIO MENOR AL ESTABLECIDO EN AVISO"
			contBitacoraDet = 1;
			Collections.sort(lstPrecioPagadoMenorQueAviso);
			for(PrecioPagadoNoCorrespondeConPagosV  l: lstPrecioPagadoMenorQueAviso){
				configTotales = new String [2];
				
				if(siAplicaFolioContrato){
					//configTotales[0] = "3,TOTAL CON;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",i"+";7,"+totalVolPorContrato2+",i"+";8,"+totalVolPorContrato3+",i"+";9,"+totalVolPorContrato4+",i;10,"+totalVolPorContrato5+",i";
					//configTotales[1] = "3,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i"+";8,"+totalVolPorBodega3+",i"+";9,"+totalVolPorBodega4+",i;10,"+totalVolPorBodega5+",i";	
				}else{
					configTotales[1] = "1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i"+";8,"+totalVolPorBodega3+",i"+";9,"+totalVolPorBodega4+",i"+";10,"+totalVolPorBodega5+",i";
				}
				
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				curpProductor = l.getCurpProductor();
				rfcProductor = l.getRfcProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getVolTotalFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpSolFacVenta()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpDocPagoSinaxc()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpTotalPagoSinaxc()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImpPrecioTonPagiSinaxc()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPrecioCalculado()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPrecioPagadoEnAviso()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				
				totalVolPorContrato += l.getVolTotalFacVenta();
				totalVolPorContrato1 += l.getImpSolFacVenta();
				totalVolPorContrato2 += l.getImpDocPagoSinaxc();
				totalVolPorContrato3 += l.getImpTotalPagoSinaxc();
				totalVolPorContrato4 += l.getImpPrecioTonPagiSinaxc();
				totalVolPorContrato5 += l.getPrecioCalculado();
				totalVolPorContrato6 += l.getPrecioPagadoEnAviso();
							
				totalVolPorBodega += l.getVolTotalFacVenta();
				totalVolPorBodega1 +=  l.getImpSolFacVenta();
				totalVolPorBodega2 += l.getImpDocPagoSinaxc();
				totalVolPorBodega3 += l.getImpTotalPagoSinaxc();
				totalVolPorBodega4 += l.getImpPrecioTonPagiSinaxc();
				totalVolPorBodega5 += l.getPrecioCalculado();
				totalVolPorBodega6 += l.getPrecioPagadoEnAviso();
				
				granTotalVol += l.getVolTotalFacVenta();
				granTotalVol1 += l.getImpSolFacVenta();
				granTotalVol2 += l.getImpDocPagoSinaxc();
				granTotalVol3 += l.getImpTotalPagoSinaxc();
				granTotalVol4 += l.getImpPrecioTonPagiSinaxc();
				granTotalVol5 += l.getPrecioCalculado();
				granTotalVol6 += l.getPrecioPagadoEnAviso();
				contBitacoraDet ++;				
			}	
			if(siAplicaFolioContrato){
				colocarTotales("3,TOTAL CON;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",i"+";7,"+totalVolPorContrato2+",i"+";8,"+totalVolPorContrato3+",i"+";9,"+totalVolPorContrato4+",i;10,"+totalVolPorContrato5+",i", w.length);
				colocarTotales("3,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i"+";8,"+totalVolPorBodega3+",i"+";9,"+totalVolPorBodega4+",i;10,"+totalVolPorBodega5+",i", w.length);
				colocarTotales("3,GRAN TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",i"+";7,"+granTotalVol2+",i"+";8,"+granTotalVol3+",i"+";9,"+granTotalVol4+",i;10,"+granTotalVol5+",i", w.length);
			}else{
				colocarTotales("1,TOTAL BOD;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i"+";8,"+totalVolPorBodega3+",i"+";9,"+totalVolPorBodega4+",i"+";10,"+totalVolPorBodega5+",i", w.length);
				colocarTotales("1,GRAN TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",i"+";7,"+granTotalVol2+",i"+";8,"+granTotalVol3+",i"+";9,"+granTotalVol4+",i"+";10,"+granTotalVol5+",i", w.length);
			}
			
		}
	}

	private void colocarInfoEnPojo() throws ParseException {
		creaEncabezadoDetalle(1);
		for(BitacoraRelcompras b: rca.getLstBitacoraRelCompras() ){ 
			List<BitacoraRelcomprasDetalle> lstBitacoraDetalle = rDAO.consultaBitacoraRelcomprasDetalle(b.getIdBitacoraRelcompras());			
			for(BitacoraRelcomprasDetalle bd :lstBitacoraDetalle){
				String [] bS = bd.getMensaje().split(";");
				if(rca.getIdCriterio() == 1){	
					columna = 0;
					RelacionComprasTMP p = new RelacionComprasTMP();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setFolioPredio(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setBancoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					
					lstPrediosNoExistenBD.add(p);
				}else if(rca.getIdCriterio() == 2){
					PrediosNoValidadosDRDE p = new PrediosNoValidadosDRDE();
					p.setFolioPredio(bS[0]!=null&&!bS[0].isEmpty()&&!bS[0].equals("null")?bS[0]:"");
					p.setFolioPredioSec(Integer.parseInt(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:""));
					p.setFolioPredioAlterno(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
					lstPrediosNoValidadosDRDE.add(p);
				}else if(rca.getIdCriterio() == 3){
					PrediosNoPagados p = new PrediosNoPagados();
					p.setFolioPredio(bS[0]!=null&&!bS[0].isEmpty()&&!bS[0].equals("null")?bS[0]:"");
					p.setFolioPredioSec(Integer.parseInt(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:""));
					p.setFolioPredioAlterno(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
					lstPrediosNoPagados.add(p);
				}else if(rca.getIdCriterio() == 4){
					ProductorExisteBD p = new ProductorExisteBD();
					p.setFolioProductor(Long.parseLong(bS[0]!=null&&!bS[0].isEmpty()&&!bS[0].equals("null")?bS[0]:""));
					p.setPaternoProductor(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
					p.setMaternoProductor(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
					p.setNombreProductor(bS[3]!=null&&!bS[3].isEmpty()&&!bS[3].equals("null")?bS[3]:"");
					p.setCurpProductor(bS[4]!=null&&!bS[4].isEmpty()&&!bS[4].equals("null")?bS[4]:"");
					p.setRfcProductor(bS[5]!=null&&!bS[5].isEmpty()&&!bS[5].equals("null")?bS[5]:"");
					lstProductorExisteBD.add(p);
				}else if(rca.getIdCriterio() == 26){
					columna = 0;
					PrediosProdsContNoExisteBD p = new PrediosProdsContNoExisteBD();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					}
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setFolioPredio(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setFolioCartaExterna(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					lstPrediosProdsContNoExistenBD.add(p);
				}else if(rca.getIdCriterio() == 30){//7.6 CURP, RFC Y/O NOMBRES INCONSISTENTES
					columna = 0;
					RelacionComprasTMP p = new RelacionComprasTMP();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					}
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;					
					lstCurpRfcYONombresInconsistentes.add(p);							
					
				}else if(rca.getIdCriterio() == 6){
					ProductorPredioPagado pro = new ProductorPredioPagado(); 
					pro.setFolioProductor(Long.parseLong(bS[0]!=null&&!bS[0].isEmpty()&&!bS[0].equals("null")?bS[0]:""));
					pro.setPaternoProductor(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
					pro.setMaternoProductor(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
					pro.setNombreProductor(bS[3]!=null&&!bS[3].isEmpty()&&!bS[3].equals("null")?bS[3]:"");
					pro.setCurpProductor(bS[4]!=null&&!bS[4].isEmpty()&&!bS[4].equals("null")?bS[4]:"");
					pro.setRfcProductor(bS[5]!=null&&!bS[5].isEmpty()&&!bS[5].equals("null")?bS[5]:"");
					lstProductorPredioPagado.add(pro);	
				}else if(rca.getIdCriterio() == 7){//EXISTA POR UNIDAD DE PRODUCCIÓN AL MENOS UN REGISTRO DE BOLETA, FACTURA, PREDIO Y PAGO
					columna = 0;
					ProductoresIncosistentes p = new ProductoresIncosistentes();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					}
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setFolioPredio(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPredioAlterno(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setNumeroBoletas(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setNumeroFacturas(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setNumeroPagos(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));	
					lstProductoresIncosistentes.add(p);
				}else if(rca.getIdCriterio() == 8){//"BOLETAS NO ESTEN DUPLICADAS POR BODEGA"
					columna = 0;
					BoletasDuplicadas bo = new BoletasDuplicadas();
					bo.setClaveBodega(bS[columna]);
					++columna;
					bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");					
					if(siAplicaFolioContrato){
						++columna;
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					}
					++columna;
					bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setBoletaTicketBascula(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					++columna;
					bo.setVolBolTicket(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setFechaEntradaBoleta(!bS[columna].equals("null")&&!bS[columna].isEmpty()&&bS[columna]!=null?(Utilerias.convertirStringToDateyyyyMMdd(bS[columna])):null);
					lstBoletasDuplicadas.add(bo);					
				}else if(rca.getIdCriterio() == 9){
					columna = 0;
					if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
						BoletasFueraDePeriodo bo = new BoletasFueraDePeriodo();
						bo.setClaveBodega(bS[columna]);
						++columna;
						bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						if(siAplicaFolioContrato){
							++columna;
							bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
						}
						++columna;
						bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setBoletaTicketBascula(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setFechaEntradaBoleta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null);
						++columna;
						bo.setVolBolTicket(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						lstBoletasFueraDePeriodo.add(bo);										
					} else {
						BoletasFueraDePeriodoPago bo = new BoletasFueraDePeriodoPago();
						bo.setClaveBodega(bS[0]);
						bo.setNombreEstado(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
						int a = 2;
						if(siAplicaFolioContrato){
							bo.setFolioContrato(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						}
						bo.setPaternoProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						bo.setMaternoProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						bo.setNombreProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						bo.setCurpProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						bo.setRfcProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						bo.setBoletaTicketBascula(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						bo.setPeriodoInicialPago(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						bo.setPeriodoFinalPago(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						bo.setFechaEntradaBoleta(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						bo.setVolBolTicket(Double.parseDouble(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:"0"));
						lstBoletasFueraDePeriodoPago.add(bo);						
					}			
				}else if(rca.getIdCriterio() == 10){
					columna = 0;
					ProductorFactura p = new ProductorFactura();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}	
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setFolioFacturaVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setFechaEmisionFac(!bS[columna].equals("null")&&!bS[columna].isEmpty()&&bS[columna]!=null&&!bS[columna].equals("-")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null);
					lstProductorFactura.add(p);
				}else if(rca.getIdCriterio() == 11){
					columna = 0;
					BoletasVsFacturas bo = new BoletasVsFacturas();
					bo.setClaveBodega(bS[columna]);
					++columna;
					bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					++columna;
					bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setVolBolTicket(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
					++columna;
					bo.setDifVolumen(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
					lstBoletasVsFacturas.add(bo);
				}else if(rca.getIdCriterio() == 12){
					columna = 0;
					RfcProductorVsRfcFactura r = new RfcProductorVsRfcFactura();
					r.setClaveBodega(bS[columna]);
					++columna;
					r.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						r.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					++columna;
					r.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setCurpProductorBD(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setRfcProductorBD(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setLeyenda(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
					lstRfcProductorVsRfcFactura.add(r);
				}else if(rca.getIdCriterio() == 31){
					columna = 0;
					RelacionComprasTMP r = new RelacionComprasTMP();
					r.setClaveBodega(bS[columna]);
					++columna;
					r.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						r.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					++columna;
					r.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setRfcFacVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
					lstRfcProductorVsRfcFactura2.add(r);
				}else if(rca.getIdCriterio() == 3000){
					columna = 0;
					RelacionComprasTMP  r = new RelacionComprasTMP();
					r.setClaveBodega(bS[columna]);
					++columna;
					r.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");					
					++columna;
					r.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
					lstRfcProductorVsRfcFacturaSinContrato.add(r);
				}else if(rca.getIdCriterio() == 13){
					if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
						FacturaFueraPeriodoAuditor f  = new FacturaFueraPeriodoAuditor();
						f.setClaveBodega(bS[0]);
						f.setNombreEstado(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
						int a = 2;
						if(siAplicaFolioContrato){
							f.setFolioContrato(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						}					
						
						f.setPaternoProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setMaternoProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setNombreProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setCurpProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setRfcProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;						
						f.setFolioFacturaVenta(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setFechaEmisionFac(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						f.setVolTotalFacVenta(Double.parseDouble(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:"0"));
						lstFacturaFueraPeriodoAuditor.add(f);
					} else {
						FacturaFueraPeriodoPago f  = new FacturaFueraPeriodoPago();
						f.setClaveBodega(bS[0]);
						f.setNombreEstado(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
						int a = 2;
						if(siAplicaFolioContrato){
							f.setFolioContrato(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						}			
						f.setPaternoProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setMaternoProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setNombreProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setCurpProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setRfcProductor(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setFolioFacturaVenta(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:""); a++;
						f.setPeriodoInicialPago(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						f.setPeriodoFinalPago(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						f.setFechaEmisionFac(Utilerias.convertirStringToDateyyyyMMdd(bS[a])); a++;
						f.setVolTotalFacVenta(Double.parseDouble(bS[a]!=null&&!bS[a].isEmpty()&&!bS[a].equals("null")?bS[a]:"0"));
						
						lstFacturaFueraPeriodoPago.add(f);
					}
				}else if(rca.getIdCriterio() == 14){
					FacturasIgualesFacAserca f = new FacturasIgualesFacAserca();
					f.setFolioProductor(Long.parseLong(bS[0]));
					f.setPaternoProductor(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
					f.setMaternoProductor(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
					f.setNombreProductor(bS[3]!=null&&!bS[3].isEmpty()&&!bS[3].equals("null")?bS[3]:"");
					f.setRfcProductor(bS[4]!=null&&!bS[4].isEmpty()&&!bS[4].equals("null")?bS[4]:"");
					f.setRfcComprador(bS[5]!=null&&!bS[5].isEmpty()&&!bS[5].equals("null")?bS[5]:"");
					int c = 6;
					for(GruposCamposRelacionV l: rca.getLstGruposCamposDetalleRelacionV()){
						if(l.getIdCampo()==15){//folioFacturaVenta
							f.setFolioFacturaVenta(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"");
						}else if(l.getIdCampo()==16){//Fecha factura
							f.setFechaEmisionFac(Utilerias.convertirStringToDateyyyyMMdd(bS[c]));
						}else if(l.getIdCampo()==17){//Rfc factura
							f.setRfcFacVenta(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"");
						}else if(l.getIdCampo()==19){//P.N.A. SOLICITADO PARA APOYO (TON.)
							f.setVolSolFacVenta(Double.parseDouble(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"0"));
						}else if(l.getIdCampo()==65){//P.N.A. TOTAL DE LA FACTURA (TON.)
							f.setVolTotalFacVenta(Double.parseDouble(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"0"));
						}else if(l.getIdCampo()==20){//IMPORTE TOTAL FACTURADO ($)
							f.setImpSolFacVenta(Double.parseDouble(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"0"));
						}else if(l.getIdCampo()==70){//VARIEDAD
							f.setVariedad(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"");
						}else if(l.getIdCampo()==71){//CULTIVO
							f.setCultivo(bS[c]!=null&&!bS[c].isEmpty()&&!bS[c].equals("null")?bS[c]:"");
						}
						c++;
					}		
					lstFacturasIgualesFacAserca.add(f);
				}else if(rca.getIdCriterio() == 15){
					columna = 0;
					PrecioPagPorTipoCambio c = new PrecioPagPorTipoCambio();
					c.setClaveBodega(bS[columna]);
					++columna;
					c.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");				
					if(siAplicaFolioContrato){
						++columna;
						c.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					}
					++columna;
					c.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setFolioFacturaVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setFechaEmisionFac((bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null));
					++columna;
					c.setPrecioFacMxpTon(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setImpSolFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));					
					++columna;
					c.setPrecioPactadoPorTonelada(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setTipoCambio(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setImporteCalculadoPagar(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setDifMontoXFac(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setDifMontoTotal(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					lstPrecioPagPorTipoCambio.add(c);					
				}else if(rca.getIdCriterio() == 16){
					columna = 0;
					ChequesDuplicadoBancoPartipante c = new ChequesDuplicadoBancoPartipante();
					c.setClaveBodega(bS[columna]);
					++columna;
					c.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");				
					if(siAplicaFolioContrato){
						++columna;
						c.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					}
					++columna;
					c.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setFolioDocPago(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setBancoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					c.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					lstChequesDuplicadoBancoPartipante.add(c);			
				}else if(rca.getIdCriterio() == 17){//"Cheques fuera del periodo del Auditor (solo aplica AXC)."
					columna = 0;
					if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
						ChequeFueraPeriodoAuditor ch = new ChequeFueraPeriodoAuditor();
						ch.setClaveBodega(bS[columna]);
						++columna;
						ch.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						if(siAplicaFolioContrato){
							++columna;
							ch.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
						}
						++columna;
						ch.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");						
						++columna;
						ch.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setFolioDocPago(bS[columna]);
						++columna;
						ch.setBancoSinaxc(bS[columna]);
						++columna;
						ch.setFechaDocPagoSinaxc((bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null));				
						++columna;
						ch.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
//						++columna;
//						ch.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
						lstChequeFueraPeriodoAuditor.add(ch);
					}else{
						ChequeFueraPeriodoContrato ch = new ChequeFueraPeriodoContrato();
						ch.setClaveBodega(bS[columna]);
						++columna;
						ch.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						if(siAplicaFolioContrato){
							++columna;
							ch.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
						}
						++columna;
						ch.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						ch.setPeriodoInicialPago((bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null));
						++columna;
						ch.setPeriodoFinalPago((bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null));
						++columna;
						ch.setFolioDocPago(bS[columna]);
						++columna;
						ch.setBancoSinaxc(bS[columna]);
						++columna;
						ch.setFechaDocPagoSinaxc((bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null));				
						++columna;
						ch.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
//						++columna;
//						ch.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
						listChequeFueraPeriodoContrato.add(ch);
					}
					
				}else if(rca.getIdCriterio() == 18){
					columna = 0;
					FacturasVsPago f = new FacturasVsPago();
					f.setClaveBodega(bS[columna]);
					++columna;
					f.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						f.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}
					++columna;
					f.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					f.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					f.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					f.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					f.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					f.setImpSolFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					
					f.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					f.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					
					lstFacturasVsPago.add(f);
				}else if(rca.getIdCriterio() == 19){
					columna = 0;
					RelacionComprasTMP p = new RelacionComprasTMP();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}					
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNumeroFacGlobal(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");					
					++columna;
					p.setVolPnaFacGlobal(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setDifVolumenFglobalVSFind(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));					
					lstFGlobalVsFIndividual.add(p);
				}else if(rca.getIdCriterio() == 20){					
					columna = 0;
					BoletasFacturasPagosIncosistentes p = new BoletasFacturasPagosIncosistentes();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}					
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setVolTotalFacturado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolBolTicket(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolEnpagos(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolumenNoProcedente(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setDiferenciaEntreVolumen(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setDiferenciaEntreImportes(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setDiferenciaEntreRFC(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPredioNoPagado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPredioInconsistente(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setDifFacGlobalIndividual(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolumenObservado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					lstBoletasFacturasPagosIncosistentes.add(p);
				}else if(rca.getIdCriterio() == 21){
					columna = 0;
					GeneralToneladasTotalesPorBodFac bo = null;
					GeneralToneladasTotPorBodega b1 = null;
					if(bS[0].equals("1")){
						 bo  = new GeneralToneladasTotalesPorBodFac();
						//La columna 0, determina el tipo de reporte
						++columna;
						if(siAplicaFolioContrato){
							bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
							++columna;
						}								
						 bo.setClaveBodega(bS[columna]);
						++columna;
						bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
						bo.setPesoNetoAnaBol(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						bo.setPesoNetoAnaFac(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						bo.setTotalBoletas(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						bo.setTotalFacturas(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						bo.setTotalRegistros(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						bo.setNumeroProdBenef(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					}else{
						b1  = new GeneralToneladasTotPorBodega();
						++columna;
						
						b1.setClaveBodega(bS[columna]);	
						System.out.println("Bodega "+bS[columna]);
						//La columna 0, determina el tipo de reporte
						if(siAplicaFolioContrato){
							++columna;
							b1.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
							System.out.println("Folio "+bS[columna]);
						}								
						++columna;
						b1.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						System.out.println("Estado "+bS[columna]);
						++columna;
						System.out.println("Peso"+bS[columna]);
						b1.setPesoNetoAnaBol(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						b1.setPesoNetoAnaFac(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						b1.setTotalBoletas(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						b1.setTotalFacturas(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						b1.setTotalRegistros(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
						++columna;
						b1.setNumeroProdBenef(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					}									
					if(bS[0].equals("1")){
						lstGeneralToneladasTotalesPorBodFac.add(bo);
					}else{						
						lstGeneralTonTotPorBodega.add(b1);
					}
										
				}else if(rca.getIdCriterio() == 22){//VALORES NULOS EN BOLETAS	
					columna = 0;
					BoletasCamposRequeridos bo = new BoletasCamposRequeridos();
					bo.setClaveBodega(bS[columna]);
					++columna;
					bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}
					++columna;
					bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setBoletaTicketBascula(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(rca.getCamposQueAplica().contains("12,")){
						++columna;
						bo.setFechaEntrada(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");						
					}
					if(rca.getCamposQueAplica().contains("63,")){
						++columna;
						bo.setVolumen(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					lstBoletasCamposRequeridos.add(bo);					
				}else if(rca.getIdCriterio() == 23){//VALORES NULOS EN FACTURAS
					columna = 0;
					FacturasCamposRequeridos bo = new FacturasCamposRequeridos();
					bo.setClaveBodega(bS[columna]);
					++columna;
					
					bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}
					++columna;
					bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setFolioFacturaVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(rca.getCamposQueAplica().contains("16,")){
						++columna;
						bo.setFechaEmisionFac(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						
					}
					if(rca.getCamposQueAplica().contains("17,")){
						++columna;
						bo.setRfcFacVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						
					}
					if(rca.getCamposQueAplica().contains(",19,")){
						++columna;
						bo.setVolSolFacVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					if(rca.getCamposQueAplica().contains("65,")){
						++columna;
						bo.setVolTotalFacVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					if(rca.getCamposQueAplica().contains("20,")){
						++columna;
						bo.setImpSolFacVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					if(rca.getCamposQueAplica().contains("70,")){
						++columna;
						bo.setVariedad(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					lstFacturasCamposRequeridos.add(bo);
					
				}else if(rca.getIdCriterio() == 24){
					columna = 0;
					PagosCamposRequeridos bo = new PagosCamposRequeridos();
					bo.setClaveBodega(bS[columna]);
					++columna;
					bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}
					++columna;
					bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setFolioDocPago(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(rca.getCamposQueAplica().contains("67,")){
						++columna;
						bo.setImpTotalPagoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					if(rca.getCamposQueAplica().contains("27,")){
						++columna;
						bo.setTipoDdocPago(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					if(rca.getCamposQueAplica().contains("16,")){
						++columna;
						bo.setFechaDocPagoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						
					}
					if(rca.getCamposQueAplica().contains("29,")){
						++columna;
						bo.setBancoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}
					if(rca.getCamposQueAplica().contains("66,")){
						++columna;
						bo.setImpDocPagoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}	
					if(rca.getCamposQueAplica().contains("68,")){
						++columna;
						bo.setImpPrecioTonPagoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}	
					lstPagosCamposRequeridos.add(bo);
				}else if(rca.getIdCriterio() == 29){
					columna = 0;
					PagMenorCompBases bo = new PagMenorCompBases();
					bo.setClaveBodega(bS[columna]);
					++columna;
					bo.setNombreEstado(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(siAplicaFolioContrato){
						++columna;
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}
					++columna;
					bo.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					bo.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");	
					++columna;
					bo.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setImpSolFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setImpCompTonAXC(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setCuota(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setPagoMenorCuota(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setCompBaseMenorTotal(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					bo.setPagoMenor(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));								
					lstPagMenorCompBases.add(bo);	
				}else if(rca.getIdCriterio() == 25){					
					columna = 0;
					
					RendimientosProcedente p = new RendimientosProcedente();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}					
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setVolTotalFacturado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setRendimientoMaximoAceptable(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolNoProcedente(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"); // AHS [LINEA] - 17022015
					lstRendimientosProcedente.add(p);
				}else if(rca.getIdCriterio() == 32){					
					columna = 0;
					VolumenNoProcedente p = new VolumenNoProcedente();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}					
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setIdVariedad(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolTotalFacturado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolApoyadoEnRegional(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolNoProcedente(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0")); // AHS [LINEA] - 17022015
					lstVolNoProcedenteYApoyEnReg.add(p);
				}else if (rca.getIdCriterio() == 27){
					columna = 0;
					VolumenFiniquito p = new VolumenFiniquito();
					p.setClaveBodega(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}
					++columna;
					p.setNombreComprador(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreVendedor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setPrecioPactadoPorTonelada(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolumen(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setDifVolumenFiniquito(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setModalidad(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("0")?bS[columna]:"");					
					lstVolumenCumplido.add(p);
				}else if(rca.getIdCriterio() == 28){
					columna = 0;
					PrecioPagadoNoCorrespondeConPagosV p = new PrecioPagadoNoCorrespondeConPagosV();
					p.setClaveBodega(bS[columna]);
					++columna;
					p.setNombreEstado(bS[columna]);
					if(siAplicaFolioContrato){
						++columna;
						p.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:""); 
					}					
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setCurpProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImpSolFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImpDocPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImpPrecioTonPagiSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPrecioCalculado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPrecioPagadoEnAviso(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					lstPrecioPagadoMenorQueAviso.add(p);
				}
					
			}		
		}//end bitacora
		
	}

	private float[] configurarNumColumnaEnTabla() {
		float[] w = {1};
		if(rca.getIdCriterio() == 1){//Valida que exista predio en base de datos 7.4
			//float[] x1 = {15,10,30,15,15,15}; // % //antes clave bodega 
			float[] x1 = {10,30,20,20,20}; // % 
			w = x1;
		}else if(rca.getIdCriterio() == 2){//"Predio se encuentre validado por la DR/DE"
			float[] x1 = {35,30,35}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 3){//"Predios esten pagados"
			float[] x1 = {35,30,35}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 26){//Valida que exista predios/productores/contratos en base de datos
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,10,20,14,14,15}; // %
				w = x1;
			}else{
				float[] x1 = {8,5,30,14,14,15}; // %
				w = x1;
			}			
		}else if(rca.getIdCriterio() == 30){// 7.6 CURP, RFC Y/O NOMBRES INCONSISTENTES
			if(siAplicaFolioContrato){
				float[] x1 = {10,10,10,30,20,20}; // %
				w = x1;
			}else{
				float[] x1 = {10,10,30,25,25}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 5){//Productores Estén asociados a Predios Validados
			float[] x1 = {25,25,25,25}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 6){//Productores Estén Asociados a Predios Pagados
			float[] x1 = {25,25,25,25}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 7){//productores incosistentes
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,10,30,15,8,8,8,8}; // %
				w = x1;
			}else{
				float[] x1 = {8,5,32,15,10,10,10,10}; // %
				w = x1;
			}			
		}else if(rca.getIdCriterio() == 8){//"Boletas no esten duplicadas por bodega"
			if(siAplicaFolioContrato){
				
				float[] x1 = {10,5,10,27,15,11,11,11}; // %
				w = x1;
			} else {
				float[] x1 = {10,5,25,15,15,15,15}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 9){//"Boletas fuera del periodo de acopio"
			if(siAplicaFolioContrato){
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {10,10,10,30,10,15,15}; // %
					w = x1;					
				} else {
					float[] x1 = {8,5,8,17,14,8,8,8,8,8}; // %
					w = x1;
				}
			} else {
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {8,5,27,15,15,15,15}; // % //COn rfc
					w = x1;
				} else {
					float[] x1 = {10,10,30,10,10,10,10,10}; // %
					w = x1;					
				}
			}
		
		}else if(rca.getIdCriterio() == 10){//"No se duplique el folio de la factura"
 			if(siAplicaFolioContrato){
				float[] x1 = {8,5,8,20,14,29,8,8,}; // %
				w = x1;
			} else {
				float[] x1 = {8,5,25,16,30,8,8}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 11){//"La suma total de la facturas por productor no sea mayor a la suma de las Boletas"
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,8,35,15,10,10,10}; // %
				w = x1;
			}else{
				float[] x1 = {15,10,30,15,10,10,10}; // %
				w = x1;
			}			
		}else if(rca.getIdCriterio() == 12){//"Que el rfc corresponda al productor"
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,8,20,10,10,10,10,10,9}; // %
				w = x1;
			}else{
				float[] x1 = {8,5,8,20,12,12,12,12,12}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 31){//"Que el rfc productor vs rfc factura"
			if(siAplicaFolioContrato){
				float[] x1 = {10,10,10,20,20,20,10}; // %
				w = x1;
			}else{
				float[] x1 = {10,10,20,20,20,20}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 3000){//"Que el rfc corresponda al productor sin contrato"
				if(siAplicaFolioContrato){
					float[] x1 = {10,10,10,20,20,20,10}; // %
					w = x1;
				}else{
					float[] x1 = {10,10,20,20,20,20}; // %
					w = x1;
				}	
		}else if(rca.getIdCriterio() == 13){//"Factura fuera del periodo del Auditor"
			if(siAplicaFolioContrato){
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {8,5,8,29,15,10,10,15}; // % listo curp
					w = x1;					
				} else {
					float[] x1 = {8,5,8,20,10,20,7,7,7,7}; // % listo Curp
					w = x1;
				}
			} else {
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {10,5,25,15,25,10,10}; // % listo curp
					w = x1;					
				} else {
					float[] x1 = {10,5,20,15,18,8,8,8,8}; // % listo curp
					w = x1;					
				}
			}
		}else if(rca.getIdCriterio() == 14){//VALIDAR QUE SEA MISMO FOLIO, RFC, VOLUMEN, IMPORTE AL REGISTRADO EN BDD
			StringBuilder composicionArreglo = new StringBuilder();	
			composicionArreglo.append("20,10,10,");
			double c = 60/rca.getLstGruposCamposDetalleRelacionV().size();			
			for(int i = 1; i<=rca.getLstGruposCamposDetalleRelacionV().size(); i++){
				composicionArreglo.append(c).append(",");
			}			
			composicionArreglo.deleteCharAt(composicionArreglo.length()-1);		
			String [] arrayS = composicionArreglo.toString().split(",");
			float[] x = new float[arrayS.length];
			for(int i = 0; i < arrayS.length; i++){
				x[i] = Float.parseFloat(arrayS[i]);
			}		
			w = x;			
		}else if(rca.getIdCriterio() == 15){//PRECIO PAGADO AL PRODUCTOR DEBERÁ SER IGUAL AL TIPO DE CAMBIO DE LA  FECHA  DE LA FACTURA
			if(siAplicaFolioContrato){
				float[] x1 = {6,4,6,10,12,7,7,7,7,7,7,7,7,7}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 16){//"NO SE REPITAN CHEQUES-BANCO POR EMPRESA"
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,10,30,15,10,12,10}; // % Con rfc
				w = x1;
			} else {
				float[] x1 = {8,5,40,17,10,10,10}; // % Con rfc
				w = x1;
			}
		}else if(rca.getIdCriterio() == 17){//"CHEQUES FUERA DEL PERIODO DEL AUDITOR (SOLO APLICA AXC)."
			if(siAplicaFolioContrato){
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
//					float[] x1 = {15,10,10,15,10,10,10,10,10}; // %
					float[] x1 = {5,5,28,12,10,10,10,10,10}; // % listo curp/rfc
					w = x1;					
				} else {
//					float[] x1 = {10,5,10,15,10,10,10,10,8,8,8}; // %
					float[] x1 = {8,5,8,16,14,8,8,8,8,8,8}; // % listo curp rfc
					w = x1;
				}				
			}else{
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
//					float[] x1 = {15,10,25,10,10,10,10,10};  // %
					float[] x1 = {8,5,33,14,10,10,10,10};  // % listo curp rfc
					w = x1;
				} else {
//					float[] x1 = {10,5,20,10,10,10,10,8,8,8};  // %
					float[] x1 = {10,5,29,10,10,10,10,8,8};  // % listo curp rfc
					w = x1;					
				}
				
			}
			
		}else if(rca.getIdCriterio() == 18){//"MONTO TOTAL PAGADO SEA MAYOR A LA SUMA DE LOS TOTALES PAGADOS AL PRODUCTOR (FACTURA,CB)."
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,8,35,14,10,10,10 }; // %
				w = x1;
			}else{
				float[] x1 = {8,5,27,15,15,15,15}; // %
				w = x1;
			}
			
		}else if(rca.getIdCriterio() == 19){//"5.5 DIFERENCIA DE VOLUMEN FACTURA GLOBAL VS FACTURAS INDIVIDUALES"			
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,10,20,14,15,5,5,5}; // %
				w = x1;
			}else{
				float[] x1 = {8,5,15,14,15,10,10,10}; // %
				w = x1;
			}	
		}else if(rca.getIdCriterio() == 20){//"BOLETAS, FACTURAS Y PAGOS INCONSISTENTES EN PRODUCTORES
			//if(siAplicaFolioContrato){
				double c = 0;	
				StringBuilder composicionArreglo = new StringBuilder();
				if(siAplicaFolioContrato){
					composicionArreglo.append("8,3,8,16,11,6,6,6,6,6,6,6,6,"); //Campos Default
				}else{
					composicionArreglo.append("8,5,20,10,6,6,6,6,6,6,6,6,"); //Campos Default
				}
				if(rca.getCriteriosByPrograma().contains( ",1,")){
					reporteAplica =  ",1,";
					composicionArreglo.append("6,");
				}
				if(rca.getCriteriosByPrograma().contains( ",26,")){
					reporteAplica =  ",26,";
					composicionArreglo.append("6,");
				}
				if(rca.getCriteriosByPrograma().contains(",19,")){
					reporteAplica += ",19,";
					composicionArreglo.append("6,");
				}	
				composicionArreglo.append("6,");
//				String [] arrayCamposQueAplica = reporteAplica.split(",");
//				
//				c = 49/arrayCamposQueAplica.length;
//				
//				for(int i = 1; i<=arrayCamposQueAplica.length; i++){
//					composicionArreglo.append(c).append(",");
//				}				
//				composicionArreglo.deleteCharAt(composicionArreglo.length()-1);		
				
				String [] arrayS = composicionArreglo.toString().split(",");
				float[] x = new float[arrayS.length];
				for(int i = 0; i < arrayS.length; i++){ 
					x[i] = Float.parseFloat(arrayS[i]);
				}		
				w = x;				
//			} else {				
//				if(rca.getCriteriosByPrograma().contains( ",1,")){
//					float[] x1 = {8,5,17,10,6,6,6,6,6,6,6,6,6,6}; // % Reporte de predios no pagados
//					w = x1;
//				}else{
//					float[] x1 = {8,5,23,10,6,6,6,6,6,6,6,6,6}; // %
//					w = x1;
//				}				
//				
//			}
		}else if(rca.getIdCriterio() == 21){//"//TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS
			if(siAplicaFolioContrato){
				float[] x1 = {11,11,11,11,11,11,11,11,11}; // %
				w = x1;
			}else{
				float[] x1 = {12,12,12,12,12,12,12,12}; // %
				w = x1;
			}		
		
		}else if(rca.getIdCriterio() == 22){//"VALORES NULOS EN BOLETA" 
			StringBuilder composicionArreglo = new StringBuilder();	
			double c = 0;
			String [] arrayCamposQueAplica = rca.getCamposQueAplica().split(",");
			if(siAplicaFolioContrato){
				composicionArreglo.append("10,5,10,20,15,");
				c = 55/arrayCamposQueAplica.length;
			}else{
				composicionArreglo.append("8,5,20,14,");
				c = 45/arrayCamposQueAplica.length;
			}
							
			for(int i = 1; i<=arrayCamposQueAplica.length; i++){
				composicionArreglo.append(c).append(",");
			}
			composicionArreglo.deleteCharAt(composicionArreglo.length()-1);		
			String [] arrayS = composicionArreglo.toString().split(",");
			float[] x = new float[arrayS.length];
			for(int i = 0; i < arrayS.length; i++){ 
				x[i] = Float.parseFloat(arrayS[i]);
			}		
			w = x;			
		}else if(rca.getIdCriterio() == 23){//VALORES NULOS EN FACTURAS
			StringBuilder composicionArreglo = new StringBuilder();	
			double c = 0;
			String [] arrayCamposQueAplica = rca.getCamposQueAplica().split(",");
			if(siAplicaFolioContrato){
				composicionArreglo.append("8,5,8,15,10,20,");
				c = 55/arrayCamposQueAplica.length;
			}else{
				composicionArreglo.append("8,5,18,12,20,");
				c = 45/arrayCamposQueAplica.length-1;//Menos el campo de la factura con idcampo = 15
			}
						
			for(int i = 1; i<=arrayCamposQueAplica.length-1; i++){//Menos el campo de la factura con idcampo = 15				
				composicionArreglo.append(c).append(",");
			} 
			composicionArreglo.deleteCharAt(composicionArreglo.length()-1);		
			String [] arrayS = composicionArreglo.toString().split(",");
			float[] x = new float[arrayS.length];
			for(int i = 0; i < arrayS.length; i++){
				x[i] = Float.parseFloat(arrayS[i]);
			}		
			w = x;	
			
		}else if(rca.getIdCriterio() == 24){//VALORES NULOS EN PAGOS
			StringBuilder composicionArreglo = new StringBuilder();	
			double c = 0;
			String [] arrayCamposQueAplica = rca.getCamposQueAplica().split(",");
			if(siAplicaFolioContrato){
				composicionArreglo.append("10,5,10,20,14,");
				c = 49/arrayCamposQueAplica.length;
			}else{
				composicionArreglo.append("8,5,20,12,");
				c = 45/arrayCamposQueAplica.length;
			}					
			for(int i = 1; i<=arrayCamposQueAplica.length; i++){
				composicionArreglo.append(c).append(",");
			}
			composicionArreglo.deleteCharAt(composicionArreglo.length()-1);		

			String [] arrayS = composicionArreglo.toString().split(",");
			float[] x = new float[arrayS.length];
			for(int i = 0; i < arrayS.length; i++){
				x[i] = Float.parseFloat(arrayS[i]);
			}		
			w = x;		
		}else if(rca.getIdCriterio() == 25){//VOLUMEN NO PROCEDENTE
			if(siAplicaFolioContrato){
				float[] x1 = {10,5,10,20,15,10,10,10 }; // %
				w = x1;
			}else{
				float[] x1 = {15,10,30,15,15,15 }; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 29){//9.1 PAGO MENOR DE LA COMPENSACIÓN DE BASES
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,8,12,10,5,7,7,7,7,7,7,7}; // %
				w = x1;
			}else{
				float[] x1 = {8,5,17,15,6,6,6,6,6,6,6,6}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 32){//VOLUMEN NO PROCEDENTE VARIEDAD
			if(siAplicaFolioContrato){
				float[] x1 = {8,5,8,25,14,10,10,10,10 }; // %
				w = x1;
			}else{
				float[] x1 = {8,5,33,14,10,10,10,10 }; // % con curp
				w = x1;
			}
		}else if(rca.getIdCriterio() == 27){//VOLUMEN DE FINIQUITO
			if(siAplicaFolioContrato){
				float[] x1 = {8,8,20,20,10,10,10,10,6}; // %
				w = x1;
			}else{
				float[] x1 = {10,20,20,10,10,10,10,10}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 28){//PRECIO MENOR AL ESTABLECIDO EN AVISO
			if(siAplicaFolioContrato){
				float[] x1 = {10,5,10,15,10,10,10,10,10,10}; // % Sin RFC
				w = x1;
			}else{
				float[] x1 = {8,5,17,14,8,8,8,8,8,8,8}; // % listo con RFC
				w = x1;
			}	
		}
		
		return w;
		
	}

	private void colocarPrimerasColumnasDetalle(
		String [] configTotales, int numColumnas){		
		if(contBitacoraDet != 0){
			if(siAplicaFolioContrato){					
				if(!temFolioContrato.equals(folioContrato) && contBitacoraDet > 1){
					if(rca.getIdCriterio()!=8){
						if(configTotales!=null){
							colocarTotales(configTotales[0], numColumnas);
						}
						
					}					
					totalVolPorContrato = 0;						
					totalVolPorContrato1 = 0;						
					totalVolPorContrato2 = 0;						
					totalVolPorContrato3 = 0;						
					totalVolPorContrato4 = 0;						
					totalVolPorContrato5 = 0;						
					totalVolPorContrato6 = 0;						
				}
			}					
			if(!claveBodegaTmp.equals(claveBodega) && contBitacoraDet > 1){
				if(configTotales!=null){					
					if(siAplicaFolioContrato && temFolioContrato.equals(folioContrato)){
						colocarTotales(configTotales[0], numColumnas);
					}					
					colocarTotales(configTotales[1], numColumnas);
				}										
				totalVolPorBodega = 0;						
				totalVolPorBodega1 = 0;						
				totalVolPorBodega2 = 0;						
				totalVolPorBodega3 = 0;						
				totalVolPorBodega4 = 0;						
				totalVolPorBodega5 = 0;						
				totalVolPorBodega6 = 0;	
				if(siAplicaFolioContrato){
					totalVolPorContrato = 0;						
					totalVolPorContrato1 = 0;						
					totalVolPorContrato2 = 0;						
					totalVolPorContrato3 = 0;						
					totalVolPorContrato4 = 0;						
					totalVolPorContrato5 = 0;						
					totalVolPorContrato6 = 0;
				}
			}
		}				
		if(!claveBodegaTmp.equals(claveBodega)){
			parrafo =  new Paragraph(claveBodega, TIMESROMAN08);
		}else{
			parrafo =  new Paragraph("", TIMESROMAN08);
		}				
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);							
		if(!nomEstadoTmp.equals(nombreEstado)){
			parrafo =  new Paragraph(nombreEstado, TIMESROMAN08);
		}else{
			parrafo =  new Paragraph("", TIMESROMAN08);
		}				
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		if(siAplicaFolioContrato){
			if(!temFolioContrato.equals(folioContrato)){					
				parrafo =  new Paragraph(folioContrato, TIMESROMAN08);			
			}else if(!claveBodegaTmp.equals(claveBodega)){
				parrafo =  new Paragraph(folioContrato, TIMESROMAN08);								
			}else{
				parrafo =  new Paragraph("", TIMESROMAN08);								
			}				
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}
		parrafo =  new Paragraph(paternoProductor+" "+maternoProductor+" "+nombreProductor, TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph((curpProductor!=null?curpProductor:rfcProductor), TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
	}

	private void colocarTotales(String configuracion, int tamanio ){
		 String[] numElementos = configuracion.split(";");
		 int contador = 0, contadorTemp = 0;
		 for(int i=0; i < numElementos.length; i++){
			 String[] subElementos = numElementos[i].split(",");
			 contador=Integer.parseInt(subElementos[0]);		
			 for(int j= contadorTemp; j<contador-1; j++){				 
				 parrafo =  new Paragraph("", TIMESROMAN08);
				 cell = new PdfPCell(parrafo);
				 cell =createCell(parrafo, 0, 3, 1,null, 2);
				 t.addCell(cell);
			 }
			 if(i != 0){
				 if (subElementos[2].equals("v")){
					 parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(Double.parseDouble(subElementos[1])), TIMESROMAN08); 
				 }else if (subElementos[2].equals("v6")){
					 parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumenSeisDec(Double.parseDouble(subElementos[1])), TIMESROMAN08); // AHS [LINEA] - 17022015 
				 }else if(subElementos[2].equals("i")){
					 parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(Double.parseDouble(subElementos[1])), TIMESROMAN08);
				 }else{
					 Double valor = Double.parseDouble(subElementos[1]);
					 parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(valor.intValue()), TIMESROMAN08); 
				 }
				 			 
			 }else{
				 parrafo =  new Paragraph(subElementos[1], TIMESROMAN08); 
			 }			 
			 cell = new PdfPCell(parrafo);
			 cell =createCell(parrafo, 0, 3, 1,null, 2);
			 t.addCell(cell);
			 contadorTemp = contador;	
		 }		 
		 for(int i=contadorTemp; i< tamanio; i++){
			 parrafo =  new Paragraph("", TIMESROMAN08);
			 cell = new PdfPCell(parrafo);
			 cell =createCell(parrafo, 0, 3, 1,null, 2);
			 t.addCell(cell);
		 }	 				
	}
		
	private void creaEncabezadoDetalle(int tipo) {
		if(rca.getIdCriterio() == 1){//Valida que exista predio en base de datos 7.4
			crearColumna("S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-PREDIO;S-3-OBSERVACIÓN;", "DET");
//			parrafo =  new Paragraph("ESTADO", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
//			parrafo =  new Paragraph("PRODUCTOR", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
//			parrafo =  new Paragraph("CURP/RFC", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
//			parrafo =  new Paragraph("PREDIO", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
//			parrafo =  new Paragraph("OBSERVACIÓN", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
//			
			
		}else if(rca.getIdCriterio() == 2){//"Predio se encuentre validado por la DR/DE"
			parrafo =  new Paragraph("PREDIO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("SECUENCIAL", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PREDIO ALTERNO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 26){//Valida que exista predios/productores/contratos en base de datos
			colocarPrimerasColumnasEncDetalle();
//			parrafo =  new Paragraph("NOMBRE PRODUCTOR", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 3, 1);
//			t.addCell(cell);			
			parrafo =  new Paragraph("PREDIO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);			
			parrafo =  new Paragraph("CARTA ADHESIÓN", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 30){// 7.6 CURP, RFC Y/O NOMBRES INCONSISTENTES
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP;S-1-RFC;", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP;S-1-RFC;", "DET");
			}				
		}else if(rca.getIdCriterio() == 4){//"PRODUCTOR EXISTA EN BASE DE DATOS"
			parrafo =  new Paragraph("FOLIO PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("CURP", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);				
		}else if(rca.getIdCriterio() == 5){//"Productores Estén asociados a Predios Validados"
			parrafo =  new Paragraph("FOLIO PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("CURP", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);		
		}else if(rca.getIdCriterio() == 6){//Productores Estén Asociados a Predios Pagados
			parrafo =  new Paragraph("FOLIO PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("CURP", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 7){//EXISTA POR UNIDAD DE PRODUCCIÓN AL MENOS UN REGISTRO DE BOLETA, FACTURA, PREDIO Y PAGO
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("PREDIOS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
//			parrafo =  new Paragraph("PREDIO ALTERNO", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
			parrafo =  new Paragraph("BOLETAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("FACTURAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PAGOS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 8){//BOLETAS O TICKETS DE BASCULA DUPLICADOS POR BODEGA
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-BOLETA TICKET;S-3-PESO NETO ANA. (TON);S-3-FECHA DE ENTRADA;", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-BOLETA TICKET;S-3-PESO NETO ANA. (TON);S-3-FECHA DE ENTRADA;", "DET");
			}				
		}else if(rca.getIdCriterio() == 9){//"BOLETAS FUERA DE PERIODO DE ACOPIO "
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("BOLETA TICKET", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			if(!rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				parrafo =  new Paragraph("FECHA INI", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph("FECHA FIN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			parrafo =  new Paragraph("FECHA ENTRADA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PESO NETO ANA. (TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);			
		}else if(rca.getIdCriterio() == 10){ // 5.1 FACTURAS DUPLICADAS
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-FOLIO FISCAL DE LA FACTURA;S-3-PESO NETO ANA. (TON);S-3-FECHA DE FACTURA;", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-FOLIO FISCAL DE LA FACTURA;S-3-PESO NETO ANA. (TON);S-3-FECHA DE FACTURA;", "DET");
			}	
		}else if(rca.getIdCriterio() == 11){//"LA SUMA TOTAL DE LA FACTURAS POR PRODUCTOR NO SEA MAYOR A LA SUMA DE LAS BOLETAS"
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("VOLUMEN DE BOLETAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("VOLUMEN DE FACTURAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);	
			parrafo =  new Paragraph("DIFERENCIA DE VOLUMEN", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 12){//"QUE EL RFC CORRESPONDA AL PRODUCTOR"			
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-EDO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP REL;S-1-CURP BD;S-1-RFC REL;"
						+ "S-1-RFC BD;S-1-LEYENDA;S-3-PESO NETO ANA. (TON) FAC;", "DET");
				}
		}else if(rca.getIdCriterio() == 31){//"QUE EL RFC CORRESPONDA AL PRODUCTOR"
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("RFC PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);	
			parrafo =  new Paragraph("PESO NETO ANA. (TON) FAC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);	
		}else if(rca.getIdCriterio() == 30){//"QUE EL RFC CORRESPONDA AL PRODUCTOR SIN CONTRATO"
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("RFC PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("CURP PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);	
			parrafo =  new Paragraph("PESO NETO ANA. (TON) FAC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);	
		}else if(rca.getIdCriterio() == 13){//"Factura fuera del periodo del Auditor"
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("FOLIO FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			if(!rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				parrafo =  new Paragraph("FECHA INI", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph("FECHA FIN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}			
			parrafo =  new Paragraph("FECHA EMISIÓN", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PESO NETO ANA. (TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);			
		}else if(rca.getIdCriterio() == 14){
			parrafo =  new Paragraph("PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC COMPRADOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);				
			for(GruposCamposRelacionV l: rca.getLstGruposCamposDetalleRelacionV()){
				if(l.getIdCampo()==15){//folioFacturaVenta
					parrafo =  new Paragraph("FACTURA VENTA", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==16){//Fecha factura
					parrafo =  new Paragraph("FECHA EMISIÓN", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==17){//Rfc factura
					parrafo =  new Paragraph("RFC FACTURA", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==19){//P.N.A. SOLICITADO PARA APOYO (TON.)
					parrafo =  new Paragraph("P.N.A. SOLICITADO PARA APOYO (TON.)", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==65){//P.N.A. TOTAL DE LA FACTURA (TON.)
					parrafo =  new Paragraph("P.N.A. TOTAL DE LA FACTURA (TON.)", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==20){//IMPORTE TOTAL FACTURADO ($)
					parrafo =  new Paragraph("IMPORTE TOTAL FACTURADO", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==70){//VARIEDAD
					parrafo =  new Paragraph("VARIEDAD", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}else if(l.getIdCampo()==71){//CULTIVO
					parrafo =  new Paragraph("CULTIVO", TIMESROMAN08BOLD);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
				}
			}				
		}else if(rca.getIdCriterio() == 15){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-EDO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-FOLIO FACTURA;S-3-FECHA FACTURA;S-3-PNA TOT FAC POR CON;"
						+ "S-3-PRECIO FAC MXP X TON;S-3-TOTAL PAG FAC EN DIC AUD;S-3-PREC PACTADO POR TON(DLLS);S-3-TIPO DE CAMBIO;"
						+ "S-3-TOTAL A PAGAR X FAC;S-3-DIF MONTO X FAC;S-3-DIF MONTO TOTAL;", "DET");
				}					
		}else if(rca.getIdCriterio() == 16){//"NO SE REPITAN CHEQUES-BANCO POR EMPRESA"
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-FOLIO DOC PAGO;S-3-BANCO;S-3-PNA TOTAL DE LA FAC(TON.);", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-FOLIO DOC PAGO;S-3-BANCO;S-3-PNA TOTAL DE LA FAC(TON);", "DET");
			}	
		}else if(rca.getIdCriterio() == 17){//"CHEQUES FUERA DEL PERIODO DEL AUDITOR (SOLO APLICA AXC)."
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("PNA TOTAL DE LA FAC(TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			if(!rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				parrafo =  new Paragraph("FECHA INI", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph("FECHA FIN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			parrafo =  new Paragraph("FOLIO DOC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("BANCO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1); 
			t.addCell(cell);
			parrafo =  new Paragraph("FECHA DOC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			
//			parrafo =  new Paragraph("POLIZA CHEQUE", TIMESROMAN08BOLD);
//			cell = new PdfPCell(parrafo);
//			cell =createCell(parrafo, 0, 1, 1);
//			t.addCell(cell);
		}else if(rca.getIdCriterio() == 18){
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("IMPORTE FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("IMPORTE PAGADO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PESO NETO ANA (FAC)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 19){//5.5 DIFERENCIA DE VOLUMEN FACTURA GLOBAL VS FACTURAS INDIVIDUALES"			
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CUPR/RFC;S-1-FAC GLOBAL;S-3-PNA TOTAL EN FAC GLOBAL;S-3-PNA TOTAL EN FAC;S-3-DIF VOLUMEN", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CUPR/RFC;S-1-FAC GLOBAL;S-3-PNA TOTAL EN FAC GLOBAL;S-3-PNA TOTAL EN FAC;S-3-DIF VOLUMEN", "DET");
			}				
		}else if(rca.getIdCriterio() == 20){//BOLETAS, FACTURAS Y PAGOS INCOSISTENTES EN PRODUCTORES
			StringBuilder encabezado = new StringBuilder();	
//			if(siAplicaFolioContrato){				
				encabezado.append("S-1-BODEGA;S-1-EDO;");
				if(siAplicaFolioContrato){
					encabezado.append("S-1-FOLIO CONTRATO;");
				}
				encabezado.append("S-1-PRODUCTOR;S-1-CURP/RFC;S-3-PESO NETO ANA TOTAL EN FAC;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);"
						+ "S-3-VOLUMEN EN PAGOS;S-3-VOLUMEN NO PROCEDENTE;S-3-DIF ENTRE VOLUMEN;S-3-DIF ENTRE IMPORTE;S-3-DIF ENTRE RFC;");
				if(reporteAplica.contains( ",1,")){
					encabezado.append("S-3-PREDIO NO PAG;");
				}
				if(reporteAplica.contains( ",26,")){
					encabezado.append("S-3-PREDIO INC;");
				}
				if(reporteAplica.contains(",19,")){
					encabezado.append("S-3-DIF FAC GLOB VS IND;");
				}				
				encabezado.append("S-3-VOLUMEN TOTAL OBSERVADO;");
				crearColumna(encabezado.toString(), "DET");
//				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-PESO NETO ANA TOTAL EN FAC;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);"
//						+ "S-3-VOLUMEN EN PAGOS;S-3-VOLUMEN NO PROCEDENTE;S-3-DIF ENTRE VOLUMEN;S-3-DIF ENTRE IMPORTE;S-3-DIF ENTRE RFC;S-3-VOLUMEN TOTAL OBSERVADO", "DET");
//			}else{
//				if(rca.getCriteriosByPrograma().contains( ",1,")){
//					crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-PESO NETO ANA TOTAL EN FAC;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);"
//							+ "S-3-IMPORTE TOTAL PAGADO;"+(rca.getCriteriosByPrograma().contains("25")?"S-3-RENDIMIENTO MAXIMO;":"S-3-VOLUMEN NO PROCEDENTE;")+"S-3-DIF ENTRE VOLUMEN;S-3-DIF ENTRE IMPORTE;S-3-DIF ENTRE RFC;S-1-PREDIO NO PAG;S-3-VOLUMEN TOTAL OBSERVADO", "DET");
//				}else{
//					crearColumna("S-3-BODEGA;S-3-ESTADO;S-3-PRODUCTOR;S-3-CURP/RFC;S-3-PESO NETO ANA TOTAL EN FAC;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);"
//							+ "S-3-IMPORTE TOTAL PAGADO;"+(rca.getCriteriosByPrograma().contains("25")?"S-3-RENDIMIENTO MAXIMO;":"S-3-VOLUMEN NO PROCEDENTE;")+"S-3-DIF ENTRE VOLUMEN;S-3-DIF ENTRE IMPORTE;S-3-DIF ENTRE RFC;S-3-VOLUMEN TOTAL OBSERVADO", "DET");
//				}
//				
//			}	
		}else if(rca.getIdCriterio() == 21){//TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS
			if(tipo == 1){
				crearColumna("S-1-BODEGA;"+(siAplicaFolioContrato?"S-1-FOLIO CONTRATO;":"")+"S-1-ESTADO;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);S-3-TOTAL DE BOLETAS;S-3-TOTAL DE FACTURAS;"
						+ "S-1-TOTAL DE REGISTROS;S-1-PRODUCT BENEFICI", "ENC");
			}else{
				crearColumna((siAplicaFolioContrato?"S-1-FOLIO CONTRATO;":"")+"S-1-BODEGA;"+"S-1-ESTADO;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);S-3-TOTAL DE BOLETAS;S-3-TOTAL DE FACTURAS;"
						+ "S-1-TOTAL DE REGISTROS;S-1-PRODUCT BENEFICI", "ENC");
			}
			
//			if(siAplicaFolioContrato){	
//				crearColumna("S-1-FOLIO CONTRATO;S-1-BODEGA;S-1-ESTADO;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);S-3-TOTAL DE BOLETAS;S-3-TOTAL DE FACTURAS;"
//					+ "S-1-TOTAL DE REGISTROS;S-1-PRODUCT BENEFICI", "ENC");
//			}else{
//				crearColumna("S-1-BODEGA;S-1-ESTADO;S-3-PESO NETO ANA (BOL);S-3-PESO NETO ANA (FAC);S-3-TOTAL DE BOLETAS;S-3-TOTAL DE FACTURAS;"
//						+ "S-3-TOTAL DE REGISTROS;S-3-PRODUCT BENEFICI", "ENC");		
//			}			
		}else if(rca.getIdCriterio() == 22){
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("BOLETA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			if(rca.getCamposQueAplica().contains("12,")){
				parrafo =  new Paragraph("FECHA ENTRADA", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("63,")){
				parrafo =  new Paragraph("P.N.A. DE LA BOLETA (TON.)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			
		}else if(rca.getIdCriterio() == 23){
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			if(rca.getCamposQueAplica().contains("16,")){
				parrafo =  new Paragraph("FECHA EMISIÓN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("17,")){
				parrafo =  new Paragraph("RFC FACTURA", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains(",19,")){
				parrafo =  new Paragraph("P.N.A. SOLICITADO PARA APOYO (TON.)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("65,")){
				parrafo =  new Paragraph("PNA TOT DE LA FAC(TON)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("20,")){
				parrafo =  new Paragraph("IMP TOTAL FACT ($)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("70,")){
				parrafo =  new Paragraph("VARIEDAD", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			
		}else if(rca.getIdCriterio() == 24){
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("FOLIO PAGO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 1);
			t.addCell(cell);
			if(rca.getCamposQueAplica().contains("67,")){
				parrafo =  new Paragraph("IMPORTE TOTAL PAG", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("27,")){
				parrafo =  new Paragraph("TIPO DE DOC DE PAGO", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("16,")){
				parrafo =  new Paragraph("FECHA", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("29,")){
				parrafo =  new Paragraph("BANCO", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("66,")){
				parrafo =  new Paragraph("IMPORTE DE DOC DE PAGO($)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("68,")){
				parrafo =  new Paragraph("PRECIO PAG POR TON($)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
			}	
		}else if(rca.getIdCriterio() == 25){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-TOTAL FACTURADO;S-3-RENDIMIENTO MAXIMO ACEPTABLE;S-3-VOLUMEN NO ACEPTABLE;", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-TOTAL FACTURADO;S-3-RENDIMIENTO MAXIMO ACEPTABLE;S-3-VOLUMEN NO ACEPTABLE;", "DET");
			}
		}else if(rca.getIdCriterio() == 29){
			crearColumna("S-1-BODEGA;S-1-ESTADO;"+(siAplicaFolioContrato?"S-1-FOLIO CONTRATO;":"")+"S-1-PRODUCTOR;S-1-CURP/RFC;S-3-PESO NETO FAC(TON);S-3-IMP FACTURA;S-3-IMP COMP;S-3-IMP PAGADO;S-3-CUOTA;S-3-DIF PAG MENOR CUOTA;S-3-COMP BASE MENOR TOTAL;S-3-DIF PAG MENOR;", "DET");			
		}else if(rca.getIdCriterio() == 32){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-VARIEDAD;S-3-VOLUMEN FACTURA;S-3-VOL APOYADO EN REG;S-3-VOLUMEN NO PROCEDENTE;", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-VARIEDAD;S-3-VOLUMEN FACTURA;S-3-VOL APOYADO EN REG;S-3-VOLUMEN NO PROCEDENTE;", "DET");
			}
		}else if(rca.getIdCriterio() == 27){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-FOLIO CONTRATO;S-1-COMPRADOR;S-1-VENDEDOR;S-1-PRECIO PACTADO POR TON (DLLS);S-1-VOLUMEN;S-1-VOLUMEN FACTURADO;S-1-DIF VOLUMEN FINIQUITO;S-1-MOD", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-COMPRADOR;S-1-VENDEDOR;S-1-PRECIO PACTADO POR TON (DLLS);S-1-VOLUMEN;S-1-VOLUMEN FACTURADO;S-1-DIF VOLUMEN FINIQUITO;S-1-MOD", "DET");
			}
		}else if(rca.getIdCriterio() == 28){//PRECIO MENOR AL ESTABLECIDO EN AVISO			
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-P.N.A TOTAL EN FAC;S-3-IMPORTE FACTURA;S-3-IMPORTE PAGOS;S-3-IMPORTE TOTAL;S-3-PRECIO EN REL DE COMPRAS;S-3-PRECIO CALCULADO PAGADO;S-3-PRECIO EN AVISO", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-CURP/RFC;S-3-P.N.A TOTAL EN FAC;S-3-IMPORTE FACTURA;S-3-IMPORTE PAGOS;S-3-IMPORTE TOTAL;S-3-PRECIO EN REL DE COMPRAS;S-3-PRECIO CALCULADO PAGADO;S-3-PRECIO EN AVISO", "DET");
			}				
		}		
		t.setHeaderRows(5);	 
	}

	private void colocarPrimerasColumnasEncDetalle() {
		parrafo =  new Paragraph("BODEGA", TIMESROMAN08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		
		t.addCell(cell);
		parrafo =  new Paragraph("ESTADO", TIMESROMAN08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		if(siAplicaFolioContrato){			
			parrafo =  new Paragraph("FOLIO CONTRATO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}
		parrafo =  new Paragraph("PRODUCTOR", TIMESROMAN08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("CURP/RFC", TIMESROMAN08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
	}
	
	private void crearColumna(String contenido, String tipoContenido){
		Font fuente = null;
		if(tipoContenido.equals("ENC")){
			fuente = TIMESROMAN08BOLD;
		}else{
			fuente = TIMESROMAN08;
		}
		String[] numElementos = contenido.split(";");	
		for(int i = 0; i < numElementos.length; i++){
			String[] subElementos = numElementos[i].split("-");
			if(subElementos[0].equals("S")){//String
				parrafo =  new Paragraph(subElementos[2], fuente);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, Integer.parseInt(subElementos[1]), 1);				
			}else if (subElementos[0].equals("V")){//Volumen
				parrafo =  new Paragraph(subElementos[2] != null && !subElementos[2].equals("null") ? TextUtil.formateaNumeroComoVolumen(Double.parseDouble(subElementos[2])):"", fuente);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, Integer.parseInt(subElementos[1]), 1);		
			}else if (subElementos[0].equals("V6")){//Volumen
				parrafo =  new Paragraph(subElementos[2] != null && !subElementos[2].equals("null") ? TextUtil.formateaNumeroComoVolumenSeisDec(Double.parseDouble(subElementos[2])):"", fuente);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, Integer.parseInt(subElementos[1]), 1);
// AHS [LINEA] - 17022015				
			}else if (subElementos[0].equals("I")){//Importe
				parrafo =  new Paragraph(subElementos[2] != null && !subElementos[2].equals("null") ? TextUtil.formateaNumeroComoCantidad(Double.parseDouble(subElementos[2])):"", fuente);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, Integer.parseInt(subElementos[1]), 1);				
			}else if (subElementos[0].equals("F")){//Fecha
				parrafo =  new Paragraph(subElementos[2] != null ? new SimpleDateFormat("dd-MMM-yyyy").format(subElementos[2]).toString()+"":"", fuente);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, Integer.parseInt(subElementos[1]), 1);
			}else{//Entero
				parrafo =  new Paragraph(subElementos[2] != null?subElementos[2]:0+"", fuente);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, Integer.parseInt(subElementos[1]), 1);
			}
			t.addCell(cell);
		}	
	}
	
	private void inicializarArrayList() {
		lstChequeFueraPeriodoAuditor = new ArrayList<ChequeFueraPeriodoAuditor>();
		lstProductorPredioValidado = new ArrayList<ProductorPredioValidado>();
		lstProductorPredioPagado = new ArrayList<ProductorPredioPagado>();
		lstPrediosProdsContNoExistenBD = new ArrayList<PrediosProdsContNoExisteBD>();
		lstProductoresIncosistentes = new ArrayList<ProductoresIncosistentes>();
		lstPrediosNoExistenBD = new ArrayList<RelacionComprasTMP>();
		lstPrediosNoValidadosDRDE = new ArrayList<PrediosNoValidadosDRDE>();
		lstPrediosNoPagados = new ArrayList<PrediosNoPagados>();
		lstProductorExisteBD = new ArrayList<ProductorExisteBD>();
		lstBoletasDuplicadas = new ArrayList<BoletasDuplicadas>();
		lstBoletasFueraDePeriodo = new ArrayList<BoletasFueraDePeriodo>();		
		lstBoletasFueraDePeriodoPago = new ArrayList<BoletasFueraDePeriodoPago>();		
		lstProductorFactura = new ArrayList<ProductorFactura>();
		lstBoletasVsFacturas = new ArrayList<BoletasVsFacturas>();
		lstRfcProductorVsRfcFactura = new ArrayList<RfcProductorVsRfcFactura>();
		lstFacturaFueraPeriodoAuditor = new ArrayList<FacturaFueraPeriodoAuditor>();
		lstFacturasIgualesFacAserca = new ArrayList<FacturasIgualesFacAserca>();
		
		lstChequesDuplicadoBancoPartipante = new ArrayList<ChequesDuplicadoBancoPartipante>();
		lstChequeFueraPeriodoAuditor = new ArrayList<ChequeFueraPeriodoAuditor>(); 
		listChequeFueraPeriodoContrato = new ArrayList< ChequeFueraPeriodoContrato>();
		lstFacturasVsPago = new ArrayList<FacturasVsPago>();
		
		lstFacturaFueraPeriodoPago = new ArrayList<FacturaFueraPeriodoPago>();
		lstBoletasCamposRequeridos = new ArrayList<BoletasCamposRequeridos>();
		lstFacturasCamposRequeridos = new ArrayList<FacturasCamposRequeridos>();
		lstPagosCamposRequeridos = new ArrayList<PagosCamposRequeridos>();
		lstGeneralToneladasTotalesPorBodFac = new ArrayList<GeneralToneladasTotalesPorBodFac>();
		lstGeneralTonTotPorBodega = new ArrayList<GeneralToneladasTotPorBodega>();
		lstBoletasFacturasPagosIncosistentes = new ArrayList<BoletasFacturasPagosIncosistentes>();
		lstRendimientosProcedente = new ArrayList<RendimientosProcedente>();
		lstVolumenCumplido = new ArrayList<VolumenFiniquito>();
		lstPrecioPagPorTipoCambio = new ArrayList<PrecioPagPorTipoCambio>();
		lstPrecioPagadoMenorQueAviso = new ArrayList<PrecioPagadoNoCorrespondeConPagosV>();
		lstRfcProductorVsRfcFacturaSinContrato = new ArrayList<RelacionComprasTMP>();
		lstRfcProductorVsRfcFactura2 = new ArrayList<RelacionComprasTMP>();
		lstVolNoProcedenteYApoyEnReg = new ArrayList<VolumenNoProcedente>();
		lstFGlobalVsFIndividual = new ArrayList<RelacionComprasTMP>();
		lstPagMenorCompBases = new ArrayList<PagMenorCompBases>();
		lstCurpRfcYONombresInconsistentes = new ArrayList<RelacionComprasTMP>();
	}

	private void addEmptyLine(int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			parrafo = new Paragraph("\n");
			document.add(parrafo);
		}
	}
	
	public void piePagina() throws DocumentException { 	
		piePagina = null;	
		PdfPCell celda = null;
		piePagina= new PdfPTable(2);
		piePagina.setTotalWidth(document.right() - document.left()); 
		piePagina.setWidths(new int[]{8,92});
				
		StringBuilder ubicacion = new StringBuilder();
		ubicacion.append("Av. Municipio Libre 377, Col. Santa Cruz Atoyac, Del. Benito Juárez  México, DF 03310\n");
		ubicacion.append("t. +52 (55) 3871. 1000,  www.sagarpa.gob.mx");
		parrafo = new Paragraph(ubicacion.toString(), TIMESROMAN08LIGTH);
		parrafo.setLeading(1,1);
		celda =	createCell(parrafo, 2, 1, 1);
		piePagina.addCell(celda);	
	} 

	private PdfPCell createCell(Paragraph enunciado, int colspan, int alineamiento, int tipoBorde){
		return createCell(enunciado, colspan, alineamiento, tipoBorde, null,0);
	}
	
	private PdfPCell createCell(Paragraph enunciado, int colspan, int alineamiento, int tipoBorde, Image imagen, int padding){
		PdfPCell cell = null;
		cell =new PdfPCell(enunciado);
		if(enunciado==null){
			cell =new PdfPCell(imagen);
		}else if(imagen==null){
			
		}else if(imagen!=null && enunciado!=null){
			cell =new PdfPCell(enunciado);
			cell.addElement(imagen);
		}
		switch(alineamiento) {
		   case 1: cell.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		   case 2: cell.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		   case 3: cell.setHorizontalAlignment(Element.ALIGN_RIGHT); break;
		   case 4: cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED); break;
		   case 5: cell.setHorizontalAlignment(Element.ALIGN_BOTTOM); break;
		}
		   
	
		switch(tipoBorde) {
		   case 1: {
			   cell.setBorder(Rectangle.NO_BORDER); break;
		   }case 2: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(1f);
			   break;
		   }case 3: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(0f);
			   cell.setBorderWidthBottom(0f);
			   break;
		   }case 4: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(0f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(0f);
			   break;
		   } case 5: {
			   cell.setBorderWidthTop(0f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(0f);
			   cell.setBorderWidthBottom(1f);   
			   break;
		   } case 6: {
			   cell.setBorderWidthTop(0f);
			   cell.setBorderWidthLeft(0f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(1f);
			   break;
		   }case 7: {
			   cell.setBorderWidthTop(0f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(0f);
			   cell.setBorderWidthBottom(0f);
			   break;
		   } case 8: {
			   cell.setBorderWidthTop(0f);
			   cell.setBorderWidthLeft(0f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(0f);
			   break;
		   } case 9: {
			   cell.setBorderWidthTop(0f);
			   cell.setBorderWidthLeft(0f);
			   cell.setBorderWidthRight(0f);
			   cell.setBorderWidthBottom(1f);
			   break;
		   } case 10: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(0f);
			   cell.setBorderWidthRight(0f);
			   cell.setBorderWidthBottom(0f);
			   break;
		   } case 11: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(0f);
			   cell.setBorderWidthBottom(1f);
			   break;
		   }case 12: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(0f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(1f);
			   break;
		   }case 13: {
			   cell.setBorderWidthTop(0f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(1f);
			   break;
		   }case 14: {
			   cell.setBorderWidthTop(1f);
			   cell.setBorderWidthLeft(1f);
			   cell.setBorderWidthRight(1f);
			   cell.setBorderWidthBottom(0f);
			   break;
		   }
		   default: {
			   break;
		   }
		}
		if (colspan > 0){
			cell.setColspan(colspan);
		}
		cell.setPadding(padding);		
		
		cell.setLeading(1, 1);
		return cell;
	}

	
	public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(100, 100);
		try {
			baseFont = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }	
	
    // AHS [LINEA] - 10022015
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(1);
        //logger.info("onEndPage: colocando el footer");
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		table.setTotalWidth(document.right() - document.left());
		//logger.info("onEndPage: headerTable");
		table.writeSelectedRows(0, -1, document.left(), document
				.getPageSize().height() - 7, cb);
		//logger.info("onEndPage: headerTable");
		String text = "";
		//text += ("Número de Listado:  " + oficioReciboPagos.getIdListado()+ "");
		text += ("                                                              Página " + writer.getPageNumber() + " de ");
		//logger.info("En el onEnd del text");
		float textSize = baseFont.getWidthPoint(text, 7);
		float textBase = document.bottom() - 20;
		cb.beginText();
		cb.setFontAndSize(baseFont, 7);
		float adjust = baseFont.getWidthPoint("10", 0);
		cb.setTextMatrix(document.right() - textSize - adjust - 80,
				textBase);
		cb.showText(text);
		cb.endText();
		cb.addTemplate(total, document.right() - adjust - 80, textBase);
		// cb.addTemplate(pdftemplate,0,0);
		cb.saveState();
		cb.restoreState();
		//logger.info("Terminando  footer");
    }
    // AHS [LINEA] - 10022015
    
    // AHS [LINEA] - 10022015
    public void onCloseDocument(PdfWriter writer, Document document) {
		//logger.info("onCloseDocument: cerrando el documento");
    	total.beginText();
    	total.setFontAndSize(baseFont, 7);
    	total.setTextMatrix(0, 0);
    	total.showText("" + (writer.getPageNumber() - 1));
    	total.endText();
    }    
    // AHS [LINEA] - 10022015
	public List<PagosV> getPago() {
		return pagoV;
	}

	public void setPago(List<PagosV> pagoV) {
		this.pagoV = pagoV;
	}

	public List<PagosDetalleV> getPagoDet() {
		return pagoDet;
	}

	public void setPagoDet(List<PagosDetalleV> pagoDet) {
		this.pagoDet = pagoDet;
	}

	public List<CuentasBancariasV> getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(List<CuentasBancariasV> cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public StringBuilder getTexto() {
		return texto;
	}

	public void setTexto(StringBuilder texto) {
		this.texto = texto;
	}
	
	public boolean isSiAplicaFolioContrato() {
		return siAplicaFolioContrato;
	}

	public void setSiAplicaFolioContrato(boolean siAplicaFolioContrato) {
		this.siAplicaFolioContrato = siAplicaFolioContrato;
	}
	

}
