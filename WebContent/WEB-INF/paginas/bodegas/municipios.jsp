<%@taglib uri="/struts-tags" prefix="s"%>
<label class="left1"><span class="requerido">*</span>Municipio:</label>
<s:select id="claveMunicipioDomFis" name="claveMunicipioDomFis" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesByMunicipioBodegas('idEstadoDomFiscal','claveMunicipioDomFis');" value="%{claveMunicipio}"/>		
		

	

	