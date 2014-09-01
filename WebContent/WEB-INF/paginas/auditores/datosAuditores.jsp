<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/auditores.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:form action="registrarAuditor" method="post" enctype="multipart/form-data" onsubmit="return chkCamposAuditor();">
<s:hidden id="editar" name="editar" value="%{editar}"/>
	<div id="dialogo_1"></div>
	<div class="borderBottom"><h1>Registro de Auditores</h1></div><br>
	<fieldset>	
		<legend>Captura de Auditores</legend>
		<div>
			<label class="left1"><span class="requerido">*</span>N&uacute;mero de Auditor:</label>
			<s:textfield name="numeroAuditor"  id="numeroAuditor" maxlength="10" size="10" value="%{numeroAuditor}" onchange="validarNumeroAuditor();"/>
		</div>
		<div class="clear"></div>
		<div id="validarNumeroAuditor"></div>		
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Nombre de Auditor:</label>
			<s:textfield name="nombre" id="nombre" maxlength="100" size="100"  />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>N&uacute;mero de Registro Despacho:</label>
			<s:textfield name="numeroRegistroDespacho"  id="numeroRegistroDespacho" maxlength="10" size="10" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Despacho:</label>
			<s:textfield name="despacho" id="despacho" maxlength="200" size="100"  />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Estado</label>
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
			<label class="left1"><span class="norequerido">*</span>Colonia:</label>
			<s:textfield name="colonia" id="colonia" maxlength="50" size="50" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Calle:</label>
			<s:textfield name="calle" id="calle" maxlength="100" size="100" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>C&oacute;digo Postal:</label>
			<s:textfield name="codigoPostal" id="codigoPostal" maxlength="8" size="8" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Tel&eacute;fono(s):</label>
			<s:textfield name="telefonos" id="telefonos" maxlength="50" size="50"  />
		</div>
		<div class="clear"></div>
	</fieldset>
	<div class="accion" style="text-align:center left">
		<s:if test="editar == 0">
			<s:submit value="Agregar Auditor" cssClass="boton2"/>	
		</s:if>
		<s:else>
			<s:submit value="Editar Auditor" cssClass="boton2"/>
		</s:else>
	</div>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/catalogos/busquedaAuditores"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>

<s:if test="editar == 3">  <script>
             $(document).ready(function() {    
				$("#numeroAuditor").attr('disabled','disabled');   
             });    
       </script>
</s:if>
