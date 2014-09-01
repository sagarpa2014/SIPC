<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/compradores.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:form action="registrarComprador" method="post" enctype="multipart/form-data" onsubmit="return chkCamposComprador();">
	<s:hidden id="idComprador" name="idComprador" value="%{idComprador}"/>
	<s:hidden id="idPerfil" name="idPerfil" value="%{#session.idPerfil}"/>
	<s:elseif test="editar==3||editar==4">	
		<s:hidden id="idRepresentante" name="idRepresentante" value="%{idRepresentante}"/>
		<s:hidden name="numCamposAnterior" id="numCamposAnterior" value="%{numCamposAnterior}"/>
	</s:elseif>
	<s:hidden id="editar" name="editar" value="%{editar}"/>
	<div id="dialogo_1"></div>
	<div class="borderBottom"><h1>Registro de Compradores</h1></div><br>
	<s:if test='#session.idPerfil==12'>
		<s:hidden name="compradorTemporal" id="compradorTemporal" value="%{1}"/>
	</s:if>
	<s:else>
		<s:hidden name="compradorTemporal" id="compradorTemporal" value="%{0}"/>
	</s:else>
	<fieldset>	
		<legend>DATOS DEL SOLICITANTE (PERSONA FISICA O MORAL)</legend>
		<div class="clear"></div>
		<s:if test='#session.idPerfil!=12'> <!-- Aplica solo para perfiles de  pagos -->
			<div>
				<label class="left1">N&uacute;mero de Folio:</label>
				<s:textfield name="folio" maxlength="30" size="10" id="folio" value="%{folio}"/>
			</div>
		</s:if>
		<div class="clear"></div>
		<div class="inline">
			<label class="left1">Tipo Persona:</label>
			<s:radio label="" onclick="datosFisComp()"  name="personaFiscal" list="#{'1':'FISICA','0':'MORAL'}" value="%{personaFiscal}" />
		</div>
		<div class="clear"></div>
		<div id="datosFiscales" >
			<s:if test="editar == 0 || editar == 3">
				<s:include value="datosFiscales.jsp"></s:include>	
			</s:if>
		</div>		
		<div class="clear"></div>
		<s:if test='#session.idPerfil!=12'> <!-- Aplica solo para perfiles de pagos -->
			<div>
				<label class="left1">Folio SURI:</label>
				<s:textfield name="idSuri" maxlength="12" size="15" id="idSuri" value="%{idSuri}"/>
			</div>
			<div class="clear"></div>
		</s:if>
		<div>
			<label class="left1">Telefono (Lada):</label>
			<s:textfield name="telefono" maxlength="50" size="30" id="telefono" value="%{telefono}"/>
		</div>
		<div class="clear"></div>
		<s:if test='#session.idPerfil!=12'> 
			<div>
				<label class="left1">Fax:</label>
				<s:textfield name="fax" maxlength="50" size="30" id="fax" value="%{fax}"/>
			</div>
		</s:if>
		<div class="clear"></div>
		<div>
			<label class="left1">Correo El&eacute;tronico:</label>
			<s:textfield name="correoElectronico" maxlength="100" size="50" id="correoElectronico" value="%{correoElectronico}"/>
		</div>
		<div class="clear"></div>
	</fieldset>
	<fieldset>
		<legend>DOMICILIO DEL SOLICITANTE</legend>
		<div>
			<label class="left1">Tipo Asentamiento Humano:</label>
			<s:select id="tipoAsentHumano" name="tipoAsentHumano" list="lstTiposAsentamiento" listKey="tipoAsentHumano" listValue="%{descripcion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  value="%{tipoAsentHumano}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Nombre Asentamiento Humano:</label>
			<s:textfield name="nombreAsentHumano" maxlength="200" size="100" id="nombreAsentHumano" value="%{nombreAsentHumano}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Tipo Vialidad:</label>
			<s:select id="tipoVialidad" name="tipoVialidad" list="lstTiposVialidad" listKey="tipoVialidad" listValue="%{descripcion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  value="%{tipoVialidad}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Nombre Vialidad:</label>
			<s:textfield name="nombreVialidad" maxlength="200" size="100" id="nombreVialidad" value="%{nombreVialidad}"/>
		</div>
		<div class="clear"></div>
		<div class="izquierda">
			<s:if test='#session.idPerfil==1'>
				<label class="left1">N&uacute;mero  :</label>
				<s:textfield name="numExterior" maxlength="30" size="10" id="numExterior" value="%{numExterior}"/>
			</s:if>
			<s:else>
				<label class="left1"><span class="requerido">*</span>N&uacute;mero Exterior:</label>
				<s:textfield name="numExterior" maxlength="30" size="10" id="numExterior" value="%{numExterior}"/>
			</s:else>
							
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero Exterior (2):</label>
			<s:textfield name="numeroExt2" maxlength="5" size="7" id="numeroExt2" value="%{numeroExt2}"/>
		</div>		
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero Interior:</label>
			<s:textfield name="numInterior" maxlength="30" size="10" id="numInterior" value="%{numInterior}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">N&uacute;mero Interior (2):</label>
			<s:textfield name="numeroInt" maxlength="5" size="7" id="numeroInt" value="%{numeroInt}"/>
		</div>			
		<div class="clear"></div>
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1">C&oacute;digo Postal:</label>
				<s:textfield name="codigoPostal" maxlength="9" size="10" id="codigoPostal" value="%{codigoPostal}"/>
			</s:if>
			<s:else>
				<label class="left1"><span class="requerido">*</span>C&oacute;digo Postal:</label>
				<s:textfield name="codigoPostal" maxlength="9" size="10" id="codigoPostal" value="%{codigoPostal}"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Referencia 1 (entre vialidades):</label>
			<s:textfield name="referencia1" maxlength="250" size="120" id="referencia1" value="%{referencia1}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Referencia 2 (vialidad posterior):</label>
			<s:textfield name="referencia2" maxlength="250" size="120" id="referencia2" value="%{referencia2}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Referencia 3 (descripción de la Ubicación):</label>
			<s:textfield name="referencia3" maxlength="250" size="120" id="referencia3" value="%{referencia3}"/>
		</div>			
		<div class="clear"></div>
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1">Estado:</label>
				<s:select id="idEstado" name="idEstado" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaMunicipioByEstado();" value="%{idEstado}"/>
			</s:if>	
			<s:else>
				<label class="left1"><span class="requerido">*</span>Estado:</label>
				<s:select id="idEstado" name="idEstado" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaMunicipioByEstado();" value="%{idEstado}"/>
			</s:else>	
		</div>
		<div class="clear"></div>
		<div id="recuperaMunicipioByEstado" >
			<s:if test="editar == 3">
				<s:include value="/WEB-INF/paginas/auditores/municipios.jsp"></s:include>	
			</s:if>
		</div>
		<div class="clear"></div>
<!-- 
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1">Colonia:</label>
				<s:textfield name="colonia" maxlength="100" size="50" id="nombre" value="%{colonia}"/>
			</s:if>
			<s:else>
				<label class="left1"><span class="requerido">*</span>Colonia:</label>
				<s:textfield name="colonia" maxlength="100" size="50" id="nombre" value="%{colonia}"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1">Calle:</label>
				<s:textfield name="calle" maxlength="60" size="50" id="calle" value="%{calle}"/>
			</s:if>	
			<s:else>
				<label class="left1"><span class="requerido">*</span>Calle:</label>
				<s:textfield name="calle" maxlength="60" size="50" id="calle" value="%{calle}"/>
			</s:else>
		</div>
		<div class="clear"></div>
-->		
	</fieldset>
	
	<s:if test='#session.idPerfil!=12'> <!-- Aplica solo para perfiles de pagos -->
		<fieldset>
			<legend>DATOS DEL REPRESENTANTE LEGAL</legend>
			<div>
				<label class="left1"><span class="requerido">*</span>N&uacute;mero(s) de Representante(s) legal(es):</label>
				<s:select id="numCampos" name="numCampos"  headerKey="-1" headerValue="-- Seleccione una opción --" 
				list="#{'1':'1', '2':'2', '3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" onchange="datosRepLegal()" value="%{numCampos}"/>
			</div>
			<div class="clear"></div>
			<div id="datosRepLegal">
				<s:if test="editar==3">
					<s:include value="datosRepLegal.jsp"></s:include>
				</s:if>		
			</div>
			<div class="clear"></div>
			<div class="inline">
				<label class="left1">Representante Legal:</label>
				<s:radio label="" name="representanteLegal" list="#{'1':'SI','0':'NO'}" value="%{representanteLegal}" />
			</div>	
			<div class="clear"></div>
			<!-- 
			<div class="inline">
				<label class="left1">Tipo Dictamen:</label>
				<s:radio label="" onclick="datosTiposDict()"  name="tipoDictamen" list="#{'1':'POSITIVO','0':'NEGATIVO'}" value="%{tipoDictamen}" />
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1">Vigencia:</label>
				<s:if test="%{vigencia==null}">
					<s:textfield name="vigencia" maxlength="10" size="10" id="vigencia" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield name="vigencia" maxlength="10" size="10" id="vigencia" value="%{getText('fecha1',{vigencia})}" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trgF" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
				<script type="text/javascript">
					<!--
						
						Calendar.setup({
							inputField     :    'vigencia',
							ifFormat       :    "%d/%m/%Y",
							button         :    'trgF',
							align          :    "br",
							singleClick    :    true
							});
						//-->
				<!--</script>			
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1">Consulta SAT:</label>
				<s:if test="%{consultaSat==null}">
					<s:textfield name="consultaSat" maxlength="10" size="10" id="consultaSat" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield name="consultaSat" maxlength="10" size="10" id="consultaSat" value="%{getText('fecha1',{consultaSat})}" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trgS" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
				<script type="text/javascript">
					<!--
						
						Calendar.setup({
							inputField     :    'consultaSat',
							ifFormat       :    "%d/%m/%Y",
							button         :    'trgS',
							align          :    "br",
							singleClick    :    true
							});
						//-->
				<!--</script>			
			</div>-->
		</fieldset>
	</s:if><!-- End aplica para perfil diferente de 12 DGPC -->
	<div class="clear"></div>
	<div class="derecha">
		<p><span class="requerido">*&nbsp;&nbsp;Indica que el campo es obligatorio</span></p>
	</div>	
	<div class="clear"></div>
	<div class="accion">
		<s:if test="editar == 0">
			<s:submit  value="Agregar Comprador" cssClass="boton2" />	
		</s:if>
		<s:else>
			<s:submit  value="Editar Comprador" cssClass="boton2" />
		</s:else>
	</div>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/catalogos/busquedaCompradores"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>		
</s:form>
