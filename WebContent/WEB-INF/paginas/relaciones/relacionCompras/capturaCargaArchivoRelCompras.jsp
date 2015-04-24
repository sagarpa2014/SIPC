<%@taglib uri="/struts-tags" prefix="s"%>
<script>
	 $(function() {
		$("#tabs").tabs();
	});
</script>	
<s:form action="cargarArchivoRelCompras"  method="post" enctype="multipart/form-data">
	<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
		<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
	</s:if>	
	<div class="borderBottom"><h1>RELACI&Oacute;N DE COMPRAS</h1></div><br>
	<div id="dialogo_1"></div>
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="tipoAccion" name="tipoAccion" value="%{tipoAccion}"/>
	<fieldset>
			<legend>Datos de la Carta de Adhesi&oacute;n</legend>
			<table class ="clean">
				<tr>
					<td><label class="left1">Folio Carta Adhesi&oacute;n:</label><td>
					<td>
						<font class="arial14boldVerde">
							<s:property value="%{folioCartaAdhesion}"/>
						</font>
					<td>
				</tr>
				<tr>
					<td><label class="left1">Programa:</label><td>
					<td>
						<font class="arial12bold"><s:property value="%{nombrePrograma}"/></font>
					<td>
				</tr>
				<tr>
					<td><label class="left1">Participante:</label><td>
					<td>
						<font class="arial12bold"><s:property value="%{nombreComprador}"/></font>
					<td>
				</tr>
			</table>
			<div class="derecha">
				<a href='<s:url value="/relaciones/getDetallePersonalizacionRel?registrar=2&idPerRel=%{idPerRel}&tipoCaptura=1&folioCartaAdhesion=%{folioCartaAdhesion}"/>' class="" title="">Ver configuraci&oacute;n de la relaci&oacute;n</a>
			</div>			
	</fieldset>	
	
	
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1" onclick= "borrarContenidoPestanias();">Cargar Archivo</a></li>
			<li><a href="#tabs-2">Eliminar Archivo</a></li>
			<li><a href="#tabs-3">Validar Relaci&oacute;n</a></li>
			<li><a href="#tabs-4">Generar Reporte</a></li>
			<li><a href="#tabs-5">Cargar Informaci&oacute;n</a></li>
		</ul>	
		<div id="tabs-1">
			<div id="cargarArchivoRelacionCompras" >
				<fieldset>
				<legend>Carga de Archivo de la Relaci&oacute;n de Compras</legend>			
				<div>
					<label class="left1">Relaci&oacute;n de Compras:</label>
					<s:file name="doc" id="doc"/>
				</div>
				<div class="clear"></div>			
				<s:if test="%{nombreArchivoExcel99 != null}">
					<fieldset>
						<legend>Ultimo Archivo cargado:</legend>
						<div class="exporta_csv">
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCartaAdhesion+nombreArchivoExcel99}"/>" title="Descargar Archivo" ></a>
						</div>	
					</fieldset>					
				</s:if>			
				<div class="clear"></div>	
				<!-- AHS 29012015 -->
				<s:if test="%{nombreArchivoLogCargaXls!=null}">
					<fieldset>
						<legend>Descargar Log de procesos</legend>	
						<div class="exporta_csv">			
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCartaAdhesion+nombreArchivoLogCargaXls}"/>" title="Descargar Archivo" ></a>
						</div>
						<div id="logCargaArchivo">
							<s:include value="listarLog.jsp"/>
						</div>
					</fieldset>
				</s:if>
				<div class="clear"></div>		
				<div class="accion">
					<s:submit  value="Confirmar" cssClass="boton2"  onclick="return chkCamposRegistroRelCompras();"/>
					<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
				</div>
			</fieldset>
			</div>			
		</div><!-- End  tabs-1 -->
		<div id="tabs-2">
			<fieldset>
					<legend>Eliminar Informaci&oacute;n</legend>
					<div class="accion">
						<s:submit  value="Eliminar Archivo" cssClass="boton2" action="eliminarArchivoComprasXls"  onclick="if (confirm('¿Esta seguro de eliminar el último archivo cargado?')){}else{return false;}"/>						
						<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
					</div>	
			</fieldset>		
		</div>
		<div id="tabs-3">
				<div id="validarRelacionCompras" >
				<fieldset>
					<legend>Validaci&oacute;n de Criterios</legend>
					<div>
						<label class="left1"><span class="requerido">*</span>Criterio de validaci&oacute;n:</label>
<%-- 						<s:select  id="grupoCriterio" name="grupoCriterio" list="lstCatCriteriosValidacion" listKey="grupo" listValue="%{grupo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{}"  onclick="recuperaVistaValidacion();"/> --%>
						<s:select  id="grupoCriterio" name="grupoCriterio" list="lstCatCriteriosValidacion" listKey="grupo" listValue="%{prioridad+' '+grupo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{}"  onclick="verificarQueCriterioPeriodoAplicar();"/>
					</div>						
					<div class="clear"></div>
					<div id="verificarQueCriterioPeriodoAplicar"></div>		
					<div class="clear"></div>				
					<div id="recuperaCamposFactura"></div>
					<div class="clear"></div>
					<div id = "logPrevalidacion">	
						<s:if test="%{tipoAccion == 1 || tipoAccion == 2}" >
							<s:include value="prevalidacionCriterios.jsp"/>
						</s:if>
					</div>					
					<div class="clear"></div>
					
					<div class="accion">
						<s:submit  value="Validar" cssClass="boton2" action="prevalidarRelacionCompras" onclick="return chkCamposPrevalidacion();"/>
						<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" class="boton" title="" >Cancelar</a>
					</div>
				</fieldset>
			</div><!--  end validarRelacionCompras -->
		</div><!-- end tabs-2 -->
		<div id="tabs-4">
			<div id="generarBitacoraRelacionCompras" >		
				<fieldset>
					<legend>Generaci&oacute;n de Bit&aacute;cora</legend>
					<div>
						<label class="left1"><span class="requerido">*</span>Fecha del Reporte:</label>
						<s:textfield id="fechaDeReporte" name="fechaDeReporte" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="" onchange=""/>
						<img width="16px" src="../images/calendar.gif" id="trgFR" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
						<script type="text/javascript">
							Calendar.setup({
								inputField     :    'fechaDeReporte',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgFR',  
								align          :    "Br",           
								singleClick    :    true
								});
						</script>
					</div>
					<div class="clear"></div>	
					<div id="respuestaGeneracionBitacora">
						<s:include value="respuestaGeneracionBitacora.jsp"/>
					</div>		
					<div class="accion">
						<s:submit  value="Generar Bítacora" action="generarBitacoraRelCompras"  onclick="return chkCamposBitacoraRelCompras();" cssClass="boton2" />
						<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" class="boton" title="" >Cancelar</a>
					</div>
				</fieldset>
			</div>
		</div>
		<div id="tabs-5">
			<div id="cargarInformacion" >
				<fieldset>
					<legend>Cargar Informaci&oacute;n</legend>
					<div class="accion">
						<s:submit  value="Cargar Información" action="cargarInformacion"  onclick="if (confirm('¿Esta seguro de guardar la información definitiva de la relación de compras?')){}else{return false;}" cssClass="boton2" />
						<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" class="boton" title="" >Cancelar</a>
					</div>
				</fieldset>
			</div>
		</div><!-- End tabs-4 -->
	</div><!-- End tabs -->
	<div class="clear"></div>	
</s:form>
<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
<script>
	$(document).ready(function() {
		var tipoAccion = $('#tipoAccion').val();
		if(tipoAccion == 0){
			$('#tabs').tabs("option", "active", 0);
		}else if(tipoAccion == 1){
			$('#tabs').tabs("option", "active", 1);
		}else if(tipoAccion == 2){
			$('#tabs').tabs("option", "active", 2);
		}else if(tipoAccion == 3){
			$('#tabs').tabs("option", "active", 3);
		}else if(tipoAccion == 4){
			$('#tabs').tabs("option", "active", 4);
		}		
	});	
	
</script>
