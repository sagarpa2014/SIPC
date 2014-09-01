<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>ASIGNACI&Oacute;N DE CARTA DE ADHESI&Oacute;N</h1></div><br>
<div id="dialogo_1"></div>
<div class="clear"></div>
<s:form action="ejecutaBusquedaAsignacionCAaEspecialista"  onsubmit="return chkCamposAsigCAaEspecialista">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">No. Oficio Carta Adhesi&oacute;n:</label>
			<s:textfield id="noOficioCA" name="noOficioCA" maxlength="30" size="30"/>
		</div>
		<div>
			<label class="left1">Carta Adhesi&oacute;n:</label>
			<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" maxlength="30" size="30"/>
		</div>
		<div>	
			<label class="left1">Especialista:</label>
			<s:select id="idEspecialista" name="idEspecialista" list="lstEspecialista" listKey="idEspecialista" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="" onchange="" value="%{}"/>
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n para realizar la consulta</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>	
</s:form>
<br>
<div class="derecha"><a href="<s:url value="/solicitudPago/capAsignacionSolPago"/>" onclick="" title="Registrar Solicitud" >[Registrar Asignaci&oacute;n Carta adehsi&oacute;n]</a></div>
<div class="clear"></div>
<s:if test="%{bandera==true}">
	<s:if test="lstAsignacionCAaEspecialistaV.size() > 0">
		<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<display:table id="r"  name="lstAsignacionCAaEspecialistaV"  list="lstAsignacionCAaEspecialistaV"  pagesize="50" sort="list" requestURI="/solicitudPago/ejecutaBusquedaAsignacionCAaEspecialista"  class="displaytag">
					<display:column  property="noOficioCA" title="Oficio Carta Adehsi&oacute;n"/>
					<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n"/>
					<display:column  property="programa" title="Programa"/>
					<display:column  property="especialista" title="Especialista"/>
					<display:column  property="estatusCartaAdhesion" title="Estatus"/>
			 		<display:column title="Ver Detalle"  headerClass="sortable" >
						<a href='<s:url value="/solicitudPago/getDetalleAsigCAaEspecialista?registrar=2&idOficioCASP=%{#attr.r.idOficioCASP}"/>' class="botonVerDetalles" title=""></a>
					</display:column>
					<display:column title="Reasignar"  headerClass="sortable" >
						<s:if test="%{#attr.r.estatusCA == 3}">
							<a href='<s:url value="/solicitudPago/getDetalleAsigCAaEspecialista?registrar=3&idOficioCASP=%{#attr.r.idOficioCASP}"/>' title="">Reasignar</a>
						</s:if>					
					</display:column>
					
				</display:table>
			</fieldset>		
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
</s:if>

