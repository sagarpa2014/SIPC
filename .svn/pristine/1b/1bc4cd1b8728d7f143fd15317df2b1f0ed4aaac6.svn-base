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
	<fieldset>	
		<legend>Captura de Compradores</legend>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>N&uacute;mero de Folio:</label>
			<s:textfield name="folio" maxlength="30" size="10" id="numInterior" value="%{folio}"/>
		</div>
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
		<br>
		<div class="clear"></div>
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1"><span class="norequerido">*</span>Estado</label>
				<s:select id="idEstado" name="idEstado" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaMunicipioByEstado();" value="%{idEstado}"/>
			</s:if>	
			<s:else>
				<label class="left1"><span class="requerido">*</span>Estado</label>
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
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1"><span class="norequerido">*</span>Colonia:</label>
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
				<label class="left1"><span class="norequerido">*</span>Calle:</label>
				<s:textfield name="calle" maxlength="60" size="50" id="calle" value="%{calle}"/>
			</s:if>	
			<s:else>
				<label class="left1"><span class="requerido">*</span>Calle:</label>
				<s:textfield name="calle" maxlength="60" size="50" id="calle" value="%{calle}"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1"><span class="norequerido">*</span>N&uacute;mero Exterior:</label>
				<s:textfield name="numExterior" maxlength="30" size="10" id="numExterior" value="%{numExterior}"/>
			</s:if>
			<s:else>
				<label class="left1"><span class="requerido">*</span>N&uacute;mero Exterior:</label>
				<s:textfield name="numExterior" maxlength="30" size="10" id="numExterior" value="%{numExterior}"/>			
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>N&uacute;mero Interior:</label>
			<s:textfield name="numInterior" maxlength="30" size="10" id="numInterior" value="%{numInterior}"/>
		</div>
		<div class="clear"></div>
		<div>
			<s:if test='#session.idPerfil==1'>
				<label class="left1"><span class="norequerido">*</span>C&oacute;digo Postal:</label>
				<s:textfield name="codigoPostal" maxlength="9" size="10" id="codigoPostal" value="%{codigoPostal}"/>
			</s:if>
			<s:else>
				<label class="left1"><span class="requerido">*</span>C&oacute;digo Postal:</label>
				<s:textfield name="codigoPostal" maxlength="9" size="10" id="codigoPostal" value="%{codigoPostal}"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Telefono:</label>
			<s:textfield name="telefono" maxlength="50" size="30" id="telefono" value="%{telefono}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Fax:</label>
			<s:textfield name="fax" maxlength="50" size="30" id="fax" value="%{fax}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Correo El&eacute;tronico:</label>
			<s:textfield name="correoElectronico" maxlength="100" size="50" id="correoElectronico" value="%{correoElectronico}"/>
		</div>


	</fieldset>
	<div class="accion" style="text-align:center left">
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
