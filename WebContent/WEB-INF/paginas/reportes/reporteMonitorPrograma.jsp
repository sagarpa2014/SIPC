<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<div class="borderBottom"><h1>MONITOR PAGOS POR PROGRAMA-COMPRADOR</h1></div><br>
<div id="dialogo_1"></div>
<s:form action="reporteMonitorPrograma" onsubmit="return chkCampoConsultaReportes();">
	<fieldset class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Fecha Inicio:</label>	
			<s:if test="%{fechaInicio==null}" >
				<s:textfield name="fechaInicio" maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaInicio" value="%{fechaInicio}" maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div>
			<label class="left1">Fecha Fin:</label>	
			<s:if test="%{fechaFin==null}" >
				<s:textfield name="fechaFin" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaFin" value="%{fechaFin}" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div class="accion">
			<a href='#' onclick="limpiarCampos()" class="boton">Limpiar</a>
			<s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>

	<div class="clear"></div>
	<s:if test="lstReporteProgramaV.size() > 0">
		<fieldset class="clear">
			<legend>Totales</legend>
			<table class="totales" style="width:100%">
				<tr>
					<th colspan="3">Tramitados</th>
					<th colspan="3">Pagados</th>
					<th colspan="3">Rechazados</th>
					<th colspan="3">Pendientes</th>
				</tr>
				<tr>
					<th>Pagos</th>
					<th>Volumen</th>
					<th>Importe</th>
					<th>Pagos</th>
					<th>Volumen</th>
					<th>Importe</th>
					<th>Pagos</th>
					<th>Volumen</th>
					<th>Importe</th>
					<th>Pagos</th>
					<th>Volumen</th>
					<th>Importe</th>
				</tr>
				<tr>
					<td><font class="arial12bold"><s:text name="contador"><s:param value="%{totalPagosTramitados}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumenTramitados}"/></s:text></font></td>
					<td><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporteTramitados}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="contador"><s:param value="%{totalPagosPagados}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumenPagados}"/></s:text></font></td>
					<td><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImportePagados}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="contador"><s:param value="%{totalPagosRechazados}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumenRechazados}"/></s:text></font></td>
					<td><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporteRechazados}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="contador"><s:param value="%{totalPagosPendientes}"/></s:text></font></td>
					<td><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumenPendientes}"/></s:text></font></td>
					<td><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImportePendientes}"/></s:text></font></td>
				</tr>
			</table>	
		</fieldset>
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReportePrograma"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
			<display:table id="r"  name="lstReporteProgramaV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteProgramaV"  pagesize="20" sort="list" requestURI="/reportes/reporteMonitorPrograma"  class="displaytag">
				<display:column  property="programa" title="Programa"/>
				<display:column  property="comprador" title="Comprador"/>		
				<display:column  title="Tramitados" class="c">
					<s:text name="contador"><s:param value="%{#attr.r.pagosTramitados}"/></s:text>
				</display:column>
				<display:column  title="Volumen Tramitado" class="d">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenTramitado}"/></s:text>
				</display:column>
				<display:column  title="Importe Tramitado" class="d">
					$<s:text name="importe"><s:param value="%{#attr.r.importeTramitado}"/></s:text>
				</display:column>
				<display:column  title="Pagados" class="c">
					<s:text name="contador"><s:param value="%{#attr.r.pagosPagados}"/></s:text>
				</display:column>
				<display:column  title="Volumen Pagado" class="d">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenPagado}"/></s:text>
				</display:column>
				<display:column  title="Importe Pagado" class="d">
					$<s:text name="importe"><s:param value="%{#attr.r.importePagado}"/></s:text>
				</display:column>
				<display:column  title="Rechazados" class="c">
					<s:text name="contador"><s:param value="%{#attr.r.pagosRechazados}"/></s:text>
				</display:column>
				<display:column  title="Volumen Rechazado" class="d">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenRechazado}"/></s:text>
				</display:column>
				<display:column  title="Importe Rechazado" class="d">
					$<s:text name="importe"><s:param value="%{#attr.r.importeRechazado}"/></s:text>
				</display:column>
				<display:column  title="Pendientes" class="c">
					<s:text name="contador"><s:param value="%{#attr.r.pagosPendientes}"/></s:text>
				</display:column>
				<display:column  title="Volumen Pendiente" class="d">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenPendiente}"/></s:text>
				</display:column>
				<display:column  title="Importe Pendiente" class="d">
					$<s:text name="importe"><s:param value="%{#attr.r.importePendiente}"/></s:text>
				</display:column>
			 </display:table>
			 
		</fieldset>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros para el reporte seleccionado</div></s:else>
</s:form>

<script type="text/javascript">
	<!--
		Calendar.setup({
			inputField     :    "fechaInicio",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg1",  
			align          :    "Br",           
			singleClick    :    true
		});
		Calendar.setup({
			inputField     :    "fechaFin",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg2",  
			align          :    "Br",           
			singleClick    :    true
			});
	//-->
</script>

<script type="text/javascript">
<!--
function limpiarCampos(){ 
	$('#fechaInicio').val("");
	$('#fechaFin').val("");	
}
//-->   
</script>
