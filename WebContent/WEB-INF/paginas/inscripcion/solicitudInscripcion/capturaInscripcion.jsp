<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>REGISTRO DE SOLICITUD DE INSCRIPCI&Oacute;N</h1></div><br>  
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<s:form action="registrarSolicitudInscripcion" method="post" enctype="multipart/form-data" onsubmit="return chkCamposSolInscripcion();">
<s:if test="%{lstProgramasV.size()>0}" >
	<fieldset>
		<legend>Datos de la Solicitud</legend>
		<s:if test="fueraTiempo == 1"> <!-- Fuera de tiempo -->
			<div class="advertencia">La fecha de la solicitud esta fuera del limite de la fecha de publicación del programa</div>
			<div class="clear"></div>
			<div>
				<label class="left1">Fecha de solicitud de inscripci&oacute;n:</label>
				<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaDocSI}"/></s:text></font>	
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1">Fecha limite de inscripci&oacute;n:</label>
				<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaLimiteSI}"/></s:text></font>	
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1">Fecha de publicaci&oacute;n del programa:</label>
				<font class="arial12bold"><s:text name="fecha"><s:param value="%{fechaPublicacionDOF}"/></s:text></font>	
			</div>
			<div class="clear"></div>
			<div>
				<label class="left1">N&uacute;mero d&iacute;as limite de solicitud inscripci&oacute;n :</label>
				<font class="arial12bold"><s:property value="%{periodoDOFSI}"/></font>	
			</div>
			<div class="clear"></div>
		</s:if>
		<s:if test="folioCartaAdhesion!='' && folioCartaAdhesion!=null">
			<div>
				<label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label>
				<font class="arial14boldVerde">
					<s:if test="estatusSI==4">
						<a href='<s:url value="/inscripcion/recuperaCuotasInicEsquema?idSI=%{idSI}&registrar=3"/>' class="" title=""><s:property value="%{folioCartaAdhesion}"/></a>	
					</s:if>
					<s:else><s:property value="%{folioCartaAdhesion}"/></s:else>
				</font>
			</div>
			<br>
			
			<s:if test="estatusSI==1 || estatusSI==4">
				<div class="correcto">Acuse de folio de la carta de adhesi&oacute;n, favor de descargarlo en caso de que lo requiera</div>
				<div class="clear"></div>
				<br>
				<div class="pdf">
					<a href="<s:url value="/inscripcion/generarAcuseAsigCartaAdhesion?idSI=%{idSI}"/>" title="Descargar Archivo" ></a>
				</div>	
			</s:if>
		</s:if>
		<div class="clear"></div>
		<s:if test="estatusSI==1 || estatusSI==3">
			<s:if test="habilitaAccion==1">
				<div class="inline">
					<label class="left1"><span class="norequerido">*</span>Si requiere realizar una acci&oacute;n, seleccione una opci&oacute;n:</label>
					<s:radio label="" onclick="selectAccionAlcanceEdicion();"  name="tipoAccion" list="#{1:'ALCANCE', 2:'EDITAR'}" value="%{tipoAccion}"/>
				</div>
			</s:if>
			<s:elseif test="habilitaAccion==2">
				<div class="inline">
					<label class="left1"><span class="norequerido">*</span>Si requiere realizar un alcance marque la casilla:</label>
					<s:radio label="" onclick="selectAccionAlcanceEdicion();"  name="tipoAccion" list="#{1:'ALCANCE'}" value="%{tipoAccion}"/>
				</div>
			</s:elseif>
		</s:if>
		<div class="clear"></div>
		<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
		<s:hidden id="idSI" name="idSI" value="%{idSI}"/>
		<div class="borderBottom" style="text-align:center"><h1>Inscripci&oacute;n</h1></div><br>		
		<div>	
			<label class="left1"><span class="requerido">*</span>Programa:</label>
			<s:select id="idPrograma" name="idPrograma" list="lstProgramasV" listKey="idPrograma" listValue="%{descripcionCorta}" headerKey="-1" headerValue="-- Seleccione una opción --"  onclick="" onchange="" value="%{idPrograma}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Participante:</label>
			<s:select id="idComprador" name="idComprador" list="lstCompradores" listKey="idComprador" listValue="%{nombre+' '+' ('+rfc+')'}" headerKey="-1" headerValue="-- Seleccione una opción --" onclick="verificarDatosComprador();" onchange="verificarDatosComprador();" value="%{idComprador}" style="width:650px"/>
		</div>
		<div class="clear"></div>
		<div id="datosInscripcion">
			<s:if test="registrar==1 || registrar==2 || registrar==3">
				<s:include value="datosInscripcion.jsp"/>
			</s:if>
		</div>
		<div class="clear"></div>		
		<br>
	</fieldset>	
</s:if>
<s:else><div class="advertencia">No se encontraron registros</div></s:else>		
		
</s:form>

<s:if test="registrar == 3"> <!-- Edición -->
	<script>
		$(document).ready(function() {	
			$("#idPrograma").attr('disabled','disabled');
			$("#idComprador").attr('disabled','disabled');			
		});	 
	</script>
</s:if>
<s:if test="registrar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');
			$('input[name=tipoAccion]').removeAttr("disabled");
		});	 
	</script>
</s:if>
<s:elseif test="registrar == 3">
	<script>
		$(document).ready(function() {	
			
		});	 
	</script>
</s:elseif>

