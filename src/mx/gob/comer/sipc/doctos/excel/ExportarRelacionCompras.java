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
import mx.gob.comer.sipc.domain.Bancos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.catalogos.Predios;
import mx.gob.comer.sipc.domain.catalogos.Productores;
import mx.gob.comer.sipc.domain.transaccionales.ComprasContrato;
import mx.gob.comer.sipc.domain.transaccionales.ComprasDatosProductor;
import mx.gob.comer.sipc.domain.transaccionales.ComprasEntradaBodega;
import mx.gob.comer.sipc.domain.transaccionales.ComprasFacVenta;
import mx.gob.comer.sipc.domain.transaccionales.ComprasFacVentaGlobal;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPagoProdAxc;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPagoProdSinAxc;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPredio;
import mx.gob.comer.sipc.vistas.domain.ComprasDatosParticipanteV;
import mx.gob.comer.sipc.vistas.domain.ComprasMaxCamposV;
import mx.gob.comer.sipc.vistas.domain.GrupoPorRelacion;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionV;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
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

public class ExportarRelacionCompras {

	private ExportarRelacionAction era;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private Row row;
	private int countRow;
	private int countColumn;
	private RelacionesDAO rDAO;
	private CatalogosDAO cDAO;
	private UtileriasDAO uDAO;
	private Map<Integer,String> configTotales;
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	private Long idPerRel;
	private int ultimaFilaDetalle;
	private int primeraFilaDetalle;
	private List<ComprasDatosParticipanteV> lstComprasDatosParticipanteV;
	private List<ComprasDatosParticipanteV> lstContenidoEncabezadoComprasV;	
	private List<GruposCamposRelacionV> lstGruposCamposRelacionV;
	private List<GruposCamposRelacionV> lstGruposCamposDetalleRelacionV;
	private long idCompPer;
	private Integer idEstado;
	
	private CellStyle csCamposEncabezado;
	private CellStyle csCamposEncDetalle;
	private CellStyle csCamposGrupoEncDetalle;
	private CellStyle csCamposDetalleFecha;
	private CellStyle csCamposDetalleImporte;
	private CellStyle csCamposDetalleVolumen;
	private CellStyle csCamposDetalleNumero;
	private CellStyle csCamposDetalle;
	private CellStyle csNombreRelacion;
	private CellStyle csLeyenda;
	private CellStyle csPrograma;
	private CellStyle csAuditor;
	private Font font6Bold;
	private Font font8Bold;
	private Cell cell;
	
	private CellStyle csCamposDetalleRed;
	private int numeroPagina;
	private String leyendaExpRElacion;
	private List<GrupoPorRelacion> lstCabeceraGrupoDetalle;
	
	private int totalFilasEncabezadoYCabecera;
	private int numeroRegistroPorPagina;
	private int columnaTotalDeLaFactura;
	private int columnaImporteDocPago;
	
	
	public ExportarRelacionCompras(ExportarRelacionAction era, Session session) {
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
			if(era.getOpcion()== 1){//Consulta de informacion capturada por bodega
				countRow = 0;
				sheet = wb.createSheet(era.getClaveBodega());
				//sheet.protectSheet("sipc2013mal");	
				setMargenSheet();
				idCompPer = era.getIdCompPer();
				crearEncabezado();
				row = sheet.createRow(++countRow);
				crearCabeceraGrupoDetalle();
				crearCabeceraCampoDetalle();
				primeraFilaDetalle = countRow+2;
				crearDetalle(era.getClaveBodega());
				crearTotales();
				crearHeader();
			}else{//opcion 2 Una vez que se ha asignado la carta de adhesion	
				lstComprasDatosParticipanteV = rDAO.consultaComprasDatosParticipanteV(era.getFolioCartaAdhesion());
				for(ComprasDatosParticipanteV l: lstComprasDatosParticipanteV){
					countRow = 0;
					sheet = wb.createSheet(l.getClaveBodega()+"-"+l.getEstado().toUpperCase());
					sheet.protectSheet("sipc2013mal");	
					setMargenSheet();
					idCompPer = l.getIdCompPer();
					idEstado = l.getEstadoAcopio();
					crearEncabezado();
					countRow++;
					crearCabeceraGrupoDetalle();
					crearCabeceraCampoDetalle();
					primeraFilaDetalle = countRow+2;
					crearDetalle(era.getClaveBodega());
					crearTotales();
					crearHeader();
					mostrarAuditor(countRow);
					lstContenidoEncabezadoComprasV = null;
				}
			}	
						
						
		    FileOutputStream out = new FileOutputStream(new File(era.getRutaCartaAdhesion()+era.getNombreRelacion()));
		    wb.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");		
	}
	
	private void crearEncabezado() throws JDBCException, Exception {	
		row = sheet.createRow(countRow);
		if(lstContenidoEncabezadoComprasV !=null){
			countRow++;
			numeroPagina++;
		}else{
			countRow = 8;
			numeroPagina++;
			if(era.getOpcion()== 1){
				lstContenidoEncabezadoComprasV =  rDAO.consultaComprasDatosParticipanteV(idCompPer);
				idEstado = lstContenidoEncabezadoComprasV.get(0).getEstadoAcopio();
			}else{
				lstContenidoEncabezadoComprasV = lstComprasDatosParticipanteV;
			}
		}
		
		if(lstContenidoEncabezadoComprasV.size()>0){					
			ComprasDatosParticipanteV gcrcv = lstContenidoEncabezadoComprasV.get(0);
			idPerRel = gcrcv.getIdPerRel();
			//Recupera los encabezados de la configuracion de la relacion
			if(lstGruposCamposRelacionV == null){
				lstGruposCamposRelacionV = rDAO.consultaGruposCampostV(0, 0, idPerRel, 0, 0, "ENC");
			}
			
			countColumn = 0;
			for(GruposCamposRelacionV l:lstGruposCamposRelacionV){
				if(l.getIdCampo()==1){							
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
					 if(numeroPagina == 1){
						 totalFilasEncabezadoYCabecera++;
						 
					 }
				}else if(l.getIdCampo()==2){
					row = sheet.createRow(++countRow);
					cell = row.createCell(countColumn);
					cell.setCellValue(l.getCampo()+": "+era.getFolioCartaAdhesion());
					cell.setCellStyle(csCamposEncabezado);
					sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7 ));
					 if(numeroPagina == 1){
						 totalFilasEncabezadoYCabecera++;
						 
					 }
				}else if(l.getIdCampo()==3){
					Estado estado = cDAO.consultaEstado(gcrcv.getEstadoAcopio()).get(0);
					row = sheet.createRow(++countRow);
					cell = row.createCell(countColumn);
					cell.setCellValue(l.getCampo()+": "+estado.getNombre().toUpperCase());
					cell.setCellStyle(csCamposEncabezado);
					sheet.addMergedRegion(new CellRangeAddress(countRow, countRow, 0, 7));
					if(numeroPagina == 1){
						 totalFilasEncabezadoYCabecera++;
						 
					}
				}
						
			}
			if(numeroPagina == 1){
				if(era.getOpcion()==1){
					totalFilasEncabezadoYCabecera+=5;
				}else{
					totalFilasEncabezadoYCabecera+=11;
				}
				 
			}
			
		}
			
	}

	private void crearDetalle(String claveBodega) throws JDBCException, Exception {
		int maxFilasCompras = 0;
		int tmpCountRow = countRow;
		int tmpCountRowByProductor = countRow;
		int ultimaFilaByProductor =0;
		boolean yaExisteColumna = false, yaExisteFila = false;
		//RECUPERA "DATOS DEL PRODUCTOR"
		int j=1;
		List<ComprasDatosProductor> lstComprasDatosProductor = rDAO.consultaComprasDatosProductor(0,idCompPer, 0);		
		for(ComprasDatosProductor productor: lstComprasDatosProductor){
			//Recupera el maximo del grupo de filas en los datos del productor
			maxFilasCompras = recuperarMaxCampoCompras(idCompPer, productor.getIdCompProd());
			countColumn = 0;
			int columnaAsignada = 0;
			boolean errorByCampo = false;
			System.out.println("Productor-->"+productor.getFolioProductor());	
			List<ComprasEntradaBodega> lstComprasEntradaBodegas = null;
			List<ComprasFacVentaGlobal> lstComprasFacVentaGlobal = null;
			List<ComprasFacVenta> lstComprasFacVenta = null;
			List<ComprasContrato> lstComprasContrato = null;
			List<ComprasPagoProdAxc> lstComprasPagoProdAxc = null;
			List<ComprasPagoProdSinAxc> lstComprasPagoProdSinAxc = null;
			boolean bandProdBen = false;
			List<Productores> lstProductor = rDAO.consultaProductores(productor.getFolioProductor());
			Productores prodTemp = null;
			if(lstProductor.size()>0){
				prodTemp = lstProductor.get(0);
			}
			if(j!=1){
				tmpCountRowByProductor = ultimaFilaByProductor+1;
			}else{
				tmpCountRowByProductor = countRow+1;
			}
			for(GruposCamposRelacionV l: lstGruposCamposDetalleRelacionV){				
				if(l.getIdGrupo().intValue() == 6){	// GRUPO 6	PRODUCTOR
					if(l.getIdCampo().intValue() == 6){ // CAMPO 6 FOLIO DEL PREDIO (Incluir el secuencial)
						//Recuperar los folios del predio
						List<ComprasPredio> lstComprasPredio = rDAO.consultaComprasPredio(productor.getIdCompProd());
						//Verifica que los predios existan tambien en el catalogo de predios_relaciones
						//if(lstComprasPredio.size()>0){
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}
							if(yaExisteColumna){
								 ++countColumn;  
							}	
							columnaAsignada = countColumn;
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
									System.out.println("Empieza detalle "+tmpCountRow);
							
								}

								try{
									String valor ="";
									boolean existePredio = true;
									if(lstComprasPredio.get(i).getFolioPredio()!=null){
										valor = lstComprasPredio.get(i).getFolioPredio()+"-"+lstComprasPredio.get(i).getFolioPredioSec();
										//Verifica que el predio y predio secuencial exista en la tabla de predios_relaciones									
										List<Predios> lstPredios = rDAO.consultaPredios(lstComprasPredio.get(i).getFolioPredio(), lstComprasPredio.get(i).getFolioPredioSec().longValue(), "", productor.getFolioProductor().toString(), idEstado);
										if(lstPredios.size()==0){
											existePredio = false;
										}										
									}else{
										valor = lstComprasPredio.get(i).getPredioAlterno();
										//Verifica que el predio alterno exista en la tabla de predios_relaciones									
										List<Predios> lstPredios = rDAO.consultaPredios("", 0, lstComprasPredio.get(i).getPredioAlterno(), productor.getFolioProductor().toString(), idEstado);
										if(lstPredios.size()<0){
											existePredio = false;
										}
									}
									
									System.out.println("tmp grupo 6 "+tmpCountRow);
									if(!existePredio){
										cell = row.createCell(countColumn);
										cell.setCellValue(valor);
										cell.setCellStyle(csCamposDetalleRed);
									}else{
										cell = row.createCell(countColumn);
										cell.setCellValue(valor);
										cell.setCellStyle(csCamposDetalle);
									}
								
									
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
					}//end idCampo == 6
				
					
					if(l.getIdCampo().intValue() == 7){ //CAMPO 7 FOLIO DEL PRODUCTOR
						System.out.println("campo 7");
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}								
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}							
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}
									
							//Verifica si existe el folio del productor en la tabla de productores
							if(lstProductor.size()>0){
								cell = row.createCell(countColumn);
								cell.setCellValue(productor.getFolioProductor().toString());
								cell.setCellStyle(csCamposDetalle);
							}else{
								cell = row.createCell(countColumn);
								cell.setCellValue(productor.getFolioProductor().toString());
								cell.setCellStyle(csCamposDetalleRed);						
							}
						}
						
						yaExisteColumna = true;
						yaExisteFila = true;
						
					}//End campo 7
					
					if(l.getIdCampo().intValue() == 8){  // CAMPO 8 NOMBRE (S) O RAZÓN SOCIAL
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}								
						columnaAsignada = countColumn;
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								 row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}							
							cell = row.createCell(countColumn);
							cell.setCellValue((prodTemp!=null?prodTemp.getNombre():""));
							cell.setCellStyle(csCamposDetalle);
							
						}
						yaExisteColumna = true;
						yaExisteFila = true;
						
					}//END CAMPO 8	
					if(l.getIdCampo().intValue() == 9){  // CAMPO 9 APELLIDO PATERNO
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						
						if(yaExisteColumna){
							 ++countColumn;
						}
						columnaAsignada = countColumn;
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								 row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}
							
							String apellidoPaterno ="";
							if(prodTemp !=null){
								if(prodTemp.getPaterno()!=null){
									apellidoPaterno = prodTemp.getPaterno(); 
								}
							}
							
							cell = row.createCell(countColumn);
							cell.setCellValue(apellidoPaterno);
							cell.setCellStyle(csCamposDetalle);
							
							
						}
						yaExisteColumna = true;
						yaExisteFila = true;
						
					}//END CAMPO 9	
					
					if(l.getIdCampo().intValue() == 10){  // CAMPO 10 APELLIDO MATERNO
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								 row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}
							String apellidoMaterno ="";
							if(prodTemp !=null){
								if(prodTemp.getMaterno()!=null){
									apellidoMaterno = prodTemp.getMaterno(); 
								}
							}
							cell = row.createCell(countColumn);
							cell.setCellValue(apellidoMaterno);
							cell.setCellStyle(csCamposDetalle);
						}
						yaExisteColumna = true;
						yaExisteFila = true;
						
					}//END 10 APELLIDO MATERNO	
					
					if(l.getIdCampo().intValue() == 69){  // CAMPO 69 CURP
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								 row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}
							String curp ="";
							if(prodTemp !=null){
								if(prodTemp.getCurp()!=null){
									curp = prodTemp.getCurp();
								}
							}
							cell = row.createCell(countColumn);
							cell.setCellValue(curp);
							cell.setCellStyle(csCamposDetalle);
							
						}
						yaExisteColumna = true;
						yaExisteFila = true;
						
					}//END 69 CURP	
					
					if(l.getIdCampo().intValue() == 17){  // CAMPO 17 RFC
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								 row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}
							String rfc ="";
							if(prodTemp !=null){
								if(prodTemp.getRfc()!=null){
									rfc = prodTemp.getRfc();
								}
							}
							cell = row.createCell(countColumn);
							cell.setCellValue(rfc);
							cell.setCellStyle(csCamposDetalle);
						}
						yaExisteColumna = true;
						yaExisteFila = true;
					}//END 17 RFC
				}//End grupo 6				
				
				
				if(l.getIdGrupo().intValue() == 7){	// GRUPO 7	ENTRADA A LA BODEGA
					System.out.println("Grupo 7");
					if(l.getIdCampo().intValue() == 11){  // CAMPO 11 N° DE BOLETA O TICKET DE BÁSCULA
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasEntradaBodegas == null){
							//Consultar la lista Entrada a bodega
							lstComprasEntradaBodegas = rDAO.consultaComprasEntradaBodega( productor.getIdCompProd());
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								String valor = lstComprasEntradaBodegas.get(i).getBoletaTicketBascula();
								cell = row.createCell(countColumn);
								cell.setCellValue(valor);
								cell.setCellStyle(csCamposDetalle);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo  = false;
						
					}//END 11 N° DE BOLETA O TICKET DE BÁSCULA	
					
					if(l.getIdCampo().intValue() == 12){  // CAMPO 12 FECHA DE ENTRADA (dd-mmm-aa)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasEntradaBodegas == null){
							//Consultar la lista Entrada a bodega
							lstComprasEntradaBodegas = rDAO.consultaComprasEntradaBodega( productor.getIdCompProd());
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								System.out.println("tmpCountRow + encabezados "+tmpCountRow);
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue(lstComprasEntradaBodegas.get(i).getFechaEntrada());
								cell.setCellStyle(csCamposDetalleFecha);
								//System.out.println("FECHA "+ lstComprasEntradaBodegas.get(i).getFechaEntrada());
								//String valor = lstComprasEntradaBodegas.get(i).getFechaEntrada().toString();
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
					}//END 12 FECHA DE ENTRADA (dd-mmm-aa)
					
					
					if(l.getIdCampo().intValue() == 63){  // CAMPO 63 P.N.A. DE LA BOLETA (TON.)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasEntradaBodegas == null){
							//Consultar la lista Entrada a bodega
							lstComprasEntradaBodegas = rDAO.consultaComprasEntradaBodega( productor.getIdCompProd());
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								System.out.println("tmpCountRow + encabezados "+tmpCountRow);
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue(lstComprasEntradaBodegas.get(i).getVolBolTicket());
								cell.setCellStyle(csCamposDetalleVolumen);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
								
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 63 P.N.A. DE LA BOLETA (TON.)
				}//End grupo 7
				
				/**** GRUPO 8 FACTURA DE VENTA GLOBAL, UTILIZADA PARA LA DETERMINACIÓN DEL T.C. (DE SER DIFERENTE A LA DEL PRODUCTOR)****/
				if(l.getIdGrupo().intValue() == 8){	
					System.out.println("Grupo 8");
					if(l.getIdCampo().intValue() == 14){  // CAMPO 14 NOMBRE DE LA PERSONA FISICA O MORAL QUE FACTURA
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVentaGlobal == null){
							lstComprasFacVentaGlobal = rDAO.consultaComprasFacVentaGlobal( productor.getIdCompProd());//Consultar la lista de Factura de Venta Global
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								String valor = lstComprasFacVentaGlobal.get(i).getNombrePerFac();
								cell = row.createCell(countColumn);
								cell.setCellValue(valor);
								cell.setCellStyle(csCamposDetalle);
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 14 NOMBRE DE LA PERSONA FISICA O MORAL QUE FACTURA
					
					if(l.getIdCampo().intValue() == 15){  // CAMPO 15 NÚMERO
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}	
						columnaAsignada = countColumn;
						if(lstComprasFacVentaGlobal == null){
							lstComprasFacVentaGlobal = rDAO.consultaComprasFacVentaGlobal( productor.getIdCompProd());//Consultar la lista de Factura de Venta Global
						}
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}		
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								String valor = lstComprasFacVentaGlobal.get(i).getFolioFacGlobal();
								cell = row.createCell(countColumn);
								cell.setCellValue(valor);
								cell.setCellStyle(csCamposDetalle);
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 15 NÚMERO
					
					if(l.getIdCampo().intValue() == 16){  // CAMPO 16 FECHA DE ENTRADA (dd-mmm-aa)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVentaGlobal == null){
							lstComprasFacVentaGlobal = rDAO.consultaComprasFacVentaGlobal( productor.getIdCompProd());//Consultar la lista de Factura de Venta Global
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalleFecha);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalleFecha);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 16 FECHA DE ENTRADA (dd-mmm-aa)
					
					
					if(l.getIdCampo().intValue() == 17){  // CAMPO 17 RFC
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVentaGlobal == null){
							lstComprasFacVentaGlobal = rDAO.consultaComprasFacVentaGlobal( productor.getIdCompProd());//Consultar la lista de Factura de Venta Global
						}
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}		
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}

							try{
								String valor = lstComprasFacVentaGlobal.get(i).getRfcFacGlobal();
								cell = row.createCell(countColumn);
								cell.setCellValue(valor);
								cell.setCellStyle(csCamposDetalle);
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 17 RFC
					
					if(l.getIdCampo().intValue() == 18){  // CAMPO 18 IMPORTE FACTURADO ($)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVentaGlobal == null){
							lstComprasFacVentaGlobal = rDAO.consultaComprasFacVentaGlobal( productor.getIdCompProd());//Consultar la lista de Factura de Venta Global
						}
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue(lstComprasFacVentaGlobal.get(i).getImpFacGlobal());
								cell.setCellStyle(csCamposDetalleImporte);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 18 IMPORTE FACTURADO ($)
					
					if(l.getIdCampo().intValue() == 64){  // CAMPO 64 P.N.A. DE LA FACTURA GLOBAL (TON.)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVentaGlobal == null){
							lstComprasFacVentaGlobal = rDAO.consultaComprasFacVentaGlobal( productor.getIdCompProd());//Consultar la lista de Factura de Venta Global
						}
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{								
								cell = row.createCell(countColumn);
								cell.setCellValue( lstComprasFacVentaGlobal.get(i).getVolFacGlobal());
								cell.setCellStyle(csCamposDetalleVolumen);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 64 P.N.A. DE LA FACTURA GLOBAL (TON.)
					
					
				}//End GRUPO 8
				
				/**** GRUPO 9 FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO ****/
				if(l.getIdGrupo().intValue() == 9){	
					System.out.println("Grupo 9");
					if(l.getIdCampo().intValue() == 15){  // CAMPO 15 NÚMERO
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVenta == null){
							lstComprasFacVenta = rDAO.consultaComprasFacVenta( productor.getIdCompProd());//Consultar la lista de FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								String valor = lstComprasFacVenta.get(i).getFolioFac();
								cell = row.createCell(countColumn);
								cell.setCellValue(valor);
								cell.setCellStyle(csCamposDetalle);
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END  15 NÚMERO	
					
					
					if(l.getIdCampo().intValue() == 16){  // CAMPO 16 FECHA DE ENTRADA (dd-mmm-aa)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
				
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVenta == null){
							lstComprasFacVenta = rDAO.consultaComprasFacVenta( productor.getIdCompProd());//Consultar la lista de FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalleFecha);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 16 FECHA DE ENTRADA (dd-mmm-aa)
								
					if(l.getIdCampo().intValue() == 17){  // CAMPO 17 RFC
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVenta == null){
							lstComprasFacVenta = rDAO.consultaComprasFacVenta( productor.getIdCompProd());//Consultar la lista de FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO
						}
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								//String valor = lstComprasFacVenta.get(i).getRfcFac();
								String rfc ="";
								if(prodTemp !=null){
									if(prodTemp.getRfc()!=null){
										rfc = prodTemp.getRfc();
									}
								}
								cell = row.createCell(countColumn);
								cell.setCellValue(rfc);
								cell.setCellStyle(csCamposDetalle);
							}catch(IndexOutOfBoundsException e){
								errorByCampo = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}
							

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;	
					}//END 17 RFC
					
					if(l.getIdCampo().intValue() == 19){  // CAMPO 19 P.N.A. SOLICITADO PARA APOYO (TON.)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVenta == null){
							lstComprasFacVenta = rDAO.consultaComprasFacVenta( productor.getIdCompProd());//Consultar la lista de FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue(lstComprasFacVenta.get(i).getVolSolFac());
								cell.setCellStyle(csCamposDetalleVolumen);
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 19 P.N.A. SOLICITADO PARA APOYO (TON.)
					
					
					if(l.getIdCampo().intValue() == 20){  // CAMPO 20 IMPORTE FACTURADO DE LO SOLICITADO PARA APOYO ($)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}		
						columnaAsignada = countColumn;
						if(lstComprasFacVenta == null){
							lstComprasFacVenta = rDAO.consultaComprasFacVenta( productor.getIdCompProd());//Consultar la lista de FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO
						}
						
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							try{
								cell = row.createCell(countColumn);
								cell.setCellValue(lstComprasFacVenta.get(i).getImpSolFac());
								cell.setCellStyle(csCamposDetalleImporte);
							}catch(IndexOutOfBoundsException e){
								errorByCampo  = true;
							}										
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 20 IMPORTE FACTURADO DE LO SOLICITADO PARA APOYO ($)
					
					
					if(l.getIdCampo().intValue() == 65){ // CAMPO 65 P.N.A. TOTAL DE LA FACTURA (TON.)
						if(j==1){
							tmpCountRow = countRow;
						}else{
							tmpCountRow = ultimaFilaByProductor; 
						}	
						if(yaExisteColumna){
							 ++countColumn;
						}	
						columnaAsignada = countColumn;
						columnaTotalDeLaFactura = countColumn; 
						if(lstComprasFacVenta == null){
							lstComprasFacVenta = rDAO.consultaComprasFacVenta( productor.getIdCompProd());//Consultar la lista de FACTURA DEL PRODUCTOR POR LA VENTA DEL GRANO
						}
						for(int i=0; i<maxFilasCompras; i++){
							if(yaExisteFila){
								row = sheet.getRow(++tmpCountRow); 
							}else{
								row = sheet.createRow(++tmpCountRow);
							}	
							if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
								tmpCountRow += totalFilasEncabezadoYCabecera;
								row = sheet.getRow(tmpCountRow);
							}else{
								tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
							}
							if(lstComprasFacVenta.size()>0){
								try{
									
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasFacVenta.get(i).getVolTotalFac());
									cell.setCellStyle(csCamposDetalleVolumen);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}
							}else{
								errorByCampo = true;
							}
																	
							if(errorByCampo){
								cell = row.createCell(countColumn);
								cell.setCellValue("");
								cell.setCellStyle(csCamposDetalle);
							}

						}
						yaExisteColumna = true;
						yaExisteFila = true;
						errorByCampo = false;
						
					}//END 65 P.N.A. TOTAL DE LA FACTURA (TON.)						
				
				}//END GRUPO 9
					/**** GRUPO 10 CONTRATO DE COMPRA-VENTA A TÉRMINO ****/
					if(l.getIdGrupo().intValue() == 10){	
						System.out.println("Grupo 10");
						
						if(l.getIdCampo().intValue() == 21){  // CAMPO 21 FOLIO
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
					
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasContrato == null){
								lstComprasContrato = rDAO.consultaComprasContratos( productor.getIdCompProd());//Consultar la lista de CONTRATO DE COMPRA-VENTA A TÉRMINO
							}
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								try{
									String valor = lstComprasContrato.get(i).getFolioContrato();
									cell = row.createCell(countColumn);
									cell.setCellValue(valor);
									cell.setCellStyle(csCamposDetalle);
								}catch(IndexOutOfBoundsException e){
									errorByCampo  = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 21 FOLIO
						
						
						if(l.getIdCampo().intValue() == 22){  // CAMPO 22 PRECIO PACTADO EN EL CONTRATO (DLS.)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasContrato == null){
								lstComprasContrato = rDAO.consultaComprasContratos( productor.getIdCompProd());//Consultar la lista de CONTRATO DE COMPRA-VENTA A TÉRMINO
							}
							
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
								}
								
								try{
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasContrato.get(i).getImpPactado());
									cell.setCellStyle(csCamposDetalleImporte);									
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;						
						}//END 22 PRECIO PACTADO EN EL CONTRATO (DLS.)
							
						
					}//End GRUPO 10
					
					
					/**** GRUPO 11 PAGO AL PRODUCTOR EN AXC ****/
					if(l.getIdGrupo().intValue() == 11){	
						System.out.println("Grupo 11");
						if(l.getIdCampo().intValue() == 23){  // CAMPO 23 PAGO DEL PRECIO PACTADO EN AXC POR TONELADA
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
					
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdAxc == null){
								lstComprasPagoProdAxc = rDAO.consultaComprasPagoProdAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR EN AXCz
							}
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}

								try{
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasPagoProdAxc.get(i).getImpPacAxc());
									cell.setCellStyle(csCamposDetalleImporte);
									
								}catch(IndexOutOfBoundsException e){
									errorByCampo  = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
						}//END 23 PAGO DEL PRECIO PACTADO EN AXC POR TONELADA
						
						
						if(l.getIdCampo().intValue() == 24){  // CAMPO 24 PAGO DEL PRECIO PACTADO EN AXC IMPORTE ($)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}	
							
							columnaAsignada = countColumn;
							if(lstComprasPagoProdAxc == null){
								lstComprasPagoProdAxc = rDAO.consultaComprasPagoProdAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR EN AXCz
							}
							
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}

								try{
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasPagoProdAxc.get(i).getImpPacAxcProd());
									cell.setCellStyle(csCamposDetalleImporte);
								}catch(IndexOutOfBoundsException e){
									errorByCampo  = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 24 PRECIO PACTADO EN EL CONTRATO (DLS.)
						
						if(l.getIdCampo().intValue() == 25){  // CAMPO 25 IMPORTE DE LA COMPENSACIÓN POR TON. ($)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdAxc == null){
								lstComprasPagoProdAxc = rDAO.consultaComprasPagoProdAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR EN AXCz
							}
							
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}

								try{
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasPagoProdAxc.get(i).getImpCompTon());
									cell.setCellStyle(csCamposDetalleImporte);
									
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 25 IMPORTE DE LA COMPENSACIÓN POR TON. ($)

						
						if(l.getIdCampo().intValue() == 26){  // CAMPO 26 IMPORTE TOTAL DE LA COMPENSACIÓN ($)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdAxc == null){
								lstComprasPagoProdAxc = rDAO.consultaComprasPagoProdAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR EN AXCz
							}
							
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								try{
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasPagoProdAxc.get(i).getImpCompTonProd());
									cell.setCellStyle(csCamposDetalleImporte);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 26 IMPORTE TOTAL DE LA COMPENSACIÓN ($)
					}//End GRUPO 11
					
					
					/**** GRUPO 12 PAGO AL PRODUCTOR SIN AXC ****/
					if(l.getIdGrupo().intValue() == 12){	
						System.out.println("Grupo 12");
						if(l.getIdCampo().intValue() == 16){  // CAMPO 16 FECHA DE ENTRADA (dd-mmm-aa)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
					
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								try{
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}catch(IndexOutOfBoundsException e){
									errorByCampo  = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 16 FECHA DE ENTRADA (dd-mmm-aa)

						if(l.getIdCampo().intValue() == 21){  // CAMPO 21 FOLIO
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
					
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow = romperPagina(tmpCountRow, columnaAsignada);
								}
								
								try{
									String valor = lstComprasPagoProdSinAxc.get(i).getFolioDocPago();
									cell = row.createCell(countColumn);
									cell.setCellValue(valor);
									cell.setCellStyle(csCamposDetalle);
								}catch(IndexOutOfBoundsException e){
									errorByCampo  = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 21 FOLIO					

						
						if(l.getIdCampo().intValue() == 27){ // CAMPO 27 TIPO DE DOCUMENTO DE PAGO (Póliza de cheque, Cheque de pago, Recibos de liquidación al productor o Pago electronico)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
					
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								
								try{
									String valor = "";
									Integer tipoDocumento = lstComprasPagoProdSinAxc.get(i).getIdTipoDocPago();
									if(tipoDocumento != null){
										//Consigue el tipo de documento
										valor  = cDAO.cosultaTipoDocumentoPagos(tipoDocumento).get(0).getTipoPago();
									}
									cell = row.createCell(countColumn);
									cell.setCellValue(valor);
									cell.setCellStyle(csCamposDetalle);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 27 TIPO DE DOCUMENTO DE PAGO (Póliza de cheque, Cheque de pago, Recibos de liquidación al productor o Pago electrónico)
						

						if(l.getIdCampo().intValue() == 29){  // CAMPO 29 BANCO
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}	
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								try{
									String valor = null;
									// Consulta el banco 
									List<Bancos> lstBanco = new ArrayList<Bancos>();
									if(lstComprasPagoProdSinAxc.get(i).getBancoId()!=null){
										lstBanco = cDAO.consultaBanco(lstComprasPagoProdSinAxc.get(i).getBancoId());
									}
									if(lstBanco.size()>0){
										valor = lstBanco.get(0).getNombre(); 
									}	
									cell = row.createCell(countColumn);
									cell.setCellValue(valor);
									cell.setCellStyle(csCamposDetalle);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END CAMPO 29 BANCO
						
						if(l.getIdCampo().intValue() == 66){  // CAMPO 66 IMPORTE DE DOCUMENTO DE PAGO ($)

							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							columnaImporteDocPago = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								
								try{
									cell = row.createCell(countColumn);
									cell.setCellValue(lstComprasPagoProdSinAxc.get(i).getImpDocPago());
									cell.setCellStyle(csCamposDetalleImporte);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 66 IMPORTE DE DOCUMENTO DE PAGO ($)

						if(l.getIdCampo().intValue() == 67){  // CAMPO 67 IMPORTE TOTAL PAGADO ($)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							int temporalIndex0 = 0;
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								if(i==0){
									temporalIndex0 = tmpCountRow;
								}
								try{
									
									String valor = "";
									cell = row.createCell(countColumn);
									cell.setCellValue(valor);
									cell.setCellStyle(csCamposDetalleImporte);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}

							}
							String letraDeColumna = CellReference.convertNumToColString(columnaImporteDocPago);
							String strFormula= "SUM("+letraDeColumna+(tmpCountRowByProductor+1)+":"+letraDeColumna+(tmpCountRow+1)+")";
							row = sheet.getRow(temporalIndex0); 
							cell = row.createCell(countColumn);
							cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
							cell.setCellFormula(strFormula);
							cell.setCellStyle(csCamposDetalleImporte);
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 67 IMPORTE TOTAL PAGADO ($)

						if(l.getIdCampo().intValue() == 68){  // CAMPO 68 PRECIO PAGADO POR TONELADA ($)
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}		
							columnaAsignada = countColumn;
							if(lstComprasPagoProdSinAxc == null){
								lstComprasPagoProdSinAxc = rDAO.consultaComprasPagoProdSinAxc( productor.getIdCompProd());//Consultar la lista de PAGO AL PRODUCTOR SIN AXC
							}
							int temporalIndex0 = 0;
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}
								
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
								}
								if(i==0){
									temporalIndex0 = tmpCountRow;
								}
								try{
									String valor = ""; //777lstComprasPagoProdSinAxc.get(i).getImpPrecioTonPago().toString();
									cell = row.createCell(countColumn);
									cell.setCellValue(valor);
									cell.setCellStyle(csCamposDetalleImporte);
								}catch(IndexOutOfBoundsException e){
									errorByCampo = true;
								}										
								if(errorByCampo){
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}
							}
							
						String letraDeColumna = CellReference.convertNumToColString(columnaImporteDocPago);
						String letraDeColumna1 = CellReference.convertNumToColString(columnaTotalDeLaFactura);
						String strFormula = "SUM(" + letraDeColumna
								+ (tmpCountRowByProductor + 1) + ":"
								+ letraDeColumna + (tmpCountRow + 1) + ")"
								+ "/" 
								+"SUM(" + letraDeColumna1
								+ (tmpCountRowByProductor + 1) + ":"
								+ letraDeColumna1 + (tmpCountRow + 1) + ")";
							row = sheet.getRow(temporalIndex0); 
							cell = row.createCell(countColumn);
							cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
							cell.setCellFormula(strFormula);
							cell.setCellStyle(csCamposDetalleImporte);
							
							yaExisteColumna = true;
							yaExisteFila = true;
							errorByCampo = false;
							
						}//END 68 PRECIO PAGADO POR TONELADA ($)
						
					}//End GRUPO 12
					
					if(l.getIdGrupo().intValue() == 13){	// GRUPO 13	NÚMERO DE PROD. BENEF.
						System.out.println("Grupo 13");
						if(l.getIdCampo().intValue() == 30){  // CAMPO 30 NÚMERO DE PROD. BENEF.
							System.out.println("Campo 30");
							if(j==1){
								tmpCountRow = countRow;
							}else{
								tmpCountRow = ultimaFilaByProductor; 
							}	
							if(yaExisteColumna){
								 ++countColumn;
							}							
							columnaAsignada = countColumn;
							for(int i=0; i<maxFilasCompras; i++){
								if(yaExisteFila){
									row = sheet.getRow(++tmpCountRow); 
								}else{
									row = sheet.createRow(++tmpCountRow);
								}
								if(sheet.isRowBroken((tmpCountRow)+(era.getOpcion() == 1?0:6))){
									tmpCountRow += totalFilasEncabezadoYCabecera;
									row = sheet.getRow(tmpCountRow);
								}else{
									tmpCountRow =romperPagina(tmpCountRow, columnaAsignada);
									
								}
								
								if(!bandProdBen && productor.getNumProdBen()!=null){
									cell = row.createCell(countColumn);
									cell.setCellValue(productor.getNumProdBen());
									cell.setCellStyle(csCamposDetalleNumero);
									bandProdBen = true;
								}else{
									cell = row.createCell(countColumn);
									cell.setCellValue("");
									cell.setCellStyle(csCamposDetalle);
								}
																	
							}
							yaExisteColumna = true;
							yaExisteFila = true;
						}//END 30 NÚMERO DE PROD. BENEF.				
							
					
					}//End GRUPO 13 NÚMERO DE PROD. BENEF.
					
				
		
			}// end GruposCamposRelacionV	
		
			j++;
			ultimaFilaByProductor = tmpCountRow;
			yaExisteColumna = false;
			yaExisteFila = false;		
		}//End productores
		
		countRow = ultimaFilaByProductor;		
		ultimaFilaDetalle = countRow+1;
		System.out.println("ultimaFilaDetalle "+ultimaFilaDetalle);
	}
	
	private int romperPagina(int tmpCountRow, int columnaAsignada) throws JDBCException, Exception {

		if(numeroPagina == 1){
			numeroRegistroPorPagina = 40;  
		}else{
			numeroRegistroPorPagina = 40; 
		}								
		if((numeroRegistroPorPagina* numeroPagina) == tmpCountRow+(era.getOpcion() == 1?0:6)){
			sheet.setRowBreak(tmpCountRow+(era.getOpcion()==1?0:6));
			System.out.println("se roompio pagin"+ tmpCountRow);
			if(era.getOpcion() == 2){
				tmpCountRow = mostrarAuditor(tmpCountRow);
			}
			row = sheet.createRow(++tmpCountRow);
			countRow = tmpCountRow;
			System.out.println("countRow antes del encabezado "+countRow);
			crearEncabezado();   
			System.out.println("Despues del encabezado"+countRow);
			tmpCountRow = countRow;
			System.out.println("tmpCountRow despues encabezado"+tmpCountRow);
			row = sheet.createRow(++tmpCountRow);
			cell = row.createCell(countColumn);
			cell.setCellValue("");
			countRow = tmpCountRow;
			System.out.println();
			crearCabeceraGrupoDetalle();
		    crearCabeceraCampoDetalle();
		    countColumn = columnaAsignada; 
		    tmpCountRow = countRow;
		    
		    row = sheet.createRow(++tmpCountRow);
		   
		}		
		 System.out.println("Despues de cabeceras"+tmpCountRow);
		return tmpCountRow;
		
	}

	private int recuperarMaxCampoCompras(long idCompPer, long idCompProd) {
		int maxCampo = 0;
		List<ComprasMaxCamposV> lstComprasMaxCamposV = rDAO.consultaComprasMaxCamposV(idCompPer, idCompProd);
		if(lstComprasMaxCamposV.size()>0){
			ComprasMaxCamposV c = lstComprasMaxCamposV.get(0);
			String cadena = c.getComprasContrato()+","+c.getComprasEntradaBodega()+","+ 
							c.getComprasFacVenta()+","+c.getComprasFacVentaGlobal()+","+
							c.getComprasPagoProdAxc()+","+c.getComprasPagoProdSinAxc()+","+c.getComprasPredio();
			String [] arrayMax = cadena.split(",");
			maxCampo = Integer.parseInt(arrayMax[0]);
			int elemento =0;
			for(int i = 1; i < arrayMax.length; i++){
				elemento = Integer.parseInt(arrayMax[i]);
				 if(elemento > maxCampo){  
					 maxCampo = elemento;  
				 }
			}
		}
		return maxCampo;
	}//end metodo recuperarMaxCampoCompras
	
	private void crearCabeceraGrupoDetalle() throws ParseException{
		countColumn=0;	
		int cuentaGruposVisibles = 0;
		if(lstCabeceraGrupoDetalle == null){
			lstCabeceraGrupoDetalle = rDAO.consultaGrupoPorRelacion(idPerRel);
		}		
		
		for(GrupoPorRelacion l:lstCabeceraGrupoDetalle){	
			if(l.getVisible()!=null){
				if(l.getVisible()){
					cuentaGruposVisibles += 1;
				}				
			}
		}
			
		countRow +=1;
		row = sheet.createRow(++countRow);
		int numeroCampos=0;
		int  lastColumn = 0;
		if(cuentaGruposVisibles!=0){
			for(GrupoPorRelacion l:lstCabeceraGrupoDetalle){			
				countColumn = countColumn+numeroCampos;
				lastColumn = countColumn+(l.getNumeroCampos()-1);
				numeroCampos = l.getNumeroCampos().intValue();
				String valor = "";
				valor = l.getGrupo();
				cell = row.createCell(countColumn);
				cell.setCellValue(valor);
				cell.setCellStyle(csCamposGrupoEncDetalle);
				if(l.getVisible()!= null){
					if(l.getVisible()){
						addMergedRegionCeldas((countRow+1), (countRow+1), countColumn, lastColumn, true, true, true, true);
					}else{
						addMergedRegionCeldas((countRow+1), (countRow+2), countColumn, lastColumn, true, true, true, true);
					}
				}
			}
		}
		
		if(numeroPagina == 1){
			totalFilasEncabezadoYCabecera++;
		}
		
	}
		
	private void crearCabeceraCampoDetalle() throws ParseException{
		if(lstGruposCamposDetalleRelacionV == null){
			lstGruposCamposDetalleRelacionV = rDAO.consultaGruposCampostV(0, 0, idPerRel, 0, 0, "DET");
		}	
		row = sheet.createRow(++countRow);
		countColumn=0;	
		configTotales = new HashMap<Integer, String>();		
		Cell cell = null;
		//int i = 0;
		for (GruposCamposRelacionV l : lstGruposCamposDetalleRelacionV){
			cell = row.createCell(countColumn++);
			cell.setCellValue(l.getCampo());
			cell.setCellStyle(csCamposEncDetalle);			
			/*if(l.getTipoDato().equals("importe") || l.getTipoDato().equals("numero") || l.getTipoDato().equals("volumen")){
				sheet.autoSizeColumn(i);
			}*/			
			if (l.getTipoDato().equals("importe")
					|| l.getTipoDato().equals("numero")
					|| l.getTipoDato().equals("volumen")){
				configTotales.put(countColumn - 1, l.getTipoDato());
			}
			//i++;
		}
		if(numeroPagina == 1){
			totalFilasEncabezadoYCabecera++;
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
			cellStyle.setFont(font6Bold);
			
		}
	}
		
	private int  mostrarAuditor(int fila) throws ParseException, IOException {
		fila +=3;
		System.out.println("fila"+fila);
		if(era.getOpcion() == 2){
			row = sheet.createRow(++fila);
			cell = row.createCell(1);
			cell.setCellValue(era.getAuditor().getNombre());
			cell.setCellStyle(csAuditor);
			addMergedRegionCeldas((fila+1),(fila+1), 1, 2, true, false, false, false);
			row = sheet.createRow(++fila);
			cell = row.createCell(1);
			cell.setCellValue("NOMBRE Y FIRMA DEL AUDITOR EXTERNO");
			cell.setCellStyle(csAuditor);
			addMergedRegionCeldas((fila+1),(fila+1),1, 2, false, false, false, false);
			row = sheet.createRow(++fila);
			cell = row.createCell(1);
			cell.setCellValue("N° DE REGISTRO ANTE LA SHCP");
			cell.setCellStyle(csAuditor);
			addMergedRegionCeldas((fila+1),(fila+1),1, 2, false, false, false, false);
		}	
		return fila;
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
	
	private void addMergedRegionCeldas(int firstFila, int lastFila, int firstColumn, int lastColumn, boolean borderTop,
		 boolean borderRight, boolean borderBottom, boolean borderLeft){			
		CellRangeAddress region = CellRangeAddress.valueOf(CellReference.convertNumToColString(firstColumn)+firstFila+":"+CellReference.convertNumToColString(lastColumn)+lastFila+"");
		sheet.addMergedRegion(region);
		if(borderTop){
			RegionUtil.setBorderTop(CellStyle.BORDER_THIN,region, sheet, wb);
		}		
		if(borderRight){
			RegionUtil.setBorderRight(CellStyle.BORDER_THIN,region, sheet, wb);
		}
		if(borderBottom){
			RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region, sheet, wb);
		}		
		if(borderLeft){
			RegionUtil.setBorderLeft(CellStyle.BORDER_THIN,region, sheet, wb );
		}
	}
	
	private void setMargenSheet(){
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		sheet.setMargin(Sheet.LeftMargin, 0.25);
		sheet.setMargin(Sheet.RightMargin, 0.25);
		sheet.setMargin(Sheet.TopMargin, 0.75);
		sheet.setMargin(Sheet.BottomMargin, 0.75);
	    sheet.getPrintSetup().setLandscape(true);
        sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.LEGAL_PAPERSIZE);
 
        
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
		footer.setRight("Page " + HeaderFooter.page() + " of "
				+ HeaderFooter.numPages());
	}
	
	private void setCellStyles(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();
		DataFormat formatImporte = wb.createDataFormat();
		DataFormat formatVolumen = wb.createDataFormat();
		
		font8Bold = wb.createFont();
		font8Bold.setFontHeightInPoints((short) 8);
		font8Bold.setFontName("Arial Narrow");
		font8Bold.setBoldweight(Font.BOLDWEIGHT_BOLD); 
		
		font6Bold = wb.createFont();
		font6Bold.setFontHeightInPoints((short) 6);
		font6Bold.setFontName("Arial Narrow");
		font6Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		Font font7Normal = wb.createFont();
		font7Normal.setFontHeightInPoints((short) 7);
		font7Normal.setFontName("Arial Narrow");
				
		Font font7NormalRed = wb.createFont();
		font7NormalRed.setFontHeightInPoints((short) 7);
		font7NormalRed.setFontName("Arial Narrow");
		font7NormalRed.setColor(Font.COLOR_RED);
	
		
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
		csCamposEncabezado.setFont(font6Bold);

		csPrograma = wb.createCellStyle();
		csPrograma.setAlignment(CellStyle.ALIGN_CENTER);
		csPrograma.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csPrograma.setWrapText(true);
		csPrograma.setFont(font6Bold);
		
		// Campos Encabezado Detalle
		csCamposEncDetalle = wb.createCellStyle();
		csCamposEncDetalle.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposEncDetalle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		csCamposEncDetalle.setWrapText(true);
		csCamposEncDetalle.setFont(font6Bold);
		csCamposEncDetalle.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposEncDetalle.setBorderTop(CellStyle.BORDER_THIN);
		csCamposEncDetalle.setBorderRight(CellStyle.BORDER_THIN);
		csCamposEncDetalle.setBorderBottom(CellStyle.BORDER_THIN);
		
		
		// Campos Grupo Encabezado Detalle
		csCamposGrupoEncDetalle = wb.createCellStyle();
		csCamposGrupoEncDetalle.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposGrupoEncDetalle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		csCamposGrupoEncDetalle.setWrapText(true);
		csCamposGrupoEncDetalle.setFont(font6Bold);
		//Tipos estilos campos detalle Fecha
		csCamposDetalleFecha = wb.createCellStyle();
		csCamposDetalleFecha.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yy"));
		csCamposDetalleFecha.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleFecha.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleFecha.setFont(font7Normal);
		csCamposDetalleFecha.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleFecha.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleFecha.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleFecha.setBorderBottom(CellStyle.BORDER_THIN);
		//Tipos estilos campos detalle importe
		csCamposDetalleImporte = wb.createCellStyle();
		csCamposDetalleImporte.setDataFormat(formatImporte.getFormat("###,###,##0.00"));
		csCamposDetalleImporte.setAlignment(CellStyle.ALIGN_RIGHT);
		csCamposDetalleImporte.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleImporte.setFont(font7Normal);
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
		csCamposDetalleVolumen.setFont(font7Normal);
		csCamposDetalleVolumen.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setBorderBottom(CellStyle.BORDER_THIN);
		csCamposDetalleVolumen.setWrapText(true);
		
		//Tipos estilos campos detalle numero
		csCamposDetalleNumero = wb.createCellStyle();
		csCamposDetalleNumero.setAlignment(CellStyle.ALIGN_RIGHT);
		csCamposDetalleNumero.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleNumero.setFont(font7Normal);
		csCamposDetalleNumero.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleNumero.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleNumero.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleNumero.setBorderBottom(CellStyle.BORDER_THIN);
		csCamposDetalleNumero.setWrapText(true);
		//Campos Detalle Default
		csCamposDetalle = wb.createCellStyle();
		csCamposDetalle.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalle.setFont(font7Normal);
		csCamposDetalle.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalle.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalle.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalle.setBorderBottom(CellStyle.BORDER_THIN);
		
		csCamposDetalleRed = wb.createCellStyle();
		csCamposDetalleRed.setAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleRed.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		csCamposDetalleRed.setFont(font7NormalRed);
		csCamposDetalleRed.setBorderLeft(CellStyle.BORDER_THIN);
		csCamposDetalleRed.setBorderTop(CellStyle.BORDER_THIN);
		csCamposDetalleRed.setBorderRight(CellStyle.BORDER_THIN);
		csCamposDetalleRed.setBorderBottom(CellStyle.BORDER_THIN);
				
		//Campo leyenda
		csLeyenda = wb.createCellStyle();
		csLeyenda.setAlignment(CellStyle.ALIGN_LEFT);
		csLeyenda.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
		csLeyenda.setFont(font7Normal);
		//Nombre auditor
		csAuditor = wb.createCellStyle();
		csAuditor.setAlignment(CellStyle.ALIGN_CENTER);
		csAuditor.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		csAuditor.setWrapText(true);
		csAuditor.setFont(font8Bold);
	}
	
}
