<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="%{lstProgramas.size()>0}" >
	<div>	
		<label class="left1"><span class="requerido">*</span>Programa:</label>
		<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="recuperaCompradoresByPrograma()" onchange="recuperaCompradoresByPrograma()" value="%{idPrograma}"/>
	</div>
	<div class="clear"></div>
	<div id="recuperaCiclosByPrograma">
		<s:if test="registrar==1 || registrar == 2 || registrar==3">
			<s:include value="/WEB-INF/paginas/inscripcion/solicitudInscripcion/ciclosByPrograma.jsp"/>
		</s:if>
	</div>
</s:if>
<s:else><div class="advertencia">No se encontraron registros</div></s:else>