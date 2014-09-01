<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1"><span class="requerido">*</span>Programa:</label>
	<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="recuperaPagos()"  onclick="recuperaPagos()" value="%{}"/>
</div>
<div id="recuperaPagos"></div>

