package mx.gob.comer.sipc.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.CuotasEsquema;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.ExpedienteAuditor;
import mx.gob.comer.sipc.domain.ExpedienteComprador;
import mx.gob.comer.sipc.domain.InicializacionEsquema;

import mx.gob.comer.sipc.domain.catalogos.Variedad;
import mx.gob.comer.sipc.domain.transaccionales.AlcanceSolicitudInscripcion;
import mx.gob.comer.sipc.domain.transaccionales.AsignacionCartasAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.CicloPrograma;
import mx.gob.comer.sipc.domain.transaccionales.CultivoVariedadEsquema;
import mx.gob.comer.sipc.domain.transaccionales.DetalleAsignacionCartasAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.ProgramaEstado;
import mx.gob.comer.sipc.domain.transaccionales.SolicitudInscripcion;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.AsignacionCartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.CartasAdhesionV;
import mx.gob.comer.sipc.vistas.domain.CiclosProgramasV;
import mx.gob.comer.sipc.vistas.domain.CompradorExpedientesV;
import mx.gob.comer.sipc.vistas.domain.CuotasEsquemaV;
import mx.gob.comer.sipc.vistas.domain.EtapaIniEsquemaV;

import mx.gob.comer.sipc.vistas.domain.HcoProgramasV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCultivo;
import mx.gob.comer.sipc.vistas.domain.SolicitudInscripcionV;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class InscripcionDAO {

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public InscripcionDAO (){
		
	}
	
	public InscripcionDAO(Session session) {
		this.session = session;
	}
		
	@SuppressWarnings("unchecked")
	public List<CompradorExpedientesV> consultaCompradoresExpedientesV(long idExpedienteComprador,  int idComprador, int idExpediente) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CompradorExpedientesV> lst=null;
		if (idExpedienteComprador != 0 && idExpedienteComprador != -1){
			consulta.append("where idExpedienteComprador = ").append(idExpedienteComprador);
		}
		
		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador=").append(idComprador);
			}else{
				consulta.append("where idComprador=").append(idComprador);
			}
		}
		
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}

		consulta.insert(0, "From CompradorExpedientesV ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteComprador> consultaExpedientComprador(long idExpedienteComprador, int idProgramaComprador, int idExpediente) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ExpedienteComprador> lst=null;
		if (idExpedienteComprador != 0 && idExpedienteComprador != -1){
			consulta.append("where idExpedienteComprador = ").append(idExpedienteComprador);
		}
		if (idProgramaComprador != 0 && idProgramaComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idProgramaComprador=").append(idProgramaComprador);
			}else{
				consulta.append("where idProgramaComprador=").append(idProgramaComprador);
			}
		}
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}
		consulta.insert(0, "From ExpedienteComprador ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<CuotasEsquemaV> consultaCuotasEsquemaV(long id, int idPrograma) throws JDBCException {
		return consultaCuotasEsquemaV(id, idPrograma, 0);
	}
	@SuppressWarnings("unchecked")
	public List<CuotasEsquemaV> consultaCuotasEsquemaV(long id, int idPrograma, int idCultivo) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CuotasEsquemaV> lst=null;
		if (id != 0 && id != -1){
			consulta.append("where id = ").append(id);
		}
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo=").append(idCultivo);
			}else{
				consulta.append("where idCultivo=").append(idCultivo);
			}
		}
		consulta.insert(0, "From CuotasEsquemaV ").append(" order by estado, cultivo");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<CuotasEsquema> consultaCuotasEsquema(long idInicializacionEsquema) throws JDBCException {
		return consultaCuotasEsquema(idInicializacionEsquema, -1, -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	public List<CuotasEsquema> consultaCuotasEsquema(long idInicializacionEsquema, int idEstado, int idCultivo, int idVariedad) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CuotasEsquema> lst=null;
		if (idInicializacionEsquema != 0 && idInicializacionEsquema != -1){
			consulta.append("where idInicializacionEsquema = ").append(idInicializacionEsquema);
		}
		
		if (idEstado != 0 && idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado =").append(idEstado);
			}else{
				consulta.append("where idEstado=").append(idEstado);
			}
		}
		
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo =").append(idCultivo);
			}else{
				consulta.append("where idCultivo=").append(idCultivo);
			}
		}
		
		if (idVariedad != 0 && idVariedad != -1){
			if(consulta.length()>0){
				consulta.append(" and idVariedad =").append(idVariedad);
			}else{
				consulta.append("where idVariedad =").append(idVariedad);
			}
		}		
		
		consulta.insert(0, "From CuotasEsquema ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	public List<InicializacionEsquema> consultaInicializacionPrograma(int idPrograma) throws JDBCException {
		return consultaInicializacionPrograma(idPrograma, null);
	}
	@SuppressWarnings("unchecked")
	public List<InicializacionEsquema> consultaInicializacionPrograma(int idPrograma, String acronimoCA) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<InicializacionEsquema> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		if (acronimoCA != null && !acronimoCA.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and acronimoCA = '").append(acronimoCA).append("'");
			} else {
				consulta.append(" where acronimoCA ='").append(acronimoCA.toUpperCase()).append("' ");
			}
		}
		
		consulta.insert(0, "From InicializacionEsquema ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	public List<SolicitudInscripcion> consultaSolicitudInscripcion(String folioCartaAdhesion) throws JDBCException {
		return consultaSolicitudInscripcion(-1, null, 0, folioCartaAdhesion);
	}
	public List<SolicitudInscripcion> consultaSolicitudInscripcion(int idPrograma) throws JDBCException {
		return consultaSolicitudInscripcion(idPrograma, null, 0, "");
	}
	public List<SolicitudInscripcion> consultaSolicitudInscripcion(int idPrograma, String folioSI) throws JDBCException {
		return consultaSolicitudInscripcion(idPrograma, folioSI, 0, "");
	}
	public List<SolicitudInscripcion> consultaSolicitudInscripcion(int idPrograma, String folioSI, long idSI) throws JDBCException {
		return consultaSolicitudInscripcion(idPrograma, folioSI, idSI, "");
	}
	@SuppressWarnings("unchecked")
	public List<SolicitudInscripcion> consultaSolicitudInscripcion(int idPrograma, String folioSI, long idSI, String folioCartaAdhesion) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SolicitudInscripcion> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		if (idSI != 0 && idSI != -1){
			if(consulta.length()>0){
				consulta.append(" and idSI=").append(idSI);
			}else{
				consulta.append("where idSI=").append(idSI);
			}
		}
		if (folioSI != null && folioSI != ""){
			if(consulta.length()>0){
				consulta.append(" and folioSI='").append(folioSI).append("'");
			}else{
				consulta.append("where folioSI='").append(folioSI).append("'");
			}
		}
		if (folioCartaAdhesion != null && folioCartaAdhesion != ""){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}
		}
		consulta.insert(0, "From SolicitudInscripcion ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	@SuppressWarnings("unchecked")
	public List<SolicitudInscripcion> consultaSolInscripcionByFolioSIORFCA (String folioSI) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SolicitudInscripcion> lst=null;
		if (folioSI != null && folioSI != ""){
			consulta.append(" where folioSI='").append(folioSI).append("'").append(" or folioCartaAdhesion='").append(folioSI).append("'");
		}
		consulta.insert(0, "From SolicitudInscripcion ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<SolicitudInscripcionV> consultaSolicitudInscripcionV(long idSI) throws JDBCException {
		return consultaSolicitudInscripcionV(idSI, 0,null, null,0);
	}
	public List<SolicitudInscripcionV> consultaSolicitudInscripcionV(String folioCartaAdhesion) throws JDBCException {
		return consultaSolicitudInscripcionV(0, 0,null, folioCartaAdhesion,0);
	}
	@SuppressWarnings("unchecked")
	public List<SolicitudInscripcionV> consultaSolicitudInscripcionV(long idSI, int idPrograma, String folioSI, String folioCartaAdhesion, int idArea) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SolicitudInscripcionV> lst=null;
		if (idSI != 0 && idSI != -1){
			consulta.append("where idSI = ").append(idSI);
		}
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if (folioSI != null && !folioSI.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioSI='").append(folioSI).append("'");
			}else{
				consulta.append("where folioSI='").append(folioSI).append("'");
			}
		}
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}
		}
		
		if (idArea != 0 && idArea!= 0){
			if(consulta.length()>0){
				consulta.append(" and idArea=").append(idArea);
			}else{
				consulta.append("where idArea=").append(idArea);
			}
		}
		consulta.insert(0, "From SolicitudInscripcionV ").append(" order by  folioSI");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<SolicitudInscripcionV> consultaSolicitudInscripcionVBusqueda(long idSI, int idPrograma, String folioSI, String folioCartaAdhesion, int idArea, String comprador) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<SolicitudInscripcionV> lst=null;
		if (idSI != 0 && idSI != -1){
			consulta.append("where idSI = ").append(idSI);
		}
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if (folioSI != null && !folioSI.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioSI='").append(folioSI).append("'");
			}else{
				consulta.append("where folioSI='").append(folioSI).append("'");
			}
		}
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion LIKE '%").append(folioCartaAdhesion).append("%' ");
			}else{
				consulta.append("where folioCartaAdhesion LIKE '%").append(folioCartaAdhesion).append("%' ");
			}
		}
		
		if (idArea != 0 && idArea!= 0){
			if(consulta.length()>0){
				consulta.append(" and idArea=").append(idArea);
			}else{
				consulta.append("where idArea=").append(idArea);
			}
		}
		
		if (comprador != null && !comprador.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and comprador LIKE '%").append(comprador).append("%' ");
			}else{
				consulta.append("where comprador LIKE '%").append(comprador).append("%' ");
			}
		}
		consulta.insert(0, "From SolicitudInscripcionV ").append(" order by  folioSI");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}

	
	//@SuppressWarnings("unchecked")
	/*public List<CartasAdhesion> consultaCartaAdhesion(long idCarta, long consecutivoCarta) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CartasAdhesion> lst=null;
		if (idCarta != 0 && idCarta != -1){
			consulta.append("where idCarta = ").append(idCarta);
		}
		if (consecutivoCarta != 0 && consecutivoCarta != -1){
			if(consulta.length()>0){
				consulta.append(" and consecutivoCarta=").append(consecutivoCarta);
			}else{
				consulta.append("where consecutivoCarta=").append(consecutivoCarta);
			}
		}
		consulta.insert(0, "From CuotasEsquemaV ").append(" order by estado, cultivo");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}*/
	
	
	
	
	/*Recupera el consecutivo */
	@SuppressWarnings("unchecked")
	public Integer recuperaMaxConsecutivoCartaAdhesion(String acronimoCA,  int idCultivo, int idCicloPrograma) throws JDBCException {
		int max=0; 

		List<Integer> lst=null;
		  lst = session.createQuery("select coalesce(max(s.consecutivoCarta),0)+1 "+
												"from SolicitudInscripcionV s"+
												" where s.acronimoCA = '"+acronimoCA+"'"+
												" and s.idCultivo = "+idCultivo+
												" and s.idCicloPrograma="+idCicloPrograma).list();
						
		  if(lst!=null && lst.size()>0){
			  max = lst.get(0);
		  }
		  
		return max;
	}
	
	
	/*public List<CartasAdhesionV> consultaCartaAdhesionV(long folioSolicitud) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CartasAdhesionV> lst=null;
		if (folioSolicitud != 0 && folioSolicitud != -1){
			consulta.append("where folioSolicitud = ").append(folioSolicitud);
		}
		
		consulta.insert(0, " from CartasAdhesionV c ");
		lst= (List<CartasAdhesionV>)session.createQuery(consulta.toString()).list();	
		return lst;
	}*/
	
	public BigDecimal recuperaVolumenAutorizadoByPrograma(int idPrograma){
		BigDecimal volumen = null;
		try{
			
			String query = "select COALESCE(sum(volumen), 0) AS volumen from asignacion_cartas_adhesion_v where id_programa="+idPrograma;
			volumen = (BigDecimal)session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			
		}	
		
		return volumen;
	}
	
	public BigDecimal recuperaImporteAutorizadoByPrograma(int idPrograma){
		BigDecimal importe = null;
		try{
			
			String query = "select COALESCE(sum(importe), 0) AS importe from asignacion_cartas_adhesion_v where id_programa="+idPrograma;
			importe = (BigDecimal)session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}	
		
		return importe;
	}
	
	public BigDecimal recuperaVolumenAutorizadoBySolicitud(String folioCartaAdhesion){
		BigDecimal volumen = null;
		try{
			
			String query = "select COALESCE(sum(volumen), 0) AS volumen from asignacion_cartas_adhesion_v where folio_carta_adhesion='"+folioCartaAdhesion+"'";
			volumen = (BigDecimal)session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}	
		
		return volumen;
	}
	public BigDecimal recuperaImporteAutorizadoBySolicitud(String folioCartaAdhesion){
		BigDecimal importe = null;
		try{
			
			String query = "select COALESCE(sum(importe), 0) AS importe from asignacion_cartas_adhesion_v where folio_carta_adhesion='"+folioCartaAdhesion+"'";
			importe = (BigDecimal)session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}	
		
		return importe;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<CicloPrograma> consultaCicloProgramas(int idPrograma)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CicloPrograma> lst = null;
		if(idPrograma!=0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		consulta.insert(0, "From CicloPrograma ");
		lst= session.createQuery(consulta.toString()).list();

		return lst;
		
	}
	
	public List<CiclosProgramasV> consultaCicloProgramasV(int idPrograma)throws JDBCException {
		return consultaCicloProgramasV(idPrograma, 0);
	}
	@SuppressWarnings("unchecked")
	public List<CiclosProgramasV> consultaCicloProgramasV(int idPrograma, int idCicloPrograma)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CiclosProgramasV> lst = null;
		if(idPrograma!=0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		if(idCicloPrograma!=0 && idCicloPrograma!=-1){
			if (consulta.length() > 0){
				consulta.append(" and idCicloPrograma =").append(idCicloPrograma);
			}else{
				consulta.append("where idCicloPrograma=").append(idCicloPrograma);
			}
		}
		consulta.insert(0, "From CiclosProgramasV ");
		lst= session.createQuery(consulta.toString()).list();

		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramaEstado> consultaProgramaEstado(int idPrograma)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ProgramaEstado> lst = null;
		if(idPrograma!=0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		consulta.insert(0, "From ProgramaEstado ");
		lst= session.createQuery(consulta.toString()).list();

		return lst;
		
	}
	
	public int borrarCiclosByProgramas(int idPrograma)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM ciclos_programas WHERE id_programa= ").append(idPrograma);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}	
	
	public int borrarEstadosByProgramas(int idPrograma)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM programas_estados WHERE id_programa= ").append(idPrograma);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}	
	
	public int borrarCuotasEsquemaByInicializacion(Long id)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM cuotas_esquema WHERE id_inicializacion_esquema= ").append(id);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	public int borrarCultivoVariedadEsquemaByPrograma(int id)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM cultivo_variedad_esquema WHERE id_programa= ").append(id);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	public int borrarEtapaIniEsquema(int id)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM etapa_ini_esquema WHERE id_programa= ").append(id);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}

	public int borrarProgramaComprador(int idPrograma, int idComprador, String carta)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM programa_comprador WHERE id_programa= ").append(idPrograma)
			.append(" and id_comprador=").append(idComprador).append(" and ").append(" no_carta=").append(carta);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}

	
	 public void borrarObjeto(Object o)throws JDBCException{
	    
	       	session.delete(o);
	        session.flush();
	       
	   }
	 
	 @SuppressWarnings("unchecked")
	public List<ProgramaCultivo> recuperaCultivoByPrograma(int idPrograma, int idCultivo)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 List<ProgramaCultivo> lista = null;
		 try{  		
			 
			 if(idPrograma!=0 && idPrograma != -1){
					consulta.append("where id_programa = ").append(idPrograma);
			 }
			 if (idCultivo != 0 && idCultivo != 1){
					if(consulta.length()>0){
						consulta.append(" and id_cultivo =").append(idCultivo);
					}else{
						consulta.append(" where id_cultivo =").append(idCultivo);
					}	
			 }			 
			 consulta.insert(0, "select id_programa, id_cultivo, cultivo from cuotas_esquemas_v  ").append(" group by 1,2,3");
			 /*
			 String query = "select id_programa, id_cultivo, cultivo " +
		   				  "from cuotas_esquemas_v " +
		   				  "where id_programa="+idPrograma+" group by 1,2,3";
		   */
			   lista = session.createSQLQuery(consulta.toString()).addEntity(ProgramaCultivo.class).list();
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el cultivo por programa debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }
	 
	 @SuppressWarnings("unchecked")
	public List<AsignacionCartasAdhesionV> consultaAsignacionCartasAdhesionV(long idAsiganacionCA, String folioCartaAdhesion) throws JDBCException {
			StringBuilder consulta= new StringBuilder();
			List<AsignacionCartasAdhesionV> lst=null;
			if (idAsiganacionCA != 0 && idAsiganacionCA != -1){
				consulta.append("where idAsiganacionCA = ").append(idAsiganacionCA);
			}
			if (folioCartaAdhesion != null && folioCartaAdhesion != ""){
				if(consulta.length()>0){
					consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
				}else{
					consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
				}
			}
			consulta.insert(0, "From AsignacionCartasAdhesionV ").append(" order by cultivo, estado, variedad ");
			lst= session.createQuery(consulta.toString()).list();	
			return lst;
		
	 }
	 @SuppressWarnings("unchecked")
	public List<HcoProgramasV> consultaHcoProgramasV(long idPrograma) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<HcoProgramasV> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		consulta.insert(0, "From HcoProgramasV ").append(" order by fechaPublicacionDof");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	 }

		@SuppressWarnings("unchecked")
	public List<ExpedienteAuditor> consultaExpedienteAuditor(long idExpedienteAuditor, String numeroAuditor, int idExpediente) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ExpedienteAuditor> lst=null;
		if (idExpedienteAuditor != 0 && idExpedienteAuditor != -1){
			consulta.append("where idExpedienteAuditor = ").append(idExpedienteAuditor);
		}
		if (numeroAuditor != null && !numeroAuditor.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and numeroAuditor LIKE '%").append(numeroAuditor).append("%' ");
			} else {
				consulta.append(" where numeroAuditor LIKE '%").append(numeroAuditor.toUpperCase())
						.append("%' ");
			}
		}
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}
		consulta.insert(0, "From ExpedienteAuditor ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
		
	public List<EtapaIniEsquemaV> consultaEtapaIniEsquemaV(long idEtapaIniEsquema) throws JDBCException {
		return consultaEtapaIniEsquemaV(idEtapaIniEsquema, 0);
	}
	
	@SuppressWarnings("unchecked")
	public List<EtapaIniEsquemaV> consultaEtapaIniEsquemaV(long idEtapaIniEsquema, int idPrograma) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EtapaIniEsquemaV> lst=null;
		if (idEtapaIniEsquema != 0 && idEtapaIniEsquema != -1){
			consulta.append("where idEtapaIniEsquema = ").append(idEtapaIniEsquema);
		}
		
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		
		consulta.insert(0, "From EtapaIniEsquemaV ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	 }

	public List<CultivoVariedadEsquema> consultaCultivoVariedadEsquema(int idPrograma) throws JDBCException {
		return consultaCultivoVariedadEsquema(idPrograma, 0, 0);
	}
	@SuppressWarnings("unchecked")
	public List<CultivoVariedadEsquema> consultaCultivoVariedadEsquema(int idPrograma, int idCultivo, int idVariedad) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CultivoVariedadEsquema> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo=").append(idCultivo);
			}else{
				consulta.append("where idCultivo=").append(idCultivo);
			}
		}
		
		if (idVariedad != 0 && idVariedad != -1){
			if(consulta.length()>0){
				consulta.append(" and idVariedad=").append(idVariedad);
			}else{
				consulta.append("where idVariedad=").append(idVariedad);
			}
		}
		
				
		consulta.insert(0, "From CultivoVariedadEsquema ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	 }

	
	@SuppressWarnings("unchecked")
	public List<Estado> recuperaEstadoByInicilizacionEsquema(long idInicializacionEsquema)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 List<Estado> lista = null;
		 try{  		
			 consulta.insert(0, "select * from estados where id_estado in " +
				  		"(select distinct id_estado from cuotas_esquema where id_inicializacion_esquema = ").append(idInicializacionEsquema).append(")")
				  		.append(" order by nombre ");
				 lista = session.createSQLQuery(consulta.toString()).addEntity(Estado.class).list(); 
			  		System.out.println("lista "+lista.get(0));
			 
			 //lista= session.createSQLQuery(consulta.toString()).list();
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de estado debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }
	
	/*@SuppressWarnings("unchecked")
	public List<Cultivo> recuperaCultivoByInicilizacionEsquemaE(long idInicializacionEsquema)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 List<Cultivo> lista = null;
		 try{  		
			 consulta.insert(0, "select * from cultivo where id_cultivo in " +
				  		"(select distinct id_cultivo from cuotas_esquema where id_inicializacion_esquema = ").append(idInicializacionEsquema).append(")")
				  		.append(" order by cultivo ");
				 lista = session.createSQLQuery(consulta.toString()).addEntity(Cultivo.class).list(); 
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de cultivo debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }*/
	@SuppressWarnings("unchecked")
	public List<Cultivo> recuperaCultivoByVolumenXCultivoVariedad(long idPrograma)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 StringBuilder subConsulta= new StringBuilder(); 
		 List<Cultivo> lista = null;
		 try{  
			 
			 if (idPrograma != 0 && idPrograma != -1){
				 subConsulta.append("where id_programa = ").append(idPrograma);	
			 }
			
			subConsulta.insert(0, "select distinct id_cultivo from cultivo_variedad_esquema ");
			consulta.insert(0, "select * from cultivo where id_cultivo in (").append(subConsulta.toString()).append(") order by cultivo");
			lista = session.createSQLQuery(consulta.toString()).addEntity(Cultivo.class).list(); 
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de cultivo debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }
	
	
	/*@SuppressWarnings("unchecked")
	public List<Cultivo> recuperaCultivoByInicilizacionEsquemaE(long idInicializacionEsquema)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 List<Cultivo> lista = null;
		 try{  		
			 consulta.insert(0, "select * from cultivo where id_cultivo in " +
				  		"(select distinct id_cultivo from cuotas_esquema where id_inicializacion_esquema = ").append(idInicializacionEsquema).append(")")
				  		.append(" order by cultivo ");
				 lista = session.createSQLQuery(consulta.toString()).addEntity(Cultivo.class).list(); 
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de cultivo debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }*/
	@SuppressWarnings("unchecked")
	public List<Variedad> recuperaVariedadByVolumenXCultivoVariedad(long idPrograma)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 StringBuilder subConsulta= new StringBuilder(); 
		 List<Variedad> lista = null;
		 try{  			 
			 if(idPrograma != 0 && idPrograma != -1){
				 subConsulta.append("where id_programa = ").append(idPrograma);	
			 }			
			subConsulta.insert(0, "select distinct id_variedad from cultivo_variedad_esquema ");
			consulta.insert(0, "select * from variedad where id_variedad in (").append(subConsulta.toString()).append(") order by variedad");
			lista = session.createSQLQuery(consulta.toString()).addEntity(Variedad.class).list(); 
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de variedad debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }
	
	
	@SuppressWarnings("unchecked")
	public List<Cultivo> recuperaCultivoByInicilizacionEsquema(long idInicializacionEsquema, int idEstado)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 StringBuilder subConsulta= new StringBuilder(); 
		 List<Cultivo> lista = null;
		 try{  
			 
			 if (idInicializacionEsquema != 0 && idInicializacionEsquema != -1){
				 subConsulta.append("where id_inicializacion_esquema = ").append(idInicializacionEsquema);
				}
			if (idEstado != 0 && idEstado != -1){
				if(subConsulta.length()>0){
					subConsulta.append(" and id_estado =").append(idEstado);
				}else{
					subConsulta.append("where id_estado=").append(idEstado);
				}
			}
			subConsulta.insert(0, "select distinct id_cultivo from cuotas_esquema ");
			consulta.insert(0, "select * from cultivo where id_cultivo in (").append(subConsulta.toString()).append(") order by cultivo");
			System.out.println("Query --- > "+consulta.toString());
			lista = session.createSQLQuery(consulta.toString()).addEntity(Cultivo.class).list(); 
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de cultivo debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }
	
	@SuppressWarnings("unchecked")
	public List<Variedad> recuperaVariedadByInicilizacionEsquema(long idInicializacionEsquema, int idCultivo, int idEstado)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 StringBuilder subConsulta= new StringBuilder(); 
		 List<Variedad> lista = null;
		 try{  		
			if (idInicializacionEsquema != 0 && idInicializacionEsquema != -1){
				 subConsulta.append("where id_inicializacion_esquema = ").append(idInicializacionEsquema);
				}
			if (idCultivo != 0 && idCultivo != -1){
				if(subConsulta.length()>0){
					subConsulta.append(" and id_cultivo =").append(idCultivo);
				}else{
					subConsulta.append("where id_cultivo=").append(idCultivo);
				}
			}
		 
			if (idEstado != 0 && idEstado != -1){
				if(subConsulta.length()>0){
					subConsulta.append(" and id_estado =").append(idEstado);
				}else{
					subConsulta.append("where id_estado =").append(idEstado);
				}
			}
		 
			subConsulta.insert(0, "select distinct id_variedad from cuotas_esquema ");
			consulta.append("select * from variedad where id_variedad in (").append(subConsulta.toString())
			  	 .append(") order by variedad ");
			System.out.println("Consulta-->"+ consulta.toString());			
			lista = session.createSQLQuery(consulta.toString()).addEntity(Variedad.class).list(); 
			  		
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de estado debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			
			return lista;
	   }
	
	public List<CartasAdhesionV> consultaCartasV(String carta) throws JDBCException {
		return consultaCartasV(carta,0,0,0,null);
	}
	
	public List<CartasAdhesionV> consultaCartasV(String carta, int idPrograma) throws JDBCException {
		return consultaCartasV(carta,idPrograma,0,0,null);
	}
	
	public List<CartasAdhesionV> consultaCartasV(String carta, int idPrograma, int idComprador) throws JDBCException {
		return consultaCartasV(carta,idPrograma,idComprador,0,null);
	}

	@SuppressWarnings("unchecked")
	public List<CartasAdhesionV> consultaCartasV(String carta, int idPrograma, int idComprador, int estatus, String oficioEntrega) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CartasAdhesionV> lst=null;
		
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}

		if (idComprador != 0 && idComprador != -1){
			if(consulta.length()>0){
				consulta.append(" and idComprador=").append(idComprador);
			}else{
				consulta.append("where idComprador = ").append(idComprador);
			}			
		}
		
		if (estatus != 0 && estatus != -1){
			if(consulta.length()>0){
				consulta.append(" and estatus=").append(estatus);
			}else{
				consulta.append("where estatus=").append(estatus);
			}
		}
		
		if(carta != null && !carta.equals("")){
			if(consulta.length()>0){
				consulta.append(" and carta='").append(carta).append("'");
			}else{
				consulta.append("where carta='").append(carta).append("'");
			}			
		}

		if(oficioEntrega != null && !oficioEntrega.equals("")){
			if(consulta.length()>0){
				consulta.append(" and oficioEntrega='").append(oficioEntrega).append("'");
			}else{
				consulta.append("where oficioEntrega='").append(oficioEntrega).append("'");
			}			
		}

		consulta.insert(0, "From CartasAdhesionV ").append(" ORDER BY programa, carta ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}	

	public int actualizaCartasPorOficio(String cartas, String oficio, String nombreOficio, String fechaOficioEntrega) throws Exception{
		int updatedEntities = 0;
		try{
			StringBuilder hql = new StringBuilder();
			hql.append("update CartaAdhesion set oficioEntrega='").append(oficio).append("'")
				.append(", nombreOficioEntrega='").append(nombreOficio).append("'")
				.append(", fechaEntrega= '").append(fechaOficioEntrega).append("'")
				.append(", estatus=7")
				.append(" where folioCartaAdhesion in (").append(cartas).append(")");
			updatedEntities = session.createQuery(hql.toString()).executeUpdate();
		}catch (Exception e){
			transaction.rollback();
			throw e; 
		}
		return updatedEntities;
	}
	
	 @SuppressWarnings("unchecked")
	public List<AlcanceSolicitudInscripcion> consultaAlcanceSI(long idSI)throws JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<AlcanceSolicitudInscripcion> lista = null;
		try{
			if(idSI != 0 && idSI != -1){
				consulta.append("where idSI = ").append(idSI);
			}					
			consulta.insert(0, "From AlcanceSolicitudInscripcion ").append(" order by fechaDocAlcance");
			lista = session.createQuery(consulta.toString()).list();	
		}catch (Exception e){
			AppLogger.error("errores","Ocurrio un error al recuperar el alcance de la solicitud de inscripcion :"+e.getMessage());
			transaction.rollback();
			e.printStackTrace();
		}
		
		return lista;
	}
	
	 public List<AsignacionCartasAdhesion> consultaAsignacionCartasAdhesion(int idEstado, int idCultivo, int idVariedad, String folioCartaAdhesion) throws JDBCException {
		 return consultaAsignacionCartasAdhesion(null, idEstado, idCultivo, idVariedad, folioCartaAdhesion, 0); 
	 }
	 public List<AsignacionCartasAdhesion> consultaAsignacionCartasAdhesion(int idEstado, int idCultivo, int idVariedad, String folioCartaAdhesion, double cuota) throws JDBCException {
		 return consultaAsignacionCartasAdhesion(null, idEstado, idCultivo, idVariedad, folioCartaAdhesion, cuota); 
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	public List<AsignacionCartasAdhesion> consultaAsignacionCartasAdhesion(String claveBodega, int idEstado, int idCultivo, int idVariedad, String folioCartaAdhesion, double cuota) throws JDBCException {
			StringBuilder consulta= new StringBuilder();
			List<AsignacionCartasAdhesion> lst=null;
			if (idEstado != 0 && idEstado != -1){
				consulta.append("where idEstado = ").append(idEstado);
			}
			
			if (claveBodega != null && claveBodega != ""){
				if(consulta.length()>0){
					consulta.append(" and claveBodega='").append(claveBodega).append("'");
				}else{
					consulta.append("where claveBodega='").append(claveBodega).append("'");
				}
			}
			if (idCultivo != 0 && idCultivo != -1){
				if(consulta.length()>0){
					consulta.append(" and idCultivo=").append(idCultivo);
				}else{
					consulta.append("where idCultivo='").append(idCultivo);
				}
			}
			if (idVariedad != 0 && idVariedad != -1){
				if(consulta.length()>0){
					consulta.append(" and idVariedad=").append(idVariedad);
				}else{
					consulta.append("where idVariedad='").append(idVariedad);
				}
			}
			if (folioCartaAdhesion != null && folioCartaAdhesion != ""){
				if(consulta.length()>0){
					consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
				}else{
					consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
				}
			}
			if(cuota!=0){
				if(consulta.length()>0){
					consulta.append(" and cuota=").append(cuota).append("");
				}else{
					consulta.append("where cuota=").append(cuota).append("");
				}
			}
			consulta.insert(0, "From AsignacionCartasAdhesion ");
			System.out.println("consulta  ------------->>"+consulta.toString());
			lst = session.createQuery(consulta.toString()).list();	
			return lst;
		
	}
	 
	 
	 
	 public List<DetalleAsignacionCartasAdhesion> consultaDetalleAsignacionCartasAdhesion(String folioCartaAdhesion, int tipo) throws JDBCException {
			return consultaDetalleAsignacionCartasAdhesion(0,0,0,folioCartaAdhesion, tipo);
		}
		
		@SuppressWarnings("unchecked")
	public List<DetalleAsignacionCartasAdhesion> consultaDetalleAsignacionCartasAdhesion(int idEstado, int idCultivo, int idVariedad, String folioCartaAdhesion, int tipo) throws JDBCException {
			StringBuilder consulta= new StringBuilder();
			List<DetalleAsignacionCartasAdhesion> lst=null;
			if (idEstado != 0 && idEstado != -1){
				consulta.append("where idEstado = ").append(idEstado);
			}
			if (idCultivo != 0 && idCultivo != -1){
				if(consulta.length()>0){
					consulta.append(" and idCultivo=").append(idCultivo);
				}else{
					consulta.append("where idCultivo='").append(idCultivo);
				}
			}
			if (idVariedad != 0 && idVariedad != -1){
				if(consulta.length()>0){
					consulta.append(" and idVariedad=").append(idVariedad);
				}else{
					consulta.append("where idVariedad='").append(idVariedad);
				}
			}
			if (folioCartaAdhesion != null && folioCartaAdhesion != ""){
				if(consulta.length()>0){
					consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
				}else{
					consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
				}
			}
			
			if (tipo!= 0 && tipo != -1){
				if(consulta.length()>0){
					consulta.append(" and tipo=").append(tipo);
				}else{
					consulta.append("where tipo=").append(tipo);
				}
			}
			consulta.insert(0, "From DetalleAsignacionCartasAdhesion ");
			lst= session.createQuery(consulta.toString()).list();	
			return lst;
		
	 }
	
		
		@SuppressWarnings("unchecked")
	public List<Variedad> recuperaVariedadByInicilizacionEsquema(long idInicializacionEsquema, int idCultivo)throws JDBCException{
			 StringBuilder consulta= new StringBuilder();
			 StringBuilder subConsulta= new StringBuilder(); 
			 List<Variedad> lista = null;
			 try{  		
				if (idInicializacionEsquema != 0 && idInicializacionEsquema != -1){
					 subConsulta.append("where id_inicializacion_esquema = ").append(idInicializacionEsquema);
					}
				if (idCultivo != 0 && idCultivo != -1){
					if(subConsulta.length()>0){
						subConsulta.append(" and id_cultivo =").append(idCultivo);
					}else{
						subConsulta.append("where id_cultivo=").append(idCultivo);
					}
				}
			 
				subConsulta.insert(0, "select distinct id_variedad from cuotas_esquema ");
				consulta.append("select * from variedad where id_variedad in (").append(subConsulta.toString())
				  	 .append(") order by variedad ");
				System.out.println("Consulta-->"+ consulta.toString());			
				lista = session.createSQLQuery(consulta.toString()).addEntity(Variedad.class).list(); 
				  		
				}catch (Exception e){
					AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de estado debido a:"+e.getMessage());
					transaction.rollback();
					e.printStackTrace();
				}
				
				return lista;
		   }
		
		
		@SuppressWarnings("unchecked")
		public List<Cultivo> recuperaCultivoByInicilizacionEsquema(long idInicializacionEsquema)throws JDBCException{
			 StringBuilder consulta= new StringBuilder();
			 List<Cultivo> lista = null;
			 try{  		
				 consulta.insert(0, "select * from cultivo where id_cultivo in " +
					  		"(select distinct id_cultivo from cuotas_esquema where id_inicializacion_esquema = ").append(idInicializacionEsquema).append(")")
					  		.append(" order by cultivo ");
					 lista = session.createSQLQuery(consulta.toString()).addEntity(Cultivo.class).list(); 
				}catch (Exception e){
					AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de cultivo debido a:"+e.getMessage());
					transaction.rollback();
					e.printStackTrace();
				}
				
				return lista;
		   }
		
	
	public Double getSumaVolumenAsignacionCA(String folioCartaAdhesion, int tipo) throws Exception{
		BigDecimal volumen = null;
		double volumenD=0;
		try{
			
			
			String query = "select COALESCE(sum(volumen), 0) AS volumen from detalle_asignaciones where folio_carta_adhesion='"+folioCartaAdhesion+"' and tipo="+tipo;
			volumen = (BigDecimal)session.createSQLQuery(query).list().get(0);
			
			if(volumen!=null){
				volumenD = volumen.doubleValue();
			}
			
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		
		return volumenD;
	}
	
	
		
	
}//end class
