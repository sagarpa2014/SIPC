<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>ASIGNACI&Oacute;N DE CARTA ADHESI&Oacute;N</h1></div><br>
<s:form action="registraAsignacionCartaAdhesion"  method="post" enctype="multipart/form-data" onsubmit="return chkCamposAsignarCartaAdhesion();">
	<s:if test="lstProgramas.size()>0">
		<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
		<s:if test="registrar==3">
			<s:hidden id="idOficioCASP" name="idOficioCASP" value="%{idOficioCASP}"/>
		</s:if>
		
		<fieldset>		
			<div>	
				<label class="left1"><span class="requerido">*</span>Programa:</label>
				<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="" onchange="" value="%{idPrograma}"/>
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>Oficio Carta de Adhesi&oacute;n:</label>
				<s:if test="registrar==0">
					<s:file  name="docCA" id="docCA"/>
				</s:if>
				<s:elseif test="registrar==2 || registrar == 3">
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docCAFileName}"/>" title="Archivo">Descargar Oficio</a>
				</s:elseif>
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>Fecha Documento:</label>
				<s:if test="%{fechaDocCA==null}" >
					<s:textfield name="fechaDocCA" maxlength="10" size="10" id="fechaDocCA" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield key="fechaDocCA" value="%{getText('fecha1',{fechaDocCA})}"  name="fechaDocCA" maxlength="10" size="10" id="fechaDocCA" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>Fecha Acuse:</label>
				<s:if test="%{fechaAcuseCA==null}" >
					<s:textfield name="fechaAcuseCA" maxlength="10" size="10" id="fechaAcuseCA" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield key="fechaAcuseCA" value="%{getText('fecha1',{fechaAcuseCA})}" name="fechaAcuseCA" maxlength="10" size="10" id="fechaAcuseCA" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>No. de Oficio:</label>
				<s:textfield id="noOficioCA" name="noOficioCA" maxlength="30" size="30"  value="%{noOficioCA}"/>
			</div>
			<div class="clear"></div>	
			<div>
				<label class="left1"><span class="requerido">*</span>N&uacute;meros de Carta Adhesi&oacute;n:</label>
				<s:textfield id="numCamposACA" name="numCamposACA"  maxlength="2" size="5"  value="%{numCamposACA}"/>
			</div>
			<div id="agregarCamposAsignacionCA">
				<s:if test="registrar==2 || registrar == 3">	
					<s:include value="/WEB-INF/paginas/solicitudPago/asignacionCartaAdhesion/agregarCamposAsigancionCA.jsp"/>
				</s:if>
			</div>
			<div class="clear"></div>							
		</fieldset>
		<s:if test="registrar==0">
			<div class="accion">
				<s:submit  value="Guardar" cssClass="boton2" />
				<a href="<s:url value="/solicitudPago/capAsignacionSolPago"/>" class="boton" title="" >Cancelar</a>
			</div>
		</s:if>
		<s:elseif test="registrar==2">
			<div class="derecha"><a href="<s:url value="/solicitudPago/capAsignacionSolPago"/>" onclick="" title="Capturar Asignaci&oacute;n Carta Adhesi&oacute;n" >[Capturar Asignaci&oacute;n Carta Adhesi&oacute;n]</a></div>
			<script>
				$(document).ready(function() {	
					$("input").attr('disabled','disabled');
					$("select").attr('disabled','disabled');
				});	 
			</script>
		</s:elseif>
		<s:elseif test="registrar==3">
			<script>
				$(document).ready(function() {	
					$("#idPrograma").attr('disabled','disabled');
					$("#noOficioCA").attr('disabled','disabled');
					$("#numCamposACA").attr('disabled','disabled');
				});	 
			</script>
			<div class="accion">
				<s:submit  value="Guardar" cssClass="boton2" />
				<a href="<s:url value="/solicitudPago/listarAsignacionCAEspecialista"/>" class="boton" title="" >Cancelar</a>
			</div>
		</s:elseif>
			<s:if test="registrar==0">
			<script type="text/javascript">
				<!--
					Calendar.setup({
						inputField     :    "fechaDocCA",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trg1",  
						align          :    "Br",           
						singleClick    :    true
					});		
					Calendar.setup({
						inputField     :    "fechaAcuseCA",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trg2",  
						align          :    "Br",           
						singleClick    :    true
					});			
					//-->
			</script>
		</s:if>
	</s:if>
	<s:else>
		<div class="advertencia">No se encontraron registros listos para asignar cartas</div>
	</s:else>
	<br>
	<div class="izquierda"><a href="<s:url value="/solicitudPago/listarAsignacionCAEspecialista"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>

</s:form>
