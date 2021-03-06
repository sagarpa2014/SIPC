<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<BR>
<div class="withborder">
	<table style="width:100%" class="clean">
		<tr>
			<td align="center">
				<img width="312" height="102"  src="<s:url value="/images/logoSagarpa.png" />" />
			</td> 
			<td align="center">
				<font class="arial14bold">
					AGENCIA DE SERVICIOS A LA COMERCIALIZACION<br>
					Y DESARROLLO DE MERCADOS AGROPECUARIOS<br><br>
				</font>
				<font class="arial12bold">
					Mec�nica de Registro, Actualizaci�n<br>
					y Seguimiento de Centros de Acopio
				</font>
			</td>
			<td align="center">
				<img width="212" height="81"  src="<s:url value="/images/logoASERCA.jpg" />" />
			</td>
		</tr>
	</table> 		
	<div class="clear"></div>
	<p align="center">
		<font class="arial12bold">REPORTE DE SEGUIMIENTO DE COSECHA EN CENTROS DE ACOPIO</font><br>
	</p>
	<div class="clear"></div>
	<p align="center">
		<font class="arial12bold">
			CICLO AGR�COLA: <s:property value="ciclo"/><br>
		</font>
	</p>
	<p align="center">
		<font class="arial12bold">
			EXISTENCIA DE GRANO DE LA VISITA ANTERIOR: <s:property value="existenciaAMAnt"/><br>
		</font>
	</p>
	<p align="center">
		<font class="arial12bold">
			PERIODO DE:  <s:property value="%{getText('fecha',{periodoInicial})}"/>     AL:     <s:property value="%{getText('fecha',{periodoFinal})}"/><br>
		</font>
	</p>	
	<div class="clear"></div>
	<center>	
		<table style="width:80%">
			<s:iterator value="lstSeguimientoAcopio" var="resultado">	
				<tr>
					<td colspan="2"><font class="arial12bold">CLAVE CENTRO ACOPIO:</font></td>
					<td align="left"><s:property value="%{claveBodega}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">NOMBRE O RAZ�N SOCIAL DEL CENTRO DE ACOPIO REGISTRADO EN ASERCA:</font></td>
					<td align="left"><s:property value="%{nombreBodega}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">NOMBRE LOCAL O REGIONAL DEL CENTRO DE ACOPIO:</font></td>
					<td align="left"><s:property value="%{nombreLocalBodega}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">NOMBRE O RAZ�N SOCIAL DEL OPERADOR DEL CENTRO DE ACOPIO:</font></td>
					<td align="left"><s:property value="%{nombreOperador}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">CULTIVO:</font></td>
					<td align="left"><s:property value="%{nombreCultivo}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">VARIEDAD:</font></td>
					<td align="left"><s:property value="%{nombreVariedad}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">VOLUMEN MERCADO LIBRE:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.volumenMercadoLibre})}"/> tons.</td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">VOLUMEN AXC:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.volumenAXC})}"/> tons.</td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">ACOPIO TOTAL TONELADAS:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.acopioTotalTon})}"/> tons.</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="2"><font class="arial12bold">NOMBRE O RAZ�N SOCIAL DEL COMPRADOR:</font></td> -->
<%-- 					<td align="left"><s:property value="%{nombreComprador}"/></td> --%>
<!-- 				</tr>				 -->
				<tr>
					<td rowspan="2"><font class="arial12bold">PAGADO</font></td>
					<td><font class="arial12bold">T.M.:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.pagadoTon})}"/></td>
				</tr>
				<tr>
					<td><font class="arial12bold">%:</font></td>
					<td align="left"><s:property value="%{pagadoPorcentaje}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">PRECIO PROMEDIO PAGADO AXC</font></td>
					<td align="left">$ <s:property value="%{getText('importe',{#resultado.precioPromPagAXC})}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">PRECIO PROMEDIO PAGADO LIBRE MERCADO</font></td>
					<td align="left">$ <s:property value="%{getText('importe',{#resultado.precioPromPagLibre})}"/></td>
				</tr>				
				<tr>
					<td rowspan="4"><font class="arial12bold">MOVILIZADO</font></td>
					<td><font class="arial12bold">FURG�N:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.mfurgon})}"/> tons.</td>
				</tr>
				<tr>
					<td><font class="arial12bold">CAMI�N:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.mcamion})}"/> tons.</td>
				</tr>
				<tr>
					<td><font class="arial12bold">MAR�TIMO:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.mmaritimo})}"/> tons.</td>
				</tr>
				<tr>
					<td><font class="arial12bold">AUTOCONSUMO:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.mautoconsumo})}"/> tons.</td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">TOTAL:</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.mtotal})}"/> tons.</td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">EXISTENCIA (ACOPIO-MOVILIZADO):</font></td>
					<td align="left"><s:property value="%{getText('volumen',{#resultado.existenciaAM})}"/> tons.</td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">DESTINO:</font></td>
					<td align="left"><s:property value="%{destino}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">OBSERVACIONES:</font></td>
					<td align="left"><s:property value="%{observaciones}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">AVANCE COSECHA:</font></td>
					<td align="left"><s:property value="%{avanceCosecha}"/></td>
				</tr>
				<tr>
					<td colspan="2"><font class="arial12bold">FECHA:</font></td>
					<td align="left"><s:property value="%{getText('fecha',{#resultado.fechaEnvio})}"/></td>
				</tr>
			</s:iterator>
		</table>	
	</center>				
	<br>
	<br>
	<div class="accion">
		<a href="#" class="boton" title="" onclick="cerrarVistaPreviaOficio()">Cerrar</a>
	</div>
</div>
