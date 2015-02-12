<%@taglib uri="/struts-tags" prefix="s"%>
<table>
	<tr>	
		<th>No.</th>		
		<s:iterator value="lstGruposCamposRelacionBodV" status="itStatus">
			<th style="text-align: center;">
				<s:property value="%{campo}"/>
			</th>			
		</s:iterator>
	</tr>		
	<tr>
		<td>
			<table class="center" style="width: 100%;" id="tCountBoletas">
				<s:iterator value="lstComprasEntradaBodBoletas" status="itStatus">
					<tr>
						<td align="center">
							<s:textfield  id="" name=""  maxlength="3" size="3"  value="%{#itStatus.count}" disabled="true"/>
						</td>
					</tr>
				</s:iterator>
			</table>
		</td>	
		<s:iterator value="lstGruposCamposRelacionBodV" status="itStatus">								
			<s:if test="%{idCampo == 11}">
				<td>	
					<table class="center" style="width: 100%;" id="tNoBoletas">
						<s:iterator value="lstComprasEntradaBodBoletas" status="itStatus">
							<tr>
								<td align="center">
									<s:textfield  id="capBolTicket%{#itStatus.count}" name="capBolTicket"  maxlength="50" size="14"  value="%{boletaTicketBascula}"  onchange="validaBodegaTicket(this.value,%{#itStatus.count});"/>
									<s:hidden id="boletaTicketBasculaAnt%{#itStatus.count}" name="" value="%{boletaTicketBascula}"/>
									<s:hidden id="errorBoletaRepetida%{#itStatus.count}" name="" value="%{0}"/>
									<div id="validaBodegaTicket<s:property value="%{#itStatus.count}"/>"></div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{idCampo == 12}">
				<td>
					<table class="center" style="width: 100%;" id="tFechaEntrada">
						<s:iterator value="lstComprasEntradaBodBoletas" status="itStatus">
							<tr>
								<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
									<td align="center">	
										<s:if test="%{fechaEntrada==null}">
											<s:textfield id="capFechaEntrada%{#itStatus.count}" name="capFechaEntrada" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{}" onchange="validaFecha(this.value, %{#itStatus.count}, 'capFechaEntrada', 'Entrada(s) a la Bodega');"/>
										</s:if>
										<s:else>
											<s:textfield id="capFechaEntrada%{#itStatus.count}" name="capFechaEntrada" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{fechaEntrada})}" onchange="validaFecha(this.value, %{#itStatus.count}, 'capFechaEntrada', 'Entrada(s) a la Bodega');"/>
										</s:else>											
										<img width="16px" src="../images/calendar.gif" id="trgA<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
										<script type="text/javascript">
										<!--
											Calendar.setup({
												inputField     :    'capFechaEntrada<s:property value="%{#itStatus.count}" />',     
												ifFormat       :    "%d/%m/%Y",     
												button         :    'trgA<s:property value="%{#itStatus.count}" />',  
												align          :    "Br",           
												singleClick    :    true
												});
											//-->
										</script>
									</td>		
								</s:if>
								<s:elseif test="registrar == 1">
									<td align="center">
										<s:textfield id="selectedFecha%{#itStatus.count}" key="selectedFecha"  value="%{getText('fecha1',{fechaEntrada})}" maxlength="10" size="10"  readonly="true" cssClass="dateBox" />
									</td>
								</s:elseif>
							</tr>
						</s:iterator>
					</table>
				</td>
			</s:if>
			<s:if test="%{idCampo == 63}">
				<td>
					<table class="center" style="width: 100%;" id="tVolBolTicket">
						<s:iterator value="lstComprasEntradaBodBoletas" status="itStatus">
							<tr>
								<td align="center">
									<s:if test="%{volBolTicket != null }">
										<s:textfield cssClass="cantidad" id="capVolBolTicket%{#itStatus.count}" name="capVolBolTicket" maxlength="15" size="20"   value="%{getText('volumenSinComas',{volBolTicket})}" onchange="validaVolumenBolTicket(this.value, %{#itStatus.count});" />
									</s:if>
									<s:else>
										<s:textfield cssClass="cantidad" id="capVolBolTicket%{#itStatus.count}" name="capVolBolTicket" maxlength="15" size="20"  value="%{}" onchange="validaVolumenBolTicket(this.value, %{#itStatus.count});" />
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
		<s:set name="totalBolTicket"  value="%{0}"></s:set>
		<td>&nbsp;</td>
		<s:iterator value="lstGruposCamposRelacionBodV" status="itStatus">
			<td style="text-align: center;">
				<s:if test="%{lstGruposCamposRelacionBodV.get(#itStatus.index+1).idCampo == 63 && #totalBolTicket != 1}">
					<s:set name="totalBolTicket"  value="%{1}"></s:set>
					<font class="arial12bold">Totales:</font>
				</s:if>
				<s:elseif test="%{idCampo == 63}">
					<s:if test="totalVolumenBolTicket!=null">
						<s:textfield  cssClass="boxTotales" id="totalesVolumenBolTicket"  disabled="true" value="%{getText('volumen',{totalVolumenBolTicket})}" size="20"/>
					</s:if>
					<s:else>
						<s:textfield  cssClass="boxTotales" id="totalesVolumenBolTicket" disabled="true" value=""/>
					</s:else>	
				</s:elseif>
				<s:else>
					&nbsp;
				</s:else>					
			</td>			
		</s:iterator>
	</tr>
</table>

