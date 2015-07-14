<%@taglib uri="/struts-tags" prefix="s"%>
<center>
	<table id="tablaBodVol" class="clean">
		<s:if test="%{numCamposBodVolumen > 0}">	
			<tr>
				<th>No.</th>
				<th>Bodega</th>
				<th>Volumen</th>
				<th>Destino Internacional</th>
				<th>Destino</th>
			</tr>
			<s:iterator value="lstAuditorVolumenSolPago"   status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td id="contenedorBodega<s:property value="%{#itStatus.count}"/>">	
						<s:select id="idBodega%{#itStatus.count}" name="capBodega[%{#itStatus.count}]" list="lstBodegas" listKey="claveBodega" listValue="%{claveBodega}" headerKey="-1" headerValue="--Seleccione--" value="%{claveBodega}"/>
					</td>
					<td>
					<s:if test="volumenAuditor!=null">
							<s:textfield id="volumen%{#itStatus.count}" name="capVolumen[%{#itStatus.count}]" maxlength="15" size="20"  cssClass="cantidad"  value="%{getText('volumenSinComas',{volumenAuditor})}"/>
					</s:if>
					<s:else>
						<s:textfield id="volumen%{#itStatus.count}" name="capVolumen[%{#itStatus.count}]" maxlength="15" size="20"  cssClass="cantidad"/>
					</s:else>
					
					</td>
					<td id="contenedorChkDestino<s:property value="%{#itStatus.count}"/>" align="center">
						<s:checkbox id="idChkDestino%{#itStatus.count}"  name="capChkDestino[%{#itStatus.count}]"  fieldValue="true"  onclick="consigueDestinoAuditorSolPago(%{#itStatus.count});"  value="%{internacional}" />
<!-- 						<input id="idChkDestino%{#itStatus.count}" name="capChkDestino[%{#itStatus.count}]" value="" type="checkbox"  class="ck" /> --> 
					</td>
					<td id="contenedorDestino<s:property value="%{#itStatus.count}"/>">
						<s:if test="%{registrar != 0}">
							<s:if test="%{internacional == true}">
								<s:select id="idDestino%{#itStatus.count}" style="width:200px" name="capDestino[%{#itStatus.count}]" list="lstDestinoInternacional" listKey="idPais" listValue="%{pais}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{idPais}"/>
							</s:if>
							<s:else>
								<s:select id="idDestino%{#itStatus.count}" style="width:200px" name="capDestino[%{#itStatus.count}]" list="lstDestinoNacional" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{idEstado}"/>
							</s:else>
						</s:if>
						<s:else>
							<s:select id="idDestino%{#itStatus.count}" style="width:200px" name="capDestino[%{#itStatus.count}]" list="lstDestinoNacional" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{idEstado}"/>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</s:if>
	</table>
</center>	

