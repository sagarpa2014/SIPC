<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>RELACIONES</h1></div><br>  
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<s:if test="tipoCaptura == 0">
	<div class="derecha"><a href="<s:url value="/relaciones/personalizaRelaciones?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>"  title="Registrar Solicitud" >[Registrar Personalizaci&oacute;n]</a></div>
	<s:if test="lstPersonalizacionRelV.size() > 0">
		<div class="clear"></div>
		<br/>
		<fieldset>
			<legend>Esquema - Relaci&oacute;n</legend>
			<display:table id="r"  name="lstPersonalizacionRelV"  list="lstPersonalizacionRelV"  pagesize="50" sort="list" requestURI="/relaciones/ejecutaBusquedaPersonalizacionRelaciones"  class="displaytag">
				<display:column  property="nombreEsquema" title="Esquema"/>
				<display:column  property="cultivos" title="Cultivo"/>
				<display:column  property="cicloAgricola" title="Ciclo Agricola"/>
				<display:column  property="relacion" title="Tipo de Relación"/>
		 		<display:column title="Ver Detalle"  headerClass="sortable">
					<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=2&idPerRel=%{#attr.r.idPerRel}"/>' class="botonVerDetalles" title=""></a>
				</display:column>
				<display:column title="Editar"  headerClass="sortable">
					<s:if test="%{#attr.r.idTipoRelacion==1}">
						<s:if test="%{#attr.r.relCompras==0}">
							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a>
						</s:if>
					</s:if>
					<s:elseif test="%{#attr.r.idTipoRelacion==2}">
						<s:if test="%{#attr.r.relTerrestre==0}">
							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a>
						</s:if>
					</s:elseif>
					<s:elseif test="%{#attr.r.idTipoRelacion==3}">
						<s:if test="%{#attr.r.relMaritima==0}">
							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a>
						</s:if>
					</s:elseif>
					<s:elseif test="%{#attr.r.idTipoRelacion==4}">
						<s:if test="%{#attr.r.relCertificadoDeposito==0}">
							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a>
						</s:if>
					</s:elseif>
					<s:elseif test="%{#attr.r.idTipoRelacion==5}">
						<s:if test="%{#attr.r.relVentas==0}">
							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a>
						</s:if>
					</s:elseif>	
				</display:column>
			</display:table>
		</fieldset>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros</div></s:else>
</s:if>
<s:else><!-- Programa ya publicado  -->
	<div class="derecha"><a href="<s:url value="/relaciones/personalizaRelaciones?idPrograma=%{idPrograma}&tipoCaptura=1"/>"  title="Registrar Solicitud" >[Registrar Personalizaci&oacute;n]</a></div>
	<s:if test="lstPersonalizacionRelacionesProgramaV.size() > 0">
		<div class="clear"></div>
		<br/>
		<fieldset>
			<legend>Esquema - Relaci&oacute;n</legend>
			<display:table id="r"  name="lstPersonalizacionRelacionesProgramaV"  list="lstPersonalizacionRelacionesProgramaV"  pagesize="50" sort="list" requestURI="/relaciones/ejecutaBusquedaPersonalizacionRelaciones"  class="displaytag">
				<display:column  property="descripcionCorta" title="Esquema"/>
				<display:column  property="cultivos" title="Cultivo"/>
				<display:column  property="cicloAgricola" title="Ciclo Agricola"/>
				<display:column  property="relacion" title="Tipo de Relación"/>
		 		<display:column title="Ver Detalle"  headerClass="sortable">
					<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=2&idPerRel=%{#attr.r.idPerRel}&tipoCaptura=1"/>' class="botonVerDetalles" title=""></a>
				</display:column>
				<display:column title="Editar"  headerClass="sortable">
					<s:if test="%{#attr.r.idTipoRelacion==1}">
						<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}&tipoCaptura=1"/>' class="editar" title=""></a>					
					</s:if>
				</display:column>
<%-- 				<display:column title="Editar"  headerClass="sortable"> --%>
<%-- 					<s:if test="%{#attr.r.idTipoRelacion==1}"> --%>
<%-- 						<s:if test="%{#attr.r.relCompras==0}"> --%>
<%-- 							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a> --%>
<%-- 						</s:if> --%>
<%-- 					</s:if> --%>
<%-- 					<s:elseif test="%{#attr.r.idTipoRelacion==2}"> --%>
<%-- 						<s:if test="%{#attr.r.relTerrestre==0}"> --%>
<%-- 							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a> --%>
<%-- 						</s:if> --%>
<%-- 					</s:elseif> --%>
<%-- 					<s:elseif test="%{#attr.r.idTipoRelacion==3}"> --%>
<%-- 						<s:if test="%{#attr.r.relMaritima==0}"> --%>
<%-- 							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a> --%>
<%-- 						</s:if> --%>
<%-- 					</s:elseif> --%>
<%-- 					<s:elseif test="%{#attr.r.idTipoRelacion==4}"> --%>
<%-- 						<s:if test="%{#attr.r.relCertificadoDeposito==0}"> --%>
<%-- 							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a> --%>
<%-- 						</s:if> --%>
<%-- 					</s:elseif> --%>
<%-- 					<s:elseif test="%{#attr.r.idTipoRelacion==5}"> --%>
<%-- 						<s:if test="%{#attr.r.relVentas==0}"> --%>
<%-- 							<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=3&idPerRel=%{#attr.r.idPerRel}"/>' class="editar" title=""></a> --%>
<%-- 						</s:if> --%>
<%-- 					</s:elseif>	 --%>
<%-- 				</display:column> --%>
			</display:table>
		</fieldset>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros</div></s:else>
</s:else>
<div class="clear"></div>
	<div class="izquierda">
		<s:if test="tipoCaptura == 0">
			<a href="<s:url value="/relaciones/listProgramaRelacion?tipoCaptura=0"/>" class="" title="" >&lt;&lt; Regresar</a>
		</s:if>
		<s:else>
			<a href="<s:url value="/relaciones/listProgramaRelacion?tipoCaptura=1"/>" class="" title="" >&lt;&lt; Regresar</a>
		</s:else>
		
	</div>	


