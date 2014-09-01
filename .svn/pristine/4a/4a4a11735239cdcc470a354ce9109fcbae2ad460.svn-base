<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="idPrograma==-1 ">
	<div>
		<label class="left1">Nombre Comprador:</label>
		<s:select id="idComprador" name="idComprador" list="lstComprador" listKey="idComprador" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="recuperaCartasByComprador()" value="%{}"/>
	</div>
</s:if>
<s:else>
	<div>
		<label class="left1">Nombre Comprador:</label>
		<s:select id="idComprador" name="idComprador" list="lstComByPrograma" listKey="idComprador" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaCartasByComprador()" />
	</div>
</s:else>
<div id="cartas"><s:include value="/WEB-INF/paginas/busquedaPagos/lstCartasAdhesion.jsp"/></div>