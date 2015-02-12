<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
<s:hidden id="cultivoRelacion" name="cultivoRelacion" value="%{cultivoRelacion}"/>
<s:hidden id="idPgrCiclo" name="idPgrCiclo" value="%{idPgrCiclo}"/>
<s:if test="hasActionErrors()">
 	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<div id="dialogo_1"></div>
<fieldset>
	<s:include value="datosGeneralesRelCompras.jsp" />
</fieldset>

<s:if test="lstGpoCampoVentasRelDetalleV.size() > 0">
	<fieldset>
		<legend>Registros</legend>		
		<display:table id="r"  name="lstGpoCampoVentasRelDetalleV"  list="lstGpoCampoVentasRelDetalleV"  pagesize="50" sort="list" requestURI="/relaciones/mostrarVentasByCicloCultivo"  class="displaytag">
			<display:column  property="folio" title="Folio Factura"/>
			<display:column title="Consultar Registro" class="c"  headerClass="sortable">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=1&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{#attr.r.cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&folioFacVenta=%{#attr.r.folio}"/>'  title="" onclick="">Consultar</a>
			</display:column>
			<s:if test="#attr.r.folioCartaAdhesion == null">
				<display:column title="Editar Registro" class="c" headerClass="sortable">
					<a href='<s:url value="/relaciones/capturarRelaciones?registrar=2&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{#attr.r.cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&folioFacVenta=%{#attr.r.folio}"/>'  title="" onclick="">Editar</a>
				</display:column>
				<display:column title="Borra Registro" class="c" headerClass="sortable">
					<a href='<s:url value="/relaciones/borrarRegistro?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{#attr.r.cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&folioFacVenta=%{#attr.r.folio}"/>'  title="" onclick="if (confirm('¿Esta seguro en eliminar el registro?')){}else{return false;}">Borrar</a>
				</display:column>
			</s:if>
		</display:table>		
	</fieldset>
</s:if>
<s:else><div class="advertencia">No se encontraron registros</div></s:else>
<div class="derecha">
	<s:if test="lstGpoCampoVentasRelDetalleV.get(0).folioCartaAdhesion == null">
		<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}"/>'  title="Registrar" >[Agregar Registro]</a>
	</s:if>
</div>
<div class="izquierda">
	<a href="<s:url value="listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="" title="" >&lt;&lt; Regresar</a>
</div>
<script>
function borrarRegistro(idPerRel, idIniEsquemaRelacion, claveBodega, cultivoRelacion,idPgrCiclo, folioCertificado, razonSocialAlmacen){
	var mensaje = "";
	mensaje = "¿Esta seguro de Eliminar el Registro con la clave Bodega "+claveBodega+" y el folio  "+folioCertificado+"?";
	if (confirm(mensaje)){
	  $(".borrar").each(function(){ 
		  $(this).attr('href', 'SIPC/relaciones/borrarRegistro?idPerRel='+idPerRel+'&idIniEsquemaRelacion='+idIniEsquemaRelacion+'&claveBodega='+claveBodega+'&cultivoRelacion='+cultivoRelacion+'&idPgrCiclo='+idPgrCiclo+'&folioCertificado'+folioCertificado+'&razonSocialAlmacen='+razonSocialAlmacen);
		  });
	}else{
		return false;
	}
}
</script>