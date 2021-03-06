package mx.gob.comer.sipc.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.comer.sipc.vistas.domain.OficioCompradorProgramaV;
import mx.gob.comer.sipc.vistas.domain.RendicionCuentasV;
import mx.gob.comer.sipc.vistas.domain.ReporteConcentradoPagosV;
import mx.gob.comer.sipc.vistas.domain.ReporteDetConcentradoPagosEtapasV;
import mx.gob.comer.sipc.vistas.domain.ReporteDetConcentradoPagosV;
import mx.gob.comer.sipc.vistas.domain.ReporteGlobalV;
import mx.gob.comer.sipc.vistas.domain.ReporteProgramaCompradorV;
import mx.gob.comer.sipc.vistas.domain.RepresentanteCompradorV;
import mx.gob.comer.sipc.vistas.domain.RespuestaPagosV;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ReportesDAO {

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public ReportesDAO() {
		
	}
	public ReportesDAO(Session session,	Transaction transaction ) {
		this.session = session;
		this.transaction = transaction;
	}
	
	@SuppressWarnings("unchecked")
	public List<RespuestaPagosV> consultaRespuestaPagosV(int idPrograma, String noOficio, String fechaInicio, String fechaFin )throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<RespuestaPagosV> lst = null;
		
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		if(noOficio != null && !noOficio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and noOficio='").append(noOficio).append("'");
			}else{
				consulta.append("where noOficio='").append(noOficio).append("'");
			}
		}
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fechaOficio,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fechaOficio,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fechaOficio,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fechaOficio,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		consulta.insert(0, "From RespuestaPagosV ").append(" order by idOficioPagos");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	public List<OficioCompradorProgramaV> consultaOficioCompradorProgramaV(long idOficioPagos) throws  JDBCException{
		return consultaOficioCompradorProgramaV(idOficioPagos,null,0);
	}
	
	public List<OficioCompradorProgramaV> consultaOficioCompradorProgramaV(long idOficioPagos, int estatus) throws  JDBCException{
		return consultaOficioCompradorProgramaV(idOficioPagos,null, estatus);
	}
	@SuppressWarnings("unchecked")
	public List<OficioCompradorProgramaV> consultaOficioCompradorProgramaV(long idOficioPagos, String idPagos, int estatus)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<OficioCompradorProgramaV> lst = null;

		if (idOficioPagos != 0 && idOficioPagos != -1){
			consulta.append("where idOficioPagos=").append(idOficioPagos);
		}
		
		if(idPagos != null && idPagos!=""){
			if(consulta.length()>0){
				consulta.append(" and idPago in (").append(idPagos).append(")");
			}else{
				consulta.append("where idPago in (").append(idPagos).append(")");
			}
		}
		
		if(estatus !=0 && estatus !=-1){
			if(consulta.length()>0){
				consulta.append(" and estatus = ").append(estatus);
			}else{
				consulta.append("where estatus =").append(estatus);
			}
		}
		consulta.insert(0, "From OficioCompradorProgramaV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<OficioCompradorProgramaV> consultaOficioCompradorProgramaVSession(long idOficioPagos)throws  Exception{
		StringBuilder consulta= new StringBuilder();
		List<OficioCompradorProgramaV> lst = null;
		try{
			session = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			transaction = session.beginTransaction();
	
			if (idOficioPagos != 0 && idOficioPagos != -1){
				consulta.append("where idPago=").append(idOficioPagos);
			}
			
			consulta.insert(0, "From OficioCompradorProgramaV ");
			lst= session.createQuery(consulta.toString()).list();
			transaction.commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<RepresentanteCompradorV> consultaRepresentanteCompradorVSession(int idRepresentate, int idComprador, int estatusRepresentante)throws Exception {
		StringBuilder consulta= new StringBuilder();
		List<RepresentanteCompradorV> lst = null;
		try{		
			session = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			transaction = session.beginTransaction();

			if(idRepresentate!=0 && idRepresentate != -1){
				consulta.append("where idRepresentate = ").append(idRepresentate);
			}
			if(idComprador != 0 && idComprador != -1){
			       if(consulta.length()>0){
			    	   consulta.append(" and idComprador=").append(idComprador);
			       	}else{
			    		consulta.append("where idComprador=").append(idComprador);
			       	}
			}
			if(estatusRepresentante != 0 && estatusRepresentante != -1){
			       if(consulta.length()>0){
			    	   consulta.append(" and estatusRepresentante=").append(estatusRepresentante);
			       	}else{
			    		consulta.append("where estatusRepresentante=").append(estatusRepresentante);
			       	}
			}
			consulta.insert(0, "From RepresentanteCompradorV ");
	        lst= session.createQuery(consulta.toString()).list(); 
	        transaction.commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		
		return lst;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RespuestaPagosV> consultaOficiosPagoV(long idOficioPagos, String noOficio, int folioCLC) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RespuestaPagosV> lst=null;
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
				consulta.append(" and folioClc=").append(folioCLC);
			}else{
				consulta.append("where folioClc=").append(folioCLC);
			}
		}
		consulta.insert(0, "From RespuestaPagosV ").append(" ORDER BY noOficio");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<RespuestaPagosV> isolatedConsultaRespuestaPagosV(int idPrograma, String noOficio, String fechaInicio, String fechaFin )throws  JDBCException{
		Session s = null;
		StringBuilder consulta= new StringBuilder();
		List<RespuestaPagosV> lst = null;

		try{			
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if (idPrograma != 0 && idPrograma != -1){
				consulta.append("where idPrograma = ").append(idPrograma);
			}
			
			if(noOficio != null && !noOficio.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and noOficio='").append(noOficio).append("'");
				}else{
					consulta.append("where noOficio='").append(noOficio).append("'");
				}
			}
			if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
				if(consulta.length()>0){
					consulta.append(" and  (TO_CHAR(fechaOficio,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
							.append(" and '").append(fechaFin).append("'");
				}else{
					consulta.append(" where (TO_CHAR(fechaOficio,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
							.append(" and '").append(fechaFin).append("'");
				}
			}else{
				if(fechaInicio != null && !fechaInicio.equals("")){
					if(consulta.length()>0){
						consulta.append(" and (TO_CHAR(fechaOficio,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
					}else{
						consulta.append("where (TO_CHAR(fechaOficio,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
					}
				}
			}
			consulta.insert(0, "From RespuestaPagosV ").append(" order by idOficioPagos");
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
	
	@SuppressWarnings("rawtypes")
	public List consultaReporteDinamico(int tipoReporte, String [] idCriterios, String[] agrupados, int pagado, 
			int inicio, int numRegistros, int pagina, String fechaInicio, String fechaFin)throws  JDBCException{
		return consultaReporteDinamico(tipoReporte, idCriterios, agrupados, 0, 0, 0, pagado, 0,0, inicio, numRegistros, pagina, fechaInicio, fechaFin, 0, 0, 0, 0);
	}

	@SuppressWarnings("rawtypes")
	public List consultaReporteDinamico(int tipoReporte, String [] idCriterios, String[] agrupados, int pagado, 
			int inicio, int numRegistros, int pagina, String fechaInicio, String fechaFin, Integer idPrograma, Integer idCultivo, Integer idVariedad, Integer idBodega)throws  JDBCException{
		return consultaReporteDinamico(tipoReporte, idCriterios, agrupados, 0, 0, 0, pagado, 0,0, inicio, numRegistros, pagina, fechaInicio, fechaFin, idPrograma, idCultivo, idVariedad, idBodega);
	}

	public List consultaReporteDinamico(int tipoReporte, String [] idCriterios, String[] agrupados, 
			int registrado, int autorizado, int solicitado, int pagado, int rechazado, int pendiente, 
			int inicio, int numRegistros, int pagina, String fechaInicio, String fechaFin, Integer idPrograma, 
			Integer idCultivo, Integer idVariedad, Integer idBodega)throws  JDBCException{
		
		List lista = new ArrayList<Object>();
		StringBuilder consulta= new StringBuilder();
		String condicionPrograma="";
		String condicionComprador="";
		String condicionOficio="";
		String condicionFechaPago="";
		String condicionEstado="";
		String condicionCultivo="";
		String condicionVariedad="";
		String condicionBodega="";
		
		//StringBuilder sqlTramitados = new StringBuilder();
		//StringBuilder sqlVolumenTramitados = new StringBuilder();
		//StringBuilder sqlImporteTramitados = new StringBuilder();
		StringBuilder sqlRegistrados = new StringBuilder();
		StringBuilder sqlVolumenRegistrados = new StringBuilder();
		StringBuilder sqlImporteRegistrados = new StringBuilder();
		StringBuilder sqlAutorizados = new StringBuilder();
		StringBuilder sqlVolumenAutorizados = new StringBuilder();
		StringBuilder sqlImporteAutorizados = new StringBuilder();
		StringBuilder sqlSolicitados = new StringBuilder();
		StringBuilder sqlVolumenSolicitados = new StringBuilder();
		StringBuilder sqlImporteSolicitados = new StringBuilder();
		StringBuilder sqlAplicados = new StringBuilder();
		StringBuilder sqlVolumenAplicados = new StringBuilder();
		StringBuilder sqlImporteAplicados = new StringBuilder();
		StringBuilder sqlRechazados = new StringBuilder();
		StringBuilder sqlVolumenRechazados = new StringBuilder();
		StringBuilder sqlImporteRechazados = new StringBuilder();
		StringBuilder sqlPendientes = new StringBuilder();
		StringBuilder sqlVolumenPendiente = new StringBuilder();
		StringBuilder sqlImportePendiente = new StringBuilder();
		StringBuilder select= new StringBuilder();
		StringBuilder groupBy= new StringBuilder();
		StringBuilder orderBy= new StringBuilder();
		final String from =" from tramitados_pagados_pendiente_v t";
		final String where =" where estatus IN (5,10)"; // AHS [LINEA] 16042015
		
		String [] elementSelect = new String [idCriterios.length];
		String [] elementOrderBy = new String [idCriterios.length];
		String [] elementGroupBy = new String [idCriterios.length];
				
		/*Asignando los valores a los criterios seleccionados por el usuario*/
		int programa = 0;
		int agrupacionPrograma = 0;
		int participante = 0;
		int agrupacionParticipante = 0;
		int oficio = 0;
		int agrupacionOficio = 0;
		int fechaPago = 0;
		int agrupacionFechaPago = 0;
		int estado = 0;
		int agrupacionEstado = 0;
		int cultivo = 0;
		int agrupacionCultivo = 0;
		int variedad = 0;
		int agrupacionVariedad = 0;
		int bodega= 0;
		int agrupacionBodega= 0;

		int idCriterio = 0;
		for(int i=0; i<idCriterios.length; i++){
			idCriterio = Integer.parseInt(idCriterios[i]);
			if(tipoReporte == 0){
				if(idCriterio==1){
					programa = idCriterio;
					agrupacionPrograma = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==2){
					participante = idCriterio;
					agrupacionParticipante = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==3){
					oficio = idCriterio;
					agrupacionOficio = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==4){
					fechaPago = idCriterio;
					agrupacionFechaPago = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==5){
					estado = idCriterio;
					agrupacionEstado = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==6){
					cultivo = idCriterio;
					agrupacionCultivo = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==7){
					variedad = idCriterio;
					agrupacionVariedad = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==8){
					bodega = idCriterio;
					agrupacionBodega = Integer.parseInt(agrupados[i]);
				}
			} else {
				if(idCriterio==1){
					programa = idCriterio;
					agrupacionPrograma = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==2){
					participante = idCriterio;
					agrupacionParticipante = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==3){
					oficio = idCriterio;
					agrupacionOficio = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==4){
					estado = idCriterio;
					agrupacionEstado = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==5){
					cultivo = idCriterio;
					agrupacionCultivo = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==6){
					variedad = idCriterio;
					agrupacionVariedad = Integer.parseInt(agrupados[i]);
				}else if(idCriterio==7){
					bodega = idCriterio;
					agrupacionBodega = Integer.parseInt(agrupados[i]);
				}				
			}
		}

		String selectCount="(select coalesce(count(ps.id_pago),0) from pagos ps ";
		String selectVolumen="(select coalesce(sum(ps.volumen),0) from pagos ps ";
		String selectImporte="(select coalesce(sum(ps.importe),0) from pagos ps ";
		
		if(tipoReporte == 1){
			if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
				selectCount="(select coalesce(count(ps.id_pago),0) from pagos ps, oficio_pagos o ";
				selectVolumen="(select coalesce(sum(ps.volumen),0) from pagos ps, oficio_pagos o ";
				selectImporte="(select coalesce(sum(ps.importe),0) from pagos ps, oficio_pagos o ";
			}else if ((fechaInicio != null && !fechaInicio.equals(""))){
				selectCount="(select coalesce(count(ps.id_pago),0) from pagos ps, oficio_pagos o ";
				selectVolumen="(select coalesce(sum(ps.volumen),0) from pagos ps, oficio_pagos o ";
				selectImporte="(select coalesce(sum(ps.importe),0) from pagos ps, oficio_pagos o ";				
			}
		}
		
		if(programa !=0){
			elementSelect[agrupacionPrograma]=" t.id_programa, t.programa,";
			elementGroupBy[agrupacionPrograma]=" t.id_programa, t.programa,";
			elementOrderBy[agrupacionPrograma]=" t.programa,";
			condicionPrograma=" and ps.id_programa=t.id_programa ";
			if(idPrograma!=null && idPrograma>0){
				condicionPrograma+=" and t.id_programa = "+idPrograma+" ";
			}
		}		
		if(participante != 0){
			elementSelect[agrupacionParticipante]=" t.id_comprador, t.comprador, t.no_carta,";	
			elementGroupBy[agrupacionParticipante]=" t.id_comprador, t.comprador, t.no_carta,";
			elementOrderBy[agrupacionParticipante]=" t.comprador,";	
			condicionComprador = " and ps.id_comprador = t.id_comprador and ps.no_carta = t.no_carta ";
		}
		if(oficio != 0){
			elementSelect[agrupacionOficio]=" t.id_oficio, t.no_oficio, t.fecha_oficio,";	
			elementGroupBy[agrupacionOficio]=" t.id_oficio, t.no_oficio, t.fecha_oficio,";
			elementOrderBy[agrupacionOficio]=" t.no_oficio,";
			condicionOficio = "and ps.id_oficio = t.id_oficio ";
		}
		if(fechaPago != 0){
			elementSelect[agrupacionFechaPago]=" t.fecha_pago,";
			elementGroupBy[agrupacionFechaPago]=" t.fecha_pago,";
			elementOrderBy[agrupacionFechaPago]=" t.fecha_pago,";
			condicionFechaPago = " and ps.fecha_pago=t.fecha_pago ";
		}
		if(estado != 0){
			if(participante != 0){
				elementSelect[agrupacionEstado]=" t.id_estado, t.estado, coalesce(t.nom_mpio_predominante,'-') as nom_mpio_predominante, "
						//+"(SELECT coalesce( cast(ps.productores_beneficiados as bigint),0)  FROM pagos ps WHERE ps.id_pago = ((SELECT max(ps.id_pago) AS max "
						//+"FROM pagos ps WHERE ps.no_carta = t.no_carta AND ps.estatus = 5 ))) as numero_prod_benef,";
						+(bodega > 0 ?"(select coalesce(sum(numero_prod_benef),0) "
						+" from relacion_compras_tmp r where r.folio_carta_adhesion =  t.no_carta and coalesce(r.clave_bodega,'NO DEFINIDA')=t.clave_bodega) as numero_prod_benef,":"");
						
				elementGroupBy[agrupacionEstado]=" t.id_estado, t.estado, coalesce(t.nom_mpio_predominante,'-'),";
				elementOrderBy[agrupacionEstado]=" t.estado,";
			} else {
				elementSelect[agrupacionEstado]=" t.id_estado, t.estado,";
				elementGroupBy[agrupacionEstado]=" t.id_estado, t.estado,";
				elementOrderBy[agrupacionEstado]=" t.estado,";				
			}
			
			if(cultivo != 0){
				if(tipoReporte == 1){
					if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
						selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
						selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
						selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";						
					}else if ((fechaInicio != null && !fechaInicio.equals(""))){
						selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
						selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
						selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";						
					}else{
						selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
						selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
						selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";	
					}
				} else {
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				}
				
				condicionEstado = " and pd.id_estado=t.id_estado ";
				if(participante != 0){
					condicionEstado += " and ps.no_carta = t.no_carta ";
				}

				elementSelect[agrupacionCultivo]=" t.id_cultivo, t.cultivo,";
				elementGroupBy[agrupacionCultivo]=" t.id_cultivo, t.cultivo,";
				elementOrderBy[agrupacionCultivo]=" t.cultivo,";
				condicionCultivo = " ps.id_pago = pd.id_pago and coalesce(pd.id_cultivo,0)=t.id_cultivo ";
				if(idCultivo!=null && idCultivo > 0){
					condicionCultivo +=" and t.id_cultivo = "+idCultivo+" ";
				}

				if(variedad > 0){				
					elementSelect[agrupacionVariedad]=" t.id_variedad, t.variedad,";
					elementGroupBy[agrupacionVariedad]=" t.id_variedad, t.variedad,";
					elementOrderBy[agrupacionVariedad]=" t.variedad,";
					condicionCultivo += " and coalesce(pd.id_variedad,0)=t.id_variedad ";
					if(idVariedad!=null && idVariedad > 0){
						condicionCultivo +=" and t.id_variedad = "+idVariedad+" ";
					}
				}

				if(bodega > 0){
					elementSelect[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
					elementGroupBy[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
					elementOrderBy[agrupacionBodega]=" t.clave_bodega,";
					condicionCultivo += " and coalesce(pd.clave_bodega,'NO DEFINIDA')=t.clave_bodega ";
					if(idBodega!=null && idBodega > 0){
						condicionCultivo +=" and t.id_bodega = "+idBodega+" ";
					}
				}
			} else {
				if(tipoReporte == 1){
					if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){					
						selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps, pagos_detalle pd ";
						selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps, pagos_detalle pd ";
						selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps, pagos_detalle pd ";
					}else if ((fechaInicio != null && !fechaInicio.equals(""))){
						selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps, pagos_detalle pd ";
						selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps, pagos_detalle pd ";
						selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps, pagos_detalle pd ";
					}else{
						selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps, pagos_detalle pd ";
						selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps, pagos_detalle pd ";
						selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps, pagos_detalle pd ";
					}
				} else {				
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from pagos ps, pagos_detalle pd ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from pagos ps, pagos_detalle pd ";
					selectImporte="(select coalesce(sum(pd.importe),0) from pagos ps, pagos_detalle pd ";
				}

				condicionEstado = " and ps.id_pago = pd.id_pago and pd.id_estado=t.id_estado ";
				if(participante != 0){
					condicionEstado += " and ps.no_carta = t.no_carta ";
				}

				if(bodega > 0){
					elementSelect[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
					elementGroupBy[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
					elementOrderBy[agrupacionBodega]=" t.clave_bodega,";
					condicionEstado += " and coalesce(pd.clave_bodega,'NO DEFINIDA')=t.clave_bodega ";
					if(idBodega!=null && idBodega > 0){
						condicionEstado +=" and t.id_bodega = "+idBodega+" ";
					}
				}
			}
		} else if(cultivo != 0){
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){					
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}else{
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}
			} else {							
				selectCount="(select coalesce(count(distinct pd.id_pago),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				selectVolumen="(select coalesce(sum(pd.volumen),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				selectImporte="(select coalesce(sum(pd.importe),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
			}
			
			elementSelect[agrupacionCultivo]=" t.id_cultivo, t.cultivo,";
			elementGroupBy[agrupacionCultivo]=" t.id_cultivo, t.cultivo,";
			elementOrderBy[agrupacionCultivo]=" t.cultivo,";
			condicionCultivo = " ps.id_pago = pd.id_pago and coalesce(pd.id_cultivo,0)=t.id_cultivo ";
			if((idCultivo!=null && idCultivo > 0)){
				condicionCultivo +=" and t.id_cultivo = "+idCultivo+" ";
			}

			if(variedad > 0){				
				elementSelect[agrupacionVariedad]=" t.id_variedad, t.variedad,";
				elementGroupBy[agrupacionVariedad]=" t.id_variedad, t.variedad,";
				elementOrderBy[agrupacionVariedad]=" t.variedad,";
				condicionCultivo += " and coalesce(pd.id_variedad,0)=t.id_variedad ";
				if((idVariedad!=null && idVariedad > 0)){
					condicionCultivo +=" and t.id_variedad = "+idVariedad+" ";
				}
			}

			if(bodega > 0){
				elementSelect[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
				elementGroupBy[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
				elementOrderBy[agrupacionBodega]=" t.clave_bodega,";
				condicionCultivo += " and coalesce(pd.clave_bodega,'NO DEFINIDA')=t.clave_bodega ";
				if(idBodega!=null && idBodega > 0){
					condicionCultivo +=" and t.id_bodega = "+idBodega+" ";
				}
			}
		} else if(bodega != 0){
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){					
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}else{
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}
			} else {							
				selectCount="(select coalesce(count(distinct pd.id_pago),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				selectVolumen="(select coalesce(sum(pd.volumen),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				selectImporte="(select coalesce(sum(pd.importe),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
			}
			
			elementSelect[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
			elementGroupBy[agrupacionBodega]=" t.id_bodega, t.clave_bodega,";
			elementOrderBy[agrupacionBodega]=" t.clave_bodega,";
			condicionBodega = " ps.id_pago = pd.id_pago and coalesce(pd.clave_bodega,'NO DEFINIDA')=t.clave_bodega ";
			if(idBodega!=null && idBodega > 0){
				condicionBodega +=" and t.id_bodega = "+idBodega+" ";
			}
			
			if(variedad > 0){				
				elementSelect[agrupacionVariedad]=" t.id_variedad, t.variedad,";
				elementGroupBy[agrupacionVariedad]=" t.id_variedad, t.variedad,";
				elementOrderBy[agrupacionVariedad]=" t.variedad,";
				condicionBodega += " and coalesce(pd.id_variedad,0)=t.id_variedad ";
				if((idVariedad!=null && idVariedad > 0)){
					condicionBodega +=" and t.id_variedad = "+idVariedad+" ";
				}
			}

		}else if(variedad != 0){
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){					
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}else{
					selectCount="(select coalesce(count(distinct pd.id_pago),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectVolumen="(select coalesce(sum(pd.volumen),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
					selectImporte="(select coalesce(sum(pd.importe),0) from  pagos ps LEFT JOIN pagos_detalle pd ON ";
				}
			} else {							
				selectCount="(select coalesce(count(distinct pd.id_pago),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				selectVolumen="(select coalesce(sum(pd.volumen),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
				selectImporte="(select coalesce(sum(pd.importe),0) from pagos ps LEFT JOIN pagos_detalle pd ON ";
			}
			
			elementSelect[agrupacionVariedad]=" t.id_variedad, t.variedad,";
			elementGroupBy[agrupacionVariedad]=" t.id_variedad, t.variedad,";
			elementOrderBy[agrupacionVariedad]=" t.variedad,";
			condicionVariedad += "ps.id_pago = pd.id_pago and coalesce(pd.id_variedad,0)=t.id_variedad ";
			
			if(idVariedad!=null && idVariedad > 0){
				condicionVariedad +=" and t.id_variedad = "+idVariedad+" ";
			}
			
		}
		
		for(int i=0; i<elementSelect.length;i++){
			select.append(elementSelect[i]);
			groupBy.append(elementGroupBy[i]);
			orderBy.append(elementOrderBy[i]);
		}
		groupBy.insert(0, " GROUP BY ");
		groupBy.deleteCharAt(groupBy.length()-1);
		orderBy.insert(0, " ORDER BY ");
		orderBy.deleteCharAt(orderBy.length()-1);
/*		
		if(tramitado!=0){
			if(cultivo != 0){
				sqlTramitados.append(selectCount+condicionCultivo);
				sqlVolumenTramitados.append(selectVolumen+condicionCultivo);
				sqlImporteTramitados.append(selectImporte+condicionCultivo);
				
				sqlTramitados.append(" where ps.id_oficio is not null ");
				sqlVolumenTramitados.append(" where ps.id_oficio is not null ");
				sqlImporteTramitados.append(" where ps.id_oficio is not null ");
				if(programa != 0){
					sqlTramitados.append(condicionPrograma);
					sqlVolumenTramitados.append(condicionPrograma);
					sqlImporteTramitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlTramitados.append(condicionComprador);
					sqlVolumenTramitados.append(condicionComprador);
					sqlImporteTramitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlTramitados.append(condicionOficio);
					sqlVolumenTramitados.append(condicionOficio);
					sqlImporteTramitados.append(condicionOficio);
				}
				if(estado != 0){
					sqlTramitados.append(condicionEstado);
					sqlVolumenTramitados.append(condicionEstado);
					sqlImporteTramitados.append(condicionEstado);
				}
			} else if(bodega != 0){
				sqlTramitados.append(selectCount+condicionBodega);
				sqlVolumenTramitados.append(selectVolumen+condicionBodega);
				sqlImporteTramitados.append(selectImporte+condicionBodega);
				
				sqlTramitados.append(" where ps.id_oficio is not null ");
				sqlVolumenTramitados.append(" where ps.id_oficio is not null ");
				sqlImporteTramitados.append(" where ps.id_oficio is not null ");
				if(programa != 0){
					sqlTramitados.append(condicionPrograma);
					sqlVolumenTramitados.append(condicionPrograma);
					sqlImporteTramitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlTramitados.append(condicionComprador);
					sqlVolumenTramitados.append(condicionComprador);
					sqlImporteTramitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlTramitados.append(condicionOficio);
					sqlVolumenTramitados.append(condicionOficio);
					sqlImporteTramitados.append(condicionOficio);
				}
				if(estado != 0){
					sqlTramitados.append(condicionEstado);
					sqlVolumenTramitados.append(condicionEstado);
					sqlImporteTramitados.append(condicionEstado);
				}				
			} else if(variedad != 0){
				sqlTramitados.append(selectCount+condicionVariedad);
				sqlVolumenTramitados.append(selectVolumen+condicionVariedad);
				sqlImporteTramitados.append(selectImporte+condicionVariedad);
				
				sqlTramitados.append(" where ps.id_oficio is not null ");
				sqlVolumenTramitados.append(" where ps.id_oficio is not null ");
				sqlImporteTramitados.append(" where ps.id_oficio is not null ");
				if(programa != 0){
					sqlTramitados.append(condicionPrograma);
					sqlVolumenTramitados.append(condicionPrograma);
					sqlImporteTramitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlTramitados.append(condicionComprador);
					sqlVolumenTramitados.append(condicionComprador);
					sqlImporteTramitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlTramitados.append(condicionOficio);
					sqlVolumenTramitados.append(condicionOficio);
					sqlImporteTramitados.append(condicionOficio);
				}
				if(estado != 0){
					sqlTramitados.append(condicionEstado);
					sqlVolumenTramitados.append(condicionEstado);
					sqlImporteTramitados.append(condicionEstado);
				}
			}else {
				sqlTramitados.append(selectCount+" where ps.id_oficio is not null ");
				sqlVolumenTramitados.append(selectVolumen+" where ps.id_oficio is not null ");
				sqlImporteTramitados.append(selectImporte+" where ps.id_oficio is not null ");
				if(programa != 0){
					sqlTramitados.append(condicionPrograma);
					sqlVolumenTramitados.append(condicionPrograma);
					sqlImporteTramitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlTramitados.append(condicionComprador);
					sqlVolumenTramitados.append(condicionComprador);
					sqlImporteTramitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlTramitados.append(condicionOficio);
					sqlVolumenTramitados.append(condicionOficio);
					sqlImporteTramitados.append(condicionOficio);
				}
				if(estado != 0){
					sqlTramitados.append(condicionEstado);
					sqlVolumenTramitados.append(condicionEstado);
					sqlImporteTramitados.append(condicionEstado);
				}
			}

			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImporteTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}else{
					if(estado != 0 || cultivo > 0 || variedad > 0 || bodega > 0 ){
						sqlTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio ");					
						sqlVolumenTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio ");
						sqlImporteTramitados.append("  AND o.id_oficio_pagos = ps.id_oficio");
					}					
				}
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlTramitados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenTramitados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteTramitados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlTramitados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenTramitados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImporteTramitados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}

			sqlTramitados.append(") as tramitados,\n");
			sqlVolumenTramitados.append(") as volumen_tramitado,\n ");
			sqlImporteTramitados.append(") as importe_tramitado,");
			select.append(sqlTramitados.toString()).append(sqlVolumenTramitados).append(sqlImporteTramitados);
		}
*/
		if(registrado!=0){
			if(cultivo != 0){
				sqlRegistrados.append(selectCount+condicionCultivo);
				sqlVolumenRegistrados.append(selectVolumen+condicionCultivo);
				sqlImporteRegistrados.append(selectImporte+condicionCultivo);
				
				sqlRegistrados.append(" where ps.estatus in (7,9) ");
				sqlVolumenRegistrados.append(" where ps.estatus in (7,9) ");
				sqlImporteRegistrados.append(" where ps.estatus in (7,9) ");
				if(programa !=0){
					sqlRegistrados.append(condicionPrograma);
					sqlVolumenRegistrados.append(condicionPrograma);
					sqlImporteRegistrados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlRegistrados.append(condicionComprador);
					sqlVolumenRegistrados.append(condicionComprador);
					sqlImporteRegistrados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlRegistrados.append(condicionOficio);
					sqlVolumenRegistrados.append(condicionOficio);
					sqlImporteRegistrados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlRegistrados.append(condicionFechaPago);
					sqlVolumenRegistrados.append(condicionFechaPago);
					sqlImporteRegistrados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlRegistrados.append(condicionEstado);
					sqlVolumenRegistrados.append(condicionEstado);
					sqlImporteRegistrados.append(condicionEstado);
				}								
			} else if(bodega != 0){
				sqlRegistrados.append(selectCount+condicionBodega);
				sqlVolumenRegistrados.append(selectVolumen+condicionBodega);
				sqlImporteRegistrados.append(selectImporte+condicionBodega);
				
				sqlRegistrados.append(" where ps.estatus in (7,9) ");
				sqlVolumenRegistrados.append(" where ps.estatus in (7,9) ");
				sqlImporteRegistrados.append(" where ps.estatus in (7,9) ");
				if(programa !=0){
					sqlRegistrados.append(condicionPrograma);
					sqlVolumenRegistrados.append(condicionPrograma);
					sqlImporteRegistrados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlRegistrados.append(condicionComprador);
					sqlVolumenRegistrados.append(condicionComprador);
					sqlImporteRegistrados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlRegistrados.append(condicionOficio);
					sqlVolumenRegistrados.append(condicionOficio);
					sqlImporteRegistrados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlRegistrados.append(condicionFechaPago);
					sqlVolumenRegistrados.append(condicionFechaPago);
					sqlImporteRegistrados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlRegistrados.append(condicionEstado);
					sqlVolumenRegistrados.append(condicionEstado);
					sqlImporteRegistrados.append(condicionEstado);
				}								
			} else {
				sqlRegistrados.append(selectCount+" where ps.estatus in (7,9) ");
				sqlVolumenRegistrados.append(selectVolumen+" where ps.estatus in (7,9) ");
				sqlImporteRegistrados.append(selectImporte+" where ps.estatus in (7,9) ");
				if(programa !=0){
					sqlRegistrados.append(condicionPrograma);
					sqlVolumenRegistrados.append(condicionPrograma);
					sqlImporteRegistrados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlRegistrados.append(condicionComprador);
					sqlVolumenRegistrados.append(condicionComprador);
					sqlImporteRegistrados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlRegistrados.append(condicionOficio);
					sqlVolumenRegistrados.append(condicionOficio);
					sqlImporteRegistrados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlRegistrados.append(condicionFechaPago);
					sqlVolumenRegistrados.append(condicionFechaPago);
					sqlImporteRegistrados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlRegistrados.append(condicionEstado);
					sqlVolumenRegistrados.append(condicionEstado);
					sqlImporteRegistrados.append(condicionEstado);
				}				
			}
/*
			if(cultivo != 0){
				sqlRegistrados.append(condicionCultivo);
				sqlVolumenRegistrados.append(condicionCultivo);
				sqlImporteRegistrados.append(condicionCultivo);
			}
*/					
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlRegistrados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenRegistrados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteRegistrados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlRegistrados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenRegistrados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImporteRegistrados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}				
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlRegistrados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenRegistrados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteRegistrados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlRegistrados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenRegistrados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImporteRegistrados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}
			
			sqlRegistrados.append(") as registrados,\n");
			sqlVolumenRegistrados.append(") as volumen_registrado,\n");
			sqlImporteRegistrados.append(") as importe_registrado,");
			select.append(sqlRegistrados.toString()).append(sqlVolumenRegistrados).append(sqlImporteRegistrados);
		}
		
		if(autorizado!=0){
			if(cultivo != 0){
				sqlAutorizados.append(selectCount+condicionCultivo);
				sqlVolumenAutorizados.append(selectVolumen+condicionCultivo);
				sqlImporteAutorizados.append(selectImporte+condicionCultivo);
				
				sqlAutorizados.append(" where ps.estatus = 1 ");
				sqlVolumenAutorizados.append(" where ps.estatus = 1 ");
				sqlImporteAutorizados.append(" where ps.estatus = 1 ");
				if(programa !=0){
					sqlAutorizados.append(condicionPrograma);
					sqlVolumenAutorizados.append(condicionPrograma);
					sqlImporteAutorizados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlAutorizados.append(condicionComprador);
					sqlVolumenAutorizados.append(condicionComprador);
					sqlImporteAutorizados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlAutorizados.append(condicionOficio);
					sqlVolumenAutorizados.append(condicionOficio);
					sqlImporteAutorizados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlAutorizados.append(condicionFechaPago);
					sqlVolumenAutorizados.append(condicionFechaPago);
					sqlImporteAutorizados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlAutorizados.append(condicionEstado);
					sqlVolumenAutorizados.append(condicionEstado);
					sqlImporteAutorizados.append(condicionEstado);
				}								
			} else if(bodega != 0){
				sqlAutorizados.append(selectCount+condicionBodega);
				sqlVolumenAutorizados.append(selectVolumen+condicionBodega);
				sqlImporteAutorizados.append(selectImporte+condicionBodega);
				
				sqlAutorizados.append(" where ps.estatus = 1 ");
				sqlVolumenAutorizados.append(" where ps.estatus = 1 ");
				sqlImporteAutorizados.append(" where ps.estatus = 1 ");
				if(programa !=0){
					sqlAutorizados.append(condicionPrograma);
					sqlVolumenAutorizados.append(condicionPrograma);
					sqlImporteAutorizados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlAutorizados.append(condicionComprador);
					sqlVolumenAutorizados.append(condicionComprador);
					sqlImporteAutorizados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlAutorizados.append(condicionOficio);
					sqlVolumenAutorizados.append(condicionOficio);
					sqlImporteAutorizados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlAutorizados.append(condicionFechaPago);
					sqlVolumenAutorizados.append(condicionFechaPago);
					sqlImporteAutorizados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlAutorizados.append(condicionEstado);
					sqlVolumenAutorizados.append(condicionEstado);
					sqlImporteAutorizados.append(condicionEstado);
				}								
			} else {
				sqlAutorizados.append(selectCount+" where ps.estatus = 1 ");
				sqlVolumenAutorizados.append(selectVolumen+" where ps.estatus = 1 ");
				sqlImporteAutorizados.append(selectImporte+" where ps.estatus = 1 ");
				if(programa !=0){
					sqlAutorizados.append(condicionPrograma);
					sqlVolumenAutorizados.append(condicionPrograma);
					sqlImporteAutorizados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlAutorizados.append(condicionComprador);
					sqlVolumenAutorizados.append(condicionComprador);
					sqlImporteAutorizados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlAutorizados.append(condicionOficio);
					sqlVolumenAutorizados.append(condicionOficio);
					sqlImporteAutorizados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlAutorizados.append(condicionFechaPago);
					sqlVolumenAutorizados.append(condicionFechaPago);
					sqlImporteAutorizados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlAutorizados.append(condicionEstado);
					sqlVolumenAutorizados.append(condicionEstado);
					sqlImporteAutorizados.append(condicionEstado);
				}				
			}
/*
			if(cultivo != 0){
				sqlAutorizados.append(condicionCultivo);
				sqlVolumenAutorizados.append(condicionCultivo);
				sqlImporteAutorizados.append(condicionCultivo);
			}
*/					
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlAutorizados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenAutorizados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteAutorizados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlAutorizados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenAutorizados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImporteAutorizados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}				
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlAutorizados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenAutorizados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteAutorizados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlAutorizados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenAutorizados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImporteAutorizados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}
			
			sqlAutorizados.append(") as autorizados,\n");
			sqlVolumenAutorizados.append(") as volumen_autorizado,\n");
			sqlImporteAutorizados.append(") as importe_autorizado,");
			select.append(sqlAutorizados.toString()).append(sqlVolumenAutorizados).append(sqlImporteAutorizados);
		}


		if(solicitado!=0){
			if(cultivo != 0){
				sqlSolicitados.append(selectCount+condicionCultivo);
				sqlVolumenSolicitados.append(selectVolumen+condicionCultivo);
				sqlImporteSolicitados.append(selectImporte+condicionCultivo);
				
				sqlSolicitados.append(" where ps.estatus = 2 ");
				sqlVolumenSolicitados.append(" where ps.estatus = 2 ");
				sqlImporteSolicitados.append(" where ps.estatus = 2 ");
				if(programa !=0){
					sqlSolicitados.append(condicionPrograma);
					sqlVolumenSolicitados.append(condicionPrograma);
					sqlImporteSolicitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlSolicitados.append(condicionComprador);
					sqlVolumenSolicitados.append(condicionComprador);
					sqlImporteSolicitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlSolicitados.append(condicionOficio);
					sqlVolumenSolicitados.append(condicionOficio);
					sqlImporteSolicitados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlSolicitados.append(condicionFechaPago);
					sqlVolumenSolicitados.append(condicionFechaPago);
					sqlImporteSolicitados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlSolicitados.append(condicionEstado);
					sqlVolumenSolicitados.append(condicionEstado);
					sqlImporteSolicitados.append(condicionEstado);
				}								
			} else if(bodega != 0){
				sqlSolicitados.append(selectCount+condicionBodega);
				sqlVolumenSolicitados.append(selectVolumen+condicionBodega);
				sqlImporteSolicitados.append(selectImporte+condicionBodega);
				
				sqlSolicitados.append(" where ps.estatus = 2 ");
				sqlVolumenSolicitados.append(" where ps.estatus = 2 ");
				sqlImporteSolicitados.append(" where ps.estatus = 2 ");
				if(programa !=0){
					sqlSolicitados.append(condicionPrograma);
					sqlVolumenSolicitados.append(condicionPrograma);
					sqlImporteSolicitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlSolicitados.append(condicionComprador);
					sqlVolumenSolicitados.append(condicionComprador);
					sqlImporteSolicitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlSolicitados.append(condicionOficio);
					sqlVolumenSolicitados.append(condicionOficio);
					sqlImporteSolicitados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlSolicitados.append(condicionFechaPago);
					sqlVolumenSolicitados.append(condicionFechaPago);
					sqlImporteSolicitados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlSolicitados.append(condicionEstado);
					sqlVolumenSolicitados.append(condicionEstado);
					sqlImporteSolicitados.append(condicionEstado);
				}								
			} else {
				sqlSolicitados.append(selectCount+" where ps.estatus = 2 ");
				sqlVolumenSolicitados.append(selectVolumen+" where ps.estatus = 2 ");
				sqlImporteSolicitados.append(selectImporte+" where ps.estatus = 2 ");
				if(programa !=0){
					sqlSolicitados.append(condicionPrograma);
					sqlVolumenSolicitados.append(condicionPrograma);
					sqlImporteSolicitados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlSolicitados.append(condicionComprador);
					sqlVolumenSolicitados.append(condicionComprador);
					sqlImporteSolicitados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlSolicitados.append(condicionOficio);
					sqlVolumenSolicitados.append(condicionOficio);
					sqlImporteSolicitados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlSolicitados.append(condicionFechaPago);
					sqlVolumenSolicitados.append(condicionFechaPago);
					sqlImporteSolicitados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlSolicitados.append(condicionEstado);
					sqlVolumenSolicitados.append(condicionEstado);
					sqlImporteSolicitados.append(condicionEstado);
				}				
			}
/*
			if(cultivo != 0){
				sqlSolicitados.append(condicionCultivo);
				sqlVolumenSolicitados.append(condicionCultivo);
				sqlImporteSolicitados.append(condicionCultivo);
			}
*/					
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlSolicitados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenSolicitados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteSolicitados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlSolicitados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenSolicitados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImporteSolicitados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}				
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlSolicitados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenSolicitados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteSolicitados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlSolicitados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenSolicitados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImporteSolicitados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}
			
			sqlSolicitados.append(") as solicitados,\n");
			sqlVolumenSolicitados.append(") as volumen_solicitado,\n");
			sqlImporteSolicitados.append(") as importe_solicitado,");
			select.append(sqlSolicitados.toString()).append(sqlVolumenSolicitados).append(sqlImporteSolicitados);
		}

		
		if(pagado!=0){
			if(cultivo != 0){
				sqlAplicados.append(selectCount+condicionCultivo);
				sqlVolumenAplicados.append(selectVolumen+condicionCultivo);
				sqlImporteAplicados.append(selectImporte+condicionCultivo);
				
				sqlAplicados.append(" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				sqlVolumenAplicados.append(" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				sqlImporteAplicados.append(" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				if(programa !=0){
					sqlAplicados.append(condicionPrograma);
					sqlVolumenAplicados.append(condicionPrograma);
					sqlImporteAplicados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlAplicados.append(condicionComprador);
					sqlVolumenAplicados.append(condicionComprador);
					sqlImporteAplicados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlAplicados.append(condicionOficio);
					sqlVolumenAplicados.append(condicionOficio);
					sqlImporteAplicados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlAplicados.append(condicionFechaPago);
					sqlVolumenAplicados.append(condicionFechaPago);
					sqlImporteAplicados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlAplicados.append(condicionEstado);
					sqlVolumenAplicados.append(condicionEstado);
					sqlImporteAplicados.append(condicionEstado);
				}								
			} else if(bodega != 0){
				sqlAplicados.append(selectCount+condicionBodega);
				sqlVolumenAplicados.append(selectVolumen+condicionBodega);
				sqlImporteAplicados.append(selectImporte+condicionBodega);
				
				sqlAplicados.append(" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				sqlVolumenAplicados.append(" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				sqlImporteAplicados.append(" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				if(programa !=0){
					sqlAplicados.append(condicionPrograma);
					sqlVolumenAplicados.append(condicionPrograma);
					sqlImporteAplicados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlAplicados.append(condicionComprador);
					sqlVolumenAplicados.append(condicionComprador);
					sqlImporteAplicados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlAplicados.append(condicionOficio);
					sqlVolumenAplicados.append(condicionOficio);
					sqlImporteAplicados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlAplicados.append(condicionFechaPago);
					sqlVolumenAplicados.append(condicionFechaPago);
					sqlImporteAplicados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlAplicados.append(condicionEstado);
					sqlVolumenAplicados.append(condicionEstado);
					sqlImporteAplicados.append(condicionEstado);
				}								
			} else {
				sqlAplicados.append(selectCount+" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				sqlVolumenAplicados.append(selectVolumen+" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				sqlImporteAplicados.append(selectImporte+" where ps.estatus in (5,10) "); // AHS [LINEA] 16042015
				if(programa !=0){
					sqlAplicados.append(condicionPrograma);
					sqlVolumenAplicados.append(condicionPrograma);
					sqlImporteAplicados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlAplicados.append(condicionComprador);
					sqlVolumenAplicados.append(condicionComprador);
					sqlImporteAplicados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlAplicados.append(condicionOficio);
					sqlVolumenAplicados.append(condicionOficio);
					sqlImporteAplicados.append(condicionOficio);
				}	
				if(fechaPago != 0){
					sqlAplicados.append(condicionFechaPago);
					sqlVolumenAplicados.append(condicionFechaPago);
					sqlImporteAplicados.append(condicionFechaPago);
				}
				if(estado != 0){
					sqlAplicados.append(condicionEstado);
					sqlVolumenAplicados.append(condicionEstado);
					sqlImporteAplicados.append(condicionEstado);
				}				
			}
/*
			if(cultivo != 0){
				sqlAplicados.append(condicionCultivo);
				sqlVolumenAplicados.append(condicionCultivo);
				sqlImporteAplicados.append(condicionCultivo);
			}
*/					
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlAplicados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenAplicados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteAplicados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlAplicados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenAplicados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImporteAplicados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}				
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlAplicados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenAplicados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteAplicados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlAplicados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenAplicados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImporteAplicados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}
			
			sqlAplicados.append(") as aplicados,\n");
			sqlVolumenAplicados.append(") as volumen_aplicado,\n");
			sqlImporteAplicados.append(") as importe_aplicado,");
			select.append(sqlAplicados.toString()).append(sqlVolumenAplicados).append(sqlImporteAplicados);
		}
		
		if(rechazado!=0){
			if(cultivo != 0){
				sqlRechazados.append(selectCount+condicionCultivo);
				sqlVolumenRechazados.append(selectVolumen+condicionCultivo);
				sqlImporteRechazados.append(selectImporte+condicionCultivo);
				
				sqlRechazados.append(" where ps.estatus =6 ");
				sqlVolumenRechazados.append(" where ps.estatus =6 ");
				sqlImporteRechazados.append(" where ps.estatus =6 ");
				if(programa !=0){
					sqlRechazados.append(condicionPrograma);
					sqlVolumenRechazados.append(condicionPrograma);
					sqlImporteRechazados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlRechazados.append(condicionComprador);
					sqlVolumenRechazados.append(condicionComprador);
					sqlImporteRechazados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlRechazados.append(condicionOficio);
					sqlVolumenRechazados.append(condicionOficio);
					sqlImporteRechazados.append(condicionOficio);
				}	
				if(estado != 0){
					sqlRechazados.append(condicionEstado);
					sqlVolumenRechazados.append(condicionEstado);
					sqlImporteRechazados.append(condicionEstado);
				}
			} else if(bodega != 0){
				sqlRechazados.append(selectCount+condicionBodega);
				sqlVolumenRechazados.append(selectVolumen+condicionBodega);
				sqlImporteRechazados.append(selectImporte+condicionBodega);
				
				sqlRechazados.append(" where ps.estatus =6 ");
				sqlVolumenRechazados.append(" where ps.estatus =6 ");
				sqlImporteRechazados.append(" where ps.estatus =6 ");
				if(programa !=0){
					sqlRechazados.append(condicionPrograma);
					sqlVolumenRechazados.append(condicionPrograma);
					sqlImporteRechazados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlRechazados.append(condicionComprador);
					sqlVolumenRechazados.append(condicionComprador);
					sqlImporteRechazados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlRechazados.append(condicionOficio);
					sqlVolumenRechazados.append(condicionOficio);
					sqlImporteRechazados.append(condicionOficio);
				}	
				if(estado != 0){
					sqlRechazados.append(condicionEstado);
					sqlVolumenRechazados.append(condicionEstado);
					sqlImporteRechazados.append(condicionEstado);
				}				
			} else {
				sqlRechazados.append(selectCount+" where ps.estatus =6 ");
				sqlVolumenRechazados.append(selectVolumen+" where ps.estatus =6 ");
				sqlImporteRechazados.append(selectImporte+" where ps.estatus =6 ");
				if(programa !=0){
					sqlRechazados.append(condicionPrograma);
					sqlVolumenRechazados.append(condicionPrograma);
					sqlImporteRechazados.append(condicionPrograma);
				}
				if(participante != 0){
					sqlRechazados.append(condicionComprador);
					sqlVolumenRechazados.append(condicionComprador);
					sqlImporteRechazados.append(condicionComprador);
				}
				if(oficio != 0){
					sqlRechazados.append(condicionOficio);
					sqlVolumenRechazados.append(condicionOficio);
					sqlImporteRechazados.append(condicionOficio);
				}	
				if(estado != 0){
					sqlRechazados.append(condicionEstado);
					sqlVolumenRechazados.append(condicionEstado);
					sqlImporteRechazados.append(condicionEstado);
				}					
			}
/*
			if(cultivo != 0){
				sqlRechazados.append(condicionCultivo);
				sqlVolumenRechazados.append(condicionCultivo);
				sqlImporteRechazados.append(condicionCultivo);
			}	
*/			
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlRechazados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenRechazados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteRechazados.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlRechazados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenRechazados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImporteRechazados.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}				
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlRechazados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenRechazados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImporteRechazados.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlRechazados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenRechazados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImporteRechazados.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}

			sqlRechazados.append(") as rechazados,\n");	
			sqlVolumenRechazados.append(") as volumen_rechazado,\n");
			sqlImporteRechazados.append(") as importe_rechazado,");		
			select.append(sqlRechazados.toString()).append(sqlVolumenRechazados).append(sqlImporteRechazados);
		}
	
		if(pendiente!=0){
			if(cultivo != 0){
				sqlPendientes.append(selectCount+condicionCultivo);
				sqlVolumenPendiente.append(selectVolumen+condicionCultivo);
				sqlImportePendiente.append(selectImporte+condicionCultivo);
				
				sqlPendientes.append(" where ps.estatus = 4 ");
				sqlVolumenPendiente.append(" where ps.estatus = 4 ");
				sqlImportePendiente.append(" where ps.estatus = 4 ");
				if(programa !=0){
					sqlPendientes.append(condicionPrograma);
					sqlVolumenPendiente.append(condicionPrograma);
					sqlImportePendiente.append(condicionPrograma);
				}
				if(participante != 0){
					sqlPendientes.append(condicionComprador);
					sqlVolumenPendiente.append(condicionComprador);
					sqlImportePendiente.append(condicionComprador);
				}
				if(oficio != 0){
					sqlPendientes.append(condicionOficio);
					sqlVolumenPendiente.append(condicionOficio);
					sqlImportePendiente.append(condicionOficio);
				}	
				if(estado != 0){
					sqlPendientes.append(condicionEstado);
					sqlVolumenPendiente.append(condicionEstado);
					sqlImportePendiente.append(condicionEstado);
				}
			} else if(bodega != 0){
				sqlPendientes.append(selectCount+condicionBodega);
				sqlVolumenPendiente.append(selectVolumen+condicionBodega);
				sqlImportePendiente.append(selectImporte+condicionBodega);
				
				sqlPendientes.append(" where ps.estatus = 4 ");
				sqlVolumenPendiente.append(" where ps.estatus = 4 ");
				sqlImportePendiente.append(" where ps.estatus = 4 ");
				if(programa !=0){
					sqlPendientes.append(condicionPrograma);
					sqlVolumenPendiente.append(condicionPrograma);
					sqlImportePendiente.append(condicionPrograma);
				}
				if(participante != 0){
					sqlPendientes.append(condicionComprador);
					sqlVolumenPendiente.append(condicionComprador);
					sqlImportePendiente.append(condicionComprador);
				}
				if(oficio != 0){
					sqlPendientes.append(condicionOficio);
					sqlVolumenPendiente.append(condicionOficio);
					sqlImportePendiente.append(condicionOficio);
				}	
				if(estado != 0){
					sqlPendientes.append(condicionEstado);
					sqlVolumenPendiente.append(condicionEstado);
					sqlImportePendiente.append(condicionEstado);
				}				
			} else {
				sqlPendientes.append(selectCount+" where ps.estatus = 4 ");
				sqlVolumenPendiente.append(selectVolumen+" where ps.estatus = 4 ");
				sqlImportePendiente.append(selectImporte+" where ps.estatus = 4 ");
				if(programa !=0){
					sqlPendientes.append(condicionPrograma);
					sqlVolumenPendiente.append(condicionPrograma);
					sqlImportePendiente.append(condicionPrograma);
				}
				if(participante != 0){
					sqlPendientes.append(condicionComprador);
					sqlVolumenPendiente.append(condicionComprador);
					sqlImportePendiente.append(condicionComprador);
				}
				if(oficio != 0){
					sqlPendientes.append(condicionOficio);
					sqlVolumenPendiente.append(condicionOficio);
					sqlImportePendiente.append(condicionOficio);
				}	
				if(estado != 0){
					sqlPendientes.append(condicionEstado);
					sqlVolumenPendiente.append(condicionEstado);
					sqlImportePendiente.append(condicionEstado);
				}
			}
/*			
			if(cultivo != 0){
				sqlPendientes.append(condicionCultivo);
				sqlVolumenPendiente.append(condicionCultivo);
				sqlImportePendiente.append(condicionCultivo);
			}	
*/			
			if(tipoReporte == 1){
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlPendientes.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenPendiente.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImportePendiente.append("  AND o.id_oficio_pagos = ps.id_oficio and to_number(TO_CHAR(o.fecha, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlPendientes.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");					
					sqlVolumenPendiente.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
					sqlImportePendiente.append("  AND o.id_oficio_pagos = ps.id_oficio and o.fecha ='").append(fechaInicio).append("'");
				}				
			} else {
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					sqlPendientes.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlVolumenPendiente.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
					sqlImportePendiente.append("  and to_number(TO_char(ps.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					sqlPendientes.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlVolumenPendiente.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
					sqlImportePendiente.append("and TO_DATE(ps.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}

			sqlPendientes.append(") as pendientes,\n");
			sqlVolumenPendiente.append(") as volumen_pendiente,\n");
			sqlImportePendiente.append(") as importe_pendiente,");
			select.append(sqlPendientes.toString()).append(sqlVolumenPendiente).append(sqlImportePendiente);
		}
		select.insert(0, "SELECT ");
		select.deleteCharAt(select.length()-1);
		
		if(tipoReporte == 1){
			if(programa !=0 && (idPrograma!=null && idPrograma>0)){
				consulta.append(" where t.id_programa = "+idPrograma+" "+" ");
				if(cultivo !=0 && (idCultivo!=null && idCultivo > 0)){
					consulta.append(" and t.id_cultivo = "+idCultivo+" "+" ");
					if(variedad !=0 && (idVariedad!=null && idVariedad > 0)){
						consulta.append(" and t.id_variedad = "+idVariedad+" "+" ");
					}
					if(bodega !=0 && (idBodega!=null && idBodega > 0)){
						consulta.append(" and t.id_bodega = "+idBodega+" "+" ");
					}
				} else if(bodega !=0 && (idBodega!=null && idBodega > 0)){
					consulta.append(" and t.id_bodega = "+idBodega+" "+" ");
				}
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					consulta.append("  and to_number(TO_CHAR(to_date(t.fecha_oficio, 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					consulta.append("and TO_DATE(t.fecha_oficio, 'DD-MM-YYYY')='").append(fechaInicio).append("'");					
				}				
			}else if (cultivo !=0 && (idCultivo!=null && idCultivo > 0)){
				consulta.append(" where t.id_cultivo = "+idCultivo+" "+" ");
				if(variedad !=0 && (idVariedad!=null && idVariedad > 0)){
					consulta.append(" and t.id_variedad = "+idVariedad+" "+" ");
				}
				if(bodega !=0 && (idBodega!=null && idBodega > 0)){
					consulta.append(" and t.id_bodega = "+idBodega+" "+" ");
				}
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					consulta.append("  and to_number(TO_CHAR(to_date(t.fecha_oficio, 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					consulta.append("and TO_DATE(t.fecha_oficio, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}
			} else if(bodega !=0 && (idBodega!=null && idBodega > 0)){
				consulta.append(" where t.id_bodega = "+idBodega+" "+" ");				
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					consulta.append("  and to_number(TO_CHAR(to_date(t.fecha_oficio, 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					consulta.append("and TO_DATE(t.fecha_oficio, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}
			}else{
				if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
					consulta.append("  where to_number(TO_CHAR(to_date(t.fecha_oficio, 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
					.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
				}else if ((fechaInicio != null && !fechaInicio.equals(""))){
					consulta.append("where TO_DATE(t.fecha_oficio, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
				}				
			}
			
			if(pagina==0){				
				consulta.insert(0, select.toString()+"\n"+from+" ").append(groupBy.toString()+" ").append(orderBy.toString());
			}else{				
				consulta.insert(0,select.toString()+"\n"+from+" ").append(groupBy.toString()+" ").append(orderBy.toString()).append(" limit ").append(numRegistros).append(" offset ").append(inicio);
			}
		}else{
			if(programa !=0 && (idPrograma!=null && idPrograma>0)){
				consulta.append(where+" and t.id_programa = "+idPrograma+" "+" ");
				if(cultivo !=0 && (idCultivo!=null && idCultivo > 0)){
					consulta.append(" and t.id_cultivo = "+idCultivo+" "+" ");
					if(variedad !=0 && (idVariedad!=null && idVariedad > 0)){
						consulta.append(" and t.id_variedad = "+idVariedad+" "+" ");
					}
					if(bodega !=0 && (idBodega!=null && idBodega > 0)){
						consulta.append(" and t.id_bodega = "+idBodega+" "+" ");
					}					
				} else if(bodega !=0 && (idBodega!=null && idBodega > 0)){
					consulta.append(" and t.id_bodega = "+idBodega+" "+" ");
				}
			}else if (cultivo !=0 && (idCultivo!=null && idCultivo > 0)){
				consulta.append(where+" and t.id_cultivo = "+idCultivo+" "+" ");
				if(variedad !=0 && (idVariedad!=null && idVariedad > 0)){
					consulta.append(" and t.id_variedad = "+idVariedad+" "+" ");
				}
				if(bodega !=0 && (idBodega!=null && idBodega > 0)){
					consulta.append(" and t.id_bodega = "+idBodega+" "+" ");
				}	
			} else if(bodega !=0 && (idBodega!=null && idBodega > 0)){
				consulta.append(where+" and t.id_bodega = "+idBodega+" "+" ");				
			} else {
				consulta.append(where+" ");
			}
			if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
				consulta.append("  and to_number(TO_char(t.fecha_pago, 'YYYYMMDD'), '99999999') between to_number(TO_CHAR(to_date('").append(fechaInicio).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')")
				.append(" and to_number(TO_CHAR(to_date('").append(fechaFin).append("', 'DD-MM-YYYY'), 'YYYYMMDD'), '99999999')");
			}else if ((fechaInicio != null && !fechaInicio.equals(""))){
				consulta.append("and TO_DATE(t.fecha_pago, 'DD-MM-YYYY')='").append(fechaInicio).append("'");
			}
			if(pagina==0){
				consulta.insert(0,select.toString()+"\n"+from+" ").append(groupBy.toString()+" ").append(orderBy.toString());
			}else{
				consulta.insert(0,select.toString()+"\n"+from+" ").append(groupBy.toString()+" ").append(orderBy.toString()).append(" limit ").append(numRegistros).append(" offset ").append(inicio);;
			}
				
		}
		System.out.println("DINAMICO "+consulta.toString());
		lista =  session.createSQLQuery(consulta.toString()).list();
		
		return lista;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<ReporteGlobalV> consultaReporteGlobalPagos(String fechaInicio, String fechaFin)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteGlobalV> lst = null;
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}		
		consulta.insert(0, "From ReporteGlobalV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}	

	@SuppressWarnings("unchecked")
	public List<ReporteProgramaCompradorV> consultaReporteProgramaCompradorPagos(String fechaInicio, String fechaFin )throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteProgramaCompradorV> lst = null;
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}		
		consulta.insert(0, "From ReporteProgramaCompradorV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}	

	@SuppressWarnings("unchecked")
	public List<ReporteGlobalV> insolatedConsultaReporteGlobalPagos(String fechaInicio, String fechaFin)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteGlobalV> lst = null;
		Session s = null;
		try{					
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
				if(consulta.length()>0){
					consulta.append(" and  (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
							.append(" and '").append(fechaFin).append("'");
				}else{
					consulta.append(" where (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
							.append(" and '").append(fechaFin).append("'");
				}
			}else{
				if(fechaInicio != null && !fechaInicio.equals("")){
					if(consulta.length()>0){
						consulta.append(" and (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
					}else{
						consulta.append("where (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
					}
				}
			}					
			consulta.insert(0, "From ReporteGlobalV ");
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
	public List<ReporteProgramaCompradorV> insolatedConsultaReporteProgramaCompradorPagos(String fechaInicio, String fechaFin)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteProgramaCompradorV> lst = null;
		Session s = null;
		try{					
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
				if(consulta.length()>0){
					consulta.append(" and  (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
							.append(" and '").append(fechaFin).append("'");
				}else{
					consulta.append(" where (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
							.append(" and '").append(fechaFin).append("'");
				}
			}else{
				if(fechaInicio != null && !fechaInicio.equals("")){
					if(consulta.length()>0){
						consulta.append(" and (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
					}else{
						consulta.append("where (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
					}
				}
			}								
			consulta.insert(0, "From ReporteProgramaCompradorV ");
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

	public Double consultaReporteGlobalTotales(int campo, String fechaInicio, String fechaFin)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		Double lst = null;
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}										
		switch (campo) {
		case 1:
			consulta.insert(0, "select sum(tramitados) From reporte_global_v ");
			break;
		case 2:
			consulta.insert(0, "select sum(volumen_tramitado) From reporte_global_v ");
			break;
		case 3:
			consulta.insert(0, "select sum(importe_tramitado) From reporte_global_v ");
			break;
		case 4:
			consulta.insert(0, "select sum(pagados) From reporte_global_v ");
			break;
		case 5:
			consulta.insert(0, "select sum(volumen_pagado) From reporte_global_v ");
			break;
		case 6:
			consulta.insert(0, "select sum(importe_pagado) From reporte_global_v ");
			break;
		case 7:
			consulta.insert(0, "select sum(rechazados) From reporte_global_v ");
			break;
		case 8:
			consulta.insert(0, "select sum(volumen_rechazado) From reporte_global_v ");
			break;
		case 9:
			consulta.insert(0, "select sum(importe_rechazado) From reporte_global_v ");
			break;
		case 10:
			consulta.insert(0, "select sum(pendientes) From reporte_global_v ");
			break;
		case 11:
			consulta.insert(0, "select sum(volumen_pendiente) From reporte_global_v ");
			break;
		case 12:
			consulta.insert(0, "select sum(importe_pendiente) From reporte_global_v ");
			break;
		}
		lst= Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());	
		return lst;
	}	

	public Double consultaReporteProgramaTotales(int campo, String fechaInicio, String fechaFin)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		Double lst = null;
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}										
		switch (campo) {
		case 1:
			consulta.insert(0, "select sum(tramitados) From reporte_programa_comprador_v ");
			break;
		case 2:
			consulta.insert(0, "select sum(volumen_tramitado) From reporte_programa_comprador_v ");
			break;
		case 3:
			consulta.insert(0, "select sum(importe_tramitado) From reporte_programa_comprador_v ");
			break;
		case 4:
			consulta.insert(0, "select sum(pagados) From reporte_programa_comprador_v ");
			break;
		case 5:
			consulta.insert(0, "select sum(volumen_pagado) From reporte_programa_comprador_v ");
			break;
		case 6:
			consulta.insert(0, "select sum(importe_pagado) From reporte_programa_comprador_v ");
			break;
		case 7:
			consulta.insert(0, "select sum(rechazados) From reporte_programa_comprador_v ");
			break;
		case 8:
			consulta.insert(0, "select sum(volumen_rechazado) From reporte_programa_comprador_v ");
			break;
		case 9:
			consulta.insert(0, "select sum(importe_rechazado) From reporte_programa_comprador_v ");
			break;
		case 10:
			consulta.insert(0, "select sum(pendientes) From reporte_programa_comprador_v ");
			break;
		case 11:
			consulta.insert(0, "select sum(volumen_pendiente) From reporte_programa_comprador_v ");
			break;
		case 12:
			consulta.insert(0, "select sum(importe_pendiente) From reporte_programa_comprador_v ");
			break;
		}
		lst= Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());	
		return lst;
	}	
	

	/*@SuppressWarnings("unchecked")
	public List<ReporteConcentradoPagosV> consultaReporteConcentradoPagos(int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteConcentradoPagosV> lst = null;

		if (ejercicio != 0 && ejercicio != -1){
			consulta.append("where anio = ").append(ejercicio);
		}
		
		consulta.insert(0, "From ReporteConcentradoPagosV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}*/	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ReporteConcentradoPagosV> consultaReporteConcentradoPagos(int ejercicio)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ReporteConcentradoPagosV> lst = null;		
		consulta.insert(0, "select * from concentrado_pagos_trimestres_v('"+ejercicio+"')");
		lst = session.createSQLQuery(consulta.toString()).addEntity(ReporteConcentradoPagosV.class).list();
		
		
		return lst;
	}
	

	public Double consultaReporteConcetradoTotales(int campo, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		Double lst = null;
		if (ejercicio != 0 && ejercicio != -1){
			consulta.append("where anio = ").append(ejercicio);
		}
		switch (campo) {
		case 1:
			consulta.insert(0, "select  COALESCE(sum(volumen_1er_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 2:
			consulta.insert(0, "select   COALESCE(sum(importe_1er_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 3:
			consulta.insert(0, "select  COALESCE(sum(volumen_2do_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 4:
			consulta.insert(0, "select  COALESCE(sum(importe_2do_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 5:
			consulta.insert(0, "select  COALESCE(sum(volumen_3er_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 6:
			consulta.insert(0, "select  COALESCE(sum(importe_3er_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 7:
			consulta.insert(0, "select  COALESCE(sum(volumen_4to_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		case 8:
			consulta.insert(0, "select  COALESCE(sum(importe_4to_trimestre),0) from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			break;
		}
		lst= Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReporteConcentradoPagosV> insolatedConsultaReporteConcentradoPagos(int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteConcentradoPagosV> lst = null;
		Session s = null;
		try{					
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			/*if (ejercicio != 0 && ejercicio != -1){
				consulta.append("where anio = ").append(ejercicio);
			}*/		
			consulta.insert(0, "select * from concentrado_pagos_trimestres_v('"+ejercicio+"')");
			lst = s.createSQLQuery(consulta.toString()).addEntity(ReporteConcentradoPagosV.class).list();
			//consulta.insert(0, "From ReporteConcentradoPagosV ");
			//lst= s.createQuery(consulta.toString()).list();
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
	public List<ReporteDetConcentradoPagosV> consultaReporteDetConcentradoPagos(int idPrograma, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteDetConcentradoPagosV> lst = null;
		consulta.insert(0, "select * from concentrado_det_pagos_trimestres_v("+ejercicio+","+idPrograma+")");
		lst = session.createSQLQuery(consulta.toString()).addEntity(ReporteDetConcentradoPagosV.class).list();
		
		return lst;
	}	

	@SuppressWarnings("unchecked")
	public List<ReporteDetConcentradoPagosV> consultaReporteDetConcentradoPagos(int idPrograma, int ejercicio, int trimestre)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteDetConcentradoPagosV> lst = null;
		consulta.insert(0, "select * from concentrado_det_pagos_trimestres_v("+ejercicio+","+idPrograma+","+trimestre+")");
		lst = session.createSQLQuery(consulta.toString()).addEntity(ReporteDetConcentradoPagosV.class).list();
		
		return lst;
	}	

	public Double consultaReporteDetConcetradoTotales(int campo, int idPrograma, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		Double lst = null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where id_programa = ").append(idPrograma);
		}
		
		if (ejercicio != 0 && ejercicio != -1){
			if(consulta.length()>0){
				consulta.append(" and anio=").append(ejercicio);
			}else{
				consulta.append("where anio=").append(ejercicio);
			}
		}
		switch (campo) {
		case 1:
			//select sum(volumen_1er_trimestre) From concentrado_det_pagos_trimestres_v (2013,28)
			consulta.insert(0, "select sum(volumen_1er_trimestre) From concentrado_det_pagos_trimestres_v("+ejercicio+", "+idPrograma+")");
			break;
		case 2:
			consulta.insert(0, "select sum(importe_1er_trimestre) From concentrado_det_pagos_trimestres_v("+ejercicio+", "+idPrograma+")");
			break;
		case 3:
			consulta.insert(0, "select sum(volumen_2do_trimestre) From concentrado_det_pagos_trimestres_v ("+ejercicio+", "+idPrograma+")");
			break;
		case 4:
			consulta.insert(0, "select sum(importe_2do_trimestre) From concentrado_det_pagos_trimestres_v ("+ejercicio+", "+idPrograma+")");
			break;
		case 5:
			consulta.insert(0, "select sum(volumen_3er_trimestre) From concentrado_det_pagos_trimestres_v ("+ejercicio+", "+idPrograma+")");
			break;
		case 6:
			consulta.insert(0, "select sum(importe_3er_trimestre) From concentrado_det_pagos_trimestres_v ("+ejercicio+", "+idPrograma+")");
			break;
		case 7:
			consulta.insert(0, "select sum(volumen_4to_trimestre) From concentrado_det_pagos_trimestres_v ("+ejercicio+", "+idPrograma+")");
			break;
		case 8:
			consulta.insert(0, "select sum(importe_4to_trimestre) From concentrado_det_pagos_trimestres_v ("+ejercicio+", "+idPrograma+")");
			break;
		}
		lst= Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());	
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<ReporteDetConcentradoPagosV> insolatedConsultaReporteDetConcentradoPagos(int idPrograma, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteDetConcentradoPagosV> lst = null;
		Session s = null;
		try{					
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			/*if (idPrograma != 0 && idPrograma != -1){
				consulta.append("where idPrograma = ").append(idPrograma);
			}
			
			if (ejercicio != 0 && ejercicio != -1){
				if(consulta.length()>0){
					consulta.append(" and ejercicio=").append(ejercicio);
				}else{
					consulta.append("where ejercicio=").append(ejercicio);
				}
			}*/
	
			consulta.insert(0, "select * from concentrado_det_pagos_trimestres_v("+ejercicio+","+idPrograma+")");
			lst = s.createSQLQuery(consulta.toString()).addEntity(ReporteDetConcentradoPagosV.class).list();
			//lst= s.createQuery(consulta.toString()).list();
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
	public List<ReporteDetConcentradoPagosV> insolatedConsultaReporteDetConcentradoPagos(int idPrograma, int ejercicio, int trimestre)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteDetConcentradoPagosV> lst = null;
		Session s = null;
		try{					
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			/*if (idPrograma != 0 && idPrograma != -1){
				consulta.append("where idPrograma = ").append(idPrograma);
			}
			
			if (ejercicio != 0 && ejercicio != -1){
				if(consulta.length()>0){
					consulta.append(" and ejercicio=").append(ejercicio);
				}else{
					consulta.append("where ejercicio=").append(ejercicio);
				}
			}*/
	
			consulta.insert(0, "select * from concentrado_det_pagos_trimestres_v("+ejercicio+","+idPrograma+","+trimestre+")");
			lst = s.createSQLQuery(consulta.toString()).addEntity(ReporteDetConcentradoPagosV.class).list();
			//lst= s.createQuery(consulta.toString()).list();
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
	public List<ReporteDetConcentradoPagosEtapasV> consultaReporteDetConcentradoPagosEtapas(int idPrograma, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteDetConcentradoPagosEtapasV> lst = null;

		/*if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}*/
		
		
		consulta.insert(0, "select * from concentrado_det_pagos_trimestres_etapas_v("+ejercicio+") where id_programa = "+idPrograma);
		lst = session.createSQLQuery(consulta.toString()).addEntity(ReporteDetConcentradoPagosEtapasV.class).list();
		
		/*if (ejercicio != 0 && ejercicio != -1){
			if(consulta.length()>0){
				consulta.append(" and ejercicio=").append(ejercicio);
			}else{
				consulta.append("where ejercicio=").append(ejercicio);
			}
		}*/

		//consulta.insert(0, "From ReporteDetConcentradoPagosEtapasV ").append(" order by cartaAdhesion");
		//lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}	

	public Double consultaReporteDetConcetradoEtapasTotales(int campo, int idPrograma, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		Double lst = null;
		/*if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where id_programa = ").append(idPrograma);
		}
		
		if (ejercicio != 0 && ejercicio != -1){
			if(consulta.length()>0){
				consulta.append(" and anio=").append(ejercicio);
			}else{
				consulta.append("where anio=").append(ejercicio);
			}
		}*/
		switch (campo) {
		case 1:
			consulta.insert(0, "select sum(volumen_1er_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 11:
			consulta.insert(0, "select sum(volumen_1er_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 12:
			consulta.insert(0, "select sum(volumen_1er_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 13:
			consulta.insert(0, "select sum(volumen_1er_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 14:
			consulta.insert(0, "select sum(volumen_1er_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 2:
			consulta.insert(0, "select sum(importe_1er_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 21:
			consulta.insert(0, "select sum(importe_1er_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 22:
			consulta.insert(0, "select sum(importe_1er_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 23:
			consulta.insert(0, "select sum(importe_1er_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 24:
			consulta.insert(0, "select sum(importe_1er_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 3:
			consulta.insert(0, "select sum(volumen_2do_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 31:
			consulta.insert(0, "select sum(volumen_2do_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 32:
			consulta.insert(0, "select sum(volumen_2do_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 33:
			consulta.insert(0, "select sum(volumen_2do_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 34:
			consulta.insert(0, "select sum(volumen_2do_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 4:
			consulta.insert(0, "select sum(importe_2do_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 41:
			consulta.insert(0, "select sum(importe_2do_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 42:
			consulta.insert(0, "select sum(importe_2do_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 43:
			consulta.insert(0, "select sum(importe_2do_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 44:
			consulta.insert(0, "select sum(importe_2do_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 5:
			consulta.insert(0, "select sum(volumen_3er_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 51:
			consulta.insert(0, "select sum(volumen_3er_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 52:
			consulta.insert(0, "select sum(volumen_3er_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 53:
			consulta.insert(0, "select sum(volumen_3er_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 54:
			consulta.insert(0, "select sum(volumen_3er_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;			
		case 6:
			consulta.insert(0, "select sum(importe_3er_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 61:
			consulta.insert(0, "select sum(importe_3er_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 62:
			consulta.insert(0, "select sum(importe_3er_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 63:
			consulta.insert(0, "select sum(importe_3er_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 64:
			consulta.insert(0, "select sum(importe_3er_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 7:
			consulta.insert(0, "select sum(volumen_4to_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 71:
			consulta.insert(0, "select sum(volumen_4to_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 72:
			consulta.insert(0, "select sum(volumen_4to_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 73:
			consulta.insert(0, "select sum(volumen_4to_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 74:
			consulta.insert(0, "select sum(volumen_4to_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;			
		case 8:
			consulta.insert(0, "select sum(importe_4to_trimestre_etapa1) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 81:
			consulta.insert(0, "select sum(importe_4to_trimestre_etapa2) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 82:
			consulta.insert(0, "select sum(importe_4to_trimestre_etapa3) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 83:
			consulta.insert(0, "select sum(importe_4to_trimestre_etapa4) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;
		case 84:
			consulta.insert(0, "select sum(importe_4to_trimestre_etapa5) From concentrado_det_pagos_trimestres_etapas_v ("+ejercicio+") where id_programa = "+idPrograma);
			break;			
		}
		lst= Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());	
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<ReporteDetConcentradoPagosEtapasV> insolatedConsultaReporteDetConcentradoPagosEtapas(int idPrograma, int ejercicio)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ReporteDetConcentradoPagosEtapasV> lst = null;
		Session s = null;
		try{					
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			/*if (idPrograma != 0 && idPrograma != -1){
				consulta.append("where idPrograma = ").append(idPrograma);
			}
			
			if (ejercicio != 0 && ejercicio != -1){
				if(consulta.length()>0){
					consulta.append(" and ejercicio=").append(ejercicio);
				}else{
					consulta.append("where ejercicio=").append(ejercicio);
				}
			}*/
	
			consulta.insert(0, "select * from concentrado_det_pagos_trimestres_etapas_v("+ejercicio+") where id_programa = "+idPrograma);
			lst = s.createSQLQuery(consulta.toString()).addEntity(ReporteDetConcentradoPagosEtapasV.class).list();
			
			/*consulta.insert(0, "From ReporteDetConcentradoPagosEtapasV ").append(" order by cartaAdhesion");
			lst= s.createQuery(consulta.toString()).list();*/
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
	public List<RendicionCuentasV> consultaReporteRendicionCuentasCID(Date periodoInicial, Date periodoFinal) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RendicionCuentasV> lst=null;
		
		consulta.append(" where idSuri is not null ");
		
		if (periodoInicial != null  && !periodoInicial.equals("") && periodoFinal != null &&  !periodoFinal.equals("")){
			if(consulta.length()>0){
				consulta.append(" and TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
						.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
			}else{
				consulta.append(" where TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
						.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
			}
		}
				
		consulta.append(" order by noCarta, idEstado, idPago, estatusMonto").insert(0, "From RendicionCuentasV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<RendicionCuentasV> isolatedconsultaReporteRendicionCuentasCID(Date periodoInicial, Date periodoFinal) throws JDBCException {
		Session s = null;
		StringBuilder consulta= new StringBuilder();
		List<RendicionCuentasV> lst=null;

		try{			
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			
			consulta.append(" where idSuri is not null ");
			if (periodoInicial != null  && !periodoInicial.equals("") && periodoFinal != null &&  !periodoFinal.equals("")){
				if(consulta.length()>0){
					consulta.append(" and TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
							.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
				}else{
					consulta.append(" where TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
							.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
				}
			}
					
			consulta.append(" order by noCarta, idEstado, idPago, estatusMonto").insert(0, "From RendicionCuentasV ");
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
	public List<RendicionCuentasV> consultaReporteRendicionCuentasSID(Date periodoInicial, Date periodoFinal) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RendicionCuentasV> lst=null;
		
		consulta.append(" where idSuri is null ");
		
		if (periodoInicial != null  && !periodoInicial.equals("") && periodoFinal != null &&  !periodoFinal.equals("")){
			if(consulta.length()>0){
				consulta.append(" and TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
						.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
			}else{
				consulta.append(" where TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
						.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
			}
		}
				
		consulta.append(" order by noCarta, idEstado, idPago, estatusMonto").insert(0, "From RendicionCuentasV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<RendicionCuentasV> isolatedconsultaReporteRendicionCuentasSID(Date periodoInicial, Date periodoFinal) throws JDBCException {
		Session s = null;
		StringBuilder consulta= new StringBuilder();
		List<RendicionCuentasV> lst=null;

		try{			
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			
			consulta.append(" where idSuri is null ");
			if (periodoInicial != null  && !periodoInicial.equals("") && periodoFinal != null &&  !periodoFinal.equals("")){
				if(consulta.length()>0){
					consulta.append(" and TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
							.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
				}else{
					consulta.append(" where TO_NUMBER(TO_CHAR(fecha,'YYYYMMDD'),'99999999') between ").append(new SimpleDateFormat("yyyyMMdd").format(periodoInicial))
							.append(" and ").append(new SimpleDateFormat("yyyyMMdd").format(periodoFinal));
				}
			}
					
			consulta.append(" order by noCarta, idEstado, idPago, estatusMonto").insert(0, "From RendicionCuentasV ");
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

}
