<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
	<s:if test="%{lstContratos.size() > 0}">
	<div class="borderBottom" style="text-align:center"><h1>Adendum</h1></div><br>
		<s:hidden id="numContratos" name="numContratos" value="%{lstContratos.size()}"/>
		<center>
			<table>
				<tr>
					<th>Folio del Contrato</th>
					<th>Fecha Inicio</th>
					<th>Fecha Fin</th>
					<th>Fecha Inicio Adendum</th>
					<th>Fecha Termino Adendum</th>
				</tr>
				<s:iterator value="lstContratos" status="itStatus">
					<tr>
						<td><s:property value="%{folioContrato}"/></td>
						<td><s:text name="fecha"><s:param value="%{periodoInicialPago}"/></s:text></td>
						<td><s:text name="fecha"><s:param value="%{periodoFinalPago}"/></s:text></td>
						<td>
							<s:if test="%{periodoInicialPagoAdendum==null}" >
								<s:textfield name="fechaInicioAdendumContrato['%{folioContrato}']" maxlength="10" size="10" id="fechaInicioAdendum%{#itStatus.count}"  cssClass="dateBox" />
							</s:if>
							<s:else>
								<s:textfield name="fechaInicioAdendumContrato['%{folioContrato}']" maxlength="10" size="10" id="fechaInicioAdendum%{#itStatus.count}"  cssClass="dateBox" value="%{getText('fecha1',{periodoInicialPagoAdendum})}" />
							</s:else>			
							<img width="16px" src="../images/calendar.gif" id="trgIC<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
							<script type="text/javascript">					
								Calendar.setup({
									inputField     :    'fechaInicioAdendum<s:property value="%{#itStatus.count}"/>',     
									ifFormat       :    "%d/%m/%Y",     
									button         :    'trgIC<s:property value="%{#itStatus.count}"/>',  
									align          :    "Br",           
									singleClick    :    true
								});					
							</script>
						</td>
						<td>
							<s:if test="%{periodoFinalPagoAdendum==null}">
								<s:textfield name="fechaFinAdendumContrato['%{folioContrato}']" maxlength="10" size="10" id="fechaFinAdendum%{#itStatus.count}"  cssClass="dateBox" />
							</s:if>
							<s:else>
								<s:textfield name="fechaFinAdendumContrato['%{folioContrato}']" maxlength="10" size="10" id="fechaFinAdendum%{#itStatus.count}" cssClass="dateBox" value="%{getText('fecha1',{periodoFinalPagoAdendum})}"/>
							</s:else>							
							<img width="16px" src="../images/calendar.gif" id="trgFC<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
							<script type="text/javascript">					
								Calendar.setup({
									inputField     :    'fechaFinAdendum<s:property value="%{#itStatus.count}" />',     
									ifFormat       :    "%d/%m/%Y",     
									button         :    'trgFC<s:property value="%{#itStatus.count}" />',  
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
	<s:else>
		<span class="requerido">No se encontraron los contraros en la tabla de aserca, por favor verifique con el administrador de sistema </span>
	</s:else>
