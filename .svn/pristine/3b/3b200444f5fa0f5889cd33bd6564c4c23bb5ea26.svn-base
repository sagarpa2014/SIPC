<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>RELACI&Oacute;N DE CERTIFICADOS DE DEP&Oacute;SITO </h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<s:if test="msjError!=null && msjError !=''">
	<div class="msjError"><s:property value="%{msjError}"/></div>
</s:if>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>

	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<fieldset>
		<legend>Certificados de Dep&oacute;sito</legend>
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
		<div class="inline">
			<s:if test="registrar == 0">
				<label class="left1"><span class="norequerido">*</span>Tipo de Carga:</label>
				<s:radio label="" onclick="seleccionaTipoCargaCer()"  name="tipoCargaCer" list="#{'1':'Carga por Archivo','2':'Carga Individual'}" value="%{tipoCargaCer}"/>
				<div class="clear"></div>
				<div id="tipoCargaCer">
					<s:include value="capMasivaCertificadoDeposito.jsp"/>
				</div>
				<div class="clear"></div>
			</s:if>
		</div>
		<div class="clear"></div>
		
		<div>
		<s:else>
			<div class="derecha"><a href="<s:url value="/solicitudPago/capCertificadoDeposito?folioCartaAdhesion=%{folioCartaAdhesion}&registrar=%{0}&agregarCer=%{1}"/>" onclick="" title="" >Agregar Certificados</a></div>
		</s:else>
		</div>
		<div class="clear"></div>
			<s:if test="registrar==2">
				<div>		
					<label class="left1"><span class="requerido">*</span>N&uacute;mero de Certificados Capturados:</label>
					<s:textfield id="numCampos" name="numCampos"  maxlength="2" size="5"  value="%{numCampos}"/>
				</div>
				<div id="agregarCertificadoDeposito">
					<s:include value="agregaCertificadoDeposito.jsp"/>
				</div>
			</s:if>
		<div class="clear"></div>
						
	</fieldset>
		

	
	<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
	<s:if test="registrar == 2"> <!-- Consulta -->
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');
			});	 	
		</script>
	</s:if>

<script>
		$(document).ready(function() {
			
			$("#numCampos").keyup(function(event){	
					var numCampos = $('#numCampos').val();
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
						   data: "numCampos="+numCampos, 
						   success: function(data){
								$('#agregarCertificadoDeposito').html(data).ready(function () {
									$("#agregarCertificadoDeposito").fadeIn('slow');
									
								});
						   }
						});//fin ajax
				});//termina keyup numCampos	
		});	 
	</script>

