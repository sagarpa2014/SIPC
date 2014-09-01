<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:if test="hasActionErrors()">
   <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div class="borderBottom"><h1>CONSULTA DE PAGOS</h1></div><br>
<s:form action="realizarBusqueda" onsubmit="return chkCamposBusquedaPagos();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramas" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaCompradoresByPrograma()" />
		</div>
		<div id="compradores"><s:include value="lstCompradores.jsp"/></div>
		<div>
			<label class="left1">Estatus:</label>
			<s:select id="estatusId" name="estatusId" list="lstEstatusPago" listKey="estatusId" listValue="%{descripcionStatus}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="" value="%{}"/>
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
		<div class="aline"></div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Pago" cssClass="boton2" />
		</div>
	</fieldset>
	<s:if test="%{bandera==true}">
		<s:if test="lstPagosV.size() > 0">
			<div class="exporta_csv">
				<label class="label2"> Exportar Datos </label> <a href="<s:url value="/pagos/exportaConsultaPagos"/>" title="Exportar Datos" ></a>
			</div>
			<div class="clear"></div>
			<br/>
			<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<div id="tablaResultados">
					<display:table id="r" name="lstPagosV"  list="lstPagosV"  pagesize="50" sort="list" requestURI="/pagos/realizarBusqueda"  class="displaytag">
						<display:column title="Fecha Transacción"  headerClass="sortable" >
							<s:text name="fecha.hora"><s:param value="%{#attr.r.fechaCreacion}"/></s:text>
			 			</display:column>
						<display:column  property="nombrePgrCorto" title="Programa"/>
						<display:column  property="noCarta" title="No. Carta"/>
						<display:column  property="nombreComprador" title="Comprador"/>
						<display:column  property="estatusPago" title="Estatus"/>
						<display:column title="Etapa" class="c" >
							<s:if test="%{#attr.r.etapa!=null}" >
								<s:property value="%{#attr.r.etapa}"/>
							</s:if>
							<s:else></s:else>
						</display:column>
						<display:column title="Volumen" class="d">
							<s:if test="%{#attr.r.volumen!=null}" >
								<s:text name="volumen"><s:param value="%{#attr.r.volumen}"/></s:text>
							</s:if>
							<s:else></s:else>
						</display:column>
						<display:column title="Importe" class="d">
							<s:text name="importe"><s:param value="%{#attr.r.importe}"/></s:text>
						</display:column>									
						<display:column title="Ver Detalle"  headerClass="sortable" >
							<a href='<s:url value="/pagos/detallesPago?idPago=%{#attr.r.idPago}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 			</display:column>
			 			<s:if test='#session.idPerfil==99'>
				 			<display:column title="Editar"  headerClass="sortable" >
			 					<s:if test='#attr.r.estatus==1'>	
									<a href='<s:url value="/pagos/capturaEdicionPago?idPago=%{#attr.r.idPago}"/>' class="editar" title="" ></a>
								</s:if><s:else></s:else>
				 			</display:column>
				 			</s:if>
				 		<s:if test='#session.idPerfil==99'>
					 			<display:column title="Eliminar"  headerClass="sortable" >
				 					<s:if test='#attr.r.estatus==1'>
										<a href='<s:url value="/pagos/eliminarPago?idPago=%{#attr.r.idPago}"/>' class="borrar" title="" ></a>
									</s:if>
									<s:else></s:else>
					 			</display:column>
				 		</s:if>
					</display:table>
				</div>
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
