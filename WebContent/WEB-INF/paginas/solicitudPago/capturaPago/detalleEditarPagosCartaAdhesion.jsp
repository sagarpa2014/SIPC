<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:form action="editarPagoCartaAdhesion" method="post" enctype="multipart/form-data" accept-charset="utf-8" onsubmit="return chkCamposPagoCartaAdhesion();">
	<s:hidden id="numCampos" name="numCampos" value="%{numCampos}"/>
	<s:hidden id="criterioPago" name="criterioPago" value="%{criterioPago}"/>
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<s:hidden id="idPago" name="idPago" value="%{idPago}"/>
	<s:hidden id="idPerfil" name="idPerfil" value="%{#session.idPerfil}"/>
	

	<s:if test="%{idPago!=null}">
		<div class="clear"></div>
		<br>
		<div class="pdf">
			<label class="left1"><font class="arial14boldVerde">Atenta Nota: </font></label>
			<a href="<s:url value="/solicitudPago/consigueAtentaNota?idPago=%{idPago}"/>" title="Descargar Atenta Nota" ></a>
		</div>
		<br>
		<br>
	</s:if>
	
	<div class="borderBottom"><h1>EDICIÓN DE PAGOS DE LA CARTA DE ADHESIÓN</h1></div><br>

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
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1">Sucursal:</label>
			<font class="arial14bold"><s:property value="sucursal"/></font>
		</div>
		<div class="clear"></div>
	</fieldset>
	<fieldset>
		<legend>Edición de Pagos</legend>
		<s:if test="%{#session.idPerfil == 6}">
			<div class="izquierda">
	            <label class="left1"><span class="requerido">*</span>Fecha Atenta Nota:</label>
	            <s:if test="%{fechaAtentaNota==null}">
	            	<s:textfield name="fechaAtentaNota" maxlength="10" size="10" id="fechaAtentaNota" readonly="true" cssClass="dateBox" />
	            </s:if>
	            <s:else>
	                <s:textfield key="fechaAtentaNota" value="%{fechaAtentaNota}" name="fechaAtentaNota" maxlength="10" size="10" id="fechaAtentaNota" readonly="true" cssClass="dateBox" />
	            </s:else>
	            <img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			</div>
			<div class="clear"></div>	
			<s:if test="%{fianza!=null}">
				<div class="izquierda">
		            <label class="left1"><span class="requerido">*</span>Fianza</label>
		           	<s:checkbox id="tieneFianza"  name="tieneFianza" fieldValue="true" value="%{tieneFianza}"  onclick="habilitaPorcentaje();" />
	           	</div>
	           	<s:if test="%{tieneFianza==true}">
		           	<div class="izquierdaOculta">
			         	<label class="left1"><span class="norequerido">*</span>Porcentaje %</label>
			            <s:textfield  id="porcentajeFianza" name="porcentajeFianza" maxlength="5" size="3"  value="%{getText('importe',{porcentajeFianza})}" onchange="setVolumenByProcentaje();"/>
			     	</div>
		     	</s:if>
			</s:if>	
			<div class="clear"></div>
			<div>
	         	<label class="left1"><span class="requerido">*</span>Solicitudes Atendidas:</label>
	            <s:textfield id="solicitudesAtendidas"  name = "solicitudesAtendidas" maxlength="5" size="10"  value="%{solicitudesAtendidas}"/>
	         </div>
	    </s:if>
	    <s:elseif test="%{#session.idPerfil == 10}">
		   	<s:if test="criterioPago==1 || criterioPago==3">
		         <div>
		         	<label class="left1">Volumen Total del Pago Efectuado:</label>
		         	<s:textfield id="volumenPago"  maxlength="20" size="20" cssClass="cantidad" value="%{getText('volumen',{volumenPago})}" disabled="true"/>
		         </div>
	         </s:if>
         </s:elseif>			
		<div class="clear"></div>
		
		<s:if test="%{#session.idPerfil == 6}">
			<div class="izquierda">
	        	<label class="left1"><span class="requerido">*</span>Productores Beneficiados:</label>
	           	<s:textfield id="productoresBeneficiados"  name = "productoresBeneficiados" maxlength="5" size="10"  value="%{productoresBeneficiados}"/>
	        </div>
	     </s:if>
	     <s:elseif test="%{#session.idPerfil == 10}">	
	        <div>
	         	<label class="left1">Importe Total del Pago Efectuado:</label>
	         	<s:textfield id="importePago"  maxlength="20" size="20" cssClass="cantidad" value="%{getText('importe',{importePago})}" disabled="true"/>
	         </div>	        
         </s:elseif>
	   <s:if test="%{#session.idPerfil == 6}"> 
	        <script type="text/javascript">
			    <!--
				 Calendar.setup({
				                inputField     :    "fechaAtentaNota",     
				                ifFormat       :    "%d/%m/%Y",     
				                button         :    "trg1",  
				                align          :    "Br",           
				                singleClick    :    true
				 });                           
				 //-->
			</script>
			<s:if test="%{tieneFianza==true}">
				<script>
			    	$(document).ready(function() {    
			    		$(".izquierdaOculta").css("display", "inline");			   
			         });     		
			    </script>
			</s:if>		
        </s:if><!-- Si el perfil es 6 -->
        
		<table class="clean" >
			<tr>
				<th class="clean">Estado</th>
				<th class="clean">Cultivo</th>
				<th class="clean">Variedad</th>
				<th class="clean">Bodega</th>
				<s:if test="criterioPago==1 || criterioPago==3">
					<th class="clean">Volumen Autorizado</th>
					<th class="clean">Volumen Apoyado</th>
					<th class="clean">Cuota Apoyo</th>
					<th class="clean">Volumen a Apoyar</th>
					<s:if test="criterioPago==3">
						<th class="clean">Etapa</th>
					</s:if>
				</s:if>
				<s:else>
					<th class="clean">Importe Autorizado</th>
					<th class="clean">Importe Apoyado</th>
					<th class="clean">Importe a Apoyar</th>
					<th class="clean">Etapa</th>
				</s:else>
			</tr>	
			<s:hidden id="numRegistroDetallePago" value="%{lstEditDetallePagosCAEspecialistaV.size()}"/>
			<s:iterator value="lstEditDetallePagosCAEspecialistaV" var="resultado" status="itStatus">
				<tr>
					<td>
						<s:textfield disabled="true" maxlength="50" size="20" value="%{#resultado.estado}"/>
					</td>
					<td>
						<s:textfield disabled="true" maxlength="50" size="20" value="%{#resultado.cultivo}"/>
					</td>
					<td>
						<s:textfield disabled="true" maxlength="50" size="30" value="%{#resultado.variedad}"/>
					</td>
					<td>
						<s:textfield disabled="true" maxlength="50" size="30" value="%{#resultado.bodega}"/>
					</td>										
					<s:if test="idCriterioPago==1 || idCriterioPago==3">
						<td align="CENTER">
							<s:textfield disabled="true" id="volumenAut%{#itStatus.count}" name="volumenesAut" maxlength="20" size="20" cssClass="cantidad" value="%{getText('volumen',{#resultado.volumenAutorizado})}"/>
							<s:hidden id="volumenesAutTemp%{#itStatus.count}" value="%{#resultado.volumen}"/>
						</td>
						<td align="CENTER">
							<s:textfield disabled="true" id="volumenApo%{#itStatus.count}" name="volumenesApo" maxlength="20" size="20" cssClass="cantidad" value="%{getText('volumen',{#resultado.volumenApoyado})}"/>
						</td>
						<td align="CENTER">
							$<s:textfield disabled="true" id="cuotaApoyo%{#itStatus.count}" name="cuotasApoyo" maxlength="15" size="15" cssClass="cantidad" value="%{getText('importe',{#resultado.cuota})}"/>
						</td>
						<td>
							<s:if test="%{#resultado.volumen != null && #resultado.volumen != 0}">
								<s:textfield id="volumenApoyar%{#itStatus.count}" name="capVolumen[%{idPagoDetalle}]" maxlength="20" size="20"  cssClass="cantidad"  value="%{getText('volumenSinComas',{#resultado.volumen})}" />
							</s:if>
							<s:else>
								<s:textfield id="volumenApoyar%{#itStatus.count}" name="capVolumen[%{idPagoDetalle}]" maxlength="20" size="20"  cssClass="cantidad"  value="%{}" />
							</s:else>
						</td>
						<s:if test="idCriterioPago==3">
							<td class="cEtapa">
								<s:select id="etapa%{#itStatus.count}" name="capEtapa[%{idPagoDetalle}]"  headerKey="-1" onchange=""
									headerValue="Seleccione una opción"								
									list="#{'I':'I', 'II':'II', 'III':'III','IV':'IV'}" />
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td align="CENTER">
							$<s:textfield disabled="true" id="importeAut%{#itStatus.count}" name="ImportesAut" maxlength="20" size="20" cssClass="cantidad" value="%{getText('importe',{#resultado.importeAutorizado})}"/>
						</td>
						<td align="CENTER">
							$<s:textfield disabled="true" id="importeApo%{#itStatus.count}" name="ImportesApo" maxlength="20" size="20" cssClass="cantidad" value="%{getText('importe',{#resultado.importeApoyado})}"/>
						</td>
						<td>$<s:textfield id="importeApoyar%{#itStatus.count}" name="capImporte[%{idPagoDetalle}]" maxlength="20" size="20"  cssClass="cantidad" value="%{#resultado.importe}" /></td>
						<td class="cEtapa">
							<s:select id="etapa%{#itStatus.count}" name="capEtapa[%{idPagoDetalle}]"  headerKey="-1" onchange=""
								headerValue="Seleccione una opción"								
								list="#{'I':'I', 'II':'II', 'III':'III','IV':'IV'}" />
						</td>
					</s:else>						
				</tr>	
			</s:iterator>
		</table>
		
	</fieldset>
	<div class="accion">
		<s:submit  value="Guardar" cssClass="boton2" />
		<a href='<s:url value="/solicitudPago/verCartaAdhesionAsignadasPagos?idPrograma=%{idPrograma}"/>' class="boton" title="">Regresar</a>
	</div>
</s:form>
