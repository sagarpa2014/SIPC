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
	private final Font TIMESROMAN08	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 6,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN08BOLD	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 6,Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.NORMAL, Color.BLACK);	
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
	private List<PrediosNoExistenBD> lstPrediosNoExistenBD;
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
	private List<FacturaFueraPeriodoAuditor> lstFacturaFueraPeriodoAuditor;
	private List<FacturasIgualesFacAserca> lstFacturasIgualesFacAserca;
	private List<PrecioPagadoProductor> lstPrecioPagadoProductor;
	private List<ChequesDuplicadoBancoPartipante> lstChequesDuplicadoBancoPartipante;
	private List<ChequeFueraPeriodoAuditor> lstChequeFueraPeriodoAuditor;
	private List<ChequeFueraPeriodoContrato> listChequeFueraPeriodoContrato;
	private List<FacturasVsPago> lstFacturasVsPago;
	private List<PrecioPagadoMenor> lstPrecioPagadoMenor;	
	private List<FacturaFueraPeriodoPago> lstFacturaFueraPeriodoPago;
	private List<BoletasCamposRequeridos> lstBoletasCamposRequeridos;
	private List<FacturasCamposRequeridos> lstFacturasCamposRequeridos;
	private List<PagosCamposRequeridos> lstPagosCamposRequeridos;
	private List<GeneralToneladasTotalesPorBodFac> lstGeneralToneladasTotalesPorBodFac;
	private List<BoletasFacturasPagosIncosistentes> lstBoletasFacturasPagosIncosistentes;
	private List<VolumenFiniquito> lstVolumenCumplido;
	private List<PrecioPagPorTipoCambio> lstPrecioPagPorTipoCambio;
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
	private double totalVolPorBodega, totalVolPorBodega1, totalVolPorBodega2,totalVolPorBodega3, totalVolPorBodega4, totalVolPorBodega5,totalVolPorBodega6,totalVolPorBodega7;
	private double granTotalVol,granTotalVol1, granTotalVol2, granTotalVol3, granTotalVol4, granTotalVol5, granTotalVol6,granTotalVol7;
	private String claveBodega, nombreEstado, folioContrato, paternoProductor, maternoProductor, nombreProductor;
	private String claveBodegaTmp, nomEstadoTmp, temProductor, temFCProductor, temFolioContrato, temFolioContratoAux;
	private float[] w;
	private List<RendimientosProcedente> lstRendimientosProcedente;
	
	private PdfTemplate total; 	// AHS [LINEA] - 10022015
	private BaseFont baseFont = null;	// AHS [LINEA] - 10022015
	private double totalVolPorContrato8;
	private double totalVolPorBodega8;
	private double granTotalVol8;
	
	
	public CruceRelComprasError(RelacionComprasAction rca, Session session) {
		this.rca = rca;
		this.session = session;
		rDAO = new RelacionesDAO(session);				
	}
	
	public void generarCruce() throws Exception{	
		
		if(rca.getIdCriterio()==20 || rca.getIdCriterio() == 21){
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
		getDatosGeneralesCartaAdehsion();
		addEmptyLine(2);		
		getCuerpo();
		addEmptyLine(1);
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
	
	private void getDatosGeneralesCartaAdehsion() throws DocumentException {		
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
		parrafo = new Paragraph(rca.getLstBitacoraRelCompras().get(0).getMensaje()+"\n\t", TIMESROMAN08);
		cell = createCell(parrafo, w.length, 1, 1, null,0);
		t.addCell(cell);				
	}
		
	private void getCuerpo() throws DocumentException, ParseException{	
				
		inicializarArrayList();					
		colocarInfoEnPojo();
		colocaDetalleEnTabla();			
		document.add(t);
		addEmptyLine(1);
	}
	  
	@SuppressWarnings("unchecked")
	private void colocaDetalleEnTabla() {
		claveBodegaTmp = ""; nomEstadoTmp=""; temProductor = ""; temFCProductor =""; temFolioContrato= "" ;
		if(rca.getIdCriterio() == 1){//VALIDA QUE EXISTA PREDIO EN BASE DE DATOS
			Collections.sort(lstPrediosNoExistenBD);
			for(PrediosNoExistenBD p: lstPrediosNoExistenBD){
				parrafo =  new Paragraph(p.getFolioPredio(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioSec()!=-1?p.getFolioPredioSec().toString():"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioAlterno(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
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
				parrafo =  new Paragraph(p.getProductor().toString(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getPaternoProductor()+" "+p.getMaternoProductor()+" "+p.getNombreProductor(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredio(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioSec()!=-1?p.getFolioPredioSec().toString():"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(p.getFolioPredioAlterno(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				claveBodegaTmp = p.getClaveBodega();
				nomEstadoTmp = p.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = p.getFolioContrato();
				}				
			}				
		}else if(rca.getIdCriterio() == 4){//"PRODUCTOR EXISTA EN BASE DE DATOS"
			Collections.sort(lstProductorExisteBD);
			for(ProductorExisteBD l: lstProductorExisteBD){
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
				parrafo =  new Paragraph(l.getFolioPredio()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getPredioAlterno()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getNumeroBoletas()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getNumeroFacturas()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getNumeroPagos()+"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
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
				configTotales[1] = "1,TOTAL;6,"+totalVolPorBodega+",v";				
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				colocarPrimerasColumnasDetalle(configTotales, 7);
*/
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
				parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getVolBolTicket() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolBolTicket()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getFechaEntradaBoleta() != null ? new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEntradaBoleta()).toString()+"":"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				if(siAplicaFolioContrato){					
					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
				}
				crearColumna("S-1-"+l.getClaveBodega(),"DET");				
				//claveBodegaTmp = l.getClaveBodega();
				//nomEstadoTmp = l.getNombreEstado();					
				//if(siAplicaFolioContrato){				
				//	temFolioContrato = l.getFolioContrato();
				//}				
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
					configTotales[0] = "3,TOTAL;7,"+totalVolPorContrato+",v";
					configTotales[1] = "1,TOTAL;7,"+totalVolPorBodega+",v";				
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);	
					parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEntradaBoleta()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
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
				colocarTotales("3,TOTAL;7,"+totalVolPorContrato+",v", w.length);
				colocarTotales("1,TOTAL;7,"+totalVolPorBodega+",v", w.length);
				colocarTotales("3,TOTAL;7,"+granTotalVol+",v", w.length);
			} else {
				Collections.sort(lstBoletasFueraDePeriodoPago);
				contBitacoraDet = 1;			
				for(BoletasFueraDePeriodoPago l: lstBoletasFueraDePeriodoPago){					
					configTotales = new String [2];
					configTotales[0] = "3,TOTAL;9,"+totalVolPorContrato+",v";
					configTotales[1] = "3,TOTAL;9,"+totalVolPorBodega+",v";
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
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
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEntradaBoleta()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
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
				colocarTotales("3,TOTAL;9,"+totalVolPorContrato+",v", w.length);
				colocarTotales("3,TOTAL;9,"+totalVolPorBodega+",v", w.length);
				colocarTotales("3,TOTAL;9,"+granTotalVol+",v", w.length);				
			}
		}else if(rca.getIdCriterio() == 10){//"NO SE DUPLIQUE EL FOLIO DE LA FACTURA"
			contBitacoraDet = 1;
			Collections.sort(lstProductorFactura);
			granTotalVol = 0;
			for (ProductorFactura l: lstProductorFactura){
				//configTotales = new String [2]; 
				//configTotales[0] = "3,TOTAL;6,"+totalVolPorContrato+",v";
				//configTotales[1] = "1,TOTAL;6,"+totalVolPorBodega+",v";						
				//claveBodega = l.getClaveBodega();
				//nombreEstado =  l.getNombreEstado();
				//folioContrato = l.getFolioContrato();
//				paternoProductor = l.getPaternoProductor();
//				maternoProductor = l.getMaternoProductor();
//				nombreProductor = l.getNombreProductor();			
				//colocarPrimerasColumnasDetalle(null, 7);				
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");				
				parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);				
				parrafo =  new Paragraph(l.getFechaEmisionFac()!=null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString():"", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);	
				if(siAplicaFolioContrato){					
					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
				}	
				crearColumna("S-1-"+l.getClaveBodega(),"DET");				
				//claveBodegaTmp = l.getClaveBodega();
				//nomEstadoTmp = l.getNombreEstado();
//				if(siAplicaFolioContrato){				
//					temFolioContrato = l.getFolioContrato();
//				}				
				//totalVolPorContrato += l.getVolTotalFacVenta();
				//totalVolPorBodega += l.getVolTotalFacVenta();
				//granTotalVol += l.getVolTotalFacVenta();		
				//contBitacoraDet ++;				
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
				configTotales[0] = "3,TOTAL;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",v;7,"+totalVolPorContrato2+",v";
				configTotales[1] = "1,TOTAL;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v";				
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();	
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
			colocarTotales("3,TOTAL;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",v;7,"+totalVolPorContrato2+",v", w.length);
			colocarTotales("1,TOTAL;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v", w.length);
			colocarTotales("3,TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",v;7,"+granTotalVol2+",v", w.length);	
		}else if(rca.getIdCriterio() == 12){//"QUE EL RFC CORRESPONDA AL PRODUCTOR"
			Collections.sort(lstRfcProductorVsRfcFactura);			
			for (RfcProductorVsRfcFactura l: lstRfcProductorVsRfcFactura){	
//				configTotales = new String [2];
//				configTotales[0] = "3,TOTAL CON;7,"+totalVolPorContrato+",v";
//				configTotales[1] = "3,TOTAL BOD;7,"+totalVolPorBodega+",v";				
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
//				totalVolPorContrato += l.getVolTotalFacVenta();
//				totalVolPorBodega += l.getVolTotalFacVenta();
//				granTotalVol += l.getVolTotalFacVenta();
				contBitacoraDet ++;
			}
//			colocarTotales("3,TOTAL CON;7,"+totalVolPorContrato+",v", w.length);
//			colocarTotales("3,TOTAL BOD;7,"+totalVolPorBodega+",v", w.length);
//			colocarTotales("3,GRAN TOTAL;7,"+granTotalVol+",v", w.length);
//			
			
		}else if(rca.getIdCriterio() == 13){
			if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				Collections.sort(lstFacturaFueraPeriodoAuditor);
				contBitacoraDet = 1;				
				for(FacturaFueraPeriodoAuditor l: lstFacturaFueraPeriodoAuditor){
					configTotales = new String [2];
					configTotales[0] = "3,TOTAL;7,"+totalVolPorContrato+",v";
					configTotales[1] = "1,TOTAL;7,"+totalVolPorBodega+",v";
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);
					parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaEmisionFac()).toString(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
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
				colocarTotales("3,TOTAL;7,"+totalVolPorContrato+",v", w.length);
				colocarTotales("1,TOTAL;7,"+totalVolPorBodega+",v", w.length);
				colocarTotales("3,TOTAL;7,"+granTotalVol+",v", w.length);
			} else {
				Collections.sort(lstFacturaFueraPeriodoPago);
				contBitacoraDet = 1;				
				for(FacturaFueraPeriodoPago l: lstFacturaFueraPeriodoPago){
					configTotales = new String [2];
					configTotales[0] = "3,TOTAL;9,"+totalVolPorContrato+",v";
					configTotales[1] = "1,TOTAL;9,"+totalVolPorBodega+",v";
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();					
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
				colocarTotales("3,TOTAL;9,"+totalVolPorContrato+",v", w.length);
				colocarTotales("1,TOTAL;9,"+totalVolPorBodega+",v", w.length);
				colocarTotales("3,TOTAL;9,"+granTotalVol+",v", w.length);				
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
				configTotales = new String [2];
				if(siAplicaFolioContrato){
					configTotales[0] = "3,TOTAL CON;5,"+totalVolPorContrato+",v;7,"+totalVolPorContrato1+",i;8,"+totalVolPorContrato2+",i;9,"+totalVolPorContrato3+",i";
					configTotales[1] = "3,TOTAL BOD;5,"+totalVolPorBodega+",v;7,"+totalVolPorBodega1+",i;8,"+totalVolPorBodega2+",i;9,"+totalVolPorBodega3+",i";
				}else{
					configTotales[1] = "1,TOTAL BOD;4,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",i;7,"+totalVolPorBodega2+",i;8,"+totalVolPorBodega3+",i";
				}								
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();	
				colocarPrimerasColumnasDetalle(configTotales, w.length);
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getPrecioPactadoPorTonelada() != null ? TextUtil.formateaNumeroComoCantidad(l.getPrecioPactadoPorTonelada()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getImpSolFacVenta() != null ? TextUtil.formateaNumeroComoCantidad(l.getImpSolFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getImporteContrato() != null ? TextUtil.formateaNumeroComoCantidad(l.getImporteContrato()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getDiferenciaImporte() != null ? TextUtil.formateaNumeroComoCantidad(l.getDiferenciaImporte()):"", TIMESROMAN08);
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
				totalVolPorContrato += l.getVolTotalFacVenta();
				totalVolPorContrato1 += l.getImpSolFacVenta();
				totalVolPorContrato2 += l.getImporteContrato();
				totalVolPorContrato3 += l.getDiferenciaImporte();		
				totalVolPorBodega += l.getVolTotalFacVenta();
				totalVolPorBodega1 += l.getImpSolFacVenta();
				totalVolPorBodega2 += l.getImporteContrato();
				totalVolPorBodega3 += l.getDiferenciaImporte();
				granTotalVol += l.getVolTotalFacVenta();
				granTotalVol1 += l.getImpSolFacVenta();
				granTotalVol2 += l.getImporteContrato();
				granTotalVol3 += l.getDiferenciaImporte();
				
				
				contBitacoraDet ++;
			}			

			if(siAplicaFolioContrato){
				colocarTotales("3,TOTAL CON;5,"+totalVolPorContrato+",v;7,"+totalVolPorContrato1+",i;8,"+totalVolPorContrato2+",i;9,"+totalVolPorContrato3+",i",w.length);
				colocarTotales("3,TOTAL BOD;5,"+totalVolPorBodega+",v;7,"+totalVolPorBodega1+",i;8,"+totalVolPorBodega2+",i;9,"+totalVolPorBodega3+",i",w.length);
				colocarTotales("3,GRAN TOTAL ;5,"+granTotalVol+",v;7,"+granTotalVol1+",i;8,"+granTotalVol2+",i;9,"+granTotalVol3+",i",w.length);
			}else{
				colocarTotales("3,TOTAL BOD;4,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",i;7,"+totalVolPorBodega2+",i;8,"+totalVolPorBodega3+",i",w.length);
				colocarTotales("3,GRAN TOTAL;4,"+granTotalVol+",v;6,"+granTotalVol1+",i;7,"+granTotalVol2+",i;8,"+granTotalVol3+",i",w.length);
			}
		
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
//				claveBodega = l.getClaveBodega();
//				nombreEstado =  l.getNombreEstado();
//				folioContrato = l.getFolioContrato();
//				paternoProductor = l.getPaternoProductor();
//				maternoProductor = l.getMaternoProductor();
//				nombreProductor = l.getNombreProductor();	
				//colocarPrimerasColumnasDetalle(configTotales, w.length);
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
				crearColumna("S-2-"+ l.getFolioDocPago(), "DET");
				System.out.println("l "+l.getVolTotalFacVenta());
				crearColumna("S-2-"+l.getBancoSinaxc(),"DET");				
				parrafo =  new Paragraph(l.getVolTotalFacVenta() != null ? TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"", TIMESROMAN08);
				parrafo.setLeading(1, 1);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);			
				if(siAplicaFolioContrato){					
					crearColumna("S-1-"+l.getFolioContrato(),"DET");	
				}
				crearColumna("S-1-"+l.getClaveBodega(),"DET");	
//				claveBodegaTmp = l.getClaveBodega();
//				nomEstadoTmp = l.getNombreEstado();					
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
					configTotales[0] = "3,TOTAL;8,"+totalVolPorContrato+",v"+";9,"+totalVolPorContrato1+",v";
					configTotales[1] = "3,TOTAL;8,"+totalVolPorBodega+",v"+";9,"+totalVolPorBodega1+",v";				
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);				
					parrafo =  new Paragraph(l.getFolioDocPago(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);				
					parrafo =  new Paragraph(l.getBancoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getFechaDocPagoSinaxc() != null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaDocPagoSinaxc()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);				
					parrafo =  new Paragraph(l.getVolTotalFacVenta()!=null && !l.getVolTotalFacVenta().equals("-")&& !l.getVolTotalFacVenta().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"" , TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getImpTotalPagoSinaxc()!=null && !l.getImpTotalPagoSinaxc().equals("-")&& !l.getImpTotalPagoSinaxc().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getImpTotalPagoSinaxc()):"" , TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolTotalFacVenta();
					totalVolPorContrato1 += l.getImpTotalPagoSinaxc();
					totalVolPorBodega += l.getVolTotalFacVenta();
					totalVolPorBodega1 += l.getImpTotalPagoSinaxc();
					granTotalVol += l.getVolTotalFacVenta();
					granTotalVol1 += l.getImpTotalPagoSinaxc();
					contBitacoraDet ++;
				}//end for
				colocarTotales("3,TOTAL;8,"+totalVolPorContrato+",v"+";9,"+totalVolPorContrato1+",v", w.length);
				colocarTotales("1,TOTAL;8,"+totalVolPorBodega+",v"+";9,"+totalVolPorBodega1+",v", w.length);
				colocarTotales("3,TOTAL;8,"+granTotalVol+",v"+";9,"+granTotalVol1+",v", w.length);
			}else{
				Collections.sort(listChequeFueraPeriodoContrato);
				for(ChequeFueraPeriodoContrato l: listChequeFueraPeriodoContrato){
					configTotales = new String [2];
					configTotales[0] = "3,TOTAL;10,"+totalVolPorContrato+",v"+";11,"+totalVolPorContrato1+",v";
					configTotales[1] = "3,TOTAL;10,"+totalVolPorBodega+",v"+";11,"+totalVolPorBodega1+",v";			
					claveBodega = l.getClaveBodega();
					nombreEstado =  l.getNombreEstado();
					folioContrato = l.getFolioContrato();
					paternoProductor = l.getPaternoProductor();
					maternoProductor = l.getMaternoProductor();
					nombreProductor = l.getNombreProductor();
					colocarPrimerasColumnasDetalle(configTotales, w.length);		
					parrafo =  new Paragraph(l.getPeriodoInicialPago()!=null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoInicialPago()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getPeriodoFinalPago()!=null ?new SimpleDateFormat("dd-MMM-yyyy").format(l.getPeriodoFinalPago()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 1);
					t.addCell(cell);
					
					parrafo =  new Paragraph(l.getFolioDocPago(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);				
					parrafo =  new Paragraph(l.getBancoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getFechaDocPagoSinaxc() != null?new SimpleDateFormat("dd-MMM-yyyy").format(l.getFechaDocPagoSinaxc()).toString():"", TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);				
					parrafo =  new Paragraph(l.getVolTotalFacVenta()!=null && !l.getVolTotalFacVenta().equals("-")&& !l.getVolTotalFacVenta().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getVolTotalFacVenta()):"" , TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					parrafo =  new Paragraph(l.getImpTotalPagoSinaxc()!=null && !l.getImpTotalPagoSinaxc().equals("-")&& !l.getImpTotalPagoSinaxc().equals("null")?TextUtil.formateaNumeroComoVolumen(l.getImpTotalPagoSinaxc()):"" , TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 3, 1);
					t.addCell(cell);
					claveBodegaTmp = l.getClaveBodega();
					nomEstadoTmp = l.getNombreEstado();
					if(siAplicaFolioContrato){				
						temFolioContrato = l.getFolioContrato();
					}
					totalVolPorContrato += l.getVolTotalFacVenta();
					totalVolPorContrato1 += l.getImpTotalPagoSinaxc();
					totalVolPorBodega += l.getVolTotalFacVenta();
					totalVolPorBodega1 += l.getImpTotalPagoSinaxc();
					granTotalVol += l.getVolTotalFacVenta();
					granTotalVol1 += l.getImpTotalPagoSinaxc();
					contBitacoraDet ++;
				}//end for				
				colocarTotales("3,TOTAL;10,"+totalVolPorContrato+",v"+";11,"+totalVolPorContrato1+",v",w.length);
				colocarTotales("3,TOTAL;10,"+totalVolPorBodega+",v"+";11,"+totalVolPorBodega1+",v",w.length);
				colocarTotales("3,TOTAL;10,"+granTotalVol+",v"+";11,"+granTotalVol1+",v",w.length);
			}

		}else if(rca.getIdCriterio() == 18){//"MONTO TOTAL PAGADO SEA MAYOR A LA SUMA DE LOS TOTALES PAGADOS AL PRODUCTOR (FACTURA,CB)."
			contBitacoraDet = 1;
			Collections.sort(lstFacturasVsPago);
			for(FacturasVsPago l: lstFacturasVsPago ){
				configTotales = new String [2];
				configTotales[0] = "3,TOTAL;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",v;7,"+totalVolPorContrato2+",v";
				configTotales[1] = "3,TOTAL;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v";
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
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
			colocarTotales("3,TOTAL;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",v;7,"+totalVolPorContrato2+",v", w.length);
			colocarTotales("3,TOTAL;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",v;7,"+totalVolPorBodega2+",v", w.length);
			colocarTotales("3,TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",v;7,"+granTotalVol2+",v", w.length);
			
		}else if(rca.getIdCriterio() == 19){//PRECIO PAGADO MENOR"
			contBitacoraDet = 1;
			Collections.sort(lstPrecioPagadoMenor);
			for(PrecioPagadoMenor  l: lstPrecioPagadoMenor){
				configTotales = new String [2];
				configTotales[0] = "3,TOTAL;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",i"+";7,"+totalVolPorContrato2+",i"+";8,"+totalVolPorContrato3+",i"+";9,"+totalVolPorContrato4+",i"+";10,"+totalVolPorContrato5+",i"+";11,"+totalVolPorContrato6+",i";
				configTotales[1] = "1,TOTAL;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i"+";8,"+totalVolPorBodega3+",i"+";9,"+totalVolPorBodega4+",i"+";10,"+totalVolPorBodega5+",i"+";11,"+totalVolPorBodega6+",i";
				claveBodega = l.getClaveBodega();
				nombreEstado =  l.getNombreEstado();
				folioContrato = l.getFolioContrato();
				paternoProductor = l.getPaternoProductor();
				maternoProductor = l.getMaternoProductor();
				nombreProductor = l.getNombreProductor();
				colocarPrimerasColumnasDetalle(configTotales, w.length);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImporteCheque()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImporteFactura()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getImporteTotal()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPrecioEnRelCompras()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPrecioCalculado()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(l.getPrecioPagado()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 1);
				t.addCell(cell);
				parrafo =  new Paragraph(l.getObservacion(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				
				totalVolPorContrato += l.getPesoNeto();
				totalVolPorContrato1 += l.getImporteCheque();
				totalVolPorContrato2 += l.getImporteFactura();
				totalVolPorContrato3 += l.getImporteTotal();
				totalVolPorContrato4 += l.getPrecioEnRelCompras();
				totalVolPorContrato5 += l.getPrecioCalculado();
				totalVolPorContrato6 += l.getPrecioPagado();
				totalVolPorBodega += l.getPesoNeto();
				totalVolPorBodega1 += l.getImporteCheque();
				totalVolPorBodega2 += l.getImporteFactura();
				totalVolPorBodega3 += l.getImporteTotal();
				totalVolPorBodega4 += l.getPrecioEnRelCompras();
				totalVolPorBodega5 += l.getPrecioCalculado();
				totalVolPorBodega6 += l.getPrecioPagado();
				granTotalVol += l.getPesoNeto();
				granTotalVol1 += l.getImporteCheque();
				granTotalVol2 += l.getImporteFactura();
				granTotalVol3 += l.getImporteTotal();
				granTotalVol4 += l.getPrecioEnRelCompras();
				granTotalVol5 += l.getPrecioCalculado();
				granTotalVol6 += l.getPrecioPagado();
				contBitacoraDet ++;				
			}	
			colocarTotales("3,TOTAL;5,"+totalVolPorContrato+",v"+";6,"+totalVolPorContrato1+",i"+";7,"+totalVolPorContrato2+",i"+";8,"+totalVolPorContrato3+",i"+";9,"+totalVolPorContrato4+",i"+";10,"+totalVolPorContrato5+",i"+";11,"+totalVolPorContrato6+",i", w.length);
			colocarTotales("1,TOTAL;5,"+totalVolPorBodega+",v"+";6,"+totalVolPorBodega1+",i"+";7,"+totalVolPorBodega2+",i"+";8,"+totalVolPorBodega3+",i"+";9,"+totalVolPorBodega4+",i"+";10,"+totalVolPorBodega5+",i"+";11,"+totalVolPorBodega6+",i", w.length);
			colocarTotales("3,TOTAL;5,"+granTotalVol+",v"+";6,"+granTotalVol1+",i"+";7,"+granTotalVol2+",i"+";8,"+granTotalVol3+",i"+";9,"+granTotalVol4+",i"+";10,"+granTotalVol5+",i"+";11,"+granTotalVol6+",i", w.length);
		}else if(rca.getIdCriterio() == 20){//RESUMEN DE OBSERVACIONES
			Collections.sort(lstBoletasFacturasPagosIncosistentes);
			contBitacoraDet = 1;
			for(BoletasFacturasPagosIncosistentes l: lstBoletasFacturasPagosIncosistentes){			
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){
							colocarTotales("3,TOTAL CON;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"
									+"7,"+totalVolPorContrato2+",v;"+"8,"+totalVolPorContrato3+",v;"
									+"9,"+totalVolPorContrato4+",v;"+"10,"+totalVolPorContrato5+",v;"+"11,"+totalVolPorContrato6+",v;"
									+"12,"+totalVolPorContrato7+",v;"+"13,"+totalVolPorContrato8+",v;",  w.length);
							
							totalVolPorContrato = 0;
							totalVolPorContrato1 = 0;
							totalVolPorContrato2 = 0;
							totalVolPorContrato3 = 0;
							totalVolPorContrato4 = 0;
							totalVolPorContrato5 = 0;
							totalVolPorContrato6 = 0;
							totalVolPorContrato7 = 0;
							totalVolPorContrato8 = 0;
							
						}
						
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){
							colocarTotales("3,TOTAL BOD;5,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",v;"
									+"7,"+totalVolPorBodega2+",v;"+"8,"+totalVolPorBodega3+",i;"
									+"9,"+totalVolPorBodega4+",v;"+"10,"+totalVolPorBodega5+",v;"+"11,"+totalVolPorBodega6+",v;"
									+"12,"+totalVolPorBodega7+",v;"+"13,"+totalVolPorBodega8+",v",  w.length);				
							totalVolPorBodega = 0;						
							totalVolPorBodega1 = 0;						
							totalVolPorBodega2 = 0;						
							totalVolPorBodega3 = 0;						
							totalVolPorBodega4 = 0;						
							totalVolPorBodega5 = 0;						
							totalVolPorBodega6 = 0;						
							totalVolPorBodega7 = 0;						
							totalVolPorBodega8 = 0;						
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
					}else{
						crearColumna("S-1-\t","DET");								
					}				
				}	
			
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
				System.out.println("Volumen observado en facturas "+l.getVolTotalFacVenta());
				crearColumna("V-3-"+l.getVolTotalFacturado()+";"+"V-3-"+l.getVolBolTicket()+";"+"V-3-"+l.getVolTotalFacVenta()+";"+"V-3-"+l.getVolEnpagos()+";"
							+"V-3-"+l.getVolumenNoProcedente()+";"+"V-3-"+l.getDiferenciaEntreVolumen()+";"+"V-3-"+l.getDiferenciaEntreImportes()+";V-3-"+l.getDiferenciaEntreRFC()+";V-3-"+l.getVolumenObservado(), "DET");
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
				totalVolPorContrato8 += (l.getVolumenObservado()!=null?l.getVolumenObservado():0);
				
				totalVolPorBodega += l.getVolTotalFacturado();
				totalVolPorBodega1 += l.getVolBolTicket();
				totalVolPorBodega2 += (l.getVolTotalFacVenta()!=null?l.getVolTotalFacVenta():0);
				totalVolPorBodega3 += l.getVolEnpagos();
				totalVolPorBodega4 += l.getVolumenNoProcedente();
				totalVolPorBodega5 += l.getDiferenciaEntreVolumen();
				totalVolPorBodega6 += l.getDiferenciaEntreImportes();
				totalVolPorBodega7 += (l.getDiferenciaEntreRFC()!=null?l.getDiferenciaEntreRFC():0);
				totalVolPorBodega8 += (l.getVolumenObservado()!=null?l.getVolumenObservado():0);
				
				granTotalVol += l.getVolTotalFacturado();
				granTotalVol1 += l.getVolBolTicket();
				granTotalVol2 += (l.getVolTotalFacVenta()!=null?l.getVolTotalFacVenta():0);
				granTotalVol3 += l.getVolEnpagos();
				granTotalVol4 += l.getVolumenNoProcedente();
				granTotalVol5 += l.getDiferenciaEntreVolumen();
				granTotalVol6 += l.getDiferenciaEntreImportes();
				granTotalVol7 += (l.getDiferenciaEntreRFC()!=null?l.getDiferenciaEntreRFC():0);
				granTotalVol8 += (l.getVolumenObservado()!=null?l.getVolumenObservado():0);
				
				contBitacoraDet ++;						
			}			
			if(siAplicaFolioContrato){
				colocarTotales("3,TOTAL CON;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"
						+"7,"+totalVolPorContrato2+",v;"+"8,"+totalVolPorContrato3+",v;"
						+"9,"+totalVolPorContrato4+",v;"+"10,"+totalVolPorContrato5+",v;"+"11,"+totalVolPorContrato6+",v;"
						+"12,"+totalVolPorContrato7+",v;"+"13,"+totalVolPorContrato8+",v;",  w.length);
				
				colocarTotales("3,TOTAL BOD;5,"+totalVolPorBodega+",v;6,"+totalVolPorBodega1+",v;"
						+"7,"+totalVolPorBodega2+",v;"+"8,"+totalVolPorBodega3+",i;"
						+"9,"+totalVolPorBodega4+",v;"+"10,"+totalVolPorBodega5+",v;"+"11,"+totalVolPorBodega6+",v;"
						+"12,"+totalVolPorBodega7+",v;"+"13,"+totalVolPorBodega8+",v;",  w.length);		
				
				colocarTotales("3,GRAN TOTAL;5,"+granTotalVol+",v;6,"+granTotalVol1+",v;"
							+"7,"+granTotalVol2+",v;"+"8,"+granTotalVol3+",i;"
							+"9,"+granTotalVol4+",v;"+"10,"+granTotalVol5+",v;"+"11,"+granTotalVol6+",v;"
							+"12,"+granTotalVol7+",v;"+"13,"+granTotalVol8+",v;",  w.length);	
			}			
			
			
			
				
		}else if(rca.getIdCriterio() == 21){//TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS
			Collections.sort(lstGeneralToneladasTotalesPorBodFac);
			contBitacoraDet = 1;
			for(GeneralToneladasTotalesPorBodFac l: lstGeneralToneladasTotalesPorBodFac){			
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){
							colocarTotales("1,TOTAL;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"
									+"7,"+totalVolPorContrato2+",c;"+"8,"+totalVolPorContrato3+",c;"
									+"9,"+totalVolPorContrato4+",c;"+"10,"+totalVolPorContrato5+",c;", w.length);			
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
					crearColumna("S-1-"+l.getClaveBodega()+";S-1-"+l.getNombreBodega(),"DET");	
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
				colocarTotales("1,TOTAL;5,"+totalVolPorContrato+",v;6,"+totalVolPorContrato1+",v;"
						+"7,"+totalVolPorContrato2+",c;"+"8,"+totalVolPorContrato3+",c;"
						+"9,"+totalVolPorContrato4+",c;"+"10,"+totalVolPorContrato5+",c;", w.length);
			}
			colocarTotales("1,TOTAL;5,"+granTotalVol+",v;6,"+granTotalVol1+",v;"
					+"7,"+granTotalVol2+",c;"+"8,"+granTotalVol3+",c;"
					+"9,"+granTotalVol4+",c;"+"10,"+granTotalVol5+",c;", w.length);	
					
		}else if(rca.getIdCriterio() == 22){
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
				parrafo =  new Paragraph(l.getBoletaTicketBascula(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				if(rca.getCamposQueAplica().contains("12")){
					parrafo =  new Paragraph(l.getFechaEntrada(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				
				if(rca.getCamposQueAplica().contains("63")){
					parrafo =  new Paragraph(l.getVolumen(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
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
				parrafo =  new Paragraph(l.getFolioFacturaVenta(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				if(rca.getCamposQueAplica().contains("16,")){
					parrafo =  new Paragraph(l.getFechaEmisionFac(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				
				if(rca.getCamposQueAplica().contains("17,")){
					parrafo =  new Paragraph(l.getRfcFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("19,")){
					parrafo =  new Paragraph(l.getVolSolFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("65,")){
					parrafo =  new Paragraph(l.getVolTotalFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("20,")){
					parrafo =  new Paragraph(l.getImpSolFacVenta(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
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
				parrafo =  new Paragraph(l.getFolioDocPago(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 2, 1);
				t.addCell(cell);
				
				if(rca.getCamposQueAplica().contains("67,")){
					parrafo =  new Paragraph(l.getImpTotalPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				
				if(rca.getCamposQueAplica().contains("27,")){
					parrafo =  new Paragraph(l.getTipoDdocPago(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("16,")){
					parrafo =  new Paragraph(l.getFechaDocPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("29,")){
					parrafo =  new Paragraph(l.getBancoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("66,")){
					parrafo =  new Paragraph(l.getImpDocPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				if(rca.getCamposQueAplica().contains("68,")){
					parrafo =  new Paragraph(l.getImpDocPagoSinaxc(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 2, 1);
					t.addCell(cell);
				}
				claveBodegaTmp = l.getClaveBodega();
				nomEstadoTmp = l.getNombreEstado();
				if(siAplicaFolioContrato){				
					temFolioContrato = l.getFolioContrato();
				}
				contBitacoraDet ++;	
			}
	
		}else if(rca.getIdCriterio() == 25){
			Collections.sort(lstRendimientosProcedente);
			contBitacoraDet = 1;
			for(RendimientosProcedente l: lstRendimientosProcedente){			
				if(contBitacoraDet != 0){
					if(siAplicaFolioContrato){
						if(!temFolioContrato.equals(l.getFolioContrato()) && contBitacoraDet > 1){
							colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v6;6,"+totalVolPorContrato1+",v6;"	// AHS [LINEA] - 17022015
									+"7,"+totalVolPorContrato2+",v6",  w.length);	// AHS [LINEA] - 17022015
							totalVolPorContrato = 0;
							totalVolPorContrato1 = 0;
							totalVolPorContrato2 = 0;
						}
						
						if(!claveBodegaTmp.equals(l.getClaveBodega()) && contBitacoraDet > 1){
							colocarTotales("3,TOTAL BODEGA;5,"+totalVolPorBodega+",v6;6,"+totalVolPorBodega1+",v6;"	// AHS [LINEA] - 17022015
									+"7,"+totalVolPorBodega2+",v6",  w.length);				// AHS [LINEA] - 17022015				
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
					}else{
						crearColumna("S-1-\t","DET");								
					}				
				}	
			
				crearColumna("S-2-"+ l.getPaternoProductor()+" "+l.getMaternoProductor()+" "+l.getNombreProductor(),"DET");
				crearColumna("V6-3-"+l.getVolTotalFacturado()+";"+"V6-3-"+l.getRendimientoMaximoAceptable()+";"+"V6-3-"+l.getVolNoProcedente(), "DET");	// AHS [LINEA] - 17022015
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
				if(temFolioContrato.equals(temFolioContratoAux)){
					colocarTotales("3,TOTAL BODEGA;5,"+totalVolPorBodega+",v6;6,"+totalVolPorBodega1+",v6;"	// AHS [LINEA] - 17022015
							+"7,"+totalVolPorBodega2+",v6",  w.length);	// AHS [LINEA] - 17022015
					colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v6;6,"+totalVolPorContrato1+",v6;"	// AHS [LINEA] - 17022015
							+"7,"+totalVolPorContrato2+",v6",  w.length);	// AHS [LINEA] - 17022015
					colocarTotales("3,GRAN TOTAL;5,"+granTotalVol+",v6;6,"+granTotalVol1+",v6;"	// AHS [LINEA] - 17022015
							+"7,"+granTotalVol2+",v6",  w.length);	// AHS [LINEA] - 17022015					
				} else {
					colocarTotales("3,TOTAL CONTRATO;5,"+totalVolPorContrato+",v6;6,"+totalVolPorContrato1+",v6;"	// AHS [LINEA] - 17022015
							+"7,"+totalVolPorContrato2+",v6",  w.length);	// AHS [LINEA] - 17022015
					colocarTotales("3,TOTAL BODEGA;5,"+totalVolPorBodega+",v6;6,"+totalVolPorBodega1+",v6;"	// AHS [LINEA] - 17022015
							+"7,"+totalVolPorBodega2+",v6",  w.length);	// AHS [LINEA] - 17022015
					colocarTotales("3,GRAN TOTAL;5,"+granTotalVol+",v6;6,"+granTotalVol1+",v6;"	// AHS [LINEA] - 17022015
							+"7,"+granTotalVol2+",v6",  w.length);	// AHS [LINEA] - 17022015										
				}
				// AHS [LINEA] - 17022015
	
			}else{
				colocarTotales("3,TOTAL;4,"+totalVolPorBodega+",v;5,"+totalVolPorBodega1+",v;"
						+"6,"+totalVolPorBodega2+",v",  w.length);
				colocarTotales("3,TOTAL;4,"+granTotalVol+",v;5,"+granTotalVol1+",v;"
						+"6,"+granTotalVol2+",v",  w.length);
			}
			//end criterio = 25	
		}else if(rca.getIdCriterio() == 27){
			//Collections.sort(lstVolumenCumplido);
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
				granTotalVol += (l.getPrecioPactadoPorTonelada() != null ? l.getPrecioPactadoPorTonelada():0);						
				granTotalVol1 += (l.getVolumen() != null ? l.getVolumen():0);						
			}							
			colocarTotales("4,TOTAL;5,"+granTotalVol+",i;6,"+granTotalVol1+",v;",  w.length);
		}//end criterio = 27
	}

	private void colocarInfoEnPojo() throws ParseException {
		creaEncabezadoDetalle();
		for(BitacoraRelcompras b: rca.getLstBitacoraRelCompras() ){ 
			List<BitacoraRelcomprasDetalle> lstBitacoraDetalle = rDAO.consultaBitacoraRelcomprasDetalle(b.getIdBitacoraRelcompras());			
			for(BitacoraRelcomprasDetalle bd :lstBitacoraDetalle){
				String [] bS = bd.getMensaje().split(";");
				if(rca.getIdCriterio() == 1){					
					PrediosNoExistenBD p = new PrediosNoExistenBD();
					p.setFolioPredio(bS[0]!=null&&!bS[0].isEmpty()&&!bS[0].equals("null")?bS[0]:"");
					p.setFolioPredioSec(Integer.parseInt(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("0")?bS[1]:"-1"));
					p.setFolioPredioAlterno(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
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
					p.setProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setPaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setMaternoProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setNombreProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");					
					++columna;
					p.setFolioPredio(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					p.setFolioPredioSec(Integer.parseInt(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("0")?bS[columna]:"-1"));
					++columna;
					p.setFolioPredioAlterno(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					lstPrediosProdsContNoExistenBD.add(p);
				}else if(rca.getIdCriterio() == 5){
					ProductorPredioValidado pro = new ProductorPredioValidado(); 
					pro.setFolioProductor(Long.parseLong(bS[0]!=null&&!bS[0].isEmpty()&&!bS[0].equals("null")?bS[0]:""));
					pro.setPaternoProductor(bS[1]!=null&&!bS[1].isEmpty()&&!bS[1].equals("null")?bS[1]:"");
					pro.setMaternoProductor(bS[2]!=null&&!bS[2].isEmpty()&&!bS[2].equals("null")?bS[2]:"");
					pro.setNombreProductor(bS[3]!=null&&!bS[3].isEmpty()&&!bS[3].equals("null")?bS[3]:"");
					pro.setCurpProductor(bS[4]!=null&&!bS[4].isEmpty()&&!bS[4].equals("null")?bS[4]:"");
					pro.setRfcProductor(bS[5]!=null&&!bS[5].isEmpty()&&!bS[5].equals("null")?bS[5]:"");
					lstProductorPredioValidado.add(pro);					
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
					r.setRfcProductor(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setRfcFacVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					++columna;
					r.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
					lstRfcProductorVsRfcFactura.add(r);
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
					c.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setPrecioPactadoPorTonelada(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setImpSolFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setImporteContrato(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					c.setDiferenciaImporte(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
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
						ch.setFolioDocPago(bS[columna]);
						++columna;
						ch.setBancoSinaxc(bS[columna]);
						++columna;
						ch.setFechaDocPagoSinaxc((bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?Utilerias.convertirStringToDateyyyyMMdd(bS[columna]):null));				
						++columna;
						ch.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
						++columna;
						ch.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
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
						++columna;
						ch.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")&&!bS[columna].equals("-")?bS[columna]:"0"));
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
					f.setImpSolFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					System.out.println("Error "+bS[columna]);
					f.setImpTotalPagoSinaxc(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					f.setVolTotalFacVenta(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					
					lstFacturasVsPago.add(f);
				}else if(rca.getIdCriterio() == 19){
					columna = 0;
					PrecioPagadoMenor p = new PrecioPagadoMenor();
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
					p.setPesoNeto(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImporteCheque(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImporteFactura(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setImporteTotal(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPrecioEnRelCompras(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPrecioCalculado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setPrecioPagado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setObservacion(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0");
					lstPrecioPagadoMenor.add(p);
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
					p.setVolumenObservado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					lstBoletasFacturasPagosIncosistentes.add(p);
				}else if(rca.getIdCriterio() == 21){
					columna = 0;
					GeneralToneladasTotalesPorBodFac bo  = new GeneralToneladasTotalesPorBodFac();
					if(siAplicaFolioContrato){
						bo.setFolioContrato(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						++columna;
					}
					bo.setClaveBodega(bS[columna]);
					++columna;
					bo.setNombreBodega(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
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
					lstGeneralToneladasTotalesPorBodFac.add(bo);					
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
					bo.setFolioFacturaVenta(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					if(rca.getCamposQueAplica().contains("16,")){
						++columna;
						bo.setFechaEmisionFac(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
						
					}
					if(rca.getCamposQueAplica().contains("19,")){
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
						bo.setImpDocPagoSinaxc(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"");
					}	
					lstPagosCamposRequeridos.add(bo);
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
					p.setVolTotalFacturado(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setRendimientoMaximoAceptable(Double.parseDouble(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"));
					++columna;
					p.setVolNoProcedente(bS[columna]!=null&&!bS[columna].isEmpty()&&!bS[columna].equals("null")?bS[columna]:"0"); // AHS [LINEA] - 17022015
					lstRendimientosProcedente.add(p);
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
					lstVolumenCumplido.add(p);
				}
					
			}		
		}//end bitacora
		
	}

	private float[] configurarNumColumnaEnTabla() {
		float[] w = {1};
		if(rca.getIdCriterio() == 1){//Valida que exista predio en base de datos
			float[] x1 = {35,30,35}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 2){//"Predio se encuentre validado por la DR/DE"
			float[] x1 = {35,30,35}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 3){//"Predios esten pagados"
			float[] x1 = {35,30,35}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 26){//Valida que exista predios/productores/contratos en base de datos
			if(siAplicaFolioContrato){
				float[] x1 = {15,5,20,10,20,10,10,10}; // %
				w = x1;
			}else{
				float[] x1 = {15,10,10,35,10,10,10}; // %
				w = x1;
			}			
		// AHS [FIN]
		}else if(rca.getIdCriterio() == 4){//Productor exista en base de dato
			float[] x1 = {20,40,20,20}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 5){//Productores Estén asociados a Predios Validados
			float[] x1 = {25,25,25,25}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 6){//Productores Estén Asociados a Predios Pagados
			float[] x1 = {25,25,25,25}; // %
			w = x1;
		}else if(rca.getIdCriterio() == 7){//productores incosistentes
			if(siAplicaFolioContrato){
				float[] x1 = {15,5,10,20,10,10,10,10,10}; // %
				w = x1;
			}else{
				float[] x1 = {15,10,25,10,10,10,10,10}; // %
				w = x1;
			}			
		}else if(rca.getIdCriterio() == 8){//"Boletas no esten duplicadas por bodega"
			if(siAplicaFolioContrato){
				float[] x1 = {35,10,15,10,15,15}; // %
				w = x1;
			} else {
				float[] x1 = {40,15,15,15,15}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 9){//"Boletas fuera del periodo de acopio"
			if(siAplicaFolioContrato){
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {10,10,10,30,10,15,15}; // %
					w = x1;					
				} else {
					float[] x1 = {10,10,10,20,10,10,10,10,10}; // %
					w = x1;
				}
			} else {
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {10,10,35,15,15,15}; // %
					w = x1;
				} else {
					float[] x1 = {10,10,30,10,10,10,10,10}; // %
					w = x1;					
				}
			}
		
		}else if(rca.getIdCriterio() == 10){//"No se duplique el folio de la factura"
 			if(siAplicaFolioContrato){
				float[] x1 = {25,35,10,10,10,10}; // %
				w = x1;
			} else {
				float[] x1 = {35,35,10,10,10}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 11){//"La suma total de la facturas por productor no sea mayor a la suma de las Boletas"
			if(siAplicaFolioContrato){
				float[] x1 = {15,10,10,25,15,15,10}; // %
				w = x1;
			}else{
				float[] x1 = {15,15,40,15,15}; // %
				w = x1;
			}			
		}else if(rca.getIdCriterio() == 12){//"Que el rfc corresponda al productor"
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
					float[] x1 = {10,10,10,30,10,15,15}; // %
					w = x1;					
				} else {
					float[] x1 = {10,10,10,20,10,10,10,10,10}; // %
					w = x1;
				}
			} else {
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {10,10,35,15,15,15}; // %
					w = x1;					
				} else {
					float[] x1 = {10,10,30,10,10,10,10,10}; // %
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
				float[] x1 = {10,10,10,20,10,10,10,10,10}; // %
				w = x1;
			}else{
				float[] x1 = {10,10,30,10,10,10,10,10}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 16){//"NO SE REPITAN CHEQUES-BANCO POR EMPRESA"
			if(siAplicaFolioContrato){
				float[] x1 = {35,15,20,10,10,10}; // %
				w = x1;
			} else {
				float[] x1 = {40,15,25,10,10}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 17){//"CHEQUES FUERA DEL PERIODO DEL AUDITOR (SOLO APLICA AXC)."
			if(siAplicaFolioContrato){
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {15,10,10,15,10,10,10,10,10}; // %
					w = x1;					
				} else {
					float[] x1 = {10,5,10,15,10,10,10,10,8,8,8}; // %
					w = x1;
				}				
			}else{
				if(rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
					float[] x1 = {15,10,25,10,10,10,10,10};  // %
					w = x1;
				} else {
					float[] x1 = {10,5,20,10,10,10,10,8,8,8};  // %
					w = x1;					
				}
				
			}
			
		}else if(rca.getIdCriterio() == 18){//"MONTO TOTAL PAGADO SEA MAYOR A LA SUMA DE LOS TOTALES PAGADOS AL PRODUCTOR (FACTURA,CB)."
			if(siAplicaFolioContrato){
				float[] x1 = {15,10,10,35,10,10,10 }; // %
				w = x1;
			}else{
				float[] x1 = {15,10,30,15,15,15}; // %
				w = x1;
			}
			
		}else if(rca.getIdCriterio() == 19){//"PRECIO PAGADO MENOR"			
			if(siAplicaFolioContrato){
				float[] x1 = {7,4,7,10,9,9,9,9,9,9,9,9}; // %
				w = x1;
			}else{
				float[] x1 = {6,9,10,9,9,9,9,9,9,9,9}; // %
				w = x1;
			}	
		}else if(rca.getIdCriterio() == 20){//"BOLETAS, FACTURAS Y PAGOS INCONSISTENTES EN PRODUCTORES
			if(siAplicaFolioContrato){
				float[] x1 = {8,8,8,15,10,10,10,10,10,5,5,5,5}; // %
				w = x1;
			} else {
				float[] x1 = {10,10,10,20,10,10,10,5,5,5,5}; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 21){//"//TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS
			System.out.println("configurando numero de campos en la tabla");
			if(siAplicaFolioContrato){
				//float[] x1 = {7,4,7,10,9,9,9,9,9,9,9,9}; // %
				float[] x1 = {10,10,10,10,10,10,10,10,10,10}; // %
				w = x1;
			}else{
				float[] x1 = {10,10,10,10,10,10,10,10,10}; // %
				w = x1;
			}		
		
		}else if(rca.getIdCriterio() == 22){//"VALORES NULOS EN BOLETA" 
			StringBuilder composicionArreglo = new StringBuilder();	
			double c = 0;
			String [] arrayCamposQueAplica = rca.getCamposQueAplica().split(",");
			if(siAplicaFolioContrato){
				composicionArreglo.append("15,10,10,20,");
				c = 55/arrayCamposQueAplica.length;
			}else{
				composicionArreglo.append("15,10,20,");
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
				composicionArreglo.append("15,10,10,20,");
				c = 55/arrayCamposQueAplica.length;
			}else{
				composicionArreglo.append("15,10,20,");
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
			System.out.println("w lenb "+w.length);
		}else if(rca.getIdCriterio() == 24){//VALORES NULOS EN PAGOS
			StringBuilder composicionArreglo = new StringBuilder();	
			double c = 0;
			String [] arrayCamposQueAplica = rca.getCamposQueAplica().split(",");
			if(siAplicaFolioContrato){
				composicionArreglo.append("15,10,10,20,");
				c = 55/arrayCamposQueAplica.length;
			}else{
				composicionArreglo.append("15,10,20,");
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
				float[] x1 = {15,10,10,20,15,15,15 }; // %
				w = x1;
			}else{
				float[] x1 = {15,10,30,15,15,15 }; // %
				w = x1;
			}
		}else if(rca.getIdCriterio() == 27){//VOLUMEN DE FINIQUITO
			if(siAplicaFolioContrato){
				float[] x1 = {10,10,30,30,10,10}; // %
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
					colocarTotales(configTotales[1], numColumnas);
				}
										
				totalVolPorBodega = 0;						
				totalVolPorBodega1 = 0;						
				totalVolPorBodega2 = 0;						
				totalVolPorBodega3 = 0;						
				totalVolPorBodega4 = 0;						
				totalVolPorBodega5 = 0;						
				totalVolPorBodega6 = 0;						
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
		
	private void creaEncabezadoDetalle() {
		if(rca.getIdCriterio() == 1){//Valida que exista predio en base de datos
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
			parrafo =  new Paragraph("NOMBRE PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);			
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
		}else if(rca.getIdCriterio() == 3){//"PREDIOS ESTEN PAGADOS"
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
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PREDIO ALTERNO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("BOLETAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("FACTURAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PAGOS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 8){//BOLETAS O TICKETS DE BASCULA DUPLICADOS POR BODEGA
			if(siAplicaFolioContrato){	
				crearColumna("S-1-PRODUCTOR;S-1-BOLETA TICKET;S-1-PESO NETO ANA. (TON);S-1-FECHA DE ENTRADA;S-1-FOLIO CONTRATO;S-1-BODEGA;", "DET");
			}else{
				crearColumna("S-1-PRODUCTOR;S-1-BOLETA TICKET;S-1-PESO NETO ANA. (TON);S-1-FECHA DE ENTRADA;S-1-BODEGA;", "DET");
			}				
		}else if(rca.getIdCriterio() == 9){//"BOLETAS FUERA DE PERIODO DE ACOPIO "
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("BOLETA TICKET", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			if(!rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				parrafo =  new Paragraph("FECHA INI", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph("FECHA FIN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			parrafo =  new Paragraph("FECHA ENTRADA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PESO NETO ANA. (TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);			
		}else if(rca.getIdCriterio() == 10){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-PRODUCTOR;S-1-FACTURA DE VENTA;S-1-PESO NETO ANA. (TON);S-1-FECHA DE FACTURA;S-1-FOLIO CONTRATO;S-1-BODEGA;", "DET");
			}else{
				crearColumna("S-1-PRODUCTOR;S-1-FACTURA DE VENTA;S-1-PESO NETO ANA. (TON);S-1-FECHA DE FACTURA;S-1-BODEGA;", "DET");
			}	
		}else if(rca.getIdCriterio() == 11){//"LA SUMA TOTAL DE LA FACTURAS POR PRODUCTOR NO SEA MAYOR A LA SUMA DE LAS BOLETAS"
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("VOLUMEN DE BOLETAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("VOLUMEN DE FACTURAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);	
			parrafo =  new Paragraph("DIFERENCIA DE VOLUMEN", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 12){//"QUE EL RFC CORRESPONDA AL PRODUCTOR"
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("RFC PRODUCTOR", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("RFC FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);	
			parrafo =  new Paragraph("PESO NETO ANA. (TON) FAC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);	
		}else if(rca.getIdCriterio() == 13){//"Factura fuera del periodo del Auditor"
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("FOLIO FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			if(!rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				parrafo =  new Paragraph("FECHA INI", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph("FECHA FIN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}			
			parrafo =  new Paragraph("FECHA EMISIÓN", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PESO NETO ANA. (TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
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
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-P.N.A TOTAL EN FAC POR CONTRATO;S-1-PRECIO PACTADO POR TON (DLLS);S-1-IMPORTE FAC POR CONTRATO;S-1-IMPORTE CALCULADO PARA CONTRATO;S-1-DIFERENCIA A PAGAR", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-P.N.A TOTAL EN FAC POR CONTRATO;S-1-PRECIO PACTADO POR TON (DLLS);S-1-IMPORTE FAC POR CONTRATO;S-1-IMPORTE CALCULADO PARA CONTRATO;S-1-DIFERENCIA A PAGAR", "DET");
			}					
		}else if(rca.getIdCriterio() == 16){//"NO SE REPITAN CHEQUES-BANCO POR EMPRESA"
			if(siAplicaFolioContrato){	
				crearColumna("S-1-PRODUCTOR;S-1-FOLIO DOCUMENTO PAGO;S-1-BANCO;S-1-P.N.A. TOTAL DE LA FACTURA (TON.);S-1-FOLIO CONTRATO;S-1-BODEGA;", "DET");
			}else{
				crearColumna("S-1-PRODUCTOR;S-1-FOLIO DOCUMENTO PAGO;S-1-BANCO;S-1-P.N.A. TOTAL DE LA FACTURA (TON.);S-1-BODEGA;", "DET");
			}	
		}else if(rca.getIdCriterio() == 17){//"CHEQUES FUERA DEL PERIODO DEL AUDITOR (SOLO APLICA AXC)."
			colocarPrimerasColumnasEncDetalle();	
			if(!rca.getLstBitacoraRelCompras().get(0).getMensaje().contains("/")){
				parrafo =  new Paragraph("FECHA INI", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
				parrafo =  new Paragraph("FECHA FIN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			parrafo =  new Paragraph("FOLIO DOC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("BANCO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1); 
			t.addCell(cell);
			parrafo =  new Paragraph("FECHA DOC", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("P.N.A. TOTAL DE LA FACTURA (TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("POLIZA CHEQUE", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 18){
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("IMPORTE FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("IMPORTE PAGADO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PESO NETO ANA (FAC)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}else if(rca.getIdCriterio() == 19){//PRECIO PAGADO MENOR"
			colocarPrimerasColumnasEncDetalle();	
			parrafo =  new Paragraph("PESO NETO FAC (TON)", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("IMPORTE CHEQUE", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("IMPORTE FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("IMPORTE TOTAL", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);				
			parrafo =  new Paragraph("PRECIO EN REL DE COMPRAS", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PRECIO CALCULADO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			parrafo =  new Paragraph("PRECIO PAGADO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);				
			parrafo =  new Paragraph("OBSERVACION", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
							
		}else if(rca.getIdCriterio() == 20){//BOLETAS, FACTURAS Y PAGOS INCOSISTENTES EN PRODUCTORES			
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-PESO NETO ANA TOTAL EN FAC;S-1-PESO NETO ANA (BOL);S-1-PESO NETO ANA (FAC);"
						+ "S-1-VOLUMEN EN PAGOS;S-1-VOLUMEN NO PROCEDENTE;S-1-DIF ENTRE VOLUMEN;S-1-DIF ENTRE IMPORTE;S-1-DIF ENTRE RFC;S-1-VOLUMEN TOTAL OBSERVADO", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-PRODUCTOR;S-1-PESO NETO ANA TOTAL EN FAC;S-1-PESO NETO ANA (BOL);S-1-PESO NETO ANA (FAC);"
						+ "S-1-IMPORTE TOTAL PAGADO;S-1-RENDIMIENTO MAXIMO;S-1-DIF ENTRE VOLUMEN;S-1-DIF ENTRE IMPORTE;S-1-DIF ENTRE RFC;S-1-VOLUMEN TOTAL OBSERVADO", "DET");
			}	
		}else if(rca.getIdCriterio() == 21){//TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS	
			if(siAplicaFolioContrato){	
				crearColumna("S-1-FOLIO CONTRATO;S-1-BODEGA;S-1-NOMBRE BODEGA;S-1-ESTADO;S-1-PESO NETO ANA (BOL);S-1-PESO NETO ANA (FAC);S-1-TOTAL DE BOLETAS;S-1-TOTAL DE FACTURAS;"
					+ "S-1-TOTAL DE REGISTROS;S-1-PRODUCT BENEFICI", "ENC");
			}else{
				crearColumna("S-1-BODEGA;S-1-NOMBRE BODEGA;S-1-ESTADO;S-1-PESO NETO ANA (BOL);S-1-PESO NETO ANA (FAC);S-1-TOTAL DE BOLETAS;S-1-TOTAL DE FACTURAS;"
						+ "S-1-TOTAL DE REGISTROS;S-1-PRODUCT BENEFICI", "ENC");		
			}			
		}else if(rca.getIdCriterio() == 22){
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("BOLETA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			if(rca.getCamposQueAplica().contains("12,")){
				parrafo =  new Paragraph("FECHA ENTRADA", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("63,")){
				parrafo =  new Paragraph("P.N.A. DE LA BOLETA (TON.)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			
		}else if(rca.getIdCriterio() == 23){
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("FACTURA", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			if(rca.getCamposQueAplica().contains("16,")){
				parrafo =  new Paragraph("FECHA EMISIÓN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("17,")){
				parrafo =  new Paragraph("RFC FACTURA", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("19,")){
				parrafo =  new Paragraph("P.N.A. SOLICITADO PARA APOYO (TON.)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("65,")){
				parrafo =  new Paragraph("P.N.A. TOTAL DE LA FACTURA (TON.)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("20,")){
				parrafo =  new Paragraph("IMPORTE TOTAL FACTURADO ($)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			
		}else if(rca.getIdCriterio() == 24){
			colocarPrimerasColumnasEncDetalle();
			parrafo =  new Paragraph("FOLIO PAGO", TIMESROMAN08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
			if(rca.getCamposQueAplica().contains("67,")){
				parrafo =  new Paragraph("FECHA EMISIÓN", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("27,")){
				parrafo =  new Paragraph("TIPO DE DOCUMENTO DE PAGO", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("16,")){
				parrafo =  new Paragraph("FECHA", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("29,")){
				parrafo =  new Paragraph("BANCO", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("66,")){
				parrafo =  new Paragraph("IMPORTE DE DOCUMENTO DE PAGO ($)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}
			if(rca.getCamposQueAplica().contains("68,")){
				parrafo =  new Paragraph("PRECIO PAGADO POR TONELADA ($)", TIMESROMAN08BOLD);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}	
		}else if(rca.getIdCriterio() == 25){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-TOTAL FACTURADO;S-1-RENDIMIENTO MAXIMO ACEPTABLE;S-1-VOLUMEN NO ACEPTABLE;", "DET");
			}else{
				crearColumna("S-1-BODEGA;S-1-ESTADO;S-1-FOLIO CONTRATO;S-1-PRODUCTOR;S-1-TOTAL FACTURADO;S-1-RENDIMIENTO MAXIMO ACEPTABLE;S-1-VOLUMEN NO ACEPTABLE;", "DET");
			}
		}else if(rca.getIdCriterio() == 26){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-FOLIO CONTRATO", "DET");
			}
		}else if(rca.getIdCriterio() == 27){
			if(siAplicaFolioContrato){	
				crearColumna("S-1-BODEGA;S-1-FOLIO CONTRATO;S-1-COMPRADOR;S-1-VENDEDOR;S-1-PRECIO PACTADO POR TON (DLLS);S-1-VOLUMEN", "DET");
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
		lstPrediosNoExistenBD = new ArrayList<PrediosNoExistenBD>();
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
		lstPrecioPagadoProductor = new ArrayList<PrecioPagadoProductor>();
		lstChequesDuplicadoBancoPartipante = new ArrayList<ChequesDuplicadoBancoPartipante>();
		lstChequeFueraPeriodoAuditor = new ArrayList<ChequeFueraPeriodoAuditor>(); 
		listChequeFueraPeriodoContrato = new ArrayList< ChequeFueraPeriodoContrato>();
		lstFacturasVsPago = new ArrayList<FacturasVsPago>();
		lstPrecioPagadoMenor = new ArrayList<PrecioPagadoMenor>();		
		lstFacturaFueraPeriodoPago = new ArrayList<FacturaFueraPeriodoPago>();
		lstBoletasCamposRequeridos = new ArrayList<BoletasCamposRequeridos>();
		lstFacturasCamposRequeridos = new ArrayList<FacturasCamposRequeridos>();
		lstPagosCamposRequeridos = new ArrayList<PagosCamposRequeridos>();
		lstGeneralToneladasTotalesPorBodFac = new ArrayList<GeneralToneladasTotalesPorBodFac>();
		lstBoletasFacturasPagosIncosistentes = new ArrayList<BoletasFacturasPagosIncosistentes>();
		lstRendimientosProcedente = new ArrayList<RendimientosProcedente>();
		lstVolumenCumplido = new ArrayList<VolumenFiniquito>();
		lstPrecioPagPorTipoCambio = new ArrayList<PrecioPagPorTipoCambio>();
		
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
