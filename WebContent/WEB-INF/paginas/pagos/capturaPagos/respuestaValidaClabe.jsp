<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="errorClabeInvalida!=0">
	<s:hidden name="errorClabeInvalida" id="errorClabeInvalida" value="%{errorClabeInvalida}"/>
	<div class="msjError"><s:property value="%{msjError}"/></div>	
</s:if>
<s:else>
	<div class="correcto"><s:property value="%{msjOk}"/></div>
	<s:hidden name="tmpCuenta" id="tmpCuenta" value="%{clabe}"/>
	<script>
		$(document).ready(function() {
			$("#cconfclabe").css("display", "inline");
			$('#cuenta').val('******************');
			$('#confirmaCuenta').bind("cut copy paste", function(e){
				 e.preventDefault();
			 });
			//$("#cclabe").css("display", "none");
		});
	</script>	
</s:else>
