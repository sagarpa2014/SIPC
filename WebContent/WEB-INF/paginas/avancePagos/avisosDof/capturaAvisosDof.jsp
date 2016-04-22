<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/avisosDof.js"/>"></script>	
<div id="dialog-form"></div> 
<div class="borderBottom"><h1>REGISTRO DE AVISOS</h1></div><br>
<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
<s:hidden id="idAvisoDof" name="idAvisoDof" value="%{idAvisoDof}"/>
<fieldset>	
	<legend>DATOS DEL AVISO</legend>
	<div class="clear"></div>
	<div>
		<label class="left1"><span class="requerido">*</span>Leyenda:</label>
		<s:textfield name="" maxlength="500" size="100" id="leyenda" value="%{av.leyenda}"/>
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
</fieldset>
<fieldset>
		<legend>DETALLE DE MONTOS DEL AVISO</legend>
		<table id="tabAvisosDof" class="">    
			<thead>
		      <tr class="">		      	        
		      	<th>Programa</th>
		      	<th>Apoyo</th>
		      	<th>Ciclo Agricola</th>
		      	<th>Cultivo</th>		      	
		      	<th>Estado</th>        
		      	<th>Volumen</th>      
		      	<th>Importe</th>
		      	<th>Volumen Regional</th>      
		      	<th>Importe Regional</th>
		      	<th>Editar</th>
		      	<th>Eliminar</th>
		      </tr>    
			 </thead>    
			 <tbody>
			 	<s:iterator value="lstAvisosDofDetalleV" var="resultado"  status="itStatus">
			 		<tr>
			 			<td id="pgr<s:property value="%{#itStatus.count}"/>"><s:property value="%{programa}"/></td>
			 			<td id="apoy<s:property value="%{#itStatus.count}"/>"><s:property value="%{apoyo}"/></td>
			 			<td id="cicloA<s:property value="%{#itStatus.count}"/>"><s:property value="%{cicloAgricola}"/></td>
			 			<td id="cult<s:property value="%{#itStatus.count}"/>"><s:property value="%{cultivo}"/></td>		
			 			<td id="est<s:property value="%{#itStatus.count}"/>"><s:property value="%{estado}"/></td>				
			 			<td id="vol<s:property value="%{#itStatus.count}"/>">
			 				<s:text name="volumen"><s:param value="%{volumen}"/></s:text>
			 			</td>				
			 			<td id="imp<s:property value="%{#itStatus.count}"/>">
			 				<s:text name="importe"><s:param value="%{importe}"/></s:text>
			 			</td>
			 			<td id="volReg<s:property value="%{#itStatus.count}"/>">
			 				<s:if test="volumenRegional!=null">
			 					<s:text name="volumen"><s:param value="%{volumenRegional}"/></s:text>
			 				</s:if>
			 			</td>				
			 			<td id="impReg<s:property value="%{#itStatus.count}"/>">
			 				<s:if test="importeRegional!=null">
			 					<s:text name="importe"><s:param value="%{importeRegional}"/></s:text>
			 				</s:if>
			 			</td>
			 			<td class="center" id="editarDetalleAviso<s:property value="%{#itStatus.count}"/>" >
			 				<a href="#"  id="<s:property value="%{id}"/>-<s:property value="%{#itStatus.count}"/>" class="editar" title=""></a>
			 			</td>
			 			<td class="center" id="eliminarDetalleAviso<s:property value="%{#itStatus.count}"/>" >
			 				<img id="imgBodegaOff" src="<s:url value="/images/eliminarOn.png"/>" onclick="if (confirm('¿Esta seguro de eliminar el registro?')){eliminarDetalleAviso(<s:property value="%{id}"/>,<s:property value="%{#itStatus.count}"/>);}else{return false;}"/>
			 			</td>
			 		</tr>
			 	</s:iterator>   
			 </tbody>
		</table>
		<button id="add-reg">Agregar Registro</button>  
</fieldset>
<div class="izquierda">
	<a href="<s:url value="/avisosDof/ltsAvisosDof"/>" class="" title="" >&lt;&lt; Regresar</a>
</div>	


