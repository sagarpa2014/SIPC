package mx.gob.comer.sipc.dao;

import java.util.Date;
import java.util.List;
import mx.gob.comer.sipc.domain.transaccionales.SeguimientoCentroAcopio;
import mx.gob.comer.sipc.vistas.domain.OperadoresBodegasV;
import mx.gob.comer.sipc.vistas.domain.ReporteSeguimientoAcopioV;
import mx.gob.comer.sipc.vistas.domain.ResumenAvanceAcopioV;
import mx.gob.comer.sipc.vistas.domain.SeguimientoCentroAcopioV;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class SeguimientoDAO {

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public SeguimientoDAO(){
		
	}
	
	public SeguimientoDAO(Session session) {
		this.session = session;
	}

	public List<SeguimientoCentroAcopio> consultaSeguimientoCA(long idSeguimientoCA ) throws JDBCException {
		return consultaSeguimientoCA(idSeguimientoCA, -1, -1, null, null, null);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SeguimientoCentroAcopio> consultaSeguimientoCA(long idSeguimientoCA, int idCiclo, int ejercicio,  Date periodoInicial, Date periodoFinal, String claveBodega) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SeguimientoCentroAcopio> lst=null;
		if (idSeguimientoCA != 0 && idSeguimientoCA != -1){
			consulta.append("where idSeguimientoCA = ").append(idSeguimientoCA);
		}
		
		if (idCiclo != 0 && idCiclo!=-1){
			if(consulta.length()>0){
				consulta.append(" and idCiclo=").append(idCiclo);
			}else{
				consulta.append("where idCiclo=").append(idCiclo);
			}
		}
		
		if (ejercicio != 0 && ejercicio!=-1){
			if(consulta.length()>0){
				consulta.append(" and ejercicio=").append(ejercicio);
			}else{
				consulta.append("where ejercicio=").append(ejercicio);
			}
		}
		
		if (periodoInicial != null  && !periodoInicial.equals("")){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(periodoInicial,'YYYY-MM-DD')) between '").append(periodoInicial).append("'")
						.append(" and '").append(periodoInicial).append("'");
			}else{
				consulta.append(" where (TO_CHAR(periodoInicial,'YYYY-MM-DD')) between '").append(periodoInicial).append("'")
						.append(" and '").append(periodoInicial).append("'");
			}
		}
		
		if (periodoFinal != null &&  !periodoFinal.equals("")){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(periodoFinal,'YYYY-MM-DD')) between '").append(periodoFinal).append("'")
						.append(" and '").append(periodoFinal).append("'");
			}else{
				consulta.append(" where (TO_CHAR(periodoFinal,'YYYY-MM-DD')) between '").append(periodoFinal).append("'")
						.append(" and '").append(periodoFinal).append("'");
			}
		}
		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega='").append(claveBodega).append("'");
			}
		}
		
		
		consulta.insert(0, "From SeguimientoCentroAcopio ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public List<SeguimientoCentroAcopioV> consultaSeguimientoCAV(long idSeguimientoCA) throws JDBCException {
		
		return consultaSeguimientoCAV(idSeguimientoCA, -1, -1, null, null, null, 0);
	}

	public List<SeguimientoCentroAcopioV> consultaSeguimientoCAV(long idSeguimientoCA, Integer idUsuario) throws JDBCException {
		
		return consultaSeguimientoCAV(idSeguimientoCA, -1, -1, null, null, null, idUsuario);
	}

	@SuppressWarnings("unchecked")
	public List<SeguimientoCentroAcopioV> consultaSeguimientoCAV(long idSeguimientoCA, int idCiclo, int ejercicio,  Date periodoInicial, Date periodoFinal, String claveBodega, Integer idUsuario) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SeguimientoCentroAcopioV> lst=null;
		if (idSeguimientoCA != 0 && idSeguimientoCA != -1){
			consulta.append("where idSeguimientoCA = ").append(idSeguimientoCA);
		}
			
		if (idCiclo != 0 && idCiclo!=-1){
			if(consulta.length()>0){
				consulta.append(" and idCiclo=").append(idCiclo);
			}else{
				consulta.append("where idCiclo=").append(idCiclo);
			}
		}
		
		if (ejercicio != 0 && ejercicio!=-1){
			if(consulta.length()>0){
				consulta.append(" and ejercicio=").append(ejercicio);
			}else{
				consulta.append("where ejercicio=").append(ejercicio);
			}
		}
		
		if (periodoInicial != null  && !periodoInicial.equals("")){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(periodoInicial,'YYYY-MM-DD')) between '").append(periodoInicial).append("'")
						.append(" and '").append(periodoInicial).append("'");
			}else{
				consulta.append(" where (TO_CHAR(periodoInicial,'YYYY-MM-DD')) between '").append(periodoInicial).append("'")
						.append(" and '").append(periodoInicial).append("'");
			}
		}
		
		if (periodoFinal != null &&  !periodoFinal.equals("")){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(periodoFinal,'YYYY-MM-DD')) between '").append(periodoFinal).append("'")
						.append(" and '").append(periodoFinal).append("'");
			}else{
				consulta.append(" where (TO_CHAR(periodoFinal,'YYYY-MM-DD')) between '").append(periodoFinal).append("'")
						.append(" and '").append(periodoFinal).append("'");
			}
		}
		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega like '%").append(claveBodega).append("%'");
			}else{
				consulta.append("where claveBodega like '%").append(claveBodega).append("%'");
			}
		}

		if (idUsuario!=null && idUsuario != 0 && idUsuario!=-1){
			if(consulta.length()>0){
				consulta.append(" and usuarioRegistro=").append(idUsuario);
			}else{
				consulta.append("where usuarioRegistro=").append(idUsuario);
			}
		}

		consulta.append(" order by claveBodega, ciclo, periodoInicial, periodoFinal ").insert(0, "From SeguimientoCentroAcopioV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<SeguimientoCentroAcopioV> consultaSeguimientoCAV(int idEstadoSeg, int idCultivoSeg, int idCicloSeg) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SeguimientoCentroAcopioV> lst=null;

		if (idEstadoSeg != 0 && idEstadoSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstadoBodega = ").append(idEstadoSeg);
			} else {
				consulta.append(" where idEstadoBodega = ").append(idEstadoSeg);
			}
		}

		if (idCultivoSeg != 0 && idCultivoSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo = ").append(idCultivoSeg);
			} else {
				consulta.append(" where idCultivo = ").append(idCultivoSeg);
			}
		}

		if (idCicloSeg != 0 && idCicloSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idCicloSeg = ").append(idCicloSeg);
			} else {
				consulta.append(" where idCicloSeg = ").append(idCicloSeg);
			}
		}

		consulta.insert(0, "From SeguimientoCentroAcopioV ").append(" order by nombreEstadoBodega, nombreCultivo, ciclo, claveBodega, nombreComprador");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<SeguimientoCentroAcopioV> isolatedConsultaReporteDetalle(int idEstadoSeg, int idCultivoSeg, int idCicloSeg)throws  JDBCException{
		Session s = null;
		StringBuilder consulta= new StringBuilder();
		List<SeguimientoCentroAcopioV> lst = null;

		try{			
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if (idEstadoSeg != 0 && idEstadoSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idEstadoBodega = ").append(idEstadoSeg);
				} else {
					consulta.append(" where idEstadoBodega = ").append(idEstadoSeg);
				}
			}

			if (idCultivoSeg != 0 && idCultivoSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idCultivo = ").append(idCultivoSeg);
				} else {
					consulta.append(" where idCultivo = ").append(idCultivoSeg);
				}
			}

			if (idCicloSeg != 0 && idCicloSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idCicloSeg = ").append(idCicloSeg);
				} else {
					consulta.append(" where idCicloSeg = ").append(idCicloSeg);
				}
			}

			consulta.insert(0, "From SeguimientoCentroAcopioV ").append(" order by nombreEstadoBodega, nombreCultivo, ciclo, claveBodega, nombreComprador");
			lst= s.createQuery(consulta.toString()).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<OperadoresBodegasV> consultaOperadoresBodegasV(String ciclo, String claveBodega) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<OperadoresBodegasV> lst=null;
		if (ciclo != null && !ciclo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and ciclo='").append(ciclo).append("'");
			}else{
				consulta.append("where ciclo='").append(ciclo).append("'");
			}
		}
		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega='").append(claveBodega).append("'");
			}
		}
		consulta.insert(0, "From OperadoresBodegasV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReporteSeguimientoAcopioV> consultaReporteConsolidado(int idEstadoSeg, int idCultivoSeg, int idCicloSeg) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteSeguimientoAcopioV> lst = null;
		
		if (idEstadoSeg != 0 && idEstadoSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstadoBodega = ").append(idEstadoSeg);
			} else {
				consulta.append(" where idEstadoBodega = ").append(idEstadoSeg);
			}
		}

		if (idCultivoSeg != 0 && idCultivoSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo = ").append(idCultivoSeg);
			} else {
				consulta.append(" where idCultivo = ").append(idCultivoSeg);
			}
		}

		if (idCicloSeg != 0 && idCicloSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idCicloSeg = ").append(idCicloSeg);
			} else {
				consulta.append(" where idCicloSeg = ").append(idCicloSeg);
			}
		}

		consulta.insert(0, "From ReporteSeguimientoAcopioV ").append(" order by nombreEstadoBodega, nombreCultivo, ciclo, claveBodega, nombreComprador");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<ReporteSeguimientoAcopioV> isolatedConsultaReporteConsolidado(int idEstadoSeg, int idCultivoSeg, int idCicloSeg)throws  JDBCException{
		Session s = null;
		StringBuilder consulta= new StringBuilder();
		List<ReporteSeguimientoAcopioV> lst = null;

		try{			
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if (idEstadoSeg != 0 && idEstadoSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idEstadoBodega = ").append(idEstadoSeg);
				} else {
					consulta.append(" where idEstadoBodega = ").append(idEstadoSeg);
				}
			}

			if (idCultivoSeg != 0 && idCultivoSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idCultivo = ").append(idCultivoSeg);
				} else {
					consulta.append(" where idCultivo = ").append(idCultivoSeg);
				}
			}

			if (idCicloSeg != 0 && idCicloSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idCicloSeg = ").append(idCicloSeg);
				} else {
					consulta.append(" where idCicloSeg = ").append(idCicloSeg);
				}
			}

			consulta.insert(0, "From ReporteSeguimientoAcopioV ").append(" order by nombreEstadoBodega, nombreCultivo, ciclo, claveBodega, nombreComprador");
			lst= s.createQuery(consulta.toString()).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}
		return lst;
	}

	public Double obtieneAcopioBodega(String claveBodega, int idCiclo, int ejercicio, int idCultivo){
		//String query;
		StringBuilder query= new StringBuilder();
		Double resp=0.0;
		try {
			if (claveBodega != null && !claveBodega.isEmpty()){
				if(query.length()>0){
					query.append(" and clave_bodega = '").append(claveBodega).append("'");
				}else{
					query.append("where clave_bodega = '").append(claveBodega).append("'");
				}
			}

			if (idCiclo != 0 && idCiclo!=-1){
				if(query.length()>0){
					query.append(" and id_ciclo=").append(idCiclo);
				}else{
					query.append("where id_ciclo=").append(idCiclo);
				}
			}
			
			if (ejercicio != 0 && ejercicio!=-1){
				if(query.length()>0){
					query.append(" and ejercicio=").append(ejercicio);
				}else{
					query.append("where ejercicio=").append(ejercicio);
				}
			}

			if (idCultivo != 0 && idCultivo!=-1){
				if(query.length()>0){
					query.append(" and id_cultivo=").append(idCultivo);
				}else{
					query.append("where id_cultivo=").append(idCultivo);
				}
			}

			resp = Double.parseDouble(session.createSQLQuery("SELECT  COALESCE(sum(acopio_total_ton),0) from seguimiento_centro_acopio "+query.toString()).list().get(0).toString());
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<ResumenAvanceAcopioV> consultaReporteResumen(int idCicloSeg) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ResumenAvanceAcopioV> lst = null;
		
		if (idCicloSeg != 0 && idCicloSeg != -1){
			if(consulta.length()>0){
				consulta.append(" and idCicloSeg = ").append(idCicloSeg);
			} else {
				consulta.append(" where idCicloSeg = ").append(idCicloSeg);
			}
		}

		consulta.insert(0, "From ResumenAvanceAcopioV ").append(" order by ciclo, nombreEstadoBodega, nombreCultivo");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<ResumenAvanceAcopioV> isolatedconsultaReporteResumen(int idCicloSeg)throws  JDBCException{
		Session s = null;
		StringBuilder consulta= new StringBuilder();
		List<ResumenAvanceAcopioV> lst = null;

		try{			
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if (idCicloSeg != 0 && idCicloSeg != -1){
				if(consulta.length()>0){
					consulta.append(" and idCicloSeg = ").append(idCicloSeg);
				} else {
					consulta.append(" where idCicloSeg = ").append(idCicloSeg);
				}
			}

			consulta.insert(0, "From ResumenAvanceAcopioV ").append(" order by ciclo, nombreEstadoBodega, nombreCultivo");
			lst= s.createQuery(consulta.toString()).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}
		return lst;
	}

	public void borrarObjeto(Object o)throws JDBCException{		    
		session.delete(o);
		session.flush();
	}
}// fin de clase
