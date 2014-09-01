<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorClaveBodega==1}">
	<div class="msjError1">clave inexistente</div>
</s:if>
<div id="errorClaveBodega<s:property value="%{count}"/>" class="elementoOculto"><s:property value="%{errorClaveBodega}"/></div>