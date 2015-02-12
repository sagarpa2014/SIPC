<%@taglib uri="/struts-tags" prefix="s"%>
<center>
	<table class="center" style="">
		<tr>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompV.get(0).idCampo == 23}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionPagoAXCImporteCompV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompProdV.get(0).idCampo == 24}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionPagoAXCImporteCompProdV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompPacV.get(0).idCampo == 25}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionPagoAXCImporteCompPacV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompPacProdV.get(0).idCampo == 26}">	
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionPagoAXCImporteCompPacProdV.get(0).campo}"/>
				</th>
			</s:if>
		</tr>
		<tr>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompV.get(0).idCampo == 23}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasContratoImporteComp" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="importe" id="capImpCompTon%{#itStatus.count}" name="capImpCompTon" maxlength="15" size="20"  value="%{impCompTon}" onblur="validaImporte(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompProdV.get(0).idCampo == 24}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasContratoImporteCompProd" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="importe" id="capImpCompTonProd%{#itStatus.count}" name="capImpCompTonProd" maxlength="15" size="20"  value="%{impCompTonProd}" onblur="validaImporte(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompPacV.get(0).idCampo == 25}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasContratoImporteCompPac" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="importe" id="capImpPacAxc%{#itStatus.count}" name="capImpPacAxc" maxlength="15" size="20"  value="%{impPacAxc}" onblur="validaImporte(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoAXCImporteCompPacProdV.get(0).idCampo == 26}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasContratoImporteCompPacProd" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="importe" id="capImpPacAxcProd%{#itStatus.count}" name="capImpPacAxcProd" maxlength="15" size="20"  value="%{impPacAxcProd}" onblur="validaImporte(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
		</tr>
	</table>
</center>