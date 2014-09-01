<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/funciones.js" />"></script>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<s:if test="hasActionErrors()">
   <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>INTERPRETE DE CONSULTAS SQL</h1></div><br>
<form  action="ejecutaSQL" name="forma" method="post"  enctype="multipart/form-data" onsubmit="return chkCamposInterpreteSQL();">
	<div id="dialogo_1"></div>
	<fieldset>
		<div class="clear"></div>	
		<div>
			<label class="left1"><span class="norequerido">*</span></label>
		</div>
		<div class="clear"></div>	
		
		<table class="clean" style="width:100%">
			<tr>
				<td width="10%">
					<label class="left1">Consulta SQL:</label>
				</td>
				<td width="80%">
					<s:textarea id="consultaSQL" name="consultaSQL" value="%{consultaSQL}" cols="130" rows="10" />
				</td>
			</tr>
		</table>
		<br/>		
		<div class="clear"></div>
		<div class="derecha">
			<p>
				<span class="requerido">*&nbsp;&nbsp;NOTA 1: Es importante considerar que se deben ingresar sentencias SQL v&aacute;lidas sint&aacute;cticamente y hacer referencia a tablas, vistas y campos de base de datos sem&aacute;nticamente v&aacute;lidos.</span>
				<br/>
				<span class="requerido">*&nbsp;&nbsp;NOTA 2: Se deber&aacute;n capturar en May&uacute;sculas las palabras reservadas <font class="arial12boldrojo">SELECT</font>, <font class="arial12boldrojo">FROM</font> y <font class="arial12boldrojo">WHERE</font> de la consulta principal.</span>
				<br/>
				<span class="requerido">*&nbsp;&nbsp;NOTA 3: Se debe utilizar el caracter <font class="arial12boldrojo">";"</font> como separador de cada columna seleccionada, as&iacute; como deber&aacute; llevar ALIAS cada una de las mismas <font class="arial12boldrojo">(ejemplo: "...</font>columna1<font class="arial12boldrojo"> AS </font>alias1<font class="arial12boldrojo">;</font>...").</span>
			</p>
		</div>		
	</fieldset>
	<div class="clear"></div>
	<div class="accion">
		<s:submit  value="Ejecutar" cssClass="boton2"/>
		<a href="<s:url value="/catalogos/ejecutaSQL"/>" class="boton" title="" >Limpiar</a>
	</div>	
	<div class="clear"></div>
	<s:if test="listRegistrosSQL.size() > 0">
		<div class="exporta_csv">
			<label class="label2"> Exportar Datos </label> <a href="<s:url value="/catalogos/exportaResultadoSQL"/>" title="Exportar Datos" ></a>
		</div>
		<div class="clear"></div>	
		<fieldset>
			<table style="width:100%">					
				<tr>
					<th>Resultado Consulta</th>
				</tr>
				<s:iterator value="listRegistrosSQL" var="resultado"  status="itStatus">
					<tr>
						<td align="left" width="1000"><font class="arial12bold"><s:property/></font></td>
					</tr>
				</s:iterator>
			</table>
		</fieldset>
	</s:if>
	<s:else>
		<br/>
		<div class="advertencia">No se encontraron registros</div>
	</s:else>
</form>