<%@taglib uri="/struts-tags" prefix="s"%>
<div class="borderBottom" style="text-align:center"><h1>Expediente</h1></div><br>
<fieldset>
	<legend>Expedientes</legend>	
	<table>
		<tr>
			<th>Documento</th>
			<s:if test='#session.idPerfil==1'>
				<th colspan="2">Cargar/Sustituir</th>
			</s:if>
		</tr>
		<s:iterator value="lstExpedientes" status="itStatus">
			<tr>
				<s:set name="idEP"><s:property value="idExpediente"/></s:set>
				<s:set name="tieneExpediente">0</s:set>
				<s:set name="id">0</s:set>
				<s:if test="lstAuditorExpedientesV.size()>0">
					<s:iterator value="lstAuditorExpedientesV" status="itStatus">
						<s:if test="idExpediente==#idEP">
							<s:set name="tieneExpediente">1</s:set>		
							<s:set name="id"><s:property value="idExpedienteAuditor"/></s:set>
							<s:set name="rutaExpediente"><s:property value="rutaExpediente"/></s:set>		
						</s:if>
					</s:iterator>
				</s:if>
				<td>
					<s:if test="#tieneExpediente!=0">
						<s:hidden name="rutaAuditor" value="%{rutaAuditor}"/>
						<a href='<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaAuditor}%{#rutaExpediente}"/>' title=""><s:property value="expediente"/></a>
					</s:if>
					<s:else><s:property value="expediente"/></s:else>
				</td>
				<s:if test='#session.idPerfil==1'>
					<td>			
						<s:form action="cargarExpedienteAuditor" method="post" enctype="multipart/form-data" onsubmit="return chkFileExpediente(%{#itStatus.count});">
							<s:hidden name="idExpediente"  value="%{idExpediente}"/>
							<s:hidden name="numeroAuditor"  value="%{numeroAuditor}"/>
							<s:hidden name="tipoExpediente"  value="%{tipoExpediente}" />
							<s:file  name="doc" id="f%{#itStatus.count}" />
							<s:if test="#tieneExpediente==1">
								<s:hidden name="idExpedienteAuditor"  value="%{id}"/>
								<s:submit value="Sustituir"/>
							</s:if>
							<s:else>
								<s:hidden name="idExpedienteAuditor"  value="%{0}"/>
								<s:submit value="Cargar"/>
							</s:else>
						</s:form>
					</td>
				</s:if>
			</tr>
		</s:iterator>
	</table>
</fieldset>