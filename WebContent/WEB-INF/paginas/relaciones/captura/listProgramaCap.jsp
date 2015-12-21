<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/relaciones.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>ESQUEMAS</h1></div><br>  
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<s:form action="ejecutaBusquedaPrograma" onsubmit="">
	<fieldset id="" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="norequerido">*</span>Esquema:</label>
			<s:select id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" list="lstIniEsquemaRelacionCriterio" listKey="idIniEsquemaRelacion" listValue="%{nombreEsquema}" headerKey="-1" headerValue="-- Seleccione una opción --" style="width:650px" />   
		</div>
		<div class="accion">	    	
		    <s:submit  value="Consultar" cssClass="boton2"/>
		</div>
	</fieldset>		
</s:form>
<br>
<s:if test="lstIniEsquemaRelacionV.size() > 0">
	<div class="clear"></div>
	<br/>
	<fieldset>
		<legend>Resultado de B&uacute;squeda</legend>
		<display:table id="r"  name="lstIniEsquemaRelacionV"  list="lstIniEsquemaRelacionV"  pagesize="50" sort="list" requestURI="/relaciones/ejecutaBusquedaProgramaRelaciones"  class="displaytag">
			<display:column  property="nombreEsquema" title="Esquema"/>
			<display:column  property="cultivos" title="Cultivo"/>
			<display:column  property="cicloAgricola" title="Ciclo Agricola"/>
			<display:column title="Relaciones"  headerClass="sortable"  class="c">
				<a href='<s:url value="/relaciones/listRelacionesCapturadas?idIniEsquemaRelacion=%{#attr.r.idIniEsquemaRelacion}"/>' title="">Ver</a>
			</display:column>
		</display:table>
	</fieldset>
</s:if>
<s:else><div class="advertencia">No se encontraron registros</div></s:else>

