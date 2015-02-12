<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorBodegaTicket==1}">
	<div class="msjError1">La Boleta/Ticket ya se encuentra registrado para esa bodega, favor de verificar</div>
</s:if>
<s:hidden id="errorBodegaTicket%{count}" name="" value="%{errorBodegaTicket}"/>