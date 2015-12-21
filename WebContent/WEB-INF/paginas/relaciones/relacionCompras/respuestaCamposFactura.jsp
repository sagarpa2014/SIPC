<%@taglib uri="/struts-tags" prefix="s"%>
<div class="clear"></div>
<s:if test="%{lstGruposCamposDetalleRelacionV.size() > 0}">	
	<center>
		<table>
			<tr>
				<th><input id="o1" name="o1" value="-1" type="checkbox" onclick ="chkTodos();" class="check_todos"/></th>
				<th>Campos</th>
			</tr>
				<s:iterator value="lstGruposCamposDetalleRelacionV" status="itStatus">
					<tr>
						<td><input id="c<s:property value="%{#itStatus.count}"/>" name="camposFactura" value="<s:property value="%{idCampo}"/>" type="checkbox"  class="ck"/></td>
						<td><s:property value="%{campo}"/></td>
					</tr>
				</s:iterator>
		</table>
	</center>						
</s:if>
<s:else>
	No hay nada que validar
</s:else>