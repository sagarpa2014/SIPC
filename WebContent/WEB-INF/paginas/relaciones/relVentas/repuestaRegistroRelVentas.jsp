<%@taglib uri="/struts-tags" prefix="s"%>
<div id="error" class="elementoOculto"><s:property value="%{error}"/></div>
<div id="cliente" class="elementoOculto"><s:property value="%{cliente}"/></div>
<div id="destino" class="elementoOculto"><s:property value="%{destino}"/></div>
<div id="usoGrano" class="elementoOculto"><s:property value="%{usoGrano}"/></div>
<div id="direccionCliente" class="elementoOculto"><s:property value="%{direccionCliente}"/></div>
<div id="idRelVentas" class="elementoOculto"><s:property value="%{idRelVentas}"/></div>
<s:if test="error!=0">
	<div class="msjError">No fue posible guardar la informaci&oacute;n, favor de reportar al administrador</div>
</s:if>

