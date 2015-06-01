<%@taglib uri="/struts-tags" prefix="s"%>
<div class="izquierda">
	<label class="left1">Periodo Inicial:</label>		
	<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaInicioAuditor}"/></s:text></font>
	<s:hidden name = "fechaInicioAuditor" value="%{fechaInicioAuditor}"/>	
</div>
<div class="izquierda">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div class="izquierda">
	<label class="left1">Periodo Final:</label>		
	<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaFinAuditor}"/></s:text></font>
	<s:hidden name = "fechaFinAuditor" value="%{fechaFinAuditor}"/>
</div>