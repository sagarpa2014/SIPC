<%@taglib uri="/struts-tags" prefix="s"%>
<div class="withborder">
	<s:if test="%{nombreReporte!=null}">
		<div class="correcto">Se gener&oacute; satisfactoriamente el archivo, favor de descargarlo</div>
		<div class="clear"></div>
		<br>
		<div class="exporta_csv">
			<a href="<s:url value="/seguimiento/consigueArchivoExcel?nombreReporte=%{nombreReporte}"/>" title="Descargar Archivo" ></a>
		</div>
		<br>
		<br>
	</s:if>
	<s:else>
		<span class="error">
			No se pudo generar el reporte resumen de avance de seguimiento, por favor verifique!!!
		</span>	
		<br>
		<div class="accion">
			<a href="#" class="boton" title="" onclick="cerrarErrorOficio()">Cerrar</a>
		</div>
	</s:else>
</div>
<div class="clear"></div>
<div class="izquierda"><a href="<s:url value="/seguimiento/consultarReporteResumenAvance"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>



	
		