<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstProgramaRelacionCiclos.size() > 0">
	<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Ciclo</th>
				<th class="clean">Año</th>
			</tr>
			<s:iterator value="lstProgramaRelacionCiclos" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:select id="ci%{#itStatus.count}" name="selecCiclo" list="lstCiclos" listKey="idCiclo" listValue="%{cicloLargo}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idCiclo}" cssClass="cicloClass" />
					</td>
					<td>
						<s:select id="a%{#itStatus.count}" name="selecAnio" list="lstEjercicios" listKey="ejercicio" listValue="%{ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{ejercicio}" cssClass="anioClass" />
					</td>					
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>
