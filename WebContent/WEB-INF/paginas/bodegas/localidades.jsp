<%@taglib uri="/struts-tags" prefix="s"%>
<label class="left1"><span class="requerido">*</span>Localidad:</label>
<s:select id="claveLocalidadDomFis" name="claveLocalidadDomFis" list="lstLocalidades"  listKey="claveLocalidad" listValue="%{nombreLocalidad}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{}"/>
													  