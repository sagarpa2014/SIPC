<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="acreditacionDictamen==0 || acreditacionDictamen==1">
	<div>
		<label class="left1"><span class="norequerido">*</span>Oficio Dictaminaci&oacute;n</label>
		<s:if test="registrar==0 || registrar==1 || registrar==3">
			<s:file  name="docOD" id="docOD"/>
		</s:if>
		<s:if test="registrar==2 || registrar == 3">
			<s:hidden id="docODFileName" name="docODFileName" value="%{docODFileName}"/>
			<s:if test="docODFileName!='' && docODFileName!=null">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docODFileName}"/>" title="Oficio">Descargar Oficio</a>
			</s:if>
			<s:else><font class="arial12bold">Sin Informaci&oacute;n</font></s:else>
		</s:if>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="norequerido">*</span>Fecha Documento</label>
		<s:if test="%{fechaDocDic==null}" >
			<s:textfield name="fechaDocDic" maxlength="10" size="10" id="fechaDocDic" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaDocDic" value="%{getText('fecha1',{fechaDocDic})}" maxlength="10" size="10" id="fechaDocDic" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg9" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="norequerido">*</span>Fecha Acuse</label>
		<s:if test="%{fechaAcuseDic==null}" >
			<s:textfield name="fechaAcuseDic" maxlength="10" size="10" id="fechaAcuseDic" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else> 
			<s:textfield key="fechaAcuseDic" value="%{getText('fecha1',{fechaAcuseDic})}"  maxlength="10" size="10" id="fechaAcuseDic" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg10" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="norequerido">*</span>No. de Oficio</label>
		<s:textfield id="noOficioDic" name="noOficioDic" maxlength="30" size="30"  value="%{noOficioDic}"/>
	</div>
	<div class="clear"></div>
<s:if test="registrar==0 || registrar==1 || registrar==3">
	<script type="text/javascript">
			<!--
			Calendar.setup({
				inputField     :    "fechaDocDic",     
				ifFormat       :    "%d/%m/%Y",     
				button         :    "trg9",  
				align          :    "Br",           
				singleClick    :    true
			});
			
			Calendar.setup({
				inputField     :    "fechaAcuseDic",     
				ifFormat       :    "%d/%m/%Y",     
				button         :    "trg10",  
				align          :    "Br",           
				singleClick    :    true
				});
			
			//-->
		</script>
</s:if>
</s:if>
<s:else>
	<div>
		<label class="left1"><span class="requerido">*</span>Observaciones del Dictamen:</label>
		<s:textarea name="obsDictaminacion" cols="120" rows="3" value="%{obsDictaminacion}"/>
	</div>
</s:else>






