<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstComByPrograma.size() > 0 && lstComByPrograma.size() > 0 ">
	<s:hidden name="criterioPago" id="criterioPago" value="%{criterioPago}"/>
	<div>
		<label class="left1"><span class="requerido">*</span>Nombre</label>
		<s:select id="idComprador" name="idComprador" list="lstComByPrograma" listKey="idComprador" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" onclick="datosComprador();" onchange="datosComprador();" />
	</div>
	<div class="clear"></div>
	<!--Se activa cuando un comprador es seleccionado, a traves de funcion ajax que consigue los datos de la clabe bancaria y rfc en caso de tener -->
	<div id="datosComprador"></div>
	<div class="clear"></div>
	<s:if test="criterioPago==2 || criterioPago==3">
		<div>
			<label class="left1"><span class="requerido">*</span>Etapa</label>
			<s:select id="etapa" name="etapa"  headerKey="-1" headerValue="Seleccione una opción"								
				list="#{'I':'I', 'II':'II', 'III':'III','IV':'IV','V':'V'}" />
		</div>
	</s:if>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Estados a apoyar:</label>
		<!-- <s:textfield id="numCampos" name="numCampos"  maxlength="100" size="30"  value=""/>-->
		<s:select id="numCampos" name="numCampos"  headerKey="-1"
				headerValue="Seleccione una opción"
				onchange="agregaEdoPorVolumen(0)"
				list="#{'1':'1', '2':'2', '3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10',
				'11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21',
				'22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31','32':'32',
				'33':'33'}" />
	</div>
	<div id="agregaEdoPorVolumen"></div>
</s:if>
<s:elseif test="lstComprador.size() < 1">No se encontraron compradores asociados al esquema</s:elseif>
<s:elseif test="l.size() < 1">No se encontraron compradores asociados al esquema</s:elseif>
<s:else>No se encontraron compradores ni estados asociados al esquema</s:else>
