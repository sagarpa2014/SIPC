<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>SOLICITUD PAGO</h1></div><br>
<fieldset>
	<legend>Programas</legend>	
	
		<s:if test='#session.idPerfil==6'>
			<display:table id="r" name="lstPrgEspecialistaNumCartasVs"  list="lstPrgEspecialistaNumCartasVs"  pagesize="50" sort="list" requestURI="/solicitudPago/ejecutaBusquedaAsignacionCAaEspecialista"  class="displaytag">
				<display:column  property="programa" title="Programa"/>
				<display:column  property="numeroCartas" title="N&uacute;mero de Cartas"/>
		 		<display:column title="Cartas Adhesi&oacute;n">
					<a href='<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{#attr.r.idPrograma}"/>' title="">Ver</a>
				</display:column>
			</display:table>
		</s:if>
		<s:elseif test='#session.idPerfil==10'>
			<display:table id="r" name="lstNumCartasVs"  list="lstNumCartasVs"  pagesize="50" sort="list" requestURI="/solicitudPago/ejecutaBusquedaAsignacionCAaEspecialista"  class="displaytag">
				<display:column  property="programa" title="Programa"/>
				<display:column  property="numeroCartas" title="N&uacute;mero de Cartas"/>
		 		<display:column title="Cartas Adhesi&oacute;n">
					<a href='<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{#attr.r.idPrograma}"/>' title="">Ver</a>
				</display:column>
			</display:table>
		</s:elseif>
</fieldset>






	


