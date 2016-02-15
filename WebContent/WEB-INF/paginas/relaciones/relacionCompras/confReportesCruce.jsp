<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>CONFIGURACI&Oacute;N DE REPORTES DE CRUCE</h1></div><br>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<s:form action="registrarConfReportesCruce"  onsubmit="return chkCamposConfReporteCruce();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	
	<fieldset>
		<div>	
			<label class="left1"><span class="norequerido">*</span>Programa:</label>
			<font class="arial12bold"><s:property value="%{programa.descripcionCorta}"/></font>
		</div>	
		<div class="clear"></div>	
		<div class="izquierda">
			<label class="left1"><span class="requerido">*</span>Reportes a aplicar:</label>
		</div>		
		<div class="izquierda">				
			<table>
				<tr>
					<th>Grupo</th>
					<th>Reporte</th>
					<th><input id="o1" name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>					
				</tr>				
				<s:iterator value="lstCatCriteriosValidacion" var="resultado"  status="itStatus">
					<tr>
						<td><s:property value="%{grupo}"/></td>
						<td><s:property value="%{criterio}"/></td>
						<td>
							<s:if test="%{idCriterio!=0}">
								<input id="" name="idExpedientesDoc" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck" checked="checked"/>
								
							</s:if>
							<s:else>
								<input id="" name="idExpedientesDoc" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck"/>
							</s:else>														
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
	