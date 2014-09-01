<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="%{complementoPorampliacionChk==true || lstDetAsigCAC.size()>0}">
	<div class="borderBottom" style="text-align:center"><h1>Complemento Carta Adhesi&oacute;n</h1></div><br>
	<div>
		<label class="left1"><span class="requerido">*</span>Archivo Ampliaci&oacute;n Carta Adhesi&oacute;n:</label>
		<s:if test="docAmpliacionCAFileName !='' && docAmpliacionCAFileName!=null">
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docAmpliacionCAFileName}"/>" title="Carta Adhesi&oacute;n">Complemento Carta Adhesi&oacute;n</a>
			</s:if>
			<s:else><s:file  name="docAmpliacionCA" id="docAmpliacionCA"/></s:else>	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Firma del Complemento de la Carta Adhesi&oacute;n:</label>
		<s:if test="%{fechaFirmaCAComplemento==null}">
			<s:textfield name="fechaFirmaCAComplemento" maxlength="10" size="10" id="fechaFirmaCAComplemento" readonly="true" cssClass="dateBox"/>
			<img src="../images/calendar.gif" id="trgfechaFirmaCAComp" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--	
					Calendar.setup({
						inputField     :    "fechaFirmaCAComplemento",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trgfechaFirmaCAComp",  
						align          :    "Br",           
						singleClick    :    true
					});
				//-->
			</script>			
		</s:if>
		<s:else>
			<s:textfield key="fechaFirmaCAComplemento" value="%{getText('fecha1',{fechaFirmaCAComplemento})}" maxlength="10" size="10" id="fechaFirmaCAComplemento" readonly="true" cssClass="dateBox" />		
		</s:else>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Cultivo, Variedad, Estados a Apoyar:</label>
		<s:if test="%{lstDetAsigCAC.size()>0}">
			<s:textfield id="numCamposComp" name="numCamposComp"  maxlength="3" size="5"  value="%{numCamposComp}" disabled="true"/>
		</s:if>
		<s:else>
			<s:textfield id="numCamposComp" name="numCamposComp"  maxlength="3" size="5"  value="%{numCamposComp}" onkeyup="agregaCultivoVariedadEstadoCAComp();"/>
		</s:else>
	</div>
	<div class="clear"></div>
	<div id="agregaCultivoVariedadEstadoCAComp"></div>
	
	<s:if test="%{lstDetAsigCAC.size()>0}">
		<s:include value="cultivoEstadoConComp.jsp"/>
	</s:if>
	<s:if test="%{lstDetAsigCAC.size()==0}">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2"/>
		</div>	
	</s:if>
</s:if>
	
