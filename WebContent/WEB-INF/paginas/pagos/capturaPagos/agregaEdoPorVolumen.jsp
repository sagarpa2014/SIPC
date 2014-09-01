<%@taglib uri="/struts-tags" prefix="s"%>

<s:if test="lstPagosDetalle.size() > 0">
<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Estado</th>
				<s:if test="criterioPago==2 || criterioPago==3">
					<th class="clean">Etapa</th>
				</s:if>
				<s:if test="criterioPago==1 || criterioPago==3">
					<th class="clean">Volumen</th>
				</s:if>				
				<th class="clean">Importe</th>
				<s:if test="criterioPago==1 || criterioPago==3">
					<th class="clean">Cuota</th>
				</s:if>
			</tr>
			<s:iterator value="lstPagosDetalle" var="resultado"  status="itStatus">
			<s:if test="editar==1"> <!-- Edicion -->
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:select id="e%{#itStatus.count}" name="selectedEdos" list="lstProgramaEdoV" listKey="idEstado" listValue="%{estado}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEstado}"/>
					</td>
					<td class="cVolumen">
						<s:textfield id="v%{#itStatus.count}" name="capVolumen" maxlength="14" size="20"  cssClass="cantidad" value="%{getText('volumenSinComas',{volumen})}" />
					</td>
					<td class="cImporte">
						<s:textfield id="i%{#itStatus.count}" name="capImporte" maxlength="16" size="20"  cssClass="cantidad" onchange="calculaTotalImporte(this.value);" onblur="calculaTotalImporte(this.value);"  value="%{getText('importeSinComas',{importe})}"/>
					</td>
					<td>
						<s:textfield id="cuota%{#itStatus.count}" disabled="true"   cssClass="cantidad" value="%{}"/>
					</td>
				</tr>
			</s:if>
			<s:else> <!-- Captura -->
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:select id="e%{#itStatus.count}" name="selectedEdos" list="lstProgramaEdoV" listKey="idEstado" listValue="%{estado}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" />
					</td>
					<s:if test="criterioPago==2 || criterioPago==3">
						<td class="cEtapa">
							<s:select id="etapa%{#itStatus.count}" name="capEtapa"  headerKey="-1" onchange=""
								headerValue="Seleccione una opción"								
								list="#{'I':'I', 'II':'II', 'III':'III','IV':'IV','V':'V'}" />
						</td>
					</s:if>
					<s:if test="criterioPago==1 || criterioPago==3">
						<td class="cVolumen">
							<s:textfield id="v%{#itStatus.count}" name="capVolumen" maxlength="14" size="20"  cssClass="cantidad"  />
						</td>
					</s:if>			
					<td class="cImporte">
						<s:textfield id="i%{#itStatus.count}" name="capImporte" maxlength="16" size="20"  cssClass="cantidad" onchange="calculaTotalImporte(this.value);" onblur="calculaTotalImporte(this.value);"  />
					</td>
					<s:if test="criterioPago==1 || criterioPago==3">
						<td>
							<s:textfield id="cuota%{#itStatus.count}" disabled="true"   cssClass="cantidad" value="%{}"/>
						</td>
					</s:if>
				</tr>
			</s:else>
				
			</s:iterator>
			<tr>
				
				<s:if test="criterioPago==1">
					<td></td>
					<td align="right"><strong>Totales:</strong></td>
					<td><s:textfield id="totalesVolumen" cssClass="cantidad" disabled="true" value="%{getText('volumen',{pagosV.volumen})}"/></td>
					<td><s:textfield id="totalesImporte" cssClass="cantidad" disabled="true" value="%{getText('importe',{pagosV.importe})}"/></td>
					<td></td>
				</s:if>
				<s:elseif test="criterioPago==2">
					<td colspan="2"></td>
					<td align="right"><strong>Totales:</strong></td>
					<td><s:textfield id="totalesImporte" cssClass="cantidad" disabled="true" value="%{getText('importe',{pagosV.importe})}"/></td>
				</s:elseif>
				<s:elseif test="criterioPago==3">
					<td colspan="2"></td>
					<td align="right"><strong>Totales:</strong></td>
					<td><s:textfield id="totalesVolumen" cssClass="cantidad" disabled="true" value="%{getText('volumen',{pagosV.volumen})}"/></td>
					<td><s:textfield id="totalesImporte" cssClass="cantidad" disabled="true" value="%{getText('importe',{pagosV.importe})}"/></td>
				</s:elseif>	
				
				
				<s:else><td></td></s:else>
				
			</tr>
		</table>
		<div>
			<p><span class="requerido">* Debe capturar los volumenes por estado</span></p>
		</div>
	</center>
</s:if>
<s:else>
	No hay estados asociados al programa seleccionado, por favor verifique
	<s:hidden name="errorProgSinEdos" id="errorProgSinEdos" value="%{errorProgSinEdos}"/>
</s:else>


<script>

$(document).ready(function() {
	//Si la accion es editar, calcula las cuotas
	if ($('#editar').length){
		if(editar == 1){
			var numCampos = $('#numCampos').val();
			var importe ="";
			var volumen="";
			var cuota="";
			for (i=1;i<parseInt(numCampos)+1;i++){
				importe = $('#i'+i).val();
				volumen = $('#v'+i).val();
				if((volumen!="" && volumen !=null)&&(importe!="" && importe !=null)){
					cuota = parseFloat(importe)/parseFloat(volumen);
					$("#cuota"+i).val(parseFloat(cuota.toFixed(2)));
				}
			
		}
	}
}
	
	
    $(".cVolumen").change(function() {	
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
        
        var imp=$('#i'+indexMasUno).val();
        calculaTotalVolumen(v);
        if((v!="" && v!=null)&&(imp!="" && imp!=null) ){
        	calculaCuota(v, imp, indexMasUno);
        }
        
    }); //end change .cVolumen

    $(".cImporte").change(function() {	
        var index = $(".cImporte").index(this);
        var indexMasUno=index +1;
        var v=$('#v'+indexMasUno).val();
        var imp=$('#i'+indexMasUno).val();
        var patron =/^\d{1,13}((\.\d{1,2})|(\.))?$/;
    	if(imp=="" || imp==null){
    		return false;
    	}
    	if(!imp.match(patron)){
    		$('#dialogo_1').html('El valor del registro es inválido, se aceptan hasta 13 digitos a la izquierda y 2 máximo a la derecha');
       		abrirDialogo();
       		return false;
    	}
        
        calculaTotalImporte(imp);
        if((v!="" && v!=null)&&(imp!="" && imp!=null) ){
        	calculaCuota(v, imp, indexMasUno);
        }
        
    }); //end change .cImporte
});

		 
</script>	


