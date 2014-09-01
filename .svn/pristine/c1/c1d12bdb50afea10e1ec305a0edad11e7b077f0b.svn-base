package mx.gob.comer.sipc.dao;


import java.util.List;

import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Expediente;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.catalogos.Variedad;
import mx.gob.comer.sipc.domain.transaccionales.AsignacionCartaEspecialista;
import mx.gob.comer.sipc.domain.transaccionales.AsignacionCartasAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.AuditorSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.CertificadosDeposito;
import mx.gob.comer.sipc.domain.transaccionales.ConstanciasAlmacenamiento;
import mx.gob.comer.sipc.domain.transaccionales.DocumentacionSPCartaAdhesion;
import mx.gob.comer.sipc.domain.transaccionales.EtapaIniEsquema;
import mx.gob.comer.sipc.domain.transaccionales.ObservacionDocumentacionSP;
import mx.gob.comer.sipc.domain.transaccionales.OficioCartaAdhesionSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.OficioObsSolicitudPago;
import mx.gob.comer.sipc.domain.transaccionales.OficioRespuestaSolicitudPago;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.AsignacionCAaEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.AsignacionCartasAdhesionEspecialistaV;
import mx.gob.comer.sipc.vistas.domain.AuditorSolicitudPagoV;
import mx.gob.comer.sipc.vistas.domain.DocumentacionSPCartaAdhesionV;
import mx.gob.comer.sipc.vistas.domain.ExpedientesProgramasV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.PrgEspecialistaNumCartasV;
import mx.gob.comer.sipc.vistas.domain.ProgramaNumCartasV;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class SolicitudPagoDAO {

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	
	@SuppressWarnings("unchecked")
	public List<ExpedientesProgramasV> consultaExpedientesProgramasOpcionalV(long idExpedientePrograma, int idPrograma, int idExpediente, boolean documentacionOpcional) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ExpedientesProgramasV> lst=null;
		if (idExpedientePrograma != 0 && idExpedientePrograma != -1){
			consulta.append("where idExpedientePrograma = ").append(idExpedientePrograma);
		}
		
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}
		
		if (documentacionOpcional != false){
			if(consulta.length()>0){
				consulta.append(" and documentacionOpcional=").append(documentacionOpcional);
			}else{
				consulta.append("where documentacionOpcional=").append(documentacionOpcional);
			}
		}
		consulta.insert(0, "From ExpedientesProgramasV ").append(" order by ").append(" prioridadExpediente");
		
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<ExpedientesProgramasV> consultaExpedientesProgramasV(long idExpedientePrograma, int idPrograma, String tipo) throws JDBCException {
		return consultaExpedientesProgramasV(idExpedientePrograma, idPrograma, tipo, null);
	}
	@SuppressWarnings("unchecked")
	public List<ExpedientesProgramasV> consultaExpedientesProgramasV(long idExpedientePrograma, int idPrograma, String tipo, Boolean opcional) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ExpedientesProgramasV> lst=null;
		if (idExpedientePrograma != 0 && idExpedientePrograma != -1){
			consulta.append("where idExpedientePrograma = ").append(idExpedientePrograma);
		}
		
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if(tipo!=null && !tipo.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and tipo in (").append(tipo).append(")");
			}else{
				consulta.append(" where tipo in (").append(tipo).append(")");
			}
		}		
		
		if(opcional!=null){
			if(consulta.length()>0){
				consulta.append(" and opcional=").append(opcional);		
			}else{
				consulta.append(" where opcional=").append(opcional);
			}			
		}
		consulta.insert(0, "From ExpedientesProgramasV ").append(" order by ").append(" prioridadExpediente");
		
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<Programa> consultaProgramasParaSolPago() throws JDBCException {
		return consultaProgramasParaSolPago(0);
	}
	/**Este metodo recupera los programas que estan listos para solicitud de pagos en la tabla de carta_adhesion
	 * 
	 * @return
	 * @throws JDBCException
	 */
	@SuppressWarnings("unchecked")
	public List<Programa> consultaProgramasParaSolPago(int estatus) throws JDBCException {
		List<Programa> lst=null;
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		
		if (estatus != 0 && estatus != -1){
			subConsulta.append(" where estatus_ca = ").append(estatus);
		}
		subConsulta.insert(0, " select distinct id_programa from carta_adhesion ");
		consulta.append("select * from programas where id_programa in (").append(subConsulta.toString()).append(")");
		lst = session.createSQLQuery(consulta.toString()).addEntity(Programa.class).list();
		return lst;
	}
	/**Recupera los programas que ya tienen configurado los expedientes 
	 * @return
	 * @throws JDBCException
	 */	
	@SuppressWarnings("unchecked")
	public List<Programa> consultaProgramasByConfiguracionExpediente() throws JDBCException {
		List<Programa> lst=null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("select * from programas where id_programa in (select distinct id_programa from expedientes_programas)");
		lst = session.createSQLQuery(consulta.toString()).addEntity(Programa.class).list();	
		
		return lst;
	}
	
	public int borrarExpedientesPrograma(int idPrograma)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM expedientes_programas WHERE id_programa= ").append(idPrograma);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}	

	/**Recupera las asignaciones de carta de adhesion a especialistas 
	 * @return
	 * @throws JDBCException
	 */

	@SuppressWarnings("unchecked")
	public List<AsignacionCartaEspecialista> consultaAsignacionCAEspecialista(long idOficioCASP, String folioCartaAdhesion, int idEspecialista) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AsignacionCartaEspecialista> lst=null;
		if (idOficioCASP != 0 && idOficioCASP != -1){
			consulta.append("where idOficioCASP = ").append(idOficioCASP);
		}
		
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}
		}	
		
		if (idEspecialista != 0 && idEspecialista != -1){
			if(consulta.length()>0){
				consulta.append(" and idEspecialista=").append(idEspecialista);
			}else{
				consulta.append("where idEspecialista=").append(idEspecialista);
			}
		}
		
		consulta.insert(0, "From AsignacionCartaEspecialista ").append(" ORDER BY folioCartaAdhesion ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<CartaAdhesion> consultaCartaAdhesion(String folioCartaAdhesion) throws JDBCException {
		return consultaCartaAdhesion(0,-1, folioCartaAdhesion);
	}
	
	/**Recupera las asignaciones de carta de adhesion a especialistas 
	 * @return
	 * @throws JDBCException
	 */
	@SuppressWarnings("unchecked")
	public List<CartaAdhesion> consultaCartaAdhesion(int idPrograma, int estatus, String folioCartaAdhesion) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CartaAdhesion> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
				
		if (estatus != 0 && estatus != -1){
			if(consulta.length()>0){
				consulta.append(" and estatus=").append(estatus);
			}else{
				consulta.append("where estatus=").append(estatus);
			}
		}
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}
		}	
		
		consulta.insert(0, "From CartaAdhesion ").append(" ORDER BY folioCartaAdhesion ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	

	@SuppressWarnings("unchecked")
	public List<OficioCartaAdhesionSolicitudPago> consultaOficioCartaAdhesionSolicitudPago(long idOficioCASP) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<OficioCartaAdhesionSolicitudPago> lst=null;
		if (idOficioCASP != 0 && idOficioCASP != -1){
			consulta.append("where idOficioCASP = ").append(idOficioCASP);
		}
			
		consulta.insert(0, "From OficioCartaAdhesionSolicitudPago ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<AsignacionCAaEspecialistaV> consultaCAaEspecialistaV(long idOficioCASP) throws JDBCException {
		
		return consultaCAaEspecialistaV(null, 0, null, 0, 0, false, idOficioCASP,null);
	}
	public List<AsignacionCAaEspecialistaV> consultaCAaEspecialistaV(int idEspecialista, int idPrograma) throws JDBCException {
		
		return consultaCAaEspecialistaV(null, idEspecialista, null, idPrograma, 0, false, 0, null);
	}
	
	public List<AsignacionCAaEspecialistaV> consultaCAaEspecialistaV(int idEspecialista, int idPrograma, boolean clabe) throws JDBCException {
		
		return consultaCAaEspecialistaV(null, idEspecialista, null, idPrograma, 0, clabe, 0, null);
	}
	public List<AsignacionCAaEspecialistaV> consultaCAaEspecialistaV(String folioCartaAdhesion) throws JDBCException {
		
		return consultaCAaEspecialistaV(null, 0, folioCartaAdhesion, 0, 0, false, 0,null);
	}
	public List<AsignacionCAaEspecialistaV> consultaCAaEspecialistaV(String noOficioCA, int idEspecialista, String folioCartaAdhesion) throws JDBCException {
		return consultaCAaEspecialistaV(noOficioCA, idEspecialista, folioCartaAdhesion, 0,0, false, 0, null);
	}
	@SuppressWarnings("unchecked")
	public List<AsignacionCAaEspecialistaV> consultaCAaEspecialistaV(String noOficioCA, int idEspecialista, String folioCartaAdhesion, int idPrograma, int estatusCA, boolean clabe,  long idOficioCASP, String nombreComprador) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AsignacionCAaEspecialistaV> lst=null;
		if (noOficioCA != null && !noOficioCA.isEmpty()){
			consulta.append("where noOficioCA = '").append(noOficioCA).append("'");
		}				
		if (idEspecialista != 0 && idEspecialista != -1){
			if(consulta.length()>0){
				consulta.append(" and idEspecialista=").append(idEspecialista);
			}else{
				consulta.append("where idEspecialista=").append(idEspecialista);
			}
		}
		if (idPrograma != 0 && idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma=").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}
		}	
		
		if(estatusCA != 0 && estatusCA != -1){
			if(consulta.length()>0){
				consulta.append(" and estatusCA=").append(estatusCA);
			}else{
				consulta.append("where estatusCA=").append(estatusCA);
			}
		}

		if(clabe){
			if(consulta.length()>0){
				consulta.append(" and clabe is not null");
			}else{
				consulta.append("where clabe is not null");
			}
		}
		
		if (idOficioCASP != 0 && idOficioCASP != -1){
			if(consulta.length()>0){
				consulta.append(" and idOficioCASP=").append(idOficioCASP);
			}else{
				consulta.append("where idOficioCASP=").append(idOficioCASP);
			}
		}
		
		if (nombreComprador != null && !nombreComprador.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and nombreComprador LIKE '%").append(nombreComprador).append("%' ");
			} else {
				consulta.append(" where nombreComprador LIKE '%").append(nombreComprador.toUpperCase()).append("%' ");
			}
		}
		consulta.insert(0, "From AsignacionCAaEspecialistaV ").append(" ORDER BY folioCartaAdhesion ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Programa> consultaProgramasAsignadosAEspecialista(int idEspecialista, boolean clabe) throws JDBCException {
		List<Programa> lst=null;
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		
		if (idEspecialista != 0 && idEspecialista != -1){
			subConsulta.append(" where id_especialista = ").append(idEspecialista);
		}
		
		if (clabe){
			if(subConsulta.length()> 0){
				subConsulta.append(" and clabe is not null");
			} else {
				subConsulta.append(" where clabe is not null");
			}			
		}
		
		subConsulta.insert(0, " select distinct id_programa from asignacion_ca_a_especialista_v ");
		consulta.append("select * from programas where id_programa in (").append(subConsulta.toString()).append(")");
		lst = session.createSQLQuery(consulta.toString()).addEntity(Programa.class).list();
		return lst;
	}

	public List<DocumentacionSPCartaAdhesion> consultaExpedientesSPCartaAdhesion(Long idExpSPCartaAdhesion) throws JDBCException {
		return consultaExpedientesSPCartaAdhesion(null, 0, idExpSPCartaAdhesion, false);
	}
	
	public List<DocumentacionSPCartaAdhesion> consultaExpedientesSPCartaAdhesion(String folioCartaAdhesion) throws JDBCException {
		return consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, 0, 0, false);
	}
	public List<DocumentacionSPCartaAdhesion> consultaExpedientesSPCartaAdhesion(String folioCartaAdhesion, long idExpediente) throws JDBCException {
		return consultaExpedientesSPCartaAdhesion(folioCartaAdhesion, idExpediente, 0, false);
	}
	/**Recupera los expedientes de la carta de adhesion 
	 * @return
	 * @throws JDBCException
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentacionSPCartaAdhesion> consultaExpedientesSPCartaAdhesion(String folioCartaAdhesion, long idExpediente, long idExpSPCartaAdhesion, boolean observacion) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<DocumentacionSPCartaAdhesion> lst=null;
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
		}
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}
		
		if (idExpSPCartaAdhesion != 0 && idExpSPCartaAdhesion != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpSPCartaAdhesion=").append(idExpSPCartaAdhesion);
			}else{
				consulta.append("where idExpSPCartaAdhesion=").append(idExpSPCartaAdhesion);
			}
		}
		if(observacion){
			if(consulta.length()>0){
				consulta.append(" and observacion = true ");
			}else{
				consulta.append("where and observacion = true ");
			}
			
		}
		consulta.insert(0, "From DocumentacionSPCartaAdhesion ").append(" order by idExpediente");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<DocumentacionSPCartaAdhesionV> consultaExpedientesSPCartaAdhesionV(String folioCartaAdhesion, String tipoExpediente, String orderBy) throws JDBCException {
		return consultaExpedientesSPCartaAdhesionV(folioCartaAdhesion, 0, tipoExpediente, orderBy);
	}
	/**Recupera los expedientes de la carta de adhesion 
	 * @return
	 * @throws JDBCException
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentacionSPCartaAdhesionV> consultaExpedientesSPCartaAdhesionV(String folioCartaAdhesion, long idExpediente, String tipoExpediente, String orderBy) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		StringBuilder in= new StringBuilder();
		List<DocumentacionSPCartaAdhesionV> lst=null;
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
		}
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}
		
		if(tipoExpediente!=null && !tipoExpediente.isEmpty()){
			String [] te = tipoExpediente.split(",");	
			for(int i=0; i<te.length; i++){
				in.append("'").append(te[i]).append("'").append(",");
			}
			in.deleteCharAt(in.length()-1);
			
			if(consulta.length()>0){
				consulta.append(" and tipoExpediente in (").append(in).append(")");
			}else{
				consulta.append(" where tipoExpediente in (").append(in).append(")");
			}
		}
		consulta.insert(0, "From DocumentacionSPCartaAdhesionV ").append(" order by ").append(orderBy);
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<OficioObsSolicitudPago> consultaOficioObsSolicitudPago(String folioCartaAdhesion) throws JDBCException {
		return consultaOficioObsSolicitudPago(folioCartaAdhesion, 0, null); 
	}
	
	public List<OficioObsSolicitudPago> consultaOficioObsSolicitudPago(int idOficioObsSolPago) throws JDBCException {
		return consultaOficioObsSolicitudPago(null, idOficioObsSolPago, null); 
	}
	
	@SuppressWarnings("unchecked")
	public List<OficioObsSolicitudPago> consultaOficioObsSolicitudPago(String folioCartaAdhesion, int idOficioObsSolPago, Boolean alcance) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<OficioObsSolicitudPago> lst=null;
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
		}		
		if(idOficioObsSolPago != 0 && idOficioObsSolPago != -1){
			if(consulta.length()>0){
				consulta.append(" and idOficioObsSolPago=").append(idOficioObsSolPago);
			}else{
				consulta.append("where idOficioObsSolPago=").append(idOficioObsSolPago);
			}
		}
		
		if(alcance != null ){
			if(consulta.length()>0){
				consulta.append(" and alcance=").append(alcance);
			}else{
				consulta.append("where alcance=").append(alcance);
			}
		}
		consulta.insert(0, "From OficioObsSolicitudPago ").append(" order by fechaCreacion");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificarSiHayAlcanceObservacion(String folioCartaAdehsion)throws JDBCException{
		 StringBuilder consulta= new StringBuilder();
		 StringBuilder subConsulta= new StringBuilder();
		 subConsulta.append("select  max(fecha_doc_obs) from oficio_obs_solicitud_pago where folio_carta_adhesion= '").append(folioCartaAdehsion).append("' and no_oficio_resp_obs is null");
		 List<OficioObsSolicitudPago> lista = null;
		 boolean band = false;
		 try{  		
			 consulta.insert(0, "select * from oficio_obs_solicitud_pago where folio_carta_adhesion ='" +folioCartaAdehsion+"' and fecha_doc_obs = ("+subConsulta.toString()+")");
			 lista = session.createSQLQuery(consulta.toString()).addEntity(OficioObsSolicitudPago.class).list(); 	 
			 band = lista!=null && lista.size()>0;			 
			}catch (Exception e){
				AppLogger.error("errores","Ocurrio un error en verificarSiHayAlcanceObservacion debido a:"+e.getMessage());
				transaction.rollback();
				e.printStackTrace();
			}
			return band;
	 }
	
	public List<CertificadosDeposito> consultaCertificadosDeposito(String folioCartaAdhesion)throws JDBCException {
		return consultaCertificadosDeposito(folioCartaAdhesion, 0, null, null);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CertificadosDeposito> consultaCertificadosDeposito(String folioCartaAdhesion, int idAlmacenadora, String claveBodega, String folio)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CertificadosDeposito> lst = null;
		if(folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
		}
		
		if(idAlmacenadora != 0 && idAlmacenadora != -1){
			if(consulta.length()>0){
				consulta.append(" and idAlmacenadora=").append(idAlmacenadora);
			}else{
				consulta.append("where idAlmacenadora=").append(idAlmacenadora);
			}
		}
		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega='").append(claveBodega).append("'");
			}
		}	
		
		if (folio != null && !folio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio='").append(folio).append("'");
			}else{
				consulta.append("where folio='").append(folio).append("'");
			}
		}	
		
		//ggfggg
		consulta.insert(0, "From CertificadosDeposito ").append(" order by ").append(" claveBodega");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public Double getSumaCertificadoDepositoByFolioCA(String folioCartaAdhesion) throws Exception{
		double sumaCD = 0;
		try{
			StringBuilder hql = new StringBuilder().append("select new java.lang.Double(COALESCE (sum(volumen),0))")
					.append("from CertificadosDeposito ").append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			List<Double> lst = (List<Double>) session.createQuery(hql.toString()).list();
			if (lst != null && lst.size() > 0){
				sumaCD = lst.get(0);
			}
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		return sumaCD;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AuditorSolicitudPago> consultaAuditorSolPago(String folioCartaAdhesion, int tipoDocumentacion)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AuditorSolicitudPago> lst = null;
		if(folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
		}
		
		if(tipoDocumentacion != 0 && tipoDocumentacion != -1){
			if(consulta.length()>0){
				consulta.append(" and tipoDocumentacion=").append(tipoDocumentacion);
			}else{
				consulta.append("where tipoDocumentacion=").append(tipoDocumentacion);
			}
		}
		
		consulta.insert(0, "From AuditorSolicitudPago ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	public List<AsignacionCartasAdhesionEspecialistaV> verCartaAdhesionAsignadasPagos(int idEspecialista, String folioCartaAdhesion) throws JDBCException {
		
		return verCartaAdhesionAsignadasPagos(null, idEspecialista, folioCartaAdhesion, 0);
	}
	public List<AsignacionCartasAdhesionEspecialistaV> verCartaAdhesionAsignadasPagos(String folioCartaAdhesion) throws JDBCException {
		
		return verCartaAdhesionAsignadasPagos(null, 0, folioCartaAdhesion, 0);
	}
	@SuppressWarnings("unchecked")
	public List<AsignacionCartasAdhesionEspecialistaV> verCartaAdhesionAsignadasPagos(String noOficioCA, int idEspecialista, String folioCartaAdhesion, long idAsiganacionCA) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AsignacionCartasAdhesionEspecialistaV> lst=null;
		if (noOficioCA != null && !noOficioCA.isEmpty()){
			consulta.append("where noOficioCA = '").append(noOficioCA).append("'");
		}				
		if (idEspecialista != 0 && idEspecialista != -1){
			if(consulta.length()>0){
				consulta.append(" and idEspecialista=").append(idEspecialista);
			}else{
				consulta.append("where idEspecialista=").append(idEspecialista);
			}
		}
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}else{
				consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
			}
		}			
		if (idAsiganacionCA != 0 && idAsiganacionCA != -1){
			if(consulta.length()>0){
				consulta.append(" and idAsiganacionCA=").append(idAsiganacionCA);
			}else{
				consulta.append("where idAsiganacionCA=").append(idAsiganacionCA);
			}
		}

		consulta.insert(0, "From AsignacionCartasAdhesionEspecialistaV ").append(" ORDER BY folioCartaAdhesion, idEstado, idCultivo, idVariedad ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}

	public AsignacionCartasAdhesionEspecialistaV verCartaAdhesionAsignadasPagos(long idAsiganacionCA) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		AsignacionCartasAdhesionEspecialistaV lst=null;
		if (idAsiganacionCA != 0 && idAsiganacionCA != -1){
			consulta.append("where idAsiganacionCA=").append(idAsiganacionCA);
		}

		consulta.insert(0, "From AsignacionCartasAdhesionEspecialistaV ").append(" ORDER BY folioCartaAdhesion, idEstado, idCultivo, idVariedad ");
		lst= (AsignacionCartasAdhesionEspecialistaV)session.createQuery(consulta.toString()).list().get(0);	
		return lst;
	}

	/**Recupera las asignaciones de montos por etapa y esquema 
	 * @return
	 * @throws JDBCException
	 */
	@SuppressWarnings("unchecked")
	public List<EtapaIniEsquema> consultaEtapasIniEsquema(int idPrograma, Integer idEtapa) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EtapaIniEsquema> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
				
		if (idEtapa != 0 && idEtapa != -1){
			if(consulta.length()>0){
				consulta.append(" and idEtapa=").append(idEtapa);
			}else{
				consulta.append("where idEtapa=").append(idEtapa);
			}
		}
		
		consulta.insert(0, "From EtapaIniEsquema ").append(" ORDER BY idEtapa ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<ObservacionDocumentacionSP> consultaObservacionDocumentacionSPByOficioNull(long idExpSPCA)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ObservacionDocumentacionSP> lst = null;
		if(idExpSPCA != 0 && idExpSPCA != -1){
			consulta.append("where idExpSPCA =").append(idExpSPCA);
		}
	
		if(consulta.length()>0){
			consulta.append(" and idOficioObsSP is null");
		}else{
			consulta.append("where idOficioObsSP is null");
		}
	
		
		consulta.insert(0, "From ObservacionDocumentacionSP ").append(" order by idObsExpSPCa");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	
	public int borrarVolumenAuditor(String folioCartaAdhesion, Integer tipoDocumentacion)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM auditor_solicitud_pago WHERE folio_carta_adhesion= ").append("'"+folioCartaAdhesion+"' AND tipo_documentacion= ").append(tipoDocumentacion);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
	}
	
	@SuppressWarnings("unchecked")
	public List<ObservacionDocumentacionSP> consultaObservacionDocumentacion(long idExpSPCA)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ObservacionDocumentacionSP> lst = null;
		if(idExpSPCA != 0 && idExpSPCA != -1){
			consulta.append("where idExpSPCA =").append(idExpSPCA);
		}	
		consulta.insert(0, "From ObservacionDocumentacionSP ").append(" order by idObsExpSPCa");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}

	@SuppressWarnings("unchecked")
	public List<PrgEspecialistaNumCartasV> consultaPgrEspecialistaNumCartasV(long idEspecialista)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PrgEspecialistaNumCartasV> lst = null;
		if(idEspecialista != 0 && idEspecialista != -1){
			consulta.append("where idEspecialista =").append(idEspecialista);
		}	
		consulta.insert(0, "From PrgEspecialistaNumCartasV ").append(" order by programa");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramaNumCartasV> consultaProgramaNumCartasV()throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ProgramaNumCartasV> lst = null;
		
		consulta.insert(0, "From ProgramaNumCartasV ").append(" order by programa");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	public List<OficioRespuestaSolicitudPago> consultaOficioRespuestaSolicitudPago(String folioCartaAdhesion) throws JDBCException {
		return consultaOficioRespuestaSolicitudPago(folioCartaAdhesion, 0);
	}
	@SuppressWarnings("unchecked")
	public List<OficioRespuestaSolicitudPago> consultaOficioRespuestaSolicitudPago(String folioCartaAdhesion, long idOficioRespuesta) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<OficioRespuestaSolicitudPago> lst=null;
		if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion='").append(folioCartaAdhesion).append("'");
		}		
		if(idOficioRespuesta != 0 && idOficioRespuesta != -1){
			if(consulta.length()>0){
				consulta.append(" and idOficioRespuesta=").append(idOficioRespuesta);
			}else{
				consulta.append("where idOficioRespuesta=").append(idOficioRespuesta);
			}
		}
		consulta.insert(0, "From OficioRespuestaSolicitudPago ").append(" order by fechaCreacion");
		lst= session.createQuery(consulta.toString()).list();
	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<OficioObsSolicitudPago> consultaOficioObsSolPagoMaxIdExp(long idExpSPCA)throws JDBCException {
		 StringBuilder consulta= new StringBuilder();
		 StringBuilder subConsulta= new StringBuilder();
		 List<OficioObsSolicitudPago> lista = null;
		 try{
			 subConsulta.append("select max(id_oficio_obs_sol_pago) from observacion_documentacion_sp  where id_exp_sp_carta_adhesion = ").append(idExpSPCA);
			 consulta.insert(0, "select * from oficio_obs_solicitud_pago where id_oficio_obs_sol_pago = ("+subConsulta.toString()+")");
			 lista = session.createSQLQuery(consulta.toString()).addEntity(OficioObsSolicitudPago.class).list(); 	 		 
		}catch (Exception e){
			AppLogger.error("errores","Ocurrio un error en consultaOficioObsSolPagoMaxIdExp debido a:"+e.getMessage());
			transaction.rollback();
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditorSolicitudPagoV> consultaAuditorSolPagoV(String folioCartaAdhesion, int tipoDocumentacion)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AuditorSolicitudPagoV> lst = null;
		if(folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
		}
		
		if(tipoDocumentacion != 0 && tipoDocumentacion != -1){
			if(consulta.length()>0){
				consulta.append(" and tipoDocumentacion=").append(tipoDocumentacion);
			}else{
				consulta.append("where tipoDocumentacion=").append(tipoDocumentacion);
			}
		}
		
		consulta.insert(0, "From AuditorSolicitudPagoV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	public List<ConstanciasAlmacenamiento> consultaConstanciasAlmacenamiento(String folioCartaAdhesion)throws JDBCException {
		return consultaConstanciasAlmacenamiento(folioCartaAdhesion, 0, null, null);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ConstanciasAlmacenamiento> consultaConstanciasAlmacenamiento(String folioCartaAdhesion, int idAlmacenadora, String claveBodega, String folio)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ConstanciasAlmacenamiento> lst = null;
		if(folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
			consulta.append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
		}
		
		if(idAlmacenadora != 0 && idAlmacenadora != -1){
			if(consulta.length()>0){
				consulta.append(" and idAlmacenadora=").append(idAlmacenadora);
			}else{
				consulta.append("where idAlmacenadora=").append(idAlmacenadora);
			}
		}
		
		if (claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega='").append(claveBodega).append("'");
			}
		}	
		
		if (folio != null && !folio.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and folio='").append(folio).append("'");
			}else{
				consulta.append("where folio='").append(folio).append("'");
			}
		}	
		
		//ggfggg
		consulta.insert(0, "From ConstanciasAlmacenamiento ").append(" order by ").append(" claveBodega");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public Double getSumaConstanciasAlmacenamientoByFolioCA(String folioCartaAdhesion) throws Exception{
		double sumaCA = 0;
		try{
			StringBuilder hql = new StringBuilder().append("select new java.lang.Double(COALESCE (sum(volumen),0))")
					.append("from ConstanciasAlmacenamiento ").append("where folioCartaAdhesion ='").append(folioCartaAdhesion).append("'");
			List<Double> lst = (List<Double>) session.createQuery(hql.toString()).list();
			if (lst != null && lst.size() > 0){
				sumaCA = lst.get(0);
			}
		}catch (Exception e){	
			transaction.rollback();
			throw e;
		}
		return sumaCA;
	}

	
	@SuppressWarnings("unchecked")
	public List<PagosV> consultaPagoAtentaNota()throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PagosV> lst = null;
		consulta.insert(0, "From PagosV where archivoAtentaNota is not null");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AsignacionCartasAdhesion> consultaCultivo(String folioCartaAdhesion, int idCultivo) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		List<AsignacionCartasAdhesion> lista = null;
		try{  
			if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
				subConsulta.append(" where folio_carta_adhesion='").append(folioCartaAdhesion).append("'");
			}
			
			if (idCultivo != 0 && idCultivo != -1){
				if(subConsulta.length()> 0){
					subConsulta.append(" and id_cultivo =").append(idCultivo);;
				} else {
					subConsulta.append(" where id_cultivo =").append(idCultivo);;
				}			
			}

			subConsulta.insert(0, "select distinct id_cultivo from asignacion_cartas_adhesion ");
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
	public List<AsignacionCartasAdhesion> consultaVariedad(String folioCartaAdhesion, int idVariedad) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		List<AsignacionCartasAdhesion> lista = null;
		try{  
			if (folioCartaAdhesion != null && !folioCartaAdhesion.isEmpty()){
				subConsulta.append(" where folio_carta_adhesion='").append(folioCartaAdhesion).append("'");
			}
			
			if (idVariedad != 0 && idVariedad != -1){
				if(subConsulta.length()> 0){
					subConsulta.append(" and id_variedad =").append(idVariedad);;
				} else {
					subConsulta.append(" where id_variedad =").append(idVariedad);;
				}			
			}

			
			subConsulta.insert(0, "select distinct id_variedad from asignacion_cartas_adhesion ");
			consulta.insert(0, "select * from variedad where id_variedad in (").append(subConsulta.toString()).append(") order by variedad");
			System.out.println("Query --- > "+consulta.toString());
			lista = session.createSQLQuery(consulta.toString()).addEntity(Variedad.class).list(); 
		}catch (Exception e){
			AppLogger.error("errores","Ocurrio un error al recuperar el catalogo de cultivo debido a:"+e.getMessage());
			transaction.rollback();
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Expediente> consultaCAExpedienteCompleto(String folioCartaAdhesion, int idPrograma) throws JDBCException {
		List<Expediente> lst=null;
		StringBuilder consulta= new StringBuilder();
		consulta.append("select e.id_expediente, e.expediente, e.descripcion, e.tipo, e.prioridad_expediente " +
				"from expedientes_programas  ep, expedientes e where  not exists (select * from documentacion_sp_carta_adhesion  d " +
				"where d.id_expediente = ep.id_expediente and d.folio_carta_adhesion = " +
				"'").append(folioCartaAdhesion).append("') ").append(" and ep.id_programa = ").append(idPrograma)
				.append(" and ep.documentacion_opcional=false and ep.id_expediente = e.id_expediente and e.tipo in ('DSP','DSPYF')").append(" order by e.prioridad_expediente");
		
		System.out.println("consulta "+consulta.toString());
		lst = session.createSQLQuery(consulta.toString()).addEntity(Expediente.class).list();
		return lst;
	}
		
	public void borraDocumentosObservados(long idExpSPCA) throws  JDBCException {
		String hql = "delete from  observacion_documentacion_sp where id_exp_sp_carta_adhesion = " + idExpSPCA;
		session.createSQLQuery(hql).executeUpdate();
	}
	
}//End clase