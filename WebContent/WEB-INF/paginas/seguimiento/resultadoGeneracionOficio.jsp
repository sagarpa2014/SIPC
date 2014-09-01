<%@taglib uri="/struts-tags" prefix="s"%>
<div class="withborder">
	<s:if test="%{errorOficioDuplicado!=true}">
		<div class="correcto">Se gener&oacute; satisfactoriamente el archivo, favor de descargarlo</div>
		<div class="clear"></div>
		<br>
		<div class="pdf">
			<a href="<s:url value="/seguimiento/consigueOficio?idSeguimiento=%{idSeguimiento}"/>" title="Descargar Archivo" ></a>
		</div>
		<br>
		<br>
	</s:if>
	<s:else>
		<span class="error">
			El n�mero de oficio ya se encuentra registrado, por favor verifique
		</span>	
		<br>
	</s:else>
	<div class="accion">
		<a href="#" class="boton" title="" onclick="cerrarErrorOficio()">Cerrar</a>
	</div>
</div>


	
		