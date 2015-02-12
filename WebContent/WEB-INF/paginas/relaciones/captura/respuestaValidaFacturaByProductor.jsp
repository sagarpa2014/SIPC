<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorFacturaByProductor==1}">
	<div class="msjError1">La Factura ya se encuentra registrada para el productor, favor de verificar</div>
</s:if>
<s:hidden id="errorFacturaByProductor%{count}" name="" value="%{errorFacturaByProductor}"/>