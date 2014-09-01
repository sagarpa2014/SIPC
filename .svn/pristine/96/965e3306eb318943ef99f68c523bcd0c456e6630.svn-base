<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>INICIALIZACI&Oacute;N DE LA SOLICITUD DE PAGO</h1></div><br>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<s:form action="registraInicializacionSP"  onsubmit="return chkCamposInicializacionSP();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	
	<fieldset>
		<div>	
			<label class="left1"><span class="requerido">*</span>Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="" onchange="verificarProgramaConfigurado();" value="%{idPrograma}"/>
		</div>
		<div class="clear"></div>
		<div id="verificarProgramaConfigurado"></div>
		<div class="clear"></div>
		<div class="izquierda">
			<label class="left1"><span class="requerido">*</span>Documentos a aplicar:</label>
		</div>
		<s:set name="encontrado">0</s:set>
		<div class="izquierda">				
			<table>
				<tr>
					<th>Documentaci&oacute;n</th>
					<th><input id="o1" name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>
					<th>Opcional
						<br>
					</th>
				</tr>				
				<s:iterator value="lstExpedientesDoc" var="resultado"  status="itStatus">
					<tr>
						<td><s:property value="%{expediente}"/></td>
						<td>
							<s:if test="registrar==0">
								<input id="" name="idExpedientesDoc" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck"/>
							</s:if>
							<s:elseif test="registrar==2 || registrar == 3">																
								<s:set name="tmp"><s:property value="idExpediente"/></s:set>	
								<s:iterator value="lstExpedienteProgramaDoc" var="resultado"  status="itStatus">
									<s:if test='#tmp==idExpediente'>
										<s:set var="encontrado" value="%{1}"/>
									</s:if>
								</s:iterator>
								<s:if test="%{#encontrado == 1}">
									<input id="" name="idExpedientesDoc" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck" checked="checked"/>
								</s:if>
								<s:else>
									<input id="" name="idExpedientesDoc" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck"/>
								</s:else>
								<s:set var="encontrado" value="%{0}"/>								
								
							</s:elseif>							
						</td>
						
						<td>
							<s:if test="registrar==0">
							<center><input id="" name="idExpedientesDoc1" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck2"/></center>
							</s:if>
							<s:elseif test="registrar==2 || registrar == 3">																
								<s:set name="tmp1"><s:property value="idExpediente"/></s:set>	
								<s:iterator value="lstExpedienteProgramaDoc" var="resultado"  status="itStatus">
									<s:if test='#tmp1==idExpediente && documentacionOpcional == true'>
										<s:set var="encontrado" value="%{1}"/>
									</s:if>
								</s:iterator>
								<s:if test="%{#encontrado == 1}">
									<center><input id="" name="idExpedientesDoc1" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck2" checked="checked"/></center>
								</s:if>
								<s:else>
									<center><input id="" name="idExpedientesDoc1" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck2"/></center>
								</s:else>
								<s:set var="encontrado" value="%{0}"/>
							</s:elseif>
							
						</td>

						
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="izquierda">
			<s:set name="encontrado">0</s:set>
			<table>
				<tr>
					<th>Fianza</th>
					<th><input id="o1" name="fianza" value="-1" type="checkbox" onclick ="chkTodos1();" class="check_todos1"/></th>
				</tr>
				<s:hidden id="numDocFianza" name="numDocFianza" value="%{lstExpedientesFianza.size()}"/>
				<s:iterator value="lstExpedientesFianza" var="resultado"  status="itStatus">
					<tr>
						<td><s:property value="%{expediente}"/></td>
						<td>
							<s:if test="registrar==0">
								<input id="" name="idExpedientesFianza" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck1" />
							</s:if>
							<s:elseif test="registrar==2 || registrar == 3">
								<s:if test="documentacion==2">
									<s:set name="tmp"><s:property value="idExpediente"/></s:set>
									<s:iterator value="lstExpedienteProgramaFianza" var="resultado"  status="itStatus">
										<s:if test='#tmp==idExpediente'>
											<s:set var="encontrado" value="%{1}"/>
										</s:if>
									</s:iterator>
									<s:if test="%{#encontrado == 1}">
										<input id="" name="idExpedientesFianza" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck1" checked="checked"/>
									</s:if>
									<s:else>
										<input id="" name="idExpedientesFianza" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck1"/>
									</s:else>
									<s:set var="encontrado" value="%{0}"/>
								</s:if>
								<s:else>
									<input id="" name="idExpedientesFianza" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck1"/>
								</s:else>
							</s:elseif>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</fieldset>
	<s:if test="registrar==0 || registrar==3">	
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/solicitudPago/programasConfigExpediente"/>" class="boton" title="" >Cancelar</a>
		</div>
	</s:if>
	<s:if test="registrar==2">
		<div class="derecha"><a href="<s:url value="/solicitudPago/capInicializacionSolPago"/>" onclick="" title="Capturar Inicializaci&oacute;n" >[Capturar Inicializaci&oacute;n]</a></div>
	</s:if>
	
	<div class="izquierda"><a href="<s:url value="/solicitudPago/programasConfigExpediente"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>

<s:if test="registrar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');
		});	 
	</script>
</s:if>
<s:if test="registrar == 3">
	<script>
			$(document).ready(function() {	
				$("#idPrograma").attr('disabled','disabled');
			});	 
	</script>
</s:if>
	