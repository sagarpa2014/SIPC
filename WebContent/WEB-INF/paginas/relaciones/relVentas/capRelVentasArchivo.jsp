<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relVentas.js" />"></script>
<div class="clear"></div>
<div id="error"></div>
<script>
$(document).ready(function(){  
	     $('form').ajaxForm({
	    	 beforeSubmit: function() {			   
			    	$("#espera").fadeIn('slow', function() {
			   		 	$("#img-espera").fadeIn('slow');
			    	});
			     },
			     success: function(data) {
			    	var $response=$(data);
			    	 var errorSistema = $response.filter('#errorSistema').text();
			    	 if(errorSistema != 0){//si hubo error
			    		 $('#error').html(data).ready(function () {		    		
								$("#img-espera").fadeOut("slow", function() {
							   		  $("#espera").fadeOut("slow");
							   	});   		
					    	});   
			    	 }else{
			    		 $('#capturaCargaArchivoRelVentas').html(data).ready(function () {		    		
								$("#img-espera").fadeOut("slow", function() {
							   		  $("#espera").fadeOut("slow");
							   	});  
					    	}); 
			    	 }
			    	$("#errorSistema").fadeOut("slow");
			     }
		     });
	}); 
</script>
<div id="capturaCargaArchivoRelVentas">
	<s:form action="registraArchivoRelVentas" onsubmit="return chkArchivoRelVentas();" method="post" enctype="multipart/form-data">
		<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
		<s:hidden id="tipoAccion" name="tipoAccion" value="%{tipoAccion}"/>
		<fieldset>
			<legend>Carga de Archivo </legend>
			<div>
				<label class="left1"><span class="norequerido">*</span>Cargar Relaci&oacute;n de Ventas a la Base de Datos:</label>
				<s:file name="doc" id="doc"/>
			</div>
			<div class="clear"></div>
			<s:if test="%{nombreArchivoLogCargaXls!=null}">
				<fieldset>
					<legend>Descargar Log de procesos</legend>	
					<div class="exporta_csv">			
						<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCartaAdhesion+nombreArchivoLogCargaXls}"/>" title="Descargar Archivo" ></a>
					</div>
					<div id="logCargaArchivo">
						<s:include value="listarLog.jsp"/>
					</div>
				</fieldset>
			</s:if>
			<div class="accion">
				<s:submit  value="Guardar" cssClass="boton2" />
				<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
			</div>
		</fieldset>
	</s:form>
</div>