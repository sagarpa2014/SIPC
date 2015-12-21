<%@taglib uri="/struts-tags" prefix="s"%>
<s:select id="idVariedad" name="idVariedad" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idVariedad}" onchange="validaClaveBodega();"/>

<div id="existenciaAMR" class="elementoOculto"><s:property value="%{existenciaAMAnt}"/></div>