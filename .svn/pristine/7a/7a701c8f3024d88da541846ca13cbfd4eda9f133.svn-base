<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom" style="text-align:center"><h1>Datos de Carta de Adhesi&oacute;n</h1></div><br>

<table class ="clean">
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label><td>
		<td colspan="5">
			<font class="arial14boldVerde">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCompleta}"/>" title="Archivo"><s:property value="%{folioCartaAdhesion}"/></a>
			</font>
		<td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Programa:</label><td>
		<td colspan="5">
			<font class="arial12bold"><s:property value="%{nombrePrograma}"/></font>
		<td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Participante:</label><td>
		<td colspan="5">
			<font class="arial12bold"><s:property value="%{comprador}"/></font>
		<td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Estado del Participante:</label><td>
		<td colspan="5">
			<s:if test="participante.estatus==1">
				<font class="arial12bold">HABILITADO</font>
			</s:if>
			<s:else><font class="arial12bold">INHABILITADO</font></s:else>	
		<td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Representate Legal:</label><td>
		<td colspan="5">
			<s:iterator value="lstRepresentanteV" var="resultado"  status="itStatus">
				<font class="arial12bold"><s:property value="%{nombreRepresentante}"/></font>
			</s:iterator>	
		<td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Cultivo:</label><td>
		<td colspan="5">
			<font class="arial12bold"><s:property value="%{cultivo}"/></font>	
		<td>
	</tr>
	<s:if test="idCriterioPago==1 || idCriterioPago==3"> <!-- Volumen o Volumen/Etapa -->
		<tr>
			<td><label class="left1"><span class="norequerido">*</span>Volumen Autorizado en SI:</label><td>
			<td>
				<font class="arial12bold"><s:text name="volumen"><s:param value="%{volumenAutorizado}"/></s:text></font>	
			<td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Volumen a Apoyar Original:</label>
			</td>
			<td>
				<font class="arial12bold"><s:text name="volumen"><s:param value="%{volumenOriginal}"/></s:text></font>
			</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Volumen a Apoyar Adendum:</label>
			</td>
			<td>
				<font class="arial12bold"><s:text name="volumen"><s:param value="%{volumenComplemento}"/></s:text></font>
			</td>			
		</tr>
	</s:if>
	<s:else> <!-- Etapa -->
		<tr>
			<td><label class="left1"><span class="norequerido">*</span>Importe Autorizado:</label></td>
			<td colspan="5"><font class="arial12bold"><s:text name="importe"><s:param value="%{importeAutorizado}"/></s:text></font></td>
		</tr>
	</s:else>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Carta Adhesi&oacute;n:</label></td>
		<td colspan="5"><font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaFirmaCA}"/></s:text></font></td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Recepci&oacute;n de la Carta Adhesi&oacute;n:</label></td>
		<td colspan="5"><font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaRecepcionCA}"/></s:text></font></td>
	</tr>
	<tr>
		<td><label class="left1"><span class="norequerido">*</span>Estatus:</label></td>
		<td colspan="5"><font class="arial12bold"><s:property value="%{estatusCartaAdhesion}"/></font></td>
	</tr>
</table>
