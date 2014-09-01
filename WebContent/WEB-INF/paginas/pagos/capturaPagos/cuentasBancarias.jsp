<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1"><span class="requerido">*</span>Cuenta Bancaria:</label>
	<s:select id="clabe" name="clabe" list="lstCuentaBancarias" listKey="clabe" listValue="%{clabe}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="recuperaDatosPlaza()" value="%{pagosV.clabe}" />
</div>	
<div class="clear"></div>
<div id="recuperaDatosPlaza"></div>
<div class="clear"></div>
<div class="inline">
	<label class="left1"><span class="norequerido">*</span>Otra Cuenta:</label>
	<s:radio label="" onclick="capturarOtraCuenta()"  name="otrac" list="#{1:'SI',0:'NO'}" value="%{0}" />
</div>
<div class="clear"></div>
<!-- Se activa cuando el usuario selecciona otra cuenta -->
<div id="otraCuenta"></div>