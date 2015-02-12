<%@taglib uri="/struts-tags" prefix="s"%>
<div class="inline" >
	<label class="left1">Tipo de Periodo:</label>
	<s:radio label="" id="pideTipoPeriodo" name="pideTipoPeriodo" list="#{2:'CONTRATO AXC', 3:'DICTAMEN AUDITOR'}" value="%{}" onclick="activaPeriodo();"/>
</div>
<div class="clear"></div>
<div id="pideAdendum" class="elementoOculto">				
	<label class="left1">Aplica Adendum:</label>				
	<s:checkbox id="aplicaAdendum"  name="aplicaAdendum" onclick ="activaPeriodoPorAdendun();" fieldValue="true" value="%{}"/>
</div>
<div id="recuperaPeriodoAuditor"></div>
<div id="respuestaPeriodoContratoAdendum"></div>