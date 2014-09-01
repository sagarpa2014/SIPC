package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import mx.gob.comer.sipc.action.inscripcion.OficioEntregaCartasAction;
import mx.gob.comer.sipc.domain.Personal;
import mx.gob.comer.sipc.vistas.domain.CartasAdhesionV;

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

public class OficioEntregaCartas extends PdfPageEventHelper {
	
	
	// Configuracion de fuentes
	//private final Font ARIALBOLD10 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);
	//private final Font ARIALNORMAL08 = FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL, Color.BLACK);
	//private final Font ARIALNORMAL08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.NORMAL, Color.BLACK);
	//private final Font ARIALBOLD12 = FontFactory.getFont(FontFactory.HELVETICA, 1, Font.NORMAL, Color.BLACK);
	

	private OficioEntregaCartasAction ata;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	private PdfPTable piePagina;
	private StringBuilder texto;
	public OficioEntregaCartas(OficioEntregaCartasAction ata) {
		this.ata = ata;
	}
	
	public void generarOficioEntregaCartas() throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 100);
		writer = PdfWriter.getInstance(document, new FileOutputStream(ata.getRutaSalida()));
		writer.setPageEvent(this);
		document.open();
		getEncabezado();
		addEmptyLine(1);
		getLugarYFecha();
		addEmptyLine(1);
		getDestinatario();
		addEmptyLine(1);
		Image img1 = Image.getInstance(ata.getRutaMarcaAgua()); 
		img1.setAlignment(Image.MIDDLE | Image.UNDERLYING); 
		document.add(img1);
		if(ata.getLstCartasV().size()>10){
			getCuerpo();
			addEmptyLine(1);
			getEmisor();
			document.newPage();
			getAnexoDetallePagos();
		} else {
			getCuerpoCompleto();
			addEmptyLine(1);
			getEmisor();			
		}
		document.close();
	}
	private void getAnexoDetallePagos() throws DocumentException {
		Paragraph pTitulo = new Paragraph();
		
		Paragraph pOficio = new Paragraph();
		pOficio.setAlignment(Element.ALIGN_RIGHT);
		pOficio.add(new Chunk("ANEXO AL OFICIO "+ata.getClaveOficio()+ata.getNoOficio()+ata.getAnioOficio(), TIMESROMAN10));
		pOficio.setSpacingAfter(10f);
		document.add(pOficio);
		
		
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		pTitulo.add(new Chunk("DETALLE DE CARTAS DE ADHESIÓN", TIMESROMAN10));
		pTitulo.setSpacingAfter(10f);
		document.add(pTitulo);
		
		PdfPCell cell = null;
		float[] w = {10, 70, 20};		
		
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
		parrafo =  new Paragraph("No.", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		parrafo =  new Paragraph("Comprador", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		parrafo =  new Paragraph("Carta de Adhesión", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 2);
		t.addCell(cell);		
		int i=1;
		for (CartasAdhesionV cartaAux : ata.getLstCartasV()){
			parrafo =  new Paragraph(i+"", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 5);
			t.addCell(cell);
			parrafo =  new Paragraph(cartaAux.getComprador(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 5);
			t.addCell(cell);
			parrafo =  new Paragraph(cartaAux.getCarta(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 13);
			t.addCell(cell);
			i++;
		}				
		document.add(t);
	}

	public  void getEncabezado() throws MalformedURLException, IOException, DocumentException {
			PdfPCell cell = null;
			PdfPTable table1 = null;	
			Paragraph enunciado = null;
			/**TABLA DE LOGOS**/	
			Image sagarpa = Image.getInstance(ata.getRutaImagen());
			sagarpa.scalePercent(50, 50);
			table1= new PdfPTable(2);
			table1.setWidthPercentage(100);
			//enunciado=new Paragraph("ASERCA\nCoordinación General de Comercialización", TIMESROMAN12);
			enunciado = new Paragraph();
			enunciado.add(new Chunk("ASERCA\nCoordinación General de Comercialización\n"+ata.getDireccionEmisor(), TIMESROMAN12));
			enunciado.add(new Chunk("\n\n"+ata.getLeyendaOficio().substring(0, 41)+"\n"+ata.getLeyendaOficio().substring(41, ata.getLeyendaOficio().length()), TIMESROMAN10NORMAL));
			cell =	createCell(null, 0, 2, 1, sagarpa);
			table1.addCell(cell);
			cell =	createCell(enunciado, 0, 3, 1, null);
			table1.addCell(cell);
	
			document.add(table1);
	}
	private void getLugarYFecha() throws DocumentException {
		parrafo = new Paragraph("N° de Oficio "+ata.getClaveOficio()+ata.getNoOficio()+ata.getAnioOficio()+"\nMéxico, D.F. "+ata.getFechaActual(), TIMESROMAN12);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	}
	private void getDestinatario() throws DocumentException {
		
		texto = new StringBuilder()
		.append(ata.getDestinatario().getNombre()).append("\n")
		.append(ata.getDestinatario().getPuesto()).append("\n")
		.append("P R E S E N T E");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	
	}
	private void getCuerpoCompleto() throws DocumentException{
		Paragraph pTitulo = new Paragraph();

		parrafo= new Paragraph("Me refiero al "+ata.getNombrePrograma()+".", TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
		StringBuilder texto = new StringBuilder();
		texto.append("Al respecto, anexo al presente envío a Usted, para la atención correspondiente, original de las Cartas de Adhesión ")
			 .append("firmadas por las partes que en ellas intervienen de los participantes que acontinuación se indican.");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);	
		
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		pTitulo.add(new Chunk("DETALLE DE CARTAS DE ADHESIÓN", TIMESROMAN10));
		pTitulo.setSpacingAfter(10f);
		document.add(pTitulo);
		
		PdfPCell cell = null;
		float[] w = {10, 70, 20};		
		
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
		parrafo =  new Paragraph("No.", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		parrafo =  new Paragraph("Comprador", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		parrafo =  new Paragraph("Carta de Adhesión", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 2);
		t.addCell(cell);		
		int i=1;
		for (CartasAdhesionV cartaAux : ata.getLstCartasV()){
			parrafo =  new Paragraph(i+"", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 5);
			t.addCell(cell);
			parrafo =  new Paragraph(cartaAux.getComprador(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 5);
			t.addCell(cell);
			parrafo =  new Paragraph(cartaAux.getCarta(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 13);
			t.addCell(cell);
			i++;
		}
		document.add(t);
		
		addEmptyLine(1);
		parrafo = new Paragraph("Sin más por el momento reciba un cordial saludo.", TIMESROMAN12);
		document.add(parrafo);	
	}

	private void getCuerpo() throws DocumentException{
		parrafo= new Paragraph("Me refiero al "+ata.getNombrePrograma()+".", TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
		StringBuilder texto = new StringBuilder();
		texto.append("Al respecto, anexo al presente envío a Usted, para la atención correspondiente, original de las Cartas de Adhesión ")
			 .append("firmadas por las partes que en ellas intervienen de los participantes que acontinuación se indican.");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
		parrafo = new Paragraph("Sin más por el momento reciba un cordial saludo.", TIMESROMAN12);
		document.add(parrafo);	
	}

	private void getEmisor() throws DocumentException {
		parrafo = new Paragraph("A T E N T A M E N T E", TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);	
		parrafo = new Paragraph(ata.getEmisor().getPuesto().toUpperCase(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
		addEmptyLine(3);
		parrafo = new Paragraph(ata.getEmisor().getNombre(), TIMESROMAN12);
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
		
		parrafo = new Paragraph("C.c.p", TIMESROMAN08);
		celda =	createCell(parrafo, 0, 2, 1);
		piePagina.addCell(celda);
		StringBuilder cpps = new StringBuilder();
		for(Personal p: ata.getLstPersonal()){
			cpps.append(p.getNombre()).append(" ").append(p.getPaterno()).append(" ")
			.append(p.getMaterno()!=null && !p.getMaterno().isEmpty()?p.getMaterno():"")
			.append(".- ").append(p.getPuesto()).append(".- ").append("Presente\n");
		}
		parrafo = new Paragraph(cpps.toString(), TIMESROMAN08);
		celda =	createCell(parrafo, 0, 2, 1);
		piePagina.addCell(celda);
		
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
