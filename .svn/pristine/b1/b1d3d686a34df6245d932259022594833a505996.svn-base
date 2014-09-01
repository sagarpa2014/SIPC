<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js"/>"></script>
<div class="borderBottom"><h1>SOLICITUD PAGO</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<s:form action="registraSolicitudPago" method="post" onsubmit="return chkCamposSolicitudPago();" enctype="multipart/form-data" accept-charset="utf-8">
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="idExpSPCartaAdhesion" name="idExpSPCartaAdhesion" value="%{idExpSPCartaAdhesion}"/>
	<fieldset>
		<legend>Solicitud de Pago</legend>
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
		<div>
			<label class="left1"><span class="norequerido">*</span>Solicitud Pago:</label>
			<a href='<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{documento.rutaDocumento}"/>' title="">Descargar Archivo</a>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Sustituir Solicitud Pago:</label>
			<s:file name="doc" id="doc"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Volumen Actual:</label>
			<s:textfield id="volumenActual" name="volumenActual" value="%{getText('volumenSinComas',{documento.volumen})}" maxlength="14" size="20"  disabled="true" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Nuevo Volumen:</label>
			<s:textfield id="volumen" name="volumen" value="%{}" maxlength="14" size="20"/>
		</div>				
	</fieldset>
	<div class="clear"></div>
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2" />
		<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
	</div>		
	<div class="clear"></div>
	
</s:form>
<div class="clear"></div>
<s:if test="%{lstObservacionDocumentacionSP.size > 0}" >
	<fieldset>
	<legend>Solicitud Pago</legend>	
		<display:table id="row" name="lstObservacionDocumentacionSP"  list="lstObservacionDocumentacionSP"  pagesize="50" sort="list" requestURI=""  class="displaytag">
			<display:column title="No." >
  	     		<s:property value="%{#attr.row_rowNum}"/>
  			</display:column>
	 		<display:column title="Archivos Anteriores">
				<a href='<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#attr.row.rutaDocumento}"/>' title="">Descargar Archivo</a>
			</display:column>
		</display:table>			
	</fieldset>
</s:if>
<div class="clear"></div>
<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>



