<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{nombreArchivoLogXls!=null}">
	<fieldset>
			<legend>Descargar Log de procesos</legend>	
			<div class="exporta_csv">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCartaAdhesion+nombreArchivoLogXls}"/>" title="Descargar Archivo" ></a>
			</div>
	</fieldset>
</s:if>

