<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/pagos.js" />"></script>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>DEVOLUCION DE PAGOS</h1></div><br>
<s:form action="consultaPagosCADevoluciones" id="" onsubmit="">
	<fieldset>
		<legend>Informaci&oacute;n del Pago</legend>	
		<div class="clear"></div>	
		<div>
			<label class="left1"><span class="norequerido">*</span></label>
			
		</div>
		<div class="clear"></div>	
		
		<table class="clean" style="width:100%">
			<tr>
	  			<td>
	  				<label class="left1">Carta de Adhesi&oacute;n:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="cartaAdhesion" name="cartaAdhesion" maxlength="30" size="30"  value="%{cartaAdhesion}" disabled="true"/>
	  			</td>
			</tr>
			<tr>
	  			<td>
	  				<label class="left1">Comprador:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="nombreComprador" name="nombreComprador" maxlength="50" size="50"  value="%{pv.nombreComprador}" disabled="true"/>
	  			</td>
			</tr>			
			<tr>
	  			<td>
	  				<label class="left1">No. Oficio DGAF:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="oficioPagos" name="oficioPagos" maxlength="20" size="20"  value="%{pv.oficioPagos}" disabled="true"/>
	  			</td>
			</tr>
			<tr>
	  			<td>
	  				<label class="left1">No. CLC:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="clc" name="clc" maxlength="10" size="10"  value="%{pv.clc}" disabled="true"/>
	  			</td>
			</tr>
			<tr>
	  			<td>
	  				<label class="left1">CLABE:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="clabe" name="clabe" maxlength="30" size="30"  value="%{pv.clabe}" disabled="true"/>
	  			</td>
			</tr>
			<tr>
	  			<td>
	  				<label class="left1">Clave Rastreo:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="claveRastreo" name="claveRastreo" maxlength="50" size="50"  value="%{pv.claveRastreo}" disabled="true"/>
	  			</td>
			</tr>
			<tr>
	  			<td>
	  				<label class="left1">Volumen Apoyado:</label>
	  			</td>
	  			<td>
	  				<s:textfield id="volumen" name="volumen" value="%{getText('volumenSinComas',{pv.volumen})}" maxlength="20" size="20"  cssClass="cantidad" disabled = "true" /> (tons.)
	  			</td>
			</tr>
			<tr>
	  			<td>
	  				<label class="left1">Importe Apoyado:</label>
	  			</td>
	  			<td>
	  				$<s:textfield id="importe" name="importe" value="%{getText('importeSinComas',{pv.importe})}" maxlength="20" size="20"  cssClass="cantidad" disabled = "true" />
	  			</td>
			</tr>
		</table>
	</fieldset>
	<div class="derecha"><a href="<s:url value="/pagos/capturaDevolucionPagoCA?idPago=%{idPago}&cartaAdhesion=%{cartaAdhesion}&registrar=1"/>" onclick="" title="Agregar Devolución" >[Agregar Devolución]</a></div><br>
	<s:if test="lstConsultaDevolucionesCA.size() > 0">
		<fieldset id="reporte2">
			<legend>Devoluciones</legend>	
			<display:table id="r"  name="lstConsultaDevolucionesCA"  list="lstConsultaDevolucionesCA"  pagesize="50" sort="list" requestURI="/pagos/consultaDevolucionesPagosCA"  class="displaytag">
				<display:column  property="lineaCaptura" title="Linea de Captura"/>
				<display:column  title="Fecha Emisi&oacute;n">
					<s:text name="fecha"> <s:param value="%{#attr.r.fechaEmisionLC}"/></s:text>
				</display:column>
				<display:column  title="Fecha Vigencia">
					<s:text name="fecha"> <s:param value="%{#attr.r.fechaVigenciaLC}"/></s:text>
				</display:column>				
				<display:column  property="volumen" title="Volumen (tons.)"  format="{0, number,0.000}" total="true" class="d"/>
				<display:column  property="importe" title="Importe ($)"  format="{0, number,currency}" total="true" class="d"/>	
				<display:column title="Linea Captura" headerClass="sortable" >
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#attr.r.rutaArchivoLC}"/>" title="Descargar Archivo" class="botonVistaPreliminar"></a>
				</display:column>
				<display:column title="Comprobante Pago" headerClass="sortable" >
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#attr.r.rutaArchivoPagoLC}"/>" title="Descargar Archivo" class="botonVistaPreliminar"></a>
				</display:column>
			 	<display:column title="Editar"  headerClass="sortable">
			 		<a href='<s:url value="/pagos/capturaDevolucionPagoCA?idPago=%{idPago}&idPagoDev=%{#attr.r.idPago}&cartaAdhesion=%{cartaAdhesion}&registrar=2"/>' class="editar"></a>	 			 	
			 	</display:column>
			 	<display:column title="Eliminar" headerClass="sortable" >
					<a href='<s:url value="/pagos/eliminarDevolucionPagoCA?idPago=%{idPago}&idPagoDev=%{#attr.r.idPago}&cartaAdhesion=%{cartaAdhesion}"/>' class="borrar" onclick="if (confirm('¿Esta seguro de Eliminar Registro?')){}else{return false;}"></a>
			 	</display:column>			 	
			</display:table>
		</fieldset>
	</s:if>
	<s:else>
		<div class="advertencia">No se encontraron devoluciones registradas para la Carta de Adhesi&oacute;n</div>
	</s:else>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/pagos/consultaPagosCADevoluciones?cartaAdhesion=%{cartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>	
</s:form>