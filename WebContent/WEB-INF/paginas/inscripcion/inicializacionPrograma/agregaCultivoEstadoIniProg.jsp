<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstCuotasEsquema.size() > 0">
<div class="clear"></div>
	<center>
		<table class="clean" id="tableCultivoEstadoIniProg">
			<tr>
				<th class="clean"></th>
				<th class="clean">Cultivo</th>
				<th class="clean">Variedad</th>
				<th class="clean">Estado</th>
				<s:if test="idCriterioPago == 1">
					<th class="clean">Cuota</th>
				</s:if>
				<th class="clean">Precio Pagado</th>
			</tr>
			<s:iterator value="lstCuotasEsquema" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<s:hidden id="contador" name="contador" value="%{#itStatus.count}"/>
					<td id="contenedorCultivoEdo<s:property value="%{#itStatus.count}"/>">
						<s:select id="c%{#itStatus.count}" name="selectedCult" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opci�n --" value="%{idCultivo}" onchange="recuperaCultivoByVariedad(this.value, %{#itStatus.count});" />
					</td>
					<td id="variedad<s:property value="%{#itStatus.count}"/>">
						<s:select id="va%{#itStatus.count}" name="selectedVariedad" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="--Seleccione una opci�n--" tabindex="0" value="%{idVariedad}"/>
					</td>
					<td id="contenedorListEdo<s:property value="%{#itStatus.count}"/>">
						<s:select id="e%{#itStatus.count}" name="selectedEdos" list="lstEstado" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" value="%{idEstado}"/>
					</td>
					<s:if test="idCriterioPago == 1">
						<td class="cCuota">
							<s:if test ="cuota!=null">
								<s:textfield id="cuota%{#itStatus.count}" name="cuota" maxlength="15" size="20"  cssClass="cantidad" value="%{getText('importeSinComas',{cuota})}" />
							</s:if>
							<s:else>
								<s:textfield id="cuota%{#itStatus.count}" name="cuota" maxlength="15" size="20"  cssClass="cantidad" value="%{}" />
							</s:else>
						</td>
					</s:if>
					<td id="contenedorPrecioPagado<s:property value="%{#itStatus.count}"/>">
							<s:if test ="precioPagado!=null">
								<s:textfield id="precioPagado%{#itStatus.count}" name="precioPagado" maxlength="15" size="20"  cssClass="cantidad" value="%{getText('importeSinComas',{precioPagado})}" onblur="validarNumerosValidos(this.value, 'precioPagado%{#itStatus.count}', 1);"/>
							</s:if>
							<s:else>
								<s:textfield id="precioPagado%{#itStatus.count}" name="precioPagado" maxlength="15" size="20"  cssClass="cantidad" value="%{}"  onblur="validarNumerosValidos(this.value, 'precioPagado%{#itStatus.count}', 1);"/>
							</s:else>
					</td>
					
					
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>

<script>
$(document).ready(function() {	
    $(".cCuota").change(function() {	
        var index = $(".cCuota").index(this);
        var indexMasUno=index +1;
        var v=$('#v'+indexMasUno).val();
        var patron =/^\d{1,10}((\.\d{1,2})|(\.))?$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del registro es inv�lido, se aceptan hasta 10 digitos a la izquierda y 2 m�ximo a la derecha');
       		abrirDialogo();
       		$('#v'+indexMasUno).val(null);
       		return false;
    	}        
    }); //end change .cCuota
});	 
</script>