<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>INICIALIZACI&Oacute;N ESQUEMA</h1></div><br>
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<s:form action="registroInicializaEsquemaRel"  method="post" enctype="multipart/form-data" onsubmit="return chkCamposInicializaProgramaRel();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<fieldset>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>T&iacute;tulo Aviso:</label>
			<s:textfield id="nombreEsquema" name="nombreEsquema"  maxlength="500" size="100"  value="%{nombreEsquema}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>No. de Ciclos a Capturar:</label>
			<s:select id="numCiclos" name="numCiclos" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'1':'1', '2':'2', '3':'3', '4':'4'}"  onchange="consigueNumCiclos(this.value);" value="%{numCiclos}"/>
		</div>		
		<div class="clear"></div>
		<div id="consigueNumCiclos">
			<s:if test="registrar!=0">
				<s:include value="ciclos.jsp"/>
			</s:if>
		</div>
		<div>
			<label class="left1"><span class="requerido">*</span>No. de Cultivos a Capturar:</label>
			<s:select id="numCultivos" name="numCultivos" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'1':'1', '2':'2', '3':'3', '4':'4'}"  onchange="consigueNumCultivos(this.value);" value="%{numCultivos}"/>
		</div>
		<div class="clear"></div>
		<div id="consigueNumCultivos">
			<s:if test="registrar!=0">
				<s:include value="cultivos.jsp"/>
			</s:if>
		</div>		
	</fieldset>
	<br>	
	<s:if test="registrar!=2">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/relaciones/listProgramaRelacion"/>" class="boton" title="" >Cancelar</a>
		</div>
	</s:if>
	<div class="clear"></div>
	<div class="izquierda">
		<a href="<s:url value="/relaciones/listProgramaRelacion"/>" class="" title="" >&lt;&lt; Regresar</a>
	</div>
	
	<s:if test="registrar==2">
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');
			});	 	
		</script>
	</s:if>
	
</s:form>
<script>
	 
</script>

