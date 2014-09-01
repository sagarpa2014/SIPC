<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div class="clear"></div>
<div id="cartas">
	<label class="left1"><span class="requerido">*</span>Carta</label>
	<s:select id="noCarta" name="noCarta" list="lstPrgoComp" listKey="noCarta" listValue="%{noCarta}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" />
</div>
<div class="clear"></div>
<!-- Datos de la cuenta Bancaria del comprador -->
	<s:if test="lstCuentaBancarias.size() > 0">
		<s:hidden id="tieneCuenta" name="tieneCuenta" value="%{1}"/> 
		<s:include value="/WEB-INF/paginas/pagos/capturaPagos/cuentasBancarias.jsp" />
		<div class="clear"></div>
		<!-- Se activa cuando el usuario selecciona otra cuenta -->
		<div id="otraCuenta"></div>
		<div class="clear"></div>
	</s:if>
	<s:else>
		<s:hidden id="tieneCuenta" name="tieneCuenta" value="%{0}"/>
		<s:include value="/WEB-INF/paginas/pagos/capturaPagos/otraCuenta.jsp" />
	</s:else>

<div id="">
<!--Datos fiscales del comprador -->
	<s:include value="/WEB-INF/paginas/pagos/capturaPagos/datosFiscales.jsp" />
</div>



