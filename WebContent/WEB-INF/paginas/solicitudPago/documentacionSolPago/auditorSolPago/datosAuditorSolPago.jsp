<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div id="dialogo_1"></div>
<s:form action="registrarAuditorSolPago" method="post" enctype="multipart/form-data" onsubmit="return chkCamposregistraAuditorSolPago();">
<fieldset>
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="tipoDocumentacion" name="tipoDocumentacion" value="%{tipoDocumentacion}"/>
	<s:hidden id="idAuditorSolPago" name="idAuditorSolPago" value="%{idAuditorSolPago}"/>
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
		<legend>Participante</legend>
		<div>
			<label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label>
			<font class="arial14boldVerde"><s:property value="%{folioCartaAdhesion}"/></font>
		</div>
		<div class="clear"></div>	
		<div>
			<label class="left1"><span class="norequerido">*</span>Participante:</label>
			<font class="arial12bold"><s:property value="%{comprador.nombre}"/></font>
		</div>
		<div class="clear"></div>
</fieldset>
<fieldset>	
		<legend>Datos del Auditor</legend>
		<div>
			<label class="left1"><span class="requerido">*</span>N&uacute;mero de Auditor:</label>
			<s:if test="%{registrar == 0}" >
				<s:textfield name="numeroAuditor"  id="numeroAuditor" maxlength="10" size="10" value="%{numeroAuditor}" onchange="validarNumeroAuditor(this.value);"/>
			</s:if>
			<s:else>
				<s:textfield name="numeroAuditor"  id="numeroAuditor" maxlength="10" size="10" value="%{numeroAuditor}" disabled="true"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div id="validarNumeroAuditor">
			<label class="left1"><span class="requerido">*</span>Nombre de Auditor:</label>
			<s:textfield id="" name="" maxlength="60" size="50"   value="%{nombreAuditor}" />
		</div>		
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Fecha Inicio:</label>
<%-- 			<s:textfield name="fechaPeriodoInicialAuditor" maxlength="10" size="10" id="fechaPeriodoInicialAuditor" readonly="true" cssClass="dateBox"/> --%>
			<s:if test="%{fechaPeriodoInicialAuditor==null}" >
				<s:textfield name="fechaPeriodoInicialAuditor" maxlength="10" size="10" id="fechaPeriodoInicialAuditor" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield value="%{getText('fecha1',{fechaPeriodoInicialAuditor})}" name="fechaPeriodoInicialAuditor" maxlength="10" size="10" id="fechaPeriodoInicialAuditor" readonly="true" cssClass="dateBox" />
			</s:else>		
			<img src="../images/calendar.gif" id="trgfechaPeriodoInicialAuditor" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
					Calendar.setup({
						inputField     :    "fechaPeriodoInicialAuditor",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trgfechaPeriodoInicialAuditor",  
						align          :    "Br",           
						singleClick    :    true
					});	
				//-->
			</script>		
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Fecha Fin:</label>
			<s:if test="%{fechaPeriodoInicialAuditor==null}" >
				<s:textfield name="fechaPeriodoFinalAuditor" maxlength="10" size="10" id="fechaPeriodoFinalAuditor" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield value="%{getText('fecha1',{fechaPeriodoFinalAuditor})}" name="fechaPeriodoFinalAuditor" maxlength="10" size="10" id="fechaPeriodoFinalAuditor" readonly="true" cssClass="dateBox" />
			</s:else>	
<%-- 			<s:textfield name="fechaPeriodoFinalAuditor" maxlength="10" size="10" id="fechaPeriodoFinalAuditor" readonly="true" cssClass="dateBox"/> --%>
			<img src="../images/calendar.gif" id="trgfechaPeriodoFinalAuditor" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
					Calendar.setup({
						inputField     :    "fechaPeriodoFinalAuditor",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trgfechaPeriodoFinalAuditor",  
						align          :    "Br",           
						singleClick    :    true
					});	
				//-->
			</script>		
		</div>
		<div class="clear"></div>
				
</fieldset>
<fieldset>
	<legend>Volumen</legend>
	<table class="clean">
		<tr>
			<td><span id="agregarCamposBodVolumen" class="tipoBoton" onclick="agregarCamposBodVol();">Agregar Volumen</span></td>
			<td><span id="removerCamposBodVolumen" class="tipoBoton" onclick="removerCamposBodVol();">Eliminar Volumen</span></td>
		</tr>
	</table>
	<div id="contenedorBodegaVolumen">
		<s:if test="registrar != 0">
			<s:include value="bodegaVolumen.jsp"/>
		</s:if>
<%-- 		<s:if test="registrar == 0"> --%>
<!-- 			<center><table id="tablaBodVol" class="clean"></table></center> -->
<%-- 		</s:if> --%>
<%-- 		<s:else> --%>
<%-- 			<s:include value="bodegaVolumen.jsp"/> --%>
<%-- 		</s:else> --%>
	</div>
	<s:hidden id="numCamposBodVolumen" name="numCamposBodVolumen" value="%{numCamposBodVolumen}"/>
</fieldset>

	<s:if test="registrar != 2">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
		</div>
	</s:if>
<div class="clear"></div>
<div class="izquierda"><a href="<s:url value="/solicitudPago/capAuditorSolPago?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=%{tipoDocumentacion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>

<s:if test="registrar == 2"> <!-- Consulta -->
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');				
			});		
		</script>
	</s:if>	
</s:form>