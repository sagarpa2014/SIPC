<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
	<fieldset class="clear">
		<legend>DATOS DEL SOLICITANTE (PERSONA FISICA O MORAL)</legend>
		<div>
			<label class="left1">Folio:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.folio}"/></font>
		</div>
		<div>
			<label class="left1">Estatus:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.descEstatus}"/></font>
		</div>
		<s:if test="%{compradoresV.estatus == 0}">
			<div>
				<label class="left1">Motivos Inhabilitaci&oacute;n:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.motivoInhabilitado}"/></font>
			</div>
		</s:if>
		<s:if test="%{personaFiscal == 1}">
			<div>
				<label class="left1">Nombre:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.nombre}"/></font>
			</div>
			<div>
				<label class="left1">CURP:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.curp}"/></font>
			</div>
			<div>
				<label class="left1">RFC:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.rfc}"/></font>
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
				<s:elseif test="%{tipoSexo==2}">
					<font class="arial12bold">Femenino</font>
				</s:elseif>
				<s:else>
					<font class="arial12bold"></font>
				</s:else>
			</div>
			<div>
				<label class="left1">Nacionalidad:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.descNacionalidad}"/></font>
			</div>
			<div>
				<label class="left1">Estado Civil:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.descEstadoCivil}"/></font>
			</div>
			<div>
				<label class="left1">Tipo Identificaci&oacute;n:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.descTipoIdentificacion}"/></font>
			</div>
			<div>
				<label class="left1">Folio Identificaci&oacute;n:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.folioIdentificacion}"/></font>
			</div>
			<div> 
				<label class="left1">RFE:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.rfe}"/></font>
			</div>
		</s:if>
		<s:else>
			<div>
				<label class="left1">RFC:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.rfc}"/></font>
			</div>
			<div>
				<label class="left1">Nombre:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.nombre}"/></font>
			</div>
			<div>
				<label class="left1">Fecha de Constituci&oacute;n:</label>
				<s:if test="%{compradoresV.fechaNacimiento != null}">
					<font class="arial12bold"><s:text name="fecha"><s:param value="%{compradoresV.fechaNacimiento}"/></s:text></font>
				</s:if>	
			</div>			
		</s:else>
		<div>
			<label class="left1">Fecha de Inscripci&oacute;n al RFC:</label>
			<font class="arial12bold"><s:property value="%{getText('fecha1',{compradoresV.fechaInscripcionRFC})}"/></font>
		</div>	
		<div>
			<label class="left1">Folio SURI:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.idSuri}"/></font>
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
		<div class="clear"></div>
	</fieldset>
	<fieldset class="clear">
		<legend>DOMICILIO DEL SOLICITANTE</legend>
		<div>
			<label class="left1">Tipo Asentamiento Humano:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.descTipoAsentHumano}"/></font>
		</div>
		<div>
			<label class="left1">Nombre Asentamiento Humano:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.nombreAsentHumano}"/></font>
		</div>
		<div>
			<label class="left1">Tipo Vialidad:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.descTipoVialidad}"/></font>
		</div>
		<div>
			<label class="left1">Nombre Vialidad:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.nombreVialidad}"/></font>
		</div>
		<div>
			<label class="left1">N&uacute;mero Exterior:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.numExterior}"/></font>	
		</div>
		<div>
			<label class="left1">N&uacute;mero Exterior (2):</label>
			<font class="arial12bold"><s:property value="%{compradoresV.numeroExt2}"/></font>
		</div>	
		<div>
			<label class="left1">N&uacute;mero Interior:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.numInterior}"/></font>	
		</div>
		<div>
			<label class="left1">N&uacute;mero Interior (2):</label>
			<font class="arial12bold"><s:property value="%{compradoresV.numeroInt}"/></font>
		</div>			
		<div>
			<label class="left1">C&oacute;digo Postal:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.codigoPostal}"/></font>
		</div>		
		<div>
			<label class="left1">Referencia 1 (entre vialidades):</label>
			<font class="arial12bold"><s:property value="%{compradoresV.referencia1}"/></font>
		</div>
		<div>
			<label class="left1">Referencia 2 (vialidad posterior):</label>
			<font class="arial12bold"><s:property value="%{compradoresV.referencia2}"/></font>
		</div>
		<div>
			<label class="left1">Referencia 3 (descripción de la Ubicación):</label>
			<font class="arial12bold"><s:property value="%{compradoresV.referencia3}"/></font>
		</div>
		<div>
			<label class="left1">Estado:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.estado}"/></font>	
		</div>
			<div>
			<label class="left1">Municipio:</label>
			<font class="arial12bold"><s:property value="%{compradoresV.municipio}"/></font>	
		</div>
		<s:if test="%{representanteLegalV.localidad != null}">	
			<div>
				<label class="left1">Localidad:</label>
				<font class="arial12bold"><s:property value="%{compradoresV.localidad}"/></font>	
			</div>
		</s:if>
	</fieldset>