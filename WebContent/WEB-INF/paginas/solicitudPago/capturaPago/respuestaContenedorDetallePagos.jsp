<%@taglib uri="/struts-tags" prefix="s"%>
<table class="clean" >
	<tr>
		<th >No.</th>
		<th >Estado</th>
		<th >Cultivo</th>
		<th >Variedad</th>
		<th >Bodega</th>
		<s:if test="criterioPago==1 || criterioPago==3">
			<th >Volumen Autorizado</th>
			<th >Volumen Apoyado</th>
			<th >Cuota Apoyo</th>
			<th >Volumen a Apoyar</th>
			<s:if test="criterioPago==3">
				<th>Etapa</th>
			</s:if>
		</s:if>
		<s:else>
			<th >Importe Autorizado</th>
			<th >Importe Apoyado</th>
			<th >Importe a Apoyar</th>
			<th >Etapa</th>
		</s:else>
	</tr>	
	<s:hidden id="numRegistroDetallePago" value="%{lstPagosDetalleCAV.size()}"/>
	<s:iterator value="lstPagosDetalleCAV" var="resultado" status="itStatus">
		<tr>
			<td><s:property value="%{#itStatus.count}"/></td>
			<td>
				<s:textfield disabled="true" maxlength="50" size="20" value="%{estado}"/>
			</td>
			<td>
				<s:textfield disabled="true" maxlength="50" size="20" value="%{cultivo}"/>
			</td>
			<td>
				<s:textfield disabled="true" maxlength="50" size="30" value="%{variedad}"/>
			</td>
			<td>
				<s:textfield disabled="true" maxlength="50" size="30" value="%{bodega}"/>
			</td>						
			<s:if test="idCriterioPago==1 || idCriterioPago==3">
				<td align="CENTER">
					<s:textfield disabled="true" id="volumenAut%{#itStatus.count}" name="volumenesAut" maxlength="20" size="15" cssClass="cantidad" value="%{getText('volumen',{volumenAutorizado})}"/>
					<s:hidden id="volumenesAutTemp%{#itStatus.count}" value="%{#resultado.volumen}"/>								
				</td>
				<td align="CENTER">
					<s:textfield disabled="true" id="volumenApo%{#itStatus.count}" name="volumenesApo" maxlength="20" size="15" cssClass="cantidad" value="%{getText('volumen',{volumenApoyado})}"/>
					<s:hidden id="impCal%{#itStatus.count}" value="{}"/>
				</td>
				<td align="CENTER">
					<s:if test="%{cuota != null && cuota != 0}">
						$<s:textfield disabled="true" id="cuotaApoyo%{#itStatus.count}" name="cuotasApoyo[%{idAsiganacionCA}]" maxlength="15" size="10" cssClass="cantidad" value="%{getText('importe',{cuota})}"/>
					</s:if>
					<s:else>
						$<s:textfield disabled="true" id="cuotaApoyo%{#itStatus.count}" name="cuotasApoyo[%{idAsiganacionCA}]" maxlength="15" size="10" cssClass="cantidad" value="%{}"/>
					</s:else>
				</td>
				<td>
				
<%-- 					<s:if test='%{fianza=="NO"}'>						 --%>
						<s:if test="%{volumen != null && volumen != 0}"> 
							<s:textfield  id="volumenApoyar%{#itStatus.count}" name="capVolumen[%{idAsiganacionCA}]" maxlength="20" size="15"  cssClass="cantidad" value="%{getText('volumenSinComas',{volumen})}"  />
						</s:if>
						<s:else>
							<s:textfield id="volumenApoyar%{#itStatus.count}" name="capVolumen[%{idAsiganacionCA}]" maxlength="20" size="15"  cssClass="cantidad" value="%{}" />
						</s:else>					
<%-- 					</s:if> --%>
<%-- 					<s:else> --%>
<%-- 						<s:if test="%{volumen != null && volumen != 0}">  --%>
<%-- 							<s:textfield id="volumenApoyar%{#itStatus.count}" name="capVolumen[%{idAsiganacionCA}]" maxlength="20" size="15"  cssClass="cantidad" value="%{getText('volumenSinComas',{volumen})}"  /> --%>
<%-- 						</s:if> --%>
<%-- 						<s:else> --%>
<%-- 							<s:textfield  id="volumenApoyar%{#itStatus.count}" name="capVolumen[%{idAsiganacionCA}]" maxlength="20" size="15"  cssClass="cantidad" value="%{}" /> --%>
<%-- 						</s:else>					 --%>
<%-- 					</s:else> --%>
				</td>
				<s:if test="idCriterioPago==3">
					<td class="cEtapa">
						<s:select id="etapa%{#itStatus.count}" name="capEtapa[%{idAsiganacionCA}]" list="lstEtapasCuotaIniEsquema" listKey="etapa" listValue="%{etapa}" headerKey="-1" headerValue="Seleccione una opción" tabindex="0" value="%{etapa}" onchange="obtenCuotaEtapa(this.value, %{#itStatus.count});"/>
					</td>						
				</s:if>
			</s:if>
			<s:else>
				<td align="CENTER">
					$<s:textfield disabled="true" id="importeAut%{#itStatus.count}" name="ImportesAut" maxlength="20" size="15" cssClass="cantidad" value="%{getText('importe',{#resultado.importe})}"/>
				</td>
				<td align="CENTER">
					$<s:textfield disabled="true" id="importeApo%{#itStatus.count}" name="ImportesApo" maxlength="20" size="15" cssClass="cantidad" value="%{getText('importe',{#resultado.importeApoyado})}"/>
				</td>
				<td>$<s:textfield id="importeApoyar%{#itStatus.count}" name="capImporte[%{idAsiganacionCA}]" maxlength="20" size="15"  cssClass="cantidad" value="%{}" /></td>
				<td class="cEtapa">
					<s:select id="etapa%{#itStatus.count}" name="capEtapa[%{idAsiganacionCA}]"  headerKey="-1" onchange=""
						headerValue="Seleccione una opción"								
						list="#{'I':'I', 'II':'II', 'III':'III','IV':'IV'}" />
				</td>
			</s:else>						
		</tr>	
	</s:iterator>
</table>