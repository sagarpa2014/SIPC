<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstCultivos.size() > 0">
	<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Cultivo</th>
			</tr>
			<s:iterator value="lstCultivos" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:select id="cultivo%{#itStatus.count}" name="selecCultivo" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idCultivo}" cssClass="cultivoClass" />
					</td>					
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>
