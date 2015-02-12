<%@taglib uri="/struts-tags" prefix="s"%>

	<legend>Datos Generales</legend>
	<div>
		<label class="left1">Esquema:</label>
		<font class="arial12bold"><s:property value="%{nombreEsquema}"/></font>
	</div>
	<div class="clear"></div>
	<s:if test="nombreRelacion!=null">
		<div>
			<label class="left1">Tipo de Relaci&oacute;n:</label>
			<font class="arial12bold"><s:property value="%{nombreRelacion}"/></font>
		</div>
	</s:if>
	<div class="clear"></div>
	<s:if test="claveBodega!=null">
		<label class="left1">Bodega:</label>
		<font class="arial12bold"><s:property value="%{claveBodega}"/></font>
	</s:if>
	<div class="clear"></div>
	<s:if test="estadoBodega!=null">
		<label class="left1">Estado:</label>
		<font class="arial12bold"><s:property value="%{estado}"/></font>
	</s:if>
	<div class="clear"></div>
	<s:if test="nombreAlmacen!=null">
		<label class="left1">Raz&oacute;n Social del Almac&eacute;n General de Dep&oacute;sito:</label>
		<font class="arial12bold"><s:property value="%{nombreAlmacen}"/></font>
	</s:if>
	<div class="clear"></div>
	<s:if test="nombreBarco!=null">
		<label class="left1">Nombre Barco:</label>
		<font class="arial12bold"><s:property value="%{nombreBarco}"/></font>
	</s:if>
	<div class="clear"></div>
	<s:if test="lugarDestino!=null">
		<label class="left1">Lugar Destino:</label>
		<font class="arial12bold"><s:property value="%{lugarDestino}"/></font>
	</s:if>
	<div class="clear"></div>

