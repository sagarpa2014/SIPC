<%@taglib uri="/struts-tags" prefix="s"%>
<fieldset>
	<legend>Datos del Representante Legal</legend>
	<div class="izquierda">
		<label class="left2">RFC</label>
		<s:textfield id="rfcRepresentanteLegal" name="rfcRepresentanteLegal"  onchange="recuperaDatosRepresentanteBodega();" maxlength="30" size="30" value="%{representanteLegal.rfc}"/>
	</div>
	<div class="clear"></div>
	<div id="recuperaDatosRepresentanteBodega">
		<s:include value="respuestaDatosRepresentate.jsp"/>
	</div>
	<div class="clear"></div>
</fieldset>
<fieldset>
	<legend>Croquis de Ubicaci&oacute;n</legend>
	<s:if test="%{registrar==0}">
		<div>
			 <label class="left1"><span class="norequerido">*</span>Cargar Archivo:</label>
			 <s:file name="doc" id="doc"/>
		</div>
	</s:if>
	<s:else>
		<s:if test="%{bodegasV.nombreArchivoCroquis!=null}">
			<div>
				<label class="left1"><span class="norequerido">*</span>Cargar Archivo:</label>
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{bodegasV.rutaCroquisBodega+bodegasV.nombreArchivoCroquis}"/>" title="Descargar Archivo">Descargar Archivo</a>
			</div>
		</s:if>
		<div class="clear"></div>
		<s:if test="%{registrar!=2}">
			<div>
				<label class="left1"><span class="norequerido">*</span>Sustituir Archivo:</label>
				<s:file name="doc" id="doc"/>
			</div>
		</s:if>
	</s:else>
</fieldset>