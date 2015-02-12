<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorClaveBodega==1}">
	<div class="msjError1">Clave Inexistente</div>
</s:if>
<s:if test="%{errorClaveBodegaExiste==1}">
	<div class="msjError1">La clave de la Bodega ya se encuentra en otro registro, favor de verificar</div>
</s:if>
<div id="errorCB" class="elementoOculto"><s:property value="%{errorClaveBodega}"/></div>
<div id="errorCBExiste" class="elementoOculto"><s:property value="%{errorClaveBodegaExiste}"/></div>
<div id="domicilio" class="elementoOculto"><s:property value="%{domicilio}"/></div>
