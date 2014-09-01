<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstAvisos.size()>0">
	<fieldset>
		<legend>Avisos</legend>
		<table id="tAvisos" class="displaytag">
			<tr>
				<th>Fecha</th>
				<th>Aviso</th>
				<th>&nbsp;</th>
			</tr>	
			<s:iterator value="lstAvisos" id="a" status="it">
				<tr>
					<td>
						<s:text name="fecha.aviso"><s:param value="fecha"/></s:text>
					</td>
					<td><s:property value="mensaje"/></td>
					<td class="c">
						<s:if test="%{!automatico}">
							<a href="#" class="boton1" title="" onclick="cierraAviso(<s:property value="idAviso"/>)">Cerrar</a>
						</s:if>
						<s:else>
							&nbsp;
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</table>
	</fieldset>
</s:if>
<s:else>
<div class="withborder">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</div>
</s:else>