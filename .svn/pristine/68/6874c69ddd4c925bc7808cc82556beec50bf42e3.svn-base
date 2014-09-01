<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/cuentasBancarias.js" />"></script>

<div class="borderBottom"><h1>CUENTAS BANCARIAS</h1></div><br>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div id="dialogo_1"></div>

<s:form action="registraCuentaBancaria" method="post" onsubmit="return chkCamposCuentasBancarias();">
	<s:hidden id="folioCartaAdhesion" name="folioCartaAdhesion" value="%{folioCartaAdhesion}"/>
	<fieldset>
		<legend>Cuenta Bancaria</legend>
		<div>
			<label class="left1"><span class="norequerido">*</span>Folio Carta Adhesi&oacute;n:</label>
			<font class="arial14boldVerde"><s:property value="%{folioCartaAdhesion}"/></font>
		</div>
		<div class="clear"></div>	
		<s:hidden id="idComprador" name="idComprador" value="%{idComprador}"/>
		<div>
			<label class="left1"><span class="norequerido">*</span>Participante:</label>
			<font class="arial12bold"><s:property value="%{comprador.nombre}"/></font>
		</div>
		<div class="clear"></div>
		<s:if test="registrar==0">
			<s:if test="lstCuentaBancariasV.size() > 0">
				<s:hidden id="tieneCuenta" name="tieneCuenta" value="%{1}"/> 
				<div>
					<label class="left1"><span class="norequerido">*</span>CLABE Bancarias Relacionadas:</label>
					<s:if test="registrar==0">
						<s:select id="clabe" name="clabe" list="lstCuentaBancariasV" listKey="clabe" listValue="%{clabe}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="recuperaDatosPlaza()" value="%{clabe}" />
					</s:if>
					<s:else>
						<font class="arial12bold"><s:property value="%{clabe}"/></font>
					</s:else>
				</div>
				<div class="clear"></div>
				<div id="recuperaDatosPlaza"></div>
				<div class="clear"></div>
				<s:if test="registrar==0">
					<div class="inline">
						<label class="left1"><span class="norequerido">*</span>Desea Capturar Otra Cuenta:</label>
						<s:radio label="" onclick="capturarOtraCuenta()"  name="otrac" list="#{1:'SI',0:'NO'}" value="%{0}" />
					</div>
				</s:if>
				<div class="clear"></div>
				<!-- Se activa cuando el usuario selecciona otra cuenta -->
				<div id="otraCuenta"></div>
			</s:if>
			<s:else>
				<s:hidden id="tieneCuenta" name="tieneCuenta" value="%{0}"/>
				<s:include value="/WEB-INF/paginas/solicitudPago/documentacionSolPago/estadoCuenta/otraCuenta.jsp"/>
			</s:else>	
		</s:if>
		<s:else>
			<div>
				<label class="left1"><span class="norequerido">*</span>CLABE Bancaria:</label>
				<font class="arial12bold"><s:property value="%{clabe}"/></font>
			</div>
			<div class="clear"></div>
			<s:include value="/WEB-INF/paginas/solicitudPago/documentacionSolPago/estadoCuenta/datosPlazaBancaria.jsp"/>
		</s:else>		
	</fieldset>
	<div class="clear"></div>
	<s:if test="registrar==0">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" class="boton" title="">Cancelar</a>
		</div>
	</s:if>	
	<div class="clear"></div>
<s:if test="lstCuentaBancariasV.size()>0">
	<s:include value="/WEB-INF/paginas/solicitudPago/documentacionSolPago/estadoCuenta/listarCuentasBancarias.jsp"/>
</s:if>
<s:else></s:else>
<div class="clear"></div>
<div class="izquierda"><a href="<s:url value="/solicitudPago/selecAccionDocumentacion?folioCartaAdhesion=%{folioCartaAdhesion}"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>


