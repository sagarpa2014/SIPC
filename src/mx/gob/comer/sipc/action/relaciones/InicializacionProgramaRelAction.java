package mx.gob.comer.sipc.action.relaciones;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Ejercicios;
import mx.gob.comer.sipc.domain.catalogos.Ciclo;
import mx.gob.comer.sipc.domain.transaccionales.IniEsquemaRelacion;
import mx.gob.comer.sipc.domain.transaccionales.ProgramaRelacionCiclos;
import mx.gob.comer.sipc.domain.transaccionales.ProgramaRelacionCultivos;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.IniEsquemaRelacionV;
import mx.gob.comer.sipc.vistas.domain.ProgramasRelacionV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InicializacionProgramaRelAction extends ActionSupport implements SessionAware {
	
	private Map<String, Object> session;	
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private RelacionesDAO rDAO = new RelacionesDAO();
	private List<Ciclo> lstCiclos;
	private List<Ejercicios> lstEjercicios;
	private List<Cultivo> lstCultivo;	
	private List<ProgramaRelacionCiclos> lstProgramaRelacionCiclos;
	private List<ProgramaRelacionCultivos> lstProgramaRelacionCultivos;
	private int idCultivo;	
	private int idCiclo;
	private int idEjercicio;
	private int idRelacion;
	private int registrar;
	private long idPerRel;
	private boolean bandConfiguracionYaRegistrada;	
	private int numCiclos;
	private int numCultivos;
	private String selecCiclo;
	private String selecAnio;
	private String cicloAgricola;
	private String nombreCultivos;
	private String fechaInicio;
	private String fechaFin;
	private String msjOk;	
	private String selecCultivo;
	private String nombreEsquema;
	private Long idIniEsquemaRelacion;
	//listas antes de publicar aviso oficialmente
	private List<IniEsquemaRelacionV> lstIniEsquemaRelacionV; 
	private List<IniEsquemaRelacion> lstIniEsquemaRelacionCriterio;
	//listas despues de publicar aviso oficialmente
	private List<ProgramasRelacionV> lstProgramasRelacionV;
	
	private int numCultivosAnterior;
	private int numCiclosAnterior;
	private int tipoCaptura;
	
	public String listProgramaRelacion(){
		try{	
			fechaInicio = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
			fechaFin = new SimpleDateFormat("yyyy").format(new Date())+"-12-31";
			if(tipoCaptura == 0){ //Antes de publicar aviso oficialmente 
				lstIniEsquemaRelacionCriterio = rDAO.consultaIniEsquemaRelacion(0,null,null,null);
				
				lstIniEsquemaRelacionV = rDAO.consultaIniEsquemaRelacionV(0,null, fechaInicio, fechaFin);
			}else{//Despues de publicar aviso oficialmente
				lstProgramasRelacionV = rDAO.consultaProgramasRelacionV(0, null, fechaInicio, fechaFin);
			}
						
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listProgramaRelacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listProgramaRelacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}
	
	

	public String getDetalleProgramaRelacion(){
		try{	
			lstIniEsquemaRelacionCriterio = rDAO.consultaIniEsquemaRelacion(idIniEsquemaRelacion);
			nombreEsquema = lstIniEsquemaRelacionCriterio.get(0).getNombreEsquema(); 	
			lstProgramaRelacionCultivos = rDAO.consultaProgramaRelacionCultivos(idIniEsquemaRelacion);
			numCultivos = lstProgramaRelacionCultivos.size(); 
			lstProgramaRelacionCiclos = rDAO.consultaProgramaRelacionCiclos(idIniEsquemaRelacion);
			numCiclos = lstProgramaRelacionCiclos.size(); 
			lstEjercicios = cDAO.consultaEjercicio(0);
			lstCiclos = cDAO.consultaCiclo(0);
			lstCultivo = cDAO.consultaCultivo();
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en getDetalleProgramaRelacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en getDetalleProgramaRelacion  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
		
	}
	
	
	public String ejecutaBusquedaProgramaRelaciones(){	
		lstIniEsquemaRelacionCriterio = rDAO.consultaIniEsquemaRelacion(0,null,null,null);
		lstIniEsquemaRelacionV = rDAO.consultaIniEsquemaRelacionV(idIniEsquemaRelacion);		
		return SUCCESS;
	}
		
	public String capProgramaRelacion(){
		try{			
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en capProgramaRelacion  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listPersonalizacionRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}
	
	
	public String registroInicializaEsquemaRel(){
		try{	
			IniEsquemaRelacion ier = null;
			String [] selecCicloArray = selecCiclo.split(",");
			String [] selecAnioArray = selecAnio.split(",");
			cicloAgricola= integrarCiclo(selecCicloArray, selecAnioArray);
			setNombreCultivos(integrarCultivo());
			
			if(registrar == 0){
				ier = new IniEsquemaRelacion();
			}else if(registrar == 3){
				ier = rDAO.consultaIniEsquemaRelacion(idIniEsquemaRelacion).get(0);
				ier.setFechaModificacion(new Date());
			}
			ier.setNombreEsquema(nombreEsquema);
			ier.setCicloAgricola(cicloAgricola);
			ier.setCultivos(nombreCultivos);
			ier.setFechaCreacion(new Date());
			ier = (IniEsquemaRelacion) cDAO.guardaObjeto(ier);
			idIniEsquemaRelacion = ier.getIdIniEsquemaRelacion();
			if(registrar == 3){
				//Borrar configuracion de ciclos y cultivos en el esquema
				rDAO.borrarCiclosEsquemaRel(idIniEsquemaRelacion);
				rDAO.borrarCultivosEsquemaRel(idIniEsquemaRelacion);
				
			}
			guardarCiclosIniEsquema(idIniEsquemaRelacion);
			guardarCultivosIniEsquema(idIniEsquemaRelacion);
			setMsjOk("Se registró satisfactoriamente la información");
			getDetalleProgramaRelacion();
			registrar = 2;
		}catch(JDBCException e){
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registroInicializaEsquemaRel  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registroInicializaEsquemaRel  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}
	
	
	public String consigueNumCiclos(){		
		lstProgramaRelacionCiclos = new ArrayList<ProgramaRelacionCiclos>();
		if(registrar == 0){
			for(int i=1; i<=numCiclos; i++){
				lstProgramaRelacionCiclos.add(new ProgramaRelacionCiclos());
			}
		}else if(registrar == 3){
			lstProgramaRelacionCiclos = rDAO.consultaProgramaRelacionCiclos(idIniEsquemaRelacion);				
			numCiclosAnterior = lstProgramaRelacionCiclos.size();
			if(numCiclos  > numCiclosAnterior){
				int resta = 0;
				resta = numCiclos - numCiclosAnterior;
				for(int i =1; i<=resta; i++){
					lstProgramaRelacionCiclos.add(new ProgramaRelacionCiclos());
				}
			}else if(numCiclos < numCiclosAnterior){
				List<ProgramaRelacionCiclos> lstProgramaRelacionCiclosTmp = new ArrayList<ProgramaRelacionCiclos>();
				for (int i=1; i<=numCiclos ; i++){
					lstProgramaRelacionCiclosTmp.add(lstProgramaRelacionCiclos.get(i-1));
				}
				lstProgramaRelacionCiclos = new ArrayList<ProgramaRelacionCiclos>();
				lstProgramaRelacionCiclos = lstProgramaRelacionCiclosTmp;
			}	
		}
		
		lstEjercicios = cDAO.consultaEjercicio(0);
		lstCiclos = cDAO.consultaCiclo(0);
		return SUCCESS;
	}
	
	public String consigueNumCultivos(){
		lstProgramaRelacionCultivos = new ArrayList<ProgramaRelacionCultivos>();
		if(registrar == 0){
			for(int i=1; i<=numCultivos; i++){
				lstProgramaRelacionCultivos.add(new ProgramaRelacionCultivos());
			}			
		}else if(registrar == 3){
			lstProgramaRelacionCultivos = rDAO.consultaProgramaRelacionCultivos(idIniEsquemaRelacion);				
			numCultivosAnterior = lstProgramaRelacionCultivos.size();
			if(numCultivos > numCultivosAnterior){
				int resta = 0;
				resta = numCultivos - numCultivosAnterior;
				for(int i =1; i<=resta; i++){
					lstProgramaRelacionCultivos.add(new ProgramaRelacionCultivos());
				}
			}else if(numCultivos < numCultivosAnterior){
				List<ProgramaRelacionCultivos> lstProgramaRelacionCultivosTmp = new ArrayList<ProgramaRelacionCultivos>();
				for (int i=1; i<=numCultivos; i++){
					lstProgramaRelacionCultivosTmp.add(lstProgramaRelacionCultivos.get(i-1));
				}
				lstProgramaRelacionCultivos = new ArrayList<ProgramaRelacionCultivos>();
				lstProgramaRelacionCultivos = lstProgramaRelacionCultivosTmp;
			}			
			
		}
		
		lstCultivo = cDAO.consultaCultivo();
		return SUCCESS;
	}
	
	
	private String integrarCiclo(String[] selecCiclo, String[] selecAnio){
		//Integracion de Ciclo
		StringBuilder cCicloL = new StringBuilder();
		int sizeArrayCiclo = selecCiclo.length;
		if(sizeArrayCiclo>1){
			for (int i = 0; i < sizeArrayCiclo; i++) {
				if(i != sizeArrayCiclo-1){
					if(selecCiclo[i].equals("1")){ 
						cCicloL.append("Primavera-Verano ").append(selecAnio[i]).append(", ");
					}else{
						cCicloL.append("Otoño-Invierno ").append(selecAnio[i]).append("/").append(Integer.parseInt(selecAnio[i].trim())+1).append(", ");						
					}
				}
			}					
			cCicloL.deleteCharAt(cCicloL.length() - 2);			
			if(selecCiclo[sizeArrayCiclo-1].equals("1")){
				cCicloL.append("y ").append("Primavera-Verano ").append(selecAnio[sizeArrayCiclo-1]);
			}else{
				cCicloL.append("y ").append("Otoño-Invierno ").append(selecAnio[sizeArrayCiclo-1]).append("/").append(Integer.parseInt(selecAnio[sizeArrayCiclo-1].trim())+1);
			}		
		}else{
			if(selecCiclo[0].equals("1")){
				cCicloL.append("Primavera-Verano"+" "+selecAnio[0]);
			}else{
				cCicloL.append("Otoño-Invierno"+" "+selecAnio[0]);
			}
		}	
		
		return cCicloL.toString();
	}//

	private String integrarCultivo() {
		String [] selecCultivoArrary = selecCultivo.split(",");
		String nombreCultivos = "";		
		for(int i=0; i<selecCultivoArrary.length; i++){		
			nombreCultivos+=cDAO.consultaCultivo(selecCultivoArrary[i]).get(0).getCultivo()+",";		
		}
		nombreCultivos = Utilerias.conformaCadena(nombreCultivos.split(","));
		System.out.println("nombreCultivos : "+nombreCultivos);
		
		return nombreCultivos;
	}

	private void guardarCultivosIniEsquema(long idIniEsquemaRelacion) {
		String [] selecCultivoArrary = selecCultivo.split(",");
		for(int i=0; i < selecCultivoArrary.length; i++){
			ProgramaRelacionCultivos prc = new ProgramaRelacionCultivos();
			prc.setIdCultivo(Integer.parseInt(selecCultivoArrary[i].trim()));
			prc.setIdIniEsquemaRelacion(idIniEsquemaRelacion);
			cDAO.guardaObjeto(prc);
		} 
	}

	private void guardarCiclosIniEsquema(long idIniEsquemaRelacion) {
		String [] selecCicloArray = selecCiclo.split(",");
		String [] selecEjercicioArray = selecAnio.split(",");
		for(int i=0; i<selecCicloArray.length; i++){
			ProgramaRelacionCiclos prc= new ProgramaRelacionCiclos();
			prc.setIdCiclo(Integer.parseInt(selecCicloArray[i].trim()));
			prc.setIdIniEsquemaRelacion(idIniEsquemaRelacion);
			prc.setEjercicio(Integer.parseInt(selecEjercicioArray[i].trim()));
			cDAO.guardaObjeto(prc);
		}
	}

	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public List<Ciclo> getLstCiclos() {
		return lstCiclos;
	}

	public void setLstCiclos(List<Ciclo> lstCiclos) {
		this.lstCiclos = lstCiclos;
	}
	
	public List<Ejercicios> getLstEjercicios() {
		return lstEjercicios;
	}
	
	public void setLstEjercicios(List<Ejercicios> lstEjercicios) {
		this.lstEjercicios = lstEjercicios;
	}
	
	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}
	
	
	public int getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}

	public int getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(int idCiclo) {
		this.idCiclo = idCiclo;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public int getIdRelacion() {
		return idRelacion;
	}

	public void setIdRelacion(int idRelacion) {
		this.idRelacion = idRelacion;
	}


		

	public int getRegistrar() {
		return registrar;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	
	public boolean isBandConfiguracionYaRegistrada() {
		return bandConfiguracionYaRegistrada;
	}

	public void setBandConfiguracionYaRegistrada(
			boolean bandConfiguracionYaRegistrada) {
		this.bandConfiguracionYaRegistrada = bandConfiguracionYaRegistrada;
	}
	
	
	public int getNumCiclos() {
		return numCiclos;
	}

	public void setNumCiclos(int numCiclos) {
		this.numCiclos = numCiclos;
	}

	public String getSelecCiclo() {
		return selecCiclo;
	}

	public void setSelecCiclo(String selecCiclo) {
		this.selecCiclo = selecCiclo;
	}

	public String getSelecAnio() {
		return selecAnio;
	}

	public void setSelecAnio(String selecAnio) {
		this.selecAnio = selecAnio;
	}

	public String getCicloAgricola() {
		return cicloAgricola;
	}

	public void setCicloAgricola(String cicloAgricola) {
		this.cicloAgricola = cicloAgricola;
	}

	public long getIdPerRel() {
		return idPerRel;
	}

	public void setIdPerRel(long idPerRel) {
		this.idPerRel = idPerRel;
	}

	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}

	public int getNumCultivos() {
		return numCultivos;
	}

	public void setNumCultivos(int numCultivos) {
		this.numCultivos = numCultivos;
	}

	

	public String getSelecCultivo() {
		return selecCultivo;
	}

	public void setSelecCultivo(String selecCultivo) {
		this.selecCultivo = selecCultivo;
	}

	public String getNombreCultivos() {
		return nombreCultivos;
	}
	
	public void setNombreCultivos(String nombreCultivos) {
		this.nombreCultivos = nombreCultivos;
	}


	public String getNombreEsquema() {
		return nombreEsquema;
	}


	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}

	
	public List<ProgramaRelacionCiclos> getLstProgramaRelacionCiclos() {
		return lstProgramaRelacionCiclos;
	}

	public void setLstProgramaRelacionCiclos(
			List<ProgramaRelacionCiclos> lstProgramaRelacionCiclos) {
		this.lstProgramaRelacionCiclos = lstProgramaRelacionCiclos;
	}

	public List<ProgramaRelacionCultivos> getLstProgramaRelacionCultivos() {
		return lstProgramaRelacionCultivos;
	}

	public void setLstProgramaRelacionCultivos(
			List<ProgramaRelacionCultivos> lstProgramaRelacionCultivos) {
		this.lstProgramaRelacionCultivos = lstProgramaRelacionCultivos;
	}


	public Long getIdIniEsquemaRelacion() {
		return idIniEsquemaRelacion;
	}

	public void setIdIniEsquemaRelacion(Long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}

	
	public List<IniEsquemaRelacionV> getLstIniEsquemaRelacionV() {
		return lstIniEsquemaRelacionV;
	}
	
	public void setLstIniEsquemaRelacionV(
			List<IniEsquemaRelacionV> lstIniEsquemaRelacionV) {
		this.lstIniEsquemaRelacionV = lstIniEsquemaRelacionV;
	}

	public List<IniEsquemaRelacion> getLstIniEsquemaRelacionCriterio() {
		return lstIniEsquemaRelacionCriterio;
	}


	public void setLstIniEsquemaRelacionCriterio(
			List<IniEsquemaRelacion> lstIniEsquemaRelacionCriterio) {
		this.lstIniEsquemaRelacionCriterio = lstIniEsquemaRelacionCriterio;
	}

	public int getTipoCaptura() {
		return tipoCaptura;
	}

	public void setTipoCaptura(int tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	public List<ProgramasRelacionV> getLstProgramasRelacionV() {
		return lstProgramasRelacionV;
	}

	public void setLstProgramasRelacionV(
			List<ProgramasRelacionV> lstProgramasRelacionV) {
		this.lstProgramasRelacionV = lstProgramasRelacionV;
	}
	
	
}
