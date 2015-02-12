<%@taglib uri="/struts-tags" prefix="s"%>
<div class="izquierda">
		<label class="left1">Fecha Inicio:</label>	
		<s:if test="%{fechaInicio==null}" >
			<s:textfield name="fechaInicio" maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaInicio" value="%{getText('fecha1',{fechaInicio})}" maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
</div>
<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
<div class="izquierda">
		<label class="left1">Fecha Fin:</label>	
		<s:if test="%{fechaFin==null}" >
			<s:textfield name="fechaFin" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaFin" value="%{getText('fecha1',{fechaFin})}" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
</div>
	<div class="clear"></div>	
	<script type="text/javascript">	
		Calendar.setup({
			inputField     :    "fechaInicio",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg1",  
			align          :    "Br",           
			singleClick    :    true
		});
		Calendar.setup({
			inputField     :    "fechaFin",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg2",  
			align          :    "Br",           
			singleClick    :    true
			});
	</script>	