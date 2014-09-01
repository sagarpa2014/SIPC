package mx.gob.comer.sipc.action.solicitudpago;

import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.SolicitudPagoDAO;
import mx.gob.comer.sipc.domain.Bancos;
import mx.gob.comer.sipc.domain.Comprador;
import mx.gob.comer.sipc.domain.CuentaBancaria;
import mx.gob.comer.sipc.domain.PlazasBancarias;
import mx.gob.comer.sipc.domain.transaccionales.CartaAdhesion;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.CuentasBancariasV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EstadoDeCuentaAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private SolicitudPagoDAO spDAO = new SolicitudPagoDAO();
	private String folioCartaAdhesion;
	private int idComprador;
	private Comprador comprador;
	private String clabe;
	private String ctaBancaria;
	private PlazasBancarias plaza;
	private List<CuentasBancariasV> lstCuentaBancariasV;
	private List<PlazasBancarias> lstPlazas;
	private String sucursal;
	private List<Bancos> bancos;
	private String nombreBanco;
	private String tipoRespuesta;
	private String msjError;
	private int errorClabeInvalida;
	private int errorConfClabeInvalida;
	private String msjOk;
	private int registrar;
	private CuentaBancaria cuentaBancaria;
	private String cuenta;
	private Integer otrac;
	

	public String capEstadoCuenta(){
		try{
			//Recupera el comprador a través de la carta Adhesión
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			if(ca.getClabe() != null){
				registrar =  0; 
				clabe = ca.getClabe();
				recuperaDatosPlaza();
			}
			idComprador = ca.getIdComprador();
			// Recupera los datos del comprador
			comprador = cDAO.consultaComprador(idComprador).get(0);
			/*Recupera las cuentas bancarias asociadas al comprador*/
			lstCuentaBancariasV = cDAO.consultaCtaBancariaV(idComprador,1,null);
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en recuperaDatosCartaAdhesion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaDatosCartaAdhesion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}		
		return SUCCESS;		
	}
	
	
	public String capturarOtraCuenta(){

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
		} catch (Exception e) {
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
	
	public String registraCuentaBancaria(){
		try{
			if(otrac == null || otrac == 1){//capturo una nueva cuenta, y se debe guardar en las cuentas del comprador
				clabe = cuenta;
				cuentaBancaria = new CuentaBancaria();
				cuentaBancaria.setBancoId(Integer.parseInt(clabe.substring(1,3)));
				cuentaBancaria.setClabe(clabe);
				cuentaBancaria.setEstatus(1);
				cuentaBancaria.setIdComprador(idComprador);
				cuentaBancaria.setNoPlaza(clabe.substring(3,6));
				cuentaBancaria.setNumeroCuenta(clabe.substring(6,17));
				cuentaBancaria.setSucursal(sucursal);
				cDAO.guardaObjeto(cuentaBancaria);				
			}else{
				/*puede que haya capturado solo la sucursal en caso de que no cuente con ella, se procede a actualizar la cuenta bancaria 
				y se asocia a la carta de adhesion*/
				if(sucursal!=null && !sucursal.isEmpty()){
					cuentaBancaria = cDAO.consultaCtaBancaria(clabe).get(0);
					cuentaBancaria.setSucursal(sucursal);
					cDAO.guardaObjeto(cuentaBancaria);
					
				}
			}
		
			//Se asocia la cuenta a la carta de adhesion
			CartaAdhesion ca = spDAO.consultaCartaAdhesion(folioCartaAdhesion).get(0);
			//Recupera la carta de adhesion
			ca.setClabe(clabe);
			cDAO.guardaObjeto(ca);
			capEstadoCuenta();
			msjOk = "Se registró satisfactoriamente el registro";
					
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
	
	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	
	public Comprador getComprador() {
		return comprador;
	}
	
	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	
	public int getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public String getCtaBancaria() {
		return ctaBancaria;
	}

	public void setCtaBancaria(String ctaBancaria) {
		this.ctaBancaria = ctaBancaria;
	}

	public PlazasBancarias getPlaza() {
		return plaza;
	}

	public void setPlaza(PlazasBancarias plaza) {
		this.plaza = plaza;
	}

	public List<PlazasBancarias> getLstPlazas() {
		return lstPlazas;
	}

	public void setLstPlazas(List<PlazasBancarias> lstPlazas) {
		this.lstPlazas = lstPlazas;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public List<Bancos> getBancos() {
		return bancos;
	}

	public void setBancos(List<Bancos> bancos) {
		this.bancos = bancos;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(String tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public String getMsjError() {
		return msjError;
	}


	public void setMsjError(String msjError) {
		this.msjError = msjError;
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

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}


	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	public Integer getOtrac() {
		return otrac;
	}

	public void setOtrac(Integer otrac) {
		this.otrac = otrac;
	}

	public List<CuentasBancariasV> getLstCuentaBancariasV() {
		return lstCuentaBancariasV;
	}

	public void setLstCuentaBancariasV(List<CuentasBancariasV> lstCuentaBancariasV) {
		this.lstCuentaBancariasV = lstCuentaBancariasV;
	}


	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}
}
