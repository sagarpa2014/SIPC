<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="idCriterioPago==1">
	<s:select id="cuota%{count}" name="capCuota" list="lstCuota" listKey="cuota" listValue="%{getText('importeSinComas',{cuota})}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{cuota}" onchange="recuperaTotalVolumen(%{count});"/>
</s:if>
<s:elseif test=" idCriterioPago==3">
	<s:select id="cuota%{count}" name="capCuota" list="lstCuota" listKey="cuota" listValue="%{folioCartaAdhesion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{cuota}" onchange="recuperaTotalVolumen(%{count});"/>
</s:elseif>
<s:else>
	<s:select id="cuota%{count}" name="capCuota" list="lstCuota" listKey="cuota" listValue="%{getText('importeSinComas',{cuota})}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{cuota}" onchange="recuperaTotalVolumen(%{count});"/>
</s:else>
					
					
					  