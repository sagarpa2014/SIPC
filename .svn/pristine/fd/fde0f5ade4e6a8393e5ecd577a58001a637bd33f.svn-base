package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


public class HeaderFooterExample extends PdfPageEventHelper {
	protected Phrase header; 
	protected PdfPTable footer;
	private  Paragraph parrafo;
	private  Font ARIALNORMAL08	= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL, Color.BLACK);
	Font FONT_PIE_PAGINA		= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private PdfPTable pdfPTable;
	
	public HeaderFooterExample(Document document) throws DocumentException { 	
		pdfPTable = null;	
		PdfPCell celda = null;
		pdfPTable= new PdfPTable(2);
		pdfPTable.setTotalWidth(document.right() - document.left()); 
		//pdfPTable.setWidthPercentage(100);
		pdfPTable.setWidths(new int[]{8,92});
		parrafo = new Paragraph("C.c.p", ARIALNORMAL08);
		celda =	createCell(parrafo, 0, 2, 1);
		pdfPTable.addCell(celda);
		StringBuilder cpps = new StringBuilder();
		for(int i=1; i<=3; i++){
			cpps.append("ok").append("Presente\n");
		}
		parrafo = new Paragraph(cpps.toString(), ARIALNORMAL08);
		celda =	createCell(parrafo, 0, 2, 1);
		pdfPTable.addCell(celda);
		
		StringBuilder piePagina = new StringBuilder();
		piePagina.append("Av. Municipio Libre 377, Piso 12 ala B, Col. Santa Cruz Atoyac, Del. Benito Juárez, México, D.F. C.P. 03310\n");
		piePagina.append("telefono +52(55)38717300 ext. 50101 y 50064, www.aserca.gob.mx");
		parrafo = new Paragraph(piePagina.toString(), FONT_PIE_PAGINA);
		parrafo.setLeading(1,1);
		//parrafo.setAlignment(Element.ALIGN_CENTER);
		celda =	createCell(parrafo, 2, 1, 1);
		pdfPTable.addCell(celda);
		//document.add(pdfPTable);
		
		
		
		
	} 
	
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent(); 
		/*if (document.getPageNumber() > 1) {
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header, 
					(document.right() - document.left()) / 2 + 
						document.leftMargin(), document.top() + 10, 0);
		}*/
		System.out.println("Rigth "+ document.right()+"botton "+document.bottom());
		pdfPTable.writeSelectedRows(0, -1, document.left(), document.bottom()-10, cb);
		
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
