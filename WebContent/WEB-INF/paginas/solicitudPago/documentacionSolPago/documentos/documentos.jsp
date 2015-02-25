<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
<s:hidden id="doctosObservados" name="doctosObservados" value="%{doctosObservados}"/>
<s:hidden id="idExpedientesTotal" name="idExpedientesTotal" value="%{idExpedientesTotal}"/>
<s:hidden id="idExpedientesObservados" name="idExpedientesObservados" value="%{idExpedientesObservados}"/>
<s:hidden id="certDepositoOAlmacenamiento" name="certDepositoOAlmacenamiento" value="%{certDepositoOAlmacenamiento}"/>
<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
<s:set  name="archivoRelacionCompras1"><s:property value="%{archivoRelacionCompras}" /></s:set>
<s:hidden id="archivoRelacionCom" name="" value="%{archivoRelacionCompras}"/>
<s:set name="reporteCruce1"><s:property value="%{reporteCruce}" /></s:set>
<div class="clear"></div>
<div class="borderBottom" style="text-align:center"><h1>Entrega de Documentos</h1></div><br>	
	<table  class="clean" width="100%">	
		<s:set name="bandTipoC">0</s:set>
		<s:set name="tipoConstancia"><s:property value="tipoConstancia"/></s:set>
		<s:iterator value="lstsExpedientesSPCartaAdhesionV" var="resultado"  status="itStatus">
			<s:if test="%{idExpediente==1}"><!-- ******************************************* ESCRITO DE ENTREGA DE DOCUMENTOS ******************************************* -->
					<tr>
						<td>
							<label class=""><span class="requerido">*</span><s:property value="%{expediente}"/></label>
						</td>
						<td>
							<s:if test="(estatusCA == 3)">
								<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
								<s:hidden id="docRequerido%{idExpediente}" name="docRequerido" value="%{}"/>								
				 			</s:if>
				 			<s:else>
								<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
							</s:else>							
						</td>
					</tr>
					<tr>
						<td>
							<label class=""><span class="requerido">*</span>Fecha Documento</label>
						</td>
						<td>							
							<s:if test="%{fechaDocumento==null}" >
								<s:textfield name="fechaDocEntDoctos" maxlength="10" size="10" id="fechaDocEntDoctos" readonly="true" cssClass="dateBox"/>
								<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
								<script type="text/javascript">
									<!--
										Calendar.setup({
											inputField     :    "fechaDocEntDoctos",     
											ifFormat       :    "%d/%m/%Y",     
											button         :    "trg1",  
											align          :    "Br",           
											singleClick    :    true
										});	
									//-->
								</script>		
							</s:if>
							<s:else>
								<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaDocumento}"/></s:text></font>
							</s:else>								
						</td>
					</tr>
					<tr>
						<td><label class=""><span class="requerido">*</span>Fecha Acuse</label></td>
						<td>								
							<s:if test="%{fechaAcuse==null}" >
								<s:textfield name="fechaAcuseEntDoctos" maxlength="10" size="10" id="fechaAcuseEntDoctos" readonly="true" cssClass="dateBox" />
								<img src="../images/calendar.gif" id="trg2" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
								<script type="text/javascript">
									<!--
									Calendar.setup({
										inputField     :    "fechaAcuseEntDoctos",     
										ifFormat       :    "%d/%m/%Y",     
										button         :    "trg2",  
										align          :    "Br",           
										singleClick    :    true
									});									   
									//-->
								</script>
							</s:if>
							<s:else>
								<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaAcuse}"/></s:text></font>
							</s:else>						
						</td>
					</tr>
				</s:if><!-- ******************************************* TERMINA ESCRITO DE ENTREGA DE DOCUMENTOS ******************************************* -->
				<s:if test="%{idExpediente!=1 && idExpediente!=2}">
					<!-- ******************************************* DIVISORES DE BLOQUES ******************************************* -->
					<s:if test="%{idExpediente==3}">
						<tr>
							<td colspan="6">
								<div class="borderBottom" style="text-align:center"><h1>Solicitud de Pago del Apoyo</h1></div><br>							
							<td>
						</tr>
					</s:if>
					<s:if test="%{idExpediente==7}">
						<tr>
							<td colspan="6">
								<div class="borderBottom" style="text-align:center"><h1>Dictamen Contable de Auditor Externo</h1></div><br>							
							<td>
						</tr>
					</s:if>
					<s:if test="%{idExpediente==8}">
						<tr>
							<td colspan="6">
								<div class="borderBottom" style="text-align:center"><h1>Relaciones</h1></div><br>							
							<td>
						</tr>
					</s:if>
					<!-- ******************************************* TERMINA DIVISORES DE BLOQUES ******************************************* -->
					<s:if test="%{(idExpediente==10 || idExpediente==34) && #bandTipoC!=1}">
						<s:set name="bandTipoC">1</s:set>
						<s:if test="%{#tipoConstancia!=0}">
							<script>
								$(document).ready(function() {										
									$("#tipoConstanciaDiv input[type='radio']").attr('disabled','disabled');
								});	 	
							</script>
						</s:if>
						<tr>
							<td colspan="6">							
								<div class="inline" id="tipoConstanciaDiv">
									<label class="left1"><span class="norequerido" id="spTC">*</span>Tipo de Constancia:</label>
									<s:if test="certDepositoOAlmacenamiento==1">
										<s:radio label="" id="tipoConstanciac" name="tipoConstancia" list="#{1:'CERTIFICADOS DE DEP&Oacute;SITO'}" value="%{#tipoConstancia}" onclick="validarTipoConstancia(this.value);"/>									
									</s:if>
									<s:elseif test="certDepositoOAlmacenamiento==2">
										<s:radio label="" id="tipoConstanciac" name="tipoConstancia" list="#{2:'CONSTANCIA DE ALMACENAMIENTO'}" value="%{#tipoConstancia}" onclick="validarTipoConstancia(this.value);"/>
									</s:elseif>
									<s:else>
										<s:radio label="" id="tipoConstanciac" name="tipoConstancia" list="#{1:'CERTIFICADOS DE DEP&Oacute;SITO',2:'CONSTANCIA DE ALMACENAMIENTO', 3:'AMBOS'}" value="%{#tipoConstancia}" onclick="validarTipoConstancia(this.value);"/>
									</s:else>
									
								</div>							
							<td>
						</tr>
					</s:if>				
					<tr> <!-- ******************************************* ETIQUETAS DE LOS DOCUMENTOS ******************************************* -->		
						<td>
							<label class="">
								<s:if test="estatusCA == 3">
									<s:if test="%{idExpediente == 1}">
										<span class="requerido" id="sp<s:property value="%{idExpediente}"/>">*</span>
									</s:if>
									<s:else><span class="norequerido" id="sp<s:property value="%{idExpediente}"/>">*</span></s:else>
								 </s:if>
								 <s:else>
								 	<span class="norequerido" id="sp<s:property value="%{idExpediente}"/>">*</span>
								 </s:else>
								<s:if test="estatusCA == 3">													
									<s:if test="%{idExpediente == 5}">
										<s:if test="%{anexo32DyaCapturado == true && #session.idPerfil!=4 }">
											<a href="<s:url value="/solicitudPago/capAnexo32D?folioCartaAdhesion=%{folioCartaAdhesion}&idExpSPCartaAdhesion=%{idExpSPCartaAdhesion}"/>" title="Anexo 32-D"><s:property value="%{expediente}"/></a>
										</s:if>
										<s:else>
											<s:property value="%{expediente}"/>
										</s:else>										
									</s:if>
									<s:elseif test="%{idExpediente == 4}">
										<s:if test="%{edoCuentaYaCapturado == true && #session.idPerfil!=4}">
											<a href="<s:url value="/solicitudPago/capEstadoCuenta?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Estado de Cuenta"><s:property value="%{expediente}"/></a>
										</s:if>
										<s:else>
											<s:property value="%{expediente}"/>
										</s:else>										
									</s:elseif>
									<s:else>
										<s:property value="%{expediente}"/>
									</s:else>
								</s:if>
								<s:else>
							
									<s:if test="%{idExpediente == 3 && #session.idPerfil!=4}">									
										<a href="<s:url value="/solicitudPago/capSolicitudPago?folioCartaAdhesion=%{folioCartaAdhesion}&idExpSPCartaAdhesion=%{idExpSPCartaAdhesion}"/>" title="Solicitud Pago"><s:property value="%{expediente}"/></a>	
									</s:if>
									<s:elseif test="%{idExpediente == 4 && #session.idPerfil!=4}">
										<a href="<s:url value="/solicitudPago/capEstadoCuenta?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Estado de Cuenta"><s:property value="%{expediente}"/></a>
									</s:elseif>
									<s:elseif test="%{idExpediente == 5 && #session.idPerfil!=4}">
										<a href="<s:url value="/solicitudPago/capAnexo32D?folioCartaAdhesion=%{folioCartaAdhesion}&idExpSPCartaAdhesion=%{idExpSPCartaAdhesion}"/>" title="Anexo 32-D"><s:property value="%{expediente}"/></a>
									</s:elseif>
									<s:elseif test="%{idExpediente == 7 && #session.idPerfil!=4}">
										<a href="<s:url value="/solicitudPago/capAuditorSolPago?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=1"/>" title="Dictamen Contable del Auditor"><s:property value="%{expediente}"/></a>
									</s:elseif>
									<s:elseif test="%{idExpediente == 8}">
										<a href="<s:url value="/relaciones/capturaCargaArchivoRelCompras?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=1&tipoAccion=-1"/>" title="Relación de Compras"><s:property value="%{expediente}"/></a>
									</s:elseif>
									<s:elseif test="%{idExpediente == 10}">
										<s:if test="%{#session.idPerfil==4}">
											<a href="<s:url value="/solicitudPago/lstCertificadoDeposito?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Relación de Certificados"><s:property value="%{expediente}"/></a>
										</s:if>
										<s:else>
											<a href="<s:url value="/solicitudPago/capCertificadoDeposito?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Relación de Certificados"><s:property value="%{expediente}"/></a>
										</s:else>
										
									</s:elseif>
									<s:elseif test="%{idExpediente == 34}">
										<s:if test="%{#session.idPerfil==4}">
											<a href="<s:url value="/solicitudPago/lstConstanciasAlmacenamiento?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Constancia de Almacenamiento"><s:property value="%{expediente}"/></a>
										</s:if>
										<s:else>
											<a href="<s:url value="/solicitudPago/capConstanciasAlmacenamiento?folioCartaAdhesion=%{folioCartaAdhesion}"/>" title="Constancia de Almacenamiento"><s:property value="%{expediente}"/></a>
										</s:else>
										
									</s:elseif>
									<s:else>
										<s:property value="%{expediente}"/>
									</s:else>
								</s:else>								
							</label>
						</td><!-- *******************************************TERMINA ETIQUETAS DE LOS DOCUMENTOS ******************************************* -->						
						<!-- ******************************************* DOCUMENTOS ******************************************* -->
						<s:if  test="sustituirArchivo==true">							
							<td>
								<s:if test="%{rutaDocumento!=null && rutaDocumento !=''}">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
								</s:if>
							</td>																					
							<td>									
								<label class=""><span class="norequerido">*</span>Cargar Documento</label>
							</td>
							<td>
								<s:if test="%{idPrograma < 41}">
									<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
								</s:if>
								<s:else>
									<s:if test="%{idExpediente!=8 && idExpediente!=9}"><!-- Diferente de la relacion de compras y reporte de cruce -->
										<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
									</s:if>
								</s:else>
							</td>
							<td>&nbsp;</td> 													
						</s:if>	
						<s:elseif  test="alcanceDocumentacion==true">
							<td>
								<s:if test="%{rutaDocumento!=null && rutaDocumento !=''}">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
								</s:if>
							</td>
	 						<td>
 								&nbsp;
 							</td>
 							<td>
 								<s:if test="observacion==true">
 									<input id="capObsExpediente<s:property value="%{idExpediente}"/>" name="capObsExpediente" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck"  checked="checked" disabled="disabled"/>
 								</s:if>
 								<s:else>
 									<input id="capObsExpediente<s:property value="%{idExpediente}"/>" name="capObsExpediente" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck" onchange="verificaObservacion(this.value);" />
 								</s:else>
 							</td>
						</s:elseif>
						<s:elseif test="%{estatusCA==3}">
							<td>
								<s:if test="%{rutaDocumento!=null && rutaDocumento !=''}">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>		
									<s:if test="%{idExpediente == 4}">
										<s:hidden id="edoCuentaYaCapturado" name="edoCuentaYaCapturado" value="%{edoCuentaYaCapturado}"/>
									</s:if>						
								</s:if>
								<s:else>&nbsp;</s:else>
							</td>
							<td>
								<s:if test="%{idPrograma < '41'}">								
									<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
									<s:hidden id="docRequerido%{idExpediente}" name="docRequerido" value="%{}"/>									
								</s:if>
								<s:else>
									<s:if test="%{idExpediente!=8 && idExpediente!=9}"><!-- Diferente de la relacion de compras y reporte de cruce -->
										<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
										<s:hidden id="docRequerido%{idExpediente}" name="docRequerido" value="%{}"/>
									</s:if>
								</s:else>
							</td>
							<td>
								<label class=""><span class="norequerido">*</span>Observaci&oacute;n</label>
							</td>
							<td>
								<input id="capObsExpediente<s:property value="%{idExpediente}"/>" name="capObsExpediente" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck" onclick="verificaObservacion(this.value);"/>								
							</td>									
						</s:elseif>
						<s:elseif  test="estatusCA==4">
							<td>
								<s:if test="%{idPrograma < 41}">							
									<s:if test="observacion==true">
										<label class="">Documento Corregido</label>
									</s:if>
									<s:else><a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a></s:else>
								</s:if>
								<s:else>
									<s:if test="%{idExpediente==8}"><!-- RELACION DE COMPRAS -->
										<s:if test="%{#archivoRelacionCompras1 !=null && #archivoRelacionCompras1 !=''}">
											<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#archivoRelacionCompras1}"/>" title="Descargar Archivo">Descargar Archivo</a>
										</s:if>
									</s:if>
									<s:elseif test="%{idExpediente==9}" ><!-- REPORTE DE CRUCE -->
										<s:if test="%{#reporteCruce1 == 'true'}">
											<a href='<s:url value="/relaciones/verReportesCruce?folioCartaAdhesion=%{folioCartaAdhesion}"/>' title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;">Ver Reportes de Cruce</a>
										</s:if>
									</s:elseif>
								</s:else>
							</td>
							<td>
								<s:if test="observacion==true">
									<s:if test="%{idPrograma < 41}">	
										<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
									</s:if>
								</s:if>
								<s:else>&nbsp;</s:else>
							</td>
							<td>							
								<s:if test="rutaDocumentoHistorico!=null">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumentoHistorico}"/>" title="Descargar Archivo Histórico" class="DescargaArchHist">Descargar Hist&oacute;rico</a>
								</s:if>
								<s:if test="observacion==true">
									<label class=""><span class="norequerido">*</span>Observaci&oacute;n</label>
								</s:if>
								<s:else>&nbsp;</s:else>
							</td>
							<td>
								<s:if test="observacion==true">
									<input id="capObsExpediente<s:property value="%{idExpediente}"/>" name="capObsExpediente" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck"  checked="checked" onclick="verificaObservacion(this.value);" />
								</s:if>
								<s:else>&nbsp;</s:else>									
							</td>
						</s:elseif>
						<s:elseif  test="estatusCA==5 || estatusCA == 9 || estatusCA == 10">
							<td>
								<s:if test="%{idPrograma < 41}">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
								</s:if>
								<s:else><!-- PROGRAMAS QUE YA CONTEMPLAN RELACION DE COMPRAS COMO UN MODULO  -->
									<s:if test="%{idExpediente!=8 && idExpediente!=9}"><!-- Diferente de la relacion de compras y reporte de cruce -->
										<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
									</s:if>
									<s:elseif test="idExpediente==8"><!-- RELACION DE COMPRAS -->
										<s:if test="%{#archivoRelacionCompras1 !=null && #archivoRelacionCompras1 !=''}">
											<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#archivoRelacionCompras1}"/>" title="Descargar Archivo">Descargar Archivo</a>
										</s:if>
									</s:elseif>
									<s:elseif test="idExpediente==9"><!-- RELACION DE CRUCE-->
										<s:if test="%{#reporteCruce1 == 'true'}">
											<a href='<s:url value="/relaciones/verReportesCruce?folioCartaAdhesion=%{folioCartaAdhesion}"/>' title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;">Ver Reportes de Cruce</a>
										</s:if>		
									</s:elseif>
								</s:else>						
							</td>
							<td>
								<s:if test="rutaDocumentoHistorico!=null">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumentoHistorico}"/>" title="Descargar Archivo Histórico" class="DescargaArchHist">Descargar Hist&oacute;rico</a>
								</s:if>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>	
						</s:elseif>
						<s:elseif test="estatusCA==6 || estatusCA==8">
							<td>
								<s:if test="%{idPrograma < 41}">
									<s:if test="%{rutaDocumento!=null && rutaDocumento !=''}">
										<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
									</s:if>
									<s:else>&nbsp;</s:else>
								</s:if>
								<s:else>
									<s:if test="%{idExpediente!=8 && idExpediente!=9}"><!-- Diferente de la relacion de compras y reporte de cruce -->
										<s:if test="%{rutaDocumento!=null && rutaDocumento !=''}">
											<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumento}"/>" title="Descargar Archivo">Descargar Archivo</a>
										</s:if>
									</s:if>
									<s:elseif test="idExpediente==8"><!-- RELACION DE COMPRAS -->
										<s:if test="%{#archivoRelacionCompras1 !=null && #archivoRelacionCompras1 !=''}">
											<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{#archivoRelacionCompras1}"/>" title="Descargar Archivo">Descargar Archivo</a>
										</s:if>
									</s:elseif>
									<s:elseif test="idExpediente==9"><!-- RELACION DE CRUCE-->
										<s:if test="%{#reporteCruce1 == 'true'}">
											<a href='<s:url value="/relaciones/verReportesCruce?folioCartaAdhesion=%{folioCartaAdhesion}"/>' title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;">Ver Reportes de Cruce</a>
										</s:if>		
									</s:elseif>
								</s:else>
							</td>
							<td>								
								<s:if test="%{rutaDocumento!=null && rutaDocumento !=''}">
									&nbsp;
								</s:if>
								<s:else>
									<s:if test="%{idPrograma < 41}">
										<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
										<s:hidden id="docRequerido%{idExpediente}" name="docRequerido" value="%{}"/>										
									</s:if>
									<s:else>
										<s:if test="%{idExpediente!=8 && idExpediente!=9}"><!-- Diferente de la relacion de compras y reporte de cruce -->
											<s:file name="doc%{idExpediente}" id="doc%{idExpediente}"/>
											<s:hidden id="docRequerido%{idExpediente}" name="docRequerido" value="%{}"/>
										</s:if>
									</s:else>									
								</s:else>							
							</td>							
							<td>
								<s:if test="rutaDocumentoHistorico!=null">
									<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaDocumentoHistorico}"/>" title="Descargar Archivo Histórico" class="DescargaArchHist">Descargar Hist&oacute;rico</a>
								</s:if>
								<label class=""><span class="norequerido">*</span>Observaci&oacute;n</label>
							</td>
							<td>
								<s:if test="observacion==true">
									<input id="capObsExpediente<s:property value="%{idExpediente}"/>" name="capObsExpediente" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck"  checked="checked" onclick="verificaObservacion(this.value);"/>
								</s:if>
								<s:else>
									<input id="capObsExpediente<s:property value="%{idExpediente}"/>" name="capObsExpediente" value="<s:property value="%{idExpediente}"/>" type="checkbox"  class="ck" onclick="verificaObservacion(this.value);"/>
								</s:else>						
								
							</td>	
						</s:elseif>										
					</tr>
					<!-- DATOS ADICIONALES EN DOCUMENTOS Solicitud de Apoyo (3), Dictamen Contable (7) -->	
					<s:if test="%{idExpediente == 7}">
<!-- CAPTURA DE PERIODOS DE DICTAMEN DEL AUDITOR - INICIO -->					
						<tr>
							<td>
								<label class=""><span id="spFechaPeriodoInicialAuditor" class="norequerido">*</span>Periodo Inicio</label>
							</td>
							<td>							
								<s:if test="%{PeriodoInicialAuditor==null}" >
									<s:textfield name="fechaPeriodoInicialAuditor" maxlength="10" size="10" id="fechaPeriodoInicialAuditor" readonly="true" cssClass="dateBox"/>
									<img src="../images/calendar.gif" id="trgfechaPeriodoInicialAuditor" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
									<script type="text/javascript">
										<!--
											Calendar.setup({
												inputField     :    "fechaPeriodoInicialAuditor",     
												ifFormat       :    "%d/%m/%Y",     
												button         :    "trgfechaPeriodoInicialAuditor",  
												align          :    "Br",           
												singleClick    :    true
											});	
										//-->
									</script>		
								</s:if>
								<s:else>
									<font class="arial12bold"><s:text name="fecha"><s:param value="%{PeriodoInicialAuditor}"/></s:text></font>
								</s:else>								
							</td>
						</tr>
						<tr>
							<td><label class=""><span id="spFechaPeriodoFinalAuditor" class="norequerido">*</span>Periodo Termino</label></td>
							<td>								
								<s:if test="%{PeriodoFinalAuditor==null}" >
									<s:textfield name="fechaPeriodoFinalAuditor" maxlength="10" size="10" id="fechaPeriodoFinalAuditor" readonly="true" cssClass="dateBox" />
									<img src="../images/calendar.gif" id="trgfechaPeriodoFinalAuditor" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
									<script type="text/javascript">
										<!--
										Calendar.setup({
											inputField     :    "fechaPeriodoFinalAuditor",     
											ifFormat       :    "%d/%m/%Y",     
											button         :    "trgfechaPeriodoFinalAuditor",  
											align          :    "Br",           
											singleClick    :    true
										});									   
										//-->
									</script>
								</s:if>
								<s:else>
									<font class="arial12bold"><s:text name="fecha"><s:param value="%{PeriodoFinalAuditor}"/></s:text></font>
								</s:else>						
							</td>
						</tr>
<!-- CAPTURA DE PERIODOS DE DICTAMEN DEL AUDITOR - FIN -->						
						<s:if test="lstAuditorSolPagoV.size() > 0">	
							<tr>	
								<td colspan="6">
									<s:include value="auditorVolumen.jsp"/>
								</td>
							</tr>
						</s:if>
						
					</s:if>
					<s:if test="%{idExpediente == 3}">
						<s:if test="%{idCriterioPago == 1 || idCriterioPago == 3}">
							<tr class="datosVolumen">
								<td>
									<label class=""><span class="norequerido" id="spv">*</span>Volumen Solicitado a Apoyar</label>
								</td>
								<td class="cVolumen">
									<s:if test="%{estatusCA==5  || estatusCA== 9 || alcanceDocumentacion== true || (estatusCA == 4 && observacion==false)}">
										<s:textfield id="v3" name="volumen" value="%{getText('volumenSinComas',{volumen})}" maxlength="14" size="20" disabled="true"/>
									</s:if>
									<s:else>
										<s:if test="volumen!=null">
											<s:textfield id="v3" name="volumen" value="%{getText('volumenSinComas',{volumen})}" maxlength="14" size="20"/>
										</s:if>
										<s:else>
											<s:textfield id="v3" name="volumen" value="%{}" maxlength="14" size="20"/>
										</s:else>
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
						<s:hidden id="anexo32DyaCapturado" name="anexo32DyaCapturado" value="%{anexo32DyaCapturado}"/>						
						<tr class="datosAnexo5">						
							<td>
								<label class=""><span class="norequerido" id="spFE">*</span>Fecha de Expedici&oacute;n Anexo 32-D</label>
							</td>
							<td>								
								<s:if test="%{fechaExpedicionAnexo==null}" >
									<s:textfield name="fechaExpedicion" maxlength="10" size="10" id="fechaExpedicion" readonly="true" cssClass="dateBox" />
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
					<s:if test="%{idExpediente == 10 && totalVolumenCertificados!=0}">
						<tr>						
							<td>
								<label class=""><span class="norequerido" id="">*</span>Volumen Total Certificado</label>
							</td>
							<td>
								<s:textfield id="" name="" value="%{getText('volumenSinComas',{totalVolumenCertificados})}" maxlength="14" size="20" disabled="true"/>
							</td>
						</tr>
					</s:if>
					<s:if test="%{idExpediente == 34 && totalVolumenConstancia!=0}">
						<tr>						
							<td>
								<label class=""><span class="norequerido" id="">*</span>Volumen Total Constancia</label>
							</td>
							<td>
								<s:textfield id="" name="" value="%{getText('volumenSinComas',{totalVolumenConstancia})}" maxlength="14" size="20" disabled="true"/>
							</td>
						</tr>
					</s:if>
					
				</s:if>	<!-- end idExpediente!=1, idExpediente!=2 -->
				<div class="clear"></div>			
			</s:iterator>	
		</table>
		<div class="clear"></div>	
		<s:if test="estatusCA!=5  && estatusCA!=4 &&  estatusCA!=9">
			<div class="borderBottom"style="text-align:center"><h1>Acciones</h1></div><br>		
			<div class="inline">
				<label class="left1"><span class="norequerido">*</span>Seleccione una opci&oacute;n:</label>
				<s:radio label="" name="habilitaAccionSP" list="#{2:'TRAMITAR OFICIO DE OBSERVACIONES Y PAGO',1:'TRAMITAR PAGO SIN OBSERVACIONES:'}" value="%{}" onclick="recuperaDocRequeridos();"/>
			</div>	
		</s:if>		
		<div class="clear"></div>	
		<div id="recuperaDocRequeridosYhabilitaOficioObs"></div>
		<s:if test="estatusCA==4 && alcanceDocumentacion==false || estatusCA==5 || estatusCA== 9">
			<s:include value="datosOficioObservacion.jsp"/>
		</s:if>
		<s:if test="alcanceDocumentacion==true">
			<s:include value="datosOficioObservacion.jsp"/>
		</s:if>
		<div id="respuestaOficio">
			<s:if test="(estatusCA==4 && alcanceDocumentacion==false || estatusCA==5 || estatusCA == 9 || estatusCA == 10)  ">
				<s:include value="recuperaOficioObservacion.jsp"/>
	 			<s:include value="recuperaOficioRespuestaObservacion.jsp"/>
	 		</s:if>	
 		</div>
		<s:if test="estatusCA==4 || estatusCA==5 || estatusCA == 9 || estatusCA == 10">
			<s:include value="pagos.jsp"/>			
		</s:if>
		<s:if test="%{rutaFiniquito!=null && rutaFiniquito!=''}">
			<s:include value="finiquito.jsp"/>
		</s:if>
		<s:if test="deshabilitaAccion != true">
			<s:if test="%{#session.idPerfil!=4 }">
				<s:if test="%{ estatusCA==4 || sustituirArchivo ==true || estatusCA==3  || estatusCA == 6  ||  estatusCA == 8 }">
					<div class="accion">
						<s:submit  value="Guardar" cssClass="boton2"/>
						<a href="<s:url value="/solicitudPago/listarPrograma"/>" class="boton" title="">Cancelar</a>
					</div>
				</s:if>
			</s:if>
		</s:if>
	</fieldset>
	<script>
		$(document).ready(function(){
			if($("#capObsExpediente9").length){
				$("#capObsExpediente9").attr('disabled','disabled');
			}
			 
		}); 
	</script>

