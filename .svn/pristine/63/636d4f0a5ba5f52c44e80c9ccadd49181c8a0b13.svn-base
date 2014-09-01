package mx.gob.comer.sipc.action.inscripcion;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mx.gob.comer.sipc.dao.CatalogosDAO;
import mx.gob.comer.sipc.dao.InscripcionDAO;
import mx.gob.comer.sipc.domain.*;
import mx.gob.comer.sipc.domain.catalogos.*;
import mx.gob.comer.sipc.domain.historico.*;
import mx.gob.comer.sipc.domain.transaccionales.*;
import mx.gob.comer.sipc.log.AppLogger;
import mx.gob.comer.sipc.utilerias.EnvioMensajes;
import mx.gob.comer.sipc.utilerias.TextUtil;
import mx.gob.comer.sipc.utilerias.Utilerias;
import mx.gob.comer.sipc.vistas.domain.CuotasEsquemaV;
import mx.gob.comer.sipc.vistas.domain.EtapaIniEsquemaV;
import mx.gob.comer.sipc.vistas.domain.HcoProgramasV;
import mx.gob.comer.sipc.vistas.domain.ProgramasV;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InicializacionProgramaAction extends ActionSupport implements SessionAware{
	@SessionTarget
	Session sessionTarget;
	
	@TransactionTarget
	Transaction transaction;
	private CatalogosDAO cDAO = new CatalogosDAO ();
	private InscripcionDAO iDAO = new InscripcionDAO();
	private List<String> lstRegistros;
	private List<CuotasEsquema> lstCuotasEsquema;
	private List<CuotasEsquemaV> lstCuotasEsquemaV;
	private List<CicloPrograma> lstCiclosProgramas;
	private List<CicloPrograma> lstCiclosProgramasC;
	private List<Ejercicios> lstEjercicios;
	private List<Ciclo> lstCiclos;
	private List<Cultivo> lstCultivo;
	private List<Variedad> lstVariedad;
	private List<Componente> lstComponente;
	private List<CriterioPago> lstCriterioPago;
	private List<UnidadMedida> lstUnidadMedida;
	private List<AreasResponsables> lstAreasResponsables;
	private List<Estado> lstEstado;
	private List<ProgramasV> lstProgramaV;
	private Integer numCampos;	
	private int numCamposAnterior;
	private int numCamposVXCVAnterior;
	private int noEtapaAnterior;
	
	private Integer numCamposVXCV;
	
	private Programa programa;
	private InicializacionEsquema inicializa;
	private Date fechaPeticion;
	private String descLineamiento;
	private String descCorta;	
	private String descLarga;
	private Integer ciclo;
	private Integer anio;
	private Double volumen;
	private Double importe;
	private int noEtapa;
	private Integer periodoDOFSI;
	private String  archivoAvisoDOF;
	private String acronimoCA;
	private int errorValidaAcronimo;
	private String leyendaAtentaNota;
	
	private Integer periodoOSIROSI;
	private Integer periodoCASP;
	private Integer periodoSPOO;
	private Integer periodoORPago;
	private Integer[] selecCiclo;
	private Integer[] selecAnio;
	private Integer[] selectedCult;
	private Integer[] selectedEdos;
	private Integer[] selectedVariedad;
	private Double[] cuota;
	//volumen por cultivo variedad
	private String selectedCultVXCV;
	private String selectedVariedadVXCV;
	private String selectedVolumenVXCV;
	private Integer idPrograma;
	private Long idInicializa;
	private Integer idUnidadMedida;
	private int idCriterioPago;
	private int idCultivo;
	private int idArea;
	
	private Map<String, Object> session;
	private String msjOk;
	private String msjError;
	private int numCiclos;
	private int idComponente;	
	private File doc;
	private String docFileName;
	private int editar;

	private List<HcoProgramasV> lstHcoProgramasV;

	private String rutaPrograma;

	private List<EtapaIniEsquemaV> lstEtapaIniEsquemaV;
	private List<CultivoVariedadEsquema> lstCultivoVariedadEsquema;

	private String abreviaturaUM;
	private int volxCulVar;
	
	private String [] selectEtapa;
	private Double[] selectCM;

	private boolean cartaAdhesionSistema;
	private int count;	
	public String listarProgramas(){
		try{
			session = ActionContext.getContext().getSession();
			int idPerfil = (Integer) session.get("idPerfil"); 
			if(idPerfil == 3 || idPerfil == 10){
				lstProgramaV = cDAO.consultaProgramaV(0);	
			}else if(idPerfil == 5){
				lstProgramaV = cDAO.consultaProgramaV(0, (Integer) session.get("idArea"));
			}			
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;		
	}
	public String capturarInicializacionPrograma(){
		try{	
			Utilerias.getResponseISO();
			lstComponente = cDAO.consultaComponente(0);
			lstCriterioPago = cDAO.consultaCriterioPago(0);
			lstAreasResponsables = cDAO.consultaAreasResponsables(0);
			
		}catch (JDBCException e) {
	    	e.printStackTrace();
	    
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;		
	}
	
	public String agregaCultivoEstadoIniProg(){
		lstCuotasEsquema = new ArrayList<CuotasEsquema>();
		if(editar == 0){
			for(int i=1; i<=numCampos; i++){
				lstCuotasEsquema.add(new CuotasEsquema());
			}	
		}else if(editar == 1){
			for(int i=0; i <selectedEdos.length; i++){
				CuotasEsquema ce = new CuotasEsquema();
				ce.setIdCultivo(selectedCult[i]);
				ce.setIdEstado(selectedEdos[i]);
				ce.setIdVariedad(selectedVariedad[i]);
				ce.setCuota(cuota[i]);	
				lstCuotasEsquema.add(ce);
			}
		}else if(editar == 2 || editar == 3 || editar == 4){
			if(lstCuotasEsquemaV==null){
				lstCuotasEsquemaV = iDAO.consultaCuotasEsquemaV(0, idPrograma);
			}
			for(CuotasEsquemaV c:lstCuotasEsquemaV){
				CuotasEsquema ce = new CuotasEsquema();
				ce.setIdCultivo(c.getIdCultivo());
				ce.setIdEstado(c.getIdEstado());
				ce.setIdVariedad(c.getIdVariedad());
				ce.setCuota(c.getCuota());	
				lstCuotasEsquema.add(ce);
			}		
			if(editar==3 || editar==4){
				if(numCampos > numCamposAnterior){
					int resta = 0;
					resta = numCampos - numCamposAnterior;
					for(int i =1; i<=resta; i++){
						lstCuotasEsquema.add(new CuotasEsquema());
					}
				}else if(numCampos < numCamposAnterior){
					List<CuotasEsquema> lstCuotasEsquemaTmp = new ArrayList<CuotasEsquema>();
					for (int i=1; i<=numCampos; i++){
						lstCuotasEsquemaTmp.add(lstCuotasEsquema.get(i-1));
					}
					lstCuotasEsquema = new ArrayList<CuotasEsquema>();
					lstCuotasEsquema = lstCuotasEsquemaTmp;
				}
			}
			
				
		}
		
		if(editar == 0 || editar == 1){
			if(idCriterioPago == 1 || idCriterioPago == 3){
				if(volxCulVar == 0){ // si desea Agregar Volumen por Cultivo Variedad
					lstCultivo = cDAO.consultaCultivo(selectedCultVXCV);
					lstVariedad = cDAO.consultaVariedad(selectedVariedadVXCV);
				}else{
					lstCultivo = cDAO.consultaCultivo();
					lstVariedad = new ArrayList<Variedad>();
					//lstVariedad = cDAO.consultaVariedad();
					
				}
			}else{
				lstCultivo = cDAO.consultaCultivo();
				lstVariedad = new ArrayList<Variedad>();
				//lstVariedad = cDAO.consultaVariedad();
			}
		}else{
			lstCultivo = cDAO.consultaCultivo();
			lstVariedad = cDAO.consultaVariedad();
		}
		lstEstado = cDAO.consultaEstado(0);
		
		return SUCCESS;
	}
	
	public String agregarVolumenPorCultivoVariedad(){
		try{
			lstCultivoVariedadEsquema = new ArrayList<CultivoVariedadEsquema>();
			if(editar == 0){
				for(int i=1; i<=numCamposVXCV; i++){
					lstCultivoVariedadEsquema.add(new CultivoVariedadEsquema());
				}
			}else if(editar == 1){
				String [] idCultivo = selectedCultVXCV.split(",");
				String [] idVariedad = selectedVariedadVXCV.split(",");
				String [] volumen = selectedVolumenVXCV.split(",");
				for(int i=0; i <idCultivo.length; i++){
					CultivoVariedadEsquema cve = new CultivoVariedadEsquema();
					cve.setIdCultivo(Integer.parseInt(idCultivo[i]));
					cve.setIdVariedad(Integer.parseInt(idVariedad[i]));
					cve.setVolumen(Double.parseDouble(volumen[i]));
					lstCultivoVariedadEsquema.add(cve);
				}
			}else if(editar == 2 || editar == 3 || editar == 4){
				lstCultivoVariedadEsquema = iDAO.consultaCultivoVariedadEsquema(idPrograma);
				System.out.println("lstCultivoVariedadEsquema "+lstCultivoVariedadEsquema.size());
				numCamposVXCVAnterior = lstCultivoVariedadEsquema.size();
				
				if(numCamposVXCV == 0){
					volxCulVar = 1; // No desea agregar volumen por cultivo variedad
				}
				
				if(editar==3 || editar==4){
					if(numCamposVXCV > numCamposVXCVAnterior){
						int resta = 0;
						resta = numCamposVXCV - numCamposVXCVAnterior;
						for(int i =1; i<=resta; i++){
							lstCultivoVariedadEsquema.add(new CultivoVariedadEsquema());
						}
					}else if(numCamposVXCV < numCamposVXCVAnterior){
						List<CultivoVariedadEsquema> lstCultivoVariedadEsquemaTmp = new ArrayList<CultivoVariedadEsquema>();
						for (int i=1; i<=numCamposVXCV; i++){
							lstCultivoVariedadEsquemaTmp.add(lstCultivoVariedadEsquema.get(i-1));
						}
						lstCultivoVariedadEsquema = new ArrayList<CultivoVariedadEsquema>();
						lstCultivoVariedadEsquema = lstCultivoVariedadEsquemaTmp;
					}
				}
				
			}
						
			lstCultivo = cDAO.consultaCultivo();
			lstVariedad = new ArrayList<Variedad>();
			//lstVariedad = cDAO.consultaVariedad();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String selectNumEtapas(){
		lstEtapaIniEsquemaV = new ArrayList<EtapaIniEsquemaV>();
		if(idCriterioPago == 3){
			abreviaturaUM = cDAO.consultaUnidadMedida(idUnidadMedida).get(0).getAbreviatura();
		}		
		if(editar == 0){
			for(int i=1; i<=noEtapa; i++){
				String etapa ="";
				if(i==1){
					etapa = "I";
				}else if(i==2){
					etapa = "II";
				}else if(i==3){
					etapa = "III";
				}else if(i==4){
					etapa = "IV";
				}else if(i==5){
					etapa = "IV";
				}
				lstEtapaIniEsquemaV.add(new EtapaIniEsquemaV(etapa));
			}
		}else if(editar == 1){
			for(int i= 0; i< selectEtapa.length; i++){
				EtapaIniEsquemaV eiev = new  EtapaIniEsquemaV();
				eiev.setEtapa(selectEtapa[i]);
				eiev.setMonto(selectCM[i]); // aplica para los dos criterios 2 || 3
			}
			
		}else if(editar == 2 || editar == 3 || editar == 4){			
			lstEtapaIniEsquemaV = iDAO.consultaEtapaIniEsquemaV(0, idPrograma);	
			noEtapaAnterior = lstEtapaIniEsquemaV.size();
			if(editar==3 || editar==4){
				if(noEtapa > noEtapaAnterior){
					int resta = 0;
					resta = noEtapa - noEtapaAnterior;
					for(int i =1; i<=resta; i++){
						noEtapaAnterior +=1;
						lstEtapaIniEsquemaV.add(new EtapaIniEsquemaV(TextUtil.convierteNumARomano(noEtapaAnterior)));
						
					}
				}else if(noEtapa < noEtapaAnterior){
					List<EtapaIniEsquemaV> lstEtapaIniEsquemaVaTmp = new ArrayList<EtapaIniEsquemaV>();
					for (int i=1; i<=noEtapa; i++){
						lstEtapaIniEsquemaVaTmp.add(lstEtapaIniEsquemaV.get(i-1));
					}
					lstEtapaIniEsquemaV = new ArrayList<EtapaIniEsquemaV>();
					lstEtapaIniEsquemaV = lstEtapaIniEsquemaVaTmp;
				}
			}
			
		}
		return SUCCESS;
	}
		
	public String consigueNumCiclos(){
		
		lstCiclosProgramas = new ArrayList<CicloPrograma>();
		if(editar == 0){
			for(int i=1; i<=numCiclos; i++){
				lstCiclosProgramas.add(new CicloPrograma());
			}
		}else if(editar == 1){			
			///guarda los ciclos en ciclos_programas
			for (int i = 0; i < selecCiclo.length; i++) {
				CicloPrograma cp = new CicloPrograma();
				cp.setIdCiclo(selecCiclo[i]);
				cp.setIdEjercicio(selecAnio[i]);
				lstCiclosProgramas.add(cp);					
			}	
		}else if(editar == 2 || editar == 3 || editar == 4){		
			///guarda los ciclos en ciclos_programas
			for(CicloPrograma cpl: lstCiclosProgramasC){
				CicloPrograma cp = new CicloPrograma();
				cp.setIdCiclo(cpl.getIdCiclo());
				cp.setIdEjercicio(cpl.getIdEjercicio());
				lstCiclosProgramas.add(cp);					
			}
			
		}
		
		
		lstEjercicios = cDAO.consultaEjercicio(0);
		lstCiclos = cDAO.consultaCiclo(0);
		return SUCCESS;
	}
	
	
	public String recuperaCultivoByVariedad(){
		//Recupera los datos de la variedad por cultivo
		lstVariedad = cDAO.consultaVariedad(0, idCultivo, null);
		return SUCCESS;		
	}
	public String recuperaCultivoByVariedadVXCV(){
		//Recupera los datos de la variedad por cultivo
		lstVariedad = cDAO.consultaVariedad(0, idCultivo, null);
		return SUCCESS;		
	}
	
	public String registroInicializaEsquema() throws Exception{
		try{
			int elementosBorrados = 0;
			programa = new Programa();
			session = ActionContext.getContext().getSession();						
			if(editar==3 || editar == 4){
				//Borra todo los elementos asociados al programa
				programa = cDAO.consultaPrograma(idPrograma).get(0);
				
				if(editar == 4){// adiciones
					//recupera incializacion 
					inicializa =  iDAO.consultaInicializacionPrograma(programa.getIdPrograma()).get(0);
					guardarHistoricos();	
					//borra objeto inicializacion
					iDAO.borrarObjeto(inicializa);
				}
				if(editar == 3){
					//borra los ciclos asociados al programa
					elementosBorrados = iDAO.borrarCiclosByProgramas(idPrograma);
					AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla ciclos_programas");
				}
				
				
				/*if(editar == 4){
					//borra objeto inicializacion
					//iDAO.borrarObjeto(inicializa);
					
				}*/
				//Borra la tabla de cultivo_variedad_esquema
				elementosBorrados = iDAO.borrarCultivoVariedadEsquemaByPrograma(idPrograma);
				//Borra la tabla de etapa_ini_esquema
				elementosBorrados = iDAO.borrarEtapaIniEsquema(idPrograma);
				elementosBorrados = iDAO.borrarEstadosByProgramas(idPrograma);
				AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla programas_estados");
				
			}			
			programa.setDescripcionCorta(descCorta);
			programa.setDescripcion(descLarga);
			programa.setDescripcionLarga(descLineamiento);
			if(editar!=4){
				integrarCiclo();
			}
						
   
			programa.setCriterioPago(idCriterioPago);
			
			if(idCriterioPago == 1 || idCriterioPago == 3){
				programa.setIdUnidadMedida(idUnidadMedida);
			}else if(idCriterioPago == 2){
				programa.setIdUnidadMedida(null);
			}
			
			if(idCriterioPago==2||idCriterioPago==3){
				programa.setNumeroEtapa(noEtapa);
			}else{
				programa.setNumeroEtapa(null);
			}
			programa.setIdArea(idArea);			
			programa.setIdComponente(idComponente);
			programa = cDAO.guardaPrograma(programa);			
			
			if(editar!=4){
				///guarda los ciclos en ciclos_programas
				for (int i = 0; i < selecCiclo.length; i++) {
					CicloPrograma cp = new CicloPrograma();
					cp.setIdCiclo(selecCiclo[i]);
					cp.setIdEjercicio(selecAnio[i]);
					cp.setIdPrograma(programa.getIdPrograma());
					cDAO.guardaObjeto(cp);
						
				}			
			}
			//guarda en la tabla de programas_estados			
			Set<String> items = new HashSet<String>(selectedEdos.length);
			Set<String> itemsCultivo = new HashSet<String>(selectedEdos.length);
			String nombreEstados = "";
			String nombreCultivos = "";			
			for(int i=0; i<selectedEdos.length; i++){	
				if(!itemsCultivo.contains(selectedCult[i].toString())){
						itemsCultivo.add(selectedCult[i].toString());
						nombreCultivos+=cDAO.consultaCultivo(selectedCult[i]).get(0).getCultivo()+",";				
				}
				if(!items.contains(selectedEdos[i].toString())){
					items.add(selectedEdos[i].toString());
					//Guarda el estado
					ProgramaEstado pe = new ProgramaEstado();
					pe.setIdPrograma(programa.getIdPrograma());
					pe.setIdEstado(selectedEdos[i]);
					cDAO.guardaObjeto(pe);
					//Cosigue el nombre del edo para concatenar el nombre del estado
					nombreEstados+=(cDAO.consultaEstado(selectedEdos[i]).get(0).getNombre()+",");	
				}
			}
			//conformando la cadena de estados
			nombreEstados = Utilerias.conformaCadena(nombreEstados.split(","));
			//conformando la cadena de cultivos
			nombreCultivos = Utilerias.conformaCadena(nombreCultivos.split(","));
			
			
			//Recupera nuevamente el programa para actualizar los estados y productos del mismo 			
			programa = cDAO.consultaPrograma(programa.getIdPrograma()).get(0);
			programa.setEstados(nombreEstados.toUpperCase());
			programa.setProducto(nombreCultivos);
			String rutaPrograma ="";
			String nombreArchivo ="";
			//Crea directorio
			if(editar!=3){
				rutaPrograma = "/SIPC/"+programa.getIdPrograma()+"/";
				Utilerias.crearDirectorio(rutaPrograma);
				programa.setRutaDocumentos(rutaPrograma);
				//Cargar Archivo
				nombreArchivo = cargarArchivoAviso(rutaPrograma);
			}else if(editar==3){
				
			}
						
			programa = (Programa) cDAO.guardaObjeto(programa);
			
			//Guarda Volumen por cultivo variedad en caso de que haya dicho si en ¿Desea agregar volumen por cultivo variedad?
			if(idCriterioPago == 1 || idCriterioPago == 3){
				if (volxCulVar == 0){
					System.out.println(selectedCultVXCV.length());
					String [] idCultivo = selectedCultVXCV.split(",");
					String [] idVariedad = selectedVariedadVXCV.split(",");
					String [] volumen = selectedVolumenVXCV.split(",");
					for (int i = 0; i < idCultivo.length; i++) {
						CultivoVariedadEsquema cve = new CultivoVariedadEsquema();
						System.out.println(idCultivo[i]);
						cve.setIdCultivo(Integer.parseInt(idCultivo[i].trim()));
						cve.setIdVariedad(Integer.parseInt(idVariedad[i].trim()));
						cve.setVolumen(Double.parseDouble(volumen[i].trim()));
						cve.setIdPrograma(programa.getIdPrograma());
						cDAO.guardaObjeto(cve);				
						
					}
					
				}
			}
			
			//Guarda las etapas en caso de que el criterio sea 2.- etapa o 3.- Volumen/Etapa
			if(idCriterioPago == 2 || idCriterioPago == 3){
				for(int i = 0; i < selectEtapa.length; i++ ){
					EtapaIniEsquema eie = new EtapaIniEsquema();
					eie.setIdEtapa(TextUtil.convierteRomanoANum(selectEtapa[i]));					
					eie.setMonto(selectCM[i]);					
					eie.setIdPrograma(programa.getIdPrograma());
					cDAO.guardaObjeto(eie);

				}
				
			}		
					
			// Crea INICIALIZACION_ESQUEMA
			inicializa = new InicializacionEsquema();
			if(editar==3){
				//recupera la incializacion del programa
				inicializa = iDAO.consultaInicializacionPrograma(idPrograma).get(0);
				//borra las cuotas de la inicializacion
				elementosBorrados = iDAO.borrarCuotasEsquemaByInicializacion(inicializa.getId());
				AppLogger.info("app", "Se borraron "+elementosBorrados+" de la tabla cuotas_esquema");	
				rutaPrograma=programa.getRutaDocumentos();
				if(doc!=null){
					//Cargar Archivo
					nombreArchivo = cargarArchivoAviso(rutaPrograma);
					File f = new File(rutaPrograma+inicializa.getArchivoPublicacionDof());
					if(f.exists()){
						if(!f.delete()){
							AppLogger.error("errores","Error al eliminar: "+rutaPrograma+inicializa.getArchivoPublicacionDof());
						}else{
							AppLogger.info("app","Se borro archivo: "+rutaPrograma+inicializa.getArchivoPublicacionDof());
						}
					}
				}else{
					nombreArchivo = inicializa.getArchivoPublicacionDof();
				}
			}
			inicializa.setIdPrograma(programa.getIdPrograma());
			inicializa.setFechaPublicacionDOF(fechaPeticion);
			inicializa.setCartaAdhesionSistema(cartaAdhesionSistema);
			inicializa.setAcronimoCA(acronimoCA);
			//inicializa.setRuta_archivo_lineamiento(ruta);
			if(idCriterioPago == 1 || idCriterioPago == 3){
				inicializa.setVolumenAutorizado(volumen);
				inicializa.setImporteAutorizado(null);
			}
			
			if(idCriterioPago == 2){
				inicializa.setImporteAutorizado(importe);
				inicializa.setVolumenAutorizado(null);
			}			
			inicializa.setPeriodoDOFSI(periodoDOFSI);
			inicializa.setPeriodoOSIROSI(periodoOSIROSI);
			inicializa.setPeriodoCartaSP(periodoCASP);
			inicializa.setPeriodoSPOO(periodoSPOO);
			inicializa.setPeriodoORPago(periodoORPago);
			inicializa.setArchivoPublicacionDof(nombreArchivo);
			inicializa.setLeyendaAtentaNota(leyendaAtentaNota);
			inicializa.setCuotasEsquema(new HashSet<CuotasEsquema>());
			
			for (int i = 0; i < selectedEdos.length; i++) {
				CuotasEsquema ce = new CuotasEsquema();
				//ce.setIdInicializacionEsquema(idInicializa);
				ce.setIdCultivo(selectedCult[i]);
				ce.setIdEstado(selectedEdos[i]);
				if(selectedVariedad[i]!=-1){
					ce.setIdVariedad(selectedVariedad[i]);
				}
				if(idCriterioPago == 1){
					ce.setCuota(cuota[i]);	
				}					
				
				inicializa.getCuotasEsquema().add(ce);
				
			}
			cDAO.guardaObjeto(inicializa);
			
			
			/*Enviar aviso de la captura de pago*/
			String log = "";
			if(editar == 0){
				log = "Se le informa que se registro el esquema de apoyo: "+programa.getDescripcionCorta()+" - "+programa.getCicloCorto();
			}else if(editar == 4){
				log = "Se le informa que se realizo una adicion al esquema de apoyo: "+programa.getDescripcionCorta()+" - "+programa.getCicloCorto();
			}
			if(editar == 0 || editar==4){
				EnvioMensajes mensajes = new EnvioMensajes(sessionTarget);
				mensajes.enviarMensaje((Integer) session.get("idUsuario"), 8, log, "Aviso");
				
			}
						
			lstProgramaV = cDAO.consultaProgramaV(0);
			msjOk = "Se guardó satisfactoriamente el registro";
								
		}catch (JDBCException e) {
			addActionError("Ocurrió un error al guardar en base de datos, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrio un error al guardar la inicializacion del esquema en base de datos debido a: "+e.getCause());
			e.printStackTrace();
			if(editar == 0){
				editar = 1;
			}			
			consultaCatalogos();			
			return INPUT;
		}catch(Exception e){
			if(editar == 0){
				editar = 1;
			}
			e.printStackTrace();
			consultaCatalogos();			
			addActionError("Ocurrio un error inesperado, favor de reportar al administrador");
			AppLogger.error("errores","Ocurrio un error inesperado al guardar la inicializacion del esquema debido a: "+e.getCause());
			return INPUT;
		}
		return SUCCESS;
	}

	public String  validarAcronimo(){
		if(iDAO.consultaInicializacionPrograma(0, acronimoCA).size()>0){
			errorValidaAcronimo = 1;
			msjError = "La definición del acrónimo ya se encuentra registrado, favor de verificar";
		}
		 
		
		return SUCCESS;
	}
	
	
	private void guardarHistoricos() {
		Date fechaCreacion = new Date();
		// insertando en las tablas historicas
		HcoPrograma hcoPrograma = new HcoPrograma();
		hcoPrograma.setCiclo(programa.getCiclo());
		hcoPrograma.setCicloCorto(programa.getCicloCorto());
		hcoPrograma.setCriterioPago(programa.getCriterioPago());
		hcoPrograma.setDescripcion(programa.getDescripcion());
		hcoPrograma.setDescripcionCorta(programa.getDescripcionCorta());
		hcoPrograma.setDescripcionLarga(programa.getDescripcionLarga());
		hcoPrograma.setEstados(programa.getEstados());
		hcoPrograma.setFechaCreacion(new Date());
		hcoPrograma.setIdArea(programa.getIdArea());
		hcoPrograma.setIdComponente(programa.getIdComponente());
		hcoPrograma.setIdPrograma(programa.getIdPrograma());				
		hcoPrograma.setIdUnidadMedida(programa.getIdUnidadMedida());
		hcoPrograma.setNumeroEtapa(programa.getNumeroEtapa());
		hcoPrograma.setProducto(programa.getProducto());
		hcoPrograma.setRutaDocumentos(programa.getRutaDocumentos());
		hcoPrograma.setUsuarioCreacion((Integer) session.get("idUsuario"));
		hcoPrograma = (HcoPrograma) cDAO.guardaObjeto(hcoPrograma);
		//Guardando inicializacion esquema
		HcoInicializacionEsquema hcoIE = new HcoInicializacionEsquema();
		hcoIE.setArchivoPublicacionDof(inicializa.getArchivoPublicacionDof());
		hcoIE.setFechaCreacion(fechaCreacion);
		hcoIE.setFechaPublicacionDOF(inicializa.getFechaPublicacionDOF());
		hcoIE.setHcoIdPrograma(hcoPrograma.getIdHcoPrograma());
		hcoIE.setImporteAutorizado(inicializa.getImporteAutorizado());
		hcoIE.setPeriodoCartaSP(inicializa.getPeriodoCartaSP());
		hcoIE.setPeriodoDOFSI(inicializa.getPeriodoDOFSI());
		hcoIE.setPeriodoOSIROSI(inicializa.getPeriodoOSIROSI());
		hcoIE.setPeriodoORPago(inicializa.getPeriodoORPago());
		hcoIE.setPeriodoSPOO(inicializa.getPeriodoSPOO());
		hcoIE.setLeyendaAtentaNota(inicializa.getLeyendaAtentaNota());
		hcoIE.setUsuarioCreacion((Integer) session.get("idUsuario"));
		hcoIE.setVolumenAutorizado(inicializa.getVolumenAutorizado());
		hcoIE.setCartaAdhesionSistema(inicializa.isCartaAdhesionSistema());
		hcoIE.setAcronimoCA(inicializa.getAcronimoCA());
		hcoIE.setHcoCuotasEsquema(new HashSet<HcoCuotasEsquema>());
		
		//recupera las cuotas esquema de inicializacion
		lstCuotasEsquema = iDAO.consultaCuotasEsquema(inicializa.getId());
		//inserta los historicos de cuotas esquema
		for(CuotasEsquema ce: lstCuotasEsquema){
			HcoCuotasEsquema hcoCE = new HcoCuotasEsquema();
			hcoCE.setIdCultivo(ce.getIdCultivo());
			hcoCE.setIdEstado(ce.getIdEstado());
			hcoCE.setIdVariedad(ce.getIdVariedad());
			hcoCE.setCuota(ce.getCuota());
			hcoCE.setFechaCreacion(fechaCreacion);
			hcoCE.setUsuarioCreacion((Integer) session.get("idUsuario"));
			hcoIE.getHcoCuotasEsquema().add(hcoCE);
		}
		
		//Guarda inicializacion y esquema en historicos
		cDAO.guardaObjeto(hcoIE);		
		//recupera los volumenes por cultivo variedad
		lstCultivoVariedadEsquema = iDAO.consultaCultivoVariedadEsquema(programa.getIdPrograma());
		for(CultivoVariedadEsquema cve: lstCultivoVariedadEsquema){
			HcoCultivoVariedadEsquema hcoCVE = new HcoCultivoVariedadEsquema();
			hcoCVE.setIdPrograma(programa.getIdPrograma());
			hcoCVE.setIdCultivo(cve.getIdCultivo());
			hcoCVE.setIdVariedad(cve.getIdVariedad());
			hcoCVE.setVolumen(cve.getVolumen());
			hcoCVE.setFechaCreacion(fechaCreacion);
			cDAO.guardaObjeto(hcoCVE);
		}
		
		//Recupera las etapa_ini_esquema
		lstEtapaIniEsquemaV = iDAO.consultaEtapaIniEsquemaV(0, programa.getIdPrograma());	
		for(EtapaIniEsquemaV eiev:lstEtapaIniEsquemaV){
			HcoEtapaIniEsquema hcoEiev = new HcoEtapaIniEsquema();
			hcoEiev.setIdEtapa(eiev.getIdEtapa());
			hcoEiev.setIdPrograma(programa.getIdPrograma());
			hcoEiev.setMonto(eiev.getMonto());
			hcoEiev.setFechaCreacion(fechaCreacion);
			hcoEiev.setUsuarioCreacion((Integer) session.get("idUsuario"));
			cDAO.guardaObjeto(hcoEiev);
		}
		//recupera los datos de programas_estados
		List<ProgramaEstado> lstPe= iDAO.consultaProgramaEstado(programa.getIdPrograma());
		//guarda programas estados
		for(ProgramaEstado pe:lstPe){
			HcoProgramaEstado hcoPE = new HcoProgramaEstado();
			hcoPE.setIdPrograma(pe.getIdPrograma());
			hcoPE.setIdEstado(pe.getIdEstado());
			hcoPE.setFechaCreacion(fechaCreacion);
			hcoPE.setUsuarioCreacion((Integer) session.get("idUsuario"));
			cDAO.guardaObjeto(hcoPE);
		}
				
		
		
	}
	private void consultaCatalogos() {
		consigueNumCiclos();
		agregaCultivoEstadoIniProg();
		capturarInicializacionPrograma();
		
	}
	private String cargarArchivoAviso(String rutaPrograma) {
		String nombreArchivo ="";
		try {
			if (!rutaPrograma.endsWith(File.separator)){
				rutaPrograma += File.separator;
			}
			String ext = docFileName.toLowerCase().substring( docFileName.lastIndexOf(".") );
			nombreArchivo = "aviso"+new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new Date() )+ext;	 
			Utilerias.copiaArchivo(rutaPrograma+nombreArchivo, doc);
		} catch (IOException e) {
			e.printStackTrace();
			AppLogger.error("errores", "Ocurrio un error al guardar el archivo de aviso");
		}
		
		return nombreArchivo;		
		
	}
	
	private void integrarCiclo() {
		/*Integracion de Ciclo*/
		StringBuilder cCicloL = new StringBuilder();
		StringBuilder cCicloC = new StringBuilder();
		int sizeArrayCiclo = selecCiclo.length;
		if(sizeArrayCiclo>1){
			for (int i = 0; i < sizeArrayCiclo; i++) {
				if(i != sizeArrayCiclo-1){
					if(selecCiclo[i]==1){ 
						cCicloL.append("Primavera-Verano ").append(selecAnio[i]).append(", ");
						cCicloC.append("PV-").append(selecAnio[i].toString().substring(2,4)).append(", ");
					}else{
						cCicloL.append("Otoño-Invierno ").append(selecAnio[i]).append("/").append(selecAnio[i]+1).append(", ");
						cCicloC.append("OI-").append(selecAnio[i].toString().substring(2,4)).append("/").append(Integer.parseInt(selecAnio[i].toString().substring(2,4))+1).append(", ");
					}
				}
			}					
			cCicloL.deleteCharAt(cCicloL.length() - 2);
			cCicloC.deleteCharAt(cCicloC.length() - 2);			
			if(selecCiclo[sizeArrayCiclo-1]==1){
				cCicloL.append("y ").append("Primavera-Verano ").append(selecAnio[sizeArrayCiclo-1]);
				cCicloC.append("y ").append("PV-").append(selecAnio[sizeArrayCiclo-1].toString().substring(2,4)).append(", ");
			}else{
				cCicloL.append("y ").append("Otoño-Invierno ").append(selecAnio[sizeArrayCiclo-1]).append("/").append(selecAnio[sizeArrayCiclo-1]+1);
				cCicloC.append("y ").append("OI-").append(selecAnio[sizeArrayCiclo-1].toString().substring(2,4)).append("/").append(Integer.parseInt(selecAnio[sizeArrayCiclo-1].toString().substring(2,4))+1);
			}		
			programa.setCiclo(cCicloL.toString());
			programa.setCicloCorto(cCicloC.toString());
			
		}else{
			if(selecCiclo[0]==1){
				programa.setCiclo("Primavera-Verano"+" "+selecAnio[0]);
				programa.setCicloCorto("PV-"+selecAnio[0].toString().substring(2,4));
			}else{
				programa.setCiclo("Otoño-Invierno"+" "+selecAnio[0]);
				programa.setCicloCorto("OI-"+Integer.parseInt(selecAnio[0].toString().substring(2,4))+1);
			}
		}	
		
	}

	public String  selectCriterioPago(){
		if(editar == 0){
			volxCulVar = 0;
		}
		if(idCriterioPago == 1 || idCriterioPago == 3){
			lstUnidadMedida = cDAO.consultaUnidadMedida(0);
		}
		
		return SUCCESS;
	}
	
	public String getDetalleInicialiciacionPrograma(){
		//Recupera datos del programa
		programa = cDAO.consultaPrograma(idPrograma).get(0);
		//Recupera la inicializacion del programa
		lstCuotasEsquemaV = iDAO.consultaCuotasEsquemaV(0, idPrograma);
		descCorta = programa.getDescripcionCorta();
		descLineamiento = programa.getDescripcionLarga();
		idComponente = programa.getIdComponente();
		numCampos = lstCuotasEsquemaV.size();		
		periodoCASP = lstCuotasEsquemaV.get(0).getPeriodoCartaSp();
		periodoDOFSI = lstCuotasEsquemaV.get(0).getPeriodoDofSi();
		periodoORPago = lstCuotasEsquemaV.get(0).getPeriodoOrPago();
		periodoOSIROSI = lstCuotasEsquemaV.get(0).getPeriodoOsiRosi();
		periodoSPOO = lstCuotasEsquemaV.get(0).getPeriodoSpOo();
		leyendaAtentaNota = lstCuotasEsquemaV.get(0).getLeyendaAtentaNota();
		archivoAvisoDOF = lstCuotasEsquemaV.get(0).getRutaDocumentos()+lstCuotasEsquemaV.get(0).getArchivoPublicacionDof();
		cartaAdhesionSistema = lstCuotasEsquemaV.get(0).isCartaAdhesionSistema(); 
		System.out.println("Acronimo "+lstCuotasEsquemaV.get(0).getAcronimoCA());
		acronimoCA = lstCuotasEsquemaV.get(0).getAcronimoCA();
		idCriterioPago = programa.getCriterioPago();
		idArea = programa.getIdArea();
		if(idCriterioPago == 1 || idCriterioPago == 3){
			//volumen
			volumen = lstCuotasEsquemaV.get(0).getVolumenAutorizado();
			idUnidadMedida = programa.getIdUnidadMedida();
			numCamposVXCV = iDAO.consultaCultivoVariedadEsquema(idPrograma).size();
			agregarVolumenPorCultivoVariedad();
		}
		if(editar == 2 || editar == 4){//Si hay adicciones recuperar oficios anteriores
			lstHcoProgramasV = iDAO.consultaHcoProgramasV(idPrograma);
			rutaPrograma = programa.getRutaDocumentos();
			
		}
		if(idCriterioPago == 2 || idCriterioPago == 3){
			noEtapa = programa.getNumeroEtapa();
			noEtapaAnterior = iDAO.consultaEtapaIniEsquemaV(0, idPrograma).size();	
			if(idCriterioPago == 2){
				//importe
				importe =  lstCuotasEsquemaV.get(0).getImporteAutorizado();
		
			}	
			//Recupera las etapas en la asignacion
			selectNumEtapas();
		}
		fechaPeticion = lstCuotasEsquemaV.get(0).getFechaPublicacionDof();
		numCamposAnterior = lstCuotasEsquemaV.size();
		//consigue los 
		agregaCultivoEstadoIniProg();
		//consigue los ciclos
		lstCiclosProgramasC = iDAO.consultaCicloProgramas(idPrograma);		
		numCiclos = lstCiclosProgramasC.size(); 
		consigueNumCiclos();	
		capturarInicializacionPrograma();
		selectCriterioPago();
		
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
	    this.session = session;
	}
	
	public Map<String, Object> getSession() {
	    return session;
	}

	public List<String> getLstRegistros() {
		return lstRegistros;
	}

	public void setLstRegistros(List<String> lstRegistros) {
		this.lstRegistros = lstRegistros;
	}

	public List<Cultivo> getLstCultivo() {
		return lstCultivo;
	}

	public void setLstCultivo(List<Cultivo> lstCultivo) {
		this.lstCultivo = lstCultivo;
	}
	
	public List<Variedad> getLstVariedad() {
		return lstVariedad;
	}
	public void setLstVariedad(List<Variedad> lstVariedad) {
		this.lstVariedad = lstVariedad;
	}
	public List<Estado> getLstEstado() {
		return lstEstado;
	}

	public void setLstEstado(List<Estado> lstEstado) {
		this.lstEstado = lstEstado;
	}

	public List<Ejercicios> getLstEjercicios() {
		return lstEjercicios;
	}

	public void setLstEjercicios(List<Ejercicios> lstEjercicios) {
		this.lstEjercicios = lstEjercicios;
	}
	
	public List<Ciclo> getLstCiclos() {
		return lstCiclos;
	}
	public void setLstCiclos(List<Ciclo> lstCiclos) {
		this.lstCiclos = lstCiclos;
	}
	
	public List<Componente> getLstComponente() {
		return lstComponente;
	}
	public void setLstComponente(List<Componente> lstComponente) {
		this.lstComponente = lstComponente;
	}
		
	public List<CriterioPago> getLstCriterioPago() {
		return lstCriterioPago;
	}
	public void setLstCriterioPago(List<CriterioPago> lstCriterioPago) {
		this.lstCriterioPago = lstCriterioPago;
	}
	public List<UnidadMedida> getLstUnidadMedida() {
		return lstUnidadMedida;
	}
	public void setLstUnidadMedida(List<UnidadMedida> lstUnidadMedida) {
		this.lstUnidadMedida = lstUnidadMedida;
	}
	public List<AreasResponsables> getLstAreasResponsables() {
		return lstAreasResponsables;
	}
	public void setLstAreasResponsables(List<AreasResponsables> lstAreasResponsables) {
		this.lstAreasResponsables = lstAreasResponsables;
	}
	public List<ProgramasV> getLstProgramaV() {
		return lstProgramaV;
	}

	public void setLstProgramaV(List<ProgramasV> lstProgramaV) {
		this.lstProgramaV = lstProgramaV;
	}
	
	public List<CicloPrograma> getLstCiclosProgramas() {
		return lstCiclosProgramas;
	}
	public void setLstCiclosProgramas(List<CicloPrograma> lstCiclosProgramas) {
		this.lstCiclosProgramas = lstCiclosProgramas;
	}
	
	public Integer getNumCampos() {
		return numCampos;
	}
	public void setNumCampos(Integer numCampos) {
		this.numCampos = numCampos;
	}
	
	public int getNumCamposAnterior() {
		return numCamposAnterior;
	}
	public void setNumCamposAnterior(int numCamposAnterior) {
		this.numCamposAnterior = numCamposAnterior;
	}
	public void setInicializa(InicializacionEsquema inicializa) {
		this.inicializa = inicializa;
	}

	public InicializacionEsquema getInicializa() {
		return inicializa;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Date getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public String getDescLineamiento() {
		return descLineamiento;
	}

	public void setDescLineamiento(String descLineamiento) {
		this.descLineamiento = descLineamiento.toUpperCase();
	}

	public String getDescCorta() {
		return descCorta;
	}

	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta.toUpperCase();
	}

	public String getDescLarga() {
		return descLarga;
	}

	public void setDescLarga(String descLarga) {
		this.descLarga = descLarga;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public int getNoEtapa() {
		return noEtapa;
	}

	public void setNoEtapa(int noEtapa) {
		this.noEtapa = noEtapa;
	}

	public Integer getPeriodoDOFSI() {
		return periodoDOFSI;
	}

	public void setPeriodoDOFSI(Integer periodoDOFSI) {
		this.periodoDOFSI = periodoDOFSI;
	}

	
	public Integer getPeriodoOSIROSI() {
		return periodoOSIROSI;
	}
	public void setPeriodoOSIROSI(Integer periodoOSIROSI) {
		this.periodoOSIROSI = periodoOSIROSI;
	}
	public Integer getPeriodoCASP() {
		return periodoCASP;
	}

	public void setPeriodoCASP(Integer periodoCASP) {
		this.periodoCASP = periodoCASP;
	}

	public Integer getPeriodoSPOO() {
		return periodoSPOO;
	}

	public void setPeriodoSPOO(Integer periodoSPOO) {
		this.periodoSPOO = periodoSPOO;
	}

	public Integer getPeriodoORPago() {
		return periodoORPago;
	}

	public void setPeriodoORPago(Integer periodoORPago) {
		this.periodoORPago = periodoORPago;
	}

	public Integer[] getSelectedCult() {
		return selectedCult;
	}

	public void setSelectedCult(Integer[] selectedCult) {
		this.selectedCult = selectedCult;
	}

	public Integer[] getSelectedEdos() {
		return selectedEdos;
	}

	public void setSelectedEdos(Integer[] selectedEdos) {
		this.selectedEdos = selectedEdos;
	}
	public Integer[] getSelectedVariedad() {
		return selectedVariedad;
	}
	public void setSelectedVariedad(Integer[] selectedVariedad) {
		this.selectedVariedad = selectedVariedad;
	}
	
	public String getSelectedCultVXCV() {
		return selectedCultVXCV;
	}
	public void setSelectedCultVXCV(String selectedCultVXCV) {
		this.selectedCultVXCV = selectedCultVXCV;
	}
	public String getSelectedVariedadVXCV() {
		return selectedVariedadVXCV;
	}
	public void setSelectedVariedadVXCV(String selectedVariedadVXCV) {
		this.selectedVariedadVXCV = selectedVariedadVXCV;
	}
	public String getSelectedVolumenVXCV() {
		return selectedVolumenVXCV;
	}
	public void setSelectedVolumenVXCV(String selectedVolumenVXCV) {
		this.selectedVolumenVXCV = selectedVolumenVXCV;
	}
	public List<CuotasEsquema> getLstCuotasEsquema() {
		return lstCuotasEsquema;
	}
	public void setLstCuotasEsquema(List<CuotasEsquema> lstCuotasEsquema) {
		this.lstCuotasEsquema = lstCuotasEsquema;
	}
	
	public Integer[] getSelecCiclo() {
		return selecCiclo;
	}
	public void setSelecCiclo(Integer[] selecCiclo) {
		this.selecCiclo = selecCiclo;
	}
	public Integer[] getSelecAnio() {
		return selecAnio;
	}
	public void setSelecAnio(Integer[] selecAnio) {
		this.selecAnio = selecAnio;
	}
	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Long getIdInicializa() {
		return idInicializa;
	}

	public void setIdInicializa(Long idInicializa) {
		this.idInicializa = idInicializa;
	}

	public Double[] getCuota() {
		return cuota;
	}

	public void setCuota(Double[] cuota) {
		this.cuota = cuota;
	}

	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	public int getIdCriterioPago() {
		return idCriterioPago;
	}
	public void setIdCriterioPago(int idCriterioPago) {
		this.idCriterioPago = idCriterioPago;
	}
	
	public int getIdCultivo() {
		return idCultivo;
	}
	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getMsjOk() {
		return msjOk;
	}

	public void setMsjOk(String msjOk) {
		this.msjOk = msjOk;
	}
		
	public String getMsjError() {
		return msjError;
	}
	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	public int getNumCiclos() {
		return numCiclos;
	}
	public void setNumCiclos(int numCiclos) {
		this.numCiclos = numCiclos;
	}
		
	public Integer getNumCamposVXCV() {
		return numCamposVXCV;
	}
	public void setNumCamposVXCV(Integer numCamposVXCV) {
		this.numCamposVXCV = numCamposVXCV;
	}
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public int getEditar() {
		return editar;
	}
	public void setEditar(int editar) {
		this.editar = editar;
	}
	public String getArchivoAvisoDOF() {
		return archivoAvisoDOF;
	}
	public void setArchivoAvisoDOF(String archivoAvisoDOF) {
		this.archivoAvisoDOF = archivoAvisoDOF;
	}
	public List<HcoProgramasV> getLstHcoProgramasV() {
		return lstHcoProgramasV;
	}
	public void setLstHcoProgramasV(List<HcoProgramasV> lstHcoProgramasV) {
		this.lstHcoProgramasV = lstHcoProgramasV;
	}
	public String getRutaPrograma() {
		return rutaPrograma;
	}
	public void setRutaPrograma(String rutaPrograma) {
		this.rutaPrograma = rutaPrograma;
	}
	public List<EtapaIniEsquemaV> getLstEtapaIniEsquemaV() {
		return lstEtapaIniEsquemaV;
	}
	public void setLstEtapaIniEsquemaV(List<EtapaIniEsquemaV> lstEtapaIniEsquemaV) {
		this.lstEtapaIniEsquemaV = lstEtapaIniEsquemaV;
	}
	public String getAbreviaturaUM() {
		return abreviaturaUM;
	}
	public void setAbreviaturaUM(String abreviaturaUM) {
		this.abreviaturaUM = abreviaturaUM;
	}
	public List<CultivoVariedadEsquema> getLstCultivoVariedadEsquema() {
		return lstCultivoVariedadEsquema;
	}
	public void setLstCultivoVariedadEsquema(
			List<CultivoVariedadEsquema> lstCultivoVariedadEsquema) {
		this.lstCultivoVariedadEsquema = lstCultivoVariedadEsquema;
	}
	public int getVolxCulVar() {
		return volxCulVar;
	}
	public void setVolxCulVar(int volxCulVar) {
		this.volxCulVar = volxCulVar;
	}
	public String[] getSelectEtapa() {
		return selectEtapa;
	}
	public void setSelectEtapa(String[] selectEtapa) {
		this.selectEtapa = selectEtapa;
	}
	public Double[] getSelectCM() {
		return selectCM;
	}
	public void setSelectCM(Double[] selectCM) {
		this.selectCM = selectCM;
	}
	
	public int getNumCamposVXCVAnterior() {
		return numCamposVXCVAnterior;
	}
	public void setNumCamposVXCVAnterior(int numCamposVXCVAnterior) {
		this.numCamposVXCVAnterior = numCamposVXCVAnterior;
	}
	
	public int getNoEtapaAnterior() {
		return noEtapaAnterior;
	}
	public void setNoEtapaAnterior(int noEtapaAnterior) {
		this.noEtapaAnterior = noEtapaAnterior;
	}
	
	public boolean isCartaAdhesionSistema() {
		return cartaAdhesionSistema;
	}
	public void setCartaAdhesionSistema(boolean cartaAdhesionSistema) {
		this.cartaAdhesionSistema = cartaAdhesionSistema;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getAcronimoCA() {
		return acronimoCA;
	}
	public void setAcronimoCA(String acronimoCA) {
		this.acronimoCA = acronimoCA;
	}
	public int getErrorValidaAcronimo() {
		return errorValidaAcronimo;
	}
	public void setErrorValidaAcronimo(int errorValidaAcronimo) {
		this.errorValidaAcronimo = errorValidaAcronimo;
	}
	public String getLeyendaAtentaNota() {
		return leyendaAtentaNota;
	}
	public void setLeyendaAtentaNota(String leyendaAtentaNota) {
		this.leyendaAtentaNota = leyendaAtentaNota;
	}

		
}
