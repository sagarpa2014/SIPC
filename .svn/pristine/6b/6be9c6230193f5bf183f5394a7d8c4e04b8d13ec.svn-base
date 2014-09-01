<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>ASIGNACIÓN DE <s:if test="idCriterioPago!=2">VOLUMEN</s:if><s:else>IMPORTE</s:else> DE LA CARTA DE ADHESI&Oacute;N</h1></div><br>
<s:form action="asignarCECartaAdhesion" method="post" enctype="multipart/form-data" onsubmit="return chkCamposACA();">
<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
<s:hidden id="idSI" name="idSI" value="%{idSI}"/>
<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
<s:hidden id="idCriterioPago" name="idCriterioPago" value="%{idCriterioPago}"/>
<s:hidden id="volumenXCultivoVariedad" name="volumenXCultivoVariedad" value="%{volumenXCultivoVariedad}"/>
<s:hidden id="fechaDOCSIInt" name="fechaDOCSIInt" value="%{fechaDOCSIInt}"/>
<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
<s:hidden id="idInicializacionEsquema" name="idInicializacionEsquema" value="%{idInicializacionEsquema}"/>
<fieldset>
		<legend>Asignaci&oacute;n</legend>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label>
		<font class="arial14boldVerde"><s:property value="%{folioCartaAdhesion}"/></font>
	</div>
	<div class="clear"></div>
	<div class ="izquierda">
		<label class="left1"><span class="requerido">*</span>Archivo Carta Adhesi&oacute;n:</label>
		<s:if test="registrar==2 || registrar == 3">
			<s:if test="docCartaAFileName !='' && docCartaAFileName!=null">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docCartaAFileName}"/>" title="Carta Adhesi&oacute;n">Carta Adhesi&oacute;n</a>
			</s:if>
			<s:else><s:file  name="docCartaA" id="docCartaA"/></s:else>
		</s:if>
		<s:else><s:file  name="docCartaA" id="docCartaA"/></s:else>
	</div>
	<s:if test="registrar == 3 && lstDetAsigCAC.size() == 0">
		<div class ="derecha">
			<label class="left1"><span class="norequerido">*</span>Complemento por Ampliaci&oacute;n:</label>
			<s:checkbox id="complementoPorampliacionChk" name="complementoPorampliacionChk" onclick="complementoAmpliacion();"/>
		</div>
	</s:if>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Firma de Carta Adhesi&oacute;n:</label>
		<s:if test="%{fechaFirmaCA==null}" >
			<s:textfield name="fechaFirmaCA" maxlength="10" size="10" id="fechaFirmaCA" readonly="true" cssClass="dateBox" />
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
				
					Calendar.setup({
						inputField     :    "fechaFirmaCA",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trg1",  
						align          :    "Br",           
						singleClick    :    true
					});
				//-->
			</script>
		</s:if>
		<s:else>
			<s:textfield key="fechaFirmaCA" value="%{getText('fecha1',{fechaFirmaCA})}" maxlength="10" size="10" id="fechaFirmaCA" readonly="true" cssClass="dateBox" />
		</s:else>				
	</div>
	
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Oficio de Elegibilidad:</label>
		<s:if test="registrar==2 || registrar == 3">
			<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docOLFileName}"/>" title="Oficio Legibilidad">Oficio Elegibilidad</a>
		</s:if>
		<s:else><s:file  name="docOL" id="docOL"/></s:else>		
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Documento:</label>
		<s:if test="%{fechaDocLeg==null}" >
			<s:textfield name="fechaDocLeg" maxlength="10" size="10" id="fechaDocLeg" readonly="true" cssClass="dateBox" />	
			<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />		
			<script type="text/javascript">
				<!--			
					Calendar.setup({
					inputField     :    "fechaDocLeg",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg2",  
					align          :    "Br",           
					singleClick    :    true
					});
				//-->
			</script>
		</s:if>
		<s:else>
			<s:textfield key="fechaDocLeg"  value="%{getText('fecha1',{fechaDocLeg})}"  maxlength="10" size="10" id="fechaDocLeg" readonly="true" cssClass="dateBox" />
		</s:else>
				
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Acuse:</label>
		
		<s:if test="%{fechaAcuseLeg==null}" >
			<s:textfield name="fechaAcuseLeg" maxlength="10" size="10" id="fechaAcuseLeg" readonly="true" cssClass="dateBox" />	
			<img src="../images/calendar.gif" id="trg3" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />		
			<script type="text/javascript">
				<!--
					Calendar.setup({
					inputField     :    "fechaAcuseLeg",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg3",  
					align          :    "Br",           
					singleClick    :    true
					});		
				//-->
			</script>			
		</s:if>
		<s:else>
			<s:textfield key="fechaAcuseLeg"  value="%{getText('fecha1',{fechaAcuseLeg})}"  maxlength="10" size="10" id="fechaAcuseLeg" readonly="true" cssClass="dateBox" />
		</s:else>		
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
		<s:textfield id="noOficioLeg" name="noOficioLeg"  maxlength="30" size="30"  value="%{noOficioLeg}"/>
	</div>
	<div class="clear"></div>
	<s:if test="idCriterioPago==1 || idCriterioPago==3"> <!-- Volumen o Volumen/Etapa -->
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Volumen Autorizado por Aviso:</label>
			<s:textfield id="volumenAutorizado" name="volumenAutorizado"  maxlength="30" size="30"  value="%{getText('volumenSinComas',{volumenAutorizado})}" disabled="true"/>
		</div>
		<div class="clear"></div>
		<s:hidden id="volumenDisponibleValidar" name="volumenDisponible" value="%{volumenDisponible}"/>
		<div>
			<label class="left1"><span class="norequerido">*</span>Volumen Disponible:</label>
			<s:textfield id="volumenDisponible" name="volumenDisponible"  maxlength="30" size="30"  value="%{getText('volumenSinComas',{volumenDisponible})}" disabled="true"/>
		</div>
		<div class="clear"></div>
		<s:hidden id="volumenInscripcionValidar" name="volumenInscripcionValidar" value="%{volumenInscripcion}"/>
		<div>
			<label class="left1"><span class="norequerido">*</span>Volumen Solicitud Inscripci&oacute;n:</label>
			<s:textfield id="volumenInscripcion" name="volumenInscripcion"  maxlength="30" size="30"  value="%{getText('volumenSinComas',{volumenInscripcion})}" disabled="true"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Volumen M&aacute;ximo a Apoyar:</label>
			<s:if test="volumenApoyar!=null">
			
				<s:hidden id="volumenApoyarAux" name="" value="%{volumenApoyar}"/>
				<s:textfield id="totalesVolumen" name="volumenApoyar"  maxlength="30" size="30"  value="%{getText('volumenSinComas',{volumenApoyar})}" disabled="true"/>
			</s:if>
			<s:else>
				<s:textfield id="totalesVolumen" name="volumenApoyar"  maxlength="30" size="30"  value="%{}" disabled="true"/>
			</s:else>
		</div>
	</s:if>
	<s:else> <!-- Etapa -->
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Importe Autorizado</label>
			<s:textfield id="importeAutorizado" name="importeAutorizado"  maxlength="30" size="30"  value="%{getText('importeSinComas',{importeAutorizado})}"/>
		</div>
		<div class="clear"></div>
		<s:hidden id="importeDisponibleValidar" name="importeDisponibleValidar" value="%{importeDisponible}"/>
		<div>
			<label class="left1"><span class="norequerido">*</span>Importe Disponible</label>
			<s:textfield id="importeDisponible" name="importeDisponible"  maxlength="30" size="30"  value="%{getText('importeSinComas',{importeDisponible})}" disabled="true"/>
		</div>
		<div class="clear"></div>
		<s:hidden id="importeInscripcionValidar" name="importeInscripcionValidar" value="%{importeInscripcion}"/>
		<div>
			<label class="left1"><span class="norequerido">*</span>Importe Inscripci&oacute;n</label>
			<s:textfield id="importeInscripcion" name="importeInscripcion"  maxlength="30" size="30"  value="%{getText('importeSinComas',{importeInscripcion})}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Importe M&aacute;ximo a Apoyar</label>
			<s:if test="importeApoyar!=null">
				<s:textfield id="totalesImporte" name="importeApoyar"  maxlength="30" size="30"  value="%{getText('importeSinComas',{importeApoyar})}"/>
			</s:if>
			<s:else>
				<s:textfield id="totalesImporte" name="importeApoyar"  maxlength="30" size="30"  value="%{}"/>
			</s:else>			
		</div>
	</s:else>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Cultivo, Variedad, Estados a Apoyar:</label>
		<s:textfield id="numCampos" name="numCampos"  maxlength="3" size="5"  value="%{numCampos}"/>
	</div>	
	<div class="clear"></div>	
	<div id="agregaCultivoVariedadEstadoCA"></div>
	<div class="clear"></div>
	<s:if test=" registrar==2 || registrar == 3">
		<s:include value="cultivoEstadoConOriginal.jsp"/>
	</s:if>
	<div id="complementoPorAmpliacion"></div>
	<s:if test="lstDetAsigCAC.size()>0">
		<s:include value="complementoXAmpliacion.jsp"/>
	</s:if>
</fieldset>
<s:if test="registrar==0 || registrar==1 || registrar == 3">	
	<div class="accion">
		<s:if test="docCartaAFileName =='' || docCartaAFileName==null">
			<s:submit  value="Guardar" cssClass="boton2"/>
			<a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>" class="boton" title="" >Cancelar</a>
		</s:if>
	</div>
</s:if>
<div class="izquierda"><a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
<s:if test="registrar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');
		});	 
	</script>
</s:if>
<s:if test="registrar == 3"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("#noOficioLeg").attr('disabled','disabled');
			$("#numCampos").attr('disabled','disabled');
		});	 
	</script>
</s:if>
</s:form>

<script>
		$(document).ready(function() {
			$("#numCampos").keyup(function(event){
					//manda a llamar
					var numCampos = $('#numCampos').val();
					var idCriterioPago = $('#idCriterioPago').val();
					var idInicializacionEsquema = $('#idInicializacionEsquema').val();					
					var volumenXCultivoVariedad = $('#volumenXCultivoVariedad').val();					
					if(numCampos == null || numCampos == ""){
						return false;
					}
					
					var patron =/^\d{1,3}$/;
					if(!numCampos.match(patron)){
			    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 3 dígitos');
			       		abrirDialogo();
			       		$('#numCampos').val(null);
			       		return false;
			    	}  
					
					$.ajax({
						   async: false,
						   type: "POST",
						   url: "agregaCultivoVariedadEstadoCA",
						   data: "numCampos="+numCampos+
						   	"&idInicializacionEsquema="+idInicializacionEsquema+
					   		"&idCriterioPago="+idCriterioPago+
					   		"&volumenXCultivoVariedad="+volumenXCultivoVariedad,					   		 
						   success: function(data){
								$('#agregaCultivoVariedadEstadoCA').html(data).ready(function () {
									$("#agregaCultivoVariedadEstadoCA").fadeIn('slow');
									
								});
						   }
						});//fin ajax
				});//termina keyup numCampos	
		});	 
	</script>
<script type="text/javascript">
	
</script>		