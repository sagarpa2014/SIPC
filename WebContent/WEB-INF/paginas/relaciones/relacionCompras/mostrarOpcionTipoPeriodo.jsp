<%@taglib uri="/struts-tags" prefix="s"%>
<div class="inline" >
	<label class="left1">Tipo de Periodo:</label>
	<s:radio label="" id="pideTipoPeriodo" name="pideTipoPeriodo" list="#{2:'CONTRATO AXC', 3:'DICTAMEN AUDITOR'}" value="%{}" onclick="activaPeriodo();"/>
</div>
<div class="clear"></div>

<div id="pideAdendum" class="elementoOculto">	
	<div class="inline" >				
		<label class="left1">Criterio a Aplicar:</label>
		<s:if test="grupoCriterio!='Boletas'">
			<s:radio label="" id="aplicaAdendum" name="aplicaAdendum" list="#{1:'ADENDUM', 2:'COMPLEMENTO'}" value="%{}" onclick="activaPeriodoPorAdendun();"/>
		</s:if>
		<s:else>
			<s:radio label="" id="aplicaAdendum" name="aplicaAdendum" list="#{1:'ADENDUM'}" value="%{}" onclick="activaPeriodoPorAdendun();"/>
		</s:else>						
<%-- 	<s:checkbox id="aplicaAdendum"  name="aplicaAdendum" onclick ="activaPeriodoPorAdendun();" fieldValue="true" value="%{}"/> --%>
	</div>
</div>
<div id="recuperaPeriodoAuditor"></div>
<div id="respuestaPeriodoContratoAdendum"></div>
<div id="respuestaPeriodoContratoComplemento"></div>