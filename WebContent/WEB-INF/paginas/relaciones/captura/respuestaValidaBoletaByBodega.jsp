<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorBodegaTicket > 0 }">
	<div class="msjError1">La boleta ya se encuentra capturada en la bodega, favor de verificar</div>
</s:if>
<div id="errorValidaBoletaByBodega<s:property value="%{count}"/>" class="elementoOculto"><s:property value="%{errorValidaBoletaByBodega}"/></div>