<%@taglib uri="/struts-tags" prefix="s"%>
	<s:if test="%{idEstado != null}">
		<div>
			<label class="left1"><span class="norequerido">*</span>Municipio:</label>
			<s:select id="claveMunicipio" name="claveMunicipio" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesByMunicipio();" value="%{claveMunicipio}"/>
		</div>
		<div class="clear"></div>
		<div id="recuperaLocalidadesByMunicipio" >
			<s:if test="editar == 3 && (idEstado != -1 && claveMunicipio != null)">
				<s:include value="/WEB-INF/paginas/auditores/localidades.jsp"></s:include>
			</s:if>
		</div>
	</s:if>