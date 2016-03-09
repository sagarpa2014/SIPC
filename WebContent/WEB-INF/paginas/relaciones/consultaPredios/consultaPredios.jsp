<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
   <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div class="borderBottom"><h1>CONSULTA DE PREDIOS, CURP O RFC</h1></div><br>
<div id="dialogo_1"></div>
<s:form action="ejecutaConsultaPredios" onsubmit="return chkConsultaPredios();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Esquema:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --" style="width:650px" />   
		</div>
		<div>
			<label class="left1">Predio:</label>
			<s:textfield id="predio" name="predio" maxlength="30" size="31"/>
		</div>
		<div>
			<label class="left1">Curp:</label>
			<s:textfield id="curp" name="curp" maxlength="30" size="30"/>
		</div>
		<div>
			<label class="left1">RFC:</label>
			<s:textfield id="rfc" name="rfc" maxlength="30" size="30"/>
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
	<s:if test="%{bandera==true}">
		<s:if test="lstPrediosRelaciones.size() > 0">
			<div class="clear"></div>
			<br/>
			<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<div id="tablaResultados">
					<display:table id="r"  name="lstPrediosRelaciones"  list="lstPrediosRelaciones"  pagesize="50" sort="list" requestURI="/relaciones/ejecutaConsultaPredios"  class="displaytag">
						<display:column  property="descripcionCorta" title="Programa"/>
						<display:column  property="folioContrato" title="Folio Contrato"/>
						<display:column  property="predio" title="Predio"/>						
						<display:column  property="curp" title="Curp"/>						
						<display:column  property="rfc" title="Rfc"/>						
					</display:table>
				</div>
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
	</s:if>
</s:form>
