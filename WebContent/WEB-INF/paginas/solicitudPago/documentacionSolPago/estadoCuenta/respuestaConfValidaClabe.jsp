<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="errorConfClabeInvalida!=0">
	<s:hidden name="errorConfClabeInvalida" id="errorConfClabeInvalida" value="%{errorConfClabeInvalida}"/>
	<div class="msjError"><s:property value="%{msjError}"/></div>	
</s:if>
<s:else>
	<div class="correcto"><s:property value="%{msjOk}"/></div>
	<script>
		$(document).ready(function() {
			var confirmaCuenta = $('#confirmaCuenta').val();
			$('#cuenta').val(confirmaCuenta);
		});
	</script>
	<s:include value="/WEB-INF/paginas/pagos/capturaPagos/datosPlazaBancaria.jsp" />
</s:else>



