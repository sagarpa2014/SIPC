<%@taglib uri="/struts-tags" prefix="s"%>
<div id="errorSistema" class="elementoOculto"><s:property value="%{errorSistema}"/></div>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>

<s:if test="%{errorSistema==0}">
	<s:include value="/WEB-INF/paginas/solicitudPago/capturaPago/detalleEditarPagosCA.jsp"/>
</s:if>
<s:else>
	<!-- hubo un error, por favor verifique -->
</s:else>
	


