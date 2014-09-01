<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/solicitudPago.js" />"></script>
<s:if test="errorNumeroAuditor!=0">
	<s:hidden name="errorNumeroAuditor" id="errorNumeroAuditor" value="%{errorNumeroAuditor}"/>
	<div class="msjError"><s:property value="%{msjError}"/></div>	
</s:if>
<s:else>
	<s:textfield name="" maxlength="60" size="60" value="%{nombreAuditor}" disabled="true"/>
</s:else>
