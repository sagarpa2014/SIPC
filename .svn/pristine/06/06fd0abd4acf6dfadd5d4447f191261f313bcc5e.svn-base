<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/archivoTesofe.js" />"></script>
<h1>Procesamiento de Archivos de Pagos TESOFE (RESPUESTA)</h1>
<s:if test="hasActionErrors()">
 		<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<s:form action="cargarArchivo" method="post" enctype="multipart/form-data" onsubmit="return chkFile();">
	<br>
	<fieldset>
		<legend>Seleccione el Archivo Respuesta de Pagos a cargar</legend>
		<s:file  name="fileUpload" id="fileUpload" />
		<s:submit value="Cargar" cssClass="boton2"/>		
	</fieldset>
</s:form>
<s:form action="ProcesarArchivoPagosRec" method="post" enctype="multipart/form-data" onsubmit="return chkFile1();">
	<br>
	<fieldset>
		<legend>Seleccione el Archivo Respuesta de Rechazos Pagos a cargar</legend>
		<s:file  name="fileUpload" id="fileUpload1" />
		<s:submit value="Cargar" cssClass="boton2"/>		
	</fieldset>
</s:form>
<s:if test="%{lstLog!=null}" >
	<s:if test="%{lstLog.size() > 0}" >
		<fieldset>
		<legend>Resultados:</legend>
			<div>
				 <s:iterator value="lstLog" status="renglon"> 
				 	<s:if test="%{ (#renglon.count)==1 }">
				 	<table class="simple" style="width:100%">
					 	<tr>
					 		<td><h1><s:property /></h1></td>
					 		<td>
						 	 	<ul class="log">
							 	 	<s:iterator value="logs">
										<li><a href="<s:url value="/pagos/viewLog"/>?l=<s:property value="file" />&hc=<s:property value="file.hashCode()" />" target="_blank"><s:property value="file" /></a> (<s:property value="size"/> )</li>		
									</s:iterator>
								</ul>
							</td>
					 	</tr>
				 	</table>	
				 	</s:if>
				 	<s:else>
						<s:property /> <br>
					</s:else>
				</s:iterator>
			</div>
		</fieldset>
	</s:if>
</s:if>

