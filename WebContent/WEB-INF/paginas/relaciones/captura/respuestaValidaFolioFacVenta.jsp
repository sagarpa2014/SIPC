<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorFolioFacVenta==1}">
	<div class="msjError1">El folio ya se encuentra registrado ciclo y cultivo</div>
</s:if>
<s:elseif test="%{errorFolioFacVenta==1}">
	<div class="msjError1">El folio ya se encuentra registrado, por favor verifique</div>
</s:elseif>
<s:hidden id="errorFolioFacVenta" name="" value="%{errorFolioFacVenta}"/>
