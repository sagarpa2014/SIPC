<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:if test="lstComprasDatosParticipanteV.size() > 0">
	<table >
		<tr>
			<th>Bodega</th>
			<th>Estado</th>
			<th>Cultivo</th>
			<th>Ciclo Agr&iacute;cola</th>
			<th>Carta Adhesi&oacute;n</th>
			<th>Agregar Registro</th>
			<th>Ver Detalle</th>
			<th>Exportar</th>
		</tr>
		<s:iterator value="lstComprasDatosParticipanteV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{claveBodega}"/></td>
				<td><s:property value="%{estado}"/></td>
				<td><s:property value="%{cultivo}"/></td>
				<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
				<td>
					<s:if test="%{folioCartaAdhesion != null}">
						<s:property value="%{folioCartaAdhesion}"/>
					</s:if>					
				</td>
				<td style="text-align: center">
					<s:if test="%{folioCartaAdhesion == null}">
						<a href="<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&claveBodega=%{claveBodega}"/>" class="" title="Agregar">Agregar</a>
					</s:if>
				</td>
				<td style="text-align: center"><a href="<s:url value="/relaciones/lstProductorRelCompras?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}&estadoBodega=%{estadoAcopio}" />" class="" title="Ver Detalle">Ver</a></td>
				<td style="text-align: center"><a href="#" class="" title="Agregar " onclick="exportarRelacion(1,'<s:property value="%{claveBodega}"/>', <s:property value="%{idCompPer}"/>,1,<s:property value="idIniEsquemaRelacion"/>,<s:property value="%{idPerRel}"/>,0,0,0,0,0,0);">Exportar</a></td>
			</tr>		
		</s:iterator>	
	</table>
</s:if>
<s:elseif test="lstGpoCampoCertificadosRelDetalleV.size() > 0"><!-- CERTIFICADOS DE DEPOSITO -->
	<table >
		<tr>
			<th>Bodega</th>
			<th>Almacen</th>
			<th>Cultivo</th>
			<th>Ciclo Agr&iacute;cola</th>
			<th>Carta Adhesi&oacute;n</th>
			<th>Agregar Registro</th>
			<th>Ver Detalle</th>
			<th>Exportar</th>
		</tr>
		<s:iterator value="lstGpoCampoCertificadosRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{claveBodega}"/></td>
				<td><s:property value="%{almacen}"/></td>
				<td><s:property value="%{cultivo}"/></td>
				<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
				<td>
					<s:if test="%{folioCartaAdhesion != null}">
						<s:property value="%{folioCartaAdhesion}"/>
					</s:if>					
				</td>
				<td style="text-align: center">
					<s:if test="%{folioCartaAdhesion == null}">
						<a href="<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&razonSocialAlmacen=%{razonSocialAlmacen}"/>" class="" title="Agregar">Agregar</a>
					</s:if>
				</td>
				<td style="text-align: center"><a href="<s:url value="/relaciones/lstCertificadoPorBodega?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&razonSocialAlmacen=%{razonSocialAlmacen}" />" class="" title="Ver Detalle">Ver</a></td>
				<td style="text-align: center"><a href="#" class="" title=" " onclick="exportarRelacion(1,'<s:property value="%{claveBodega}"/>', 0, <s:property value="idRelacion"/>,<s:property value="idIniEsquemaRelacion"/>, <s:property value="%{idPerRel}"/>,<s:property value="%{cultivoRegistro}"/>,<s:property value="%{idPgrCiclo}"/>,<s:property value="%{razonSocialAlmacen}"/>,0,0,0);">Exportar</a></td>
			</tr>		
		</s:iterator>	
	</table>
</s:elseif>
<s:elseif test="lstGpoCampoVentasRelDetalleV.size() > 0"><!--RELACION DE VENTAS -->
	<table >
		<tr>
			<th>Cultivo</th>
			<th>Ciclo Agr&iacute;cola</th>
			<th>Carta Adhesi&oacute;n</th>
			<th>Agregar Registro</th>
			<th>Ver Detalle</th>
			<th>Exportar</th>
		</tr>
		<s:iterator value="lstGpoCampoVentasRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{cultivo}"/></td>
				<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
				<td>
					<s:if test="%{folioCartaAdhesion != null}">
						<s:property value="%{folioCartaAdhesion}"/>
					</s:if>					
				</td>
				<td style="text-align: center">
					<s:if test="%{folioCartaAdhesion == null}">
						<a href="<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}"/>" class="" title="Agregar">Agregar</a>
					</s:if>
				</td>
				<td style="text-align: center"><a href="<s:url value="/relaciones/mostrarVentasByCicloCultivo?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}" />" class="" title="Ver Detalle">Ver</a></td>
				<td style="text-align: center"><a href="#" class="" title=" " onclick="exportarRelacion(1,'<s:property value="%{claveBodega}"/>', 0, <s:property value="idRelacion"/>,<s:property value="idIniEsquemaRelacion"/>, <s:property value="%{idPerRel}"/>,<s:property value="%{cultivoRegistro}"/>,<s:property value="%{idPgrCiclo}"/>,0,0,0,0);">Exportar</a></td>
			</tr>		
		</s:iterator>	
	</table>
</s:elseif>
<s:elseif test="lstGpoCampoTerrestreRelDetalleV.size() > 0"><!--RELACION TERRESTRE -->
	<table >
		<tr>
			<th>Bodega / Planta Procesadora</th>
			<th>Estado</th>
			<th>Cultivo</th>
			<th>Ciclo Agr&iacute;cola</th>
			<th>Carta Adhesi&oacute;n</th>
			<th>Agregar Registro</th>
			<th>Ver Detalle</th>
			<th>Exportar</th>
		</tr>
		<s:iterator value="lstGpoCampoTerrestreRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{claveBodegaOpp}"/></td>
				<td><s:property value="%{estado}"/></td>
				<td><s:property value="%{cultivo}"/></td>
				<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
				<td>
					<s:if test="%{folioCartaAdhesion != null}">
						<s:property value="%{folioCartaAdhesion}"/>
					</s:if>					
				</td>
				<td style="text-align: center">
					<s:if test="%{folioCartaAdhesion == null}">
						<a href="<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodegaOpp}&estadoBodega=%{estadoBodega}"/>" class="" title="Agregar">Agregar</a>
					</s:if>
				</td>
				<td style="text-align: center"><a href="<s:url value="/relaciones/mostrarTerrestreByBodegaOPlantaProcesadora?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodegaOpp}&estadoBodega=%{estadoBodega}" />" class="" title="Ver Detalle">Ver</a></td>
				<td style="text-align: center"><a href="#" class="" title=" " onclick="exportarRelacion(1,'<s:property value="%{claveBodegaOpp}"/>', 0, <s:property value="idRelacion"/>,<s:property value="idIniEsquemaRelacion"/>, <s:property value="%{idPerRel}"/>,<s:property value="%{cultivoRegistro}"/>,<s:property value="%{idPgrCiclo}"/>,0,<s:property value="%{estadoBodega}"/>, 0, 0);">Exportar</a></td>
			</tr>		
		</s:iterator>	
	</table>
</s:elseif>
<s:elseif test="lstGpoCampoMaritimaRelDetalleV.size() > 0"><!-- MARITIMA -->
	<table>
		<tr>
			<th>Bodega/Lugar de Embarque</th>
			<th>Barco</th>
			<th>Lugar Destino</th>
			<th>Cultivo</th>
			<th>Ciclo Agr&iacute;cola</th>
			<th>Carta Adhesi&oacute;n</th>
			<th>Agregar Registro</th>
			<th>Ver Detalle</th>
			<th>Exportar</th>
		</tr>
		<s:iterator value="lstGpoCampoMaritimaRelDetalleV" var="resultado"  status="itStatus">
			<tr>
				<td><s:property value="%{claveBodegaOpp}"/></td>
				<td><s:property value="%{nombreBarco}"/></td>
				<td><s:property value="%{lugarDestino}"/></td>
				<td><s:property value="%{cultivo}"/></td>
				<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
				<td>
					<s:if test="%{folioCartaAdhesion != null}">
						<s:property value="%{folioCartaAdhesion}"/>
					</s:if>					
				</td>
				<td style="text-align: center">
					<s:if test="%{folioCartaAdhesion == null}">
						<a href="<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodegaOpp}&nombreBarco=%{nombreBarco}&lugarDestino=%{lugarDestino}"/>" class="" title="Agregar">Agregar</a>
					</s:if>	
				</td>
				<td style="text-align: center"><a href="<s:url value="/relaciones/mostrarMaritimaByBodegaOPlantaProcesadora?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idPerRel=%{idPerRel}&cultivoRelacion=%{cultivoRegistro}&idPgrCiclo=%{idPgrCiclo}&claveBodega=%{claveBodegaOpp}&nombreBarco=%{nombreBarco}&lugarDestino=%{lugarDestino}" />" class="" title="Ver Detalle">Ver</a></td>
				<td style="text-align: center"><a href="#" title=" " onclick="exportarRelacion(1,'<s:property value="%{claveBodega}"/>', 0, <s:property value="idRelacion"/>,<s:property value="idIniEsquemaRelacion"/>, <s:property value="%{idPerRel}"/>,<s:property value="%{cultivoRegistro}"/>,<s:property value="%{idPgrCiclo}"/>, 0,0, '<s:property value="%{nombreBarco}"/>', '<s:property value="%{lugarDestino}"/>');">Exportar</a></td>
			</tr>		
		</s:iterator>	
	</table>
</s:elseif>
<s:else><div class="advertencia">No se encontraron registros </div></s:else>