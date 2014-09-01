<%@taglib uri="/struts-tags" prefix="s"%>
<div id="errorSistema"><s:property value="%{errorSistema}"/></div>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>

<s:if test="%{errorSistema==0}">
	<s:include value="contenidoDocumentacion.jsp"/>
</s:if>
<s:else>
	<!-- hubo un error, por favor verifique -->
</s:else>
	


