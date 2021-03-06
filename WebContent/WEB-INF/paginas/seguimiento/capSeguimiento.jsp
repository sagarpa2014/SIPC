<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/seguimiento.js" />"></script>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<div class="borderBottom"><h1>SEGUIMIENTO DE COSECHA EN CENTROS DE ACOPIO</h1></div><br>
<form  action="registroSeguimientoCA" name="forma" method="post"  enctype="multipart/form-data" onsubmit="return chkCamposSeguimientoAcopio();">
<s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>
<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
<s:hidden id="idSeguimientoCA" name="idSeguimientoCA" value="%{idSeguimientoCA}"/>
<s:hidden id="claveBodegaAux" name="claveBodegaAux" value="%{claveBodega}"/>
<s:hidden id="idCultivoAux" name="idCultivoAux" value="%{idCultivo}"/>
<s:hidden id="idCicloAux" name="idCicloAux" value="%{idCiclo}"/>
<s:hidden id="ejercicioAux" name="ejercicioAux" value="%{ejercicio}"/>
<div id="dialogo_1"></div>
<fieldset>
	<legend>REGISTRO</legend>	
	<div class="clear"></div>	
	<div>
		<label class="left1"><span class="norequerido">*</span></label>
		
	</div>
	<div class="clear"></div>	
	
	<table class="clean" style="width:100%">
		<tr>
  			<td>
  				<label class="left1"><span class="requerido">*</span>Ciclo Agr&iacute;cola:</label>
  			</td>
  			<td>
  				<s:select id="idCiclo" name="idCiclo" list="lstCiclos" listKey="idCiclo" listValue="%{cicloLargo}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" value="%{sca.idCiclo}" onchange="validaClaveBodega();" />
  			</td>
  			<td colspan="2">
  				<s:select id="ejercicio" name="ejercicio" list="lstEjercicios" listKey="ejercicio" listValue="%{ejercicio}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" value="%{sca.ejercicio}" onchange="validaClaveBodega();" />
  			</td>
  			<td>
				<font class="arial14boldVerde">Estatus: <s:property value="%{descEstatus}"/></font>
  			</td>
		</tr>
		<tr>
			<td>
				<label class="left1"><span class="requerido">*</span>Existencia de Grano de la Visita Anterior:</label>
			</td>
			<td colspan="4">
<%-- 				<s:if test="existenciaAMAnt==0"> --%>
<%-- 					ok<s:textfield id="existenciaAMAnt" name="existenciaAMAnt" value="%{}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true"/> (T.M.) --%>
<%-- 				</s:if> --%>
<%-- 				<s:else> --%>
					<s:textfield id="existenciaAMAnt" name="existenciaAMAnt" value="%{getText('volumenSinComas',{existenciaAMAnt})}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true"/> (T.M.)
<%-- 				</s:else> --%>
			</td>
		</tr>	
		<tr>
			<td><label class="left1"><span class="requerido">*</span>Periodo de:</label></td>
			<td>
				<s:if test="%{sca.periodoInicial==null}">
					<s:textfield id="periodoInicial" name="periodoInicial" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{fechaActual})}" onchange=""/>
				</s:if>	
				<s:else>
					<s:textfield id="periodoInicial" name="periodoInicial" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{sca.periodoInicial})}" onchange=""/>
				</s:else>
				<s:if test="registrar!=1 && registrar!=3"> <!-- && registrar!=2 -->
					<img width="16px" src="../images/calendar.gif" id="trgPeriodoInicial" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     :    'periodoInicial',     
							ifFormat       :    "%d/%m/%Y",     
							button         :    'trgPeriodoInicial',  
							align          :    "Br",           
							singleClick    :    true
							});
					</script>
				</s:if>
			</td>
			<td>Al:</td>
			<td colspan="2">
				<s:if test="%{sca.periodoFinal==null}">
					<s:textfield id="periodoFinal" name="periodoFinal" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{fechaActual})}" onchange=""/>
				</s:if>	
				<s:else>
					<s:textfield id="periodoFinal" name="periodoFinal" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{sca.periodoFinal})}" onchange=""/>
				</s:else>
				<s:if test="registrar!=1 && registrar!=3"> <!-- && registrar!=2 -->
					<img width="16px" src="../images/calendar.gif" id="trgPeriodoFinal" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     :    'periodoFinal',     
							ifFormat       :    "%d/%m/%Y",     
							button         :    'trgPeriodoFinal',  
							align          :    "Br",           
							singleClick    :    true
							});
					</script>
				</s:if>
			</td>
		</tr>
		<tr>
  			<td>
  				<label class="left1"><span class="requerido">*</span>Clave del Centro Acopio:</label>
  			</td>
  			<td colspan="4">
  				<s:if test="%{sca.claveBodega!=null && sca.claveBodega != ''}">
  					<s:textfield id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{sca.claveBodega}" onchange="validaClaveBodega();" />
  				</s:if>
  				<s:else>
  					<s:textfield id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{}" onchange="validaClaveBodega();" />
  				</s:else>  					
  				
  			</td>
		</tr>
		<tr id="">
			<td  colspan="4" id="validaClaveBodega">
				<s:include value="datosBodega.jsp"/>
			</td>
<%-- 			 --%>
		</tr>
		<tr>
			<td>
				<label class="left1"><span class="requerido">*</span>Cultivo:</label>
			</td>
			<td colspan="4">
				<s:select id="idCultivo"  name="idCultivo" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" value="%{sca.idCultivo}" onchange="recuperaVariedadByCultivo(this.value);" />
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Variedad:</label>
			</td>
			<td colspan="4" id="variedad">
				<s:select id="idVariedad"  name="idVariedad" list="lstVariedad" listKey="idVariedad" listValue="%{variedad}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" value="%{sca.idVariedad}" onchange="validaClaveBodega();" />
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Volumen Mercado Libre:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.volumenMercadoLibre != null && sca.volumenMercadoLibre != ''}">
					<s:textfield id="volumenMercadoLibre" name="volumenMercadoLibre" value="%{getText('volumenSinComas',{sca.volumenMercadoLibre})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalAcopio(this.value, 'volumenMercadoLibre',1);"/> (T.M.)
				</s:if>
				<s:else>	
					<s:textfield id="volumenMercadoLibre" name="volumenMercadoLibre" value="" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalAcopio(this.value, 'volumenMercadoLibre',1);"/> (T.M.)
				</s:else>
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Volumen AXC:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.volumenAXC != null && sca.volumenAXC != ''}">
					<s:textfield id="volumenAXC" name="volumenAXC" value="%{getText('volumenSinComas',{sca.volumenAXC})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalAcopio(this.value, 'volumenAXC',1);"/> (T.M.)
				</s:if>
				<s:else>
					<s:textfield id="volumenAXC" name="volumenAXC" value="" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalAcopio(this.value, 'volumenAXC',1);"/> (T.M.)
				</s:else>
				
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Transferencia:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.transferencia != null && sca.transferencia != ''}">
					<s:textfield id="transferencia" name="transferencia" value="%{getText('volumenSinComas',{sca.transferencia})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalAcopio(this.value, 'transferencia',1);"/> (T.M.)
				</s:if>
				<s:else>
					<s:textfield id="transferencia" name="transferencia" value="" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalAcopio(this.value, 'transferencia',1);"/> (T.M.)
				</s:else>
				
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1"><span class="requerido">*</span>Acopio Total Toneladas:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.acopioTotalTon != null && sca.acopioTotalTon != ''}">
					<s:textfield id="acopioTotalTon" name="acopioTotalTon"  value="%{getText('volumenSinComas',{sca.acopioTotalTon})}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true" /> (T.M.)
				</s:if>
				<s:else>
					<s:textfield id="acopioTotalTon" name="acopioTotalTon" value="%{}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true" /> (T.M.)
				</s:else>
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<label class="left1">Nombre o Raz�n Social del Comprador:</label> -->
<!-- 			</td> -->
<!-- 			<td colspan="4"> -->
<%-- 				<s:select id="idComprador" name="idComprador" list="lstComprador" listKey="idComprador" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opci�n --" onchange="recuperaCartasByComprador()"  style="width:650px" value="%{sca.idComprador}"/> --%>
<!-- 			</td> -->
<!-- 		</tr>				 -->
		<tr>
			<td>
				<label class="left1">Pagado:</label>
			</td>
			<td>
				<s:if test="%{sca.pagadoTon != null && sca.pagadoTon != ''}">
					<s:textfield id="pagadoTon" name="pagadoTon" value="%{getText('volumenSinComas',{sca.pagadoTon})}" maxlength="14" size="20"  cssClass="cantidad" onchange="calcularPorcentajeDeLoPagado(this.value)"/>
				</s:if>
				<s:else>
					<s:textfield id="pagadoTon" name="pagadoTon" value="%{}" maxlength="14" size="20"  cssClass="cantidad"  onchange="calcularPorcentajeDeLoPagado(this.value)"/>
				</s:else>
			</td>
			<td colspan="3">
				<s:if test="%{sca.pagadoPorcentaje != null && sca.pagadoPorcentaje != ''}">
					<s:textfield id="pagadoPorcentaje" name="pagadoPorcentaje" value="%{getText('importeSinComas',{sca.pagadoPorcentaje})}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true" />
				</s:if>
				<s:else>
					<s:textfield id="pagadoPorcentaje" name="pagadoPorcentaje" value="%{}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true"/>
				</s:else>
				
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<label class="center1"><span class="norequerido">*</span>T.M.</label>			
			</td>
			<td colspan="3">
				<label class="center1"><span class="norequerido">*</span>%</label>			
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Precio Promedio Pagado:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.precioPromPagAXC != null && sca.precioPromPagAXC != ''}">
					$ <s:textfield id="precioPromPagAXC" name="precioPromPagAXC" value="%{getText('importeSinComas',{sca.precioPromPagAXC })}" maxlength="14" size="20"  cssClass="cantidad" /> (AXC)
				</s:if>
				<s:else>
					$ <s:textfield id="precioPromPagAXC" name="precioPromPagAXC" value="%{}" maxlength="14" size="20"  cssClass="cantidad" /> (AXC)
				</s:else>
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Precio Promedio Pagado:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.precioPromPagLibre != null && sca.precioPromPagLibre != ''}">
					$ <s:textfield id="precioPromPagLibre" name="precioPromPagLibre" value="%{getText('importeSinComas',{sca.precioPromPagLibre })}" maxlength="14" size="20"  cssClass="cantidad" /> (Libre Mercado)
				</s:if>
				<s:else>
					$ <s:textfield id="precioPromPagLibre" name="precioPromPagLibre" value="%{}" maxlength="14" size="20"  cssClass="cantidad" /> (Libre Mercado)
				</s:else>
			</td>
		</tr>		
		<tr>
			<td>
				<label class="left1">Movilizado:</label>
			</td>
			<td>
				<s:if test="%{sca.mfurgon != null && sca.mfurgon != ''}">
					<s:textfield id="mfurgon" name="mfurgon" value="%{getText('volumenSinComas',{sca.mfurgon})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mfurgon',1);"/>
				</s:if>
				<s:else>
					<s:textfield id="mfurgon" name="mfurgon" value="%{}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mfurgon',1);" />
				</s:else>				
			</td>
			<td>
				<s:if test="%{sca.mcamion != null && sca.mcamion != ''}">
					<s:textfield id="mcamion" name="mcamion" value="%{getText('volumenSinComas',{sca.mcamion})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mcamion',1);"/>
				</s:if>
				<s:else>
					<s:textfield id="mcamion" name="mcamion" value="%{}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mcamion',1);"/>
				</s:else>
			</td>
			<td>
				<s:if test="%{sca.mmaritimo != null && sca.mmaritimo != ''}">
					<s:textfield id="mmaritimo" name="mmaritimo" value="%{getText('volumenSinComas',{sca.mmaritimo})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mmaritimo',1);"/>
				</s:if>
				<s:else>
					<s:textfield id="mmaritimo" name="mmaritimo" value="%{}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mmaritimo',1);" />
				</s:else>
				
			</td>
			<td>
				<s:if test="%{sca.mautoconsumo != null && sca.mautoconsumo != ''}">
					<s:textfield id="mautoconsumo" name="mautoconsumo" value="%{getText('volumenSinComas',{sca.mautoconsumo})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mautoconsumo',1);"/>
				</s:if>
				<s:else>
					<s:textfield id="mautoconsumo" name="mautoconsumo" value="%{}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mautoconsumo',1);" />
				</s:else>				
			</td>
			<td>
				<s:if test="%{sca.mtransferencia != null && sca.mtransferencia != ''}">
					<s:textfield id="mtransferencia" name="mtransferencia" value="%{getText('volumenSinComas',{sca.mtransferencia})}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mtransferencia',1);"/>
				</s:if>
				<s:else>
					<s:textfield id="mtransferencia" name="mtransferencia" value="%{}" maxlength="14" size="20"  cssClass="cantidad" onchange="getTotalMovilizado(this.value, 'mtransferencia',1);" />
				</s:else>				
			</td>					
			<td>
				<s:if test="%{sca.mtotal != null && sca.mtotal != ''}">
					<s:textfield id="mtotal" name="mtotal"  value="%{getText('volumenSinComas',{sca.mtotal})}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true" /> (T.M.)
				</s:if>
				<s:else>
					<s:textfield id="mtotal" name="mtotal" value="%{}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true" /> (T.M.)
				</s:else>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Furg&oacute;n</label>			
			</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Cami&oacute;n</label>			
			</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Mar&iacute;timo</label>			
			</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Autoconsumo</label>			
			</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Transferencia</label>			
			</td>
			<td>
				<label class="left1"><span class="norequerido">*</span>Total</label>			
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1"><span class="requerido">*</span>Existencia (Acopio-Movilizado):</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.existenciaAM == null} ">	
					<s:textfield id="existenciaAM" name="existenciaAM" value="%{}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true" /> (T.M.)
				</s:if>
				<s:else>
					<s:textfield id="existenciaAM" name="existenciaAM" value="%{getText('volumenSinComas',{sca.existenciaAM})}" maxlength="14" size="20"  cssClass="cantidad" disabled = "true"/> (T.M.)
				</s:else>				
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Destino:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.destino==null ||sca.destino==''">
					<s:textfield id="destino" name="destino" value="" maxlength="200" size="100" />
				</s:if>
				<s:else>
					<s:textfield id="destino" name="destino" value="%{sca.destino}" maxlength="200" size="100" />
				</s:else>				
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Observaciones:</label>
			</td>
			<td colspan="4">
				<s:if test="%{sca.observaciones==null ||sca.observaciones==''}">
					<s:textarea id="observaciones" name="observaciones" value="" cols="80" rows="1" />	
				</s:if>
				<s:else>
					<s:textarea id="observaciones" name="observaciones" value="%{sca.observaciones}" cols="140" rows="1" />
				</s:else>
			</td>
		</tr>
		<tr>
			<td>
				<label class="left1">Avance de Acopio o Recepci�n:</label>
			</td>
			<td colspan="1">
				<s:if test="%{sca.avanceCosecha != null && sca.avanceCosecha != ''}">
					<s:textfield id="avanceCosecha" name="avanceCosecha"  value="%{getText('importeSinComas',{sca.avanceCosecha})}" maxlength="14" size="20"  cssClass="cantidad" /> %
				</s:if>
				<s:else>
					<s:textfield id="avanceCosecha" name="avanceCosecha" value="%{}" maxlength="14" size="20"  cssClass="cantidad"/>%
				</s:else>
			</td>
			<td colspan="3" ></td>
		</tr> 
		<tr>
			<td>
				<label class="left1"><span class="requerido">*</span>Fecha:</label>
			</td>
			<td colspan="4">
				<s:if test="%{fechaActual!=null}">
					<s:textfield id="fechaEnvio" name="fechaEnvio" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{fechaActual})}" onchange=""/>
				</s:if>	
				<s:else>
					<s:textfield id="fechaEnvio" name="fechaEnvio" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{getText('fecha1',{sca.fechaEnvio})}" onchange=""/>
				</s:else>
				<s:if test="registrar!=1 && registrar!=3">
					<img width="16px" src="../images/calendar.gif" id="trgFechaEnvio" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     :    'fechaEnvio',     
							ifFormat       :    "%d/%m/%Y",     
							button         :    'trgFechaEnvio',  
							align          :    "Br",           
							singleClick    :    true
							});
					</script>
				</s:if>				
			</td>
		</tr> 
	</table>
</fieldset>
<br>
<s:if test="%{#session.idPerfil==12}"> <!-- DGPC -->
	<div class="clear"></div>
	<fieldset>
		<legend>AUTORIZACION DE CAMBIOS</legend>	
		<div class="clear"></div>	
		<table  class="clean" style="width:100%">
			<tr>
				<td>
					<label class="left1"><span class="requerido">*</span>Justificaci&oacute;n de la Autorizaci&oacute;n:</label>
				</td>
				<td colspan="4">
					<s:if test="%{sca.justificacionAutoriza==null || sca.justificacionAutoriza==''}">
						<s:textarea id="justificacionAutoriza" name="justificacionAutoriza" value="" cols="140" rows="1" />
					</s:if>
					<s:else>
						<s:textarea id="justificacionAutoriza" name="justificacionAutoriza" value="%{sca.justificacionAutoriza}" cols="140" rows="1" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td>
					<label class=""><span class="requerido">*</span>Archivo Autorizaci&oacute;n:</label>
				</td>
				<td colspan="4">
		 			<s:if test="%{sca.idEstatus == 1}">
		 				<s:if test="%{sca.justificacionAutoriza==null || sca.justificacionAutoriza==''}">
		 					<s:file name="doc" id="doc"/>
		 				</s:if>
			 			<s:else>
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaJustificacionAutoriza}"/>" title="Descargar Archivo">Descargar Archivo</a>
						</s:else>		 				
		 			</s:if>
					<s:if test="%{sca.idEstatus == 2}">
						<s:file name="doc" id="doc"/>
		 			</s:if>
		 			<s:if test="%{sca.idEstatus == 3}">
						<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaJustificacionAutoriza}"/>" title="Descargar Archivo">Descargar Archivo</a>
					</s:if>							
				</td>
			</tr>				
		</table>
	</fieldset>
</s:if>
<s:if test="registrar!=1"> <!-- Registro o Edicion -->
	<div class="derecha">
		<p><span class="requerido">*&nbsp;&nbsp;Indica que el campo es obligatorio</span></p>
	</div>
</s:if>
<br>
<s:if test="registrar!=1">
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2"/>
	</div>
</s:if>

<div class="clear"></div>
<div class="izquierda"><a href="<s:url value="/seguimiento/listSeguimiento"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</form>
<s:if test="registrar==1"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');		
			$("#observaciones").attr('disabled','disabled');		
			$("#justificacionAutoriza").attr('disabled','disabled');
		});	 	
	</script>
</s:if>
<s:if test="registrar==2"> <!-- Edicion -->
	<script>
		$(document).ready(function() {			
			$("#idCiclo").attr('disabled','disabled');		
			$("#ejercicio").attr('disabled','disabled');		
			$("#claveBodega").attr('disabled','disabled');
//			$("#periodoInicial").attr('disabled','disabled');		
//			$("#periodoFinal").attr('disabled','disabled');
		});	 
	</script>
</s:if>
<s:if test="registrar==3"> <!-- Autorizacion -->
	<script>
		$(document).ready(function() {	
			//$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');		
			$("#observaciones").attr('disabled','disabled');			
			$("#periodoInicial").attr('disabled','disabled');		
			$("#periodoFinal").attr('disabled','disabled');		
			$("#claveBodega").attr('disabled','disabled');
			$("#volumenMercadoLibre").attr('disabled','disabled');
			$("#volumenAXC").attr('disabled','disabled');
			$("#pagadoTon").attr('disabled','disabled');
			$("#pagadoPorcentaje").attr('disabled','disabled');
			$("#mfurgon").attr('disabled','disabled');
			$("#mcamion").attr('disabled','disabled');
			$("#mmaritimo").attr('disabled','disabled');
			$("#mautoconsumo").attr('disabled','disabled');
			$("#destino").attr('disabled','disabled');
			$("#observaciones").attr('disabled','disabled');
			$("#precioPromPagAXC").attr('disabled','disabled');
			$("#avanceCosecha").attr('disabled','disabled');
			$("#fechaEnvio").attr('disabled','disabled');
			$("#justificacionAutoriza").attr('enabled','disabled');
		});	 	
	</script>
</s:if>

