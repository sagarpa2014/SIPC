<%@taglib uri="/struts-tags" prefix="s"%>

<div class="clear"></div>
<center>
	<table class="clean">
		<tr>
			<th class="clean">
			<th class="clean">Auditor</th>
			<th class="clean">Nombre</th>
			<th class="clean">Volumen</th>
		</tr>
		<s:iterator value="lstAuditorSolPagoV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{#itStatus.count}"/></td>
				<td>
					<s:textfield id="" name="" maxlength="15" size="20" value="%{numeroAuditor}"  disabled="true"/>
				</td>
				<td>
					<s:textfield id=""  maxlength="15" size="50" value="%{nombre}"  disabled="true"/>
				</td>
				<td>
					<s:textfield id="" name="" maxlength="15" size="20"  cssClass="cantidad" value="%{getText('volumen',{volumenAuditor})}"  disabled="true"/>					
				</td>					
			</tr>	
		</s:iterator>
	</table>
</center>

