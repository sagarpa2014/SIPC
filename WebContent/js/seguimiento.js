$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

function chkCamposConsultaSeguimiento(){	
	var idCiclo = $('#idCiclo').val();
	var ejercicio = $('#ejercicio').val();
	var periodoInicial = $('#periodoInicial').val();
	var periodoFinal = $('#periodoFinal').val();
	var claveBodega = $('#claveBodega').val();
	if(idCiclo == -1 && ejercicio == -1 
		&& (periodoInicial == null || periodoInicial=="")
		&& (periodoFinal == null || periodoFinal=="")
		&& (claveBodega == null || claveBodega=="")){
		$('#dialogo_1').html('Debe capturar al menos un criterio de búsqueda ');
   		abrirDialogo();
 		return false;	
	}
}

/********SEGUIMIENTO DE ACOPIO*****/
function chkCamposSeguimientoAcopio(){	
	var validaVolumen = "";
	var validaImporte = "";
	var validaPorcentaje = "";

	$("#idCiclo").removeAttr("disabled");
	$("#ejercicio").removeAttr("disabled");
	$("#claveBodega").removeAttr("disabled");
	
	var registrar = $('#registrar').val();
	if(registrar != 3){
		if(registrar == 0){
			/***** CICLO ****/
			var idCiclo = $('#idCiclo').val();
			if(idCiclo == -1){
				$('#dialogo_1').html('Seleccione el ciclo agricola');
		   		abrirDialogo();
		 		return false;	
			}
			/***** EJERCICIO ****/
			var ejercicio = $('#ejercicio').val();
			if(ejercicio == -1){
				$('#dialogo_1').html('Seleccione el ejercicio');
		   		abrirDialogo();
		 		return false;	
			}
			
			/***** FECHA PERIODO INICIAL****/
			var periodoInicial = $('#periodoInicial').val();
			if(periodoInicial == null || periodoInicial==""){
				$('#dialogo_1').html('Seleccione el periodo inicial');
				abrirDialogo();
				return false;
			}
			/***** FECHA PERIODO FINAL ****/
			var periodoFinal = $('#periodoFinal').val();
			if(periodoFinal == null || periodoFinal==""){
				$('#dialogo_1').html('Seleccione el periodo final');
				abrirDialogo();
				return false;
			}
			
			/***** CLAVE BODEGA ****/
			var claveBodega = $('#claveBodega').val();
			if(claveBodega == null || claveBodega==""){
				$('#dialogo_1').html('Capture la clave bodega');
				abrirDialogo();
				return false;
			}
			
			if( $('#errorClaveBodega').val() ==1){
				$('#dialogo_1').html('Capture correctamente la clave de la bodega');
			   	abrirDialogo();
				return false;
			}
			
		}//END REGISTRAR = 0
		
		/***** CULTIVO ****/
		if( $('#idCultivo').val()==-1){
			$('#dialogo_1').html('Seleccione el cultivo');
		   	abrirDialogo();
			return false;
		}

		/***** VARIEDAD ****/
		if( $('#idVariedad').val()==-1){
			$('#dialogo_1').html('Seleccione la variedad');
		   	abrirDialogo();
			return false;
		}

		/***** VOLUMEN MERCADO LIBRE ****/
		var volumenMercadoLibre = $('#volumenMercadoLibre').val();
		if(volumenMercadoLibre != null && volumenMercadoLibre != ''){
	//		validaVolumen ="";
			validaVolumen = validarVolumen(volumenMercadoLibre,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del volumen Mercado Libre es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
	
		/***** VOLUMEN AXC ****/
		var volumenAXC = $('#volumenAXC').val();
		if(volumenAXC != null && volumenAXC != ''){
	//		validaVolumen ="";
			validaVolumen = validarVolumen(volumenAXC,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del volumen AXC es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
	
		/***** ACOPIO TONELADAS ****/
		var acopioTotalTon = $('#acopioTotalTon').val();
		if(acopioTotalTon == null || acopioTotalTon==""){
			$('#dialogo_1').html('Capture el Acopio Toneladas');
			abrirDialogo();
			return false;
		}else{
	//		validaVolumen ="";
			validaVolumen = validarVolumen(acopioTotalTon,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del Acopio Toneladas es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
/*			} else if (acopioTotalTon==0){
				$('#dialogo_1').html('El valor del Acopio Toneladas debe ser mayor a cero');
				abrirDialogo();
				return false;
*/
			}
		}
		$("#acopioTotalTon").removeAttr("disabled");
		$("#pagadoPorcentaje").removeAttr("disabled");
		
		/***** PAGADO EN TONELADAS ****/
		var pagadoTon = $('#pagadoTon').val();
		if(pagadoTon != null && pagadoTon != ""){
	//		validaVolumen ="";
			validaVolumen = validarVolumen(pagadoTon,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del volumen Pagado es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
		
		/***** PAGADO EN PORCENTAJE ****/
		var pagadoPorcentaje = $('#pagadoPorcentaje').val();
		if(pagadoPorcentaje != null && pagadoPorcentaje != ""){
	//		validaPorcentaje ="";
			validaPorcentaje = validarPorcentaje(pagadoPorcentaje,0,0);
			if(validaPorcentaje == 0){
				$('#dialogo_1').html('El valor del porcentaje Pagado es inválido, se deben capturar decimales y aceptan hasta 3 digitos a la izquierda y 2 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
		
		
		/***** MOVILIZADO FURGON ****/
		var mfurgon = $('#mfurgon').val();
		if(mfurgon != null && mfurgon != ''){
	//		validaVolumen ="";
			validaVolumen = validarVolumen(mfurgon,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del volumen movilizado vía FFCC es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
	
		
		/***** MOVILIZADO CAMION ****/
		var mcamion = $('#mcamion').val();
		if(mcamion != null && mcamion != ''){
	//		validaVolumen ="";
			validaVolumen = validarVolumen(mcamion,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del movilizado vía autotransporte de carga es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
		
		/***** MOVILIZADO MARITIMO ****/
		var mmaritimo = $('#mmaritimo').val();
		if(mmaritimo != null && mmaritimo != ''){
	//		validaVolumen ="";
			validaVolumen = validarVolumen(mmaritimo,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del movilizado vía marítima es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
		$("#mtotal").removeAttr("disabled"); 
		/***** EXISTENCIA (ACOPIO-MOVILIZADO) ****/
		var existenciaAM = $('#existenciaAM').val();
		if(existenciaAM == null || existenciaAM==""){
			$('#dialogo_1').html('Capture la existencia (Acopio-Movilizado)');
			abrirDialogo();
			return false;
		}else{
	//		validaVolumen ="";
			validaVolumen = validarVolumen(existenciaAM,0,0);
			if(validaVolumen == 0){
				$('#dialogo_1').html('El valor del volumen Existencia es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
		$("#existenciaAM").removeAttr("disabled");
		
		/***** PRECIO PROMEDIO PAGADO AXC ****/
		var precioPromPagAXC = $('#precioPromPagAXC').val();
		if(precioPromPagAXC != null && precioPromPagAXC != ""){
	//		validaImporte ="";
			validaImporte = validarImporte(precioPromPagAXC,0,0);
			if(validaImporte == 0){
				$('#dialogo_1').html('El valor del importe Precio Promedio Pagado es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
	
		
		/***** PRECIO PROMEDIO PAGADO LIBRE ****/
	/*
		var precioPromPagLibre = $('#precioPromPagLibre').val();
		if(precioPromPagLibre != null && precioPromPagLibre != ""){
			validaImporte ="";
			validaImporte = validarImporte(precioPromPagLibre,0,0);
			if(validaImporte == 0){
				$('#dialogo_1').html('El valor del importe Precio Promedio Pagado Libre es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
				return false;
			}
		}
	*/	
	
		/***** AVANCE COSECHA ****/
		var avanceCosecha = $('#avanceCosecha').val();
		if(avanceCosecha != null && avanceCosecha != ""){
	//		validaPorcentaje ="";
			validaPorcentaje = validarPorcentaje(avanceCosecha,0,0);
			if(validaPorcentaje == 0){
				$('#dialogo_1').html('El valor del porcentaje de Avance es inválido, se deben capturar decimales y aceptan hasta 3 digitos a la izquierda y 2 máximo a la derecha');
				abrirDialogo();
				return false;
			}
		}
		
		/***** FECHA ENVIO ****/
		var fechaEnvio = $('#fechaEnvio').val();
		if(fechaEnvio == null || fechaEnvio==""){
			$('#dialogo_1').html('Seleccione la fecha de envío del reporte');
			abrirDialogo();
			return false;
		}
	} else {
		/*********JUSTIFICACION DE CAMBIOS*****/
		var justificacionAutoriza = $('#justificacionAutoriza').val();
		if(justificacionAutoriza == null || justificacionAutoriza==""){
			$('#dialogo_1').html('Capture la justificacion de la autorizacion de cambios al registro');
			abrirDialogo();
			return false;
		}

		/*********ARCHIVO DE JUSTIFICACION DE CAMBIOS*****/
		var doc = $('#doc').val();
		if(doc == null || doc ==""){
			$('#dialogo_1').html('Seleccione el archivo para justificacion de cambios');
			abrirDialogo();
			return false;
		}
	}
}// END chkCamposSeguimientoAcopio




function validaClaveBodega(){
	
	//Valida que la bodega exista en la base de datos
	var claveBodega =   $('#claveBodega').val();
	if((claveBodega == "" || claveBodega == null)){
		return false;
	}	
	var idCiclo =   $('#idCiclo').val();
	var ejercicio =   $('#ejercicio').val();
	var idCultivo =   $('#idCultivo').val();
	var idVariedad =   $('#idVariedad').val();
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "validaClaveBodega", 
		   data: "claveBodega="+claveBodega+
		   		 "&idCiclo="+idCiclo+
		   		 "&ejercicio="+ejercicio+
		   		"&idCultivo="+idCultivo+
		   		"&idVariedad="+idVariedad,
		   success: function(data){
			   var $response=$(data);
			   var errorClaveBodega = $response.filter('#errorCB').text();
//			   var nombreCentroAcopio = $response.filter('#nombreCentroAcopioR').text();
//			   var nomRegionalCentroAcopio = $response.filter('#nomRegionalCentroAcopioR').text();
//			   var operadorCentroAcopio = $response.filter('#operadorCentroAcopioR').text();
//			   
			   var existenciaAMAnt = $response.filter('#existenciaAMR').text();
		   
			   if(errorClaveBodega == 1){
				   $('#errorClaveBodega').val(1);
			   }else{
				   $('#errorClaveBodega').val(0);
			   }
//			   console.log("nombreCentroAcopio"+nombreCentroAcopio);	
//			   $('#nombreCentroAcopio').val(nombreCentroAcopio);
//			   $('#nomRegionalCentroAcopio').val(nomRegionalCentroAcopio);
//			   $('#operadorCentroAcopio').val(operadorCentroAcopio);
			   
			   if(existenciaAMAnt == null || existenciaAMAnt == ''){
				   existenciaAMAnt = 0;
			   }
			   $('#existenciaAMAnt').val(existenciaAMAnt);
			   
			   $("#validaClaveBodega").html(data).ready(function () {	
				
			   });
		   }
		});//fin ajax
}


function recuperaExistenciaEnSeguimiento(){
	var claveBodega =   $('#claveBodega').val();
		
	var idCiclo =   $('#idCiclo').val();
	var ejercicio =   $('#ejercicio').val();
	var idCultivo =   $('#idCultivo').val();
	var idVariedad =   $('#idVariedad').val();
	
	if((claveBodega != "" && claveBodega != null 
			&& idCiclo != -1 && ejercicio != -1 
			&& idCultivo != -1 && idVa )){
		return false;
	}
}


function validarVolumen(volumen, id, msj){
//	alert('volumen: '+volumen+ 'id: '+id+ 'msj: '+msj);
	var patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
	if (!volumen.match(patron)){	
		if(id!=0){
			$('#'+id).val(null);
		}
//		if(msj==1){
//			$('#dialogo_1').html('El valor del volumen es incorrecto, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
//			abrirDialogo();
			//return false;
//		}
		return 0;
	}else{
		return 1;
	}
}

function validarImporte(importe, id, msj){
	var patron =/^\d{1,10}((\.\d{1,2})|(\.))?$/;
	if (!importe.match(patron)){
		if(id!=0){
			$('#'+id).val(null);	
		}
			
//		if(msj==1){
//			$('#dialogo_1').html('El valor del importe es incorrecto, se deben capturar decimales y se aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
//			abrirDialogo();
//		}
		return 0;
	}else{
		return 1;
	}
}

function validarPorcentaje(cantidad, id, msj){
	var patron =/^\d{1,3}((\.\d{1,2})|(\.))?$/;
	if (!cantidad.match(patron)){
		if(id!=0){
			$('#'+id).val(null);	
		}
			
//		if(msj==1){
//			$('#dialogo_1').html('El valor del porcentaje es incorrecto, se deben capturar decimales y se aceptan hasta 3 digitos a la izquierda y 2 máximo a la derecha');
//			abrirDialogo();
//		}
		return 0;
	}else{
		return 1;
	}
}


function getTotalMovilizado(volumen, id, msj){
	if(volumen!=null && volumen!=''){
		if(volumen>0){
			var v = validarVolumen(volumen, id, msj);
			if(v==0){
				return false;
			}			
		}
	}	
	
	var totalVolumenMovilizado = 0;	
	var mfurgon =  $('#mfurgon').val();
	var mcamion =  $('#mcamion').val();
	var mmaritimo =  $('#mmaritimo').val();
	if(mfurgon!=null && mfurgon !=''){
		totalVolumenMovilizado = (totalVolumenMovilizado+parseFloat(mfurgon));	
	}	
	if(mcamion!=null && mcamion !=''){
		totalVolumenMovilizado = (totalVolumenMovilizado+parseFloat(mcamion));	 
	}	
	if(mmaritimo!=null && mmaritimo !=''){
		totalVolumenMovilizado = (totalVolumenMovilizado+parseFloat(mmaritimo));	
	}	
	var totalExistencia = 0.0;	
	var acopioTotalTon =  $('#acopioTotalTon').val();
	var existenciaAMAnt =  $('#existenciaAMAnt').val();
	var existencia = parseFloat(acopioTotalTon)+parseFloat(existenciaAMAnt);
	if(parseFloat(totalVolumenMovilizado) > existencia){
		$('#'+id).val(null);
		$('#dialogo_1').html('No se puede movilizar mas de lo acopiado');
		abrirDialogo();
		return false;
	}
	$('#mtotal').val(totalVolumenMovilizado.toFixed(3));
	var mtotal =  $('#mtotal').val();
	var existenciaAMAnt =  $('#existenciaAMAnt').val();
	totalExistencia = parseFloat(existenciaAMAnt) + parseFloat(acopioTotalTon-mtotal);
//alert('existenciaAMAnt: '+existenciaAMAnt+' (acopioTotalTon-mtotal): '+(acopioTotalTon-mtotal)+' totalExistencia: '+totalExistencia);	
	$('#existenciaAM').val(totalExistencia.toFixed(3));
}

function vistaPreviaOficio(idSeguimiento){
//	var idSeguimiento = $('#idSeguimiento').val();
//	alert("idSeguimiento: "+idSeguimiento);
	$("#reporte1").fadeOut('5000');
		
	$("#reporte2").fadeOut('5000', function(){
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "vistaPreviaOficio",
			   data: "idSeguimiento="+idSeguimiento,
			   success: function(data){
					$('#vistaPrevia').html(data).ready(function () {
						$("#vistaPrevia").fadeIn('slow');
					});
			   }
			});
		});

}

function cerrarVistaPreviaOficio(){
	
	$("#vistaPrevia").fadeOut('3000', function(){
		$("#reporte1").fadeIn('3000');	
		$("#reporte2").fadeIn('3000');
	});
}

function cerrarErrorOficio(){
	$("#resultadoGeneracionOficio").fadeOut('3000', function(){
		$("#reporte1").fadeIn('3000');	
		$("#reporte2").fadeIn('3000');	
	});
}

function generarFormatoSeguimiento(idSeguimiento){
//	var idSeguimiento = $('#idSeguimiento').val();
	$("#reporte1").fadeOut('5000');
	
	$("#reporte2").fadeOut('5000', function(){
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "generarReporteSeguimiento",
			   data: "idSeguimiento="+idSeguimiento,
			   success: function(data){
					$('#resultadoGeneracionOficio').html(data).ready(function () {
						$("#resultadoGeneracionOficio").fadeIn('slow');
					});
			   }
			});
		});
}

function getTotalAcopio(volumen, id, msj){
	if(volumen!=null && volumen!=''){
		if(volumen>0){
			var v = validarVolumen(volumen, id, msj);
			if(v==0){
				return false;
			}			
		}
	}

	var totalVolumenAcopio = 0;	
	var volumenMercadoLibre =  $('#volumenMercadoLibre').val();
	var volumenAXC =  $('#volumenAXC').val();
	if(volumenMercadoLibre!=null && volumenMercadoLibre !=''){
		totalVolumenAcopio = (totalVolumenAcopio+parseFloat(volumenMercadoLibre));	
	}	
	if(volumenAXC!=null && volumenAXC !=''){
		totalVolumenAcopio = (totalVolumenAcopio+parseFloat(volumenAXC));	 
	}		
	$('#acopioTotalTon').val(totalVolumenAcopio.toFixed(3));
	var acopioTotalTon =  $('#acopioTotalTon').val();	
	var totalExistencia = 0.0;	
	var mtotal =  $('#mtotal').val();
	var existenciaAMAnt =  $('#existenciaAMAnt').val();
	totalExistencia = parseFloat(existenciaAMAnt) + parseFloat(acopioTotalTon-mtotal);
	//alert('existenciaAMAnt: '+existenciaAMAnt+' (acopioTotalTon-mtotal): '+(acopioTotalTon-mtotal)+' totalExistencia: '+totalExistencia);	
	$('#existenciaAM').val(totalExistencia.toFixed(3));
}

function chkCampoConsultaReporteConsolidado(){
	var idEstado = $('#idEstadoSeg').val();
	var idCultivo = $('#idCultivoSeg').val();
	var idCiclo = $('#idCicloSeg').val();
	if((idEstado == -1) || (idCultivo == -1) || (idCiclo == -1)){
		$('#dialogo_1').html('Debe capturar todos los criterios de busqueda, para realizar la consulta');
   		abrirDialogo();
   		return false;
	}
}

function chkCampoConsultaReporteDetalle(){
	var idEstado = $('#idEstadoSeg').val();
	var idCultivo = $('#idCultivoSeg').val();
	var idCiclo = $('#idCicloSeg').val();
	if((idEstado == -1) || (idCultivo == -1) || (idCiclo == -1)){
		$('#dialogo_1').html('Debe capturar todos los criterios de busqueda, para realizar la consulta');
   		abrirDialogo();
   		return false;
	}
}

function chkCampoConsultaReporteResumen(){
	var idCiclo = $('#idCicloSeg').val();
	if((idCiclo == -1)){
		$('#dialogo_1').html('Debe capturar el criterio de busqueda, para realizar la consulta');
   		abrirDialogo();
   		return false;
	}
}

function recuperaVariedadByCultivo(idCultivo){
	if(idCultivo == -1){
		return false;
	}

	var claveBodega = $('#claveBodega').val();
	var idCiclo = $('#idCiclo').val();
	var ejercicio = $('#ejercicio').val();
	var idCultivo = $('#idCultivo').val();
	var idVariedad = $('#idVariedad').val();	
	
//	alert('claveBodega: '+claveBodega+ ' idCiclo: '+idCiclo+ ' ejercicio: '+ejercicio+' cultivo: '+idCultivo);
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaVariedadByCultivo",
		   data: "claveBodega="+claveBodega+
	   		 	 "&idCiclo="+idCiclo+
	   		 	 "&ejercicio="+ejercicio+
	   		 	 "&idCultivo="+idCultivo+
	   		 	 "&idVariedad="+idVariedad,
		   success: function(data){
			   	var $response=$(data);
			   	var existenciaAMAnt = $response.filter('#existenciaAMR').text();
			   	$('#existenciaAMAnt').val(existenciaAMAnt);
			   	
				$("#variedad").html(data).ready(function () {
				});
		   }
		});//fin ajax
}


function calcularPorcentajeDeLoPagado(pagadoTon){	
	var validaVolumen = validarVolumen(pagadoTon,0,0);
	if(validaVolumen == 0){
		$('#dialogo_1').html('El valor de lo pagado es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
		abrirDialogo();
		return false;
	}
	var acopioTotalTon =  $('#acopioTotalTon').val();
	var pagadoPorcentaje = 0;
	if(acopioTotalTon !=null && acopioTotalTon != ''){
		pagadoPorcentaje = parseFloat(pagadoTon * 100) / parseFloat(acopioTotalTon);
		$('#pagadoPorcentaje').val(pagadoPorcentaje.toFixed(2));
	}
	
}
