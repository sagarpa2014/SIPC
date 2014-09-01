<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="<s:url value="/js/pagos.js" />"></script>
<s:if test="cuadroSatisfactorio!=null && cuadroSatisfactorio !=''">
	<s:include value="/WEB-INF/paginas/template/abrirMensajeDialogoCorrecto.jsp" />
</s:if>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>DEVOLUCION DE PAGOS</h1></div><br>
<s:form action="consultaPagosCADevoluciones" id="" onsubmit="return chkCamposDevolucion();">
	<fieldset id="reporte1" class="clear">
		<legend>Criterios de B&uacute;squeda</legend>
		<table class="clean"style="width: 80%">
			<tr>
	  			<td >
	  				<label class="">Carta de Adhesi&oacute;n:</label>
	  			</td>
	  			<td >
	  				<s:textfield id="cartaAdhesion"  name="cartaAdhesion" maxlength="30" size="30"  value="%{}"/>
	  			</td>
			</tr>
		</table>
		
		<div class="accion">	    	
			<s:submit  value="Consultar" cssClass="boton2"/>
		</div>
	</fieldset>
	<br>
	<s:if test="lstConsultaPagosCA.size() > 0">
		<fieldset id="reporte2">
			<legend>Resultado de B&uacute;squeda</legend>	
			<display:table id="r"  name="lstConsultaPagosCA"  list="lstConsultaPagosCA"  pagesize="50" sort="list" requestURI="/pagos/consultaPagosCADevoluciones"  class="displaytag">
				<display:column  property="noCarta" title="Folio Carta Adhesión"/>
				<display:column  property="nombreComprador" title="Comprador"/>
				<display:column  property="oficioPagos" title="No. Oficio DGAF"/>
				<display:column  property="clc" title="No. CLC"/>
				<display:column  property="clabe" title="CLABE"/>
				<display:column  property="claveRastreo" title="Clave Rastreo"/>
				<display:column  property="volumen" title="Volumen (tons.)"  format="{0, number,0.000}" total="true" class="d"/>
				<display:column  property="importe" title="Importe ($)"  format="{0, number,currency}" total="true" class="d"/>				
			 	<display:column title="Devoluci&oacute;n" headerClass="sortable" >
					<a href='<s:url value="/pagos/consultaDevolucionesPagosCA?idPago=%{#attr.r.idPago}&cartaAdhesion=%{cartaAdhesion}"/>' class="botonDev" onclick=""></a>
			 	</display:column>
			</display:table>
		</fieldset>
	</s:if>
	<s:else>
		<div class="advertencia">No se encontraron pagos registrados para la Carta de Adhesi&oacute;n</div>
	</s:else>
</s:form>

 <script type="text/javascript">
	 function chkCamposDevolucion(){
		 var cartaAdhesion = $('#cartaAdhesion').val();	
		 if(cartaAdhesion == null || cartaAdhesion == ""){
				$('#dialogo_1').html('Capture el folio de la carta de adhesión');
				abrirDialogo();
				return false;
			}
	 }
 </script>