<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<div class="borderBottom"><h1>RELACIONES</h1></div><br>  
<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<fieldset>
	<s:include value="datosGeneralesRelCompras.jsp" />
</fieldset>
<s:if test="lstPersonalizacionRelV.size() > 0">
	<div class="clear"></div>
	<br/>
	<fieldset>
		<legend>Relaciones Registradas</legend>
		<table  style="width: 100%">
			<tr>
				<th>Relaci&oacute;n</th>
				<th>Agregar Bodegas</th>
				<th>Registrar Carta Adhesi&oacute;n</th>
				<th>Ver</th>
			</tr>
			<s:iterator value="lstPersonalizacionRelV" var="resultado"  status="itStatus">
				<tr>
					<td style="width: 30%" ><s:property value="%{relacion}"/></td>
					<td style="text-align: center; width: 10%"><a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>' class="" title="">Agregar</a></td>
					<td style="text-align: center; width: 10%">
						<a href='<s:url value="/relaciones/capRegistroCartaAdhesion?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idRelacion=%{idTipoRelacion}"/>'  title="" onclick="">Registrar</a>
					</td>
					<td style="width: 50%" >
						<img id="imgBodegaOff<s:property value="%{#itStatus.count}"/>" src="<s:url value="/images/mas.png"/>" onclick="recuperaBodegas(<s:property value="%{idPerRel}"/>, <s:property value="%{idIniEsquemaRelacion}"/>, <s:property value="%{#itStatus.count}"/>, 1, <s:property value="%{idTipoRelacion}"/>);"/>
						<img id="imgBodegaOn<s:property value="%{#itStatus.count}"/>"  class="elementoOculto" src="<s:url value="/images/menos.png"/>" onclick="recuperaBodegas(<s:property value="%{idPerRel}"/>, <s:property value="%{idIniEsquemaRelacion}"/>, <s:property value="%{#itStatus.count}"/>,0, <s:property value="%{idTipoRelacion}"/>);"/>
						<div id="recuperaBodegas<s:property value="%{#itStatus.count}"/>"></div>
					</td>
				</tr>
			</s:iterator>
		</table>
	</fieldset>
</s:if>
<s:else><div class="advertencia">No se encontraron registros</div></s:else>
<div class="izquierda">
		<a href="<s:url value="/relaciones/listProgramaCap"/>" class="" title="" >&lt;&lt; Regresar</a>
</div>
<script>
 function busquedaFolio(registrar, idPerRel){
	 var claveBodega = "";
	 var folioPredio = "";	 
		if(registrar == 1){
			claveBodega=prompt("Capture la clave de la bodega que desea consultar");
			if(claveBodega == null || claveBodega ==""){
				return false;
			}
			if (confirm){
				folioProductor=prompt("Capture el numero de predio");
				if(folioProductor == null || folioProductor ==""){
					return false;
				}
			}else{
				return false;
			}
		}else if(registrar == 2){
			claveBodega=prompt("Capture la clave de la bodega que desea consultar");
			if(claveBodega == null || claveBodega ==""){
				return false;
			}
			if (confirm){
				folioProductor=prompt("Capture el Folio del Productor");
				if(folioProductor == null || folioProductor ==""){
					return false;
				}
			}else{
				return false;
			}
		}
		if (confirm){
			if(registrar == 1){
				  $(".botonVerDetalles").each(function(){
						$(this).attr('href','capturarRelaciones?registrar='+registrar+'&claveBodega='+claveBodega+'&folioProductor='+folioProductor+'&idPerRel='+idPerRel);
					});
			}else if(registrar == 2){
				$(".editar").each(function(){
					$(this).attr('href','capturarRelaciones?registrar='+registrar+'&claveBodega='+claveBodega+'&folioProductor='+folioProductor+'&idPerRel='+idPerRel);
				});
 			}
		}
 }
 </script>
<script>
function borrarRegistro(idPerRel){
	var mensaje = "";
	var claveBodega = "";
	var folioProductor = "";
	claveBodega=prompt("Capture la clave de la bodega que desea borrar");
	if(claveBodega == null || claveBodega ==""){
		return false;
	}
	if (confirm){
		folioProductor=prompt("Capture el numero de predio que desea borrar");
		if(folioProductor == null || folioProductor ==""){
			return false;
		}
	}else{
		return false;
	}
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
<script>
function registraCartaAdhesion(idPerRel, idComprador, idIniEsquemaRelacion){
	console.log("idIniEsquemaRelacion "+idIniEsquemaRelacion);
	var folioCartaAdhesion = "";
	folioCartaAdhesion=prompt("Capture el folio de la Carta Adhesion para asociar los registros");
	if(folioCartaAdhesion == null || folioCartaAdhesion ==""){
		return false;
	}
	if (confirm){
		$(".botonVerDetalles").each(function(){
			$(this).attr('href', 'SIPC/relaciones/registraCartaAdhesion?idPerRel='+idPerRel+'&idComprador='+idComprador+'&folioCartaAdhesion='+folioCartaAdhesion+"&idIniEsquemaRelacion="+idIniEsquemaRelacion);
		});
	}else{
		return false;
	}
 }
 </script>