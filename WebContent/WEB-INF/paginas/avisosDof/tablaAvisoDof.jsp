<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<div id="">
<form>
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/> 
	<s:hidden id="id" name="id" value="%{id}"/>
	<fieldset> 
		<legend>Montos del Aviso </legend>
		<div>
			<label class="left4"><span class="requerido">*</span>Programa:</label>
			<s:select id="idPgrAv" name="" list="lstPgrAviso" style="width:200px" listKey="id" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{}" onchange="" />
		</div>
		<div class="clear"></div>	    
		<div>
			<label class="left4"><span class="requerido">*</span>Cultivo:</label>
			<s:select id="idCultivo" cssClass="a" name="" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idCultivo}" onchange="recuperaVariedadByCultivoEdoAsigCA(this.value, %{count});" />
		</div>
		<div class="clear"></div>	    
		<div>
			<label class="left4"><span class="requerido">*</span>Ciclo:</label>
			<s:select id="ciclo" name="" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'PV':'PV', 'OI':'OI'}"  onchange="" value="%{bodegasV.piso}"/>
		</div>		
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Ejercicio:</label>
			<s:select id="ejercicio" name="" list="lstEjercicios" style="width:200px" listKey="ejercicio" listValue="%{ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{}" onchange="" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left4"><span class="requerido">*</span>Estado:</label>
			<s:select id="idEstado" name="" list="lstEstados" style="width:200px" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{rv.idCliente}" onchange="" />
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
			<label class="left4"><span class="requerido">*</span>Importe:</label>
			<s:if test="rv.volumen!=null">
				<s:textfield id="importeC" name="" maxlength="15" size="20"  cssClass="cantidad" onblur="validarImporte(this.value, 'importeC', 1);" value="%{getText('volumenSinComas',{rv.volumen})}"/>
			</s:if>
			<s:else>
				<s:textfield id="importeC" name="" maxlength="15" size="20"  cssClass="cantidad" onblur="validarImporte(this.value, 'importeC', 1);" value=""/>
			</s:else>
		</div>
		<div id="repuestaRegistroAvisosDof"></div>		    
	</fieldset>  
</form>
</div>
