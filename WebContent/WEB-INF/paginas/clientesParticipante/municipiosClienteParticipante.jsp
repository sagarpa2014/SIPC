<%@taglib uri="/struts-tags" prefix="s"%>
<label class="left1">Municipio:</label>
<s:select name="clienteParticipante.claveMunicipio" id="claveMunicipio" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesPorMunicipio('idEstado','claveMunicipio', 'recuperaLocalidadesPorMunicipioClienterPar', 'recuperaLocalidadesPorMunicipioClienterPar');" value="%{clienteParticipante.claveMunicipio}"/>

			       