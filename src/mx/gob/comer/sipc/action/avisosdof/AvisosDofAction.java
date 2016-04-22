package mx.gob.comer.sipc.action.avisosdof;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.gob.comer.sipc.dao.AvancePagosDAO;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Ejercicios;
import mx.gob.comer.sipc.domain.EjerciciosAgricola;
import mx.gob.comer.sipc.domain.Estado;
import mx.gob.comer.sipc.domain.catalogos.ApoyoAviso;
import mx.gob.comer.sipc.domain.catalogos.ProgAviso;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDof;
import mx.gob.comer.sipc.domain.transaccionales.AvisosDofDetalle;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.AvisosDofDetalleV;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AvisosDofAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable{
	
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	private CatalogosDAO cDAO = new CatalogosDAO();
	private AvancePagosDAO aDAO = new AvancePagosDAO();
	private List<EjerciciosAgricola> lstEjercicios;
	private List<ProgAviso> lstPgrAviso;
	private List<Estado> lstEstados;
	private List<ApoyoAviso> lstApoyoAviso;
	private List<Cultivo> lstCultivo;
	private List<AvisosDofDetalleV> lstAvisosDofDetalleV;
	private List<AvisosDofDetalle> lstAvisosDofDetalle;
	private AvisosDofDetalleV add;
	private String programa;
	private String estado;
	private AvisosDof av;
	private AvisosDofDetalle avd;
	private AvisosDof avisosDof;
	private AvisosDofDetalle avisosDofDetalle;
	private String leyenda;
	
	private int registrar;	
	private int idAvisoDof;
	private Map<String, Object> session;
	private int error;
	private List<AvisosDof> lstAvisosDof;
	private Boolean bandera;
	private String cultivo;
	private String apoyo;
	private int errorDetalleRepetido;
	private int errorAvisoYaCapturado;
	private int registrosBorrados;
	private int id;
	private int avanceCapturados;
//	private String ciclo;
//	private Integer anio;
	private String cicloAgricola;
	private int count;
	
	
	public String ltsAvisosDof(){
		try{			
			lstAvisosDof = aDAO.getAvisosDof(null, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	public String buscarAvisoDof(){
		try{			
			lstAvisosDof = aDAO.getAvisosDof(null, new SimpleDateFormat("yyyy-MM-dd").format(av.getFechaPublicacion()));
			bandera = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	public String capturaAvisosDof(){
		try{
			if(registrar != 0){
				//consigue el aviso
				av = aDAO.getAvisosDof(idAvisoDof,null).get(0);
				lstAvisosDofDetalleV = aDAO.getAvisosDofDetalleV(idAvisoDof,0,0,0,null,0,0);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String registrarAvisosDof(){
		try{		
			//Utilerias.getResponseISO();
			session = ActionContext.getContext().getSession();
			cicloAgricola = avd.getCiclo()+ avd.getAnio().toString().substring(2,4);
			if(registrar == 0){
				 //Verifica si la clave de aviso no se ha capturado
				if( aDAO.getAvisosDof(-1, new SimpleDateFormat("yyyy-MM-dd").format(av.getFechaPublicacion())).size()>0){
					errorAvisoYaCapturado = 1;
					return SUCCESS;
				}				
				 avisosDof = new AvisosDof();
				 avisosDof.setFechaRegistro(new Date());
				 avisosDof.setUsuarioRegistro((Integer) session.get("idUsuario"));
				 avisosDof.setAvisoDofDetalle(new HashSet<AvisosDofDetalle>());
			}else{
				avisosDof = aDAO.getAvisosDof(av.getId(),null).get(0);
				avisosDof.setFechaActualizacion(new Date());
				avisosDof.setUsuarioActualizacion((Integer) session.get("idUsuario"));				
				//Verifica que no se haya capturado la llave unica (id, apoyo, ciclo agricola, programa, cultivo y estado)
//				lstAvisosDofDetalle =  aDAO.getAvisosDofDetalle(av.getId(), avd.getIdPrograma(), avd.getIdApoyo(),avd.getIdCultivo(),
//						cicloAgricola, avd.getIdEstado(), 0);
//				if(lstAvisosDofDetalle.size() > 0){
//					errorDetalleRepetido = 1;
//					return SUCCESS;
//				}
			}		
			avisosDof.setLeyenda(leyenda);
			avisosDof.setFechaPublicacion(av.getFechaPublicacion());
			if(registrar == 0 || registrar == 1){
				avisosDofDetalle = new AvisosDofDetalle();
				lstAvisosDofDetalle =  aDAO.getAvisosDofDetalle(0, avd.getIdPrograma(), avd.getIdApoyo(),avd.getIdCultivo(),
						cicloAgricola, avd.getIdEstado(), 0);
				if(lstAvisosDofDetalle.size() > 0){
					errorDetalleRepetido = 1;
					return SUCCESS;
				}
			}else{				
				lstAvisosDofDetalle =  aDAO.getAvisosDofDetalle(0,0,0,0,null,0,id);
				avisosDofDetalle = lstAvisosDofDetalle.get(0);
				if(avisosDofDetalle.getIdPrograma().intValue() == avd.getIdPrograma().intValue() 
						&& avisosDofDetalle.getIdApoyo().intValue() == avd.getIdApoyo().intValue()
						&& avisosDofDetalle.getIdCultivo().intValue() == avd.getIdCultivo().intValue()
						&& avisosDofDetalle.getIdEstado().intValue() == avd.getIdEstado().intValue()
						&& avisosDofDetalle.getCiclo().equals(avd.getCiclo())
						&& avisosDofDetalle.getAnio().intValue() == avd.getAnio().intValue()
						){					
					System.out.println("Coincide con los mismos parametros");
				}else{
					//Verificar que no exista otro registro con los mismos parametros
					lstAvisosDofDetalle =  aDAO.getAvisosDofDetalle(0, avd.getIdPrograma(), avd.getIdApoyo(),avd.getIdCultivo(),
							cicloAgricola, avd.getIdEstado(), 0);
					if(lstAvisosDofDetalle.size() > 0){
						errorDetalleRepetido = 1;
						return SUCCESS;
					}
				}
			}			
			
			avisosDofDetalle.setIdPrograma(avd.getIdPrograma());
			avisosDofDetalle.setIdApoyo(avd.getIdApoyo());
			avisosDofDetalle.setIdCultivo(avd.getIdCultivo());
			avisosDofDetalle.setCicloAgricola(cicloAgricola);
			avisosDofDetalle.setCiclo(avd.getCiclo());
			avisosDofDetalle.setAnio(avd.getAnio());
			avisosDofDetalle.setIdEstado(avd.getIdEstado());
			avisosDofDetalle.setVolumen(avd.getVolumen());
			avisosDofDetalle.setImporte(avd.getImporte());		
			avisosDofDetalle.setVolumenRegional(avd.getVolumenRegional());
			avisosDofDetalle.setImporteRegional(avd.getImporteRegional());
			avisosDof.getAvisoDofDetalle().add(avisosDofDetalle);			
			av = (AvisosDof) cDAO.guardaObjeto(avisosDof);	
			//Consulta el detalle para recuperar nombre de programa, estado, cultivo y apoyo
			//if(registrar == 0){
				lstAvisosDofDetalleV =  aDAO.getAvisosDofDetalleV(av.getId(), avd.getIdPrograma(), avd.getIdApoyo(),avd.getIdCultivo(),
						avd.getCicloAgricola(), avd.getIdEstado(), 0);
			//}			
			programa = lstAvisosDofDetalleV.get(0).getPrograma();
			estado = lstAvisosDofDetalleV.get(0).getEstado();
			cultivo = lstAvisosDofDetalleV.get(0).getCultivo();
			apoyo = lstAvisosDofDetalleV.get(0).getApoyo();
			error = 0;
			if(registrar == 0 || registrar == 1){
				registrar = 1;
			}else{
				registrar = 2;
			}
			 
		}catch(Exception e){			 
			error = 1;
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	public String getTablaAvisosDof(){
		try{	
			//Consigue la informacion de la tabla de aviso
			List<Ejercicios> lstEjerciciosOriginal = cDAO.consultaEjercicio(0);
			lstEjercicios = new ArrayList<EjerciciosAgricola>();			
			for(Ejercicios e: lstEjerciciosOriginal){
				lstEjercicios.add(new EjerciciosAgricola(e.getEjercicio(), e.getEjercicio().toString()));
			}		
			
			lstPgrAviso = cDAO.getProgAviso(0, null);
			lstEstados = cDAO.consultaEstado(0);
			lstApoyoAviso = cDAO.getApoyoAviso(0);
			lstCultivo = cDAO.consultaCultivo();
			if(registrar == 2){
				//Consigue registro de aviso a traves de id
				add = aDAO.getAvisosDofDetalleV(id).get(0);	
				if(add.getCiclo().equals("OI")){
					Integer ejercicioSiguiente  = 0;
					for(Ejercicios e: lstEjerciciosOriginal){
						ejercicioSiguiente = e.getEjercicio()+1;
						lstEjercicios.add(new EjerciciosAgricola(e.getEjercicio(), e.getEjercicio() +" / "+ejercicioSiguiente.toString()));
						
					}
				}
			}
		}catch(JDBCException e) {	
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	
	
	public String eliminarDetalleAviso(){	
		try {
			//Verifica que no se haya captura un avance con esta configuracion
			lstAvisosDofDetalleV =  aDAO.getAvisosDofDetalleV(id);
			AvisosDofDetalleV ad = lstAvisosDofDetalleV.get(0); 
			avanceCapturados  = aDAO.getAvanceExistente(ad.getIdAvisosDof(), ad.getIdProgAviso(), ad.getIdCultivo(), ad.getIdEstado(), ad.getCicloAgricola());
			if(avanceCapturados > 0){
				registrosBorrados = -1;
			}else{
				registrosBorrados = aDAO.borrarDetalleAviso(id);	
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	public String recuperaAnio(){	
		List<Ejercicios> lstEjerciciosOriginal = cDAO.consultaEjercicio(0);
		lstEjercicios = new ArrayList<EjerciciosAgricola>();
		if(avd.getCiclo().equals("PV") || avd.getCiclo().equals("-1") ){
			for(Ejercicios e: lstEjerciciosOriginal){
				lstEjercicios.add(new EjerciciosAgricola(e.getEjercicio(), e.getEjercicio().toString()));
			}
		}else{//OI
			Integer ejercicioSiguiente  = 0;
			for(Ejercicios e: lstEjerciciosOriginal){
				ejercicioSiguiente = e.getEjercicio()+1;
				lstEjercicios.add(new EjerciciosAgricola(e.getEjercicio(), e.getEjercicio() +" / "+ejercicioSiguiente.toString()));
				
			}
		}
		
		return SUCCESS;
	}
	
		
	public List<EjerciciosAgricola> getLstEjercicios() {
		return lstEjercicios;
	}

	public void setLstEjercicios(List<EjerciciosAgricola> lstEjercicios) {
		this.lstEjercicios = lstEjercicios;
	}

	public List<ProgAviso> getLstPgrAviso() {
		return lstPgrAviso;
	}

	public void setLstPgrAviso(List<ProgAviso> lstPgrAviso) {
		this.lstPgrAviso = lstPgrAviso;
	}
	
	public List<Estado> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Estado> lstEstados) {
		this.lstEstados = lstEstados;
	}	

	public List<ApoyoAviso> getLstApoyoAviso() {
		return lstApoyoAviso;
	}

	public void setLstApoyoAviso(List<ApoyoAviso> lstApoyoAviso) {
		this.lstApoyoAviso = lstApoyoAviso;
	}	
	
	
	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}
		
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public AvisosDof getAv() {
		return av;
	}

	public void setAv(AvisosDof av) {
		this.av = av;
	}

	public AvisosDofDetalle getAvd() {
		return avd;
	}

	public void setAvd(AvisosDofDetalle avd) {
		this.avd = avd;
	}	

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
	
	
	public List<AvisosDof> getLstAvisosDof() {
		return lstAvisosDof;
	}

	public void setLstAvisosDof(List<AvisosDof> lstAvisosDof) {
		this.lstAvisosDof = lstAvisosDof;
	}

	public Boolean getBandera() {
		return bandera;
	}

	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}
	
	
	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}	

	public List<AvisosDofDetalleV> getLstAvisosDofDetalleV() {
		return lstAvisosDofDetalleV;
	}

	public void setLstAvisosDofDetalleV(List<AvisosDofDetalleV> lstAvisosDofDetalleV) {
		this.lstAvisosDofDetalleV = lstAvisosDofDetalleV;
	}

	
	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	

	public int getErrorDetalleRepetido() {
		return errorDetalleRepetido;
	}

	public void setErrorDetalleRepetido(int errorDetalleRepetido) {
		this.errorDetalleRepetido = errorDetalleRepetido;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {	
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		
	}

	public int getRegistrosBorrados() {
		return registrosBorrados;
	}

	public void setRegistrosBorrados(int registrosBorrados) {
		this.registrosBorrados = registrosBorrados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getErrorAvisoYaCapturado() {
		return errorAvisoYaCapturado;
	}

	public void setErrorAvisoYaCapturado(int errorAvisoYaCapturado) {
		this.errorAvisoYaCapturado = errorAvisoYaCapturado;
	}	

	public int getIdAvisoDof() {
		return idAvisoDof;
	}

	public void setIdAvisoDof(int idAvisoDof) {
		this.idAvisoDof = idAvisoDof;
	}

	public int getAvanceCapturados() {
		return avanceCapturados;
	}

	public void setAvanceCapturados(int avanceCapturados) {
		this.avanceCapturados = avanceCapturados;
	}

		public String getApoyo() {
		return apoyo;
	}

	public void setApoyo(String apoyo) {
		this.apoyo = apoyo;
	}

	public String getCicloAgricola() {
		return cicloAgricola;
	}

	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}

	public AvisosDofDetalleV getAdd() {
		return add;
	}

	public void setAdd(AvisosDofDetalleV add) {
		this.add = add;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}	
	
	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) throws UnsupportedEncodingException {
		this.leyenda = Utilerias.convertirISO88591aUTF8(leyenda);
	}

	

}
