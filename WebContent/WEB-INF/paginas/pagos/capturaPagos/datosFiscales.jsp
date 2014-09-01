<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="comprador.rfc!=null">
	<s:hidden id="tieneRfc" name="tieneRfc" value="%{1}"/>
	<div>
		<label class="left1"><span class="norequerido">*</span>RFC:</label>
		<s:textfield  disabled="true" maxlength="100" size="30"  value="%{comprador.rfc}"/>
	</div>
	<div class="clear"></div>
	<s:if test="comprador.curp!=null && comprador.curp!=''">
		<div>
			<label class="left1"><span class="norequerido">*</span>CURP:</label>
			<s:textfield  disabled="true" maxlength="100" size="30"  value="%{comprador.curp}"/>
		</div>
	</s:if>
</s:if>
<s:else>
	<s:hidden id="tieneRfc" name="tieneRfc" value="%{0}"/> <!-- No tiene RFC, debe capturar -->
	<!-- Capturar los datos fiscales -->
	<div class="inline">
		<label class="left1">Tipo Persona:</label>
		<s:radio label="" onclick="capturarRFC()"  name="personaFiscal" list="#{'1':'FISICA','0':'MORAL'}" value="%{1}" />
	</div>
	<div class="clear"></div>
	<div id="rfc">
		<label class="left1"><span class="requerido">*</span>RFC</label>
		<s:textfield id="rfcc" name="rfc"  maxlength="13" size="30"  value=""/>
	</div>
	<div class="clear"></div>
	<div id="curp">
		<label class="left1"><span class="norequerido">*</span>CURP</label>
		<s:textfield id="curpc" name="curp"  maxlength="18" size="30"  value=""/>
	</div>
</s:else>