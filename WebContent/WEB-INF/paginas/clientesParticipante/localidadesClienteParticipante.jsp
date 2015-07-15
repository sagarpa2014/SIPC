<%@taglib uri="/struts-tags" prefix="s"%>
<label class="left1">Localidad:</label>
<s:select name="clienteParticipante.claveLocalidad" id="claveLocalidad" list="lstLocalidades"  listKey="claveLocalidad" listValue="%{nombreLocalidad}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{clienteParticipante.claveLocalidad}"/>
													  