<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>

<div class="borderBottom">
	<h1>
		ASIGNACI&Oacute;N DE AUDITOR <s:if test="tipoDocumentacion==1">DOCUMENTACI&Oacute;N</s:if><s:else>FIANZA</s:else>
	</h1>
</div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<s:if test="errorNumeroAuditor!=null && errorNumeroAuditor !=''">
	<div class="errorNumeroAuditor"><s:property value="%{errorNumeroAuditor}"/></div>
</s:if>
<div id="dialogo_1"></div>
<s:form action="registrarAuditorSolPago" method="post" onsubmit="return chkCamposregistraAuditorSolPago();">
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="tipoDocumentacion" name="tipoDocumentacion" value="%{tipoDocumentacion}"/>
	<s:hidden id="numCamposAVAnterior" name="numCamposAVAnterior" value="%{numCamposAVAnterior}"/>
	<fieldset>
		<legend>Auditor</legend>
		<div>
			<label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label>
			<font class="arial14boldVerde"><s:property value="%{folioCartaAdhesion}"/></font>
		</div>
		<div class="clear"></div>	
		<div>
			<label class="left1"><span class="norequerido">*</span>Participante:</label>
			<font class="arial12bold"><s:property value="%{comprador.nombre}"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Número Auditores que Dictaminaron:</label>
			<s:textfield id="numCamposAV" name="numCamposAV"  maxlength="3" size="5"  value="%{numCamposAV}" onchange="validarRfc();"/>
			
		</div>
		<div id="agregarAuditorVolumen">
			<s:include value="agregaAuditorVolumen.jsp"></s:include>
		</div>
		<script>
			$(document).ready(function() {
				$("#numCamposAV").keyup(function(event){
						//manda a llamar
						var numCamposAV = $('#numCamposAV').val();
						var folioCartaAdhesion = $('#folioCartaAdhesion').val();
						var tipoDocumentacion = $('#tipoDocumentacion').val();
						if(numCamposAV == null || numCamposAV == ""){
							return false;
						}
						
						var patron =/^\d{1,3}$/;
						if(!numCamposAV.match(patron)){
				    		$('#dialogo_2').html('El valor del campo es inválido, se aceptan hasta 3 dígitos');
				       		abrirDialogo2();
				       		$('#numCamposAV').val(null);
				       		return false;
				    	}  
						var numCamposAVAnterior ="";
						if(tipoDocumentacion==1 || tipoDocumentacion==2){
							numCamposAVAnterior = $('#numCamposAVAnterior').val();
						}
						
						$.ajax({
							   async: false,
							   type: "POST",
							   url: "agregarAuditorVolumen",
							   data: "numCamposAV="+numCamposAV+
						   		"&numCamposAVAnterior="+numCamposAVAnterior+
						   		"&folioCartaAdhesion="+folioCartaAdhesion+ 
						   		"&tipoDocumentacion="+tipoDocumentacion, 
							   success: function(data){
									$('#agregarAuditorVolumen').html(data).ready(function () {
										$("#agregarAuditorVolumen").fadeIn('slow');
										
									});
							   }
							});//fin ajax
					});//termina keyup numCampos	
			});	 
		</script>
	</fieldset>
	<div class="clear"></div>
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
		</div>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
		
</s:form>