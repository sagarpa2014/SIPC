<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorFolioTalonTerrestre==1}">
	<div class="msjError1">El folio ya se encuentra registrado, por favor verifique</div>
</s:if>
<s:hidden id="errorFolioTalonTerrestre" name="" value="%{errorFolioTalonTerrestre}"/>
