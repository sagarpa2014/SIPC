<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>

<s:if test="hasActionErrors()">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
</s:if>
<div id="dialogo_1"></div>	
<s:form action="registraCartaAdhesion"  id="target" onsubmit="return chkCamposRegistroCartaAdhesion();"> 
	<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
	<s:hidden id="idRelacion" name="idRelacion" value="%{idRelacion}"/>
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<div class="borderBottom"><h1>Registro de Carta de Adhesi&oacute;n</h1></div><br>
		<fieldset>
			<s:include value="datosGeneralesRelCompras.jsp" />	
			<s:if test="lstComprasDatosParticipanteV.size() > 0">
				<div>
					<label class="left1"><span class="requerido">*</span>Carta Adhesi&oacute;n:</label>
					<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" size="30"  value="%{}"/>
				</div>
				<br>
				<div>
					<p><span class="requerido">*&nbsp;Debe marcar los registros para la asignación de carta</span></p>
				</div>
				<table style="width:100%">
					<tr>
						<th><input id="o1" name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>
						<th>Bodega</th>
						<th>Estado</th>
						<th>Cultivo</th>
						<th>Ciclo Agr&iacute;cola</th>
					</tr>
					<s:hidden id="numDatos" name="numDatos" value="%{lstComprasDatosParticipanteV.size()}"/>
					<s:iterator value="lstComprasDatosParticipanteV" var="resultado"  status="itStatus">
						<tr>
							<td style="text-align: center"><input id="ck<s:property value="itStatus.count"/>" name="idSComPer" value="<s:property value="idCompPer"/>" type="checkbox"  class="ck"/></td>
							<td><s:property value="%{claveBodega}"/></td>
							<td><s:property value="%{estado}"/></td>
							<td><s:property value="%{cultivo}"/></td>
							<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
						</tr>		
					</s:iterator>	
				</table>
				<div class="accion">
					<s:submit  value="Registrar" cssClass="boton2"/>
					<a href="<s:url value="listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="Cancelar">Cancelar</a>
				</div>
			</s:if>
		<s:elseif test="lstGpoCampoTerrestreRelDetalleV.size() > 0"><!-- RELACION TERRESTRE -->
			<div>
				<label class="left1"><span class="requerido">*</span>Carta Adhesi&oacute;n:</label>
				<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" size="30"  value="%{}"/>
			</div>
			<br>
			<div>
				<p><span class="requerido">*&nbsp;Debe marcar los registros para la asignación de carta</span></p>
			</div>
			<table style="width:100%">
				<tr>
					<th><input id="o1" name="o1" value="-1" type="checkbox"
						onclick="chkTodos();" class="check_todos" />
					</th>
					<th>Bodega / Planta Procesadora</th>
					<th>Cultivo</th>
					<th>Ciclo</th>
					<th>Ejercicio</th>
				</tr>
				<s:iterator value="lstGpoCampoTerrestreRelDetalleV" var="resultado"
					status="itStatus">
					<tr>
						<s:set name= "idRegCATemp"><s:property value="%{idPerRel}"/>-<s:property value="%{claveBodegaOpp}"/>-<s:property value="%{cultivoRegistro}"/>-<s:property value="%{idPgrCiclo}"/></s:set>
						<td style="text-align: center"><input id="ck<s:property value="itStatus.count"/>" name="idRegCA" value="<s:property value="%{idRegCATemp}"/>" type="checkbox"  class="ck"/></td>
						<td><s:property value="%{claveBodegaOpp}"/></td>
						<td><s:property value="%{cultivo}" /></td>
						<td><s:property value="%{cicloLargo}"/></td>
						<td><s:property value="%{ejercicio}"/></td>
					</tr>
				</s:iterator>
			</table>
			<div class="accion">
				<s:submit  value="Registrar" cssClass="boton2"/>
				<a href="<s:url value="listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="Cancelar">Cancelar</a>
			</div>			
		</s:elseif>
		<s:elseif test="lstGpoCampoMaritimaRelDetalleV.size() > 0">
			<!-- RELACION MARITIMA -->
				<div>
					<label class="left1"><span class="requerido">*</span>Carta Adhesi&oacute;n:</label>
					<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" size="30"  value="%{}"/>
				</div>
				<br>
				<div>
					<p><span class="requerido">*&nbsp;Debe marcar los registros para la asignación de carta</span></p>
				</div>
			<table style="width:100%">
				<tr>
					<th><input id="o1" name="o1" value="-1" type="checkbox"
						onclick="chkTodos();" class="check_todos" />
					</th>
					<th>Bodega / Planta Procesadora</th>
					<th>Barco</th>
					<th>Lugar Destino</th>
					<th>Cultivo</th>
					<th>Ciclo</th>
					<th>Ejercicio</th>
				</tr>
				<s:iterator value="lstGpoCampoMaritimaRelDetalleV" var="resultado"  status="itStatus">
					<tr>
						<s:set name= "idRegCATemp"><s:property value="%{idPerRel}"/>-<s:property value="%{claveBodegaOpp}"/>-<s:property value="%{cultivoRegistro}"/>-<s:property value="%{idPgrCiclo}"/>-<s:property value="%{nombreBarco}"/>-<s:property value="%{lugarDestino}"/></s:set>
						<td style="text-align: center"><input id="ck<s:property value="itStatus.count"/>" name="idRegCA" value="<s:property value="%{idRegCATemp}"/>" type="checkbox"  class="ck"/></td>
						<td><s:property value="%{claveBodegaOpp}"/></td>
						<td><s:property value="%{nombreBarco}"/></td>
						<td><s:property value="%{lugarDestino}"/></td>
						<td><s:property value="%{cultivo}"/></td>
						<td><s:property value="%{cicloLargo}"/></td>
						<td><s:property value="%{ejercicio}"/></td>
					</tr>		
				</s:iterator>	
			</table>
			<div class="accion">
				<s:submit  value="Registrar" cssClass="boton2"/>
				<a href="<s:url value="listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="Cancelar">Cancelar</a>
			</div>			
		</s:elseif>
		<s:elseif test="lstGpoCampoCertificadosRelDetalleV.size() > 0"><!-- CERTIFICADO DE DEPOSITO -->
				<div>
					<label class="left1"><span class="requerido">*</span>Carta Adhesi&oacute;n:</label>
					<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" size="30"  value="%{}"/>
				</div>
				<br>
				<div>
					<p><span class="requerido">*&nbsp;Debe marcar los registros para la asignación de carta</span></p>
				</div>
				<table style="width:100%">
					<tr>
						<th><input id="o1" name="o1" value="-1" type="checkbox"
							onclick="chkTodos();" class="check_todos" /></th>
						<th>Bodega</th>
						<th>Almacen</th>
						<th>Cultivo</th>
						<th>Ciclo Agr&iacute;cola</th>
					</tr>
					<s:iterator value="lstGpoCampoCertificadosRelDetalleV" var="resultado"  status="itStatus">
						<tr>
							<s:set name= "idRegCATemp"><s:property value="%{idPerRel}"/>-<s:property value="%{claveBodega}"/>-<s:property value="%{razonSocialAlmacen}"/>-<s:property value="%{cultivoRegistro}"/>-<s:property value="%{idPgrCiclo}"/></s:set>
							<td style="text-align: center"><input id="ck<s:property value="itStatus.count"/>" name="idRegCA" value="<s:property value="%{idRegCATemp}"/>" type="checkbox"  class="ck"/></td>
							<td><s:property value="%{claveBodega}"/></td>
							<td><s:property value="%{almacen}"/></td>
							<td><s:property value="%{cultivo}"/></td>
							<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
						</tr>		
					</s:iterator>
				</table>
				<div class="accion">
					<s:submit  value="Registrar" cssClass="boton2"/>
					<a href="<s:url value="listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="Cancelar">Cancelar</a>
				</div>	
			</s:elseif>
			<s:elseif test="lstGpoCampoVentasRelDetalleV.size() > 0"> <!-- RELACION DE VENTAS -->
				<div>
					<label class="left1"><span class="requerido">*</span>Carta Adhesi&oacute;n:</label>
					<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" size="30"  value="%{}"/>
				</div>
				<br>
				<div>
					<p><span class="requerido">*&nbsp;Debe marcar los registros para la asignación de carta</span></p>
				</div>
				<table style="width:100%">
					<tr>
						<th><input id="o1" name="o1" value="-1" type="checkbox"
							onclick="chkTodos();" class="check_todos" /></th>
						<th>Cultivo</th>
						<th>Ciclo Agr&iacute;cola</th>
					</tr>
					<s:iterator value="lstGpoCampoVentasRelDetalleV" var="resultado"  status="itStatus">
						<tr>
							<s:set name= "idRegCATemp"><s:property value="%{idPerRel}"/>-<s:property value="%{cultivoRegistro}"/>-<s:property value="%{idPgrCiclo}"/></s:set>
							<td style="text-align: center"><input id="ck<s:property value="itStatus.count"/>" name="idRegCA" value="<s:property value="%{idRegCATemp}"/>" type="checkbox"  class="ck"/></td>
							<td><s:property value="%{cultivo}"/></td>
							<td><s:property value="%{cicloLargo}"/>&nbsp;<s:property value="%{ejercicio}"/></td>
						</tr>		
					</s:iterator>
				</table>
				<div class="accion">
					<s:submit  value="Registrar" cssClass="boton2"/>
					<a href="<s:url value="listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="Cancelar">Cancelar</a>
				</div>	
			</s:elseif>
		</fieldset>
	
	<s:else><div class="advertencia">No se encontraron registros para asignaci&oacute;n de carta</div></s:else>
	<br>
	<div class="izquierda">
		<a href="<s:url value="listRelacionesCapturadas?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="" title="Cancelar">&lt;&lt; Regresar</a>
	</div>
</s:form>