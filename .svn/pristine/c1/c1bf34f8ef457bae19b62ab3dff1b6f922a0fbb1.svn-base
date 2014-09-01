<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>ESQUEMA DE APOYOS</h1></div><br>
<div id="dialogo_1"></div>
<s:if test='#session.idPerfil==3'>
	<div class="derecha"><a href="<s:url value="/inscripcion/capturarInicializacionPrograma"/>" onclick="" title="Agregar Esquema" >[Agregar Esquema]</a></div><br>
</s:if>
<s:if test="lstProgramaV.size() > 0">
	<display:table id="r"  name="lstProgramaV"  list="lstProgramaV"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaCompradores"  class="displaytag">
		<display:column  property="descripcionCorta" title="Programa"/>
		<display:column  property="ciclo" title="Ciclo"/>
		<display:column title="Fecha Publicación"  headerClass="sortable" >
			<s:text name="fecha"><s:param value="%{#attr.r.fechaPublicacion}"/></s:text>
		</display:column>
		<display:column title="Lineamiento"  headerClass="sortable" >
			<s:set name="rutaCompleta"><s:property value="%{#attr.r.rutaDocumentos}"/></s:set>
			<div class="pdf">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#attr.r.rutaDocumentos+#attr.r.archivoPublicacionDof}"/>" title="Archivo de Publicación DOF" ></a>
			</div>
		</display:column>		
		<s:if test='#session.idPerfil==3'>
			<display:column title="Adiciones"  headerClass="sortable" >
				<a href='<s:url value="/inscripcion/getDetalleInicialiciacionPrograma?editar=4&idPrograma=%{#attr.r.idPrograma}"/>' class="botonVerDetalles" title=""></a>
			</display:column>
			<display:column title="Editar"  headerClass="sortable" >
				<s:if test='#attr.r.numDeSolicitudes==0'>
					<a href='<s:url value="/inscripcion/getDetalleInicialiciacionPrograma?editar=3&idPrograma=%{#attr.r.idPrograma}"/>' class="editar" title=""></a>
				</s:if>
			</display:column>		
		</s:if>
		<display:column title="Ver Detalle"  headerClass="sortable" >
			<a href='<s:url value="/inscripcion/getDetalleInicialiciacionPrograma?editar=2&idPrograma=%{#attr.r.idPrograma}"/>' class="botonVerDetalles" title=""></a>
		</display:column>
		
	</display:table>
</s:if>
<s:else><div class="advertencia">No se encontraron registros</div></s:else>


