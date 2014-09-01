<%@taglib uri="/struts-tags" prefix="s"%>
<div class="withborder">
	<s:if test="%{nombreArchivo!=null}">
		<div class="correcto">Se gener&oacute; satisfactoriamente el archivo, favor de descargarlo</div>
		<div class="clear"></div>
		<br>
		<div class="exporta_csv">
			<a href="<s:url value="/reportes/consigueArchivoExcelRepC?nombreArchivo=%{nombreArchivo}"/>" title="Descargar Archivo" ></a>
		</div>
		<br>
		<br>
	</s:if>
	<s:else>
		<span class="error">
			No se pudo generar el archivo de excel, por favor verifique
		</span>	
	</s:else>
</div>


	
		