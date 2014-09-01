<%@taglib uri="/struts-tags" prefix="s"%>
<div> 
	<label class="left1">Carta Adhesi&oacute;n:</label>
	<s:select id="noCarta" name="noCarta" list="lstCartas" listKey="carta" listValue="%{carta}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="" value="%{}"/>
</div>