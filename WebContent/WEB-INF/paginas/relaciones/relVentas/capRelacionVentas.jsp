<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relVentas.js" />"></script>	
	<div class="borderBottom"><h1>RELACI&Oacute;N DE VENTAS</h1></div><br>
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="tipoAccion" name="tipoAccion" value="%{tipoAccion}"/>
	<fieldset>
			<legend>Datos de la Carta de Adhesi&oacute;n</legend>
			<table class ="clean">
				<tr>
					<td><label class="left1">Folio Carta Adhesi&oacute;n:</label><td>
					<td>
						<font class="arial14boldVerde">
							<s:property value="%{folioCartaAdhesion}"/>
						</font>
					<td>
				</tr>
				<tr>
					<td><label class="left1">Programa:</label><td>
					<td>
						<font class="arial12bold"><s:property value="%{nombrePrograma}"/></font>
					<td>
				</tr>
				<tr>
					<td><label class="left1">Participante:</label><td>
					<td>
						<font class="arial12bold"><s:property value="%{nombreComprador}"/></font>
					<td>
				</tr>
			</table>
			<div class="inline">
			<label class="left1"><span class="norequerido">*</span>Tipo de Carga:</label>
			<s:radio label="" onclick="seleccionaTipoCargaRelVentas();"  name="tipoCargaRelVentas" list="#{'1':'Carga Individual','2':'Carga por Archivo'}" value="%{tipoCargaRelVentas}"/>
			<div class="clear"></div>			
		</div>			
	</fieldset>	
	<div id="tipoCargaRelVentas">
		<s:if test="%{tipoCargaRelVentas == 1}">
 			<s:include value="capRelVentasManual.jsp"/> 
		</s:if>
		<s:else>
			<s:include value="capRelVentasArchivo.jsp"/>
		</s:else>
	</div>	
	<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
	


