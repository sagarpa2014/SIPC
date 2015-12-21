<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{fechaInicio==null && fechaFin==null}" >
	<span class="requerido">No se ha capturado el periodo del dictamen del auditor, por favor capture la informaci&oacute;n en el apartado de la documentaci&oacute;n de la solicitud de pago</span><br>
</s:if>
<s:else>
	<s:include value="mostrarPeriodo.jsp"/>
</s:else>