<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:form action="registraCapturaRelacion"  id="target" onsubmit="return chkCamposCapturaRelaciones();">
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
	<s:hidden id="idRelacion" name="idRelacion" value="%{idRelacion}"/>
	<s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>
	<s:hidden id="errorClaveBodegaExiste" name="errorClaveBodegaExiste" value="%{errorClaveBodegaExiste}"/>
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="folioTalonMaritimo" name="folioTalonMaritimo" value="%{folioTalonMaritimo}"/>
	<s:set name= "claveBodega1" value="%{claveBodega}"></s:set>
	<s:set name= "nombreBarco1" value="%{nombreBarco}"></s:set>
	<s:set name= "lugarDestino1" value="%{lugarDestino}"></s:set>
	<s:set name= "folioCartaAdhesion1"  value="%{folioCartaAdhesion}"></s:set>
	<div class="inline">
		<label class="left1">Seleccione el Tipo de Tramo:</label>
		<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
			<s:radio label="" onclick=""  name="tipoFormatoMaritimo" list="#{0:'BODEGA DE ORIGEN - PUERTO DE EMBARQUE', 1:'PLANTA PROCESADORA - PUERTO DE EMBARQUE'}" value="%{tipoFormatoMaritimo}" disabled="true"/>
		</s:if>
		<s:else>
			<s:radio label="" onclick="validaClaveBodega();"  name="tipoFormatoMaritimo" list="#{0:'BODEGA DE ORIGEN - PUERTO DE EMBARQUE', 1:'PLANTA PROCESADORA - PUERTO DE EMBARQUE'}" value="%{tipoFormatoMaritimo}" />
		</s:else>		
	</div>
	<div class="clear"></div>
	<div class="borderBottom" style="text-align:center"><h1>Encabezado de la Relaci&oacute;n de Movilizacion Maritima</h1></div><br>
	<table style="width:100%">
		<tr>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<tr>
			<td>
				CICLO AGRICOLA DEL REGISTRO:
			</td>
			<td>
				<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass" />
			</td>	
		</tr>
		<s:iterator value="lstGpoCampoMaritimaRelEncabezadoV" var="resultado"  status="itStatus">
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
					<td align="justify">
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="50" size="50"  value="%{#claveBodega1}"  disabled="true" />
						</s:if>
						<s:else>
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="50" size="50"  value="%{#claveBodega1}" onkeyup="validaClaveBodega();"/>
						</s:else>
						<div id="validaClaveBodega"></div>
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
				<s:elseif test="idCampo ==44 && idGrupo == 19">
					<td>
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:textfield cssClass="caracter" id="nombreBarco" name="nombreBarco"  maxlength="150" size="50"  value="%{#nombreBarco1}" disabled="true"/>
						</s:if>
						<s:else>
							<s:textfield cssClass="caracter" id="nombreBarco" name="nombreBarco"  maxlength="150" size="50"  value="%{#nombreBarco1}" onblur="validaClaveBodega();"/>
						</s:else>
						
					</td>
				</s:elseif>
				<s:elseif test="idCampo ==45 && idGrupo == 20" >
					<td align="justify">
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:textfield cssClass="caracter" id="lugarDestino" name="lugarDestino"  maxlength="150" size="50"  value="%{#lugarDestino1}" disabled="true"/>
						</s:if>
						<s:else>
							<s:textfield cssClass="caracter" id="lugarDestino" name="lugarDestino"  maxlength="150" size="50"  value="%{#lugarDestino1}" onblur="validaClaveBodega();"/>
						</s:else>
					</td>
				</s:elseif>
			</tr>
		</s:iterator>
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
		<s:iterator value="lstGpoCampoMaritimaRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<s:if test='#temporal!=idGrupo'>
					<td><s:property value="grupo"/></td>
				</s:if>
				<s:else>
					<td>&nbsp;</td>
				</s:else>
				<td align="center" ><s:property value="%{#itStatus.count}"/></td>
				<td><s:property value="%{campo}"/></td>
				<s:if test="%{idGrupo == 15 && idCampo == 46}">
					<td>
						<s:select cssClass="lstEstados" id="edoOrigen" name="descripcionDet[%{idCampoRelacion}]" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{descripcion}"/>
					</td>
				</s:if>
				<s:elseif test="%{idGrupo == 15 && idCampo == 32}">
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
						<s:textfield id="selectedFechaEmbarque" name="descripcionDet[%{idCampoRelacion}]" maxlength="12" size="12"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
						<img src="../images/calendar.gif" id="trgE" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
						<!--
							Calendar.setup({
								inputField     :    'selectedFechaEmbarque',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgE',  
								align          :    "Br",           
								singleClick    :    true
								});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="registrar == 1">
						<td align="justify">
							<s:textfield id="selectedFechaEmbarque" key="selectedFecha"  value="%{descripcion}" maxlength="12" size="12"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>
				</s:elseif>
				<s:elseif test="%{idGrupo == 15 && idCampo == 47}">
					<td >
						<s:textfield  id="folioTalonMaritimoTemp" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="14"  value="%{descripcion}" onblur="validarFolioTalonMaritima();"/>
						<div id="validarFolioTalonMaritima"></div>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 15 && idCampo == 34}">
					<td>
						<s:textfield  id="volumenEmbarcado" name="descripcionDet[%{idCampoRelacion}]" maxlength="15" size="20"  value="%{descripcion}" onblur="validarVolumen(this.value, 'volumenEmbarcado', 1);"/>					
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 48}">
					<td align="justify">
						<s:textfield  id="noConocimiento" name="descripcionDet[%{idCampoRelacion}]"  maxlength="150" size="50"  value="%{descripcion}"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 36}">
					<td align="justify">
						<s:textfield  id="puertoEmbarque" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="50"  value="%{descripcion}" onblur=""/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 39}">
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
						<s:textfield id="selectedFechaRec" name="descripcionDet[%{idCampoRelacion}]" maxlength="12" size="12"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
						<img src="../images/calendar.gif" id="trgRec" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
						<!--
							Calendar.setup({
								inputField     :    'selectedFechaRec',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgRec',  
								align          :    "Br",           
								singleClick    :    true
								});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="registrar == 1">
						<td align="justify">
							<s:textfield id="selectedFechaRec" key="selectedFecha"  value="%{descripcion}" maxlength="12" size="12"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 49}">
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
						<s:textfield id="selectedFechaEmi" name="descripcionDet[%{idCampoRelacion}]" maxlength="12" size="12"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
						<img src="../images/calendar.gif" id="trgEmi" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
						<!--
							Calendar.setup({
								inputField     :    'selectedFechaEmi',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgEmi',  
								align          :    "Br",           
								singleClick    :    true
								});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="registrar == 1">
					<td align="justify">
						<s:textfield id="selectedFechaEmi" key="selectedFecha"  value="%{descripcion}" maxlength="12" size="12"  readonly="true" cssClass="dateBox" />
					</td>
					</s:elseif>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 40}">
					<td align="justify">
						<s:textfield cssClass="numero" id="noTickets" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="14"  value="%{descripcion}" onblur="validaNumero(this.value, 'noTickets', 1);"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 50}">
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
						<s:textfield id="selectedFechaSal" name="descripcionDet[%{idCampoRelacion}]" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
						<img src="../images/calendar.gif" id="trgSal" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
						<!--
							Calendar.setup({
								inputField     :    'selectedFechaSal',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgSal',  
								align          :    "Br",           
								singleClick    :    true
								});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="registrar == 1">
						<td align="justify">
							<s:textfield id="selectedFechaSal" key="selectedFecha"  value="%{descripcion}" maxlength="12" size="12"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 51}">
					<td align="justify">
						<s:textfield cssClass="caracter" id="puertoDescarga" name="descripcionDet[%{idCampoRelacion}]"  maxlength="150" size="50"  value="%{descripcion}"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 16 && idCampo == 41}">
					<td align="justify">
						<s:textfield cssClass="" id="volumenRecibido" name="descripcionDet[%{idCampoRelacion}]"  maxlength="20" size="15"  value="%{descripcion}"   onblur="validarVolumen(this.value, 'volumenRecibido', 1);"/>
					</td>
				</s:elseif>
				
			</tr>
			<s:set name="temporal">
				<s:property value="idGrupo"/>
			</s:set>
		</s:iterator>
	</table>
	</div>
	<div class="accion">
		<s:if test="registrar == 0 || registrar == 2 ">
			<s:submit  value="Guardar" cssClass="boton2" />
			<s:if test="claveBodega != null && claveBodega !='' ">
				<a href="<s:url value="/relaciones/mostrarMaritimaByBodegaOPlantaProcesadora?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodega}&nombreBarco=%{nombreBarco}&lugarDestino=%{lugarDestino}"/>" class="boton" title="" >Cancelar</a>
			</s:if>
			<s:else>
				<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="" >Cancelar</a>
			</s:else>
		</s:if>
	</div>	
	<s:if test="registrar == 1  && #folioCartaAdhesion1 == null">">
		<div class="derecha">
			<s:if test="%{claveBodega != null  && claveBodega != '' }">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&nombreBarco=%{nombreBarco}&lugarDestino=%{lugarDestino}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:if>
			<s:else>
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:else>		
		</div>
	</s:if>	
	<div class="izquierda">
		<s:if test="%{claveBodega != null  && claveBodega != ''}">
			<a href="<s:url value="/relaciones/mostrarMaritimaByBodegaOPlantaProcesadora?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodega}&nombreBarco=%{nombreBarco}&lugarDestino=%{lugarDestino}"/>" class="" title="" >&lt;&lt; Regresar</a>
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
			if($('#folioTalonMaritimoTemp').length){
				$(document).ready(function() {
					$("#folioTalonMaritimoTemp").attr('disabled','disabled');
				});
			}	
		</script>
	</s:elseif>	
</s:form>