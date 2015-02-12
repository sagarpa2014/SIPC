package mx.gob.comer.sipc.action.relaciones;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.RelacionesDAO;
import mx.gob.comer.sipc.domain.Cultivo;
import mx.gob.comer.sipc.domain.Ejercicios;
import mx.gob.comer.sipc.domain.catalogos.Ciclo;
import mx.gob.comer.sipc.domain.catalogos.Relaciones;
import mx.gob.comer.sipc.domain.transaccionales.CamposRelacion;
import mx.gob.comer.sipc.domain.transaccionales.CicloPrograma;
import mx.gob.comer.sipc.domain.transaccionales.CriteriosValidacionTransaccional;
import mx.gob.comer.sipc.domain.transaccionales.GruposRelacion;
import mx.gob.comer.sipc.domain.transaccionales.IniEsquemaRelacion;
import mx.gob.comer.sipc.domain.transaccionales.PersonalizacionRelaciones;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.vistas.domain.CriteriosValidacionRelacionDFV;
import mx.gob.comer.sipc.vistas.domain.CriteriosValidacionTransaccionalV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionDefaultV;
import mx.gob.comer.sipc.vistas.domain.GruposCamposRelacionV;
import mx.gob.comer.sipc.vistas.domain.PersonalizacionRelacionesProgramaV;
import mx.gob.comer.sipc.vistas.domain.PersonalizacionRelacionesV;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PersonalizacionRelacionAction extends ActionSupport implements SessionAware {
	
	private Map<String, Object> session;
	
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private RelacionesDAO rDAO = new RelacionesDAO();
	private List<Ciclo> lstCiclos;
	private List<Ejercicios> lstEjercicios;
	private List<Cultivo> lstCultivo;
	private List<Relaciones> lstRelaciones;
	private List<GruposCamposRelacionDefaultV> lstGpoCampoRelDDetalle;
	private List<GruposCamposRelacionDefaultV> lstGpoCampoRelDEncabezado;
	private List<GruposCamposRelacionV> lstGpoCampoRelEncabezadoV;
	private List<GruposCamposRelacionV> lstGpoCampoRelDetalleV;
	private List<PersonalizacionRelacionesV> lstPersonalizacionRelV;
	private List<CicloPrograma> lstCiclosProgramas;
	private int idCultivo;	
	private int idCiclo;
	private int idEjercicio;
	private int idRelacion;
	private Map<Long,Integer> posicionEnc;
	private Map<Long, Integer> posicionDet;
	private Map<Integer, Integer> posicionGrupoDetalle;
	private Integer[] grupoVisible;
	private Integer[] camposOpcional;
	private int registrar;
	private long idPerRel;
	private boolean bandConfiguracionYaRegistrada;	
	//private String fechaInicio;
	//private String fechaFin;
	private String msjOk;
	private GruposRelacion gr;
	private ArrayList<Cultivo> lstCultivos;
	private String selecCultivo;
	private List<IniEsquemaRelacion> lstIniEsquemaRelacion;
	private long idIniEsquemaRelacion;
	private String nombreEsquema;
	
	private long idCampoRelacion;
	private int contador;
	private String [] camposValidacionCriterio;
	private List<CriteriosValidacionRelacionDFV> lstCriteriosValidacionRelacionDFV;
	private List<CriteriosValidacionTransaccionalV> lstTV; 
	private int idGrupo;
	private int idCampo;
	//declaracion de variables cuando el programa se ha publicado oficialmente
	private int tipoCaptura;
	private int idPrograma;
	private List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV;
	private String folioCartaAdhesion;
	
	
	public String listPersonalizacionRelaciones(){
		try{			
			personalizaRelaciones();
			//fechaInicio = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
			//fechaFin = new SimpleDateFormat("yyyy").format(new Date())+"-12-31";
			if(tipoCaptura == 0){
				lstPersonalizacionRelV = rDAO.consultaPersonalizacionRelaciones(0, 0, null, null, null, null, idIniEsquemaRelacion);
			}else{
				lstPersonalizacionRelacionesProgramaV =  rDAO.consultaPersonalizacionRelacionesProgramaV(0, 0, null, null, null, null, idPrograma);
			}
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en listPersonalizacionRelaciones  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en listPersonalizacionRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;
	}
	
	public String ejecutaBusquedaPersonalizacionRelaciones(){
		personalizaRelaciones();
		lstPersonalizacionRelV = rDAO.consultaPersonalizacionRelaciones(0, idRelacion,null, null, null, null, idIniEsquemaRelacion);
		return SUCCESS;
	}
	
	public String getDetallePersonalizacionRel(){
		if(tipoCaptura == 0){
			PersonalizacionRelacionesV prv = rDAO.consultaPersonalizacionRelaciones(idPerRel).get(0);
			idIniEsquemaRelacion = prv.getIdIniEsquemaRelacion();
			idRelacion = prv.getIdTipoRelacion();		
		}else{
			PersonalizacionRelacionesProgramaV prv = rDAO.consultaPersonalizacionRelacionesProgramaV(idPerRel, 0, null, null, null, null, 0).get(0);
			idPrograma = prv.getIdPrograma();
			idRelacion = prv.getIdTipoRelacion();
			System.out.println("Folio Carta ad"+folioCartaAdhesion);
		}
		
		personalizaRelaciones();
		recuperaConfRelacionD();
		return SUCCESS;
	}
	public String personalizaRelaciones(){
		try{	
			//Recupera ciclo y ejercicio
			//setLstEjercicios(cDAO.consultaEjercicio(0));
			//setLstCiclos(cDAO.consultaCiclo(0));
			//setLstCultivo(cDAO.consultaCultivo());
			if(tipoCaptura == 0){
				setNombreEsquema(rDAO.consultaIniEsquemaRelacion(idIniEsquemaRelacion).get(0).getNombreEsquema());
			}else{
				setNombreEsquema(cDAO.consultaPrograma(idPrograma).get(0).getDescripcionCorta());
			}
			
			//setLstIniEsquemaRelacion(rDAO.consultaIniEsquemaRelacion(0,null,null,null));
			setLstRelaciones(rDAO.consultaRelacion(0));
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en personalizaRelaciones  debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en personalizaRelaciones  debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}
		
		return SUCCESS;		
	}

	public String recuperaConfRelacionD(){
		try{	
			if(registrar == 0){
				if(tipoCaptura == 0){
					//Verifica que no exista la configuracion de los parametros(ciclo agricola, cultivo y tipo relacion) en base de datos
					if(rDAO.consultaPersonalizacionRelacion(0, idRelacion, idIniEsquemaRelacion).size()>0){
						setBandConfiguracionYaRegistrada(true);
						return SUCCESS;
					}	
				}else{
					if(rDAO.consultaPersonalizacionRelacionesProgramaV(0, idRelacion, null, null, null, null , idPrograma).size()>0){
						setBandConfiguracionYaRegistrada(true);
						return SUCCESS;
					}
				}
				
			}
			//Grupos y campos de la relacion a nivel detalle
			llenarListaGpoCampoRelEnc(); 
			llenarListaGpoCampoRelDet(); 		
		
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en recuperaConfRelacionD debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en recuperaConfRelacionD debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}		
		return SUCCESS;		
	}
	
	private void llenarListaGpoCampoRelEnc() {
		lstGpoCampoRelDEncabezado = rDAO.consultaGruposCamposRelacionDefaultV(idRelacion, "ENC");
		lstGpoCampoRelEncabezadoV = new ArrayList<GruposCamposRelacionV>();
		GruposCamposRelacionV gcrv = null;
		List<GruposCamposRelacionV> listGrv = new ArrayList<GruposCamposRelacionV>();
		for(GruposCamposRelacionDefaultV e: lstGpoCampoRelDEncabezado ){
			//Recupera los datos de 
			if(registrar == 0){
				lstGpoCampoRelEncabezadoV.add(new GruposCamposRelacionV(e.getIdGpoCampoRelacionD(), e.getIdGrupo(), e.getGrupo(), e.getIdCampo(), e.getCampo()));
			}else if(registrar == 2 || registrar==3){
				//Consigue informacion de registro
				listGrv = rDAO.consultaGruposCampostV(0, idRelacion, idPerRel, e.getIdGrupo(), e.getIdCampo(), "");
				if(listGrv.size()>0){
					gcrv  = listGrv.get(0);
					lstGpoCampoRelEncabezadoV.add(new GruposCamposRelacionV(e.getIdGpoCampoRelacionD(), gcrv.getIdGrupo(), gcrv.getGrupo(), gcrv.getPosicionGrupo(), gcrv.getIdCampo(), gcrv.getCampo(), gcrv.getPosicionCampo(), gcrv.getVisible(), gcrv.getOpcional()));
				}else{
					lstGpoCampoRelEncabezadoV.add(new GruposCamposRelacionV(e.getIdGpoCampoRelacionD(), e.getIdGrupo(), e.getGrupo(), e.getIdCampo(), e.getCampo()));
				}
					
						
				
			}
		}	
	}
	private void llenarListaGpoCampoRelDet() {
		lstGpoCampoRelDDetalle = rDAO.consultaGruposCamposRelacionDefaultV(idRelacion,"DET");
		lstGpoCampoRelDetalleV = new ArrayList<GruposCamposRelacionV>();
		GruposCamposRelacionV gcrv = null;
		List<GruposCamposRelacionV> listGrv = new ArrayList<GruposCamposRelacionV>();
		for(GruposCamposRelacionDefaultV e: lstGpoCampoRelDDetalle ){
			//Recupera los datos de 
			if(registrar == 0){
				lstGpoCampoRelDetalleV.add(new GruposCamposRelacionV(e.getIdGpoCampoRelacionD(), e.getIdGrupo(), e.getGrupo(), e.getIdCampo(), e.getCampo()));
			}else if(registrar == 2 || registrar==3){
				//Consigue informacion de registro
				//listGrv = rDAO.consultaGruposCampostV(idRelacion, idPerRel, e.getIdGrupo(), e.getIdCampo(), -1, "");
				listGrv = rDAO.consultaGruposCampostV(0, idRelacion, idPerRel, e.getIdGrupo(), e.getIdCampo(), "");
				if(listGrv.size()>0){
					gcrv = listGrv.get(0);
					lstGpoCampoRelDetalleV.add(new GruposCamposRelacionV(e.getIdGpoCampoRelacionD(), gcrv.getIdGrupo(), gcrv.getGrupo(), gcrv.getPosicionGrupo(), gcrv.getIdCampo(), gcrv.getCampo(), gcrv.getPosicionCampo(), gcrv.getVisible(), gcrv.getOpcional()));	
				}else{
					lstGpoCampoRelDetalleV.add(new GruposCamposRelacionV(e.getIdGpoCampoRelacionD(), e.getIdGrupo(), e.getGrupo(), e.getIdCampo(), e.getCampo()));
				}
				
			}			
		}
	}

	public String registraPersonalizacionRelacion(){
		try{	

			if(registrar==0){//REGISTRO
				//Guarda la personalizacion de la relacion
				idPerRel = guardarPersonalizacionRelacion();
			}else{//EDICION
				//Recupera lista de grupos_relacion para borrar
				rDAO.borrarPersonalizacionRelacion(idPerRel);
//				List<GruposRelacion> lstGruposRelacion = rDAO.consultaGruposRelacion(idPerRel);
//				for(GruposRelacion gr: lstGruposRelacion){
//					cDAO.borrarObjeto(gr);
//				}				
			}
			
			//Registra los encabezados
			guardarEncabezadosRelacion(idPerRel);
			//Registrar Detalle
			guardarDetalleRelacion(idPerRel);
			registrar = 2;
			setMsjOk("Se registró satisfactoriamente la información");
			
		}catch(JDBCException e) {
	    	e.printStackTrace();
	    	AppLogger.error("errores","Ocurrio un error en registraPersonalizacionRelacion debido a: "+e.getCause());
	    	addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
	    } catch(Exception e) {
			e.printStackTrace();
			AppLogger.error("errores","Ocurrio un error en registraPersonalizacionRelacion debido a: "+e.getMessage());
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
		}finally{
			getDetallePersonalizacionRel();
			//personalizaRelaciones();
			//recuperaConfRelacionD();
		}
		
		return SUCCESS;		
	}
	

	private long guardarPersonalizacionRelacion() throws JDBCException{
			
		PersonalizacionRelaciones pr = new PersonalizacionRelaciones();
		pr.setIdRelacion(idRelacion);
		if(tipoCaptura == 0){
			pr.setIdIniEsquemaRelacion(idIniEsquemaRelacion);
		}else{
			pr.setIdPrograma(idPrograma);
		}
		
		pr.setFechaCreacion(new Date());
		pr = (PersonalizacionRelaciones) cDAO.guardaObjeto(pr);		
		return pr.getIdPerRel();
	}

	private void guardarEncabezadosRelacion(long idPerRel){
		GruposCamposRelacionDefaultV gCampoRelacionD = null;
		Set<Long> posicionEncIt = posicionEnc.keySet();
		Iterator<Long> it =  posicionEncIt.iterator();
		while(it.hasNext()){
			Long idGpoCampoRelacionDEnc = it.next();
			Integer posicion = posicionEnc.get(idGpoCampoRelacionDEnc);
			if(posicion!=null){
				System.out.println("Posicion "+posicion);
				GruposRelacion gr = new GruposRelacion();
				gCampoRelacionD = rDAO.consultaGruposCamposRelacionDefaultV(idGpoCampoRelacionDEnc).get(0);
				gr.setIdGrupo(gCampoRelacionD.getIdGrupo());
				gr.setIdPerRel(idPerRel);				
				gr.setPosicion(posicion);
				gr = (GruposRelacion) cDAO.guardaObjeto(gr);
				//gr.setCamposRelacion(new HashSet<CamposRelacion>());
				CamposRelacion cr = new CamposRelacion();
				cr.setIdCampo(gCampoRelacionD.getIdCampo());
				cr.setPosicion(posicion);
				cr.setIdPerRel(idPerRel);
				cr.setIdGrupoRelacion(gr.getIdGrupoRelacion());
				cr = (CamposRelacion) cDAO.guardaObjeto(cr);
				//gr.getCamposRelacion().add(cr);				
				//cDAO.guardaObjeto(gr);
			}
		}
	}
	
	
	private void guardarDetalleRelacion(long idPerRel) {
		Set<Integer> posicionGrupoDetalleIt = posicionGrupoDetalle.keySet();
		Iterator<Integer> it = posicionGrupoDetalleIt.iterator();
		while(it.hasNext()){
			Integer idGpo = it.next();
			Integer posicion = posicionGrupoDetalle.get(idGpo);
			if(posicion !=null){
				gr = new GruposRelacion();
				gr.setIdPerRel(idPerRel);
				gr.setIdGrupo(idGpo.intValue());
				//Recupera la visibilidad del grupo
				//gr.setVisible(recuperarVisibilidadGrupo(idGpo.intValue()));
				//Recupera la posicion del grupo
				gr.setPosicion(posicion);
				//gr.setCamposRelacion(new HashSet<CamposRelacion>());
				gr = (GruposRelacion) cDAO.guardaObjeto(gr);
				recuperarConfCampo(idGpo);
				
				//cDAO.guardaObjeto(gr);
				
			}
			
			
		}
	}
	

		
	private void recuperarConfCampo(Integer idGpo) {
		Set<Long> posicionDetIt = posicionDet.keySet();
		Iterator<Long> it = posicionDetIt.iterator();		
		GruposCamposRelacionDefaultV gCampoRelacionD = null;
		while(it.hasNext()){
			Long idGpoCampoRelacionDet = it.next();
			Integer posicion = posicionDet.get(idGpoCampoRelacionDet);
			if(posicion!=null){
				gCampoRelacionD = rDAO.consultaGruposCamposRelacionDefaultV(idGpoCampoRelacionDet).get(0);
				if(gCampoRelacionD.getIdGrupo().intValue()==idGpo.intValue()){
					CamposRelacion cr = new CamposRelacion();
					cr.setIdCampo(gCampoRelacionD.getIdCampo());
					cr.setPosicion(posicion);
					cr.setCampoOpcional(recuperarCampoOpcional(idGpoCampoRelacionDet));
					cr.setIdPerRel(idPerRel);
					cr.setIdGrupoRelacion(gr.getIdGrupoRelacion());
					cr = (CamposRelacion) cDAO.guardaObjeto(cr);
					guardarCriteriosValidacion(cr, idGpoCampoRelacionDet);
					//gr.getCamposRelacion().add(cr);		
				}
			}
			

		}
	}
	
	
private void guardarCriteriosValidacion(CamposRelacion cr, Long idGpoCampoRelacionDet) {
		
	int idCriterioValidacionRlDf = 0;
	if(camposValidacionCriterio!=null){
		for(int i = 0; i< camposValidacionCriterio.length; i++){
			String [] camposValidacionCriterioTemp = camposValidacionCriterio[i].split("-");
			idGrupo = Integer.parseInt(camposValidacionCriterioTemp[0]);
			idCampo = Integer.parseInt(camposValidacionCriterioTemp[1]);
			idCampoRelacion = Long.parseLong(camposValidacionCriterioTemp[2]);
			idCriterioValidacionRlDf = Integer.parseInt(camposValidacionCriterioTemp[3]);
			if(idGpoCampoRelacionDet.longValue() == idCampoRelacion){
			
				CriteriosValidacionTransaccional criterio = new CriteriosValidacionTransaccional();
				criterio.setIdCampoRelacion(cr.getIdCampoRelacion());
				criterio.setIdCriterioValidacionRlFk(idCriterioValidacionRlDf);
				criterio.setIdGrupo(idGrupo);
				criterio.setIdCampo(idCampo);
				criterio.setIdPerRel(idPerRel);
				cDAO.guardaObjeto(criterio);
			}	
		}
	}
	
		
}
//	private boolean recuperarVisibilidadGrupo(Integer idGpo) {
//		boolean visible = false;
//		if(grupoVisible != null){
//			for(int i= 0; i < grupoVisible.length; i++){
//				if(idGpo.intValue() == grupoVisible[i].intValue()){
//					visible = true;
//					break;
//				}
//			}
//		}
//		return visible;
//	}
	
	private boolean recuperarCampoOpcional(Long idGpoCampoRelacionDet) {

		boolean opcional = false;
		if(camposOpcional != null){
			for(int i= 0; i < camposOpcional.length; i++){
				if(idGpoCampoRelacionDet.intValue() == camposOpcional[i].intValue()){
					opcional = true;
					break;
				}
			}
		}
		return opcional;
	}
	
	public String recuperaCriteriosValidacion(){
		GruposCamposRelacionDefaultV gCampoRelacionD = null;
		//recupera composicion de grupo y campo a traves de idCampoRelacion
		gCampoRelacionD = rDAO.consultaGruposCamposRelacionDefaultV(idCampoRelacion).get(0);
		//recupera criterio de validacion de acuerdo a grupo y campo  
		lstCriteriosValidacionRelacionDFV = rDAO.consultaCriteriosValidacionRelacionDfV(gCampoRelacionD.getIdGrupo(), gCampoRelacionD.getIdCampo(), 1);
		lstTV = new ArrayList<CriteriosValidacionTransaccionalV>();
		for (CriteriosValidacionRelacionDFV df:lstCriteriosValidacionRelacionDFV){
			if(registrar == 0){
				lstTV.add(new CriteriosValidacionTransaccionalV(df.getIdCriterioValidacionRlDf(), df.getCriterio()));
			}else {
				 List<CriteriosValidacionTransaccional> lstTemp = rDAO.consultaCriteriosValidacionRelacionTransaccional(0, idPerRel, 0, df.getIdGrupo(),  df.getIdCampo(), df.getIdCriterioValidacionRlDf());
				 if(lstTemp.size()>0){
					 lstTV.add(new CriteriosValidacionTransaccionalV(df.getIdCriterioValidacionRlDf(), df.getCriterio(), lstTemp.get(0).getIdCampoRelacion()));
				 }else{
					 lstTV.add(new CriteriosValidacionTransaccionalV(df.getIdCriterioValidacionRlDf(), df.getCriterio()));
				 }
				
			}
			
		}
		
		return SUCCESS;		
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

	public List<Relaciones> getLstRelaciones() {
		return lstRelaciones;
	}

	public void setLstRelaciones(List<Relaciones> lstRelaciones) {
		this.lstRelaciones = lstRelaciones;
	}
	
	public List<GruposCamposRelacionDefaultV> getLstGpoCampoRelDDetalle() {
		return lstGpoCampoRelDDetalle;
	}

	public void setLstGpoCampoRelDDetalle(
			List<GruposCamposRelacionDefaultV> lstGpoCampoRelDDetalle) {
		this.lstGpoCampoRelDDetalle = lstGpoCampoRelDDetalle;
	}

	public List<GruposCamposRelacionDefaultV> getLstGpoCampoRelDEncabezado() {
		return lstGpoCampoRelDEncabezado;
	}

	public void setLstGpoCampoRelDEncabezado(
			List<GruposCamposRelacionDefaultV> lstGpoCampoRelDEncabezado) {
		this.lstGpoCampoRelDEncabezado = lstGpoCampoRelDEncabezado;
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


	public Integer[] getGrupoVisible() {
		return grupoVisible;
	}

	public void setGrupoVisible(Integer[] grupoVisible) {
		this.grupoVisible = grupoVisible;
	}

	public Map<Long, Integer> getPosicionEnc() {
		return posicionEnc;
	}

	public void setPosicionEnc(Map<Long, Integer> posicionEnc) {
		this.posicionEnc = posicionEnc;
	}

	public Map<Long, Integer> getPosicionDet() {
		return posicionDet;
	}

	public void setPosicionDet(Map<Long, Integer> posicionDet) {
		this.posicionDet = posicionDet;
	}


	public int getRegistrar() {
		return registrar;
	}

	public Map<Integer, Integer> getPosicionGrupoDetalle() {
		return posicionGrupoDetalle;
	}

	public void setPosicionGrupoDetalle(Map<Integer, Integer> posicionGrupoDetalle) {
		this.posicionGrupoDetalle = posicionGrupoDetalle;
	}

	public void setRegistrar(int registrar) {
		this.registrar = registrar;
	}

	public List<GruposCamposRelacionV> getLstGpoCampoRelEncabezadoV() {
		return lstGpoCampoRelEncabezadoV;
	}

	public void setLstGpoCampoRelEncabezadoV(
			List<GruposCamposRelacionV> lstGpoCampoRelEncabezadoV) {
		this.lstGpoCampoRelEncabezadoV = lstGpoCampoRelEncabezadoV;
	}

	public List<GruposCamposRelacionV> getLstGpoCampoRelDetalleV() {
		return lstGpoCampoRelDetalleV;
	}

	public void setLstGpoCampoRelDetalleV(
			List<GruposCamposRelacionV> lstGpoCampoRelDetalleV) {
		this.lstGpoCampoRelDetalleV = lstGpoCampoRelDetalleV;
	}

	public boolean isBandConfiguracionYaRegistrada() {
		return bandConfiguracionYaRegistrada;
	}

	public void setBandConfiguracionYaRegistrada(
			boolean bandConfiguracionYaRegistrada) {
		this.bandConfiguracionYaRegistrada = bandConfiguracionYaRegistrada;
	}
	
	public List<PersonalizacionRelacionesV> getLstPersonalizacionRelV() {
		return lstPersonalizacionRelV;
	}

	public void setLstPersonalizacionRelV(List<PersonalizacionRelacionesV> lstPersonalizacionRelV) {
		this.lstPersonalizacionRelV = lstPersonalizacionRelV;
	}


	public List<CicloPrograma> getLstCiclosProgramas() {
		return lstCiclosProgramas;
	}

	public void setLstCiclosProgramas(List<CicloPrograma> lstCiclosProgramas) {
		this.lstCiclosProgramas = lstCiclosProgramas;
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

	public ArrayList<Cultivo> getLstCultivos() {
		return lstCultivos;
	}

	public void setLstCultivos(ArrayList<Cultivo> lstCultivos) {
		this.lstCultivos = lstCultivos;
	}

	public String getSelecCultivo() {
		return selecCultivo;
	}

	public void setSelecCultivo(String selecCultivo) {
		this.selecCultivo = selecCultivo;
	}

	public List<IniEsquemaRelacion> getLstIniEsquemaRelacion() {
		return lstIniEsquemaRelacion;
	}
	public void setLstIniEsquemaRelacion(List<IniEsquemaRelacion> lstIniEsquemaRelacion) {
		this.lstIniEsquemaRelacion = lstIniEsquemaRelacion;
	}

	public long getIdIniEsquemaRelacion(){
		return idIniEsquemaRelacion;
	}

	public void setIdIniEsquemaRelacion(long idIniEsquemaRelacion) {
		this.idIniEsquemaRelacion = idIniEsquemaRelacion;
	}

	public Integer[] getCamposOpcional() {
		return camposOpcional;
	}

	public void setCamposOpcional(Integer[] camposOpcional) {
		this.camposOpcional = camposOpcional;
	}

	public String getNombreEsquema() {
		return nombreEsquema;
	}

	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}	
	

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	
	
	public String[] getCamposValidacionCriterio() {
		return camposValidacionCriterio;
	}

	public void setCamposValidacionCriterio(String[] camposValidacionCriterio) {
		this.camposValidacionCriterio = camposValidacionCriterio;
	}

	public long getIdCampoRelacion() {
		return idCampoRelacion;
	}

	public void setIdCampoRelacion(long idCampoRelacion) {
		this.idCampoRelacion = idCampoRelacion;
	}

	public List<CriteriosValidacionTransaccionalV> getLstTV() {
		return lstTV;
	}

	public void setLstTV(List<CriteriosValidacionTransaccionalV> lstTV) {
		this.lstTV = lstTV;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public int getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}

	public int getTipoCaptura() {
		return tipoCaptura;
	}

	public void setTipoCaptura(int tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<PersonalizacionRelacionesProgramaV> getLstPersonalizacionRelacionesProgramaV() {
		return lstPersonalizacionRelacionesProgramaV;
	}

	public void setLstPersonalizacionRelacionesProgramaV(
			List<PersonalizacionRelacionesProgramaV> lstPersonalizacionRelacionesProgramaV) {
		this.lstPersonalizacionRelacionesProgramaV = lstPersonalizacionRelacionesProgramaV;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}

	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
}
