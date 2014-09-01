<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<div class="borderBottom"><h1>DETALLE DE APOYOS ENTREGADOS</h1></div><br>
<div id="dialogo_1"></div>
<fieldset class="clear">
	<legend>Subprograma y/o Esquema de Apoyo</legend>
	<div>				
		<font class="arial12bold"><s:property value="%{programa}"/></font>	
	</div>
</fieldset>
<div class="clear"></div>
<s:if test="lstReporteDetConcentradoPagosV.size() > 0">
 	<s:if test="trimestre == 0">
		<fieldset class="clear">
			<legend>Totales</legend>
			<table class="totales" style="width:100%">
				<tr>
					<th colspan="2">1er. Trimestre</th>
					<th colspan="2">Gran Total</th>
				</tr>
				<tr>
					<th>Volumen (ton.)</th>
					<th>Monto ($)</th>
					<th>Volumen (ton.)</th>
					<th>Monto Neto ($)</th>
				</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre}"/></s:text></font></td>
					</tr>					
			</table>	
		</fieldset>
		<br/>
			<div class="exporta_csv">
				<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagos?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}"/>" title="Exportar Datos" ></a>
			</div>
		<div class="clear"></div>
		<fieldset>
				<display:table id="r"  name="lstReporteDetConcentradoPagosV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentrado"  class="displaytag">
					<display:column  property="comprador" title="Comprador"/>
					<display:column  property="cartaAdhesion" title="No. Carta"/>
  					<display:column  title="1er. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestre}"/></s:text>
					</display:column>
					<display:column  property="estados" title="Origen del Grano"/>
					<display:column  property="ciclo" title="Ciclo"/>
					<display:column  property="producto" title="Producto"/>					
				 </display:table>
		</fieldset>
	</s:if>
		<s:elseif test="trimestre == 1">
			<fieldset class="clear">
				<legend>Totales</legend>
				<table class="totales" style="width:100%">
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">2do. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre+totalVolumen2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre+totalImporte2doTrimestre}"/></s:text></font></td>
					</tr>					
				</table>	
			</fieldset>
			<br/>
			<div class="exporta_csv">
				<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagos?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}"/>" title="Exportar Datos" ></a>
			</div>
			<div class="clear"></div>
			<fieldset>
				<display:table id="r"  name="lstReporteDetConcentradoPagosV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentrado"  class="displaytag">
					<display:column  property="comprador" title="Comprador"/>
					<display:column  property="cartaAdhesion" title="No. Carta"/>
					<display:column  title="1er. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestre}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestre}"/></s:text>
					</display:column>
					<display:column  property="estados" title="Origen del Grano"/>
					<display:column  property="ciclo" title="Ciclo"/>
					<display:column  property="producto" title="Producto"/>
				 </display:table>
			</fieldset>
		</s:elseif>		
		<s:elseif test="trimestre == 2">
			<fieldset class="clear">
				<legend>Totales</legend>
				<table class="totales" style="width:100%">
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">2do. Trimestre</th>
						<th colspan="2">3er. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre+totalVolumen2doTrimestre+totalVolumen3erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre+totalImporte2doTrimestre+totalImporte3erTrimestre}"/></s:text></font></td>						
					</tr>
				</table>	
			</fieldset>
			<br/>
			<div class="exporta_csv">
				<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagos?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}"/>" title="Exportar Datos" ></a>
			</div>
			<div class="clear"></div>
			<fieldset>
				<display:table id="r"  name="lstReporteDetConcentradoPagosV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentrado"  class="displaytag">
					<display:column  property="comprador" title="Comprador"/>
					<display:column  property="cartaAdhesion" title="No. Carta"/>
					<display:column  title="1er. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestre}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestre}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestre}"/></s:text>
					</display:column>
					<display:column  property="estados" title="Origen del Grano"/>
					<display:column  property="ciclo" title="Ciclo"/>
					<display:column  property="producto" title="Producto"/>
				 </display:table>
			</fieldset>
		</s:elseif>
		<s:elseif test="trimestre == 3">
			<fieldset class="clear">
				<legend>Totales</legend>
				<table class="totales" style="width:100%">
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">2do. Trimestre</th>
						<th colspan="2">3er. Trimestre</th>
						<th colspan="2">4to. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestre+totalVolumen2doTrimestre+totalVolumen3erTrimestre+totalVolumen4toTrimestre}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestre+totalImporte2doTrimestre+totalImporte3erTrimestre+totalImporte4toTrimestre}"/></s:text></font></td>						
					</tr>
				</table>	
			</fieldset>
			<br/>
			<div class="exporta_csv">
				<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagos?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}"/>" title="Exportar Datos" ></a>
			</div>
			<div class="clear"></div>
			<fieldset>
				<display:table id="r"  name="lstReporteDetConcentradoPagosV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentrado"  class="displaytag">
					<display:column  property="comprador" title="Comprador"/>
					<display:column  property="cartaAdhesion" title="No. Carta"/>
					<display:column  title="1er. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestre}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestre}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestre}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestre}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestre}"/></s:text>
					</display:column>
					<display:column  property="estados" title="Origen del Grano"/>
					<display:column  property="ciclo" title="Ciclo"/>
					<display:column  property="producto" title="Producto"/>
				 </display:table>
			</fieldset>
		</s:elseif>
</s:if>
<s:else><div class="advertencia">No se encontraron registros para el reporte seleccionado</div></s:else>