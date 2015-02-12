<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorProductores==1}">
	<div class="msjError1">El folio que capturó no se encuentra registrado, favor de verificar</div>
</s:if>
<s:elseif test="%{errorProductores==2}">
	<div class="msjError1">El folio que capturó ya se encuentra registrado para la bodega, favor de verificar</div>
</s:elseif>
<div id="errorProductoresR" class="elementoOculto"><s:property value="%{errorProductores}"/></div>
<div id="nombreProductor" class="elementoOculto"><s:property value="%{nombreProductor}"/></div>
<div id="paterno" class="elementoOculto"><s:property value="%{paterno}"/></div>
<div id="materno" class="elementoOculto"><s:property value="%{materno}"/></div>
<div id="rfc" class="elementoOculto"><s:property value="%{rfc}"/></div>
<div id="curp" class="elementoOculto"><s:property value="%{curp}"/></div>
<div id="tipoPersona" class="elementoOculto"><s:property value="%{tipoPersona}"/></div>
<s:include value="foliosPredios.jsp"></s:include>