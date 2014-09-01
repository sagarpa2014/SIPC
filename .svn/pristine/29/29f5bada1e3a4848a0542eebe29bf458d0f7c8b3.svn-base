<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js"/>"></script>
<div class="borderBottom"><h1>ANEXO 32-D</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>
<s:form action="registraAnexo32D" method="post" onsubmit="return chkCamposAnexo32();" enctype="multipart/form-data" accept-charset="utf-8">
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="idExpSPCartaAdhesion" name="idExpSPCartaAdhesion" value="%{idExpSPCartaAdhesion}"/>
	<fieldset>
		<legend>Anexo 32-D</legend>
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
			<label class="left1"><span class="norequerido">*</span>Anexo 32-D:</label>
			<a href='<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{documento.rutaDocumento}"/>' title="">Descargar Archivo</a>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Sustituir Anexo 32-D:</label>
			<s:file name="doc" id="doc"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Fecha Expedici&oacute;n Actual:</label>
			<font class="arial12bold"><s:text name="fecha"><s:param value="%{documento.fechaExpedicionAnexo}"/></s:text></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Nueva Fecha Expedici&oacute;n:</label>
			<s:if test="%{fechaExpedicion==null}" >
				<s:textfield name="fechaExpedicion" maxlength="10" size="10" id="fechaExpedicion" readonly="true" cssClass="dateBox"/>
			</s:if>
			<s:else>
				<s:textfield key="fechaExpedicion" value="%{fechaExpedicion}"  maxlength="10" size="10" id="fechaExpedicion" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
					Calendar.setup({
						inputField     :    "fechaExpedicion",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trg1",  
						align          :    "Br",           
						singleClick    :    true
					});
				//-->
			</script>
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
	<legend>Anexo 32-D</legend>	
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



