<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<div class="borderBottom"><h1>REGISTRO DE DEVOLUCION DE PAGOS</h1></div><br>
<form  action="registroDevolucionPagoCA" name="forma" method="post"  enctype="multipart/form-data" onsubmit="return chkCamposDevolucion();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="cartaAdhesion" name="cartaAdhesion" value="%{cartaAdhesion}"/>
	<s:hidden id="idPago" name="idPago" value="%{idPago}"/>
	<s:hidden id="idPagoDev" name="idPagoDev" value="%{idPagoDev}"/>
	<div id="dialogo_1"></div>
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
	<br>
	<fieldset>
		<legend>Registro Devoluci&oacute;n</legend>	
		<div class="clear"></div>	
		<table  class="clean" style="width:100%">
			<tr>
	  			<td>
	  				<label class="left1"><span class="requerido">*</span>Linea de Captura:</label>
	  			</td>
	  			<td>
  					<s:textfield id="lineaCaptura" name="lineaCaptura" maxlength="50" size="50"  value="%{dev.lineaCaptura}" />
	  			</td>
			</tr>
			<tr>
				<td>
					<label class="left1"><span class="requerido">*</span>Fecha Emisi&oacute;n:</label>
				</td>
				<td>
					<s:if test="%{dev.fechaEmisionLC == null || dev.fechaEmisionLC == ''}">
						<s:textfield id="fechaEmision" name="fechaEmision" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="" />
					</s:if>
					<s:else>
						<s:textfield id="fechaEmision" name="fechaEmision" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{dev.fechaEmisionLC})}" />
					</s:else>
					<img width="16px" src="../images/calendar.gif" id="trgFechaEmision" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     :    'fechaEmision',     
							ifFormat       :    "%d/%m/%Y",     
							button         :    'trgFechaEmision',  
							align          :    "Br",           
							singleClick    :    true
							});
					</script>
				</td>
			</tr>
			<tr>
				<td>
					<label class="left1"><span class="requerido">*</span>Fecha Vigencia:</label>
				</td>
				<td>
					<s:if test="%{dev.fechaVigenciaLC == null || dev.fechaVigenciaLC == ''}">
						<s:textfield id="fechaVigencia" name="fechaVigencia" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="" />
					</s:if>
					<s:else>
						<s:textfield id="fechaVigencia" name="fechaVigencia" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{dev.fechaVigenciaLC})}" />
					</s:else>
					<img width="16px" src="../images/calendar.gif" id="trgFechaVigencia" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     :    'fechaVigencia',     
							ifFormat       :    "%d/%m/%Y",     
							button         :    'trgFechaVigencia',  
							align          :    "Br",           
							singleClick    :    true
							});
					</script>
				</td>
			</tr> 		
			<tr>
				<td>
					<label class="left1">Volumen de la Devoluci&oacute;n:</label>
				</td>
				<td>
					<s:if test="%{dev.volumen == null}">
						<s:textfield id="volumenDev" name="volumenDev" value="" maxlength="14" size="20"  cssClass="cantidad" /> (tons.)				
					</s:if>
					<s:else>
						<s:textfield id="volumenDev" name="volumenDev" value="%{getText('volumenSinComas',{dev.volumen})}" maxlength="14" size="20"  cssClass="cantidad" /> (tons.)
					</s:else>
				</td>
			</tr>
			<tr>
				<td>
					<label class="left1"><span class="requerido">*</span>Importe de la Devoluci&oacute;n:</label>
				</td>
				<td>
					<s:if test="%{dev.importe == null}">
						$<s:textfield id="importeDev" name="importeDev" value="" maxlength="14" size="20"  cssClass="cantidad" />
					</s:if>
					<s:else>
						$<s:textfield id="importeDev" name="importeDev" value="%{getText('importeSinComas',{dev.importe})}" maxlength="14" size="20"  cssClass="cantidad" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td>
					<label class=""><span class="requerido">*</span>Archivo Linea de Captura:</label>
				</td>
				<td>
					<s:file name="doc" id="doc"/>
		 			<s:if test="%{dev.rutaArchivoLC != null || dev.rutaArchivoLC != ''}">
						<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{dev.rutaArchivoLC}"/>" title="Descargar Archivo">Descargar Archivo</a>
					</s:if>							
				</td>
			</tr>
			<tr>
				<td>
					<label class=""><span class="requerido">*</span>Archivo Comprobante de Pago:</label>
				</td>
				<td>
					<s:file name="doc2" id="doc2"/>
		 			<s:if test="%{dev.rutaArchivoPagoLC != null || dev.rutaArchivoPagoLC != ''}">
						<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{dev.rutaArchivoPagoLC}"/>" title="Descargar Archivo">Descargar Archivo</a>
					</s:if>
				</td>
			</tr>
		</table>
	</fieldset>
	<br>
	<div class="derecha">
		<p><span class="requerido">*&nbsp;&nbsp;Indica que el campo es obligatorio</span></p>
	</div>
	<br>
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2"/>
	</div>
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/pagos/consultaDevolucionesPagosCA?idPago=%{idPago}&cartaAdhesion=%{cartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</form>