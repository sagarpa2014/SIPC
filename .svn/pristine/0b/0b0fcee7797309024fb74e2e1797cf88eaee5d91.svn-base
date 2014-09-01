<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/archivoTesofe.js" />"></script>
<div class="borderBottom"><h1>CARGA DE ARCHIVOS TESOFE</h1></div>
<div class="clear"></div>
<div id="dialogo_1"></div>
<s:form action="cargarArchivoTesofe" method="post" enctype="multipart/form-data" onsubmit="return chkFileEscaneados();">
	<br>
	<fieldset class="clear">
		<legend>Oficios</legend>
		<div>
			<s:hidden id="noOficio" name="noOficio" value="%{noOficio}"/>
			<label class="left1"><span class="norequerido">*</span>No Oficio:</label>
			<font class="arial12bold"><s:property value="%{oficioPagos.noOficio}"/></font>
		</div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Folio CLC:</label>
			<font class="arial12bold"><s:property value="%{oficioPagos.folioCLC}"/></font>
		</div>
		
		<div>
			<label class="left1"><span class="requerido">*</span>CGC a DGAF:</label>
			<s:file  name="cgcDgaf" id="cgcDgaf"/>
		</div>
		<div>
			<label class="left1"><span class="requerido">*</span>DGAF a CGC:</label>
			<s:file  name="dgafCgc" id="dgafCgc"/>
		</div>
		
		
		<div class="accion">
			<s:submit value="Cargar" cssClass="boton2"/>
			<a href="<s:url value="pagos/consultaOficiosEnvioTesofe"/>" class="boton" title="" >Cancelar</a>
		</div>		
	</fieldset>
</s:form>
