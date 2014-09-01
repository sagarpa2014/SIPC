<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<fieldset class="clear">
	<legend>Datos de la Bodega</legend>
	<div>
		<label class="left1">Clave de la Bodega:</label>
		<font class="arial12bold"><s:property value="%{bodegasV.claveBodega}"/></font>	
	</div>
	<div>
		<label class="left1">Nombre de la Bodega:</label>
		<font class="arial12bold"><s:property value="%{bodegasV.nombre}"/></font>
	</div>
	<div>
		<label class="left1">RFC:</label>
		<font class="arial12bold"><s:property value="%{bodegasV.rfcBodega}"/></font>	
	</div>
	<div>
		<label class="left1">Estado</label>
		<font class="arial12bold"><s:property value="%{bodegasV.nombreEstado}"/></font>	
	</div>
	<div>
		<label class="left1">Direcci&oacute;n:</label>
		<font class="arial12bold"><s:property value="%{bodegasV.direccion}"/></font>	
	</div>
	<div>
		<label class="left1">C&oacute;digo Postal:</label>
		<font class="arial12bold"><s:property value="%{bodegasV.codigoPostal}"/></font>
	</div>
	
</fieldset>