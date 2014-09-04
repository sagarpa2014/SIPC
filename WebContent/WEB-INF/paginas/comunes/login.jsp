<%@ taglib uri="/struts-tags" prefix="s"%>

<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<div id="dialogo_1"></div>	
<s:if test="%{#session.nombreUsuario == null }" >
	<div id="login" class="elementoOculto">
		<h1>Bienvenido</h1>
		<s:form name="forma" action="acceso" id="frmLog" onsubmit="return chkCamposLogin();">
				<label>Usuario:</label>
				<s:textfield name="nombreUsuario" maxlength="20" size="20" id="nombreUsuario" />
				
				<label>Contraseña:</label>
				<s:password name="password" maxlength="20" size="20" id="password"/>
			<br/>
			<br/>
			<div>
				<s:submit value="Entrar"  id="button"  cssClass="boton2" />
			</div>	
		</s:form>
	</div>
</s:if>

<div id="browserDesactualizado" class="advertencia">Su navegador no esta actualizado, el sistema soporta Internet Explorer, Firefox y Chrome en sus ultimas versiones</div>

<script>
	$(document).ready(function() { 
		var navegador = JSON.stringify(bowser);
		var version = parseInt(bowser.version);
		if(navegador.indexOf("Internet Explorer")!=-1){
			if(version < 12){
				$("#login").fadeOut('slow');
				$("#browserDesactualizado").fadeIn('slow');
			}else{
				$("#login").fadeIn('slow');
			}
		}else if(navegador.indexOf("Firefox")!=-1){
			if(version < 29){
				$("#login").fadeOut('slow');
				$("#browserDesactualizado").fadeIn('slow');
			}else{
				$("#login").fadeIn('slow');
			}
		}else if(navegador.indexOf("Chrome")!=-1){
			if(version < 36){
				$("#login").fadeOut('slow');
				$("#browserDesactualizado").fadeIn('slow');
			}else{
				$("#login").fadeIn('slow');
			}
		}			
	});    
</script>

