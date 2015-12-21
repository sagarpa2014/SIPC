<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1">Localidad:</label>
	<s:select id="claveLocalidad" name="claveLocalidad" list="lstLocalidades" listKey="claveLocalidad" listValue="%{nombreLocalidad}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{claveLocalidad}"/>
</div>													  