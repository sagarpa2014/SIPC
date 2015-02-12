<%@taglib uri="/struts-tags" prefix="s"%>
<center>
	<table class="center" style="">
		<tr>
			<s:if test="%{lstGruposCamposRelacionContratoFolioV.get(0).idCampo == 21}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionContratoFolioV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionContratoImporteV.get(0).idCampo == 22}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionContratoImporteV.get(0).campo}"/>
				</th>
			</s:if>
		</tr>
		<tr>
			<s:if test="%{lstGruposCamposRelacionContratoFolioV.get(0).idCampo == 21}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasContratoFolio" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="folio" id="capFolioContrato%{#itStatus.count}" name="capFolioContrato"  maxlength="10" size="14"  value="%{folioContrato}" onblur="validaFolio(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionContratoImporteV.get(0).idCampo == 22}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasContratoImporte" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="importe" id="capImpPactado%{#itStatus.count}" name="capImpPactado" maxlength="15" size="20"  value="%{impPactado}" onblur="validaImporte(this.value, %{#itStatus.count});"/>					
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
		</tr>
	</table>
</center>