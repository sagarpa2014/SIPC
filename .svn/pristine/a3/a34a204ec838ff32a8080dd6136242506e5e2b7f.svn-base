<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/reportes.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div class="borderBottom"><h1>REPORTES DIN&Aacute;MICO</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div id="dialogo_1"></div>
<fieldset>
	<legend>Capture los valores para generar el reporte</legend>
	<div class="inline">
		<label class="left1">Tipo de Reporte:</label>
		<s:radio label="" onclick="selectTipoReporte()"  name="tipoReporte" list="#{'0':'POR FECHA DE DEPÓSITO','1':'POR OFICIO'}" value="%{0}" />			
	</div>
	<div class="clear"></div>
		<div id="seleccionCriterios"><s:include value="/WEB-INF/paginas/reportes/reporteDinamico/seleccionCriterios.jsp" /></div>
	<div class="accion">
		<a href="#" class="boton" title="" onclick="generarReporteDinamico()">Consultar Reporte</a>
	</div> 
	
</fieldset>	
<div id="resultadoReporteDinamico"></div>	


