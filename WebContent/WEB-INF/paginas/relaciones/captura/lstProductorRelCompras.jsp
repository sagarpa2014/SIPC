<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
<s:hidden id="claveBodega" name="claveBodega" value="%{claveBodega}"/>
<s:hidden id="idCompPer" name="idCompPer" value="%{idCompPer}"/>
<s:set name= "estatus1"  value="%{estatus}"/>


<fieldset>
	<s:include value="datosGeneralesRelCompras.jsp" />
</fieldset>
<fieldset>
	<legend>Productores</legend>		
	<display:table id="r"  name="lstComprasDatosProductorV"  list="lstComprasDatosProductorV"  pagesize="50" sort="list" requestURI="/relaciones/lstProductorRelCompras"  class="displaytag">
		<display:column  property="folioProductor" title="Folio Productor"/>
		<display:column  title="Nombre Productor">
			<s:property value="%{#attr.r.nombre}"/>&nbsp;
			<s:if test="%{#attr.r.paterno !=null && #attr.r.paterno != ''}"><s:property value="%{#attr.r.paterno}"/></s:if>
			<s:if test="%{#attr.r.materno !=null && #attr.r.materno != ''}"><s:property value="%{#attr.r.materno}"/></s:if>
		</display:column>
		<display:column  property="rfc" title="RFC"/>
		<display:column  property="curp" title="CURP"/>
		<display:column title="Consultar Registro" class="c"  headerClass="sortable">
			<a href='<s:url value="/relaciones/capturarRelaciones?registrar=1&claveBodega=%{claveBodega}&folioProductor=%{#attr.r.folioProductor}&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&idCompProd=%{#attr.r.idCompProd}"/>'  title="" onclick="">Consultar</a>
		</display:column>
		<s:if test="%{#estatus1==1}">
			<display:column title="Editar Registro" class="c" headerClass="sortable">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=2&claveBodega=%{claveBodega}&folioProductor=%{#attr.r.folioProductor}&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&idCompProd=%{#attr.r.idCompProd}"/>'  title="" onclick="">Editar</a>
			</display:column>		
			<display:column title="Borra Registro" class="c" headerClass="sortable">
				<a href='<s:url value="/relaciones/borrarRegistro?claveBodega=%{claveBodega}&folioProductor=%{#attr.r.folioProductor}&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>'  title="" onclick="if (confirm('¿Esta seguro en eliminar el registro?')){}else{return false;}">Borrar</a>
			</display:column>
		</s:if>
	</display:table>		
</fieldset>
<div class="izquierda">
	<a href="<s:url value="listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="" title="" >&lt;&lt; Regresar</a>
</div>

<script>
function borrarRegistro(idPerRel, claveBodega, folioProductor){
	var mensaje = "";
	mensaje = "¿Esta seguro de Eliminar el Registro con la clave Bodega "+claveBodega+" y el folio productor "+folioProductor+"?";
	if (confirm(mensaje)){
	  $(".borrar").each(function(){ 		  
		  $(this).attr('href', 'SIPC/relaciones/borrarRegistro?idPerRel='+idPerRel+'&claveBodega='+claveBodega+'&folioProductor='+folioProductor);
		  });
	}else{
		return false;
	}
}
</script>