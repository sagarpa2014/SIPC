<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:form onsubmit="return validaFolioCLC()" action="generaArchivoPagosTesofe" namespace="/pagos" name="forma" >
<s:hidden name="" />
<s:hidden name="" />

<div id="dialog_1"></div>

	<fieldset class="clear">
		<legend>Dep&oacute;sitos v&iacute;a TESOFE</legend>
		<div>
			<label class="left1"><span class="requerido">*</span>No. Oficio DGMD a DGAF:</label>
			<s:textfield id="oficioCGC1" name="oficioCGC" maxlength="30" size="31"/>
		</div>
		<div>
			<label class="left1"><span class="requerido">*</span>Folio CLC:</label>
			<s:textfield id="folioCLC1" name="folioCLC" maxlength="5" size="6"/>
		</div>
		<div>
			<label class="left1"><span class="requerido">*</span>N&uacute;mero de Archivo:</label>
			<s:textfield id="consecutivoArchivo" name="consecutivoArchivo" maxlength="2" size="6" />
		</div>
		<div>
			<label class="left1"><span class="requerido">*</span>Secuencia Inicial:</label>
			<s:textfield id="secuenciaInicialArchivoUsuario" name="secuenciaInicialArchivoUsuario" maxlength="4" size="6" />
		</div>
		<div id="validaNumeroSecuenciaArchivo"></div>
		<s:if test='nombreArchivo!=null'>
			<div>			
				<label>Archivo generado:</label>
					<a href="<s:url value="/pagos/descargarArchivoPagosEnvio.action?nombreArchivo=%{nombreArchivo}" />" title="Descargar Archivo Pagos">
						<strong><s:property value="nombreArchivo" /></strong>
					</a>
					<br/>
			</div>
		</s:if>
	</fieldset>
	<br>
	<div class="accion">	
		<s:submit value="Generar Archivo" cssClass="boton2" />
	</div>
	<s:if test="%{lstLog!=null && lstLog.size() > 1}" >
		<fieldset>
		<legend>Resultados:</legend>
			<div class="arial12normal">
				 <s:iterator value="lstLog" status="renglon"> 
				 	<s:if test="%{ (#renglon.count)==1 }">
				 	<table class="simple" style="width:100%">
					 	<tr>
					 		<td><h1><s:property /></h1></td>
					 		<td>
						 	 	<ul class="log">
							 	 	<s:iterator value="logs">
										<li><a href="<s:url value="/pagos/viewLog"/>?l=<s:property value="file" />&hc=<s:property value="file.hashCode()" />" target="_blank"><s:property value="file" /></a> (<s:property value="size"/> )</li>		
									</s:iterator>
								</ul>
							</td>
					 	</tr>
				 	</table>	
				 	</s:if>
				 	<s:else>
						<s:property /> <br>
					</s:else>
				</s:iterator>
			</div>
		</fieldset>
	</s:if>	
</s:form>	
<script type="text/javascript">
<!--
function validaFolioCLC(){ 
	var oficioCGC = $('#oficioCGC1').val();
	var folioCLC = $('#folioCLC1').val();	
	var consecutivoArchivo = $('#consecutivoArchivo').val();
	var secuenciaInicialArchivoUsuario = $('#secuenciaInicialArchivoUsuario').val();
	
	var patronOficio =/^F00.\d{4}\/\d{1,8}\/\d{4}$/;
	var patronFolioCLC =/^\d{1,5}$/;
	var patronConsecutivoArchivo =/^\d{2}$/;
	var patronSecuenciaInicialArchivoUsuario =/^\d{1,4}$/;

	if(!oficioCGC.match(patronOficio)){
		$('#dialogo_1').html('El oficio es incorrecto, se debe capturar conforme a la siguiente nomenclatura ejemplo: F00.4200/99999/2015');
		abrirDialogo();
		return false;
	} 
	
	if(!folioCLC.match(patronFolioCLC)){
		$('#dialogo_1').html('El folio CLC es incorrecto, se deben capturar hasta 5 digitos');
		abrirDialogo();
		return false;
	}	
	if(!consecutivoArchivo.match(patronConsecutivoArchivo)){
		$('#dialogo_1').html('El consecutivo del archivo es incorrecto, se deben capturar 2 digitos');
		abrirDialogo();
		return false;
	}	
	if(!secuenciaInicialArchivoUsuario.match(patronSecuenciaInicialArchivoUsuario)){
		$('#dialogo_1').html('La secuencia inicial proporcinada por el usuario es incorrecta, se deben capturar hasta 4 digitos');
		abrirDialogo();
		return false;
	}	
}

function validaNumeroSecuenciaArchivo(){
	var numeroArchivo = $('#numeroArchivo').val();
	if(numeroArchivo == null || numeroArchivo == ""){
		return false;
	}
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "validaNumeroSecuenciaArchivo",
		   data: "numeroArchivo="+numeroArchivo,
		   success: function(data){
				$('#validaNumeroSecuenciaArchivo').html(data).ready(function () {
				});
		   }
		}); //termina ajax
}
//-->   
</script>

