<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:form action="registraPersonalizacionRelacion" onsubmit="return chkCamposCapturaPersonalizacionRel();">
	<s:if test="hasActionErrors()">
	  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
	</s:if>
	<div class="borderBottom"><h1>CONFIGURACI&Oacute;N DE RELACIONES</h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
	</s:if>
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="tipoCaptura" name="tipoCaptura" value="%{tipoCaptura}"/>
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
	<s:if test="registrar==3">
		<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset>
		<legend>Personalizaci&oacute;n de Relaciones</legend>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Esquema:</label>
			<font class="arial12bold"><s:property value="%{nombreEsquema}"/></font>  
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Tipo de Relaci&oacute;n:</label>
			<s:select id="idRelacion" name="idRelacion" list="lstRelaciones" listKey="idRelacion" listValue="%{relacion}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaConfRelacionD()" onclik="recuperaConfRelacionD()" />   
		</div>
		<div class="clear"></div>
		<div id="recuperaConfRelacionD">
			<s:if test="registrar==2 || registrar==3">
				<s:include value="configuracionRelacionD.jsp"/>
			</s:if>
		</div>		
	</fieldset>
	<div class="clear"></div>
	<s:if test="registrar==0 || registrar == 3">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/relaciones/listPersonalizacionRelaciones?idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="" >Cancelar</a>
		</div>
	</s:if>
	<div class="clear"></div>
	<div class="izquierda">
		<s:if test="%{tipoCaptura == 0}">
			<a href="<s:url value="/relaciones/listPersonalizacionRelaciones?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&tipoCaptura=0"/>" class="" title="" >&lt;&lt; Regresar</a>	
		</s:if>
		<s:else>
			<s:if test='#session.idPerfil==10'>
				<a href="<s:url value="/relaciones/listPersonalizacionRelaciones?idPrograma=%{idPrograma}&tipoCaptura=1"/>" class="" title="" >&lt;&lt; Regresar</a>
			</s:if>
			<s:else>
				<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
				<a href="<s:url value="/relaciones/capturaCargaArchivoRelCompras?folioCartaAdhesion=%{folioCartaAdhesion}&tipoDocumentacion=1&tipoAccion=-1"/>" class="" title="" >&lt;&lt; Regresar</a>
			</s:else>
			
		</s:else>
		
	</div>
	<s:if test="registrar==2">
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');
			});	 	
		</script>
	</s:if>
	<s:elseif test="registrar==3">
		<script>
			$(document).ready(function() {	
				$("#idCultivo").attr('disabled','disabled');
				$("#cicloAgricola").attr('disabled','disabled');
				$("#idRelacion").attr('disabled','disabled');
			});	 	
		</script>
	</s:elseif>
	
</s:form>
