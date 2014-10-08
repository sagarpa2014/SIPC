<%@taglib uri="/struts-tags" prefix="s"%>
	<s:hidden id="errorRepresentanteLegal" name="errorRepresentanteLegal" value="%{errorRepresentanteLegal}"/>	
	<s:if test="errorRepresentanteLegal==1">
		<div class="msjError1">No se encuentra registrado el representante en el sistema, por favor verifique</div>
	</s:if>
	
	<div class="izquierda">
		<label class="left2">Nombre</label>
		<s:textfield id="" disabled = "true" name=""  maxlength="50" size="50" value="%{representanteLegal.nombre}"/>
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">Calle</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="100" size="80" value="%{representanteLegal.calle}"/>
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">N&uacute;mero Exterior</label>
		<s:textfield id=""  disabled = "true" name=""  maxlength="30" size="30" value="%{representanteLegal.numExterior}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2">N&uacute;mero Interior</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.numInterior}"/>
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">Localidad:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.localidad}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2">Municipio:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.municipio}"/>		
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">Entidad Federativa:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.estadoNacimiento}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2">C.P.:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.codigoPostal}"/>
		
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">Tel&eacute;fonos:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.telefonos}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2">Fax:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="30" size="30" value="%{representanteLegal.fax}"/>
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">Correo Electr&oacute;nico:</label>
		<s:textfield id="" disabled = "true"  name=""  maxlength="50" size="50" value="%{representanteLegal.correoElectronico}"/>
	</div>