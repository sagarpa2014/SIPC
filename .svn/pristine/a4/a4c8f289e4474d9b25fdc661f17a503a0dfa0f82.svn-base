<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:if test="lstConstanciasAlmacenamiento.size() > 0">
<s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>
<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean">No.</th>
				<th class="clean">Bodega</th>
				<s:if test="registrar == 2">
					<th class="clean">Detalle Bodega</th>
				</s:if>
				<th class="clean">Almacenadora</th>
				<th class="clean">N&uacute;mero de Folio</th>
				<th class="clean">Expedici&oacute;n</th>
				<th class="clean">Cultivo</th>
				<th class="clean">Variedad</th>
				<th class="clean">Volumen</th>
			</tr>
			<s:iterator value="lstConstanciasAlmacenamiento" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<s:if test="registrar == 0">
						<td id="contenedorBodega<s:property value="%{#itStatus.count}"/>">
							<s:textfield id="claveBodega%{#itStatus.count}" name="capBodega" maxlength="15" size="15"  value="%{claveBodega}"  onkeyup="validaClaveBodega(this.value, %{#itStatus.count});" onchange="validaClaveBodega(this.value, %{#itStatus.count});"/>
							<div id="validaClaveBodega<s:property value="%{#itStatus.count}"/>"></div>						
						</td>
					</s:if>
					<s:else>
						<s:if test='#temporal!=claveBodega'>
							<td align="center"><s:textfield id="claveBodega%{#itStatus.count}" name="capBodega" maxlength="15" size="15"  value="%{claveBodega}"  onkeyup="validaClaveBodega(this.value, %{#itStatus.count});" onchange="validaClaveBodega(this.value, %{#itStatus.count});"/></td>
							<td>
								<s:hidden id="claveBodega" name="claveBodega" value="%{claveBodega}"/>
								<a href='<s:url value="/solicitudPago/verDetalleBodegas?claveBodega=%{claveBodega}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
							</td>	
						</s:if>
						<s:else>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</s:else>
						<s:set name="temporal">
							<s:property value="%{claveBodega}" />
						</s:set>
					</s:else>				
					<td>
						<s:select id="alm%{#itStatus.count}" cssClass="lista" name="selectedAlmacenadora" list="lstAlmacenadora" listKey="idAlmacen" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idAlmacenadora}" onchange=""/>
					</td>
					<td id="contenedorFolio<s:property value="%{#itStatus.count}"/>">
						<s:textfield id="folio%{#itStatus.count}" name="capFolio" maxlength="15" size="15" value="%{folio}"  />						
					</td>
					<td> <!-- Expedicion -->
						<s:if test="%{fechaExpedicion==null}" >
							<s:textfield name="selectFechaExpedicion" maxlength="10" size="10" id="fechaExpedicion%{#itStatus.count}" readonly="true" cssClass="dateBox" />
							<img src="../images/calendar.gif" id="trgE<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
							<script type="text/javascript">
								<!--
									Calendar.setup({
										inputField     :    'fechaExpedicion<s:property value="%{#itStatus.count}" />',     
										ifFormat       :    "%d/%m/%Y",     
										button         :    'trgE<s:property value="%{#itStatus.count}" />',  
										align          :    "Br",           
										singleClick    :    true
										});
								//-->
							</script>	
						</s:if>
						<s:else>
							<s:textfield key="fechaExpedicion"  value="%{getText('fecha',{fechaExpedicion})}"  maxlength="10" size="10" id="fechaExpedicion%{#itStatus.count}"/>
						</s:else>
						
					</td>
					<td>
						<s:select id="cult%{#itStatus.count}" cssClass="lista" name="selectedCultivo" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idCultivo}" onchange=""/>
					</td>
					<td>
						<s:select id="var%{#itStatus.count}" cssClass="lista" name="selectedVariedad" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idVariedad}" onchange=""/>
					</td>					
					<td class="cVolumen">
						<s:if test="volumen!=null">
							<s:textfield id="v%{#itStatus.count}" name="capVolumen" maxlength="15" size="15"  cssClass="cantidad"  value="%{getText('volumenSinComas',{volumen})}" />
						</s:if>
						<s:else>
							<s:textfield id="v%{#itStatus.count}" name="capVolumen" maxlength="15" size="15"  cssClass="cantidad" value="%{}" />
						</s:else>						
					</td>
				</tr>

			</s:iterator>
			<tr>
				<td colspan = "7"></td>
				<td align="right"><strong>Totales:</strong></td>
				<td>	
					<s:if test="totalVolumen!=null">
						<s:textfield id="totalesVolumen" cssClass="cantidad" disabled="true" value="%{getText('volumen',{totalVolumen})}" size="15"/>
					</s:if>
					<s:else>
						<s:textfield id="totalesVolumen" cssClass="cantidad" disabled="true"/>
					</s:else>
				</td>
			</tr>
			
		</table>
	</center>
</s:if>


<script>
$(document).ready(function() {
		$(".cVolumen").change(function(){
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
});


</script>		

