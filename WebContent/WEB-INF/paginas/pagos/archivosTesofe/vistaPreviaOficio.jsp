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
				Agencia de Servicios a la Comercialización y<br>
				Desarrollo de Mercados Agropecuarios <br>
				<!-- <s:property value="leyendaOficio"/>-->
			</font>
		</span>
		<div class="clear"></div>
		<span class="derecha">
			<font class="arial12normal">
				Coordinación General de Comercialización<br><br>
			</font>
		</span>		
		<div class="clear"></div>
		<span class="derecha">
			<font class="arial12normal">
				DIRECCION GENERAL DE DESARROLLO DE MERCADOS<br>
				E INFRAESTRUCTURA COMERCIAL <br>
			</font>
		</span>		
		<div class="clear"></div>
		<br>
		<p class="arial12normal">
			<font class="arial12bold">Oficio No. <s:property value="claveOficio"/><s:property value="noOficio"/><s:property value="anioOficio"/></font><br>
		</p>
		<span class="derecha">
			<font class="arial12normal">
				México, D.F. <s:property value="fechaActual"/><br>
			</font>
		</span>
		<br>
		<p class="arial12normal">
			<font class="arial12bold">
				<s:property value="destinatario.iniProfesion.toUpperCase()"/>&nbsp;<s:property value="destinatario.nombre"/><br>
				<s:property value="destinatario.puesto"/><br>
				PRESENTE
			</font>
		</p>
		<p class="arial12normal">
			<s:property value="%{primerParrafo}"/>
			<s:if test="%{version == 1}">
				<font class="arial12bold"><s:property value="%{nombrePrograma}"/></font>
			</s:if>
			<s:else><s:property value="%{nombrePrograma}"/></s:else>
			<s:property value="p11Texto"/>
			<font class="arial12bold"><s:property value="%{p12Texto}"/></font>
			<s:property value="p13Texto"/>	
			<s:if test="%{version == 1}"><font class="arial12bold"><s:property value="p14Texto"/></font></s:if>		
		</p>
		<p class="arial12normal">
			<s:property value="%{segundoParrafo}"/> 
			<font class="arial12bold"><s:property value="noDepositos"/><s:property value="p21Texto"/></font>
			<s:property value="p22Texto"/><font class="arial12bold"><s:property value="importeLetra"/></font>.
		</p>
		<p class="arial12normal">
			<s:property value="%{tercerParrafo}"/>
		</p>
		<p class="arial12normal">
			<s:property value="%{cuartoParrafo}"/>
		</p>
		<br>
		<center>
			<font class="arial12bold">A&nbsp;T&nbsp;E&nbsp;N&nbsp;T&nbsp;A&nbsp;M&nbsp;E&nbsp;N&nbsp;T&nbsp;E </font><br>
			<font class="arial12bold">
				<s:property value="emisor.puesto"/><br><br><br>
				<s:property value="emisor.iniProfesion.toUpperCase()"/>&nbsp;<s:property value="emisor.nombre"/>
			</font>
		</center>
		<br>
		<div id="cpp">
			<div id="frasecpp"><font class="arial12normal">C.c.e.p</font></div>
			<div id="personal">
				<font class="arial12normal"><s:property value="%{ccep1.iniProfesion}"/>&nbsp;<s:property value="%{ccep1.nombre}"/>&nbsp;<s:property value="%{ccep1.paterno}"/>&nbsp;<s:property value="%{ccep1.materno}"/>
				.-<s:property value="%{ccep1.puesto}"/>.-<s:property value="%{ccep1.correo}"/></font><br>
<!-- 
				<font class="arial12normal"><s:property value="%{ccep2.iniProfesion}"/>&nbsp;<s:property value="%{ccep2.nombre}"/>&nbsp;<s:property value="%{ccep2.paterno}"/>&nbsp;<s:property value="%{ccep2.materno}"/>
				.-<s:property value="%{ccep2.puesto}"/>.-<s:property value="%{ccep2.correo}"/></font><br>
				<font class="arial12normal"><s:property value="%{ccep3.iniProfesion}"/>&nbsp;<s:property value="%{ccep3.nombre}"/>&nbsp;<s:property value="%{ccep3.paterno}"/>&nbsp;<s:property value="%{ccep3.materno}"/>
				.-<s:property value="%{ccep3.puesto}"/>.-<s:property value="%{ccep3.correo}"/></font><br>
 -->							
			</div>
		</div>
		<div class="clear"></div>
		<br>
		<div class="borderBottom" style="text-align:center"><font class="arial12bold">DETALLE DE PAGOS</font></div>
		<br>
		<div class="clear"></div>
		<table style="width:100%">
			<tr>
				<th>Comprador</th>
				<th>No. Carta</th>
				<s:if test="criterioPago==2 || criterioPago==3">
					<th>Etapa</th>
				</s:if>
				<s:if test="criterioPago==1 || criterioPago==3">
					<th>Volumen</th>
				</s:if>
				<th>Importe</th>
			</tr>
			<s:iterator value="lstPagosV" var="resultado">
				<tr>
					<td><s:property value="nombreComprador" /></td>
					<td><s:property value="noCarta" /></td>
					<s:if test=" idCriterioPago==2 || idCriterioPago==3">
						<td align="center"><s:property value="%{etapa}"/></td>
					</s:if>
					<s:if test="idCriterioPago==1 || idCriterioPago==3">
						<td align="right"><s:text name="volumen"><s:param value="%{volumen}"/></s:text></td>
					</s:if>
					<td align="right"><s:text name="importe"><s:param value="%{importe}"/></s:text></td>
				</tr>	
			</s:iterator>	
			<tr>
				<td>&nbsp;</td>
				<td align="right"><font class="arial12bold">TOTALES:</font></td>
				<s:if test="criterioPago==2 || criterioPago==3">
					<td><s:property value="%{etapa}"/></td>
				</s:if>
				<s:if test="criterioPago==1 || criterioPago==3">
					<td align="right"><s:text name="volumen"><s:param value="%{totalVolumen}"/></s:text></td>
				</s:if>
				<td align="right">$&nbsp;<s:text name="importe"><s:param value="%{totalImporte}"/></s:text></td>
			</tr>
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
