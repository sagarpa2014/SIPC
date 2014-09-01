<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom" style="text-align:center"><h1>Documentos</h1></div><br>
<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>	
<table  class="clean" style="width:100%">	
	<s:iterator value="lstsExpedientesSPCartaAdhesionV" var="resultado"  status="itStatus">	
			<tr>	
				<td>
					<label class=""><span class="requerido">*</span>
						<s:if test="%{capturarInfoFianza == true}">
							<s:if test="%{idExpediente == 4}">
								<a href="<s:url value="/solicitudPago/capEstadoCuenta?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Estado de Cuenta"><s:property value="%{expediente}"/></a>
							</s:if>
							<s:elseif test="%{idExpediente == 5}">
								<a href="<s:url value="/solicitudPago/capAnexo32D?folioCartaAdhesion=%{folioCartaAdhesion}&idExpSPCartaAdhesion=%{idExpSPCartaAdhesion}"/>" title="Anexo 32-D"><s:property value="%{expediente}"/></a>							
							</s:elseif>
							<s:elseif test="%{idExpediente == 33}">
								<a href="<s:url value="/solicitudPago/capAuditorSolPago?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=2"/>" title="Dictamen Contable del Auditor"><s:property value="%{expediente}"/></a>
							</s:elseif>
							<s:else>
								<s:property value="%{expediente}"/>
							</s:else>									
						</s:if>
						<s:else>
								<s:property value="%{expediente}"/>
						</s:else>
					</label>
				</td>
				<td>
					<s:if test="registrar==0">
						<s:if test="rutaDocumento!=null">
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
						</s:if>
						<s:else>
							<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
						</s:else>
					</s:if>
					<s:if test="registrar==2 || registrar == 3 || registrar == 4">
						<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
					</s:if>
				</td>
			</tr>
			<s:if test="%{idExpediente == 32}">
				<s:if test="%{idCriterioPago == 1 || idCriterioPago == 3}">
					<tr class="datosVolumen">
						<td>
							<label class=""><span class="requerido">*</span>Volumen Solicitado a Apoyar</label>
						</td>
						<td class="cVolumen">
							<s:if test="volumen!=null">
								<s:textfield id="v3" name="volumen" value="%{getText('volumenSinComas',{volumen})}" maxlength="14" size="20"  />
							</s:if>
							<s:else>
								<s:textfield id="v3" name="volumen" value="%{}" maxlength="14" size="20"/>
							</s:else>
							<script>
								$(document).ready(function() {		
									$(".cVolumen").change(function() {	
								        var v=$('#v3').val();
								        var patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
								    	if(v=="" || v==null){
								    		return false;
								    	}
								    	if(!v.match(patron)){
								    		$('#dialogo_1').html('El valor del registro es inválido, se aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
								       		abrirDialogo();
								       		return false;
								    	}
								    }); 
								});
							</script>
						</td>
					</tr>						
				</s:if>						
			</s:if>
			<s:if test="%{idExpediente == 5}">
				<tr class="datosAnexo5">						
					<td>
						<label class=""><span class="requerido">*</span>Fecha de Expedici&oacute;n Anexo 32-D</label>
					</td>
					<td>
						<s:if test="%{registrar==0}">
							<s:if test="%{fechaExpedicion==null}" >
								<s:textfield name="fechaExpedicion" maxlength="10" size="10" id="fechaExpedicion" readonly="true" cssClass="dateBox" />
							</s:if>
							<s:else>
								<s:textfield key="fechaExpedicion" value="%{fechaExpedicion}"  maxlength="10" size="10" id="fechaExpedicion" readonly="true" cssClass="dateBox" />
							</s:else>
							<img src="../images/calendar.gif" id="trgFechaExpedicion" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
							<script type="text/javascript">
								<!--
									Calendar.setup({
										inputField     :    "fechaExpedicion",     
										ifFormat       :    "%d/%m/%Y",     
										button         :    "trgFechaExpedicion",  
										align          :    "Br",           
										singleClick    :    true
									});											   
									//-->
							</script>										
						</s:if>
						<s:else>
							<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaExpedicionAnexo}"/></s:text></font>
						</s:else>
					</td>							
				</tr>						
			</s:if>									
	</s:iterator>	
</table>

