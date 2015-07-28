<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<div id="">
<form>
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/> 
	<s:hidden id="idRelVentas" name="idRelVentas" value="%{idRelVentas}"/>
	<fieldset> 
		<legend>Captura de la Relación de Ventas</legend>
		<div>
			<label class="left4"><span class="requerido">*</span>Cliente:</label>
			<s:select id="idClienteC" name="" list="lstClienteDeParticipante" style="width:200px" listKey="id" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{rv.idCliente}" onchange="" />
		</div>
		<div class="clear"></div>	    
		<div>
			<label class="left4"><span class="requerido">*</span>Folio:</label>
			<s:textfield id="folioFacturaC" name="" maxlength="35" size="30" value="%{rv.folioFactura}" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Fecha:</label>
			<s:if test="%{rv.fecha==null}" >
				<s:textfield  name ="" size="12" id="fechaFacturaC"  readonly="true"/>
			</s:if>
			<s:else>
				<s:textfield  name ="" size="12" id="fechaFacturaC"  value="%{getText('fecha1',{rv.fecha})}" readonly="true"/>
			</s:else>			
			<script>
				$(function() {
					$( '#fechaFacturaC').datepicker({
						dateFormat: 'dd/mm/yy'
					});
				});
			</script>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Volumen:</label>
			<s:if test="rv.volumen!=null">
				<s:textfield id="volumenC" name="" maxlength="15" size="20"  cssClass="cantidad" onblur="validarVolumen(this.value, 'volumenC', 1);" value="%{getText('volumenSinComas',{rv.volumen})}"/>
			</s:if>
			<s:else>
				<s:textfield id="volumenC" name="" maxlength="15" size="20"  cssClass="cantidad" onblur="validarVolumen(this.value, 'volumenC', 1);" value=""/>
			</s:else>
			
		</div>
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Aplica Internacional:</label>
			<s:checkbox id="idChkDestino"  name=""  fieldValue="true"  onclick="consigueDestinoRelVentas();"  value="%{rv.internacional}" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Destino:</label>
			<div id="contenedorDestino">
				<s:if test="%{rv.internacional == true}">
					<s:select id="idDestinoC" style="width:200px" name="" list="lstDestinoInternacional" listKey="idPais" listValue="%{pais}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{rv.idPais}"/>
				</s:if>
				<s:else>
					<s:select id="idDestinoC" style="width:200px" name="" list="lstDestinoNacional" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{rv.idEstado}"/>
				</s:else>
			</div>					
		</div>
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Uso del Grano:</label>
			<s:select id="idUsoGranoC" style="width:200px" name="" list="lstTipoUsoGrano" listKey="id" listValue="%{usoGrano}" headerKey="-1" headerValue="--Seleccione una opción--" tabindex="0" value="%{rv.idUsoGrano}"/>
		</div>
		<div class="clear"></div>
		<div id="repuestaRegistroRelVentas"></div>		    
	</fieldset>  
</form>
</div>
