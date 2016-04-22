package mx.gob.comer.sipc.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.domain.transaccionales.AvancePagos;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDof;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDofDetalle;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AvancePagosV;
import mx.gob.comer.sipc.vistas.domain.AvisosDofDetalleV;

import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.SQLQuery;
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
	public List<AvisosDof> getAvisosDof(Integer id, String fechaPublicacion) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDof> lst=null;
		if (id !=null && id != 0 && id != -1){
			consulta.append("where id_avisos_dof = ").append(id);
		}
		
		if(fechaPublicacion != null && !fechaPublicacion.equals("")){
			if(consulta.length()>0){
				consulta.append(" and fecha_publicacion ='").append(fechaPublicacion).append("'");
			}else{
				consulta.append("where fecha_publicacion ='").append(fechaPublicacion).append("'");
			}
		}
		consulta.insert(0, "From AvisosDof ").append(" ORDER BY fechaPublicacion");
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<AvisosDofV> getAvisosDofV(Integer id) throws  JDBCException{
//		StringBuilder consulta= new StringBuilder();
//		List<AvisosDofV> lst=null;
//		if (!(id == null || id.equals(""))){
//			consulta.append("where id = '").append(id).append("'");
//		}		
//		
//		consulta.insert(0, "From AvisosDofV ").append(" ORDER BY fechaPublicacion");
//		lst= session.createQuery(consulta.toString()).list();
//		
//		return lst;
//	}
	
//	public List<AvisosDofDetalleV> getAvisosDofDetalle(String claveAcceso) throws  JDBCException{
//		return getAvisosDofDetalle(claveAcceso, 0,0,null,0,0, null, 0);
//	}
//	
	public List<AvisosDofDetalleV> getAvisosDofDetalleV(int id) throws  JDBCException{
		return getAvisosDofDetalleV(0, 0,0,0,null,0,id);
	}
	@SuppressWarnings("unchecked")
	public List<AvisosDofDetalleV> getAvisosDofDetalleV(int idAvisosDof, int idProgAviso, int idApoyo, int idCultivo, 
			String cicloAgricola,  int idEstado, Integer id) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDofDetalleV> lst=null;
		if (idAvisosDof != 0 && idAvisosDof != -1){
			consulta.append("where idAvisosDof = ").append(idAvisosDof);
		}		
		
		if(idProgAviso !=0 && idProgAviso!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idProgAviso =").append(idProgAviso);
				}else{
					consulta.append("where idProgAviso=").append(idProgAviso);
				}
		 }
		if(idApoyo !=0 && idApoyo != -1){
			  if(consulta.length()>0){
					consulta.append(" and idApoyo =").append(idApoyo);
				}else{
					consulta.append("where idApoyo = ").append(idApoyo);
				}
		 }
		 
		 if(idCultivo !=0 && idCultivo!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idCultivo =").append(idCultivo);
				}else{
					consulta.append("where idCultivo=").append(idCultivo);
				} 
		 }
		
		 
		 if (cicloAgricola !=  null && !cicloAgricola.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and cicloAgricola ='").append(cicloAgricola).append("'");
				}else{
					consulta.append("where cicloAgricola ='").append(cicloAgricola).append("'");
				}			
		 }
		 
		 
		 if(idEstado !=0 && idEstado!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idEstado =").append(idEstado);
				}else{
					consulta.append("where idEstado =").append(idEstado);
				} 
		 }
		
		 if(id !=0 && id!=-1){
			  if(consulta.length()>0){
					consulta.append(" and id =").append(id);
				}else{
					consulta.append("where id =").append(id);
				} 
		 }		
		
		consulta.insert(0, "From AvisosDofDetalleV ").append(" ORDER BY programa, cultivo, cicloAgricola, estado");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<AvisosDofDetalle> getAvisosDofDetalle(int idAvisosDof, int idProgAviso, int idApoyo, int idCultivo, 
			String cicloAgricola,  int idEstado, Integer id) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AvisosDofDetalle> lst=null;
		if (idAvisosDof != 0 && idAvisosDof != -1){
			consulta.append("where idAvisosDof = ").append(idAvisosDof);
		}		
		
		if(idProgAviso !=0 && idProgAviso!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idPrograma =").append(idProgAviso);
				}else{
					consulta.append("where idPrograma=").append(idProgAviso);
				}
		 }
		if(idApoyo !=0 && idApoyo != -1){
			  if(consulta.length()>0){
					consulta.append(" and idApoyo =").append(idApoyo);
				}else{
					consulta.append("where idApoyo = ").append(idApoyo);
				}
		 }
		 
		 if(idCultivo !=0 && idCultivo!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idCultivo =").append(idCultivo);
				}else{
					consulta.append("where idCultivo=").append(idCultivo);
				} 
		 }
		
		 
		 if (cicloAgricola !=  null && !cicloAgricola.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and cicloAgricola ='").append(cicloAgricola).append("'");
				}else{
					consulta.append("where cicloAgricola ='").append(cicloAgricola).append("'");
				}			
		 }
		 
		 
		 if(idEstado !=0 && idEstado!=-1){
			  if(consulta.length()>0){
					consulta.append(" and idEstado =").append(idEstado);
				}else{
					consulta.append("where idEstado =").append(idEstado);
				} 
		 }
		
		 if(id !=0 && id!=-1){
			  if(consulta.length()>0){
					consulta.append(" and id =").append(id);
				}else{
					consulta.append("where id =").append(id);
				} 
		 }		
		
		consulta.insert(0, "From AvisosDofDetalle ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public int borrarUltimoArchivoAvancePagos()throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("delete from avance_pagos where folio_archivo = (select max(folio_archivo) from avance_pagos )" );
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	public int borrarDetalleAviso(Integer id)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("delete from avisos_dof_detalle where id = ").append(id);
						elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	public int getAvanceExistente(Integer idAvisosDof, int idProgAviso, int idCultivo, 
			 int idEstado, String cicloAgricola) throws Exception{ 
		String query;
		BigInteger resp = null;
		try {
			query = "select count(*) from avance_pagos "+
					" where  id_programa = "+idProgAviso+
					" and id_cultivo = "+idCultivo+
					" and ciclo_agricola = '"+cicloAgricola+"'"+
					" and id_estado = "+idEstado;
			resp = (BigInteger) session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return resp.intValue();
	}


	@SuppressWarnings("unchecked")
	public List<AvancePagosV> getAvancePagosV(int idPrograma, int idApoyo, int idCultivo, 
			 int idEstado, String cicloAgricola, String fechaRegistro, String fechaAvance) throws Exception{ 
		List<AvancePagosV> lst = null;
		StringBuilder consulta= new StringBuilder();
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		if (idApoyo != 0 && idApoyo != -1){
			if(consulta.length()>0){
				consulta.append(" and idApoyo=").append(idApoyo);
			}else{
				consulta.append("where idApoyo =").append(idApoyo);
			}
		}
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo=").append(idCultivo);
			}else{
				consulta.append("where idCultivo =").append(idCultivo);
			}
		}
		if (idEstado != 0 && idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado=").append(idEstado);
			}else{
				consulta.append("where idEstado =").append(idEstado);
			}
		}
		if (cicloAgricola != null && !cicloAgricola.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and cicloAgricola ='").append(cicloAgricola).append("'");
			}else{
				consulta.append("where cicloAgricola ='").append(cicloAgricola).append("'");
			}
		}	
		
		if(fechaRegistro != null && !fechaRegistro.equals("")){
			if(consulta.length()>0){
				consulta.append(" and TO_CHAR(fechaRegistro,'YYYY-MM-DD') ='").append(fechaRegistro).append("'");
			}else{
				consulta.append("where TO_CHAR(fechaRegistro,'YYYY-MM-DD') ='").append(fechaRegistro).append("'");
			}
		}
		if(fechaAvance != null && !fechaAvance.equals("")){
			if(consulta.length()>0){
				consulta.append(" and TO_CHAR(fechaAvance,'YYYY-MM-DD') ='").append(fechaAvance).append("'");
			}else{
				consulta.append("where TO_CHAR(fechaAvance,'YYYY-MM-DD') ='").append(fechaAvance).append("'");
			}
		}		
		consulta.insert(0, "From AvancePagosV ").append(" ORDER BY programa, cicloAgricola, cultivo, estado");
		lst= session.createQuery(consulta.toString()).list();		
		return lst;
	}
	
	
	
	public List<AvancePagos> getFechasRegistroAvancet() throws Exception{ 
		String consulta;
		List<AvancePagos> lst = new ArrayList<AvancePagos>();
		try {
			consulta = "select distinct TO_CHAR(fecha_registro,'YYYY-MM-DD') as fecha_registro from avance_pagos ";
			SQLQuery query = session.createSQLQuery(consulta.toString());	
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				AvancePagos  a = new AvancePagos();
				Date fecha = Utilerias .convertirStringToDateyyyyMMdd((String) row.get("fecha_registro"));
				a.setFechaRegistro(fecha);
				lst.add(a);	
			}
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return lst;
	}
	
}
