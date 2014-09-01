<%@taglib uri="/struts-tags" prefix="s"%>
<div class="borderBottom" style="text-align:center"><h1>Cargar Oficio de Observaciones</h1></div><br>
<div class="clear"></div>
<div id="">
	<label class="left1"><span class="norequerido">*</span>Documentaci&oacute;n Sin Observaciones:</label>
	<s:checkbox id="doctosSinObservacion"  name="doctosSinObservacion" onclick ="recuperaDocRequeridos();" fieldValue="true" value="%{cargarOficioObservacion}"/>
</div>
<div id="recuperaDocRequeridos"></div>
<div class="clear"></div>
<div id="">
	<label class="left1"><span class="norequerido">*</span>Habilita Oficio de Observaciones:</label>
	<s:checkbox id="habilitarOficioObs"  name="habilitarOficioObs" onclick ="habilitarOficioObservacion();" fieldValue="true" value="%{cargarOficioObservacion}"/>
</div>
<div class="clear"></div>		



	


