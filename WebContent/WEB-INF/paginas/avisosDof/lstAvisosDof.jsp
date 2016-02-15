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
			<label class="left1">Clave del Aviso:</label>
			<s:textfield id="claveAviso" name="claveAviso" maxlength="6" size="31"/>
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar" cssClass="boton2" />
		</div>
	</fieldset>
<%-- 	<s:if test="%{bandera==true}"> --%>
		<s:if test="lstAvisosDofV.size() > 0">
				<div class="clear"></div>
				<br/>
				<fieldset>
					<legend>Resultado de B&uacute;squeda</legend>
					<div id="tablaResultados">
						<display:table id="r"  name="lstAvisosDofV"  list="lstOficioPagos"  pagesize="50" sort="list" requestURI="/avisosDof/buscarAvisoDof"  class="displaytag">
							<display:column  property="claveAviso" title="Clave"/>
							<display:column  property="leyenda" title="Leyenda"/>
							<display:column title="Fecha de Publicación" >
								<s:if test="%{#attr.r.fechaPublicacion!=null}">
									<s:text name="fecha"><s:param value="%{#attr.r.fechaPublicacion}"/></s:text>	
								</s:if>
							</display:column>
							<display:column title="Editar"  headerClass="sortable" >	
								<a href='<s:url value="/avisosDof/capturaAvisosDof?registrar=1&claveAviso=%{#attr.r.claveAviso}"/>' class="editar" title="" ></a>
					 		</display:column>
						</display:table>
					</div>
				</fieldset>
		</s:if>
<%-- 	</s:if> --%>
	
</s:form>