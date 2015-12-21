<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/seguimiento.js" />"></script>

<s:form action="realizarConsultaReporteResumenAva" onsubmit="return chkCampoConsultaReporteResumen();">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>REPORTE RESUMEN DE AVANCE DEL SEGUIMIENTO DE ACOPIO</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Ciclo:</label>
			<s:select id="idCicloSeg" name="idCicloSeg" list="lstCiclosSeg" listKey="idCiclo" listValue="%{ciclo}" headerKey="-1" headerValue="-- Seleccione una opción --" />
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe seleccionar el criterio de b&uacute;squeda</span></p>
		</div>
		<div class="accion">
			<s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
	
	<s:if test="%{bandera==true}">
		<s:if test="lstReporteResumen.size() > 0">
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/seguimiento/exportaReporteResumenAva"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<display:table id="r"  name="lstReporteResumen" defaultsort="2" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteResumen"  pagesize="30" sort="list" requestURI="/seguimiento/realizarConsultaReporteResumenAva"  class="displaytag">
					<display:column  property="ciclo" title="Ciclo"/>
					<display:column  property="nombreEstadoBodega" title="Entidad Federativa"/>
					<display:column  property="nombreCultivo" title="Cultivo"/>
					<display:column  property="acopioTotal" title="Acopio Total"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="movilizadoTotal" title="Movilizado Total"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="existenciaAM" title="Existencia (Acop.-Mov.)"  format="{0, number,0.000}" total="true" class="d"/>
				 </display:table>
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios de b&uacute;squeda</div></s:else>
	</s:if>
</s:form>