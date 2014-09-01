<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js"/>"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<s:form action="registraCapDocumentacion" name="forma" method="post" enctype="multipart/form-data" accept-charset="utf-8"  onsubmit="return chkCamposDocumentacion();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="idCriterioPago" name="idCriterioPago" value="%{idCriterioPago}"/>
	<s:hidden id="estatusCA" name="estatusCA" value="%{estatusCA}"/>
	<fieldset>
		<s:include value="datosCartaAdhesion.jsp"/>
		<div class="clear"></div>
		<s:if test="estatusCA==6 || estatusCA==8 || estatusCA == 5">
 			<s:if test="tCartaAdehsionHastaSP == true">
 				<div class="advertencia">La fecha presentaci&oacute;n de la solicitud de pago esta fuera de tiempo</div>
 			</s:if>
 		</s:if>
 		<div class="clear"></div>
		
		<s:if test="estatusCA==5">
			<s:if test="deshabilitaAccion != true">
				<div id="sus">
					<label class="left1"><span class="norequerido">*</span>Sustituir Archivos:</label>
					<s:checkbox id="sustituirArchivo"  name="sustituirArchivo" onclick ="sustituirArchivo1();" fieldValue="true" value="%{sustituirArchivo}"/>
				</div>
			</s:if>
		</s:if>
		<div class="clear"></div>
		<s:if test="estatusCA==4">
			<s:if test="deshabilitaAlcanceDocumentacion!=true">
				<div id="alc">
					<label class="left1"><span class="norequerido">*</span>Alcance al Oficio de Observaciones:</label>
					<s:checkbox id="alcanceDocumentacion"  name="alcanceDocumentacion" onclick ="alcance();" fieldValue="true" value="%{}"/>
				</div>				
			</s:if>
 		</s:if>
 		<div class="clear"></div>
 		<div id="documentos">
 			<s:include value="documentos.jsp"/>
 		</div>
		<div class="izquierda"><a href="<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{idPrograma}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
		
</s:form>
<script>
	function alcance(){
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		var registrar = "";
		var alcanceDocumentacion = $('input:checkbox[name=alcanceDocumentacion]:checked').val();
		if(alcanceDocumentacion){
			registrar = 4;
			$("#respuestaOficio").fadeOut('slow');
			$("#registrar").val(4);	
			$("#sus").fadeOut('slow');
			
		}else{
			registrar = 3;
			$("#respuestaOficio").fadeIn('slow');
			$("#registrar").val(3);
			$("#habilitarOficioRespuesta").fadeOut('slow');
			$("#sus").fadeIn('slow');
			
		}
		$("#documentos").fadeOut('slow', function() {
		  	$.ajax({
			   async: false,
			   type: "POST",
			   url: "alcanceDocumentacion",
			   data: "folioCartaAdhesion="+folioCartaAdhesion
			   		+"&alcanceDocumentacion="+alcanceDocumentacion
		   			+"&registrar="+registrar,
			   success: function(data){
					$('#documentos').html(data).ready(function () {
						$("#documentos").fadeIn('slow');
						$("#habilitarOficioRespuesta").fadeIn('slow');
						
					});
			   }
			});
		});
	}
	
	function obsNuevas(){
		console.log("hola");
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		var registrar = "";
		var nuevasObservaciones = $('input:checkbox[name=nuevasObservaciones]:checked').val();
		if(nuevasObservaciones){
			$("#respuestaOficio").fadeOut('slow');
			$("#registrar").val(5);	
		}else{
			registrar = 2;
			$("#respuestaOficio").fadeIn('slow');
			$("#registrar").val(3);
			$("#habilitarOficioRespuesta").fadeOut('slow');
			
		}
		
		$("#documentos").fadeOut('slow', function() {
		  	$.ajax({
			   async: false,
			   type: "POST",
			   url: "activarNuevasObservaciones",
			   data: "folioCartaAdhesion="+folioCartaAdhesion
			   		+"&nuevasObservaciones="+nuevasObservaciones
		   			+"&registrar="+registrar,
			   success: function(data){
					$('#documentos').html(data).ready(function () {
						$("#documentos").fadeIn('slow');
						$("#habilitarOficioRespuesta").fadeIn('slow');
						
					});
			   }
			});
		});
	}

	function docParcial1(){
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		var registrar = "";
		var docParcial = $('input:checkbox[name=docParcial]:checked').val();
		
		if(docParcial){
			registrar = 6;
			//$("#respuestaOficio").fadeOut('slow');
			$("#registrar").val(6);	
		}else{
			docParcial = false;
			registrar = 0;
			//$("#respuestaOficio").fadeIn('slow');
			$("#registrar").val(0);
			//$("#habilitarOficioRespuesta").fadeOut('slow');
		}
		$("#documentos").fadeOut('slow', function() {
		  	$.ajax({
			   async: false,
			   type: "POST",
			   url: "docParcial",
			   data: "folioCartaAdhesion="+folioCartaAdhesion
		   		+"&docParcial="+docParcial
	   			+"&registrar="+registrar,
			   success: function(data){
					$('#documentos').html(data).ready(function () {
						$("#documentos").fadeIn('slow');
						//$("#habilitarOficioRespuesta").fadeIn('slow');
						
					});
			   }
			});
		});
	}
	
	function sustituirArchivo1(){
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		var registrar = "";
		var sustituirArchivo = $('input:checkbox[name=sustituirArchivo]:checked').val();
		if(sustituirArchivo){
			registrar = 7;
			$("#registrar").val(7);	
			if ($('#alc').length){
				$("#alc").fadeOut('slow');
				
			}
		}else{
			$("#alc").fadeIn('slow');
		}
		$("#documentos").fadeOut('slow', function() {
		  	$.ajax({
			   async: false,
			   type: "POST",
			   url: "sustituirArchivo",
			   data: "folioCartaAdhesion="+folioCartaAdhesion
			   		+"&sustituirArchivo="+sustituirArchivo
		   			+"&registrar="+registrar,
			   success: function(data){
					$('#documentos').html(data).ready(function () {
						$("#documentos").fadeIn('slow');
					});
			   }
			});
		});
	}
</script>
<s:if test="registrar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			//$("input").attr('disabled','disabled');
			//$("select").attr('disabled','disabled');
			//$("#tipoDocumentacionSP input[type='radio']").removeAttr( "disabled" );
			$(".ck").attr('disabled','disabled');
		});	 	
	</script>
</s:if>

