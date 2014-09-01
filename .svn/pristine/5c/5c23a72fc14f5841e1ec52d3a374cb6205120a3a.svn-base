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
<s:if test="lstReporteDetConcentradoPagosEtapasV.size() > 0">
 	<s:if test="trimestre == 0">
		<fieldset class="clear">
			<legend>Totales</legend>
			<table class="totales" style="width:100%">
				<s:if test="numeroEtapa == 1">
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
					</tr>
					<tr>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
					</tr>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<tr>
						<th colspan="4">1er. Trimestre</th>
						<th colspan="4">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
					</tr>
					<tr>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 3">
					<tr>
						<th colspan="6">1er. Trimestre</th>
						<th colspan="6">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 4">
					<tr>
						<th colspan="8">1er. Trimestre</th>
						<th colspan="8">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 5">
					<tr>
						<th colspan="8">1er. Trimestre</th>
						<th colspan="8">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV}"/></s:text></font></td>
					</tr>				
				</s:elseif>
			</table>	
		</fieldset>
		<br/>
			<div class="exporta_csv">
				<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagosEtapas?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}&numeroEtapa=%{numeroEtapa}"/>" title="Exportar Datos" ></a>
			</div>
		<div class="clear"></div>
		<fieldset>
				<display:table id="r"  name="lstReporteDetConcentradoPagosEtapasV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosEtapasV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentradoEtapas"  class="displaytag">
					<display:column  property="comprador" title="Comprador"/>
					<display:column  property="cartaAdhesion" title="No. Carta"/>
					<s:if test="numeroEtapa == 1">
	  					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
						</display:column>
					</s:if>
					<s:elseif test="numeroEtapa == 2">
	  					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
						</display:column>
	  					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
						</display:column>					
					</s:elseif>
					<s:elseif test="numeroEtapa == 3">
	  					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
						</display:column>
	  					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
						</display:column>					
	  					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
						</display:column>					
					</s:elseif>
					<s:elseif test="numeroEtapa == 4">
	  					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
						</display:column>
	  					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
						</display:column>					
	  					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
						</display:column>					
	  					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
						</display:column>					
					</s:elseif>
					<s:elseif test="numeroEtapa == 5">
	  					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
						</display:column>
	  					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
						</display:column>					
	  					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
						</display:column>					
	  					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
						</display:column>					
	  					<display:column  title="1er. Trimestre - Etapa V - Volumen (ton.)" class="d">
							<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa5}"/></s:text>
						</display:column>
						<display:column  title="1er. Trimestre - Etapa V - Monto ($)" class="d">
							$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa5}"/></s:text>
						</display:column>					
					</s:elseif>					
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
				<s:if test="numeroEtapa == 1">			
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">2do. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
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
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
					</tr>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<tr>
						<th colspan="4">1er. Trimestre</th>
						<th colspan="4">2do. Trimestre</th>
						<th colspan="4">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 3">
					<tr>
						<th colspan="6">1er. Trimestre</th>
						<th colspan="6">2do. Trimestre</th>
						<th colspan="6">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 4">
					<tr>
						<th colspan="8">1er. Trimestre</th>
						<th colspan="8">2do. Trimestre</th>
						<th colspan="8">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV+totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV+totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 5">
					<tr>
						<th colspan="10">1er. Trimestre</th>
						<th colspan="10">2do. Trimestre</th>
						<th colspan="10">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV+totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV+totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV+totalVolumen2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV+totalImporte2doTrimestreEtapaV}"/></s:text></font></td>
					</tr>				
				</s:elseif>
			</table>	
		</fieldset>
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagosEtapas?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}&numeroEtapa=%{numeroEtapa}"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
			<display:table id="r"  name="lstReporteDetConcentradoPagosEtapasV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosEtapasV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentradoEtapas"  class="displaytag">
				<display:column  property="comprador" title="Comprador"/>
				<display:column  property="cartaAdhesion" title="No. Carta"/>
				<s:if test="numeroEtapa == 1">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>				
				</s:elseif>				
				<s:elseif test="numeroEtapa == 3">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>				
				</s:elseif>				
				<s:elseif test="numeroEtapa == 4">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa4}"/></s:text>
					</display:column>				
				</s:elseif>				
				<s:elseif test="numeroEtapa == 5">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa4}"/></s:text>
					</display:column>				
					<display:column  title="2do. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa5}"/></s:text>
					</display:column>				
				</s:elseif>				
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
				<s:if test="numeroEtapa == 1">
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">2do. Trimestre</th>
						<th colspan="2">3er. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
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
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI}"/></s:text></font></td>						
					</tr>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<tr>
						<th colspan="4">1er. Trimestre</th>
						<th colspan="4">2do. Trimestre</th>
						<th colspan="4">3er. Trimestre</th>
						<th colspan="4">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 3">
					<tr>
						<th colspan="6">1er. Trimestre</th>
						<th colspan="6">2do. Trimestre</th>
						<th colspan="6">3er. Trimestre</th>
						<th colspan="6">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
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
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII+totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII+totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 4">
					<tr>
						<th colspan="8">1er. Trimestre</th>
						<th colspan="8">2do. Trimestre</th>
						<th colspan="8">3er. Trimestre</th>
						<th colspan="8">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII+totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII+totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV+totalVolumen2doTrimestreEtapaIV+totalVolumen3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV+totalImporte2doTrimestreEtapaIV+totalImporte3erTrimestreEtapaIV}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 5">
					<tr>
						<th colspan="10">1er. Trimestre</th>
						<th colspan="10">2do. Trimestre</th>
						<th colspan="10">3er. Trimestre</th>
						<th colspan="10">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII+totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII+totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV+totalVolumen2doTrimestreEtapaIV+totalVolumen3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV+totalImporte2doTrimestreEtapaIV+totalImporte3erTrimestreEtapaIV}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV+totalVolumen2doTrimestreEtapaV+totalVolumen3erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV+totalImporte2doTrimestreEtapaV+totalImporte3erTrimestreEtapaV}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
			</table>	
		</fieldset>
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagosEtapas?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}&numeroEtapa=%{numeroEtapa}"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
			<display:table id="r"  name="lstReporteDetConcentradoPagosEtapasV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosEtapasV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentradoEtapas"  class="displaytag">
				<display:column  property="comprador" title="Comprador"/>
				<display:column  property="cartaAdhesion" title="No. Carta"/>
				<s:if test="numeroEtapa == 1">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
				</s:elseif>
				<s:elseif test="numeroEtapa == 3">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa3}"/></s:text>
					</display:column>
				</s:elseif>
				<s:elseif test="numeroEtapa == 4">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa4}"/></s:text>
					</display:column>
				</s:elseif>
				<s:elseif test="numeroEtapa == 5">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa5}"/></s:text>
					</display:column>
				</s:elseif>
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
				<s:if test="numeroEtapa == 1">
					<tr>
						<th colspan="2">1er. Trimestre</th>
						<th colspan="2">2do. Trimestre</th>
						<th colspan="2">3er. Trimestre</th>
						<th colspan="2">4to. Trimestre</th>
						<th colspan="2">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa I</th>
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
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI+totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI+totalImporte4toTrimestreEtapaI}"/></s:text></font></td>						
					</tr>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<tr>
						<th colspan="4">1er. Trimestre</th>
						<th colspan="4">2do. Trimestre</th>
						<th colspan="4">3er. Trimestre</th>
						<th colspan="4">4to. Trimestre</th>
						<th colspan="4">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI+totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI+totalImporte4toTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII+totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII+totalImporte4toTrimestreEtapaII}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 3">
					<tr>
						<th colspan="6">1er. Trimestre</th>
						<th colspan="6">2do. Trimestre</th>
						<th colspan="6">3er. Trimestre</th>
						<th colspan="6">4to. Trimestre</th>
						<th colspan="6">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI+totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI+totalImporte4toTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII+totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII+totalImporte4toTrimestreEtapaII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII+totalVolumen3erTrimestreEtapaIII+totalVolumen4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII+totalImporte3erTrimestreEtapaIII+totalImporte4toTrimestreEtapaIII}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 4">
					<tr>
						<th colspan="8">1er. Trimestre</th>
						<th colspan="8">2do. Trimestre</th>
						<th colspan="8">3er. Trimestre</th>
						<th colspan="8">4to. Trimestre</th>
						<th colspan="8">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI+totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI+totalImporte4toTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII+totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII+totalImporte4toTrimestreEtapaII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII+totalVolumen3erTrimestreEtapaIII+totalVolumen4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII+totalImporte3erTrimestreEtapaIII+totalImporte4toTrimestreEtapaIII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV+totalVolumen2doTrimestreEtapaIV+totalVolumen3erTrimestreEtapaIV+totalVolumen4toTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV+totalImporte2doTrimestreEtapaIV+totalImporte3erTrimestreEtapaIV+totalImporte4toTrimestreEtapaIV}"/></s:text></font></td>						
					</tr>				
				</s:elseif>				
				<s:elseif test="numeroEtapa == 5">
					<tr>
						<th colspan="10">1er. Trimestre</th>
						<th colspan="10">2do. Trimestre</th>
						<th colspan="10">3er. Trimestre</th>
						<th colspan="10">4to. Trimestre</th>
						<th colspan="10">Gran Total</th>
					</tr>
					<tr>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
						<th colspan="2">Etapa I</th>
						<th colspan="2">Etapa II</th>
						<th colspan="2">Etapa III</th>
						<th colspan="2">Etapa IV</th>
						<th colspan="2">Etapa V</th>
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
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto ($)</th>
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
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
						<th>Volumen (ton.)</th>
						<th>Monto Neto ($)</th>
					</tr>
					<tr>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte2doTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen3erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte3erTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen4toTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte4toTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaI+totalVolumen2doTrimestreEtapaI+totalVolumen3erTrimestreEtapaI+totalVolumen4toTrimestreEtapaI}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaI+totalImporte2doTrimestreEtapaI+totalImporte3erTrimestreEtapaI+totalImporte4toTrimestreEtapaI}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaII+totalVolumen2doTrimestreEtapaII+totalVolumen3erTrimestreEtapaII+totalVolumen4toTrimestreEtapaII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaII+totalImporte2doTrimestreEtapaII+totalImporte3erTrimestreEtapaII+totalImporte4toTrimestreEtapaII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIII+totalVolumen2doTrimestreEtapaIII+totalVolumen3erTrimestreEtapaIII+totalVolumen4toTrimestreEtapaIII}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIII+totalImporte2doTrimestreEtapaIII+totalImporte3erTrimestreEtapaIII+totalImporte4toTrimestreEtapaIII}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaIV+totalVolumen2doTrimestreEtapaIV+totalVolumen3erTrimestreEtapaIV+totalVolumen4toTrimestreEtapaIV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaIV+totalImporte2doTrimestreEtapaIV+totalImporte3erTrimestreEtapaIV+totalImporte4toTrimestreEtapaIV}"/></s:text></font></td>						
						<td align="right"><font class="arial12bold"><s:text name="volumen"><s:param value="%{totalVolumen1erTrimestreEtapaV+totalVolumen2doTrimestreEtapaV+totalVolumen3erTrimestreEtapaV+totalVolumen4toTrimestreEtapaV}"/></s:text></font></td>
						<td align="right"><font class="arial12bold">$<s:text name="importe"><s:param value="%{totalImporte1erTrimestreEtapaV+totalImporte2doTrimestreEtapaV+totalImporte3erTrimestreEtapaV+totalImporte4toTrimestreEtapaV}"/></s:text></font></td>						
					</tr>				
				</s:elseif>
			</table>	
		</fieldset>
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteDetConcentradoPagosEtapas?idPrograma=%{idPrograma}&ejercicio=%{ejercicio}&trimestre=%{trimestre}&numeroEtapa=%{numeroEtapa}"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
			<display:table id="r"  name="lstReporteDetConcentradoPagosEtapasV" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteDetConcentradoPagosEtapasV"  pagesize="20" sort="list" requestURI="/reportes/reporteDetalleConcentradoEtapas"  class="displaytag">
				<display:column  property="comprador" title="Comprador"/>
				<display:column  property="cartaAdhesion" title="No. Carta"/>
				<s:if test="numeroEtapa == 1">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa1}"/></s:text>
					</display:column>
				</s:if>
				<s:elseif test="numeroEtapa == 2">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa2}"/></s:text>
					</display:column>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 3">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa2}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa3}"/></s:text>
					</display:column>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 4">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa2}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa3}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa4}"/></s:text>
					</display:column>				
				</s:elseif>
				<s:elseif test="numeroEtapa == 5">
					<display:column  title="1er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen1erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="1er. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe1erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen2doTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="2do. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe2doTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen3erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="3er. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe3erTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa1}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa I - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa1}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa II - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa2}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa II - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa2}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa III - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa3}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa III - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa3}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa IV - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa4}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa IV - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa4}"/></s:text>
					</display:column>				
					<display:column  title="4to. Trimestre - Etapa V - Volumen (ton.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen4toTrimestreEtapa5}"/></s:text>
					</display:column>
					<display:column  title="4to. Trimestre - Etapa V - Monto ($)" class="d">
						$<s:text name="importe"><s:param value="%{#attr.r.importe4toTrimestreEtapa5}"/></s:text>
					</display:column>				
				</s:elseif>
				<display:column  property="estados" title="Origen del Grano"/>
				<display:column  property="ciclo" title="Ciclo"/>
				<display:column  property="producto" title="Producto"/>
			 </display:table>
		</fieldset>
	</s:elseif>	
</s:if>
<s:else><div class="advertencia">No se encontraron registros para el reporte seleccionado</div></s:else>