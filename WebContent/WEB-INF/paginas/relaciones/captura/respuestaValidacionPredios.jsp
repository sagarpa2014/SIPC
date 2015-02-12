<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorValidacion==1}">
	<div class="msjError1">No se encuentra registrado el predio que captur&oacute;, por favor verifique</div>
</s:if>
<s:if test ="%{errorValidacion==2}">
	<div class="msjError1">La superficie capturada rebasa la superficie total del predio, puede ser que se haya capturado en otra bodega por favor verifique</div>
</s:if>
<s:hidden id="errorValidacion%{count}" name="" value="%{errorValidacion}"/>	
<div id="superficieTotal<s:property value="%{count}"/>" class="elementoOculto"><s:property value="%{superficieTotal}"/></div>