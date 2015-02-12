<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>



<s:if test="%{lstBitacoraRelComprasPorGrupo.size() > 0}">

	<table class="clean">
		<s:iterator value="lstBitacoraRelComprasPorGrupo" var="resultado"  status="itStatus">
			<tr>
				<td>
					<div class="withborder">
						<div class="correcto"><s:property value="%{mensaje}"/></div>
						<div class="pdf">
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaArchivo+nombreArchivo}"/>" title="Descargar Archivo" ></a>
						</div>		
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>
<s:else>
	
</s:else>