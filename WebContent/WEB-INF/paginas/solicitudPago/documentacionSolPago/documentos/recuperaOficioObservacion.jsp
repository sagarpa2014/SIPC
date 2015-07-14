<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstOficioObsSolicitudPago.size()>0">
	<s:if test="estatusCA==5 || estatusCA == 9"><div class="borderBottom" style="text-align:center"><h1>Descarga de Oficio de Observaciones</h1></div><br></s:if>
	<table class="clean">
		<s:iterator value="lstOficioObsSolicitudPago" var="resultado"  status="itStatus">
			<s:if test="rutaDocObs!=null">
				<tr>
					<td>
						<label class="left1"><span class="norequerido">*</span>Oficio de Observaci&oacute;n&nbsp;<s:if test="alcance==true">(Alcance)</s:if></label>
					</td>
					<td>
						<s:if test="sustituirArchivo == true">
							<s:file name="docObsA"  id="resp%{#itStatus.count}" onchange="llenarArrayOficioObs(%{idOficioObsSolPago},  %{#itStatus.count});"/>
							<s:hidden id="idOficioObs%{#itStatus.count}" name="idOficioObsSolPagoA" value="%{}" />
						</s:if>
						<s:else>
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocObs}"/>" title="Archivo Oficio Observaci&oacute;n">
								<s:property value="%{noOficioObs}"/>
							</a>
						</s:else>
					</td>
				</tr>
			</s:if>
		</s:iterator>
	</table>
</s:if>