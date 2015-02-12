<%@taglib uri="/struts-tags" prefix="s"%>
<center>
	<table class="center">
		<tr>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalNombreV.get(0).idCampo == 14}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionVentaGlobalNombreV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalNumeroV.get(0).idCampo == 15}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionVentaGlobalNumeroV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalFechaV.get(0).idCampo == 16}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionVentaGlobalFechaV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalRfcV.get(0).idCampo == 17}">	
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionVentaGlobalRfcV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalImporteV.get(0).idCampo == 18}">
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionVentaGlobalImporteV.get(0).campo}"/>
				</th>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalVolumenV.get(0).idCampo == 64}">	
				<th style="text-align: center;">
					<s:property value="%{lstGruposCamposRelacionVentaGlobalVolumenV.get(0).campo}"/>
				</th>
			</s:if>
		</tr>
		<tr>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalNombreV.get(0).idCampo == 14}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasVentaGlobalNombre" status="itStatus">
							<tr>
								<td>
									<s:textfield cssClass="caracter" id="capNombrePerFac%{#itStatus.count}" name="capNombrePerFac"  maxlength="150" size="50"  value="%{nombrePerFac}"/>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalNumeroV.get(0).idCampo == 15}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasVentaGlobalNumero" status="itStatus">
							<tr>
								<td>
									<s:textfield cssClass="folio" id="capFolioFacGlobal%{#itStatus.count}" name="capFolioFacGlobal"  maxlength="10" size="14"  value="%{folioFacGlobal}" onblur="validaFolio(this.value, %{#itStatus.count});"/>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalFechaV.get(0).idCampo == 16}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasVentaGlobalFecha" status="itStatus">
							<tr>
							<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
								<td align="justify">	
									<s:textfield id="capFechaEmisionFacGlobal%{#itStatus.count}" name="capFechaEmisionFacGlobal" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{fechaEntrada}"/>
									<img src="../images/calendar.gif" id="trgB<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
									<script type="text/javascript">
									<!--
										Calendar.setup({
											inputField     :    'capFechaEmisionFacGlobal<s:property value="%{#itStatus.count}" />',     
											ifFormat       :    "%d/%m/%Y",     
											button         :    'trgB<s:property value="%{#itStatus.count}" />',  
											align          :    "Br",           
											singleClick    :    true
											});
										//-->
									</script>
								</td>		
							</s:if>
							<s:elseif test="registrar == 1">
								<td align="justify">
									<s:textfield id="capFechaEmisionFacGlobal%{#itStatus.count}" key="capFechaEmisionFacGlobal"  value="%{fechaEntrada}" maxlength="10" size="10"  readonly="true" cssClass="dateBox" />
								</td>
							</s:elseif>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalRfcV.get(0).idCampo == 17}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasVentaGlobalRfc" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="rfc" id="capRfcFacGlobal%{#itStatus.count}" name="capRfcFacGlobal" maxlength="150" size="50"  value="%{rfcFacGlobal}" onblur="validaRfc(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalImporteV.get(0).idCampo == 18}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasVentaGlobalImporte" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="importe" id="capImpFacGlobal%{#itStatus.count}" name="capImpFacGlobal" maxlength="15" size="20"  value="%{impFacGlobal}" onblur="validaImporte(this.value, %{#itStatus.count});"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaGlobalVolumenV.get(0).idCampo == 64}">
				<td>
					<table class="center" style="width: 100%;">
						<s:iterator value="lstComprasVentaGlobalVolumen" status="itStatus">
							<tr>
								<td align="justify">
									<s:textfield cssClass="volumen" id="capVolFacGlobal%{#itStatus.count}" name="capVolFacGlobal" maxlength="15" size="20"  value="%{volFacGlobal}" onblur="validaVolumen(this.value, %{#itStatus.count});"/>
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
		</tr>
	</table>
</center>