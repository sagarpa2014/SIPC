<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>SOLICITUD DE PAGO</h1></div><br>
<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
<div class="inline">
	<label class="left1">Seleccione una opción:</label>
	<s:if test="documentacion==1"> <!-- Solo documentacion -->
		<s:radio label="" onclick="selectDocumentacionSP();"  name="tipoDocumentacion" list="#{1:'DOCUMENTACI&Oacute;N'}" value="%{tipoDocumentacion}" />	
	</s:if>
	<s:else><!-- Documentacion y fianza-->
		<s:radio label="" onclick="selectDocumentacionSP();"  name="tipoDocumentacion" list="#{1:'DOCUMENTACI&Oacute;N', 2:'FIANZA'}" value="%{tipoDocumentacion}"/>
	</s:else>
</div>
<div class="clear"></div>
<div id="contenidoDocumentacion"><s:include value="documentacion.jsp"/></div>
