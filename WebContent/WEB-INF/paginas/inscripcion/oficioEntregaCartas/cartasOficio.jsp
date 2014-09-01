<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="lstCartasV.size() > 0">
	<div id="pagos">
		<div>
			<label class="left1"><span class="requerido">*</span>No. de Oficio</label>
			<s:textfield id="claveOficio" name="claveOficio"  maxlength="10" size="8"  value="%{claveOficio}"/>
			<s:textfield id="noOficio" name="noOficio"  maxlength="10" size="8"  value="%{}"/>
			<s:textfield id="anioOficio" name="anioOficio"  maxlength="10" size="8"  value="%{anioOficio}"/>
		</div>
		<div class="clear"></div>
		<div>
            <label class="left1"><span class="requerido">*</span>Fecha Oficio Entrega:</label>
            <s:if test="%{fechaOficioEntrega==null}">
               <s:textfield name="fechaOficioEntrega" maxlength="10" size="10" id="fechaOficioEntrega" readonly="true" cssClass="dateBox" />
            </s:if>
            <s:else>
               <s:textfield key="fechaOficioEntrega" value="%{fechaOficioEntrega}" name="fechaOficioEntrega" maxlength="10" size="10" id="fechaOficioEntrega" readonly="true" cssClass="dateBox" />
            </s:else>
            <img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>			
		<div class="clear"></div>
		<table style="width:100%">
			<tr>
				<th><input id="o1" name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>
				<th>No. Carta</th>
				<th>Participante</th>
			</tr>
			<s:iterator value="lstCartasV" var="resultado">
				<tr>
					<td align="center"><input id="" name="" value="<s:property value="carta"/>" type="checkbox"  class="ck"/></td>
					<td><s:property value="carta" /></td>
					<td><s:property value="comprador" /></td>
				</tr>	
			</s:iterator>	
		</table>	
		<div class="accion">
			<a href="#" class="boton" title="" onclick="vistaPreviaOficio()">Vista Previa</a>
			<a href='#' onclick="generarOficioEntregaCartas()" class="boton">Generar Oficio</a>
			<a href="<s:url value="/inscripcion/oficioEntregaCartas"/>" class="boton" title="Cancelar" >Cancelar</a>
		</div>
	</div>	
	<div id="vistaPrevia" class="vistaPrevia"></div>
	<div id="resultadoGeneracionOficio"></div>	
<script type="text/javascript">
    <!--
	 Calendar.setup({
	                inputField     :    "fechaOficioEntrega",     
	                ifFormat       :    "%d/%m/%Y",     
	                button         :    "trg1",  
	                align          :    "Br",           
	                singleClick    :    true
	 });                           
	 //-->
</script>
</s:if>
<s:else><div class="advertencia">No se encontraron cartas de adhesión para el programa seleccionado</div></s:else>