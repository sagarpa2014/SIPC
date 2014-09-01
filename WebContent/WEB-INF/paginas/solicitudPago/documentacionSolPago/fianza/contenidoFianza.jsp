<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js"/>"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<s:form action="registraCapFianza" method="post" enctype="multipart/form-data" accept-charset="utf-8"  onsubmit="return chkCamposFianza();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="idCriterioPago" name="idCriterioPago" value="%{idCriterioPago}"/>
	<fieldset>
		<s:include value="/WEB-INF/paginas/solicitudPago/documentacionSolPago/documentos/datosCartaAdhesion.jsp"/>
 		<div id="documentos">
 			<s:include value="documentosFianza.jsp"/>
 		</div>
 		<div class="clear"></div>
	</fieldset>
	<s:if test="registrar==0 || registrar == 3">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/solicitudPago/listarPrograma"/>" class="boton" title="">Cancelar</a>
		</div>
	</s:if>
	<div class="izquierda"><a href="<s:url value="/solicitudPago/listarPrograma"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>	
		
</s:form>
<script>
</script>
<s:if test="registrar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			//$("input").attr('disabled','disabled');
			//$("select").attr('disabled','disabled');
			//$("#tipoDocumentacionSP input[type='radio']").removeAttr( "disabled" );
		});	 	
	</script>
</s:if>

