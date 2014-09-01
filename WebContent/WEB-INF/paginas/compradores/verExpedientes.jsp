<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<label class="left1">Nombre del Comprador:</label>
	<font class="arial12bold"><s:property value="%{comprador.nombre}"/></font>
</div>
<br>
<s:include value="/WEB-INF/paginas/compradores/expedientesComprador.jsp" />
