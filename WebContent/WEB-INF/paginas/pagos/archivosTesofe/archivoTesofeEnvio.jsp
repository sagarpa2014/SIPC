<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/archivoTesofe.js" />"></script>
<div id="dialogo_1"></div>
<div class="borderBottom"><h1>ARCHIVOS DE ENVÍO TESOFE</h1></div><br>

	<div class="inline">
		<label class="left1">Seleccione una opción:</label>
		<s:radio label="" onclick="selectTipoOficio()"  name="tipoOficio" list="#{0:'GENERAR OFICIO', 1:'GENERAR ARCHIVO', 2:'REGRESAR ARCHIVO'}" value="%{0}" />
	</div>
	<div class="clear"></div>
	<div id="contenidoTipoOficio"><s:include value="/WEB-INF/paginas/pagos/archivosTesofe/oficioEnvio.jsp"/></div>
