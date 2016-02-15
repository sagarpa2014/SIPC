<%@taglib uri="/struts-tags" prefix="s"%>
<div id="error" class="elementoOculto"><s:property value="%{error}"/></div>
<div id="programa" class="elementoOculto"><s:property value="%{programa}"/></div>
<div id="estado" class="elementoOculto"><s:property value="%{estado}"/></div>
<div id="cultivo" class="elementoOculto"><s:property value="%{cultivo}"/></div>
<s:if test="%{error!=0}">
	<div class="msjError">No fue posible guardar la informaci&oacute;n, favor de reportar al administrador</div>
</s:if>




