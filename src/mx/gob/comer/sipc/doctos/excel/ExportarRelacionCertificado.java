package mx.gob.comer.sipc.doctos.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.gob.comer.sipc.action.relaciones.ExportarRelacionAction;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.dao.UtileriasDAO;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionCertificadosV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionV;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ExportarRelacionCertificado {

	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;
	private ExportarRelacionAction era;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private Row row;
	private int countRow;
	private int countColumn;
	private RelacionesDAO rDAO;
	private CatalogosDAO cDAO;
	private UtileriasDAO uDAO;
	private Long idPerRel;
	private int ultimaFilaDetalle;
	private int primeraFilaDetalle;
	private Map<Integer,String> configTotales;	
	private List<GruposCamposRelacionV> lstGruposCamposRelacionV;
	private List<GruposCamposRelacionCertificadosV> lstContenidoEncabezadoCertificadosV;
	private List<GruposCamposRelacionCertificadosV> lstCamposRelacionCertificadosV;
	private CellStyle csCamposEncabezado;
	private CellStyle csCamposEncDetalle;
	private CellStyle csCamposDetalleFecha;
	private CellStyle csCamposDetalleImporte;
	private CellStyle csCamposDetalleVolumen;
	private CellStyle csCamposDetalle;
	private CellStyle csNombreRelacion;
	private CellStyle csLeyenda;
	private CellStyle csAuditor;
	private CellStyle csPrograma;
	private Font font9Bold;
	private Font font8Bold;
	private Cell cell;
	private String claveBodega;
	private Integer razonSocialAlmacen;
	private int numeroPagina;
	private String leyendaExpRElacion;
	
	public ExportarRelacionCertificado(ExportarRelacionAction era, Session session){
		this.era = era;
		this.session = session;
		rDAO = new RelacionesDAO(session);
		cDAO = new CatalogosDAO(session);
		uDAO = new UtileriasDAO(session);
	}
	
	public void generarArchivoExcel() throws FileNotFoundException, IOException, Exception{
			countRow = 0;
			wb = new HSSFWorkbook();	
			setCellStyles(wb);
			leyendaExpRElacion = uDAO.getParametros("LEYENDA_EXP_RELACIONES");
			if(era.getOpcion() == 1 ){
				countRow = 0;
				sheet = wb.createSheet(era.getClaveBodega());	
				setMargenSheet();
				claveBodega = era.getClaveBodega();
				razonSocialAlmacen = era.getRazonSocialAlmacen();  
				crearEncabezado(claveBodega, razonSocialAlmacen);
				recuperarDatosDetalle(claveBodega, razonSocialAlmacen);
				countRow++;
				crearCabeceraCampoDetalle();
				crearDetalle( 0,  lstCamposRelacionCertificadosV.size());
				crearTotales();
				crearHeader();
			}else {
				int j=1;
				String tmpClaveBodega ="";
				List<GruposCamposRelacionCertificadosV> listGruposCamposRelacionCertificadosV = rDAO.consultaRelacionCDByBodegaAlmacen(0,0,era.getFolioCartaAdhesion());
				for(GruposCamposRelacionCertificadosV l: listGruposCamposRelacionCertificadosV){
					if(!tmpClaveBodega.equals(l.getClaveBodega()) ){
						j=1;
					}
					countRow = 0;
					sheet = wb.createSheet(l.getClaveBodega()+"-ALM"+j);
					sheet.protectSheet("sipc2013mal");	
					setMargenSheet();
					claveBodega = l.getClaveBodega();
					razonSocialAlmacen = l.getRazonSocialAlmacen();  
					crearEncabezado(claveBodega, razonSocialAlmacen);
					recuperarDatosDetalle(claveBodega,razonSocialAlmacen);
					countRow++;
					crearCabeceraCampoDetalle();
					crearDetalle(0, lstCamposRelacionCertificadosV.size());
					crearTotales();
					mostrarAuditor();
					crearHeader();
					tmpClaveBodega = l.getClaveBodega();
					j++;
					lstContenidoEncabezadoCertificadosV = null;
				}
			}// end relacion = 4				
								
		    FileOutputStream out = new FileOutputStream(new File(era.getRutaCartaAdhesion()+era.getNombreRelacion()));
		    wb.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");		
	}
	
	private void crearEncabezado(String claveBodega, int razonSocialAlmacen) throws JDBCException, Exception {	
		row = sheet.createRow(countRow);	
		if(lstContenidoEncabezadoCertificadosV != null ){
			countRow++;
			numeroPagina++; 
		}else{	
			countRow = 8;	
			numeroPagina++;
			if(era.getOpcion()== 1){
				lstContenidoEncabezadoCertificadosV = rDAO.consultaGruposCamposCertificadosV(0,  era.getIdPerRel(), era.getIdComprador(), null,  claveBodega, era.getCultivoRelacion(), "", null, true, era.getIdPgrCiclo(), razonSocialAlmacen);		
			}else{
				lstContenidoEncabezadoCertificadosV = rDAO.consultaGruposCamposCertificadosV(era.getIdRelacion(),  -1, -1, era.getFolioCartaAdhesion(),  claveBodega, -1, "", null, true,0,razonSocialAlmacen);
			}
		}
						
		if(lstContenidoEncabezadoCertificadosV.size()>0){					
			GruposCamposRelacionCertificadosV gcrcv = lstContenidoEncabezadoCertificadosV.get(0);
			idPerRel = gcrcv.getIdPerRel().longValue();
			//Recupera los encabezados de la configuracion de la relacion
			List<GruposCamposRelacionV> lstGruposCamposRelacionV = rDAO.consultaGruposCampostV(0, 0, idPerRel, 0, 0, "ENC");
			countColumn = 0;
			Cell cell = null;
			for(GruposCamposRelacionV l:lstGruposCamposRelacionV){
				if(l.getIdCampo()==1){//"NOMBRE DEL PARTICIPANTE"						
					 List<Comprador> lstComprador = cDAO.consultaComprador(gcrcv.getIdComprador());
					 if(lstComprador.size()>0){
						 Comprador c = lstComprador.get(0);
						 String nombreComprador = c.getNombre()+(c.getApellidoPaterno()!=null?c.getApellidoPaterno():"")+(c.getApellidoPaterno()!=null?c.getApellidoPaterno():"");
						 row = sheet.createRow(++countRow);
						 cell = row.createCell(countColumn);
						 cell.setCellValue(l.getCampo()+": "+nombreComprador);
						 cell.setCellStyle(csCamposEncabezado);	
						 sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7 ));
					 }
				}else if(l.getIdCampo()==2){//"CLAVE DE LA CARTA DE ADHESIÓN"
					row = sheet.createRow(++countRow);
					cell = row.createCell(countColumn);
					cell.setCellValue(l.getCampo()+": "+era.getFolioCartaAdhesion());
					cell.setCellStyle(csCamposEncabezado);
					sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7 ));
				}else if(l.getIdCampo()==4){//"CLAVE DE LA BODEGA"
					row = sheet.createRow(++countRow);
					cell = row.createCell(countColumn);
					cell.setCellValue(l.getCampo()+": "+claveBodega);
					cell.setCellStyle(csCamposEncabezado);
					sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7 ));
				}else if(l.getIdCampo()==52){//"DOMICILIO DEL CENTRO DE ACOPIO"
					//Recupera del catalogo de Bodegas el domicilio del centro de acopio
					Bodegas bodega = cDAO.consultaBodegas(claveBodega).get(0);
					row = sheet.createRow(++countRow);
					cell = row.createCell(countColumn);
					cell.setCellValue(l.getCampo()+": "+bodega.getCalle());
					cell.setCellStyle(csCamposEncabezado);
					sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7 ));
				}else if(l.getIdCampo()==53){//"RAZÓN SOCIAL DEL ALMACÉN GENERAL DE DEPÓSITO"
					String nombreAlmacen  = cDAO.consultaAlmacenadora(razonSocialAlmacen).get(0).getNombre();
					row = sheet.createRow(++countRow);
					cell = row.createCell(countColumn);
					cell.setCellValue(l.getCampo()+": "+nombreAlmacen);
					cell.setCellStyle(csCamposEncabezado);
					sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7 ));
				}						
			}
		}//end relacion = 4
		
		
	
	}
	
	private void crearCabeceraCampoDetalle() throws ParseException{
		if(lstGruposCamposRelacionV == null){
			lstGruposCamposRelacionV = rDAO.consultaGruposCampostV(0, 0, era.getIdPerRel(), 0, 0, "DET");
		}
		
		row = sheet.createRow(++countRow);
		countColumn=0;	
		configTotales = new HashMap<Integer, String>();		
		Cell cell = null;
		int i = 0;
		for (GruposCamposRelacionV l : lstGruposCamposRelacionV) {
			cell = row.createCell(countColumn++);
			cell.setCellValue(l.getCampo());
			cell.setCellStyle(csCamposEncDetalle);
			sheet.autoSizeColumn(i);
			if (l.getTipoDato().equals("importe")
					|| l.getTipoDato().equals("numero")
					|| l.getTipoDato().equals("volumen")){
				configTotales.put(countColumn - 1, l.getTipoDato());
			}
			i++;
		}
		
	}

	private void crearDetalle(int comienzo, int fin ) throws Exception {		
		String tmpCertificado = "";		
		Cell cell = null;
		int numeroRegistroPorPagina = 0;
		try{
			for(int i = comienzo; i < fin; i++){
				if(i==0){
					primeraFilaDetalle = countRow+2;
				}
				if(!tmpCertificado.equals(lstCamposRelacionCertificadosV.get(i).getFolio())){
					row = sheet.createRow(++countRow);
					if(numeroPagina == 1){
						numeroRegistroPorPagina = 49;  
					}else{
						numeroRegistroPorPagina = 53; 
					}
					if((numeroRegistroPorPagina * numeroPagina) == countRow + (era.getOpcion()==1?0:6)){											
						sheet.setRowBreak(countRow+(era.getOpcion()==1?0:6));
						if(era.getOpcion() == 2){
							mostrarAuditor();
						}				

						row = sheet.createRow(++countRow);
						crearEncabezado(claveBodega, razonSocialAlmacen);   
						row = sheet.createRow(++countRow);
						cell = row.createCell(countColumn);
						cell.setCellValue("");
					    row = sheet.createRow(++countRow);
					    crearCabeceraCampoDetalle();
					    row = sheet.createRow(++countRow);
					}				
					
					countColumn = 0;
				}				
				cell = row.createCell(countColumn);
				if(lstCamposRelacionCertificadosV.get(i).getTipoDato().equals("fecha") ){
					cell.setCellValue(Utilerias.convertirStringToDate(lstCamposRelacionCertificadosV.get(i).getDescripcion()));
					cell.setCellStyle(csCamposDetalleFecha);
				}else if(lstCamposRelacionCertificadosV.get(i).getTipoDato().equals("importe")){
					cell.setCellValue(Double.parseDouble(lstCamposRelacionCertificadosV.get(i).getDescripcion()));
					cell.setCellStyle(csCamposDetalleImporte);
				}else if(lstCamposRelacionCertificadosV.get(i).getTipoDato().equals("volumen")){
					cell.setCellValue(Double.parseDouble(lstCamposRelacionCertificadosV.get(i).getDescripcion()));
					cell.setCellStyle(csCamposDetalleVolumen);
				}else{
					cell.setCellValue(lstCamposRelacionCertificadosV.get(i).getDescripcion());					
					cell.setCellStyle(csCamposDetalle);
				}
				countColumn++;
				 
				tmpCertificado = lstCamposRelacionCertificadosV.get(i).getFolio();		
				if(i==lstCamposRelacionCertificadosV.size()-1){
					ultimaFilaDetalle = countRow+1;
				}
				
				
				
			}		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	private void crearTotales() {
		Set<Integer> configTotalesIt = configTotales.keySet();
		Iterator<Integer> it =  configTotalesIt.iterator();
		String letraDeColumna ="";
		row = sheet.createRow(++countRow);		
		while(it.hasNext()){
			Integer columna = it.next();
			String tipoDato = configTotales.get(columna);
			letraDeColumna= CellReference.convertNumToColString(columna);
			String strFormula= "SUM("+letraDeColumna+primeraFilaDetalle+":"+letraDeColumna+ultimaFilaDetalle+")";
			 Cell cell = row.createCell(columna);
		     CellStyle cellStyle = wb.createCellStyle();
			if(tipoDato.equals("numero")){
				
			}else if(tipoDato.equals("volumen")){				
				DataFormat format = wb.createDataFormat();
				cellStyle.setDataFormat(format.getFormat("###,###,###,##0.000"));
				cell.setCellStyle(cellStyle);
			}else if(tipoDato.equals("importe")){
				 DataFormat format = wb.createDataFormat();
				 cellStyle.setDataFormat(format.getFormat("###,###,###,##0.00"));
			}
			cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			cell.setCellStyle(cellStyle);
			cell.setCellFormula(strFormula);
			cellStyle.setFont(font9Bold);
			
		}
	}
	
	private void mostrarAuditor() throws ParseException, IOException {
		countRow +=3;
		
		if(era.getOpcion() == 2){
			row = sheet.createRow(++countRow);
			cell = row.createCell(1);
			cell.setCellValue(era.getAuditor().getNombre());
			cell.setCellStyle(csAuditor);
			addMergedRegionCeldas(countRow+1,1, 2, true, false, false, false);
			row = sheet.createRow(++countRow);
			cell = row.createCell(1);
			cell.setCellValue("NOMBRE Y FIRMA DEL AUDITOR EXTERNO");
			cell.setCellStyle(csAuditor);
			addMergedRegionCeldas(countRow+1,1, 2, false, false, false, false);
			row = sheet.createRow(++countRow);
			cell = row.createCell(1);
			cell.setCellValue("N° DE REGISTRO ANTE LA SHCP");
			cell.setCellStyle(csAuditor);
			addMergedRegionCeldas(countRow+1,1, 2, false, false, false, false);
			//countRow +=2;
		}	
	}
			
	private void crearHeader() throws IOException, ParseException {		
		InputStream is = new FileInputStream(era.getRutaImagen());
	    byte[] bytes = IOUtils.toByteArray(is);
	    int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
	    is.close();
	    CreationHelper helper = wb.getCreationHelper();    
	    // Create the drawing patriarch.  This is the top level container for all shapes. 
	    Drawing drawing = sheet.createDrawingPatriarch();
	    //add a picture shape
	    ClientAnchor anchor = helper.createClientAnchor();
	    anchor.setCol1(0);
	    anchor.setRow1(0);
	    Picture pict = drawing.createPicture(anchor, pictureIdx);
	    pict.resize();
	    row = sheet.createRow(0);	
	    //Consigue nombre de la relacion
	    String nombreRelacion = rDAO.consultaRelacion(era.getIdRelacion()).get(0).getRelacion();
	    cell = row.createCell(2);
	    cell.setCellValue(nombreRelacion);
	    cell.setCellStyle(csNombreRelacion);	    
	    CellRangeAddress region = CellRangeAddress.valueOf("C1:H4");
	    sheet.addMergedRegion(region);
	    row = sheet.createRow(5);
	    cell = row.createCell(0);
	    cell.setCellValue("TIPO DE APOYO: ");
	    cell.setCellStyle(csCamposEncabezado); 
	    cell = row.createCell(1);
	    cell.setCellValue(era.getTipoApoyo());
	    cell.setCellStyle(csPrograma); 
	    sheet.addMergedRegion(new CellRangeAddress(5, 7, 1, 7));
	}

	private void recuperarDatosDetalle(String claveBodega, int razonSocialAlmacen) {
		lstCamposRelacionCertificadosV = new ArrayList<GruposCamposRelacionCertificadosV>();
		if(era.getOpcion() == 1){
			lstCamposRelacionCertificadosV = rDAO.consultaGruposCamposCertificadosV(0, era.getIdPerRel(), era.getIdComprador(),null, claveBodega, era.getCultivoRelacion(),null, null, false, era.getIdPgrCiclo(), razonSocialAlmacen);
		}else{// Cuando ya se asigna la carta de adhesion
			lstCamposRelacionCertificadosV = rDAO.consultaGruposCamposCertificadosV(era.getIdRelacion(),  -1, -1, era.getFolioCartaAdhesion(),  claveBodega, -1, "DET", null, false, 0, razonSocialAlmacen);				     
		}
	}

	private void setMargenSheet(){
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		sheet.setMargin(Sheet.LeftMargin, 0.25);
		sheet.setMargin(Sheet.RightMargin, 0.25);
		sheet.setMargin(Sheet.TopMargin, 0.75);
		sheet.setMargin(Sheet.BottomMargin, 0.75);
		// Setup the Header and Footer Margins
		sheet.setMargin(Sheet.HeaderMargin, 0.25);
		sheet.setMargin(Sheet.FooterMargin, 0.25);
		Header header = sheet.getHeader();
		header.setRight(df.format(date));
		// Set Footer Information with Page Numbers
		Footer footer = sheet.getFooter();
		if(leyendaExpRElacion!=null && !leyendaExpRElacion.isEmpty()){
			footer.setLeft(HSSFHeader.font("Arial Narrow", "Normal")
					+ HSSFHeader.fontSize((short) 9) + leyendaExpRElacion);
		}
	
		footer.setRight(HSSFHeader.font("Arial Narrow", "Normal")
				+ HSSFHeader.fontSize((short) 9)+"Page " + HeaderFooter.page() + " of "
				+ HeaderFooter.numPages());
	}
	
	private void addMergedRegionCeldas(int fila, int firstColumn, int lastColumn, boolean borderTop,
		 boolean borderRight, boolean borderBottom, boolean borderLeft){			
		CellRangeAddress region = CellRangeAddress.valueOf(CellReference.convertNumToColString(firstColumn)+fila+":"+CellReference.convertNumToColString(lastColumn)+fila+"");
		sheet.addMergedRegion(region);
		if(borderTop){
			RegionUtil.setBorderTop(CellStyle.BORDER_THIN,region, sheet, wb);
		}
		
		if(borderRight){
			RegionUtil.setBorderRight(CellStyle.BORDER_THIN,region, sheet, wb );
		}
		if(borderBottom){
			RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region, sheet, wb);
		}
		
		if(borderLeft){
			RegionUtil.setBorderLeft(CellStyle.BORDER_THIN,region, sheet, wb );
		}
	}
	
	private void setCellStyles(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();
		DataFormat formatImporte = wb.createDataFormat();
		DataFormat formatVolumen = wb.createDataFormat();
		
		
		font8Bold = wb.createFont();
		font8Bold.setFontHeightInPoints((short) 8);
		font8Bold.setFontName("Arial Narrow");
		font8Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		font9Bold = wb.createFont();
		font9Bold.setFontHeightInPoints((short) 9);
		font9Bold.setFontName("Arial Narrow");
		font9Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		Font font9Normal= wb.createFont();
		font9Normal.setFontHeightInPoints((short) 9);
		font9Normal.setFontName("Arial Narrow");
		
		Font font10Normal= wb.createFont();
		font10Normal.setFontHeightInPoints((short) 10);
		font10Normal.setFontName("Arial Narrow");
		
		Font font11Normal= wb.createFont();
		font11Normal.setFontHeightInPoints((short) 11);
		font11Normal.setFontName("Arial Narrow");
		
		
		Font fontNombreRelacion= wb.createFont();
		fontNombreRelacion.setFontHeightInPoints((short) 12);
		fontNombreRelacion.setFontName("Arial Narrow");
		fontNombreRelacion.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontNombreRelacion.setUnderline(Font.U_DOUBLE);

		
		//Nombre Relacion	
		csNombreRelacion =  wb.createCellStyle();
		csNombreRelacion.setAlignment(CellStyle.ALIGN_CENTER);
		csNombreRelacion.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		csNombreRelacion.setFont(fontNombreRelacion);
		//Informacion Encabezado
		csCamposEncabezado =  wb.createCellStyle();
		csCamposEncabezado.setAlignment(CellStyle.ALIGN_GENERAL);
		csCamposEncabezado.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
		csCamposEncabezado.setFont(font9Bold);

		csPrograma = wb.createCellStyle();
		csPrograma.setAlignment(CellStyle.ALIGN_CENTER);
		csPrograma.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csPrograma.setWrapText(true);
		csPrograma.setFont(font9Bold);
		
		// Campos Encabezado Detalle
		csCamposEncDetalle = wb.createCellStyle();
		csCamposEncDetalle.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposEncDetalle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		csCamposEncDetalle.setWrapText(true);
		csCamposEncDetalle.setFont(font9Bold);
		csCamposEncDetalle.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposEncDetalle.setBorderTop(CellStyle.BORDER_THIN);
		csCamposEncDetalle.setBorderRight(CellStyle.BORDER_THIN);
		csCamposEncDetalle.setBorderBottom(CellStyle.BORDER_THIN);
		
		//Tipos estilos campos detalle Fecha
		csCamposDetalleFecha = wb.createCellStyle();
		csCamposDetalleFecha.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yy"));
		csCamposDetalleFecha.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleFecha.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleFecha.setFont(font9Normal);
		csCamposDetalleFecha.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleFecha.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleFecha.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleFecha.setBorderBottom(CellStyle.BORDER_THIN);
		//Tipos estilos campos detalle importe
		csCamposDetalleImporte = wb.createCellStyle();
		csCamposDetalleImporte.setDataFormat(formatImporte.getFormat("###,###,##0.00"));
		csCamposDetalleImporte.setAlignment(CellStyle.ALIGN_RIGHT);
		csCamposDetalleImporte.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleImporte.setFont(font9Normal);
		csCamposDetalleImporte.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleImporte.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleImporte.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleImporte.setBorderBottom(CellStyle.BORDER_THIN);
		csCamposDetalleImporte.setWrapText(true);		
		//Tipos estilos campos detalle volumen
		csCamposDetalleVolumen = wb.createCellStyle();
		csCamposDetalleVolumen.setDataFormat(formatVolumen.getFormat("###,###,##0.000"));
		csCamposDetalleVolumen.setAlignment(CellStyle.ALIGN_RIGHT);
		csCamposDetalleVolumen.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleVolumen.setFont(font9Normal);
		csCamposDetalleVolumen.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setBorderBottom(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setWrapText(true);
		//Campos Detalle Default
		csCamposDetalle = wb.createCellStyle();
		csCamposDetalle.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalle.setFont(font9Normal);
		csCamposDetalle.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalle.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalle.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalle.setBorderBottom(CellStyle.BORDER_THIN);
		//Campo leyenda
		csLeyenda = wb.createCellStyle();
		csLeyenda.setAlignment(CellStyle.ALIGN_LEFT);
		csLeyenda.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
		csLeyenda.setFont(font9Normal);
		
		
		//Nombre auditor
		csAuditor = wb.createCellStyle();
		csAuditor.setAlignment(CellStyle.ALIGN_CENTER);
		csAuditor.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		csAuditor.setWrapText(true);
		csAuditor.setFont(font8Bold);
	}
}
