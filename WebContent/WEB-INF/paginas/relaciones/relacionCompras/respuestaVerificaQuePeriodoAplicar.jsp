<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="%{grupoCriterio=='Facturas'}">
	<div ><label class="left1">Aplica Importe Pagos</label>	
		  <s:checkbox id="aplicaImportePago"  name="aplicaImportePago" onclick ="" fieldValue="true" value="%{}"/>
	</div>
</s:if>
<div class="clear"></div>
<s:if test="%{grupoCriterio=='Boletas'}">
	<s:if test="%{aplicaPeriodoLineamiento==1}">
		<s:include value="mostrarPeriodo.jsp"/>
		<s:hidden name = "pideTipoPeriodo" value="%{1}"/>
	</s:if>
	<s:else>
		<s:include value="mostrarOpcionTipoPeriodo.jsp"/>					
	</s:else>				
</s:if>
<s:elseif  test="%{grupoCriterio=='Facturas' || grupoCriterio=='Pagos'}">
	<s:include value="mostrarOpcionTipoPeriodo.jsp"/>				
</s:elseif>