package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import mx.gob.comer.sipc.action.inscripcion.InscripcionAction;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class AcuseCartaAdhesion extends PdfPageEventHelper {
	
	// Configuracion de fuentes
	
	private final Font HELVETICA09	= FontFactory.getFont(FontFactory.HELVETICA, 9,Font.NORMAL, Color.BLACK);
	private final Font HELVETICA10BOLD= FontFactory.getFont(FontFactory.HELVETICA, 10,Font.BOLD, Color.BLACK);
	private final Font HELVETICA12	= FontFactory.getFont(FontFactory.HELVETICA, 12,Font.NORMAL, Color.BLACK);
	
	
	
	private InscripcionAction apa;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	public AcuseCartaAdhesion(InscripcionAction apa) {
		this.apa = apa;
	}
	
	public void generarAcuseCartaAdhesion() throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 100);
		writer = PdfWriter.getInstance(document, new FileOutputStream(apa.getRutaSolicitud()+apa.getNombreOficio()));
		writer.setPageEvent(this);
		document.open();
		getEncabezado();
		addEmptyLine(1);
		getTituloOficio();
		addEmptyLine(1);	
		addMarcaAgua();
		getCuerpo();
		document.close();
	}
	
	private void getTituloOficio() throws DocumentException {
		parrafo = new Paragraph("GENERACIÓN FOLIO CARTA ADHESIÓN ", HELVETICA12);
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
		addEmptyLine(1);		
		String fechaAcuseFolioCA = new SimpleDateFormat("dd/MM/yyyy").format(apa.getFechaAcuseFCA()).toString();
		parrafo = new Paragraph("FECHA: "+fechaAcuseFolioCA, HELVETICA12);
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_RIGHT);
		document.add(parrafo);
	}

	public  void getEncabezado() throws MalformedURLException, IOException, DocumentException {
		PdfPCell cell = null;
		PdfPTable table1 = null;	
		
		/**TABLA DE LOGOS**/	
		Image sagarpa = Image.getInstance(apa.getRutaImagen());
		Image aserca = Image.getInstance(apa.getRutaAserca());
		sagarpa.scalePercent(50, 50);
		aserca.scalePercent(50,50);
		table1= new PdfPTable(2);
		table1.setWidthPercentage(100);
		cell =	createCell(null, 0, 2, 1, sagarpa);
		table1.addCell(cell);
		cell =	createCell(null, 0, 3, 1, aserca);
		table1.addCell(cell);
		document.add(table1);
	}
	
	private void addMarcaAgua() throws Exception{
		Image img1 = Image.getInstance(apa.getRutaMarcaAgua()); 
		img1.setAlignment(Image.MIDDLE | Image.UNDERLYING); 
		document.add(img1);
	}
	private void getCuerpo()throws DocumentException{
		float[] w = {30,70}; // 25% ,75%
		PdfPCell cell = null;
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
				
		parrafo =  new Paragraph("FOLIO CARTA ADHESIÓN:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph(apa.getFolioCartaAdhesion(), HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("\n", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);	
		
		parrafo =  new Paragraph("PROGRAMA:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph(apa.getDescripcionLarga(), HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 4, 1);
		t.addCell(cell);
		
		
		parrafo =  new Paragraph("\n", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);	
		
		parrafo =  new Paragraph("PARTICIPANTE:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph(apa.getNombreComprador(), HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("\n", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);	
		
		
		parrafo =  new Paragraph("CICLO:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph(apa.getNombreCiclo().toUpperCase(), HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("\n", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);	
		
				
		parrafo =  new Paragraph("CULTIVO:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph(apa.getNombreCultivo(), HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("\n", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);	
		
		parrafo =  new Paragraph("ACREDITACIÓN JURÍDICA:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph("POSITIVA", HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("\n", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);	
		
		
		parrafo =  new Paragraph("ACREDITACIÓN TÉCNICA:", HELVETICA09);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);		
		parrafo =  new Paragraph("POSITIVA", HELVETICA10BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);	
		addEmptyLine(2);
		
		document.add(t);	
	}
			
			
	
	private void addEmptyLine(int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			parrafo = new Paragraph("\n");
			document.add(parrafo);
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
			   cell.setBorderWidthTop(0f);
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

}
