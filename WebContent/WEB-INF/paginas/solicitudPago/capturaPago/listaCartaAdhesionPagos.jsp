<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>CARTAS DE ADHESI&Oacute;N</h1></div><br>
<fieldset>
	<legend>Programa de Apoyos: <font class="arial14bold"><s:property value="%{nombrePrograma}"/></font></legend>
	<s:if test='#session.idPerfil==6'>
		<display:table id="r" name="lstAsignacionCAaEspecialistaV"  list="lstAsignacionCAaEspecialistaV"  pagesize="50" sort="list" requestURI="/solicitudPago/verCartaAdehsionAsignadasPagos"  class="displaytag">
			<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n"/>
			<display:column  property="nombreComprador" title="Participante"/>
			<display:column  property="estatusCartaAdhesion" title="Estatus"/>
			<display:column  property="fianza" title="Incluye Fianza"/>
	 		<display:column title="Pagos">
	 			<s:if test="%{#attr.r.estatusCA==4 || #attr.r.estatusCA==5}">
					<a href='<s:url value="/solicitudPago/detallePagosCartaAdhesion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&idPrograma=%{#attr.r.idPrograma}&clabe=%{#attr.r.clabe}"/>' title="">Registrar</a>
				</s:if>
			</display:column>			
	 		<display:column title="">
				<a href='<s:url value="/solicitudPago/consultaPagosCartaAdhesion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&idPrograma=%{#attr.r.idPrograma}"/>' title="">Ver</a>
			</display:column>
		</display:table>
	</s:if>
	<s:else>
		<display:table id="r" name="lstCartasPrimerPagoV"  list="lstCartasPrimerPagoV"  pagesize="50" sort="list" requestURI="solicitudPago/verCartaAdhesionAsignadasPagos"  class="displaytag">
			<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n"/>
			<display:column  property="comprador" title="Participante"/>			
	 		<display:column title="">
				<a href='<s:url value="/solicitudPago/detalleEditarPagosCartaAdhesion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&idPrograma=%{#attr.r.idPrograma}&idPago=%{#attr.r.idPago}"/>' title="">Ver</a>
			</display:column>
		</display:table>
	</s:else>		
</fieldset>
<div class="accion">
	<a href="<s:url value="/solicitudPago/listarProgramasPagosCartaAdhesion"/>" class="boton" title="">Regresar</a>
</div>
