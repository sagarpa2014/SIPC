<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:if test="hasActionErrors()">
   <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div class="borderBottom"><h1>CONTROL OFICOS CLC</h1></div><br>
<s:form action="realizarBusqueda" onsubmit="return chkCamposBusquedaPagos();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaCompradoresByPrograma()" />
		</div>
		<div id="compradores"><s:include value="lstCompradores.jsp"/></div>
		<div>
			<label class="left1">No. Oficio CLC:</label>
			<s:textfield id="oficioCLC" name="oficioCLC" maxlength="30" size="30"/>
		</div>		
		<div>
			<label class="left1">Estatus:</label>
			<s:select id="estatusId" name="estatusId" list="lstEstatusOficio" listKey="estatusId" listValue="%{descripcionStatus}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="" value="%{}"/>
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="aline"></div>
		<div class="accion">	    	
		    <s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
</s:form>
<s:if test="%{bandera==true}">
	<s:if test="lstOficiosCLCV.size() > 0">
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaConsultaOficiosCLC"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<br/>
		<fieldset>
			<legend>Resultado de B&uacute;squeda</legend>
			<div id="tablaResultados">
				<display:table id="r" name="lstOficiosCLCV"  list="lstOficiosCLCV"  pagesize="50" sort="list" requestURI="/reportes/realizarBusqueda"  class="displaytag">
					<display:column  property="noCarta" title="No. Carta" headerClass="sortable"/>					
					<display:column  property="nombreComprador" title="Comprador"/>
					<display:column  property="rfc" title="RFC"/>
					<display:column  property="clabe" title="Clabe"/>
					<display:column title="Volumen (tons.)" class="d">
						<s:text name="volumen"><s:param value="%{#attr.r.volumen}"/></s:text>
					</display:column>
					<display:column title="Importe ($)" class="d">
						<s:text name="importe"><s:param value="%{#attr.r.importe}"/></s:text>
					</display:column>
					<display:column  property="noOficio" title="No. Oficio"/>
					<display:column  property="folioCLC" title="Folio CLC"/>
					<display:column  property="estatusDesc" title="Estatus"/>
				</display:table>
			</div>
		</fieldset>
		<div class="accion">
			<a href="<s:url value="/reportes/busquedaOficiosCLC"/>" class="boton" title="" >Limpiar</a>
		</div>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
</s:if>	