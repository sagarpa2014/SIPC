<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/bodegas.js" />"></script>
	<s:if test='%{clienteParticipante.tipoPersona=="F"}'>
		<div class="izquierda">
			<label class="left1"><span class="requerido">*</span>RFC:</label>
			<s:if test="registrar==0">
				<s:textfield name="clienteParticipante.rfc" id="rfc" maxlength="13" size="30" value="%{clienteParticipante.rfc}"  onkeyup ="convierteAmayuscula('rfc');" onchange="validarRfc('rfc', 'validarRfcClienteParticipante', 'validarRfcClienteParticipante');"/>
			</s:if>
			<s:else>
				<s:textfield name="clienteParticipante.rfc" id="rfc" maxlength="13" size="30" value="%{clienteParticipante.rfc}"  disabled="true"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Nombre:</label>
			<s:textfield name="clienteParticipante.nombre" maxlength="200" size="50" id="nombre" value="%{clienteParticipante.nombre}" onkeyup ="convierteAmayuscula('nombre');"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Apellido Paterno:</label>
			<s:textfield name="clienteParticipante.apellidoPaterno" maxlength="200" size="50" id="apellidoPaterno" value="%{clienteParticipante.apellidoPaterno}" onkeyup ="convierteAmayuscula('apellidoPaterno');"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Apellido Materno:</label>
			<s:textfield name="clienteParticipante.apellidoMaterno" maxlength="200" size="50" id="apellidoMaterno" value="%{clienteParticipante.apellidoMaterno}" onkeyup ="convierteAmayuscula('apellidoMaterno');" />
		</div>
		<div class="clear"></div>
		<div class="izquierda">
			<label class="left1">CURP:</label>
			<s:textfield name="clienteParticipante.curp"  id="curp" maxlength="18" size="30" value="%{clienteParticipante.curp}" onkeyup ="convierteAmayuscula('curp');"/>
		</div>		
	</s:if>	<!-- End persona Fisica -->
	<s:else><!-- PERSONA MORAL -->		
		<div id="rfc">
			<label class="left1"><span class="requerido">*</span>RFC:</label>
			<s:if test="registrar==0">
				<s:textfield name="clienteParticipante.rfc" id="rfc" maxlength="13" size="30" value="%{clienteParticipante.rfc}"  onkeyup ="convierteAmayuscula('rfc');" onchange="validarRfc('rfc', 'validarRfcClienteParticipante', 'validarRfcClienteParticipante');"/>
			</s:if>
			<s:else>
				<s:textfield name="clienteParticipante.rfc" id="rfc" maxlength="13" size="30" value="%{clienteParticipante.rfc}"  disabled="true"/>
			</s:else>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Nombre:</label>
			<s:textfield name="clienteParticipante.nombre" maxlength="200" size="50" id="nombre" value="%{clienteParticipante.nombre}" onkeyup ="convierteAmayuscula('nombre');"/>
		</div>		 
		<div class="clear"></div>		
<!-- 	</div> -->
	</s:else>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Telefono (Lada):</label>
		<s:textfield name="clienteParticipante.telefonos" maxlength="50" size="30" id="telefono" value="%{clienteParticipante.telefonos}"/>
	</div>
	<div class="clear"></div>	