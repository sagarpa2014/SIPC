<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="clear"></div>
<s:form action="registroManualCertificadoDeposito" onsubmit="return chkCamposregistraCertificadoDeposito();" method="post">
<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<div>
		<label class="left1"><span class="requerido">*</span>N&uacute;mero de Certificados a Capturar:</label>
		<s:textfield id="numCampos" name="numCampos"  maxlength="2" size="5"  value="%{numCampos}"/>
	</div>
	<div id="agregarCertificadoDeposito">
		<s:if test="registrar==2">
			<s:include value="/WEB-INF/paginas/solicitudPago/documentacionSolPago/certificadoDeposito/agregaCertificadoDeposito.jsp"/>
		</s:if>
	</div>
	<div class="clear"></div>
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2" />
		<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
	</div>
</s:form>

<script>
		$(document).ready(function() {
			
			$("#numCampos").keyup(function(event){	
					var numCampos = $('#numCampos').val();
					var folioCartaAdhesion = $('#folioCartaAdhesion').val();
					if(numCampos == null || numCampos == ""){
						return false;
					}
					var patron =/^\d{1,2}$/;
					if(!numCampos.match(patron)){
			    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 2 dígitos');
			       		abrirDialogo();
			       		$('#numCampos').val(null);
			       		return false;
			    	}  
			
					$.ajax({
						   async: false,
						   type: "POST",
						   url: "agregarCertificadoDeposito",
						   data: "numCampos="+numCampos+
						   "&folioCartaAdhesion="+folioCartaAdhesion,
						   success: function(data){
								$('#agregarCertificadoDeposito').html(data).ready(function () {
									$("#agregarCertificadoDeposito").fadeIn('slow');
									
								});
						   }
						});//fin ajax
				});//termina keyup numCampos	
		});	 
</script>