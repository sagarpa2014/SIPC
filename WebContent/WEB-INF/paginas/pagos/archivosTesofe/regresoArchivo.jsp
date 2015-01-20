<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>

<s:form onsubmit="return validaArchivoEnvio()" action="regresaArchivoPagosTesofe" namespace="/pagos" name="forma" >
<s:hidden name="" />
<s:hidden name="" />

<div id="dialog_1"></div>

	<fieldset class="clear">
		<legend>Regreso de Archivos de Env&iacute;o de Pagos TESOFE</legend>
		<div>
			<label class="left1"><span class="norequerido">*</span>No. Oficio DGMD a DGAF:</label>
			<s:textfield id="oficioCGC1" name="oficioCGC" maxlength="30" size="31"/>
		</div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Folio CLC:</label>
			<s:textfield id="folioCLC1" name="folioCLC" maxlength="5" size="6"/>
		</div>
	</fieldset>
	<br>
	<div class="accion">	
		<s:submit value="Regresa Archivo" cssClass="boton2" />
	</div>
</s:form>	
<script type="text/javascript">
function validaArchivoEnvio(){ 
	var oficioCGC = $('#oficioCGC1').val();
	var folioCLC = $('#folioCLC1').val();	
	
	var patronOficio =/^F00.\d{4}\/\d{1,8}\/\d{4}$/;
	var patronFolioCLC =/^\d{1,5}$/;
	
	if((oficioCGC==null || oficioCGC=="") && (folioCLC==null || folioCLC=="")){
		$('#dialogo_1').html('Debe proporcionar al menos un valor en alguno de los campos No. Oficio CLC y/o Folio CLC');
		abrirDialogo();
		return false;		
	}
	
	if(oficioCGC!=null && oficioCGC!=""){
		if(!oficioCGC.match(patronOficio)){
			$('#dialogo_1').html('El oficio es incorrecto, se debe capturar conforme a la siguiente nomenclatura ejemplo: F00.4200/99999/2015');
			abrirDialogo();
			return false;
		}		
	}
	
	if(folioCLC!=null && folioCLC!=""){
		if(!folioCLC.match(patronFolioCLC)){
			$('#dialogo_1').html('El folio CLC es incorrecto, se deben capturar hasta 5 digitos');
			abrirDialogo();
			return false;
		}
	}
}
</script>

