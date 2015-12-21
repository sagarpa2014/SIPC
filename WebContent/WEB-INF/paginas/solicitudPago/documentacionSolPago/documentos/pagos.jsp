<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:if test="lstPagosV.size() > 0">
	<div class="borderBottom"style="text-align:center"><h1>Pagos Tramitados</h1></div><br>	
	<display:table id="r" name="lstPagosV"  list="lstPagosV"  pagesize="50" sort="list" class="displaytag">
		<s:if test='#session.idPerfil!=5'>
			<display:column title="Fecha de Solicitud de Pago"  headerClass="sortable" >
				<s:text name="fecha"><s:param value="%{#attr.r.fechaAtentaNota}"/></s:text>
			</display:column>
		</s:if>
		<display:column title="Fecha de Pago"  headerClass="sortable" >
			<s:if test="%{#attr.r.fechaPago!=null}">
				<s:text name="fecha"><s:param value="%{#attr.r.fechaPagoDate}"/></s:text>
			</s:if>			
		</display:column>
		<display:column  property="nombrePgrCorto" title="Programa"/>
		<display:column  property="noCarta" title="No. Carta"/>
		<display:column  property="nombreComprador" title="Comprador"/>
		<display:column  property="estatusPago" title="Estatus"/>
		<display:column title="Etapa" class="c" >
			<s:if test="%{#attr.r.etapa!=null}" >
				<s:property value="%{#attr.r.etapa}"/>
			</s:if>
			<s:else></s:else>
		</display:column>
		<display:column title="Volumen" class="d">
			<s:if test="%{#attr.r.volumen!=null}" >
				<s:text name="volumen"><s:param value="%{#attr.r.volumen}"/></s:text>
			</s:if>
			<s:else></s:else>
		</display:column>
		<display:column title="Importe" class="d">
			<s:text name="importe"><s:param value="%{#attr.r.importe}"/></s:text>
		</display:column>		
		<s:if test='#session.idPerfil!=5'>							
			<display:column title="Ver Detalle"  headerClass="sortable">
				<a href='<s:url value="/pagos/detallesPago?idPago=%{#attr.r.idPago}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=800,height=400,scrollbars=yes'); return false;"></a>
			</display:column>
		</s:if>
		<display:footer>
			      <tr>
			      	<s:if test='#session.idPerfil!=5'>
			      		<td colspan="7" class="d"><font class="arial12bold">Totales</font></td>
			      	</s:if>
			      	<s:else>
			      		<td colspan="6" class="d"><font class="arial12bold">Totales</font></td>
			      	</s:else>
			        
			        <td><s:text name="volumen"><s:param value="%{totalVolEnTramPago}"/></s:text></td>
			        <td><s:text name="importe"><s:param value="%{totalImpEnTramPago}"/></s:text></td>
			        <td>&nbsp;</td>
			      </tr>
		</display:footer>
	</display:table>		
</s:if>