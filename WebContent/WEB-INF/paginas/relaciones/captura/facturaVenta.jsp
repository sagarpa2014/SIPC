<%@taglib uri="/struts-tags" prefix="s"%>
<table style="" >
		<tr>
			<s:iterator value="lstGruposCamposRelacionVentaNumeroV" status="itStatus">
				<th style="text-align: center;">
					<s:property value="%{campo}"/>
				</th>		
				<s:if test="%{#itStatus.index+1 == lstGruposCamposRelacionVentaNumeroV.size()}">
					<th>
						PRECIO POR TONELADA
					</th>
				</s:if>	
			</s:iterator>			
		</tr>
		<tr>
			<s:iterator value="lstGruposCamposRelacionVentaNumeroV" status="itStatus">
				<s:if test="%{idCampo == 15}">
					<td>
						<table class="center" style="width: 100%;" id="tNumFacVenta">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
									<td>
										<s:textfield id="capFolioFac%{#itStatus.count}" name="capFolioFac"  maxlength="36" size="20"  value="%{folioFac}"  onchange="validaFoliosFactura(%{#itStatus.count});"/>
										<s:if test="%{registrar == 2}">
											<s:if test="%{folioFac != null}">
												<s:hidden id="folioFacAnt%{#itStatus.count}" name="" value="%{folioFac}"/>
											</s:if>
										</s:if>										
										<div id="validaFoliosFactura<s:property value="%{#itStatus.count}"/>"></div>
										<s:hidden id="errorFacturasRepetidos%{#itStatus.count}" name="" value="%{0}"/>					
										<s:hidden id="errorFacturaByProductor%{#itStatus.count}" name="" value="%{0}"/>					
									</td>
										
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
				<s:if test="%{idCampo == 16}">
					<td>
						<table class="center" style="width: 100%;" id="tFechaEmisionFac">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
								<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
									<td align="center">
										<s:if test="%{fechaEmisionFac==null}">
											<s:textfield id="capFechaEmisionFac%{#itStatus.count}" name="capFechaEmisionFac" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{}" onchange="validaFecha(this.value, %{#itStatus.count}, 'capFechaEmisionFac', 'Factura de Venta del Grano');"/>
										</s:if>	
										<s:else>
											<s:textfield id="capFechaEmisionFac%{#itStatus.count}" name="capFechaEmisionFac" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{fechaEmisionFac})}" onchange="validaFecha(this.value, %{#itStatus.count}, 'capFechaEmisionFac', 'Factura de Venta del Grano');"/>
										</s:else>
										<img width="16px" src="../images/calendar.gif" id="trgC<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
										<script type="text/javascript">
											Calendar.setup({
												inputField     :    'capFechaEmisionFac<s:property value="%{#itStatus.count}" />',     
												ifFormat       :    "%d/%m/%Y",     
												button         :    'trgC<s:property value="%{#itStatus.count}" />',  
												align          :    "Br",           
												singleClick    :    true
												});
										</script>
									</td>		
								</s:if>
								<s:elseif test="registrar == 1">
									<td align="center">
										<s:textfield id="capFechaEmisionFac%{#itStatus.count}" key="capFechaEmisionFac" value="%{getText('fecha1',{fechaEmisionFac})}" maxlength="10" size="10"  readonly="true" cssClass="dateBox" />
									</td>
								</s:elseif>
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
				<s:if test="%{idCampo == 17}">
					<td>
						<table class="center" style="width: 100%;" id="tRfcFacVenta">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
									<td align="center">
										<s:textfield cssClass="rfc" id="capRfcFac%{#itStatus.count}" name="capRfcFac" maxlength="15" size="15"  value="%{#rfc1}" onblur="validaRfc(this.value, %{#itStatus.count});" disabled="true"/>					
									</td>				
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
				<s:if test="%{idCampo == 19}">
					<td>
						<table class="center" style="width: 100%;" id="tCapVolSolFac">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
									<td align="center">
										<s:if test="%{volSolFac != null }">
											<s:textfield cssClass="volumen" id="capVolSolFac%{#itStatus.count}" name="capVolSolFac" maxlength="14" size="20"  value="%{getText('volumenSinComas',{volSolFac})}"  onblur="validarVolumen(this.value, 'capVolSolFac%{#itStatus.count}', 1);" onkeyup="calculaVolumenFacturas();"/>
										</s:if>
										<s:else>
											<s:textfield cssClass="volumen" id="capVolSolFac%{#itStatus.count}" name="capVolSolFac" maxlength="14" size="20"  value="%{}" onblur="validarVolumen(this.value, 'capVolSolFac%{#itStatus.count}', 1);"  onkeyup="calculaVolumenFacturas();"/>
										</s:else>
									</td>				
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
				<s:if test="%{idCampo == 65}">
					<td>
						<table class="center" style="width: 100%;" id="tCapVolTotalFac">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
									<td align="center">
										<s:if test="%{volTotalFac != null }">
											<s:textfield cssClass="volumen" id="capVolTotalFac%{#itStatus.count}" name="capVolTotalFac" maxlength="14" size="20"  value="%{getText('volumenSinComas',{volTotalFac})}"  onblur="validarVolumen(this.value, 'capVolTotalFac%{#itStatus.count}', 1);" onkeyup="calculaTotalVolumenFacturas();"/>
										</s:if>
										<s:else>
											<s:textfield cssClass="volumen" id="capVolTotalFac%{#itStatus.count}" name="capVolTotalFac" maxlength="14" size="20"  value="%{}" onblur="validarVolumen(this.value, 'capVolTotalFac%{#itStatus.count}', 1);" onkeyup="calculaTotalVolumenFacturas();"/>
										</s:else>
									</td>				
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
				<s:if test="%{idCampo == 20}">
					<td>
						<table class="center" style="width: 100%;" id="tCapImpSolFac">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
									<td align="center">
										<s:if test="%{impSolFac != null }">	
											<s:textfield cssClass="importe" id="capImpSolFac%{#itStatus.count}" name="capImpSolFac" maxlength="14" size="20"  value="%{getText('importeSinComas',{impSolFac})}"  onblur="validarImporte(this.value, 'capImpSolFac%{#itStatus.count}', 1);" onkeyup="calculaTotalImporteFacturas(this.value, %{#itStatus.count});"/>
										</s:if>
										<s:else>
											<s:textfield cssClass="importe" id="capImpSolFac%{#itStatus.count}" name="capImpSolFac" maxlength="14" size="20"  value="%{}" onblur="validarImporte(this.value, 'capImpSolFac%{#itStatus.count}', 1);"    onkeyup="calculaTotalImporteFacturas(this.value, %{#itStatus.count});"/>
										</s:else>
									</td>				
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
				<s:if test="%{#itStatus.index+1 == lstGruposCamposRelacionVentaNumeroV.size()}">
					<td>
						<table class="center" style="width: 100%;" id="tprecioXTonelada">
							<s:iterator value="lstComprasFacVentaGrano" status="itStatus">
								<tr>
									<td>
										<s:textfield cssClass="importe" id="precioXTonelada%{#itStatus.count}" name="" maxlength="14" size="20"  value="%{}" disabled="true"/>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</s:if>
			</s:iterator>
		</tr>
		<tr>	
			<s:set name="totalFacturaVenta"  value="%{0}"></s:set>
			<s:iterator value="lstGruposCamposRelacionVentaNumeroV" status="itStatus">
				<td style="text-align: center;">
					<s:if test="%{(lstGruposCamposRelacionVentaNumeroV.get(#itStatus.index+1).idCampo == 19 
					|| lstGruposCamposRelacionVentaNumeroV.get(#itStatus.index+1).idCampo == 65 
					|| lstGruposCamposRelacionVentaNumeroV.get(#itStatus.index+1).idCampo == 20) && #totalFacturaVenta != 1}">
						<s:set name="totalFacturaVenta"  value="%{1}"></s:set>
						<font class="arial12bold">Totales:</font>
					</s:if>
					<s:elseif test="%{idCampo == 19}">
						<s:if test="totalFacVentaVolSolicitado!=null">
							<s:textfield id="totalesVolumenSolicitado" cssClass="boxTotales" disabled="true" value="%{getText('volumen',{totalFacVentaVolSolicitado})}" size="20"/>
						</s:if>
						<s:else>
							<s:textfield id="totalesVolumenSolicitado" cssClass="boxTotales" disabled="true"/>
						</s:else>	
					</s:elseif>
					<s:elseif  test="%{idCampo == 65}">
						<s:if test="totalFacVentaVolFacturado!=null">
							<s:textfield id="totalesVolumenFacturado" cssClass="boxTotales" disabled="true" value="%{getText('volumen',{totalFacVentaVolFacturado})}" size="20"/>
						</s:if>
						<s:else>
							<s:textfield id="totalesVolumenFacturado" cssClass="boxTotales" disabled="true"/>
						</s:else>
					</s:elseif>
					<s:elseif  test="%{idCampo == 20}">
						<s:if test="totalFacVentaImpFacturas!=null">
							<s:textfield id="totalesImporteFacturas" cssClass="boxTotales" disabled="true" value="%{getText('importe',{totalFacVentaImpFacturas})}" size="20"/>
						</s:if>
						<s:else>
							<s:textfield id="totalesImporteFacturas" cssClass="boxTotales" disabled="true"/>
						</s:else>
					</s:elseif>
					<s:else>
						&nbsp;
					</s:else>					
				</td>			
			</s:iterator>
		</tr>
</table>
<s:if test="%{registrar == 1  || registrar == 2}">
	<script>
		$(document).ready(function(){
			setValPrecioXTonelada();
		});	 
	</script>
</s:if>

