<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:if test='#session.idPerfil==10 || #session.idPerfil==6'>
	<div class="borderBottom"><h1>SOLICITUD PAGO</h1></div><br>
</s:if>
<s:else>
	<div class="borderBottom"><h1>APOYOS</h1></div><br>
</s:else>
<fieldset>
	<legend>Programas</legend>	
	
		<s:if test='#session.idPerfil==6'>
			<display:table id="r" name="lstPrgEspecialistaNumCartasVs"  list="lstPrgEspecialistaNumCartasVs"  pagesize="50" sort="list" requestURI="/solicitudPago/ejecutaBusquedaAsignacionCAaEspecialista"  class="displaytag">
				<display:column  property="programa" title="Programa"/>
				<display:column  property="numeroCartas" title="N&uacute;mero de Cartas"/>
		 		<display:column title="Cartas Adhesi&oacute;n">
					<a href='<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{#attr.r.idPrograma}"/>' title="">Ver</a>
				</display:column>
			</display:table>
		</s:if>		
		<s:elseif test='#session.idPerfil==1 || #session.idPerfil==4 || #session.idPerfil==10'>
			<display:table id="r" name="lstNumCartasVs" list="lstNumCartasVs"  pagesize="50" sort="list" requestURI="/solicitudPago/listarPrograma"  class="displaytag" >
				<display:column title="Programa"  class="dcolor3">
					<a href='<s:url value="/inscripcion/listarProgramas?idPrograma=%{#attr.r.idPrograma}"/>' title="" class="link1"><s:property value ="%{#attr.r.programa}" /></a>
				</display:column>				
		 		<display:column property="numeroCartas" title="Firmadas" class="dcolor2"/>
				<display:column  property="noCartaOperacion" title="En proceso" class="dcolor2"/> 
				<display:column  property="noCartaCerradas" title="Cerradas" class="dcolor2"/>
				<display:column  title="Volumen (Ton)" class="dcolor">	
					<s:text name="volumen"><s:param value="%{#attr.r.volumenSusceptible}"/></s:text>
				</display:column>
				<display:column  title="Importe ($)" class="dcolor">	
					<s:text name="importe"><s:param value="%{#attr.r.importeSusceptible}"/></s:text>
				</display:column>			
				<display:column  title="Volumen (Ton)" class="dcolor2">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenEnRevision}"/></s:text>
				</display:column>
				<display:column  title="Importe ($)" class="dcolor2">
					<s:text name="importe"><s:param value="%{#attr.r.importeEnRevision}"/></s:text>
				</display:column>
				<display:column   title="Volumen (Ton)" class="dcolor">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenAutorizado}"/></s:text>
				</display:column>				
				<display:column   title="Importe ($)" class="dcolor">
					<s:text name="importe"><s:param value="%{#attr.r.importeAutorizado}"/></s:text>
				</display:column>
				<display:column  title="Volumen (Ton)" class="dcolor2">
					<s:text name="volumen"><s:param value="%{#attr.r.volumenApoyado}"/></s:text>
				</display:column>
				<display:column   title="Importe ($)" class="dcolor2">
					<s:text name="importe"><s:param value="%{#attr.r.importeApoyado}"/></s:text>
				</display:column>
				<display:column title="Cartas Adhesi&oacute;n"  class="ccolor">
					<a href='<s:url value="/solicitudPago/verCartaAdehsionAsignadas?idPrograma=%{#attr.r.idPrograma}"/>' title="">Ver</a>
				</display:column>
				<display:footer>
			      <tr>
			        <td colspan="1" class="dcolor3"><font class="arial12boldWhite">Totales</font></td>
			        <td class="dcolor3"><s:text name="contador"><s:param value="%{totalCAFirmadas}"/></s:text></td>
			        <td class="dcolor3"><s:text name="contador"><s:param value="%{totalCAEnRevision}"/></s:text></td>
			        <td class="dcolor3"><s:text name="contador"><s:param value="%{totalCACerradas}"/></s:text></td>
			        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolSusceptible}"/></s:text></td>
			        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpSusceptible}"/></s:text></td>
			        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolEnRevision}"/></s:text></td>
			        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpEnRevision}"/></s:text></td>
			        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolEnTramPago}"/></s:text></td>
			        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpEnTramPago}"/></s:text></td>
			        <td class="dcolor3"><s:text name="volumen"><s:param value="%{totalVolPago}"/></s:text></td>
			        <td class="dcolor3"><s:text name="importe"><s:param value="%{totalImpPago}"/></s:text></td>
			        <td class="dcolor3">&nbsp;</td>
			      </tr>
			    </display:footer>
			    
			</display:table>
		</s:elseif>
</fieldset>
<s:if test='#session.idPerfil==10 || #session.idPerfil==4'>
	<script>
		$(document).ready(function() {
			$('#r > thead').prepend('<tr><th colspan="1">&nbsp;</th><th colspan="3">Cartas de Adhesi&oacute;n</th><th colspan="2">Autorizada en Carta</th><th colspan="2">Listas para Pago</th><th colspan="2">En Tr&aacute;mite de Pago (DGAF-SIAFF)</th><th colspan="2">Pagos</th><th colspan="1">&nbsp;</th></tr>');
			
			 
		});
	</script>
</s:if>




	


