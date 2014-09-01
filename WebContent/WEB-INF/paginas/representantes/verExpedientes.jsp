<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1">Nombre del Representante Legal:</label>
	<font class="arial12bold"><s:property value="%{representante.nombre}"/></font>
</div>
<br>
<s:include value="/WEB-INF/paginas/representantes/expedientesRepresentante.jsp" />
