<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="%{pideTipoPeriodo == 3}">
	<s:if test="%{fechaInicioAuditor==null && fechaFinAuditor==null}" >
		<span class="requerido">No se ha capturado el periodo del dictamen del auditor, por favor capture la informaci&oacute;n en el apartado de la documentaci&oacute;n de la solicitud de pago</span><br>
	</s:if>
	<s:else>
		<s:include value="mostrarPeriodo.jsp"/>
	</s:else>
</s:if>
<div class="clear"></div>
<div class="inline">				
	<label class="left1">Criterio a Aplicar:</label>
	<s:if test="%{pideTipoPeriodo == 2}">
		<div class="izquierda"><label>Aplica Adendum</label></div>	
		<div class="izquierda">
			<s:checkbox id="aplicaAdendum"  name="aplicaAdendum" onclick ="activaPeriodoPorAdendun();" fieldValue="true" value="%{}"/>
		</div>
	</s:if>
	<s:if test="grupoCriterio!='Boletas'">			
		<div class="izquierda"><label>Aplica Complemento</label></div>
		<div class="izquierda">
			<s:checkbox id="aplicaComplemento"  name="aplicaComplemento" onclick ="activaPeriodoPorComplemento();" fieldValue="true" value="%{}"/>
		</div>	
	</s:if>
</div>
<div class="clear"></div>
<div id="respuestaPeriodoContratoAdendum"></div>
<div id="respuestaPeriodoContratoComplemento"></div>

