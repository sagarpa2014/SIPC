<%@taglib uri="/struts-tags" prefix="s"%>

<s:form action="registraCapturaRelacion"  id="target" onsubmit="return chkCamposCapturaRelaciones();">
	<!-- Encabezado de la relacion -->
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>	
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
	<s:hidden id="idRelacion" name="idRelacion" value="%{idRelacion}"/>
	<s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>
	<s:hidden id="errorClaveBodegaExiste" name="errorClaveBodegaExiste" value="%{errorClaveBodegaExiste}"/>
	<s:hidden id="folioTalonTerrestre" name="folioTalonTerrestre" value="%{folioTalonTerrestre}"/>
	<s:set name= "claveBodega1" value="%{claveBodega}"></s:set>
	<s:set name= "estadoBodega1" value="%{estadoBodega}"></s:set>
	<s:set name= "folioCartaAdhesion1"  value="%{folioCartaAdhesion}"></s:set>
	<div class="inline">
		<label class="left1">Seleccione el Tipo de Tramo:</label>
		<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
			<s:radio label="" name="tipoFormatoTerrestre" list="#{0:'BODEGA DE ORIGEN - PLANTA PROCESADORA O FRONTERA', 1:'PLANTA PROCESADORA - FRONTERA'}" value="%{tipoFormatoTerrestre}" disabled="true"/>
		</s:if>
		<s:else>
			<s:radio label="" onclick="validaClaveBodega();"  name="tipoFormatoTerrestre" list="#{0:'BODEGA DE ORIGEN - PLANTA PROCESADORA O FRONTERA', 1:'PLANTA PROCESADORA - FRONTERA'}" value="%{tipoFormatoTerrestre}"/>
		</s:else>
	</div>
	<div class="clear"></div>	
	<div class="borderBottom" style="text-align:center"><h1>Encabezado de la Relaci&oacute;n de Movilizacion Terrestre</h1></div><br>				
	<table style="width:100%">
		<tr>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		
		<s:iterator value="lstGpoCampoTerrestreRelEncabezadoV" var="resultado"  status="itStatus">
			<tr>
				<td>
					<s:property value="%{campo}"/>
				</td>
				<s:if test="tipoDato == 'lstCompradores'">
					<td align="justify" >
						<s:textfield id="idComp" name="nombre" size="100"  value="%{nombre}" disabled="true"/>
					</td>
				</s:if>
				<s:elseif test="tipoDato == 'bodega'">
					<td>
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="50" size="50"  value="%{#claveBodega1}"  disabled="true"/>
						</s:if>
						<s:else>
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="50" size="50"  value="%{#claveBodega1}" onkeyup="validaClaveBodega();" onchange="validaClaveBodega();"/>
							<div id="validaClaveBodega"></div>
						</s:else>
					</td>
				</s:elseif>
				<s:elseif test="tipoDato == 'lstEstados'">
					<td>	
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:select cssClass="lstEstados" id="estadoBodega" name="estadoBodega" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{estadoBodega1}"  disabled="true" />	
						</s:if>
						<s:else>
							<s:select cssClass="lstEstados" id="estadoBodega" name="estadoBodega" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{estadoBodega1}" onchange="validaClaveBodega();"/>
						</s:else>	
						
					</td>
				</s:elseif>
				<s:elseif test="tipoDato == 'folioCarta'">
					<s:if test="%{#folioCartaAdhesion1 ==null}">
						<td align="justify">
							<s:textfield cssClass="folioCarta" id="folioCarta" name="folioCartaAdhesion" maxlength="50" size="50"  value="No se ha registrado Carta de Adhesion" disabled="true"/>
						</td>
					</s:if>
					<s:else>
						<td align="justify">
							<s:textfield cssClass="folioCarta" id="folioCarta" name="folioCartaAdhesion" maxlength="50" size="50"  value= "%{#folioCartaAdhesion1}" disabled="true"/>
						</td>
					</s:else>
				</s:elseif>
				<s:elseif test="tipoDato == 'lstCultivo'">
					<td align="justify">
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange="" disabled="true"/>
						</s:if>
						<s:else>
							<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange="validaClaveBodega();" />
						</s:else>
					</td>
				</s:elseif>
			</tr>
		</s:iterator>
		<tr>
			<td>
				CICLO AGRICOLA DEL REGISTRO:
			</td>
			<td>
				<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass" disabled="true" />
				</s:if>
				<s:else>
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass" onchange="validaClaveBodega();"/>
				</s:else>
			</td>			
		</tr>
	</table>
	<div id="relaciones">
	<!-- Detalle de la relacion -->
	<div class="borderBottom" style="text-align:center"><h1>Detalle de la Relaci&oacute;n</h1></div><br>
	<table style="width:100%">
		<tr>
			<th>Grupo</th>
			<th>No. Campo</th>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<s:iterator value="lstGpoCampoTerrestreRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<s:if test='#temporal!=idGrupo'>
					<td><s:property value="grupo"/></td>
				</s:if>
				<s:else>
					<td>&nbsp;</td>
				</s:else>
				<td align="center" ><s:property value="%{#itStatus.count}"/></td>
				<td><s:property value="%{campo}"/></td>
				<s:if test="%{idGrupo == 15 && idCampo == 32}"> <!-- FECHA DE EMBARQUE -->
					<s:if test="registrar == 0 || registrar == 2">
						<td>	
							<s:textfield id="selectedFechaEmbarque" name="descripcionDet[%{idCampoRelacion}]" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
							<img src="../images/calendar.gif" id="trgFechaEmbarque" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
							<script type="text/javascript">
							<!--
								Calendar.setup({
								inputField     :    'selectedFechaEmbarque',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgFechaEmbarque',  
								align          :    "Br",           
								singleClick    :    true
								});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="registrar == 1">
						<td align="justify">
							<s:textfield id="" key="selectedFecha"  value="%{descripcion}" maxlength="12" size="12"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>
				</s:if>
				<s:elseif test="%{idGrupo == 15 && idCampo == 33}"> <!-- FOLIO DEL TALON -->
					<td align="justify">
						<s:textfield id="folioTalonTerrestreTemp" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="14"  value="%{descripcion}" onblur="validarFolioTalonTerrestre();"/>
						<div id="validarFolioTalonTerrestre"></div>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 15 && idCampo == 34}"> <!-- VOLUMEN EMBARCADO -->
					<td align="justify">
						<s:textfield  id="volumenEmbarcado" name="descripcionDet[%{idCampoRelacion}]" maxlength="15" size="20"  value="%{descripcion}" onblur="validarVolumen(this.value, 'volumenEmbarcado', 1);"/>					
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 35}"><!-- BODEGA FINAL -->
					<td align="justify">
		
						<s:textfield id="idEstadoBodegaFinal" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="30"  value="%{descripcion}" />
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 37}"><!-- FRONTERA-->
					<td align="justify">
						<s:textfield id="frontera" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="30"  value="%{descripcion}" />
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 38}"><!-- PLANTA PROCESADORA-->
					<td align="justify">
						<s:textfield id="plantaProcesadora" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="30"  value="%{descripcion}" />
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 39}"><!-- FECHA RECECPCION -->
					<s:if test="registrar == 0 || registrar == 2">
						<td>	
							<s:textfield id="selectedFechaRecepcion" name="descripcionDet[%{idCampoRelacion}]" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
							<img src="../images/calendar.gif" id="trgFechaRecepcion" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
							<script type="text/javascript">
								<!--
									Calendar.setup({
									inputField     :    'selectedFechaRecepcion',     
									ifFormat       :    "%d/%m/%Y",     
									button         :    'trgFechaRecepcion',  
									align          :    "Br",           
									singleClick    :    true
									});
								//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="registrar == 1">
						<td>
							<s:textfield id="" key=""  value="%{descripcion}" maxlength="12" size="12"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>					
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 40}"><!--N° TICKETS DE BÁSCULA DE ENTRADA EN ALMACÉN -->
					<td>
						<s:textfield  id="noTickets" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="14"  value="%{descripcion}"  onblur="validaNumero(this.value, 'noTickets', 1);"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 41}"><!-- VOLUMEN RECIBIDO -->
					<td>
						<s:textfield  id="volumenRecibido" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="14"  value="%{descripcion}" onblur="validarVolumen(this.value, 'volumenRecibido', 1);"/>
					</td>
				</s:elseif>
			</tr>
			<s:set name="temporal">
				<s:property value="idGrupo" />
			</s:set>
		</s:iterator>
	</table>
	</div>
	<div class="accion">
		<s:if test="registrar == 0 || registrar == 2">
			<s:submit  value="Guardar" cssClass="boton2" />
			<s:if test="claveBodega != null && claveBodega !='' ">
				<a href="<s:url value="/relaciones/mostrarTerrestreByBodegaOPlantaProcesadora?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&estadoBodega=%{estadoBodega}"/>" class="boton" title="" >Cancelar</a>
			</s:if>
			<s:else>
				<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="" >Cancelar</a>
			</s:else>
		</s:if>
	</div>
	<s:if test="registrar == 1  && #folioCartaAdhesion1 == null">">
		<div class="derecha">
			<s:if test="%{claveBodega != null  && claveBodega != '' }">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodega}&estadoBodega=%{estadoBodega}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:if>
			<s:else>
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:else>		
		</div>
	</s:if>
	<div class="izquierda">		
		<s:if test="%{claveBodega != null  && claveBodega != ''}">
			<a href="<s:url value="/relaciones/mostrarTerrestreByBodegaOPlantaProcesadora?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&estadoBodega=%{estadoBodega}"/>" class="" title="" >&lt;&lt; Regresar</a>
		</s:if>
		<s:else>
			<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="" title="" >&lt;&lt; Regresar</a>
		</s:else>		
	</div>
	<s:if test="registrar==1">
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');
			});	 	
		</script>
	</s:if>	
	<s:elseif test="registrar == 2">
		<script>
			if($('#folioTalonTerrestreTemp').length){
				$(document).ready(function() {
					$("#folioTalonTerrestreTemp").attr('disabled','disabled');
				});
			}	
		</script>
	</s:elseif>			
		
</s:form>