<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<div id="ok">
	<s:form action="capturaPagoCartaAdhesion" method="post" enctype="multipart/form-data" accept-charset="utf-8" onsubmit="return chkCamposPagoCartaAdhesion();">
		<s:hidden id="numCampos" name="numCampos" value="%{numCampos}"/>
		<s:hidden id="criterioPago" name="criterioPago" value="%{criterioPago}"/>
		<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
		<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
		<s:hidden id="idPerfil" name="idPerfil" value="%{#session.idPerfil}"/>
		<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
		<s:hidden id="idPago" name="idPago" value="%{idPago}"/>
	
		<s:if test="%{idPago!=null}">
			<div class="clear"></div>
			<br>
			<div class="pdf">
				<a href="<s:url value="/solicitudPago/consigueAtentaNota?idPago=%{idPago}"/>" title="Descargar Atenta Nota" ></a>
			</div>
			<br>
			<br>
		</s:if>
		
		<div class="borderBottom"><h1>CAPTURA DE PAGOS DE LA CARTA DE ADHESIÓN</h1></div><br>
	
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
			<legend>Captura de Pagos</legend>
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
		            <label class="left1"><span class="norequerido">*</span>Fianza</label>
		            <s:checkbox id="tieneFianza"  name="tieneFianza" fieldValue="true" value="%{fianza}" onclick="habilitaPorcentaje();" />
		        </div>
		        <div class="izquierdaOculta">
		         	<label class="left1"><span class="norequerido">*</span>Porcentaje %</label>
		            <s:textfield  id="porcentajeFianza" name="porcentajeFianza" maxlength="5" size="3"  value="%{}" onchange="setVolumenByProcentaje();"/>
		        </div>
			</s:if>
			
			<div class="clear"></div>
			<div>
         		<label class="left1"><span class="requerido">*</span>Solicitudes Atendidas:</label>
            	<s:textfield id="solicitudesAtendidas"  name = "solicitudesAtendidas" maxlength="5" size="10"  value="%{solicitudesAtendidas}"/>
         	</div>			
			<div class="clear"></div>
			<div>
        		<label class="left1"><span class="requerido">*</span>Productores Beneficiados:</label>
           		<s:textfield id="productoresBeneficiados"  name = "productoresBeneficiados" maxlength="5" size="10"  value="%{productoresBeneficiados}"/>
        	</div>
        	<div class="clear" ></div>
        	<div id="contenedorDetallePagos" align="center">
        		<s:include value="respuestaContenedorDetallePagos.jsp"/>
        	</div>		                           
		</fieldset>
		<div class="accion">
			<s:submit  value="Vista Previa" cssClass="boton2" action="vistaPreviaAtentaNota" onclick="return chkCamposPagoCartaAdhesion();"/>
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href='<s:url value="/solicitudPago/verCartaAdhesionAsignadasPagos?idPrograma=%{idPrograma}"/>' class="boton" title="">Regresar</a>
		</div>
	</s:form>
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
</div>	
<div id="vistaPrevia" ></div>	