package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import mx.gob.comer.sipc.pagos.action.ArchivosTesofeAction;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.vistas.domain.PagosV;

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

public class OficioEnvioCGCDGAF extends PdfPageEventHelper {
	
	
	// Configuracion de fuentes
	//private final Font ARIALBOLD10 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN10 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);
	@SuppressWarnings("unused")
	private final Font TIMESROMAN12NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN10NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, Color.BLACK);
	//private final Font ARIALNORMAL08 = FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL, Color.BLACK);
	//private final Font ARIALNORMAL08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08LIGTH= FontFactory.getFont(FontFactory.HELVETICA,  8, Font.NORMAL	, Color.LIGHT_GRAY);
	private final Font TIMESROMAN08	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN07	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 7,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMAN12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.NORMAL, Color.BLACK);
	private final Font TIMESROMANBOLD12	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11,Font.BOLD, Color.BLACK);
	private final Font TIMESROMANBOLD10	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 10,Font.BOLD, Color.BLACK);
	private final Font TIMESROMANBOLD9	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 9,Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN06	= FontFactory.getFont(FontFactory.TIMES_ROMAN, 6,Font.NORMAL, Color.BLACK);
	//private final Font ARIALBOLD12 = FontFactory.getFont(FontFactory.HELVETICA, 1, Font.NORMAL, Color.BLACK);
	

	private ArchivosTesofeAction ata;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	private PdfPTable piePagina;
	private StringBuilder texto;
	public OficioEnvioCGCDGAF(ArchivosTesofeAction ata) {
		this.ata = ata;
	}
	
	public void generarOficioEnvioCGCDGAF() throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 80);
		writer = PdfWriter.getInstance(document, new FileOutputStream(ata.getRutaSalida()));
		writer.setPageEvent(this);
		document.open();
		getEncabezado();
		addEmptyLine(0);
		getNoOficio();
		getLugarYFecha();
		addEmptyLine(1);
		getDestinatario();
		addEmptyLine(1);
		Image img1 = Image.getInstance(ata.getRutaMarcaAgua()); 
		img1.setAlignment(Image.MIDDLE | Image.UNDERLYING); 
		document.add(img1);
		getCuerpo();
		addEmptyLine(1);
		getEmisor();
		document.newPage();
		getAnexoDetallePagos();
		document.close();
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
			enunciado.add(new Chunk("Agencia de Servicios a la Comercialización y\n Desarrrollo de Mercados Agropecuarios\n", TIMESROMANBOLD12));
			enunciado.add(new Chunk("Coordinación General de Comercialización\n", TIMESROMANBOLD10));
			enunciado.add(new Chunk("Dirección General de Desarrollo de\nMercados e Infraestructura Comercial\n", TIMESROMANBOLD9));
			enunciado.add(new Chunk("Dirección de Pago de Apoyos a la Comercialización\n", TIMESROMANBOLD9));
			enunciado.add(new Chunk("\n"+ata.getLeyendaOficio(), TIMESROMAN10NORMAL));
/*
			if(ata.getLeyendaOficio().length()<=40){
				enunciado.add(new Chunk("\n\n"+ata.getLeyendaOficio(), TIMESROMAN10NORMAL));
			}else{
				enunciado.add(new Chunk("\n\n"+ata.getLeyendaOficio().substring(0, 41)+"\n"+ata.getLeyendaOficio().substring(41, ata.getLeyendaOficio().length()), TIMESROMAN10NORMAL));
			}
*/			
			cell =	createCell(null, 0, 2, 1, sagarpa);
			table1.addCell(cell);
			cell =	createCell(enunciado, 0, 3, 1, null);
			table1.addCell(cell);
			document.add(table1);
	}
	
	private void getNoOficio() throws DocumentException{
		parrafo = new Paragraph("Oficio No. "+ata.getClaveOficio()+ata.getNoOficio()+ata.getAnioOficio(), TIMESROMANBOLD12);
		document.add(parrafo);
	}
	private void getLugarYFecha() throws DocumentException {
		parrafo = new Paragraph("México, D.F. "+ata.getFechaActual(), TIMESROMAN12);
		parrafo.setAlignment(Rectangle.ALIGN_LEFT);
		document.add(parrafo);	
	}
	private void getDestinatario() throws DocumentException {
		
		texto = new StringBuilder()
		.append(ata.getDestinatario().getIniProfesion().toUpperCase()+" "+ata.getDestinatario().getNombre()).append("\n")
		.append(ata.getDestinatario().getPuesto()).append("\n")
		.append("PRESENTE");
		parrafo = new Paragraph(texto.toString(), TIMESROMANBOLD12);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
	
	}
	private void getCuerpo() throws DocumentException{
		parrafo = new Paragraph();
		parrafo.add(new Chunk(ata.getPrimerParrafo(), TIMESROMAN12));
		parrafo.add(new Chunk(ata.getNombrePrograma(), (ata.getVersion() == 0? TIMESROMAN12:TIMESROMANBOLD12)));
		parrafo.add(new Chunk(ata.getP11Texto(), TIMESROMAN12));
		parrafo.add(new Chunk(ata.getP12Texto(), TIMESROMANBOLD12));
		parrafo.add(new Chunk(ata.getP13Texto(), TIMESROMAN12));
		if(ata.getVersion() == 1){
			parrafo.add(new Chunk(ata.getP14Texto(), TIMESROMANBOLD12));
		}
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		parrafo.setLeading(1,1);
		document.add(parrafo);	
		addEmptyLineCuerpo(1);		
		parrafo = new Paragraph();
		parrafo.add(new Chunk(ata.getSegundoParrafo(), TIMESROMAN12));
		parrafo.add(new Chunk(ata.getNoDepositos()+"", TIMESROMANBOLD12));
		parrafo.add(new Chunk(ata.getP21Texto()+"", TIMESROMANBOLD12));
		parrafo.add(new Chunk(ata.getP22Texto(), TIMESROMAN12));
		parrafo.add(new Chunk(ata.getImporteLetra(), TIMESROMANBOLD12));
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLineCuerpo(1);		
		parrafo = new Paragraph();
		parrafo.add(new Chunk(ata.getTercerParrafo(), TIMESROMAN12));
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);	
		addEmptyLineCuerpo(1);
		
		parrafo = new Paragraph();
		parrafo.add(new Chunk(ata.getCuartoParrafo(), TIMESROMAN12));
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);
		addEmptyLineCuerpo(1);
		
		parrafo = new Paragraph();
		parrafo.add(new Chunk(ata.getQuintoParrafo(), TIMESROMAN12));
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);		
	}
    private void getEmisor() throws DocumentException {
		parrafo = new Paragraph("A T E N T A M E N T E", TIMESROMANBOLD10);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);	
		parrafo = new Paragraph(ata.getEmisor().getPuesto().toUpperCase(), TIMESROMANBOLD10);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
//		parrafo = new Paragraph("De conformidad con los artículos 8, fracción VIII, 21 y Noveno Transitorio del Reglamento Interior\n" +
//								"de la Agencia de Servicios a la Comercialización y Desarrollo de Mercados Agropecuarios y el oficio No.F00.1000/001/2015", TIMESROMAN06);
//		parrafo = new Paragraph("De conformidad con los artículos 5, 7 segundo párrafo, 8 fracción VIII, 12 fracción V, 27 y "
//				+ "Transitorios Primero, Tercero, Quinto, Sexto y Noveno\ndel Reglamento Interior de ASERCA, "
//				+ "publicado en el DOF el 28 de diciembre del 2014 y el oficio No.F00.1000/004/2015", TIMESROMAN06);
//		parrafo.setAlignment(Element.ALIGN_CENTER);
//		document.add(parrafo);
		
		addEmptyLine(2);
/*		
		if(ata.getIdPrograma() == 31){
			addEmptyLine(1);
		}else{
			addEmptyLine(2);
		}	
*/
		parrafo = new Paragraph((ata.getEmisor().getIniProfesion()!=null && !ata.getEmisor().getIniProfesion().isEmpty()?ata.getEmisor().getIniProfesion().toUpperCase()+" ":"")
				+ata.getEmisor().getNombre(), TIMESROMANBOLD10);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);
		parrafo = new Paragraph("Facultado para \"Autorizar el pago de los apoyos a los beneficiarios de los programas, esquemas y servicios de apoyos de los productos Agroalimentarios y de los proyectos\n"
				+ "de infraestructura básica comercial, previo cumplimiento a las disposiciones jurídica aplicables\", según oficio F00.1000/004/2015 de la Dirección en Jefe de ASERCA.", TIMESROMAN06);
		parrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafo);		
	}

	private void addEmptyLine(int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			parrafo = new Paragraph("\n");
			document.add(parrafo);
		}
	}

	private void addEmptyLineCuerpo(int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			parrafo = new Paragraph("\n",TIMESROMAN08);
			document.add(parrafo);
		}
	}

	public void piePagina() throws DocumentException { 	
		piePagina = null;	
		PdfPCell celda = null;
		piePagina= new PdfPTable(2);
		piePagina.setTotalWidth(document.right() - document.left()); 
		piePagina.setWidths(new int[]{8,92});
		
		parrafo = new Paragraph("C.c.e.p.", TIMESROMAN08);
		celda =	createCell(parrafo, 0, 2, 1);
		piePagina.addCell(celda);
		parrafo = new Paragraph();
		//ccpe1
		
		parrafo.add(new Chunk(ata.getCcep1().getIniProfesion()+" "+ata.getCcep1().getNombre()+" "+ata.getCcep1().getPaterno()+" "+
				(ata.getCcep1().getMaterno()!=null && !ata.getCcep1().getMaterno().isEmpty()?ata.getCcep1().getMaterno():"")+
				".- "+ata.getCcep1().getPuesto()+".- ", TIMESROMAN08));
		//parrafo.add(new Chunk("Luz Maria Corona Martinez"+".- "+"Mi puesto"+".- ", TIMESROMAN08));
		parrafo.add(new Chunk((ata.getCcep1().getCorreo()!=null && !ata.getCcep1().getCorreo().isEmpty())?ata.getCcep1().getCorreo()+"\n":"\n", TIMESROMAN07));

		//ccpe2
		parrafo.add(new Chunk(ata.getCcep2().getIniProfesion()+" "+ata.getCcep2().getNombre()+" "+ata.getCcep2().getPaterno()+" "+
				(ata.getCcep2().getMaterno()!=null && !ata.getCcep2().getMaterno().isEmpty()?ata.getCcep2().getMaterno():"")+
				".- "+ata.getCcep2().getPuesto()+".- ", TIMESROMAN08));
		parrafo.add(new Chunk((ata.getCcep2().getCorreo()!=null && !ata.getCcep2().getCorreo().isEmpty())?ata.getCcep2().getCorreo()+"\n":"\n", TIMESROMAN07));
/*		
		//ccpe3
		parrafo.add(new Chunk(ata.getCcep3().getIniProfesion()+" "+ata.getCcep3().getNombre()+" "+ata.getCcep3().getPaterno()+" "+
				(ata.getCcep3().getMaterno()!=null && !ata.getCcep3().getMaterno().isEmpty()?ata.getCcep3().getMaterno():"")+
				".- "+ata.getCcep3().getPuesto()+".- ", TIMESROMAN08));
		parrafo.add(new Chunk((ata.getCcep3().getCorreo()!=null && !ata.getCcep3().getCorreo().isEmpty())?ata.getCcep3().getCorreo()+"\n":"\n", TIMESROMAN07));
*/		
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
	
	private void getAnexoDetallePagos() throws DocumentException {
		Paragraph pTitulo = new Paragraph();
		double totalVolumen = 0;
		double totalImporte = 0;
		
		Paragraph pOficio = new Paragraph();
		pOficio.setAlignment(Element.ALIGN_RIGHT);
		pOficio.add(new Chunk("ANEXO AL OFICIO "+ata.getClaveOficio()+ata.getNoOficio()+ata.getAnioOficio(), TIMESROMAN10));
		pOficio.setSpacingAfter(10f);
		document.add(pOficio);		
		
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		pTitulo.add(new Chunk("DETALLE DE PAGOS", TIMESROMAN10));
		pTitulo.setSpacingAfter(10f);
		document.add(pTitulo);		
		
		PdfPCell cell = null;
		float[] w = {1};
		if(ata.getCriterioPago()== 3 ){
			float[] x = {10,25,  20, 15, 15, 15}; // %
			 w = x;
		}else{
			float [] y = {10,25, 25, 20, 20}; // %	
			w = y;
		}
		
		
		
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
		parrafo =  new Paragraph("No.", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		parrafo =  new Paragraph("COMPRADOR", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		parrafo =  new Paragraph("CARTA", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t.addCell(cell);
		if(ata.getCriterioPago()== 2 || ata.getCriterioPago()==3){
			parrafo =  new Paragraph("ETAPA", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 11);
			t.addCell(cell);
		}
		if(ata.getCriterioPago()== 1 || ata.getCriterioPago()==3){
			parrafo =  new Paragraph("VOLUMEN", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 11);
			t.addCell(cell);	
		}
		
		parrafo =  new Paragraph("IMPORTE", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 2);
		t.addCell(cell);
		int i=1;
		for (PagosV pago : ata.getLstPagosV()){
			
			if(ata.getCriterioPago()== 1 || ata.getCriterioPago()==3){
				totalVolumen += pago.getVolumen();	
			}			
			totalImporte += pago.getImporte();
			parrafo =  new Paragraph(i+"", TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 5);
			t.addCell(cell);
			parrafo =  new Paragraph(pago.getNombreComprador(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 5);
			t.addCell(cell);
			parrafo =  new Paragraph(pago.getNoCarta(), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 2, 5);
			t.addCell(cell);
			if(ata.getCriterioPago()== 2 || ata.getCriterioPago()==3){
				parrafo =  new Paragraph(pago.getEtapa(), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 5);
				t.addCell(cell);	
			}
			if(ata.getCriterioPago()== 1 || ata.getCriterioPago()==3){
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(pago.getVolumen()), TIMESROMAN08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 3, 5);
				t.addCell(cell);	
			}
			
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoCantidad(pago.getImporte()), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 13);
			t.addCell(cell);
			i++;
		}
		
		
		parrafo =  new Paragraph("", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		if(ata.getCriterioPago()==1){
			cell =createCell(parrafo, 2, 2, 1);
		}else if(ata.getCriterioPago()==2){
			cell =createCell(parrafo, 3, 2, 1);
		}else{
			cell =createCell(parrafo, 3, 2, 1);
		}
		
		t.addCell(cell);
		parrafo =  new Paragraph("TOTALES:", TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 3, 5);
		t.addCell(cell);
		

		if(ata.getCriterioPago()==1 || ata.getCriterioPago()==3){
			parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(totalVolumen), TIMESROMAN08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 3, 5);
			t.addCell(cell);	
		}
		
		
		parrafo =  new Paragraph("$ "+TextUtil.formateaNumeroComoCantidad(totalImporte), TIMESROMAN08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 3, 13);
		t.addCell(cell);
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
			piePagina();
			if(writer.getPageNumber()<=1){
				piePagina.writeSelectedRows(0, -1, document.left(), document.bottom()-1, cb);
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
