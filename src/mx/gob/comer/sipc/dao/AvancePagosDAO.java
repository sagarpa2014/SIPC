package mx.gob.comer.sipc.dao;

import java.util.List;

import mx.gob.comer.sipc.domain.transaccionales.AvisosDof;
import mx.gob.comer.sipc.vistas.domain.AvisosDofDetalleV;
import mx.gob.comer.sipc.vistas.domain.AvisosDofV;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;



public class AvancePagosDAO {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;
	
	
	@SuppressWarnings("unchecked")
	public List<AvisosDof> getAvisosDof(String claveAcceso) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDof> lst=null;
		if (!(claveAcceso==null || claveAcceso.equals(""))){
			consulta.append("where claveAcceso = '").append(claveAcceso).append("'");
		}		
		consulta.insert(0, "From AvisosDof ").append(" ORDER BY fechaPublicacion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<AvisosDofV> getAvisosDofV(String claveAcceso) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDofV> lst=null;
		if (!(claveAcceso==null || claveAcceso.equals(""))){
			consulta.append("where claveAcceso = '").append(claveAcceso).append("'");
		}		
		consulta.insert(0, "From AvisosDofV ").append(" ORDER BY fechaPublicacion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<AvisosDofDetalleV> getAvisosDofDetalle(String claveAcceso) throws  JDBCException{
		return getAvisosDofDetalle(claveAcceso);
	}
	@SuppressWarnings("unchecked")
	public List<AvisosDofDetalleV> getAvisosDofDetalle(String claveAcceso, int programa, int idCultivo, 
			String ciclo, int ejercicio, int idEstado) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDofDetalleV> lst=null;
		if (!(claveAcceso==null || claveAcceso.equals(""))){
			consulta.append("where claveAcceso = '").append(claveAcceso).append("'");
		}		
		
		 if(programa !=0 && programa!=-1){
			  if(consulta.length()>0){
					consulta.append(" and programa =").append(programa);
				}else{
					consulta.append("where programa=").append(programa);
				}
		  }

		
		consulta.insert(0, "From AvisosDofDetalleV ").append(" ORDER BY programa, cultivo, ciclo, ejercicio, estado");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
}
