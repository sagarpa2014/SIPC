<%@taglib uri="/struts-tags" prefix="s"%>

<s:if test="lstEtapaIniEsquemaV.size() > 0">
<div class="clear"></div>
	<center>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Etapa</th>
				<s:if test="idCriterioPago==2" >
					<th class="clean">Monto</th>
				</s:if>
				<s:elseif test="idCriterioPago==3">
					<th class="clean">Cuota</th>
				</s:elseif>
			</tr>
			<s:iterator value="lstEtapaIniEsquemaV" var="resultado"  status="itStatus">
				<tr>
					<td><s:property value="%{#itStatus.count}"/></td>
					<td>
						<s:textfield id="etapa%{#itStatus.count}" name="selectEtapa" maxlength="15" size="20"  cssClass="textCentrado" value="%{etapa}" disabled="true"/>
					</td>
					<td>
						<s:if test ="monto!=null">
							<s:textfield id="cuotaMonto%{#itStatus.count}" name="selectCM" maxlength="15" size="20"  cssClass="cantidad" value="%{getText('importeSinComas',{monto})}" />
						</s:if>
						<s:else>
							<s:textfield id="cuotaMonto%{#itStatus.count}" name="selectCM" maxlength="15" size="20"  cssClass="cantidad" value="%{}" />
						</s:else>
						<s:if test="idCriterioPago==3">
							$/<s:property value="%{abreviaturaUM}"/>					
						</s:if>		
					</td>
					
								
				</tr>	
			</s:iterator>
		</table>
	</center>
</s:if>