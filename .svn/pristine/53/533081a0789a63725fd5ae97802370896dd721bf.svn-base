<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/compradores.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<s:form action="ejecutaBusquedaCompradores" >
	<fieldset>
		<legend>Consulta de Compradores</legend>
		<div>
			<label class="left1">Nombre del Comprador:</label>
			<s:textfield name="nombre" maxlength="50" size="50" id="nombre" />
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
		    <s:submit  value="Consultar Comprador" cssClass="boton2" />
		</div>
	</fieldset>
</s:form>
<div class="derecha"><a href="<s:url value="/catalogos/agregarComprador"/>" onclick="" title="Agregar Comprador" >[Agregar Comprador]</a></div>
<div class="clear"></div>
	<s:if test="lstCompradores.size() > 0">
		<fieldset>
			<legend>Resultado de B&uacute;squeda</legend>
			<display:table id="r"  name="lstCompradores"  list="lstCompradores"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaCompradores"  class="displaytag">
				<display:column  property="nombre" title="Nombre"/>
				<s:if test="apellidoPaterno != null || apellidoPaterno != ''">
					<display:column  property="apellidoPaterno" title="Apellido Paterno"/>
				</s:if>
				<s:if test="apellidoPaterno != null || apellidoPaterno != ''">
					<display:column  property="apellidoMaterno" title="Apellido Materno"/>
				</s:if>
				<display:column  property="rfc" title="RFC"/>
				<display:column  property="curp" title="CURP"/>
				<display:column  property="folio" title="Folio"/>
				<display:column  title="ESTATUS">
					<s:if test='#attr.r.estatus==1'>
						Habilitado
					</s:if>
					<s:else>
						Inhabilitado
					</s:else>
				</display:column>
				<display:column title="Expediente"  headerClass="sortable" >
					<a href='<s:url value="/catalogos/verExpediente?idComprador=%{#attr.r.idComprador}"/>' class="expediente" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 	</display:column>
				<display:column title="Ver Detalle"  headerClass="sortable" >
					<a href='<s:url value="/catalogos/detallesComprador?idComprador=%{#attr.r.idComprador}&tipoPersona=%{#attr.r.tipoPersona}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 	</display:column>
			 	<display:column title="Editar"  headerClass="sortable" >
			 		<a href='<s:url value="/catalogos/recuperaDatosComprador?editar=3&idComprador=%{#attr.r.idComprador}&sexo=%{#attr.r.sexo}"/>' class="editar" title="" ></a>
			 	</display:column>
			 	<s:if test='#session.idPerfil==10'>
				 	<display:column title="Habilitar/Inhabilitar"  headerClass="sortable" >
				 	<s:set name="idC" ><s:property value="%{#attr.r.idComprador}"/></s:set>
				 				 		
				 		<s:if test="#attr.r.estatus == 1">
				 			<a id="o" href='<s:url value="/catalogos/cambiarEstatus?idComprador=%{#attr.r.idComprador}"/>' class="inhabilitar" title="" onclick="return cambiarEstatus(1, <s:property value="%{#idC}"/>);"></a>			 		
				 		</s:if>
				 		<s:else>
				 			<a href='<s:url value="/catalogos/cambiarEstatus?idComprador=%{#attr.r.idComprador}"/>' class="habilitar" title="" onclick="if (confirm('¿Esta seguro en Habilitar a esta Comprador?')){}else{return false;}"></a>
				 		</s:else>
				 	</display:column>
				 </s:if>
			</display:table>
		</fieldset>
	</s:if>
	<s:else><div class="advertencia">No se encontraron registros con los criterios capturados</div></s:else>

<script>
 function disp_confirm(){
	 var r=confirm("Press a button!");
	 if (r==true){
	   alert("You pressed OK!");
	 }else{
	  	alert("You pressed Cancel!");
	  	return false;
	 }
 }
 
 
 
 function cambiarEstatus(estatus, idComprador){
		var mensaje = "";
		var motivo = "";
		if(estatus == 1){
			motivo=prompt("Capture el motivo de inhabilitación ");
			if(motivo == null || motivo ==""){
				return false;
			}
			mensaje = "¿Esta seguro de Inhabilitar al Participante?";
		}else{
			mensaje = "¿Esta seguro en Habilitar al Participante?";
		}
		if (confirm(mensaje)){
			if(estatus == 1){			
				  $("#motivo").val(motivo);
				  $("#o").each(function(){ 
					    $(this).attr('href', 'SIPC/catalogos/cambiarEstatus?idComprador='+idComprador + '&motivo='+motivo); 
					});
			}
		}else{
			return false;
		}
		
	}

 </script>

