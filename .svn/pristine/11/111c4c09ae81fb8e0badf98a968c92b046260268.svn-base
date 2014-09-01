package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.gob.comer.sipc.action.solicitudpago.CapturaPagosCartaAdhesionAction;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.domain.InicializacionEsquema;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.vistas.domain.CuentasBancariasV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class AtentaNotaPagosC extends PdfPageEventHelper {
	
	// Configuracion de fuentes
	
	// Configuracion de fuentes
	//private final Font ARIALBOLD10 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
	//private final Font ARIALNORMAL08 = FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL, Color.BLACK);
	//private final Font ARIALNORMAL08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN10 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12BOLD	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.BOLD, Color.BLACK);
	//private final Font ARIALBOLD12 = FontFactory.getFont(FontFactory.HELVETICA, 1, Font.NORMAL, Color.BLACK);
	
	private CapturaPagosCartaAdhesionAction apa;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	private PagosDAO pDAO;
	private CatalogosDAO cDAO;
	private InscripcionDAO iDAO;
	private List<PagosV> pagoV;
	private List<PagosDetalleV> pagoDet;
	private List<CuentasBancariasV> cuentaBancaria;
	private List<InicializacionEsquema> iniciaEsq;
	private StringBuilder texto;
	private PdfPTable piePagina;

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;	

	public AtentaNotaPagosC(CapturaPagosCartaAdhesionAction apa, Session session) {
		this.apa = apa;
		this.session = session;
		pDAO = new PagosDAO(session);
		cDAO = new CatalogosDAO(session);
		iDAO = new InscripcionDAO(session);
		
		
	}
	
	public void generarAtentaNotaPago(Long idPago) throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 50);
		writer = PdfWriter.getInstance(document, new FileOutputStream(apa.getRutaSalida()));
		writer.setPageEvent(this);
		document.open();
			
		/*Consigue los datos del pago*/
		if(idPago != -1){
			pagoV = pDAO.consultaPagosV(idPago);
			System.out.println(pagoV.get(0).getIdPrograma());
			pagoDet = pDAO.consultaPagosDetalleV(0, idPago);
			iniciaEsq = iDAO.consultaInicializacionPrograma(pagoV.get(0).getIdPrograma());
			cuentaBancaria = cDAO.consultaCtaBancariaV(0, 0, pagoV.get(0).getClabe());
			document.newPage();
			getEncabezado();
			getLugarYFecha(pagoV.get(0).getFechaAtentaNota());
			addEmptyLine(1);
			getDestinatario();
			addEmptyLine(1);
			Image img1 = Image.getInstance(apa.getRutaMarcaAgua()); 
			img1.setAlignment(Image.MIDDLE | Image.UNDERLYING); 
			document.add(img1);
			getCuerpo();
			
			addEmptyLine(1);
			getEmisor();
		}
		document.close();
	}
	
	private void getAnexoDetallePagos() throws DocumentException {
		PdfPCell cell = null;
		float[] w = {1};
		Double sumaVolumen=0.0, sumaImporte = 0.0;
		
		if(apa.getCriterioPago() == 3){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				float[] x1 = {20, 20, 20, 20, 20}; // %
				w = x1;
			} else {
				float[] x2 = {20, 15, 15, 15, 20, 15}; // %
				w = x2;
			}
		} else if(apa.getCriterioPago() == 2){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				float[] y1 = {35, 35, 30}; // %	
				w =y1;				
			} else {
				float[] y2 = {25, 25, 25, 25}; // %	
				w =y2;
			}
		} else {
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				float[] z1 = {25, 25, 25, 25}; // %	
				w =z1;
			} else {
				float[] z2 = {20, 20, 20, 20, 20}; // %	
				w =z2;
			}
		}
		
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
		if(apa.getCriterioPago()== 1){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				parrafo =  new Paragraph("VOLUMEN (Ton)", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 2);
				t.addCell(cell);				
			} else {
				parrafo =  new Paragraph("VARIEDAD", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 2);
				t.addCell(cell);

				parrafo =  new Paragraph("VOLUMEN (Ton)", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 12);
				t.addCell(cell);
			}

			parrafo =  new Paragraph("IMPORTE ($)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);

			parrafo =  new Paragraph("ESTADO(S)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);
			
			parrafo =  new Paragraph("CUOTA*", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);
		} else if(apa.getCriterioPago()== 2){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				parrafo =  new Paragraph("ETAPA", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 2);
				t.addCell(cell);				
			} else {
				parrafo =  new Paragraph("VARIEDAD", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 2);
				t.addCell(cell);
				
				parrafo =  new Paragraph("ETAPA", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 12);
				t.addCell(cell);
			}

			parrafo =  new Paragraph("IMPORTE ($)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);

			parrafo =  new Paragraph("ESTADO(S)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);
		} else if(apa.getCriterioPago()==3){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				parrafo =  new Paragraph("ETAPA", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 2);
				t.addCell(cell);				
			} else {
				parrafo =  new Paragraph("VARIEDAD", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 2);
				t.addCell(cell);

				parrafo =  new Paragraph("ETAPA", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 12);
				t.addCell(cell);
			}

			parrafo =  new Paragraph("VOLUMEN (Ton)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);

			parrafo =  new Paragraph("IMPORTE ($)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);

			parrafo =  new Paragraph("ESTADO(S)", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);
			
			parrafo =  new Paragraph("CUOTA*", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 12);
			t.addCell(cell);
		}
		
		for (PagosDetalleV pagoAux : pagoDet){
			if(apa.getCriterioPago()== 1){
				if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
					parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(pagoAux.getVolumen()), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 13);
					t.addCell(cell);
				} else {
					parrafo =  new Paragraph(pagoAux.getVariedad(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 13);
					t.addCell(cell);	

					parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(pagoAux.getVolumen()), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 6);
					t.addCell(cell);
				}

				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(pagoAux.getImporte()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);			

				parrafo =  new Paragraph(pagoAux.getEstado(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);			
				
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(pagoAux.getCuota()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);				
			} else if(apa.getCriterioPago()== 2){
				if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
					parrafo =  new Paragraph(pagoAux.getEtapa(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 13);
					t.addCell(cell);						
				} else {
					parrafo =  new Paragraph(pagoAux.getVariedad(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 13);
					t.addCell(cell);	

					parrafo =  new Paragraph(pagoAux.getEtapa(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 6);
					t.addCell(cell);
				}

				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(pagoAux.getImporte()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);			

				parrafo =  new Paragraph(pagoAux.getEstado(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);			
			} else if(apa.getCriterioPago()== 3){
				if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
					parrafo =  new Paragraph(pagoAux.getEtapa(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 13);
					t.addCell(cell);						
				} else {
					parrafo =  new Paragraph(pagoAux.getVariedad(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 13);
					t.addCell(cell);	

					parrafo =  new Paragraph(pagoAux.getEtapa(), TIMESROMAN08);
					cell = new PdfPCell(parrafo);
					cell =createCell(parrafo, 0, 1, 6);
					t.addCell(cell);	
				}

				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(pagoAux.getVolumen()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);	

				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(pagoAux.getImporte()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);			

				parrafo =  new Paragraph(pagoAux.getEstado(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);	
				
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(pagoAux.getCuota()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 6);
				t.addCell(cell);
			}
			
			sumaVolumen += pagoAux.getVolumen();
			sumaImporte += pagoAux.getImporte();
		}
		
		if(apa.getCriterioPago()== 1){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(sumaVolumen), TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 13);
				t.addCell(cell);
			} else {
				parrafo =  new Paragraph("TOTAL:", TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);	

				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(sumaVolumen), TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 13);
				t.addCell(cell);
			}

			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(sumaImporte), TIMESROMAN10);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 6);
			t.addCell(cell);			

			parrafo =  new Paragraph("", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);			
			
			parrafo =  new Paragraph("", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);				
		} else if(apa.getCriterioPago()== 2){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				parrafo =  new Paragraph("TOTAL:", TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);						
			} else {
				parrafo =  new Paragraph("", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);	

				parrafo =  new Paragraph("TOTAL:", TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);
			}

			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(sumaImporte), TIMESROMAN10);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 13);
			t.addCell(cell);			

			parrafo =  new Paragraph("", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);			
		} else if(apa.getCriterioPago()== 3){
			if(pagoDet.get(0).getVariedad()==null||pagoDet.get(0).getVariedad()==""){
				parrafo =  new Paragraph("TOTAL:", TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);						
			} else {
				parrafo =  new Paragraph("", TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);	

				parrafo =  new Paragraph("TOTAL:", TIMESROMAN10);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 1);
				t.addCell(cell);	
			}

			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(sumaVolumen), TIMESROMAN10);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 13);
			t.addCell(cell);	

			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(sumaImporte), TIMESROMAN10);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 6);
			t.addCell(cell);			

			parrafo =  new Paragraph("", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);	
			
			parrafo =  new Paragraph("", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 1);
			t.addCell(cell);
		}		

		document.add(t);
	}

	private void getDatosCuentaBancaria() throws DocumentException {
		PdfPCell cell = null;
		float[] x = {20, 25, 5, 20, 30}; // %
		
		PdfPTable t = new PdfPTable(x);
		t.setWidthPercentage(100);
		parrafo =  new Paragraph("Banco", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 2);
		t.addCell(cell);
		parrafo =  new Paragraph(cuentaBancaria.get(0).getBanco(), TIMESROMAN10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 12);
		t.addCell(cell);
		parrafo =  new Paragraph("", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);			
		/*parrafo =  new Paragraph("Plaza", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 2);
		t.addCell(cell);			
		parrafo =  new Paragraph(cuentaBancaria.get(0).getNombrePlaza(), TIMESROMAN10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 12);
		t.addCell(cell);*/
		parrafo =  new Paragraph("Clabe", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 2);
		t.addCell(cell);			
		parrafo =  new Paragraph(cuentaBancaria.get(0).getClabe(), TIMESROMAN10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 12);
		t.addCell(cell);

		parrafo =  new Paragraph("No. Cuenta", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 13);
		t.addCell(cell);
		parrafo =  new Paragraph(cuentaBancaria.get(0).getNumeroCuenta(), TIMESROMAN10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 6);
		t.addCell(cell);
		parrafo =  new Paragraph("", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);			
		

		/*parrafo =  new Paragraph("No. Sucursal", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 13);
		t.addCell(cell);
		parrafo =  new Paragraph(cuentaBancaria.get(0).getSucursal(), TIMESROMAN10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 6);
		t.addCell(cell);*/
/*		parrafo =  new Paragraph("", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);		*/
		
		parrafo =  new Paragraph("Estado", TIMESROMAN10NORMAL);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 13);
		t.addCell(cell);			
		parrafo =  new Paragraph(cuentaBancaria.get(0).getEstado().toUpperCase(), TIMESROMAN10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 6);
		t.addCell(cell);

		document.add(t);
	}

	public  void getEncabezado() throws MalformedURLException, IOException, DocumentException {
			PdfPCell cell = null;
			PdfPTable table1 = null;	
			Paragraph enunciado = null;
			/**TABLA DE LOGOS**/	
			Image sagarpa = Image.getInstance(apa.getRutaImagen());
			sagarpa.scalePercent(50, 50);
			table1= new PdfPTable(2);
			table1.setWidthPercentage(100);
			//enunciado=new Paragraph("ASERCA\nCoordinación General de Comercialización", TIMESROMAN12);
			enunciado = new Paragraph();
			enunciado.add(new Chunk("ASERCA\nCoordinación General de Comercialización\nDirección General de Desarrollo de Mercados\nDirección de Pagos de Apoyos a la Comercialización", TIMESROMAN12));
			enunciado.add(new Chunk("\n\n"+apa.getLeyendaOficio().substring(0, 41)+"\n"+apa.getLeyendaOficio().substring(41, apa.getLeyendaOficio().length()), TIMESROMAN10NORMAL));
			cell =	createCell(null, 0, 2, 1, sagarpa);
			table1.addCell(cell);
			cell =	createCell(enunciado, 0, 3, 1, null);
			table1.addCell(cell);
	
			document.add(table1);
	}
	private void getLugarYFecha(Date fechaAtentaNota) throws DocumentException {
		String fechaAN = " a "+new SimpleDateFormat("dd").format(fechaAtentaNota).toString()+" de "
                + TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(fechaAtentaNota).toString()))+" de "
                + new SimpleDateFormat("yyyy").format(fechaAtentaNota).toString();
		parrafo = new Paragraph("ATENTA NOTA No. "+"\nMéxico, D.F. "+fechaAN, TIMESROMAN12);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	}
	private void getDestinatario() throws DocumentException {
		
		texto = new StringBuilder()
		.append(apa.getDestinatario().getNombre()).append("\n")
		.append(apa.getDestinatario().getPuesto()).append("\n")
		.append("P R E S E N T E");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12BOLD);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	
	}
	private void getCuerpo() throws DocumentException{
		String textoFianza = ""; 
		StringBuilder textoEsquema = new StringBuilder();
		String leyendaCriterio = "";
		Double cantidadCriterio = 0.0; 

		
		textoEsquema.append(new Chunk("En referencia a la operación del esquema ",TIMESROMAN10NORMAL)).append(new Chunk(pagoV.get(0).getNombrePgrLarga(),TIMESROMAN10))
					.append(new Chunk(", me permito someter a su consideración lo siguiente:", TIMESROMAN10NORMAL));
		parrafo= new Paragraph(textoEsquema.toString(), TIMESROMAN10NORMAL);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
				
		/*if (apa.getFianza().equals("SI") && pagoV.get(0).getNumPago().equals("Primer Pago")){
			textoFianza = " (fianza)";
		}*/
		if (apa.getCriterioPago()==1){
			leyendaCriterio = "volumen";
			cantidadCriterio = pagoV.get(0).getVolumenAutorizado(); 
		} else if (apa.getCriterioPago()==2){
			leyendaCriterio = "importe";
			cantidadCriterio = pagoV.get(0).getImporteAutorizado();
		} else if (apa.getCriterioPago()==3){
			leyendaCriterio = "volumen";
			cantidadCriterio = pagoV.get(0).getVolumenAutorizado();
		}
			
		StringBuilder texto = new StringBuilder();
		texto.append(new Chunk("Derivado de la revisión del expediente con Carta de Adhesión ",TIMESROMAN10NORMAL))
			 .append(new Chunk(pagoV.get(0).getNoCarta(),TIMESROMAN10))
			 .append(new Chunk(" del solicitante ",TIMESROMAN10NORMAL)).append(new Chunk(pagoV.get(0).getNombreComprador(),TIMESROMAN10))
			 .append(new Chunk(" con RFC ",TIMESROMAN10NORMAL)).append(new Chunk(pagoV.get(0).getRfc(),TIMESROMAN10))
			 .append(new Chunk(", el cual participa con un ",TIMESROMAN10NORMAL)).append(new Chunk(leyendaCriterio,TIMESROMAN10)).append(new Chunk(" autorizado de ",TIMESROMAN10NORMAL))
			 .append(new Chunk(TextUtil.formateaNumeroComoVolumen(cantidadCriterio),TIMESROMAN10)) 
			 .append(new Chunk(" toneladas del cultivo ",TIMESROMAN10NORMAL)).append(new Chunk(pagoV.get(0).getCultivo().toUpperCase(),TIMESROMAN10))
			 .append(new Chunk(", se ha identificado un ",TIMESROMAN10NORMAL)).append(new Chunk(leyendaCriterio,TIMESROMAN10)).append(new Chunk(" susceptible de apoyo correspondiente al ",TIMESROMAN10NORMAL))
			 .append(new Chunk(pagoV.get(0).getNumPago(),TIMESROMAN10))
			 .append(new Chunk(textoFianza,TIMESROMAN10))
			 .append(new Chunk(", conforme a los datos que a continuación se especifican: ",TIMESROMAN10NORMAL));
		parrafo = new Paragraph(texto.toString(), TIMESROMAN10NORMAL);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
				
		getAnexoDetallePagos();
		if(apa.getCriterioPago()== 1 || apa.getCriterioPago()==3){
			parrafo = new Paragraph("* Cuota: corresponde al monto del apoyo por tonelada establecido en "+ iniciaEsq.get(0).getLeyendaAtentaNota() , TIMESROMAN08);
			parrafo.setAlignment(Element.ALIGN_CENTER);
			document.add(parrafo);	
		}
		parrafo = new Paragraph("Datos de la cuenta bancaria:", TIMESROMAN10NORMAL);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
		
		getDatosCuentaBancaria();
		addEmptyLine(1);
		parrafo = new Paragraph("Sin más por el momento reciba un cordial saludo.", TIMESROMAN10NORMAL);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);
	}
	
    private void getEmisor() throws DocumentException {
		parrafo = new Paragraph("A T E N T A M E N T E", TIMESROMAN10);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);	
		addEmptyLine(1);
		parrafo = new Paragraph(apa.getEmisor().getNombre(), TIMESROMAN10);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
		parrafo = new Paragraph(apa.getEmisor().getPuesto(), TIMESROMAN10);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
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
	
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent(); 
		try {
			System.out.println("pagina"+writer.getPageNumber());
			/*if(writer.getPageNumber()==1){
				System.out.println("p: "+writer.getPageNumber());
				piePagina();
			}*/
			piePagina();
			if(writer.getPageNumber()<=1){
				piePagina.writeSelectedRows(0, -1, document.left(), document.bottom()-5, cb);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	private PdfPCell createCell(Paragraph enunciado, int colspan, int alineamiento, int tipoBorde){
		return createCell(enunciado, colspan, alineamiento, tipoBorde, null);
	}
	private PdfPCell createCell(Paragraph enunciado, int colspan, int alineamiento, int tipoBorde, Image imagen)
	{
		PdfPCell cell = null;
		if(enunciado==null){
			cell =new PdfPCell(imagen);
		}else if(imagen==null){
			cell =new PdfPCell(enunciado);
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
		cell.setPadding(4);
		return cell;
	}

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

}
