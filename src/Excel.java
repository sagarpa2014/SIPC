import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class Excel {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WriteException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		String cadena="Programa,Participante,Carta";
		String p="0,1,2";
		String [] c =cadena.split(",");
		String [] detalle =p.split(",");
		String filename = "c:/SIPC/lars.xls";
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(new File(filename));
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			WritableCellFormat cf1 = new WritableCellFormat(wf1);
			cf1.setAlignment(Alignment.CENTRE);
			cf1.setWrap(true);
			int col =0;
			for(int i=0; i<c.length; i++){
				sheet.addCell(new Label(i, 0, c[i], cf1)); /*1 Columna, 2 Fila*/
			}
			
			for(int i=1; i<=5; i++){
				for(int j=0; j<detalle.length; j++){
					sheet.addCell(new Label(j, i, detalle[j], cf1));
					
				}
			}
			
			/*sheet.addCell(new Label(0, 0, "hola", cf1)); /*1 Columna, 2 Fila*/
			/*sheet.addCell(new Label(1, 0, "hola1", cf1)); /*1 Columna, 2 Fila*/
			/*sheet.addCell(new Label(2, 0, "hola2", cf1)); /*1 Columna, 2 Fila*/
			/*sheet.addCell(new Label(3, 0, "hola3", cf1)); /*1 Columna, 2 Fila*/
			
			workbook.write();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally{
			if (workbook != null){
				try {
					workbook.close();
				} catch (WriteException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}



	        
	}

}
