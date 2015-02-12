<%@taglib uri="/struts-tags" prefix="s"%>
<s:hidden id="numPredios" name="numPredios" value="%{numPredios}"/>
<div id="errorNoExistenPrediosR" class="elementoOculto"><s:property value="%{errorNoExistenPredios}"/></div>
<s:if test="errorNoExistenPredios == 1">
	<div class="msjError1">No existen predios asociados al Productor/Estado</div>
</s:if>
<s:if test="registrar != 1 && lstComprasPredio.size() > 0">	
	<table class="clean" >
		<tr>
			<s:if test="estatusProductor != 2">
				<td><span id="guardarPredios" class="tipoBoton" onclick="setValidarCamposByApartado(6);">Guardar</span></td>
			</s:if>
		</tr>
	</table>
</s:if>
<br>
<s:if test="lstComprasPredio.size() > 0">
<table class="center">
		<tr>
			<th style="text-align: center;">PREDIO</th>
			<th>SECUENCIAL</th>
			<th>PREDIO ALTERNO</th>
			<th>MODALIDAD</th>
			<th>SUPERFICIE A APOYAR</th>
		</tr>
		<tbody>
			<s:iterator value="lstComprasPredio" status="itStatus">
				<tr>							
					<td>
						<s:textfield cssClass="folio" id="capPredio%{#itStatus.count}" name="capPredio" maxlength="15" size="20"  value="%{folioPredio}" onchange="validaPredios(%{#itStatus.count});" disabled="true"/>
						<div id="validaPredio<s:property value="%{#itStatus.count}"/>"></div>
						<s:hidden id="errorPrediosRepetidos1%{#itStatus.count}" name="" value="%{0}"/>					
						<s:hidden id="errorPrediosRepetidos2%{#itStatus.count}" name="" value="%{0}"/>	
						<s:hidden id="capPredioAnt%{#itStatus.count}" name="" value="%{folioPredio}"/>				
						<s:hidden id="capPredioSec%{#itStatus.count}" name="" value="%{folioPredioSec}"/>				
						<s:hidden id="idPredio%{#itStatus.count}" name="" value="%{idPredio}"/>				
					</td>
					<td>
						<s:textfield id="capPredioSec%{#itStatus.count}" name="capPredioSec" maxlength="15" size="20"  value="%{folioPredioSec}" onchange="validaPredios(%{#itStatus.count});" disabled="true"/>
					</td>
					<td>
						<s:textfield id="capPredioAlterno%{#itStatus.count}" name="capPredioAlterno%{#itStatus.count}" maxlength="15" size="20"  value="%{predioAlterno}" onchange="" disabled="true"/>
					</td>
					<td>
						<s:if test='%{modalidad=="R"}'>
							<s:textfield id="" name="" maxlength="14" size="20"  value="RIEGO"  disabled="true"/>
						</s:if>
						<s:else>
							<s:textfield id="" name="" maxlength="14" size="20"  value="TEMPORAL"  disabled="true"/>
						</s:else>						
					</td>
					<td>
						<s:if test="%{superficieApoyar!=null}">
							<s:if test='%{predioAlterno!=null && predioAlterno!=""}'>
								<s:textfield id="superficiePredio%{#itStatus.count}" name="capSuperficiePredio['%{predioAlterno}']" maxlength="14" size="20" value="%{getText('volumenSinComas',{superficieApoyar})}" onchange="validaPredios(%{#itStatus.count});" cssClass="cantidad" />
							</s:if>
							<s:else>
								<s:set name="folioPrePredioSec" ><s:property value="%{folioPredio}"/>-<s:property value="%{folioPredioSec}"/></s:set>
								<s:textfield id="superficiePredio%{#itStatus.count}" name="capSuperficiePredio['%{#folioPrePredioSec}']" maxlength="14" size="20" value="%{getText('volumenSinComas',{superficieApoyar})}" onchange="validaPredios(%{#itStatus.count});" cssClass="cantidad" />
							</s:else>														
						</s:if>
						<s:else>
							<s:if test='%{predioAlterno!=null && predioAlterno!=""}'>
								<s:textfield id="superficiePredio%{#itStatus.count}" name="capSuperficiePredio['%{predioAlterno}']" maxlength="14" size="20"  value="%{}" onchange="validaPredios(%{#itStatus.count});" cssClass="cantidad" />
							</s:if>
							<s:else>
								<s:set name="folioPrePredioSec" ><s:property value="%{folioPredio}"/>-<s:property value="%{folioPredioSec}"/></s:set>
								<s:textfield id="superficiePredio%{#itStatus.count}" name="capSuperficiePredio['%{#folioPrePredioSec}']" maxlength="14" size="20"  value="%{}" onchange="validaPredios(%{#itStatus.count});" cssClass="cantidad" />
							</s:else>							
						</s:else>					
						<div id="validaPredioSuperficie<s:property value="%{#itStatus.count}"/>"></div>	
					</td>
				</tr>
			</s:iterator>
		</tbody>
		<tfoot>
			<tr id="totalFolioPredios">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><font class="arial12bold">Totales:</font></td>
				<td>
					<s:if test="totalesSuperficieApoyar!=null">
						<s:textfield id="totalesSuperficieApoyar" cssClass="boxTotales" disabled="true" value="%{getText('volumen',{totalesSuperficieApoyar})}" size="20"/>
					</s:if>
					<s:else>
						<s:textfield id="totalesSuperficieApoyar" cssClass="boxTotales" disabled="true"/>
					</s:else>
				</td>
			</tr>
		</tfoot>
	</table>
</s:if>