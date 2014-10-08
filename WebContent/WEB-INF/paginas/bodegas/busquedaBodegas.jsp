<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/archivoTesofe.js" />"></script>
<s:if test="hasActionErrors()">
   <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<div class="borderBottom"><h1>CONSULTA DE BODEGAS</h1></div><br>
<div id="dialogo_1"></div>
<s:form action="ejecutaBusquedaBodegas" onsubmit="return chkCamposBusquedaBodega();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Clave Bodega:</label>
			<s:textfield id="claveBodega" name="claveBodega"  maxlength="15" size="15"  value="%{claveBodega}"/>
		</div>
		<div>
			<label class="left1">Nombre Bodega:</label>
			<s:textfield id="nombreBodega" name="nombreBodega" maxlength="30" size="50"/>
		</div>
		<div>
			<label class="left1">Estado:</label>
			<s:select id="idEstado" name="idEstado" list="lstEstado" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEstado}" onchange=""/>
		</div>		
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Bodega" cssClass="boton2" />
		</div>
	</fieldset>
	<s:if test="%{bandera==true}">
		<s:if test="lstBodegasV.size() > 0">
			<div class="clear"></div>
			<br/>
			<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<div id="tablaResultados">
					<display:table id="r"  name="lstBodegasV"  list="lstBodegasV"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaBodegas"  class="displaytag">
						<display:column  property="claveBodega" title="Clave Bodega"/>
						<display:column  property="nombre" title="Nombre Bodega"/>						
						<display:column  property="direccion" title="Direcci&oacute;n"/>
						<display:column title="Ver Detalle"  headerClass="sortable">
							<a href='<s:url value="/catalogos/detalleBodegas?claveBodega=%{#attr.r.claveBodega}&registrar=2"/>' class="botonVerDetalles" title="" ></a>
			 			</display:column>			 			
					</display:table>
				</div>
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
	</s:if>
</s:form>
