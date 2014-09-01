<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1"><span class="norequerido">*</span>Número de Cuenta:</label>
	<s:textfield  disabled="true" maxlength="100" size="30"  value="%{ctaBancaria}"/>
</div>
<div class="clear"></div>
<div>
	<label class="left1"><span class="norequerido">*</span>Número Plaza:</label>
	<s:textfield  disabled="true" maxlength="100" size="30"  value="%{plaza.numeroPlaza}"/>
</div>
<div class="clear"></div>
<div>
	<label class="left1"><span class="norequerido">*</span>Nombre Plaza:</label>
	<s:textfield  disabled="true" maxlength="100" size="30"  value="%{plaza.nombrePlaza}"/>
</div>
<div class="clear"></div>
<div>
	<label class="left1"><span class="norequerido">*</span>Banco:</label>
	<s:textfield  disabled="true" maxlength="100" size="30"  value="%{nombreBanco}"/>
</div>
<div class="clear"></div>
<s:if test="sucursal!=null">
	<div>
		<label class="left1"><span class="norequerido">*</span>Sucursal:</label>
		<s:textfield  disabled="true" maxlength="100" size="30"  value="%{sucursal}"/>
	</div>
</s:if>
<s:else>
	<div>
		<label class="left1"><span class="requerido">*</span>Sucursal:</label>
		<s:textfield id="sucursal" name="sucursal"  maxlength="100" size="30"  value=""/>
	</div>
</s:else>
<div class="clear"></div>

