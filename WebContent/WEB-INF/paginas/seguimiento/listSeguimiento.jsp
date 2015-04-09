<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/seguimiento.js" />"></script>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>SEGUIMIENTO DE ACOPIO</h1></div><br>
	<s:form action="consultaSeguimiento" id="" onsubmit="">
		<fieldset id="reporte1" class="clear">
			<legend>Criterios de B&uacute;squeda</legend>
			<table class="clean"style="width: 80%">
			<tr>
	  			<td >
	  				<label class="">Ciclo Ag&iacute;cola:</label>
	  			</td>
	  			<td >
	  				<s:select id="idCiclo" name="idCiclo" list="lstCiclos" listKey="idCiclo" listValue="%{cicloLargo}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{sca.idCiclo}" onchange="" />
	  			</td>
	  			<td colspan="2" >
	  				<s:select id="ejercicio" name="ejercicio" list="lstEjercicios" listKey="ejercicio" listValue="%{ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{sca.ejercicio}" onchange="" />
	  			</td>
			</tr>
			<tr>
	  			<td >
	  				<label class="left1">Clave Centro Acopio:</label>
	  			</td>
	  			<td colspan="3" >
	  				<s:textfield id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{}" onchange="" />		
	  			</td>
			</tr>
			</table>
			
			<div class="accion">	    	
				<s:submit  value="Consultar" cssClass="boton2"/>
			</div>
		</fieldset>
	<s:if test="%{#session.idPerfil==11}"> <!-- Regional -->
		<div class="derecha"><a href="<s:url value="/seguimiento/capSeguimiento"/>" onclick="" title="Agregar Seguimiento Acopio" >[Agregar Seguimiento Acopio]</a></div><br>
	</s:if>

	<s:if test="listSeguimientoCentroAcopioV.size() > 0">
		<fieldset id="reporte2">
			<legend>Resultado de B&uacute;squeda</legend>	
			<display:table id="r"  name="listSeguimientoCentroAcopioV"  list="listSeguimientoCentroAcopioV"  pagesize="50" sort="list" requestURI="/seguimiento/listSeguimiento"  class="displaytag">
				<display:column  property="claveBodega" title="Centro de Acopio"/>		
				<display:column  property="ciclo" title="Ciclo Agr&iacute;cola"/>
				<display:column  property="nombreCultivo" title="Cultivo"/>
				<display:column  property="nombreVariedad" title="Variedad"/>
				<display:column  title="Periodo Inicial">
					<s:text name="fecha"> <s:param value="%{#attr.r.periodoInicial}"/></s:text>
				</display:column>
				<display:column  title="Periodo Final">
					<s:text name="fecha"> <s:param value="%{#attr.r.periodoFinal}"/></s:text>
				</display:column>
				<display:column  property="descEstatus" title="Estatus"/>
				<display:column title="Vista Preliminar"  headerClass="sortable" >
					<a href="#" class="botonVistaPreliminar" title="" onclick="vistaPreviaOficio(<s:property value="%{#attr.r.idSeguimientoCA}"/>)"></a>
			 	</display:column>
			 	<s:if test="%{#session.idPerfil==11}"> <!-- Regional -->
				 	<display:column title="Generacion PDF" headerClass="sortable" >
						<a href='<s:url value="/seguimiento/generarReporteSeguimiento?idSeguimiento=%{#attr.r.idSeguimientoCA}"/>' class="botonPDF" onclick="if (confirm('¿Esta seguro de Generar Formato (PDF)?')){}else{return false;}"></a>
				 	</display:column>
				 </s:if>		
				<display:column title="Ver Detalle"  headerClass="sortable" >
					<a href='<s:url value="/seguimiento/capSeguimiento?idSeguimientoCA=%{#attr.r.idSeguimientoCA}&registrar=1"/>' class="botonVerDetalles" ></a>
			 	</display:column>
			 	<s:if test="%{#session.idPerfil==11}"> <!-- Regional -->
				 	<display:column title="Editar"  headerClass="sortable">
					 	<s:if test="%{#attr.r.idEstatus == 1 || #attr.r.idEstatus == 3}">
						 	<a href='<s:url value="/seguimiento/capSeguimiento?idSeguimientoCA=%{#attr.r.idSeguimientoCA}&registrar=2"/>' class="editar"></a>
						</s:if> 			 			 	
					</display:column>
					<display:column title="Eliminar"  headerClass="sortable">
						<s:if test="%{#attr.r.idEstatus == 1 || #attr.r.idEstatus == 3}">
							<a href='<s:url value="/seguimiento/eliminarSeguimiento?idSeguimientoCA=%{#attr.r.idSeguimientoCA}"/>' class="borrar" title="" onclick="if (confirm('¿Esta seguro de eliminar registro?')){}else{return false;}"></a>
						 </s:if>	 			 	
					</display:column>					 	
				 </s:if>
				 <s:if test="%{#session.idPerfil==12}"> <!-- DGPC -->
				 	<display:column title="Autorizar Cambios"  headerClass="sortable">
				 		<s:if test="%{#attr.r.idEstatus == 2}">
				 			<a href='<s:url value="/seguimiento/capSeguimiento?idSeguimientoCA=%{#attr.r.idSeguimientoCA}&registrar=3"/>' class="autorizar"></a>
				 		</s:if>
				 	</display:column>
				 </s:if>
			</display:table>
		</fieldset>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros capturados</div></s:else>
	</s:form>
<div id="vistaPrevia" class="vistaPrevia"></div>
<div id="resultadoGeneracionOficio"></div>			 