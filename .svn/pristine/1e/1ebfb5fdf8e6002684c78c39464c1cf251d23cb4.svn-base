<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstAuditorSolPago.size() > 0">
<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean">
				<th class="clean">N&uacute;mero de Auditor</th>
				<th class="clean">Auditor</th>
				<th class="clean">Volumen</th>
			</tr>
			<s:iterator value="lstAuditorSolPago" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:textfield id="capNumeroAuditor%{#itStatus.count}" name="capNumeroAuditor" maxlength="15" size="20" value="%{numeroAuditor}" onchange="validarNumeroAuditor(this.value, %{#itStatus.count});"/>			
					</td>
					<td id="validarNumeroAuditor<s:property value="%{#itStatus.count}"/>">
						<s:select id="auditorV%{#itStatus.count}" name="selectedAuditorV" list="lstAuditoresExternos" listKey="numeroAuditor" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{numeroAuditor}" onchange="" disabled="true"/>
					</td>
					<td>
						<s:textfield id="v%{#itStatus.count}" name="capVolumen" maxlength="15" size="20"  cssClass="cantidad" value="%{volumenAuditor}" />
					</td>					
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>
<s:else>
	
</s:else>


<script>
$(document).ready(function() {
		$(".cVolumen").change(function(){
		        var index = $(".cVolumen").index(this);
		        var indexMasUno=index +1;
		        var v=$('#v'+indexMasUno).val();
		        var patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
		    	if(v=="" || v==0){
		    		return false;
		    	}
		    	if(!v.match(patron)){
		    		$('#dialogo_1').html('El valor del registro es inválido, se aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
		       		abrirDialogo();
		       		return false;
		    	}
		        calculaTotalVolumen(v);
		        
		}); //end change .cVolumen
});
</script>	