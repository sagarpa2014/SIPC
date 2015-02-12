<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{errorFolioCertificado==1}">
	<div class="msjError1">El folio ya se encuentra registrado para la bodega, ciclo, cultivo, y almacen</div>
</s:if>
<s:elseif test="%{errorFolioCertificadoAlmacen==1}">
	<div class="msjError1">El folio ya se encuentra registrado para el almacen, por favor verifique</div>
</s:elseif>
<s:hidden id="errorFolioCertificado" name="" value="%{errorFolioCertificado}"/>
<s:hidden id="errorFolioCertificadoAlmacen" name="" value="%{errorFolioCertificadoAlmacen}"/>