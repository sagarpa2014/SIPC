package mx.gob.comer.sipc.pagos.action;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.PagosDAO;
import mx.gob.comer.sipc.domain.Bancos;
import mx.gob.comer.sipc.domain.Bitacora;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.CuentaBancaria;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.Pagos;
import mx.gob.comer.sipc.domain.PagosDetalle;
import mx.gob.comer.sipc.domain.PlazasBancarias;
import mx.gob.comer.sipc.domain.Programa;
import mx.gob.comer.sipc.domain.ProgramaComprador;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.PagosDetalleV;
import mx.gob.comer.sipc.vistas.domain.PagosV;
import mx.gob.comer.sipc.vistas.domain.ProgramaCompradorV;
import mx.gob.comer.sipc.vistas.domain.ProgramaEstadoV;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
*Pagos Action
*
*Version 1.0
*
*Enero 2013
*
*/
@SuppressWarnings("serial")
public class PagosAction extends ActionSupport implements SessionAware, Serializable{
	private Map<String, Object> session;
	private List<PagosDetalle> lstPagosDetalle;
	//private List<PagosDetalleV> lstPagosDetalleV;
	private List<Estado> lstEstados;
	private List<Programa> lstProgramas;
	private List<Comprador> lstComprador;
	private List<CuentaBancaria> lstCuentaBancarias;
	private List<ProgramaEstadoV> lstProgramaEdoV;
	private List<PagosDetalleV> lstPagosDetalleV;
	private Comprador comprador;
	/*variables del formulario*/
	private int idPrograma;
	private int idComprador;
	private int tieneCuenta;
	private int tieneRfc;
	private int otrac;
	private int numCampos;
	private int personaFiscal;
	private int estadoPlaza;
	private String cuenta;
	private String rfc;
	private String curp;
	private String clabe;
	private String nombrePlaza;
	private Integer[] selectedEdos;
	private Double[] capVolumen;
	private Double[] capImporte;
	private String[] capEtapa;
	private String tipoRespuesta;
	private int errorClabeInvalida;
	private int errorConfClabeInvalida;
	private int errorNoCarta;
	private PlazasBancarias plaza;
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private PagosDAO pDAO = new PagosDAO();
	private String nombreEstado;
	private String noCarta;
	private int errorProgSinEdos;
	private String nombreBanco;
	private String sucursal;
	private CuentaBancaria cuentaBancaria;
	private List<ProgramaComprador> lstPrgoComp;
	private List<PlazasBancarias> lstPlazas;
	private List<Bancos> bancos;
	private String msjError;
	private String msjOk;
	private Pagos p;
	private String log;
	private List<ProgramaCompradorV> lstComByPrograma;
	private String ctaBancaria;
	private PagosV pagosV;
	private Long idPago;
	private int editar;
	private int criterioPago;
	private String etapa;
	
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;	
	/**
	 *  Acción que verifica el acceso de los usuarios y consulta el menu de acuerdo al perfil del mismo.
	 * 
	 * @throws JDBCException Si ocurre un error de base de datos al consultar el acceso de usuarios
	 * 
	 */
	public String capturaPagos(){
		try{	
			Utilerias.getResponseISO();
			recuperaCatalogos(null, "P", "C");
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String recuperaEdoCompradoresByPrograma(){
		//Recupera los estados asociados al programa*/
		lstPagosDetalle = new ArrayList<PagosDetalle>();
		try {
			//Recuperando lista de estados
			/*lstProgramaEdoV = cDAO.consultaEdoXPrograma(idPrograma, -1);
			if(lstProgramaEdoV.size()>0){
				for(int i=1; i<=numCampos; i++){
					lstPagosDetalle.add(new PagosDetalle());
				}	
			}*/
			//Recupera los compradores que tienen asociado un el programa en la tabla tabla programa_compradores*/
			/***Modificar por los compradores que existen en la tabla de programa_compradores**/
			//lstComprador = cDAO.consultaComprador(0,1);
			lstComByPrograma = cDAO.consultaCompradorByPrograma(idPrograma);
			//Recupera los criterios del pago si es por volumen o por etapa
			criterioPago = cDAO.consultaPrograma(idPrograma).get(0).getCriterioPago();
			
			
		} catch (JDBCException e) {	
			e.printStackTrace();
		} 
		
		return SUCCESS;
	}

	
	/**
	 * Recupera las cuentas bancarias, datos fiscales del comprador y las cartas asociadas al programa 
	 * y comprador seleccionado
	 *  
	 * @throws JDBCException Si ocurre un error al conseguir los catálogos en la base de datos 
	 * 
	 */
	public String datosComprador(){
		try{
			
			/*Recupera los datos del comprador*/
			if(idComprador != -1){
				if(idPrograma != -1){
				/* Recupera las cartas asociadas al programa y comprador seleccionados por el usuario*/
					 lstPrgoComp = cDAO.consultaProgComprador(idComprador, idPrograma);
					/*if(lstPrgoComp.size()>0){
						noCarta = lstPrgoComp.get(0).getNoCarta();
					}else{
						addActionError("No es posible capturar el pago, no existe el número de carta en el sistema favor de reportar al administrador");
						errorNoCarta = 1;
						return SUCCESS;
					}*/
				}
				/*Recupera las cuentas bancarias asociadas al comprador*/
				lstCuentaBancarias = cDAO.consultaCtaBancaria(idComprador,1);
				/*Recupera los datos fiscales del comprador*/
				comprador = cDAO.consultaComprador(idComprador).get(0);
				//errorNoCarta = 0;
			}
		}catch (JDBCException e) {
			addActionError("Ocurrio un error al guardar en base de datos, favor de reportar al administrador");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * Recupera los datos de la cuenta bancaria seleccionada por el usuario 
	 *  
	 * @throws JDBCException Si ocurre un error al conseguir los catálogos en la base de datos 
	 * 
	 */
	public String recuperaDatosPlaza(){
		try{
			plaza = new PlazasBancarias();
			lstPlazas = cDAO.consultaPlaza(clabe.substring(3,6));
			if(lstPlazas.size()>0){
				plaza =lstPlazas.get(0);
			}else{
				AppLogger.error("errores","No se encuentra los datos de la plaza asociado a la cuenta bancaria");
			}
			cuenta = clabe.substring(6,17);
			ctaBancaria = clabe.substring(6,17);
			/*Verifica que la sucursal se encuentre en la cuenta bancaria*/
			List<CuentaBancaria> cb = cDAO.consultaCtaBancaria(clabe);
			if(cb.size()>0){
				sucursal =cb.get(0).getSucursal();
			}
			
			bancos = cDAO.consultaBanco(Integer.parseInt(clabe.substring(1,3)));
			if(bancos.size()>0){
				nombreBanco = bancos.get(0).getNombre();	
			}else{
				AppLogger.error("errores","No se encuentra el banco asociado a la cuenta bancaria");
			}
		}catch (JDBCException e) {
			addActionError("Ocurrio un error inesperado al consultar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrió un error al consultar los datos de la plaza debido a:"+e.getCause());
			e.printStackTrace();
		}catch (Exception e) {
			addActionError("Ocurrio un error inesperado al consultar los datos de la plaza, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrió un error al consultar los datos de la plaza debido a:"+e.getCause());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 *  
	 * 
	 * @throws 
	 * 
	 */
	public String agregaEdoPorVolumen(){	
		lstPagosDetalle = new ArrayList<PagosDetalle>();
		System.out.println("Criterio "+criterioPago);
		try {
			//Recuperando lista de estados
			lstProgramaEdoV = cDAO.consultaEdoXPrograma(idPrograma, -1);
			if(editar ==1){
				lstPagosDetalle = pDAO.consultaPagosDetalle(0, idPago);
				int numCamposAnterior = lstPagosDetalle.size();
				
				if(numCampos > numCamposAnterior){//
					int resta = 0;
					resta = numCampos - numCamposAnterior;
					for(int i = 1; i<=resta; i++){ //se agregan a la lista de  los valores de los pagos detalle que actulemente tiene el pago + los que determine el usuario
						
						lstPagosDetalle.add(new PagosDetalle());
					}
				}else if(numCampos < numCamposAnterior){//si el numCampos es menor a los que  actualmente tiene la central 
					List<PagosDetalle> lstPagosDetalleTmp = new ArrayList<PagosDetalle>();
					for (int i=1; i<=numCampos; i++){
						lstPagosDetalleTmp.add(lstPagosDetalle.get(i-1));
					}
					lstPagosDetalle = new ArrayList<PagosDetalle>();
					lstPagosDetalle = lstPagosDetalleTmp;
				}
			}else{
				if(lstProgramaEdoV.size()>0){
					for(int i=1; i<=numCampos; i++){
						lstPagosDetalle.add(new PagosDetalle());
					}
				}
			}
			
			
		} catch (JDBCException e) {	
			addActionError("Ocurrió un error inesperado al consultar los estados asociados al programa, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrió un error al consultar los estados asociados al programa debido a:"+e.getCause());
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	public String validarCuentaBancaria(){
		tipoRespuesta ="clabeInvalida";
		try{
			if(clabe.length()!=18){
				msjError = "La clabe debe ser de 18 digitos";
				errorClabeInvalida = 1;
				return SUCCESS;
			}else{
				if(!clabe.matches("^\\d*$")){
					msjError = "La cuenta es incorrecta";
					errorClabeInvalida = 1;
					return SUCCESS;
				}
				cuenta = clabe.substring(6,17);
				/* Verifica que la cuenta no exista */
				if(cDAO.consultaCtaBancaria(clabe).size()>0){
					msjError = "La clabe ya existe, favor de verificar";
					errorClabeInvalida = 1;
					return SUCCESS;
				}
				
				/*Valida que exista el banco*/
				List<Bancos> bancos = cDAO.consultaBanco(Integer.parseInt(clabe.substring(1,3)));
				if(bancos.size()==0){
					msjError = "El banco no se encuentra en el catalogo del sistema, favor de informar al administrador";
					errorClabeInvalida = 1;
					return SUCCESS;
				}
				nombreBanco = bancos.get(0).getNombre();
				/*Valida que exista la plaza y en caso de no existir, inserta en la base de datos*/

				lstPlazas = cDAO.consultaPlaza(clabe.substring(3,6));
				if(lstPlazas.size()== 0){
					msjError = "La plaza bancaria no se encuentra en el catalogo del sistema, favor de informar al administrador";
					errorClabeInvalida = 1;
					//recuperaCatalogos("e", null, null);
					return SUCCESS;
					//nombreEstado = cDAO.consultaEstado(plaza.getEstadoId()).get(0).getNombre();
				}
				plaza = lstPlazas.get(0);
				if(cDAO.validaClabeInterbancaria(clabe)==0){
					msjError = "El digito verificador es incorrecto, por favor verifique";
					errorClabeInvalida = 1;
					return SUCCESS;
				}
			}
			errorClabeInvalida = 0;
			msjOk = "Cuenta correcta";
		}catch (JDBCException e) {
			addActionError("Ocurrio un error inesperado al consultar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores", "Ocurrió un error al validar la cuenta bancaria debido a :"+e.getCause());
			e.printStackTrace();
		}catch(Exception e){
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			AppLogger.error("errores", "Ocurrió un error al validar la cuenta bancaria debido a :"+e.getCause());
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String validarConfirmacionCtaBancaria(){
		tipoRespuesta = "confClabeInvalida";
		/** Valida el digito verificador una vez que captura la confirmación de la clabe interbancaria*/
		try {
			if(!cuenta.equals(clabe)){
				errorConfClabeInvalida = 1;
				msjError ="La cuentas no coindicen";
				return SUCCESS;
			}else{
				lstPlazas = cDAO.consultaPlaza(clabe.substring(3,6));	
				plaza = lstPlazas.get(0);
				List<Bancos> bancos = cDAO.consultaBanco(Integer.parseInt(clabe.substring(1,3)));
				nombreBanco = bancos.get(0).getNombre();
				ctaBancaria = clabe.substring(6,17);
			}
			errorConfClabeInvalida = 0;
			msjOk="Cuenta correcta";
		} catch (Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String agregarEditarPago() throws Exception{
		try{
			session = ActionContext.getContext().getSession();
			/* Guarda los datos del pago*/
			//pago = new Pagos();
			
			if(tieneCuenta == 1){//El comprador tiene cuentas asociados
				verificarCuentaBancaria();
			}else{
				clabe = cuenta;
				guardaCuentaBancaria();
			}
			if(tieneRfc==0){
				//Recupera datos del comprador a través de su id
				comprador = cDAO.consultaComprador(idComprador).get(0);
				//Actualiza rfc de comprador
				comprador.setTipoPersona((personaFiscal==1?"F":"M"));
				comprador.setRfc(rfc);
				comprador.setCurp(curp);
				cDAO.guardaObjeto(comprador);
			}
			guardarPagos();
			session = ActionContext.getContext().getSession();
			
			PagosV p = new PagosV();
			p = pDAO.consultaPagosV(idPago).get(0);
			
			
			log = "Se le informa que se capturó un pago del Programa: "+p.getNombrePgrCorto().toUpperCase()+" - Comprador: "+p.getNombreComprador().toUpperCase()
				   + " - Carta: "+p.getNoCarta()+ " - clabe:"+ p.getClabe()
				   +" - importe: $"+ TextUtil.formateaNumeroComoCantidad(p.getImporte())+ (p.getVolumen()!=null ? " - volumen:"+ TextUtil.formateaNumeroComoVolumen(p.getVolumen()):"")+
				   (p.getEtapa()!=null ? " - Etapa: "+p.getEtapa():"" );
			cDAO.guardaObjeto(new Bitacora((Integer) session.get("idUsuario"), 3, new Date(),log));
			/*Enviar aviso de la captura de pago*/
			EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
			mensajes.enviarMensaje((Integer) session.get("idUsuario"), 3, log, "Aviso");
			msjOk = "Se agrego correctamente el pago";
			idPrograma = -1;
			
		}catch (JDBCException e) {
			addActionError("Ocurrio un error al guardar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrio un error al guardar el pago en base de datos debido a: "+e.getCause());
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrio un error inesperado al guardar el pago debido a: "+e.getCause());
		}finally{					
			try {
				recuperaCatalogos(null, "P", "C");
			} catch (JDBCException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	private void verificarCuentaBancaria() throws JDBCException, Exception {
		if(otrac == 1){//capturo una nueva cuenta, y se debe guardar en las cuentas del comprador
			clabe = cuenta;
			guardaCuentaBancaria();
		}else{
			//puede que haya capturado solo la sucursal en caso de que no cuente con ella, se procede a actualizar la cuenta bancaria*/
			if(sucursal!=null && !sucursal.isEmpty()){
				cuentaBancaria = cDAO.consultaCtaBancaria(clabe).get(0);
				cuentaBancaria.setSucursal(sucursal);
				cDAO.guardaObjeto(cuentaBancaria);
			}
		}
		
	}
	
	public String eliminarPago(){
		try {
			session = ActionContext.getContext().getSession();
			p = pDAO.consultaPagos(idPago).get(0);
			cDAO.borrarObjeto(p);
			recuperaCatalogos(null, "P", "C");
			msjOk="Se eliminó satisfactoriamente el pago";
			log = "El usuario: "+(String) session.get("nombreUsuario")+" eliminó el pago "+idPago +" satisfactoriamente";
			AppLogger.info("app",log);
		}catch (JDBCException e) {
			addActionError("Error al eliminar el pago");
			AppLogger.error("errores", "Ocurrio un error al eliminar el pago debido a:"+e.getCause());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return SUCCESS;
	}

	public String actualizarPago(){
		try{
			session = ActionContext.getContext().getSession();
			verificarCuentaBancaria();
			actualizaRegistroPagos();
			capturaEdicionPago();
			msjOk = "El pago se actualizó correctamente";
		}catch (JDBCException e) {
			addActionError("Ocurrio un error al guardar en base de datos, favor de reportar al administrador");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		return SUCCESS;
		
	}
			
	private void actualizaRegistroPagos() {
		Pagos pagoAnterior = null;
		double totalImporte =0;
		double totalVolumen =0;
		//consigue el pago
		p = pDAO.getPagos(idPago);
		pagoAnterior= new Pagos(p); 
		//borra el detalle pagos asociados al pago
		pDAO.borraDetallePagos(idPago);
		p.setClabe(clabe);
		p.setPagosDetalle(new HashSet<PagosDetalle>());
		for (int i = 0; i < selectedEdos.length; i++) {
			PagosDetalle pd = new PagosDetalle();
			pd.setIdEstado(selectedEdos[i]);
			pd.setVolumen(capVolumen[i]);
			pd.setImporte(capImporte[i]);
			p.getPagosDetalle().add(pd);
			totalImporte += capImporte[i];
			totalVolumen += capVolumen[i];
		
		}
		p.setVolumen(totalVolumen);
		p.setImporte(totalImporte);
		p.setFechaModificacion(new Date());
		p.setUsuarioModificacion((Integer) session.get("idUsuario"));
		cDAO.guardaObjeto(p);
		log = "El usuario: "+(String) session.get("nombreUsuario")+" actualizó el pago "+pagoAnterior.toString()+ " por: "+p.toString()+" satisfactoriamente";
		AppLogger.info("app", log);
		cDAO.guardaObjeto(new Bitacora((Integer) session.get("idUsuario"),4, new Date(), (log.length()>500?log.substring(0, 500):log)));	
	}

	private void guardaCuentaBancaria()throws JDBCException, Exception{
		cuentaBancaria = new CuentaBancaria();
		cuentaBancaria.setBancoId(Integer.parseInt(clabe.substring(1,3)));
		cuentaBancaria.setClabe(clabe);
		cuentaBancaria.setEstatus(1);
		cuentaBancaria.setIdComprador(idComprador);
		cuentaBancaria.setNoPlaza(clabe.substring(3,6));
		cuentaBancaria.setNumeroCuenta(clabe.substring(6,17));
		cuentaBancaria.setSucursal(sucursal);
		
		cDAO.guardaObjeto(cuentaBancaria);
		
	}


	private void guardarPagos() throws JDBCException, Exception{
		double totalImporte =0;
		double totalVolumen =0;
		p = new Pagos();
		p.setClabe(clabe);
		p.setEstatus(1);
		p.setFechaCreacion(new Date());
		p.setIdComprador(idComprador);
		p.setIdPrograma(idPrograma);
		p.setNoCarta(noCarta);
		p.setPagosDetalle(new HashSet<PagosDetalle>());
		for (int i = 0; i < selectedEdos.length; i++) {
			PagosDetalle pd = new PagosDetalle();
			pd.setIdEstado(selectedEdos[i]);
			if(criterioPago==1 || criterioPago ==3){
				pd.setVolumen(capVolumen[i]);
			}
			if(criterioPago==2 || criterioPago ==3){
				pd.setEtapa(capEtapa[i]);
			}
			
			pd.setImporte(capImporte[i]);
			p.getPagosDetalle().add(pd);
			totalImporte += capImporte[i];
			if(criterioPago==1 || criterioPago ==3){
				totalVolumen += capVolumen[i];
			}
		}
		
		if(criterioPago==1 || criterioPago ==3){
			p.setVolumen(totalVolumen);
		}
		if(criterioPago==2 || criterioPago ==3){
			p.setEtapa(etapa);
		}
		
		p.setImporte(totalImporte);
		p.setUsuarioCreacion((Integer) session.get("idUsuario"));
		idPago = cDAO.guardaPagos(p);
	}

	
	public String capturarOtraCuenta(){

		return SUCCESS;
	}
	
	public String capturaEdicionPago(){
		try{
			Utilerias.getResponseISO();
			pagosV = pDAO.consultaPagosV(idPago).get(0);
			//recupera las cuentas bancarias del productor
			lstCuentaBancarias = cDAO.consultaCtaBancaria(pagosV.getIdComprador(),1);
			//recupera el detallle de lops pagos x estado
			lstPagosDetalle = pDAO.consultaPagosDetalle(0, idPago);
			/*Recupera los datos del detalle del pago*/
			lstPagosDetalleV = pDAO.consultaPagosDetalleV(0,idPago);
			//recupera los estados asociados al programa
			lstProgramaEdoV = cDAO.consultaEdoXPrograma(pagosV.getIdPrograma(), -1);
			numCampos = lstPagosDetalle.size();
			editar = 1;
			
		}catch(Exception e){
			e.printStackTrace();
			AppLogger.error("errores","Ocurrió un error inesperado al obtener los datos del pago");
			
			
		}
	
		return SUCCESS;
	}
	
	
		
	private void recuperaCatalogos(String edos, String pgrs, String compradores) throws JDBCException, Exception {
		
		if(edos!=null && !edos.equals("")){
			lstEstados = cDAO.consultaEstado(0);
		}
		if(pgrs!=null && !pgrs.equals("")){
			lstProgramas = cDAO.consultaPrograma(0);
		}
		if(compradores!=null && !compradores.equals("")){
			lstComprador = cDAO.consultaComprador(0,1);
		}
		
	}
	
	public List<PagosDetalle> getLstPagosDetalle() {
		return lstPagosDetalle;
	}

	public void setLstPagosDetalle(List<PagosDetalle> lstPagosDetalle) {
		this.lstPagosDetalle = lstPagosDetalle;
	}
	
	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}
	
	public List<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(List<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public List<Comprador> getLstComprador() {
		return lstComprador;
	}

	public void setLstComprador(List<Comprador> lstComprador) {
		this.lstComprador = lstComprador;
	}

	public List<CuentaBancaria> getLstCuentaBancarias() {
		return lstCuentaBancarias;
	}

	public void setLstCuentaBancarias(List<CuentaBancaria> lstCuentaBancarias) {
		this.lstCuentaBancarias = lstCuentaBancarias;
	}

	public List<ProgramaEstadoV> getLstProgramaEdoV() {
		return lstProgramaEdoV;
	}

	public void setLstProgramaEdoV(List<ProgramaEstadoV> lstProgramaEdoV) {
		this.lstProgramaEdoV = lstProgramaEdoV;
	}

	public List<ProgramaComprador> getLstPrgoComp() {
		return lstPrgoComp;
	}

	public void setLstPrgoComp(List<ProgramaComprador> lstPrgoComp) {
		this.lstPrgoComp = lstPrgoComp;
	}

	public int getNumCampos() {
		return numCampos;
	}

	public void setNumCampos(int numCampos) {
		this.numCampos = numCampos;
	}

	
	public Integer[] getSelectedEdos() {
		return selectedEdos;
	}

	public void setSelectedEdos(Integer[] selectedEdos) {
		this.selectedEdos = selectedEdos;
	}
	
	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}
	
	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	
	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public int getTieneCuenta() {
		return tieneCuenta;
	}

	public void setTieneCuenta(int tieneCuenta) {
		this.tieneCuenta = tieneCuenta;
	}

	public int getTieneRfc() {
		return tieneRfc;
	}

	public void setTieneRfc(int tieneRfc) {
		this.tieneRfc = tieneRfc;
	}

	public int getOtrac() {
		return otrac;
	}

	public void setOtrac(int otrac) {
		this.otrac = otrac;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	public Double[] getCapVolumen() {
		return capVolumen;
	}
	
	public void setCapVolumen(Double[] capVolumen) {
		this.capVolumen = capVolumen;
	}
	
	public Double[] getCapImporte() {
		return capImporte;
	}

	public void setCapImporte(Double[] capImporte) {
		this.capImporte = capImporte;
	}

	public String[] getCapEtapa() {
		return capEtapa;
	}

	public void setCapEtapa(String[] capEtapa) {
		this.capEtapa = capEtapa;
	}

	public int getPersonaFiscal() {
		return personaFiscal;
	}

	public void setPersonaFiscal(int personaFiscal) {
		this.personaFiscal = personaFiscal;
	}
	
	public String getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(String tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public int getErrorClabeInvalida() {
		return errorClabeInvalida;
	}
	
	public void setErrorClabeInvalida(int errorClabeInvalida) {
		this.errorClabeInvalida = errorClabeInvalida;
	}

	public int getErrorConfClabeInvalida() {
		return errorConfClabeInvalida;
	}

	public void setErrorConfClabeInvalida(int errorConfClabeInvalida) {
		this.errorConfClabeInvalida = errorConfClabeInvalida;
	}
	public int getErrorProgSinEdos() {
		return errorProgSinEdos;
	}

	public void setErrorProgSinEdos(int errorProgSinEdos) {
		this.errorProgSinEdos = errorProgSinEdos;
	}

	public int getEstadoPlaza() {
		return estadoPlaza;
	}

	public void setEstadoPlaza(int estadoPlaza)  {
		this.estadoPlaza = estadoPlaza;
	}

	public String getNombrePlaza() {
		return nombrePlaza;
	}

	public void setNombrePlaza(String nombrePlaza) throws UnsupportedEncodingException{
		this.nombrePlaza = Utilerias.convertirISO88591aUTF8(nombrePlaza);
	}
	
	
	public PlazasBancarias getPlaza() {
		return plaza;
	}

	public void setPlaza(PlazasBancarias plaza) {
		this.plaza = plaza;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}



	public String getNombreEstado() {
		return nombreEstado;
	}
	
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	
	public String getCtaBancaria() {
		return ctaBancaria;
	}

	public void setCtaBancaria(String ctaBancaria) {
		this.ctaBancaria = ctaBancaria;
	}

	/**
	 * Implementar la interfaz SessionAware, para session de usuario
	 */ 
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public String getNoCarta() {
		return noCarta;
	}

	public void setNoCarta(String noCarta) {
		this.noCarta = noCarta;
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}

	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public int getErrorNoCarta() {
		return errorNoCarta;
	}

	public void setErrorNoCarta(int errorNoCarta) {
		this.errorNoCarta = errorNoCarta;
	}

	public List<ProgramaCompradorV> getLstComByPrograma() {
		return lstComByPrograma;
	}

	public void setLstComByPrograma(List<ProgramaCompradorV> lstComByPrograma) {
		this.lstComByPrograma = lstComByPrograma;
	}

	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public PagosV getPagosV() {
		return pagosV;
	}

	public void setPagosV(PagosV pagosV) {
		this.pagosV = pagosV;
	}
	
	public List<PagosDetalleV> getLstPagosDetalleV() {
		return lstPagosDetalleV;
	}

	public void setLstPagosDetalleV(List<PagosDetalleV> lstPagosDetalleV) {
		this.lstPagosDetalleV = lstPagosDetalleV;
	}

	public int getEditar() {
		return editar;
	}

	public void setEditar(int editar) {
		this.editar = editar;
	}

	public int getCriterioPago() {
		return criterioPago;
	}

	public void setCriterioPago(int criterioPago) {
		this.criterioPago = criterioPago;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

}
