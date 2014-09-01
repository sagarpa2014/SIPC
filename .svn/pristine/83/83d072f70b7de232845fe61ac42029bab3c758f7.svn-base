<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="clear"></div>
<s:form action="registroArchivoConstanciasAlmacenamiento" onsubmit="return chkArchivoConstanciasAlmacenamiento();" method="post" enctype="multipart/form-data">
<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<div>
		<label class="left1"><span class="norequerido">*</span>Cargar Constancias a la Base de Datos:</label>
		<s:file name="doc" id="doc"/>
	</div>
	<div class="clear"></div>
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2" />
		<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
	</div>
</s:form>