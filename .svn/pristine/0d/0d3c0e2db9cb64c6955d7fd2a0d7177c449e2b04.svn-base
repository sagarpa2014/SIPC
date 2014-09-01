<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="lstProgramaEdoV.size() > 0">
	<div>
		<label class="left1">Ciclo:</label>
		<s:textfield disabled="true" maxlength="13" size="30"  value="%{lstProgramaEdoV.get(0).ciclo}"/>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Estado:</label>
		<s:select id="idEstado" name="idEstado" list="lstProgramaEdoV" listKey="idEstado" listValue="%{estado}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{}" onchange="recuperaPagos()"/>
	</div>
	<div id="recuperaPagos"></div>
</s:if>
<s:else>
	<h2>No se encontraron estados asociados al programa</h2>
</s:else>


