<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
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
			    		 $('#capturaCargaArchivoRelCompras').html(data).ready(function () {		    		
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
<s:if test="%{lstPersonalizacionRelacionesProgramaV.size()>0}">
	<s:if test="%{existeRelacionYaValidada == 'true'}">
		<div class="msjError1">Ya se encuentra cargada y validada la relaci&oacute;n de compras para la carta de adhesi&oacute;n seleccionada</div>
	</s:if>
	<s:else>
		<div id="capturaCargaArchivoRelCompras">
			<s:include value="capturaCargaArchivoRelCompras.jsp"/>
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="msjError1">No se ha configurado el layout de la relación de compras</div>
</s:else>

