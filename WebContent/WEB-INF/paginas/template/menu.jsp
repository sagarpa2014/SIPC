<%@taglib uri="/struts-tags" prefix="s"%>
<ul id="navmenu-h">
	<li><a class="esquinaIzq" href="<s:url value="/bienvenido"/>">Inicio</a></li>
	<s:if test='#session.idPerfil==1'>
		<li><a  href="#">Pagos</a>
			<ul>
				<li><a href="<s:url value="/pagos/capturaPagos"/>">Captura</a></li>
				<li><a href="<s:url value="/pagos/busquedaAutorizacionPagos"/>">Autorizaci&oacute;n</a></li>
				<li><a href="<s:url value="/pagos/busquedaPagos"/>">Consulta</a></li>			
			</ul>
		</li>
		<li ><a  href="#">Reportes</a>
			<ul>
				<li><a href="<s:url value="/reportes/reporteConcentradoPagos"/>">Concentrado Pagos</a></li>	
			</ul>
		</li>
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>
				<li><a href="<s:url value="/catalogos/busquedaCompradores"/>">Compradores</a></li>
				<li><a href="<s:url value="/catalogos/busquedaAuditores"/>">Auditores</a></li>
				<li><a href="<s:url value="/catalogos/busquedaRepLegal"/>">Representantes Legales</a></li>
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li>
			</ul>
		</li>
		<li><a href="#">Solicitud de Pago</a>
			<ul>
				<li><a href="<s:url value="/solicitudPago/listarAsignacionCAEspecialista"/>">Asignaci&oacute;n a Especialista</a></li>		
				<li><a href="<s:url value="/solicitudPago/listarPrograma"/>">Relaci&oacute;n de Documentos</a></li>	 
			</ul>
		</li>
		
	</s:if>
	<s:elseif test='#session.idPerfil==2'>
		<li ><a  href="<s:url value="/pagos/busquedaPagos"/>">Consulta de Pagos</a></li>
		<li><a href="#">Reportes</a>
			<ul>
				<li><a href="<s:url value="/reportes/capturaReportePagosTesofe"/>">Pagos a Tesofe</a></li> 
			</ul>
		</li>
		<li><a href="#">Archivos TESOFE</a>
			<ul>
				<li><a href="<s:url value="/pagos/archivoTesofeEnvio"/>">Generaci&oacute;n</a></li>	
				<li><a href="<s:url value="/pagos/cargarArchivo"/>">Respuesta</a></li>
				<li><a href="<s:url value="/pagos/consultaOficiosEnvioTesofe"/>">Consulta</a></li>
			</ul>
		</li>
		<li ><a  href="<s:url value="/pagos/busquedaPagosCADevoluciones"/>">Devoluciones</a></li>
	</s:elseif>
	<s:elseif test='#session.idPerfil==3'>
		<li ><a  href="#">Reportes</a>
			<ul>
				<li><a href="<s:url value="/reportes/reporteMonitorGlobal"/>">Monitor Global Pagos</a></li>	
				<li><a href="<s:url value="/reportes/reporteMonitorPrograma"/>">Monitor Programa-Comprador</a></li>
			</ul>
		</li>
		<li><a  href="#">Inscripci&oacute;n</a>
			<ul>
				<li><a href="<s:url value="/inscripcion/listarProgramas?idPrograma=0"/>">Inicializaci&oacute;n Programa</a></li>		 
			</ul>
		</li>
		<li><a href="#">SQL</a>
			<ul>
				<li><a href="<s:url value="/catalogos/ejecutaSQL"/>">Interprete SQL</a></li> 
			</ul>
		</li>
	</s:elseif>
	<s:elseif test='#session.idPerfil==10'>
		<li><a  href="#">Pagos</a>
			<ul>
				<li><a href="<s:url value="/pagos/capturaPagos"/>">Captura</a></li>
				<li><a href="<s:url value="/pagos/busquedaAutorizacionPagos"/>">Autorizaci&oacute;n</a></li>
				<li><a href="<s:url value="/pagos/busquedaPagos"/>">Consulta</a></li>
			</ul>
		</li>
		<li><a href="#">Reportes</a>
			<ul>
				<li><a href="<s:url value="/reportes/capturaReportePagosTesofe"/>">Pagos a Tesofe</a></li>
				<li><a href="<s:url value="/reportes/reporteMonitorGlobal"/>">Monitor Global Pagos</a></li>	
				<li><a href="<s:url value="/reportes/reporteMonitorPrograma"/>">Monitor Programa-Comprador</a></li>
				<li><a href="<s:url value="/reportes/capturaReporteDinamico"/>">Reportes Din&aacute;micos</a></li>
				<li><a href="<s:url value="/reportes/reporteConcentradoPagos"/>">Concentrado Pagos</a></li>
				<li><a href="<s:url value="/reportes/rendicionCuentasCID"/>">Rendici&oacute;n de Cuentas (ID)</a></li> 
				<li><a href="<s:url value="/reportes/rendicionCuentasSID"/>">Rendici&oacute;n de Cuentas</a></li>
			</ul>
		</li>
		<li><a href="#">Archivos TESOFE</a>
			<ul>
				<li><a href="<s:url value="/pagos/archivoTesofeEnvio"/>">Generaci&oacute;n</a></li>	
				<li><a href="<s:url value="/pagos/cargarArchivo"/>">Respuesta</a></li>
				<li><a href="<s:url value="/pagos/consultaOficiosEnvioTesofe"/>">Consulta</a></li>
			</ul>
		</li>
		<li ><a  href="<s:url value="/pagos/busquedaPagosCADevoluciones"/>">Devoluciones</a></li>
		<li><a href="#">Inscripci&oacute;n</a>
			<ul>
				<li><a href="<s:url value="/inscripcion/listarProgramas?idPrograma=0"/>">Inicializaci&oacute;n Programa</a></li>
				<li><a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>">Solicitud de Inscripción</a></li>		 
			</ul>
		</li>
		<li><a href="#">Solicitud de Pago</a>
			<ul>
				<li><a href="<s:url value="/solicitudPago/programasConfigExpediente"/>">Inicializaci&oacute;n</a></li>
				<li><a href="<s:url value="/solicitudPago/listarAsignacionCAEspecialista"/>">Asignaci&oacute;n a Especialista</a></li>
				<li><a href="<s:url value="/solicitudPago/listarPrograma"/>">Relaci&oacute;n de Documentos</a></li>		 
				<li><a href="<s:url value="/solicitudPago/listarProgramasPagosCartaAdhesion"/>">Ajuste Pago de Fianza</a></li><!-- Trámite de Pago -->
			</ul>
		</li>
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>
				<li><a href="<s:url value="/catalogos/busquedaAuditores"/>">Auditores</a></li>
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li>
				<li><a href="<s:url value="/catalogos/busquedaCompradores"/>">Compradores</a></li>
				<li><a href="<s:url value="/catalogos/busquedaRepLegal"/>">Representantes Legales</a></li>
			</ul>
		</li>
		<li><a href="#">SQL</a>
			<ul>
				<li><a href="<s:url value="/catalogos/ejecutaSQL"/>">Interprete SQL</a></li> 
			</ul>
		</li>			
	</s:elseif>
	<s:elseif test='#session.idPerfil==4'>
		<li><a  href="#">Reportes</a>
			<ul>		 
				<li><a href="<s:url value="/solicitudPago/listarPrograma"/>">Apoyos</a></li>	 
			</ul>
		</li>
	</s:elseif>
	<s:elseif test='#session.idPerfil==5'>
		<li><a href="#">Inscripci&oacute;n</a>
			<ul>
				<li><a href="<s:url value="/inscripcion/listarProgramas"/>">Inicializaci&oacute;n Programa</a></li>
				<li><a href="<s:url value="/inscripcion/busquedaSolicitudIns"/>">Solicitud de Inscripción</a></li>		 
				<li><a href="<s:url value="/inscripcion/oficioEntregaCartas"/>">Entrega de Cartas Adhesi&oacute;n</a></li>
			</ul>
		</li>
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>
				<li><a href="<s:url value="/catalogos/busquedaCompradores"/>">Compradores</a></li>
				<li><a href="<s:url value="/catalogos/busquedaRepLegal"/>">Representantes Legales</a></li>		
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li> 
			</ul>
		</li>
	</s:elseif>
	<s:elseif test='#session.idPerfil==6'>
		<li><a href="#">Solicitud de Pago</a>
			<ul>
				<li><a href="<s:url value="/solicitudPago/listarPrograma"/>">Relaci&oacute;n de Documentos</a></li>
				<li><a href="<s:url value="/solicitudPago/listarProgramasPagosCartaAdhesion"/>">Trámite de Pago</a></li>
				<li><a href="<s:url value="/pagos/busquedaPagos"/>">Consulta de Pagos</a></li>		 		 
			</ul>
		</li>	
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>
				<li><a href="<s:url value="/catalogos/busquedaCompradores"/>">Compradores</a></li>	
				<li><a href="<s:url value="/catalogos/busquedaAuditores"/>">Auditores</a></li>
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li>		 
			</ul>
		</li>
		<li ><a  href="<s:url value="/pagos/busquedaPagosCADevoluciones"/>">Devoluciones</a></li>
	</s:elseif>
	<s:elseif test='#session.idPerfil==7'>
		<li><a href="#">Reportes</a>
			<ul>
				<li><a href="<s:url value="/reportes/capturaReporteDinamico"/>">Reportes Din&aacute;micos</a></li> 
				<li><a href="<s:url value="/reportes/rendicionCuentasCID"/>">Rendici&oacute;n de Cuentas (ID)</a></li> 
				<li><a href="<s:url value="/reportes/rendicionCuentasSID"/>">Rendici&oacute;n de Cuentas</a></li>
			</ul>
		</li>	
		<li><a href="#">SQL</a>
			<ul>
				<li><a href="<s:url value="/catalogos/ejecutaSQL"/>">Interprete SQL</a></li> 
			</ul>
		</li>	
	</s:elseif>
	<s:elseif test='#session.idPerfil==8'>
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>		
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li> 
			</ul>
		</li>	
	</s:elseif>
	<s:elseif test='#session.idPerfil==11'> <!-- Regionales -->
		<li><a  href="#">Seguimiento</a>
			<ul>		
				<li><a href="<s:url value="/seguimiento/listSeguimiento"/>">Registro</a></li> 
				<li><a href="<s:url value="/seguimiento/consultaReporteResumenAvance"/>">Reporte Concentrado</a></li>
			</ul>
		</li>
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>		
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li> 
			</ul>
		</li>	
	
	</s:elseif>
	<s:elseif test='#session.idPerfil==12'> <!-- DGPC -->
		<li><a  href="#">Seguimiento</a>
			<ul>		
				<li><a href="<s:url value="/seguimiento/listSeguimiento"/>">Registro</a></li> 
				<li><a href="<s:url value="/seguimiento/consultaReporteConsolidado"/>">Reporte de Avances</a></li>
				<li><a href="<s:url value="/seguimiento/consultaReporteDetalle"/>">Reporte de Avances (Detalle)</a></li>
				<li><a href="<s:url value="/seguimiento/consultaReporteResumenAvance"/>">Reporte Resumen</a></li>
			</ul>
		</li>
		<li><a  href="#">Cat&aacute;logos</a>
			<ul>		
				<li><a href="<s:url value="/catalogos/busquedaBodegas"/>">Bodegas</a></li>  
				<li><a href="<s:url value="/catalogos/busquedaCompradores"/>">Compradores Temporal</a></li>
			</ul>
		</li>	
	
	</s:elseif>
	
	<li><a href="<s:url value="/salir.action" />" class="esquinaDer">Salir</a></li>
</ul>