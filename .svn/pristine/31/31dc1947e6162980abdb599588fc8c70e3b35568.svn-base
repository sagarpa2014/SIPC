package mx.gob.comer.sipc.dao;

import java.util.List;

import mx.gob.comer.sipc.domain.CuentaBancaria;
import mx.gob.comer.sipc.domain.EstatusPago;
import mx.gob.comer.sipc.domain.OficioPagos;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.PagosDetalle;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Especialista;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.CartaAdhesionEtapaVolImpV;
import mx.gob.comer.sipc.vistas.domain.OficioPagosV;
import mx.gob.comer.sipc.vistas.domain.PagosCartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleCAV;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class PagosDAO {

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public PagosDAO(){
		
	}
	
	public PagosDAO(Session session) {
		this.session = session;
	}

	public List<PagosV> consultaPagosV(long idPago) throws JDBCException {
		return consultaPagosV(idPago,-1,-1,null,null);
	}
	
	public List<PagosV> consultaPagosV(long idPago, int idPrograma, int idComprador, String fechaInicio, String fechaFin) throws JDBCException {
		return consultaPagosV(idPago,idPrograma,idComprador,fechaInicio,fechaFin,-1);
	}
	
	public List<PagosV> consultaPagosV(long idPago, int idPrograma, int idComprador, String fechaInicio, String fechaFin, int estatus) throws JDBCException {
		return consultaPagosV(idPago,idPrograma,idComprador,fechaInicio,fechaFin, estatus, 0,0, null);
	}

	public List<PagosV> consultaPagosV(long idPago, int idPrograma, int idComprador, String fechaInicio, String fechaFin, int estatus, String carta) throws JDBCException {
		return consultaPagosV(idPago,idPrograma,idComprador,fechaInicio,fechaFin, estatus, 0,0, carta);
	}

	public List<PagosV> consultaPagosV(long idOficio, int estatus) throws JDBCException {
			return consultaPagosV(0,0,0,"","",estatus, idOficio,0, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<PagosV> consultaPagosV(long idPago, int idPrograma, int idComprador, String fechaInicio, String fechaFin, int estatus, long idOficio, long idEspecialista, String carta) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PagosV> lst=null;
		if (idComprador != 0 && idComprador != -1){
			consulta.append("where idComprador = ").append(idComprador);
		}
		
		if (estatus != 0 && estatus != -1){
			if(consulta.length()>0){
				consulta.append(" and estatus=").append(estatus);
			}else{
				consulta.append("where estatus=").append(estatus);
			}
		}
		
		if (idPago != 0 && idPago != -1){
			if(consulta.length()>0){
				consulta.append(" and idPago=").append(idPago);
			}else{
				consulta.append("where idPago=").append(idPago);
			}
		}
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fechaCreacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fechaCreacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fechaCreacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fechaCreacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		
		if (idOficio != 0 && idOficio != -1){
			if(consulta.length()>0){
				consulta.append(" and idOficio=").append(idOficio);
			}else{
				consulta.append("where idOficio=").append(idOficio);
			}
		}
		
		if (idEspecialista != 0 && idEspecialista != -1){
			if(consulta.length()>0){
				consulta.append(" and idEspecialista=").append(idEspecialista);
			}else{
				consulta.append("where idEspecialista=").append(idEspecialista);
			}
		}

		if(carta != null && !carta.equals("") && !carta.equals("-1")){
			if(consulta.length()>0){
				consulta.append(" and noCarta='").append(carta).append("'");
			}else{
				consulta.append("where noCarta='").append(carta).append("'");
			}			
		}

		consulta.insert(0, "From PagosV ").append(" ORDER BY noCarta,nombrePgrCorto, nombreComprador ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<PagosDetalleV> consultaPagosDetalleV(long idPagoDetalle, long idPago) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PagosDetalleV> lst=null;
		if (idPagoDetalle != 0 && idPagoDetalle != -1){
			consulta.append("where idPagoDetalle = ").append(idPagoDetalle);
		}
		
		if (idPago != 0 && idPago != -1){
			if(consulta.length()>0){
				consulta.append(" and idPago=").append(idPago);
			}else{
				consulta.append("where idPago=").append(idPago);
			}
		}
		
		consulta.insert(0, "From PagosDetalleV ").append(" ORDER BY estado ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PagosDetalle> consultaPagosDetalle(long idPagoDetalle, long idPago) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PagosDetalle> lst=null;
		if (idPagoDetalle != 0 && idPagoDetalle != -1){
			consulta.append("where idPagoDetalle = ").append(idPagoDetalle);
		}
		if (idPago != 0 && idPago != -1){
			if(consulta.length()>0){
				consulta.append(" and idPago=").append(idPago);
			}else{
				consulta.append("where idPago=").append(idPago);
			}
		}
		consulta.insert(0, "From PagosDetalle ").append(" ORDER BY idPago, idEstado, idCultivo, idVariedad, etapa ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	@SuppressWarnings("unchecked")
	public List<OficioPagos> consultaOficiosPago(long idOficioPagos, String noOficio, int folioCLC) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<OficioPagos> lst=null;
		if (idOficioPagos != 0 && idOficioPagos != -1){
			consulta.append("where idOficioPagos = ").append(idOficioPagos);
		}
		
		if (noOficio != null && !noOficio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and noOficio='").append(noOficio).append("'");
			}else{
				consulta.append("where noOficio='").append(noOficio).append("'");
			}
		}
		if (folioCLC!= 0 && folioCLC!=-1){
			if(consulta.length()>0){
				consulta.append(" and folioCLC=").append(folioCLC);
			}else{
				consulta.append("where folioCLC=").append(folioCLC);
			}
		}
		consulta.insert(0, "From OficioPagos ").append(" ORDER BY noOficio");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OficioPagosV> consultaOficiosPagoV(long idOficioPagos, String noOficio, int folioCLC) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<OficioPagosV> lst=null;
		if (idOficioPagos != 0 && idOficioPagos != -1){
			consulta.append("where idOficioPagos = ").append(idOficioPagos);
		}
		
		if (noOficio != null && !noOficio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and noOficio='").append(noOficio).append("'");
			}else{
				consulta.append("where noOficio='").append(noOficio).append("'");
			}
		}
		if (folioCLC!= 0 && folioCLC!=-1){
			if(consulta.length()>0){
				consulta.append(" and folioCLC=").append(folioCLC);
			}else{
				consulta.append("where folioCLC=").append(folioCLC);
			}
		}
		consulta.insert(0, "From OficioPagosV ").append(" ORDER BY noOficio");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	public long guardaOficioPagos(OficioPagos o) throws  Exception, JDBCException{
		 try {
			session.save(o);
			session.flush();
			session.clear();				
		 } catch( Exception e ){
	        transaction.rollback();
	        throw e; 
	     } 
		return o.getIdOficioPagos();
					
	}
	
	public int actualizaPagosPorOficio(String idPagos, long idOficio, int estatus) throws Exception{
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder();
			hql.append("update Pagos set idOficio=").append(idOficio)
				.append(", estatus=2 ")
				.append(" where idPago in(").append(idPagos).append(")");
			updatedEntities = session.createQuery(hql.toString()).executeUpdate();
		}catch (Exception e){
			transaction.rollback();
			throw e; 
		}
		return updatedEntities;
	}
	
	
	@SuppressWarnings("unchecked")
	public Double getSumaImportePagos(String idPagos) throws Exception{
		double sumaImportes = 0;
		try{
			StringBuilder hql = new StringBuilder().append("select new java.lang.Double( sum(importe) ) ")
					.append("from Pagos ").append("where ").append("idPago in(").append(idPagos).append(")");
			List<Double> lst = (List<Double>) session.createQuery(hql.toString()).list();
			if (lst != null && lst.size() > 0){
				sumaImportes = lst.get(0);
			}
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		return sumaImportes;
	}

	@SuppressWarnings("unchecked")
	public Double getSumaVolumenPagos(String idPagos) throws Exception{
		double sumaImportes = 0;
		try{
			StringBuilder hql = new StringBuilder().append("select new java.lang.Double( sum(volumen) ) ")
					.append("from Pagos ").append("where ").append("idPago in(").append(idPagos).append(")");
			List<Double> lst = (List<Double>) session.createQuery(hql.toString()).list();
			if (lst != null && lst.size() > 0){
				sumaImportes = lst.get(0);
			}
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		return sumaImportes;
	}
	
	public Pagos getPagos(Long idPago){
		  Pagos lst=null;	  
		  try {
			  lst = (Pagos) session.createQuery("from Pagos where idPago = "+idPago+" order by idPago").list().get(0);
			  if(lst!=null){
				  return lst;
			  }
			  return null;
		  }catch(JDBCException e){
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:getPagos:JDBCE: "+e.getMessage() );
			}catch (HibernateException e) {
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:getPagos:HE: "+e.getMessage() );
			}
		  
		   return lst;  
	  } // end getPagos() 
	
	public void borraDetallePagos(long idPago) throws  JDBCException {
		String hql = "delete from  pagos_detalle where id_pago = " + idPago;
		int registros = session.createSQLQuery(hql).executeUpdate();
		AppLogger.info("Se borraron "+ registros+" al actualizar el pago "+ idPago);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pagos> consultaPagos(long idPago) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Pagos> lst=null;
		if (idPago != 0 && idPago != -1){
			consulta.append("where idPago = ").append(idPago);
		}
		consulta.insert(0, "From Pagos ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	@SuppressWarnings("unchecked")
	public PagosV validaExisteCveRastreo(String clave)throws Exception{
		  try {
			  List<PagosV> lst=null;
			  lst = session.createQuery("from PagosV where claveRastreo = '"+clave+"' and estatus = 4").list();
			  if(lst!=null && lst.size()>0){
				  return lst.get(0);
			  }
			  return null;
		  }catch(JDBCException e){
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:validaExisteCveRastreo:Codigo error:" +  e.getErrorCode() );
				AppLogger.error( "pagos", "Mensaje:" +  e.getMessage()  );
				AppLogger.error( "pagos", "SQL:" +  e.getSQL() );
				AppLogger.error( "pagos", "Causa:"+e.getCause().getMessage());
				throw e;
		  }
	  } // end validaExisteCveRastreo()
	
	public CuentaBancaria getCuentaBancaria(String clabe){
		CuentaBancaria lst=null;	  
		  try {
			  lst = (CuentaBancaria) session.createQuery("from CuentaBancaria where clabe = '"+clabe+"'").list().get(0);
			  if(lst!=null){
				  return lst;
			  }
			  return null;
		  }catch(JDBCException e){
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:getCuentaBancaria:JDBCE: "+e.getMessage() );
			}catch (HibernateException e) {
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:getCuentaBancaria:HE: "+e.getMessage() );
			}
		  
		   return lst;  
	} // end getCuentaBancaria() 

	public OficioPagos getOficioPagos(Long idOficio){
		OficioPagos lst=null;	  
		  try {
			  lst = (OficioPagos) session.createQuery("from OficioPagos where idOficioPagos = "+idOficio).list().get(0);
			  if(lst!=null){
				  return lst;
			  }
			  return null;
		  }catch(JDBCException e){
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:getOficioPagos:JDBCE: "+e.getMessage() );
		  }catch (HibernateException e) {
				e.printStackTrace();
				AppLogger.error( "pagos", "PagosDAO:getOficioPagos:HE: "+e.getMessage() );
		  }
		  
		  return lst;  
	} // end getOficioPagos() 

	@SuppressWarnings("unchecked")
	public List<PagosDetalleV> consultaPagosDetalleVSession(long idPagoDetalle, long idPago) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PagosDetalleV> lst=null;
		try{
			session = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			transaction = session.beginTransaction();

			if (idPagoDetalle != 0 && idPagoDetalle != -1){
				consulta.append("where idPagoDetalle = ").append(idPagoDetalle);
			}
			
			if (idPago != 0 && idPago != -1){
				if(consulta.length()>0){
					consulta.append(" and idPago=").append(idPago);
				}else{
					consulta.append("where idPago=").append(idPago);
				}
			}
			
			consulta.insert(0, "From PagosDetalleV ").append(" ORDER BY estado ");
			lst= session.createQuery(consulta.toString()).list();	
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}
		}

		return lst;
	}
	
	public List<Pagos> consultaPagos(long idPago, String claveRastreo,String descRechazo, int estatus) throws JDBCException {
		return consultaPagos(idPago, claveRastreo, descRechazo, estatus, null);		
	}

	@SuppressWarnings("unchecked")
	public List<Pagos> consultaPagos(long idPago, String claveRastreo,String descRechazo, int estatus, String atentaNota) throws JDBCException {
		
		StringBuilder consulta= new StringBuilder();
		List<Pagos> lst=null;
		if (idPago != 0 && idPago != -1){
			consulta.append("where idPago = ").append(idPago);
		}
		
		if (claveRastreo != null && !claveRastreo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveRastreo='").append(claveRastreo).append("'");
			}else{
				consulta.append("where claveRastreo='").append(claveRastreo).append("'");
			}
		}
		if (estatus!= 0 && estatus!=-1){
			if(consulta.length()>0){
				consulta.append(" and estatus=").append(estatus);
			}else{
				consulta.append("where estatus=").append(estatus);
			}
		}
		if (descRechazo != null && !descRechazo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and descRechazo='").append(claveRastreo).append("'");
			}else{
				consulta.append("where descRechazo='").append(claveRastreo).append("'");
			}
		}
		if (atentaNota != null && !atentaNota.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and atentaNota='").append(atentaNota).append("'");
			}else{
				consulta.append("where atentaNota='").append(atentaNota).append("'");
			}
		}
		consulta.insert(0, "From Pagos ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<Especialista> consultaEspecialistaPagos(int idEspecialista) throws JDBCException {
		List<Especialista> lst=null;
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		subConsulta.append("select distinct id_especialista from pagos_v where id_especialista is not null");
		consulta.append("select * from especialistas where id_especialista in (").append(subConsulta.toString()).append(")");
		lst = session.createSQLQuery(consulta.toString()).addEntity(Especialista.class).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Programa> consultaProgByEspecialista(long idEspecialista) throws JDBCException {
		List<Programa> lst=null;
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		if (idEspecialista != 0 && idEspecialista != -1){
			subConsulta.append(" where id_especialista = ").append(idEspecialista);
		}
		
		subConsulta.insert(0, "select distinct id_programa from pagos_v ");
		consulta.append("select * from programas where id_programa in (").append(subConsulta.toString()).append(")");
		lst = session.createSQLQuery(consulta.toString()).addEntity(Programa.class).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<EstatusPago> consultaEstatusPago(long estatusId) throws JDBCException {
		List<EstatusPago> lst=null;
		StringBuilder consulta= new StringBuilder();
		if (estatusId != 0 && estatusId != -1){
			consulta.append(" where estatusId in ").append(estatusId);
		}
		
		
		consulta.insert(0, "From EstatusPago ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}	
	
	@SuppressWarnings("unchecked")
	public List<CartaAdhesionEtapaVolImpV> consultaVolImpCartaAdhesionEtapa(String cartaAdhesion, Integer idEtapa) throws JDBCException {
		
		StringBuilder consulta= new StringBuilder();
		List<CartaAdhesionEtapaVolImpV> lst=null;
		if (cartaAdhesion != null && cartaAdhesion != ""){
			consulta.append("where folioCartaAdhesion = '").append(cartaAdhesion).append("'");
		}
		
		if (idEtapa!= 0 && idEtapa!=-1){
			if(consulta.length()>0){
				consulta.append(" and idEtapa=").append(idEtapa);
			}else{
				consulta.append("where idEtapa=").append(idEtapa);
			}
		}
		consulta.insert(0, "From CartaAdhesionEtapaVolImpV ").append(" ORDER BY folioCartaAdhesion, idEtapa ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public Double getSumaImporteApoyadoCA(String folioCartaAdhesion) throws Exception{
		double sumaImportes = 0;
		try{
			StringBuilder hql = new StringBuilder().append("select new java.lang.Double( sum(importe) ) ")
					.append("from Pagos ").append("where ").append("noCarta = '").append(folioCartaAdhesion).append("' and estatus not in (8, 3, 6)");
			List<Double> lst = (List<Double>) session.createQuery(hql.toString()).list();
			if (lst != null && lst.size() > 0){
				sumaImportes = lst.get(0);
			}
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		return sumaImportes;
	}

	@SuppressWarnings("unchecked")
	public Double getSumaVolumenApoyadoCA(String folioCartaAdhesion) throws Exception{
		double sumaVolumenes = 0;
		try{
			StringBuilder hql = new StringBuilder().append("select new java.lang.Double( sum(volumen) ) ")
					.append("from Pagos ").append("where ").append("noCarta = '").append(folioCartaAdhesion).append("' and estatus not in (8, 3, 6)");
			List<Double> lst = (List<Double>) session.createQuery(hql.toString()).list();
			if (lst != null && lst.size() > 0){
				sumaVolumenes = lst.get(0);
			}
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		return sumaVolumenes;
	}

	@SuppressWarnings("unchecked")
	public List<PagosCartasAdhesionV> consultaPagosEdoCulVarCA(String folioCartaAdhesion, Integer idEstado, Integer idCultivo, Integer idVariedad) throws JDBCException {
		
		StringBuilder consulta= new StringBuilder();
		List<PagosCartasAdhesionV> lst=null;
		if (folioCartaAdhesion != null && folioCartaAdhesion != ""){
			consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
		}
		
		if (idEstado!= 0 && idEstado!=-1){
			if(consulta.length()>0){
				consulta.append(" and idEstado=").append(idEstado);
			}else{
				consulta.append("where idEstado=").append(idEstado);
			}
		}
		if (idCultivo!= 0 && idCultivo!=-1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo=").append(idCultivo);
			}else{
				consulta.append("where idCultivo=").append(idCultivo);
			}
		}
		if (idVariedad!= 0 && idVariedad!=-1){
			if(consulta.length()>0){
				consulta.append(" and idVariedad=").append(idVariedad);
			}else{
				consulta.append("where idVariedad=").append(idVariedad);
			}
		}

		consulta.insert(0, "From PagosCartasAdhesionV ").append(" ORDER BY folioCartaAdhesion, idEstado, idCultivo, idVariedad ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<PagosDetalleCAV> consultaPagosDetalleCAV(String folioCartaAdhesion, long idPago, Integer idEstado, Integer idCultivo, Integer idVariedad, Integer idEtapa) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PagosDetalleCAV> lst=null;
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
		}				
		if (idPago != 0 && idPago != -1){
			if(consulta.length()>0){
				consulta.append(" and idPago=").append(idPago);
			}else{
				consulta.append("where idPago=").append(idPago);
			}
		}
		if (idEstado!= 0 && idEstado!=-1){
			if(consulta.length()>0){
				consulta.append(" and idEstado=").append(idEstado);
			}else{
				consulta.append("where idEstado=").append(idEstado);
			}
		}
		if (idCultivo!= 0 && idCultivo!=-1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo=").append(idCultivo);
			}else{
				consulta.append("where idCultivo=").append(idCultivo);
			}
		}
		if (idVariedad!= 0 && idVariedad!=-1){
			if(consulta.length()>0){
				consulta.append(" and idVariedad=").append(idVariedad);
			}else{
				consulta.append("where idVariedad=").append(idVariedad);
			}
		}
		if (idEtapa!= 0 && idEtapa!=-1){
			if(consulta.length()>0){
				consulta.append(" and idEtapa=").append(idEtapa);
			}else{
				consulta.append("where idEtapa=").append(idEtapa);
			}
		}		
		consulta.insert(0, "From PagosDetalleCAV ").append(" ORDER BY folioCartaAdhesion, idEstado, idCultivo, idVariedad, idEtapa ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pagos> consultaPagosOficio(long idOficioPagos) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Pagos> lst=null;
		if (idOficioPagos != 0 && idOficioPagos != -1){
			consulta.append("where idOficio = ").append(idOficioPagos);
		}
		consulta.insert(0, "From Pagos ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	
	
}// fin de clase
