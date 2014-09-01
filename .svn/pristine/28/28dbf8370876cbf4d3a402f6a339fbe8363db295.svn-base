<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div class="borderBottom"><h1>SOLICITUD PAGO</h1></div><br>
<s:if test='#session.idPerfil==10'>
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
				<s:select id="idEspecialista" name="idEspecialista" list="lstEspecialista" listKey="idEspecialista" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="" value="%{}"/>
			</div>
			<div>
				<label class="left1">Estatus:</label>
				<s:select id="idEstatusCA" name="idEstatusCA" list="lstEstatusCartaAdhesion" listKey="idEstatusCA" listValue="%{descripcionStatus}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="" value="%{}"/>
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
		
		<display:table id="r" name="lstAsignacionCAaEspecialistaV"  list="lstAsignacionCAaEspecialistaV"  pagesize="50" sort="list" requestURI="/solicitudPago/verCartaAdehsionAsignadas"  class="displaytag">
			<display:column  property="folioCartaAdhesion" title="Carta Adhesi&oacute;n"/>
			<display:column  property="nombreComprador" title="Participante"/>
			<s:if test='#session.idPerfil==10'>
				<display:column  property="especialista" title="Especialista"/>
			</s:if>
			<display:column  property="estatusCartaAdhesion" title="Estatus"/>
			<display:column title="ED" >
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
			<display:column title="SP">
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
			<display:column title="DC">
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
			<display:column title="R">
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
	 		<display:column title="Documentaci&oacute;n">
				<a href='<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{#attr.r.folioCartaAdhesion}"/>' title="">Ver</a>
			</display:column>
		</display:table>		
</fieldset>
<div class="izquierda"><a href="<s:url value="/solicitudPago/listarPrograma"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>	


