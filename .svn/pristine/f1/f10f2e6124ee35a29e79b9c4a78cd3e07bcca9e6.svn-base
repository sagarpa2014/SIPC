<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<fieldset class="clear">
	<legend>Datos del oficio</legend>
	<div>
		<label class="left1">N&uacute;mero de Oficio:</label>
		<font class="arial12bold"><s:property value="%{oficioPagosV.noOficio}"/></font>	
	</div>
	<div>
		<label class="left1">Fecha Oficio</label>
		<font class="arial12bold"><s:text name="fecha"><s:param value="%{oficioPagosV.fechaOficio}"/></s:text></font>	
	</div>
	<div>
		<label class="left1">Total Pagos:</label>
		<font class="arial12bold"><s:text name="contador"><s:param value="%{oficioPagosV.totalPagos}"/></s:text></font>	
	</div>
	<s:if test="criterioPago==2 || criterioPago==3">
		<div>
			<label class="left1">Etapa:</label>
			<font class="arial12bold"><s:property value="%{oficioPagosV.etapa}"/></font>	
		</div>
	</s:if>
	<s:if test="criterioPago==1 || criterioPago==3">
		<div>
			<label class="left1">Total Volumen:</label>
			<font class="arial12bold"><s:text name="volumen"><s:param value="%{oficioPagosV.totalVolumen}"/></s:text></font>	
		</div>
	</s:if>
	<div>
		<label class="left1">Total Importe:</label>
		<font class="arial12bold">$<s:text name="importe"><s:param value="%{oficioPagosV.totalImporte}"/></s:text></font>	
	</div>
	
	<div>
		<label class="left1">Folio CLC:</label>
		<font class="arial12bold"><s:property value="%{oficioPagosV.folioClc}"/></font>	
	</div>
	<div>
		<label class="left1">Archivo DAT</label>
		<font class="arial12bold"><s:property value="%{oficioPagosV.archivoEnvio}"/></font>	
	</div>
	<div>
		<label class="left1">Aplicados:</label>
		<font class="arial12bold"><s:text name="contador"><s:param value="%{oficioPagosV.aplicados}"/></s:text></font>	
	</div>
	<s:if test="criterioPago==2 || criterioPago==3">
		<div>
			<label class="left1">Etapa:</label>
			<font class="arial12bold"><s:property value="%{oficioPagosV.etapa}"/></font>	
		</div>
	</s:if>
	<s:if test="criterioPago==1 || criterioPago==3">
		<div>
			<label class="left1">Volumen Pagos Aplicados:</label>
			<font class="arial12bold"><s:text name="volumen"><s:param value="%{oficioPagosV.totalVolumenAplicados}"/></s:text></font>	
		</div>
	</s:if>
	<div>
		<label class="left1">Importe Pagos Aplicados:</label>
		<font class="arial12bold">$<s:text name="importe"><s:param value="%{oficioPagosV.totalImporteAplicados}"/></s:text></font>	
	</div>	
	<div>
		<label class="left1">Rechazados:</label>
		<font class="arial12bold"><s:text name="contador"><s:param value="%{oficioPagosV.rechazados}"/></s:text></font>	
	</div>
	<s:if test="oficioPagosV.criterioPago==2 || oficioPagosV.criterioPago==3">
		<div>
			<label class="left1">Etapa:</label>
			<font class="arial12bold"><s:property value="%{oficioPagosV.etapa}"/></font>	
		</div>
	</s:if>
	<s:if test="oficioPagosV.criterioPago==1 || oficioPagosV.criterioPago==3">
		<div>
			<label class="left1">Volumen Pagos Rechazados:</label>
			<font class="arial12bold"><s:text name="volumen"><s:param value="%{oficioPagosV.totalVolumenRechazados}"/></s:text></font>	
		</div>
	</s:if>
	<div>
		<label class="left1">Importe Pagos Rechazados:</label>
		<font class="arial12bold">$<s:text name="importe"><s:param value="%{oficioPagosV.totalImporteRechazados}"/></s:text></font>	
	</div>
	
</fieldset>