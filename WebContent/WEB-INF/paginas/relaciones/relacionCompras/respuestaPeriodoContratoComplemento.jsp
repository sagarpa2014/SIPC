<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<div>
	<label class="left1">Capture el rango de fechas:</label>
</div>

<div class="izquierda">
	<label >Periodo Inicial:</label>		
	<s:textfield name="fechaInicio" maxlength="10" size="10" id="fechaInicioCom" readonly="true" cssClass="dateBox"/>
	<img src="../images/calendar.gif" id="trgfechaInicioCom" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	<script type="text/javascript">
		<!--
			Calendar.setup({
				inputField     :    "fechaInicioCom",     
				ifFormat       :    "%d/%m/%Y",     
				button         :    "trgfechaInicioCom",  
				align          :    "Br",           
				singleClick    :    true
			});	
		//-->
	</script>
</div>
<div class="izquierda">
	<label >Periodo Final:</label>		
	<s:textfield name="fechaFin" maxlength="10" size="10" id="fechaFinCom" readonly="true" cssClass="dateBox"/>
	<img src="../images/calendar.gif" id="trgfechaFinCom" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	<script type="text/javascript">
		<!--
			Calendar.setup({
				inputField     :    "fechaFinCom",     
				ifFormat       :    "%d/%m/%Y",     
				button         :    "trgfechaFinCom",  
				align          :    "Br",           
				singleClick    :    true
			});	
		//-->
	</script>
</div>