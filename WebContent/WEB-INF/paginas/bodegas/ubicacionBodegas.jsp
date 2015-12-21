<%@taglib uri="/struts-tags" prefix="s"%>
 <s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>	
<s:if test="errorClaveBodega==2">
	<div class="msjError1">No se encuentra el estado, cader y ddr capturado, por favor verifique</div>
</s:if>
<s:elseif test="errorClaveBodega==1">
	<div class="msjError1">La clave de bodega ya se encuentra registrada, por favor verifique</div>
</s:elseif>
<table class= "clean" style="width: 100%">
	<tr>
		<td colspan="2">
			<label class="left1">Estado:</label>
			<s:textfield disabled="true" id="nombreEstado" maxlength="50" size="50" value="%{caderV.nombreEstado}"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label class="left1">DDR:</label>
			<s:textfield disabled="true" id="ddr" maxlength="50" size="40" value="%{caderV.nombreDDR}"/>
		</td>
	</tr>
	<tr>		
		<td colspan="2">
			<label class="left1">CADER:</label>
			<s:textfield disabled="true" id="cader" maxlength="50" size="40" value="%{caderV.nombreCader}"/>		  			
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label class="left1"><span class="requerido">*</span>Municipio:</label>
			<s:if test="registrar == 2">
				<s:textfield  maxlength="50" size="40" value="%{bodegasV.nombreMunicipio}"/>
			</s:if>
			<s:else>
				<s:select id="claveMunicipio" name="claveMunicipio" list="lstMunicipiosUbicacionBodegas" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="" value="%{bodegasV.municipio}"/>			
			</s:else>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label class="left1"><span class="requerido">*</span>Ejido:</label>
			<s:if test="registrar==2">
				<s:textfield  maxlength="50" size="40" value="%{bodegasV.nombreEjido}"/>
			</s:if>
			<s:else>
				<s:select id="ejido" name="ejido" list="lstEjido" listKey="ejido" listValue="%{nombreEjido}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="();" value="%{bodegasV.ejido}"/>
			</s:else>
		</td>
	</tr>
</table>
			
		  