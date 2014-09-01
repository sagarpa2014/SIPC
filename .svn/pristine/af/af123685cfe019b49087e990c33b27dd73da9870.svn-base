<%@taglib uri="/struts-tags" prefix="s"%>
<div class="borderBottom" style="text-align:center"><h1>Datos del Oficio de Observaciones</h1></div><br>
	<table class="clean">
		<s:if test="rutaDocObs==null">
			<tr>
				<td><label class="left1"><s:if test="%{alcanceDocumentacion == true}"><span class="requerido">*</span></s:if><s:else><span class="norequerido">*</span></s:else>Oficio de Observación</label></td>
				<td>
					<s:file name="docObs" id="docObs"/>
				</td>			
			</tr>
		</s:if>
		<tr>
			<td><label class="left1"><s:if test="%{alcanceDocumentacion == true}"><span class="requerido">*</span></s:if><s:else><span class="norequerido">*</span></s:else>Fecha Documento</label></td>
			<td>
				<s:if test="%{fechaDocOBS==null}" >
					<s:textfield name="fechaDocOBS" maxlength="10" size="10" id="fechaDocOBS" readonly="true" cssClass="dateBox" />
					<img src="../images/calendar.gif" id="trg3" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
					<script type="text/javascript">
						<!--
							Calendar.setup({
								inputField     :    "fechaDocOBS",     
								ifFormat       :    "%d/%m/%Y",     
								button         :    "trg3",  
								align          :    "Br",           
								singleClick    :    true
							});											   
							//-->
					</script>
				</s:if>
				<s:else>
					<s:textfield id="" name="" maxlength="30" size="30"  value="%{getText('fecha1',{fechaDocOBS})}" disabled="true"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1"><s:if test="%{alcanceDocumentacion == true}"><span class="requerido">*</span></s:if><s:else><span class="norequerido">*</span></s:else>Fecha Acuse</label>	
			</td>
			<td>
				<s:if test="%{fechaAcuseOBS==null}" >
					<s:textfield name="fechaAcuseOBS" maxlength="10" size="10" id="fechaAcuseOBS" readonly="true" cssClass="dateBox" />
					<img src="../images/calendar.gif" id="trg4" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
					<script type="text/javascript">
						<!--
							Calendar.setup({
							inputField     :    "fechaAcuseOBS",     
							ifFormat       :    "%d/%m/%Y",     
							button         :    "trg4",  
							align          :    "Br",           
							singleClick    :    true
						});											   
							//-->
					</script>
				</s:if>
				<s:else>
					<s:textfield id="" name="" maxlength="30" size="30"  value="%{getText('fecha1',{fechaAcuseOBS})}" disabled="true"/>
				</s:else>		
			</td>
		</tr>
		
		<tr>
			<td><label class="left1"><s:if test="%{noOficioOBS==null}" ><span class="requerido">*</span></s:if><s:else><span class="norequerido">*</span></s:else>No. de Oficio</label></td>
			<td>
				<s:if test="%{noOficioOBS==null}" ><s:textfield id="noOficioOBS" name="noOficioOBS" maxlength="30" size="30"  value="%{}"/></s:if>
				<s:else><s:textfield id="noOficioOBS" name="noOficioOBS" maxlength="30" size="30"  value="%{noOficioOBS}" disabled="true"/></s:else>
			</td>
		</tr>
		
	</table>



	


