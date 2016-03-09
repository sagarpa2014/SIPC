<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{lstLog!=null}">
	<s:if test="%{lstLog.size() > 0}" >
		 <s:iterator value="lstLog" status="renglon"> 
			<font class="arial12normal"><s:property/></font><br>
		</s:iterator>		
	</s:if>
</s:if>