<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<BR>
<div class="withborder">
	<s:if test="%{errorOficioDuplicado!=true}">
		<span class="izquierda">
			<img width="312" height="102"  src="<s:url value="/images/logoSagarpa.png" />" />
		</span> 
		<span class="derecha">
			<font class="arial12bold">
				ASERCA<br>
				Coordinación General de Comercialización<br>
				<s:property value="direccionEmisor"/><br><br>
				 <s:property value="leyendaOficio"/>
			</font>
		</span>
		<div class="clear"></div>
		<br>
		<p class="arial12normal">
			No. de Oficio <font class="arial12bold"><s:property value="claveOficio"/><s:property value="noOficio"/><s:property value="anioOficio"/></font><br>
			México, D.F. <s:property value="fechaActual"/><br>
			 
		</p>
		<p class="arial12normal">
			<s:property value="destinatario.nombre"/><br>
			<s:property value="destinatario.puesto"/><br>
			P&nbsp;R&nbsp;E&nbsp;S&nbsp;E&nbsp;N&nbsp;T&nbsp;E
		</p>
		<p class="arial12normal">Me refiero al <font class="arial12bold"><s:property value="nombrePrograma"/></font>.</p>
		<p class="arial12normal">
			Al respecto, anexo al presente envío a Usted, para la atención correspondiente, original de las Cartas de Adhesión 
			firmadas por las partes que en ellas intervienen los participantes que acontinuación se indican. 
		</p>
		<p class="arial12normal">
			Sin más por el momento reciba un cordial saludo.
		</p>
		<br>
		<center>
			<font class="arial12normal">A&nbsp;T&nbsp;E&nbsp;N&nbsp;T&nbsp;A&nbsp;M&nbsp;E&nbsp;N&nbsp;T&nbsp;E </font><br>
			<font class="arial12normal">
				<s:property value="emisor.puesto"/><br><br><br>
				<s:property value="emisor.nombre"/>
			</font>
		</center>
		<br>
		<div id="cpp">
			<div id="frasecpp"><font class="arial12normal">C.c.p</font></div>
			<div id="personal">
				<s:iterator value="lstPersonal" var="resultado"  status="itStatus">
					<font class="arial12normal"><s:property value="%{nombre}"/>&nbsp;<s:property value="%{paterno}"/>
					&nbsp;<s:property value="%{materno}"/>.-<s:property value="%{puesto}"/>.-Presente</font><br>
				</s:iterator>				
			</div>
		</div>
		<div class="clear"></div>
		<br>
		<div class="borderBottom" style="text-align:center"><font class="arial12bold">DETALLE DE CARTAS DE ADHESIÓN</font></div>
		<br>
		<div class="clear"></div>
		<table style="width:100%">
			<tr>
				<th>No.</th>
				<th>Comprador</th>
				<th>Carta de Adhesión</th>
			</tr>
			<s:iterator value="lstCartasV" var="resultado"  status="itStatus">
				<tr>
					<td align="center"><s:property value="%{#itStatus.count}"/></td>
					<td><s:property value="comprador" /></td>
					<td><s:property value="carta" /></td>
				</tr>	
			</s:iterator>	
		</table>
	</s:if>
	<s:else>
		<span class="error">
			El número de oficio ya se encuentra registrado, por favor verifique
		</span>	
	</s:else>
	<br>
	<div class="accion">
		<a href="#" class="boton" title="" onclick="cerrarVistaPreviaOficio()">Cerrar</a>
	</div>
</div>
