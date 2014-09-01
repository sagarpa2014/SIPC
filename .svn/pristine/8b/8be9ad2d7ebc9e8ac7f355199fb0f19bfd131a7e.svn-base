<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>CARTAS DE ADHESIÓN</h1></div><br>
<fieldset>
	<legend>Programa de Apoyos: <font class="arial14bold"><s:property value="%{nombrePrograma}"/></font></legend>
		<display:table id="r" name="lstAsignacionCAaEspecialistaV"  list="lstAsignacionCAaEspecialistaV"  pagesize="50" sort="list" requestURI="/solicitudPago/verCartaAdehsionAsignadasPagos"  class="displaytag">
			<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n"/>
			<display:column  property="nombreComprador" title="Participante"/>
			<display:column  property="estatusCartaAdhesion" title="Estatus"/>
			<display:column  property="fianza" title="Incluye Fianza"/>
	 		<display:column title="Pagos">
				<a href='<s:url value="/solicitudPago/detallePagosCartaAdhesion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&nombrePrograma=%{#attr.r.idPrograma}&clabe=%{#attr.r.clabe}"/>' title="">Registrar</a>
			</display:column>
	 		<display:column title="">
				<a href='<s:url value="/solicitudPago/consultaPagosCartaAdhesion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}"/>' title="">Ver</a>
			</display:column>
		</display:table>
</fieldset>
<div class="accion">
	<a href="<s:url value="/solicitudPago/listarProgramasPagosCartaAdhesion"/>" class="boton" title="" >Regresar</a>
</div>
