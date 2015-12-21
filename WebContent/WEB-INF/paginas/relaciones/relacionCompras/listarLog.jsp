<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{lstLog!=null}">
	<s:if test="%{lstLog.size() > 0}" >
		 <s:iterator value="lstLog" status="renglon"> 
		 	<!--<s:if test="%{(#renglon.count)==1 }">
			 	<table class="simple" style="width:100%">
				 	<tr>
				 		<td>
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaCartaAdhesion+nombreArchivoLog}"/>" title="Descargar Archivo">Descargar Archivo Log</a>		
						</td>
				 	</tr>
			 	</table>	
		 	</s:if>-->
			<s:property/><br>
		</s:iterator>		
	</s:if>
</s:if>	

