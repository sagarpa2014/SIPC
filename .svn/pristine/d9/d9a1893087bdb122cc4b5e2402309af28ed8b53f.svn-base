<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
	<link rel="stylesheet" type="text/css" href="<s:url value="/css/screen.css" />" media="screen, projection" />
    <link rel="stylesheet" type="text/css" href="<s:url value="/css/nav-h.css" />" media="screen, projection" />
	<script type="text/javascript" src="<s:url value="/js/nav-h.js" />"></script>
	<script type="text/javascript" language="JavaScript" src="<s:url value="/js/code.js" />"></script>
	<!--[if gte IE 5.5]>
	<script type="text/javascript" src="<s:url value="/js/nav-h.js" />"></script>
	<![endif]-->
	<title>Por favor espere...</title>
	<meta http-equiv="refresh" content="2;url=<s:url/>"/>
</head>
<body>
	<div id="contenido">
		<div id="bienvenida">
			<div id="principal">
				<img class="load" src="<s:url value="/images/logoASERCA.jpg" />" />
			</div>
			<s:if test="%{#session.nombreUsuario!=null}" >
				<div id="secundario">
					<s:include value="/WEB-INF/paginas/template/menu.jsp" />
					<div class="clear"></div>
					<div class="derecha">
						<div>
							<label class="left" >Bienvenido:</label><font class="fuente1"><s:property value="#session.nombreUsuario" /></font>
						</div>
						<div class="clear"></div>
						<div>
							<label class="left">Rol:</label><font class="fuente1"><s:property value="#session.perfil" /></font>
						</div>
					</div>
				</div>
			</s:if>
			<s:else><div id="secundario"></div></s:else>
		</div>				  
		<div class="clear"></div>
		
        <div id="pContenido">
        	<div class="erroresApp"><s:actionerror /></div>
			<div class="mensajesApp"><s:actionmessage /></div>
        	<!-- Inicia contenido -->
			<tiles:insertAttribute name="body" ignore="true" />
            <!-- Termina contenido -->
        </div>
		<div id="pie">
			<p>&copy;<s:date name="new java.util.Date()" format="yyyy" /> <a href="http://www.aserca.gob.mx" title="" target="_blank">ASERCA</a> Av. Municipio Libre #377, Col. Santa Cruz Atoyac C.P. 03310 México D.F. Teléfono (55) 38 71 73 00.</p>
		</div>
	</div>
	
	<div id="espera">
		<div id="img-espera">
			<img class="load" src="<s:url value="/images/ajax-loader.gif" />" />
  		</div>
  		<div id="texto-espera">Espere un momento por favor ...</div>
	</div>
</body>
</html>