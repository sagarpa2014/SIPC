<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:if test="hasActionErrors()">
  	 <s:include value="/WEB-INF/paginas/template/abrirMensajeDialogo.jsp" />
</s:if>
<s:if test="msjOk!=null && msjOk !=''">
	<div id="mjsS"><div  class="msjSatisfactorio"><s:property value="%{msjOk}"/></div></div>	
</s:if>
<div class="borderBottom"><h1>AVISOS DE APOYOS</h1></div><br>
<div id="dialogo_1"></div>
<div class="clear"></div><br>
<s:form action="registroInicializaEsquema"  method="post" enctype="multipart/form-data" onsubmit="return chkCamposInicializa();">
	<s:elseif test="editar==3||editar==4">
		<s:hidden id="idPrograma" name="idPrograma" value="%{idPrograma}"/>
		<s:hidden name="numCamposAnterior" id="numCamposAnterior" value="%{numCamposAnterior}"/>
		<s:hidden name="numCamposVXCVAnterior" id="numCamposVXCVAnterior" value="%{numCamposVXCVAnterior}"/>
	</s:elseif>
	<s:hidden id="editar" name="editar" value="%{editar}"/>
	
	<fieldset class="formulario" >
		<legend>
			<s:if test="editar==0 || editar==1">Capture los datos del apoyos</s:if>
			<s:elseif test="editar==2">Consulta de Aviso de Apoyo</s:elseif>
			<s:elseif test="editar==3">Edici&oacute;n de Aviso de Apoyo</s:elseif>
			<s:elseif test="editar==4">Adici&oacute;n de Aviso de Apoyo</s:elseif>
		</legend>
		<div class="borderBottom"><h1>Titulo Aviso</h1></div><br> 
		<div>
			<label class="left1"><span class="requerido">*</span>Fecha de publicaci&oacute;n DOF</label>
			<s:if test="%{fechaPeticion==null}" >
				<s:textfield name="fechaPeticion" value="%{fechaPeticion}"  maxlength="10" size="10" id="fechaPeticion" readonly="true" cssClass="dateBox" />
			</s:if>
			<s:else>
				<s:textfield key="fechaPeticion" value="%{fechaPeticion}"  maxlength="10" size="10" id="fechaPeticion" readonly="true" cssClass="dateBox" />
			</s:else>
			<img src="../images/calendar.gif" id="trg1" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>T&iacute;tulo Aviso:</label>
			<s:textfield id="descLineamiento" name="descLineamiento"  maxlength="500" size="100"  value="%{descLineamiento}"/>
		</div>
		<div class="clear"></div>
		<s:if test="editar==2 || editar==3 || editar ==4">
			<s:if test="editar==2 || editar == 4">
				<s:if test="lstHcoProgramasV.size()>0">
					<s:iterator value="lstHcoProgramasV" var="resultado"  status="itStatus">
						<div>
							<label class="left1"><span class="requerido">*</span>Adici&oacute;n publicaci&oacute;n DOF</label>
							<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{rutaPrograma+archivoPublicacionDof}"/>" title="	i&oacute;n DOF"><s:text name="fecha"><s:param value="%{fechaPublicacionDof}"/></s:text></a>
						</div>
						<div class="clear"></div>
					</s:iterator>
				</s:if>
			</s:if>
			<div>
				<label class="left1"><span class="requerido">*</span>Archivo de publicaci&oacute;n DOF</label>
				<a href="<s:url value="/devuelveArchivoByRuta?rutaCompleta=%{archivoAvisoDOF}"/>" title="Archivo de Publicaci&oacute;n DOF">Archivo DOF</a>
			</div>
		</s:if>
		<div class="clear"></div>
		<s:if test="editar==0 || editar==1 || editar==3 || editar ==4">
			<div>
				<label class="left1"><span class="requerido">*</span>Archivo de publicaci&oacute;n DOF</label>
				<s:file  name="doc" id="doc"/>
			</div>
		</s:if>	
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Area responsable:</label>
			<s:select id="idArea" name="idArea" list="lstAreasResponsables" listKey="idArea" listValue="%{area}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0"  value="%{idArea}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Carta Adhesi&oacute;n por sistema:</label>
			<s:checkbox id="cartaAdhesionSistema"  name="cartaAdhesionSistema" fieldValue="true" value="%{cartaAdhesionSistema}" />
		</div>
		<div class="clear"></div>
		<div class="borderBottom" style="text-align:center"><h1>Apoyos</h1></div><br> 
		<div>
			<label class="left1"><span class="requerido">*</span>Concepto de Apoyo:</label>
			<s:select id="idComponente" name="idComponente" list="lstComponente" listKey="idComponente" listValue="%{componente}" headerKey="-1" headerValue="-- Seleccione una opción --" value="%{idComponente}" />
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Descripci&oacute;n Corta:</label>
			<s:textfield id="descCorta" name="descCorta"  maxlength="50" size="50"  value="%{descCorta}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Definici&oacute;n  de Acr&oacute;nimo:</label>
			<s:textfield id="acronimoCA" name="acronimoCA"  maxlength="50" size="50"  value="%{acronimoCA}"/>
		</div>
		<div class="clear"></div>
		<div id="validarAcronimo"></div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Leyenda Atenta Nota:</label>
			<s:textfield id="leyendaAtentaNota" name="leyendaAtentaNota"  maxlength="200" size="100"  value="%{leyendaAtentaNota}"/>
		</div>
		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>N&uacute;mero de Ciclos:</label>
			<s:select id="numCiclos" name="numCiclos" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'1':'1', '2':'2', '3':'3', '4':'4'}"  onchange="consigueNumCiclos(this.value);" value="%{numCiclos}"/>
		</div>
		<div class="clear"></div>
		<div id="consigueNumCiclos">
			<s:if test="editar==1 || editar ==2 || editar == 3 || editar ==4">
				<s:include value="/WEB-INF/paginas/inscripcion/inicializacionPrograma/ciclos.jsp" />
			</s:if>			
		</div>

		<div class="clear"></div>
		<div>
			<label class="left1"><span class="requerido">*</span>Criterio Pago:</label>
			<s:select id="idCriterioPago" name="idCriterioPago" list="lstCriterioPago" listKey="idCriterioPago" listValue="%{criterioPago}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" onchange="selectCriterioPago();" value="%{idCriterioPago}"/>
		</div>
		<div class="clear"></div>
		
		<div class="clear"></div>
		<div id ="selectCriterioPago">
			<s:if test="editar==1 || editar==2 || editar==3 || editar==4">
				<s:include value="/WEB-INF/paginas/inscripcion/inicializacionPrograma/criterioPago.jsp" />
			</s:if>	
		</div>
		
		<div class="clear"></div>		
	<div class="clear"></div>
	<div class="borderBottom"><h1>Tiempos del tr&aacute;mite</h1></div><br> 
	<div class="clear"></div>
	
	
	<div class="izquierda">
		<label><span class="requerido">*</span>Publicaci&oacute;n de Avisos en DOF hasta Presentaci&oacute;n de Sol. de Inscripci&oacute;n:&nbsp;&nbsp;</label>
	</div>
	<div class="izquierda">
		<s:textfield id="periodoDOFSI" name="periodoDOFSI"  maxlength="10" size="10"  value="%{periodoDOFSI}"/>
	</div>
	<div class="izquierda">
		<label>&nbsp;&nbsp;(No. de D&iacute;as)</label>
	</div>
	<div class="izquierda">
		<label><span class="requerido">*</span>Emisi&oacute;n oficio obs de Sol. ins hasta recepción oficio respuesta Obs Sol inscripci&oacute;n </label>
	</div>
	<div class="izquierda">
		<s:textfield id="periodoOSIROSI" name="periodoOSIROSI"  maxlength="10" size="10"  value="%{periodoOSIROSI}"/>
	</div>
	<div class="izquierda">
		<label>&nbsp;&nbsp;(No. de D&iacute;as)</label>
	</div>	
	<div class="izquierda">
		<label><span class="requerido">*</span>Emisi&oacute;n de Carta Adhesi&oacute;n hasta Presentaci&oacute;n de Sol. de Pago:&nbsp;&nbsp;</label>
	</div>
	<div class="izquierda">
		<s:textfield id="periodoCASP" name="periodoCASP"  maxlength="10" size="10"  value="%{periodoCASP}"/>
	</div>
	<div class="izquierda">
		<label>&nbsp;&nbsp;(No. de D&iacute;as)</label>
	</div>	
	<div class="izquierda">
		<label><span class="requerido">*</span>Presentaci&oacute;n de Sol. de Pago hasta Presentaci&oacute;n del Oficio de Observaciones:&nbsp;&nbsp;</label>
	</div>
	<div class="izquierda">
		<s:textfield id="periodoSPOO" name="periodoSPOO"  maxlength="10" size="10"  value="%{periodoSPOO}"/>
	</div>
	<div class="izquierda">
		<label>&nbsp;&nbsp;(No. de D&iacute;as)</label>
	</div>
	
	<div class="izquierda">
		<label><span class="requerido">*</span>Recepci&oacute;n de Oficio Respuesta Observaciones hasta Emisi&oacute;n del Pago:&nbsp;&nbsp;</label>
	</div>
	<div class="izquierda">
		<s:textfield id="periodoORPago" name="periodoORPago"  maxlength="10" size="10"  value="%{periodoORPago}"/> 
	</div>
	<div class="izquierda">
		<label>&nbsp;&nbsp;(No. de D&iacute;as)</label>
	</div>
	<div>
		<p><span class="requerido">* Debe capturar los valores de los campos obligatorios</span></p>
	</div>
	</fieldset>
	<br>
	<s:if test="editar==0 || editar==1 || editar==3 || editar ==4">
		<div class="accion">
			<s:submit  value="Guardar" cssClass="boton2" />
			<a href="<s:url value="/inscripcion/listarProgramas"/>" class="boton" title="" >Cancelar</a>
		</div>
	</s:if>
	<s:elseif test="editar==2">
		<div class="accion">
			<a href="<s:url value="/inscripcion/listarProgramas"/>" class="boton" title="" >Regresar</a>
		</div>
	</s:elseif>
</s:form>
<s:if test="editar == 2"> <!-- Consulta -->
	<script>
		$(document).ready(function() {	
			$("input").attr('disabled','disabled');
			$("select").attr('disabled','disabled');
		});	 
	</script>
</s:if>
<s:if test="editar==4"> <!-- Adiciones -->
	<script>
		$(document).ready(function() {
			var numCiclos = $('#numCiclos').val();
			$("#numCiclos").attr('disabled','disabled');
			var i=0;
			for( i=1; i<parseInt(numCiclos)+1; i++){
				$('#ci'+i).attr('disabled','disabled');
				$('#a'+i).attr('disabled','disabled');
			}
			
		});	 
	</script>
</s:if>


<script>
$(document).ready(function() {	
	
	$("#acronimoCA").keyup(function(event) {	
		validarAcronimo();
	});
	
    $("#periodoDOFSI").keyup(function(event) {	
        var v = $('#periodoDOFSI').val();
        var patron =/^\d{1,10}$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 10 dígitos');
       		abrirDialogo();
       		$('#periodoDOFSI').val(null);
       		return false;
    	}        
    }); //end change #cPeriodoDOFSI

    $("#periodoOSIROSI").keyup(function(event) {	
        var v = $('#periodoOSIROSI').val();
        var patron =/^\d{1,10}$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 10 dígitos');
       		abrirDialogo();
       		$('#periodoOSIROSI').val(null);
       		return false;
    	}        
    }); //end change #periodoOSIROSI
    
    $("#periodoCASP").keyup(function(event) {	
        var v = $('#periodoCASP').val();
        var patron =/^\d{1,10}$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 10 dígitos');
       		abrirDialogo();
       		$('#periodoCASP').val(null);
       		return false;
    	}        
    }); //end change #periodoCASP

    $("#periodoSPOO").keyup(function(event) {	
        var v = $('#periodoSPOO').val();
        var patron =/^\d{1,10}$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 10 dígitos');
       		abrirDialogo();
       		$('#periodoSPOO').val(null);
       		return false;
    	}        
    }); //end change #periodoSPOO

    $("#periodoORPago").keyup(function(event) {	
        var v = $('#periodoORPago').val();
        var patron =/^\d{1,10}$/;
    	if(v=="" || v==null){
    		return false;
    	}
    	if(!v.match(patron)){
    		$('#dialogo_1').html('El valor del campo es inválido, se aceptan hasta 10 dígitos');
       		abrirDialogo();
       		$('#periodoORPago').val(null);
       		return false;
    	}        
    }); //end change #periodoORPago
    	
	
	
});	 
</script>

<script type="text/javascript">
	<!--
		Calendar.setup({
			inputField     :    "fechaPeticion",     
			ifFormat       :    "%d/%m/%Y",     
			button         :    "trg1",  
			align          :    "Br",           
			singleClick    :    true
		});		
	//-->
</script>