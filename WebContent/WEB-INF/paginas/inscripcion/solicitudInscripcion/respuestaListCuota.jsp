<%@taglib uri="/struts-tags" prefix="s"%>
<s:select id="cuota%{count}" name="capCuota" list="lstCuota" listKey="cuota" listValue="%{getText('importeSinComas',{cuota})}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{cuota}" onchange="recuperaTotalVolumen(%{count});"/>
					
					
					  