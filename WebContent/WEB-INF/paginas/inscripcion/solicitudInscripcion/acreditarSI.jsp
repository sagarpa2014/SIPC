<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="acreditacionSI==2">
	<label class="left1"><span class="requerido">*</span>Observaciones de la Solicitud de Inscripci&oacute;n:</label>
	<s:textarea name="obsSolInscripcion" id ="obsSolInscripcion" cols="120" rows="3" value="%{obsSolInscripcion}"/>	
</s:if>





