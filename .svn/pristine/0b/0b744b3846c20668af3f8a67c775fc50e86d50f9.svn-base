<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div>	
</s:if>
<s:form action="ejecutaBusquedaAuditor" >
	<fieldset>	
		<legend>Consulta de Auditores</legend>
		<div>
			<label class="left1">N&uacute;mero de Auditor:</label>
			<s:textfield name="numeroAuditor" maxlength="10" size="10" id="numeroAuditor" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Nombre del Auditor:</label>
			<s:textfield name="nombre" maxlength="30" size="30" id="nombre" />
		</div>		
		<div>
			<p><span class="requerido">*&nbsp;Debe capturar el dato o seleccionar al menos una opci&oacute;n</span></p>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar Auditor" cssClass="boton2" />
		</div>
	</fieldset>
</s:form>
<s:if test='#session.idPerfil==1'>
	<div class="derecha"><a href="<s:url value="/catalogos/agregarAuditor"/>" onclick="" title="Agregar Auditor" >[Agregar Auditor]</a></div>
</s:if>
<div class="clear"></div>
	<s:if test="lstAuditoresExternos.size() > 0">
		<fieldset>
			<legend>Resultado de B&uacute;squeda</legend>
			<display:table id="r"  name="lstAuditoresExternos"  list="lstAuditoresExternos"  pagesize="50" sort="list" requestURI="/catalogos/ejecutaBusquedaAuditor"  class="displaytag">
				<display:column  property="nombre" title="Nombre"/>
				<display:column  property="numeroAuditor" title="Número Auditor"/>
				<display:column  title="ESTATUS">
					<s:if test='#attr.r.estatus==1'>
						Habilitado
					</s:if>
					<s:else>
						Inhabilitado
					</s:else>
				</display:column>
				<display:column title="Expediente"  headerClass="sortable">
					<a href='<s:url value="/catalogos/verExpedientesAuditor?numeroAuditor=%{#attr.r.numeroAuditor}"/>' class="expediente" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 	</display:column>
				<display:column title="Ver Detalle"  headerClass="sortable" >
					<a href='<s:url value="/catalogos/detallesAuditor?numeroAuditor=%{#attr.r.numeroAuditor}"/>' class="botonVerDetalles" title="" target="winload" onclick="window.open(this.href, this.target, 'width=600,height=400,scrollbars=yes'); return false;"></a>
			 	</display:column>
			 	<s:if test='#session.idPerfil==1'>
				 	<display:column title="Editar"  headerClass="sortable" >
				 		<a href='<s:url value="/catalogos/recuperaDatosAuditor?editar=3&numeroAuditor=%{#attr.r.numeroAuditor}"/>' class="editar" title="" ></a>
				 	</display:column>
			 	</s:if>
			 	<s:if test='#session.idPerfil==10'>	 		
				 	<display:column title="Habilitar/Inhabilitar"  headerClass="sortable" >
				 	<s:set name="idA" ><s:property value="%{#attr.r.numeroAuditor}"/></s:set>
				 				 		
				 		<s:if test="#attr.r.estatus == 1">
				 			<a id="o" href='<s:url value="/catalogos/cambiarEstatusAuditor?numeroAuditor=%{#attr.r.numeroAuditor}"/>' class="inhabilitar" title="" onclick="return cambiarEstatus(1, <s:property value="%{#idA}"/>);"></a>			 		
				 		</s:if>
				 		<s:else>
				 			<a href='<s:url value="/catalogos/cambiarEstatusAuditor?numeroAuditor=%{#attr.r.numeroAuditor}"/>' class="habilitar" title="" onclick="if (confirm('¿Esta seguro en Habilitar a esta Auditor?')){}else{return false;}"></a>
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
 
 function cambiarEstatus(estatus, numeroAuditor){
		var mensaje = "";
		var motivo = "";
		if(estatus == 1){
			motivo=prompt("Capture el motivo de inhabilitación ");
			if(motivo == null || motivo ==""){
				return false;
			}
			mensaje = "¿Esta seguro de Inhabilitar al Auditor?";
		}else{
			mensaje = "¿Esta seguro en Habilitar al Auditor?";
		}
		if (confirm(mensaje)){
			if(estatus == 1){			
				  $("#motivo").val(motivo);
				  $("#o").each(function(){ 
					    $(this).attr('href', 'SIPC/catalogos/cambiarEstatusAuditor?numeroAuditor='+numeroAuditor+ '&motivo='+motivo); 
					});
			}
		}else{
			return false;
		}
		
	}

 </script>
 