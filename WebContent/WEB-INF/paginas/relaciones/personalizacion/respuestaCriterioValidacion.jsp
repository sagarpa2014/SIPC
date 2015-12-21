<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="lstTV.size() > 0">
	<s:set name="idCampoRelacionTemp"><s:property value="%{idCampoRelacion}"/></s:set>
	<s:set name="idGrupoTemp"><s:property value="%{idGrupo}"/></s:set>
	<s:set name="idCampoTemp"><s:property value="%{idCampo}"/></s:set>
	<table>
		<s:iterator value="lstTV" status="itStatus">
			<tr>
				<td>
					<s:if test='registrar==0'>
						<input id="validacionCriterio<s:property value="%{count}"/><s:property value="%{#itStatus.count}"/>" name="camposValidacionCriterio" value="<s:property value="%{#idGrupoTemp}"/>-<s:property value="%{#idCampoTemp}"/>-<s:property value="%{#idCampoRelacionTemp}"/>-<s:property value="%{idCriterioValidacionRlFk}"/>" type="checkbox"  class=""/>
					</s:if>
					<s:else>
						<s:if test='%{idCampoRelacion!=null}'>
							<input id="validacionCriterio<s:property value="%{count}"/><s:property value="%{#itStatus.count}"/>" name="camposValidacionCriterio" value="<s:property value="%{#idGrupoTemp}"/>-<s:property value="%{#idCampoTemp}"/>-<s:property value="%{#idCampoRelacionTemp}"/>-<s:property value="%{idCriterioValidacionRlFk}"/>" type="checkbox"  class="" checked="checked"/>
						</s:if>
						<s:else>
							<input id="validacionCriterio<s:property value="%{count}"/><s:property value="%{#itStatus.count}"/>" name="camposValidacionCriterio" value="<s:property value="%{#idGrupoTemp}"/>-<s:property value="%{#idCampoTemp}"/>-<s:property value="%{#idCampoRelacionTemp}"/>-<s:property value="%{idCriterioValidacionRlFk}"/>" type="checkbox"  class=""/>
						</s:else>
					</s:else>
				</td>
				<td><s:property value="%{criterio}"/></td>
			</tr>
		</s:iterator>	
	</table>
</s:if>
