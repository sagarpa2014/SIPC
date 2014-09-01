<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<fieldset class="clear">
	<legend>Datos del Representante Legal</legend>
	<div>
		<label class="left1">RFC:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.rfc}"/></font>	
	</div>
		<s:if test="%{representanteLegalV.curp != null}">
			<div>
				<label class="left1">CURP:</label>
				<font class="arial12bold"><s:property value="%{representanteLegalV.curp}"/></font>	
			</div>
		</s:if>
	<div>
		<label class="left1">Nombre:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.nombre}"/></font>
	</div>
	<div>
		<label class="left1">Fecha de Nacimiento:</label>
		<s:if test="%{representanteLegalV.fechaNacimiento != null}">
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{representanteLegalV.fechaNacimiento}"/></s:text></font>
		</s:if>	
	</div>
	<div>
		<label class="left1">Entidad de Nacimiento:</label>
		<s:if test="%{representanteLegalV.estadoNacimiento != null}">
			<font class="arial12bold"><s:property value="%{representanteLegalV.estadoNacimiento}"/></font>
		</s:if>
	</div>
	<div>
		<label class="left1">Sexo:</label>
			<s:if test="%{tipoSexo==1}">
				<font class="arial12bold">Masculino</font>
			</s:if>
			<s:elseif test="%{tipoSexo==2}">
				<font class="arial12bold">Femenino</font>
			</s:elseif>
			<s:elseif test="%{tipoSexo==0}">
				<font class="arial12bold"></font>
			</s:elseif>
	</div>	
	<div>
		<label class="left1">Estado:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.estado}"/></font>	
	</div>
		<div>
		<label class="left1">Municipio:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.municipio}"/></font>	
	</div>
	<s:if test="%{representanteLegalV.localidad != null}">	
		<div>
			<label class="left1">Localidad :</label>
			<font class="arial12bold"><s:property value="%{representanteLegalV.localidad}"/></font>	
		</div>
	</s:if>		
	<s:if test="%{representanteLegalV.colonia != null}">		
		<div>
			<label class="left1">Colonia:</label>
			<font class="arial12bold"><s:property value="%{representanteLegalV.colonia}"/></font>
		</div>
	</s:if>	
	<div>
		<label class="left1">Calle:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.calle}"/></font>	
	</div>
	<div>
		<label class="left1">N&uacute;mero Exterior:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.numExterior}"/></font>	
	</div>
	<div>
		<label class="left1">N&uacute;mero Interior:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.numInterior}"/></font>	
	</div>
	<div>
		<label class="left1">C&oacute;digo Postal:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.codigoPostal}"/></font>
	</div>
	<div>
		<label class="left1">Tel&eacute;fono(s):</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.telefono}"/></font>	
	</div>
	<div>
		<label class="left1">Fax:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.fax}"/></font>	
	</div>
	<div>
		<label class="left1">Correo Electronico:</label>
		<font class="arial12bold"><s:property value="%{representanteLegalV.correoElectronico}"/></font>	
	</div>
	
</fieldset>