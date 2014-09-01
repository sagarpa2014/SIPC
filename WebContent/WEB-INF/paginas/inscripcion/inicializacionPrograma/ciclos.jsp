<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstCiclosProgramas.size() > 0">
<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Ciclo</th>
				<th class="clean">Año</th>
			</tr>
			<s:iterator value="lstCiclosProgramas" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:select id="ci%{#itStatus.count}" name="selecCiclo" list="lstCiclos" listKey="idCiclo" listValue="%{cicloLargo}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idCiclo}" />
					</td>
					<td>
						<s:select id="a%{#itStatus.count}" name="selecAnio" list="lstEjercicios" listKey="ejercicio" listValue="%{ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEjercicio}" />
					</td>					
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>
