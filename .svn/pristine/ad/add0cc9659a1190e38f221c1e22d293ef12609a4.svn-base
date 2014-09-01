package mx.gob.comer.sipc.oficios.pdf;

import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.dao.ReportesDAO;
import mx.gob.comer.sipc.pagos.action.AcusesPagosAction;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.vistas.domain.OficioCompradorProgramaV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;

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

@SuppressWarnings("unused")
public class AcusePagosAplicados extends PdfPageEventHelper {
	
	// Configuracion de fuentes	
	private final Font HELVETICA08	= FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL, Color.BLACK);
	private final Font HELVETICA12	= FontFactory.getFont(FontFactory.HELVETICA, 12,Font.NORMAL, Color.BLACK);
	private final Font HELVETICA08BOLD= FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD, Color.BLACK);
	private final Font TIMESROMAN08BOLD= FontFactory.getFont(FontFactory.HELVETICA, 7,Font.BOLD, Color.BLACK);
	
	private AcusesPagosAction apa;
	private PdfWriter writer;
	private Document document;
	private Paragraph parrafo;
	private ReportesDAO rDAO = new ReportesDAO();
	private PagosDAO pDAO = new PagosDAO();
	public AcusePagosAplicados(AcusesPagosAction apa) {
		this.apa = apa;
	}
	
	
	public void generarAcusePagosAplicados() throws Exception{	
		document = new Document(PageSize.LETTER, 50, 50, 50, 100);
		writer = PdfWriter.getInstance(document, new FileOutputStream(apa.getRutaSalida()+apa.getNombreOficio()));
		writer.setPageEvent(this);
		document.open();
		
		String [] idPagos = apa.getSelectedPagos().split(",");
		
		for(int i=0; i<idPagos.length; i++){
			System.out.println("idPago "+idPagos[i]);
			/*Consigue los datos del pago*/
			if(Integer.parseInt(idPagos[i]) != -1){
				OficioCompradorProgramaV ocpv=rDAO.consultaOficioCompradorProgramaVSession(Integer.parseInt(idPagos[i])).get(0);
				List<PagosDetalleV> pagoDet=pDAO.consultaPagosDetalleVSession(0,Integer.parseInt(idPagos[i]));
				document.newPage();
				getEncabezado();
				addEmptyLine(1);
				getTituloOficio();
				addEmptyLine(1);
				getCuerpo(ocpv, pagoDet);
				addEmptyLine(2);
				
				
			}
			
		}
	
		document.close();
	}
	
	private void getTituloOficio() throws DocumentException {
		parrafo = new Paragraph("DISPERSIÓN DE RECURSOS PARA EL PAGO DE APOYOS "+"\nA TRAVÉS DE CUENTAS DE LIQUIDACIÓN CERTIFICADAS (CLC) ", HELVETICA12);
		parrafo.setLeading(1,1);
		parrafo.setAlignment(Element.ALIGN_CENTER);
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
	
	private void getCuerpo(OficioCompradorProgramaV ocpv, List<PagosDetalleV> pagoDet) throws DocumentException{
		float[] w = {25,25,25,25}; // 25%
		PdfPCell cell = null;
		PdfPTable t = new PdfPTable(w);
		t.setWidthPercentage(100);
		parrafo =  new Paragraph("FECHA DE CONSULTA:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		Date fechaActual = new Date();
		String fechaActualS = TextUtil.consigueNombreDia(new SimpleDateFormat("EEEE").format(fechaActual).toString()).toLowerCase()+", "
							 +new SimpleDateFormat("dd").format(fechaActual).toString()+" de "
							 +TextUtil.consigueMes(Integer.parseInt(new SimpleDateFormat("MM").format(fechaActual).toString())).toLowerCase()+" de "
							 +new SimpleDateFormat("yyyy").format(fechaActual).toString();
		parrafo =  new Paragraph(fechaActualS, HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("\n\n", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 4, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("FECHA DE PAGO:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(new java.text.SimpleDateFormat("dd/MM/yyyy").format(ocpv.getFechaPago()), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("FECHA DE PRESENTACIÓN:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(new java.text.SimpleDateFormat("dd/MM/yyyy").format(ocpv.getFechaPresentacion()), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("\n\n", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 4, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("ESTADO:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("EFECTUADO", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("OPERACIÓN:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("PAGO DE APOYOS", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("FOLIO OPERACIÓN:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(ocpv.getClaveRastreo(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("RAMO:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(apa.getRamo(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("UR:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(apa.getUnidadResponsable(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("FOLIO_CLC:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(ocpv.getFolioClc().toString(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);

		parrafo =  new Paragraph("BANCO:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(ocpv.getBanco(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		
		parrafo =  new Paragraph("CUENTA ABONO:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(ocpv.getClabe(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);

		parrafo =  new Paragraph("IMPORTE:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("$"+TextUtil.formateaNumeroComoCantidad(ocpv.getImporte()), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("\n\n\n", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 4, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("DATOS DEL BENEFICIARIO", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 4, 1, 9);
		t.addCell(cell);	
		parrafo =  new Paragraph("\n", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 4, 2, 10);
		t.addCell(cell);
		parrafo =  new Paragraph("RFC:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(ocpv.getRfc().toUpperCase(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph("NOMBRE:", HELVETICA08);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 2, 1);
		t.addCell(cell);
		parrafo =  new Paragraph(ocpv.getComprador().toUpperCase(), HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 3, 2, 1);
		t.addCell(cell);
		
		
		// Imprime el detalle del pago por estado
		parrafo =  new Paragraph("\n", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 4, 1, 1);
		t.addCell(cell);

		float[] y = {1};
		if(ocpv.getCriterioPago()==1 || ocpv.getCriterioPago()==2 ){
			float[] x = {20, 20, 20, 20, 20}; // %
			 y = x;
		}else{
			float [] x = {15, 15, 20, 10, 20, 20}; // %	
			y =x;
		}
			
		PdfPTable t1 = new PdfPTable(y);
		parrafo =  new Paragraph("ESTADO", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t1.addCell(cell);
		
		parrafo =  new Paragraph("CULTIVO", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t1.addCell(cell);
		
		if(pagoDet.get(0).getVariedad() != null){
		parrafo =  new Paragraph("VARIEDAD", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 11);
		t1.addCell(cell);
		}
		
		if(ocpv.getCriterioPago()==2 || ocpv.getCriterioPago()==3 ){
			parrafo =  new Paragraph("ETAPA", HELVETICA08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 11);
			t1.addCell(cell);	
		}
		if(ocpv.getCriterioPago()==1 || ocpv.getCriterioPago()==3 ){
			parrafo =  new Paragraph("VOLUMEN(Ton)", HELVETICA08BOLD);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 11);
			t1.addCell(cell);	
		}
		
		parrafo =  new Paragraph("IMPORTE", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 2);
		t1.addCell(cell);

		for(int i=0; i<pagoDet.size(); i++){
			parrafo =  new Paragraph(pagoDet.get(i).getEstado().toUpperCase(), HELVETICA08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 5);
			t1.addCell(cell);
			
			parrafo =  new Paragraph(pagoDet.get(i).getCultivo().toUpperCase(), HELVETICA08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 5);
			t1.addCell(cell);
			
			if(pagoDet.get(i).getVariedad() != null){
			parrafo =  new Paragraph(pagoDet.get(i).getVariedad().toUpperCase(), HELVETICA08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 5);
			t1.addCell(cell);
			}
			
			if(ocpv.getCriterioPago()==2 || ocpv.getCriterioPago()==3 ){
				parrafo =  new Paragraph(ocpv.getEtapa(), HELVETICA08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 5);
				t1.addCell(cell);
			}
			if(ocpv.getCriterioPago()==1 || ocpv.getCriterioPago()==3 ){
				parrafo =  new Paragraph(TextUtil.formateaNumeroComoVolumen(pagoDet.get(i).getVolumen()), HELVETICA08);
				cell = new PdfPCell(parrafo);
				cell =createCell(parrafo, 0, 1, 5);
				t1.addCell(cell);	
			}
			
			parrafo =  new Paragraph("$"+TextUtil.formateaNumeroComoCantidad(pagoDet.get(i).getImporte()), HELVETICA08);
			cell = new PdfPCell(parrafo);
			cell =createCell(parrafo, 0, 1, 13);
			t1.addCell(cell);						
		}
		/*parrafo =  new Paragraph("", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 2, 1, 13);
		t1.addCell(cell);
		parrafo =  new Paragraph("", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 13);
		t1.addCell(cell);
		parrafo =  new Paragraph("", HELVETICA08BOLD);
		cell = new PdfPCell(parrafo);
		cell =createCell(parrafo, 0, 1, 13);
		t1.addCell(cell);*/

		document.add(t);
		document.add(t1);
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
