<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/relVentas.js" />"></script>	
<div id="dialog-form">	
</div> 
 <fieldset>
		<legend>Registros existentes</legend>
		<table id="tabRelVentas" class="">    
			<thead>
		      <tr class="">        
		      	<th>Cliente</th>
		      	<th>Direcci&oacute;n</th>
		      	<th>Folio Factura</th>        
		      	<th>Fecha</th>      
		      	<th>Volumen</th>
		      	<th>Destino</th>
		      	<th>Uso del Grano</th>
<!-- 		      	<th>Editar</th> -->
		      </tr>    
			 </thead>    
			 <tbody>
			 	<s:iterator value="lstRelVentasV" var="resultado"  status="itStatus">
			 		<tr>
			 			<td><s:property value="%{cliente}"/></td>				
			 			<td><s:property value="%{direccionCliente}"/></td>				
			 			<td><s:property value="%{folioFactura}"/></td>				
			 			<td>
			 				<s:text name="fecha"><s:param value="%{fecha}"/></s:text>
			 			</td>				
			 			<td>
			 				<s:text name="volumen"><s:param value="%{volumen}"/></s:text>
			 			</td>
			 			<td><s:property value="%{destino}"/></td>
			 			<td><s:property value="%{desUsoGrano}"/></td>
<%-- 			 			<td><a href="#"  id="<s:property value="%{id}"/>" class="editar" title=""></a></td>	 							 --%>
			 		</tr>
			 	</s:iterator>   
			 </tbody>
		</table>
		<button id="add-reg">Agregar Registro</button>  
</fieldset>
