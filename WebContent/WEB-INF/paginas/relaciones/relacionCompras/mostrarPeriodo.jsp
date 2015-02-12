<%@taglib uri="/struts-tags" prefix="s"%>
<div class="izquierda">
	<label class="left1">Periodo Inicial:</label>		
	<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaInicio}"/></s:text></font>
	<s:hidden name = "fechaInicio" value="%{fechaInicio}"/>	
</div>
<div class="izquierda">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div class="izquierda">
	<label class="left1">Periodo Final:</label>		
	<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaFin}"/></s:text></font>
	<s:hidden name = "fechaFin" value="%{fechaFin}"/>
</div>