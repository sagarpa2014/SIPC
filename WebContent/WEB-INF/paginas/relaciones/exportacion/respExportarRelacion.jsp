<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div class="borderBottom"><h1>EXPORTACI&Oacute;N DE RELACIONES</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div id="dialogo_1"></div>
<fieldset>
	<s:if test="msjOk!=null && msjOk !=''">
		<div class="exporta_csv">
			<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCartaAdhesion+nombreRelacion}"/>" title="Descargar Archivo" ></a>
		</div>
	</s:if>
	<s:else>
		<div class="advertencia">No existe informaci&oacute;n registrada para la Carta de Adhesi&oacute;n que capturo</div>
	</s:else>
</fieldset>
<br>
<s:if test="opcion == 1">
	<div class="izquierda"><a href="<s:url value="/relaciones/listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:if>
<s:else>
	<div class="izquierda"><a href="<s:url value="/relaciones/capExportarRelacion"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:else>


