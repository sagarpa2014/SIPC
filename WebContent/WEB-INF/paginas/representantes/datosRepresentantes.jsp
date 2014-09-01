<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/compradores.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:form action="registrarRepLegal" method="post" enctype="multipart/form-data" onsubmit="return chkCamposRepresentante();">
<s:hidden id="idRepresentante" name="idRepresentante" value="%{idRepresentante}"/>
<s:hidden id="idPerfil" name="idPerfil" value="%{#session.idPerfil}"/>
<s:hidden id="editar" name="editar" value="%{editar}"/>
	<div id="dialogo_1"></div>
	<div class="borderBottom"><h1>Registro de Representante Legal</h1></div><br>
	<fieldset>	
		<legend>DATOS DEL REPRESENTANTE LEGAL</legend>
		<div class="izquierda">
			<label class="left1"><span class="requerido">*</span>Nombre:</label>
			<s:textfield name="nombre" maxlength="200" size="50" id="nombre" value="%{nombre}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Apellido Paterno:</label>
			<s:textfield name="apellidoPaterno" maxlength="200" size="50" id="apellidoPaterno" value="%{apellidoPaterno}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Apellido Materno:</label>
			<s:textfield name="apellidoMaterno" maxlength="200" size="50" id="apellidoMaterno" value="%{apellidoMaterno}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Fecha de Nacimiento:</label>
			<s:if test="%{fechaNacimiento==null}">
				<s:textfield name="fechaNacimiento" maxlength="10" size="10" id="fechaNacimiento" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield name="fechaNacimiento" maxlength="10" size="10" id="fechaNacimiento" value="%{getText('fecha1',{fechaNacimiento})}" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trgF" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
					
					Calendar.setup({
						inputField     :    'fechaNacimiento',
						ifFormat       :    "%d/%m/%Y",
						button         :    'trgF',
						align          :    "br",
						singleClick    :    true
						});
					//-->
			</script>
		</div>
		<div class="clear"></div>
		<div class="izquierda">
			<label class="left1">Entidad de Nacimiento:</label>
			<s:select id="entidadNacimiento" name="entidadNacimiento" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{entidadNacimiento}"/>
		</div>
		<div class="clear"></div>
		<div class="inline">
			<label class="left1">Sexo:</label>
			<s:radio label="" name="tipoSexo" list="#{'1':'Masculino','2':'Femenino'}" value="%{tipoSexo}" />
		</div>		
		<div class="clear"></div>
		<div class="izquierda">
			<label class="left1">CURP:</label>
			<s:textfield name="curp"  id="curp" maxlength="18" size="30" />
		</div>
		<div class="clear"></div>
		<div class="izquierda">
			<label class="left1"><span class="requerido">*</span>RFC:</label>
			<s:textfield name="rfc" id="rfc" maxlength="13" size="30" value="%{rfc}" onchange="validaRfc();"/>
		</div>
		<div id="validaRfc"></div>
		<div class="clear"></div>
		<div class="inline" >
				<label class="left1">Nacionalidad:</label>
				<s:radio  label="" name="nacionalidad" list="#{'1':'MEXICANA','2':'EXTRANJERA'}" value="%{nacionalidad}" />
		</div>
		<div class="clear"></div>
		<div class="izquierda">
			<label class="left1">Estado Civil:</label>
			<s:select id="estadoCivil" name="estadoCivil" list="lstEstadosCivil" listKey="estadoCivil" listValue="%{descripcion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  value="%{estadoCivil}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Tipo Identificaci&oacute;n:</label>
			<s:select id="tipoIdentificacion" name="tipoIdentificacion" list="lstTiposIdentificacion" listKey="tipoIdentificacion" listValue="%{descripcion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  value="%{tipoIdentificacion}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Folio Identificaci&oacute;n:</label>
			<s:textfield name="folioIdentificacion" maxlength="30" size="35" id="folioIdentificacion" value="%{folioIdentificacion}"/>
		</div>			
		<div class="clear"></div>
		<div>
			<label class="left1">RFE:</label>
			<s:textfield name="rfe" maxlength="18" size="20" id="rfe" value="%{rfe}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Documenta Acredita:</label>
			<s:textfield name="documentoAcredita" maxlength="200" size="100" id="documentoAcredita" value="%{documentoAcredita}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Vigencia del Poder:</label>
			<s:if test="%{vigenciaPoder==null}">
				<s:textfield name="vigenciaPoder" maxlength="10" size="10" id="vigenciaPoder" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield name="vigenciaPoder" maxlength="10" size="10" id="vigenciaPoder" value="%{getText('fecha1',{vigenciaPoder})}" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trgVP" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
					
					Calendar.setup({
						inputField     :    'vigenciaPoder',
						ifFormat       :    "%d/%m/%Y",
						button         :    'trgVP',
						align          :    "br",
						singleClick    :    true
						});
					//-->
			<!--</script>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Tel&eacute;fono(s):</label>
			<s:textfield name="telefonos" maxlength="50" size="30" id="telefonos" value="%{telefonos}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Fax:</label>
			<s:textfield name="fax" maxlength="50" size="30" id="fax" value="%{fax}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Correo El&eacute;tronico:</label>
			<s:textfield name="correoElectronico" maxlength="100" size="50" id="correoElectronico" value="%{correoElectronico}"/>
		</div>
		<div class="clear"></div>
	</fieldset>
	<fieldset>
		<legend>DOMICILIO DEL REPRESENTANTE LEGAL</legend>
		<div>
			<label class="left1">Estado</label>
			<s:select id="idEstado" name="idEstado" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaMunicipioByEstado();" value="%{idEstado}"/>
		</div>
		<div class="clear"></div>
		<div id="recuperaMunicipioByEstado" >
			<s:if test="editar == 3">
				<s:include value="/WEB-INF/paginas/auditores/municipios.jsp"></s:include>	
			</s:if>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Colonia:</label>
			<s:textfield name="colonia" maxlength="100" size="50" id="nombre" value="%{colonia}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Calle:</label>
			<s:textfield name="calle" maxlength="60" size="50" id="calle" value="%{calle}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero Exterior:</label>
			<s:textfield name="numExterior" maxlength="30" size="10" id="numExterior" value="%{numExterior}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero Interior:</label>
			<s:textfield name="numInterior" maxlength="30" size="10" id="numInterior" value="%{numInterior}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">C&oacute;digo Postal:</label>
			<s:textfield name="codigoPostal" maxlength="9" size="10" id="codigoPostal" value="%{codigoPostal}"/>
		</div>
		<div class="clear"></div>
	</fieldset>
	<div class="clear"></div>
	<div class="derecha">
		<p><span class="requerido">*&nbsp;&nbsp;Indica que el campo es obligatorio</span></p>
	</div>
	<div class="clear"></div>	
	<div class="accion" style="text-align:center">
		<s:if test="editar == 0">
			<s:submit  value="Agregar Representante" cssClass="boton2" />	
		</s:if>
		<s:else>
			<s:submit  value="Editar Representante" cssClass="boton2" />
		</s:else>
	</div>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/catalogos/busquedaRepLegal"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>