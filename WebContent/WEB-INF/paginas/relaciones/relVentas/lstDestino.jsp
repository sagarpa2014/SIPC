<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{aplicaInternacional == true}">
	<s:select id="idDestinoC" style="width:200px" name="" list="lstDestinoInternacional" listKey="idPais" listValue="%{pais}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{idPais}"/>
</s:if>
<s:else>
	<s:select id="idDestinoC" style="width:200px" name="" list="lstDestinoNacional" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{idEstado}"/>
</s:else>
		