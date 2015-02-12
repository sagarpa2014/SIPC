<%@taglib uri="/struts-tags" prefix="s"%>
<table style="">
	<tr>
		<s:iterator value="lstGruposCamposRelacionPagoSinAXC" status="itStatus">	
			<s:if test="%{idCampo != 67 && idCampo != 68}">
				<th style="text-align: center;">
					<s:property value="%{campo}"/>
				</th>
			</s:if>
		</s:iterator>			
	</tr>
	<tr>
		<s:iterator value="lstGruposCamposRelacionPagoSinAXC" status="itStatus">
			<s:if test="%{idCampo == 27}">
				<td>
					<table class="center" style="width: 100%;" id="tCapIdTipoDocPago">
						<s:iterator value="lstComprasPagoSinAXC" status="itStatus">
							<tr>
								<td align="center">
									<s:select cssClass="lstTipoDocumentoPagos" id="capIdTipoDocPago%{#itStatus.count}" name="capIdTipoDocPago" list="lstTipoDocumentoPagos" listKey="idTipoDocumentoPago" listValue="%{tipoPago}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idTipoDocPago}" onchange="validaTipoDocPago(%{#itStatus.count});"/>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{idCampo == 29}" >
				<td>
					<table class="center" style="width: 100%;" id="tCapBancoIdSin">
						<s:iterator value="lstComprasPagoSinAXC" status="itStatus">
							<tr>
								<td align="center">
									<s:if test="%{idTipoDocPago == 3}" >
										<s:select cssClass="lstBancos" id="capBancoIdSin%{#itStatus.count}" name="capBancoIdSin" list="lstBancos" listKey="bancoId" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{bancoId}"  disabled="true" />
									</s:if>
									<s:else>
										<s:select cssClass="lstBancos" id="capBancoIdSin%{#itStatus.count}" name="capBancoIdSin" list="lstBancos" listKey="bancoId" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{bancoId}" />
									</s:else>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{idCampo == 16}">
				<td >
					<table class="center" id="tCapFechaDocPago">
						<s:iterator value="lstComprasPagoSinAXC" status="itStatus">
							<tr>
							<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
								<td align="center"  style="text-align: center; width: 20%;">	
									<s:if test="%{fechaDocPago==null}">
										<s:textfield id="capFechaDocPago%{#itStatus.count}" name="capFechaDocPago" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{}" onchange="validaFecha(this.value, %{#itStatus.count}, 'capFechaDocPago', 'Pago al Productor sin AXC');"/>
									</s:if>
									<s:else>
										<s:textfield id="capFechaDocPago%{#itStatus.count}" name="capFechaDocPago" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{fechaDocPago})}" onchange="validaFecha(this.value, %{#itStatus.count}, 'capFechaDocPago', 'Pago al Productor sin AXC');"/>
									</s:else>
									<img width="16px" src="../images/calendar.gif" id="trgD<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
									<script type="text/javascript">									
										Calendar.setup({
											inputField     :    'capFechaDocPago<s:property value="%{#itStatus.count}" />',     
											ifFormat       :    "%d/%m/%Y",     
											button         :    'trgD<s:property value="%{#itStatus.count}" />',  
											align          :    "Br",           
											singleClick    :    true
											});
									</script>
								</td>		
							</s:if>
							<s:elseif test="registrar == 1">
								<td align="center" class="fecha">
									<s:textfield id="capFechaDocPago%{#itStatus.count}" key="capFechaDocPago" value="%{getText('fecha1',{fechaDocPago})}" maxlength="10" size="10"  readonly="true" cssClass="dateBox" />
								</td>
							</s:elseif>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{idCampo == 21}">
				<td>
					<table class="center" style="width: 100%;" id="tCapFolioDocPago">
						<s:iterator value="lstComprasPagoSinAXC" status="itStatus">
							<tr>
								<td align="center">
									<s:textfield cssClass="folio" id="capFolioDocPago%{#itStatus.count}" name="capFolioDocPago"  maxlength="10" size="14"  value="%{folioDocPago}" onblur="validaFolioDocPago(this.value, 'capFolioDocPago%{#itStatus.count}');"/>					
								</td>				
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{idCampo == 66}">
				<td>
					<table class="center" style="width: 100%;" id="tCapImpDocPago">
						<s:iterator value="lstComprasPagoSinAXC" status="itStatus">
							<tr>
								<td align="center">
									<s:if test="%{impDocPago != null }">	
										<s:textfield cssClass="importe" id="capImpDocPago%{#itStatus.count}" name="capImpDocPago" maxlength="15" size="20" value="%{getText('importeSinComas',{impDocPago})}"  onblur="validarImporte(this.value, 'capImpDocPago%{#itStatus.count}', 1);" onchange="calculaTotalImportePagos();"/>
									</s:if>
									<s:else>
										<s:textfield cssClass="importe" id="capImpDocPago%{#itStatus.count}" name="capImpDocPago" maxlength="15" size="20"  value="%{}" onblur="validarImporte(this.value, 'capImpDocPago%{#itStatus.count}', 1);" onchange="calculaTotalImportePagos();"/>
									</s:else>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
		</s:iterator>
	</tr>
	<tr>	
		<s:set name="totalPagoSinAXC"  value="%{0}"></s:set>
		<s:iterator value="lstGruposCamposRelacionPagoSinAXC" status="itStatus">
			<s:if test="%{idCampo != 67 && idCampo != 68}">
				<td style="text-align: center;">
					<s:if test="%{lstGruposCamposRelacionPagoSinAXC.get(#itStatus.index+1).idCampo == 66 && #totalPagoSinAXC != 1}">
						<s:set name="totalPagoSinAXC"  value="%{1}"></s:set>
						<font class="arial12bold">Totales:</font>
					</s:if>	
					<s:elseif test="%{idCampo == 66}">
						<s:if test="totalPagoSinAXCImpDocPagos!=null">
							<s:textfield id="totalesImportePagos" cssClass="boxTotales" disabled="true" value="%{getText('importe',{totalPagoSinAXCImpDocPagos})}" size="20"/>
						</s:if>
						<s:else>
							<s:textfield id="totalesImportePagos" cssClass="boxTotales" disabled="true"/>
						</s:else>	
					</s:elseif>
					<s:else>
						&nbsp;
					</s:else>					
				</td>	
			</s:if>		
		</s:iterator>
	</tr>
</table>

