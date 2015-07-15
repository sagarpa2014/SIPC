<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/clienteParticipante.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:form action="registrarClienteParticipante" method="post" enctype="multipart/form-data" onsubmit="return chkCamposClienteParticipante();">
<s:hidden id="idCliente" name="idCliente" value="%{idCliente}"/>
<s:hidden id="idPerfil" name="idPerfil" value="%{#session.idPerfil}"/>
<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<div id="dialogo_1"></div>
	<div class="borderBottom"><h1>Registro de Cliente del Participante</h1></div><br>
	<fieldset>	
		<legend>DATOS GENERALES</legend>
		<div class="inline" id="contTipoPersona">
			<label class="left1">Tipo Persona:</label>
			<s:radio label="" onclick="recuperaSeleccionTipoPersonaFiscalCliente()"  id="tipoPersonaFiscalCliente" name="tipoPersonaFiscalCliente" list="#{'F':'FISICA','M':'MORAL'}" value="%{tipoPersonaFiscalCliente}" />
		</div>
		<div class="clear"></div>
		<div id="datosClienteFiscales">		
			<s:include value="datosFiscales.jsp"/>
		</div>
	</fieldset>
	<fieldset>
		<legend>DOMICILIO</legend>
		<div>
			<label class="left1"><span class="requerido">*</span>Estado</label>
			<s:select id="idEstado" name="clienteParticipante.idEstado" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaMunicipioPorEstado('idEstado', 'recuperaMunicipioByEstadoClientePar', 'recuperaMunicipioByEstadoClientePar');" value="%{clienteParticipante.idEstado}"/>
		</div>
		<div class="clear"></div>
		<div id="recuperaMunicipioByEstadoClientePar">
			<label class="left1"><span class="requerido">*</span>Municipio:</label>
			<s:select name="clienteParticipante.claveMunicipio" id="claveMunicipio" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesPorMunicipio('idEstado','claveMunicipio', 'recuperaLocalidadesPorMunicipioRLBodega', 'recuperaLocalidadesPorMunicipioRLBodega');" value="%{clienteParticipante.claveMunicipio}"/>			
		</div>
		<div class="clear"></div>
		<div id="recuperaLocalidadesPorMunicipioClienterPar">
			<label class="left1">Localidad:</label>
			<s:select name="clienteParticipante.claveLocalidad" id="claveLocalidad" list="lstLocalidades"  listKey="claveLocalidad" listValue="%{nombreLocalidad}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{clienteParticipante.claveLocalidad}"/>			
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Colonia:</label>
			<s:textfield name="clienteParticipante.colonia" maxlength="100" size="50" id="colonia" value="%{clienteParticipante.colonia}" onkeyup ="convierteAmayuscula('colonia');" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Calle:</label>
			<s:textfield name="clienteParticipante.calle" maxlength="60" size="50" id="calle" value="%{clienteParticipante.calle}" onkeyup ="convierteAmayuscula('calle');" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>N&uacute;mero Exterior:</label>
			<s:textfield name="clienteParticipante.numExterior" maxlength="30" size="10" id="numExterior" value="%{clienteParticipante.numExterior}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero Interior:</label>
			<s:textfield name="clienteParticipante.numInterior" maxlength="30" size="10" id="numInterior" value="%{clienteParticipante.numInterior}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>C&oacute;digo Postal:</label>
			<s:textfield name="clienteParticipante.codigoPostal" maxlength="9" size="10" id="codigoPostal" value="%{clienteParticipante.codigoPostal}"/>
		</div>
		<div class="clear"></div>
	</fieldset>
	<div class="clear"></div>
	<div class="derecha">
		<p><span class="requerido">*&nbsp;&nbsp;Indica que el campo es obligatorio</span></p>
	</div>
	<div class="clear"></div>	
	<div class="accion" style="text-align:center">
		<s:if test="registrar != 2">
			<s:submit  value="Registrar Cliente" cssClass="boton2" />	
		</s:if>
	</div>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/catalogos/busquedaClienteParticipante"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>

<s:if test="registrar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');				
		});		
	</script>
</s:if>	
<s:if test="registrar == 1"> <!-- Edicion -->
	<script>
		$(document).ready(function() {	
			$('input[name=tipoPersonaFiscalCliente]').attr("disabled",true);		
		});	
	</script>
</s:if>