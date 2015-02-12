package mx.gob.comer.sipc.dao;
import java.math.BigInteger;
import java.util.List;












import mx.gob.comer.sipc.domain.AuditoresExternos;
import mx.gob.comer.sipc.domain.Bancos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.CuentaBancaria;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Ejercicios;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.EstatusOficio;
import mx.gob.comer.sipc.domain.EstatusPago;
import mx.gob.comer.sipc.domain.Expediente;
import mx.gob.comer.sipc.domain.ExpedienteAuditor;
import mx.gob.comer.sipc.domain.ExpedienteComprador;
import mx.gob.comer.sipc.domain.Localidades;
import mx.gob.comer.sipc.domain.Municipios;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.Parametros;
import mx.gob.comer.sipc.domain.Perfiles;
import mx.gob.comer.sipc.domain.Personal;
import mx.gob.comer.sipc.domain.PlazasBancarias;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.ProgramaComprador;
import mx.gob.comer.sipc.domain.Usuarios;
import mx.gob.comer.sipc.domain.catalogos.AlmacenGeneralDeposito;
import mx.gob.comer.sipc.domain.catalogos.AreasResponsables;
import mx.gob.comer.sipc.domain.catalogos.BodegaUso;
import mx.gob.comer.sipc.domain.catalogos.Bodegas;
import mx.gob.comer.sipc.domain.catalogos.CapacidadesBodegas;
import mx.gob.comer.sipc.domain.catalogos.CatCriteriosValidacion;
import mx.gob.comer.sipc.domain.catalogos.Ciclo;
import mx.gob.comer.sipc.domain.catalogos.Componente;
import mx.gob.comer.sipc.domain.catalogos.CriterioPago;
import mx.gob.comer.sipc.domain.catalogos.Ejido;
import mx.gob.comer.sipc.domain.catalogos.Especialista;
import mx.gob.comer.sipc.domain.catalogos.EstadosCivil;
import mx.gob.comer.sipc.domain.catalogos.EstatusCartaAdhesion;
import mx.gob.comer.sipc.domain.catalogos.EstatusSeguimiento;
import mx.gob.comer.sipc.domain.catalogos.ExpedienteRepresentante;
import mx.gob.comer.sipc.domain.catalogos.Modalidad;
import mx.gob.comer.sipc.domain.catalogos.RepresentanteComprador;
import mx.gob.comer.sipc.domain.catalogos.RepresentanteLegal;
import mx.gob.comer.sipc.domain.catalogos.TipoDocumentoPago;
import mx.gob.comer.sipc.domain.catalogos.TiposAsentamiento;
import mx.gob.comer.sipc.domain.catalogos.TiposIdentificacion;
import mx.gob.comer.sipc.domain.catalogos.TiposVialidad;
import mx.gob.comer.sipc.domain.catalogos.UnidadMedida;
import mx.gob.comer.sipc.domain.catalogos.Variedad;
import mx.gob.comer.sipc.domain.transaccionales.SolicitudInscripcion;
import mx.gob.comer.sipc.vistas.domain.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;













import org.hibernate.JDBCException;

public class CatalogosDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public CatalogosDAO(){
		
	}
	public CatalogosDAO(Session session) {
		this.session = session;
	}

	/**
	 * Consulta por idUsuario a la tabla de "USUARIOS"   
	 *  
	 * @param idUsuario Es el id del usuario
	 * 		
	 * @throws JDBCException Si ocurre cualquier error al consultar el usuarios
	 *  
	 * @return Lista de datos de usuarios
	 *  
	 */
	@SuppressWarnings("unchecked")
	public List<Usuarios> consultaUsuarios(int idUsuario, String nomUsuario, String password) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<Usuarios> lst=null;
		if (idUsuario != 0 && idUsuario != -1){
			consulta.append("where idUsuario = ").append(idUsuario);
		}	
		if (!(nomUsuario==null || nomUsuario.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and nombreUsuario = '").append(nomUsuario).append("'");
			}else{
				consulta.append("where nombreUsuario= '").append(nomUsuario).append("'");
			}
		}
		if (!(password==null || password.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and contrasenia = '").append(password).append("'");
			}else{
				consulta.append("where contrasenia= '").append(password).append("'");
			}
		}
		consulta.insert(0, "From Usuarios ").append(" ORDER BY nombre, paterno, materno");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	public List<Estado> consultaEstado(int idEstado) throws JDBCException {
		 return consultaEstado( idEstado,  null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> consultaEstado(int idEstado, String clave) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Estado> lst=null;
		if (idEstado != 0 && idEstado != -1){
			consulta.append("where idEstado = ").append(idEstado);
		}
		if (!(clave==null || clave.equals(""))){
			if(consulta.length()>0){
				consulta.append(" and clave = '").append(clave).append("'");
			}else{
				consulta.append("where clave = '").append(clave).append("'");
			}
		}
		consulta.insert(0, "From Estado ").append(" ORDER BY nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<Programa> consultaPrograma(int idPrograma) throws JDBCException {
		return consultaPrograma (idPrograma, 0,0);
	}
	
	public List<Programa> consultaPrograma(int idPrograma, int idArea) throws JDBCException {
		return consultaPrograma (idPrograma, idArea, 0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Programa> consultaPrograma(int idPrograma, int idArea, int idComponente) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Programa> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		if (idArea != 0 && idArea != -1){
			if(consulta.length()>0){
				consulta.append(" and idArea =").append(idArea);
			}else{
				consulta.append("where idArea=").append(idArea);
			}
		}
		if (idComponente != 0 && idComponente != -1){
			if(consulta.length()>0){
				consulta.append(" and idComponente =").append(idComponente);
			}else{
				consulta.append("where idComponente=").append(idComponente);
			}
		}
		consulta.insert(0, "From Programa ").append(" ORDER BY descripcionCorta");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bancos> consultaBanco(int bancoId) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Bancos> lst=null;
		if (bancoId != 0 && bancoId != -1){
			consulta.append("where bancoId = ").append(bancoId);
		}
		consulta.insert(0, "From Bancos ").append(" ORDER BY nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<PlazasBancarias> consultaPlaza(String numeroPlaza) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<PlazasBancarias> lst=null;
		if ( numeroPlaza!=null && !numeroPlaza.equals("")){
			consulta.append("where numeroPlaza = '").append(numeroPlaza).append("'");
		}
		consulta.insert(0, "From PlazasBancarias ").append(" ORDER BY numeroPlaza");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProgramaCompradorV> consultaCompradorByPrograma(int idPrograma) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ProgramaCompradorV> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		
		consulta.insert(0, "From ProgramaCompradorV ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<CuentaBancaria> consultaCtaBancaria(int idComprador) throws JDBCException {
		return consultaCtaBancaria(idComprador,0,null);
	}

	public List<CuentaBancaria> consultaCtaBancaria(int idComprador,int estatus) throws JDBCException {
		return consultaCtaBancaria(idComprador,estatus,null);
	}
	
	public List<CuentaBancaria> consultaCtaBancaria(String clabe) throws JDBCException {
		return consultaCtaBancaria(0,0,clabe);
	}
	
	@SuppressWarnings("unchecked")
	public List<CuentaBancaria> consultaCtaBancaria(int idComprador, int estatus, String clabe) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CuentaBancaria> lst=null;
		if (idComprador != 0 && idComprador != -1){
			consulta.append("where idComprador = ").append(idComprador);
		}
		if(estatus!=0){
			if(consulta.length()>0){
				consulta.append(" and estatus =").append(estatus);
			}else{
				consulta.append("where estatus=").append(estatus);
			}
		}
		if(clabe!=null && !clabe.equals("")){
			if(consulta.length()>0){
				consulta.append(" and clabe='").append(clabe).append("'");
			}else{
				consulta.append("where clabe='").append(clabe).append("'");
			}
		}
		
		consulta.insert(0, "From CuentaBancaria ").append(" ORDER BY clabe ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<CuentasBancariasV> consultaCtaBancariaV(int idComprador, int estatus, String clabe) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CuentasBancariasV> lst=null;
		if (idComprador != 0 && idComprador != -1){
			consulta.append("where idComprador = ").append(idComprador);
		}
		if(estatus!=0){
			if(consulta.length()>0){
				consulta.append(" and estatus =").append(estatus);
			}else{
				consulta.append("where estatus=").append(estatus);
			}
		}
		if(clabe!=null && !clabe.equals("")){
			if(consulta.length()>0){
				consulta.append(" and clabe='").append(clabe).append("'");
			}else{
				consulta.append("where clabe='").append(clabe).append("'");
			}
		}
		
		consulta.insert(0, "From CuentasBancariasV ").append(" ORDER BY clabe ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	public List<ProgramaComprador> consultaProgComprador(int idComprador, int idPrograma) throws JDBCException {
		return consultaProgComprador(idComprador,idPrograma,"");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramaComprador> consultaProgComprador(int idComprador, int idPrograma, String noCarta) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ProgramaComprador> lst=null;
		if ( idComprador!=-1){
			consulta.append("where idComprador =").append(idComprador);
		}
		if(idPrograma != -1){
			if(consulta.length()>0){
				consulta.append(" and idPrograma =").append(idPrograma);
			}else{
				consulta.append("where idPrograma=").append(idPrograma);
			}
		}
		if(noCarta != null && !noCarta.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and noCarta ='").append(noCarta).append("'");
			}else{
				consulta.append("where noCarta ='").append(noCarta).append("'");
			}
		}
		consulta.insert(0, "From ProgramaComprador ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramaEstadoV> consultaEdoXPrograma(int idPrograma, int idEstado) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ProgramaEstadoV> lst=null;
		if ( idPrograma!=-1){
			consulta.append("where idPrograma =").append(idPrograma);
		}
		if(idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado =").append(idEstado);
			}else{
				consulta.append("where idEstado=").append(idEstado);
			}
		}
		consulta.insert(0, "From ProgramaEstadoV ").append(" ORDER BY estado ");;
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<Personal> consultaPersonal(int idPersonal, String puesto, boolean oficioCgcDgaf, boolean oficioCppCgcDgaf, boolean oficioVoBoCgcDgaf){
		return consultaPersonal(idPersonal, puesto, oficioCgcDgaf, oficioCppCgcDgaf, oficioVoBoCgcDgaf, false, false, false, null);
	}
	
	public List<Personal> consultaPersonal(boolean cppAcuseRechazados){
		return consultaPersonal(0, null, false, false, false, cppAcuseRechazados, false, false, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Personal> consultaPersonal(int idPersonal, String puesto, boolean oficioCgcDgaf, boolean oficioCppCgcDgaf, boolean oficioVoBoCgcDgaf, boolean cppAcuseRechazados, boolean oficioEntregaCartas, boolean oficioCppEntregaCartas, String area){
		StringBuilder consulta= new StringBuilder();
		List<Personal> lst=null;
		if (idPersonal != 0 && idPersonal != -1){		
			consulta.append("where idPersonal = ").append(idPersonal);
		}
		
		if(puesto!=null && puesto!=""){
			if (consulta.length() > 0){
				consulta.append(" and puesto ='").append(puesto).append("'");
			}else{
				consulta.append("where puesto='").append(puesto).append("'");
			}
		}

		if (oficioCgcDgaf){
			if (consulta.length() > 0){
				consulta.append(" and oficioCgcDgaf=").append(true);
			}else{
				consulta.append("where oficioCgcDgaf=").append(true);
			}
		}
		
		if (oficioCppCgcDgaf){
			if (consulta.length() > 0){
				consulta.append(" and oficioCppCgcDgaf=").append(true);
			}else{
				consulta.append("where oficioCppCgcDgaf=").append(true);
			}
		}
		
		if (oficioVoBoCgcDgaf){
			if (consulta.length() > 0){
				consulta.append(" and oficioVoboCgcDgaf=").append(true);
			}else{
				consulta.append("where oficioVoboCgcDgaf=").append(true);
			}
		}
		if (cppAcuseRechazados){
			if (consulta.length() > 0){
				consulta.append(" and cppAcuseRechazados=").append(true);
			}else{
				consulta.append("where cppAcuseRechazados=").append(true);
			}
		}

		if (oficioEntregaCartas){
			if (consulta.length() > 0){
				consulta.append(" and oficioEntregaCarta=").append(true);
			}else{
				consulta.append("where oficioEntregaCarta=").append(true);
			}
		}

		if (oficioCppEntregaCartas){
			if (consulta.length() > 0){
				consulta.append(" and oficioCppEntregaCarta=").append(true);
			}else{
				consulta.append("where oficioCppEntregaCarta=").append(true);
			}
		}

		if(area!=null && area!=""){
			if (consulta.length() > 0){
				consulta.append(" and area ='").append(area).append("'");
			}else{
				consulta.append("where area='").append(area).append("'");
			}
		}

		consulta.insert(0, "From Personal ").append(" order by idPersonal");
		lst = session.createQuery(consulta.toString()).list();
		session.clear();
		
		return lst;

	}
	
	public List<Personal> consultaPersonalSQLQuery(int idPersonal, String puesto, boolean oficioCgcDgaf, boolean oficioCppCgcDgaf, boolean oficioVoBoCgcDgaf, int idPrograma){
		return consultaPersonalSQLQuery(idPersonal, puesto, oficioCgcDgaf, oficioCppCgcDgaf, oficioVoBoCgcDgaf, idPrograma, false);
	}

	public List<Personal> consultaPersonalSQLQuery(int idPersonal, String puesto, boolean oficioCgcDgaf, boolean oficioCppCgcDgaf, boolean oficioVoBoCgcDgaf, int idPrograma, boolean oficioCppEntregaCartas){
		return consultaPersonalSQLQuery(idPersonal, puesto, oficioCgcDgaf, oficioCppCgcDgaf, oficioVoBoCgcDgaf, idPrograma, oficioCppEntregaCartas, false);
	}

	@SuppressWarnings("unchecked")
	public List<Personal> consultaPersonalSQLQuery(int idPersonal, String puesto, boolean oficioCgcDgaf, boolean oficioCppCgcDgaf, boolean oficioVoBoCgcDgaf, int idPrograma, boolean oficioCppEntregaCartas, boolean idArea){
		StringBuilder consulta= new StringBuilder();
		List<Personal> lst=null;
		if (idPersonal != 0 && idPersonal != -1){		
			consulta.append("where id_personal = ").append(idPersonal);
		}
		
		if(puesto!=null && puesto!=""){
			if (consulta.length() > 0){
				consulta.append(" and puesto ='").append(puesto).append("'");
			}else{
				consulta.append("where puesto='").append(puesto).append("'");
			}
		}
		if (oficioCgcDgaf){
			if (consulta.length() > 0){
				consulta.append(" and oficio_cgc_dgaf=").append(true);
			}else{
				consulta.append("where oficio_cgc_dgaf=").append(true);
			}
		}
		if (oficioCppCgcDgaf){
			if (consulta.length() > 0){
				consulta.append(" and oficio_cpp_cgc_dgaf=").append(true).append(" and position('").append(idPrograma).append("' in oficio_cpp_cgc_dgaf_pg)<>0");
			}else{
				consulta.append(" where oficio_cpp_cgc_dgaf=").append(true).append(" and position('").append(idPrograma).append("' in oficio_cpp_cgc_dgaf_pg)<>0");
			}
		}
		
		if (oficioVoBoCgcDgaf){
			if (consulta.length() > 0){
				consulta.append(" and oficio_vobo_cgc_dgaf=").append(true).append(" and position('").append(idPrograma).append("' in oficio_vobo_cgc_dgaf_pg)<>0");;
			}else{
				consulta.append(" where oficio_vobo_cgc_dgaf=").append(true).append(" and position('").append(idPrograma).append("' in oficio_vobo_cgc_dgaf_pg)<>0");;
			}
		}
		
		if (oficioCppEntregaCartas){
			if (consulta.length() > 0){
				consulta.append(" and oficio_cpp_entrega_cartas=").append(true);
			}else{
				consulta.append("where oficio_cpp_entrega_cartas=").append(true);
			}
		}

		if (idArea){
			if (consulta.length() > 0){
				consulta.append(" and position('").append(idPrograma).append("' in area)<>0");
			}else{
				consulta.append(" where position('").append(idPrograma).append("' in area)<>0");
			}
		}
		
		consulta.insert(0, "select * from personal ").append(" order by id_personal");
		lst = session.createSQLQuery(consulta.toString()).addEntity(Personal.class).list();
		session.clear();
		System.out.println("Lista "+lst.size());
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public String consultaParametros(String variable){
		String respuesta = "";
		try{
			String valor = null;
			List<Parametros> lst = (List<Parametros>) session.createQuery("from Parametros where variable = '"+ variable+"'").list();
			if(lst!=null && lst.size()>0){
				valor = lst.get(0).getValor();
			}		
			respuesta = valor;
		}catch (JDBCException e){
			e.printStackTrace();
		}
		return respuesta;
	} 
	
	/**
	 * Guarda un objeto en la base de datos de acuerdo al mapeo de la tabla que tiene asignado dicho objeto  
	 *  
	 * @param o Es el objeto a guardar		
	 * @throws Exception Si ocurre un error al guardar el objeto
	 * @throws JDBCException Si ocurre cualquier error al guardar en la base de datos
	 *  
	 *  
	 */
	public Object guardaObjeto(Object o)throws JDBCException {
		try{
			session.saveOrUpdate(o);
			session.flush();
			session.clear();
		}catch (JDBCException e){
			e.printStackTrace();
			transaction.rollback();
			throw e;
		}
		return o;
	}
	
	public long guardaPagos(Pagos p)throws JDBCException {
		try{
			session.saveOrUpdate(p);
			session.flush();
			session.clear();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}
		return p.getIdPago();
	}
	
	public SolicitudInscripcion guardaSolicitudInscripcion(SolicitudInscripcion si)throws JDBCException {
		try{
			session.saveOrUpdate(si);
			session.flush();
			session.clear();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}
		return si;
	}
	
	public int validaClabeInterbancaria(String clabe) throws Exception{ 
		String query;
		int resp=0;
		try {
			query = "SELECT valida_clabe('"+clabe+"')";
			resp = (Integer) session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public List<Perfiles> consultaPerfil(int idPerfil) throws  JDBCException{
		StringBuilder consulta= new StringBuilder();
		List<Perfiles> lst=null;
		if (idPerfil != 0 && idPerfil != -1){
			consulta.append("where idPerfil = ").append(idPerfil);
		}	
		consulta.insert(0, "From Perfiles ").append(" ORDER BY perfil");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comprador> consultaPruebaComprador(String nombre) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Comprador> lst=null;
		if (nombre != null && nombre != ""){
			consulta.append("where nombre = '").append(nombre).append("'");
		}
		
		consulta.insert(0, "From Comprador ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	/**
	 * Borra un objeto  en la base de datos de acuerdo al mapeo de la tabla que tiene asignado dicho objeto  
	 *  
	 * @param o Es el objeto a borrar		
	 * @throws Exception Si ocurre un error al borrar el objeto
	 * @throws JDBCException Si ocurre cualquier error al borrar el objeto en la base de datos
	 *  
	 *  
	 */
	public void borrarObjeto(Object o)throws JDBCException {	
		session.delete(o);
		session.flush();
		session.clear();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Programa> isolatedConsultaPrograma(int idPrograma) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		Session s = null;
		List<Programa> lst=null;
		try{
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if (idPrograma != 0 && idPrograma != -1){
				consulta.append("where idPrograma = ").append(idPrograma);
			}
			consulta.insert(0, "From Programa ").append(" ORDER BY descripcionCorta");
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
	public List<EstatusPago> consultaEstatusPago() throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstatusPago> lst=null;
		
		consulta.append("From EstatusPago ").append(" ORDER BY descripcionStatus");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ejercicios> consultaEjercicio(int ejercicio) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Ejercicios> lst=null;
		if (ejercicio != 0 && ejercicio != -1){
			consulta.append("where ejercicio = ").append(ejercicio);
		}
		consulta.insert(0, "From Ejercicios ").append(" ORDER BY ejercicio");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	public List<Cultivo> consultaCultivo(int idCultivo) throws JDBCException {
		return consultaCultivo(idCultivo, null);
	}
	
	public List<Cultivo> consultaCultivo() throws JDBCException {
		return consultaCultivo(0, null);
	}
	
	public List<Cultivo> consultaCultivo(String idCultivoS) throws JDBCException {
		return consultaCultivo(0, idCultivoS);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cultivo> consultaCultivo(int idCultivo, String idCultivoS) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Cultivo> lst=null;
		if (idCultivo != 0 && idCultivo != -1){
			consulta.append("where idCultivo = ").append(idCultivo);
		}
		if (idCultivoS != null && !idCultivoS.isEmpty()){
			
			if(consulta.length()>0){
				consulta.append(" and idCultivo in (").append(idCultivoS).append( ") ");
			}else{
				consulta.append(" where idCultivo in (").append(idCultivoS).append( ") ");
			}
		}
		
		consulta.insert(0, "From Cultivo ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditoresExternos> consultaAuditoresbyNumAuditor(String numeroAuditor)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AuditoresExternos> lst = null;
		if (numeroAuditor != null && !numeroAuditor.isEmpty()){
			consulta.append("where numeroAuditor = '").append(numeroAuditor).append( "' ");
		}
		consulta.insert(0, "From AuditoresExternos ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditoresExternos> consultaAuditores(String numeroAuditor, String nombre, int estatus)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AuditoresExternos> lst = null;
		if (numeroAuditor != null && !numeroAuditor.isEmpty()){
			consulta.append("where numeroAuditor = '").append(numeroAuditor).append( "' ");
		}
		if (nombre != null && !nombre.isEmpty()){
			
			if(consulta.length()>0){
				consulta.append(" and nombre LIKE '%").append(nombre).append( "%' ");
			}else{
				consulta.append(" where nombre LIKE '%").append(nombre.toUpperCase()).append( "%' ");
			}
		}
		if( estatus !=-1){
			if(consulta.length()>0){
				consulta.append(" and estatus =").append(estatus);
			}else{
				consulta.append(" where estatus =").append(estatus);
			}
		}
			
		consulta.insert(0, "From AuditoresExternos ").append(" order by nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Expediente> consultaExpediente(int idExpediente, String tipoExpediente)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		StringBuilder in = new StringBuilder();
		List<Expediente> lst = null;
		if(idExpediente!=0 && idExpediente != -1){
			consulta.append("where idExpediente = '").append(idExpediente).append("'");
		}
		if (tipoExpediente != null && !tipoExpediente.isEmpty()){
			
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
		consulta.insert(0, "From Expediente ").append("order by expediente");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
		
	public Programa guardaPrograma(Programa o)throws JDBCException {
		try{
			session.saveOrUpdate(o);
			session.flush();
			session.clear();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}
		return o;
	}
	
	public Object guardaPrograma(Object o)throws JDBCException {
		try{
			session.saveOrUpdate(o);
			session.flush();
			session.clear();
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}
		return o;
	}
	
	public List<ProgramasV> consultaProgramaV(int idPrograma)throws JDBCException {
		return consultaProgramaV(idPrograma, 0);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProgramasV> consultaProgramaV(int idPrograma, int idArea)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<ProgramasV> lst = null;
		if(idPrograma!=0 && idPrograma != -1){
			consulta.append("where idPrograma = ").append(idPrograma);
		}
		if (idArea != 0 && idArea != -1){
			if(consulta.length()>0){
				consulta.append(" and idArea =").append(idArea);
			}else{
				consulta.append(" where idArea =").append(idArea);
			}	
		}
		consulta.insert(0, "From ProgramasV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<Ciclo> consultaCiclo(int idCiclo)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Ciclo> lst = null;
		if(idCiclo!=0 && idCiclo != -1){
			consulta.append("where idCiclo = ").append(idCiclo);
		}
		consulta.insert(0, "From Ciclo ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}

	@SuppressWarnings("unchecked")
	public List<Componente> consultaComponente(int idComponente)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Componente> lst = null;
		if(idComponente!=0 && idComponente != -1){
			consulta.append("where idComponente = ").append(idComponente);
		}
		consulta.insert(0, "From Componente ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CriterioPago> consultaCriterioPago(int idCriterioPago)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CriterioPago> lst = null;
		if(idCriterioPago!=0 && idCriterioPago != -1){
			consulta.append("where idCriterioPago = ").append(idCriterioPago);
		}
		consulta.insert(0, "From CriterioPago ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadMedida> consultaUnidadMedida(int idUnidadMedida)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<UnidadMedida> lst = null;
		if(idUnidadMedida!=0 && idUnidadMedida != -1){
			consulta.append("where idUnidadMedida = ").append(idUnidadMedida);
		}
		consulta.insert(0, "From UnidadMedida ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	public List<Variedad> consultaVariedad()throws JDBCException {
		return consultaVariedad(0,0, null);
	}
	
	public List<Variedad> consultaVariedad(String idVariedades)throws JDBCException {
		return consultaVariedad(0,0, idVariedades);
	}
	
	@SuppressWarnings("unchecked")
	public List<Variedad> consultaVariedad(int idVariedad, int idCultivo, String idVariedades)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Variedad> lst = null;
		if(idVariedad!=0 && idVariedad != -1){
			consulta.append("where idVariedad = ").append(idVariedad);
		}
		if (idCultivo != 0 && idCultivo != -1){
			if(consulta.length()>0){
				consulta.append(" and idCultivo =").append(idCultivo);
			}else{
				consulta.append(" where idCultivo =").append(idCultivo);
			}	
		}
		if (idVariedades != null && !idVariedades.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and idVariedad in  (").append(idVariedades).append(")");
			}else{
				consulta.append(" where idVariedad in  (").append(idVariedades).append(")");
			}	
		}
		consulta.insert(0, "From Variedad ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditoresExternosV> consultaAuditoresExternosV(String numeroAuditor)
			throws JDBCException {
		/*
		 * , String nombre, String estado, String municipio, String localidad,
		 * String calle, Integer telefonos, String estatus, Integer codigoPostal
		 */
		StringBuilder consulta = new StringBuilder();

		List<AuditoresExternosV> lst = null;
		if (numeroAuditor != null && !numeroAuditor.isEmpty()) {
			consulta.append(" where numeroAuditor = '").append(numeroAuditor).append("' ");
		}/*
		 * if (nombre != null && !nombre.isEmpty()){ if(consulta.length()>0){
		 * consulta.append(" and nombre LIKE '%").append(nombre).append( "%' ");
		 * }else{
		 * consulta.append(" where nombre LIKE '%").append(nombre).append(
		 * "%' "); } } if (estado != null && !estado.isEmpty()){
		 * if(consulta.length()>0){
		 * consulta.append(" and estado LIKE '%").append(estado).append( "%' ");
		 * }else{
		 * consulta.append(" where estado LIKE '%").append(estado).append(
		 * "%' "); } } if (municipio != null && !municipio.isEmpty()){
		 * if(consulta.length()>0){
		 * consulta.append(" and municipio LIKE '%").append(municipio).append(
		 * "%' "); }else{
		 * consulta.append(" where municipio LIKE '%").append(municipio).append(
		 * "%' "); } } if (localidad != null && !localidad.isEmpty()){
		 * if(consulta.length()>0){
		 * consulta.append(" and localidad LIKE '%").append(localidad).append(
		 * "%' "); }else{
		 * consulta.append(" where localidad LIKE '%").append(localidad).append(
		 * "%' "); } } if (calle != null && !calle.isEmpty()){
		 * if(consulta.length()>0){
		 * consulta.append(" and calle LIKE '%").append(calle).append( "%' ");
		 * }else{ consulta.append(" where calle LIKE '%").append(calle).append(
		 * "%' "); } }
		 * 
		 * if (telefonos != 0 && telefonos != -1){ if(consulta.length()>0){
		 * consulta.append(" and telefonos =").append(telefonos); }else{
		 * consulta.append(" where telefonos =").append(telefonos); } }
		 * 
		 * if (estatus != null && !estatus.isEmpty()){ if(consulta.length()>0){
		 * consulta.append(" and estatus LIKE '%").append(estatus).append(
		 * "%' "); }else{
		 * consulta.append(" where estatus LIKE '%").append(estatus).append(
		 * "%' "); } }
		 * 
		 * if (codigoPostal != 0 && codigoPostal != -1){
		 * if(consulta.length()>0){
		 * consulta.append(" and codigoPostal =").append(codigoPostal); }else{
		 * consulta.append(" where codigoPostal =").append(codigoPostal); } }
		 */

		consulta.insert(0, "From AuditoresExternosV ").append(
				" order by numeroAuditor");
		lst = session.createQuery(consulta.toString()).list();

		return lst;
	}
		
	@SuppressWarnings("unchecked")
	public List<Municipios> consultaMunicipiosbyEdo(int idEstado,
			int idMunicipio, int claveMunicipio, String nombreMunicipio)
			throws JDBCException {
		StringBuilder consulta = new StringBuilder();
		List<Municipios> lst = null;
		if (idEstado != 0 && idEstado != -1) {
			consulta.append("where idEstado = ").append(idEstado);
		}
		if (idMunicipio != 0 && idMunicipio != -1) {
			if (consulta.length() > 0) {
				consulta.append(" and idMunicipio =").append(idMunicipio);
			} else {
				consulta.append(" where idMunicipio =").append(idMunicipio);
			}
		}
		if (claveMunicipio != 0 && claveMunicipio != -1) {
			if (consulta.length() > 0) {
				consulta.append(" and claveMunicipio =").append(claveMunicipio);
			} else {
				consulta.append(" where claveMunicipio =").append(
						claveMunicipio);
			}
		}
		if (nombreMunicipio != null && !nombreMunicipio.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and nombreMunicipio LIKE '%")
						.append(nombreMunicipio).append("%' ");
			} else {
				consulta.append(" where nombreMunicipio LIKE '%")
						.append(nombreMunicipio).append("%' ");
			}
		}

		consulta.insert(0, "From Municipios ").append(
				" order by nombreMunicipio");
		lst = session.createQuery(consulta.toString()).list();

		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<Localidades> consultaLocalidadByMunicipio(int idLocalidad,
			int idEstado, int claveMunicipio, int claveLocalidad,
			String nombreLocalidad) throws JDBCException {
		StringBuilder consulta = new StringBuilder();

		List<Localidades> lst = null;
		if (idLocalidad != 0 && idLocalidad != -1) {
			consulta.append("where idLocalidad = ").append(idLocalidad);
		}
		if (idEstado != 0 && idEstado != -1) {
			if (consulta.length() > 0) {
				consulta.append(" and idEstado =").append(idEstado);
			} else {
				consulta.append(" where idEstado =").append(idEstado);
			}
		}
		if (claveMunicipio != 0 && claveMunicipio != -1) {
			if (consulta.length() > 0) {
				consulta.append(" and claveMunicipio =").append(claveMunicipio);
			} else {
				consulta.append(" where claveMunicipio =").append(
						claveMunicipio);
			}
		}

		if (claveLocalidad != 0 && claveLocalidad != -1) {
			if (consulta.length() > 0) {
				consulta.append(" and claveLocalidad =").append(claveLocalidad);
			} else {
				consulta.append(" where claveLocalidad =").append(
						claveLocalidad);
			}
		}
		if (nombreLocalidad != null && !nombreLocalidad.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and nombreLocalidad LIKE '%")
						.append(nombreLocalidad).append("%' ");
			} else {
				consulta.append(" where nombreLocalidad LIKE '%")
						.append(nombreLocalidad).append("%' ");
			}
		}

		consulta.insert(0, "From Localidades ").append(
				" order by nombreLocalidad");
		lst = session.createQuery(consulta.toString()).list();

		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<AreasResponsables> consultaAreasResponsables(int idArea)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AreasResponsables> lst = null;
		if(idArea!=0 && idArea != -1){
			consulta.append("where idArea = ").append(idArea);
		}
		consulta.insert(0, "From AreasResponsables ").append(" order by area");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditorExpedientesV> consultaAuditorExpedientesV(long idExpedienteAuditor, String numeroAuditor, int idExpediente) throws JDBCException {
        StringBuilder consulta= new StringBuilder();
        List<AuditorExpedientesV> lst=null;
        if (idExpedienteAuditor != 0 && idExpedienteAuditor != -1){
        	consulta.append("where idExpedienteAuditor = ").append(idExpedienteAuditor);
        }
		if (numeroAuditor != null && !numeroAuditor.isEmpty()){
			
			if(consulta.length()>0){
				consulta.append(" and numeroAuditor ='").append(numeroAuditor).append( "' ");
			}else{
				consulta.append(" where numeroAuditor ='").append(numeroAuditor).append( "' ");
			}
		}
		if (idExpediente != 0 && idExpediente != -1){
	       if(consulta.length()>0){
	    	   consulta.append(" and idExpediente=").append(idExpediente);
	       	}else{
	    		consulta.append("where idExpediente=").append(idExpediente);
	       	}
		}

    	consulta.insert(0, "From AuditorExpedientesV ");
        lst= session.createQuery(consulta.toString()).list(); 
        return lst;
  }	
	
	public List<RepresentanteCompradorV> consultaRepresentanteCompradorV(int idRepresentate, int idComprador)throws JDBCException {
		return consultaRepresentanteCompradorV (idRepresentate, idComprador,0);
	}
	
	@SuppressWarnings("unchecked")
	public List<RepresentanteCompradorV> consultaRepresentanteCompradorV(int idRepresentate, int idComprador, int estatusRepresentante)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RepresentanteCompradorV> lst = null;
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
		return lst;
		
	}

	@SuppressWarnings("unchecked")
	public List<RepresentanteComprador> consultaRepresentanteComprador(int idComprador)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RepresentanteComprador> lst = null;
		if(idComprador!=0 && idComprador != -1){
			consulta.append("where idComprador = ").append(idComprador);
		}
		
		consulta.insert(0, "From RepresentanteComprador ");
        lst= session.createQuery(consulta.toString()).list(); 
		return lst;
		
	}

	@SuppressWarnings("unchecked")
	public List<RepresentanteLegal> consultaRepresentanteLegal(int idRepresentante,String nombre, String rfc)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RepresentanteLegal> lst = null;
		if(idRepresentante!=0 && idRepresentante != -1){
			consulta.append("where idRepresentante = ").append(idRepresentante);
		}
		
		if (nombre != null && !nombre.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and nombre LIKE '%").append(nombre).append("%' ");
			} else {
				consulta.append(" where nombre LIKE '%").append(nombre.toUpperCase()).append("%' ");
			}
		}
		
		if (rfc != null && !rfc.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and rfc LIKE '%").append(rfc).append("%' ");
			} else {
				consulta.append(" where rfc LIKE '%").append(rfc.toUpperCase()).append("%' ");	
			}
		}
		
		consulta.insert(0, " From RepresentanteLegal ").append(" order by nombre");
        lst= session.createQuery(consulta.toString()).list(); 
		return lst;
		
	}
	
	public int borrarRepresentanteComprador(int idComprador)throws JDBCException {
		int elementosBorrados = 0;
		try{
			StringBuilder hql = new StringBuilder()
			.append("DELETE FROM representante_comprador WHERE id_comprador= ").append(idComprador);
			elementosBorrados = session.createSQLQuery(hql.toString()).executeUpdate();
			
		}catch (JDBCException e){
			transaction.rollback();
			throw e;
		}	
		return elementosBorrados;
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
				consulta.append(" where numeroAuditor LIKE '%").append(numeroAuditor.toUpperCase()).append("%' ");
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

	@SuppressWarnings("unchecked")
	public List<ExpedienteComprador> consultaExpedienteComprador(long idExpedienteComprador, int idProgramaComprador, int idExpediente) throws JDBCException {
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
	public List<Especialista> consultaEspecialista(int idEspecialista) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Especialista> lst=null;
		if (idEspecialista != 0 && idEspecialista != -1){
			consulta.append("where idEspecialista = ").append(idEspecialista);
		}
		consulta.insert(0, "From Especialista ").append(" ORDER BY nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<RepresentanteExpedientesV> consultaRepresentanteExpedientesV(long idExpedienteRepresentante, int idRepresentante, int idExpediente) throws JDBCException {
        StringBuilder consulta= new StringBuilder();
        List<RepresentanteExpedientesV> lst=null;
        if (idExpedienteRepresentante != 0 && idExpedienteRepresentante != -1){
        	consulta.append("where idExpedienteRepresentante = ").append(idExpedienteRepresentante);
        }
		if (idRepresentante != 0 && idRepresentante != -1){
		       if(consulta.length()>0){
		    	   consulta.append(" and idRepresentante=").append(idRepresentante);
		       	}else{
		    		consulta.append("where idRepresentante=").append(idRepresentante);
		       	}
		}
		if (idExpediente != 0 && idExpediente != -1){
	       if(consulta.length()>0){
	    	   consulta.append(" and idExpediente=").append(idExpediente);
	       	}else{
	    		consulta.append("where idExpediente=").append(idExpediente);
	       	}
		}

    	consulta.insert(0, "From RepresentanteExpedientesV ");
        lst= session.createQuery(consulta.toString()).list(); 
        return lst;
        
	}	
	
	
	public List<RepresentanteLegalV> consultaRepresentanteLegalV(long idRepresentante)throws JDBCException {
		return consultaRepresentanteLegalV(idRepresentante, null);
	}
	@SuppressWarnings("unchecked")
	public List<RepresentanteLegalV> consultaRepresentanteLegalV(long idRepresentante, String rfc)throws JDBCException {
		StringBuilder consulta = new StringBuilder();

		List<RepresentanteLegalV> lst = null;
        if (idRepresentante != 0 && idRepresentante != -1){
        	consulta.append("where idRepresentante = ").append(idRepresentante);
        }
        
        if (rfc != null && !rfc.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and rfc ='").append(rfc).append("' ");
			} else {
				consulta.append(" where rfc ='").append(rfc).append("' ");
			}
		}
        consulta.insert(0, "From RepresentanteLegalV ").append("order by nombre");
		lst = session.createQuery(consulta.toString()).list();
	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteRepresentante> consultaExpedienteRepresentante(long idExpedienteRepresentante, int idRepresentante, int idExpediente) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		
		List<ExpedienteRepresentante> lst=null;
		if (idExpedienteRepresentante != 0 && idExpedienteRepresentante != -1){
			consulta.append("where idExpedienteRepresentante = ").append(idExpedienteRepresentante);
		}
		if (idRepresentante != 0 && idRepresentante != -1){
			if(consulta.length()>0){
				consulta.append(" and idRepresentante=").append(idRepresentante);
			}else{
				consulta.append("where idRepresentante=").append(idRepresentante);
			}
		}
		if (idExpediente != 0 && idExpediente != -1){
			if(consulta.length()>0){
				consulta.append(" and idExpediente=").append(idExpediente);
			}else{
				consulta.append("where idExpediente=").append(idExpediente);
			}
		}
		consulta.insert(0, "From ExpedienteRepresentante ");
		lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<RepresentanteLegal> consultaRepresentantebyRfc(String rfc)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<RepresentanteLegal> lst = null;
		if (rfc != null && !rfc.isEmpty()){
			consulta.append("where rfc = '").append(rfc.toUpperCase()).append( "' ");
		}
		consulta.insert(0, "From RepresentanteLegal ").append(" order by nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comprador> consultaCompradorbyRfc(String rfc)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Comprador> lst = null;
		if (rfc != null && !rfc.isEmpty()){
			consulta.append("where rfc = '").append(rfc.toUpperCase()).append( "' ");
		}
		consulta.insert(0, "From Comprador ").append(" order by nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<AlmacenGeneralDeposito> consultaAlmacenadora(int idAlmacen)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<AlmacenGeneralDeposito> lst = null;
		if(idAlmacen!=0 && idAlmacen != -1){
			consulta.append("where idAlmacen = ").append(idAlmacen);
		}
		consulta.insert(0, "From AlmacenGeneralDeposito ").append(" order by nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Bodegas> consultaBodegas(String claveBodega)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Bodegas> lst = null;
		if(claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega ='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega ='").append(claveBodega).append("'");
			}
		}
		consulta.insert(0, "From Bodegas ").append(" order by nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}
	
	
	
	public List<ProgramaCartasV> consultaCarta(String carta) throws Exception,JDBCException {
		return consultaCarta(0, 0, carta, null);
	}

	@SuppressWarnings("unchecked")
	public List<ProgramaCartasV> consultaCarta(Integer idPrograma, Integer idComprador, String carta, Integer idEspecialista) throws Exception, JDBCException {
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		StringBuilder where= new StringBuilder();
		List<ProgramaCartasV> lst=null;
		if ( carta!=null && !carta.equals("")){
			where.append(" and no_carta = '").append(carta).append("'");
		}
		if (idEspecialista != null) {
			where.append(" and id_especialista =").append(idEspecialista);
		}
		if (idPrograma != 0 && idPrograma != -1){
			where.append(" and id_programa=").append(idPrograma);
		}
		if (idComprador != 0 && idComprador != -1){
			where.append(" and id_comprador=").append(idComprador);
		}

		subConsulta.append("select distinct no_carta from pagos_v ");
		consulta.insert(0,"select * from programa_cartas_v where folio_carta_adhesion in (").append(subConsulta.toString()).append(")").append(where.toString()).append(" ORDER BY folio_carta_adhesion");
		lst = session.createSQLQuery(consulta.toString()).addEntity(ProgramaCartasV.class).list();
		//lst= session.createQuery(consulta.toString()).list();		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<ProgramaCartasV> consultaCartasByPrograma(int idPrograma, int idComprador) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		StringBuilder subConsulta= new StringBuilder();
		StringBuilder where= new StringBuilder();
		List<ProgramaCartasV> lst=null;
		if (idPrograma != 0 && idPrograma != -1){
			where.append(" and id_programa = ").append(idPrograma);
		}
		if (idComprador != 0 && idComprador != -1){
			where.append(" and id_comprador=").append(idComprador);
		}

		subConsulta.append("select distinct no_carta from pagos_v ");
		consulta.insert(0,"select * from programa_cartas_v where folio_carta_adhesion in (").append(subConsulta.toString()).append(")").append(where.toString()).append(" ORDER BY folio_carta_adhesion");
		lst = session.createSQLQuery(consulta.toString()).addEntity(ProgramaCartasV.class).list();
		//consulta.insert(0, "From PagosV ").append(" ORDER BY noCarta");
		//lst= session.createQuery(consulta.toString()).list();	
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstatusCartaAdhesion> consultaEstatusCartaAdhesion(String idEstatusCA)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstatusCartaAdhesion> lst = null;
		
		if (idEstatusCA != null && !idEstatusCA.isEmpty()){
			consulta.append(" where idEstatusCA in (").append(idEstatusCA).append(")");			
		}
		
		consulta.insert(0, "From EstatusCartaAdhesion ").append(" order by descripcion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}

	public List<BodegasV> consultaBodegasV(String claveBodega)throws JDBCException{
		return consultaBodegasV(claveBodega, -1, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<BodegasV> consultaBodegasV(String claveBodega, int idEstado, String nombreBodega)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<BodegasV> lst = null;
		if(claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega ='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega ='").append(claveBodega).append("'");
			}
		}
		if(idEstado != 0 && idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado =").append(idEstado);
			}else{
				consulta.append("where idEstado =").append(idEstado);
			}
		}
		if (nombreBodega != null && !nombreBodega.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and nombre LIKE '%").append(nombreBodega).append("%' ");
			} else {
				consulta.append(" where nombre LIKE '%").append(nombreBodega.toUpperCase()).append("%' ");
			}
		}
		consulta.insert(0, "From BodegasV ").append(" order by claveBodega");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstadosCivil> consultaEstadoCivil(int estadoCivil) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstadosCivil> lst=null;
		if (estadoCivil != 0 && estadoCivil != -1){
			consulta.append("where estadoCivil = ").append(estadoCivil);
		}
		consulta.insert(0, "From EstadosCivil ").append(" ORDER BY descripcion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<TiposIdentificacion> consultaTipoIdentificacion(int tipoIdentificacion) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<TiposIdentificacion> lst=null;
		if (tipoIdentificacion != 0 && tipoIdentificacion != -1){
			consulta.append("where tipoIdentificacion = ").append(tipoIdentificacion);
		}
		consulta.insert(0, "From TiposIdentificacion ").append(" ORDER BY descripcion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<TiposVialidad> consultaTipoVialidad(int tipoVialidad) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<TiposVialidad> lst=null;
		if (tipoVialidad != 0 && tipoVialidad != -1){
			consulta.append("where tipoVialidad = ").append(tipoVialidad);
		}
		consulta.insert(0, "From TiposVialidad ").append(" ORDER BY descripcion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<TiposAsentamiento> consultaTipoAsentamiento(int tipoAsentHumano) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<TiposAsentamiento> lst=null;
		if (tipoAsentHumano != 0 && tipoAsentHumano != -1){
			consulta.append("where tipoAsentHumano = ").append(tipoAsentHumano);
		}
		consulta.insert(0, "From TiposAsentamiento ").append(" ORDER BY descripcion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<EstatusSeguimiento> consultaEstatusSeguimiento(int idEstatus) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstatusSeguimiento> lst=null;
		if (idEstatus != 0 && idEstatus != -1){
			consulta.append("where idEstatus = ").append(idEstatus);
		}
		consulta.insert(0, "From EstatusSeguimiento ").append(" ORDER BY descripcion");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<EstadosSeguimientoV> consultaEstadosSeguimiento(int idEstado)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstadosSeguimientoV> lst = null;
		if(idEstado!=0 && idEstado != -1){
			consulta.append("where idEstado = ").append(idEstado);
		}
		consulta.insert(0, "From EstadosSeguimientoV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<EstadosSeguimientoV> isolatedConsultaEstadosSeguimiento(int idEstado) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstadosSeguimientoV> lst = null;
		Session s = null;
		try {
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if(idEstado!=0 && idEstado != -1){
				consulta.append("where idEstado = ").append(idEstado);
			}
			consulta.insert(0, "From EstadosSeguimientoV ");
			lst= s.createQuery(consulta.toString()).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<CultivosSeguimientoV> consultaCultivosSeguimiento(int idCultivo)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CultivosSeguimientoV> lst = null;
		if(idCultivo!=0 && idCultivo != -1){
			consulta.append("where idCultivo = ").append(idCultivo);
		}
		consulta.insert(0, "From CultivosSeguimientoV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<CultivosSeguimientoV> isolatedConsultaCultivosSeguimiento(int idCultivo)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CultivosSeguimientoV> lst = null;
		Session s = null;
		try {
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if(idCultivo!=0 && idCultivo != -1){
				consulta.append("where idCultivo = ").append(idCultivo);
			}
			consulta.insert(0, "From CultivosSeguimientoV ");
			lst= s.createQuery(consulta.toString()).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<CiclosSeguimientoV> consultaCiclosSeguimiento(int idCiclo)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CiclosSeguimientoV> lst = null;
		if(idCiclo!=0 && idCiclo != -1){
			consulta.append("where idCiclo = ").append(idCiclo);
		}
		consulta.insert(0, "From CiclosSeguimientoV ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<CiclosSeguimientoV> isolatedConsultaCiclosSeguimiento(int idCiclo)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CiclosSeguimientoV> lst = null;
		Session s = null;
		try {
			s = com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
			if(idCiclo!=0 && idCiclo != -1){
				consulta.append("where idCiclo = ").append(idCiclo);
			}
			consulta.insert(0, "From CiclosSeguimientoV ");
			lst= s.createQuery(consulta.toString()).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(s!=null && s.isOpen()){
				s.close();
			}
		}			
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<CapacidadesBodegas> consultaCapacidadBodega(String claveBodega)throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CapacidadesBodegas> lst = null;
		if(claveBodega != null && !claveBodega.isEmpty()){
			if(consulta.length()>0){
				consulta.append(" and claveBodega ='").append(claveBodega).append("'");
			}else{
				consulta.append("where claveBodega ='").append(claveBodega).append("'");
			}
		}
		consulta.insert(0, "From CapacidadesBodegas ").append(" order by claveBodega");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
		
	}

	//CATALOGO DE COMPRADORES
	public List<Comprador> consultaComprador(int idComprador) throws Exception,JDBCException {
		return consultaComprador(idComprador, null);
	}
	
	public List<Comprador> consultaComprador(int idComprador, Integer estatus)
		throws Exception, JDBCException {
		return consultaComprador(idComprador, estatus, "", "");
	}

	@SuppressWarnings("unchecked")
	public List<Comprador> consultaComprador(int idComprador, Integer estatus,String nombre, String rfc) throws JDBCException {
		StringBuilder consulta = new StringBuilder();
		List<Comprador> lst = null;
		if (idComprador != 0 && idComprador != -1) {
			consulta.append("where idComprador = ").append(idComprador);
		}
		if (estatus != null) {
			if (consulta.length() > 0) {
				consulta.append(" and estatus =").append(estatus);
			} else {
				consulta.append("where estatus=").append(estatus);
			}
		
		}
		if (nombre != null && !nombre.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and nombre LIKE '%").append(nombre).append("%' ");
			} else {
				consulta.append(" where nombre LIKE '%").append(nombre.toUpperCase()).append("%' ");
			}
		}
		if (rfc != null && !rfc.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and rfc LIKE '%").append(rfc).append("%' ");
			} else {
				consulta.append(" where rfc LIKE '%").append(rfc.toUpperCase()).append("%' ");
			}
		}
		consulta.insert(0, "From Comprador ").append(" ORDER BY nombre");
		lst = session.createQuery(consulta.toString()).list();
		return lst;
	}	

	@SuppressWarnings("unchecked")
	public List<CompradoresV> consultaCompradoresV(Integer idComprador)
			throws JDBCException {
		StringBuilder consulta = new StringBuilder();

		List<CompradoresV> lst = null;
		if (idComprador != 0 && idComprador != -1) {
			consulta.append("where idComprador = ").append(idComprador);
		}

		consulta.insert(0, "From CompradoresV ")
				.append(" order by idComprador");
		lst = session.createQuery(consulta.toString()).list();

		return lst;
	}
	
	//consulta catalogo de caders
	@SuppressWarnings("unchecked")
	public List<CadersV> consultaCadersV(long idCader, int idEstado, long ddr, long cader) throws Exception, JDBCException {
		StringBuilder consulta= new StringBuilder();
		
		List<CadersV> lst=null;
		
		if (idCader != 0 && idCader != -1) {
			consulta.append("where idCader = ").append(idCader);
		}
		
		if(idEstado != 0 && idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado =").append(idEstado);
			}else{
				consulta.append("where idEstado =").append(idEstado);
			}
		}
		
		if(ddr != 0 && ddr != -1){
			if(consulta.length()>0){
				consulta.append(" and ddr =").append(ddr);
			}else{
				consulta.append("where ddr =").append(ddr);
			}
		}
		
		if(cader != 0 && cader!= -1){
			if(consulta.length()>0){
				consulta.append(" and cader =").append(cader);
			}else{
				consulta.append("where cader =").append(cader);
			}
		}
		consulta.insert(0, "From CadersV ");
		lst = session.createQuery(consulta.toString()).list();
				
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Ejido> consultaEjido(long idEjido, int idEstado){
		List<Ejido> lst=null;
		StringBuilder consulta= new StringBuilder();
		
		if (idEjido != 0 && idEjido != -1) {
			consulta.append("where idEjido = ").append(idEjido);
		}
		
		if(idEstado != 0 && idEstado != -1){
			if(consulta.length()>0){
				consulta.append(" and idEstado =").append(idEstado);
			}else{
				consulta.append("where idEstado =").append(idEstado);
			}
		}
		
		consulta.insert(0, "From Ejido ");
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BodegaUso> consultaBodegaUsuo(String clave){
		List<BodegaUso> lst=null;
		StringBuilder consulta= new StringBuilder();
		
		if (clave != null  && !clave.isEmpty()) {
			consulta.append("where clave = '").append(clave).append("'");
		}	
		
		consulta.insert(0, "From BodegaUso ");
		lst = session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Estado> consultaEstado(String idEstado) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Estado> lst=null;
		if (idEstado != null && !idEstado.equals("")){
			consulta.append("where idEstado in (").append(idEstado).append(")");
		}
		consulta.insert(0, "From Estado ").append(" ORDER BY nombre");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}	

	public int validaPeriodoSeguimientoExistente(String bodega, int idCiclo, int ejercicio, int idCultivo, int idVariedad, String periodo) throws Exception{ 
		String query;
		BigInteger resp=null;
		try {
			query = "select count(*) from seguimiento_centro_acopio "+
					" where "+periodo+" between to_number(to_char(periodo_inicial, 'YYYYMMDD'), '99999999') and to_number(to_char(periodo_final, 'YYYYMMDD'), '99999999')"+
					" and id_ciclo = "+idCiclo+
					" and ejercicio = "+ejercicio+
					" and id_cultivo = "+idCultivo+
					" and id_variedad = "+idVariedad+
					" and clave_bodega = '"+bodega+"'";
			resp = (BigInteger) session.createSQLQuery(query).list().get(0);
			
		}catch(JDBCException e){
			e.printStackTrace();
		}
		return resp.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<EstatusOficio> consultaEstatusOficio() throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<EstatusOficio> lst=null;
		
		consulta.append("From EstatusOficio ").append(" ORDER BY descripcionStatus");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	/**Funciones de Relaciones**/
	public List<TipoDocumentoPago> cosultaTipoDocumentoPagos(long idTipoDocumentoPago) throws JDBCException {
		return cosultaTipoDocumentoPagos(idTipoDocumentoPago, null);
	}
	@SuppressWarnings("unchecked")
	public List<TipoDocumentoPago> cosultaTipoDocumentoPagos(long idTipoDocumentoPago, String tipoPago) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<TipoDocumentoPago> lst=null;
		if (idTipoDocumentoPago != 0 && idTipoDocumentoPago != -1){
			consulta.append("where idTipoDocumentoPago = ").append(idTipoDocumentoPago);
		}
		if (tipoPago != null && !tipoPago.isEmpty()) {
			if (consulta.length() > 0) {
				consulta.append(" and tipoPago = '").append(tipoPago).append("' ");
			} else {
				consulta.append(" where tipoPago ='").append(tipoPago).append("' ");
			}
		}
		consulta.insert(0, "From TipoDocumentoPago ");
		lst= session.createQuery(consulta.toString()).list();
		
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<Modalidad> consultaModalidad(long idModalidad) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<Modalidad> lst=null;
		if (idModalidad != 0 && idModalidad != -1){
			consulta.append("where idModalidad = ").append(idModalidad);
		}
		consulta.insert(0, "From Modalidad ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<CatCriteriosValidacion> consultaCatCriteriosValidacion(int idCriterio) throws JDBCException {
		StringBuilder consulta= new StringBuilder();
		List<CatCriteriosValidacion> lst=null;
		if (idCriterio != 0 && idCriterio != -1){
			consulta.append("where idCriterio = ").append(idCriterio);
		}
		consulta.insert(0, "From CatCriteriosValidacion ");
		lst= session.createQuery(consulta.toString()).list();
		return lst;
		
	}
}