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
			consulta.append("where claveAviso = '").append(claveAcceso).append("'");
		}		
		consulta.insert(0, "From AvisosDofV ").append(" ORDER BY fechaPublicacion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<AvisosDofDetalleV> getAvisosDofDetalle(String claveAcceso) throws  JDBCException{
		return getAvisosDofDetalle(claveAcceso, 0,0,null,0,0, null);
	}
	@SuppressWarnings("unchecked")
	public List<AvisosDofDetalleV> getAvisosDofDetalle(String claveAcceso, int idProgAviso, int idCultivo, 
			String ciclo, int ejercicio, int idEstado, String cicloAgricola) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDofDetalleV> lst=null;
		if (!(claveAcceso==null || claveAcceso.equals(""))){
			consulta.append("where claveAcceso = '").append(claveAcceso).append("'");
		}		
		
		if(idProgAviso !=0 && idProgAviso!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idProgAviso =").append(idProgAviso);
				}else{
					consulta.append("where idProgAviso=").append(idProgAviso);
				}
		 }
		 
		 if(idCultivo !=0 && idCultivo!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idCultivo =").append(idCultivo);
				}else{
					consulta.append("where idCultivo=").append(idCultivo);
				} 
		 }
		 
		 if (ciclo !=  null && !ciclo.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and ciclo ='").append(ciclo).append("'");
				}else{
					consulta.append("where ciclo ='").append(ciclo).append("'");
				}			
		 }
		 
		 if (cicloAgricola !=  null && !cicloAgricola.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and cicloAgricola ='").append(ciclo).append("'");
				}else{
					consulta.append("where cicloAgricola ='").append(ciclo).append("'");
				}			
		 }
		 
		 if(ejercicio !=0 && ejercicio!=-1){
			  if(consulta.length()>0){
					consulta.append(" and ejercicio =").append(ejercicio);
				}else{
					consulta.append("where ejercicio =").append(ejercicio);
				} 
		 }
		 
		 if(idEstado !=0 && idEstado!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idEstado =").append(idEstado);
				}else{
					consulta.append("where idEstado =").append(idEstado);
				} 
		 }
		 
		
		consulta.insert(0, "From AvisosDofDetalleV ").append(" ORDER BY programa, cultivo, ciclo, ejercicio, estado");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
}
