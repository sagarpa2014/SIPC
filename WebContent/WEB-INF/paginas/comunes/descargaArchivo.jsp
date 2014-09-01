<%@taglib uri="/struts-tags" prefix="s"%>

<s:if test="%{tipo!=null && tipo == 'pagos'}">
	<h1>Archivo de Pagos TESOFE</h1>
</s:if>

<s:if test="%{xls!=null}">
	<s:form  action="DescargarAchivo" cssClass="downloadBox">>	
		<s:hidden name="archivo"  value="%{xls}" />
		<s:hidden name="tipo"  value="%{tipo}" />
		<s:submit value="Descargar %{xls}"/>
	</s:form>
	<s:if test="%{tipo!=null && tipo == 'pagos'}">
 		<p><button type="button" onclick="window.close();">Cerrar</button></p>
 	</s:if>
</s:if>
<s:else>
	<s:if test="%{tipo!=null && tipo == 'pagos'}">
		<p>No hay Informaci&oacute;n de Pagos TESOFE por exportar</p>
	</s:if>
</s:else>