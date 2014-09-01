<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="lstDetAsigCAO.size > 0">
<center>
	<div>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Estado</th>
				<th class="clean">Cultivo</th>
				<th class="clean">Variedad</th>				
				<s:if test="idCriterioPago==1 || idCriterioPago==3">
					<th class="clean">Volumen</th>
				</s:if>
				<s:if test="idCriterioPago==2">
					<th class="clean">Importe</th>
				</s:if>								
			</tr>	
			<s:iterator value="lstDetAsigCAO" var="resultado"  status="itStatus">
				<tr>					
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:select list="lstEstado" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idEstado}" disabled="true"/>
					</td>
					<td>
						<s:select list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idCultivo}"  disabled="true"/>
					</td>
					<td>
						<s:select  list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idVariedad}" disabled="true"/>
					</td>											
					<s:if test="idCriterioPago==1 || idCriterioPago==3">
						<td>
							<s:textfield value="%{getText('volumenSinComas',{volumen})}" maxlength="14" size="20"  cssClass="cantidad" disabled="true"/>								
						</td>
					</s:if>
					<s:elseif test="idCriterioPago==2">
						<td>
							<s:textfield  value="%{getText('importeSinComas',{importe})}"  maxlength="14" size="20" cssClass="cantidad" disabled="true"/>
						</td>
					</s:elseif>
				</tr>						
			</s:iterator>
		</table>
	</div>
</center>
</s:if>

