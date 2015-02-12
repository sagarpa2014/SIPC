<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:form action="registraCapturaRelacion"  id="target" onsubmit="return chkCamposCapturaRelaciones();">
	<!-- Encabezado de la relacion -->
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>	
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
	<s:hidden id="idRelacion" name="idRelacion" value="%{idRelacion}"/>	
	<s:hidden id="folioFacVenta" name="folioFacVenta" value="%{folioFacVenta}"/>
	<s:set name= "folioCartaAdhesion1"  value="%{folioCartaAdhesion}"></s:set>
	<div class="borderBottom" style="text-align:center"><h1>Encabezado de la Relaci&oacute;n de Ventas</h1></div><br>				
	<table style="width:100%">
		<tr>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<tr>
			<td>
				PRODUCTO A  APOYAR:
			</td>
			<td>
				<s:if test="%{cultivoRelacion != null && cultivoRelacion != ''}">
					<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange="" disabled="true"/>
				</s:if>
				<s:else>
					<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange=""/>
				</s:else>
			</td>			
		</tr>
		<tr>
			<td>
				CICLO AGRICOLA DEL REGISTRO:
			</td>
			<td>
				<s:if test="%{cultivoRelacion != null && cultivoRelacion != ''}">
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" disabled="true"/>
				</s:if>
				<s:else>
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}"/>
				</s:else>		
			</td>			
		</tr>	
		<s:iterator value="lstGpoCampoVentasRelEncabezadoV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{campo}"/></td>
				<s:if test="%{idCampo == 1}">
					<td align="justify" >
						<s:textfield id="idComp" name="nombre" size="100"  value="%{nombre}" disabled="true"/>
					</td>
				</s:if>
				<s:elseif test="%{idCampo == 2 }">
					<s:if test="%{#folioCartaAdhesion1 == null}">
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
		<s:iterator value="lstGpoCampoVentasRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<s:if test='#temporal!=idGrupo'>
					<td><s:property value="grupo"/></td>
				</s:if>
				<s:else>
					<td>&nbsp;</td>
				</s:else>
				<td align="center" ><s:property value="%{#itStatus.count}"/></td>
				<td><s:property value="%{campo}"/></td>
				<s:if test="%{idGrupo == 27 && idCampo == 8}"><!-- Nombre o razon social -->
					<td>
						<s:textfield id="nombreRazonSocial" name="descripcionDet[%{idCampoRelacion}]"  maxlength="150" size="50"  value="%{descripcion}"/>
					</td>
				</s:if>
				<s:elseif test="%{idGrupo == 27 && idCampo == 58}"><!--Domicilio-->
					<td>
						<s:textfield id="domicilio" name="descripcionDet[%{idCampoRelacion}]"  maxlength="150" size="50"  value="%{descripcion}"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 27 && idCampo == 59}"><!--Telefono con clave-->
					<td>
						<s:textfield  id="telefono" name="descripcionDet[%{idCampoRelacion}]"  maxlength="150" size="50"  value="%{descripcion}"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 28 && idCampo == 16}"><!-- Fecha -->
					<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
						<td align="justify">	
						<s:textfield id="selectedFecha" name="descripcionDet[%{idCampoRelacion}]" maxlength="10" size="10"  readonly="true" cssClass="dateBox" value="%{descripcion}"/>
						<img src="../images/calendar.gif" id="trgE" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
						<!--
							Calendar.setup({
								inputField     :    'selectedFecha',     
								ifFormat       :    "%d/%m/%Y",     
								button         :    'trgE',  
								align          :    "Br",           
								singleClick    :    true
								});
							//-->
							</script>
						</td>		
					</s:if>
					<s:elseif test="descripcion !=null && registrar == 1">
					<td align="justify">
						<s:textfield id="selectedFecha%{#itStatus.count}" key="selectedFecha"  value="%{descripcion}" maxlength="10" size="10"  readonly="true" cssClass="dateBox"/>
					</td>
					</s:elseif>
				</s:elseif>
				<s:elseif test="%{idGrupo == 28 && idCampo == 21}"><!-- Folio Factura Venta -->
					<td align="justify">
						<s:textfield id="folioFacVentaTemp" name="descripcionDet[%{idCampoRelacion}]"  maxlength="50" size="14"  value="%{descripcion}" onblur="validarFolioFacVenta(this.value);"/>
						<div id="validarFolioFacVenta"></div>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 28 && idCampo == 60}"><!-- Volumen -->
					<td align="justify">
						<s:textfield  id="volumen" name="descripcionDet[%{idCampoRelacion}]" maxlength="15" size="20"  value="%{descripcion}" onblur="validarVolumen(this.value, 'volumen', 1);"/>					
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 29 && idCampo == 61}"><!-- Estado -->
					<td align="justify">
						<s:select  id="idEstadoDet" name="descripcionDet[%{idCampoRelacion}]" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{descripcion}"/>
					</td>
				</s:elseif>
				<s:elseif test="%{idGrupo == 30 && idCampo == 62}"><!-- Uso del grano -->
					<td align="justify">
						<s:textfield  id="usoGrano" name="descripcionDet[%{idCampoRelacion}]" maxlength="30" size="20"  value="%{descripcion}" onblur=""/>
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
		<s:if test="registrar == 0 || registrar == 2">
			<s:submit  value="Guardar" cssClass="boton2" />
			<s:if test="%{cultivoRelacion != null  && cultivoRelacion != ''}">
				<a href="<s:url value="/relaciones/mostrarVentasByCicloCultivo?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}"/>" class="boton" title="" >Cancelar</a>
			</s:if>
			<s:else>
				<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="" >Cancelar</a>
			</s:else>
		</s:if>
	</div>	
	<s:if test="registrar == 1  && #folioCartaAdhesion1 == null">
		<div class="derecha">
			<s:if test="%{cultivoRelacion != null  && cultivoRelacion != ''}">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:if>
			<s:else>
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:else>		
		</div>
	</s:if>	
	
	<div class="izquierda">
		<s:if test="%{cultivoRelacion != null  && cultivoRelacion != ''}">
			<a href="<s:url value="/relaciones/mostrarVentasByCicloCultivo?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRelacion}&idPgrCiclo=%{idPgrCiclo}"/>" class="" title="" >&lt;&lt; Regresar</a>
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
			if($('#folioFacVentaTemp').length){
				$(document).ready(function() {
					$("#folioFacVentaTemp").attr('disabled','disabled');
				});
			}	
		</script>
	</s:elseif>
</s:form>