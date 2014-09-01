<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<center>
	<div id="lstVolumenInscripcion">
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Estado</th>
				<th class="clean">Cultivo</th>
				<th class="clean">Variedad</th>
				<s:if test="idCriterioPago==1 || idCriterioPago==3">
					<th class="clean">Volumen</th>
				</s:if>
				<s:if test="idCriterioPago==2">
					<th class="clean">Importe</th>
				</s:if>
				<s:if test="registrar==0">
					<s:if test="volumenXCultivoVariedad==1"> <!-- Si en la inicializacion existe un limite de volumen por culñtivo variedad -->
						<th class="clean"> Volumen M&aacute;ximo<br>Cultivo Variedad</th>
					</s:if>
				</s:if>					
			</tr>	
				<s:iterator value="lstAsigCA" var="resultado"  status="itStatus">
					<tr>					
						<td><s:property value="%{#itStatus.count}"/></td>
						<td>
							<s:select id="e%{#itStatus.count}" name="capEstado" list="lstEstado" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEstado}" onchange="recuperaCultivoByEstadoAsigCA(this.value, %{#itStatus.count});"/>
						</td>
						<td id="cultivo<s:property value="%{#itStatus.count}"/>">
							<s:select id="c%{#itStatus.count}" name="capCultivo" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idCultivo}" />
						</td>
						<td id="variedad<s:property value="%{#itStatus.count}"/>">
						  	<s:select id="va%{#itStatus.count}" name="capVariedad" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idVariedad}"/>
						</td>
						<!-- <td>
							<s:select id="va%{#itStatus.count}" name="capVariedad" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idVariedad}"/>
						</td>
						 -->						
						
						<s:if test="idCriterioPago==1 || idCriterioPago==3">
							<s:if test="registrar==0"><s:set var="volumen" value="%{}" /></s:if>
							<s:elseif test="registrar==1"><s:set var="volumen" value="%{volumenAutorizado}" /></s:elseif>
							<s:elseif test="registrar==2 || registrar == 3"><s:set var="volumen" value="%{volumen}" /></s:elseif>
							<td class="cVolumen">
								<s:if test="#volumen!=null">
									<s:textfield id="v%{#itStatus.count}" name="capVolumen" value="%{getText('volumenSinComas',{#volumen})}" maxlength="14" size="20"  cssClass="cantidad" />
								</s:if>
								<s:else>
									<s:textfield id="v%{#itStatus.count}" name="capVolumen" value="%{}" maxlength="14" size="20"  cssClass="cantidad" onchange="recuperaTotalVolumen(this.value, %{#itStatus.count});"/>
								</s:else>
							</td>							
							<s:if test="volumenXCultivoVariedad==1"> <!-- Si en la inicializacion existe un limite de volumen por cultivo variedad -->
								<td id="vxcv<s:property value="%{#itStatus.count}"/>">
									<s:textfield id="" name="" value=""  maxlength="14" size="20"  cssClass="cantidad" disabled="true"/>
								</td>
							</s:if>							
						</s:if>
						<s:elseif test="idCriterioPago==2">
							<s:if test="registrar==0"><s:set var="importe" value="%{}" /></s:if>
							<s:elseif test="registrar==1"><s:set var="importe" value="%{importeAutorizado}" /><s:property value="%{importeAutorizado}"/></s:elseif>
							<s:elseif test="registrar==2"><s:set var="importe" value="%{importe}" /><s:property value="%{importeAutorizado}"/></s:elseif>
							<td class="cImporte">
								<s:if test="#importe!=null">
									<s:textfield id="i%{#itStatus.count}" name="capImporte" value="%{getText('importeSinComas',{#importe})}" disabled="true" cssClass="cantidad" onchange="calculaTotalImporte(this.value);" onblur="calculaTotalImporte(this.value);" />
								</s:if>
								<s:else>
									<s:textfield id="i%{#itStatus.count}" name="capImporte" value="%{}"  cssClass="cantidad" onchange="calculaTotalImporte(this.value);" onblur="calculaTotalImporte(this.value);" />
								</s:else>
							</td>
						</s:elseif>
					</tr>
						
				</s:iterator>
		</table>
	</div>
</center>

<s:if test="registrar==3">
<script>
	$(document).ready(function() {
		$("#lstVolumenInscripcion").find('input').attr('disabled','disabled');	
		$("#lstVolumenInscripcion").find('select').attr('disabled','disabled');	
	});		 
</script>	

</s:if>



<script>

$(document).ready(function() {		
	
	
    /*$(".cVolumen").change(function() {	
        var index = $(".cVolumen").index(this);
        var indexMasUno=index +1;
        var v=$('#v'+indexMasUno).val();
        var patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del registro es inválido, se aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
       		abrirDialogo();
       		return false;
    	}
        calculaTotalVolumen(v);
        
        
    }); //end change .cVolumen
    */
    
    $(".cImporte").change(function() {	
        var index = $(".cImporte").index(this);
        var indexMasUno=index +1;
        var imp=$('#i'+indexMasUno).val();
        var patron =/^\d{1,10}((\.\d{1,2})|(\.))?$/;
    	if(i=="" || i==null){
    		return false;
    	}
    	if(!imp.match(patron)){
    		$('#dialogo_1').html('El valor del registro es inválido, se aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
       		abrirDialogo();
       		return false;
    	}
       
    	calculaTotalImporte();
        
        
    }); //end change .cVolumen


    function calculaTotalImporte(){
    	var numCampos = $('#numCampos').val();
    	var totalImporte = null;
    	var importe = null;
    	var i = 0;
    	/*Calcula el valor total de importe*/
    	for (i=1;i<parseInt(numCampos)+1;i++){
    		importe = $('#i'+i).val();
    		if(importe!="" && importe !=null){
    			totalImporte = (totalImporte+parseFloat(importe));	
    		}
    	}
    	
    	$("#totalesImporte").val(totalImporte.toFixed(2));	
    	
    }

    
});


		 
</script>	


