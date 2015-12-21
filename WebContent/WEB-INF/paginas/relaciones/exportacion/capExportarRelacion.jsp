<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div class="borderBottom"><h1>EXPORTACI&Oacute;N DE RELACIONES</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div id="dialogo_1"></div>
<fieldset>
	<legend>Exportaci&oacute;n de Relaciones</legend>
	<div>
		<label class="left1"><span class="requerido">*</span>Folio Carta Adhesi&oacute;n:</label>
		<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion"  maxlength="150" size="50"  value="%{}"/>
	</div>	
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Tipo de Relaci&oacute;n:</label>
		<s:select id="idRelacion" name="idRelacion" list="lstRelaciones" listKey="idRelacion" listValue="%{relacion}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaConfRelacionD()" onclik="recuperaConfRelacionD()" />   
	</div>			
</fieldset>
<div class="accion">
	<a href="#" class="boton" title="Agregar " onclick="exportarRelacion(2,'',0,0,0,0,0,0,0,0,0,0);">Exportar Informaci&oacute;n</a>
</div>	

