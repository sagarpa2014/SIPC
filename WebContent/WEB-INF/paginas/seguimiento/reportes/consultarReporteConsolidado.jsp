<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/seguimiento.js" />"></script>

<s:form action="realizarConsultaReporteConsolidado" onsubmit="return chkCampoConsultaReporteConsolidado();">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>REPORTE CONSOLIDADO DEL SEGUIMIENTO DE ACOPIO</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Estado:</label>
			<s:select id="idEstadoSeg" name="idEstadoSeg" list="lstEstadosSeg" listKey="idEstado" listValue="%{nombreEstado}" headerKey="-1" headerValue="-- Seleccione una opción --" />
		</div>
		<div>
			<label class="left1">Producto:</label>
			<s:select id="idCultivoSeg" name="idCultivoSeg" list="lstCultivosSeg" listKey="idCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" />
		</div>
		<div>
			<label class="left1">Ciclo:</label>
			<s:select id="idCicloSeg" name="idCicloSeg" list="lstCiclosSeg" listKey="idCiclo" listValue="%{ciclo}" headerKey="-1" headerValue="-- Seleccione una opción --" />
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe seleccionar todos los criterios de b&uacute;squeda</span></p>
		</div>
		<div class="accion">
			<s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
	
	<s:if test="%{bandera==true}">
		<s:if test="lstReporteConsolidado.size() > 0">
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/seguimiento/exportaReporteConsolidado"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<display:table id="r"  name="lstReporteConsolidado" defaultsort="2" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteConsolidado"  pagesize="30" sort="list" requestURI="/seguimiento/realizarConsultaReporteConsolidado"  class="displaytag">
					<display:column  property="claveBodega" title="Clave Bodega"/>
					<display:column  property="nombreBodega" title="Nombre Bodega"/>
					<display:column  property="nombreLocalBodega" title="Nombre Local Bodega"/>
					<display:column  property="nombreOperador" title="Operador"/>
					<display:column  property="nombreComprador" title="Comprador"/>
					<display:column  property="volumenAXC" title="Volumen AxC"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="precioPromPagAXC" title="Precio Pagado"  format="{0, number,0.00}" total="true" class="d"/>
					<display:column  property="volumenMercadoLibre" title="Volumen Libre Mercado"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="acopioTotalTon" title="Acopio Toneladas"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="pagadoTon" title="Volumen Pagado"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="pagadoPorcentaje" title="Porcentaje Pagado"  format="{0, number,0.00}" total="true" class="d"/>
					<display:column  property="movilizadoFurgon" title="Movilizado Furgón"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="movilizadoCamion" title="Movilizado Camión"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="movilizadoMaritimo" title="Movilizado Marítimo"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="movilizadoTotal" title="Movilizado Total"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="existenciaAM" title="Existencia (Acop.-Mov.)"  format="{0, number,0.000}" total="true" class="d"/>
					<display:column  property="avanceCosecha" title="Avance Cosecha"  format="{0, number,0.00}" total="true" class="d"/>
				 </display:table>
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios de b&uacute;squeda</div></s:else>
	</s:if>
</s:form>