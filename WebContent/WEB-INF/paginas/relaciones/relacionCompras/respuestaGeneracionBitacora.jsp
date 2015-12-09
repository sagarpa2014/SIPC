<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>


<fieldset>
	<legend>Reportes de Cruce</legend>
	<s:if test="%{lstBitacoraRelComprasPorGrupo.size() > 0}">
	<s:set name="fechaTemp"><s:text name="fecha"><s:param value="%{lstBitacoraRelComprasPorGrupo.get(0).fechaRegistro}"/></s:text></s:set>	
		<table class="clean">
			<s:iterator value="lstBitacoraRelComprasPorGrupo" var="resultado"  status="itStatus">
				<s:set name="fechaC"><s:text name="fecha"><s:param value="%{fechaRegistro}"/></s:text></s:set>
				<s:if test="%{#itStatus.first == true}">
					<tr><td><div class="borderBottom" style="text-align:center"><h1><s:text name="fecha"><s:param value="%{fechaRegistro}"/></s:text></h1></div></td></tr>
					</s:if>
				<s:elseif test="%{#fechaC != #fechaTemp}">
					<tr><td><div class="borderBottom" style="text-align:center"><h1><s:text name="fecha"><s:param value="%{fechaRegistro}"/></s:text></h1></div></td></tr>
				</s:elseif>
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
				<s:set name="fechaTemp"><s:text name="fecha"><s:param value="%{fechaRegistro}"/></s:text></s:set>
			</s:iterator>
		</table>
	</s:if>
</fieldset>