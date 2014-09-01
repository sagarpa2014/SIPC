<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstCultivoVariedadEsquema.size() > 0">
<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Cultivo</th>
				<th class="clean">Variedad</th>
				<th class="clean">Volumen</th>
			</tr>
			<s:iterator value="lstCultivoVariedadEsquema" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
					
						<s:select id="cultivoVXCV%{#itStatus.count}" name="selectedCultVXCV" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idCultivo}" onchange="recuperaCultivoByVariedadVXCV(this.value, %{#itStatus.count});"/>
					</td>
					<td id="contenedorVariedadVXCV<s:property value="%{#itStatus.count}"/>">
						<s:select id="variedadVXCV%{#itStatus.count}" name="selectedVariedadVXCV" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idVariedad}"/>						
					</td>
					<td>
						<s:if test="volumen!=null">
							<s:textfield id="volumenVXCV%{#itStatus.count}" name="selectedVolumenVXCV" maxlength="15" size="20"  cssClass="cantidad"  value="%{getText('volumenSinComas',{volumen})}" />
						</s:if>
						<s:else>
							<s:textfield id="volumenVXCV%{#itStatus.count}" name="selectedVolumenVXCV" maxlength="15" size="20"  cssClass="cantidad" value="%{}" />
						</s:else>						
					</td>
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>
<s:else>
	
</s:else>


<script>
$(document).ready(function() {	
   
});	 
</script>