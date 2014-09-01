<%@taglib uri="/struts-tags" prefix="s"%>
 <script>
 $(function() {
		$("#tabs").tabs();
	});

</script>

<s:form action="ejecutaAlta" onsubmit="">
	<br>
	<br>
	<br>
	<br>
	<div id="tabs">	
	  <ul>
	    <li><a href="#tabs-1">Ubicaci&oacute;n Bodega</a></li>
	    <li><a href="#tabs-2">Datos Generales</a></li>
	    <li><a href="#tabs-3">Capacidad de Almacenamiento</a></li>
	    <li><a href="#tabs-4">Datos del Propietario</a></li>
	  </ul>	
	  <div id="tabs-1">
		  <fieldset>
		  	<div class="izquierda">	  	
				<label class="left1">Clave Bodega:</label>
				<font class="arial14boldVerde"><s:property value="%{bodegasV.claveBodega}"/></font>
			</div>
			<div class="derecha">
  				<label class="left1">Fecha Registro:</label>
  				<s:textfield disabled="true" maxlength="10" size="10" cssClass="fecha" value="%{getText('fecha1',{bodegasV.fechaRegistro})}"/>  				
			</div>
			<div class="clear"></div> 	
		  </fieldset>
		  <br>
		  <fieldset>  	
		  	<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td colspan="2">
	  					<label class="left1">Estado:</label>
	  					<s:textfield disabled="true" maxlength="50" size="50" value="%{bodegasV.nombreEstado}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
	  					<label class="left1">DDR:</label>
	  					<s:textfield disabled="true" maxlength="50" size="30" value="%{bodegasV.nombreDdr}"/>
		  			</td>
		  			<td>
	  					<label class="left1">CADER:</label>
	  					<s:textfield disabled="true" maxlength="50" size="30" value="%{bodegasV.nombreCader}"/>		  			
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
	  					<label class="left1">Municipio:</label>
	  					<s:textfield disabled="true" maxlength="50" size="30" value="%{bodegasV.nombreMunicipio}"/>
		  			</td>
		  			<td>
	  					<label class="left1">Ejido:</label>
	  					<s:textfield disabled="true" maxlength="50" size="30" value="%{bodegasV.nombreEjido}"/>
		  			</td>
		  		</tr>
		  	</table>
		  </fieldset>
		  <br>
		  <fieldset>
		  	<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label ><span class="norequerido">*</span>Latitud:</label>
		  			</td>
		  			<td>
		  				<s:textfield disabled="true" maxlength="3" size="3" value="%{bodegasV.latitudGrados}"/>
		  			</td>
		  			<td>
		  				<s:textfield disabled="true" maxlength="3" size="3" value="%{bodegasV.latitudMinutos}"/>
		  			</td>
		  			<td>
		  				<s:textfield disabled="true" maxlength="3" size="3" value="%{bodegasV.latitudSegundos}"/>
		  			</td>
		  			<td>
		  				<label ><span class="norequerido">*</span>Longitud:</label>
		  			</td>
		  			<td>
		  				<s:textfield disabled="true" maxlength="3" size="3" value="%{bodegasV.longitudGrados}"/>
		  			</td>
		  			<td>
		  				<s:textfield disabled="true" maxlength="3" size="3" value="%{bodegasV.longitudMinutos}"/>
		  			</td>
		  			<td>
		  				<s:textfield disabled="true" maxlength="3" size="3" value="%{bodegasV.longitudSegundos}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>&nbsp;</td>
		  			<td>grados</td>
		  			<td>minutos</td>
		  			<td>segundos</td>
		  			<td>&nbsp;</td>
		  			<td>grados</td>
		  			<td>minutos</td>
		  			<td>segundos</td>
		  		</tr>
		  	</table> 		  
		  </fieldset>
	  </div>
	  <div id="tabs-2">
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
	  			<tr>
	  				<td>
	  					<label class="left1">Clasificaci&oacute;n:</label>
	  					<s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.clasificacion}"/>
	  				</td>
	  				<td>
	  					<label >Nivel Uso:</label>	
	  				</td>
	  				<td><s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.uso1}"/></td>
	  				<td><s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.uso2}"/></td>
	  				<td><s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.uso3}"/></td>
	  				<td>
	  					<label class="left1">RFC operador:</label>
	  					<s:textfield disabled="true" maxlength="15" size="15" value="%{bodegasV.rfcBodega}"/>
	  				</td>	  				
	  			</tr>
	  		</table>			
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td colspan="6">
	  					<label class="left1">Nombre Bodega:</label>
	  					<s:textfield disabled="true" maxlength="80" size="80" value="%{bodegasV.nombre}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="6">
	  					<label class="left1">Conocida como:</label>
	  					<s:textfield disabled="true" maxlength="80" size="80" value="%{bodegasV.aliasBodega}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="6">
	  					<label class="left1">Domicilio:</label>
	  					<s:textfield disabled="true" maxlength="80" size="90" value="%{bodegasV.direccion}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
	  					<label class="left1">C&oacute;digo Postal:</label>
	  				</td>
	  				<td>
	  					<s:textfield disabled="true" maxlength="6" size="6" value="%{bodegasV.codigoPostal}"/>
		  			</td>
		  			<td>
	  					<label class="left1">Tel&eacute;fono:</label>
	  				</td>
	  				<td>
	  					<s:textfield disabled="true" maxlength="35" size="20" value="%{bodegasV.telefono}"/>
		  			</td>
		  			<td>
	  					<label class="left1">Fax:</label>
		  			</td>
		  			<td>	  					
	  					<s:textfield disabled="true" maxlength="15" size="10" value="%{bodegasV.fax}"/>
		  			</td>
		  		</tr>  
		  	</table>
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
	  					<label class="left1">Gerente o Almacenista:</label>
	  					<s:textfield disabled="true" maxlength="90" size="80" value="%{bodegasV.gerente}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
	  					<label class="left1">Correo electr&oacute;nico:</label>
	  					<s:textfield disabled="true" maxlength="150" size="100" value="%{bodegasV.correoElectronico}"/>		  				
		  			</td>
		  		</tr>
		  	</table>
	  	</fieldset>  
	  </div>
	  <div id="tabs-3">
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">Capacidad bodega:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadBodega}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Intemperie:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadIntemperie}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">No. Silos:</label>
		  				<s:textfield disabled="true" maxlength="10" size="10" cssClass="cantidad" value="%{getText('contador',{bodegasV.noSilos})}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Techada:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadTechada}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Capacidad Silos:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadSilos}"/>
		  			</td>		  			
		  			<td>
		  				<label class="left1"><font class="arial12bold">TOTAL ALMACENAMIENTO:</font></label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.totalAlmacenamiento}"/>		  						
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">RECEPCION:</label>
		  				<label class="left1">No. de rampas:</label>
		  				<s:textfield disabled="true" maxlength="10" size="10" cssClass="cantidad" value="%{getText('contador',{bodegasV.numRampas})}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadRampas}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">SECADO:</label>
		  				<label class="left1">No. de secadoras:</label>
		  				<s:textfield disabled="true" maxlength="10" size="10" cssClass="cantidad" value="%{getText('contador',{bodegasV.numSecadoras})}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadSecado}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">EMBARQUE:</label>
		  				<label class="left1">No. de cargas:</label>
		  				<s:textfield disabled="true" maxlength="10" size="10" cssClass="cantidad" value="%{getText('contador',{bodegasV.numCargas})}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadCargas}"/>
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">TIPO DE BASCULA:</label>
		  				<label class="left1">(Piso):</label>
		  				<s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.piso}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad/B&aacute;scula:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadPiso}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">TIPO DE BASCULA:</label>
		  				<label class="left1">(FFCC):</label>
		  				<s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.ffcc}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad/B&aacute;scula:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.capacidadFfcc}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">ESPUELA DE FFCC:</label>
		  				<label class="left1">(S/N):</label>
		  				<s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.espuelaFfcc}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad:</label>
		  				<s:textfield disabled="true" maxlength="35" size="35" value="%{bodegasV.capacidadEspuela}"/>
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">Capacidad de recepci&oacute;n:</label>
		  				<s:textfield disabled="true" maxlength="20" size="20" value="%{bodegasV.capacidadRecepcion}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Capacidad embarque:</label>
		  				<s:textfield disabled="true" maxlength="20" size="20" value="%{bodegasV.capacidadEmbarque}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Existe equipo de an&aacute;lisis (S/N):</label>
		  				<s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.equipoAnalisis}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Aplica normas de calidad (S/N):</label>
		  				<s:textfield disabled="true" maxlength="2" size="2" value="%{bodegasV.aplicaNormasCalidad}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="2">
		  				<label class="left1">Observaciones:</label>
		  				<s:textfield disabled="true" maxlength="120" size="120" value="%{bodegasV.observaciones}"/>
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>	  	
	  </div>
	  <div id="tabs-4">
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">RFC:</label>
		  				<s:textfield disabled="true" maxlength="15" size="15" value="%{bodegasV.rfcPropietario}"/>		  				
		  			</td>
		  			<td>
		  				<label class="left1">CURP:</label>
		  				<s:textfield disabled="true" maxlength="18" size="18" value="%{bodegasV.curpPropietario}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Nombre:</label>
		  				<s:textfield disabled="true" maxlength="60" size="60" value="%{bodegasV.nombrePropietario}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Paterno:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.paternoPropietario}"/>
		  			</td>		  			
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Materno:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.maternoPropietario}"/>		  						
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">Domicilio:</label>
		  				<s:textfield disabled="true" maxlength="70" size="70" value="%{bodegasV.callePropietario}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Localidad:</label>
		  				<s:textfield disabled="true" maxlength="30" size="30" value="%{bodegasV.localidadPropietario}"/>
		  			</td>
		  			<td>
		  				<label class="left1">C&oacute;digo Postal:</label>
		  				<s:textfield disabled="true" maxlength="5" size="5" value="%{bodegasV.cpPropietario}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<label class="left1">Tel&eacute;fonos:</label>
		  				<s:textfield disabled="true" maxlength="35" size="35" value="%{bodegasV.telefonoPropietario}"/>
		  			</td>
		  			<td>
		  				<label class="left1">Fax:</label>
		  				<s:textfield disabled="true" maxlength="15" size="15" value="%{bodegasV.faxPropietario}"/>
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label class="left1">Correo electr&oacute;nico:</label>
		  				<s:textfield disabled="true" maxlength="60" size="60" value="%{bodegasV.correoPropietario}"/>
		  			</td>
		  		</tr>
		  	</table>	  		
	  	</fieldset>
	  </div>
	</div>
	<br>
	<div class="izquierda"><a href="<s:url value="/catalogos/busquedaBodegas"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
</s:form>
