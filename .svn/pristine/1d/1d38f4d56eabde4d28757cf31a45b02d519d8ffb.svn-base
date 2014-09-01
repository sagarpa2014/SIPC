<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="idPrograma==-1 ">
	<div>
		<label class="left1">Nombre Comprador:</label>
		<s:select id="idComprador" name="idComprador" list="lstCompByPrograma" listKey="idComprador" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="" value="%{}"/>
	</div>
</s:if>
<s:else>
	<div>
		<label class="left1">Nombre Comprador:</label>
		<s:select id="idComprador" name="idComprador" list="lstCompByPrograma" listKey="idComprador" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --"  />
	</div>
</s:else>