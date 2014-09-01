<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="estatusCA == 4" >
	<div class="clear"></div>
	<div class="borderBottom" style="text-align:center"><h1>Cargar Escrito de Respuesta</h1></div><br>
	<div class="clear"></div>
	<table class="clean">	
		<tr>
			<td><label class="left1"><span class="requerido">*</span>Escrito de Respuesta</label></td>
			<td><s:file name="docResp" id="docResp"/></td>				
		</tr>
		<tr>
			<td><label class="left1"><span class="requerido">*</span>Fecha Documento</label></td>
			<td>
				<s:if test="%{fechaDocResp==null}">
					<s:textfield name="fechaDocResp" maxlength="10" size="10" id="fechaDocResp" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield key="fechaDocResp" value="%{fechaDocResp}"  maxlength="10" size="10" id="fechaDocResp" readonly="true" cssClass="dateBox"/>
				</s:else>
				<img src="../images/calendar.gif" id="trgD" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1"><span class="requerido">*</span>Fecha Acuse</label>	
			</td>
			<td>
				<s:if test="%{fechaAcuseResp==null}" >
					<s:textfield name="fechaAcuseResp" maxlength="10" size="10" id="fechaAcuseResp" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield key="fechaAcuseResp" value="%{fechaAcuseResp}"  maxlength="10" size="10" id="fechaAcuseResp" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trgA" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />		
			</td>
		</tr>
	</table>
	<script type="text/javascript">
	<!--
		Calendar.setup({
			inputField     :    'fechaDocResp',     
			ifFormat       :    "%d/%m/%Y",     
			button         :    'trgD',  
			align          :    "Br",           
			singleClick    :    true
			});
		Calendar.setup({
			inputField     :    'fechaAcuseResp',     
			ifFormat       :    "%d/%m/%Y",     
			button         :    'trgA',  
			align          :    "Br",           
			singleClick    :    true
			});
		//-->
	</script>	
</s:if>
<s:if test="lstOficioRespuestaSP.size()>0">
	<div class="borderBottom" style="text-align:center"><h1>Escrito Respuesta a Observaciones del Participante</h1></div><br>
	<table class="clean">
		<s:iterator value="lstOficioRespuestaSP" var="resultado"  status="itStatus">
			<tr>
				<td><label class="left1"><span class="requerido">*</span>Oficio de Respuesta</label></td>
				<td>
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocRespuesta+nomArchivoRespuesta}"/>" title="Archivo Oficio Observaci&oacute;n">
						<s:property value="%{noOficioRespuesta}"/>
					</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>





	


