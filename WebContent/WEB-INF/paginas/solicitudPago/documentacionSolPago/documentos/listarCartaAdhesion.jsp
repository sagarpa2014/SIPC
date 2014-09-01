<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:if test='#session.idPerfil==10 || #session.idPerfil==6'>
	<div class="borderBottom"><h1>SOLICITUD PAGO</h1></div><br>
</s:if>
<s:else>
	<div class="borderBottom"><h1>APOYOS</h1></div><br>
</s:else>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<s:if test='#session.idPerfil==10 || #session.idPerfil==4  || #session.idPerfil==1'>
	<s:form action="verCartaAdehsionAsignadas" onsubmit="return chkCampoConsultaCartaAdhesionSP();">
		<div id="dialogo_1"></div>
		<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
		<fieldset id="" class="clear">
			<legend>Criterios de B&uacute;squeda</legend>
			<div>
				<label class="left1">Participante:</label>
				<s:textfield id="busParticipante" name="busParticipante" maxlength="100" size="100"/>
			</div>
			<div>
				<label class="left1">Folio Carta Adhesi&oacute;n:</label>
				<s:textfield id="folioCartaAdhesion" name="folioCartaAdhesion" maxlength="30" size="31"/>
			</div>
			<div>
				<label class="left1">Especialista:</label>
				<s:select id="idEspecialista" name="idEspecialista" list="lstEspecialista" listKey="idEspecialista" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" onchange="" value="%{}"/>
			</div>
			<div>
				<label class="left1">Estatus:</label>
				<s:select id="idEstatusCA" name="idEstatusCA" list="lstEstatusCartaAdhesion" listKey="idEstatusCA" listValue="%{descripcionStatus}" headerKey="-1" headerValue="-- Seleccione una opci�n --" tabindex="0" onchange="" value="%{}"/>
			</div>
			<div>
				<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
			</div>
			<div class="accion">	    	
			    <s:submit  value="Consultar" cssClass="boton2" />
			</div>
		</fieldset>
	</s:form>
</s:if>

<fieldset>
	<legend>Programa de Apoyos: <font class="arial14bold"><s:property value="%{nombrePrograma}"/></font></legend>
		
		<display:table id="r" name="lstAsignacionCAaEspecialistaV"  list="lstAsignacionCAaEspecialistaV"  pagesize="30" sort="list" requestURI="/solicitudPago/verCartaAdehsionAsignadas"  class="displaytag">
			<s:if test='#session.idPerfil==6'>
				<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n" class="c"/>
			</s:if>
			<s:else>
				<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n" class="dcolor3"/>
			</s:else>
			<s:if test='#session.idPerfil==10 || #session.idPerfil==1'>
				<display:column  property="nombreComprador" title="Participante" class="dcolor" />
			</s:if>
			<s:elseif test='#session.idPerfil==4'>
				<display:column  property="nombreComprador" title="Participante" class="dcolor2" />
			</s:elseif>
			<s:else><display:column  property="nombreComprador" title="Participante" /></s:else>
			<s:if test='#session.idPerfil==10 || #session.idPerfil==1'>
				<display:column  property="especialista" title="Especialista " class="dcolor2" />
			</s:if>		
			<s:if test='#session.idPerfil==10 || #session.idPerfil==4 || #session.idPerfil==1'>
				<display:column   title="Volumen (Ton)" class="dcolor">	
					<s:text name="volumen"><s:param value="%{#attr.r.volumenAutorizado}"/></s:text>
				</display:column>
				<display:column   title="Importe ($)" class="dcolor">	
					<s:text name="importe"><s:param value="%{#attr.r.importeAutorizado}"/></s:text>
				</display:column>			
				<display:column  title="Volumen (Ton)" class="dcolor2">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenEnRevision}"/></s:text>
				</display:column>
				<display:column  title="Importe ($)" class="dcolor2">
					<s:text name="importe"><s:param value="%{#attr.r.importeEnRevision}"/></s:text>
				</display:column>
				<display:column   title="Volumen (Ton)" class="dcolor">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenAutorizadoPago}"/></s:text>
				</display:column>				
				<display:column   title="Importe ($)" class="dcolor">
					<s:text name="importe"><s:param value="%{#attr.r.importeAutorizadoPago}"/></s:text>
				</display:column>
				<display:column  title="Volumen (Ton)" class="dcolor2">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenApoyado}"/></s:text>
				</display:column>
				<display:column   title="Importe ($)" class="dcolor2">
					<s:text name="importe"><s:param value="%{#attr.r.importeApoyado}"/></s:text>
				</display:column>
				<display:column   title="Expediente Cerrado" class="ccolor">
					<s:if test='#attr.r.estatusCA==9 || #attr.r.estatusCA==10 '>
						<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
					</s:if>
					<s:else>
						<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
					</s:else>
				</display:column>	
				<s:if test='#session.idPerfil==10 || #session.idPerfil==1'>
					<display:footer>
				      <tr>
				        <td colspan="3" class="dcolor3"><font class="arial12boldWhite">Totales</font></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolSusceptible}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpSusceptible}"/></s:text></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolEnRevision}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpEnRevision}"/></s:text></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolEnTramPago}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpEnTramPago}"/></s:text></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolPago}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpPago}"/></s:text></td>
						<s:if test='#session.idPerfil==10'>        
				        	<td colspan ="9" class="dcolor3">&nbsp;</td>
				        </s:if>
				        <s:else>
				        	<td colspan ="3" class="dcolor3">&nbsp;</td>
				        </s:else>
				      </tr>
			    	</display:footer>
				</s:if>			
				<s:else>
					<display:footer>
				      <tr>
				        <td colspan="2" class="dcolor3"><font class="arial12boldWhite">Totales</font></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolSusceptible}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpSusceptible}"/></s:text></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolEnRevision}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpEnRevision}"/></s:text></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolEnTramPago}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpEnTramPago}"/></s:text></td>
				        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolPago}"/></s:text></td>
				        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpPago}"/></s:text></td>
				        <td colspan ="2" class="dcolor3">&nbsp;</td>
				      </tr>
			    	</display:footer>
				</s:else>
			</s:if>	
		
			<s:if test='#session.idPerfil==10 || #session.idPerfil==1'>
				<display:column  property="estatusCartaAdhesion" title="Estatus" class="dcolor2"/>
			</s:if>
			<s:elseif test='#session.idPerfil==6' >
				<display:column  property="estatusCartaAdhesion" title="Estatus" />	
			</s:elseif>	
			<s:if test='#session.idPerfil==10 || #session.idPerfil==1'>	
				<s:if test='#session.idPerfil==10'> <!-- Los semaforos se mostraran solo al perfil 10 -->		
					<display:column title="ED" class="dcolor">
						<s:if test='#attr.r.ed==2'>			
							<img src="<s:url value="/images/verde.jpg"/>" width="15" height="15" alt="Verde" />	
						</s:if>
						<s:elseif test='#attr.r.ed==0'>								
							<img src="<s:url value="/images/rojo.jpg"/>"width="15" height="15" alt="rojo" />
						</s:elseif>
						<s:elseif test='#attr.r.ed==1'>								
							<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
						</s:elseif>
					</display:column>
					<display:column title="SP" class="dcolor">
						<s:if test='#attr.r.sp==2'>
							<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
						</s:if>
						<s:elseif test='#attr.r.sp==0'>								
							<img src="<s:url value="/images/rojo.jpg"/>"width="15" height="15" alt="rojo" />
						</s:elseif>
						<s:elseif test='#attr.r.sp==1'>								
							<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
						</s:elseif>
					</display:column>
					<display:column title="DC" class="dcolor">
						<s:if test='#attr.r.dc==2'>
							<img src="<s:url value="/images/verde.jpg"/>" width="15" height="15" alt="Verde" />	
						</s:if>
						<s:elseif test='#attr.r.dc==0'>								
							<img src="<s:url value="/images/rojo.jpg"/>"width="15" height="15" alt="rojo" />
						</s:elseif>
						<s:elseif test='#attr.r.dc==1'>								
							<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
						</s:elseif>
					</display:column>
					<display:column title="R" class="dcolor">
						<s:if test='#attr.r.r==2'>
							<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
						</s:if>
						<s:elseif test='#attr.r.r==0'>								
							<img src="<s:url value="/images/rojo.jpg" />"width="15" height="15" alt="rojo" />
						</s:elseif>
						<s:elseif test='#attr.r.r==1'>								
							<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
						</s:elseif>
					</display:column>
					<display:column title="Finiquito" class="dcolor">
						<s:if test='#attr.r.expdienteFiniquitado==true'>
							<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
						</s:if>
						<s:else>								
							<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
						</s:else>
					</display:column>
				</s:if>
				<display:column title="Cierre Expediente" class="dcolor">
					<s:if test='#attr.r.estatusCA==9'>
							<a href='<s:url value="/solicitudPago/cierreExpedienteCARevocar?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&idPrograma=%{idPrograma}"/>' title="" onclick="if (confirm('�Esta seguro de Reabrir Expediente de la Carta de Adhesi�n?')){}else{return false;}">Reabrir</a>										
					</s:if>
					<s:elseif test='#attr.r.estatusCA==4 || #attr.r.estatusCA==5 '>
						<a href='<s:url value="/solicitudPago/cierreExpedienteCA?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&idPrograma=%{idPrograma}"/>' title="" onclick="if (confirm('�Esta seguro de Cerrar Expediente de la Carta de Adhesi�n?')){}else{return false;}">Confirmar</a>
					</s:elseif>
				</display:column>													
			</s:if> <!-- End perfil 10 o 1 -->
			<s:elseif test='#session.idPerfil==6'>
				<display:column title="ED" class="c">
					<s:if test='#attr.r.ed==2'>			
						<img src="<s:url value="/images/verde.jpg"/>" width="15" height="15" alt="Verde" />	
					</s:if>
					<s:elseif test='#attr.r.ed==0'>								
						<img src="<s:url value="/images/rojo.jpg"/>"width="15" height="15" alt="rojo" />
					</s:elseif>
					<s:elseif test='#attr.r.ed==1'>								
						<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
					</s:elseif>
				</display:column>
				<display:column title="SP" class="c">
					<s:if test='#attr.r.sp==2'>
						<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
					</s:if>
					<s:elseif test='#attr.r.sp==0'>								
						<img src="<s:url value="/images/rojo.jpg"/>"width="15" height="15" alt="rojo" />
					</s:elseif>
					<s:elseif test='#attr.r.sp==1'>								
						<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
					</s:elseif>
				</display:column>
				<display:column title="DC" class="c">
					<s:if test='#attr.r.dc==2'>
						<img src="<s:url value="/images/verde.jpg"/>" width="15" height="15" alt="Verde" />	
					</s:if>
					<s:elseif test='#attr.r.dc==0'>								
						<img src="<s:url value="/images/rojo.jpg"/>"width="15" height="15" alt="rojo" />
					</s:elseif>
					<s:elseif test='#attr.r.dc==1'>								
						<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
					</s:elseif>
				</display:column>
				<display:column title="R" class="c">
					<s:if test='#attr.r.r==2'>
						<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
					</s:if>
					<s:elseif test='#attr.r.r==0'>								
						<img src="<s:url value="/images/rojo.jpg" />"width="15" height="15" alt="rojo" />
					</s:elseif>
					<s:elseif test='#attr.r.r==1'>								
						<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
					</s:elseif>						
				</display:column>
				<display:column title="Finiquito" class="c">
					<s:if test='#attr.r.expdienteFiniquitado == true'>
						<img src="<s:url value="/images/verde.jpg" />" width="15" height="15" alt="Verde" />	
					</s:if>
					<s:else>								
						<img src="<s:url value="/images/amarillo.jpg" />" width="15" height="15" alt="amarillo" />
					</s:else>
				</display:column>
			</s:elseif>
			<s:if test='#session.idPerfil==6'>
				<display:column title="Documentaci&oacute;n" class="c">
					<a href='<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}"/>' title="">Ver</a>
				</display:column>
				<display:column title="Finiquito" class="c">
					<a href='<s:url value="/solicitudPago/capturarFiniquito?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}&idPrograma=%{idPrograma}&tipoVistaFiniquito=1"/>' title="">
					<s:if test='#attr.r.expdienteFiniquitado == true'>Ver</s:if>
					<s:else>Cargar</s:else>
					</a>
				</display:column>					
			</s:if>
			<s:elseif test='#session.idPerfil==10 || #session.idPerfil==4'>
				<display:column title="Documentaci&oacute;n" class="ccolor">
					<a href='<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}"/>' title="">Ver</a>
				</display:column>
			</s:elseif>	 		
		</display:table>		
</fieldset>
<div class="izquierda"><a href="<s:url value="/solicitudPago/listarPrograma"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>	

<s:if test='#session.idPerfil==4'>
	<script>
		$(document).ready(function() {
			$('#r > thead').prepend('<tr><th colspan="2">&nbsp;</th><th colspan="2">Autorizada en Carta</th><th colspan="2">Lista para Pago</th><th colspan="2">En Tr&aacute;mite de Pago (DGAF-SIAFF)</th><th colspan="2">Pagos</th><th colspan="2">&nbsp;</th></tr>');
		});
	</script>
</s:if>
<s:if test='#session.idPerfil==10'>
	<script>
		$(document).ready(function() {
			$('#r > thead').prepend('<tr><th colspan="3">&nbsp;</th><th colspan="2">Autorizada en Carta</th><th colspan="2">Lista para Pago</th><th colspan="2">En Tr&aacute;mite de Pago (DGAF-SIAFF)</th><th colspan="2">Pagos</th><th colspan="9">&nbsp;</th></tr>');
		});
	</script>
</s:if>
<s:if test='#session.idPerfil==1'>
	<script>
		$(document).ready(function() {
			$('#r > thead').prepend('<tr><th colspan="3">&nbsp;</th><th colspan="2">Autorizada en Carta</th><th colspan="2">Lista para Pago</th><th colspan="2">En Tr&aacute;mite de Pago (DGAF-SIAFF)</th><th colspan="2">Pagos</th><th colspan="3">&nbsp;</th></tr>');
		});
	</script>
</s:if>


