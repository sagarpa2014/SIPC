<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/avisosDof.js"/>"></script>	
<div id="dialog-form"></div>
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
			    		 $('#capturaCargaArchivoAvancePagos').html(data).ready(function () {		    		
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
<div id="dialogo_1"></div>
<div id="capturaCargaArchivoAvancePagos">
<s:form action="registraArchivoAvancePagos" onsubmit="return chkArchivoAvancePagos();" method="post" enctype="multipart/form-data">
<s:hidden id="claveAviso" name="claveAviso" value="%{claveAviso}"/>
	<div class="borderBottom"><h1>REGISTRO DE AVISOS</h1></div><br>
	<fieldset>	
		<legend>DATOS DEL AVISO</legend>
		<div>
			<label class="left1"><span class="requerido">*</span>Clave del Aviso:</label>	
			<font class="arial12bold"><s:property value="%{adv.claveAviso}"/></font>			
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Leyenda:</label>
			<font class="arial12bold"><s:property value="%{adv.leyenda}"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Fecha de Publicaci&oacute;n:</label> 		
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{adv.fechaPublicacion}"/></s:text></font>		
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Apoyo:</label>
			<font class="arial12bold"><s:property value="%{adv.apoyo}"/></font>		
		</div>	
	</fieldset>
	<fieldset>	
		<legend>ARCHIVO AVANCE DE PAGOS</legend>
		<div class="derecha">
			<img id="imgBodegaOff" src="<s:url value="/images/eliminarOn.png"/>" onclick="if (confirm('�Esta seguro de eliminar el �ltimo archivo?')){eliminarArchivo();}else{return false;}"/>
		</div>
		<div class="derecha">
			<font class="fuente1">Eliminar &uacute;ltimo archivo cargado</font>
		</div>
		<div class="clear"></div>	
		<div id="eliminarArchivoAvance" class="derecha"></div>
		<div class="clear"></div>	
		<div>
			<label class="left1">Archivo:</label>
			<s:file name="doc" id="doc"/>
		</div>	
		<div class="clear"></div>
		<s:if test="%{nombreArchivoLogCargaXls!=null}">
			<fieldset>
				<legend>Descargar Log de procesos</legend>	
				<div class="exporta_csv">			
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaLog+nombreArchivoLogCargaXls}"/>" title="Descargar Archivo" ></a>
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
	<div class="izquierda">
		<a href="<s:url value="/avisosDof/ltsAvisosDof"/>" class="" title="" >&lt;&lt; Regresar</a>
	</div>
</s:form>
</div>