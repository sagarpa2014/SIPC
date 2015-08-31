package mx.gob.comer.sipc.dao;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.domain.catalogos.CatCriteriosValidacion;
import mx.gob.comer.sipc.domain.catalogos.Predios;
import mx.gob.comer.sipc.domain.catalogos.Productores;
import mx.gob.comer.sipc.domain.catalogos.Relaciones;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcompras;
import mx.gob.comer.sipc.domain.transaccionales.BitacoraRelcomprasDetalle;
import mx.gob.comer.sipc.domain.transaccionales.CamposRelacion;
import mx.gob.comer.sipc.domain.transaccionales.CamposRelacionCertificados;
import mx.gob.comer.sipc.domain.transaccionales.CamposRelacionMaritima;
import mx.gob.comer.sipc.domain.transaccionales.CamposRelacionTerrestre;
import mx.gob.comer.sipc.domain.transaccionales.CamposRelacionVentas;
import mx.gob.comer.sipc.domain.transaccionales.ComprasContrato;
import mx.gob.comer.sipc.domain.transaccionales.ComprasDatosParticipante;
import mx.gob.comer.sipc.domain.transaccionales.ComprasDatosProductor;
import mx.gob.comer.sipc.domain.transaccionales.ComprasEntradaBodega;
import mx.gob.comer.sipc.domain.transaccionales.ComprasFacVenta;
import mx.gob.comer.sipc.domain.transaccionales.ComprasFacVentaGlobal;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPagoProdAxc;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPagoProdSinAxc;
import mx.gob.comer.sipc.domain.transaccionales.ComprasPredio;
import mx.gob.comer.sipc.domain.transaccionales.CriteriosValidacionTransaccional;
import mx.gob.comer.sipc.domain.transaccionales.GruposRelacion;
import mx.gob.comer.sipc.domain.transaccionales.IniEsquemaRelacion;
import mx.gob.comer.sipc.domain.transaccionales.PersonalizacionRelaciones;
import mx.gob.comer.sipc.domain.transaccionales.ProgramaRelacionCiclos;
import mx.gob.comer.sipc.domain.transaccionales.ProgramaRelacionCultivos;
import mx.gob.comer.sipc.domain.transaccionales.RelacionComprasTMP;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.ComprasBodegaTicketV;
import mx.gob.comer.sipc.vistas.domain.ComprasDatosParticipanteV;
import mx.gob.comer.sipc.vistas.domain.ComprasDatosProductorV;
import mx.gob.comer.sipc.vistas.domain.ComprasMaxCamposV;
import mx.gob.comer.sipc.vistas.domain.ComprasPredioV;
import mx.gob.comer.sipc.vistas.domain.CriteriosValidacionRelacionDFV;
import mx.gob.comer.sipc.vistas.domain.GrupoPorRelacion;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionCertificadosV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionDefaultV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionMaritimaV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionTerrestreV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionVentasV;
import mx.gob.comer.sipc.vistas.domain.IniEsquemaRelacionV;
import mx.gob.comer.sipc.vistas.domain.PersonalizacionRelacionesProgramaV;
import mx.gob.comer.sipc.vistas.domain.PersonalizacionRelacionesV;
import mx.gob.comer.sipc.vistas.domain.ProgramaRelacionCiclosV;
import mx.gob.comer.sipc.vistas.domain.ProgramaRelacionCultivosV;
import mx.gob.comer.sipc.vistas.domain.ProgramasRelacionV;
import mx.gob.comer.sipc.vistas.domain.RelacionPorFolioCABodega;
import mx.gob.comer.sipc.vistas.domain.relaciones.*;

import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class RelacionesDAO {
	

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
		
	public RelacionesDAO() {
		
	}
	public RelacionesDAO(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Relaciones> consultaRelacion(int idRelacion)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<Relaciones> lst = null;
		
		if (idRelacion != 0 && idRelacion != -1){
			consulta.append("where idRelacion = ").append(idRelacion);
		}
		
		consulta.insert(0, "From Relaciones ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	public List<GruposCamposRelacionDefaultV> consultaGruposCamposRelacionDefaultV(int idRelacion, String tipoSeccion) throws JDBCException {
		return consultaGruposCamposRelacionDefaultV(idRelacion, tipoSeccion, 0);
	}
	
	public List<GruposCamposRelacionDefaultV> consultaGruposCamposRelacionDefaultV(long idGpoCampoRelacionD) throws JDBCException {
		return consultaGruposCamposRelacionDefaultV(0, null, idGpoCampoRelacionD);
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionDefaultV> consultaGruposCamposRelacionDefaultV(int idRelacion, String tipoSeccion, long idGpoCampoRelacionD)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<GruposCamposRelacionDefaultV> lst = null;
		
		if (idRelacion != 0 && idRelacion != -1){
			consulta.append("where idRelacion = ").append(idRelacion);
		}

		if (tipoSeccion != null && !tipoSeccion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoSeccion='").append(tipoSeccion).append("'");
			}else{
				consulta.append("where tipoSeccion='").append(tipoSeccion).append("'");
			}
		}	
		if (idGpoCampoRelacionD != 0 && idGpoCampoRelacionD != -1){
			if(consulta.length()>0){
				consulta.append(" and idGpoCampoRelacionD =").append(idGpoCampoRelacionD);
			}else{
				consulta.append("where idGpoCampoRelacionD =").append(idGpoCampoRelacionD);
			}
		}
		consulta.insert(0, "From GruposCamposRelacionDefaultV ").append(" ORDER BY posicionGrupo, posicionCampo");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<GruposCamposRelacionV> consultaGruposCampostV(long idCampoRelacion, String tipoSeccion)throws  JDBCException{
		return consultaGruposCampostV(idCampoRelacion, -1, -1, -1, -1, tipoSeccion, "", "");
	}
	public List<GruposCamposRelacionV> consultaGruposCampostV(long idCampoRelacion, int idTipoRelacion, long idPerRel, int idGrupo, int idCampo, String tipoSeccion)throws  JDBCException{
		return consultaGruposCampostV(idCampoRelacion, idTipoRelacion, idPerRel, idGrupo, idCampo, tipoSeccion, "", "");
	}
	
	public List<GruposCamposRelacionV> consultaGruposCampostV(long idCampoRelacion, int idTipoRelacion, long idPerRel, int idGrupo, int idCampo, String tipoSeccion, String tipoDato)throws  JDBCException{
		return consultaGruposCampostV(idCampoRelacion, idTipoRelacion, idPerRel, idGrupo, idCampo, tipoSeccion, "", tipoDato);
	}
	
	public List<GruposCamposRelacionV> consultaGruposCampostV(long idPerRel, int idGrupo)throws  JDBCException{
		return consultaGruposCampostV(-1, -1, idPerRel, idGrupo, -1, null, "", null);
	}
	public List<GruposCamposRelacionV> consultaGruposCampostV(long idPerRel, int idGrupo, int idCampo)throws  JDBCException{
		return consultaGruposCampostV(-1, -1, idPerRel, idGrupo, idCampo, null, "", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionV> consultaGruposCampostV(long idCampoRelacion, int idTipoRelacion, long idPerRel, int idGrupo, int idCampo, String tipoSeccion, String tipoGrupo, String tipoDato)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<GruposCamposRelacionV> lst = null;
		
		if (idCampoRelacion != 0 && idCampoRelacion != -1){
			consulta.append("where idCampoRelacion = ").append(idCampoRelacion);
		}
		if (idTipoRelacion != 0 && idTipoRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idTipoRelacion=").append(idTipoRelacion);
			}else{
				consulta.append("where idTipoRelacion=").append(idTipoRelacion);
			}
		}

		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append("where idPerRel=").append(idPerRel);
			}
		}
		if (idGrupo != 0 && idGrupo != -1){
			if(consulta.length()>0){
				consulta.append(" and idGrupo=").append(idGrupo);
			}else{
				consulta.append("where idGrupo=").append(idGrupo);
			}
		}
		
		if (idCampo != 0 && idCampo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCampo =").append(idCampo);
			}else{
				consulta.append("where idCampo =").append(idCampo);
			}
		}
		
		if (tipoSeccion !=  null && !tipoSeccion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoSeccion ='").append(tipoSeccion).append("'");
			}else{
				consulta.append("where tipoSeccion ='").append(tipoSeccion).append("'");
			}
		}
		
		if (tipoGrupo !=  null && !tipoGrupo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoGrupo ='").append(tipoGrupo).append("'");
			}else{
				consulta.append("where tipoGrupo ='").append(tipoGrupo).append("'");
			}
		}
		consulta.insert(0, "From GruposCamposRelacionV ").append(" ORDER BY posicionGrupo, posicionCampo");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<PersonalizacionRelacionesV> consultaPersonalizacionRelaciones(long idPerRel)throws  JDBCException{
		return consultaPersonalizacionRelaciones(idPerRel, 0,null, null, null, null,0);
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonalizacionRelacionesV> consultaPersonalizacionRelaciones(long idPerRel, int idRelacion,  String cicloAgricola, String cultivos, String fechaInicio, String fechaFin, long idIniEsquemaRelacion)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<PersonalizacionRelacionesV> lst = null;
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}

		if (idRelacion != 0 && idRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idTipoRelacion=").append(idRelacion);
			}else{
				consulta.append("where idTipoRelacion=").append(idRelacion);
			}
		}
		
		if (cicloAgricola != null && !cicloAgricola.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and cicloAgricola='").append(cicloAgricola).append("'");
			}else{
				consulta.append("where cicloAgricola='").append(cicloAgricola).append("'");
			}
		}
		if (cultivos != null &&  !cultivos.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and cultivos= '").append(cultivos).append("'");
			}else{
				consulta.append("where cultivos='").append(cultivos).append("'");
			}
		}
		
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		
		if (idIniEsquemaRelacion != 0 &&  idIniEsquemaRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idIniEsquemaRelacion=").append(idIniEsquemaRelacion);
			}else{
				consulta.append("where idIniEsquemaRelacion=").append(idIniEsquemaRelacion);
			}
		}
		
		consulta.insert(0, "From PersonalizacionRelacionesV ").append(" order by nombreEsquema, relacion");
		lst= session.createQuery(consulta.toString()).list();
				
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposRelacion> consultaGruposRelacion(long idPerRel)throws  JDBCException{ 
		StringBuilder consulta= new StringBuilder();
		List<GruposRelacion> lst = null;
		
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}

		consulta.insert(0, "From GruposRelacion ");
		lst= session.createQuery(consulta.toString()).list();

		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CamposRelacion> consultaCamposRelacion(long idPerRel)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<CamposRelacion> lst = null;
		
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}

		consulta.insert(0, "From CamposRelacion ");
		lst= session.createQuery(consulta.toString()).list();

		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GrupoPorRelacion> consultaGrupoPorRelacion(long idPerRel)throws  JDBCException{
		List<GrupoPorRelacion> lst = null;
		String consulta = "select g.id_grupo,  g.grupo, g.posicion_grupo,  g.visible, count(g.id_grupo) as  numero_campos " +
				"from grupos_campos_relacion_v g where g.tipo_seccion='DET' and id_per_rel = " + idPerRel +
				" group by g.id_grupo, g.grupo, g.posicion_grupo,  g.visible order by g.posicion_grupo";  
		lst= session.createSQLQuery(consulta.toString()).addEntity(GrupoPorRelacion.class).list();
		
		return lst;
	}
		
	
	
	
	@SuppressWarnings("unchecked")
	public List<ProgramaRelacionCultivosV> consultaCultivosPerRel(long idPerRel)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ProgramaRelacionCultivosV> lst=null;
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}
		consulta.insert(0, "From ProgramaRelacionCultivosV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramaRelacionCiclosV> consultaCiclosEjerPerRel(long idPerRel)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ProgramaRelacionCiclosV> lst=null;
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}
		consulta.insert(0, "From ProgramaRelacionCiclosV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	public int borrarPartProd(long idCompPer, long idCompProd)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("DELETE FROM compras_predio WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_contrato WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_entrada_bodega WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_fac_venta WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_fac_venta_global WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_pago_prod_sin_axc WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_pago_prod_axc WHERE id_comp_prod= ").append(idCompProd).append(";");
			hql.append("DELETE FROM compras_pago_prod_sin_axc WHERE id_comp_prod= ").append(idCompProd).append("; ");
			hql.append("DELETE FROM compras_comp_productor WHERE id_comp_per= ").append(idCompPer).append(" and id_comp_prod =").append(idCompProd).append("; ");
			//hql.append("DELETE FROM compras_datos_participante WHERE id_comp_per= ").append(idCompPer);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	
	
	public int borrarFolioMaritima(BigInteger folioSerial)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM campos_relacion_maritima WHERE folio_registro= ").append(folioSerial);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
		
	
	@SuppressWarnings("unchecked")
	public List<ProgramaRelacionCiclosV> consultaIdPgrCiclo(Integer idCiclo, Integer ejercicio, long idPerRel)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ProgramaRelacionCiclosV> lst=null;
		if (idCiclo != 0 && idCiclo != -1){
			consulta.append("where idCiclo = ").append(idCiclo);
		}
		if (ejercicio != 0 && ejercicio != -1){
			if(consulta.length()>0){
				consulta.append(" and ejercicio =").append(ejercicio);
			}else{
				consulta.append("where ejercicio =").append(ejercicio);
			}
		}
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel =").append(idPerRel);
			}else{
				consulta.append("where idPerRel =").append(idPerRel);
			}
		}
		consulta.insert(0, "From ProgramaRelacionCiclosV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	public int registraFolioCartaCompras(String folioCartaAdhesion,  long idComPer)throws JDBCException {
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("update  compras_datos_participante set folio_carta_adhesion = '").append(folioCartaAdhesion).append("', ").append(" id_estatus_compras_datos_par=2")
			.append(" WHERE id_comp_per=").append(idComPer);
			
			updatedEntities  = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return updatedEntities;
	}
	
	
	
	
	//
	
	//CERTIFICADOS DE DEPOSITO	
	public List<CamposRelacionCertificados> consultaCamposCertificados(Integer idComprador, Integer idCultivo, long idPgrCiclo, String claveBodega,int razonSocialAlmacen)throws  JDBCException{
		return consultaCamposCertificados(-1, idComprador, idCultivo, idPgrCiclo, null, claveBodega, razonSocialAlmacen);
	}
	
	@SuppressWarnings("unchecked")
	public List<CamposRelacionCertificados> consultaCamposCertificados(long idCampoRelacion, Integer idComprador, Integer idPgrCultivo, long idPgrCiclo, String folioCertificado, String claveBodega, int razonSocialAlmacen)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<CamposRelacionCertificados> lst=null;
		
		if (idCampoRelacion != 0 && idCampoRelacion != -1){
			consulta.append("where idCampoRelacion = ").append(idCampoRelacion);
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador =").append(idComprador);
			}else{
				consulta.append("where idComprador =").append(idComprador);
			}
		}
		if (idPgrCultivo != 0 && idPgrCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCultivo =").append(idPgrCultivo);
			}else{
				consulta.append("where idPgrCultivo =").append(idPgrCultivo);
			}
		}
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		if (folioCertificado != null && !folioCertificado.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio ='").append(folioCertificado).append("'");
			}else{
				consulta.append("where folio =").append(folioCertificado).append("'");
			}
		}	
		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega ='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega ='").append(claveBodega).append("'");
			}
		}	
		
		if (razonSocialAlmacen != 0 && razonSocialAlmacen != -1){
			if(consulta.length()>0){
				consulta.append(" and razonSocialAlmacen =").append(razonSocialAlmacen);
			}else{
				consulta.append("where razonSocialAlmacen =").append(razonSocialAlmacen);
			}
		}
		
		consulta.insert(0, "From CamposRelacionCertificados ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public List<GruposCamposRelacionCertificadosV> consultaGruposCamposCertificadosV(String folio, int razonSocialAlmacen)throws  JDBCException{
		return consultaGruposCamposCertificadosV(-1, -1, -1, "", "", -1,  null, folio, false, -1, razonSocialAlmacen);
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionCertificadosV> consultaGruposCamposCertificadosV(int idTipoRelacion,  long idPerRel, Integer idComprador, String folioCartaAdhesion, String claveBodega, int cultivoRegistro, String tipoSeccion, String folio, boolean limit,long idPgrCiclo, int razonSocialAlmacen)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<GruposCamposRelacionCertificadosV> lst = null;
		if (idTipoRelacion != 0 && idTipoRelacion != -1){
			consulta.append("where idTipoRelacion=").append(idTipoRelacion);
		}
		
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append("where idPerRel=").append(idPerRel);
			}
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador=").append(idComprador);
			}else{
				consulta.append("where idComprador=").append(idComprador);
			}
		}
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		if (claveBodega !=  null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega ='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega ='").append(claveBodega).append("'");
			}
		}
		if (cultivoRegistro != 0 && cultivoRegistro != -1){
			if(consulta.length()>0){
				consulta.append(" and cultivoRegistro =").append(cultivoRegistro);
			}else{
				consulta.append("where cultivoRegistro =").append(cultivoRegistro);
			}
		}
		if (tipoSeccion !=  null && !tipoSeccion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoSeccion ='").append(tipoSeccion).append("'");
			}else{
				consulta.append("where tipoSeccion ='").append(tipoSeccion).append("'");
			}
		}		
		if (folio != null && !folio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio = '").append(folio).append("'");
			}else{
				consulta.append("where folio = '").append(folio).append("'");;
			}
		}	
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		if (razonSocialAlmacen != 0 && razonSocialAlmacen != -1){
			if(consulta.length()>0){
				consulta.append(" and razonSocialAlmacen =").append(razonSocialAlmacen);
			}else{
				consulta.append("where razonSocialAlmacen =").append(razonSocialAlmacen);
			}
		}
		
		consulta.insert(0, "From GruposCamposRelacionCertificadosV ").append(" ORDER BY folioCartaAdhesion, claveBodega, folio, posicionGrupo, posicionCampo");    
		if(limit){
			lst = session.createQuery(consulta.toString()).setFirstResult(0).setMaxResults(1).list();   
		}else{
			lst= session.createQuery(consulta.toString()).list();
		} 
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionCertificadosV> consultaRelacionCDByBodegaAlmacen(long idPerRel, int idComprador, String folioCartaAdhesion)throws  JDBCException{
		List<GruposCamposRelacionCertificadosV> lst = null;
		StringBuilder condicion = new StringBuilder();
		if (idPerRel != 0 && idPerRel != -1){
			condicion.append(" where idPerRel = ").append(idPerRel);
		} 
		if (idComprador != 0 && idComprador != -1){
			if(condicion.length()>0){
				condicion.append(" and idComprador =").append(idComprador);
			}else{
				condicion.append(" where idComprador =").append(idComprador);
			}
		}
		
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(condicion.length()>0){
				condicion.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				condicion.append(" where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		 
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionCertificadosV (idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodega, razonSocialAlmacen, almacen, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio )"+
						" from GruposCamposRelacionCertificadosV"+
						condicion.toString()+
						" group by idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodega, razonSocialAlmacen, almacen, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio"+
						" order by ejercicio, cultivoRegistro, cicloLargo, claveBodega,  almacen ";
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
		
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionCertificadosV> consultaRelacionCDByBodegaAlmacenRegistroCA(long idPerRel, int idComprador)throws  JDBCException{
		List<GruposCamposRelacionCertificadosV> lst = null;
		
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionCertificadosV (idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodega, razonSocialAlmacen, almacen, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio )"+
				" from GruposCamposRelacionCertificadosV"+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+ " and folioCartaAdhesion is null"+
				" group by idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodega, razonSocialAlmacen, almacen, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio"+
				" order by ejercicio, cultivoRegistro, cicloLargo, claveBodega,  almacen ";
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	
	}
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionCertificadosV> consultaRelacionBodegaFolioCertificado(long idPerRel, int idComprador, String claveBodega, int cultivoRegistro, long idPgrCiclo,  Integer razonSocialAlmacen)throws  JDBCException{
		List<GruposCamposRelacionCertificadosV> lst = null;
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionCertificadosV  (idPerRel,  idComprador, folioCartaAdhesion, claveBodega, razonSocialAlmacen, almacen, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio, folio)"+
						" from   GruposCamposRelacionCertificadosV "+
						" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+" and claveBodega= '"+claveBodega+"'"+" and razonSocialAlmacen = "+razonSocialAlmacen+
						" and cultivo_registro ="+cultivoRegistro+" and id_pgr_ciclo="+idPgrCiclo+
						" group by idPerRel,  idComprador, folioCartaAdhesion, claveBodega, razonSocialAlmacen, almacen, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio, folio"+
						" order by folio";
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public int registraFolioCartaCertificados(String folioCartaAdhesion, Integer idComprador, long idPerRel, String claveBodega, int cultivoRelacion, long idPgrCiclo, int razonSocialAlmacen)throws JDBCException {
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("update  campos_relacion_certificados set folio_carta_adhesion  = '").append(folioCartaAdhesion).append("'")
			.append(" WHERE id_comprador= ").append(idComprador).append(" AND id_per_rel=").append(idPerRel)
			.append(" AND clave_bodega ='").append(claveBodega).append("'").append(" AND id_pgr_cultivo=").append(cultivoRelacion)
			.append(" and id_pgr_ciclo = ").append(idPgrCiclo).append(" and razon_social_almacen=").append(razonSocialAlmacen);
			
			updatedEntities  = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return updatedEntities;
	}
	
	public int borrarFolioCertificados(long idPerRel, Integer idComprador, String claveBodega, int cultivoRegistro, String folioCertificado, long idPgrCiclo, int razonSocialAlmacen)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM campos_relacion_certificados WHERE id_per_rel= ").append(idPerRel)
			.append(" and id_comprador=").append(idComprador).append(" and id_pgr_cultivo = ").append(cultivoRegistro)
			.append(" and id_pgr_ciclo = ").append(idPgrCiclo).append(" and folio = '"+folioCertificado+"' and razon_social_almacen =").append(razonSocialAlmacen)
			.append(" and claveBodega=").append(claveBodega);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}

	//VENTAS	
	@SuppressWarnings("unchecked")
	public List<CamposRelacionVentas> consultaCamposVentas(long idCampoRelacion, Integer idComprador, Integer idPgrCultivo, long idPgrCiclo, String folioFacVenta)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<CamposRelacionVentas> lst=null;
		
		if (idCampoRelacion != 0 && idCampoRelacion != -1){
			consulta.append("where idCampoRelacion = ").append(idCampoRelacion);
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador =").append(idComprador);
			}else{
				consulta.append("where idComprador =").append(idComprador);
			}
		}
		
		if (idPgrCultivo != 0 && idPgrCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCultivo =").append(idPgrCultivo);
			}else{
				consulta.append("where idPgrCultivo =").append(idPgrCultivo);
			}
		}
		
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		
		if (folioFacVenta != null && !folioFacVenta.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio ='").append(folioFacVenta).append("'");
			}else{
				consulta.append("where folio =").append(folioFacVenta).append("'");
			}
		}	
		consulta.insert(0, "From CamposRelacionVentas ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionVentasV> consultaGruposCamposVentasV(int idTipoRelacion,  long idPerRel, Integer idComprador, String folioCartaAdhesion, Integer idCultivo, String tipoSeccion, String folio, boolean limit, long idPgrCiclo)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<GruposCamposRelacionVentasV> lst = null;
		if (idTipoRelacion != 0 && idTipoRelacion != -1){
			consulta.append("where idTipoRelacion=").append(idTipoRelacion);
		}
		
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append("where idPerRel=").append(idPerRel);
			}
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador=").append(idComprador);
			}else{
				consulta.append("where idComprador=").append(idComprador);
			}
		}
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and cultivoRegistro =").append(idCultivo);
			}else{
				consulta.append("where cultivoRegistro =").append(idCultivo);
			}
		}
		if (tipoSeccion !=  null && !tipoSeccion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoSeccion ='").append(tipoSeccion).append("'");
			}else{
				consulta.append("where tipoSeccion ='").append(tipoSeccion).append("'");
			}    
		}		
		if (folio != null && !folio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio = '").append(folio).append("'");
			}else{
				consulta.append("where folio = '").append(folio).append("'");;
			}
		}	
		
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		consulta.insert(0, "From GruposCamposRelacionVentasV ").append(" ORDER BY folioCartaAdhesion, folio, posicionGrupo, posicionCampo");    
		if(limit){
			lst= session.createQuery(consulta.toString()).setFirstResult(0).setMaxResults(1).list();   
		}else{
			lst= session.createQuery(consulta.toString()).list();
		} 
		return lst;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionVentasV> consultaRelacionVentaByCicloCultivo(long idPerRel, int idComprador, String folioCartaAdhesion)throws JDBCException{
		StringBuilder condicion = new StringBuilder();
		List<GruposCamposRelacionVentasV> lst = null;
		String consulta = "";
		if (idPerRel != 0 && idPerRel != -1){
			condicion.append("where idPerRel = ").append(idPerRel);
		} 
		if (idComprador != 0 && idComprador != -1){
			if(condicion.length()>0){
				condicion.append(" and idComprador =").append(idComprador);
			}else{
				condicion.append("where idComprador =").append(idComprador);
			}
		}
		
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(condicion.length()>0){
				condicion.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				condicion.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionVentasV(idPerRel, idComprador, folioCartaAdhesion, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio )"+
				" from GruposCamposRelacionVentasV "+
				 condicion.toString()+
				" group by idPerRel, idComprador, folioCartaAdhesion, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio "+
				" order by ejercicio, cultivoRegistro, cicloLargo";
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionVentasV> consultaVentaByFolioCicloCultivo(long idPerRel, int idComprador,  int cultivoRegistro, long idPgrCiclo)throws  JDBCException{
		List<GruposCamposRelacionVentasV> lst = null;
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionVentasV(idPerRel, idComprador, folioCartaAdhesion, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio, folio)"+
				" from GruposCamposRelacionVentasV "+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+
				" and cultivo_registro = "+cultivoRegistro+" and id_pgr_ciclo = "+idPgrCiclo+
				" group by idPerRel, idComprador, folioCartaAdhesion, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio, folio "+
				" order by ejercicio, cultivoRegistro, cicloLargo, folio ";
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionVentasV> consultaRelacionVentaByCicloCultivoRegistroCA(long idPerRel, int idComprador)throws  JDBCException{
		List<GruposCamposRelacionVentasV> lst = null;
		
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionVentasV(idPerRel, idComprador, folioCartaAdhesion, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio )"+
				" from GruposCamposRelacionVentasV "+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+ " and folioCartaAdhesion is null"+
				" group by idPerRel, idComprador, folioCartaAdhesion, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio "+
				" order by ejercicio, cultivoRegistro, cicloLargo";
		lst = session.createQuery(consulta.toString()).list();
		
		
		return lst;
	
	}
	
	public int borrarFolioVentas(long idPerRel, Integer idComprador,
			Integer cultivoRelacion, String folioFacVenta, long idPgrCiclo) {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM campos_relacion_ventas WHERE id_per_rel= ").append(idPerRel)
					.append(" and id_comprador =").append(idComprador).append(" and id_pgr_cultivo = ").append(cultivoRelacion)
					.append(" and id_pgr_ciclo = ").append(idPgrCiclo).append(" and folio = '"+folioFacVenta+"'");
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	
	public int registraFolioCartaVentas(String folioCartaAdhesion, Integer idComprador, long idPerRel, int cultivoRelacion, long idPgrCiclo)throws JDBCException {
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("update  campos_relacion_ventas set folio_carta_adhesion  = '").append(folioCartaAdhesion).append("'")
			.append(" WHERE id_comprador= ").append(idComprador).append(" AND id_per_rel=").append(idPerRel)
			.append(" AND id_pgr_cultivo=").append(cultivoRelacion)
			.append(" and id_pgr_ciclo = ").append(idPgrCiclo);
			updatedEntities  = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return updatedEntities;
	}
	//TERRESTRE	
	@SuppressWarnings("unchecked")
	public List<CamposRelacionTerrestre> consultaCamposTerrestre(long idCampoRelacion, Integer idComprador, Integer idPgrCultivo, long idPgrCiclo, String folioTalonTerrestre, String claveBodegaOpp)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<CamposRelacionTerrestre> lst=null;
		
		if (idCampoRelacion != 0 && idCampoRelacion != -1){
			consulta.append("where idCampoRelacion = ").append(idCampoRelacion);
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador =").append(idComprador);
			}else{
				consulta.append("where idComprador =").append(idComprador);
			}
		}
		if (idPgrCultivo != 0 && idPgrCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCultivo =").append(idPgrCultivo);
			}else{
				consulta.append("where idPgrCultivo =").append(idPgrCultivo);
			}
		}
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		
		if (folioTalonTerrestre != null && !folioTalonTerrestre.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio ='").append(folioTalonTerrestre).append("'");
			}else{
				consulta.append("where folio ='").append(folioTalonTerrestre).append("'");
			}
		}	
		
		if (claveBodegaOpp != null && !claveBodegaOpp.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}else{
				consulta.append("where claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}
		}	
		consulta.insert(0, "From CamposRelacionTerrestre ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionTerrestreV> consultaGruposCamposTerrestreV(int idTipoRelacion,  long idPerRel, Integer idComprador, String folioCartaAdhesion, String claveBodegaOpp, Integer idCultivo, String tipoSeccion, String folio, boolean limit, long idPgrCiclo, int estadoBodega)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<GruposCamposRelacionTerrestreV> lst = null;
		if (idTipoRelacion != 0 && idTipoRelacion != -1){
			consulta.append("where idTipoRelacion=").append(idTipoRelacion);
		}
		
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append("where idPerRel=").append(idPerRel);
			}
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador=").append(idComprador);
			}else{
				consulta.append("where idComprador=").append(idComprador);
			}
		}
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		if (claveBodegaOpp !=  null && !claveBodegaOpp.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}else{
				consulta.append("where claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}
		}
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and cultivoRegistro =").append(idCultivo);
			}else{
				consulta.append("where cultivoRegistro =").append(idCultivo);
			}
		}
		if (tipoSeccion !=  null && !tipoSeccion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoSeccion ='").append(tipoSeccion).append("'");
			}else{
				consulta.append("where tipoSeccion ='").append(tipoSeccion).append("'");
			}
		}		
		if(folio != null && !folio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio = '").append(folio).append("'");
			}else{
				consulta.append("where folio = '").append(folio).append("'");;
			}
		}	
		
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		
		if (estadoBodega != 0 && estadoBodega != -1){
			if(consulta.length()>0){
				consulta.append(" and estadoBodega =").append(estadoBodega);
			}else{
				consulta.append("where estadoBodega =").append(estadoBodega);
			}
		}
		consulta.insert(0, "From GruposCamposRelacionTerrestreV ").append(" ORDER BY folioCartaAdhesion, claveBodegaOpp, folio, posicionGrupo, posicionCampo");    
		if(limit){
			lst= session.createQuery(consulta.toString()).setFirstResult(0).setMaxResults(1).list();   
		}else{
			lst= session.createQuery(consulta.toString()).list();
		} 
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionTerrestreV> consultaRelacionTerrestreByBodegaOPP(long idPerRel, int idComprador, String folioCartaAdhesion)throws  JDBCException{
		List<GruposCamposRelacionTerrestreV> lst = null;
		StringBuilder condicion = new StringBuilder();
		if (idPerRel != 0 && idPerRel != -1){
			condicion.append(" where idPerRel = ").append(idPerRel);
		} 
		if (idComprador != 0 && idComprador != -1){
			if(condicion.length()>0){
				condicion.append(" and idComprador =").append(idComprador);
			}else{
				condicion.append(" where idComprador =").append(idComprador);
			}
		}
		
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(condicion.length()>0){
				condicion.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				condicion.append(" where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}		 
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionTerrestreV (idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp, estadoBodega, estado, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio )"+
						" from GruposCamposRelacionTerrestreV"+
						condicion.toString()+
						" group by idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp, estadoBodega, estado, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio"+
						" order by ejercicio, cultivoRegistro, cicloLargo, claveBodegaOpp, estado";
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionTerrestreV> consultaRelacionTerrestreByBodegaOPPRegistroCA(long idPerRel, int idComprador)throws  JDBCException{
		List<GruposCamposRelacionTerrestreV> lst = null;
		
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionTerrestreV (idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp, estadoBodega, estado, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio )"+
				" from GruposCamposRelacionTerrestreV"+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+ " and folioCartaAdhesion is null"+
				" group by idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp, estadoBodega, estado, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio"+
				" order by ejercicio, cultivoRegistro, cicloLargo, claveBodegaOpp ";
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionTerrestreV> consultaTerrestreByFolioBodegaOPlanP(long idPerRel, int idComprador,  int cultivoRegistro, long idPgrCiclo, String claveBodegaOpp)throws  JDBCException{
		List<GruposCamposRelacionTerrestreV> lst = null;
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionTerrestreV(idPerRel, idComprador, folioCartaAdhesion, claveBodegaOpp, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio, folio)"+
				" from GruposCamposRelacionTerrestreV "+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+
				" and cultivo_registro = "+cultivoRegistro+" and id_pgr_ciclo = "+idPgrCiclo+" and claveBodegaOpp='"+claveBodegaOpp+"'"+
				" group by idPerRel, idComprador, folioCartaAdhesion, claveBodegaOpp, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio, folio "+
				" order by  folio ";
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	public int registraFolioCartaTerrestre (String folioCartaAdhesion, Integer idComprador, long idPerRel, Integer idCultivo,long idPgrCiclo, String claveBodegaOpp)throws JDBCException {
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("update campos_relacion_terrestre set folio_carta_adhesion  = '").append(folioCartaAdhesion).append("'")
			.append(" WHERE id_comprador = ").append(idComprador).append(" AND id_per_rel=").append(idPerRel)
			.append(" AND id_pgr_cultivo = ").append(idCultivo).append("AND id_pgr_ciclo= ").append(idPgrCiclo)
			.append(" AND clave_bodega_o_pp = '").append(claveBodegaOpp).append("'");
			updatedEntities  = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return updatedEntities;
	}
	
	public int borrarFolioTerrestre(long idPerRel, Integer idComprador, String claveBodegaOpp, int cultivoRegistro, String folioTalon, long idPgrCiclo)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM campos_relacion_terrestre WHERE id_per_rel= ").append(idPerRel)
			.append(" and id_comprador=").append(idComprador).append(" and id_pgr_cultivo = ").append(cultivoRegistro)
			.append(" and id_pgr_ciclo = ").append(idPgrCiclo).append(" and folio = '"+folioTalon+"' ")
			.append(" and clave_bodega_o_pp='").append(claveBodegaOpp).append("'");
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
		
	//RELACION MARITIMA
	@SuppressWarnings("unchecked")
	public List<CamposRelacionMaritima> consultaCamposMaritima(
			long idCampoRelacion, Integer idComprador, Integer idPgrCultivo,
			long idPgrCiclo, String folioTalonMaritimo, String claveBodegaOpp, String nombreBarco, String lugarDestino)
			throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CamposRelacionMaritima> lst=null;
		
		if (idCampoRelacion != 0 && idCampoRelacion != -1){
			consulta.append("where idCampoRelacion = ").append(idCampoRelacion);
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador =").append(idComprador);
			}else{
				consulta.append("where idComprador =").append(idComprador);
			}
		}
		if (idPgrCultivo != 0 && idPgrCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCultivo =").append(idPgrCultivo);
			}else{
				consulta.append("where idPgrCultivo =").append(idPgrCultivo);
			}
		}
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		if (folioTalonMaritimo != null && !folioTalonMaritimo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio ='").append(folioTalonMaritimo).append("'");
			}else{
				consulta.append("where folio ='").append(folioTalonMaritimo).append("'");
			}
		}	
		
		if(claveBodegaOpp != null && !claveBodegaOpp.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}else{
				consulta.append("where claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}
		}		
		if(nombreBarco != null && !nombreBarco.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and nombreBarco ='").append(nombreBarco).append("'");
			}else{
				consulta.append("where nombreBarco ='").append(nombreBarco).append("'");
			}
		}		
		if(lugarDestino != null && !lugarDestino.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and lugarDestino ='").append(lugarDestino).append("'");
			}else{
				consulta.append("where lugarDestino ='").append(lugarDestino).append("'");
			}
		}
		consulta.insert(0, "From CamposRelacionMaritima ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
		
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionMaritimaV> consultaGruposCamposMaritimaV(
			int idTipoRelacion, long idPerRel, Integer idComprador,
			String folioCartaAdhesion, String claveBodegaOpp,
			Integer cultivoRegistro, String tipoSeccion,
			String folioTalonMaritimo, boolean limit, long idPgrCiclo,
			String nombreBarco, String lugarDestino) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<GruposCamposRelacionMaritimaV> lst = null;
		if (idTipoRelacion != 0 && idTipoRelacion != -1){
			consulta.append("where idTipoRelacion=").append(idTipoRelacion);
		}
		
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append("where idPerRel=").append(idPerRel);
			}
		}
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador=").append(idComprador);
			}else{
				consulta.append("where idComprador=").append(idComprador);
			}
		}
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		if (claveBodegaOpp !=  null && !claveBodegaOpp.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}else{
				consulta.append("where claveBodegaOpp ='").append(claveBodegaOpp).append("'");
			}
		}
		if (cultivoRegistro != 0 && cultivoRegistro != -1){
			if(consulta.length()>0){
				consulta.append(" and cultivoRegistro =").append(cultivoRegistro);
			}else{
				consulta.append("where cultivoRegistro =").append(cultivoRegistro);
			}
		}
		if (tipoSeccion !=  null && !tipoSeccion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipoSeccion ='").append(tipoSeccion).append("'");
			}else{
				consulta.append("where tipoSeccion ='").append(tipoSeccion).append("'");
			}
		}		
		if (folioTalonMaritimo != null && !folioTalonMaritimo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio = '").append(folioTalonMaritimo).append("'");
			}else{
				consulta.append("where folio = '").append(folioTalonMaritimo).append("'");;
			}
		}	
		
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo =").append(idPgrCiclo);
			}else{
				consulta.append("where idPgrCiclo =").append(idPgrCiclo);
			}
		}
		if(nombreBarco != null && !nombreBarco.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and nombreBarco ='").append(nombreBarco).append("'");
			}else{
				consulta.append("where nombreBarco ='").append(nombreBarco).append("'");
			}
		}		
		if(lugarDestino != null && !lugarDestino.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and lugarDestino ='").append(lugarDestino).append("'");
			}else{
				consulta.append("where lugarDestino ='").append(lugarDestino).append("'");
			}
		}
		consulta.insert(0, "From GruposCamposRelacionMaritimaV ").append(" ORDER BY folioCartaAdhesion, claveBodegaOpp, folio, posicionGrupo, posicionCampo");    
		if(limit){
			lst= session.createQuery(consulta.toString()).setFirstResult(0).setMaxResults(1).list();   
		}else{
			lst= session.createQuery(consulta.toString()).list();
		} 		
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionMaritimaV> consultaRelacionMaritimaByBodegaOPP(long idPerRel, int idComprador, String folioCartaAdhesion)throws  JDBCException{
		List<GruposCamposRelacionMaritimaV> lst = null;
		StringBuilder condicion = new StringBuilder();
		if (idPerRel != 0 && idPerRel != -1){
			condicion.append(" where idPerRel = ").append(idPerRel);
		} 
		if (idComprador != 0 && idComprador != -1){
			if(condicion.length()>0){
				condicion.append(" and idComprador =").append(idComprador);
			}else{
				condicion.append(" where idComprador =").append(idComprador);
			}
		}
		
		if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
			if(condicion.length()>0){
				condicion.append(" and folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}else{
				condicion.append(" where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			}
		}
		 
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionMaritimaV (idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp,  cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio, nombreBarco, lugarDestino )"+
						" from GruposCamposRelacionMaritimaV"+
						  condicion.toString()+
						" group by idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio, nombreBarco, lugarDestino"+
						" order by ejercicio, cultivoRegistro, cicloLargo, claveBodegaOpp, nombreBarco, lugarDestino";
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionMaritimaV> consultaRelacionMaritimaByBodegaOPPRegistroCA(long idPerRel, int idComprador)throws  JDBCException{
		List<GruposCamposRelacionMaritimaV> lst = null;		
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionMaritimaV (idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp, cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio, nombreBarco, lugarDestino)"+
				" from GruposCamposRelacionMaritimaV"+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+ " and folioCartaAdhesion is null"+
				" group by idPerRel, idTipoRelacion, idComprador, folioCartaAdhesion, claveBodegaOpp,  cultivoRegistro, cultivo,  idPgrCiclo, cicloLargo, ejercicio, nombreBarco, lugarDestino"+
				" order by ejercicio, cultivoRegistro, cicloLargo, claveBodegaOpp ";
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<GruposCamposRelacionMaritimaV> consultaMaritimaByFolioBodegaOPlanP(long idPerRel, int idComprador,  int cultivoRegistro, long idPgrCiclo, String claveBodegaOpp, String nombreBarco, String lugarDestino)throws  JDBCException{
		List<GruposCamposRelacionMaritimaV> lst = null;
		String consulta = "select new mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionMaritimaV(idPerRel, idComprador, folioCartaAdhesion, claveBodegaOpp, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio, folio, nombreBarco, lugarDestino)"+
				" from GruposCamposRelacionMaritimaV "+
				" where idPerRel = "+idPerRel+" and idComprador= "+idComprador+
				" and cultivoRegistro = "+cultivoRegistro+" and idPgrCiclo = "+idPgrCiclo+
				" and nombreBarco='"+nombreBarco+"' and lugarDestino='"+lugarDestino+"' and claveBodegaOpp='"+claveBodegaOpp+"'"+
				" group by idPerRel, idComprador, folioCartaAdhesion, claveBodegaOpp, cultivoRegistro, cultivo, idPgrCiclo, cicloLargo, ejercicio, folio, nombreBarco, lugarDestino "+
				" order by  folio ";
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	public int registraFolioCartaMaritima(String folioCartaAdhesion,
			Integer idComprador, long idPerRel, Integer idCultivo,
			long idPgrCiclo, String claveBodegaOpp, String nombreBarco,
			String lugarDestino) throws JDBCException {
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("update  campos_relacion_maritima set folio_carta_adhesion  = '").append(folioCartaAdhesion).append("'")
			.append(" WHERE id_comprador= ").append(idComprador).append(" AND id_per_rel=").append(idPerRel)
			.append(" AND id_pgr_cultivo = ").append(idCultivo).append("AND id_pgr_ciclo= ").append(idPgrCiclo)
			.append(" AND clave_bodega_o_pp = '").append(claveBodegaOpp).append("'")
			.append(" AND nombre_barco='").append(nombreBarco).append("'")
			.append(" AND lugar_destino='").append(lugarDestino).append("'");
			updatedEntities  = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return updatedEntities;
	}
	
	public int borrarFolioMaritima(long idPerRel, Integer idComprador,
			String claveBodegaOpp, int cultivoRegistro, String folioTalon,
			long idPgrCiclo, String nombreBarco, String lugarDestino)
			throws JDBCException {
	int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM campos_relacion_maritima WHERE id_per_rel= ").append(idPerRel)
			.append(" and id_comprador=").append(idComprador).append(" and id_pgr_cultivo = ").append(cultivoRegistro)
			.append(" and id_pgr_ciclo = ").append(idPgrCiclo).append(" and folio = '"+folioTalon+"' ")
			.append(" and clave_bodega_o_pp='").append(claveBodegaOpp).append("'")
			.append(" AND nombre_barco='").append(nombreBarco).append("'")
			.append(" AND lugar_destino='").append(lugarDestino).append("'");
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	//RELACION DE COMPRAS
	public List<ComprasDatosParticipante> consultaComprasDatosParticipantes(Integer idComprador, String claveBodega, long idPerRel)throws  JDBCException{
		return consultaComprasDatosParticipantes(idComprador,claveBodega, idPerRel, 0, 0, 0,0, null);
	}
	
	public List<ComprasDatosParticipante> consultaComprasDatosParticipantes(long idCompPer)throws  JDBCException{
		return consultaComprasDatosParticipantes(-1,null, -1, idCompPer,0,0,0, null);
	}
	public List<ComprasDatosParticipante> consultaComprasDatosParticipantes(String folioCartaAdhesion)throws  JDBCException{
		return consultaComprasDatosParticipantes(-1,null, -1, 0,0,0,0, folioCartaAdhesion);
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasDatosParticipante> consultaComprasDatosParticipantes(Integer idComprador, String claveBodega, long idPerRel, long idCompPer, int idCultivo, long idPgrCiclo, int estadoAcopio, String folioCartaAdhesion)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasDatosParticipante> lst = null;
		if (idComprador != 0 && idComprador != -1){
			consulta.append(" where idComprador =").append(idComprador);
		}
		if (claveBodega !=  null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega = '").append(claveBodega).append("'");
			}else{
				consulta.append(" where claveBodega = '").append(claveBodega).append("'");
			}
		}
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append(" where idPerRel=").append(idPerRel);
			}
		}
		
		if (idCompPer != 0 && idCompPer != -1){
			if(consulta.length()>0){
				consulta.append(" and idCompPer=").append(idCompPer);
			}else{
				consulta.append(" where idCompPer=").append(idCompPer);
			}
		}
		
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo=").append(idCultivo);
			}else{
				consulta.append(" where idCultivo=").append(idCultivo);
			}
		}
		
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo=").append(idPgrCiclo);
			}else{
				consulta.append(" where idPgrCiclo=").append(idPgrCiclo);
			}
		}
		
		if(estadoAcopio != 0 && estadoAcopio != -1){
			if(consulta.length()>0){
				consulta.append(" and estadoAcopio=").append(estadoAcopio);
			}else{
				consulta.append(" where estadoAcopio=").append(estadoAcopio);
			}
		}
		
		if (claveBodega !=  null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega = '").append(claveBodega).append("'");
			}else{
				consulta.append(" where claveBodega = '").append(claveBodega).append("'");
			}
		}
		consulta.insert(0, "From ComprasDatosParticipante ").append(" order by idComprador, claveBodega");
		System.out.println(consulta);
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ComprasDatosProductor> consultaComprasDatosProductor(long idCompProd, long idCompPer, long folioProductor)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasDatosProductor> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append(" where idCompProd=").append(idCompProd);
		}
		
		if ( idCompPer != 0 && idCompPer!=-1){
			if(consulta.length()>0){
				consulta.append(" and idCompPer=").append(idCompPer);
			}else{
				consulta.append(" where idCompPer=").append(idCompPer);
			}
		}
		if ( folioProductor != 0 && folioProductor != -1){
			if(consulta.length()>0){
				consulta.append(" and folioProductor=").append(folioProductor);
			}else{
				consulta.append(" where folioProductor=").append(folioProductor);
			}
		}		
		consulta.insert(0, "From ComprasDatosProductor ").append(" order by folioProductor");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ComprasDatosProductorV> consultaComprasDatosProductorV(long idCompPer, long folioProductor)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasDatosProductorV> lst=null;
		if (idCompPer != 0 && idCompPer != -1){
			consulta.append(" where idCompPer=").append(idCompPer);
		}
		if ( folioProductor != 0){
			if(consulta.length()>0){
				consulta.append(" and folioProductor=").append(folioProductor);
			}else{
				consulta.append(" where folioProductor=").append(folioProductor);
			}
		}		
		
		consulta.insert(0, "From ComprasDatosProductorV ").append(" order by folioProductor");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public List<ComprasPredioV> consultaComprasPredioV(long idCompProd)throws  JDBCException{
		return consultaComprasPredioV(idCompProd, null, -1, -1, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasPredioV> consultaComprasPredioV(long idCompProd, String folioPredio, int folioPredioSec, long idPerRel, String predioAlterno)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasPredioV> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		
		if (folioPredio != null && ! folioPredio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioPredio='").append(folioPredio).append("'");
			}else{
				consulta.append(" where folioPredio='").append(folioPredio).append("'");
			}
		}
		
		if (folioPredioSec != -1 && folioPredioSec !=0){
			if(consulta.length()>0){
				consulta.append(" and folioPredioSec=").append(folioPredioSec);
			}else{
				consulta.append(" where folioPredioSec=").append(folioPredio);
			}
		}
		
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel=").append(idPerRel);
			}else{
				consulta.append(" where idPerRel=").append(idPerRel);
			}
		}
		
		if (predioAlterno != null && ! predioAlterno.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and predioAlterno='").append(predioAlterno).append("'");
			}else{
				consulta.append(" where predioAlterno='").append(predioAlterno).append("'");
			}
		}
		
		consulta.insert(0, "From ComprasPredioV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public List<ComprasPredio> consultaComprasPredio(long idCompProd)throws  JDBCException{
		return consultaComprasPredio(0, idCompProd, null, 0, null);
	}
	@SuppressWarnings("unchecked")
	public List<ComprasPredio> consultaComprasPredio(long idPredio, long idCompProd, String predio, int predioSecuencial, String predioAlterno)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasPredio> lst=null;
		if (idPredio != 0 && idPredio != -1){
			consulta.append("where idPredio = ").append(idPredio);
		}
		if (idCompProd != 0 && idCompProd != -1){
			if(consulta.length()>0){
				consulta.append(" and idCompProd=").append(idCompProd);
			}else{
				consulta.append(" where idCompProd=").append(idCompProd);
			}
		}
		
		
		if (predio != null && ! predio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioPredio='").append(predio).append("'");
			}else{
				consulta.append(" where folioPredio='").append(predio).append("'");
			}
		}
		
		if (predioSecuencial != -1 && predioSecuencial !=0){
			if(consulta.length()>0){
				consulta.append(" and folioPredioSec=").append(predioSecuencial);
			}else{
				consulta.append(" where folioPredioSec=").append(predioSecuencial);
			}
		}
		
		if (predioAlterno != null && ! predioAlterno.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and predioAlterno='").append(predioAlterno).append("'");
			}else{
				consulta.append(" where predioAlterno='").append(predioAlterno).append("'");
			}
		}
	
		consulta.insert(0, "From ComprasPredio ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasEntradaBodega> consultaComprasEntradaBodega(long idCompProd)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasEntradaBodega> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		consulta.insert(0, "From ComprasEntradaBodega ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasFacVentaGlobal> consultaComprasFacVentaGlobal(long idCompProd)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasFacVentaGlobal> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		consulta.insert(0, "From ComprasFacVentaGlobal ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public List<ComprasFacVenta> consultaComprasFacVenta(long idCompProd)throws  JDBCException{
		return consultaComprasFacVenta(idCompProd, null);
	}
	@SuppressWarnings("unchecked")
	public List<ComprasFacVenta> consultaComprasFacVenta(long idCompProd, String folioFac)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasFacVenta> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		if (folioFac != null && !folioFac.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioFac='").append(folioFac).append("'");
			}else{
				consulta.append(" where folioFac=").append(folioFac).append("'");
			}
		}
		
		consulta.insert(0, "From ComprasFacVenta ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasContrato> consultaComprasContratos(long idCompProd)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasContrato> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		consulta.insert(0, "From ComprasContrato ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasPagoProdAxc> consultaComprasPagoProdAxc(long idCompProd)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasPagoProdAxc> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		consulta.insert(0, "From ComprasPagoProdAxc ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasPagoProdSinAxc> consultaComprasPagoProdSinAxc(long idCompProd)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasPagoProdSinAxc> lst=null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append("where idCompProd = ").append(idCompProd);
		}
		consulta.insert(0, "From ComprasPagoProdSinAxc ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	/**Inicializacion de Programa **/ 	
	@SuppressWarnings("unchecked")
	public List<ProgramaRelacionCiclos > consultaProgramaRelacionCiclos(long idIniEsquemaRelacion)throws  JDBCException{
		List<ProgramaRelacionCiclos> lst = null;
		StringBuilder consulta= new StringBuilder();
		
		if (idIniEsquemaRelacion != 0 && idIniEsquemaRelacion != -1){
			consulta.append("where idIniEsquemaRelacion = ").append(idIniEsquemaRelacion);
		}
		
		consulta.insert(0, "From ProgramaRelacionCiclos ");
		lst= session.createQuery(consulta.toString()).list();		
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramaRelacionCultivos > consultaProgramaRelacionCultivos(long idIniEsquemaRelacion)throws  JDBCException{
		List<ProgramaRelacionCultivos> lst = null;
		StringBuilder consulta= new StringBuilder();
		
		if (idIniEsquemaRelacion != 0 && idIniEsquemaRelacion != -1){
			consulta.append("where idIniEsquemaRelacion = ").append(idIniEsquemaRelacion);
		}
		
		consulta.insert(0, "From ProgramaRelacionCultivos ");
		lst= session.createQuery(consulta.toString()).list();		
		
		return lst;
	}
	
	public List<IniEsquemaRelacionV> consultaIniEsquemaRelacionV(long idIniEsquemaRelacion)throws  JDBCException{
		return consultaIniEsquemaRelacionV(idIniEsquemaRelacion, null,null, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<IniEsquemaRelacionV> consultaIniEsquemaRelacionV(long idIniEsquemaRelacion, String nombreEsquema, String fechaInicio, String fechaFin)throws  JDBCException{
		List<IniEsquemaRelacionV> lst = null;
		StringBuilder consulta= new StringBuilder();
		
		if (idIniEsquemaRelacion != 0 && idIniEsquemaRelacion != -1){
			consulta.append("where idIniEsquemaRelacion = ").append(idIniEsquemaRelacion);
		}
		
		
		if (nombreEsquema != null && !nombreEsquema.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and nombreEsquema =").append(nombreEsquema);
			}else{
				consulta.append("where nombreEsquema =").append(nombreEsquema);
			}
		}
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		consulta.insert(0, "From IniEsquemaRelacionV ");
		lst= session.createQuery(consulta.toString()).list();		
		
		return lst;
	}
	
	public List<IniEsquemaRelacion> consultaIniEsquemaRelacion(long idIniEsquemaRelacion)throws  JDBCException{
		return consultaIniEsquemaRelacion(idIniEsquemaRelacion, null,null, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<IniEsquemaRelacion> consultaIniEsquemaRelacion(long idIniEsquemaRelacion, String nombreEsquema, String fechaInicio, String fechaFin)throws  JDBCException{
		List<IniEsquemaRelacion> lst = null;
		StringBuilder consulta= new StringBuilder();
		
		if (idIniEsquemaRelacion != 0 && idIniEsquemaRelacion != -1){
			consulta.append("where idIniEsquemaRelacion = ").append(idIniEsquemaRelacion);
		}
		
		
		if (nombreEsquema != null && !nombreEsquema.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and nombreEsquema =").append(nombreEsquema);
			}else{
				consulta.append("where nombreEsquema =").append(nombreEsquema);
			}
		}
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		consulta.insert(0, "From IniEsquemaRelacion ");
		lst= session.createQuery(consulta.toString()).list();		
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonalizacionRelaciones> consultaPersonalizacionRelacion(long idPerRel, int idRelacion, long idIniEsquemaRelacion)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<PersonalizacionRelaciones> lst = null;
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}

		if (idRelacion != 0 && idRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idRelacion=").append(idRelacion);
			}else{
				consulta.append("where idRelacion=").append(idRelacion);
			}
		}
		if (idIniEsquemaRelacion != 0 && idIniEsquemaRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idIniEsquemaRelacion=").append(idIniEsquemaRelacion);
			}else{
				consulta.append("where idIniEsquemaRelacion=").append(idIniEsquemaRelacion);
			}
		}
		
		
		consulta.insert(0, "From PersonalizacionRelaciones ");
		lst= session.createQuery(consulta.toString()).list();
				
		return lst;
	}
	
	public int borrarCiclosEsquemaRel(long idIniEsquemaRelacion)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM programa_relacion_ciclos WHERE id_ini_esquema_relacion= ").append(idIniEsquemaRelacion);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	public int borrarCultivosEsquemaRel(long idIniEsquemaRelacion)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM programa_relacion_cultivos WHERE id_ini_esquema_relacion= ").append(idIniEsquemaRelacion);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasMaxCamposV> consultaComprasMaxCamposV(long idCompPer, long idCompProd)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasMaxCamposV> lst = null;
		if (idCompPer != 0 && idCompPer != -1){
			consulta.append("where idCompPer = ").append(idCompPer);
		}
		
		if (idCompProd != 0 && idCompProd != -1){
			if(consulta.length()>0){
				consulta.append(" and idCompProd= ").append(idCompProd);
			}else{
				consulta.append("where idCompProd = ").append(idCompProd);
			}
		}		
		
		consulta.insert(0, "From ComprasMaxCamposV ");
		lst= session.createQuery(consulta.toString()).list();
				
		return lst;
	}
	
	public int borrarRegistros (long idCompProd, int apartado)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder consulta = new StringBuilder();
			if(apartado == 6){
				consulta.append("DELETE FROM compras_predio WHERE id_comp_prod= ").append(idCompProd).append("; ");//predios del productor
			}
			
			if(apartado == 7){
				consulta.append("DELETE FROM compras_entrada_bodega WHERE id_comp_prod=").append(idCompProd).append("; "); //Entradas a la Bodega
			}
			
			if(apartado == 9){
				consulta.append("DELETE FROM compras_fac_venta WHERE id_comp_prod=").append(idCompProd).append("; ");//Factura de Venta del Grano
			}
			
			if(apartado == 12){
				consulta.append("DELETE FROM compras_pago_prod_sin_axc WHERE id_comp_prod=").append(idCompProd).append("; ");//Pago al Productor sin AXC
			}			
			elementosBorrados = session.createSQLQuery(consulta.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasDatosParticipante> consultaCultivoCiclo(Integer idComprador, Integer idCultivo, long idPgrCiclo)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasDatosParticipante> lst = null;
		if (idComprador != 0 && idComprador != -1){
			consulta.append(" where idComprador =").append(idComprador);
		}
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo = '").append(idCultivo).append("'");
			}else{
				consulta.append(" where idCultivo = '").append(idCultivo).append("'");
			}
		}
		if (idPgrCiclo != 0 && idPgrCiclo != -1){
			if(consulta.length()>0){
				consulta.append(" and idPgrCiclo=").append(idPgrCiclo);
			}else{
				consulta.append(" where idPgrCiclo=").append(idPgrCiclo);
			}
		}
		consulta.insert(0, "From ComprasDatosParticipante ");
		System.out.println(consulta);
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComprasBodegaTicketV> consultaBodegaTicket(long idCompProd, String boletaTicketBascula, String claveBodega)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasBodegaTicketV> lst = null;
		if (idCompProd != 0 && idCompProd != -1){
			consulta.append(" where idCompProd =").append(idCompProd);
		}
		if (boletaTicketBascula != null && boletaTicketBascula != ""){
			if(consulta.length()>0){
				consulta.append(" and boletaTicketBascula = '").append(boletaTicketBascula).append("'");
			}else{
				consulta.append("where boletaTicketBascula = '").append(boletaTicketBascula).append("'");
			}
		}
		if (claveBodega != null && claveBodega != ""){
			if(consulta.length()>0){
				consulta.append(" and claveBodega = '").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega = '").append(claveBodega).append("'");
			}
		}
		consulta.insert(0, "From ComprasBodegaTicketV ");
		System.out.println(consulta);
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	/****nuevo***/
	public List<ComprasDatosParticipanteV> consultaComprasDatosParticipanteV(long idCompPer)throws  JDBCException{
		return consultaComprasDatosParticipanteV( 0, null, null, 0, 0, idCompPer,0,0);
	}
	public List<ComprasDatosParticipanteV> consultaComprasDatosParticipanteV(String folioCartaAdhesion)throws  JDBCException{
		return consultaComprasDatosParticipanteV( 0, folioCartaAdhesion, null, 0, 0, 0,0,0);
	}
	public List<ComprasDatosParticipanteV> consultaComprasDatosParticipanteV(int idRelacion, String folioCartaAdhesion, String claveBodega)throws  JDBCException{
		return consultaComprasDatosParticipanteV( idRelacion,  folioCartaAdhesion,  claveBodega, 0, 0, 0, 0,0);
	}
	@SuppressWarnings("unchecked")
	public List<ComprasDatosParticipanteV> consultaComprasDatosParticipanteV(int idRelacion, String folioCartaAdhesion, String claveBodega, long idPerRel, Integer idComprador, long idCompPer, int idEstado, int idEstatus)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<ComprasDatosParticipanteV> lst = null;
		if (idRelacion != 0 && idRelacion != -1){
			consulta.append("where idRelacion = ").append(idRelacion);
		}
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
			}
		}		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega = '").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega = '").append(claveBodega).append("'");
			}
		}
		if (idPerRel != 0 && idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel = ").append(idPerRel);
			}else{
				consulta.append("where idPerRel = ").append(idPerRel);
			}
		}
		
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador = ").append(idComprador);
			}else{
				consulta.append("where idComprador = ").append(idComprador);
			}
		}
		
		if (idCompPer != 0 && idCompPer != -1){
			if(consulta.length()>0){
				consulta.append(" and idCompPer = ").append(idCompPer);
			}else{
				consulta.append("where idCompPer = ").append(idCompPer);
			}
		}
		if (idEstatus != 0 && idEstatus != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstatusComprasDatosPar = ").append(idEstatus);
			}else{
				consulta.append("where idEstatusComprasDatosPar = ").append(idEstatus);
			}
		}
		consulta.insert(0, "From ComprasDatosParticipanteV ").append(" order by claveBodega, cultivo, cicloLargo, ejercicio, estado");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<RelacionPorFolioCABodega> consultaRelacionComprasXFolioCAYBodega(String folioCartaAdhesion)throws  JDBCException{
		List<RelacionPorFolioCABodega> lst = null;
		
		String consulta = "select folio_carta_adhesion, clave_bodega, estado_acopio, id_tipo_relacion "+
						" from  compras_datos_participante_v "+
						" where folio_carta_adhesion = '"+folioCartaAdhesion+"'"+
						" group by  folio_carta_adhesion, clave_bodega, estado_acopio, id_tipo_relacion";
		lst= session.createSQLQuery(consulta.toString()).addEntity(RelacionPorFolioCABodega.class).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Productores> consultaProductores(Long productor)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<Productores> lst = null;
		if (productor != 0 && productor != -1){
			consulta.append("where productor = ").append(productor);
		}
		consulta.insert(0, "From Productores ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public List<Predios> consultaPredios(String predio, long secuencial)throws  JDBCException{
		return consultaPredios(predio, secuencial, null);
	}
	
	public List<Predios> consultaPredios(String predio, long secuencial, String predioAlterno)throws  JDBCException{
		return consultaPredios(predio, secuencial, predioAlterno, "", 0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Predios> consultaPredios(String predio, long secuencial, String predioAlterno, String folioProductor, int idEstado)throws  JDBCException{
		
				
		StringBuilder consulta= new StringBuilder();
		List<Predios> lst = null;
		if (predio != null && !predio.isEmpty() ){
			consulta.append("where predio = '").append(predio).append("'");
		}
		
		if(secuencial != 0 && secuencial != -1){
			if(consulta.length()>0){
				consulta.append(" and predioSecuencial = ").append(secuencial);
			}else{
				consulta.append("where predioSecuencial = ").append(secuencial);
			}
		}
		
		
		if (predioAlterno != null && !predioAlterno.isEmpty() ){
			if(consulta.length()>0){
				consulta.append(" and predioAlterno = '").append(predioAlterno).append("'");
			}else{
				consulta.append("where predioAlterno = '").append(predioAlterno).append("'");
			}
		}
		
		if (folioProductor != null && !folioProductor.isEmpty() ){
			if(consulta.length()>0){
				consulta.append(" and folioProductor = ").append(folioProductor);
			}else{
				consulta.append("where folioProductor = ").append(folioProductor);
			}
		}
		
		
		if(idEstado != 0 && idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado = ").append(idEstado);
			}else{
				consulta.append("where idEstado = ").append(idEstado);
			}
		}
		consulta.insert(0, "From Predios ").append(" order by predio, predioSecuencial, predioAlterno ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	public Double getSuperficiePredioPrograma(String folioPredio, Integer secuencial, long idPerRel)throws JDBCException{
		StringBuilder consulta= new StringBuilder();
		double superficie = 0;
		consulta.insert(0, "select  COALESCE(sum(superficie),0) from compras_predio where folio_predio = ('"+folioPredio+"')"+" and "+ "folio_predio_sec="+secuencial+" and "+"id_per_rel="+idPerRel);
		superficie = Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());
		return superficie;
	}
	
	public Double getSuperficiePredioProgramaAlterno(String folioPredio,  long idPerRel)throws JDBCException{
		StringBuilder consulta= new StringBuilder();
		double superficie = 0;
		consulta.insert(0, "select  COALESCE(sum(superficie),0) from compras_predio where folio_predio = ('"+folioPredio+"') and "+"id_per_rel="+idPerRel);
		superficie = Double.parseDouble(session.createSQLQuery(consulta.toString()).list().get(0).toString());
		return superficie;
	}
	
	public int validaBoletaByBodega(String boletaTicketBascula, String claveBodega)throws JDBCException{
		
		int valida = 0;		
		StringBuilder query = new StringBuilder();
		query.append("select count(1)")
		.append("from compras_entrada_bodega ceb, ")
		.append("compras_comp_productor ccp,")
		.append("compras_datos_participante cdp")
		.append("where ceb.boleta_ticket_bascula = ").append(boletaTicketBascula).append("' ")
		.append("and ceb.id_comp_prod = ccp.id_comp_prod ")
		.append("and ccp.id_comp_per = cdp.id_comp_per ")
		.append("and cdp.clave_bodega = '").append(claveBodega).append("' ");
		valida = (Integer) session.createSQLQuery(query.toString()).addEntity(Integer.class).list().get(0);
		return valida;
	}
	
	public int validaPartBodegaProd(String claveBodega, long folioProductor, int idComprador)throws JDBCException{
		
		int valida = 0;		
		StringBuilder query = new StringBuilder();
		query.append("select count(1)")
		.append("from compras_comp_productor ccp, ")
		.append("compras_datos_participante cdp ")
		.append("where ccp.id_comp_per = cdp.id_comp_per ")
		.append("and cdp.clave_bodega = '").append(claveBodega).append("' ")
		.append(" and ccp.folio_productor = ").append(folioProductor)
		.append(" and cdp.id_comprador= ").append(idComprador);
		valida = Integer.parseInt(session.createSQLQuery(query.toString()).list().get(0).toString());
		return valida;
	}
	
	
	public int validaFacturaByProductor(String folioFactura, long folioProductor, long idCompProd){
		int valida = 0;
		StringBuilder consulta= new StringBuilder();
		consulta.append(" where cfv.id_comp_prod = ccp.id_comp_prod ");
		
		if (folioFactura != null && !folioFactura.isEmpty() ){
			if(consulta.length()>0){
				consulta.append(" and cfv.folio_fac='").append(folioFactura).append("'");
			}else{
				consulta.append("where cfv.folio_fac='").append(folioFactura).append("'");
			}			
		}
		
		if (folioProductor !=0 &&  folioProductor != -1){
			if(consulta.length()>0){
				consulta.append(" and ccp.folio_productor = ").append(folioProductor);
			}else{
				consulta.append("where ccp.folio_productor = ").append(folioProductor);
			}
		}
		
		if (idCompProd !=0 &&  idCompProd != -1){
			if(consulta.length()>0){
				consulta.append(" and ccp.id_comp_prod  = ").append(idCompProd);
			}else{
				consulta.append("where ccp.id_comp_prod  = ").append(idCompProd);
			}
		}
			
		consulta.insert(0, "select count(1) from compras_comp_productor ccp, compras_fac_venta cfv,compras_datos_participante cdp ");
	
		valida = Integer.parseInt(session.createSQLQuery(consulta.toString()).list().get(0).toString());
		return valida;
	}
	
	@SuppressWarnings("unchecked")
	public List<CriteriosValidacionRelacionDFV> consultaCriteriosValidacionRelacionDfV(int idGrupo, int idCampo, int idRelacion){
		StringBuilder consulta= new StringBuilder();
		List<CriteriosValidacionRelacionDFV> lst = null;
		
		if (idGrupo != 0 && idGrupo!=-1 ){
			consulta.append("where idGrupo = ").append(idGrupo);
		}
		
		if (idCampo !=0 &&  idCampo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCampo  = ").append(idCampo);
			}else{
				consulta.append("where idCampo  = ").append(idCampo);
			}
		}		
		consulta.insert(0, "From CriteriosValidacionRelacionDFV ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CriteriosValidacionTransaccional> consultaCriteriosValidacionRelacionTransaccional(
			long idCriterioValidacionTransaccional, long idPerRel,
			long idCampoRelacion, int idGrupo, int idCampo, long  idCriterioValidacionRlFk ) {
		StringBuilder consulta= new StringBuilder();
		List<CriteriosValidacionTransaccional> lst = null;
		
		if (idCriterioValidacionTransaccional != 0 && idCriterioValidacionTransaccional!=-1 ){
			consulta.append("where idCriterioValidacionTransaccional = ").append(idCriterioValidacionTransaccional);
		}
		
		if (idPerRel !=0 &&  idPerRel != -1){
			if(consulta.length()>0){
				consulta.append(" and idPerRel  = ").append(idPerRel);
			}else{
				consulta.append("where idPerRel  = ").append(idPerRel);
			}
		}	
		if (idCampoRelacion !=0 &&  idCampoRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idCampoRelacion  = ").append(idCampoRelacion);
			}else{
				consulta.append("where idCampoRelacion  = ").append(idCampoRelacion);
			}
		}	
		if (idGrupo !=0 &&  idGrupo != -1){
			if(consulta.length()>0){
				consulta.append(" and idGrupo  = ").append(idGrupo);
			}else{
				consulta.append("where idGrupo  = ").append(idGrupo);
			}
		}
		if (idCampo !=0 &&  idCampo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCampo  = ").append(idCampo);
			}else{
				consulta.append("where idCampo  = ").append(idCampo);
			}
		}
		if (idCriterioValidacionRlFk !=0 &&  idCriterioValidacionRlFk != -1){
			if(consulta.length()>0){
				consulta.append(" and idCriterioValidacionRlFk  = ").append(idCriterioValidacionRlFk);
			}else{
				consulta.append("where idCriterioValidacionRlFk  = ").append(idCriterioValidacionRlFk);
			}
		}
		consulta.insert(0, "From CriteriosValidacionTransaccional ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
		
	}
	public int borrarPersonalizacionRelacion(long idPerRel)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM criterios_validacion_transaccional WHERE id_per_rel= ").append(idPerRel).append("; ");
			hql.append("DELETE FROM campos_relacion WHERE id_per_rel= ").append(idPerRel).append("; ");
			hql.append("DELETE FROM grupos_relacion WHERE id_per_rel= ").append(idPerRel).append("; ");
			
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProgramasRelacionV> consultaProgramasRelacionV(long idPrograma, String nombreEsquema, String fechaInicio, String fechaFin)throws  JDBCException{
		List<ProgramasRelacionV> lst = null;
		StringBuilder consulta= new StringBuilder();
		
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		
		if (nombreEsquema != null && !nombreEsquema.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and descripcionCorta ='").append(nombreEsquema).append("'");
			}else{
				consulta.append("where descripcionCorta =").append(nombreEsquema).append("'");
			}
		}
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fechaRegistro,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fechaRegistro,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fechaRegistro,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fechaRegistro,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		consulta.insert(0, "From ProgramasRelacionV ");
		lst= session.createQuery(consulta.toString()).list();		
		
		return lst;
	}	
	
	@SuppressWarnings("unchecked")
	public List<PersonalizacionRelacionesProgramaV> consultaPersonalizacionRelacionesProgramaV(long idPerRel, int idRelacion,  String cicloAgricola, String cultivos, String fechaInicio, String fechaFin, int idPrograma)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<PersonalizacionRelacionesProgramaV> lst = null;
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where idPerRel = ").append(idPerRel);
		}

		if (idRelacion != 0 && idRelacion != -1){
			if(consulta.length()>0){
				consulta.append(" and idTipoRelacion=").append(idRelacion);
			}else{
				consulta.append("where idTipoRelacion=").append(idRelacion);
			}
		}
		
		if (cicloAgricola != null && !cicloAgricola.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and cicloAgricola='").append(cicloAgricola).append("'");
			}else{
				consulta.append("where cicloAgricola='").append(cicloAgricola).append("'");
			}
		}
		if (cultivos != null &&  !cultivos.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and cultivos= '").append(cultivos).append("'");
			}else{
				consulta.append("where cultivos='").append(cultivos).append("'");
			}
		}
		
		if((fechaInicio != null && !fechaInicio.equals(""))&& (fechaFin !=null && !fechaFin.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and  (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}else{
				consulta.append(" where (TO_CHAR(fecha_creacion,'YYYY-MM-DD')) between '").append(fechaInicio).append("'")
						.append(" and '").append(fechaFin).append("'");
			}
		}else{
			if(fechaInicio != null && !fechaInicio.equals("")){
				if(consulta.length()>0){
					consulta.append(" and (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}else{
					consulta.append("where (TO_CHAR(fecha_creacion,'YYYY-MM-DD'))='").append(fechaInicio).append("'");
				}
			}
		}
		
		if (idPrograma != 0 &&  idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		
		consulta.insert(0, "From PersonalizacionRelacionesProgramaV ").append(" order by descripcionCorta, relacion");
		lst= session.createQuery(consulta.toString()).list();
				
		return lst;
	}
	
	
	
	
	public List<CatCriteriosValidacion> consultaCatCriteriosValidacionByPrograma(long idPerRel)throws  JDBCException{
		List<CatCriteriosValidacion> lst = new ArrayList<CatCriteriosValidacion>();
		String consulta = "select distinct grupo, prioridad  "
				+"from criterios_validacion_transaccional_v "
				+"where  id_per_rel = " + idPerRel + " and grupo is not null "
				+"order by prioridad";  
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			CatCriteriosValidacion c = new CatCriteriosValidacion();
			c.setGrupo((String) row.get("grupo"));
			Integer prioridad = (Integer) row.get("prioridad");			
			c.setPrioridad(prioridad!=null?prioridad.intValue():0);
			lst.add(c);
		}
		
		return lst;
	}
	
	public List<CatCriteriosValidacion> recuperaCatCriteriosValidacionByGurpoPrograma(long idPerRel, String grupo)throws  JDBCException{
		return recuperaCatCriteriosValidacionByGurpoPrograma(idPerRel, grupo, null);
	}
	
	public List<CatCriteriosValidacion> recuperaCatCriteriosValidacionByGurpoPrograma(long idPerRel, String grupo, String pidePeriodo )throws  JDBCException{
		List<CatCriteriosValidacion> lst = new ArrayList<CatCriteriosValidacion>();
		StringBuilder consulta  = new StringBuilder();
		
		if (idPerRel != 0 && idPerRel != -1){
			consulta.append("where id_per_rel = ").append(idPerRel);
		}
		
		if (grupo != null && !grupo.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and grupo = '").append(grupo).append("' ");
			} else {
				consulta.append(" where grupo ='").append(grupo).append("' ");
			}
		}
		if (pidePeriodo != null && !pidePeriodo.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and pide_periodo = '").append(pidePeriodo).append("' ");
			} else {
				consulta.append(" where pide_periodo ='").append(pidePeriodo).append("'  ");
			}
		}
		consulta.insert(0, "select id_criterio, criterio from criterios_validacion_transaccional_v ").append(" order by prioridad_criterio" );  
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			CatCriteriosValidacion c = new CatCriteriosValidacion();
			c.setIdCriterio((Integer) row.get("id_criterio"));
			c.setPidePeriodo((String) row.get("pide_periodo"));
			c.setCriterio((String) row.get("criterio"));

			lst.add(c);
		}
		
		return lst;
	}
	
	public List<RelacionComprasTMP> consultaRelacionComprasTMP(String folioCartaAdhesion)throws  JDBCException{
		return consultaRelacionComprasTMP(folioCartaAdhesion, null, null, null, null, null, null,null, null, null, null, null, null);
	}
	public List<RelacionComprasTMP> consultaRelacionComprasTMP(String folioCartaAdhesion, String claveBodega, String estado, String folioContrato,
			String paterno, String materno, String nombre, String curpProductor, String rfcProductor, String boleta)throws  JDBCException{
		return consultaRelacionComprasTMP(folioCartaAdhesion, claveBodega, estado, folioContrato,
				 paterno,  materno,  nombre, curpProductor, rfcProductor,  boleta, null, null, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<RelacionComprasTMP> consultaRelacionComprasTMP(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
			String paternoProductor, String maternoProductor, String nombreProductor, String curpProductor, String rfcProductor, String boleta, String factura, String pago, String banco)throws  JDBCException{
		List<RelacionComprasTMP> lst = null;
		StringBuilder consulta= new StringBuilder();
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
		}	
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega='").append(claveBodega).append("'");
			}
		}
		if (nombreEstado != null && !nombreEstado.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and nombreEstado='").append(nombreEstado).append("'");
			}else{
				consulta.append("where nombreEstado='").append(nombreEstado).append("'");
			}
		}
		
		
		
		if (folioContrato != null && !folioContrato.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioContrato='").append(folioContrato).append("'");
			}else{
				consulta.append("where folioContrato='").append(folioContrato).append("'");
			}
		}
		if (paternoProductor != null && !paternoProductor.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and paternoProductor='").append(paternoProductor).append("'");
			}else{
				consulta.append("where paternoProductor='").append(paternoProductor).append("'");
			}
		}
		if (maternoProductor != null && !maternoProductor.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and maternoProductor='").append(maternoProductor).append("'");
			}else{
				consulta.append("where maternoProductor='").append(maternoProductor).append("'");
			}
		}
		if (nombreProductor != null && !nombreProductor.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and nombreProductor='").append(nombreProductor).append("'");
			}else{
				consulta.append("where nombreProductor='").append(nombreProductor).append("'");
			}
		}
		if (curpProductor != null && !curpProductor.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and curpProductor='").append(curpProductor).append("'");
			}else{
				consulta.append("where curpProductor='").append(curpProductor).append("'");
			}
		}
		if (rfcProductor != null && !rfcProductor.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and rfcProductor='").append(rfcProductor).append("'");
			}else{
				consulta.append("where rfcProductor='").append(rfcProductor).append("'");
			}
		}
		if (boleta != null && !boleta.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and boletaTicketBascula='").append(boleta).append("'");
			}else{
				consulta.append("where boletaTicketBascula='").append(boleta).append("'");
			}
		}
		
		if (factura != null && !factura.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioFacturaVenta='").append(factura).append("'");
			}else{
				consulta.append("where folioFacturaVenta='").append(factura).append("'");
			}
		}
		if (pago != null && !pago.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioDocPago='").append(pago).append("'");
			}else{
				consulta.append("where folioDocPago='").append(pago).append("'");
			}
		}	
		
		
		if (banco != null && !banco.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and bancoSinaxc='").append(banco).append("'");
			}else{
				consulta.append("where bancoSinaxc='").append(banco).append("'");
			}
		}	
		
		consulta.insert(0, "From RelacionComprasTMP ").append(" order by idRelacionComprasTmp");
		
		
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<RelacionComprasTMP> consultaRelacionComprasTMPLimit1(String folioCartaAdhesion)throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<RelacionComprasTMP> lst = null;
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
		}		
		consulta.insert(0, "From RelacionComprasTMP ");
		lst = session.createQuery(consulta.toString()).setFirstResult(0).setMaxResults(1).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificaCriterio99OKmaxFechaRegistro(String folioCartaAdhesion)throws  JDBCException{
		List<BitacoraRelcompras> lst = null;
		boolean band = false;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT * FROM bitacora_relcompras  ")
				.append(" WHERE folio_carta_adhesion = '").append(folioCartaAdhesion).append("' AND status =1 AND id_criterio = 99 ")
				.append(" AND fecha_registro = ( SELECT max (fecha_registro) from bitacora_relcompras where folio_carta_adhesion = '")
				.append(folioCartaAdhesion).append("' AND status =1 and id_criterio = 99) ");
		lst= session.createSQLQuery(consulta.toString()).list();
			
		if(lst.size()>0){
			band = true;
		}
		return band;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean verificaErrorCriterioDif99(String folioCartaAdhesion)throws  JDBCException{
		List<BitacoraRelcompras> lst = null;
		boolean band = false;
		StringBuilder consulta= new StringBuilder();
		consulta.append("From BitacoraRelcompras  ")
				.append(" where folioCartaAdhesion = '").append(folioCartaAdhesion)
				.append("' and status = 0 and idCriterio <> 99 ")
				.append(" and nombreArchivo is not null ");
		lst= session.createQuery(consulta.toString()).list();
			
		if(lst.size()>0){
			band = true;
		}
		return band;
	}
	
		
	public int borrarRelacionComprasTmpByFolioCarta(String folioCartaAdhesion)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("DELETE FROM relacion_compras_tmp WHERE folio_carta_adhesion = '").append(folioCartaAdhesion).append("'; ");
			
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	public List<BitacoraRelcompras> consultaBitacoraRelcompras(String folioCartaAdhesion, String idCriterio, String status)throws  JDBCException{
		
		return consultaBitacoraRelcompras(folioCartaAdhesion, idCriterio, status, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<BitacoraRelcompras> consultaBitacoraRelcompras(String folioCartaAdhesion, String idCriterio, String status, Boolean contieneArchivo)throws  JDBCException{		
		List<BitacoraRelcompras> lst = null;
		StringBuilder consulta= new StringBuilder();
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
		}		
		if (idCriterio != null && ! idCriterio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and idCriterio in (").append(idCriterio).append(")");
			}else{
				consulta.append("where idCriterio in (").append(idCriterio).append(")");
			}
		}
		if (status != null &&  !status.isEmpty() ){
			if(consulta.length()>0){
				consulta.append(" and status in (").append(status).append(")");
			}else{
				consulta.append("where status in (").append(status).append(")");
			}
		}
		
		if(contieneArchivo!= null){
			if(contieneArchivo){
				if(consulta.length()>0){
					consulta.append(" and nombreArchivo is not null");
				}else{
					consulta.append("where nombreArchivo is not null");
				}
			}else{
				if(consulta.length()>0){
					consulta.append(" and nombreArchivo is null");
				}else{
					consulta.append("where nombreArchivo is null");
				}
			}
		}
		consulta.insert(0, "From BitacoraRelcompras ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<BitacoraRelcompras> consultaBitacoraRelcomprasDif99(String folioCartaAdhesion, String status, Boolean contieneArchivo)throws  JDBCException{		
		List<BitacoraRelcompras> lst = null;
		StringBuilder consulta= new StringBuilder();
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
		}		
		
		if(consulta.length()>0){
			consulta.append(" and idCriterio <> 99 ");
		}else{
			consulta.append("where idCriterio <> 99 ");
		}			
		
		if (status != null &&  !status.isEmpty() ){
			if(consulta.length()>0){
				consulta.append(" and status in (").append(status).append(")");
			}else{
				consulta.append("where status in (").append(status).append(")");
			}
		}
		
		if(contieneArchivo!= null){
			if(contieneArchivo){
				if(consulta.length()>0){
					consulta.append(" and nombreArchivo is not null");
				}else{
					consulta.append("where nombreArchivo is not null");
				}
			}else{
				if(consulta.length()>0){
					consulta.append(" and nombreArchivo is null");
				}else{
					consulta.append("where nombreArchivo is null");
				}
			}
		}
		consulta.insert(0, "From BitacoraRelcompras ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<BitacoraRelcompras> consultaBitacoraDif99(String folioCartaAdhesion, String status, Boolean contieneArchivo)throws  JDBCException{		
		List<BitacoraRelcompras> lst = null;
		StringBuilder consulta= new StringBuilder();
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion = '").append(folioCartaAdhesion).append("'");
		}		
		
		if(consulta.length()>0){
			consulta.append(" and idCriterio <> 99");
		}else{
			consulta.append(" where idCriterio <> 99");
		}
		
		if (status != null &&  !status.isEmpty() ){
			if(consulta.length()>0){
				consulta.append(" and status in (").append(status).append(")");
			}else{
				consulta.append("where status in (").append(status).append(")");
			}
		}
		
		if(contieneArchivo!= null){
			if(contieneArchivo){
				if(consulta.length()>0){
					consulta.append(" and nombreArchivo is not null");
				}else{
					consulta.append("where nombreArchivo is not null");
				}
			}else{
				if(consulta.length()>0){
					consulta.append(" and nombreArchivo is null");
				}else{
					consulta.append("where nombreArchivo is null");
				}
			}
		}
		consulta.insert(0, "From BitacoraRelcompras ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}


	
	@SuppressWarnings("unchecked")
	public List<BitacoraRelcomprasDetalle> consultaBitacoraRelcomprasDetalle(long idBitacoraRelcompras)throws  JDBCException{
		List<BitacoraRelcomprasDetalle> lst = null;
		StringBuilder consulta= new StringBuilder();
		
		if (idBitacoraRelcompras != 0 &&  idBitacoraRelcompras != -1){
			consulta.append("where idBitacoraRelcompras=").append(idBitacoraRelcompras);
		}		
		consulta.insert(0, "From BitacoraRelcomprasDetalle ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<ProductorExisteBD> verificaSiexisteProductor(String folioCartaAdhesion, int idPrograma)throws  JDBCException{	// AHS 29012015
		List<ProductorExisteBD> lst = null;		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select distinct productor, nombre_productor, paterno_productor, materno_productor, curp_productor, rfc_productor  ")
				.append("from relacion_compras_tmp r ")
				.append(" WHERE folio_carta_adhesion = '").append(folioCartaAdhesion)
				.append("' AND productor is not null ")
//				.append(" AND NOT EXISTS (select 1 from productores where productor = r.productor)");  AHS 29012015
				.append(" AND NOT EXISTS (select distinct 1 from predios_relaciones p where p.productor = r.productor and id_programa = ")  // AHS 29012015
				.append(idPrograma).append( ")");	// AHS 29012015
				
		lst= session.createSQLQuery(consulta.toString()).addEntity(ProductorExisteBD.class).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProductorPredioValidado> verificaProductorAsociadoPredioValidado(String folioCartaAdhesion, int idPrograma)throws  JDBCException{
		List<ProductorPredioValidado> lst = null;		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select distinct productor, nombre_productor, paterno_productor, materno_productor, curp_productor, rfc_productor  ")
				.append("from relacion_compras_tmp r ")
				.append(" WHERE folio_carta_adhesion = '").append(folioCartaAdhesion)
				.append("' AND productor is not null ")
				.append(" AND EXISTS (select distinct 1 from predios_relaciones p where p.productor = r.productor and validado ='NO' and id_programa = ") // AHS 29012015
							.append(idPrograma).append( ")");
				
		lst= session.createSQLQuery(consulta.toString()).addEntity(ProductorPredioValidado.class).list();
		return lst;	
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProductorPredioPagado> verificaProductorAsociadoPredioPagado(String folioCartaAdhesion, int idPrograma)throws  JDBCException{
		List<ProductorPredioPagado> lst = null;		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select distinct productor, nombre_productor, paterno_productor, materno_productor, curp_productor, rfc_productor  ")
				.append("from relacion_compras_tmp r ")
				.append(" WHERE folio_carta_adhesion = '").append(folioCartaAdhesion)
				.append("' AND productor is not null ")
				.append(" AND EXISTS (select distinct 1 from predios_relaciones p where p.productor = r.productor and pagado ='NO' and id_programa = ") // AHS 29012015
							.append(idPrograma).append( ")");
				
		lst= session.createSQLQuery(consulta.toString()).addEntity(ProductorPredioPagado.class).list();
		return lst;	
	
	}	


	@SuppressWarnings("unchecked")
	public List<BoletasDuplicadas> verificaBoletaDuplicadasEnRelComprasTmp(String folioCartaAdhesion)throws  JDBCException{
		List<BoletasDuplicadas> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT ('A'||row_number() OVER ()) AS id, c.clave_bodega,  nombre_estado, c.folio_contrato, c.paterno_productor, c.materno_productor, c.nombre_productor, c.curp_productor, c.rfc_productor, c.boleta_ticket_bascula, c.vol_bol_ticket, c.fecha_entrada_boleta  ")
				.append("FROM relacion_compras_tmp c, ")
				.append("(select clave_bodega,  boleta_ticket_bascula  ")
				.append(" from relacion_compras_tmp ")
				.append("  where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append(" group by clave_bodega, boleta_ticket_bascula ")
				.append(" HAVING (COUNT(boleta_ticket_bascula) > 1)) v ")
				.append("WHERE c.boleta_ticket_bascula is not null")
				.append(" and c.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append(" and c.clave_bodega= v.clave_bodega  and c.boleta_ticket_bascula = v.boleta_ticket_bascula ")				
				.append("UNION ")
				.append("SELECT ('B'||row_number() OVER ()) AS id,  clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, boleta_ticket_bascula, vol_bol_ticket, fecha_entrada_boleta  ")
				.append("FROM relacion_compras_tmp r ")
				.append("WHERE boleta_ticket_bascula is not null").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("AND exists (SELECT 1 FROM compras_bodega_ticket_v where r.clave_bodega = clave_bodega and r.boleta_ticket_bascula = boleta_ticket_bascula) ")
				.append("GROUP BY clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, boleta_ticket_bascula, vol_bol_ticket, fecha_entrada_boleta ")
				.append("ORDER BY clave_bodega, nombre_estado, folio_contrato, boleta_ticket_bascula, paterno_productor, materno_productor, nombre_productor ");	
		System.out.println("bOLETAS DUPLICADAS "+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(BoletasDuplicadas.class).list();
		
		
		
//		select c.clave_bodega, c.folio_contrato, c.paterno_productor, c.materno_productor, c.nombre_productor, c.boleta_ticket_bascula, c.vol_bol_ticket, c.fecha_entrada_boleta
//		from relacion_compras_tmp c, 
//		(select clave_bodega,  boleta_ticket_bascula 
//		from relacion_compras_tmp  
//		group by clave_bodega, boleta_ticket_bascula
//		HAVING (COUNT(boleta_ticket_bascula) > 1)) v
//		where c.clave_bodega= v.clave_bodega  
//		and c.boleta_ticket_bascula = v.boleta_ticket_bascula 
		
		return lst;
	}
	
	//"La suma total de las Boletas sea mayor al volumen facturado"
	@SuppressWarnings("unchecked")
	public List<BoletasVsFacturas> verificaBoletaVsFacturas(String folioCartaAdhesion)throws  JDBCException{
		List<BoletasVsFacturas> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, clave_bodega, nombre_estado,folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor,sum(vol_bol_ticket) as vol_bol_ticket, sum( vol_total_fac_venta) as vol_total_fac_venta, 0 as diferencia_volumen  ")
				.append("FROM relacion_compras_tmp ")
				.append("WHERE folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("GROUP BY clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor ")
				.append("HAVING (sum(vol_bol_ticket) < sum( vol_total_fac_venta) ) ")
				.append("ORDER BY  paterno_productor, materno_productor, nombre_productor");				
		lst= session.createSQLQuery(consulta.toString()).addEntity(BoletasVsFacturas.class).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductorFactura> verificaFacturaDuplicadasEnRelComprasTmp(String folioCartaAdhesion)throws  JDBCException{
		List<ProductorFactura> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT ('A'||row_number() OVER ()) AS id, c.clave_bodega,  nombre_estado, c.folio_contrato, c.paterno_productor, c.materno_productor, c.nombre_productor, c.curp_productor, c.rfc_productor, c.folio_factura_venta, c.vol_total_fac_venta, c.fecha_emision_fac ")
				.append("FROM relacion_compras_tmp c, ")
				.append(" (select  folio_factura_venta ")
				.append(" from relacion_compras_tmp ")
				.append("group by folio_factura_venta ")
				.append("HAVING (COUNT(folio_factura_venta) > 1)) v ")
				.append("WHERE c.folio_factura_venta is not null").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append(" and c.folio_factura_venta is not null ")
				.append("and c.folio_factura_venta = v.folio_factura_venta ")
				.append("UNION ")
				.append("SELECT ('B'||row_number() OVER ()) AS id, clave_bodega, nombre_estado, folio_contrato,  paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_factura_venta, vol_total_fac_venta, fecha_emision_fac  ")
				.append("FROM relacion_compras_tmp r ")
				.append("WHERE folio_factura_venta is not null").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("AND exists (select 1 from productor_facturas_v where r.productor = folio_productor and r.folio_factura_venta = folio_fac) ")
				.append("ORDER BY 1,2,3,4 ");
		System.out.println("Facturas Duplicadas "+consulta.toString());
				
		lst= session.createSQLQuery(consulta.toString()).addEntity(ProductorFactura.class).list();
			
		return lst; 
	}
	
	@SuppressWarnings("unchecked")
	public List<RfcProductorVsRfcFactura> verificaRFCProductorVsRFCFactura(String folioCartaAdhesion, int idPrograma)throws  JDBCException{ // AHS 29012015
		List<RfcProductorVsRfcFactura> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, p.rfc rfc_productor, r.rfc_fac_venta , ") // AHS 29012015
				.append("(select COALESCE(sum(vol_total_fac_venta),0) from relacion_compras_tmp r1 where r.clave_bodega = r1.clave_bodega and  r.nombre_estado = r1.nombre_estado and  r.nombre_estado = r1.nombre_estado " )
				.append(" and  r.folio_contrato = r1.folio_contrato and COALESCE(r.paterno_productor,'X') =  COALESCE(r1.paterno_productor,'X') and  COALESCE(r.materno_productor,'X') =  COALESCE(r1.materno_productor,'X' )")
				.append(" and  COALESCE(r.nombre_productor,'X') =  COALESCE(r1.nombre_productor,'X')  ) as vol_total_fac_venta ")
				.append("FROM relacion_compras_tmp r, productores p ") // AHS 29012015
				//.append("WHERE rfc_productor <> rfc_fac_venta ").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ") // AHS 29012015
				.append("WHERE r.rfc_fac_venta is not null ").append(" and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ") // AHS 29012015
				.append("and p.productor = r.productor ").append("and p.rfc <> r.rfc_fac_venta ") // AHS 29012015
				.append(" AND EXISTS (select distinct 1 from predios_relaciones pr where pr.productor = p.productor and pr.id_programa = ").append(idPrograma).append( ")") // AHS 29012015
				.append("GROUP BY r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, curp_productor, p.rfc, r.rfc_fac_venta ");		
		
		System.out.println("Consulta RFC "+consulta.toString());
		
		lst= session.createSQLQuery(consulta.toString()).addEntity(RfcProductorVsRfcFactura.class).list();
		
			
		return lst; 
	}
	
	public List<RelacionComprasTMP> verificaRFCVsCURPProductor(String folioCartaAdhesion)throws  JDBCException{ 
		List<RelacionComprasTMP> lst =new ArrayList<RelacionComprasTMP>();
		StringBuilder consulta= new StringBuilder();
//		consulta.append("SELECT  r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor,  rfc_productor, r.curp_productor, ") 
//				.append("(select COALESCE(sum(vol_total_fac_venta),0) from relacion_compras_tmp r1 where r.clave_bodega = r1.clave_bodega and  r.nombre_estado = r1.nombre_estado and  r.nombre_estado = r1.nombre_estado " )
//				.append(" and  COALESCE(r.paterno_productor,'X') =  COALESCE(r1.paterno_productor,'X') and  COALESCE(r.materno_productor,'X') =  COALESCE(r1.materno_productor,'X' )")
//				.append(" and  COALESCE(r.nombre_productor,'X') =  COALESCE(r1.nombre_productor,'X')  ) as vol_total_fac_venta ")
//				.append("FROM relacion_compras_tmp r ") 
//				.append("WHERE  coalesce(substring(rfc_productor,1,10),'X') <>  coalesce(substring(curp_productor,1,10),'X') ")
//				.append(" and  rfc_productor is not null and curp_productor is not null ").append(" and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ") 
//				.append("GROUP BY r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.rfc_productor, r.curp_productor ");		

		consulta.append("SELECT  r.folio_carta_adhesion, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.rfc_productor, r.curp_productor, ")
				.append("(select COALESCE(sum(vol_total_fac_venta),0) ")
				.append("from relacion_compras_tmp r1 ")
				.append("where r.folio_carta_adhesion = r1.folio_carta_adhesion ") 
				.append("and r.clave_bodega = r1.clave_bodega ")
				.append("and r.nombre_estado = r1.nombre_estado ")
				.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r1.curp_productor,r1.rfc_productor)) as vol_total_fac_venta ") 
				.append("FROM relacion_compras_tmp r ")
				.append("WHERE (r.rfc_productor is not null or r.curp_productor is not null ) ")
				.append("and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ") 
				.append("and NOT EXISTS (SELECT 1 FROM pagos_topes_relaciones p ")     
				.append("WHERE (p.curp_productor = r.curp_productor OR p.rfc_productor = r.rfc_productor) ")
				.append("AND p.variedad = r.id_variedad ")
				.append("AND p.id_programa = r.id_programa ") 
				.append("AND p.rfc_comprador = r.rfc_comprador ")
				.append("AND p.estado = r.estado_acopio) ")
				.append("GROUP BY r.folio_carta_adhesion, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.rfc_productor, r.curp_productor ");

		System.out.println("Consulta RFC sin contrato "+consulta.toString());
		
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list(); 
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			RelacionComprasTMP  r = new RelacionComprasTMP();
			r.setClaveBodega((String) row.get("clave_bodega"));
			r.setNombreEstado((String) row.get("nombre_estado"));
			r.setFolioContrato((String) row.get("folio_contrato"));
			r.setNombreProductor((String) row.get("nombre_productor"));
			r.setPaternoProductor((String) row.get("paterno_productor"));
			r.setMaternoProductor((String) row.get("materno_productor"));
			r.setRfcProductor((String) row.get("rfc_productor"));
			r.setCurpProductor((String) row.get("curp_productor"));	
			BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
			r.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			lst.add(r);
		}
			
		return lst; 
	}

	
	@SuppressWarnings("unchecked")
	public List<FacturasVsPago> verificaFacturasVsPago(String folioCartaAdhesion)throws JDBCException{
		List<FacturasVsPago> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor,")
				.append("COALESCE(sum(imp_sol_fac_venta),0) as imp_sol_fac_venta, COALESCE(sum(imp_total_pago_sinaxc),0) as imp_total_pago_sinaxc, ")
				.append("(COALESCE(sum(imp_sol_fac_venta),0) - COALESCE(sum(imp_total_pago_sinaxc),0)) as diferencia_importe, ")
				.append("COALESCE(sum(vol_total_fac_venta),0) as  vol_total_fac_venta ")
				.append("FROM relacion_compras_tmp ")
				.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("GROUP BY clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor ")
				.append("HAVING coalesce(sum(imp_total_pago_sinaxc),0) < coalesce(sum(imp_sol_fac_venta),0) and (COALESCE(sum(imp_sol_fac_venta),0) - COALESCE(sum(imp_total_pago_sinaxc),0)) > 1.00 ")
				.append("ORDER BY clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor");				
		lst= session.createSQLQuery(consulta.toString()).addEntity(FacturasVsPago.class).list();
		System.out.println("Query pagos vs facturas: "+consulta.toString());
		return lst;
	}	
	
	public List<RelacionComprasTMP> verificaSiPrediosExistenBD(String folioCartaAdhesion, int idPrograma)throws JDBCException{
		List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
		StringBuilder consulta= new StringBuilder();
		
		consulta.append("select pc.clave_bodega, pc.nombre_estado, pc.folio_contrato,  pc.folio_carta_adhesion, pc.id_programa, pc.paterno_productor, pc.materno_productor, pc.nombre_productor, pc.curp_productor, pc.rfc_productor, pc.predio, 'FALTA PREDIO' as leyenda ")
				.append("from  ")
				.append("(select distinct r.folio_carta_adhesion, r.clave_bodega, r.nombre_estado,r.paterno_productor, r.materno_productor, r.nombre_productor, r.folio_contrato, r.id_programa, r.curp_productor, r.rfc_productor, p.predio ")
				.append("from relacion_compras_tmp  r, predios_relaciones p ")
				.append("WHERE r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("and  r.folio_predio is not null ")
				.append("and  r.id_programa =").append(idPrograma) 
				.append("and COALESCE(r.curp_productor,r.rfc_productor) = COALESCE(p.curp, p.rfc) ")
				.append("and  r.id_programa = p.id_programa) pc ")
				.append("where not exists ")
				.append("(select 1 from relacion_compras_tmp  r1 ") 
				.append("where r1.folio_carta_adhesion = pc.folio_carta_adhesion ")
				.append("and r1.clave_bodega = pc.clave_bodega ")
				.append("and r1.nombre_estado = pc.nombre_estado ")
				.append("and coalesce(r1.folio_contrato,'X') = coalesce(pc.folio_contrato,'X') ")
				.append("and r1.folio_predio is not null ")
			    .append("and r1.id_programa = pc.id_programa ") 
			    .append("and r1.folio_predio = pc.predio ")
			    .append("and COALESCE(r1.curp_productor,r1.rfc_productor) = COALESCE(pc.curp_productor, pc.rfc_productor) ) ")
//			    .append("order by pc.folio_carta_adhesion, pc.id_programa, pc.curp_productor, pc.rfc_productor, pc.predio ")
			    .append("UNION ")
			    .append("select clave_bodega, nombre_estado, folio_contrato,  folio_carta_adhesion, id_programa, ")
				.append("paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_predio  as predio, 'PRED NO ASOC A PROD' as leyenda ")
				.append("from relacion_compras_tmp ")
				.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("and id_programa =").append(idPrograma)
				.append("and folio_predio is not null ")
				.append("and (COALESCE(curp_productor,'X'),COALESCE(rfc_productor,'X')) in ") 
				.append("(select distinct COALESCE(r1.curp_productor,'X'),COALESCE(r1.rfc_productor,'X') ")
				.append("from relacion_compras_tmp  r1 ")
				.append("WHERE r1.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("and r1.id_programa =").append(idPrograma)
				.append("and r1.folio_predio is not null ") 
				.append("and not exists ")
				.append("(select 1 ")
				.append("from predios_relaciones pr ")
				.append("where pr.id_programa = ").append(idPrograma)
				.append("and COALESCE(pr.curp, pr.rfc) = COALESCE(r1.curp_productor,r1.rfc_productor))) ")
				.append("order by 4, 5, 9, 10, 11 ");
		
		System.out.println("Predios que no estan pagados "+consulta.toString());
		
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			RelacionComprasTMP b = new RelacionComprasTMP();
//			Integer id  = (Integer) row.get("id");
//			b.setIdRelacionComprasTmp(id.longValue());
			b.setClaveBodega((String) row.get("clave_bodega"));
		    b.setNombreEstado((String) row.get("nombre_estado"));
		    b.setPaternoProductor((String) row.get("paterno_productor"));
		    b.setMaternoProductor((String) row.get("materno_productor"));
		    b.setNombreProductor((String) row.get("nombre_productor"));
		    b.setCurpProductor((String) row.get("curp_productor"));
		    b.setRfcProductor((String) row.get("rfc_productor"));
		    b.setFolioPredio((String) row.get("predio"));
		    b.setBancoSinaxc((String) row.get("leyenda"));
		    lst.add(b);
		}

		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PrediosNoValidadosDRDE> verificaPrediosNoValidadosDRDE(String folioCartaAdhesion, int idPrograma)throws JDBCException{
		List<PrediosNoValidadosDRDE> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("select distinct folio_predio, folio_predio_sec, null AS folio_predio_alterno, id_relacion_compras_tmp AS id ")
		.append("from relacion_compras_tmp  r ")
		.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and  folio_predio is not null ")
		.append("and folio_predio_sec is not null ")
		.append("and exists ")
			.append("(select 1 ")
			.append("from predios_relaciones p ")
			.append("where p.predio = r.folio_predio ")
			.append("and p.predio_secuencial = r.folio_predio_sec and validado = 'NO' and p.id_programa =").append(idPrograma).append(") ")
		.append("UNION ")
		.append("select  distinct NULL AS folio_predio, 0 AS folio_predio_sec, folio_predio_alterno, id_relacion_compras_tmp AS id ")
		.append("from relacion_compras_tmp  r ")
		.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and  folio_predio_alterno is not null ")
		.append("and  exists ")
			.append("(select 1")
			.append("from predios_relaciones p ")
			.append("where p.predio_alterno = r.folio_predio_alterno and validado = 'NO' and p.id_programa =").append(idPrograma).append(") ");
		
		lst= session.createSQLQuery(consulta.toString()).addEntity(PrediosNoValidadosDRDE.class).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<PrediosNoPagados> verificaPrediosNoPagados(String folioCartaAdhesion, int idPrograma)throws JDBCException{
		List<PrediosNoPagados> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("select distinct folio_predio, folio_predio_sec, null AS folio_predio_alterno, id_relacion_compras_tmp AS id ")
		.append("from relacion_compras_tmp  r ")
		.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and folio_predio is not null ")
		.append("and folio_predio_sec is not null ")
		.append("and exists ")
			.append("(select 1 ")
			.append("from predios_relaciones p ")
			.append("where p.predio = r.folio_predio ")
			.append("and p.predio_secuencial = r.folio_predio_sec and pagado = 'NO' and p.id_programa =").append(idPrograma).append(") ")
		.append("UNION ")
		.append("select  distinct NULL AS folio_predio, 0 AS folio_predio_sec, folio_predio_alterno, id_relacion_compras_tmp AS id ")
		.append("from relacion_compras_tmp  r ")
		.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and  folio_predio_alterno is not null ")
		.append("and exists ")
			.append("(select 1")
			.append("from predios_relaciones p ")
			.append("where p.predio_alterno = r.folio_predio_alterno and pagado = 'NO' and p.id_programa =").append(idPrograma).append(") ");
		
		lst= session.createSQLQuery(consulta.toString()).addEntity(PrediosNoPagados.class).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<BoletasFueraDePeriodo> verificaBoletasFueraDePeriodo(String folioCartaAdhesion, String fechaInicio, String fechaFin)throws JDBCException{
		List<BoletasFueraDePeriodo> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, clave_bodega, estado_acopio, nombre_estado, folio_contrato, nombre_productor, paterno_productor, materno_productor, curp_productor, rfc_productor, boleta_ticket_bascula, fecha_entrada_boleta, vol_bol_ticket ")
		.append("from relacion_compras_tmp ")
		.append("WHERE boleta_ticket_bascula is not null").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and (TO_CHAR(fecha_entrada_boleta,'YYYY-MM-DD')) not between '").append(fechaInicio).append("'").append(" and '").append(fechaFin).append("'")
		.append("group by clave_bodega, estado_acopio, nombre_estado, folio_contrato, nombre_productor, paterno_productor, materno_productor,  curp_productor, rfc_productor, boleta_ticket_bascula, fecha_entrada_boleta, vol_bol_ticket ")
		.append("order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");	
		lst= session.createSQLQuery(consulta.toString()).addEntity(BoletasFueraDePeriodo.class).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<BoletasFueraDePeriodoPago> verificaBoletasFueraDePeriodoPago(String folioCartaAdhesion)throws JDBCException{
		List<BoletasFueraDePeriodoPago> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id,  r.clave_bodega, r.nombre_estado, r.paterno_productor, r.materno_productor, r.nombre_productor,  r.boleta_ticket_bascula, r.folio_contrato, r.fecha_entrada_boleta, r.vol_bol_ticket, c.periodo_inicial_pago, c.periodo_final_pago ")
		.append("from relacion_compras_tmp r, contratos_relacion_compras c ")
		.append("WHERE r.boleta_ticket_bascula is not null and r.folio_contrato is not null and r.folio_contrato = c.folio_contrato")
		.append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by  r.clave_bodega,  r.nombre_estado,  r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.boleta_ticket_bascula, r.fecha_entrada_boleta, r.vol_bol_ticket,  c.periodo_inicial_pago, c.periodo_final_pago ")
		.append("having to_number(TO_CHAR(r.fecha_entrada_boleta,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999')")
		.append("order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");	
		System.out.println("Boletas Fuera de Periodo Contrato sin adendum: "+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(BoletasFueraDePeriodoPago.class).list();
		return lst;		
	}	
	
	
	public List<BoletasFueraDePeriodoPago> verificaBoletasFueraDePeriodoContratoAdendum(String folioCartaAdhesion)throws JDBCException{
		List<BoletasFueraDePeriodoPago> lst = new ArrayList<BoletasFueraDePeriodoPago>();
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id,  r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.boleta_ticket_bascula,  r.fecha_entrada_boleta, r.vol_bol_ticket, r.periodo_inicial_contrato, r.periodo_final_contrato ")
		.append("from relacion_compras_tmp r ")
		.append("WHERE r.boleta_ticket_bascula is not null and r.folio_contrato is not null ")
		.append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by  r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.boleta_ticket_bascula, r.fecha_entrada_boleta, r.vol_bol_ticket,  r.periodo_inicial_contrato, r.periodo_final_contrato  ")
		.append("having to_number(TO_CHAR(r.fecha_entrada_boleta,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999')")
		.append("order by clave_bodega, nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor");
		System.out.println("BoletasFuera de Per x contrato Aplica Adendum: "+consulta.toString());
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>) object;
			BoletasFueraDePeriodoPago b = new BoletasFueraDePeriodoPago();
			b.setClaveBodega((String) row.get("clave_bodega"));
			b.setNombreEstado((String) row.get("nombre_estado"));
			b.setFolioContrato((String) row.get("folio_contrato"));
			b.setPaternoProductor((String) row.get("paterno_productor"));
			b.setMaternoProductor((String) row.get("materno_productor"));
			b.setNombreProductor((String) row.get("nombre_productor"));
			b.setBoletaTicketBascula((String) row.get("boleta_ticket_bascula"));
			b.setFechaEntradaBoleta((Date) row.get("fecha_entrada_boleta"));
			BigDecimal valor = (BigDecimal) row.get("vol_bol_ticket");
			b.setVolBolTicket(valor!=null ? valor.doubleValue():null);
			b.setPeriodoInicialPago((Date) row.get("periodo_inicial_contrato"));
			b.setPeriodoFinalPago((Date) row.get("periodo_final_contrato"));	
			lst.add(b);			
		}
		return lst;

	}	
	
	
	@SuppressWarnings("unchecked")
	public List<FacturaFueraPeriodoAuditor> verificaFacturaFueraDePeriodo(String folioCartaAdhesion, String fechaInicio, String fechaFin)throws JDBCException{
		List<FacturaFueraPeriodoAuditor> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_factura_venta, fecha_emision_fac, vol_total_fac_venta ")
		.append("from relacion_compras_tmp ")
		.append("WHERE folio_factura_venta is not null").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and (TO_CHAR(fecha_emision_fac,'YYYY-MM-DD')) not between '").append(fechaInicio).append("'").append(" and '").append(fechaFin).append("'")
		.append("group by clave_bodega,  nombre_estado, folio_contrato, nombre_productor, paterno_productor, materno_productor,  curp_productor, rfc_productor, folio_factura_venta, fecha_emision_fac, vol_total_fac_venta ")
		.append("order by clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		lst= session.createSQLQuery(consulta.toString()).addEntity(FacturaFueraPeriodoAuditor.class).list();
		
		System.out.println("Fac fuera de periodo sin complemento 1 o 3: "+consulta.toString());
		
		return lst;
	}
		
	@SuppressWarnings("unchecked")
	public List<FacturaFueraPeriodoAuditor> verificaFacturaFueraDePeriodoAudLinComplemento(String folioCartaAdhesion, String fechaInicio, String fechaFin, String fechaInicioCom, String fechaFinCom )throws JDBCException{
		List<FacturaFueraPeriodoAuditor> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_factura_venta, fecha_emision_fac, vol_total_fac_venta ")
		.append("from relacion_compras_tmp ")
		.append("WHERE folio_factura_venta is not null").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		//.append("and (TO_CHAR(fecha_emision_fac,'YYYY-MM-DD')) not between '").append(fechaInicio).append("'").append(" and '").append(fechaFin).append("'")
		.append("group by clave_bodega,  nombre_estado, folio_contrato, nombre_productor, paterno_productor, materno_productor, curp_productor, rfc_productor, folio_factura_venta, fecha_emision_fac, vol_total_fac_venta ")
		.append("HAVING (to_number(TO_CHAR(fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number('").append(fechaInicio).append("','99999999') and to_number('").append(fechaFin).append("','99999999')) ")
		.append("AND (to_number(TO_CHAR(fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number('").append(fechaInicioCom).append("','99999999') and to_number('").append(fechaFinCom).append("','99999999'))")		
		.append("order by clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		lst= session.createSQLQuery(consulta.toString()).addEntity(FacturaFueraPeriodoAuditor.class).list();
		System.out.println("Fac Fuera de periodo 1 o 3 complemento "+consulta.toString());
		
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FacturaFueraPeriodoPago> verificaFacturaFueraDePeriodoContrato(String folioCartaAdhesion)throws JDBCException{
		List<FacturaFueraPeriodoPago> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_factura_venta,  r.fecha_emision_fac, r.vol_total_fac_venta, c.periodo_inicial_pago, c.periodo_final_pago ")
		.append("from relacion_compras_tmp r, contratos_relacion_compras c ")
		.append("WHERE r.folio_factura_venta is not null and r.folio_contrato is not null and r.folio_contrato = c.folio_contrato ").append(" and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by  r.clave_bodega,  r.nombre_estado,r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_factura_venta, r.fecha_emision_fac, r.vol_total_fac_venta, c.periodo_inicial_pago, c.periodo_final_pago ")
		.append("having to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999')")
		.append("order by r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor ");		
		System.out.println("Fac Fuera de Periodo Contrato-SinComplemento "+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(FacturaFueraPeriodoPago.class).list();		
		return lst;
	}

	
	public List<FacturaFueraPeriodoPago> verificaFacturaFueraDePeriodoContratoComplemento(String folioCartaAdhesion, String fechaInicio, String fechaFin)throws JDBCException{		
		List<FacturaFueraPeriodoPago> lst = new ArrayList<FacturaFueraPeriodoPago>();
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, ")
			.append("r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_factura_venta,  r.fecha_emision_fac, r.vol_total_fac_venta, ")
			.append("CASE WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999') ") 
			.append("AND  to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
			.append("c.periodo_inicial_pago ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN '").append(fechaInicio).append("' ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') THEN ") 
			.append("c.periodo_inicial_pago ")
			.append("ELSE NULL ")
			.append("END periodo_inicial_contrato, ")
			.append("CASE WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999') ") 
			.append("AND  to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
			.append("c.periodo_final_pago ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN '").append(fechaFin).append("' ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') THEN ") 
			.append("c.periodo_final_pago ")
			.append("ELSE NULL ")
			.append("END periodo_final_contrato ")
			.append("from relacion_compras_tmp r, contratos_relacion_compras c ")
			.append("WHERE r.folio_factura_venta is not null and r.folio_contrato is not null and r.folio_contrato = c.folio_contrato and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("group by  r.clave_bodega,  r.nombre_estado,r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor,r.curp_productor, r.rfc_productor, r.folio_factura_venta, r.fecha_emision_fac, r.vol_total_fac_venta, c.periodo_inicial_pago, c.periodo_final_pago ") 
			.append("having (to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999')) ")
			.append("AND (to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number('").append(fechaInicio).append("','99999999') and to_number('").append(fechaFin).append("','99999999')) ")
			.append("order by r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor ");
		//lst= session.createSQLQuery(consulta.toString()).addEntity(FacturaFueraPeriodoPago.class).list();
		
		System.out.println(" Factura Fuera De Periodo Contrato Complemento "+consulta.toString());
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>) object;
			FacturaFueraPeriodoPago b = new FacturaFueraPeriodoPago();
			b.setClaveBodega((String) row.get("clave_bodega"));
			b.setNombreEstado((String) row.get("nombre_estado"));
			b.setFolioContrato((String) row.get("folio_contrato"));
			b.setPaternoProductor((String) row.get("paterno_productor"));
			b.setMaternoProductor((String) row.get("materno_productor"));
			b.setNombreProductor((String) row.get("nombre_productor"));
			b.setCurpProductor((String) row.get("curp_productor"));
			b.setRfcProductor((String) row.get("rfc_productor"));
			b.setFolioFacturaVenta((String) row.get("folio_factura_venta"));
			b.setFechaEmisionFac((Date) row.get("fecha_emision_fac"));
			BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
			b.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			b.setPeriodoInicialPago((Date) row.get("periodo_inicial_contrato"));
			b.setPeriodoFinalPago((Date) row.get("periodo_final_contrato"));	
			lst.add(b);			
		}
		
		return lst;
	}

	
	public List<FacturaFueraPeriodoPago> verificaFacturaFueraDePeriodoContratoAdendum(String folioCartaAdhesion)throws JDBCException{
		List<FacturaFueraPeriodoPago> lst = new ArrayList<FacturaFueraPeriodoPago>();
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.folio_factura_venta,  r.fecha_emision_fac, r.vol_total_fac_venta, r.periodo_inicial_contrato, r.periodo_final_contrato ")
		.append("from relacion_compras_tmp r ")
		.append("WHERE r.folio_factura_venta is not null ")
		.append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by  r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.folio_factura_venta, r.fecha_emision_fac, r.vol_total_fac_venta,  r.periodo_inicial_contrato, r.periodo_final_contrato  ")
		.append("having to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999')")
		.append("order by clave_bodega, nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor");	
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>) object;
			FacturaFueraPeriodoPago b = new FacturaFueraPeriodoPago();
			b.setClaveBodega((String) row.get("clave_bodega"));
			b.setNombreEstado((String) row.get("nombre_estado"));
			b.setFolioContrato((String) row.get("folio_contrato"));
			b.setPaternoProductor((String) row.get("paterno_productor"));
			b.setMaternoProductor((String) row.get("materno_productor"));
			b.setNombreProductor((String) row.get("nombre_productor"));
			b.setFolioFacturaVenta((String) row.get("folio_factura_venta"));
			b.setFechaEmisionFac((Date) row.get("fecha_emision_fac"));
			BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
			b.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			b.setPeriodoInicialPago((Date) row.get("periodo_inicial_contrato"));
			b.setPeriodoFinalPago((Date) row.get("periodo_final_contrato"));	
			lst.add(b);			
		}
		return lst;

	}
	
	
	
	public List<FacturaFueraPeriodoPago> verificaFacturaFueraDePeriodoContratoAdendumComplemento(String folioCartaAdhesion, String fechaInicio, String fechaFin )throws JDBCException, ParseException{
		List<FacturaFueraPeriodoPago> lst = new ArrayList<FacturaFueraPeriodoPago>();
		StringBuilder consulta= new StringBuilder();

		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, ") 
			.append("r.folio_factura_venta,  r.fecha_emision_fac, r.vol_total_fac_venta, ")
			.append("CASE WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999') ") 
			.append("AND  to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
			.append("r.periodo_inicial_contrato ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN '").append(fechaInicio).append("' ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') THEN ") 
			.append("r.periodo_inicial_contrato ")
			.append("ELSE  NULL ")
			.append("END periodo_inicial_contrato, ")
			.append("CASE WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999') ") 
			.append("AND  to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
			.append("r.periodo_final_contrato ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN  '").append(fechaFin).append("' ")
			.append("WHEN to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') THEN ") 
			.append("r.periodo_final_contrato ELSE NULL ")
			.append("END periodo_final_contrato ")
			.append("from relacion_compras_tmp r ")
			.append("WHERE r.folio_factura_venta is not null and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("group by  r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.folio_factura_venta, r.fecha_emision_fac, r.vol_total_fac_venta,  r.periodo_inicial_contrato, r.periodo_final_contrato ")
			.append("HAVING (to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999')) ")
			.append("AND (to_number(TO_CHAR(r.fecha_emision_fac,'YYYYMMDD'),'99999999') not between to_number('").append(fechaInicio).append("','99999999') and to_number('").append(fechaFin).append("','99999999')) ")
		    .append("order by clave_bodega, nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		
		System.out.println(" FacturaFueraDePeriodoContratoAdendumComplemento "+consulta.toString());
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>) object;
			FacturaFueraPeriodoPago b = new FacturaFueraPeriodoPago();
			b.setClaveBodega((String) row.get("clave_bodega"));
			b.setNombreEstado((String) row.get("nombre_estado"));
			b.setFolioContrato((String) row.get("folio_contrato"));
			b.setPaternoProductor((String) row.get("paterno_productor"));
			b.setMaternoProductor((String) row.get("materno_productor"));
			b.setNombreProductor((String) row.get("nombre_productor"));
			b.setFolioFacturaVenta((String) row.get("folio_factura_venta"));
			b.setFechaEmisionFac((Date) row.get("fecha_emision_fac"));
			BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
			b.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			b.setPeriodoInicialPago((Date) row.get("periodo_inicial_contrato"));
			b.setPeriodoFinalPago((Date) row.get("periodo_final_contrato"));	
			lst.add(b);			
		}
		
		//lst= session.createSQLQuery(consulta.toString()).addEntity(FacturaFueraPeriodoPago.class).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ChequeFueraPeriodoAuditor> verificaChequeFueraDePeriodo(String folioCartaAdhesion, String fechaInicio, String fechaFin)throws JDBCException{
		List<ChequeFueraPeriodoAuditor> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, folio_carta_adhesion, clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_doc_pago, banco_sinaxc, fecha_doc_pago_sinaxc, imp_total_pago_sinaxc, ")
		.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp rt where  rt.clave_bodega = r.clave_bodega and coalesce(rt.folio_contrato,'X') = coalesce(r.folio_contrato,'X') ")
//		.append("rt.nombre_productor = r.nombre_productor and coalesce(rt.paterno_productor, '') = coalesce(r.paterno_productor, '')")
//		.append(" and coalesce(rt.materno_productor,'') = coalesce(r.materno_productor,'') and rt.folio_carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta ")
		.append(" and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(rt.curp_productor,rt.rfc_productor) and rt.folio_carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta ")
		.append("from relacion_compras_tmp r ")
		.append("WHERE folio_doc_pago is not null  ").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and (TO_CHAR(fecha_doc_pago_sinaxc,'YYYY-MM-DD')) not between '").append(fechaInicio).append("'").append(" and '").append(fechaFin).append("'")
		.append("group by  folio_carta_adhesion, clave_bodega,  nombre_estado,  folio_contrato, nombre_productor, paterno_productor, materno_productor, curp_productor, rfc_productor, folio_doc_pago, banco_sinaxc, fecha_doc_pago_sinaxc, imp_total_pago_sinaxc ")
		.append("order by clave_bodega,  nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		System.out.println("Cheques Fuera Periodo sin Complemento "+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(ChequeFueraPeriodoAuditor.class).list();
		
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ChequeFueraPeriodoAuditor> verificaChequeFueraDePeriodoAudLinComplemento(String folioCartaAdhesion, String fechaInicio, String fechaFin, String fechaInicioC, String fechaFinC)throws JDBCException{
		List<ChequeFueraPeriodoAuditor> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, folio_carta_adhesion, clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, folio_doc_pago, banco_sinaxc, fecha_doc_pago_sinaxc, imp_total_pago_sinaxc, ")
		.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp rt where  rt.clave_bodega = r.clave_bodega and coalesce(rt.folio_contrato,'') = coalesce(r.folio_contrato,'') and ")
		.append("rt.nombre_productor = r.nombre_productor and coalesce(rt.paterno_productor, '') = coalesce(r.paterno_productor, '')")
		.append(" and coalesce(rt.materno_productor,'') = coalesce(r.materno_productor,'') and rt.folio_carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta ")
		.append("from relacion_compras_tmp r ")
		.append("WHERE folio_doc_pago is not null  ").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		//.append("and (TO_CHAR(fecha_doc_pago_sinaxc,'YYYY-MM-DD')) not between '").append(fechaInicio).append("'").append(" and '").append(fechaFin).append("'")
		.append("group by  folio_carta_adhesion, clave_bodega,  nombre_estado,  folio_contrato, nombre_productor, paterno_productor, materno_productor, folio_doc_pago, banco_sinaxc, fecha_doc_pago_sinaxc, imp_total_pago_sinaxc ")
		.append("HAVING (TO_CHAR(fecha_doc_pago_sinaxc,'YYYYMMDD')) not between '").append(fechaInicio).append("'").append(" and '").append(fechaFin).append("' ")
		.append("AND (TO_CHAR(fecha_doc_pago_sinaxc,'YYYYMMDD')) not between '").append(fechaInicioC).append("'").append(" and '").append(fechaFinC).append("'")		
		.append("order by clave_bodega,  nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		
		System.out.println("Cheque fuera de periodo 1 o 3 complemento"+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(ChequeFueraPeriodoAuditor.class).list();
		
		
		return lst;
	}


	
	public List<ChequeFueraPeriodoContrato> verificaChequeFueraDePeriodoPagoAdendum(String folioCartaAdhesion)throws JDBCException{
		List<ChequeFueraPeriodoContrato> lst = new ArrayList<ChequeFueraPeriodoContrato>();
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc, r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc,  r.periodo_inicial_contrato, r.periodo_final_contrato, ")
		.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp rt where  rt.clave_bodega = r.clave_bodega and coalesce(rt.folio_contrato,'') = coalesce(r.folio_contrato,'') and ")
		.append("rt.nombre_productor = r.nombre_productor and coalesce(rt.paterno_productor, '') = coalesce(r.paterno_productor, '')")
		.append(" and coalesce(rt.materno_productor,'') = coalesce(r.materno_productor,'') and rt.folio_carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta ")
		.append("from relacion_compras_tmp r ")
		.append("WHERE r.folio_doc_pago is not null   ")
		.append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by r.folio_carta_adhesion, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc, r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc,  r.periodo_inicial_contrato, r.periodo_final_contrato ")
		.append("having to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999')")
		.append("order by clave_bodega, nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor");	
		SQLQuery query = session.createSQLQuery(consulta.toString());
		System.out.println("Pagos Fuera de periodo Adendum "+consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>) object;
			ChequeFueraPeriodoContrato b = new ChequeFueraPeriodoContrato();
			b.setClaveBodega((String) row.get("clave_bodega"));
			b.setNombreEstado((String) row.get("nombre_estado"));
			b.setFolioContrato((String) row.get("folio_contrato"));
			b.setPaternoProductor((String) row.get("paterno_productor"));
			b.setMaternoProductor((String) row.get("materno_productor"));
			b.setNombreProductor((String) row.get("nombre_productor"));
			b.setCurpProductor((String) row.get("curp_productor"));
			b.setRfcProductor((String) row.get("rfc_productor"));
			b.setFolioDocPago((String) row.get("folio_doc_pago"));
			b.setBancoSinaxc((String) row.get("banco_sinaxc"));
			b.setFechaDocPagoSinaxc((Date) row.get("fecha_doc_pago_sinaxc"));
			BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
			b.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			b.setPeriodoInicialPago((Date) row.get("periodo_inicial_contrato"));
			b.setPeriodoFinalPago((Date) row.get("periodo_final_contrato"));	
			valor = (BigDecimal) row.get("imp_total_pago_sinaxc");
			b.setImpTotalPagoSinaxc(valor!=null ? valor.doubleValue():null);
			lst.add(b);			
		}



		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ChequeFueraPeriodoContrato> verificaChequeFueraDePeriodoContrato(String folioCartaAdhesion)throws JDBCException{
		List<ChequeFueraPeriodoContrato> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc,  r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc, c.periodo_inicial_pago, c.periodo_final_pago, ")
		.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp rt where  rt.clave_bodega = r.clave_bodega and coalesce(rt.folio_contrato,'') = coalesce(r.folio_contrato,'') and ")
		.append("rt.nombre_productor = r.nombre_productor and coalesce(rt.paterno_productor, '') = coalesce(r.paterno_productor, '')")
		.append(" and coalesce(rt.materno_productor,'') = coalesce(r.materno_productor,'') and rt.folio_"
				+ "carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta ")
		.append("from relacion_compras_tmp r, contratos_relacion_compras c ")
		.append("WHERE folio_doc_pago is not null  and r.folio_contrato is not null and r.folio_contrato = c.folio_contrato ").append(" and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by folio_carta_adhesion, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc,  r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc, c.periodo_inicial_pago, c.periodo_final_pago ")
		.append("having to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999')")
		.append("order by r.clave_bodega, r.nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		
		System.out.println("Pagos Fuera Per sin Complemento "+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(ChequeFueraPeriodoContrato.class).list();
		AppLogger.info("app", "query---> "+consulta.toString());
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ChequeFueraPeriodoContrato> verificaChequeFueraDePeriodoContratoComplemento(String folioCartaAdhesion, String fechaInicio,String fechaFin)throws JDBCException{
		List<ChequeFueraPeriodoContrato> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc,  r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc")
		.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp rt where  rt.clave_bodega = r.clave_bodega and coalesce(rt.folio_contrato,'') = coalesce(r.folio_contrato,'') and ")
		.append("rt.nombre_productor = r.nombre_productor and coalesce(rt.paterno_productor, '') = coalesce(r.paterno_productor, '')")
		.append(" and coalesce(rt.materno_productor,'') = coalesce(r.materno_productor,'') and rt.folio_carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta, ")
		.append("CASE WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999') ") 
		.append("AND  to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
		.append("c.periodo_inicial_pago ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN '").append(fechaInicio).append("' ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') THEN ") 
		.append("c.periodo_inicial_pago ")
		.append("ELSE NULL ")
		.append("END periodo_inicial_contrato, ")
		.append("CASE WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999') ") 
		.append("AND  to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
		.append("c.periodo_final_pago ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN '").append(fechaFin).append("' ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') THEN ") 
		.append("c.periodo_final_pago ")
		.append("ELSE NULL ")
		.append("END periodo_final_contrato ")
		.append("from relacion_compras_tmp r, contratos_relacion_compras c ")
		.append("WHERE folio_doc_pago is not null  and r.folio_contrato is not null and r.folio_contrato = c.folio_contrato ").append(" and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("GROUP BY folio_carta_adhesion, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc,  r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc, c.periodo_inicial_pago, c.periodo_final_pago ")
		.append("HAVING to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(c.periodo_inicial_pago,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(c.periodo_final_pago,'YYYYMMDD'),'99999999')")
		.append("AND (to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') not between to_number('").append(fechaInicio).append("','99999999') and to_number('").append(fechaFin).append("','99999999')) ")
		.append("order by r.clave_bodega, r.nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor ");
		
		System.out.println("Pago fuera Per Contrato Complemento "+consulta.toString());
		lst= session.createSQLQuery(consulta.toString()).addEntity(ChequeFueraPeriodoContrato.class).list();
		
		
		return lst;
	}
	
	
	public List<ChequeFueraPeriodoContrato> verificaChequeFueraDePeriodoContratoAdendumComplemento(String folioCartaAdhesion, String fechaInicio, String fechaFin)throws JDBCException{
		List<ChequeFueraPeriodoContrato> lst = new ArrayList<ChequeFueraPeriodoContrato>();
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc, r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc, ")
		.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp rt where  rt.clave_bodega = r.clave_bodega and coalesce(rt.folio_contrato,'') = coalesce(r.folio_contrato,'') and ")
		.append("rt.nombre_productor = r.nombre_productor and coalesce(rt.paterno_productor, '') = coalesce(r.paterno_productor, '')")
		.append(" and coalesce(rt.materno_productor,'') = coalesce(r.materno_productor,'') and rt.folio_carta_adhesion  = r.folio_carta_adhesion ) as vol_total_fac_venta, ")
		.append("CASE WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999') ") 
		.append("AND  to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
		.append("r.periodo_inicial_contrato ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN '").append(fechaInicio).append("' ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') THEN ") 
		.append("r.periodo_inicial_contrato ")
		.append("ELSE  NULL ")
		.append("END periodo_inicial_contrato, ")
		.append("CASE WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999') ") 
		.append("AND  to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number('").append(fechaInicio).append("','99999999')  THEN ")
		.append("r.periodo_final_contrato ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') > to_number('").append(fechaFin).append("','99999999') THEN  '").append(fechaFin).append("' ")
		.append("WHEN to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') < to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') THEN ") 
		.append("r.periodo_final_contrato ELSE NULL ")
		.append("END periodo_final_contrato ")
		.append("from relacion_compras_tmp r ")
		.append("WHERE r.folio_doc_pago is not null   ")
		.append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("GROUP BY r.folio_carta_adhesion, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, r.folio_doc_pago, r.banco_sinaxc, r.fecha_doc_pago_sinaxc, r.imp_total_pago_sinaxc,  r.periodo_inicial_contrato, r.periodo_final_contrato ")
		.append("HAVING to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') not between to_number(TO_CHAR(r.periodo_inicial_contrato,'YYYYMMDD'),'99999999') and to_number(TO_CHAR(r.periodo_final_contrato,'YYYYMMDD'),'99999999')")
		.append("AND (to_number(TO_CHAR(r.fecha_doc_pago_sinaxc,'YYYYMMDD'),'99999999') not between to_number('").append(fechaInicio).append("','99999999') and to_number('").append(fechaFin).append("','99999999')) ")
		.append("ORDER BY clave_bodega, nombre_estado, r.folio_contrato, paterno_productor, materno_productor, nombre_productor");
		System.out.println("Pago Fuera Periodo Adendum Complemento "+consulta.toString());
		
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>) object;
			ChequeFueraPeriodoContrato b = new ChequeFueraPeriodoContrato();
			b.setClaveBodega((String) row.get("clave_bodega"));
			b.setNombreEstado((String) row.get("nombre_estado"));
			b.setFolioContrato((String) row.get("folio_contrato"));
			b.setPaternoProductor((String) row.get("paterno_productor"));
			b.setMaternoProductor((String) row.get("materno_productor"));
			b.setNombreProductor((String) row.get("nombre_productor"));
			b.setCurpProductor((String) row.get("curp_productor"));
			b.setRfcProductor((String) row.get("rfc_productor"));
			b.setFolioDocPago((String) row.get("folio_doc_pago"));
			b.setBancoSinaxc((String) row.get("banco_sinaxc"));
			b.setFechaDocPagoSinaxc((Date) row.get("fecha_doc_pago_sinaxc"));
			BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
			b.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			b.setPeriodoInicialPago((Date) row.get("periodo_inicial_contrato"));
			b.setPeriodoFinalPago((Date) row.get("periodo_final_contrato"));	
			valor = (BigDecimal) row.get("imp_total_pago_sinaxc");
			b.setImpTotalPagoSinaxc(valor!=null ? valor.doubleValue():null);
			lst.add(b);			
			
		}
		
		
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<ChequesDuplicadoBancoPartipante> verifiChequeDuplicadosPorEmpresaBanco(String folioCartaAdhesion)throws JDBCException{
		List<ChequesDuplicadoBancoPartipante> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("SELECT ('A'||row_number() OVER ()) AS id, c.clave_bodega,  nombre_estado, c.folio_contrato, c.paterno_productor, c.materno_productor, c.nombre_productor, ")
			.append("c.curp_productor, c.rfc_productor, c.folio_doc_pago, c.banco_sinaxc, ")
			.append("(select sum(vol_total_fac_venta) from relacion_compras_tmp r1 where r1.folio_carta_adhesion = c.folio_carta_adhesion and r1.clave_bodega = c.clave_bodega ")
			.append("and r1.nombre_estado = c.nombre_estado and coalesce(c.folio_contrato,'X') = coalesce(r1.folio_contrato,'X') ")
//			.append("and coalesce(r1.paterno_productor,'X') = coalesce(c.paterno_productor,'X') and  coalesce(r1.materno_productor,'X') = coalesce(c.materno_productor,'X') ")
//			.append("and coalesce(r1.nombre_productor,'X') = coalesce(c.nombre_productor,'X')) as vol_total_fac_venta ")
			.append("and COALESCE(r1.curp_productor, r1.rfc_productor) = COALESCE(c.curp_productor,c.rfc_productor)) as vol_total_fac_venta ")			
			.append("FROM relacion_compras_tmp c, ")
			.append("(select   r.id_comprador, r.folio_doc_pago, r.banco_sinaxc ")
			.append("from relacion_compras_tmp r  ")
			.append("where  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("group by r.id_comprador, r.folio_doc_pago,  r.banco_sinaxc ")
			.append("HAVING ( COUNT(folio_doc_pago) > 1) ) v ")
			.append("WHERE c.folio_doc_pago is not null ").append(" and c.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("and c.id_comprador= v.id_comprador  and c.folio_doc_pago = v.folio_doc_pago and c.banco_sinaxc = v.banco_sinaxc  order by c.folio_doc_pago, c.banco_sinaxc");	
//			.append("UNION ")
//			.append("select row_number() OVER ()  as id, r.id_comprador, r.id_tipo_doc_pago, r.folio_doc_pago, r.banco_id, r.banco_sinaxc ")
//			.append("from relacion_compras_tmp r ")
//			.append("where id_tipo_doc_pago = 1 and id_tipo_doc_pago is not null  and folio_carta_adhesion =  '").append(folioCartaAdhesion).append("' ")
//			.append("AND exists (SELECT 1 FROM compras_pago_prod_sin_axc_v cpa where r.id_comprador = cpa.id_comprador and r.id_tipo_doc_pago = cpa.id_tipo_doc_pago and r.folio_doc_pago= cpa.folio_doc_pago and r.banco_id = cpa.banco_id)");
//		
			System.out.println("Pagos duplicados "+consulta.toString());
			lst= session.createSQLQuery(consulta.toString()).addEntity(ChequesDuplicadoBancoPartipante.class).list();
			

		return lst;
	}	
	
	@SuppressWarnings("unchecked")
	public List<RelacionComprasTMP> consultaRelacionComprasTMPXfacturasVentas(String folioCartaAdhesion)throws JDBCException{
		List<RelacionComprasTMP> lst = null;
		StringBuilder consulta= new StringBuilder();		
		consulta.append("From RelacionComprasTMP where folioFacturaVenta is not null and numeroFacGlobal is not null ")
		.append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");		
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VolumenFacturaGlobalVsVolumenFacturas> verificaVolumenFacturaGlobalVsVolumenFacturas(String folioCartaAdhesion)throws JDBCException{
		List<VolumenFacturaGlobalVsVolumenFacturas> lst = null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("select row_number() OVER () as id, numero_fac_global, sum(vol_pna_fac_global) as volumen_global, sum(vol_total_fac_venta) as volumen_facturas ")
			.append("from relacion_compras_tmp  ")
			.append("where numero_fac_global is not null ").append(" and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("group by numero_fac_global ");	
		
			lst= session.createSQLQuery(consulta.toString()).addEntity(VolumenFacturaGlobalVsVolumenFacturas.class).list();

		return lst;
	}
	
	public int actualizaFechaTipoCambioXFactura(String numeroFacturaGlobal, String folioCartaAdhesion)throws JDBCException {
		int elementosActuializados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("UPDATE  relacion_compras_tmp  set fecha_tipo_cambio  = fecha_emision_fac ")
			.append("where numero_fac_global = '").append(numeroFacturaGlobal).append("' ")
			.append("and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
			elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosActuializados;
	}
	
	public int actualizaFechaTipoCambioXFacturaGlobal(String numeroFacturaGlobal, String folioCartaAdhesion)throws JDBCException {
		int elementosActuializados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("UPDATE  relacion_compras_tmp  set fecha_tipo_cambio  = fecha_fac_global ")
			.append("where numero_fac_global = '").append(numeroFacturaGlobal).append("' ")
			.append("and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
			elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosActuializados;
	}
	
	@SuppressWarnings("unchecked")
	public List<PrecioPagadoProductor> consultaPrecioPagadoProductor(String folioCartaAdhesion)throws JDBCException {
		List<PrecioPagadoProductor> lst = null;
		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select id, folio_carta_adhesion, clave_bodega, folio_contrato, productor, paterno_productor, materno_productor, nombre_productor, folio_factura_venta, vol_sol_fac_venta,")
		.append("fecha_tipo_cambio, imp_sol_fac_venta,precio_pactado_por_tonelada, tipo_cambio, precio_tonelada, precio_pac_x_tipo_cambio, pt_menor_ppxt_cambio, total_pagar_factura_pna_prec_cal,  dif_monto_fac,")
		.append("(select sum(dif_monto_fac)  from  precio_pagado_productor_v p  where pp.folio_contrato = p.folio_contrato and pp.productor = p.productor ) as dif_monto_total ")
		.append(" from  precio_pagado_productor_v pp ")
		.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
//		.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and pt_menor_ppxt_cambio = 'bad'");
		
		lst= session.createSQLQuery(consulta.toString()).addEntity(PrecioPagadoProductor.class).list();
		
		return lst;
						
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> recuperaContratoByCartaAdhesion(String folioCartaAdhesion)throws JDBCException {
		List<String> lst = null;
		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select distinct(folio_contrato)  from relacion_compras_tmp  ")
				.append("where folio_contrato is not null and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");		
		lst= session.createSQLQuery(consulta.toString()).list();
		
		return lst;
	}
		
	public int actualizaFechaTipoCambioXAdendum(String folioContrato, Date fechaTipoCambio, String folioCartaAdhesion)throws JDBCException {
		int elementosActuializados = 0;
		try{
			StringBuilder hql = new StringBuilder()			
			.append("UPDATE  relacion_compras_tmp  set fecha_tipo_cambio  =  '").append(fechaTipoCambio).append("' ")
			.append("where folio_contrato = '").append(folioContrato).append("' and folio_factura_venta is not null ")
			.append("and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
			elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosActuializados;
	}
	
	public List<FacturasIgualesFacAserca> consultaFacIgualesFacAserca(
			String folioCartaAdhesion, int folioFacturaVenta,
			int fechaEmisionFac, int rfc, int volTotalFacVenta,
			int volSolFacVenta, int impSolFacVenta, int idVariedad, int idCultivo, List<GruposCamposRelacionV> lstGruposCamposDetalleRelacionV) throws JDBCException {
		
		
		List<FacturasIgualesFacAserca> lstFacturasIgualesFacAserca = new ArrayList<FacturasIgualesFacAserca>();		
		StringBuilder consultaSelect = new StringBuilder();		
		StringBuilder consultaFrom = new StringBuilder();		
		StringBuilder consultaWhere = new StringBuilder();		
		StringBuilder consultaWhereSubconsulta = new StringBuilder();		
		consultaSelect.append("select  productor, paterno_productor, materno_productor, nombre_productor, rfc_productor, rfc_comprador, ");
		
		for(GruposCamposRelacionV l: lstGruposCamposDetalleRelacionV){
			if(l.getIdCampo()==15){//folioFacturaVenta
				consultaSelect.append("folio_factura_venta,");
			}else if(l.getIdCampo()==16){//Fecha factura
				consultaSelect.append("fecha_emision_fac,");
			}else if(l.getIdCampo()==17){//Rfc factura
				consultaSelect.append("rfc_fac_venta,");
			}else if(l.getIdCampo()==19){//P.N.A. SOLICITADO PARA APOYO (TON.)
				consultaSelect.append("vol_sol_fac_venta,");
			}else if(l.getIdCampo()==65){//P.N.A. TOTAL DE LA FACTURA (TON.)
				consultaSelect.append("vol_total_fac_venta,");
			}else if(l.getIdCampo()==20){//IMPORTE TOTAL FACTURADO ($)
				consultaSelect.append("imp_sol_fac_venta,");
			}else if(l.getIdCampo()==70){//VARIEDAD
				consultaSelect.append("variedad,");
			}else if(l.getIdCampo()==71){//CULTIVO
				consultaSelect.append("cultivo,");
			}
		}
		if (folioFacturaVenta != 0){
			consultaWhereSubconsulta.append(" where folio_factura_venta = r.folio_factura_venta");
		}	
		if(fechaEmisionFac != 0){			
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  fecha_emision_fac = r.fecha_emision_fac ");
			}else{
				consultaWhereSubconsulta.append(" where  fecha_emision_fac = r.fecha_emision_fac ");
			}			
		}
		if(rfc != 0){			
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  rfc_fac_venta = r.rfc_fac_venta ");
			}else{
				consultaWhereSubconsulta.append(" where  rfc_fac_venta = r.rfc_fac_venta ");
			}
		}		
		
		if(volTotalFacVenta != 0){
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  vol_total_fac_venta = r.vol_total_fac_venta ");
			}else{
				consultaWhereSubconsulta.append(" where  vol_total_fac_venta = r.vol_total_fac_venta ");
			}
		}		
		if(volSolFacVenta != 0){			
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  vol_sol_fac_venta = r.vol_sol_fac_venta ");
			}else{
				consultaWhereSubconsulta.append(" where  vol_sol_fac_venta = r.vol_sol_fac_venta ");
			}
		}
		
		if(impSolFacVenta != 0){			
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  imp_sol_fac_venta = r.imp_sol_fac_venta ");
			}else{
				consultaWhereSubconsulta.append(" where  imp_sol_fac_venta = r.imp_sol_fac_venta ");
			}
		}
		if(idVariedad != 0){
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  id_variedad = r.id_variedad ");
			}else{
				consultaWhereSubconsulta.append(" where  id_variedad = r.id_variedad ");
			}
		}
		if(idCultivo != 0){
			
			if(consultaWhereSubconsulta.length()>0){
				consultaWhereSubconsulta.append(" and  id_cultivo = r.id_cultivo ");
			}else{
				consultaWhereSubconsulta.append(" where  id_cultivo = r.id_cultivo ");
			}
		}
		consultaSelect.deleteCharAt(consultaSelect.length()-1);   
		consultaFrom.append(" FROM relacion_compras_tmp r ");
		consultaWhere.append("where  folio_factura_venta is not null and folio_carta_adhesion = '").append(folioCartaAdhesion).append("'")
		.append("and not EXISTS (select 1 from compras_fac_venta_aserca ").append(consultaWhereSubconsulta).append(")");
		  
		
		SQLQuery query = session.createSQLQuery(consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+" order by paterno_productor, materno_productor, nombre_productor");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		
		BigDecimal valor = null;
		for(Object object : data){
           Map<?, ?> row = (Map<?, ?>)object;
           FacturasIgualesFacAserca f = new FacturasIgualesFacAserca();
           BigDecimal productor = (BigDecimal) row.get("productor");
           f.setFolioProductor(productor.longValue());
           f.setPaternoProductor((String) row.get("paterno_productor"));
           f.setMaternoProductor((String) row.get("materno_productor"));
           f.setNombreProductor((String) row.get("nombre_productor"));
           f.setRfcProductor((String) row.get("rfc_productor"));
           f.setRfcComprador((String) row.get("rfc_comprador"));
           if (folioFacturaVenta != 0){
        	   System.out.print("folio_factura_venta " + row.get("folio_factura_venta"));
        	   f.setFolioFacturaVenta((String) row.get("folio_factura_venta"));
           }
           if(fechaEmisionFac != 0){
        	   f.setFechaEmisionFac((Date) row.get("fecha_emision_fac"));
           }
           if(rfc != 0){
        	   f.setRfcFacVenta((String) row.get("rfc_fac_venta"));
           }
           if(volTotalFacVenta != 0){
               valor = (BigDecimal) row.get("vol_total_fac_venta");
        	   f.setVolTotalFacVenta(valor.doubleValue());
           }
   		   if(volSolFacVenta != 0){
   			   valor = (BigDecimal) row.get("vol_sol_fac_venta");
   			   f.setVolSolFacVenta(valor.doubleValue());
   		   }
	   		if(impSolFacVenta != 0){
	   			valor = (BigDecimal) row.get("imp_sol_fac_venta");
	   			f.setImpSolFacVenta(valor.doubleValue());
	   		}
	   		if(idVariedad != 0){
	   			f.setVariedad((String) row.get("variedad"));
	   		}
	   		if(idCultivo != 0){
	   			f.setCultivo((String) row.get("cultivo"));
	   		}
	   		lstFacturasIgualesFacAserca.add(f);
        }//end map
		
		return lstFacturasIgualesFacAserca;
	}
	
	
	
	public List<PrecioPagadoNoCorrespondeConPagosV> consultaPrecioPagadoNoCorrespondeConPagosV(String folioCartaAdhesion)throws JDBCException {
		List<PrecioPagadoNoCorrespondeConPagosV> lst = new ArrayList<PrecioPagadoNoCorrespondeConPagosV>();		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select * ")		
		.append(" from  precio_pagado_no_corresponde_con_pagos_v ")
		.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and abs((COALESCE(imp_precio_ton_pago_sinaxc,0) - COALESCE(precio_calculado,0)))>1.00  ");
		System.out.println("Precio no corresponde con pagos "+consulta.toString());
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			BigDecimal valor = null;
			PrecioPagadoNoCorrespondeConPagosV  r = new PrecioPagadoNoCorrespondeConPagosV();
			r.setFolioCartaAdhesion((String) row.get("folio_carta_adhesion"));
			r.setClaveBodega((String) row.get("clave_bodega"));
			r.setNombreEstado((String) row.get("nombre_estado"));
			r.setFolioContrato((String) row.get("folio_contrato"));
			r.setPaternoProductor((String) row.get("paterno_productor"));
			r.setMaternoProductor((String) row.get("materno_productor"));
			r.setNombreProductor((String) row.get("nombre_productor"));
			r.setCurpProductor((String) row.get("curp_productor"));
			r.setRfcProductor((String) row.get("rfc_productor"));
			valor = (BigDecimal) row.get("vol_total_fac_venta");
			r.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_doc_pago_sinaxc");
			r.setImpDocPagoSinaxc(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_total_pago_sinaxc");
			r.setImpTotalPagoSinaxc(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_precio_ton_pago_sinaxc");
			r.setImpPrecioTonPagiSinaxc(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("precio_calculado");
			r.setPrecioCalculado(valor!=null ? valor.doubleValue():null);			
			lst.add(r);
		}
		return lst;
						
	}
	
	
	public List<PrecioPagadoNoCorrespondeConPagosV> consultaPrecioMenorQAviso(String folioCartaAdhesion)throws JDBCException {
		List<PrecioPagadoNoCorrespondeConPagosV> lst = new ArrayList<PrecioPagadoNoCorrespondeConPagosV>();		
		StringBuilder consulta= new StringBuilder();
		consulta.append("select * ")		
		.append(" from  precio_pagado_no_corresponde_con_pagos_v ")
		.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("and (precio_calculado < precio_pagado_en_aviso ")
		.append("or imp_doc_pago_sinaxc <> imp_sol_fac_venta )");

		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);		
		System.out.println("Precio pagado menor al aviso "+consulta.toString());
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			BigDecimal valor = null;
			PrecioPagadoNoCorrespondeConPagosV  r = new PrecioPagadoNoCorrespondeConPagosV();
			r.setFolioCartaAdhesion((String) row.get("folio_carta_adhesion"));
			r.setClaveBodega((String) row.get("clave_bodega"));
			r.setNombreEstado((String) row.get("nombre_estado"));
			r.setFolioContrato((String) row.get("folio_contrato"));
			r.setPaternoProductor((String) row.get("paterno_productor"));
			r.setMaternoProductor((String) row.get("materno_productor"));
			r.setNombreProductor((String) row.get("nombre_productor"));
			r.setCurpProductor((String) row.get("curp_productor"));
			r.setRfcProductor((String) row.get("rfc_productor"));
			valor = (BigDecimal) row.get("vol_total_fac_venta");
			r.setVolTotalFacVenta(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_sol_fac_venta");
			r.setImpSolFacVenta(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_doc_pago_sinaxc");
			r.setImpDocPagoSinaxc(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_total_pago_sinaxc");
			r.setImpTotalPagoSinaxc(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("imp_precio_ton_pago_sinaxc");
			r.setImpPrecioTonPagiSinaxc(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("precio_calculado");
			r.setPrecioCalculado(valor!=null ? valor.doubleValue():null);
			valor = (BigDecimal) row.get("precio_pagado_en_aviso");
			r.setPrecioPagadoEnAviso(valor!=null ? valor.doubleValue():null);
			
			lst.add(r);
		}
		return lst;
						
	}
	//INSERCION DE INFORMACION A LAS TABLAS CORRESPONDIENTES	
	
	//Consigue datos para llenar la tabla de compras_datos_participante
	public List<RelacionComprasTMP> recuperaParaComprasDatosParticipante (String folioCartaAdhesion)throws JDBCException {
		List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
		StringBuilder consulta = new StringBuilder();	
		consulta.append("select folio_carta_adhesion, clave_bodega, estado_acopio  ")
		.append("from relacion_compras_tmp ")
		.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
		.append("group by folio_carta_adhesion, clave_bodega, estado_acopio ");
		
		SQLQuery query = session.createSQLQuery(consulta.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		for(Object object : data){
			Map<?, ?> row = (Map<?, ?>)object;
			RelacionComprasTMP  r = new RelacionComprasTMP();
			r.setClaveBodega((String) row.get("clave_bodega"));
			r.setEstadoAcopio((Integer) row.get("estado_acopio"));
			
			lst.add(r);
		}
		
		return lst;
	}
		
	//Consigue datos para llenar la tabla de compras_comp_productor
		public List<RelacionComprasTMP> recuperaParaComprasCompProductor(String folioCartaAdhesion, String clavebodega, int idEstado)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select productor, curp_productor, rfc_productor, numero_prod_benef  ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and numero_prod_benef is not null and productor is not null  ")
			.append(" group by productor, curp_productor, rfc_productor, numero_prod_benef ");		
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				BigDecimal productor = (BigDecimal) row.get("productor");
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setFolioProductor(productor.longValue());
				r.setCurpProductor((String) row.get("curp_productor"));
				r.setRfcProductor((String) row.get("rfc_productor"));
				r.setNumeroProdBenef((Integer) row.get("numero_prod_benef"));
				
				lst.add(r);
			}
			
			return lst;
		}
	
		
		//Consigue datos para llenar la tabla de compras_predio
		public List<RelacionComprasTMP> recuperaParaComprasPredio(String folioCartaAdhesion, String clavebodega, int idEstado, int productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select folio_predio, folio_predio_sec, folio_predio_alterno  ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and ")
			.append("productor =").append(productor).append(" and folio_predio is not null or folio_predio_alterno is not null");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setFolioPredio((String) row.get("folio_predio"));
				r.setFolioPredioSec((Integer) row.get("folio_predio_sec"));
				r.setFolioPredioAlterno((String) row.get("folio_predio_alterno"));
				lst.add(r);
			}		
			return lst;
		}
	
		
		//Consigue datos para llenar la tabla de compras_fac_venta
		public List<RelacionComprasTMP> recuperaParaComprasFacVenta(String folioCartaAdhesion, String clavebodega, int idEstado, long productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select folio_factura_venta, fecha_emision_fac, rfc_fac_venta, vol_total_fac_venta, vol_sol_fac_venta, imp_sol_fac_venta  ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and ")
			.append("productor =").append(productor).append(" and folio_factura_venta is not null ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			BigDecimal valor = null;
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setFolioFacturaVenta((String) row.get("folio_factura_venta"));
				r.setFechaEmisionFac((Date)row.get("fecha_emision_fac"));
				r.setRfcFacVenta((String) row.get("rfc_fac_venta"));
				 	valor = (BigDecimal) row.get("vol_total_fac_venta");
				r.setVolTotalFacVenta(valor!=null ?valor.doubleValue():null);
				 	valor = (BigDecimal) row.get("vol_sol_fac_venta");
				r.setVolSolFacVenta(valor!=null ? valor.doubleValue():null);
					valor = (BigDecimal) row.get("imp_sol_fac_venta");
				r.setImpSolFacVenta(valor!=null ? valor.doubleValue(): null);
				lst.add(r);
			}		
			return lst;
		}
		
		public List<RelacionComprasTMP> recuperaParaComprasEntradaBodega(String folioCartaAdhesion, String clavebodega, int idEstado, long productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select boleta_ticket_bascula, fecha_entrada_boleta, vol_bol_ticket  ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and ")
			.append("productor =").append(productor).append(" and boleta_ticket_bascula is not null ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			BigDecimal valor = null;
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setBoletaTicketBascula((String) row.get("boleta_ticket_bascula"));
				r.setFechaEntradaBoleta((Date) row.get("fecha_entrada_boleta"));
				valor = (BigDecimal) row.get("vol_bol_ticket");
				r.setVolBolTicket(valor.doubleValue());
				lst.add(r);
			}		
			return lst;
		}
			

		public List<RelacionComprasTMP> recuperaParaComprasFacVentaGlobal(String folioCartaAdhesion, String clavebodega, int idEstado, long productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select persona_factura_global, numero_fac_global,  fecha_fac_global, rfc_fac_global, sum(vol_pna_fac_global) as vol_pna_fac_global, sum(imp_fac_global) as imp_fac_global ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and numero_fac_global is not null ")
			.append("group by persona_factura_global, numero_fac_global,  fecha_fac_global, rfc_fac_global");			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			BigDecimal valor = null;
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setPersonaFacturaGlobal((String) row.get("persona_factura_global"));
				r.setNumeroFacGlobal((String) row.get("numero_fac_global"));
				r.setFechaFacGlobal((Date) row.get("fecha_fac_global"));
				r.setRfcFacGlobal((String) row.get("rfc_fac_global"));
				valor = (BigDecimal) row.get("vol_pna_fac_global");
				r.setVolPnaFacGlobal(valor!=null ? valor.doubleValue(): null);
				valor = (BigDecimal) row.get("imp_fac_global");
				r.setImpFacGlobal(valor!=null ? valor.doubleValue(): null);				
				lst.add(r);
			}		
			return lst;
		}
			

		public List<RelacionComprasTMP> recuperaParaComprasPagoProdSinAXC(String folioCartaAdhesion, String clavebodega, int idEstado, long productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select id_tipo_doc_pago, folio_doc_pago, imp_doc_pago_sinaxc, fecha_doc_pago_sinaxc, banco_id, imp_total_pago_sinaxc, imp_precio_ton_pago_sinaxc  ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("'  and ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and ")
			.append("productor =").append(productor).append(" and folio_doc_pago is not null ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			BigDecimal valor = null;
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				//Integer idTipoDocPago  = (Integer) row.get("id_tipo_doc_pago");
				//r.setIdTipoDocPago(idTipoDocPago.longValue());
				r.setFolioDocPago((String) row.get("folio_doc_pago"));
				r.setFechaFacGlobal((Date) row.get("fecha_doc_pago_sinaxc"));
				r.setBancoId((Integer) row.get("banco_id"));
				valor = (BigDecimal) row.get("imp_total_pago_sinaxc");
				r.setImpPrecioTonPagiSinaxc(valor!=null ?valor.doubleValue():null);
				valor = (BigDecimal) row.get("imp_precio_ton_pago_sinaxc");
				r.setImpPrecioTonPagiSinaxc(valor!=null ? valor.doubleValue(): null);				
				lst.add(r);
			}		
			return lst;
		}
			
		
		public List<RelacionComprasTMP> recuperaParaComprasContrato(String folioCartaAdhesion, String clavebodega, int idEstado, long productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select productor, folio_contrato, imp_pactado_compra_venta ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and ")
			.append("productor =").append(productor).append(" and folio_contrato  is not null ")
			.append("group by productor, folio_contrato, imp_pactado_compra_venta ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			BigDecimal valor = null;
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setFolioContrato((String) row.get("folio_contrato"));				
				valor = (BigDecimal) row.get("imp_pactado_compra_venta");
				r.setImpPrecioTonPagiSinaxc(valor!= null ?valor.doubleValue():null);	
				
				lst.add(r);
			}		
			return lst;
		}
		
		public List<RelacionComprasTMP> recuperaParaComprasPredio(String folioCartaAdhesion, String clavebodega, int idEstado, long productor)throws JDBCException {
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta = new StringBuilder();	
			consulta.append("select productor, folio_predio, folio_predio_sec, folio_predio_alterno ")
			.append("from relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ")
			.append("clave_bodega = '").append(clavebodega).append("' and estado_acopio = ").append(idEstado).append(" and ")
			.append("productor =").append(productor).append(" and (folio_predio is not null or folio_predio_alterno is not null) ")
			.append("group by productor, folio_predio, folio_predio_sec, folio_predio_alterno ");		
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP  r = new RelacionComprasTMP();
				r.setFolioPredio((String) row.get("folio_predio"));
				r.setFolioPredioSec((Integer) row.get("folio_predio_sec"));
				r.setFolioPredioAlterno((String) row.get("folio_predio_alterno"));	
				lst.add(r);
			}		
			return lst;
		}
				
		@SuppressWarnings("unchecked")
		public List<ProductoresIncosistentes> consultaProductoresIncosistentes(String folioCartaAdhesion)throws JDBCException {
			List<ProductoresIncosistentes> lst = null;			
			StringBuilder consulta= new StringBuilder();
			consulta.append("SELECT row_number() OVER () AS id,  clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, ")
			.append("count(folio_predio) as folio_predio, count(folio_predio_alterno) as predio_alterno,")
//			.append("count(folio_predio) as folio_predio, ")
			.append("count(boleta_ticket_bascula) as numero_boletas, count (folio_factura_venta) as numero_facturas, count(folio_doc_pago) as numero_pagos")
			.append(" from  relacion_compras_tmp ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("GROUP BY clave_bodega,  nombre_estado, folio_contrato,  paterno_productor, materno_productor, nombre_productor,  curp_productor, rfc_productor ")
//			.append("HAVING (COUNT(boleta_ticket_bascula) = 0 or COUNT(folio_factura_venta) = 0 or COUNT(folio_doc_pago)= 0 or (count(folio_predio) = 0 and count(folio_predio_alterno) = 0))");
			.append("HAVING (COUNT(boleta_ticket_bascula) = 0 or COUNT(folio_factura_venta) = 0 or COUNT(folio_doc_pago)= 0 or (count(folio_predio) = 0))");
				
			System.out.println("Falta de informacion:"+consulta.toString());
			
			lst= session.createSQLQuery(consulta.toString()).addEntity(ProductoresIncosistentes.class).list();
			
			return lst;
							
		}

		
		public  List<BoletasCamposRequeridos> consultaBoletasInfoRequerida (String folioCartaAdhesion,  String camposQueAplica) throws JDBCException {
			StringBuilder consultaSelect = new StringBuilder();
			StringBuilder consultaWhere = new StringBuilder();
			String consultaWhereS ="";
			StringBuilder consultaFrom = new StringBuilder();
			StringBuilder consultaGroupBy = new StringBuilder();
			
			List<BoletasCamposRequeridos> lstBoletasCamposRequeridos  = new ArrayList<BoletasCamposRequeridos>();
			consultaSelect.append("select clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, boleta_ticket_bascula,");
			consultaGroupBy.append(" group by clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor,  curp_productor, rfc_productor, boleta_ticket_bascula, ");
			consultaWhereS ="(";
			
			if(camposQueAplica.contains("12,")){
					consultaSelect.append("fecha_entrada_boleta,");
					consultaGroupBy.append("fecha_entrada_boleta,");
					consultaWhereS += " fecha_entrada_boleta is null or ";
			}
			if(camposQueAplica.contains("63,")){
					consultaSelect.append("vol_bol_ticket,");
					consultaGroupBy.append("vol_bol_ticket,");
					consultaWhereS +=" vol_bol_ticket is null or ";	
			}
			
			
			consultaSelect.deleteCharAt(consultaSelect.length()-1);
			consultaGroupBy.deleteCharAt(consultaGroupBy.length()-1);
			consultaWhereS = consultaWhereS.substring(0, consultaWhereS.length()-3);
			consultaWhereS += ")";			
			
			consultaFrom.append(" from relacion_compras_tmp ");
			consultaWhere.append(" where  boleta_ticket_bascula is not null and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ");
			
			System.out.println("Boletas con nulos: "+consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+consultaWhereS.toString()+consultaGroupBy.toString()+" order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");
			SQLQuery query = session.createSQLQuery(consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+consultaWhereS.toString()+consultaGroupBy.toString()+" order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				BoletasCamposRequeridos b = new BoletasCamposRequeridos();
				b.setClaveBodega((String) row.get("clave_bodega"));
				b.setNombreEstado((String) row.get("nombre_estado"));
				b.setFolioContrato((String) row.get("folio_contrato"));
				b.setPaternoProductor((String) row.get("paterno_productor"));
		        b.setMaternoProductor((String) row.get("materno_productor"));
		        b.setNombreProductor((String) row.get("nombre_productor"));
		        b.setCurpProductor((String) row.get("curp_productor"));
		        b.setRfcProductor((String) row.get("curp_productor"));
		        b.setBoletaTicketBascula((String) row.get("boleta_ticket_bascula"));
		        if(camposQueAplica.contains("12,")){
		        	if((Date) row.get("fecha_entrada_boleta")==null){
		        		b.setFechaEntrada("REQUERIDO");
		        	}else{
		        		b.setFechaEntrada(null);
		        	}
		        }		        
		        if(camposQueAplica.contains("63,")){
		        	if((BigDecimal) row.get("vol_bol_ticket")==null){
		        		b.setVolumen("REQUERIDO");
		        	}else{
		        		b.setVolumen(null);
		        	}
		        }
		        
		        if((BigDecimal) row.get("vol_bol_ticket")!=null){
		        	BigDecimal valor = (BigDecimal) row.get("vol_bol_ticket");
		        	b.setVolBolTicket(valor.doubleValue());
		        }
		        lstBoletasCamposRequeridos.add(b);
			}
			
			return lstBoletasCamposRequeridos;
			
		}

		public  List<FacturasCamposRequeridos> consultaFacturasInfoRequerida (String folioCartaAdhesion,  String camposQueAplica) throws JDBCException {
			StringBuilder consultaSelect = new StringBuilder();
			StringBuilder consultaWhere = new StringBuilder();
			String consultaWhereS ="";
			StringBuilder consultaFrom = new StringBuilder();
			StringBuilder consultaGroupBy = new StringBuilder();
			
			List<FacturasCamposRequeridos> lstFacturasCamposRequeridos  = new ArrayList<FacturasCamposRequeridos>();
			consultaSelect.append("select clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_factura_venta, vol_total_fac_venta,");
			consultaGroupBy.append(" group by clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_factura_venta, vol_total_fac_venta,");
			consultaWhereS ="(";
			
			if(camposQueAplica.contains("16,")){
					consultaSelect.append("fecha_emision_fac,");
					consultaGroupBy.append("fecha_emision_fac,");
					consultaWhereS += " fecha_emision_fac is null or ";
			}
			if(camposQueAplica.contains("17,")){
					consultaSelect.append("rfc_fac_venta,");
					consultaGroupBy.append("rfc_fac_venta,");
					consultaWhereS +=" rfc_fac_venta is null or ";	
			}
			if(camposQueAplica.contains("19,")){
				consultaSelect.append("vol_sol_fac_venta,");
				consultaGroupBy.append("vol_sol_fac_venta,");
				consultaWhereS +=" vol_sol_fac_venta is null or ";	
			}
			if(camposQueAplica.contains("65,")){
				consultaSelect.append("vol_total_fac_venta,");
				consultaGroupBy.append("vol_total_fac_venta,");
				consultaWhereS +=" vol_total_fac_venta is null or ";	
			}
			if(camposQueAplica.contains("20,")){
				consultaSelect.append("imp_sol_fac_venta,");
				consultaGroupBy.append("imp_sol_fac_venta,");
				consultaWhereS +=" imp_sol_fac_venta is null or ";	
			}
			if(camposQueAplica.contains("70,")){
				consultaSelect.append("id_variedad,");
				consultaGroupBy.append("id_variedad,");
				consultaWhereS +=" id_variedad is null or ";	
			}
			
					
			consultaSelect.deleteCharAt(consultaSelect.length()-1);
			consultaGroupBy.deleteCharAt(consultaGroupBy.length()-1);
			System.out.println("Antes "+consultaWhereS);
			consultaWhereS = consultaWhereS.substring(0, consultaWhereS.length()-3);
			consultaWhereS += ")";			
			
			consultaFrom.append(" from relacion_compras_tmp ");
			consultaWhere.append(" where  folio_factura_venta is not null and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ");			
			
			System.out.println("Query facturas nulas: "+consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+consultaWhereS.toString()+consultaGroupBy.toString()+" order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");
			SQLQuery query = session.createSQLQuery(consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+consultaWhereS.toString()+consultaGroupBy.toString()+" order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				FacturasCamposRequeridos b = new FacturasCamposRequeridos();
				b.setClaveBodega((String) row.get("clave_bodega"));
				b.setNombreEstado((String) row.get("nombre_estado"));
				b.setFolioContrato((String) row.get("folio_contrato"));
				b.setPaternoProductor((String) row.get("paterno_productor"));
		        b.setMaternoProductor((String) row.get("materno_productor"));
		        b.setNombreProductor((String) row.get("nombre_productor"));
		        b.setCurpProductor((String) row.get("curp_productor"));
		        b.setRfcProductor((String) row.get("rfc_productor"));
		        b.setFolioFacturaVenta((String) row.get("folio_factura_venta"));
		        if(camposQueAplica.contains("16,")){
		        	if((Date) row.get("fecha_emision_fac")==null){
		        		b.setFechaEmisionFac("REQUERIDO");
		        	}else{
		        		b.setFechaEmisionFac(null);
		        	}
		        }		        
		        if(camposQueAplica.contains("17,")){
		        	if((String) row.get("rfc_fac_venta")==null){
		        		b.setRfcFacVenta("REQUERIDO");
		        	}else{
		        		b.setRfcFacVenta(null);
		        	}
		        }
		        if(camposQueAplica.contains("19,")){
		        	if((BigDecimal) row.get("vol_sol_fac_venta")==null){
		        		b.setVolSolFacVenta("REQUERIDO");
		        	}else{
		        		b.setVolSolFacVenta(null);
		        	}
		        }
		        if(camposQueAplica.contains("65,")){
		        	if((BigDecimal) row.get("vol_total_fac_venta")==null){
		        		b.setVolTotalFacVenta("REQUERIDO");
		        	}else{
		        		b.setVolTotalFacVenta(null);
		        	}
		        }
		        if(camposQueAplica.contains("20,")){
		        	if((BigDecimal) row.get("imp_sol_fac_venta")==null){
		        		b.setImpSolFacVenta("REQUERIDO");
		        	}else{
		        		b.setImpSolFacVenta(null);
		        	}
		        }
		        if(camposQueAplica.contains("70,")){
		        	if((Integer) row.get("id_variedad")==null){
		        		b.setVariedad("REQUERIDO");
		        	}else{
		        		b.setVariedad(null);
		        	}
		        }
		        if((BigDecimal) row.get("vol_total_fac_venta")!=null){
		        	BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
		        	b.setVolTotalFacVentaD(valor.doubleValue());
		        }
		       
		       
		        lstFacturasCamposRequeridos.add(b);
			}
			
			return lstFacturasCamposRequeridos;
			
		}
		public  List<PagosCamposRequeridos> consultaPagosInfoRequerida (String folioCartaAdhesion,  String camposQueAplica) throws JDBCException {
			StringBuilder consultaSelect = new StringBuilder();
			StringBuilder consultaWhere = new StringBuilder();
			String consultaWhereS ="";
			StringBuilder consultaFrom = new StringBuilder();
			StringBuilder consultaGroupBy = new StringBuilder();
			
			List<PagosCamposRequeridos> lstPagosCamposRequeridos  = new ArrayList<PagosCamposRequeridos>();
			consultaSelect.append("select clave_bodega, nombre_estado, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_doc_pago, imp_total_pago_sinaxc, " +	// AHS [LINEA] - 10022015
					"(select COALESCE(sum(vol_total_fac_venta),0) " +	// AHS [LINEA] - 10022015
					"from relacion_compras_tmp r1 "+ 	// AHS [LINEA] - 10022015
					"where r.clave_bodega = r1.clave_bodega "+ 	// AHS [LINEA] - 10022015
					"and  r.nombre_estado = r1.nombre_estado "+	// AHS [LINEA] - 10022015
					"and  r.estado_acopio = r1.estado_acopio "+	// AHS [LINEA] - 10022015
					"and  COALESCE(r.folio_contrato,'X') = COALESCE(r1.folio_contrato,'X') " +
					"and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r1.curp_productor,r1.rfc_productor)) as vol_total_fac_venta,");
//					"and  COALESCE(r.paterno_productor,'X') =  COALESCE(r1.paterno_productor,'X') "+	// AHS [LINEA] - 10022015
//					"and  COALESCE(r.materno_productor,'X') =  COALESCE(r1.materno_productor,'X') "+	// AHS [LINEA] - 10022015
//					"and  COALESCE(r.nombre_productor,'X') =  COALESCE(r1.nombre_productor,'X')  ) as vol_total_fac_venta,");	// AHS [LINEA] - 10022015
			consultaGroupBy.append(" group by clave_bodega, nombre_estado, estado_acopio, folio_contrato, paterno_productor, materno_productor, nombre_productor, curp_productor, rfc_productor, folio_doc_pago, imp_total_pago_sinaxc,");	// AHS [LINEA] - 10022015
			consultaWhereS ="(";
			
			if(camposQueAplica.contains("67,")){
					consultaSelect.append("imp_total_pago_sinaxc,");
					consultaGroupBy.append("imp_total_pago_sinaxc,");
					consultaWhereS += " imp_total_pago_sinaxc is null or ";
			}
			if(camposQueAplica.contains("27,")){
					consultaSelect.append("tipo_doc_pago,");
					consultaGroupBy.append("tipo_doc_pago,");
					consultaWhereS +=" tipo_doc_pago is null or ";	
			}
			if(camposQueAplica.contains("16,")){
				consultaSelect.append("fecha_doc_pago_sinaxc,");
				consultaGroupBy.append("fecha_doc_pago_sinaxc,");
				consultaWhereS +=" fecha_doc_pago_sinaxc is null or ";	
			}
		
			if(camposQueAplica.contains("29,")){
				consultaSelect.append("banco_sinaxc,");
				consultaGroupBy.append("banco_sinaxc,");
				consultaWhereS +=" banco_sinaxc is null or ";	
			}
		
			if(camposQueAplica.contains("66,")){
				consultaSelect.append("imp_doc_pago_sinaxc,");
				consultaGroupBy.append("imp_doc_pago_sinaxc,");
				consultaWhereS +=" imp_doc_pago_sinaxc is null or ";	
			}
			if(camposQueAplica.contains("68,")){
				consultaSelect.append("imp_precio_ton_pago_sinaxc,");
				consultaGroupBy.append("imp_precio_ton_pago_sinaxc,");
				consultaWhereS +=" imp_precio_ton_pago_sinaxc is null or ";	
			}
		
			
			consultaSelect.deleteCharAt(consultaSelect.length()-1);
			consultaGroupBy.deleteCharAt(consultaGroupBy.length()-1);
			consultaWhereS = consultaWhereS.substring(0, consultaWhereS.length()-3);
			consultaWhereS += ")";		
			consultaFrom.append(" from relacion_compras_tmp r "); // AHS [LINEA] - 10022015
			consultaWhere.append(" where  folio_doc_pago is not null and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and ");
			System.out.println("Pagos con valores nulos:"+consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+consultaWhereS.toString()+consultaGroupBy.toString()+" order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");
			
			SQLQuery query = session.createSQLQuery(consultaSelect.toString()+consultaFrom.toString()+consultaWhere.toString()+consultaWhereS.toString()+consultaGroupBy.toString()+" order by clave_bodega, nombre_estado, paterno_productor, materno_productor, nombre_productor");
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				PagosCamposRequeridos b = new PagosCamposRequeridos();
				b.setClaveBodega((String) row.get("clave_bodega"));
				b.setNombreEstado((String) row.get("nombre_estado"));
				b.setFolioContrato((String) row.get("folio_contrato"));
				b.setPaternoProductor((String) row.get("paterno_productor"));
		        b.setMaternoProductor((String) row.get("materno_productor"));
		        b.setNombreProductor((String) row.get("nombre_productor"));
		        b.setCurpProductor((String) row.get("curp_productor"));
		        b.setRfcProductor((String) row.get("rfc_productor"));
		        b.setFolioDocPago((String) row.get("folio_doc_pago"));
		        BigDecimal valorVolTotalFacVenta = (BigDecimal) row.get("vol_total_fac_venta");	// AHS [LINEA] - 10022015
	        	b.setVolTotalFacVentaD(valorVolTotalFacVenta.doubleValue());	// AHS [LINEA] - 10022015
		        
		        if(camposQueAplica.contains("67,")){
		        	if((BigDecimal) row.get("imp_total_pago_sinaxc")==null){
		        		b.setImpTotalPagoSinaxc("REQUERIDO");
		        	}else{
		        		b.setImpTotalPagoSinaxc(null);
		        	}
		        }
		        if(camposQueAplica.contains("27,")){
		        	if((String) row.get("tipo_doc_pago")==null){
		        		b.setTipoDdocPago("REQUERIDO");
		        	}else{
		        		b.setTipoDdocPago(null);
		        	}
		        }	
		        if(camposQueAplica.contains("16,")){
		        	if((Date) row.get("fecha_doc_pago_sinaxc")==null){
		        		b.setFechaDocPagoSinaxc("REQUERIDO");
		        	}else{
		        		b.setFechaDocPagoSinaxc(null);
		        	}
		        }
		        
		        if(camposQueAplica.contains("29,")){
		        	if((String) row.get("banco_sinaxc")==null){
		        		b.setBancoSinaxc("REQUERIDO");
		        	}else{
		        		b.setBancoSinaxc(null);
		        	}
		        }	
		        if(camposQueAplica.contains("66,")){
		        	if((BigDecimal) row.get("imp_doc_pago_sinaxc")==null){
		        		b.setImpDocPagoSinaxc("REQUERIDO");
		        	}else{
		        		b.setImpDocPagoSinaxc(null);
		        	}
		        }
		        if(camposQueAplica.contains("68,")){
		        	if((BigDecimal) row.get("imp_precio_ton_pago_sinaxc")==null){
		        		b.setImpPrecioTonPagoSinaxc("REQUERIDO");
		        	}else{ 
		        		b.setImpPrecioTonPagoSinaxc(null);
		        	}
		        }
		        
		        if((BigDecimal) row.get("imp_total_pago_sinaxc")==null){
	        		b.setImpTotalPagoSinaxc("REQUERIDO");
	        		
	        	}else{
	        		b.setImpTotalPagoSinaxc(null);
	        	}
		        
		        if((BigDecimal) row.get("imp_total_pago_sinaxc")!=null){
		        	BigDecimal valor = (BigDecimal) row.get("imp_total_pago_sinaxc");
		        	b.setImpTotalPagoSinaxcD(valor.doubleValue());
		        }
		        
		        
		        lstPagosCamposRequeridos.add(b);
			}
			
			return lstPagosCamposRequeridos;
			
		}
		
		public int	borrarRelacionComprasByFolioCarta(String folioCartaAdhesion)throws JDBCException {
			int elementosBorrados = 0;
			StringBuilder hql = new StringBuilder()	
			.append("DELETE FROM  relacion_compras_tmp WHERE folio_carta_adhesion= '").append(folioCartaAdhesion).append("'; ");
			//.append("DELETE FROM  bitacora_relcompras WHERE folio_carta_adhesion= '").append(folioCartaAdhesion).append("'; ");
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			return elementosBorrados;
		}

		
		@SuppressWarnings("unchecked")
		public List<ContratosRelacionCompras> consultaPeriodosContratosRelCompras(String folioCartaAdhesion)throws JDBCException {			
			List<ContratosRelacionCompras> lst = null;			
			String consulta = "select folio_contrato, periodo_inicial_pago, periodo_final_pago, periodo_inicial_pago_adendum, periodo_final_pago_adendum "+
					          "from contratos_relacion_compras " +
					          "where folio_contrato in ("+
					          							"select distinct(folio_contrato) "+
					          							"from relacion_compras_tmp "+
					          							"where folio_contrato is not null and folio_carta_adhesion = '"+folioCartaAdhesion+"') "
					          +"order by folio_contrato";
			
			lst= session.createSQLQuery(consulta.toString()).addEntity(ContratosRelacionCompras.class).list();
			
			return lst;
		}
		
		@SuppressWarnings("unchecked")
		public List<ContratosRelacionCompras> consultaContratosRelCompras(String folioContrato)throws JDBCException {			
			List<ContratosRelacionCompras> lst = null;
			StringBuilder consulta= new StringBuilder();
			if (folioContrato != null && !folioContrato.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and folioContrato='").append(folioContrato).append("'");
				}else{
					consulta.append("where folioContrato='").append(folioContrato).append("'");
				}
			}
			consulta.insert(0, "From ContratosRelacionCompras ");
			lst= session.createQuery(consulta.toString()).list();
			return lst;
		}
		
		
		public List<RelacionComprasTMP> consultaSoloContratosRelacionCompras(String folioCartaAdhesion)throws  JDBCException{
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta= new StringBuilder();			
			consulta.append(" select distinct(folio_contrato) ")
			.append("from relacion_compras_tmp ")
			.append("where folio_contrato is not null and  folio_carta_adhesion = '").append(folioCartaAdhesion).append("'");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
		        RelacionComprasTMP rl = new RelacionComprasTMP();
		        rl.setFolioContrato((String) row.get("folio_contrato"));
		        lst.add(rl);
			}			
			
			return lst;
		}
		
		
		
		public int actualizaFechaAdendumContratoEnRelCompras(String folioContrato, String folioCartaAdhesion, Date fechaInicio, Date fechaFin )throws JDBCException {
			int elementosActualizados = 0;
			try{
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  set periodo_inicial_contrato  = '").append(fechaInicio).append("',")
				.append(" periodo_final_contrato = '").append(fechaFin).append("'")
				.append("where folio_contrato = '").append(folioContrato).append("' ")
				.append("and folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
				elementosActualizados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActualizados;
		}
		
		
		public List<GeneralToneladasTotalesPorBodFac> consultaGeneralToneladasTotalesPorBodFac(String folioCartaAdhesion)throws  JDBCException{
			List<GeneralToneladasTotalesPorBodFac> lst = new ArrayList<GeneralToneladasTotalesPorBodFac>();
			StringBuilder consulta= new StringBuilder();			
			consulta.append(" select folio_contrato, r.clave_bodega, b.nombre_bodega, nombre_estado, "
					+ "sum(vol_bol_ticket)as peso_neto_ana_bol, sum(vol_total_fac_venta) as peso_neto_ana_fac, "
					+ "count(distinct boleta_ticket_bascula)as total_boletas, count(distinct folio_factura_venta) as total_facturas,"
					+ "count(*) as total_registros, sum(numero_prod_benef) as numero_prod_benef ")
			.append("from relacion_compras_tmp r ")
			.append("LEFT JOIN bodegas b ON b.clave_bodega = r.clave_bodega ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("group by folio_contrato, r.clave_bodega, b.nombre_bodega, nombre_estado ")
			.append("order by folio_contrato, r.clave_bodega, nombre_estado ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			BigInteger valorInt = null;
			System.out.println("Toneladas "+consulta.toString());
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				GeneralToneladasTotalesPorBodFac rl = new GeneralToneladasTotalesPorBodFac();
		        rl.setFolioContrato((String) row.get("folio_contrato"));
		        rl.setClaveBodega((String) row.get("clave_bodega"));
		        rl.setNombreBodega((String) row.get("nombre_bodega"));
		        rl.setNombreEstado((String) row.get("nombre_estado"));
		        BigDecimal valor = (BigDecimal) row.get("peso_neto_ana_bol");
		        rl.setPesoNetoAnaBol(valor!=null ? valor.doubleValue():null);
		        valor = (BigDecimal) row.get("peso_neto_ana_fac");
		        rl.setPesoNetoAnaFac(valor!=null ? valor.doubleValue():null);
		        valorInt = (BigInteger) row.get("total_boletas");
		        rl.setTotalBoletas(valorInt != null ? valorInt.intValue():null);
		        valorInt = (BigInteger) row.get("total_facturas");
		        rl.setTotalFacturas(valorInt != null ? valorInt.intValue():null);
		        valorInt = (BigInteger) row.get("total_registros");
		        rl.setTotalRegistros(valorInt != null ? valorInt.intValue():null);
		        valorInt = (BigInteger) row.get("numero_prod_benef");
		        rl.setNumeroProdBenef(valorInt != null ?valorInt.intValue():null);
		        lst.add(rl);
			}			
			
			return lst;
		}
		
		public List<BoletasFacturasPagosIncosistentes> consultaBoletasFacturasPagosIncosistentes(String folioCartaAdhesion, int idPrograma)throws  JDBCException{
			List<BoletasFacturasPagosIncosistentes> lst = new ArrayList<BoletasFacturasPagosIncosistentes>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("SELECT row_number() OVER (),  r.clave_bodega,  r.nombre_estado, r.estado_acopio, r.folio_contrato, r.rfc_productor, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, ") 
			.append("(select COALESCE(sum(vol_total_fac_venta),0) ")
			.append("from relacion_compras_tmp r1 ")
			.append("where r.clave_bodega = r1.clave_bodega ") 
			.append("and  r.nombre_estado = r1.nombre_estado ")
			.append("and  r.estado_acopio = r1.estado_acopio ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r1.folio_contrato,'X') ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r1.curp_productor,r1.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r1.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r1.materno_productor,'X') ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r1.nombre_productor,'X')  and r.folio_carta_adhesion = r1.folio_carta_adhesion ) as vol_total_fac_venta, ")
			.append("and r.folio_carta_adhesion = r1.folio_carta_adhesion ) as vol_total_fac_venta, ")
			.append("(select COALESCE(sum(r2.vol_bol_ticket),0) ")
			.append("from relacion_compras_tmp r2 ")
			.append("where r.clave_bodega = r2.clave_bodega ") 
			.append("and  r.nombre_estado = r2.nombre_estado ")
			.append("and  r.estado_acopio = r2.estado_acopio ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r2.folio_contrato,'X') ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r2.curp_productor,r2.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r2.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r2.materno_productor,'X') ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r2.nombre_productor,'X') and r.folio_carta_adhesion = r2.folio_carta_adhesion ")
			.append("and r.folio_carta_adhesion = r2.folio_carta_adhesion ")
			.append("and  r2.boleta_incosistente= true ")
			.append(") as volumen_boletas_inc, ")
			.append("(select COALESCE(sum(r3.vol_total_fac_venta),0) ")
			.append("from relacion_compras_tmp r3 ")
			.append("where r.clave_bodega = r3.clave_bodega ") 
			.append("and  r.nombre_estado = r3.nombre_estado  ")
			.append("and  r.estado_acopio = r3.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r3.folio_contrato,'X')  ") 
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r3.curp_productor,r3.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r3.paterno_productor,'X')  ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r3.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r3.nombre_productor,'X') and r.folio_carta_adhesion = r3.folio_carta_adhesion ")
			.append("and r.folio_carta_adhesion = r3.folio_carta_adhesion ")
			.append("and  r3.factura_inconsistente= true ")
			.append(") as volumen_facturas_inc, ")
			.append("(select COALESCE(sum(r4.vol_total_fac_venta),0) ")
			.append("from relacion_compras_tmp r4 ")
			.append("where r.clave_bodega = r4.clave_bodega ") 
			.append("and  r.nombre_estado = r4.nombre_estado ")
			.append("and  r.estado_acopio = r4.estado_acopio ") 
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r4.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r4.curp_productor,r4.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r4.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r4.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r4.nombre_productor,'X')   and r.folio_carta_adhesion = r4.folio_carta_adhesion ")
			.append("and r.folio_carta_adhesion = r4.folio_carta_adhesion ")
			.append("and  EXISTS ( ")
			.append("select 1 ")
			.append("from relacion_compras_tmp r41 ")
			.append("where r.clave_bodega = r41.clave_bodega ") 
			.append("and  r.nombre_estado = r41.nombre_estado ")
			.append("and  r.estado_acopio = r41.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r41.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r41.curp_productor,r41.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r41.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r41.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r41.nombre_productor,'X')   ")
			.append("and  r41.pago_inconsistente= true and r.folio_carta_adhesion = r41.folio_carta_adhesion ")
			.append(") ")
			.append(") as  vol_en_pagos, ")
			.append("(select COALESCE(max(r5.dif_volumen_fac_mayor),0)  ")
			.append("from relacion_compras_tmp r5  ")
			.append("where r.clave_bodega = r5.clave_bodega ") 
			.append("and  r.nombre_estado = r5.nombre_estado ")
			.append("and  r.estado_acopio = r5.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r5.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r5.curp_productor,r5.rfc_productor)  ")
//			.APPEND("AND  COALESCE(R.PATERNO_PRODUCTOR,'X') =  COALESCE(R5.PATERNO_PRODUCTOR,'X') ")
//			.APPEND("AND  COALESCE(R.MATERNO_PRODUCTOR,'X') =  COALESCE(R5.MATERNO_PRODUCTOR,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r5.nombre_productor,'X') and  r.folio_carta_adhesion = r5.folio_carta_adhesion  ")
			.append("and  r.folio_carta_adhesion = r5.folio_carta_adhesion  ")
			.append("and  EXISTS ( ") 
			.append("select 1 ")
			.append("from relacion_compras_tmp r51 ")
			.append("where r.clave_bodega = r51.clave_bodega ") 
			.append("and  r.nombre_estado = r51.nombre_estado ")
			.append("and  r.estado_acopio = r51.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r51.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r51.curp_productor,r51.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r51.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r51.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r51.nombre_productor,'X') and r.folio_carta_adhesion = r51.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r51.folio_carta_adhesion  ")
			.append("and  r51.facturas_mayores_boletas = true ")
			.append(") ")
			.append(") as  facturas_mayores_boletas, ")
			.append("(select COALESCE(sum(r6.vol_total_fac_venta),0) ")
			.append("from relacion_compras_tmp r6 ")
			.append("where r.clave_bodega = r6.clave_bodega ") 
			.append("and  r.nombre_estado = r6.nombre_estado ")
			.append("and  r.estado_acopio = r6.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r6.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r6.curp_productor,r6.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r6.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r6.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r6.nombre_productor,'X')  and r.folio_carta_adhesion = r6.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r6.folio_carta_adhesion  ")
			.append("and  EXISTS ( ")
			.append("select 1 ")
			.append("from relacion_compras_tmp r61 ")
			.append("where r.clave_bodega = r61.clave_bodega ") 
			.append("and  r.nombre_estado = r61.nombre_estado ")
			.append("and  r.estado_acopio = r61.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r61.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r61.curp_productor,r61.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r61.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r61.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r61.nombre_productor,'X')   ")
			.append("and  r61.pagos_menores_facturas = true and r.folio_carta_adhesion = r61.folio_carta_adhesion")
			.append(") ")
			.append(") as  pagos_menores_facturas, ")
			
			.append("(select COALESCE(sum(r6.vol_total_fac_venta),0) ")
			.append("from relacion_compras_tmp r6 ")
			.append("where r.clave_bodega = r6.clave_bodega ") 
			.append("and  r.nombre_estado = r6.nombre_estado ")
			.append("and  r.estado_acopio = r6.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r6.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r6.curp_productor,r6.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r6.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r6.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r6.nombre_productor,'X') and r.folio_carta_adhesion = r6.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r6.folio_carta_adhesion  ")
			.append("and  EXISTS ( ")
			.append("select 1 ")
			.append("from relacion_compras_tmp r61 ")
			.append("where r.clave_bodega = r61.clave_bodega ") 
			.append("and  r.nombre_estado = r61.nombre_estado ")
			.append("and  r.estado_acopio = r61.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r61.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r61.curp_productor,r61.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r61.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r61.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r61.nombre_productor,'X')  and r.folio_carta_adhesion = r61.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r61.folio_carta_adhesion  ")
			.append("and  r61.rfc_inconsistente = true ")
			.append(") ")
			.append(") as  diferencia_entre_rfc, ")
			.append("(select COALESCE(sum(r6.vol_total_fac_venta),0) ")
			.append("from relacion_compras_tmp r6 ")
			.append("where r.clave_bodega = r6.clave_bodega ") 
			.append("and  r.nombre_estado = r6.nombre_estado ")
			.append("and  r.estado_acopio = r6.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r6.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r6.curp_productor,r6.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r6.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r6.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r6.nombre_productor,'X') and r.folio_carta_adhesion = r6.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r6.folio_carta_adhesion  ")
			.append("and  EXISTS ( ")
			.append("select 1 ")
			.append("from relacion_compras_tmp r61 ")
			.append("where r.clave_bodega = r61.clave_bodega ") 
			.append("and  r.nombre_estado = r61.nombre_estado ")
			.append("and  r.estado_acopio = r61.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r61.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r61.curp_productor,r61.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r61.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r61.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r61.nombre_productor,'X')  and r.folio_carta_adhesion = r61.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r61.folio_carta_adhesion  ")
			.append("and  r61.predio_no_pagado = true ")
			.append(") ")
			.append(") as  predio_no_pagado, ")
			
			.append("(select  COALESCE(max(r7.volumen_no_procedente),0) ")
			.append("from relacion_compras_tmp r7 ")
			.append("where r.clave_bodega = r7.clave_bodega ") 
			.append("and  r.nombre_estado = r7.nombre_estado ")
			.append("and  r.estado_acopio = r7.estado_acopio  ")
			.append("and  COALESCE(r.folio_contrato,'X') = COALESCE(r7.folio_contrato,'X')  ")
			.append("and COALESCE(r.curp_productor, r.rfc_productor) = COALESCE(r7.curp_productor,r7.rfc_productor)  ")
//			.append("and  COALESCE(r.paterno_productor,'X') =  COALESCE(r7.paterno_productor,'X') ")
//			.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r7.materno_productor,'X')  ")
//			.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r7.nombre_productor,'X') and r.folio_carta_adhesion = r7.folio_carta_adhesion  ")
			.append("and r.folio_carta_adhesion = r7.folio_carta_adhesion  ")
			.append(") as  volumen_no_procedente ")
			.append("from relacion_compras_tmp  r  ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("and (boleta_incosistente = true or factura_inconsistente = true or pago_inconsistente = true or facturas_mayores_boletas = true or pagos_menores_facturas = true or rfc_inconsistente = true  or predio_no_pagado = true or COALESCE(r.volumen_no_procedente,0) > 0 )")
			.append("group by folio_carta_adhesion, clave_bodega,  nombre_estado, r.estado_acopio, folio_contrato, r.rfc_productor, paterno_productor, materno_productor, nombre_productor, curp_productor");
			
			System.out.println("Query Resumen observaciones "+consulta.toString());
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				BoletasFacturasPagosIncosistentes b = new BoletasFacturasPagosIncosistentes();
		        b.setFolioContrato((String) row.get("folio_contrato"));
		        b.setClaveBodega((String) row.get("clave_bodega"));
		        b.setNombreEstado((String) row.get("nombre_estado"));
		        b.setPaternoProductor((String) row.get("paterno_productor"));
		        b.setMaternoProductor((String) row.get("materno_productor"));
		        b.setNombreProductor((String) row.get("nombre_productor"));
		        b.setCurpProductor((String) row.get("curp_productor"));
		        b.setRfcProductor((String) row.get("rfc_productor"));
		        BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
		        b.setVolTotalFacturado(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("volumen_boletas_inc"); 
		        b.setVolBolTicket(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("volumen_facturas_inc"); 
		        b.setVolTotalFacVenta(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("vol_en_pagos");
		        b.setVolEnpagos(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("volumen_no_procedente");
		        b.setVolumenNoProcedente(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("facturas_mayores_boletas");
		        b.setDiferenciaEntreVolumen(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("pagos_menores_facturas");
		        b.setDiferenciaEntreImportes(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("diferencia_entre_rfc");
		        b.setDiferenciaEntreRFC(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("predio_no_pagado");
		        b.setPredioNoPagado(valor!=null ? valor.doubleValue():0.0);
		        lst.add(b);	
			}
			
			
			
			return lst;
		}		

		public int actualizaBolFactPagEnRelacionComprasTMP(String folioCartaAdhesion, boolean aplicaBoletas, boolean aplicaFacturas, boolean aplicaPagos)throws JDBCException {
			int elementosActuializados = 0;
			try{
				StringBuilder set  = new StringBuilder();
				set.append("set " );
				if(aplicaBoletas){
					set.append(" boleta_incosistente = null,");
				}
				if(aplicaFacturas){
					set.append(" factura_inconsistente = null,");
				}
				if(aplicaPagos){
					set.append(" pago_inconsistente = null,");
				}				
				set.deleteCharAt(set.length()-1);		
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(set.toString())
				.append(" where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		public int actualizaCamposIconsistentes(String folioCartaAdhesion, boolean facMayBol, boolean pagMenFac, boolean predioInconsistente, boolean volumenNoprocedente)throws JDBCException {
			return actualizaCamposIconsistentes( folioCartaAdhesion,  facMayBol,  pagMenFac,  predioInconsistente, volumenNoprocedente, false);
		}
		public int actualizaCamposIconsistentes(String folioCartaAdhesion, boolean facMayBol, boolean pagMenFac, boolean predioInconsistente, boolean volumenNoprocedente, boolean rfcInconsistente)throws JDBCException {
			int elementosActuializados = 0;
			try{
				StringBuilder set  = new StringBuilder();
				set.append("set " );
				if(facMayBol){
					set.append(" facturas_mayores_boletas = null, dif_volumen_fac_mayor = null,");
					
				}
				
				if(pagMenFac){
					set.append(" pagos_menores_facturas = null,");
				}
				
				if(predioInconsistente){
					set.append(" predio_inconsistente = null,");
					set.append(" precio_no_pagado = null,");
					
				}
				
				if(volumenNoprocedente){
					set.append(" volumen_no_procedente = null,");
				}
				
				if(rfcInconsistente){
					set.append(" rfc_inconsistente = null,");
				}
								
				set.deleteCharAt(set.length()-1);		
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(set.toString())
				.append(" where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ");
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		
		public int actualizaFacMayBolOPagMenFac(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, String curpProductor, String rfcProductor, boolean facMayBol, boolean pagMenFac)throws JDBCException {
			return  actualizaFacMayBolOPagMenFac(folioCartaAdhesion, claveBodega, nombreEstado, folioContrato,
					paternoProductor, maternoProductor, curpProductor, rfcProductor,nombreProductor, facMayBol, pagMenFac,  false, false);
		}
		
		public int actualizaFacMayBolOPagMenFac(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, String curpProductor, String rfcProductor, boolean facMayBol, boolean pagMenFac, boolean rfcInconsistente, boolean facIncXTipoCambio)throws JDBCException {
			return  actualizaFacMayBolOPagMenFac(folioCartaAdhesion,  claveBodega, nombreEstado, folioContrato,
					 paternoProductor, maternoProductor, nombreProductor, curpProductor,  rfcProductor,  facMayBol,  pagMenFac, rfcInconsistente,  facIncXTipoCambio, false);
		}
		public int actualizaFacMayBolOPagMenFac(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, String curpProductor, String rfcProductor, boolean facMayBol, boolean pagMenFac, boolean rfcInconsistente, boolean facIncXTipoCambio, boolean predioNoPagado)throws JDBCException {
			int elementosActuializados = 0;
			StringBuilder where = new StringBuilder();
			//Actualiza incosistencias por unidad de produccion
			try{
				StringBuilder set  = new StringBuilder();
				if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
					where.append(" where folio_carta_adhesion ='").append(folioCartaAdhesion).append("' ");
				}
				
				if (claveBodega != null && !claveBodega.isEmpty()){
					if(where.length()>0){
						where.append(" and clave_bodega ='").append(claveBodega).append("' ");
					}else{
						where.append(" where clave_bodega ='").append(claveBodega).append("' ");
					}
				}
				
				if (nombreEstado != null && !nombreEstado.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_estado ='").append(nombreEstado).append("' ");
					}else{
						where.append(" where nombre_estado ='").append(nombreEstado).append("' ");
					}
				}	
				if (folioContrato != null && !folioContrato.isEmpty()){
					if(where.length()>0){
						where.append(" and folio_contrato ='").append(folioContrato).append("' ");
					}else{
						where.append(" where folio_contrato ='").append(folioContrato).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and folio_contrato is null ");
					}else{
						where.append(" where folio_contrato is null ");
					}
				}
				if (paternoProductor != null && !paternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and paterno_productor ='").append(paternoProductor).append("' ");
					}else{
						where.append(" where paterno_productor ='").append(paternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and paterno_productor is null ");
					}else{
						where.append(" where paterno_productor is null");
					}
				}			
				
				
				if (maternoProductor != null && !maternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and materno_productor ='").append(maternoProductor).append("' ");
					}else{
						where.append(" where materno_productor ='").append(maternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and materno_productor is null ");
					}else{
						where.append(" where materno_productor is null");
					}
				}
				if (nombreProductor != null && !nombreProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_productor ='").append(nombreProductor).append("' ");
					}else{
						where.append(" where nombre_productor ='").append(nombreProductor).append("' ");
					}
				}
				
				if(curpProductor != null && !curpProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and curp_productor ='").append(curpProductor).append("' ");
					}else{
						where.append(" where curp_productor ='").append(curpProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and curp_productor is null");
					}else{
						where.append(" where curp_productor is null");
					}
					if(rfcProductor != null && !rfcProductor.isEmpty()){
						if(where.length()>0){
							where.append(" and rfc_productor ='").append(rfcProductor).append("' ");
						}else{
							where.append(" where rfc_productor ='").append(rfcProductor).append("' ");
						}
					}else{
						if(where.length()>0){
							where.append(" and rfc_productor is null");
						}else{
							where.append(" where rfc_productor is null");
						}
					}
					
				}
				
				set.append("set " );
				if(facMayBol){
					set.append(" facturas_mayores_boletas = true,");
				}				
				if(pagMenFac){
					set.append(" pagos_menores_facturas = true,");
				}
				if(rfcInconsistente){
					set.append(" rfc_inconsistente = true,");
				}
				if(facIncXTipoCambio){
					set.append(" factura_inconsistente = true,");
				}					
				if(predioNoPagado){
					set.append(" predio_no_pagado = true,");
				}
				set.deleteCharAt(set.length()-1);
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(set.toString()).append(where.toString());
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		public int actualizaPagosInconsistente(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, String curpProductor, String rfcProductor, boolean pagos)throws JDBCException {
			int elementosActuializados = 0;
			StringBuilder where = new StringBuilder();
			try{
				StringBuilder set  = new StringBuilder();
				if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
					where.append(" where folio_carta_adhesion ='").append(folioCartaAdhesion).append("' ");
				}
				
				if (claveBodega != null && !claveBodega.isEmpty()){
					if(where.length()>0){
						where.append(" and clave_bodega ='").append(claveBodega).append("' ");
					}else{
						where.append(" where clave_bodega ='").append(claveBodega).append("' ");
					}
				}
				
				if (nombreEstado != null && !nombreEstado.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_estado ='").append(nombreEstado).append("' ");
					}else{
						where.append(" where nombre_estado ='").append(nombreEstado).append("' ");
					}
				}	
				if (folioContrato != null && !folioContrato.isEmpty()){
					if(where.length()>0){
						where.append(" and folio_contrato ='").append(folioContrato).append("' ");
					}else{
						where.append(" where folio_contrato ='").append(folioContrato).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and folio_contrato is null ");
					}else{
						where.append(" where folio_contrato is null ");
					}
				}
				if (paternoProductor != null && !paternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and paterno_productor ='").append(paternoProductor).append("' ");
					}else{
						where.append(" where paterno_productor ='").append(paternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and paterno_productor is null ");
					}else{
						where.append(" where paterno_productor is null");
					}
				}
				if (maternoProductor != null && !maternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and materno_productor ='").append(maternoProductor).append("' ");
					}else{
						where.append(" where materno_productor ='").append(maternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and materno_productor is null ");
					}else{
						where.append(" where materno_productor is null");
					}
				}
				if (nombreProductor != null && !nombreProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_productor ='").append(nombreProductor).append("' ");
					}else{
						where.append(" where nombre_productor ='").append(nombreProductor).append("' ");
					}
				}
				
				if (curpProductor != null && !curpProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and curp_productor ='").append(curpProductor).append("' ");
					}else{
						where.append(" where curp_productor ='").append(curpProductor).append("' ");
					}
				}
				if (rfcProductor != null && !rfcProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and rfc_productor ='").append(rfcProductor).append("' ");
					}else{
						where.append(" where rfc_productor ='").append(rfcProductor).append("' ");
					}
				}
				set.append("set " );				
				if(pagos){
					set.append(" pago_inconsistente = true,");
				}								
				set.deleteCharAt(set.length()-1);
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(set.toString()).append(where.toString());
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		
		
		public int actualizaPredioIncosistente(Long id, String folioCartaAdhesion)throws JDBCException {
			int elementosActuializados = 0;
			StringBuilder where = new StringBuilder();
			try{
				if (id!=null){
					where.append(" where id_relacion_compras_tmp =").append(id).append(" and folio_carta_adhesion = '"+folioCartaAdhesion+"'");
				}				
			
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(" set predio_inconsistente = true ").append(where.toString());
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		
		
		public int actualizaPredioIncosistentePorValorNulo(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor)throws JDBCException {
			int elementosActuializados = 0;
			StringBuilder where = new StringBuilder();
			try{
				
				if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
					where.append(" where folio_carta_adhesion ='").append(folioCartaAdhesion).append("' ");
				}
				
				if (claveBodega != null && !claveBodega.isEmpty()){
					if(where.length()>0){
						where.append(" and clave_bodega ='").append(claveBodega).append("' ");
					}else{
						where.append(" where clave_bodega ='").append(claveBodega).append("' ");
					}
				}
				
				if (nombreEstado != null && !nombreEstado.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_estado ='").append(nombreEstado).append("' ");
					}else{
						where.append(" where nombre_estado ='").append(nombreEstado).append("' ");
					}
				}
				
				if (folioContrato != null && !folioContrato.isEmpty()){
					if(where.length()>0){
						where.append(" and folio_contrato ='").append(folioContrato).append("' ");
					}else{
						where.append(" where folio_contrato ='").append(folioContrato).append("' ");
					}
				}	
				if (paternoProductor != null && !paternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and paterno_productor ='").append(paternoProductor).append("' ");
					}else{
						where.append(" where paterno_productor ='").append(paternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and paterno_productor  is null ");
					}else{
						where.append(" where paterno_productor  is null ");
					}
				}
				if (maternoProductor != null && !maternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and materno_productor ='").append(maternoProductor).append("' ");
					}else{
						where.append(" where materno_productor ='").append(maternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and materno_productor is null ");
					}else{
						where.append(" where materno_productor is null");
					}
				}
				if (nombreProductor != null && !nombreProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_productor ='").append(nombreProductor).append("' ");
					}else{
						where.append(" where nombre_productor ='").append(nombreProductor).append("' ");
					}
				}			
			
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(" set predio_inconsistente = true ").append(where.toString());
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		
		public int actualizaVolumenNoProcedente(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, String curpProductor, String rfcProductor, String volumenNoProcedente)throws JDBCException {	// AHS [LINEA] - 17022015
			int elementosActuializados = 0;
			StringBuilder where = new StringBuilder();
			try{
				if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
					where.append(" where folio_carta_adhesion ='").append(folioCartaAdhesion).append("' ");
				}
				

				if (claveBodega != null && !claveBodega.isEmpty()){
					if(where.length()>0){
						where.append(" and clave_bodega ='").append(claveBodega).append("' ");
					}else{
						where.append(" where clave_bodega ='").append(claveBodega).append("' ");
					}
				}
				
				if (nombreEstado != null && !nombreEstado.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_estado ='").append(nombreEstado).append("' ");
					}else{
						where.append(" where nombre_estado ='").append(nombreEstado).append("' ");
					}
				}
				
				if (folioContrato != null && !folioContrato.isEmpty()){
					if(where.length()>0){
						where.append(" and folio_contrato ='").append(folioContrato).append("' ");
					}else{
						where.append(" where folio_contrato ='").append(folioContrato).append("' ");
					}
				}	
				if (paternoProductor != null && !paternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and paterno_productor ='").append(paternoProductor).append("' ");
					}else{
						where.append(" where paterno_productor ='").append(paternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and paterno_productor  is null ");
					}else{
						where.append(" where paterno_productor  is null ");
					}
				}
				if (maternoProductor != null && !maternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and materno_productor ='").append(maternoProductor).append("' ");
					}else{
						where.append(" where materno_productor ='").append(maternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and materno_productor is null ");
					}else{
						where.append(" where materno_productor is null");
					}
				}
				if (nombreProductor != null && !nombreProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_productor ='").append(nombreProductor).append("' ");
					}else{
						where.append(" where nombre_productor ='").append(nombreProductor).append("' ");
					}
				}
				
				if(curpProductor != null && !curpProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and curp_productor ='").append(curpProductor).append("' ");
					}else{
						where.append(" where curp_productor ='").append(curpProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and curp_productor is null");
					}else{
						where.append(" where curp_productor is null");
					}
					if(rfcProductor != null && !rfcProductor.isEmpty()){
						if(where.length()>0){
							where.append(" and rfc_productor ='").append(rfcProductor).append("' ");
						}else{
							where.append(" where rfc_productor ='").append(rfcProductor).append("' ");
						}
					}else{
						if(where.length()>0){
							where.append(" and rfc_productor is null");
						}else{
							where.append(" where rfc_productor is null");
						}
					}
					
				}
				
				
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(" set volumen_no_procedente = "+volumenNoProcedente).append(where.toString()); // AHS [LINEA] - 17022015
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		
		
		public int actualizaDiferenciaVolumenXFacturaMayor(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, double difVolumenFacMayor)throws JDBCException {	// AHS [LINEA] - 17022015
			int elementosActuializados = 0;
			StringBuilder where = new StringBuilder();
			try{
				if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
					where.append(" where folio_carta_adhesion ='").append(folioCartaAdhesion).append("' ");
				}
				
				if (folioContrato != null && !folioContrato.isEmpty()){
					if(where.length()>0){
						where.append(" and folio_contrato ='").append(folioContrato).append("' ");
					}else{
						where.append(" where folio_contrato ='").append(folioContrato).append("' ");
					}
				}	
				if (paternoProductor != null && !paternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and paterno_productor ='").append(paternoProductor).append("' ");
					}else{
						where.append(" where paterno_productor ='").append(paternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and paterno_productor  is null ");
					}else{
						where.append(" where paterno_productor  is null ");
					}
				}
				if (maternoProductor != null && !maternoProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and materno_productor ='").append(maternoProductor).append("' ");
					}else{
						where.append(" where materno_productor ='").append(maternoProductor).append("' ");
					}
				}else{
					if(where.length()>0){
						where.append(" and materno_productor  is null ");
					}else{
						where.append(" where materno_productor  is null ");
					}
				}
				if (nombreProductor != null && !nombreProductor.isEmpty()){
					if(where.length()>0){
						where.append(" and nombre_productor ='").append(nombreProductor).append("' ");
					}else{
						where.append(" where nombre_productor ='").append(nombreProductor).append("' ");
					}
				}
				
				
				StringBuilder hql = new StringBuilder()			
				.append("UPDATE  relacion_compras_tmp  ").append(" set dif_volumen_fac_mayor = "+difVolumenFacMayor).append(where.toString()); // AHS [LINEA] - 17022015
				elementosActuializados = session.createSQLQuery(hql.toString()).executeUpdate();
				
			}catch (JDBCException e){
				transaction.rollback();
				throw e;
			}	
			return elementosActuializados;
		}
		public List<RelacionComprasTMP> consultaRFCFacturaVentaByProductor(String folioCartaAdhesion) throws JDBCException {			
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta= new StringBuilder();	
			
			consulta.append("select r.folio_carta_adhesion, r.clave_bodega,  r.nombre_estado,  r.folio_contrato,r.paterno_productor, r.materno_productor, r.nombre_productor,  ")
					.append("(select  rfc_fac_venta from relacion_compras_tmp r1   ")
					.append("where r.folio_carta_adhesion = r1.folio_carta_adhesion and  r.clave_bodega = r1.clave_bodega and r.nombre_estado = r1.nombre_estado and  coalesce(r.folio_contrato,'X') = coalesce(r1.folio_contrato,'X') and ")
					.append(" coalesce(r.paterno_productor,'X') = coalesce(r1.paterno_productor,'X') and  coalesce(r.materno_productor,'X') = coalesce(r1.materno_productor,'X') and coalesce(r.nombre_productor,'X') = coalesce(r1.nombre_productor,'X')  and rfc_fac_venta is not null limit 1 ) as rfc_fac_venta  ")
				.append("from relacion_compras_tmp r where r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("group by r.folio_carta_adhesion, r.clave_bodega,  r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor ") 
				.append("order by r.folio_carta_adhesion, r.clave_bodega,  r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP b = new RelacionComprasTMP();
				b.setClaveBodega((String) row.get("clave_bodega"));
			    b.setNombreEstado((String) row.get("nombre_estado"));
			    b.setPaternoProductor((String) row.get("paterno_productor"));
			    b.setMaternoProductor((String) row.get("materno_productor"));
			    b.setNombreProductor((String) row.get("nombre_productor"));
			    b.setFolioContrato((String) row.get("folio_contrato"));
			    b.setRfcFacVenta((String) row.get("rfc_fac_venta"));
			    lst.add(b);	
			}
			
			return lst;

		}
		
		//Actualiza RFC productor
		public int actualizaRFCProductor(String folioCartaAdhesion, String claveBodega, String nombreEstado, String folioContrato,
				String paternoProductor, String maternoProductor, String nombreProductor, String rfcFacturaVenta) throws JDBCException {	
			int elementosActualizados = 0;
			StringBuilder where = new StringBuilder();
			where.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
				.append("and clave_bodega = '").append(claveBodega).append("' ")
				.append("and nombre_estado = '").append(nombreEstado).append("' ");
			
			if (folioContrato != null && !folioContrato.isEmpty()){
				if(where.length()>0){
					where.append(" and folio_contrato ='").append(folioContrato).append("' ");
				}else{
					where.append("where folio_contrato ='").append(folioContrato).append("' ");
				}
			}	
			if (paternoProductor != null && !paternoProductor.isEmpty()){
				if(where.length()>0){
					where.append(" and paterno_productor ='").append(paternoProductor).append("' ");
				}else{
					where.append("where paterno_productor ='").append(paternoProductor).append("' ");
				}
			}else{
				if(where.length()>0){
					where.append(" and paterno_productor  is null ");
				}else{
					where.append(" where paterno_productor  is null ");
				}
			}
			if (maternoProductor != null && !maternoProductor.isEmpty()){
				if(where.length()>0){
					where.append(" and materno_productor ='").append(maternoProductor).append("' ");
				}else{
					where.append("where materno_productor ='").append(maternoProductor).append("' ");
				}
			}else{
				if(where.length()>0){
					where.append(" and materno_productor  is null ");
				}else{
					where.append(" where materno_productor  is null ");
				}
			}
			if (nombreProductor != null && !nombreProductor.isEmpty()){
				if(where.length()>0){
					where.append(" and nombre_productor ='").append(nombreProductor).append("' ");
				}else{
					where.append("where nombre_productor ='").append(nombreProductor).append("' ");
				}
			}
			
			StringBuilder hql = new StringBuilder();
			if(rfcFacturaVenta!=null){
				hql.append("UPDATE  relacion_compras_tmp  set rfc_productor  = '").append(rfcFacturaVenta.trim()).append("'").append(where.toString());				
				elementosActualizados = session.createSQLQuery(hql.toString()).executeUpdate();
			}
			
			return elementosActualizados;
			
		}
		
		

		public List<RendimientosProcedente> consultaRendimientosProcedente(String folioCartaAdhesion, int idPrograma)throws  JDBCException{
			List<RendimientosProcedente> lst = new ArrayList<RendimientosProcedente>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("select  r.clave_bodega,  r.nombre_estado, r.estado_acopio, r.folio_contrato, r.rfc_productor, curp_productor, r.paterno_productor, r.materno_productor, r.nombre_productor, ")
			.append("vol_total_fac_venta, rendimiento_maximo, volumen_no_procedente ")
			.append("from volumen_no_procedente_v r ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("and id_programa = ").append(idPrograma).append(" and volumen_no_procedente > 0");
			System.out.println("Query volumen no procedente "+consulta.toString());
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RendimientosProcedente b = new RendimientosProcedente();
		        b.setFolioContrato((String) row.get("folio_contrato"));
		        b.setClaveBodega((String) row.get("clave_bodega"));
		        b.setNombreEstado((String) row.get("nombre_estado"));
		        b.setPaternoProductor((String) row.get("paterno_productor"));
		        b.setMaternoProductor((String) row.get("materno_productor"));
		        b.setCurpProductor((String) row.get("curp_productor"));
		        b.setRfcProductor((String) row.get("rfc_productor"));
		        BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
		        b.setVolTotalFacturado(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("rendimiento_maximo"); 
		        b.setRendimientoMaximoAceptable(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("volumen_no_procedente"); 
		        b.setVolNoProcedente(valor!=null ? valor.toString():"0.0");	// AHS [LINEA] - 17022015
		        
		        lst.add(b);	
			}		
			return lst;
		}	
		@SuppressWarnings("unchecked")
		public List<PrediosProdsContNoExisteBD> verificaSiPrediosProdsContExistenBD(String folioCartaAdhesion, int idPrograma)throws JDBCException{
			List<PrediosProdsContNoExisteBD> lst = null;
			StringBuilder consulta= new StringBuilder();
//			consulta.append("select distinct clave_bodega, nombre_estado, folio_contrato, productor, paterno_productor, materno_productor, nombre_productor, folio_predio, folio_predio_sec, null AS folio_predio_alterno, id_relacion_compras_tmp AS id ")
//			.append("from relacion_compras_tmp  r ")
//			.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
//			.append("and  folio_predio is not null ")
//			.append("and folio_predio_sec is not null ")
//			.append("and not exists ")
//			.append("	(select 1 ")
//			.append("	from predios_relaciones p ")
//			.append("	where p.predio = r.folio_predio ")
//			.append("	and p.predio_secuencial = r.folio_predio_sec ")
//			.append("	and p.productor = r.productor ")
//			.append("	and p.folio_contrato = r.folio_contrato ")
//			.append("	and p.id_programa =").append(idPrograma).append(") ")
//			.append("UNION ")
//			.append("select  distinct clave_bodega, nombre_estado, folio_contrato, productor, paterno_productor, materno_productor, nombre_productor, NULL AS folio_predio, 0 AS folio_predio_sec, folio_predio_alterno, id_relacion_compras_tmp AS id ")
//			.append("from relacion_compras_tmp  r ")
//			.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
//			.append("and  folio_predio_alterno is not null ")
//			.append("and not exists ")
//			.append("	(select 1 ")
//			.append("	from predios_relaciones p ")
//			.append("	where p.predio_alterno = r.folio_predio_alterno ")
//			.append("	and p.productor = r.productor ")
//			.append("	and p.folio_contrato = r.folio_contrato	")
//			.append("	and p.id_programa =").append(idPrograma).append(") ");
//			
		
			consulta.append("select distinct clave_bodega, nombre_estado, folio_contrato, productor, paterno_productor, ")
			.append("materno_productor, nombre_productor, folio_predio, folio_predio_sec, null AS folio_predio_alterno, id_relacion_compras_tmp AS id, ")
			.append("(select folio_carta_adhesion ")
			.append("from relacion_compras_tmp ")
			.append("where folio_contrato = r.folio_contrato ")
			.append("and rfc_productor = r.rfc_productor ")
			.append("and folio_predio = r.folio_predio  ")
			.append("and folio_predio_sec = r.folio_predio_sec ") 
			.append("and folio_carta_adhesion != r.folio_carta_adhesion ")
			.append("limit 1 ")
			.append(") folio_carta_externa ")	
			.append("from relacion_compras_tmp  r ") 
			.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ") 
			.append("and  folio_predio is not null  ")
			.append("and  folio_predio_sec is not null ") 
			.append("and  ")
			.append("	((exists ") 
			.append("(select 1 ")
			.append("from relacion_compras_tmp ")
			.append("where folio_contrato = r.folio_contrato ")
			.append("and rfc_productor = r.rfc_productor ")
			.append(" and folio_predio = r.folio_predio  ")
			.append("and folio_predio_sec = r.folio_predio_sec ") 
			.append(" and folio_carta_adhesion != r.folio_carta_adhesion ")
			.append("	)) ")
			.append(" OR 	 ")
			.append("(not exists ") 
			.append("(select 1  ")
			.append("from predios_relaciones p ") 
			.append("where p.predio = r.folio_predio ") 
			.append("and p.predio_secuencial = r.folio_predio_sec ") 
			.append("and p.productor = r.productor 	 ")
			.append("and p.folio_contrato = r.folio_contrato ") 	
			.append("and p.id_programa =").append(idPrograma).append(") ) ")
			.append(") ")
			.append("UNION ") 
			.append("select distinct clave_bodega, nombre_estado, folio_contrato, productor, paterno_productor, materno_productor, nombre_productor, ") 
			.append("NULL AS folio_predio, 0 AS folio_predio_sec, folio_predio_alterno, id_relacion_compras_tmp AS id , ")
			.append("(select folio_carta_adhesion ")
			.append("from relacion_compras_tmp ")
			.append("where folio_contrato = r.folio_contrato ")
			.append("and rfc_productor = r.rfc_productor ")
			.append("and folio_predio_alterno = r.folio_predio_alterno ")
			.append("and folio_carta_adhesion != r.folio_carta_adhesion ")
			.append("limit 1 ")
			.append(") folio_carta ")
			.append("from relacion_compras_tmp  r ") 
			.append("WHERE  folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ") 
			.append("and  folio_predio_alterno is not null ")
			.append("and ((exists  ")
			.append("(select 1 ")
			.append("from relacion_compras_tmp ")
			.append("where folio_contrato = r.folio_contrato ")
			.append("and rfc_productor = r.rfc_productor ")
			.append("and folio_predio_alterno = r.folio_predio_alterno ")
			.append("and folio_carta_adhesion != r.folio_carta_adhesion ")
			.append(")) ")
			.append("or 	 ")
			.append("(not exists ") 	
			.append("(select 1 from predios_relaciones p ") 
			.append("where p.predio_alterno = r.folio_predio_alterno ") 
			.append(" and p.productor = r.productor  ")
			.append(" and p.folio_contrato = r.folio_contrato ") 
			.append(" and p.id_programa =").append(idPrograma).append(") ) ")
			.append(") ");
			
			
			
			System.out.println("cpredios existan "+consulta.toString());
			lst= session.createSQLQuery(consulta.toString()).addEntity(PrediosProdsContNoExisteBD.class).list();
			return lst;
		}
		
		public List<VolumenFiniquito> consultaVolumenCumplido(String folioCartaAdhesion)throws  JDBCException{
			List<VolumenFiniquito> lst = new ArrayList<VolumenFiniquito>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("select r.clave_bodega, r.folio_contrato, b.nombre_comprador, b.nombre_vendedor, c.precio_pactado_por_tonelada, b.volumen ")
			.append("from relacion_compras_tmp r, bodegas_contratos b, contratos_relacion_compras c ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and r.clave_bodega = b.clave_bodega  and b.folio_contrato = r.folio_contrato and c.folio_contrato = r.folio_contrato ")
			.append("group by r.clave_bodega, r.folio_contrato, b.nombre_comprador, b.nombre_vendedor, c.precio_pactado_por_tonelada, b.volumen ")			
			.append("order by r.clave_bodega, r.folio_contrato"); // AHS CAMBIO 29062015
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				VolumenFiniquito b = new VolumenFiniquito();
				b.setClaveBodega((String) row.get("clave_bodega"));
		        b.setFolioContrato((String) row.get("folio_contrato"));
		        b.setNombreComprador((String) row.get("nombre_comprador"));
		        b.setNombreVendedor((String) row.get("nombre_vendedor"));
		        BigDecimal valor = (BigDecimal) row.get("volumen");
		        b.setVolumen(valor!=null ? valor.doubleValue():0.0);	
		        valor = (BigDecimal) row.get("precio_pactado_por_tonelada");
		        b.setPrecioPactadoPorTonelada(valor!=null ? valor.doubleValue():0.0);		        
		        lst.add(b);	
			}		
			return lst;
		}		
		
		public List<BitacoraRelcompras> consultaBitacoraRelacionHCO(String folioCartaAdhesion)throws  JDBCException{
			List<BitacoraRelcompras> lst = new ArrayList<BitacoraRelcompras>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("select folio_carta_adhesion ")
			.append("from bitacora_relcompras_hco ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and id_criterio =  99");
			

			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				BitacoraRelcompras b = new BitacoraRelcompras();
				b.setFolioCartaAdhesion((String) row.get("folio_carta_adhesion"));		        
		        lst.add(b);	
			}		
			return lst;
		}
		
		public List<BitacoraRelcompras> verificaCargaBitacoraRelCompras(String folioCartaAdhesion)throws  JDBCException{
			List<BitacoraRelcompras> lst = new ArrayList<BitacoraRelcompras>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("select coalesce(carga,false) as carga ")
			.append("from bitacora_relcompras ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and id_criterio = 99");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				BitacoraRelcompras b = new BitacoraRelcompras();
				b.setCarga((Boolean) row.get("carga"));		        
		        lst.add(b);	
			}		
			return lst;
		}

		//Actualiza Bandera Carga de Archivo
		public int actualiceCargaBitacoraRelCompras(String folioCartaAdhesion) throws JDBCException {	
			int elementosActualizados = 0;
			StringBuilder where = new StringBuilder();
			where.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' and id_criterio = 99");
			
			StringBuilder hql = new StringBuilder()			
			.append("UPDATE  bitacora_relcompras  set carga = true ").append(where.toString());
			
			elementosActualizados = session.createSQLQuery(hql.toString()).executeUpdate();
			return elementosActualizados;
			
		}
		
		public List<PrecioPagPorTipoCambio> consultaPrecioPagPorTipoCambio(String folioCartaAdhesion)throws  JDBCException{
			List<PrecioPagPorTipoCambio> lst = new ArrayList<PrecioPagPorTipoCambio>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("select r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor, v1.vol_total_fac_venta, v1.precio_pactado_por_tonelada, v1.imp_sol_fac_venta, v1.importe_calculado_a_pagar, v1.diferecia_importe_pagado ")
			.append("from contrato_tipo_cambio_v v1, relacion_compras_tmp r ")
			.append("where r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("and COALESCE(r.folio_contrato,'X') = COALESCE(v1.folio_contrato,'X') ")
			.append("and COALESCE(r.clave_bodega,'X') = COALESCE(v1.clave_bodega,'X') ") // AHS 26062015			
			.append("and coalesce(r.paterno_productor,'X') = coalesce(v1.paterno_productor,'X') ")
			.append("and coalesce(r.materno_productor,'X') =  coalesce(v1.materno_productor,'X')  and coalesce(r.nombre_productor ,'X') = coalesce(v1.nombre_productor,'X') ")
			.append("and coalesce(r.curp_productor,'X') =  coalesce(v1.curp_productor,'X')  and coalesce(r.rfc_productor ,'X') = coalesce(v1.rfc_productor,'X') ")
			.append("and diferecia_importe_pagado > 1.00 ")
			.append("group by  r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor,v1.vol_total_fac_venta,v1.precio_pactado_por_tonelada, v1.imp_sol_fac_venta, v1.importe_calculado_a_pagar, v1.diferecia_importe_pagado ")
			.append("order by r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor ");
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			System.out.println("Tipo cambio "+consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				PrecioPagPorTipoCambio b = new PrecioPagPorTipoCambio();	
				b.setClaveBodega((String) row.get("clave_bodega"));
				b.setNombreEstado((String) row.get("nombre_estado"));
				b.setFolioContrato((String) row.get("folio_contrato"));
				b.setPaternoProductor((String) row.get("paterno_productor"));
				b.setMaternoProductor((String) row.get("materno_productor"));
				b.setNombreProductor((String) row.get("nombre_productor"));
				b.setCurpProductor((String) row.get("curp_productor"));
				b.setRfcProductor((String) row.get("rfc_productor"));
				BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
				b.setVolTotalFacVenta(valor.doubleValue());
				valor = (BigDecimal) row.get("precio_pactado_por_tonelada");
				b.setPrecioPactadoPorTonelada(valor.doubleValue());
				valor = (BigDecimal) row.get("imp_sol_fac_venta");
				b.setImpSolFacVenta(valor.doubleValue());				
				valor = (BigDecimal) row.get("importe_calculado_a_pagar");
				b.setImporteContrato(valor.doubleValue());
				valor = (BigDecimal) row.get("diferecia_importe_pagado");
				b.setDiferenciaImporte(valor.doubleValue());		
		        lst.add(b);	
			}		
			return lst;
		}
		
		
		public List<BitacoraRelcompras> consultaBitacoraRelcompras(String folioCartaAdhesion)throws  JDBCException{
			List<BitacoraRelcompras> lst = new ArrayList<BitacoraRelcompras>(); 
			StringBuilder consulta= new StringBuilder();	
			
			consulta.append("select criterio, ruta_archivo, nombre_archivo from bitacora_relcompras b, cat_criterios_validacion c ")  
					.append("where  b.id_criterio = c.id_criterio and status = 0 and ")
					.append(" folio_carta_adhesion = '").append(folioCartaAdhesion).append("'  ")
					.append(" and nombre_archivo is not null ")
					.append("order by numero_validacion ");
			
			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				BitacoraRelcompras b = new BitacoraRelcompras();
				b.setMensaje((String) row.get("criterio"));
				b.setNombreArchivo((String) row.get("nombre_archivo"));
				b.setRutaArchivo((String) row.get("ruta_archivo"));
				
						
		        lst.add(b);	
			}		
			return lst;
		}
		
		//FRIJOL
		public List<RelacionComprasTMP> validaRFCFacVsFacComprador(String folioCartaAdhesion, int idPrograma)throws  JDBCException{			
			List<RelacionComprasTMP> lst = new ArrayList<RelacionComprasTMP>();
			StringBuilder consulta= new StringBuilder();				
			consulta.append("SELECT row_number() OVER () AS id, r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, ")
					.append("p.rfc_productor, r.rfc_fac_venta, ")
					.append("(select COALESCE(sum(vol_total_fac_venta),0) ") 
					.append("from relacion_compras_tmp r1  ")
					.append("where r.clave_bodega = r1.clave_bodega ") 
					.append("and  r.nombre_estado = r1.nombre_estado  ")
					.append("and  r.nombre_estado = r1.nombre_estado ")
					.append("and  r.folio_contrato = r1.folio_contrato  ")
					.append("and COALESCE(r.paterno_productor,'X') =  COALESCE(r1.paterno_productor,'X') ") 
					.append("and  COALESCE(r.materno_productor,'X') =  COALESCE(r1.materno_productor,'X' ) ")
					.append("and  COALESCE(r.nombre_productor,'X') =  COALESCE(r1.nombre_productor,'X')  ) as vol_total_fac_venta ")
					.append("FROM relacion_compras_tmp r, pagos_topes_relaciones p ")
					.append("WHERE r.rfc_fac_venta is not null ")
					.append("and r.folio_carta_adhesion = '").append(folioCartaAdhesion).append("'  ")
					.append("and CASE WHEN r.curp_productor IS NOT NULL THEN r.curp_productor ELSE r.rfc_productor END = CASE WHEN r.curp_productor IS NOT NULL THEN p.curp_productor ELSE p.rfc_productor END ")
					.append("and p.estado = r.estado_acopio ")
					.append("and p.cultivo = r.id_cultivo ")
					.append("and p.variedad = r.id_variedad ")
					.append("and p.id_programa = r.id_programa ")
					.append("and p.rfc_comprador <> r.rfc_fac_venta ") 
					.append("AND EXISTS (select distinct 1 from predios_relaciones pr where pr.curp = p.curp_productor and pr.id_programa = ").append(idPrograma).append(") ")
					.append("GROUP BY r.clave_bodega, r.nombre_estado, r.folio_contrato, r.paterno_productor, r.materno_productor, r.nombre_productor, p.rfc_productor, r.rfc_fac_venta ");			
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionComprasTMP b = new RelacionComprasTMP();
				b.setClaveBodega((String) row.get("clave_bodega"));
				b.setNombreEstado((String) row.get("nombre_estado"));
				b.setFolioContrato((String) row.get("folio_contrato"));
				b.setPaternoProductor((String) row.get("paterno_productor"));
				b.setMaternoProductor((String) row.get("materno_productor"));
				b.setNombreProductor((String) row.get("nombre_productor"));
				b.setRfcProductor((String) row.get("rfc_productor"));
				b.setRfcFacVenta((String) row.get("rfc_fac_venta"));
				BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
				b.setVolTotalFacVenta(valor.doubleValue());
						
		        lst.add(b);	
			}		
			return lst;

			
			
		}
		
		
		public List<VolumenNoProcedente> validaVolumenNoProcedenteYApoyadoEnReg(String folioCartaAdhesion, int idPrograma)throws  JDBCException{
			List<VolumenNoProcedente> lst = new ArrayList<VolumenNoProcedente>();
			StringBuilder consulta= new StringBuilder();	
			consulta.append("select  r.clave_bodega,  r.nombre_estado, r.folio_contrato,  r.paterno_productor, r.materno_productor, r.nombre_productor, r.curp_productor, r.rfc_productor,")
			.append("vol_total_fac_venta, volumen_pagado, volumen_no_procedente, id_variedad ")
			.append("from volumen_no_procedente_y_apoyado_reg r ")
			.append("where folio_carta_adhesion = '").append(folioCartaAdhesion).append("' ")
			.append("and id_programa = ").append(idPrograma).append(" and volumen_no_procedente > 0");
			System.out.println("Query volumen no procedente por lo apoyado en regional "+consulta.toString());
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();
			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				VolumenNoProcedente b = new VolumenNoProcedente();
		        b.setFolioContrato((String) row.get("folio_contrato"));
		        b.setClaveBodega((String) row.get("clave_bodega"));
		        b.setNombreEstado((String) row.get("nombre_estado"));
		        b.setPaternoProductor((String) row.get("paterno_productor"));
		        b.setMaternoProductor((String) row.get("materno_productor"));
		        b.setNombreProductor((String) row.get("nombre_productor"));
		        b.setCurpProductor((String) row.get("curp_productor"));
		        b.setRfcProductor((String) row.get("rfc_productor"));
		        BigDecimal valor = (BigDecimal) row.get("vol_total_fac_venta");
		        b.setVolTotalFacturado(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("volumen_pagado"); 
		        b.setVolApoyadoEnRegional(valor!=null ? valor.doubleValue():0.0);
		        valor = (BigDecimal) row.get("volumen_no_procedente"); 
		        b.setVolNoProcedente(valor!=null ? valor.doubleValue():0.0);	
		        Integer idVariedad = (Integer) row.get("id_variedad");
		        b.setIdVariedad(idVariedad);
		        lst.add(b);	
			}		
			return lst;
		}


		
		
		public List<RelacionVentasV> consultaRelVentasV(long id, String folioCartaAdhesion)throws  JDBCException{
			List<RelacionVentasV> lst = new ArrayList<RelacionVentasV>();
			StringBuilder consulta= new StringBuilder();	
			
			if (id != 0 && id != -1){
				consulta.append("where id=").append(id);
			}		
						
			if (folioCartaAdhesion !=  null && !folioCartaAdhesion.isEmpty()){
				if(consulta.length()>0){
					consulta.append(" and folio_carta_adhesion ='").append(folioCartaAdhesion).append("'");
				}else{
					consulta.append("where folio_carta_adhesion ='").append(folioCartaAdhesion).append("'");
				}
			}
			
			consulta.insert(0, "select  * from relacion_ventas_v ");
			SQLQuery query = session.createSQLQuery(consulta.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<?> data = query.list();			
			for(Object object : data){
				Map<?, ?> row = (Map<?, ?>)object;
				RelacionVentasV b = new RelacionVentasV();
				Integer idRelVentas  = (Integer) row.get("id");
				b.setId(idRelVentas.longValue());
				b.setCliente((String) row.get("nombre_cliente"));
				b.setDireccionCliente((String) row.get("direccion_cliente"));
				b.setFolioCartaAdhesion((String) row.get("folio_carta_adhesion"));
				b.setFolioFactura((String) row.get("folio_factura"));
				BigDecimal valor = (BigDecimal) row.get("volumen");
			    b.setVolumen(valor!=null ? valor.doubleValue():0.0);
			    Date fecha = (Date) row.get("fecha");
			    b.setFecha(fecha!=null ? fecha:null);
				b.setDestino((String) row.get("destino"));
				b.setDesUsoGrano((String) row.get("uso_grano"));
		        lst.add(b);	
			}		
			return lst;
		}

		@SuppressWarnings("unchecked")
		public List<RelacionVentas> consultaRelacionVenta(long id)throws JDBCException{
			StringBuilder consulta= new StringBuilder();
			List<RelacionVentas> lst = null;
			
			if (id != 0 && id != -1){
				consulta.append("where id=").append(id);
			}
			consulta.insert(0, "From RelacionVentas ").append(" ORDER BY idCliente");
			lst= session.createQuery(consulta.toString()).list();
			
			return lst;
		}
}
