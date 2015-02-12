<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>

<s:form action="registraCapturaRelacion"  id="target" onsubmit="return chkCamposCapturaRelaciones();">
	<!-- Encabezado de la relacion -->
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
	<s:hidden id="idRelacion" name="idRelacion" value="%{idRelacion}"/>
	<s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>
	<s:hidden id="errorClaveBodegaExiste" name="errorClaveBodegaExiste" value="%{errorClaveBodegaExiste}"/>
	<div class="borderBottom" style="text-align:center"><h1>Encabezado de la Relaci&oacute;n de Certificados</h1></div><br>
	<s:set name= "claveBodega1" value="%{claveBodega}"></s:set>
	<s:set name= "razonSocialAlmacen1" value="%{razonSocialAlmacen}"></s:set>
	<s:set name= "domicilioBodega1" value="%{domicilioBodega}"></s:set>
	<s:set name= "folioCartaAdhesion1"  value="%{folioCartaAdhesion}"></s:set>
	
	<s:hidden id="" name="folioCertificado" value="%{folioCertificado}"/>	
	
	<table style="width:100%">
		<tr>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<tr>
			<td>PRODUCTO A APOYAR</td>
			<td align="justify">
				<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
					<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange="" disabled="true"/>
				</s:if>
				<s:else>
					<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange="validaClaveBodega();"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<td>
				CICLO AGRICOLA DEL REGISTRO:
			</td>
			<td>
				<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass" disabled="true"/>
				</s:if>
				<s:else>
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass" onchange="validaClaveBodega();"/>
				</s:else>
			</td>	
		</tr>	
		<s:iterator value="lstGpoCampoCertificadosRelEncabezadoV" var="resultado"  status="itStatus">
			<tr>
				<td>
					<s:property value="%{campo}"/>
				</td>
				<s:if test="%{idCampo == 1}">
					<td>
						<s:textfield id="idComp" name="nombre" size="100"  value="%{nombre}" disabled="true"/>
					</td>
				</s:if>
				<s:elseif test="%{idCampo == 2 }">
					<s:if test="%{#folioCartaAdhesion1 ==null}">
						<td align="justify">
							<s:textfield cssClass="folioCarta" id="folioCarta" name="folioCartaAdhesion" maxlength="50" size="50"  value="No se ha registrado Carta de Adhesion" disabled="true"/>
						</td>
					</s:if>
					<s:else>
						<td>
							<s:textfield cssClass="folioCarta" id="folioCarta" name="folioCartaAdhesion" maxlength="50" size="50"  value= "%{#folioCartaAdhesion1}" disabled="true"/>
						</td>
					</s:else>
				</s:elseif>
				<s:elseif test="%{idCampo == 4}">
					<td align="justify">
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{#claveBodega1}" disabled="true"/>
						</s:if>
						<s:else>
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{#claveBodega1}" onkeyup="validaClaveBodega(this.value);" onchange="validaClaveBodega();"/>
						</s:else>
						<div id="validaClaveBodega"></div>
					</td>
				</s:elseif>				
				<s:if test="%{idCampo ==52}">
					<td align="justify">
						<s:textfield cssClass="caracter" id="domicilioBodega" name="domicilioBodega"  maxlength="150" size="50"  value="%{#domicilioBodega1}" disabled="true"/>
					</td>
				</s:if>
				<s:if test="%{idCampo ==53}" >
					<td align="justify">
						<s:if test="#razonSocialAlmacen1 != null && #razonSocialAlmacen1 != ''">
							<s:select cssClass="lstAlmacenes" id="razonSocialAlmacen" name="razonSocialAlmacen" list="lstAlmacenes" listKey="idAlmacen" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{#razonSocialAlmacen1}" disabled="true"/>
						</s:if>
						<s:else>
							<s:select cssClass="lstAlmacenes" id="razonSocialAlmacen" name="razonSocialAlmacen" list="lstAlmacenes" listKey="idAlmacen" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{#razonSocialAlmacen1}" onchange="validaClaveBodega();"/>
						</s:else>
					</td>
				</s:if>
			</tr>
		</s:iterator>
		
	</table>
	<div id="relaciones">
	<!-- Detalle de la relacion -->
	<div class="borderBottom" style="text-align:center"><h1>Detalle de la Relaci&oacute;n</h1></div><br>
	<table style="width:100%">
		<tr>
			<th>No. Campo</th>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<s:iterator value="lstGpoCampoCertificadosRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<td align="center" ><s:property value="%{#itStatus.count}"/></td>
				<td><s:property value="%{campo}"/></td>
				<s:if test="%{idGrupo == 24 && idCampo == 55}">
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
							<s:textfield id="selectedFechaExp" name="descripcionDet[%{idCampoRelacion}]" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
							<img src="../images/calendar.gif" id="trgExp<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
							<script type="text/javascript">
							<!--
								Calendar.setup({
									inputField     :    'selectedFechaExp',     
									ifFormat       :    "%d/%m/%Y",     
									button         :    'trgExp<s:property value="%{#itStatus.count}" />',  
									align          :    "Br",           
									singleClick    :    true
									});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="descripcion !=null && registrar == 1">
						<td align="justify">
							<s:textfield id="selectedFechaExp%{#itStatus.count}" key="selectedFecha"  value="%{descripcion}" maxlength="10" size="10"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>
				</s:if>
				<s:if test="%{idGrupo == 25 && idCampo == 56}">
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
							<s:textfield id="selectedFechaVig" name="descripcionDet[%{idCampoRelacion}]" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
							<img src="../images/calendar.gif" id="trgVig<s:property value="%{#itStatus.count}" />" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
							<script type="text/javascript">
							<!--
								Calendar.setup({
									inputField     :    'selectedFechaVig',     
									ifFormat       :    "%d/%m/%Y",     
									button         :    'trgVig<s:property value="%{#itStatus.count}"/>',  
									align          :    "Br",           
									singleClick    :    true
									});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="descripcion !=null && registrar == 1">
						<td align="justify">
							<s:textfield id="selectedFechaVig" key="selectedFecha"  value="%{descripcion}" maxlength="10" size="10"  readonly="true" cssClass="dateBox" />
						</td>
					</s:elseif>
				</s:if>
				<s:elseif test="%{idGrupo == 23 && idCampo == 54}">
					<td align="justify">
						<s:textfield cssClass="folio" id="folioCertificado" name="descripcionDet[%{idCampoRelacion}]"  maxlength="10" size="14"  value="%{descripcion}" onblur="validaFolioCertificadoDeposito(this.value);"/>
						<div id="validaFolioCertificadoDeposito"></div>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 26 && idCampo == 57}">
					<td align="justify">
						<s:textfield cssClass="volumen" id="volumenAmparado" name="descripcionDet[%{idCampoRelacion}]" maxlength="15" size="14"  value="%{descripcion}" onblur="validarVolumen(this.value, 'volumenAmparado', 1);"/>					
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
				<a href="<s:url value="/relaciones/lstCertificadoPorBodega?idIniEsquemaRelacion=8&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&razonSocialAlmacen=%{razonSocialAlmacen1}"/>" class="boton" title="" >Cancelar</a>
			</s:if>
			<s:else>
				<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="" >Cancelar</a>
			</s:else>
		</s:if>
	</div>	
	<s:if test="registrar == 1 && #folioCartaAdhesion1 == null">
		<div class="derecha">
			<s:if test="%{claveBodega != null  && claveBodega != '' }">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}&razonSocialAlmacen=%{razonSocialAlmacen1}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:if>
			<s:else>
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:else>		
		</div>
	</s:if>	
	
	<div class="izquierda">
		<s:if test="%{claveBodega != null  && claveBodega != '' }">
			<a href="<s:url value="/relaciones/lstCertificadoPorBodega?idIniEsquemaRelacion=8&idPerRel=32&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=9&razonSocialAlmacen=%{razonSocialAlmacen1}"/>" class="" title="" >&lt;&lt; Regresar</a>
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
			if($('#folioCertificado').length){
				$(document).ready(function() {
					$("#folioCertificado").attr('disabled','disabled');
				});
			}	
		</script>
	</s:elseif>		
</s:form>