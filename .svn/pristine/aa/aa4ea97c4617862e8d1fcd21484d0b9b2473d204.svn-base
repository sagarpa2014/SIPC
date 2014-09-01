<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<fieldset class="clear">
	<legend>Datos del pago</legend>
	<div>
		<label class="left1">Programa:</label>
		<font class="arial12bold"><s:property value="%{pagosV.nombrePgrCorto}"/></font>	
	</div>
	<div>
		<label class="left1">Nombre Comprador:</label>
		<font class="arial12bold"><s:property value="%{pagosV.nombreComprador}"/></font>
	</div>
	<div>
		<label class="left1">RFC:</label>
		<font class="arial12bold"><s:property value="%{pagosV.rfc}"/></font>	
	</div>
	<s:if test="comprador.curp!=null && comprador.curp!=''">
		<div>
			<label class="left1">CURP:</label>
			<font class="arial12bold"><s:property value="%{pagosV.curp}"/></font>	
		</div>
	</s:if>
	<div>
		<label class="left1">Clabe Interbancaria:</label>
		<font class="arial12bold"><s:property value="%{pagosV.clabe}"/></font>	
	</div>
	<div>
		<label class="left1">No. Carta:</label>
		<font class="arial12bold"><s:property value="%{pagosV.noCarta}"/></font>	
	</div>
	<s:if test="criterioPago==2 || criterioPago==3">
		<div>
			<label class="left1">Etapa:</label>
			<font class="arial12bold"><s:property value="%{pagosV.etapa}"/></font>
		</div>
	</s:if>
	<s:if test="criterioPago==1 || criterioPago==3">
		<div>
			<label class="left1">Volumen:</label>
			<font class="arial12bold"><s:text name="volumen"><s:param value="%{pagosV.volumen}"/></s:text></font>
		</div>
	</s:if>
	
	<div>
		<label class="left1">Importe:</label>
		<font class="arial12bold">$&nbsp;<s:text name="importe"><s:param value="%{pagosV.importe}"/></s:text></font>
	</div>
	<div>
		<label class="left1">Fecha Pago:</label>
		<font class="arial12bold"><s:property value="%{pagosV.fechaPago}"/></font>
	</div>
	
	<s:if test="lstPagosDetalleV.size() > 0">
		<div>
			<label class="left1">Estados a apoyar:</label>
			<table >
				<tr>
					<th>Estado </th>
					<th>Cultivo </th>
					<th>Variedad </th>
					<s:if test="criterioPago==2 || criterioPago==3">
						<th>Etapa</th>
					</s:if>
					<s:if test="criterioPago==1 || criterioPago==3">
						<th>Volumen</th>
					</s:if>				
					<th>Importe</th>
				</tr>
				<s:iterator value="lstPagosDetalleV" var="resultado"  status="itStatus">
					<tr>
						<td class="td1"><s:property value="%{estado}"/></td>						
						<td class="td1" align="right"><s:text name="cultivo"><s:param value="%{cultivo}"/></s:text></td>
						<td class="td1" align="right"><s:text name="variedad"><s:param value="%{variedad}"/></s:text></td>
						<s:if test="criterioPago==2 || criterioPago==3">
							<td class="td1" align="center"><s:property value="%{etapa}"/></td>
						</s:if>
						<s:if test="criterioPago==1 || criterioPago==3">
							<td class="td1" align="right"><s:text name="volumen"><s:param value="%{volumen}"/></s:text></td>
						</s:if>
						<td class="td1" align="right"><s:text name="importe"><s:param value="%{importe}"/></s:text></td>
					</tr>
				</s:iterator>
			</table>
		</div>
				
	</s:if>
	
</fieldset>