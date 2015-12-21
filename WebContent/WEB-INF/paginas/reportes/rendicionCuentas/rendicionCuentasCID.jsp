<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/reportes.js" />"></script>

<s:form action="realizarConsultaRendicionCuentasCID" onsubmit="return chkCampoConsultaReporteRendicionCuentasCID();">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>REPORTE DE RENDICION DE CUENTAS (ID)</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Fecha Inicio:</label>	
			<s:if test="%{fechaInicio==null}" >
				<s:textfield name="fechaInicio" maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaInicio" value="%{fechaInicio}"  maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div>
			<label class="left1">Fecha Fin:</label>	
			<s:if test="%{fechaFin==null}" >
				<s:textfield name="fechaFin" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaFin" value="%{fechaFin}" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe seleccionar los criterios de b&uacute;squeda</span></p>
		</div>
		<div class="accion">
			<s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
	
	<s:if test="lstReporteRendicionCuentasCID.size() > 0">
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaReporteRendicionCuentasCID"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
			<legend>Resultado de B&uacute;squeda</legend>
			<display:table id="r"  name="lstReporteRendicionCuentasCID" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstReporteRendicionCuentasCID"  pagesize="30" sort="list" requestURI="/reportes/realizarConsultaRendicionCuentasCID"  class="displaytag">
				<display:column  property="noCarta" title="Carta Adhesi&oacute;n"/>
				<display:column  property="nombreEstado" title="Entidad Aplicaci&oacute;n"/>
				<display:column  property="nombreCultivo" title="Cultivo"/>
				<display:column  property="fecha" title="Fecha"/>
				<display:column  property="estatusMonto" title="Estatus"/>
				<display:column  property="montoFederal" title="Monto ($)"  format="{0, number,0.00}" total="true" class="d"/>
				<display:column  property="cantidad" title="Cantidad (tons.)"  format="{0, number,0.000}" total="true" class="d"/>
				<display:column  property="cicloAgricola" title="Ciclo"/>
				<display:column  property="anioEjercicio" title="A&ntilde;o Fiscal"/>
			 </display:table>
		</fieldset>
	</s:if>
	<s:else>
		<div class="advertencia">No se encontraron registros con los criterios de b&uacute;squeda</div>
	</s:else>
</s:form>
<script type="text/javascript">
	<!--
		Calendar.setup({
			inputField     :    "fechaInicio",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg1",  
			align          :    "Br",           
			singleClick    :    true
		});
		Calendar.setup({
			inputField     :    "fechaFin",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg2",  
			align          :    "Br",           
			singleClick    :    true
			});
	//-->
</script>