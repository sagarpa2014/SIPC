<%@taglib uri="/struts-tags" prefix="s"%>
<div id="dialogo_2"></div>
<s:if test="idCriterioPago==1 || idCriterioPago ==3">
	<div>
		<label class="left1"><span class="requerido">*</span>Unidad de Medida:</label>
		<s:select id="idUnidadMedida" name="idUnidadMedida" list="lstUnidadMedida" listKey="idUnidadMedida" listValue="%{unidadMedida}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idUnidadMedida}" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Volumen Autorizado en Aviso:</label>
		<s:if test="volumen!=null">
			<s:textfield id="volumen" name="volumen"  maxlength="15" size="20"  value="%{getText('volumenSinComas',{volumen})}"/>
		</s:if>
		<s:else>
			<s:textfield id="volumen" name="volumen"  maxlength="15" size="20"  value="%{}"/>
		</s:else>
	</div>
	<div class="clear"></div>	
	<div class="inline">
		<label class="left1"><span class="requerido">*</span>¿Desea Agregar Volumen por Cultivo Variedad?</label>
		<s:radio label="" onclick="agregaNumCamposCultVar()" name="volxCulVar" list="#{0:'SI',1:'NO'}" value="%{volxCulVar}" />
		<s:if test="editar==3 || 4">
		
		</s:if>
	</div>
	<div class="clear"></div>	
	<div id="numVolPorCultivoVariedad">
		<label class="left1"><span class="requerido">*</span>Número Campos de Volumen por Cultivo Variedad a Apoyar:</label>
		<s:textfield id="numCamposVXCV" name="numCamposVXCV"  maxlength="3" size="5"  value="%{numCamposVXCV}"/>
	</div>
	<div id="agregarVolumenPorCultivoVariedad">
		<s:if test="editar==1 || editar==2 || editar==3 || editar==4">
			<s:include value="/WEB-INF/paginas/inscripcion/inicializacionPrograma/agregaVolumenPorCultivoVariedad.jsp" />
		</s:if>
	</div>
	<script>
		$(document).ready(function() {
			$("#numCamposVXCV").keyup(function(event){
					//manda a llamar
					var numCamposVXCV = $('#numCamposVXCV').val();
					var editar = $('#editar').val();
					if(numCamposVXCV == null || numCamposVXCV == ""){
						return false;
					}
					
					var patron =/^\d{1,3}$/;
					if(!numCamposVXCV.match(patron)){
			    		$('#dialogo_2').html('El valor del campo es inválido, se aceptan hasta 3 dígitos');
			       		abrirDialogo2();
			       		$('#numCamposVXCV').val(null);
			       		return false;
			    	}  
					var idPrograma ="";
					var numCamposVXCVAnterior ="";
					console.log("ok1");
					if(editar==3 || editar==4){
						idPrograma = $('#idPrograma').val();
						numCamposVXCVAnterior = $('#numCamposVXCVAnterior').val();
					}
					
					$.ajax({
						   async: false,
						   type: "POST",
						   url: "agregarVolumenPorCultivoVariedad",
						   data: "numCamposVXCV="+numCamposVXCV+
					   		"&editar="+editar+
					   		"&numCamposVXCVAnterior="+numCamposVXCVAnterior+
					   		"&idPrograma="+idPrograma, 
						   success: function(data){
								$('#agregarVolumenPorCultivoVariedad').html(data).ready(function () {
									$("#agregarVolumenPorCultivoVariedad").fadeIn('slow');
									
								});
						   }
						});//fin ajax
				});//termina keyup numCampos	
		});	 
	</script>
	
	
</s:if>
<s:if test="idCriterioPago==2 || idCriterioPago==3" >
	<s:if test="idCriterioPago==2">
		<div>
			<label class="left1"><span class="requerido">*</span>Importe Autorizado a Apoyar por Etapa:</label>
			<s:if test="importe!=null">
				<s:textfield id="importe" name="importe"  maxlength="15" size="20"  value="%{getText('importeSinComas',{importe})}"/>
			</s:if>
			<s:else>
				<s:textfield id="importe" name="importe"  maxlength="15" size="20"  value="%{}"/>
			</s:else>
		</div>
	</s:if>	
	<div class="clear"></div>		
	<div>
		<label class="left1"><span class="requerido">*</span>N&uacute;mero Etapas:</label>
		<s:select id="noEtapa" name="noEtapa" onchange="selectNumEtapas();" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'1':'1', '2':'2', '3':'3', '4':'4'}"  value="%{noEtapa}"/>
	</div>
	<div id="selectNumEtapas">
		<s:if test="editar==1 || editar==2 || editar==3 || editar==4">
			<s:include value="/WEB-INF/paginas/inscripcion/inicializacionPrograma/selectNumEtapa.jsp" />
		</s:if>
	</div>
</s:if>
<div class="clear"></div>	
<div>
	<label class="left1"><span class="requerido">*</span>Estados a Apoyar:</label>
	<s:textfield id="numCampos" name="numCampos"  maxlength="3" size="5"  value="%{numCampos}"/>
</div>	
<div class="clear"></div>	
<div id="agregaCultivoEstadoIniProg">
	<s:if test="editar==1 || editar==2 || editar==3 || editar==4">
		<s:include value="/WEB-INF/paginas/inscripcion/inicializacionPrograma/agregaCultivoEstadoIniProg.jsp" />
	</s:if>	
</div>


<script>
	$(document).ready(function() {
		$("#numCampos").keyup(function(event){
			var volxCulVar = -1;
			var selectedCultVXCV = new Array();
			var selectedVariedadVXCV = new Array();
			var idCriterioPago = $('#idCriterioPago').val();

			if(idCriterioPago == 1 || idCriterioPago ==3){
				 volxCulVar = $('input:radio[name=volxCulVar]:checked').val();
				if(volxCulVar == 0){
					var numCamposVXCV = $('#numCamposVXCV').val();
					if(numCamposVXCV < 1 || numCamposVXCV ==null ||  numCamposVXCV ==""){
						$('#dialogo_2').html('Capture el número de registros para el volumen por cultivo variedad');
				   		abrirDialogo2();
						return false;
					}else{
						var i =0;
						var j = 0;
						var cultivoVXCV ="";
						var variedadVXCV ="";
						var volumenVXCV ="";
						var cultivoVXCVTemp ="";
						var variedadVXCVTemp = "";
						//var volumenVXCVTemp = "";
						//Validar que los campos sean seleccionados
						console.log("variedadVXCV1"+  $('#variedadVXCV1').val());
						for (i=1;i<parseInt(numCamposVXCV)+1;i++){
							cultivoVXCV = $('#cultivoVXCV'+i).val();
							console.log("cultivoVXCV"+ cultivoVXCV);
							variedadVXCV = $('#variedadVXCV'+i).val();
							
							volumenVXCV = $('#volumenVXCV'+i).val();
													
							if(cultivoVXCV ==-1 || variedadVXCV ==-1 || (volumenVXCV == "" || volumenVXCV == null)){
					   			$('#dialogo_2').html('Capture los valores del registro '+i+' volumen  por cultivo variedad');
						   		abrirDialogo2();
						 		return false;
					   		 }else{
					   			 //Valida que el cultivo y variedad no se encuentren repetidos
					   			for (j=1;j<parseInt(numCamposVXCV)+1;j++){
					   				if(i!=j){
					   					cultivoVXCVTemp = $('#cultivoVXCV'+j).val();
										variedadVXCVTemp = $('#variedadVXCV'+j).val();
										if(cultivoVXCV == cultivoVXCVTemp && variedadVXCV == variedadVXCVTemp){
											
											$('#dialogo_2').html('El registro '+i+' en volumen por cultivo variedad se encuentra repetido, favor de verificar');
						   			   		abrirDialogo2();
						   			 		return false;
										}
					   				}
					   			}
					   		 }
							selectedCultVXCV.push($('#cultivoVXCV'+i).val());
							selectedVariedadVXCV.push($('#variedadVXCV'+i).val());
						}
						
					}
				}// end else volxCulVar
			}
			
			//manda a llamar
			var numCampos = $('#numCampos').val();
			var editar = $('#editar').val();
			if(numCampos == null || numCampos == ""){
				return false;
			}
			
			var patron =/^\d{1,3}$/;
			if(!numCampos.match(patron)){
	    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 3 dígitos');
	       		abrirDialogo();
	       		$('#numCampos').val(null);
	       		return false;
	    	}  
			var idPrograma ="";
			var numCamposAnterior ="";
			
			if(editar==3 || editar==4){
				idPrograma = $('#idPrograma').val();
				numCamposAnterior = $('#numCamposAnterior').val();
			}
			
			$.ajax({
				   async: false,
				   type: "POST",
				   url: "agregaCultivoEstadoIniProg",
				   data: "numCampos="+numCampos+
			   		"&editar="+editar+
			   		"&numCamposAnterior="+numCamposAnterior+
			   		"&idPrograma="+idPrograma+
			   		"&selectedCultVXCV="+selectedCultVXCV+
			   		"&selectedVariedadVXCV="+selectedVariedadVXCV+
			   		"&volxCulVar="+volxCulVar+
			   		"&idCriterioPago="+idCriterioPago, 
				   success: function(data){
						$('#agregaCultivoEstadoIniProg').html(data).ready(function () {
							$("#agregaCultivoEstadoIniProg").fadeIn('slow');
							
						});
				   }
				});//fin ajax
	
		});//termina keyup numCampos	

	});	 
</script>

