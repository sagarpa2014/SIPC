<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<script type="text/javascript" src="<s:url value="/js/capturaPagos.js" />"></script>

<s:form action="/pagos/actualizarPago" onsubmit="return chkCamposActualizaPagos();">
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div class="borderBottom"><h1>EDICI&Oacute;N DE PAGOS</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div id="dialogo_1"></div>
<s:hidden id="editar" name="editar" value="%{1}"/>
<s:hidden id="idPago" name="idPago" value="%{pagosV.idPago}"/>
<fieldset class="clear">
	<legend>Datos del pago</legend>
	<div>
		<label class="left1"><span class="norequerido">*</span>Programa:</label>	
		<s:hidden id="idPrograma" name="idPrograma" value="%{pagosV.idPrograma}"/>
		<font class="arial12bold"><s:property value="%{pagosV.nombrePgrCorto}"/></font>	
	</div>
	<div>
		<label class="left1"><span class="norequerido">*</span>Nombre Comprador:</label>
		<font class="arial12bold"><s:property value="%{pagosV.nombreComprador}"/></font>
	</div>
	<div>
		<label class="left1"><span class="norequerido">*</span>No. Carta:</label>
		<s:textfield maxlength="100" disabled="true" size="30"  value="%{pagosV.noCarta}"/>	
	</div>
	<s:if test="lstCuentaBancarias.size() > 0">
		<s:include value="/WEB-INF/paginas/pagos/capturaPagos/cuentasBancarias.jsp" />
	</s:if>
	<div>
		<label class="left1">RFC:</label>
		<s:textfield  disabled="true" maxlength="100" size="30"  value="%{pagosV.rfc}"/>	
	</div>
	<s:if test="comprador.curp!=null && comprador.curp!=''">
		<div>
			<label class="left1">CURP:</label>	
			<s:textfield  disabled="true" maxlength="100" size="30"  value="%{pagosV.curp}"/>
		</div>
	</s:if>
	<div>
		<label class="left1">Estados a apoyar actual:</label>
		<table >
			<tr>
				<th>Estado </th>
				<th>Volumen</th>
				<th>Importe</th>
			</tr>
			<s:iterator value="lstPagosDetalleV" var="resultado"  status="itStatus">
				<tr>
					<td class="td1"><s:property value="%{estado}"/></td>
					<td class="td1" align="right"><s:text name="volumen"><s:param value="%{volumen}"/></s:text></td>
					<td class="td1" align="right"><s:text name="importe"><s:param value="%{importe}"/></s:text></td>
				</tr>
			</s:iterator>
		</table>
		</div>
	<div>
		<label class="left1"><span class="requerido">*</span>Estados a apoyar:</label>
		<!-- <s:textfield id="numCampos" name="numCampos"  maxlength="100" size="30"  value=""/>-->
		<s:select id="numCampos" name="numCampos"  headerKey="-1"
				headerValue="Seleccione una opción"
				onchange="agregaEdoPorVolumen(1)"
				list="#{'1':'1', '2':'2', '3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10',
				'11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21',
				'22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31','32':'32',
				'33':'33'}" 
				value="%{numCampos}"/>
	</div>
	<div id="agregaEdoPorVolumen">
		<s:include value="/WEB-INF/paginas/pagos/capturaPagos/agregaEdoPorVolumen.jsp" />
	</div>
</fieldset>
<br>
<div class="accion">
	<s:submit  value="Actualizar Pago" cssClass="boton2" />
	<a href="<s:url value="/pagos/busquedaPagos"/>" class="boton" title="" >Cancelar</a>
</div>
</s:form>
