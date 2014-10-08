<%@taglib uri="/struts-tags" prefix="s"%>


 <script>
 $(function() {
		$("#tabs").tabs();
	});
</script>
<script type="text/javascript" src="<s:url value="/js/bodegas.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>
<s:form action="registraBodega"  enctype="multipart/form-data" accept-charset="utf-8"   onsubmit="return chkCamposBodega();">
	<s:hidden id="registrar" name="registrar" value="%{registrar}"/>
	<s:hidden id="fechaActualL" name="fechaActualL" value="%{fechaActualL}"/>
	<br>
	<br>
	<br>
	<br>	 
	<div id="tabs">	
	  <ul>
	    <li><a href="#tabs-1" id="tabla1">Ubicaci&oacute;n Bodega</a></li>
	    <li><a href="#tabs-2" id="tabla2">Datos de la Bodega</a></li>
	    <li><a href="#tabs-3" id="tabla3">Capacidad de Almacenamiento</a></li>
	    <li><a href="#tabs-4" id="tabla4">Representante Legal</a></li>
	  </ul>	
	  <div id="tabs-1" >
		  <fieldset>	
		  	<legend>Clave de la Bodega</legend>	  
		  	<div class="izquierda">	  	
				<label class="left1"><span class="requerido">*</span>Clave Bodega:</label>
				<s:if test="registrar==1 || registrar==2">
					<font class="arial14boldVerde"><s:property value="%{bodegasV.claveBodega}"/></font>
					<s:hidden id="claveBodega" name="claveBodega" value="%{bodegasV.claveBodega}"/>
				</s:if>
				<s:else>
					<s:textfield id="claveBodega" name="claveBodega" maxlength="11" size="30" value="%{}" onchange="validaComposicionClaveBodega();"/>
				</s:else>
			</div>
			<div class="derecha">
  				<label class="left1"><span class="requerido">*</span>Fecha Registro:</label>
  				<s:textfield id="fechaRegistro" name="fechaRegistro" maxlength="10" size="10" cssClass="fecha" value="%{getText('fecha1',{bodegasV.fechaRegistro})}"/>  
  				<s:if test="registrar!=2">
					<img width="16px" src="../images/calendar.gif" id="trgFechaRegistro" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     :    'fechaRegistro',     
							ifFormat       :    "%d/%m/%Y",     
							button         :    'trgFechaRegistro',  
							align          :    "Br",           
							singleClick    :    true
							});
					</script>
				</s:if>				
			</div>
			<div class="clear"></div> 	
		  </fieldset>
		  <br>
		  <fieldset>
		  	<legend>Ubicaci&oacute;n</legend>  
		  	<div id="validaComposicionClaveBodega">
			  	<s:include value="ubicacionBodegas.jsp"/>
			 </div>	
		  </fieldset>
		  <br>
		  <fieldset>
		  	<legend>Coordenadas Geogr&aacute;ficas</legend>
		  	<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
		  				<label ><span class="requerido">*</span>Latitud:</label>
		  			</td>
		  			<td>
		  				<s:textfield id="latitudGrados" name ="latitudGrados" maxlength="2" size="3" value="%{bodegasV.latitudGrados}" onchange="validaRango(this.value,  'latitudGrados', 1, 'Latitud');"/>
		  			</td>
		  			<td>
		  				<s:textfield id="latitudMinutos" name ="latitudMinutos"  maxlength="2" size="3" value="%{bodegasV.latitudMinutos}" onchange="validaRango(this.value,  'latitudMinutos', 2, 'Latitud minutos');"/>
		  			</td>
		  			<td>
		  				<s:textfield id="latitudSegundos" name ="latitudSegundos" maxlength="2" size="3" value="%{bodegasV.latitudSegundos}" onchange="validaRango(this.value,  'latitudSegundos', 3, 'Latitud segundos');"/>
		  			</td>
		  			<td>
		  				<label ><span class="requerido">*</span>Longitud:</label>
		  			</td>
		  			<td>
		  				<s:textfield id="longitudGrados" name ="longitudGrados" maxlength="2" size="3" value="%{bodegasV.longitudGrados}" onchange="validaRango(this.value,  'longitudGrados', 1, 'Longitud');"/>
		  			</td>
		  			<td>
		  				<s:textfield id="longitudMinutos" name ="longitudMinutos" maxlength="2" size="3" value="%{bodegasV.longitudMinutos}" onchange="validaRango(this.value,  'longitudMinutos', 2, 'Longitud minutos');"/>
		  			</td>
		  			<td>
		  				<s:textfield  id="longitudSegundos" name="longitudSegundos" maxlength="2" size="3" value="%{bodegasV.longitudSegundos}" onchange="validaRango(this.value,  'longitudSegundos', 3, 'Longitud segundos');"/>
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
	  		<legend>Clasificaci&oacute;n</legend>
	  		<table class= "clean" style="width: 100%">
	  			<tr>
	  				<td align="left">
	  					<label><span class="requerido">*</span>Clasificaci&oacute;n:</label>
	  				</td>
	  				<td align="left" colspan="5">
		  				<s:select id="clasificacion" name="clasificacion" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{1:'PRIMER',2:'SEGUNDO',3:'TERCER', 4:'CUART0'}"  onchange="" value="%{bodegasV.clasificacion}"/>
	  				</td>
	  			<tr>
	  			<tr>
	  				<td>
	  					<label >Nivel:</label>	
	  				</td>
	  				<td align="center">
	  					<s:select id="claveUso1" name="claveUso1" list="lstBodegaUso" listKey="clave" listValue="%{tipoBodegaUso}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{bodegasV.uso1}"/>
	  				</td>
	  				<td align="center">
	  					<s:select id="claveUso2" name="claveUso2" list="lstBodegaUso" listKey="clave" listValue="%{tipoBodegaUso}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{bodegasV.uso2}"/>
	  				</td>
	  				<td align="center">
	  					<s:select id="claveUso3" name="claveUso3" list="lstBodegaUso" listKey="clave" listValue="%{tipoBodegaUso}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{bodegasV.uso3}"/>
	  				</td>
	  				<td>&nbsp;</td>
		  			<td>&nbsp;</td>
	  			</tr>
		  		<tr>
		  			<td>&nbsp;</td>
		  			<td align="center"><span class="requerido">*</span>Uso 1</td>
		  			<td align="center">Uso 2</td>
		  			<td align="center">Uso 3</td>
		  			<td>&nbsp;</td>		  	
		  			<td>&nbsp;</td>		  			
		  			
		  		</tr>	  			
	  		</table>			
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<legend>Datos Fiscales</legend>
	  		<table class= "clean" style="width: 100%">
	  			 <tr> 	
	  			  	<td colspan="6">
	  					<label class="left1"><span class="requerido">*</span>RFC:</label>
	  					<s:textfield  id="rfcBodega" name= "rfcBodega" maxlength="15" size="20" value="%{bodegasV.rfcBodega}" onkeyup ="convierteAmayuscula('rfcBodega');"/>
	  				</td>	  				
	  			</tr>
		  		<tr>
		  			<td colspan="6">
	  					<label class="left1"><span class="requerido">*</span>Nombre o Raz&oacute;n Social:</label>
	  					<s:textfield id="nombreRazonSocial"  name ="nombreRazonSocial" maxlength="80" size="80" value="%{bodegasV.nombre}" onkeyup ="convierteAmayuscula('nombreRazonSocial');"/>		  				
		  			</td>
		  		</tr>
		  	</table>
		</fieldset>
		<fieldset>
		<legend>Domicilio Fiscal</legend>
			<table class= "clean">
 				<tr>
		  			<td colspan="6">
	  					<label class="left1"><span class="requerido">*</span>Domicilio:</label>
	  					<s:textfield id="domicilioFiscal" name="domicilioFiscal"  maxlength="200" size="90" value="%{bodegasV.domicilioFiscal}" onkeyup ="convierteAmayuscula('domicilioFiscal');"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="6">
	  					<label class="left1"><span class="requerido">*</span>Calle:</label>
	  					<s:textfield id="calleDomFiscal"  name="calleDomFiscal" maxlength="80" size="90" value="%{bodegasV.calle}" onkeyup ="convierteAmayuscula('calleDomFiscal');"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="6">
	  					<label class="left1"><span class="requerido">*</span>N&uacute;mero:</label>
	  					<s:textfield id="numeroDomFiscal"  name="numeroDomFiscal" maxlength="20" size="20" value="%{bodegasV.numeroBodega}"/>		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="6">
		  				<label class="left1"><span class="requerido">*</span>Entidad Federativa:</label>	
		  				<s:select id="idEstadoDomFiscal" name="idEstadoDomFiscal" list="lstEstados" listKey="idEstado" listValue="%{nombre}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  onchange="recuperaMunicipioByEdoBodegas(this.value);" value="%{bodegasV.idEstado}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td id="recuperaMunicipioByEdoBodegas" colspan="6">	
		  				<label class="left1"><span class="requerido">*</span>Municipio:</label>
		  				<s:select id="claveMunicipioDomFis" name="claveMunicipioDomFis" list="lstMunicipios" listKey="claveMunicipio" listValue="%{nombreMunicipio}" headerKey="-1" headerValue="-- Seleccione una opción --" onchange="recuperaLocalidadesByMunicipioBodegas('idEstadoDomFiscal','claveMunicipioDomFis');" value="%{bodegasV.municipio}"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td id="recuperaLocalidadesByMunicipioBodegas" colspan="6">
			  			<label class="left1"><span class="requerido">*</span>Localidad:</label>
						<s:select id="claveLocalidadDomFis" name="claveLocalidadDomFis" list="lstLocalidades"  listKey="claveLocalidad" listValue="%{nombreLocalidad}" headerKey="-1" headerValue="-- Seleccione una opción --"  value="%{bodegasV.idLocalidadBodega}"/>
					</td>		  			
		  		</tr>
		  		<tr>
		  			<td colspan="6"> 
	  					<label class="left1"><span class="requerido">*</span>C&oacute;digo Postal:</label>
	  					<s:textfield  id="cpDomFiscal" name="cpDomFiscal" maxlength="6" size="6" value="%{bodegasV.codigoPostal}"/>
	  				</td>		  			
		  		</tr>  
		  		<tr>
		  			<td>
	  					<label class="left1"><span class="requerido">*</span>Tel&eacute;fono:</label>
	  					<s:textfield  id="telefonoDomFis" name ="telefonoDomFis" maxlength="50" size="30" value="%{bodegasV.telefono}"/>
	  				</td>  				
		  			<td>
	  					<label class="left1"><span class="requerido">*</span>Fax:</label>
	  					<s:textfield  id="faxDomFis" name ="faxDomFis" maxlength="50" size="30" value="%{bodegasV.fax}"/>
		  			</td>
		  			<td colspan="4"></td>
		  		</tr>
		  		
		  	</table>
	  	</fieldset>
	  	<br>
	  	<fieldset>
	  		<legend>Personas autorizadas para proporcionar información</legend>
	  		<table class= "clean" style="width: 100%">
		  		<tr>
		  			<td>
	  					<label class="left1"><span class="requerido">*</span>Nombre (pat, mat, nom):</label>
	  					<s:textfield id="personaAutorizada1" name="personaAutorizada1"  maxlength="100" size="50" value="%{bodegasV.personaAutorizada1}" onkeyup ="convierteAmayuscula('personaAutorizada1');"/>		  				
		  			</td>
		  			<td>
	  					<label class="left2"><span class="requerido">*</span>Puesto:</label>
	  					<s:textfield id="puestoPersonaAutorizada1" name="puestoPersonaAutorizada1" maxlength="50" size="40" value="%{bodegasV.puestoPersonaAutorizada1}" onkeyup ="convierteAmayuscula('puestoPersonaAutorizada1');" />		  				
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
	  					<label class="left1"><span class="requerido">*</span>Nombre (pat, mat, nom):</label>
	  					<s:textfield id="personaAutorizada2" name="personaAutorizada2"  maxlength="100" size="50" value="%{bodegasV.personaAutorizada2}" onkeyup ="convierteAmayuscula('personaAutorizada2');"/>		  				
		  			</td>
		  			<td>
	  					<label class="left2"><span class="requerido">*</span>Puesto:</label>
	  					<s:textfield id="puestoPersonaAutorizada2" name="puestoPersonaAutorizada2" maxlength="50" size="40" value="%{bodegasV.puestoPersonaAutorizada2}" onkeyup ="convierteAmayuscula('puestoPersonaAutorizada2');"/>		  				
		  			</td>
		  		</tr>
		  	</table>
	  	</fieldset>  
	  </div>
	  <div id="tabs-3">
	  		<s:include value="capacidadAlmacenamiento.jsp"/>  	
	  </div>
	  <div id="tabs-4">
	  	<s:include value="datosRepresentanteLegal.jsp"/> 

	  </div>
	</div>
	<br>
	<s:if test='#session.idPerfil==11 || #session.idPerfil==12'>
		<s:if test="registrar != 2">
			<div class="accion">
				<s:submit  value="Guardar" cssClass="boton2" />
			</div>
		</s:if>
	</s:if>
	<div class="izquierda"><a href="<s:url value="/catalogos/busquedaBodegas"/>" onclick="" title="" >&lt;&lt; Regresar</a></div>
	<s:if test="registrar == 2"> <!-- Consulta -->
		<script>
			$(document).ready(function() {	
				$("input").attr('disabled','disabled');
				$("select").attr('disabled','disabled');				
			});
			
			
		</script>
	</s:if>
	
	
</s:form>
