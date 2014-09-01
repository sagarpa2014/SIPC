<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:form action="" method="post" enctype="multipart/form-data" accept-charset="utf-8" onsubmit="">
	<s:hidden id="numCampos" name="numCampos" value="%{numCampos}"/>
	<s:hidden id="criterioPago" name="criterioPago" value="%{criterioPago}"/>

	<s:if test="%{idPago!=null}">
		<div class="clear"></div>
		<br>
		<div class="pdf">
			<a href="<s:url value="/solicitudPago/consigueAtentaNota?idPago=%{idPago}"/>" title="Descargar Atenta Nota" ></a>
		</div>
		<br>
		<br>
	</s:if>
	
	<div class="borderBottom"><h1>CONSULTA DE PAGOS DE LA CARTA DE ADHESIÓN</h1></div><br>

	<div id="dialogo_1"></div>
	<fieldset>
		<div>
			<label class="left1">Carta de Adhesión: </label>
			<font class="arial14boldVerde"><s:property value="folioCartaAdhesion"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Estatus: </label>
			<font class="arial14bold"><s:property value="estatusCA"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Participante: </label>
			<font class="arial14bold"><s:property value="nombreComprador"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Programa de Apoyos: </label>
			<font class="arial14bold"><s:property value="nombrePrograma"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Criterio de Pago: </label>
			<font class="arial14bold"><s:property value="descCriterioPago"/></font>		
		</div>
		<div class="clear"></div>	
		<div>
			<label class="left1">Incluye Fianza: </label>
			<font class="arial14bold"><s:property value="fianza"/></font>
		</div>
		<div class="clear"></div>	
		<div>
			<label class="left1">Cuenta Bancaria (CLABE):</label>
			<font class="arial14bold"><s:property value="clabe"/></font>
		</div>	
		<div class="clear"></div>
		<div>
			<label class="left1">Banco:</label>
			<font class="arial14bold"><s:property value="nombreBanco"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Número de Cuenta:</label>
			<font class="arial14bold"><s:property value="ctaBancaria"/></font>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Plaza:</label>
			<font class="arial14bold"><s:property value="plaza.numeroPlaza"/> - <s:property value="plaza.nombrePlaza"/></font>
<!-- 			<s:textfield  disabled="true" maxlength="100" size="30"  value="%{plaza.numeroPlaza}"/>  -->
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Sucursal:</label>
			<font class="arial14bold"><s:property value="sucursal"/></font>
		</div>
		<div class="clear"></div>
	</fieldset>
	<fieldset>
		<legend>Detalle de Pagos</legend>
		<s:if test="lstConsultaPagosCAEspecialista.size()>0">		
			<table class="clean">
				<tr>
					<th class="clean">Fecha Registro</th>
					<th class="clean">Estado</th>
					<th class="clean">Cultivo</th>
					<s:if test="criterioPago==1 || criterioPago==3">
						<th class="clean">Volumen Apoyado</th>
						<th class="clean">Importe Apoyado</th>
						<th class="clean">Cuota Apoyo</th>
						<s:if test="criterioPago==3">
							<th class="clean">Etapa</th>
						</s:if>
					</s:if>
					<s:else>
						<th class="clean">Importe Apoyado</th>
						<th class="clean">Etapa</th>
					</s:else>
					<th class="clean">Estatus</th>
					<th class="clean">Atenta Nota</th>
					<th class="clean">Accion</th>
				</tr>	
				<s:iterator value="lstConsultaPagosCAEspecialista" var="resultado" status="itStatus">
					<tr>
						<td>
							<s:textfield disabled="true" maxlength="15" size="15" value="%{getText('fecha',{#resultado.fechaCreacion})}" />
						</td>
						<td>
							<s:textfield disabled="true" maxlength="50" size="20" value="%{#resultado.estado}"/>
						</td>
						<td>
							<s:textfield disabled="true" maxlength="50" size="20" value="%{#resultado.cultivo}"/>
						</td>
						<s:if test="idCriterioPago==1 || idCriterioPago==3">
							<td align="CENTER">
								<s:textfield disabled="true" id="volumenApo%{#itStatus.count}" name="volumenesApo" maxlength="20" size="15" cssClass="cantidad" value="%{getText('volumen',{#resultado.volumen})}"/>
							</td>
							<td align="CENTER">
								$<s:textfield disabled="true" id="importeApo%{#itStatus.count}" name="ImportesApo" maxlength="20" size="15" cssClass="cantidad" value="%{getText('importe',{#resultado.importe})}"/>
							</td>
							<td align="CENTER">
								$<s:textfield disabled="true" id="cuotaApoyo%{#itStatus.count}" name="cuotasApoyo" maxlength="15" size="10" cssClass="cantidad" value="%{getText('importe',{(#resultado.importe/#resultado.volumen)})}"/>
							</td>
							<s:if test="idCriterioPago==3">
								<td align="CENTER">
									<s:textfield disabled="true" maxlength="5" size="5" value="%{#resultado.etapa}"/>
								</td>
							</s:if>
						</s:if>
						<s:else>
							<td align="CENTER">
								$<s:textfield disabled="true" id="importeApo%{#itStatus.count}" name="ImportesApo" maxlength="20" size="15" cssClass="cantidad" value="%{getText('importe',{#resultado.importe})}"/>
							</td>
							<td align="CENTER">
								<s:textfield disabled="true" maxlength="5" size="5" value="%{#resultado.etapa}"/>
							</td>
						</s:else>
						<td align="CENTER">
							<s:textfield disabled="true" maxlength="20" size="20" value="%{#resultado.estatusPago}"/>
						</td>
						<td>
							<a href="<s:url value="/solicitudPago/consigueAtentaNota?idPago=%{#resultado.idPago}"/>" title="Descargar" class="pdf"></a>
						</td>
						<s:if test="%{#resultado.estatusPago=='REGISTRADO'}">
							<td>
								<a href='<s:url value="/solicitudPago/detalleEditarPagosCartaAdhesion?idPago=%{#resultado.idPago}&folioCartaAdhesion=%{#resultado.noCarta}"/>' class="" title="">Editar</a>
							</td>
						</s:if>
					</tr>	
				</s:iterator>
			</table>
		</s:if>
		<s:else><div class="advertencia">No existen pagos capturados para la carta de adhesión</div></s:else>
	</fieldset>
	<div class="accion">
		<a href='<s:url value="/solicitudPago/verCartaAdhesionAsignadasPagos?idPrograma=%{idPrograma}"/>' class="boton" title="">Regresar</a>
	</div>
</s:form>