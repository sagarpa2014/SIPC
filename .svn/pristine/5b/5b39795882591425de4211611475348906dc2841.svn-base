<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="lstPagosV.size() > 0">
	<div id="pagos">
		<div>
			<label class="left1">Ciclo:</label>
			<s:textfield disabled="true" maxlength="13" size="30"  value="%{ciclo}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
			<s:textfield id="claveOficio" name="claveOficio"  maxlength="10" size="8"  value="%{claveOficio}"/>
			<s:textfield id="noOficio" name="noOficio"  maxlength="10" size="8"  value="%{}"/>
			<s:textfield id="anioOficio" name="anioOficio"  maxlength="10" size="8"  value="%{anioOficio}"/>
		</div>
		<div class="clear"></div>
		<table style="width:100%">
			<tr>
				<th><input id="o1" name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>
				<th>Comprador</th>
				<th>No. Carta</th>
				<th>Clabe</th>
				<s:if test="criterioPago==2 || criterioPago==3">
					<th>Etapa</th>
				</s:if>
				<s:if test="criterioPago==1 || criterioPago==3">
					<th>Volumen</th>
				</s:if>
				<th>Importe</th>
				<th>Detalles</th>
			</tr>
			<s:iterator value="lstPagosV" var="resultado">
				<tr>
					<td align="center"><input id="" name="" value="<s:property value="idPago"/>" type="checkbox"  class="ck"/></td>
					<td><s:property value="nombreComprador" /></td>
					<td><s:property value="noCarta" /></td>
					<td><s:property value="clabe" /></td>
					<s:if test="criterioPago==2 || criterioPago==3">
						<td align="center"><s:property value="%{etapa}"/></td>
					</s:if>
					<s:if test="criterioPago==1 || criterioPago==3">
						<td align="right"><s:text name="volumen"><s:param value="%{volumen}"/></s:text></td>
					</s:if>
					<td align="right"><s:text name="importe"><s:param value="%{importe}"/></s:text></td>
					<td><a href='<s:url value="/pagos/detallesPago?idPago=%{idPago}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a></td>
				</tr>	
			</s:iterator>	
		</table>	
		<div class="accion">
			<a href="#" class="boton" title="" onclick="vistaPreviaOficio()">Vista Previa</a>
			<a href='#' onclick="generarOficioEnvioCGCDGAF()" class="boton">Generar Oficio</a>
			<a href="<s:url value="/pagos/archivoTesofeEnvio"/>" class="boton" title="Cancelar" >Cancelar</a>
		</div>	
	</div>	
	<div id="vistaPrevia" class="vistaPrevia"></div>
	<div id="resultadoGeneracionOficio"></div>	
</s:if>
<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>