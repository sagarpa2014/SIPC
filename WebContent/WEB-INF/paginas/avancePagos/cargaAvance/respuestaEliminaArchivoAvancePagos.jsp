<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<s:if test="registrosBorrados > 0">
	<font class="arial12boldVerde">Se eliminaron <s:property value="%{registrosBorrados}"/> registros</font>
</s:if>
<s:else>
	<font class="arial12boldrojo">No hay nada que eliminar</font>
</s:else>



