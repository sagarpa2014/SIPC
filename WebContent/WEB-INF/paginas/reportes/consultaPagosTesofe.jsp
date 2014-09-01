<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/reportes.js" />"></script>

<s:form action="realizarConsultaPagosTesofe" onsubmit="return chkCampoConsultaPagosTesofe();">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>PAGOS A TESOFE</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">No. Oficio</label>
			<s:textfield id="oficioCGC" name="oficioCGC" maxlength="30" size="30"/>
		</div>
		<div>
			<label class="left1">Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --" />
		</div>
		<div>
			<label class="left1">Fecha Inicio:</label>	
			<s:if test="%{fechaInicio==null}" >
				<s:textfield name="fechaInicio" maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaInicio" value="%{}"    maxlength="10" size="10" id="fechaInicio" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div>
			<label class="left1">Fecha Fin:</label>	
			<s:if test="%{fechaFin==null}" >
				<s:textfield name="fechaFin" maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaFin" value="%{}"   maxlength="10" size="10" id="fechaFin" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">
			<s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
	
	<s:if test="%{bandera==true}">
		<s:if test="lstRespuestaPagoV.size() > 0">
		<br/>
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/reportes/exportaPagosTESOFE"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>
		<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<display:table id="r"  name="lstRespuestaPagoV" defaultsort="2" decorator="org.displaytag.decorator.TotalTableDecorator" list="lstRespuestaPagoV"  pagesize="30" sort="list" requestURI="/reportes/realizarConsultaPagosTesofe"  class="displaytag">
					<display:column title="Fecha" >
						<s:text name="fecha"><s:param value="%{#attr.r.fechaOficio}"/></s:text>
				 	</display:column>
					<display:column  property="noOficio" title="Oficio"/>
					<display:column  property="totalPagos" title="Depositos"  format="{0, number,0}" total="true" class="c"/>
					<display:column  property="totalImporte" title="Importe"  format="{0, number,000.00}" total="true" class="d"/>
					<display:column  property="totalVolumen" title="Volumen"  format="{0, number,0.00}" total="true" class="d"/>
					<display:column  property="cicloCorto" title="Ciclo"/>
					<display:column  property="descripcionCorta" title="Programa"/>
				 	<display:column  property="folioClc" title="CLC"/>
				 	<display:column  property="archivoEnvio" title="Archivo Env&iacute;o"/>
				 	<display:column title="Aplicados" class="c" >
	 					<s:if test='#attr.r.aplicados!=0'>	
							<a href='<s:url value="/reportes/capturaAplicados?idOficio=%{#attr.r.idOficioPagos}"/>' target="winload" onclick="window.open(this.href, this.target, 'width=800,height=500,scrollbars=yes'); return false;"><s:property value="%{#attr.r.aplicados}"/></a>
						</s:if><s:else>-</s:else>
				 	</display:column>
				 	<display:column title="Rechazados" class="c">
	 					<s:if test='#attr.r.rechazados!=0'>	
							<a href='<s:url value="/reportes/capturaRechazos?idOficio=%{#attr.r.idOficioPagos}"/>' target="winload" onclick="window.open(this.href, this.target, 'width=800,height=500,scrollbars=yes'); return false;" ><s:property value="%{#attr.r.rechazados}"/></a>
						</s:if><s:else>-</s:else>
				 	</display:column>
				 	<display:column title="Ver Detalle">
						<a href='<s:url value="/reportes/detalleOficioPagos?idOficio=%{#attr.r.idOficioPagos}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=500,scrollbars=yes'); return false;"></a>
		 			</display:column>
				 </display:table>
				 
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
	</s:if>
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

