package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import mx.gob.comer.sipc.dao.ReportesDAO;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.pagos.action.AcusesPagosAction;
import mx.gob.comer.sipc.vistas.domain.OficioCompradorProgramaV;
import mx.gob.comer.sipc.vistas.domain.RepresentanteCompradorV;

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

public class AcusePagosRechazados extends PdfPageEventHelper {
	
	// Configuracion de fuentes
	private final Font TIMESROMAN08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);

	private AcusesPagosAction apa;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	private PdfPTable piePagina;
	private StringBuilder texto;
	private ReportesDAO rDAO = new ReportesDAO();
	public AcusePagosRechazados(AcusesPagosAction apa) {
		this.apa = apa;
	}
	
	public void generarAcusePagosRechazados() throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 100);
		writer = PdfWriter.getInstance(document, new FileOutputStream(apa.getRutaSalida()+apa.getNombreOficio()));
		writer.setPageEvent(this);
		document.open();
		
		String [] idPagos = apa.getSelectedPagos().split(",");
		String [] numOficio = apa.getSelectedOficios().split(",");
		int j=0;
		List<RepresentanteCompradorV> lstRepresentateComprador = null;
		for(int i=0; i<idPagos.length; i++){
			
			if(Integer.parseInt(idPagos[i])!=-1){
				/*Consigue los datos del pago*/
				OficioCompradorProgramaV ocpv=rDAO.consultaOficioCompradorProgramaVSession(Integer.parseInt(idPagos[i])).get(0);
				document.newPage();
				getEncabezado();
				addEmptyLine(2);
				getLugarYFecha(numOficio[j]);
				addEmptyLine(1);
				//Recupera los representantes legal(es) apoderados
				lstRepresentateComprador = rDAO.consultaRepresentanteCompradorVSession(0, ocpv.getIdComprador(), 1);
				
				//getDestinatario(lstRepresentateComprador +"\nREPRESENTANTE LEGAL DE "+ocpv.getComprador());
				getDestinatario(lstRepresentateComprador, "REPRESENTANTE LEGAL DE "+ocpv.getComprador());
				addEmptyLine(1);
				addMarcaAgua();
				getCuerpo(ocpv);
				addEmptyLine(1);
				getEmisor();
			}
			j++;
			if(Integer.parseInt(idPagos[i])==-1){
				j=0;
			}
			
		}
	
		document.close();
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
		enunciado=new Paragraph("ASERCA\nCoordinación General de Comercialización\nDirección General de Desarrollo de Mercados\nDirección de Pago de Apoyos a la Comercialización", TIMESROMAN12);
		cell =	createCell(null, 0, 2, 1, sagarpa);
		table1.addCell(cell);
		cell =	createCell(enunciado, 0, 3, 1, null);
		table1.addCell(cell);
		enunciado = new Paragraph(apa.getLeyendaOficio().substring(0, 41)+"\n"+apa.getLeyendaOficio().substring(41, apa.getLeyendaOficio().length()), TIMESROMAN10NORMAL);
		cell =	createCell(enunciado, 2, 3, 1, null);
		table1.addCell(cell);
		document.add(table1);
	}
	
	private void getLugarYFecha(String noOficio) throws DocumentException {
		parrafo = new Paragraph("N° de Oficio "+noOficio+"\nMéxico, D.F. "+apa.getFechaActual(), TIMESROMAN12);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	}
	
	private void getDestinatario(List<RepresentanteCompradorV> lstRepresentateComprador, String comprador) throws DocumentException {
		texto = new StringBuilder();
		
		for(RepresentanteCompradorV rc: lstRepresentateComprador){
			texto.append(rc.getNombreRepresentante().toUpperCase())
			.append("\n");
		}
		
		texto.append(comprador).append("\n")
		.append("P R E S E N T E");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	
	}
	
	private void addMarcaAgua() throws Exception{
		Image img1 = Image.getInstance(apa.getRutaMarcaAgua()); 
		img1.setAlignment(Image.MIDDLE | Image.UNDERLYING); 
		document.add(img1);
	}
	
	private void getCuerpo(OficioCompradorProgramaV ocpv) throws DocumentException{
		
		StringBuilder texto = new StringBuilder();
		texto.append("Con referencia a la operación del esquema "+ocpv.getProgramaLargo());
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);

		texto = new StringBuilder();
		texto.append("Al respecto, le informo que la cuenta bancaria que presentó para el pago de sus apoyos correspondiente a la carta de adhesión "+ocpv.getCartaAdhesion())
			 .append(", no se efectuó ya que la TESOFE por medio del sistema SIAFF, reporta que el motivo del rechazo es "+ocpv.getDesRechazo()+".");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);

		texto = new StringBuilder();
		texto.append("Por lo anterior, le solicito por este conducto presente otra cuenta bancaria anexando copia simple del estado de cuenta.");
		parrafo = new Paragraph(texto.toString(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLine(1);
		
		parrafo = new Paragraph("Le saluda.", TIMESROMAN12);
		document.add(parrafo);	
	}
	
	private void getEmisor() throws DocumentException {
		parrafo = new Paragraph("A T E N T A M E N T E", TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);	
		parrafo = new Paragraph(apa.getEmisor().getPuesto().toUpperCase(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
		addEmptyLine(3);
		parrafo = new Paragraph(apa.getEmisor().getNombre(), TIMESROMAN12);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
	}
	
	public void piePagina() throws DocumentException { 	
		piePagina = null;	
		PdfPCell celda = null;
		piePagina= new PdfPTable(2);
		piePagina.setTotalWidth(document.right() - document.left()); 
		piePagina.setWidths(new int[]{8,92});

/*		
		parrafo = new Paragraph("C.c.p", TIMESROMAN08);
		celda =	createCell(parrafo, 0, 2, 1);
		piePagina.addCell(celda);
		StringBuilder cpps = new StringBuilder();
		for(Personal p: apa.getLstPersonalCpp()){
			cpps.append(p.getNombre()).append(" ").append(p.getPaterno()).append(" ")
			.append(p.getMaterno()!=null && !p.getMaterno().isEmpty()?p.getMaterno():"")
			.append(".- ").append(p.getPuesto()).append(p.getCorreo()!=null && !p.getCorreo().isEmpty()?(".-"+p.getCorreo()):"");
		}
		parrafo = new Paragraph(cpps.toString(), TIMESROMAN08);
		celda =	createCell(parrafo, 0, 2, 1);
		piePagina.addCell(celda);
*/		
		
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
			piePagina();
			piePagina.writeSelectedRows(0, -1, document.left(), document.bottom()-5, cb);
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error al generar el pie de pagina, debido a: "+e.getCause());
		}
		
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
