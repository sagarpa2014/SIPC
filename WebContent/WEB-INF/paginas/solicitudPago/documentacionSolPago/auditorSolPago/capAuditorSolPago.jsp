<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>

<div class="borderBottom">
	<h1>
		ASIGNACI&Oacute;N DE AUDITOR <s:if test="tipoDocumentacion==1">DOCUMENTACI&Oacute;N</s:if><s:else>FIANZA</s:else>
	</h1>
</div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<s:if test="errorNumeroAuditor!=null && errorNumeroAuditor !=''">
	<div class="errorNumeroAuditor"><s:property value="%{errorNumeroAuditor}"/></div>
</s:if>
<div id="dialogo_1"></div>
<s:form action="registrarAuditorSolPago" method="post" onsubmit="return chkCamposregistraAuditorSolPago();">
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="tipoDocumentacion" name="tipoDocumentacion" value="%{tipoDocumentacion}"/>
	<s:hidden id="numCamposAVAnterior" name="numCamposAVAnterior" value="%{numCamposAVAnterior}"/>
	<fieldset>
		<legend>Auditor</legend>
		<div>
			<label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label>
			<font class="arial14boldVerde"><s:property value="%{folioCartaAdhesion}"/></font>
		</div>
		<div class="clear"></div>	
		<div>
			<label class="left1"><span class="norequerido">*</span>Participante:</label>
			<font class="arial12bold"><s:property value="%{comprador.nombre}"/></font>
		</div>
		<div class="clear"></div>
	</fieldset>
<%-- 	<s:if test='%{estatus!=9}'> --%>
		<div class="derecha"><a href="<s:url value="/solicitudPago/agregarAuditor?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=%{tipoDocumentacion}&registrar=0"/>" onclick="" title="Agregar Auditor">[Agregar Auditor]</a></div><br>
<%-- 	</s:if>	 --%>
	<s:if test="lstAuditorSolPagoV.size() > 0">
		<fieldset>			
			<display:table id="r"  name="lstAuditorSolPagoV"  list="lstAuditorSolPagoV"  pagesize="50" sort="list" requestURI="//ejecutaBusquedaBodegas"  class="displaytag">
				<display:column  property="numeroAuditor" title="Numero Auditor"/>
				<display:column  property="nombre" title="Nombre"/>
				<display:column title="Fecha Inicio"  headerClass="sortable">		
					<s:if test="%{#attr.r.fechaInicio!=null}">
						<s:text name="fecha"><s:param value="%{#attr.r.fechaInicio}"/></s:text>
					</s:if>
				</display:column>
				<display:column title="Fecha Fin"  headerClass="sortable">
					<s:if test="%{#attr.r.fechaFin!=null}">
						<s:text name="fecha"><s:param value="%{#attr.r.fechaFin}"/></s:text>
					</s:if>	
				</display:column>	
				<display:column title="Volumen" class="d">
					<s:if test="%{#attr.r.volumenAuditor!=null}" >
						<s:text name="volumen"><s:param value="%{#attr.r.volumenAuditor}"/></s:text>
					</s:if>
				</display:column>	
				<display:column title="Editar"  headerClass="sortable">
					<a href='<s:url value="/solicitudPago/agregarAuditor?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=%{tipoDocumentacion}&registrar=1&idAuditorSolPago=%{#attr.r.idAuditorSolPago}"/>' class="editar" title="" ></a>
				</display:column>		
				<display:column title="Ver Detalle"  headerClass="sortable">
					<a href='<s:url value="/solicitudPago/agregarAuditor?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=%{tipoDocumentacion}&registrar=2&idAuditorSolPago=%{#attr.r.idAuditorSolPago}"/>' class="botonVerDetalles" title="" ></a>
			 	</display:column>			 			
			</display:table>		
		</fieldset>		
	</s:if>		
	<div class="clear"></div>
	<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>