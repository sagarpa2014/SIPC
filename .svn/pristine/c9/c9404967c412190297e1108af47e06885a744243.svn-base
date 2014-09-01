<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
<fieldset class="clear">
	<legend>Datos del Comprador</legend>
	<div>
		<label class="left1">Folio</label>
		<font class="arial12bold"><s:property value="%{compradoresV.folio}"/></font>
	</div>
	<s:if test="%{personaFiscal == 1}">
		<div>
			<label class="left1">RFC</label>
			<font class="arial12bold"><s:property value="%{compradoresV.rfc}"/></font>
		</div> 
		<div>
			<label class="left1">CURP</label>
			<font class="arial12bold"><s:property value="%{compradoresV.curp}"/></font>
		</div>
		<div>
			<label class="left1">Apellido Paterno:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.apellidoPaterno}"/></font>
		</div>
		<div>
			<label class="left1">Apellido Materno:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.apellidoMaterno}"/></font>
		</div>
		<div>
			<label class="left1">Nombre:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.nombre}"/></font>
		</div>
		<div>
			<label class="left1">Fecha de Nacimiento:</label>
			<s:if test="%{compradoresV.fechaNacimiento != null}">
				<font class="arial12bold"><s:text name="fecha"><s:param value="%{compradoresV.fechaNacimiento}"/></s:text></font>
			</s:if>	
		</div>
		<div>
			<label class="left1">Entidad de Nacimiento:</label>
			<s:if test="%{compradoresV.estadoNacimiento != null}">
				<font class="arial12bold"><s:property value="%{compradoresV.estadoNacimiento}"/></font>
			</s:if>
		</div>
		<div>
			<label class="left1">Sexo:</label>
			<s:if test="%{tipoSexo==1}">
				<font class="arial12bold">Masculino</font>
			</s:if>
			<s:else>
				<font class="arial12bold">Femenino</font>
			</s:else>
		</div>
	</s:if>
	<s:else>
		<div>
			<label class="left1">RFC:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.rfc}"/></font>
		</div>
		<div>
			<label class="left1">Nombre de Comprador:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.nombre}"/></font>
		</div>
	</s:else>
	<div>
		<label class="left1">Estado</label>
		<font class="arial12bold"><s:property value="%{compradoresV.estado}"/></font>	
	</div>
		<div>
		<label class="left1">Municipio</label>
		<font class="arial12bold"><s:property value="%{compradoresV.municipio}"/></font>	
	</div>
	<s:if test="%{representanteLegalV.localidad != null}">	
		<div>
			<label class="left1">Localidad :</label>
			<font class="arial12bold"><s:property value="%{compradoresV.localidad}"/></font>	
		</div>
	</s:if>		
	<s:if test="%{representanteLegalV.colonia != null}">		
		<div>
			<label class="left1">Colonia:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.colonia}"/></font>
		</div>
	</s:if>
	<div>
		<label class="left1">Calle:</label>
		<font class="arial12bold"><s:property value="%{compradoresV.calle}"/></font>	
	</div>
	<div>
		<label class="left1">N&uacute;mero Exterior:</label>
		<font class="arial12bold"><s:property value="%{compradoresV.numExterior}"/></font>	
	</div>
	<div>
		<label class="left1">N&uacute;mero Interior:</label>
		<font class="arial12bold"><s:property value="%{compradoresV.numInterior}"/></font>	
	</div>
	<div>
		<label class="left1">C&oacute;digo Postal:</label>
		<font class="arial12bold"><s:property value="%{compradoresV.codigoPostal}"/></font>
	</div>
	<div>
		<label class="left1">Tel&eacute;fono(s):</label>
		<font class="arial12bold"><s:property value="%{compradoresV.telefono}"/></font>	
	</div>
	<div>
		<label class="left1">Fax:</label>
		<font class="arial12bold"><s:property value="%{compradoresV.fax}"/></font>	
	</div>
	<div>
		<label class="left1">Correo Electronico:</label>
		<font class="arial12bold"><s:property value="%{compradoresV.correoElectronico}"/></font>	
	</div>
	
</fieldset>