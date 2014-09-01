<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/capturaPagos.js" />"></script>
<s:form action="agregarEditarPago" onsubmit="return chkCamposPagos();">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>CAPTURA DE PAGOS</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset>
		<legend>Datos del pago</legend>
		<div class="clear"></div><br>
		<div>
			<label class="left1"><span class="requerido">*</span>Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="recuperaEdoCompradoresByPrograma()" onchange="recuperaEdoCompradoresByPrograma()" />
		</div>
		<div class="clear"></div>
		<div id="recuperaEdoCompradoresByPrograma"></div>
	</fieldset>
	<br>
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2" />
		<a href="<s:url value="/pagos/capturaPagos"/>" class="boton" title="" >Limpiar</a>
	</div>
</s:form>
