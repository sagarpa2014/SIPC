<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorClaveBodega==1}">
	<div class="msjError1">clave inexistente</div>
</s:if>
<div id="errorCB" class="elementoOculto"><s:property value="%{errorClaveBodega}"/></div>
<div id="nombreCentroAcopioR" class="elementoOculto"><s:property value="%{nombreCentroAcopio}"/></div>
<div id="nomRegionalCentroAcopioR" class="elementoOculto"><s:property value="%{nomRegionalCentroAcopio}"/></div>
<div id="operadorCentroAcopioR" class="elementoOculto"><s:property value="%{operadorCentroAcopio}"/></div>