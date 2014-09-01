<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/compradores.js" />"></script>
	<s:if test="personaFiscal==1">
		<div>
			<label class="left1"><span class="requerido">*</span>RFC</label>
			<s:textfield id="rfcc" name="rfc"  maxlength="13" size="30"  value="%{rfc}" onchange="validarRfc();"/>
		</div>
		<div class="clear"></div>
			<div id="validarRfc"></div> 
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>CURP</label>
			<s:textfield id="curpc" name="curp"  maxlength="18" size="30"  value="%{curp}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Apellido Paterno:</label>
			<s:textfield name="apellidoPaterno" maxlength="200" size="50" id="apellidoPaterno" value="%{apellidoPaterno}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Apellido Materno:</label>
			<s:textfield name="apellidoMaterno" maxlength="200" size="50" id="apellidoMaterno" value="%{apellidoMaterno}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Nombre:</label>
			<s:textfield name="nombre" maxlength="200" size="50" id="nombre" value="%{nombre}"/>
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
		<div>
			<label class="left1"><span class="requerido">*</span>Entidad de Nacimiento:</label>
			<s:select id="entidadNacimiento" name="entidadNacimiento" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{entidadNacimiento}"/>
		</div>
		<div class="clear"></div>
		<div class="inline">
			<label class="left1"><span class="norequerido">*</span>Sexo:</label>
			<s:radio label="" name="tipoSexo" list="#{'1':'Masculino','2':'Femenino'}" value="%{tipoSexo}"/>
		</div>	
		<div class="clear"></div>
	</s:if>	
	<s:else>
		<div id="rfc">
			<label class="left1"><span class="requerido">*</span>RFC</label>
			<s:textfield id="rfcc" name="rfc"  maxlength="13" size="30"  value="%{rfc}" onchange="validarRfc();"/>
		</div>
		<div class="clear"></div>
			<div id="validarRfc"></div> 
		<div class="clear"></div>	
		<div>
			<label class="left1"><span class="requerido">*</span>Nombre del Comprador:</label>
			<s:textfield name="nombre" maxlength="200" size="50" id="nombre" value="%{nombre}"/>
		</div>
	</s:else>