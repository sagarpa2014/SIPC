<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="acreditacionJuridica==0 || acreditacionJuridica==1">
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Oficio Acreditaci&oacute;n</label>
		<s:if test="registrar==0 || registrar==1 || registrar==3">
			<s:file  name="docOA" id="docOA"/>
		</s:if>
		<s:if test="registrar==2 || registrar == 3">
			<s:hidden id="docOAFileName" name="docOAFileName" value="%{docOAFileName}"/>
			<s:if test="docOAFileName!='' && docOAFileName!=null">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docOAFileName}"/>" title="Oficio">Descargar Oficio</a>
			</s:if>
		</s:if>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Documento</label>
		<s:if test="%{fechaDocOA==null}" >
			<s:textfield name="fechaDocOA" maxlength="10" size="10" id="fechaDocOA" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaDocOA" value="%{getText('fecha1',{fechaDocOA})}" maxlength="10" size="10" id="fechaDocOA" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg5" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Acuse</label>
		<s:if test="%{fechaAcuseOA==null}" >
			<s:textfield name="fechaAcuseOA" maxlength="10" size="10" id="fechaAcuseOA" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaAcuseOA" value="%{getText('fecha1',{fechaAcuseOA})}" maxlength="10" size="10" id="fechaAcuseOA" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg6" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
		<s:textfield id="noOficioA" name="noOficioA"  maxlength="30" size="30"  value="%{noOficioA}"/>
	</div>
	<div class="clear"></div>
	<script type="text/javascript">
			<!--
				Calendar.setup({
					inputField     :    "fechaDocOA",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg5",  
					align          :    "Br",           
					singleClick    :    true
				});
				Calendar.setup({
					inputField     :    "fechaAcuseOA",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg6",  
					align          :    "Br",           
					singleClick    :    true
					});		
			//-->
		</script>
</s:if>
<s:else>
	<div>
		<label class="left1"><span class="requerido">*</span>Oficio C.J. Con Observaci&oacute;n:</label>
		<s:if test="registrar==0 || registrar==1 || registrar == 3">
			<s:file  name="docOOA" id="docOOA"/>
		</s:if>
		<s:if test="registrar==2 || registrar==3">
			<s:hidden id="docOOAFileName" name="docOOAFileName" value="%{docOOAFileName}"/>
			<s:if test="docOOAFileName!='' && docOOAFileName!=null">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docOOAFileName}"/>" title="Oficio">Descargar Oficio</a>
			</s:if>
		</s:if>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Documento:</label>
		<s:if test="%{fechaDocOOA==null}" >
			<s:textfield name="fechaDocOOA" maxlength="10" size="10" id="fechaDocOOA" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaDocOOA"  value="%{getText('fecha1',{fechaDocOOA})}" maxlength="10" size="10" id="fechaDocOOA" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg7" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Acuse:</label>
		<s:if test="%{fechaAcuseOOA==null}" >
			<s:textfield name="fechaAcuseOOA" maxlength="10" size="10" id="fechaAcuseOOA" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaAcuseOOA" value="%{getText('fecha1',{fechaAcuseOOA})}" maxlength="10" size="10" id="fechaAcuseOOA" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg8" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
		<s:textfield id="noOficioOA" name="noOficioOA"  maxlength="30" size="30"  value="%{noOficioOA}"/>
	</div>
	<script type="text/javascript">
		<!--
			Calendar.setup({
				inputField     :    "fechaDocOOA",     
				ifFormat       :    "%d/%m/%Y",     
				button         :    "trg7",  
				align          :    "Br",           
				singleClick    :    true
			});
			Calendar.setup({
				inputField     :    "fechaAcuseOOA",     
				ifFormat       :    "%d/%m/%Y",     
				button         :    "trg8",  
				align          :    "Br",           
				singleClick    :    true
				});		
		//-->
	</script>
</s:else>






