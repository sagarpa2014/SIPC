<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/reportes.js" />"></script>

<s:form onsubmit="">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>GENERACI&Oacute;N DE ACUSE</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset class="clear">
		<legend>Datos del Oficio</legend>
		<div>
			<label class="left1">N&uacute;mero de Oficio:</label>
			<font class="arial12bold"><s:property value="%{lstOfCompradorPgr.get(0).noOficio}"/></font>
		</div>
		<div>
			<label class="left1">Programa:</label>
			<font class="arial12bold"><s:property value="%{lstOfCompradorPgr.get(0).programa}"/></font>
		</div>
		<div class="clear"></div>
		<s:if test="lstOfCompradorPgr.size() > 0">
		<s:hidden name="numeroRechazos" id="numeroRechazos" value="%{lstOfCompradorPgr.size()}"/>
			<table style="width:100%">
				<tr>
					<th><input name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>
					<th>Comprador </th>
					<s:if test="lstOfCompradorPgr.get(0).criterioPago==2 || lstOfCompradorPgr.get(0).criterioPago==3">
						<th>Etapa</th>
					</s:if>
					<s:if test="lstOfCompradorPgr.get(0).criterioPago==1 || lstOfCompradorPgr.get(0).criterioPago==3">
						<th>Volumen</th>
					</s:if>
					<th>Importe</th>
					
				</tr>
				<s:iterator value="lstOfCompradorPgr" var="resultado"  status="itStatus">
					<tr>
						<td align="center"><input id="p<s:property value="%{#itStatus.count}"/>" name="idPagos" value="<s:property value="idPago"/>" type="checkbox"  class="ck"/></td>
						<td class="td1"><s:property value="%{comprador}"/></td>						
						<s:if test="criterioPago==2 || criterioPago==3">
							<td class="td1" align="right"><s:property value="etapa"/></td>
						</s:if>
						<s:if test="criterioPago==1 || criterioPago==3">
							<td class="td1" align="right"><s:text name="volumen"><s:param value="%{volumen}"/></s:text></td>
						</s:if>
						<td class="td1" align="right"><s:text name="importe"><s:param value="%{importe}"/></s:text></td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
		<div id="resultado"></div>
		<div class="accion">
			<a href='#' onclick="generarAcusesAplicados()" class="boton">Generar Acuse</a>
		</div>
	</fieldset>		
</s:form>

