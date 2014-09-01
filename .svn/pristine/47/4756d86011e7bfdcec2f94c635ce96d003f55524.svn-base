<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<fieldset class="clear">
	<legend>Datos del oficio</legend>
	<div>
		<label class="left1">N&uacute;mero de Oficio:</label>
		<font class="arial12bold"><s:property value="%{oficioPagosV.noOficio}"/></font>	
	</div>
	<div>
		<label class="left1">Total Pagos:</label>
		<font class="arial12bold"><s:text name="contador"><s:param value="%{oficioPagosV.totalPagos}"/></s:text></font>	
	</div>
	<div>
		<label class="left1">Total Importe:</label>
		<font class="arial12bold">$<s:text name="importe"><s:param value="%{oficioPagosV.totalImporte}"/></s:text></font>	
	</div>
	<div>
		<label class="left1">Total Volumen:</label>
		<font class="arial12bold"><s:text name="volumen"><s:param value="%{oficioPagosV.totalVolumen}"/></s:text></font>	
	</div>
	<s:if test="%{oficioPagosV.archivoEnvio!='' && oficioPagosV.archivoEnvio!=null }">
		<div>
			<label class="left1">Folio CLC:</label>
			<font class="arial12bold"><s:property value="%{oficioPagosV.folioCLC}"/></font>	
		</div>
		<div>
			<label class="left1">Archivo DAT</label>
			<font class="arial12bold"><s:property value="%{oficioPagosV.archivoEnvio}"/></font>	
		</div>
		<div>
			<label class="left1">Fecha Presentaci&oacute;n</label>
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{oficioPagosV.fechaPresentacion}"/></s:text></font>	
		</div>
		<div>
			<label class="left1">Fecha Pago</label>
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{oficioPagosV.fechaPago}"/></s:text></font>	
		</div>		
	</s:if>
</fieldset>