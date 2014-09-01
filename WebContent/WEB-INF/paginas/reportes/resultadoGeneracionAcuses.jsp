<%@taglib uri="/struts-tags" prefix="s"%>
<div class="withborder">
	<div class="correcto">Se gener&oacute; satisfactoriamente el archivo, favor de descargarlo</div>
	<div class="clear"></div>
	<br>
	<div class="pdf">
		<a href="<s:url value="/devuelveArchivo?rutaArchivo=%{rutaSalida}&nombreArchivo=%{nombreOficio}&mimeType=pdf"/>" title="Descargar Archivo" ></a>
	</div>
	<br>
	<br>		
</div>
<br>
<div class="accion">
	<a href='#' onclick="window.close();" class="boton">Cerrar</a>
</div>


	
		