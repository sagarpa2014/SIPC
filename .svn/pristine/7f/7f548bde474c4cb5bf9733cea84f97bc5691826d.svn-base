<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>

<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<s:if test="estatusSI==1">
	<div class="borderBottom"><h1>GENERACI&Oacute;N FOLIO CARTA ADHESI&Oacute;N</h1></div><br>
</s:if>
<s:elseif test="estatusSI==2">
	<div class="borderBottom"><h1>REGISTRO DE SOLICITUD ACREDITACI&Oacute;N NEGATIVA</h1></div><br>
</s:elseif>
<s:else>
	<div class="borderBottom"><h1>REGISTRO DE SOLICITUD ACREDITACI&Oacute;N CON OBSERVACIONES</h1></div><br>
</s:else>
<div class="clear"></div>
<fieldset>
	<legend>Solicitud de Inscripci&oacute;n</legend>
	<s:if test="fueraTiempo == 1"> <!-- Fuera de tiempo -->
		<div class="advertencia">La fecha de la solicitud esta fuera del limite de la fecha de publicación del programa</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Fecha de solicitud de inscripci&oacute;n:</label>
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaDocSI}"/></s:text></font>	
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Fecha limite de inscripci&oacute;n:</label>
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaLimiteSI}"/></s:text></font>	
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Fecha de publicaci&oacute;n del programa:</label>
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaPublicacionDOF}"/></s:text></font>	
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero d&iacute;as limite de solicitud inscripci&oacute;n :</label>
			<font class="arial12bold"><s:property value="%{periodoDOFSI}"/></font>	
		</div>
		<div class="clear"></div>
	</s:if>
	<div class="clear"></div>
	<div>
		<label class="left1">Programa:</label>
		<font class="arial12bold"><s:property value="%{pv.descripcionLarga}"/></font>	
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1">Participante:</label>
		<font class="arial12bold"><s:property value="%{comprador.nombre}"/></font>	
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1">Ciclo:</label>
		<font class="arial12bold"><s:property value="%{nombreCiclo}"/></font>	
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1">Cultivo:</label>
		<font class="arial12bold"><s:property value="%{nombreCultivo}"/></font>	
	</div>
	<div class="clear"></div>
	<s:if test="idCriterioPago==1 || idCriterioPago==3">
		<div>
			<label class="left1">Volumen Solicitado:</label>
			<font class="arial12bold"><s:text name="volumen"><s:param value="%{volumenInscripcion}"/></s:text></font>	
		</div>
	</s:if>
	<s:elseif test="idCriterioPago==2">
		<div>
			<label class="left1">Importe Solicitado:</label>
			<font class="arial12bold"><s:text name="volumen"><s:param value="%{importeInscripcion}"/></s:text></font>	
		</div>
	</s:elseif>	
	<div class="clear"></div>
	<s:if test="folioCartaAdhesion!='' && folioCartaAdhesion != null">
		<div>
			<label class="left1">Folio Carta Adhesi&oacute;n:</label>
			<font class="arial14boldVerde"><s:property value="%{folioCartaAdhesion}"/></font>	
		</div>
	</s:if>
</fieldset>
<div class="accion">
	<a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>" class="boton" title="" >Regresar</a>
</div>




