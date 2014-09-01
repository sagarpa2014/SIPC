<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/archivoTesofe.js" />"></script>
<s:if test="hasActionErrors()">
   <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div class="borderBottom"><h1>CONSULTA DE OFICIOS</h1></div><br>
<div id="dialogo_1"></div>
<s:form action="realizarConOficiosEnvioTesofe" onsubmit="return chkCampoConsultaOficio();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">No. Oficio CGC a DGAF:</label>
			<s:textfield id="oficioCGC" name="oficioCGC" maxlength="30" size="31"/>
		</div>
		<div>
			<label class="left1">Folio CLC:</label>
			<s:textfield id="folioCLC" name="folioCLC" maxlength="5" size="6"/>
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Oficio Pago" cssClass="boton2" />
		</div>
	</fieldset>
	<s:if test="%{bandera==true}">
		<s:if test="lstOficioPagos.size() > 0">
			<div class="clear"></div>
			<br/>
			<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<div id="tablaResultados">
					<display:table id="r"  name="lstOficioPagos"  list="lstOficioPagos"  pagesize="50" sort="list" requestURI="/monitoreo/consultarLog"  class="displaytag">
						<display:column  title="No. Oficio">
							<a href="<s:url value="/pagos/consigueOficio?idOficio=%{#attr.r.idOficioPagos}&tipoArchivo=oficioEnvioCgcDgaf"/>" title="Descargar Archivo" ><s:property value="%{#attr.r.noOficio}"/></a>
						</display:column>
						<display:column  property="folioCLC" title="CLC"/>
						<display:column title="Archivo Envio">
							<s:if test="%{#attr.r.archivoEnvio!='' && #attr.r.archivoEnvio!=null }">
								<a href="<s:url value="/pagos/descargarArchivoPagosEnvio.action?nombreArchivo=%{#attr.r.archivoEnvio}" />" title="Descargar Archivo Pagos"><s:property value="%{#attr.r.archivoEnvio}"/></a>
							</s:if>
						</display:column>
				 		<display:column title="Oficios Escaneados"  class="center">
				 			<s:if test='#attr.r.folioCLC!=null'>
				 				<s:if test='#attr.r.oficioCgcDgafEscaneo==null && #attr.r.oficioDgafCgcEscaneo==null'>
									<a href='<s:url value="/pagos/capturaCargaArchivoTesofe?noOficio=%{#attr.r.noOficio}"/>' title="">Cargar Oficios</a>
								</s:if>
								<s:else>
								<div class="centrado">
									<div class="pdf">
										<a href="<s:url value="/pagos/consigueOficio?idOficio=%{#attr.r.idOficioPagos}&tipoArchivo=oficioCgcDgafEscaneo"/>" title="Oficio CGC a DGAF" ></a>
									</div>
									<div class="pdf">
										<a href="<s:url value="/pagos/consigueOficio?idOficio=%{#attr.r.idOficioPagos}&tipoArchivo=oficioDgafCgcEscaneo"/>" title="Oficio DGAF a CGC" ></a>
									</div>
									</div>
								</s:else>
							</s:if>
				 		</display:column>
						<display:column title="Ver Detalle"  headerClass="sortable">
							<a href='<s:url value="/pagos/detalleOficioPagos?idOficio=%{#attr.r.idOficioPagos}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 			</display:column>
			 			<s:if test='#attr.r.folioCLC==null'>
							<display:column title="Regresar Oficio"  headerClass="sortable">
								<a href='<s:url value="/pagos/regresaOficioPagos?idOficio=%{#attr.r.idOficioPagos}"/>' class="botonRegresaOficio" title="" onclick="if (confirm('¿Esta seguro de regresar el Oficio de Pagos?')){}else{return false;}"></a>
				 			</display:column>			 				
			 			</s:if>
					</display:table>
				</div>
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
	</s:if>
</s:form>
