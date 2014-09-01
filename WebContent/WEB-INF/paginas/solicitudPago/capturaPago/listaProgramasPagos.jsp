<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<div class="borderBottom"><h1>PROGRAMAS DE APOYOS A LA COMERCIALIZACIÓN</h1></div><br>
<fieldset>
	<legend>Programas</legend>	
		<display:table id="r" name="lstProgramas"  list="lstProgramas"  pagesize="50" sort="list" requestURI="/solicitudPago/listarProgramasPagosCartaAdhesion"  class="displaytag">
			<display:column  property="descripcionCorta" title="Programa"/>
	 		<display:column title="Cartas de Adhesi&oacute;n">
				<a href='<s:url value="/solicitudPago/verCartaAdhesionAsignadasPagos?idPrograma=%{#attr.r.idPrograma}"/>' title="">Ver</a>
			</display:column>
		</display:table>			
</fieldset>
