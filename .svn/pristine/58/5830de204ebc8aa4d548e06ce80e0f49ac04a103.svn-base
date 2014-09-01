<%@taglib uri="/struts-tags" prefix="s"%>

<div id="paginando">

	<s:if test="lstReporte.size() > 0">
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportarExcelReporteDinamico"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
			<legend>Resultado del Reporte</legend>
			<table class="displaytag">
				<tr><s:property value="%{encabezado}" escape=""/></tr>
				<s:property value="%{registroDetalle}" escape=""/>
			</table>
			<table class="clean">
				<tr>
					<td>Registros encontrados: <s:property value="#session.totalRegistros" /></td>
					<s:if test="%{(pagina-1)>0}">
						<s:set var="pag" value="%{pagina-1}" />
						<td><a class="paginaAnteriorM" href="#" title="" onclick="irPaginando(<s:property value="%{pagina-1}"/>)"><span>&nbsp;&lsaquo;&nbsp;</span></a></td>
					</s:if>
					<s:iterator value="lstTotalPaginas" var="resultado"  status="itStatus">
						<s:if test="%{pagina==#itStatus.count}">
							<td><font class="paginaSeleccionadaM"><s:property value="%{pagina}"/> </font></td>
						</s:if>
						<s:else><td><a href="#" class="paginador" title="" onclick="irPaginando(<s:property value="%{#itStatus.count}"/>)"><s:property value="%{#itStatus.count}"/></a></td> </s:else>
					</s:iterator>
					<s:if test="%{(pagina+1)<=lstTotalPaginas.size()}">
						<td><a class="paginaSiguienteM" href="#" title="" onclick="irPaginando(<s:property value="%{pagina+1}"/>)"><span>&nbsp;&rsaquo;&nbsp;</span></a></td>
					</s:if>								
				</tr>
			</table>
		</fieldset>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
	
</div>