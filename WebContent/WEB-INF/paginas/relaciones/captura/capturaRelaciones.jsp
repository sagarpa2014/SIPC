<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>		
	<div class="borderBottom"><h1><s:property value="%{nombreRelacion}"/></h1></div><br>
	<s:if test="msjOk!=null && msjOk !=''">
		<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>
	</s:if>
	<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
		<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
	</s:if>
	<s:if test="hasActionErrors()">
  	 	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp"/>
	</s:if>
	<div id="dialogo_1"></div>
	<fieldset>
		<legend>Captura de la Relaci&oacute;n</legend>
		<div class="clear"></div>
		<div>
			<label class="left1">Ciclo Agr&iacute;cola:</label>
			<s:textfield id="cicloAgricola" name="" maxlength="10" size="80" value="%{cicloAgricola}" disabled="true"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Cultivo:</label>
			<s:textfield id="cultivos" name="" maxlength="10" size="80" value="%{nombreCultivos}" disabled="true"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Tipo de Relaci&oacute;n:</label>
			<s:select id="idRelacion" name="idRelacion" list="lstRelaciones" listKey="idRelacion" listValue="%{relacion}" headerKey="-1" headerValue="-- Seleccione una opción --"  disabled="true"/>   
		</div>
		<div class="clear"></div>
		<!-- <div id="configuracionRelaciones"> -->
			<s:if test="%{idRelacion == 1}">
				<s:include value="configuracionRelacionesCompras.jsp"/>
			</s:if>	
			<s:elseif test="%{idRelacion == 2}">
				<s:include value="configuracionRelacionesTerrestre.jsp"/>
			</s:elseif>
			<s:elseif test="idRelacion == 3">
				<s:include value="configuracionRelacionesMaritima.jsp"/>
			</s:elseif>
			<s:elseif test="idRelacion == 4">
				<s:include value="configuracionRelacionesCertificados.jsp"/>
			</s:elseif>
			<s:elseif test="idRelacion == 5">
				<s:include value="configuracionRelacionesVentas.jsp"/>
			</s:elseif>	
		<!-- </div>-->
	</fieldset>
	<div class="clear"></div>
	