<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1">Nombre del Auditor:</label>
	<font class="arial12bold"><s:property value="%{auditor.nombre}"/></font>
</div>
<br>
<s:include value="/WEB-INF/paginas/auditores/expedientesAuditores.jsp" />
