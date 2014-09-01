<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>RELACI&Oacute;N DE CONSTANCIAS DE ALMACENAMIENTO</h1></div><br>
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
		<legend>Constancias de Almacenamiento</legend>
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
				<s:radio label="" onclick="seleccionaTipoCargaCons()"  name="tipoCargaCons" list="#{'1':'Carga por Archivo','2':'Carga Individual'}" value="%{tipoCargaCons}"/>
				<div class="clear"></div>
				<div id="tipoCargaCons">
					<s:include value="capMasivaConstanciasAlmacenamiento.jsp"/>
				</div>
				<div class="clear"></div>
			</s:if>
		</div>
		<div class="clear"></div>
		
		<div>
		<s:else>
			<div class="derecha"><a href="<s:url value="/solicitudPago/capConstanciasAlmacenamiento?folioCartaAdhesion=%{folioCartaAdhesion}&registrar=%{0}&agregarCons=%{1}"/>" onclick="" title="" >Agregar Constancias</a></div>
		</s:else>
		</div>
		<div class="clear"></div>
			<s:if test="registrar==2">
				<div>		
					<label class="left1"><span class="requerido">*</span>N&uacute;mero de Constancias Capturadas:</label>
					<s:textfield id="numCampos" name="numCampos"  maxlength="2" size="5"  value="%{numCampos}"/>
				</div>
				<div id="agregarConstanciaAlmacenamiento">
					<s:include value="agregarConstanciaAlmacenamiento.jsp"/>
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
						   url: "agregarConstanciaAlmacenamiento",
						   data: "numCampos="+numCampos, 
						   success: function(data){
								$('#agregarConstanciaAlmacenamiento').html(data).ready(function () {
									$("#agregarConstanciaAlmacenamiento").fadeIn('slow');
									
								});
						   }
						});//fin ajax
				});//termina keyup numCampos	
		});	 
	</script>

