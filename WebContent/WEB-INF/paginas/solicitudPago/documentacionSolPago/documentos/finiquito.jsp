<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js"/>"></script>
<s:form action="registraFiniquito" name="forma" method="post" enctype="multipart/form-data" accept-charset="utf-8"  onsubmit="return chkCamposFiniquito();">	
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>	
	<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
	<s:if test="%{tipoVistaFiniquito == 1}">	
		<div class="borderBottom"><h1>FINIQUITO</h1></div><br>
	</s:if>
	<s:else>
		<div class="borderBottom" style="text-align:center"><h1>Finiquito</h1></div><br>
	</s:else>
	<s:if test="%{tipoVistaFiniquito == 1}">	
		
		<fieldset>
			<legend>Carta Adhesi&oacute;n: <font class="arial14bold"><s:property value="%{folioCartaAdhesion}"/></font></legend>	</s:if>
		<table class="clean">
			<tr>
				<s:if test="%{rutaFiniquito!=null && rutaFiniquito!=''}">
					<td><label class="left1">Documento Finiquito</label></td>
					<td>
						<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaFiniquito}"/>" title="Descargar Archivo">Descargar Archivo</a>
					</td>
				</s:if>
				<s:else>			
					<td><label class="left1">Documento Finiquito</label></td>
					<td>
						<s:file name="docFin" id="docFin"/>
					</td>			
				</s:else>
			</tr>		
			<tr>
				<td><label class="left1">Fecha Documento</label></td>
				<td>
					<s:if test="%{fechaDocFin==null}" >
						<s:textfield name="fechaDocFin" maxlength="10" size="10" id="fechaDocFin" readonly="true" cssClass="dateBox" />
						<img src="../images/calendar.gif" id="trg90" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
							<!--
								Calendar.setup({
									inputField     :    "fechaDocFin",     
									ifFormat       :    "%d/%m/%Y",     
									button         :    "trg90",  
									align          :    "Br",           
									singleClick    :    true
								});											   
								//-->
						</script>
					</s:if>
					<s:else>
						<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaDocFin}"/></s:text></font>
					</s:else>
				</td>
			</tr>
			<tr>
				<td>
					<label class="left1">Fecha Acuse</label>	
				</td>
				<td>
					<s:if test="%{fechaAcuseFin==null}" >
						<s:textfield name="fechaAcuseFin" maxlength="10" size="10" id="fechaAcuseFin" readonly="true" cssClass="dateBox" />
						<img src="../images/calendar.gif" id="trg91" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
						<script type="text/javascript">
							<!--
								Calendar.setup({
								inputField     :    "fechaAcuseFin",     
								ifFormat       :    "%d/%m/%Y",     
								button         :    "trg91",  
								align          :    "Br",           
								singleClick    :    true
							});											   
								//-->
						</script>
					</s:if>
					<s:else>
						<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaAcuseFin}"/></s:text></font>
					</s:else>		
				</td>
			</tr>		
		</table>
		<s:if test="%{rutaFiniquito==null || rutaFiniquito==''}">
			<div class="accion">
				<s:submit  value="Guardar" cssClass="boton2"/>
				<a href="<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{idPrograma}"/>" class="boton" title="">Cancelar</a>
			</div>
		</s:if>
	<s:if test="%{tipoVistaFiniquito == 1}">
		</fieldset>
		<div class="izquierda"><a href="<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{idPrograma}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
	</s:if>
</s:form>