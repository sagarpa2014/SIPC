
package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

import mx.gob.comer.sipc.action.seguimiento.SeguimientoAction;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.vistas.domain.SeguimientoCentroAcopioV;

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

public class ReporteSeguimientoAcopio extends PdfPageEventHelper {
	
	
	// Configuracion de fuentes
	//private final Font ARIALBOLD10 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
	@SuppressWarnings("unused")
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);
	private final Font TIMESROMANBOLD10 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
	private final Font TIMESROMANBOLD10UND = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.UNDERLINE, Color.BLACK);
	
	//private final Font ARIALNORMAL08 = FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL, Color.BLACK);
	//private final Font ARIALNORMAL08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN07	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 7,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN14	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 14,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMANBOLD12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.BOLD, Color.BLACK);
	private final Font TIMESROMANBOLD16	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,Font.BOLD, Color.BLACK);
	//private final Font ARIALBOLD12 = FontFactory.getFont(FontFactory.HELVETICA, 1, Font.NORMAL, Color.BLACK);
	

	private SeguimientoAction ata;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
//	private PdfPTable piePagina;
	public ReporteSeguimientoAcopio(SeguimientoAction ata) {
		this.ata = ata;
	}
	
	public void generarReporteSeguimientoAcopio() throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 80);
		writer = PdfWriter.getInstance(document, new FileOutputStream(ata.getRutaSalida()));
		writer.setPageEvent(this);
		document.open();
		getEncabezado();
		addEmptyLine(1);
		getTitulo();
		addEmptyLine(1);
		getCiclo();
		//addEmptyLine(1);
		getPeriodo();
		addEmptyLine(1);
		getCuerpo();
		getEmisor();
		document.close();
	}
	
	public  void getEncabezado() throws MalformedURLException, IOException, DocumentException {
			PdfPCell cell = null;
			PdfPTable table1 = null;	
			Paragraph enunciado = null;
			/**TABLA DE LOGOS**/	
			Image sagarpa = Image.getInstance(ata.getRutaLogoS());
			sagarpa.scalePercent(50, 50);
			Image aserca = Image.getInstance(ata.getRutaLogoA());
			aserca.scalePercent(50, 50);

			table1= new PdfPTable(2);
			table1.setWidthPercentage(100);
			cell =	createCell(null, 0, 2, 1, sagarpa);
			table1.addCell(cell);
			cell =	createCell(null, 0, 3, 1, aserca);
			table1.addCell(cell);

			enunciado = new Paragraph();
			enunciado.add(new Chunk("AGENCIA DE SERVICIOS A LA COMERCIALIZACION\n Y DESARROLLO DE MERCADOS AGROPECUARIOS", TIMESROMANBOLD12));
			cell =	createCell(enunciado, 2, 1, 1, null);
			table1.addCell(cell);
			enunciado = new Paragraph();
			enunciado.add(new Chunk("Mecánica de Registro, Actualización y Seguimiento de Centros de Acopio", TIMESROMAN12));			
			cell =	createCell(enunciado, 2, 1, 1, null);
			table1.addCell(cell);

			document.add(table1);
	}
	
	private void getTitulo() throws DocumentException{
		parrafo = new Paragraph("REPORTE DE SEGUIMIENTO DE COSECHA EN CENTROS DE ACOPIO", TIMESROMANBOLD10);
		parrafo.setAlignment(Rectangle.ALIGN_CENTER);
		parrafo.setLeading(1,1);
		document.add(parrafo);
	}
	private void getCiclo() throws DocumentException {
		parrafo = new Paragraph("CICLO AGRÍCOLA "+ata.getCiclo(), TIMESROMANBOLD10);
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Rectangle.ALIGN_CENTER);
		document.add(parrafo);	
	}
	private void getPeriodo() throws DocumentException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedPeriodoInicial = formatter.format(ata.getPeriodoInicial());
		String formattedPeriodoFinal = formatter.format(ata.getPeriodoFinal());
		
		parrafo = new Paragraph("PERIODO DE: "+formattedPeriodoInicial+" AL: "+formattedPeriodoFinal, TIMESROMANBOLD10UND);
		parrafo.setAlignment(Rectangle.ALIGN_LEFT);
		document.add(parrafo);	
	}

	private void getEmisor() throws DocumentException {
		PdfPCell cell = null;
		float[] w = {1};
		float [] y = {48, 4, 48}; // %	
		w = y;
		
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);

		parrafo =  new Paragraph("", TIMESROMAN07);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("", TIMESROMAN07);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("", TIMESROMAN07);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("GERENTE/ALMACENISTA", TIMESROMANBOLD10);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("", TIMESROMANBOLD10);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("RECIBO INFORMACIÓN", TIMESROMANBOLD10);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);

		parrafo =  new Paragraph("Empresa/Centro de Acopio\n\n\n", TIMESROMANBOLD10);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("\n\n\n", TIMESROMANBOLD10);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph("En Representación de ASERCA\n\n\n", TIMESROMANBOLD10);
		parrafo.setLeading(1, 1);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("Nombre, Firma y Sello del Centro de Acopio", TIMESROMANBOLD10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 10);
		t.addCell(cell);
		parrafo =  new Paragraph("", TIMESROMANBOLD10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph("Nombre y Firma de ASERCA", TIMESROMANBOLD10);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 10);
		t.addCell(cell);
		
		document.add(t);
	}

	private void addEmptyLine(int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			parrafo = new Paragraph("\n");
			document.add(parrafo);
		}
	}
	
	private void getCuerpo() throws DocumentException {
		PdfPCell cell = null;
		float[] w = {1};
		float [] y = {35, 15, 50}; // %	
		w = y;
		
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
		for (SeguimientoCentroAcopioV reporte : ata.getLstSeguimientoAcopio()){
			
			parrafo =  new Paragraph("CLAVE CENTRO ACOPIO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getClaveBodega(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);
		
			parrafo =  new Paragraph("NOMBRE DEL CENTRO DE ACOPIO REGISTRADO EN ASERCA:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getNombreBodega(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("NOMBRE LOCAL O REGIONAL DEL CENTRO DE ACOPIO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getNombreLocalBodega(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("OPERADOR DEL CENTRO DE ACOPIO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getNombreOperador(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("COMERCIALIZADOR/COMPRADOR:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getNombreComprador(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("CULTIVO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getNombreCultivo(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("VARIEDAD:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getNombreVariedad(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("VOLUMEN MERCADO LIBRE:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getVolumenMercadoLibre())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("VOLUMEN AXC:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getVolumenAXC())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("ACOPIO TONELADAS:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getAcopioTotalTon())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("PAGADO", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			t.addCell(cell);
			
			PdfPTable nested2 = new PdfPTable(1);
			parrafo =  new Paragraph("Tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested2.addCell(cell);
			parrafo =  new Paragraph("%:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested2.addCell(cell);

			t.addCell(new PdfPCell(nested2));

			nested2 = new PdfPTable(1);			
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getPagadoTon()), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested2.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(reporte.getPagadoPorcentaje()), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested2.addCell(cell);

			t.addCell(new PdfPCell(nested2));
		
			parrafo =  new Paragraph("MOVILIZADO", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			t.addCell(cell);
			
			PdfPTable nested4 = new PdfPTable(1);
			parrafo =  new Paragraph("FURGON:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested4.addCell(cell);
			parrafo =  new Paragraph("CAMION:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested4.addCell(cell);
			parrafo =  new Paragraph("MARITIMO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested4.addCell(cell);
			parrafo =  new Paragraph("TOTAL:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested4.addCell(cell);
			
			t.addCell(new PdfPCell(nested4));
			
			nested4 = new PdfPTable(1);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getMfurgon())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested4.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getMcamion())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested4.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getMmaritimo())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested4.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getMtotal())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested4.addCell(cell);

			t.addCell(new PdfPCell(nested4));
			
			parrafo =  new Paragraph("EXISTENCIA (ACOPIO-MOVILIZADO):", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(reporte.getExistenciaAM())+" tons.", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("DESTINO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getDestino(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("OBSERVACIONES:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getObservaciones(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

/*			
			parrafo =  new Paragraph("PRECIO PROMEDIO PAGADO", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			t.addCell(cell);
			
			PdfPTable nested3 = new PdfPTable(1);
			parrafo =  new Paragraph("AxC:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);
			nested3.addCell(cell);
			parrafo =  new Paragraph("Libre:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 2);		
			nested3.addCell(cell);

			t.addCell(new PdfPCell(nested3));
			
			nested3 = new PdfPTable(1);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(reporte.getPrecioPromPagAXC()), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested3.addCell(cell);
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(reporte.getPrecioPromPagLibre()), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			nested3.addCell(cell);
			
			t.addCell(new PdfPCell(nested3));
*/
			parrafo =  new Paragraph("PRECIO PROMEDIO PAGADO:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph("$ "+TextUtil.formateaNumeroComoCantidad(reporte.getPrecioPromPagAXC()), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);
			
			parrafo =  new Paragraph("AVANCE COSECHA:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			parrafo =  new Paragraph(reporte.getAvanceCosecha(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);

			parrafo =  new Paragraph("FECHA:", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 2, 2, 2);
			t.addCell(cell);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String formattedFechaEnvio = formatter.format(reporte.getFechaEnvio());
			parrafo =  new Paragraph(formattedFechaEnvio, TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 2);
			t.addCell(cell);
		}
		document.add(t);		
	}

	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent(); 
		try {
			System.out.println("pagina"+writer.getPageNumber());
			/*if(writer.getPageNumber()==1){
				System.out.println("p: "+writer.getPageNumber());
				piePagina();
			}*/
//			piePagina();
//			if(writer.getPageNumber()<=1){
//				piePagina.writeSelectedRows(0, -1, document.left(), document.bottom()-1, cb);
//			}
			
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

}
