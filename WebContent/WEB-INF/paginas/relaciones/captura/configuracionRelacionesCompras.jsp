<%@taglib uri="/struts-tags" prefix="s"%>

<s:form action="registraCapturaRelacion"  id="target" onsubmit="return chkCamposCapturaRelaciones();">  
	<s:hidden id="errorProductores" name="errorProductores" value="%{errorProductores}"/>
	<s:hidden id="errorClaveBodega" name="errorClaveBodega" value="%{errorClaveBodega}"/>
	<s:hidden id="errorClaveBodegaExiste" name="errorClaveBodegaExiste" value="%{errorClaveBodegaExiste}"/>
	<s:hidden id="errorNoExistenPredios" name="errorNoExistenPredios" value="%{errorNoExistenPredios}"/>
	<s:hidden id="validaApartado" name="validaApartado" value="%{0}"/>
	<s:hidden id="idCompPer" name="idCompPer" value="%{idCompPer}"/>
	<s:hidden id="folioSerial" name="folioSerial" value="%{folioSerial}"/>
	<s:hidden id="fechaActualL" name="fechaActualL" value="%{fechaActualL}"/>
	<!-- Encabezado de la relacion -->
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>	
	<s:hidden id="idRelacion" name="idRelacion" value="%{idRelacion}"/>
	<s:hidden id="idCompProd" name="idCompProd" value="%{idCompProd}"/>	
	<s:hidden id="idIniEsquemaRelacion" name="idIniEsquemaRelacion" value="%{idIniEsquemaRelacion}"/>
	<s:hidden id="idPerRel" name="idPerRel" value="%{idPerRel}"/>	
	<s:hidden id="tipoPersona" name="tipoPersona" value="%{tipoPersona}"/>
	<s:hidden id="estatusProductor" name="estatusProductor" value="%{estatusProductor}"/>
	 <script>
	 	$(function() {
			$("#tabs").tabs();
		});
	</script>
	<br>
	<div class="borderBottom" style="text-align:center"><h1>Datos del Participante</h1></div><br>
	<table style="width:100%;">
		<tr>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<s:set name= "folioCartaAdhesion1"  value="%{folioCartaAdhesion}"></s:set>
		<s:set name= "claveBodega1" value="%{claveBodega}"></s:set>
		<s:set name= "nomProductor1" value="%{nombreProductor}"></s:set>
		<s:set name= "paterno1" value="%{paterno}"></s:set>
		<s:set name= "materno1" value="%{materno}"></s:set>
		<s:set name= "rfc1" value="%{rfc}"></s:set>
		<s:set name= "curp1" value="%{curp}"></s:set>
		<s:set name= "domicilioBodega1" value="%{domicilioBodega}"></s:set>		
		<s:property value="%{nomProductor}"/>
		<s:iterator value="lstGruposCamposRelacionPartV" var="resultado"  status="itStatus">
			<tr>
				<td>
					<s:property value="%{campo}"/>
				</td>
				<s:if test="tipoDato == 'lstCompradores'">
					<td align="justify" >
						<s:textfield id="idComp" name="nombre" size="100"  value="%{nombre}" disabled="true"/>
					</td>
				</s:if>
				<s:elseif test="tipoDato == 'bodega'">
					<td align="justify">
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{#claveBodega1}" disabled="true"/>						
						</s:if>
						<s:else>
							<s:textfield cssClass="bodega" id="claveBodega" name="claveBodega" maxlength="15" size="15"  value="%{#claveBodega1}" onchange="validaClaveBodega();" />
						</s:else>						
					</td>					
				</s:elseif>
				<s:elseif test="tipoDato == 'lstEstados'">
					<td align="justify">		
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:select cssClass="estadoBodega" id="estadoBodega" name="estadoBodega" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{estadoBodega}" disabled="true"/>
						</s:if>
						<s:else>
							<s:select cssClass="estadoBodega" id="estadoBodega" name="estadoBodega" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{estadoBodega}" onchange="recuperaDatosProductor(1);"/>
						</s:else>
						
					</td>
				</s:elseif>
				<s:elseif test="tipoDato == 'folioCarta'">
					<s:if test="%{#folioCartaAdhesion1 ==null}">
						<td align="justify">
							<s:textfield cssClass="folioCarta" id="folioCarta" name="folioCartaAdhesion" maxlength="50" size="50"  value="No se ha registrado Carta de Adhesion" disabled="true"/>
						</td>
					</s:if>
					<s:else>
						<td align="justify">
							<s:textfield cssClass="folioCarta" id="folioCarta" name="folioCartaAdhesion" maxlength="50" size="50"  value= "%{#folioCartaAdhesion1}" disabled="true"/>
						</td>
					</s:else>
				</s:elseif>
				<s:elseif test="tipoDato == 'lstCultivo'">
					<td align="justify">
						<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
							<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}"  disabled="true"/>
						</s:if>
						<s:else>
							<s:select cssClass="lstCultivo" id="cultivo" name="cultivoRelacion" list="lstCultivoPerRel" listKey="idPgrRelCultivo" listValue="%{nombreCultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{cultivoRelacion}" onchange="validaClaveBodega();"/>
						</s:else>
					</td>
				</s:elseif>
			</tr>
			<s:if test="tipoDato == 'bodega'">
				<tr>
					<td>DOMICILIO DE LA BODEGA</td>
					<td>
						<div id="validaClaveBodega"></div>
						<s:if test="%{errorClaveBodega == 0}">							
							<s:textfield cssClass="caracter" id="domicilioBodega" name="domicilioBodega"  maxlength="150" size="50"  value="%{#domicilioBodega1}" disabled="true"/>
						</s:if>
					</td>
				</tr>			
			</s:if>
			
		</s:iterator>
		<tr>
			<td>
				CICLO AGRICOLA DEL REGISTRO:
			</td>
			<td>
				<s:if test="#claveBodega1 != null && #claveBodega1 != ''">
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass"  disabled="true"/>
				</s:if>
				<s:else>
					<s:select id="idPgrCiclo" name="idPgrCiclo" list="lstCicloPerRel" listKey="idPgrCiclo" listValue="%{cicloLargo+' '+ejercicio}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{idPgrCiclo}" cssClass="cicloClass"  onchange="validaClaveBodega();"/>
				</s:else>
				
			</td>			
		</tr>
	</table>
	<br>
	<div class="borderBottom" style="text-align:center"><h1>Datos del Productor</h1></div><br>
	<table style="width:100%;">
		<tr>
			<th>Campos</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<s:iterator value="lstGruposCamposRelacionProdV" var="resultado"  status="itStatus">
			<tr>
			 	<s:if test = "%{idCampo == 7}">
			 		<td>
						<s:property value="%{campo}"/>
					</td>
					<s:if test="tipoDato == 'folio'">
						<td align="justify" >
							<s:textfield id="folioProductor" name="folioProductor" maxlength="20" size="20"  value="%{folioProductor}" onchange="recuperaDatosProductor(2);"/>
							<s:hidden id="folioProductorAnt" name="" value="%{folioProductor}"/>
							<div id="recuperaDatosProductor"></div>
						</td>
					</s:if>
				</s:if>
				<s:if test = "%{idCampo == 8}">
					<td>
						<s:property value="%{campo}"/>
					</td>
					<td align="justify">
						<s:textfield id="nomProductor" name="nombreProductor"  maxlength="150" size="100"  value="%{#nomProductor1}" disabled="true"/>
					</td>
				</s:if>
				<s:if test = "%{idCampo == 9}">
					<td>
						<s:property value="%{campo}"/>
					</td>
					<td align="justify">
						<s:textfield id="pat" name="paterno"  maxlength="150" size="50"  value="%{#paterno1}" disabled="true"/>
					</td>
				</s:if>
				<s:if test = "%{idCampo == 10}">
					<td>
						<s:property value="%{campo}"/>
					</td>
					<td align="justify">
						<s:textfield id="mat" name="materno"  maxlength="150" size="50"  value="%{#materno1}" disabled="true"/>
					</td>
				</s:if>
				<s:if test = "%{idCampo == 17}">
					<td>
						<s:property value="%{campo}"/>
					</td>
					<td align="justify">
						<s:textfield id="rfcR" name="rfc"  maxlength="150" size="50"  value="%{#rfc1}" onchange="validaRfcProductor(this.value);" disabled ="true"/>
					</td>
				</s:if>
				<s:if test = '%{idCampo == 69  && tipoPersona=="F"}'>
					<td>
						<s:property value="%{campo}"/>
					</td>
					<td align="justify">
						<s:textfield id="curpR" name="curp"  maxlength="150" size="50"  value="%{#curp1}" disabled ="true"/>
					</td>
				</s:if>
			</tr>	
		</s:iterator>
		<tr>
			<td>N&Uacute;MERO DE PRODUCTORES A APOYAR</td>
			<td><s:textfield id="numProdBen" name="numProdBen" maxlength="15" size="20"  value="%{numProdBen}" /></td>
		</tr>		
	</table>
	<div class="clear"></div>
	<br>
	<script>
	 $(function() {
			$("#tabs").tabs();
		});
	</script>	
	<div id="tabs">
		<ul>
			<s:if test="%{lstGruposCamposRelacionPrediosV.get(0).idCampo == 6}">
				<li><a href="#tabs-1">Predios del Productor</a></li>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionBodV.get(0).getIdGrupo() == 7}">
				<li><a href="#tabs-2">Entrada(s) a la Bodega</a></li>		
			</s:if>
			<s:if test="%{lstGruposCamposRelacionVentaNumeroV.get(0).getIdGrupo() == 9}">
				<li><a href="#tabs-3">Factura de Venta del Grano</a></li>
			</s:if>
			<s:if test="%{lstGruposCamposRelacionPagoSinAXC.get(0).getIdGrupo() == 12}">
				 <li><a href="#tabs-4">Pago al Productor sin AXC</a></li>
			</s:if>  
		</ul>	
		<s:if test="%{lstGruposCamposRelacionPrediosV.get(0).idCampo == 6}">
			<div id="tabs-1"><!-- Predios del Productor  -->					
								
				<div id="foliosPredios">
					<s:if test="registrar == 1 || registrar == 2">
						<s:include value="foliosPredios.jsp"></s:include>
					</s:if>
				</div>
				<div class="clear"></div>
			</div> <!-- End tabs-1 Predios del Productor -->
		</s:if>
		<s:if test="%{lstGruposCamposRelacionBodV.get(0).getIdGrupo() == 7}">
			<div id="tabs-2"><!-- Inicio Entrada(s) a la Bodega-->
				<s:if test="registrar != 1">	
					<table class="clean" >
						<tr>
							<td><span  id="agregarEntradaBodega" class="tipoBoton" onclick="agregarEntradaBodega();">Agregar Registro</span></td>
							<td><span id="removerEntradaBodega" class="tipoBoton" onclick="removerEntradaBodega();">Remover Registro</span></td>
							<s:if test="estatusProductor != 2">
								<td><span id="guardarEntradaBodega" class="tipoBoton" onclick="setValidarCamposByApartado(7);">Guardar</span></td>
							</s:if>							
						</tr>
					</table>
				</s:if>	
				<br>							
				<s:hidden id="numBolTicket" name="numBolTicket" value="%{numBolTicket}"/>
				<div class="clear"></div>
				<div id="bolTicket">
				<s:if test="registrar == 1 || registrar == 2">
					<s:include value="bolTicket.jsp"></s:include>
				</s:if>
				</div>
				<div class="clear"></div>
			</div><!-- End tabs-2  Entrada(s) a la Bodega-->	
		</s:if>	
		<s:if test="%{lstGruposCamposRelacionVentaNumeroV.get(0).getIdGrupo() == 9}">
			<div id="tabs-3"><!-- Factura de Venta del Grano -->
				<s:if test="registrar != 1">	
					<table class="clean" >
						<tr>
							<td><span  id="agregarFacturaVenta" class="tipoBoton" onclick="agregarFacturaVenta();">Agregar Registro</span></td>
							<td><span id="removerFacturaVenta" class="tipoBoton" onclick="removerFacturaVenta();">Remover Registro</span></td>
							<s:if test="estatusProductor != 2">
								<td><span id="guardarFacturaVenta" class="tipoBoton" onclick="setValidarCamposByApartado(9);">Guardar</span></td>
							</s:if>
						</tr>
					</table>
				</s:if>
				<br>	
				<s:hidden id="numFacVenta" name="numFacVenta" value="%{numFacVenta}"/>
				<div class="clear"></div>
				<div id="facturaVenta">
				<s:if test="registrar == 1 || registrar == 2">
					<s:include value="facturaVenta.jsp"></s:include>
				</s:if>
				</div>
				<div class="clear"></div>
				<br>	
			</div><!-- End tabs-3 Factura de Venta del Grano -->		
		</s:if>
		<s:if test="%{lstGruposCamposRelacionPagoSinAXC.get(0).getIdGrupo() == 12}">
			<div id="tabs-4"> <!-- Pago al Productor sin AXC -->
				<s:if test="registrar != 1">	
					<table class="clean" >
						<tr>
							<td><span  id="agregarPagosSinAXC" class="tipoBoton" onclick="agregarPagosSinAXC();">Agregar Registro</span></td>
							<td><span id="removerPagosSinAXC" class="tipoBoton" onclick="removerPagosSinAXC();">Remover Registro</span></td>
							<s:if test="estatusProductor != 2">
								<td><span id="guardarPagosSinAXC" class="tipoBoton" onclick="setValidarCamposByApartado(12);">Guardar</span></td>
							</s:if>
						</tr>
					</table>
				</s:if>
				<br>
				<s:hidden id="numPagosSinAXC" name="numPagosSinAXC" value="%{numPagosSinAXC}"/>
				<div class="clear"></div>	
				<div id="pagosSinAXC">
					<s:if test="registrar == 1 || registrar == 2">
						<s:include value="pagosSinAXC.jsp"></s:include>
					</s:if>
				</div>
				<div class="clear"></div>
				<br>
			</div><!--End tabs-4 Pago al Productor sin AXC -->
		</s:if>
	</div>
	<s:if test="registrar == 1 && #folioCartaAdhesion1 == null">
		<div class="derecha">
			<s:if test="idCompPer != 0">
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&claveBodega=%{claveBodega}&estadoBodega=%{estadoBodega}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:if>
			<s:else>
				<a href='<s:url value="/relaciones/capturarRelaciones?registrar=0&idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>'  title="Registrar" >[Agregar Registro]</a>
			</s:else>		
		</div>
	</s:if>	
	<div class="clear"></div>
	<div class="accion">
		<s:if test="registrar == 0 || registrar == 2 || registrar == 3">
			<s:submit  value="Captura Completa" cssClass="boton2" onclick="setValidarCamposByApartado(100);"/>
			<s:if test="claveBodega != null && claveBodega !='' ">
				<a href="<s:url value="/relaciones/lstProductorRelCompras?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}"/>" class="boton" title="" >Cancelar</a>
			</s:if>
			<s:else>
				<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="boton" title="" >Cancelar</a>
			</s:else>
		</s:if>
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<s:if test="claveBodega != null && claveBodega !='' ">
			<a href="<s:url value="/relaciones/lstProductorRelCompras?idIniEsquemaRelacion=%{idIniEsquemaRelacion}&idCompPer=%{idCompPer}&idPerRel=%{idPerRel}&claveBodega=%{claveBodega}&estadoBodega=%{estadoBodega}"/>" class="" title="" >&lt;&lt; Regresar</a>	
		</s:if>
		<s:else>
			<a href="<s:url value="/relaciones/listRelacionesCapturadas?idPerRel=%{idPerRel}&idIniEsquemaRelacion=%{idIniEsquemaRelacion}"/>" class="" title="" >&lt;&lt; Regresar</a>
		</s:else>
	</div>
	<s:if test="registrar==1">
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');
			});	 	
		</script>
	</s:if>	
		
<s:if test="registrar != 1">	
	<script>
		$(document).ready(function(){
			if($('#numBolTicket').length){
				var numBolTicket = $('#numBolTicket').val();
				if(numBolTicket == 0){
					$("#removerEntradaBodega").fadeOut('slow');
					$("#guardarEntradaBodega").fadeOut('slow');
				}else{
					$("#removerEntradaBodega").fadeIn('slow');
					$("#guardarEntradaBodega").fadeIn('slow');
				}
			}
			
			if($('#numFacVenta').length){
				var numFacVenta = $('#numFacVenta').val();
				if(numFacVenta == 0){
					$("#removerFacturaVenta").fadeOut('slow');
					$("#guardarFacturaVenta").fadeOut('slow');
				}else{
					$("#removerFacturaVenta").fadeIn('slow');
					$("#guardarFacturaVenta").fadeIn('slow');
				}
			}
			
			if($('#numPagosSinAXC').length){
				var numPagosSinAXC = $('#numPagosSinAXC').val();
				if(numPagosSinAXC == 0){
					$("#removerPagosSinAXC").fadeOut('slow');
					$("#guardarPagosSinAXC").fadeOut('slow');
				}else{
					$("#removerPagosSinAXC").fadeIn('slow');
					$("#guardarPagosSinAXC").fadeIn('slow');
				}
			}
		});		
	</script>
</s:if>	
<s:if test="registrar == 2">	
	<script>
		$(document).ready(function(){
			$("#folioProductor").attr('disabled','disabled');
		});		
	</script>
	<s:if test='tipoPersona=="F"'>
		<script>
		$(document).ready(function(){
			$("#numProdBen").attr('disabled','disabled');
		});		
	</script>
	</s:if>
</s:if>	

</s:form>