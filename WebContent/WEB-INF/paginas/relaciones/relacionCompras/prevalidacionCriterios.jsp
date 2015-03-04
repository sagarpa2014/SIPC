<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<fieldset>
	<legend>Observaciones</legend>
	<s:if test="%{lstPrediosNoExistenBD.size() > 0}"><!-- Predios que no existen en base de datos -->
		<span class="requerido">LOS PREDIOS NO SE ENCUENTRAN EN LA BASE DE DATOS ASERCA</span><br>
	</s:if>
	<s:if test="%{lstPrediosNoValidadosDRDE.size() > 0}"> <!--Predios no validados por la DR/DE -->
		<span class="requerido">LOS PREDIOS NO SE ENCUENTRAN VALIDADOS POR LA DR/DE</span><br>
	</s:if>
	<s:if test="%{lstPrediosNoPagados.size() > 0}"> <!--Predios no pagados -->
		<span class="requerido">LOS PREDIOS NO SE ENCUENTRAN PAGADOS</span><br>
	</s:if>
	<s:if test="%{lstPrediosProdsContNoExistenBD.size() > 0}"><!-- Predios/Productores/Contratos que no existen en base de datos -->
		<span class="requerido">LOS PREDIOS/PRODUCTORES/CONTRATOS NO SE ENCUENTRAN EN LA BASE DE DATOS ASERCA</span><br>
	</s:if>	
	<s:if test="%{lstProductorExisteBD.size() > 0}"> <!--Productor exista en base de datos -->
		<span class="requerido">LOS PRODUCTORES NO SE ENCONTRARON EN LA BASE DE DATOS DE ASERCA</span><br>
	</s:if>
	<s:if test="%{lstProductorPredioValidado.size() > 0}"> <!--Productor asociado a predio validado  -->
		<span class="requerido">LOS PRODUCTORES NO SE ENCUENTRAN ASOCIADOS A UN PREDIO VALIDADO</span><br>
	</s:if>
	<s:if test="%{lstProductorPredioPagado.size() > 0}"> <!--Productor asociado a predio pagado  -->
		<span class="requerido">LOS PRODUCTORES NO SE ENCUENTRAN ASOCIADOS A UN PREDIO PAGADO</span><br>
	</s:if>
	<s:if test="%{lstProductoresIncosistentes.size() > 0}"> <!--Productores incosistentes  -->
		<span class="requerido">FALTA DE INFORMACIÓN DE AL MENOS UN REGISTRO EN BOLETA, FACTURA, PREDIO Y PAGO</span><br>
	</s:if>
	<s:if test="%{lstBoletasCamposRequeridos.size() > 0}"> <!-- Boletas con campos requeridos  -->
		<span class="requerido">EXISTEN VALORES NULOS EN LOS CAMPOS REQUERIDOS DE LAS BOLETAS</span><br>
	</s:if>
	<s:if test="%{lstFacturasCamposRequeridos.size() > 0}"> <!-- Facturas con campos requeridos  -->
		<span class="requerido">EXISTEN VALORES NULOS EN LOS CAMPOS REQUERIDOS DE LAS FACTURAS</span><br>
	</s:if>
	<s:if test="%{lstPagosCamposRequeridos.size() > 0}"> <!-- Pagos con campos requeridos  -->
		<span class="requerido">EXISTEN VALORES NULOS EN LOS CAMPOS REQUERIDOS DE LOS PAGOS</span><br>
	</s:if>		
	<s:if test="%{lstBoletasDuplicadas.size() > 0}"> <!-- Boletas no esten duplicadas por bodega -->
		<span class="requerido">BOLETAS O TICKETS DE BASCULA DUPLICADOS POR BODEGA</span><br>
	</s:if>	
	<s:if test="%{lstProductorFactura.size() > 0}"> <!--No se duplique el folio de la factura -->
		<span class="requerido">LAS FACTURAS SE ENCUENTRAN DUPLICADAS</span><br>
	</s:if>
	<s:if test="%{lstBoletasVsFacturas.size() > 0}"> <!-- La suma total de las Boletas sea mayor al volumen facturado -->
		<span class="requerido">LAS FACTURAS SON MAYORES AL VOLUMEN DE LAS BOLETAS</span><br>
	</s:if>
	<s:if test="%{lstBoletasFueraDePeriodo.size() > 0}"> <!--Boletas fuera del periodo de acopio  idCriterio 9 -->
		<span class="requerido">BOLETAS FUERA DE PERIODO DE ACOPIO <s:text name="fecha1"><s:param value="%{fechaInicio}"/></s:text>-<s:text name="fecha1"><s:param value="%{fechaFin}"/></s:text></span><br>	
	</s:if>
	<s:if test="%{lstBoletasFueraDePeriodoPago.size() > 0}"> <!--Boletas fuera del periodo de pago (Se valida contra folio de contrato) idCriterio 9 -->
		<span class="requerido">BOLETAS FUERA DE PERIODO DEL CONTRATO</span><br>	
	</s:if>
	<s:if test="%{lstRfcProductorVsRfcFactura.size() > 0}"> <!--Que el rfc factura corresponda al rfc productor -->
		<span class="requerido">LOS RFC DE LAS FACTURAS NO CORRESPONDEN AL PRODUCTOR</span><br>
	</s:if>
	<s:if test="%{lstFacturaFueraPeriodo.size() > 0}"> <!--Factura fuera del periodo del Auditor -->
		<span class="requerido">LAS FACTURAS SE ENCUENTRAN FUERA DEL PERIODO <s:text name="fecha1"><s:param value="%{fechaInicio}"/></s:text>-<s:text name="fecha1"><s:param value="%{fechaFin}"/></s:text></span><br>
	</s:if>
	<s:if test="%{lstFacturaFueraPeriodoPagoAdendum.size() > 0}"> <!--Factura fuera del periodo del adendum -->
		<span class="requerido">LAS FACTURAS SE ENCUENTRAN FUERA DEL PERIODO DE PAGO <s:text name="fecha1"><s:param value="%{fechaInicio}"/>-</s:text>-<s:text name="fecha1"><s:param value="%{fechaFin}"/></s:text></span><br>
	</s:if>
	<s:if test="%{lstFacturaFueraPeriodoPago.size() > 0}"> <!--Factura fuera del periodo del pago-->
		<span class="requerido">LAS FACTURAS SE ENCUENTRAN FUERA DEL PERIODO DE PAGO</span><br>
	</s:if>
	<s:if test="%{lstChequeFueraPeriodoAuditor.size() > 0}"> <!--Cheques fuera del periodo del Auditor (solo aplica AXC) -->
		<span class="requerido">LOS CHEQUES SE ENCUENTRAN FUERA DEL PERIODO DEL AUDITOR <s:text name="fecha1"><s:param value="%{fechaInicio}"/></s:text>-<s:text name="fecha1"><s:param value="%{fechaFin}"/></s:text></span><br>
	</s:if>
	<s:if test="%{lstChequeFueraPeriodoContrato.size() > 0}"> <!--Cheques fuera del periodo de pago (solo aplica AXC)"-->
		<span class="requerido">LOS CHEQUES SE ENCUENTRAN FUERA DEL PERIODO DE PAGO</span><br>
	</s:if>
	<s:if test="%{lstChequesDuplicadoBancoPartipante.size() > 0}"> <!--No se repitan cheques-banco por empresa"-->
		<span class="requerido">LOS CHEQUES SE ENCUENTRAN DUPLICADOS PARA EL BANCO EMPRESA</span><br>
	</s:if>
	<s:if test="%{lstFacturasVsPago.size() > 0}"> <!--No se repitan cheques-banco por empresa"-->
		<span class="requerido">MONTOS PAGADOS MENORES A LAS FACTURAS EMITIDAS</span><br>
	</s:if>
	<s:if test="%{lstPrecioPagadoProductor.size() > 0}"> <!--Precio pagado al productor deberá ser igual al tipo de cambio de la  fecha  de la factura"-->
		<span class="requerido">PRECIO MENOR ENTRE PRECIO FACTURA Y PRECIO PACTADO EN CONTRATO X TIPO DE CAMBIO</span><br>
	</s:if>
	<s:if test="%{lstFacturasIgualesFacAserca.size() > 0}"> <!-- Validar que sea mismo folio, rfc, volumen, importe al registrado en BDD-->
		<span class="requerido">LOS DATOS DE LAS FACTURAS NO CORRESPONDEN A LAS QUE SE ENCUENTRAN REGISTRADAS EN LA BASE DE DATOS DE ASERCA</span><br>
	</s:if>
	<s:if test="%{lstPrecioPagadoMenor.size() > 0}"> <!--Cheques fuera del periodo de pago (solo aplica AXC)"-->
		<span class="requerido">PRECIO PAGADO MENOR</span><br>
	</s:if>
	<s:if test="%{lstGeneralToneladasTotalesPorBodFac.size() > 0}"> <!--TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS"-->
		<span class="requerido">TONELADAS TOTALES POR BODEGA DE BOLETAS Y FACTURAS</span><br>
	</s:if>
	<s:if test="%{lstBoletasFacturasPagosIncosistentes.size() > 0}"> <!--BOLETAS, FACTURAS Y PAGOS INCONSISTENTES POR PRODUCTOR"-->
		<span class="requerido">RESUMEN DE OBSERVACIONES</span><br>
	</s:if>
	<s:if test="%{lstRendimientosProcedente.size() > 0}"> <!--VOLUMEN NO PROCEDENTE POR RENDIMIENTO"-->
		<span class="requerido">VOLUMEN NO PROCEDENTE POR RENDIMIENTO MAXIMO</span><br>
	</s:if>
	<s:if test="%{lstVolumenCumplido.size() > 0}"> <!--VOLUMEN DE FINIQUITO"-->
		<span class="requerido">VOLUMEN DE FINIQUITO</span><br>
	</s:if>
	<s:if test="%{lstPrecioPagPorTipoCambio.size() > 0}"> <!--VOLUMEN DE FINIQUITO"-->
		<span class="requerido">PRECIO PAGADO AL PRODUCTOR MENOR AL PACTADO EN EL CONTRATO</span><br>
	</s:if>
	
</fieldset>
<s:include value="incluyeLogXls.jsp"/>