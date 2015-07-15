<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/clienteParticipante.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<s:form action="ejecutaBusquedaClienteParticipante" >
	<fieldset>	
		<legend>Consulta de Representantes Legales</legend>
		<div>
			<label class="left1">Nombre del Representante Legal:</label>
			<s:textfield name="clienteParticipante.nombre" maxlength="50" size="50" id="nombre" onkeyup ="convierteAmayuscula('nombre');"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">RFC:</label>
			<s:textfield name="clienteParticipante.rfc" maxlength="20" size="20" id="rfc" onkeyup ="convierteAmayuscula('rfc');" />
		</div>		
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Cliente" cssClass="boton2" />
		</div>
	</fieldset>
</s:form>
<div class="derecha"><a href="<s:url value="/catalogos/agregarClienteParticipante"/>" onclick="" title="Agregar Cliente" >[Agregar Cliente del Participante]</a></div>
<div class="clear"></div>
	<s:if test="lstClienteDeParticipante.size() > 0">
		<fieldset>
			<legend>Resultado de B&uacute;squeda</legend>
			<display:table id="r"  name="lstClienteDeParticipante"  list="lstClienteDeParticipante"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaClienteParticipante"  class="displaytag">
				<display:column  property="nombre" title="Nombre"/>
				<display:column  property="rfc" title="RFC"/>
				<display:column title="Ver Detalle"  headerClass="sortable" >
					<a href='<s:url value="/catalogos/agregarClienteParticipante?registrar=2&idCliente=%{#attr.r.id}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=750,scrollbars=yes'); return false;"></a>
			 	</display:column>
			 	<display:column title="Editar"  headerClass="sortable" >
			 		<a href='<s:url value="/catalogos/agregarClienteParticipante?registrar=1&idCliente=%{#attr.r.id}"/>' class="editar" title="" ></a>
			 	</display:column>
			</display:table>
		</fieldset>
	</s:if>
<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>
