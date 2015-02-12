<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="%{lstContratos.size() > 0}">
	<s:hidden id="numContratos" name="numContratos" value="%{lstContratos.size()}"/>
	<center>
		<table>
			<tr>
				<th>Folio del Contrato</th>
				<th>Capture la fecha del Contrato</th>
			</tr>
			<s:iterator value="lstContratos" status="itStatus">
				<s:set name="folioContrato"><s:property/></s:set>
				<tr>
					<td><s:property/></td>
					<td>
						<s:textfield name="fechaContratoTipoCambio['%{<s:property/>}']" maxlength="10" size="10" id="fechaContrato%{#itStatus.count}" readonly="true" cssClass="dateBox" />
						<img width="16px" src="../images/calendar.gif" id="trgC<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
						<script type="text/javascript">					
							Calendar.setup({
								inputField     :    'fechaContrato<s:property value="%{#itStatus.count}" />',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgC<s:property value="%{#itStatus.count}" />',  
								align          :    "Br",           
								singleClick    :    true
							});					
						</script>
					</td>
				</tr>
			</s:iterator>
		</table>
	</center>			
</s:if>