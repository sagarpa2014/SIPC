<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorClaveBodega==1}">
	<div class="msjError1">clave inexistente</div>
</s:if>
<div id="errorCB" class="elementoOculto"><s:property value="%{errorClaveBodega}"/></div>
<%-- <div id="nombreCentroAcopioR" class="elementoOculto"><s:property value="%{nombreCentroAcopio}"/></div> --%>
<%-- <div id="nomRegionalCentroAcopioR" class="elementoOculto"><s:property value="%{nomRegionalCentroAcopio}"/></div> --%>
<%-- <div id="operadorCentroAcopioR" class="elementoOculto"><s:property value="%{operadorCentroAcopio}"/></div> --%>

<div id="existenciaAMR" class="elementoOculto"><s:property value="%{existenciaAMAnt}"/></div>
<div>
	<label class="left1">Nombre o Razón Social del Centro de Acopio Registrado en ASERCA:</label>
	<s:if test="%{nombreCentroAcopio!=null && nombreCentroAcopio != ''}">
		<s:textfield id="nombreCentroAcopio" name="nombreCentroAcopio" maxlength="15" size="80"  value="%{nombreCentroAcopio}" onchange="" disabled="true"/>
	</s:if>
	<s:else>
		<s:textfield id="nombreCentroAcopio" name="nombreCentroAcopio" maxlength="15" size="80"  value="%{}" onchange="" disabled="true"/>
	</s:else>
</div>
<div class="clear"></div>
<div>
	<label class="left1">Nombre Local o Regional del Centro de Acopio:</label>
	<s:if test="%{nomRegionalCentroAcopio!=null && nomRegionalCentroAcopio != ''}">
		<s:textfield id="nomRegionalCentroAcopio" name="nomRegionalCentroAcopio" maxlength="15" size="80"  value="%{nomRegionalCentroAcopio}" onchange="" disabled="true"/>	
	</s:if>
	<s:else>
		<s:textfield id="nomRegionalCentroAcopio" name="nomRegionalCentroAcopio" maxlength="15" size="80"  value="%{}" onchange="" disabled="true"/>
	</s:else>
</div>
<div class="clear"></div>
<div>
	<label class="left1">Nombre o Razón Social del Operador del Centro de Acopio:</label>
	<s:if test="%{lstObv.size() > 0}">
		<s:select id="operadorCentroAcopio" name="rfcOperador" list="lstObv" listKey="rfcOperador" listValue="%{ciclo+' '+rfcOperador+' '+nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{rfcOperador}"/>
	</s:if>
	<s:else><span class="requerido">No se tiene asociado ningun operador de la bodega, que se capturo</span></s:else>
</div>


<!-- <table class="clean" style="width:100%"> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 			<label class="left1">Nombre o Razón Social del Centro de Acopio Registrado en ASERCA:</label>	 -->
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<s:if test="%{nombreCentroAcopio!=null && nombreCentroAcopio != ''}"> --%>
<%-- 				<s:textfield id="nombreCentroAcopio" name="nombreCentroAcopio" maxlength="15" size="80"  value="%{nombreCentroAcopio}" onchange="" disabled="true"/> --%>
<%-- 			</s:if> --%>
<%-- 			<s:else> --%>
<%-- 				<s:textfield id="nombreCentroAcopio" name="nombreCentroAcopio" maxlength="15" size="80"  value="%{}" onchange="" disabled="true"/> --%>
<%-- 			</s:else>				 --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 			<label class="left1">Nombre Local o Regional del Centro de Acopio:</label> -->
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<s:if test="%{nomRegionalCentroAcopio!=null && nomRegionalCentroAcopio != ''}"> --%>
<%-- 				<s:textfield id="nomRegionalCentroAcopio" name="nomRegionalCentroAcopio" maxlength="15" size="80"  value="%{nomRegionalCentroAcopio}" onchange="" disabled="true"/>	 --%>
<%-- 			</s:if> --%>
<%-- 			<s:else> --%>
<%-- 				<s:textfield id="nomRegionalCentroAcopio" name="nomRegionalCentroAcopio" maxlength="15" size="80"  value="%{}" onchange="" disabled="true"/> --%>
<%-- 			</s:else> --%>
			
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 			<label class="left1">Nombre o Razón Social del Operador del Centro de Acopio:</label> -->
<!-- 		</td> -->
<!-- 		<td>	 -->
<%-- 			<s:if test="%{lstObv.size() > 0}"> --%>
<%-- 				<s:select id="operadorCentroAcopio" name="operadorCentroAcopio" list="lstObv" listKey="id" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{id}"/> --%>
<%-- 			</s:if> --%>
<%-- 			<s:else><span class="requerido">No se tiene asociado ningun operador de la bodega, que se capturo</span></s:else>	 --%>
			
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->