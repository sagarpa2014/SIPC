<%@taglib uri="/struts-tags" prefix="s"%>
	<s:if test="%{idEstado != null}">
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1">Municipio:</label>
				<s:select id="claveMunicipio" name="claveMunicipio" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesByMunicipio();" value="%{claveMunicipio}"/>
			</s:if>	
			<s:else>
				<label class="left1"><span class="requerido">*</span>Municipio:</label>
				<s:select id="claveMunicipio" name="claveMunicipio" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesByMunicipio();" value="%{claveMunicipio}"/>
			</s:else>	
		</div>		
		<div class="clear"></div>
		<div id="recuperaLocalidadesByMunicipio" >
			<s:if test="editar == 3 && (idEstado != -1 && claveMunicipio != null)">
				<s:include value="/WEB-INF/paginas/auditores/localidades.jsp"></s:include>
			</s:if>
		</div>
	</s:if>