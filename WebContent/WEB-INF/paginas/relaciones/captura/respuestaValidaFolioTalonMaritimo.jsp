<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorFolioTalonMaritimo==1}">
	<div class="msjError1">El folio ya se encuentra registrado, por favor verifique</div>
</s:if>
<s:hidden id="errorFolioTalonMaritimo" name="" value="%{errorFolioTalonMaritimo}"/>
