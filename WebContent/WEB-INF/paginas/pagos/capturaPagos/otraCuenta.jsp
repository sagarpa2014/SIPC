<%@taglib uri="/struts-tags" prefix="s"%>
<div id="cclabe">
	<label class="left1"><span class="requerido">*</span>Clabe interbancaria:</label>
	<s:textfield id="cuenta" name="cuenta"  maxlength="18" size="30"  value="" onkeyup="validaClabe();"/>
</div>
<div class="clear"></div>
<div id="msjCuenta"></div>
<div class="clear"></div>
<div id="cconfclabe">
	<label class="left1"><span class="requerido">*</span>Confirmar clabe interbancaria:</label>
	<s:textfield id="confirmaCuenta" name="confirmaCuenta"  maxlength="18" size="30"  value="" onkeyup="validaConfClabe();"/>
</div>
<div class="clear"></div>
<div id="msjConfCta"></div>
<script>
		$(document).ready(function() {
			 $('#cuenta').bind("cut copy paste", function(e){
				 e.preventDefault();
			 });
			 
		});
</script>