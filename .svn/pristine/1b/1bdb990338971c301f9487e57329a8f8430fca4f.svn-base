<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<div class="clear"></div>
<center>
	<div>
		<table class="clean">
			<tr>
				<th class="clean"></th>
				<th class="clean">Carta Adhesi&oacute;n</th>
				<th class="clean">Especialista</th>
				<s:if test="registrar==0">
					<th class="clean">Fecha Firma Carta Adhesi&oacute;n</th>
				</s:if>	
				<s:if test="registrar==2 || registrar==3">
					<th class="clean">Estatus</th>
				</s:if>					
			</tr>
			
			<s:if test="registrar==0">				
				<s:iterator value="lstAsigCAEspecialista" var="resultado"  status="itStatus">
					<tr>
						<td><s:property value="%{#itStatus.count}"/></td>
						<td>
							<s:select id="ca%{#itStatus.count}" name="capCartaAdhesion" list="lstCartaAdhesion" listKey="folioCartaAdhesion" listValue="%{folioCartaAdhesion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{folioCartaAdhesion}" onchange="recuperaFechaCartaAdhesion(this.value, %{#itStatus.count});" />
						</td>
						<td>
							<s:select id="esp%{#itStatus.count}" name="capEspecialista" list="lstEspecialista" listKey="idEspecialista" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEspecialista}"/>
						</td>
						<s:if test="registrar==0">
							<td id="ffca<s:property value="%{#itStatus.count}"/>">
								<s:textfield key="" value="%{}" name="" maxlength="30" size="30" id="" readonly="true" />
							</td>
						</s:if>
					</tr>		
				</s:iterator>
			</s:if>
			<s:else>
				<s:iterator value="lstAsigCAEspecialistaV" var="resultado"  status="itStatus">
					<tr>
						<td><s:property value="%{#itStatus.count}"/></td>
						<td>
							<s:select id="ca%{#itStatus.count}" name="capCartaAdhesion" list="lstCartaAdhesion" listKey="folioCartaAdhesion" listValue="%{folioCartaAdhesion}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{folioCartaAdhesion}" onchange="recuperaFechaCartaAdhesion(this.value, %{#itStatus.count});" disabled="true"/>
						</td>
						<td>
							<s:if test="%{estatusCA == 3}">	
								<s:select id="esp%{#itStatus.count}" name="capEspecialista" list="lstEspecialista" listKey="idEspecialista" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEspecialista}"/>
								<s:hidden id="cap%{#itStatus.count}" name="capCartaAdhesionCopy" value="%{folioCartaAdhesion}"/>
							</s:if>
							<s:else>
								<s:select id="esp%{#itStatus.count}" name="capEspecialista" list="lstEspecialista" listKey="idEspecialista" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idEspecialista}" disabled="true"/>
							</s:else>
						</td>
						<td>
							<s:textfield id="estatusCA%{#itStatus.count}" name="volumen" value="%{estatusCartaAdhesion}" maxlength="14" size="50" disabled="true"/>
						</td>						
					</tr>		
				</s:iterator>
			</s:else>
		</table>
	</div>
</center>

