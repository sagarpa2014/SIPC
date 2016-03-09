<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/avisosDof.js"/>"></script>	
<div id="dialog-form"></div> 
<div class="borderBottom"><h1>REGISTRO DE AVISOS</h1></div><br>
<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
<fieldset>	
	<legend>DATOS DEL AVISO</legend>
	<div>
		<label class="left1"><span class="requerido">*</span>Clave del Aviso:</label>
		<s:if test="%{av.claveAviso!=null && av.claveAviso != ''}" >
			<s:textfield id="claveAviso" name="claveAviso" maxlength="6" size="30"  value="%{av.claveAviso}" disabled="true"/>
		</s:if>
		<s:else>
			<s:textfield id="claveAviso" name="claveAviso" maxlength="6" size="30"  value="%{av.claveAviso}"/>
		</s:else>	
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Leyenda:</label>
		<s:textfield name="" maxlength="100" size="50" id="leyenda" value="%{av.leyenda}"/>
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Fecha de Publicaci&oacute;n:</label> 
		<s:if test="%{av.fechaPublicacion==null}" >
			<s:textfield name="" maxlength="10" size="10" id="fechaPublicacion" readonly="true" cssClass="dateBox" />
		</s:if>
		<s:else>
			<s:textfield name="" maxlength="10" size="10" id="fechaPublicacion" readonly="true" cssClass="dateBox" value="%{getText('fecha1',{av.fechaPublicacion})}" />
		</s:else>
			
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
			<script type="text/javascript">
				<!--
				
					Calendar.setup({
						inputField     :    "fechaPublicacion",     
						ifFormat       :    "%d/%m/%Y",     
						button         :    "trg1",  
						align          :    "Br",           
						singleClick    :    true
					});
				//-->
			</script>
			
				
	</div>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Apoyo:</label>
		<s:select id="idApoyo" name="" list="lstApoyoAviso" style="width:200px" listKey="id" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{av.idApoyo}" onchange="" />		
	</div>	
</fieldset>
<fieldset>
		<legend>DETALLE DE MONTOS DEL AVISO</legend>
		<table id="tabAvisosDof" class="">    
			<thead>
		      <tr class="">        
		      	<th>Programa</th>
		      	<th>Cultivo</th>
		      	<th>Ciclo</th>
		      	<th>Ejercicio</th>
		      	<th>Estado</th>        
		      	<th>Volumen</th>      
		      	<th>Importe</th>
<%-- 		      	<s:if test="%{registrar!=0}" > --%>
		      		<th>Eliminar Registro</th>
<%-- 		      	</s:if> --%>
		      </tr>    
			 </thead>    
			 <tbody>
			 	<s:iterator value="lstAvisosDofDetalleV" var="resultado"  status="itStatus">
			 		<tr>
			 			<td><s:property value="%{programa}"/></td>
			 			<td><s:property value="%{cultivo}"/></td>				
			 			<td><s:property value="%{ciclo}"/></td>				
			 			<td><s:property value="%{Ejercicio}"/></td>		
			 			<td><s:property value="%{estado}"/></td>				
			 			<td>
			 				<s:text name="volumen"><s:param value="%{volumen}"/></s:text>
			 			</td>				
			 			<td>
			 				<s:text name="importe"><s:param value="%{importe}"/></s:text>
			 			</td>
<%-- 			 			<s:if test="%{registrar!=0}" > --%>
			 				<td class="center" id="eliminarDetalleAviso<s:property value="%{#itStatus.count}"/>" >
			 					<img id="imgBodegaOff" src="<s:url value="/images/eliminarOn.png"/>" onclick="if (confirm('¿Esta seguro de eliminar el registro?')){eliminarDetalleAviso(<s:property value="%{id}"/>,<s:property value="%{#itStatus.count}"/>);}else{return false;}"/>
			 				</td>
<%-- 			 			</s:if>					 --%>
			 		</tr>
			 	</s:iterator>   
			 </tbody>
		</table>
		<button id="add-reg">Agregar Registro</button>  
</fieldset>
<div class="izquierda">
	<a href="<s:url value="/avisosDof/ltsAvisosDof"/>" class="" title="" >&lt;&lt; Regresar</a>
</div>	


