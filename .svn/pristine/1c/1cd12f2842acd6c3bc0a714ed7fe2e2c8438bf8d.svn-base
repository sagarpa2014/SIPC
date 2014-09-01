<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="msjError!=null && msjError !=''">
	<div class="msjError"><s:property value="%{msjError}"/></div>	
</s:if>
<s:if test="datosCompradorCompleto==1">
	<div class="clear"></div>
	<s:hidden id="cartaAdhesionSistema" name="cartaAdhesionSistema" value="%{cartaAdhesionSistema}"/>
	<s:hidden id="acronimoCA" name="acronimoCA" value="%{acronimoCA}"/>
	<s:hidden id="fechaPublicacionDOFInt" name="fechaPublicacionDOFInt" value="%{fechaPublicacionDOFInt}"/>
	<div>
		<label class="left1"><span class="requerido">*</span>Ciclo:</label>
		<s:select id="idCicloPrograma" name="idCicloPrograma" list="lstCiclosProgramasV" listKey="idCicloPrograma" listValue="%{cicloCorto + ' - ' + ejercicio}"  headerKey="-1" headerValue="-- Seleccione una opción --" onclick="" onchange="" value="%{idCicloPrograma}"/>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Folio Inscripci&oacute;n:</label>
		<s:textfield id="folioSI" name="folioSI"  maxlength="20" size="20"  value="%{folioSI}" onchange="validarFolioInscripcion();" />
	</div>
	<div class="clear"></div>
	<div id="validarFolioInscripcion"></div>		
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Cultivo</label>
		<s:select id="idCultivo" name="idCultivo" list="lstCultivoByPrograma" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" onclick="" onchange="" value="%{idCultivo}"/>
	</div>
	<div class="clear"></div>
	<s:hidden id="idCriterioPago" name="idCriterioPago" value="%{idCriterioPago}"/>
	<s:if test="idCriterioPago==1 || idCriterioPago==3"> <!-- Volumen o Volumen/Etapa -->
		<div>
			<label class="left1"><span class="requerido">*</span>Volumen Solicitado:</label>
			<s:if test="volumenInscripcion!=null">
				<s:if test="registrar==1 || registrar==3 || registrar == 4 || registrar == 5">
					<s:textfield id="volumenInscripcion" name="volumenInscripcion"  maxlength="15" size="20" value="%{getText('volumenSinComas',{volumenInscripcion})}"/>
				</s:if>
				<s:else>
					<s:textfield id="volumenInscripcion" name="volumenInscripcion"  maxlength="15" size="20"  value="%{getText('volumen',{volumenInscripcion})}"/>
				</s:else>
			</s:if>
			<s:else>
				<s:textfield id="volumenInscripcion" name="volumenInscripcion"  maxlength="15" size="20"  value="%{}"/>
			</s:else>
		</div>
	</s:if>
	<s:else> <!-- Etapa -->
		<div>
			<label class="left1"><span class="requerido">*</span>Importe Solicitado:</label>
			<s:if test="importeInscripcion!=null">
				<s:if test="registrar==3 || registrar == 4 || registrar == 5">
					<s:textfield id="importeInscripcion" name="importeInscripcion"  maxlength="15" size="20"  value="%{importeInscripcion}"/>
				</s:if>
				<s:else>
					<s:textfield id="importeInscripcion" name="importeInscripcion"  maxlength="15" size="20"  value="%{getText('importe',{importeInscripcion})}"/>
				</s:else>
			</s:if>
			<s:else>
				<s:textfield id="importeInscripcion" name="importeInscripcion"  maxlength="15" size="20"  value="%{}"/>
			</s:else>
		</div>
	</s:else>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Archivo de Solicitud de Inscripci&oacute;n</label>
		<s:if test="registrar==0 || registrar==1 || registrar ==3  || registrar == 5">
			<s:file  name="docSI" id="docSI"/>
		</s:if>
		<s:if test="registrar==2 || registrar==3 || registrar == 4 || registrar == 5">
			<s:hidden id="docSIFileName" name="docSIFileName" value="%{docSIFileName}"/>
			<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docSIFileName}"/>" title="Archivo de Solicitud de Inscripci&oacute;n">Descargar Solicitud</a>
		</s:if>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Documento</label>
		<s:if test="%{fechaDocSI==null}">
			<s:textfield name="fechaDocSI" maxlength="10" size="10" id="fechaDocSI" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaDocSI" value="%{getText('fecha1',{fechaDocSI})}" name="fechaDocSI" maxlength="10" size="10" id="fechaDocSI" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Acuse</label>
		<s:if test="%{fechaAcuseSI==null}" >
			<s:textfield name="fechaAcuseSI" maxlength="10" size="10" id="fechaAcuseSI" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaAcuseSI" value="%{getText('fecha1',{fechaAcuseSI})}" name="fechaAcuseSI" maxlength="10" size="10" id="fechaAcuseSI" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Auditor</label>
		<s:select id="numeroAuditor" name="numeroAuditor" list="lstAuditoresExternos" listKey="numeroAuditor" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" onclick="" onchange="" value="%{numeroAuditor}"/>
	</div>
	<div class="clear"></div>
	<div class="inline">
		<label class="left1"><span class="requerido">*</span>Tipo de Reporte:</label>
		<s:radio label="" onclick="" name="tipoReporte" list="#{0:'CONSUMO',1:'VENTAS',2:'AMBOS'}" value="%{tipoReporte}"/>	
	</div>

	<s:if test="%{registrar == 4 || (registrar == 2 && lstaAlcanceSI.size > 0)}">
		<!-- ***************************ALCANCE A LA SOLICITUD DE INSCRIPCION registrar == 4**************** -->
		<div class="clear"></div>
		<div class="borderBottom" style="text-align:center"><h1>Alcance a la Solicitud de Inscripci&oacute;n</h1></div><br>
		<div class="clear"></div>
		<s:if test="%{lstaAlcanceSI.size > 0}">	
			<s:iterator value="lstaAlcanceSI" status="itStatus">
				<div>
					<label class="left1"><span class="norequerido">*</span>Alcance <s:property value="%{#itStatus.count}"/> </label>
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaArchivo+nomArchivoAlcance}"/>" title="Archivo del Alcance">
						<s:property value="%{noOficioAlcance}"/>
					</a>
				</div>
				<div class="clear"></div>
				<div>
					<label class="left1"><span class="norequerido">*</span>Fecha Documento</label>
					<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaDocAlcance}"/></s:text></font>
				</div>
				<div class="clear"></div>
				<div>
					<label class="left1"><span class="norequerido">*</span>Fecha Acuse</label>
					<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaAcuseAlcance}"/></s:text></font>
				</div>
				<div class="clear"></div>
			</s:iterator>
		</s:if>
		<div class="clear"></div>
		<s:if test="%{registrar == 4}">
			<div>
				<label class="left1"><span class="requerido">*</span>Oficio Alcance a la Solicitud de Inscripci&oacute;n</label>
				<s:if test="registrar==4">
					<s:file  name="docASI" id="docASI"/>
				</s:if>
				<s:else>
					<s:hidden id="docASIFileName" name="docASIFileName" value="%{docASIFileName}"/>
					<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docASIFileName}"/>" title="Oficio">Descargar Oficio</a>
				</s:else>
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>Fecha Documento</label>
				<s:if test="%{fechaDocASI==null}" >
					<s:textfield name="fechaDocASI" maxlength="10" size="10" id="fechaDocASI" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield key="fechaDocASI"  value="%{getText('fecha1',{fechaDocASI})}"  maxlength="10" size="10" id="fechaDocASI" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trgASI1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>Fecha Acuse</label>
				<s:if test="%{fechaAcuseASI==null}" >
					<s:textfield name="fechaAcuseASI" maxlength="10" size="10" id="fechaAcuseASI" readonly="true" cssClass="dateBox" />
				</s:if>
				<s:else>
					<s:textfield key="fechaAcuseASI" value="%{getText('fecha1',{fechaAcuseASI})}" maxlength="10" size="10" id="fechaAcuseASI" readonly="true" cssClass="dateBox" />
				</s:else>
				<img src="../images/calendar.gif" id="trgASI2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
				<s:textfield id="noOficioASI" name="noOficioASI" maxlength="30" size="30"  value="%{noOficioASI}"/>
			</div>
			<div class="clear"></div>
			<script type="text/javascript">
				<!--
					Calendar.setup({
						inputField     :    "fechaDocASI",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trgASI1",  
						align          :    "Br",           
						singleClick    :    true
					});
					Calendar.setup({
						inputField     :    "fechaAcuseASI",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trgASI2",  
						align          :    "Br",           
						singleClick    :    true
						});		
				//-->
			</script>
		</s:if><!-- End alcance registrar == 4 -->
		<s:if test="%{registrar == 2}">
			<s:if test="%{registrar == 2}">
				
			</s:if>
		</s:if>
	</s:if><!-- End alcance registrar == 2 || registrar == 4 -->
	<div class="clear"></div>
	<div class="borderBottom" style="text-align:center"><h1>Acreditaci&oacute;n Juridica</h1></div><br>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Oficio Petici&oacute;n Acreditaci&oacute;n</label>
		<s:if test="registrar==0 || registrar==1 || registrar == 3 || registrar == 5">
			<s:file  name="docOPA" id="docOPA"/>
		</s:if>
		<s:if test="registrar==2 || registrar==3 || registrar == 4 || registrar == 5">
			<s:hidden id="docOPAFileName" name="docOPAFileName" value="%{docOPAFileName}"/>
			<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{docOPAFileName}"/>" title="Oficio">Descargar Oficio</a>
		</s:if>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Documento</label>
		<s:if test="%{fechaDocOPA==null}" >
			<s:textfield name="fechaDocOPA" maxlength="10" size="10" id="fechaDocOPA" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaDocOPA"  value="%{getText('fecha1',{fechaDocOPA})}"  maxlength="10" size="10" id="fechaDocOPA" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg3" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha Acuse</label>
		<s:if test="%{fechaAcuseOPA==null}" >
			<s:textfield name="fechaAcuseOPA" maxlength="10" size="10" id="fechaAcuseOPA" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield key="fechaAcuseOPA" value="%{getText('fecha1',{fechaAcuseOPA})}" maxlength="10" size="10" id="fechaAcuseOPA" readonly="true" cssClass="dateBox" />
		</s:else>
		<img src="../images/calendar.gif" id="trg4" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
		<s:textfield id="noOficioPA" name="noOficioPA" maxlength="30" size="30"  value="%{noOficioPA}"/>
	</div>
	<div class="clear"></div>
	<div class="inline">
		<label class="left1"><span class="requerido">*</span>Acreditaci&oacute;n Juridica:</label>
		<s:radio label="" onclick="acreditar();" id="acreditacionJuridica" name="acreditacionJuridica" list="#{0:'POSITIVA',1:'NEGATIVA',2:'CON OBSERVACIONES'}" value="%{acreditacionJuridica}" />
	</div>
	<div class="clear"></div>
	<div id="acreditacion"><s:include value="/WEB-INF/paginas/inscripcion/solicitudInscripcion/acreditar.jsp"/></div>
	<div class="clear"></div>
	<div class="borderBottom" style="text-align:center"><h1>Acreditaci&oacute;n T&eacute;cnica</h1></div><br>
	<div class="clear"></div>
	<div class="inline">
		<label class="left1"><span class="requerido">*</span>Acreditaci&oacute;n Solicitud Inscripci&oacute;n:</label>
		<s:radio label=""  onclick="acreditarSI();" id="acreditacionSI" name="acreditacionSI" list="#{0:'POSITIVA',1:'NEGATIVA',2:'CON OBSERVACIONES'}" value="%{acreditacionSI}" />
	</div>
	<div class="clear"></div>
	<div id="acreditarSI"><s:include value="/WEB-INF/paginas/inscripcion/solicitudInscripcion/acreditarSI.jsp"/></div>
	<div class="clear"></div>
	<div class="inline">
		<label class="left1"><span class="requerido">*</span>Acreditaci&oacute;n de Dictamen de Consumo o Ventas:</label>
		<s:radio label="" onclick="acreditarDictaminacion();" id="acreditacionDictamen" name="acreditacionDictamen" list="#{0:'POSITIVA',1:'NEGATIVA',2:'CON OBSERVACIONES'}" value="%{acreditacionDictamen}" />
	</div>
	<div class="clear"></div>
	<div id="acreditarDictaminacion"><s:include value="/WEB-INF/paginas/inscripcion/solicitudInscripcion/acreditarDictaminacion.jsp"/></div>
	
	<s:if test="registrar==0 || registrar==1 || registrar == 3 || registrar == 4 || registrar == 5">	
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>" class="boton" title="" >Cancelar</a>
		</div>
	</s:if>
	<s:elseif test="registrar==2">
		<div class="accion">
			<a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>" class="boton" title="" >Regresar</a>
		</div>
	</s:elseif>	
	
	
	<s:if test="registrar==0 || registrar==1 || registrar == 3 || registrar == 5">
		<script type="text/javascript">
			<!--
				Calendar.setup({
					inputField     :    "fechaDocSI",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg1",  
					align          :    "Br",           
					singleClick    :    true
				});		
				Calendar.setup({
					inputField     :    "fechaAcuseSI",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg2",  
					align          :    "Br",           
					singleClick    :    true
				});
					
				Calendar.setup({
					inputField     :    "fechaDocOPA",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg3",  
					align          :    "Br",           
					singleClick    :    true
				});
				Calendar.setup({
					inputField     :    "fechaAcuseOPA",     
					ifFormat       :    "%d/%m/%Y",     
					button         :    "trg4",  
					align          :    "Br",           
					singleClick    :    true
					});
				
				//-->
		</script>
	</s:if>
	<s:if test="registrar == 4"> <!-- Alcance a la solicitud de inscripcion -->
		<script>
			$(document).ready(function(){
				var idCriterioPago = $('#idCriterioPago').val(); 
				if(idCriterioPago==1 || idCriterioPago==3){
					$('#volumenInscripcion').css({"border-color":"red"});	
				}else{
					$('#importeInscripcion').css({"border-color":"red"});
				}
				$('#docASI').css({"border-color":"red"});	
				$('#fechaDocASI').css({"border-color":"red"});	
				$('#fechaAcuseASI').css({"border-color":"red"});	
				$('#noOficioASI').css({"border-color":"red"});			
				$("input").removeAttr('disabled');
				$("select").removeAttr('disabled');			
				$("#idPrograma").attr('disabled','disabled');
				$("#idComprador").attr('disabled','disabled');
				$("#numeroAuditor").attr('disabled','disabled');
				$('input[name=tipoReporte]').attr('disabled','disabled');
				$("#folioSI").attr('disabled','disabled');
				$("#idCultivo").attr('disabled','disabled');
				$("#noOficioPA").attr('disabled','disabled');
				$('input[name=acreditacionJuridica]').attr('disabled','disabled');
				if ($('#noOficioA').length){
					$("#noOficioA").attr('disabled','disabled');
				}
				$('input[name=acreditacionSI]').attr('disabled','disabled');
				if ($('#obsSolInscripcion').length){
					$("#obsSolInscripcion").attr('disabled','disabled');
				}
				$('input[name=acreditacionDictamen]').attr('disabled','disabled');
				if ($('#obsDictaminacion').length){
					$("#obsDictaminacion").attr('disabled','disabled');
				}
				if($('#noOficioDic').length){
					$("#noOficioDic").attr('disabled','disabled');
				}				
				
				
			});	 
		</script>
	</s:if>
</s:if>
