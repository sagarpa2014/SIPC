<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>SOLICITUD DE INSCRIPCI&Oacute;N</h1></div><br>  
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<s:form action="ejecutaBusquedaSolicitudIns" onsubmit="return chkCamposBusquedaSI();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
			<label class="left1">Folio Solicitud Inscripci&oacute;n:</label>
			<s:textfield id="folioSI" name="folioSI" maxlength="30" size="30"/>
		</div>
		<div>
			<label class="left1">Carta Adhesi&oacute;n:</label>
			<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" maxlength="30" size="30"/>
		</div>
		<div>	
			<label class="left1"><span class="requerido">*</span>Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramasV" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="" onchange="" value="%{}"/>
		</div>
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Solicitud" cssClass="boton2" />
		</div>
	</fieldset>		
</s:form>
<br>

<s:if test='#session.idPerfil==5'>
	<div class="derecha"><a href="<s:url value="/inscripcion/capturarInscripcion"/>" onclick="" title="Registrar Solicitud" >[Registrar Solicitud]</a></div>
</s:if>
<s:if test="%{bandera==true}">
		<s:if test="lstSolInsV.size() > 0">
			<div class="clear"></div>
			<br/>
			<fieldset>
				<legend>Resultado de B&uacute;squeda</legend>
				<display:table id="r"  name="lstSolInsV"  list="lstSolInsV"  pagesize="50" sort="list" requestURI="/inscripcion/ejecutaBusquedaSolicitudIns"  class="displaytag">
					<display:column  property="folioSI" title="Folio SI"/>
					<display:column  property="programa" title="Programa"/>
					<display:column  property="comprador" title="Comprador"/>
					<display:column  property="desEstatusSI" title="Estatus"/>
					<display:column title="Ciclo">
						<s:property value="%{#attr.r.cicloCorto}"/><s:property value="%{#attr.r.ejercicio}"/>	
			 		</display:column>
			 		<display:column title="Carta Adhesi&oacute;n"  headerClass="sortable" >
						<s:if test="%{#attr.r.folioCartaAdhesion != null}">
							<s:property value="%{#attr.r.folioCartaAdhesion}"/>
						</s:if>
					</display:column>
			 		<display:column title="Volumen Solicitud" class="d">
			 			<s:text name="volumen"><s:param value="%{#attr.r.volumenInscripcion}"/></s:text>
			 		</display:column>
			 		<display:column title="Volumen Asignado a Carta Adhesi&oacute;n" class="d">
			 			<s:if test="%{#attr.r.volumenTotalCarta != 0}" >
			 				<s:text name="volumen"><s:param value="%{#attr.r.volumenTotalCarta}"/></s:text>
			 			</s:if>
			 		</display:column>			 		
			 		<display:column title="Importe Solicitud" class="d">
			 			<s:if test="%{#attr.r.importeInscripcion != 0}">
			 				<s:text name="importe"><s:param value="%{#attr.r.importeInscripcion}"/></s:text>
			 			</s:if>
			 		</display:column>
			 		<display:column title="Importe Asignado a Carta Adhesi&oacute;n" class="d">
			 			<s:if test="%{#attr.r.importeTotalCarta != 0}">
			 				<s:text name="volumen"><s:param value="%{#attr.r.importeTotalCarta}"/></s:text>
			 			</s:if>			 			
			 		</display:column>
			 		<display:column title="Ver Detalle"  headerClass="sortable" >
						<a href='<s:url value="/inscripcion/getDetalleSolIns?registrar=2&idSI=%{#attr.r.idSI}"/>' class="botonVerDetalles" title=""></a>
					</display:column>
					<s:if test="%{#session.idPerfil==5}">					
						<display:column title="Acción"  headerClass="sortable" >
							<s:if test="%{#attr.r.estatus == 3}">
								<a href='<s:url value="/inscripcion/getDetalleSolIns?registrar=3&idSI=%{#attr.r.idSI}"/>' class="" title="">Acreditar</a>
							</s:if>
							<s:elseif test="%{#attr.r.estatus == 1 && #attr.r.folioCartaAdhesion != null}">
								<a href='<s:url value="/inscripcion/recuperaCuotasInicEsquema?idSI=%{#attr.r.idSI}&idPrograma=%{#attr.r.idPrograma}"/>' class="" title="">Asignaci&oacute;n</a>
							</s:elseif>
							<s:elseif test="%{#attr.r.estatus == 1 && #attr.r.folioCartaAdhesion == null}">
								<a href='<s:url value="/inscripcion/generarFolioCartaAdhesion?idSI=%{#attr.r.idSI}"/>' class="" title="">Generar Folio</a>
							</s:elseif>							
						</display:column>
					</s:if>
				</display:table>
			</fieldset>
		</s:if>
		<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
</s:if>
