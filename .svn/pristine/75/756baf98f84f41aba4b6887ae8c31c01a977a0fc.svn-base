<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>INICIALIZACI&Oacute;N DE LA SOLICITUD DE PAGO</h1></div><br>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<s:form action=""  onsubmit="">
	<div class="derecha"><a href="<s:url value="/solicitudPago/capInicializacionSolPago"/>" onclick="" title="Capturar Inicializaci&oacute;n" >[Capturar Inicializaci&oacute;n]</a></div><br>
	<s:if test="lstProgramas.size() > 0">
		<display:table id="r"  name="lstProgramas"  list="lstProgramas"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaAuditor"  class="displaytag">
			<display:column  property="descripcionCorta" title="Programa"/>
			<display:column title="Ver Detalle"  headerClass="sortable">
				<a href='<s:url value="/solicitudPago/getDetalleConfExpPrograma?registrar=2&idPrograma=%{#attr.r.idPrograma}"/>' class="botonVerDetalles" title="" ></a>
		 	</display:column>
		 	<display:column title="Editar"  headerClass="sortable" >
		 		<a href='<s:url value="/solicitudPago/getDetalleConfExpPrograma?registrar=3&idPrograma=%{#attr.r.idPrograma}"/>' class="editar" title="" ></a>
		 	</display:column>
		</display:table>	
	</s:if>
	<s:else>
		<div class="advertencia">No se encontraron programas para configurar expedientes</div>
	</s:else>
</s:form>
