<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/representantes.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<s:form action="ejecutaBusquedaRepLegal" >
	<fieldset>	
		<legend>Consulta de Representantes Legales</legend>
		<div>
			<label class="left1">Nombre del Representante Legal:</label>
			<s:textfield name="nombre" maxlength="30" size="30" id="nombre"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">RFC:</label>
			<s:textfield name="rfc" maxlength="20" size="20" id="rfc" />
		</div>		
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Representante" cssClass="boton2" />
		</div>
	</fieldset>
</s:form>
<div class="derecha"><a href="<s:url value="/catalogos/agregarRepLegal"/>" onclick="" title="Agregar Representante" >[Agregar Representante Legal]</a></div>
<div class="clear"></div>
	<s:if test="lstRepresentanteLegal.size() > 0">
		<fieldset>
			<legend>Resultado de B&uacute;squeda</legend>
			<display:table id="r"  name="lstRepresentanteLegal"  list="lstRepresentanteLegal"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaRepLegal"  class="displaytag">
				<display:column  property="nombre" title="Nombre"/>
				<s:if test="apellidoPaterno != null || apellidoPaterno != ''">
					<display:column  property="apellidoPaterno" title="Apellido Paterno"/>
				</s:if>
				<s:if test="apellidoPaterno != null || apellidoPaterno != ''">
					<display:column  property="apellidoMaterno" title="Apellido Materno"/>
				</s:if>
				<display:column  property="rfc" title="RFC"/>
				<display:column title="Expediente"  headerClass="sortable">
					<a href='<s:url value="/catalogos/verExpedientesRepLegal?idRepresentante=%{#attr.r.idRepresentante}"/>' class="expediente" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 	</display:column>
				<display:column title="Ver Detalle"  headerClass="sortable" >
					<a href='<s:url value="/catalogos/detallesRepresentante?idRepresentante=%{#attr.r.idRepresentante}&tipoPersona=%{#attr.r.tipoPersona}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 	</display:column>
			 	<display:column title="Editar"  headerClass="sortable" >
			 		<a href='<s:url value="/catalogos/recuperaDatosRepresentante?editar=3&idRepresentante=%{#attr.r.idRepresentante}"/>' class="editar" title="" ></a>
			 	</display:column>
			</display:table>
		</fieldset>
	</s:if>
<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
