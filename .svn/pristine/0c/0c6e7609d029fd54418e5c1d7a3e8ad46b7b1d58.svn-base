<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstRecuperaRepLeg.size() > 0">
	<center>
		<table class="clean">
			<tr>
				<th >Nombre </th>
				<th >Representante Responsable</th>
			</tr>
			<s:iterator value="lstRecuperaRepLeg" status="itStatus">
				<tr>
					<td><s:select name="capRepresentante" id="capRepresentante%{#itStatus.count}" list="lstRepLegalV" listKey="idRepresentante"  listValue="nombre+' ('+rfc+')'" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="" value="%{idRepresentante}"/></td>
					<td align="center">
						<s:if test="editar==0">
							<input id="capEstatus<s:property value="%{#itStatus.count}"/>" name="capEstatus" value="<s:property value="%{#itStatus.count-1}"/>" type="checkbox"  class="ck"/>
						</s:if>
						<s:elseif test="editar==3">	
							<s:hidden id="capEstatusTemp%{#itStatus.count}" name="" value="%{estatusRepresentante}"/>						
							<input id="capEstatus<s:property value="%{#itStatus.count}"/>" name="capEstatus" value="<s:property value="%{#itStatus.count-1}"/>" type="checkbox"  class="ck"/>
						</s:elseif>
					</td>
				</tr>
			</s:iterator>
		</table>
	</center>
</s:if>

<s:if test="editar==3">
	<script type="text/javascript">
	<!--
		$(document).ready(function() {
			var numCampos = $('#numCampos').val();
			var i=0;			
			for (i=1;i<parseInt(numCampos)+1;i++){
				var capEstatusTemp = $('#capEstatusTemp'+i).val();
				console.log("capEstatusTemp: "+capEstatusTemp);
				if(capEstatusTemp == 1){
					$("#capEstatus"+i).attr("checked", "checked");
				}				
			}
			
			
		});	
	//-->
	</script>
</s:if>
