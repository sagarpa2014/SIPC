package mx.gob.comer.sipc.dao;

import java.util.Date;
import java.util.List;

import mx.gob.comer.sipc.domain.ArchivosPagos;
import mx.gob.comer.sipc.log.AppLogger;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ArchivosPagosDAO extends SqlDAO{

  @SessionTarget
  Session session;
	
  @TransactionTarget
  Transaction transaction;

  
  public ArchivosPagos getArchivoPagos(long archivoId) throws Exception{
  	   try {
  		   ArchivosPagos lst=null;
  		   StringBuilder hql = new StringBuilder();
  		   if (archivoId != 0 && archivoId != -1){
  				hql.append(" WHERE archivoId=").append(archivoId);
  		   }
  		   hql.insert(0, "From ArchivosPagos ");
  		   lst = (ArchivosPagos)session.createQuery(hql.toString()).list().get(0);	
  			
  		   return lst;
  	   }catch(JDBCException e){
  			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getArchivoPagos:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
  			throw e;
  		}catch (HibernateException e) {
  			AppLogger.error( "pagos", "PagosDAO:getArchivoPagos:HE: "+e.getMessage() );
  			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getArchivoPagos:E: "+e.getMessage() );
			throw e;
  		}  		
  } // end getArchivoPagos()

  @SuppressWarnings("unchecked")
  public ArchivosPagos getArchivoPagos(String archivoNombre) throws Exception{
	   try {
		   List<ArchivosPagos> lst=null;
		   StringBuilder hql = new StringBuilder();
		   if (archivoNombre != null && !archivoNombre.isEmpty()){
				hql.append(" WHERE nombreArchivo='").append(archivoNombre).append("'");
		   }
		   hql.insert(0, "From ArchivosPagos ");
		   lst = session.createQuery(hql.toString()).list();	
		   if(lst!=null && lst.size()>0){
			  return lst.get(0);
		   }
		   return null;
	   }catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getArchivoPagos2:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
			throw e;
		}catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:getArchivoPagos2:HE: "+e.getMessage() );
			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getArchivoPagos2:E: "+e.getMessage() );
			throw e;
		}
  } // end getArchivoPagos()
    
  public Integer getMaximoConsecutivoArchivoPagos(Integer bancoId, String fecha)throws Exception{
	  Integer lst=null;
	  try {
			lst = (Integer) session.createQuery(" select coalesce(max(consecutivoArchivo)+1,0) "+
												" from ArchivosPagos "+
												" where bancoId = "+bancoId+
												" and fechaArchivo = '"+fecha+"'").list().get(0);
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getMaximoConsecutivoArchivoPagos2:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
		}catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:getMaximoConsecutivoArchivoPagos2:HE: "+e.getMessage() );
			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getMaximoConsecutivoArchivoPagos2:E: "+e.getMessage() );
			throw e;
		}
	  
	   return lst;  
  } // end getMaximoConsecutivoArchivoPagos()

  public Integer getMaximoConsecutivoArchivoPagos(String codigoOperacion, Integer bancoId, String fecha, Integer medioPago)throws Exception{
	  Integer lst=null;
	  try {
			lst = (Integer) session.createQuery(" select coalesce(max(consecutivoArchivo)+1,0) "+
												" from ArchivosPagos "+
												" where codigoOperacion = '"+codigoOperacion+"'"+
												" and bancoId = "+bancoId+
												" and medioPago = "+medioPago+
												" and fechaArchivo = '"+fecha+"'").list().get(0);
		}catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getMaximoConsecutivoArchivoPagos:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
		}catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:getMaximoConsecutivoArchivoPagos:HE: "+e.getMessage() );
			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getMaximoConsecutivoArchivoPagos:E: "+e.getMessage() );
			throw e;
		}
	  
	   return lst;  
  } // end getMaximoConsecutivoArchivoPagos()

  public void saveArchivosPagos(ArchivosPagos archivo) throws Exception{
	   try {
		   	session.saveOrUpdate(archivo);
	   		session.flush();
	   		session.clear();
	   		session.evict(archivo);	   		
	   }catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:saveArchivosPagos:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
			throw e;
		}catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:saveArchivosPagos:HE: "+e.getMessage() );
			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:saveArchivosPagos:E: "+e.getMessage() );
			throw e;
		}

  } // end saveArchivosPagos()

  @SuppressWarnings("unchecked")
  public List<String> getArchivoEnvioIntruccionPago(long folio, String codigoOperacion, Integer medioPago, Session session)throws Exception{
	  try {
		  List<String> lst=null;
		  lst = session.createQuery(" select nombreArchivo " +
									" from ArchivosPagos "+
									" where codigoOperacion = '"+codigoOperacion+"'"+
									" and medioPago = "+medioPago+
									" and folio like '%"+folio+"%'").list();
		  if(lst!=null && lst.size()>0){
			return lst;
		  }
	  }catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getArchivoEnvioIntruccionPago:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
	  }catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:getArchivoEnvioIntruccionPago:HE: "+e.getMessage() );
			throw e;
	  }catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getArchivoEnvioIntruccionPago:E: "+e.getMessage() );
			throw e;
	  }
	  return null;
  } // end getArchivoEnvioIntruccionPago()

  public boolean validaNumArchivosBanco(Date fecha, Integer bancoId, String medioPago) {
	String query;
	boolean resp;
	int medioPagoId = 0;
	// TODO Auto-generated method stub
	if(medioPago.equals("C")){
		medioPagoId = 1;
	} else if(medioPago.equals("D")){
		medioPagoId = 2;
	} if(medioPago.equals("O")){
		medioPagoId = 3;
	}
	try {
		query = "SELECT valida_num_archivos_banco('"+fecha+"',"+bancoId+","+medioPagoId+")";
		resp = (Boolean) session.createSQLQuery(query).list().get(0);
		return resp;
	}catch(JDBCException e){
		e.printStackTrace();
		AppLogger.error("Codigo error:" +  e.getErrorCode() );
		AppLogger.error("Mensaje:" +  e.getMessage()  );
		AppLogger.error("SQL:" +  e.getSQL() );
		AppLogger.error("Causa:"+e.getCause().getMessage());
		throw e;
	}catch (HibernateException e) {
		AppLogger.error( e.getCause() );
		throw e;
	}	    
  } // end validaNumArchivosBanco()

  @SuppressWarnings("unchecked")
  public Long getSecuenciaInicialMaximoArchivo(Integer bancoId, String fecha)throws Exception{
	  try {
		  List<Long> lst=null;
		  lst = session.createQuery(	"select coalesce(secuenciaInicial,0) "+
												" from ArchivosPagos "+
												" where bancoId = "+bancoId+
												" and fechaArchivo = '"+fecha+"'"+
												" and consecutivoArchivo = "+
												" (select coalesce(max(consecutivoArchivo),0) "+
												" from ArchivosPagos "+
												" where bancoId = "+bancoId+
												" and fechaArchivo = '"+fecha+"')").list();
		  if(lst!=null && lst.size()>0){
				return lst.get(0);
		  }
	  }catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getSecuenciaInicialMaximoArchivo:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
		}catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:getSecuenciaInicialMaximoArchivo:HE: "+e.getMessage() );
			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getSecuenciaInicialMaximoArchivo:E: "+e.getMessage() );
			throw e;
		}
		return Long.valueOf(0);
  } // end getSecuenciaInicialMaximoArchivo()

  @SuppressWarnings("unchecked")
  public Long getSecuenciaFinalMaximoArchivo(Integer bancoId, String fecha)throws Exception{
	  try {
		  List<Long> lst=null;
		  lst = session.createQuery(	"select coalesce(secuenciaFinal,0) "+
												" from ArchivosPagos "+
												" where bancoId = "+bancoId+
												" and fechaArchivo = '"+fecha+"'"+
												" and consecutivoArchivo = "+
												" (select coalesce(max(consecutivoArchivo),0) "+
												" from ArchivosPagos "+
												" where bancoId = "+bancoId+
												" and fechaArchivo = '"+fecha+"')").list();
		  if(lst!=null && lst.size()>0){
				return lst.get(0);
		  }
	  }catch(JDBCException e){
			e.printStackTrace();
			AppLogger.error( "pagos", "ArchivosPagosDAO:getSecuenciaFinalMaximoArchivo:Codigo error:" +  e.getErrorCode() );
			AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
			AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
			AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
		}catch (HibernateException e) {
			AppLogger.error( "pagos", "PagosDAO:getSecuenciaFinalMaximoArchivo:HE: "+e.getMessage() );
			throw e;
		}catch (Exception e) {
			AppLogger.error( "pagos", "PagosDAO:getSecuenciaFinalMaximoArchivo:E: "+e.getMessage() );
			throw e;
		}
		return Long.valueOf(0);
  } // end getSecuenciaFinalMaximoArchivo()

  public List<ArchivosPagos> consultaArchivoPagos(int consecutivo, String fecha){
	  return consultaArchivoPagos( consecutivo,  fecha, "", -1);
  }
  public List<ArchivosPagos> consultaArchivoPagos( String anio, int folioCLC){
	  return consultaArchivoPagos( -1,"", anio, folioCLC);
  }
  /**
 * @param consecutivo
 * @param fecha
 * @return
 */
@SuppressWarnings("unchecked")
public List<ArchivosPagos> consultaArchivoPagos(int consecutivo, String fecha,  String anio, int folioCLC){
	  StringBuilder consulta= new StringBuilder();
	  List<ArchivosPagos> lst=null;
	  if (consecutivo != -1){
			consulta.append("where consecutivoArchivo = ").append(consecutivo);
	  }
	  if(fecha != null && !fecha.equals("")){
		  if(consulta.length()>0){
			  consulta.append(" and TO_CHAR(fechaArchivo,'YYYY-MM-DD')='").append(fecha).append("'");
		  }else{
			  consulta.append(" where TO_CHAR(fechaArchivo,'YYYY-MM-DD')='").append(fecha).append("'");
		  }
	  }
	  if(anio != null && !anio.equals("")){
		  if(consulta.length()>0){
			  consulta.append(" and TO_CHAR(fechaArchivo,'YYYY')='").append(anio).append("'");
		  }else{
			  consulta.append(" where TO_CHAR(fechaArchivo,'YYYY')='").append(anio).append("'");
		  }
	  }
	
	  if(folioCLC !=0 && folioCLC!=-1){
		  if(consulta.length()>0){
				consulta.append(" and folio ='").append(folioCLC).append("'");
			}else{
				consulta.append("where folio='").append(folioCLC).append("'");
			}
	  }
	  consulta.insert(0, "From ArchivosPagos ");
	  lst= session.createQuery(consulta.toString()).list();	
	  return lst;
  }
  
  
  
}//end Class ArchivosPagosDAO
