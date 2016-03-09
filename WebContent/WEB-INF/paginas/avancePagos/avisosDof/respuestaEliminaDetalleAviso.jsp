<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<s:if test="registrosBorrados > 0">
	<font class="arial12boldVerde">Registro eliminado</font>
</s:if>
<s:elseif test="registrosBorrados == -1">
	<font class="arial12boldrojo">No es posible eliminar, ya existe avance capturado</font>
</s:elseif>
<s:else>
	<font class="arial12boldrojo">No hay nada que eliminar</font>
</s:else>



