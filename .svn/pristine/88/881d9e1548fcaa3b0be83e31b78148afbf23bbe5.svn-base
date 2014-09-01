<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<BR>
<div class="withborder">
	<span class="izquierda">
		<img width="312" height="102"  src="<s:url value="/images/logoSagarpa.png" />" />
	</span> 
	<span class="derecha">
		<font class="arial12bold">
			ASERCA<br>
			Coordinación General de Comercialización<br>
			Dirección General de Desarrollo de Mercados<br>
			Dirección de Pagos de Apoyos a la Comercialización<br><br>
			 <s:property value="leyendaOficio"/>
		</font>
	</span>
	<div class="clear"></div>
	<br>
	<p class="arial12normal">
		ATENTA NOTA No. <br>
		México, D.F. <s:property value="fechaActual"/><br>
		 
	</p>
	<p class="arial12normal">
		<s:property value="destinatario.nombre"/><br>
		<s:property value="destinatario.puesto"/><br>
		P&nbsp;R&nbsp;E&nbsp;S&nbsp;E&nbsp;N&nbsp;T&nbsp;E
	</p>
	<p class="arial12normal">En referencia a la operación del esquema <font class="arial12bold"><s:property value="nombrePrograma"/></font>, me permito someter a su consideración lo siguiente:</p>
	<p class="arial12normal">
		Derivado de la revisión del expediente con Carta de Adhesión <font class="arial12bold"><s:property value="folioCartaAdhesion"/></font> 
		del solicitante <font class="arial12bold"><s:property value="nombreComprador"/></font>, el cual participa con un <s:property value="leyendaCriterio"/> autorizado de <font class="arial12bold"><s:property value="cantidadCriterio"/></font>
		toneladas del cultivo <font class="arial12bold"><s:property value="cultivo"/></font>, se ha identificado un <s:property value="leyendaCriterio"/> susceptible de apoyo correspondiente al <font class="arial12bold"><s:property value="numeroPago"/><s:property value="textoFianza"/></font>,
		conforme a los datos que a continuación se especifican:
	</p>
	<div class="clear"></div>
	<table style="width:100%">
		<tr>
			<s:if test="criterioPago==1">
				<th>VARIEDAD</th>
				<th>VOLUMEN (Ton)</th>
				<th>IMPORTE ($)</th>
				<th>ESTADO(S)</th>
				<th>CUOTA *</th>
			</s:if>
			<s:elseif test="criterioPago==2">
				<th>VARIEDAD</th>
				<th>ETAPA</th>
				<th>IMPORTE ($)</th>
				<th>ESTADO(S)</th>
			</s:elseif>
			<s:elseif test="criterioPago==3">
				<th>VARIEDAD</th>
				<th>ETAPA</th>
				<th>VOLUMEN (Ton)</th>
				<th>IMPORTE ($)</th>
				<th>ESTADO(S)</th>
				<th>CUOTA *</th>
			</s:elseif>
		</tr>
		<s:iterator value="lstPagosVistaPrevia" var="resultado"  status="itStatus">
			<tr>
				<s:if test="idCriterioPago==1">
					<td align="center"><s:property value="variedad" /></td>
					<td align="center"><s:property value="volumen" /></td>
					<td align="center"><s:property value="importe" /></td>
					<td align="center"><s:property value="estado" /></td>
					<td align="center"><s:property value="cuota" /></td>
				</s:if>
				<s:elseif test="idCriterioPago==2">
					<td align="center"><s:property value="variedad" /></td>
					<td align="center"><s:property value="etapa" /></td>
					<td align="center"><s:property value="importe" /></td>
					<td align="center"><s:property value="estado" /></td>
				</s:elseif>
				<s:elseif test="idCriterioPago==3">
					<td align="center"><s:property value="variedad" /></td>
					<td align="center"><s:property value="etapa" /></td>
					<td align="center"><s:property value="volumen" /></td>
					<td align="center"><s:property value="importe" /></td>
					<td align="center"><s:property value="estado" /></td>
					<td align="center"><s:property value="cuota" /></td>
				</s:elseif>				
			</tr>	
		</s:iterator>	
	</table>
	<table style="width:100%" class="clean">
		<tr>
			<td align="center"><s:property value="leyendaEsquema" /></td>
		</tr>
	</table>
	<br>
	<p class="arial12normal">Datos de la cuenta bancaria:</p>
	<div class="clear"></div>
	<table style="width:100%">
		<tr>
			<th>Banco</th>
			<td><s:property value="nombreBanco"/></td>
			<th>No. Cuenta</th>
			<td><s:property value="cuenta"/></td>

		</tr>	
		<tr>
			<th>Clabe</th>
			<td><s:property value="clabe"/></td>
			<th>Estado</th>
			<td><s:property value="plaza.entidadPlaza"/></td>
		</tr>	
	</table>
	<div class="clear"></div>
	<p class="arial12normal">
		Sin más por el momento reciba un cordial saludo.
	</p>
	<br>
	<div style="text-align:center">
		<font class="arial12normal">A&nbsp;T&nbsp;E&nbsp;N&nbsp;T&nbsp;A&nbsp;M&nbsp;E&nbsp;N&nbsp;T&nbsp;E </font><br>
		<font class="arial12normal">
			<s:property value="emisor.nombre"/><br><br><br>
			<s:property value="emisor.puesto"/>
		</font>
	</div>
	<br>	
	<div class="accion">
		<a href='<s:url value="/solicitudPago/detallePagosCartaAdhesion?folioCartaAdhesion=%{folioCartaAdhesion}"/>' title="">Cerrar</a>
	</div>
</div>
