<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/avisosDof.js"/>"></script>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>AVISOS</h1></div><br>
<div class="derecha"><a href="<s:url value="/avisosDof/capturaAvisosDof?registrar=0"/>"  title="Registrar Solicitud" >[Registrar Aviso]</a></div>
<s:form action="buscarAvisoDof" onsubmit="return chkCampoBuscarAvisoDof();">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div>
		<label class="left1"><span class="requerido">*</span>Fecha de Publicaci&oacute;n:</label> 
			<s:textfield name="av.fechaPublicacion" maxlength="10" size="10" id="fechaPublicacion" readonly="true" cssClass="dateBox" />			
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
				
					Calendar.setup({
						inputField     :    "fechaPublicacion",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trg1",  
						align          :    "Br",           
						singleClick    :    true
					});
				//-->
			</script>	
	</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
<%-- 	<s:if test="%{bandera==true}"> --%>
		<s:if test="lstAvisosDof.size() > 0">
				<div class="clear"></div>
				<br/>
				<fieldset>
					<legend>Resultado de B&uacute;squeda</legend>
					<div id="tablaResultados">
						<display:table id="r"  name="lstAvisosDof"  list="lstOficioPagos"  pagesize="50" sort="list" requestURI="/avisosDof/buscarAvisoDof"  class="displaytag">
							<display:column  property="leyenda" title="Leyenda"/>
							<display:column title="Fecha de Publicación" >
								<s:if test="%{#attr.r.fechaPublicacion!=null}">
									<s:text name="fecha"><s:param value="%{#attr.r.fechaPublicacion}"/></s:text>	
								</s:if>
							</display:column>							
							<display:column title="Editar"  headerClass="sortable" >	
								<a href='<s:url value="/avisosDof/capturaAvisosDof?registrar=1&idAvisoDof=%{#attr.r.id}"/>' class="editar" title="" ></a>
					 		</display:column>
						</display:table>
					</div>
				</fieldset>
		</s:if>
<%-- 	</s:if> --%>
	
</s:form>